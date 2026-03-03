# **M3 UD1 Lezione 3 - Sospensione e riattivazione dei processi**

### **1. Introduzione**

Il **multitasking** è la capacità del sistema operativo di **gestire più processi in modo concorrente** condividendo un solo processore.  
L’obiettivo è **massimizzare l’uso della CPU** e fornire all’utente l’illusione che più programmi si stiano eseguendo contemporaneamente.

In questa lezione studiamo come il sistema operativo **sospende, riattiva e alterna** i processi, analizzando anche il meccanismo di **time-sharing**, che permette di distribuire equamente il tempo di CPU tra più processi attivi.

---
### **2. Classificazione dei processi**

I processi possono essere classificati in base all’uso prevalente delle risorse fisiche:

$$  
\begin{cases}  
\textbf{Processi I/O-bound:}~ & \text{eseguono molte operazioni di input/output e brevi calcoli CPU.} \\\\  
\textbf{Processi CPU-bound:}~ & \text{eseguono molti calcoli logico-aritmetici e poche operazioni di I/O.}  
\end{cases}  
$$

Un sistema bilanciato deve alternare entrambi i tipi di processi per **mantenere alta l’efficienza globale**, evitando che la CPU resti inattiva o che l’I/O diventi un collo di bottiglia.

---
### **3. Realizzazione del multitasking**

#### **3.1. Obiettivo**

Massimizzare lo sfruttamento del processore garantendo che, mentre un processo è sospeso in attesa di I/O, un altro possa usare la CPU.
#### **3.2. Metodologia**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Sospendere il processo in esecuzione.} \\\\  
\textbf{2.}~ & \text{Ordinare i processi pronti secondo una politica di scheduling.} \\\\  
\textbf{3.}~ & \text{Selezionare (dispatching) il prossimo processo da eseguire.} \\\\  
\textbf{4.}~ & \text{Riattivare il processo selezionato, ripristinandone il contesto.}  
\end{cases}  
$$

In questo modo la CPU viene continuamente riassegnata, e il sistema dà l’impressione di **parallelismo logico** anche in presenza di un solo processore fisico.

---
### **4. Politiche e meccanismi del multitasking**

#### **4.1. Politiche**

Le politiche determinano _quando_ e _perché_ un processo può essere sospeso:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Dopo una richiesta di I/O.} \\\\  
\textbf{2.}~ & \text{Dopo la creazione di un sottoprocesso, attendendone la terminazione.} \\\\  
\textbf{3.}~ & \text{Quando rilascia volontariamente la CPU.}  
\end{cases}  
$$

Queste sospensioni sono dette **sincrone** perché avvengono in corrispondenza di eventi previsti dal programma stesso (es. una `read()` o una `wait()`).

#### **4.2. Meccanismi**

I meccanismi implementano materialmente le politiche:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Salvataggio del contesto del processo sospeso (registri, stack, contatori).} \\\\  
\textbf{2.}~ & \text{Dispatching del nuovo processo selezionato dallo scheduler.} \\\\  
\textbf{3.}~ & \text{Ripristino del contesto del processo riattivato.}  
\end{cases}  
$$

Questa sequenza costituisce il **context switch**, ovvero il cambio di processo in esecuzione.

---
### **5. Il Time-Sharing**

#### **5.1. Definizione**

Il **time-sharing** è una forma di multitasking basata sulla **condivisione del tempo di CPU** tra i processi.  
Serve a garantire **reattività** nei sistemi interattivi, dove l’utente si aspetta risposte rapide.

$$  
\begin{cases}  
\textbf{Obiettivo:}~ & \text{creare l’illusione che tutti i processi evolvano contemporaneamente.} \\\\  
\textbf{Problema:}~ & \text{i processi CPU-bound potrebbero monopolizzare il processore.} \\\\  
\textbf{Soluzione:}~ & \text{forzare il rilascio del processore tramite pre-emption.}  
\end{cases}  
$$

#### **5.2. Quanto di tempo (Time Slice)**

Ogni processo ha un **intervallo massimo di uso della CPU**, chiamato _quanto di tempo_ o _time slice_.  
Quando il tempo scade, il processo viene **sospeso automaticamente**, e la CPU viene assegnata a un altro processo.

---
### **6. Real-time clock e pre-emption**

Per realizzare il time-sharing si usa un **real-time clock (RTC)**, un dispositivo hardware che **genera interruzioni periodiche**.  
Ad ogni interruzione, il sistema operativo controlla se il processo corrente ha superato il proprio quanto di tempo.

