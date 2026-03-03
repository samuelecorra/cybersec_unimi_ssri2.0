#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct _cella {
    int elem;
    struct _cella *next, *prev;
} cella;

typedef cella* posizione;
typedef cella* lista;


// ========== Utilities ==========

void creaListaVuota(lista *L) {
    *L = malloc(sizeof(cella));
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

// Se leggiamo la sentinella, dobbiamo notificarlo
int leggiElemLista(posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Attenzione: sentinella raggiunta!\n");
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

void cancElemLista(posizione p) {
    p->prev->next = p->next;
    p->next->prev = p->prev;
    free(p);
}


lista rangoIterativo(lista L) {
    lista R;
    creaListaVuota(&R);

    posizione p = ultimoNodo(L);
    int acc = leggiElemLista(p, L);

    insElemInListaPrimaDellaPosizionePassata(acc, primoNodo(R));  // inserisco il rango dell'ultimo elemento
    p = precedenteDi(p);
    while (!fineLista(p, L)) {
        acc += leggiElemLista(p, L);
        insElemInListaPrimaDellaPosizionePassata(acc, primoNodo(R));  // inserisco il rango corrente
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

    cella *ultimoEl = L;                      // all’inizio l’ultimo è la sentinella

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
            insElemInListaPrimaDellaPosizionePassata(rango, R);  // inserisco in coda
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
            insElemInListaPrimaDellaPosizionePassata(rango, R);  // inserisco in coda
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
