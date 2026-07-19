#include <stdio.h>

#define N 8

/*
 * Convenzione: regine[colonna] contiene la riga interna 0..7, ottenuta
 * sottraendo 1 alla riga scacchistica 1..8. La stampa procede quindi dalla
 * riga interna 7 (riga 8, in alto) alla riga interna 0 (riga 1, in basso).
 * La funzione visualizza qualsiasi stato: non ne verifica i conflitti.
 */
void stampaConfig(const int regine[N]) {
    fputs("    ", stdout);
    for (int colonna = 0; colonna < N; ++colonna) {
        printf("%d   ", colonna);
    }
    puts("← indici colonne");
    puts("  ---------------------------------");

    for (int riga = N - 1; riga >= 0; --riga) {
        printf("%d |", riga + 1);
        for (int colonna = 0; colonna < N; ++colonna) {
            if (regine[colonna] == riga) {
                fputs(" Q |", stdout);
            } else {
                int rigaVisiva = N - 1 - riga;
                fputs((rigaVisiva + colonna) % 2 == 0 ? " - |" : " * |", stdout);
            }
        }
        printf(" %d\n", riga);
    }

    puts("  ---------------------------------");
    puts("    a   b   c   d   e   f   g   h");
}

int main(void) {
    // Figura 1 del PDF: stato 1-based {8,4,1,3,6,2,7,5}.
    const int regine[N] = {7, 3, 0, 2, 5, 1, 6, 4};
    stampaConfig(regine);
    return 0;
}
