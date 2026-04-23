#include <stdio.h>
#include <stdlib.h>             // direttiva per funzioni di utilità generale come rand() e srand()
#include <time.h>               // direttiva per time() per numeri casuali
#include <windows.h>           // direttiva per SetConsoleOutputCP()

int main() {

    // INDOVINA IL NUMERO - GIOCO SEMPLICE MA INTERESSANTE E INTERATTIVO!

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    // Iniziamo con le variabili che ci serviranno:

    int rispostaGiocatore = 0;
    int tentativi = 0;
    int min = 1;
    int max = 100;

    // E ora la risposta del computer:
    srand((unsigned int)time(NULL)); // inizializziamo il generatore con il tempo attuale
    
    int rispostaComputer = (rand() % (max - min + 1)) + min; // numero tra 1 e 100

    // Inizio del gioco:
    printf("Benvenuto al gioco 'Indovina il Numero'!\n");
    printf("Ho scelto un numero tra %d e %d. Riesci a indovinarlo?\n", min, max);
    
    printf("Inserisci e vediamo se ci hai azzecato al primo tentativo: ");
    scanf("%d", &rispostaGiocatore);
    getchar(); // puliamo il buffer
    tentativi++;

    while (rispostaGiocatore != rispostaComputer) {
        if (rispostaGiocatore < rispostaComputer) {
            printf("Troppo basso! Riprova: ");
        } else {
            printf("Troppo alto! Riprova: ");
        }
        scanf("%d", &rispostaGiocatore);
        getchar(); // puliamo il buffer
        tentativi++;
    }

    // Se siamo qui, il giocatore ha indovinato
    printf("Complimenti! Hai indovinato il numero %d in %d tentativi!\n", rispostaComputer, tentativi);

}