/*
 * Una variabile locale come "int a" ha normalmente durata automatica, non è
 * "allocata staticamente". malloc riserva invece a runtime un blocco di memoria
 * contiguo di dimensione richiesta e restituisce void *, convertito implicitamente
 * nel puntatore destinazione in C. Il contenuto iniziale è indeterminato.
 */

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

int main(void) {
    size_t quantità = 0;
    printf("Quanti numeri vuoi inserire? ");
    if (scanf("%zu", &quantità) != 1 || quantità == 0) {
        fputs("Quantità non valida.\n", stderr);
        return EXIT_FAILURE;
    }
    if (quantità > SIZE_MAX / sizeof(int)) {
        fputs("Dimensione richiesta troppo grande.\n", stderr);
        return EXIT_FAILURE;
    }

    int *voti = malloc(quantità * sizeof *voti);
    if (voti == NULL) {
        fputs("Allocazione non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }

    for (size_t i = 0; i < quantità; ++i) {
        printf("Inserisci il voto %zu: ", i + 1);
        if (scanf("%d", &voti[i]) != 1) {
            fputs("Voto non valido.\n", stderr);
            free(voti);
            return EXIT_FAILURE;
        }
    }

    for (size_t i = 0; i < quantità; ++i) {
        printf("%d%s", voti[i], i + 1 == quantità ? "\n" : ", ");
    }

    free(voti);
    voti = NULL; // utile contro il riuso locale, ma non azzera eventuali alias
    return EXIT_SUCCESS;
}
