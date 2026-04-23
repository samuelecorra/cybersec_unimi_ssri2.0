// SECONDO APPROCCIO (efficiente O(N^2))
#include <stdio.h>

#define N 3  // Dimensione della matrice (modificabile fino a 20)

int main(void) {

    int matrice1[N][N] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    int matrice2[N][N] = {0};

    // Le diagonali “\” hanno indici con differenza costante (riga - colonna)
    // Le diagonali “/” hanno indici con somma costante (riga + colonna)

    // Per memorizzare le somme delle diagonali:
    int sommaDiscendente[2 * N - 1] = {0}; // diagonali tipo "\" 5, di cui le due agli angoli degenerano ai singoli elementi
    int sommaAscendente[2 * N - 1]  = {0}; // diagonali tipo "/" 5, idem come sopra

    // --- Fase 1: calcolo di tutte le somme delle diagonali ---
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            // Calcoliamo i rapporti di tale cella per identificare le diagonali a cui appartiene
            int rappDisc = (riga - colonna) + (N - 1); // aumentiamo di (N-1) per evitare indici negativi,
            // questo non cambia il fatto che i rapporti rimangono univoci quindi non ci sono problemi
            int rappAsc  = riga + colonna;

            // Aggiungiamo il valore della cella corrente alle somme delle diagonali corrispondenti
            sommaDiscendente[rappDisc] += matrice1[riga][colonna];
            sommaAscendente[rappAsc]  += matrice1[riga][colonna];
        }
    }

    // --- Fase 2: costruzione della nuova matrice ---
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            // Ricostruiamo la stessa logica per trovare le diagonali di ogni cella
            int rappDisc = (riga - colonna) + (N - 1);
            int rappAsc  = riga + colonna;
            // Recuperiamo le somme calcolate in precedenza che però includono due volte l’elemento stesso
            int sommaDiagDisc = sommaDiscendente[rappDisc];
            int sommaDiagAsc  = sommaAscendente[rappAsc];
            // Calcoliamo il valore finale escludendo due volte l’elemento stesso
            matrice2[riga][colonna] = sommaDiagDisc + sommaDiagAsc - 2 * matrice1[riga][colonna];
        }
    }

    // --- Stampa dei risultati ---
    printf("Matrice originale:\n");
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            printf("%d\t", matrice1[riga][colonna]);
        }
        printf("\n");
    }

    printf("\nMatrice risultante:\n");
    for (int riga = 0; riga < N; ++riga) {
        for (int colonna = 0; colonna < N; ++colonna) {
            printf("%d\t", matrice2[riga][colonna]);
        }
        printf("\n");
    }

    return 0;
}
