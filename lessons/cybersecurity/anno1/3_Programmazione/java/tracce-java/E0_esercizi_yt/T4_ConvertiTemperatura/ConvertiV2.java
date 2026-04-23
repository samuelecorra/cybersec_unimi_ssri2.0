package E0_esercizi_yt.T4_ConvertiTemperatura;

import java.util.Scanner;

public class ConvertiV2 {

    // Similmente allo scorso programma, vediamo come chiedere all'utente
    // se vuole convertire da Celsius a Fahrenheit o viceversa, e poi gli chiediamo
    // la temperatura da convertire, per poi mostrargli il risultato.

    static void main() {

        Scanner sc = new Scanner(System.in);
        double tIn;
        double tOut;
        String unita;

        System.out.println("Convertitore di temperature!");
        System.out.print("Inserisci la temperatura da convertire: ");
        tIn = sc.nextDouble();

        System.out.println("Vuoi convertire in Celsius o Fahrenheit?");
        System.out.print("Inserisci C o F: ");
        unita = sc.next().toUpperCase(); // leggiamo solo il primo carattere e lo portiamo in maiuscolo

        // Ternary operator per scegliere la conversione al posto di un if-else
        tOut = unita.equals("C") ? ((tIn - 32) * 5/9) : unita.equals("F") ? (tIn * 9/5 + 32) : Double.NaN;
        // Si noti che dopo il secondo ? c'è un altro unita.equals("F") per gestire il caso in cui l'input non sia valido
        // Diciamo che stiamo concatenando due operatori ternari per simulare un if-elseif-else
        System.out.printf("La temperatura convertita è: %.2f°%s.%n", tOut, unita);

    }
}
