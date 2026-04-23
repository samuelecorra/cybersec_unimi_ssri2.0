#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int** trasformaMatrice(int N, int** matriceOriginale) {
    
// Per ogni elemento i-j-esimo della matrice che passiamo,
// dobbiamo controllare gli otto elementi adiacenti (orizzontali, verticali e diagonali), stabilire
// qual è il massimo tra di essi, e assegnarlo alla matrice trasformata in posizione i-j-esima.

// Ma come fare a controllare gli otto elementi adiacenti?

    // Iniziamo a definire la matrice trasformata, che sarà una copia della matrice originale.
    int** matriceTrasformata = malloc(N * sizeof(int*));
    for (int i = 0; i < N; i++) {
        matriceTrasformata[i] = malloc(N * sizeof(int));
    }

    // E' lo stesso identico processo di allocazione dinamica di prima.

    // Ora che abbiamo la matrice trasformata pronta e allocata, possiamo procedere.
    // Qual è l'idea dietro questa trasformazione?


///////////////////////////////////////////////////////////////////////////////////////////////////////////////


// Abbiamo innanzitutto da comprendere chi sono i "vicini" di un elemento i-j-esimo della matrice.

// 🧠 IDEAZIONE: Chi sono i "vicini"?
// Abbiamo 8 possibilità intorno a un punto:

//   (i-1,j-1)   (i-1,j)   (i-1,j+1)
//   (i,  j-1)   (i,  j)   (i,  j+1)
//   (i+1,j-1)   (i+1,j)   (i+1,j+1)

// Il centro, come da richiesta del prof, non lo consideriamo.

// Quindi i vicini sono:
// Sopra: (i-1, j) // Sotto: (i+1, j) // Sinistra: (i, j-1) // Destra: (i, j+1)
// Diagonale alto-sinistra: (i-1, j-1) // Diagonale alto-destra: (i-1, j+1)
// Diagonale basso-sinistra: (i+1, j-1) // Diagonale basso-destra: (i+1, j+1)

// ⚠️ Attenzione ai bordi! Se sei sul bordo (es: prima riga), non puoi accedere a (i-1, j), perché andresti
// fuori dalla matrice.

// Perfetto... o quasi. Che opzioni abbiamo al momento?
// 1. L'idea più elegante è quella di usare un classico ciclo for annidato che scandisca elemento per elemento.
// 2. Ad ogni singolo elemento scansionato, andiamo ad accedere manualmente agli 8 elementi adiacenti e ne
//    calcoliamo il massimo, che andremo a inserire nella matrice trasformata.

// Questa soluzione è semplice e diretta, ma potrebbe essere un po' verbosa. In gergo, è la soluzione
// boilerplate, quella che ti fa scrivere tanto codice per fare una cosa semplice.
// Tuttavia è anche la più brute-force, e quindi la più facile da implementare senza errori.

// Ma dobbiamo abituarci già da ora a scrivere codice efficiente, non possiamo scegliere la strada più semplice
// e veloce, ma quella più elegante e manutenibile. Quindi, procediamo con un trucco.

// Useremo due variabili di offset, ovvero una variazione rispetto a una posizione base.

// Nel nostro caso, quando scandiamo una matrice con gli indici i (per le righe) e j (per le colonne), 
// un offset è uno spostamento rispetto a i o j.

// 🎯 Esempio: variabile di
// Supponiamo di essere in una cella della matrice con coordinate (i, j).

// Ora vogliamo "guardare" sopra: ci spostiamo di -1 nella riga ⇒ i - 1.

// Vogliamo guardare sotto: ci spostiamo di +1 nella riga ⇒ i + 1.

// Ora questo delta, questa variazione, possiamo incapsularla in una variabile, ottenendo:

// int di = -1;  // significa: sali di una riga
// int di = 0;   // stai sulla stessa riga
// int di = +1;  // scendi di una riga

// Lo stesso vale per la colonna:

// int dj = -1;  // vai a sinistra
// int dj = 0;   // resta nella stessa colonna
// int dj = +1;  // vai a destra

