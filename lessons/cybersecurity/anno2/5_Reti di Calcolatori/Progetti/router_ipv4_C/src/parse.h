/* =====================================================================
 * parse.h  —  Checksum e validazione degli header.
 *
 * Raccoglie le funzioni "matematiche" del data plane: il checksum
 * Internet (RFC 1071), il checksum UDP con pseudo-header, la validazione
 * dell'header IPv4 e il decremento del TTL con aggiornamento incrementale
 * del checksum (RFC 1624).
 * ===================================================================== */
#ifndef CROUTER_PARSE_H
#define CROUTER_PARSE_H

#include "common.h"

/* Checksum Internet (RFC 1071) sul buffer indicato; ritorna il valore
 * già in network order, pronto per essere scritto nel campo header.
 * Proprietà chiave: calcolata su un header il cui campo checksum è già
 * corretto, restituisce 0 — è così che si VERIFICA un checksum. */
uint16_t inet_cksum(const void *data, size_t len);

/* Checksum UDP: include lo "pseudo-header" IPv4 (saddr/daddr/proto/len),
 * come richiesto dall'RFC 768. saddr/daddr sono in network order. */
uint16_t udp_cksum(uint32_t saddr, uint32_t daddr, const void *seg, size_t seg_len);

/* Validazione completa dell'header IPv4: versione=4, IHL sensato,
 * lunghezze coerenti con i byte disponibili, checksum corretto.
 * 'avail' = byte effettivamente presenti (per non leggere oltre il buffer). */
bool ipv4_valid(const uint8_t *ip_pkt, size_t avail);

/* Decrementa il TTL aggiornando il checksum in modo INCREMENTALE, senza
 * ricalcolarlo su tutto l'header (RFC 1624, eq. 3). */
void ipv4_ttl_dec(struct ipv4_hdr *ih);

#endif /* CROUTER_PARSE_H */
