package E4_avanzati.RegistroStudenti.Main;

import E4_avanzati.RegistroStudenti.Classi.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainRegistroStudenti {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RegistroStudenti registro = new RegistroStudenti();

        boolean esci = false;

        while (!esci) {
            stampaMenu();
            System.out.print("Scelta: ");
            String input = scanner.nextLine();

            int scelta;
            try {
                scelta = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Inserire un numero valido.\n");
                continue;
            }

            switch (scelta) {
                case 1 -> aggiungiStudente(scanner, registro);
                case 2 -> aggiungiVoto(scanner, registro);
                case 3 -> mostraMediaStudente(scanner, registro);
                case 4 -> rimuoviStudente(scanner, registro);
                case 5 -> registro.stampaRegistro();
                case 6 -> cercaStudente(scanner, registro);
                case 0 -> {
                    System.out.println("Uscita dal programma. Ciao!");
                    esci = true;
                }
                default -> System.out.println("Scelta non valida.\n");
            }
        }

        scanner.close();
    }

    private static void stampaMenu() {
        System.out.println();
        System.out.println("====== REGISTRO STUDENTI ======");
        System.out.println("1) Aggiungi studente");
        System.out.println("2) Aggiungi voto a studente");
        System.out.println("3) Mostra media di uno studente");
        System.out.println("4) Rimuovi studente");
        System.out.println("5) Stampa registro completo");
        System.out.println("6) Cerca studente per matricola");
        System.out.println("0) Esci");
        System.out.println("================================");
    }

    private static void aggiungiStudente(Scanner scanner, RegistroStudenti registro) {
        try {
            System.out.print("Inserisci matricola (numero intero): ");
            int matricola = Integer.parseInt(scanner.nextLine());

            System.out.print("Inserisci nome studente: ");
            String nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Il nome non può essere vuoto.\n");
                return;
            }

            boolean inserito = registro.aggiungiStudente(matricola, nome);
            if (inserito) {
                System.out.println("Studente aggiunto correttamente.\n");
            } else {
                System.out.println("Esiste già uno studente con questa matricola.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Matricola non valida.\n");
        }
    }

    private static void aggiungiVoto(Scanner scanner, RegistroStudenti registro) {
        try {
            System.out.print("Inserisci matricola studente: ");
            int matricola = Integer.parseInt(scanner.nextLine());

            System.out.print("Inserisci voto (0-30): ");
            int voto = Integer.parseInt(scanner.nextLine());

            if (voto < 0 || voto > 31) {
                System.out.println("Voto non valido. Inserire un valore tra 0 e 30.\n");
                return;
            }

            boolean ok = registro.aggiungiVotoAStudente(matricola, voto);
            if (ok) {
                System.out.println("Voto aggiunto correttamente.\n");
            } else {
                System.out.println("Studente non trovato.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido.\n");
        }
    }

    private static void mostraMediaStudente(Scanner scanner, RegistroStudenti registro) {
        try {
            System.out.print("Inserisci matricola studente: ");
            int matricola = Integer.parseInt(scanner.nextLine());

            Studente s = registro.cercaStudente(matricola);
            if (s == null) {
                System.out.println("Studente non trovato.\n");
                return;
            }

            double media = s.calcolaMedia();
            if (s.getVoti().isEmpty()) {
                System.out.println("Lo studente " + s.getNome() + " non ha ancora voti registrati.\n");
            } else {
                System.out.printf("Media voti di %s (matricola %d): %.2f%n%n",
                        s.getNome(), s.getMatricola(), media);
            }
        } catch (NumberFormatException e) {
            System.out.println("Matricola non valida.\n");
        }
    }

    private static void rimuoviStudente(Scanner scanner, RegistroStudenti registro) {
        try {
            System.out.print("Inserisci matricola dello studente da rimuovere: ");
            int matricola = Integer.parseInt(scanner.nextLine());

            boolean rimosso = registro.rimuoviStudente(matricola);
            if (rimosso) {
                System.out.println("Studente rimosso correttamente.\n");
            } else {
                System.out.println("Studente non trovato.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Matricola non valida.\n");
        }
    }

    private static void cercaStudente(Scanner scanner, RegistroStudenti registro) {
        try {
            System.out.print("Inserisci matricola studente: ");
            int matricola = Integer.parseInt(scanner.nextLine());

            Studente s = registro.cercaStudente(matricola);
            if (s == null) {
                System.out.println("Studente non trovato.\n");
            } else {
                System.out.println("Dati studente:");
                System.out.println(s);
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("Matricola non valida.\n");
        }
    }
}

