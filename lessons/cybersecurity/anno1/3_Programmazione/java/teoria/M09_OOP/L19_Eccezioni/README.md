# ⭐ LEZIONE 19 – LE ECCEZIONI IN JAVA

## 💡 **Cos’è un’eccezione?**

Un’eccezione è un **evento anomalo** che interrompe il flusso normale del programma.
Esempi classici:

* divisione per zero
* accesso a un indice fuori dai limiti dell’array
* file non trovato
* input non valido
* connessione al database fallita

Quando accade un errore del genere, Java crea un oggetto di tipo `Exception` e lancia (throws) quel segnale.

Il nostro compito: **gestirla** con `try / catch / finally`.

---

# 🔥 STRUTTURA BASE

```java
try {
    // Codice pericoloso
} catch (ExceptionType e) {
    // Cosa fare se si verifica quell'eccezione
} finally {
    // Codice che viene SEMPRE eseguito (anche in presenza di errori)
}
```

---

# 🎯 Tipi di eccezioni

### **1️⃣ Unchecked (RuntimeException)**

Non obbligano il programmatore a gestirle.

Esempi:

* `ArithmeticException`
* `ArrayIndexOutOfBoundsException`
* `NullPointerException`
* `IllegalArgumentException`

### **2️⃣ Checked (Exception)**

DEVONO essere gestite con try/catch **oppure** dichiarate con `throws`.

Esempi:

* `IOException`
* `FileNotFoundException`
* `SQLException`

---

# 💻 FILE 1 — `MainEccezioniBase.java`

Spiegazione pratica dei concetti fondamentali.

```java
package M09_OOP.L19_Eccezioni;

public class MainEccezioniBase {

    public static void main(String[] args) {

        System.out.println("=== Esempio 1: Divisione per zero ===");

        try {
            int a = 10;
            int b = 0;
            int risultato = a / b;   // genera ArithmeticException
            System.out.println("Risultato: " + risultato);
        } catch (ArithmeticException e) {
            System.out.println("Errore: divisione per zero!");
            System.out.println("Messaggio dell'eccezione: " + e.getMessage());
        }

        System.out.println("\nIl programma continua normalmente.\n");

        // -------------------------------------------------------------

        System.out.println("=== Esempio 2: Accesso oltre i limiti di un array ===");

        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Errore: indice fuori dai limiti dell'array!");
        }

        System.out.println("\nProgramma ancora vivo.\n");

        // -------------------------------------------------------------

        System.out.println("=== Esempio 3: NullPointerException ===");

        try {
            String s = null;
            System.out.println(s.length()); // NPE
        } catch (NullPointerException e) {
            System.out.println("Errore: oggetto null!");
        }

        System.out.println("\nFINE ESEMPI BASE");
    }
}
```

---

# 💻 FILE 2 — `MainTryCatchFinally.java`

Dimostrazione vera di `finally`.

```java
package M09_OOP.L19_Eccezioni;

/**
 * Dimostrazione del blocco finally.
 * È un blocco che VIENE SEMPRE ESEGUITO.
 */
public class MainTryCatchFinally {

    public static void main(String[] args) {

        System.out.println("=== Dimostrazione del finally ===");

        try {
            System.out.println("Codice pericoloso...");
            int n = 10 / 0; 
        } catch (ArithmeticException e) {
            System.out.println("ECCEZIONE CATTURATA: divisione per zero.");
        } finally {
            System.out.println("Blocco finally ESEGUITO comunque.");
        }

        System.out.println("\nFine dimostrazione.\n");
    }
}
```

---

# 💻 FILE 3 — `MainEccezioniChecked.java`

Esempio di eccezione checked (file non trovato).

```java
package M09_OOP.L19_Eccezioni;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esempio di gestione delle eccezioni CHECKED:
 * FileReader e BufferedReader lanciano IOException.
 */
public class MainEccezioniChecked {

    public static void main(String[] args) {

        System.out.println("=== Lettura da file con eccezioni checked ===");

        try {
            BufferedReader br = new BufferedReader(new FileReader("file_che_non_esiste.txt"));
            String linea = br.readLine();
            System.out.println("Contenuto: " + linea);
            br.close();
        } catch (IOException e) {
            System.out.println("ERRORE: impossibile leggere il file!");
            System.out.println("Dettagli: " + e.getMessage());
        }

        System.out.println("\nIl programma continua comunque.");
    }
}
```

---

# 💻 FILE 4 — `Calcolatrice.java` + `MainCalcolatriceConEccezioni.java`

Implementiamo una piccola calcolatrice che LANCIA noi stessi un’eccezione custom.

## `Calcolatrice.java`

```java
package M09_OOP.L19_Eccezioni;

/**
 * Piccola classe Calcolatrice per mostrare:
 * - Lancio di eccezioni con throw
 * - Uso di IllegalArgumentException
 */
public class Calcolatrice {

    public static int dividi(int a, int b) {
        if (b == 0) {
            // Lanciamo manualmente un'eccezione
            throw new IllegalArgumentException("Non puoi dividere per zero!");
        }
        return a / b;
    }

    public static int radiceQuadrata(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("La radice quadrata di numero negativo non è definita!");
        }
        return (int) Math.sqrt(n);
    }
}
```

---

## `MainCalcolatriceConEccezioni.java`

```java
package M09_OOP.L19_Eccezioni;

public class MainCalcolatriceConEccezioni {

    public static void main(String[] args) {

        System.out.println("=== Calcolatrice con eccezioni ===");

        try {
            int r1 = Calcolatrice.dividi(10, 2);
            System.out.println("10 / 2 = " + r1);

            int r2 = Calcolatrice.dividi(10, 0); // genera eccezione
            System.out.println("10 / 0 = " + r2);

        } catch (IllegalArgumentException e) {
            System.out.println("Errore della calcolatrice: " + e.getMessage());
        }

        try {
            int r3 = Calcolatrice.radiceQuadrata(-25);
            System.out.println(r3);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nella radice: " + e.getMessage());
        }

        System.out.println("\n=== Fine dimostrazione ===");
    }
}
```

---

# 📘 MINI-RIEPILOGO per la memoria

### ✔ Eccezione = evento anomalo che interrompe il flusso del programma

### ✔ try = blocco in cui “rischiamo”

### ✔ catch = blocco che intercetta l’errore

### ✔ finally = codice che viene sempre eseguito

### ✔ throw = lancia manualmente un’eccezione

### ✔ throws = dichiara che un metodo può lanciare un’eccezione
