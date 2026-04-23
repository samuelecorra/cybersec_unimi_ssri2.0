package M10_FileHandling.L01_ScritturaFile;

import java.io.FileOutputStream;
import java.io.IOException;

/*
 * ============================================================
 * L01 — SCRITTURA FILE (Metodo 4: FileOutputStream)
 * ============================================================
 *
 * FileOutputStream:
 *  - per file binari (immagini, PDF, audio, byte raw)
 *  - NON scrive testo, ma byte
 */
public class MainFileOutputStream {

    static void main(String[] args) {

        System.out.println("=== FILEOUTPUTSTREAM: SCRITTURA BINARIA ===");

        try {
            FileOutputStream fos = new FileOutputStream("dati.bin");

            byte[] dati = { 10, 20, 30, 40, 50 };
            fos.write(dati);

            fos.close();
            System.out.println("Scrittura completata (dati.bin)");

        } catch (IOException e) {
            System.out.println("Errore nella scrittura binaria!");
        }

        System.out.println("=== Fine FileOutputStream ===");
    }
}
