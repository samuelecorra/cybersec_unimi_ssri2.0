# Soluzione dell'esercitazione — 24 novembre 2025

Esercitazione di **Algoritmi e Strutture Dati** che combina l'algoritmo di **Dijkstra** per i cammini minimi con una batteria di esercizi su **ricorrenze e complessità**: metodo esperto, albero di ricorsione, cicli annidati e analisi di algoritmi ricorsivi.

> **Riferimenti di teoria:** [M06/UD1 — Dijkstra e Johnson](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L2_Algo_Dijkstra_Johnson.md), [M06/UD1 — Problema dei cammini minimi](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L1_1_Problema_dei_cammini_minimi.md), [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md), [M01 — Efficienza e complessità](../M01_Elementi_Fondamentali_DS_e_Algo/UD1/L2_1_Efficienza_e_complessita.md).

---

### **1. Esercizio 1 — Algoritmo di Dijkstra**

#### **1.1. Traccia**

Applicare Dijkstra con sorgente $1$ al grafo **orientato pesato** con vertici $\{1,\dots,6\}$ e archi

| Arco | Peso | | Arco | Peso |
|---|---:|---|---|---:|
| $1 \to 2$ | 10 | | $3 \to 5$ | 8 |
| $1 \to 3$ | 10 | | $5 \to 4$ | 8 |
| $2 \to 3$ | 10 | | $4 \to 6$ | 6 |
| $2 \to 4$ | 10 | | $5 \to 6$ | 4 |
| $2 \to 5$ | 9 | | | |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Richiamo dell'algoritmo**

Dijkstra mantiene per ogni vertice una **stima** $d[v]$ della distanza dalla sorgente e il predecessore $\pi[v]$ sul cammino corrente. A ogni passo estrae dalla coda di priorità il vertice non ancora definitivo con $d$ minima e **rilassa** i suoi archi uscenti: per ogni arco $(u,v)$, se $d[u] + w(u,v) < d[v]$ si aggiornano $d[v]$ e $\pi[v] = u$.

> 📌 La correttezza si fonda sulla **non negatività dei pesi**: quando un vertice viene estratto con stima minima, nessun cammino alternativo può migliorarla, perché passerebbe per vertici a distanza maggiore o uguale e aggiungerebbe peso non negativo. Con un solo arco negativo la proprietà cade e serve Bellman-Ford.

#### **1.3. Tabella di esecuzione**

Ogni cella riporta $d[v]$ e $\pi[v]$ nella forma "distanza, predecessore".

| Estratto | $2$ | $3$ | $4$ | $5$ | $6$ |
|---|---|---|---|---|---|
| *(inizio)* | $\infty$, N | $\infty$, N | $\infty$, N | $\infty$, N | $\infty$, N |
| **1** ($d=0$) | **10, 1** | **10, 1** | $\infty$, N | $\infty$, N | $\infty$, N |
| **2** ($d=10$) | — | 10, 1 | **20, 2** | **19, 2** | $\infty$, N |
| **3** ($d=10$) | — | — | 20, 2 | **18, 3** | $\infty$, N |
| **5** ($d=18$) | — | — | 20, 2 | — | **22, 5** |
| **4** ($d=20$) | — | — | — | — | 22, 5 |
| **6** ($d=22$) | — | — | — | — | — |

#### **1.4. Commento ai passaggi**

**Estrazione di 2.** Rilassando gli archi uscenti da $2$: il vertice $3$ avrebbe $10+10 = 20$, peggiore del $10$ già noto, quindi **nessun aggiornamento**; $4$ passa a $20$ e $5$ a $19$.

**Estrazione di 3.** Fra $2$ e $3$ c'è pareggio a $10$; si estrae prima $2$, poi $3$. L'arco $3 \to 5$ porta $5$ a $10+8 = 18 < 19$: **il predecessore di 5 cambia da 2 a 3**. È il punto più istruttivo della traccia: una stima già assegnata può ancora migliorare finché il vertice non viene estratto.

