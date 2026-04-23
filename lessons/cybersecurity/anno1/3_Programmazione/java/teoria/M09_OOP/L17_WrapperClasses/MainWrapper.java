package M09_OOP.L17_WrapperClasses;

import java.util.ArrayList;
import java.util.List;

/*
 * ============================================================
 * LEZIONE 17 — WRAPPER CLASSES
 * ============================================================
 *
 * Definizione:
 *  Una wrapper class è una classe che “incapsula” un tipo primitivo
 *  permettendogli di comportarsi come un oggetto.
 *
 * A cosa servono:
 *  - Permettono di usare primitive dentro collezioni (List, Map…)
 *  - Offrono metodi statici utili (parse, confronto, controllo)
 *  - Rendono possibile l’autoboxing/unboxing
 *
 * Consiglio pratico:
 *  usa i wrapper SOLO quando ti servono davvero oggetti.
 */
public class MainWrapper {

    static void main(String[] args) {

        // ============================================================
        // 1. ESEMPIO BASE: Autoboxing e Unboxing
        // ============================================================
        Integer a = 10;    // autoboxing: int -> Integer
        int b = a;         // unboxing: Integer -> int

        System.out.println("Autoboxing: Integer a = " + a);
        System.out.println("Unboxing: int b = " + b);

        // ============================================================
        // 2. Wrapper come OGGETTI con metodi utili
        // ============================================================
        Integer numero = Integer.valueOf(42);
        System.out.println("\nMetodi di Integer:");
        System.out.println("numero = " + numero);
        System.out.println("Max value = " + Integer.MAX_VALUE);
        System.out.println("Min value = " + Integer.MIN_VALUE);

        Double d = Double.parseDouble("3.14159");
        System.out.println("\nDouble.parseDouble: " + d);

        Character c = 'A';
        System.out.println("\nCharacter.isLetter: " + Character.isLetter(c));
        System.out.println("Character.isDigit: " + Character.isDigit(c));

        Boolean bool = Boolean.parseBoolean("true");
        System.out.println("\nBoolean.parseBoolean: " + bool);

        // ============================================================
        // 3. Perché servono i wrapper? Le Collezioni!
        // ============================================================
        System.out.println("\n=== LISTA DI Integer (non posso usare int!) ===");

        List<Integer> valori = new ArrayList<>();

        valori.add(10);  // autoboxing
        valori.add(20);
        valori.add(30);

        for (Integer x : valori) {
            System.out.println("Valore: " + x);
        }

        // ============================================================
        // 4. Esempio utile: conversione String -> numero
        // ============================================================
        String input = "12345";

        int convertito = Integer.parseInt(input);
        System.out.println("\nString \"" + input + "\" convertita in int = " + convertito);

        // ============================================================
        // 5. Esempio più “pratico”: calcolo media con List<Integer>
        // ============================================================
        System.out.println("\n=== Calcolo Media ===");

        List<Integer> voti = new ArrayList<>();
        voti.add(30);
        voti.add(28);
        voti.add(27);
        voti.add(30);

        int somma = 0;
        for (Integer voto : voti) {
            somma += voto;  // unboxing automatico
        }

        double media = (double) somma / voti.size();
        System.out.println("Media = " + media);
    }
}
