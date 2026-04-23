package E1_esercizi_introduttivi.esercizio_4;

import java.util.Scanner;

public class ArrayRicerca {

    // Ricerca lineare su array di interi: ritorna l'indice o -1 se non trovato
    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) return i;
        }
        return -1;
    }

    // Ricerca lineare su array di stringhe: ritorna l'indice o -1 se non trovato
    static int linearSearch(String[] a, String target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].equals(target)) return i;            // variante: equalsIgnoreCase(...)
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] numeri = {1, 3, 4, 5, 6, 2, 7, 8, 9};
        String[] parole = {"cane", "gatto", "pesce", "uccello"};

        try (Scanner scanner = new Scanner(System.in)) {

            // --- Ricerca su array di interi ---
            System.out.print("Inserisci il valore intero da cercare nell'array numerico: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Valore non valido. Inserisci un intero: ");
                scanner.next(); // scarta token non numerico
            }
            int bersaglio = scanner.nextInt();
            scanner.nextLine(); // pulizia newline

            int posNum = linearSearch(numeri, bersaglio);
            if (posNum >= 0) {
                System.out.println("Il valore bersaglio e' all'indice " + posNum);
            } else {
                System.out.println("L'elemento non e' stato trovato nell'array di interi.");
            }

            // --- Ricerca su array di stringhe ---
            System.out.print("Inserisci la parola da cercare nell'array di stringhe: ");
            String bersaglioStringa = scanner.nextLine().trim();

            int posStr = linearSearch(parole, bersaglioStringa);
            if (posStr >= 0) {
                System.out.println("La parola bersaglio e' all'indice " + posStr);
            } else {
                System.out.println("L'elemento non e' stato trovato nell'array di stringhe.");
            }
        }
    }
}
