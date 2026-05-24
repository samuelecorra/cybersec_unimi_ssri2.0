# **M2 UD1 Lezione 1: Funzioni di un sistema operativo**

---

### **1. Introduzione**

Il **sistema operativo (SO)** è il componente software che **gestisce tutte le risorse del calcolatore** e fornisce agli utenti un’interfaccia semplice e uniforme per utilizzarle.  
Il suo scopo è **astrarre la complessità dell’hardware**, **virtualizzare le risorse** e **coordinare i processi** in esecuzione, garantendo efficienza, protezione e usabilità.

In questa lezione analizziamo:

- gli **obiettivi principali** del sistema operativo,
    
- la sua **organizzazione logica**,
    
- e le **funzioni fondamentali** che esso svolge.
    

---

### **2. Obiettivi di un sistema operativo**

Il sistema operativo nasce con due grandi obiettivi: **astrazione** e **virtualizzazione**.

$$  
\begin{cases}  
\textbf{Astrazione:}~ & \text{innalzare il livello di rappresentazione dei componenti hardware.} \\\\  
\textbf{Virtualizzazione:}~ & \text{fornire a ogni programma un ambiente indipendente e dedicato.}  
\end{cases}  
$$

#### **2.1. Astrazione**

L’astrazione serve a **semplificare l’uso del calcolatore**.  
Il sistema operativo trasforma i meccanismi fisici complessi (CPU, memoria, dischi, dispositivi di rete) in **entità logiche più intuitive**, come:

- processi,
    
- file,
    
- directory,
    
- e dispositivi virtuali.

L'innalzamento del livello di astrazione ha un duplice obiettivo:

- **rendere più semplice l'uso e l'accesso alle risorse**, mostrando ai programmi e agli utenti un'interazione più semplice;
- fornire una **gestione ottimizzata delle risorse**, **nascondendo i dettagli di gestione** all'interno di questa astrazione.

Ciò consente di usare il computer **senza conoscere i dettagli dell’hardware**, e al tempo stesso permette una **gestione ottimizzata** delle risorse.

#### **2.2. Virtualizzazione**

La **virtualizzazione** crea un’“immagine” dell'intero sistema di elaborazione **dedicata a ciascun programma** in esecuzione, anche se in realtà esistono **più programmi** caricati nel sistema, che vengono eseguiti apparentemente in parallelo (in realtà, alternandosi sull'unica CPU fisica disponibile).

Ogni processo ha la sensazione di possedere:

- una CPU dedicata,
    
- una memoria privata,
    
- e dispositivi di I/O esclusivi.

Detto in altri termini: la virtualizzazione fornisce a ogni processo l'illusione di avere a disposizione una **propria macchina di von Neumann dedicata**, pur essendo questa fisicamente **condivisa** tra tutti i processi in esecuzione.

##### **Benefici per la programmazione**

Il programmatore, se può considerare di avere una **macchina virtuale dedicata** al suo programma, può:

- **ignorare la presenza degli altri programmi** in esecuzione nel sistema;
- non doversi preoccupare dei **problemi di condivisione delle risorse**;
- non dover gestire eventuali necessità di interazione con altri processi (se non quando esplicitamente desiderate).

Questo assicura **indipendenza, sicurezza e isolamento** tra i programmi, semplificando la programmazione e migliorando la stabilità del sistema.

---

### **3. Organizzazione logica di un sistema operativo**

Un sistema operativo è strutturato in **livelli funzionali**, ciascuno dei quali gestisce una risorsa specifica.  
Questi livelli cooperano per garantire un controllo coerente e uniforme su tutto il sistema.

$$  
\begin{cases}  
\textbf{Gestione del processore} \\\\  
\textbf{Gestione della memoria centrale} \\\\  
\textbf{Gestione delle periferiche} \\\\  
\textbf{Gestione del file system} \\\\  
\textbf{Gestione dell’interfaccia utente}  
\end{cases}  
$$

#### **3.1. Primo livello: astrazione e virtualizzazione dei componenti hardware**

Le prime **tre famiglie** di funzioni del SO — gestione del **processore**, della **memoria centrale** e delle **periferiche** — si occupano di **astrarre e virtualizzare i componenti fisici** della macchina di von Neumann:

