package M09_OOP.L14_Incapsulamento;

/*
 * ============================================================
 * CLASSE Automobile
 * ============================================================
 * Modello didattico per l'incapsulamento.
 *
 * - I campi sono PRIVATE → NON accessibili direttamente dall'esterno.
 * - Li rendiamo accessibili tramite GETTERS (lettura)
 *   e SETTERS (scrittura con regole).
 */
public class Automobile {

    // 🔒 CAMPI PRIVATI (INACCESSIBILI DIRETTAMENTE)
    private String marca;
    private String modello;
    private int anno;
    private int velocita;

    /*
     * Costruttore completo dell'Automobile.
     */
    public Automobile(String marca, String modello, int anno) {
        this.marca = marca;
        this.modello = modello;
        setAnno(anno);   // usiamo il setter per applicare regole
        this.velocita = 0;
    }

    // ============================================================
    //  GETTERS (metodi che rendono un campo LEGGIBILE)
    // ============================================================

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public int getAnno() {
        return anno;
    }

    public int getVelocita() {
        return velocita;
    }

    // ============================================================
    //  SETTERS (regole per modificare i campi)
    // ============================================================

    public void setAnno(int anno) {
        if (anno < 1886) {
            // 1886 = prima automobile della storia (Benz Patent-Motorwagen)
            System.out.println("Anno non valido: le auto non esistevano prima del 1886.");
            this.anno = 1886;
        } else {
            this.anno = anno;
        }
    }

    /*
     * Modifica diretta della velocità controllata tramite metodi specifici.
     * NON esiste un setter classico per non permettere valori illegali.
     */
    private void setVelocita(int velocita) {
        if (velocita < 0) velocita = 0;
        if (velocita > 300) velocita = 300; // limite sicurezza
        this.velocita = velocita;
    }

    // ============================================================
    // METODI PUBBLICI DI COMPORTAMENTO
    // (non permettiamo modifiche dirette a velocità)
    // ============================================================

    public void accelera(int incremento) {
        setVelocita(velocita + incremento);
        System.out.println("L'auto accelera. Velocità attuale: " + velocita + " km/h.");
    }

    public void frena(int decremento) {
        setVelocita(velocita - decremento);
        System.out.println("L'auto frena. Velocità attuale: " + velocita + " km/h.");
    }

    @Override
    public String toString() {
        return marca + " " + modello + " (" + anno + "), " +
                "velocità attuale: " + velocita + " km/h.";
    }
}
