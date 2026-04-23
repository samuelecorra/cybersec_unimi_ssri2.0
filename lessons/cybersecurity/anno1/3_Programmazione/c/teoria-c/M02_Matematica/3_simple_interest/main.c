// Realizzare un programma che calcoli l'interesse semplice su un capitale
// iniziale C, ad un tasso di interesse annuo i, per un numero di anni n, non è
// poi così difficile. La formula è la seguente:

// I = C * i * n    dove:

// I = interesse semplice, ovvero l'ammontare degli interessi maturati
// C = capitale iniziale, ovvero la somma di denaro su cui si calcolano gli interessi
// i = tasso di interesse annuo, espresso in forma decimale (ad esempio 5% = 0.05)
// n = numero di anni, ovvero il periodo di tempo per cui si calcolano gli interessi

// Se poi vogliamo calcolare il montante M, ovvero la somma totale che si
// ottiene alla fine del periodo, dobbiamo sommare il capitale iniziale C
// all'interesse semplice I:

// M = C + I

#include <stdio.h>
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <math.h>               // direttiva per funzioni matematiche

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    double capitaleIniziale; // C
    double tassoInteresse;   // i
    int numeroAnni;         // n

    // Chiediamo all'utente il capitale iniziale:
    printf("Inserisci il capitale iniziale (C): ");
    scanf("%lf", &capitaleIniziale); // %lf per double

    // Chiediamo all'utente il tasso di interesse annuo:
    printf("Inserisci il tasso di interesse annuo in %% (ad esempio 5 per 5%%): ");
    scanf("%lf", &tassoInteresse); // %lf per double
    tassoInteresse /= 100.0; // Convertiamo la percentuale in forma decimale

    // Chiediamo all'utente il numero di anni:
    printf("Inserisci il numero di anni (n): ");
    scanf("%d", &numeroAnni); // %d per int

    // Calcoliamo l'interesse semplice:
    double interesseSemplice = capitaleIniziale * tassoInteresse * numeroAnni;

    // Calcoliamo il montante:
    double montante = capitaleIniziale + interesseSemplice;

    // Mostriamo i risultati:
    printf("Interesse semplice (I): %.2f\n", interesseSemplice);
    printf("Montante (M): %.2f\n", montante);

    return 0;
}