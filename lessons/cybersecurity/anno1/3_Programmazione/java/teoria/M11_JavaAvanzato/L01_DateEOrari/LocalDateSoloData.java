package M11_JavaAvanzato.L01_DateEOrari;

import java.time.LocalDate;

public class LocalDateSoloData {
    static void main(String[] args) {

        LocalDate today = LocalDate.now();        // data di oggi
        LocalDate specific = LocalDate.of(2025, 11, 17);

        System.out.println("Oggi: " + today);
        System.out.println("Data specifica: " + specific);

        // Operazioni comuni

        // 1. Operazioni di somma e sottrazione di giorni, settimane, mesi, anni
        System.out.println("Ieri: " + LocalDate.now().minusDays(1)); // O usiamo now
        System.out.println("Domani: " + today.plusDays(1)); // o riassumiamo con today che è uguale ma è una variabile più leggibile

        System.out.println("Tra esattamente una settimana: " + today.plusWeeks(1));
        System.out.println("Esattamente settimana scorsa: " + today.minusWeeks(1));

        System.out.println("Mese prossimo: " + today.plusMonths(1));
        System.out.println("Mese scorso: " + today.minusMonths(1));

        System.out.println("Anno scorso: " + today.minusYears(1));
        System.out.println("Anno prossimo: " + today.plusYears(1));

        // 2. Ottenere singoli campi della data:
        System.out.println("Anno: " + today.getYear()); // Es. 2025

        System.out.println("Mese (numero): " + today.getMonthValue()); // 1-12
        System.out.println("Mese (nome): " + today.getMonth()); // Stampa in inglese JANUARY, FEBRUARY, ecc.

        System.out.println("Giorno del mese: " + today.getDayOfMonth()); // 1-31
        System.out.println("Giorno della settimana: " + today.getDayOfWeek()); // Stampa in inglese MONDAY, TUESDAY, ecc.

    }
}
