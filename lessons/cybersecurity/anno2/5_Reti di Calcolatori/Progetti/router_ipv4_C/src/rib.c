/* =====================================================================
 * rib.c  —  Tabella di routing e longest prefix match.
 *
 * Implementazione volutamente semplice: un array lineare scandito per
 * intero a ogni lookup. Con le decine di rotte della demo è più che
 * sufficiente; l'alternativa (trie binario / PATRICIA) è discussa in
 * relazione come evoluzione per grandi tabelle.
 * ===================================================================== */
#include <string.h>

#include "log.h"
#include "rib.h"

static struct rib_entry tab[RIB_MAX];   /* la tabella vera e propria         */
static const struct router *g_rt;       /* riferimento per risolvere i nomi if */

/* Distanza amministrativa per origine (valori "alla Cisco"): più basso =
 * più fidato. Indicizzati da ORIG_CONNECTED/STATIC/RIP. Una connessa (0)
 * batte una statica (1) che batte una RIP (120), a parità di prefisso. */
static const uint8_t dist[]      = { 0, 1, 120 };
/* Lettera mostrata nei log/dump per ciascuna origine. */
static const char    origin_ch[] = { 'C', 'S', 'R' };

void rib_init(const struct router *rt)
{
    memset(tab, 0, sizeof tab);
    g_rt = rt;
}

struct rib_entry *rib_lookup(uint32_t dst)
{
    struct rib_entry *best = NULL;
    for (int i = 0; i < RIB_MAX; i++) {
        struct rib_entry *e = &tab[i];
        /* Salta gli slot liberi e le rotte a metrica infinita (in
         * cancellazione): non sono instradabili. */
        if (!e->in_use || e->metric >= RIP_INFINITY)
            continue;
        /* La rotta "contiene" dst se, mascherando dst con la netmask della
         * rotta, si ottiene esattamente il suo prefisso. */
        if ((dst & prefix_to_mask(e->prefix_len)) != e->prefix)
            continue;
        /* Criterio di preferenza, in ordine:
         *   1) prefisso più lungo (più specifico) = longest prefix match;
         *   2) a parità, distanza amministrativa minore;
         *   3) a parità, metrica minore. */
        if (!best ||
            e->prefix_len > best->prefix_len ||
            (e->prefix_len == best->prefix_len &&
             dist[e->origin] < dist[best->origin]) ||
            (e->prefix_len == best->prefix_len &&
             dist[e->origin] == dist[best->origin] &&
             e->metric < best->metric))
            best = e;
    }
    return best;
}

struct rib_entry *rib_find(uint32_t prefix, uint8_t len)
{
    for (int i = 0; i < RIB_MAX; i++)
        if (tab[i].in_use && tab[i].prefix == prefix && tab[i].prefix_len == len)
            return &tab[i];
    return NULL;
}

/* Nome dell'interfaccia i-esima (per log leggibili); '?' se non ancora
 * collegata la router globale. */
static const char *ifname(int ifidx)
{
    return g_rt ? g_rt->ifaces[ifidx].name : "?";
}

struct rib_entry *rib_set(uint32_t prefix, uint8_t len, uint32_t next_hop,
                          int ifidx, uint8_t metric, uint8_t origin)
{
    /* Se la rotta esiste la aggiorniamo (op '~'); altrimenti cerchiamo uno
     * slot libero e la inseriamo (op '+'). 'op' serve solo per il log. */
    struct rib_entry *e = rib_find(prefix, len);
    char op = '~';
    if (!e) {
        for (int i = 0; i < RIB_MAX; i++)
            if (!tab[i].in_use) {
                e = &tab[i];
                break;
            }
        if (!e) {
            LOG_ERR("RIB piena, impossibile aggiungere %s/%u", ip_str(prefix), len);
            return NULL;
        }
        op = '+';
    }
    e->in_use      = true;
    e->prefix      = prefix;
    e->prefix_len  = len;
    e->next_hop    = next_hop;
    e->ifidx       = ifidx;
    e->metric      = metric;
    e->origin      = origin;
    e->last_update = mono_now();
    e->garbage_at  = 0;
    e->changed     = true;         /* nuova/variata: da annunciare in RIP     */
    LOG_INFO("RIB: %c %s/%u via %s dev %s metrica %u [%c]",
             op, ip_str(prefix), len,
             next_hop ? ip_str(next_hop) : "-", ifname(ifidx),
             metric, origin_ch[origin]);
    return e;
}

void rib_delete(struct rib_entry *e)
{
    LOG_INFO("RIB: - %s/%u via %s dev %s [%c]",
             ip_str(e->prefix), e->prefix_len,
             e->next_hop ? ip_str(e->next_hop) : "-", ifname(e->ifidx),
             origin_ch[e->origin]);
    e->in_use = false;             /* lo slot torna disponibile               */
}

int rib_size(void)
{
    return RIB_MAX;
}

struct rib_entry *rib_slot(int i)
{
    return &tab[i];
}

void rib_dump(void)
{
    time_t now = mono_now();
    printf("\n================== RIB (tabella di routing) ==================\n");
    /* I "%-20s" ecc. sono campi allineati a sinistra a larghezza fissa,
     * per una tabella incolonnata. */
    printf("%-20s %-15s %-6s %-4s %-4s %s\n",
           "destinazione", "next-hop", "dev", "met", "org", "eta'");
    for (int i = 0; i < RIB_MAX; i++) {
        struct rib_entry *e = &tab[i];
        if (!e->in_use)
            continue;
        char dest[24], age[24];
        snprintf(dest, sizeof dest, "%s/%u", ip_str(e->prefix), e->prefix_len);
        /* L'"età" ha senso solo per le rotte RIP (soggette a timeout); per
         * connesse/statiche mostriamo "-". "(gc)" segnala la fase di
         * garbage collection. */
        if (e->origin == ORIG_RIP)
            snprintf(age, sizeof age, "%lds%s",
                     (long)(now - e->last_update), e->garbage_at ? " (gc)" : "");
        else
            snprintf(age, sizeof age, "-");
        printf("%-20s %-15s %-6s %-4u %-4c %s\n",
               dest, e->next_hop ? ip_str(e->next_hop) : "-",
               ifname(e->ifidx), e->metric, origin_ch[e->origin], age);
    }
    printf("===============================================================\n\n");
    fflush(stdout);
}
