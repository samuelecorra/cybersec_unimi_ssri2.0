## 1. Cosa sono `Timer` e `TimerTask` (in parole semplici)

* **`Timer`**
  È una classe che si occupa di **lanciare compiti (`Task`) dopo un certo tempo** o **a intervalli regolari**.
  Pensa a un **timer da cucina**: tu lo imposti e lui, allo scadere, fa “DRIIN” (cioè esegue il codice che gli hai detto di eseguire).

* **`TimerTask`**
  È la **classe che rappresenta il compito da eseguire** allo scadere del timer.
  Tu crei una tua classe che **estende `TimerTask`** e **sovrascrivi il metodo `run()`**: quello è il codice che verrà eseguito.

Schema mentale:

```text
Timer  ---->  esegue  ---->  TimerTask.run()
```

---

## 2. Primo esempio: eseguire un’azione dopo 3 secondi

Immaginiamo di voler stampare un messaggio **una sola volta** dopo 3 secondi.

```java
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        // 1) Creiamo il Timer
        Timer timer = new Timer();
        // Questo crea un thread interno che si occuperà di eseguire i task programmati.

        // 2) Creiamo il compito da eseguire (TimerTask) usando una classe anonima
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Sono passati 3 secondi! Eseguo il task una volta.");
            }
        };

        // 3) Pianifichiamo il task:
        //    - delay = 3000 ms = 3 secondi
        //    - verrà eseguito UNA SOLA VOLTA dopo il ritardo
        timer.schedule(task, 3000);

        System.out.println("Task programmato. Aspetto 3 secondi...");
    }
}
```

### Spiegazione riga per riga (a livello atomico)

```java
import java.util.Timer;
import java.util.TimerTask;
```

* Importiamo le classi della libreria standard che ci servono.

```java
Timer timer = new Timer();
```

* Creiamo un oggetto `Timer`.
* Internamente questo apre **un thread** che controllerà i tempi e lancerà i task quando arrivano all’orario giusto.

```java
TimerTask task = new TimerTask() {
    @Override
    public void run() {
        System.out.println("Sono passati 3 secondi! Eseguo il task una volta.");
    }
};
```

* Qui stiamo creando un **oggetto anonimo** che estende `TimerTask`.
* **`run()`** è il metodo che il `Timer` chiamerà quando scatta il timer.
* Dentro `run()` mettiamo il codice da eseguire.

```java
timer.schedule(task, 3000);
```

* `schedule(TimerTask task, long delay)`
  → dice: *esegui questo `task` una sola volta, dopo `delay` millisecondi*.
* `3000` millisecondi = `3` secondi.

```java
System.out.println("Task programmato. Aspetto 3 secondi...");
```

* Questo viene stampato subito. Poi, **dopo 3 secondi**, verrà stampato il messaggio dal `run()`.

---

## 3. Esecuzione periodica: ogni 2 secondi

Ora vogliamo far eseguire un’azione **più volte**, ad intervalli regolari.
Esempio: stampare l’orario ogni 2 secondi.

```java
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();

        TimerTask stampaOraTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Esecuzione periodica: " + System.currentTimeMillis());
            }
        };

        long delay = 1000;   // primo avvio dopo 1 secondo
        long period = 2000;  // poi ogni 2 secondi

        // schedule(TimerTask task, long delay, long period)
        timer.schedule(stampaOraTask, delay, period);

        System.out.println("Task periodico programmato.");
    }
}
```

### Come funziona `schedule(task, delay, period)`

* **`delay`**: quanti millisecondi aspettare prima della **prima esecuzione**.
* **`period`**: ogni quanti millisecondi ripetere il task.

Quindi con `delay = 1000` e `period = 2000`:

* 1ª esecuzione a 1 secondo.
* 2ª esecuzione a 3 secondi.
* 3ª esecuzione a 5 secondi.
* e così via.

---

## 4. Fermare il timer: `cancel()`

Se non fai niente, il `Timer` continua a vivere e il programma **rimane attivo** perché il thread del `Timer` è *non-daemon* (non si ferma da solo).

Per fermarlo:

* puoi chiamare `timer.cancel()` quando non ti serve più;
* oppure farlo dentro un altro `TimerTask`.

### Esempio: esegui ogni 2 secondi, ma fermati dopo 10 secondi

```java
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();

        // Task periodico
        TimerTask stampaTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tick: " + System.currentTimeMillis());
            }
        };

        // Task che ferma il timer
        TimerTask stopTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("STOP: cancello il timer.");
                timer.cancel(); // ferma tutte le esecuzioni future
            }
        };

        long delayIniziale = 0;       // subito
        long periodo = 2000;          // ogni 2 secondi
        long delayStop = 10000;       // dopo 10 secondi

        timer.schedule(stampaTask, delayIniziale, periodo); // stampa ogni 2 secondi
        timer.schedule(stopTask, delayStop);                // dopo 10 secondi ferma tutto

        System.out.println("Timer avviato: stampa ogni 2s e si ferma dopo 10s.");
    }
}
```

