# Soluzione dell'appello — 17 gennaio 2025

Prova di **Algoritmi e Strutture Dati**, durata dichiarata: 2 ore e 30 minuti. La traccia comprende sei domande di teoria e tre esercizi.

### **1. Domande di teoria**

#### **1.1. Fattore di bilanciamento di un AVL**

**Traccia.** Definire il fattore di bilanciamento e calcolarlo su un AVL di altezza almeno 3.

> **Riferimento di teoria:** [M05 — Alberi bilanciati di ricerca](../../M05_DS_Orizzontali/UD3/L1_Alberi_bilanciati_di_ricerca.md).

Ponendo $h(\varnothing)=-1$, il fattore di bilanciamento del nodo $v$ è

$$
bf(v)=h(v.sinistro)-h(v.destro).
$$

Un albero binario di ricerca è AVL se per ogni nodo $bf(v)\in\{-1,0,1\}$. Consideriamo l'albero descritto dalle relazioni seguenti: radice 8; figli di 8: 4 e 12; figli di 4: 2 e 6; figli di 12: 10 e 14; figlio sinistro di 2: 1. Il cammino $8,4,2,1$ ha tre archi, quindi l'altezza è 3.

| Nodo | Altezza sinistra | Altezza destra | $bf$ |
|---:|---:|---:|---:|
| 1, 6, 10, 14 | $-1$ | $-1$ | 0 |
| 2 | 0 | $-1$ | 1 |
| 4 | 1 | 0 | 1 |
| 12 | 0 | 0 | 0 |
| 8 | 2 | 1 | 1 |

Tutti i fattori hanno valore assoluto al più 1. Se si adotta la convenzione opposta, $h(destro)-h(sinistro)$, cambiano soltanto i segni.

#### **1.2. Algoritmo di Kruskal**

**Traccia.** Spiegare scopo e complessità di Kruskal.

> **Riferimenti di teoria:** [M08 — Kruskal](../../M08_Greedy/UD2/L1_Algoritmo_di_Kruskal.md), [M05 — MFSET](../../M05_DS_Orizzontali/UD1/L3_MFSET.md).

Kruskal trova un albero ricoprente di costo minimo di un grafo non orientato, connesso e pesato. Ordina gli archi per peso non decrescente e aggiunge un arco soltanto se collega due componenti distinte; `TROVA` e `FONDI` impediscono la formazione di cicli. Termina dopo avere scelto $|V|-1$ archi.

L'ordinamento costa $O(|E|\log |E|)$; le operazioni MFSET con unione per rango e compressione dei cammini costano complessivamente $O(|E|\alpha(|V|))$. Domina quindi l'ordinamento:

$$
O(|E|\log |E|)=O(|E|\log |V|)
$$

per un grafo semplice.

#### **1.3. Notazioni $O$, $\omega$ e $\Theta$**

**Traccia.** Dare le definizioni formali.

Siano $f,g:\mathbb{N}\to\mathbb{R}_{\ge 0}$ definitivamente non negative.

- $g(n)\in O(f(n))$ se esistono $c>0$ e $n_0$ tali che $g(n)\le c f(n)$ per ogni $n\ge n_0$;
- $g(n)\in\omega(f(n))$ se per ogni $c>0$ esiste $n_0$ tale che $g(n)>c f(n)$ per ogni $n\ge n_0$;
- $g(n)\in\Theta(f(n))$ se esistono $c_1,c_2>0$ e $n_0$ tali che $c_1f(n)\le g(n)\le c_2f(n)$ per ogni $n\ge n_0$.

Per esempio, $3n^2+5n+2\in\Theta(n^2)$ e dunque anche in $O(n^2)$, mentre $n^3\in\omega(n^2)$.

#### **1.4. Classificazione degli archi in DFS**

**Traccia.** Definire archi d'albero, all'indietro, in avanti e di attraversamento, con un esempio.

> **Riferimento di teoria:** [M04 — Proprietà della DFS](../../M04_DS_Reticolari/UD2/L3_Proprieta_grafo.md).

In un grafo orientato, rispetto alla foresta DFS:

- un **arco d'albero** scopre un vertice bianco;
- un **arco all'indietro** va a un antenato ancora grigio;
- un **arco in avanti** va a un discendente già visitato, ma non è nell'albero;
- un **arco di attraversamento** collega vertici senza relazione antenato-discendente, tipicamente verso un vertice nero di un altro ramo.

