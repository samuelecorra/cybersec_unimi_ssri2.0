package M09_OOP.L11_Interfacce;

/*
 * ============================================================
 * CLASSE Marmotta
 * ============================================================
 * La Marmotta è un'altra tipica preda di montagna.
 * Ruoli:
 *  - Preda
 *  - AnimaleMontano
 *
 * Usiamo un comportamento diverso rispetto al Camoscio, per vedere
 * come ogni classe possa implementare l'interfaccia in modo personalizzato.
 */
public class Marmotta implements Preda, AnimaleMontano {

    private String nome;

    public Marmotta(String nome) {
        this.nome = nome;
    }

    @Override
    public void fuggi() {
        System.out.println(nome + " si getta velocemente nella tana sottoterra.");
    }

    @Override
    public void emettiVerso() {
        System.out.println(nome + " fischia forte per avvisare le altre marmotte del pericolo.");
    }

    @Override
    public void descriviHabitat() {
        System.out.println(nome + " vive in tane scavate nei prati montani, vicino a pendii erbosi.");
    }

    // In questo caso NON sovrascriviamo strategiaDiSopravvivenza()
    // e quindi usiamo il metodo di default definito in Preda.

    @Override
    public String toString() {
        return "Marmotta{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
