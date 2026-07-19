package M10_FileHandling.L01_ScritturaFile;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
 * ============================================================
 * L01 — SCRITTURA FILE (Metodo 1: FileWriter)
 * ============================================================
 *
 * FileWriter:
 *  - ideale per file di testo semplici
 *  - espone operazioni di scrittura di caratteri e stringhe
 *  - può sovrascrivere o appendere al file
 *
 * NOTA IMPORTANTE:
 *  Le operazioni di FileWriter possono lanciare IOException (eccezione CHECKED).
 *  Occorre gestirla con try/catch oppure con throws.
 */
public class MainFileWriter {

    public static void main(String[] args) {

        System.out.println("=== FILEWRITER: SCRITTURA BASE ===");

        try (FileWriter writer = new FileWriter(
                "testo_filewriter.txt", StandardCharsets.UTF_8)) {

            writer.write("Ciao Samuele!\n");
            writer.write("Questo file è stato scritto con FileWriter.\n");
            writer.write("È il metodo più semplice.\n");

            System.out.println("Scrittura completata (testo_filewriter.txt)");

        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file!");
            System.out.println("Dettagli: " + e.getMessage());
        }

        System.out.println("=== Fine FileWriter ===\n");
    }
}
