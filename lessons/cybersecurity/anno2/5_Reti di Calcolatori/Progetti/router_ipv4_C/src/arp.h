/* =====================================================================
 * arp.h  —  Address Resolution Protocol (RFC 826).
 *
 * Per spedire un pacchetto IP a un vicino sulla LAN serve il suo MAC.
 * ARP lo scopre: "chi ha l'IP X? dimmi il tuo MAC". Questo modulo:
 *   - risponde alle richieste ARP per i NOSTRI IP;
 *   - mantiene una CACHE (IP -> MAC) con scadenza;
 *   - risolve il MAC di un next-hop, accodando il pacchetto finché la
 *     risposta non arriva (coda dei "pending").
 * ===================================================================== */
#ifndef CROUTER_ARP_H
#define CROUTER_ARP_H

#include "common.h"

void arp_init(void);   /* azzera cache e coda (all'avvio)                    */

/* Elabora un frame ARP ricevuto: apprende il mittente e, se è una
 * richiesta per un nostro IP, risponde. */
void arp_input(struct router *rt, int ifidx, uint8_t *frame, size_t len);

void arp_learn(uint32_t ip, const uint8_t *mac);      /* inserisce/aggiorna la cache */
bool arp_lookup(uint32_t ip, uint8_t *mac_out);       /* cerca in cache; true se trovato */

/* Trasmette il frame IP (MAC sorgente già impostato) verso next_hop: se
 * il MAC è in cache invia subito; altrimenti ACCODA il pacchetto e manda
 * una richiesta ARP, ritrasmettendo quando la risposta arriva. */
void arp_output(struct router *rt, int ifidx, uint32_t next_hop,
                uint8_t *frame, size_t len);

/* Chiamata periodica: ritrasmette le richieste pendenti e fa scadere le
 * entry vecchie della cache. */
void arp_tick(struct router *rt, time_t now);

#endif /* CROUTER_ARP_H */
