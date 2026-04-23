#include <stdio.h>
#include <windows.h>
#include <stdbool.h>

int main() {

    SetConsoleOutputCP(CP_UTF8);

    float prezzoBiglietto = 10.00;
    bool èStudente = false;
    bool èSocioDelCinema = true;

    // Se un utente è studente, ottiene uno sconto del 10% sul biglietto.
    // Se un utente è socio del cinema, ottiene uno sconto del 20% sul biglietto.
    // Se un utente è sia studente che socio del cinema, ottiene uno sconto 
    // del (10+20) = 30% sul biglietto.

    if (èStudente) {

        if (èSocioDelCinema) {

            printf("Sei uno studente e un socio del cinema,"
                   " ergo ottieni uno sconto del 30%% sul biglietto!\n");
            prezzoBiglietto *= 0.7; // applica sconto del 30%

        } else {
            printf("Sei solo uno studente, ergo ottieni uno sconto del 10%% sul biglietto!\n");
            prezzoBiglietto *= 0.9; // applica sconto del 10%
        }
        
    } else {
        if (èSocioDelCinema) {
            printf("Sei solo un socio del cinema, ergo ottieni uno sconto del 20%% sul biglietto!\n");
            prezzoBiglietto *= 0.8; // applica sconto del 20%
        } else {
            printf("Non sei né studente né socio del cinema, "
                   "pertanto non ottieni alcuno sconto sul biglietto.\n");
        }
    }

    printf("Il prezzo finale del biglietto è: %.2f\n", prezzoBiglietto);

    return 0;

}

// Vedremo poi che possiamo migliorare questo codice usando gli operatori logici
// (&&, ||, !) per combinare le condizioni in un unico if-else if-else, invece di
// usare if annidati (nested ifs). In ogni caso è buona prassi evitare di annidare
// troppi if, perché rende il codice meno leggibile. MA CIO' NON TOGLIE CHE NON
// FUNZIONI PERFETTAMENTE BENE! ANZI A VOLTE E' L'UNICO MODO PER FARE CIO' CHE
// VOGLIAMO FARE!