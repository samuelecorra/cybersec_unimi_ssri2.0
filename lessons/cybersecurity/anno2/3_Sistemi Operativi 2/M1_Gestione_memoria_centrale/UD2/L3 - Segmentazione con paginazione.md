# **M1 UD2 Lezione 3 - Segmentazione con paginazione**

---

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

La tecnica fonde le caratteristiche di entrambe, sfruttando il meglio di ciascuna:

#### **a. Dalla paginazione eredita:**

- la **gestione semplice ed efficiente** della memoria;
- la **facile individuazione dei frame liberi** in memoria centrale;
- la **libertà nella scelta del frame** in cui caricare una pagina (qualsiasi frame libero va bene, essendo tutti uguali);
- l'**assenza nativa di frammentazione esterna**.

#### **b. Dalla segmentazione eredita:**

- la **tipizzazione logica** delle sezioni di memoria;
- il **controllo degli accessi** e della **liceità delle operazioni** in base al tipo del segmento;
- la possibilità di **condividere porzioni di memoria** tra processi in modo semplice ed efficiente.

---

### **4. Struttura logico-fisica**

- La **memoria fisica** è divisa in **pagine fisiche (frame)**, tutte di **uguale dimensione**.
- Lo **spazio logico del processo** è considerato uno spazio **bidimensionale** diviso in **segmenti logici** (ciascuno tipizzato).
- **Ogni segmento è ulteriormente suddiviso in pagine logiche** tutte della stessa dimensione, che coincide con quella delle pagine fisiche.

#### **Caratteristiche**

- I **segmenti** contengono dati o codice di **diverso tipo** (tipizzazione).
- Le **pagine** di un segmento rappresentano porzioni omogenee dello spazio logico interno a quel segmento.
- I segmenti possono avere **dimensioni diverse**, ma le pagine sono **tutte della stessa dimensione**, così come i frame fisici.
- **Ogni pagina logica di un segmento** è caricata in **un frame fisico**.

#### **4.1. Differenza chiave rispetto alla segmentazione pura**

Nella segmentazione con paginazione **non viene più caricato l'intero segmento** in memoria centrale: viene caricata **solo la singola pagina del segmento che serve**, ponendola in una pagina fisica disponibile.

Poiché la pagina logica ha **esattamente la stessa dimensione** della pagina fisica, l'operazione di caricamento è:

- **molto più rapida ed efficiente** rispetto al caso della segmentazione pura (dove si dovevano spostare segmenti interi, potenzialmente grandi);
- **priva di sprechi**: nessuno spazio inutilizzato rimane nel frame, perché pagina logica e fisica coincidono perfettamente.

---

### **5. Traduzione degli indirizzi**

#### **Indirizzo logico**

È composto da tre campi:

$$  
\text{Indirizzo logico} = (s, p, d)  
$$

dove:

- $s$ = numero del segmento (talvolta chiamato **selettore**),
- $p$ = numero della pagina all'interno del segmento,
- $d$ = spiazzamento (offset) all'interno della pagina.

#### **Indirizzo fisico**

L'indirizzo fisico generato dalla MMU è:

$$  
\text{Indirizzo fisico} = (f, d)  
$$

dove $f$ è il numero del **frame fisico** che contiene la pagina richiesta.

#### **Schema completo della doppia traduzione**

La traduzione avviene in **due passaggi** ben distinti:

![](imgs/Pasted%20image%2020260531232511.png)

$$
\begin{array}{c}
\text{CPU genera indirizzo logico } (s, p, d) \\\\
\downarrow \\\\
\text{Passo 1: si usa } s \text{ come selettore nella tabella dei segmenti} \\\\
\downarrow \\\\
\text{Si ottiene il riferimento alla tabella delle pagine specifica del segmento } s \\\\
\downarrow \\\\
\text{Passo 2: si usa } p \text{ come indice nella tabella delle pagine del segmento} \\\\
\downarrow \\\\
\text{Si ottiene il numero di frame fisico } f \\\\
\downarrow \\\\
\text{Indirizzo fisico: } (f, d) \;\longrightarrow\; \text{accesso alla cella di memoria}
\end{array}
$$

