## **Lezione 3: Programmare con i grafi**

---

### **1. Obiettivo della lezione**

Questa lezione mostra **come utilizzare gli operatori fondamentali dei grafi** per costruire un esempio concreto di grafo orientato e per **analizzarne la complessità**.  
Seguendo lo schema visto nel Modulo 1 (“Soluzione di un semplice problema computazionale”), ci eserciteremo a progettare un algoritmo efficiente che genera un grafo con precise regole di collegamento tra i nodi.

---

### **2. Il problema**

Vogliamo creare un **grafo orientato** composto da:

$$  
N = {1, 2, 3, \dots, n}  
$$

e un insieme di archi $A$ tale che:

- ogni **nodo pari** abbia un arco verso **tutti i nodi dispari maggiori** di sé;
    
- ogni **nodo dispari** abbia un arco verso **tutti i nodi pari maggiori** di sé.
    

In altre parole, il grafo alterna connessioni tra pari e dispari in modo sistematico e crescente.

---

### **3. Esempio grafico**

Per $n = 7$:

- i nodi pari sono $2, 4, 6$
    
- i nodi dispari sono $1, 3, 5, 7$
    

**Archi da pari a dispari:**

- 2 → 3, 5, 7
    
- 4 → 5, 7
    
- 6 → 7
    

**Archi da dispari a pari:**

- 1 → 2, 4, 6
    
- 3 → 4, 6
    
- 5 → 6
    

Totale archi: $(3 + 2 + 1) + (3 + 2 + 1) = 12$

---

### **4. Complessità del problema**

La creazione di un grafo con $n$ nodi e $m$ archi implica due operazioni fondamentali:

- **inserire i nodi** → $O(n)$
    
- **inserire gli archi** → $O(m)$
    

Pertanto, la **complessità minima teorica** del problema è:

$$  
\Omega(n + m)  
$$

Ogni algoritmo corretto per costruire il grafo dovrà necessariamente visitare tutti i nodi e creare tutti gli archi.

---

### **5. Implementazione in C**

Ecco la procedura per costruire il grafo descritto:

```c
grafo *creaGrafo(int n) {
    grafo *G;
    nodo ui, uj;
    int i, j;

    creagrafo(G);

    // Inserimento dei nodi
    for (i = 1; i <= n; i++)
        insnodo(ui, G);

    // Archi da dispari a pari
    for (i = 1; i < n; i += 2)
        for (j = i + 1; j <= n; j += 2)
            insarco(ui, uj, G);

    // Archi da pari a dispari
    for (i = 2; i < n; i += 2)
        for (j = i + 1; j <= n; j += 2)
            insarco(ui, uj, G);

    return G;
}
```

#### **Analisi del codice**

1. **Creazione del grafo** – inizializza la struttura dati.
    
2. **Primo ciclo `for`** – inserisce tutti i nodi $N = {1, ..., n}$.
    
3. **Secondo ciclo** – collega ogni nodo dispari ai pari successivi.
    
4. **Terzo ciclo** – collega ogni nodo pari ai dispari successivi.
    

---

### **6. Analisi della complessità dell’algoritmo**

L’algoritmo presenta **tre cicli principali**:

1. **Primo ciclo:**  
    Inserisce $n$ nodi → $O(n)$
    
2. **Secondo e terzo ciclo:**  
    Inseriscono in totale $m$ archi → $O(m)$
    
3. **Operatori elementari (`insnodo`, `insarco`, ecc.)** → ciascuno in $O(1)$
    

Quindi la **complessità complessiva** è:

$$  
O(n + m)  
$$

L’algoritmo è **ottimo**, perché raggiunge il limite inferiore teorico per la costruzione del grafo.

---

### **7. Stima del numero di archi $m$**

Per $n = 7$ (caso dispari):

- **Nodi pari:**  
    2 → 3, 5, 7 → 3 archi  
    4 → 5, 7 → 2 archi  
    6 → 7 → 1 arco
    
    Totale pari → $3 + 2 + 1 = 6$ archi
    
- **Nodi dispari:**  
    1 → 2, 4, 6 → 3 archi  
    3 → 4, 6 → 2 archi  
    5 → 6 → 1 arco
    
    Totale dispari → $3 + 2 + 1 = 6$ archi
    

Totale generale:

$$  
m = 6 + 6 = 12  
$$

In generale, per $n$ dispari, vale:

$$  
m = O(n^2)  
$$

Poiché il numero totale di archi cresce quadraticamente con il numero di nodi.

---

### **8. Sintesi conclusiva**

|Aspetto|Descrizione|
|---|---|
|**Obiettivo**|Costruire un grafo orientato con connessioni tra pari e dispari|
|**Operatori**|`creagrafo`, `insnodo`, `insarco`|
|**Numero di nodi**|$n$|
|**Numero di archi**|$m = O(n^2)$|
|**Complessità totale**|$O(n + m)$|
|**Ottimalità**|Sì, l’algoritmo raggiunge la complessità minima teorica|

---

> 🧠 **In sintesi:**  
> Questa lezione chiude il cerchio concettuale sui grafi, mostrando come passare dalla **definizione formale** alla **costruzione operativa**.  
> I grafi non sono solo concetti matematici, ma strumenti algoritmici: da un’idea di collegamento logico, sappiamo ora tradurre in codice una rete complessa — e analizzarne la complessità con metodo.