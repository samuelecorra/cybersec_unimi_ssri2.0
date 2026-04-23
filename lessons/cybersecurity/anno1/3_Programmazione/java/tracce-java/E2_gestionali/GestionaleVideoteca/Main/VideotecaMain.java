package E2_gestionali.GestionaleVideoteca.Main;

import E2_gestionali.GestionaleVideoteca.Classi.*;


import java.util.*;

public class VideotecaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Videoteca videoteca = new Videoteca();
        System.out.println("Benvenuto nella Videoteca");
        System.out.println("Comandi disponibili:");
        System.out.println("* ADDFILM <id>,<titolo>,<genere>");
        System.out.println("* ADDCLIENTE <id>,<nome>");
        System.out.println("* NOLEGGIA <idFilm>,<idCliente>");
        System.out.println("* RESTITUISCI <idFilm>,<idCliente>");
        System.out.println("* END (per terminare)");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] parti = input.split(" ", 2);
            String comando = parti[0];

            switch (comando) {
                case "ADDFILM":
                    String[] filmDettagli = parti[1].split(",");
                    int idFilm = Integer.parseInt(filmDettagli[0].trim());
                    String titolo = filmDettagli[1].trim();
                    String genere = filmDettagli[2].trim();
                    videoteca.aggiungiFilm(new Film(idFilm, 1, titolo, genere));
                    break;
                case "ADDCLIENTE":
                    String[] clienteDettagli = parti[1].split(",");
                    int idCliente = Integer.parseInt(clienteDettagli[0].trim());
                    String nome = clienteDettagli[1].trim();
                    videoteca.registraCliente(new Cliente(idCliente, nome));
                    System.out.println("Cliente registrato: " + nome);
                    break;
                case "NOLEGGIA":
                    String[] noleggiaDettagli = parti[1].split(",");
                    int idFilmNoleggio = Integer.parseInt(noleggiaDettagli[0].trim());
                    int idClienteNoleggio = Integer.parseInt(noleggiaDettagli[1].trim());
                    if (videoteca.noleggiaFilm(idFilmNoleggio, idClienteNoleggio)) {
                        System.out.println("Noleggio effettuato (Film ID: " + idFilmNoleggio + ", Cliente ID: " + idClienteNoleggio + ")");
                    } else {
                        System.out.println("Impossibile noleggiare il film (Film ID: " + idFilmNoleggio + ", Cliente ID: " + idClienteNoleggio + ")");
                    }
                    break;
                case "RESTITUISCI":
                    String[] restituisciDettagli = parti[1].split(",");
                    int idFilmRestituzione = Integer.parseInt(restituisciDettagli[0].trim());
                    int idClienteRestituzione = Integer.parseInt(restituisciDettagli[1].trim());
                    if (videoteca.restituisciFilm(idFilmRestituzione, idClienteRestituzione)) {
                        System.out.println("Restituzione effettuata (Film ID: " + idFilmRestituzione + ", Cliente ID: " + idClienteRestituzione + ")");
                    } else {
                        System.out.println("Impossibile restituire il film (Film ID: " + idFilmRestituzione + ", Cliente ID: " + idClienteRestituzione + ")");
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

