# Soluzione dell'esercitazione — 19 dicembre 2025

Ultima esercitazione del corso: **ricorrenze con radici**, analisi di algoritmi ricorsivi nel caso peggiore, **Bellman-Ford** con pesi negativi e **Floyd-Warshall** con rilevamento di un ciclo negativo.

> **Riferimenti di teoria:** [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md), [M06/UD1 — Bellman-Ford-Moore e Pape-D'Esopo](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L3_Algo_BellmanFordMoore_pila_PapeDesopo.md), [M06/UD1 — Problema dei cammini minimi](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L1_2_Problema_dei_cammini_minimi.md), [M06/UD1 — Confronto delle complessità](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L4_Confronto_tra_complessita.md).

---

### **1. Esercizio 1 — Metodo esperto: $T(n)=3T(n/2)+4n^{2}\sqrt{n}$**

#### **1.1. Riscrittura di $f(n)$ in forma di potenza**

Prima di qualunque confronto conviene esprimere $f(n)$ come potenza unica di $n$:

$$
f(n) = 4\,n^{2}\sqrt{n} = 4\,n^{2}\cdot n^{1/2} = 4\,n^{5/2} = 4\,n^{2.5}.
$$

#### **1.2. Parametri e spartiacque**

$$
a = 3, \qquad b = 2, \qquad n^{\log_2 3} \approx n^{1.585}.
$$

#### **1.3. Confronto e caso 3**

$$
4n^{2.5} = \Omega\!\left(n^{\log_2 3 + \varepsilon}\right) \quad \text{con } \varepsilon \approx 0.9 ,
$$

perché $2.5$ supera abbondantemente $1.585$. **Caso 3.**

**Condizione di regolarità:**

$$
3 \cdot 4\left(\frac{n}{2}\right)^{2}\sqrt{\frac{n}{2}} \;\le\; c \cdot 4n^{2}\sqrt{n} .
$$

Sviluppando il membro sinistro:

$$
3 \cdot 4 \cdot \frac{n^{2}}{4}\cdot\frac{\sqrt{n}}{\sqrt{2}} = \frac{3}{4\sqrt{2}}\cdot 4n^{2}\sqrt{n} .
$$

Semplificando il fattore comune $4n^{2}\sqrt n$:

$$
\frac{3}{4\sqrt{2}} \le c
\qquad\Longleftrightarrow\qquad
\frac{3}{2^{2.5}} \le c .
$$

Poiché $2^{2.5} = 4\sqrt2 \approx 5.657$, si ottiene

$$
c = \frac{3}{2^{2.5}} \approx 0.53 < 1 .
$$

$$
\boxed{T(n) = \Theta\!\left(n^{2}\sqrt{n}\right) = \Theta\!\left(n^{2.5}\right)}
$$

> 💡 La costante $\dfrac{a}{b^{k}} = \dfrac{3}{2^{2.5}}$ è il candidato automatico ogni volta che $f(n) = n^{k}$: la condizione di regolarità del caso 3 si riduce sempre a $\dfrac{a}{b^{k}} < 1$, cioè a $\log_b a < k$ — che è esattamente il confronto già fatto per stabilire di essere nel caso 3. **Verificata la prima condizione, la seconda è automatica per le $f$ polinomiali.**

---

### **2. Esercizio 2 — Metodo esperto: $T(n)=10T(n/3)+3n^{2}+n$**

#### **2.1. Semplificazione di $f(n)$**

$$
f(n) = 3n^{2} + n = \Theta(n^{2}),
$$

perché il termine lineare è dominato dal quadratico.

#### **2.2. Parametri e spartiacque**

$$
a = 10, \qquad b = 3, \qquad n^{\log_3 10} \approx n^{2.096}.
$$

Il valore dell'esponente si colloca fra $2$ e $3$, perché $3^{2} = 9 < 10 < 27 = 3^{3}$:

$$
2 < \log_3 10 < 3 .
$$

#### **2.3. Confronto e caso 1**

Qui la spartiacque **supera** $f(n)$: $n^{2.096}$ cresce più di $n^{2}$, e la differenza è polinomiale:

$$
3n^{2}+n = O\!\left(n^{\log_3 10 - \varepsilon}\right) \quad \text{con } \varepsilon = 0.05 \;\;(\text{cioè } n^{2.046}).
$$

**Caso 1**, dominano le foglie:

$$
\boxed{T(n) = \Theta\!\left(n^{\log_3 10}\right) \approx \Theta\!\left(n^{2.096}\right)}
$$

> ⚠️ La differenza fra $n^{2}$ e $n^{2.096}$ sembra minima ma è **polinomiale**, quindi sufficiente per il caso 1. Il margine $\varepsilon$ non deve essere grande: basta che esista. Su $n = 10^{6}$ il rapporto $n^{0.096}$ vale circa $3.7$: un fattore quasi $4$, tutt'altro che trascurabile.

---

### **3. Esercizio 3 — Algoritmo ricorsivo con chiamata a procedura esterna**

#### **3.1. Traccia**

```
Algo(A, n)
{
    if n < 1 then return 1;

    t = Algo(A, n/2);

    if t > n^2 then t = t - (1/2) * Algo(A, n/2);

    for j = 1 to n
        t = t + A[j] + Algo1(n);

    return t;
}
```

sapendo che `Algo1(n)` ha complessità $\Theta(\sqrt{n})$. Si analizzi il **caso peggiore**.

#### **3.2. Individuazione del caso peggiore**

L'algoritmo contiene una chiamata ricorsiva **condizionata**: la seconda `Algo(A, n/2)` viene eseguita solo se `t > n^2`. Il caso peggiore è quello in cui la condizione è **sempre vera**, e si pagano quindi **due** chiamate ricorsive per livello.

> 📌 Quando una chiamata è dentro un `if`, il caso migliore e quello peggiore possono avere complessità di ordine diverso. Qui: caso migliore $T(n) = T(n/2) + n\sqrt n$, caso peggiore $T(n) = 2T(n/2) + n\sqrt n$. Curiosamente, come si vedrà, il risultato asintotico coincide — ma va dimostrato, non dato per scontato.

#### **3.3. Costo del ciclo**

Il `for` esegue $n$ iterazioni; ciascuna somma due termini a costo costante (`t` e `A[j]`) più una chiamata `Algo1(n)` di costo $\Theta(\sqrt n)$:

$$
n \cdot \Theta(\sqrt{n}) = \Theta\!\left(n^{1.5}\right) = \Theta\!\left(n\sqrt n\right).
$$

> ⚠️ L'argomento di `Algo1` è $n$, **non** $j$: il costo $\Theta(\sqrt n)$ non varia lungo il ciclo e si può moltiplicare direttamente. Se fosse stato `Algo1(j)` sarebbe servita la sommatoria $\sum_{j=1}^n \sqrt j = \Theta(n^{1.5})$ — che qui darebbe lo stesso ordine, ma per una ragione diversa.

#### **3.4. La ricorrenza e la sua risoluzione**

$$
T(n) = 2\,T\!\left(\frac{n}{2}\right) + n\sqrt{n} .
$$

$$
a = 2, \qquad b = 2, \qquad n^{\log_2 2} = n, \qquad f(n) = n^{1.5}.
$$

**Confronto:** $n^{1.5} = \Omega(n^{1+\varepsilon})$ con $\varepsilon = 0.5$. **Caso 3.**

**Condizione di regolarità:**

$$
2\left(\frac{n}{2}\right)^{1.5} = 2 \cdot \frac{n^{1.5}}{2^{1.5}} = \frac{n^{1.5}}{\sqrt{2}} \;\le\; c\,n^{1.5}
\quad\Longleftrightarrow\quad
\frac{1}{\sqrt 2} \le c ,
$$

soddisfatta con $c = \dfrac{1}{\sqrt2} \approx 0.707 < 1$ ✅

$$
\boxed{T(n) = \Theta\!\left(n\sqrt{n}\right) = \Theta\!\left(n^{1.5}\right)}
$$

> 💡 Poiché siamo nel caso 3, il costo è dominato dal **lavoro alla radice** e il numero di chiamate ricorsive influisce solo sulla costante. Nel caso migliore ($a=1$) si avrebbe $n^{\log_2 1}=1$ e ancora caso 3, quindi ancora $\Theta(n^{1.5})$: qui i due casi coincidono asintoticamente, ma la verifica andava fatta.

---

### **4. Esercizio 4 — Somma ricorsiva con partizione in tre parti**

#### **4.1. Traccia**

```
Algo(A, i, j)                    /* chiamata iniziale: Algo(A, 1, n) */
{
    if (j < i) then return 0;
    else if (i == j) then return A[i];
    else
    {
        d = (j - i + 1) / 3;
        return Algo(A, i,      i+d-1)
             + Algo(A, i+d,    i+2d-1)
             + Algo(A, i+2d,   j);
    }
}
```

#### **4.2. Che cosa calcola**

L'algoritmo **somma tutti gli elementi** di $A[i \dots j]$, dividendo l'intervallo in tre parti di circa $d = n/3$ elementi ciascuna e sommando ricorsivamente i risultati parziali. I due casi base coprono l'intervallo vuoto (somma $0$, elemento neutro) e quello di un solo elemento (la somma è l'elemento stesso).

