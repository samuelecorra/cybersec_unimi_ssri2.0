package M09_OOP.L18_Collezioni_Lista_ArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * ============================================================
 * LEZIONE 18 – FOCUS SU ARRAYLIST
 * ============================================================
 *
 * Definizione:
 *  ArrayList<E> è un'implementazione di List basata su un array
 *  interno ridimensionabile (dinamico).
 *
 * Caratteristiche:
 *  - accesso per indice veloce: list.get(i)
 *  - dimensione variabile: add/remove cambiano la lunghezza
 *  - permette duplicati e valori null
 *  - NON è thread-safe (ma va benissimo per il 99% dei casi base)
 *
 * In questa classe:
 *  1. Creiamo e popoliamo ArrayList di Integer (wrapper → autoboxing)
 *  2. Vediamo i metodi fondamentali (add, get, set, remove, size, contains)
 *  3. Iteriamo in vari modi (for, for-each, Iterator)
 *  4. Usiamo Collections.sort()
 *  5. Creiamo una ArrayList<Studente> per oggetti custom
 */
public class MainArrayList {

    public static void main(String[] args) {

        // ============================================================
        // 1. ArrayList di Integer (wrapper, non int)
        // ============================================================
        List<Integer> numeri = new ArrayList<>();

        // add(E e) → aggiunge in coda
        numeri.add(10);   // autoboxing da int a Integer
        numeri.add(5);
        numeri.add(30);
        numeri.add(20);

        System.out.println("=== ArrayList<Integer> numeri ===");
        System.out.println("Contenuto iniziale: " + numeri);
        System.out.println("Size (dimensione): " + numeri.size());

        // get(int index) → leggere un elemento
        System.out.println("\nElemento in posizione 0: " + numeri.get(0));

        // set(int index, E e) → sostituire un elemento
        numeri.set(1, 7); // sostituisce il valore in posizione 1 (5 → 7)
        System.out.println("Dopo set(1, 7): " + numeri);

        // add(int index, E e) → inserire in posizione specifica
        numeri.add(2, 99);
        System.out.println("Dopo add(2, 99): " + numeri);

        // contains(Object o) → contiene questo elemento?
        System.out.println("\nLa lista contiene 30? " + numeri.contains(30));
        System.out.println("La lista contiene 100? " + numeri.contains(100));

        // remove(Object o) → rimuove il PRIMO o
        numeri.remove(Integer.valueOf(30));
        System.out.println("Dopo remove(Integer.valueOf(30)): " + numeri);

        // remove(int index) → rimuove per indice
        numeri.remove(0); // rimuove l’elemento all’indice 0
        System.out.println("Dopo remove(0): " + numeri);

        // isEmpty()
        System.out.println("\nLa lista è vuota? " + numeri.isEmpty());


        // ============================================================
        // 2. Iterare su una ArrayList
        // ============================================================
        System.out.println("\n=== Iterazioni sulla lista numeri ===");

        // (a) for classico con indice
        System.out.println("FOR classico:");
        for (int i = 0; i < numeri.size(); i++) {
            System.out.println("Index " + i + " → " + numeri.get(i));
        }

        // (b) for-each (enhanced for)
        System.out.println("\nFOR-EACH:");
        for (Integer n : numeri) {
            System.out.println("Valore: " + n);
        }

        // (c) Iterator esplicito (più verboso, ma utile da conoscere)
        System.out.println("\nITERATOR:");
        Iterator<Integer> it = numeri.iterator();
        while (it.hasNext()) {
            Integer n = it.next();
            System.out.println("Iterator → " + n);
            // volendo potresti fare it.remove() per rimuovere mentre iteri
        }


        // ============================================================
        // 3. Ordinare una ArrayList
        // ============================================================
        System.out.println("\n=== Ordinamento con Collections.sort ===");

        System.out.println("Prima dell'ordinamento: " + numeri);
        Collections.sort(numeri);  // ordina in ordine crescente naturale
        System.out.println("Dopo l'ordinamento: " + numeri);

        // reverse
        Collections.reverse(numeri);
        System.out.println("Dopo Collections.reverse: " + numeri);


        // ============================================================
        // 4. ArrayList di OGGETTI: ArrayList<Studente>
        // ============================================================
        System.out.println("\n=== ArrayList<Studente> ===");

        List<Studente> studenti = new ArrayList<>();

        studenti.add(new Studente("Samuele", "S12345", 1));
        studenti.add(new Studente("Erika", "E54321", 2));
        studenti.add(new Studente("Luca", "L11111", 1));

        // Stampa base: usa toString() definito in Studente
        System.out.println("Lista studenti:");
        for (Studente s : studenti) {
            System.out.println(" - " + s);
        }

        // Accesso per indice
        Studente primo = studenti.get(0);
        System.out.println("\nPrimo studente in lista: " + primo.getNome());

        // Rimozione di uno studente
        studenti.remove(1); // rimuovo Erika
        System.out.println("\nDopo remove(1):");
        for (Studente s : studenti) {
            System.out.println(" - " + s);
        }

        // Nota: per contains/remove su oggetti, se vuoi che due studenti
        // con stessa matricola siano considerati "uguali", dovresti
        // ridefinire equals() e hashCode() in Studente.
        // Per questa lezione introduttiva non è strettamente necessario.

        System.out.println("\n=== FINE LEZIONE SU ARRAYLIST ===");
    }
}
