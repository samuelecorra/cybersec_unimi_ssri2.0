package E2_gestionali.GestionaleBiblioteca.Classi;

public class Libro {
    private final int id;
    private final String titolo;
    private final String autore;

    public Libro(int id, String titolo, String autore) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
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
}
