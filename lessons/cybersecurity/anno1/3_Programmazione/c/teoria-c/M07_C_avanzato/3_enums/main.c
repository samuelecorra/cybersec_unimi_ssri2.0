/*
 * Un'enumerazione definisce costanti intere dotate di nomi significativi.
 * Se non si specificano valori, la prima costante vale 0 e le successive
 * aumentano di uno. È possibile scegliere un valore iniziale diverso.
 *
 * Le costanti non devono per forza avere valori distinti: non esiste quindi una
 * corrispondenza biunivoca garantita tra nomi e numeri. Anche un enum può essere
 * dichiarato in un blocco; qui è a livello di file per essere riusabile.
 */

#include <stdio.h>

enum GiornoDellaSettimana {
    LUNEDI = 1,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA
};

int main(void) {
    enum GiornoDellaSettimana oggi = MERCOLEDI;
    printf("Oggi è il giorno numero %d della settimana.\n", oggi);

    switch (oggi) {
        case LUNEDI:    puts("Inizio della settimana!"); break;
        case MARTEDI:   puts("Secondo giorno della settimana."); break;
        case MERCOLEDI: puts("Siamo a metà settimana!"); break;
        case GIOVEDI:   puts("Quasi venerdì..."); break;
        case VENERDI:   puts("Finalmente venerdì!"); break;
        case SABATO:    puts("È sabato, tempo di relax!"); break;
        case DOMENICA:  puts("Domenica: prepariamoci per la nuova settimana."); break;
        default:        puts("Valore non associato a un giorno valido."); break;
    }

    // Il C non impedisce di convertire un intero non valido nel tipo enum:
    enum GiornoDellaSettimana inputNonValidato = (enum GiornoDellaSettimana)99;
    if (inputNonValidato < LUNEDI || inputNonValidato > DOMENICA) {
        puts("Il valore 99 deve essere rifiutato dalla logica del programma.");
    }

    return 0;
}
