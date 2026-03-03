# **M4 UD3 Lezione 2 - Chiamata di procedura remota (RPC)**

### **1. Introduzione**

La **chiamata di procedura remota** (_Remote Procedure Call_, **RPC**) è un meccanismo che consente a un processo di **eseguire una procedura su un’altra macchina** della rete come se fosse locale.  
L’obiettivo è **sfruttare le risorse** (informative o fisiche) presenti su un nodo remoto, mantenendo però **il resto del processo** sulla macchina di origine.

Questo approccio semplifica la programmazione distribuita, fornendo un modello di interazione **analogo alla chiamata di procedura locale**, ma basato su **comunicazioni di rete**.

---

### **2. Obiettivi della RPC**

La RPC consente di:

- Eseguire funzioni su **macchine remote** in cui risiedono le risorse necessarie.
    
- **Delegare** l’elaborazione di una parte del programma a un nodo più adatto.
    
- **Mantenere trasparenza**: l’utente non percepisce la differenza tra procedura locale e remota.

In pratica:

> Il processo chiamante è un’entità **attiva**,  
> la procedura chiamata è un’entità **passiva** residente su un altro nodo.

---

### **3. Architettura e realizzazione della RPC**

La chiamata di procedura remota si basa su **comunicazioni strutturate tra processi**.  
Gli elementi fondamentali sono:

1. **Processo chiamante (client)**
    
    - Avvia la richiesta di esecuzione remota.
        
    - Invia un **messaggio strutturato** con:
        
        - identificatore della funzione richiesta,
            
        - parametri della procedura.
    
2. **Procedura chiamata (server)**
    
    - Riceve la richiesta e la elabora.
        
    - Restituisce il **risultato** in un messaggio di risposta.
    
3. **Demone in ascolto (server daemon)**
    
    - È in attesa di richieste su una **porta specifica**.
        
    - Attiva la procedura corrispondente al messaggio ricevuto.
    
4. **Stub**
    
    - Interfaccia che rappresenta la **versione remota della procedura**.
        
    - Esiste uno _stub_ per ogni funzione remota.
        
    - Si occupa di:
        
        - tradurre i parametri nella forma corretta,
            
        - scambiare i messaggi tra client e server.

---

### **4. Esecuzione**

Durante l’esecuzione di una RPC:

1. Il processo chiamante invoca una funzione come se fosse locale.
    
2. Lo _stub client_ traduce i parametri e invia la richiesta al server.
    
3. Il _demone remoto_ riceve la richiesta e attiva la procedura corrispondente.
    
4. Il risultato viene inviato allo _stub client_, che lo riconverte e lo restituisce al programma.

**Vantaggi principali:**

- Trasparenza completa per il programmatore.
    
- Nessuna gestione esplicita della comunicazione in rete.
    
- Semplicità di integrazione nei linguaggi di programmazione procedurali.

---

### **5. Problemi principali**

#### **5.1 Differenze di rappresentazione dei dati**

I nodi della rete possono avere **architetture diverse** (es. endianess o formati di dati differenti).  
Per garantire interoperabilità, viene utilizzata una **rappresentazione esterna standard**:

$$  
\text{XDR – External Data Representation}  
$$

L’XDR converte i dati in un formato neutro comune prima della trasmissione e li riconverte al ricevimento.

---

#### **5.2 Semantica della chiamata**

È necessario gestire le **ripetizioni o perdite** dei messaggi di richiesta e risposta, definendo la **semantica dell’esecuzione**:

|Tipo di semantica|Descrizione|Meccanismo|
|---|---|---|
|**Al più una volta** (_At most once_)|La procedura è eseguita **una sola volta** oppure **non eseguita affatto**, in caso di errore.|Uso di **marche di tempo** o **storico delle richieste** per evitare duplicazioni.|
|**Esattamente una volta** (_Exactly once_)|La procedura è garantita **una sola volta** e sempre eseguita.|Gestione rigorosa dell’unicità della richiesta e della conferma di esecuzione.|

---

### **6. Esempio di applicazione: File System distribuito**

Un esempio tipico di RPC è l’implementazione di un **file system distribuito (DFS)**:

- I **demoni RPC** sui nodi server gestiscono le richieste.
    
- I **client** inviano messaggi alla **porta DFS** del server che ospita il file.
    
- Il **risultato** (esito o dati richiesti) viene restituito tramite un messaggio di risposta.

Questo modello consente ai processi di **accedere a file remoti** come se fossero locali, senza conoscere la posizione fisica dei dati.

---

### **7. Invocazione di metodo remoto (RMI)**

La **Remote Method Invocation (RMI)** è un’estensione orientata agli oggetti della RPC.  
È utilizzata, ad esempio, in **Java**.

#### **7.1 Funzionamento**

- Un thread invoca un **metodo** di un **oggetto remoto**:
    
    - situato in una **JVM diversa** sulla stessa macchina, oppure
        
    - su un **nodo remoto** connesso in rete.
    
- L’ambiente di esecuzione gestisce automaticamente:
    
    - la serializzazione dei parametri,
        
    - la comunicazione,
        
    - e la ricezione del risultato.

#### **7.2 Differenze tra RPC e RMI**

|Aspetto|RPC|RMI|
|---|---|---|
|**Paradigma**|Procedurale|Orientato agli oggetti|
|**Gestione connessione**|Responsabilità del programmatore|Gestita automaticamente dal linguaggio|
|**Trasparenza**|Parziale|Totale|
|**Esempio di implementazione**|C, C++, Python|Java (RMI), CORBA, EJB|

---

### **8. Vantaggi complessivi della RPC**

- **Semplifica la programmazione distribuita** con un modello familiare.
    
- **Nasconde la complessità della rete** dietro interfacce semplici.
    
- **Permette modularità e riuso del codice** in ambienti eterogenei.
    
- **Supporta interoperabilità** tra sistemi e linguaggi diversi.

---

### **9. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Obiettivo**|Eseguire procedure su nodi remoti in modo trasparente|
|**Componenti**|Processo chiamante, stub, demone remoto, messaggi|
|**Problemi principali**|Differenze di rappresentazione, semantica della chiamata|
|**Soluzione**|XDR, marche di tempo, controllo dell’unicità|
|**Estensione OOP**|Invocazione di metodo remoto (RMI)|
|**Esempi**|File system distribuiti, servizi remoti|

---

### **10. Conclusione**

La **chiamata di procedura remota (RPC)** è una delle basi fondamentali della **computazione distribuita**, poiché consente ai processi di comunicare e collaborare senza preoccuparsi della distanza fisica.  
Con l’evoluzione verso ambienti **object-oriented**, la RPC si è trasformata nella **RMI**, che offre una gestione più astratta e automatica, aprendo la strada alle **architetture distribuite moderne** come i servizi web e i sistemi cloud.