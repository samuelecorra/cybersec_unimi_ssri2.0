package M09_OOP.L08_MethodOverriding;

public class MethodOverriding {

    /*
     * Il method overriding (sovrascrittura dei metodi) permette a una sottoclasse
     * di ridefinire il comportamento di un metodo già dichiarato nella superclasse.
     *
     * Grazie al polimorfismo:
     *  - la scelta del metodo da chiamare avviene a runtime
     *  - conta il TIPO REALE dell’oggetto, non il tipo della variabile di riferimento.
     */

    public static void main(String[] args) {

        // Oggetto della superclasse
        Veicolo vGenerico = new Veicolo();

        // Upcasting: oggetto Auto visto come Veicolo
        Veicolo vAuto = new Auto();

        // Upcasting: oggetto Aircraft visto come Veicolo
        Veicolo vAircraft = new Aircraft();

        // Upcasting: oggetto Harrier visto come Veicolo
        Veicolo vF16 = new Harrier();

        System.out.println("=== Demo overriding di startEngine() ===");
        vGenerico.startEngine(); // chiama Veicolo.startEngine() => Motore di veicolo generico avviato.
        vAuto.startEngine();     // chiama Auto.startEngine() => Motore dell'auto avviato. Pronta a partire su strada.
        vAircraft.startEngine(); // chiama Aircraft.startEngine()
        // => Controlli di bordo completati. Motore dell'aeromobile pronto al decollo.
        vF16.startEngine();      // chiama Harrier.startEngine() =>

        System.out.println();
        System.out.println("=== Demo polimorfismo su metodo comune ===");
        stampaInfoPatente(vGenerico);
        stampaInfoPatente(vAuto);
        stampaInfoPatente(vAircraft);
        stampaInfoPatente(vF16);

        System.out.println();
        System.out.println("=== Uso di metodi specifici della sottoclasse ===");
        // Per usare i metodi specifici di Harrier, devo avere una variabile di tipo Harrier
        Harrier harrier = new Harrier();
        harrier.startEngine();
        harrier.decolloLineare();
        harrier.atterraggioLineare(); // Facciamo un primo volo lineare
        harrier.decolloVTOL(); // Ora decolliamo in modalità VTOL
        // Una volta in volo, possiamo tornare alla modalità lineare
        harrier.disattivaVTOLinVolo(); // Ora procediamo con un volo lineare
        // Voliamo un po'...
        harrier.attivaPostBruciatore(); // Attiviamo il postbruciatore per aumentare la velocità
        // Spegniamolo per tornare a velocità normali
        harrier.disattivaPostBruciatore();
        // Per sbaglio, attiviamo la modalità VTOL in volo
        harrier.attivaVTOLinVolo();
        // Correggiamo disattivandola
        harrier.disattivaVTOLinVolo();
        // Infine, atterriamo in modalità VTOL
        harrier.atterraggioVTOL();
    }

    /*
     * Metodo che mostra bene il polimorfismo:
     * accetta un Veicolo (superclasse) ma può ricevere QUALSIASI sottoclasse.
     */
    static void stampaInfoPatente(Veicolo v) {
        System.out.println("Tipo di veicolo: " + v.getClass().getSimpleName()
                + " | richiede patente? " + v.isRichiedePatente()
                + " | patente richiesta: " + v.getPatenteRichiesta());
    }
}

