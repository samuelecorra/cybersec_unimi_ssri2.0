/* Versione basata su matrici allocate come array di puntatori alle righe. */

#include <limits.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int **allocaMatrice(size_t n) {
    if (n == 0 || n > SIZE_MAX / sizeof(int)) return NULL;
    int **matrice = calloc(n, sizeof *matrice);
    if (matrice == NULL) return NULL;
    for (size_t riga = 0; riga < n; ++riga) {
        matrice[riga] = malloc(n * sizeof *matrice[riga]);
        if (matrice[riga] == NULL) {
            while (riga > 0) free(matrice[--riga]);
            free(matrice);
            return NULL;
        }
    }
    return matrice;
}

static void liberaMatrice(int **matrice, size_t n) {
    if (matrice == NULL) return;
    for (size_t riga = 0; riga < n; ++riga) free(matrice[riga]);
    free(matrice);
}

static int **trasformaMatrice(size_t n, int *const originale[]) {
    int **risultato = allocaMatrice(n);
    if (risultato == NULL) return NULL;

    for (size_t riga = 0; riga < n; ++riga) {
        for (size_t colonna = 0; colonna < n; ++colonna) {
            int massimo = INT_MIN;
            for (int deltaRiga = -1; deltaRiga <= 1; ++deltaRiga) {
                for (int deltaColonna = -1; deltaColonna <= 1; ++deltaColonna) {
                    if (deltaRiga == 0 && deltaColonna == 0) continue;
                    long r = (long)riga + deltaRiga;
                    long c = (long)colonna + deltaColonna;
                    if (r >= 0 && c >= 0 && r < (long)n && c < (long)n
                        && originale[r][c] > massimo) massimo = originale[r][c];
                }
            }
            risultato[riga][colonna] = massimo;
        }
    }
    return risultato;
}

static void stampaMatrice(int *const matrice[], size_t n) {
    for (size_t riga = 0; riga < n; ++riga) {
        for (size_t colonna = 0; colonna < n; ++colonna) printf("%d ", matrice[riga][colonna]);
        putchar('\n');
    }
}

int main(void) {
    enum { N = 4 };
    const int valori[N][N] = {{4,5,6,7}, {8,2,4,5}, {6,7,8,1}, {2,8,4,6}};
    int **originale = allocaMatrice(N);
    if (originale == NULL) return 1;
    for (size_t riga = 0; riga < N; ++riga)
        for (size_t colonna = 0; colonna < N; ++colonna)
            originale[riga][colonna] = valori[riga][colonna];

    int **risultato = trasformaMatrice(N, originale);
    if (risultato == NULL) {
        liberaMatrice(originale, N);
        return 1;
    }
    puts("Matrice originale:");
    stampaMatrice(originale, N);
    puts("Matrice dei massimi adiacenti:");
    stampaMatrice(risultato, N);
    liberaMatrice(originale, N);
    liberaMatrice(risultato, N);
    return 0;
}
