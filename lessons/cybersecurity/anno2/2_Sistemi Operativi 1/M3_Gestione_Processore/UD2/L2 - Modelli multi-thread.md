# **M3 UD2 Lezione 2 - Modelli multi-thread**

---

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
Il principale **problema** è la **serializzazione** dei thread: solo uno può essere effettivamente in esecuzione alla volta. Conseguenza importante: anche su un'**architettura multiprocessore**, pur essendo concettualmente separati i flussi dei singoli thread, **di fatto l'esecuzione risulta serializzata** poiché il thread di livello kernel è uno solo.

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
Ogni thread può essere schedulato dal kernel su un processore differente, **massimizzando la parallelizzazione effettiva** e permettendo anche **parallelismo fisico** in caso di più processori disponibili. Inoltre, **evita il problema** della sospensione dell'intera applicazione durante un'operazione di I/O di un singolo thread: il blocco rimane confinato al solo thread richiedente.

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

È un compromesso tra i due modelli precedenti: mantiene l'efficienza dei thread utente, ma consente al kernel di distribuire il carico su più CPU.

##### **Motivazione storica**

Questo modello nasce per **superare un limite pratico** del modello 1:1: in molti sistemi il **numero massimo di thread kernel** che si possono creare è **forzatamente limitato**, perché generare un thread a livello kernel comporta un overhead non trascurabile e troppi kernel thread degraderebbero le prestazioni. Mappando **N thread utente su M thread kernel** (con M < N), si risolve simultaneamente il problema del parallelismo e quello del contenimento del numero di kernel thread attivi.

---
#### **3.4. Modello a due livelli**

È una **variante del molti-a-molti** in cui i **thread utente vengono raggruppati in sottoinsiemi separati**, e **ciascun sottoinsieme** è mappato indipendentemente su un **proprio gruppo di thread kernel** (con cardinalità eventualmente diverse).

##### **Esempio del docente**

Supponiamo di avere un processo con **5 thread utente**, suddivisi in due sottoinsiemi:

- un primo sottoinsieme di **4 thread utente** mappato su un gruppo di thread kernel;
- un secondo sottoinsieme di **1 thread utente** mappato su un singolo thread kernel.

In questo modo si **specializzano i gruppi** di thread utente, dando a ciascuno un'opportunità diversa di esecuzione, tempo di risposta e prontezza, in base alle **esigenze specifiche** dei thread del processo.

![](imgs/Pasted%20image%2020260319151328.png)

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{massima flessibilità, consente scelte dinamiche di mappaggio e specializzazione dei gruppi.} \\\\  
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

Tutti i thread hanno lo **stesso ruolo e priorità**: sono **tutti uguali, tutti capaci di risolvere l'applicazione**. Le richieste che pervengono al processo possono essere trattate da **uno qualunque** di essi, dato che sono **equivalenti** tra loro.

$$  
\text{Esempio: thread simmetrici in un server web che gestiscono richieste indipendenti.}  
$$

Questo modello è il più semplice da implementare, ma può diventare inefficiente se i thread non si bilanciano bene nei tempi di lavoro.

---

#### **4.2. Thread gerarchici (coordinatore + lavoratori)**

In questo modello — noto anche come pattern **boss/worker** o **manager/worker** — esiste un **thread coordinatore** e un insieme di **thread lavoratori**:

1. il **coordinatore** è l'**unico abilitato a ricevere le richieste** dal mondo esterno;
2. una volta ricevuta una richiesta, la **comprende** ed effettua eventuali **manipolazioni preliminari**;
3. la **assegna** quindi a uno specifico **thread lavoratore** del gruppo, scegliendo quale in base a criteri propri;
4. il **lavoratore** esegue le attività richieste e produce i risultati raggiunti.

$$  
\text{Esempio: web server con un coordinatore che fa accept() sulle connessioni e le distribuisce ai worker che servono le richieste.}  
$$

Questo approccio migliora il **controllo** e la **modularità**, ma richiede meccanismi di sincronizzazione accurati per evitare deadlock o starvation.

---
#### **4.3. Thread in pipeline**

I thread cooperano **sequenzialmente**, come le stazioni di una catena di montaggio: il risultato di un thread diventa l'input del successivo.

Più precisamente, le richieste che pervengono al processo vengono elaborate così:

1. il **primo thread** della pipeline prende **una richiesta alla volta** dal mondo esterno;
2. esegue su di essa una **elaborazione parziale**, producendo una **risposta intermedia** che diventa una nuova "richiesta" per lo stadio successivo;
3. propaga il risultato al **secondo thread**, che a sua volta esegue un'altra elaborazione parziale e propaga al terzo, e così via;
4. i thread sono **connessi in cascata** uno sull'altro fino a quando l'**ultimo** completa l'elaborazione e rilascia i risultati ai richiedenti.

$$  
\text{Esempio: un sistema di elaborazione video dove thread diversi gestiscono acquisizione, elaborazione e compressione.}  
$$

Questo modello è molto usato nei sistemi **streaming** e nei **filtri dati**, perché permette di sfruttare parallelismo e modularità insieme: mentre uno stadio elabora la richiesta N, lo stadio precedente sta già lavorando sulla richiesta N+1.

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