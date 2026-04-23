// Ma se volessimo che la funzione, la quale elabora dei dati, restituisca un valore?
// In questo caso, dobbiamo usare il costrutto "return".

// Il costrutto return ha due scopi principali:
// 1. Restituire un valore dalla funzione al chiamante
// 2. Terminare l'esecuzione della funzione e tornare al punto di chiamata!

// ====================================================================================

#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

// Supponiamo che ci scocci ogni volta invocare pow dal math.h per calcolare il 
// quadrato di un numero.
// Creiamo una funzione che lo faccia per noi:
double quadrato(double base) {
    return pow(base, 2); // Restituisce il valore di base elevato al quadrato
}

// N.B. La funzione pow restituisce un double, quindi anche la nostra funzione
// deve avere return type double!

// ====================================================================================

// Supponiamo di dimenticarci di includere math.h
double quadratoSenzaLibreria(double base) {
    return base * base; // Restituisce il valore di base elevato al quadrato
}

// N.B2. Le funzioni più semplici hanno il corpo costituito dal solo return!

// ====================================================================================

bool controlloMaggioreEtà(int età) {
    if (età >= 18) {
        return true; // Restituisce true se l'età è 18 o più
    } else {
        return false; // Restituisce false se l'età è inferiore a 18
    }
}

// ====================================================================================

int massimo(int a, int b) {
    if (a > b) {
        return a; // Restituisce a se a è maggiore di b
    } else if (a < b) {
        return b; // Restituisce b altrimenti
    } else {
        return 0; // Restituisce 0 se a e b sono uguali
    }
}

int main() {

    SetConsoleOutputCP(65001);

    double numero, numeroAlQuadrato;

    printf("Inserisci un numero: ");
    scanf("%lf", &numero);
    getchar();

    // Ora invochiamo la funzione quadrato:
    numeroAlQuadrato = quadrato(numero); // Chiamata della funzione

    printf("Il quadrato - usando math.h - di %.2lf è %.2lf\n", numero, numeroAlQuadrato);
    printf("Il quadrato - senza usare math.h - di %.2lf è %.2lf\n", numero, quadratoSenzaLibreria(numero));
    
// ====================================================================================

    printf("\nPremi INVIO per continuare...\n");
    getchar();

    int età;
    printf("Inserisci la tua età: ");
    scanf("%d", &età);
    getchar();

    if (controlloMaggioreEtà(età)) {
        printf("Sei maggiorenne!\n");
    } else {
        printf("Sei minorenne!\n");
    }

// ====================================================================================

    printf("\nPremi INVIO per continuare...\n");
    getchar();

    int num1, num2;
    printf("Inserisci due numeri interi (separati da spazio): ");
    scanf("%d %d", &num1, &num2);
    getchar();
    int max = massimo(num1, num2);
    
    if (max != 0) {
        printf("Il numero maggiore tra %d e %d è %d\n", num1, num2, max);
    } else {
        printf("I numeri sono uguali!\n");
    }

    // Ecco finalmente spiegato il motivo per cui alla fine del main troviamo
    // sempre return 0;!!!!!!!!!
    return 0;
}