
// Vediamo ora invece come leggere da un file di testo.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {

    char buffer[100] = {0};

    // Apriamo il file in modalità lettura ("r").
    FILE *file = fopen("filefolder/input.txt", "r");
    if (file == NULL) {
        perror("Errore nell'apertura del file");
        return EXIT_FAILURE;
    }

    // Leggiamo il contenuto del file riga per riga.
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);  // Dopo averlo letto, stampiamo la riga.
    }

    // Chiudiamo il file.
    fclose(file);
    return EXIT_SUCCESS;
}