#include <stdbool.h>
#include <stdio.h>

static bool buonCompleanno(int *pEtà) {
    if (pEtà == NULL) {
        return false;
    }

    // Le parentesi sono necessarie: l'incremento postfisso lega più strettamente
    // della dereferenziazione. *pEtà++ incrementerebbe il puntatore, non l'intero.
    (*pEtà)++;
    return true;
}

int main(void) {
    int età = 17;
    int *pEtà = &età;

    printf("Età prima: %d\n", età);
    (void)buonCompleanno(pEtà);
    printf("Età dopo:  %d\n", età);

    età = 17;
    (void)buonCompleanno(&età); // si può passare direttamente l'indirizzo
    printf("Età dopo la chiamata diretta: %d\n", età);

    if (!buonCompleanno(NULL)) {
        puts("Puntatore nullo rifiutato senza dereferenziazione.");
    }
    return 0;
}
