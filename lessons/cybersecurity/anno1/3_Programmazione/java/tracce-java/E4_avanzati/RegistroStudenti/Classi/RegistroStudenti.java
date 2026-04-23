package E4_avanzati.RegistroStudenti.Classi;

import java.util.HashMap;
import java.util.Map;

public class RegistroStudenti {

    // Chiave: matricola, Valore: oggetto Studente
    private final Map<Integer, Studente> studenti;

    public RegistroStudenti() {
        this.studenti = new HashMap<>();
    }

    // Aggiunge uno studente nuovo, se la matricola non esiste già
    public boolean aggiungiStudente(int matricola, String nome) {
        if (studenti.containsKey(matricola)) {
            return false; // già presente
        }
        Studente s = new Studente(matricola, nome);
        studenti.put(matricola, s);
        return true;
    }

    // Aggiunge un voto a uno studente esistente
    public boolean aggiungiVotoAStudente(int matricola, int voto) {
        Studente s = studenti.get(matricola);
        if (s == null) {
            return false;
        }
        s.aggiungiVoto(voto);
        return true;
    }

    // Restituisce lo studente con quella matricola (null se non esiste)
    public Studente cercaStudente(int matricola) {
        return studenti.get(matricola);
    }

    // Rimuove uno studente dal registro
    public boolean rimuoviStudente(int matricola) {
        if (!studenti.containsKey(matricola)) {
            return false;
        }
        studenti.remove(matricola);
        return true;
    }

    // Stampa tutti gli studenti del registro
    public void stampaRegistro() {
        if (studenti.isEmpty()) {
            System.out.println("Il registro è vuoto.");
            return;
        }
        System.out.println("=== REGISTRO STUDENTI ===");
        for (Studente s : studenti.values()) {
            System.out.println(s);
        }
        System.out.println("=========================");
    }

    // Restituisce true se il registro è vuoto (può tornare utile)
    public boolean isVuoto() {
        return studenti.isEmpty();
    }
}

