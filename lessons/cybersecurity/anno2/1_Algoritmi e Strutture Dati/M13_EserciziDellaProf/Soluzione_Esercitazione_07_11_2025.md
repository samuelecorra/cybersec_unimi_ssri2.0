# Soluzione dell'esercitazione — 7 novembre 2025

Esercitazione di **Algoritmi e Strutture Dati** interamente dedicata a **esercizi tratti da appelli d'esame passati** (17/06/2022, 05/07/2021, 29/07/2022, 25/02/2022): ricorrenze risolte con il metodo esperto, con l'albero di ricorsione e con il metodo di sostituzione, più l'analisi di codice iterativo e ricorsivo.

> **Riferimenti di teoria:** [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md), [M01 — Efficienza e complessità](../M01_Elementi_Fondamentali_DS_e_Algo/UD1/L2_1_Efficienza_e_complessita.md), [M01 — Complessità per induzione](../M01_Elementi_Fondamentali_DS_e_Algo/UD1/L3_Complessita_per_induzione.md), [M07/UD2 — MergeSort](../M07_Divide_et_Impera/UD2/L1_Merge_sort.md).

---

### **1. Appello 17/06/2022, domanda 9 — $T(n)=8T(n/3)+2^{n}$**

#### **1.1. Traccia**

Risolvere la ricorrenza

$$
T(n) = 8\,T\!\left(\frac{n}{3}\right) + 2^{n}.
$$

#### **1.2. Identificazione dei parametri e della spartiacque**

$$
a = 8, \qquad b = 3, \qquad f(n) = 2^{n}.
$$

$$
n^{\log_b a} = n^{\log_3 8} \approx n^{1.893}.
$$

Il calcolo dell'esponente: $\log_3 8 = \dfrac{\ln 8}{\ln 3} = \dfrac{2.0794}{1.0986} \approx 1.893$, cioè poco meno di $n^{2}$.

#### **1.3. Confronto e applicazione del caso 3**

La funzione $f(n) = 2^{n}$ è **esponenziale**, quindi cresce più rapidamente di qualsiasi potenza di $n$:

$$
2^{n} = \Omega\!\left(n^{\log_3 8 + \varepsilon}\right) \quad \text{per ogni } \varepsilon > 0 .
$$

Siamo nel caso 3, ma occorre verificare la **condizione di regolarità** $a\,f(n/b) \le c\,f(n)$ con $c < 1$:

$$
8 \cdot 2^{n/3} \;\le\; c \cdot 2^{n}
\quad\Longleftrightarrow\quad
8 \cdot \frac{2^{n/3}}{2^{n}} \le c
\quad\Longleftrightarrow\quad
8 \cdot 2^{n/3 - n} \le c .
$$

Poiché $\dfrac{n}{3} - n = -\dfrac{2n}{3}$:

$$
8 \cdot 2^{-2n/3} = 8 \cdot \left(\frac{1}{2^{2/3}}\right)^{n} \le c .
$$

Il termine di sinistra **tende a zero** al crescere di $n$ (base minore di $1$ elevata a $n$), quindi è certamente minore di, ad esempio, $c = 1/2$ per $n$ sufficientemente grande. Negli appunti la maggiorazione viene scritta come $8 \cdot (1/4)^{n/3}$, che è la stessa quantità con la base riscritta: $2^{-2n/3} = (2^{-2})^{n/3} = (1/4)^{n/3}$.

$$
\boxed{T(n) = \Theta(2^{n})}
$$

> 💡 Quando $f(n)$ è **esponenziale** e la ricorsione riduce la dimensione per un fattore costante, il caso 3 si applica sempre: il costo della radice domina in modo schiacciante. Il tempo speso a calcolare $\log_3 8$ è, in questi casi, del tutto superfluo ai fini del risultato — ma serve a dimostrare formalmente che si è nel caso 3.

---

### **2. Analisi di un algoritmo iterativo con `while` annidati**

#### **2.1. Traccia**

