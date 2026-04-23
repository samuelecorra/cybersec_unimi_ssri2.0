package M09_OOP.L11_Interfacce;

/*
 * ============================================================
 * INTERFACCIA Preda
 * ============================================================
 * Un'interfaccia, in Java, è un "contratto": definisce COSA un oggetto
 * deve saper fare (metodi da implementare) ma NON contiene la logica
 * concreta (il COME).
 *
 * Qui "Preda" rappresenta il RUOLO di un animale che può essere cacciato.
 * Un animale può essere contemporaneamente:
 *  - una Preda (per un predatore più grande)
 *  - e un Predatore (per animali più piccoli)
 *
 * Usare interfacce per questi ruoli è perfetto perché:
 *  - un'unica classe può implementare PIÙ interfacce (più ruoli)
 *  - separiamo l'idea di "essere preda" dall'idea di "essere predatore"
 */

public interface Preda {

    /*
     * Metodo che descrive cosa fa la preda quando percepisce un pericolo.
     * Non definiamo il corpo del metodo, lasciamo alle classi concrete
     * la scelta di COME fuggire (correre, nascondersi, volare, scavare...).
     */
    void fuggi();

    /*
     * Esempio di METODO DI DEFAULT nelle interfacce (da Java 8 in poi).
     * Un metodo di default ha già una piccola implementazione.
     * Le classi che implementano l'interfaccia possono:
     *  - usarlo così com'è
     *  - oppure OVERRIDE se vogliono un comportamento diverso.
     */
    default void strategiaDiSopravvivenza() {
        System.out.println("La preda osserva l'ambiente e cerca una via di fuga sicura.");
    }
}
