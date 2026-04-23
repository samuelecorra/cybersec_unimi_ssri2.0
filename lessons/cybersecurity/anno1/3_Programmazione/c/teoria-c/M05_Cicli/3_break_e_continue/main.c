/*
In C, ci sono forniti due strumenti per poter veicolare direttamente l'esecuzione
di qualsiasi ciclo:

1) keyword break
2) keyword continue

break: permette di uscire da un ciclo in qualsiasi momento, indipendentemente
dal fatto che la condizione di continuazione del ciclo sia vera o falsa.
E' come il pulsante stop sul telecomando della TV.

continue: permette di saltare l'iterazione corrente del ciclo e passare
direttamente alla successiva iterazione, se la condizione di continuazione
è vera. E' come il pulsante skip sul telecomando della TV.

Esempio break:  */

#include <stdio.h>

int main() {
   
    for (int i = 0; i < 10; i++) {
        if (i == 5) {
            break; // esci dal ciclo quando i è uguale a 5
        }
        printf("Valore di i: %d\n", i);
    }
    printf("Ciclo terminato.\n");
    // Abbiamo stampato i valori da 0 a 4, poi il ciclo si è interrotto 
    // quando i è diventato 5

    printf("Premi un tasto per continuare...\n");
    getchar();

    // Esempio continue:
    for (int i = 0; i < 10; i++) {
        if (i % 2 == 0) {
            continue; // salta l'iterazione corrente se i è pari
        }
        printf("Valore di i (dispari): %d\n", i);
    }
    printf("Ciclo terminato.\n");
    // Abbiamo stampato solo i valori dispari da 0 a 9, saltando quelli pari
    
    printf("Premi un tasto per uscire...\n");
    getchar();

    return 0;
}