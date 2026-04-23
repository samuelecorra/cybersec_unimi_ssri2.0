package E2_gestionali.GestionaleGarage.Main;

import E2_gestionali.GestionaleGarage.Classi.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GarageMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print(
                "Inserisci il numero di persone che vuoi registrare: "
        );
        int n;
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                n = Integer.parseInt(line);
                if (n < 0) {
                    System.out.print("Inserisci un numero >= 0: ");
                } else break;
            } catch (NumberFormatException e) {
                System.out.print("Valore non valido. Riprova: ");
            }
        }

        List<Guidatore> guidatori = new ArrayList<>();
        Garage garage = new Garage();

        int targa = 100; // targa iniziale

        for (int i = 0; i < n; i++) {
            System.out.print("Nome e cognome del guidatore " + (i + 1) + ": ");
            String nomeECognome = scanner.nextLine().trim();

            System.out.print("Marca dell'auto di " + nomeECognome + ": ");
            String marca = scanner.nextLine().trim();

            System.out.print("Modello della " + marca + " di " + nomeECognome + ": ");
            String modello = scanner.nextLine().trim();

            Auto auto = new Auto(marca, modello, String.valueOf(targa++));
            Guidatore guidatore = new Guidatore(nomeECognome, auto);
            guidatori.add(guidatore);

            System.out.println("Registrato: " + guidatore);
        }

        System.out.println("\n========================================");
        System.out.println("Menù interattivo gestione garage");
        System.out.println("----------------------------------------");
        System.out.println("Comandi:");
        System.out.println("  E <Nome Cognome>  → entra (parcheggia)");
        System.out.println("  U <Nome Cognome>  → esce (rimuove)");
        System.out.println("  L                 → lista auto in garage");
        System.out.println("  F                 → termina");
        System.out.println("========================================\n");

        while (true) {
            System.out.print("Inserisci un comando: ");
            String comando = scanner.nextLine().trim();
            if (comando.isEmpty()) continue;

            if (comando.equalsIgnoreCase("F")) {
                System.out.println("Programma terminato.");
                break;
            }

            if (comando.equalsIgnoreCase("L")) {
                garage.visualizzaAuto();
                continue;
            }

            // Comandi con argomento (E/U + Nome Cognome)
            String[] parti = comando.split(" ", 2);
            if (parti.length < 2) {
                System.out.println("Formato non valido. Usa: E/U <Nome Cognome>.");
                continue;
            }

            String azione = parti[0].trim();
            String nominativo = parti[1].trim();

            // Cerca il guidatore registrato
            Guidatore personaTrovata = null;
            for (Guidatore g : guidatori) {
                if (g.getNomeECognome().equalsIgnoreCase(nominativo)) {
                    personaTrovata = g;
                    break;
                }
            }

            if (personaTrovata == null) {
                System.out.println("Persona non trovata tra i registrati.");
                continue;
            }

            if (azione.equalsIgnoreCase("E")) {
                int ret = personaTrovata.parcheggia(garage);
                if (ret == 0) {
                    System.out.println(nominativo + " entra e parcheggia.");
                } else {
                    System.out.println("Errore: l'auto di " + nominativo + " è già nel garage.");
                }
            } else if (azione.equalsIgnoreCase("U")) {
                int ret = personaTrovata.rimuoviAuto(garage);
                if (ret == 0) {
                    System.out.println(nominativo + " esce dal garage.");
                } else {
                    System.out.println("Errore: l'auto di " + nominativo + " non è nel garage.");
                }
            } else {
                System.out.println("Comando non riconosciuto. Usa E, U, L o F.");
            }
        }

        // Stato finale
        System.out.println("\nStato finale del garage:");
        garage.visualizzaAuto();

        scanner.close();
    }
}
