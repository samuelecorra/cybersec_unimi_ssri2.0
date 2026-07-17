/* =====================================================================
 * arp.c  —  Cache ARP, risposta alle richieste, coda dei pacchetti in
 *           attesa di risoluzione del next-hop.
 * ===================================================================== */
#include <string.h>

#include "arp.h"
#include "log.h"
#include "netio.h"

#define ARP_CACHE_SZ  64    /* entry massime nella cache IP->MAC              */
#define ARP_PEND_SZ   16    /* pacchetti massimi in attesa di risoluzione     */
#define ARP_CACHE_TTL 300   /* durata di una entry di cache, in secondi       */
#define ARP_RETRIES   3     /* tentativi di richiesta prima di rinunciare     */

/* Una associazione IP -> MAC nella cache. 'seen' = quando è stata vista
 * l'ultima volta (per la scadenza e per scegliere quale rimpiazzare). */
struct cache_entry {
    bool     in_use;
    uint32_t ip;
    uint8_t  mac[6];
    time_t   seen;
};

/* Un pacchetto in attesa che si risolva il MAC del suo next-hop. Teniamo
 * il frame INTERO già pronto: appena arriva la risposta ARP basta
 * riempirne il MAC di destinazione e trasmetterlo. */
struct pend_entry {
    bool     in_use;
    uint32_t ip;                  /* next-hop di cui aspettiamo il MAC        */
    int      ifidx;               /* interfaccia da cui uscirà                */
    size_t   len;                 /* lunghezza del frame accodato             */
    time_t   last_tx;             /* ultima richiesta inviata (per il retry)  */
    int      tries;               /* quante richieste già inviate             */
    uint8_t  frame[FRAME_BUF_SZ]; /* copia del frame da spedire               */
};

/* Tabelle statiche (dimensione fissa: nessuna malloc, nessun leak). */
static struct cache_entry cache[ARP_CACHE_SZ];
static struct pend_entry  pend[ARP_PEND_SZ];

void arp_init(void)
{
    memset(cache, 0, sizeof cache);
    memset(pend, 0, sizeof pend);
}

void arp_learn(uint32_t ip, const uint8_t *mac)
{
    /* Scansione unica che fa tre cose: se l'IP è già in cache lo aggiorna;
     * intanto ricorda il primo slot libero e la entry più vecchia, per
     * poter inserire se l'IP è nuovo. */
    struct cache_entry *free_e = NULL, *oldest = &cache[0];
    for (int i = 0; i < ARP_CACHE_SZ; i++) {
        if (cache[i].in_use && cache[i].ip == ip) {
            if (memcmp(cache[i].mac, mac, 6) != 0)   /* il MAC è cambiato?     */
                LOG_DEBUG("ARP: %s cambia MAC in %s", ip_str(ip), mac_str(mac));
            memcpy(cache[i].mac, mac, 6);
            cache[i].seen = mono_now();
            return;
        }
        if (!cache[i].in_use && !free_e)
            free_e = &cache[i];
        if (cache[i].seen < oldest->seen)            /* traccia la più vecchia */
            oldest = &cache[i];
    }
    /* IP nuovo: usa uno slot libero, o rimpiazza la entry più vecchia
     * (politica LRU) se la cache è piena. */
    struct cache_entry *e = free_e ? free_e : oldest;
    e->in_use = true;
    e->ip     = ip;
    memcpy(e->mac, mac, 6);
    e->seen = mono_now();
    LOG_DEBUG("ARP: appresa associazione %s -> %s", ip_str(ip), mac_str(mac));
}

bool arp_lookup(uint32_t ip, uint8_t *mac_out)
{
    for (int i = 0; i < ARP_CACHE_SZ; i++)
        if (cache[i].in_use && cache[i].ip == ip) {
            memcpy(mac_out, cache[i].mac, 6);
            return true;
        }
    return false;
}

