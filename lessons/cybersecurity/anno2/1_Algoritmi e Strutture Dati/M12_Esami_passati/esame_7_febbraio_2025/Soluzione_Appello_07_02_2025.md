# Soluzione dell'appello online — 7 febbraio 2025

Prova di **Algoritmi e Strutture Dati**, durata dichiarata: 2 ore e 30 minuti. La traccia comprende sette domande di teoria e tre esercizi.

### **1. Domande di teoria**

#### **1.1. Notazione $O(f(n))$**

**Traccia.** Definire formalmente $O(f(n))$ e fornire un esempio.

Per funzioni definitivamente non negative, $g(n)\in O(f(n))$ se esistono $c>0$ e $n_0\in\mathbb{N}$ tali che

$$
0\le g(n)\le c f(n)\qquad\text{per ogni }n\ge n_0.
$$

Per esempio, $3n^2+5n+2\le 10n^2$ per $n\ge1$, quindi $3n^2+5n+2\in O(n^2)$.

#### **1.2. Algoritmo non deterministico**

**Traccia.** Dare una definizione anche formale.

> **Riferimento di teoria:** [M11 — Non determinismo](../../M11_Teoria_Complessita/UD1/L2_NonDeterminismo_ed_enumerazione.md).

Un algoritmo non deterministico può avere più mosse successive ammissibili. La sua esecuzione su $x$ è un albero di computazione e $x$ viene accettato se **esiste** almeno un ramo che termina nello stato di accettazione. Equivalentemente, per un problema decisionale $L$ esiste un verificatore $V$ tale che

$$
x\in L\iff \exists y,\ |y|\le p(|x|): V(x,y)=1,
$$

dove $p$ è un polinomio. È l'astrazione “indovina un certificato e verificalo”; non significa scegliere casualmente.

#### **1.3. Grafo orientato fortemente connesso**

Un digrafo $G=(V,E)$ è fortemente connesso se per ogni coppia $u,v\in V$ esistono sia un cammino orientato da $u$ a $v$, sia uno da $v$ a $u$. Per esempio, il ciclo $a\to b\to c\to a$ è fortemente connesso; $a\to b\to c$ non lo è.

#### **1.4. Cancellazione in un BST**

> **Riferimento di teoria:** [M05 — Alberi binari di ricerca](../../M05_DS_Orizzontali/UD3/L1_Alberi_bilanciati_di_ricerca.md).

Dopo avere trovato il nodo $x$ in $O(h)$:

1. se è una foglia, lo si scollega;
2. se ha un solo figlio, il figlio prende il posto di $x$;
3. se ha due figli, si copia in $x$ la chiave del successore, cioè il minimo del sottoalbero destro, e si elimina quel successore, che ha al più un figlio.

Per esempio, nel BST con radice 20, figli 10 e 30, figli di 10 pari a 5 e 15, e figlio destro di 30 pari a 40: cancellare 5 copre il caso foglia; cancellare 30 collega direttamente 40 a 20; cancellare 20 lo sostituisce con il successore 40 dopo le operazioni precedenti. Il costo è $O(h)$: $O(\log n)$ se l'albero è bilanciato, $O(n)$ nel caso degenere.

#### **1.5. Tecnica di backtracking**

> **Riferimento di teoria:** [M09 — Progetto di algoritmi backtrack](../../M09_BackTrack/UD1/L1_Progetti_di_algoritmi_backtrack.md).

Il backtracking costruisce incrementalmente una soluzione nello spazio delle scelte. Se una soluzione parziale viola un vincolo o non può più essere completata, il ramo viene potato e si annulla l'ultima scelta. Lo schema è: scegli, verifica la promettenza, ricorri, ripristina. Esempi classici sono $n$ regine, colorazione di grafi e Sudoku. Nel caso pessimo il numero di configurazioni può essere esponenziale.

#### **1.6. Problemi NP-completi**

> **Riferimento di teoria:** [M11 — Classi P e NP](../../M11_Teoria_Complessita/UD2/L1_Classi_P_e_NP.md).

Un problema decisionale $B$ è NP-completo se:

1. $B\in NP$, quindi ogni risposta positiva ha un certificato verificabile in tempo polinomiale;
2. per ogni $A\in NP$, $A\le_p B$.

Sono quindi i problemi più difficili di NP rispetto alle riduzioni polinomiali. Se un solo problema NP-completo appartenesse a P, allora $P=NP$.

#### **1.7. Problema decisionale**

Un problema decisionale associa a ogni istanza una risposta sì/no, cioè definisce un linguaggio di istanze positive. Esempio: `PATH` riceve un grafo $G$ e due vertici $s,t$ e chiede se esista un cammino da $s$ a $t$; una DFS o BFS decide il problema in $O(|V|+|E|)$.

### **2. Esercizio 1 — Casa più vicina alla scuola**

**Traccia.** Individuare quale tra le case $A,B,C$ è più vicina alla scuola $J$.

> **Riferimento di teoria:** [M06 — Dijkstra](../../M06_Impatto_delle_DS_su_complessita_algo/UD1/L2_Algo_Dijkstra_Johnson.md).

Le strade sono non orientate e hanno lunghezza non negativa. È sufficiente eseguire Dijkstra una sola volta da $J$. Gli archi della figura sono

