package E3_simulazione_giochi.Serpenti_e_Scale.Classi;

import java.util.HashMap;
import java.util.Map;

public class Tabellone {
    public static final int CASELLA_FINALE = 100;

    // key = casella di partenza, value = casella di arrivo
    private final Map<Integer, Integer> serpentiEScale = new HashMap<>();

    public Tabellone() {
        inizializzaSerpentiEScale();
    }

    private void inizializzaSerpentiEScale() {
        // Esempio di configurazione (puoi cambiarla):
        // scale
        serpentiEScale.put(4, 14);
        serpentiEScale.put(9, 31);
        serpentiEScale.put(20, 38);
        serpentiEScale.put(40, 59);
        // serpenti
        serpentiEScale.put(17, 7);
        serpentiEScale.put(28, 3);
        serpentiEScale.put(54, 34);
        serpentiEScale.put(62, 19);
        serpentiEScale.put(87, 24);
        serpentiEScale.put(99, 78);
    }

    /**
     * Applica l’effetto di serpenti/scale.
     * Se la casella non ha serpente/scala, restituisce la posizione invariata.
     */
    public int applicaEffetto(int posizione) {
        return serpentiEScale.getOrDefault(posizione, posizione);
    }

    public boolean haEffetto(int posizione) {
        return serpentiEScale.containsKey(posizione);
    }

    public int getDestinazione(int posizione) {
        return serpentiEScale.get(posizione);
    }
}

