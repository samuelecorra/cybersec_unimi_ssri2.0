/*
 * Le variabili automatiche di blocco nascono a ogni ingresso nel blocco.
 * Una variabile locale static conserva invece il valore tra chiamate e viene
 * inizializzata una sola volta. "static" a livello di file limita inoltre il
 * collegamento del nome a questa unità di traduzione.
 *
 * extern dichiara un oggetto definito altrove; nei programmi multifile la
 * dichiarazione condivisa va normalmente in un header e la definizione in un .c.
 */

#include <stdio.h>

static int chiamateTotali = 0; // durata statica e collegamento interno

static unsigned prossimoIdentificatore(void) {
    static unsigned successivo = 1; // conserva il valore tra le chiamate
    ++chiamateTotali;
    return successivo++;
}

int main(void) {
    printf("ID: %u\n", prossimoIdentificatore());
    printf("ID: %u\n", prossimoIdentificatore());
    printf("Chiamate totali: %d\n", chiamateTotali);

    /*
     * Una variabile statica non è memoria dinamica: non si libera con free().
     * Inoltre una funzione static non è richiamabile per nome da un altro .c.
     */
    return 0;
}
