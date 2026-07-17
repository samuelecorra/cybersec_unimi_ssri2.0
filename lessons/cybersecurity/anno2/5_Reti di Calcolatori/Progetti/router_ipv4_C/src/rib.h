/* =====================================================================
 * rib.h  —  RIB (Routing Information Base): la tabella di routing.
 *
 * È l'array di rotte su cui il forwarding fa il "longest prefix match".
 * Ogni rotta ha un'ORIGINE (connessa / statica / RIP) e una METRICA. A
 * parità di prefisso, la scelta è governata dalla "distanza amministrativa"
 * (connessa < statica < RIP), come sui router Cisco.
 * ===================================================================== */
#ifndef CROUTER_RIB_H
#define CROUTER_RIB_H

#include "common.h"

#define RIB_MAX      128   /* numero massimo di rotte in tabella             */
#define RIP_INFINITY 16    /* metrica RIP che significa "irraggiungibile"    */

/* Origini di una rotta, in ordine di affidabilità decrescente. L'ordine
 * QUI indicizza gli array dist[]/origin_ch[] in rib.c. */
enum { ORIG_CONNECTED, ORIG_STATIC, ORIG_RIP };

/* Una rotta. È esattamente la struct proposta nel documento di design
 * (L2 §2.4), con l'aggiunta dei due campi di stato usati da RIP
 * (garbage_at, changed). */
struct rib_entry {
    bool     in_use;      /* slot occupato?                                  */
    uint32_t prefix;      /* rete di destinazione, network order, mascherata */
    uint8_t  prefix_len;  /* lunghezza del prefisso (per l'LPM)              */
    uint32_t next_hop;    /* IP del prossimo hop; 0 = rete connessa          */
    int      ifidx;       /* interfaccia di uscita (indice in router.ifaces) */
    uint8_t  metric;      /* hop count RIP; 16 = infinito                    */
    uint8_t  origin;      /* ORIG_CONNECTED | ORIG_STATIC | ORIG_RIP         */
    time_t   last_update; /* ultimo aggiornamento (clock monotono) -> timeout */
    time_t   garbage_at;  /* 0 = viva; >0 = in cancellazione da quell'istante */
    bool     changed;     /* da annunciare nel prossimo triggered update     */
};

void rib_init(const struct router *rt);

/* Longest Prefix Match: fra tutte le rotte che "contengono" dst, ritorna
 * la più specifica (prefisso più lungo); a parità, vince la distanza
 * amministrativa minore e poi la metrica minore. Ignora le rotte a
 * metrica 16 (in cancellazione). NULL se nessuna rotta matcha. */
struct rib_entry *rib_lookup(uint32_t dst);

/* Ricerca ESATTA per (prefisso, lunghezza) — usata da RIP per aggiornare
 * una rotta specifica. */
struct rib_entry *rib_find(uint32_t prefix, uint8_t len);

/* Inserisce o aggiorna la rotta (prefix/len). Ritorna la entry o NULL. */
struct rib_entry *rib_set(uint32_t prefix, uint8_t len, uint32_t next_hop,
                          int ifidx, uint8_t metric, uint8_t origin);
void rib_delete(struct rib_entry *e);

/* Accesso "grezzo" alla tabella, per le scansioni di RIP (che deve
 * iterare su tutte le rotte per gli update e i timer). */
int rib_size(void);                 /* = RIB_MAX                             */
struct rib_entry *rib_slot(int i);  /* la i-esima entry (anche se libera)    */

void rib_dump(void);                /* stampa la tabella (SIGUSR1)           */

#endif /* CROUTER_RIB_H */
