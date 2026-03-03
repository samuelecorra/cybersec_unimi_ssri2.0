La **tecnica di progettazione "greedy"** (o **algoritmo goloso**) è un approccio generale alla risoluzione di problemi ottimizzativi.  
L’idea di base è **prendere decisioni locali ottimali**, nella speranza che queste conducano a una **soluzione globale ottimale**.

In pratica, un algoritmo greedy costruisce la soluzione **passo dopo passo**, scegliendo a ogni iterazione **l’opzione migliore disponibile in quel momento**, senza tornare mai indietro a riconsiderare le scelte fatte.

Questo modulo ha come obiettivo:

- comprendere **lo schema generale** della tecnica greedy,
    
- imparare a **riconoscere i problemi** in cui tale strategia è applicabile,
    
- **progettare e analizzare** algoritmi greedy specifici,
    
- e infine **valutare la complessità** delle soluzioni ottenute.
    

Gli algoritmi greedy trovano ampio impiego in problemi classici come **la selezione di attività**, **la costruzione di alberi di copertura minima (MST)**, o **la codifica di Huffman**, dove la scelta locale ottimale porta effettivamente alla soluzione migliore complessiva.


---

## **Lezione 1: Progetto di algoritmi Greedy**

### **1. Introduzione**

In questa lezione viene analizzato lo **schema generale di progetto** degli algoritmi di tipo **greedy**, basati sulla cosiddetta **strategia dell’ingordo**.  
Essa consiste nel **compiere, a ogni passo, la scelta migliore nell’immediato**, senza pianificare una strategia a lungo termine.

La tecnica greedy è adatta alla risoluzione di **problemi di selezione ottima** di un sottoinsieme di elementi da un insieme iniziale.  
Il **criterio di ottimalità** è definito da una funzione che valuta i singoli elementi in base a determinati parametri, come valore o peso.

---

### **2. Schema generale dell’algoritmo**

```c
void Greedy(insieme *A) {
    /* A = {a1, …, an} */
    S = ∅;
    {ordina ai ∈ A rispetto al criterio ottimo};
    for (i = 1; i <= n; i++)
        if (S ∪ {ai} è una soluzione valida)
            S = S ∪ {ai};
    /* restituisci S come soluzione */
}
```

L’algoritmo parte da un insieme $A = {a_1, \dots, a_n}$ e costruisce **incrementalmente** un sottoinsieme $S$, che rappresenta la soluzione.  
Gli **elementi di A** vengono prima **ordinati** secondo un **criterio di ottimalità**, poi inseriti uno per volta in $S$ **se la loro aggiunta mantiene valida la soluzione**.

Un algoritmo greedy di base si ottiene dunque **specificando l’ordinamento** e **la regola di costruzione** dell’insieme $S$.

---

### **3. Esempi di applicazione**

#### **Esempio 1: Problema dello zaino**

**Problema:**  
Dati $n$ oggetti $a_i$ di valore $v_i$ e peso $p_i$, scegliere un sottoinsieme di oggetti che **massimizzi il valore totale** rispettando la capacità $C$ dello zaino.

**Ordinamento:**  
Decrescente rispetto al valore $v_i$.

**Costruzione dell’insieme $S$:**

$$  
S = S \cup {a_i} \quad \text{se} \quad p_i + \sum_{a_j \in S} p_j \le C  
$$

---

#### **Esempio 2: Problema del Bin Packing**

**Problema:**  
Dati $n$ oggetti $a_i$ di peso $p_i$, partizionarli in contenitori (bin) $B_k$ di capacità $C$ in modo da **minimizzare il numero totale di bin** utilizzati.

**Ordinamento:**  
Decrescente rispetto al peso $p_i$.

**Costruzione dell’insieme $S$:**  
Inserisci $a_i$ nel contenitore $B_k$ scelto in modo che

$$  
C - \sum_{a_j \in B_k} p_j + p_i  
$$

sia **minimo**.

---

### **4. Principio di funzionamento**

Un algoritmo greedy prende decisioni secondo un **principio di ottimalità locale**:

- a ogni passo viene risolto un **sottoproblema più piccolo** del precedente;
    
- la soluzione dipende dalle scelte passate, ma **non da quelle future**.
    

Affinché un algoritmo greedy produca **una soluzione ottima**, devono essere verificate due proprietà fondamentali:

1. **Scelta Greedy**
    
2. **Sottostruttura Ottima**
    

---

### **5. Scelta Greedy**

Data una **caratterizzazione matematica** della soluzione, bisogna dimostrare che questa può essere modificata in modo da **utilizzare una prima scelta greedy**, riducendo così il problema a un sottoproblema più piccolo dello stesso tipo.

L’obiettivo è provare che la soluzione costruita come **sequenza di scelte greedy** è effettivamente **ottima**.

---

### **6. Sottostruttura Ottima**

Per mostrare che una scelta greedy riduce il problema a uno più piccolo dello stesso tipo, occorre dimostrare che **una soluzione ottima del problema contiene al suo interno le soluzioni ottime dei sottoproblemi**.

In altre parole, le successive configurazioni di $S$ devono essere **soluzioni ottime per i sottoproblemi** considerati.

---

### **7. Conclusione**

In sintesi:

- È stata introdotta la **tecnica di progetto greedy**.
    
- L’ottimalità di un algoritmo greedy si basa su due concetti: **scelta greedy** e **sottostruttura ottima**.
    
- In molti casi gli algoritmi greedy **non garantiscono** la soluzione ottima, ma forniscono **una buona soluzione iniziale** per algoritmi più sofisticati.
    
- Il concetto di **matroide** (approfondito in seguito) rappresenta una formalizzazione astratta dei problemi che **possono essere risolti esattamente** con strategie greedy.