/*
ARRAY COME PARAMETRI DI FUNZIONE

In un parametro di funzione, le dichiarazioni int valori[] e int *valori vengono
adattate allo stesso tipo puntatore. La funzione non riceve una copia dell'intero
array e non conosce automaticamente il numero dei suoi elementi.

Per questo si passa separatamente la lunghezza, normalmente con size_t. Dentro la
funzione non bisogna tentare sizeof(valori) / sizeof(valori[0]): sizeof(valori)
misurerebbe il puntatore, non l'array originale.

Gli elementi restano quelli dell'array del chiamante. Se il parametro non è const,
la funzione può modificarli. const int valori[] documenta e impone invece che la
funzione non modifichi gli elementi attraverso quel parametro.
*/

#include <stddef.h>
#include <stdio.h>

void stampaArray(const int valori[], size_t lunghezza) {
    for (size_t i = 0; i < lunghezza; i++) {
        printf("%d", valori[i]);
        if (i + 1U == lunghezza) {
            printf("\n");
        } else {
            printf(" ");
        }
    }
}

void raddoppiaArray(int valori[], size_t lunghezza) {
    for (size_t i = 0; i < lunghezza; i++) {
        valori[i] *= 2;
    }
}

long long sommaArray(const int valori[], size_t lunghezza) {
    long long somma = 0LL;
    for (size_t i = 0; i < lunghezza; i++) {
        somma += valori[i];
    }
    return somma;
}

int main(void) {
    int numeri[] = {2, 4, 6, 8};
    size_t lunghezza = sizeof(numeri) / sizeof(numeri[0]);

    printf("Array originale: ");
    stampaArray(numeri, lunghezza);
    printf("Somma: %lld\n", sommaArray(numeri, lunghezza));

    raddoppiaArray(numeri, lunghezza);
    printf("Dopo raddoppiaArray: ");
    stampaArray(numeri, lunghezza);

    return 0;
}
