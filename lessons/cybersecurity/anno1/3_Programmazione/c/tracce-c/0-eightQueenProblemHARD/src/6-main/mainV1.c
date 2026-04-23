// ====================== INIZIO: FUNZIONI GIA' PRESENTI (copiate 1:1) ======================
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <ctype.h>

#define N 8

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


// Potremmo anche solo farci ritornare l'euristica, ma per chiarezza espandiamo una funzione a parte...
int verifica(const int stato[N]) {
    int conflitti = 0;

    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {

            // Conflitto su stessa riga
            if (stato[i] == stato[j])
                conflitti++;

            // Conflitto su diagonale ↘️ o ↙️
            if (abs(stato[i] - stato[j]) == abs(i - j))
                conflitti++;
        }
    }

    return conflitti;
}


int euristica(const int stato[N]) {
    int costo = 0;

    for (int i = 0; i < N; i++) { // Implicitamente stiamo calcolando la combinazione semplice di due elementi
        for (int j = i + 1; j < N; j++) { // ma per tutte ed 8 in un colpo solo ed intuitivo

            if (stato[i] == stato[j]) // Stessa riga
                costo++;

            if (abs(stato[i] - stato[j]) == abs(i - j)) // Diagonale ↘️ o ↙️
                costo++;
        }
    }

    return costo; // 0 se soluzione altrimenti costo = numero di conflitti
} 


int caricaConfig(const char *nome_file, int stato[N]) {
    FILE *fp = fopen(nome_file, "r");
    if (fp == NULL) {
        printf("Errore: impossibile aprire il file '%s'\n", nome_file);
        return 0;
    }

    for (int i = 0; i < N; i++) {
        if (fscanf(fp, "%d", &stato[i]) != 1) {
            printf("Errore: formato non valido nel file '%s'\n", nome_file);
            fclose(fp);
            return 0;
        }
    }

    fclose(fp);
    return 1;
}


// ------------------------
// Helpers di conversione
// ------------------------
static int col_from_char(char c) {
    c = (char)tolower((unsigned char)c);
    if (c < 'a' || c > 'h') return -1;
    return (int)(c - 'a'); // 0..7
}

static int riga_from_scacchi(int r) {
    if (r < 1 || r > 8) return -1;
    return r - 1; // 1..8 -> 0..7
}

// ---------------------------------------------
// Variante indicizzata (0-based, 0-based)
// ---------------------------------------------
int cerca_idx(const int stato[N], int col_idx, int riga_idx) {
    if (col_idx < 0 || col_idx >= N) return -1; // colonna fuori range
    if (riga_idx < 0 || riga_idx >= N) return -2; // riga fuori range

    int copia[N];
    for (int c = 0; c < N; c++) copia[c] = stato[c];

    // spostamento ipotetico della sola regina in colonna col_idx
    copia[col_idx] = riga_idx;

    // valuta l'euristica della configurazione ipotetica
    return euristica(copia);
}

// ------------------------------------------------------
// Interfaccia richiesta dall'esercizio (a..h, 1..8)
// ------------------------------------------------------
int cerca(const int stato[N], char col_scacchi, int riga_scacchi) {
    int c = col_from_char(col_scacchi);
    if (c < 0) return -1;

    int r = riga_from_scacchi(riga_scacchi);
    if (r < 0) return -2;

    return cerca_idx(stato, c, r);
}
// ====================== FINE FUNZIONI ======================



// ====================== MAIN “N REGINE” (menu completo) ======================
#include <string.h>

static void flush_line(void) {
    int ch;
    while ((ch = getchar()) != '\n' && ch != EOF) { /* scarta */ }
}

static int leggi_intero_sicuro(const char *prompt, int *out) {
    char buf[128];
    for (;;) {
        if (prompt) printf("%s", prompt);
        if (!fgets(buf, sizeof buf, stdin)) return 0;
        char *p = buf;
        // salta spazi
        while (*p==' '||*p=='\t') p++;
        // prova a leggere un int
        int v; char extra;
        if (sscanf(p, "%d %c", &v, &extra) == 1) {
            *out = v;
            return 1;
        }
        printf("Input non valido. Riprova.\n");
    }
}

