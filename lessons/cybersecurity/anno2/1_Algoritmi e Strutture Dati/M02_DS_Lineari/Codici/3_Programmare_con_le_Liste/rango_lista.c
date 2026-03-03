
// Algoritmi e Strutture Dati - Modulo 2, Unità 1, Lezione 2: Programmare con le Liste

// Consegna: al fine di imparare a programmare con le liste, implementare una funzione
// che, data una lista di interi, ne calcoli il rango di ogni elemento, restituendo
// una nuova lista con i ranghi corrispondenti.

// Il rango è la sommatoria del valore dell'i-esimo elemento e di tutti i valori degli
// elementi che lo seguono fino all'n-esimo, ovvero quello finale.

// Esempio banale:

// L = [3, 1, 4, 2]
// Rango(L) = [10, 7, 6, 2]

// =====================================================================================

// Definiamo come in passato la struttura della lista bidirezionale concatenata:

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct _cella {
    int elem;
    struct _cella *next, *prev;
} cella;

typedef cella* posizione;   // posizione = puntatore a cella
typedef cella* lista;       // lista = puntatore alla sentinella

// ========== Utilities ==========
void creaListaVuota(lista *L) {           // passo l’indirizzo perché alloco dentro
    *L = malloc(sizeof(cella));
    (*L)->next = *L;
    (*L)->prev = *L;
}

bool isListaVuota(lista L) {
    return L->next == L; // basta questo (prev sarà uguale)
}   // in questo caso non serve passare per indirizzo perché non alloco nulla

posizione primoNodo(lista L)   { return L->next; }
posizione ultimoNodo(lista L)  { return L->prev; }

posizione successivoDi(posizione p) { return p->next; }   // ← NOTA: non serve lista qui 
// anche se nella specifica sintattica è presente, perché p->next è sufficiente a individuare 
// il successivo di una determinata lista!
posizione precedenteDi(posizione p) { return p->prev; }    // ← idem

bool fineLista(posizione p, lista L) { return p == L; } 
// se siamo alla sentinella, siamo alla fine


// ========== Operazioni sulla lista ==========

/* ==== 1) leggilista: (posizione, lista) -> tipoelem ==== */
/* pre: p != L (non leggere la sentinella) */
int leggiElemLista(posizione p, lista L) {
    (void)L;
    return p->elem;
}

/* ==== 2) scrivilista: (tipoelem, posizione, lista) -> lista ==== */
/* scrive nell'elemento in posizione p (pre: p != L) */
lista scriviLista(int x, posizione p, lista L) {
    p->elem = x;
    return L;   // per coerenza con la firma
}

/* ==== 3) inslista: (tipoelem, posizione, lista) -> lista ==== */
/* inserisce **prima di p**. Se p==L, inserisce in coda. */
lista insLista(int x, posizione p, lista L) {
    posizione n = malloc(sizeof(cella));
    if (!n) { perror("malloc"); exit(1); }
    n->elem = x;

    n->next = p;
    n->prev = p->prev;

    p->prev->next = n;
    p->prev = n;
    return L;
}

/* ==== 4) canclista: (posizione, lista) -> lista ==== */
/* cancella il nodo in posizione p (pre: p != L) */
lista cancLista(posizione p, lista L) {
    p->prev->next = p->next;
    p->next->prev = p->prev;
    free(p);
    return L;
}


// Calcoli dei ranghi:


// Iterativo
lista rangoIterativo(lista L) {
    lista R;
    creaListaVuota(&R);

    posizione p = ultimoNodo(L);
    int acc = leggiElemLista(p, L);

    insLista(acc, primoNodo(R), R);  // inserisco il rango dell'ultimo elemento
    p = precedenteDi(p);
    while (!fineLista(p, L)) {
        acc += leggiElemLista(p, L);
        insLista(acc, primoNodo(R), R);  // inserisco il rango corrente
        p = precedenteDi(p);
    }

    return R;
}


// Ricorsivo
int rangoRicorsivo(posizione p, lista L) {
    int acc = 0;

    if (fineLista(p, L)) {
        return 0;
    } else if (fineLista(successivoDi(p), L)) {
        acc = leggiElemLista(p, L);
    } else {
        acc = leggiElemLista(p, L) + rangoRicorsivo(successivoDi(p), L);
    }
    return acc;
}

// ========== Demo ==========
int main(void) {
    lista L;
    creaListaVuota(&L);

    cella *tail = L;                      // all’inizio l’ultimo è la sentinella

    for (int i = 9; i >= 1; --i) {
        cella *new = malloc(sizeof(cella));
        new->elem = i;

        new->next = L;                    // inserimento in coda
        new->prev = tail;

        tail->next = new;
        L->prev = new;

        tail = new;
    }

    // Stampa di debug: L { sentinella -> 9 -> ... -> 1 -> sentinella }
    printf("Lista originale L: { Sentinella -> ");
    for (posizione p = primoNodo(L); p != L; p = successivoDi(p)) {
        printf("%d -> ", p->elem);
    }
    printf("Sentinella }\n");

    printf("Digita la tua scelta e poi premi invio\n"
        "1 per il calcolo iterativo della lista dei ranghi;\n"
        "2 per il calcolo ricorsivo;\n"
        "Scelta: ");

    int choice;
    scanf("%d", &choice);
    getchar();  // Consuma il carattere di nuova linea rimasto nel buffer

    if (choice == 1) {
        lista R = rangoIterativo(L);

        // Stampa di debug: R { sentinella -> ... -> ... -> sentinella }
        printf("Iterativamente, Lista dei ranghi R: { Sentinella -> ");
        for (posizione p = primoNodo(R); p != R; p = successivoDi(p)) {
            printf("%d -> ", p->elem);
        }
        printf("Sentinella }\n");
        printf("Premi 2 per vedere anche l'algoritmo ricorsivo...\n");
        scanf("%d", &choice);
        getchar();

    } else if (choice == 2) {
        lista R;
        creaListaVuota(&R);

        // Calcolo i ranghi e li inserisco in R
        for (posizione p = primoNodo(L); !fineLista(p, L); p = successivoDi(p)) {
            int rango = rangoRicorsivo(p, L);
            insLista(rango, R, R);  // inserisco in coda
        }

        // Stampa di debug: R { sentinella -> ... -> ... -> sentinella }
        printf("Ricorsivamente, Lista dei ranghi R: { Sentinella -> ");
        for (posizione p = primoNodo(R); p != R; p = successivoDi(p)) {
            printf("%d -> ", p->elem);
        }
        printf("Sentinella }\n");
        printf("Premi un tasto per uscire...\n");
        getchar();
    }

    if (choice == 2) {
        lista R;
        creaListaVuota(&R);

        // Calcolo i ranghi e li inserisco in R
        for (posizione p = primoNodo(L); !fineLista(p, L); p = successivoDi(p)) {
            int rango = rangoRicorsivo(p, L);
            insLista(rango, R, R);  // inserisco in coda
        }

        // Stampa di debug: R { sentinella -> ... -> ... -> sentinella }
        printf("Ricorsivamente, Lista dei ranghi R: { Sentinella -> ");
        for (posizione p = primoNodo(R); p != R; p = successivoDi(p)) {
            printf("%d -> ", p->elem);
        }
        printf("Sentinella }\n");
        printf("Premi un tasto per uscire...\n");
        getchar();
    }
    
    return 0;
}
