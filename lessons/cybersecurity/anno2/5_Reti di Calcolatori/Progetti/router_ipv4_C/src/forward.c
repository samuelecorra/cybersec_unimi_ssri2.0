/* =====================================================================
 * forward.c  —  Il motore di inoltro IPv4.
 *
 * Riassume i passi 4-6 della pipeline del data plane (design L2 §2.2):
 * TTL -> lookup LPM -> risoluzione ARP del next-hop -> trasmissione.
 * ===================================================================== */
#include <string.h>

#include "arp.h"
#include "forward.h"
#include "icmp.h"
#include "log.h"
#include "parse.h"
#include "rib.h"

void forward_ip(struct router *rt, int in_ifidx, uint8_t *frame, size_t len)
{
    struct ipv4_hdr *ih = (struct ipv4_hdr *)(frame + ETH_HDR_LEN);

    /* (1) TTL. Un pacchetto che arriva con TTL 0 o 1 non può essere
     * inoltrato (decrementandolo diventerebbe 0): si genera un ICMP Time
     * Exceeded verso il mittente. È PROPRIO questo meccanismo che rende il
     * router visibile a traceroute (che invia pacchetti con TTL crescente
     * per "scoprire" gli hop uno a uno). */
    if (ih->ttl <= 1) {
        LOG_DEBUG("TTL esaurito per %s -> %s, genero Time Exceeded",
                  ip_str(ih->saddr), ip_str(ih->daddr));
        icmp_send_error(rt, in_ifidx, frame, len,
                        ICMP_TIME_EXCEEDED, 0);
        return;
    }

    /* (2) Lookup della rotta (longest prefix match). Nessuna rotta ->
     * Destination Unreachable (network), come fa un router reale. */
    struct rib_entry *e = rib_lookup(ih->daddr);
    if (!e) {
        LOG_DEBUG("nessuna rotta per %s, genero Net Unreachable",
                  ip_str(ih->daddr));
        icmp_send_error(rt, in_ifidx, frame, len,
                        ICMP_DEST_UNREACH, ICMP_CODE_NET_UNREACH);
        return;
    }

    /* (3) Decrementa il TTL aggiornando il checksum in modo incrementale. */
    ipv4_ttl_dec(ih);

    struct iface *out = &rt->ifaces[e->ifidx];
    /* Il next-hop è il gateway della rotta; se la rotta è connessa
     * (next_hop == 0) il next-hop è la destinazione stessa (è sulla LAN). */
    uint32_t next_hop = e->next_hop ? e->next_hop : ih->daddr;

    /* (4) Riscrivi il MAC sorgente con quello dell'interfaccia di uscita.
     * Il MAC di destinazione lo mette arp_output quando risolve il next-hop. */
    struct eth_hdr *eh = (struct eth_hdr *)frame;
    memcpy(eh->src, out->mac, 6);

    /* Il frame ricevuto può contenere padding Ethernet (i frame minimi
     * sono 60 byte): ritrasmettiamo solo i byte utili, cioè header
     * Ethernet + lunghezza totale IP dichiarata. */
    size_t flen = ETH_HDR_LEN + ntohs(ih->tot_len);

    LOG_DEBUG("inoltro %s -> %s via %s dev %s ttl=%u",
              ip_str(ih->saddr), ip_str(ih->daddr),
              e->next_hop ? ip_str(next_hop) : "diretto", out->name, ih->ttl);
    /* (5) Consegna al modulo ARP: invia subito se il MAC è noto, altrimenti
     * accoda e risolve. */
    arp_output(rt, e->ifidx, next_hop, frame, flen);
}
