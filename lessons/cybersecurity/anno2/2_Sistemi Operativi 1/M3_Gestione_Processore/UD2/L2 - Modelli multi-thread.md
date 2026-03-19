## **M3 UD2 Lezione 2 - Modelli multi-thread**

### **1. Introduzione**

Dopo aver compreso cos’è un **thread** e perché è stato introdotto, in questa lezione analizziamo **come i thread vengono gestiti e mappati** all’interno del sistema operativo.  
Un sistema multi-thread può essere realizzato in modi diversi, a seconda di **come i thread a livello utente vengono associati ai thread gestiti dal kernel**.

Lo studio dei **modelli di mappaggio** e dei **modelli di cooperazione** tra thread è fondamentale per capire come un sistema multi-thread ottiene le sue prestazioni e come gestisce la concorrenza.

---
### **2. Realizzazione dei sistemi multi-thread**

Esistono due approcci fondamentali alla gestione dei thread:

$$  
\begin{cases}  
\textbf{Sistemi con supporto solo per processi:}~ & \text{i thread vengono simulati a livello utente, dentro un unico processo.} \\\\  
\textbf{Sistemi con supporto per thread nel kernel:}~ & \text{i thread a livello utente corrispondono a thread reali gestiti dal kernel.}  
\end{cases}  
$$

Nel primo caso il sistema operativo non è a conoscenza dei thread, che sono gestiti da librerie user-level.  
Nel secondo caso invece il kernel conosce i thread e può schedularli autonomamente, permettendo **reale concorrenza** su più core.

---
### **3. Modelli di mappaggio thread utente ↔ thread kernel**

Il modo in cui i thread utente sono associati ai thread kernel definisce l’**architettura del sistema multi-thread**.  
Esistono quattro modelli principali:

---
#### **3.1. Modello molti-a-uno**

In questo modello, **più thread utente** sono mappati su **un solo thread kernel**.

![](imgs/Pasted%20image%2020260319145731.png)

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{gestione veloce, nessun intervento del kernel, basso overhead.} \\\\  
\textbf{Svantaggi:}~ & \text{assenza di vera concorrenza: se un thread si blocca, si blocca tutto il processo.}  
\end{cases}  
$$

È una soluzione **semplice ma limitata**, adatta solo a sistemi con un singolo processore o applicazioni che non richiedono I/O intensivo.  
Il principale **problema** è la **serializzazione** dei thread: solo uno può essere effettivamente in esecuzione alla volta.

---
#### **3.2. Modello uno-a-uno**

In questo modello, **ogni thread utente** corrisponde a **un thread kernel**.

![](imgs/Pasted%20image%2020260319151211.png)

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{vera concorrenza, sfruttamento del multiprocessore, indipendenza tra thread.} \\\\  
\textbf{Svantaggi:}~ & \text{overhead elevato e numero massimo di thread limitato dalle risorse del kernel.}  
\end{cases}  
$$

Questo modello è usato da molti sistemi moderni (ad esempio Windows e Linux), poiché bilancia semplicità e parallelismo reale.  
Ogni thread può essere schedulato dal kernel su un processore differente.

---
#### **3.3. Modello molti-a-molti**

Qui **più thread utente** vengono mappati su **più thread kernel**, con **numero flessibile e dinamico** di associazioni.

![](imgs/Pasted%20image%2020260319151312.png)

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{bilancia efficienza e concorrenza, consente adattamento dinamico.} \\\\  
\textbf{Svantaggi:}~ & \text{implementazione complessa, sincronizzazione più difficile.}  
\end{cases}  
$$

È un compromesso tra i due modelli precedenti: mantiene l’efficienza dei thread utente, ma consente al kernel di distribuire il carico su più CPU.

---
#### **3.4. Modello a due livelli**

È una **variante del molti-a-molti**, in cui:

- alcuni thread utente sono mappati **direttamente su thread kernel** (come nel modello 1:1),
    
- altri passano attraverso un livello di gestione intermedio (come nel modello molti-a-molti).

![](imgs/Pasted%20image%2020260319151328.png)

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{massima flessibilità, consente scelte dinamiche di mappaggio.} \\\\  
\textbf{Svantaggi:}~ & \text{implementazione complessa e maggiore overhead gestionale.}  
\end{cases}  
$$

Questo modello permette di ottimizzare l’esecuzione a seconda del tipo di thread:  
ad esempio, quelli che gestiscono I/O possono essere diretti, quelli computazionali gestiti in gruppo.

---
### **4. Modelli di cooperazione tra thread**

Oltre alla relazione tra thread utente e kernel, è importante studiare **come i thread collaborano tra loro**.  
I thread possono organizzarsi secondo **diversi modelli cooperativi**, a seconda della natura del compito e della struttura del programma.

---
#### **4.1. Thread simmetrici**

Tutti i thread hanno lo **stesso ruolo e priorità**.  
Condividono le stesse risorse e collaborano in modo paritario per completare il lavoro complessivo.

$$  
\text{Esempio: thread simmetrici in un server web che gestiscono richieste indipendenti.}  
$$

Questo modello è il più semplice da implementare, ma può diventare inefficiente se i thread non si bilanciano bene nei tempi di lavoro.

---
#### **4.2. Thread gerarchici**

In questo modello esiste una **relazione di dipendenza** tra thread:  
alcuni thread gestiscono altri thread, in modo analogo alla gerarchia padre–figlio nei processi.

$$  
\text{Esempio: thread principale che crea e coordina thread secondari per gestire moduli diversi di un’applicazione.}  
$$

Questo approccio migliora il **controllo** e la **modularità**, ma richiede meccanismi di sincronizzazione accurati per evitare deadlock o starvation.

---
#### **4.3. Thread in pipeline**

I thread cooperano **sequenzialmente**, come le stazioni di una catena di montaggio:  
il risultato di un thread diventa l’input del successivo.

$$  
\text{Esempio: un sistema di elaborazione video dove thread diversi gestiscono acquisizione, elaborazione e compressione.}  
$$

Questo modello è molto usato nei sistemi **streaming** e nei **filtri dati**, perché permette di sfruttare parallelismo e modularità insieme.

---
### **5. Sintesi finale**

$$  
\begin{cases}  
\textbf{Modelli di mappaggio:}~ & \text{molti-a-uno, uno-a-uno, molti-a-molti, a due livelli.} \\\\  
\textbf{Modelli di cooperazione:}~ & \text{simmetrici, gerarchici, in pipeline.} \\\\  
\textbf{Obiettivo comune:}~ & \text{sfruttare la concorrenza migliorando efficienza, controllo e scalabilità.}  
\end{cases}  
$$

---
### **6. Conclusione**

La gestione multi-thread è una delle sfide più eleganti dei sistemi operativi moderni.  
Saper combinare **mappaggio efficiente** e **cooperazione tra thread** consente di ottenere sistemi **reattivi, scalabili e ben bilanciati**.

I modelli visti oggi costituiscono le fondamenta su cui si basano i framework multi-thread moderni, dai **server cloud** ai **motori grafici** dei videogiochi, fino ai **sistemi embedded real-time**.