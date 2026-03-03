// Non andiamo ancora a studiare gli algoritmi di ordinamento in quanto sono materiale "avanzato".
// Però, per il calcolo del tempo di esecuzione è bene fare un esempio con uno di questi algoritmi...
// Selection Sort
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
void selectionSort(int* arrayDiNumeri, int dimensioneArray) {
    // numero di elementi da ordinare
    int n = dimensioneArray;
    // Scorro l'array dal primo al penultimo elemento (l'ultimo sarà già ordinato dopo che tutti gli altri lo saranno)
    for(int j = 0; j < n-1; j++) {

        // Cerco il minimo tra gli elementi da j = 0 a j = n-1
        // per farlo, assumo che il minimo sia l'elemento in posizione j
        int min = j;

        // Scorro di nuovo l'array, stavolta dalla posizione j+1, ovvero la successiva rispetto
        // al minimo supposto, fino alla fine dell'array
        for(int i = j+1; i < n; i++) {
            // Se trovo un elemento più piccolo del minimo supposto, aggiorno il minimo
            if (arrayDiNumeri[i] < arrayDiNumeri[min]) {
                min = i;
            }
        }
        // Alla fine ciclo interno, abbiamo trovato il minimo tra gli elementi non ordinati:
        // Se è diverso dall'iniziale, ovvero l'elemento in posizione j, scambio i due elementi
        // altrimenti lascio tutto com'è
        if (min != j) {
            int temp = arrayDiNumeri[j];
            arrayDiNumeri[j] = arrayDiNumeri[min];
            arrayDiNumeri[min] = temp;
        }
    }

}

// Test:
int main(void) {

    int A[] = {64, 25, 12, 22, 11};
    int dimensioneArray = sizeof(A)/sizeof(A[0]);
    selectionSort(A, dimensioneArray); // in C il nome dell'array è un puntatore al primo elemento:
    printf("Array ordinato: \n");
    for (int i = 0; i < dimensioneArray; i++) {
        printf("%d ", A[i]);
    }
    printf("Press any key to continue...\n");
    getchar();
    return 0;
}