package M09_OOP.L05_Static;

public class Amico {

    static int numeroAmici;
    String nome;

    Amico(String nome) {
        this.nome = nome;
        numeroAmici++;
    }

    // ATTENZIONE:
    void mostraAmici() {
        // System.out.println("Numero di amici: " + this.numeroAmici); // ERRORE!
        // Essendo attributo di classe, non può essere referenziato con "this", che si riferisce all'istanza corrente.
        System.out.println("Numero di amici: " + Amico.numeroAmici); // CORRETTO
        // oppure anche
        System.out.println("Numero di amici: " + numeroAmici); // CORRETTO
    }

}
