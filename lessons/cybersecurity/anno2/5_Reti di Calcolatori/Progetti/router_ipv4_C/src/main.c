/* =====================================================================
 * main.c  —  Punto d'ingresso, inizializzazione ed EVENT LOOP.
 *
 * Struttura del programma:
 *   1. legge la configurazione e apre i socket (uno per interfaccia);
 *   2. popola la RIB con le rotte connesse e statiche;
 *   3. avvia RIP;
 *   4. entra nell'event loop: un unico select() attende i pacchetti su
 *      tutte le interfacce e, con il suo timeout, fa avanzare i timer.
 *
 * Un solo processo, un solo thread: niente race condition, niente lock.
 * ===================================================================== */
#include <errno.h>
#include <signal.h>        /* signal, sig_atomic_t, SIGINT/SIGTERM/SIGUSR1  */
#include <stdlib.h>        /* srand, rand                                   */
#include <string.h>
#include <sys/select.h>    /* select, fd_set, FD_ZERO/FD_SET/FD_ISSET       */
#include <unistd.h>        /* close, getpid                                 */

#include "arp.h"
#include "common.h"
#include "config.h"
#include "forward.h"
#include "icmp.h"
#include "log.h"
#include "netio.h"
#include "parse.h"
#include "rib.h"
#include "ripd.h"

/* Flag impostati dai gestori di segnale. 'volatile sig_atomic_t' è il tipo
 * OBBLIGATORIO per variabili condivise con un signal handler: 'volatile'
 * impedisce al compilatore di "cachearle" in un registro (l'handler può
 * cambiarle in qualsiasi momento), e sig_atomic_t garantisce che la
 * lettura/scrittura sia atomica rispetto ai segnali. */
static volatile sig_atomic_t g_stop;   /* 1 = uscire dall'event loop         */
static volatile sig_atomic_t g_dump;   /* 1 = stampare la RIB                */

/* Gestore dei segnali: minimale (nei signal handler si può fare pochissimo
 * in sicurezza). SIGUSR1 chiede il dump della RIB; SIGINT/SIGTERM chiedono
 * l'arresto. Il lavoro vero lo fa l'event loop quando vede i flag. */
static void on_signal(int sig)
{
    if (sig == SIGUSR1)
        g_dump = 1;
    else
        g_stop = 1;
}

/*
 * IL PIANO DATI, PASSO PER PASSO (design §2.2). Chiamata per ogni frame:
 * demultiplexing Ethernet -> validazione IPv4 -> "è per noi?" (consegna
 * locale a ICMP/RIP) -> altrimenti inoltro.
 */
