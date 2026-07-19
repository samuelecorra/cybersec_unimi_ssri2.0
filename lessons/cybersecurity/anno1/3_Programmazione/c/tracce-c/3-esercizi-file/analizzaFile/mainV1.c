#include <stdbool.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Analizza un file di testo byte per byte. Con UTF-8, un carattere non ASCII
 * può occupare più byte e viene quindi contato più volte. I risultati sono
 * restituiti tramite i quattro parametri di output; il valore di ritorno
 * distingue il successo dall'impossibilita di aprire o leggere il file.
 */
bool analizzaFile(const char *nomeFile,
                  int *caratteri,
                  int *parole,
                  int *frasi,
                  int *paragrafi)
{
    *caratteri = 0;
    *parole = 0;
    *frasi = 0;
    *paragrafi = 0;

    FILE *file = fopen(nomeFile, "r");
    if (file == NULL) {
        fprintf(stderr, "Errore nell'apertura del file \"%s\".\n", nomeFile);
        return false;
    }

    bool in_parola = false;
    bool riga_non_vuota = false;
    bool riga_iniziata = false;
    bool punto_precedente = false;
    int lunghezza_riga = 0;
    bool riga_segnalata = false;
    int ch;

    while ((ch = fgetc(file)) != EOF) {
        unsigned char carattere = (unsigned char)ch;

        if (carattere == '\n') {
            if (riga_non_vuota) {
                (*paragrafi)++;
            }
            in_parola = false;
            riga_non_vuota = false;
            riga_iniziata = false;
            punto_precedente = false;
            lunghezza_riga = 0;
            riga_segnalata = false;
            continue;
        }

        /* Nei file Windows '\r\n', '\r' non appartiene al testo da contare. */
        if (carattere == '\r') {
            continue;
        }

        riga_iniziata = true;
        (*caratteri)++;
        lunghezza_riga++;
        if (lunghezza_riga > 300 && !riga_segnalata) {
            fprintf(stderr,
                    "[Avviso] Rilevata una riga oltre i 300 caratteri.\n");
            riga_segnalata = true;
        }

        if (!isspace(carattere)) {
            riga_non_vuota = true;
        }

        if (carattere == '.') {
            if (!punto_precedente) {
                (*frasi)++;
            }
            punto_precedente = true;
        } else {
            punto_precedente = false;
        }

        if (isspace(carattere)) {
            in_parola = false;
        } else if (!in_parola) {
            (*parole)++;
            in_parola = true;
        }
    }

    /* Una riga finale priva di newline deve comunque essere considerata. */
    if (riga_iniziata && riga_non_vuota) {
        (*paragrafi)++;
    }

    if (ferror(file)) {
        fprintf(stderr, "Errore durante la lettura del file \"%s\".\n", nomeFile);
        fclose(file);
        return false;
    }

    if (fclose(file) == EOF) {
        fprintf(stderr, "Errore durante la chiusura del file \"%s\".\n", nomeFile);
        return false;
    }
    return true;
}

int main(void)
{
    char nomeFile[256];
    int caratteri;
    int parole;
    int frasi;
    int paragrafi;

    printf("Inserisci nome file (con .txt): ");
    if (fgets(nomeFile, sizeof nomeFile, stdin) == NULL) {
        fprintf(stderr, "Errore in input.\n");
        return EXIT_FAILURE;
    }
    nomeFile[strcspn(nomeFile, "\n")] = '\0';

    if (!analizzaFile(nomeFile, &caratteri, &parole, &frasi, &paragrafi)) {
        return EXIT_FAILURE;
    }

    printf("caratteri: %d\n", caratteri);
    printf("parole:    %d\n", parole);
    printf("frasi:     %d\n", frasi);
    printf("paragrafi: %d\n", paragrafi);
    return EXIT_SUCCESS;
}
