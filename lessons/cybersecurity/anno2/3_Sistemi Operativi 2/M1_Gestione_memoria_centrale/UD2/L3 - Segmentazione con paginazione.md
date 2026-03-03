# **M1 UD2 Lezione 3 - Segmentazione con paginazione**

### **1. Introduzione**

La **segmentazione con paginazione** combina i vantaggi delle due tecniche precedenti — **segmentazione** e **paginazione** — eliminandone i rispettivi limiti.  
In particolare, conserva la **tipizzazione logica** propria della segmentazione, ma sfrutta la **gestione efficiente e priva di frammentazione** della paginazione.

L’obiettivo è quindi fornire un modello di memoria **logicamente strutturato** ma **fisicamente regolare e compatto**, evitando sprechi di spazio.

---

### **2. Problema di partenza**

La segmentazione, pur offrendo una visione logica coerente con la struttura del programma, introduce il problema della **frammentazione esterna**: i segmenti hanno dimensione variabile e non sempre riempiono perfettamente i frame di memoria fisica.

#### **Obiettivi**

- Mantenere i vantaggi della segmentazione:  
    → tipizzazione, protezione e condivisione logica.
    
- Evitare la frammentazione esterna, grazie alla divisione in pagine di dimensione fissa.

---

### **3. Principio della segmentazione con paginazione**

La tecnica fonde le caratteristiche di entrambe:

#### **a. Dalla paginazione eredita:**

- la **semplicità** di gestione della memoria;
    
- la **rapidità** nel trovare frame liberi;
    
- l’assenza di **frammentazione esterna**.

#### **b. Dalla segmentazione eredita:**

- la **tipizzazione logica** delle sezioni di memoria;
    
- il controllo degli **accessi e delle operazioni consentite**;
    
- la possibilità di **condividere porzioni di memoria** tra processi.

---

### **4. Struttura logico-fisica**

- La **memoria fisica** è divisa in **pagine fisiche (frame)**.
    
- Lo **spazio logico del processo** è diviso in **segmenti logici**, e **ogni segmento è ulteriormente suddiviso in pagine logiche**.

#### **Caratteristiche**

- I **segmenti** contengono dati o codice di **diverso tipo** (tipizzazione).
    
- Le **pagine** di un segmento rappresentano porzioni omogenee dello spazio logico interno a quel segmento.
    
- I segmenti possono avere **dimensioni diverse**, ma le pagine sono **tutte della stessa dimensione**, così come i frame fisici.
    
- **Ogni pagina logica di un segmento** è caricata in **un frame fisico**.

---

### **5. Traduzione degli indirizzi**

#### **Indirizzo logico**

È composto da tre campi:

$$  
\text{Indirizzo logico} = (s, p, d)  
$$

dove:

- ( s ) = numero del segmento,
    
- ( p ) = numero della pagina all’interno del segmento,
    
- ( d ) = spiazzamento (offset) all’interno della pagina.

#### **Indirizzo fisico**

L’indirizzo fisico generato dalla MMU è:

$$  
\text{Indirizzo fisico} = (f, d)  
$$

dove ( f ) è il numero del **frame fisico** che contiene la pagina richiesta.

In questo modo, la traduzione avviene in due passaggi:

1. dalla **tabella dei segmenti** alla **tabella delle pagine del segmento**;
    
2. dalla **tabella delle pagine** al **frame fisico** effettivo.

---

### **6. Esempio pratico**

Nel sistema **Intel 80x86**, la segmentazione con paginazione è implementata nativamente:

- ogni processo possiede più **segmenti logici**, ognuno dei quali è **paginato internamente**;
    
- la **MMU** effettua automaticamente la doppia traduzione (segmento → pagina → frame).

Questo modello fornisce **flessibilità logica** e **efficienza fisica**, risultando alla base dei sistemi operativi moderni (Windows, Linux, macOS).

---

### **7. Gestione della segmentazione con paginazione**

#### **(1) Caricamento**

Le pagine necessarie nell’immediato futuro vengono caricate in memoria centrale.  
Le pagine appartenenti a un segmento possono trovarsi in **frame non contigui**, esattamente come nella paginazione.

#### **(2) Area di swap**

Le pagine non caricate risiedono in **memoria secondaria**.  
Quando un frame modificato deve essere liberato, viene **salvato su disco** prima della sostituzione.

#### **(3) Ruolo del programmatore**

Il programmatore non gestisce esplicitamente la memoria, ma definisce implicitamente la struttura in segmenti attraverso:

- la suddivisione del codice in moduli,
    
- l’uso di librerie,
    
- la separazione di stack, heap e dati globali.

Questa organizzazione permette al sistema operativo di costruire automaticamente la corrispondente struttura segmentata e paginata.

#### **(4) Gestione del sistema operativo**

Il sistema operativo gestisce:

- la selezione delle pagine da caricare,
    
- il caricamento delle pagine mancanti,
    
- la scelta dei frame da liberare,
    
- lo scaricamento dei frame non più necessari.

Tutto il processo è **automatico e trasparente** per l’utente.

---

### **8. Supporto hardware: MMU**

La **Memory Management Unit** fornisce supporto specifico alla segmentazione con paginazione:

- effettua la **doppia traduzione** (segmento → pagina → frame);
    
- mantiene o punta alle **tabelle dei segmenti e delle pagine**;
    
- garantisce il controllo degli accessi, i permessi e la protezione dei segmenti;
    
- gestisce i **page fault**, segnalando al sistema operativo quando è necessario caricare una pagina non presente.

---

### **9. Sintesi finale**

- La **segmentazione con paginazione** integra i vantaggi delle due tecniche precedenti:  
    → tipizzazione logica (segmentazione)  
    → efficienza e assenza di frammentazione (paginazione).
    
- È **configurata implicitamente** dal programmatore e **gestita automaticamente** dal sistema operativo.
    
- Permette di creare **spazi logici più grandi** della memoria fisica reale.
    
- È **efficiente**, poiché muove solo piccole porzioni di memoria (pagine).
    
- **Elimina la frammentazione** mantenendo la coerenza semantica tra i segmenti del programma.