# Soluzione dell'esercitazione — 1 dicembre 2025

Esercitazione di **Algoritmi e Strutture Dati** particolarmente ampia: ricorrenze, **alberi AVL** con inserimenti e cancellazioni, **Dijkstra**, **HeapSort**, **tabelle hash** con due metodi di scansione e **alberi rosso-neri**. È di fatto un ripasso generale del corso.

> **Riferimenti di teoria:** [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md), [M05/UD3 — Alberi rosso-neri](../M05_DS_Orizzontali/UD3/L2_Alberi_rosso_neri.md), [M05/UD3 — Alberi bilanciati di ricerca](../M05_DS_Orizzontali/UD3/L1_Alberi_bilanciati_di_ricerca.md), [M05/UD2 — Tabelle Hash](../M05_DS_Orizzontali/UD2/L2_Tabelle_Hash.md), [M03/UD2 — Heap](../M03_DS_Alberi/UD2/L1_Heap.md), [M03/UD2 — HeapSort](../M03_DS_Alberi/UD2/L2_HeapSort_e_SelectionSort.md), [M06/UD1 — Dijkstra](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L2_Algo_Dijkstra_Johnson.md).

---

### **1. Esercizio 1 — Tre chiamate ricorsive identiche**

#### **1.1. Traccia**

```
Algo(n)
{
    if n < 5 then return 1;
    return Algo(n/2) + Algo(n/2) + Algo(n/2);
}
```

#### **1.2. La lettura sbagliata**

La tentazione naturale è riscrivere il `return` come `3 * Algo(n/2)`: le tre chiamate hanno lo stesso argomento e restituiscono lo stesso valore, quindi "basta calcolarlo una volta". Questa lettura porta a

$$
T(n) = 1 \cdot T\!\left(\frac{n}{2}\right) + c \quad\Longrightarrow\quad n^{\log_2 1} = n^{0} = 1 = \Theta(f(n)) \quad\Longrightarrow\quad \text{caso 2} \quad\Longrightarrow\quad \Theta(\log n).
$$

> ⚠️ **È scorretta.** Il valore restituito è effettivamente lo stesso, ma il **tempo** no: l'interprete non sa che le tre chiamate sono equivalenti e le esegue tutte e tre, ricalcolando ogni volta l'intero sottoalbero di ricorsione. La riscrittura `3 * Algo(n/2)` cambia il programma, non lo semplifica.

#### **1.3. La ricorrenza corretta**

$$
T(n) = 3\,T\!\left(\frac{n}{2}\right) + c .
$$

**Albero di ricorsione:**

| Livello $i$ | Nodi | Costo del livello |
|---:|---:|---:|
| $0$ | $1$ | $c$ |
| $1$ | $3$ | $3c$ |
| $2$ | $9$ | $9c$ |
| $\vdots$ | $\vdots$ | $\vdots$ |
| $i$ | $3^{i}$ | $3^{i}c$ |

Profondità: la dimensione si dimezza, quindi

$$
\frac{n}{2^{i}} = 1 \iff i = \log_2 n .
$$

Il costo delle foglie, che domina la serie geometrica di ragione $3$, è

$$
3^{\log_2 n} \cdot c = n^{\log_2 3} \cdot c
$$

usando l'identità $a^{\log_b n} = n^{\log_b a}$.

$$
\boxed{T(n) = \Theta\!\left(n^{\log_2 3}\right) \approx \Theta\!\left(n^{1.585}\right)}
$$

**Verifica con il metodo esperto.** $a=3$, $b=2$, $n^{\log_2 3} \approx n^{1.585}$, $f(n)=c=O(n^{1.585-\varepsilon})$ → **caso 1** → $\Theta(n^{\log_2 3})$ ✅

> 💡 La distanza fra le due risposte è enorme: $\Theta(\log n)$ contro $\Theta(n^{1.585})$. Su $n = 10^{6}$ significa una ventina di operazioni contro circa $10^{9.5}$. È lo stesso salto che separa Fibonacci ricorsivo ingenuo da Fibonacci con memoizzazione.

---

### **2. Esercizio 2 — Metodo esperto: $T(n)=2T(n/8)+\sqrt[3]{n}$**

$$
a = 2, \qquad b = 8, \qquad f(n) = \sqrt[3]{n} = n^{1/3}.
$$

$$
n^{\log_b a} = n^{\log_8 2} = n^{1/3} = \sqrt[3]{n},
$$

perché $8^{1/3} = 2$, cioè $\log_8 2 = 1/3$.

