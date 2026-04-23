package M02_Matematica.L03_NumeriRandomici;

import java.util.Random;

public class numeriRandomici {
    static void main() {

// In Java, per generare numeri randomici, ci sono due strade:

        // 1. Usare la classe Random del package java.util
        Random random = new Random();
        int numeroRandomClasseRandom = random.nextInt(10); // Genera un intero casuale tra 0 e 9
        System.out.println("Numero randomico usando la classe Random: " + numeroRandomClasseRandom);
        int numeroRandomClasseRandom2 = random.nextInt(100); // Genera un intero casuale tra 0 e 99
        System.out.println("Numero randomico usando la classe Random: " + numeroRandomClasseRandom2);
        int numeroRandomClasseRandom3 = random.nextInt(50) + 50; // Genera un intero casuale tra 50 e 99
        System.out.println("Numero randomico usando la classe Random: " + numeroRandomClasseRandom3);
        int numeroRandomClasseRandom4 = random.nextInt(1,6); // Genera un intero casuale tra 1 e 5 (destro escluso)
        System.out.println("Numero randomico usando la classe Random (1-5): " + numeroRandomClasseRandom4);

        // E' comodo, infatti per il lancio di un dado si può fare così:
        int lancioDado = random.nextInt(1, 7); // 1–6
        System.out.println("Lancio del dado (1-6): " + lancioDado);
        // Per lanciare il dado di Dungeons & Dragons (d20):
        int lancioDadoD20 = random.nextInt(1, 21); // 1–20
        System.out.println("Lancio del dado D20 (1-20): " + lancioDadoD20);

        // Ovviamente se si chiama nextDouble() si ottiene un double casuale tra 0.0 e 1.0
        double numeroRandomDouble = random.nextDouble();
        System.out.println("Numero randomico double usando la classe Random: " + numeroRandomDouble);
        double numeroRandomDouble2 = random.nextDouble() * 100; // double tra 0.0 e 100.0
        System.out.println("Numero randomico double tra 0.0 e 100.0 usando la classe Random: " + numeroRandomDouble2);

        // Comodità assurda: codificare il lancio di moneta con boolean randomici!
        boolean lancioMoneta = random.nextBoolean(); // true o false casuale
        if (lancioMoneta) {
            System.out.println("Lancio della moneta: Testa");
        } else {
            System.out.println("Lancio della moneta: Croce");
        }

// ==============================================================================

        // 2. Usare il metodo Math.random() come si faceva in C/C++ con rand()
// ==========================================================
//                GENERAZIONE DI NUMERI RANDOMICI IN JAVA
// ==========================================================
//
// Math.random() restituisce un numero double compreso tra:
//      0.0 (incluso)  →  1.0 (escluso)
// È quindi perfetto per generare intervalli tramite scalatura.
//
// FORMULA BASE:
//      double r = Math.random();   // 0.0 <= r < 1.0
//
// Da qui si costruiscono tutti gli altri casi.
// ==========================================================


// ----------------------------------------------------------
// Esempio 1: Numero double casuale tra 0.0 e 1.0
// ----------------------------------------------------------
        double numeroRandom1 = Math.random();
        System.out.println("Numero randomico tra 0.0 e 1.0: " + numeroRandom1);


// ----------------------------------------------------------
// Esempio 2: Intero casuale tra 0 e max (max escluso)
// Formula: (int)(Math.random() * max)
// ----------------------------------------------------------
        int numeroRandom2 = (int)(Math.random() * 10); // 0–9
        System.out.println("Intero randomico tra 0 e 9 (max escluso): " + numeroRandom2);


// ----------------------------------------------------------
// Esempio 3: Intero casuale tra 0 e max (max incluso)
// Formula: (int)(Math.random() * (max + 1))
// ----------------------------------------------------------
        int numeroRandomIncluso = (int)(Math.random() * (10 + 1)); // 0–10
        System.out.println("Intero randomico tra 0 e 10 (incluso): " + numeroRandomIncluso);


// ----------------------------------------------------------
// Esempio 4: Intero casuale tra min e max (ENTRAMBI inclusi)
// Formula: (int)(Math.random() * (max - min + 1)) + min
// ----------------------------------------------------------
        int min = 5;
        int max = 15;
        int numeroRandom3 = (int)(Math.random() * (max - min + 1)) + min; // 5–15
        System.out.println("Intero randomico tra " + min + " e " + max + " (inclusi): " + numeroRandom3);


// ----------------------------------------------------------
// Esempio 5: Intervallo inclusivo generico (uguale al precedente)
// Utile quando vuoi ribadire la formula generale per min–max inclusivi
// ----------------------------------------------------------
        int numeroRandomIncluso2 = (int)(Math.random() * (max - min + 1)) + min;
        System.out.println("Intero randomico tra " + min + " e " + max + " (inclusi): " + numeroRandomIncluso2);


    }
}
