/*
In C è possibile annidare i cicli (nested loops).
Questo ci torna utile per iterare su strutture dati multidimensionali, come le matrici.
Ma può essere comodo anche per altre situazioni, come ad esempio quando si vogliono generare combinazioni di elementi.

Esempio di ciclo annidato:
*/

#include <stdio.h>

int main() {
    for (int i = 0; i < 3; i++) {   // scorriamo i da 0 a 2
        for (int j = 0; j < 2; j++) {   // scorriamo j da 0 a 1
            printf("| i: %d , j: %d |", i, j); // stampiamo i e j
        }
        printf("\n------------------------------\n"); // nuova linea dopo ogni iterazione di i
    }

    // Cosa ci aspettiamo di vedere:
    // 3 righe indicizzate da i (0, 1, 2)
    // 2 colonne indicizzate da j (0, 1)
    // con rispettive "coordinates" (i, j)


//======================================================================================
    
    // Un'altro esempio valido: stampiamo tre volte con un nested loop un countdown

    for (int count = 0; count < 3; count++) { // Tre iterazioni per il countdown
        printf("Countdown: %d - ", count+1); // Stampa il numero del countdown (count+1 per partire da 1)

        for (int repeat = 10; repeat > 0; repeat--) { // ripeti 10 volte
            printf("%d ", repeat); // stampa il valore corrente del countdown
        }
        printf("\n"); // nuova linea dopo ogni ultimo numero del countdown
    }


//======================================================================================


    // Un ultimo esempio: con un nested loop, possiamo stampare una tabella di moltiplicazione,
    // ovvero una tabella bidimensionale in cui l'elemento i-j-esimo è il prodotto di i per j.

    printf("\nTabella di moltiplicazione (1-10):\n");
    for (int i = 1; i <= 10; i++) { // rig
        for (int j = 1; j <= 10; j++) { // colonne
            printf("%5d", i * j); // stampa il prodotto di i e j con un campo di larghezza 4 per l'allineamento
        }
        printf("\n"); // nuova linea dopo ogni riga della tabella
    }

    // Abbiamo implicitamente fatto uso di quella che si chiama tabulazione fissa,
    // ovvero colei che si oppone a \t (tabulazione orizzontale).
    // La tabulazione fissa consiste nel riservare un certo numero di caratteri
    // per ogni elemento stampato, in modo da allineare correttamente le colonne.
    // Quella standard con l'escape sequence \t non sempre funziona bene,
    // specialmente quando i numeri hanno lunghezze diverse (ad esempio 1 e 10).
    
    printf("Premi un tasto per continuare...\n\n");
    getchar();
    printf("//=================================================================\n\n");


//======================================================================================


    // Ultimissimo esempio: si possono stampare tabelle riempite con il medesimo simbolo:

    int rows = 0;
    int cols = 0;
    char symbol = '\0';

    printf("Inserisci il numero di righe: ");
    scanf("%d", &rows);
    getchar(); // consuma il newline rimasto nel buffer dopo scanf

    printf("Inserisci il numero di colonne: ");
    scanf("%d", &cols);
    getchar(); // consuma il newline rimasto nel buffer dopo scanf

    printf("Inserisci il simbolo da usare: ");
    scanf("%c", &symbol);
    getchar(); // consuma il newline rimasto nel buffer dopo scanf

    printf("\nTabella %dx%d riempita con '%c':\n", rows, cols, symbol);
    for (int i = 0; i < rows; i++) { // righe
        for (int j = 0; j < cols; j++) { // colonne
            printf("%c ", symbol); // stampa il simbolo con uno spazio per separarlo
        }
        printf("\n"); // nuova linea dopo ogni riga della tabella
    }

    printf("Premi un tasto per uscire...\n");
    getchar();
    
    return 0;
}