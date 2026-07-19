# **M6 UD2 Lezione 3 - Criterio della radice**

### **1. Enunciato generale**

Per una serie reale o complessa poniamo

$$
\rho=\limsup_{n\to\infty}\sqrt[n]{|a_n|}.
$$

Allora:

- se $\rho<1$, $\sum a_n$ converge assolutamente;
- se $\rho>1$, inclusa $\rho=+\infty$, la serie diverge;
- se $\rho=1$, il criterio è inconcludente.

La formulazione usa il [limite superiore di M05](../../M05_Successioni/UD1%20-%20Definizioni%20ed%20esempi/L4B%20-%20Limite%20superiore%20e%20limite%20inferiore.md) e funziona anche quando la successione delle radici non possiede limite ordinario.

### **2. Dimostrazione della convergenza**

Se $\rho<1$, scegliamo $q$ con

$$
\rho<q<1.
$$

Per la definizione di limite superiore, definitivamente

$$
\sqrt[n]{|a_n|}\leq q.
$$

Elevando alla potenza $n$,

$$
|a_n|\leq q^n.
$$

Il confronto con la geometrica prova la convergenza assoluta. $\square$

### **3. Dimostrazione della divergenza**

Se $\rho>1$, scegliamo $q$ con $1<q<\rho$. Per la caratterizzazione del $\limsup$, esistono infiniti indici $n$ tali che

$$
\sqrt[n]{|a_n|}>q,
$$

quindi $|a_n|>q^n>1$ lungo una sottosuccessione. Il termine generale non tende a zero e la serie diverge. $\square$

Per $\rho=1$ si confrontino

$$
\sum\frac1n
\quad\text{e}\quad
\sum\frac1{n^2}:
$$

in entrambe $\sqrt[n]{|a_n|}\to1$, ma la prima diverge e la seconda converge.

### **4. Esempi con potenze $n$-esime**

Per

$$
a_n=\left(\frac{2n+1}{5n}\right)^n,
$$

$$
\sqrt[n]{a_n}=\frac{2n+1}{5n}\to\frac25<1,
$$

quindi la serie converge.

Per

$$
a_n=\left(1+\frac2n\right)^n,
$$

la radice tende a $1$, ma $a_n\to e^2\neq0$: la serie diverge per il termine generale. Il criterio della radice non sostituisce i controlli preliminari.

### **5. Esempio oscillante**

Definiamo

$$
a_n=
\begin{cases}
2^{-n}, & n\text{ pari},\\
3^{-n}, & n\text{ dispari}.
\end{cases}
$$

Le radici oscillano tra $1/2$ e $1/3$, quindi non hanno limite, ma

$$
\limsup\sqrt[n]{a_n}=\frac12<1.
$$

La serie converge. Questa è la ragione operativa per preferire la formulazione con $\limsup$.

### **6. Coefficienti binomiali e parametro**

Consideriamo

$$
\sum_{n=0}^{\infty}\binom{2n}{n}x^n.
$$

Dall'identità

$$
\sum_{k=0}^{2n}\binom{2n}{k}=4^n
$$

e dal fatto che il coefficiente centrale è il massimo, segue

$$
\frac{4^n}{2n+1}
\leq
\binom{2n}{n}
\leq4^n.
$$

Prendendo radici $n$-esime,

$$
\sqrt[n]{\binom{2n}{n}}\to4.
$$

Il criterio della radice dà convergenza assoluta per $|x|<1/4$ e divergenza per $|x|>1/4$. Nei punti $|x|=1/4$ il criterio vale $1$ e occorre uno studio separato. Per $x=1/4$ la minorazione precedente confronta i termini con $1/(2n+1)$ e prova la divergenza.

Per $x=-1/4$ poniamo

$$
b_n=\frac1{4^n}\binom{2n}{n}
=\prod_{k=1}^{n}\left(1-\frac1{2k}\right).
$$

Si ha

$$
\frac{b_{n+1}}{b_n}=\frac{2n+1}{2n+2}<1
$$

e, usando $\ln(1-t)\leq-t$ per $0<t<1$,

$$
0<b_n\leq
\exp\left(-\frac12\sum_{k=1}^{n}\frac1k\right)\to0.
$$

La serie converge quindi per Leibniz, ma non assolutamente perché la serie dei moduli è il caso $x=1/4$. La classificazione reale è completa: convergenza assoluta per $|x|<1/4$, condizionata per $x=-1/4$ e divergenza negli altri casi.

Questo è un esercizio parametrico numerico; la teoria generale del raggio delle serie di potenze è rinviata al blocco dedicato a Taylor e alle serie di funzioni.

### **7. Rapporto e radice a confronto**

Il rapporto è naturale per fattoriali, prodotti e ricorrenze consecutive. La radice è naturale per espressioni elevate alla potenza $n$ e resta applicabile tramite $\limsup$ in presenza di oscillazioni.

Quando esiste il limite del rapporto dei moduli, esso spesso coincide con il limite delle radici $n$-esime; il rapporto fornisce allora almeno la stessa informazione. Tuttavia può essere indefinito per termini nulli o molto più laborioso. Entrambi sono inconcludenti sulle $p$-serie perché rilevano soltanto la scala geometrica.

### **8. Stima del resto**

Se per $n\geq N$ si è provato $|a_n|\leq q^n$ con $q<1$, allora

$$
|R_N|\leq\sum_{n=N+1}^{\infty}q^n
=\frac{q^{N+1}}{1-q}.
$$

Il criterio della radice produce quindi una stima una volta scelta una quota $q$ definitivamente valida; il valore $\rho$ da solo non è una ragione esatta.

### **9. Errori frequenti**

1. Usare il limite ordinario quando non esiste e ignorare il $\limsup$.
2. Concludere nel caso $\rho=1$.
3. Dimenticare il valore assoluto.
4. Credere che $\rho>1$ implichi $a_n\to+\infty$ con segno fissato: basta la mancata convergenza a zero del modulo.
5. Non studiare separatamente i parametri di frontiera.
6. Confondere una maggiorazione $|a_n|\leq q^n$ con un'equivalenza.

### **10. Esercizi**

1. Dimostrare i due rami del criterio usando la definizione di $\limsup$.
2. Studiare $\sum[(3n+1)/(4n-2)]^n$.
3. Costruire una serie per cui le radici oscillino ma il $\limsup$ sia minore di $1$.
4. Studiare $\sum(1+1/n)^{-n^2}$.
5. Applicare rapporto e radice a $\sum n^n/(3^n n!)$ e confrontare i calcoli.
6. Ricostruire le stime sul coefficiente binomiale centrale.
7. Classificare $\sum\binom{2n}{n}x^n$ per ogni $x\in\mathbb R$, motivando separatamente i due punti di frontiera.
8. Spiegare perché rapporto e radice non decidono alcuna $p$-serie.
9. Ottenere una stima esplicita del resto quando $\sqrt[n]{|a_n|}\leq0{,}8$ per $n\geq20$.
10. Fornire un esempio in cui il rapporto non sia definito infinite volte ma la radice decida.

### **11. Riepilogo**

> ✅ La radice misura la crescita geometrica globale del singolo termine. La formulazione con $\limsup$ è quella robusta; i punti di frontiera richiedono sempre un criterio diverso.
