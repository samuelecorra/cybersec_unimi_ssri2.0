/*
Il for loop è un ciclo precondizionato, come il while loop, ma la sua sintassi
è più compatta e consente di gestire in modo più chiaro le variabili di controllo.
In un for loop, infatti, è possibile inizializzare la variabile di controllo, 
definire la condizione di continuazione e specificare l'operazione di incremento
o decremento in un'unica riga di codice.

La struttura di un for loop è la seguente:

for (inizializzazione; condizione; aggiornamento) {
   // blocco di codice da eseguire
}

Quando torna utile rispetto al while loop?
// Quando si conosce a priori il numero di iterazioni da eseguire, o quando
// si lavora con array o strutture dati indicizzate!

Ecco un esempio di utilizzo del for loop in C:  */

#include <stdio.h>
#include <windows.h> // per Sleep()
// Supponiamo di voler stampare cinque volte un messaggio di sveglia:

int main() {

   for (int i = 0; i < 5; i++) { // i parte da 0, finché è minore di 5, incrementa di 1
      printf("Forza soldato, svegliati (%d)!\n", i+1); // i+1 per stampare da 1 a 5
   }

   // In questo esempio, la variabile di controllo i viene inizializzata a 0,
   // la condizione di continuazione è che i sia minore di 5, e l'operazione
   // di incremento è i++. Il blocco di codice all'interno del for loop verrà
   // eseguito cinque volte, con i che assume valori da 0 a 4.

   printf("Premi invio per continuare...\n");
   getchar(); // per catturare l'invio finale

   for (int i = 10; i > 0; i--) { // i parte da 10, finché è maggiore di 0, decrementa di 1
        Sleep(500); // pausa di 500 millisecondi (0.5 secondi) 
        printf("Conto alla rovescia: %d\n", i);
    }
    printf("Decollo!\n");

}

// ATTENZIONE: L'INCREMENTO/DECREMENTO E' A PIACERE, NON E' OBBLIGATORIO USARE I++/I--
// SI PUO' USARE ANCHE I+=2, I-=3, I*=2, I/=2, ECC.

// E' POSSIBILE ANCHE USARE PIU' VARIABILI DI CONTROLLO, SEPARATE DA UNA VIRGOLA
// ESEMPIO: for (int i = 0, j = 10; i < 10; i++, j--) { ... }
// E' POSSIBILE ANCHE NON USARE L'INIZIALIZZAZIONE
// ESEMPIO: int i = 0; for (; i < 10; i++) { ... }
// E' POSSIBILE ANCHE NON USARE L'AGGIORNAMENTO
// ESEMPIO: for (int i = 0; i < 10; ) { ... i++; ... }