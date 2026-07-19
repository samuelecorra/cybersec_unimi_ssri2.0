/*
 * Nella maggior parte delle espressioni, il nome di un array viene convertito in
 * un puntatore al primo elemento. Fanno eccezione, tra gli altri, sizeof, _Alignof
 * e l'operatore &: un array non è quindi "un puntatore".
 */

#include <stddef.h>
#include <stdio.h>

int main(void) {
    int voti[] = {30, 25, 23, 31, 27};
    size_t numeroVoti = sizeof voti / sizeof voti[0];

    for (size_t i = 0; i < numeroVoti; ++i) {
        printf("Indirizzo del voto %zu: %p\n", i + 1, (void *)&voti[i]);
    }

    printf("voti:     %p, tipo dopo il decay: int *\n", (void *)voti);
    printf("&voti[0]: %p, tipo: int *\n", (void *)&voti[0]);
    printf("&voti:    %p, tipo: int (*)[%zu]\n", (void *)&voti, numeroVoti);

    // Questo è un compound literal: crea un vero array automatico che vive fino
    // alla fine del blocco. Non è un cast e pVoti2 non conserva la sua lunghezza.
    int *pVoti2 = (int[]){30, 25, 23, 31, 27};
    size_t numeroVoti2 = 5;

    printf("sizeof(voti) = %zu, sizeof(pVoti2) = %zu\n",
           sizeof voti, sizeof pVoti2);
    for (size_t i = 0; i < numeroVoti2; ++i) {
        printf("pVoti2[%zu] = %d\n", i, pVoti2[i]);
    }

    // Una funzione che riceve un puntatore deve ricevere separatamente anche la
    // lunghezza, oppure adottare un'altra convenzione esplicita.
    return 0;
}
