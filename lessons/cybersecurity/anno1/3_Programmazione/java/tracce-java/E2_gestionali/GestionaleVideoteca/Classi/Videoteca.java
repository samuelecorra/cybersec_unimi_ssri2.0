package E2_gestionali.GestionaleVideoteca.Classi;

import java.util.*;

public class Videoteca {
    private final List<Film> catalogoFilm;
    private final List<Cliente> clienti;

    public Videoteca() {
        this.catalogoFilm = new ArrayList<>(); // lista film vuota
        this.clienti = new ArrayList<>(); // lista clienti vuota
    }

    public void aggiungiFilm(Film film){
        for (Film f : catalogoFilm) {
            if (f.getId() == film.getId()) {
                if (f.getTitolo().equals(film.getTitolo()) && f.getGenere().equals(film.getGenere())) {
                    f.incrementaCopie();
                    System.out.println("Aggiunta una copia della pellicola. Copie del film: " + f.getCopie());
                    return;
                } else {
                    System.out.println("Titolo o genere diversi dal film già registrato con questo ID");
                    return;
                }
            }
        }
        catalogoFilm.add(film);
        System.out.println("Film aggiunto: " + film.getTitolo() + " (" + film.getGenere() + ")");
    }

    public void registraCliente(Cliente cliente){
        clienti.add(cliente);
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
        film.decrementaCopie();
        System.out.println("Copie del film: " + film.getCopie());
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
            System.out.println("Copie del film: " + film.getCopie());
        }
        return restituito;
    }
}

