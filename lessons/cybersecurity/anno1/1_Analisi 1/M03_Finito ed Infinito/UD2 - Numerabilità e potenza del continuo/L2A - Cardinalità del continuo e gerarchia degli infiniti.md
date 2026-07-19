# **M3 UD2 Lezione 2A - Cardinalità del continuo e gerarchia degli infiniti**

### **1. Intervallo aperto e retta reale**

La funzione:

$$
T:(0,1)\to\mathbb R,
\qquad
T(x)=\tan\left(\pi x-\frac{\pi}{2}\right),
$$

è biiettiva. È strettamente crescente e, quando $x$ tende agli estremi $0$ e $1$, i suoi valori percorrono rispettivamente $-\infty$ e $+\infty$. L'inversa è:

$$
T^{-1}(y)=\frac12+\frac{1}{\pi}\arctan y.
$$

Pertanto:

$$
|(0,1)|=|\mathbb R|=\mathfrak c.
$$

<!-- TODO FIGURA:
Disegnare il grafico di T(x)=tan(pi x-pi/2) sul dominio (0,1), con asintoti verticali in x=0 e x=1 e immagine estesa su tutta la retta reale. Affiancare due rette orientate, (0,1) e R, con alcune coppie x <-> T(x). Obiettivo didattico: mostrare una biiezione esplicita tra un intervallo limitato e l'intera retta, non soltanto lo slogan che aggiungere punti non cambia l'infinito.
-->

### **2. Intervalli aperti, chiusi e semiaperti**

L'inclusione:

$$
(0,1)\hookrightarrow[0,1]
$$

è iniettiva. È iniettiva anche:

$$
j:[0,1]\to(0,1),
\qquad
j(x)=\frac{x+1}{3},
$$

perché $j([0,1])=[1/3,2/3]\subset(0,1)$. Cantor-Bernstein fornisce:

$$
|[0,1]|=|(0,1)|=\mathfrak c.
$$

Lo stesso confronto vale per $[0,1)$ e $(0,1]$. Non basta dire che sono stati aggiunti o rimossi uno o due punti: le due iniezioni costituiscono la giustificazione.

### **3. Tutti gli intervalli reali**

Se $a<b$, la trasformazione affine:

$$
L(x)=a+(b-a)x
$$

è una biiezione tra $(0,1)$ e $(a,b)$, e analogamente tra gli intervalli con gli estremi inclusi in modo corrispondente.

Per una semiretta aperta, la funzione:

$$
x\mapsto\ln(x-a)
$$

è una biiezione $(a,+\infty)\to\mathbb R$. Le semirette chiuse hanno la stessa cardinalità per Cantor-Bernstein. Anche $(-\infty,b)$ e $(-\infty,b]$ sono equipotenti a $\mathbb R$ mediante riflessione.

Quindi ogni intervallo reale non degenere, limitato o illimitato, aperto, chiuso o semiaperto, ha cardinalità:

$$
\mathfrak c.
$$

I casi esclusi sono essenziali:

$$
|\varnothing|=0,
\qquad
|[a,a]|=1.
$$

### **4. Cardinalità di $\mathbb R^n$**

Per ogni intero finito $n\geq1$:

$$
|\mathbb R^n|=\mathfrak c.
$$

**Dimostrazione.** Sappiamo che $\mathbb R\sim\{0,1\}^{\mathbb N}$. Una $n$-upla di successioni binarie equivale a una funzione:

$$
u:\mathbb N\times I_n\to\{0,1\}.
$$

Poiché $\mathbb N\times I_n\sim\mathbb N$, riordinare le coordinate produce una singola successione binaria. Dunque:

$$
\left|\left(\{0,1\}^{\mathbb N}\right)^n\right|
=
|\{0,1\}^{\mathbb N}|.
$$

Trasportando le biiezioni si ottiene $|\mathbb R^n|=|\mathbb R|$. Questa prova non intercala sviluppi decimali e quindi non soffre di rappresentazioni doppie. $\square$

Per $n=2$, l'operazione è intuitivamente l'intercalamento delle coordinate binarie pari e dispari; la formulazione mediante funzioni evita ambiguità.

### **5. Insiemi di funzioni**

Per insiemi $A$ e $B$, $B^A$ indica l'insieme di tutte le funzioni:

$$
f:A\to B.
$$

