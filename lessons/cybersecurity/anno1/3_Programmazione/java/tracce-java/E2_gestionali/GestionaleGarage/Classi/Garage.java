package E2_gestionali.GestionaleGarage.Classi;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    // ATTRIBUTI
    private final List<Auto> parcheggio; // final perché non voglio cambiare il riferimento a questa lista

    // COSTRUTTORE
    public Garage() {
        this.parcheggio = new ArrayList<>();
    }

    // METODI
    // Ritorna 0 se ok, -1 se già presente o argomento nullo
    public int aggiungiAuto(Auto autoDaInserire) {
        if (autoDaInserire == null) return -1;

        for (Auto a : parcheggio) { // per ogni auto già presente enhanced for
            if (a.getTarga().equals(autoDaInserire.getTarga())) {
                return -1; // auto con stessa targa già presente
            }
        }
        parcheggio.add(autoDaInserire);
        return 0;
    }

    // Ritorna 0 se rimossa, -1 se non trovata o argomento nullo
    public int rimuoviAuto(Auto autoDaRimuovere) {
        if (autoDaRimuovere == null) return -1;

        // uso ciclo a indici per poter rimuovere in sicurezza
        for (int i = 0; i < parcheggio.size(); i++) { // per ogni auto già presente
            Auto a = parcheggio.get(i); // prendo l'auto alla posizione i
            if (a.getTarga().equals(autoDaRimuovere.getTarga())) { // e confronto la targa
                parcheggio.remove(i);
                return 0;
            }
        }
        return -1;
    }

    // Stampa numerata delle auto presenti
    public void visualizzaAuto() {
        System.out.println("Auto attualmente nel garage:");
        if (parcheggio.isEmpty()) {
            System.out.println("(nessuna)");
            return;
        }
        int i = 1;
        for (Auto a : parcheggio) {
            System.out.println(i + ". " + a);
            i++;
        }
    }

    // Utility comoda per test o log
    public int numeroAuto() {
        return parcheggio.size();
    }
}
