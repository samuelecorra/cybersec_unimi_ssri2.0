package M03_Condizioni_e_Logica.L01_If;

import java.util.Scanner;

public class If {

    // Ci sono tre keyword principali per le condizioni in Java:
    // if, else if, else

    // if (condition) {
        // blocco di codice eseguito se la condizione è vera
    // } else if (anotherCondition) {
        // blocco di codice eseguito se la prima condizione è falsa
        // e un'altra condizione è vera
     // } else {
        // blocco di codice eseguito se tutte le condizioni precedenti sono false
    // }

    static void main() {

        // ESEMPIO 1: Solo un if che spezza il flusso lineare del codice:
        // è il caso più semplice:
        int eta = 17;
        if (eta >= 18) {
            System.out.println("Sei maggiorenne."); // Non verrà stampato
        }

        // ESEMPIO 2: if...else
        if (eta >= 18) {
            System.out.println("Sei maggiorenne.");
        } else {
            System.out.println("Sei minorenne."); // Verrà stampato
        }

        // ESEMPIO 3: if...else if...else
        int voto = 85;
        if (voto >= 90) {
            System.out.println("Ottimo! 🧠");
        } else if (voto >= 75) {
            System.out.println("Buono! 😊"); // Verrà stampato
        } else if (voto >= 60) {
            System.out.println("Sufficiente! 😒");
        } else {
            System.out.println("Insufficiente! 😫");
        }

        // ASSOLUTAMENTE ATTENZIONE ALL'ORDINE DELLE VARIE VALUTAZIONI QUANDO SI VALUTA
        // UNA CATENA DI if...else if...else!

        // ESEMPIO SBAGLIATO:
        if (voto >= 60) {
            System.out.println("Sufficiente! 😒"); // Verrà sempre stampato ANCHE PER VOTI ECCELLENTI!
        } else if (voto >= 75) {
            System.out.println("Buono! 😊"); // mai stampati i successivi!
        } else if (voto >= 90) {
            System.out.println("Ottimo! 🧠");
        } else {
            System.out.println("Insufficiente! 😫");
        }

        // COSA DEDUCIAMO, COME BUONA PRASSI?
        // Quando si scrivono catene di if...else if...else,
        // si deve partire dalla condizione più restrittiva
        // (quella che "filtra" di più) fino ad arrivare
        // a quella più generica.

        // Se come condizione si valutano i booleani, la leggibilità aumenta, a patto di dare
        // nomi significativi alle variabili booleane stesse.
        boolean isStudent = true;
        if (isStudent) { // invece di if (isStudent == true) che è ridondante
            System.out.println("Hai diritto allo sconto studenti. 😛");
        } else {
            System.out.println("Non hai diritto allo sconto studenti. 🥲");
        }


        // Alcuni dei metodi che vedremo in futuro restituiscono booleani,
        // che possono essere usati direttamente nelle condizioni per aumentare la leggibilità del codice.

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il tuo nome:");
        String nome = scanner.nextLine();

        if (nome.isEmpty()) { // isEmpty() restituisce true se la stringa è vuota
            System.out.println("Non hai inserito alcun nome. 🤬");
        } else {
            System.out.println("Ciao, " + nome + "! 😛");
        }

    }
}