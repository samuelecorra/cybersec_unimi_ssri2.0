package M09_OOP.L05_Static;

public class Static {

    // La keyword static ha un significato particolare molto importante in Java.

    // Se usata su un attributo di una classe, indica che l'attributo è condiviso tra tutte le istanze della classe.
    // In altre parole, c'è una sola copia dell'attributo statico, indipendentemente dal numero di oggetti creati.
    // Questo è utile per mantenere uno stato comune o contatori condivisi tra tutte le istanze.

    // Se usata su un metodo, indica che il metodo può essere chiamato senza creare un'istanza della classe.

    // Un blocco statico viene eseguito una sola volta, durante l'inizializzazione della classe.

    // Se usata su una classe interna, indica che la classe interna non ha un riferimento implicito alla classe esterna.

    public static void main(String[] args) {

        Amico a1 = new Amico("Luca");
        Amico a2 = new Amico("Marco");
        Amico a3 = new Amico("Giulia");
        System.out.println("Numero di amici: " + Amico.numeroAmici); // ATTRIBUTO DI CLASSE, NON DI ISTANZA
        // => NON scriviamo a1.numeroAmici, perchè numeroAmici è un attributo statico, condiviso da tutte le istanze della classe Amico.

        // Output: Numero di amici: 3
        // Senza static, ogni istanza di Amico avrebbe il proprio contatore numeroAmici,
        // e non sarebbe possibile tenere traccia del numero totale di amici creati.

        // DA QUESTA LEZIONE COSA CAPIAMO?
        // Che quando studiavamo i metodi di Math, ad esempio Math.sqrt(),
        // sqrt() era un metodo statico, quindi potevamo chiamarlo senza creare un'istanza di Math.
        double radice = Math.sqrt(16); // CORRETTO
        // Math m = new Math(); // ERRORE: il costruttore di Math è privato.
        // Una classe che contiene solo metodi statici potrebbe invece essere istanziabile
        // se dichiarasse un costruttore accessibile.
        System.out.println("Radice quadrata di 16: " + radice); // Output: Radice quadrata di 16: 4.0


    }
}
