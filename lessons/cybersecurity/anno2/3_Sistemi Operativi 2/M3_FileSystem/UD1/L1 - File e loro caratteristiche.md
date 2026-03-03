# **M3 UD1 Lezione 1 - File e loro caratteristiche**

### **1. Introduzione al File System**

Il **file system** è il componente del sistema operativo che fornisce una **visione logica e astratta** dei dispositivi fisici di memorizzazione, permettendo di organizzare, gestire e trasferire informazioni in modo uniforme e indipendente dall’hardware.

#### **Funzioni principali**

- Astrazione dei dispositivi fisici di memorizzazione e trasferimento.
    
- Visione omogenea dei diversi dispositivi.
    
- Supporto alla **memorizzazione di lungo termine** e al **trasferimento dei dati**.
    
- Gestione di:
    
    - **Informazioni** (dati e programmi) → _file_
        
    - **Metainformazioni** (descrittori, strutture di gestione) → _directory_

Il file system costituisce quindi il **livello logico superiore** della gerarchia di memoria, dove le informazioni vengono organizzate in **file** e **directory** per consentirne l’accesso efficiente e sicuro.

---

### **2. Aggregazione di informazioni**

Le informazioni vengono raggruppate in strutture logiche:

- **Array** → insieme di $( N )$ elementi **omogenei**.
    
- **Record** → insieme di $( K )$ elementi **eterogenei** (di tipi diversi).

I file possono essere visti come **collezioni di elementi omogenei** (dati) o come **insiemi di record** (strutture complesse).

---

### **3. Concetto di File**

Un **file** è una sequenza di elementi **omogenei** (caratteri, byte, record) di **numero non definito a priori**, che rappresenta un’unità logica di informazione.

Ogni file è identificato da un **nome** e gestito dal file system attraverso un **descrittore** che ne registra attributi e posizione.

---

### **4. Tipi di File**

#### **4.1 File di dati**

- **Numerici**
    
- **Alfabetici (character)**
    
- **Binari (byte)**

#### **4.2 File di programmi**

- Contengono istruzioni eseguibili o codice sorgente.

#### **4.3 Classificazioni comuni per estensione**

|Tipo|Estensione|Descrizione|
|---|---|---|
|**Eseguibile**|`.exe`, `.com`, `.bin`|Programma eseguibile|
|**Oggetto**|`.obj`, `.o`|File compilato ma non linkato|
|**Sorgente**|`.c`, `.cc`, `.java`, `.asm`, `.pas`|Codice sorgente|
|**Batch / Script**|`.bat`, `.sh`|Sequenza automatica di comandi|
|**Testo**|`.txt`, `.doc`|File di testo o documento|
|**Elaborazione testi**|`.wp`, `.rtf`, `.tex`|Formati formattati|
|**Libreria**|`.lib`, `.a`, `.so`, `.dll`|Raccolta di moduli o funzioni|
|**Grafico**|`.jpg`, `.gif`, `.png`, `.bmp`, `.ps`, `.pdf`|Immagini e grafici|
|**Archivio**|`.zip`, `.tar`, `.gz`, `.rar`|Dati compressi o archiviati|
|**Multimedia**|`.mp3`, `.mpeg`, `.mov`, `.avi`|Audio e video|

---

### **5. Struttura dei File**

I file possono presentare differenti livelli di organizzazione:

1. **Senza struttura**
    
    - Sequenza di byte o parole senza formattazione (es. file binari).
    
2. **Struttura semplice**
    
    - Sequenza di **linee di testo** a lunghezza fissa o variabile.
    
3. **Struttura complessa**
    
    - Documenti formattati o **file rilocabili/eseguibili**, che includono informazioni aggiuntive per il caricamento e l’uso dinamico.

---

### **6. Attributi dei File**

Ogni file è descritto da un insieme di **attributi** (metadati) memorizzati nella directory o nel descrittore del file.

#### **Principali attributi**

- **Nome** → identificatore simbolico del file.
    
- **Identificatore** → numero univoco interno assegnato dal sistema operativo.
    
