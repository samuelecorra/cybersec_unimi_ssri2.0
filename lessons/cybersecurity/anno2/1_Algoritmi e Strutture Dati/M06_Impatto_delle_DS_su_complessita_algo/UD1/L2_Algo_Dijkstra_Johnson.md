## **Lezione 2: Algoritmi di Dijkstra e Johnson**

### **1. Introduzione**

In questa lezione analizziamo due versioni fondamentali della **procedura SPT** per la ricerca dei **cammini minimi**:  
l’**algoritmo di Dijkstra** (1959) e l’**algoritmo di Johnson** (1977).

Entrambi si basano sulla stessa idea generale: estrarre **il nodo con etichetta minima** da un insieme $S$ e aggiornare le etichette dei nodi adiacenti.  
La differenza sostanziale sta nel **modo in cui l’insieme $S$ viene realizzato** — cioè nella struttura dati scelta per gestire le priorità.

Poiché l’efficienza complessiva dell’algoritmo dipende direttamente da come è implementata questa estrazione, studieremo come cambiano **la complessità** e **le operazioni principali** a seconda che $S$ sia una **lista** o uno **heap**.

Tratteremo:

1. l’algoritmo di **Dijkstra**
    
2. l’algoritmo di **Johnson**
    
3. un cenno all’**Applet Java** di simulazione visiva.
    

---

### **2. L’algoritmo di Dijkstra**

#### **2.1. Idea di base**

L’insieme $S$ viene realizzato come **coda di priorità** implementata tramite **lista non ordinata**.  
Ogni elemento di $S$ è un nodo $u$, e la **priorità** associata è il valore della sua etichetta $d_u$ (la distanza corrente dal nodo sorgente $r$).

Ad ogni iterazione:

- si estrae da $S$ il nodo con **etichetta minima**,
    
- si visitano i suoi nodi adiacenti,
    
- si aggiornano le etichette dei vicini se si trova un cammino più conveniente.
    

Le operazioni fondamentali sulla coda sono:

- `min(S)` → restituisce il nodo con etichetta minima,
    
- `cancellamin(S)` → lo rimuove da $S$.
    

---

#### **2.2. Ipotesi e validità**

L’algoritmo di Dijkstra funziona correttamente **solo se tutti i pesi degli archi sono non negativi**, cioè se:

$$  
c_{uv} \ge 0 \quad \forall (u,v) \in A  
$$

In questo caso, l’algoritmo garantisce di trovare la soluzione ottima, perché una volta che un nodo viene estratto, la sua distanza è definitiva e non può più essere migliorata.

---

#### **2.3. Complessità dell’algoritmo**

Ogni nodo viene estratto **una sola volta** dall’insieme $S$.  
Poiché l’insieme è una lista non ordinata:

- la ricerca del minimo (`min`) richiede tempo $O(n)$,
    
- anche la cancellazione (`cancellamin`) richiede tempo $O(n)$,
    
- e il ciclo principale viene eseguito $n$ volte (una per ogni nodo).
    

Ne risulta una **complessità complessiva** pari a:

$$  
O(n^2)  
$$

Questa è la versione classica dell’algoritmo di **Dijkstra**, adatta a grafi di dimensioni moderate.

---

### **3. L’algoritmo di Johnson**

#### **3.1. Idea di base**

Johnson migliorò l’efficienza dell’algoritmo di Dijkstra sostituendo la lista con una **coda di priorità realizzata tramite heap** (tipicamente un _min-heap_).  
In questo modo l’operazione di estrazione del nodo con etichetta minima diventa molto più rapida.

Tuttavia, l’uso di uno heap introduce una difficoltà:  
le **priorità dei nodi cambiano frequentemente**, perché ogni volta che un’etichetta $d_v$ viene aggiornata, bisogna modificare la posizione di $v$ all’interno dello heap.

---

#### **3.2. L’operatore “decrementa”**

Per risolvere il problema dell’aggiornamento delle priorità, Johnson introdusse un nuovo operatore chiamato **decrementa**, che ha lo scopo di ridurre la priorità (cioè il valore di $d_u$) di un nodo all’interno della coda.

Formalmente:

- **Funzione:**  
    decrementa(elemento, nuova_priorità, S) → S’
    
- **Precondizione:** $u \in S$, $p < d_u$
    
- **Postcondizione:** $d_u = p$
    

In altre parole, quando troviamo un cammino più corto per $u$, aggiorniamo la sua etichetta nel grafo **e** la sua priorità nello heap, mantenendo la struttura ordinata.

---

#### **3.3. Complessità dell’algoritmo**

L’ipotesi rimane la stessa: tutti i pesi devono essere **non negativi**, cioè $c_{uv} \ge 0$.  
In questo caso, anche l’algoritmo di Johnson è polinomiale, e ogni nodo viene estratto **una sola volta** da $S$.

Analisi:

- Il ciclo `while` è eseguito $n$ volte.
    
- Le operazioni fondamentali (`estrazione`, `decrementa`) richiedono tempo $O(\log n)$ grazie all’uso dello heap.
    

Tuttavia, l’operatore `decrementa` può essere richiamato più volte: nel **caso pessimo**, può essere invocato per ogni arco del grafo, cioè per tutti i nodi adiacenti $v \in A(u)$ ad ogni iterazione.

La complessità totale diventa quindi:

$$  
O(n \log n + \sum_{u \in S} |A(u)| \log n)  
$$

Poiché $\sum_{u \in S} |A(u)| = m$, dove $m$ è il numero totale di archi, otteniamo la **forma finale della complessità**:

$$  
O(m \log n)  
$$

Questo rende l’algoritmo di Johnson molto più efficiente rispetto a Dijkstra, specialmente in grafi densi o di grandi dimensioni.

---

### **4. Riepilogo e animazione Java**

Abbiamo visto due algoritmi appartenenti alla **classe degli algoritmi basati sull’estrazione del nodo con etichetta minima**:

- **Dijkstra**, che usa una lista non ordinata ($O(n^2)$);
    
- **Johnson**, che usa uno heap ($O(m \log n)$).
    

Entrambi garantiscono la correttezza in presenza di **pesi non negativi** e differiscono solo nella **gestione della coda di priorità**.

Per visualizzare in modo intuitivo il funzionamento di questi algoritmi, è disponibile un’**applet Java** che mostra passo dopo passo come l’insieme $S$ viene aggiornato, e come i nodi vengono estratti e rilassati fino a formare l’albero dei cammini minimi.

---

### **5. Sintesi**

- Entrambi gli algoritmi si basano sulla **procedura SPT** e sulle **condizioni di Bellman**.
    
- La differenza sta nella **struttura dati usata per l’insieme S**.
    
- La **scelta della struttura dati** influenza direttamente la **complessità computazionale**.
    

|Algoritmo|Struttura dati|Complessità|
|---|---|---|
|Dijkstra|Lista non ordinata|$O(n^2)$|
|Johnson|Heap (min-heap)|$O(m \log n)$|
