package E3_simulazione_giochi.VendingMachine.Classi;

/* sottoclasse di Prodotto le cui istanze rappresentano una merendina
possiede l'attributo addizionale calorie */

public class Merendina extends Prodotto{
    private final int calorie;

    public Merendina(int costo, String nome, int calorie){
        super(costo, nome);
        this.calorie = calorie;
    }

    public int getCalorie(){
        return calorie;
    }
}

