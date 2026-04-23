/* Ora finalmente diamo coerenza ai vettori, o array che dir si voglia! 

// UN ARRAY IN C E' UNA COLLEZIONE DI DIMENSIONE FISSATA DI ELEMENTI DEL MEDESIMO TIPO.

// SIMILE A UNA VARIABILE, MA CHE PUO' CONTENERE PIU' VALORI.

// IN MEMORIA, GLI ELEMENTI DI UN ARRAY SONO MEMORIZZATI IN POSIZIONI/CELLE CONTIGUE,
// E QUESTO PERMETTE L'ACCESSO INDEX-BASED AGLI ELEMENTI STESSI, OVVERO PERMETTE
// L'INDICIZZAZIONE DEGLI ELEMENTI.     */

#include <stdio.h>
#include <stdlib.h>

int main() {

    // DICHIARAZIONE DI UN ARRAY:
    // Sintassi:
    // tipo_elementi nome_array[dimensione_array];

    int numeri[5];  // solo dichiarazione, senza inizializzazione

    int numeri2[4] = {1, 2, 3, 4}; // dichiarazione e inizializzazione

    int numeri3[] = {0}; // dichiarazione e inizializzazione,
    // con dimensione dedotta dal numero di elementi

    int numeri4[3] = {0}; // tutti gli elementi inizializzati all'unico valore a destra
    
    int numeriConFor[5]; // ci serve dopo


    // INIZIALIZZAZIONE DEGLI ELEMENTI DELL'ARRAY
    // Può essere fatta manualmente, elemento per elemento, con indice:
    numeri[0] = 10;
    numeri[1] = 20;
    numeri[2] = 30;
    numeri[3] = 40;
    numeri[4] = 50;

    // Oppure con un ciclo for:
    for (int i = 0; i < 5; i++) {
        numeriConFor[i] = (i + 1) * 10;
    }


//=====================================================================================


    // Con la stessa logica possiamo anche stampare gli elementi:

    // 1. Per indici specifici:

    printf("Elemento 0: %d\n", numeri[0]);
    printf("Elemento 1: %d\n", numeri[1]);
    printf("Elemento 2: %d\n", numeri[2]);
    printf("Elemento 3: %d\n", numeri[3]);
    printf("Elemento 4: %d\n", numeri[4]);
    printf("\n");


    // ACCESSO AGLI ELEMENTI DELL'ARRAY E STAMPA
    for (int i = 0; i < 5; i++) {
        printf("Elemento %d: %d\n", i, numeriConFor[i]);
    }

    return 0;
}