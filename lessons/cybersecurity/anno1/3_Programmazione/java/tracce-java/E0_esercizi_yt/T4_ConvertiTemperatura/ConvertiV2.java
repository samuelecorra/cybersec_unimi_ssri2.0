package E0_esercizi_yt.T4_ConvertiTemperatura;

import java.util.Scanner;
import java.util.Locale;

public class ConvertiV2 {

    // Similmente allo scorso programma, vediamo come chiedere all'utente
    // se vuole convertire da Celsius a Fahrenheit o viceversa, e poi gli chiediamo
    // la temperatura da convertire, per poi mostrargli il risultato.

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double tIn;
        double tOut;
        String unita;

        System.out.println("Convertitore di temperature!");
        System.out.print("Inserisci la temperatura da convertire: ");
        tIn = sc.nextDouble();

        System.out.println("Vuoi convertire in Celsius o Fahrenheit?");
        System.out.print("Inserisci C o F: ");
        unita = sc.next().trim().toUpperCase(Locale.ROOT); // leggiamo un token e lo portiamo in maiuscolo

        if (!unita.equals("C") && !unita.equals("F")) {
            System.out.println("Scelta non valida: inserisci C oppure F.");
            sc.close();
            return;
        }

        // Ternary operator per scegliere la conversione al posto di un if-else
        tOut = unita.equals("C") ? ((tIn - 32) * 5 / 9) : (tIn * 9 / 5 + 32);
        System.out.printf("La temperatura convertita è: %.2f°%s.%n", tOut, unita);

        sc.close();

    }
}
