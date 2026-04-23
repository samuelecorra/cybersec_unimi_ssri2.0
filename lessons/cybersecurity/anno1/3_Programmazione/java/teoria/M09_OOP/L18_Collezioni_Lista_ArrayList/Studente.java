package M09_OOP.L18_Collezioni_Lista_ArrayList;

/*
 * Piccola classe Studente da usare dentro le nostre ArrayList
 * per mostrare che le collezioni non sono solo di tipi primitivi/wrapper,
 * ma soprattutto di OGGETTI.
 */
public class Studente {

    private String nome;
    private String matricola;
    private int annoCorso;

    public Studente(String nome, String matricola, int annoCorso) {
        this.nome = nome;
        this.matricola = matricola;
        this.annoCorso = annoCorso;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricola() {
        return matricola;
    }

    public int getAnnoCorso() {
        return annoCorso;
    }

    @Override
    public String toString() {
        return nome + " (matricola: " + matricola + ", anno: " + annoCorso + ")";
    }
}
