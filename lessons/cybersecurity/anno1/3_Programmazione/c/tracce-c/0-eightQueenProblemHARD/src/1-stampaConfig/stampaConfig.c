#include <stdio.h>
#include <stdlib.h>

// Funzione per stampare la configurazione attuale delle 8 regine
void stampaConfig(const int regine[8]) {

    // Stampa intestazione con indici colonna
    printf("    ");
    for (int c = 0; c < 8; c++) printf("%d   ", c);
    printf("← indici colonne\n");

    // Linea superiore
    printf("  ---------------------------------\n");

    // Stampa scacchiera
    for (int r = 0; r < 8; r++) {
        int rigaReale = 8 - r;   // 8..1 per lato sinistro
        printf("%d |", rigaReale);

        for (int c = 0; c < 8; c++) {
            if (regine[c] == r) {
                printf(" Q |");  // Regina
            } else {
                // Alternanza colori: angolo in alto sinistra bianco
                if ((r + c) % 2 == 0)
                    printf(" - |");  // Casella bianca
                else
                    printf(" * |");  // Casella nera
            }
        }

        printf(" %d\n", r); // Indice riga 0-based a destra
    }

    // Linea inferiore
    printf("  ---------------------------------\n");

    // Lettere colonne
    printf("    a   b   c   d   e   f   g   h\n");
}


// Esempio di utilizzo
int main(void) {
    // Configurazione valida: [7, 4, 0, 3, 6, 1, 5, 2]
    int regine[8] = {7, 4, 0, 3, 6, 1, 5, 2};

    stampaConfig(regine);

    return 0;
}