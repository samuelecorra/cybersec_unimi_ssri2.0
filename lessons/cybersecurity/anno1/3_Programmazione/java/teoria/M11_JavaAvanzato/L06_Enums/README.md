# 🟥 **LEZIONE SUPER COMPLETA: ENUM (Enumerazioni) in Java**

Gli **enum** sono un *tipo di dato speciale* introdotto in Java 5 per risolvere un problema molto comune:

> rappresentare un insieme FINITO e FISSO di valori costanti, in modo sicuro, leggibile e pulito.

---

# 🔵 **1. Perché esistono gli enum? Il problema che risolvono**

Prima degli enum, i programmatori usavano:

```java
public static final int LUNEDI = 1;
public static final int MARTEDI = 2;
...
```

Oppure:

```java
public static final String LUNEDI = "LUNEDI";
```

Problemi:

* ❌ Integer o String possono essere sbagliati (typo, valore non valido).
* ❌ Non esiste controllo su cosa è "ammesso".
* ❌ Non puoi aggiungere metodi, comportamenti, descrizioni interne.
* ❌ Nei `switch` le String costano di più rispetto agli enum.

Gli enum risolvono tutto in un colpo solo.

---

# 🔵 **2. Che cos’è un Enum?**

Un **Enum** è una **classe speciale** con un numero FINITO di istanze predefinite.

Definizione semplice:

> Un enum è come una “scatola chiusa” di valori permessi.

Esempio intuitivo:

* Le stagioni dell’anno
* I giorni della settimana
* I livelli di difficoltà di un videogioco
* Gli stati di un ordine (CREATO → SPEDITO → CONSEGNATO)

---

# 🔵 **3. Sintassi base**

```java
enum Stagione {
    PRIMAVERA,
    ESTATE,
    AUTUNNO,
    INVERNO
}
```

Questi NON sono semplici stringhe: sono **oggetti statici, final, unici**.

Usarli:

```java
Stagione s = Stagione.AUTUNNO;
System.out.println(s);
```

Output:
`AUTUNNO`

---

# 🟦 **4. Perché gli enum migliorano il codice?**

### ✔ **1. Sicurezza**

Impossibile assegnare valori non previsti:

```java
Stagione s = Stagione.PIPPO; // ERRORE
```

### ✔ **2. Leggibilità migliorata**

Con uno sguardo capisci subito cosa rappresenta il valore.

### ✔ **3. Perfetti nei `switch`**

Java può ottimizzare molto meglio un `switch` sugli enum.

### ✔ **4. Puoi aggiungere metodi o campi**

Gli enum possono contenere:

* costruttori
* metodi
* campi
* override di toString()

Sono vere classi, solo con istanze limitate.

---

# 🟥 **5. Esempio completo con costruttore e metodo**

Caso: voglio rappresentare i livelli di difficoltà di un videogioco.

```java
enum Difficolta {
    FACILE(1),
    MEDIO(2),
    DIFFICILE(3);

    private int livello;  // attributo interno

    Difficolta(int livello) {
        this.livello = livello;
    }

    public int getLivello() {
        return livello;
    }
}
```

Uso:

```java
public class Main {
    public static void main(String[] args) {
        Difficolta d = Difficolta.MEDIO;

        System.out.println(d);             // MEDIO
        System.out.println(d.getLivello()); // 2
    }
}
```

---

# 🟧 **6. Enum + switch (super comodo)**

```java
enum Comando {
    START, STOP, PAUSA
}

public class Main {
    public static void main(String[] args) {
        Comando c = Comando.START;

        switch (c) {
            case START -> System.out.println("Gioco avviato");
            case STOP -> System.out.println("Gioco interrotto");
            case PAUSA -> System.out.println("Gioco in pausa");
        }
    }
}
```

---

# 🟦 **7. Enum con comportamento diverso per ogni valore**

Java permette una cosa assurda: dare *override personalizzato* a ogni elemento.

```java
enum Operazione {
    SOMMA {
        double esegui(double a, double b) { return a + b; }
    },
    MOLTIPLICA {
        double esegui(double a, double b) { return a * b; }
    };

    abstract double esegui(double a, double b);
}
```

Uso:

```java
double r = Operazione.SOMMA.esegui(5, 7);
System.out.println(r); // 12
```

---

# 🟥 **8. Differenze tra Enum e altre costanti**

| Metodo                | Benefici                                                   | Problemi                                         |
| --------------------- | ---------------------------------------------------------- | ------------------------------------------------ |
| `final static int`    | veloce, semplice                                           | nessuna protezione, difficile capire significato |
| `final static String` | leggibile                                                  | typo, confronto lento                            |
| **Enum**              | sicuro, potente, estendibile, leggibile, veloce nei switch | nessuno, è lo standard moderno                   |

---

# 🟨 **9. Caso pratico didattico – Semaforo**

```java
enum ColoreSemaforo {
    ROSSO, GIALLO, VERDE
}

public class Main {
    public static void main(String[] args) {
        ColoreSemaforo c = ColoreSemaforo.ROSSO;

        if (c == ColoreSemaforo.ROSSO) {
            System.out.println("Fermati!");
        }
    }
}
```

---