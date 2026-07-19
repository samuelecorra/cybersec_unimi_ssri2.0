/* Versione didattica con funzioni separate e dimensione fissata a compile time. */

#include <stdio.h>

#define N 3

static void clonaMatrice(double originale[N][N], double copia[N][N]) {
    for (int riga = 0; riga < N; ++riga)
        for (int colonna = 0; colonna < N; ++colonna)
            copia[riga][colonna] = originale[riga][colonna];
}

static void calcolaMedie(double originale[N][N], double medie[N][N]) {
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            double somma = 0.0;
            int quantità = 0;
            for (int deltaRiga = -1; deltaRiga <= 1; ++deltaRiga) {
                for (int deltaColonna = -1; deltaColonna <= 1; ++deltaColonna) {
                    if (deltaRiga == 0 && deltaColonna == 0) continue;
                    int r = riga + deltaRiga;
                    int c = colonna + deltaColonna;
                    if (r >= 0 && r < N && c >= 0 && c < N) {
                        somma += originale[r][c];
                        ++quantità;
                    }
                }
            }
            medie[riga][colonna] = somma / (double)quantità;
        }
    }
}

static void stampaMatrice(double matrice[N][N]) {
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) printf("%7.2f ", matrice[riga][colonna]);
        putchar('\n');
    }
}

int main(void) {
    double originale[N][N]; // durata automatica, non "statica"
    double copia[N][N];
    double medie[N][N];

    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            printf("Valore [%d][%d]: ", riga, colonna);
            if (scanf("%lf", &originale[riga][colonna]) != 1) {
                fputs("Valore non valido.\n", stderr);
                return 1;
            }
        }
    }

    clonaMatrice(originale, copia);
    calcolaMedie(copia, medie);
    puts("Matrice originale:");
    stampaMatrice(originale);
    puts("Matrice delle medie degli otto adiacenti possibili:");
    stampaMatrice(medie);
    return 0;
}
