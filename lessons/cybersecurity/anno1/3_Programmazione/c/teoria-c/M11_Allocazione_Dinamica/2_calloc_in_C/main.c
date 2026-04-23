// La calloc è una funzione simile alla malloc, ma con due differenze principali:
// 1) La calloc accetta due argomenti: il numero di elementi
//    e la dimensione di ogni elemento (in byte).

// 2) La calloc inizializza a zero tutti i byte della memoria allocata,
//    mentre la malloc lascia il contenuto della memoria non inizializzato.

// Si chiama così perché sta per "contiguous allocation" (allocazione contigua).
// Infatti, la memoria allocata con calloc è garantita essere contigua,
// il che può essere utile per certe strutture dati come array e matrici.
// Mentre la malloc non garantisce che la memoria allocata sia contigua
// (sebbene spesso lo sia).

// Malloc è più veloce della calloc perché non deve inizializzare la memoria.
// Ma calloc è più sicura, conduce a meno bug legati a memoria non inizializzata!

// SINTASSI DI CALLOC:

// void *calloc(size_t num, size_t size);

// dove: size_t è un tipo di dato intero senza segno (unsigned int) che rappresenta
// la dimensione in byte. La funzione restituisce un puntatore di tipo void*
// alla memoria allocata, oppure NULL se l'allocazione fallisce.

#include <stdio.h>
#include <stdlib.h>  // Per calloc e free

int main() {

    int giocatori = 0;

    printf("Quanti giocatori vuoi inserire? ");
    scanf("%d", &giocatori);
    getchar();  // Per consumare il newline rimasto nel buffer
    
    // Allochiamo dinamicamente un array di n stringhe, che ricordiamo essere
    // un array di array di caratteri usando calloc.
    char **arrayGiocatori = (char **)calloc(giocatori, sizeof(char *));

    // Evitiamo segmentation fault controllando che l'allocazione sia andata a buon fine.
    if (arrayGiocatori == NULL) {
        perror("Errore nell'allocazione della memoria");
        return EXIT_FAILURE;
    }

    // Ora possiamo usare l'array come se fosse stato dichiarato staticamente.
    for (int i = 0; i < giocatori; i++) {
        printf("Inserisci il giocatore %d: ", i + 1);
        arrayGiocatori[i] = (char *)calloc(20, sizeof(char));  // Allocazione per il nome del giocatore
        if (arrayGiocatori[i] == NULL) {
            perror("Errore nell'allocazione della memoria");
            return EXIT_FAILURE;
        }
        scanf("%19s", arrayGiocatori[i]);  // Limitiamo l'input a 19 caratteri
        getchar();  // Per consumare il newline rimasto nel buffer
    }

    printf("I giocatori inseriti sono:\n");
    for (int i = 0; i < giocatori; i++) {
        printf("[%s], ", arrayGiocatori[i]);
    }
    printf("\n");

    // Non dimentichiamoci di liberare la memoria allocata quando non ci serve più!
    for (int i = 0; i < giocatori; i++) {
        free(arrayGiocatori[i]);
    }
    free(arrayGiocatori);
    // E evitiamo dangling pointer azzerando il puntatore.
    arrayGiocatori = NULL;

    return EXIT_SUCCESS;
}