package M08_Arrays.L05_Array2d;

import java.util.Arrays;

public class Matrici2d {

    // Un array 2d in Java è fondamentalmente un array di array.
    // Ogni elemento dell'array principale è a sua volta un array.

        // Se tutte le righe hanno la stessa lunghezza, un array 2D può essere
        // visto come una matrice. Java consente anche array "frastagliati",
        // nei quali le righe hanno lunghezze differenti.
    // Questo dettaglino è alla base dell'intelligenza artificiale,
    // specialmente nelle reti neurali, dove le matrici sono usate
    // per rappresentare i pesi delle connessioni tra i neuroni.

    public static void main(String[] args) {

        // Partiamo col dichiarare tre array e poi un array 2d che li contiene.
        String[] frutta = {"Mela", "Banana", "Arancia"};
        String[] verdura = {"Carota", "Lattuga", "Spinaci"};
        String[] cereali = {"Riso", "Grano", "Mais"};

        String[][] alimenti = {frutta, verdura, cereali};

        // Ora vediamo come stampare gli elementi di questo array 2d.

        // 1. Esempio sbagliato che può trarre in inganno:
        System.out.println("Esempio sbagliato:");
        for (String[] alimento : alimenti) {
            // Usa Object.toString(): tipo dell'array + hash identificativo.
            // Non stampa né gli elementi né un indirizzo di memoria utilizzabile.
            System.out.println(alimento);
        }

        System.out.println("Stampa delle righe con Arrays.toString():");
        for (String[] riga : alimenti) {
            System.out.println(Arrays.toString(riga));
        }

        // 2. Esempio corretto serve un nested loop:
        System.out.println("\nEsempio corretto:");
        for (String[] alimento : alimenti) {
            for (String item : alimento) {
                System.out.print(item + " ");
            }
            System.out.println(); // Va a capo dopo ogni categoria
        }

        // ==========================================================

        // Ovviamente possiamo dichiarare e inizializzare un array 2d
        // direttamente, senza passare per array singoli.
        int[][] matrice = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Stampiamo la matrice
        System.out.println("\nMatrice 2D:");
        for (int[] ints : matrice) { // ciclo per le righe
            for (int anInt : ints) { // ciclo per le colonne
                System.out.print(anInt + " ");
            }
            System.out.println(); // Va a capo dopo ogni riga
        }

        // ==========================================================

        // Per aggiornare i singoli elementi si accede sia a riga che colonna:
        String [][] libri = {
            {"Il Signore degli Anelli", "Lo Hobbit"},
            {"1984", "Fahrenheit 451"},
            {"Il Nome della Rosa", "Il Pendolo di Foucault"}
        };

        // Aggiorniamo "1984" in "Brave New World"
        libri[1][0] = "Brave New World";
        System.out.println("\nLibri aggiornati:");
        for (String[] sezione : libri) {
            for (String libro : sezione) {
                System.out.print(libro + " ");
            }
        }

        // ==========================================================

        // Le possibilità con le matrici sono svariate: iniziamo da un'idea carina:
        // il tastierino numerico di un telefono!
        char[][] tastierino = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'},
            {'*', '0', '#'}
        };

        System.out.println("\nTastierino numerico:");
        for (char[] riga : tastierino) {
            for (char tasto : riga) {
                System.out.print(tasto + " ");
            }
            System.out.println();
        }


    }
}
