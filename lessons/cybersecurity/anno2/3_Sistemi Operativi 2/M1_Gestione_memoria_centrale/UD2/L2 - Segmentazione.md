# **M1 UD2 Lezione 2 - Segmentazione**

### **1. Introduzione**

La **segmentazione** è una tecnica di gestione della memoria centrale che nasce per superare i limiti concettuali della **paginazione**.  
Mentre la paginazione divide la memoria in blocchi uniformi e anonimi, la segmentazione suddivide lo spazio logico del processo in **unità semantiche**, coerenti con la struttura del programma (come codice, dati, stack, heap, ecc.).

L’obiettivo è fornire una **visione logica più vicina al programmatore**, consentendo la **tipizzazione**, la **protezione** e la **condivisione** delle diverse aree di memoria.

---

### **2. Problemi di partenza**

La segmentazione affronta gli stessi problemi della paginazione — l’inefficienza del caricamento e la necessità di gestire la memoria in modo dinamico — ma aggiunge un ulteriore livello di astrazione.

Nella paginazione:

- lo spazio logico è visto come un **unico vettore di indirizzi** omogenei;
    
- non è possibile distinguere semanticamente le varie porzioni (codice, dati, stack);
    
- la condivisione di codice tra processi è complessa, poiché tutto è gestito come blocchi numerici anonimi.

---

### **3. Obiettivi della segmentazione**

#### **(1) Stessi obiettivi della paginazione**

- Caricare solo le parti necessarie di un processo;
    
- Minimizzare l’occupazione di memoria;
    
- Mantenere la gestione automatica e trasparente.

La differenza è che, qui, **le porzioni non hanno dimensione fissa**.

#### **(2) Visione utente e tipizzazione**

- Offrire una visione **più naturale e semantica** dello spazio di indirizzamento, dal punto di vista del programmatore.
    
- Associare **un significato logico** a ciascuna sezione di memoria (segmento codice, dati globali, stack, heap...).
    
- Permettere il **controllo degli accessi e delle operazioni** in base al tipo di segmento.

#### **(3) Condivisione**

- Supportare la **condivisione efficiente** di porzioni di memoria tra più processi, come nel caso di moduli o librerie comuni.

---

### **4. Concetto di segmentazione**

#### **Struttura logica e fisica**

- La **memoria fisica** è suddivisa in **segmenti fisici (frame)**: una struttura **monodimensionale**.
    
- Lo **spazio logico** del processo è diviso in **segmenti logici (segmenti)**: una struttura **bidimensionale**, dove ogni segmento rappresenta un’unità funzionale distinta del programma.

#### **Caratteristiche**

- Ogni segmento contiene dati o codice di **tipo specifico**.
    
- I segmenti possono avere **dimensioni diverse**.
    
- Ogni segmento logico è caricato in un frame fisico **di dimensione adeguata**.

---

### **5. Tabella dei segmenti**

Per ogni processo esiste una **Tabella dei Segmenti**, che definisce la corrispondenza tra i segmenti logici e le loro posizioni fisiche in memoria.

$$  
\text{TabellaSegmenti[Segmento]} =  
\begin{cases}  
(\text{IndirizzoBaseFrame}, \text{DimensioneSegmento}), & \text{se caricato} \\\\  
\text{---}, & \text{se non caricato}  
\end{cases}  
$$

#### **Indirizzi logici e fisici**

Ogni indirizzo logico generato dal processo è formato da:

$$  
\text{Indirizzo logico} = (s, d)  
$$

dove

- ( s ) = numero del segmento logico,
    
- ( d ) = spiazzamento (offset) all’interno del segmento.

La **MMU** traduce l’indirizzo logico nell’indirizzo fisico:

$$  
\text{Indirizzo fisico} = (\text{BaseFrame}_s + d)  
$$

In pratica, all’indirizzo base del frame del segmento viene sommato lo spiazzamento richiesto.

---

### **6. Gestione della segmentazione**

