package E3_simulazione_giochi.VendingMachine.Classi;

import java.util.Objects;

/* classe astratta che descrive un prodotto venduto dalla macchinetta
il prodotto possiede due attributi: costo (un intero) e nome (una stringa)*/

public abstract class Prodotto{
    private final int costo;
    private final String nome;

    public Prodotto(int costo, String nome){
        if (costo <= 0) {
            throw new IllegalArgumentException("Il costo deve essere positivo");
        }
        this.costo = costo;
        this.nome = Objects.requireNonNull(nome, "nome").trim();
        if (this.nome.isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
    }

    public int getCosto(){
        return costo;
    }

    public String getNome(){
        return nome;
    }
}
