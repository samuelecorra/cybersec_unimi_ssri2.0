# **M2 UD2 Lezione 3 - Dischi RAID**

### **1. Introduzione**

Il termine **RAID** (Redundant Array of Inexpensive Disks) indica una tecnologia che consente di **aggregare più dischi fisici** in un’unica unità logica, con l’obiettivo di **migliorare le prestazioni, l’affidabilità o entrambe**.

Un sistema RAID gestisce in modo coordinato un gruppo di dischi in modo che appaiano al sistema operativo come **un solo disco virtuale**, dotato di:

- **maggiore tolleranza ai guasti**,
    
- **prestazioni più elevate**,
    
- e, in molti casi, **riduzione dei costi complessivi** grazie all’uso di dischi standard a basso costo.

---

### **2. Obiettivi del RAID**

Gli obiettivi principali dell’architettura RAID sono due:

1. **Aumentare l’affidabilità (ridondanza)**  
    tramite **replicazione o codici di correzione degli errori**.
    
2. **Aumentare le prestazioni**  
    sfruttando il **parallelismo di accesso** tra più dischi.

---

### **3. Ridondanza e affidabilità**

La **ridondanza** consiste nell’introdurre **informazioni duplicate o di controllo** per garantire la sopravvivenza dei dati in caso di guasto di uno o più dischi.

#### **Forme di ridondanza**

- **Replicazione dei dati:** mantenimento di più copie identiche degli stessi dati.
    
- **Informazioni di correzione:** aggiunta di bit o blocchi di parità.
    
- **Codici ECC (Error Correcting Codes):** codici speciali per rilevare e correggere errori di lettura o scrittura.

#### **Parametri di affidabilità**

- **MTTF (Mean Time To Failure):** tempo medio al guasto di un singolo disco.
    
- **MTTR (Mean Time To Repair):** tempo medio di riparazione o sostituzione.
    
- **MTTDL (Mean Time To Data Loss):** tempo medio alla perdita dei dati complessivi del sistema RAID.

L’obiettivo è **massimizzare il MTTDL**, cioè la durata complessiva del sistema prima di una perdita irreversibile dei dati.

---

### **4. Parallelismo e prestazioni**

Il **parallelismo** consente di aumentare la velocità complessiva del sistema RAID dividendo i dati in **più parti distribuite** sui dischi.

#### **Data striping**

I dati vengono “**spezzati**” e scritti in modo alternato su dischi diversi.

Due modalità principali:

- **Bit-level striping:** distribuzione dei singoli bit tra più dischi.
    
- **Block-level striping:** distribuzione di blocchi di dati interi (più efficiente nei sistemi moderni).

Il risultato è un **aumento della velocità di trasferimento**, poiché più dischi operano in parallelo.

---

### **5. Livelli di RAID**

I sistemi RAID sono classificati in **livelli numerici (RAID 0, 1, 2, ... 6)**, ciascuno caratterizzato da un diverso equilibrio tra prestazioni, ridondanza e costo.

---

#### **RAID 0 – Striping (senza ridondanza)**

- **Descrizione:** i dati vengono suddivisi in blocchi e distribuiti su più dischi (block-level striping).
    
- **Obiettivo:** aumentare le **prestazioni** grazie all’accesso parallelo.
    
- **Tolleranza ai guasti:** assente – il guasto di un solo disco comporta la **perdita totale dei dati**.
    
- **Vantaggi:**
    
    - throughput elevato,
        
    - utilizzo completo dello spazio disponibile.
    
- **Svantaggi:**
    
    - nessuna ridondanza o protezione dei dati.

---

#### **RAID 1 – Mirroring (duplicazione)**

- **Descrizione:** ogni disco ha un duplicato esatto.
    
- **Obiettivo:** garantire **massima affidabilità** replicando i dati su due o più dischi.
    
- **Tolleranza ai guasti:** elevata — i dati restano accessibili anche in caso di guasto di un disco.
    
- **Vantaggi:**
    
    - sicurezza e disponibilità dei dati,
        
    - tempi di lettura migliorati (si può leggere da più copie).
    
- **Svantaggi:**
    
    - costo elevato (serve il doppio dello spazio).

---

#### **RAID 2 – Codici di correzione (ECC)**

- **Descrizione:** i dati vengono suddivisi a livello di bit e associati a **codici di correzione degli errori (Hamming o simili)**.
    
- **Tolleranza ai guasti:** molto alta, poiché gli errori possono essere rilevati e corretti.
    
- **Vantaggi:**
    
    - alta affidabilità teorica.
    
