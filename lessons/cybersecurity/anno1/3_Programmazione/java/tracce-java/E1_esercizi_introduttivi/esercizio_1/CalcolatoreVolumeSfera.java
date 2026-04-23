package E1_esercizi_introduttivi.esercizio_1; // riga di intestazione del file che dichiara il package di appartenenza

import java.util.Scanner;

public class CalcolatoreVolumeSfera {
    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il raggio della sfera in cm: ");
        double raggio = scanner.nextDouble();

        double volume = (4.0 / 3.0) * Math.PI * Math.pow(raggio, 3); // V = (4/3) * π * r^3

        System.out.printf("Il volume della sfera con raggio %.2f è: %.2f cm^3", raggio, volume);
        scanner.close();
    }
}