/* 

In questo modulo discutiamo di come condizionare il flusso esecutivo del programma C
che scriviamo.

Iniziamo introducendo le istruzioni condizionali, in particolare le if statements.

// IF STATEMENTS

// Sono principalmente 3:

1.  if (condizione) {
        blocco di codice da eseguire se la condizione è vera
    }
    blocco di codice da eseguire sempre dopo l'if

La prima è la più semplice, e si limita a eseguire un blocco di codice solo se una
determinata condizione è vera (true). Se la condizione è falsa (false), il blocco di 
codice non viene eseguito, e si passa direttamente al blocco successivo.

2.  if (condizione) {
        blocco di codice da eseguire se la condizione è vera
    } else {
        blocco di codice da eseguire se la condizione è falsa
    }
    blocco di codice da eseguire sempre dopo l'if-else

Ma in certi casi può essere utile eseguire un blocco di codice se la condizione è vera,
e un altro blocco di codice se la condizione è falsa. In questo caso si usa la struttura
if-else, che permette di specificare due blocchi di codice distinti in base all'esito
della valutazione della condizione.

3. if (condizione1) {
        blocco di codice da eseguire se condizione1 è vera
    } else if (condizione2) {
        blocco di codice da eseguire se condizione1 è falsa e condizione2 è vera
    } else {
        blocco di codice da eseguire se entrambe le condizioni sono false
    }
    blocco di codice da eseguire sempre dopo l'if-else if-else

Infine, in alcuni casi può essere necessario valutare più di una condizione in sequenza,
eseguendo blocchi di codice diversi in base a quale condizione risulta vera. In questo
caso si usa la struttura if-else if-else, che permette di specificare più condizioni e
i relativi blocchi di codice da eseguire.
Ovviamente ci possono essere tanti "else if" quanti ne servono, ma alla fine può esserci
solo un "else" finale, che cattura tutti i casi in cui nessuna delle condizioni precedenti
è risultata vera, ovvero un "catch-all", o "default case".


//====================================================================================


Esempio pratico:    */

#include <stdio.h>
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <math.h>               // direttiva per funzioni matematiche
#include <stdbool.h>           // direttiva per usare il tipo bool

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    int età;
    printf("Inserisci la tua età: ");
    scanf("%d", &età); // %d per int
    getchar(); // consuma il newline rimasto nel buffer

    if (età < 0) {
        printf("Errore: l'età non può essere negativa.\n");
        // Usciamo dal programma con codice di errore
        return 1;
    }

    // Se superiamo questo if allora l'età è sicuramente >= 0 e quindi valida.
    // Avvisiamo l'utente:
    printf("Hai inserito un'età valida: %d\n", età);
    
    printf("Premi Invio per continuare...\n");
    getchar(); // aspettiamo che l'utente prema Invio

    if (età < 16) {
        printf("Non hai l'età minima per iniziare a lavorare.\n");
    } else {
        printf("Hai l'età minima per iniziare a lavorare.\n");
    }

    // Attenzione a come però decidiamo di costruire l'if-else if-else:
    if (età > 18) {
        printf("Hai l'età minima per guidare\n");
    } else if (età > 80) {
        printf("Sei troppo anziano per metterti alla guida!\n");
    } else {
        printf("Non sei maggiorenne, ergo non puoi guidare!\n");
    }
    // Nella cascata condizionale appena presentata, se qualcuno inserisse
    // 90 come età, gli verrebbe detto che può guidare, ma ciò non rispetta la logica
    // desiderata del programma!

    // Allora poniamo attenzione a come costruire i nostri controlli combinati,
    // perché l'ordine riflette la logica:
    // E' buona prassi mettere i controlli più restrittivi prima,
    // e i più permissivi dopo:
    if (età > 80) {
        printf("Sei troppo anziano per metterti alla guida!\n");
    } else if (età > 18) {
        printf("Hai l'età minima per guidare\n");
    } else {
        printf("Non sei maggiorenne, ergo non puoi guidare!\n");
    }

// ====================================================================================

// Regole d'oro da ricordare per le if statements:

// La condizione, qualsiasi essa sia, fa prendere il ramo true se è diversa da zero,
// e prende il ramo false se è uguale a zero.

// Quindi è logico che metteremo all'interno della condizione un'espressione
// che ritorna un valore booleano, ovvero true (diverso da zero, di solito 1) o false
// (uguale a zero).

// E' ovvio che questo si presta bene ai confronti numerici, o ai confronti tra stringhe
// che vedremo dopo, perché tali operatori matematici e funzioni built in di string.h
// ritornano valori booleani per costruzione, ovvero proprio per facilitare la scrittura
// di condizioni.

// Ma se come condizione mettessimo una variabile di tipo int, float, double, char,
// o qualsiasi altro tipo di dato, il comportamento sarebbe lo stesso:

    int countdown = 0;

    if (countdown) { // equivalente a if (countdown != 0)
        printf("Countdown non è zero, quindi la condizione è vera!\n");
    } else {
        printf("Countdown è zero, quindi BUON ANNO!\n");
    }
    
    double temperatura = 0.0;
    if (!temperatura) { // equivalente a if (temperatura == 0.0)
        printf("La temperatura è zero, quindi siamo al punto di congelamento dell'acqua!\n");
    } else {
        printf("La temperatura non è zero, quindi la condizione è falsa!\n");
    }

    char lettera = '\0'; // carattere nullo
    if (lettera) { // equivalente a if (lettera != '\0')
        printf("La lettera non è il carattere nullo!\n");
    } else {
        printf("La lettera è il carattere nullo!\n");
    }

    // La comodità del tipo bool è che rende il codice più leggibile, in quanto
    // l'uguaglianza a 1 o a 0 è implicita, ergo facoltativa:
    
    bool èStudente = true; // false è definito in stdbool.h come 0, true come 1
    
    if (èStudente) { // equivalente a if (èStudente != false)
        printf("Sei uno studente!\n");
    } else {
        printf("Non sei uno studente!\n");
    }

    // Ma possiamo anche esplicitarla ovviamente:
    if (èStudente == true) {
        printf("Sei uno studente! (controllo esplicito)\n");
    } else {
        printf("Non sei uno studente! (controllo esplicito)\n");
    }

    return 0;
}