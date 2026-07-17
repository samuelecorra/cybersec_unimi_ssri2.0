/* =====================================================================
 * icmp.c  —  Echo Reply e messaggi di errore ICMP.
 * ===================================================================== */
#include <string.h>

#include "icmp.h"
#include "log.h"
#include "netio.h"
#include "parse.h"

void icmp_input(struct router *rt, int ifidx, uint8_t *frame, size_t len)
{
    (void)len;   /* la lunghezza utile la ricaviamo da tot_len dell'IP        */
    struct iface *ifc = &rt->ifaces[ifidx];
    struct eth_hdr  *eh = (struct eth_hdr *)frame;
    struct ipv4_hdr *ih = (struct ipv4_hdr *)(frame + ETH_HDR_LEN);
    size_t ihl      = (size_t)(ih->ver_ihl & 0x0F) * 4;   /* lunghezza header IP */
    size_t icmp_len = ntohs(ih->tot_len) - ihl;           /* lunghezza parte ICMP */
    if (icmp_len < sizeof(struct icmp_hdr))
        return;
    struct icmp_hdr *ic = (struct icmp_hdr *)(frame + ETH_HDR_LEN + ihl);
    /* Verifica il checksum ICMP (deve dare 0 su un messaggio integro). */
    if (inet_cksum(ic, icmp_len) != 0) {
        LOG_DEBUG("ICMP: checksum errato da %s, scarto", ip_str(ih->saddr));
        return;
    }
    /* Rispondiamo solo agli Echo Request; ogni altro tipo lo ignoriamo. */
    if (ic->type != ICMP_ECHO_REQUEST) {
        LOG_DEBUG("ICMP: tipo %u da %s ignorato", ic->type, ip_str(ih->saddr));
        return;
    }

    /* Echo Reply costruito IN LOCO riusando il pacchetto ricevuto: basta
     * "rovesciare" mittente/destinatario. Il payload dell'echo (i dati che
     * il ping include) resta invariato, com'è richiesto.
     *
     * (a) scambia i MAC Ethernet (dst <-> src) tramite un buffer temporaneo. */
    uint8_t tmp[6];
    memcpy(tmp, eh->dst, 6);
    memcpy(eh->dst, eh->src, 6);
    memcpy(eh->src, tmp, 6);

    /* (b) scambia gli IP e imposta un TTL fresco; poi ricalcola il checksum
     * dell'header IP (azzerandolo prima, come vuole l'algoritmo). */
    uint32_t t = ih->saddr;
    ih->saddr = ih->daddr;
    ih->daddr = t;
    ih->ttl = 64;
    ih->checksum = 0;
    ih->checksum = inet_cksum(ih, ihl);

    /* (c) cambia il tipo ICMP da Echo Request (8) a Echo Reply (0) e
     * ricalcola il checksum ICMP. */
    ic->type = ICMP_ECHO_REPLY;
    ic->checksum = 0;
    ic->checksum = inet_cksum(ic, icmp_len);

    netio_send(ifc, frame, ETH_HDR_LEN + ihl + icmp_len);
    LOG_DEBUG("ICMP: Echo Reply %s -> %s", ip_str(ih->saddr), ip_str(ih->daddr));
}

void icmp_send_error(struct router *rt, int in_ifidx, const uint8_t *orig,
                     size_t orig_len, uint8_t type, uint8_t code)
{
    static uint16_t ip_id = 1;             /* contatore per il campo IP id     */
    struct iface *ifc = &rt->ifaces[in_ifidx];
    const struct eth_hdr  *oeh = (const struct eth_hdr *)orig;              /* frame originale */
    const struct ipv4_hdr *oih = (const struct ipv4_hdr *)(orig + ETH_HDR_LEN);
    size_t oihl = (size_t)(oih->ver_ihl & 0x0F) * 4;

    /* Casi in cui NON si deve generare un errore ICMP (RFC 1122 §3.2.2),
     * per non innescare tempeste di errori o rispondere a chi non ha senso:
     *   - frammenti diversi dal primo (i 13 bit bassi di frag_off != 0);
     *   - sorgente non unicast (0.0.0.0, broadcast, o multicast/classe D);
     *   - un pacchetto che è a sua volta un ICMP di errore (rispondiamo
     *     solo a Echo Request/Reply). IN_MULTICAST è una macro di
     *     <netinet/in.h> vera per gli indirizzi 224.0.0.0/4. */
    if ((ntohs(oih->frag_off) & 0x1FFF) != 0)
        return;
    if (oih->saddr == 0 || oih->saddr == 0xFFFFFFFFu ||
        IN_MULTICAST(ntohl(oih->saddr)))
        return;
    if (oih->proto == IPPROTO_ICMP &&
        orig_len >= ETH_HDR_LEN + oihl + 1) {
        const struct icmp_hdr *oic =
            (const struct icmp_hdr *)(orig + ETH_HDR_LEN + oihl);
        if (oic->type != ICMP_ECHO_REQUEST && oic->type != ICMP_ECHO_REPLY)
            return;
    }

    /* Il payload dell'errore "cita" il pacchetto colpito: header IP + primi
     * 8 byte (che per TCP/UDP contengono le porte, così il mittente
     * identifica la connessione). */
    size_t quote = ntohs(oih->tot_len);
    if (quote > oihl + 8)
        quote = oihl + 8;

    /* Buffer di uscita dimensionato per il caso peggiore: eth + IP(20) +
     * ICMP(8) + header IP citato con opzioni (max 60) + 8 byte. */
    uint8_t out[ETH_HDR_LEN + 20 + 8 + 60 + 8];
    struct eth_hdr  *eh = (struct eth_hdr *)out;
    struct ipv4_hdr *ih = (struct ipv4_hdr *)(out + ETH_HDR_LEN);
    struct icmp_hdr *ic = (struct icmp_hdr *)(out + ETH_HDR_LEN + 20);

    /* L'errore rientra dall'interfaccia da cui è arrivato il pacchetto,
     * verso il MAC del precedente hop (che sa raggiungere la sorgente). */
    memcpy(eh->dst, oeh->src, 6);
    memcpy(eh->src, ifc->mac, 6);
    eh->ethertype = htons(ETHERTYPE_IPV4);

    /* Header IP dell'errore. ver_ihl=0x45: versione 4, IHL 5 (=20 byte). */
    size_t tot = 20 + 8 + quote;
    ih->ver_ihl  = 0x45;
    ih->tos      = 0;
    ih->tot_len  = htons((uint16_t)tot);
    ih->id       = htons(ip_id++);
    ih->frag_off = 0;
    ih->ttl      = 64;
    ih->proto    = IPPROTO_ICMP;
    ih->checksum = 0;
    ih->saddr    = ifc->ip;                /* mittente: noi                    */
    ih->daddr    = oih->saddr;             /* destinatario: chi ha inviato     */
    ih->checksum = inet_cksum(ih, 20);

    /* Corpo ICMP: type/code, 4 byte "rest" a zero (non usati per questi
     * tipi), poi la citazione del pacchetto originale a partire dal byte 8. */
    ic->type = type;
    ic->code = code;
    ic->rest = 0;
    memcpy((uint8_t *)ic + 8, oih, quote);
    ic->checksum = 0;
    ic->checksum = inet_cksum(ic, 8 + quote);

    netio_send(ifc, out, ETH_HDR_LEN + tot);
    LOG_DEBUG("ICMP: errore tipo %u codice %u a %s (originale verso %s)",
              type, code, ip_str(oih->saddr), ip_str(oih->daddr));
}
