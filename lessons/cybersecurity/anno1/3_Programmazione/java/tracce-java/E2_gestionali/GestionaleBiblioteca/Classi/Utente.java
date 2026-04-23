package E2_gestionali.GestionaleBiblioteca.Classi;

import java.util.List;
import java.util.ArrayList;

public class Utente {
    private final int id;
    private final String nome;
    private final List<Libro> libriInPrestito = new ArrayList<>();

    public Utente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void addLibro(Libro libro) {
        libriInPrestito.add(libro);
    }

    public Libro removeLibro(int idLibro) {
        for (Libro libro : libriInPrestito) {
            if (libro.getId() == idLibro) {
                libriInPrestito.remove(libro);
                return libro;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Utente [" + id + "] " + nome;
    }
}