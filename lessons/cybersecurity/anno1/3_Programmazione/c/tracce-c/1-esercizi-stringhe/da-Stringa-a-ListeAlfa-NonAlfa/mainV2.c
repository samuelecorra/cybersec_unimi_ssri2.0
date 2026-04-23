#include <stdio.h>
#include <stdlib.h>
#include <ctype.h> // per poter usare isalpha() e simili
#include <string.h>

// ======================== STRUTTURE ================================================ //
typedef struct Nodo {
    char c;
    struct Nodo* next;
} Nodo;

typedef struct Lista {
    Nodo* head;
    Nodo* tail;
} Lista;

// ======================== FUNZIONI AUSILIARIE ====================================== //
static void inizializzaListaVuota(Lista* L) {
    L->head = NULL;
    L->tail = NULL;
}

/* Inserisce in coda in O(1), restituendo 0 in caso di fallimento e 1 in caso di successo */
static int insInCoda(Lista* L, char ch) {
    Nodo* n = (Nodo*)malloc(sizeof(Nodo));
    if (!n) return 0;                 // fallimento allocazione
    n->c = ch;
    n->next = NULL;
    if (!L->head) { // caso lista vuota
        L->head = L->tail = n; // sia primo che ultimo nodo sono n
    } else {
        L->tail->next = n; // l'ex-coda ora punta ad n
        L->tail = n; // n diventa la nuova coda
    }
    return 1;
}

static void stampaLista(const Lista* L) {
    for (const Nodo* curr = L->head; curr; curr = curr->next) {
        unsigned char uc = (unsigned char)curr->c; // casting per corretta visualizzazione
        if (uc == ' ')      printf("'spc' ");
        else if (uc == '\t') printf("'\\t' ");
        else if (uc < 32 || uc == 127) printf("[0x%02X] ", uc); // caratteri di controllo
        else                 printf("%c ", curr->c);
    }
    puts(""); // equivalente a printf("\n");
}

static void liberaLista(Lista* L) {
    Nodo* curr = L->head;
    while (curr) {
        Nodo* nx = curr->next;
        free(curr);
        curr = nx;
    }
    L->head = L->tail = NULL;
}

// ======================== FUNZIONE PRINCIPALE RICHIESTA =================================== //
static void separaStringa(const char* s, Lista* alfabetici, Lista* nonAlfabetici) {

    // Cicliamo sui caratteri che compongono la stringa
    for (const unsigned char* p = (const unsigned char*)s; *p; p++) {
        if (isalpha(*p)) {
            if (!insInCoda(alfabetici, (char)*p)) { // Inseriamo e al contempo controlliamo l'eventuale fallimento
                fprintf(stderr, "Memoria insufficiente.\n");
                return;
            }
        } else {
            if (!insInCoda(nonAlfabetici, (char)*p)) { // Inseriamo e al contempo controlliamo l'eventuale fallimento
                fprintf(stderr, "Memoria insufficiente.\n");
                return;
            }
        }
    }
}

// ======================== MAIN DI TEST ============================================== //
int main(void) {
    const char* input = "Ciao 123!";   // hardcoded, ma modificabile a piacere

    // In alternativa, per input dinamico da tastiera, si potrebbe usare:
    // char buf[1024];
    // fgets(buf, sizeof buf, stdin);
    // buf[strcspn(buf, "\n")] = '\0';
    // const char* input = buf;
    
    // NOTA: per lo scopo dell'esercizio, basta editare l'input hardcoded per
    // sincerarsi che funziona comunque a prescindere dalla lunghezza della stringa inputtata.

    Lista Lalpha, Lnon;

    // ESTREMA ATTENZIONE: BISOGNA SEMPRE PASSARE PER RIFERIMENTO CON AMPERSAND LE VARIE LISTE,
    // poiché tali strutture contengono puntatori che devono essere modificati all'interno della funzione.
    inizializzaListaVuota(&Lalpha);
    inizializzaListaVuota(&Lnon);
    // INVECE la stringa è già un puntatore al primo carattere, quindi non serve l'ampersand
    separaStringa(input, &Lalpha, &Lnon);

    printf("Lista 1 (alfabetici): ");
    stampaLista(&Lalpha);
    printf("Lista 2 (non alfabetici): ");
    stampaLista(&Lnon);

    liberaLista(&Lalpha);
    liberaLista(&Lnon);
    return 0;
}
