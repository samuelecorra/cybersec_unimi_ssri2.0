package E2_gestionali.GestionaleVideoteca.Main;

import E2_gestionali.GestionaleVideoteca.Classi.*;


import java.util.*;

public class VideotecaMain {
    public static void main(String[] args) {
        Videoteca videoteca = new Videoteca();
        System.out.println("Benvenuto nella Videoteca");
        System.out.println("Comandi disponibili:");
        System.out.println("* ADDFILM <id>,<titolo>,<genere>");
        System.out.println("* ADDCLIENTE <id>,<nome>");
        System.out.println("* NOLEGGIA <idFilm>,<idCliente>");
        System.out.println("* RESTITUISCI <idFilm>,<idCliente>");
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
                case "ADDFILM":
                    String[] filmDettagli = leggiDettagli(parti, 3, "ADDFILM <id>,<titolo>,<genere>");
                    int idFilm = Integer.parseInt(filmDettagli[0].trim());
                    String titolo = filmDettagli[1].trim();
                    String genere = filmDettagli[2].trim();
                    if (videoteca.aggiungiFilm(new Film(idFilm, 1, titolo, genere))) {
                        System.out.println("Film/copia registrato: " + titolo + " (" + genere + ")");
                    } else {
                        System.out.println("L'ID è già associato a titolo o genere differenti.");
                    }
                    break;
                case "ADDCLIENTE":
                    String[] clienteDettagli = leggiDettagli(parti, 2, "ADDCLIENTE <id>,<nome>");
                    int idCliente = Integer.parseInt(clienteDettagli[0].trim());
                    String nome = clienteDettagli[1].trim();
                    if (videoteca.registraCliente(new Cliente(idCliente, nome))) {
                        System.out.println("Cliente registrato: " + nome);
                    } else {
                        System.out.println("ID cliente già registrato: " + idCliente);
                    }
                    break;
                case "NOLEGGIA":
                    String[] noleggiaDettagli = leggiDettagli(parti, 2, "NOLEGGIA <idFilm>,<idCliente>");
                    int idFilmNoleggio = Integer.parseInt(noleggiaDettagli[0].trim());
                    int idClienteNoleggio = Integer.parseInt(noleggiaDettagli[1].trim());
                    if (videoteca.noleggiaFilm(idFilmNoleggio, idClienteNoleggio)) {
                        System.out.println("Noleggio effettuato (Film ID: " + idFilmNoleggio + ", Cliente ID: " + idClienteNoleggio + ")");
                    } else {
                        System.out.println("Impossibile noleggiare il film (Film ID: " + idFilmNoleggio + ", Cliente ID: " + idClienteNoleggio + ")");
                    }
                    break;
                case "RESTITUISCI":
                    String[] restituisciDettagli = leggiDettagli(parti, 2, "RESTITUISCI <idFilm>,<idCliente>");
                    int idFilmRestituzione = Integer.parseInt(restituisciDettagli[0].trim());
                    int idClienteRestituzione = Integer.parseInt(restituisciDettagli[1].trim());
                    if (videoteca.restituisciFilm(idFilmRestituzione, idClienteRestituzione)) {
                        System.out.println("Restituzione effettuata (Film ID: " + idFilmRestituzione + ", Cliente ID: " + idClienteRestituzione + ")");
                    } else {
                        System.out.println("Impossibile restituire il film (Film ID: " + idFilmRestituzione + ", Cliente ID: " + idClienteRestituzione + ")");
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
