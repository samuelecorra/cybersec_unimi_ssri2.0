# Soluzione dell'esercitazione — 17 ottobre 2025

Esercitazione di **Algoritmi e Strutture Dati** dedicata agli **algoritmi di ordinamento**: HeapSort con ordinamento decrescente, QuickSort con pivot centrale e MergeSort.

> **Riferimenti di teoria:** [M03/UD2 — Heap](../M03_DS_Alberi/UD2/L1_Heap.md), [M03/UD2 — HeapSort e SelectionSort](../M03_DS_Alberi/UD2/L2_HeapSort_e_SelectionSort.md), [M07/UD2 — MergeSort](../M07_Divide_et_Impera/UD2/L1_Merge_sort.md), [M07/UD2 — QuickSort](../M07_Divide_et_Impera/UD2/L2_Quick_sort.md).

---

### **1. Esercizio 1 — HeapSort in ordine decrescente**

#### **1.1. Traccia**

Ordinare in **ordine decrescente** con HeapSort la sequenza

$$
9,\; 29,\; 22,\; 7,\; 96,\; 87,\; 3,\; 11,\; 86,\; 75,\; 32,\; 63,\; 0,\; 74,\; 46,\; 53
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Perché serve un MIN-heap**

Questo è il punto concettuale dell'esercizio, e va deciso **prima** di scrivere qualunque nodo.

HeapSort lavora sullo stesso vettore, senza memoria ausiliaria, sfruttando la seguente idea: la radice dell'heap contiene sempre l'elemento "estremo" (massimo o minimo, a seconda del tipo di heap); si scambia la radice con l'ultima foglia, si riduce di uno la dimensione dell'heap e si ripristina la proprietà con `restauraheap`. L'elemento estratto resta **in fondo** alla parte già ordinata.

Ne segue la regola operativa:

| Obiettivo | Tipo di heap | Perché |
|---|---|---|
| ordine **crescente** | MAX-heap | il massimo, estratto per primo, finisce nell'ultima posizione |
| ordine **decrescente** | **MIN-heap** | il minimo, estratto per primo, finisce nell'ultima posizione |

Poiché la traccia chiede l'ordine **decrescente**, si costruisce un **MIN-heap**: ogni nodo ha valore minore o uguale a quello dei suoi figli, e la radice contiene il minimo assoluto.

> 📌 L'errore più costoso in questo esercizio è costruire d'istinto un MAX-heap perché è quello visto a lezione, e poi cercare di "invertire il risultato" alla fine. L'inversione dell'ordine si ottiene invertendo il **verso della relazione d'ordine nell'heap**, non ribaltando l'array a posteriori.

#### **1.3. Rappresentazione dell'heap come vettore**

Si usa l'indicizzazione da $1$ a $n$, per cui il nodo in posizione $i$ ha

$$
\text{padre} = \left\lfloor \frac{i}{2} \right\rfloor, \qquad
\text{figlio sinistro} = 2i, \qquad
\text{figlio destro} = 2i+1 .
$$

Il vettore iniziale, con $n=16$, è:

| $i$ | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $A[i]$ | 9 | 29 | 22 | 7 | 96 | 87 | 3 | 11 | 86 | 75 | 32 | 63 | 0 | 74 | 46 | 53 |

#### **1.4. Costruzione del MIN-heap**

La costruzione procede dal basso verso l'alto: si applica `restauraheap` a tutti i nodi interni, cioè da $i=\lfloor n/2 \rfloor = 8$ fino a $i=1$. Le foglie (posizioni $9$–$16$) soddisfano banalmente la proprietà e non richiedono interventi.

| $i$ | nodo | figli | azione | vettore risultante |
|---:|---:|---|---|---|
| 8 | 11 | 53 | $11<53$: nessuno scambio | invariato |
| 7 | 3 | 74, 46 | $3$ è già il minore | invariato |
| 6 | 87 | 63, 0 | scambio $87 \leftrightarrow 0$ | $\dots,0,3,11,86,75,32,63,87,74,46,53$ |
| 5 | 96 | 75, 32 | scambio $96 \leftrightarrow 32$ | $\dots,32,0,3,11,86,75,96,63,87,\dots$ |
| 4 | 7 | 11, 86 | $7$ è già il minore | invariato |
| 3 | 22 | 0, 3 | scambio $22 \leftrightarrow 0$; poi $22$ contro $63,87$: si ferma | $9,29,0,7,32,22,3,\dots$ |
| 2 | 29 | 7, 32 | scambio $29 \leftrightarrow 7$; poi $29$ contro $11,86$: scambio $29\leftrightarrow 11$ | $9,7,0,11,32,22,3,29,\dots$ |
| 1 | 9 | 7, 0 | scambio $9 \leftrightarrow 0$; poi $9$ contro $22,3$: scambio $9\leftrightarrow 3$; poi $9$ contro $74,46$: si ferma | vedi sotto |

