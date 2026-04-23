package M09_OOP.L12_Polimorfismo;

import java.util.List;

/*
 * ============================================================
 * CLASSE Parcheggio
 * ============================================================
 * Questa classe NON rappresenta un veicolo, ma un "contesto" dove
 * usare il polimorfismo.
 *
 * L'idea è avere metodi che lavorano su una LISTA DI Veicolo:
 *  - senza sapere se dentro ci sono Auto, Moto, Bici, ecc.
 *  - chiamando metodi polimorfici (accelerare, descrivere, ecc.).
 */
public class Parcheggio {

    /*
     * Stampa le informazioni di tutti i veicoli passati.
     * Qui si vede SUBITO il polimorfismo, perché descrivi()
     * viene risolto dinamicamente per ogni tipo concreto.
     */
    public static void stampaSituazioneParcheggio(List<Veicolo> veicoli) {
        System.out.println("=== STATO DEL PARCHEGGIO ===");
        for (Veicolo v : veicoli) {
            // Anche se la variabile è di tipo Veicolo,
            // viene chiamato il descrivi() della sottoclasse concreta.
            System.out.println(v.descrivi());
        }
        System.out.println("=== FINE STATO ===\n");
    }

    /*
     * Fa avviare tutti i veicoli.
     * Tutti hanno il metodo avvia() ereditato da Veicolo.
     */
    public static void avviaTutti(List<Veicolo> veicoli) {
        System.out.println(">>> Avvio di tutti i veicoli nel parcheggio...");
        for (Veicolo v : veicoli) {
            v.avvia();
        }
        System.out.println();
    }

    /*
     * Chiede a tutti i veicoli di accelerare di un certo incremento.
     *
     * Polimorfismo:
     *  - stesso metodo chiamato (accelera(incremento))
     *  - ma implementazioni differenti (Auto, Moto, Bici...).
     */
    public static void acceleraTutti(List<Veicolo> veicoli, int incremento) {
        System.out.println(">>> Tutti i veicoli accelerano di circa " + incremento + " km/h...");
        for (Veicolo v : veicoli) {
            v.accelera(incremento);
        }
        System.out.println();
    }

    /*
     * Esempio di uso di instanceof e cast:
     *  - a livello didattico per mostrare che possiamo accedere a
     *    metodi/attributi specifici di una sottoclasse SOLO dopo il cast.
     *
     * Nella programmazione "seria" si cerca di ridurre al minimo
     * l'uso di instanceof, ma è ottimo per capire il concetto.
     */
    public static void stampaDettagliSpecifici(List<Veicolo> veicoli) {
        System.out.println(">>> Dettagli SPECIFICI per tipo di veicolo...");

        for (Veicolo v : veicoli) {
            if (v instanceof Auto auto) {
                System.out.println("   - AUTO: targa=" + auto.getTarga()
                        + ", porte=" + auto.getNumeroPorte()
                        + ", alimentazione=" + auto.getAlimentazione());
            } else if (v instanceof Moto moto) {
                System.out.println("   - MOTO: targa=" + moto.getTarga()
                        + ", haBauletto=" + moto.isHaBauletto());
            } else if (v instanceof Bicicletta bici) {
                System.out.println("   - BICI: targa=" + bici.getTarga()
                        + ", elettrica=" + bici.isElettrica());
            } else {
                System.out.println("   - ALTRO VEICOLO: " + v.descrivi());
            }
        }

        System.out.println();
    }
}
