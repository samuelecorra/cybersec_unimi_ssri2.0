
/* Gli array bidimensionali, che altro non sono che matrici/tabelle
così come le abbiamo conosciute in matematica/algebra lineare, sono
definiti da due dimensioni che facilitano l'astrazione: righe e colonne. */

// Più formalmente, un array bidimensionale è un array di array, ovvero
// ogni elemento dell'array principale è a sua volta un array.

// L'array più esterno rappresenta le righe, mentre l'array più interno
// rappresenta le colonne. FERMI - IN CHE SENSO? 

// In un array bidimensionale, per accedere a un elemento specifico, è necessario
// fornire due indici: il primo per la riga e il secondo per la colonna.
// Ad esempio, per accedere all'elemento nella seconda riga e terza colonna
// si utilizza la sintassi: array[1][2].


// ====================================================================================


// OK, ma come si dichiara un array bidimensionale in C?

#include <stdio.h>

int main() {

    // Se per un array monodimensionale usavamo una coppia di quadre,
    // per un array bidimensionale ne usiamo due coppie:

    // int numeri[][]; // ERRORE!

    // Ma attenzione: in C, quando si dichiara un array bidimensionale,
    // è obbligatorio specificare la dimensione della seconda (o più
    // in generale dell'ultima) dimensione. Questo perché il compilatore
    // deve sapere quanti elementi ci sono in ogni riga per calcolare
    // correttamente gli indirizzi di memoria.

    int numeri[3][4]; // 3 righe e 4 colonne

    // In questo esempio, abbiamo dichiarato un array bidimensionale
    // chiamato "numeri" con 3 righe e 4 colonne. Questo significa che
    // l'array può contenere un totale di 3 * 4 = 12 elementi.

    // Possiamo anche inizializzare un array bidimensionale al momento
    // della dichiarazione:

    int matrice[2][3] = {
        {1, 2, 3},   // Prima riga
        {4, 5, 6}    // Seconda riga
    };

    // Oppure in modo più compatto:

    int matriceCompatta[2][3] = {1, 2, 3, 4, 5, 6};

    // In entrambi i casi, abbiamo creato una matrice con 2 righe e
    // 3 colonne.


//=====================================================================================


    // SOTTIGLIEZZA IMPORTANTE: QUANDO SI DICHIARA E INIZIALIZZA NELLA MEDESIMA RIGA
    // DI CODICE, ALLORA E SOLO ALLORA SI POSSONO OMETTERE LE DIMENSIONI DELLA PRIMA
    // (O PIÙ IN GENERALE DELLE PRIME N-1) DIMENSIONI. IL COMPILATORE E' IN GRADO, IN 
    // QUESTO CASO, DI CALCOLARLE AUTOMATICAMENTE.

    int numeriDa1a9[][3] = { {1, 2, 3}, 
                             {4, 5, 6}, 
                             {7, 8, 9} }; // OK

    // In questo caso, il compilatore calcola automaticamente che
    // la prima dimensione deve essere 3, poiché 9 elementi su 3 colonne devono per
    // forza essere distribuiti su 3 righe!


//=====================================================================================


    // Per accedere agli elementi di un array bidimensionale,
    // utilizziamo due indici:

    int elemento = matrice[1][2]; // Accesso all'elemento nella seconda riga, terza colonna (valore 6)

    printf("Elemento nella seconda riga, terza colonna: %d\n", elemento);

    // NON SI DIMENTICHI: GLI INDICI IN C PARTONO DA 0!


//=====================================================================================
    

    // Supponiamo di voler stampare la prima riga della matrice di poco fa:
    printf("Prima riga della matrice: ");
    for(int j = 0; j < 3; j++) { // 3 colonne
        printf("%d ", numeriDa1a9[0][j]); // Prima riga, tutte le colonne
    }

    printf("\n");

    // Oppure, per stampare la prima colonna:
    printf("Prima colonna della matrice: ");
    for(int i = 0; i < 3; i++) { // 2
        printf("%d ", numeriDa1a9[i][0]); // Tutte le righe, prima colonna
    }

    printf("\n");

    // Oppure, per stampare l'intera matrice:
    printf("Intera matrice:\n");
    for(int i = 0; i < 3; i++) { // Per ogni riga
        for(int j = 0; j < 3; j++) { // Per ogni colonna
            printf("%d ", numeriDa1a9[i][j]);
        }
        printf("\n"); // Nuova riga dopo ogni riga della matrice
    }


//=====================================================================================


// ESERCIZIO: Stampare in console il numpad di un telefonino:

    char numpad[4][3] = { {'1', '2', '3'},
                          {'4', '5', '6'},
                          {'7', '8', '9'},
                          {'*', '0', '#'} };

    printf("Numpad di un telefonino:\n");
    
    for(int i = 0; i < 4; i++) { // Per ogni riga
        for(int j = 0; j < 3; j++) { // Per ogni colonna
            printf("%c ", numpad[i][j]);
        }
        printf("\n"); // Nuova riga dopo ogni riga della matrice
    }

return 0;
}