package M09_OOP.L12_Polimorfismo;

/*
 * ============================================================
 * CLASSE ASTRATTA Veicolo
 * ============================================================
 * Un VEICOLO è un concetto generale: auto, moto, bici, bus, camion...
 *
 * Perché la facciamo ASTRATTA?
 *  - Non ha senso creare un "Veicolo" generico direttamente (non esiste
 *    un "veicolo" senza tipo nella realtà).
 *  - Ha invece senso condividere ATTRIBUTI e METODI comuni a tutti
 *    i veicoli (targa, velocità massima, accensione, frenata, ecc.).
 *
 * Con una classe astratta possiamo:
 *  - definire campi e metodi "comuni" GIÀ implementati
 *  - definire METODI ASTRATTI che le sottoclassi DEVONO implementare
 *    con la propria versione (polimorfismo tramite overriding).
 */
public abstract class Veicolo {

    // ----------------- CAMPI COMUNI -----------------
    protected String targa;
    protected int velocitaMassima;
    protected int velocitaCorrente;

    /*
     * Costruttore di base.
     * Usiamo "protected" per permettere SOLO alle sottoclassi di istanziare
     * il veicolo, non dall'esterno (tanto la classe è astratta).
     */
    protected Veicolo(String targa, int velocitaMassima) {
        this.targa = targa;
        this.velocitaMassima = velocitaMassima;
        this.velocitaCorrente = 0; // un veicolo parte fermo
    }

    // ----------------- METODI COMUNI CONCRETI -----------------

    /*
     * Metodo che simula l'accensione generica del veicolo.
     * Le sottoclassi EREDITANO questo comportamento così com'è.
     */
    public void avvia() {
        System.out.println("[" + targa + "] Il veicolo viene avviato. Motore in funzione.");
    }

    /*
     * Frenata/fine corsa comune a tutti i veicoli.
     * Le sottoclassi possono usare direttamente questo metodo.
     */
    public void ferma() {
        velocitaCorrente = 0;
        System.out.println("[" + targa + "] Il veicolo si ferma. Velocità corrente: " + velocitaCorrente + " km/h.");
    }

    // ----------------- METODO ASTRATTO (POLIMORFISMO) -----------------

    /*
     * Metodo ASTRATTO: non ha implementazione nella classe base.
     *
     * L'IDEA:
     *  - Tutti i veicoli sanno "accelerare",
     *  - ma il MODO di accelerare può essere molto diverso
     *    (un'auto accelera diversamente da una bici).
     *
     * Le sottoclassi DEVONO implementare questo metodo OBBLIGATORIAMENTE.
     */
    public abstract void accelera(int incremento);

    // ----------------- METODO POLIMORFICO NON ASTRATTO -----------------

    /*
     * Metodo comune che può essere ridefinito nelle sottoclassi
     * per arricchire la descrizione.
     */
    public String descrivi() {
        return "Veicolo generico [targa=" + targa +
                ", vmax=" + velocitaMassima +
                ", vCorrente=" + velocitaCorrente + "]";
    }

    // Getter semplici per usare i campi dall'esterno nel main
    public String getTarga() {
        return targa;
    }

    public int getVelocitaMassima() {
        return velocitaMassima;
    }

    public int getVelocitaCorrente() {
        return velocitaCorrente;
    }
}
