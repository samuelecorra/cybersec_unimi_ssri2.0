package M09_OOP.L18_Collezioni_Lista_ArrayList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * ============================================================
 * LEZIONE 18 – INTRODUZIONE AL JAVA COLLECTION FRAMEWORK
 *                     FOCUS: LIST e ARRAYLIST
 * ============================================================
 *
 * OBIETTIVO:
 *  - Capire COSA sono le Collezioni in Java.
 *  - Capire il ruolo dell'interfaccia List.
 *  - Distinguere i principali tipi di Liste:
 *      * ArrayList
 *      * LinkedList
 *      * (storicamente anche Vector / Stack)
 *  - Vedere perché ArrayList è la lista "di default" nel 90% dei casi.
 *
 * ------------------------------------------------------------
 * 1) JAVA COLLECTION FRAMEWORK (JCF)
 * ------------------------------------------------------------
 *  È un insieme di interfacce e classi che forniscono strutture dati
 *  pronte all'uso (liste, insiemi, mappe, code, ecc.).
 *
 *  Le tre “macro-famiglie” principali:
 *
 *    - List  → collezioni ORDINATE, con elementi indicizzati e duplicati.
 *    - Set   → insiemi SENZA duplicati (modello "insieme matematico").
 *    - Map   → associazioni chiave → valore (tipo dizionario).
 *
 *  Noi in questa lezione ci concentriamo su List.
 *
 * ------------------------------------------------------------
 * 2) INTERFACCIA List<E>
 * ------------------------------------------------------------
 *  - Rappresenta una sequenza ORDINATA di elementi.
 *  - Ogni elemento ha un indice (0, 1, 2, ...).
 *  - Sono ammessi elementi DUPLICATI.
 *  - Tipo generico: List<E> (E = tipo degli elementi).
 *
 *  Metodi fondamentali:
 *    - add(E e)             → aggiunge in coda
 *    - add(int index, E e)  → inserisce in posizione specifica
 *    - get(int index)       → legge l'elemento all'indice
 *    - set(int index, E e)  → sostituisce l'elemento all'indice
 *    - remove(int index)    → rimuove per indice
 *    - remove(Object o)     → rimuove il PRIMO uguale a o
 *    - size()               → numero di elementi
 *    - isEmpty()            → lista vuota?
 *    - contains(Object o)   → la lista contiene quell’elemento?
 *
 * ------------------------------------------------------------
 * 3) IMPLEMENTAZIONI PRINCIPALI DI List
 * ------------------------------------------------------------
 *  - ArrayList<E>
 *      * internamente usa un array RIDIMENSIONABILE.
 *      * accesso per indice molto veloce: O(1) medio.
 *      * inserimenti/rimozioni in mezzo alla lista costosi: O(n).
 *      * è la scelta standard nella maggior parte dei casi.
 *
 *  - LinkedList<E>
 *      * internamente usa una lista doppiamente concatenata.
 *      * inserimenti/rimozioni al centro o ai bordi più efficienti
 *        (se hai già il riferimento al nodo).
 *      * accesso per indice più lento: O(n).
 *      * utile per code, deque, e scenari con molte insert/remove.
 *
 *  - Vector<E> (e Stack)
 *      * versioni più vecchie, sincronizzate, oggi usate MOLTO raramente.
 *      * a livello didattico è sufficiente: "esistono, ma quasi mai usate".
 *
 * In questo file facciamo solo un assaggio.
 * Nel file MainArrayList.java andiamo pesanti sulle ArrayList.
 */
public class MainIntroduzioneCollezioni {

    public static void main(String[] args) {

        // ============================================================
        // ESEMPIO: List come TIPO GENERALE, ArrayList come IMPLEMENTAZIONE
        // ============================================================

        // Convenzione: lato SINISTRO uso l'interfaccia,
        // lato DESTRO uso una specifica implementazione.
        List<String> listaArray = new ArrayList<>();
        List<String> listaLinked = new LinkedList<>();

        listaArray.add("Primo");
        listaArray.add("Secondo");
        listaArray.add("Terzo");

        listaLinked.add("Primo");
        listaLinked.add("Secondo");
        listaLinked.add("Terzo");

        System.out.println("Contenuto di listaArray (ArrayList):");
        for (String s : listaArray) {
            System.out.println(" - " + s);
        }

        System.out.println("\nContenuto di listaLinked (LinkedList):");
        for (String s : listaLinked) {
            System.out.println(" - " + s);
        }

        // In questo esempio il comportamento è identico.
        // Le DIFFERENZE stanno soprattutto nelle PRESTAZIONI interne,
        // non nel modo in cui tu usi la lista.

        // Per il resto della lezione useremo ArrayList come lista di riferimento.
    }
}
