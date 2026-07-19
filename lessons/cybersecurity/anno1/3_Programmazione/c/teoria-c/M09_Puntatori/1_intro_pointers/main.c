/*
 * Un puntatore contiene l'indirizzo di un oggetto o di una funzione compatibile.
 * I puntatori permettono, tra l'altro, di modificare oggetti del chiamante, evitare
 * copie grandi, percorrere array e gestire memoria dinamica. Non possiedono però
 * automaticamente l'oggetto puntato e restano validi soltanto durante la sua vita.
 */

#include <stdio.h>

int main(void) {
    int età = 17;
    int *pEtà = NULL; // valore sentinella: al momento non punta a un int

    printf("Età: %d\n", età);
    printf("Indirizzo di età: %p\n", (void *)&età);

    pEtà = &età;
    printf("Indirizzo di età: %p\n", (void *)&età);
    printf("Valore di pEtà:   %p\n", (void *)pEtà);

    // %p richiede un void *; la forma testuale dell'indirizzo è definita
    // dall'implementazione, quindi non si antepone manualmente "0x".
    return 0;
}
