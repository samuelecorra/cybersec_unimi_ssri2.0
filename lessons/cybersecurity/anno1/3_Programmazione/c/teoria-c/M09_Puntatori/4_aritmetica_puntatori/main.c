
// ARITMETICA DEI PUNTATORI:

// Possiamo fare operazioni aritmetiche sui puntatori, come sommare o sottrarre
// un intero al puntatore stesso. In questo modo, possiamo spostarci avanti o indietro
// nell'array a cui il puntatore punta.

#include <stdio.h>
#include <windows.h>
#include <stdlib.h>

int main() {

    SetConsoleOutputCP(CP_UTF8);

    int voti[] = { 30, 25, 23, 31, 27 };

    printf("Indirizzo del primo voto con indice: 0x%p\n", &voti[0]);
    printf("Indirizzo del primo voto con puntatore: 0x%p\n", voti);

    printf("Indirizzo del secondo voto con indice: 0x%p\n", &voti[1]);
    printf("Indirizzo del secondo voto con puntatore + 1: 0x%p\n", voti + 1);

    printf("Indirizzo del terzo voto con indice: 0x%p\n", &voti[2]);
    printf("Indirizzo del terzo voto con puntatore + 2: 0x%p\n", voti + 2);

    // Notiamo che quel +1 ci fa saltare di 4 byte, che è la dimensione di un int
    // sulla nostra architettura. Se avessimo un array di char, il +1 ci farebbe saltare
    // di 1 byte, che è la dimensione di un char.

    // Possiamo anche dereferenziare il puntatore con l'offset per ottenere il valore
    // dell'elemento corrispondente:

    printf("Valore del primo voto con indice: %d\n", voti[0]);
    printf("Valore del primo voto con puntatore: %d\n", *(voti));

    printf("Valore del secondo voto con indice: %d\n", voti[1]);
    printf("Valore del secondo voto con puntatore + 1: %d\n", *(voti + 1));
    // ATTENZIONISSIMA: LE PARENTESI TONDE SONO OBBLIGATORIE, ALTRIMENTI IL COMPILATORE
    // ESEGUE PRIMA LA DEREFERENZIAZIONE E POI L'ADDIZIONE, IL CHE NON HA SENSO!

    printf("Valore del terzo voto con indice: %d\n", voti[2]);
    printf("Valore del terzo voto con puntatore + 2: %d\n", *(voti + 2));
    // E così via...

    printf("Valore del quarto voto con indice: %d\n", voti[3]);
    printf("Valore del quarto voto con puntatore + 3: %d\n", *(voti + 3));

    printf("Valore del quinto voto con indice: %d\n", voti[4]);
    printf("Valore del quinto voto con puntatore + 4: %d\n", *(voti + 4));


// RIASSUMIAMO:

    // determinare l'indirizzo si può fare in due maniere equivalenti:

    // 1. &array[i]
    // 2. array + i // solo array nel caso di puntatore al primo elemento

    // determinare il valore si può fare in due maniere equivalenti:

    // 1. array[i]
    // 2. *(array + i) // solo array nel caso di puntatore al primo elemento
    // con le parentesi tonde obbligatorie

    printf("Premi invio per continuare...\n");
    getchar(); // pausa, aspetta invio

    // Possiamo anche usare un puntatore esplicito:
    int *pVoti = voti; // pVoti punta al primo elemento di voti

    for (int i = 0; i < 5; i++) {
        printf("Il %d° voto è: %d\n", i + 1, *(pVoti + i));
    }

    // E chiaramente dall'ultima osservazione discerne una diretta conseguenza:
    // Possiamo puntare esplicitamente a un elemento qualsiasi dell'array

    int *pTerzoVoto = &voti[2]; // puntatore al terzo elemento (indice 2)
    printf("Il terzo voto è: %d\n", *pTerzoVoto);
    printf("L'indirizzo del terzo voto è: 0x%p\n", pTerzoVoto);

    printf("Il quarto voto è: %d\n", *(pTerzoVoto + 1));
    printf("L'indirizzo del quarto voto è: 0x%p\n", pTerzoVoto + 1);

    // E così ci torna utile anche la sottrazione di puntatori:
    printf("Il secondo voto è: %d\n", *(pTerzoVoto - 1));
    printf("L'indirizzo del secondo voto è: 0x%p\n", pTerzoVoto - 1);

    printf("Premi invio per uscire...\n");
    getchar(); // pausa, aspetta invio

    
    // WARNING FINALE: QUANDO ACCEDIAMO TRAMITE INDICE, IL COMPILATORE SEGNALA ERRORE
    // SE PROVIAMO A SPINGERCI OLTRE LA FINE DELL'ARRAY. INVECE, SE USIAMO
    // L'ARITMETICA DEI PUNTATORI, IL COMPILATORE NON SEGNALA NULLA, E
    // POTREMMO ACCEDERE A MEMORIA NON NOSTRA, CON RISCHIO DI CORRUPZIONE
    // DI MEMORIA E COMPORTAMENTI IMPREVEDIBILI DEL PROGRAMMA!

    return 0;
}