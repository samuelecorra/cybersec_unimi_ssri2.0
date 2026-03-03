# **M2 UD1 Lezione 1: Funzioni di un sistema operativo**

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
    

Ciò consente di usare il computer **senza conoscere i dettagli dell’hardware**, e al tempo stesso permette una **gestione ottimizzata** delle risorse.

#### **2.2. Virtualizzazione**

La **virtualizzazione** crea un’“immagine” del sistema per ciascun programma in esecuzione.  
Ogni processo ha la sensazione di possedere:

- una CPU dedicata,
    
- una memoria privata,
    
- e dispositivi di I/O esclusivi.
    

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

---

### **4. Funzioni di un sistema operativo**

#### **4.1. Gestione del processore**

Il sistema operativo gestisce il **processore** organizzando l’esecuzione dei programmi attraverso i **processi**.  
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
Il sistema operativo fornisce un’**interfaccia uniforme** per l’accesso ai dispositivi, nascondendo la complessità hardware.

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

I **file** rappresentano i dati in forma permanente e strutturata.  
Il sistema operativo fornisce un **file system** per creare, leggere, scrivere e organizzare i file in modo gerarchico (directory, alberi, percorsi).

$$  
\begin{cases}  
\text{Creazione, cancellazione e copia di file e directory;} \\\\  
\text{Lettura, scrittura e ricerca di dati;} \\\\  
\text{Protezione, permessi e sicurezza;} \\\\  
\text{Accounting, salvataggio e ripristino.}  
\end{cases}  
$$

---

#### **4.5. Gestione dell’interfaccia utente**

L’interfaccia utente consente la comunicazione tra l’utente (o i programmi) e il sistema operativo.  
Può essere di due tipi:

- **CLI (Command Line Interface)** – testuale e diretta;
    
- **GUI (Graphical User Interface)** – grafica e interattiva.
    

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