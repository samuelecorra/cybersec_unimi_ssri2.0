package M08_Arrays.L02_MetodiArray;

import java.util.Arrays; // per usufruire della classe Arrays e dei suoi metodi statici di utilità sugli array,
// come sort(), binarySearch(), equals(), fill(), copyOf(), toString(), e altro ancora.

public class MetodiArray {

    // Oltre al già citato length...

    // ===================================================================

    // UTILITÀ SUGLI ARRAY: LA CLASSE JAVA.UTIL.ARRAYS

    // Java fornisce una classe utility chiamata Arrays nel package java.util
    // che offre vari metodi statici per operare sugli array, come l'ordinamento,
    // la ricerca, la copia, il confronto, e altro ancora.

    // Proviamo ad ordinare i nostri frutti in ordine alfabetico, o meglio, lessicografico:
    static void main() {

        String[] frutta = {"mela", "banana", "arancia", "kiwi", "pera"};
        System.out.println("Frutti originali:");
        for (String frutto : frutta) {
            System.out.println(frutto); // mela è in prima posizione
        }

        // Metodo sort:
        // Ordinamento dell'array in ordine alfabetico -- o meglio, lessicografico
        Arrays.sort(frutta);

        System.out.println("Frutti ordinati in ordine lessicografico:");
        for (String frutto : frutta) {
            System.out.println(frutto); // mela non è più in prima posizione
        }

        // ===================================================================

        // Metodo fill:
        // Riempie l'array con un valore specifico in ogni posizione
        Arrays.fill(frutta, "ananas");
        System.out.println("Array riempito con 'ananas':");
        for (String frutto : frutta) {
            System.out.println(frutto); // ogni frutto è "ananas"
        }

        // Per ora ci fermiamo qui, ma la classe Arrays offre molti altri metodi utili
        // per lavorare con gli array in Java.
    }


}
