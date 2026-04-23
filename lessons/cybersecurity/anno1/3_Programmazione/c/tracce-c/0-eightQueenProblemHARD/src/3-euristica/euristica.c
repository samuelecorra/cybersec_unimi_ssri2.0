#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define N 8

int euristica(const int stato[N]) {
    int costo = 0;

    for (int i = 0; i < N; i++) { // Implicitamente stiamo calcolando la combinazione semplice di due elementi
        for (int j = i + 1; j < N; j++) { // ma per tutte ed 8 in un colpo solo ed intuitivo

            if (stato[i] == stato[j]) // Stessa riga
                costo++;

            if (abs(stato[i] - stato[j]) == abs(i - j)) // Diagonale ↘️ o ↙️
                costo++;
        }
    }

    return costo; // 0 se soluzione altrimenti costo = numero di conflitti
}