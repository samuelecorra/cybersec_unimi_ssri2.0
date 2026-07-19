package M09_OOP.L18_Collezioni_Lista_ArrayList;

import java.util.Objects;

/*
 * Piccola classe Studente da usare dentro le nostre ArrayList
 * per mostrare che le collezioni non sono solo di tipi primitivi/wrapper,
 * ma soprattutto di OGGETTI.
 */
public class Studente {

    private final String nome;
    private final String matricola;
    private final int annoCorso;

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

    /* La matricola è l'identità logica dello studente in questo modello. */
    @Override
    public boolean equals(Object altro) {
        if (this == altro) {
            return true;
        }
        if (!(altro instanceof Studente studente)) {
            return false;
        }
        return matricola.equals(studente.matricola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricola);
    }

    @Override
    public String toString() {
        return nome + " (matricola: " + matricola + ", anno: " + annoCorso + ")";
    }
}
