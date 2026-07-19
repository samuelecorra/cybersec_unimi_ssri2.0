/* Secondo approccio: pre-calcola le 2N-1 somme per verso, O(N^2). */

#include <stdio.h>

#define N 3

int main(void) {
    const int originale[N][N] = {{1,2,3}, {4,5,6}, {7,8,9}};
    int risultato[N][N] = {0};
    int discendenti[2 * N - 1] = {0}; // indice (riga-colonna)+(N-1)
    int ascendenti[2 * N - 1] = {0};  // indice riga+colonna

    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            discendenti[riga - colonna + N - 1] += originale[riga][colonna];
            ascendenti[riga + colonna] += originale[riga][colonna];
        }
    }

    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            // La cella appartiene a entrambe le somme e va rimossa due volte.
            risultato[riga][colonna] = discendenti[riga - colonna + N - 1]
                                      + ascendenti[riga + colonna]
                                      - 2 * originale[riga][colonna];
            printf("%d\t", risultato[riga][colonna]);
        }
        putchar('\n');
    }
    return 0;
}