### Cosa succede in pratica

* `stampaTask` parte subito e poi ogni 2 secondi.
* Dopo 10 secondi, `stopTask` viene eseguito e chiama `timer.cancel()`.
* Tutti i task futuri vengono **cancellati** e il thread del `Timer` viene terminato.

---

## 5. Creare una *classe* `TimerTask` vera e propria (non anonima)

Finora abbiamo usato **classi anonime**, ma spesso è meglio avere una **classe con un nome** e magari dei **campi**.

Esempio: un task che simula una **notifica di promemoria**.

```java
import java.util.TimerTask;

public class PromemoriaTask extends TimerTask {

    private String messaggio;

    public PromemoriaTask(String messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public void run() {
        System.out.println("🔔 PROMEMORIA: " + messaggio);
    }
}
```

E nel `Main`:

```java
import java.util.Timer;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();

        PromemoriaTask promemoria1 = new PromemoriaTask("Bevi un bicchiere d'acqua!");
        PromemoriaTask promemoria2 = new PromemoriaTask("Fai una pausa dagli schermi.");

        // Dopo 5 secondi
        timer.schedule(promemoria1, 5000);

        // Dopo 10 secondi
        timer.schedule(promemoria2, 10000);

        System.out.println("Promemoria programmati.");
    }
}
```

### Vantaggi della classe dedicata

* Il codice nel `run()` è **separato**, più leggibile.
* Puoi aggiungere campi, costruttori, metodi di supporto.
* Se in futuro vuoi riutilizzare lo stesso tipo di task in altri punti del programma, hai già una classe pronta.

---

## 6. `schedule()` vs `scheduleAtFixedRate()`

Esistono due metodi principali per l’esecuzione periodica:

1. `schedule(task, delay, period)`
2. `scheduleAtFixedRate(task, delay, period)`

La differenza è sottile ma importante:

* **`schedule()`**
  Cerca di eseguire il task **“a ritardo fisso”**:
  ogni nuova esecuzione è programmata **`period` millisecondi dopo la fine di quella precedente** (se il task ritarda, le esecuzioni si spostano in avanti).

* **`scheduleAtFixedRate()`**
  Cerca di mantenere una **frequenza costante**:
  le esecuzioni sono programmate a multipli fissi del tempo iniziale, **anche se una esecuzione ritarda**.
  Se un’esecuzione arriva in ritardo, il timer può tentare di “recuperare” eseguendo i task più ravvicinati.

Per il tuo uso scolastico, nella maggior parte dei casi ti basta:

```java
timer.schedule(task, delay, period);
```

---

## 7. Attenzione: `Timer` usa **un solo thread**

Dettaglio importante (molto “da programmatore serio”):

* `Timer` usa **un solo thread** per eseguire *tutti* i `TimerTask`.
* Se un task impiega tanto tempo, **blocca gli altri**.
* Esempio: se un `run()` fa una `Thread.sleep(10000);`, gli altri task restano in coda e partono tardi.

Per progetti seri si preferisce spesso `ScheduledExecutorService`, ma per:

* esercizi scolastici
* piccoli progettini
* demo

`Timer` + `TimerTask` è perfetto e molto più semplice da capire.

---

## 8. Mini-project: “cuore” di un piccolo cronometro / reminder

Facciamo un esempio completo in stile “progettino didattico”:

* Ogni secondo stampiamo il “secondo n°”.
* Dopo 5 secondi, smettiamo.

```java
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {

        Timer timer = new Timer();

        // Task che conta i secondi
        TimerTask contatoreTask = new TimerTask() {

            private int secondiTrascorsi = 0;

            @Override
            public void run() {
                secondiTrascorsi++;
                System.out.println("Secondi trascorsi: " + secondiTrascorsi);

                if (secondiTrascorsi >= 5) {
                    System.out.println("Ho raggiunto i 5 secondi, mi fermo.");
                    timer.cancel(); // fermo il timer qui dentro
                }
            }
        };

        // Avvia dopo 1 secondo, ripeti ogni 1 secondo
        timer.schedule(contatoreTask, 1000, 1000);

        System.out.println("Cronometro avviato.");
    }
}
```

Qui hai:

* un **campo** interno (`secondiTrascorsi`) dentro il `TimerTask`;
* un **controllo logico** nel `run()` che decide quando fermare tutto;
* l’uso di `timer.cancel()` dall’interno del task stesso.
