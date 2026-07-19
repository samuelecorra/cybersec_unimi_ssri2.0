# **M5 UD4 Lezione 1 - Il numero e**

### **1. Perché compare una nuova costante**

Se un capitale unitario cresce del $100\%$ in un anno e gli interessi vengono accreditati $n$ volte, a ogni intervallo il fattore di crescita è $1+1/n$. Il capitale finale è quindi

$$
x_n=\left(1+\frac{1}{n}\right)^n,\qquad n\geq 1.
$$

I primi valori, $2$, $2{,}25$, $2{,}370\ldots$, suggeriscono una crescita verso un numero vicino a $2{,}718$. L'osservazione numerica non basta: occorre provare che $(x_n)$ converge.

> 📌 In questa lezione $e$ non viene presupposto: è definito come limite di una successione della quale si dimostrano monotonia e limitatezza.

### **2. Sviluppo binomiale di $x_n$**

Dal binomio di Newton,

$$
x_n=\sum_{k=0}^{n}\binom{n}{k}\frac{1}{n^k}
=\sum_{k=0}^{n}\frac{1}{k!}\prod_{j=0}^{k-1}\left(1-\frac{j}{n}\right).
$$

Per $k=0$ il prodotto vuoto vale $1$. Per ogni $k\leq n$, il termine corrispondente è non negativo.

#### **2.1 Monotonia**

Confrontiamo $x_n$ e $x_{n+1}$. Per ogni $k\leq n$ e ogni $j\in\{0,\ldots,k-1\}$,

$$
1-\frac{j}{n+1}\geq 1-\frac{j}{n}.
$$

I fattori sono non negativi, quindi il termine di indice $k$ nello sviluppo di $x_{n+1}$ è almeno quello di indice $k$ nello sviluppo di $x_n$. Inoltre $x_{n+1}$ contiene il nuovo termine positivo di indice $n+1$. Ne segue

$$
x_{n+1}>x_n.
$$

Dunque $(x_n)$ è strettamente crescente.

#### **2.2 Limitatezza superiore**

Poiché ogni prodotto nello sviluppo è al più $1$,

$$
x_n\leq \sum_{k=0}^{n}\frac{1}{k!}.
$$

Per $k\geq 2$ vale $k!\geq 2^{k-1}$: infatti $2\cdot 3\cdots k\geq 2^{k-1}$. Pertanto

$$
x_n\leq 2+\sum_{k=2}^{n}\frac{1}{2^{k-1}}
=3-\frac{1}{2^{n-1}}<3.
$$

La successione è crescente e limitata superiormente. Per il teorema di convergenza monotona ammette limite finito.

### **3. Definizione del numero $e$**

Si definisce

$$
e:=\lim_{n\to+\infty}\left(1+\frac{1}{n}\right)^n.
$$

Dalle stime precedenti,

$$
2\leq e\leq 3,
$$

e calcoli più accurati forniscono

$$
e=2{,}718281828\ldots
$$

Consideriamo anche

$$
y_n=\left(1+\frac{1}{n}\right)^{n+1}=x_n\left(1+\frac{1}{n}\right).
$$

Poiché $x_n\to e$ e $1+1/n\to 1$, l'aritmetica dei limiti dà $y_n\to e$. Le due successioni forniscono quindi approssimazioni dello stesso numero.

<!-- TODO FIGURA: rappresentare sullo stesso piano discreto x_n=(1+1/n)^n e y_n=(1+1/n)^(n+1), con la retta orizzontale y=e; evidenziare che entrambe convergono allo stesso valore e che x_n resta sotto e. Obiettivo didattico: visualizzare la definizione sequenziale di e e distinguere convergenza da raggiungimento del limite. -->

### **4. Capitalizzazione composta e continua**

Con capitale iniziale $C_0$, tasso annuo $r$ e $n$ capitalizzazioni nell'anno,

$$
C_n=C_0\left(1+\frac{r}{n}\right)^n.
$$

Il passaggio ideale a capitalizzazioni sempre più frequenti conduce a $C_0e^r$. L'interpretazione finanziaria motiva la costante, ma la definizione matematica di $e$ resta quella del paragrafo precedente.

### **5. Generalizzazioni e loro statuto logico**

Per ogni $a\in\mathbb{R}$, quando la base è positiva definitivamente,

$$
\left(1+\frac{a}{n}\right)^n\longrightarrow e^a.
$$

Più in generale, per $a,b\in\mathbb{R}$,

$$
\left(1+\frac{a}{n}\right)^{bn}\longrightarrow e^{ab},
$$

se l'espressione è reale e ben definita per gli indici considerati. Queste formule non sono usate per definire simultaneamente tutte le potenze reali di $e$: la loro dimostrazione completa richiede le proprietà della funzione esponenziale e del logaritmo, che saranno fondate nei moduli sui limiti di funzione e sulla continuità. Qui il caso $a=1$ è stato dimostrato senza presupporre tali risultati.

Una variante utile è

$$
\left(1+\frac{1}{m_n}\right)^{m_n}\to e
$$

quando $(m_n)$ è una successione di interi positivi con $m_n\to+\infty$: si tratta infatti di una sottosuccessione di $(x_n)$.

> ⚠️ Se $t_n\to+\infty$ assume valori reali non interi, la formula $(1+1/t_n)^{t_n}\to e$ richiede la potenza reale, non segue dal solo teorema sulle sottosuccessioni.

### **6. Errori frequenti**

- Concludere che $x_n\to1$ perché la base tende a $1$: anche l'esponente varia.
- Scrivere $1^{+\infty}=e$: $1^{\infty}$ è una forma indeterminata, non un valore.
- Invocare $e$ per provare l'esistenza del limite che definisce $e$: sarebbe un ragionamento circolare.
- Applicare la formula generalizzata senza verificare che la base sia positiva e la potenza reale definita.

### **7. Esercizi**

1. Calcolare $x_1,x_2,x_3,x_4$ e verificare direttamente la crescita nei primi quattro indici.
2. Dimostrare per induzione che $k!\geq 2^{k-1}$ per ogni $k\geq1$.
3. Ricostruire tutti i passaggi della prova $x_n<3$, indicando dove si usa la positività dei fattori.
4. Dimostrare che $\left(1+\frac{1}{2n}\right)^{2n}\to e$ mediante il teorema sulle sottosuccessioni.
5. Calcolare $\lim_{n\to\infty}\left(1+\frac{3}{n}\right)^{2n}$ usando la generalizzazione dichiarata.
6. Spiegare perché $\left(1-\frac{n+1}{n}\right)^n$ non è un'applicazione lecita della forma esponenziale standard.
7. Confrontare il capitale prodotto da una, due, quattro e dodici capitalizzazioni per $C_0=1000$ e $r=0{,}05$.
8. Stabilire quali parti della costruzione di $e$ dipendono dalla completezza di $\mathbb{R}$.

### **8. Riepilogo**

> ✅ La successione $\left(1+1/n\right)^n$ è crescente, è limitata superiormente da $3$ e quindi converge. Il suo limite definisce $e$; le generalizzazioni con parametro sono risultati successivi, non scorciatoie per la prova di esistenza.
