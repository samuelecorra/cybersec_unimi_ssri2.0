# **M3 UD1 Lezione 1 - Concetto di processo**

---

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

Un qualunque sistema di elaborazione tende a **sfruttare poco il processore**, poiché le operazioni di ingresso/uscita sono usualmente **molto più lente** delle operazioni interne al processore.

Più precisamente:

- l'esecuzione delle istruzioni interne al processore — o che coinvolgono l'accesso alla **memoria centrale** — richiede tempi rapidi, dovuti all'uso di dispositivi di tipo **elettronico**: i tempi di reazione del sistema sono quindi i **tempi elettronici** (nanosecondi);
- le **periferiche**, dovendo interagire col mondo esterno, eseguono operazioni meccaniche, elettromeccaniche o ottiche, attivate da dispositivi che richiedono **attività elettromeccaniche**: sono quindi **molto più lente** del processore (millisecondi o oltre), pur restando velocissime rispetto ai tempi del mondo esterno.

In un sistema sequenziale, quando un programma richiede un'operazione di input/output (es. leggere da disco), la **CPU resta inattiva** finché la periferica non risponde, **non potendo eseguire computazione utile** per far avanzare il programma in esecuzione.

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

All’interno della memoria, un processo è composto da diverse aree, ciascuna con un ruolo preciso nell'evoluzione della computazione:

$$  
\begin{cases}  
\textbf{Area codice (text):}~ & \text{contiene le istruzioni del programma.} \\\\  
\textbf{Area dati (data):}~ & \text{contiene le variabili globali dichiarate dal programmatore.} \\\\  
\textbf{Stack:}~ & \text{memorizza le variabili locali e i parametri delle funzioni.} \\\\  
\textbf{Heap:}~ & \text{contiene le variabili allocate dinamicamente.} \\\\  
\textbf{Registri:}~ & \text{contengono le variabili temporanee e l’indirizzo dell’istruzione corrente (Program Counter).}  
\end{cases}  
$$

Più in dettaglio:

- le **variabili locali** e quelle non locali — incluso il **contesto di esecuzione** della procedura attiva — vengono create dinamicamente sullo **stack** man mano che le procedure vengono invocate;
- le **variabili temporanee generate dal compilatore** per supportare l'esecuzione di **istruzioni complesse** del linguaggio di programmazione vengono poste tipicamente nei **registri** del processore;
- le variabili create dinamicamente tramite le **funzioni apposite** del linguaggio (es. `malloc`, `new`) trovano posto nello **heap**.

---
### **5. Stato di evoluzione della computazione**

#### **5.1. Il processo come funzione che trasforma informazioni**

Astrattamente, il processo può essere visto come una **funzione** che **trasforma informazioni** eseguendo le istruzioni del programma:

$$
\text{Valori iniziali (costanti, input da periferiche)} \;\longrightarrow\; \text{Istruzioni del programma} \;\longrightarrow\; \text{Risultati finali (emessi a periferiche, disco, video)}
$$

L'**evoluzione della computazione** di un processo è l'insieme delle **attività** che esso svolge durante la propria esecuzione, dal caricamento dei valori iniziali fino alla produzione dei risultati finali.

#### **5.2. Macchina a stati finiti**

Il **processo** può essere visto come una **macchina a stati finiti**:  
gli stati rappresentano le informazioni su cui opera, e le transizioni sono determinate dalle istruzioni che modificano tali informazioni.

#### **5.3. Definizione formale**

Lo **stato di evoluzione della computazione** è l'insieme dei valori di **tutte** le informazioni da cui dipende il modo in cui il processo evolverà:

$$  
\text{Stato di evoluzione} = \text{insieme dei valori di tutte le variabili e registri del processo.}  
$$

In particolare:

- **dati globali** (in memoria centrale),
    
- **dati allocati dinamicamente** sullo heap,
    
- **dati sullo stack** (variabili locali, contesto delle procedure attive),
    
- **contenuto dei registri** del processore (incluse le variabili temporanee del compilatore e i risultati temporanei delle istruzioni),
    
- **Program Counter**, che individua **qual è l'istruzione che deve essere eseguita**.

Questo insieme di valori definisce **esattamente dove si trova il processo nella sua esecuzione** e da quali valori dipende la sua evoluzione futura.

---

### **6. Cambio di processo (context switch)**

