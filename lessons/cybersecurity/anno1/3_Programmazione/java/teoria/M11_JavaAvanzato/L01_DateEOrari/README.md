
# 📘 **M11 Lezione 1: Date & Time in Java (LocalDate, LocalTime, LocalDateTime, Instant/UTC)**

Java mette a disposizione un intero framework per lavorare con date e orari: il pacchetto
`java.time`.
È moderno, sicuro, molto più potente delle vecchie classi (`Date`, `Calendar`).

In questa lezione vediamo:

* `LocalDate` → solo la data
* `LocalTime` → solo l’ora
* `LocalDateTime` → data + ora (senza fuso)
* `Instant` → timestamp UTC puro (perfetto per database e log)
* `ZonedDateTime` → data + ora + fuso orario
* `DateTimeFormatter` → formattazione
* Conversioni fra i vari tipi

Ogni blocco ha **spiegazione + codice**.

---

# ⭐ 1. **LocalDate — SOLO la data**

### Cosa rappresenta?

Una data senza orario e senza fuso.
Esempi: anniversario, compleanno, scadenza, data odierna.

### Codice:

```java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        LocalDate today = LocalDate.now();        // data di oggi
        LocalDate specific = LocalDate.of(2025, 11, 17);

        System.out.println("Oggi: " + today);
        System.out.println("Data specifica: " + specific);

        // Operazioni comuni
        System.out.println("Domani: " + today.plusDays(1));
        System.out.println("Mese prossimo: " + today.plusMonths(1));
        System.out.println("Anno scorso: " + today.minusYears(1));

        System.out.println("Giorno del mese: " + today.getDayOfMonth());
        System.out.println("Giorno della settimana: " + today.getDayOfWeek());
    }
}
```

---

# ⭐ 2. **LocalTime — SOLO l’orario**

### Cosa rappresenta?

Un orario puro, senza data e senza fuso.
Esempi: “apertura alle 08:30”, timer, orari di lezioni.

### Codice:

```java
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        LocalTime now = LocalTime.now();
        LocalTime specific = LocalTime.of(14, 30, 15);

        System.out.println("Ora attuale: " + now);
        System.out.println("Orario specifico: " + specific);

        System.out.println("Tra 1 ora: " + now.plusHours(1));
        System.out.println("Tra 30 min: " + now.plusMinutes(30));
    }
}
```

---

# ⭐ 3. **LocalDateTime — data + ora (SENZA fuso)**

### Cosa rappresenta?

Una data con un orario, ma **non** indica il fuso orario.

Perfetto per:

* timestamp locali,
* eventi di calendario,
* scadenze.

### Codice:

```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime specific = LocalDateTime.of(2025, 11, 17, 16, 30);

        System.out.println("Adesso: " + now);
        System.out.println("Data+ora specifica: " + specific);

        // Operazioni
        System.out.println("Tra 24 ore: " + now.plusDays(1));
    }
}
```

---

# ⭐ 4. **Instant — timestamp UTC (per DATABASE e LOG)**

### Cosa rappresenta?

Un numero di secondi/nanosecondi dalla **Epoch** (1 gennaio 1970 UTC).
È il modo *standard* di salvare tempi in sistemi distribuiti.

### Codice:

```java
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        Instant now = Instant.now();  // sempre UTC

        System.out.println("Timestamp UTC: " + now);

        // Confronti
        Instant later = now.plusSeconds(3600);
        System.out.println("Tra 1 ora (UTC): " + later);
    }
}
```

---

# ⭐ 5. **ZonedDateTime — data + ora + fuso orario**

Per lavorare su orari *reali* nel mondo (Italia, New York, Tokyo…).

### Codice:

```java
import java.time.*;

public class Main {
    public static void main(String[] args) {

        ZonedDateTime nowMilano = ZonedDateTime.now(ZoneId.of("Europe/Rome"));
        ZonedDateTime nowTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        System.out.println("Ora Roma:  " + nowMilano);
        System.out.println("Ora Tokyo: " + nowTokyo);

        // Conversioni di fuso
        ZonedDateTime milanoToTokyo = nowMilano.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("Roma convertita in Tokyo: " + milanoToTokyo);
    }
}
```

---

# ⭐ 6. **Formattare date e orari (DateTimeFormatter)**

### Codice:

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String formatted = now.format(fmt);

        System.out.println("Formattato: " + formatted);
    }
}
```

---

# ⭐ 7. **Conversioni importanti**

### LocalDateTime → Instant (serve un fuso!)

```java
import java.time.*;

LocalDateTime ldt = LocalDateTime.now();
Instant instant = ldt.atZone(ZoneId.of("Europe/Rome")).toInstant();
```

### Instant → LocalDateTime

```java
LocalDateTime fromInstant =
        LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Rome"));
```

---

# ⭐ 8. **Mini project: mostra tutto**

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dt = LocalDateTime.now();
        Instant inst = Instant.now();

        ZonedDateTime rome = ZonedDateTime.now(ZoneId.of("Europe/Rome"));
        ZonedDateTime ny = rome.withZoneSameInstant(ZoneId.of("America/New_York"));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("Data: " + date);
        System.out.println("Ora: " + time);
        System.out.println("Data+Ora: " + dt.format(fmt));
        System.out.println("UTC timestamp: " + inst);
        System.out.println("Roma: " + rome);
        System.out.println("NY  : " + ny);
    }
}
```