Esempio: siano gli archi $s\to a$, $a\to b$, $b\to s$, $s\to b$, $c\to b$ e si avvii la DFS da $s$, visitando poi $c$. Gli archi $s\to a$ e $a\to b$ sono d'albero, $b\to s$ è all'indietro, $s\to b$ è in avanti e $c\to b$ è di attraversamento.

#### **1.5. Ricorrenza con il teorema dell'esperto**

**Traccia.** Risolvere $T(n)=2T(n/3)+(\log n)^2$.

> **Riferimento di teoria:** [M07 — Complessità degli algoritmi ricorsivi](../../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md).

Qui $a=2$, $b=3$ e

$$
n^{\log_b a}=n^{\log_3 2}.
$$

Poiché $(\log n)^2=O(n^{\log_3 2-\varepsilon})$ per un opportuno $\varepsilon>0$, vale il primo caso del teorema dell'esperto:

$$
\boxed{T(n)=\Theta\!\left(n^{\log_3 2}\right)}.
$$

#### **1.6. Complessità dello pseudocodice**

Il `for` esterno esegue $\Theta(N)$ iterazioni. Per ciascuna, `j` assume le potenze di 2 minori di $N$, quindi il primo `while` itera $\Theta(\log_2N)$ volte. Per ogni valore di `j`, `k` assume le potenze di 3 minori di $N$, per $\Theta(\log_3N)$ iterazioni. Ne segue

$$
\boxed{\Theta(N\log_2N\log_3N)=\Theta\!\left(N(\log N)^2\right)}.
$$

Le inizializzazioni e gli incrementi non modificano l'ordine asintotico.

### **2. Esercizio 1 — Dijkstra da A**

**Traccia.** Calcolare tutti i cammini minimi dalla sorgente $A$ e il cammino minimo da $A$ ad $H$.

> **Riferimento di teoria:** [M06 — Dijkstra](../../M06_Impatto_delle_DS_su_complessita_algo/UD1/L2_Algo_Dijkstra_Johnson.md).

Dal grafo orientato della traccia si leggono gli archi pesati:

$$
\begin{aligned}
&A\to B:2,\ A\to C:5,\ A\to F:20,\\
&B\to D:8,\ B\to E:22,\ D\to C:6,\ D\to E:3,\\
&C\to G:4,\ G\to F:4,\ G\to H:12,\\
&F\to C:9,\ F\to H:1,\ H\to D:11,\ H\to E:5,\ E\to H:4.
\end{aligned}
$$

La tabella riporta soltanto le etichette che diventano definitive o cambiano; tra parentesi compare il predecessore. A parità di etichetta si estrae in ordine alfabetico.

| Estratto | $S$ dopo l'estrazione | Rilassamenti efficaci |
|---|---|---|
| $A:0$ | $\{A\}$ | $B=2(A)$, $C=5(A)$, $F=20(A)$ |
| $B:2$ | $\{A,B\}$ | $D=10(B)$, $E=24(B)$ |
| $C:5$ | $\{A,B,C\}$ | $G=9(C)$ |
| $G:9$ | $\{A,B,C,G\}$ | $F=13(G)$, $H=21(G)$ |
| $D:10$ | $\{A,B,C,G,D\}$ | $E=13(D)$ |
| $E:13$ | $\{A,B,C,G,D,E\}$ | $H=17(E)$ |
| $F:13$ | $\{A,B,C,G,D,E,F\}$ | $H=14(F)$ |
| $H:14$ | $V$ | nessuno |

Le distanze e i predecessori finali sono:

| Vertice | $A$ | $B$ | $C$ | $D$ | $E$ | $F$ | $G$ | $H$ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| Distanza | 0 | 2 | 5 | 10 | 13 | 13 | 9 | 14 |
| Predecessore | — | $A$ | $A$ | $B$ | $D$ | $G$ | $C$ | $F$ |

L'albero dei cammini minimi contiene $A\to B$, $A\to C$, $B\to D$, $D\to E$, $C\to G$, $G\to F$, $F\to H$. In particolare,

$$
\boxed{A\to C\to G\to F\to H,qquad 5+4+4+1=14}.
$$

