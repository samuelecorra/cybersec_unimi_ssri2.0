#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct Nodo {
    char carattere;
    struct Nodo *successivo;
} Nodo;

typedef struct {
    Nodo *testa;
    Nodo *coda;
} Lista;

static void inizializzaLista(Lista *lista) {
    lista->testa = NULL;
    lista->coda = NULL;
}

static bool inserisciInCoda(Lista *lista, char carattere) {
    Nodo *nuovo = malloc(sizeof *nuovo);
    if (nuovo == NULL) return false;
    nuovo->carattere = carattere;
    nuovo->successivo = NULL;

    if (lista->coda == NULL) lista->testa = nuovo;
    else lista->coda->successivo = nuovo;
    lista->coda = nuovo;
    return true;
}

static bool separaStringa(const char *testo, Lista *alfabetici, Lista *nonAlfabetici) {
    for (const unsigned char *p = (const unsigned char *)testo; *p != '\0'; ++p) {
        Lista *destinazione = isalpha(*p) ? alfabetici : nonAlfabetici;
        if (!inserisciInCoda(destinazione, (char)*p)) return false;
    }
    return true;
}

static void stampaLista(const Lista *lista) {
    for (const Nodo *nodo = lista->testa; nodo != NULL; nodo = nodo->successivo) {
        unsigned char valore = (unsigned char)nodo->carattere;
        if (valore == ' ') fputs("'spc' ", stdout);
        else if (valore == '\t') fputs("'\\t' ", stdout);
        else if (valore < 32 || valore == 127) printf("[0x%02X] ", (unsigned int)valore);
        else printf("'%c' ", nodo->carattere);
    }
    putchar('\n');
}

static void liberaLista(Lista *lista) {
    Nodo *corrente = lista->testa;
    while (corrente != NULL) {
        Nodo *successivo = corrente->successivo;
        free(corrente);
        corrente = successivo;
    }
    inizializzaLista(lista);
}

int main(void) {
    const char input[] = "Ciao 123!";
    Lista alfabetici;
    Lista nonAlfabetici;
    inizializzaLista(&alfabetici);
    inizializzaLista(&nonAlfabetici);

    if (!separaStringa(input, &alfabetici, &nonAlfabetici)) {
        fputs("Memoria insufficiente.\n", stderr);
        liberaLista(&alfabetici);
        liberaLista(&nonAlfabetici);
        return 1;
    }

    fputs("Lista alfabetici: ", stdout);
    stampaLista(&alfabetici);
    fputs("Lista non alfabetici: ", stdout);
    stampaLista(&nonAlfabetici);
    liberaLista(&alfabetici);
    liberaLista(&nonAlfabetici);
    return 0;
}
