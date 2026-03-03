# **M2 UD1 Lezione 2 - Architetture dei sistemi operativi**

### **1. Introduzione**

Il sistema operativo è un insieme complesso di componenti che devono cooperare in modo coerente, sicuro ed efficiente.  
Per questo, nel tempo sono state sviluppate **diverse architetture**, ognuna con **una diversa filosofia progettuale**.

L’architettura di un sistema operativo descrive **come le sue funzioni sono organizzate** e **come i moduli interagiscono tra loro e con l’hardware**.  
Ogni modello cerca di bilanciare **prestazioni, modularità e facilità di manutenzione**.

---
### **2. Sistema monolitico**

#### **2.1. Struttura e caratteristiche**

È la **forma più antica e semplice** di sistema operativo.  
Tutte le funzioni — gestione dei processi, della memoria, dell’I/O e del file system — sono **contenute in un unico blocco di codice eseguibile**, detto _kernel monolitico_.

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{esecuzione rapida (pochi passaggi e overhead minimo).} \\\\  
\textbf{Svantaggi:}~ & \text{manutenzione molto difficile e scarsa modularità.}  
\end{cases}  
$$

Qualsiasi modifica al kernel implica **ricompilare e testare l’intero sistema**.  
Questo modello era adatto ai sistemi semplici e a bassa complessità hardware, ma è ormai **superato**.

---
### **3. Sistema con struttura gerarchica**

Questo modello introduce una **organizzazione delle funzioni in livelli gerarchici**, dove ciascun livello dipende solo da quelli sottostanti.

Serve a **limitare le dipendenze dirette** tra i moduli, ma **non separa completamente le funzioni**.  
Di conseguenza, anche se migliora la leggibilità, la **manutenzione resta difficile**, poiché i livelli non sono nettamente isolati.

---
### **4. Sistema stratificato**

#### **4.1. Idea di base**

Il sistema stratificato è un’evoluzione della struttura gerarchica:  
ogni **strato** (layer) fornisce **servizi al livello superiore** e utilizza **solo quelli del livello inferiore**.

$$  
\begin{cases}  
\textbf{Strato 0:}~ & \text{hardware} \\  
\textbf{Strato 1:}~ & \text{gestione dei processi e della memoria} \\  
\textbf{Strato 2:}~ & \text{file system e I/O} \\  
\textbf{Strato 3:}~ & \text{interfaccia utente e API}  
\end{cases}  
$$

Questa separazione migliora la **chiarezza progettuale** e la **sicurezza** del sistema.  
Ogni errore rimane confinato al livello in cui si verifica.

#### **4.2. Limiti**

Il principale svantaggio è la **limitata efficienza**: le chiamate tra strati generano overhead e rallentano l’esecuzione.

---
### **5. Sistema a microkernel**

#### **5.1. Concetto**

Il modello a **microkernel** nasce per ridurre la complessità del kernel tradizionale.  
Si basa su una **netta separazione tra meccanismi e politiche**:

$$  
\begin{cases}  
\textbf{Meccanismi:}~ & \text{funzioni di base del sistema (comunicazione, gestione memoria, scheduling).} \\  
\textbf{Politiche:}~ & \text{regole di gestione implementate come processi utente esterni.}  
\end{cases}  
$$

Il kernel contiene **solo i servizi essenziali**, mentre tutto il resto (file system, driver, gestione periferiche, GUI, ecc.) gira in **spazio utente** come processi indipendenti.

#### **5.2. Vantaggi e limiti**

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{maggiore sicurezza, stabilità e facilità di modifica.} \\  
\textbf{Svantaggi:}~ & \text{prestazioni inferiori per via delle continue comunicazioni tra processi.}  
\end{cases}  
$$

Il microkernel è oggi alla base di molti sistemi moderni, come **macOS**, **QNX** e **MINIX 3**, grazie alla sua robustezza e modularità.

---
### **6. Sistema a moduli funzionali**

#### **6.1. Struttura**

Il sistema operativo è suddiviso in **moduli indipendenti**, ognuno dedicato a una funzione specifica (gestione file, memoria, I/O, rete, ecc.).  
I moduli possono essere **caricati o rimossi dinamicamente**, rendendo il sistema flessibile e adattabile.

#### **6.2. Principi chiave**

