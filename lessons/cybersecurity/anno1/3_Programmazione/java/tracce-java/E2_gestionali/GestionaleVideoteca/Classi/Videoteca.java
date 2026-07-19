package E2_gestionali.GestionaleVideoteca.Classi;

import java.util.*;

public class Videoteca {
    private final List<Film> catalogoFilm;
    private final List<Cliente> clienti;

    public Videoteca() {
        this.catalogoFilm = new ArrayList<>(); // lista film vuota
        this.clienti = new ArrayList<>(); // lista clienti vuota
    }

    public boolean aggiungiFilm(Film film){
        if (film == null) return false;
        for (Film f : catalogoFilm) {
            if (f.getId() == film.getId()) {
                if (f.getTitolo().equals(film.getTitolo()) && f.getGenere().equals(film.getGenere())) {
                    f.incrementaCopie();
                    return true;
                }
                return false;
            }
        }
        catalogoFilm.add(film);
        return true;
    }

    public boolean registraCliente(Cliente cliente){
        if (cliente == null || trovaCliente(cliente.getId()) != null) {
            return false;
        }
        clienti.add(cliente);
        return true;
    }

    public boolean noleggiaFilm(int idFilm, int idCliente){
        Film film = null;
        for (Film f : catalogoFilm){
            if (f.getId() == idFilm){
                film = f;
                break;
            }
        }
        if(film == null) return false;
        if(film.getCopie() <= 0) return false;
        Cliente cliente = null;
        for (Cliente c : clienti){
            if (c.getId() == idCliente){
                cliente = c;
                break;
            }
        }
        if(cliente == null) return false;
        if (!film.decrementaCopie()) return false;
        cliente.noleggiaFilm(film);
        return true;
    }

    public boolean restituisciFilm(int idFilm, int idCliente){
        Film film = null;
        for (Film f : catalogoFilm){
            if (f.getId() == idFilm){
                film = f;
                break;
            }
        }
        if(film == null) return false;
        Cliente cliente = null;
        for(Cliente c : clienti){
            if(c.getId() == idCliente){
                cliente = c;
                break;
            }
        }
        if(cliente == null) return false;
        boolean restituito = cliente.restituisciFilm(idFilm);
        if (restituito) {
            film.incrementaCopie();
        }
        return restituito;
    }

    private Cliente trovaCliente(int idCliente) {
        for (Cliente cliente : clienti) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }
}
