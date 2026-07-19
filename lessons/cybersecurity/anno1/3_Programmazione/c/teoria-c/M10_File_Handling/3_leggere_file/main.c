#include <stdio.h>
#include <stdlib.h>

int main(void) {
    char buffer[100];
    FILE *file = fopen("filefolder/input.txt", "r");
    if (file == NULL) {
        perror("Apertura del file di input");
        return EXIT_FAILURE;
    }

    while (fgets(buffer, sizeof buffer, file) != NULL) {
        fputs(buffer, stdout);
    }

    // NULL da fgets può significare EOF normale oppure errore: ferror distingue i casi.
    if (ferror(file)) {
        perror("Lettura del file");
        (void)fclose(file);
        return EXIT_FAILURE;
    }

    if (fclose(file) == EOF) {
        perror("Chiusura del file di input");
        return EXIT_FAILURE;
    }
    return EXIT_SUCCESS;
}
