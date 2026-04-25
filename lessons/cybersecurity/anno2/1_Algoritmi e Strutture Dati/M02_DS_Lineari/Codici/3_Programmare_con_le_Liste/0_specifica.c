#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct _cella {
    int elem;
    struct _cella *next, *prev;
} cella;

// Abbiamo due opzioni se vogliamo passare by reference i nodi/liste:

// 1. Aliasiamo i puntatori a cella: per modificare il contenuto dei NODI basta passare by value
// (il puntatore è già un riferimento). Solo quando dobbiamo modificare il puntatore
// stesso nel chiamante (es. creaListaVuota) serve un livello in più: lista *L.
typedef cella* posizione; // sinonimo di nodo
typedef cella* lista;
// 2. Aliasiamo i nodi NON come puntatori, e useremo l'& nelle varie funzioni per passare by reference.
// Però, in questo caso, se dimentichiamo anche un solo &, rischiamo di passare per valore!


// ========== Utilities ==========

void creaListaVuota(lista *L) {
    *L = malloc(sizeof(cella)); // alloco spazio per la sentinella assegnandolo al puntatore alla lista
    if (!*L) {
        perror("malloc"); // gestione dell'errore di allocazione
        exit(1);
    }
    (*L)->next = *L; // Se va a buon fine l'allocazione, inizializzo i campi della sentinella
    (*L)->prev = *L;
}

// D'ora in avanti non dovremo più passare per riferimento!
bool isListaVuota(lista L) {
    return L->next == L;
}

posizione primoNodo(lista L)   { return L->next; }
posizione ultimoNodo(lista L)  { return L->prev; }

posizione successivoDi(posizione p) { return p->next; }
posizione precedenteDi(posizione p) { return p->prev; }


bool fineLista(posizione p, lista L) { return p == L; }

// Se leggiamo la sentinella, dobbiamo notificarlo
int leggiElemLista(posizione p, lista L) {
    if (fineLista(p, L)) { // fineLista ritorna bool quindi basta e avanza come condizione
        fprintf(stderr, "Attenzione: sentinella raggiunta!\n");
    }
    return p->elem; // l'arrow operator è più comodo di dereferenziare e poi accedere al campo (*p).elem è più verboso di p->elem
}

void sovrascriviElemLista(int x, posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Attenzione: scrittura sulla sentinella non consentita!\n");
        exit(1);
    }
    p->elem = x;
}

void insElemInListaPrimaDi(int x, posizione p) {
    posizione n = malloc(sizeof(cella)); // creo un nodo
    if (!n) { perror("malloc"); exit(1); }
    n->elem = x;

    // Come già visto nella lezione di teoria M02_DS_Lineari\UD1\L1\L1_Liste.md,
    // ci sono in tutto 4 re-link dei puntatori da aggiornare per inserire n prima di p:
    n->next = p;
    n->prev = p->prev;

    p->prev->next = n;
    p->prev = n;
}

void cancElemLista(posizione p) {
    // Stavolta è più easy: solo 2 re-link, poi liberiamo la memoria della cella cancellata.
    p->prev->next = p->next;
    p->next->prev = p->prev;
    free(p);
}

// -------------------------------------------------------------------------------------------

lista rangoIterativo(lista L) {
    lista R;
    creaListaVuota(&R);

    posizione p = ultimoNodo(L);
    int acc = leggiElemLista(p, L);

    insElemInListaPrimaDi(acc, primoNodo(R));  // inserisco il rango dell'ultimo elemento
    p = precedenteDi(p);
    while (!fineLista(p, L)) {
        acc += leggiElemLista(p, L);
        insElemInListaPrimaDi(acc, primoNodo(R));  // inserisco il rango corrente
        p = precedenteDi(p);
    }

    return R;
}

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
    // oppure se non avessimo voluto usare l'ampersand, avremmo potuto fare:
    lista *indirizzoLista = &L;
    creaListaVuota(indirizzoLista); // Ma capite bene che è molto più snello usare & direttamente!

    cella *ultimoEl = L; // all’inizio l’ultimo è la sentinella

    for (int i = 9; i >= 1; --i) {
        cella *new = malloc(sizeof(cella));
        new->elem = i;

        new->next = L;                    // inserimento in coda
        new->prev = ultimoEl;

        ultimoEl->next = new;
        L->prev = new;

        ultimoEl = new;
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
            insElemInListaPrimaDi(rango, primoNodo(R));  // inserisco in coda
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
            insElemInListaPrimaDi(rango, primoNodo(R));  // inserisco in coda
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