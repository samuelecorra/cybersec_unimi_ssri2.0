package M09_OOP.L08_MethodOverriding;

public class Veicolo {

    // Lo teniamo protected così le sottoclassi possono leggerlo/scriverlo direttamente
    // I modificatori di accesso li vedremo meglio più avanti.
    protected boolean richiedePatente;
    protected String patenteRichiesta;
    public Veicolo() {
        // Non tutti i veicoli richiedono una patente (es. biciclette)
        // quindi di default impostiamo a false.
        this.richiedePatente = false;
        this.patenteRichiesta = "Non specificata";
    }

    // Getter per leggere l'informazione dall'esterno in modo pulito
    public boolean isRichiedePatente() {
        return richiedePatente;
    }
    public String getPatenteRichiesta() {
        return patenteRichiesta;
    }

    /*
     * Questo è il metodo che verrà sovrascritto (overridden) nelle sottoclassi.
     * È NON static (di istanza), quindi partecipa al polimorfismo.
     */
    void startEngine() {
        System.out.println("Motore di veicolo generico avviato.");
    }
}
