package M10_FileHandling.L02_LetturaFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * ============================================================
 * L02 — LETTURA FILE (Metodo 2: Scanner)
 * ============================================================
 *
 * Scanner:
 *  - perfetto per leggere testo strutturato
 *  - può leggere parola per parola, numero per numero
 *  - eccezione lanciata: FileNotFoundException (CHECKED)
 */
public class MainScannerReader {

    static void main(String[] args) {

        System.out.println("=== LETTURA FILE CON SCANNER ===");

        try {
            Scanner scanner = new Scanner(new File("testo_printwriter.txt"));

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println("[Scanner] " + linea);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Errore: file non trovato!");
        }

        System.out.println("\n=== Fine lettura con Scanner ===");
    }
}