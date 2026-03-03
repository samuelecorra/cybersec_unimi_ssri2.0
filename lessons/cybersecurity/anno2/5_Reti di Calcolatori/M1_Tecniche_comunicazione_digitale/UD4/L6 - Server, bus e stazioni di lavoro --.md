# **Lezione 6: Server, bus e stazioni di lavoro**

### **1. Il ruolo del server in una rete locale**

In una rete locale (LAN), il **server** è il nodo centrale che fornisce **servizi, risorse e applicazioni condivise** agli altri dispositivi, chiamati **client** o **stazioni di lavoro**.  
Il server è connesso al **backbone** della rete, cioè al collegamento principale ad alta capacità che unisce tutti i segmenti.

Le sue funzioni principali sono:

- **condividere dati e applicazioni** con gli utenti della rete;
    
- gestire l’**autenticazione e i permessi di accesso**;
    
- fungere spesso da **motore di esecuzione dei database** o da gestore dei servizi web, di posta o di stampa.
    

Per sostenere questi compiti, un server deve essere **molto più potente** di un normale computer client.

---

### **2. Caratteristiche hardware di un server**

#### **a) Potenza di calcolo**

Un server deve disporre di un **processore ad alte prestazioni**, spesso con **più CPU** (o più core) in grado di elaborare simultaneamente diverse richieste di rete.  
Nei sistemi più avanzati si utilizzano **architetture multiprocessore simmetriche (SMP)**, dove tutti i processori condividono la stessa memoria centrale.

#### **b) Capacità di memoria**

Il server deve possedere **una grande quantità di RAM**, poiché gestisce contemporaneamente molte operazioni e connessioni.  
Tipicamente si parla di **diversi gigabyte**, spesso oltre i **5 GB**, utilizzando **memorie SDRAM** o tecnologie più recenti con **tempi di accesso inferiori ai 10 ns**.

#### **c) Memoria di massa**

Il sistema di archiviazione deve essere:

- **molto ampio**, per conservare file, database e backup;
    
- **molto veloce**, con tempi di accesso al disco **inferiori ai 10 ms**.
    

I dischi utilizzati sono generalmente di tipo **SCSI (Small Computer System Interface)**, che consentono **alte velocità di trasferimento** e il **collegamento a catena (daisy chain)** di più unità.

Le nuove architetture puntano verso l’uso di **Fiber Channel**, una tecnologia che combina **larghezza di banda elevata** e **bassa latenza**, ideale per ambienti server e storage condiviso.

#### **d) Bus di sistema**

Il **bus** interno del server deve essere in grado di gestire grandi flussi di dati.  
Si utilizzano bus ad alte prestazioni come **Fast PCI** o, nelle versioni più recenti, **PCI-X** e **PCI Express (PCIe)**, che permettono un notevole aumento della **velocità di comunicazione tra CPU, memoria e periferiche**.

---

### **3. Affidabilità e continuità di servizio**

Poiché il server è il cuore della rete, deve garantire **funzionamento continuo** e **massima affidabilità**.  
Per questo vengono adottate soluzioni ridondanti e sistemi di protezione:

#### **a) Backup dei dati**

Si utilizzano **dispositivi di backup** come i **nastri magnetici** o unità dedicate, in grado di salvare periodicamente tutti i dati presenti sul server.  
Questo permette di ripristinare rapidamente il sistema in caso di guasti o cancellazioni accidentali.

#### **b) Alimentazione di emergenza (UPS)**

Un **UPS (Uninterruptible Power Supply)** o **gruppo di continuità** fornisce alimentazione temporanea in caso di blackout, evitando l’arresto improvviso del server e la perdita di dati.

#### **c) Memorizzazione su dischi RAID**

I server professionali utilizzano **sistemi RAID (Redundant Array of Independent Disks)**, in cui **più dischi lavorano insieme** per:

- migliorare le **prestazioni** (parallelismo di lettura/scrittura);
    
- aumentare la **sicurezza dei dati** tramite ridondanza;
    
- permettere la **sostituzione “a caldo” (hot-swap)** di un disco guasto senza spegnere il sistema.
    

#### **d) Alimentazione ridondante**

I server di fascia alta dispongono di **più alimentatori indipendenti**, anch’essi **hot-swappable**, per garantire il funzionamento anche se un modulo di alimentazione si guasta.

---

### **4. La stazione di lavoro**

La **stazione di lavoro** (workstation) è il computer utilizzato dall’utente finale per accedere ai servizi della rete.  
La maggior parte delle applicazioni viene eseguita direttamente su queste macchine, che devono quindi essere **adeguatamente dimensionate** in termini di:

- **potenza di calcolo** (CPU e GPU quando serve);
    
- **quantità di memoria**;
    
- **capacità di archiviazione locale**.
    

Come regola empirica:

> Una workstation deve essere **potente abbastanza da eseguire le proprie applicazioni in modo autonomo**, anche nel caso in cui il server non sia momentaneamente disponibile.

Questo garantisce **continuità operativa** e **maggiore autonomia** dell’utente.

---

### **5. Architettura di rete e bus di comunicazione**

I server e le workstation comunicano attraverso **bus di rete** e **canali di interconnessione ad alta velocità**, che collegano:

- schede di rete (NIC),
    
- switch,
    
- router,
    
- e dispositivi di archiviazione centralizzati.
    

In una rete ben progettata, il bus e il backbone devono assicurare:

- **latenza ridotta** (ritardo minimo nella trasmissione);
    
- **larghezza di banda elevata**;
    
- **ridondanza** per evitare interruzioni del servizio.
    

---

### **6. Sintesi concettuale**

- Il **server** è il cuore logico della rete: gestisce applicazioni, archivi e servizi condivisi.
    
- Deve essere **potente, veloce e affidabile**, con architetture **multiprocessore**, **RAM abbondante** e **dischi SCSI o Fiber Channel**.
    
- La **tecnologia RAID** e gli **UPS** garantiscono sicurezza e continuità del servizio.
    
- Le **workstation** sono i terminali di lavoro, progettate per operare in modo autonomo ma coordinate dal server.
    
- Tutti i dispositivi sono interconnessi tramite **bus ad alte prestazioni** e un **backbone di rete** che assicura velocità e affidabilità.