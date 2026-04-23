package E4_avanzati.GestionePrenotazioni.Classi;

public class Tavolo {

    private final int numero;
    private final int posti;

    public Tavolo(int numero, int posti) {
        if (posti <= 0) {
            throw new IllegalArgumentException("Il numero di posti deve essere positivo");
        }
        this.numero = numero;
        this.posti = posti;
    }

    public int getNumero() {
        return numero;
    }

    public int getPosti() {
        return posti;
    }

    @Override
    public String toString() {
        return "Tavolo #" + numero + " (" + posti + " posti)";
    }
}

