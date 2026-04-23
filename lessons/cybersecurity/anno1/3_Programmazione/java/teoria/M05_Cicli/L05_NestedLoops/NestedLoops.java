package M05_Cicli.L05_NestedLoops;

import java.util.Scanner;

public class NestedLoops {

    // I cicli annidati in JAVA sono cicli all'interno di altri cicli.

    // Quando si usano?

    // 1. MATRICI, ovvero le strutture dati bidimensionali (array di array).
    //    Per esempio, per scorrere una matrice 2D, si usa un ciclo esterno per le righe
    //    e un ciclo interno per le colonne.

    // 2. Più in generale, è un concetto che si affronta spessissimo nell'ambito
    // di Algoritmi e Strutture Dati, per esempio per algoritmi di ordinamento
    // o per la manipolazione di strutture dati complesse.

    // Stampiamo una matrice che ha 3 righe del tipo: 1 2 3 4 5 6 7 8 9
    static void main() {

        for (int riga = 1; riga <= 3; riga++) { // ciclo esterno per le righe
            for (int colonna = 1; colonna <= 9; colonna++) { // ciclo interno per le colonne
                System.out.print(colonna + " "); // stampa i numeri da 1 a 9
            }
            System.out.println(); // va a capo dopo ogni riga
        }

        // Vediamo come generare una matrice di tot righe, tot colonne e fillata con
        // un simbolo specifico, per esempio '*'
        int rows;
        int cols;
        char symbol;

        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci il numero di righe: ");
        rows = sc.nextInt();
        System.out.print("Inserisci il numero di colonne: ");
        cols = sc.nextInt();
        System.out.print("Inserisci il simbolo di riempimento: ");
        symbol = sc.next().charAt(0);
        sc.close();

        for (int i = 1; i <= rows; i++) { // ciclo esterno per le righe
            for (int j = 1; j <= cols; j++) { // ciclo interno per le colonne
                System.out.print(symbol + " "); // stampa il simbolo di riempimento
            }
            System.out.println(); // va a capo dopo ogni riga
        }

        // Per non allungare troppo il brodo, ci fermiamo qui.
        // Ovviamente questo è un argomento molto vasto e lo riprenderemo
        // più avanti nel corso, quando parleremo di array multidimensionali
        // e di algoritmi più complessi.

    }
}
