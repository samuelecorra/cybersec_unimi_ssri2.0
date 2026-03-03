
## **Lezione 1: Esplorazione di un grafo**

---

### **1. Introduzione**

Così come per il tipo di dato _albero_, anche per il tipo di dato _grafo_ è necessario disporre di **strumenti algoritmici** che permettano una visita sistematica di tutti i nodi e degli archi.  
Questi strumenti sono gli **algoritmi di visita**, che servono a esplorare il grafo partendo da un nodo iniziale e raggiungendo progressivamente tutti i nodi connessi.

Le strategie fondamentali di visita sono due:

- **DFS (Depth First Search)** → _visita in profondità_
    
- **BFS (Breadth First Search)** → _visita in ampiezza_ o _a ventaglio_
    

---

### **2. DFS – Depth First Search**

La DFS è una **diretta estensione della visita in ordine anticipato** di un albero ordinato.  
L’idea è di **seguire un cammino il più a fondo possibile** e, solo quando non ci sono più nodi da esplorare in quella direzione, **tornare indietro**.

#### **Schema operativo**

1. Si visita un nodo `u` e lo si marca come visitato.
    
2. Si esplorano i suoi nodi adiacenti, visitandoli uno alla volta.
    
3. Quando si raggiunge un nodo i cui adiacenti sono già tutti visitati, si torna indietro lungo l’ultimo arco visitato.
    
4. Si riprende l’esplorazione da altri cammini ancora non percorsi.
    

#### **Codice (versione ricorsiva)**

```c
void DFS(grafo G, nodo u) {
    nodo v;
    { esame nodo u e marcalo visitato };
    for each v ∈ A(u) {
        { esamina arco (u,v) };
        if (v non visitato)
            DFS(G, v);
    }
}
```

In questo schema:

- la funzione viene richiamata **ricorsivamente** per ogni nodo adiacente non ancora visitato;
    
- la **ricorsione** gestisce implicitamente il ritorno ai nodi precedenti, come una pila.
    

---

### **3. BFS – Breadth First Search**

La BFS visita i nodi **in ordine di distanza crescente** dal nodo di partenza.  
La _distanza_ tra due nodi è il **numero minimo di archi** che li separa lungo un cammino.  
Si tratta quindi di un’estensione della **visita per livelli** di un albero radicato.

#### **Codice (versione iterativa)**

```c
void BFS(grafo G, nodo u) {
    nodo v;
    coda Q;

    creacoda(Q);
    incoda(u, Q);

    while (!codavuota(Q)) {
        u = leggicoda(Q);
        fuoricoda(Q);
        { esame nodo u e marcalo visitato };

        for each v ∈ A(u) {
            { esamina arco (u,v) };
            if (v non visitato && v ∉ Q)
                incoda(v, Q);
        }
    }
}
```

#### **Spiegazione passo per passo**

1. Si crea una **coda** `Q` e si inserisce il nodo di partenza.
    
2. Finché la coda non è vuota:
    
    - si estrae il primo nodo,
        
    - lo si elabora e lo si marca come visitato,
        
    - si esaminano i suoi adiacenti, inserendo nella coda quelli non ancora visitati.
        
3. L’ordine della coda determina l’ordine di esplorazione → _per livelli_.
    

---

### **4. Nodo visitato**

Durante entrambe le visite, occorre tenere traccia dei nodi già visitati.  
Si utilizza un **vettore booleano** `visitato[]`:

$$  
visitato[u] =  
\begin{cases}  
\text{FALSE}, & \text{se } u \text{ non è ancora visitato} \  
\text{TRUE}, & \text{se } u \text{ è già stato visitato}  
\end{cases}  
$$

---

### **5. Complessità**

Ogni nodo viene visitato **una sola volta**, e per ciascun nodo `v` si scandisce **una sola volta l’insieme A(v)** dei suoi adiacenti.

Di conseguenza, la complessità di entrambe le procedure è:

$$  
O(n + m)  
$$

dove:

- $n$ = numero di nodi
    
- $m$ = numero di archi
    

Si tratta quindi della **complessità ottima** per una visita completa di un grafo.

---

### **6. DFS iterativa**

È possibile riscrivere la DFS in **forma iterativa**, utilizzando una **pila** invece della ricorsione.

#### **Procedura**

- Sostituire ogni operatore per la **coda** del codice BFS con il corrispondente operatore per la **pila**.
    
- Per ottenere lo stesso ordine di visita, è necessario **scandire al contrario l’insieme A(u)**, in modo che **l’ultimo nodo inserito nella pila** sia quello visitato **alla prossima iterazione**.
    

---

### **7. Sintesi finale**

|Algoritmo|Struttura usata|Tipo di visita|Complessità|Note|
|---|---|---|---|---|
|**DFS**|Pila (ricorsiva o iterativa)|In profondità|$O(n + m)$|Approccio top-down, utile per scoprire cammini|
|**BFS**|Coda|In ampiezza|$O(n + m)$|Approccio per livelli, utile per calcolare distanze|

---

> 🧠 **In sintesi:**  
> DFS e BFS sono due facce della stessa medaglia: entrambe percorrono l’intero grafo in tempo ottimo, ma lo fanno con strategie opposte.  
> La prima scava in profondità, la seconda si allarga in ampiezza.  
> Saperle applicare e alternare significa saper “vedere” il grafo in 3D: **una struttura viva da esplorare, non solo da rappresentare.**
