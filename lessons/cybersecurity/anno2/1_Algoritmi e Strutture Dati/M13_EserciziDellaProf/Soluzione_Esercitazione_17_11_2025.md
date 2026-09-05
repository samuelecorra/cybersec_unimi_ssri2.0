# Soluzione dell'esercitazione — 17 novembre 2025

Esercitazione di **Algoritmi e Strutture Dati** sui **grafi**: visita in profondità con classificazione degli archi, visita in ampiezza con calcolo dei livelli, ordinamento topologico su DAG e algoritmo di Prim per l'albero ricoprente minimo.

> **Riferimenti di teoria:** [M04/UD1 — Grafi: definizioni e modelli](../M04_DS_Reticolari/UD1/L1_Grafi_definizioni_e_modelli.md), [M04/UD2 — Esplorazione di un grafo](../M04_DS_Reticolari/UD2/L1_Esplorazione_grafo.md), [M04/UD2 — Proprietà di un grafo](../M04_DS_Reticolari/UD2/L3_Proprieta_grafo.md), [M08/UD2 — Algoritmo di Kruskal](../M08_Greedy/UD2/L1_Algoritmo_di_Kruskal.md), [M12 — Algoritmo di Prim e proprietà del taglio](../M12_Esami_passati/Approfondimenti_per_Esame/L2%20-%20Algoritmo%20di%20Prim%20e%20propriet%C3%A0%20del%20taglio.md).

---

### **1. Esercizio 1 — DFS e classificazione degli archi**

#### **1.1. Traccia**

Eseguire la visita in profondità (DFS) sul grafo **orientato** con vertici $\{A,B,C,D,E,F,G,H\}$ e archi

$$
A\to B,\; A\to C,\; A\to E,\; B\to F,\; C\to D,\; D\to A,\; D\to H,\; E\to B,\; E\to G,\; F\to E,\; F\to G,\; H\to G
$$

partendo da $A$ e visitando i vicini in ordine alfabetico. Classificare tutti gli archi.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Richiamo: le quattro classi di archi**

Durante una DFS su grafo orientato ogni arco $(u,v)$ ricade in **esattamente una** di quattro categorie, determinabile dal colore di $v$ nel momento in cui l'arco viene esaminato:

| Classe | Colore di $v$ all'esame | Significato |
|---|---|---|
| **albero** (tree) | bianco (mai visto) | $v$ viene scoperto proprio grazie a questo arco |
| **indietro** (back) | grigio (in corso) | $v$ è un **antenato** di $u$ nella foresta DFS |
| **avanti** (forward) | nero, con $d[u] < d[v]$ | $v$ è un **discendente** di $u$, già completato per altra via |
| **attraversamento** (cross) | nero, con $d[v] < d[u]$ | $v$ appartiene a un ramo già chiuso, non imparentato |

Equivalentemente, con i tempi di scoperta $d$ e fine $f$:

- **albero/avanti:** $d[u] < d[v] < f[v] < f[u]$ (intervalli **annidati**, $v$ dentro $u$);
- **indietro:** $d[v] < d[u] < f[u] < f[v]$ (intervalli annidati, $u$ dentro $v$);
- **attraversamento:** $f[v] < d[u]$ (intervalli **disgiunti**).

> 📌 La presenza di **almeno un arco all'indietro** è condizione necessaria e sufficiente perché un grafo orientato contenga un ciclo. È il criterio su cui si basa il test di aciclicità in tempo $\Theta(|V|+|E|)$.

#### **1.3. Esecuzione della visita**

Si parte da $A$ e si esaminano i vicini in ordine alfabetico. La colonna "azione" riporta l'evento che avviene in quell'istante di tempo.