La funzione spartiacque e $f(n)$ hanno **esattamente lo stesso ordine**: siamo nel **caso 2**.

$$
\boxed{T(n) = \Theta\!\left(\sqrt[3]{n}\,\log n\right)}
$$

> 📌 Il caso 2 è quello in cui tutti i livelli dell'albero costano uguale: sono $\log_8 n$ e ciascuno vale $\Theta(\sqrt[3]n)$, da cui il fattore logaritmico. È la stessa struttura di MergeSort ($a=b=2$, $f(n)=n$, spartiacque $n$), solo con esponente $1/3$ anziché $1$.

---

### **3. Esercizio 3 — Metodo esperto: $T(n)=2T(n/3)+(\log n)^{2}$**

$$
a = 2, \qquad b = 3, \qquad n^{\log_3 2} \approx n^{0.63}, \qquad f(n) = \log^{2} n .
$$

Il confronto è fra una potenza e un logaritmo al quadrato. Poiché **qualunque potenza positiva di $n$ batte definitivamente qualunque potenza del logaritmo**:

$$
\log^{2} n = O\!\left(n^{\log_3 2 - \varepsilon}\right) \quad \text{ad esempio con } \varepsilon = 0.1, \text{ cioè } n^{0.53}.
$$

**Caso 1**, domina il costo delle foglie:

$$
\boxed{T(n) = \Theta\!\left(n^{\log_3 2}\right) \approx \Theta\!\left(n^{0.63}\right)}
$$

> ⚠️ Non ci si lasci ingannare dall'esponente $2$: $\log^{2}n$ non è "quasi quadratico". Per $n = 10^{6}$ si ha $\log^{2}n \approx 400$, mentre $n^{0.63} \approx 5\,600$. La gerarchia $\log^{k} n \ll n^{\varepsilon} \ll n \ll n^{k} \ll a^{n}$ vale per **ogni** $k$ ed **ogni** $\varepsilon>0$.

---

### **4. Esercizio 4 — Cicli annidati con basi di crescita diverse**

#### **4.1. Traccia**

```
/* N intero positivo > 0 */
s = 0;  j = 0;  k = 0;
for i = 0 to N
{
    j = 1;
    while (j < N)
    {
        k = 1;
        while (k < N)
        {
            s = s + 1;
            k = k * 3;
        }
        j = j * 2;
    }
}
```

#### **4.2. Analisi**

| Ciclo | Progressione | Condizione | Iterazioni |
|---|---|---|---|
| `for i` | $0,1,2,\dots,N$ | $i \le N$ | $\Theta(N)$ |
| `while j` | $1,2,4,8,16,\dots$ | $2^{x} < N$ | $\log_2 N$ |
| `while k` | $1,3,9,27,81,\dots$ | $3^{y} < N$ | $\log_3 N$ |

I tre cicli sono indipendenti (nessun limite dipende dagli indici esterni), quindi i costi si moltiplicano:

$$
T(N) = \Theta(N) \cdot \log_2 N \cdot \log_3 N .
$$

Poiché $\log_3 N = \dfrac{\log_2 N}{\log_2 3}$, il fattore $1/\log_2 3 \approx 0.63$ è una **costante** e viene assorbito:

$$
\boxed{T(N) = \Theta\!\left(N \log^{2} N\right)}
$$

> 💡 Le basi dei logaritmi non contano nell'analisi asintotica proprio per questa ragione: cambiare base equivale a moltiplicare per una costante. Vanno però tenute distinte nei conteggi esatti — il ciclo su $k$ compie davvero meno iterazioni di quello su $j$.

---

### **5. Esercizio 5 — Albero AVL**

#### **5.1. Traccia**

Inserire nell'ordine $6, 15, 12, 21, 17, 25, 27, 30$ in un albero AVL inizialmente vuoto, poi cancellare $6$ e $15$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Richiamo: fattore di bilanciamento e rotazioni**

Un **albero AVL** è un albero binario di ricerca in cui, per ogni nodo $v$,

$$
\beta(v) = h(\text{sottoalbero sinistro}) - h(\text{sottoalbero destro}) \in \{-1, 0, +1\},
$$

dove l'altezza di un albero vuoto vale $-1$ e quella di una foglia vale $0$. Quando dopo un inserimento o una cancellazione un nodo esce da questo intervallo, si applica una rotazione al **nodo critico più profondo** (il più vicino alla foglia modificata).

I quattro casi, denominati secondo la direzione dei due passi che portano dal nodo critico al sottoalbero cresciuto:

