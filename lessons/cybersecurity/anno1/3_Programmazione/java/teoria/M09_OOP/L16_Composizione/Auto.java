package M09_OOP.L16_Composizione;

/*
 * ============================================================
 * CLASSE Auto (COMPOSIZIONE)
 * ============================================================
 *
 * Questa classe rappresenta un'auto che CONTIENE un Motore.
 *
 * RELAZIONE DI COMPOSIZIONE ("parte-tutto"):
 *  - L'Auto è il TUTTO.
 *  - Il Motore è una PARTE fondamentale dell'Auto.
 *  - Il motore è CREATO DENTRO il costruttore di Auto.
 *  - Il motore NON viene passato dall'esterno, non gira "da solo".
 *
 * Nel modello concettuale:
 *  - Se l'oggetto Auto viene "distrutto", il suo Motore non ha più
 *    senso di esistere (muore con l'Auto).
 *
 * Questo è diverso dall'aggregazione:
 *  - aggregazione: oggetti interni possono esistere indipendentemente
 *  - composizione: oggetti interni hanno ciclo di vita dipendente dal "contenitore"
 */
public class Auto {

    private String marca;
    private String modello;
    private int anno;
    private Motore motore;   // COMPOSIZIONE: Auto HA-UN Motore

    /*
     * Costruttore dell'Auto.
     *
     * Qui avviene la COMPOSIZIONE vera e propria:
     *  - instanziamo il Motore DENTRO l'auto
     *  - dall'esterno non vedo il "new Motore", vedo solo "new Auto(...)"
     */
    public Auto(String marca, String modello, int anno,
                int cilindrataMotore, int cavalliMotore, String tipoAlimentazione) {

        this.marca = marca;
        this.modello = modello;
        this.anno = anno;

        // COMPOSIZIONE: il motore viene creato internamente, come parte dell'Auto
        this.motore = new Motore(cilindrataMotore, cavalliMotore, tipoAlimentazione);
    }

    // ================== METODI DI COMPORTAMENTO ==================

    /*
     * Accende l'auto → in realtà chiede al motore di avviarsi.
     */
    public void accendi() {
        System.out.println("Sto accendendo l'auto " + marca + " " + modello + "...");
        motore.avvia();
    }

    /*
     * Spegne l'auto → spegne il motore.
     */
    public void spegni() {
        System.out.println("Sto spegnendo l'auto " + marca + " " + modello + "...");
        motore.spegni();
    }

    /*
     * Simuliamo una semplice accelerazione: controlliamo se il motore è acceso.
     */
    public void accelera() {
        if (!motore.isAcceso()) {
            System.out.println("Impossibile accelerare: il motore è spento.");
        } else {
            System.out.println("L'auto " + marca + " " + modello +
                    " sta accelerando sfruttando i suoi " + motore.getCavalli() + " cavalli!");
        }
    }

    public void mostraDettagli() {
        System.out.println("=== DETTAGLI AUTO ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modello: " + modello);
        System.out.println("Anno: " + anno);
        System.out.println("Motore: " + motore);
        System.out.println("=====================\n");
    }

    @Override
    public String toString() {
        return "Auto{" +
                "marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", anno=" + anno +
                '}';
    }
}

