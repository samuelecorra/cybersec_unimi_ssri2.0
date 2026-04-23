#include <stdio.h>
#include <stdlib.h>

#define N 8

int caricaConfig(const char *nome_file, int stato[N]) {
    FILE *fp = fopen(nome_file, "r");
    if (fp == NULL) {
        printf("Errore: impossibile aprire il file '%s'\n", nome_file);
        return 0;
    }

    for (int i = 0; i < N; i++) {
        if (fscanf(fp, "%d", &stato[i]) != 1) {
            printf("Errore: formato non valido nel file '%s'\n", nome_file);
            fclose(fp);
            return 0;
        }
    }

    fclose(fp);
    return 1;
}