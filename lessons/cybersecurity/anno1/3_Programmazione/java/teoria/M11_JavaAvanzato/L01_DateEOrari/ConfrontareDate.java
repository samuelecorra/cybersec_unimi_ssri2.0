package M11_JavaAvanzato.L01_DateEOrari;

import java.time.*;

public class ConfrontareDate {

    static void main() {

        LocalDateTime date1 = LocalDateTime.of(2023, 5, 20, 14, 30);
        LocalDateTime date2 = LocalDateTime.of(2024, 6, 15, 10, 0);

        if (date1.isBefore(date2)) {
            System.out.println("date1 è prima di date2");
        } else if (date1.isAfter(date2)) {
            System.out.println("date1 è dopo date2");
        } else if(date1.isEqual(date2)) {
            System.out.println("date1 è uguale a date2");
        }
    }
}
