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

    static void main(String[] args) {

        System.out.println("=== RANDOM ACCESS FILE ===");

        try {
            RandomAccessFile raf = new RandomAccessFile("testo_buffered.txt", "r");

            System.out.println("Lunghezza file: " + raf.length() + " byte");

            // Legge i primi 15 byte
            byte[] buffer = new byte[15];
            raf.read(buffer);
            System.out.println("Primi 15 bytes: " + new String(buffer));

            // Spostiamo il cursore nel punto 5
            raf.seek(5);
            byte[] buffer2 = new byte[10];
            raf.read(buffer2);
            System.out.println("Bytes da posizione 5: " + new String(buffer2));

            raf.close();

        } catch (IOException e) {
            System.out.println("Errore con RandomAccessFile!");
        }

        System.out.println("\n=== Fine RandomAccessFile ===");
    }
}