```
Algoritmo(n)
{
    c = 0;  a = n;

    while (a > 1)
    {
        b = 1;
        while (b <= a*a)
        {
            c = c + 1;
            b = 2 * b;
        }
        a = a / 2;
    }
    return c;
}
```

#### **2.2. Ciclo esterno**

La variabile $a$ parte da $n$ e si **dimezza** a ogni iterazione:

$$
a = n,\; \frac{n}{2},\; \frac{n}{4},\; \dots,\; \frac{n}{2^{i}},\; \dots
$$

Il ciclo termina quando $a \le 1$, cioè

$$
\frac{n}{2^{i}} = 1 \iff \log n = \log 2^{i} \iff i = \log n .
$$

Il ciclo esterno esegue quindi $\log n$ iterazioni; alla $i$-esima il valore di $a$ è $n/2^{i}$.

#### **2.3. Ciclo interno**

La variabile $b$ parte da $1$ e raddoppia: $b = 1, 2, 4, 8, \dots, 2^{j}$. La condizione di permanenza è $b \le a^{2}$, cioè

$$
2^{j} \le a^{2} \iff \log\left(2^{j}\right) \le \log\left(a^{2}\right) \iff j \le 2\log a .
$$

Il ciclo interno esegue dunque $\Theta(\log a)$ iterazioni — il fattore $2$ dovuto al quadrato è una **costante moltiplicativa** e non cambia l'ordine di grandezza.

> ⚠️ È un errore frequente concludere che $b \le a^2$ costi $\Theta(\log^2 a)$ perché "c'è un quadrato". Il quadrato è **dentro il logaritmo**: $\log(a^2) = 2\log a$, non $(\log a)^2$. La differenza fra $\log(a^2)$ e $\log^2 a$ è la stessa che passa fra un fattore costante e un fattore logaritmico.

#### **2.4. Costo complessivo**

Sommando il costo del ciclo interno su tutte le iterazioni del ciclo esterno, con $a = n/2^{i}$:

$$
T(n) = \sum_{i=0}^{\log n - 1} 2\log\!\left(\frac{n}{2^{i}}\right)
= 2 \sum_{i=0}^{\log n - 1} \bigl(\log n - i\bigr).
$$

La sommatoria, letta al contrario, è la somma dei primi $\log n$ interi:

$$
\sum_{i=0}^{\log n - 1} (\log n - i) = \log n + (\log n - 1) + \dots + 2 + 1 = \frac{\log n\,(\log n + 1)}{2}.
$$

Sostituendo:

$$
T(n) = 2 \cdot \frac{\log n\,(\log n + 1)}{2} = \log^{2} n + \log n .
$$

$$
\boxed{T(n) = \Theta(\log^{2} n)}
$$

---

### **3. Appello 05/07/2021, domanda 7 — Confronto fra soluzione iterativa e ricorsiva**

#### **3.1. Traccia**

Un problema ammette una soluzione **iterativa** di costo $O(n^{2})$ e una soluzione **ricorsiva** descritta da

$$
T(n) =
\begin{cases}
n & \text{se } n \le 1,\\[4pt]
a\,T\!\left(\dfrac{n}{4}\right) + 1 & \text{se } n > 1 .
\end{cases}
$$

Per quali valori di $a$ la soluzione ricorsiva è preferibile?

#### **3.2. Risoluzione della ricorrenza in funzione di $a$**

$$
b = 4, \qquad f(n) = 1, \qquad n^{\log_b a} = n^{\log_4 a}.
$$

Per $a > 1$ si ha $\log_4 a > 0$, quindi $n^{\log_4 a}$ cresce polinomialmente mentre $f(n)=1$ è costante:

$$
f(n) = 1 = O\!\left(n^{\log_4 a - \varepsilon}\right)
$$

per un $\varepsilon$ opportuno. Siamo nel **caso 1** e il costo è dominato dalle foglie dell'albero di ricorsione:

$$
T(n) = \Theta\!\left(n^{\log_4 a}\right).
$$

#### **3.3. Confronto con $O(n^2)$**