#### **4.3. La ricorrenza**

Tre chiamate ricorsive su $n/3$ elementi ciascuna, più lavoro costante (una divisione e due addizioni):

$$
T(n) = 3\,T\!\left(\frac{n}{3}\right) + c .
$$

$$
a = 3, \qquad b = 3, \qquad n^{\log_3 3} = n^{1} = n, \qquad f(n) = c = \Theta(1).
$$

**Confronto:** $c = O(n^{1-\varepsilon})$ per $\varepsilon = 1$ (basta $c = O(n^{0})$). **Caso 1.**

$$
\boxed{T(n) = \Theta(n)}
$$

#### **4.4. Verifica di buon senso**

Il risultato è quello atteso: per sommare $n$ numeri bisogna leggerli tutti, quindi $\Omega(n)$ è un limite inferiore per qualunque algoritmo. La divisione in tre parti non fa risparmiare nulla — e non potrebbe, dato che nessun elemento può essere ignorato.

> 💡 Questa ricorrenza è il caso limite in cui $a = b$: i sottoproblemi sono tanti quanti le parti in cui si divide, quindi **le foglie sono esattamente $n$** e ciascuna costa $\Theta(1)$. Il divide-et-impera qui non è un'ottimizzazione ma solo una riformulazione ricorsiva della somma lineare; ha però il pregio di parallelizzarsi bene, cosa che il ciclo sequenziale non fa.

