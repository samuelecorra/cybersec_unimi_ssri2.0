package M06_Metodi.L01_Metodi;

public class Metodi {

    // Cos'è un metodo in Java?
    // Un metodo in Java è un blocco di codice che esegue una specifica funzione.
    // I metodi aiutano a organizzare il codice in modo modulare e riutilizzabile.

    // Il main è un metodo speciale che funge da punto di ingresso per l'esecuzione del programma.
    public static void main(String[] args) {

        // In generale, i metodi in programmazione servono ad assolvere
        // una regola fondamentale della buona programmazione: la riusabilità del codice,
        // che si riassume in tre lettere:

        // DRY - Don't Repeat Yourself (Non Ripeterti)

        // Sono l'equivalente delle funzioni in C/C++.
        // Hanno un return type, una () per passare parametri, e un nome,
        // e in fase di chiamata si usano con il nome e le ().

        String nomePrincipessa = "Gommarosa";
        int anni = 18;

        stampaAuguri(nomePrincipessa, anni);
        stampaAuguri(nomePrincipessa, anni);
        stampaAuguri(nomePrincipessa, anni); // Siamo in vena di fare triplamente auguri! :D

        int risultatoSomma = somma(5, 10);
        System.out.printf("La somma di 5 e 10 è: %d%n", risultatoSomma);
        double numero = 16.0;
        System.out.printf("La radice quadrata di %.2f è: %.2f%n", numero, radiceQuadDi(numero));
        System.out.printf("Il quadrato di %.2f è: %.2f%n", numero, quadratoDi(numero));
        System.out.println("Il cubo di " + numero + " è: " + cuboDi(numero));

        String nome = "Mario";
        String cognome = "Rossi";
        System.out.println("Nominativo completo: " + getNominativoCompleto(nome, cognome));

        int eta = 20;
        if (controllaEta(eta)) {
            System.out.println("Sei maggiorenne.");
        } else {
            System.out.println("Sei minorenne.");
        }
        
    }

        // Esempio di metodo:

    // statico poiché non richiede un'istanza della classe per essere chiamato!
    // E soprattutto perché lo chiameremo dal main, che è statico a sua volta! Se un metodo statico ne chiama un altro,
    // quest'ultimo deve essere statico a sua volta perché non esiste un'istanza della classe in quel contesto!

        static void stampaAuguri(String nome, int eta) {
            System.out.println("Tanti auguri a te!");
            System.out.println("Tanti auguri a te!");
            System.out.printf("Tanti auguri alla Principessa %s!", nome);
            System.out.printf("%nOggi compie %d anni!%n", eta);
            System.out.println("Tanti auguri a te!");
        }

        // E fin qui facile... ma se vogliamo che il metodo restituisca un valore?
        // In questo caso, invece di "void", dobbiamo specificare il tipo di dato di ritorno!

        static int somma(int a, int b) {
            return a + b; // restituisce la somma di a e b
        }

        static double radiceQuadDi(double x) {
            return Math.sqrt(x); // restituisce la radice quadrata di x
        }

        static double quadratoDi(double x) {
            return x * x; // restituisce il quadrato di x
        }

        // Possiamo chiamare ricorsivamente quadrato per fare il cubo!
        static double cuboDi(double x) {
            return quadratoDi(x) * x; // restituisce il cubo di x
        }

        // Possiamo crearci i nostri personalissimi metodi di utilities:
        static String getNominativoCompleto(String nome, String cognome) {
            return nome + " " + cognome;
        }

        static boolean controllaEta(int eta) {
            return eta >= 18; // restituisce true se l'età è 18 o più, altrimenti false
        }

}