Occorre confrontare $n^{\log_4 a}$ con $n^{2}$, cioè $\log_4 a$ con $2$:

$$
\log_4 a = 2 \iff a = 4^{2} = 16 .
$$

Il valore critico è quindi $a = 16$:

| Valore di $a$ | $n^{\log_4 a}$ | Conclusione |
|---|---|---|
| $a < 16$ | $o(n^{2})$ | conviene la soluzione **ricorsiva** |
| $a = 16$ | $\Theta(n^{2})$ | le due soluzioni sono **equivalenti** |
| $a > 16$ | $\omega(n^{2})$ | conviene la soluzione **iterativa** |

Verifica del caso limite:

$$
n^{\log_4 16} = n^{2} \qquad \text{perché } 4^{2}=16 .
$$

$$
\boxed{
\begin{aligned}
a &< 16 &&\Rightarrow\; \text{ricorsivo migliore}\\
a &= 16 &&\Rightarrow\; \text{equivalenti}\\
a &> 16 &&\Rightarrow\; \text{iterativo migliore}
\end{aligned}}
$$

> 💡 Il parametro $a$ conta il numero di **sottoproblemi**, non la loro dimensione. Con $b=4$ fisso, ogni raddoppio di $a$ aggiunge $\log_4 2 = 0.5$ all'esponente finale: un algoritmo che genera $16$ sottoproblemi da un quarto dei dati non guadagna nulla rispetto alla scansione quadratica diretta.

---

### **4. Appello 05/07/2021, domanda 8 — Cicli annidati con ordinamento**

#### **4.1. Traccia**

```
for (i = 0; i < n; i++)
{
    for (j = 0; j < n; j++)
        a[j] = randomValue(i);

    Sort(a);
}
```

Sapendo che `randomValue` costa $\Theta(1)$ e che `Sort` è un algoritmo di ordinamento efficiente, determinare la complessità.

#### **4.2. Svolgimento**

Il corpo del ciclo esterno esegue due operazioni **in sequenza**, quindi i loro costi si **sommano**:

- il ciclo `for` interno riempie $n$ celle con chiamate a costo costante: $\Theta(n)$;
- la chiamata `Sort(a)` ordina un vettore di $n$ elementi: $O(n\log n)$ per un algoritmo ottimo basato sui confronti (MergeSort, HeapSort, QuickSort nel caso medio).

Costo di una iterazione:

$$
\Theta(n) + O(n\log n) = O(n\log n),
$$

perché $n = O(n\log n)$: nella somma **domina il termine maggiore**.

Il ciclo esterno ripete tutto $n$ volte, quindi i costi si **moltiplicano**:

$$
T(n) = n \cdot \bigl(n + n\log n\bigr) = n^{2} + n^{2}\log n = O(n^{2}\log n).
$$

$$
\boxed{T(n) = \Theta(n^{2}\log n)}
$$

> ⚠️ Il punto da non dare per scontato è che il vettore viene **riempito da capo a ogni iterazione**: l'ordinamento non può essere ammortizzato o saltato, perché all'iterazione successiva `a` è di nuovo disordinato. Se invece `Sort` fosse chiamato una sola volta fuori dal ciclo, il costo scenderebbe a $\Theta(n^{2})$.

---

### **5. Appello 29/07/2022, domanda 9 — $T(n) = T(n/5) + T(3n/4) + \Theta(n)$**

#### **5.1. Traccia**

Risolvere

$$
T(n) = T\!\left(\frac{n}{5}\right) + T\!\left(\frac{3n}{4}\right) + \Theta(n).
$$

#### **5.2. Perché il metodo esperto non si applica**

Il Master Theorem richiede la forma $a\,T(n/b) + f(n)$, con **tutti i sottoproblemi della stessa dimensione**. Qui i due sottoproblemi hanno dimensioni diverse ($n/5$ e $3n/4$): occorre l'**albero di ricorsione**.

#### **5.3. Costruzione dell'albero di ricorsione**

