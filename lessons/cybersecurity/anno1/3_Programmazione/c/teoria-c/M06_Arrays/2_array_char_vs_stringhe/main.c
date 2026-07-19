
// Poniamo il focus sugli array di caratteri:

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {


    // Qui come si può vedere inizializziamo un array di caratteri:
    // Il punto è che in C non esiste il tipo stringa, ma si usano gli array di caratteri
    // per rappresentare le stringhe di testo.
    // Ogni stringa è memorizzata in un array di char, ma non ogni array di char è una
    // stringa: per esserlo deve contenere un terminatore '\0' entro i propri limiti.
    
    char lettereDiDustin[] = {'D', 'u', 's', 't', 'i', 'n'};
    char nome1[] = "Dustin"; // dichiarazione e inizializzazione di una stringa
    
    // lettereDiDustin NON contiene '\0', quindi non può essere passato a strlen o %s:
    // farlo leggerebbe oltre l'array e causerebbe comportamento indefinito. Per scorrere
    // tutti i suoi elementi usiamo la dimensione dell'array.
    size_t numeroLettere = sizeof(lettereDiDustin) / sizeof(lettereDiDustin[0]);
    for(size_t i = 0; i < numeroLettere; i++) {
        printf("%c ", lettereDiDustin[i]);
    }

    printf("\n");

    // Mentre qui ci sembra più ovvio che stiamo lavorando con una stringa ergo usare
    // le sue funzioni:
    size_t lunghezzaNome = strlen(nome1);
    for(size_t i = 0; i < lunghezzaNome; i++) {
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

    // Come calcoliamo il numero di elementi di un vero array quando il suo tipo e la
    // sua dimensione sono noti nel medesimo scope?
    // Semplice, usiamo sizeof() che ci restituisce la dimensione in byte
    // dell'array in questione, e la dividiamo per la dimensione in byte del tipo
    // di dato che contiene - ovvero del singolo elemento, 
    // ottenendo così il numero di elementi:

    // Per nome1 il rapporto vale 7 perché include anche il terminatore '\0'. Sottraiamo
    // quindi 1 se vogliamo stampare soltanto i sei caratteri visibili.
    for(size_t i = 0; i < (sizeof(nome1) / sizeof(nome1[0])) - 1; i++) {
        printf("%c", nome1[i]);
    }

    printf("\n\n");


    // Facciamo lo stesso con un array non-stringa per sincerarci della validità del metodo:
    int numeri[] = {10, 20, 30, 40, 50};

    size_t dimensioneArrayNumeri = sizeof(numeri) / sizeof(numeri[0]);
    printf("Dimensione dell'array numeri: %zu\n", dimensioneArrayNumeri);

    // E la usiamo dentro al ciclo nella condizione:
    for(size_t i = 0; i < dimensioneArrayNumeri; i++) {
        printf("%d ", numeri[i]);
    }

    // Questa tecnica funziona solo dove l'oggetto è ancora un array. Quando un array
    // viene passato a una funzione, il parametro è normalmente adattato a puntatore e
    // sizeof restituisce la dimensione del puntatore, non il numero di elementi.
    
    printf("\n\n");

    return 0;
}
