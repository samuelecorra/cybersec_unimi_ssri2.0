package E2_gestionali.GestionaleVideoteca.Classi;


public class Film {
    private final int id;
    private int copie;
    private final String titolo;
    private final String genere;

    public Film(int id, int copie, String titolo, String genere) {
        this.id = id;
        this.copie = copie;
        this.titolo = titolo;
        this.genere = genere;
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

    public void decrementaCopie() {
        if (copie > 0) {
            copie--;
        } else {
            System.out.println("Nessuna copia disponibile per il decremento");
        }
    }

    @Override
    public String toString() {
        return "Film{id=" + id + ", titolo='" + titolo + "', genere='" + genere + "', copie='" + copie + "'}";
    }
}

