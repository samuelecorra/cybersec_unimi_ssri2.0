package M07_ScopeVariabili.L02_FinalEDurata;

import java.util.ArrayList;
import java.util.List;

public class FinalEDurata {

    /*
     * Un campo statico appartiene alla classe e vive finché la classe resta
     * caricata. Un campo di istanza appartiene invece a un singolo oggetto e
     * rimane raggiungibile finché lo è quell'oggetto.
     */
    private static int oggettiCreati;
    private final int identificatore;

    public FinalEDurata() {
        oggettiCreati++;
        identificatore = oggettiCreati;
    }

    public int getIdentificatore() {
        return identificatore;
    }

    public static int getOggettiCreati() {
        return oggettiCreati;
    }

    public static void main(String[] args) {
        /*
         * Una variabile locale nasce quando l'esecuzione entra nel suo blocco e
         * non è più accessibile dopo l'uscita. Deve essere inizializzata prima
         * dell'uso: a differenza dei campi, non riceve un valore di default.
         */
        final int soglia = 3;
        // soglia = 4; // ERRORE: una variabile final può essere assegnata una sola volta.

        FinalEDurata primo = new FinalEDurata();
        FinalEDurata secondo = new FinalEDurata();

        System.out.println("Identificatori: " + primo.getIdentificatore()
                + ", " + secondo.getIdentificatore());
        System.out.println("Oggetti creati: " + FinalEDurata.getOggettiCreati());
        System.out.println("Soglia locale: " + soglia);

        /*
         * final blocca la riassegnazione del riferimento, non rende immutabile
         * l'oggetto indicato. La lista può ancora cambiare contenuto.
         */
        final List<String> nomi = new ArrayList<>();
        nomi.add("Ada");
        nomi.add("Grace");
        // nomi = new ArrayList<>(); // ERRORE: il riferimento è final.
        System.out.println("Lista riferita da una variabile final: " + nomi);

        /*
         * Una costante di classe combina static e final. Per convenzione il suo
         * nome è in maiuscolo con underscore: static evita una copia per oggetto,
         * final impedisce una nuova assegnazione.
         */
        System.out.println("Secondi in un minuto: " + Costanti.SECONDI_PER_MINUTO);
    }

    private static final class Costanti {
        private static final int SECONDI_PER_MINUTO = 60;

        private Costanti() {
            // Impedisce di istanziare questa classe di sole costanti.
        }
    }
}