**Estrazione di 5.** Da $5$: il vertice $4$ avrebbe $18+8 = 26 > 20$, nessun aggiornamento; $6$ passa a $18+4 = 22$.

**Estrazione di 4.** Da $4$: $6$ avrebbe $20+6 = 26 > 22$, nessun aggiornamento. L'arco $4 \to 6$, pur essendo l'unico altro modo di raggiungere $6$, non viene mai usato.

#### **1.5. Risultato**

| Vertice | $1$ | $2$ | $3$ | $4$ | $5$ | $6$ |
|---|---:|---:|---:|---:|---:|---:|
| distanza $d$ | 0 | 10 | 10 | 20 | 18 | 22 |
| predecessore $\pi$ | — | 1 | 1 | 2 | 3 | 5 |

**Albero dei cammini minimi:**

- **1**
  - **2** (peso 10) → **4** (peso 10)
  - **3** (peso 10) → **5** (peso 8) → **6** (peso 4)

I cammini minimi sono $1\to2$ ($10$), $1\to3$ ($10$), $1\to2\to4$ ($20$), $1\to3\to5$ ($18$), $1\to3\to5\to6$ ($22$).

$$
\text{Complessità: } \Theta(|V|^{2}) \text{ con vettore},\quad O((|V|+|E|)\log|V|) \text{ con heap binario}.
$$

---

### **2. Esercizio 2 — Metodo esperto: $T(n)=3T(n/4)+n\log n$**

#### **2.1. Identificazione dei parametri**

$$
a = 3, \qquad b = 4, \qquad f(n) = n\log n, \qquad n^{\log_4 3} \approx n^{0.79}.
$$

Il calcolo: $\log_4 3 = \dfrac{\ln 3}{\ln 4} = \dfrac{1.0986}{1.3863} \approx 0.7925$.

#### **2.2. Confronto e caso 3**

Occorre confrontare $n^{1}\log n$ con $n^{0.79}$:

$$
n\log n = \Omega\!\left(n^{\log_4 3 + \varepsilon}\right) \quad \text{con } \varepsilon = 0.1 ,
$$

perché $n\log n$ cresce più di $n^{0.89}$. Siamo nel **caso 3**.

**Condizione di regolarità:**

$$
3 \cdot \frac{n}{4}\log\frac{n}{4} \;\le\; c\, n\log n
\quad\Longleftrightarrow\quad
\frac{3}{4}\,\log\frac{n}{4} \le c\log n .
$$

Poiché $\log\frac{n}{4} = \log n - 2 < \log n$, la disuguaglianza è soddisfatta con

$$
c = \frac{3}{4} < 1 .
$$

$$
\boxed{T(n) = \Theta(n\log n)}
$$

> 💡 Il margine è più ampio di quanto sembri: $\frac34 \log\frac n4 < \frac34 \log n$ già di per sé, quindi la costante $3/4$ funziona senza dover ricorrere alla sottrazione del $2$. La sottrazione conferma però che la disuguaglianza vale per **ogni** $n \ge 4$, non solo asintoticamente.

---

### **3. Esercizio 3 — Tre cicli annidati con condizione $j/n \le n$**

#### **3.1. Traccia**

```
for i = 1 to n
{
    j = i + 1;
    while (j/n <= n)
    {
        k = 1;
        while (k <= n)
        {
            P(4);          /* P è di costo costante */
            k = k + 3;
        }
        j = j + 1;
    }
}
```

#### **3.2. Analisi ciclo per ciclo**

**Ciclo esterno.** L'indice $i$ va da $1$ a $n$: $\Theta(n)$ iterazioni.

**Ciclo intermedio.** La condizione $j/n \le n$ equivale a $j \le n^{2}$: il ciclo prosegue finché $j$ non supera $n^{2}$. Poiché $j$ parte da $i+1$ e cresce di uno per volta, le iterazioni sono $n^{2} - i = O(n^{2})$.

