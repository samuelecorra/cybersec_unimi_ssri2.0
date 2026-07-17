/* =====================================================================
 * forward.h  —  Motore di inoltro (il "verbo" del router).
 *
 * Prende un pacchetto IPv4 GIÀ VALIDATO destinato a qualcun altro e lo
 * inoltra: controlla il TTL, decrementa e aggiorna il checksum, cerca la
 * rotta (LPM), riscrive i MAC e trasmette. Genera gli ICMP di errore
 * quando serve (TTL scaduto, nessuna rotta) delegandoli al modulo icmp.
 * ===================================================================== */
#ifndef CROUTER_FORWARD_H
#define CROUTER_FORWARD_H

#include "common.h"

/* Inoltra il frame ricevuto sull'interfaccia in_ifidx. Il pacchetto IP è
 * già stato validato dal chiamante (ipv4_valid). 'len' è la lunghezza del
 * frame ricevuto (può includere padding Ethernet). */
void forward_ip(struct router *rt, int in_ifidx, uint8_t *frame, size_t len);

#endif /* CROUTER_FORWARD_H */
