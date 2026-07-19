/* Versione C17 portabile basata su un array di puntatori alle righe. */

#include <limits.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    unsigned char carattere;
    size_t frequenza;
} Risultato;

static Risultato elaboraMatrice(size_t n, char *const matrice[]) {
    size_t occorrenze[UCHAR_MAX + 1] = {0};
    for (size_t riga = 0; riga < n; ++riga) {
        for (size_t colonna = 0; colonna < n; ++colonna) {
            ++occorrenze[(unsigned char)matrice[riga][colonna]];
        }
    }

    Risultato risultato = {0, 0};
    for (size_t valore = 0; valore <= UCHAR_MAX; ++valore) {
        if (occorrenze[valore] > risultato.frequenza) {
            risultato.carattere = (unsigned char)valore;
            risultato.frequenza = occorrenze[valore];
        }
    }

    puts("Matrice filtrata:");
    for (size_t riga = 0; riga < n; ++riga) {
        for (size_t colonna = 0; colonna < n; ++colonna) {
            printf("%c ", (unsigned char)matrice[riga][colonna] == risultato.carattere
                              ? (char)risultato.carattere : ' ');
        }
        putchar('\n');
    }
    return risultato;
}

static char **allocaMatrice(size_t n) {
    char **matrice = calloc(n, sizeof *matrice);
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

static void liberaMatrice(char **matrice, size_t n) {
    for (size_t riga = 0; riga < n; ++riga) free(matrice[riga]);
    free(matrice);
}

int main(void) {
    enum { N = 4 };
    const char esempio[N][N] = {
        {'a', 'b', 'a', 'c'},
        {'d', 'a', 'e', 'f'},
        {'g', 'h', 'a', 'i'},
        {'j', 'k', 'l', 'a'}
    };

    char **matrice = allocaMatrice(N);
    if (matrice == NULL) {
        fputs("Allocazione non riuscita.\n", stderr);
        return 1;
    }
    for (size_t riga = 0; riga < N; ++riga) {
        for (size_t colonna = 0; colonna < N; ++colonna) {
            matrice[riga][colonna] = esempio[riga][colonna];
        }
    }

    Risultato risultato = elaboraMatrice(N, matrice);
    printf("Carattere più frequente: '%c', %zu occorrenze.\n",
           (char)risultato.carattere, risultato.frequenza);
    liberaMatrice(matrice, N);
    return 0;
}
