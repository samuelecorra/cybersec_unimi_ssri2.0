package E2_gestionali.GestionaleDischeria.Main;

import E2_gestionali.GestionaleDischeria.Classi.*;
import java.util.*;

public class DischeriaMain {
    public static void main(String[] args) {
        Discheria discheria = new Discheria();

        System.out.println("Benvenuto nella Discheria");
        System.out.println("Comandi disponibili:");
        System.out.println("* ADD-DISCO <id>,<titolo>,<artista>");
        System.out.println("* ADD-CLIENTE <id>,<nome>");
        System.out.println("* PRENDI <idDisco>,<idCliente>");
        System.out.println("* RESTITUISCI <idDisco>,<idCliente>");
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
                case "ADD-DISCO":
                    String[] discoDettagli = leggiDettagli(parti, 3, "ADD-DISCO <id>,<titolo>,<artista>");
                    int idDisco = Integer.parseInt(discoDettagli[0].trim());
                    String titolo = discoDettagli[1].trim();
                    String artista = discoDettagli[2].trim();
                    if (discheria.aggiungiDisco(new Disco(idDisco, titolo, artista))) {
                        System.out.println("Disco aggiunto: " + titolo + " (" + artista + ")");
                    } else {
                        System.out.println("ID disco già registrato: " + idDisco);
                    }
                    break;

                case "ADD-CLIENTE":
                    String[] clienteDettagli = leggiDettagli(parti, 2, "ADD-CLIENTE <id>,<nome>");
                    int idCliente = Integer.parseInt(clienteDettagli[0].trim());
                    String nome = clienteDettagli[1].trim();
                    if (discheria.registraCliente(new Cliente(idCliente, nome))) {
                        System.out.println("Cliente registrato: " + nome);
                    } else {
                        System.out.println("ID cliente già registrato: " + idCliente);
                    }
                    break;

                case "PRENDI":
                    String[] prendiDettagli = leggiDettagli(parti, 2, "PRENDI <idDisco>,<idCliente>");
                    int idDiscoPrendi = Integer.parseInt(prendiDettagli[0].trim());
                    int idClientePrendi = Integer.parseInt(prendiDettagli[1].trim());
                    if (discheria.prendiDisco(idDiscoPrendi, idClientePrendi)) {
                        System.out.println("Disco prenotato: " + idDiscoPrendi + " dal cliente: " + idClientePrendi);
                    } else {
                        System.out.println("Impossibile prenotare il disco");
                    }
                    break;

                case "RESTITUISCI":
                    String[] restituisciDettagli = leggiDettagli(parti, 2, "RESTITUISCI <idDisco>,<idCliente>");
                    int idDiscoRestituisci = Integer.parseInt(restituisciDettagli[0].trim());
                    int idClienteRestituisci = Integer.parseInt(restituisciDettagli[1].trim());
                    if (discheria.restituisciDisco(idDiscoRestituisci, idClienteRestituisci)) {
                        System.out.println("Disco restituito: " + idDiscoRestituisci + " dal cliente: " + idClienteRestituisci);
                    } else {
                        System.out.println("Impossibile restituire il disco");
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
