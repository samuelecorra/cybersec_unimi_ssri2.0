package M09_OOP.L15_Aggregazione;

/*
 * ============================================================
 * CLASSE Studente
 * ============================================================
 * Questa classe rappresenta un singolo studente universitario.
 *
 * NOTA IMPORTANTE (AGGREGAZIONE):
 *  - Lo studente ESISTE indipendentemente dai corsi a cui è iscritto.
 *  - Posso creare uno studente senza assegnarlo subito a nessun corso.
 *  - Può cambiare corso, disiscriversi, ecc., ma continua ad esistere.
 *
 * Questo è fondamentale per capire l'AGGREGAZIONE:
 *  il "contenuto" (Studente) ha un suo ciclo di vita autonomo
 *  rispetto al "contenitore" (CorsoUniversitario).
 */
public class Studente {

    private String nome;
    private String matricola;

    public Studente(String nome, String matricola) {
        this.nome = nome;
        this.matricola = matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricola() {
        return matricola;
    }

    @Override
    public String toString() {
        return nome + " (matricola: " + matricola + ")";
    }
}
