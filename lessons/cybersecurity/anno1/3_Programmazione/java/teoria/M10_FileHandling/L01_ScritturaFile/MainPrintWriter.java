package M10_FileHandling.L01_ScritturaFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * ============================================================
 * L01 — SCRITTURA FILE (Metodo 3: PrintWriter)
 * ============================================================
 *
 * PrintWriter:
 *  - ideale per report, logs, file strutturati
 *  - supporta println(), printf(), format()
 *  - molto comodo quando devi scrivere testo formattato
 */
public class MainPrintWriter {

    static void main(String[] args) {

        System.out.println("=== PRINTWRITER: SCRITTURA FORMATTATA ===");

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("testo_printwriter.txt"));

            pw.println("Report generato da PrintWriter");
            pw.printf("Oggi è il giorno %d del mese.\n", 17);
            pw.printf("Il risultato di 10/3 è %.2f\n", 10.0 / 3.0);

            pw.close();
            System.out.println("Scrittura completata (testo_printwriter.txt)");

        } catch (IOException e) {
            System.out.println("Errore con PrintWriter: " + e.getMessage());
        }

        System.out.println("=== Fine PrintWriter ===\n");
    }
}
