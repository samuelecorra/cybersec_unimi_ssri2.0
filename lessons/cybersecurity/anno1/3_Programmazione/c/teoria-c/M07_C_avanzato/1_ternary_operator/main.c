// Operatore condizionale (detto anche ternario)
//
// Sintassi:
//     condizione ? espressione_se_vera : espressione_se_falsa
//
// È un'espressione: produce un valore. È quindi adatto a scegliere tra due valori
// semplici; per rami lunghi o con molti effetti collaterali, if/else è più leggibile.

#include <stdbool.h>
#include <stdio.h>

int main(void) {
    int numero = 10;

    if (numero % 2 == 0) {
        printf("%d è pari\n", numero);
    } else {
        printf("%d è dispari\n", numero);
    }

    // Lo stesso criterio può selezionare direttamente il testo da stampare.
    printf("%d è %s\n", numero, numero % 2 == 0 ? "pari" : "dispari");

    int voto1 = 7;
    int voto2 = 5;
    float media = (float)(voto1 + voto2) / 2.0f;

    // Un confronto è già un'espressione booleana: "? true : false" è ridondante.
    bool èPromossa = media >= 6.0f;
    printf("%s La media è %.2f.\n",
           èPromossa ? "Promossa!" : "Non promossa.", media);

    int a = 5;
    int b = 10;
    int massimo = a > b ? a : b;
    printf("Il valore massimo è: %d\n", massimo);

    // L'operatore associa da destra; una catena resta però meno chiara di if/else.
    int voto = 27;
    const char *fascia = voto >= 28 ? "ottimo" : voto >= 24 ? "buono" : "da migliorare";
    printf("Valutazione: %s\n", fascia);

    return 0;
}
