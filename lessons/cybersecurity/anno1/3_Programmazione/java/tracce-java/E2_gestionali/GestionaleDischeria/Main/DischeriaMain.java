package E2_gestionali.GestionaleDischeria.Main;

import E2_gestionali.GestionaleDischeria.Classi.*;
import java.util.*;

public class DischeriaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Discheria discheria = new Discheria();

        System.out.println("Benvenuto nella Discheria");
        System.out.println("Comandi disponibili:");
        System.out.println("* ADD-DISCO <id>,<titolo>,<artista>");
        System.out.println("* ADD-CLIENTE <id>,<nome>");
        System.out.println("* PRENDI <idDisco>,<idCliente>");
        System.out.println("* RESTITUISCI <idDisco>,<idCliente>");
        System.out.println("* END (per terminare)");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] parti = input.split(" ", 2);
            String comando = parti[0];
            switch (comando) {
                case "ADD-DISCO":
                    String[] discoDettagli = parti[1].split(",");
                    int idDisco = Integer.parseInt(discoDettagli[0].trim());
                    String titolo = discoDettagli[1].trim();
                    String artista = discoDettagli[2].trim();
                    discheria.aggiungiDisco(new Disco(idDisco, titolo, artista));
                    System.out.println("Disco aggiunto: " + titolo + " (" + artista + ")");
                    break;

                case "ADD-CLIENTE":
                    String[] clienteDettagli = parti[1].split(",");
                    int idCliente = Integer.parseInt(clienteDettagli[0].trim());
                    String nome = clienteDettagli[1].trim();
                    discheria.registraCliente(new Cliente(idCliente, nome));
                    System.out.println("Cliente registrato: " + nome);
                    break;

                case "PRENDI":
                    String[] prendiDettagli = parti[1].split(",");
                    int idDiscoPrendi = Integer.parseInt(prendiDettagli[0].trim());
                    int idClientePrendi = Integer.parseInt(prendiDettagli[1].trim());
                    if (discheria.prendiDisco(idDiscoPrendi, idClientePrendi)) {
                        System.out.println("Disco prenotato: " + idDiscoPrendi + " dal cliente: " + idClientePrendi);
                    } else {
                        System.out.println("Impossibile prenotare il disco");
                    }
                    break;

                case "RESTITUISCI":
                    String[] restituisciDettagli = parti[1].split(",");
                    int idDiscoRestituisci = Integer.parseInt(restituisciDettagli[0].trim());
                    int idClienteRestituisci = Integer.parseInt(restituisciDettagli[1].trim());
                    if (discheria.restituisciDisco(idDiscoRestituisci, idClienteRestituisci)) {
                        System.out.println("Disco restituito: " + idDiscoRestituisci + " dal cliente: " + idClienteRestituisci);
                    } else {
                        System.out.println("Impossibile restituire il disco");
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
