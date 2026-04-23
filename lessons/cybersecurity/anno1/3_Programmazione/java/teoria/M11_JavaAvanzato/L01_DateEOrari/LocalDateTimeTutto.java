package M11_JavaAvanzato.L01_DateEOrari;

import java.time.LocalDateTime;

public class LocalDateTimeTutto {

    static void main(String[] args) {

        // Analogamente a LocalDate e LocalTime,
        // LocalDateTime combina data e orario in un'unica classe.
        // Non ha fuso orario (vedi ZonedDateTime per quello).
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime specific = LocalDateTime.of(2025, 11, 17, 15, 30, 45);

        System.out.println("Adesso: " + now); // 2025-11-17T21:08:36.634402600
        System.out.println("Data+ora specifica: " + specific); // 2025-11-17T15:30:45

        // Operazioni
        System.out.println("Tra 24 ore: " + now.plusDays(1)); // 2025-11-18T21:08:36.634402600
        System.out.println("Un'ora fa: " + now.minusHours(1)); // 2025-11-17T21:08:36.634402600
        // E così via per settimane, mesi, anni, minuti, secondi
        System.out.println("Tra 10 minuti: " + now.plusMinutes(10)); // 2025-11-17T21:08:36.634402600
        System.out.println("30 secondi fa: " + now.minusSeconds(30)); // 2025-11-17T21:08:36.634402600

    }
}
