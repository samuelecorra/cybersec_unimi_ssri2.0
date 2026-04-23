package M09_OOP.L19_Eccezioni;

public class MainEccezioniBase {

    static void main(String[] args) {

        System.out.println("=== Esempio 1: Divisione per zero ===");

        try {
            int a = 10;
            int b = 0;
            int risultato = a / b;   // genera ArithmeticException
            System.out.println("Risultato: " + risultato);
        } catch (ArithmeticException e) {
            System.out.println("Errore: divisione per zero!");
            System.out.println("Messaggio dell'eccezione: " + e.getMessage());
        }

        System.out.println("\nIl programma continua normalmente.\n");

        // -------------------------------------------------------------

        System.out.println("=== Esempio 2: Accesso oltre i limiti di un array ===");

        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Errore: indice fuori dai limiti dell'array!");
        }

        System.out.println("\nProgramma ancora vivo.\n");

        // -------------------------------------------------------------

        System.out.println("=== Esempio 3: NullPointerException ===");

        try {
            String s = null;
            System.out.println(s.length()); // NPE
        } catch (NullPointerException e) {
            System.out.println("Errore: oggetto null!");
        }

        System.out.println("\nFINE ESEMPI BASE");
    }
}
