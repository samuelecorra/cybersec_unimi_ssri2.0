package E4_avanzati.ClassificaVideogame.Main;

import E4_avanzati.ClassificaVideogame.Classi.*;
import java.util.Scanner;

public class MainClassificaVideogame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Classifica classifica = new Classifica();

        boolean continua = true;

        while (continua) {
            System.out.println();
            System.out.println("=== MENU CLASSIFICA VIDEOGIOCO ===");
            System.out.println("1) Aggiungi giocatore");
            System.out.println("2) Aggiorna punteggio");
            System.out.println("3) Stampa classifica ordinata");
            System.out.println("4) Esci");
            System.out.print("Scelta: ");

            int scelta;
            try {
                scelta = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
                continue;
            }

            switch (scelta) {
                case 1 -> {
                    System.out.print("Id giocatore (intero positivo): ");
                    int id = leggiIntero(scanner);

                    System.out.print("Nickname: ");
                    String nickname = scanner.nextLine();

                    System.out.print("Punteggio iniziale: ");
                    int punteggio = leggiIntero(scanner);

                    boolean ok = classifica.aggiungiGiocatore(id, nickname, punteggio);
                    if (ok) {
                        System.out.println("Giocatore aggiunto correttamente.");
                    } else {
                        System.out.println("Errore: esiste già un giocatore con questo id.");
                    }
                }
                case 2 -> {
                    System.out.print("Id del giocatore da aggiornare: ");
                    int id = leggiIntero(scanner);

                    System.out.print("Nuovo punteggio: ");
                    int nuovoPunteggio = leggiIntero(scanner);

                    boolean ok = classifica.aggiornaPunteggio(id, nuovoPunteggio);
                    if (ok) {
                        System.out.println("Punteggio aggiornato.");
                    } else {
                        System.out.println("Nessun giocatore trovato con questo id.");
                    }
                }
                case 3 -> classifica.stampaClassificaOrdinata();
                case 4 -> {
                    System.out.println("Uscita dal programma.");
                    continua = false;
                }
                default -> System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
    }

    private static int leggiIntero(Scanner scanner) {
        while (true) {
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Valore non valido, riprova: ");
            }
        }
    }
}

