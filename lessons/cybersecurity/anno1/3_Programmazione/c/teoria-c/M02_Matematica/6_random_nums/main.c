// Qualche capitolo fa abbiamo già visto rand() e srand() per generare numeri 
// pseudocasuali in C.

#include <stdio.h>
#include <stdlib.h>             // direttiva per funzioni di utilità generale come rand() e srand()
#include <time.h>               // direttiva per time() per numeri casuali
#include <windows.h>           // direttiva per SetConsoleOutputCP()

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    // Per generare numeri pseudo-casuali in C si usa la funzione rand()
    // che ritorna un numero intero pseudo-casuale compreso tra 0 e RAND_MAX.
    // RAND_MAX è una costante definita in stdlib.h, il cui valore minimo
    // garantito è 32767, ma può essere più grande a seconda dell'implementazione.

    printf("Sul mio OS, RAND_MAX vale: %d\n", RAND_MAX);

    printf("\n//===============================================================\n\n");

    // Esempio di utilizzo di rand():
    int randomInt = rand();               // numero intero pseudo-casuale
    printf("Numero casuale generato: %d\n", randomInt);

    // Ma senza un seed continueremo sempre a generare la stessa sequenza
    // di numeri pseudo-casuali ogni volta che eseguiamo il programma, che nel mio caso,
    // dato, che stampiamo un singolo numero, sarà sempre 41.

    // Per cambiare la sequenza di numeri pseudo-casuali dobbiamo inizializzare
    // il generatore con un seme (seed) usando la funzione srand().

    // Si sfrutta la funzione time() di time.h che ritorna il numero di secondi
    // trascorsi dal 1 gennaio 1970 (epoca Unix), che cambia ogni secondo.

    // ATTENZIONE: srand ha tipo di ritorno void, quindi non bisogna assegnare il seme
    // generato a nessuna variabile, ci pensa il compilatore per noi!
    
    srand(time(NULL)); // inizializziamo il generatore con il tempo attuale
    
    // Ora ogni volta che eseguiamo il programma, otterremo un numero diverso
    randomInt = rand();               // numero intero pseudo-casuale
    printf("Numero casuale generato dopo aver inizializzato il seme: %d\n", randomInt);
    
    // Notiamo che il numero tende sempre a salire verso l'alto, perché il tempo
    // trascorso dal 1970 è sempre crescente, e quindi il seme è sempre più grande.
    // Per generare numeri pseudo-casuali in un intervallo specifico, si usa
    // l'operatore modulo %, che ritorna il resto della divisione tra due numeri.
    // Quindi rand() % N ritorna un numero compreso tra 0 e N-1!

    int randomIntInRange = rand() % 100;  // numero intero pseudo-casuale tra 0 e 99
    printf("Numero casuale generato tra 0 e 99: %d\n", randomIntInRange);

    // Tra l'altro, con il modulo risolviamo il problema per cui ad ogni esecuzione
    // vedevamo un pattern fisso di crescita del numero pseudo-casuale, perché
    // il modulo "riporta indietro" il numero ad ogni N, quindi...


    printf("\n//===============================================================\n\n");

    
    // Ma se non volessimo 0 come minimo bensì un altro numero?

    int min = 50;
    int max = 100;

    int randBetweenMinMax1 = (rand() % (max - min + 1)) + min; // numero tra 50 e 100
    int randBetweenMinMax2 = (rand() % (max - min + 1)) + min; // numero tra 50 e 100
    int randBetweenMinMax3 = (rand() % (max - min + 1)) + min; // numero tra 50 e 100
    // LA PRECEDENTE FORMULA VA IMPERATIVAMENTE MEMORIZZATA!
    
    printf("Numero casuale generato tra %d e %d: %d\n", min, max, randBetweenMinMax1);
    printf("Numero casuale generato tra %d e %d: %d\n", min, max, randBetweenMinMax2);
    printf("Numero casuale generato tra %d e %d: %d\n", min, max, randBetweenMinMax3);

    return 0;
}