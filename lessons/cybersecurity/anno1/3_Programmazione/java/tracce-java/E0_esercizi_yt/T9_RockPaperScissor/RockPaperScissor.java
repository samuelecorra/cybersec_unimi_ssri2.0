package E0_esercizi_yt.T9_RockPaperScissor;

import java.util.Random;
import java.util.Scanner;
import java.util.Locale;

public class RockPaperScissor {

    // Variabili statiche per mantenere le statistiche tra le partite
    private static int vittorieUtente = 0;
    private static int vittorieIA = 0;
    private static int pareggi = 0;

    public static void main(String[] args) {

        // Implementazione del gioco
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[] opzioni = {"sasso", "carta", "forbice"};

        boolean playAgain = true;

        System.out.println("Benvenuto a Sasso, Carta, Forbice!");

        while (playAgain) {
            System.out.print("Inserisci la tua scelta (Sasso, Carta, Forbice): ");
            String sceltaUtente = sc.nextLine().trim().toLowerCase(Locale.ROOT);

            // Validazione dell'input utente
            while (!sceltaUtente.equals("sasso") && !sceltaUtente.equals("carta") && !sceltaUtente.equals("forbice")) {
                System.out.print("Scelta non valida. Per favore, inserisci Sasso, Carta o Forbice: ");
                sceltaUtente = sc.nextLine().trim().toLowerCase(Locale.ROOT);
            }

            // Scelta casuale dell'IA
            int indiceCasuale = rand.nextInt(3);
            String sceltaIA = opzioni[indiceCasuale];
            System.out.println("L'IA ha scelto: " + sceltaIA);

            // Determinazione del vincitore
            if (sceltaUtente.equals(sceltaIA)) {
                System.out.println("Pareggio!");
                pareggi++;
            } else if ((sceltaUtente.equals("sasso") && sceltaIA.equals("forbice")) ||
                    (sceltaUtente.equals("carta") && sceltaIA.equals("sasso")) ||
                    (sceltaUtente.equals("forbice") && sceltaIA.equals("carta"))) {
                System.out.println("Hai vinto!");
                vittorieUtente++;
            } else {
                System.out.println("Hai perso!");
                vittorieIA++;
            }

            // Statistiche attuali
            System.out.println("Statistiche attuali:");
            System.out.println("Vittorie utente: " + vittorieUtente);
            System.out.println("Vittorie IA: " + vittorieIA);
            System.out.println("Pareggi: " + pareggi);

            System.out.println("Vuoi giocare di nuovo? (sì/no): ");
            String risposta = sc.nextLine().trim().toLowerCase(Locale.ROOT);
            playAgain = risposta.equals("sì") || risposta.equals("si");
        }

        System.out.println("Grazie per aver giocato!");
        sc.close();
    }
}
