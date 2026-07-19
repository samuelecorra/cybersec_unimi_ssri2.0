#include <stdio.h>
#include "header.h"

enum esito_lettura {
    LETTURA_ERRORE = -1,
    LETTURA_FINE = 0,
    LETTURA_VALORE = 1
};

/* Distingue correttamente un intero, la fine regolare e un input malformato. */
static enum esito_lettura leggi_prossimo(FILE *fp, int *val)
{
    int esito = fscanf(fp, "%d", val);
    if (esito == 1) {
        return LETTURA_VALORE;
    }
    if (esito == EOF && !ferror(fp)) {
        return LETTURA_FINE;
    }
    return LETTURA_ERRORE;
}

int unisciFile(const char *file1in, const char *file2in, const char *fileOut)
{
    FILE *f1 = fopen(file1in, "r");
    FILE *f2 = fopen(file2in, "r");
    if (f1 == NULL || f2 == NULL) {
        if (f1) fclose(f1);
        if (f2) fclose(f2);
        return 0;
    }

    FILE *fo = fopen(fileOut, "w");
    if (fo == NULL) {
        fclose(f1);
        fclose(f2);
        return 0;
    }

    int v1;
    int v2;
    enum esito_lettura stato1 = leggi_prossimo(f1, &v1);
    enum esito_lettura stato2 = leggi_prossimo(f2, &v2);

    int primo = 1;
    int riuscito = stato1 != LETTURA_ERRORE && stato2 != LETTURA_ERRORE;
    while (riuscito &&
           (stato1 == LETTURA_VALORE || stato2 == LETTURA_VALORE)) {
        int out;
        if (stato1 == LETTURA_VALORE && stato2 == LETTURA_VALORE) {
            if (v1 <= v2) { out = v1; stato1 = leggi_prossimo(f1, &v1); }
            else          { out = v2; stato2 = leggi_prossimo(f2, &v2); }
        } else if (stato1 == LETTURA_VALORE) {
            out = v1;
            stato1 = leggi_prossimo(f1, &v1);
        } else {
            out = v2;
            stato2 = leggi_prossimo(f2, &v2);
        }

        if (stato1 == LETTURA_ERRORE || stato2 == LETTURA_ERRORE) {
            riuscito = 0;
        }
        if (riuscito && !primo && fputc(' ', fo) == EOF) {
            riuscito = 0;
        }
        if (riuscito && fprintf(fo, "%d", out) < 0) {
            riuscito = 0;
        }
        primo = 0;
    }

    if (riuscito && fputc('\n', fo) == EOF) {
        riuscito = 0;
    }
    if (fclose(f1) == EOF) riuscito = 0;
    if (fclose(f2) == EOF) riuscito = 0;
    if (fclose(fo) == EOF) riuscito = 0;
    return riuscito;
}
