## **Lezione 1: Il problema dei cammini minimi (parte 2)**

### **1. Introduzione**

In questa seconda parte della lezione dimostriamo formalmente il **Teorema di Bellman**, che abbiamo già utilizzato per derivare la procedura generale **SPT** (Shortest Path Tree).  
Vedremo come le **condizioni di Bellman** non siano solo un criterio empirico, ma il risultato diretto di una **dimostrazione rigorosa** in due direzioni, basata su un ragionamento logico e per assurdo.

Gli argomenti che tratteremo sono:

1. richiamo della definizione di etichette $d_u$
    
2. enunciazione del teorema di Bellman
    
3. dimostrazione nelle due direzioni: $(\Rightarrow)$ e $(\Leftarrow)$
    

---

### **2. Etichette $d_u$**

Ad ogni nodo $u \in N$ associamo un’etichetta $d_u \in \mathbb{Z}$, definita come la **distanza di $u$ dal nodo sorgente $r$** all’interno dell’albero $T$.

Formalmente:

$$  
d_u = C(P_{ru}) = \sum_{(i,j) \in P_{ru}} c_{ij}  
$$

Esempio:  
Supponiamo un grafo con nodo sorgente $1$ e pesi come segue:  
$d_1 = 0$, $d_2 = 1$, $d_5 = 2$, $d_4 = 4$, $d_6 = 6$.

Queste etichette rappresentano i **costi minimi parziali** dei cammini dall’origine a ciascun nodo.

---

### **3. Teorema di Bellman**

Il **Teorema di Bellman** afferma che:

> La soluzione ammissibile individuata dal generico albero di copertura $T$ è **ottima**  
> se e solo se, per ogni arco $(i,j) \in A$, valgono le seguenti condizioni:

$$
\begin{cases}
d_i + c_{ij} = d_j, & \text{se } (i,j) \in T \\
d_i + c_{ij} \ge d_j, & \text{se } (i,j) \notin T
\end{cases}
$$

Intuitivamente, la prima condizione esprime il fatto che tutti gli archi **dell’albero** rappresentano esattamente i cammini minimi, mentre la seconda garantisce che **nessun altro arco esterno** può migliorare le distanze trovate.

---

### **4. Dimostrazione (⇒)**

**Tesi:**  
Se $T$ è un albero ottimo, allora valgono le condizioni (a) e (b).

**Dimostrazione:**  
Consideriamo due casi.

1. **Se $(i,j) \in T$:**  
    Poiché $T$ è ottimo, il cammino minimo da $r$ a $j$ passa necessariamente per $i$.  
    Quindi $d_i + c_{ij} = d_j$.  
    La condizione (a) è verificata.
    
2. **Se $(i,j) \notin T$:**  
    Affinché $T$ sia ottimo deve valere la (b).  
    Supponiamo invece, per assurdo, che (b) sia falsa, cioè che  
    $d_i + c_{ij} < d_j$.  
    Allora esisterebbe un **cammino alternativo** che collega $r$ a $j$ con costo minore di $d_j$, quindi migliore di quello presente in $T$.  
    Ma questo contraddice l’ipotesi che $T$ sia un albero ottimo.
    

Conclusione: le due condizioni (a) e (b) devono necessariamente valere.

---

### **5. Dimostrazione (⇐) — Parte 1**

**Tesi:**  
Se valgono le condizioni (a) e (b), allora l’albero $T$ è ottimo.

**Dimostrazione:**  
Supponiamo per assurdo che esista un nodo $u$ per cui il cammino $P_{ru}$ individuato da $T$ **non sia ottimo**.  
Allora deve esistere un **cammino alternativo** $P'_{ru}$ con costo minore, cioè:

$$  
d'_u < d_u  
$$

Sia $d'_j$ la distanza di un generico nodo $j$ lungo il cammino alternativo $P'_{ru}$.

---

### **6. Dimostrazione (⇐) — Parte 2**

Osserviamo che il nodo sorgente è lo stesso, quindi $d_r = d'_r = 0$.  
Se $d'_u < d_u$, allora lungo il cammino alternativo deve esistere almeno **un arco** $(h, k)$ tale che:

- le distanze dei nodi $h$ e $k$ risultano “invertite” rispetto alla soluzione ottima, cioè  
    $d_h \neq d'_h$ e $d_k > d'_k$.
    

In particolare, valgono due relazioni:

- **per costruzione:**  
    $d'_h + c_{hk} = d'_k$
    
- **per ipotesi (condizioni di Bellman):**  
    $d_h + c_{hk} \ge d_k$
    

---

### **7. Dimostrazione (⇐) — Parte 3**

Combinando le due relazioni, otteniamo:

$$  
d'_k = d'_h + c_{hk} \ge d_h + c_{hk} \ge d_k  
$$

Da cui segue:

$$  
d'_k \ge d_k  
$$

Ma ciò contraddice l’ipotesi iniziale $d_k > d'_k$.  
Abbiamo quindi raggiunto un **assurdo**: l’esistenza di un cammino migliore $P'_{ru}$ è impossibile.

Pertanto, se le condizioni (a) e (b) sono verificate, l’albero $T$ rappresenta effettivamente una **soluzione ottima**.

---

### **8. Conclusione e sintesi**

Abbiamo così dimostrato il **Teorema di Bellman** nelle due direzioni, utilizzando un **ragionamento per assurdo**.  
Il teorema ci fornisce una **caratterizzazione matematica completa** dei cammini minimi e giustifica la correttezza dell’algoritmo SPT.

In sintesi:

- il teorema è dimostrato in due parti,
    
- il metodo usato è la **dimostrazione per assurdo**,
    
- esistono anche altre dimostrazioni basate sulla **teoria della dualità** della programmazione matematica, ma quella vista è la più intuitiva e diretta.