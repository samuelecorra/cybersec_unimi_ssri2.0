package E2_gestionali.GestionaleBiblioteca.Main;

import E2_gestionali.GestionaleBiblioteca.Classi.*;
import java.util.*;

public class BibliotecaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        System.out.println("Benvenuto nella Biblioteca");
        System.out.println("Comandi disponibili:");
        System.out.println("* ADDLIBRO <id>,<titolo>,<autore>");
        System.out.println("* ADDUTENTE <id>,<nome>");
        System.out.println("* PRESTA <idLibro>,<idUtente>");
        System.out.println("* RESTITUISCI <idLibro>,<idUtente>");
        System.out.println("* END (per terminare)");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] parti = input.split(" ", 2);
            String comando = parti[0];

            switch (comando) {
                case "ADDLIBRO":
                    String[] libroDettagli = parti[1].split(",");
                    int idLibro = Integer.parseInt(libroDettagli[0].trim());
                    String titolo = libroDettagli[1].trim();
                    String autore = libroDettagli[2].trim();
                    biblioteca.aggiungiLibro(new Libro(idLibro, titolo, autore));
                    System.out.println("Libro aggiunto: " + titolo + " (" + autore + ")");
                    break;

                case "ADDUTENTE":
                    String[] utenteDettagli = parti[1].split(",");
                    int idUtente = Integer.parseInt(utenteDettagli[0].trim());
                    String nome = utenteDettagli[1].trim();
                    biblioteca.registraUtente(new Utente(idUtente, nome));
                    System.out.println("Utente registrato: " + nome);
                    break;

                case "PRESTA":
                    String[] prestaDettagli = parti[1].split(",");
                    int idLibroPrestito = Integer.parseInt(prestaDettagli[0].trim());
                    int idUtentePrestito = Integer.parseInt(prestaDettagli[1].trim());
                    if (biblioteca.prestaLibro(idLibroPrestito, idUtentePrestito)) {
                        System.out.println("Prestito effettuato");
                    } else {
                        System.out.println("Impossibile prestare il libro");
                    }
                    break;

                case "RESTITUISCI":
                    String[] restituisciDettagli = parti[1].split(",");
                    int idLibroRestituzione = Integer.parseInt(restituisciDettagli[0].trim());
                    int idUtenteRestituzione = Integer.parseInt(restituisciDettagli[1].trim());
                    if (biblioteca.restituisciLibro(idLibroRestituzione, idUtenteRestituzione)) {
                        System.out.println("Restituzione effettuata");
                    } else {
                        System.out.println("Impossibile restituire il libro");
                    }
                    break;

                case "END":
                    scanner.close();
                    return;

                default:
                    System.out.println("Comando non riconosciuto.");
                    break;
            }
        }
        scanner.close();
    }
}