| Tempo | Evento | Arco esaminato | Classe |
|---:|---|---|---|
| 1 | scoperta di $A$ | — | — |
| 2 | scoperta di $B$ | $A \to B$ | **albero** |
| 3 | scoperta di $F$ | $B \to F$ | **albero** |
| 4 | scoperta di $E$ | $F \to E$ | **albero** |
| — | $E$ esamina $B$: grigio | $E \to B$ | **indietro** |
| 5 | scoperta di $G$ | $E \to G$ | **albero** |
| 6 | fine di $G$ | — | — |
| 7 | fine di $E$ | — | — |
| — | $F$ esamina $G$: nero, $d[F]{=}3 < d[G]{=}5$ | $F \to G$ | **avanti** |
| 8 | fine di $F$ | — | — |
| 9 | fine di $B$ | — | — |
| 10 | scoperta di $C$ | $A \to C$ | **albero** |
| 11 | scoperta di $D$ | $C \to D$ | **albero** |
| — | $D$ esamina $A$: grigio | $D \to A$ | **indietro** |
| 12 | scoperta di $H$ | $D \to H$ | **albero** |
| — | $H$ esamina $G$: nero, $f[G]{=}6 < d[H]{=}12$ | $H \to G$ | **attraversamento** |
| 13 | fine di $H$ | — | — |
| 14 | fine di $D$ | — | — |
| 15 | fine di $C$ | — | — |
| — | $A$ esamina $E$: nero, $d[A]{=}1 < d[E]{=}4$ | $A \to E$ | **avanti** |
| 16 | fine di $A$ | — | — |

#### **1.4. Tempi di scoperta e di fine**

| Vertice | $A$ | $B$ | $C$ | $D$ | $E$ | $F$ | $G$ | $H$ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| $d$ (scoperta) | 1 | 2 | 10 | 11 | 4 | 3 | 5 | 12 |
| $f$ (fine) | 16 | 9 | 15 | 14 | 7 | 8 | 6 | 13 |

#### **1.5. Albero DFS**

- **A**
  - **B** (albero)
    - **F** (albero)
      - **E** (albero)
        - **G** (albero)
  - **C** (albero)
    - **D** (albero)
      - **H** (albero)

#### **1.6. Classificazione completa**

| Arco | Classe | Giustificazione con i tempi |
|---|---|---|
| $A \to B$ | albero | $B$ bianco alla scoperta |
| $A \to C$ | albero | $C$ bianco alla scoperta |
| $A \to E$ | **avanti** | $d[A]=1 < d[E]=4 < f[E]=7 < f[A]=16$, $E$ non è figlio di $A$ |
| $B \to F$ | albero | — |
| $C \to D$ | albero | — |
| $D \to A$ | **indietro** | $d[A]=1 < d[D]=11 < f[D]=14 < f[A]=16$ |
| $D \to H$ | albero | — |
| $E \to B$ | **indietro** | $d[B]=2 < d[E]=4 < f[E]=7 < f[B]=9$ |
| $E \to G$ | albero | — |
| $F \to E$ | albero | — |
| $F \to G$ | **avanti** | $d[F]=3 < d[G]=5 < f[G]=6 < f[F]=8$, $G$ non è figlio di $F$ |
| $H \to G$ | **attraversamento** | $f[G]=6 < d[H]=12$: intervalli disgiunti |

#### **1.7. Osservazioni**

Il grafo **contiene cicli**, perché ha due archi all'indietro: $D \to A$ chiude il ciclo $A \to C \to D \to A$ ed $E \to B$ chiude il ciclo $B \to F \to E \to B$.

La distinzione fra arco in avanti e arco di attraversamento è la più insidiosa: in entrambi i casi il vertice di destinazione è **nero** quando l'arco viene esaminato. Il discriminante è il confronto fra i tempi:

- se $d[u] < d[v]$, allora $v$ è stato scoperto **dopo** $u$ e quindi durante l'esplorazione di $u$: è un discendente → **avanti**;
- se $d[v] < d[u]$, allora $v$ apparteneva a un ramo esplorato **prima** → **attraversamento**.

> ⚠️ In un grafo **non orientato** esistono solo archi d'albero e archi all'indietro: gli archi in avanti e di attraversamento non possono presentarsi, perché ogni arco viene esaminato da entrambi i suoi estremi e il primo esame lo classifica sempre come albero o indietro.

$$
\text{Complessità: } \Theta(|V| + |E|) \text{ con liste di adiacenza.}
$$

---

