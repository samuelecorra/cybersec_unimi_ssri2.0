# **M3 UD2 Lezione 1 - Thread**

---

### **1. Introduzione**

Con l’aumento della complessità delle applicazioni e la necessità di tempi di risposta sempre più rapidi, la gestione basata solo sui **processi tradizionali** non è più sufficiente.  
Molte applicazioni moderne — come **server web, sistemi informativi o programmi interattivi complessi** — devono gestire **più attività contemporaneamente** (ad esempio, più richieste di utenti o flussi di dati paralleli).

Per soddisfare queste esigenze nasce il concetto di **thread**, un’evoluzione naturale del processo, che consente di eseguire **più flussi di controllo** all’interno dello stesso programma, condividendo le stesse risorse.

---

### **2. Motivazioni**

#### **2.1. Le tre attività tipiche di un'applicazione**

Una qualunque applicazione esegue tipicamente **tre tipi di attività**:

$$  
\begin{cases}  
\textbf{Controllo del flusso di operazioni} & \text{(sequenza logica di esecuzione)} \\\\  
\textbf{Operazioni di I/O} & \text{(lettura, scrittura, comunicazione con periferiche)} \\\\  
\textbf{Elaborazione + spostamento dati} & \text{(calcolo aritmetico/logico, trasferimenti memoria ↔ processore)}  
\end{cases}  
$$

Nelle applicazioni con **alta disponibilità del servizio** e **basso tempo di risposta**, è necessario porre molta cura nel **bilanciamento e nell'esecuzione** di queste attività.

#### **2.2. Esempi che richiedono multi-flusso**

- **Word processor interattivo**: l'utente vuole vedere **immediatamente** l'effetto delle modifiche — sull'impaginazione, sui caratteri, nella scrittura del testo, nella realizzazione di immagini e formule. Richiede forte interattività e tempo di risposta molto basso.
- **Server web**: il sistema che eroga le pagine deve rispondere con un tempo molto basso a richieste provenienti da **tanti utenti contemporaneamente**, mantenendo un'elevata **disponibilità** anche sotto carico.
- **Sistemi informativi complessi** (grandi basi di dati, analisi e manipolazione intensiva): devono offrire risposte rapide a molti utenti e garantire **scalabilità** — il sistema non deve "sedersi" e diventare lento al crescere del numero di utenti.

#### **2.3. I problemi che ne derivano**

Da questi scenari emergono problemi specifici:

- è necessario avere **più flussi di controllo nello stesso processo** per svolgere attività simili (es. servire più richieste in parallelo);
- bisogna gestire con cura l'**attesa nelle operazioni di I/O**: se un processo sta attendendo il completamento di un I/O, **non riesce ad ascoltare richieste analoghe** che arrivano nel frattempo;
- spesso è necessario **condividere molta informazione** tra le entità che cooperano nell'applicazione (es. pagine web in cache, dati comuni di un Word processor) — diventa cruciale poter condividere memoria centrale come **mezzo rapido di scambio**.

In sistemi tradizionali basati su processi, ogni attività indipendente doveva essere gestita da **un processo distinto**, con conseguenti:

- **ritardi** dovuti alle attese di I/O,
- **spreco di risorse** per la duplicazione di memoria,
- **scarsa efficienza** nella comunicazione tra processi separati.

---

### **3. Limiti dell’approccio a soli processi**

$$  
\begin{cases}  
\textbf{Problema 1:}~ & \text{ogni processo ha un solo flusso di controllo (un solo thread).} \\\\  
\textbf{Problema 2:}~ & \text{durante un’operazione di I/O, la CPU rimane inutilizzata.} \\\\  
\textbf{Problema 3:}~ & \text{la comunicazione tra processi richiede passaggi complessi e lenti (IPC, Inter Process Communication).}  
\end{cases}  
$$

In applicazioni come un **server web**, questi limiti diventano critici: mentre un processo attende la risposta da un client, non può servire altre richieste.

#### **3.1. L'approccio tradizionale a processi: un servizio + tanti client**

Con i soli processi, il problema verrebbe affrontato instaurando un **processo di servizio** centrale e tanti **processi client** che cooperano con esso o vi accedono. Questo modello è **fattibile**, ma **non è efficiente**: il costo della creazione dei processi e dell'IPC tra essi rende difficile raggiungere gli obiettivi di **alta disponibilità** e **basso tempo di risposta** richiesti dagli scenari moderni.

---

### **4. Soluzione: il modello multi-thread**

La soluzione consiste nel permettere **più flussi di controllo nello stesso processo**.  
Nasce così il concetto di **thread**, anche detto **processo leggero** (_lightweight process_).

