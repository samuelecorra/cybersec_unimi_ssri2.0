# **M1 UD2 Lezione 2 - Segmentazione**

---

### **1. Introduzione**

La **segmentazione** è una tecnica di gestione della memoria centrale che nasce per superare i limiti concettuali della **paginazione**.  
Mentre la paginazione divide la memoria in blocchi uniformi e anonimi, la segmentazione suddivide lo spazio logico del processo in **unità semantiche**, coerenti con la struttura del programma (come codice, dati, stack, heap, ecc.).

L’obiettivo è fornire una **visione logica più vicina al programmatore**, consentendo la **tipizzazione**, la **protezione** e la **condivisione** delle diverse aree di memoria.

---

### **2. Problemi di partenza**

La segmentazione affronta gli stessi problemi della paginazione — l'inefficienza del caricamento e la necessità di gestire la memoria in modo dinamico — ma aggiunge un ulteriore livello di astrazione.

Nella paginazione:

- lo spazio logico è visto come un **unico vettore di indirizzi** omogenei;
- non è possibile **distinguere semanticamente** le varie porzioni (codice, dati, stack), né assegnare loro un **tipo** che definisca le operazioni lecite;
- la **condivisione** di codice tra processi è limitata e poco granulare: si può condividere "**tutto il codice o nulla**", senza un meccanismo naturale per condividere solo specifiche sezioni semantiche.

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

- Ogni segmento contiene dati o codice di **tipo specifico** (codice, dati globali, heap, stack, tabella simboli, ...).
- I segmenti possono avere **dimensioni diverse**, riflettendo la dimensione naturale del loro contenuto.
- Un segmento logico è caricato in un frame fisico di dimensione **uguale o sufficiente a contenerlo** — vincolo dimensionale chiave: a differenza della paginazione, non basta una qualunque pagina fisica libera, serve un frame **abbastanza grande**.

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

- $s$ = numero del segmento logico,
- $d$ = spiazzamento (offset) all'interno del segmento.

La **MMU** traduce l'indirizzo logico nell'indirizzo fisico:

$$  
\text{Indirizzo fisico} = \text{BaseFrame}_s + d  
$$

In pratica, all'indirizzo base del frame del segmento viene sommato lo spiazzamento richiesto.

#### **Schema completo: traduzione + verifica di liceità**

Lo schema della MMU per la segmentazione include un **controllo aggiuntivo rispetto alla paginazione**, perché ogni segmento ha una **dimensione propria** che funge da limite:

$$
\begin{array}{c}
\text{CPU genera indirizzo logico } (s, d) \\\\
\downarrow \\\\
\text{Usa } s \text{ come indice nella tabella dei segmenti} \\\\
\downarrow \\\\
\text{Si legge la coppia } (\text{BaseFrame}_s,\ \text{Dimensione}_s) \\\\
\downarrow \\\\
\text{Confronto: } d < \text{Dimensione}_s \;? \\\\
\\
\swarrow\;\;\searrow \\\\
\text{Sì: lecito} \;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\text{No: violazione} \\\\
\downarrow \;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\;\downarrow \\\\
\text{Indirizzo fisico} \;\;\;\;\;\;\;\;\;\;\;\;\text{trap: \textbf{segmentation fault}} \\\\
\text{BaseFrame}_s + d
\end{array}
$$

La **dimensione del segmento** (il *limite*) è quindi parte integrante del meccanismo di traduzione: serve a **proteggere** ogni segmento dai propri accessi fuori dai bordi.

#### **Mapping bidimensionale → lineare**

In memoria centrale ogni segmento logico viene caricato in una porzione lineare della RAM. La tabella dei segmenti **conserva gli accoppiamenti di posizione** che trasformano la struttura **bidimensionale** dello spazio di indirizzamento del processo (più segmenti, ciascuno con il proprio indirizzamento lineare) nella struttura **lineare** della memoria centrale (un unico vettore di indirizzi).

---

### **6. Gestione della segmentazione**

#### **(1) Caricamento**

I segmenti necessari per la prossima fase di computazione dei processi nello stato di *ready* vengono **caricati in memoria centrale**.  
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
- rileva due tipologie distinte di errore, sollevando una trap verso il processore.

#### **Due tipi di trap distinte**

