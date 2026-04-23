package M09_OOP.L11_Interfacce;

/*
 * ============================================================
 * INTERFACCIA Predatore
 * ============================================================
 * Rappresenta il ruolo di chi caccia una preda.
 * Anche qui definiamo un contratto: un predatore DEVE saper cacciare.
 */
public interface Predatore {

    /*
     * Metodo che descrive l'azione principale di un predatore:
     * tentare la caccia a una preda.
     *
     * Notare che il parametro è di tipo "Preda" (interfaccia).
     * Questo significa che QUALSIASI oggetto che implementa Preda
     * può essere passato a questo metodo, indipendentemente dalla
     * classe concreta (Camoscio, Marmotta, ecc.).
     *
     * → Questo è POLIMORFISMO basato su interfacce.
     */
    void caccia(Preda p);

    /*
     * Metodo di default: comportamento generico di avvicinamento alla preda.
     * Una classe concreta può decidere se usarlo così com'è oppure ridefinirlo.
     */
    default void avvicinatiAllaPredaSilenziosamente() {
        System.out.println("Il predatore si muove in silenzio tra i cespugli...");
    }
}
