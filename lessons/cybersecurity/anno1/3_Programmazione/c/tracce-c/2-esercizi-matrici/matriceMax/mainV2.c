#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// =============================== FUNZIONE DI ELABORAZIONE ============================================

int** ricavaMatriceMaxAdiacenti(int N, int matriceOriginale[N][N]) {
    
    // Creiamo la matrice di output
    int** matriceMax = malloc(N * sizeof(int*)); // Allochiamo le righe
    for (int i = 0; i < N; i++) {
        matriceMax[i] = malloc(N * sizeof(int)); // Allochiamo le colonne per ogni riga
    }

    // Definiamo gli spostamenti per gli 8 adiacenti: così facendo escludiamo direttamente
    // l'elemento centrale e rendiamo il codice più leggibile
    int spostamenti[8][2] = {
        {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
        // Sopra                    Laterali         Sotto

    for (int i = 0; i < N; i++) { // Per ogni elemento della matrice originale
        for (int j = 0; j < N; j++) {

            int maxVal = -2147483648; // Valore minimo "placeholder" per inizializzare il massimo
            for (int k = 0; k < 8; k++) { // Scorriamo gli 8 possibili adiacenti
                int newRow = i + spostamenti[k][0]; // Calcoliamo la nuova riga
                int newCol = j + spostamenti[k][1]; // Calcoliamo la nuova colonna
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) { // Controlliamo che gli indici newRow e newCol siano validi
                    if (matriceOriginale[newRow][newCol] > maxVal) { // allora controlliamo se tale adiacente è > dell'ultimo massimo trovato
                        maxVal = matriceOriginale[newRow][newCol]; // Aggiorniamo il massimo
                    } // Altrimenti non aggiorniamo
                } // Altrimenti non è un adiacente valido e non facciamo nulla
            } // Dopo aver controllato tutti gli adiacenti, assegniamo il massimo trovato alla matrice di output
            matriceMax[i][j] = maxVal;
        }
    }
    return matriceMax;
}

// Testiamo:
int main() {

    int n = 4;
    int matriceOriginale[4][4] = {
        {4, 5, 6, 7},
        {8, 2, 4, 5},
        {6, 7, 8, 1},
        {2, 8, 4, 6}
    };

    printf("Matrice originale (copia):\n");     // Stampiamo la matrice originale
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("%d ", matriceOriginale[i][j]);
        }
        printf("\n");
    }
    printf("\n");

    int** matriceTrasformata = ricavaMatriceMaxAdiacenti(n, matriceOriginale); // Otteniamo la matrice trasformata
    printf("Matrice trasformata (massimi adiacenti):\n"); // Stampiamo la matrice trasformata
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("%d ", matriceTrasformata[i][j]);
        }
        printf("\n");
    }

    for (int i = 0; i < n; i++) free(matriceTrasformata[i]); // Liberiamo la memoria allocata per le righe
    free(matriceTrasformata); // Liberiamo la memoria allocata per il puntatore alle righe

    return 0;
}