- la gestione del processore **"moltiplica" virtualmente la CPU**, mostrando ai programmi P1, P2, P3 in esecuzione un processore apparentemente **dedicato a ciascuno** di essi (pur essendo unico e fisicamente condiviso);
- la gestione della memoria centrale crea un'**immagine virtuale della RAM**, in cui ogni processo crede di avere accessibile una memoria completamente dedicata a sé, mentre in realtà ne ha solo una **porzione**;
- la gestione delle periferiche crea un'**astrazione delle periferiche fisiche** in modo tale che l'insieme dei dispositivi venga visto come dedicato a ciascun programma, anche se in realtà è condiviso.

L'insieme di queste tre funzionalità crea l'**illusione**, per ciascun programma, di avere a disposizione una **macchina di von Neumann personale**.

#### **3.2. Secondo livello: astrazione logica del file system**

Le prime tre famiglie di funzioni, però, creano una visione del sistema ancora **identica a quella fisica**: ogni programma deve infatti **conoscere la struttura fisica** del sistema e — soprattutto — sapere **dove sono poste le varie informazioni**, reperendole in base alla loro **posizione fisica**.

Questo è estremamente **scomodo** dal punto di vista della programmazione e dell'organizzazione del lavoro nel sistema.

Il SO compie allora un **ulteriore salto di qualità** introducendo la **gestione del file system**, che:

- mostra ai programmi una visione **ancora più astratta e di livello superiore**;
- **nasconde i dettagli di dove sono poste le informazioni**;
- permette di reperire le informazioni tramite un **nome logico**, non più tramite la loro posizione fisica nel sistema.

#### **3.3. Interfaccia verso utenti e programmi**

A completamento, il SO espone un'**interfaccia** che consente:

- a un **utente fisico** di digitare comandi attraverso opportuni dispositivi di I/O;
- ai **programmi applicativi** di chiamare le funzioni messe a disposizione dal sistema operativo (system call).

---

### **4. Funzioni di un sistema operativo**

#### **4.1. Gestione del processore**

Il sistema operativo gestisce il **processore** organizzando l’esecuzione dei programmi attraverso i **processi**.

> Nei sistemi operativi moderni, i programmi in esecuzione vengono chiamati **processi** perché sono **sequenze di attività** svolte dal sistema.

Le principali funzioni sono:

$$  
\begin{cases}  
\text{Creazione e terminazione dei processi;} \\\\  
\text{Sospensione e riattivazione;} \\\\  
\text{Schedulazione della CPU;} \\\\  
\text{Sincronizzazione tra processi;} \\\\  
\text{Gestione dei deadlock;} \\\\  
\text{Comunicazione tra processi (IPC).}  
\end{cases}  
$$

##### **Schedulazione**

La gestione ottimale del processore — ovvero la **condivisione del processore nel tempo** — viene ottenuta attraverso opportuni **algoritmi di schedulazione** dei processi, che determinano l'**ordine** con cui i processi vengono eseguiti, in modo da far apparire la CPU dedicata a ciascun processo e quindi creare la **virtualizzazione del processore**.

##### **Sincronizzazione e deadlock**

I processi possono interagire tra loro **sincronizzandosi** per l'uso di risorse condivise: il SO mette quindi a disposizione un insieme di funzioni che supportano tale sincronizzazione.

Tuttavia, i meccanismi di sincronizzazione possono dare origine ad **attese infinite** (*deadlock*): situazioni in cui **ciascun processo è in attesa di risorse possedute da altri processi**, le quali però **non verranno mai rilasciate**, perché a loro volta gli altri processi sono in attesa di risorse possedute dal primo. Il sistema operativo deve essere in grado di **rilevare e risolvere** queste situazioni di stallo.

##### **Cooperazione vs competizione**

I processi non solo **competono** per l'uso di risorse condivise, ma possono anche **cooperare** tra loro. Per questo motivo il SO fornisce un insieme di funzioni di **comunicazione tra processi** (IPC), che permettono lo scambio strutturato di informazioni.

Grazie a queste funzioni, più programmi possono condividere la CPU in modo ordinato e controllato.

---

#### **4.2. Gestione della memoria centrale**

La **memoria centrale (RAM)** contiene i processi in esecuzione e i loro dati.  
Il sistema operativo deve gestirla in modo efficiente e sicuro, garantendo che:

- più processi possano coesistere (multiprogrammazione),
    
- nessuno acceda a zone riservate di altri.
    

$$  
\begin{cases}  
\text{Allocazione e deallocazione della memoria ai processi;} \\\\  
\text{Caricamento e scaricamento di processi o porzioni di essi;} \\\\  
\text{Protezione e isolamento delle aree di memoria.}  
\end{cases}  
$$

