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

    int x = 13; // global-like variable (instance variable)
    static int y = 42; // global-like variable (static variable)

    // Le variabili locali vincono sempre sulle global-like se hanno lo stesso nome!
    // In generale si può dire che vince lo scope più interno, ovvero si manifesta
    // il cosiddetto "variable shadowing".

        static void main(String[] args) {

        // IN C dicevamo "scope locale" quando la variabile era accessibile solo all'interno
        // di un blocco di codice (ad esempio, all'interno di una funzione o di un ciclo).
        // In Java, il concetto è simile, ma si parla più comunemente di "scope di metodo" e "scope di blocco".

        // Esempio di variabile con scope di metodo:
        int x = 10; // Variabile locale al metodo main

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
