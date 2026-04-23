// Infine, vediamo come usare realloc per ridimensionare un blocco di memoria già
// allocato in precedenza con malloc o calloc. La funzione realloc prende due argomenti:
// un puntatore al blocco di memoria da ridimensionare e la nuova dimensione desiderata.

// SINTASSI:
// void *realloc(void *ptr, size_t new_size);

#include <stdio.h>
#include <stdlib.h>
#include <windows.h> // Per SetConsoleOutputCP

int main() {

    SetConsoleOutputCP(CP_UTF8); // Per Windows, per abilitare i caratteri UTF-8 nella console
    
    int numeroPrezzi = 0;

    printf("Quanti prezzi vuoi inserire? ");
    scanf("%d", &numeroPrezzi);
    getchar(); // per consumare il carattere di newline rimasto nel buffer

    // Allochiamo dinamicamente un array di float per memorizzare i prezzi
    float *prezzi = (float *)malloc(numeroPrezzi * sizeof(float));
    // Il casting è facoltativo in C, ma è buona pratica includerlo per chiarezza.

    if (prezzi == NULL) {
        printf("Errore di allocazione della memoria!\n");
        return 1; // Esci con codice di errore
    }

    // Inseriamo i prezzi
    for (int i = 0; i < numeroPrezzi; i++) {
        printf("Inserisci il prezzo %d: ", i + 1);
        scanf("%f", &prezzi[i]);
        getchar(); // per consumare il carattere di newline rimasto nel buffer
    }

    // Per ora stampiamo i prezzi inseriti finora:
    printf("Prezzi inseriti:\n");
    for (int i = 0; i < numeroPrezzi; i++) {
        printf("Prezzo %d: %.2f€\n", i + 1, prezzi[i]);
    }

// ==================================================================================

    // ORA: PUNTO CRUCIALE: SUPPONIAMO DI VOLER CAMBIARE IL NUMERO DEI PREZZI
    int nuoviPrezzi = 0;

    printf("Quanti prezzi vuoi inserire in totale? ");
    scanf("%d", &nuoviPrezzi);
    getchar();

    // Ridimensioniamo l'array di prezzi usando realloc
    float *temp = (float *)realloc(prezzi, nuoviPrezzi * sizeof(float));
    // Ritorna un puntatore al nuovo blocco di memoria, che potrebbe essere diverso
    // dall'originale. Per questo motivo, è buona pratica usare un puntatore temporaneo.

    // TRA L'ALTRO, REALLOC LIBERA ANCHE IL VECCHIO BLOCCO DI MEMORIA!

    // MA PUO' FALLIRE, QUINDI DOBBIAMO CONTROLLARE:
    if (temp == NULL) {
        printf("Errore di riallocazione della memoria!\n");
        free(prezzi); // Liberiamo il vecchio blocco di memoria
        return 1; // Esci con codice di errore
    }

    prezzi = temp; // Aggiorniamo il puntatore originale
    temp = NULL; // Evitiamo dangling pointer

    // Ora possiamo inserire i nuovi prezzi
    for (int i = numeroPrezzi; i < nuoviPrezzi; i++) {
        printf("Inserisci il prezzo %d: ", i + 1);
        scanf("%f", &prezzi[i]);
        getchar(); // per consumare il carattere di newline rimasto nel buffer
    }

    // Stampiamo tutti i prezzi
    printf("Prezzi finali:\n");
    for (int i = 0; i < nuoviPrezzi; i++) {
        printf("Prezzo %d: %.2f€\n", i + 1, prezzi[i]);
    }

    // Liberiamo la memoria allocata
    free(prezzi);
    return 0;
}