In sintesi:

1. **Selettore → Tabella dei segmenti**: il sistema mantiene **l'insieme di tutte le tabelle dei segmenti** caricate; il selettore individua quella del segmento desiderato.
2. **Tabella delle pagine del segmento**: ogni segmento ha la **propria tabella delle pagine** (a differenza della paginazione pura dove ce n'è una sola per processo); essa mappa le pagine logiche del segmento sui frame fisici della RAM.
3. **Combinazione con lo spiazzamento**: il frame $f$ e lo spiazzamento $d$ vengono uniti per generare l'indirizzo fisico effettivo della cella.

---

### **6. Esempio pratico**

Nel sistema **Intel 80x86**, la segmentazione con paginazione è implementata nativamente:

- ogni processo possiede più **segmenti logici**, ognuno dei quali è **paginato internamente**;
    
- la **MMU** effettua automaticamente la doppia traduzione (segmento → pagina → frame).

Questo modello fornisce **flessibilità logica** e **efficienza fisica**, risultando alla base dei sistemi operativi moderni (Windows, Linux, macOS).

---

### **7. Gestione della segmentazione con paginazione**

#### **(1) Caricamento**

In memoria centrale vengono caricate **solo le pagine necessarie nell'immediato futuro** per i processi nello stato di **pronto**.

> 📌 **Differenza chiave rispetto alla segmentazione pura**: nella segmentazione pura si caricava sempre l'intero segmento; qui invece **solo le pagine di ciascun segmento che effettivamente servono** vengono caricate — un'evoluzione che incrementa drasticamente l'efficienza dell'uso della memoria centrale.

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
- garantisce il controllo degli accessi, i permessi e la protezione dei segmenti.

#### **Due tipi di trap distinte**

La MMU genera due trap diverse a seconda del tipo di errore:

$$
\begin{cases}
\textbf{Page fault:} & \text{la \textbf{pagina} richiesta non è caricata in memoria centrale.} \\\\
& \text{Il SO risponde caricando la pagina mancante dall'area di swap,} \\\\
& \text{poi l'operazione che era fallita viene completata. } \\\\
\textbf{Segmentation violation:} & \text{si accede a un indirizzo \textbf{fuori dai limiti} del segmento} \\\\
& \text{(violazione dello spazio consentito). Errore di programmazione:} \\\\
& \text{tipicamente il processo viene terminato dal SO.}
\end{cases}
$$

Le due trap riflettono la **duplice natura** della tecnica: il **page fault** è ereditato dalla paginazione, la **segmentation violation** dalla segmentazione.

---

### **9. Sintesi finale**

- La **segmentazione con paginazione** integra i vantaggi delle due tecniche precedenti:  
    → tipizzazione logica (segmentazione)  
    → efficienza e assenza di frammentazione (paginazione).
- È **configurata implicitamente** dal programmatore e **gestita automaticamente** dal sistema operativo.
- Permette di creare **spazi logici più grandi** della memoria fisica reale.
- È **efficiente**, poiché muove solo piccole porzioni di memoria (pagine).
- **Elimina la frammentazione** mantenendo la coerenza semantica tra i segmenti del programma.

#### **9.1. Le quattro proprietà chiave**

In conclusione, la segmentazione con paginazione si distingue per **quattro proprietà fondamentali**:

1. **Spazio logico > spazio fisico**: come paginazione e segmentazione pure, crea uno spazio logico più grande di quello fisico assegnato al processo.
2. **Tipizzazione semantica**: come la segmentazione pura, consente di associare un tipo (e quindi un controllo della liceità degli accessi) a ciascun segmento.
3. **Configurazione implicita + gestione automatica**: la struttura in segmenti è definita implicitamente dal programmatore tramite programmazione modulare; tutto il resto è in carico al SO.
4. **Gestione efficiente senza frammentazione**: la realizzazione tramite paginazione garantisce caricamento/scaricamento rapidi delle pagine e, soprattutto, **elimina la frammentazione** della segmentazione pura — tutte le pagine sono utilizzate completamente, e non c'è più bisogno di selezionare frame sovradimensionati per contenere segmenti grandi.
