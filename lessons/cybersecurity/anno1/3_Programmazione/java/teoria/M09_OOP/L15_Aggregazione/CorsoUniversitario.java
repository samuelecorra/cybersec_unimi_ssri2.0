package M09_OOP.L15_Aggregazione;

import java.util.ArrayList;
import java.util.List;

/*
 * ============================================================
 * CLASSE CorsoUniversitario
 * ============================================================
 * Questa classe rappresenta un SINGOLO corso (es. "Programmazione 1").
 *
 * RELAZIONE DI AGGREGAZIONE:
 *  - Un corso "ha" una COLLEZIONE di studenti iscritti.
 *  - Il corso CONTIENE riferimenti agli studenti (has-a).
 *  - Se il corso viene eliminato, gli STUDENTI continuano ad esistere
 *    come oggetti indipendenti.
 *
 * → Questo è l'esempio tipico di AGGREGAZIONE.
 */
public class CorsoUniversitario {

    private String nomeCorso;
    private String codiceCorso;
    private List<Studente> studentiIscritti;

    public CorsoUniversitario(String nomeCorso, String codiceCorso) {
        this.nomeCorso = nomeCorso;
        this.codiceCorso = codiceCorso;
        this.studentiIscritti = new ArrayList<>();
    }

    /*
     * Iscrive uno studente al corso.
     * NOTA: lo studente deve essere stato creato PRIMA, fuori dal corso.
     * Il corso si limita a "tenerne un riferimento".
     */
    public void iscriviStudente(Studente s) {
        if (!studentiIscritti.contains(s)) {
            studentiIscritti.add(s);
            System.out.println("Studente " + s + " iscritto al corso " + nomeCorso + ".");
        } else {
            System.out.println("Lo studente " + s + " è già iscritto al corso " + nomeCorso + ".");
        }
    }

    public void disiscriviStudente(Studente s) {
        if (studentiIscritti.remove(s)) {
            System.out.println("Studente " + s + " disiscritto dal corso " + nomeCorso + ".");
        } else {
            System.out.println("Lo studente " + s + " non era iscritto al corso " + nomeCorso + ".");
        }
    }

    public void stampaStudentiIscritti() {
        System.out.println("=== Studenti iscritti al corso: " + nomeCorso + " ===");
        if (studentiIscritti.isEmpty()) {
            System.out.println("Nessuno studente iscritto.");
        } else {
            for (Studente s : studentiIscritti) {
                System.out.println("- " + s);
            }
        }
        System.out.println();
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public String getCodiceCorso() {
        return codiceCorso;
    }

    public List<Studente> getStudentiIscritti() {
        return studentiIscritti;
    }

    @Override
    public String toString() {
        return nomeCorso + " (" + codiceCorso + ")";
    }
}
