In questo modulo si studia come la **scelta della struttura dati** influenzi in modo diretto l’**efficienza** e la **complessità** di un algoritmo.  
Due algoritmi che risolvono lo stesso problema possono avere prestazioni molto diverse a seconda di **come vengono rappresentati e gestiti i dati**.  
Si analizzeranno diversi esempi pratici, in particolare il **problema dei cammini minimi**, per comprendere come differenti strutture (liste di adiacenza, matrici, code di priorità, ecc.) modifichino i tempi di esecuzione.  
Il modulo introduce inoltre il concetto di **problema computazionale**, la sua **classificazione in base all’obiettivo**, e fornisce gli strumenti per **progettare algoritmi efficienti** capaci di risolvere problemi complessi in modo ottimale.


---

## **Lezione 1: Il problema dei cammini minimi**

### **1. Introduzione**

In questa lezione introduciamo il **problema del cammino minimo**, conosciuto anche come **Shortest Path Tree (SPT)**.  
L’obiettivo è determinare, in un grafo orientato e pesato, il **percorso più breve** dal nodo sorgente verso tutti gli altri nodi.

Per raggiungere questo obiettivo, utilizzeremo le **condizioni di Bellman**, che ci permettono di caratterizzare matematicamente una soluzione ottima e di costruire una **procedura generale** per risolvere il problema.  
Da questa procedura deriveranno poi i diversi algoritmi specifici per i cammini minimi, come quelli di **Dijkstra**, **Johnson**, **Bellman-Ford-Moore**, **con pila** e **Pape-D’Esopo**.

Durante la lezione vedremo:

1. la **definizione formale** del problema,
    
2. le **condizioni di Bellman**,
    
3. la **procedura SPT** per generare l’albero dei cammini minimi.
    

---

### **2. Definizione del problema**

Sia dato un **grafo orientato** $G = (N, A)$, dove:

- $N$ è l’insieme dei nodi,
    
- $A$ è l’insieme degli archi,
    
- ad ogni arco $(i, j) \in A$ è associato un **peso** $c_{ij}$, che rappresenta la distanza, il costo o il tempo di percorrenza tra $i$ e $j$.
    

Dato un nodo sorgente $r \in N$, vogliamo trovare per ogni nodo $u \in N$ un **cammino** $P_{ru}$ da $r$ a $u$ tale che la **somma dei pesi** sugli archi di $P_{ru}$ sia la più piccola possibile.

Il **costo** di un cammino $P_{ru}$ è definito come:

$$  
C(P_{ru}) = \sum_{(i,j) \in P_{ru}} c_{ij}  
$$

L’obiettivo del problema è quindi determinare, per ogni nodo $u$, un cammino $P_{ru}$ che **minimizzi** $C(P_{ru})$.

---

### **3. Esempio intuitivo**

Consideriamo un grafo che rappresenta una rete di città collegate da strade, dove ogni arco ha un peso pari alla lunghezza del tratto.  

![](imgs/Pasted%20image%2020260722215650.png)

Supponiamo di voler trovare il percorso più breve dal nodo 1 al nodo 6.

Esempio di due possibili cammini:

- $P_{16} = \{(1,3), (3,4), (4,6)\}$ con costo $C(P_{16}) = 7$
    
- $P_{16} = \{(1,2), (2,5), (5,4), (4,6)\}$ con costo $C(P_{16}) = 6$
    

Poiché $6 < 7$, il cammino minimo è il secondo, con costo $6$.

---

### **4. Caratterizzazione della soluzione**

Una **soluzione ammissibile** al problema dei cammini minimi è composta da $n-1$ cammini, uno per ogni nodo diverso dal sorgente $r$.  
Affinché la soluzione sia valida, devono essere rispettate due condizioni fondamentali:

1. Ogni nodo $u \in N \setminus \{r\}$ deve essere raggiungibile da $r$.
    
2. Non devono esistere **cicli di costo negativo**.
    

Un ciclo negativo è un percorso chiuso il cui costo totale è minore di zero.  
Se nel grafo esiste un ciclo di costo negativo, non è possibile ottenere una soluzione ottimale limitata, perché aggiungendo un giro del ciclo si può ottenere un costo ancora più basso all’infinito.

---

### **5. Struttura della soluzione**

Analizzando tutti i cammini che partono dal nodo sorgente $r$, si osserva che:

- due cammini distinti possono **coincidere nella parte iniziale**,
    
- ma non possono coincidere nella parte finale, altrimenti esisterebbero due cammini distinti che raggiungono lo stesso nodo.
    

Da questa osservazione segue che la soluzione ammissibile può essere rappresentata come un **albero di copertura** $T$ del grafo $G$, **radicato in $r$**, che contiene un cammino minimo da $r$ a ogni nodo $u \in N \setminus \{r\}$.

---

### **6. Etichette e condizioni di Bellman**

Associamo a ogni nodo $u \in N$ un’etichetta $d_u$ che rappresenta la distanza minima da $r$ a $u$.

$$  
d_u = C(P_{ru}) = \sum_{(i,j) \in P_{ru}} c_{ij}  
$$

Per esempio, in un grafo con nodo sorgente $1$, potremmo avere:  
$d_1 = 0$, $d_2 = 1$, $d_5 = 2$, $d_4 = 4$, $d_6 = 6$.

![](imgs/Pasted%20image%2020260722215744.png)

