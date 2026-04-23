package E4_avanzati.ClassificaVideogame.Classi;

import java.util.*;

public class Classifica {

    private final Map<Integer, Giocatore> giocatori = new HashMap<>();

    /**
     * Aggiunge un nuovo giocatore alla classifica.
     * Restituisce true se l'aggiunta va a buon fine, false se l'id è già presente.
     */
    public boolean aggiungiGiocatore(int id, String nickname, int punteggioIniziale) {
        if (giocatori.containsKey(id)) {
            return false; // id già presente
        }
        Giocatore g = new Giocatore(id, nickname, punteggioIniziale);
        giocatori.put(id, g);
        return true;
    }

    /**
     * Aggiorna il punteggio di un giocatore dato il suo id.
     * Restituisce true se l'aggiornamento avviene, false se l'id non esiste.
     */
    public boolean aggiornaPunteggio(int id, int nuovoPunteggio) {
        Giocatore g = giocatori.get(id);
        if (g == null) {
            return false;
        }
        g.setPunteggio(nuovoPunteggio);
        return true;
    }

    /**
     * Stampa la classifica ordinata dal più forte al più scarso.
     * A parità di punteggio, ordina alfabeticamente per nickname.
     */
    public void stampaClassificaOrdinata() {
        if (giocatori.isEmpty()) {
            System.out.println("La classifica è vuota.");
            return;
        }

        // Copio i giocatori in una lista
        List<Giocatore> lista = new ArrayList<>(giocatori.values());

        // Ordino la lista con un Comparator
        lista.sort(
                Comparator
                        .comparingInt(Giocatore::getPunteggio)
                        .reversed()                    // prima chi ha punteggio più alto
                        .thenComparing(Giocatore::getNickname) // a parità, per nickname
        );

        System.out.println("=== CLASSIFICA GIOCATORI ===");
        int posizione = 1;
        for (Giocatore g : lista) {
            System.out.printf(
                    "%d) %-15s (id=%d) - Punteggio: %d%n",
                    posizione,
                    g.getNickname(),
                    g.getId(),
                    g.getPunteggio()
            );
            posizione++;
        }
    }
}
