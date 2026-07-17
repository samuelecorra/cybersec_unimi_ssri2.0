/* =====================================================================
 * netio.h  —  I/O di rete grezzo su socket AF_PACKET.
 *
 * È il livello più basso di crouter: apre un socket "raw" per ogni
 * interfaccia e legge/scrive FRAME ETHERNET COMPLETI (header L2 inclusi).
 * È ciò che rende il router "userspace": lo stack del kernel non tocca
 * questi pacchetti, li vediamo e li costruiamo noi byte per byte.
 * ===================================================================== */
#ifndef CROUTER_NETIO_H
#define CROUTER_NETIO_H

#include "common.h"

/* Apre il socket AF_PACKET/SOCK_RAW legato all'interfaccia 'ifc', ne
 * ricava ifindex e MAC (via ioctl) e attiva la modalità promiscua.
 * Riempie ifc->fd, ifc->sys_ifindex, ifc->mac. Ritorna il fd o -1. */
int netio_open(struct iface *ifc);

/* Legge il prossimo frame in ingresso (NON bloccante). Ritorna la
 * lunghezza in byte, oppure 0 se al momento non c'è nulla da leggere.
 * Scarta i frame in uscita (i nostri stessi TX, che il socket riflette)
 * e quelli non destinati a noi a livello 2. */
ssize_t netio_recv(struct iface *ifc, uint8_t *buf, size_t sz);

/* Trasmette 'len' byte (un frame Ethernet già completo) sull'interfaccia. */
void netio_send(struct iface *ifc, const void *frame, size_t len);

#endif /* CROUTER_NETIO_H */
