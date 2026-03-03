## **Lezione 3: Proprietà di un grafo**

---

### **1. Introduzione**

In questa lezione analizziamo come **verificare le proprietà topologiche di un grafo**, cioè le caratteristiche strutturali che ne determinano il comportamento e l’organizzazione.  
Queste verifiche sono fondamentali per poter applicare correttamente **algoritmi più complessi** come quelli di ottimizzazione o di ordinamento topologico.

Studieremo in particolare:

- come verificare la **connessione** e la **forte connessione** di un grafo;
    
- come individuare le **componenti connesse**;
    
- come costruire e interpretare un **albero di copertura**;
    
- e come riconoscere se un grafo è **aciclico**.
    

---

### **2. Connessione di un grafo**

#### **Grafi non orientati**

Un **grafo non orientato** è **connesso** se, al termine di una **visita completa (DFS o BFS)**, **tutti i nodi risultano marcati come visitati**.  
In altre parole, partendo da un nodo qualsiasi, è possibile raggiungere ogni altro nodo del grafo.

#### **Grafi orientati**

Un **grafo orientato** è **fortemente connesso** se:

1. al termine della visita, tutti i nodi sono marcati come visitati;
    
2. invertendo il verso di tutti gli archi e ripetendo la visita, **tutti i nodi risultano nuovamente visitati**.
    

> In breve: un grafo orientato è fortemente connesso se esiste un cammino in **entrambi i sensi** tra ogni coppia di nodi.

---

### **3. Componenti connesse**

La **connessione** di un grafo non orientato può essere studiata anche in termini di **componenti connesse**, ossia i **sottografi massimali connessi** di cui il grafo è composto.

Ogni **componente connessa** è un insieme di nodi tra i quali esiste almeno una catena di collegamento, ma che non sono collegati a nodi di altre componenti.

#### **Idea operativa**

L’approccio standard consiste nel **ripetere una visita DFS** finché esistono nodi non ancora marcati.  
Ogni visita individua una nuova componente e ne marca tutti i nodi appartenenti.

---

### **4. Algoritmo per le componenti connesse**

```c
void compConnesse(grafo G, int n) {
    int comp[maxlung];
    int i, numcomp = 0;

    for (i = 0; i < n; i++)
        comp[i] = 0;

    for (i = 0; i < n; i++)
        if (comp[i] == 0) {
            numcomp++;
            DFSm(G, i, comp, numcomp);
        }
}

void DFSm(grafo G, nodo u, int *C, int n) {
    nodo v;
    C[u] = n;

    for each v ∈ A(u)
        if (C[v] == 0)
            DFSm(G, v, C, n);
}
```

#### **Spiegazione logica**

- Il vettore `comp[]` contiene l’indice della componente connessa a cui appartiene ogni nodo.
    
- All’inizio tutti i valori sono 0 (non visitati).
    
- Ogni chiamata ricorsiva a `DFSm()` marca tutti i nodi appartenenti alla stessa componente connessa.
    
- `numcomp` tiene il conto del numero totale di componenti.
    

---

### **5. Alberi di copertura**

Un **albero di copertura** è un sottoinsieme del grafo formato da **tutti i nodi** e da **n − 1 archi** che li collegano senza formare cicli.  
È la struttura base prodotta da una visita DFS o BFS completa.

#### **Tipi principali**

- **Albero di copertura DFS** → contiene gli archi che, durante la visita in profondità, collegano un nodo già marcato a uno non marcato.
    
- **Albero di copertura BFS** → ottenuto in modo analogo durante una visita in ampiezza.
    

---

### **6. Classificazione degli archi (nella DFS)**

Durante una visita DFS, gli archi del grafo vengono suddivisi in **quattro categorie**, a seconda della loro relazione con l’albero di copertura:

1. **Archi in T** → collegano un nodo marcato a uno non marcato (appartengono all’albero di copertura).
    
2. **Archi all’indietro** → collegano un nodo a un suo **antenato** nell’albero.
    
3. **Archi in avanti** → collegano un nodo a un suo **discendente**.
    
4. **Archi di attraversamento** → collegano nodi sullo stesso livello dell’albero, ma in rami differenti.
    

Questa classificazione permette di identificare **proprietà topologiche** come la presenza di cicli.

![[Pasted image 20251018193416.png]]

---

### **7. Grafo aciclico**

Un **grafo orientato** è detto **aciclico** se, durante la visita DFS, **non vengono mai trovati archi all’indietro**.  
Gli archi all’indietro indicano infatti un **ciclo**, cioè un cammino chiuso che riconduce a un nodo già visitato.

#### **Esempio**

Se, durante la DFS, troviamo un arco (4 → 2) e il nodo 2 è un **antenato** di 4 nell’albero di copertura, allora esiste un ciclo 2 → 4 → 2.

---

### **8. Complessità degli algoritmi**

Tutte le visite e verifiche descritte si basano su **DFS** e mantengono una **complessità lineare** rispetto al numero di nodi e archi:

$$  
O(n + m)  
$$

dove:

- $n$ è il numero di nodi,
    
- $m$ è il numero di archi.
    

---

### **9. Sintesi finale**

|Proprietà|Descrizione|Metodo di verifica|Complessità|
|---|---|---|---|
|**Connessione**|Tutti i nodi raggiungibili in un grafo non orientato|Una visita DFS/BFS|$O(n + m)$|
|**Forte connessione**|Tutti i nodi raggiungibili in entrambe le direzioni|Due visite DFS (grafo e trasposto)|$O(n + m)$|
|**Componenti connesse**|Sottografi connessi massimali|DFS iterativa per ogni nodo non marcato|$O(n + m)$|
|**Acilicità**|Nessun arco all’indietro in DFS|Analisi tipo arco durante la visita|$O(n + m)$|

---

> 🧠 **In sintesi:**  
> L’analisi delle proprietà topologiche è il passo che trasforma un grafo da _struttura di dati_ a _modello di conoscenza_.  
> Capire come un grafo è connesso, dove si interrompe, o se presenta cicli, è ciò che permette agli algoritmi successivi di muoversi con sicurezza dentro la sua architettura.