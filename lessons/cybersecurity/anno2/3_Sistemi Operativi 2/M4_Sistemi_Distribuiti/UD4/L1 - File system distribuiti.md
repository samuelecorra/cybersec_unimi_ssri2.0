# **M4 UD4 Lezione 1 - Struttura e funzioni**

### **1. Introduzione**

Nei sistemi distribuiti, i **file system** permettono di accedere e condividere **risorse informative e fisiche** su più macchine connesse in rete.  
L’obiettivo principale è fornire all’utente una **visione unificata e trasparente** dei dati, indipendentemente dalla loro effettiva posizione.

Un file system distribuito deve quindi garantire:

- **trasparenza d’accesso**,
    
- **efficienza nella gestione delle risorse**,
    
- **consistenza e affidabilità** delle informazioni condivise.

---

### **2. File system di rete e file system distribuito**

#### **2.1 File system di rete (NFS – Network File System)**

Un **file system di rete** è l’insieme dei file system presenti sulle diverse macchine della rete.  
Ogni nodo può **montare localmente** un file system remoto e accedere ai file come se fossero locali.

**Caratteristiche principali:**

- Collezione di file system indipendenti.
    
- Montaggio manuale dei file system remoti.
    
- **Problema:** l’utente deve conoscere la **topologia della rete** e la **posizione fisica delle risorse**.

---

#### **2.2 File system distribuito (DFS – Distributed File System)**

Un **file system distribuito** integra i file system delle varie macchine in un **unico file system globale**, fornendo un accesso uniforme e trasparente.

**Obiettivi:**

- **Omogeneità di visione:** ogni nodo vede la stessa struttura logica dei file.
    
- **Trasparenza dell’allocazione:** l’utente non deve sapere su quale macchina si trova il file.
    
- **Accesso unificato:** i file appaiono come parte di un’unica gerarchia logica.

---

### **3. Nomi dei file**

#### **3.1 Identificazione nei diversi modelli**

- Nei **file system di rete**, un file è identificato da:
    $$
    \text{nome macchina} + \text{percorso del file sul file system remoto}  
    $$
    
    Il file system remoto è “montato” su un punto del file system locale.
    
- Nei **file system distribuiti**, ogni file ha un **nome univoco** all’interno del **file system globale**, indipendente dalla macchina in cui si trova.

---

#### **3.2 Trasparenza del nome e della locazione**

Un file può essere:

- **mappato automaticamente** a un indirizzo **locale o remoto**;
    
- **visibile** oppure **invisibile** nella sua locazione fisica.

**Tipologie di trasparenza:**

- **Trasparenza della posizione:** l’utente non deve conoscere la posizione del file.
    
- **Trasparenza della locazione:** l’accesso non cambia anche se il file viene spostato.

I sistemi distribuiti possono anche gestire:

- **repliche dei file** (copie identiche su nodi diversi),
    
- **migrazione dei file** (spostamento dinamico dei file nella rete).

---

### **4. Accesso ai file**

#### **4.1 File system di rete**

- L’accesso avviene tramite **servizi remoti**, spesso implementati mediante **RPC (Remote Procedure Call)**.
    
- È possibile effettuare:
    
    - **copiatura locale** del file,
        
    - **aggiornamento locale**,
        
    - **salvataggio remoto**.

#### **4.2 File system distribuito**

- L’accesso appare **locale**, anche se le operazioni possono essere eseguite **da remoto in modo trasparente**.
    
- Il sistema si occupa di inoltrare automaticamente le richieste ai nodi corretti.

---

### **5. Gestione della cache**

Per migliorare le prestazioni, i sistemi distribuiti utilizzano **cache locali** sui client.

#### **5.1 Locazione**

- Le cache possono essere **sul client** o **sul server**.

#### **5.2 Politiche di aggiornamento**

|Strategia|Descrizione|
|---|---|
|**Write-through**|Ogni modifica viene subito scritta anche nel server remoto.|
|**Delayed-write**|Le modifiche vengono accumulate e scritte periodicamente.|
|**Write-on-close**|Le modifiche vengono inviate solo quando il file viene chiuso.|

#### **5.3 Consistenza della cache**

Due modalità principali di verifica:

- **Client-initiated:** il client chiede al server se la copia locale è aggiornata.
    
- **Server-initiated:** il server notifica ai client le modifiche effettuate altrove.

La scelta della **dimensione della cache** influisce su prestazioni e coerenza.

---

### **6. Stato del file server**

#### **6.1 File server senza stato (stateless)**

- Ogni richiesta è **autonoma** e contiene tutte le informazioni necessarie.
    
- Il server **non conserva alcuna informazione** tra due richieste successive.
    
- **Vantaggi:** semplicità e maggiore tolleranza ai guasti.
    
- **Svantaggi:** ridotta efficienza, poiché ogni richiesta deve essere completa.

#### **6.2 File server con stato (stateful)**

- Il server mantiene **informazioni sullo stato delle connessioni**:
    
    - file aperti,
        
    - puntatori di lettura/scrittura,
        
    - identificatori di sessione.
    
- **Vantaggi:** maggiore efficienza e riduzione del traffico di rete.
    
- **Svantaggi:** complessità di gestione e minor tolleranza ai guasti.

---

### **7. Replica dei file**

La **replicazione dei file** consiste nel mantenere **copie identiche** dello stesso file su più nodi della rete.

**Scopi principali:**

- **Ridondanza**, per aumentare l’affidabilità e la disponibilità.
    
- **Migliori prestazioni**, grazie alla riduzione dei tempi di accesso.
    
- **Trasparenza:** l’utente non percepisce la presenza di repliche.
    
- **Aggiornamento:** può essere gestito in modo:
    
    - sincrono (tutte le copie sempre identiche),
        
    - asincrono (sincronizzazione periodica).

---

### **8. Sintesi finale**

|Tema|File system di rete|File system distribuito|
|---|---|---|
|**Struttura**|Collezione di FS indipendenti|Integrazione in un unico FS globale|
|**Trasparenza**|Limitata: visibile la macchina remota|Totale: visione unica per l’utente|
|**Accesso**|Tramite RPC e montaggi remoti|Locale con servizi remoti trasparenti|
|**Gestione cache**|Lato client, manuale|Automatica e trasparente|
|**Server**|Stateless (NFS)|Stateful o ibrido (DFS evoluti)|

---

### **9. Conclusione**

Il **file system distribuito** rappresenta l’evoluzione naturale dei file system di rete.  
Permette di ottenere un’unica visione coerente e trasparente dei dati, migliorando **usabilità, efficienza e affidabilità**.  
I concetti chiave – **trasparenza**, **replicazione** e **gestione dello stato** – sono fondamentali per comprendere i moderni **DFS**, come quelli utilizzati nei sistemi **cloud e data center**.