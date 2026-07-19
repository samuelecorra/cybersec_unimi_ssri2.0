package E0_esercizi_yt.T11_MusicPlayers.MusicPlayerV2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/*
 * ============================================================
 * MusicPlayerApp
 * ============================================================
 *
 * Piccola applicazione da console che:
 *  - crea un MusicPlayer
 *  - mostra un menù testuale
 *  - permette di:
 *      1) suonare dall'inizio
 *      2) mettere in pausa
 *      3) riprendere
 *      4) fermare
 *      5) attivare loop
 *      6) disattivare loop
 *      0) uscire
 *
 * QUI vediamo anche la gestione delle eccezioni checked
 * del costruttore di MusicPlayer.
 */
public class MusicPlayerApp {

    public static void main(String[] args) {

        // ► 1. Percorso del file audio
        // Senza argomenti viene usato il WAV incluso nella repository. Per provare
        // un altro brano si può passare il suo percorso come primo argomento.
        String filePath = args.length > 0
                ? args[0]
                : Path.of("lessons", "cybersecurity", "anno1", "3_Programmazione",
                        "java", "tracce-java", "E0_esercizi_yt", "T11_MusicPlayers",
                        "audio", "Lazza-Molotov.wav").toString();

        try (Scanner scanner = new Scanner(System.in);
             MusicPlayer player = new MusicPlayer(filePath)) {
            System.out.println("File audio caricato correttamente.");
            int scelta;

            do {
                System.out.println("\n=== MUSIC PLAYER ===");
                System.out.println("1) Play dall'inizio");
                System.out.println("2) Pausa");
                System.out.println("3) Riprendi");
                System.out.println("4) Stop");
                System.out.println("5) Attiva loop");
                System.out.println("6) Disattiva loop");
                System.out.println("0) Esci");
                System.out.print("Scelta: ");

                while (!scanner.hasNextInt()) {
                    System.out.print("Inserisci un numero valido: ");
                    scanner.next();
                }
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1 -> player.playFromStart();
                    case 2 -> player.pausa();
                    case 3 -> player.riprendi();
                    case 4 -> player.stop();
                    case 5 -> player.attivaLoop();
                    case 6 -> player.disattivaLoop();
                    case 0 -> System.out.println("Uscita dal player...");
                    default -> System.out.println("Scelta non valida.");
                }

            } while (scelta != 0);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Formato del file audio NON supportato.");
            return;
        } catch (IOException e) {
            System.out.println("Errore di I/O nel caricamento del file: " + e.getMessage());
            return;
        } catch (LineUnavailableException e) {
            System.out.println("Linea audio non disponibile sul sistema.");
            return;
        }

        System.out.println("Programma terminato.");
    }
}