static void handle_frame(struct router *rt, int ifidx, uint8_t *frame, size_t len)
{
    struct iface *ifc = &rt->ifaces[ifidx];
    if (len < ETH_HDR_LEN)                   /* nemmeno un header Ethernet   */
        return;
    struct eth_hdr *eh = (struct eth_hdr *)frame;
    uint16_t et = ntohs(eh->ethertype);

    /* (1) Demultiplexing per EtherType: ARP al suo modulo, IPv4 prosegue,
     * tutto il resto si scarta. */
    if (et == ETHERTYPE_ARP) {
        arp_input(rt, ifidx, frame, len);
        return;
    }
    if (et != ETHERTYPE_IPV4)
        return;
    /* (2) Validazione IPv4: pacchetto malformato -> scarto SILENZIOSO
     * (nessun ICMP, come da RFC: non si "risponde" a spazzatura). */
    if (!ipv4_valid(frame + ETH_HDR_LEN, len - ETH_HDR_LEN)) {
        LOG_DEBUG("IPv4 non valido su %s, scarto silenzioso", ifc->name);
        return;
    }

    struct ipv4_hdr *ih = (struct ipv4_hdr *)(frame + ETH_HDR_LEN);
    size_t   ihl = (size_t)(ih->ver_ihl & 0x0F) * 4;
    uint32_t d   = ih->daddr;

    /* (3) "È per noi?" Tre casi di consegna locale:
     *   to_us     = destinazione = un nostro IP;
     *   rip_mcast = destinazione = 224.0.0.9 (annunci RIP);
     *   bcast     = broadcast limitato (255.255.255.255) o di rete
     *               (tutti i bit host a 1: ifc->ip | ~mask).
     * first_frag distingue il primo frammento (che ha gli header di
     * trasporto) dai successivi (che non li hanno). */
    bool to_us      = ip_is_ours(rt, d);
    bool rip_mcast  = (d == htonl(RIP_MCAST_HOST));
    bool bcast      = (d == 0xFFFFFFFFu) || (d == (ifc->ip | ~ifc->mask));
    bool first_frag = (ntohs(ih->frag_off) & 0x1FFF) == 0;

    if (to_us || rip_mcast || bcast) {
        if (!first_frag)                     /* frammenti successivi: ignora  */
            return;
        if (ih->proto == IPPROTO_UDP) {
            if (ntohs(ih->tot_len) < ihl + sizeof(struct udp_hdr))
                return;
            struct udp_hdr *uh = (struct udp_hdr *)(frame + ETH_HDR_LEN + ihl);
            if (ntohs(uh->dport) == RIP_PORT) {   /* UDP/520 -> il demone RIP */
                ripd_input(rt, ifidx, frame, len);
                return;
            }
            /* UDP verso una porta chiusa di un nostro IP -> Port Unreachable
             * (non per i broadcast/multicast, dove non ha senso). */
            if (to_us)
                icmp_send_error(rt, ifidx, frame, len,
                                ICMP_DEST_UNREACH, ICMP_CODE_PORT_UNREACH);
            return;
        }
        if (ih->proto == IPPROTO_ICMP && to_us) {   /* ping verso di noi      */
            icmp_input(rt, ifidx, frame, len);
            return;
        }
        return;   /* altri protocolli destinati a noi: non gestiti, ignora   */
    }

    /* (4) Non è per noi. Il multicast non-RIP non si inoltra (non facciamo
     * routing multicast); tutto il resto va al motore di forwarding. */
    if (IN_MULTICAST(ntohl(d)))
        return;

    forward_ip(rt, ifidx, frame, len);
}

static void usage(const char *argv0)
{
    fprintf(stderr, "uso: %s -c <file di configurazione> [-v]\n", argv0);
}

