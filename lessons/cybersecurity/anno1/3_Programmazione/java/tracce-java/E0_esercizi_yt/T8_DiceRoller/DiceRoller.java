package E0_esercizi_yt.T8_DiceRoller;

public class DiceRoller {

    // Minigioco che in base all'input dell'utente simula il lancio di un dado a 6 facce,
    // stampando la faccia del dado nel terminale:

    static void main() {
        java.util.Random random = new java.util.Random();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Benvenuto nel simulatore di lancio del dado!");
        System.out.println("Quanti dadi vuoi lanciare?");
        int numDadi = scanner.nextInt();
        int total = 0;

        while (numDadi <= 0) {
            System.out.println("Per favore, inserisci un numero positivo di dadi da lanciare:");
            numDadi = scanner.nextInt();
        }

        System.out.println("Lanciando " + numDadi + " dado(i) a 6 facce...");

         for (int i = 1; i <= numDadi; i++) {
            int lancioDado = random.nextInt(1, 7); // Genera un intero casuale tra 1 e 6
             stampaFaccia(lancioDado);
            System.out.println("Dado " + i + ": " + lancioDado);
            total += lancioDado;
        }
        System.out.println("Totale dei dadi lanciati: " + total);


        scanner.close();
    }

    static void stampaFaccia(int numero) {

        String faccia1 = """
                -----------
                |         |
                |    *    |
                |         |
                -----------""";

        String faccia2 = """
                -----------
                | *       |
                |         |
                |       * |
                -----------""";

        String faccia3 = """
                -----------
                | *       |
                |    *    |
                |       * |
                -----------""";

        String faccia4 = """
                -----------
                | *     * |
                |         |
                | *     * |
                -----------""";

        String faccia5 = """
                -----------
                | *     * |
                |    *    |
                | *     * |
                -----------""";

        String faccia6 = """
                -----------
                | *     * |
                | *     * |
                | *     * |
                -----------""";

        switch (numero) {
            case 1 -> System.out.println(faccia1);
            case 2 -> System.out.println(faccia2);
            case 3 -> System.out.println(faccia3);
            case 4 -> System.out.println(faccia4);
            case 5 -> System.out.println(faccia5);
            case 6 -> System.out.println(faccia6);
            default -> System.out.println("Numero non valido per un dado a 6 facce.");
        }
    }
}
