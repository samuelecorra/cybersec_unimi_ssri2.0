package E2_gestionali.GestionaleBiblioteca.Classi;

import java.util.*;

public class Biblioteca {
    private final List<Libro> libriDisponibili;
    private final List<Utente> utenti;

    public Biblioteca() {
        this.libriDisponibili = new ArrayList<Libro>();
        this.utenti = new ArrayList<Utente>();
    }

    public boolean aggiungiLibro(Libro libro) {
        if (libro == null || libroRegistrato(libro.getId())) {
            return false;
        }
        libriDisponibili.add(libro);
        return true;
    }

    public boolean registraUtente(Utente utente) {
        if (utente == null || trovaUtentePerId(utente.getId()) != null) {
            return false;
        }
        utenti.add(utente);
        return true;
    }

    public boolean prestaLibro(int idLibro, int idUtente) {
        Libro libro = trovaLibroPerId(idLibro);
        Utente utente = trovaUtentePerId(idUtente);

        if (libro != null && utente != null) {
            utente.addLibro(libro);
            libriDisponibili.remove(libro);
            return true;
        } else {
            return false;
        }
    }

    public boolean restituisciLibro(int idLibro, int idUtente) {
        Utente utente = trovaUtentePerId(idUtente);
        if (utente == null) {
            return false;
        }
        Libro restituito = utente.removeLibro(idLibro);
        if (restituito != null) {
            return aggiungiLibro(restituito);
        } else {
            return false;
        }
    }

    private Libro trovaLibroPerId(int idLibro) {
        for (Libro libro : libriDisponibili) {
            if (libro.getId() == idLibro) {
                return libro;
            }
        }
        return null;
    }

    private Utente trovaUtentePerId(int idUtente) {
        for (Utente utente : utenti) {
            if (utente.getId() == idUtente) {
                return utente;
            }
        }
        return null;
    }

    private boolean libroRegistrato(int idLibro) {
        if (trovaLibroPerId(idLibro) != null) {
            return true;
        }
        return utenti.stream().anyMatch(utente -> utente.haLibro(idLibro));
    }
}
