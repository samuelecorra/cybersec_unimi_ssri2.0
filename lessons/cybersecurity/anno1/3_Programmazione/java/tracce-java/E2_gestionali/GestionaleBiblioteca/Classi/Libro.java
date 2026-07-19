package E2_gestionali.GestionaleBiblioteca.Classi;

public class Libro {
    private final int id;
    private final String titolo;
    private final String autore;

    public Libro(int id, String titolo, String autore) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID del libro deve essere positivo.");
        }
        this.id = id;
        this.titolo = richiediTesto(titolo, "titolo");
        this.autore = richiediTesto(autore, "autore");
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    @Override
    public String toString() {
        return "[" + id + "] '" + titolo + "' di " + autore;
    }

    private static String richiediTesto(String valore, String campo) {
        if (valore == null || valore.isBlank()) {
            throw new IllegalArgumentException("Il campo " + campo + " non può essere vuoto.");
        }
        return valore.trim();
    }
}
