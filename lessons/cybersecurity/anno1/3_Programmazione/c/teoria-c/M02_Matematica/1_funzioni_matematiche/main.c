#include <stdio.h>              // direttiva
#include <math.h>               // direttiva per funzioni matematiche
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <time.h>               // direttiva per time() per numeri casuali
#include <stdlib.h>             // direttiva per funzioni di utilità generale come rand()

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro


//================================================================================


    // Esempi di funzioni matematiche:

    double x = 2.0;
    double y = 3.0;

    double potenza = pow(x, y);      // x elevato alla y
    double radice = sqrt(16.0);      // radice quadrata di 16
    
    double logaritmoNat = log(2.71828); // logaritmo naturale di e (circa 2.71828)
    double logConBase10 = log10(100.0); // logaritmo in base 10 di 100
    double logConBase2 = log2(8.0);      // logaritmo in base 2 di 8
    
    // Stampiamo i risultati:
    printf("Esempi di funzioni matematiche in C:\n");
    
    printf("pow(%.2f, %.2f) = %.2f\n", x, y, potenza);
    printf("sqrt(16.0) = %.2f\n", radice);
    
    printf("log(2.71828) = %.5f\n", logaritmoNat);
    printf("log10(100.0) = %.2f\n", logConBase10);
    printf("log2(8.0) = %.2f\n", logConBase2);


//================================================================================


    // DIGRESSIONE FONDAMENTALE SULLE FUNZIONI TRIGONOMETRICHE:

    // 1. ACCETTANO ANGOLI IN RADIANTI NON IN GRADI!!!!
    
    // 2. RADIANTE = GRADO * (π / 180)
    
    // 3. π (pi greco) = 3.14159 (approssimato)
    
    // 4. ESEMPIO: 90 GRADI = 90 * (3.14159 / 180) = 1.570795 RADIANTI
    // 5. ESEMPIO: 45 GRADI = 45 * (3.14159 / 180) = 0.7853975 RADIANTI

    // LE TRE FONDAMENTALI:
    double seno = sin(3.14159 / 2);  // seno di π/2 ovvero 90 gradi
    double coseno = cos(0.0);        // coseno di 0 gradi
    double tangente = tan(3.14159 / 4); // tangente di π/4 ovvero 45 gradi
    
    // LE ALTRE TRE (inversa delle fondamentali):
    double arcoseno = asin(1.0);      // ritorna l'angolo il cui seno è 1.0
    double arcocoseno = acos(1.0);    // ritorna l'angolo il cui coseno è 1.0
    double arcotangente = atan(1.0);  // ritorna l'angolo la cui tangente è 1.0

    // C'è una versione migliorata di arcotangente che accetta due
    // argomenti (y e x) e ritorna l'angolo corretto in base al
    // quadrante in cui si trova il punto (x, y):
    double arcotangente2 = atan2(1.0, 1.0); // ritorna l'angolo la cui tangente è y/x


    // Per le reciproche non esistono funzioni specifiche, ma si può fare
    // 1/sin(x), 1/cos(x), 1/tan(x), il che è immediato!

    double csc = 1.0 / seno;  // cosecante
    double sec = 1.0 / coseno; // secante
    double cot = 1.0 / tangente; // cotangente

    // Stampiamo i risultati: 
    
    printf("\nEsempi di funzioni trigonometriche in C:\n");
    
    printf("sin(π/2) = %.2f\n", seno);
    printf("cos(0.0) = %.2f\n", coseno);
    printf("tan(π/4) = %.2f\n", tangente);

    printf("asin(1.0) = %.2f\n", arcoseno);
    printf("acos(1.0) = %.2f\n", arcocoseno);
    printf("atan(1.0) = %.2f\n", arcotangente);
    printf("atan2(1.0, 1.0) = %.2f\n", arcotangente2);

    printf("csc(π/2) = %.2f\n", csc);
    printf("sec(0.0) = %.2f\n", sec);
    printf("cot(π/4) = %.2f\n", cot);


