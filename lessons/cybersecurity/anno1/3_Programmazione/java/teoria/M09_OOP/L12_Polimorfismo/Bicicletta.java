package M09_OOP.L12_Polimorfismo;

/*
 * ============================================================
 * CLASSE Bicicletta
 * ============================================================
 * Anche la bici è un veicolo, ma è:
 *  - SENZA motore
 *  - con logica di accelerazione ancora diversa
 *
 * Dimostra che:
 *  - possiamo usare la stessa classe astratta anche per "veicoli"
 *    concettualmente diversi (motorizzati / non motorizzati)
 *  - il polimorfismo non è legato solo ai motori ma alla logica che
 *    associamo alla gerarchia di classi.
 */
public class Bicicletta extends Veicolo {

    private boolean elettrica; // esempio: bici elettrica o normale?

    public Bicicletta(String targa, int velocitaMassima, boolean elettrica) {
        super(targa, velocitaMassima);
        this.elettrica = elettrica;
    }

    @Override
    public void accelera(int incremento) {
        // Se è elettrica, la bici "aiuta" un po' in più
        int incrementoEffettivo = elettrica ? incremento + 2 : incremento;

        velocitaCorrente += incrementoEffettivo;

        if (velocitaCorrente > velocitaMassima) {
            velocitaCorrente = velocitaMassima;
        }

        System.out.println("[BICI " + targa + "] Accelerazione di " + incrementoEffettivo + " km/h. " +
                "Velocità attuale: " + velocitaCorrente + " km/h (vmax=" + velocitaMassima +
                ", elettrica=" + elettrica + ").");
    }

    @Override
    public String descrivi() {
        return "Bicicletta [targa=" + targa +
                ", vmax=" + velocitaMassima +
                ", vCorrente=" + velocitaCorrente +
                ", elettrica=" + elettrica + "]";
    }

    public boolean isElettrica() {
        return elettrica;
    }
}
