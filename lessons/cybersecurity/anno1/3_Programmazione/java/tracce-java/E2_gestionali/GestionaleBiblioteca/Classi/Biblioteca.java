package E2_gestionali.GestionaleBiblioteca.Classi;

import java.util.*;

public class Biblioteca {
    private final List<Libro> libriDisponibili;
    private final List<Utente> utenti;

    public Biblioteca() {
        this.libriDisponibili = new ArrayList<Libro>();
        this.utenti = new ArrayList<Utente>();
    }

    public void aggiungiLibro(Libro libro) {
        libriDisponibili.add(libro);
    }

    public void registraUtente(Utente utente) {
        utenti.add(utente);
    }

    public boolean prestaLibro(int idLibro, int idUtente) {
        Libro libro = trovaLibroPerId(idLibro);
        Utente utente = trovaUtentePerId(idUtente);

        if (libro != null && utente != null) {
            utente.addLibro(libro);
            libriDisponibili.remove(libro);
            System.out.println("Libro: " + libro.toString() + " prestato a " + utente.toString());
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
            aggiungiLibro(restituito);
            System.out.println("Libro: " + restituito.toString() + " restituito da " + utente.toString());
            return true;
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
}