//================================================================================


    // Funzioni di arrotondamento:

    double num1 = 5.7;
    double num2 = -5.7;
    double arrotondato = round(num1); // arrotonda al numero intero più vicino
    double arrotondatoNeg = round(num2); // arrotonda al numero intero più vicino

    double num3 = 5.3;
    double num4 = -5.3;
    double troncato = trunc(num3); // tronca la parte decimale
    double troncatoNeg = trunc(num4); // tronca la parte decimale

    double num5 = 5.3;
    double num6 = -5.3;
    double arrotondatoPerEccesso = ceil(num5); // arrotonda per eccesso, a prescindere
    // che sia .1 o .9
    double arrotondatoPerEccessoNeg = ceil(num6); // arrotonda per eccesso, ATTENZIONE
    // che per i negativi arrotonda verso lo 0!!!

    double num7 = 5.7;
    double num8 = -5.7;
    double arrotondatoPerDifetto = floor(num7); // arrotonda per difetto, a prescindere
    double arrotondatoPerDifettoNeg = floor(num8); // arrotonda per difetto, ATTENZIONE
    // che per i negativi arrotonda verso il -∞!!!

    // Stampiamo i risultati:
    printf("\nEsempi di funzioni di arrotondamento in C:\n");

    printf("round(%.2f) = %.2f\n", num1, arrotondato);
    printf("round(%.2f) = %.2f\n", num2, arrotondatoNeg);

    printf("trunc(%.2f) = %.2f\n", num3, troncato);
    printf("trunc(%.2f) = %.2f\n", num4, troncatoNeg);

    printf("ceil(%.2f) = %.2f\n", num5, arrotondatoPerEccesso);
    printf("ceil(%.2f) = %.2f\n", num6, arrotondatoPerEccessoNeg);
    
    printf("floor(%.2f) = %.2f\n", num7, arrotondatoPerDifetto);
    printf("floor(%.2f) = %.2f\n", num8, arrotondatoPerDifettoNeg);


//================================================================================


    // Funzioni di valore assoluto:

    int intValoreAssoluto = abs(-10);        // valore assoluto per interi
    double doubleValoreAssoluto = fabs(-5.5); // valore assoluto per double

    long longValoreAssoluto = labs(-100L);   // valore assoluto per long
    float floatValoreAssoluto = fabsf(-3.3f); // valore assoluto per float
    long double longDoubleValoreAssoluto = fabsl(-7.7L); // valore assoluto per long double

    // Stampiamo i risultati:
    printf("\nEsempi di funzioni di valore assoluto in C:\n");

    printf("abs(-10) = %d\n", intValoreAssoluto);
    printf("fabs(-5.5) = %.2f\n", doubleValoreAssoluto);
    
    printf("labs(-100L) = %ld\n", longValoreAssoluto);
    printf("fabsf(-3.3f) = %.2f\n", floatValoreAssoluto);
    printf("fabsl(-7.7L) = %.2Lf\n", longDoubleValoreAssoluto);


//================================================================================

   
    // Funzioni di generazione numeri PSEUDOcasuali:

    // La funzione più comune e immediata che si può usare è rand():

    // Sintassi generica di rand():
    // int rand(void);
    // Ritorna un numero intero pseudo-casuale compreso tra 0 e RAND_MAX.
    // RAND_MAX è una costante definita in stdlib.h, il cui valore minimo
    // garantito è 32767, ma può essere più grande a seconda dell'implementazione.

    // Esempio di utilizzo di rand():
    int randomInt = rand();               // numero intero pseudo-casuale

    // Ovviamente si può pilotare l'intervallo di numeri pseudo-casuali
    // usando l'operatore modulo, il perché è semplice:
    // rand() % N ritorna un numero compreso tra 0 e N-1!
    int randomIntInRange = rand() % 100;  // numero intero pseudo-casuale tra 0 e 99

    // Stampiamo i risultati:
    printf("\nEsempi di funzioni di generazione numeri pseudo-casuali in C:\n");
    printf("rand() = %d\n", randomInt);
    printf("rand() %% 100 = %d\n", randomIntInRange);


    // C'è poi da introdurre la funzione srand(), che serve per inizializzare
    // il generatore di numeri pseudo-casuali con un seme (seed).
    // Se non si chiama srand(), il generatore viene inizializzato con un valore
    // predefinito (di solito 1), il che significa che ogni volta che si esegue
    // il programma, si ottiene la stessa sequenza di numeri pseudo-casuali!

    // Sintassi generica di srand():
    // void srand(unsigned int seed);
    // Inizializza il generatore di numeri pseudo-casuali con il valore di seed.

    // Chiaramente, a meno che non si voglia modificare a mano ogni volta il seme,
    // bisogna trovare un escamotage per avere un seme "variabile" ad ogni esecuzione,
    // e si noti che la risposta risiede nella domanda stessa: cosa cambia ogni volta
    // che si esegue un programma? Il tempo! E quindi si usa il tempo come seme.

    // Per fare ciò, si usa la funzione time() definita in time.h, che ritorna
    // il numero di secondi trascorsi dal 1 gennaio 1970.
    
    // Sintassi geenrica di time():
    // time_t time(time_t *t);
    // Ritorna il tempo attuale come valore di tipo time_t. Se t non è NULL,
    // il valore viene anche memorizzato nell'indirizzo puntato da t.
    // Se invece settiamo t a NULL, il valore non viene memorizzato da nessuna parte,
    // il che ci è congeniale perché ci basta il valore di ritorno!

    srand((unsigned int)time(NULL));

    // Stampiamo il risultato:
    printf("Sequenza random: ");

    for (int i = 0; i < 5; i++) {
        printf("%d ", rand() % 100);
        }
    printf("\n");

    return 0;
    
}