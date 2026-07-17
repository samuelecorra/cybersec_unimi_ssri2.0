/* =====================================================================
 * ripd.c  —  Implementazione di RIPv2 (RFC 2453).
 *
 * MODELLO. RIP è "distance-vector": periodicamente ogni router annuncia
 * ai vicini l'intera tabella ("so raggiungere X a metrica m"). Chi riceve
 * somma 1 alla metrica e tiene il percorso più corto. La metrica è il
 * numero di hop; 16 = infinito = irraggiungibile.
 *
 * STABILITÀ. Due meccanismi anti-loop:
 *   - split horizon con "poisoned reverse": una rotta appresa da
 *     un'interfaccia viene riannunciata SU quella stessa interfaccia con
 *     metrica 16 (dico al vicino "da me non passare per tornare a te").
 *   - triggered update: al cambiare di una rotta si annuncia subito, senza
 *     attendere il periodico, per accelerare la convergenza.
 *
 * TIMER per rotta: se non arrivano update per 180 s la rotta è invalidata
 * (metrica 16); dopo altri 120 s viene rimossa (garbage collection).
 *
 * Nota implementativa: NON usiamo un socket UDP del kernel. Costruiamo
 * l'INTERO frame (Ethernet+IP+UDP+RIP) a mano e lo spariamo sul socket
 * raw, coerentemente con la scelta "tutto userspace".
 * ===================================================================== */
#include <stdlib.h>    /* rand (per il jitter dei timer)                     */
#include <string.h>

#include "arp.h"
#include "log.h"
#include "netio.h"
#include "parse.h"
#include "rib.h"
#include "ripd.h"

#define RIP_CMD_REQUEST  1     /* "mandami la tua tabella"                   */
#define RIP_CMD_RESPONSE 2     /* "ecco la mia tabella" (annuncio)          */
#define RIP_MAX_ENTRIES  25    /* max rotte per datagramma (RFC 2453)       */
#define RIP_TIMEOUT      180   /* s senza update -> rotta invalidata        */
#define RIP_GARBAGE      120   /* s dopo l'invalidazione -> rotta rimossa   */

/* MAC multicast corrispondente all'IP 224.0.0.9: gli indirizzi multicast
 * IPv4 si mappano su MAC 01:00:5e + i 23 bit bassi dell'IP (qui 0.0.09). */
static const uint8_t RIP_MCAST_MAC[6] = { 0x01, 0x00, 0x5e, 0x00, 0x00, 0x09 };

/* Header RIP (4 byte), seguito da 0..25 entry. */
struct rip_hdr {
    uint8_t  command;   /* 1 request, 2 response                            */
    uint8_t  version;   /* 2 per RIPv2                                       */
    uint16_t zero;      /* deve essere 0 (must be zero)                     */
} __attribute__((packed));

/* Una entry RIPv2 (20 byte). I campi mask/next_hop sono la novità di v2
 * rispetto a v1 e sono ciò che permette il classless (VLSM) e l'interop. */
struct rip_entry {
    uint16_t af;        /* Address Family: 2 = IPv4 (0 = "tutta la tabella") */
    uint16_t tag;       /* Route Tag (per marcare rotte esterne; noi 0)     */
    uint32_t ip;        /* indirizzo della rete                             */
    uint32_t mask;      /* subnet mask (RIPv2)                              */
    uint32_t next_hop;  /* next-hop suggerito (0 = "usa me come next-hop")  */
    uint32_t metric;    /* metrica 1..16                                    */
} __attribute__((packed));

static bool   enabled;      /* RIP attivo su almeno un'interfaccia?         */
static time_t t_periodic;   /* prossimo update periodico                    */
static time_t t_trigger;    /* prossimo triggered update; 0 = nessuno       */

/* Dopo aver annunciato, azzera il flag "changed" di tutte le rotte. */
static void clear_changed(void)
{
    for (int i = 0; i < rib_size(); i++)
        rib_slot(i)->changed = false;
}

/* Programma un triggered update fra 1-4 s (ritardo casuale). Il ritardo
 * evita raffiche di annunci quando cambiano più rotte insieme, e la
 * sincronizzazione fra router (RFC 2453 §3.10.1). */
static void schedule_trigger(void)
{
    if (enabled && !t_trigger)
        t_trigger = mono_now() + 1 + rand() % 4;
}

/* Costruisce e trasmette un frame completo Ethernet+IP+UDP che trasporta
 * 'payload' (un messaggio RIP) verso dst_ip/dst_mac. */
