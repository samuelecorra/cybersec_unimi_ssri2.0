package M10_FileHandling.L02_LetturaFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
 * ============================================================
 * L02 — LETTURA FILE (Metodo 1: BufferedReader + FileReader)
 * ============================================================
 *
 * BufferedReader:
 *  - leggiamo i file di testo LINEA PER LINEA
 *  - è veloce ed efficiente
 *
 * FileReader:
 *  - converte i byte in caratteri
 *  - può essere usato direttamente o avvolto in BufferedReader
 */
public class MainBufferedReader {

    public static void main(String[] args) {

        System.out.println("=== LETTURA FILE (BufferedReader + FileReader) ===");

        try (BufferedReader br = new BufferedReader(new FileReader(
                "testo_buffered.txt", StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Letto: " + linea);
            }

        } catch (IOException e) {
            System.out.println("Errore nella lettura del file!");
            System.out.println("Dettaglio: " + e.getMessage());
        }

        System.out.println("\n=== Fine lettura ===");
    }
}
