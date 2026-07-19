/*
 * Anche una struct viene passata per valore: la funzione riceve una copia.
 * Passando invece il suo indirizzo si evita la copia; const documenta che la
 * funzione di stampa non deve modificarla. Con un puntatore si usa l'operatore ->.
 */

#include <stdbool.h>
#include <stdio.h>

typedef struct {
    char nome[15];
    char cognome[15];
    int età;
    int classe;
    bool abbonataBarScuola;
    char indirizzo[30];
} Studentessa;

static void stampaStudentessa(const Studentessa *studentessa) {
    if (studentessa == NULL) {
        return;
    }
    printf("Nome: %s\nCognome: %s\nEtà: %d\nClasse: %d\nAbbonata: %s\nIndirizzo: %s\n",
           studentessa->nome, studentessa->cognome, studentessa->età,
           studentessa->classe, studentessa->abbonataBarScuola ? "sì" : "no",
           studentessa->indirizzo);
}

int main(void) {
    Studentessa principessa = {
        .nome = "Principessa",
        .cognome = "Gommarosa",
        .età = 18,
        .classe = 5,
        .abbonataBarScuola = true,
        .indirizzo = "Liceo delle Scienze Umane"
    };

    stampaStudentessa(&principessa);
    return 0;
}
