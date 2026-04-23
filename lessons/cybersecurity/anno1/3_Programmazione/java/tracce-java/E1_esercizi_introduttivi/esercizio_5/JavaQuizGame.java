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
                    "Quale supereroe Marvel ha un'IA integrata nel proprio costume?",
                    "In Avengers Infinity War, Peter Parker ha un costume ultra-tech che gli permette di resistere a...",
                    "Chi è il più cervellone dell'Universo Marvel?",
                    "Chi era Asimov e perché ora è tornato in voga?"
            };

            String[][] opzioni = {
                    {"CPU", "GPU", "RAM", "Hard Disk"},
                    {"Iron Man", "Spider-Man", "Black Panther", "Rocket Raccoon"},
                    {
                            "agli esplosivi prediletti come arma dai suoi nemici",
                            "alla carenza di ossigeno a grandi altitudini",
                            "all'alta pressione subacquea",
                            "alla gravità zero nello spazio"
                    },
                    {"Tony Stark", "Reed Richards", "Stephen Strange", "Doctor Victor Von Doom"},
                    {
                            "sci-fi pioneer a cui ora viene dato credito per l'incombenza di AI e robotica intelligente",
                            "Un antagonista di Star Trek con protesi robotiche intelligenti e responsive",
                            "Un regista sci-fi che dopo Zemeckis e altri ha rivoluzionato ex-novo il genere",
                            "Un ingegnere aerospaziale che teorizza l'uso di AI per contattare forme di vita extraterrestri"
                    }
            };

            String[] risposteCorrette = {
                    "CPU",
                    "Iron Man",
                    "alla carenza di ossigeno a grandi altitudini",
                    "Reed Richards",
                    "sci-fi pioneer a cui ora viene dato credito per l'incombenza di AI e robotica intelligente"
            };

            String[] spiegazioni = {
                    "La CPU è il componente principale che esegue le istruzioni dei programmi.",
                    "Iron Man utilizza un'intelligenza artificiale chiamata J.A.R.V.I.S. nel suo costume.",
                    "Tony, durante la battaglia di New York contro i Chitauri, finisce nello spazio profondo. " +
                            "Per Peter, Stark installa un sistema di supporto vitale che compensa le grandi altitudini.",
                    "Reed Richards (Mister Fantastic) è celebre per l'intelligenza straordinaria oltre ai suoi poteri elastici.",
                    "Asimov è stato un autore di fantascienza (le Tre Leggi della Robotica) ed è tornato in auge con l'interesse per AI e robotica."
            };

            int punteggio = 0;
            final int NUM_OPZIONI = 4;

            System.out.println("\n-------------------------");
            System.out.println("       INIZIO QUIZ       ");
            System.out.println("-------------------------\n");

            for (int i = 0; i < domande.length; i++) {
                System.out.println("Domanda " + (i + 1) + ": " + domande[i]);
                for (int j = 0; j < opzioni[i].length; j++) {
                    System.out.println((j + 1) + ". " + opzioni[i][j]);
                }

                int rispostaUtente = leggiRispostaValida(scanner, 1, NUM_OPZIONI);

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