> ⚠️ La condizione scritta come $j/n \le n$ nasconde il vero limite. Moltiplicando entrambi i membri per $n$ (positivo) si ottiene $j \le n^{2}$: il ciclo è **quadratico**, non lineare. È l'errore che la traccia vuole far commettere.

**Ciclo interno.** L'indice $k$ parte da $1$ e cresce **di tre in tre** fino a $n$: le iterazioni sono $\lceil n/3 \rceil = \Theta(n)$. Il passo $3$ è una costante moltiplicativa e non cambia l'ordine.

**Corpo.** La chiamata `P(4)` ha argomento **costante**, quindi costa $\Theta(1)$ indipendentemente da $n$.

#### **3.3. Costo complessivo**

$$
T(n) = \underbrace{\Theta(n)}_{\text{ciclo } i} \cdot \underbrace{O(n^{2})}_{\text{ciclo } j} \cdot \underbrace{\Theta(n)}_{\text{ciclo } k} = O(n^{4}).
$$

$$
\boxed{T(n) = O(n^{4})}
$$

> 💡 `P(4)` costa $\Theta(1)$ perché l'argomento è la costante $4$, non $n$: la complessità di una procedura va sempre valutata **sull'argomento effettivo**. Se la chiamata fosse stata `P(n)` con $P$ lineare, il costo complessivo sarebbe salito a $O(n^{5})$.

---

### **4. Esercizio 4 — Ricorrenza mal posta e sua correzione**

#### **4.1. La forma scorretta**

$$
T(n) = 2\,T(n) + \sqrt{n} + 42 \qquad \text{(non è una ricorrenza valida)}
$$

Il sottoproblema ha la **stessa dimensione** dell'originale: la ricorsione non progredisce verso il caso base e la definizione è circolare. Non esiste alcuna funzione $T$ che la soddisfi (se non identicamente nulla o infinita): sostituendo si otterrebbe $T(n) - 2T(n) = \sqrt n + 42$, cioè $T(n) = -\sqrt n - 42$, negativa e priva di senso come tempo di calcolo.

> ⚠️ Prima di applicare qualunque metodo, verificare sempre che l'argomento ricorsivo sia **strettamente minore** di $n$: $n/b$ con $b>1$, oppure $n-k$ con $k \ge 1$. È il controllo che distingue una ricorrenza da un'identità impossibile.

#### **4.2. La forma corretta**

$$
T(n) = 2\,T\!\left(\frac{n}{2}\right) + \sqrt{n} + 42
$$

$$
a = 2, \qquad b = 2, \qquad n^{\log_2 2} = n^{1} = n, \qquad f(n) = \sqrt n + 42 .
$$

Confronto:

$$
\sqrt n + 42 = n^{0.5} + 42 = O\!\left(n^{1-\varepsilon}\right) \quad \text{con } \varepsilon = 0.1 \;(\text{cioè } n^{0.9}).
$$

Siamo nel **caso 1**, dominano le foglie:

$$
\boxed{T(n) = \Theta(n)}
$$

> 💡 Il termine additivo $42$ è irrilevante: $\sqrt n + 42 = \Theta(\sqrt n)$ perché la costante è dominata dalla radice per $n$ grande. Nelle ricorrenze le costanti additive non spostano mai il caso del teorema.

---

### **5. Esercizio 5 — Albero di ricorsione: $T(n)=3T(n/4)+cn^{3}$**

#### **5.1. Traccia**

$$
T(n) =
\begin{cases}
3\,T\!\left(\dfrac{n}{4}\right) + c\,n^{3} & \text{se } n > 4,\\[6pt]
c & \text{altrimenti.}
\end{cases}
$$

#### **5.2. Struttura dell'albero**

