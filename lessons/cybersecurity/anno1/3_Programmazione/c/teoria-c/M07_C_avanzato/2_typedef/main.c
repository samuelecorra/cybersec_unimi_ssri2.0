/*
 * typedef crea un alias per un tipo esistente:
 *     typedef tipo_esistente nuovo_nome;
 *
 * Un typedef può comparire sia a livello di file sia dentro un blocco. L'ambito
 * dell'alias segue le normali regole di visibilità: qui lo dichiariamo a livello
 * di file perché sia utilizzabile da tutte le funzioni successive.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef int Intero;
typedef char Stringa[15]; // fino a 14 caratteri più il terminatore '\0'
typedef char *PtrStringa;

int main(void) {
    Intero a = 10;
    Intero b = 20;
    printf("La somma di %d e %d è %d\n", a, b, a + b);

    Stringa nome1 = "Principessa";
    enum { CAPACITÀ_NOME = 20 };
    PtrStringa nome2 = malloc(CAPACITÀ_NOME * sizeof *nome2);

    if (nome2 == NULL) {
        fputs("Allocazione non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }

    printf("Nome1: %s\nInserisci il tuo nome: ", nome1);
    if (fgets(nome2, CAPACITÀ_NOME, stdin) == NULL) {
        fputs("Lettura non riuscita.\n", stderr);
        free(nome2);
        return EXIT_FAILURE;
    }
    nome2[strcspn(nome2, "\n")] = '\0';
    printf("Nome2: %s\n", nome2);

    free(nome2);
    nome2 = NULL;

    /*
     * Attenzione: PtrStringa nasconde il fatto che il tipo sia un puntatore.
     * Per esempio "PtrStringa x, y" dichiara due puntatori, mentre
     * "char *x, y" dichiara un puntatore e un char. Gli alias di puntatore
     * vanno quindi usati soltanto quando migliorano davvero la leggibilità.
     */
    return EXIT_SUCCESS;
}
