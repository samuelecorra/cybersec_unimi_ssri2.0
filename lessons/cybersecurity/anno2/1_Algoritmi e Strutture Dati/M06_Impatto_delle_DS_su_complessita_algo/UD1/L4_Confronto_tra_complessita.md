## **Lezione 4: Confronto tra complessità**

### **1. Introduzione**

Dopo aver analizzato i principali algoritmi per la ricerca dei **cammini minimi**, in questa lezione confrontiamo la **complessità computazionale** delle varie soluzioni viste finora, in funzione del tipo di **implementazione dell’insieme S** e della **struttura del grafo** su cui lavorano.

L’obiettivo è comprendere **quale algoritmo scegliere** a seconda del **contesto applicativo**:

- tipo di grafo (sparso o denso),
    
- dimensioni del problema,
    
- necessità di gestire archi con pesi negativi.
    

---

### **2. Tabella riassuntiva delle complessità**

|**Algoritmo**|**Insieme S / Struttura dati**|**Complessità**|**Note principali**|
|---|---|---|---|
|**Dijkstra**|Coda di priorità con lista non ordinata|$O(n^2)$|Pesi non negativi|
|**Johnson**|Coda di priorità con heap|$O(m \log n)$|Pesi non negativi|
|**Bellman-Ford-Moore (BFM)**|Coda FIFO|$O(nm)$|Supporta pesi negativi|
|**Algoritmo con pila**|Pila (LIFO)|Super-polinomiale|Può degenerare su grafi aciclici|
|**Pape-D’Esopo**|Dequeue (coda doppia)|Super-polinomiale (efficiente in pratica)|Ottimo per grafi sparsi e planari|

---

### **3. Grafi sparsi e grafi densi**

Un grafo $G = (N, A)$ è definito da:

- $n = |N|$ → numero di nodi
    
- $m = |A|$ → numero di archi
    

A seconda del rapporto tra $n$ e $m$, distinguiamo due tipologie:

- **Grafo sparso:**  
    quando il numero di archi è dello stesso ordine di grandezza dei nodi, cioè  
    $m = O(n)$
    
- **Grafo denso:**  
    quando il numero di archi cresce quadraticamente con i nodi, cioè  
    $m = O(n^2)$
    

Questa distinzione è fondamentale perché la **complessità** di un algoritmo può dipendere in modo diverso da $m$ e $n$.

---

### **4. Dijkstra vs. Johnson**

#### **4.1. Caso di grafi sparsi**

- Dijkstra → $O(n^2)$
    
- Johnson → $O(n \log n)$
    

In questo caso **Johnson è più efficiente**, grazie all’uso dell’heap che riduce drasticamente i tempi di estrazione del nodo con etichetta minima.

#### **4.2. Caso di grafi densi**

- Dijkstra → $O(n^2)$
    
- Johnson → $O(n^2 \log n)$
    

Qui invece **Dijkstra risulta più vantaggioso**, perché l’overhead dell’heap non compensa il grande numero di operazioni dovute all’elevato numero di archi.

**Conclusione:**  
Johnson è migliore su grafi **sparsi**,  
Dijkstra è migliore su grafi **densi**.

---

### **5. Coda vs. Coda di priorità**

#### **5.1. Caso di grafi sparsi**

- Dijkstra (lista non ordinata) → $O(n^2)$
    
- Johnson (heap) → $O(n \log n)$
    
- Bellman-Ford-Moore (coda) → $O(n^2)$
    

#### **5.2. Caso di grafi densi**

- Dijkstra → $O(n^2)$
    
- Johnson → $O(n^2 \log n)$
    
- Bellman-Ford-Moore → $O(n^3)$
    

Osserviamo che, in termini di efficienza asintotica:

$$  
\text{heap} < \text{lista non ordinata} \approx \text{coda}  
$$

ma in contesti specifici possiamo dire anche che:

$$  
\text{lista non ordinata} < \text{heap} < \text{coda}  
$$

---

### **6. Coda vs. Pila**

Sebbene la pila e la coda abbiano **operatori con la stessa complessità $O(1)$**, l’effetto che producono sulla procedura SPT è profondamente diverso.

- Con la **coda** (FIFO), la visita segue un ordine “ampio” e bilanciato.
    
- Con la **pila** (LIFO), la visita diventa “profonda” e squilibrata, generando un comportamento **esponenziale** in alcuni casi.
    

Il passaggio da una **coda** a una **pila** trasforma quindi un algoritmo **polinomiale** in uno **super-polinomiale**.  
La differenza non è dovuta ai costi degli operatori, ma alla **regola di esplorazione del grafo** implicita nella struttura.

---

### **7. Coda vs. Dequeue**

A prima vista, la **dequeue** (coda a doppia estremità) sembrerebbe solo una versione più flessibile della coda, quindi con prestazioni simili.  
In realtà, la politica di gestione ibrida (LIFO+FIFO) la rende **più instabile** nel caso pessimo.

#### **7.1. Caso pessimo**

Quando prevale il comportamento LIFO, la complessità può diventare **super-polinomiale**, come per l’algoritmo con pila.

#### **7.2. Caso efficiente**

Sperimentalmente, però, l’algoritmo di **Pape-D’Esopo** si dimostra **molto efficiente** su:

- grafi **sparsi**, dove $m = O(n)$,
    
- grafi **planari**, in cui il numero di archi è naturalmente limitato.
    

In questi contesti, l’inserimento “in testa” dei nodi migliorati (politica LIFO) accelera la propagazione delle etichette e riduce il numero di aggiornamenti complessivi.

---

### **8. Sintesi conclusiva**

Abbiamo ora una visione completa dell’evoluzione della **complessità** nella famiglia di algoritmi per i cammini minimi:

|**Algoritmo**|**Struttura S**|**Complessità**|**Pesi negativi**|**Uso consigliato**|
|---|---|---|---|---|
|Dijkstra|Lista non ordinata|$O(n^2)$|✗|Grafi densi|
|Johnson|Heap|$O(m \log n)$|✗|Grafi sparsi|
|Bellman-Ford-Moore|Coda|$O(nm)$|✓|Grafi generici (anche con pesi negativi)|
|Con pila|Pila|Super-polinomiale|✓|Solo teorico|
|Pape-D’Esopo|Dequeue|Super-polinomiale (ma veloce in pratica)|✓|Grafi planari o reti stradali|
