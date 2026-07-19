package E2_gestionali.GestionaleVideotecaV2.Classi;

public class Film {

    // ATTRIBUTI
    private final int id;
    private final String titolo;
    private final String genere;

    // COSTRUTTORE
    public Film(int id, String titolo, String genere) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID della copia deve essere positivo.");
        }
        this.id = id;
        this.titolo = richiediTesto(titolo, "titolo");
        this.genere = richiediTesto(genere, "genere");
    }

    // GETTERS
    public int getId() { return id; }
    public String getTitolo() { return titolo; }
    public String getGenere() { return genere; }

    // COMODA STAMPA PER RAPPRESENTARE LA SINGOLA COPIA FISICA DI UN CERTO FILM
    @Override
    public String toString() {
        return "Film{id=" + id + ", titolo='" + titolo + "', genere='" + genere + "'}";
    }

    private static String richiediTesto(String valore, String campo) {
        if (valore == null || valore.isBlank()) {
            throw new IllegalArgumentException("Il campo " + campo + " non può essere vuoto.");
        }
        return valore.trim();
    }
}
