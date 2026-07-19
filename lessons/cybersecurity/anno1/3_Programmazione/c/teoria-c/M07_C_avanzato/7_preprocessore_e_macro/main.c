/*
 * Il preprocessore gestisce direttive come #include, #define e #if prima della
 * compilazione. Le macro sono sostituzioni testuali: non hanno controllo di tipo
 * e possono valutare più volte un argomento. Per la logica ordinaria, preferire
 * funzioni; usare le macro quando serve davvero generare testo o compilazione
 * condizionale. Le include guard dell'header evitano inclusioni multiple.
 */

#include "config.h"

#include <stdio.h>

static int quadrato(int valore) {
    return valore * valore;
}

int main(void) {
    int lato = 5;
    printf("Corso: %s\n", NOME_CORSO);
    printf("Voto massimo: %d\n", VOTO_MASSIMO);
    printf("Quadrato con macro: %d\n", QUADRATO(lato));
    printf("Quadrato con funzione: %d\n", quadrato(lato));

    // Non scrivere QUADRATO(lato++): la macro incrementerebbe lato due volte.
#if defined(DEBUG)
    puts("Build di debug");
#endif
    return 0;
}
