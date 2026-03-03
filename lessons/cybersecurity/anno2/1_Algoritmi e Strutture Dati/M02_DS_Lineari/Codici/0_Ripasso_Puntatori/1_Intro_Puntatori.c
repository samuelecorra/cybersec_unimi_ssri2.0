
/* Introduzione ai puntatori */

// Prima di iniziare a lavorare con le liste, è necessario fare un bel ripasso generale e approfondito sui puntatori in C.

// Il C è un linguaggio di programmazione di basso livello che permette di manipolare direttamente la memoria del 
// computer attraverso l'uso dei puntatori. 

// Un puntatore è una variabile che memorizza l'indirizzo di memoria di un'altra variabile. 
// Questo concetto è fondamentale per comprendere come funzionano le strutture dati dinamiche come le liste concatenate.

#include <stdio.h>
#include <stdlib.h>

int main() {
    int var = 42;               // una variabile intera
    int *ptr = &var;           // un puntatore che memorizza l'indirizzo di var

    printf("Valore di var: %d\n", var);               // stampa il valore di var
    printf("Indirizzo di var: %p\n", &var);    // stampa l'indirizzo di var
    printf("Valore di ptr: %p\n", ptr);        // stampa il valore di ptr (indirizzo di var)
    printf("Valore puntato da ptr: %d\n", *ptr);      // dereferenziazione del puntatore per ottenere il valore di var

    // Modifica del valore di var tramite il puntatore
    *ptr = 100;
    printf("Nuovo valore di var dopo modifica tramite ptr: %d\n", var);

    return 0;
}