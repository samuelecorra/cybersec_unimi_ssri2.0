# **M2 UD1 Lezione 2 - Architetture dei sistemi operativi**

---

### **1. Introduzione**

Il sistema operativo è un insieme complesso di componenti che devono cooperare in modo coerente, sicuro ed efficiente.  
Per questo, nel tempo sono state sviluppate **diverse architetture**, ognuna con **una diversa filosofia progettuale**.

L’architettura di un sistema operativo descrive **come le sue funzioni sono organizzate** e **come i moduli interagiscono tra loro e con l’hardware**.  
Ogni modello cerca di bilanciare **prestazioni, modularità e facilità di manutenzione**.

---
### **2. Sistema monolitico**

#### **2.1. Struttura e caratteristiche**

È la **forma più antica e semplice** di sistema operativo, tipica dei **primissimi sistemi operativi degli anni '70**.  
Tutte le funzioni — gestione dei processi, della memoria, dell’I/O e del file system — sono **contenute in un unico blocco di codice eseguibile**, detto *kernel monolitico*.

In questo modello il SO è un **"contenitore"** in cui le funzioni vengono inserite **senza una specifica strutturazione**: le chiamate di funzione vengono organizzate come risulta utile dal punto di vista della **programmazione del sistema**, senza vincoli architetturali.

##### **Problema: chiamate "all'indietro"**

La conseguenza è che **funzioni di livello più astratto possono essere chiamate da funzioni di livello più basso**, e viceversa. La struttura globale delle dipendenze diventa quindi un **grafo completo** che può contenere persino dei **cicli**, generando una notevole **confusione nelle dipendenze** delle funzioni una dall'altra.

Questo approccio era tollerabile negli anni '70, quando:

- la **quantità di funzioni** era abbastanza limitata,
- le **macchine da gestire** erano semplici,
- il tipo di gestione richiesto era fondamentalmente **orientato alla gestione a lotti** dei programmi.

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{esecuzione rapida (pochi passaggi e overhead minimo).} \\\\  
\textbf{Svantaggi:}~ & \text{manutenzione molto difficile e scarsa modularità.}  
\end{cases}  
$$

Qualsiasi modifica al kernel implica **ricompilare e testare l’intero sistema**.  
Questo modello, con la crescita della complessità dei sistemi, è stato rapidamente **superato**.

---
### **3. Sistema con struttura gerarchica**

Per superare la difficoltà di manutenzione del modello monolitico, la **prima soluzione** è stata quella di **strutturare le funzioni in modo gerarchico**, organizzando le chiamate in modo che le funzioni di **alto livello** possano chiamare **solo funzioni di più basso livello** (non necessariamente del livello immediatamente inferiore).

In questo modo è stato possibile **eliminare le relazioni "all'indietro"**: chiamate di funzioni semplici, usate dai bassi livelli di gestione, che andavano a invocare funzioni più astratte e di respiro più ampio.

Si è dunque passati da una **struttura delle dipendenze a grafo completo** (eventualmente con cicli) a un'**organizzazione strettamente gerarchica**, identificando esplicite dipendenze gerarchiche tra le funzioni e collocando ciascuna funzione al livello corrispondente.

#### **3.1. Limiti residui**

La manutenzione si è **semplificata**, ma è rimasta ancora difficile: la separazione nei livelli gerarchici è **puramente legata alla dipendenza delle chiamate**, **non ai ruoli funzionali** delle singole componenti. Non c'è quindi chiarezza su quali siano le **responsabilità** delle varie parti del sistema.

---
### **4. Sistema stratificato**

#### **4.1. Idea di base**

Il sistema stratificato introduce una **chiara separazione modulare delle funzioni** svolte da ciascun componente del SO. È un'evoluzione della struttura gerarchica: ogni **strato** (layer) fornisce **servizi al livello superiore** e utilizza **solo quelli del livello inferiore**, ma — a differenza del modello gerarchico — la separazione è ora basata sui **ruoli funzionali** e non solo sulle dipendenze di chiamata.