#### **(1) Caricamento**

I segmenti necessari per la prossima fase di computazione dei processi nello stato di _ready_ vengono **caricati in memoria centrale**.  
Essi possono essere collocati in **frame non contigui**, migliorando la flessibilità della gestione.

#### **(2) Memoria secondaria**

I segmenti non caricati risiedono nell’**area di swap**.  
Quando un frame deve essere liberato, le sue modifiche vengono **salvate su disco** prima della rimozione.

#### **(3) Ruolo del programmatore**

La suddivisione di un programma in segmenti è **implicita nella sua struttura**:

- ogni **modulo compilato separatamente** diventa un segmento;
    
- il **compilatore e il linker** generano automaticamente segmenti dedicati a:
    
    - codice del modulo,
        
    - dati globali,
        
    - tabella dei simboli,
        
    - stack,
        
    - heap.

Il programmatore non gestisce esplicitamente la memoria, ma la segmentazione riflette la struttura logica del suo programma.

#### **(4) Gestione automatica del S.O.**

Il sistema operativo si occupa di:

- scegliere quali segmenti caricare,
    
- caricare i segmenti mancanti in memoria,
    
- selezionare i frame da liberare,
    
- scaricare i segmenti non più necessari.

Tutte queste operazioni sono **trasparenti per l’utente**.

---

### **7. Supporto hardware: MMU**

Come per la paginazione, la **Memory Management Unit (MMU)** fornisce supporto hardware per la segmentazione:

- contiene la **tabella dei segmenti** o un **puntatore** al suo indirizzo in RAM;
    
- traduce in tempo reale gli **indirizzi logici** in **indirizzi fisici**;
    
- rileva eventuali **violazioni di accesso** (ad esempio spiazzamento oltre i limiti del segmento).

La MMU garantisce quindi la **sicurezza e l’integrità** dello spazio di memoria dei processi.

---

### **8. Protezione e condivisione dei segmenti**

#### **Protezione**

- Ogni processo può accedere **solo ai propri segmenti**, e tale protezione è **implicita nella tabella dei segmenti**.
    
- Ogni voce può includere **bit di protezione** che specificano i permessi:

|Tipo di accesso|Descrizione|
|---|---|
|**Lettura/Scrittura**|Il segmento può essere letto e modificato.|
|**Sola Lettura**|Il segmento è accessibile solo in lettura.|
|**Sola Esecuzione**|Il segmento contiene codice eseguibile non modificabile.|

#### **Condivisione**

È possibile condividere segmenti tra più processi — ad esempio librerie o moduli di codice — garantendo così **risparmio di memoria** e **consistenza del codice condiviso**.

---

### **9. Frammentazione della memoria**

Poiché i segmenti possono avere **dimensioni variabili**, può verificarsi **frammentazione esterna**:

- quando un segmento viene caricato, deve occupare un frame sufficientemente grande;
    
- eventuali porzioni di spazio inutilizzate nel frame costituiscono **sfridi di memoria**.

#### **Soluzione**

Il sistema operativo può eseguire una **garbage collection della memoria**, cioè un processo di **compattazione** che unisce gli spazi liberi e, se necessario, **riloca i segmenti caricati** per ottenere frame contigui più ampi.

---

### **10. Sintesi finale**

- La **segmentazione** suddivide la memoria in **unità logiche di tipo diverso**, in contrasto con la paginazione che usa blocchi uniformi.
    
- È configurata **implicitamente dal programmatore** attraverso la struttura del codice.
    
- È **gestita automaticamente** dal sistema operativo e supportata dalla **MMU**.
    
- Permette di associare **tipi, permessi e funzioni** ai diversi segmenti, abilitando protezione e condivisione.
    
- Introduce però **frammentazione esterna**, mitigabile tramite compattazione.
    
- Come la paginazione, consente a ogni processo di percepire uno **spazio logico più grande** della memoria fisica realmente disponibile.