package M09_OOP.L11_Interfacce;

import java.util.ArrayList;
import java.util.List;

/*
 * ============================================================
 * CLASSE DI TEST: SimulatoreEcosistemaMontano
 * ============================================================
 * Questo main ha l'obiettivo di MOSTRARE, in pratica, come funzionano
 * le interfacce Preda, Predatore e AnimaleMontano.
 *
 * Idee chiave che vedi in azione:
 *  1. POLIMORFISMO: lavoriamo con liste di INTERFACCE
 *     (List<AnimaleMontano>, List<Preda>, List<Predatore>)
 *     senza conoscere la classe concreta degli oggetti.
 *
 *  2. RUOLI MULTIPLI: un oggetto può essere:
 *       - allo stesso tempo Preda e AnimaleMontano
 *       - oppure Predatore e AnimaleMontano
 *
 *  3. METODI DI DEFAULT: vediamo strategiaDiSopravvivenza() e
 *     avvicinatiAllaPredaSilenziosamente() in azione.
 */
public class SimulatoreEcosistemaMontano {

    static void main(String[] args) {

        // Creiamo alcune istanze concrete di animali
        Camoscio camoscio = new Camoscio("Camoscio Carlo");
        Marmotta marmotta = new Marmotta("Marmotta Marta");
        Lupo lupo = new Lupo("Lupo Luca");
        AquilaReale aquila = new AquilaReale("Aquila Anna");

        // ======================================================
        // 1. Lista di AnimaleMontano → vista "ecologica"
        // ======================================================
        List<AnimaleMontano> animaliMontani = new ArrayList<>();
        animaliMontani.add(camoscio);
        animaliMontani.add(marmotta);
        animaliMontani.add(lupo);
        animaliMontani.add(aquila);

        System.out.println("=== DESCRIZIONE GENERALE DELL'ECOSISTEMA MONTANO ===");
        for (AnimaleMontano animale : animaliMontani) {
            System.out.println(">> " + animale.getClass().getSimpleName());
            animale.descrizioneGenerale();
            animale.emettiVerso();
            animale.descriviHabitat();
            System.out.println();
        }

        // ======================================================
        // 2. Lista di Preda → vista dal punto di vista delle prede
        // ======================================================
        List<Preda> prede = new ArrayList<>();
        prede.add(camoscio);
        prede.add(marmotta);

        System.out.println("=== STRATEGIE DI SOPRAVVIVENZA DELLE PREDE ===");
        for (Preda p : prede) {
            System.out.println(">> " + p);
            // strategiaDiSopravvivenza() è di default, ma il Camoscio l'ha sovrascritta.
            p.strategiaDiSopravvivenza();
            System.out.println();
        }

        // ======================================================
        // 3. Lista di Predatore → vista dal punto di vista dei predatori
        // ======================================================
        List<Predatore> predatori = new ArrayList<>();
        predatori.add(lupo);
        predatori.add(aquila);

        System.out.println("=== I PREDATORI OSSERVANO LE PREDE ===");
        for (Predatore predatore : predatori) {
            System.out.println(">> Predatore: " + predatore.getClass().getSimpleName());

            // Scegliamo una preda a caso dalla lista (per semplicità alterniamo manualmente).
            for (Preda p : prede) {
                System.out.println("   - Nuova simulazione di caccia contro: " + p);
                predatore.caccia(p);
                System.out.println();
            }
        }

        // ======================================================
        // 4. Esempio esplicito di POLIMORFISMO con variabili di tipo interfaccia
        // ======================================================
        System.out.println("=== POLIMORFISMO CON VARIABILI DI TIPO INTERFACCIA ===");

        Preda predaPolimorfa = new Marmotta("Marmotta Polimorfa");
        // Anche se la classe concreta è Marmotta, la variabile è di tipo Preda.
        // Possiamo chiamare SOLO i metodi dell'interfaccia Preda.
        predaPolimorfa.strategiaDiSopravvivenza();
        predaPolimorfa.fuggi();

        Predatore predatorePolimorfo = new Lupo("Lupo Polimorfo");
        predatorePolimorfo.caccia(predaPolimorfa);

        // NOTA: non possiamo fare predatorePolimorfo.emettiVerso()
        // perché il tipo di riferimento è Predatore, che NON definisce quel metodo.
        // Anche se l'oggetto CONCRETO è un Lupo (che implementa AnimaleMontano),
        // dal punto di vista del compilatore abbiamo solo un "Predatore".
    }
}

