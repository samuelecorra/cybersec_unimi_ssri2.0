#include <stdio.h>
#include <stdbool.h>

int main() {
    // Innanzitutto dichiariamo e filliamo l'array non-ordinato:
    int colori_bandiera[] = {
        0,  // verde
        1,  // bianco   // k ed i iniziali
        2,  // rosso
        1,  // bianco
        0,  // verde
        2,  // rosso
        1,  // bianco
        0,  // verde    // j iniziale
        2   // rosso
    };

    // Ora possiamo procedere con la dichiarazione dei tre cursori:
    int dimensione_array, i, j, k;

    i = k = 1;
    dimensione_array = sizeof(colori_bandiera) / sizeof(colori_bandiera[0]);
    printf("Dimensione array: %d\n", dimensione_array); // DEBUG SEMPRE UTILE (9)
    j = 7;

    // Come preannunciato, condizione di uscita è che i superi j:
    while (i < j) {
        switch(colori_bandiera[i]) {

            // Gli scambi dei valori sono classici scambi con variabile di appoggio temporanea

            case 0: {   // verde
                int temp = colori_bandiera[i];
                colori_bandiera[i] = colori_bandiera[k];
                colori_bandiera[k] = temp;
                k++;
                break;
            }
            case 1: {   // bianco
                i++;
                break;
            }
            case 2: {   // rosso
                int temp = colori_bandiera[i];
                colori_bandiera[i] = colori_bandiera[j];
                colori_bandiera[j] = temp;
                j--;
                break;
            }
        }} // Fine switch e fine while

    // Debuggiamo per verificare, converttendo i numeri in colori:
    for (int idx = 0; idx < dimensione_array; idx++) {
        switch(colori_bandiera[idx]) {
            case 0:
                printf("verde ");
                break;
            case 1:
                printf("bianco ");
                break;
            case 2:
                printf("rosso ");
                break;
        }
    }

    return 0;
}