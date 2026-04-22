#include <stdio.h>
#include <stdlib.h>

// La coda viene implementata come una lista dinamica:
typedef struct Nodo {
    int dato;
    struct Nodo* prossimo;
} Nodo;

// Solo dopo aver definito il tipo Nodo, possiamo definire il tipo Coda:
typedef struct Coda {
    Nodo* testa;
    Nodo* coda;
} Coda;

// Funzione per creare una nuova coda vuota
Coda* creaCoda() {
    Coda* c = (Coda*)malloc(sizeof(Coda));
    c->testa = NULL;
    c->coda = NULL;
    return c;
}

// Ora supponiamo che la coda riceva dei lavori che deve passare poi
// a un server che li elabora internamente. Serve dunque una funzione
// per inserire un nuovo lavoro nella coda:
void accodaLavoro ( evento *E, Coda *L, tempo t) {
    tempo delay = random_delay(); // Simula un ritardo casuale
}