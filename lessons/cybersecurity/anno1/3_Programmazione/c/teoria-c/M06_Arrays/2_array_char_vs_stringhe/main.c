
// Poniamo il focus sugli array di caratteri:

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {


    // Qui come si può vedere inizializziamo un array di caratteri:
    // Il punto è che in C non esiste il tipo stringa, ma si usano gli array di caratteri
    // per rappresentare le stringhe di testo.
    // Quindi, sia che noi dichiariamo un array di caratteri, sia che dichiariamo una stringa,
    // in entrambi i casi stiamo effettivamente lavorando con un array di caratteri.
    
    char lettereDiDustin[] = {'D', 'u', 's', 't', 'i', 'n'};
    char nome1[] = "Dustin"; // dichiarazione e inizializzazione di una stringa
    
    // E' proprio per questo che possiamo usare le funzioni di manipolazione delle stringhe
    // anche su array di caratteri come quello sopra, che non sembrano contenere una
    // stringa vera e propria ma solo una sequenza di caratteri!
    for(int i = 0; i < strlen(lettereDiDustin); i++) {
        printf("%c ", lettereDiDustin[i]);
    }

    printf("\n");

    // Mentre qui ci sembra più ovvio che stiamo lavorando con una stringa ergo usare
    // le sue funzioni:
    for(int i = 0; i < strlen(nome1); i++) {
        printf("%c", nome1[i]);
    }

    printf("\n");


//=====================================================================================


    // La comodità risiede nel fatto che possiamo comodamente accedere ai singoli
    // caratteri della sequenza/stringa che dir si voglia mediante
    // l'indicizzazione con operatore di accesso agli elementi []:

    printf("Primo carattere di nome1: %c\n", nome1[0]);
    printf("Secondo carattere di nome1: %c\n", nome1[1]);
    printf("Terzo carattere di nome1: %c\n", nome1[2]);
    printf("Quarto carattere di nome1: %c\n", nome1[3]);
    printf("Quinto carattere di nome1: %c\n", nome1[4]);
    printf("Sesto carattere di nome1: %c\n", nome1[5]);
    printf("\n");

    printf("//==============================================================");

    printf("\n\n");

    // Come facciamo a generalizzare la condizione di i < strlen(nome1) per tutti gli
    // array non sapendo il loro tipo e la loro dimensione?
    // Semplice, usiamo sizeof() che ci restituisce la dimensione in byte
    // dell'array in questione, e la dividiamo per la dimensione in byte del tipo
    // di dato che contiene - ovvero del singolo elemento, 
    // ottenendo così il numero di elementi:

    for(int i = 0; i < sizeof(nome1) / sizeof(nome1[0]); i++) {
        printf("%c", nome1[i]);
    }

    printf("\n\n");


    // Facciamo lo stesso con un array non-stringa per sincerarci della validità del metodo:
    int numeri[] = {10, 20, 30, 40, 50};

    int dimensioneArrayNumeri = sizeof(numeri) / sizeof(numeri[0]);
    printf("Dimensione dell'array numeri: %d\n", dimensioneArrayNumeri);

    // E la usiamo dentro al ciclo nella condizione:
    for(int i = 0; i < dimensioneArrayNumeri; i++) {
        printf("%d ", numeri[i]);
    }
    
    printf("\n\n");

    return 0;
}