$$  
\begin{cases}  
\textbf{Periodo RTC:}~ & p_{RTC} \\  
\textbf{Multiplo operativo:}~ & \Delta = k \cdot p_{RTC}  
\end{cases}  
$$

Il valore $\Delta$ rappresenta il quanto di tempo assegnato a ciascun processo.  
Un periodo troppo breve comporta **sovraccarico di interruzioni**, mentre uno troppo lungo riduce la **reattività del sistema**.

---
### **7. Politiche di sospensione nel time-sharing**

Nel modello time-sharing un processo può essere sospeso in quattro casi:

$$  
\begin{cases}  
1.~ & \text{Dopo una richiesta di I/O;} \\  
2.~ & \text{Dopo la creazione di un sottoprocesso;} \\  
3.~ & \text{Quando rilascia volontariamente la CPU;} \\  
4.~ & \text{Quando scade il quanto di tempo assegnato.}  
\end{cases}  
$$

Analizzando le sospensioni dal punto di vista logico:

$$  
\begin{cases}  
\textbf{Rispetto all’evoluzione della computazione:} &  
\begin{cases}  
\text{Sincrona:}~ & 1, 2, 3 \\  
\text{Asincrona:}~ & 4  
\end{cases} \\\\  
\textbf{Rispetto alla scrittura del programma:} &  
\begin{cases}  
\text{Esplicita:}~ & 3 \\  
\text{Implicita:}~ & 1, 2, 4  
\end{cases}  
\end{cases}  
$$

---
### **8. Sospensione di un processo**

La **sospensione** consiste in due fasi:

$$  
\begin{cases}  
\textbf{1. Attivazione della procedura di sospensione:}~ &  
\begin{cases}  
\text{Sincrona (kernel mode):}~ & \text{durante operazioni di I/O o creazione processi.} \\\\  
\text{Sincrona (user mode):}~ & \text{rilascio volontario del processore.} \\\\  
\text{Asincrona:}~ & \text{allo scadere del quanto di tempo.}  
\end{cases} \\\\\\  
\textbf{2. Salvataggio del contesto di esecuzione:}~ &  
\begin{cases}  
\text{Salvataggio di tutti i registri sullo stack;} \\\\  
\text{Memorizzazione dello stack pointer nel PCB.}  
\end{cases}  
\end{cases}  
$$

---
### **9. Riattivazione di un processo**

La **riattivazione** è l’operazione inversa alla sospensione:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Recupero dallo stack del processo dei registri CPU.} \\\\  
\textbf{2.}~ & \text{Ripristino dello stack pointer e del base pointer dal PCB.} \\\\  
\textbf{3.}~ & \text{Ripresa dell’esecuzione dal punto in cui era stata interrotta.}  
\end{cases}  
$$

Una volta completato il ripristino, il processo torna nello stato **Running** e continua la sua computazione.

---
### **10. Il context switch**

Il **cambiamento del processo in esecuzione** si realizza tramite il **context switch**:

$$  
\text{Context Switch} = \text{Sospensione del processo corrente} + \text{Riattivazione del nuovo processo}  
$$

È uno dei meccanismi più delicati del sistema operativo, poiché avviene centinaia o migliaia di volte al secondo.  
Deve essere **rapidissimo** e **trasparente**, altrimenti l’overhead ridurrebbe l’efficienza globale del sistema.

---
### **11. Dispatching**

Il **dispatcher** è il componente del sistema operativo che **seleziona** il processo successivo dalla **coda dei pronti** e lo **mette in esecuzione**.  
È il punto di collegamento tra lo **scheduler** (che decide _chi_ eseguire) e il **meccanismo di riattivazione** (che effettivamente _lo esegue_).

---
### **12. Sintesi finale**

$$  
\begin{cases}  
\textbf{Multitasking:}~ & \text{esecuzione concorrente di più processi per ottimizzare la CPU.} \\\\  
\textbf{Time-sharing:}~ & \text{distribuzione temporale equa della CPU tramite pre-emption.} \\\\  
\textbf{Sospensione:}~ & \text{salvataggio del contesto del processo corrente.} \\\\  
\textbf{Riattivazione:}~ & \text{ripristino del contesto e ripresa dell’esecuzione.} \\\\ 
\textbf{Context switch:}~ & \text{meccanismo che realizza il cambio di processo.}  
\end{cases}  
$$

---
### **13. Conclusione dell’Unità**

Con questa lezione si conclude l’**Unità Didattica 1 – Processi**.  
Abbiamo visto:

- come i processi vengono **creati, sospesi e riattivati**;
    
- come il sistema operativo implementa **il multitasking e il time-sharing**;
    
- e come, tramite il **context switch**, la CPU passi con efficienza da un processo all’altro.