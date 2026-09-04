# Soluzione dell'appello — 9 giugno 2025

Prova di **Algoritmi e Strutture Dati**, durata dichiarata: 2 ore e 30 minuti. La traccia comprende sei domande di teoria e tre esercizi.

### **1. Domande di teoria**

#### **1.1. Pesi negativi nei cammini minimi**

**Traccia.** Spiegare quando gli archi negativi creano problemi nella definizione o nel calcolo dei cammini minimi.

> **Riferimenti di teoria:** [M06 — Cammini minimi](../../M06_Impatto_delle_DS_su_complessita_algo/UD1/L1_2_Problema_dei_cammini_minimi.md), [M06 — Bellman-Ford-Moore](../../M06_Impatto_delle_DS_su_complessita_algo/UD1/L3_Algo_BellmanFordMoore_pila_PapeDesopo.md).

Un arco negativo, da solo, non rende indefinito il problema: se non esistono cicli negativi raggiungibili dalla sorgente, ogni vertice raggiungibile ammette una distanza minima finita. Rende però scorretto Dijkstra, perché una distanza dichiarata definitiva può diminuire in seguito. Nel grafo $s\to a:2$, $s\to b:5$, $b\to a:-4$, Dijkstra fisserebbe $d(a)=2$, mentre il cammino $s\to b\to a$ pesa 1.

Se invece un ciclo di peso totale negativo è raggiungibile dalla sorgente e da esso si può raggiungere $v$, non esiste un cammino minimo finito verso $v$: ripetendo il ciclo si ottengono costi arbitrariamente piccoli. Bellman-Ford rileva il fenomeno quando, dopo $|V|-1$ passate, un arco è ancora rilassabile.

#### **1.2. Notazione $\Theta(f(n))$**

Per funzioni definitivamente non negative, $g(n)\in\Theta(f(n))$ se esistono $c_1,c_2>0$ e $n_0$ tali che

$$
c_1f(n)\le g(n)\le c_2f(n)\qquad\text{per ogni }n\ge n_0.
$$

Per esempio, per $n\ge1$ vale $3n^2\le3n^2+5n+2\le10n^2$, dunque $3n^2+5n+2\in\Theta(n^2)$.

#### **1.3. Grafo orientato fortemente connesso**

Un digrafo $G=(V,E)$ è fortemente connesso se, per ogni $u,v\in V$, esistono un cammino orientato $u\leadsto v$ e un cammino orientato $v\leadsto u$. Il ciclo $a\to b\to c\to a$ è fortemente connesso; eliminando $c\to a$ non lo è più.

#### **1.4. Cancellazione in un BST**

> **Riferimento di teoria:** [M05 — Alberi binari di ricerca](../../M05_DS_Orizzontali/UD3/L1_Alberi_bilanciati_di_ricerca.md).

Dopo la ricerca del nodo $x$:

1. **nessun figlio:** si scollega $x$ dal padre;
2. **un figlio:** il figlio viene collegato direttamente al padre di $x$;
3. **due figli:** si sostituisce la chiave di $x$ con quella del suo successore, il minimo del sottoalbero destro, e si elimina il successore, che non ha figlio sinistro.

Un esempio unico copre i tre casi. Nel BST con radice 20, figli 10 e 30, figli di 10 pari a 5 e 15, e figlio destro di 30 pari a 40: 5 è una foglia; 30 ha un solo figlio; 20 ha due figli e può essere sostituito dal successore. Ogni operazione costa $O(h)$, quindi $O(\log n)$ in un albero bilanciato e $O(n)$ nel caso degenere.

#### **1.5. Complessità di `Sort`**

> **Riferimento di teoria:** [M07 — Ricorrenze](../../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md).

Per un intervallo di lunghezza $n$, ciascuna delle tre chiamate opera su circa $2n/3$ elementi; confronto, scambio e calcolo di $k$ costano $\Theta(1)$. Quindi

$$
T(n)=3T(2n/3)+\Theta(1).
$$

Nel teorema dell'esperto $a=3$, $b=3/2$ e $f(n)=\Theta(1)$ è polinomialmente minore di $n^{\log_{3/2}3}$. Pertanto

$$
\boxed{T(n)=\Theta\!\left(n^{\log_{3/2}3}\right)\approx\Theta(n^{2.710})}.
$$

Il fatto che i sottointervalli si sovrappongano non elimina chiamate: l'algoritmo non usa memoizzazione.

#### **1.6. Albero di ricorsione**

Per

$$
T(n)=2T(n/2)+\Theta(n^2),
$$

al livello $i$ vi sono $2^i$ sottoproblemi di taglia $n/2^i$. Il lavoro non ricorsivo del livello è

$$
2^i\Theta\!\left((n/2^i)^2\right)=\Theta(n^2/2^i).
$$

Sommando fino a $\log_2n-1$ si ottiene una serie geometrica limitata da $2n^2$; le $n$ foglie aggiungono $\Theta(n)$. Quindi

$$
\boxed{T(n)=\Theta(n^2)}.
$$

### **2. Esercizio 1 — B-tree di ordine 4**

**Traccia.** Inserire $4,15,2,1,10,12,8,20,16,25,30$ in un B-tree inizialmente vuoto.

