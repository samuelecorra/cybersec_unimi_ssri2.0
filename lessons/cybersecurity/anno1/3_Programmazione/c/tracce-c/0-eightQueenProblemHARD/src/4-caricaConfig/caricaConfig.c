#include <stdio.h>

#define N 8

/* Caricamento transazionale: stato viene aggiornato soltanto se l'intero file è
 * valido. Il formato adottato dal progetto usa righe interne 0..7. */
int caricaConfig(const char *nomeFile, int stato[N]) {
    if (nomeFile == NULL || stato == NULL) {
        return 0;
    }

    FILE *file = fopen(nomeFile, "r");
    if (file == NULL) {
        perror(nomeFile);
        return 0;
    }

    int temporaneo[N];
    int valido = 1;
    for (int i = 0; i < N; ++i) {
        if (fscanf(file, "%d", &temporaneo[i]) != 1
            || temporaneo[i] < 0 || temporaneo[i] >= N) {
            valido = 0;
            break;
        }
    }

    char extra = '\0';
    if (valido && fscanf(file, " %c", &extra) == 1) {
        valido = 0; // dopo gli N interi sono ammessi soltanto spazi
    }
    if (ferror(file)) {
        valido = 0;
    }
    if (fclose(file) == EOF) {
        valido = 0;
    }

    if (!valido) {
        fprintf(stderr, "Configurazione non valida in '%s'.\n", nomeFile);
        return 0;
    }
    for (int i = 0; i < N; ++i) {
        stato[i] = temporaneo[i];
    }
    return 1;
}
