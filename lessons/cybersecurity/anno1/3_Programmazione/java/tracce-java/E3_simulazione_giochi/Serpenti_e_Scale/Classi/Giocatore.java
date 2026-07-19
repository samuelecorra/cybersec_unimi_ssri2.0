package E3_simulazione_giochi.Serpenti_e_Scale.Classi;

import java.util.Objects;

public class Giocatore {
    private final String nome;
    private int posizione; // da 0 (partenza) a 100

    public Giocatore(String nome) {
        this.nome = Objects.requireNonNull(nome, "nome").trim();
        if (this.nome.isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        this.posizione = 0; // tutti partono fuori dal tabellone
    }

    public String getNome() {
        return nome;
    }

    public int getPosizione() {
        return posizione;
    }

    public void muoviDi(int passi) {
        if (passi < 0) {
            throw new IllegalArgumentException("I passi non possono essere negativi");
        }
        setPosizione(posizione + passi);
    }

    public void setPosizione(int nuovaPosizione) {
        if (nuovaPosizione < 0 || nuovaPosizione > Tabellone.CASELLA_FINALE) {
            throw new IllegalArgumentException("La posizione deve essere compresa tra 0 e 100");
        }
        this.posizione = nuovaPosizione;
    }

    @Override
    public String toString() {
        return nome + " (posizione: " + posizione + ")";
    }
}