### **2. Esercizio 2 — BFS su grafo non orientato**

#### **2.1. Traccia**

Eseguire la visita in ampiezza (BFS) a partire dal vertice $0$ sul grafo **non orientato** con vertici $\{0,1,\dots,10\}$ e liste di adiacenza:

| Vertice | Adiacenti |
|---:|---|
| $0$ | $2,\,3$ |
| $1$ | $2,\,4,\,8$ |
| $2$ | $0,\,1,\,3,\,8$ |
| $3$ | $0,\,2,\,8$ |
| $4$ | $1,\,5,\,7,\,9,\,10$ |
| $5$ | $4,\,6,\,9$ |
| $6$ | $5,\,9$ |
| $7$ | $4,\,10$ |
| $8$ | $1,\,2,\,3,\,10$ |
| $9$ | $4,\,5,\,6$ |
| $10$ | $4,\,7,\,8$ |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. Esecuzione con la coda FIFO**

La BFS mantiene una **coda**: si estrae un vertice, si scandiscono i suoi adiacenti e si accodano quelli non ancora scoperti, assegnando loro distanza $d[u]+1$ e predecessore $u$.

| Passo | Vertice estratto | Archi esaminati | Nuovi vertici scoperti | Coda dopo il passo |
|---:|---:|---|---|---|
| 1 | $0$ | $(0,2),(0,3)$ | $2\,[d{=}1]$, $3\,[d{=}1]$ | $2,3$ |
| 2 | $2$ | $(2,1),(2,8),(2,3)$ | $1\,[d{=}2]$, $8\,[d{=}2]$ | $3,1,8$ |
| 3 | $3$ | $(3,2),(3,8)$ | — | $1,8$ |
| 4 | $1$ | $(1,4),(1,8)$ | $4\,[d{=}3]$ | $8,4$ |
| 5 | $8$ | $(8,1),(8,2),(8,3),(8,10)$ | $10\,[d{=}3]$ | $4,10$ |
| 6 | $4$ | $(4,1),(4,5),(4,7),(4,9),(4,10)$ | $5,7,9\,[d{=}4]$ | $10,5,7,9$ |
| 7 | $10$ | $(10,4),(10,7),(10,8)$ | — | $5,7,9$ |
| 8 | $5$ | $(5,4),(5,6),(5,9)$ | $6\,[d{=}5]$ | $7,9,6$ |
| 9 | $7$ | $(7,4),(7,10)$ | — | $9,6$ |
| 10 | $9$ | $(9,4),(9,5),(9,6)$ | — | $6$ |
| 11 | $6$ | — | — | vuota |

**Ordine di visita:** $0,\;2,\;3,\;1,\;8,\;4,\;10,\;5,\;7,\;9,\;6$.

#### **2.3. Distanze e livelli**

| Vertice | $0$ | $1$ | $2$ | $3$ | $4$ | $5$ | $6$ | $7$ | $8$ | $9$ | $10$ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $d$ (distanza da $0$) | 0 | 2 | 1 | 1 | 3 | 4 | 5 | 4 | 2 | 4 | 3 |
| predecessore $\pi$ | — | 2 | 0 | 0 | 1 | 4 | 5 | 4 | 2 | 4 | 8 |

Raggruppando per livello:

| Livello | Vertici |
|---:|---|
| $0$ | $\{0\}$ |
| $1$ | $\{2,\,3\}$ |
| $2$ | $\{1,\,8\}$ |
| $3$ | $\{4,\,10\}$ |
| $4$ | $\{5,\,7,\,9\}$ |
| $5$ | $\{6\}$ |

#### **2.4. Albero BFS**

- **0**
  - **2** → **1** → **4** → **5** → **6**, **7**, **9**
  - **3**
  - (da 2) **8** → **10**

Esplicitamente, gli archi dell'albero BFS sono

$$
(0,2),\; (0,3),\; (2,1),\; (2,8),\; (1,4),\; (8,10),\; (4,5),\; (4,7),\; (4,9),\; (5,6).
$$

Sono $10$ archi per $11$ vertici, come deve essere per un albero ricoprente di un grafo connesso.

