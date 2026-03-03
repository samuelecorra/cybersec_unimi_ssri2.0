
// In seconda battuta, ci domandiamo se sia possibile trovare un altro modo
// per calcolare il minimo di un array.

// A una mente più attenta non sfuggirà che la ricorsione è la strategia
// duale dell'iterazione.

// Infatti, se supponiamo che l'array non sia vuoto, possiamo riconoscere
// un caso base e possiamo anche riconoscere un caso ricorsivo.

#include <stdio.h>

int minimo_ricorsivo(int* array, int j, int k) {
    
    int min;
    // Caso base: se l'array ha un solo elemento...
    if (j == k) {
        min = array[j];
    } else {
        // Caso ricorsivo: 
        min = minimo_ricorsivo(array, j+1, k);
        min = array[j] < min ? array[j] : min;
    }
    return min;
}

// Ora possiamo utilizzare la funzione appena creata:

int main() {
    int numeri[6] = {20, 46, 92, 15, 13, 2004};
    int j = 0;
    int k = 5;
    int minimo = minimo_ricorsivo(numeri, j, k);
    printf("Il minimo dell'array e': %.2d\n", minimo);
}