| Caso | Configurazione | Rotazione |
|---|---|---|
| **SS** (sinistra-sinistra) | $\beta = +2$, figlio sinistro con $\beta \ge 0$ | **singola** a destra |
| **DD** (destra-destra) | $\beta = -2$, figlio destro con $\beta \le 0$ | **singola** a sinistra |
| **SD** (sinistra-destra) | $\beta = +2$, figlio sinistro con $\beta < 0$ | **doppia**: sinistra sul figlio, poi destra |
| **DS** (destra-sinistra) | $\beta = -2$, figlio destro con $\beta > 0$ | **doppia**: destra sul figlio, poi sinistra |

> 📌 La regola pratica: si guarda il **cammino di due passi** dal nodo critico verso il nodo appena inserito. Se i due passi vanno nella stessa direzione (linea) basta una rotazione singola; se cambiano direzione (triangolo) serve la doppia.

#### **5.3. Inserimenti**

**Inserimento di 6.** Albero vuoto: $6$ diventa radice. $\beta(6)=0$.

**Inserimento di 15.** $15 > 6$: figlio destro. $\beta(6) = -1 - 0 = -1$ ✓ bilanciato.

- **6** → destro: **15**

**Inserimento di 12 — caso DS.** $12 > 6$ (destra), $12 < 15$ (sinistra): $12$ diventa figlio sinistro di $15$.

$$
\beta(6) = h(\text{vuoto}) - h(15) = -1 - 1 = -2 \quad \text{(squilibrio)}
$$

Il nodo critico è $6$; il cammino verso $12$ è **destra** poi **sinistra**: caso **DS**, rotazione doppia.

1. Rotazione **destra** su $15$: si ottiene $6 \to 12 \to 15$ (tutto a destra);
2. rotazione **sinistra** su $6$: $12$ sale in radice.

- **12** → sinistro: **6**, destro: **15**

**Inserimento di 21.** $21 > 12 \to 15$, $21 > 15$: figlio destro di $15$. Altezze: $h(6)=0$, $h(15)=1$, quindi $\beta(12) = 0-1 = -1$ ✓ e $\beta(15) = -1-0 = -1$ ✓. Nessun intervento.

**Inserimento di 17 — caso DS.** $17 > 12 \to 15$, $17 > 15 \to 21$, $17 < 21$: figlio sinistro di $21$.

$$
\beta(15) = h(\text{vuoto}) - h(21) = -1 - 1 = -2 \quad \text{(squilibrio)}
$$

Nodo critico $15$, cammino **destra-sinistra**: caso **DS**.

1. Rotazione destra su $21$;
2. rotazione sinistra su $15$.

- **12** → sinistro: **6**; destro: **17** (sinistro **15**, destro **21**)

**Inserimento di 25 — caso DD.** $25$ scende $12 \to 17 \to 21$ e diventa figlio destro di $21$.

$$
\beta(21) = -1, \qquad \beta(17) = 1 - 2 = -1, \qquad \beta(12) = 0 - 3 = -2 \quad \text{(squilibrio)}
$$

Nodo critico $12$, cammino **destra-destra**: caso **DD**, rotazione **singola a sinistra** su $12$. Il nodo $17$ sale, $12$ ne diventa figlio sinistro ereditando $15$ come figlio destro.

- **17** → sinistro: **12** (sinistro **6**, destro **15**); destro: **21** → destro: **25**

**Inserimento di 27 — caso DD.** $27$ diventa figlio destro di $25$.

$$
\beta(25) = -1 \;\text{ok}, \qquad \beta(21) = -1 - 1 = -2 \quad \text{(squilibrio)}
$$

Nodo critico $21$, caso **DD**: rotazione singola a sinistra su $21$.

- **17** → sinistro: **12** (**6**, **15**); destro: **25** (sinistro **21**, destro **27**)

**Inserimento di 30.** $30$ diventa figlio destro di $27$.

$$
\beta(27) = -1\;\text{ok},\qquad \beta(25) = 0 - 1 = -1\;\text{ok},\qquad \beta(17) = 1 - 2 = -1\;\text{ok}
$$

**Nessuno squilibrio**: l'albero resta AVL senza rotazioni.

**Albero dopo tutti gli inserimenti:**

- **17**
  - **12** → sinistro: **6**, destro: **15**
  - **25** → sinistro: **21**, destro: **27** → destro: **30**

#### **5.4. Cancellazioni**

**Cancellazione di 6.** Il nodo $6$ è una foglia: si elimina direttamente. Ora $12$ ha solo il figlio destro $15$:

$$
\beta(12) = -1 - 0 = -1 \;\text{ok}, \qquad \beta(17) = 1 - 2 = -1 \;\text{ok}
$$

