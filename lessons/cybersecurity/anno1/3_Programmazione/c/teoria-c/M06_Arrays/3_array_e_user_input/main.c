// Si noti che come con le variabili classiche. accedere a elementi di un array
// non inizializzato porta a comportamenti indefiniti, così come l'accesso
// a elementi fuori dai limiti dell'array!

#include <stdio.h>

int main() {
    int arr[5];

    // Stampiamo senza inizializzare:
    for (int i = 0; i < 5; i++) {
        printf("arr[%d] = %d\n", i, arr[i]);
    }

    /* A me è uscito:
    arr[0] = 2006608864;
    arr[1] = 484;
    arr[2] = 0;
    arr[3] = 1;
    arr[4] = 0; // ovvero valori spazzatura provenienti da altri programmi!
    
    // Ergo l'inizializzazione di un array ci riserva lo spazio necessario per
    // eseguire accessi sicuri!

    // NOTA: se l'array fosse stato dichiarato statico (static int arr[5];)
    // tutti gli elementi sarebbero stati inizializzati a 0 automaticamente!
    // Ma di questo tratteremo più avanti!

    // NOTA2: se l'array fosse stato dichiarato globale (fuori da ogni funzione)
    // tutti gli elementi sarebbero stati inizializzati a 0 automaticamente!        */


// ====================================================================================

    
    // NOTA3: la parziale inizializzazione di un array implica che il compilatore
    // inizializza a 0 tutti gli elementi non inizializzati, ergo l'inizializzazione
    // parziale è sicura!

    int arr2[5] = {1, 2}; // inizializzo solo i primi due elementi

    // Stampiamo:
    for (int i = 0; i < 5; i++) {
        printf("arr2[%d] = %d\n", i, arr2[i]);
    }
        // Otteniamo:
        // arr2[0] = 1
        // arr2[1] = 2
        // arr2[2] = 0
        // arr2[3] = 0
        // arr2[4] = 0          // TUTTO OK!


// ====================================================================================


    // Supponiamo ora che sia l'utente a decidere come inizializzare l'array sul momento:

    int numeri[5];

    // Versione senza ausilio del ciclo:

    printf("\n\nInserisci 5 numeri interi:\n");
    printf("Numero 1: ");
    scanf("%d", &numeri[0]);
    getchar(); // per consumare il newline rimasto nel buffer
    printf("Numero 2: ");
    scanf("%d", &numeri[1]);
    getchar();
    printf("Numero 3: ");
    scanf("%d", &numeri[2]);
    getchar();
    printf("Numero 4: ");
    scanf("%d", &numeri[3]);
    getchar();
    printf("Numero 5: ");
    scanf("%d", &numeri[4]);
    getchar();

    // Stampiamo:
    for (int i = 0; i < 5; i++) {
        printf("numeri[%d] = %d\n", i, numeri[i]);
    }

    // Eccessivamente verbosa la scorsa versione, giusto?
    // Bene, il ciclo for ci viene in aiuto:

    // Versione con ausilio del ciclo:
    printf("\n\nInserisci 5 numeri interi:\n");

    int dimensione = sizeof(numeri) / sizeof(numeri[0]); // calcolo la dimensione dell'array
    
    for (int i = 0; i < dimensione; i++) {
        printf("Numero %d: ", i + 1);
        scanf("%d", &numeri[i]);
        getchar(); // per consumare il newline rimasto nel buffer
    }

    // Stampiamo:
    for (int i = 0; i < dimensione; i++) {
        printf("numeri[%d] = %d\n", i, numeri[i]);
    }

    // COMPUTAZIONALMENTE EFFICIENTE!

    
    return 0;
}