/*
 * Una struct aggrega sotto un solo nome dati anche eterogenei. In C i membri
 * non sono metodi e non esistono automaticamente incapsulamento, ereditarietà
 * o polimorfismo. Il compilatore può inserire padding tra i membri per rispettare
 * i requisiti di allineamento: sizeof(struct) può quindi superare la somma delle
 * dimensioni apparenti dei campi.
 */

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

typedef struct {
    char nome[10];
    char cognome[10];
    int età;
    int anno;
    bool abbonatoBarUniversità;
    char facoltà[30];
} Studente;

int main(void) {
    struct Studentessa principessa = {
        .nome = "Principessa",
        .cognome = "Gommarosa",
        .età = 18,
        .classe = 5,
        .abbonataBarScuola = true,
        .indirizzo = "Liceo delle Scienze Umane"
    };

    Studente cybergenio = {
        .nome = "Cyber",
        .cognome = "Prodigy",
        .età = 21,
        .anno = 2,
        .abbonatoBarUniversità = true,
        .facoltà = "Sicurezza Informatica"
    };

    printf("Studentessa: %s %s, età %d, classe %d, abbonata: %s, indirizzo: %s\n",
           principessa.nome, principessa.cognome, principessa.età,
           principessa.classe, principessa.abbonataBarScuola ? "sì" : "no",
           principessa.indirizzo);
    printf("Studente: %s %s, età %d, anno %d, abbonato: %s, facoltà: %s\n",
           cybergenio.nome, cybergenio.cognome, cybergenio.età,
           cybergenio.anno, cybergenio.abbonatoBarUniversità ? "sì" : "no",
           cybergenio.facoltà);

    // Leggere una struct automatica non inizializzata è comportamento indefinito,
    // non una fonte affidabile di presunti "garbage values".
    struct Studentessa sconosciuta = {0};
    printf("Campi iniziali sicuri: nome=\"%s\", età=%d, abbonata=%s\n",
           sconosciuta.nome, sconosciuta.età,
           sconosciuta.abbonataBarScuola ? "sì" : "no");

    printf("Dimensione della struct Studentessa: %zu byte\n", sizeof principessa);
    return 0;
}