#### **2.5. Osservazioni**

> 📌 La BFS calcola le **distanze minime in numero di archi** dalla sorgente. È corretta perché la coda garantisce che i vertici siano estratti in ordine non decrescente di distanza: quando si estrae un vertice a distanza $k$, tutti i vertici a distanza $\le k$ sono già stati scoperti.

Il vertice $6$ è il più lontano da $0$, a distanza $5$. Si noti che $6$ è adiacente sia a $5$ sia a $9$, entrambi a distanza $4$: viene scoperto dal primo dei due che viene estratto dalla coda, cioè $5$. L'albero BFS **non è unico** — dipende dall'ordine delle liste di adiacenza — ma le **distanze** $d[\cdot]$ sono univocamente determinate.

Il grafo è connesso: tutti gli $11$ vertici hanno distanza finita da $0$.

$$
\text{Complessità: } \Theta(|V| + |E|).
$$

---

### **3. Esercizio 3 — Ordinamento topologico**

#### **3.1. Traccia**

Dato il grafo **orientato aciclico** (DAG) con vertici $\{A,\dots,G\}$ e archi pesati

$$
C\!\to\! A\,(1),\;\; C\!\to\! D\,(2),\;\; C\!\to\! F\,(4),\;\; A\!\to\! B\,(2),\;\; A\!\to\! D\,(4),\;\; D\!\to\! B\,(3),
$$
$$
D\!\to\! E\,(2),\;\; D\!\to\! F\,(1),\;\; D\!\to\! G\,(5),\;\; F\!\to\! G\,(3),\;\; G\!\to\! E\,(2),\;\; E\!\to\! B\,(1)
$$

determinare un ordinamento topologico partendo la visita dal vertice $C$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 I pesi degli archi sono presenti nel disegno ma **irrilevanti** per l'ordinamento topologico, che dipende solo dalla direzione degli archi. Servono eventualmente per altre domande sullo stesso grafo (cammini minimi, cammino critico).

#### **3.2. Metodo**

L'ordinamento topologico di un DAG si ottiene eseguendo una DFS e ordinando i vertici per **tempo di fine decrescente**. La correttezza segue dall'osservazione: in un DAG, per ogni arco $(u,v)$ si ha sempre $f[u] > f[v]$.

> 📌 Perché $f[u] > f[v]$? Se all'esame di $(u,v)$ il vertice $v$ è bianco, diventa discendente di $u$ e finisce prima; se è nero, ha già finito; se fosse grigio, $(u,v)$ sarebbe un arco all'indietro e il grafo avrebbe un ciclo — escluso per ipotesi. In ogni caso ammissibile $v$ finisce prima di $u$.

Operativamente: **si inserisce ogni vertice in testa a una lista nel momento in cui viene annerito**; al termine la lista è già ordinata topologicamente, senza bisogno di alcun ordinamento esplicito.

#### **3.3. Esecuzione della DFS da $C$**

Vicini esaminati in ordine alfabetico.

| Tempo | Evento |
|---:|---|
| 1 | scoperta di $C$; vicini $A, D, F$ |
| 2 | scoperta di $A$ (arco $C\to A$); vicini $B, D$ |
| 3 | scoperta di $B$ (arco $A\to B$); $B$ non ha archi uscenti |
| 4 | **fine di $B$** |
| 5 | scoperta di $D$ (arco $A\to D$); vicini $B, E, F, G$ — $B$ è già nero |
| 6 | scoperta di $E$ (arco $D\to E$); vicino $B$, già nero |
| 7 | **fine di $E$** |
| 8 | scoperta di $F$ (arco $D\to F$); vicino $G$ |
| 9 | scoperta di $G$ (arco $F\to G$); vicino $E$, già nero |
| 10 | **fine di $G$** |
| 11 | **fine di $F$** |
| 12 | **fine di $D$** (il vicino $G$ è già nero) |
| 13 | **fine di $A$** ($D$ già nero) |
| 14 | **fine di $C$** ($D$ e $F$ già neri) |

