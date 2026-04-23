/* I cicli sono strutture di controllo che permettono di ripetere un blocco di codice
   più volte, fino al verificarsi di una certa condizione. In C, i cicli più comuni
   sono il ciclo while e il ciclo for. 
   
Partiamo dal ciclo while:

UN WHILE LOOP ITERA CODICE FINTANTO CHE LA CONDIZIONE È VERA! 
CONDIZIONE NECESSARIA PER LA PRIMA ESECUZIONE DEL CICLO E QUINDI L'ENTRATA NEL CICLO
E' FONDAMENTALE CHE LA CONDIZIONE SIA INIZIALMENTE VERA, ALTRIMENTI IL CICLO NON
VERREBBE MAI ESEGUITO! 

E' DUNQUE LA CONCRETIZZAZIONE DI QUEL CHE AVEVAMO CHIAMATO "CICLO PRECONDIZIONATO"
QUANDO STUDIAVAMO INFORMATICA TEORICA!

Per ora limitiamoci a un esempio semplice:   */

#include <stdio.h>

int main() {
   
   int numeroDaInserire;

   while (numeroDaInserire != 2000) {  // finché numeroDaInserire è diverso da 2000
   
      printf("Inserisci un numero (2000 per terminare): ");
        scanf("%d", &numeroDaInserire);
        getchar(); // per catturare il newline lasciato da scanf
        printf("Hai inserito: %d\n", numeroDaInserire);
   
   } // fine del ciclo while

   // Il ciclo è costruito bene perché si può realizzare la condizione di uscita!

   printf("Hai inserito 2000, il ciclo termina!\n");
   printf("Premi invio per continuare...\n");
   getchar(); // per catturare l'invio finale

//=====================================================================================

// Per quanto riguarda invece il do while, si tratta di un ciclo post-condizionato,
// ovvero la condizione viene verificata DOPO l'esecuzione del blocco di codice, ergo
// il ciclo viene eseguito almeno una volta, a prescindere dal fatto che la condizione
// sia vera o falsa.

   int età = 17;

   do {
      printf("Benvenuto in discoteca!\n");
   } while (età > 18); // finché età è minore di 18
   printf("Mi spiace, non puoi entrare.\n");
   
// Qui la condizione di uscita è già realizzata da prima che si entri nel ciclo,
// ma il ciclo viene eseguito comunque una volta, perché la condizione viene
// verificata DOPO l'esecuzione del blocco di codice.
   
   printf("Premi invio per continuare...\n");
   getchar(); // per catturare l'invio finale

// Metodi usuali per interrompere un ciclo:
// 1) La condizione di uscita viene realizzata all'interno del ciclo, mediante
//    un'istruzione di controllo condizionale (if)
// 2) Si usa un'istruzione di salto (break) per uscire dal ciclo
// 3) Operatori di incremento/decremento per modificare la variabile di controllo
// che detterà la condizione di uscita

   return 0;

}
