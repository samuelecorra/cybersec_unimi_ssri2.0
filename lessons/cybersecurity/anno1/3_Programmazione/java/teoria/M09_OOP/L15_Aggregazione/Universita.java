package M09_OOP.L15_Aggregazione;

import java.util.ArrayList;
import java.util.List;

/*
 * ============================================================
 * CLASSE Universita
 * ============================================================
 * Rappresenta l'insieme globale:
 *  - Ha una lista di CORSI
 *  - Ha una lista di STUDENTI
 *
 * Anche qui parliamo di AGGREGAZIONE:
 *  - L'università "ha" studenti e corsi.
 *  - Ma uno studente può teoricamente "staccarsi" da questa università
 *    ed esistere (come oggetto) anche altrove.
 *  - Allo stesso modo, un corso può essere rimosso dall'università
 *    senza "distruggere" gli studenti oggetto.
 */
public class Universita {

    private String nome;
    private List<Studente> studenti;
    private List<CorsoUniversitario> corsi;

    public Universita(String nome) {
        this.nome = nome;
        this.studenti = new ArrayList<>();
        this.corsi = new ArrayList<>();
    }

    // ================= GESTIONE STUDENTI =================

    public void aggiungiStudente(Studente s) {
        if (!studenti.contains(s)) {
            studenti.add(s);
            System.out.println("Studente " + s + " aggiunto all'università " + nome + ".");
        } else {
            System.out.println("Lo studente " + s + " è già registrato nell'università.");
        }
    }

    public void rimuoviStudente(Studente s) {
        if (studenti.remove(s)) {
            System.out.println("Studente " + s + " rimosso dall'università " + nome + ".");
        } else {
            System.out.println("Lo studente " + s + " non è presente nell'università.");
        }
    }

    // ================= GESTIONE CORSI =================

    public void aggiungiCorso(CorsoUniversitario c) {
        if (!corsi.contains(c)) {
            corsi.add(c);
            System.out.println("Corso " + c + " aggiunto all'università " + nome + ".");
        } else {
            System.out.println("Il corso " + c + " è già presente nell'università.");
        }
    }

    public void rimuoviCorso(CorsoUniversitario c) {
        if (corsi.remove(c)) {
            System.out.println("Corso " + c + " rimosso dall'università " + nome + ".");
        } else {
            System.out.println("Il corso " + c + " non è presente nell'università.");
        }
    }

    // ================= METODI DI STAMPA =================

    public void stampaStudenti() {
        System.out.println("\n=== ELENCO STUDENTI DELL'UNIVERSITÀ " + nome + " ===");
        if (studenti.isEmpty()) {
            System.out.println("Nessuno studente registrato.");
        } else {
            for (Studente s : studenti) {
                System.out.println("- " + s);
            }
        }
    }

    public void stampaCorsi() {
        System.out.println("\n=== ELENCO CORSI DELL'UNIVERSITÀ " + nome + " ===");
        if (corsi.isEmpty()) {
            System.out.println("Nessun corso attivo.");
        } else {
            for (CorsoUniversitario c : corsi) {
                System.out.println("- " + c);
            }
        }
    }

    public String getNome() {
        return nome;
    }
}
