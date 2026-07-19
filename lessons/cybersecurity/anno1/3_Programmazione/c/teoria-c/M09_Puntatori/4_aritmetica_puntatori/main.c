/*
 * p + i avanza di i elementi del tipo puntato, non di i byte. L'aritmetica è
 * definita soltanto entro lo stesso array (compresa la posizione one-past-end).
 * Il puntatore one-past-end può essere confrontato, ma non dereferenziato.
 */

#include <stddef.h>
#include <stdio.h>

int main(void) {
    int voti[] = {30, 25, 23, 31, 27};
    size_t quantità = sizeof voti / sizeof voti[0];

    for (size_t i = 0; i < quantità; ++i) {
        printf("&voti[%zu] = %p, voti + %zu = %p, valore = %d\n",
               i, (void *)&voti[i], i, (void *)(voti + i), *(voti + i));
    }

    int *pTerzoVoto = &voti[2];
    printf("Terzo: %d, quarto: %d, secondo: %d\n",
           *pTerzoVoto, *(pTerzoVoto + 1), *(pTerzoVoto - 1));

    ptrdiff_t distanza = &voti[4] - &voti[1];
    printf("Distanza tra indice 4 e indice 1: %td elementi\n", distanza);

    int *fine = voti + quantità;
    for (int *corrente = voti; corrente != fine; ++corrente) {
        printf("%d ", *corrente);
    }
    putchar('\n');

    // *(voti + 1) è voti[1]. L'espressione *voti + 1 è invece valida ma diversa:
    // somma 1 al valore del primo elemento. Né indici né puntatori garantiscono
    // controlli automatici dei limiti; oltrepassarli produce comportamento indefinito.
    return 0;
}