---

### **5. Esercizio 5 — Algoritmo di Bellman-Ford**

#### **5.1. Traccia**

Determinare i cammini minimi dal vertice $1$ che usano **al più 4 archi**, nel grafo orientato con vertici $\{1,\dots,6\}$ e archi

| Arco | Peso | | Arco | Peso |
|---|---:|---|---|---:|
| $1 \to 2$ | 7 | | $3 \to 2$ | 2 |
| $1 \to 3$ | 8 | | $3 \to 6$ | 2 |
| $2 \to 4$ | 4 | | $5 \to 3$ | **−2** |
| $2 \to 5$ | 1 | | $5 \to 4$ | 2 |
| $2 \to 6$ | 2 | | $6 \to 5$ | 3 |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Perché non si può usare Dijkstra**

Il grafo contiene l'arco $5 \to 3$ di peso $-2$. Dijkstra assume che i pesi siano non negativi: la sua correttezza si fonda sull'idea che, una volta estratto il vertice con stima minima, nessun cammino alternativo possa migliorarla. Con un peso negativo un cammino più lungo può risultare più leggero, e l'estrazione non è più definitiva.

**Bellman-Ford** rinuncia alla coda di priorità e rilassa **tutti** gli archi ripetutamente. Dopo $k$ passate, $d[v]$ contiene il peso del cammino minimo da $1$ a $v$ che usa **al più $k$ archi**. Dopo $|V|-1$ passate si hanno tutti i cammini minimi, perché in assenza di cicli negativi nessun cammino minimo può usare più di $|V|-1$ archi.