Nessun ribilanciamento.

**Cancellazione di 15 — caso DD.** Anche $15$ è una foglia. Dopo la rimozione $12$ diventa una foglia, quindi $h(12) = 0$:

$$
\beta(17) = h(12) - h(25) = 0 - 2 = -2 \quad \text{(squilibrio)}
$$

Nodo critico $17$. Il figlio destro $25$ ha $\beta(25) = h(21) - h(27) = 0 - 1 = -1 \le 0$: **stesso segno** del padre, quindi caso **DD** e rotazione **singola a sinistra** su $17$.

Il nodo $25$ sale in radice, $17$ diventa suo figlio sinistro ed eredita $21$ (ex figlio sinistro di $25$) come figlio destro.

#### **5.5. Albero finale**

- **25** — radice
  - **17** → sinistro: **12**, destro: **21**
  - **27** → destro: **30**

**Verifica dei fattori di bilanciamento:**

| Nodo | $h$ sinistro | $h$ destro | $\beta$ | Esito |
|---|---:|---:|---:|---|
| $12$ | $-1$ | $-1$ | $0$ | ✅ |
| $21$ | $-1$ | $-1$ | $0$ | ✅ |
| $30$ | $-1$ | $-1$ | $0$ | ✅ |
| $17$ | $0$ | $0$ | $0$ | ✅ |
| $27$ | $-1$ | $0$ | $-1$ | ✅ |
| $25$ | $1$ | $1$ | $0$ | ✅ |

L'albero è AVL, con altezza $2$ e $6$ nodi.

> ⚠️ **Nota sulla ricostruzione.** Gli appunti disegnano l'ultima rotazione con il nodo $12$ già privo dei figli $6$ e $15$: la figura corrisponde perciò allo stato **dopo** le due cancellazioni, non all'inserimento di $30$. Con $6$ e $15$ ancora presenti l'inserimento di $30$ non provoca alcuno squilibrio, come verificato sopra; la rotazione DD serve invece a riparare la cancellazione di $15$. L'albero finale coincide comunque con quello degli appunti.

> 💡 **AVL contro rosso-neri.** Gli AVL sono più rigidamente bilanciati (altezza $\le 1.44\log n$ contro $2\log n$), quindi cercano più in fretta; ma la cancellazione può richiedere $O(\log n)$ rotazioni, contro le al più $3$ di un rosso-nero. La scelta dipende dal rapporto fra ricerche e aggiornamenti.

---

### **6. Esercizio 6 — Algoritmo di Dijkstra**

#### **6.1. Traccia**

Applicare Dijkstra con sorgente $J$ al grafo **non orientato pesato** con vertici $\{A,\dots,J\}$ e archi

| Arco | Peso | | Arco | Peso | | Arco | Peso |
|---|---:|---|---|---:|---|---|---:|
| $A$–$E$ | 190 | | $D$–$G$ | 250 | | $G$–$J$ | 250 |
| $E$–$H$ | 90 | | $D$–$F$ | 130 | | $H$–$J$ | 300 |
| $E$–$D$ | 90 | | $F$–$G$ | 120 | | $I$–$J$ | 280 |
| $E$–$G$ | 150 | | $F$–$I$ | 80 | | $C$–$F$ | 240 |
| $H$–$G$ | 100 | | $G$–$I$ | 110 | | $B$–$D$ | 110 |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. Tabella di esecuzione**

| Estratto | $A$ | $B$ | $C$ | $D$ | $E$ | $F$ | $G$ | $H$ | $I$ |
|---|---|---|---|---|---|---|---|---|---|
| *(inizio)* | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ |
| **J** (0) | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | **250, J** | **300, J** | **280, J** |
| **G** (250) | $\infty$ | $\infty$ | $\infty$ | **500, G** | **400, G** | **370, G** | — | 300, J | 280, J |
| **I** (280) | $\infty$ | $\infty$ | $\infty$ | 500, G | 400, G | **360, I** | — | 300, J | — |
| **H** (300) | $\infty$ | $\infty$ | $\infty$ | 500, G | **390, H** | 360, I | — | — | — |
| **F** (360) | $\infty$ | $\infty$ | **600, F** | **490, F** | 390, H | — | — | — | — |
| **E** (390) | **580, E** | $\infty$ | 600, F | **480, E** | — | — | — | — | — |
| **D** (480) | 580, E | **590, D** | 600, F | — | — | — | — | — | — |
| **A** (580) | — | 590, D | 600, F | — | — | — | — | — | — |
| **B** (590) | — | — | 600, F | — | — | — | — | — | — |
| **C** (600) | — | — | — | — | — | — | — | — | — |