static void send_packet(struct router *rt, int ifidx, uint32_t dst_ip,
                        const uint8_t *dst_mac, uint16_t dport,
                        const void *payload, size_t plen)
{
    static uint16_t ip_id = 0x1000;
    struct iface *ifc = &rt->ifaces[ifidx];
    uint8_t buf[FRAME_BUF_SZ];
    /* I tre header si sovrappongono al buffer a offset fissi:
     * Ethernet(14) | IP(20) | UDP(8) | RIP payload. */
    struct eth_hdr  *eh = (struct eth_hdr *)buf;
    struct ipv4_hdr *ih = (struct ipv4_hdr *)(buf + ETH_HDR_LEN);
    struct udp_hdr  *uh = (struct udp_hdr *)(buf + ETH_HDR_LEN + 20);

    /* Copia il payload RIP subito dopo l'header UDP. */
    memcpy(buf + ETH_HDR_LEN + 20 + 8, payload, plen);

    /* --- Ethernet --- */
    memcpy(eh->dst, dst_mac, 6);
    memcpy(eh->src, ifc->mac, 6);
    eh->ethertype = htons(ETHERTYPE_IPV4);

    /* --- IP --- (ver_ihl 0x45 = IPv4, header 20 byte) */
    ih->ver_ihl  = 0x45;
    ih->tos      = 0xC0;   /* CS6/DSCP 48: marca il traffico come "controllo di rete" */
    ih->tot_len  = htons((uint16_t)(20 + 8 + plen));
    ih->id       = htons(ip_id++);
    ih->frag_off = 0;
    /* TTL 1 per il multicast RIP: gli annunci non devono uscire dalla LAN
     * (i vicini sono a un solo hop); unicast (risposta a una request) TTL 64. */
    ih->ttl      = (dst_ip == htonl(RIP_MCAST_HOST)) ? 1 : 64;
    ih->proto    = IPPROTO_UDP;
    ih->checksum = 0;
    ih->saddr    = ifc->ip;
    ih->daddr    = dst_ip;
    ih->checksum = inet_cksum(ih, 20);

    /* --- UDP --- (porta sorgente e destinazione 520) */
    uh->sport    = htons(RIP_PORT);
    uh->dport    = htons(dport);
    uh->len      = htons((uint16_t)(8 + plen));
    uh->checksum = 0;
    uh->checksum = udp_cksum(ih->saddr, ih->daddr, uh, 8 + plen);

    netio_send(ifc, buf, ETH_HDR_LEN + 20 + 8 + plen);
}

/* Invia un Response (annuncio) su una interfaccia. Se changed_only è vero
 * manda solo le rotte modificate (triggered update), altrimenti tutte
 * (update periodico o risposta a una request). Applica poisoned reverse. */
static void send_update(struct router *rt, int ifidx, bool changed_only,
                        uint32_t dst_ip, const uint8_t *dst_mac, uint16_t dport)
{
    /* payload = header RIP (4 byte) + fino a 25 entry. */
    uint8_t payload[4 + RIP_MAX_ENTRIES * sizeof(struct rip_entry)];
    struct rip_hdr   *rh = (struct rip_hdr *)payload;
    struct rip_entry *re = (struct rip_entry *)(payload + 4);
    int n = 0, sent = 0;

    rh->command = RIP_CMD_RESPONSE;
    rh->version = 2;
    rh->zero    = 0;

    for (int i = 0; i < rib_size(); i++) {
        struct rib_entry *e = rib_slot(i);
        if (!e->in_use)
            continue;
        if (changed_only && !e->changed)     /* triggered: solo le cambiate  */
            continue;
        /* POISONED REVERSE: se la rotta esce proprio da QUESTA interfaccia,
         * la annunciamo qui con metrica infinita (16). Così il vicino da
         * cui l'abbiamo imparata non crederà mai di poterci usare per
         * raggiungerla: è la difesa base contro i loop di routing. */
        uint8_t metric = (e->ifidx == ifidx) ? RIP_INFINITY : e->metric;
        re[n].af       = htons(2);           /* AFI = IPv4 (obbligo interop) */
        re[n].tag      = 0;
        re[n].ip       = e->prefix;
        re[n].mask     = prefix_to_mask(e->prefix_len);
        re[n].next_hop = 0;                  /* 0 = "usate me come next-hop" */
        re[n].metric   = htonl(metric);
        n++;
        /* Se raggiungiamo il massimo di entry per datagramma, spediamo e
         * ricominciamo un nuovo pacchetto. */
        if (n == RIP_MAX_ENTRIES) {
            send_packet(rt, ifidx, dst_ip, dst_mac, dport, payload,
                        4 + n * sizeof(struct rip_entry));
            sent += n;
            n = 0;
        }
    }
    if (n) {                                 /* le entry rimanenti           */
        send_packet(rt, ifidx, dst_ip, dst_mac, dport, payload,
                    4 + n * sizeof(struct rip_entry));
        sent += n;
    }
    if (sent)
        LOG_DEBUG("RIP: %d rotte inviate su %s%s", sent,
                  rt->ifaces[ifidx].name, changed_only ? " (triggered)" : "");
}

