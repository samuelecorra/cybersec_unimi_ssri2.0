# **M4 UD1 Lezione 1 - Obiettivi e funzioni di un sistema distribuito**

### **1. Introduzione**

Un **sistema distribuito** è un insieme di **sistemi di elaborazione autonomi**, collegati in rete, che cooperano per fornire all’utente un **ambiente integrato e coerente**.  
L’obiettivo è far apparire l’intero sistema come **un’unica macchina logica**, nascondendo la complessità della distribuzione fisica delle risorse.

Questa lezione illustra:

- le **architetture di elaborazione distribuite**,
    
- i **sistemi operativi di rete e distribuiti**,
    
- e i **principali meccanismi di robustezza e trasparenza**.

---

### **2. Architetture di elaborazione distribuite**

#### **2.1 Caratteristiche generali**

Un sistema distribuito è costituito da più **unità di elaborazione**, ciascuna dotata di:

- **Processore:** almeno uno per nodo.
    
- **Memoria:** locale a ciascun sistema di elaborazione.
    
- **Periferiche:** possono essere:
    
    - **locali e condivise** in rete (es. stampanti di rete),
        
    - **locali e non condivise** (es. orologio interno),
        
    - **globali**, accessibili da più nodi.
    
- **Rete di comunicazione:** mezzo che interconnette tutti i sistemi.

#### **2.2 Eterogeneità**

Nei sistemi distribuiti coesistono macchine con **diverse architetture hardware e sistemi operativi**, il che richiede meccanismi di **compatibilità e interoperabilità**.

#### **2.3 Terminologia**

- **Sito (site):** posizione fisica o logica di un sistema.
    
- **Nodo o host:** singola macchina del sistema distribuito.
    
- **Client:** nodo che richiede un servizio.
    
- **Server:** nodo che fornisce risorse o servizi.

---

### **3. Vantaggi dell’elaborazione distribuita**

1. **Integrazione dei sottosistemi**  
    Consente la cooperazione di macchine e servizi differenti in un unico ambiente.
    
2. **Condivisione delle risorse (resource sharing)**  
    Ogni nodo può accedere a risorse remote (file, stampanti, database).
    
3. **Parallelismo della computazione (computation speedup)**  
    Le attività possono essere suddivise tra più nodi, riducendo i tempi di elaborazione.
    
4. **Riduzione di complessità e costi (downsizing)**  
    L’uso di più sistemi minori sostituisce macchine centrali costose.
    
5. **Aumento dell’affidabilità e della tolleranza ai guasti (fault tolerance)**  
    Un guasto locale non compromette l’intero sistema.
    
6. **Scalabilità (scalability)**  
    È possibile aggiungere o rimuovere nodi senza alterare il funzionamento complessivo.

---

### **4. Sistemi operativi per architetture distribuite**

#### **4.1 Sistemi operativi di rete (Network Operating Systems – NOS)**

I sistemi operativi di rete offrono servizi per **l’accesso e la comunicazione tra macchine collegate in rete**, mantenendo tuttavia la **visibilità delle singole componenti**.

**Funzionalità principali:**

- **Accesso remoto alle risorse** (file, stampanti, dispositivi).
    
- **Elaborazione remota** tramite _login remoto_ (`telnet`).
    
- **Comunicazione tra processi remoti** tramite _socket_.
    
- **Esecuzione di procedure remote** (_Remote Procedure Call – RPC_).
    
- **Spooling di stampa remoto**.
    
- **Trasferimento di file** (`ftp`).
    
- **File system remoti** (montaggio e condivisione di directory).
    
- **Posta elettronica (e-mail)**.

> Nei NOS l’utente percepisce la rete, i nodi e le differenze tra sistemi.

---

#### **4.2 Sistemi operativi distribuiti (Distributed Operating Systems – DOS)**

I sistemi operativi distribuiti forniscono **trasparenza totale**:  
le risorse remote appaiono come risorse **locali**, e il sistema gestisce automaticamente la **migrazione dei dati e dei processi**.

**Caratteristiche principali:**

- **Accesso trasparente alle risorse.**
    
- **Gestione automatica della migrazione di dati e processi.**
    
- **Uniformità delle operazioni di sistema tra nodi diversi.**

---

### **5. Migrazione nei sistemi distribuiti**