| Livello | Nodi | Costo totale del livello |
|---:|---|---|
| $0$ | $n$ | $n$ |
| $1$ | $\dfrac{n}{5}$ e $\dfrac{3n}{4}$ | $\dfrac{n}{5} + \dfrac{3n}{4} = \dfrac{4n + 15n}{20} = \dfrac{19}{20}n$ |
| $2$ | $\dfrac{n}{25},\; \dfrac{3n}{20},\; \dfrac{3n}{20},\; \dfrac{9n}{16}$ | $\left(\dfrac{19}{20}\right)^{2} n$ |
| $\vdots$ | $\vdots$ | $\vdots$ |
| $k$ | $2^{k}$ nodi | $\left(\dfrac{19}{20}\right)^{k} n$ |

Il passaggio chiave è il calcolo del **fattore di riduzione per livello**:

$$
\frac{1}{5} + \frac{3}{4} = \frac{4}{20} + \frac{15}{20} = \frac{19}{20} < 1 .
$$

Ogni livello costa una frazione fissa $19/20$ del precedente, perché il costo lineare $\Theta(n)$ si ripartisce proporzionalmente alle dimensioni dei sottoproblemi.

#### **5.4. Somma dei livelli**

Il ramo più profondo è quello che si riduce più lentamente, cioè quello con fattore $3/4$: si esaurisce quando

$$
\left(\frac{3}{4}\right)^{j} n = 1 \iff n = \left(\frac{4}{3}\right)^{j} \iff j = \log_{4/3} n = \Theta(\log n).
$$

