package E2_gestionali.GestionaleDischeria.Classi;

public class Disco {
    private final int id;
    private final String titolo;
    private final String artista;

    public Disco(int id, String titolo, String artista) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID del disco deve essere positivo.");
        }
        this.id = id;
        this.titolo = richiediTesto(titolo, "titolo");
        this.artista = richiediTesto(artista, "artista");
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Disco{id=" + id + ", titolo='" + titolo + "', artista='" + artista + "'}";
    }

    private static String richiediTesto(String valore, String campo) {
        if (valore == null || valore.isBlank()) {
            throw new IllegalArgumentException("Il campo " + campo + " non può essere vuoto.");
        }
        return valore.trim();
    }
}
