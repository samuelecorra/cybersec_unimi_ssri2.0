// FASE DI PROGETTAZIONE DEL CODICE:

// 1. Innanzitutto dobbiamo definire una funzione che cloni la matrice originale, perché non possiamo
//    lavorare direttamente su di essa, ma dobbiamo creare una copia per poterla trasformare.

// 2. Poi dobbiamo definire una funzione che prende in ingresso la matrice clonata e la trasforma,
//    calcolando la media degli elementi adiacenti per ogni elemento della matrice originale.

// 3. Infine, dobbiamo definire una funzione di stampa per le matrici, in modo da poter visualizzare
//    sia la matrice originale che quella trasformata.

// 4. Infine, dobbiamo scrivere il main che gestisce l'allocazione STATICA della matrice originale,
//    l'inserimento dei dati, la clonazione della matrice, la trasformazione e la stampa delle matrici.


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Iniziamo a definire la funzione di clonazione della matrice originale.

// Partiamo da ciò che sappiamo che deve prendere in ingresso:

// 1. La matrice originale, che è stata dichiarata e inizializzata come statica nel main.
// La passiamo con la prima quadra vuota e la seconda quadra con N, che è buona prassi per poter
// passare matrici di dimensioni diverse in futuro, se necessario.

// 2. La dimensione N della matrice quadrata, che è un intero, NON VA PASSATA!
// Attenzione però, bisogna per forza di cose usare una #define per settarlo come costante,
// altrimenti gli standard C non ci permettono di passare matrici di dimensioni variabili
// come argomento di una funzione. Quindi, per ora, lo dichiariamo come #define N 3, così che in futuro
// quando vorremo riproporre il medesimo esercizio con una matrice di dimensioni diverse,
// basterà cambiare il valore di N e il codice funzionerà comunque. Si modifica letteralmente una sola riga!


// Change this macro value to test with different matrix sizes:
#define N 3

// Riassumendo: se nell'esercizio scorso es_matriceMax.c abbiamo usato malloc per allocare
// la matrice dinamicamente, qui invece useremo una matrice statica, che è più semplice e diretta,
// ma ATTENZIONE, bisogna per forza usare l'espediente della #define per definire N,
// altrimenti il compilatore non accetterà di passare matrici di dimensioni variabili come argomento!

// Facciamo tutte le matrici come matrici di double direttamente, così non avremo problemi di precisione quando
// andremo a fare le medie degli adiacenti, che sono quasi sempre numeri con la virgola...

void clonaMatrice(double matriceOriginale[N][N], double matriceClonata[N][N]) {
    
    // Cloniamo con doppio ciclo for annidato. Aver passato N come macro ci permette di andare a risparmiare
    // molto codice nella funzione di clonaggio, infatti non allochiamo nulla dinamicamente ma procediamo
    // immnediatamente con la copia delle varie caselle della matrice originale.

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            matriceClonata[i][j] = matriceOriginale[i][j];
        }
    }

}

// Ora abbiamo la matrice clonata, ma dobbiamo anche definire la funzione di trasformazione della matrice,
// che è la parte davvero CORPOSA dell'esercizio...

// Vogliamo che lavori sulla clonata, non sull'originale quindi le passiamo come argomenti
// l'originale sempre con dimensioni parametrizzate, e la trasformata sempre con dimensioni parametrizzate.
// La clonata ovviamente nel main sarà statica E NON INIZIALIZZATA, perché la inizializzeremo
// proprio dentro 