/* Costruisce e trasmette una richiesta ARP "who-has ip?" in broadcast. */
static void send_request(struct router *rt, int ifidx, uint32_t ip)
{
    struct iface *ifc = &rt->ifaces[ifidx];
    uint8_t buf[ETH_HDR_LEN + sizeof(struct arp_pkt)];
    struct eth_hdr *eh = (struct eth_hdr *)buf;
    struct arp_pkt *ap = (struct arp_pkt *)(buf + ETH_HDR_LEN);

    /* Header Ethernet: destinazione broadcast (FF:FF:FF:FF:FF:FF), così
     * tutti in LAN lo ricevono e solo il titolare dell'IP risponde. */
    memset(eh->dst, 0xFF, 6);
    memcpy(eh->src, ifc->mac, 6);
    eh->ethertype = htons(ETHERTYPE_ARP);

    /* Corpo ARP: request (oper=1) di tipo Ethernet/IPv4. Mettiamo i nostri
     * MAC/IP come mittente e l'IP cercato come target; il MAC target è 0
     * (è proprio ciò che stiamo chiedendo). */
    ap->htype = htons(1);                 /* hardware = Ethernet               */
    ap->ptype = htons(ETHERTYPE_IPV4);    /* protocollo = IPv4                 */
    ap->hlen  = 6;
    ap->plen  = 4;
    ap->oper  = htons(1);                 /* request                           */
    memcpy(ap->sha, ifc->mac, 6);         /* Sender Hardware Address = noi     */
    ap->spa = ifc->ip;                    /* Sender Protocol Address = nostro IP */
    memset(ap->tha, 0, 6);                /* Target MAC: ignoto (lo chiediamo) */
    ap->tpa = ip;                         /* Target IP: quello da risolvere    */

    netio_send(ifc, buf, sizeof buf);
    LOG_DEBUG("ARP: who-has %s su %s", ip_str(ip), ifc->name);
}

/* Appena appreso un MAC, spedisce tutti i pacchetti in coda per quell'IP. */
static void flush_pending(struct router *rt, uint32_t ip)
{
    uint8_t mac[6];
    if (!arp_lookup(ip, mac))
        return;
    for (int i = 0; i < ARP_PEND_SZ; i++) {
        struct pend_entry *p = &pend[i];
        if (!p->in_use || p->ip != ip)
            continue;
        /* Completa il frame con il MAC di destinazione appena scoperto e
         * trasmettilo. */
        memcpy(((struct eth_hdr *)p->frame)->dst, mac, 6);
        netio_send(&rt->ifaces[p->ifidx], p->frame, p->len);
        p->in_use = false;               /* slot liberato                     */
        LOG_DEBUG("ARP: inoltrato pacchetto in coda verso %s", ip_str(ip));
    }
}

void arp_input(struct router *rt, int ifidx, uint8_t *frame, size_t len)
{
    struct iface *ifc = &rt->ifaces[ifidx];
    if (len < ETH_HDR_LEN + sizeof(struct arp_pkt))    /* frame troppo corto  */
        return;
    struct arp_pkt *ap = (struct arp_pkt *)(frame + ETH_HDR_LEN);
    /* Accetta solo ARP "classico" Ethernet/IPv4 con lunghezze standard. */
    if (ntohs(ap->htype) != 1 || ntohs(ap->ptype) != ETHERTYPE_IPV4 ||
        ap->hlen != 6 || ap->plen != 4)
        return;

    uint16_t op = ntohs(ap->oper);
    /* Da OGNI pacchetto ARP (request o reply) impariamo l'associazione del
     * mittente: è "ARP gratuito" gratis, riduce le richieste future. */
    if (ap->spa != 0)
        arp_learn(ap->spa, ap->sha);

    if (op == 1 && ap->tpa == ifc->ip) {
        /* Richiesta per un NOSTRO IP: la trasformiamo in risposta
         * riscrivendo il frame IN LOCO (evita di costruirne uno nuovo).
         *   - MAC Ethernet: dst = chi ha chiesto, src = noi;
         *   - oper -> 2 (reply);
         *   - target = il richiedente, sender = noi (IP e MAC). */
        uint32_t requester = ap->spa;
        struct eth_hdr *eh = (struct eth_hdr *)frame;
        memcpy(eh->dst, ap->sha, 6);
        memcpy(eh->src, ifc->mac, 6);
        ap->oper = htons(2);
        memcpy(ap->tha, ap->sha, 6);
        ap->tpa = ap->spa;
        memcpy(ap->sha, ifc->mac, 6);
        ap->spa = ifc->ip;
        netio_send(ifc, frame, ETH_HDR_LEN + sizeof(struct arp_pkt));
        LOG_DEBUG("ARP: rispondo a %s (%s is-at %s)",
                  ip_str(requester), ip_str(ifc->ip), mac_str(ifc->mac));
    } else if (op == 2) {
        /* È una risposta: potrebbe sbloccare pacchetti che aspettavano
         * proprio questo MAC. */
        flush_pending(rt, ap->spa);
    }
}

