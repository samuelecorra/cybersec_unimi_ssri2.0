package E1_esercizi_introduttivi.esercizio_3;

import java.util.Scanner;

public class ArrayAuto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dimensione;
        do {
            System.out.print("Inserisci il numero di auto che vuoi inserire (1-100): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Valore non valido. Inserisci un intero tra 1 e 100: ");
                scanner.next();
            }
            dimensione = scanner.nextInt();
            if (dimensione < 1 || dimensione > 100) {
                System.out.println("La dimensione deve essere compresa tra 1 e 100.");
            }
        } while (dimensione < 1 || dimensione > 100);
        // Puliamo il buffer di input dalla ultima newline
        scanner.nextLine();

        String[] automobili = new String[dimensione];

        // Inserimento dei valori nell'array
        for (int i = 0; i < automobili.length; i++) {
            System.out.print("Inserisci il valore per l'elemento " + i + ": ");
            automobili[i] = scanner.nextLine();
        }

        // Stampa dei valori dell'array
        System.out.println("I valori inseriti nell'array sono:");
        for (int i = 0; i < automobili.length; i++) {
            System.out.println("Auto " + i + " inserita nel database: " + automobili[i]);
        }

        scanner.close();
    }
}
