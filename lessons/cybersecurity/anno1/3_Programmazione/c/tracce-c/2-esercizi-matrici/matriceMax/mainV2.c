/* Versione VLA: la matrice risultante è fornita dal chiamante, senza malloc. */

#include <limits.h>
#include <stddef.h>
#include <stdio.h>

static void ricavaMassimi(size_t n, const int originale[n][n], int risultato[n][n]) {
    static const int direzioni[8][2] = {
        {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}
    };
    for (size_t riga = 0; riga < n; ++riga) {
        for (size_t colonna = 0; colonna < n; ++colonna) {
            int massimo = INT_MIN;
            for (size_t d = 0; d < 8; ++d) {
                long r = (long)riga + direzioni[d][0];
                long c = (long)colonna + direzioni[d][1];
                if (r >= 0 && c >= 0 && r < (long)n && c < (long)n
                    && originale[r][c] > massimo) massimo = originale[r][c];
            }
            risultato[riga][colonna] = massimo;
        }
    }
}

int main(void) {
    enum { N = 4 };
    const int originale[N][N] = {{4,5,6,7}, {8,2,4,5}, {6,7,8,1}, {2,8,4,6}};
    int risultato[N][N];
    ricavaMassimi(N, originale, risultato);

    for (size_t riga = 0; riga < N; ++riga) {
        for (size_t colonna = 0; colonna < N; ++colonna) printf("%d ", risultato[riga][colonna]);
        putchar('\n');
    }
    return 0;
}
