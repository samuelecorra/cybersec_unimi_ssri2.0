# ⭐ LEZIONE L01 — SCRITTURA SU FILE IN JAVA

## **4 modalità fondamentali per scrivere su file**

1. **FileWriter**
   → semplice, perfetto per file di testo piccoli/medi.

2. **BufferedWriter**
   → come FileWriter, ma molto più veloce per grandi quantità di testo.

3. **PrintWriter**
   → ideale per scrivere testo “formattato” (tipo report, log, CSV).

4. **FileOutputStream**
   → per file *binari* (immagini, audio, PDF...).

---

# ⚠️ Tutti i metodi di scrittura possono generare una **IOException**

Ecco perché questa lezione è perfetta dopo il modulo eccezioni.

---

# 📌 File 1 — MainFileWriter.java

Scrittura base con FileWriter.

```java
package M10_FileHandling.L01_ScritturaFile;

import java.io.FileWriter;
import java.io.IOException;

/**
 * ============================================================
 * L01 — SCRITTURA FILE (Metodo 1: FileWriter)
 * ============================================================
 *
 * FileWriter:
 *  - ideale per file di testo semplici
 *  - scrive carattere per carattere
 *  - può sovrascrivere o appendere al file
 *
 * NOTA IMPORTANTE:
 *  FileWriter lancia sempre IOException (eccezione CHECKED).
 *  Occorre gestirla con try/catch oppure con throws.
 */
public class MainFileWriter {

    public static void main(String[] args) {

        System.out.println("=== FILEWRITER: SCRITTURA BASE ===");

        try {
            FileWriter writer = new FileWriter("testo_filewriter.txt");

            writer.write("Ciao Samuele!\n");
            writer.write("Questo file è stato scritto con FileWriter.\n");
            writer.write("È il metodo più semplice.\n");

            writer.close();  // IMPORTANTISSIMO
            System.out.println("Scrittura completata (testo_filewriter.txt)");

        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file!");
            System.out.println("Dettagli: " + e.getMessage());
        }

        System.out.println("=== Fine FileWriter ===\n");
    }
}
```

---

# 📌 File 2 — MainBufferedWriter.java

Scrittura più efficiente con buffer.

```java
package M10_FileHandling.L01_ScritturaFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ============================================================
 * L01 — SCRITTURA FILE (Metodo 2: BufferedWriter)
 * ============================================================
 *
 * BufferedWriter:
 *  - più veloce e più efficiente
 *  - perfetto per grandi quantità di testo (file molto lunghi)
 */
public class MainBufferedWriter {

    public static void main(String[] args) {

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
```

---

# 📌 File 3 — MainPrintWriter.java

Scrittura “smart” con PrintWriter.

```java
package M10_FileHandling.L01_ScritturaFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
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

    public static void main(String[] args) {

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
```

---

# 📌 File 4 — MainFileOutputStream.java

Scrittura di file **BINARY** (immagini, audio, pdf, byte).

```java
package M10_FileHandling.L01_ScritturaFile;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ============================================================
 * L01 — SCRITTURA FILE (Metodo 4: FileOutputStream)
 * ============================================================
 *
 * FileOutputStream:
 *  - per file binari (immagini, PDF, audio, byte raw)
 *  - NON scrive testo, ma byte
 */
public class MainFileOutputStream {

    public static void main(String[] args) {

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
```

---

# 📘 Mini-riepilogo da appendere agli appunti

### **Metodi di scrittura file (testo):**

| Metodo             | Caratteristiche                                 |
| ------------------ | ----------------------------------------------- |
| **FileWriter**     | semplice, diretto, sovrascrive o appende        |
| **BufferedWriter** | più veloce, buffer interno                      |
| **PrintWriter**    | migliore per testo formattato (println, printf) |

### **Per file binari:**

| Metodo               | Note                               |
| -------------------- | ---------------------------------- |
| **FileOutputStream** | scrive byte → immagini, audio, pdf |

