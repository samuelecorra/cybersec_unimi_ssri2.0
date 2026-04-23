package E3_simulazione_giochi.VendingMachine.Classi;

/* classe astratta che descrive un prodotto venduto dalla macchinetta
il prodotto possiede due attributi: costo (un intero) e nome (una stringa)*/

public abstract class Prodotto{
    private final int costo;
    private final String nome;

    public Prodotto(int costo, String nome){
        this.costo = costo;
        this.nome = nome;
    }

    public int getCosto(){
        return costo;
    }

    public String getNome(){
        return nome;
    }
}
