package M09_OOP.L12_Polimorfismo;

import java.util.ArrayList;
import java.util.List;

/*
 * ============================================================
 * CLASSE DI TEST: TestPolimorfismoVeicoli
 * ============================================================
 *
 * Obiettivo didattico:
 *  - Mostrare in modo CHIARISSIMO cos'è il POLIMORFISMO:
 *    "stessa interfaccia (stessa firma di metodo), molteplici
 *     comportamenti diversi a seconda dell'oggetto concreto".
 *
 * Concetti chiave:
 *  1. Una variabile di tipo Veicolo può riferirsi a:
 *       - un oggetto Auto
 *       - un oggetto Moto
 *       - un oggetto Bicicletta
 *     → e chiamando accelera(), viene usata la versione corretta.
 *
 *  2. Collezioni polimorfiche:
 *       - List<Veicolo> che contiene sia Auto che Moto che Bici.
 *
 *  3. Differenza tra:
 *       - tipo STATICO (quello dichiarato: Veicolo)
 *       - tipo DINAMICO (quello reale in memoria: Auto, Moto, Bici).
 */
public class TestPolimorfismoVeicoli {

    static void main(String[] args) {

        // ------------------------------------------------------
        // 1. Creazione di oggetti specifici
        // ------------------------------------------------------
        Auto auto1 = new Auto("AUTO123", 200, 5, "benzina");
        Moto moto1 = new Moto("MOTO777", 220, true);
        Bicicletta bici1 = new Bicicletta("BICI001", 45, false);

        // ------------------------------------------------------
        // 2. Polimorfismo su variabili di tipo Veicolo
        // ------------------------------------------------------
        // Tipo STATICO: Veicolo
        // Tipo DINAMICO: Auto, Moto, Bicicletta
        Veicolo v1 = auto1;
        Veicolo v2 = moto1;
        Veicolo v3 = bici1;

        System.out.println("=== ESEMPIO DI POLIMORFISMO SU SINGOLE VARIABILI ===");
        v1.avvia();
        v1.accelera(30); // → chiama Auto.accelera()

        v2.avvia();
        v2.accelera(30); // → chiama Moto.accelera()

        v3.avvia();
        v3.accelera(15); // → chiama Bicicletta.accelera()
        System.out.println();

        // ------------------------------------------------------
        // 3. Lista polimorfica di Veicolo
        // ------------------------------------------------------
        List<Veicolo> veicoliParcheggio = new ArrayList<>();
        veicoliParcheggio.add(auto1);
        veicoliParcheggio.add(moto1);
        veicoliParcheggio.add(bici1);

        // Stampo la situazione iniziale del parcheggio
        Parcheggio.stampaSituazioneParcheggio(veicoliParcheggio);

        // Avvio tutti i veicoli
        Parcheggio.avviaTutti(veicoliParcheggio);

        // Tutti accelerano con la stessa chiamata, ma comportamenti diversi
        Parcheggio.acceleraTutti(veicoliParcheggio, 20);

        // Stampo i dettagli specifici usando instanceof (solo didattico)
        Parcheggio.stampaDettagliSpecifici(veicoliParcheggio);

        // ------------------------------------------------------
        // 4. Dimostrazione di cosa NON si può fare col solo tipo Veicolo
        // ------------------------------------------------------
        System.out.println("=== TIPO STATICO vs TIPO DINAMICO ===");

        Veicolo veicoloGenerico = new Auto("AUTO999", 180, 3, "elettrica");

        // Posso chiamare SOLO i metodi definiti in Veicolo:
        veicoloGenerico.avvia();
        veicoloGenerico.accelera(40);
        System.out.println(veicoloGenerico.descrivi());

        // NON posso fare, ad esempio:
        // veicoloGenerico.getNumeroPorte(); // ERRORE di compilazione
        //
        // perché:
        //  - Il compilatore guarda il TIPO STATICO della variabile (Veicolo),
        //    e in Veicolo NON esiste getNumeroPorte().
        //
        // Se voglio accedere a metodi specifici di Auto, devo CASTARE:

        if (veicoloGenerico instanceof Auto autoSpecifico) {
            System.out.println("Numero porte (dopo il cast): " + autoSpecifico.getNumeroPorte());
        }

        System.out.println("\n=== FINE DIMOSTRAZIONE POLIMORFISMO VEICOLI ===");
    }
}
