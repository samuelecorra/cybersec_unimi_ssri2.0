# **M3 UD5 Lezione 2 - Variabili di lock**

### **1. Introduzione**

Nella lezione precedente abbiamo visto come la concorrenza possa portare a conflitti quando più processi accedono a **risorse condivise** senza coordinamento.  
In questa lezione studiamo come ottenere la **mutua esclusione** — ossia l’accesso esclusivo di un solo processo alla volta — attraverso **variabili di turno** e **variabili di lock**.

Si tratta di approcci a **livello di istruzioni**, cioè implementazioni **software o hardware** molto vicine alla CPU, che costituiscono la base dei meccanismi di sincronizzazione più complessi (come semafori e monitor).

---
### **2. Variabile di turno**

#### **2.1. Concetto**

Una **variabile di turno** è una variabile condivisa che indica **a quale processo spetta l’uso della risorsa** in un dato momento.  
Serve a **sincronizzare due processi concorrenti**, garantendo che accedano alla loro **sezione critica** in modo alternato.

$$  
\text{turn} =  
\begin{cases}  
0, & \text{se è il turno del processo } P_0; \\\\  
1, & \text{se è il turno del processo } P_1.  
\end{cases}  
$$

---
### **3. Algoritmi basati su variabile di turno**

#### **3.1. Algoritmo 1 – Turno rigido**

Il primo algoritmo assegna il turno a un solo processo per volta.

**Codice Java (semplificato):**

```java
private volatile int turn = 0;

public void enteringCriticalSection(int t) {
    while (turn != t)
        Thread.yield(); // attesa attiva
}

public void leavingCriticalSection(int t) {
    turn = 1 - t;
}
```

**Analisi:**

$$  
\begin{cases}  
\textbf{Mutua esclusione:}~ & \text{garantita.} \\\\  
\textbf{Progresso:}~ & \text{non garantito.} \\\\  
\textbf{Alternanza:}~ & \text{stretta — i processi si alternano rigidamente.}  
\end{cases}  
$$

L’algoritmo impone che i processi **si alternino obbligatoriamente**, anche se uno di essi non ha bisogno della risorsa.  
Questa limitazione viene eliminata negli algoritmi successivi.

---
#### **3.2. Algoritmo 2 – Flag di stato**

Qui si usano **flag booleani** per indicare l’intenzione di entrare nella sezione critica.

```java
private volatile boolean flag0 = false, flag1 = false;

public void enteringCriticalSection(int t) {
    if (t == 0) {
        flag0 = true;
        while (flag1 == true)
            Thread.yield();
    } else {
        flag1 = true;
        while (flag0 == true)
            Thread.yield();
    }
}

public void leavingCriticalSection(int t) {
    if (t == 0) flag0 = false; else flag1 = false;
}
```

**Analisi:**

$$  
\begin{cases}  
\textbf{Mutua esclusione:}~ & \text{non garantita in tutti i casi.} \\\\  
\textbf{Progresso:}~ & \text{non garantito.} \\\\  
\textbf{Problema:}~ & \text{possibile attesa infinita (busy waiting reciproco).}  
\end{cases}  
$$

I due processi potrebbero rimanere **bloccati entrambi**, se impostano contemporaneamente i loro flag prima che uno entri nella sezione critica.

---
#### **3.3. Algoritmo 3 – Peterson**

L’**Algoritmo di Peterson** combina **flag di intenzione** e **variabile di turno**, risolvendo i limiti precedenti.  
È una delle soluzioni più note ed eleganti per la mutua esclusione a due processi.

```java
private volatile boolean flag0 = false, flag1 = false;
private volatile int turn = 0;

public void enteringCriticalSection(int t) {
    int other = 1 - t;
    turn = other;
    if (t == 0) {
        flag0 = true;
        while (flag1 && turn == other)
            Thread.yield();
    } else {
        flag1 = true;
        while (flag0 && turn == other)
            Thread.yield();
    }
}

public void leavingCriticalSection(int t) {
    if (t == 0) flag0 = false; else flag1 = false;
}
```

