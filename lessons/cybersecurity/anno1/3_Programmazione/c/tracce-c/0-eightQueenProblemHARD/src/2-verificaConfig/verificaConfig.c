#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define N 8

// Potremmo anche solo farci ritornare l'euristica, ma per chiarezza espandiamo una funzione a parte...
int verifica(const int stato[N]) {
    int conflitti = 0;

    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {

            // Conflitto su stessa riga
            if (stato[i] == stato[j])
                conflitti++;

            // Conflitto su diagonale ↘️ o ↙️
            if (abs(stato[i] - stato[j]) == abs(i - j))
                conflitti++;
        }
    }

    return conflitti;
}