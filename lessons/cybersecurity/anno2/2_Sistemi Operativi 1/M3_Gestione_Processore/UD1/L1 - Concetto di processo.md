# **M3 UD1 Lezione 1 - Concetto di processo**

### **1. Introduzione**

Con l’avvento della **multiprogrammazione** e del **multitasking**, i sistemi operativi hanno dovuto affrontare un problema centrale: **come gestire più programmi contemporaneamente** condividendo un solo processore.  
Il concetto di **processo** nasce proprio per risolvere questa esigenza.

Un **processo** è un **programma in esecuzione**, dotato di:

- codice da eseguire,
    
- dati propri,
    
- e uno **stato di evoluzione** che descrive in quale punto del programma si trova e con quali risorse sta lavorando.

---
### **2. Multi-tasking**

#### **2.1. Il problema originario**

In un sistema sequenziale, quando un programma richiede un’operazione di input/output (es. leggere da disco), la **CPU resta inattiva** finché la periferica non risponde.  
Questo comporta un **uso inefficiente del processore**, che rimane fermo anche se potrebbe eseguire altro.

#### **2.2. La soluzione: multiprogrammazione e multitasking**

Per sfruttare al massimo il processore, si introduce l’idea di **eseguire più programmi contemporaneamente**.

$$  
\begin{cases}  
\textbf{Multiprogrammazione:}~ & \text{più programmi caricati in memoria contemporaneamente.} \\\\  
\textbf{Multitasking:}~ & \text{turnazione rapida dei programmi sul processore per mascherare i tempi di attesa.}  
\end{cases}  
$$

Il processore, in realtà, **non esegue due programmi nello stesso istante**, ma **passa da uno all’altro così rapidamente** da creare l’illusione del parallelismo.

---
### **3. Il concetto di processo**

Un **processo** è dunque **l’istanza attiva** di un programma in esecuzione.

$$  
\begin{cases}  
\textbf{Programma:}~ & \text{entità passiva → insieme di istruzioni memorizzate.} \\\\  
\textbf{Processo:}~ & \text{entità attiva → esecuzione del programma con dati e stato propri.}  
\end{cases}  
$$

Ogni processo comprende:

- **Codice** del programma (istruzioni);
    
- **Dati** (variabili globali, locali e dinamiche);
    
- **Contesto di esecuzione**, ovvero l’insieme delle informazioni che permettono di sospendere e riprendere il processo in qualsiasi momento.

---
### **4. Struttura interna di un processo**

All’interno della memoria, un processo è composto da diverse aree:

$$  
\begin{cases}  
\textbf{Area codice (text):}~ & \text{contiene le istruzioni del programma.} \\\\  
\textbf{Area dati (data):}~ & \text{contiene le variabili globali.} \\\\  
\textbf{Stack:}~ & \text{memorizza le variabili locali e i parametri delle funzioni.} \\\\  
\textbf{Heap:}~ & \text{contiene le variabili allocate dinamicamente.} \\\\  
\textbf{Registri:}~ & \text{contengono le variabili temporanee e l’indirizzo dell’istruzione corrente (Program Counter).}  
\end{cases}  
$$

---
### **5. Stato di evoluzione della computazione**

Il **processo** può essere visto come una **macchina a stati finiti**:  
gli stati rappresentano le informazioni su cui opera, e le transizioni sono determinate dalle istruzioni che modificano tali informazioni.

$$  
\text{Stato di evoluzione della computazione} = \text{insieme dei valori di tutte le variabili e registri del processo.}  
$$

In particolare:

- **variabili globali e locali**,
    
- **variabili temporanee nei registri**,
    
- **variabili allocate dinamicamente**,
    
- **informazioni di contesto** (base pointer, stack pointer, indirizzo di ritorno),
    
- **Program Counter**, che indica la prossima istruzione da eseguire.

Questo insieme di valori definisce **esattamente dove si trova il processo nella sua esecuzione**.

---
### **6. Cambio di processo**

Quando il sistema operativo decide di sospendere un processo per eseguirne un altro (operazione detta **context switch**), deve:

1. **salvare lo stato di evoluzione della computazione** del processo corrente;
    
2. **caricare lo stato del nuovo processo** da eseguire.

Solo così ciascun processo potrà **riprendere da dove si era interrotto**, senza errori o inconsistenze.

