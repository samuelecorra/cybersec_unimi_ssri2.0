package M11_JavaAvanzato.L04_Generics;

import java.util.ArrayList;

public class IntroGenerics {

    // La programmazione generica in Java è un paradigma che consente di definire classi,
    // interfacce e/o metodi con svariati tipi di dati, senza specificare un tipo concreto.

    // Si usa un placeholder (spesso una lettera maiuscola come T, E, K, V) per rappresentare il tipo generico.
    // che fa da type parameter, ovvero da segnaposto per un tipo di dato che verrà specificato
    // al momento dell'istanza della classe o della chiamata del metodo.

    // D'altro canto, possiamo anche usufruire di type argument, ovvero del tipo concreto
    // che viene passato al type parameter quando si crea un'istanza di una classe generica
    // o si chiama un metodo generico.

    static void main() {

        // In questo caso specifichiamo il type argument, non il qualsiasi type parameter
        ArrayList<String> frutti = new ArrayList<>(); // Il secondo diamond operator è opzionale da Java 7 in poi
        // infatti si tende a lasciarlo vuoto tanto Java inferisce il tipo automaticamente

        frutti.add("Mela");
        frutti.add("Banana");
        frutti.add("Arancia");

        // Ovviamente il type argument è arbitrario:
        ArrayList<Integer> interi = new ArrayList<>();

        interi.add(1);
        interi.add(2);
        interi.add(3);

        // ============================================================

        // Ora invece programmiamo genericamente:
        Box<String> scatola = new Box<>();

        scatola.setItem("Contenuto: Vinile");
        System.out.println("Contenuto della scatola di Stringhe: " + scatola.getItem());

        // Cambiamo il tipo di dato contenuto nella scatola:
        Box<Integer> scatolaInteri = new Box<>();
        scatolaInteri.setItem(42);
        System.out.println("Contenuto della scatola di interi: " + scatolaInteri.getItem());

        // E ancora..
        Box<Double> scatolaDouble = new Box<>();
        scatolaDouble.setItem(3.14);
        System.out.println("Contenuto della scatola di double: " + scatolaDouble.getItem());

        // ============================================================

        // Proviamo con prodotto:
        Prodotto<String, Double> prodotto = new Prodotto<>("Laptop", 999.99);
        System.out.println("Prodotto: " + prodotto.getNome() + ", Prezzo: " + prodotto.getPrezzo());

        Prodotto<String, Integer> prodotto2 = new Prodotto<>("Smartphone", 799);
        System.out.println("Prodotto: " + prodotto2.getNome() + ", Prezzo: " + prodotto2.getPrezzo());

        // Questa era solo un'introduzione molto basilare ai generics in Java.
        // Si consulti il README.md per approfondire!
    }
}
