/*
 * La libreria standard tratta i file come stream bufferizzati. fopen associa
 * un file a un oggetto FILE e restituisce NULL se l'apertura fallisce.
 *
 * Modi principali:
 *   r   lettura; il file deve esistere
 *   w   scrittura; crea o tronca il file
 *   a   scrittura in coda; crea se necessario
 *   r+  lettura/scrittura; il file deve esistere
 *   w+  lettura/scrittura; crea o tronca
 *   a+  lettura/scrittura; le scritture vanno in coda
 * Aggiungere b (per esempio rb) seleziona la modalità binaria, importante sui
 * sistemi che distinguono file testuali e binari.
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    FILE *file = fopen("filefolder/esempio.txt", "r");
    if (file == NULL) {
        perror("Apertura di filefolder/esempio.txt");
        return EXIT_FAILURE;
    }
    puts("File aperto con successo.");

    // fclose restituisce EOF se il flush o la chiusura falliscono. Assegnare NULL
    // al puntatore dopo fclose non dice nulla sull'esito della chiusura.
    if (fclose(file) == EOF) {
        perror("Chiusura del file");
        return EXIT_FAILURE;
    }
    file = NULL; // evita il riuso accidentale dello stream ormai chiuso
    puts("File chiuso con successo.");
    return EXIT_SUCCESS;
}
