package E0_esercizi_yt.T6_NumberGuessingGame;

public class NumberGuessingGame {
    public static void main(String[] args) {
        // Numero casuale tra 1 e 100
        int numeroDaIndovinare = (int) (Math.random() * 100) + 1;
        int tentativi = 0;
        int massimoTentativi = 10;
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Benvenuto al gioco di indovinare il numero!");
        System.out.println("Ho scelto un numero tra 1 e 100. Hai " + massimoTentativi + " tentativi per indovinarlo.");

        while (tentativi < massimoTentativi) {
            System.out.print("Inserisci il tuo tentativo: ");
            int tentativoUtente = scanner.nextInt();
            tentativi++;

            if (tentativoUtente < numeroDaIndovinare) {
                System.out.println("Troppo basso! Hai ancora " + (massimoTentativi - tentativi) + " tentativi.");
            } else if (tentativoUtente > numeroDaIndovinare) {
                System.out.println("Troppo alto! Hai ancora " + (massimoTentativi - tentativi) + " tentativi.");
            } else {
                System.out.println("Congratulazioni! Hai indovinato il numero in " + tentativi + " tentativi.");
                break;
            }
        }

        if (tentativi == massimoTentativi) {
            System.out.println("Mi dispiace, hai esaurito i tentativi. Il numero era: " + numeroDaIndovinare);
        }

        scanner.close();
    }
}