- **Tipo** → formato o scopo del file.
    
- **Locazione** → posizione fisica nei dispositivi di memoria.
    
- **Dimensione** → numero totale di byte o record.
    
- **Date** → creazione, ultimo accesso, ultima modifica.
    
- **Proprietario** → utente o processo che detiene i diritti di accesso.
    
- **Protezione** → permessi di lettura, scrittura, esecuzione.
    
- **Formato** → struttura logica dei dati.

L’insieme di questi attributi costituisce il **descrittore del file**.

---

### **7. Operazioni sui File**

Il file system fornisce un insieme di **operazioni fondamentali** che permettono di creare, modificare e gestire i file.

|Operazione|Descrizione|
|---|---|
|**Creazione**|Crea un nuovo file vuoto.|
|**Scrittura**|Inserisce o aggiorna i dati all’interno del file.|
|**Lettura**|Estrae dati dal file.|
|**Riposizionamento (seek)**|Sposta il puntatore all’interno del file.|
|**Cancellazione**|Rimuove definitivamente il file.|
|**Troncamento**|Elimina parte del contenuto, mantenendo il file.|
|**Accodamento**|Aggiunge dati in coda.|
|**Modifica attributi**|Cambia proprietà (es. permessi o proprietario).|
|**Blocco / sblocco**|Gestisce l’accesso concorrente (file sharing).|

---

### **8. Uso dei File**

#### **8.1 Apertura**

Quando un file viene aperto, il sistema operativo:

- verifica i **permessi di accesso**;
    
- individua il **descrittore del file** nel file system;
    
- determina la **locazione fisica** sui dispositivi;
    
- controlla eventuali **condivisioni o lock attivi**;
    
- inizializza le strutture di gestione nella **tabella dei file aperti**.

#### **8.2 Lettura e scrittura**

- Ogni file aperto possiede un **puntatore all’elemento corrente**, che indica la posizione del prossimo byte o record da leggere/scrivere.
    
- Il sistema accede ai dati tramite la **tabella dei file aperti**, che contiene:
    
    - la posizione dei blocchi del file,
        
    - il puntatore di posizione corrente,
        
    - e le informazioni di blocco o accesso condiviso.

#### **8.3 Chiusura**

Alla chiusura del file:

- vengono **aggiornati gli attributi** e le informazioni di gestione;
    
- si **rilasciano eventuali lock** o risorse condivise;
    
- e vengono **rimosse le informazioni temporanee** dalla tabella dei file aperti.

---

### **9. Metodi di accesso**

I file possono essere letti e scritti secondo diverse modalità, a seconda della natura del supporto e delle necessità applicative.

#### **Accesso sequenziale**

- Utilizzato nei dispositivi a **nastro**.
    
- I dati vengono letti in ordine, uno dopo l’altro.
    
- Semplice ma inefficiente per accessi casuali.

#### **Accesso diretto**

- Tipico dei **dischi magnetici o SSD**.
    
- Consente di accedere direttamente a qualsiasi blocco conoscendone l’indice o l’indirizzo.

#### **Accesso indicizzato**

- Si basa su una **struttura di indice** che collega i record alle loro posizioni fisiche.
    
- Consente sia accessi sequenziali che diretti, con efficienza intermedia.

---

### **10. Sintesi finale**

|Concetto|Descrizione|
|---|---|
|**File System**|Fornisce una visione logica e unificata dei dispositivi di memoria|
|**File**|Insieme di dati omogenei gestiti come un’unica unità|
|**Attributi**|Informazioni descrittive sul file|
|**Operazioni**|Creazione, lettura, scrittura, cancellazione, modifica|
|**Uso**|Apertura, accesso tramite tabella, chiusura|
|**Metodi di accesso**|Sequenziale, diretto, indicizzato|

In sintesi, il **file system** è il meccanismo attraverso cui il sistema operativo offre una rappresentazione **astratta, strutturata e sicura dei dati**, consentendo un accesso efficiente e controllato alle risorse permanenti del sistema.