- **Integrazione modulare**: ogni componente interagisce tramite interfacce ben definite.
    
- **Incapsulamento**: ogni modulo è autonomo e non interferisce con gli altri.

#### **6.3. Vantaggi e limiti**

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{alta flessibilità, buona manutenibilità, possibilità di aggiornamenti mirati.} \\  
\textbf{Svantaggi:}~ & \text{prestazioni inferiori rispetto ai kernel monolitici.}  
\end{cases}  
$$

Questo approccio è oggi molto diffuso (ad esempio nei **kernel Linux**), poiché combina efficienza e modularità.

---
### **7. Sistema a macchine virtuali**

#### **7.1. Concetto**

L’architettura a **macchine virtuali** permette di eseguire più sistemi operativi indipendenti sullo stesso hardware.  
Ogni sistema virtuale dispone di **un ambiente completo e isolato**, gestito da un software detto **hypervisor** o **VMM (Virtual Machine Monitor)**.

#### **7.2. Funzionamento**

L’hypervisor crea **più macchine astratte**, ciascuna con una copia virtuale di CPU, memoria e periferiche.  
In questo modo è possibile **far convivere ambienti diversi** (per esempio Linux, Windows e BSD sulla stessa macchina fisica).

#### **7.3. Vantaggi e limiti**

$$  
\begin{cases}  
\textbf{Vantaggi:}~ & \text{ottima virtualizzazione, isolamento, sicurezza, sperimentazione.} \\  
\textbf{Svantaggi:}~ & \text{prestazioni limitate e maggiore complessità di gestione.}  
\end{cases}  
$$

Le macchine virtuali sono alla base del **cloud computing** e dei moderni **sistemi di virtualizzazione (VMware, VirtualBox, KVM, Hyper-V)**.

---
### **8. Programmi di sistema**

Oltre al kernel vero e proprio, i sistemi operativi includono una serie di **programmi di sistema**, che semplificano la gestione e lo sviluppo software.  
Si distinguono in due categorie:

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
### **11. APPROFONDIMENTO - Classificazione architetturale di Windows e macOS**

Entrambi sono sistemi operativi **ibridi**, cioè non appartengono in modo “puro” a una sola delle architetture viste (monolitico, stratificato, microkernel, ecc.), ma **combinano** diversi principi per bilanciare **prestazioni, sicurezza e modularità**.

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

- Non è un microkernel puro (molti driver e moduli girano in _kernel mode_).
    
- Non è monolitico puro (ha separazione logica, API, moduli caricabili).
    
- È quindi **un kernel ibrido**, progettato per essere **veloce come un monolitico**, ma **stabile e modulare come un microkernel**.

##### **1.3. Esempi**

- Windows NT, 2000, XP, 7, 10, 11 → **ibrido monolitico/modulare**
    
- Kernel: `ntoskrnl.exe`
    
- Driver caricabili dinamicamente (`.sys`) → struttura **modulare**.

---
#### **2. macOS – Architettura ibrida (microkernel + BSD)**

##### **2.1. Struttura generale**

macOS è costruito sul kernel **XNU**, acronimo di _X is Not Unix_.  
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

#### **3. Confronto rapido**

|Sistema Operativo|Architettura|Base storica|Caratteristiche principali|
|---|---|---|---|
|**Windows (NT → 11)**|**Ibrida (monolitico + modulare)**|Kernel NT|Prestazioni elevate, modularità interna, compatibilità ampia|
|**macOS (XNU)**|**Ibrida (microkernel Mach + BSD)**|Mach + BSD|Stabilità, isolamento, sicurezza e compatibilità UNIX|
|**Linux**|**Monolitico modulare**|UNIX-like|Kernel unico con moduli caricabili a runtime|
|**QNX / MINIX 3**|**Microkernel puro**|Ricerca e embedded|Isolamento totale e massima affidabilità|

---
#### **4. Sintesi finale**

$$  
\begin{cases}  
\textbf{Windows:}~ & \text{kernel ibrido con struttura modulare e gestione centralizzata (HAL + Executive).} \\  
\textbf{macOS:}~ & \text{kernel ibrido XNU, basato su Mach e BSD, con I/O Kit a oggetti.} \\  
\textbf{Entrambi:}~ & \text{non appartengono a un solo modello, ma uniscono i vantaggi di più architetture.}  
\end{cases}  
$$
