package E0_esercizi_yt.T11_MusicPlayers.MusicPlayerV2;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/*
 * ============================================================
 * CLASSE MusicPlayer
 * ============================================================
 *
 * Questa classe incapsula tutta la logica per:
 *  - caricare un file audio (wav, au, aiff)
 *  - riprodurlo
 *  - metterlo in pausa
 *  - riprendere
 *  - fermare
 *  - attivare/disattivare il loop
 *
 * Usa le classi del package javax.sound.sampled:
 *  - AudioSystem
 *  - AudioInputStream
 *  - Clip
 *
 * Le eccezioni principali:
 *  - UnsupportedAudioFileException → formato audio non supportato
 *  - IOException                  → problemi di I/O sul file
 *  - LineUnavailableException     → risorsa audio non disponibile
 */
public class MusicPlayer {

    private Clip clip;
    private boolean inPausa = false;
    private long posizionePausa = 0; // in microsecondi

    /*
     * Costruttore: carica il file audio e prepara il Clip.
     *
     * @param filePath percorso del file audio (es. "audio/canzone.wav")
     */
    public MusicPlayer(String filePath)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File file = new File(filePath);

        if (!file.exists()) {
            // Meglio controllare subito per evitare errori più strani dopo
            throw new IOException("File audio non trovato: " + file.getAbsolutePath());
        }

        // Otteniamo lo stream audio dal file
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

        // Otteniamo un Clip (una linea che può riprodurre brevi suoni)
        clip = AudioSystem.getClip();

        // Carichiamo i dati audio nel Clip
        clip.open(audioStream);
    }

    /*
     * Avvia la riproduzione dall'inizio.
     * Se era in pausa, riparte dall'inizio (non dalla pausa).
     */
    public void playFromStart() {
        if (clip == null) {
            System.out.println("Nessun clip caricato.");
            return;
        }

        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
        inPausa = false;
        System.out.println("▶ Riproduzione dall'inizio.");
    }

    /*
     * Metti in pausa la riproduzione.
     * Salviamo la posizione attuale in microsecondi.
     */
    public void pausa() {
        if (clip == null) {
            System.out.println("Nessun clip caricato.");
            return;
        }

        if (!clip.isRunning()) {
            System.out.println("Il brano non è in riproduzione.");
            return;
        }

        posizionePausa = clip.getMicrosecondPosition();
        clip.stop();
        inPausa = true;
        System.out.println("⏸ Brano in pausa.");
    }

    /*
     * Riprende la riproduzione dal punto in cui era stata messa in pausa.
     */
    public void riprendi() {
        if (clip == null) {
            System.out.println("Nessun clip caricato.");
            return;
        }

        if (!inPausa) {
            System.out.println("Il brano non è in pausa.");
            return;
        }

        clip.setMicrosecondPosition(posizionePausa);
        clip.start();
        inPausa = false;
        System.out.println("▶ Ripresa dalla pausa.");
    }

    /*
     * Ferma completamente la riproduzione e torna all'inizio.
     */
    public void stop() {
        if (clip == null) {
            return;
        }

        clip.stop();
        clip.setMicrosecondPosition(0);
        inPausa = false;
        System.out.println("⏹ Brano fermato.");
    }

    /*
     * Attiva il loop infinito del brano.
     */
    public void attivaLoop() {
        if (clip == null) {
            return;
        }

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        System.out.println("🔁 Loop attivo (riproduzione continua).");
    }

    /*
     * Disattiva il loop, il brano continuerà fino alla fine e poi si fermerà.
     */
    public void disattivaLoop() {
        if (clip == null) {
            return;
        }

        // Per disattivare il loop basta impostare il numero di loop a 0.
        clip.loop(0);
        System.out.println("⏹ Loop disattivato (nessuna ripetizione automatica).");
    }

    /*
     * Rilascia le risorse audio.
     * Va chiamato quando il programma termina.
     */
    public void chiudi() {
        if (clip != null) {
            clip.stop();
            clip.close();
            System.out.println("Risorse audio rilasciate.");
        }
    }
}