| Vertice | $A$ | $B$ | $C$ | $D$ | $E$ | $F$ | $G$ |
|---|---:|---:|---:|---:|---:|---:|---:|
| $d$ | 2 | 3 | 1 | 5 | 6 | 8 | 9 |
| $f$ | 13 | 4 | 14 | 12 | 7 | 11 | 10 |

#### **3.4. Ordinamento risultante**

Ordinando per $f$ decrescente:

$$
C\,(14),\quad A\,(13),\quad D\,(12),\quad F\,(11),\quad G\,(10),\quad E\,(7),\quad B\,(4)
$$

$$
\boxed{C,\; A,\; D,\; F,\; G,\; E,\; B}
$$

#### **3.5. Verifica**

Un ordinamento topologico è corretto se **ogni arco va da sinistra a destra** nella sequenza. Posizioni: $C{=}1$, $A{=}2$, $D{=}3$, $F{=}4$, $G{=}5$, $E{=}6$, $B{=}7$.

| Arco | Da | A | Verifica |
|---|---:|---:|---|
| $C \to A$ | 1 | 2 | ✅ |
| $C \to D$ | 1 | 3 | ✅ |
| $C \to F$ | 1 | 4 | ✅ |
| $A \to B$ | 2 | 7 | ✅ |
| $A \to D$ | 2 | 3 | ✅ |
| $D \to B$ | 3 | 7 | ✅ |
| $D \to E$ | 3 | 6 | ✅ |
| $D \to F$ | 3 | 4 | ✅ |
| $D \to G$ | 3 | 5 | ✅ |
| $F \to G$ | 4 | 5 | ✅ |
| $G \to E$ | 5 | 6 | ✅ |
| $E \to B$ | 6 | 7 | ✅ |

Tutti i dodici archi rispettano l'ordine.

> ⚠️ L'ordinamento topologico **non è unico** in generale. Qui però $C$ è l'unico vertice senza archi entranti e $B$ l'unico senza archi uscenti, e i vincoli sono così fitti che la sequenza risulta forzata: si può verificare che ogni altro ordine violerebbe almeno un arco.

$$
\text{Complessità: } \Theta(|V| + |E|).
$$

---

### **4. Esercizio 4 — Algoritmo di Prim**

#### **4.1. Traccia**

Applicare l'algoritmo di Prim, con sorgente $A$, al grafo **non orientato pesato** con $13$ vertici e $20$ archi:

| Arco | Peso | | Arco | Peso | | Arco | Peso |
|---|---:|---|---|---:|---|---|---:|
| $A$–$B$ | 1 | | $C$–$G$ | 1 | | $H$–$L$ | 6 |
| $A$–$E$ | 2 | | $D$–$H$ | 1 | | $J$–$I$ | 5 |
| $A$–$J$ | 6 | | $E$–$C$ | 5 | | $J$–$K$ | 4 |
| $B$–$C$ | 2 | | $F$–$I$ | 6 | | $J$–$M$ | 3 |
| $B$–$D$ | 6 | | $G$–$H$ | 4 | | $K$–$L$ | 3 |
| $B$–$K$ | 5 | | $G$–$I$ | 3 | | $K$–$M$ | 5 |
| $C$–$F$ | 3 | | $G$–$L$ | 4 | | | |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Richiamo dell'algoritmo**

Prim costruisce l'albero ricoprente minimo **facendolo crescere da un unico vertice**. Mantiene per ogni vertice non ancora inserito:

- $\text{key}[v]$: il peso dell'arco più leggero che collega $v$ all'albero già costruito (inizialmente $\infty$, salvo $\text{key}[r]=0$ per la sorgente);
- $\pi[v]$: l'estremo di tale arco che appartiene all'albero.

A ogni passo si estrae dalla coda di priorità il vertice con $\text{key}$ minima, lo si aggiunge all'albero e si **rilassano** i suoi archi: per ogni vicino $v$ ancora fuori dall'albero, se $w(u,v) < \text{key}[v]$ si aggiornano $\text{key}[v] = w(u,v)$ e $\pi[v] = u$.

