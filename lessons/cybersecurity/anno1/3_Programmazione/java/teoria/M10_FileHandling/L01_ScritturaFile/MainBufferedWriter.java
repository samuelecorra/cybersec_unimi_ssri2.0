package M10_FileHandling.L01_ScritturaFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * ============================================================
 * L01 — SCRITTURA FILE (Metodo 2: BufferedWriter)
 * ============================================================
 *
 * BufferedWriter:
 *  - più veloce e più efficiente
 *  - perfetto per grandi quantità di testo (file molto lunghi)
 */
public class MainBufferedWriter {

    static void main(String[] args) {

        System.out.println("=== BUFFEREDWRITER: SCRITTURA EFFICIENTE ===");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("testo_buffered.txt"));

            bw.write("Scrittura tramite BufferedWriter.\n");
            bw.write("Questo metodo mette i dati in un buffer\n");
            bw.write("e li invia al file in blocchi più grandi.\n");

            bw.close(); // svuota il buffer e chiude
            System.out.println("Scrittura completata (testo_buffered.txt)");

        } catch (IOException e) {
            System.out.println("Errore durante la scrittura!");
        }

        System.out.println("=== Fine BufferedWriter ===\n");
    }
}