**Analisi:**

$$  
\begin{cases}  
\textbf{Mutua esclusione:}~ & \text{garantita.} \\\\  
\textbf{Progresso:}~ & \text{garantito.} \\\\  
\textbf{Attesa limitata:}~ & \text{rispettata.}  
\end{cases}  
$$

Questo algoritmo rappresenta una **soluzione software perfetta** per due processi, ma non scala facilmente a un numero maggiore di processi.

---
### **4. Variabile di lock**

#### **4.1. Concetto**

Una **variabile di lock** è una variabile condivisa che rappresenta **lo stato di una risorsa**:

$$  
\text{lock} =  
\begin{cases}  
0, & \text{risorsa libera;} \\  
1, & \text{risorsa occupata (in uso da un processo).}  
\end{cases}  
$$

Quando un processo vuole entrare nella sua **sezione critica**, deve prima **acquisire il lock**.  
Quando termina, deve **rilasciarlo**.

---
#### **4.2. Implementazione con disabilitazione delle interruzioni**

Un approccio elementare consiste nel **disabilitare temporaneamente le interruzioni hardware** durante l’accesso alla sezione critica:

- **Acquisizione della risorsa:**
    
    1. Disabilito le interruzioni.
        
    2. Controllo la variabile `lock`.
        
    3. Se `lock = 0`, la imposto a `1` e procedo.
        
    4. Se `lock = 1`, riabilito le interruzioni e attendo.
        
- **Rilascio della risorsa:**
    
    ```c
    lock = 0;
    ```

Questo metodo garantisce **mutua esclusione**, ma è pericoloso nei sistemi multiprocessore: disabilitare le interruzioni su un core **non blocca gli altri**.

---
### **5. Supporto hardware – Istruzioni atomiche**

I moderni processori offrono **istruzioni atomiche** per implementare i lock in modo efficiente e sicuro, senza disabilitare le interruzioni.  
Una delle più note è **TEST-AND-SET**.

#### **5.1. Istruzione TEST-AND-SET**

- Legge la variabile `lock`.
    
- Imposta `lock = 1`.
    
- Restituisce il **vecchio valore** di `lock` in un flag interno.

```c
boolean TestAndSet(boolean *lock) {
    boolean old = *lock;
    *lock = true;
    return old;
}
```

#### **5.2. Esempio di utilizzo**

```c
while (TestAndSet(&lock))
    ; // attesa attiva (busy waiting)

critical_section();

lock = false;
```

**Funzionamento:**

- Se `lock` era `false`, la funzione lo imposta a `true` e il processo entra.
    
- Se `lock` era `true`, il processo resta in attesa.

L’operazione è **atomica**, quindi nessun altro processo può leggere e modificare `lock` contemporaneamente.

---
### **6. Sintesi finale**

$$  
\begin{cases}  
\textbf{Variabile di turno:}~ & \text{definisce a chi spetta usare la risorsa (Peterson).} \\\\  
\textbf{Variabile di lock:}~ & \text{definisce se la risorsa è libera o occupata.} \\\\  
\textbf{Implementazioni:}~ & \text{software (turni) o hardware (istruzioni atomiche).} \\\\  
\textbf{Obiettivo:}~ & \text{realizzare la mutua esclusione in modo efficiente e sicuro.}  
\end{cases}  
$$

---
### **7. Conclusione**

Le **variabili di turno** e le **variabili di lock** rappresentano le fondamenta di tutti i meccanismi di sincronizzazione.  
Gli algoritmi software, come quello di Peterson, mostrano la logica della cooperazione ordinata;  
i **lock hardware**, invece, offrono soluzioni efficienti per architetture reali.

Nelle lezioni successive vedremo come questi principi vengano generalizzati nei **semafori**, che estendono il concetto di lock a più processi e operazioni di attesa condizionata.