package E0_esercizi_yt.T12_Impiccato;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Impiccato {

    static void main() {

        // Gioco dell'Impiccato (attento a non sbagliare troppo!)

        String word = getRandomWord();

        if (word == null) {
            System.out.println("Errore: impossibile caricare le parole dal file!");
            return;
        }

        Scanner sc = new Scanner(System.in);

        ArrayList<Character> wordState = new ArrayList<>();
        int tentativiSbagliati = 0;

        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        System.out.println("************************************");
        System.out.println("Benvenuto al gioco dell'Impiccato!");
        System.out.println("************************************");

        System.out.println(getFiguraImpiccato(tentativiSbagliati)); // Stampiamo la situazione iniziale

        while (tentativiSbagliati < 8 && wordState.contains('_')) {

            System.out.println();
            System.out.println("Tentativi sbagliati: " + tentativiSbagliati + " su 8");
            System.out.println();
            System.out.print("Parola generata: ");

            for (Character c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("Inserisici una lettera e premi Invio: ");
            char letteraTentata = sc.next().toLowerCase().charAt(0);

            if (word.indexOf(letteraTentata) != -1) {
                System.out.println("Brav*!");

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letteraTentata) {
                        wordState.set(i, letteraTentata);
                    }
                }

            } else {
                tentativiSbagliati++;
                System.out.println("Lettera non presente nella parola.");
                System.out.println(getFiguraImpiccato(tentativiSbagliati));
            }

        }

        // O abbiamo vinto, o siamo stati impiccati
        if (tentativiSbagliati < 8) {
            System.out.println("Complimenti! Hai indovinato la parola: " + word);
        } else {
            System.out.println("Peccato! Sei stato impiccato. La parola era: " + word);
        }

        sc.close();

    }

    static String getRandomWord() {
        ArrayList<String> parole = new ArrayList<>();

        try {
            File file = new File("1_PRIMO_ANNO/java/tracce-java/E0_esercizi_yt/T12_Impiccato/parole.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String parola = fileScanner.nextLine().trim();
                if (!parola.isEmpty()) {
                    parole.add(parola.toLowerCase());
                }
            }

            fileScanner.close();

            if (parole.isEmpty()) {
                return null;
            }

            Random random = new Random();
            return parole.get(random.nextInt(parole.size()));

        } catch (FileNotFoundException e) {
            System.out.println("File parole.txt non trovato!");
            return null;
        } catch(IOException e) {
            System.out.println("Errore di I/O durante la lettura del file parole.txt!");
            return null;
        }
    }

    static String getFiguraImpiccato(int tentativiSbagliati) {

        return switch(tentativiSbagliati){
            case 0 -> """
                    
                    
                    
                    
                    
                    =========""";
            case 1 -> """
                    
                    
                    
                    
                    |
                    =========""";
            case 2 -> """
                    
                    |
                    |
                    |
                    |
                    =========""";
            case 3 -> """
                    +---+
                    |
                    |
                    |
                    |
                    =========""";
            case 4 -> """
                    +---+
                    |   O
                    |
                    |
                    |
                    =========""";
            case 5 -> """
                    +---+
                    |   O
                    |   |
                    |
                    |
                    =========""";
            case 6 -> """
                    +---+
                    |   O
                    |  /|\\
                    |
                    |
                    =========""";
            case 7 -> """
                    +---+
                    |   O
                    |  /|\\
                    |  /
                    |
                    =========""";
            case 8 -> """
                    +---+
                    |   O
                    |  /|\\
                    |  / \\
                    |
                    =========""";
            default -> "";
        };
    }
}
