/* Introduciamo ora la funzione scanf() per l'input dell'utente.

Sintassi generica:
scanf("formato", &variabile);       // Ha due parametri passati:

// 1) Una stringa di formato (come per printf())
// 2) L'indirizzo di memoria della variabile in cui salvare il valore,
// raggiunto mediante l'operatore & (operatore "address of", ampersand).


//================================================================================


// Di solito è buona pratica precedere scanf() con un printf() che spiega
// all'utente cosa deve inserire.                                                   */

#include <stdio.h>
#include <windows.h>    // necessario per SetConsoleOutputCP() su Windows
#include <string.h>   // necessario per strlen()

int main() {

    // Settiamo la console in UTF-8 per poter usare caratteri accentati
    // (funziona solo su Windows, su Linux e MacOS la console è già in UTF-8)
    SetConsoleOutputCP(CP_UTF8);

    // Dichiarazione variabili
    int età;
    float altezza;
    char iniziale;
    char nome[50]; // array di char per la stringa del nome
    char nomeEcognome[50]; // array di char per nome e cognome


    // Richiesta
    printf("Quanti anni hai? ");
    // Inserimento
    scanf("%d", &età); // %d per int
    // Risposta
    printf("Ciao! Hai %d anni.\n", età);

    printf("Qual è la tua altezza in metri (es. 1.75)? ");
    scanf("%f", &altezza); // %f per float
    printf("La tua altezza è %.2f metri.\n", altezza);

    printf("Qual è l'iniziale del tuo nome? ");
    scanf(" %c", &iniziale); // % c per char, lo spazio prima di %c serve a ignorare la
                             // newline lasciata nel buffer da un input precedente
    printf("La tua iniziale è %c.\n", iniziale);

    getchar(); // consuma la newline rimasta nel buffer

    printf("Qual è il tuo nome? ");
    scanf("%s", nome); // %s per stringhe
    printf("Ciao %s!\n", nome);

    // ATTENZIONE: scanf() con %s legge solo fino al primo spazio, ergo NON SA LEGGERE
    // ALCUN TIPO DI SPAZIO, ovvero spazio, tabulazione, newline.
    // Quindi se l'utente inserisce "Mario Rossi", verrà letto solo "Mario".
    printf("Qual è il tuo nome e cognome? ");
    scanf("%s", nomeEcognome); // %s per stringhe
    printf("Ciao %s!\n", nomeEcognome); // Stampa solo il nome, non il cognome

    // Per l'ultimo esempio, puliamo il buffer di input per evitare problemi:
    // Non preoccupiamoci di questo strano codice per ora, lo vedremo in dettaglio
    // nel capitolo sui file:

    int c; // variabile per leggere i caratteri dal buffer
    while ((c = getchar()) != '\n' && c != EOF) {
        // svuota il buffer
    }

    // Ora il buffer è pulito, possiamo usare un metodo migliore per leggere
    // stringhe con spazi:

    // Si introdurrà meglio nel capitolo dedicato al file handling, ma è bene sapere
    // già che esiste una funzione dedicata alla lettura di stringhe con spazi:
    // fgets();

    // La sua sintassi generica prevede:
    // fgets(variabile, dimensione, stdin);

    // Partiamo dalla fine degli argomenti passati: si passa un certo puntatore a file,
    // ovvero una certa sorgente da cui leggere. stdin è lo standard input, aka tastiera.
    // La dimensione è la dimensione massima della stringa che si vuole leggere,
    // per evitare buffer overflow. Infine si passa la qualsiasi variabile in cui salvare
    // la stringa letta, che può essere benissimo un array di char ma anche un file pointer.
    // Esempio:
    printf("Qual è il tuo nome e cognome? ");
    fgets(nomeEcognome, sizeof(nomeEcognome), stdin); // legge fino a 49 caratteri + null terminator
    
    // Però fgets legge fino al newline di submit, quindi include il newline nella stringa letta.
    // Se non vogliamo questo comportamento, dobbiamo rimuovere il newline manualmente:
    // Anticipiamo una funzione banale che useremo quando parleremo di stringhe:
    
    nomeEcognome[strlen(nomeEcognome) - 1] = '\0'; 
    
    // rimuove il newline se presente
    // settandolo a null terminator (funziona solo se l'utente ha inserito almeno un carattere!!
    
    printf("Ciao %s!\n", nomeEcognome); // Stampa sia il nome che il cognome, SENZA
    // FAR ANDARE A CAPO IL PUNTO ESCLAMATIVO!

    return 0;
}