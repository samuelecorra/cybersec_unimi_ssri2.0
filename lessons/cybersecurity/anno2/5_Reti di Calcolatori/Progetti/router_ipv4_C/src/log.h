/* =====================================================================
 * log.h  —  Logging a livelli con timestamp.
 *
 * Quattro livelli di gravità decrescente: ERR < WARN < INFO < DEBUG.
 * Impostando la soglia con log_set_level() si filtra il rumore: a INFO
 * (default) i messaggi DEBUG non vengono stampati; con -v (DEBUG) si
 * vede tutto, utile durante i test.
 * ===================================================================== */
#ifndef CROUTER_LOG_H
#define CROUTER_LOG_H

/* I livelli sono interi crescenti: un messaggio è stampato se il suo
 * livello è <= alla soglia corrente. L'ordine QUI conta, perché indicizza
 * gli array di etichette e colori in log.c. */
enum { LOG_L_ERR, LOG_L_WARN, LOG_L_INFO, LOG_L_DEBUG };

void log_set_level(int lvl);   /* imposta la soglia (default LOG_L_INFO)     */

/* La firma con "..." la rende variadica come printf.
 * __attribute__((format(printf, 2, 3))) è un'estensione GCC: dice al
 * compilatore che dall'argomento 2 (fmt) in poi vale la sintassi di
 * printf, così `-Wall` controlla che i %d/%s combacino con gli argomenti
 * — gli stessi controlli che avresti su printf. */
void log_msg(int lvl, const char *fmt, ...) __attribute__((format(printf, 2, 3)));

/* Macro di comodo: si scrive LOG_INFO("x=%d", x) invece di passare il
 * livello a mano. __VA_ARGS__ inoltra tutti gli argomenti variabili. */
#define LOG_ERR(...)   log_msg(LOG_L_ERR,   __VA_ARGS__)
#define LOG_WARN(...)  log_msg(LOG_L_WARN,  __VA_ARGS__)
#define LOG_INFO(...)  log_msg(LOG_L_INFO,  __VA_ARGS__)
#define LOG_DEBUG(...) log_msg(LOG_L_DEBUG, __VA_ARGS__)

#endif /* CROUTER_LOG_H */
