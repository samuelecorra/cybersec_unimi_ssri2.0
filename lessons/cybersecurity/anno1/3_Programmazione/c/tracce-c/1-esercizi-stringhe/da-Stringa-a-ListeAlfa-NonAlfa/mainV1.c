/* Versione base: inserire in coda richiede ogni volta una scansione O(n). */

#include <ctype.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct Nodo {
    char carattere;
    struct Nodo *successivo;
} Nodo;

static int aggiungiInCoda(Nodo **testa, char carattere) {
    Nodo *nuovo = malloc(sizeof *nuovo);
    if (nuovo == NULL) return 0;
    nuovo->carattere = carattere;
    nuovo->successivo = NULL;

    Nodo **collegamento = testa;
    while (*collegamento != NULL) collegamento = &(*collegamento)->successivo;
    *collegamento = nuovo;
    return 1;
}

static void liberaLista(Nodo **testa) {
    while (*testa != NULL) {
        Nodo *successivo = (*testa)->successivo;
        free(*testa);
        *testa = successivo;
    }
}

static char *leggiRiga(void) {
    size_t capacità = 32;
    size_t lunghezza = 0;
    char *testo = malloc(capacità);
    if (testo == NULL) return NULL;

    int carattere = 0;
    while ((carattere = getchar()) != '\n' && carattere != EOF) {
        if (lunghezza + 1 == capacità) {
            if (capacità > SIZE_MAX / 2) {
                free(testo);
                return NULL;
            }
            size_t nuovaCapacità = capacità * 2;
            char *temporaneo = realloc(testo, nuovaCapacità);
            if (temporaneo == NULL) {
                free(testo);
                return NULL;
            }
            testo = temporaneo;
            capacità = nuovaCapacità;
        }
        testo[lunghezza++] = (char)carattere;
    }
    if (carattere == EOF && lunghezza == 0) {
        free(testo);
        return NULL;
    }
    testo[lunghezza] = '\0';
    return testo;
}

static int separaStringa(const char *testo, Nodo **alfabetici, Nodo **nonAlfabetici) {
    for (const unsigned char *p = (const unsigned char *)testo; *p != '\0'; ++p) {
        Nodo **destinazione = isalpha(*p) ? alfabetici : nonAlfabetici;
        if (!aggiungiInCoda(destinazione, (char)*p)) return 0;
    }
    return 1;
}

static void stampaLista(const Nodo *testa) {
    for (const Nodo *nodo = testa; nodo != NULL; nodo = nodo->successivo) {
        if (nodo->carattere == ' ') fputs("'spc' ", stdout);
        else printf("'%c' ", nodo->carattere);
    }
    putchar('\n');
}

int main(void) {
    fputs("Inserisci una stringa: ", stdout);
    char *input = leggiRiga();
    if (input == NULL) {
        fputs("Lettura o allocazione non riuscita.\n", stderr);
        return 1;
    }

    Nodo *alfabetici = NULL;
    Nodo *nonAlfabetici = NULL;
    if (!separaStringa(input, &alfabetici, &nonAlfabetici)) {
        fputs("Memoria insufficiente.\n", stderr);
        liberaLista(&alfabetici);
        liberaLista(&nonAlfabetici);
        free(input);
        return 1;
    }

    fputs("Caratteri alfabetici: ", stdout);
    stampaLista(alfabetici);
    fputs("Caratteri non alfabetici: ", stdout);
    stampaLista(nonAlfabetici);

    liberaLista(&alfabetici);
    liberaLista(&nonAlfabetici);
    free(input);
    return 0;
}
