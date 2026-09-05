# Soluzione dell'esercitazione — 28 novembre 2025

Esercitazione di **Algoritmi e Strutture Dati** su **ricorrenze con sottoproblemi di dimensione diversa**, analisi di algoritmi ricorsivi elementari (massimo, ricerca binaria) e introduzione ai **cammini minimi fra tutte le coppie**.

> **Riferimenti di teoria:** [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md), [M07/UD1 — Progetto Divide et Impera](../M07_Divide_et_Impera/UD1/L2_Progetto_di_algo_Divide_et_Impera.md), [M06/UD1 — Problema dei cammini minimi](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L1_2_Problema_dei_cammini_minimi.md), [M05/UD2 — Dizionari con vettori ordinati](../M05_DS_Orizzontali/UD2/L1_Dizionari_con_vettori_ordinati.md).

---

### **1. Esercizio 1 — Albero di ricorsione con partizione sbilanciata**

#### **1.1. Traccia**

$$
T(n) =
\begin{cases}
1 & \text{se } n = 1,\\[6pt]
T\!\left(\dfrac{n}{3}\right) + T\!\left(\dfrac{2n}{3}\right) + c\,n & \text{se } n > 1 .
\end{cases}
$$

Risolvere con l'albero di ricorsione.

#### **1.2. Perché il metodo esperto non basta**

I due sottoproblemi hanno dimensioni **diverse** ($n/3$ e $2n/3$): la forma non è $a\,T(n/b)+f(n)$ e il Master Theorem non è applicabile. L'albero di ricorsione, invece, gestisce senza difficoltà la partizione asimmetrica.

#### **1.3. Costo per livello**

| Livello | Nodi | Costo totale |
|---:|---|---|
| $0$ | $cn$ | $cn$ |
| $1$ | $c\dfrac{n}{3}$ e $c\dfrac{2n}{3}$ | $c\left(\dfrac{n}{3} + \dfrac{2n}{3}\right) = cn$ |
| $2$ | $c\dfrac{n}{9},\; c\dfrac{2n}{9},\; c\dfrac{2n}{9},\; c\dfrac{4n}{9}$ | $cn$ |
| $\vdots$ | $\vdots$ | $cn$ |

Il punto chiave è che le due frazioni **sommano esattamente a uno**:

$$
\frac{1}{3} + \frac{2}{3} = 1 .
$$

Poiché il costo locale è lineare, ogni livello ridistribuisce il lavoro senza perderne né guadagnarne: **finché l'albero è completo, ogni livello costa esattamente $cn$**.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.4. I due rami estremi**

L'albero **non** è bilanciato: i rami hanno lunghezze diverse a seconda di quante volte si è scelta la parte piccola o quella grande.

**Ramo più corto** (sempre $n/3$): si esaurisce quando

$$
\left(\frac{1}{3}\right)^{i} n = 1 \iff i = \log_3 n .
$$

**Ramo più lungo** (sempre $2n/3$): si esaurisce quando

$$
\left(\frac{2}{3}\right)^{i} n = 1 \iff n = \left(\frac{3}{2}\right)^{i} \iff i = \log_{3/2} n .
$$

Sotto il livello $\log_3 n$ alcuni rami sono già terminati e i livelli costano **meno** di $cn$; il livello più profondo è $\log_{3/2}n$.

#### **1.5. Limitazione superiore e inferiore**

**Limite superiore.** Supponendo (a favore di sicurezza) che tutti i livelli fino al più profondo costino $cn$:

$$
T(n) \;\le\; \sum_{i=0}^{\log_{3/2} n} c\,n = c\,n \sum_{i=0}^{\log_{3/2} n} 1 = c\,n \log_{3/2} n = O(n\log n).
$$

**Limite inferiore.** Fino al livello $\log_3 n$ l'albero è **completo** e ogni livello costa esattamente $cn$:

$$
T(n) \;\ge\; \sum_{i=0}^{\log_3 n} c\,n = c\,n\log_3 n = \Omega(n\log n).
$$

I logaritmi in basi diverse differiscono per una costante moltiplicativa ($\log_b n = \log n / \log b$), che la notazione asintotica assorbe. Le due limitazioni coincidono:

$$
\boxed{T(n) = \Theta(n\log n)}
$$