// ⚙️ Uso tipico
// Appurato questo, le variabili di e dj vengono usate nei cicli per visitare tutte le celle adiacenti, 
// in modo molto elegante:


    // Iteriamo su ogni elemento della matrice originale.
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {

        // Fin qui sappiamo già cosa stiam facendo. Abbiamo accesso al primo elemento di indici 0 e 0.
        // Dopo che avremo eseguito questo corpo per tale elemento, passeremo all'elemento di indici 1 e 0,
        // poi 2 e 0, 3 e 0...

        // ORA VIENE IL BELLO.
        // Ci serve una variabile extra alla quale appoggiarci, nella quale porremo il valore massimo
        // che scoveremo tra i vari posti adiacenti della matrice.

            int maxTrovatoTraGliAdiac = -99999; // Inizializziamo il massimo a un valore molto basso.
            // Questa inizializzazione a un numero così basso ci serve perché così facendo, qualsiasi valore
            // legittimo che troveremo negli elementi adiacenti sarà più grande di questo, quindi potremo
            // aggiornarlo correttamente. GARANTISCE CHE ALMENO UNO DEGLI 8 VICINI SARA' PRESO COME MASSIMO
            
            // Infine, controlliamo gli otto elementi adiacenti.
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if (di == 0 && dj == 0) continue; // Saltiamo l'elemento corrente.

                    // Ricordiamo che la keyword continue fa direttamente passare alla successiva iterazione.

                    // Adesso calcoliamo gli effettivi offset sommando indici originali e rispettivi delta
                    int new_i = i + di;
                    int new_j = j + dj;

                    // MA ATTENZIONE AI BORDI DELLA MATRICE, DOVE NON SI HANNO 8 ADIACENTI!
                    // Verifichiamo che l'indice sia valido.

                    // "se la nuova riga è compresa tra 0 e la dimensione della matrice-1, aka minore di N,
                    // E AL CONTEMPO && vale lo stesso discorso per la nuova colonna, allora siamo in un
                    // adiacente valido. Si apre il ramo true dell'if:"
                    if ((new_i >= 0 && new_i < N) && (new_j >= 0 && new_j < N)) {

                        // Annidiamo un altro if: se l'elemento adiacente nella matrice originale è
                        // maggiore del nostro "valore placeholder, ed effettivamente lo sarà sempre perché lo
                        // garantiamo, allora bisogna sovrascrivere tale valore con quello appena scovato"
                        if (matriceOriginale[new_i][new_j] > maxTrovatoTraGliAdiac) {
                            maxTrovatoTraGliAdiac = matriceOriginale[new_i][new_j];
                        }
                    }
                
                // Ed ora ripetiamo il ragionamento appena fatto per gli altri offset...

                }
            }
            // Qui abbiamo concluso tutti gli adiacenti, e vorrà dire che maxTrovatoTraGliAdiac è davvero
            // il massimo
            // Assegniamo il massimo trovato alla matrice trasformata.
            matriceTrasformata[i][j] = maxTrovatoTraGliAdiac;
        }

        // Ora reiteriamo il tutto passando all'elemento successivo
    }

    // Ora abbiamo reiterato per tutti gli elementi!
    return matriceTrasformata;
}

// Come preannunciato, ci serve anche una funzione di stampa:

void stampaMatrice(int** matrice, int N) {

// Come si stampava una matrice in C lo ricordiamo? Sempre con un ciclo for:

for (int i = 0; i < N; i++) {

    for (int j = 0; j < N; j++) {

        printf("%d ", matrice[i][j]);
    }
    printf("\n");
}

}


// Ora finalmente passiamo a scrivere il main:

int main () {

int N = 4;

// Allochiamo spazio dinamicamente...
int** matrix1 = malloc(N * sizeof(int*));

// E subito dopo allochiamo lo spazio per poterle effettivamente riempire
for (int i = 0; i < N; i++) {
        matrix1[i] = malloc(N * sizeof(int));
    }

// Ora siamo pronti con la nostra matrice creata dinamicamente.
// Mettiamole dentro dei valori.

// Inserimento dati
int valori[4][4] = {
        {4, 5, 6, 7},
        {8, 2, 4, 5},
        {6, 7, 8, 1},
        {2, 8, 4, 6}
    };
    
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
            matrix1[i][j] = valori[i][j];
        }


printf("Premere invio per stampare la matrice originale...\n");
getchar();

stampaMatrice(matrix1, 4);

int** matriceMax = trasformaMatrice(4, matrix1);

printf("\nPremere invio per stampare la matrice trasformata come da consegna...\n");
getchar();

stampaMatrice(matriceMax, 4);

printf("\nMatrice stampata con successo. Esercizio terminato.\n"
"Premere invio per uscire dal programma...");
getchar();

return 0;;

}