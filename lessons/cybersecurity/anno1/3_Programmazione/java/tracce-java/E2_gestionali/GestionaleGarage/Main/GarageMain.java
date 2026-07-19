package E2_gestionali.GestionaleGarage.Main;

import E2_gestionali.GestionaleGarage.Classi.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GarageMain {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Inserisci il numero di persone che vuoi registrare (0-100): ");
            int n;
            while (true) {
                String line = scanner.nextLine().trim();
                try {
                    n = Integer.parseInt(line);
                    if (n < 0 || n > 100) {
                        System.out.print("Inserisci un numero tra 0 e 100: ");
                    } else break;
                } catch (NumberFormatException e) {
                    System.out.print("Valore non valido. Riprova: ");
                }
            }

            List<Guidatore> guidatori = new ArrayList<>();
            Garage garage = new Garage();

            int targa = 100; // targa iniziale

            for (int i = 0; i < n; i++) {
                String nomeECognome;
                while (true) {
                    nomeECognome = leggiTesto(scanner, "Nome e cognome del guidatore " + (i + 1) + ": ");
                    String nomeDaVerificare = nomeECognome;
                    boolean duplicato = guidatori.stream()
                            .anyMatch(g -> g.getNomeECognome().equalsIgnoreCase(nomeDaVerificare));
                    if (!duplicato) break;
                    System.out.println("Nominativo già registrato: usane uno distinguibile.");
                }

                String marca = leggiTesto(scanner, "Marca dell'auto di " + nomeECognome + ": ");
                String modello = leggiTesto(scanner, "Modello della " + marca + " di " + nomeECognome + ": ");

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

        }
    }

    private static String leggiTesto(Scanner scanner, String messaggio) {
        while (true) {
            System.out.print(messaggio);
            String valore = scanner.nextLine().trim();
            if (!valore.isEmpty()) {
                return valore;
            }
            System.out.println("Il valore non può essere vuoto.");
        }
    }
}