Adottiamo la convenzione standard: ordine $m=4$ significa al più quattro figli e tre chiavi per nodo; i nodi non radice hanno almeno una chiave. Prima di scendere in un nodo pieno lo si divide, promuovendo la chiave mediana. La notazione $[k_1,\ldots,k_r](C_0,\ldots,C_r)$ elenca le chiavi del nodo e poi i figli.

| Chiave | B-tree dopo l'inserimento |
|---:|---|
| 4 | $[4]$ |
| 15 | $[4,15]$ |
| 2 | $[2,4,15]$ |
| 1 | $[4]([1,2],[15])$ |
| 10 | $[4]([1,2],[10,15])$ |
| 12 | $[4]([1,2],[10,12,15])$ |
| 8 | $[4,12]([1,2],[8,10],[15])$ |
| 20 | $[4,12]([1,2],[8,10],[15,20])$ |
| 16 | $[4,12]([1,2],[8,10],[15,16,20])$ |
| 25 | $[4,12,16]([1,2],[8,10],[15],[20,25])$ |
| 30 | $[12]([4]([1,2],[8,10]),[16]([15],[20,25,30]))$ |

Nell'ultimo passo la radice piena viene divisa promuovendo 12; poi 30 entra nella foglia destra. Tutte le foglie finali si trovano alla stessa profondità e ogni nodo rispetta i limiti di occupazione.

### **3. Esercizio 2 — Albero rosso-nero**

**Traccia.** Inserire $46,43,36,17,24,13,50$ e cancellare $13,17,24$.

Si usa la convenzione CLRS. `N` indica nero, `R` rosso, $\varnothing$ una foglia NIL nera; la forma compatta è `chiaveColore(sinistro,destro)`.

#### **3.1. Inserimenti**

| Operazione | Ripristino | Albero risultante |
|---:|---|---|
| $+46$ | radice nera | $46N$ |
| $+43$ | nessuno | $46N(43R,\varnothing)$ |
| $+36$ | rotazione destra su 46 e ricolorazione | $43N(36R,46R)$ |
| $+17$ | padre e zio rossi: ricolorazione | $43N(36N(17R,\varnothing),46N)$ |
| $+24$ | sinistra-destra: rotazione sinistra su 17, poi destra su 36 | $43N(24N(17R,36R),46N)$ |
| $+13$ | padre e zio rossi: ricolorazione | $43N(24R(17N(13R,\varnothing),36N),46N)$ |
| $+50$ | nessuno | $43N(24R(17N(13R,\varnothing),36N),46N(\varnothing,50R))$ |

#### **3.2. Cancellazioni**

| Operazione | Ripristino | Albero risultante |
|---:|---|---|
| $-13$ | si elimina una foglia rossa | $43N(24R(17N,36N),46N(\varnothing,50R))$ |
| $-17$ | foglia nera: il fratello 36 diventa rosso e 24 assorbe il nero aggiuntivo | $43N(24N(\varnothing,36R),46N(\varnothing,50R))$ |
| $-24$ | il solo figlio 36 prende il suo posto e diventa nero | $43N(36N,46N(\varnothing,50R))$ |

La forma finale ha radice 43 nera, figlio sinistro 36 nero e figlio destro 46 nero, con 50 rosso figlio destro di 46. Ogni cammino verso NIL ha la stessa altezza nera.

### **4. Esercizio 3 — Grafo orientato pesato in C**

**Traccia.** Definire le strutture e il prototipo di `cercaV`, che cerca per codice e restituisce il nome.

> **Riferimento di teoria:** [M04 — Programmare con grafi](../../M04_DS_Reticolari/UD1/L3_Programmare_con_grafi.md).

Ogni vertice conserva codice univoco, nome e lista degli archi uscenti. Essendo il grafo orientato, un arco $u\to v$ compare soltanto nella lista di $u$.

```c
#include <stddef.h>

typedef struct Vertex Vertex;

typedef struct AdjNode {
    Vertex *to;             /* vertice destinazione */
    double weight;          /* peso dell'arco uscente */
    struct AdjNode *next;   /* prossimo arco uscente */
} AdjNode;

struct Vertex {
    char *cod;              /* codice univoco posseduto dal grafo */
    char *name;             /* nome associato, posseduto dal grafo */
    AdjNode *out;           /* testa della lista degli archi uscenti */
    Vertex *next;           /* prossimo vertice */
};

typedef struct {
    Vertex *vertices;
    size_t vertex_count;
    size_t edge_count;
} Graph;

/* Restituisce il nome associato a cod, oppure NULL se assente. */
const char *cercaV(const Graph *g, const char *cod);
```

`cercaV` verifica gli argomenti, scorre `g->vertices`, confronta `cod` con `v->cod` mediante `strcmp` e restituisce `v->name` alla prima uguaglianza; non trasferisce la proprietà della stringa. Con la lista di vertici il costo è $O(|V|\,L)$ nel caso peggiore, dove $L$ è il costo massimo del confronto tra codici; assumendo codici di lunghezza limitata, è $O(|V|)$. Un indice hash aggiuntivo darebbe tempo atteso $O(1)$.

### **5. Nota sul documento originale**

Il PDF contiene sette pagine, ma le pagine dalla 3 alla 7 sono completamente bianche: la traccia effettiva termina a pagina 2 e non risultano esercizi omessi.

### **6. Traccia originale**

[PDF dell'appello del 9 giugno 2025](algo_09_06_2025.pdf)
