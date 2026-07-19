/*
 * Il C non assegna automaticamente la proprietà della memoria. Qui creaSequenza
 * trasferisce al chiamante la responsabilità di chiamare free una volta sola.
 * Uscite anticipate e fallimenti devono convergere verso un cleanup completo.
 */

#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static bool creaSequenza(size_t quantità, int **risultato) {
    if (risultato == NULL || quantità == 0 || quantità > SIZE_MAX / sizeof(int)) {
        return false;
    }

    int *sequenza = malloc(quantità * sizeof *sequenza);
    if (sequenza == NULL) {
        return false;
    }
    for (size_t i = 0; i < quantità; ++i) {
        sequenza[i] = (int)i;
    }
    *risultato = sequenza;
    return true;
}

int main(void) {
    int *valori = NULL;
    size_t quantità = 5;

    if (!creaSequenza(quantità, &valori)) {
        fputs("Creazione non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }
    for (size_t i = 0; i < quantità; ++i) {
        printf("%d%s", valori[i], i + 1 == quantità ? "\n" : ", ");
    }

    free(valori);
    valori = NULL;
    // Dopo free: dereferenziare valori, liberarlo di nuovo o usare un suo alias
    // pendente sarebbe un errore (use-after-free o double-free).
    return EXIT_SUCCESS;
}
