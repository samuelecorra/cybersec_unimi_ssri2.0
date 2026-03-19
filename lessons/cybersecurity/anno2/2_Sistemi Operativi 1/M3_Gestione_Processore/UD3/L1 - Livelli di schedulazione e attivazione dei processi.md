# **M3 UD3 Lezione 1 - Livelli di schedulazione e attivazione dei processi**

### **1. Introduzione**

La **schedulazione** è il meccanismo attraverso cui il **sistema operativo decide quale processo deve usare la CPU** in un determinato momento.  
L’obiettivo è ottenere un uso efficiente del processore e una gestione equilibrata dei processi, in modo da offrire all’utente l’impressione che tutti stiano procedendo contemporaneamente.

In realtà, la CPU può eseguire **un solo processo per volta** su ogni core, ma grazie alla velocità di commutazione tra processi (context switch) si ottiene una **concorrenza apparente**, tipica dei sistemi _time-sharing_.

---
### **2. Obiettivi della schedulazione**

Il compito dello scheduler è quello di:  
$$  
\begin{cases}  
\textbf{1.}~ & \text{Gestire la turnazione dei processi sul processore.} \\\\  
\textbf{2.}~ & \text{Stabilire politiche di ordinamento per decidere chi eseguire per primo.} \\\\  
\textbf{3.}~ & \text{Massimizzare lo sfruttamento della CPU e migliorare la reattività del sistema.}  
\end{cases}  
$$

A tale scopo, il sistema operativo utilizza **diversi livelli di schedulazione**, ciascuno con ruoli e frequenze differenti.

---
### **3. Livelli della schedulazione**

Esistono tre livelli principali di schedulazione, ognuno dei quali opera in momenti e con obiettivi diversi:

$$  
\begin{cases}  
\textbf{Schedulazione a lungo termine} & \text{(job scheduler)} \\\\  
\textbf{Schedulazione a medio termine} & \text{(medium-term scheduler)} \\\\  
\textbf{Schedulazione a breve termine} & \text{(CPU scheduler)}  
\end{cases}  
$$

---
### **4. Schedulazione a breve termine (short-term scheduling)**

#### **4.1. Funzione**

È il livello più vicino all’hardware e il più frequente.  
Il **CPU scheduler** ordina i processi che sono già **in memoria centrale** e nello stato di **pronto all’esecuzione (Ready)**.

Il processo posto in cima alla lista sarà quello scelto dal **dispatcher**, che effettuerà il **cambio di contesto (context switch)** per metterlo in esecuzione.

#### **4.2. Caratteristiche principali**

- Eseguita molto frequentemente, tipicamente **ogni 100 ms** circa.
    
- Deve essere **estremamente veloce**, per non introdurre ritardi di gestione.
    
- Usa algoritmi semplici per minimizzare l’overhead.

Questo tipo di schedulazione è tipico dei sistemi _time-sharing_, dove la CPU passa rapidamente da un processo all’altro per mantenere alta la reattività.

---
### **5. Schedulazione a lungo termine (long-term scheduling)**

#### **5.1. Funzione**

Il **job scheduler** seleziona **quali processi ammettere in memoria centrale** per l’esecuzione, tra quelli che sono stati attivati o caricati nel sistema.  
In pratica, determina **quanti e quali processi potranno entrare nello stato di pronto**.

#### **5.2. Obiettivo**

Bilanciare il gruppo di processi caricati in memoria per ottenere un uso ottimale della CPU.  
In particolare, lo scheduler cerca di mescolare:

$$  
\begin{cases}  
\textbf{Processi CPU-bound:}~ & \text{richiedono molta elaborazione e poco I/O;} \\\\  
\textbf{Processi I/O-bound:}~ & \text{richiedono frequenti operazioni di input/output.}  
\end{cases}  
$$

Una combinazione equilibrata massimizza l’utilizzo complessivo del processore.

#### **5.3. Frequenza e complessità**

- Viene eseguito **poco frequentemente**, in genere ogni pochi minuti.
    