---

#### **4.3. Gestione delle periferiche**

Le **periferiche** (dischi, tastiera, rete, ecc.) hanno caratteristiche molto diverse tra loro.  
Il sistema operativo ha quindi come obiettivo quello di fornire un'**interfaccia unica e omogenea** per l'accesso ai dispositivi, nascondendo la complessità hardware, e soprattutto di **ottimizzarne la gestione in un ambiente condiviso**.

A questo scopo il SO organizza meccanismi di **bufferizzazione** (*buffering*) e di **caching** delle informazioni, per **rendere più veloce l'accesso** ai dati da parte dei processi (riducendo l'attesa dei tempi elettromeccanici dei dispositivi).

$$  
\begin{cases}  
\text{Configurazione e inizializzazione dei dispositivi;} \\\\  
\text{Interfaccia generale e omogenea per l’I/O;} \\\\  
\text{Gestione e ottimizzazione del traffico di dati;} \\\\  
\text{Protezione, buffering e caching.}  
\end{cases}  
$$

Questo garantisce efficienza e omogeneità di interazione tra CPU e mondo esterno.

---

#### **4.4. Gestione del file system**

Il **file system** è l'**ulteriore livello di astrazione** che permette di accedere alle informazioni in modo **logico**, anziché tramite un reperimento **fisico-posizionale** (come avverrebbe nella macchina di von Neumann di base).

In particolare, il file system introduce:

- il **file** come **componente elementare** che descrive una **sequenza di informazioni** memorizzate nel sistema;
- una **organizzazione logica** dei file in una struttura gerarchica, tipicamente l'**albero dei direttori** (directory).

In questo modo, ogni informazione può essere individuata tramite un **nome logico** (es. `/home/utente/documenti/tesi.pdf`) anziché tramite la sua **posizione fisica** su disco (settore, traccia, blocco).

Tali funzioni devono permettere di **gestire file e directory** consentendone:

$$  
\begin{cases}  
\text{Creazione, cancellazione e copia di file e directory;} \\\\  
\text{Lettura, scrittura e ricerca di dati;} \\\\  
\text{Protezione, permessi e sicurezza;} \\\\  
\text{Accounting (valutazioni d'uso), salvataggio e ripristino.}  
\end{cases}  
$$

---

#### **4.5. Gestione dell’interfaccia utente**

L’interfaccia utente consente la comunicazione tra l’utente (o i programmi) e il sistema operativo, permettendo di **dare comandi** e di **ricevere i risultati dell'elaborazione**.

Esistono due tipologie di interpreti dei comandi:

- un **interprete per gli utenti**, con cui essi interagiscono digitando comandi (CLI testuale o GUI grafica);
- un **interprete per i programmi applicativi**, che consente loro di invocare le funzioni del SO tramite **system call**.

Le **librerie di sistema** costituiscono il **modo concreto** con cui i programmi applicativi possono interagire con il sistema operativo: incapsulano le system call e le espongono come API utilizzabili dai linguaggi di programmazione.

Possiamo distinguere:

- **CLI (Command Line Interface)** – testuale e diretta;
    
- **GUI (Graphical User Interface)** – grafica e interattiva.

A queste si aggiungono inoltre le funzioni di **autenticazione degli utenti** — per garantire un accesso protetto alle risorse — e le funzioni di **verifica e gestione di errori e malfunzionamenti** nel sistema.

$$  
\begin{cases}  
\text{Interprete dei comandi utente;} \\\\  
\text{Interprete dei comandi per i programmi (system call);} \\\\  
\text{Librerie e API di sistema;} \\\\  
\text{Autenticazione e gestione degli errori.}  
\end{cases}  
$$

---

### **5. Sintesi finale**

$$  
\begin{cases}  
\textbf{Obiettivi:}~ & \text{astrazione e virtualizzazione delle risorse.} \\\\  
\textbf{Organizzazione logica:}~ & \text{insieme di livelli funzionali (CPU, memoria, I/O, file, interfaccia).} \\\\  
\textbf{Funzioni principali:}~ & \text{gestione processi, memoria, periferiche, file system, interfacce.}  
\end{cases}  
$$

Il sistema operativo, dunque, **nasconde la complessità dell’hardware** e **fornisce un ambiente coerente e controllato** per l’esecuzione dei programmi.  
È l’elemento che rende il calcolatore **una macchina realmente utilizzabile**, traducendo la potenza della macchina di von Neumann in un sistema interattivo e stabile.