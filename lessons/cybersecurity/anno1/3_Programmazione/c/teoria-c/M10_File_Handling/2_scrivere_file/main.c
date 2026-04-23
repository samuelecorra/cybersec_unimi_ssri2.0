
// Ora che sappiamo come aprire e chiudere i file, vediamo come si scrive su un file:

#include <stdio.h>
#include <stdlib.h> // necessario per usare la funzione exit()

int main() {

    FILE *ptrFile; // dichiariamo un puntatore a FILE

    // Apriamo un file in modalità scrittura ("w").
    // Se il file non esiste, viene creato. Se esiste, viene sovrascritto.
    ptrFile = fopen("filefolder/output.txt", "w");
    if (ptrFile == NULL) {
        perror("Errore apertura file");
        exit(EXIT_FAILURE);
    }
    
    printf("File aperto con successo per la scrittura!\n");

    // Per scrivere su un file, usiamo la funzione fprintf(), che funziona
    // come printf(), ma invece di stampare sulla console, scrive nel file.
    // Sintassi: int fprintf(FILE *stream, const char *format, ...);

    // fprintf di norma ritorna:
    // - il numero di caratteri scritti in caso di successo
    // - un numero negativo in caso di errore


    // Possiamo passare direttamente una stringa:
    if (fprintf(ptrFile, "Ciao Principessa!\n") < 0) {
        perror("Errore scrittura file");
        exit(EXIT_FAILURE);
    }

    printf("Dati scritti con successo nel file!\n");

    // Ma possiamo anche rimanere fedeli alla stringa di formato:
    char messaggio[] = "Cara Principessa, hai un castello da favola...";
    if (fprintf(ptrFile, "%s", messaggio) < 0) {
        perror("Errore scrittura file");
        exit(EXIT_FAILURE);
    }

    printf("Dati scritti con successo nel file!\n");

    // Dopo aver finito di scrivere, chiudiamo il file.
    fclose(ptrFile);
    ptrFile = NULL; // buona norma azzerare il puntatore

    if (ptrFile != NULL) {
        perror("Errore chiusura file");
        exit(EXIT_FAILURE);
    }
    printf("File chiuso con successo!\n");
    
    return 0;
}