package M09_OOP.L11_Interfacce;

/*
 * ============================================================
 * INTERFACCIA AnimaleMontano
 * ============================================================
 * Questa interfaccia rappresenta ciò che è comune a TUTTI gli animali
 * del nostro ecosistema di montagna, sia prede che predatori.
 *
 * Non stiamo modellando un "animale" come classe concreta, ma come
 * insieme di azioni che un animale montano deve saper compiere:
 *  - emettere un verso
 *  - descrivere il proprio habitat
 */
public interface AnimaleMontano {

    /*
     * Ogni animale montano emette un verso caratteristico.
     * La classe concreta deciderà che suono stampare.
     */
    void emettiVerso();

    /*
     * Descrizione breve dell'habitat tipico di quell'animale.
     * Anche qui la classe concreta sceglie il testo specifico.
     */
    void descriviHabitat();

    /*
     * Metodo di default per una piccola descrizione generica.
     * Può essere usato "as is" oppure sovrascritto.
     */
    default void descrizioneGenerale() {
        System.out.println("Sono un animale che vive in ambiente montano.");
    }
}
