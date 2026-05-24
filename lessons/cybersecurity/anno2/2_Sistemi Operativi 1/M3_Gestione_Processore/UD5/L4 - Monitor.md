# **M3 UD5 Lezione 4 - Monitor**

---

### **1. Introduzione**

Nelle lezioni precedenti abbiamo visto come la sincronizzazione possa essere realizzata con **semafori**.  
Tuttavia, i semafori presentano un problema serio: la loro **correttezza dipende interamente dal programmatore**.

Un uso errato delle operazioni `acquire()` e `release()` può facilmente introdurre **violazioni della mutua esclusione**, **deadlock** o **attese infinite**.  
Il sistema operativo non ha modo di accorgersene, poiché le operazioni sui semafori sono **primitive** — semplici chiamate di sistema.

Per ovviare a questo rischio, i linguaggi di programmazione moderni introducono un **livello più alto di astrazione**: il **monitor**.

---
### **2. Problemi dei semafori**

$$  
\begin{cases}  
\textbf{Violazioni della mutua esclusione:}~ & \text{più processi accedono simultaneamente alla stessa risorsa.} \\\\  
\textbf{Attese infinite:}~ & \text{un processo resta bloccato per errore logico.} \\\\  
\textbf{Responsabilità del programmatore:}~ & \text{nessun controllo automatico da parte del sistema operativo.}  
\end{cases}  
$$

Il sistema operativo esegue i semafori **solo se usati correttamente** — ma non può verificarlo.  
Serve dunque un meccanismo che **forzi automaticamente l’uso corretto** della sincronizzazione.

---
### **3. Obiettivo del monitor**

L’obiettivo del **monitor** è proprio questo:  
$$  
\text{Innalzare il livello di astrazione della sincronizzazione, garantendone la correttezza.}  
$$

Un monitor è un **costrutto linguistico** del linguaggio di programmazione,  
che il **compilatore** traduce automaticamente nelle **chiamate corrette** al sistema operativo.  
In questo modo il programmatore non può più sbagliare la logica di sincronizzazione.

---
### **4. Definizione di monitor**

Un **monitor** è un **modulo di sincronizzazione** che incapsula:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Variabili condivise.} \\\\  
\textbf{2.}~ & \text{Procedure di accesso (entry procedures).} \\\\  
\textbf{3.}~ & \text{Meccanismi di sincronizzazione interna.}  
\end{cases}  
$$

Il monitor garantisce automaticamente che **solo un processo alla volta** possa essere attivo al suo interno.  
Tutti gli altri processi che invocano le sue procedure vengono **sospesi in attesa**.

#### **Esempio di sintassi generale**

```java
monitor BufferCondiviso {

    int buffer[N];
    int count = 0;

    public entry void inserisci(int valore) {
        // codice per inserire un elemento
    }

    public entry int preleva() {
        // codice per prelevare un elemento
    }
}
```

- Le **entry procedures** sono le uniche funzioni accessibili dall’esterno.
    
- Tutte le variabili e le risorse interne del monitor sono **protette automaticamente**.
    
- L’accesso è **mutuamente esclusivo**: se un processo sta eseguendo `inserisci()`,  
    un altro che chiama `preleva()` dovrà attendere.

---
### **5. Realizzazione e funzionamento**

#### **5.1. Accesso controllato**

All'interno del monitor, il sistema di sincronizzazione è **implicito**:  
il compilatore genera automaticamente le chiamate ai meccanismi di blocco e sblocco.

##### **Implementazione: variabile condizione associata**

Per realizzare la mutua esclusione, il compilatore associa al monitor una **variabile condizione** che rappresenta lo **stato di uso** del monitor (libero o occupato). Concettualmente, questa variabile **coincide con un semaforo binario**: viene inizializzata a `1` (libero) e viene manipolata automaticamente all'ingresso e all'uscita di ogni entry procedure.

$$  
\begin{cases}  
\text{Un solo processo}~ & \text{può entrare nel monitor alla volta.} \\\\  
\text{Gli altri processi}~ & \text{vengono sospesi fino al rilascio.}  
\end{cases}  
$$

Quando il processo corrente esce da una **entry procedure**,  
il sistema operativo **riattiva automaticamente** uno dei processi sospesi.

##### **Esempio narrativo: due processi P e Q sullo stesso monitor**