/* Invia una Request "dammi tutta la tabella" (usata all'avvio). La forma
 * canonica è UNA entry con AFI=0 e metrica 16 (RFC 2453 §3.9.1). */
static void send_request(struct router *rt, int ifidx)
{
    uint8_t payload[4 + sizeof(struct rip_entry)];
    struct rip_hdr   *rh = (struct rip_hdr *)payload;
    struct rip_entry *re = (struct rip_entry *)(payload + 4);

    rh->command = RIP_CMD_REQUEST;
    rh->version = 2;
    rh->zero    = 0;
    memset(re, 0, sizeof *re);
    re->metric = htonl(RIP_INFINITY);   /* af=0 + metrica 16 = "tutta la tabella" */

    send_packet(rt, ifidx, htonl(RIP_MCAST_HOST), RIP_MCAST_MAC, RIP_PORT,
                payload, sizeof payload);
    LOG_INFO("RIP: request iniziale su %s", rt->ifaces[ifidx].name);
}

void ripd_init(struct router *rt)
{
    /* RIP è attivo solo se almeno un'interfaccia ha l'opzione "rip". */
    enabled = false;
    for (int i = 0; i < rt->n_ifaces; i++)
        if (rt->ifaces[i].rip)
            enabled = true;
    if (!enabled) {
        LOG_INFO("RIP disabilitato (nessuna interfaccia con opzione rip)");
        return;
    }
    /* All'avvio chiediamo subito la tabella ai vicini (convergenza rapida,
     * senza aspettare il loro primo annuncio periodico). */
    for (int i = 0; i < rt->n_ifaces; i++)
        if (rt->ifaces[i].rip)
            send_request(rt, i);
    t_periodic = mono_now() + 3 + rand() % 3;   /* primo annuncio nostro fra 3-5 s */
}

/* Elabora UNA entry di un Response, aggiornando la RIB. È il cuore
 * dell'algoritmo distance-vector (RFC 2453 §3.9.2). */
