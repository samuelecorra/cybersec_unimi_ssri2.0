#include <stdio.h>
#include <string.h>
#include <windows.h>
#include <stdbool.h>    // Per usare il tipo bool (true/false)

//===================================================================================

struct Studentessa {
    char nome[15];      // Principessa
    char cognome[15];   // Gommarosa
    int età;            // 18
    int classe;         // 5
    bool abbonataBarScuola; // true
    char indirizzo[30]; // Liceo delle Scienze Umane
};

int main() {

    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows

    // Istanza di struct Studentessa
    struct Studentessa Principessa = {0}; // Inizializzo tutti i campi a zero

    // E ora che non rischiamo garbage values, possiamo tranquillamente "compilare"
    // i campi uno per uno, usando l'operatore punto (.)

    // Preannunciamo la funzione strcpy per copiare stringhe:
    // sintassi di strcpy:
    // strcpy(destinazione, sorgente);

    strcpy(Principessa.nome, "Principessa");
    strcpy(Principessa.cognome, "Gommarosa");
    Principessa.età = 18;
    Principessa.classe = 5;
    Principessa.abbonataBarScuola = true;
    strcpy(Principessa.indirizzo, "Liceo delle Scienze Umane");

    // Stampa a video per verifica:
    printf("Nome: %s\n", Principessa.nome);
    printf("Cognome: %s\n", Principessa.cognome);
    printf("Età: %d\n", Principessa.età);
    printf("Classe: %d\n", Principessa.classe);
    printf("Abbonata al bar della scuola: %s\n", Principessa.abbonataBarScuola ? "Sì" : "No");
    printf("Indirizzo: %s\n", Principessa.indirizzo);
    printf("\n");

    // Subito sorge il dubbio... ma è un po' prolisso stampare un sacco di campi
    // uno per uno, no? E se la struct avesse 50 campi?
    // La soluzione è creare una funzione apposita che stampi TUTTA la struct
    // passata come parametro!

    printf("Premi INVIO per uscire...\n");
    getchar();

    return 0;
}