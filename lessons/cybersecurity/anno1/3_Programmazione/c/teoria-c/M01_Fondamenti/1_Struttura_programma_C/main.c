/* FONDAMENTI DI C: STRUTTURA DI UN PROGRAMMA

Un programma in C è essenzialmente una funzione, detta "main", ovvero appunto
la funzione principale, che viene eseguita per prima quando il programma viene
avviato. Tutta la logica interna del programma SOTTOSTÀ alla funzione main,
che può a sua volta chiamare altre funzioni, definite dall'utente o predefinite.

Non interessiamoci per ora a quello che possiamo scrivere dentro al main, bensì
al suo esterno: infatti si noti subito che il main ha un tipo di ritorno, "int",
che sta per "intero" (integer in inglese), e che indica che la funzione main
restituisce un valore intero al sistema operativo quando termina la sua esecuzione.

// Di solito, ritorna:
- 0 se il programma termina correttamente
- un numero diverso da 0 se il programma termina con un errore

// Quando non prende nulla in ingresso, ci sono due sintassi ugualmente valide:

int main() {
    // corpo della funzione
}

// oppure

int main(void) {
    // corpo della funzione
}

Ma nulla vieta che il main possa ricevere dei parametri in ingresso, che per ora non ci
interessano, ma che vedremo in futuro.
La sintassi completa della funzione main è quindi:

int main(int argc, char *argv[]) {
    // corpo della funzione
}

// Per ora ci limitiamo a dire che argc sono il numero di argomenti passati
// al programma, e argv è un array di stringhe che contiene gli argomenti stessi!


//================================================================================


// MA PRIMA DEL MAIN? 
Prima della funzione main, possiamo trovare delle direttive di preprocessore,
che iniziano con il simbolo #, e che servono a includere delle librerie,
definire delle costanti, o altre operazioni di preprocessamento che sono dunque
eseguite prima della compilazione vera e propria del codice. Le macro infatti
rimpiazzano testualmente il codice sorgente prima che questo venga compilato, così
come le librerie incluse con #include vengono "copiate e incollate" nel punto
in cui sono incluse ancor prima che il compilatore inizi a leggere il codice!

Non includiamo le librerie INTERE, cioè non includiamo il loro implementativo.c,
ma solo il loro file di intestazione .h, che contiene le dichiarazioni delle
funzioni e delle strutture dati che la libreria mette a disposizione. In questo
modo, il compilatore sa quali funzioni e strutture sono disponibili senza
avere bisogno del codice sorgente completo della libreria. Il codice sorgente
della libreria viene invece collegato (linkato) al nostro programma in una fase
successiva, detta appunto fase di linking, che avviene dopo la compilazione!


*///==============================================================================


#include <stdio.h>              // direttiva

int main() {                    // ingresso nel main
    printf("Hello, World!\n");  // corpo
    return 0;                   // del main
}                               // uscita dal main


/*================================================================================


Qualche nota aggiuntiva:

- Dal C99 in poi, se main termina senza un return, il compilatore sottintende 
return 0;, ma è buona norma scriverlo sempre esplicitamente per chiarezza e portabilità.

- Ci sono due tipi di commenti in C:
  - Commenti su più righe, racchiusi tra /* e */                                                /*
  - Commenti su una sola riga, che iniziano con // e terminano alla fine della riga


//================================================================================


🔹 Fasi di traduzione di un programma C


Preprocessing

Risolve direttive che iniziano con # (#include, #define, #ifdef …).
I file .h vengono copiati testualmente dentro al sorgente.
I commenti vengono rimossi.
Risultato: un file sorgente preprocessato (ancora testo C, ma già espanso).


Compilazione vera e propria

Il codice C preprocessato viene tradotto in assembly per l’architettura target.
Risultato: un file assembly (.s).


Assemblaggio
L’assembly viene tradotto in codice oggetto binario (macchina, ma ancora incompleto).
Risultato: un file oggetto (.o).


Linking
I vari file oggetto e le librerie precompilate vengono uniti in un unico file eseguibile.
Risultato: l’eseguibile finale (su Linux tipicamente a.out, su Windows .exe).


🔹 Schema riassuntivo

Sorgente .c
   ↓ (preprocessing)
Codice C preprocessato
   ↓ (compilazione)
Assembly .s
   ↓ (assemblaggio)
Oggetto .o
   ↓ (linking)
Eseguibile


//================================================================================

In molti compilatori si trova anche void main(), ma non è conforme allo standard C.
È accettato solo come estensione non portabile, quindi da evitare.

//================================================================================*/