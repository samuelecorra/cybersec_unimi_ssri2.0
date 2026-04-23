/*

//================================================================================


Ora che sappiamo cosa sono le variabili in C, vogliamo logicamente vederne il
contenuto anche al di fuori del nostro file sorgente, cioè vogliamo stampare
il loro valore sulla console. Per fare questo, usiamo la funzione printf(),
che sta per "print formatted", cioè "stampa formattata". Questa funzione
permette di stampare del testo sulla console, e di includere al suo interno
il valore di variabili di diversi tipi, formattandole in modo opportuno.


//================================================================================


Per usare la funzione printf(), dobbiamo includere la libreria standard di
input/output, che si chiama stdio.h. Questa libreria contiene la dichiarazione
della funzione printf(), e altre funzioni utili per l'input/output, come scanf()
per leggere dati dalla console.


//================================================================================


La sintassi della funzione printf() è la seguente:

int printf(const char *format, ...);

Il primo parametro è una stringa di formato, che contiene del testo normale
e dei segnaposto per le variabili che vogliamo stampare. I segnaposto sono
dei caratteri speciali che iniziano con il simbolo %, seguiti da una lettera
che indica il tipo di variabile da stampare. Ad esempio, %d è un segnaposto
per un intero, %f è un segnaposto per un numero in virgola mobile, e %s è
un segnaposto per una stringa.

Il secondo parametro (e gli eventuali successivi) sono le variabili da stampare,
che devono corrispondere ai segnaposto nella stringa di formato, sia per tipo
che per ordine. Vedremo nelle prossime lezioni in dettaglio i vari segnaposto
e come usarli.
*/

//================================================================================

// Iniziamo da esempi semplici e poi nella prossima lezione sui vari specificatori
// amplieremo i nostri orizzonti!

#include <stdio.h>
#include <stdbool.h>      // necessario per usare il tipo bool

int main() {
    printf("Hello, World!\n"); // stampa una stringa fissa

    int età = 21;
    printf("Ho %d anni.\n", età); // stampa un intero

    float altezza = 1.77;
    printf("La mia altezza è %f metri.\n", altezza); // stampa un float con 2 decimali

    char iniziale = 'Y';
    printf("La mia iniziale è %c.\n", iniziale); // stampa un carattere

    double terraSole = 149597870.7;
    printf("La distanza media Terra-Sole è %.1f km.\n", terraSole); // stampa un double con 1 decimale
    
    // per quanto riguarda le stringhe, non abbiamo ancora introdotto gli array
    // ma per ora ci basti sapere che NON ESISTE il tipo "stringa" in C!
    // le stringhe sono in realtà degli array di char terminati da un carattere speciale!
    char nome[] = "Erica";
    printf("Mi chiamo %s.\n", nome); // stampa una stringa

    bool èStudente = true;
    printf("I booleani in C restituiscono %d per true e %d per false.\n", èStudente, !èStudente); // stampa un bool come intero
                                        // 1           // 0

    return 0; // termina il programma con successo
}

//================================================================================
