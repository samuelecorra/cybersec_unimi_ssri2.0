# **M3 UD5 Lezione 3 - Semafori**

### **1. Introduzione**

Dopo aver studiato le **variabili di lock** e le **istruzioni atomiche**, possiamo ora innalzare il livello di astrazione introducendo un nuovo strumento fondamentale: il **semaforo**.  
Il semaforo è una **funzione del sistema operativo** che consente di **sincronizzare i processi** e **controllare l’accesso alle risorse condivise** in modo sicuro e strutturato.

L’idea alla base è semplice:  
un semaforo è una **variabile intera protetta**, su cui agiscono due sole operazioni atomiche:

- `acquire(S)` (o `wait`, `P`) – per richiedere l’uso della risorsa;
    
- `release(S)` (o `signal`, `V`) – per rilasciare la risorsa.

Queste operazioni sono **atomiche**, cioè non possono essere interrotte: il sistema operativo garantisce che non si verifichino condizioni di corsa o incoerenze durante la loro esecuzione.

---
### **2. Obiettivi del semaforo**

L’introduzione dei semafori risolve tre problemi chiave della sincronizzazione:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Evitare gli errori nell’uso delle variabili di lock o di turno.} \\\\  
\textbf{2.}~ & \text{Evitare la disabilitazione manuale delle interruzioni, rischiosa nei sistemi multiprocessore.} \\\\  
\textbf{3.}~ & \text{Affidare la gestione della mutua esclusione al sistema operativo, in modo controllato.}  
\end{cases}  
$$

---
### **3. Semaforo binario**

#### **3.1. Definizione**

Un **semaforo binario** è una variabile $S$ che può assumere solo due valori:

$$  
S =  
\begin{cases}  
1, & \text{la risorsa è libera;} \\\\  
0, & \text{la risorsa è occupata.}  
\end{cases}  
$$

#### **3.2. Operazioni fondamentali**

- **`acquire(S)`** → richiede l’uso della risorsa.  
    Se $S = 1$, il processo entra nella sezione critica e il semaforo diventa $0$.  
    Se $S = 0$, il processo resta in attesa.
    
- **`release(S)`** → rilascia la risorsa.  
    Imposta $S = 1$ e consente l’accesso ad altri processi eventualmente in coda.

Entrambe le funzioni sono **atomiche**, poiché vengono implementate direttamente dal kernel come **procedure di sistema**.

---
### **4. Uso del semaforo binario**

Esempio schematico:

```c
Semaphore S = 1;

acquire(S);        // richiesta risorsa
criticalSection(); // sezione critica
release(S);        // rilascio risorsa
```

---
### **5. Implementazione**

#### **5.1. Caso 1 – Attesa attiva**

Il metodo più semplice per gestire un semaforo è la **busy waiting**:  
il processo rimane in un ciclo di attesa finché la risorsa non diventa disponibile.

$$  
\begin{cases}  
\textbf{Struttura dati:}~ & \text{variabile binaria } S. \\\\  
\textbf{Comportamento:}~ & \text{il processo resta in attesa attiva su } S = 0. \\\\  
\textbf{Problema:}~ & \text{spreco di tempo CPU e inefficienza.}  
\end{cases}  
$$

---
#### **5.2. Caso 2 – Sospensione e rischedulazione**

Per evitare sprechi di CPU, il sistema operativo può **sospendere** i processi che attendono la risorsa, mantenendoli in una **coda di attesa** associata al semaforo.

$$  
\begin{cases}  
\textbf{Strutture dati:}~ & \text{variabile binaria } S + \text{coda dei processi in attesa.} \\\\  
\textbf{Comportamento:}~ &  
\begin{cases}  
\text{Se } S = 1, & \text{il processo entra nella sezione critica.} \\\\  
\text{Se } S = 0, & \text{il processo viene sospeso e accodato.}  
\end{cases} \\\\  
\textbf{Al rilascio:}~ & \text{`release(S)` riattiva il primo processo in coda.}  
\end{cases}  
$$

Lo **scheduler** decide l’ordine con cui i processi accedono alla risorsa,  
secondo la politica adottata (tipicamente FIFO).

---
### **6. Semaforo generalizzato**

#### **6.1. Definizione**

Un **semaforo generalizzato** è una variabile intera $S$ che rappresenta **n risorse identiche** condivise.

$$  
S =  
\begin{cases}  
n, & \text{ci sono } n \text{ risorse libere;} \\\\  
0, & \text{tutte le risorse sono in uso.}  
\end{cases}  
$$

#### **6.2. Operazioni**

- **`acquire(S)`** → decrementa $S$ se $S > 0$;  
    se $S = 0$, il processo viene sospeso.
    
- **`release(S)`** → incrementa $S$, segnalando che una risorsa è stata liberata.

In questo modo, più processi possono accedere contemporaneamente alla risorsa, purché il numero complessivo non superi la capacità $n$.

---
#### **6.3. Esempio pratico**

Supponiamo che tre processi ($P_1, P_2, P_3$) condividano un pool di **due stampanti**:

```c
Semaphore printer = 2;

acquire(printer);      // prenota una stampante
printDocument();       // utilizzo
release(printer);      // libera la stampante
```

- Quando due processi stanno stampando, il terzo rimane sospeso.
    
- Al rilascio, il sistema operativo riattiva il primo processo in attesa.

---
### **7. Vantaggi dei semafori**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Eliminano la necessità di manipolare variabili di lock o interruzioni.} \\\\  
\textbf{2.}~ & \text{Sono gestiti dal sistema operativo, quindi sicuri e portabili.} \\\\  
\textbf{3.}~ & \text{Consentono sia la mutua esclusione (binari) che la cooperazione (generalizzati).} \\\\  
\textbf{4.}~ & \text{Permettono la sospensione e rischedulazione dei processi in modo efficiente.}  
\end{cases}  
$$

---
### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Semaforo binario:}~ & \text{1 risorsa alla volta (mutua esclusione).} \\\\  
\textbf{Semaforo generalizzato:}~ & \text{insieme di risorse identiche, fino a } n. \\\\  
\textbf{Operazioni:}~ & \text{`acquire(S)` e `release(S)`, atomiche.} \\\\  
\textbf{Implementazione:}~ & \text{attesa attiva o sospensione con coda.} \\\\  
\textbf{Gestione:}~ & \text{affidata al sistema operativo.}  
\end{cases}  
$$

---
### **9. Conclusione**

I **semafori** rappresentano la **prima astrazione di alto livello** per la sincronizzazione nei sistemi operativi.  
Permettono di gestire in modo corretto ed efficiente la **mutua esclusione**, senza dover scrivere codice a basso livello o preoccuparsi dell’atomicità delle istruzioni.

Nelle prossime lezioni vedremo come i semafori si evolvano ulteriormente in strutture più complesse come **monitor** e **transazioni atomiche**, che consentono un controllo ancora più fine e sicuro dei processi concorrenti.