/*
Cos'è lo scope di una variabile?
Lo scope di una variabile è la porzione di codice in cui la variabile è visibile e 
accessibile. 
In italiano, si può tradurre come "ambito" o "visibilità".

// REGOLA D'ORO:
// VARIABILI NEL MEDESIMO SCOPE NON POSSONO AVERE LO STESSO NOME!
// VARIABILI IN SCOPE DIVERSI POSSONO AVERE LO STESSO NOME!

In C, lo scope può essere globale o locale:

1. Scope Globale:
   - Una variabile dichiarata al di fuori di qualsiasi funzione ha uno scope globale.
   - È accessibile da qualsiasi funzione all'interno dello stesso file o in altri file 
     se dichiarata con la parola chiave `extern`.
   - Spesso risulta utile per condividere dati tra diverse funzioni o moduli, ma anche
     problematica se non gestita correttamente, poiché può portare a conflitti di
     nomi e difficoltà nel debug (molto arduo debuggare codice che fa uso di molte
     variabili globali!)

2. Scope Locale:
   - Una variabile dichiarata all'interno di una funzione ha uno scope locale.
    - È accessibile solo all'interno di quella funzione.
    - Le variabili locali sono create quando la funzione viene chiamata e distrutte
      quando la funzione termina.
    - Utilizzare variabili locali aiuta a evitare conflitti di nomi e rende il codice
      più facile da comprendere e mantenere.
    Inoltre, le variabili locali possono essere dichiarate come `static` per mantenere
    il loro valore tra le chiamate della funzione, ma rimangono comunque locali alla
    funzione in cui sono state dichiarate. Vedremo poi approfonditamente questa keyword!    */

#include <stdio.h>

// Dichiarazione di una variabile globale
int risultato = 0; // Variabile globale

int somma(int a, int b) {
    // Dichiarazione di una variabile locale
    int risultato = a + b;
    return risultato;
}

int main() {
    int x = 5, y = 10;

    // Chiamata della funzione somma
    risultato = somma(x, y);

    // Stampa del risultato
    printf("Risultato della somma: %d\n", risultato); // Stampa la variabile globale

    // Noteremo che verrà stampato il valore della variabile locale, quindi vince
    // lo scope più interno (locale) rispetto a quello globale. Questo si chiama
    // shadowing, ovvero l'oscuramento di una variabile globale da parte di una
    // variabile locale con lo stesso nome. Ecco spiegato perché sono sconsigliate le
    // variabili globali!


// ====================================================================================


    // Per completezza, ricordiamo che esistono anche le variabili con scope di blocco:
    // all'interno di un ciclo for o di un'istruzione if, ad esempio, se dichiariamo
    // qualche variabile (la i di indice dei cicli for, ad esempio), questa avrà
    // scope di blocco, ovvero sarà visibile e accessibile solo all'interno del blocco
    // in cui è stata dichiarata, e alla fine del blocco verrà distrutta!

    return 0;
}