| Livello $i$ | Nodi | Dimensione | Costo di un nodo | Costo del livello |
|---:|---:|---|---|---|
| $0$ | $1$ | $n$ | $c\,n^{3}$ | $c\,n^{3}$ |
| $1$ | $3$ | $\dfrac{n}{4}$ | $c\left(\dfrac{n}{4}\right)^{3} = \dfrac{c\,n^{3}}{64}$ | $\dfrac{3}{64}\,c\,n^{3}$ |
| $2$ | $9$ | $\dfrac{n}{16}$ | $\dfrac{c\,n^{3}}{4096}$ | $\left(\dfrac{3}{64}\right)^{2} c\,n^{3}$ |
| $\vdots$ | $\vdots$ | $\vdots$ | $\vdots$ | $\vdots$ |
| $i$ | $3^{i}$ | $\dfrac{n}{4^{i}}$ | $\dfrac{c\,n^{3}}{64^{i}}$ | $\left(\dfrac{3}{64}\right)^{i} c\,n^{3}$ |

Il rapporto $\dfrac{3}{64}$ nasce dal contrasto fra i due effetti: il numero di nodi si **triplica**, ma il costo di ciascuno si divide per $4^{3} = 64$ (la dimensione si divide per $4$ e il costo è cubico). La riduzione prevale nettamente.

#### **5.3. Altezza dell'albero**

Il caso base è $n \le 4$, non $n \le 1$: la ricorsione si arresta quando

$$
\frac{n}{4^{i}} = 4 \iff n = 4^{i+1} \iff i+1 = \log_4 n \iff i = \log_4 n - 1 = \Theta(\log n).
$$

#### **5.4. Somma e risultato**

$$
T(n) = \sum_{i=0}^{\log_4 n - 1} \left(\frac{3}{64}\right)^{i} c\,n^{3}
\;<\; c\,n^{3} \sum_{i=0}^{\infty} \left(\frac{3}{64}\right)^{i}
= c\,n^{3} \cdot \frac{1}{1 - \frac{3}{64}} = \frac{64}{61}\,c\,n^{3}.
$$

Il solo costo della radice è $c\,n^{3}$, dunque $T(n) = \Omega(n^{3})$:

$$
\boxed{T(n) = \Theta(n^{3})}
$$

**Verifica con il metodo esperto.** $n^{\log_4 3} \approx n^{0.79}$ e $f(n) = cn^{3} = \Omega(n^{0.79+\varepsilon})$; la condizione di regolarità dà $3c(n/4)^3 = \frac{3}{64}cn^3 \le c' cn^3$ con $c' = 3/64 < 1$. **Caso 3**, quindi $\Theta(n^{3})$. ✅

---

### **6. Esercizio 6 — StoogeSort**

#### **6.1. Traccia**

```
Algo(A, i, j)
{
    if A[i] > A[j] then scambia(A[i], A[j]);
    if (i + 1 >= j) then return true;
    k = floor((j - i + 1) / 3);
    Algo(A, i,   j-k);
    Algo(A, i+k, j);
    Algo(A, i,   j-k);
}
```

#### **6.2. Che cosa fa l'algoritmo**

È il celebre **StoogeSort**, un algoritmo di ordinamento deliberatamente inefficiente ma corretto. L'idea: si ordina il primo $2/3$ dell'intervallo, poi l'ultimo $2/3$, poi **di nuovo** il primo $2/3$.

La correttezza è meno ovvia di quanto sembri. Dopo la prima chiamata, i due terzi iniziali sono ordinati; la seconda chiamata sistema gli ultimi due terzi, e in particolare porta nell'ultimo terzo i valori più grandi dell'intervallo — che vi restano definitivamente. La terza chiamata ripara il disordine che la seconda può aver introdotto nella parte iniziale.

#### **6.3. Analisi della complessità**

Ogni chiamata ricorsiva opera su un intervallo di

$$
(j-k) - i + 1 = (j-i+1) - k \approx n - \frac{n}{3} = \frac{2n}{3}
$$

elementi, dove $n = j-i+1$. Le chiamate sono **tre** e il lavoro locale (un confronto, uno scambio, una divisione) è costante:

$$
T(n) = 3\,T\!\left(\frac{2n}{3}\right) + \Theta(1).
$$

