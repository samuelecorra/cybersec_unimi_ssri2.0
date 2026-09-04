# Soluzione dell'appello — 28 luglio 2025

Prova di **Algoritmi e Strutture Dati**, durata dichiarata: 2 ore e 30 minuti. La traccia comprende sette domande di teoria e tre esercizi.

### **1. Domande di teoria**

#### **1.1. Specifica del tipo di dato pila**

> **Riferimento di teoria:** [M02 — Pile](../../M02_DS_Lineari/UD2/L1/L1_Pile.md).

Una specifica sintattica essenziale è:

```text
CREAPILA: () -> pila
PILAVUOTA: pila -> booleano
INPILA: (elemento, pila) -> pila
LEGGIPILA: pila -> elemento
FUORIPILA: pila -> pila
```

Rappresentando una pila come sequenza con la cima a sinistra, la semantica è

$$
\begin{aligned}
CREAPILA()&=\varepsilon,\\
INPILA(x,S)&=x\cdot S,\\
PILAVUOTA(S)&\iff S=\varepsilon,\\
LEGGIPILA(x\cdot S)&=x,\\
FUORIPILA(x\cdot S)&=S.
\end{aligned}
$$

Le ultime due operazioni hanno precondizione $S\ne\varepsilon$. La politica è LIFO.

#### **1.2. Previsita, invisita e postvisita**

> **Riferimento di teoria:** [M03 — Visite di alberi](../../M03_DS_Alberi/UD1/L2_Alberi_Algoritmi_visita.md).

Per un albero binario con radice $r$, sottoalbero sinistro $L$ e destro $R$:

- previsita: $r,L,R$;
- invisita: $L,r,R$;
- postvisita: $L,R,r$.

Esempio: radice 8; figli 4 e 12; figli di 4: 2 e 6; figli di 12: 10 e 14.

| Visita | Ordine |
|---|---|
| Previsita | $8,4,2,6,12,10,14$ |
| Invisita | $2,4,6,8,10,12,14$ |
| Postvisita | $2,6,4,10,14,12,8$ |

#### **1.3. Catene e circuiti**

> **Riferimento di teoria:** [M04 — Grafi, definizioni](../../M04_DS_Reticolari/UD1/L1_Grafi_definizioni_e_modelli.md).

Una catena è una sequenza $u_0,u_1,\ldots,u_k$ in cui ogni coppia consecutiva è un arco. È **semplice** se non ripete vertici; è **chiusa** se $u_0=u_k$; è un **circuito** se è chiusa e, salvo la coincidenza tra primo e ultimo vertice, non ripete vertici né archi. Nel triangolo con archi $ab,bc,ca$: $a,b,c$ è semplice; $a,b,c,a,b,a$ è chiusa ma non un circuito semplice; $a,b,c,a$ è un circuito.

#### **1.4. Cancellazione in un BST**

> **Riferimento di teoria:** [M05 — Alberi binari di ricerca](../../M05_DS_Orizzontali/UD3/L1_Alberi_bilanciati_di_ricerca.md).

Trovato il nodo $x$, se è foglia lo si scollega; se ha un solo figlio, il figlio prende il suo posto; se ha due figli, la chiave viene sostituita con il successore, minimo del sottoalbero destro, che viene poi eliminato. Per esempio, nel BST con radice 20, figli 10 e 30, figli di 10 pari a 5 e 15, e figlio destro di 30 pari a 40, le cancellazioni di 5, 30 e 20 mostrano rispettivamente i tre casi. Il costo è $O(h)$.

#### **1.5. Dijkstra**

> **Riferimento di teoria:** [M06 — Dijkstra](../../M06_Impatto_delle_DS_su_complessita_algo/UD1/L2_Algo_Dijkstra_Johnson.md).

Dijkstra calcola i cammini minimi da una sorgente in un grafo pesato. Mantiene distanze provvisorie, estrae il vertice di etichetta minima e rilassa i suoi archi. È corretto soltanto se tutti i pesi sono non negativi: con archi negativi un vertice già fissato potrebbe ricevere in seguito una distanza minore. Costa $O(|V|^2)$ con vettore e matrice, oppure $O((|V|+|E|)\log|V|)$ con liste e heap binario.

#### **1.6. Backtracking**

> **Riferimento di teoria:** [M09 — Backtracking](../../M09_BackTrack/UD1/L1_Progetti_di_algoritmi_backtrack.md).

Il backtracking esplora l'albero delle scelte costruendo soluzioni parziali. Appena un vincolo dimostra che un prefisso non può portare a una soluzione, il ramo viene potato; dopo la chiamata ricorsiva si annulla la scelta. Nell'esempio delle $n$ regine si colloca una regina per riga e si prosegue soltanto se colonna e diagonali sono libere.

#### **1.7. Problemi NP-completi**

> **Riferimento di teoria:** [M11 — Classi P e NP](../../M11_Teoria_Complessita/UD2/L1_Classi_P_e_NP.md).

Un problema decisionale $B$ è NP-completo se $B\in NP$ e ogni problema $A\in NP$ è riducibile a $B$ in tempo polinomiale. Le risposte positive di $B$ sono quindi verificabili in tempo polinomiale e $B$ è NP-hard. Se un problema NP-completo fosse in P, seguirebbe $P=NP$.

### **2. Esercizio 1 — DFS da A**

**Traccia.** Visitare il digrafo da $A$, con liste di adiacenza alfabetiche; riportare tempi e classificazione di tutti gli archi.