Quando il sistema operativo decide di sospendere un processo per eseguirne un altro (operazione detta **context switch**), deve:

1. **salvare lo stato di evoluzione della computazione** del processo corrente;
    
2. **caricare lo stato del nuovo processo** da eseguire.

Solo così ciascun processo potrà **riprendere esattamente da dove si era interrotto**, senza errori o inconsistenze — come se il cambio di processo fosse stato **trasparente** per la computazione.

La domanda chiave è: **cosa serve davvero salvare?** Analizziamo componente per componente.

#### **6.1. Codice — di norma non si salva**

In generale, il **codice è fisso e non modificabile**: ciò che è in memoria centrale resta inalterato, e quando torneremo a eseguire il processo troveremo esattamente le stesse istruzioni. Non serve quindi salvarlo.

> ⚠️ **Eccezione: codice automodificante.** Alcuni linguaggi consentono di modificare il codice durante l'esecuzione (codice *self-modifying*). In tal caso il codice diventa parte dello stato di evoluzione e va salvato. Per i linguaggi convenzionali (**C, C++, Pascal, Fortran**) il codice è assunto **non modificabile**, e non ce ne occupiamo in questo corso.

#### **6.2. Dati globali, heap, stack — non si salvano**

Anche **dati globali**, **heap** e **contenuto dello stack** **non vanno salvati** esplicitamente, perché:

- risiedono **in memoria centrale**, nello **spazio di indirizzamento** assegnato al processo;
- se garantiamo che **la memoria di un processo è accessibile solo al processo proprietario**, quella porzione **non sarà toccata da altri processi**;
- i valori vi permarranno inalterati fino alla ripresa dell'esecuzione.

In altre parole: la **protezione della memoria** rende implicito il "salvataggio" di tutto ciò che è nello spazio di indirizzamento del processo.

#### **6.3. Registri e Program Counter — vanno salvati**

L'**unica componente che non risiede nello spazio di indirizzamento del processo** è il **contenuto dei registri** del processore. Quando si cede la CPU a un altro processo, **i registri saranno sovrascritti**, e quindi vanno **salvati** da qualche parte per poter essere ripristinati al rientro in esecuzione.

Vanno salvati:

- tutti i **registri general-purpose**;
- il **Program Counter** (è un registro particolare, ma è un registro);
- gli eventuali flag e registri di stato.

Il salvataggio può avvenire in:

- una **struttura dati del sistema operativo** (tipicamente il **PCB**, descritto più avanti);
- oppure direttamente sulla **cima dello stack** del processo.

#### **6.4. Lo Stack Pointer: l'unico vero problema**

Se i registri vengono memorizzati sulla cima dello stack, **il contenuto dello stack rimane automaticamente in memoria centrale** e non richiede ulteriore gestione.

Resta però un problema: lo **Stack Pointer** è un **registro**, e va perso al cambio di processo come gli altri. Per ritrovare la cima dello stack — e da lì recuperare il Program Counter e i valori dei registri salvati — è necessario **salvare esplicitamente lo Stack Pointer** in modo opportuno (tipicamente nel PCB).

$$
\begin{cases}
\textbf{NON serve salvare:}~ & \text{codice, dati globali, heap, contenuto dello stack (sono in RAM protetta).} \\\\
\textbf{Serve salvare:}~ & \text{tutti i registri della CPU, incluso il Program Counter.} \\\\
\textbf{Va sempre salvato esplicitamente:}~ & \text{lo Stack Pointer (altrimenti non si ritrova la cima dello stack).}
\end{cases}
$$

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

#### **8.1. Le transizioni principali**

##### **New → Ready (creazione completata)**

Quando un processo viene creato, **durante la fase di New** il sistema operativo:

- configura **tutte le informazioni** di gestione del processo;
- riserva lo **spazio in memoria centrale**;
- registra **quali risorse** il processo intende utilizzare;
- imposta le strutture per **accounting** e statistiche d'uso.

Al termine, il SO concede al nuovo processo di **entrare nella competizione** per l'uso del processore, ponendolo nello stato di **Ready**.

##### **Ready → Running (dispatching)**

Dall'insieme dei processi pronti, il SO ne preleva uno secondo opportuni criteri (scheduling) e lo porta in esecuzione tramite il meccanismo del **dispatching**.

##### **Running → Waiting (attesa di una risorsa)**

