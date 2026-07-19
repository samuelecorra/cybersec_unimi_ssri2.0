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
        // Java permetterebbe anche this.numeroAmici, ma sarebbe fuorviante:
        // il campo non appartiene alla singola istanza. Si preferisce il nome della classe.
        System.out.println("Numero di amici: " + Amico.numeroAmici);
        // oppure anche
        System.out.println("Numero di amici: " + numeroAmici); // CORRETTO
    }

}
