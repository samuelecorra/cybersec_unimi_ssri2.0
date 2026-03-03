## **Lezione 5: Applicazione dei cammini minimi**

### **1. Introduzione**

In questa lezione applichiamo il modello dei **cammini minimi (SPT)** a due problemi computazionali specifici.  
L’obiettivo non è implementare da zero un nuovo algoritmo, ma **adattare la struttura e la logica dell’algoritmo SPT** a contesti diversi, modificando:

- il **significato dei costi** $c_{uv}$ sugli archi,
    
- la **semantica delle etichette** $d_u$ sui nodi,
    
- e le **condizioni di Bellman** in base al problema.
    

Vedremo come, partendo dalla stessa architettura logica, possiamo risolvere due problemi molto differenti:

1. il **numero minimo di hop** in una rete,
    
2. la **portata massima** di un cammino.
    

---

### **2. Problema: numero minimo di hop**

#### **2.1. Descrizione del problema**

Sia dato un grafo $G = (N, A)$ con:

- $|N| = n$ nodi,
    
- $|A| = m$ archi.
    

Il grafo rappresenta una **rete di telecomunicazione** in cui:

- ogni **nodo** $u$ è un **router**,
    
- ogni **arco** $(u,v)$ rappresenta una **connessione diretta** tra due router.
    

Il **numero di hop** indica quanti router vengono attraversati per passare dal nodo sorgente $r$ a un generico nodo $u$.

Il problema consiste nel trovare, per ogni $u \in N \setminus {r}$, il **cammino con il numero minimo di hop** a partire da $r$.

---

#### **2.2. Esempio**

Consideriamo due possibili cammini tra i nodi $(a)$ e $(b)$:

- il primo attraversa **1 hop**,
    
- il secondo attraversa **2 hop**.
    

L’obiettivo è chiaramente quello di **minimizzare** il numero di router intermedi.

---

#### **2.3. Modellazione tipo SPT**

Per risolvere il problema, possiamo usare l’approccio SPT senza alcuna modifica strutturale, semplicemente ridefinendo i costi e le etichette.

- Costo sugli archi:  
    $c_{uv} = 1 \quad \forall (u,v) \in A$  
    (ogni passaggio tra router conta come un hop).
    
- Etichetta $d_u$:  
    rappresenta il **numero minimo di hop** da $r$ a $u$.
    
- Condizioni SPT:  
    rimangono **identiche** alle condizioni di Bellman classiche.
    

---

#### **2.4. Osservazione**

Poiché tutti i costi sono unitari, se l’algoritmo SPT utilizza una **coda FIFO**, il comportamento risultante è quello di una **visita in ampiezza (BFS)** del grafo.  
In questo caso, la **verifica delle condizioni di Bellman** coincide esattamente con il controllo:

> “Nodo già visitato / non visitato”

In altre parole, il problema del **cammino minimo in termini di hop** è una **BFS applicata a un grafo non pesato**.

---

### **3. Problema: portata massima**

#### **3.1. Descrizione del problema**

Consideriamo di nuovo un grafo $G = (N, A)$ con $|N| = n$ e $|A| = m$.  
Ogni nodo rappresenta un router e ogni arco $(u,v)$ una connessione diretta.

Ora però, a ogni arco $(u,v)$ è associata una **portata massima** $f_{uv}$, cioè la quantità massima di dati che può transitare contemporaneamente su quel collegamento.

La **portata di un cammino $P$** è definita come:

$$  
F(P) = \min { f_{ij} : (i,j) \in P }  
$$

Cioè la portata massima del cammino è determinata dal **collegamento più debole** lungo quel percorso.

---

#### **3.2. Obiettivo**

Proporre un algoritmo che calcoli, per ogni $u \in N \setminus {r}$, un cammino da $r$ a $u$ **di portata massima**, cioè il cammino che consente il **massimo flusso simultaneo** tra i due nodi.

---

#### **3.3. Esempio**

Tra due nodi $(a)$ e $(b)$ possono esistere diversi cammini:

- il primo con portata massima $1$,
    
- il secondo con portata massima $3$.
    

Il secondo è evidentemente **preferibile**, perché il suo collegamento più debole è più “forte”.

---

#### **3.4. Modellazione tipo SPT**

Anche in questo caso possiamo adattare l’algoritmo SPT modificando solo le regole di calcolo.

- **Costo dell’arco:**  
    $c_{uv} = f_{uv}$ per ogni $(u,v) \in A$
    
- **Etichetta $d_u$:**  
    rappresenta la **portata massima** del cammino da $r$ a $u$, cioè il minimo tra gli archi lungo il percorso.
    

Inizializzazione:

- $d_r = +\infty$
    
- $d_u = 0$ per ogni $u \in N \setminus {r}$
    

---

#### **3.5. Condizioni SPT adattate**

Le condizioni SPT diventano:

$$  
d_v \ge \min { d_u, f_{uv} }  
$$

Durante l’esecuzione, se la condizione è violata, si effettua l’aggiornamento:

$$  
\text{se } d_v < \min { d_u, f_{uv} } \text{ allora } d_v = \min { d_u, f_{uv} }  
$$

In questo modo le etichette $d_u$ vengono progressivamente **ridotte** fino a convergere verso la portata massima effettiva di ciascun cammino.

---

### **4. Analisi delle proprietà**

L’adattamento dell’algoritmo SPT si basa su tre elementi fondamentali:

1. la **ridefinizione del costo** sugli archi;
    
2. la **ridefinizione delle etichette** sui nodi;
    
3. la **modifica delle condizioni di Bellman** in base alla nuova semantica del problema.
    

La **correttezza** del modello è garantita dal fatto che:

- i costi sono **non negativi**,
    
- e le etichette variano in modo **monotono** (non crescente o non decrescente).
    

Queste due proprietà assicurano che le **condizioni di Bellman adattate** mantengano validità logica e convergenza dell’algoritmo.

---

### **5. Sintesi finale**

Abbiamo applicato il modello SPT a due problemi differenti:

|**Problema**|**Significato dei costi $c_{uv}$**|**Etichetta $d_u$**|**Condizioni SPT**|**Risultato**|
|---|---|---|---|---|
|Numero minimo di hop|$c_{uv} = 1$|Minimo numero di hop|Invariata|Visita BFS|
|Portata massima|$c_{uv} = f_{uv}$|Portata massima (minimo tra archi)|$d_v \ge \min{d_u, f_{uv}}$|Cammino a portata massima|

In entrambi i casi, l’adattamento dell’algoritmo SPT dimostra la **flessibilità del modello dei cammini minimi**, che può essere riutilizzato in contesti diversi semplicemente cambiando la **semantica dei dati** senza alterare la **logica strutturale** dell’algoritmo.