- **Svantaggi:**
    
    - complessità elevata e inefficienza pratica.
    
- **Uso:** raro nei sistemi moderni, sostituito da soluzioni più semplici (RAID 3 o 5).

---

#### **RAID 3 – Parità a bit alternati**

- **Descrizione:** i dati vengono distribuiti a livello di bit su più dischi, mentre un disco separato memorizza la **parità** (bit-interleaved parity).
    
- **Tolleranza ai guasti:** perdita di un solo disco tollerata.
    
- **Vantaggi:**
    
    - elevata velocità nei trasferimenti sequenziali di grandi blocchi.
    
- **Svantaggi:**
    
    - scarsa efficienza con accessi concorrenti a piccoli blocchi (il disco di parità è un collo di bottiglia).

---

#### **RAID 4 – Parità a blocchi alternati**

- **Descrizione:** simile al RAID 3, ma la distribuzione avviene a **livello di blocco** anziché di bit.
    
- **Parità:** mantenuta su un disco dedicato (block-interleaved parity).
    
- **Vantaggi:**
    
    - migliore parallelismo rispetto a RAID 3,
        
    - facile ricostruzione dei dati.
    
- **Svantaggi:**
    
    - disco di parità ancora singolo → rischio di saturazione e rallentamento.

---

#### **RAID 5 – Parità distribuita**

- **Descrizione:** i blocchi di parità non sono concentrati su un solo disco, ma **distribuiti** tra tutti i dischi (block-interleaved distributed parity).
    
- **Tolleranza ai guasti:** può sopportare la perdita di **un disco**.
    
- **Vantaggi:**
    
    - ottimo compromesso tra prestazioni, capacità e sicurezza,
        
    - elevata scalabilità e bilanciamento del carico.
    
- **Svantaggi:**
    
    - ricostruzione dei dati più lenta in caso di guasto.

---

#### **RAID 6 – Doppia parità (P + Q redundancy)**

- **Descrizione:** estensione del RAID 5 che aggiunge **una seconda informazione di parità** (P e Q) per tollerare **fino a due guasti simultanei**.
    
- **Vantaggi:**
    
    - massima sicurezza dei dati.
    
- **Svantaggi:**
    
    - overhead più alto in termini di calcolo e spazio.
    
- **Uso:** tipico di sistemi server e ambienti enterprise.

---

#### **RAID 0+1 e RAID 1+0 (ibridi)**

- **RAID 0+1:**
    
    - crea prima un array con striping (RAID 0), poi lo duplica (RAID 1).
        
    - prestazioni elevate, ma meno tollerante ai guasti (un guasto può rendere inutilizzabile un intero gruppo).
    
- **RAID 1+0 (RAID 10):**
    
    - unisce mirroring e striping, ma in ordine inverso: prima duplicazione, poi distribuzione.
        
    - unisce **affidabilità del RAID 1** e **prestazioni del RAID 0**.
        
    - più resistente ai guasti rispetto a 0+1.

---

### **6. Sintesi finale**

|Livello|Ridondanza|Prestazioni|Tolleranza ai guasti|Note|
|---|---|---|---|---|
|**RAID 0**|❌ Nessuna|🔹 Alta|❌ Nessuna|Solo prestazioni|
|**RAID 1**|✅ Completa|🔸 Media|✅ Alta|Dati duplicati|
|**RAID 2**|✅ ECC|🔸 Media|✅ Alta|Obsoleto|
|**RAID 3**|✅ Parità su 1 disco|🔸 Alta sequenziale|✅ Media|Bottleneck su disco di parità|
|**RAID 4**|✅ Parità a blocchi|🔸 Buona|✅ Media|Parità concentrata|
|**RAID 5**|✅ Parità distribuita|🔹 Alta|✅ Alta|Ottimo compromesso|
|**RAID 6**|✅ Doppia parità|🔸 Media|✅ Molto alta|Enterprise|
|**RAID 10**|✅ Mirroring + Striping|🔹 Alta|✅ Alta|Bilanciato e affidabile|

---

### **7. Conclusione**

Le **architetture RAID** rappresentano una soluzione fondamentale per migliorare **prestazioni, affidabilità e disponibilità dei dati** nei sistemi di memorizzazione.  
La scelta del livello più adatto dipende dal **compromesso desiderato** tra velocità, sicurezza e costo.  
Nei sistemi moderni, i livelli **RAID 5, RAID 6 e RAID 10** sono i più diffusi grazie alla loro **scalabilità e resilienza**.