int main(int argc, char **argv)
{
    const char *conf = NULL;

    /* Parsing degli argomenti: -c <file> (obbligatorio), -v = log verboso. */
    for (int i = 1; i < argc; i++) {
        if (strcmp(argv[i], "-c") == 0 && i + 1 < argc)
            conf = argv[++i];
        else if (strcmp(argv[i], "-v") == 0)
            log_set_level(LOG_L_DEBUG);
        else {
            usage(argv[0]);
            return 1;
        }
    }
    if (!conf) {
        usage(argv[0]);
        return 1;
    }

    /* Seme del generatore pseudo-casuale (serve al jitter dei timer RIP).
     * time XOR pid così due istanze avviate nello stesso secondo divergono. */
    srand((unsigned)(time(NULL) ^ getpid()));

    /* 'static' per queste strutture grandi: vanno nel segmento dati, non
     * nello stack (che ha spazio limitato). */
    static struct router rt;
    static struct static_route st[MAX_STATIC];
    int n_st = 0;
    if (config_load(conf, &rt, st, &n_st) < 0)
        return 1;

    LOG_INFO("crouter in avvio: %d interfacce, %d rotte statiche (pid %d)",
             rt.n_ifaces, n_st, getpid());

    /* Apre un socket raw per ogni interfaccia. */
    for (int i = 0; i < rt.n_ifaces; i++)
        if (netio_open(&rt.ifaces[i]) < 0)
            return 1;

    rib_init(&rt);
    arp_init();

    /* Rotte CONNESSE: una per interfaccia (la sua rete è a distanza 0). */
    for (int i = 0; i < rt.n_ifaces; i++) {
        struct iface *ifc = &rt.ifaces[i];
        rib_set(ifc->ip & ifc->mask, ifc->prefix_len, 0, i, 1, ORIG_CONNECTED);
    }
    /* Rotte STATICHE: per ognuna troviamo su quale interfaccia sta il suo
     * next-hop (deve essere su una rete direttamente connessa, altrimenti
     * non sapremmo come raggiungerlo). */
    for (int i = 0; i < n_st; i++) {
        int ifidx = -1;
        for (int j = 0; j < rt.n_ifaces; j++)
            if ((st[i].via & rt.ifaces[j].mask) ==
                (rt.ifaces[j].ip & rt.ifaces[j].mask)) {
                ifidx = j;
                break;
            }
        if (ifidx < 0) {
            LOG_ERR("rotta statica %s/%u: next-hop %s non su una rete connessa",
                    ip_str(st[i].prefix), st[i].prefix_len, ip_str(st[i].via));
            return 1;
        }
        rib_set(st[i].prefix, st[i].prefix_len, st[i].via, ifidx, 1, ORIG_STATIC);
    }

    ripd_init(&rt);
    rib_dump();

    /* Registra i gestori di segnale (dopo l'init, per non stampare durante
     * la costruzione della RIB). */
    signal(SIGINT, on_signal);    /* Ctrl-C                                  */
    signal(SIGTERM, on_signal);   /* kill "gentile"                          */
    signal(SIGUSR1, on_signal);   /* dump della RIB su richiesta             */

    /* EVENT LOOP unico basato su select(): attende contemporaneamente su
     * tutti i socket con un timeout breve; quel timeout fa da "battito" per
     * i timer di ARP e RIP. */
    static uint8_t frame[FRAME_BUF_SZ];
    while (!g_stop) {
        /* Prepara l'insieme dei descrittori da osservare in lettura.
         * FD_ZERO azzera l'insieme, FD_SET aggiunge un fd; select vuole
         * anche il valore (max fd + 1). */
        fd_set rfds;
        FD_ZERO(&rfds);
        int maxfd = -1;
        for (int i = 0; i < rt.n_ifaces; i++) {
            FD_SET(rt.ifaces[i].fd, &rfds);
            if (rt.ifaces[i].fd > maxfd)
                maxfd = rt.ifaces[i].fd;
        }
        /* Timeout di 500 ms: se non arriva nulla, select ritorna comunque e
         * andiamo lo stesso a far girare i timer sotto. */
        struct timeval tv = { 0, 500 * 1000 };
        int rc = select(maxfd + 1, &rfds, NULL, NULL, &tv);
        if (rc < 0 && errno != EINTR) {      /* errore vero (non un segnale) */
            LOG_ERR("select: %s", strerror(errno));
            break;
        }
        /* Se ci sono socket pronti, li svuotiamo tutti (netio_recv è non
         * bloccante e torna 0 quando l'interfaccia non ha altro). */
        if (rc > 0)
            for (int i = 0; i < rt.n_ifaces; i++)
                if (FD_ISSET(rt.ifaces[i].fd, &rfds)) {
                    ssize_t n;
                    while ((n = netio_recv(&rt.ifaces[i], frame, sizeof frame)) > 0)
                        handle_frame(&rt, i, frame, (size_t)n);
                }

        /* Timer: ritrasmissioni/scadenze ARP e macchina a stati RIP. */
        arp_tick(&rt, mono_now());
        ripd_tick(&rt);

        /* Richiesta di dump della RIB (SIGUSR1). */
        if (g_dump) {
            rib_dump();
            g_dump = 0;
        }
    }

    /* Uscita pulita: dump finale e chiusura dei socket. */
    LOG_INFO("crouter: arresto");
    rib_dump();
    for (int i = 0; i < rt.n_ifaces; i++)
        if (rt.ifaces[i].fd >= 0)
            close(rt.ifaces[i].fd);
    return 0;
}
