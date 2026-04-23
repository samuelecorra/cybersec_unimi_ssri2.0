
// In questa lezione daremo per scontato l'argomento degli array!

// Gli array sono strettamente collegati ai puntatori, infatti il nome di un array
// rappresenta l'indirizzo del primo elemento dell'array stesso.

// Per ora limitiamoci ad aprire un main e testare alcune nozioni che già dovremmo sapere:

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

int main() {

    SetConsoleOutputCP(CP_UTF8);

    // Dichiariamo e inizializziamo un array di interi:
    int voti[] = { 30, 25, 23, 31, 27};

    int sizeVoti = sizeof(voti) / sizeof(voti[0]);
    
    // Stampiamo gli indirizi di ciascun voto con l'ampersand:
    for (int i = 0; i < sizeVoti; i++) {
        printf("L'indirizzo del %d° voto è: 0x%p\n", i+1, &voti[i]);
    }

    // Noteremo che gli indirizzi differiscono sempre di 4, il che è logico perché
    // ogni int occupa 4byte sulla nostra architettura!

    // Se invece avessimo una collezione di lettere:
    char lettere[] = { 'D', 'u', 's', 't', 'i', 'n' };

    int sizeLettere = sizeof(lettere) / sizeof(lettere[0]);

    for (int i = 0; i < sizeLettere; i++) {
        printf("L'indirizzo della %da lettera è: 0x%p\n", i+1, &lettere[i]);
    }

    // Qui invece noteremo che differiscono sempre di 1, infatti i char occupano 1byte
    // ciascuno ergo una cella ciascuno!


// =====================================================================================


    // Fin qui nulla di nuovo...

    // Ora se tentassimo di stampare l'indirizzo di memoria dell'array vero e proprio...

    printf("L'indirizzo di voti è: 0x%p (con ampersand)\n", &voti);
    printf("L'indirizzo di voti è: 0x%p (senza ampersand, decay implicito)\n", voti);
    printf("Che guardacaso coincide con quello del primo elemento dei voti:\n");
    printf("L'indirizzo di voti[0] è: 0x%p\n", &voti[0]);


    // Ma allora possiamo dichiarare un array come un puntatore a intero?
    int *pVoti2 = (int[]) { 30, 25, 23, 31, 27 };
    // Sì, ma attenzione: bisogna castare l'inizializzatore come un array di interi
    // (int[]) altrimenti il compilatore si lamenta

    int sizeVoti2 = sizeof(pVoti2) / sizeof(pVoti2[0]);
    for (int i = 0; i < sizeVoti2; i++) {
        printf("Il %d° voto è: %d\n", i+1, pVoti2[i]);
    }

    int dimensioneVera = 5; // dobbiamo tenere traccia della dimensione in qualche modo
    printf("La dimensione dell'array pVoti2 è: %d\n", dimensioneVera);

    for (int i = 0; i < dimensioneVera; i++) {
        printf("L'indirizzo del %d° voto è: 0x%p\n", i+1, &pVoti2[i]);
    }
    // ATTENZIONE: sizeof(pVoti2) NON FUNZIONA COME CI ASPETTIAMO PERCHE'
    // pVoti2 è un puntatore a intero, e quindi sizeof(pVoti2) restituisce
    // la dimensione di un puntatore (8byte su architettura a 64bit), e non
    // la dimensione dell'array a cui punta!
    // Quindi otteniamo 8 / 4 = 2, che non è il numero di elementi
    // nell'array (5)!

    // Quindi se vogliamo usare un puntatore per scorrere un array, dobbiamo
    // tenere traccia della sua dimensione in qualche modo esternamente!
}

// QUINDI:

// 1. il nome di un array decade implicitamente nell'indirizzo del primo elemento
//    (decay implicito)

// 2. l'operatore & (ampersand) applicato a un array restituisce l'indirizzo
//    dell'intero array (che coincide con l'indirizzo del primo elemento), ma
// ATTENZIONE I TIPI SONO DIVERSI: 

//   - &voti è di tipo "puntatore a array di 5 interi" (int (*)[5])
//   - voti è di tipo "puntatore a intero" (int *)
//   - &voti[0] è di tipo "puntatore a intero"

// MA ALLORA POTREMO SCORRERE L'ARRAY USANDO UN PUNTATORE? CERTO, MA BISOGNA
// INTRODURRE UN ALTRO CONCETTO: L'ARITMETICA DEI PUNTATORI!