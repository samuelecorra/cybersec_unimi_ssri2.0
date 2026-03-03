# **M2 UD1 Lezione 2 - Realizzazione del sottosistema di I/O (parte 1)**

### **1. Introduzione**

Il sottosistema di **ingresso/uscita (I/O)** comprende l’insieme dei meccanismi software che permettono al sistema operativo di gestire le operazioni tra CPU, memoria e periferiche in modo efficiente, ordinato e trasparente.

La sua realizzazione si basa su un insieme di **funzioni di gestione** e **strutture dati** che cooperano per ottimizzare il flusso dei dati e garantire l’affidabilità del sistema.

Le principali funzioni di gestione del sottosistema I/O sono:

- **Schedulazione delle operazioni**
    
- **Bufferizzazione**
    
- **Caching**
    
- **Spooling**
    
- **Locking**
    
- **Gestione degli errori**

---

### **2. Schedulazione delle richieste di I/O**

Ogni periferica può ricevere **più richieste simultanee** da processi differenti.  
Il sistema operativo deve quindi **mantenere e gestire una coda di richieste** per ciascun dispositivo, ordinandole in modo da **ottimizzare l’utilizzo complessivo delle risorse**.

#### **Obiettivi**

- Migliorare l’efficienza complessiva del sistema I/O.
    
- Minimizzare i tempi di attesa e di accesso ai dispositivi.
    
- Evitare conflitti e sovrapposizioni nelle operazioni.

#### **Principali politiche di schedulazione**

1. **FIFO (First In, First Out)** – le richieste vengono servite nell’ordine di arrivo.
    
2. **Per priorità** – le richieste più importanti vengono servite prima.
    
3. **Per scadenza (deadline)** – le richieste con vincoli temporali ravvicinati hanno precedenza.
    
4. **Politiche miste** – combinazioni dinamiche dei criteri precedenti, a seconda del tipo di dispositivo.

---

### **3. Bufferizzazione**

La **bufferizzazione** consiste nel **memorizzare temporaneamente i dati** durante il trasferimento tra memoria e periferiche.  
Serve ad adattare **le differenze di velocità e di formato** tra sorgente e destinazione.

#### **Funzioni principali**

- Adattare **velocità diverse** tra CPU/memoria e dispositivi I/O.
    
- Adattare **dimensioni diverse dei blocchi di dati** in trasferimento.
    
- Garantire la **consistenza semantica** dei dati trasferiti:  
    anche se i valori in memoria cambiano prima del completamento dell’operazione, la periferica utilizza comunque **i dati originali al momento della chiamata**.

#### **Vantaggi**

- Riduzione dei tempi di attesa per il processo.
    
- Maggiore parallelismo tra elaborazione e trasferimento dati.
    
- Migliore efficienza complessiva del sistema.

---

### **4. Caching**

Il **caching** è una tecnica di ottimizzazione che consiste nel **conservare una copia dei dati** letti da una periferica in **memoria veloce**, in modo da riutilizzarli rapidamente senza accedere nuovamente al dispositivo fisico.

#### **Scopi principali**

- Ridurre il tempo medio di accesso ai dati.
    
- Evitare letture ripetute da periferiche lente o ad alta latenza.
    
- Sfruttare la **località temporale**: i dati recentemente letti sono probabilmente richiesti di nuovo a breve.

#### **Effetto**

L’uso della cache **riduce il carico complessivo** sul sottosistema I/O, migliorando sensibilmente le prestazioni percepite dai processi.

---

### **5. Spooling**

Lo **spooling** (Simultaneous Peripheral Operations On-Line) è una forma particolare di bufferizzazione usata per **periferiche lente o condivise**, come stampanti o plotter.

#### **Principio**

- I dati in uscita vengono **accumulati in un buffer temporaneo** su disco o in memoria.
    
- Il processo scrivente può continuare l’esecuzione senza attendere il completamento effettivo dell’operazione fisica.
    
- Un **processo dedicato di spooler** si occupa di gestire in coda le operazioni di output.

#### **Vantaggi**

- Disaccoppiamento tra **velocità logica** del processo e **velocità reale** del dispositivo.
    
- Possibilità di servire **più processi contemporaneamente**, grazie alla gestione in coda.
    
- Migliore utilizzo delle periferiche condivise.

---

### **6. Locking (prenotazione dei dispositivi)**

Il **locking** serve a garantire che una periferica **non venga utilizzata simultaneamente da più processi**, prevenendo conflitti e corruzioni di dati.

#### **Principio**

- Quando un processo deve accedere a un dispositivo, **lo prenota** (lock).
    
- Finché la periferica resta bloccata, **nessun altro processo** può utilizzarla.
    
- Al termine dell’operazione, il processo **rilascia il lock**, rendendo il dispositivo nuovamente disponibile.

#### **Esempio**

Durante la stampa di un documento, la stampante viene “bloccata” dal processo spooler; altri processi devono attendere il rilascio prima di poter inviare nuovi lavori.

---

### **7. Gestione degli errori**

Durante le operazioni di I/O possono verificarsi **malfunzionamenti** o **guasti** dei dispositivi.  
Il sistema operativo deve rilevare e gestire tali eventi in modo **robusto e non distruttivo**.

#### **Tipologie di errori**

1. **Guasti permanenti:**  
    difetti fisici irreparabili del dispositivo (es. rottura del disco, guasto meccanico).  
    → Richiedono isolamento e notifica all’amministratore.
    
2. **Malfunzionamenti transitori:**  
    errori temporanei dovuti a rumore elettrico, timeout o sovraccarico del bus.  
    → Possono essere risolti ripetendo l’operazione o eseguendo un reset.

#### **Strategie di gestione**

- Registrazione dell’errore nei log di sistema.
    
- Tentativi di **recupero automatico** o **riavvio dell’operazione**.
    
- In casi critici, **disattivazione temporanea del dispositivo** o del driver.

---

### **8. Sintesi finale**

Le funzioni di gestione del sottosistema I/O costituiscono la base per una comunicazione efficiente e sicura tra CPU e periferiche:

|Funzione|Scopo principale|
|---|---|
|**Schedulazione**|Ordina e ottimizza le richieste I/O|
|**Bufferizzazione**|Adatta velocità e dimensioni tra sorgente e destinazione|
|**Caching**|Riduce i tempi di accesso ai dati|
|**Spooling**|Disaccoppia processi e periferiche lente o condivise|
|**Locking**|Garantisce accesso in mutua esclusione|
|**Gestione errori**|Mantiene l’affidabilità del sistema|

Queste tecniche costituiscono la base logica e funzionale su cui si costruisce l’intero **sottosistema di I/O**, che sarà approfondito nella prossima parte della lezione con l’analisi delle **strutture dati del kernel** e delle **prestazioni del sistema I/O**.