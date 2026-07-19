package M11_JavaAvanzato.L01_DateEOrari;

import java.time.Instant;

public class InstantTimestampUTC {

    // Coordinated Universal Time (UTC, in italiano Tempo Coordinato Universale)
    // è lo standard di tempo primario
    // usato in tutto il mondo. Non cambia con le stagioni (non c'è
    // l'ora legale). UTC e GMT sono spesso usati come sinonimi negli orari civili,
    // ma UTC è uno standard temporale atomico mentre GMT è un fuso/convenzione.
    // In Java, Instant rappresenta un punto preciso sulla linea temporale,
    // indipendente da qualsiasi fuso orario.

    public static void main(String[] args) {

        Instant now = Instant.now();  // sempre UTC

        System.out.println("Timestamp UTC: " + now);

        // Confronti
        Instant later = now.plusSeconds(3600);
        System.out.println("Tra 1 ora (UTC): " + later);
    }
}
