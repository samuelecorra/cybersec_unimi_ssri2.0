package M11_JavaAvanzato.L09_LambdaExpressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExpressions {

    /*
     * Una lambda fornisce l'implementazione del singolo metodo astratto di
     * un'interfaccia funzionale. @FunctionalInterface non è obbligatoria, ma fa
     * verificare al compilatore che il contratto rimanga davvero funzionale.
     */
    @FunctionalInterface
    interface Operazione {
        int applica(int primo, int secondo);
    }

    public static void main(String[] args) {
        Operazione somma = (a, b) -> a + b;
        Operazione prodotto = (a, b) -> a * b;

        System.out.println("4 + 7 = " + somma.applica(4, 7));
        System.out.println("4 * 7 = " + prodotto.applica(4, 7));

        List<String> linguaggi = new ArrayList<>(
                List.of("Java", "C", "Python", "Go"));

        // Predicate<T>: riceve T e restituisce boolean.
        Predicate<String> almenoQuattroCaratteri = testo -> testo.length() >= 4;
        linguaggi.removeIf(almenoQuattroCaratteri.negate());

        // Comparator<T>: confronta due T. comparingInt costruisce il confronto
        // a partire dalla lunghezza; thenComparing risolve le parità.
        linguaggi.sort(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()));

        // Function<T, R>: trasforma un T in un R.
        Function<String, String> inMaiuscolo = String::toUpperCase;

        // Consumer<T>: consuma un T senza produrre un valore di ritorno.
        Consumer<String> stampa = valore -> System.out.println("- " + valore);
        for (String linguaggio : linguaggi) {
            stampa.accept(inMaiuscolo.apply(linguaggio));
        }

        /*
         * Una lambda può catturare soltanto variabili locali final o
         * "effectively final", cioè mai riassegnate dopo l'inizializzazione.
         */
        String prefisso = "Lunghezza";
        linguaggi.forEach(nome ->
                System.out.println(prefisso + " di " + nome + ": " + nome.length()));

        // prefisso = "Dimensione"; // Renderebbe illegale la cattura precedente.
    }
}
