package E2_gestionali.GestionaleVideotecaV2.Classi;

public class Film {

    // ATTRIBUTI
    private final int id;
    private final String titolo;
    private final String genere;

    // COSTRUTTORE
    public Film(int id, String titolo, String genere) {
        this.id = id;
        this.titolo = titolo;
        this.genere = genere;
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

}