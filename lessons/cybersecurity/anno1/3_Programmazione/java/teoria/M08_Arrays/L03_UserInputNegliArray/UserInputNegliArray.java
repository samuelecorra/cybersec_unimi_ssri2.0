package M08_Arrays.L03_UserInputNegliArray;

public class UserInputNegliArray {
    public static void main(String[] args) {

        // User Input negli Array in Java
        // Per ottenere input dall'utente, utilizziamo la classe Scanner
        // che fa parte del package java.util

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Creiamo un array per memorizzare i cibi
        String[] cibi = new String[3];

        // Ciclo per ottenere input dall'utente e popolare l'array
        for (int i = 0; i < cibi.length; i++) { // NOTA: questo è uno dei casi in cui serve il for tradizionale e non l'enhanced con ":"
            System.out.print("Inserisci il cibo " + (i + 1) + ": ");
            cibi[i] = scanner.nextLine();
        }

        // Visualizziamo i cibi inseriti
        System.out.println("I cibi inseriti sono:");
        for (String cibo : cibi) { // Qui invece l'indice non serve internamente quindi usiamo l'enhanced senza problemi
            System.out.println(cibo);
        }

        // ========================================================================

        // 1. COMODITA':
        // Non dobbiamo per forza dichiarare con un valore numerico, ma possiamo passare alla new String[]
        // un'altra variabile intera che contiene la dimensione desiderata, proprio da user input.
        System.out.print("Quanti cibi vuoi inserire? ");
        int numeroCibi = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline rimasto dopo nextInt()

        String[] altriCibi = new String[numeroCibi];

        // E poi popoliamo:
        for (int i = 0; i < altriCibi.length; i++) {
            System.out.print("Inserisci il cibo " + (i + 1) + ": ");
            altriCibi[i] = scanner.nextLine();
        }

        System.out.println("I cibi inseriti sono:");
        for (String cibo : altriCibi) {
            System.out.println(cibo);
        }

        // Chiudiamo lo scanner per evitare perdite di risorse
        scanner.close();
    }
}
