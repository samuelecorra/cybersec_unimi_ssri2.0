# 🟥 **LEZIONE COMPLETA E ATOMICA: THREADING IN JAVA**

Questa è una lezione *Feynman-style*, super chiara, super esplicita, con codice perfetto da incollare in IntelliJ.

---

# 🔵 **1. Cos’è il Threading (multithreading) in Java**

Un **thread** è come un “mini-programma” che vive dentro il tuo programma principale.

Java permette di eseguire **più thread contemporaneamente**, così che il programma possa fare più cose allo stesso tempo.

### 🎯 Perché è utile?

Perché migliora enormemente le prestazioni quando devi fare operazioni:

* lente (file I/O)
* di rete
* attese
* operazioni pesanti non-blocking
* compiti in background

---

# 🔵 **2. Due modi per creare un Thread**

Ci sono esattamente due approcci ufficiali:

### **Opzione 1 — Estendere la classe `Thread` (più semplice, meno flessibile)**

### **Opzione 2 — Implementare l’interfaccia `Runnable` (migliore, più professionale)**

In entrambi i casi devi definire **il contenuto del thread** dentro il metodo:

```java
public void run()
```

Il thread parte usando:

```java
thread.start();
```

⚠ **IMPORTANTE:**
`start()` crea un *nuovo thread*.
`run()` viene eseguito **nello stesso thread**, non crea nulla → NON usarlo per avviare.

---

# 🟥 **3. OPZIONE 1 – Estendere la classe Thread**

(*Uso didattico, immediato, perfetto da capire*)

## 🔧 **Esempio completo**

```java
class MyThread extends Thread {

    @Override
    public void run() {
        // Questo è il codice che il thread eseguirà
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread esteso → " + i);

            try {
                Thread.sleep(500); // pausa di mezzo secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 🔥 Avvio dei thread nel main:

```java
public class Main {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();  
        t2.start();  
    }
}
```

### 🔍 Cosa succede?

* `t1` e `t2` CORRONO davvero in parallelo.
* L’output non sarà ordinato → normale, è multithreading.

---

# 🟩 **4. OPZIONE 2 – Implementare Runnable (la scelta migliore)**

**Perché è migliore?**

* Non sprechi l'estensione di classe (Java non ha ereditarietà multipla).
* Più flessibile.
* Più pulito.
* È lo standard industriale.

## 🔧 **Esempio perfetto**

```java
class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Runnable → " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 🔥 Avvio:

```java
public class Main {
    public static void main(String[] args) {

        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());

        t1.start();
        t2.start();
    }
}
```

---

# 🟧 **5. Possiamo migliorarlo con le Lambda (Java moderno)**

Se l'interfaccia ha **un solo metodo**, come Runnable, puoi scrivere:

```java
Thread t = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("Lambda thread: " + i);
    }
});
t.start();
```

Pulito, breve, professionale.

---

# 🟥 **6. Thread.sleep() – che succede davvero?**

`Thread.sleep(ms)`
→ mette il thread in pausa per il tempo richiesto
→ **NON blocca gli altri thread**.

---

# 🟦 **7. Quando scegliere Thread vs Runnable**

| Metodo                    | Vantaggi                           | Svantaggi                                      |
| ------------------------- | ---------------------------------- | ---------------------------------------------- |
| **Estendere Thread**      | più semplice, didattico            | perdi la possibilità di estendere altre classi |
| **Implementare Runnable** | moderno, flessibile, usato ovunque | un po’ più verboso senza lambda                |

Regola generale:

### 👉 **Sempre meglio Runnable (o lambda).**

---

# 🟥 **8. Thread naming – importantissimo per debugging**

```java
Thread t = new Thread(new MyRunnable(), "CaricatoreFile");
t.start();

System.out.println(t.getName());
```

---

# 🟧 **9. Esempio realistico – progress bar fake**

```java
class LoadingBar implements Runnable {

    @Override
    public void run() {
        String anim = "|/-\\";
        for (int i = 0; i < 20; i++) {
            char c = anim.charAt(i % anim.length());
            System.out.print("\rCaricamento " + c);
            try { Thread.sleep(150); } catch (Exception e) {}
        }
    }
}
```

Main:

```java
public class Main {
    public static void main(String[] args) {
        Thread loading = new Thread(new LoadingBar());
        loading.start();
    }
}
```

---