### **3. Esercizio 2 — Inserimenti in un albero rosso-nero**

**Traccia.** Inserire, nell'ordine, $11,21,8,18,20,28,43,83$.

Usiamo la convenzione CLRS: i nuovi nodi sono rossi, la radice e le foglie NIL sono nere. Nella notazione compatta `chiaveColore(sinistro,destro)`, `N` significa nero, `R` rosso e $\varnothing$ una foglia NIL.

| Inserimento | Trasformazione di ripristino | Albero dopo il ripristino |
|---:|---|---|
| 11 | la radice diventa nera | $11N(\varnothing,\varnothing)$ |
| 21 | nessuna: il padre è nero | $11N(\varnothing,21R)$ |
| 8 | nessuna: il padre è nero | $11N(8R,21R)$ |
| 18 | padre 21 e zio 8 rossi: entrambi neri | $11N(8N,21N(18R,\varnothing))$ |
| 20 | caso sinistra-destra: rotazione sinistra su 18, poi destra su 21 e ricolorazione | $11N(8N,20N(18R,21R))$ |
| 28 | padre 21 e zio 18 rossi: ricolorazione | $11N(8N,20R(18N,21N(\varnothing,28R)))$ |
| 43 | caso destra-destra: rotazione sinistra su 21 | $11N(8N,20R(18N,28N(21R,43R)))$ |
| 83 | prima si ricolorano 21 e 43; poi rotazione sinistra su 11 e ricolorazione | $20N(11R(8N,18N),28R(21N,43N(\varnothing,83R)))$ |

La forma finale, espressa per livelli, è: $[20N]$; $[11R,28R]$; $[8N,18N,21N,43N]$; sotto 43 compare il figlio destro $83R$. Ogni cammino dalla radice a NIL contiene tre nodi neri contando la NIL, e nessun nodo rosso ha un figlio rosso.

### **4. Esercizio 3 — Grafo non orientato pesato in C**

**Traccia.** Progettare una rappresentazione a liste di adiacenza e il prototipo di `add_edge`, senza implementare la funzione.

> **Riferimento di teoria:** [M04 — Realizzazioni dei grafi](../../M04_DS_Reticolari/UD1/L2_Grafi_specifica_e_realizzazioni.md).

I vertici sono numerati da 0 a $n-1$. Il vettore `adj` contiene una testa di lista per vertice; poiché il grafo è non orientato, l'arco $\{u,v\}$ è memorizzato due volte, come $u\to v$ e $v\to u$.

```c
#include <stddef.h>
#include <stdbool.h>

typedef struct AdjNode {
    size_t to;              /* indice del vertice adiacente */
    double weight;          /* peso dell'arco */
    struct AdjNode *next;   /* prossimo elemento della lista */
} AdjNode;

typedef struct {
    size_t vertex_count;    /* numero di vertici: 0..vertex_count-1 */
    size_t edge_count;      /* numero di archi non orientati */
    AdjNode **adj;          /* vettore delle teste di lista */
} Graph;

/* Restituisce true se inserisce entrambe le copie dell'arco. */
bool add_edge(Graph *g, size_t u, size_t v, double weight);
```

La funzione verifica `g != NULL`, $u,v<g->vertex_count$ e la validità del peso; alloca due `AdjNode`, li collega in testa a `g->adj[u]` e `g->adj[v]`, incrementa `edge_count` una sola volta e restituisce `false` senza modificare il grafo se una delle allocazioni fallisce. Con inserimento in testa e senza ricerca di duplicati il costo è $\Theta(1)$; se il contratto vieta archi duplicati, il controllo costa $O(\deg(u)+\deg(v))$.

> ⚠️ Inserire una sola copia trasformerebbe la rappresentazione in quella di un grafo orientato.

### **5. Verifica finale**

- Tutte le etichette di Dijkstra sono definitive perché i pesi sono non negativi.
- L'albero rosso-nero finale rispetta ordinamento, radice nera, assenza di coppie rosso-rosso e uguale altezza nera.
- La rappresentazione C distingue correttamente numero di archi e numero di elementi nelle liste.

### **6. Traccia originale**

[PDF dell'appello del 17 gennaio 2025](algo_17_01_2025.pdf)
