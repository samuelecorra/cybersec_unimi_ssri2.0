package E0_esercizi_yt.T14_AlarmClock;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SvegliaConMusica {

    public static void main(String[] args) {

        // Java - Implementiamo una sveglia che suona una musica all'ora stabilita.

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); // Per poter parsare l'ora in un oggetto LocalTime

        LocalTime orarioSveglia = null;
        String percorsoCanzone = args.length > 0
                ? args[0]
                : Path.of("lessons", "cybersecurity", "anno1", "3_Programmazione",
                        "java", "tracce-java", "E0_esercizi_yt", "T14_AlarmClock",
                        "sinteticoxstarshopping.wav").toString();


        while(orarioSveglia == null){
            try{
                System.out.print("Inserisci l'orario della sveglia (HH:mm:ss): ");
                String tempoInput = sc.nextLine();

                orarioSveglia = LocalTime.parse(tempoInput, dtf);
                System.out.println("Sveglia impostata per le: " + orarioSveglia);
            }
            catch(DateTimeParseException ex){
                System.out.println("Formato orario non valido. Usa (HH:mm:ss)");
            }
        }

        LocalDateTime adesso = LocalDateTime.now();
        LocalDateTime prossimaSveglia = LocalDateTime.of(LocalDate.now(), orarioSveglia);
        if (!prossimaSveglia.isAfter(adesso)) {
            prossimaSveglia = prossimaSveglia.plusDays(1);
        }

        AlarmClock sveglia = new AlarmClock(prossimaSveglia, percorsoCanzone, sc);
        Thread threadSveglia = new Thread(sveglia);

        threadSveglia.start();
        try {
            threadSveglia.join();
        } catch (InterruptedException e) {
            threadSveglia.interrupt();
            Thread.currentThread().interrupt();
        } finally {
            sc.close();
        }

    }
}
