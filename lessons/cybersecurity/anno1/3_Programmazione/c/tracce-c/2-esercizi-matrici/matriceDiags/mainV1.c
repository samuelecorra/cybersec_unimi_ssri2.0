// PRIMO APPROCCIO (inefficiente, O(N^4), ma semplice da capire)
#include <stdio.h>

#define N 3 // Cambiare il valore di N per matrici di dimensioni diverse
// il programma gira a prescindere dal valore di N!

int main() {

    int m1[N][N] = { // Ci aspettiamo alla fine di vedere la matrice M2:
        {1, 2, 3},    // {14, 10, 12},
        {4, 5, 6},    // {10, 20, 10},
        {7, 8, 9}     // {8,  10, 6}
    };

    printf("Matrice originale:\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%d\t", m1[i][j]);
        }
        printf("\n");
    }

    int m2[N][N] = {0}; // inizializziamo tutta la matrice finale a 0;

    for (int i = 0; i < N; i++) {     // Iniziamo a scorrere ogni elemento della matrice originale:
        for (int j = 0; j < N; j++) {
            
        // Su una qualsiasi diagonale discendente (da sx a dx) di una matrice, è costante il rapporto i-j:
        // Analogamente, su una qualsiasi diagonale ascendente (da sx a dx) di una matrice, è costante i+j.

        // ESEMPIO: abbiamo la generica matrice in cui evidenziamo gli indici delle celle:
        //    | (0,0) (0,1) (0,2) |
        //    | (1,0) (1,1) (1,2) |
        //    | (2,0) (2,1) (2,2) |

        // Si nota che sulla diagonale discendente principale è costante i-j = 0,
        // La diagonale discendente che passa per m[1][0] e m[2][1] ha rapporto i-j = 1,
        // La diagonale discendente che passa per m[0,1] e m[1][2] ha rapporto i-j = -1,

        // la diagonale ascendente principale ha i+j = 2,
        // la diagonale ascendente che passa per m[1][0] e m[0][1] ha rapporto i+j = 1,
        // la diagonale ascendente che passa per m[2][1] e m[1][2] ha rapporto i+j = 3,


            // Alla luce di ciò, per ogni cella controlliamo se le altre rispettano tali rapporti:
            // Se sì, sommiamo il loro valore a m2[i][j], altrimenti no, escludendo ovviamente la cella stessa (i,j).
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {

                    // Se l'indice è diverso dalla cella stessa E se vale uno dei due rapporti:
                    if ((x != i || y != j) && (x - y == i - j || x + y == i + j)) {
                        m2[i][j] += m1[x][y]; // aggiorniamo il valore della matrice finale
                    }
                }
            }
        }
    }

    // Stampa della matrice risultante
    printf("\nMatrice finale:\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%d\t", m2[i][j]);
        }
        printf("\n");
    }

    return 0;
}