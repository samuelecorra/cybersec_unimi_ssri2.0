package E2_gestionali.GestionaleVideotecaV2.Classi;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private final int id;
    private final String nome;
    private final List<Film> filmNoleggiati;

    // COSTRUTTORE
    public Cliente(int id, String nome) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID del cliente deve essere positivo.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome del cliente non può essere vuoto.");
        }
        this.id = id;
        this.nome = nome.trim();
        this.filmNoleggiati = new ArrayList<>();
    }

    // GETTERS
    public int getId() { return id; }
    public String getNome() { return nome; }
    public List<Film> getFilmNoleggiati() { return List.copyOf(filmNoleggiati); }
    public int getNumeroFilmNoleggiati() { return filmNoleggiati.size(); }

    // METODI

    // Aggiunge una copia fisica alla lista dei film noleggiati dal cliente
    public void aggiungiAiNoleggiati(Film film) {
        if (film == null) {
            throw new IllegalArgumentException("Il film noleggiato non può essere nullo.");
        }
        filmNoleggiati.add(film);
    }

    /*
     * Restituisce il film con l'id specificato, se presente tra i noleggiati.
     * Ritorna:
     *  - l'oggetto Film se trovato e rimosso
     *  - null se il cliente non ha una copia con quell'id
     */
    public Film restituisciFilm(int idFilm) {
        for (int i = 0; i < filmNoleggiati.size(); i++) {
            Film f = filmNoleggiati.get(i);
            if (f.getId() == idFilm) {
                return filmNoleggiati.remove(i);
            }
        }
        return null;
    }

    // COMODA STAMPA PER RAPPRESENTARE UN CERTO CLIENTE
    @Override
    public String toString() {
        return "Cliente{id=" + id +
                ", nome='" + nome + '\'' +
                ", numeroFilmNoleggiati=" + getNumeroFilmNoleggiati() +
                ", filmNoleggiati=" + filmNoleggiati +
                '}';
    }
}