Maggiorando (l'albero non è completo, alcuni rami muoiono prima, quindi la somma su tutti i livelli è una sovrastima):

$$
T(n) \;\le\; n \sum_{k=0}^{\log n} \left(\frac{19}{20}\right)^{k}
\;<\; n \sum_{k=0}^{\infty} \left(\frac{19}{20}\right)^{k}
= n \cdot \frac{1}{1 - \frac{19}{20}} = 20\,n .
$$

Quindi $T(n) = O(n)$. D'altra parte il solo costo della radice è $\Theta(n)$, per cui $T(n) = \Omega(n)$. Le due limitazioni coincidono:

$$
\boxed{T(n) = \Theta(n)}
$$

> 📌 La regola generale: se in $T(n) = \sum_i T(\alpha_i n) + \Theta(n)$ si ha $\sum_i \alpha_i < 1$, il costo è $\Theta(n)$ e domina la radice; se $\sum_i \alpha_i = 1$ il costo è $\Theta(n\log n)$ e tutti i livelli pesano ugualmente (è il caso di MergeSort, con $1/2 + 1/2 = 1$); se $\sum_i \alpha_i > 1$ dominano le foglie e il costo è superlineare.
>
> Questa ricorrenza compare nell'analisi dell'algoritmo di **selezione lineare** (mediana delle mediane): è proprio la disuguaglianza $\frac{1}{5} + \frac{3}{4} < 1$ a garantire il tempo lineare nel caso peggiore.

---

### **6. Analisi di due procedure ricorsive: $F(n)$ e $P(n)$**

#### **6.1. Traccia**

```
Procedura F(n)
{
    if n <= 2 then return n
    else
    {
        i = arrotonda_per_difetto(n/3);
        j = arrotonda_per_eccesso(n/3);
        x = F(i);
        y = F(j);
        p = P(n);
        return p*x + y;
    }
}

Procedura P(n)
{
    if n == 0 then return 1
    else
    {
        k = arrotonda_per_difetto(n/2);
        x = P(k);
        if n è pari then return x*x
        else return 2*x*x;
    }
}
```

#### **6.2. Analisi di $P(n)$**

$P$ effettua **una sola** chiamata ricorsiva su $n/2$; il resto del corpo (una divisione, un test di parità, una o due moltiplicazioni) costa $\Theta(1)$:

$$
T_P(n) = 1 \cdot T_P\!\left(\frac{n}{2}\right) + c .
$$

Applicando il metodo esperto: $a=1$, $b=2$, $n^{\log_2 1} = n^{0} = 1$ e $f(n) = c = \Theta(1) = \Theta\!\left(n^{0}\right)$. Siamo nel **caso 2**:

$$
\boxed{T_P(n) = \Theta\!\left(n^{0}\log n\right) = \Theta(\log n)}
$$

> 💡 $P(n)$ calcola $2^{n}$ (o una potenza analoga) per **elevamenti al quadrato successivi**: è l'algoritmo di esponenziazione veloce. Il numero di moltiplicazioni scende da $n$ a $\log n$ perché ogni passo dimezza l'esponente anziché decrementarlo.

#### **6.3. Analisi di $F(n)$**

$F$ effettua **due** chiamate ricorsive, entrambe su circa $n/3$ (una arrotondata per difetto, l'altra per eccesso — gli arrotondamenti non alterano il comportamento asintotico). In aggiunta chiama $P(n)$, il cui costo si è appena determinato:

$$
T_F(n) = 2\,T_F\!\left(\frac{n}{3}\right) + \Theta(\log n) + c .
$$

Parametri: $a=2$, $b=3$, $f(n) = \log n$.

$$
n^{\log_b a} = n^{\log_3 2} \approx n^{0.63}.
$$

Il confronto: $\log n$ cresce più lentamente di **qualsiasi** potenza positiva di $n$, quindi

$$
\log n = O\!\left(n^{\log_3 2 - \varepsilon}\right) \quad \text{ad esempio con } \varepsilon = 0.1 .
$$

Siamo nel **caso 1**, il costo è dominato dalle foglie:

$$
\boxed{T_F(n) = \Theta\!\left(n^{\log_3 2}\right) \approx \Theta\!\left(n^{0.63}\right)}
$$

> ⚠️ Un errore che si vede spesso: sommare i due costi scrivendo $T_F(n) = \Theta(n^{0.63}) + \Theta(\log n)$ e tenerli entrambi. Il termine $\log n$ è già **incluso** nella ricorrenza come $f(n)$, e il caso 1 stabilisce che è asintoticamente trascurabile rispetto al lavoro complessivo delle foglie. Il metodo esperto restituisce già il costo totale.

---

### **7. Appello 25/02/2022, domanda 7 — Metodo di sostituzione su $T(n)=T(n-1)+\log n$**

#### **7.1. Traccia**

Dimostrare, con il metodo di sostituzione, che

$$
T(n) = T(n-1) + \log n \;\Longrightarrow\; T(n) = O(n\log n).
$$

#### **7.2. Il metodo di sostituzione**

Il metodo consiste in tre passi: **congetturare** la forma della soluzione, **assumerla vera** per valori minori di $n$ (ipotesi induttiva), **dimostrarla** per $n$ determinando le costanti.

Qui la ricorrenza è di tipo **sottrattivo** ($n-1$, non $n/b$), quindi il metodo esperto non è applicabile e la sostituzione è la via naturale.

**Congettura:**

$$
T(n) \le c\,n\log n \qquad \text{per un'opportuna costante } c > 0 .
$$

**Ipotesi induttiva** (assunta vera per $n-1$):

$$
T(n-1) \le c\,(n-1)\log(n-1).
$$

#### **7.3. Passo induttivo**

Si sostituisce l'ipotesi nella ricorrenza:

$$
T(n) = T(n-1) + \log n \;\le\; c\,(n-1)\log(n-1) + \log n .
$$

Si maggiora $\log(n-1) \le \log n$ (il logaritmo è crescente), passaggio lecito perché stiamo cercando un limite **superiore**:

$$
T(n) \;\le\; c\,(n-1)\log n + \log n .
$$

Si sviluppa il prodotto:

$$
T(n) \;\le\; c\,n\log n - c\log n + \log n .
$$

Si vuole che il membro di destra non superi $c\,n\log n$, cioè:

$$
c\,n\log n - c\log n + \log n \;\le\; c\,n\log n
\quad\Longleftrightarrow\quad
-c\log n + \log n \le 0 .
$$

Raccogliendo $\log n$:

$$
(1 - c)\log n \le 0 .
$$

Per $n \ge 2$ si ha $\log n \ge 1 > 0$, quindi la disuguaglianza è soddisfatta se e solo se

$$
1 - c \le 0 \iff \boxed{c \ge 1}.
$$

#### **7.4. Conclusione**

Scegliendo $c = 1$ (o qualunque $c \ge 1$ che soddisfi anche il caso base) l'induzione si chiude e si conclude

$$
T(n) = O(n\log n).
$$

> 📌 Il limite è **stretto**: sviluppando la ricorrenza si ottiene $T(n) = \sum_{k=1}^{n}\log k = \log(n!)$, e per l'approssimazione di Stirling $\log(n!) = \Theta(n\log n)$. Dunque $T(n) = \Theta(n\log n)$: la maggiorazione $\log(n-1)\le\log n$, per quanto grossolana, non ha fatto perdere precisione.

> ⚠️ Nel metodo di sostituzione bisogna dimostrare **esattamente** la forma congetturata, senza costanti additive residue. Concludere con "$\le c\,n\log n + \log n$, che è comunque $O(n\log n)$" è un errore logico: l'induzione richiederebbe poi $T(n)\le c\,n\log n + k\log n$ e la costante additiva si accumulerebbe a ogni passo.

---

### **8. Appello 25/02/2022, domanda 8 — Costruzione di un albero da un array ordinato**

#### **8.1. Traccia**

`A` è un array di numeri interi **ordinati e distinti**.

```
Algoritmo(A, i, j)
{
    if (i <= j) then
    {
        m = floor((i + j) / 2);
        T.left  = Algoritmo(A, i, m-1);
        T.right = Algoritmo(A, m+1, j);
        T.key   = A[m];
        return T;
    }
    else return nil;
}
```

Dire che cosa costruisce l'algoritmo e determinarne la complessità.

#### **8.2. Che cosa costruisce**

L'algoritmo costruisce un **albero binario di ricerca perfettamente bilanciato** a partire da un array ordinato. Il valore centrale diventa la radice; ricorsivamente, la metà sinistra dell'array genera il sottoalbero sinistro e la metà destra quello destro.

La proprietà di ricerca è garantita dall'ordinamento dell'array: tutti gli elementi in posizione $< m$ sono minori di $A[m]$ e finiscono a sinistra, tutti quelli in posizione $> m$ sono maggiori e finiscono a destra.

#### **8.3. Esempio con $A = [1,2,3,4,5]$, $i=1$, $j=5$**

| Chiamata | $m$ | chiave | sottoalbero sinistro | sottoalbero destro |
|---|---:|---:|---|---|
| $(1,5)$ | 3 | $3$ | $(1,2)$ | $(4,5)$ |
| $(1,2)$ | 1 | $1$ | $(1,0)$ → `nil` | $(2,2)$ |
| $(2,2)$ | 2 | $2$ | `nil` | `nil` |
| $(4,5)$ | 4 | $4$ | $(4,3)$ → `nil` | $(5,5)$ |
| $(5,5)$ | 5 | $5$ | `nil` | `nil` |

Albero risultante:

- **3** — radice
  - sinistro: **1** → destro: **2**
  - destro: **4** → destro: **5**

L'altezza è $2$, contro l'altezza $4$ che si otterrebbe inserendo gli elementi nell'ordine $1,2,3,4,5$ in un BST ordinario (albero completamente degenere, una lista).

#### **8.4. Complessità**

Ogni chiamata su un intervallo di $n$ elementi genera due chiamate su circa $n/2$ elementi ciascuna e svolge lavoro costante (una divisione, un accesso all'array, la creazione di un nodo):

$$
T(n) = 2\,T\!\left(\frac{n}{2}\right) + c .
$$

Metodo esperto: $a=2$, $b=2$, $n^{\log_2 2} = n^{1} = n$, $f(n) = c = O\!\left(n^{1-\varepsilon}\right)$ per $\varepsilon = 1$. Siamo nel **caso 1**:

$$
\boxed{T(n) = \Theta(n)}
$$

> ⚠️ **Correzione rispetto agli appunti.** Negli appunti dell'esercitazione la ricorrenza $T(n)=2T(n/2)+c$ è seguita dalla conclusione $\Theta(\log n)$: si tratta di un refuso. La risposta corretta è $\Theta(n)$, e lo si vede anche senza teoremi: l'algoritmo **crea un nodo per ogni elemento** dell'array, quindi non può costare meno di $n$ operazioni. Un costo $\Theta(\log n)$ è quello della ricorrenza $T(n)=1\cdot T(n/2)+c$, cioè di una ricorsione che scarta metà dei dati anziché visitarli entrambi — è il caso della ricerca binaria, non quello della costruzione dell'albero.

> 💡 Confronto utile: costruire il BST bilanciato da un array **già ordinato** costa $\Theta(n)$; se l'array non fosse ordinato bisognerebbe prima ordinarlo, con un costo complessivo di $\Theta(n\log n)$ dominato dall'ordinamento.

---

### **9. Domanda 2 — Albero binario di ricerca**

Negli appunti compare, isolato e senza il testo della domanda, il disegno di un albero binario di ricerca:

- **10** — radice
  - sinistro: **6**
    - sinistro: **1** → destro: **4**
    - destro: **9**
  - destro: **18**

> ⚠️ Il testo della domanda non è riportato nel materiale: non è possibile ricostruire per congettura che cosa venisse chiesto (inserimento, cancellazione, visita, verifica della proprietà di ricerca). Si riporta pertanto il solo albero, verificandone la struttura.

**Verifica della proprietà di ricerca.** Ogni nodo ha chiavi minori nel sottoalbero sinistro e maggiori nel destro: $1 < 4 < 6 < 9 < 10 < 18$. La **visita simmetrica** (in-order) produce infatti la sequenza ordinata

$$
1,\; 4,\; 6,\; 9,\; 10,\; 18 .
$$

L'albero ha $6$ nodi e altezza $3$; non è bilanciato (il sottoalbero destro della radice contiene un solo nodo, quello sinistro ne contiene quattro).

---

### **10. Sintesi dei risultati**

| # | Appello | Oggetto | Tecnica | Risultato |
|---|---|---|---|---|
| 1 | 17/06/2022 D9 | $T(n)=8T(n/3)+2^{n}$ | metodo esperto, caso 3 | $\Theta(2^{n})$ |
| 2 | — | `while` annidati con $b \le a^{2}$ | somma dei costi per livello | $\Theta(\log^{2} n)$ |
| 3 | 05/07/2021 D7 | $T(n)=aT(n/4)+1$ vs $O(n^{2})$ | metodo esperto, caso 1 | soglia $a = 16$ |
| 4 | 05/07/2021 D8 | doppio ciclo con `Sort` | somma e prodotto dei costi | $\Theta(n^{2}\log n)$ |
| 5 | 29/07/2022 D9 | $T(n)=T(n/5)+T(3n/4)+\Theta(n)$ | albero di ricorsione | $\Theta(n)$ |
| 6 | — | procedure $F(n)$ e $P(n)$ | metodo esperto, casi 2 e 1 | $\Theta(\log n)$, $\Theta(n^{\log_3 2})$ |
| 7 | 25/02/2022 D7 | $T(n)=T(n-1)+\log n$ | sostituzione, $c \ge 1$ | $O(n\log n)$ |
| 8 | 25/02/2022 D8 | BST bilanciato da array ordinato | metodo esperto, caso 1 | $\Theta(n)$ |

> ✅ **Come scegliere il metodo.** Metodo esperto se la ricorrenza ha la forma $a\,T(n/b)+f(n)$ con sottoproblemi della stessa dimensione; albero di ricorsione se le dimensioni differiscono o se serve capire dove si concentra il lavoro; sostituzione per le ricorrenze sottrattive ($n-1$, $n-2$) e ogni volta che si dispone già di una congettura da confermare.