> 📌 Prim è corretto per la **proprietà del taglio**: dato un qualunque taglio che non attraversi archi già scelti, l'arco di peso minimo che lo attraversa appartiene a un MST. A ogni passo il taglio è quello che separa i vertici dell'albero dai rimanenti, e l'estrazione dalla coda seleziona esattamente l'arco leggero di quel taglio.

Nella tabella le celle riportano la coppia $\text{key}\,/\,\pi$ nella forma compatta "peso + predecessore" (ad esempio **1A** significa $\text{key}=1$, $\pi=A$). Le celle barrate indicano vertici già estratti.

#### **4.3. Tabella di esecuzione**

| Estratto | $B$ | $C$ | $D$ | $E$ | $F$ | $G$ | $H$ | $I$ | $J$ | $K$ | $L$ | $M$ |
|---|---|---|---|---|---|---|---|---|---|---|---|---|
| *(inizio)* | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ |
| **A** (0) | **1A** | ∞ | ∞ | **2A** | ∞ | ∞ | ∞ | ∞ | **6A** | ∞ | ∞ | ∞ |
| **B** (1) | — | **2B** | **6B** | 2A | ∞ | ∞ | ∞ | ∞ | 6A | **5B** | ∞ | ∞ |
| **C** (2) | — | — | 6B | 2A | **3C** | **1C** | ∞ | ∞ | 6A | 5B | ∞ | ∞ |
| **G** (1) | — | — | 6B | 2A | 3C | — | **4G** | **3G** | 6A | 5B | **4G** | ∞ |
| **E** (2) | — | — | 6B | — | 3C | — | 4G | 3G | 6A | 5B | 4G | ∞ |
| **F** (3) | — | — | 6B | — | — | — | 4G | 3G | 6A | 5B | 4G | ∞ |
| **I** (3) | — | — | 6B | — | — | — | 4G | — | **5I** | 5B | 4G | ∞ |
| **H** (4) | — | — | **1H** | — | — | — | — | — | 5I | 5B | 4G | ∞ |
| **D** (1) | — | — | — | — | — | — | — | — | 5I | 5B | 4G | ∞ |
| **L** (4) | — | — | — | — | — | — | — | — | 5I | **3L** | — | ∞ |
| **K** (3) | — | — | — | — | — | — | — | — | **4K** | — | — | **5K** |
| **J** (4) | — | — | — | — | — | — | — | — | — | — | — | **3J** |
| **M** (3) | — | — | — | — | — | — | — | — | — | — | — | — |

#### **4.4. Commento ai passaggi non ovvi**

**Estrazione di $C$ anziché di $E$.** Dopo l'estrazione di $B$ le chiavi minime sono $\text{key}[C]=2$ e $\text{key}[E]=2$: c'è pareggio. Si sceglie $C$; la scelta è arbitraria e non altera il peso finale dell'albero (può però cambiare l'albero prodotto, se il grafo ammette più MST).

**Aggiornamento di $J$ da 6 a 5.** Inizialmente $\text{key}[J]=6$ tramite l'arco $A$–$J$. Quando si estrae $I$, l'arco $J$–$I$ di peso $5$ è più leggero: $\text{key}[J]$ scende a $5$ con $\pi[J]=I$.

**Aggiornamento di $D$ da 6 a 1.** $D$ era raggiungibile dall'albero solo tramite $B$–$D$ (peso $6$). Quando si estrae $H$, l'arco $D$–$H$ di peso $1$ lo sostituisce: è il miglioramento più drastico dell'intera esecuzione, e mostra bene che $\text{key}$ registra sempre il **miglior collegamento noto finora**, non un valore definitivo.

**Aggiornamento di $K$ da 5 a 3.** Con l'estrazione di $L$ l'arco $K$–$L$ di peso $3$ batte $B$–$K$ di peso $5$.

**Aggiornamento di $J$ da 5 a 4 e di $M$ da 5 a 3.** L'estrazione di $K$ porta $J$ a $4$ tramite $J$–$K$; la successiva estrazione di $J$ porta $M$ a $3$ tramite $J$–$M$, migliorando il precedente $K$–$M$ di peso $5$.

