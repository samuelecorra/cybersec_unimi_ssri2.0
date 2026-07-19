/* Primo approccio: per ciascuna cella scandisce l'intera matrice, O(N^4). */

#include <stdio.h>

#define N 3

int main(void) {
    const int originale[N][N] = {{1,2,3}, {4,5,6}, {7,8,9}};
    int risultato[N][N] = {0};

    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            for (int altraRiga = 0; altraRiga < N; ++altraRiga) {
                for (int altraColonna = 0; altraColonna < N; ++altraColonna) {
                    int stessaCella = altraRiga == riga && altraColonna == colonna;
                    int stessaDiscendente = altraRiga - altraColonna == riga - colonna;
                    int stessaAscendente = altraRiga + altraColonna == riga + colonna;
                    if (!stessaCella && (stessaDiscendente || stessaAscendente)) {
                        risultato[riga][colonna] += originale[altraRiga][altraColonna];
                    }
                }
            }
        }
    }

    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) printf("%d\t", risultato[riga][colonna]);
        putchar('\n');
    }
    return 0;
}
