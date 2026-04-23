package M09_OOP.L08_MethodOverriding;

public class Aircraft extends Veicolo {

    /*
     * "Aircraft" include aerei, elicotteri, droni, ecc.
     * Come per Auto, anche qui un velivolo richiede una patente specifica.
     */
    public Aircraft() {
        super();
        this.richiedePatente = true;
        this.patenteRichiesta = "Licenza di pilota aeronautico generica";
    }

    @Override
    void startEngine() {
        System.out.println("Controlli di bordo completati. Motore dell'aeromobile pronto al decollo.");
    }

}