Il processo può mettersi in attesa di una risorsa o del **completamento di un'operazione di I/O**, lasciando il processore e venendo accodato **automaticamente nella coda di attesa specifica** della risorsa richiesta.

##### **Waiting → Ready (risorsa disponibile)**

Quando l'operazione si completa o la risorsa diventa disponibile, il processo viene spostato dalla coda di attesa di nuovo nello stato **Ready**, perché ora dispone di **tutte le risorse tranne il processore** e può rientrare in competizione.

##### **Running → Ready (preemption)**

Dallo stato di esecuzione il processo può essere **forzatamente sospeso** (interruzione del SO), pur avendo tutte le risorse disponibili per operare. Questo avviene per **lasciare posto ad altri processi** (preemption): il processo torna in Ready, dato che l'unica risorsa che sta perdendo è la CPU, e rientra in competizione con gli altri fino al successivo dispatch.

##### **Running → Terminated (terminazione)**

Quando il processo termina, viene effettuato il **completamento dell'accounting** e il processo viene rimosso dal sistema. Se la terminazione è dovuta a un **errore**, prima della rimozione può essere attivato il **post-mortem debugging**, che consente di analizzare le cause del fallimento.

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

I descrittori (PCB) dei processi vengono **accodati nelle varie code** che rappresentano gli stati del processo. È la tipica struttura dati che il sistema operativo mantiene per organizzare il ciclo di vita dei processi.

![](imgs/Pasted%20image%2020260319131502.png)

$$  
\begin{cases}  
\textbf{Coda dei pronti (Ready Queue):}~ & \text{un'unica coda con tutti i processi pronti a usare la CPU.} \\\\  
\textbf{Coda dei running:}~ & \text{contiene un solo processo (uno per ciascun processore, in multi-CPU).} \\\\
\textbf{Code di attesa (Waiting Queues):}~ & \text{una coda separata per ciascuna risorsa o periferica.} \\\\
\textbf{Coda dei nuovi e terminati:}~ & \text{processi in creazione o terminazione.}  
\end{cases}  
$$

Da notare:

- la coda dei processi **Running** ha un'unica "posizione" per ogni processore fisico presente — su una macchina con CPU singola, **al più un processo** può essere in Running in un dato istante;
- esiste una **coda di attesa distinta per ogni risorsa** (disco, stampante, semaforo, evento, …), non una coda di attesa unica generica.

Le transizioni tra code rappresentano **il ciclo di vita dinamico** dei processi all’interno del sistema operativo.

![](imgs/Pasted%20image%2020260319131515.png)

---

#### **9.3. Modello a code (queueing model)**

L'evoluzione della computazione di un processo e le transizioni tra gli stati possono essere efficacemente descritte tramite un **modello a code**:

- il **processore** è visto come un **servizio** servito da una **coda dei processi pronti**;
- quando un processo viene **creato**, viene inserito nella coda dei pronti;
- quando un processo **termina** la propria computazione, esce dal processore e dal sistema;
- se invece il processo richiede un'operazione di I/O, entra nella **coda di quella specifica periferica** finché l'operazione non si completa, dopodiché torna nella coda dei pronti.

Questo modello consente, fra l'altro, di applicare strumenti analitici (teoria delle code) per stimare prestazioni, tempi di attesa e throughput del sistema.

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

> ⚠️ **Attenzione — distinzione fondamentale:**
> Lo **stato del processo** (New / Ready / Running / Waiting / Terminated) **NON è** lo **stato di evoluzione della computazione** del processo.
>
> - Lo **stato di evoluzione della computazione** descrive *cosa* sta calcolando il processo: l'insieme dei valori di tutte le variabili, dei registri e del Program Counter.
> - Lo **stato del processo** descrive invece *come* il processo sta usando (o non sta usando) il processore.
>
> Sono **due concetti ortogonali** che usano entrambi la parola "stato" — ma si riferiscono a piani completamente diversi della vita del processo.

---
### **11. Conclusione**

Il concetto di **processo** è la base della gestione del processore:  
consente al sistema operativo di **astrarre il flusso di esecuzione**, di **gestire il multitasking** e di **garantire l’equità nell’uso della CPU**.

Nei prossimi passi studieremo **come questi processi vengono pianificati** dal sistema operativo, analizzando gli **algoritmi di schedulazione** che determinano *chi* esegue *cosa* e *quando*.