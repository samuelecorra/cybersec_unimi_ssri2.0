#include <stdio.h>
#define N 4

int main() {

    int matrice[N][N];
    // ATTENZIONE: il tipo float serve perché le medie saranno quasi sicuramente
    // numeri con la virgola!
    float matriceMedie[N][N];

    // Popoliamo la matrice da cui iniziare:
    printf("Inserisci i valori della matrice %dx%d\n", N, N);
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            printf("Elemento [%d][%d]:\n> ", i, j);
            scanf("%d", &matrice[i][j]);
        }
    }

    // Ora dobbiamo, per ciascun elemento che scorriamo, "visitare" i suoi vicini e
    // sommarli per poter fare la media. Ovviamente serve una variabile di conteggio
    // che tenga traccia di quanti vicini validi ci sono (l'elemento centrale ha 4 vicini,
    // quelli sui bordi ne hanno 3, quelli negli angoli ne hanno 2):
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            int somma = 0;
            int conteggio = 0;
            for (int x=i-1; x<=i+1; x++) { // vediamo chi c'è a sinistra/destra
                for (int y=j-1; y<=j+1; y++) { // vediamo chi c'è sopra/sotto
                    if (x>=0 && x<N && y>=0 && y<N) { // controllo di validità degli indici
                        if (x!=i || y!=j) { // escludo l'elemento stesso, ovvero basta che almeno
                            // uno dei due indici sia diverso
                            somma += matrice[x][y];
                            conteggio++;
                        }
                    }
                }
            }
            // Alla fine del controllo sui vicini, popoliamo la matrice delle medie, CASTANDO!!!
            matriceMedie[i][j] = (float)somma / conteggio; // casto float per divisione
        }
    }

    printf("Le due matrici affiancate:\n");

    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            printf("%d ", matrice[i][j]);
        }
        printf("   ");
        for (int j=0; j<N; j++) {
            printf("%.2f ", matriceMedie[i][j]);
        }
        printf("\n");
    }

    return 0;
}