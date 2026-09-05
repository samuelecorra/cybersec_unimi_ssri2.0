# Soluzione dell'esercitazione — 12 dicembre 2025

Esercitazione di **Algoritmi e Strutture Dati** su **flusso massimo** in una rete, esercizi "inversi" su alberi e tabelle hash (dedurre l'input dall'output) e due ricorrenze risolte con il metodo esperto.

> **Riferimenti di teoria:** [M04/UD1 — Grafi: definizioni e modelli](../M04_DS_Reticolari/UD1/L1_Grafi_definizioni_e_modelli.md), [M04/UD2 — Esplorazione di un grafo](../M04_DS_Reticolari/UD2/L1_Esplorazione_grafo.md), [M05/UD2 — Tabelle Hash](../M05_DS_Orizzontali/UD2/L2_Tabelle_Hash.md), [M03/UD2 — Heap](../M03_DS_Alberi/UD2/L1_Heap.md), [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md).

---

### **1. Esercizio 1 — Flusso massimo e taglio minimo**

#### **1.1. Traccia**

Data la rete di flusso con sorgente $s$, pozzo $t$ e vertici $\{a, B, C, d, E, f\}$, verificare il flusso indicato, stabilire se è massimo e individuare un taglio minimo.

| Arco | Capacità | Flusso | | Arco | Capacità | Flusso |
|---|---:|---:|---|---|---:|---:|
| $s \to a$ | 10 | 8 | | $B \to E$ | 4 | 2 |
| $s \to B$ | 8 | 8 | | $B \to C$ | 3 | 0 |
| $s \to C$ | 5 | 5 | | $C \to E$ | 8 | 5 |
| $a \to d$ | 5 | 4 | | $d \to E$ | 3 | 0 |
| $a \to B$ | 2 | 0 | | $d \to t$ | 10 | 10 |
| $a \to E$ | 4 | 4 | | $E \to t$ | 4 | 4 |
| $B \to d$ | 6 | 6 | | $E \to f$ | 7 | 7 |
| | | | | $f \to t$ | 10 | 7 |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Richiamo: le due condizioni di ammissibilità**

Un flusso $f$ su una rete è **ammissibile** se soddisfa:

1. **vincolo di capacità:** $0 \le f(u,v) \le c(u,v)$ per ogni arco;
2. **conservazione del flusso:** per ogni vertice $v$ diverso da $s$ e $t$, il flusso entrante uguaglia quello uscente.

Il **valore** del flusso è la quantità netta che esce dalla sorgente (o, equivalentemente, che entra nel pozzo):

$$
|f| = \sum_{v} f(s,v) - \sum_{v} f(v,s).
$$

#### **1.3. Verifica della conservazione**

| Vertice | Flusso entrante | Flusso uscente | Bilanciato? |
|---|---|---|---|
| $a$ | $8$ (da $s$) | $4 + 0 + 4 = 8$ | ✅ |
| $B$ | $8 + 0 = 8$ | $6 + 2 + 0 = 8$ | ✅ |
| $C$ | $5 + 0 = 5$ | $5$ | ✅ |
| $d$ | $4 + 6 = 10$ | $0 + 10 = 10$ | ✅ |
| $E$ | $4 + 2 + 5 + 0 = 11$ | $4 + 7 = 11$ | ✅ |
| $f$ | $7$ | $7$ | ✅ |

Il vincolo di capacità è rispettato su tutti gli archi (nessun flusso supera la propria capacità).

#### **1.4. Valore del flusso**

$$
|f| = f(s,a) + f(s,B) + f(s,C) = 8 + 8 + 5 = \boxed{21}
$$

Controllo dal lato del pozzo:

$$
f(d,t) + f(E,t) + f(f,t) = 10 + 4 + 7 = 21
$$

#### **1.5. Il flusso è massimo? Il teorema max-flow min-cut**

Un **taglio** $(S,T)$ è una partizione dei vertici con $s \in S$ e $t \in T$; la sua **capacità** è la somma delle capacità degli archi che vanno da $S$ a $T$ (gli archi in senso contrario non si contano). Il teorema di Ford-Fulkerson afferma che

$$
\max_{f} |f| \;=\; \min_{(S,T)} c(S,T),
$$

e che un flusso è massimo **se e solo se** esiste un taglio di capacità pari al suo valore, ovvero se e solo se la rete residua non contiene cammini aumentanti.

**Si consideri il taglio**

$$
S = \{s,\, a,\, B,\, C,\, d,\, E\}, \qquad T = \{f,\, t\}.
$$

