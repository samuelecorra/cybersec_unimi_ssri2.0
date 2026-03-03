# **M2 UD2 Lezione 2 - Organizzazione del disco**

### **1. Introduzione**

La **configurazione e organizzazione del disco** rappresentano un passaggio fondamentale per rendere una memoria di massa utilizzabile dal sistema operativo.  
Ogni disco deve essere inizializzato, partizionato e formattato prima di poter ospitare un file system o altre strutture logiche.  
Inoltre, è necessario prevedere meccanismi di **gestione dei blocchi difettosi** e un’**area di swap** per il supporto alla multiprogrammazione.

---

### **2. Configurazione del disco**

La configurazione di un disco avviene in tre fasi principali:

#### **a. Formattazione fisica (a basso livello)**

Questa operazione suddivide il disco in **settori** che il **controller** può leggere e scrivere.

Ogni settore è composto da:

- **Header:** contiene identificativi e informazioni di controllo.
    
- **Zona dati:** contiene il contenuto effettivo del settore.
    
- **Terminatore:** indica la fine del settore.

Per garantire l’affidabilità, ogni settore include un **checksum** usato per la **rilevazione e correzione degli errori**.

---

#### **b. Partizionamento**

Il **partizionamento** divide il disco in più aree indipendenti, ognuna delle quali può essere gestita come se fosse un **disco separato**.

- Ogni **partizione** può contenere un proprio file system o essere destinata ad altri scopi (es. area di swap).
    
- Un disco può essere gestito anche come **unica partizione** nel caso di sistemi semplici.

---

#### **c. Formattazione logica (ad alto livello)**

La formattazione logica costruisce le **strutture dati del file system** all’interno di una partizione.  
In questa fase vengono creati:

- la **tabella di allocazione dei file**,
    
- la **struttura delle directory**,
    
- e lo **spazio riservato per i metadati**.

In alternativa, una partizione può essere utilizzata come **disco grezzo (raw disk)**, cioè accessibile direttamente senza file system.  
Questa modalità è tipica per **database** o **aree di swap dedicate**, dove si preferisce un accesso più rapido e privo di overhead.

---

### **3. Il blocco di avvio (Boot Block)**

Il **blocco di avvio** è un’area del disco che contiene **il codice necessario per l’avvio del sistema operativo** o di una sua parte.

- Nei sistemi tradizionali, è il **primo settore** del disco (Master Boot Record – MBR).
    
- Nei sistemi moderni (UEFI), è sostituito dalla **tabella GPT** e da una partizione di boot separata.

La presenza del boot block consente al sistema di **caricare il kernel in memoria** e avviare la fase di inizializzazione del sistema operativo.

---

### **4. Blocchi difettosi (Bad Blocks)**

I **blocchi difettosi** sono settori del disco che risultano **illeggibili o danneggiati**.  
Possono comparire già durante la produzione o a seguito dell’usura del supporto.

#### **Gestione manuale**

Durante la **formattazione logica**, l’amministratore può **escludere manualmente** i blocchi difettosi, marcandoli come inutilizzabili.

#### **Gestione automatica**

I moderni dischi implementano tecniche di **rimappatura automatica** dei settori danneggiati, tra cui:

- **Sector sparing:** il blocco difettoso viene sostituito con un settore di riserva.
    
- **Sector forwarding:** il controller devia le richieste verso un settore alternativo.
    
- **Sector slipping:** i blocchi successivi vengono spostati per compensare il settore guasto.

Queste soluzioni sono gestite a livello di **firmware del disco**, e risultano trasparenti al sistema operativo.

---

### **5. Uso dell’area di swap**

L’**area di swap** è uno spazio su disco utilizzato dal sistema operativo per **gestire l’eccesso di memoria** quando la RAM non è sufficiente.

#### **Funzione principale**

Supporta i meccanismi di:

- **Multiprogrammazione**, consentendo a più processi di coesistere in memoria virtuale.
    
- **Multitasking**, permettendo la sospensione e ripresa dei processi.

#### **Contenuto dell’area di swap**

A seconda del tipo di gestione della memoria:

- Può contenere **interi processi** (nei sistemi con solo swapping).
    
- Può contenere **pagine** di processo (nei sistemi con paginazione).
    
- Può contenere **segmenti** di processo (nei sistemi con segmentazione).

#### **Posizione**

L’area di swap può essere:

- una **partizione dedicata**, gestita come **disco grezzo (raw)**;
    
- oppure un **file speciale** all’interno del file system, più flessibile ma leggermente meno efficiente.

---

### **6. Strutturazione dell’area di swap**

L’area di swap è suddivisa in **blocchi** e **mappe di allocazione**.

- La **mappa di swap** definisce l’ordine e la posizione dei blocchi assegnati a ciascun processo, costruendo lo **spazio di indirizzamento virtuale**.
    
- I **blocchi di swap** rappresentano le unità effettive di memoria secondaria riservate ai processi.

Un’implementazione efficiente della mappa di swap è essenziale per ridurre la frammentazione e ottimizzare i tempi di scambio con la memoria principale.

---

### **7. Sintesi finale**

|Concetto|Descrizione|
|---|---|
|**Formattazione fisica**|Divide il disco in settori gestibili dal controller|
|**Partizionamento**|Suddivide il disco in aree logiche indipendenti|
|**Formattazione logica**|Crea le strutture del file system o usa il disco in modalità raw|
|**Blocco di avvio**|Contiene il codice per l’avvio del sistema operativo|
|**Blocchi difettosi**|Gestiti manualmente o automaticamente (sparing, forwarding, slipping)|
|**Area di swap**|Spazio disco per pagine, segmenti o interi processi|
|**Mappa di swap**|Definisce la corrispondenza tra memoria virtuale e blocchi fisici|

In sintesi, la corretta **organizzazione del disco** è il prerequisito per garantire **affidabilità, efficienza e continuità operativa** del sistema di memorizzazione.