# **M2 UD2 Lezione 4 - Memoria terziaria**

### **1. Introduzione**

La **memoria terziaria** rappresenta il livello più esterno e capiente della **gerarchia di memorizzazione**.  
È utilizzata per archiviare **grandi volumi di dati** a basso costo, che vengono acceduti **raramente** o solo in operazioni di backup, archiviazione o trasferimento.

Questa lezione descrive:

- le **periferiche di archiviazione terziaria**,
    
- le loro **caratteristiche principali**,
    
- e le **tecniche di gestione** adottate dai sistemi operativi per integrarle nella struttura complessiva della memoria.

---

### **2. Obiettivi della memoria terziaria**

L’uso di memorie terziarie è motivato da tre principali obiettivi:

1. **Gestire grandissimi volumi di dati**
    
    - Tipicamente attraverso **supporti removibili**, che possono essere archiviati, trasportati o sostituiti.
    
2. **Ridurre i costi di memorizzazione**
    
    - I dispositivi terziari hanno un **costo per unità di memoria molto più basso** rispetto ai dischi magnetici o SSD.
    
3. **Affrontare basse frequenze di accesso**
    
    - Sono pensate per dati **non usati frequentemente**, dove la **latenza di accesso elevata** non costituisce un problema.

---

### **3. Periferiche di archiviazione terziaria**

Le periferiche appartenenti alla memoria terziaria comprendono diverse tecnologie, ognuna con vantaggi e limiti specifici.

#### **3.1 Dischi rimovibili**

- **Dischetti (floppy disk)** – ormai obsoleti, di bassa capacità e lenta velocità.
    
- **Dischi magnetici** – dischi rigidi rimovibili, di grande capacità ma minor affidabilità meccanica.
    
- **Dischi magneto-ottici** – combinano scrittura magnetica e lettura ottica.
    
- **Dischi ottici** – CD, DVD, Blu-Ray, ampiamente usati per distribuzione o archiviazione.
    
- **Dischi a cambiamento di fase** – sfruttano variazioni fisiche del materiale per rappresentare i dati (es. riscrivibili).

#### **3.2 Nastri magnetici**

- Supporti a **lunghezza lineare** con **accesso sequenziale**.
    
- Ancora oggi impiegati per **backup di grandi sistemi** o archiviazioni di lunga durata.
    
- Offrono un **costo per GB estremamente basso**, ma tempi di accesso molto elevati.

#### **3.3 Memorizzazione olografica**

- Tecnologia sperimentale basata sulla **registrazione tridimensionale della luce**.
    
- Potenziale capacità e densità di memorizzazione molto superiori ai dischi ottici tradizionali.

#### **3.4 Sistemi meccanici microelettronici (MEMS)**

- Sistemi basati su **microattuatori** e **microstrutture mobili**, con densità paragonabile agli hard disk ma con minor consumo energetico e maggior miniaturizzazione.

---

### **4. Caratteristiche delle memorie terziarie**

Le principali caratteristiche da considerare per i dispositivi di memoria terziaria sono:

1. **Velocità**
    
    - Definita in termini di:
        
        - **Larghezza di banda (bandwidth):** quantità di dati trasferibili nell’unità di tempo.
            
        - **Latenza di accesso:** tempo necessario per raggiungere il dato richiesto.
    
2. **Affidabilità**
    
    - Determinata dalla qualità del supporto, dal numero di cicli di riscrittura e dalle condizioni ambientali.
    
3. **Costo**
    
    - Generalmente il **più basso per unità di capacità** tra tutte le classi di memoria.
        
    - Si paga però in termini di **tempo di accesso** e **frequenza di utilizzo**.

---

### **5. Gestione della memoria terziaria (1)**

La gestione di questi dispositivi si articola su due livelli principali:

1. **Gestione dei dispositivi fisici**
    
    - Include il controllo dei supporti, il montaggio e lo smontaggio, la verifica dell’integrità fisica e la sincronizzazione con il sistema operativo.
    
