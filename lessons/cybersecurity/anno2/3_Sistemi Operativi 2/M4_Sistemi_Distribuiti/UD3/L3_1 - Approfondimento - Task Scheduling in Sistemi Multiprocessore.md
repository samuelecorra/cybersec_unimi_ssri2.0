# **M4 UD3 Lezione 3.1 - Approfondimento - Task Scheduling in Sistemi Multiprocessore**

Questa **dispensa (“Task Scheduling in Multiprocessing Systems”, IEEE Computer, 1995)** integra e amplia la **Lezione 3 – Allocazione dei processi**, fornendo una visione più formale e teorica del _problema di scheduling_ nei sistemi multiprocessore e distribuiti.  

### **1. Introduzione**

Il **problema di scheduling dei task** nei sistemi multiprocessore e distribuiti riguarda la **scelta dell’ordine di esecuzione** e **l’assegnazione dei compiti ai processori** in modo da ottimizzare una o più misure di prestazione (tempo totale, utilizzo, throughput).

Esempi reali:

- clienti serviti da più sportelli bancari,
    
- aerei in attesa di atterraggio,
    
- lavori su una catena di montaggio,
    
- task software distribuiti su più CPU.

In tutti questi casi, la questione è **come distribuire e ordinare le attività** per massimizzare l’efficienza complessiva.

---

### **2. Modello di scheduling**

Un **programma parallelo** è rappresentato come un insieme parzialmente ordinato di task:

$$  
P = (V, <)  
$$

dove:

- **V** è l’insieme dei task,
    
- **vᵢ < vⱼ** indica che il task _vᵢ_ deve terminare prima che _vⱼ_ inizi.

Ogni task _vᵢ_ ha un **costo computazionale** $A_i$,  
e ogni arco diretto $(i, j)$ ha un **costo di comunicazione** $D_{ij}$.

Il grafo risultante è un **grafo aciclico orientato (DAG)** detto **task graph**.

Il sistema di destinazione è modellato come un insieme di **processori connessi** da una rete, rappresentata con un grafo non orientato.  
Ogni processore _pₖ_ è caratterizzato da:

- **Sₖ** → velocità,
    
- **Bₖ** → tempo di inizializzazione di un task,
    
- **Iₖ** → tempo di inizializzazione di un messaggio.

L’obiettivo è costruire una **funzione di scheduling**:

$$  
f: V \to {1, 2, ..., m} \times [0, +\infty)  
$$

che assegna a ciascun task il **processore** e il **tempo di inizio** ottimale.

---

### **3. Costi di comunicazione**

La **durata totale del programma** è data da:

$$  
C_{tot} = C_{exec} + C_{comm}  
$$

dove:

- $C_{exec}$ è il **tempo di esecuzione complessivo** (schedule length),
    
- $C_{comm}$ è il **tempo di comunicazione** tra processori.

Sono stati proposti tre modelli per calcolare $C_{comm}$:

#### **Modello A**

- Il costo di comunicazione dipende dal **numero di messaggi** tra task su processori diversi.
    
- È il modello più semplice e astratto.

#### **Modello B**

- Tiene conto del **riuso delle comunicazioni** tra stessi processori (evita doppi conteggi).
    
- È più realistico di A.

#### **Modello C**

- Assume che ogni processore abbia un **processore di I/O dedicato**, permettendo di **sovrapporre calcolo e comunicazione**.
    
- È il modello più accurato ma anche più complesso da simulare.

---

### **4. Complessità del problema**

Determinare lo scheduling ottimale di un insieme di task su più processori è un problema **NP-completo** nella maggior parte dei casi.

Sono stati studiati alcuni casi particolari in cui la soluzione è polinomiale, ma in generale:

- l’ottimo è **computazionalmente intrattabile**,
    
- si ricorre a **algoritmi euristici o approssimati**.

---

### **5. Scheduling ottimale – casi risolubili**

Ci sono solo pochi casi per i quali esistono **algoritmi polinomiali**:

1. **Grafi ad albero (in-forest / out-forest)**
    
    - Algoritmo di **Hu (1961)**, detto _level algorithm_.
        
    - Assegna priorità in base al livello del task (distanza da un nodo terminale).
        
    - Esegue sempre il task con livello più alto disponibile.
    
2. **Grafi con ordine a intervalli (interval orders)**
    
    - Algoritmo _greedy_ che seleziona i task con **maggior numero di successori**.
        
    - Tempo di esecuzione: $O(|E| + |V|)$.
    
3. **Grafi arbitrari su due processori**
    
    - Algoritmo di **Coffman e Graham (1972)** basato su etichette di priorità.
        
    - Complessità: $O(n^2)$.

---

### **6. Scheduling con costi di comunicazione**

Quando si considera anche la comunicazione tra nodi, la complessità cresce ulteriormente.  
Due casi rimangono risolvibili in tempo polinomiale:

- **Alberi su due processori**  
    → È possibile trasformare il grafo originale in un _grafo esteso_ che include i costi di comunicazione, poi applicare algoritmi classici.
    
- **Interval orders su più processori**  
    → Algoritmo di **Ali e El-Rewini (1993)**, che assegna i task in base al numero di successori e al tempo minimo di inizio.

---

### **7. Algoritmi euristici**

Poiché il problema è NP-completo, nella pratica si usano **euristiche**:

#### **a. List Scheduling**

1. Assegnare a ogni task una **priorità**.
    
2. Ordinare i task in **coda di priorità decrescente**.
    
3. Eseguire sempre il **task pronto con priorità più alta** sul primo processore libero.

#### **b. Cluster Scheduling**

1. Raggruppare (clusterizzare) i task che comunicano molto.
    
2. Assegnare ogni cluster a un processore.
    
3. Ordinare i task all’interno di ciascun cluster.

Questo metodo bilancia **località di dati** e **minimizzazione della comunicazione**.

---

### **8. Strumenti software di supporto**

Diversi tool sono stati sviluppati per **automatizzare** il processo di scheduling e analisi delle prestazioni:

|Strumento|Funzione principale|Caratteristiche|
|---|---|---|
|**Parallax**|Strumento di progettazione|Genera Gantt chart, analisi di percorso critico, simulazioni di prestazioni|
|**Pyrros**|Strumento di compilazione e generazione codice|Crea codice C parallelo ottimizzato a partire da un grafo di task|
|**Hypertool**|Strumento di analisi delle prestazioni|Valuta tempo di esecuzione, comunicazione e sincronizzazione|

---

### **9. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Obiettivo**|Minimizzare il tempo totale di esecuzione di un insieme di task su più processori|
|**Modello**|Task graph (DAG) con costi di calcolo e comunicazione|
|**Complessità**|NP-completo nella maggior parte dei casi|
|**Casi risolubili**|Alberi, interval orders, grafi a due processori|
|**Strategie pratiche**|List scheduling, clustering, bilanciamento del carico|
|**Strumenti**|Parallax, Pyrros, Hypertool|

---

### **10. Conclusione**

Il **task scheduling** rappresenta uno dei problemi più complessi e cruciali nella **computazione distribuita**.  
Data la sua natura **NP-completa**, l’obiettivo pratico è individuare **soluzioni approssimate ma efficienti**, che bilancino calcolo, comunicazione e vincoli architetturali.

L’evoluzione di algoritmi euristici e strumenti software ha reso possibile affrontare scenari reali di **parallelismo massivo e distribuito**, ponendo le basi dei moderni sistemi **multi-core e cloud computing**.