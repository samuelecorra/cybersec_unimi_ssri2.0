#include <stdint.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

static char **allocaMatrice(size_t dimensione) {
    if (dimensione == 0 || dimensione > SIZE_MAX / sizeof(char *)
        || dimensione > (size_t)LONG_MAX) return NULL;
    char **matrice = calloc(dimensione, sizeof *matrice);
    if (matrice == NULL) return NULL;

    for (size_t riga = 0; riga < dimensione; ++riga) {
        matrice[riga] = malloc(dimensione * sizeof *matrice[riga]);
        if (matrice[riga] == NULL) {
            while (riga > 0) free(matrice[--riga]);
            free(matrice);
            return NULL;
        }
    }
    return matrice;
}

static void liberaMatrice(char **matrice, size_t dimensione) {
    if (matrice == NULL) return;
    for (size_t riga = 0; riga < dimensione; ++riga) free(matrice[riga]);
    free(matrice);
}

static int indiceLettera(unsigned char carattere) {
    if (carattere >= 'A' && carattere <= 'Z') return (int)(carattere - 'A');
    if (carattere >= 'a' && carattere <= 'z') return (int)(carattere - 'a');
    return -1;
}

static int leggiMatrice(char **matrice, size_t dimensione) {
    for (size_t riga = 0; riga < dimensione; ++riga) {
        for (size_t colonna = 0; colonna < dimensione; ++colonna) {
            char carattere = '\0';
            printf("Lettera [%zu][%zu]: ", riga, colonna);
            if (scanf(" %c", &carattere) != 1 || indiceLettera((unsigned char)carattere) < 0) {
                fputs("È richiesta una lettera ASCII A-Z o a-z.\n", stderr);
                return 0;
            }
            matrice[riga][colonna] = carattere;
        }
    }
    return 1;
}

static char **elaboraMatrice(char *const originale[], size_t dimensione) {
    char **risultato = allocaMatrice(dimensione);
    if (risultato == NULL) return NULL;

    static const int direzioni[8][2] = {
        {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}
    };
    for (size_t riga = 0; riga < dimensione; ++riga) {
        for (size_t colonna = 0; colonna < dimensione; ++colonna) {
            int indiceMinimo = 26;
            char carattereMinimo = '\0';
            for (size_t d = 0; d < 8; ++d) {
                long rigaVicina = (long)riga + direzioni[d][0];
                long colonnaVicina = (long)colonna + direzioni[d][1];
                if (rigaVicina < 0 || colonnaVicina < 0
                    || rigaVicina >= (long)dimensione || colonnaVicina >= (long)dimensione) continue;

                char candidato = originale[rigaVicina][colonnaVicina];
                int indice = indiceLettera((unsigned char)candidato);
                if (indice < indiceMinimo) {
                    indiceMinimo = indice;
                    carattereMinimo = candidato; // conserva il case del primo minimo incontrato
                }
            }
            risultato[riga][colonna] = carattereMinimo;
        }
    }
    return risultato;
}

static void stampaMatrice(char *const matrice[], size_t dimensione) {
    for (size_t riga = 0; riga < dimensione; ++riga) {
        for (size_t colonna = 0; colonna < dimensione; ++colonna) printf("%c ", matrice[riga][colonna]);
        putchar('\n');
    }
}

int main(void) {
    size_t dimensione = 0;
    printf("Dimensione della matrice (almeno 2): ");
    if (scanf("%zu", &dimensione) != 1 || dimensione < 2
        || dimensione > (size_t)LONG_MAX) {
        fputs("Dimensione non valida.\n", stderr);
        return 1;
    }

    char **originale = allocaMatrice(dimensione);
    if (originale == NULL || !leggiMatrice(originale, dimensione)) {
        liberaMatrice(originale, dimensione);
        return 1;
    }
    char **risultato = elaboraMatrice(originale, dimensione);
    if (risultato == NULL) {
        fputs("Allocazione non riuscita.\n", stderr);
        liberaMatrice(originale, dimensione);
        return 1;
    }

    puts("Matrice originale:");
    stampaMatrice(originale, dimensione);
    puts("Matrice risultante:");
    stampaMatrice(risultato, dimensione);
    liberaMatrice(originale, dimensione);
    liberaMatrice(risultato, dimensione);
    return 0;
}
