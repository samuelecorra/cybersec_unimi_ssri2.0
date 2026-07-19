package E3_simulazione_giochi.VendingMachine.Classi;

/* sottoclasse di Prodotto che rappresenta una bevanda
   possiede l'attributo addizionale volume (un intero) */

public class Bevanda extends Prodotto{
    private final int volume;

    public Bevanda(int costo, String nome, int volume){
        super(costo, nome);
        if (volume <= 0) {
            throw new IllegalArgumentException("Il volume deve essere positivo");
        }
        this.volume = volume;
    }

    public int getVolume(){
        return volume;
    }
}
