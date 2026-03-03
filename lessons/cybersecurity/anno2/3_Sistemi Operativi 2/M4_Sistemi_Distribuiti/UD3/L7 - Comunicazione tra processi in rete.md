# **M4 UD3 Lezione 7 - Comunicazione tra processi in rete**

### **1. Introduzione**

Nei sistemi distribuiti, i processi che risiedono su nodi differenti devono poter **comunicare tra loro** per coordinare le attività, condividere dati e sincronizzare le operazioni.  
Le tecniche di comunicazione in rete sono una **naturale estensione** di quelle utilizzate nei sistemi centralizzati, ma devono tener conto della **latenza**, della **possibile perdita di pacchetti** e dell’**eterogeneità delle macchine** coinvolte.

Questa lezione illustra i principali **modelli e strumenti di comunicazione**:

- scambio di messaggi,
    
- mailbox,
    
- file condivisi,
    
- socket.

---

### **2. Scambio di messaggi**

#### **2.1 Concetto generale**

Lo **scambio di messaggi** (message passing) nei sistemi distribuiti funziona in modo **analogo a quello su una singola macchina**, ma con un’ulteriore complessità dovuta alla presenza della rete.

Un messaggio contiene:

- **identificativo del mittente**,
    
- **destinatario**,
    
- **dati** da trasmettere,
    
- eventuali **informazioni di controllo** (priorità, timestamp, ecc.).

#### **2.2 Buffer distribuiti**

In ambiente distribuito, i **buffer di comunicazione** non sono più locali ma **remotizzati**:

- il sistema operativo gestisce la **trasparenza della localizzazione**,
    
- garantisce **ordinamento e consegna affidabile** dei messaggi,
    
- può implementare **politiche di ritrasmissione** in caso di errore o congestione.

---

### **3. Mailbox**

#### **3.1 Analogia con i sistemi centralizzati**

Le **mailbox** (caselle postali) rappresentano un meccanismo di comunicazione **asincrono**.  
In un sistema centralizzato, più processi possono:

- depositare messaggi in una mailbox,
    
- prelevarli in ordine di arrivo (FIFO).

#### **3.2 Mailbox in ambiente distribuito**

Nel contesto distribuito:

- le mailbox possono essere **distribuite tra nodi diversi**,
    
- il sistema operativo gestisce l’**indirizzamento** e la **coerenza** dei messaggi,
    
- le mailbox possono essere **locali o globali** (accessibili da tutti i nodi),
    
- la comunicazione può avvenire **in modo trasparente**, come se i processi risiedessero sullo stesso sistema.

**Vantaggi:**

- asincronia totale (mittente e destinatario non devono essere attivi contemporaneamente);
    
- tolleranza ai guasti parziali della rete;
    
- semplicità concettuale per modelli produttore/consumatore.

---

### **4. File**

#### **4.1 Comunicazione tramite file**

Un altro metodo di comunicazione tra processi è l’utilizzo di **file condivisi**:

- in un sistema singolo, i processi comunicano scrivendo e leggendo nello stesso file;
    
- in un sistema distribuito, i file possono essere **ubicati su server remoti**, accessibili via rete.

#### **4.2 File in ambiente distribuito**

La gestione dei **file remoti** viene affidata al **file system distribuito (DFS)**, che si occupa di:

- rendere **trasparenti le operazioni di lettura/scrittura**,
    
- garantire **coerenza dei dati**,
    
- fornire un **modello di accesso uniforme** a tutti i nodi.

**Esempi di uso:**

- condivisione di risultati intermedi tra processi su macchine diverse,
    
- logging centralizzato di eventi di sistema,
    
- comunicazione batch asincrona.

---

### **5. Socket**

#### **5.1 Concetto**

I **socket** rappresentano l’interfaccia più diffusa per la **comunicazione diretta** tra processi, sia in locale che in rete.  
Un socket è definito da una **coppia (IP, porta)** e fornisce un canale bidirezionale per lo scambio di dati.

#### **5.2 Tipologie**

- **Socket di tipo stream (TCP):** orientati alla connessione, garantiscono l’affidabilità della trasmissione.
    
- **Socket di tipo datagram (UDP):** non orientati alla connessione, adatti a comunicazioni rapide e non garantite.

#### **5.3 Porte e messaggi**

- Ogni servizio di rete è associato a una **porta numerica ben definita**.
    
- I processi si connettono specificando l’indirizzo IP e la porta di destinazione.
    
- I messaggi vengono **impacchettati e inviati** dal sistema operativo attraverso la rete.

#### **5.4 Comunicazione in ambiente distribuito**

Nel contesto distribuito, i socket consentono:

- l’interazione **diretta e sincrona** tra processi remoti;
    
- la costruzione di **architetture client/server**;
    
- la gestione di **più connessioni simultanee** mediante thread o multiprocessi.

**Esempio:**

- un processo server resta in ascolto su una porta (socket in _listen_);
    
- un processo client apre una connessione verso quella porta;
    
- una volta stabilita la connessione, lo scambio avviene tramite **read()** e **write()** su socket.

---

### **6. Realizzazione nel sistema operativo**

Il **sistema operativo** si occupa di **remotizzare le operazioni di comunicazione**:

- mantiene la trasparenza tra comunicazione locale e remota;
    
- gestisce buffering, sincronizzazione e controllo degli errori;
    
- fornisce API uniformi per tutti i meccanismi (messaggi, mailbox, file, socket).

---

### **7. Sintesi finale**

|Metodo|Tipo di comunicazione|Caratteristiche principali|
|---|---|---|
|**Messaggi**|Sincrona o asincrona|Buffer distribuiti, controllo della consegna|
|**Mailbox**|Asincrona|Scambio di messaggi FIFO, comunicazione indiretta|
|**File**|Asincrona|Condivisione persistente di dati, supporto DFS|
|**Socket**|Sincrona|Connessione diretta, client/server, porte di rete|

---

### **8. Conclusione**

La **comunicazione tra processi in rete** estende i principi della comunicazione locale a un ambiente distribuito, offrendo diversi meccanismi con differenti livelli di:

- **astrazione**,
    
- **affidabilità**,
    
- **efficienza**.

Il sistema operativo fornisce il supporto per tutti questi modelli, consentendo di **integrare comunicazioni locali e remote** in modo trasparente, ponendo così le basi per i moderni sistemi **client/server e peer-to-peer**.