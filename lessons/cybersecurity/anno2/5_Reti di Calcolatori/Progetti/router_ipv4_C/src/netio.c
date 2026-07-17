/* =====================================================================
 * netio.c  —  Socket "raw" a livello 2 (AF_PACKET).
 * ===================================================================== */
#include <errno.h>              /* errno + costanti EAGAIN/EINTR/...          */
#include <linux/if_packet.h>    /* struct sockaddr_ll, struct packet_mreq,    */
                                /*   PACKET_* (specifici di Linux)            */
#include <net/ethernet.h>       /* ETH_P_ALL (tutti gli EtherType)            */
#include <string.h>             /* memset, memcpy, strerror                   */
#include <sys/ioctl.h>          /* ioctl + SIOCGIFINDEX / SIOCGIFHWADDR       */
#include <sys/socket.h>         /* socket, bind, send, recvfrom, setsockopt   */
#include <unistd.h>             /* close                                      */

#include "log.h"
#include "netio.h"

int netio_open(struct iface *ifc)
{
    /* socket(AF_PACKET, SOCK_RAW, htons(ETH_P_ALL))
     *   AF_PACKET : famiglia "packet" di Linux -> accesso ai frame Ethernet
     *               grezzi, con l'header di livello 2 incluso. <sys/socket.h>
     *   SOCK_RAW  : consegna il frame COMPLETO (con SOCK_DGRAM il kernel
     *               toglierebbe l'header Ethernet).
     *   ETH_P_ALL : "ricevi ogni EtherType"; filtreremo noi. Va in network
     *               order (htons) perché è il protocollo a livello link. */
    int fd = socket(AF_PACKET, SOCK_RAW, htons(ETH_P_ALL));
    if (fd < 0) {                          /* socket() torna -1 su errore     */
        LOG_ERR("socket(%s): %s", ifc->name, strerror(errno));
        return -1;
    }

    /* struct ifreq è la struttura "generica" per le ioctl sulle
     * interfacce: si mette il nome in ifr_name e la ioctl riempie il
     * campo pertinente all'operazione richiesta. */
    struct ifreq ifr;
    memset(&ifr, 0, sizeof ifr);
    snprintf(ifr.ifr_name, IFNAMSIZ, "%s", ifc->name);   /* nome interfaccia   */

    /* SIOCGIFINDEX: "get interface index" — dal nome ("eth1") ottiene
     * l'indice numerico che il kernel usa per identificare l'interfaccia. */
    if (ioctl(fd, SIOCGIFINDEX, &ifr) < 0) {
        LOG_ERR("SIOCGIFINDEX(%s): %s", ifc->name, strerror(errno));
        close(fd);
        return -1;
    }
    ifc->sys_ifindex = ifr.ifr_ifindex;

    /* SIOCGIFHWADDR: "get hardware address" — il MAC dell'interfaccia.
     * Sta in ifr_hwaddr.sa_data (i primi 6 byte). Ci serve per mettere il
     * MAC sorgente corretto nei frame che trasmettiamo. */
    if (ioctl(fd, SIOCGIFHWADDR, &ifr) < 0) {
        LOG_ERR("SIOCGIFHWADDR(%s): %s", ifc->name, strerror(errno));
        close(fd);
        return -1;
    }
    memcpy(ifc->mac, ifr.ifr_hwaddr.sa_data, 6);

    /* bind() lega il socket a UNA sola interfaccia: senza, riceveremmo i
     * frame di tutte. sockaddr_ll è l'indirizzo "link-layer" di AF_PACKET;
     * l'unico campo che conta per legarsi è sll_ifindex. */
    struct sockaddr_ll sll;
    memset(&sll, 0, sizeof sll);
    sll.sll_family   = AF_PACKET;
    sll.sll_protocol = htons(ETH_P_ALL);
    sll.sll_ifindex  = ifc->sys_ifindex;
    if (bind(fd, (struct sockaddr *)&sll, sizeof sll) < 0) {
        LOG_ERR("bind(%s): %s", ifc->name, strerror(errno));
        close(fd);
        return -1;
    }

    /* Modalità promiscua: fa consegnare all'interfaccia anche i frame il
     * cui MAC di destinazione non è il nostro — in particolare i multicast
     * di RIP (MAC 01:00:5e:00:00:09). setsockopt con PACKET_ADD_MEMBERSHIP
     * e mr_type=PACKET_MR_PROMISC attiva la promiscuità in modo pulito
     * (si disattiva da sola alla chiusura del socket). SOL_PACKET è il
     * "livello" delle opzioni specifiche di AF_PACKET.
     * Se fallisce logghiamo soltanto: in molte topologie il traffico
     * arriverebbe comunque. */
    struct packet_mreq mr;
    memset(&mr, 0, sizeof mr);
    mr.mr_ifindex = ifc->sys_ifindex;
    mr.mr_type    = PACKET_MR_PROMISC;
    if (setsockopt(fd, SOL_PACKET, PACKET_ADD_MEMBERSHIP, &mr, sizeof mr) < 0)
        LOG_WARN("PACKET_MR_PROMISC(%s): %s", ifc->name, strerror(errno));

    ifc->fd = fd;
    LOG_INFO("interfaccia %s: ifindex=%d mac=%s ip=%s/%u%s",
             ifc->name, ifc->sys_ifindex, mac_str(ifc->mac),
             ip_str(ifc->ip), ifc->prefix_len, ifc->rip ? " [rip]" : "");
    return fd;
}

ssize_t netio_recv(struct iface *ifc, uint8_t *buf, size_t sz)
{
    /* Ciclo perché alcuni casi ("riprova") richiedono di rileggere senza
     * uscire dalla funzione. */
    for (;;) {
        struct sockaddr_ll from;         /* chi/come è arrivato il frame       */
        socklen_t fl = sizeof from;
        /* recvfrom con MSG_DONTWAIT = lettura NON bloccante: se non c'è
         * nulla ritorna subito -1 con errno EAGAIN/EWOULDBLOCK. 'from' ci
         * dà, tra l'altro, il "packet type" (unicast a noi, broadcast,
         * ecc.) in sll_pkttype. */
        ssize_t n = recvfrom(ifc->fd, buf, sz, MSG_DONTWAIT,
                             (struct sockaddr *)&from, &fl);
        if (n < 0) {
            if (errno == EINTR)          /* interrotta da un segnale: riprova  */
                continue;
            /* EAGAIN/EWOULDBLOCK = "niente da leggere ora": non è un errore,
             * torniamo 0. Ogni altro errno è anomalo e va segnalato. */
            if (errno != EAGAIN && errno != EWOULDBLOCK)
                LOG_WARN("recvfrom(%s): %s", ifc->name, strerror(errno));
            return 0;
        }
        /* AF_PACKET riflette anche i frame che NOI trasmettiamo: vanno
         * scartati o li riprocesseremmo. */
        if (from.sll_pkttype == PACKET_OUTGOING)
            continue;
        /* In promiscua riceviamo anche frame indirizzati (a livello 2) ad
         * altri host: non ci riguardano. */
        if (from.sll_pkttype == PACKET_OTHERHOST)
            continue;
        return n;                        /* frame buono: 'n' byte in buf       */
    }
}

void netio_send(struct iface *ifc, const void *frame, size_t len)
{
    /* Il socket è già legato all'interfaccia (bind), quindi basta send():
     * trasmette il frame così com'è, header Ethernet incluso. */
    if (send(ifc->fd, frame, len, 0) < 0)
        LOG_WARN("send(%s): %s", ifc->name, strerror(errno));
}
