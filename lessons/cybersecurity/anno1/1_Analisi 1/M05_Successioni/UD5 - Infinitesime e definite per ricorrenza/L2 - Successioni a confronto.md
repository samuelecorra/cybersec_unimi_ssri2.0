# **M5 UD5 Lezione 2 - Successioni a confronto**

### **1. Confronto mediante rapporto**

Per successioni definitivamente positive $(a_n)$ e $(b_n)$, il limite di $a_n/b_n$ distingue tre casi fondamentali:

$$
\frac{a_n}{b_n}\to0
\quad\Longrightarrow\quad
a_n\text{ è trascurabile rispetto a }b_n;
$$

$$
\frac{a_n}{b_n}\to L\in(0,+\infty)
\quad\Longrightarrow\quad
a_n\text{ e }b_n\text{ hanno lo stesso ordine};
$$

$$
\frac{a_n}{b_n}\to+\infty
\quad\Longrightarrow\quad
a_n\text{ domina }b_n.
$$

«Stesso ordine» non significa «equivalenti»: per esempio $2n$ e $n$ hanno rapporto limite $2$, non $1$.

### **2. Notazione $O$ grande**

Si scrive

$$
a_n=O(b_n)
$$

se $b_n\neq0$ definitivamente ed esistono $C>0$ e $N\in\mathbb{N}$ tali che

$$
|a_n|\leq C|b_n|\qquad\text{per ogni }n\geq N.
$$

In particolare, $a_n=O(1)$ significa che $(a_n)$ è limitata. La notazione non è un'uguaglianza ordinaria: $O(b_n)$ rappresenta una classe di successioni.

Se $a_n=o(b_n)$, allora $a_n=O(b_n)$. Il viceversa è falso: $n=O(n)$, ma $n\neq o(n)$.

### **3. Calcolo con $O$, $o$ e $\sim$**

Valgono, quando tutte le espressioni sono definite definitivamente,

$$
O(a_n)+O(a_n)=O(a_n),
$$

$$
O(a_n)O(b_n)=O(a_nb_n),
$$

$$
o(a_n)O(b_n)=o(a_nb_n).
$$

La transitività assume forme utili:

$$
a_n=o(b_n),\quad b_n=O(c_n)
\Longrightarrow
a_n=o(c_n),
$$

$$
a_n=O(b_n),\quad b_n=O(c_n)
\Longrightarrow
a_n=O(c_n).
$$

Per esempio,

$$
n^3+5n=n^3\left(1+\frac{5}{n^2}\right)\sim n^3.
$$

### **4. Gerarchia delle crescite**

Per ogni $\alpha,\beta>0$ e $a>1$,

$$
(\ln n)^\beta=o(n^\alpha),
$$

$$
n^\alpha=o(a^n),
$$

$$
a^n=o(n!),
$$

$$
n!=o(n^n).
$$

In forma mnemonica,

$$
(\ln n)^\beta\ll n^\alpha\ll a^n\ll n!\ll n^n.
$$

Il simbolo $\ll$ qui è soltanto una notazione informale per $o$.

#### **4.1 Giustificazioni**

Il confronto tra potenze ed esponenziali è stato ottenuto tramite rapporti successivi o Stolz-Cesàro. Per $a^n/n!$, scegliendo un intero $m>a$, per $n$ grande si ha

$$
\frac{a^{n+1}/(n+1)!}{a^n/n!}=\frac{a}{n+1}\leq\frac1m<1,
$$

quindi la coda è dominata da una geometrica infinitesima. Infine,

$$
0<\frac{n!}{n^n}=\prod_{k=1}^{n}\frac{k}{n},
$$

e una quota crescente dei fattori è uniformemente minore di $1$, da cui il limite nullo. Il confronto logaritmo-potenza sarà provato in forma organica dopo la costruzione analitica di $\ln$; in M05 è registrato come risultato fondamentale coordinato con i moduli successivi.

### **5. Applicazioni**

Dal momento che $n^3+5n\sim n^3$ e $n^3=o(2^n)$,

$$
\frac{n^3+5n}{2^n}\to0.
$$

Analogamente,

$$
\frac{3^n+n^{10}}{3^n}
=1+\frac{n^{10}}{3^n}\to1,
$$

quindi $3^n+n^{10}\sim3^n$.

In analisi degli algoritmi, $T(n)=O(n\log n)$ fornisce un limite superiore asintotico, non afferma che $T(n)/(n\log n)\to1$. Questa distinzione evita di attribuire alla notazione $O$ più informazione di quella che contiene.

### **6. Confronti non risolti dal solo ordine**

Se $a_n=O(b_n)$ e $b_n=O(a_n)$, le successioni hanno ordini comparabili, ma il rapporto può non convergere. Per esempio

$$
a_n=2+(-1)^n,
\qquad
b_n=1
$$

soddisfano entrambe le stime $O$ reciproche, mentre $a_n/b_n$ oscilla tra $1$ e $3$.

Inoltre, $a_n=o(b_n)$ non determina il limite di $a_n$ se $b_n$ diverge: $n=o(n^2)$, ma $n\to+\infty$.

### **7. Errori frequenti**

- Confondere $O$ con $o$ o con $\sim$.
- Scrivere $a_n=O(b_n)$ senza valore assoluto quando i segni variano.
- Dedurre l'esistenza del limite del rapporto dalla sola doppia stima $O$.
- Usare la gerarchia senza controllare $a>1$ e gli esponenti positivi.
- Trattare $O(b_n)$ come un numero da cancellare algebricamente.

### **8. Esercizi**

1. Classificare le relazioni tra $n$, $2n+1$, $n^2$ usando $O$, $o$ e $\sim$.
2. Dimostrare che $n^4+3n^2-1\sim n^4$.
3. Calcolare $\lim (n^5+1)/3^n$ usando la gerarchia.
4. Dimostrare che $2^n=o(n!)$ con una maggiorazione geometrica della coda.
5. Mostrare che $n!=o(n^n)$ separando il prodotto nei fattori con $k\leq n/2$ e $k>n/2$.
6. Fornire un esempio di successioni $a_n=O(b_n)$ e $b_n=O(a_n)$ il cui rapporto non abbia limite.
7. Stabilire se $(-1)^n=O(1)$, $o(1)$ oppure $\sim1$.
8. Tradurre rigorosamente l'affermazione algoritmica «il costo è al più quadratico» e distinguerla da $T(n)\sim n^2$.
9. Ordinare per crescita $n^{20}$, $1{,}01^n$, $(\ln n)^{100}$, $n!$ e $n^n$.

### **9. Riepilogo**

> ✅ Il rapporto offre il confronto più preciso; $O$ esprime un controllo uniforme definitivo, $o$ una trascurabilità e $\sim$ l'identità del termine principale. La gerarchia delle crescite deve essere applicata con le relative ipotesi.
