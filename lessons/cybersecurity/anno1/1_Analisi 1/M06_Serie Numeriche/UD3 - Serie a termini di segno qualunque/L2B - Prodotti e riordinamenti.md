# **M6 UD3 Lezione 2B - Prodotti e riordinamenti**

### **1. Prodotto di Cauchy**

Date due serie con indice iniziale $0$,

$$
\sum_{n=0}^{\infty}a_n,
\qquad
\sum_{n=0}^{\infty}b_n,
$$

si definiscono

$$
c_n=\sum_{k=0}^{n}a_kb_{n-k}.
$$

La serie $\sum c_n$ è il **prodotto di Cauchy**. Il coefficiente $c_n$ raccoglie tutte le coppie di indici con somma $n$, come nel prodotto formale di due polinomi.

Non va confuso con il prodotto termine a termine $\sum a_nb_n$.

<!-- TODO FIGURA: griglia degli indici (k,j) nel primo quadrante; evidenziare le diagonali k+j=n che formano c_n e il triangolo k+j<=N che forma la somma parziale del prodotto di Cauchy. Obiettivo didattico: distinguere convoluzione, prodotto termine a termine e prodotto delle somme. -->

### **2. Due serie assolutamente convergenti**

> **Teorema.** Se $\sum a_n$ e $\sum b_n$ convergono assolutamente, allora il prodotto di Cauchy converge assolutamente e

$$
\sum_{n=0}^{\infty}c_n
=
\left(\sum_{n=0}^{\infty}a_n\right)
\left(\sum_{n=0}^{\infty}b_n\right).
$$

**Dimostrazione.** Per ogni $N$,

$$
\sum_{n=0}^{N}|c_n|
\leq
\sum_{n=0}^{N}\sum_{k=0}^{n}|a_k|\,|b_{n-k}|
\leq
\left(\sum_{k=0}^{N}|a_k|\right)
\left(\sum_{j=0}^{N}|b_j|\right).
$$

Le somme parziali della serie dei moduli di $c_n$ sono crescenti e limitate, dunque convergono. L'assoluta convergenza permette inoltre di approssimare la doppia somma con un rettangolo o con un triangolo lasciando una coda assoluta arbitrariamente piccola. Il limite delle somme triangolari è quindi il prodotto $AB$. $\square$

### **3. Teorema di Mertens**

> **Teorema.** Se una delle due serie converge assolutamente e l'altra converge, il prodotto di Cauchy converge e ha somma $AB$.

**Idea dimostrativa.** Supponiamo $\sum|a_n|<\infty$ e poniamo $B_m=\sum_{j=0}^{m}b_j$. La somma parziale del prodotto è

$$
C_N=\sum_{k=0}^{N}a_k B_{N-k}.
$$

Poiché $(B_m)$ converge, è limitata. In $C_N-AB$ si separano un numero finito di indici $k$, per i quali $B_{N-k}\to B$, e una coda di $\sum|a_k|$, uniformemente piccola. Il termine mancante $B\sum_{k>N}a_k$ tende a zero. Ne segue $C_N\to AB$.

### **4. Perché servono ipotesi**

Consideriamo due copie della serie condizionatamente convergente

$$
\sum_{n=0}^{\infty}\frac{(-1)^n}{\sqrt{n+1}}.
$$

Il coefficiente del prodotto di Cauchy ha modulo

$$
|c_n|=
\sum_{k=0}^{n}\frac1{\sqrt{(k+1)(n-k+1)}}.
$$

Poiché

$$
\sqrt{(k+1)(n-k+1)}\leq\frac{n+2}{2},
$$

si ottiene

$$
|c_n|\geq\frac{2(n+1)}{n+2}\to2.
$$

Il termine generale del prodotto non tende a zero: il prodotto diverge. La convergenza separata dei fattori non basta.

### **5. Permutazioni finite e infinite**

Una permutazione finita modifica soltanto l'ordine di un numero finito di addendi e non cambia somma né natura. Un riordinamento infinito usa una biiezione

$$
\pi:\mathbb N\to\mathbb N
$$

e produce $\sum a_{\pi(n)}$.

> **Teorema.** Se $\sum a_n$ converge assolutamente a $S$, ogni riordinamento converge assolutamente alla stessa somma.