> ⚠️ L'arco $F$–$I$ (peso $6$) e l'arco $H$–$L$ (peso $6$) **non** vengono mai selezionati: al momento in cui vengono esaminati, i loro estremi dispongono già di collegamenti più leggeri ($G$–$I$ di peso $3$ e $G$–$L$ di peso $4$). Sono i due archi che il taglio scarta.

#### **4.5. Albero ricoprente minimo**

Ordine di inserimento dei vertici: $A,\,B,\,C,\,G,\,E,\,F,\,I,\,H,\,D,\,L,\,K,\,J,\,M$.

| # | Arco | Peso |
|---:|---|---:|
| 1 | $A$–$B$ | 1 |
| 2 | $B$–$C$ | 2 |
| 3 | $C$–$G$ | 1 |
| 4 | $A$–$E$ | 2 |
| 5 | $C$–$F$ | 3 |
| 6 | $G$–$I$ | 3 |
| 7 | $G$–$H$ | 4 |
| 8 | $H$–$D$ | 1 |
| 9 | $G$–$L$ | 4 |
| 10 | $L$–$K$ | 3 |
| 11 | $K$–$J$ | 4 |
| 12 | $J$–$M$ | 3 |

**Numero di archi:** $12 = |V| - 1 = 13 - 1$ ✅

**Peso totale:**

$$
1+2+1+2+3+3+4+1+4+3+4+3 = 31 .
$$

#### **4.6. Complessità**

| Realizzazione della coda di priorità | Costo |
|---|---|
| vettore con ricerca lineare del minimo | $\Theta(|V|^{2})$ |
| heap binario | $O\bigl((|V|+|E|)\log|V|\bigr)$ |
| heap di Fibonacci | $O\bigl(|E| + |V|\log|V|\bigr)$ |

Su grafi **densi** conviene la versione con vettore; su grafi **sparsi** quella con heap.

> 💡 **Prim contro Kruskal.** Prim fa crescere un unico albero da un vertice e usa una coda di priorità; Kruskal ordina gli archi per peso crescente e ne unisce le componenti con una struttura MFSET, per un costo $O(|E|\log|E|)$. Entrambi sono greedy e si fondano sulla stessa proprietà del taglio; Prim è più adatto ai grafi densi, Kruskal ai grafi sparsi o quando gli archi sono già ordinati. Applicati allo stesso grafo restituiscono alberi di **peso identico**, ma non necessariamente gli stessi archi quando ci sono pesi ripetuti.

---

### **5. Esercizi assegnati per casa**

Al termine dell'esercitazione sono state assegnate tre ricorrenze:

1. $T(n) = T\!\left(\dfrac{n}{3}\right) + 5n$ — da risolvere con il **metodo esperto**;
2. $T(n) = T\!\left(\dfrac{n}{2}\right) + \log n$ — da verificare con il **metodo di sostituzione**, ipotesi $T(n) = O\!\left((\log n)^{2}\right)$;
3. $T(n) = \begin{cases} 1 & \text{se } n = 1\\ 3\,T\!\left(\dfrac{n}{2}\right) + n^{2} & \text{se } n > 1 \end{cases}$ — da risolvere con l'**albero di ricorsione**.

> 📌 Tutte e tre sono state corrette nell'esercitazione successiva: si veda [Soluzione dell'esercitazione del 21 novembre 2025](Soluzione_Esercitazione_21_11_2025.md).

---

### **6. Sintesi**

| Esercizio | Algoritmo | Risultato principale | Complessità |
|---|---|---|---|
| 1 | DFS con classificazione | 7 archi d'albero, 2 indietro, 2 avanti, 1 attraversamento; grafo **ciclico** | $\Theta(|V|+|E|)$ |
| 2 | BFS | 6 livelli, vertice più lontano $6$ a distanza $5$ | $\Theta(|V|+|E|)$ |
| 3 | Ordinamento topologico | $C, A, D, F, G, E, B$ | $\Theta(|V|+|E|)$ |
| 4 | Prim | MST di 12 archi, peso totale $31$ | $\Theta(|V|^2)$ o $O(|E|\log|V|)$ |
