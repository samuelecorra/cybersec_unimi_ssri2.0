# **M3 UD2 Lezione 1 - Struttura e realizzazione del file system**

### **1. Obiettivo del file system**

Il **file system** ha come obiettivo principale quello di **gestire in modo omogeneo le risorse informative e fisiche** del sistema di elaborazione, trattandole come **flussi di informazioni elementari**.

In particolare, si concentra sulla gestione delle **informazioni memorizzate nelle memorie secondarie**, ossia le **memorie di massa (dischi)**, trasformando il livello fisico di memorizzazione in una **visione logica strutturata** basata sui file.

#### **Caratteristiche fondamentali**

- Le memorie secondarie sono **dispositivi di memorizzazione a blocchi**.
    
- Ciascun disco può essere suddiviso in **partizioni**, ognuna delle quali può ospitare un file system indipendente.

---

### **2. Visione utente e visione fisica**

Dal punto di vista dell’utente:

- un **file** è un **flusso di elementi omogenei** (byte, record, caratteri).

Dal punto di vista del sistema operativo:

- i dati sono memorizzati in **blocchi fisici** di dimensione fissa all’interno delle memorie di massa.

Il compito del file system è quindi quello di **trasformare la rappresentazione fisica in una rappresentazione logica**, fornendo un’interfaccia astratta e uniforme.

#### **Due principi chiave**

1. **Astrazione della rappresentazione fisica** → nasconde la complessità del dispositivo.
    
2. **Virtualizzazione delle informazioni** → consente l’accesso ai file come entità logiche indipendenti dall’hardware sottostante.

---

### **3. Struttura della gestione del file system**

La gestione del file system è organizzata in **livelli gerarchici**, ciascuno con responsabilità specifiche:

#### **3.1 Gestione della periferica**

- Si occupa della **comunicazione diretta con i dispositivi di memoria**.
    
- Include sia la gestione **dipendente** dal dispositivo (driver specifici), sia quella **indipendente** (interfaccia generale verso il sistema operativo).

#### **3.2 File system di base**

- Gestisce le operazioni di **lettura e scrittura dei blocchi fisici** sul disco.
    
- È responsabile della **coerenza tra livello logico e livello fisico**.

#### **3.3 Modulo di organizzazione dei file**

- Costruisce la **sequenza di blocchi fisici** che compongono ciascun file.
    
- Gestisce lo **spazio libero** (allocazione e deallocazione dei blocchi).

#### **3.4 File system logico**

- Gestisce i **metadati** che definiscono la struttura logica del file system (directory, descrittori, identificatori).
    
- Si occupa dell’**identificazione, protezione e controllo degli accessi** ai file.

---

### **4. Strutture dati per la gestione del file system**

Le strutture dati utilizzate dal file system si distinguono in **strutture su disco** e **strutture in memoria centrale**.

---

#### **4.1 Strutture su disco**

Sono persistenti e memorizzate direttamente nella memoria secondaria.

|Struttura|Descrizione|
|---|---|
|**Blocco di controllo del boot**|Contiene le informazioni necessarie per avviare il sistema operativo.|
|**Blocco di controllo della partizione**|Definisce i limiti e la struttura logica della partizione.|
|**Directory**|Struttura che descrive l’organizzazione gerarchica dei file.|
|**Blocchi di controllo dei file**|Contengono i metadati associati a ciascun file (dimensione, posizione, permessi, ecc.).|

---

#### **4.2 Strutture in memoria centrale**

Sono create o mantenute dal sistema operativo durante l’esecuzione, per migliorare le prestazioni e ridurre gli accessi al disco.

|Struttura|Funzione|
|---|---|
|**Tabella delle partizioni**|Elenca tutte le partizioni disponibili e i relativi parametri.|
|**Descrittori delle directory**|Memorizzano le informazioni operative sulle directory attive.|
|**Tabella dei file aperti del sistema**|Contiene i riferimenti a tutti i file attualmente aperti a livello globale.|
|**Tabella dei file aperti per processo**|Mantiene i puntatori ai file aperti dal singolo processo.|
|**Tabella di montaggio dei file system**|Registra i file system attualmente montati e i loro punti di montaggio.|

---

### **5. Strutture del file system in memoria**

Quando un file viene **aperto**, il sistema operativo:

1. Carica nella memoria centrale le informazioni necessarie dalla directory o dai blocchi di controllo del file.
    
2. Aggiorna le **tabelle dei file aperti** del sistema e del processo.
    
3. Inizializza i **puntatori di posizione corrente** per la lettura o scrittura.

Durante la **lettura o scrittura**, vengono aggiornate dinamicamente:

- le informazioni sui **blocchi fisici coinvolti**,
    
- e gli **indicatori di stato** (offset, accessi, lock, ecc.).

---

### **6. File system virtuali**

Il concetto di **file system virtuale (VFS)** introduce un **livello di astrazione ulteriore**, che consente al sistema operativo di:

- **uniformare l’accesso** a diversi tipi di file system (es. FAT, NTFS, ext4, NFS, ecc.);
    
- **gestire più file system eterogenei** in modo trasparente;
    
- offrire un’unica interfaccia standard per tutte le operazioni sui file.

Il VFS funge quindi da **strato intermedio** tra le chiamate di sistema (API) e le implementazioni specifiche dei vari file system.

---

### **7. Sintesi finale**

|Livello|Funzione principale|
|---|---|
|**Gestione della periferica**|Interazione con i dispositivi fisici di memoria|
|**File system di base**|Lettura e scrittura dei blocchi fisici|
|**Organizzazione dei file**|Sequenza dei blocchi e gestione dello spazio libero|
|**File system logico**|Gestione dei metadati e identificazione dei file|
|**VFS**|Interfaccia unificata per diversi file system|

---

### **8. Conclusione**

La **struttura del file system** è concepita in modo multilivello per separare la **gestione fisica** dei dispositivi dalla **visione logica** offerta all’utente.  
Grazie alle **strutture dati su disco e in memoria**, e all’uso del **file system virtuale**, il sistema operativo riesce a garantire **efficienza, flessibilità e portabilità** nella gestione dei dati.