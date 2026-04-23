package E3_simulazione_giochi.Serpenti_e_Scale.Classi;

public class Giocatore {
    private final String nome;
    private int posizione; // da 0 (partenza) a 100

    public Giocatore(String nome) {
        this.nome = nome;
        this.posizione = 0; // tutti partono fuori dal tabellone
    }

    public String getNome() {
        return nome;
    }

    public int getPosizione() {
        return posizione;
    }

    public void muoviDi(int passi) {
        posizione += passi;
    }

    public void setPosizione(int nuovaPosizione) {
        this.posizione = nuovaPosizione;
    }

    @Override
    public String toString() {
        return nome + " (posizione: " + posizione + ")";
    }
}

