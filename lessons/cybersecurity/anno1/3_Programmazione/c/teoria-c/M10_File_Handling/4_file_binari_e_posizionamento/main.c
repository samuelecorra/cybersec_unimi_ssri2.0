/*
 * fwrite e fread trasferiscono oggetti binari. Un file prodotto copiando una
 * struct non è un formato portabile tra architetture o compilatori: padding,
 * endianess e rappresentazioni dei tipi possono cambiare. Per dati persistenti
 * interoperabili serve una serializzazione definita esplicitamente.
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int matricola;
    double media;
} Record;

int main(void) {
    const Record originali[] = {
        {.matricola = 101, .media = 27.5},
        {.matricola = 102, .media = 29.0}
    };
    Record letti[2] = {0};

    // tmpfile apre un file binario temporaneo in modalità aggiornamento; viene
    // eliminato automaticamente quando è chiuso o al termine normale del processo.
    FILE *file = tmpfile();
    if (file == NULL) {
        perror("Creazione del file temporaneo");
        return EXIT_FAILURE;
    }

    size_t scritti = fwrite(originali, sizeof originali[0], 2, file);
    if (scritti != 2) {
        fputs("Scrittura binaria incompleta.\n", stderr);
        (void)fclose(file);
        return EXIT_FAILURE;
    }

    // Tra una scrittura e una lettura su uno stream di aggiornamento è necessario
    // un riposizionamento (o un flush nei casi ammessi). fseek torna all'inizio.
    if (fseek(file, 0L, SEEK_SET) != 0) {
        perror("Riposizionamento");
        (void)fclose(file);
        return EXIT_FAILURE;
    }

    size_t quantità = fread(letti, sizeof letti[0], 2, file);
    if (quantità != 2) {
        fputs(ferror(file) ? "Errore di lettura binaria.\n" : "Fine file anticipata.\n", stderr);
        (void)fclose(file);
        return EXIT_FAILURE;
    }

    for (size_t i = 0; i < quantità; ++i) {
        printf("Matricola %d, media %.1f\n", letti[i].matricola, letti[i].media);
    }

    if (fclose(file) == EOF) {
        perror("Chiusura del file temporaneo");
        return EXIT_FAILURE;
    }
    return EXIT_SUCCESS;
}
