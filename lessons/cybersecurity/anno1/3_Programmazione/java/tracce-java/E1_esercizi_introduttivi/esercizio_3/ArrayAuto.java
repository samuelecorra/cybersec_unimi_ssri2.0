package E1_esercizi_introduttivi.esercizio_3;

import java.util.Scanner;

public class ArrayAuto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il numero di auto che vuoi inserire: ");
        int dimensione = scanner.nextInt();
        // Puliamo il buffer di input dalla ultima newline
        scanner.nextLine();

        String[] automobili;
        automobili = new String[dimensione];

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