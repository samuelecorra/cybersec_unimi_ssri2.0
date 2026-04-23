package M09_OOP.L12_Polimorfismo;

/*
 * ============================================================
 * CLASSE Auto
 * ============================================================
 * Sottoclasse CONCRETA di Veicolo.
 *
 * EREDITA:
 *  - targa
 *  - velocitaMassima
 *  - velocitaCorrente
 *  - avvia()
 *  - ferma()
 *
 * IMPLEMENTA:
 *  - accelera(int incremento) (metodo astratto della classe base)
 *
 * POLIMORFISMO:
 *  - Se io salvo un'Auto in una variabile di tipo Veicolo,
 *    quando chiamo accelera() verrà eseguita LA VERSIONE DI Auto.
 */
public class Auto extends Veicolo {

    private int numeroPorte;
    private String alimentazione; // es. "benzina", "diesel", "elettrica", "ibrida"

    public Auto(String targa, int velocitaMassima, int numeroPorte, String alimentazione) {
        super(targa, velocitaMassima); // richiamo il costruttore della classe astratta
        this.numeroPorte = numeroPorte;
        this.alimentazione = alimentazione;
    }

    @Override
    public void accelera(int incremento) {
        // Simuliamo un'accelerazione "realistica" di un'auto.
        velocitaCorrente += incremento;

        if (velocitaCorrente > velocitaMassima) {
            velocitaCorrente = velocitaMassima;
        }

        System.out.println("[AUTO " + targa + "] Accelerazione di " + incremento + " km/h. " +
                "Velocità attuale: " + velocitaCorrente + " km/h (vmax=" + velocitaMassima + ").");
    }

    @Override
    public String descrivi() {
        return "Auto [targa=" + targa +
                ", vmax=" + velocitaMassima +
                ", vCorrente=" + velocitaCorrente +
                ", porte=" + numeroPorte +
                ", alimentazione=" + alimentazione + "]";
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public String getAlimentazione() {
        return alimentazione;
    }
}