MIN-heap ottenuto:

| $i$ | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $A[i]$ | 0 | 7 | 3 | 11 | 32 | 22 | 9 | 29 | 86 | 75 | 96 | 63 | 87 | 74 | 46 | 53 |

Per livelli:

- **livello 0:** $0$
- **livello 1:** $7$, $3$
- **livello 2:** $11$, $32$, $22$, $9$
- **livello 3:** $29$, $86$, $75$, $96$, $63$, $87$, $74$, $46$
- **livello 4:** $53$

> ⚠️ Il nodo $53$ è figlio sinistro di $29$ (posizione $16 = 2\cdot 8$): l'albero non è completo all'ultimo livello, ed è normale — l'heap è un albero **quasi completo**, riempito da sinistra a destra.

#### **1.5. Fase di estrazione**

A ogni passo: si scambia la radice $A[1]$ con l'ultimo elemento dell'heap, si riduce la dimensione di uno e si esegue `restauraheap(1)`. La tabella riporta lo stato **dopo** ogni ripristino; la colonna "estratto" indica il valore che si è appena stabilizzato nella coda ordinata.

| Passo | estratto | posizione definitiva | heap residuo (dopo `restauraheap`) |
|---:|---:|---:|---|
| 1 | 0 | 16 | $3,7,9,11,32,22,46,29,86,75,96,63,87,74,53$ |
| 2 | 3 | 15 | $7,11,9,29,32,22,46,53,86,75,96,63,87,74$ |
| 3 | 7 | 14 | $9,11,22,29,32,63,46,53,86,75,96,74,87$ |
| 4 | 9 | 13 | $11,29,22,53,32,63,46,87,86,75,96,74$ |
| 5 | 11 | 12 | $22,29,46,53,32,63,74,87,86,75,96$ |
| 6 | 22 | 11 | $29,32,46,53,75,63,74,87,86,96$ |
| 7 | 29 | 10 | $32,53,46,86,75,63,74,87,96$ |
| 8 | 32 | 9 | $46,53,63,86,75,96,74,87$ |
| 9 | 46 | 8 | $53,75,63,86,87,96,74$ |
| 10 | 53 | 7 | $63,75,74,86,87,96$ |
| 11 | 63 | 6 | $74,75,96,86,87$ |
| 12 | 74 | 5 | $75,86,96,87$ |
| 13 | 75 | 4 | $86,87,96$ |
| 14 | 86 | 3 | $87,96$ |
| 15 | 87 | 2 | $96$ |
| 16 | 96 | 1 | — |

Dettaglio dei primi tre passi, che sono quelli svolti alla lavagna:

**Passo 1.** Si scambia $A[1]=0$ con $A[16]=53$. Lo $0$ è definitivo in posizione $16$, la dimensione scende a $15$ e la radice contiene $53$, che viola la proprietà di MIN-heap. `restauraheap` fa scendere $53$: contro i figli $7$ e $3$ il minore è $3$ (posizione $3$), quindi scambio; poi $53$ contro $22$ e $9$, il minore è $9$ (posizione $7$), scambio; infine $53$ contro $74$ e $46$, il minore è $46$ (posizione $15$), scambio. Il cammino di discesa è $1 \to 3 \to 7 \to 15$, lungo $\lfloor\log 15\rfloor = 3$ livelli.

**Passo 2.** Si scambia la nuova radice $3$ con $A[15]=53$; $3$ è definitivo in posizione $15$. La radice torna a essere $53$ e scende lungo $1 \to 2 \to 4 \to 8$.

**Passo 3.** Si scambia la radice $7$ con $A[14]=74$; $7$ è definitivo in posizione $14$. Il $74$ scende lungo $1 \to 3 \to 6 \to 12$.

#### **1.6. Risultato**

Il vettore finale, letto da sinistra a destra, è ordinato in senso **decrescente**:

$$
96,\; 87,\; 86,\; 75,\; 74,\; 63,\; 53,\; 46,\; 32,\; 29,\; 22,\; 11,\; 9,\; 7,\; 3,\; 0
$$

#### **1.7. Complessità**

$$
T(n) = \underbrace{\Theta(n)}_{\text{costruzione heap}} + \underbrace{n \cdot \Theta(\log n)}_{n \text{ estrazioni}} = \Theta(n \log n).
$$

