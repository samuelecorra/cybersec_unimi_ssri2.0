# **M3 UD2 Lezione 5 - Valutazione dell’efficienza e delle prestazioni**

### **1. Introduzione**

L’obiettivo di questa lezione è comprendere come valutare e ottimizzare le **prestazioni complessive del file system**, sia in termini di **efficienza** (uso ottimale delle risorse) sia in termini di **rapidità** (tempo di accesso e operazioni di I/O).

L’analisi si articola in due dimensioni principali:

- **Efficienza** → riguarda l’uso dello spazio e delle risorse.
    
- **Prestazioni** → riguarda la velocità di utilizzo e la reattività del sistema.

---

### **2. Efficienza del file system**

#### **2.1 Definizione**

L’efficienza misura la capacità del file system di **sfruttare al meglio le risorse disponibili**, minimizzando gli sprechi di spazio e di tempo.

#### **2.2 Aspetti principali**

1. **Dimensionamento delle strutture**
    
    - **Blocchi:** la scelta della dimensione dei blocchi influenza lo spazio e la velocità di accesso.
        
    - **Puntatori:** devono essere abbastanza grandi da indirizzare tutti i blocchi del disco.
        
    - **Metadati:** la loro struttura deve essere proporzionata alla dimensione e al numero dei file.
    
2. **Rendimento dello spazio**
    
    - **Frammentazione interna:** spazio inutilizzato all’interno dei blocchi allocati.
        
    - **Frammentazione esterna:** blocchi liberi non contigui che impediscono nuove allocazioni ottimali.
        
    - **Aree dedicate alla gestione:** porzioni del disco riservate a strutture di controllo (directory, tabelle FAT, i-node, bitmap, ecc.).

---

### **3. Tecniche per migliorare l’efficienza**

Per migliorare l’efficienza complessiva, si adottano **strategie di configurazione e ottimizzazione strutturale**.

#### **3.1 Cluster di dimensioni variabili**

- Raggruppano più blocchi fisici in un’unica unità logica (**cluster**).
    
- Consentono un buon compromesso tra velocità di accesso e sfruttamento dello spazio.

#### **3.2 Definizione ottimale dei parametri**

- La scelta delle **dimensioni di blocchi, puntatori e metadati** deve tener conto della **tecnologia hardware** e delle **modalità d’uso del sistema** (frequenza di lettura/scrittura, dimensione media dei file, ecc.).

#### **3.3 Esempi**

- **FAT10, FAT16, FAT32:** varianti che differiscono per la **lunghezza dei puntatori** e quindi per il **numero massimo di blocchi indirizzabili**.
    
- **Allocazione statica o dinamica** delle tabelle in memoria:
    
    - Statica → tabelle precaricate in RAM (prestazioni più elevate, consumo di memoria fisso).
        
    - Dinamica → caricamento su richiesta (maggior flessibilità, ma prestazioni inferiori).

---

### **4. Prestazioni del file system**

#### **4.1 Definizione**

Le prestazioni indicano la **rapidità di uso delle risorse**, ovvero la capacità del file system di garantire **tempi di accesso ridotti** e **elevato throughput** nelle operazioni di I/O.

#### **4.2 Fattori principali**

- **Modo di utilizzo delle risorse** (accessi sequenziali o casuali).
    
- **Strutture dati di supporto** (tabelle, cache, buffer).
    
- **Supporti hardware** (memoria centrale, dischi, controller, bus, ecc.).

---

### **5. Tecniche per migliorare le prestazioni**

#### **5.1 Algoritmi e strutture dati efficienti**

- Utilizzare **algoritmi semplici** ma ottimizzati per le operazioni più frequenti (lookup, allocazione, journaling).
    
- Predisporre **strutture dati ad accesso diretto o indicizzato** (hash table, B-tree, i-node).

---

#### **5.2 Supporti hardware dedicati**

L’hardware può migliorare significativamente la rapidità di accesso ai dati.

- **Cache del disco:** memoria intermedia tra il file system e il dispositivo fisico, riduce gli accessi diretti.
    
- **Cache delle pagine:** memorizza blocchi di dati recentemente usati, migliorando la località temporale.
    
- **Buffer cache unificata:** combina la cache del disco e quella della memoria virtuale per evitare duplicazioni.
    
- **Memoria virtuale unificata:** consente di trattare una porzione di RAM come disco virtuale.

---

#### **5.3 Doppia cache e buffer unificato**

- L’uso di una **doppia cache** consente di ottimizzare sia le operazioni di I/O sia quelle di memoria.
    
- La **buffer cache unificata** riduce il numero di copie e sincronizzazioni tra livelli diversi di memoria.

---

#### **5.4 Gestione della cache**

Una cache efficiente deve essere gestita da **algoritmi di sostituzione** e **priorità di paginazione**.

- **Algoritmo LRU (Least Recently Used):** elimina il blocco meno recentemente utilizzato.
    
- **Priorità di paginazione:** alcuni blocchi (es. metadati) possono avere priorità di permanenza maggiore.

---

#### **5.5 Ottimizzazioni I/O**

- **I/O mediato da cache:** riduce i tempi di latenza raggruppando le operazioni.
    
- **Minimizzazione dello spostamento della testina:** ottimizza le richieste di accesso al disco.
    
- **Scritture asincrone:** le scritture vengono posticipate, accumulando più modifiche prima del commit.
    
- **Alternative a LRU:**
    
    - **Free-behind:** elimina i blocchi appena letti.
        
    - **Read-ahead:** pre-carica i blocchi che si prevede verranno letti successivamente.

---

#### **5.6 Memoria virtuale unificata e RAM-disk**

- La **memoria virtuale unificata** permette di usare porzioni della RAM come se fossero disco (**RAM-disk**).
    
- Migliora drasticamente le prestazioni per file temporanei o cache di sistema, a scapito della volatilità dei dati.

---

### **6. Sintesi finale**

|Categoria|Obiettivo|Tecniche principali|
|---|---|---|
|**Efficienza**|Massimizzare l’uso delle risorse|Dimensionamento ottimale di blocchi, puntatori, metadati; cluster variabili; gestione frammentazione|
|**Prestazioni**|Massimizzare la velocità di accesso|Cache disco/pagine, buffer unificato, scritture asincrone, RAM-disk, algoritmi LRU/read-ahead|

---

### **7. Conclusione**

L’efficienza e le prestazioni di un file system dipendono dalla **scelta accurata degli algoritmi, delle strutture dati e dei componenti hardware**.  
Un sistema ben progettato deve:

- sfruttare al massimo lo spazio disponibile,
    
- ridurre gli sprechi e le latenze,
    
- e adattarsi alle caratteristiche del carico di lavoro e della tecnologia di memorizzazione.