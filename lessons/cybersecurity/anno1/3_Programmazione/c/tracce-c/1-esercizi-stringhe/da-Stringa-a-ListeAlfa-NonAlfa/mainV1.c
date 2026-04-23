
// Esercizio 1:
// Costruire il seguente programma:
// Viene fornita in ingresso una stringa di cui non è nota a priori la dimensione,
// e già qui si può intuire che il modo più facile per rispettare la richiesta
// è mediante user input:

// 1. Facciamo inserire all'utente la stringa (senza limite di caratteri)
// 2. Creiamo dinamicamente una lista di caratteri (array di char) che
//    contenga la stringa inserita dall'utente

// Dopodiché, creare due linked list SEPARATE:

// 1. La prima conterrà tutti i caratteri alfabetici della stringa
// 2. La seconda conterrà tutti i caratteri non alfabetici della stringa:
// spazi, numeri, caratteri speciali ecc ecc

// Alla fine, giusto per verificare che il programma funzioni,
// stampare a video le due liste separate.


// ====================================================================================


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX 100 // Impostiamo un massimo giusto per evitare problemi di buffer overflow

// Strutturiamo i nodi alfabetici e non come liste unidirezionali (next è puntatore a nodo):
typedef struct nodoCharAlfa {
    char alfaData;
    struct nodoCharAlfa* nextAlfaNode;
} nodoCharAlfa;

typedef struct nodoCharNonAlfa {
    char nonAlfaData;
    struct nodoCharNonAlfa* nextNonAlfaNode;
} nodoCharNonAlfa;


// ====================================================================================

// Realizziamo due funzioni distinte per aggiungere un nuovo nodo a ciascuna lista
// Cosa passeremo a queste funzioni?
// Dobbiamo passare il nodo precedente della lista e il carattere da aggiungere

// LA PARTE PIU' DI DIFFICILE COMPRENSIONE E' QUELLA DEL NODO PRECEDENTE:
// Innanzitutto, lo chiameremo head tanto per convenzione, ma in realtà
// non è detto che sia la testa della lista, bensì l'ultimo nodo della lista.
// Però ovviamente la prima volta che verrà invocata la funzione, head
// sarà NULL, perché la lista è vuota, ergo si riferirà davvero alla testa.
// Una volta chiarita la scelta della nomenclatura, dobbiamo capire
// che head è un puntatore a un nodo, e che se vogliamo modificare il nodo
// a cui si riferisce, dobbiamo farlo tramite il puntatore stesso, non
// tramite una copia del puntatore!

// Infatti in C tutti i parametri sono passati per valore di default: tocca a noi
// programmatori decidere se far passare un parametro per valore o per riferimento.
// In questo caso vogliamo farlo passare per indirizzo, altrimenti non potremo
// modificare il puntatore originale, e la lista non verrà mai popolata!
// Ma visto che head è un puntatore a un nodo, il suo indirizzo sarà un
// puntatore a un puntatore a un nodo, ovvero un doppio puntatore a un nodo.

// ====================================================================================


// 1. Funzione per aggiungere un nodo alla lista dei caratteri alfabetici

nodoCharAlfa* addAlphaNode(nodoCharAlfa** head, char data) {

    // Creiamo un nuovo nodo:
    // allochiamo dinamicamente esattamente lo spazio per un nodo alfabetico,
    // castiamo tale puntatore a (AlphaNode*), e lo assegniamo a newNode
    nodoCharAlfa* newNode = (nodoCharAlfa*)malloc(sizeof(nodoCharAlfa));
    newNode->alfaData = data;
    newNode->nextAlfaNode = NULL;   // In quanto ultimo aggiunto

    // Stiamo trattando un puntatore a puntatore a nodo:
    // per controllare se siamo all'inizio della lista, dereferenziamo
    // il doppio puntatore, e controlliamo se il puntatore a nodo è NULL:
    if (*head == NULL) {
        *head = newNode;    // Se la lista è vuota, il nuovo nodo diventa la testa
    } else {  // Altrimenti, vuol dire che la lista è già parzialmente popolata:
        // Scorriamo la lista fino alla fine con un puntatore temporaneo che inizializziamo proprio
        // con il valore del puntatore all'ultimo nodo della lista, ovvero *head.)
        nodoCharAlfa* curr = *head;
        while (curr->nextAlfaNode) {    // E' sottinteso finché curr->nextAlfaNode != NULL
            curr = curr->nextAlfaNode;  // Spostiamo il puntatore al nodo successivo
        }
        curr->nextAlfaNode = newNode;   // Aggiungiamo il nuovo nodo alla fine della lista
    }
    return newNode;  // Restituiamo il puntatore al nuovo nodo creato
}


