package M09_OOP.L07_Super;

public class Cane extends Animale {

    String nome;
    int eta;

    Cane(String nome, int eta) {
        super("Canis lupus familiaris"); // richiamo della superclasse
        this.nome = nome;
        this.eta = eta;
    }

    @Override // Anticipiamo la prossima keyword...
    void verso() {
        System.out.println("Il cane abbaia.");
    }

    void versoOriginale() {
        super.verso(); // chiamo il metodo della superclasse
    }
}