Lo schema tipico stratifica le funzioni del SO in questo ordine, dal più basso (vicino all'hardware) al più alto:

$$  
\begin{cases}  
\textbf{Livello 1:}~ & \text{gestione del processore (base)} \\\\
\textbf{Livello 2:}~ & \text{gestione della memoria centrale} \\\\
\textbf{Livello 3:}~ & \text{gestione delle periferiche} \\\\
\textbf{Livello 4:}~ & \text{gestione del file system} \\\\
\textbf{Livello 5:}~ & \text{gestione dell'interfaccia utente}
\end{cases}  
$$

Questa separazione migliora la **chiarezza progettuale** e la **sicurezza** del sistema.  
Ogni errore rimane confinato al livello in cui si verifica.

#### **4.2. Limiti**

L'efficienza del sistema risulta **limitata e ridotta** rispetto al caso più semplice (monolitico): un livello che voglia accedere alle operazioni di base per la virtualizzazione dei singoli componenti deve infatti **chiamare i livelli inferiori** uno dopo l'altro, generando **overhead** ad ogni passaggio e rallentando l’esecuzione.

---
### **5. Sistema a microkernel**

#### **5.1. Motivazione**

Il modello a microkernel nasce dall'esigenza di superare i limiti del sistema stratificato in **grossi sistemi complessi con molti utenti**, in cui:

- diventava critico **rispondere in tempi stretti** alle richieste dei vari utenti;
- era necessario poter **aggiornare e modificare il sistema senza mai fermarlo**, garantendo **continuità dei servizi**.

#### **5.2. Concetto: separazione tra meccanismi e politiche**

Il modello a **microkernel** si basa su una **rigida separazione tra meccanismi e politiche**:

$$  
\begin{cases}  
\textbf{Meccanismi:}~ & \text{operazioni di gestione e accesso alle risorse, che non cambiano mai} \\\\
& \text{anche al cambiare del modo con cui si desidera ordinare la gestione.} \\\\
\textbf{Politiche:}~ & \text{regole di gestione — definizione astratta del diritto di uso} \\\\
& \text{delle risorse, dell'ordine d'uso, delle priorità tra processi.}  
\end{cases}  
$$

Nel **microkernel** vengono inseriti **solo i meccanismi** per la gestione degli aspetti di base del sistema: meccanismi per la gestione del processore, della memoria, dell'I/O e così via.

Tutto ciò che è **politica** (e quindi *regole* di gestione, non *operazioni* di gestione) viene posto **al di sopra** del microkernel: ad esempio le politiche del file system o le politiche di scheduling dei processi. Queste porzioni possono addirittura essere **realizzate come processi in esecuzione** sul sistema, e quindi essere modificate, sostituite o aggiornate senza dover fermare il kernel.

#### **5.3. Vantaggi e limiti**

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{maggiore sicurezza, stabilità, modificabilità molto facile.} \\\\
\textbf{Svantaggi:}~ & \text{prestazioni inferiori, soprattutto in sistemi complessi,} \\\\
& \text{per via delle continue comunicazioni tra processi.}  
\end{cases}  
$$

Il microkernel è oggi alla base di molti sistemi moderni, come **macOS**, **QNX** e **MINIX 3**, grazie alla sua robustezza e modularità.

---
### **6. Sistema a moduli funzionali**

#### **6.1. Origine: tecnologie di sviluppo a oggetti**

Un altro modo di organizzare il SO al fine di ottenere un'ottima modificabilità fa ricorso alle **tecnologie di sviluppo software più recenti**, basate sull'uso di **oggetti** e sulla **programmazione con linguaggi orientati agli oggetti**.

#### **6.2. Struttura**

Si ha tipicamente un **kernel** centrale che contiene **soprattutto i meccanismi di base**. Intorno al kernel vengono costruiti **moduli** che si combinano in modo modulare e consentono l'**inserimento e l'estrazione di componenti** senza dover rimodificare il resto del sistema:

- gestione del processore,
- gestione della memoria,
- gestione dell'I/O,
- e ai livelli più elevati, gestione del file system.

I moduli possono essere **caricati o rimossi dinamicamente**, rendendo il sistema flessibile e adattabile.

#### **6.3. Principi chiave**

- **Integrazione modulare**: ogni componente interagisce tramite interfacce ben definite.
    
- **Incapsulamento**: ogni modulo è autonomo e non interferisce con gli altri.

#### **6.4. Vantaggi e limiti**

La **modificabilità** diventa molto buona, ma le **prestazioni degradano** rispetto ai sistemi più elementari: l'introduzione degli oggetti e dell'intera **infrastruttura di gestione degli oggetti** porta a un **overhead di esecuzione** che riduce le prestazioni globali.

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{alta flessibilità, buona manutenibilità, possibilità di aggiornamenti mirati.} \\  
\textbf{Svantaggi:}~ & \text{prestazioni inferiori rispetto ai kernel monolitici per overhead degli oggetti.}  
\end{cases}  
$$

Questo approccio è oggi molto diffuso (ad esempio nei **kernel Linux**), poiché combina efficienza e modularità.

---
### **7. Sistema a macchine virtuali**

#### **7.1. Motivazione e concetto**

L'architettura a **macchine virtuali** nasce dall'esigenza di **astrarre ulteriormente** le risorse, facendo in modo che ogni programma in esecuzione possa vedere un proprio ambiente — eventualmente **diverso** da quello degli altri programmi.

Si tratta di una costruzione gerarchica del SO in cui troviamo:

- in basso, l'**hardware fisico** così come fornito dal produttore;
- al di sopra, un **kernel di macchina virtuale** (*Virtual Machine Monitor*, VMM, detto anche **hypervisor**) che genera più macchine virtuali (VM₁, VM₂, VM₃, …);
- su ciascuna VM viene poi installato uno **specifico sistema operativo**.

#### **7.2. Funzionamento: replicazione, non astrazione**

A differenza degli altri livelli del SO visti finora, il VMM **non astrae** l'hardware: lo **replica esattamente, senza fare nessuna modifica**, creando una **copia per ogni insieme di processi** che si vogliono eseguire su una specifica macchina virtuale.

Su ciascuna VM si può installare un sistema operativo differente: ad esempio uno Unix su VM₁, un OS/2 su VM₂, un altro OS più complesso su VM₃, e così via. **Ciascun OS vede esattamente la macchina hardware così com'è**, come se fosse appoggiato direttamente sull'hardware fisico, **senza sapere** che in realtà ci sono altri ambienti operativi in contemporanea nel sistema.

In questo modo è possibile **far convivere sistemi operativi diversi** sulla stessa macchina fisica (per esempio Linux, Windows e BSD).

##### **Conseguenze sull'organizzazione**

- Ciascuna macchina virtuale può essere usata anche da **un solo processo**.
- Il VMM **virtualizza completamente le risorse**, rendendo **minima la necessità di gestione** ai livelli superiori.
- Le funzioni residue al livello superiore servono solo ad **astrarre e semplificare** l'uso delle risorse, come se fossero sempre completamente dedicate al singolo processo.

#### **7.3. Vantaggi e limiti**

La complessità, l'astrazione e la modularità di questo modello si pagano con **prestazioni ridotte**.

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{ottima virtualizzazione, isolamento, sicurezza, sperimentazione.} \\  
\textbf{Svantaggi:}~ & \text{prestazioni limitate e maggiore complessità di gestione.}  
\end{cases}  
$$

Le macchine virtuali sono alla base del **cloud computing** e dei moderni **sistemi di virtualizzazione (VMware, VirtualBox, KVM, Hyper-V)**.

---
### **8. Programmi di sistema**

Oltre al kernel vero e proprio, i sistemi operativi includono una serie di **programmi di sistema**, che permettono di gestire in modo ottimale le risorse e supportare l'utente nelle attività di sviluppo.

Questi strumenti completano il supporto fornito dal kernel: l'utente non solo dispone delle funzioni del SO secondo le architetture viste, ma anche di **supporti più complessi** utilizzati come normali programmi da mandare in esecuzione sull'architettura di base, qualunque essa sia.

Si distinguono in due grandi categorie.

#### **8.1. Programmi per la gestione delle risorse**

Funzioni di sistema utilizzate per:

- la **gestione dei file**;
- il **reperimento di informazioni sullo stato** del sistema;
- l'**attivazione dell'esecuzione di programmi**;
- l'esecuzione delle **comunicazioni** all'interno del sistema.

#### **8.2. Programmi per lo sviluppo di applicazioni**

Strumenti che supportano l'intero ciclo di vita del software:

$$
\begin{cases}
\textbf{Editor:}~ & \text{programmi per scrivere il codice sorgente delle applicazioni.} \\\\
\textbf{Compilatori / Assemblatori:}~ & \text{programmi per generare il codice oggetto a partire dal sorgente.} \\\\
\textbf{Linker:}~ & \text{programmi per generare il codice eseguibile mettendo insieme} \\\\
& \text{i vari oggetti creati separatamente.} \\\\
\textbf{Debugger:}~ & \text{programmi per verificare la corretta esecuzione del codice} \\\\
& \text{in un ambiente speciale, controllato e guidato.}
\end{cases}
$$

#### **8.3. Riepilogo**

$$  
\begin{cases}  
\textbf{Gestione delle risorse:}~ & \text{utility per file, stato del sistema, comunicazioni, esecuzione processi.} \\  
\textbf{Sviluppo applicazioni:}~ & \text{editor, compilatori, linker, debugger.}  
\end{cases}  
$$

Questi strumenti formano il **livello superiore del sistema operativo**, rendendo la macchina realmente usabile da utenti e programmatori.

---
### **9. Sintesi finale**

$$  
\begin{cases}  
\textbf{Monolitico:}~ & \text{semplice ma difficile da manutenere.} \\\\  
\textbf{Gerarchico:}~ & \text{migliora l’ordine ma resta rigido.} \\\\  
\textbf{Stratificato:}~ & \text{chiaro e sicuro ma meno efficiente.} \\\\  
\textbf{Microkernel:}~ & \text{modificabile e stabile ma più lento.} \\\\  
\textbf{Modulare:}~ & \text{flessibile e diffuso (es. Linux).} \\\\  
\textbf{Macchine virtuali:}~ & \text{massima indipendenza, base del cloud.}  
\end{cases}  
$$

---
### **10. Conclusione**

Le architetture dei sistemi operativi riflettono **l’evoluzione dell’informatica**:  
dalle strutture monolitiche dei primi calcolatori, alle architetture modulari e virtualizzate dei sistemi moderni.

Capire come un sistema operativo è costruito significa **comprendere il modo in cui esso pensa, decide e controlla** la macchina sottostante.  
Il passo successivo sarà analizzare **come nasce e si avvia**: la generazione, il bootstrap e le interfacce operative.

---

### **11. APPROFONDIMENTO - Classificazione architetturale di Windows, macOS e Linux**

I tre grandi sistemi operativi general-purpose di oggi non appartengono in modo "puro" a una sola delle architetture viste (monolitico, stratificato, microkernel, ecc.), ma **combinano** diversi principi per bilanciare **prestazioni, sicurezza e modularità**. Windows e macOS adottano kernel **ibridi**, mentre Linux è un **kernel monolitico modulare**.

---
#### **1. Windows – Architettura ibrida (monolitico + microkernel)**

##### **1.1. Struttura generale**

Windows (dalla linea NT in poi: NT, 2000, XP, 10, 11) si basa su un **kernel ibrido**.  
Significa che **il nucleo centrale contiene ancora molte funzioni monolitiche**, ma è **organizzato modularmente** e **separato in livelli** come in un microkernel.

$$  
\begin{cases}  
\textbf{Livello hardware abstraction layer (HAL):}~ & \text{fornisce un'interfaccia uniforme verso l'hardware.} \\  
\textbf{Kernel:}~ & \text{gestisce thread, interrupt, sincronizzazione, scheduler.} \\  
\textbf{Executive:}~ & \text{implementa servizi di più alto livello (memoria, I/O, file system, sicurezza).} \\  
\textbf{User mode:}~ & \text{ospita subsystem come Win32, POSIX, .NET, GUI, ecc.}  
\end{cases}  
$$

##### **1.2. In sintesi**

- Non è un microkernel puro (molti driver e moduli girano in *kernel mode*).
    
- Non è monolitico puro (ha separazione logica, API, moduli caricabili).
    
- È quindi **un kernel ibrido**, progettato per essere **veloce come un monolitico**, ma **stabile e modulare come un microkernel**.

##### **1.3. Esempi**

- Windows NT, 2000, XP, 7, 10, 11 → **ibrido monolitico/modulare**
    
- Kernel: `ntoskrnl.exe`
    
- Driver caricabili dinamicamente (`.sys`) → struttura **modulare**.

---
#### **2. macOS – Architettura ibrida (microkernel + BSD)**

##### **2.1. Struttura generale**

macOS è costruito sul kernel **XNU**, acronimo di *X is Not Unix*.  
È anch’esso un **kernel ibrido**, derivato da due mondi:

$$  
\begin{cases}  
\textbf{Microkernel Mach:}~ & \text{gestione processi, memoria, messaggi, IPC.} \\  
\textbf{BSD layer:}~ & \text{servizi POSIX, file system, networking e sicurezza.} \\  
\textbf{Driver e I/O Kit:}~ & \text{architettura modulare orientata agli oggetti (C++).}  
\end{cases}  
$$

##### **2.2. Filosofia**

Apple scelse Mach per avere **stabilità e isolamento** (proprietà dei microkernel),  
ma aggiunse BSD per **compatibilità e prestazioni** (caratteristiche dei kernel monolitici).  
Il risultato è un sistema ibrido e stratificato, estremamente coerente e robusto.

##### **2.3. In sintesi**

- Microkernel **Mach** gestisce i meccanismi base.
    
- Sottosistema **BSD** fornisce i servizi POSIX e di rete.
    
- **I/O Kit** realizza la modularità tramite driver caricabili dinamicamente.

---

#### **3. Linux – Architettura monolitica modulare**

##### **3.1. Struttura generale**

Linux è basato su un **kernel monolitico**: tutti i servizi fondamentali del SO (scheduler, gestione memoria, file system, networking, driver di dispositivo) girano nello **stesso spazio di indirizzamento** in **kernel mode**, comunicando tramite chiamate di funzione dirette senza l'overhead di IPC tipico dei microkernel.

A differenza dei monolitici "classici" degli anni '70, però, Linux è anche **fortemente modulare**: il kernel può essere esteso a runtime tramite i **Loadable Kernel Modules** (LKM), file `.ko` (*kernel object*) che vengono caricati e scaricati dinamicamente con `insmod`, `modprobe` e `rmmod`. Questo permette di aggiungere o rimuovere driver, file system, protocolli di rete e altre funzionalità **senza ricompilare né riavviare** il kernel.

$$
\begin{cases}
\textbf{Spazio kernel:}~ & \text{scheduler, MM, VFS, networking, driver — tutto in un unico indirizzamento.} \\\\
\textbf{Loadable Kernel Modules:}~ & \text{estensioni .ko caricabili a runtime (driver, fs, netfilter…).} \\\\
\textbf{Spazio utente:}~ & \text{shell, librerie (glibc), demoni, applicazioni — comunicano via syscall.} \\\\
\textbf{Interfaccia POSIX:}~ & \text{conformità Unix per portabilità delle applicazioni.}
\end{cases}
$$

##### **3.2. Filosofia**

Linus Torvalds, nei celebri dibattiti con Andrew Tanenbaum (autore di MINIX) del 1992, difese esplicitamente la scelta monolitica per ragioni di **prestazioni** e **semplicità implementativa**, rifiutando il microkernel come "obsoleto" per i carichi reali dell'epoca. La modularità tramite LKM è arrivata in seguito (kernel 1.2, 1995) come compromesso pragmatico: **prestazioni del monolitico** + **flessibilità di gestione** vicina a quella dei sistemi modulari.

##### **3.3. In sintesi**

- Kernel unico: `vmlinuz` (l'immagine compressa caricata dal bootloader).
- **Driver e file system** disponibili sia **integrati** nel kernel sia come **moduli caricabili** (`.ko`).
- Comunicazione applicazioni ↔ kernel tramite **system call** standardizzate (interfaccia POSIX).
- Distribuito sotto licenza **GPLv2**, con sviluppo coordinato da Torvalds e da migliaia di contributori.
- Base di **Android** (kernel Linux + runtime Android), di **ChromeOS**, di tutte le principali distribuzioni server e desktop, e della stragrande maggioranza dei sistemi cloud, embedded e supercomputer.

---

#### **4. Confronto rapido**

|Sistema Operativo|Architettura|Base storica|Caratteristiche principali|
|---|---|---|---|
|**Windows (NT → 11)**|**Ibrida (monolitico + modulare)**|Kernel NT|Prestazioni elevate, modularità interna, compatibilità ampia|
|**macOS (XNU)**|**Ibrida (microkernel Mach + BSD)**|Mach + BSD|Stabilità, isolamento, sicurezza e compatibilità UNIX|
|**Linux**|**Monolitico modulare**|UNIX-like (1991, Torvalds)|Kernel unico con moduli `.ko` caricabili a runtime, POSIX, GPL|
|**QNX / MINIX 3**|**Microkernel puro**|Ricerca e embedded|Isolamento totale e massima affidabilità|

---

#### **5. Sintesi finale**

$$  
\begin{cases}  
\textbf{Windows:}~ & \text{kernel ibrido con struttura modulare e gestione centralizzata (HAL + Executive).} \\\\
\textbf{macOS:}~ & \text{kernel ibrido XNU, basato su Mach e BSD, con I/O Kit a oggetti.} \\\\
\textbf{Linux:}~ & \text{kernel monolitico in un unico spazio di indirizzamento, estendibile a runtime via LKM.} \\\\
\textbf{In comune:}~ & \text{nessuno appartiene a un solo modello puro, ognuno combina i vantaggi di più architetture.}  
\end{cases}  
$$
