# ⭐ LEZIONE 17 – Wrapper Classes in Java

I **wrapper** sono classi che “incartano” i tipi primitivi per poterli usare come **oggetti**.

Primitive → Wrapper

* `int` → `Integer`
* `double` → `Double`
* `boolean` → `Boolean`
* `char` → `Character`
* `byte` → `Byte`
* `short` → `Short`
* `long` → `Long`
* `float` → `Float`

---

# ❓ Perché esistono?

Per due motivi fondamentali:

### **1️⃣ Le collezioni Java non accettano tipi primitivi**

Esempio:

```java
List<int> numeri = new ArrayList<>();  // ❌ NON ESISTE
```

Ma:

```java
List<Integer> numeri = new ArrayList<>(); // ✔️ FUNZIONA
```

Perché le liste lavorano solo con **oggetti**, non primitive.

---

### **2️⃣ Hanno metodi utili**

Esempi:

* `Integer.parseInt("123")`
* `Double.parseDouble("3.14")`
* `Character.isDigit(c)`
* `Boolean.parseBoolean("true")`

Le primitive non hanno metodi.

---

# ⭐ BONUS: Autoboxing e Unboxing

Java converte automaticamente tra primitivo e wrapper.

```java
Integer x = 5;   // autoboxing: da int → Integer
int y = x;       // unboxing: da Integer → int
```

---

# 👇 Ecco la lezione con codice completo

## `MainWrapper.java`

```java
package M09_OOP.L17_WrapperClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * LEZIONE 17 — WRAPPER CLASSES
 * ============================================================
 *
 * Definizione:
 *  Una wrapper class è una classe che “incapsula” un tipo primitivo
 *  permettendogli di comportarsi come un oggetto.
 *
 * A cosa servono:
 *  - Permettono di usare primitive dentro collezioni (List, Map…)
 *  - Offrono metodi statici utili (parse, confronto, controllo)
 *  - Rendono possibile l’autoboxing/unboxing
 *
 * Consiglio pratico:
 *  usa i wrapper SOLO quando ti servono davvero oggetti.
 */
public class MainWrapper {

    public static void main(String[] args) {

        // ============================================================
        // 1. ESEMPIO BASE: Autoboxing e Unboxing
        // ============================================================
        Integer a = 10;    // autoboxing: int -> Integer
        int b = a;         // unboxing: Integer -> int

        System.out.println("Autoboxing: Integer a = " + a);
        System.out.println("Unboxing: int b = " + b);

        // ============================================================
        // 2. Wrapper come OGGETTI con metodi utili
        // ============================================================
        Integer numero = Integer.valueOf(42);
        System.out.println("\nMetodi di Integer:");
        System.out.println("numero = " + numero);
        System.out.println("Max value = " + Integer.MAX_VALUE);
        System.out.println("Min value = " + Integer.MIN_VALUE);

        Double d = Double.parseDouble("3.14159");
        System.out.println("\nDouble.parseDouble: " + d);

        Character c = 'A';
        System.out.println("\nCharacter.isLetter: " + Character.isLetter(c));
        System.out.println("Character.isDigit: " + Character.isDigit(c));

        Boolean bool = Boolean.parseBoolean("true");
        System.out.println("\nBoolean.parseBoolean: " + bool);

        // ============================================================
        // 3. Perché servono i wrapper? Le Collezioni!
        // ============================================================
        System.out.println("\n=== LISTA DI Integer (non posso usare int!) ===");

        List<Integer> valori = new ArrayList<>();

        valori.add(10);  // autoboxing
        valori.add(20);
        valori.add(30);

        for (Integer x : valori) {
            System.out.println("Valore: " + x);
        }

        // ============================================================
        // 4. Esempio utile: conversione String -> numero
        // ============================================================
        String input = "12345";

        int convertito = Integer.parseInt(input);
        System.out.println("\nString \"" + input + "\" convertita in int = " + convertito);

        // ============================================================
        // 5. Esempio più “pratico”: calcolo media con List<Integer>
        // ============================================================
        System.out.println("\n=== Calcolo Media ===");

        List<Integer> voti = new ArrayList<>();
        voti.add(30);
        voti.add(28);
        voti.add(27);
        voti.add(30);

        int somma = 0;
        for (Integer voto : voti) {
            somma += voto;  // unboxing automatico
        }

        double media = (double) somma / voti.size();
        System.out.println("Media = " + media);
    }
}
```

---

# 📚 Ricapitolone breve, perfetto per gli appunti

**Wrapper Classes = permettono ai tipi primitivi di comportarsi come oggetti.**

* Servono per le **collezioni**
* Hanno **metodi utili**
* Permettono **autoboxing/unboxing automatico**
* Tipi: `Integer`, `Double`, `Boolean`, `Character`, ecc.

---

