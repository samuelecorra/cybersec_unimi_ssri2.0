package E2_gestionali.GestionaleVideoteca.Classi;


import java.util.*;

public class Cliente {
    private final int id;
    private final String nome;
    private final List<Film> filmNoleggiati;

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.filmNoleggiati = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void noleggiaFilm(Film film) {
        filmNoleggiati.add(film);
    }

    public boolean restituisciFilm(int idFilm) {
        return filmNoleggiati.removeIf(f -> f.getId() == idFilm);
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', filmNoleggiati=" + filmNoleggiati + "}";
    }
}
