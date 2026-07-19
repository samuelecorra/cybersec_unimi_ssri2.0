package M01_Fondamenti.L05_UserInput;

// Se in C c'era scanf mediante stdio.h, in Java per leggere l'input
// da console si usa la classe Scanner, che fa parte del package java.util.
import java.util.Scanner;
import java.util.Locale;

public class UserInput {
    public static void main(String[] args) {
        // Creiamo un oggetto Scanner per leggere l'input da console (System.in)
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT); // separatore decimale '.' indipendente dal sistema

        // Con scanf usavamo i format specifiers (%d, %f, %s, ecc.),
        // in Java invece usiamo metodi specifici dell'oggetto Scanner
        // che sono più sicuri, leggibili e intuitivi da usare.

        // Chiediamo all'utente di inserire un numero intero
        System.out.print("Inserisci un numero intero: ");
        int numeroIntero = scanner.nextInt(); // Legge un intero
        scanner.nextLine(); // Consuma il newline rimasto dal nextInt

        // Chiediamo all'utente di inserire un numero decimale
        System.out.print("Inserisci un numero decimale: ");
        double numeroDecimale = scanner.nextDouble(); // Legge un double
        scanner.nextLine(); // Consuma il newline rimasto dal nextDouble

        // Chiediamo all'utente di inserire un long:
        System.out.print("Inserisci un numero long: ");
        long numeroLong = scanner.nextLong(); // Legge un long
        scanner.nextLine(); // Consuma il newline rimasto dal nextLong

        // Chiediamo all'utente di inserire un valore booleano
        System.out.print("Inserisci un valore booleano (true/false): ");
        boolean booleano = scanner.nextBoolean(); // Legge un booleano
        scanner.nextLine(); // Consuma il newline rimasto dal nextBoolean

        // Chiediamo all'utente di inserire una stringa
        System.out.print("Inserisci una stringa: ");
        String testo = scanner.nextLine(); // Legge una linea di testo
        // scanner.nextLine(); non serve qui perché stiamo leggendo una linea completa

        // Chiediamo all'utente di inserire un singolo carattere
        System.out.print("Inserisci un singolo carattere: ");
        String rigaCarattere = scanner.nextLine();
        if (rigaCarattere.isEmpty()) {
            System.err.println("Non è stato inserito alcun carattere.");
            scanner.close();
            return;
        }
        char carattere = rigaCarattere.charAt(0); // prende la prima unità UTF-16 della riga
        // scanner.nextLine(); non serve qui perché stiamo leggendo una linea completa

        // Stampiamo i valori inseriti dall'utente
        System.out.println("Hai inserito l'intero: " + numeroIntero);
        System.out.println("Hai inserito il decimale: " + numeroDecimale);
        System.out.println("Hai inserito il long: " + numeroLong);
        System.out.println("Hai inserito il booleano: " + booleano);
        System.out.println("Hai inserito la stringa: " + testo);
        System.out.println("Hai inserito il carattere: " + carattere);

        // Chiudiamo lo scanner per evitare perdite di risorse
        scanner.close();
    }
}
