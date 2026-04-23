package M09_OOP.L19_Eccezioni;

public class MainCalcolatriceConEccezioni {

    static void main(String[] args) {

        System.out.println("=== Calcolatrice con eccezioni ===");

        try {
            int r1 = Calcolatrice.dividi(10, 2);
            System.out.println("10 / 2 = " + r1);

            int r2 = Calcolatrice.dividi(10, 0); // genera eccezione
            System.out.println("10 / 0 = " + r2);

        } catch (IllegalArgumentException e) {
            System.out.println("Errore della calcolatrice: " + e.getMessage());
        }

        try {
            int r3 = Calcolatrice.radiceQuadrata(-25);
            System.out.println(r3);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nella radice: " + e.getMessage());
        }

        System.out.println("\n=== Fine dimostrazione ===");
    }
}