- Può essere **assente** in sistemi con carico costante.
    
- Usa **algoritmi complessi**, poiché lavora su tempi lunghi e strategie globali.

---
### **6. Schedulazione a medio termine (medium-term scheduling)**

#### **6.1. Motivazioni**

In un sistema attivo, si possono verificare problemi come:  
$$  
\begin{cases}  
\textbf{1.}~ & \text{Troppi processi concorrenti che rallentano il sistema.} \\\\  
\textbf{2.}~ & \text{Uso inefficiente della CPU per sbilanciamento tra CPU-bound e I/O-bound.} \\\\  
\textbf{3.}~ & \text{Memoria centrale insufficiente per ospitare tutti i processi attivi.}  
\end{cases}  
$$

#### **6.2. Obiettivi**

Per risolvere questi problemi, lo **scheduler a medio termine**:  
$$  
\begin{cases}  
\textbf{1.}~ & \text{Riduce la concorrenza, sospendendo processi temporaneamente.} \\\\  
\textbf{2.}~ & \text{Ottimizza la distribuzione dei processi attivi.} \\\\  
\textbf{3.}~ & \text{Libera memoria centrale per aumentare l’efficienza.}  
\end{cases}  
$$

#### **6.3. Soluzione: swapping**

La tecnica principale è lo **swapping**, ossia lo spostamento temporaneo dei processi tra memoria centrale e memoria di massa:

![](imgs/Pasted%20image%2020260319154257.png)

$$  
\begin{cases}  
\textbf{Swapping out:}~ & \text{rimozione di processi dalla memoria centrale;} \\\\  
\textbf{Swapping in:}~ & \text{reintroduzione di processi precedentemente sospesi.}  
\end{cases}  
$$

In questo modo si mantiene un **gruppo dinamico di processi attivi**, adattato al carico di lavoro corrente e alla disponibilità di risorse.

---
### **7. Tipologie di attivazione**

La schedulazione può essere attivata in due modi, a seconda di come viene interrotto un processo in esecuzione.

---
#### **7.1. Schedulazione non pre-emptive**

Il processo in esecuzione **non viene interrotto forzatamente**:  
cede il processore **solo in punti naturali della sua esecuzione**, ad esempio quando:

- richiede un’operazione di I/O,
    
- crea un processo e attende la sua terminazione,
    
- rilascia volontariamente la CPU,
    
- o termina.

È una **gestione sincrona** con l’evoluzione del programma: il cambio di contesto avviene solo in momenti “sicuri”.

---
#### **7.2. Schedulazione pre-emptive**

Nei sistemi _time-sharing_, la CPU può essere **sottratta forzatamente** a un processo anche se non ha terminato la propria attività.  
Questo avviene quando scade il **quanto di tempo (time quantum)** assegnato.

È una **gestione asincrona**, perché il cambio di contesto può avvenire indipendentemente dallo stato del processo.

Questo approccio migliora la **responsività** e l’equità, ma introduce un leggero overhead dovuto al maggior numero di commutazioni.

---
### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Schedulazione a lungo termine:}~ & \text{decide quali processi caricare in memoria.} \\\\  
\textbf{Schedulazione a medio termine:}~ & \text{regola dinamicamente i processi attivi (swapping).} \\\\  
\textbf{Schedulazione a breve termine:}~ & \text{sceglie quale processo pronto deve usare la CPU.} \\\\  
\textbf{Attivazione non pre-emptive:}~ & \text{rilascio volontario o naturale della CPU.} \\\\  
\textbf{Attivazione pre-emptive:}~ & \text{rilascio forzato allo scadere del quanto di tempo.}  
\end{cases}  
$$

---
### **9. Conclusione**

La schedulazione è la chiave dell’efficienza di un sistema operativo:  
decide chi lavora, per quanto tempo e con quale priorità.

I tre livelli — lungo, medio e breve termine — collaborano per mantenere il sistema **reattivo, bilanciato e sempre operativo**, dando l’illusione di un’esecuzione parallela dei processi anche su CPU singole.