#### **5.3. Tabella di esecuzione**

Ogni cella riporta "distanza, predecessore"; $i$ è il numero di passate, cioè il numero massimo di archi consentito.

| $i$ | $1$ | $2$ | $3$ | $4$ | $5$ | $6$ |
|---:|---|---|---|---|---|---|
| $0$ | 0, N | $\infty$, N | $\infty$, N | $\infty$, N | $\infty$, N | $\infty$, N |
| $1$ | 0, N | **7, 1** | **8, 1** | $\infty$, N | $\infty$, N | $\infty$, N |
| $2$ | 0, N | 7, 1 | 8, 1 | **11, 2** | **8, 2** | **9, 2** |
| $3$ | 0, N | 7, 1 | **6, 5** | **10, 5** | 8, 2 | 9, 2 |
| $4$ | 0, N | 7, 1 | 6, 5 | 10, 5 | 8, 2 | **8, 3** |

#### **5.4. Commento alle passate**

**Passata 1.** Solo gli archi uscenti da $1$ producono effetto: $d[2]=7$, $d[3]=8$.

**Passata 2.** Dai vertici $2$ e $3$: $d[4] = 7+4 = 11$, $d[5] = 7+1 = 8$, $d[6] = 7+2 = 9$. L'arco $3 \to 2$ darebbe $8+2 = 10 > 7$: nessun effetto.

**Passata 3 — l'arco negativo entra in gioco.** Ora $d[5] = 8$ è finito, quindi l'arco $5 \to 3$ di peso $-2$ è utilizzabile:

$$
d[3] = \min(8,\; 8 + (-2)) = \mathbf{6},
$$

e il predecessore di $3$ passa da $1$ a $5$. Il cammino $1 \to 2 \to 5 \to 3$ costa $7+1-2 = 6$, meno dell'arco diretto $1\to3$ da $8$.

Contemporaneamente l'arco $5 \to 4$ dà $d[4] = 8+2 = 10 < 11$.

**Passata 4.** Il miglioramento di $d[3]$ si propaga: l'arco $3 \to 6$ dà

$$
d[6] = \min(9,\; 6+2) = \mathbf{8},
$$

con predecessore $3$.

> 📌 Si osservi il **ritardo di propagazione**: il vantaggio dell'arco negativo, disponibile dalla passata 3, raggiunge il vertice $6$ solo alla passata 4. È esattamente il motivo per cui servono $|V|-1$ passate: nel caso peggiore un miglioramento deve attraversare tutto il grafo, un arco per passata.

#### **5.5. Risultato**

| Vertice | $1$ | $2$ | $3$ | $4$ | $5$ | $6$ |
|---|---:|---:|---:|---:|---:|---:|
| distanza | 0 | 7 | 6 | 10 | 8 | 8 |
| predecessore | — | 1 | 5 | 5 | 2 | 3 |

**Albero dei cammini minimi:**

- **1**
  - **2** (7)
    - **5** (8)
      - **3** (6)
        - **6** (8)
      - **4** (10)

I cammini sono $1\to2$ ($7$), $1\to2\to5$ ($8$), $1\to2\to5\to3$ ($6$), $1\to2\to5\to4$ ($10$), $1\to2\to5\to3\to6$ ($8$).

> 💡 Il vertice $3$ ha distanza $6$, **minore** di quella del suo predecessore nell'albero DFS naturale: è la firma dei pesi negativi. Si noti anche che $d[3]=6 < d[2]=7$ pur essendo $3$ più lontano in numero di archi — con pesi negativi la profondità nell'albero non è monotona rispetto alla distanza.

$$
\text{Complessità: } \Theta(|V|\cdot|E|).
$$

---

### **6. Esercizio 6 — Floyd-Warshall e rilevamento di un ciclo negativo**

#### **6.1. Traccia**

Applicare Floyd-Warshall al grafo orientato con vertici $\{1,2,3,4\}$ e archi

$$
1\to2\;(3),\quad 1\to3\;(2),\quad 2\to3\;(6),\quad 2\to4\;(-10),\quad 3\to2\;(8),\quad 4\to2\;(1),\quad 4\to3\;(9).
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. Richiamo dell'algoritmo**

