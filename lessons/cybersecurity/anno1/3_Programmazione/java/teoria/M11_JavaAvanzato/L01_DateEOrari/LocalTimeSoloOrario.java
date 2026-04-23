package M11_JavaAvanzato.L01_DateEOrari;

import java.time.LocalTime;

public class LocalTimeSoloOrario {

    static void main(String[] args) {

        LocalTime now = LocalTime.now();
        LocalTime specific = LocalTime.of(14, 30, 15);

        System.out.println("Ora attuale: " + now);
        System.out.println("Orario specifico: " + specific);

        System.out.println("Tra 1 ora: " + now.plusHours(1));
        System.out.println("Tra 30 min: " + now.plusMinutes(30));
        System.out.println("15 secondi fa: " + now.minusSeconds(15) );
        System.out.println("15 secondi dopo: " + now.plusSeconds(15) );
        // Come si nota, tutte e 3 le Local class (LocalDate, LocalTime, LocalDateTime)
        // hanno metodi simili per operazioni di somma e sottrazione.
    }
}