---
### **7. Stati di uso del processore**

Durante la sua vita, un processo può trovarsi in diversi stati, che descrivono **come sta usando il processore**:

$$  
\begin{cases}  
\textbf{Nuovo (New):}~ & \text{il processo è stato creato ma non ancora avviato.} \\\\  
\textbf{Pronto (Ready):}~ & \text{il processo è in attesa di ottenere la CPU.} \\\\  
\textbf{In esecuzione (Running):}~ & \text{il processo sta usando il processore.} \\\\  
\textbf{In attesa (Waiting):}~ & \text{il processo attende un evento esterno o una risorsa.} \\\\  
\textbf{Terminato (Terminated):}~ & \text{l’esecuzione è conclusa.}  
\end{cases}  
$$

---
### **8. Diagramma degli stati del processo**

Il comportamento di un processo nel tempo si può rappresentare con un **grafo orientato**, in cui:

- i **nodi** rappresentano gli stati,
    
- gli **archi** rappresentano le possibili **transizioni** tra stati.

![](imgs/Pasted%20image%2020260319131424.png)

Le transizioni principali sono:

- **New → Ready**: il processo è pronto a partire;
    
- **Ready → Running**: il processo ottiene la CPU;
    
- **Running → Waiting**: attende un evento (es. I/O);
    
- **Running → Ready**: viene interrotto dallo scheduler;
    
- **Waiting → Ready**: l’evento atteso si è verificato;
    
- **Running → Terminated**: ha terminato l’esecuzione.

---
### **9. Supporti per la gestione dei processi**

#### **9.1. Process Control Block (PCB)**

Ogni processo è descritto dal suo **Process Control Block**, un blocco di dati gestito dal sistema operativo che ne contiene tutte le informazioni di controllo.

$$  
\begin{cases}  
\text{Identificatore del processo (PID);} \\\\  
\text{Stato attuale (New, Ready, Running, Waiting, Terminated);} \\\\  
\text{Program Counter e registri CPU;} \\\\  
\text{Informazioni di scheduling (priorità, tempo residuo, ecc.);} \\\\  
\text{Informazioni sulla memoria (limiti, segmenti, pagine);} \\\\  
\text{Informazioni I/O e file aperti;} \\\\  
\text{Dati di accounting (tempo CPU usato, utente proprietario, ecc.).}  
\end{cases}  
$$

Il PCB è ciò che permette al sistema operativo di **sospendere e riprendere** correttamente un processo.

---
#### **9.2. Code dei processi**

I processi vengono organizzati in **code**, una per ogni stato:

![](imgs/Pasted%20image%2020260319131502.png)

$$  
\begin{cases}  
\textbf{Coda dei pronti (Ready Queue):}~ & \text{processi pronti a usare la CPU.} \\\\  
\textbf{Coda d’attesa (Waiting Queue):}~ & \text{processi sospesi in attesa di un evento.} \\\\  
\textbf{Coda dei nuovi e terminati:}~ & \text{processi in creazione o terminazione.}  
\end{cases}  
$$

Le transizioni tra code rappresentano **il ciclo di vita dinamico** dei processi all’interno del sistema operativo.

![](imgs/Pasted%20image%2020260319131515.png)

---
### **10. Sintesi finale**

$$  
\begin{cases}  
\textbf{Processo:}~ & \text{programma in esecuzione con stato, dati e contesto propri.} \\\\  
\textbf{Evoluzione:}~ & \text{insieme delle informazioni che definiscono il punto corrente di esecuzione.} \\\\  
\textbf{Stato:}~ & \text{modo in cui il processo usa il processore (New, Ready, Running, Waiting, Terminated).} \\\\  
\textbf{Gestione:}~ & \text{PCB e code di processo per il controllo e la schedulazione.}  
\end{cases}  
$$

---
### **11. Conclusione**

Il concetto di **processo** è la base della gestione del processore:  
consente al sistema operativo di **astrarre il flusso di esecuzione**, di **gestire il multitasking** e di **garantire l’equità nell’uso della CPU**.

Nei prossimi passi studieremo **come questi processi vengono pianificati** dal sistema operativo, analizzando gli **algoritmi di schedulazione** che determinano _chi_ esegue _cosa_ e _quando_.