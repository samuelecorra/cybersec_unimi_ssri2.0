/*
 * Un puntatore a puntatore contiene l'indirizzo di una variabile puntatore.
 * È utile, per esempio, quando una funzione deve aggiornare il puntatore del
 * chiamante o quando si rappresenta un array dinamico di stringhe.
 */

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

static bool creaIntero(int **destinazione, int valore) {
    if (destinazione == NULL) {
        return false;
    }

    int *nuovo = malloc(sizeof *nuovo);
    if (nuovo == NULL) {
        return false;
    }
    *nuovo = valore;
    *destinazione = nuovo; // modifica il puntatore del chiamante
    return true;
}

int main(void) {
    int x = 19;
    int *p1 = &x;
    int **p2 = &p1;
    int ***p3 = &p2;

    printf("x attraverso 0/1/2/3 livelli: %d, %d, %d, %d\n",
           x, *p1, **p2, ***p3);
    printf("&x=%p, p1=%p, *p2=%p, **p3=%p\n",
           (void *)&x, (void *)p1, (void *)*p2, (void *)**p3);
    printf("&p1=%p e p2=%p; &p2=%p e p3=%p\n",
           (void *)&p1, (void *)p2, (void *)&p2, (void *)p3);

    int *dinamico = NULL;
    if (!creaIntero(&dinamico, 42)) {
        fputs("Allocazione non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }
    printf("Valore creato tramite parametro int **: %d\n", *dinamico);
    free(dinamico);
    dinamico = NULL;
    return EXIT_SUCCESS;
}
