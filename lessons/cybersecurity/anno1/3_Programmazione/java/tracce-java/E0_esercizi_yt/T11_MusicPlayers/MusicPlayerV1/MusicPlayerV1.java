package E0_esercizi_yt.T11_MusicPlayers.MusicPlayerV1;

import javax.sound.sampled.*; // serve per AudioSystem, AudioInputStream, Clip, ecc...
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayerV1 {

    static void main() {

        // Come riprodurre audio con Java (.wav, .au, .aiff)

        String filePath = "C:/Users/nabis/cybersamu-gitlocker/1_PRIMO_ANNO/java/tracce-java/E0_esercizi_yt/T11_MusicPlayers/audio/Lazza-Molotov.wav"; // Innanzitutto il percorso del file audio, relativo!
        File file = new File(filePath); // Creiamo un oggetto File con il percorso, che non è più un puntatore come in C!

        // Tutto ciò che riguarda il file handling richiede la gestione delle eccezioni in Java!
        try(Scanner sc = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);){

            Clip clip = AudioSystem.getClip(); // Otteniamo un Clip (una linea che può riprodurre brevi suoni)
            clip.open(audioStream);

            String risposta = "";

            while(!risposta.equals("Q")) {

                System.out.println("P = Play");
                System.out.println("S = Stop");
                System.out.println("L = Loop");
                System.out.println("R = Reset");
                System.out.println("Q = Quit");
                System.out.println("--> Scegli un'opzione: ");

                risposta = sc.next().toUpperCase();

                switch(risposta){
                    case "P":
                        clip.start();
                        System.out.println("Riproduzione in corso...");
                        break;
                    case "S":
                        clip.stop();
                        System.out.println("Riproduzione fermata.");
                        break;
                    case "L":
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        System.out.println("Loop attivato.");
                        break;
                    case "R":
                        clip.setMicrosecondPosition(0);
                        System.out.println("Posizione resettata all'inizio.");
                        break;
                    case "Q":
                        clip.stop();
                        clip.close();
                        System.out.println("Uscita dal programma.");
                        break;
                    default:
                        System.out.println("Opzione non valida. Riprova: ");
                }
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("File audio NON trovato: " + file.getAbsolutePath());
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println("Formato del file audio NON supportato.");
        }
        catch(LineUnavailableException e){
            System.out.println("Linea audio non disponibile sul sistema.");
        }
        catch(IOException e){
            System.out.println("Errore di I/O nel caricamento del file: " + e.getMessage());
        }
        finally{
            System.out.println("Esecuzione terminata.");
        }
    }
}