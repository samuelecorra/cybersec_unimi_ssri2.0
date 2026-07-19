package E2_gestionali.GestionaleBiblioteca.Classi;

import java.util.List;
import java.util.ArrayList;

public class Utente {
    private final int id;
    private final String nome;
    private final List<Libro> libriInPrestito = new ArrayList<>();

    public Utente(int id, String nome) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID dell'utente deve essere positivo.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome dell'utente non può essere vuoto.");
        }
        this.id = id;
        this.nome = nome.trim();
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
        for (int i = 0; i < libriInPrestito.size(); i++) {
            Libro libro = libriInPrestito.get(i);
            if (libro.getId() == idLibro) {
                return libriInPrestito.remove(i);
            }
        }
        return null;
    }

    public boolean haLibro(int idLibro) {
        return libriInPrestito.stream().anyMatch(libro -> libro.getId() == idLibro);
    }

    @Override
    public String toString() {
        return "Utente [" + id + "] " + nome;
    }
}
