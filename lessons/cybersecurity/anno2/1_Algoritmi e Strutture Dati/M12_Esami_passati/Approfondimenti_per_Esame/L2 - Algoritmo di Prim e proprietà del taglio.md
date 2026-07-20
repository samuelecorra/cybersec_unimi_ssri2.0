# Algoritmo di Prim e proprietà del taglio

### **1. Albero ricoprente minimo**

Sia $G=(V,E,w)$ un grafo non orientato, connesso e pesato. Un **albero ricoprente** è un sottografo $T=(V,E_T)$ connesso, aciclico e contenente tutti i vertici; ha esattamente $|V|-1$ archi. Un **minimum spanning tree** (MST) minimizza

$$
w(T)=\sum_{e\in E_T}w(e).
$$

L'MST non è necessariamente unico: pesi uguali possono consentire più alberi ottimi.

### **2. Tagli e archi che attraversano un taglio**

Un **taglio** è una partizione dei vertici in due insiemi non vuoti $S$ e $V\setminus S$. Un arco $(u,v)$ **attraversa** il taglio se un estremo è in $S$ e l'altro in $V\setminus S$.

> 📌 Proprietà del taglio: dato un taglio che rispetta gli archi già scelti, un arco di peso minimo tra quelli che lo attraversano è sicuro, cioè può appartenere a un MST che contiene gli archi già scelti.

#### **2.1. Dimostrazione per scambio**

Sia $e$ un arco leggero del taglio e sia $T$ un MST compatibile con gli archi già scelti. Se $e\in T$, non c'è nulla da provare. Se $e\notin T$, aggiungere $e$ a $T$ crea un unico ciclo. Poiché $e$ attraversa il taglio, nel ciclo esiste almeno un altro arco $f$ che attraversa il medesimo taglio. Essendo $e$ leggero, $w(e)\le w(f)$. Allora

$$
T'=T-\{f\}+\{e\}
$$

è ancora un albero ricoprente e $w(T')\le w(T)$. Dall'ottimalità di $T$, anche $T'$ è un MST e contiene $e$.

### **3. Algoritmo di Prim**

Prim fa crescere un solo albero a partire da un vertice $s$. A ogni passo considera il taglio $(S,V\setminus S)$, dove $S$ contiene i vertici già raggiunti, e sceglie un arco di peso minimo che lo attraversa.

```text
PRIM(G, s)
    per ogni v in V
        key[v] <- +infinito
        parent[v] <- NIL
    key[s] <- 0
    Q <- coda di priorità contenente V

    mentre Q non è vuota
        u <- ESTRAI-MIN(Q)
        per ogni arco {u,v} con v ancora in Q
            se w(u,v) < key[v]
                parent[v] <- u
                key[v] <- w(u,v)
                DECREMENTA-CHIAVE(Q,v,key[v])
```

Gli archi $\{parent[v],v\}$ per $v\ne s$ formano l'MST. `key[v]` non è una distanza dalla sorgente: è il peso del più economico arco noto che collega $v$ all'albero corrente.

#### **3.1. Esempio**

Con archi $AB=2$, $AC=5$, $BC=1$, $BD=4$, $CD=3$ e partenza $A$:

1. $S=\{A\}$: si sceglie $AB=2$;
2. $S=\{A,B\}$: si sceglie $BC=1$;
3. $S=\{A,B,C\}$: si sceglie $CD=3$.

L'albero ha peso $2+1+3=6$. Ogni scelta è giustificata dalla proprietà del taglio corrente.

### **4. Correttezza e complessità**

L'invariante è: dopo ogni iterazione, gli archi scelti sono contenuti in almeno un MST. L'arco aggiunto è leggero sul taglio tra vertici estratti e non estratti, quindi è sicuro per la proprietà del taglio. Dopo $|V|-1$ aggiunte si ottiene un albero ricoprente minimo.

- matrice di adiacenza e ricerca lineare del minimo: $\Theta(|V|^2)$;
- liste di adiacenza e heap binario: $O((|V|+|E|)\log|V|)=O(|E|\log|V|)$ per grafi connessi;
- heap di Fibonacci: $O(|E|+|V|\log|V|)$.

### **5. Prim e Kruskal a confronto**

Prim mantiene un unico albero e sceglie il miglior arco uscente dal taglio corrente. [Kruskal](../../M08_Greedy/UD2/L1_Algoritmo_di_Kruskal.md) mantiene invece una foresta, considera globalmente gli archi in ordine di peso e usa MFSET per evitare cicli. Entrambi si fondano sulla sicurezza degli archi leggeri, ma costruiscono la soluzione con invarianti diversi.

> ⚠️ Prim non è Dijkstra: la scelta di Prim minimizza il peso del singolo arco di collegamento; Dijkstra minimizza la distanza totale provvisoria dalla sorgente.

> ✅ Il taglio è lo strumento teorico che giustifica la scelta greedy di Prim e, in forma affine, quella di Kruskal.
