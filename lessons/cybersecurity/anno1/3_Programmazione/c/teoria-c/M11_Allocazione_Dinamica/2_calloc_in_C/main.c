/*
 * calloc(numero, dimensione) alloca un unico blocco contiguo come malloc e ne
 * azzera tutti i byte. Non è corretto dire che malloc non garantisca contiguità.
 * Inoltre byte tutti zero non rappresentano necessariamente il valore nullo di
 * ogni possibile tipo; qui calloc inizializza in modo utile l'array di puntatori
 * sulle piattaforme C ordinarie, ma il programma assegna comunque ogni elemento.
 * calloc può anche rilevare internamente l'overflow del prodotto dei due argomenti.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

enum { CAPACITÀ_NOME = 40 };

static void liberaNomi(char **nomi, size_t quantità) {
    for (size_t i = 0; i < quantità; ++i) {
        free(nomi[i]);
    }
    free(nomi);
}

int main(void) {
    size_t giocatori = 0;
    printf("Quanti giocatori vuoi inserire? ");
    if (scanf("%zu", &giocatori) != 1 || giocatori == 0) {
        fputs("Quantità non valida.\n", stderr);
        return EXIT_FAILURE;
    }
    int carattere = 0;
    while ((carattere = getchar()) != '\n' && carattere != EOF) {
        // svuota il resto della riga
    }

    char **nomi = calloc(giocatori, sizeof *nomi);
    if (nomi == NULL) {
        fputs("Allocazione non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }

    size_t creati = 0;
    for (; creati < giocatori; ++creati) {
        nomi[creati] = calloc(CAPACITÀ_NOME, sizeof *nomi[creati]);
        if (nomi[creati] == NULL) {
            fputs("Allocazione del nome non riuscita.\n", stderr);
            liberaNomi(nomi, creati);
            return EXIT_FAILURE;
        }

        printf("Inserisci il giocatore %zu: ", creati + 1);
        if (fgets(nomi[creati], CAPACITÀ_NOME, stdin) == NULL) {
            fputs("Lettura non riuscita.\n", stderr);
            liberaNomi(nomi, creati + 1);
            return EXIT_FAILURE;
        }
        nomi[creati][strcspn(nomi[creati], "\n")] = '\0';
    }

    for (size_t i = 0; i < giocatori; ++i) {
        printf("[%s]%s", nomi[i], i + 1 == giocatori ? "\n" : ", ");
    }

    liberaNomi(nomi, giocatori);
    nomi = NULL;
    return EXIT_SUCCESS;
}
