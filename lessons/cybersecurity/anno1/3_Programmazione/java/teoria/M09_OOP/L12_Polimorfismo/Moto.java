package M09_OOP.L12_Polimorfismo;

/*
 * ============================================================
 * CLASSE Moto
 * ============================================================
 * Anche Moto estende Veicolo.
 *
 * Ruolo didattico:
 *  - mostra un'altra IMPLEMENTAZIONE del metodo astratto accelera()
 *    con una logica diversa rispetto ad Auto.
 *  - stessa firma del metodo, ma codice diverso → POLIMORFISMO.
 */
public class Moto extends Veicolo {

    private boolean haBauletto;

    public Moto(String targa, int velocitaMassima, boolean haBauletto) {
        super(targa, velocitaMassima);
        this.haBauletto = haBauletto;
    }

    @Override
    public void accelera(int incremento) {
        // Simuliamo che la moto reagisce più "nervosa" all'accelerazione:
        int incrementoEffettivo = incremento + 5; // giusto per differenziare

        velocitaCorrente += incrementoEffettivo;

        if (velocitaCorrente > velocitaMassima) {
            velocitaCorrente = velocitaMassima;
        }

        System.out.println("[MOTO " + targa + "] Accelerazione di " + incrementoEffettivo + " km/h. " +
                "Velocità attuale: " + velocitaCorrente + " km/h (vmax=" + velocitaMassima + ").");
    }

    @Override
    public String descrivi() {
        return "Moto [targa=" + targa +
                ", vmax=" + velocitaMassima +
                ", vCorrente=" + velocitaCorrente +
                ", haBauletto=" + haBauletto + "]";
    }

    public boolean isHaBauletto() {
        return haBauletto;
    }
}