#### **6.3. Commento ai rilassamenti significativi**

**$H$ non viene migliorato tramite $G$.** Estratto $G$ con $d=250$, il cammino $J \to G \to H$ costerebbe $250+100 = 350$, peggiore del $300$ dell'arco diretto $H$–$J$: nessun aggiornamento.

**$F$ passa da $370$ a $360$.** Estratto $I$ con $d = 280$, l'arco $F$–$I$ di peso $80$ dà $280+80 = 360 < 370$ (via $G$): $\pi[F]$ cambia da $G$ a $I$.

**$E$ passa da $400$ a $390$.** Estratto $H$ con $d=300$, l'arco $E$–$H$ di peso $90$ dà $390 < 400$.

**$D$ migliora due volte.** Prima $500$ via $G$ ($250+250$), poi $490$ via $F$ ($360+130$), infine $480$ via $E$ ($390+90$). È il vertice con la storia più movimentata: tre predecessori diversi prima di stabilizzarsi.

#### **6.4. Risultato**

| Vertice | $J$ | $G$ | $I$ | $H$ | $F$ | $E$ | $D$ | $A$ | $B$ | $C$ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| distanza | 0 | 250 | 280 | 300 | 360 | 390 | 480 | 580 | 590 | 600 |
| predecessore | — | $J$ | $J$ | $J$ | $I$ | $H$ | $E$ | $E$ | $D$ | $F$ |

**Cammino minimo da $A$ a $J$:** ricostruendo a ritroso da $A$: $\pi[A]=E$, $\pi[E]=H$, $\pi[H]=J$, quindi

$$
A \to E \to H \to J \qquad \text{di peso } 190 + 90 + 300 = 580 .
$$

**Albero dei cammini minimi radicato in $J$:**

- **J**
  - **G** (250)
  - **I** (280) → **F** (360) → **C** (600)
  - **H** (300) → **E** (390) → **A** (580), **D** (480) → **B** (590)

---

### **7. Esercizio 7 — HeapSort in ordine crescente**

#### **7.1. Traccia**

Ordinare in ordine **crescente** con HeapSort la sequenza

$$
79,\; 87,\; 7,\; 9,\; 12,\; 74,\; 52,\; 20 .
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Scelta del tipo di heap**

Per l'ordine **crescente** serve un **MAX-heap**: il massimo, che sta in radice, viene scambiato con l'ultimo elemento e si stabilizza nella coda dell'array, dove deve trovarsi in un ordinamento crescente.

#### **7.3. Costruzione del MAX-heap**

Vettore iniziale (indici da $1$ a $8$):

| $i$ | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| $A[i]$ | 79 | 87 | 7 | 9 | 12 | 74 | 52 | 20 |

Si applica `restauraheap` dai nodi interni verso la radice, cioè da $i = \lfloor 8/2 \rfloor = 4$ a $i=1$:

| $i$ | nodo | figli | azione |
|---:|---:|---|---|
| 4 | 9 | 20 | $20 > 9$: scambio → $A[4]=20$, $A[8]=9$ |
| 3 | 7 | 74, 52 | massimo $74$: scambio → $A[3]=74$, $A[6]=7$ |
| 2 | 87 | 20, 12 | $87$ è già il massimo: nessuno scambio |
| 1 | 79 | 87, 74 | massimo $87$: scambio → $A[1]=87$, $A[2]=79$; poi $79$ contro $20,12$: si ferma |

MAX-heap ottenuto:

| $i$ | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| $A[i]$ | 87 | 79 | 74 | 20 | 12 | 7 | 52 | 9 |

#### **7.4. Fase di estrazione**

| Passo | estratto | posizione definitiva | heap residuo dopo `restauraheap` |
|---:|---:|---:|---|
| 1 | 87 | 8 | $79,\,20,\,74,\,9,\,12,\,7,\,52$ |
| 2 | 79 | 7 | $74,\,20,\,52,\,9,\,12,\,7$ |
| 3 | 74 | 6 | $52,\,20,\,7,\,9,\,12$ |
| 4 | 52 | 5 | $20,\,12,\,7,\,9$ |
| 5 | 20 | 4 | $12,\,9,\,7$ |
| 6 | 12 | 3 | $9,\,7$ |
| 7 | 9 | 2 | $7$ |
| 8 | 7 | 1 | — |

