/*
 * realloc può ampliare o ridurre un blocco. Se ha successo, restituisce il nuovo
 * indirizzo (che può coincidere col precedente) e il vecchio puntatore non va più
 * usato. Se fallisce, restituisce NULL e il vecchio blocco resta valido.
 */

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int leggiQuantità(const char *richiesta, size_t *valore) {
    printf("%s", richiesta);
    return scanf("%zu", valore) == 1 && *valore > 0;
}

int main(void) {
    size_t numeroPrezzi = 0;
    if (!leggiQuantità("Quanti prezzi vuoi inserire? ", &numeroPrezzi)
        || numeroPrezzi > SIZE_MAX / sizeof(float)) {
        fputs("Dimensione non valida.\n", stderr);
        return EXIT_FAILURE;
    }

    float *prezzi = malloc(numeroPrezzi * sizeof *prezzi);
    if (prezzi == NULL) {
        fputs("Allocazione non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }

    for (size_t i = 0; i < numeroPrezzi; ++i) {
        printf("Inserisci il prezzo %zu: ", i + 1);
        if (scanf("%f", &prezzi[i]) != 1) {
            fputs("Prezzo non valido.\n", stderr);
            free(prezzi);
            return EXIT_FAILURE;
        }
    }

    size_t nuoviPrezzi = 0;
    if (!leggiQuantità("Quanti prezzi vuoi conservare in totale? ", &nuoviPrezzi)
        || nuoviPrezzi > SIZE_MAX / sizeof *prezzi) {
        fputs("Nuova dimensione non valida.\n", stderr);
        free(prezzi);
        return EXIT_FAILURE;
    }

    float *temporaneo = realloc(prezzi, nuoviPrezzi * sizeof *prezzi);
    if (temporaneo == NULL) {
        fputs("Riallocazione non riuscita; il vecchio blocco è ancora valido.\n", stderr);
        free(prezzi);
        return EXIT_FAILURE;
    }
    prezzi = temporaneo;

    // Se il blocco cresce, soltanto la parte già esistente conserva i valori;
    // i nuovi elementi hanno contenuto indeterminato e vanno inizializzati.
    for (size_t i = numeroPrezzi; i < nuoviPrezzi; ++i) {
        printf("Inserisci il prezzo %zu: ", i + 1);
        if (scanf("%f", &prezzi[i]) != 1) {
            fputs("Prezzo non valido.\n", stderr);
            free(prezzi);
            return EXIT_FAILURE;
        }
    }

    for (size_t i = 0; i < nuoviPrezzi; ++i) {
        printf("Prezzo %zu: %.2f\n", i + 1, prezzi[i]);
    }
    free(prezzi);
    return EXIT_SUCCESS;
}
