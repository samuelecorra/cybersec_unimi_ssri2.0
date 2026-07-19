package M07_ScopeVariabili.L01_ScopeVariabili;

public class ScopeVariabili {

    // Scope delle variabili in Java

    // In Java, lo "scope" di una variabile si riferisce alla porzione di codice
    // in cui la variabile è accessibile. Lo scope è determinato dalla posizione
    // in cui la variabile viene dichiarata.

    // Partiamo da quello che in C chiamavamo scope globale, ovvero le variabili
    // dichiarate all'esterno di qualsiasi funzione. In Java, le variabili globali
    // non esistono nello stesso modo, ma possiamo avere variabili di istanza
    // (dichiarate all'interno di una classe ma fuori da qualsiasi metodo)
    // e variabili statiche (anche dette variabili di classe, dichiarate con la parola chiave "static"
    // sempre fuori da qualsiasi metodo).

    int x = 13; // campo di istanza: ogni oggetto ha la propria copia
    static int y = 42; // campo statico (o di classe): condiviso da tutte le istanze

    // Se una variabile locale ha lo stesso nome di un campo, il nome semplice indica
    // la variabile locale: è il cosiddetto "variable shadowing". Il campo rimane
    // comunque accessibile attraverso un oggetto (oppure tramite il nome della classe,
    // se è statico).

    public static void main(String[] args) {

        // IN C dicevamo "scope locale" quando la variabile era accessibile solo all'interno
        // di un blocco di codice (ad esempio, all'interno di una funzione o di un ciclo).
        // In Java, il concetto è simile, ma si parla più comunemente di "scope di metodo" e "scope di blocco".

        // Esempio di variabile con scope di metodo:
        int x = 10; // Variabile locale al metodo main
        ScopeVariabili esempio = new ScopeVariabili();
        System.out.println("x locale: " + x);
        System.out.println("campo x dell'oggetto: " + esempio.x);
        System.out.println("campo statico y: " + ScopeVariabili.y);

        if (x > 5) {
            int y = 20; // Variabile locale al blocco if
            System.out.println("y dentro l'if: " + y); // Accessibile qui
        }
        // System.out.println("y fuori dall'if: " + y); // Errore! y non è accessibile qui

        for (int i = 0; i < 3; i++) {
            int z = i * 2; // Variabile locale al blocco for
            System.out.println("z dentro il for: " + z); // Accessibile qui
        }
        // System.out.println("z fuori dal for: " + z); // Errore! z non è accessibile qui

        altroMetodo();

    }

    // Fuori dal main se c'è un altro metodo, all'interno di esso posso dichiarare tranquillamente
    // altre variabili con lo stesso nome di quelle del main, perché hanno scope diversi!
    static void altroMetodo() {
        int x = 50; // Questa x è diversa da quella del main
        System.out.println("x dentro altroMetodo: " + x); // Stampa 50
    }
}
