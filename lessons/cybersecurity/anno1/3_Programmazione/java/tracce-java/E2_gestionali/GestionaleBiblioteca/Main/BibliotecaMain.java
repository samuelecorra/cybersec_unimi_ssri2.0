package E2_gestionali.GestionaleBiblioteca.Main;

import E2_gestionali.GestionaleBiblioteca.Classi.*;
import java.util.*;

public class BibliotecaMain {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        System.out.println("Benvenuto nella Biblioteca");
        System.out.println("Comandi disponibili:");
        System.out.println("* ADDLIBRO <id>,<titolo>,<autore>");
        System.out.println("* ADDUTENTE <id>,<nome>");
        System.out.println("* PRESTA <idLibro>,<idUtente>");
        System.out.println("* RESTITUISCI <idLibro>,<idUtente>");
        System.out.println("* END (per terminare)");

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }
                String[] parti = input.split("\\s+", 2);
                String comando = parti[0].toUpperCase(Locale.ROOT);

                try {
                    switch (comando) {
                case "ADDLIBRO":
                    String[] libroDettagli = leggiDettagli(parti, 3, "ADDLIBRO <id>,<titolo>,<autore>");
                    int idLibro = Integer.parseInt(libroDettagli[0].trim());
                    String titolo = libroDettagli[1].trim();
                    String autore = libroDettagli[2].trim();
                    if (biblioteca.aggiungiLibro(new Libro(idLibro, titolo, autore))) {
                        System.out.println("Libro aggiunto: " + titolo + " (" + autore + ")");
                    } else {
                        System.out.println("ID libro già registrato: " + idLibro);
                    }
                    break;

                case "ADDUTENTE":
                    String[] utenteDettagli = leggiDettagli(parti, 2, "ADDUTENTE <id>,<nome>");
                    int idUtente = Integer.parseInt(utenteDettagli[0].trim());
                    String nome = utenteDettagli[1].trim();
                    if (biblioteca.registraUtente(new Utente(idUtente, nome))) {
                        System.out.println("Utente registrato: " + nome);
                    } else {
                        System.out.println("ID utente già registrato: " + idUtente);
                    }
                    break;

                case "PRESTA":
                    String[] prestaDettagli = leggiDettagli(parti, 2, "PRESTA <idLibro>,<idUtente>");
                    int idLibroPrestito = Integer.parseInt(prestaDettagli[0].trim());
                    int idUtentePrestito = Integer.parseInt(prestaDettagli[1].trim());
                    if (biblioteca.prestaLibro(idLibroPrestito, idUtentePrestito)) {
                        System.out.println("Prestito effettuato");
                    } else {
                        System.out.println("Impossibile prestare il libro");
                    }
                    break;

                case "RESTITUISCI":
                    String[] restituisciDettagli = leggiDettagli(parti, 2, "RESTITUISCI <idLibro>,<idUtente>");
                    int idLibroRestituzione = Integer.parseInt(restituisciDettagli[0].trim());
                    int idUtenteRestituzione = Integer.parseInt(restituisciDettagli[1].trim());
                    if (biblioteca.restituisciLibro(idLibroRestituzione, idUtenteRestituzione)) {
                        System.out.println("Restituzione effettuata");
                    } else {
                        System.out.println("Impossibile restituire il libro");
                    }
                    break;

                case "END":
                    return;

                default:
                    System.out.println("Comando non riconosciuto.");
                    break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Input non valido: " + e.getMessage());
                }
            }
        }
    }

    private static String[] leggiDettagli(String[] parti, int quanti, String uso) {
        if (parti.length < 2) {
            throw new IllegalArgumentException("sintassi: " + uso);
        }
        String[] dettagli = parti[1].split(",", -1);
        if (dettagli.length != quanti) {
            throw new IllegalArgumentException("sintassi: " + uso);
        }
        return dettagli;
    }
}