// UNA PICCOLA ACCORTEZZA: LA TRASFORMATA SARA' UN ARRAY DI ARRAY DI DOUBLE, PERCHE' PALESEMENTE
// NON AVREMO QUASI MAI UNA MEDIA CHE RESTITUISCE UN INTERO, MA AL 99.9% CI DARA' UN NUMERO CON VIRGOLA,
// QUINDI E' UN PASSO FONDAMENTALE DICHIARARE UN BUON TIPO STATICO PER LA MATRICE TRASFORMATA!
// => EVITIAMO PERDITE DI PRECISIONE:
void trasformaMatriceConMediaAdiacenti(double matriceClonata[N][N], double matriceTrasformata[N][N]) {

    // Dobbiamo scorrere ogni elemento della matrice clonata.
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {

            // Prima di calcolare la media degli elementi adiacenti, dobbiamo munirci di due variabili di
            // supporto: iniziamo con un valore placeholder
            // per la somma e un contatore per gli elementi validi:
            double sommaElementiValidi = 0;
            int counterElementiValidi = 0;

            // Controlliamo le posizioni adiacenti. (8 direzioni generiche intorno a un elemento, dalle quali
            // poi escluderemo l'elemento stesso e le posizioni non valide fuori dalla matrice)

            // Si ricordi che la tattica vincente è usare un doppio ciclo for annidato con cui scorriamo
            // i vari delta-offset, ovvero le variazioni rispetto alla posizione base dello scorrimento dei
            // due cicli for di poc'anzi!
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if (di == 0 && dj == 0) continue; // Saltiamo l'elemento centrale (continue è la keyword
                    // che blocca direttamente l'iterazione corrente del ciclo for e salta alla successiva).

                    int ni = i + di;    // Calcoliamo l'offset effettivo per la riga dell'elemento adiacente
                    int nj = j + dj;    // Calcoliamo l'offset effettivo per la colonna dell'elemento adiacente

                    // Verifichiamo i limiti della matrice
                    if (ni >= 0 && ni < N && nj >= 0 && nj < N) {   // se gli indici coi delta sono dentro ai limiti della matrice
                        sommaElementiValidi += matriceClonata[ni][nj];  // Tale adiacente è valido,
                        // e lo dobbiamo aggiungere alla somma degli elementi validi che circondano l'elemento i-j-esimo.

                        // Ricordiamo che sommaElementiValidi += matriceClonata[ni][nj] è una forma compatta del C
                        // che sottintende la sintassi più estesa:
                        // sommaElementiValidi = sommaElementiValidi + matriceClonata[ni][nj];
                        // Che nel primo caso fa 0 + matriceClonata[ni][nj].
                        counterElementiValidi++;    // Incrementiamo il contatore degli elementi validi che circondano
                        // il nostro i-j-esimo elemento. Una volta che avremo finito di iterare avremo il numero effettivo
                        // di elementi validi che circondano l'elemento i-j-esimo della matrice clonata, o per meglio
                        // dire, il denominatore della media che calcoleremo alla fine!
                    }
                }
            }

            // Calcoliamo la media e la assegniamo alla matrice trasformata
            
            if (counterElementiValidi > 0) {    // Questa if è sempre vera per qualsiasi elemento della qualsiasi
                // matrice, perché ogni eleemento ha almeno un adiacente, ma è buona prassi
                // verificare che il contatore degli elementi validi sia maggiore di 0 per poter constatare
                // che non è rimasto al valore placeholder iniziale, oltre al fatto che non potremmo dividere
                // per 0, quindi sarebbe un errore di runtime.

                // E finalmente facciamo la media aritmetica e la assegnamo all'elemento i-j-esimo della matrice
                // trasformata:
                matriceTrasformata[i][j] = sommaElementiValidi / counterElementiValidi;
            }
        }
    }
}

// Ora abbiamo la funzione di trasformazione della matrice che calcola la media degli elementi adiacenti.

// Manca da implementare la funzione di stampa della matrice, che è abbastanza semplice:

void stampaMatriceOriginale(double matriceOriginale[N][N]) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%5.1f ", matriceOriginale[i][j]);  // il 5 prima del .1f serve per una formattazione forzata
            // che ci consente una tabulazione fissa, molto meglio del classico \\t che non funziona bene con i numeri.
        }
        printf("\n");
    }
}

// Allo stesso identico modo definiamo la funzione di stampa della matrice trasformata:

void stampaMatriceTrasformata(double matriceTrasformata[N][N]) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%5.1f ", matriceTrasformata[i][j]);  // il 5 prima del .1f serve per una formattazione forzata
            // che ci consente una tabulazione fissa, e il .1f serve per stampare i numeri con un decimale.
        }
        printf("\n");
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Ora passiamo a scrivere il main:

int main() {

printf("Benvenuto nel programma di trasformazione della matrice con media degli adiacenti!\n");
printf("Prego, inserire il numero di righe e colonne modificando la #define N.\n");
printf("Per ora, N è impostato a %d.\n", N);

// Dichiariamo la matrice originale e la matrice trasformata, che sono entrambe statiche.
double matriceOriginale[N][N];
double matriceClonata[N][N];
double matriceTrasformata[N][N];

// Vogliamo che la matrice originale contenga i valori:

// { {4, 19, 2008}, {10, 11, 2003}, {5, 6, 2007} };


// Ma in C dopo la dichiarazione di una matrice non si può assegnare tutto in una mega graffa, non siamo
// liberi come in C++.
// Quindi dobbiamo inserire i valori uno per uno, o usare un ciclo for per farlo in modo più elegante.

// Tramite for:
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        printf("Inserisci il valore per la cella [%d][%d]: ", i, j);
        scanf("%lf", &matriceOriginale[i][j]);
        getchar(); // Per catturare l'invio dopo lo scanf, così non ci sono problemi con i buffer.
    }
}

printf("Matrice originale inserita:\n");
stampaMatriceOriginale(matriceOriginale);

printf("Premere invio per clonare la matrice...\n");
getchar();

printf("Clonazione della matrice...\n");
clonaMatrice(matriceOriginale, matriceClonata);
printf("Matrice clonata:\n");
stampaMatriceOriginale(matriceClonata);

printf("Premere invio per trasformare la matrice...\n");
getchar();
printf("Trasformazione della matrice...\n");
trasformaMatriceConMediaAdiacenti(matriceClonata, matriceTrasformata);

printf("Matrice trasformata con media degli adiacenti:\n");
stampaMatriceTrasformata(matriceTrasformata);
printf("Trasformazione completata!\n");
printf("Grazie per aver utilizzato il programma di trasformazione della matrice!\n");

printf("Premere invio per terminare il programma...\n");
getchar();
return 0;  // Ritorniamo 0 per indicare che il programma è terminato correttamente.
}
