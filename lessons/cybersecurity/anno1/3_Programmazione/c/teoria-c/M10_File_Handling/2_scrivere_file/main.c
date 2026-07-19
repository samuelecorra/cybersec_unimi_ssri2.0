#include <stdio.h>
#include <stdlib.h>

int main(void) {
    FILE *file = fopen("filefolder/output.txt", "w");
    if (file == NULL) {
        perror("Apertura del file di output");
        return EXIT_FAILURE;
    }

    const char messaggio[] = "Cara Principessa, hai un castello da favola...";
    if (fprintf(file, "Ciao Principessa!\n%s\n", messaggio) < 0) {
        perror("Scrittura del file");
        (void)fclose(file);
        return EXIT_FAILURE;
    }

    // La bufferizzazione può rimandare l'errore fino al flush effettuato da fclose:
    // controllare solo fprintf non è sufficiente per confermare la persistenza.
    if (fclose(file) == EOF) {
        perror("Chiusura del file di output");
        return EXIT_FAILURE;
    }
    file = NULL;
    puts("Dati scritti e file chiuso con successo.");
    return EXIT_SUCCESS;
}