> **Riferimenti di teoria:** [M04 — DFS](../../M04_DS_Reticolari/UD2/L1_Esplorazione_grafo.md), [M04 — Classificazione degli archi](../../M04_DS_Reticolari/UD2/L3_Proprieta_grafo.md).

Dalla figura si leggono le liste alfabetiche:

- $A:(B,F,H,I)$; $B:(C,E)$; $C:()$; $D:(M)$; $E:(L)$; $F:(D)$;
- $G:(A,E,H,J)$; $H:()$; $I:()$; $J:(K,M)$; $K:(I)$; $L:(G)$; $M:()$.

Incrementando il tempo sia alla scoperta sia alla fine visita, l'ordine di scoperta è

$$
\boxed{A,B,C,E,L,G,H,J,K,I,M,F,D}.
$$

| Vertice | $t_1/t_2$ | Padre DFS |
|---|---:|---|
| $A$ | $1/26$ | — |
| $B$ | $2/21$ | $A$ |
| $C$ | $3/4$ | $B$ |
| $E$ | $5/20$ | $B$ |
| $L$ | $6/19$ | $E$ |
| $G$ | $7/18$ | $L$ |
| $H$ | $8/9$ | $G$ |
| $J$ | $10/17$ | $G$ |
| $K$ | $11/14$ | $J$ |
| $I$ | $12/13$ | $K$ |
| $M$ | $15/16$ | $J$ |
| $F$ | $22/25$ | $A$ |
| $D$ | $23/24$ | $F$ |

| Classe | Archi |
|---|---|
| Albero | $A\to B$, $B\to C$, $B\to E$, $E\to L$, $L\to G$, $G\to H$, $G\to J$, $J\to K$, $K\to I$, $J\to M$, $A\to F$, $F\to D$ |
| All'indietro | $G\to A$, $G\to E$ |
| In avanti | $A\to H$, $A\to I$ |
| Attraversamento | $D\to M$ |

Gli intervalli temporali confermano le classi: per un arco in avanti l'intervallo del destinatario è contenuto in quello della sorgente; per $D\to M$, invece, $M$ è già nero e i due intervalli sono disgiunti.

### **3. Esercizio 2 — HeapSort decrescente**

**Traccia.** Ordinare $[4,1,3,2,16,9,10,14,8,7]$ e mostrare lo heap come albero binario.

> **Riferimento di teoria:** [M03 — HeapSort](../../M03_DS_Alberi/UD2/L2_HeapSort_e_SelectionSort.md).

Per l'ordine decrescente si usa un min-heap. La costruzione bottom-up produce

$$
[1,2,3,4,7,9,10,14,8,16].
$$

La sua forma per livelli è $[1]$; $[2,3]$; $[4,7,9,10]$; $[14,8,16]$. La tabella riporta dopo ogni estrazione i livelli del min-heap ancora attivo; la coda ordinata è a destra della barra.

| Minimo collocato | Livelli dello heap attivo | Coda ordinata |
|---:|---|---|
| 1 | $[2];[4,3];[8,7,9,10];[14,16]$ | $[1]$ |
| 2 | $[3];[4,9];[8,7,16,10];[14]$ | $[2,1]$ |
| 3 | $[4];[7,9];[8,14,16,10]$ | $[3,2,1]$ |
| 4 | $[7];[8,9];[10,14,16]$ | $[4,3,2,1]$ |
| 7 | $[8];[10,9];[16,14]$ | $[7,4,3,2,1]$ |
| 8 | $[9];[10,14];[16]$ | $[8,7,4,3,2,1]$ |
| 9 | $[10];[16,14]$ | $[9,8,7,4,3,2,1]$ |
| 10 | $[14];[16]$ | $[10,9,8,7,4,3,2,1]$ |
| 14 | $[16]$ | $[14,10,9,8,7,4,3,2,1]$ |

Il risultato è

$$
\boxed{[16,14,10,9,8,7,4,3,2,1]}.
$$

Costruzione $\Theta(n)$, ordinamento $\Theta(n\log n)$, spazio ausiliario $O(1)$.

### **4. Esercizio 3 — Tre valori con estremi a distanza x**

**Traccia.** Stabilire se esistono $i,j,k$ tali che $A[i]<A[j]<A[k]$ e $A[k]-A[i]=x$.

Poiché i valori sono distinti e non è richiesto $i<j<k$, dopo l'ordinamento basta trovare due estremi distanti $x$ con almeno un elemento tra loro.

```text
TRE_VALORI(A, n, x)
    if n < 3 or x <= 0
        return false
    ORDINA_CRESCENTE(A)
    for i <- 0 to n-3
        k <- RICERCA_BINARIA(A, i+1, n-1, A[i] + x)
        if k != NON_TROVATO and k >= i+2
            return true
    return false
```

Se l'algoritmo restituisce `true`, scegliendo qualunque $j$ con $i<j<k$ si ha $A[i]<A[j]<A[k]$ e la differenza richiesta. Viceversa, ogni terna valida produce, dopo l'ordinamento, due estremi separati da almeno una posizione e la ricerca li trova. Il costo è $O(n\log n)$ per ordinamento e ricerche, con spazio dipendente dall'algoritmo di ordinamento scelto.

### **5. Verifica finale**

- La DFS classifica tutti e soli i 17 archi della figura.
- Ogni riga di HeapSort conserva la proprietà di min-heap nella parte attiva.
- L'algoritmo del terzo esercizio usa l'ipotesi di valori distinti per garantire la disuguaglianza stretta dell'elemento intermedio.

### **6. Traccia originale**

[PDF dell'appello del 28 luglio 2025](algo28_07_2025.pdf)
