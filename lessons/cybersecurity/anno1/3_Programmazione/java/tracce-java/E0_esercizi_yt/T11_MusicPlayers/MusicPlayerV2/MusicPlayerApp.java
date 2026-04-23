package E0_esercizi_yt.T11_MusicPlayers.MusicPlayerV2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
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

    static void main(String[] args) {

        // ► 1. Percorso del file audio
        // Metti un file .wav nella tua cartella del progetto, ad esempio in:
        // src/audio/canzone.wav
        // e usa un path relativo come questo:
        String filePath = "src/audio/canzone.wav";

        MusicPlayer player = null;

        try {
            player = new MusicPlayer(filePath);
            System.out.println("File audio caricato correttamente.");
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

        // ► 2. Menù testuale
        Scanner scanner = new Scanner(System.in);
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
                scanner.next(); // scarta input non valido
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

        // ► 3. Chiudiamo risorse
        if (player != null) {
            player.chiudi();
        }
        scanner.close();

        System.out.println("Programma terminato.");
    }
}