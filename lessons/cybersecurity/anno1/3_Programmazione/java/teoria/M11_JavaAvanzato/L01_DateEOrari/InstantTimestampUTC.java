package M11_JavaAvanzato.L01_DateEOrari;

import java.time.Instant;

public class InstantTimestampUTC {

    // Universal Coordinated Time (UTC) è lo standard di tempo primario
    // usato in tutto il mondo. Non cambia con le stagioni (non c'è
    // l'ora legale) ed è equivalente al vecchio GMT (Greenwich Mean Time).
    // In Java, la classe Instant rappresenta un timestamp
    // in UTC, cioè un punto preciso nel tempo indipendentemente dal fuso orario.

    static void main(String[] args) {

        Instant now = Instant.now();  // sempre UTC

        System.out.println("Timestamp UTC: " + now);

        // Confronti
        Instant later = now.plusSeconds(3600);
        System.out.println("Tra 1 ora (UTC): " + later);
    }
}