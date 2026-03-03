# **M3 UD1 Lezione 3 - Condivisione dei file e protezione (parte 2)**

### **1. Introduzione**

Questa seconda parte della lezione completa il tema della **condivisione dei file** nei sistemi operativi, concentrandosi su:

- la **coerenza** delle informazioni in caso di accessi concorrenti o guasti,
    
- e i meccanismi di **protezione e controllo dell’accesso** ai file.

L’obiettivo è comprendere come il sistema operativo garantisca la **consistenza e la sicurezza dei dati condivisi** in ambienti multiutente e distribuiti.

---

### **2. Coerenza dei file condivisi**

La **coerenza** definisce il modo in cui un file condiviso deve essere gestito affinché le informazioni rimangano **consistenti** tra tutti gli utenti o processi che lo utilizzano.

#### **2.1 Obiettivi**

- Evitare conflitti durante modifiche simultanee.
    
- Garantire che gli aggiornamenti ai file siano **visibili** secondo regole ben definite.

#### **2.2 Modalità di aggiornamento**

Un sistema può adottare diverse **politiche di visibilità delle modifiche**:

1. **Aggiornamenti immediatamente visibili**  
    Le modifiche a un file condiviso diventano visibili **subito** per tutti gli utenti.  
    → massima coerenza, ma rischio di rallentamento e conflitti.
    
2. **Aggiornamenti visibili alla chiusura del file**  
    Le modifiche vengono rese visibili **solo al termine della sessione** del processo che ha eseguito le scritture.  
    → riduce il rischio di conflitti, ma può generare differenze temporanee tra copie del file.
    
3. **Aggiornamenti visibili nelle sessioni successive**  
    Le modifiche vengono applicate solo **dopo una nuova apertura del file**.  
    → garantisce stabilità durante l’uso, ma ritarda la propagazione degli aggiornamenti.
    
4. **File condivisi immutabili**  
    I file non possono essere modificati, solo letti o copiati.  
    → soluzione ideale per file statici (ad esempio librerie, eseguibili, o documentazione condivisa).

---

### **3. Coerenza in caso di guasti**

Nei **sistemi distribuiti o di rete**, la coerenza dei file può essere compromessa da **guasti locali o di comunicazione**.

#### **3.1 Tipologie di guasto**

- **Guasti locali del client** → il nodo che accede al file si interrompe o perde la connessione.
    
- **Guasti locali del server** → perdita temporanea o permanente del nodo che ospita il file.
    
- **Guasti di rete** → interruzioni o degrado nella comunicazione tra nodi.

#### **3.2 Strategie di gestione**

Per mantenere l’integrità e la disponibilità dei dati, il sistema deve gestire:

1. **Rilevamento del guasto**
    
    - Monitoraggio continuo dei nodi e dei collegamenti di rete.
        
    - Meccanismi di _timeout_ e messaggi di verifica (heartbeat).
    
2. **Ripristino (recovery)**
    
    - Operazioni di rollback o ripristino dallo stato precedente.
        
    - Uso di copie temporanee o journaling per recuperare lo stato coerente.
    
3. **Sopravvivenza con capacità limitate**
    
    - Il sistema continua a funzionare in modo parziale fino al ripristino completo.
        
    - Alcuni nodi possono operare in modalità _read-only_.
    
4. **Tolleranza ai guasti mediante ridondanza**
    
    - Repliche dei dati su più nodi (mirroring, RAID, backup).
        
    - Meccanismi di sincronizzazione automatica per garantire consistenza.

---

### **4. Protezione dei file**

L’obiettivo della **protezione** è impedire accessi non autorizzati e garantire la **consistenza logica e semantica** dei dati rispetto agli accessi leciti.

#### **4.1 Scopi della protezione**

- Prevenire modifiche o letture improprie.
    
- Salvaguardare la riservatezza e l’integrità dei dati.
    
- Garantire un accesso coerente con le regole di sicurezza del sistema.

#### **4.2 Tecniche di protezione**

1. **Protezione fisica**  
    Limitazione dell’accesso fisico ai dispositivi di memorizzazione.
    
2. **Permessi sulle operazioni**  
    Assegnazione di privilegi specifici ai singoli utenti o gruppi per ciascun file:

|Operazione|Significato|
|---|---|
|**Lettura (r)**|Permette di leggere il contenuto del file|
|**Scrittura (w)**|Permette di modificarne il contenuto|
|**Esecuzione (x)**|Permette di eseguire il file (se programma)|
|**Cancellazione (d)**|Permette di eliminare il file|
|**Accodamento (a)**|Permette di aggiungere dati in fondo al file|

---

### **5. Controllo dell’accesso**

Il **controllo dell’accesso** stabilisce _chi_ può compiere _quali operazioni_ su una determinata risorsa.

#### **5.1 Principio base**

L’accesso è consentito solo se l’identità del richiedente corrisponde a una **autorizzazione registrata** nel sistema.

#### **5.2 Modelli principali**

1. **Access Control List (ACL)**
    
    - Ogni file mantiene una **lista di controllo degli accessi** con l’elenco degli utenti e i permessi associati.
        
    - Offre **grande flessibilità**, ma può diventare **complessa da gestire** nei sistemi con molti utenti.
    
2. **Versione semplificata dell’ACL**
    
    - Modello **proprietario / gruppo / universo**, usato in UNIX.
        
    - Ogni file è associato a tre livelli di permessi distinti:
        
        - **Owner** (proprietario del file)
            
        - **Group** (utenti appartenenti allo stesso gruppo)
            
        - **Others** (tutti gli altri utenti)
    
3. **Capability List (Lista delle capacità)**
    
    - Rappresenta il punto di vista opposto:  
        per ogni utente si memorizza **l’elenco delle risorse accessibili** e i permessi associati.
        
    - Più compatta e utile in sistemi distribuiti.
    
1. **Password individuali o di gruppo**
    
    - Alcuni file o directory possono essere protetti da **password dedicate**, richieste ogni volta che si tenta l’accesso.
        
    - Metodo semplice ma meno scalabile, adatto a sistemi piccoli o locali.

---

### **6. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Coerenza**|Mantiene consistenza delle informazioni tra utenti e nodi|
|**Modalità di aggiornamento**|Immediata, alla chiusura, post-sessione, immutabile|
|**Guasti gestiti**|Client, server, rete|
|**Protezione**|Evita accessi impropri e garantisce la sicurezza dei dati|
|**Permessi principali**|Lettura, scrittura, esecuzione, cancellazione, accodamento|
|**Controllo accessi**|Basato su ACL, modello UNIX, capability o password|

---

### **7. Conclusione dell’unità**

Con questa lezione si conclude l’**Unità 1 – Interfaccia del file system**.  
Abbiamo visto come il sistema operativo:

- gestisca la **struttura logica** di file e directory,
    
- realizzi la **condivisione controllata** delle informazioni,
    
- e garantisca la **protezione e coerenza** dei dati, anche in presenza di accessi simultanei o guasti.

Questi principi costituiscono le basi per comprendere le **strutture interne e i meccanismi di implementazione del file system**, che verranno approfonditi nelle unità successive.