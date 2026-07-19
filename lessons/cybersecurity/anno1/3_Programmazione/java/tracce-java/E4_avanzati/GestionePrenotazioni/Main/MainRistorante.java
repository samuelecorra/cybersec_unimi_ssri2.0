package E4_avanzati.GestionePrenotazioni.Main;

import E4_avanzati.GestionePrenotazioni.Classi.*;
import java.util.Scanner;

public class MainRistorante {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Ristorante ristorante = new Ristorante();

            boolean finito = false;

            while (!finito) {
                System.out.println();
                System.out.println("=== GESTIONE RISTORANTE ===");
                System.out.println("1) Aggiungi tavolo");
                System.out.println("2) Prenota tavolo");
                System.out.println("3) Libera tavolo");
                System.out.println("4) Elenco tavoli liberi");
                System.out.println("5) Elenco tavoli occupati");
                System.out.println("0) Esci");
                System.out.print("Scelta: ");

                int scelta;
                try {
                    scelta = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido, riprova.");
                    continue;
                }

                try {
                    switch (scelta) {
                        case 1 -> {
                            System.out.print("Numero tavolo: ");
                            int numero = leggiIntero(scanner);
                            System.out.print("Numero posti: ");
                            int posti = leggiIntero(scanner);

                            boolean ok = ristorante.aggiungiTavolo(numero, posti);
                            if (ok) {
                                System.out.println("Tavolo aggiunto correttamente.");
                            } else {
                                System.out.println("Esiste già un tavolo con questo numero.");
                            }
                        }

                        case 2 -> {
                            System.out.print("Nome cliente: ");
                            String nome = scanner.nextLine();
                            System.out.print("In quanti siete? ");
                            int persone = leggiIntero(scanner);

                            int risultato = ristorante.prenotaTavoloAutomatico(nome, persone);

                            if (risultato > 0) {
                                System.out.println("Prenotazione effettuata con successo!");
                                System.out.println("Tavolo assegnato: " + risultato);
                            } else if (risultato == -2) {
                                System.out.println("ERRORE: Il numero di persone (" + persone + ") supera la capienza massima del ristorante.");
                                System.out.println("Contattare il gestore per prenotazioni speciali.");
                            } else {
                                System.out.println("Impossibile prenotare: nessun tavolo disponibile per " + persone + " persone.");
                            }
                        }

                        case 3 -> {
                            System.out.print("Numero tavolo da liberare: ");
                            int numero = leggiIntero(scanner);

                            boolean ok = ristorante.liberaTavolo(numero);
                            if (ok) {
                                System.out.println("Tavolo liberato correttamente.");
                            } else {
                                System.out.println("Impossibile liberare: tavolo inesistente o già libero.");
                            }
                        }

                        case 4 -> ristorante.stampaTavoliLiberi();

                        case 5 -> ristorante.stampaTavoliOccupati();

                        case 0 -> {
                            finito = true;
                            System.out.println("Uscita dal programma. A presto!");
                        }

                        default -> System.out.println("Scelta non valida, riprova.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Dati non validi: " + e.getMessage());
                }
            }
        }
    }

    private static int leggiIntero(Scanner scanner) {
        String valore = scanner.nextLine().trim();
        try {
            return Integer.parseInt(valore);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("è richiesto un numero intero", e);
        }
    }
}
