#include <stdio.h>
#include <stdlib.h>
#include "header.h"

int main() {
    // Definiamo i nomi dei file di input e output
    const char *file1in = "file1.txt";
    const char *file2in = "file2.txt";
    const char *fileOut = "fileOut.txt";

    // Chiamiamo la funzione unisciFile con i nomi dei file
    int res = unisciFile(file1in, file2in, fileOut);

    // Controlliamo il risultato della funzione
    if (res == 0) {
        printf("Si è verificato un errore durante l'unione dei file.\n");
        return 1; // Uscita con codice di errore
    } else printf("fileOut.txt contiene ora tutti i numeri di file1.txt e file2.txt uniti e ordinati.\n");

    printf("Premere Invio per terminare...");
    getchar(); // Attende l'input dell'utente prima di terminare
    return 0; // Uscita con successo
}

// Andare nel qualsiasi terminale e digitare i comandi (dopo essersi posizionati nella cartella del codice):
// gcc -o test mainV1.c source.c
// e poi eseguire con semplicemente "./test" su Linux/Mac o "test.exe" su Windows.