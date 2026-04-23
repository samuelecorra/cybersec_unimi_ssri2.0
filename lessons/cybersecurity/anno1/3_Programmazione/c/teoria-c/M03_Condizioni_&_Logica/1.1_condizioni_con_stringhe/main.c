/*

// Finora abbiamo lavorato con condizioni degli if che valutavano principalmente numeri,
// ma anche i confronti tra stringhe sono molto comuni, e spesso necessari, e
// soprattutto restituiscono booleani che rendono l'implementazione di if statements
// molto semplice e diretta!                */

#include <stdio.h>
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <math.h>               // direttiva per funzioni matematiche
#include <stdbool.h>           // direttiva per usare il tipo bool
#include <string.h>            // direttiva per funzioni di manipolazione stringhe

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    char nome[10];           // array di char per il nome

    printf("Come ti chiami? ");
    fgets(nome, sizeof(nome), stdin); // leggi una riga di input con spazi
    nome[strcspn(nome, "\n")] = '\0'; // rimuovi il newline '\n' se presente
    // la funzione strcspn(), che sta per "string complement span", restituisce
    // l'indice del primo carattere trovato nella stringa letterale passatagli
    // ("\n" in questo caso), o la lunghezza della stringa se non trovato.
    // Questo è utile per rimuovere il newline '\n' lasciando il resto della stringa intatto.

    // lasciando il newline '\n' nel buffer di input. 
    // Questo può dare problemi se poi usiamo un'altra scanf/fgets subito dopo.

    // E ora che abbiamo immagazzinato il nome, possiamo iniziare a divertirci:

    if (strlen(nome) == 0) { // se la lunghezza del nome è zero
        printf("Non hai inserito il tuo nome!\n");
    } else if (strcmp(nome, "Principessa") == 0) { // se il nome è esattamente "Principessa"
        printf("Ciao Principessa! Che bello sapere che sei qui!\n");
    } else {
        printf("Ciao persona che non è Principessa, non ho piacere di conoscerti!\n");
    }
}

// Le funzioni built in per le stringhe come strlen() e strcmp() sono molto utili
// per lavorare con le condizioni degli if statements, ci basti sapere per ora
// che strlen() restituisce la lunghezza della stringa (escluso il terminatore '\0');
// strcmp() confronta due stringhe e restituisce 0 se sono uguali, un valore negativo
// se la prima è "minore" della seconda (in ordine lessicografico),
// e un valore positivo se la prima è "maggiore" della seconda.
// Per maggiori dettagli su queste funzioni, e altre utili per lavorare con le stringhe,
// rimandiamo alle lezioni successive e alla documentazione ufficiale di C.  */