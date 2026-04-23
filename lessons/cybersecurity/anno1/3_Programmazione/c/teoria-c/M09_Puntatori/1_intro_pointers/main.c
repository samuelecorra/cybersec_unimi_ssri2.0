
// INIZIA L'INCUBO DEI PRINCIPIANTI ALLE PRESE CON IL LINGUAGGIO C:
// I PUNTATORI (POINTERS)

// Un puntatore è una variabile che contiene l'indirizzo di memoria di un'altra variabile.

// BENEFIT: ci consentono di non sprecare la memoria, poiché passiamo l'indirizzo
// di una struttura enorme piuttosto che copiare tutta la struttura.

// Quindi quando il programma deve comunicare con una funzione o comunque inviare
// dati, invece di copiare tutto il contenuto della variabile, può semplicemente
// passare il suo indirizzo di memoria, che è molto più piccolo e leggero.

#include <stdio.h>
#include <windows.h>

int main() {

    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows

    int età = 17;

    // Se vogliamo stampare in console il valore della variabile età,
    // possiamo farlo così:

    printf("Età: %d\n", età);



    // MA se volessimo stampare l'INDIRIZZO DI MEMORIA della variabile età?
    // Per farlo, usiamo l'operatore & (e commerciale) prima del nome della variabile
    // Ricordiamo che l'ampersand è l'address-of operator, cioè l'operatore
    // che restituisce l'indirizzo di memoria di una variabile. A questo punto
    // il format specifier %p (pointer) è quello giusto per stampare un indirizzo di 
    // memoria:

    printf("Indirizzo di memoria di età: 0x%p\n", &età);
    printf("//======================================================\n");

//=====================================================================================


    // DICHIARAZIONE DI UN PUNTATORE

    // SINTASSI GENERICA:

    // tipo_di_dato_puntato *nome_puntatore;

    // ESEMPIO:

    int *pEtà; // puntatore a intero (int)

    // INIZIALIZZAZIONE:

    pEtà = &età; // assegniamo al puntatore l'indirizzo di memoria della variabile età

    // Ora pEtà contiene l'indirizzo di memoria della variabile età.

    // Se ristampiamo i due valori, vediamo che l'indirizzo di memoria
    // è lo stesso:

    printf("Indirizzo di memoria di età: 0x%p\n", &età);
    printf("Valore di pEtà (indirizzo di età): 0x%p\n", pEtà);

    printf("Premi invio per uscire...\n");
    getchar(); // pausa, aspetta invio        

    return 0;
}