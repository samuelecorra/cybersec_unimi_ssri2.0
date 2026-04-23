package E2_gestionali.GestionaleVideotecaV2.Classi;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private final int id;
    private final String nome;
    private final List<Film> filmNoleggiati;
    private int numeroFilmNoleggiati;

    // COSTRUTTORE
    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.filmNoleggiati = new ArrayList<>();
        this.numeroFilmNoleggiati = 0;
    }

    // GETTERS
    public int getId() { return id; }
    public String getNome() { return nome; }
    public List<Film> getFilmNoleggiati() { return filmNoleggiati; }
    public int getNumeroFilmNoleggiati() { return numeroFilmNoleggiati; }

    // METODI

    // Aggiunge una copia fisica alla lista dei film noleggiati dal cliente
    public void aggiungiAiNoleggiati(Film film) {
        filmNoleggiati.add(film);
        numeroFilmNoleggiati++;
    }

    /*
     * Restituisce il film con l'id specificato, se presente tra i noleggiati.
     * Ritorna:
     *  - l'oggetto Film se trovato e rimosso
     *  - null se il cliente non ha una copia con quell'id
     */
    public Film restituisciFilm(int idFilm) {
        for (Film f : filmNoleggiati) {
            if (f.getId() == idFilm) {
                filmNoleggiati.remove(f);
                numeroFilmNoleggiati--;
                return f;
            }
        }
        return null;
    }

    // COMODA STAMPA PER RAPPRESENTARE UN CERTO CLIENTE
    @Override
    public String toString() {
        return "Cliente{id=" + id +
                ", nome='" + nome + '\'' +
                ", numeroFilmNoleggiati=" + numeroFilmNoleggiati +
                ", filmNoleggiati=" + filmNoleggiati +
                '}';
    }
}
