#include <stdlib.h>

#define N 8

/* Conta una volta ogni coppia di regine in conflitto. Le colonne sono distinte
 * per costruzione; restano da controllare righe e diagonali. */
int verifica(const int stato[N]) {
    int conflitti = 0;
    for (int prima = 0; prima < N; ++prima) {
        for (int seconda = prima + 1; seconda < N; ++seconda) {
            if (stato[prima] == stato[seconda]
                || abs(stato[prima] - stato[seconda]) == seconda - prima) {
                ++conflitti;
            }
        }
    }
    return conflitti;
}
