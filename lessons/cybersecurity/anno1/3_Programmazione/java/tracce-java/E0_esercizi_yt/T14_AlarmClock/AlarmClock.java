package E0_esercizi_yt.T14_AlarmClock;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable {

    private final LocalTime orarioSveglia;
    private final String percorsoCanzone;
    private final Scanner sc;

    public AlarmClock(LocalTime orarioSveglia, String percorsoCanzone, Scanner scanner) {
        this.orarioSveglia = orarioSveglia;
        this.percorsoCanzone = percorsoCanzone;
        this.sc = scanner;
    }

    @Override
    public void run() {
        while (LocalTime.now().isBefore(orarioSveglia)) {
            try {
                Thread.sleep(1000); // Controlla ogni secondo

                LocalTime ora = LocalTime.now();
                System.out.printf("\rOra attuale: %02d:%02d:%02d", ora.getHour(), ora.getMinute(), ora.getSecond());

            } catch (InterruptedException e) {
                System.out.println("Sveglia interrotta. Buona giornata, principessa!");
            }
        }

        suonaCanzone(percorsoCanzone);
         // Toolkit.getDefaultToolkit().beep(); // Suona un beep come segnale acustico (quello di default dell'SO)
    }

    private void suonaCanzone(String percorsoCanzone) {

        File fileAudio = new File(percorsoCanzone);

        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileAudio)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            System.out.print("\nPremi INVIO per fermare la sveglia...");
            sc.nextLine(); // Attende l'input dell'utente
            clip.stop();
            clip.close();
        }
        catch(UnsupportedAudioFileException e){
            System.out.println("Formato audio non supportato: " + e.getMessage());
        }
        catch(LineUnavailableException e){
            System.out.println("Errore durante la riproduzione della canzone: " + e.getMessage());
        }
        catch(IOException e){
            System.out.println("Errore di I/O con il file audio: " + e.getMessage());
        }
    }
}
