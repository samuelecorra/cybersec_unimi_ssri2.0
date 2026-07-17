/* =====================================================================
 * log.c  —  Implementazione del logging a livelli.
 * ===================================================================== */
#include <stdarg.h>    /* va_list/va_start/va_end: argomenti variadici       */
#include <stdio.h>     /* printf, vprintf, putchar, fflush, fileno           */
#include <sys/time.h>  /* gettimeofday, struct timeval (per i millisecondi)  */
#include <time.h>      /* localtime_r, struct tm                             */
#include <unistd.h>    /* isatty (rileva se stdout è un terminale)           */

#include "log.h"

static int g_level = LOG_L_INFO;  /* soglia corrente; cambiata da log_set_level */
static int g_color = -1;          /* -1 = "non ancora deciso" (vedi log_msg)    */

void log_set_level(int lvl)
{
    g_level = lvl;
}

void log_msg(int lvl, const char *fmt, ...)
{
    /* Etichetta e colore per ciascun livello. Gli indici corrispondono
     * all'enum in log.h (ERR=0, WARN=1, INFO=2, DEBUG=3). I codici tipo
     * "\033[1;31m" sono sequenze di escape ANSI: \033 è il carattere ESC,
     * "1;31" = grassetto rosso; "\033[0m" resetta il colore. */
    static const char *tag[] = { "ERR ", "WARN", "INFO", "DBG " };
    static const char *col[] = { "\033[1;31m", "\033[1;33m", "\033[1;32m", "\033[36m" };

    if (lvl > g_level)          /* messaggio meno grave della soglia: scartato */
        return;

    /* Decidiamo UNA volta sola (g_color parte a -1) se colorare: solo se
     * stdout è un vero terminale. isatty(fileno(stdout)) è falso quando
     * l'output è rediretto su file/pipe, dove i codici ANSI sarebbero
     * fastidiosi caratteri sporchi. */
    if (g_color < 0)
        g_color = isatty(fileno(stdout));

    /* gettimeofday dà secondi + microsecondi; localtime_r li spezza in
     * ora/minuti/secondi locali. La variante "_r" (reentrant) scrive in
     * una struct fornita da noi invece di un buffer statico condiviso. */
    struct timeval tv;
    struct tm tm;
    gettimeofday(&tv, NULL);
    localtime_r(&tv.tv_sec, &tm);

    /* Prefisso "HH:MM:SS.mmm [LIV] " (con o senza colore). tv_usec/1000
     * converte i microsecondi in millisecondi. */
    if (g_color)
        printf("%02d:%02d:%02d.%03ld %s[%s]\033[0m ",
               tm.tm_hour, tm.tm_min, tm.tm_sec, tv.tv_usec / 1000, col[lvl], tag[lvl]);
    else
        printf("%02d:%02d:%02d.%03ld [%s] ",
               tm.tm_hour, tm.tm_min, tm.tm_sec, tv.tv_usec / 1000, tag[lvl]);

    /* Corpo del messaggio: si inoltra la lista di argomenti variabili a
     * vprintf (la versione di printf che accetta un va_list già pronto).
     * va_start "apre" la lista partendo dall'ultimo parametro nominato
     * (fmt), va_end la chiude. */
    va_list ap;
    va_start(ap, fmt);
    vprintf(fmt, ap);
    va_end(ap);
    putchar('\n');

    /* fflush forza la scrittura immediata: senza, con output rediretto
     * su file i log resterebbero nel buffer e in caso di crash si
     * perderebbero le ultime righe (proprio quelle che servono). */
    fflush(stdout);
}
