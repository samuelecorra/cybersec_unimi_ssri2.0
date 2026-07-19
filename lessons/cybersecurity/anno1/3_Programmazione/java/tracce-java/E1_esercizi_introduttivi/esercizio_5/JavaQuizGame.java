package E1_esercizi_introduttivi.esercizio_5;

import java.util.Locale;
import java.util.Scanner;

public class JavaQuizGame {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useLocale(Locale.US); // per sicurezza su eventuali input numerici

            System.out.println("Benvenuto nel gioco del quiz!");
            System.out.print("Inserisci il tuo nome: ");
            String nome = scanner.nextLine().trim();

            if (nome.equalsIgnoreCase("Principessa")) {
                System.out.println("Benvenuta " + nome + "!");
                System.out.println("Premi INVIO per iniziare...");
                scanner.nextLine();
            } else {
                System.out.println("Ciao " + nome + "! Puoi comunque partecipare al quiz.");
                System.out.println("Premi INVIO per iniziare...");
                scanner.nextLine();
            }

            String[] domande = {
                    "Quale componente informatico è filosoficamente detto il cervello del computer?",
                    "Quale IA assiste Tony Stark nei primi film di Iron Man?",
                    "Quale armatura riceve Peter Parker in Avengers: Infinity War?",
                    "Quale membro dei Fantastici Quattro è Reed Richards?",
                    "Per quale idea della sua narrativa robotica è noto Isaac Asimov?"
            };

            String[][] opzioni = {
                    {"CPU", "GPU", "RAM", "Hard Disk"},
                    {"J.A.R.V.I.S.", "KAREN", "Ultron", "Skynet"},
                    {"Iron Spider", "Mark I", "War Machine", "Hulkbuster"},
                    {"Mister Fantastic", "La Torcia Umana", "La Cosa", "La Donna Invisibile"},
                    {"Le Tre Leggi della Robotica", "Il test di Turing", "La macchina analitica", "La legge di Moore"}
            };

            String[] risposteCorrette = {
                    "CPU",
                    "J.A.R.V.I.S.",
                    "Iron Spider",
                    "Mister Fantastic",
                    "Le Tre Leggi della Robotica"
            };

            String[] spiegazioni = {
                    "La CPU è il componente principale che esegue le istruzioni dei programmi.",
                    "J.A.R.V.I.S. è l'assistente IA di Tony Stark nei primi film dedicati a Iron Man.",
                    "L'Iron Spider è l'armatura tecnologica progettata da Tony Stark per Peter Parker.",
                    "Reed Richards è l'identità civile di Mister Fantastic.",
                    "Asimov formulò nella sua narrativa le celebri Tre Leggi della Robotica."
            };

            int punteggio = 0;
            System.out.println("\n-------------------------");
            System.out.println("       INIZIO QUIZ       ");
            System.out.println("-------------------------\n");

            for (int i = 0; i < domande.length; i++) {
                System.out.println("Domanda " + (i + 1) + ": " + domande[i]);
                for (int j = 0; j < opzioni[i].length; j++) {
                    System.out.println((j + 1) + ". " + opzioni[i][j]);
                }

                int rispostaUtente = leggiRispostaValida(scanner, 1, opzioni[i].length);

                String scelta = opzioni[i][rispostaUtente - 1];
                if (scelta.equals(risposteCorrette[i])) {
                    System.out.println("Risposta corretta!");
                    punteggio++;
                } else {
                    System.out.println("Risposta sbagliata. Quella corretta era: " + risposteCorrette[i]);
                    System.out.println("Spiegazione: " + spiegazioni[i]);
                }
                System.out.println("-------------------------");
            }

            int tot = domande.length;
            double percentuale = (punteggio * 100.0) / tot;

            System.out.printf("Punteggio finale: %d su %d (%.2f%%)%n", punteggio, tot, percentuale);
            System.out.println("Grazie per aver giocato!");
        }
    }

    // Legge un intero nell'intervallo [min, max] gestendo input non numerici
    private static int leggiRispostaValida(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("La tua risposta (numero " + min + "-" + max + "): ");
            if (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                scanner.nextLine(); // consuma newline
                if (x >= min && x <= max) return x;
                System.out.println("Valore fuori intervallo. Riprova.");
            } else {
                System.out.println("Input non valido. Inserisci un numero.");
                scanner.next(); // scarta token non numerico
            }
        }
    }
}
