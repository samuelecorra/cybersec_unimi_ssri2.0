/* =====================================================================
 * config.h  —  Parsing del file di configurazione di crouter.
 *
 * Il file di testo descrive quali interfacce gestire (con IP/prefisso e
 * se abilitare RIP) ed eventuali rotte statiche. Esempio:
 *
 *     interface eth1 10.0.1.1/24 rip
 *     interface eth2 10.0.12.1/30 rip
 *     static 10.0.4.0/24 via 10.0.12.2
 * ===================================================================== */
#ifndef CROUTER_CONFIG_H
#define CROUTER_CONFIG_H

#include "common.h"

#define MAX_STATIC 32   /* numero massimo di rotte statiche configurabili    */

/* Una rotta statica letta dal file: "raggiungi <prefix>/<len> via <gateway>". */
struct static_route {
    uint32_t prefix;      /* rete di destinazione, network order, già mascherata */
    uint8_t  prefix_len;  /* lunghezza del prefisso                              */
    uint32_t via;         /* IP del next-hop (gateway), network order            */
};

/* Legge 'path' e riempie: le interfacce dentro 'rt', le rotte statiche
 * nell'array 'statics' (con il conteggio in *n_statics).
 * Ritorna 0 se tutto ok, -1 su errore (file assente o riga non valida). */
int config_load(const char *path, struct router *rt,
                struct static_route *statics, int *n_statics);

#endif /* CROUTER_CONFIG_H */
