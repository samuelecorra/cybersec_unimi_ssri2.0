package E4_avanzati.GestionePrenotazioni.Classi;

public class Prenotazione {

    private final String nomeCliente;
    private final int numeroPersone;

    public Prenotazione(String nomeCliente, int numeroPersone) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del cliente non può essere vuoto");
        }
        if (numeroPersone <= 0) {
            throw new IllegalArgumentException("Il numero di persone deve essere positivo");
        }
        this.nomeCliente = nomeCliente.trim();
        this.numeroPersone = numeroPersone;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    @Override
    public String toString() {
        return "Prenotazione di " + nomeCliente + " per " + numeroPersone + " persone";
    }
}
