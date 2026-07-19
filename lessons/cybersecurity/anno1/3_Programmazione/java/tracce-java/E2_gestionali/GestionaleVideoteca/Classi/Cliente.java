package E2_gestionali.GestionaleVideoteca.Classi;


import java.util.*;

public class Cliente {
    private final int id;
    private final String nome;
    private final List<Film> filmNoleggiati;

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

    public int getId() {
        return id;
    }

    public void noleggiaFilm(Film film) {
        filmNoleggiati.add(film);
    }

    public boolean restituisciFilm(int idFilm) {
        for (int i = 0; i < filmNoleggiati.size(); i++) {
            if (filmNoleggiati.get(i).getId() == idFilm) {
                filmNoleggiati.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', filmNoleggiati=" + filmNoleggiati + "}";
    }
}
