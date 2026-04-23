
// Dicevamo... Subito sorge il dubbio... ma è un po' prolisso stampare un sacco di campi
// uno per uno, no? E se la struct avesse 50 campi?
// La soluzione è creare una funzione apposita che stampi TUTTA la struct
// passata come parametro!


//===================================================================================


#include <stdio.h>
#include <string.h>
#include <windows.h>
#include <stdbool.h>    // Per usare il tipo bool (true/false)


//===================================================================================


typedef struct  {
    char nome[15];      // Principessa
    char cognome[15];   // Gommarosa
    int età;            // 18
    int classe;         // 5
    bool abbonataBarScuola; // true
    char indirizzo[30]; // Liceo delle Scienze Umane
} Studentessa;


// Dichiarazione e definizione della funzione che stampa TUTTA la struct
void stampaStudentessa(Studentessa s) {
    printf("Nome: %s\n", s.nome);
    printf("Cognome: %s\n", s.cognome);
    printf("Età: %d\n", s.età);
    printf("Classe: %d\n", s.classe);
    printf("Abbonata al bar della scuola: %s\n", s.abbonataBarScuola ? "Sì" : "No");
    printf("Indirizzo: %s\n", s.indirizzo);
    printf("\n");
}

int main() {

    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows

    // Istanza di struct Studentessa
    Studentessa Principessa = {0}; // Inizializzo tutti i campi a zero

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
    stampaStudentessa(Principessa); // Passo la struct come parametro alla funzione

    printf("Premi INVIO per uscire...");
    getchar();

    return 0;
}