La costruzione dell'heap costa $\Theta(n)$ e non $\Theta(n\log n)$: i nodi vicini alle foglie, che sono la maggioranza, hanno cammini di discesa cortissimi. Sommando l'altezza di tutti i nodi si ottiene

$$
\sum_{h=0}^{\log n} \frac{n}{2^{h+1}} \cdot h \;\le\; n \sum_{h=0}^{\infty} \frac{h}{2^{h+1}} = n .
$$

> ✅ HeapSort ha $\Theta(n\log n)$ nel caso migliore, medio e peggiore, e opera **in loco** con $O(1)$ memoria ausiliaria. Non è stabile: gli scambi fra radice e ultima foglia possono invertire elementi di uguale valore.

---

### **2. Esercizio 2 — QuickSort con pivot in posizione centrale**

#### **2.1. Traccia**

Ordinare con QuickSort la sequenza (indici da $1$ a $12$)

$$
38,\; 81,\; 22,\; 48,\; 13,\; 69,\; 93,\; 14,\; 45,\; 58,\; 79,\; 72
$$

scegliendo come pivot l'elemento in posizione

$$
\left\lfloor \frac{\text{sinistra} + \text{destra}}{2} \right\rfloor .
$$

#### **2.2. Convenzione di partizionamento**

> ⚠️ La variante usata in esercitazione **non** coincide con quella della lezione teorica. In [M07/UD2 — QuickSort](../M07_Divide_et_Impera/UD2/L2_Quick_sort.md) il pivot è il primo elemento e il partizionamento è a **cursore singolo** (schema di Lomuto). Qui il pivot è l'elemento centrale e il partizionamento è a **due cursori che si rincorrono** (schema di Hoare). I due schemi producono partizioni diverse pur essendo entrambi corretti: negli esercizi bisogna dichiarare quale si sta usando.

Lo schema adottato è il seguente. Sia $x = A[p]$ il pivot, con $p = \lfloor (\text{sin}+\text{des})/2 \rfloor$:

1. $i$ parte da sinistra e avanza **finché $A[i] \le x$**;
2. $j$ parte da destra e arretra **finché $A[j] \ge x$**;
3. se $i < j$ si scambiano $A[i]$ e $A[j]$, poi $i{+}{+}$ e $j{-}{-}$, e si torna al punto 1;
4. quando $i > j$ la scansione termina: gli elementi in $[\text{sin},j]$ sono $\le x$, quelli in $[i,\text{des}]$ sono $\ge x$.

Al termine il pivot va portato nella sua posizione definitiva. Detta $p$ la posizione che il pivot occupa in quel momento:

- se $p \le j$, si scambia $A[p] \leftrightarrow A[j]$ e il pivot è definitivo in $j$;
- se $p \ge i$, si scambia $A[p] \leftrightarrow A[i]$ e il pivot è definitivo in $i$;
- se $j < p < i$, il pivot **è già** nella posizione definitiva e non serve alcuno scambio.

> 💡 Il terzo caso è quello che gli appunti non esplicitano ma che serve per non sbagliare le chiamate ricorsive: quando i due cursori si incrociano scavalcando il pivot, questo si trova già esattamente sul confine fra le due parti.

#### **2.3. Prima partizione: intervallo $[1,12]$**

Pivot: $p = \lfloor (1+12)/2 \rfloor = 6$, quindi $x = A[6] = 69$.

**Primo incontro.** $i$ avanza da $1$: $38 \le 69$, poi si ferma su $i=2$ perché $81 > 69$. $j$ arretra da $12$: $72 \ge 69$, $79 \ge 69$, si ferma su $j=10$ perché $58 < 69$. Essendo $i<j$ si scambiano $81$ e $58$:

$$
38,\; \mathbf{58},\; 22,\; 48,\; 13,\; \underline{69},\; 93,\; 14,\; 45,\; \mathbf{81},\; 79,\; 72
$$

**Secondo incontro.** $i$ riparte da $3$: $22, 48, 13$ e anche il pivot $69$ soddisfano $A[i] \le 69$; ci si ferma su $i=7$ perché $93 > 69$. $j$ riparte da $9$ e si ferma subito, perché $45 < 69$. Essendo $i=7 < j=9$ si scambiano $93$ e $45$:

$$
38,\; 58,\; 22,\; 48,\; 13,\; \underline{69},\; \mathbf{45},\; 14,\; \mathbf{93},\; 81,\; 79,\; 72
$$

