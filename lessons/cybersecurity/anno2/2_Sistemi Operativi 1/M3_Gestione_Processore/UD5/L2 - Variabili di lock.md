# **M3 UD5 Lezione 2 - Variabili di lock**

---

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

L'algoritmo impone che i processi **si alternino obbligatoriamente**, anche se uno di essi non ha bisogno della risorsa: se $P_0$ vuole entrare due volte di seguito ma $P_1$ non ne ha bisogno, $P_0$ deve comunque **aspettare** che $P_1$ esegua almeno una volta la propria sezione critica per "passargli" il turno. Questo viola il requisito di **progresso**: la decisione di chi entra dovrebbe spettare solo ai processi che vogliono effettivamente entrare.

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
\textbf{Mutua esclusione:}~ & \text{garantita.} \\\\  
\textbf{Alternanza forzata:}~ & \text{eliminata (un processo può rientrare se l'altro non è interessato).} \\\\
\textbf{Progresso:}~ & \text{non garantito.} \\\\  
\textbf{Problema:}~ & \text{possibile attesa infinita (deadlock o starvation reciproci).}  
\end{cases}  
$$

L'algoritmo 2 **migliora** rispetto al primo perché un processo può entrare nella sezione critica quando vuole, **senza dover attendere il turno** dell'altro se questo non sta cercando di entrare. Tuttavia:

- se **entrambi** i processi settano il proprio `flag = true` quasi contemporaneamente, **prima** di entrare nel `while`, ciascuno vede il flag dell'altro a `true` e **attende per sempre**: **deadlock**;
- in scenari meno gravi, un processo (es. $P_0$) potrebbe sempre vincere la "corsa" sull'altro ($P_1$), che subisce **starvation**.

---
#### **3.3. Algoritmo 3 – Peterson**

L'**Algoritmo di Peterson** combina **flag di intenzione** e **variabile di turno**, risolvendo i limiti precedenti.  
È una delle soluzioni più note ed eleganti per la mutua esclusione a due processi.

##### **Logica di funzionamento**

L'idea chiave per evitare il deadlock dell'algoritmo 2 è **cedere cortesemente il turno all'altro processo** prima di tentare l'ingresso:

1. il processo che vuole entrare imposta la variabile `turn` con l'**identificatore dell'altro** (gesto di cortesia);
2. poi setta il proprio `flag = true` (dichiarazione di volontà);
3. attende finché **entrambe** le condizioni sono vere: «l'altro vuole entrare **AND** è il turno dell'altro».

Se **due processi** invocano la procedura quasi contemporaneamente, **l'ultimo dei due** che esegue l'assegnamento a `turn` sovrascrive il valore precedente; di conseguenza `turn` indicherà l'identità dell'altro processo, e **il primo arrivato passa** (perché vedrà `turn` puntato a sé). Questo elegante meccanismo elimina la possibilità di stallo reciproco.

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

##### **Perché serve l'atomicità**

Anche con una semplice variabile `lock`, l'operazione di acquisizione richiede una **sequenza di tre passi**: (1) leggere il valore corrente, (2) decidere cosa fare, (3) modificare il valore se la risorsa era libera. Questa sequenza **non è atomica** in un sistema time-sharing: un'interruzione tra il passo 1 e il passo 3 potrebbe permettere a un altro processo di "infilarsi" in mezzo, leggere lo stesso `lock = 0` e acquisire anch'esso la risorsa — **violando la mutua esclusione**.

##### **Soluzione: disabilitare le interruzioni**

Un approccio elementare consiste nel **disabilitare temporaneamente le interruzioni hardware** per rendere atomica l'intera sequenza:

- **Acquisizione della risorsa:**
    1. Disabilito le interruzioni.
    2. Controllo la variabile `lock`.
    3. Se `lock = 0`, la imposto a `1`, riabilito le interruzioni e procedo.
    4. Se `lock = 1`, riabilito le interruzioni e attendo.
- **Rilascio della risorsa:**

    ```c
    lock = 0;
    ```

Questo metodo garantisce **mutua esclusione**, ma è **non scalabile** nei sistemi multiprocessore: disabilitare le interruzioni su un core **non blocca gli altri core**, che potrebbero comunque accedere alla variabile `lock`. Inoltre, disabilitare globalmente le interruzioni di tutti i processori sarebbe inaccettabile dal punto di vista delle prestazioni complessive del sistema.

---
### **5. Supporto hardware – Istruzioni atomiche**

Per superare i limiti della disabilitazione delle interruzioni (in particolare la **non scalabilità** ai sistemi multiprocessore), i moderni processori offrono **istruzioni macchina atomiche** che permettono di implementare i lock in modo efficiente e sicuro, **senza** dover toccare le interruzioni.

Essendo istruzioni macchina (non sequenze), sono **per definizione indivisibili**: nessuna interruzione né accesso da altri core può "spezzarne" l'esecuzione. Una delle più note è **TEST-AND-SET**.

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

> ⚠️ **Avvertenza importante.** Tutti gli approcci visti in questa lezione operano **a livello di istruzione** (variabili di turno, lock, test-and-set). Sono **strumenti di basso livello** che richiedono **enorme attenzione da parte del programmatore** per essere usati correttamente: un singolo errore (un'assegnazione fuori ordine, un flag dimenticato, una condizione mal scritta) può facilmente introdurre race condition, deadlock o starvation difficili da diagnosticare.

Nelle lezioni successive vedremo come questi principi vengano generalizzati nei **semafori**, che estendono il concetto di lock a più processi e operazioni di attesa condizionata — fornendo astrazioni di **più alto livello** che riducono il margine di errore del programmatore.