$$
\begin{cases}
\textbf{Segmentation fault:} & \text{il segmento desiderato \textbf{non è presente} in memoria centrale} \\\\
& \text{(va caricato dall'area di swap).} \\\\
\textbf{Segmentation violation:} & \text{l'indirizzo richiesto \textbf{eccede i limiti} del segmento} \\\\
& \text{(spiazzamento } d \ge \text{Dimensione}_s \text{) — accesso illecito.}
\end{cases}
$$

Nel primo caso il sistema operativo risponde caricando il segmento mancante. Nel secondo caso si tratta di un **errore di programmazione** (es. accesso a un array oltre i suoi indici): il SO tipicamente termina il processo che ha generato la violazione.

La MMU garantisce quindi la **sicurezza e l'integrità** dello spazio di memoria dei processi.

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

##### **Confronto con la paginazione: l'esempio dell'editor**

Riprendiamo l'esempio dell'editor condiviso tra due utenti che modificano due file diversi:

| Aspetto | Paginazione | Segmentazione |
| --- | --- | --- |
| Condivisione del codice | Possibile, ma **bisogna garantire** che nelle pagine condivise **non finiscano** porzioni di dati di nessuno dei processi | **Naturale e automatica**: codice e dati sono in segmenti distinti per costruzione |
| Layout richiesto | Separazione netta tra codice e dati **a livello di pagina** (responsabilità del caricatore) | Separazione **implicita** nella struttura dei segmenti |
| Granularità | "Tutto il codice o nulla" — difficile condividere singole sezioni semantiche | Granularità a livello di **segmento logico** — si possono condividere selettivamente codice, librerie, ecc. |

Aver diviso lo spazio di indirizzamento in **segmenti tipizzati** crea **automaticamente** la separazione tra codice e dati, abilitando una condivisione naturale e priva del rischio di mischiare dati privati con codice condiviso.

---

### **9. Frammentazione della memoria**

Poiché i segmenti possono avere **dimensioni variabili**, può verificarsi **frammentazione esterna**:

- quando un segmento viene caricato, deve occupare un frame sufficientemente grande;
- eventuali porzioni di spazio inutilizzate nel frame costituiscono **sfridi di memoria**.

#### **Il problema reale: spazio sufficiente ma non contiguo**

L'insieme degli sfridi può accumularsi nel tempo e costituire una **quantità non piccola** di memoria centrale. Si arriva alla situazione critica: pur **sommando tutti gli sfridi** si avrebbe spazio sufficiente per caricare un nuovo segmento, **ma di fatto non si riesce a farlo**, perché lo spazio è **distribuito** in frammenti separati e il nuovo segmento richiede un'area **contigua**.

#### **Soluzione: garbage collection (compattazione)**

Il sistema operativo può eseguire una **garbage collection della memoria**: una procedura di **compattazione** che:

1. **collezziona gli sfridi inusabili** sparsi per la memoria;
2. li **accorpa** spostando i segmenti caricati in posizioni adiacenti;
3. lascia gli **spazi liberi contigui alla fine** della memoria, ora utilizzabili per nuovi segmenti.

##### **Perché è possibile: la rilocabilità dinamica**

La compattazione è realizzabile grazie a una proprietà fondamentale della segmentazione: gli indirizzi sono generati in modo **relativo (base + spiazzamento)** ed è quindi possibile **rilocare dinamicamente** un segmento in una posizione diversa della RAM. Per farlo basta:

1. **spostare fisicamente** il segmento nella nuova posizione;
2. **aggiornare** nella tabella dei segmenti l'indirizzo di base che identifica il segmento fisico in cui è caricato il segmento logico.

Non occorre modificare nulla nel codice del processo: la MMU continuerà a tradurre correttamente gli indirizzi logici usando il nuovo valore della base.

---

### **10. Sintesi finale**

- La **segmentazione** suddivide la memoria in **unità logiche di tipo diverso**, in contrasto con la paginazione che usa blocchi uniformi.
- È configurata **implicitamente dal programmatore** attraverso la struttura del codice (programmazione modulare), tradotta in segmenti dal compilatore e dal linker.
- È **gestita automaticamente** dal sistema operativo e supportata dalla **MMU** in modo efficiente.
- Permette di associare **tipi, permessi e funzioni** ai diversi segmenti, abilitando protezione e condivisione granulare.
- Introduce però **frammentazione esterna**, mitigabile tramite compattazione (resa possibile dalla rilocabilità dinamica).
- Come la paginazione, consente a ogni processo di percepire uno **spazio logico più grande** della memoria fisica realmente disponibile.

#### **10.1. Confronto sintetico segmentazione vs paginazione**

| Aspetto | Paginazione | Segmentazione |
| --- | --- | --- |
| Dimensione delle porzioni | **Fissa** (uguale per tutte) | **Variabile** (in base al contenuto del segmento) |
| Tipizzazione delle porzioni | ❌ Assente (blocchi anonimi) | ✅ Presente (codice, dati, stack, heap, ...) |
| Controllo della liceità degli accessi | Solo bit di protezione per pagina | Bit di protezione **+ controllo dei limiti** (`d < Dimensione`) |
| Condivisione semantica selettiva | Difficile (granularità a livello di pagina) | Naturale (un segmento per volta) |
| Frammentazione | **Interna** (entro la pagina) | **Esterna** (tra i segmenti) |
| Configurazione | Trasparente (granularità fissa) | Implicita tramite struttura modulare del codice |
| Errori tipici | **Page fault** | **Segmentation fault** (assenza) e **segmentation violation** (out-of-bounds) |

In entrambi i casi la gestione è **automatica e indipendente dal programmatore**, e tutte e due le tecniche realizzano lo stesso obiettivo strategico: creare uno **spazio logico maggiore** di quello fisico assegnato al processo.