**Terzo incontro.** $i$ riparte da $8$: $14 \le 69$, quindi $i$ passa a $9$ dove $93 > 69$ e si ferma. $j$ riparte da $8$ e si ferma subito, perché $14 < 69$. Ora $i=9 > j=8$: la scansione è conclusa.

**Collocazione del pivot.** Il pivot si trova in $p=6 \le j=8$, quindi si scambia $A[6] \leftrightarrow A[8]$:

$$
\underbrace{38,\; 58,\; 22,\; 48,\; 13,\; 14,\; 45}_{\text{tutti} < 69},\; \underline{\mathbf{69}},\; \underbrace{93,\; 81,\; 79,\; 72}_{\text{tutti} > 69}
$$

Il $69$ è nella posizione $8$, che è quella che occuperà nel vettore ordinato. Si ricorre su $[1,7]$ e su $[9,12]$.

#### **2.4. Ricorsione sul sottovettore destro $[9,12]$**

Pivot: $p = \lfloor (9+12)/2 \rfloor = 10$, quindi $x = A[10] = 81$. Il sottovettore è $93, 81, 79, 72$.

**Primo incontro.** $i=9$ si ferma subito ($93 > 81$); $j=12$ si ferma subito ($72 < 81$). Scambio $93 \leftrightarrow 72$:

$$
\mathbf{72},\; \underline{81},\; 79,\; \mathbf{93}
$$

**Secondo incontro.** $i$ riparte da $10$: $81 \le 81$ e $79 \le 81$, si ferma su $i=12$ ($93>81$). $j$ riparte da $11$ e si ferma subito ($79 < 81$). Ora $i=12 > j=11$: fine scansione.

**Collocazione del pivot.** $p=10 \le j=11$, quindi $A[10] \leftrightarrow A[11]$:

$$
72,\; 79,\; \underline{\mathbf{81}},\; 93
$$

Le chiamate ricorsive su $[9,10]$ e $[12,12]$ terminano senza ulteriori scambi: il sottovettore destro è ordinato.

#### **2.5. Ricorsione sul sottovettore sinistro $[1,7]$**

Questa parte non era stata sviluppata alla lavagna; si completa qui con la stessa convenzione.

**Partizione di $[1,7]$ = $38,58,22,48,13,14,45$.** Pivot $p=\lfloor 8/2 \rfloor = 4$, $x=48$.
$i$ si ferma su $2$ ($58>48$), $j$ su $7$ ($45<48$): scambio $58 \leftrightarrow 45$, ottenendo $38,45,22,48,13,14,58$. Poi $i$ scorre $22,48,14,13$ (tutti $\le 48$) e si ferma su $i=7$ ($58>48$), mentre $j$ si ferma su $6$ ($14<48$). Essendo $p=4 \le j=6$, si scambia $A[4] \leftrightarrow A[6]$:

$$
\underbrace{38,\; 45,\; 22,\; 14,\; 13}_{<48},\; \underline{\mathbf{48}},\; \underbrace{58}_{>48}
$$

**Partizione di $[1,5]$ = $38,45,22,14,13$.** Pivot $p=3$, $x=22$.
$i=1$ si ferma subito ($38>22$), $j=5$ pure ($13<22$): scambio, ottenendo $13,45,22,14,38$. Poi $i=2$ ($45>22$) e $j=4$ ($14<22$): scambio, ottenendo $13,14,22,45,38$. Infine $i$ scavalca il pivot e si ferma su $4$, mentre $j$ scende a $2$: si ha $j=2 < p=3 < i=4$, dunque **il pivot è già in posizione definitiva** e non serve alcuno scambio:

$$
\underbrace{13,\; 14}_{<22},\; \underline{\mathbf{22}},\; \underbrace{45,\; 38}_{>22}
$$

**Sottovettori residui.** $[1,2] = 13,14$ è già ordinato. In $[4,5] = 45,38$ il pivot è $A[4]=45$; la scansione porta allo scambio $45 \leftrightarrow 38$, dando $38,45$.

#### **2.6. Risultato e complessità**

$$
13,\; 14,\; 22,\; 38,\; 45,\; 48,\; 58,\; 69,\; 72,\; 79,\; 81,\; 93
$$

| Caso | Ricorrenza | Costo |
|---|---|---|
| migliore / medio | $T(n)=2T(n/2)+\Theta(n)$ | $\Theta(n\log n)$ |
| peggiore | $T(n)=T(n-1)+\Theta(n)$ | $\Theta(n^{2})$ |

Il caso peggiore si presenta quando ogni partizione è massimamente sbilanciata, cioè quando il pivot risulta sistematicamente il minimo o il massimo del sottovettore.