// 2. Funzione duale per aggiungere un nodo alla lista dei caratteri non alfabetici
void addNonAlphaNode(nodoCharNonAlfa** head, char data) {

    nodoCharNonAlfa* newNode = (nodoCharNonAlfa*)malloc(sizeof(nodoCharNonAlfa));
    newNode->nonAlfaData = data;
    newNode->nextNonAlfaNode = NULL;
    if (*head == NULL) {
        *head = newNode;
    } else {
        nodoCharNonAlfa* curr = *head;
        while (curr->nextNonAlfaNode) {
            curr = curr->nextNonAlfaNode;
        }
        curr->nextNonAlfaNode = newNode;
    }
}


// ====================================================================================


// Ora possiamo procedere con il main

int main() {

    printf("Inserisci una stringa (max %d caratteri): ", MAX-1);
    char input[MAX];

    // Usiamo fgets per leggere la stringa, così da poter includere anche gli spazi
    if (fgets(input, sizeof(input), stdin) == NULL) {
        fprintf(stderr, "Errore nella lettura della stringa.\n");
        return 1;
    }
    // Individuiamo con strcspn la posizione del carattere di nuova linea '\n'
    // e lo sostituiamo con il terminatore.
    input[strcspn(input, "\n")] = '\0';

    printf("Stringa inserita: %s\n", input);

    // Ora che abbiamo la stringa, possiamo creare le due liste
    nodoCharAlfa* alphaHead = NULL;       // Testa della lista dei caratteri alfabetici
    nodoCharNonAlfa* nonAlphaHead = NULL; // Testa della lista dei caratteri non alfabetici

    // Scorriamo la stringa e popoliamo le due liste
    for (size_t i = 0; i < strlen(input); i++) {

        // Perché il cast a (unsigned char)?
        // Perché la funzione isalpha si aspetta un int che rappresenta un carattere,
        // ma il nostro input è di tipo char, che può essere signed o unsigned a seconda
        // dell'implementazione del compilatore. Se char è signed, e il carattere ha un valore
        // negativo (ad esempio caratteri con codici ASCII > 127), il comportamento di isalpha
        // potrebbe non essere quello atteso. Castando a unsigned char, ci assicuriamo che
        // il valore sia sempre positivo.
        if (isalpha((unsigned char)input[i])) {   // Controlliamo se il carattere è alfabetico
            // AMPERSAND PER PASSARE PER INDIRIZZO E PERMETTERE LA MODIFICA DEL PUNTATORE ORIGINALE
            addAlphaNode(&alphaHead, input[i]);   // Aggiungiamo alla lista dei caratteri alfabetici
        } else {
            addNonAlphaNode(&nonAlphaHead, input[i]); // Aggiungiamo alla lista dei caratteri non alfabetici
        }
    }

    // Ora stampiamo le due liste per verificare che tutto funzioni

    // NB: andrebbe bene anche senza variabili temporanee, ma così è più chiaro!
    printf("Caratteri alfabetici: ");
    nodoCharAlfa* tempAlpha = alphaHead;
    while (tempAlpha) {   // E' sottinteso finché tempAlpha != NULL
        printf("'%c' ", tempAlpha->alfaData);
        tempAlpha = tempAlpha->nextAlfaNode;
    }
    printf("\n");

    printf("Caratteri non alfabetici: ");
    nodoCharNonAlfa* tempNonAlpha = nonAlphaHead;
    while (tempNonAlpha) {
        if(tempNonAlpha->nonAlfaData == ' ')
            printf("'spc' ");
        else
        printf("'%c' ", tempNonAlpha->nonAlfaData);
        tempNonAlpha = tempNonAlpha->nextNonAlfaNode;
    }
    printf("\n");

    // Liberiamo la memoria allocata per le liste:
    // L'idea di base è di scorrere ogni lista e liberare ogni nodo uno per uno,
    // a partire dalla testa fino alla fine della lista, mediante un ciclo while che
    // trae ausilio da un puntatore temporaneo per non perdere il riferimento al nodo successivo
    // Tale puntatore temporaneo comunque punta allo stesso nodo della testa, quindi si
    // elimina il nodo originale, non una copia del nodo!
    while (alphaHead != NULL) {
        nodoCharAlfa* temp = alphaHead;  // Salviamo il nodo corrente
        alphaHead = alphaHead->nextAlfaNode; // Spostiamo la testa al nodo successivo
        free(temp); // Liberiamo il nodo salvato
    }
    while (nonAlphaHead != NULL) {
        nodoCharNonAlfa* temp = nonAlphaHead;
        nonAlphaHead = nonAlphaHead->nextNonAlfaNode;
        free(temp);
    }

    return 0;
}