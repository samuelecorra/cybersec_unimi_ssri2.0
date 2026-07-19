package M10_FileHandling.L02_LetturaFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/*
 * ============================================================
 * L02 — LETTURA FILE (Metodo 2: Scanner)
 * ============================================================
 *
 * Scanner:
 *  - perfetto per leggere testo strutturato
 *  - può leggere parola per parola, numero per numero
 *  - l'apertura del file può lanciare IOException (CHECKED)
 */
public class MainScannerReader {

    public static void main(String[] args) {

        System.out.println("=== LETTURA FILE CON SCANNER ===");

        try (Scanner scanner = new Scanner(
                new File("testo_printwriter.txt"), StandardCharsets.UTF_8)) {

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println("[Scanner] " + linea);
            }

        } catch (IOException e) {
            System.out.println("Errore durante l'apertura del file: " + e.getMessage());
        }

        System.out.println("\n=== Fine lettura con Scanner ===");
    }
}
