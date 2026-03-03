# **M3 UD1 Lezione 3 - Condivisione dei file e protezione (parte 1)**

### **1. Introduzione**

Nei sistemi multi-utente e distribuiti, il file system deve consentire la **condivisione sicura dei file** tra diversi utenti e processi, garantendo al contempo **coerenza** dei dati e **protezione** da accessi non autorizzati.

Questa lezione analizza:

- i **modelli di condivisione dei file** in sistemi centralizzati e distribuiti,
    
- le problematiche di **coerenza dei dati** in presenza di accessi simultanei,
    
- e i meccanismi di **protezione e controllo degli accessi**.

---

### **2. Condivisione dei file nei sistemi multi-utente**

Nei **sistemi multi-utente**, più utenti possono accedere contemporaneamente agli stessi file per **scopi di cooperazione o condivisione delle informazioni**.

#### **2.1 Obiettivi principali**

- Favorire la **collaborazione tra utenti** e processi.
    
- Permettere **accessi concorrenti controllati**, in modo da garantire l’integrità dei dati.

#### **2.2 Tipologie di accesso**

- **Accesso contemporaneo** → consentito quando le operazioni sono **compatibili** (ad esempio lettura simultanea).
    
- **Accesso in mutua esclusione** → imposto quando le operazioni sono **incompatibili** (ad esempio scritture simultanee sullo stesso file).

---

### **3. Gestione degli utenti e dei permessi**

Per garantire una condivisione sicura, il sistema operativo deve **identificare in modo univoco** utenti e gruppi, e assegnare loro **diritti di accesso differenziati**.

#### **3.1 Identificazione**

Ogni utente è associato a:

- un **identificatore univoco (UID)**,
    
- e a uno o più **gruppi di appartenenza (GID)**.

#### **3.2 Assegnazione dei diritti**

I diritti di accesso sono stabiliti in funzione di tre categorie:

|Categoria|Descrizione|
|---|---|
|**Utente proprietario (owner)**|Colui che ha creato o possiede il file|
|**Gruppo del proprietario (group)**|Utenti appartenenti allo stesso gruppo|
|**Altri (others)**|Tutti gli altri utenti del sistema|

Ogni categoria può avere permessi differenti di:

- **lettura (r)**,
    
- **scrittura (w)**,
    
- **esecuzione (x)**.

#### **3.3 Verifica degli accessi**

Quando un processo tenta di accedere a un file, il sistema operativo:

1. identifica l’utente richiedente,
    
2. consulta i **metadati del file** (owner, group, permessi),
    
3. e verifica la **compatibilità tra richiesta e diritti**.

Se la richiesta non è conforme, l’operazione viene **negata** e viene generato un **errore di protezione**.

---

### **4. File system remoti e condivisione in rete**

Nei **sistemi distribuiti**, la condivisione dei file avviene anche tra **macchine distinte collegate in rete**.

#### **4.1 Modalità di condivisione**

1. **Trasferimento di file**
    
    - Il file viene **copiato** da un sistema all’altro.
        
    - Esempi:
        
        - **FTP (File Transfer Protocol)**
            
        - **HTTP (HyperText Transfer Protocol)**
        
    - Il trasferimento può essere:
        
        - **anonimo** → accesso libero o pubblico;
            
        - **autenticato** → accesso previa identificazione.
        
2. **File system in rete**
    
    - Il file resta **memorizzato su un server remoto**, ma viene **reso accessibile ai client** attraverso un’interfaccia trasparente.
        
    - Tipologie principali:
        
        - **File system di rete (NFS – Network File System)** con modello **client/server**;
            
        - **File system distribuito**, dove la gestione e la replica dei file è **distribuita tra più nodi** per maggiore efficienza e tolleranza ai guasti.

---

### **5. Coerenza e protezione**

Anche se solo accennati in questa prima parte, due concetti chiave regolano la condivisione:

- **Coerenza** → mantenere i dati sincronizzati e consistenti tra utenti o nodi diversi.
    
- **Protezione** → garantire che solo gli utenti autorizzati possano accedere o modificare i file, secondo regole definite dal sistema.

Questi temi saranno approfonditi nella **seconda parte della lezione**.

---

### **6. Sintesi finale**

|Tema|Descrizione|
|---|---|
|**Condivisione dei file**|Accesso controllato ai file da parte di più utenti o processi|
|**Sistemi multi-utente**|Definizione di permessi per utente, gruppo e altri|
|**File system remoti**|Accesso via rete mediante FTP, HTTP o modelli client/server|
|**Sistemi distribuiti**|Replica e gestione cooperativa dei file tra nodi|
|**Obiettivi principali**|Coerenza dei dati e protezione dagli accessi non autorizzati|
