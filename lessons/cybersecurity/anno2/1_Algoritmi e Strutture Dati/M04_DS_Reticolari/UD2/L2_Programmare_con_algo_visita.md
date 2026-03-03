## **Lezione 2: Programmare con gli algoritmi di visita**

---

### **1. Obiettivo della lezione**

Questa lezione applica in modo operativo gli algoritmi di **visita dei grafi** per risolvere due veri **problemi computazionali**.  
Lo schema di riferimento è lo stesso del Modulo 1 (“Soluzione di un semplice problema computazionale”), ma qui il punto di partenza è un **grafo in input** che deve essere trasformato in un **nuovo grafo** con determinate proprietà.

Gli obiettivi principali sono:

- comprendere **come modificare gli algoritmi di visita** per produrre nuovi grafi;
    
- mantenere una **complessità ottima** (lineare rispetto al numero di nodi e archi).
    

---

### **2. I due problemi da risolvere**

#### **Problema 1 – Creazione del grafo trasposto**

Dato un grafo orientato $G = (N, A)$, vogliamo costruire il grafo trasposto $G^T = (N, A^T)$, dove:

$$  
A^T = { (v, u) \ | \ (u, v) \in A }  
$$

Cioè: ogni arco del grafo originale viene **invertito di direzione**.

---

#### **Problema 2 – Orientamento di un grafo non orientato**

Dato un grafo **non orientato** $G = (N, A)$, vogliamo ottenere un grafo **orientato** $G^O = (N, A^O)$, dove:

$$  
A^O = { (u, v), (v, u) \ | \ [u, v] \in A }  
$$

In pratica, ogni arco bidirezionale $[u, v]$ del grafo non orientato viene sostituito da **due archi orientati** $(u, v)$ e $(v, u)$.

---

### **3. Complessità dei problemi**

In entrambi i casi, gli algoritmi devono:

- leggere **tutti i nodi** del grafo ($n$);
    
- scandire **tutti gli archi** ($m$);
    
- e creare un nuovo grafo con le stesse dimensioni.
    

È quindi inevitabile una complessità minima teorica di:

$$  
\Omega(n + m)  
$$

Un algoritmo ottimo sarà dunque quello che mantiene **questa complessità lineare**, senza sprechi.

---

### **4. Algoritmo per il grafo trasposto**

```c
grafo* creaGrafoTrasposto(grafo G, nodo u) {
    nodo v;
    coda Q;
    grafo *GT;

    creagrafo(GT);
    creacoda(Q);
    incoda(u, Q);

    while (!codavuota(Q)) {
        u = leggicoda(Q);
        fuoricoda(Q);

        if (u ∉ GT)
            insnodo(u, GT);

        for each v ∈ A(u) {
            if (v ∉ GT)
                insnodo(v, GT);

            insarco(v, u, GT);   // arco invertito

            if (v non visitato && v ∉ Q)
                incoda(v, Q);
        }
    }

    return GT;
}
```

#### **Logica dell’algoritmo**

- È una **modifica dell’algoritmo BFS**: mentre esplora gli archi $(u, v)$, li **inverte** in fase di inserimento nel nuovo grafo $G^T$.
    
- Ogni nodo o arco viene analizzato una sola volta.
    
- Gli operatori `insnodo` e `insarco` vengono chiamati solo quando necessario.
    

#### **Complessità**

Ogni operazione di visita o inserimento è in **tempo costante $O(1)$**, quindi:

$$  
T(n, m) = O(n + m)  
$$

---

### **5. Algoritmo per il grafo orientato da un non orientato**

```c
grafo* orientaGrafo(grafo G, nodo u) {
    nodo v;
    coda Q;
    grafo *G0;

    creagrafo(G0);
    creacoda(Q);
    incoda(u, Q);

    while (!codavuota(Q)) {
        u = leggicoda(Q);
        fuoricoda(Q);

        if (u ∉ G0)
            insnodo(u, G0);

        for each v ∈ A(u) {
            if (v ∉ G0)
                insnodo(v, G0);

            insarco(u, v, G0);   // arco orientato

            if (v non visitato && v ∉ Q)
                incoda(v, Q);
        }
    }

    return G0;
}
```

#### **Osservazioni**

- Anche questo algoritmo è una **visita BFS modificata**.
    
- Quando si esplora il nodo `u`, si inseriscono gli archi $(u, v)$ ma **non** serve aggiungere anche $(v, u)$, perché verrà aggiunto durante la scansione di `A(v)`.
    
- Il vettore `visitato[]` assicura che ogni nodo sia gestito una sola volta.
    

#### **Vettore di controllo**

$$

visitato[u] =
\begin{cases}
\text{FALSE}, & \text{se } u \text{ non è stato visitato} \\
\text{TRUE}, & \text{se } u \text{ è stato visitato}
\end{cases}

$$

---

### **6. Complessità e ottimalità**

Entrambi gli algoritmi sono **lineari** nel numero di nodi e archi:

$$  
O(n + m)  
$$

Questo perché:

- ogni arco viene visitato una sola volta;
    
- l’appartenenza alla coda o al grafo è verificata in **tempo costante**;
    
- nessuna operazione ridondante viene effettuata.
    

> Questi algoritmi sono quindi **ottimi**, raggiungendo il limite inferiore teorico di complessità.

---

### **7. Sintesi finale**

|Problema|Tipo di grafo|Operazione|Algoritmo|Complessità|
|---|---|---|---|---|
|**1. Grafo trasposto**|Orientato|Inversione archi|BFS modificata|$O(n + m)$|
|**2. Grafo orientato da non orientato**|Non orientato|Duplicazione archi orientati|BFS modificata|$O(n + m)$|

---

> 🧠 **In sintesi:**  
> Entrambi i problemi mostrano la potenza degli algoritmi di visita: basta modificarne il comportamento interno per trasformare strutture complesse mantenendo l’efficienza ottimale.  
> DFS e BFS non servono solo per esplorare — **sono veri strumenti di costruzione computazionale.**
