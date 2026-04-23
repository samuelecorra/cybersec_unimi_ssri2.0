# 🟥 **LEZIONE COMPLETA: MULTITHREADING IN JAVA**

## 🔵 1. Cos’è il multithreading?

Il **multithreading** permette a un programma Java di eseguire **più parti di codice contemporaneamente**.

Un *thread* è:

> Un flusso di istruzioni indipendente che può lavorare in parallelo con altri.

Esempi reali:

* scaricare file mentre aggiorni una progress bar
* riprodurre musica mentre l'interfaccia rimane responsiva
* gestire richieste di rete contemporanee
* fare operazioni lente in background senza bloccare tutto

---

# 🟦 2. Come Java gestisce i thread

Java ha una classe fondamentale:

```java
java.lang.Thread
```

Ogni thread esegue il suo codice dentro:

```java
public void run()
```

Per avviarlo si usa:

```java
thread.start();
```

⚠ IMPORTANTISSIMO
→ **start() crea un nuovo thread**
→ **run() NON crea un nuovo thread** (si comporta come un metodo normale)

---

# 🟩 3. Ci sono due modi per creare un thread

Java ti permette due tecniche:

### ✔ Opzione 1 — **Estendere la classe Thread**

Più semplice, meno flessibile.

### ✔ Opzione 2 — **Implementare Runnable**

Migliore, consigliata sempre.

Li vediamo entrambi con codice perfetto.

---

# 🟥 4. OPTION 1 – Estendere la classe Thread

### ✨ Classe personalizzata che si comporta da thread:

```java
class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread → " + i);

            try {
                Thread.sleep(500); // pausa di 0.5 secondi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 🟦 Avvio nel main:

```java
public class Main {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();   // crea realmente un nuovo thread
        t2.start();
    }
}
```

### ✨ Output (interleaved):

```
Thread → 1
Thread → 1
Thread → 2
Thread → 2
...
```

Le stampe si mescolano = multithreading reale.

---

# 🟩 5. OPTION 2 – Implementare Runnable (modo migliore)

Questa è la modalità professionale, perché puoi ancora estendere altre classi.

### ✨ Classe Runnable:

```java
class MyTask implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Runnable task → " + i);

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 🟦 Avvio:

```java
public class Main {
    public static void main(String[] args) {

        Thread t1 = new Thread(new MyTask());
        Thread t2 = new Thread(new MyTask());

        t1.start();
        t2.start();
    }
}
```

---

# 🟧 6. Versione moderna con Lambda (Java 8+)

Essendo Runnable un’interfaccia funzionale:

```java
public class Main {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Lambda thread: " + i);
            }
        });

        t.start();
    }
}
```

Pulitissimo.

---

# 🟦 7. Thread.sleep() – come funziona davvero

`Thread.sleep(ms)` sospende *solo il thread chiamante*, non tutti.

Esempio:

* il Thread A va in sleep → Thread B continua
* tutto avviene in parallelo

---

# 🟥 8. Thread naming per debugging professionale

```java
Thread t1 = new Thread(new MyTask(), "Downloader");
Thread t2 = new Thread(new MyTask(), "Uploader");

t1.start();
t2.start();
```

Ora:

```java
System.out.println(t1.getName());
```

---

# 🟧 9. Controllare la concorrenza dei thread (join)

```java
t1.join(); // aspetta che t1 finisca
t2.join();
```

Questo è utile quando vuoi:

* scaricare file
* aspettare che entrambi siano finiti
* procedere all’elaborazione

---

# 🟪 10. Thread priorities

```java
t1.setPriority(Thread.MAX_PRIORITY); // 10
t2.setPriority(Thread.MIN_PRIORITY); // 1
```

Non garantisce l’ordine, ma influenza la scheduler JVM.

---

# 🟩 11. Daemon Thread (thread "di servizio")

Un daemon thread è un thread che NON impedisce la chiusura del programma.

```java
t.setDaemon(true);
```

Esempi:

* auto-save
* pulizia dati
* task in background invisibili

---

# 🟥 12. Esempio pratico completo (progress bar + worker)

### Worker thread

```java
class Worker implements Runnable {
    @Override
    public void run() {
        System.out.println("Inizio lavoro pesante...");
        try { Thread.sleep(4000); } catch (Exception ignored) {}
        System.out.println("Lavoro completato!");
    }
}
```

### Progress bar thread

```java
class LoadingBar implements Runnable {
    @Override
    public void run() {
        String anim = "|/-\\";
        for (int i = 0; i < 30; i++) {
            System.out.print("\rCaricamento " + anim.charAt(i % anim.length()));
            try { Thread.sleep(120); } catch (Exception ignored) {}
        }
    }
}
```

### Main

```java
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(new Worker());
        Thread loading = new Thread(new LoadingBar());
        loading.setDaemon(true);

        worker.start();
        loading.start();

        worker.join();   // aspetto che il lavoro finisca

        System.out.println("\nFine programma");
    }
}
```

---

