package M09_OOP.L08_MethodOverriding;

public class Auto extends Veicolo {

    /*
     * Se non definissimo alcun costruttore, Java genererebbe automaticamente
     * un costruttore di default che chiama super().
     *
     * Ma un'auto richiede sicuramente una patente, quindi sovrascriviamo
     * il valore impostato nella superclasse.
     */
    public Auto() {
        super(); // chiamata esplicita al costruttore di Veicolo (anche se sarebbe implicita)
        this.richiedePatente = true;
        this.patenteRichiesta = "Patente di guida (categoria B)";
    }

    @Override
    void startEngine() {
        System.out.println("Motore dell'auto avviato. Pronta a partire su strada.");
    }
}
