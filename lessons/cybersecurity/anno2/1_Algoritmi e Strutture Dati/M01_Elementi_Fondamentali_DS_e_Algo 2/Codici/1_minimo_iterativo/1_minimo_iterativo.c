// Nel corso di algoritmi e strutture dati, tutti i codici di riferimento e d'esempio
// sono scritti in C. Non perdiamoci in chiacchiere e vediamo in prima battuta come
// realizzare un semplice algoritmo per calcolare il minimo di un array di interi.

// La soluzione più immediata da pensare per una mente umana alle prime armi è la seguente:

// 1. Prendo il primo elemento e ipotizzo che sia il minimo
// 2. Lo confronto con tutti gli altri, e ad ogni confronto confermo o confuto la mia ipotesi
// 3. Alla fine del ciclo, l'elemento che rimane come minimo è effettivamente il minimo

// Si noti che questa è una soluzione iterativa, in quanto utilizza un ciclo per scorrere
// gli elementi dell'array. Teniamolo a mente per dopo.

#include <stdio.h>

int minimo_iterativo(int* array, int j, int k) {

    int min = array[j]; // Accedo al primo elemento e lo ipotizzo come minimo
    int i; // Dichiaro la variabile di ciclo

    for (i = j + 1; i <= k; i++) { // Ciclo dal secondo elemento fino all'ultimo
        // Uso un ternary operator per snellire il codice:
        min = array[i] < min ? array[i] : min;
    }
    return min;
}

// Ora possiamo utilizzare la funzione appena creata:

int main() {
    int numeri[6] = {10, 4, 2000, 20, 13, 2004};
    int j = 0;
    int k = 5;
    int minimo = minimo_iterativo(numeri, j, k);
    printf("Il minimo dell'array e': %.2d\n", minimo);
}