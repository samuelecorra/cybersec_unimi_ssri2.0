package codici;

public class intro_jml {
    // Oggi iniziamo a parlare di Java Modeling Language (JML), un linguaggio di specifica formale per Java.
    // JML ci permette di annotare il nostro codice Java con specifiche formali che descrivono 
    // il comportamento atteso del programma. 
    // Queste specifiche possono essere utilizzate per verificare la correttezza del codice, 
    // sia manualmente che automaticamente.

    // In JML, possiamo specificare precondizioni, postcondizioni e invarianti per i nostri metodi e classi.

    static void main(String[] args) {
        // Esempio di specifica JML per un metodo che calcola la somma di due numeri interi
        /*
         * @ requires a >= 0 && b >= 0; // Precondizione: a e b devono essere non negativi
         * @ ensures \result == a + b; // Postcondizione: il risultato deve essere la somma di a e b
         */
        int sum(int a, int b) {
            return a + b;
        }
    }

}