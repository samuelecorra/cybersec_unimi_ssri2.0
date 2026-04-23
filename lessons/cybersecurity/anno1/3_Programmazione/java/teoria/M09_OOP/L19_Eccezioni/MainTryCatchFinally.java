package M09_OOP.L19_Eccezioni;

/*
 * Dimostrazione del blocco finally.
 * È un blocco che VIENE SEMPRE ESEGUITO.
 */
public class MainTryCatchFinally {

    static void main(String[] args) {

        System.out.println("=== Dimostrazione del finally ===");

        try {
            System.out.println("Codice pericoloso...");
            int n = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ECCEZIONE CATTURATA: divisione per zero.");
        } finally {
            System.out.println("Blocco finally ESEGUITO comunque.");
        }

        System.out.println("\nFine dimostrazione.\n");
    }
}
