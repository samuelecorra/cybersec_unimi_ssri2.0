/* Versione con VLA: il supporto ai VLA è opzionale nelle implementazioni C11-C17. */

#include <limits.h>
#include <stddef.h>
#include <stdio.h>

typedef struct {
    unsigned char carattere;
    size_t frequenza;
} Risultato;

static Risultato elaboraMatrice(size_t n, const char matrice[n][n]) {
    size_t occorrenze[UCHAR_MAX + 1] = {0};
    for (size_t riga = 0; riga < n; ++riga)
        for (size_t colonna = 0; colonna < n; ++colonna)
            ++occorrenze[(unsigned char)matrice[riga][colonna]];

    Risultato risultato = {0, 0};
    for (size_t valore = 0; valore <= UCHAR_MAX; ++valore) {
        if (occorrenze[valore] > risultato.frequenza) {
            risultato = (Risultato){(unsigned char)valore, occorrenze[valore]};
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

int main(void) {
    const char matrice[5][5] = {
        {'e', 'r', 'i', 'k', 'a'},
        {'l', 'u', 'c', 'a', 's'},
        {'b', 'i', 'l', 'l', 'y'},
        {'v', 'e', 'c', 'n', 'a'},
        {'a', 'm', 'o', 'r', 'e'}
    };
    Risultato risultato = elaboraMatrice(5, matrice);
    printf("Carattere più frequente: '%c', %zu occorrenze.\n",
           (char)risultato.carattere, risultato.frequenza);
    return 0;
}
