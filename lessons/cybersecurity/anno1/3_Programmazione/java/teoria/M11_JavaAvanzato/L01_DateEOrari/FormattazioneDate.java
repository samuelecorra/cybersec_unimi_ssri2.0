package M11_JavaAvanzato.L01_DateEOrari;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class FormattazioneDate {

    static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now(); // Prima inizializziamo una data+ora
        // Poi definiamo un formato personalizzato usando DateTimeFormatter:
        // mediante il metodo ofPattern, possiamo specificare il formato desiderato.

        // Questo esempio è il classico formato italiano: lunedì 17/11/2025 21:49
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("eeee dd/MM/yyyy HH:mm");

        String formatted = now.format(fmt1); // il metodo format applica il formato alla data/ora tramutandola in stringa
        System.out.println("Formattato (ITA): " + formatted);

        // Il formato americano standard invece è: Monday, November 17, 2025 09:49 PM
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("eeee, MMMM dd, yyyy hh:mm a");
        String formatted2 = now.format(fmt2);
        System.out.println("Formattato (USA): " + formatted2);
    }
}