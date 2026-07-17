/* =====================================================================
 * config.c  —  Parser del file di configurazione (formato riga-per-riga).
 * ===================================================================== */
#include <stdio.h>
#include <string.h>

#include "config.h"
#include "log.h"

/* Converte una stringa CIDR "A.B.C.D/L" in indirizzo (network order) +
 * lunghezza prefisso. Ritorna 0 se ok, -1 se malformata.
 *
 * sscanf con "%15[0-9.]/%u":
 *   %15[0-9.] = leggi fino a 15 caratteri presi SOLO dall'insieme cifre e
 *               punto (ferma la parte IP prima della '/'); il 15 evita
 *               overflow del buffer ip[INET_ADDRSTRLEN] (16 con il '\0').
 *   /%u       = poi una '/' letterale e un intero senza segno (il prefisso).
 * Il valore di ritorno di sscanf è il numero di campi assegnati: qui deve
 * essere 2, altrimenti la stringa non aveva la forma attesa. */
static int parse_cidr(const char *s, uint32_t *addr, uint8_t *len)
{
    char ip[INET_ADDRSTRLEN];
    unsigned l;
    if (sscanf(s, "%15[0-9.]/%u", ip, &l) != 2 || l > 32)
        return -1;
    struct in_addr a;
    /* inet_pton ("presentation to network"): testo -> binario in network
     * order. Ritorna 1 SOLO se la stringa è un IPv4 valido (respinge
     * "999.0.0.1", ecc.). */
    if (inet_pton(AF_INET, ip, &a) != 1)
        return -1;
    *addr = a.s_addr;
    *len  = (uint8_t)l;
    return 0;
}

/*
 * Carica la configurazione da 'path'. Direttive riconosciute:
 *   interface <nome> <ip>/<prefisso> [rip]
 *   static <rete>/<prefisso> via <gateway>
 * Tutto ciò che segue '#' è commento; righe vuote ignorate.
 */
int config_load(const char *path, struct router *rt,
                struct static_route *statics, int *n_statics)
{
    FILE *f = fopen(path, "r");
    if (!f) {
        LOG_ERR("impossibile aprire la configurazione %s", path);
        return -1;
    }

    char line[256];
    int ln = 0;                 /* numero di riga, per messaggi d'errore utili */
    rt->n_ifaces = 0;
    *n_statics = 0;

    /* fgets legge una riga alla volta (incluso il '\n'), fino a EOF. */
    while (fgets(line, sizeof line, f)) {
        ln++;

        /* Taglia il commento: il primo '#' termina la stringa. */
        char *hash = strchr(line, '#');
        if (hash)
            *hash = '\0';

        /* strtok spezza la riga in "token" separati da spazi/tab/CR/LF.
         * La PRIMA chiamata riceve la stringa; le successive ricevono NULL
         * per continuare sulla stessa. Nota: strtok modifica 'line' in
         * loco (inserisce '\0' al posto dei separatori). */
        char *tok = strtok(line, " \t\r\n");
        if (!tok)               /* riga vuota o solo commento: salta          */
            continue;

        if (strcmp(tok, "interface") == 0) {
            /* Attesi: <nome> <cidr> [rip] */
            char *name = strtok(NULL, " \t\r\n");
            char *cidr = strtok(NULL, " \t\r\n");
            char *opt  = strtok(NULL, " \t\r\n");
            if (!name || !cidr || rt->n_ifaces >= MAX_IFACES)
                goto err;
            struct iface *ifc = &rt->ifaces[rt->n_ifaces];
            memset(ifc, 0, sizeof *ifc);        /* azzera tutti i campi        */
            /* snprintf invece di strcpy: tronca in sicurezza se il nome è
             * più lungo del buffer, senza mai sforare. */
            snprintf(ifc->name, sizeof ifc->name, "%s", name);
            if (parse_cidr(cidr, &ifc->ip, &ifc->prefix_len) < 0)
                goto err;
            ifc->mask = prefix_to_mask(ifc->prefix_len);
            ifc->rip  = opt && strcmp(opt, "rip") == 0;  /* "rip" presente? */
            ifc->fd   = -1;                      /* socket non ancora aperto    */
            rt->n_ifaces++;
        } else if (strcmp(tok, "static") == 0) {
            /* Attesi: <cidr> via <gateway> */
            char *cidr = strtok(NULL, " \t\r\n");
            char *kw   = strtok(NULL, " \t\r\n");
            char *gw   = strtok(NULL, " \t\r\n");
            if (!cidr || !kw || strcmp(kw, "via") != 0 || !gw || *n_statics >= MAX_STATIC)
                goto err;
            struct static_route *sr = &statics[*n_statics];
            if (parse_cidr(cidr, &sr->prefix, &sr->prefix_len) < 0)
                goto err;
            struct in_addr a;
            if (inet_pton(AF_INET, gw, &a) != 1)
                goto err;
            sr->via     = a.s_addr;
            /* Normalizza il prefisso azzerando i bit host (es. se scrivono
             * 10.0.4.5/24 lo riduciamo a 10.0.4.0/24). */
            sr->prefix &= prefix_to_mask(sr->prefix_len);
            (*n_statics)++;
        } else {
            goto err;           /* prima parola sconosciuta: direttiva errata  */
        }
        continue;

        /* Gestione errore comune: chiude il file e segnala riga+percorso.
         * Un'unica etichetta 'err' evita di ripetere il cleanup ovunque —
         * è l'uso "pulito" del goto in C. */
err:
        LOG_ERR("%s:%d: direttiva non valida", path, ln);
        fclose(f);
        return -1;
    }

    fclose(f);
    if (rt->n_ifaces == 0) {
        LOG_ERR("%s: nessuna interfaccia configurata", path);
        return -1;
    }
    return 0;
}