1. Stato iniziale: condizione = `1` (monitor libero).
2. Arriva **P** e chiama una entry procedure del monitor. La condizione passa a `0`, P **entra** nella sezione critica.
3. Arriva **Q** e prova a chiamare un'altra entry procedure dello stesso monitor. Trovando la condizione = `0`, **non può procedere**: il sistema attiva automaticamente una primitiva di **`wait`** che pone Q nella **coda di attesa** del monitor.
4. **P** termina la propria entry procedure. Il sistema automaticamente:
    - rilascia il monitor;
    - vede che esiste un processo pendente (Q) ed esegue una primitiva di **`signal`** per **risvegliarlo**;
    - cede a Q l'uso del monitor.
5. **Q** procede nella propria computazione. Al termine, rilascia il monitor e **ripristina la condizione a `1`** (libero) — pronto per i prossimi processi.

Il programmatore non scrive **nessuna** delle chiamate a `wait` e `signal` di accesso al monitor: sono **completamente generate dal compilatore** a partire dal costrutto `monitor`.

---
#### **5.2. Condizioni di sincronizzazione**

All’interno del monitor possono essere definite **variabili di condizione**,  
che gestiscono la sospensione selettiva dei processi.

Esempio concettuale:

```java
condition full, empty;

public entry void inserisci(int x) {
    if (count == N) full.wait();   // attende spazio disponibile
    buffer[in] = x;
    count++;
    empty.signal();                // risveglia eventuali consumatori
}

public entry int preleva() {
    if (count == 0) empty.wait();  // attende nuovi elementi
    int x = buffer[out];
    count--;
    full.signal();                 // risveglia eventuali produttori
    return x;
}
```

Questo codice implementa in modo naturale il **problema produttore–consumatore**,  
ma senza semafori: le operazioni `wait()` e `signal()` sono **gestite dal compilatore** e **garantiscono automaticamente la mutua esclusione**.

---
### **6. Uso dei monitor**

#### **6.1. Vantaggi principali**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Mutua esclusione automatica.} \\\\  
\textbf{2.}~ & \text{Controllo del compilatore, non del programmatore.} \\\\  
\textbf{3.}~ & \text{Codice più leggibile e sicuro.} \\\\  
\textbf{4.}~ & \text{Eliminazione dei deadlock causati da errori logici.}  
\end{cases}  
$$

#### **6.2. Limiti**

- Il monitor è **un costrutto di linguaggio**, quindi dipende dal **supporto del compilatore**.
    
- Non tutti i linguaggi lo implementano direttamente (es. C ne è privo, mentre Java ne integra i principi con `synchronized` e `wait()/notify()`).

---
### **7. Sintesi finale**

$$  
\begin{cases}  
\textbf{Semafori:}~ & \text{strumenti di basso livello, il cui uso corretto è responsabilità del programmatore.} \\\\  
\textbf{Monitor:}~ & \text{costrutti di alto livello, che garantiscono la mutua esclusione automaticamente.} \\\\  
\textbf{Implementazione:}~ & \text{a livello di linguaggio, con supporto del compilatore.} \\\\  
\textbf{Vantaggio:}~ & \text{sincronizzazione sicura e semplice da usare.}  
\end{cases}  
$$

---
### **8. Conclusione**

Il **monitor** rappresenta il naturale passo successivo nell'evoluzione dei meccanismi di sincronizzazione.  
Mentre i semafori richiedono al programmatore di gestire manualmente la correttezza del codice,  
il monitor **sposta la responsabilità al linguaggio e al compilatore**, garantendo che solo un processo alla volta acceda alle risorse condivise.

Si tratta dunque di un **approccio di alto livello**, che riduce gli errori, aumenta la leggibilità del codice e offre una sincronizzazione intrinsecamente sicura.

> ⚠️ **Limite residuo: bypass del monitor.** Il monitor garantisce un uso corretto delle risorse condivise **solo per i programmi che effettivamente lo utilizzano**. Se un programma "scorretto" cerca di accedere **direttamente** alla risorsa — bypassando le entry procedure del monitor — **non c'è alcuna garanzia** che la gestione resti corretta. La protezione del monitor è quindi limitata al **perimetro del costrutto**: la disciplina d'accesso deve essere rispettata da tutti i programmi che condividono la risorsa.
