/* 🔹 Interesse Composto
1. Concetto base

Con l’interesse semplice, ogni anno calcoli l’interesse sempre sul capitale iniziale.
Con l’interesse composto, invece, gli interessi maturati si sommano al capitale, e dal 
periodo successivo anche loro iniziano a produrre interessi.

👉 Per questo si parla di capitalizzazione degli interessi: 
gli interessi diventano parte del capitale, e si ottiene una crescita esponenziale.

2. Formula del montante

La formula è:
M = C · (1 + i)ⁿ

E se poi teniamo conto che:
I = M - C       // allora...

I = C · [(1 + i)ⁿ - 1]

dove:
M = montante (capitale finale dopo n periodi)
C = capitale iniziale
i = tasso di interesse per periodo (es. 5% = 0,05)
n = numero di periodi (anni, semestri, mesi… dipende da come è definito il tasso)


//================================================================================


4. Esempio pratico

Supponiamo:

Capitale 𝐶 = 2000 €
Tasso annuo 𝑖 = 5% = 0,05
Tempo 𝑛 = 2 anni

Calcolo:

𝑀 = 2000 ⋅ (1 + 0,05)² = 2000 ⋅ 1,1025 = 2205

𝑰 = 2205 - 2000 = 205 €


//================================================================================


// Implementazione in C:        */


#include <stdio.h>
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <math.h>               // direttiva per funzioni matematiche

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    double capitaleIniziale; // C
    double tassoInteresse;   // i
    int numeroAnni;         // n

    // Chiediamo all'utente il capitale iniziale:
    printf("Inserisci il capitale iniziale (C): ");
    scanf("%lf", &capitaleIniziale); // %lf per double

    // Chiediamo all'utente il tasso di interesse annuo:
    printf("Inserisci il tasso di interesse annuo in %% (ad esempio 5 per 5%%): ");
    scanf("%lf", &tassoInteresse); // %lf per double
    tassoInteresse /= 100.0; // Convertiamo la percentuale in forma decimale

    // Chiediamo all'utente il numero di anni:
    printf("Inserisci il numero di anni (n): ");
    scanf("%d", &numeroAnni); // %d per int

    // Calcoliamo il montante:
    double montante = capitaleIniziale * pow((1 + tassoInteresse), numeroAnni);

    // Calcoliamo gli interessi:
    double interessi = montante - capitaleIniziale;

    // Mostriamo i risultati:
    printf("Montante finale (M): %.2f €\n", montante);
    printf("Interessi guadagnati (I): %.2f €\n", interessi);

    return 0;
}

/*================================================================================


// Le formule finora descritte ci sono congeniali perché il tasso è annuo e il 
tempo è in anni. Ma se il tasso fosse periodico (ad esempio mensile) e il tempo
in anni, come si fa?

... */