// Finora abbiamo sempre allocato staticamente la memoria per le variabili
// (ad esempio: int a;). Vediamo ora come allocare memoria dinamicamente
// durante l'esecuzione del programma, utilizzando la funzione malloc().

// malloc prepara un blocco di memoria di una certa dimensione di byte ben specificata
// dal programmatore, e restituisce un puntatore al primo byte di questo blocco.
// La memoria allocata con malloc rimane allocata fino a quando non viene liberata
// esplicitamente con la funzione free().

// ===================================================================================


// Ecco che finalmente gli array intesi come puntatori a caratteri trovano il loro
// senso: possiamo allocare un array di dimensione specificata dall'utente a runtime!

#include <stdio.h>
#include <stdlib.h>  // Per malloc e free

int main() {

    int n;

    printf("Quanti numeri vuoi inserire? ");
    scanf("%d", &n);

    // Allochiamo dinamicamente un array di n interi.
    int *voti = (int *)malloc(n * sizeof(int));

    // Evitiamo segmentation fault controllando che l'allocazione sia andata a buon fine.
    if (voti == NULL) {
        perror("Errore nell'allocazione della memoria");
        return EXIT_FAILURE;
    }

    // Ora possiamo usare l'array come se fosse stato dichiarato staticamente.
    for (int i = 0; i < n; i++) {
        printf("Inserisci il voto %d: ", i + 1);
        scanf("%d", &voti[i]);
        getchar();  // Per consumare il newline rimasto nel buffer
    }

    printf("I voti inseriti sono:\n");
    for (int i = 0; i < n; i++) {
        printf("[%d], ", voti[i]);
    }
    printf("\n");

    // Non dimentichiamoci di liberare la memoria allocata quando non ci serve più!
    free(voti);
    // E evitiamo dangling pointer azzerando il puntatore.
    voti = NULL;

    return EXIT_SUCCESS;
}