> 📌 **La regola della somma delle frazioni.** Data $T(n) = \sum_i T(\alpha_i n) + \Theta(n)$:
> - $\sum_i \alpha_i < 1$ → domina la radice → $\Theta(n)$;
> - $\sum_i \alpha_i = 1$ → tutti i livelli pesano uguale → $\Theta(n\log n)$;
> - $\sum_i \alpha_i > 1$ → dominano le foglie → costo superlineare.
>
> Questa ricorrenza appartiene al secondo caso ed è quella del **QuickSort con partizione sbilanciata fissa a $1{:}2$**: anche una divisione costantemente sbilanciata mantiene il costo $\Theta(n\log n)$, purché il rapporto sia costante. Solo una partizione che isola un numero costante di elementi (come $1$ e $n-1$) degrada a $\Theta(n^{2})$.

---

### **2. Esercizio 2 — Metodo esperto: $T(n)=3T(n/4)+cn^{2}$**

#### **2.1. Traccia**

$$
T(n) =
\begin{cases}
1 & \text{se } n = 1,\\[6pt]
3\,T\!\left(\dfrac{n}{4}\right) + c\,n^{2} & \text{se } n > 1 .
\end{cases}
$$

#### **2.2. Svolgimento**

$$
a = 3, \qquad b = 4, \qquad f(n) = c\,n^{2}, \qquad n^{\log_4 3} \approx n^{0.8}.
$$

**Confronto:**

$$
c\,n^{2} = \Omega\!\left(n^{\log_4 3 + \varepsilon}\right) \quad \text{con } \varepsilon = 0.2 \;\;(\text{cioè } n^{1.0}),
$$

e in effetti $n^{2}$ supera abbondantemente $n^{0.8}$. **Caso 3.**

**Condizione di regolarità:**

$$
3 \cdot c\left(\frac{n}{4}\right)^{2} \;\le\; d \cdot c\,n^{2}
\quad\Longleftrightarrow\quad
\frac{3}{16}\,c\,n^{2} \le d\,c\,n^{2}
\quad\Longleftrightarrow\quad
\frac{3}{16} \le d .
$$

Si sceglie $d = \dfrac{3}{16} = \dfrac{3}{4^{2}} \approx 0.19 < 1$ ✓

$$
\boxed{T(n) = \Theta\!\left(c\,n^{2}\right) = \Theta(n^{2})}
$$

> 💡 La costante di regolarità $\dfrac{a}{b^{k}}$ (qui $\dfrac{3}{4^{2}}$, dove $k$ è il grado di $f$) è sempre il candidato naturale: quando $f(n)=n^{k}$, la condizione $a\,f(n/b) \le d\,f(n)$ si riduce a $\dfrac{a}{b^{k}} \le d$, e il caso 3 vale esattamente quando $a < b^{k}$, cioè quando $\log_b a < k$. È lo stesso confronto che si fa a occhio all'inizio.

---

### **3. Esercizio 3 — Massimo ricorsivo di un vettore**

#### **3.1. Traccia**

```
Max(A, n)
{
    if n == 1 then return A[1]
    else
    {
        m = Max(A, n-1);
        if A[n] > m then return A[n]
        else return m;
    }
}
```

#### **3.2. Impostazione della ricorrenza**

La procedura effettua **una sola** chiamata ricorsiva su $n-1$ elementi; il resto (un confronto e un return) costa $\Theta(1)$:

$$
T(n) = T(n-1) + c, \qquad T(1) = c .
$$

#### **3.3. Risoluzione per sostituzione ripetuta (srotolamento)**

Si espande la ricorrenza sostituendo ripetutamente la definizione:

$$
\begin{aligned}
T(n) &= T(n-1) + c\\
     &= \bigl[T(n-2) + c\bigr] + c = T(n-2) + 2c\\
     &= \bigl[T(n-3) + c\bigr] + 2c = T(n-3) + 3c\\
     &= T(n-4) + 4c\\
     &\;\;\vdots\\
     &= T(n-k) + k\,c .
\end{aligned}
$$

Lo srotolamento si arresta quando si raggiunge il caso base, cioè per $n-k = 1$, ovvero $k = n-1$:

$$
T(n) = T(1) + (n-1)\,c = c + (n-1)c = n\,c .
$$

$$
\boxed{T(n) = \Theta(n)}
$$