Dettaglio del **passo 1**: si scambia $A[1]=87$ con $A[8]=9$; l'$87$ è definitivo in posizione $8$ e l'heap scende a $7$ elementi con radice $9$. Il ripristino fa scendere il $9$: contro $79$ e $74$ vince $79$ (posizione $2$), scambio; poi $9$ contro $20$ e $12$ vince $20$ (posizione $4$), scambio; la posizione $4$ non ha più figli entro l'heap.

Lo **stato dopo quattro estrazioni** — quello disegnato negli appunti — ha coda ordinata $52, 74, 79, 87$ nelle posizioni $5$–$8$ e heap residuo con radice $20$, figli $12$ e $7$, e $9$ come figlio sinistro di $12$. ✅

#### **7.5. Risultato**

$$
7,\; 9,\; 12,\; 20,\; 52,\; 74,\; 79,\; 87
$$

$$
T(n) = \underbrace{\Theta(n)}_{\text{costruzione}} + \underbrace{n\cdot\Theta(\log n)}_{\text{estrazioni}} = \Theta(n\log n)
$$

---

### **8. Esercizio 8 — Tabelle hash con $m=11$**

#### **8.1. Traccia**

Inserire le chiavi

$$
16,\; 30,\; 38,\; 26,\; 41,\; 45,\; 37,\; 23,\; 32
$$

in una tabella di dimensione $m = 11$, con due metodi:

1. **scansione lineare:** $h(k,i) = (k + i) \bmod 11$;
2. **hashing doppio:** $h(k,i) = \bigl((k \bmod 11) + i \cdot ((k \bmod 7) + 1)\bigr) \bmod 11$.

Valori della funzione hash primaria:

| $k$ | 16 | 30 | 38 | 26 | 41 | 45 | 37 | 23 | 32 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $k \bmod 11$ | 5 | 8 | 5 | 4 | 8 | 1 | 4 | 1 | 10 |

Ci sono quattro collisioni iniziali: $38$ con $16$, $41$ con $30$, $37$ con $26$, $23$ con $45$.

> 💡 Qui $m = 11$ è **primo**, a differenza del $m=10$ usato nell'[esercitazione del 24 ottobre](Soluzione_Esercitazione_24_10_2025.md). È la scelta raccomandata dalla teoria: rende la distribuzione più uniforme e, nell'hashing doppio, garantisce che ogni passo $h_2 < m$ sia coprimo con $m$, quindi che la scansione copra l'intera tabella.

#### **8.2. Metodo 1 — Scansione lineare**

| Chiave | $h'$ | Sequenza di ispezione | Posizione |
|---:|---:|---|---:|
| $16$ | 5 | $5$ libera | **5** |
| $30$ | 8 | $8$ libera | **8** |
| $38$ | 5 | $5$ occupata, $6$ libera | **6** |
| $26$ | 4 | $4$ libera | **4** |
| $41$ | 8 | $8$ occupata, $9$ libera | **9** |
| $45$ | 1 | $1$ libera | **1** |
| $37$ | 4 | $4,5,6$ occupate, $7$ libera | **7** |
| $23$ | 1 | $1$ occupata, $2$ libera | **2** |
| $32$ | 10 | $10$ libera | **10** |

| Indice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Contenuto | | 45 | 23 | | 26 | 16 | 38 | 37 | 30 | 41 | 32 |

Ispezioni totali: $1+1+2+1+2+1+4+2+1 = 15$.

Si è formato un **agglomerato** nelle posizioni $4$–$10$: sette celle consecutive piene. È il clustering primario, che ha costretto il $37$ a quattro ispezioni.

#### **8.3. Metodo 2 — Hashing doppio**

Funzione secondaria $h_2(k) = (k \bmod 7) + 1$:

| $k$ | 16 | 30 | 38 | 26 | 41 | 45 | 37 | 23 | 32 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $k \bmod 7$ | 2 | 2 | 3 | 5 | 6 | 3 | 2 | 2 | 4 |
| $h_2(k)$ | 3 | 3 | 4 | 6 | 7 | 4 | 3 | 3 | 5 |

