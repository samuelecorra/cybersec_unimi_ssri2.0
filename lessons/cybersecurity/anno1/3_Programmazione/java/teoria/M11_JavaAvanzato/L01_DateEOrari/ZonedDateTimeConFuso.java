package M11_JavaAvanzato.L01_DateEOrari;

import java.time.*;

public class ZonedDateTimeConFuso {

    static void main(String[] args) {

        // Nel mondo reale però, le date e gli orari devono essere accompagnate dal fuso orario corrispondente:
        ZonedDateTime nowMilano = ZonedDateTime.now(ZoneId.of("Europe/Rome"));
        ZonedDateTime nowTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        System.out.println("Ora Roma:  " + nowMilano);
        System.out.println("Ora Tokyo: " + nowTokyo);

        // Conversioni di fuso: se abbiamo una data/ora in un fuso orario,
        // possiamo convertirla in un altro fuso orario usando withZoneSameInstant e poi sempre passanod
        // il ZoneId desiderato.
        ZonedDateTime milanoToTokyo = nowMilano.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("Roma convertita in Tokyo: " + milanoToTokyo);

    }
}