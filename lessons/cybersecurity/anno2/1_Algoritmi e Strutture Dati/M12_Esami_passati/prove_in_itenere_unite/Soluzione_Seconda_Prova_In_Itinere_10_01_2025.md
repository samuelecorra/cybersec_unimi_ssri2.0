# Soluzione della seconda prova in itinere — 10 gennaio 2025

Prova di **Algoritmi e Strutture Dati**, durata dichiarata: 1 ora e 30 minuti. La traccia comprende sei domande di teoria e tre esercizi.

### **1. Domande di teoria**

#### **1.1. Minimum Spanning Tree e Kruskal**

> **Riferimenti di teoria:** [M08 — Kruskal](../../M08_Greedy/UD2/L1_Algoritmo_di_Kruskal.md), [M05 — MFSET](../../M05_DS_Orizzontali/UD1/L3_MFSET.md).

Dato un grafo non orientato, connesso e pesato $G=(V,E,w)$, un albero ricoprente contiene tutti i vertici, è connesso, aciclico e ha $|V|-1$ archi. Un MST minimizza $\sum_{e\in T}w(e)$.

Kruskal ordina gli archi per peso non decrescente e aggiunge un arco se i suoi estremi appartengono a componenti diverse, individuate mediante MFSET. L'ordinamento costa $O(|E|\log|E|)$; `TROVA` e `FONDI`, con rango e compressione, costano complessivamente $O(|E|\alpha(|V|))$. Quindi

$$
O(|E|\log|E|)=O(|E|\log|V|).
$$

La proprietà del taglio garantisce che ogni arco scelto sia sicuro.

#### **1.2. String matching approssimato**

> **Riferimento di teoria:** [M10 — String matching approssimato](../../M10_Programmazione_Dinamica/UD2/L1_Algoritmo_per_StringMatching_approssimato.md).

Dati pattern $P=p_1\ldots p_m$ e testo $T=t_1\ldots t_n$, si cerca la sottostringa di $T$ a distanza di edit minima da $P$. Con costo unitario per inserimento, cancellazione e sostituzione:

$$
D[i,j]=\min\begin{cases}
D[i-1,j]+1,\\
D[i,j-1]+1,\\
D[i-1,j-1]+[p_i\ne t_j].
\end{cases}
$$

Per consentire che il match inizi ovunque nel testo si pone $D[0,j]=0$; il minimo dell'ultima riga individua il miglior termine. Tempo $\Theta(mn)$, spazio $\Theta(mn)$, riducibile a $\Theta(m)$ se non serve ricostruire l'allineamento.

#### **1.3. MergeSort**

> **Riferimento di teoria:** [M07 — MergeSort](../../M07_Divide_et_Impera/UD2/L1_Merge_sort.md).

MergeSort divide il vettore in due metà, le ordina ricorsivamente e le fonde in tempo lineare:

$$
T(n)=2T(n/2)+\Theta(n),\qquad T(1)=\Theta(1).
$$

Poiché $a=b=2$ e $n^{\log_ba}=n$, il secondo caso del teorema dell'esperto dà $\boxed{T(n)=\Theta(n\log n)}$ nei casi migliore, medio e peggiore.

#### **1.4. Fibonacci iterativo e ricorsivo**

> **Riferimento di teoria:** [M07/UD1 — Fibonacci: ricorsione vs. iterativo](../../M07_Divide_et_Impera/UD1/L3_Fibonacci_ricorsione_vs_iterativo.md).

La versione iterativa calcola una sola volta $F_2,\ldots,F_n$: tempo $\Theta(n)$ e spazio $\Theta(1)$. La ricorsione ingenua soddisfa

$$
T(n)=T(n-1)+T(n-2)+\Theta(1)=\Theta(\varphi^n),
$$

perché ricalcola ripetutamente gli stessi sottoproblemi. Memoizzazione o programmazione dinamica riportano il tempo a $\Theta(n)$.

#### **1.5. Taglio di un grafo**

> **Riferimento di teoria:** [Approfondimento — Tagli e Prim](../Approfondimenti_per_Esame/L2%20-%20Algoritmo%20di%20Prim%20e%20propriet%C3%A0%20del%20taglio.md).

Un taglio è una partizione $(S,V\setminus S)$ con $\varnothing\ne S\ne V$. Un arco attraversa il taglio se ha un estremo in ciascuna parte. Per $V=\{a,b,c,d\}$ e $S=\{a,b\}$, gli eventuali archi $ac,ad,bc,bd$ attraversano il taglio, mentre $ab$ e $cd$ no. Negli MST, un arco di peso minimo che attraversa un taglio compatibile con le scelte correnti è sicuro.

#### **1.6. Riduzione polinomiale**

> **Riferimento di teoria:** [M11 — Classi P e NP](../../M11_Teoria_Complessita/UD2/L1_Classi_P_e_NP.md).

Si scrive $A\le_pB$ se esiste una funzione $f$ calcolabile in tempo polinomiale tale che

$$
x\in A\iff f(x)\in B.
$$

La riduzione preserva la risposta: se $B\in P$, allora anche $A\in P$. Per dimostrare che $B$ è almeno difficile quanto $A$, la direzione corretta è da $A$ a $B$.

### **2. Esercizio 1 — Dijkstra da u**

> **Riferimento di teoria:** [M06 — Dijkstra](../../M06_Impatto_delle_DS_su_complessita_algo/UD1/L2_Algo_Dijkstra_Johnson.md).

