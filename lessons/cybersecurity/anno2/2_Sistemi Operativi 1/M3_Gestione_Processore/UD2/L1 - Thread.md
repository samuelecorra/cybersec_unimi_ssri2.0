# **M3 UD2 Lezione 1 - Thread**

### **1. Introduzione**

Con l’aumento della complessità delle applicazioni e la necessità di tempi di risposta sempre più rapidi, la gestione basata solo sui **processi tradizionali** non è più sufficiente.  
Molte applicazioni moderne — come **server web, sistemi informativi o programmi interattivi complessi** — devono gestire **più attività contemporaneamente** (ad esempio, più richieste di utenti o flussi di dati paralleli).

Per soddisfare queste esigenze nasce il concetto di **thread**, un’evoluzione naturale del processo, che consente di eseguire **più flussi di controllo** all’interno dello stesso programma, condividendo le stesse risorse.

---
### **2. Motivazioni**

Le applicazioni moderne presentano **più componenti attive**:

$$  
\begin{cases}  
\textbf{Controllo del flusso di operazioni} & \text{(sequenza logica di esecuzione)} \\\\  
\textbf{Operazioni di I/O} & \text{(lettura, scrittura, comunicazione con periferiche)} \\\\  
\textbf{Elaborazione} & \text{(calcolo, analisi, logica applicativa)}  
\end{cases}  
$$

In sistemi tradizionali basati su processi, ogni attività indipendente doveva essere gestita da **un processo distinto**.  
Tuttavia, ciò comportava:

- **ritardi** dovuti alle attese di I/O,
    
- **spreco di risorse** per la duplicazione di memoria,
    
- **scarsa efficienza** nella comunicazione tra processi separati.

---
### **3. Limiti dell’approccio a soli processi**

$$  
\begin{cases}  
\textbf{Problema 1:}~ & \text{ogni processo ha un solo flusso di controllo (un solo thread).} \\\\  
\textbf{Problema 2:}~ & \text{durante un’operazione di I/O, la CPU rimane inutilizzata.} \\\\  
\textbf{Problema 3:}~ & \text{la comunicazione tra processi richiede passaggi complessi e lenti (IPC).}  
\end{cases}  
$$

In applicazioni come un **server web**, questi limiti diventano critici: mentre un processo attende la risposta da un client, non può servire altre richieste.

---
### **4. Soluzione: il modello multi-thread**

La soluzione consiste nel permettere **più flussi di controllo nello stesso processo**.  
Nasce così il concetto di **thread**, anche detto **processo leggero** (_lightweight process_).

#### **4.1. Definizione**

Un **thread** è un **flusso di controllo dell’esecuzione di istruzioni** all’interno di un processo.  
Ogni thread rappresenta **una sequenza indipendente di operazioni**, ma tutti i thread di un processo:

$$  
\begin{cases}  
\text{condividono la memoria centrale;} \\\\  
\text{condividono i file aperti e le risorse del processo padre;} \\\\  
\text{mantengono registri e stack propri.}  
\end{cases}  
$$

---
### **5. Struttura di un processo multi-thread**

Nel modello multi-thread, il processo diventa un **contenitore di risorse condivise**, mentre i thread sono le **unità di esecuzione reali**.

$$  
\begin{cases}  
\textbf{Processo (heavyweight):}~ & \text{contiene codice, dati e risorse comuni.} \\\\  
\textbf{Thread (lightweight):}~ & \text{esegue istruzioni, gestisce stack e registri propri.}  
\end{cases}  
$$

In altre parole, più thread operano **contemporaneamente** sullo stesso codice, cooperando in memoria condivisa senza dover passare attraverso meccanismi di intercomunicazione pesanti.

---
### **6. Benefici dei thread**

L’introduzione dei thread offre vantaggi notevoli per l’efficienza e la reattività del sistema.

$$  
\begin{cases}  
\textbf{1. Prontezza di risposta:}~ & \text{un thread può servire una nuova richiesta mentre un altro attende I/O.} \\\\  
\textbf{2. Condivisione di risorse:}~ & \text{i thread condividono la memoria e i file aperti del processo padre.} \\\\  
\textbf{3. Economia:}~ & \text{la creazione e lo switch tra thread è molto più rapido che tra processi.} \\\\  
\textbf{4. Scalabilità:}~ & \text{su sistemi multiprocessore, thread diversi possono essere eseguiti in parallelo.}  
\end{cases}  
$$

Queste proprietà rendono il multi-threading ideale per sistemi **ad alta disponibilità** e **basso tempo di risposta**, come server, simulatori o applicazioni grafiche complesse.

---
### **7. Supporti di gestione dei thread**

I thread possono essere **gestiti a due livelli diversi**, a seconda del modo in cui il sistema operativo li implementa.

#### **7.1. Thread a livello utente**

Gestiti da **librerie in spazio utente** (ad esempio POSIX `pthread`).  
Le operazioni di creazione, sincronizzazione e terminazione dei thread avvengono **senza coinvolgere il kernel**, tramite semplici chiamate a funzione.

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{esecuzione veloce, gestione flessibile e indipendente dal SO.} \\\\  
\textbf{Svantaggi:}~ & \text{se un thread si blocca in I/O, l’intero processo resta sospeso.}  
\end{cases}  
$$

#### **7.2. Thread a livello kernel**

Gestiti direttamente dal **sistema operativo**.  
Ogni thread è visibile al kernel e può essere pianificato separatamente.

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{vera concorrenza su più core e gestione indipendente tra thread.} \\\\  
\textbf{Svantaggi:}~ & \text{maggior overhead dovuto alle system call.}  
\end{cases}  
$$

---
### **8. Librerie di thread**

Le librerie di gestione forniscono le funzioni di base per creare, avviare, sospendere e terminare i thread.

$$
\begin{cases}
\textbf{Spazio utente:}~ & \text{le chiamate sono funzioni locali (es. \texttt{pthread\_create()}).} \\\\
\textbf{Spazio kernel:}~ & \text{le chiamate sono vere e proprie system call al sistema operativo.}
\end{cases}
$$

---
### **9. Sintesi finale**

$$  
\begin{cases}  
\textbf{Thread:}~ & \text{unità base di utilizzo della CPU all’interno di un processo.} \\\\  
\textbf{Processo multi-thread:}~ & \text{più flussi di controllo che condividono risorse comuni.} \\\\  
\textbf{Benefici:}~ & \text{reattività, efficienza e scalabilità.} \\\\  
\textbf{Gestione:}~ & \text{a livello utente o a livello kernel, tramite librerie dedicate.}  
\end{cases}  
$$

---
### **10. Conclusione**

Il **thread** rappresenta il passo evolutivo del concetto di processo:  
dalla singola unità di esecuzione alla possibilità di avere **più flussi concorrenti nello stesso spazio di memoria**.

Grazie a questa struttura, i sistemi operativi moderni possono ottenere **alta efficienza, maggiore parallelismo e reattività immediata**, sfruttando appieno le architetture multi-core e multi-processore.