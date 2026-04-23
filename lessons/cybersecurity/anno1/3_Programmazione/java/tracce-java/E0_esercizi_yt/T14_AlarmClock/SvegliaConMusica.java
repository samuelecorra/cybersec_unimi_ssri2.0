package E0_esercizi_yt.T14_AlarmClock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SvegliaConMusica {

    static void main() {

        // Java - Implementiamo una sveglia che suona una musica all'ora stabilita.

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); // Per poter parsare l'ora in un oggetto LocalTime

        LocalTime orarioSveglia = null;
        String percorsoCanzone = "C:/Users/nabis/cybersamu-gitlocker/1_PRIMO_ANNO/java/tracce-java/E0_esercizi_yt/T14_AlarmClock/sinteticoxstarshopping.wav"; // Inserisci qui il percorso del file audio da riprodurre


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

        AlarmClock sveglia = new AlarmClock(orarioSveglia, percorsoCanzone, sc);
        Thread threadSveglia = new Thread(sveglia);

        threadSveglia.start();

        // sc.close(); questo lo chiudiamo dall'altra classe perché per fermare la sveglia potremmo aver bisogno di input

    }
}