static void process_entry(struct router *rt, int ifidx, uint32_t from,
                          const struct rip_entry *re)
{
    if (ntohs(re->af) != 2)                  /* solo entry IPv4              */
        return;
    uint32_t metric_in = ntohl(re->metric);
    if (metric_in < 1 || metric_in > RIP_INFINITY)   /* metrica fuori range  */
        return;

    /* La netmask deve essere "contigua" (tutti 1 poi tutti 0). Trucco:
     * per una maschera valida m, (m | (m-1)) copre tutti i 32 bit
     * (0xFFFFFFFF). Se non è così, la maschera ha buchi -> scarta. */
    uint32_t maskh = ntohl(re->mask);
    if (maskh != 0 && ((maskh | (maskh - 1)) != 0xFFFFFFFFu))
        return;
    /* __builtin_popcount = numero di bit a 1: su una maschera contigua è
     * proprio la lunghezza del prefisso (es. 255.255.255.0 -> 24). */
    uint8_t  plen   = (uint8_t)__builtin_popcount(maskh);
    uint32_t prefix = re->ip & re->mask;     /* normalizza la rete           */
    uint32_t ph     = ntohl(prefix);
    /* Rifiuta reti impossibili da annunciare: loopback (127/8) e classe
     * D/E (>= 224.0.0.0). */
    if ((ph >> 24) == 127 || ph >= 0xE0000000u)
        return;

    /* La metrica "da noi" è quella annunciata + 1 (un hop in più), con
     * tetto a 16. */
    uint8_t metric = (uint8_t)(metric_in + 1 > RIP_INFINITY ? RIP_INFINITY
                                                            : metric_in + 1);

    /* Next-hop: il campo re->next_hop è usabile solo se punta a un host
     * sulla stessa rete dell'interfaccia (e non è un nostro IP); altrimenti
     * il next-hop è il mittente stesso dell'annuncio ('from'). */
    struct iface *ifc = &rt->ifaces[ifidx];
    uint32_t nh = re->next_hop;
    if (nh == 0 || (nh & ifc->mask) != (ifc->ip & ifc->mask) || ip_is_ours(rt, nh))
        nh = from;

    struct rib_entry *e = rib_find(prefix, plen);
    if (!e) {
        /* Rotta nuova: la inseriamo solo se raggiungibile (metrica < 16). */
        if (metric < RIP_INFINITY) {
            rib_set(prefix, plen, nh, ifidx, metric, ORIG_RIP);
            schedule_trigger();
        }
        return;
    }
    if (e->origin != ORIG_RIP)
        return;   /* le rotte connesse/statiche vincono: RIP non le tocca    */

    if (e->next_hop == nh) {
        /* Update dallo STESSO gateway che già ci fornisce la rotta. */
        if (metric < RIP_INFINITY) {
            e->last_update = mono_now();     /* rinfresca il timer di validità */
            e->garbage_at  = 0;
        }
        if (metric == e->metric)             /* nulla di nuovo               */
            return;
        if (metric == RIP_INFINITY) {
            /* Il gateway ci dice che la rotta è caduta: avvio del "deletion
             * process" (metrica 16, parte il garbage timer, annuncio subito). */
            if (!e->garbage_at) {
                e->metric     = RIP_INFINITY;
                e->garbage_at = mono_now();
                e->changed    = true;
                LOG_INFO("RIP: %s/%u irraggiungibile (annuncio di %s)",
                         ip_str(prefix), plen, ip_str(from));
                schedule_trigger();
            }
            return;
        }
        /* Stesso gateway, metrica diversa: la si accetta comunque (il
         * gateway è la nostra unica fonte per questa rotta). */
        LOG_INFO("RIP: metrica di %s/%u aggiornata %u -> %u",
                 ip_str(prefix), plen, e->metric, metric);
        e->metric  = metric;
        e->ifidx   = ifidx;
        e->changed = true;
        schedule_trigger();
    } else if (metric < e->metric ||
               (metric == e->metric && metric < RIP_INFINITY && !e->garbage_at &&
                mono_now() - e->last_update >= RIP_TIMEOUT / 2)) {
        /* Update da un gateway DIVERSO: si adotta il nuovo percorso se la
         * metrica è migliore, oppure uguale ma la rotta attuale è vecchia
         * (oltre metà timeout) — euristica RFC 2453 §3.9.2 per non restare
         * appesi a un gateway che sta per scadere. */
        LOG_INFO("RIP: percorso migliore per %s/%u via %s metrica %u (era %u via %s)",
                 ip_str(prefix), plen, ip_str(nh), metric,
                 e->metric, ip_str(e->next_hop));
        e->next_hop    = nh;
        e->ifidx       = ifidx;
        e->metric      = metric;
        e->last_update = mono_now();
        e->garbage_at  = 0;
        e->changed     = true;
        schedule_trigger();
    }
    /* Metrica uguale da un gateway diverso e rotta ancora "fresca": si
     * mantiene la rotta esistente (nessun flapping). */
}

