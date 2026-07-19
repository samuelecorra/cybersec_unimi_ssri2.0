/*
RICORSIONE

Una funzione è ricorsiva quando chiama sé stessa, direttamente o indirettamente.
Una definizione ricorsiva corretta richiede:

1. uno o più casi base, risolti senza un'ulteriore chiamata;
2. un passo ricorsivo che avvicini ogni chiamata a un caso base.

Ogni chiamata attiva conserva parametri, variabili locali e indirizzo di ritorno
nel proprio record di attivazione. Una ricorsione troppo profonda può esaurire lo
stack: il C non garantisce l'ottimizzazione delle chiamate tail-recursive.

Il fattoriale è definito per n >= 0 da:
0! = 1
n! = n * (n - 1)! per n > 0

Con unsigned long long, 20! è rappresentabile sulle comuni implementazioni a
64 bit, mentre 21! non lo è. Il tipo e il dominio vanno quindi controllati.
*/

#include <stdio.h>

unsigned long long fattoriale(unsigned int n) {
    if (n == 0U) {
        return 1ULL; // caso base
    }
    return (unsigned long long)n * fattoriale(n - 1U); // passo ricorsivo
}

int main(void) {
    unsigned int n = 5U;
    printf("%u! = %llu\n", n, fattoriale(n));

    return 0;
}
