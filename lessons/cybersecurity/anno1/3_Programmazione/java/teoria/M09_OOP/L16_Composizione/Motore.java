package M09_OOP.L16_Composizione;

/*
 * ============================================================
 * CLASSE Motore
 * ============================================================
 * Questa classe rappresenta un MOTORE di automobile.
 *
 * NELLA COMPOSIZIONE:
 *  - Il motore NON è pensato per esistere "da solo" nel programma.
 *  - Viene creato DENTRO l'oggetto Auto.
 *  - Non ha senso (nel nostro modello) avere un Motore senza Auto.
 *
 * A differenza dell'AGGREGAZIONE:
 *  - lì gli oggetti interni possono esistere indipendentemente
 *    (Studente indipendente dal Corso, Libro indipendente dalla Library).
 */
public class Motore {

    private int cilindrata;           // es. 1600, 2000...
    private int cavalli;              // potenza in cavalli
    private boolean acceso;           // stato del motore
    private String tipoAlimentazione; // es. "benzina", "diesel", "elettrico"

    /*
     * Costruttore del motore.
     * Viene tipicamente chiamato SOLO dalla classe Auto.
     */
    public Motore(int cilindrata, int cavalli, String tipoAlimentazione) {
        this.cilindrata = cilindrata;
        this.cavalli = cavalli;
        this.tipoAlimentazione = tipoAlimentazione;
        this.acceso = false;
    }

    public void avvia() {
        if (!acceso) {
            acceso = true;
            System.out.println("Motore avviato. (" + cilindrata + "cc, " + cavalli +
                    " CV, " + tipoAlimentazione + ")");
        } else {
            System.out.println("Il motore è già acceso.");
        }
    }

    public void spegni() {
        if (acceso) {
            acceso = false;
            System.out.println("Motore spento.");
        } else {
            System.out.println("Il motore è già spento.");
        }
    }

    public boolean isAcceso() {
        return acceso;
    }

    public int getCavalli() {
        return cavalli;
    }

    @Override
    public String toString() {
        return "Motore{" +
                "cilindrata=" + cilindrata +
                ", cavalli=" + cavalli +
                ", tipoAlimentazione='" + tipoAlimentazione + '\'' +
                ", acceso=" + acceso +
                '}';
    }
}
