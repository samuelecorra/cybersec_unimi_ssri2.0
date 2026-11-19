#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * KMP della docente:
 * - indicizzazione logica 1-based;
 * - P[j] logico corrisponde a P[j - 1] nel vero array C;
 * - back[1] ... back[m];
 * - j/h usati per costruire la tabella di fallback.
 */

void calcola_back(const char *P, int *back, int m)
{
    int j, h;

    back[1] = 0;   // Se fallisco sul primo carattere non ho alcun prefisso da riutilizzare.
    j = 1;         // Posizione del pattern per cui sto costruendo back.
    h = 0;         // Possibile posizione di fallback / bordo candidato.

    while (j < m) {   // j < m evita di accedere oltre P[m].

        // Se non ho più candidati (h=0), oppure il bordo corrente può essere esteso:
        if (h == 0 || P[j - 1] == P[h - 1]) {

            j++;
            h++;

            // Ottimizzazione nextval: se P[j] == P[h], saltare a back[h] evita un confronto già noto inutile.
            back[j] = (P[j - 1] == P[h - 1]) ? back[h] : h;
        }

        else {
            h = back[h];   // Il candidato h non funziona: provo un bordo più corto senza cambiare j.
        }
    }
}


int KMP(const char *P, const char *T, int n, int m)
{
    int i, j;
    int *back;

    // Il pattern vuoto si trova all’inizio del testo (posizione 1, 1-based).
    if (m == 0) {
        return 1;
    }

    // m+1 celle perché usiamo realmente back[1] ... back[m].
    back = malloc((m + 1) * sizeof(int));

    if (back == NULL) {
        fprintf(stderr, "Errore: impossibile allocare il vettore back.\n");
        exit(EXIT_FAILURE);
    }

    calcola_back(P, back, m);   // Preprocessing del pattern: costruzione della tabella back.

    i = 1;   // Posizione corrente nel testo T.
    j = 1;   // Posizione corrente nel pattern P.

    while (i <= n && j <= m) {

        // j=0 è la sentinella; altrimenti confronto T[i] con P[j].
        if (j == 0 || T[i - 1] == P[j - 1]) {
            i++;
            j++;
        }

        else {
            j = back[j];   // Mismatch: i NON retrocede; cambia soltanto la posizione nel pattern.
        }
    }

    free(back);

    // Se j>m il pattern è stato trovato e i-m è la posizione iniziale 1-based; altrimenti restituisce n+1.
    return (j > m ? i - m : i);
}


/* Main solo per testare l'algoritmo: non fa parte del KMP della docente. */
int main(void)
{
    const char *T = "ABCABCABD";
    const char *P = "ABCABD";

    int n = (int) strlen(T);
    int m = (int) strlen(P);

    int posizione = KMP(P, T, n, m);

    if (posizione <= n) {
        printf("Pattern trovato in posizione %d (indicizzazione 1-based).\n", posizione);
        printf("In un normale array C sarebbe indice %d (0-based).\n", posizione - 1);
    }
    else {
        printf("Pattern non trovato.\n");
    }

    return 0;
}