package M10_FileHandling.L02_LetturaFile;

import java.io.FileInputStream;
import java.io.IOException;

/*
 * ============================================================
 * L02 — LETTURA FILE (Metodo 3: FileInputStream)
 * ============================================================
 *
 * FileInputStream:
 *  - per leggere file binari (immagini, pdf, mp3, ecc.)
 *  - restituisce byte (0–255)
 */
public class MainFileInputStream {

    static void main(String[] args) {

        System.out.println("=== LETTURA BINARIA (FileInputStream) ===");

        try {
            FileInputStream fis = new FileInputStream("dati.bin");

            int byteLetto;
            while ((byteLetto = fis.read()) != -1) {
                System.out.println("Byte letto: " + byteLetto);
            }

            fis.close();

        } catch (IOException e) {
            System.out.println("Errore lettura binaria!");
        }

        System.out.println("\n=== Fine lettura binaria ===");
    }
}