Parametri: $a = 3$, $b = 3/2$, $f(n) = \Theta(1)$.

$$
n^{\log_b a} = n^{\log_{3/2} 3} \approx n^{2.71},
$$

perché $\log_{3/2} 3 = \dfrac{\ln 3}{\ln 1.5} = \dfrac{1.0986}{0.4055} \approx 2.7095$.

Confronto: $f(n) = 1 = O\!\left(n^{\log_{3/2}3 - \varepsilon}\right)$ per qualunque $\varepsilon < 2.71$. **Caso 1**:

$$
\boxed{T(n) = \Theta\!\left(n^{\log_{3/2} 3}\right) \approx \Theta\!\left(n^{2.71}\right)}
$$

> ⚠️ StoogeSort è **peggiore dell'InsertionSort** ($\Theta(n^{2})$) e persino del BubbleSort: $n^{2.71}$ cresce sensibilmente più di $n^{2}$. Serve a mostrare che un algoritmo divide-et-impera non è automaticamente efficiente: qui i sottoproblemi sono grandi ($2n/3$ anziché $n/2$) e numerosi ($3$ anziché $2$), e $\sum \alpha_i = 3 \cdot \frac23 = 2 > 1$ fa esplodere il numero di foglie.

---

### **7. Esercizio 7 — Doppio ciclo con costo dipendente dall'indice**

#### **7.1. Traccia**

```
count = 0;  j = 0;
for i = 0 to log n
{
    j = 2;
    while (j < 2^n)
    {
        istruzione;
        j = 2 * j;
    }
}
```

Si analizzino due varianti dell'istruzione interna:

1. `count++` — costo costante;
2. `count = count + f(i)` — dove $f$ ha complessità $O(m)$ sull'input $m$, quindi $f(i)$ costa $O(i)$.

#### **7.2. Struttura dei cicli**

**Ciclo esterno.** $i$ va da $0$ a $\log n$: $\Theta(\log n)$ iterazioni.

**Ciclo interno.** $j$ parte da $2$ e raddoppia: $j = 2, 4, 8, \dots, 2^{x}$. La condizione $j < 2^{n}$ diventa

$$
2^{x} < 2^{n} \iff x < n ,
$$

quindi $\Theta(n)$ iterazioni.

> 💡 Il limite $2^{n}$ è **esponenziale** ma il ciclo raddoppia, e le due esponenzialità si elidono lasciando un costo lineare. Se il limite fosse stato $n$ anziché $2^n$, le iterazioni sarebbero state $\log n$.

#### **7.3. Variante 1 — corpo a costo costante**

$$
T(n) = \Theta(\log n) \cdot \Theta(n) \cdot \Theta(1) = \Theta(n\log n).
$$

#### **7.4. Variante 2 — corpo di costo $\Theta(i)$**

Il costo del corpo dipende dall'indice esterno, quindi non si può moltiplicare: bisogna **sommare** sui valori di $i$.

$$
T(n) = \sum_{i=0}^{\log n} \bigl(n \cdot i\bigr) = n \sum_{i=0}^{\log n} i = n \cdot \frac{\log n\,(\log n + 1)}{2}.
$$

$$
\boxed{T(n) = \Theta\!\left(n \log^{2} n\right)}
$$

> ⚠️ Sostituire il valore massimo $i = \log n$ al posto della somma darebbe $n \log n \cdot \log n = n\log^2 n$: qui il risultato coincide a meno della costante $1/2$, ma è un caso fortunato. In generale la sostituzione del massimo **sovrastima**, ed è corretta solo se si sta cercando un limite superiore $O$ e non un $\Theta$.

---

### **8. Esercizio 8 — Ricorsione tripla con decremento unitario**

#### **8.1. Traccia**

```
f1(n)
{
    if n <= 1 then return 1
    else return f1(n-1) + f1(n-1) + f1(n-1);
}
```

#### **8.2. La ricorrenza**