Floyd-Warshall calcola i cammini minimi fra **tutte le coppie** con tre cicli annidati. La quantità $d_{ij}^{(k)}$ è il peso del cammino minimo da $i$ a $j$ che usa come vertici **intermedi** solo quelli in $\{1,\dots,k\}$, e vale la ricorrenza

$$
d_{ij}^{(k)} = \min\left\{\, d_{ij}^{(k-1)},\; d_{ik}^{(k-1)} + d_{kj}^{(k-1)} \,\right\}.
$$

L'interpretazione: il cammino minimo da $i$ a $j$ che può usare i primi $k$ intermedi o **non passa** per $k$ (primo termine) o **ci passa esattamente una volta** (secondo termine), spezzandosi in due sottocammini che usano solo i primi $k-1$.

#### **6.3. Matrici iniziali**

$$
D^{(0)} = \begin{pmatrix}
0 & 3 & 2 & \infty\\
\infty & 0 & 6 & -10\\
\infty & 8 & 0 & \infty\\
\infty & 1 & 9 & 0
\end{pmatrix},
\qquad
\Pi^{(0)} = \begin{pmatrix}
\text{N} & 1 & 1 & \text{N}\\
\text{N} & \text{N} & 2 & 2\\
\text{N} & 3 & \text{N} & \text{N}\\
\text{N} & 4 & 4 & \text{N}
\end{pmatrix}
$$

Nella diagonale si pone $0$ (cammino vuoto), negli elementi corrispondenti ad archi il peso, altrove $\infty$.

#### **6.4. Iterazione con $k=1$**

Il vertice $1$ non ha archi **entranti**: la prima colonna di $D^{(0)}$ è tutta $\infty$ tranne $d_{11}$. Nessun cammino può quindi passare per $1$ come intermedio, e la matrice resta invariata.

#### **6.5. Iterazione con $k=2$**

Ora si considerano i cammini che passano per il vertice $2$. Poiché $d_{24} = -10$ è fortemente negativo, gli aggiornamenti sono numerosi:

| Elemento | Vecchio | Candidato $d_{i2}+d_{2j}$ | Nuovo |
|---|---:|---|---:|
| $d_{14}$ | $\infty$ | $d_{12}+d_{24} = 3 + (-10) = -7$ | **$-7$** |
| $d_{34}$ | $\infty$ | $d_{32}+d_{24} = 8 + (-10) = -2$ | **$-2$** |
| $d_{43}$ | $9$ | $d_{42}+d_{23} = 1 + 6 = 7$ | **$7$** |
| $d_{13}$ | $2$ | $d_{12}+d_{23} = 3+6 = 9$ | $2$ (invariato) |
| $d_{44}$ | $0$ | $d_{42}+d_{24} = 1 + (-10) = \mathbf{-9}$ | vedi §6.6 |

$$
D^{(2)} = \begin{pmatrix}
0 & 3 & 2 & \mathbf{-7}\\
\infty & 0 & 6 & -10\\
\infty & 8 & 0 & \mathbf{-2}\\
\infty & 1 & \mathbf{7} & 0
\end{pmatrix},
\qquad
\Pi^{(2)} = \begin{pmatrix}
\text{N} & 1 & 1 & \mathbf{2}\\
\text{N} & \text{N} & 2 & 2\\
\text{N} & 3 & \text{N} & \mathbf{2}\\
\text{N} & 4 & \mathbf{2} & \text{N}
\end{pmatrix}
$$

#### **6.6. Il ciclo negativo**

L'elemento diagonale $d_{44}$ merita attenzione particolare. Il candidato calcolato è

$$
d_{42} + d_{24} = 1 + (-10) = -9 < 0 .
$$

Un valore **negativo sulla diagonale** significa che esiste un cammino da $4$ a sé stesso di peso negativo, cioè un **ciclo negativo**:

$$
4 \xrightarrow{\;1\;} 2 \xrightarrow{\;-10\;} 4 \qquad \text{di peso } 1 - 10 = -9 .
$$

