package E2_gestionali.GestionaleVideotecaV2.Main;

import E2_gestionali.GestionaleVideotecaV2.Classi.Cliente;
import E2_gestionali.GestionaleVideotecaV2.Classi.Film;
import E2_gestionali.GestionaleVideotecaV2.Classi.Videoteca;

import java.util.Scanner;

public class VideotecaMain {

    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);        // Servirà user input

        Videoteca videoteca = new Videoteca();          // Creiamo la videoteca

        // Per facilitare l'utente, inizieremo con hardcodare alcuni film e clienti
        videoteca.aggiungiAlCatalogo(new Film(1, "Inception", "Sci-Fi"));
        videoteca.aggiungiAlCatalogo(new Film(2, "The Godfather", "Mobster"));
        videoteca.aggiungiAlCatalogo(new Film(3, "The Dark Knight", "Action"));
        videoteca.aggiungiAlCatalogo(new Film(4, "The Godfather", "Mobster"));
        videoteca.aggiungiAlCatalogo(new Film(5, "Back to the Future", "Sci-Fi"));
        videoteca.aggiungiAlCatalogo(new Film(6, "Ready Player One", "Cyberpunk"));
        videoteca.aggiungiAlCatalogo(new Film(7, "The Last of Us", "Horror"));
        videoteca.aggiungiAlCatalogo(new Film(8, "The Lion King", "Animation"));
        videoteca.aggiungiAlCatalogo(new Film(9, "GoodFellas", "Mobster"));
        videoteca.aggiungiAlCatalogo(new Film(10, "Back to the Future", "Sci-Fi"));

        videoteca.registraCliente(new Cliente(1, "Cyber Prodigy"));
        videoteca.registraCliente(new Cliente(2, "Network Jammer"));
        videoteca.registraCliente(new Cliente(3, "Pixel Warrior"));
        videoteca.registraCliente(new Cliente(4, "Glitch Master"));
        videoteca.registraCliente(new Cliente(5, "Quantum Coder"));


        System.out.println("===== GESTIONALE VIDEOTECA =====");
        stampaMenu(); // Stampiamo il menu iniziale

        while (true) {      // Apriamo il ciclo principale di lettura comandi
            System.out.print("\nCOMANDO E ARGOMENTI > "); // Prompt per l'input
            String input = scanner.nextLine().trim(); // Leggiamo l'input e rimuoviamo spazi bianchi iniziali/finali

            if (input.isEmpty()) {
                continue; // Ignora input vuoti ritornando all'inizio del ciclo e quindi riproponendo il prompt
            }

            // Se l'input non è vuoto, lo dividiamo in comando + argomenti
            String[] parti = input.split("\\s+", 2); // Dividiamo in massimo 2 parti: comando e resto,
            // Tagliamo su uno o pi spazi bianchi (\\s+) e limitiamo a 2 parti (2) come richiesto

            String comando = parti[0].toUpperCase(); // Prendiamo il comando (prima parte) e lo portiamo in maiuscolo per compatibilità

            // Prima di guardare l'argomento
            if (comando.equals("END")) {
                System.out.println("Chiusura della videoteca. Arrivederci!");
                break;
            }

            if (comando.equals("VIEW")) {
                System.out.println(videoteca);
                stampaMenu();
                continue;
            }

            try {
                switch (comando) {

                    case "ADDFILM":
                        gestisciAggiuntaFilm(videoteca, parti);
                        break;

                    case "ADDCLIENTE":
                        gestisciAggiuntaCliente(videoteca, parti);
                        break;

                    case "NOLEGGIA":
                        gestisciNoleggio(videoteca, parti);
                        break;

                    case "RESTITUISCI":
                        gestisciRestituzione(videoteca, parti);
                        break;

                    default:
                        System.out.println("Comando non riconosciuto. Digita HELP per vedere i comandi disponibili.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Errore nel processamento del comando: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // -------- Funzioni di supporto per il main -------- //

    private static void stampaMenu() {
        System.out.println("Comandi disponibili:");
        System.out.println("  VIEW - Mostra lo stato attuale della videoteca");
        System.out.println("  ADDFILM <id>,<titolo>,<genere>");
        System.out.println("  ADDCLIENTE <id>,<nome>");
        System.out.println("  NOLEGGIA <idFilm>,<idCliente>");
        System.out.println("  RESTITUISCI <idFilm>,<idCliente>");
        System.out.println("  END");
    }

    private static void gestisciAggiuntaFilm(Videoteca videoteca, String[] parti) {
        if (parti.length < 2) {
            System.out.println("Uso corretto: ADDFILM <id>,<titolo>,<genere>");
            return;
        }

        String[] filmDettagli = parti[1].split(",");
        if (filmDettagli.length != 3) {
            System.out.println("Errore: devi fornire esattamente 3 campi: <id>,<titolo>,<genere>");
            return;
        }

        try {
            int idFilm = Integer.parseInt(filmDettagli[0].trim());
            String titolo = filmDettagli[1].trim();
            String genere = filmDettagli[2].trim();

            Film film = new Film(idFilm, titolo, genere);
            videoteca.aggiungiAlCatalogo(film);
        } catch (NumberFormatException e) {
            System.out.println("Errore: l'id del film deve essere un intero.");
        }
    }

    private static void gestisciAggiuntaCliente(Videoteca videoteca, String[] parti) {
        if (parti.length < 2) {
            System.out.println("Uso corretto: ADDCLIENTE <id>,<nome>");
            return;
        }

        String[] clienteDettagli = parti[1].split(",");
        if (clienteDettagli.length != 2) {
            System.out.println("Errore: devi fornire esattamente 2 campi: <id>,<nome>");
            return;
        }

        try {
            int idCliente = Integer.parseInt(clienteDettagli[0].trim());
            String nome = clienteDettagli[1].trim();

            Cliente cliente = new Cliente(idCliente, nome);
            videoteca.registraCliente(cliente);
            System.out.println("Cliente registrato: " + nome + " (ID: " + idCliente + ")");
        } catch (NumberFormatException e) {
            System.out.println("Errore: l'id del cliente deve essere un intero.");
        }
    }

    private static void gestisciNoleggio(Videoteca videoteca, String[] parti) {
        if (parti.length < 2) {
            System.out.println("Uso corretto: NOLEGGIA <idFilm>,<idCliente>");
            return;
        }

        String[] noleggiaDettagli = parti[1].split(",");
        if (noleggiaDettagli.length != 2) {
            System.out.println("Errore: devi fornire esattamente 2 campi: <idFilm>,<idCliente>");
            return;
        }

        try {
            int idFilm = Integer.parseInt(noleggiaDettagli[0].trim());
            int idCliente = Integer.parseInt(noleggiaDettagli[1].trim());

            boolean ok = videoteca.noleggiaFilm(idFilm, idCliente);
            if (ok) {
                System.out.println("Noleggio registrato (Film ID: " + idFilm + ", Cliente ID: " + idCliente + ").");
            } else {
                System.out.println("Impossibile completare il noleggio.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore: idFilm e idCliente devono essere interi.");
        }
    }

    private static void gestisciRestituzione(Videoteca videoteca, String[] parti) {
        if (parti.length < 2) {
            System.out.println("Uso corretto: RESTITUISCI <idFilm>,<idCliente>");
            return;
        }

        String[] restituisciDettagli = parti[1].split(",");
        if (restituisciDettagli.length != 2) {
            System.out.println("Errore: devi fornire esattamente 2 campi: <idFilm>,<idCliente>");
            return;
        }

        try {
            int idFilm = Integer.parseInt(restituisciDettagli[0].trim());
            int idCliente = Integer.parseInt(restituisciDettagli[1].trim());

            boolean ok = videoteca.restituisciFilm(idFilm, idCliente);
            if (ok) {
                System.out.println("Restituzione registrata (Film ID: " + idFilm + ", Cliente ID: " + idCliente + ").");
            } else {
                System.out.println("Impossibile completare la restituzione.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore: idFilm e idCliente devono essere interi.");
        }
    }
}
