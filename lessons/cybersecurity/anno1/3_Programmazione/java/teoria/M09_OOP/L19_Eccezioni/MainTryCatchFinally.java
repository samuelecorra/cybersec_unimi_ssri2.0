package M09_OOP.L19_Eccezioni;

/*
 * Dimostrazione del blocco finally.
 * È eseguito sia nel percorso normale sia dopo un'eccezione gestita.
 */
public class MainTryCatchFinally {

    public static void main(String[] args) {

        System.out.println("=== Dimostrazione del finally ===");

        try {
            System.out.println("Codice pericoloso...");
            int divisore = args.length; // con zero argomenti vale 0
            int n = 10 / divisore;
            System.out.println("Risultato: " + n);
        } catch (ArithmeticException e) {
            System.out.println("ECCEZIONE CATTURATA: divisione per zero.");
        } finally {
            System.out.println("Blocco finally ESEGUITO comunque.");
        }

        System.out.println("\nFine dimostrazione.\n");
    }
}
