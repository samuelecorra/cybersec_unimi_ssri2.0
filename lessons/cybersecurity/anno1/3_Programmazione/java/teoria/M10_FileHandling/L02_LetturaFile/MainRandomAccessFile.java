package M10_FileHandling.L02_LetturaFile;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * ============================================================
 * L02 — LETTURA FILE (Metodo 4: RandomAccessFile)
 * ============================================================
 *
 * RandomAccessFile:
 *  - puoi leggere e scrivere IN QUALSIASI PUNTO del file
 *  - puoi saltare avanti/indietro con seek()
 *  - fondamentale per file molto grandi o indicizzati
 */
public class MainRandomAccessFile {

    public static void main(String[] args) {

        System.out.println("=== RANDOM ACCESS FILE ===");

        try (RandomAccessFile raf = new RandomAccessFile("dati.bin", "r")) {

            System.out.println("Lunghezza file: " + raf.length() + " byte");

            int primoByte = raf.readUnsignedByte();
            System.out.println("Primo byte: " + primoByte);

            // seek usa offset di byte, non indici di caratteri.
            raf.seek(2);
            int terzoByte = raf.readUnsignedByte();
            System.out.println("Byte in posizione 2: " + terzoByte);

        } catch (IOException e) {
            System.out.println("Errore con RandomAccessFile!");
        }

        System.out.println("\n=== Fine RandomAccessFile ===");
    }
}
