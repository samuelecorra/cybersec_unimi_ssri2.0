/* Dicevamo...
E se invece non capitalizzassimo gli interessi ogni anno, ma più frequentemente?
Ad esempio ogni semestre, o ogni trimestre, o addirittura ogni mese?
👉 Si parla di interesse composto periodico, e la formula è leggermente diversa
da quella dell’interesse composto "normale".

2. Formula del montante

M = C · (1 + i/m)^(n·m):

dove:
M = montante (capitale finale dopo n periodi)
C = capitale iniziale
i = tasso annuo nominale (TAN) (es. 5% = 0,05)
n = numero di periodi (anni, semestri, mesi… dipende da come è definito il tasso)
m = numero di capitalizzazioni per periodo (es. 12 per mensile, 4 per trimestrale, 
2 per semestrale, 1 per annuale)

// L'implementazione cambia di poco rispetto a prima, vediamo come: */

#include <stdio.h>
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <math.h>               // direttiva per funzioni matematiche

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    double capitaleIniziale; // C
    double TAN;   // i
    int numeroAnni;         // n
    int capitalizzazioniPerAnno; // m

    // Chiediamo all'utente il capitale iniziale:
    printf("Inserisci il capitale iniziale (C): ");
    scanf("%lf", &capitaleIniziale); // %lf per double

    // Chiediamo all'utente il tasso di interesse annuo:
    printf("Inserisci il tasso annuo nominale (TAN) in %% (ad esempio 5 per 5%%): ");
    scanf("%lf", &TAN); // %lf per double
    TAN /= 100.0; // Convertiamo la percentuale in forma decimale

    // Chiediamo all'utente per quanto vuole capitalizzare:
    printf("Inserisci il numero di anni per cui calcolare gli interessi (n): ");
    scanf("%d", &numeroAnni); // %d per int

    // Chiediamo all'utente il numero di capitalizzazioni per anno:
    printf("Inserisci il numero di capitalizzazioni per anno (m) (ad esempio 12 per mensile, 4 per trimestrale, 2 per semestrale, 1 per annuale): ");
    scanf("%d", &capitalizzazioniPerAnno); // %d per int

    // Calcoliamo il montante:
    double montante = capitaleIniziale * pow((1 + (TAN / capitalizzazioniPerAnno)), numeroAnni * capitalizzazioniPerAnno);

    // Calcoliamo gli interessi:
    double interessi = montante - capitaleIniziale;

    // E infine possiamo anche calcolare il cosiddetto TAE, ovvero il Tasso Annuo Effettivo,
    // che rappresenta il tasso di interesse reale che si ottiene tenendo conto della
    // capitalizzazione periodica. La formula per calcolare il TAE è la seguente,
    // scritta con l'esponente m apice e non con l'accento circonflesso ^ come in C:
    // TAE = (1 + (TAN / m))ᵐ - 1

    double TAE = pow((1 + (TAN / capitalizzazioniPerAnno)), capitalizzazioniPerAnno) - 1;


    // Stampiamo i risultati:
    printf("Montante finale (M): %.2f\n", montante);
    printf("Interessi guadagnati: %.2f\n", interessi);
    printf("Tasso Annuo Effettivo (TAE): %.2f%%\n", TAE * 100);


//================================================================================


    // Un'ultima piccola precisazione:
    // Ora che abbiamo il TAE, possiamo usarlo per calcolarci il montante dopo
    // tot anni, MA NON DOPO TOT PERIODI, perché il TAE è un tasso annuo!
    // Quindi se vogliamo calcolare il montante dopo n anni, possiamo usare questa
    // scorciatoia per non intricarci con tan e numero di capitalizzazioni per anno:
    // M = C * (1 + TAE)ⁿ

    
    printf("========================================================\n");
    printf("\nCalcolo del montante usando il TAE invece di TAN e m:\n");
    printf("Montante calcolato con TAN e m: %.2f\n", montante);
    
    // Ricalcoliamo il montante usando il TAE:
    montante = capitaleIniziale * pow((1 + TAE), numeroAnni);

    printf("Montante calcolato con TAE: %.2f\n", montante);
    // noteremo che il risultato è lo stesso, ma che la formula è più semplice!
    
    printf("========================================================\n");
    return 0;
}