> 💡 Il risultato è quello atteso: per trovare il massimo bisogna **guardare tutti gli elementi almeno una volta**, quindi $\Omega(n)$ è un limite inferiore per qualunque algoritmo, ricorsivo o iterativo. Più precisamente servono esattamente $n-1$ confronti, ed è dimostrabile che non se ne può fare a meno (ogni confronto elimina al più un candidato).

> ⚠️ La versione ricorsiva consuma $\Theta(n)$ **spazio di pila**, mentre quella iterativa usa $\Theta(1)$ memoria ausiliaria. A parità di tempo, la ricorsione lineare non tail-recursive paga in spazio: su un vettore di milioni di elementi provocherebbe uno stack overflow.

---

### **4. Esercizio 4 — Ricerca binaria ricorsiva**

#### **4.1. Traccia**

```
Algo(A, x, i, j)
{
    if i > j then return -1
    else
    {
        m = (i + j) / 2;
        if A[m] == x then return m
        else if A[m] > x then return Algo(A, x, i,   m-1)
        else                  return Algo(A, x, m+1, j);
    }
}
```

#### **4.2. Analisi**

L'array è ordinato. A ogni chiamata si confronta l'elemento centrale con la chiave cercata e si prosegue **su una sola metà**: l'altra viene scartata integralmente perché l'ordinamento garantisce che non possa contenere $x$.

