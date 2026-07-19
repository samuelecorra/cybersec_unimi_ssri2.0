package E1_esercizi_introduttivi.esercizio_2;

import java.util.Scanner;

public class InteresseComposto {

    public static void main(String[] args) {

        // Creiamo lo scanner per leggere i dati in input
        Scanner scanner = new Scanner(System.in);

        double capitaleIniziale;
        double tassoInteresse;
        int n;
        int t;

        // --- LETTURA E VALIDAZIONE DEL CAPITALE INIZIALE ---
        do {
            System.out.print("Inserisci il capitale iniziale (>= 0): ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Valore non valido! Inserisci un numero: ");
                scanner.next(); // scarta l’input errato
            }
            capitaleIniziale = scanner.nextDouble();
            if (!Double.isFinite(capitaleIniziale) || capitaleIniziale < 0)
                System.out.println("Il capitale deve essere finito e non negativo!");
        } while (!Double.isFinite(capitaleIniziale) || capitaleIniziale < 0);

        // --- LETTURA E VALIDAZIONE DEL TASSO DI INTERESSE ---
        do {
            System.out.print("Inserisci il tasso di interesse (in percentuale, >= 0): ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Valore non valido! Inserisci un numero: ");
                scanner.next();
            }
            tassoInteresse = scanner.nextDouble();
            if (!Double.isFinite(tassoInteresse) || tassoInteresse < 0)
                System.out.println("Il tasso deve essere finito e non negativo!");
        } while (!Double.isFinite(tassoInteresse) || tassoInteresse < 0);

        // Conversione in forma decimale
        tassoInteresse /= 100;

        // --- LETTURA E VALIDAZIONE DEL NUMERO DI CAPITALIZZAZIONI ---
        do {
            System.out.print("Inserisci il numero di volte che l'interesse viene capitalizzato in un anno (>= 1): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Valore non valido! Inserisci un intero: ");
                scanner.next();
            }
            n = scanner.nextInt();
            if (n < 1)
                System.out.println("Il numero di capitalizzazioni deve essere almeno 1!");
        } while (n < 1);

        // --- LETTURA E VALIDAZIONE DEL NUMERO DI ANNI ---
        do {
            System.out.print("Inserisci il numero di anni (>= 0): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Valore non valido! Inserisci un intero: ");
                scanner.next();
            }
            t = scanner.nextInt();
            if (t < 0)
                System.out.println("Il numero di anni non può essere negativo!");
        } while (t < 0);

        // --- CALCOLO DEL MONTANTE FINALE ---
        double montanteFinale = capitaleIniziale
                * Math.pow(1 + tassoInteresse / n, (double) n * t);

        // --- OUTPUT ---
        System.out.printf("Il montante finale dopo %d anni è: %.2f%n", t, montanteFinale);

        scanner.close();
    }
}
