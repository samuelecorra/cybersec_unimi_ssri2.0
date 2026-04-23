package E4_avanzati.GestionePrenotazioni.Classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ristorante {

    // Tutti i tavoli esistenti (chiave = numero tavolo)
    private final Map<Integer, Tavolo> tavoli = new HashMap<>();

    // Prenotazioni attive: tavolo -> prenotazione
    private final Map<Integer, Prenotazione> prenotazioni = new HashMap<>();

    // SCELTA 1
    /**
     * Aggiunge un nuovo tavolo al ristorante.
     * Ritorna true se il tavolo è stato aggiunto, false se esiste già un tavolo con quel numero.
     */
    public boolean aggiungiTavolo(int numeroTavolo, int posti) {
        if (tavoli.containsKey(numeroTavolo)) {
            return false; // già presente
        }
        Tavolo t = new Tavolo(numeroTavolo, posti);
        tavoli.put(numeroTavolo, t);
        return true;
    }

    // SCELTA 2
    /**
     * Trova e prenota automaticamente un tavolo libero adatto per il numero di persone.
     * Ritorna il numero del tavolo prenotato, oppure -1 se non è stato possibile prenotare.
     * -1 = nessun tavolo disponibile o abbastanza grande
     * -2 = numero persone supera la capienza massima del ristorante
     */
    public int prenotaTavoloAutomatico(String nomeCliente, int numeroPersone) {
        // Trova la capienza massima tra tutti i tavoli
        int capienzaMassima = 0;
        for (Tavolo t : tavoli.values()) {
            if (t.getPosti() > capienzaMassima) {
                capienzaMassima = t.getPosti();
            }
        }

        // Se il numero di persone supera la capienza massima, errore specifico
        if (numeroPersone > capienzaMassima) {
            return -2;
        }

        // Cerca il primo tavolo libero adatto
        for (Tavolo t : tavoli.values()) {
            int numeroTavolo = t.getNumero();
            // Se è libero e abbastanza capiente
            if (!prenotazioni.containsKey(numeroTavolo) && t.getPosti() >= numeroPersone) {
                Prenotazione p = new Prenotazione(nomeCliente, numeroPersone);
                prenotazioni.put(numeroTavolo, p);
                return numeroTavolo; // Ritorna il numero del tavolo prenotato
            }
        }

        // Nessun tavolo libero trovato
        return -1;
    }

    // SCELTA 3
    /**
     * Libera un tavolo se è occupato.
     * Ritorna true se il tavolo è stato liberato, false se era già libero o non esiste.
     */
    public boolean liberaTavolo(int numeroTavolo) {
        if (!tavoli.containsKey(numeroTavolo)) {
            return false; // tavolo inesistente
        }
        return (prenotazioni.remove(numeroTavolo) != null);
    }

    // SCELTA 4
    /**
     * Ritorna la lista dei tavoli liberi.
     */
    public List<Tavolo> getTavoliLiberi() {
        List<Tavolo> liberi = new ArrayList<>();
        for (Tavolo t : tavoli.values()) {
            if (!prenotazioni.containsKey(t.getNumero())) {
                liberi.add(t);
            }
        }
        return liberi;
    }

    /**
     * Stampa i tavoli liberi con un minimo di formattazione.
     */
    public void stampaTavoliLiberi() {
        List<Tavolo> liberi = getTavoliLiberi();
        if (liberi.isEmpty()) {
            System.out.println("Non ci sono tavoli liberi.");
        } else {
            System.out.println("Tavoli liberi:");
            for (Tavolo t : liberi) {
                System.out.println(" - " + t);
            }
        }
    }

    // SCELTA 5
    /**
     * Ritorna la lista dei tavoli occupati.
     */
    public List<Tavolo> getTavoliOccupati() {
        List<Tavolo> occupati = new ArrayList<>();
        for (Integer numeroTavolo : prenotazioni.keySet()) {
            Tavolo t = tavoli.get(numeroTavolo);
            if (t != null) {
                occupati.add(t);
            }
        }
        return occupati;
    }

    /**
     * Stampa i tavoli occupati con le relative prenotazioni.
     */
    public void stampaTavoliOccupati() {
        List<Tavolo> occupati = getTavoliOccupati();

        if (occupati.isEmpty()) {
            System.out.println("Non ci sono tavoli occupati.");
            return;
        }

        System.out.println("Tavoli occupati:");
        for (Tavolo t : occupati) {
            Prenotazione p = prenotazioni.get(t.getNumero());
            System.out.println(" - " + t + " -> " + p);
        }
    }
}
