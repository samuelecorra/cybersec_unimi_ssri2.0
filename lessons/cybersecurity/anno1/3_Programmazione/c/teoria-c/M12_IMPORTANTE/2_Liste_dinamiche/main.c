/*
 * Una lista concatenata è composta da nodi allocati separatamente. Ogni nodo
 * contiene un dato e il collegamento al successivo. "Testa" e "coda" indicano
 * ruoli: la testa è il primo nodo (o NULL nella lista vuota); la coda è l'ultimo
 * nodo e non deve necessariamente essere conservata in una variabile dedicata.
 *
 * Vantaggi: dimensione dinamica e inserimenti/rimozioni O(1) quando si possiede
 * già il collegamento da aggiornare. Costi: un puntatore per nodo, allocazioni,
 * scarsa località di cache e accesso per indice O(n). Una lista non usa quindi
 * automaticamente meno memoria di un array e non rende ricerca/ordinamento facili.
 */

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct Nodo {
    int dato;
    struct Nodo *prossimo;
} Nodo;

static Nodo *creaNodo(int dato) {
    Nodo *nuovo = malloc(sizeof *nuovo);
    if (nuovo != NULL) {
        nuovo->dato = dato;
        nuovo->prossimo = NULL;
    }
    return nuovo;
}

static bool inserisciInTesta(Nodo **testa, int dato) {
    if (testa == NULL) {
        return false;
    }
    Nodo *nuovo = creaNodo(dato);
    if (nuovo == NULL) {
        return false;
    }
    nuovo->prossimo = *testa;
    *testa = nuovo;
    return true;
}

static bool inserisciInCoda(Nodo **testa, int dato) {
    if (testa == NULL) {
        return false;
    }
    Nodo *nuovo = creaNodo(dato);
    if (nuovo == NULL) {
        return false;
    }
    Nodo **collegamento = testa;
    while (*collegamento != NULL) {
        collegamento = &(*collegamento)->prossimo;
    }
    *collegamento = nuovo;
    return true;
}

static bool rimuoviPrimo(Nodo **testa, int dato) {
    if (testa == NULL) {
        return false;
    }
    Nodo **collegamento = testa;
    while (*collegamento != NULL && (*collegamento)->dato != dato) {
        collegamento = &(*collegamento)->prossimo;
    }
    if (*collegamento == NULL) {
        return false;
    }
    Nodo *daEliminare = *collegamento;
    *collegamento = daEliminare->prossimo;
    free(daEliminare);
    return true;
}

static void stampaLista(const Nodo *testa) {
    for (const Nodo *corrente = testa; corrente != NULL; corrente = corrente->prossimo) {
        printf("%d -> ", corrente->dato);
    }
    puts("NULL");
}

static void distruggiLista(Nodo **testa) {
    if (testa == NULL) {
        return;
    }
    Nodo *corrente = *testa;
    while (corrente != NULL) {
        Nodo *successivo = corrente->prossimo; // salvare prima di free
        free(corrente);
        corrente = successivo;
    }
    *testa = NULL;
}

int main(void) {
    Nodo *testa = NULL;
    if (!inserisciInCoda(&testa, 10)
        || !inserisciInCoda(&testa, 20)
        || !inserisciInCoda(&testa, 30)
        || !inserisciInTesta(&testa, 5)) {
        fputs("Allocazione di un nodo non riuscita.\n", stderr);
        distruggiLista(&testa);
        return EXIT_FAILURE;
    }

    stampaLista(testa);
    (void)rimuoviPrimo(&testa, 20);
    stampaLista(testa);
    distruggiLista(&testa);
    return EXIT_SUCCESS;
}