// Parsing mosse: accetta "g7", "G7", "g 7", "  g   7  "
static int parse_mossa(const char *line, char *colChar, int *rigaScacchi) {
    char c = 0;
    int r = -1;

    // tenta formato compatto: lettera + numero
    if (sscanf(line, " %c %d", &c, &r) != 2) return 0;

    // validazione base qui; la validazione finale la fa cerca()
    *colChar = c;
    *rigaScacchi = r;
    return 1;
}

static void stampa_menu(void) {
    printf("\n");
    printf("1) Stampa stato scacchiera\n");
    printf("2) Verifica soluzione\n");
    printf("3) Costo euristico\n");
    printf("4) Carica stato da file\n");
    printf("5) Valuta una mossa (senza modificare)\n");
    printf("6) Esegui una mossa (modifica lo stato)\n");
    printf("0) Esci\n");
    printf("> ");
}

int main(void) {
    // Stato iniziale: una soluzione classica (0-based per righe)
    int stato[N] = {7, 4, 0, 3, 6, 1, 5, 2};

    // “Partenza” come da traccia: leggi un numero e prosegui
    int dummy = 0;
    leggi_intero_sicuro("N-regine (N=8). Inserisci un numero qualsiasi per iniziare: ", &dummy);

    for (;;) {
        stampa_menu();
        int scelta = -1;
        if (!leggi_intero_sicuro(NULL, &scelta)) break;

        if (scelta == 0) {
            printf("Bye!\n");
            break;
        }

        switch (scelta) {
            case 1: {
                printf("\n");
                stampaConfig(stato);
                break;
            }
            case 2: {
                int conf = verifica(stato);
                if (conf == 0) printf("Stato: SOLUZIONE VALIDA ✅ (conflitti = 0)\n");
                else            printf("Stato: NON SOLUZIONE ❌ (conflitti = %d)\n", conf);
                break;
            }
            case 3: {
                int cost = euristica(stato);
                printf("Costo euristico: %d\n", cost);
                break;
            }
            case 4: {
                char path[512];
                printf("Percorso file (con N=8 interi 0..7): ");
                if (!fgets(path, sizeof path, stdin)) { puts("Input interrotto."); break; }
                // rimuovi newline
                size_t L = strlen(path);
                if (L && path[L-1]=='\n') path[L-1]=0;

                int tmp[N];
                if (caricaConfig(path, tmp)) {
                    // opzionale: valida range qui (caricaConfig già legge 8 numeri)
                    for (int i=0;i<N;i++) {
                        if (tmp[i] < 0 || tmp[i] >= N) {
                            printf("Valore fuori range alla posizione %d. Stato non aggiornato.\n", i);
                            goto dopo_carica;
                        }
                    }
                    for (int i=0;i<N;i++) stato[i]=tmp[i];
                    printf("Stato aggiornato da file.\n");
                }
dopo_carica:
                break;
            }
            case 5: {
                char buf[128], col; int riga;
                printf("Mossa da VALUTARE (es. g7 oppure \"g 7\"): ");
                if (!fgets(buf, sizeof buf, stdin)) { puts("Input interrotto."); break; }
                if (!parse_mossa(buf, &col, &riga)) { puts("Formato mossa non valido."); break; }

                int costo = cerca(stato, col, riga);
                if (costo < 0) {
                    if (costo == -1) puts("Colonna fuori range (usa a..h).");
                    else              puts("Riga fuori range (usa 1..8).");
                } else {
                    printf("Costo ipotetico dopo la mossa %c%d: %d\n",
                           (char)tolower((unsigned char)col), riga, costo);
                }
                break;
            }
            case 6: {
                char buf[128], col; int riga;
                printf("Mossa da ESEGUIRE (es. a8 oppure \"a 8\"): ");
                if (!fgets(buf, sizeof buf, stdin)) { puts("Input interrotto."); break; }
                if (!parse_mossa(buf, &col, &riga)) { puts("Formato mossa non valido."); break; }

                int cIdx = col_from_char(col);
                int rIdx = riga_from_scacchi(riga);
                if (cIdx < 0) { puts("Colonna fuori range (usa a..h)."); break; }
                if (rIdx < 0) { puts("Riga fuori range (usa 1..8).");   break; }

                stato[cIdx] = rIdx; // esegui la mossa
                printf("Mossa %c%d eseguita.\n", (char)tolower((unsigned char)col), riga);
                break;
            }
            default:
                printf("Scelta non valida. Inserisci un numero tra 0 e 6.\n");
        }
    }

    return 0;
}
// ====================== FINE: MAIN “N REGINE” ======================