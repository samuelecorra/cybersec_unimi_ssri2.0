/* Gli array sono utili sì, ma richiedono tassativa omogeneità dei dati.

PROBLEMA: gli oggetti reali non sono affatto costituiti da elementi omogenei tra di loro!

SOLUZIONE: le STRUCTS! -> AGGREGAZIONI DI DATI ETEROGENEI IN UN UNICO TIPO 
DI DATO COMPLESSO!

BENEFIT: possiamo creare tipi di dati complessi, contenti informazioni correlate
anche se diverse concettualmente tra di loro!

Gli struct sono simili agli oggetti dei linguaggi più moderni, MA ATTENZIONE:
1) Non hanno metodi (funzioni associate al tipo di dato)
2) Non supportano l'incapsulamento (tutti i membri sono pubblici)
3) Non supportano l'ereditarietà (non possiamo creare struct derivate da altre)
4) Non supportano il polimorfismo (non possiamo avere funzioni che operano in modo diverso
   a seconda del tipo di struct)

// INSOMMA... Le struct sono l'embrione degli oggetti, ma non sono veri e propri oggetti!
// Infatti è dal C degli anni '70/'80 che nacque l'esigenza di predisporre tutti i
// miglioramenti strutturali che hanno portato all'Object Oriented Programming (OOP)!


//===================================================================================


// Come per typedef ed enum, anche le struct per buona prassi si dichiarano
// all'inizio del file, prima del main().

// E come per gli enum, possiamo usare typedef per creare un alias per il tipo struct,
// in modo da non dover scrivere ogni volta "struct NomeStruct", ma ricordiamoci che
// in questo caso il nome del tipo va posto DOPO la definizione della struct stessa!    */


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

// Possiamo anche usare typedef per creare un alias per il tipo struct:
typedef struct {
    char nome[10];      // Samuele
    char cognome[10];   // Corrà
    int età;            // 21
    int anno;         // 2
    bool abbonatoBarUniversità; // true
    char facoltà[30]; // Sicurezza Informatica
} Studente;


//===================================================================================


// Nel main possiamo "istanziare" (creare) variabili di tipo struct:

int main() {

    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows


    // Istanza di struct Studentessa
    struct Studentessa Principessa = { "Principessa",
                                    "Gommarosa",
                                    18,
                                    5,
                                    true,
                                    "Liceo delle Scienze Umane" };


    Studente Cybergenio = { "Cyber",
                             "Prodigy",
                             21,
                             2,
                             true,
                             "Sicurezza Informatica" };

    // Notare che per l'istanza di Studente non abbiamo scritto "struct" davanti,
    // perché abbiamo usato typedef per creare un alias!

    
//===================================================================================

    // Possiamo accedere ai membri della struct usando l'operatore punto (.)
    printf("Studentessa: %s %s,\netà: %d,\nclasse: %d,\nabbonata al bar della scuola: %s,\nindirizzo: %s\n",
           Principessa.nome,
           Principessa.cognome,
           Principessa.età,
           Principessa.classe,
           Principessa.abbonataBarScuola ? "Sì" : "No",    // il ternary torna utile spesso!
           Principessa.indirizzo);

    printf("Studente: %s %s,\netà: %d,\nanno: %d,\nabbonato al bar dell'università: %s,\nfacoltà: %s\n",
           Cybergenio.nome,
           Cybergenio.cognome,
           Cybergenio.età,
           Cybergenio.anno,
           Cybergenio.abbonatoBarUniversità ? "Sì" : "No",
           Cybergenio.facoltà);

    printf("Premi INVIO per continuare...\n");
    getchar();

    // ===================================================================================

    // Come ormai dovremmo aver intuito, non inizializzare una struct e tentare
    // di accedere ai suoi campi porta a comportamenti indefiniti (garbage values):
    struct Studentessa Sconosciuta; // Non inizializzata

    printf("Studentessa sconosciuta: %s %s,\netà: %d,\nclasse: %d,\nabbonata al bar della scuola: %s,\nindirizzo: %s\n",
           Sconosciuta.nome,
           Sconosciuta.cognome,
           Sconosciuta.età,
           Sconosciuta.classe,
           Sconosciuta.abbonataBarScuola ? "Sì" : "No",
           Sconosciuta.indirizzo);

    // QUINDI ATTENZIONE!
    // Per ovviare al problema se non vogliamo inizializzare tutti i campi subito
    // con valori specifici, ci basta settare tutto a zero:

    struct Studentessa Sconosciuta2 = {0}; // Inizializza tutto a zero
    
    printf("Studentessa sconosciuta 2: %s %s,\netà: %d,\nclasse: %d,\nabbonata al bar della scuola: %s,\nindirizzo: %s\n",
           Sconosciuta2.nome,
           Sconosciuta2.cognome,
           Sconosciuta2.età,
           Sconosciuta2.classe,
           Sconosciuta2.abbonataBarScuola ? "Sì" : "No",
           Sconosciuta2.indirizzo);  // Ora è tutto a zero/false/""/ STAMPA SICURA!

    
}