void arp_output(struct router *rt, int ifidx, uint32_t next_hop,
                uint8_t *frame, size_t len)
{
    uint8_t mac[6];
    if (len > FRAME_BUF_SZ)              /* non entrerebbe nella copia in coda */
        return;

    /* Caso veloce: MAC già noto -> completa e spedisci subito. */
    if (arp_lookup(next_hop, mac)) {
        memcpy(((struct eth_hdr *)frame)->dst, mac, 6);
        netio_send(&rt->ifaces[ifidx], frame, len);
        return;
    }

    /* MAC ignoto: accoda il pacchetto e avvia la risoluzione. Nel frattempo
     * cerca uno slot libero e verifica se una richiesta per lo stesso IP è
     * già in volo (per non duplicarla). */
    struct pend_entry *slot = NULL;
    bool requested = false;
    for (int i = 0; i < ARP_PEND_SZ; i++) {
        if (pend[i].in_use && pend[i].ip == next_hop)
            requested = true;
        if (!pend[i].in_use && !slot)
            slot = &pend[i];
    }
    if (!slot) {
        LOG_WARN("ARP: coda di risoluzione piena, scarto pacchetto verso %s",
                 ip_str(next_hop));
        return;
    }
    slot->in_use  = true;
    slot->ip      = next_hop;
    slot->ifidx   = ifidx;
    slot->len     = len;
    slot->tries   = 1;
    slot->last_tx = mono_now();
    memcpy(slot->frame, frame, len);     /* conserva il frame da spedire dopo  */
    if (!requested)                      /* invia la richiesta solo se serve   */
        send_request(rt, ifidx, next_hop);
}

void arp_tick(struct router *rt, time_t now)
{
    /* 1) Ritrasmissione delle richieste pendenti (una al secondo circa). */
    for (int i = 0; i < ARP_PEND_SZ; i++) {
        struct pend_entry *p = &pend[i];
        if (!p->in_use || now - p->last_tx < 1)   /* non pronto a ritentare    */
            continue;
        if (p->tries >= ARP_RETRIES) {
            /* Esauriti i tentativi: il next-hop non risponde, scarta il
             * pacchetto (in un router reale genererebbe Host Unreachable). */
            LOG_WARN("ARP: %s non risolto dopo %d tentativi, scarto",
                     ip_str(p->ip), p->tries);
            p->in_use = false;
            continue;
        }
        p->tries++;
        p->last_tx = now;
        send_request(rt, p->ifidx, p->ip);
    }
    /* 2) Scadenza delle entry di cache più vecchie di ARP_CACHE_TTL. */
    for (int i = 0; i < ARP_CACHE_SZ; i++)
        if (cache[i].in_use && now - cache[i].seen > ARP_CACHE_TTL) {
            LOG_DEBUG("ARP: entry %s scaduta", ip_str(cache[i].ip));
            cache[i].in_use = false;
        }
}
