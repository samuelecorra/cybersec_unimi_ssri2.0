## **Lezione 2: Il problema della massima diversità**

### **1. Introduzione**

#### **1.1 Obiettivo della lezione**

In questa lezione analizziamo il **problema della massima diversità** e vedremo come progettare un **algoritmo greedy efficiente** per risolverlo.  
Particolare attenzione sarà data al ruolo dell’**ordinamento**, che nello schema generale di un algoritmo greedy serve come **strumento pratico** per applicare il criterio di ottimalità scelto.

Tuttavia, in questo caso specifico, vedremo che **il criterio di ottimalità non coincide con un semplice ordinamento**, perché i valori da ordinare **dipendono dinamicamente** dalle scelte già fatte nella costruzione della soluzione.

---

#### **1.2 Formulazione del problema**

Sia dato un insieme di elementi:

$$  
N = { e_i : i = 1, \dots, n }  
$$

Per ogni coppia di elementi $(e_i, e_j)$ è definita una **funzione di diversità**:

$$  
D : (e_i, e_j) \mapsto d_{ij}  
$$

dove $d_{ij} \ge 0$ e, naturalmente, $d_{ii} = 0$.

La **diversità totale** di un sottoinsieme $M \subseteq N$ è definita come:

$$  
z(M) = \frac{1}{2} \sum_{e_i \in M} \sum_{e_j \in M} d_{ij}  
$$

La divisione per 2 serve a non contare due volte ogni coppia $(i,j)$ e $(j,i)$.

**Obiettivo:**  
dato un insieme $N$ di $n$ elementi e un numero $m < n$, vogliamo selezionare un sottoinsieme $M$ di $m$ elementi che **massimizzi la funzione di diversità** $z(M)$.

---

#### **Interpretazione grafica**

Possiamo rappresentare il problema anche tramite un **grafo completo e pesato** $G = (N, A)$ dove:

- ogni nodo $i \in N$ rappresenta un elemento $e_i$;
    
- ogni arco $[i, j] \in A$ ha peso $d_{ij}$, che rappresenta la diversità tra $e_i$ ed $e_j$.
    

Un sottoinsieme $M$ di nodi forma una **cricca** nel grafo $G$ (cioè un insieme di nodi tutti collegati tra loro).  
Il problema della massima diversità consiste dunque nel **trovare la cricca di $m$ nodi con somma di pesi massima**, cioè quella che massimizza $z(M)$.

---

### **2. Progetto di un algoritmo greedy efficiente**

#### **2.1 Primo schema: `simpleGreedy`**

L’idea di base è aggiungere, passo dopo passo, **il nodo che aumenta maggiormente la diversità totale**.

Per misurare il contributo di un nodo $i$ non ancora incluso nella soluzione $M$, definiamo:

$$  
D_i = \sum_{j \in M} d_{ij}  
$$

cioè la **somma delle diversità** tra il nodo $i$ e tutti i nodi già inclusi in $M$.

A ogni passo scegliamo il nodo **con valore $D_i$ massimo** tra quelli non ancora scelti.  
La costruzione della soluzione continua finché non abbiamo inserito $m$ nodi.

---

##### **Schema base dell’algoritmo**

```c
void simpleGreedy(grafo G, int m, insieme *M, int *z) {
    int card = 1;
    creainsieme(M);

    k = argmax { ∑_{j∈A(i)} d_ij : i ∈ N };
    inserisci(k, M);
    z = 0;

    while (card <= m) {
        k = argmax { D_i : i ∈ N \ M };
        inserisci(k, M);
        z = z + D_k;
        card++;
    }
}
```

L’insieme $M$ viene inizializzato con il nodo $k$ che ha **la somma totale di diversità massima** verso tutti gli altri nodi, cioè:

$$  
k = \arg\max_i \sum_{j \in A(i)} d_{ij}  
$$

---

##### **Analisi della complessità**

- Il ciclo `while` si ripete al massimo $(m - 1)$ volte.
    
- Gli operatori `inserisci` e `appartiene` (se implementati con un vettore booleano) hanno costo $O(1)$.
    
