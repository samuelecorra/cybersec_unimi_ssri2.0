package M09_OOP.L16_Composizione;

/*
 * ============================================================
 * LEZIONE 16 – COMPOSIZIONE
 * ============================================================
 *
 * DEFINIZIONE (in parallelo al commento della lezione YouTube):
 *
 *  Composition = rappresenta una relazione "parte-tutto" molto forte.
 *  Un oggetto contiene un altro oggetto come parte ESSENZIALE della
 *  sua struttura, e l'oggetto contenuto DIPENDE dal contenitore per
 *  il suo ciclo di vita.
 *
 *  In termini intuitivi:
 *   - "Se distruggo il tutto, anche le parti smettono di avere senso."
 *
 * DIFFERENZA RAPIDA CON L'AGGREGAZIONE:
 *  - AGGREGAZIONE: Library "ha" Books, ma i Books esistono anche senza Library.
 *  - COMPOSIZIONE: Auto "ha" Motore, e il Motore nel nostro modello
 *    vive e muore con l'Auto.
 *
 * OBIETTIVO DEL MAIN:
 *  - Creare un'Auto che internamente crea il proprio Motore.
 *  - Usare i metodi dell'Auto (accendi, spegni, accelera) che delegano
 *    il lavoro al Motore interno.
 *  - Far capire che dall'esterno non si lavora quasi mai con "Motore",
 *    ma solo con "Auto", perché il motore è parte interna.
 */
public class MainComposizione {

    public static void main(String[] args) {

        // 1. Creiamo un'auto.
        //    Nota: non vediamo nessun "new Motore" qui → è nascosto dentro.
        Auto auto = new Auto(
                "Tesla",
                "Model S Plaid",
                2023,
                0,          // cilindrata simbolica (elettrica)
                1020,       // cavalli
                "elettrico" // tipo alimentazione
        );

        System.out.println("=== CREAZIONE AUTO (COMPOSIZIONE) ===");
        auto.mostraDettagli();

        // 2. Proviamo ad accelerare a motore spento
        System.out.println(">>> Tentativo di accelerazione a motore spento:");
        auto.accelera();

        // 3. Accendiamo l'auto (accende il motore interno)
        System.out.println("\n>>> Accensione dell'auto:");
        auto.accendi();

        // 4. Ora acceleriamo
        System.out.println("\n>>> Accelerazione con motore acceso:");
        auto.accelera();

        // 5. Spegniamo l'auto (spegne il motore interno)
        System.out.println("\n>>> Spegnimento dell'auto:");
        auto.spegni();

        // 6. Riflessione concettuale (commento didattico):
        //
        //  In questa lezione NON abbiamo MAI creato un "Motore" da solo nel main.
        //  Non abbiamo qualcosa come:
        //     Motore m = new Motore(...);
        //
        //  Il motore è sempre gestito internamente da Auto. Questo è IL SEGNO
        //  della COMPOSIZIONE: l'oggetto parte esiste solo come componente del tutto.
        //
        System.out.println("\n=== FINE LEZIONE SULLA COMPOSIZIONE ===");
    }
}