| Chiave | $h_1$ | $h_2$ | Sequenza di ispezione | Posizione |
|---:|---:|---:|---|---:|
| $16$ | 5 | 3 | $5$ libera | **5** |
| $30$ | 8 | 3 | $8$ libera | **8** |
| $38$ | 5 | 4 | $5$ occ.; $i=1$: $(5+4)=9$ | **9** |
| $26$ | 4 | 6 | $4$ libera | **4** |
| $41$ | 8 | 7 | $8$ occ.; $i=1$: $(8+7)=15 \bmod 11 = 4$ occ.; $i=2$: $(8+14)=22 \bmod 11 = 0$ | **0** |
| $45$ | 1 | 4 | $1$ libera | **1** |
| $37$ | 4 | 3 | $4$ occ.; $i=1$: $(4+3)=7$ | **7** |
| $23$ | 1 | 3 | $1$ occ.; $i=1$: $4$ occ.; $i=2$: $7$ occ.; $i=3$: $(1+9)=10$ | **10** |
| $32$ | 10 | 5 | $10$ occ.; $i=1$: $15 \bmod 11 = 4$ occ.; $i=2$: $20 \bmod 11 = 9$ occ.; $i=3$: $25 \bmod 11 = 3$ | **3** |

| Indice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Contenuto | 41 | 45 | | 32 | 26 | 16 | | 37 | 30 | 38 | 23 |

Ispezioni totali: $1+1+2+1+3+1+2+4+4 = 19$.

#### **8.4. Confronto**

| | Lineare | Doppio |
|---|---:|---:|
| Ispezioni totali | **15** | 19 |
| Ispezioni massime per chiave | 4 | 4 |
| Celle libere finali | $0, 3$ | $2, 6$ |

> ⚠️ In questa istanza la scansione lineare risulta **migliore** in numero di ispezioni. Non è una contraddizione con la teoria: con $n=9$ chiavi su $m=11$ celle il fattore di carico è $\alpha = 9/11 \approx 0.82$, molto alto, e su tabelle così piene le differenze fra i metodi diventano erratiche. Il vantaggio dell'hashing doppio si manifesta in media e per $\alpha$ moderati; su una singola istanza può perdere.
>
> Si noti anche che con $m=11$ primo tutti i valori $h_2 \in \{1,\dots,7\}$ sono coprimi con $11$: la scansione dell'hashing doppio è **garantita** coprire tutte e undici le celle, cosa che con $m=10$ non era assicurata.

---

### **9. Esercizio 9 — Albero rosso-nero**

#### **9.1. Traccia**

Inserire nell'ordine $11, 21, 8, 18, 20, 28, 43, 83$ in un albero rosso-nero inizialmente vuoto.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Si usa la stessa numerazione dei casi dell'[esercitazione del 31 ottobre](Soluzione_Esercitazione_31_10_2025.md): **caso 1** zio rosso (ricolorazione), **caso 2** zio nero e nodo disallineato (rotazione sul padre), **caso 3** zio nero e nodo allineato (ricolorazione + rotazione sul nonno). Si scrive **(N)** per nero e **(R)** per rosso.

#### **9.2. Inserimenti di 11, 21, 8**

$11$ diventa radice nera; $21$ va a destra e $8$ a sinistra, entrambi rossi con padre nero: nessuna violazione.

- **11 (N)** → sinistro: **8 (R)**, destro: **21 (R)**

#### **9.3. Inserimento di 18 — caso 1**

$18 > 11$ (destra), $18 < 21$ (sinistra): $18$ **(R)** ha padre $21$ **(R)**. Lo zio è $8$, **rosso**.

**Caso 1** — $21$ e $8$ diventano neri, $11$ rosso; il nodo problematico risale a $11$ che è la radice e torna nera.

- **11 (N)** → sinistro: **8 (N)**; destro: **21 (N)** → sinistro: **18 (R)**

#### **9.4. Inserimento di 20 — caso 2 seguito dal caso 3**

$20$ scende $11 \to 21 \to 18$ e diventa figlio **destro** di $18$ **(R)**: violazione. Il nonno è $21$, lo zio è `NIL` (nero).

$20$ è figlio destro mentre $18$ è figlio sinistro: configurazione **disallineata**.

**Caso 2** — rotazione **sinistra** su $18$; il nodo problematico diventa $18$ e la catena $21 \to 20 \to 18$ è allineata a sinistra.

**Caso 3** — $20$ diventa nero, $21$ rosso, rotazione **destra** su $21$.

- **11 (N)** → sinistro: **8 (N)**; destro: **20 (N)** → sinistro: **18 (R)**, destro: **21 (R)**

#### **9.5. Inserimento di 28 — caso 1**

$28$ scende $11 \to 20 \to 21$ e diventa figlio destro di $21$ **(R)**: violazione. Il nonno è $20$, lo zio è $18$, **rosso**.

**Caso 1** — $21$ e $18$ diventano neri, $20$ rosso; il nodo problematico risale a $20$, il cui padre $11$ è nero: il ripristino termina.

- **11 (N)** → sinistro: **8 (N)**; destro: **20 (R)** → sinistro **18 (N)**, destro **21 (N)** → destro: **28 (R)**

