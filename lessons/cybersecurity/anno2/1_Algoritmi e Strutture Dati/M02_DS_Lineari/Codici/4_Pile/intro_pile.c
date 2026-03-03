#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Obiettivo: partire dalla medesima struttura della lezione precedente,
// creare un alter ego di "lista" che rappresenti la pila, e implementare
// la politica LIFO che la caratterizza.

// Non ci resta che fare CTRL+V della lezione precedente...
typedef struct _cella {
    int elem;
    struct _cella *next, *prev;
} cella;

typedef cella* posizione;
typedef cella* lista;


void creaListaVuota(lista *L) {
    *L = malloc(sizeof(cella));
    if (!*L) { perror("malloc"); exit(1); }
    (*L)->next = *L;
    (*L)->prev = *L;
}

bool isListaVuota(lista L) {
    return L->next == L;
}

posizione primoNodo(lista L)   { return L->next; }
posizione ultimoNodo(lista L)  { return L->prev; }

posizione successivoDi(posizione p) { return p->next; }
posizione precedenteDi(posizione p) { return p->prev; }

bool fineLista(posizione p, lista L) { return p == L; } 

int leggiElemLista(posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Attenzione: sentinella raggiunta!\n");
        exit(1);
    }
    return p->elem;
}

void sovrascriviElemLista(int x, posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Attenzione: scrittura sulla sentinella non consentita!\n");
        exit(1);
    }
    p->elem = x;
}

void insElemInListaPrimaDellaPosizionePassata(int x, posizione p) {
    posizione n = malloc(sizeof(cella));
    if (!n) { perror("malloc"); exit(1); }
    n->elem = x;

    n->next = p;
    n->prev = p->prev;

    p->prev->next = n;
    p->prev = n;
}

void cancElemLista(posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Errore: tentativo di cancellare la sentinella!\n");
        exit(1);
    }

    p->prev->next = p->next;
    p->next->prev = p->prev;
    free(p);
}

// =========================
//        P I L A  (LIFO)
// =========================

typedef lista pila;  // pila = lista con "cima" = primo nodo dopo la sentinella

// Crea una pila vuota (solo sentinella)
void creaPilaVuota(pila *P) {
    creaListaVuota(P);
}

// true se non ci sono nodi "veri"
bool isPilaVuota(pila P) {
    return isListaVuota(P);
}

// Legge il qualsiasi elemento (senza rimuoverlo)
int leggiElemInPosizione(posizione p, pila P) {
    return leggiElemLista(p, P);
}

// Legge (senza rimuovere) l'elemento in cima
int leggiElemInCima(pila P) {
    if (isPilaVuota(P)) {
        fprintf(stderr, "Errore: leggiElemInCima su pila vuota!\n");
        exit(1);
    }
    return leggiElemLista(primoNodo(P), P);
}

// Legge (senza rimuovere) l'elemento in fondo
int leggiElemAllaBase(pila P) {
    if (isPilaVuota(P)) {
        fprintf(stderr, "Errore: leggiElemAllaBase su pila vuota!\n");
        exit(1);
    }
    return leggiElemLista(ultimoNodo(P), P);
}

// Pop: rimuove l'elemento in cima
void poppaDallaCima(pila P) {
    if (isPilaVuota(P)) {
        fprintf(stderr, "Warning: pop su pila vuota ignorato.\n");
        return;
    }
    cancElemLista(primoNodo(P), P);  // passa anche P per bloccare la sentinella
}

// Push: inserisce in cima (subito dopo la sentinella)
void pushaInCima(int x, pila P) {
    // Inseriamo un nuovo nodo PRIMA della posizione passata.
    // Se P è vuota, primoNodo(P) == sentinella: va benissimo.
    insElemInListaPrimaDellaPosizionePassata(x, primoNodo(P));
}

// (opzionale) Svuota la pila e libera anche la sentinella
void distruggiPila(pila *P) {
    if (!*P) return;
    posizione cur = successivoDi(*P);
    while (!fineLista(cur, *P)) {
        posizione nxt = successivoDi(cur);
        cancElemLista(cur, *P);
        cur = nxt;
    }
    free(*P);   // libera la sentinella
    *P = NULL;
}

// Funzioni di stampa:

// Vogliamo realizzare una funzione di stampa che:

// 1) Se la pila è vuota, stampa: "Pila = [ Sentinella P ]"
// 2) Altrimenti, stampa: 

//    a) Se la pila ha un solo elemento:
//       "Pila = [ Sentinella P ] [ <elemento1> (sia cima che base) ]

//    b) Se la pila ha più elementi:
//       "Pila = [ Sentinella P ] [ <elemento1> (cima), <elemento2>, ..., <elementoN> (base) ]"

void stampaPila(pila P) {
    printf("Pila = [ Sentinella P ]");
    if (isPilaVuota(P)) {
        printf("\n\n");
        return;
    }

    printf("  [ ");
    posizione cur = primoNodo(P);
    while (!fineLista(cur, P)) {
        int elem = leggiElemInPosizione(cur, P);   // ✅ usa la funzione pubblica della pila
        bool isTop  = (cur == primoNodo(P));
        bool isBase = (cur == ultimoNodo(P));

        if (isTop && isBase) {
            printf("%d (sia cima che base) ]", elem);
        } else if (isTop) {
            printf("%d (cima) ]", elem);
        } else if (isBase) {
            printf("%d (base) ]", elem);
        } else {
            printf("%d ]", elem);
        }

        cur = successivoDi(cur);
        if (!fineLista(cur, P)) printf("  [ ");
    }
    printf("\n\n");
}




// Facciamo una demo per le nostre geniali elucubrazioni:
int main() {
    pila P;
    creaPilaVuota(&P);

    // Stampiamo lo stato iniziale:
    stampaPila(P);
    
    // Riempiamo con un ciclo una pila di 10elementi da 10 a 100, e a ogni iterazione la stampiamo evidenziando la cima e la base:
    for (int i = 1; i <= 10; i++) {
        pushaInCima(i * 10, P);
        stampaPila(P);
    }

    return 0;
}