/* =====================================================================
 * ripd.h  —  RIPv2, Routing Information Protocol versione 2 (RFC 2453).
 *
 * Il piano di CONTROLLO: fa imparare e mantenere aggiornate le rotte
 * scambiando annunci con i router vicini. RIP è un protocollo
 * "distance-vector": ogni router dice ai vicini "so raggiungere la rete X
 * a distanza (metrica) m"; chi ascolta somma 1 e sceglie il percorso più
 * corto. La metrica è il numero di hop, con 16 = infinito (irraggiungibile).
 *
 * Trasporto: UDP porta 520, annunci in multicast 224.0.0.9.
 * ===================================================================== */
#ifndef CROUTER_RIPD_H
#define CROUTER_RIPD_H

#include "common.h"

#define RIP_PORT       520
#define RIP_MCAST_HOST 0xE0000009u   /* 224.0.0.9 in HOST order (E0.00.00.09) */

/* All'avvio: invia le Request iniziali (per convergere subito) e arma il
 * timer degli update periodici. */
void ripd_init(struct router *rt);

/* Elabora un datagramma RIP (UDP/520) ricevuto, già validato a livello IP. */
void ripd_input(struct router *rt, int ifidx, uint8_t *frame, size_t len);

/* Chiamata periodica dall'event loop: gestisce timeout/garbage delle
 * rotte, invia i triggered update pendenti e gli update periodici (ogni
 * ~30 s con jitter). */
void ripd_tick(struct router *rt);

#endif /* CROUTER_RIPD_H */