Il punto essenziale: le tre chiamate `f1(n-1)` sono **tre invocazioni distinte**, non una sola moltiplicata per tre. Il compilatore non memorizza il risultato della prima per riutilizzarlo, quindi ognuna paga il proprio costo:

$$
T(n) = 3\,T(n-1) + \Theta(1).
$$

> ⚠️ Scrivere $T(n) = T(n-1) + \Theta(1)$ "perché tanto è sempre lo stesso valore" è l'errore classico e porta a $\Theta(n)$ invece che a un costo esponenziale: una differenza abissale. Il valore restituito è effettivamente lo stesso, ma il **tempo** speso per ottenerlo viene pagato tre volte.

#### **8.3. Albero di ricorsione**

| Livello $i$ | Nodi | Costo del livello |
|---:|---:|---:|
| $0$ | $3^{0} = 1$ | $1$ |
| $1$ | $3^{1} = 3$ | $3$ |
| $2$ | $3^{2} = 9$ | $9$ |
| $\vdots$ | $\vdots$ | $\vdots$ |
| $n$ | $3^{n}$ | $3^{n}$ |

Poiché la dimensione cala di **uno** per volta, l'albero ha profondità $n$ (non $\log n$). Sommando:

$$
T(n) = 3^{0} + 3^{1} + 3^{2} + \dots + 3^{n} = \frac{3^{n+1}-1}{2} = \Theta(3^{n}).
$$

$$
\boxed{T(n) = \Theta(3^{n})}
$$

> 📌 Nella serie geometrica di ragione $3$ domina **l'ultimo termine**: la somma vale circa $\frac32$ volte le sole foglie. Il costo è quindi determinato interamente dal livello più profondo, come sempre accade quando la ragione supera $1$.

> 💡 Il metodo esperto **non è applicabile**: la ricorrenza è sottrattiva ($n-1$), non divisiva. Per questa famiglia vale invece il risultato generale $T(n) = a\,T(n-b) + f(n)$ con $a>1 \Rightarrow T(n) = \Theta\!\left(a^{n/b}\right)$ quando $f$ è polinomiale — qui $a=3$, $b=1$, da cui $\Theta(3^n)$.
>
> Se si memorizzasse il risultato (memoizzazione), le chiamate distinte diverrebbero $n$ e il costo crollerebbe a $\Theta(n)$: è esattamente il salto che la programmazione dinamica realizza su Fibonacci.

---

### **9. Sintesi dei risultati**

| # | Oggetto | Tecnica | Risultato |
|---|---|---|---|
| 1 | Dijkstra su grafo di 6 vertici | rilassamento con coda di priorità | $d[6]=22$ per $1\to3\to5\to6$ |
| 2 | $T(n)=3T(n/4)+n\log n$ | metodo esperto, caso 3 | $\Theta(n\log n)$ |
| 3 | tre cicli con $j \le n^{2}$ | prodotto dei costi | $O(n^{4})$ |
| 4 | $T(n)=2T(n/2)+\sqrt n + 42$ | metodo esperto, caso 1 | $\Theta(n)$ |
| 5 | $T(n)=3T(n/4)+cn^{3}$ | albero di ricorsione, ragione $3/64$ | $\Theta(n^{3})$ |
| 6 | StoogeSort | metodo esperto, caso 1 | $\Theta(n^{\log_{3/2}3}) \approx \Theta(n^{2.71})$ |
| 7 | doppio ciclo, corpo $\Theta(1)$ / $\Theta(i)$ | prodotto / sommatoria | $\Theta(n\log n)$ / $\Theta(n\log^{2} n)$ |
| 8 | tripla ricorsione su $n-1$ | albero di ricorsione, ragione $3$ | $\Theta(3^{n})$ |

> ✅ Le due trappole ricorrenti in questa esercitazione: **condizioni di ciclo mascherate** ($j/n \le n$ è in realtà $j \le n^2$) e **chiamate ricorsive ripetute** contate una sola volta. Entrambe si evitano riscrivendo la condizione in forma esplicita e contando le invocazioni, non i valori distinti.
