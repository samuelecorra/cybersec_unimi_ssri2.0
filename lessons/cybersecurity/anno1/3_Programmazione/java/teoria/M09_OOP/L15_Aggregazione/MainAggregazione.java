package M09_OOP.L15_Aggregazione;

/*
 * ============================================================
 * LEZIONE 15 – AGGREGAZIONE
 * ============================================================
 *
 * DEFINIZIONE (in stile commento che hai nello screenshot):
 *
 *  Aggregazione = rappresenta una relazione "has-a" tra oggetti.
 *  Un oggetto contiene uno o più altri oggetti come parte della
 *  propria struttura, MA gli oggetti contenuti possono esistere
 *  indipendentemente dal contenitore.
 *
 *  Esempio classico:
 *    - Un'Università ha molti Studenti e molti Corsi.
 *    - Gli Studenti esistono anche se un corso viene eliminato.
 *    - Gli Studenti vengono creati all'esterno e poi AGGREGATI
 *      ai corsi o all'università.
 *
 * OBIETTIVO DEL MAIN:
 *  - Creare alcuni studenti
 *  - Creare alcuni corsi universitari
 *  - Creare un'università
 *  - Agganciare studenti e corsi all'università (has-a)
 *  - Iscrivere studenti ai corsi (has-a)
 *  - Mostrare che, anche se elimino un corso, gli studenti
 *    continuano a esistere come oggetti separati.
 */
public class MainAggregazione {

    static void main(String[] args) {

        // 1. Creiamo alcuni studenti (esistono da soli, senza corsi)
        Studente s1 = new Studente("Samuele", "S12345");
        Studente s2 = new Studente("Erika", "E54321");
        Studente s3 = new Studente("Luca", "L11111");

        // 2. Creiamo alcuni corsi universitari
        CorsoUniversitario programmazione = new CorsoUniversitario("Programmazione 1", "INF101");
        CorsoUniversitario matematica = new CorsoUniversitario("Matematica del Discreto", "MAT202");

        // 3. Creiamo l'università
        Universita uni = new Universita("Università degli Studi di Milano");

        System.out.println("=== CREAZIONE ENTITÀ ===");
        // Aggiungiamo studenti e corsi all'università (AGGREGAZIONE)
        uni.aggiungiStudente(s1);
        uni.aggiungiStudente(s2);
        uni.aggiungiStudente(s3);

        uni.aggiungiCorso(programmazione);
        uni.aggiungiCorso(matematica);

        // 4. Iscriviamo gli studenti ai corsi
        System.out.println("\n=== ISCRIZIONE AI CORSI ===");
        programmazione.iscriviStudente(s1);
        programmazione.iscriviStudente(s2);

        matematica.iscriviStudente(s1);
        matematica.iscriviStudente(s3);

        // 5. Stampiamo la situazione attuale
        uni.stampaStudenti();
        uni.stampaCorsi();

        programmazione.stampaStudentiIscritti();
        matematica.stampaStudentiIscritti();

        // 6. Dimostrazione didattica:
        //    RIMUOVO un corso dall'università,
        //    ma gli studenti OGGETTO esistono ancora.
        System.out.println("=== RIMOZIONE DEL CORSO 'Programmazione 1' DALL'UNIVERSITÀ ===");
        uni.rimuoviCorso(programmazione);

        // Anche se il corso è stato rimosso dalla lista corsi
        // l'oggetto "programmazione" e gli oggetti "Studente" esistono ancora in memoria.
        uni.stampaCorsi();  // non contiene più "Programmazione 1"

        // Ma posso ancora usare l'oggetto "programmazione" qui:
        System.out.println("\nStato del corso 'Programmazione 1' dopo la rimozione dall'università:");
        programmazione.stampaStudentiIscritti(); // la lista iscritti è ancora lì

        // E ovviamente gli studenti esistono ancora:
        System.out.println("Gli oggetti Studente sono ancora vivi:");
        System.out.println("- " + s1);
        System.out.println("- " + s2);
        System.out.println("- " + s3);

        System.out.println("\n=== FINE LEZIONE SULL'AGGREGAZIONE ===");
    }
}
