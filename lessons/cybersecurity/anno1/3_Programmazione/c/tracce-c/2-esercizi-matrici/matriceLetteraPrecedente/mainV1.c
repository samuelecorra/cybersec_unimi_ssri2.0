#include <stdio.h>
#include <stdlib.h> // per allocazione dinamica e gestione errori
#include <ctype.h> // per isalpha e tolower

char** genMatrix(int dim) { // Funzione di generazione per evitare define
    char** m = (char**)malloc(dim * sizeof(char*)); // alloco righe
    if (m == NULL) {
        fprintf(stderr, "Errore di allocazione memoria per righe\n");
        exit(EXIT_FAILURE);
    }

    for (int row = 0; row < dim; row++) {
        m[row] = (char*)malloc(dim * sizeof(char)); // alloco colonne
        if (!m[row]) { // forma compatta equivalente di (m[row] == NULL)
            fprintf(stderr, "Errore di allocazione memoria per colonne\n");
            exit(EXIT_FAILURE);
        }
    }

    printf("Matrice %dx%d generata con successo.\n", dim, dim);
    printf("Premi r/R per usarla come matrice risultante (vuota)\n"
           "Oppure qualsiasi altro tasto usarla come iniziale da popolare: ");
    char choice;
    scanf(" %c", &choice);
    if (choice == 'r' || choice == 'R') {
        return m; // restituisco la matrice vuota
    }

    for(int row = 0; row < dim; row++) {
        for(int col = 0; col < dim; col++) { // Popoliamo
            printf("Inserisci la lettera [%d][%d]: ", row, col);
            scanf(" %c", &m[row][col]); // lo spazio prima di %c serve a ignorare eventuali newline o spazi
            while(!isalpha(m[row][col])) {
                printf("InvalidInput. Inserisci la lettera [%d][%d]: ", row, col);
                scanf(" %c", &m[row][col]);
            }
        }
    }
    return m;
}

char** processMatrix(char** originale, int dim) {
    char** risultante = genMatrix(dim); // premi r/R per averla vuota

    const int directions[8][2] = {
        {-1,-1},{-1,0},{-1,1},
        { 0,-1},        { 0,1}, // spostamenti per raggiungere tutti i vicini
        { 1,-1},{ 1,0},{ 1,1}
    };

    for (int row = 0; row < dim; row++) {
        for (int col = 0; col < dim; col++) {

            char bestLower = '{';      // '{' è 1 oltre 'z' in ASCII => sentinella
            char bestOrig  = '?';      // placeholder

            for (int d = 0; d < 8; d++) { // Dunque complessità totale O(8 * n^2) = O(n^2)
                int nr = row + directions[d][0];
                int nc = col + directions[d][1];
                if (nr < 0 || nr >= dim || nc < 0 || nc >= dim) continue;

                char candOrig  = originale[nr][nc];
                char candLower = (char)tolower((unsigned char)candOrig);

                if (candLower < bestLower) {
                    bestLower = candLower;
                    bestOrig  = candOrig;  // mantieni il case originale
                }
            }

            risultante[row][col] = bestOrig;
        }
    }
    return risultante;
}


// Testiamo:
int main() {
    int dim;
    printf("Inserisci la dimensione della matrice quadrata: ");
    scanf("%d", &dim);

    char** originale = genMatrix(dim);
    char** risultante = processMatrix(originale, dim);

    printf("Matrice originale:\n");
    for(int row = 0; row < dim; row++) {
        for(int col = 0; col < dim; col++) {
            printf("%c ", originale[row][col]);
        }
        printf("\n");
    }

    printf("Matrice risultante:\n");
    for(int row = 0; row < dim; row++) {
        for(int col = 0; col < dim; col++) {
            printf("%c ", risultante[row][col]);
        }
        printf("\n");
    }

    for(int row = 0; row < dim; row++) { // Liberiamo la memoria allocata
        free(originale[row]);
        free(risultante[row]); // libero ogni riga => ergo anche le colonne
    }
    free(originale); 
    free(risultante); // libero il puntatore alle righe

    return 0;
}