![Processo single-thread vs processo multi-thread](imgs/Pasted%20image%2020260319145100.png)

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

#### **5.1. Cosa viene condiviso e cosa è privato**

All'interno di uno stesso processo multi-thread:

$$
\begin{cases}
\textbf{Condivisi tra tutti i thread:}~ & \text{codice (unico), dati globali, variabili allocate dinamicamente (heap), file aperti.} \\\\
\textbf{Privati di ciascun thread:}~ & \text{contesto di esecuzione, stack, registri (incluso Program Counter).}
\end{cases}
$$

#### **5.2. Perché ogni thread deve avere il proprio stack (e registri)**

Il punto chiave è capire **perché** stack e registri **non possono essere condivisi**.

Se ci fosse un **solo stack** per tutti i thread, di fatto si avrebbe **l'evoluzione uguale per tutti**: si attiverebbero **sempre le stesse procedure** con gli **stessi valori delle variabili locali**, perché lo stack determina la sequenza di chiamate attive e i loro frame.

Per consentire l'**evoluzione parallela e indipendente** dei thread — ciascuno con la propria sequenza di chiamate di procedura e i propri valori temporanei — è quindi indispensabile che ogni thread disponga di:

- uno **stack proprio**, su cui costruisce autonomamente la propria catena di attivazione delle procedure e le proprie variabili locali;
- una **copia propria dei registri**, incluso il **Program Counter**, che individua quale istruzione del codice condiviso il thread sta eseguendo in quel momento.

In questo modo, sullo **stesso codice condiviso**, ogni thread può trovarsi in un **punto diverso** dell'esecuzione e portare avanti una **computazione diversa**.

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

#### **6.1. Alta affidabilità e disponibilità**

Il modello multi-thread garantisce **alta affidabilità** e **alta disponibilità** del sistema: esiste la **probabilità** che almeno un thread sia libero in ogni momento e quindi pronto a rispondere a un evento esterno. Solo se le richieste sono **veramente tantissime** e non ci sono abbastanza thread disponibili, alcune richieste rimangono pendenti per un tempo non trascurabile.

#### **6.2. Economia di memoria e di esecuzione**

L'uso dei thread porta a un'**economia di occupazione in memoria centrale**: c'è un **solo codice**, un **solo insieme di dati globali**, un **solo insieme di informazioni di gestione dei file**, indipendentemente da quanti thread compongano il processo.

Ma c'è anche un'**economia nell'esecuzione delle operazioni di accesso alle informazioni condivise**: **non è necessario attivare i meccanismi complessi** che sarebbero richiesti per **superare la barriera di separazione** tra spazi di indirizzamento di processi diversi.

Ricordiamo infatti che, per garantire la consistenza dell'informazione, **un processo può accedere solo alla propria memoria**. Questo comporta serie difficoltà nella condivisione di informazioni tra processi diversi: spesso non è possibile farlo in modo efficiente in memoria centrale, e in alcuni casi l'accesso è **del tutto impedito** se la separazione rigida tra spazi di indirizzamento viene applicata strettamente.

#### **6.3. Asimmetria nella protezione: perché il SO può "rilassare" la separazione tra thread**

Un processo diviso in thread, **esternamente**, si comporta come un insieme di processi cooperanti — ma **internamente** non esistono barriere di comunicazione tra i thread, perché tutti accedono alla **stessa memoria centrale** unica del processo.

Il **sistema operativo può accettare** di non forzare la separazione degli spazi di memoria tra i thread di uno stesso processo, perché:

- anche se i thread si "danno fastidio" modificando il contenuto della memoria centrale, **non possono mai disturbare gli altri processi** del sistema;
- gli eventuali problemi rimarrebbero **tutti e soli** dell'applicazione che ha problemi tra i propri thread (l'applicazione potrebbe bloccarsi o comportarsi in modo scorretto, ma soltanto **lei**);
- dal punto di vista del SO, **nessun'altra applicazione viene toccata** da errori o malevolenze interne a un'applicazione multi-thread che si comporti male.

In sintesi: la **rigida separazione** ha senso **tra processi diversi**; **all'interno di uno stesso processo**, la separazione tra thread sarebbe un costo senza beneficio per la protezione globale del sistema.

#### **6.4. Sfruttamento delle architetture multiprocessore**

Un ulteriore vantaggio è la possibilità di utilizzare **architetture multiprocessore** in modo efficiente: i thread di uno stesso processo possono essere **distribuiti dinamicamente** sui vari processori disponibili, migliorando così **globalmente il tempo di risposta** dell'applicazione. Si ottiene quindi vero parallelismo (non solo concorrenza), trasparente al codice applicativo.

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