Il **Teorema di Bellman** afferma che la soluzione ammissibile individuata dall’albero $T$ è **ottima se e solo se** per ogni arco $(i,j) \in A$ valgono le seguenti condizioni:

$$
\begin{cases}
d_i + c_{ij} = d_j, & \text{se } (i,j) \in T \\
d_i + c_{ij} \ge d_j, & \text{se } (i,j) \notin T
\end{cases}
$$

In altre parole:

- se l’arco appartiene all’albero, il costo del cammino minimo verso $j$ è esattamente quello che si ottiene passando da $i$;
    
- se non appartiene all’albero, non deve permettere un cammino più conveniente.
    

---

### **7. Schema dell’algoritmo SPT**

Il principio dell’algoritmo generale SPT è di verificare, **arco per arco**, se le condizioni di Bellman sono rispettate.  
Ogni volta che troviamo un arco $(i,j)$ tale che $d_i + c_{ij} < d_j$, significa che abbiamo trovato un percorso migliore: aggiorniamo quindi $d_j$ e modifichiamo l’albero $T$.

L’idea alla base è:

1. Finché esiste un arco $(i,j)$ che viola le condizioni di Bellman, si aggiorna $d_j = d_i + c_{ij}$.
    
2. L’arco $(i,j)$ viene aggiunto all’albero al posto del precedente arco entrante in $j$.
    

In pseudocodice, lo schema generale si traduce come segue:

```c
void SPT( grafo G, nodo r ) {
    { T albero di copertura di G radicato in r };

    while ( ∃ (i,j) : d_i + c_ij < d_j ) {    // violazione delle condizioni di Bellman
        d_j = d_i + c_ij;
        T = T ∪ {(i,j)} \ {(h,j)};             // (h,j): arco incidente in j prima della sostituzione
    }
}
```

L’arco $(h,j)$ è l’arco che, prima dell’aggiornamento, entrava in $j$ all’interno di $T$: la sostituzione $T \cup \{(i,j)\} \setminus \{(h,j)\}$ realizza esattamente il passo 2 appena descritto, rimpiazzando il vecchio arco entrante con quello nuovo appena scoperto.

Questo processo continua finché tutte le condizioni sono verificate, e quindi l’albero risultante rappresenta l’insieme dei cammini minimi.

---

### **8. Implementazione operativa**

Il passo cruciale dell’algoritmo è la **selezione dell’arco da aggiornare**.  
Per farlo, si introduce un insieme $S$ di nodi, inizialmente contenente solo il nodo sorgente $r$.

La logica di esecuzione è la seguente:

1. Si estrae un nodo $u$ da $S$.
    
2. Si visitano tutti gli archi $(u, v)$ uscenti da $u$.
    
3. Se l’arco $(u, v)$ viola la condizione di Bellman, cioè se $d_u + c_{uv} < d_v$, allora si aggiorna $d_v$ e si inserisce $v$ in $S$ per verificare se da esso possono derivare ulteriori miglioramenti.
    

Inizialmente tutte le etichette $d_u$ sono poste a un valore molto alto (ad esempio infinito), tranne $d_r = 0$.

Il codice seguente traduce questa logica in pseudocodice, esplicitando anche l’inizializzazione dell’albero $T$ (qui rappresentato tramite il vettore dei padri) e la gestione dell’insieme $S$ con gli operatori del tipo di dato astratto **insieme** visto nel Modulo 5:

```c
void SPT( grafo G, nodo r ) {
    albero T; insieme S; int d[n], k; nodo u, v;

    for ( k = 0; k < n; k++ ) {              // inizializzazione etichette e vettore dei padri
        T[k] = r; d[k] = MAXINT;
    }
    T[r] = r; d[r] = 0;

    creainsieme( S ); inserisci( r, S );      // inizializzazione insieme S

    while ( !insiemevuoto(S) ) {
        u = leggi(S); cancella( u, S );       // estrazione da insieme S

        for each v ∈ A(u)
            if ( d[u] + c[u,v] < d[v] ) {      // visita adiacenti di u; verifica condizioni di Bellman
                T[v] = u;
                d[v] = d[u] + c[u,v];          // aggiornamento etichetta
                if ( v ∉ S ) inserisci(v, S);
            }
    }
}
```

Il vettore `T[]` funge da **vettore dei padri**: `T[v] = u` significa che, nell’albero dei cammini minimi, il padre di `v` è `u`, cioè l’arco $(u,v)$ è quello scelto per raggiungere $v$. L’operatore `A(u)` (introdotto nel Modulo 4) restituisce i nodi adiacenti a `u`, mentre `creainsieme`, `insiemevuoto`, `inserisci` e `cancella` sono gli operatori generici dell’insieme $S$: la loro **realizzazione concreta** (lista, coda, pila o heap) è proprio ciò che distinguerà, nelle prossime lezioni, gli algoritmi di Dijkstra, Johnson, Bellman-Ford-Moore, con pila e Pape-D’Esopo.

---

### **9. Verso la prossima lezione**

Nelle prossime lezioni dimostreremo formalmente il **Teorema di Bellman** e vedremo come la scelta della **struttura dati** per gestire l’insieme $S$ influenzi la complessità dell’algoritmo.  
Analizzeremo in particolare gli algoritmi di **Dijkstra**, **Johnson**, **Bellman-Ford-Moore**, **con pila** e **Pape-D’Esopo**, confrontandone le prestazioni e i contesti di applicazione.