# **M2 UD2 Lezione 1 - Schedulazione degli accessi al disco**

### **1. Introduzione**

La gestione delle **memorie di massa** costituisce un elemento essenziale dei sistemi operativi.  
Poiché i dischi meccanici e anche gli SSD presentano tempi di accesso significativamente più lunghi rispetto alla memoria centrale, è necessario ottimizzare le operazioni di **lettura e scrittura** mediante opportune **politiche di schedulazione**.

L’obiettivo della schedulazione è **ridurre il tempo medio di accesso al disco** e **massimizzare la larghezza di banda complessiva** del sistema.

---

### **2. Tempo di accesso al disco**

Il **tempo di accesso** (Access Time) è il tempo complessivo necessario per eseguire un’operazione di I/O su disco.

Esso è composto da due componenti principali:

1. **Tempo di ricerca (Seek Time)**  
    → Tempo richiesto per spostare la testina di lettura/scrittura sulla traccia desiderata.
    
2. **Latenza di rotazione (Rotational Latency)**  
    → Tempo necessario affinché il settore richiesto ruoti fino a trovarsi sotto la testina.

Il tempo totale di accesso si può esprimere come:

$$  
T_{accesso} = T_{seek} + T_{rotazione} + T_{trasferimento}  
$$

dove:

- $( T_{seek} )$: tempo di posizionamento della testina,
    
- $( T_{rotazione} )$: tempo di attesa per l’allineamento del settore,
    
- $( T_{trasferimento} )$: tempo effettivo di lettura o scrittura dei dati.

---

### **3. Larghezza di banda del disco**

La **larghezza di banda (bandwidth)** indica la capacità del disco di **trasferire dati** in un certo intervallo di tempo.

$$  
\text{Bandwidth} = \frac{\text{Numero di byte trasferiti}}{\text{Tempo totale di trasferimento}}  
$$

Un’elevata larghezza di banda implica un **flusso continuo e rapido di dati**, con minimi tempi di attesa tra le operazioni.

---

### **4. Obiettivi della schedulazione del disco**

La schedulazione degli accessi mira a:

- **Ridurre il tempo medio di accesso** alle tracce e ai settori richiesti.
    
- **Aumentare la larghezza di banda effettiva**, riducendo i tempi morti.
    
- **Bilanciare le richieste di I/O** provenienti da più processi concorrenti.
    
- **Prevenire starvation** (richieste che restano in attesa troppo a lungo).

---

### **5. Algoritmi di schedulazione degli accessi**

#### **a. FCFS – First Come, First Served**

È la strategia più semplice:  
le richieste vengono servite **nell’ordine di arrivo**.

**Vantaggi**

- Facile da implementare.
    
- Garantisce equità tra i processi.

**Svantaggi**

- Non ottimizza il movimento delle testine.
    
- Può produrre **tempi di attesa elevati**, se le richieste sono sparse su tracce molto distanti.

---

#### **b. SSTF – Shortest Seek Time First**

Serve per primo il **richiesta più vicina** alla posizione corrente della testina, minimizzando il **tempo di ricerca**.

**Vantaggi**

- Riduce notevolmente il tempo medio di accesso.
    
- Migliora la larghezza di banda rispetto a FCFS.

**Svantaggi**

- Può generare **starvation** per le richieste lontane, se la testina resta concentrata in un’area del disco.
    
- È meno prevedibile nei sistemi in tempo reale.

---

#### **c. SCAN – Algoritmo dell’ascensore**

La testina si muove **in una direzione fissa (es. verso l’esterno)** servendo tutte le richieste lungo il percorso.  
Quando raggiunge l’estremo, **inverte la direzione** e serve le richieste nel verso opposto.

**Vantaggi**

- Riduce i movimenti inutili della testina.
    
- Fornisce tempi medi di accesso inferiori rispetto a FCFS e più regolari di SSTF.

**Svantaggi**

- Le richieste ai bordi del disco possono subire **ritardi maggiori**.

---

#### **d. C-SCAN – Circular SCAN**

Versione modificata dello SCAN.  
La testina **serve le richieste in un’unica direzione** (ad esempio dall’interno verso l’esterno), poi **torna rapidamente all’inizio** senza servire richieste nel tragitto di ritorno.

**Vantaggi**

- Offre **tempi di attesa uniformi** per tutte le richieste.
    
- Evita la penalizzazione delle richieste centrali o esterne.

**Svantaggi**

- Maggiore tempo di rotazione complessivo, poiché il ritorno della testina non è produttivo.

---

#### **e. LOOK**

È una variante dello SCAN:  
la testina **si ferma** quando non ci sono più richieste nella direzione corrente, evitando di raggiungere i limiti estremi del disco.

**Vantaggi**

- Riduce i movimenti inutili della testina.
    
- Offre prestazioni simili a SCAN ma con tempi medi più bassi.

**Svantaggi**

- Come SSTF e SCAN, può introdurre un minimo di **ineguaglianza temporale** tra richieste centrali e periferiche.

---

### **6. Selezione dell’algoritmo di schedulazione**

La scelta dell’algoritmo ottimale dipende da:

- **Numero e tipo di richieste** di I/O generate dal sistema.
    
- **Distribuzione spaziale** delle tracce richieste.
    
- **Metodo di allocazione dei file** (sequenziale, indicizzato, contiguo...).
    
- **Caratteristiche del disco** (rotazionale o SSD).

Spesso i sistemi operativi implementano il modulo di schedulazione come **componente indipendente**, in modo da poterlo sostituire o configurare dinamicamente.  
Gli algoritmi **SSTF** e **LOOK** rappresentano in genere le **scelte predefinite più bilanciate**.

---

### **7. Sintesi finale**

- Il **tempo di accesso** al disco dipende da ricerca, latenza e trasferimento.
    
- La **schedulazione** serve a ridurre i movimenti della testina e i tempi di attesa.
    
- Gli algoritmi principali sono:
    
    - **FCFS:** semplice ma poco efficiente.
        
    - **SSTF:** ottimizza il seek time, ma può causare starvation.
        
    - **SCAN:** bilanciato, con movimento bidirezionale.
        
    - **C-SCAN:** regolare e prevedibile, con scansione circolare.
        
    - **LOOK:** come SCAN, ma evita movimenti superflui.
    
- La **scelta dell’algoritmo** dipende dal carico di lavoro e dal tipo di dispositivo.


In sintesi, la schedulazione del disco è una componente fondamentale per ottimizzare **prestazioni, latenza e throughput** dell’intero sottosistema di memorie di massa.