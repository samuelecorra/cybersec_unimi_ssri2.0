package E3_simulazione_giochi.Serpenti_e_Scale.Classi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partita {
    private final List<Giocatore> giocatori = new ArrayList<>();
    private final Dado dado1;
    private final Dado dado2;
    private final Tabellone tabellone;
    private final Scanner scanner;

    public Partita(List<String> nomiGiocatori) {
        if (nomiGiocatori == null || nomiGiocatori.size() < 2) {
            throw new IllegalArgumentException("Servono almeno 2 giocatori");
        }

        for (String nome : nomiGiocatori) {
            giocatori.add(new Giocatore(nome));
        }

        this.dado1 = new Dado(6);
        this.dado2 = new Dado(6);
        this.tabellone = new Tabellone();
        this.scanner = new Scanner(System.in);
    }

    public void gioca() {
        boolean finita = false;
        Giocatore vincitore = null;

        System.out.println("=== INIZIO PARTITA: SERPENTI E SCALE ===");

        while (!finita) {
            for (Giocatore g : giocatori) {

                System.out.println("\nTURNO DI: " + g.getNome());
                System.out.println("Posizione attuale: " + g.getPosizione());
                System.out.print("Premi INVIO per lanciare i due dadi...");
                scanner.nextLine();

                int tiro1 = dado1.lancia();
                int tiro2 = dado2.lancia();
                int somma = tiro1 + tiro2;

                System.out.println(g.getNome() + " ha tirato: " + tiro1 + " e " + tiro2 + " (totale: " + somma + ")");

                int posizioneAttuale = g.getPosizione();
                int nuovaPosizione = posizioneAttuale + somma;

                // Gestione "rimbalzo" oltre il 100
                if (nuovaPosizione > Tabellone.CASELLA_FINALE) {
                    int eccesso = nuovaPosizione - Tabellone.CASELLA_FINALE; // quanto hai sforato
                    nuovaPosizione = Tabellone.CASELLA_FINALE - eccesso;     // rimbalzo indietro

                    System.out.println("Hai sforato il 100! Arrivi al 100 e rimbalzi indietro fino alla casella "
                            + nuovaPosizione);
                }

                g.setPosizione(nuovaPosizione);
                System.out.println(g.getNome() + " si sposta alla casella " + nuovaPosizione);

                // Controllo serpenti/scale
                if (tabellone.haEffetto(nuovaPosizione)) {
                    int destinazione = tabellone.getDestinazione(nuovaPosizione);
                    if (destinazione > nuovaPosizione) {
                        System.out.println("Scala! Salti dalla casella " + nuovaPosizione +
                                " alla casella " + destinazione);
                    } else {
                        System.out.println("Serpente! Scivoli dalla casella " + nuovaPosizione +
                                " alla casella " + destinazione);
                    }
                    g.setPosizione(destinazione);
                }

                System.out.println("Nuova posizione di " + g.getNome() + ": " + g.getPosizione());

                // Controllo vittoria
                if (g.getPosizione() == Tabellone.CASELLA_FINALE) {
                    finita = true;
                    vincitore = g;
                    break;
                }
            }
        }

        System.out.println("\n=== PARTITA FINITA! ===");
        System.out.println("Ha vinto: " + vincitore.getNome());
    }
}

