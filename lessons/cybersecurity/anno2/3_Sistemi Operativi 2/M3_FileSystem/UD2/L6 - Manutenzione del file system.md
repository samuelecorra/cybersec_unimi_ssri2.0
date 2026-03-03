# **M3 UD2 Lezione 6 - Manutenzione del file system**

### **1. Introduzione**

La **manutenzione del file system** comprende tutte le attività necessarie a **garantire integrità, affidabilità e disponibilità** dei dati nel tempo.  
Un file system efficiente non deve soltanto archiviare informazioni, ma anche **preservarne la consistenza** e permettere il **ripristino** dopo guasti o danneggiamenti.

Gli aspetti principali trattati in questa lezione sono:

- la **gestione degli errori**,
    
- la **coerenza tra dati e metadati**,
    
- e le **tecniche di backup e ripristino**.

---

### **2. Errori nel file system**

I **malfunzionamenti** di un file system possono derivare da due principali categorie di cause:

#### **2.1 Danneggiamenti della struttura dati (consistenza)**

- Si verificano quando la **struttura logica del file system** (directory, indici, tabelle FAT, i-node, ecc.) viene alterata in modo anomalo.
    
- Possono causare:
    
    - perdita di riferimenti ai file,
        
    - incongruenze tra dimensioni e contenuti,
        
    - errori di allocazione o duplicazione dei blocchi.

#### **2.2 Danneggiamenti del supporto fisico**

- Si riferiscono a **guasti hardware o degrado** del dispositivo di memorizzazione.
    
- Esempi tipici:
    
    - settori danneggiati su disco,
        
    - guasti del controller,
        
    - rottura o usura del supporto magnetico/ottico.

Tali errori possono compromettere **definitivamente** l’accesso ai dati se non vengono rilevati e corretti tempestivamente.

---

### **3. Coerenza del file system**

La **coerenza** (o **consistenza**) del file system è la proprietà che assicura la **corrispondenza tra dati e metadati**, sia in memoria centrale che su disco.

#### **3.1 Problematica**

Durante le operazioni di lettura e scrittura, i dati e i metadati possono trovarsi in **stati intermedi non coerenti**, specialmente in caso di:

- interruzioni improvvise di alimentazione,
    
- crash del sistema operativo,
    
- errori del driver o del controller del disco.

#### **3.2 Meccanismi di mantenimento**

Per garantire la consistenza si impiegano:

1. **Controllore della coerenza (consistency checker)**
    
    - Analizza periodicamente la struttura del file system.
        
    - Rileva e corregge incongruenze tra i blocchi e i metadati.
        
    - Esempi: _fsck_ (UNIX/Linux), _chkdsk_ (Windows).
        
2. **Scritture sincrone dei dati e dei metadati critici**
    
    - I dati fondamentali (es. i-node, directory, tabelle) vengono scritti **immediatamente e in modo coordinato** su disco.
        
    - Riduce la probabilità di perdita in caso di interruzione del sistema.

---

### **4. Backup e ripristino**

#### **4.1 Backup: definizione e scopo**

Il **backup** consiste nel creare una **copia di sicurezza** dei dati e dei metadati, con l’obiettivo di poter **recuperare informazioni** in caso di perdita o danneggiamento.

Serve a contrastare eventi come:

- malfunzionamenti hardware,
    
- guasti improvvisi,
    
- errori umani,
    
- disastri fisici (incendi, allagamenti, ecc.).

#### **4.2 Tipologie di backup**

1. **Backup completo**
    
    - Copia **tutti i file e i metadati** presenti nel sistema.
        
    - Garantisce la massima sicurezza, ma richiede **molto tempo e spazio**.
    
2. **Backup incrementale**
    
    - Copia **solo i file modificati** dopo l’ultimo backup.
        
    - È più rapido e compatto, ma il **ripristino** richiede l’unione di più copie.

---

#### **4.3 Ripristino (Restore)**

Il **ripristino** è l’operazione inversa del backup:  
consiste nel **ricaricare i dati e i metadati** dal supporto di backup verso la memoria di massa principale, per riportare il sistema a uno stato coerente precedente al guasto.

- Può essere **totale** (ripristino dell’intero sistema) o **parziale** (ripristino di specifici file o directory).
    
- È essenziale che il backup includa **metadati e strutture di controllo**, non solo i contenuti dei file.

---

### **5. File system con journaling**

Nei **file system moderni** viene adottato un approccio basato sulla **registrazione delle operazioni** (_log-based_ o _transaction-oriented file system_), noto come **journaling**.

#### **5.1 Principio di funzionamento**

- Ogni operazione di scrittura o modifica del file system viene prima **registrata in un log** (journal).
    
- Solo dopo la registrazione, l’operazione viene effettivamente eseguita.
    
- In caso di crash o guasto, il sistema può **ricostruire uno stato coerente** rileggendo il journal e completando o annullando le operazioni incomplete.

#### **5.2 Vantaggi**

- Garantisce un **elevato livello di affidabilità e consistenza**.
    
- Permette un **ripristino rapido** dopo interruzioni improvvise.
    
- Riduce la necessità di controlli completi del file system all’avvio.

#### **5.3 Esempi di file system con journaling**

- **ext3 / ext4** (Linux)
    
- **NTFS** (Windows)
    
- **HFS+ e APFS** (macOS)

---

### **6. Sintesi finale**

|Aspetto|Descrizione|Tecniche principali|
|---|---|---|
|**Errori del file system**|Danneggiamento logico o fisico delle strutture|Controlli di coerenza, manutenzione periodica|
|**Coerenza**|Allineamento tra dati e metadati in memoria e su disco|Consistency checker, scritture sincrone|
|**Backup e ripristino**|Copia e recupero dei dati dopo guasti|Backup completo e incrementale|
|**Journaling**|Registrazione preventiva delle operazioni|Log-based recovery, file system transazionali|

---

### **7. Conclusione**

La manutenzione del file system è essenziale per garantire la **sicurezza e l’affidabilità dei dati**.  
Attraverso controlli di coerenza, procedure di backup e tecniche di journaling, il sistema operativo può **prevenire la perdita di informazioni**, **limitare i danni da guasti fisici** e **ripristinare rapidamente la normalità operativa**.