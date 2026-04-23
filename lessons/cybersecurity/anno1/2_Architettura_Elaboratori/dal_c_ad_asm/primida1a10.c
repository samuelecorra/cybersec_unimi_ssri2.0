
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Vogliamo realizzare un programma d'esempio che possa poi essere riscritturato in Assembly LC-2, il
// linguaggio di basso livello della CPU virtuale creata da Patt e Patel, docenti all'Università della
// Virginia.

// Questo esempio ci servirà per comprendere meglio che abbiamo iniziato a programmare in alto livello
// con C, ovvero mediante astrazioni della macchina fisica sottostante:

// Abbiamo sempre fatto affidamento a variabili tipizzate che ci facilitano non poco il lavoro;
// Strutture di controllo evolute, come cicli e condizionali bidirezionali, che ci permettono di
// esprimere in modo chiaro e conciso le nostre intenzioni;
// E questo ci ha sempre garantito LA PORTABILITÀ del codice, ovvero la possibilità di
// scrivere un programma che può essere eseguito su macchine diverse senza dover riscrivere il codice
// per farlo combaciare con la logica di ogni CPU sottostante!

// Ma in Assembly LC-2, non abbiamo nulla di tutto ciò: dobbiamo scrivere il codice in modo
// molto più dettagliato e specifico, gestendo direttamente i registri e le istruzioni della CPU.
// Questo significa che perdiamo gran parte delle astrazioni e della portabilità che avevamo con C,
// ma guadagniamo in controllo e efficienza.

// Non si ha portabilità, né strutture di controllo evolute, ma a questo possiamo comunque "ovviare"...
// Non potremo scrivere if-then-else, ma possiamo simularne perfettamente il comportamento!


//////////////////////////////////////////////////////////////////////////////////////////////////////////////


// Il programma in questione ci permette di calcolare e ottenere a video tutti i numeri primi compresi tra
// 1 e 10, utilizzando un algoritmo molto semplice e intuitivo, almeno per quanto riguardo la sua
// implementazione ad alto livello con comode astrazioni! Ecco perché partiamo dal C verso l'asm e non viceversa!

#include <stdio.h>
#define MAXI 9

#define FALSE 0
#define TRUE !FALSE   // ovvero neghiamo bit a bit il valore FALSE, ottenendo TRUE

int main() {
   int i, j, isPrime, prod;    // due variabili di ciclo, una per il flag primo e una per il prodotto

   for ( i=1 ; i<=MAXI ; i++ ) {  // ciclo per i numeri da 1 a 9
      isPrime = TRUE;  // inizializziamo il flag primo a TRUE per ogni numero, e quindi l'idea è andare
        // col secondo ciclo a escludere coloro che non sono effettivamente primi tramite un controllo

    // Questo controllo si poggia sull'idea che, se un numero i non è primo, allora i numeri da 2 a i-1
    // ovvero una opportuna variabile j che viene inizializzata a 2 e incrementata fino a i-1, tramite ciclo,
    // se moltiplicati per un certo valore k, NON restituiscono mai il valore i:

    // ma in Assembly LC-2 non abbiamo la possibilità di moltiplicare, se non con un trucco che
    // simula la moltiplicazione con un ciclo di somma, quindi ecco che si spiega perché abbiamo iniziato
    // il corso di Programmazione con algoritmi in pseudocodice che trattavano moltiplicazioni e divisioni
    // in veste di somme o sottrazioni ripetute!

    // Abbiamo dichiarato precedentemente una variabile prod, che inizializziamo a 0, all'interno del
    // secondo ciclo for annidato, col quale valutiamo se il numero i è primo o meno:

    for ( j=2 ; j<i ; j++ ) {  // ciclo per i numeri da 2 a i-1

        prod = 0;  // inizializziamo il prodotto a 0
        while ( prod < i )  // finché il prodotto è minore di i
            prod += j;  // sommiamo j al prodotto, simulando la moltiplicazione
            if ( prod == i )  // se il prodotto è uguale a i, allora non è primo
                isPrime = FALSE;  // impostiamo il flag primo a FALSE
      
    }

    if ( isPrime == TRUE )  // se il flag primo è ancora TRUE, allora i è primo
        printf("\n%d è primo e compreso tra 1 e 10", i);  // stampiamo il numero primo trovato
    else
        printf("\n%d non è primo e compreso tra 1 e 10", i);  // altrimenti, stampiamo che non è primo
   }

   printf("\n\nPremere invio per terminare il programma...");
   getchar();  // attendiamo che l'utente prema invio per terminare il programma

}

// Ora riportiamo tutto ciò su LC-2Edit, ovvero l'editor di codice Assembly LC-2, MA COME COMMENTO DI PARTENZA,
// per farci un'idea chiara di come trasformare il tutto...
    

