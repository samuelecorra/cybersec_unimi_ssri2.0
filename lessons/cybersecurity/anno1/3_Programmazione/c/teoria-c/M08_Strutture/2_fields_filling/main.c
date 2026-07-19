#include <stdbool.h>
#include <stdio.h>

struct Studentessa {
    char nome[15];
    char cognome[15];
    int età;
    int classe;
    bool abbonataBarScuola;
    char indirizzo[30];
};

int main(void) {
    struct Studentessa principessa = {0};

    // snprintf conosce la capacità della destinazione. strcpy sarebbe corretto
    // soltanto dopo avere dimostrato che sorgente e terminatore entrano nel buffer.
    (void)snprintf(principessa.nome, sizeof principessa.nome, "%s", "Principessa");
    (void)snprintf(principessa.cognome, sizeof principessa.cognome, "%s", "Gommarosa");
    (void)snprintf(principessa.indirizzo, sizeof principessa.indirizzo,
                   "%s", "Liceo delle Scienze Umane");
    principessa.età = 18;
    principessa.classe = 5;
    principessa.abbonataBarScuola = true;

    printf("Nome: %s\nCognome: %s\nEtà: %d\nClasse: %d\nAbbonata: %s\nIndirizzo: %s\n",
           principessa.nome, principessa.cognome, principessa.età,
           principessa.classe, principessa.abbonataBarScuola ? "sì" : "no",
           principessa.indirizzo);
    return 0;
}