void ripd_input(struct router *rt, int ifidx, uint8_t *frame, size_t len)
{
    (void)len;   /* i limiti sono già garantiti da ipv4_valid() sul tot_len */
    if (!enabled)
        return;
    struct iface *ifc = &rt->ifaces[ifidx];
    if (!ifc->rip)                           /* interfaccia senza RIP: ignora */
        return;

    struct eth_hdr  *eh = (struct eth_hdr *)frame;
    struct ipv4_hdr *ih = (struct ipv4_hdr *)(frame + ETH_HDR_LEN);
    size_t ihl = (size_t)(ih->ver_ihl & 0x0F) * 4;
    struct udp_hdr *uh = (struct udp_hdr *)(frame + ETH_HDR_LEN + ihl);

    /* Controlli di lunghezza: l'UDP deve contenere almeno header RIP (4) e
     * stare dentro il datagramma IP; il payload dopo l'header RIP deve
     * essere un multiplo esatto della dimensione di una entry. */
    size_t udp_len = ntohs(uh->len);
    if (udp_len < 8 + 4 || ihl + udp_len > ntohs(ih->tot_len))
        return;
    size_t plen = udp_len - 8;               /* byte del messaggio RIP        */
    if ((plen - 4) % sizeof(struct rip_entry) != 0)
        return;
    int n = (int)((plen - 4) / sizeof(struct rip_entry));   /* numero di entry */

    struct rip_hdr   *rh = (struct rip_hdr *)((uint8_t *)uh + 8);
    struct rip_entry *re = (struct rip_entry *)((uint8_t *)rh + 4);

    /* Impariamo "gratis" il MAC del vicino dal frame ricevuto (se è sulla
     * nostra rete e non siamo noi): risparmia un giro di ARP quando gli
     * risponderemo in unicast. */
    if ((ih->saddr & ifc->mask) == (ifc->ip & ifc->mask) &&
        !ip_is_ours(rt, ih->saddr))
        arp_learn(ih->saddr, eh->src);

    if (rh->command == RIP_CMD_REQUEST) {
        /* Rispondiamo solo alla request canonica "tutta la tabella"
         * (una entry, AFI=0, metrica 16). */
        if (n == 1 && re[0].af == 0 && ntohl(re[0].metric) == RIP_INFINITY) {
            LOG_INFO("RIP: request della tabella completa da %s, rispondo",
                     ip_str(ih->saddr));
            /* Risposta in UNICAST verso chi ha chiesto, sulla sua porta. */
            send_update(rt, ifidx, false, ih->saddr, eh->src, ntohs(uh->sport));
        }
        return;
    }
    if (rh->command != RIP_CMD_RESPONSE)     /* comando sconosciuto          */
        return;

    /* Validazioni sul Response (RFC 2453 §3.9.2): versione >= 2, porta
     * sorgente 520, mittente sulla nostra rete, e non un nostro stesso
     * annuncio riflesso. */
    if (rh->version < 2)
        return;
    if (ntohs(uh->sport) != RIP_PORT)
        return;
    if ((ih->saddr & ifc->mask) != (ifc->ip & ifc->mask)) {
        LOG_DEBUG("RIP: response da rete non connessa (%s), scarto",
                  ip_str(ih->saddr));
        return;
    }
    if (ip_is_ours(rt, ih->saddr))
        return;

    LOG_DEBUG("RIP: response da %s con %d entry", ip_str(ih->saddr), n);
    for (int i = 0; i < n; i++)
        process_entry(rt, ifidx, ih->saddr, &re[i]);
}

void ripd_tick(struct router *rt)
{
    if (!enabled)
        return;
    time_t now = mono_now();

    /* (1) Timer per rotta: invalidazione (180 s) e rimozione (120 s dopo). */
    for (int i = 0; i < rib_size(); i++) {
        struct rib_entry *e = rib_slot(i);
        if (!e->in_use || e->origin != ORIG_RIP)   /* solo rotte RIP         */
            continue;
        if (!e->garbage_at && now - e->last_update >= RIP_TIMEOUT) {
            /* Nessun update da troppo tempo: la rotta è sospetta -> metrica
             * 16 e parte la garbage collection, e lo annunciamo subito. */
            e->metric     = RIP_INFINITY;
            e->garbage_at = now;
            e->changed    = true;
            LOG_INFO("RIP: timeout della rotta %s/%u (nessun update da %ld s)",
                     ip_str(e->prefix), e->prefix_len,
                     (long)(now - e->last_update));
            schedule_trigger();
        } else if (e->garbage_at && now - e->garbage_at >= RIP_GARBAGE) {
            rib_delete(e);                   /* scaduto anche il garbage timer */
        }
    }

    /* (2) Triggered update pendente: annuncia SOLO le rotte cambiate. */
    if (t_trigger && now >= t_trigger) {
        for (int i = 0; i < rt->n_ifaces; i++)
            if (rt->ifaces[i].rip)
                send_update(rt, i, true, htonl(RIP_MCAST_HOST),
                            RIP_MCAST_MAC, RIP_PORT);
        clear_changed();
        t_trigger = 0;
        LOG_INFO("RIP: triggered update inviato");
    }

    /* (3) Update periodico: annuncia l'intera tabella su ogni interfaccia. */
    if (now >= t_periodic) {
        for (int i = 0; i < rt->n_ifaces; i++)
            if (rt->ifaces[i].rip)
                send_update(rt, i, false, htonl(RIP_MCAST_HOST),
                            RIP_MCAST_MAC, RIP_PORT);
        clear_changed();
        t_trigger = 0;                       /* il periodico include già tutto */
        /* Prossimo update fra ~30 s con jitter (25-35 s): il jitter evita
         * che i router si sincronizzino annunciando tutti insieme. */
        t_periodic = now + 25 + rand() % 11;
        LOG_DEBUG("RIP: update periodico inviato");
    }
}