$$
\begin{aligned}
&AE:190,\ BD:110,\ CF:240,\ DE:90,\ DF:130,\ DG:250,\\
&EG:150,\ EH:90,\ FG:120,\ FI:80,\ GH:100,\ GI:110,\\
&GJ:250,\ HJ:300,\ IJ:280.
\end{aligned}
$$

| Estratto | $S$ | Rilassamenti efficaci |
|---|---|---|
| $J:0$ | $\{J\}$ | $G=250(J)$, $H=300(J)$, $I=280(J)$ |
| $G:250$ | $\{J,G\}$ | $E=400(G)$, $D=500(G)$, $F=370(G)$ |
| $I:280$ | $\{J,G,I\}$ | $F=360(I)$ |
| $H:300$ | $\{J,G,I,H\}$ | $E=390(H)$ |
| $F:360$ | $\{J,G,I,H,F\}$ | $C=600(F)$, $D=490(F)$ |
| $E:390$ | $\{J,G,I,H,F,E\}$ | $A=580(E)$, $D=480(E)$ |
| $D:480$ | $\{J,G,I,H,F,E,D\}$ | $B=590(D)$ |
| $A:580$ | $\{J,G,I,H,F,E,D,A\}$ | nessuno |
| $B:590$ | $\{J,G,I,H,F,E,D,A,B\}$ | nessuno |
| $C:600$ | $V$ | nessuno |

I tre cammini richiesti sono

$$
\begin{aligned}
J\to H\to E\to A &: 300+90+190=580,\\
J\to H\to E\to D\to B &: 300+90+90+110=590,\\
J\to I\to F\to C &: 280+80+240=600.
\end{aligned}
$$

Pertanto abita più vicino lo studente in $\boxed{A}$.

### **3. Esercizio 2 — HeapSort decrescente**

**Traccia.** Ordinare $[5,13,2,25,7,17,20,8,4]$ in ordine decrescente, mostrando tutti i passi.

> **Riferimento di teoria:** [M03 — HeapSort](../../M03_DS_Alberi/UD2/L2_HeapSort_e_SelectionSort.md).

Per ottenere l'ordine decrescente in-place si costruisce un **min-heap**: a ogni passo il minimo viene scambiato con l'ultima posizione della parte attiva. L'array rappresenta lo heap per livelli.

Costruzione bottom-up, applicando `restauraheap` agli indici da 3 a 0:

| Indice restaurato | Array |
|---:|---|
| 3 | $[5,13,2,4,7,17,20,8,25]$ |
| 2 | $[5,13,2,4,7,17,20,8,25]$ |
| 1 | $[5,4,2,8,7,17,20,13,25]$ |
| 0 | $[2,4,5,8,7,17,20,13,25]$ |

Durante l'ordinamento, la barra separa il min-heap attivo dalla coda già ordinata.

| Minimo collocato | Stato dopo `restauraheap` |
|---:|---|
| 2 | $[4,7,5,8,25,17,20,13]\mid[2]$ |
| 4 | $[5,7,13,8,25,17,20]\mid[4,2]$ |
| 5 | $[7,8,13,20,25,17]\mid[5,4,2]$ |
| 7 | $[8,17,13,20,25]\mid[7,5,4,2]$ |
| 8 | $[13,17,25,20]\mid[8,7,5,4,2]$ |
| 13 | $[17,20,25]\mid[13,8,7,5,4,2]$ |
| 17 | $[20,25]\mid[17,13,8,7,5,4,2]$ |
| 20 | $[25]\mid[20,17,13,8,7,5,4,2]$ |

Il risultato è

$$
\boxed{[25,20,17,13,8,7,5,4,2]}.
$$

La costruzione costa $\Theta(n)$ e le $n-1$ estrazioni costano $O(\log n)$ ciascuna: HeapSort usa $\Theta(n\log n)$ tempo e $O(1)$ spazio ausiliario.

### **4. Esercizio 3 — `pop` su pila a lista semplice**

**Traccia.** Scrivere lo pseudocodice dell'operazione `pop`.

> **Riferimenti di teoria:** [M02 — Pile](../../M02_DS_Lineari/UD2/L1/L1_Pile.md), [M02 — Programmare con pile](../../M02_DS_Lineari/UD2/L2/L2_Programmare_con_pile.md).

La testa della lista è la cima della pila.

```text
POP(P)
    if P.top = NIL
        error "underflow"
    old <- P.top
    value <- old.value
    P.top <- old.next
    dealloca(old)
    P.size <- P.size - 1
    return value
```

Il salvataggio di `old` è indispensabile per liberare il nodo dopo avere avanzato la cima. Il costo è $\Theta(1)$ nel tempo e $\Theta(1)$ nello spazio.

### **5. Verifica finale**

- I tre cammini verso $A,B,C$ sono minimi perché Dijkstra opera su pesi non negativi.
- Il min-heap attivo conserva a ogni riga la proprietà padre $\le$ figli.
- `pop` gestisce esplicitamente pila vuota, aggiornamento della cima e rilascio della memoria.

### **6. Traccia originale**

[PDF dell'appello online del 7 febbraio 2025](algo_07_02_2025.pdf)