$$
\boxed{\text{Il grafo contiene un ciclo negativo: i cammini minimi non sono definiti}}
$$

> ⚠️ In presenza di un ciclo negativo raggiungibile, il concetto stesso di cammino minimo **perde senso**: percorrendo il ciclo un numero arbitrario di volte il peso scende indefinitamente, e l'estremo inferiore è $-\infty$. Nessun algoritmo può restituire un valore finito, perché non esiste.
>
> Il criterio di rilevamento è quello appena visto: **se al termine dell'algoritmo un elemento diagonale $d_{ii}$ è negativo, esiste un ciclo negativo passante per $i$**. Bellman-Ford usa un criterio equivalente: se una $|V|$-esima passata migliora ancora qualche stima, c'è un ciclo negativo.

> 💡 Nella prassi implementativa la diagonale viene lasciata a $0$ e si esegue un controllo finale esplicito; per questo la matrice $D^{(2)}$ riportata sopra ha $d_{44}=0$. Il valore $-9$ è la **spia diagnostica**, non un dato da propagare: propagarlo renderebbe negative anche altre celle in modo incontrollato.

#### **6.7. Complessità**

$$
\Theta(|V|^{3}) \text{ tempo}, \qquad \Theta(|V|^{2}) \text{ spazio}.
$$

I tre cicli annidati sono su $k$, $i$, $j$, ciascuno di $|V|$ iterazioni. L'ordine dei cicli è **vincolante**: $k$ deve stare all'esterno, altrimenti la ricorrenza userebbe valori non ancora consolidati.

#### **6.8. Confronto fra gli algoritmi di cammino minimo**

| Algoritmo | Sorgenti | Pesi negativi | Cicli negativi | Complessità |
|---|---|---|---|---|
| **Dijkstra** | singola | ✗ | non rilevati | $O((|V|+|E|)\log|V|)$ |
| **Bellman-Ford** | singola | ✅ | **rilevati** | $\Theta(|V|\,|E|)$ |
| **Floyd-Warshall** | tutte le coppie | ✅ | **rilevati** | $\Theta(|V|^{3})$ |
| **Johnson** | tutte le coppie | ✅ | rilevati | $O(|V|^{2}\log|V| + |V||E|)$ |

> ✅ La regola di scelta: **Dijkstra** se i pesi sono non negativi e serve una sola sorgente; **Bellman-Ford** se ci sono pesi negativi o si vuole rilevare cicli negativi; **Floyd-Warshall** su grafi densi quando servono tutte le coppie; **Johnson** su grafi sparsi per tutte le coppie (esegue Bellman-Ford una volta per ripesare gli archi, poi Dijkstra da ogni vertice).

---

### **7. Sintesi**

| # | Oggetto | Tecnica | Risultato |
|---|---|---|---|
| 1 | $T(n)=3T(n/2)+4n^{2}\sqrt n$ | metodo esperto, caso 3, $c=3/2^{2.5}$ | $\Theta(n^{2.5})$ |
| 2 | $T(n)=10T(n/3)+3n^{2}+n$ | metodo esperto, caso 1 | $\Theta(n^{\log_3 10}) \approx \Theta(n^{2.096})$ |
| 3 | `Algo` con chiamata condizionata | caso peggiore + caso 3 | $\Theta(n\sqrt n)$ |
| 4 | somma ricorsiva in tre parti | metodo esperto, caso 1, $a=b=3$ | $\Theta(n)$ |
| 5 | Bellman-Ford con arco $-2$ | 4 passate di rilassamento | $d[3]=6$ via $1\to2\to5\to3$ |
| 6 | Floyd-Warshall | matrici $D$ e $\Pi$ | **ciclo negativo** $4\to2\to4$ di peso $-9$ |

> ✅ Il messaggio conclusivo del corso sui cammini minimi: **i pesi negativi non sono un dettaglio tecnico ma cambiano il problema**. Rendono inapplicabile Dijkstra, richiedono più passate a Bellman-Ford, e — se formano un ciclo — fanno svanire l'esistenza stessa della soluzione. Riconoscere quale algoritmo si applica a quale grafo è tanto importante quanto saperlo eseguire.
