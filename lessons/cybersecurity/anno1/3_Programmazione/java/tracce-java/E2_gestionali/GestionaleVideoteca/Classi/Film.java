package E2_gestionali.GestionaleVideoteca.Classi;


public class Film {
    private final int id;
    private int copie;
    private final String titolo;
    private final String genere;

    public Film(int id, int copie, String titolo, String genere) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID del film deve essere positivo.");
        }
        if (copie <= 0) {
            throw new IllegalArgumentException("Il numero iniziale di copie deve essere positivo.");
        }
        this.id = id;
        this.copie = copie;
        this.titolo = richiediTesto(titolo, "titolo");
        this.genere = richiediTesto(genere, "genere");
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getGenere() {
        return genere;
    }

    // gestione copie

    public int getCopie() {
        return copie;
    }

    public void incrementaCopie() {
        copie++;
    }

    public boolean decrementaCopie() {
        if (copie > 0) {
            copie--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Film{id=" + id + ", titolo='" + titolo + "', genere='" + genere + "', copie=" + copie + "}";
    }

    private static String richiediTesto(String valore, String campo) {
        if (valore == null || valore.isBlank()) {
            throw new IllegalArgumentException("Il campo " + campo + " non può essere vuoto.");
        }
        return valore.trim();
    }
}