Gli archi che attraversano il taglio da $S$ verso $T$ sono:

| Arco | Capacità | Flusso | Saturo? |
|---|---:|---:|---|
| $d \to t$ | 10 | 10 | ✅ |
| $E \to t$ | 4 | 4 | ✅ |
| $E \to f$ | 7 | 7 | ✅ |
| **Totale** | **21** | **21** | |

Non esistono archi da $T$ verso $S$ (né $f$ né $t$ hanno archi uscenti verso $S$).

$$
c(S,T) = 10 + 4 + 7 = 21 = |f|
$$

Poiché il valore del flusso uguaglia la capacità di un taglio, per il teorema **il flusso è massimo e il taglio è minimo**.

$$
\boxed{|f_{\max}| = 21, \qquad \text{taglio minimo } (\{s,a,B,C,d,E\},\{f,t\})}
$$

> 📌 Il criterio operativo per riconoscere un taglio minimo: **tutti** gli archi da $S$ a $T$ devono essere **saturi** e **tutti** quelli da $T$ a $S$ devono avere flusso **nullo**. Qui la prima condizione è verificata sui tre archi e la seconda è banale perché non ci sono archi entranti in $S$.

#### **1.6. Perché i cammini indicati non sono aumentanti**

Gli appunti considerano due cammini candidati:

**$s \to B \to E \to t$.** L'arco $s \to B$ ha flusso $8$ su capacità $8$: è **saturo**, capacità residua nulla. Il cammino è impraticabile già al primo arco.

**$s \to B \to E \to f \to t$.** Stesso problema all'origine; inoltre anche $E \to f$ è saturo ($7/7$). Il cammino è doppiamente bloccato.

> 💡 Nella **rete residua** un cammino aumentante può usare due tipi di archi: quelli **diretti** con capacità residua $c(u,v) - f(u,v) > 0$, e quelli **all'indietro** che annullano flusso già instradato, con capacità residua $f(v,u) > 0$. Ford-Fulkerson cerca cammini in questa rete; quando non ne trova più, il flusso è massimo e l'insieme dei vertici raggiungibili da $s$ nella rete residua è esattamente il lato $S$ del taglio minimo.
>
> Verifica su questa istanza: da $s$ nella rete residua si raggiunge $a$ (residuo $10-8=2$), da $a$ si raggiunge $B$ (residuo $2$) e $d$ (residuo $1$), da $d$ si raggiunge $E$ (residuo $3$), da $C$ si raggiunge $E$ (residuo $3$). Si raggiungono dunque $\{s,a,B,C,d,E\}$ e **non** $f$ né $t$: è esattamente il taglio individuato. ✅

$$
\text{Complessità di Edmonds-Karp: } O(|V|\,|E|^{2}).
$$

---

### **2. Esercizio 2 — Posizione di minimo, mediano e massimo in un albero**

#### **2.1. Traccia**

Dato l'albero binario con etichette

- **H**
  - **D**
    - **B** → sinistro: **A**, destro: **C**
    - **F** → sinistro: **E**, destro: **G**
  - **L**
    - **K**
    - **M**

individuare la posizione del valore più piccolo, di quello mediano e di quello più grande.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Il testo della domanda non è riportato negli appunti**: si vedono solo l'albero, una sequenza di lettere e tre evidenziazioni (H per il minimo, K per il mediano, G per il massimo). Non è possibile ricostruire con certezza quale struttura dati fosse dichiarata nella traccia. Si riportano perciò la sequenza verificabile e la discussione delle due letture possibili, senza inventare la formulazione mancante.

#### **2.2. La sequenza riportata: visita per livelli**

La sequenza scritta sotto l'albero è

$$
H,\; D,\; L,\; B,\; F,\; K,\; M,\; A,\; C,\; E,\; G
$$

che è esattamente la **visita per livelli** (BFS) dell'albero: prima la radice, poi i nodi di profondità $1$ da sinistra a destra, poi quelli di profondità $2$, infine quelli di profondità $3$.

| Livello | Nodi |
|---:|---|
| $0$ | $H$ |
| $1$ | $D$, $L$ |
| $2$ | $B$, $F$, $K$, $M$ |
| $3$ | $A$, $C$, $E$, $G$ |

