## **M3 UD2 Lezione 3 - Gestione dei thread**

### **1. Introduzione**

Dopo aver studiato il concetto di **thread** e i modelli di **multi-threading**, questa lezione analizza **le operazioni fondamentali di gestione dei thread**:

- **creazione**,
    
- **esecuzione**,
    
- **cancellazione**,
    
- **sincronizzazione e comunicazione**,
    
- **processi leggeri (LWP)**.

Queste funzioni rappresentano il ciclo vitale dei thread, analogo a quello dei processi, ma più flessibile e leggero.

---
### **2. Creazione dei thread**

#### **2.1. Concetto generale**

La **creazione di un thread** consiste nell’apertura di un nuovo flusso di esecuzione all’interno del processo padre.  
Ogni thread creato condivide le risorse del processo principale (memoria, file, variabili globali), ma dispone di **stack e registri propri**.

---
#### **2.2. Creazione tramite `fork()`**

La chiamata `fork()` nei sistemi UNIX genera un **nuovo processo**, non un thread.  
Tuttavia, se `fork()` viene eseguita **all’interno di un processo multi-thread**, il comportamento può variare a seconda dell’implementazione:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Duplicazione di tutti i thread del processo padre.} \\\\  
\textbf{2.}~ & \text{Duplicazione del solo thread chiamante.}  
\end{cases}  
$$

La seconda modalità è la più comune: evita di copiare tutti i thread, riducendo il rischio di incoerenze.

---
#### **2.3. Creazione tramite librerie**

Nella maggior parte dei sistemi, la creazione di thread avviene tramite **funzioni di libreria**, come:

$$
\text{pthread\_create() \quad o \quad CreateThread()}
$$

Queste funzioni allocano lo stack del nuovo thread, inizializzano i registri e lo inseriscono nello scheduler del sistema operativo.

---
### **3. Esecuzione dei thread**

Dopo la creazione, il thread entra nello stato **Ready** e attende di essere schedulato dal sistema operativo.  
Una volta ottenuta la CPU, passa nello stato **Running** ed esegue la funzione specificata al momento della creazione.

#### **3.1. Chiamata `exec()`**

La chiamata `exec()` carica un nuovo programma nel processo corrente.  
Se viene eseguita da un thread, essa **rimpiazza l’intero processo**, causando la terminazione di tutti i thread del gruppo.

In altre parole, `exec()` **non sostituisce solo il thread chiamante**, ma **l’intero processo**, perché cambia completamente lo spazio di indirizzamento.

---
### **4. Cancellazione dei thread**

La **cancellazione** (o terminazione forzata) è la rimozione di un thread **prima** che abbia completato la sua esecuzione naturale.  
Può essere richiesta dal thread stesso o da un altro thread dello stesso processo.

#### **4.1. Modalità di cancellazione**

$$  
\begin{cases}  
\textbf{Cancellazione asincrona:}~ & \text{terminazione immediata del thread target.} \\\\  
\textbf{Cancellazione differita:}~ & \text{il thread verifica periodicamente se può terminare (punti di cancellazione).}  
\end{cases}  
$$

La modalità differita è preferita, poiché garantisce **terminazione ordinata** e **rilascio sicuro delle risorse**.

---
#### **4.2. Punti di cancellazione**

Nel modello differito, ogni thread definisce **punti di controllo** (o _cancellation points_) dove verifica se deve interrompersi.  
Esempi di punti di cancellazione:

- chiamate di I/O (`read()`, `write()`),
    
- attese (`sleep()`, `pthread_join()`),
    
- funzioni di sincronizzazione.

Questa tecnica consente di evitare corruzioni di memoria o deadlock dovuti a terminazioni improvvise.

---
### **5. Sincronizzazione e comunicazione**

Poiché i thread di uno stesso processo condividono lo spazio di memoria, la **sincronizzazione** è indispensabile per evitare conflitti durante l’accesso a dati comuni.

#### **5.1. Tipologie di sincronizzazione**

$$  
\begin{cases}  
\textbf{Sincronizzazione cooperativa:}~ & \text{i thread collaborano esplicitamente (es. mutex, semafori).} \\\\  
\textbf{Sincronizzazione forzata:}~ & \text{imposta dal sistema operativo tramite scheduling e segnali.}  
\end{cases}  
$$

---
#### **5.2. Comunicazione tra thread**

I thread possono comunicare:  
$$  
\begin{cases}  
\textbf{1.}~ & \text{tra tutti i thread dello stesso processo;} \\\\  
\textbf{2.}~ & \text{tra un sottoinsieme di thread;} \\\\  
\textbf{3.}~ & \text{con un thread specifico.}  
\end{cases}  
$$

La comunicazione avviene di norma tramite **variabili condivise**, ma può anche sfruttare **meccanismi di segnalazione** (signal o eventi) forniti dal sistema operativo.

---
### **6. Processi leggeri (Lightweight Processes – LWP)**

#### **6.1. Definizione**

I **processi leggeri (LWP)** rappresentano una **via di mezzo tra thread e processi**.  
Sono usati nei modelli **molti-a-molti** e **a due livelli** per collegare i thread utente con i thread kernel.

![](imgs/Pasted%20image%2020260319152351.png)

Un LWP agisce come un **processore virtuale**:  
fa da ponte tra i thread gestiti in spazio utente e quelli riconosciuti dal sistema operativo.

#### **6.2. Ruolo del LWP**

$$  
\begin{cases}  
\textbf{Utente → LWP → Kernel} \\\\  
\text{Thread utente invia richieste al suo LWP,} \\\\  
\text{che le traduce in operazioni di sistema reali.}  
\end{cases}  
$$

Questo meccanismo permette di mantenere **alta efficienza** (gestione rapida in user mode) e **vera concorrenza** (grazie al controllo kernel).

---
### **7. Sintesi finale**

$$  
\begin{cases}  
\textbf{Creazione:}~ & \text{tramite librerie o `fork()`, con risorse condivise.} \\\\  
\textbf{Esecuzione:}~ & \text{gestita dal kernel, con `exec()` che sostituisce l’intero processo.} \\\\  
\textbf{Cancellazione:}~ & \text{asincrona o differita, tramite punti di cancellazione.} \\\\  
\textbf{Sincronizzazione e comunicazione:}~ & \text{tramite variabili condivise o primitive di sistema.} \\\\  
\textbf{Processi leggeri (LWP):}~ & \text{ponte tra thread utente e kernel nei modelli molti-a-molti.}  
\end{cases}  
$$

---
### **8. Conclusione**

La gestione dei thread rappresenta il **cuore del parallelismo moderno**:  
consente di creare, coordinare e terminare flussi di esecuzione multipli all’interno dello stesso processo, garantendo flessibilità e prestazioni elevate.

I **Lightweight Processes** completano questa architettura fungendo da intermediari efficienti tra **user space** e **kernel space**, permettendo di sfruttare appieno le potenzialità del multi-threading su sistemi multiprocessore.