#### **5.1 Migrazione dei dati**

Tre strategie principali:

1. **Copia–lavoro–salvataggio:**  
    Il file è copiato localmente, modificato e poi rispedito al server (FTP automatizzato).
    
2. **Copia parziale di file:**  
    Simile alla paginazione: solo parti del file sono trasferite.
    
3. **Problema di compatibilità:**  
    Occorre garantire la compatibilità di rappresentazione tra architetture differenti (es. endianness, formati binari).

---

#### **5.2 Migrazione computazionale**

Due approcci principali:

1. **Migrazione di procedura:**
    
    - Esecuzione di **procedure remote** (_RPC_).
        
    - Comunicazione tra processi tramite scambio di messaggi.
        
    - Motivazioni:
        
        - hardware o software remoto più performante,
            
        - disponibilità di risorse specializzate,
            
        - accesso a dati remoti.
    
2. **Migrazione di processo:**
    
    - Trasferimento dell’intero **processo in esecuzione** su un altro nodo.
        
    - Include anche il concetto di **agenti mobili**.
        
    - Motivazioni:
        
        - bilanciamento del carico,
            
        - aumento della velocità di elaborazione,
            
        - accesso a risorse o dati remoti.

---

### **6. Esempi di risorse distribuite**

- **File server distribuito:**  
    Montaggio trasparente di file system remoti.
    
- **Stampanti distribuite:**  
    Accessibili da più nodi in rete.
    
- **Posta elettronica:**  
    Sistema di messaggistica multi-nodo integrato.

---

### **7. Robustezza nei sistemi distribuiti**

I sistemi distribuiti devono essere in grado di **continuare a funzionare anche in presenza di guasti**.

#### **7.1 Tipi di guasto**

- Interruzione di un collegamento di rete.
    
- Guasto di una macchina o di un processo.
    
- Corruzione dei dati o errori di sincronizzazione.

---

#### **7.2 Tecniche di gestione dei guasti**

1. **Rilevamento dei guasti**
    
    - Monitoraggio periodico con _handshaking_ tra nodi.
        
    - Controllo di _timeout_.
        
    - Computazione duplicata con confronto dei risultati.
    
2. **Mascheramento degli errori**
    
    - Duplicazione delle risorse o delle elaborazioni.
        
    - Votazione a maggioranza per la validazione dei risultati.
    
3. **Riconfigurazione del sistema**
    
    - Esclusione automatica dei componenti guasti aggiornando le tabelle di instradamento.
        
    - Sostituzione temporanea delle macchine non operative.
    
4. **Ripristino (Recovery)**
    
    - Reintegrazione dei nodi riparati nel sistema.
        
    - Aggiornamento delle tabelle e sincronizzazione con gli altri nodi.
        
    - Gestione dei messaggi o file non consegnati.

---

### **8. Aspetti progettuali di un sistema distribuito**

Un sistema operativo distribuito deve garantire:

- **Trasparenza di allocazione delle risorse**  
    (processori, memorie, periferiche, file).
    
- **Mobilità** dell’utente, dei dati e dei processi.
    
- **Tolleranza ai guasti** e **continuità del servizio**.
    
- **Scalabilità**, ossia capacità di espandersi senza perdita di prestazioni.

---

### **9. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Architettura**|Insieme di nodi interconnessi e cooperanti|
|**Vantaggi**|Condivisione risorse, parallelismo, affidabilità, scalabilità|
|**Sistemi operativi di rete**|Accesso remoto visibile all’utente|
|**Sistemi operativi distribuiti**|Trasparenza totale, migrazione automatica|
|**Robustezza**|Rilevamento, mascheramento, riconfigurazione, ripristino|
|**Obiettivo finale**|Sistema unificato, efficiente e tollerante ai guasti|

---

### **10. Conclusione**

I **sistemi distribuiti** rappresentano un’evoluzione naturale dei sistemi multiprocessore, orientati alla **cooperazione tra elaboratori autonomi**.  
La loro forza risiede nella **condivisione efficiente delle risorse**, nella **scalabilità**, e nella capacità di garantire **continuità operativa anche in presenza di guasti**.  
Le funzioni descritte in questa lezione costituiscono la base per comprendere i **meccanismi di gestione, sincronizzazione e comunicazione** che verranno approfonditi nelle unità successive.