> 💡 Scegliere il pivot **in posizione centrale** anziché come primo elemento non migliora il caso peggiore in senso asintotico, ma elimina il caso patologico più frequente nella pratica: un vettore già ordinato (o ordinato al contrario), sul quale la scelta del primo elemento degenera immediatamente in $\Theta(n^2)$, mentre la scelta centrale produce partizioni perfettamente bilanciate.

---

### **3. Esercizio 3 — MergeSort**

#### **3.1. Traccia**

Ordinare con MergeSort la sequenza di sette elementi

$$
38,\; 81,\; 22,\; 48,\; 13,\; 69,\; 93
$$

#### **3.2. Fase di divisione**

Con $n=7$ dispari la divisione non è simmetrica: si prendono $\lceil n/2 \rceil = 4$ elementi a sinistra e $\lfloor n/2 \rfloor = 3$ a destra, coerentemente con lo svolgimento della docente.

| Livello | Sottovettori |
|---:|---|
| 0 | $[38,81,22,48,13,69,93]$ |
| 1 | $[38,81,22,48]$ &nbsp;&nbsp;&nbsp; $[13,69,93]$ |
| 2 | $[38,81]$ &nbsp; $[22,48]$ &nbsp;&nbsp;&nbsp; $[13,69]$ &nbsp; $[93]$ |
| 3 | $[38]$ $[81]$ &nbsp; $[22]$ $[48]$ &nbsp;&nbsp;&nbsp; $[13]$ $[69]$ &nbsp; $[93]$ |

La divisione si arresta sui sottovettori di un solo elemento, che sono ordinati per definizione: è il caso base della ricorsione.

#### **3.3. Fase di fusione**

La fusione risale l'albero confrontando le teste delle due sequenze ordinate e prelevando ogni volta la minore.

| Passo | Sequenze fuse | Risultato |
|---:|---|---|
| 1 | $[38]$ e $[81]$ | $[38,81]$ |
| 2 | $[22]$ e $[48]$ | $[22,48]$ |
| 3 | $[13]$ e $[69]$ | $[13,69]$ |
| 4 | $[38,81]$ e $[22,48]$ | $[22,38,48,81]$ |
| 5 | $[13,69]$ e $[93]$ | $[13,69,93]$ |
| 6 | $[22,38,48,81]$ e $[13,69,93]$ | $[13,22,38,48,69,81,93]$ |

Il dettaglio della fusione finale, che è quella che richiede più attenzione:

| Confronto | Elemento prelevato | Uscita parziale |
|---|---|---|
| $22$ vs $13$ | $13$ | $13$ |
| $22$ vs $69$ | $22$ | $13,22$ |
| $38$ vs $69$ | $38$ | $13,22,38$ |
| $48$ vs $69$ | $48$ | $13,22,38,48$ |
| $81$ vs $69$ | $69$ | $13,22,38,48,69$ |
| $81$ vs $93$ | $81$ | $13,22,38,48,69,81$ |
| — (sinistra esaurita) | $93$ | $13,22,38,48,69,81,93$ |

#### **3.4. Risultato e complessità**

$$
13,\; 22,\; 38,\; 48,\; 69,\; 81,\; 93
$$

$$
T(n) = 2\,T\!\left(\frac{n}{2}\right) + \Theta(n) = \Theta(n\log n)
$$

MergeSort ha lo stesso costo nei tre casi (migliore, medio, peggiore), perché la struttura della ricorsione non dipende dai valori. Richiede però $\Theta(n)$ memoria ausiliaria per la fusione, a differenza di HeapSort che lavora in loco.

---

### **4. Confronto finale fra i tre algoritmi**

| | HeapSort | QuickSort | MergeSort |
|---|---|---|---|
| Caso migliore | $\Theta(n\log n)$ | $\Theta(n\log n)$ | $\Theta(n\log n)$ |
| Caso medio | $\Theta(n\log n)$ | $\Theta(n\log n)$ | $\Theta(n\log n)$ |
| Caso peggiore | $\Theta(n\log n)$ | $\Theta(n^{2})$ | $\Theta(n\log n)$ |
| Memoria ausiliaria | $O(1)$ | $O(\log n)$ per la pila | $\Theta(n)$ |
| Stabile | no | no | sì |
| Dove sta il lavoro | ripristino dell'heap | partizionamento (divisione) | fusione (ricomposizione) |

> ✅ Tre modi diversi di raggiungere $n\log n$: HeapSort lo garantisce sempre ma con costanti alte, QuickSort è il più veloce in pratica ma non offre garanzie sul caso peggiore, MergeSort garantisce il costo ed è stabile ma paga memoria aggiuntiva.