La visita per livelli si realizza con una **coda**: si accoda la radice, poi ripetutamente si estrae un nodo, lo si visita e si accodano i suoi figli. Costa $\Theta(n)$ e usa $O(n)$ spazio nel caso peggiore (l'ultimo livello di un albero completo contiene circa $n/2$ nodi).

#### **2.3. Le due letture possibili**

**Se l'albero è un MIN-HEAP** (interpretazione compatibile con le evidenziazioni):

- il **minimo** sta necessariamente nella **radice**, cioè in $H$ — è la proprietà definitoria dell'heap;
- il **massimo** sta necessariamente in una **foglia**: le foglie sono $A, C, E, G, K, M$, e $G$ è una di queste. Il massimo non può stare in un nodo interno, perché avrebbe un figlio con valore maggiore, contro la proprietà di heap;
- il **mediano** può trovarsi **in qualunque posizione** tranne la radice: l'heap ordina solo lungo i cammini radice-foglia, non fra rami diversi. $K$ è una posizione possibile fra tante.

**Se l'albero fosse un albero binario di ricerca**, le risposte sarebbero diverse e determinate univocamente: il minimo è il nodo più a **sinistra** ($A$), il massimo il più a **destra** ($M$), e il mediano è l'elemento centrale della visita simmetrica.

> 📌 La distinzione è concettualmente importante: **BST e heap ordinano in modo diverso**. Il BST impone un ordine *orizzontale* (sinistra $<$ nodo $<$ destra) e permette la ricerca binaria; l'heap impone un ordine *verticale* (padre $\le$ figli) e permette solo l'accesso $O(1)$ all'estremo. Nell'heap trovare un elemento qualsiasi costa $\Theta(n)$, nel BST bilanciato $O(\log n)$.

---

### **3. Esercizio 3 — Ricostruire la sequenza di inserimento in una tabella hash**

#### **3.1. Traccia**

Data la tabella hash risultante

| Indice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Contenuto | 9 | 18 | | 12 | 3 | 14 | 4 | 21 | |

ottenuta con $h(x) = x \bmod n$ e **scansione lineare**, stabilire quale delle seguenti sequenze di inserimento può averla prodotta:

| | Sequenza |
|---|---|
| (a) | $9,\; 14,\; 4,\; 18,\; 12,\; 3,\; 21$ |
| (b) | $12,\; 3,\; 14,\; 18,\; 4,\; 9,\; 21$ |
| (c) | $12,\; 14,\; 3,\; 9,\; 4,\; 18,\; 21$ |
| (d) | $9,\; 12,\; 14,\; 3,\; 4,\; 21,\; 18$ |
| (e) | $12,\; 9,\; 18,\; 3,\; 14,\; 21,\; 4$ |

#### **3.2. Primo passo: determinare $n$**

La traccia non fornisce $n$: va dedotto dalla tabella. Le chiavi presenti sono $9, 18, 12, 3, 14, 4, 21$ e le posizioni occupate sono $0,1,3,4,5,6,7$.

Il candidato immediato $n = 10$ va escluso subito: si avrebbe $9 \bmod 10 = 9$, ma la posizione $9$ è vuota e il $9$ si trova in posizione $0$.

Si prova **$n = 9$**:

| $x$ | 9 | 18 | 12 | 3 | 14 | 4 | 21 |
|---|---:|---:|---:|---:|---:|---:|---:|
| $x \bmod 9$ | 0 | 0 | 3 | 3 | 5 | 4 | 3 |

Le posizioni naturali sono coerenti con la tabella: $9$ e $18$ si contendono lo $0$, mentre $12$, $3$ e $21$ si contendono il $3$. Le celle occupate ($0,1,3,4,5,6,7$) sono esattamente quelle raggiungibili per scansione lineare a partire da $0$ e da $3$.

$$
\boxed{n = 9}
$$

#### **3.3. Verifica di ciascuna sequenza**

**(a) $9, 14, 4, 18, 12, 3, 21$**

| Chiave | $h'$ | Esito |
|---:|---:|---|
| $9$ | 0 | libera → **0** ✅ |
| $14$ | 5 | libera → **5** ✅ |
| $4$ | 4 | libera → **4** ✗ |

La tabella richiede il $3$ in posizione $4$ e il $4$ in posizione $6$. Qui il $4$ occupa subito la $4$ perché arriva prima del $3$. **Non compatibile.**

**(b) $12, 3, 14, 18, 4, 9, 21$**

| Chiave | $h'$ | Esito |
|---:|---:|---|
| $12$ | 3 | **3** ✅ |
| $3$ | 3 | occ. → **4** ✅ |
| $14$ | 5 | **5** ✅ |
| $18$ | 0 | **0** ✗ |

La tabella vuole il $9$ in posizione $0$ e il $18$ in posizione $1$. Qui il $18$ arriva prima del $9$ e ne prende il posto. **Non compatibile.**

**(c) $12, 14, 3, 9, 4, 18, 21$**

| Chiave | $h'$ | Sequenza di ispezione | Posizione |
|---:|---:|---|---:|
| $12$ | 3 | $3$ libera | **3** ✅ |
| $14$ | 5 | $5$ libera | **5** ✅ |
| $3$ | 3 | $3$ occ., $4$ libera | **4** ✅ |
| $9$ | 0 | $0$ libera | **0** ✅ |
| $4$ | 4 | $4,5$ occ., $6$ libera | **6** ✅ |
| $18$ | 0 | $0$ occ., $1$ libera | **1** ✅ |
| $21$ | 3 | $3,4,5,6$ occ., $7$ libera | **7** ✅ |

**Compatibile.** ✅

**(d) $9, 12, 14, 3, 4, 21, 18$**

| Chiave | $h'$ | Sequenza di ispezione | Posizione |
|---:|---:|---|---:|
| $9$ | 0 | $0$ libera | **0** ✅ |
| $12$ | 3 | $3$ libera | **3** ✅ |
| $14$ | 5 | $5$ libera | **5** ✅ |
| $3$ | 3 | $3$ occ., $4$ libera | **4** ✅ |
| $4$ | 4 | $4,5$ occ., $6$ libera | **6** ✅ |
| $21$ | 3 | $3,4,5,6$ occ., $7$ libera | **7** ✅ |
| $18$ | 0 | $0$ occ., $1$ libera | **1** ✅ |

**Compatibile.** ✅

**(e) $12, 9, 18, 3, 14, 21, 4$**

| Chiave | $h'$ | Sequenza di ispezione | Posizione |
|---:|---:|---|---:|
| $12$ | 3 | $3$ | **3** ✅ |
| $9$ | 0 | $0$ | **0** ✅ |
| $18$ | 0 | $0$ occ., $1$ | **1** ✅ |
| $3$ | 3 | $3$ occ., $4$ | **4** ✅ |
| $14$ | 5 | $5$ | **5** ✅ |
| $21$ | 3 | $3,4,5$ occ., $6$ | **6** ✗ |

La tabella vuole il $4$ in posizione $6$ e il $21$ in posizione $7$. Qui il $21$ arriva prima del $4$ e occupa la $6$. **Non compatibile.**

#### **3.4. Risposta**

$$
\boxed{\text{Le sequenze compatibili sono (c) e (d)}}
$$

> ⚠️ **Divergenza dagli appunti.** Il materiale della docente riporta una freccia accanto all'opzione **(a)**, che però la simulazione mostra incompatibile: nella (a) il $4$ precede il $3$ e ne occupa la cella. Le sequenze verificate come compatibili sono la (c) e la (d). Non essendo riportato il testo esatto del quesito (potrebbe chiedere quale sequenza **non** possa produrre la tabella, oppure indicare la sequenza di riferimento), si lascia la verifica completa di tutte e cinque le opzioni, che è comunque il lavoro richiesto in sede d'esame.

#### **3.5. Il criterio generale**

Perché una sequenza sia compatibile devono valere due condizioni:

1. ogni chiave che **non** collide deve trovarsi nella sua posizione naturale $h'(x)$;
2. fra chiavi che collidono, **l'ordine di arrivo determina l'ordine delle celle**: la prima ad arrivare occupa la posizione più vicina a $h'$, la seconda quella successiva, e così via.

Nel nostro caso i due gruppi in collisione sono $\{9, 18\}$ su $0$ e $\{12, 3, 21\}$ su $3$, più il $4$ che collide indirettamente. Le condizioni si traducono in tre vincoli d'ordine:

$$
9 \text{ prima di } 18, \qquad 12 \text{ prima di } 3, \qquad 3 \text{ prima di } 4 \text{ prima di } 21 .
$$

Le sequenze (c) e (d) li rispettano tutti; le altre ne violano almeno uno.

---

### **4. Esercizio 4 — Metodo esperto: $T(n)=4T(n/2)+\dfrac{n}{\log n}$**

$$
a = 4, \qquad b = 2, \qquad f(n) = \frac{n}{\log n}, \qquad n^{\log_2 4} = n^{2}.
$$

Confronto: $\dfrac{n}{\log n}$ è **minore** di $n$, che a sua volta è molto minore di $n^{2}$. Il caso 2 è escluso: $f(n)$ non ha l'ordine di $n^{2}$.

**Caso 1** — verifica che $f$ sia polinomialmente inferiore alla spartiacque:

$$
\frac{n}{\log n} = O\!\left(n^{2-\varepsilon}\right) \quad \text{ad esempio con } \varepsilon = 0.5, \text{ cioè } n^{1.5}.
$$

La verifica è immediata: $\dfrac{n/\log n}{n^{1.5}} = \dfrac{1}{n^{0.5}\log n} \to 0$, quindi $f(n)$ è addirittura $o(n^{1.5})$.

$$
\boxed{T(n) = \Theta\!\left(n^{\log_2 4}\right) = \Theta(n^{2})}
$$

> 💡 Il fattore $1/\log n$ non crea difficoltà **qui** perché la distanza dalla spartiacque è ampia: fra $n/\log n$ e $n^{2}$ c'è un margine polinomiale abbondante. Diventa invece fatale quando $f(n)=n/\log n$ e la spartiacque è $n$, come nell'[esercitazione del 15 dicembre](Soluzione_Esercitazione_15_12_2025.md): lì il rapporto è solo logaritmico e il teorema non si applica.

---

### **5. Esercizio 5 — Metodo esperto: $T(n)=8T(n/3)+2n^{2}\log n$**

$$
a = 8, \qquad b = 3, \qquad f(n) = 2n^{2}\log n, \qquad n^{\log_3 8} \approx n^{1.893}.
$$

Poiché $3^{1} = 3 < 8 < 9 = 3^{2}$, si ha $1 < \log_3 8 < 2$: la spartiacque sta **fra** $n$ e $n^{2}$.

**Confronto.** La funzione $2n^{2}\log n$ è superiore a $n^{2}$, quindi a maggior ragione supera $n^{1.893}$; il margine è polinomiale:

$$
2n^{2}\log n = \Omega\!\left(n^{\log_3 8 + \varepsilon}\right) \quad \text{con } \varepsilon = 0.1 \;\;(\text{cioè } n^{1.99}).
$$

**Caso 3.** Verifica della condizione di regolarità:

$$
8 \cdot 2\left(\frac{n}{3}\right)^{2}\log\frac{n}{3} \;\le\; c \cdot 2n^{2}\log n .
$$

Sviluppando il membro sinistro:

$$
8 \cdot 2 \cdot \frac{n^{2}}{9} \cdot \log\frac{n}{3} = \frac{8}{9}\cdot 2n^{2}\log\frac{n}{3} .
$$

Semplificando il fattore comune $2n^{2}$:

$$
\frac{8}{9}\log\frac{n}{3} \;\le\; c\log n .
$$

Poiché $\log\frac{n}{3} = \log n - \log 3 < \log n$, la disuguaglianza vale con

$$
c = \frac{8}{9} < 1 .
$$

$$
\boxed{T(n) = \Theta\!\left(n^{2}\log n\right)}
$$

> 📌 Il fattore $2$ davanti a $n^{2}\log n$ si semplifica in entrambi i membri e non compare nel risultato, coerentemente con la notazione $\Theta$ che ignora le costanti moltiplicative. Vale la pena portarlo nei calcoli solo per verificare che si semplifichi davvero.

---

### **6. Sintesi**

| # | Oggetto | Tecnica | Risultato |
|---|---|---|---|
| 1 | flusso massimo su rete a 8 vertici | verifica + teorema max-flow min-cut | $|f_{\max}| = 21$, taglio $(\{s,a,B,C,d,E\},\{f,t\})$ |
| 2 | posizione di min/mediano/max | proprietà dell'heap | minimo in radice, massimo in una foglia, mediano ovunque |
| 3 | ricostruzione dell'input di una hash | simulazione inversa | $n=9$; sequenze **(c)** e **(d)** |
| 4 | $T(n)=4T(n/2)+n/\log n$ | metodo esperto, caso 1 | $\Theta(n^{2})$ |
| 5 | $T(n)=8T(n/3)+2n^{2}\log n$ | metodo esperto, caso 3, $c=8/9$ | $\Theta(n^{2}\log n)$ |

> ✅ Gli esercizi 2 e 3 appartengono a una tipologia frequente agli scritti: **partire dal risultato e risalire all'input**. Il metodo è sempre lo stesso — ricavare i vincoli che l'output impone (qui: il valore di $n$, poi l'ordine relativo delle chiavi in collisione) e verificare le opzioni una per una, senza scorciatoie.
