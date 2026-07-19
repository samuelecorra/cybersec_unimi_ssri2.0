// Qualche capitolo fa abbiamo già visto rand() e srand() per generare numeri 
// pseudocasuali in C.

#include <stdio.h>
#include <stdlib.h>             // direttiva per funzioni di utilità generale come rand() e srand()
#include <time.h>               // direttiva per time() per numeri casuali
#include <windows.h>           // direttiva per SetConsoleOutputCP()

int main(void) {

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
    // dato che stampiamo un singolo numero, può essere sempre lo stesso. Il valore
    // concreto dipende dall'implementazione: non è portabile aspettarsi 41.

    // Per cambiare la sequenza di numeri pseudo-casuali dobbiamo inizializzare
    // il generatore con un seme (seed) usando la funzione srand().

    // Si sfrutta spesso time() di time.h. Sulle piattaforme comuni il valore è legato
    // ai secondi trascorsi dall'epoca Unix; lo standard C non impone questa specifica
    // rappresentazione, ma garantisce un valore di tempo di calendario time_t.

    // srand ha tipo di ritorno void: riceve il seme e aggiorna lo stato interno del
    // generatore; non restituisce un numero casuale.
    
    srand((unsigned int)time(NULL)); // conversione esplicita al tipo richiesto da srand
    
    // Esecuzioni avviate in secondi diversi ottengono normalmente sequenze diverse;
    // due avvii nello stesso secondo possono invece usare lo stesso seme.
    randomInt = rand();               // numero intero pseudo-casuale
    printf("Numero casuale generato dopo aver inizializzato il seme: %d\n", randomInt);
    
    // Un seme più grande non implica risultati più grandi: rand() produce una sequenza
    // deterministica dipendente dal seme, non una funzione monotona del seme.
    // Per generare numeri pseudo-casuali in un intervallo specifico, si usa spesso
    // l'operatore modulo %, che ritorna il resto della divisione tra due numeri.
    // Quindi rand() % N ritorna un numero compreso tra 0 e N-1. Questa scorciatoia
    // introduce modulo bias quando RAND_MAX + 1 non è divisibile per N ed è inadatta
    // a crittografia, simulazioni che richiedono forte uniformità o sicurezza.

    int randomIntInRange = rand() % 100;  // numero intero pseudo-casuale tra 0 e 99
    printf("Numero casuale generato tra 0 e 99: %d\n", randomIntInRange);

    // Il modulo limita l'intervallo, ma non migliora la qualità del generatore e non
    // elimina eventuali correlazioni della sequenza.


    printf("\n//===============================================================\n\n");

    
    // Ma se non volessimo 0 come minimo bensì un altro numero?

    int min = 50;
    int max = 100;

    int randBetweenMinMax1 = (rand() % (max - min + 1)) + min; // numero tra 50 e 100
    int randBetweenMinMax2 = (rand() % (max - min + 1)) + min; // numero tra 50 e 100
    int randBetweenMinMax3 = (rand() % (max - min + 1)) + min; // numero tra 50 e 100
    // La formula include entrambi gli estremi, purché min <= max e l'ampiezza sia valida.
    
    printf("Numero casuale generato tra %d e %d: %d\n", min, max, randBetweenMinMax1);
    printf("Numero casuale generato tra %d e %d: %d\n", min, max, randBetweenMinMax2);
    printf("Numero casuale generato tra %d e %d: %d\n", min, max, randBetweenMinMax3);

    return 0;
}
