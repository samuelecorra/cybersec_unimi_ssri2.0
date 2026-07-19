/* Versione compatta. Centro: 8 vicini; bordo non angolare: 5; angolo: 3. */

#include <stdio.h>

#define N 4

int main(void) {
    int originale[N][N];
    double medie[N][N];

    puts("Inserisci i valori della matrice:");
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            printf("Elemento [%d][%d]: ", riga, colonna);
            if (scanf("%d", &originale[riga][colonna]) != 1) {
                fputs("Valore non valido.\n", stderr);
                return 1;
            }
        }
    }

    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            long long somma = 0;
            int quantità = 0;
            for (int r = riga - 1; r <= riga + 1; ++r) {
                for (int c = colonna - 1; c <= colonna + 1; ++c) {
                    if (r >= 0 && r < N && c >= 0 && c < N
                        && (r != riga || c != colonna)) {
                        somma += originale[r][c];
                        ++quantità;
                    }
                }
            }
            medie[riga][colonna] = (double)somma / (double)quantità;
        }
    }

    puts("Matrice originale e matrice delle medie:");
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) printf("%d ", originale[riga][colonna]);
        fputs("   ", stdout);
        for (int colonna = 0; colonna < N; ++colonna) printf("%.2f ", medie[riga][colonna]);
        putchar('\n');
    }
    return 0;
}