#### **9.6. Inserimento di 43 — caso 3**

$43$ scende fino a diventare figlio destro di $28$ **(R)**: violazione. Il nonno è $21$ **(N)**, lo zio è `NIL` (nero).

Sia $43$ sia $28$ sono figli destri: configurazione **allineata**, **caso 3**. Si colora $28$ di nero e $21$ di rosso, poi si ruota a **sinistra** su $21$.

- **11 (N)** → sinistro: **8 (N)**; destro: **20 (R)** → sinistro **18 (N)**; destro **28 (N)** → sinistro **21 (R)**, destro **43 (R)**

#### **9.7. Inserimento di 83 — caso 1 seguito dal caso 3**

$83$ scende $11 \to 20 \to 28 \to 43$ e diventa figlio destro di $43$ **(R)**: violazione. Il nonno è $28$, lo zio è $21$, **rosso**.

**Caso 1** — $43$ e $21$ diventano neri, $28$ rosso; il nodo problematico risale a $28$. Ma il padre di $28$ è $20$, anch'esso **rosso**: la violazione si è spostata.

Nuovo nonno: $11$; nuovo zio: $8$, **nero**. Il nodo $28$ è figlio destro e $20$ è figlio destro di $11$: configurazione **allineata**.

**Caso 3** — $20$ diventa nero, $11$ rosso, rotazione **sinistra** su $11$. Il nodo $20$ diventa la **nuova radice**.

#### **9.8. Albero finale**

- **20 (N)** — radice
  - **11 (R)**
    - **8 (N)**
    - **18 (N)**
  - **28 (R)**
    - **21 (N)**
    - **43 (N)** → destro: **83 (R)**

#### **9.9. Verifica delle proprietà**

**Proprietà 4 (nessun rosso con figlio rosso).** I nodi rossi sono $11$, $28$, $83$. Il nodo $11$ ha figli $8$ e $18$, entrambi neri; $28$ ha figli $21$ e $43$, entrambi neri; $83$ è una foglia. ✅

**Proprietà 5 (altezza nera uniforme).** Contando i nodi neri da un figlio della radice fino a `NIL` incluso:

| Cammino | Neri contati | $bh$ |
|---|---|---:|
| $20 \to 11 \to 8 \to \text{NIL}$ | $8$, NIL | 2 |
| $20 \to 11 \to 18 \to \text{NIL}$ | $18$, NIL | 2 |
| $20 \to 28 \to 21 \to \text{NIL}$ | $21$, NIL | 2 |
| $20 \to 28 \to 43 \to \text{NIL}$ (sinistro) | $43$, NIL | 2 |
| $20 \to 28 \to 43 \to 83 \to \text{NIL}$ | $43$, NIL ($83$ è rosso) | 2 |

Tutti i cammini hanno $bh = 2$. ✅ L'albero ha $8$ nodi e altezza $3$, entro il limite $2\log_2 9 \approx 6.3$.

---

### **10. Sintesi**

| # | Oggetto | Risultato |
|---|---|---|
| 1 | tre chiamate ricorsive identiche | $\Theta(n^{\log_2 3})$, **non** $\Theta(\log n)$ |
| 2 | $T(n)=2T(n/8)+\sqrt[3]{n}$ | caso 2 → $\Theta(\sqrt[3]{n}\log n)$ |
| 3 | $T(n)=2T(n/3)+\log^{2}n$ | caso 1 → $\Theta(n^{\log_3 2})$ |
| 4 | cicli con basi $2$ e $3$ | $\Theta(N\log^{2}N)$ |
| 5 | AVL, 8 inserimenti + 2 cancellazioni | radice finale $25$; 4 rotazioni in inserimento, 1 in cancellazione |
| 6 | Dijkstra da $J$ | $d[A]=580$ per $A\to E\to H\to J$ |
| 7 | HeapSort crescente su max-heap | $7,9,12,20,52,74,79,87$ |
| 8 | hash $m=11$, lineare vs doppio | 15 vs 19 ispezioni |
| 9 | rosso-nero, 8 inserimenti | radice finale $20$, $bh=2$ |

> ✅ Le due strutture bilanciate a confronto in questa esercitazione: **AVL** (esercizio 5) mantiene il bilanciamento con i fattori $\beta \in \{-1,0,+1\}$ e rotazioni immediate; **rosso-nero** (esercizio 9) lo mantiene con i colori e l'altezza nera, tollerando alberi più sbilanciati ma con meno rotazioni. Entrambi garantiscono $O(\log n)$ per ricerca, inserimento e cancellazione.