2. **Astrazione virtuale**
    
    - Fornisce una rappresentazione logica del dispositivo, rendendolo compatibile con il file system o con altre interfacce software.

---

### **6. Gestione della memoria terziaria (2)**

#### **Dischi rimovibili**

- Possono essere gestiti come:
    
    - **Dispositivi grezzi (raw device)** → accesso diretto ai blocchi fisici.
        
    - **File system dedicato** → gestione strutturata dei file e delle directory.
    
- Permettono **uso condiviso tra processi** e **accesso diretto o sequenziale**, a seconda della tecnologia.

#### **Nastri magnetici**

- Sempre gestiti come **dispositivi grezzi**.
    
- Consentono solo **uso esclusivo** da parte di un processo alla volta.
    
- L’accesso è **strettamente sequenziale**, con tempi di posizionamento elevati.

---

### **7. Gestione della memoria terziaria (3)**

#### **Denominazione dei file nei supporti rimovibili**

Un problema rilevante nella gestione dei supporti terziari è la **denominazione univoca dei file**.

**Sfida:**  
Garantire che un file mantenga un nome univoco anche quando il supporto viene rimosso o sostituito.

**Problemi principali:**

- Necessità di **includere un identificativo del supporto** nel nome del file.
    
- **Portabilità** dei supporti tra sistemi diversi.
    
- **Mancanza di standard omogenei** per l’identificazione universale dei supporti.

**Standard parziali esistenti:**

- **CD audio**
    
- **CD-ROM / DVD** (standard ISO 9660, UDF)

Negli altri casi, la denominazione univoca è lasciata all’**interpretazione dell’applicativo** o alla **responsabilità dell’utente**.

---

### **8. Gestione della memoria terziaria (4)**

#### **Gestione della memorizzazione gerarchica (HSM – Hierarchical Storage Management)**

Il sistema di **memorizzazione gerarchica** estende la gerarchia di memoria per includere la memoria terziaria.  
Si tratta di una **integrazione automatica** tra i livelli di archiviazione (RAM → disco → jukebox → nastro).

**Principi di funzionamento:**

- I **file piccoli e frequentemente usati** restano su **dischi magnetici** (livello secondario).
    
- I **file grandi, vecchi o raramente usati** vengono **spostati automaticamente** in dispositivi di memoria terziaria (es. jukebox ottici o nastri).

**Vantaggi:**

- Riduzione del costo complessivo di archiviazione.
    
- Gestione trasparente per l’utente.
    
- Ottimizzazione dello spazio su disco e del throughput del sistema.

---

### **9. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Obiettivo**|Gestire grandi volumi di dati a basso costo|
|**Dispositivi principali**|Dischi rimovibili, nastri magnetici, sistemi ottici, olografici e MEMS|
|**Caratteristiche chiave**|Larghezza di banda, latenza, affidabilità, costo|
|**Gestione fisica**|Controllo e sincronizzazione dei supporti|
|**Gestione virtuale**|Integrazione con il file system|
|**Accesso**|Diretto (dischi) o sequenziale (nastri)|
|**Denominazione file**|Necessaria identificazione univoca del supporto|
|**HSM (Hierarchical Storage Management)**|Sposta automaticamente i file meno usati su supporti più lenti e capienti|

---

### **10. Conclusione del modulo**

Con questa lezione si conclude il **Modulo 2 – Gestione dell’ingresso/uscita**.  
Abbiamo esplorato:

- la **struttura dei sottosistemi di I/O**,
    
- la **realizzazione del sottosistema nel kernel**,
    
- la **gestione delle memorie di massa** (dischi e RAID),
    
- e la **memoria terziaria** come estensione della gerarchia.

Queste conoscenze forniscono una visione completa dell’**architettura I/O nei sistemi operativi moderni**, dove l’efficienza, la sicurezza e la scalabilità della gestione dei dati sono elementi chiave per l’intero sistema.