**Dimostrazione.** Dato $\varepsilon>0$, scegliamo un insieme finito di indici $F$ tale che la somma dei moduli fuori da $F$ sia minore di $\varepsilon$. Una somma parziale abbastanza lunga del riordinamento contiene tutti gli indici di $F$; la differenza rispetto a $S$ coinvolge soltanto termini fuori da $F$ ed è quindi minore di $\varepsilon$. Lo stesso argomento sui moduli prova l'assoluta convergenza. $\square$

### **6. Teorema di riordinamento di Riemann**

> **Teorema.** Una serie reale condizionatamente convergente può essere riordinata per convergere a qualunque $L\in\mathbb R$, per divergere a $+\infty$ o a $-\infty$, oppure per oscillare senza limite.

Se $\sum a_n$ converge condizionatamente, le serie delle parti positive e negative divergono entrambe a $+\infty$. Per ottenere un valore $L$:

1. si aggiungono termini positivi non ancora usati finché la somma supera $L$;
2. si aggiungono termini negativi finché scende sotto $L$;
3. si ripete.

Ogni termine viene prima o poi usato. Le sovraelongazioni sono al più il modulo dell'ultimo termine aggiunto e tendono a zero, perché $a_n\to0$. Le somme parziali convergono dunque a $L$.

Per divergere a $+\infty$, si costruiscono blocchi positivi che superano livelli $1,2,3,\ldots$ inserendo via via anche i termini negativi; analogamente per $-\infty$. Alternando blocchi che superano livelli positivi e negativi crescenti si ottiene oscillazione.

<!-- TODO FIGURA: rappresentare la costruzione di Riemann verso un bersaglio L: blocchi di termini positivi oltre L, blocchi negativi sotto L e sovraelongazioni decrescenti; indicare che ogni termine viene utilizzato. Obiettivo didattico: mostrare perché la convergenza condizionata dipende dall'ordine. -->

### **7. Raggruppamenti**

Raggruppare termini consecutivi senza cambiarne l'ordine conserva la somma di una serie convergente, purché ogni gruppo sia finito e gli estremi dei gruppi tendano a infinito: le nuove somme parziali sono una sottosuccessione di quelle originali.

Il converso è falso: raggruppare può far convergere una serie originariamente divergente, come

$$
1-1+1-1+\cdots
$$

raggruppata in coppie. Perciò non si può dedurre la convergenza della serie originale dalla sola convergenza dei gruppi.

### **8. Errori frequenti**

1. Moltiplicare due serie termine a termine.
2. Scrivere il prodotto delle somme senza verificare assoluta convergenza o Mertens.
3. Confondere la diagonale $k+j=n$ con il quadrato $k,j\leq n$.
4. Riordinare una serie condizionata come se fosse assoluta.
5. Credere che un raggruppamento convergente renda convergente la serie originale.
6. Descrivere Riemann senza spiegare perché le parti positiva e negativa sono infinite.

### **9. Esercizi**

1. Calcolare i primi quattro coefficienti del prodotto di due geometriche.
2. Verificare direttamente che il prodotto di Cauchy di $\sum x^n$ con se stessa ha coefficienti $(n+1)x^n$.
3. Ricostruire la prova di assoluta convergenza del prodotto.
4. Completare tutti i dettagli della prova di Mertens.
5. Verificare il controesempio con $(-1)^n/\sqrt{n+1}$.
6. Dimostrare l'invarianza dei riordinamenti assoluti con un insieme finito $F$.
7. Descrivere i primi passi del riordinamento dell'armonica alternata verso $L=2$.
8. Costruire uno schema di riordinamento divergente a $+\infty$.
9. Spiegare perché raggruppare $1-1+1-1+\cdots$ non assegna una somma alla serie ordinaria.
10. Distinguere in un esempio prodotto termine a termine, prodotto di Cauchy e prodotto delle somme.

### **10. Riepilogo**

> ✅ Il prodotto di Cauchy è una convoluzione e richiede ipotesi di convergenza. L'assoluta convergenza rende prodotti e riordinamenti stabili; la convergenza condizionata è fragile, come formalizza il teorema di Riemann.