I sette vertici diversi da $u$ non sono etichettati nella figura; li chiamiamo $a$ alto-sinistra, $b$ alto-destra, $c$ centro-sinistra, $d$ centro, $e$ destra, $f$ basso-sinistra e $g$ basso-destra. Gli archi non orientati sono:

$$
ua:3,\ uc:2,\ uf:4,\ ab:8,\ ac:2,\ ad:5,\ cd:11,\ cg:6,\ de:7,\ dg:7,\ be:7,\ eg:9,\ fg:3.
$$

La tabella mostra il nodo estratto, l'insieme $S$ dei definitivi e i soli aggiornamenti efficaci.

| Estratto | $S$ | Rilassamenti efficaci |
|---|---|---|
| $u:0$ | $\{u\}$ | $a=3(u)$, $c=2(u)$, $f=4(u)$ |
| $c:2$ | $\{u,c\}$ | $d=13(c)$, $g=8(c)$ |
| $a:3$ | $\{u,c,a\}$ | $b=11(a)$, $d=8(a)$ |
| $f:4$ | $\{u,c,a,f\}$ | $g=7(f)$ |
| $g:7$ | $\{u,c,a,f,g\}$ | $e=16(g)$ |
| $d:8$ | $\{u,c,a,f,g,d\}$ | $e=15(d)$ |
| $b:11$ | $\{u,c,a,f,g,d,b\}$ | nessuno |
| $e:15$ | $V$ | nessuno |

Le distanze finali sono

$$
d(u)=0,\ d(c)=2,\ d(a)=3,\ d(f)=4,\ d(g)=7,\ d(d)=8,\ d(b)=11,\ d(e)=15.
$$

L'albero dei cammini minimi contiene $uc,ua,uf,fg,ad,ab,de$. Il cammino verso $e$ è $u-a-d-e$ e pesa $3+5+7=15$.

### **3. Esercizio 2 — QuickSort con primo pivot**

> **Riferimento di teoria:** [M07 — QuickSort](../../M07_Divide_et_Impera/UD2/L2_Quick_sort.md).

Si adotta la convenzione della lezione: in ogni sottovettore si collocano gli elementi $\le p$, poi il pivot $p$, poi quelli $>p$, conservando l'ordine relativo.

1. pivot 40: $[24,15,16,\mathbf{40},83,50,71,95,47,60,81,74]$;
2. pivot 24 nel blocco sinistro: $[15,16,\mathbf{24},40,83,50,71,95,47,60,81,74]$;
3. pivot 15 in $[15,16]$: nessun cambiamento;
4. pivot 83 nel blocco destro: $[15,16,24,40,50,71,47,60,81,74,\mathbf{83},95]$;
5. pivot 50: $[15,16,24,40,47,\mathbf{50},71,60,81,74,83,95]$;
6. pivot 71: $[15,16,24,40,47,50,60,\mathbf{71},81,74,83,95]$;
7. pivot 81: $[15,16,24,40,47,50,60,71,74,\mathbf{81},83,95]$.

Il risultato è

$$
\boxed{[15,16,24,40,47,50,60,71,74,81,83,95]}.
$$

### **4. Esercizio 3 — KMP**

> **Riferimento di teoria:** [M09 — String matching e KMP](../../M09_BackTrack/UD2/L1_Algoritmi_per_StringMatching.md).

Per $S=\texttt{bacbabababacaca}$ e $P=\texttt{ababaca}$, la funzione prefisso è:

| $j$ | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---:|---:|---:|---:|---:|---:|---:|
| $P[j]$ | a | b | a | b | a | c | a |
| $\pi[j]$ | 0 | 0 | 1 | 2 | 3 | 0 | 1 |

Con $q$ pari alla lunghezza del prefisso riconosciuto:

| $i$ | $S[i]$ | Operazione | Nuovo $q$ |
|---:|:---:|---|---:|
| 0 | b | mismatch con $P[0]$ | 0 |
| 1 | a | match con $P[0]$ | 1 |
| 2 | c | mismatch con $P[1]$; fallback a 0; mismatch con $P[0]$ | 0 |
| 3 | b | mismatch con $P[0]$ | 0 |
| 4 | a | match | 1 |
| 5 | b | match | 2 |
| 6 | a | match | 3 |
| 7 | b | match | 4 |
| 8 | a | match | 5 |
| 9 | b | mismatch con $P[5]=c$; fallback $q=\pi[4]=3$; lo stesso `b` è confrontato con $P[3]=b$ e coincide | 4 |
| 10 | a | match | 5 |
| 11 | c | match | 6 |
| 12 | a | match completo | 7 |

Il pattern compare in $S[6..12]$ con indici da zero, cioè dalla settima posizione. KMP non arretra mai nel testo: al mismatch in posizione 9 riusa il bordo `aba` e confronta di nuovo lo stesso carattere del testo. Il costo è $\Theta(|S|+|P|)$.

### **5. Nota sulla duplicazione della fonte**

Questa è la soluzione autonoma del PDF raccolto in `prove_in_itenere_unite`. La stessa prova era già archiviata come due immagini in `esame_10_gennaio_2025`; gli svolgimenti sono stati mantenuti coerenti, correggendo qui la stringa $S$ secondo il PDF.

### **6. Traccia originale**

[PDF della seconda prova in itinere del 10 gennaio 2025](2025-01-10-spi.pdf)