Il lavoro locale (calcolo di $m$, un accesso all'array, due confronti) è $\Theta(1)$:

$$
T(n) = 1 \cdot T\!\left(\frac{n}{2}\right) + c .
$$

Metodo esperto:

$$
a = 1, \qquad b = 2, \qquad n^{\log_2 1} = n^{0} = 1, \qquad f(n) = c = \Theta(1) = \Theta\!\left(n^{0}\right).
$$

Poiché $f(n)$ ha **esattamente** l'ordine della funzione spartiacque, siamo nel **caso 2**:

$$
T(n) = \Theta\!\left(n^{\log_b a}\log n\right) = \Theta\!\left(n^{0}\log n\right) = \Theta(\log n).
$$

$$
\boxed{T(n) = \Theta(\log n)}
$$

#### **4.3. Confronto con la ricerca sequenziale**

| | Ricerca sequenziale | Ricerca binaria |
|---|---|---|
| Prerequisito | nessuno | array **ordinato** |
| Caso peggiore | $\Theta(n)$ | $\Theta(\log n)$ |
| Caso migliore | $\Theta(1)$ | $\Theta(1)$ |
| Spazio (versione ricorsiva) | $\Theta(n)$ pila | $\Theta(\log n)$ pila |

> 📌 Il guadagno è drastico: su un milione di elementi la ricerca sequenziale ne esamina in media mezzo milione, la binaria al più venti. Il costo è l'ordinamento preliminare, $\Theta(n\log n)$, che però si ammortizza su molte ricerche: conviene ordinare non appena le ricerche previste sono più di $\log n$.

> 💡 Ricerca binaria e Max ricorsivo sono i due archetipi della ricorsione **su un solo sottoproblema**: se la dimensione si dimezza il costo è $\Theta(\log n)$, se cala di uno è $\Theta(n)$. La differenza fra $T(n/2)+c$ e $T(n-1)+c$ è tutta qui.

---

### **5. Esercizio 5 — Cammini minimi fra tutte le coppie**

#### **5.1. Traccia**

Dato il grafo **orientato pesato** con vertici $\{1,2,3,4\}$ e archi

$$
1\to2\;(3),\qquad 1\to3\;(8),\qquad 1\to4\;(7),\qquad 2\to4\;(2),\qquad 3\to4\;(1),
$$

determinare la matrice delle distanze minime fra tutte le coppie e la matrice dei predecessori.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Il principio di ottimalità**

Il metodo si fonda su una proprietà che vale per tutti gli algoritmi di cammino minimo: **ogni sottocammino di un cammino minimo è a sua volta minimo**. Se il cammino ottimo da $i$ a $j$ passa per $k$, allora il tratto $i \rightsquigarrow k$ è il cammino minimo da $i$ a $k$ e il tratto $k \rightsquigarrow j$ è il minimo da $k$ a $j$.

La conseguenza operativa è la ricorrenza che permette di **estendere** i cammini di un arco alla volta:

$$
d_{ij}^{(m)} = \min_{1\le k\le n}\left\{ d_{ik}^{(m-1)} + w_{kj} \right\},
$$

dove $d_{ij}^{(m)}$ è il peso del cammino minimo da $i$ a $j$ che usa **al più $m$ archi**. Al passo $0$ si parte dalla matrice dei pesi ($d_{ii}=0$, $d_{ij}=w_{ij}$ se l'arco esiste, $\infty$ altrimenti); dopo $n-1$ passi tutti i cammini minimi sono stati trovati, perché in un grafo senza cicli negativi nessun cammino minimo usa più di $n-1$ archi.

> 💡 L'esempio grafico degli appunti illustra la scelta: per andare da $i$ a $j$ si può usare l'arco diretto di peso $10$, oppure passare per un intermedio con costo $2 + 10 = 12$, oppure per un altro con $6$ — vince il minimo, cioè $6$. È letteralmente l'operazione di **minimo su tutti gli intermedi possibili**.

#### **5.3. Matrice delle distanze**

Il grafo è molto semplice: tutti gli archi puntano "in avanti" e $4$ non ha archi uscenti.

$$
D = \begin{pmatrix}
0 & 3 & 8 & \mathbf{5}\\
\infty & 0 & \infty & 2\\
\infty & \infty & 0 & 1\\
\infty & \infty & \infty & 0
\end{pmatrix},
\qquad
\Pi = \begin{pmatrix}
\text{NIL} & 1 & 1 & \mathbf{2}\\
\text{NIL} & \text{NIL} & \text{NIL} & 2\\
\text{NIL} & \text{NIL} & \text{NIL} & 3\\
\text{NIL} & \text{NIL} & \text{NIL} & \text{NIL}
\end{pmatrix}
$$

**L'unico valore non banale è $d_{14}$.** Ci sono tre modi di andare da $1$ a $4$:

| Cammino | Peso |
|---|---:|
| $1 \to 4$ (arco diretto) | $7$ |
| $1 \to 2 \to 4$ | $3 + 2 = \mathbf{5}$ |
| $1 \to 3 \to 4$ | $8 + 1 = 9$ |

Il minimo è $5$, ottenuto passando per $2$; di conseguenza $\pi_{14} = 2$, non $1$.

> ⚠️ È il caso più istruttivo: **l'arco diretto non è il cammino minimo**. Un algoritmo che si limitasse a leggere la matrice dei pesi restituirebbe $7$; solo l'estensione ai cammini di due archi scopre il valore $5$. Gli $\infty$ nella parte inferiore riflettono l'assenza di archi entranti in $1$ e uscenti da $4$: il grafo è un DAG orientato "da 1 verso 4".

#### **5.4. Ricostruzione del cammino**

La matrice $\Pi$ permette di ricostruire il cammino a ritroso: $\pi_{14}=2$ dice che l'ultimo arco è $2\to4$; poi $\pi_{12}=1$ dice che il precedente è $1\to2$; si è arrivati alla sorgente. Il cammino è quindi

$$
1 \to 2 \to 4 \qquad \text{di peso } 5 .
$$

$$
\text{Complessità: } \Theta(|V|^{4}) \text{ per l'estensione ingenua, } \Theta(|V|^{3}\log|V|) \text{ con il raddoppio, } \Theta(|V|^{3}) \text{ con Floyd-Warshall.}
$$

---

### **6. Sintesi**

| # | Oggetto | Tecnica | Risultato |
|---|---|---|---|
| 1 | $T(n)=T(n/3)+T(2n/3)+cn$ | albero di ricorsione, $\frac13+\frac23=1$ | $\Theta(n\log n)$ |
| 2 | $T(n)=3T(n/4)+cn^{2}$ | metodo esperto, caso 3, $d=3/16$ | $\Theta(n^{2})$ |
| 3 | massimo ricorsivo | srotolamento di $T(n-1)+c$ | $\Theta(n)$ |
| 4 | ricerca binaria ricorsiva | metodo esperto, caso 2 | $\Theta(\log n)$ |
| 5 | cammini minimi fra tutte le coppie | principio di ottimalità | $d_{14}=5$ via il vertice $2$ |

> ✅ Il filo conduttore: **dove finisce il lavoro**. Nell'esercizio 1 è distribuito uniformemente su tutti i livelli, nel 2 concentrato alla radice, nel 3 e nel 4 lungo una singola catena — corta o lunga a seconda che la dimensione si dimezzi o cali di uno. Riconoscere questa distribuzione è più importante che ricordare a memoria i tre casi del teorema.