La convenzione mette il dominio all'esponente. Per insiemi finiti:

$$
|B^A|=|B|^{|A|}.
$$

Per insiemi arbitrari resta valida la biiezione:

$$
\mathcal P(A)\sim\{0,1\}^A.
$$

In particolare:

$$
|\{0,1\}^{\mathbb N}|
=
2^{\aleph_0}
=
\mathfrak c.
$$

L'insieme di tutte le successioni reali ha ancora cardinalità $\mathfrak c$:

$$
|\mathbb R^{\mathbb N}|
=
\mathfrak c^{\aleph_0}
=
(2^{\aleph_0})^{\aleph_0}
=
2^{\aleph_0\cdot\aleph_0}
=
\mathfrak c.
$$

L'insieme di tutte le funzioni reali di variabile reale è invece molto più grande:

$$
|\mathbb R^{\mathbb R}|
=
\mathfrak c^{\mathfrak c}
=
2^{\mathfrak c}
>
\mathfrak c.
$$

L'uguaglianza centrale usa $\mathfrak c=2^{\aleph_0}$ e $\aleph_0\cdot\mathfrak c=\mathfrak c$; la disuguaglianza finale è il teorema di Cantor.

### **6. Aritmetica cardinale essenziale**

Le costruzioni già introdotte danno:

$$
\aleph_0+\aleph_0=\aleph_0,
$$

perché l'unione disgiunta di due copie di $\mathbb N$ si enumera alternando le copie, e:

$$
\aleph_0\cdot\aleph_0=\aleph_0,
$$

perché $\mathbb N^2\sim\mathbb N$. Inoltre:

$$
2^{\aleph_0}=\mathfrak c,
\qquad
\mathfrak c+\aleph_0=\mathfrak c,
\qquad
\mathfrak c\cdot\aleph_0=\mathfrak c.
$$

Queste uguaglianze non derivano dalle usuali cancellazioni algebriche: ciascuna rappresenta una biiezione o un'applicazione di Cantor-Bernstein.

### **7. Gerarchia degli infiniti**

Il teorema di Cantor si può iterare:

$$
|\mathbb N|
<
|\mathcal P(\mathbb N)|
<
|\mathcal P(\mathcal P(\mathbb N))|
<
\cdots.
$$

Quindi non esiste un'unica “dimensione dell'infinito” e non esiste una cardinalità massima: da ogni insieme $A$ si passa a uno strettamente più grande, $\mathcal P(A)$.

Indichiamo con:

$$
\aleph_0=|\mathbb N|
$$

la più piccola cardinalità infinita e con $\aleph_1$ la più piccola cardinalità strettamente maggiore di $\aleph_0$. Abbiamo invece definito:

$$
\mathfrak c=2^{\aleph_0}.
$$

Il teorema di Cantor garantisce soltanto:

$$
\aleph_0<\mathfrak c.
$$

<!-- TODO FIGURA:
Rappresentare una scala crescente con N al livello aleph_0, P(N) al livello 2^{aleph_0}=c, P(P(N)) al livello 2^c e ulteriori iterazioni. Inserire tra aleph_0 e c un punto interrogativo etichettato “esiste una cardinalità intermedia?” e separare visivamente il teorema di Cantor dalla risposta dell'ipotesi del continuo. Obiettivo didattico: mostrare la gerarchia senza identificare automaticamente c con aleph_1.
-->

### **8. Ipotesi del continuo**

L'**ipotesi del continuo** afferma che non esiste una cardinalità $\kappa$ tale che:

$$
\aleph_0<\kappa<2^{\aleph_0}.
$$

Equivalentemente:

$$
2^{\aleph_0}=\aleph_1.
$$

Non è un teorema di ZFC. Gödel mostrò che, se ZFC è coerente, anche ZFC con l'ipotesi del continuo è coerente; Cohen mostrò che, sotto la stessa ipotesi di coerenza, anche la sua negazione è coerente. Si dice che l'ipotesi del continuo è **indipendente da ZFC**.

> 📌 Scrivere $\mathfrak c=\aleph_1$ senza dichiarare l'ipotesi del continuo è un errore. Il risultato sempre valido in ZFC è $\mathfrak c=2^{\aleph_0}>\aleph_0$.

Forcing e costruzione dei modelli appartengono alla logica matematica avanzata e non sono sviluppati qui.

### **9. Cantor non è Russell**

