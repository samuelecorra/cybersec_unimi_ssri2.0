#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    long long *divisori;
    size_t count;
} DivisoriArray;

/*
 * L'array dinamico offre memoria contigua e accesso per indice. Sono necessari
 * due passaggi: il primo conta i divisori, il secondo riempie l'array.
 */
DivisoriArray divisori(long long n)
{
    DivisoriArray risultato = {NULL, 0};
    if (n < 1) {
        return risultato;
    }

    for (long long i = 1; ; i++) {
        if (n % i == 0) {
            if (risultato.count == SIZE_MAX) {
                return (DivisoriArray){NULL, 0};
            }
            risultato.count++;
        }
        if (i == n) {
            break; /* evita l'overflow dell'incremento quando n == LLONG_MAX */
        }
    }

    if (risultato.count > SIZE_MAX / sizeof *risultato.divisori) {
        return (DivisoriArray){NULL, 0};
    }
    risultato.divisori = malloc(risultato.count * sizeof *risultato.divisori);
    if (risultato.divisori == NULL) {
        risultato.count = 0;
        return risultato;
    }

    size_t indice = 0;
    for (long long i = 1; ; i++) {
        if (n % i == 0) {
            risultato.divisori[indice++] = i;
        }
        if (i == n) {
            break;
        }
    }
    return risultato;
}

int main(void)
{
    long long n = 83528476;
    printf("Divisori di %lld:\n", n);

    DivisoriArray lista = divisori(n);
    if (lista.divisori == NULL) {
        fprintf(stderr, "Input non valido o memoria insufficiente.\n");
        return EXIT_FAILURE;
    }
    for (size_t i = 0; i < lista.count; i++) {
        printf("%lld  ", lista.divisori[i]);
    }
    putchar('\n');
    free(lista.divisori);
    return EXIT_SUCCESS;
}
