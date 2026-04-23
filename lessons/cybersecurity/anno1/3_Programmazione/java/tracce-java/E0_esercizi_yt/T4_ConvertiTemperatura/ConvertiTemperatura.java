package E0_esercizi_yt.T4_ConvertiTemperatura;

import java.util.Scanner;

public class ConvertiTemperatura {

    // Similmente allo scorso programma, vediamo come chiedere all'utente
    // se vuole convertire da Celsius a Fahrenheit o viceversa, e poi gli chiediamo
    // la temperatura da convertire, per poi mostrargli il risultato.

    static void main() {

        Scanner sc = new Scanner(System.in);
        double temperaturaInput;
        double temperaturaConvertita;
        String unita;

        System.out.println("Convertitore di temperature!");
        System.out.println("Vuoi convertire in Celsius o Fahrenheit?");
        System.out.print("Inserisci C o F: ");
        unita = sc.next().toUpperCase(); // leggiamo solo il primo carattere e lo portiamo in maiuscolo

        if (unita.equals("C")) {
            System.out.print("Inserisci la temperatura in Fahrenheit: ");
            temperaturaInput = sc.nextDouble();
            temperaturaConvertita = (temperaturaInput - 32) * 5/9;
            System.out.printf("%.2f Fahrenheit sono %.2f°%s.%n", temperaturaInput, temperaturaConvertita, unita);
        } else if (unita.equals("F")) {
            System.out.print("Inserisci la temperatura in Celsius: ");
            temperaturaInput = sc.nextDouble();
            temperaturaConvertita = (temperaturaInput * 9/5) + 32;
            System.out.printf("%.2f Celsius sono %.2f°%s.%n", temperaturaInput, temperaturaConvertita, unita);
        } else {
            System.out.println("Scelta non valida. Per favore, esegui di nuovo il programma e scegli 1 o 2.");
        }

    }
}