Nel teorema di Cantor l'insieme:

$$
D=\{a\in A:a\notin f(a)\}
$$

è definito **all'interno di un insieme fissato $A$**, quindi $D\subseteq A$ è un insieme legittimo.

Il paradosso di Russell nasce invece dalla pretesa illimitata di formare “l'insieme di tutti gli insiemi che non appartengono a se stessi”. La distinzione mostra perché la teoria ingenua degli insiemi richiede assiomi che limitino la comprensione. Non serve il paradosso di Russell per dimostrare il teorema di Cantor.

### **10. Collegamenti con informatica e Analisi**

I programmi finiti su un alfabeto finito sono al più numerabili. Le funzioni:

$$
\mathbb N\to\{0,1\}
$$

sono invece non numerabili. Poiché ogni programma determina al più una funzione, le funzioni calcolabili sono al più numerabili; esistono quindi funzioni che nessun programma può calcolare. È una conseguenza di cardinalità, non ancora una dimostrazione del problema dell'arresto.

Nei moduli successivi:

- una [successione](../../M05_Successioni/UD1%20-%20Definizioni%20ed%20esempi/L1%20-%20Intro%20alle%20successioni.md) sarà una funzione $\mathbb N\to\mathbb R$, e il suo insieme di valori sarà al più numerabile;
- le serie saranno indicizzate dai naturali;
- intervalli e densità saranno usati in limiti, continuità e integrazione;
- i numeri complessi di M04 non modificano il confronto cardinale centrale: $\mathbb C\sim\mathbb R^2\sim\mathbb R$.

Questi sono collegamenti strutturali, non anticipazioni della teoria analitica.

### **11. Errori frequenti**

- Dire che un intervallo chiuso ha più punti di uno aperto perché contiene gli estremi.
- Usare l'intercalamento decimale per $\mathbb R^2$ senza gestire gli sviluppi non unici.
- Confondere $\mathbb R^n$ con $\mathbb R^{\mathbb N}$ o con $\mathbb R^{\mathbb R}$.
- Trattare le operazioni cardinali come normali operazioni reali e cancellare termini senza una biiezione.
- Scrivere $\mathfrak c=\aleph_1$ come fatto dimostrato.
- Confondere il teorema diagonale di Cantor con il paradosso di Russell.

### **12. Esercizi**

1. Costruire biiezioni tra $(a,b)$ e $(0,1)$ e tra $(a,+\infty)$ e $\mathbb R$.
2. Usare Cantor-Bernstein per confrontare $[0,1)$ e $[0,1]$.
3. Descrivere esplicitamente come due successioni binarie vengono intercalate in una sola e scrivere l'operazione inversa.
4. Dimostrare che $|\mathbb C|=\mathfrak c$ usando $\mathbb C\sim\mathbb R^2$.
5. Confrontare le cardinalità di $\mathcal P(\mathbb N)$, $\mathbb R$, $\mathbb R^3$ e $\mathcal P(\mathbb R)$.
6. Problema complesso: sia $X$ l'insieme delle funzioni $f:\mathbb N\to\mathbb Q$ e sia $Y=\mathcal P(\mathbb N)$. Costruire confronti cardinali nei due versi, giustificare ogni codifica, applicare Cantor-Bernstein per classificare $X$, confrontare poi $X$ con $\mathcal P(X)$ mediante Cantor e spiegare perché una semplice enumerazione diagonale delle coppie non potrebbe enumerare $X$.

### **13. Riepilogo**

> ✅ Ogni intervallo reale non degenere e ogni spazio $\mathbb R^n$ di dimensione finita hanno cardinalità $\mathfrak c$, ma l'insieme di tutte le funzioni reali è strettamente più grande.

- $(0,1)\sim[0,1]\sim\mathbb R$;
- ogni intervallo non degenere ha cardinalità $\mathfrak c$;
- $|\mathbb R^n|=\mathfrak c$ per $n$ finito e positivo;
- $\mathcal P(A)\sim\{0,1\}^A$ chiarisce la potenza cardinale;
- Cantor genera una gerarchia senza cardinalità massima;
- $\mathfrak c=2^{\aleph_0}$, mentre $\mathfrak c=\aleph_1$ è l'ipotesi del continuo;
- i programmi sono numerabili, ma non tutte le funzioni binarie sono calcolabili.
