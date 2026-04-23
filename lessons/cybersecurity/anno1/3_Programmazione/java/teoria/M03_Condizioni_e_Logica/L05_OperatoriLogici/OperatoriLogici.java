package M03_Condizioni_e_Logica.L05_OperatoriLogici;

import java.util.Scanner;

public class OperatoriLogici {
    public static void main(String[] args) {

        // Operatori logici in Java
        // AND logico: &&
        // OR logico: ||
        // NOT logico: !

        boolean condizioneA = true;
        boolean condizioneB = false;

        // Esempio 1: AND logico
        if (condizioneA && condizioneB) {
            System.out.println("Entrambe le condizioni sono vere.");
        } else {
            System.out.println("Almeno una delle condizioni è falsa."); // Verrà stampato
        }

        // Esempio 2: OR logico
        if (condizioneA || condizioneB) {
            System.out.println("Almeno una delle condizioni è vera."); // Verrà stampato
        } else {
            System.out.println("Entrambe le condizioni sono false.");
        }

        // Esempio 3: NOT logico
        if (!condizioneB) {
            System.out.println("La condizione B è falsa."); // Verrà stampato
        }

        // Ricordiamo che la precedenza degli operatori logici è:
        // 1. NOT (!)
        // 2. AND (&&) e 3. OR (||)
        // Hanno precedenza più alta rispetto agli operatori di confronto (==, !=, <, >, <=, >=) e
        // più bassa rispetto agli operatori aritmetici (+, -, *, /, %).

        // Spesso è buona pratica usare le parentesi per chiarire l'ordine di valutazione:
        // Spesso si usano per concatenare più condizioni in modo leggibile.
        // ESEMPIO:
        boolean eSoleggiato = true;
        boolean eWeekend = true;
        boolean dobbiamoStudiare = false;
        if (eSoleggiato && (eWeekend || !dobbiamoStudiare)) {
            System.out.println("Andiamo al mare!"); // Verrà stampato
        } else {
            System.out.println("Rimaniamo a casa.");
        }

        // Altri esempi complessi con tante condizioni logiche concatenate:
        int eta = 20;
        boolean haPatente = true;
        boolean haAssicurazione = false;
        // Come detto prima, >= ha la precedenza su &&, quindi questa espressione
        // viene valutata come: (eta >= 18) && haPatente && ha Assicurazione
        if (eta >= 18 && haPatente && haAssicurazione) {
            System.out.println("Puoi guidare l'auto.");
        } else {
            System.out.println("Non puoi guidare l'auto."); // Verrà stampato
        }

        // Possiamo fare validare un username e password con condizioni logiche:
        String username = "utente123";
        String password = "passwordSicura!";
        String usernameCorretta = "utente123";
        String passwordCorretta = "passwordSicura!";
        if (username.equals(usernameCorretta) && password.equals(passwordCorretta)) {
            System.out.println("Accesso consentito."); // Verrà stampato
        } else {
            System.out.println("Accesso negato.");
        }

        // o ancora, con user input:
        Scanner scanner = new Scanner(System.in);

        // username deve essere tra 5 e 15 caratteri
        // non può contenere spazi o _
        System.out.print("Inserisci un username: ");
        String userInput = scanner.nextLine();
        boolean lunghezzaValida = userInput.length() >= 5 && userInput.length() <= 15;
        boolean senzaSpaziEOUnderscore = !userInput.contains(" ") && !userInput.contains("_");
        if (lunghezzaValida && senzaSpaziEOUnderscore) {
            System.out.println("Username valido."); // Verrà stampato se rispetta le condizioni
        } else {
            System.out.println("Username non valido.");
        }

    }
}
