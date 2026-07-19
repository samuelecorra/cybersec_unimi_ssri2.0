#include <ctype.h>
#include <errno.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef N
#define N 8
#endif

#if N < 1 || N > 26
#error "N deve essere compreso tra 1 e 26"
#endif

static int euristica(const int stato[N]) {
    int costo = 0;
    for (int prima = 0; prima < N; ++prima) {
        for (int seconda = prima + 1; seconda < N; ++seconda) {
            if (stato[prima] == stato[seconda]
                || abs(stato[prima] - stato[seconda]) == seconda - prima) {
                ++costo;
            }
        }
    }
    return costo;
}

static int verifica(const int stato[N]) {
    return euristica(stato);
}

static void stampaConfig(const int stato[N]) {
    fputs("    ", stdout);
    for (int colonna = 0; colonna < N; ++colonna) printf("%c   ", 'a' + colonna);
    putchar('\n');

    for (int riga = N - 1; riga >= 0; --riga) {
        printf("%2d |", riga + 1);
        for (int colonna = 0; colonna < N; ++colonna) {
            if (stato[colonna] == riga) fputs(" Q |", stdout);
            else {
                int rigaVisiva = N - 1 - riga;
                fputs((rigaVisiva + colonna) % 2 == 0 ? " - |" : " * |", stdout);
            }
        }
        putchar('\n');
    }
}

static int caricaConfig(const char *nomeFile, int stato[N]) {
    FILE *file = fopen(nomeFile, "r");
    if (file == NULL) {
        perror(nomeFile);
        return 0;
    }

    int temporaneo[N];
    int valido = 1;
    for (int i = 0; i < N; ++i) {
        if (fscanf(file, "%d", &temporaneo[i]) != 1
            || temporaneo[i] < 0 || temporaneo[i] >= N) {
            valido = 0;
            break;
        }
    }
    char extra = '\0';
    if (valido && fscanf(file, " %c", &extra) == 1) valido = 0;
    int erroreLettura = ferror(file);
    int erroreChiusura = fclose(file) == EOF;
    if (erroreLettura || erroreChiusura) valido = 0;

    if (!valido) {
        fprintf(stderr, "Configurazione non valida in '%s'.\n", nomeFile);
        return 0;
    }
    memcpy(stato, temporaneo, sizeof temporaneo);
    return 1;
}

static int indiceColonna(char colonna) {
    unsigned char valore = (unsigned char)tolower((unsigned char)colonna);
    return valore >= 'a' && valore < 'a' + N ? (int)(valore - 'a') : -1;
}

static int indiceRiga(int rigaScacchistica) {
    return rigaScacchistica >= 1 && rigaScacchistica <= N
               ? rigaScacchistica - 1
               : -1;
}

static int cerca(const int stato[N], char colonna, int rigaScacchistica) {
    int indiceCol = indiceColonna(colonna);
    if (indiceCol < 0) return -1;
    int indiceRig = indiceRiga(rigaScacchistica);
    if (indiceRig < 0) return -2;

    int copia[N];
    memcpy(copia, stato, sizeof copia);
    copia[indiceCol] = indiceRig;
    return euristica(copia);
}

static int leggiRiga(char buffer[], size_t capacità) {
    if (fgets(buffer, (int)capacità, stdin) == NULL) return 0;
    if (strchr(buffer, '\n') != NULL) return 1;

    int carattere = 0;
    while ((carattere = getchar()) != '\n' && carattere != EOF) { }
    return 0; // riga troncata
}

static int analizzaIntero(const char *testo, int *risultato) {
    errno = 0;
    char *fine = NULL;
    long valore = strtol(testo, &fine, 10);
    if (fine == testo || errno == ERANGE || valore < INT_MIN || valore > INT_MAX) return 0;
    while (isspace((unsigned char)*fine)) ++fine;
    if (*fine != '\0') return 0;
    *risultato = (int)valore;
    return 1;
}

static int leggiIntero(const char *richiesta, int *risultato) {
    char buffer[128];
    for (;;) {
        if (richiesta != NULL) fputs(richiesta, stdout);
        if (!leggiRiga(buffer, sizeof buffer)) return 0;
        if (analizzaIntero(buffer, risultato)) return 1;
        puts("Input non valido. Riprova.");
    }
}

static int analizzaMossa(const char *testo, char *colonna, int *riga) {
    while (isspace((unsigned char)*testo)) ++testo;
    if (*testo == '\0') return 0;
    *colonna = *testo++;
    while (isspace((unsigned char)*testo)) ++testo;

    errno = 0;
    char *fine = NULL;
    long valore = strtol(testo, &fine, 10);
    if (fine == testo || errno == ERANGE || valore < INT_MIN || valore > INT_MAX) return 0;
    while (isspace((unsigned char)*fine)) ++fine;
    if (*fine != '\0') return 0;
    *riga = (int)valore;
    return 1;
}

static void stampaMenu(void) {
    puts("\n1) Stampa stato scacchiera\n"
         "2) Verifica soluzione\n"
         "3) Costo euristico\n"
         "4) Carica stato da file\n"
         "5) Valuta una mossa (senza modificare)\n"
         "6) Esegui una mossa (modifica lo stato)\n"
         "0) Esci");
}

int main(void) {
    int stato[N];
#if N == 8
    const int soluzione[N] = {7, 3, 0, 2, 5, 1, 6, 4};
    memcpy(stato, soluzione, sizeof stato);
#else
    for (int i = 0; i < N; ++i) stato[i] = i;
#endif

    for (;;) {
        stampaMenu();
        int scelta = -1;
        if (!leggiIntero("> ", &scelta)) break;
        if (scelta == 0) break;

        switch (scelta) {
            case 1:
                stampaConfig(stato);
                break;
            case 2: {
                int conflitti = verifica(stato);
                if (conflitti == 0) puts("Soluzione valida.");
                else printf("Configurazione con %d conflitti.\n", conflitti);
                break;
            }
            case 3:
                printf("Costo euristico: %d\n", euristica(stato));
                break;
            case 4: {
                char percorso[512];
                fputs("Percorso del file: ", stdout);
                if (!leggiRiga(percorso, sizeof percorso)) {
                    puts("Percorso assente o troppo lungo.");
                    break;
                }
                percorso[strcspn(percorso, "\n")] = '\0';
                if (caricaConfig(percorso, stato)) puts("Stato aggiornato.");
                break;
            }
            case 5:
            case 6: {
                char buffer[128];
                char colonna = '\0';
                int riga = 0;
                fputs(scelta == 5 ? "Mossa da valutare (es. g7): " : "Mossa da eseguire (es. g7): ", stdout);
                if (!leggiRiga(buffer, sizeof buffer)
                    || !analizzaMossa(buffer, &colonna, &riga)) {
                    puts("Formato mossa non valido.");
                    break;
                }
                int colonnaInterna = indiceColonna(colonna);
                int rigaInterna = indiceRiga(riga);
                if (colonnaInterna < 0 || rigaInterna < 0) {
                    puts("Mossa fuori dalla scacchiera.");
                    break;
                }
                if (scelta == 5) {
                    printf("Costo ipotetico: %d\n", cerca(stato, colonna, riga));
                } else {
                    stato[colonnaInterna] = rigaInterna;
                    puts("Mossa eseguita.");
                }
                break;
            }
            default:
                puts("Scelta non valida: usare un numero da 0 a 6.");
                break;
        }
    }
    return 0;
}
