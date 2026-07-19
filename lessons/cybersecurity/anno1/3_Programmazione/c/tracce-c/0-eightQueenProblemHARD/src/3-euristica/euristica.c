#include <stdlib.h>

#define N 8

/*
 * Il costo è il numero di coppie in conflitto, non una distanza minima in mosse
 * dalla soluzione. Con N=8 il valore è compreso tra 0 e C(8,2)=28.
 */
int euristica(const int stato[N]) {
    int costo = 0;
    for (int prima = 0; prima < N; ++prima) {
        for (int seconda = prima + 1; seconda < N; ++seconda) {
            if (stato[prima] == stato[seconda]
                || abs(stato[prima] - stato[seconda]) == seconda - prima) {
                ++costo;
            }
        }
    }
    return costo;
}