- Ad ogni iterazione, per calcolare tutti i $D_i$ bisogna scansionare i valori $d_{ij}$ tra i nodi in $M$ e quelli non in $M$.
    

Il numero totale di operazioni corrisponde alla somma:

$$  
\sum_{i=1}^{m-1} (n - i)i  
$$

Poiché $(n - i) < n$, si ha:

$$  
\sum_{i=1}^{m-1} (n - i)i < n \sum_{i=1}^{m-1} i = n \frac{(m - 1)m}{2}  
$$

Quindi la **complessità complessiva** risulta:

$$  
O(n m^2)  
$$

---

#### **2.2 Migliorare l’efficienza**

L’obiettivo ora è **aggiornare i valori $D_i$ in modo incrementale**, evitando di ricalcolarli da zero a ogni iterazione.

Osserviamo che:

- Se $M = \emptyset$, allora $D_i = 0 \quad \forall i \in N$
    
- Se $M = {j}$, allora $D_i = d_{ij}$
    
- Se aggiungiamo $k$ a $M$, allora per ogni $i \in N \setminus M$:
    

$$  
D'_i = D_i + d_{ik}  
$$

Quindi ad ogni passo aggiorniamo semplicemente i valori $D_i$ **sommando** il contributo del nuovo nodo $k$.

In questo modo l’aggiornamento costa solo $O(n)$ invece di $O(nm)$ per iterazione.

---

##### **Schema dell’algoritmo efficiente**

```c
void efficientGreedy(grafo G, int m, insieme *M, int *z) {
    card = 1;
    int *D;
    D = (int *) malloc(n * sizeof(int));
    creainsieme(M);

    k = argmax { ∑_{j∈A(i)} d_ij : i ∈ N };
    inserisci(k, M);
    for (i = 0; i < n; i++) D[i] = d_ik;
    z = 0;

    while (card < m) {
        k = argmax { D_i : i ∈ N \ M };
        inserisci(k, M);
        z = z + D_k;
        for (i = 0; i < n; i++)
            if (!appartiene(i, M))
                D[i] = D[i] + d_ik;
        card++;
    }
}
```

---

##### **Analisi della complessità**

All’interno del ciclo `while`, ripetuto $O(m)$ volte:

- selezione dell’elemento $k$: $O(n)$
    
- inserimento e aggiornamenti vari: $O(1)$
    
- aggiornamento dei $D_i$: $O(n)$
    

Quindi la complessità complessiva è:

$$  
O(n m)  
$$

Il numero totale di operazioni per aggiornare i $D_i$ è:

$$  
\sum_{i=1}^{m-1} (n - i)  
$$

che, a confronto con la formula precedente $\sum (n - i)i$, mostra la riduzione di complessità da $O(n m^2)$ a $O(n m)$.

---

#### **2.3 Uso di uno heap**

Si potrebbe pensare di usare una **coda di priorità** (heap) per rendere più veloce la selezione del nodo $k$ con valore massimo di $D_i$, riducendo il costo da $O(n)$ a $O(\log n)$.

Tuttavia, poiché i valori $D_i$ **cambiano a ogni iterazione**, lo heap andrebbe **ricostruito ogni volta**, operazione che costa $O(n)$ (come visto nell’algoritmo HeapSort).

Di conseguenza, l’uso di uno heap **non migliora l’ordine di complessità complessivo**.

---

### **3. Conclusioni**

Abbiamo studiato il **problema della massima diversità**, fornendo:

1. una **formulazione matematica e grafica** del problema,
    
2. una **prima versione greedy** con complessità $O(n m^2)$,
    
3. una **versione ottimizzata** con calcolo incrementale dei contributi, di complessità $O(n m)$.
    

Infine, abbiamo visto che l’uso di strutture dati più complesse (come heap) non migliora ulteriormente l’efficienza, poiché i valori di priorità ($D_i$) **variano dinamicamente** a ogni passo.

L’approccio greedy rimane quindi una soluzione efficace e semplice per ottenere **buone approssimazioni** al problema della massima diversità.