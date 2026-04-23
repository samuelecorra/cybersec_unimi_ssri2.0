// Dal momento che non assegnare valori alle variabili porta a UB, è buona norma
// inizializzarle sempre:

#include <stdio.h>

int main() {

    int età = 0;
    char iniziale = '\0'; // carattere nullo
    float mediaScolastica = 0.0f; // 'f' indica che è un float e non un double
    char nome[50] = ""; // stringa vuota

    printf("%d\n", età);
    printf("%c\n", iniziale);
    printf("%f\n", mediaScolastica);
    printf("%s\n", nome);               // non otteniamo più UB!

    // REGOLA D'ORO: INIZIALIZZA, POI ACCEDI/USA!

    return 0;
}
