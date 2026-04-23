# ⭐ LEZIONE L02 — LETTURA DA FILE IN JAVA

## **3 grandi famiglie: testo, testo “comodo”, binario**

---

# 📌 FILE 1 — MainBufferedReader.java

**Il metodo più usato in assoluto per leggere testo.**

```java
package M10_FileHandling.L02_LetturaFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
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
 *  - si usa sempre insieme a BufferedReader
 */
public class MainBufferedReader {

    public static void main(String[] args) {

        System.out.println("=== LETTURA FILE (BufferedReader + FileReader) ===");

        try {
            BufferedReader br = new BufferedReader(new FileReader("testo_buffered.txt"));

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Letto: " + linea);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Errore nella lettura del file!");
            System.out.println("Dettaglio: " + e.getMessage());
        }

        System.out.println("\n=== Fine lettura ===");
    }
}
```

---

# 📌 FILE 2 — MainScannerReader.java

**Scanner** è comodo quando vuoi leggere numeri, parole, token, ecc.

```java
package M10_FileHandling.L02_LetturaFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
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

    public static void main(String[] args) {

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
```

---

# 📌 FILE 3 — MainFileInputStream.java

Lettura *binaria* byte per byte.

```java
package M10_FileHandling.L02_LetturaFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ============================================================
 * L02 — LETTURA FILE (Metodo 3: FileInputStream)
 * ============================================================
 *
 * FileInputStream:
 *  - per leggere file binari (immagini, pdf, mp3, ecc.)
 *  - restituisce byte (0–255)
 */
public class MainFileInputStream {

    public static void main(String[] args) {

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
```

---

# 📌 FILE 4 — MainRandomAccessFile.java

Leggere parti specifiche di un file, spostarsi con un “cursore”.
Potentissimo per database, log enormi, file di grandi dimensioni.

```java
package M10_FileHandling.L02_LetturaFile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
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
```

---

# 🎯 RIEPILOGO PER GLI APPUNTI

### **TECNOLOGIE PER LETTURA FILE DI TESTO:**

| Metodo                          | Descrizione                                      |
| ------------------------------- | ------------------------------------------------ |
| **BufferedReader + FileReader** | metodo standard, veloce, lettura linea per linea |
| **Scanner**                     | comodo per token, numeri, parsing facile         |
| **RandomAccessFile**            | lettura/scrittura a posizioni specifiche         |

### **PER FILE BINARI:**

| Metodo              | Note                                         |
| ------------------- | -------------------------------------------- |
| **FileInputStream** | lettura byte-per-byte (immagini, pdf, audio) |


