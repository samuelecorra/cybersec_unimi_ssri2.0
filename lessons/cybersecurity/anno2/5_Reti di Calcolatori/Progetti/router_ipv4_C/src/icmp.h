/* =====================================================================
 * icmp.h  —  Generazione di messaggi ICMP (RFC 792).
 *
 * Due funzioni: rispondere agli Echo Request (il "ping" verso i nostri
 * IP) e generare gli errori ICMP durante l'inoltro (Time Exceeded per
 * traceroute, Destination Unreachable quando manca la rotta o la porta).
 * ===================================================================== */
#ifndef CROUTER_ICMP_H
#define CROUTER_ICMP_H

#include "common.h"

/* Tipi ICMP (campo 'type'). */
#define ICMP_ECHO_REPLY    0
#define ICMP_DEST_UNREACH  3
#define ICMP_ECHO_REQUEST  8
#define ICMP_TIME_EXCEEDED 11

/* Codici (campo 'code') per Destination Unreachable. */
#define ICMP_CODE_NET_UNREACH  0    /* rete non raggiungibile               */
#define ICMP_CODE_HOST_UNREACH 1    /* host non raggiungibile               */
#define ICMP_CODE_PORT_UNREACH 3    /* porta chiusa (UDP verso di noi)      */

/* Gestisce un ICMP destinato a noi: se è un Echo Request, risponde con
 * Echo Reply (fa "rispondere al ping" i nostri IP). */
void icmp_input(struct router *rt, int ifidx, uint8_t *frame, size_t len);

/* Genera un errore ICMP ('type'/'code') verso il mittente del pacchetto
 * 'orig'. Come vuole l'RFC 792, il messaggio cita l'header IP originale +
 * i primi 8 byte del suo payload, così il mittente capisce quale flusso è
 * stato colpito. */
void icmp_send_error(struct router *rt, int in_ifidx, const uint8_t *orig,
                     size_t orig_len, uint8_t type, uint8_t code);

#endif /* CROUTER_ICMP_H */
