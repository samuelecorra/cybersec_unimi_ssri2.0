# **M6 UD3 Lezione 2C - Serie complesse e doppie**

### **1. Serie a valori complessi**

Una **serie complessa** è una serie

$$
\sum_{n=n_0}^{\infty}z_n,
\qquad z_n\in\mathbb{C},
$$

definita, come nel caso reale, attraverso le somme parziali

$$
S_N=\sum_{n=n_0}^{N}z_n.
$$

La serie converge a $S\in\mathbb{C}$ se $S_N\to S$ nel piano complesso, cioè se $|S_N-S|\to0$.

Scrivendo $z_n=x_n+iy_n$ e $S=A+iB$, si ha

$$
\sum z_n=S
\quad\Longleftrightarrow\quad
\sum x_n=A
\quad\text{e}\quad
\sum y_n=B.
$$

**Dimostrazione.** Poiché

$$
|\operatorname{Re}w|\le |w|,
\qquad
|\operatorname{Im}w|\le |w|,
$$

la convergenza complessa implica quella delle parti reale e immaginaria. Viceversa, se entrambe convergono, allora

$$
|S_N-S|
\le
|\operatorname{Re}(S_N-S)|+|\operatorname{Im}(S_N-S)|\to0.
$$

La teoria reale si trasferisce quindi alle serie complesse, purché le disuguaglianze d'ordine siano sostituite da stime sul modulo.

### **2. Convergenza assoluta nel campo complesso**

La serie $\sum z_n$ converge **assolutamente** se

$$
\sum_{n=n_0}^{\infty}|z_n|<\infty.
$$

Anche in $\mathbb{C}$ la convergenza assoluta implica la convergenza ordinaria. Infatti, per $q>p$,

$$
\left|\sum_{n=p+1}^{q}z_n\right|
\le
\sum_{n=p+1}^{q}|z_n|,
$$

e il criterio di Cauchy applicato alla serie reale dei moduli conclude la dimostrazione.

#### **2.1 Serie geometrica complessa**

Per $z\in\mathbb{C}$,

$$
\sum_{n=0}^{\infty}z^n
$$

converge se e solo se $|z|<1$ e in tal caso

$$
\sum_{n=0}^{\infty}z^n=\frac{1}{1-z}.
$$

Se $|z|<1$, la formula segue dall'identità finita

$$
(1-z)\sum_{n=0}^{N}z^n=1-z^{N+1}
$$

e dal fatto che $|z^{N+1}|=|z|^{N+1}\to0$. Se $|z|\ge1$, il termine generale $z^n$ non tende a zero.

Questo esempio collega le serie numeriche alle oscillazioni complesse $e^{in\theta}$ usate in analisi armonica e nell'elaborazione dei segnali.

### **3. Serie doppie**

Sia $(a_{m,n})_{m,n\ge0}$ una famiglia di numeri reali o complessi. Non basta scrivere informalmente $\sum_{m,n}a_{m,n}$: occorre precisare come vengono sommati i termini.

Le **somme rettangolari** sono

$$
S_{M,N}=\sum_{m=0}^{M}\sum_{n=0}^{N}a_{m,n}.
$$

Si dice che la serie doppia converge in senso rettangolare a $S$ se

$$
\forall\varepsilon>0\ \exists K\in\mathbb{N}\ \forall M,N\ge K:
\quad |S_{M,N}-S|<\varepsilon.
$$

Si possono inoltre considerare le due somme iterate

$$
\sum_{m=0}^{\infty}\left(\sum_{n=0}^{\infty}a_{m,n}\right),
\qquad
\sum_{n=0}^{\infty}\left(\sum_{m=0}^{\infty}a_{m,n}\right),
$$

oppure enumerare le coppie $(m,n)$ lungo diagonali. Queste procedure non sono automaticamente equivalenti.

<!-- TODO FIGURA: griglia del primo quadrante con celle a_(m,n), rettangolo 0<=m<=M e 0<=n<=N, righe e colonne orientate e diagonali m+n=k numerate. Obiettivo didattico: confrontare somme rettangolari, somme iterate ed enumerazione diagonale di una serie doppia. -->

### **4. Termini non negativi: teorema di Tonelli**

Se $a_{m,n}\ge0$, tutte le somme parziali aumentano quando si amplia l'insieme finito degli indici. Si definisce allora, anche con valore $+\infty$,

$$
\sum_{m,n\ge0}a_{m,n}
=
\sup\left\{
\sum_{(m,n)\in F}a_{m,n}:F\subseteq\mathbb{N}^2\text{ finito}
\right\}.
$$

Il **teorema di Tonelli per le serie** afferma che

$$
\sum_{m,n\ge0}a_{m,n}
=
\sum_{m=0}^{\infty}\left(\sum_{n=0}^{\infty}a_{m,n}\right)
=
\sum_{n=0}^{\infty}\left(\sum_{m=0}^{\infty}a_{m,n}\right),
$$

dove è ammesso il valore $+\infty$.

**Idea della dimostrazione.** Ogni somma su un insieme finito di coppie è dominata da una somma su un rettangolo sufficientemente grande; viceversa, ogni rettangolo è un insieme finito. Il supremo delle somme finite coincide quindi con il supremo delle somme rettangolari. La monotonia permette poi di completare prima le righe o prima le colonne senza cancellazioni.

### **5. Convergenza assoluta: teorema di Fubini**

Se

$$
\sum_{m,n\ge0}|a_{m,n}|<\infty,
$$

il **teorema di Fubini per le serie** garantisce che:

1. ogni riga e ogni colonna converge assolutamente;
2. le due somme iterate convergono;
3. le somme iterate, le somme rettangolari e ogni enumerazione di $\mathbb{N}^2$ hanno lo stesso valore.

**Giustificazione.** Tonelli applicato a $|a_{m,n}|$ mostra che tutte le code fuori da un insieme finito possono essere rese piccole. Due diversi ordini di somma differiscono, dopo aver incluso lo stesso nucleo finito, soltanto per termini contenuti in tale coda; la disuguaglianza triangolare ne controlla uniformemente il contributo.

Il risultato è l'analogo bidimensionale dell'invarianza per riordinamento delle serie assolutamente convergenti.

### **6. Perché l'ipotesi assoluta è necessaria**

Definiamo, per $m,n\ge0$,

$$
a_{m,n}=
\begin{cases}
1, & n=m,\\
-1, & n=m+1,\\
0, & \text{altrimenti}.
\end{cases}
$$

Ogni riga contiene $1$ e subito dopo $-1$, quindi

$$
\sum_{m=0}^{\infty}\left(\sum_{n=0}^{\infty}a_{m,n}\right)=0.
$$

La colonna $n=0$ contiene soltanto $1$; ogni colonna $n\ge1$ contiene invece $-1$ e $1$. Pertanto

$$
\sum_{n=0}^{\infty}\left(\sum_{m=0}^{\infty}a_{m,n}\right)=1.
$$

Le due somme iterate esistono ma sono diverse. Inoltre

$$
\sum_{m,n\ge0}|a_{m,n}|=+\infty,
$$

quindi Fubini non è applicabile. L'esempio mostra concretamente che cancellazioni distribuite in ordini diversi possono cambiare il risultato.

### **7. Collegamenti e confini del modulo**

L'enumerazione diagonale usa la [stabilità della numerabilità](../../M03_Finito%20ed%20Infinito/UD2%20-%20Numerabilit%C3%A0%20e%20potenza%20del%20continuo/L1A%20-%20Stabilit%C3%A0%20della%20numerabilit%C3%A0%20e%20applicazioni.md) di $\mathbb{N}^2$, trattata in M03. Qui interessa la conseguenza analitica: enumerare una famiglia doppia produce una serie semplice, ma l'indipendenza dall'enumerazione richiede non negatività o convergenza assoluta.

Una **serie di potenze** ha forma

$$
\sum_{n=0}^{\infty}c_n(x-x_0)^n.
$$

Per ogni $x$ fissato è una serie numerica e i criteri di rapporto o radice possono studiarne la convergenza. La teoria organica del raggio e dell'intervallo di convergenza, della convergenza uniforme e delle operazioni termine a termine appartiene però ai moduli sulle successioni e serie di funzioni e alla trattazione di Taylor. M06 non la anticipa integralmente.

Le serie doppie preparano analogamente le future somme multiple e gli scambi tra limite, somma e integrale, che richiederanno ipotesi specifiche nei moduli successivi.

### **8. Applicazioni numeriche e informatiche**

In un calcolo reale si somma sempre un insieme finito di termini. Anche quando la serie converge assolutamente, l'ordine può influire sull'errore di arrotondamento in virgola mobile: sommare quantità con scale molto diverse può perdere cifre significative. Metodi come la somma compensata riducono tale errore numerico, ma non modificano il valore matematico della serie.

Le convoluzioni discrete sono prodotti di Cauchy finiti o troncati; le trasformate discrete organizzano invece somme complesse contenenti radici dell'unità. I teoremi di questa lezione spiegano quali passaggi al limite richiedano un controllo assoluto.

### **9. Esercizi rappresentativi**

1. Stabilire per quali $z\in\mathbb{C}$ converge $\sum_{n=1}^{\infty}(z/2)^n$ e calcolarne la somma.
2. Dimostrare direttamente che se $\sum z_n$ converge assolutamente, allora convergono assolutamente $\sum\operatorname{Re}z_n$ e $\sum\operatorname{Im}z_n$.
3. Calcolare $\sum_{m,n\ge0}2^{-m-n}$ prima per righe e poi per diagonali.
4. Applicare Tonelli a $a_{m,n}=1/[(m+1)^2(n+1)^2]$ e scrivere la somma doppia come prodotto di due serie.
5. Verificare tutte le somme di riga e di colonna dell'esempio della sezione 6 e calcolare le somme rettangolari $S_{M,N}$ lungo $M=N$ e lungo $N=M+1$.
6. Costruire una matrice con un numero finito di termini non nulli per riga e per colonna le cui due somme iterate coincidano, ma la serie dei moduli diverga.
7. Per $a_{m,n}=x^{m+n}$, determinare i valori reali di $x$ per cui è lecito applicare Fubini e calcolare la somma.
8. Spiegare perché una bigezione $\mathbb{N}\to\mathbb{N}^2$ non basta, da sola, a garantire che ogni enumerazione dia la stessa somma.
9. Classificare, per ogni $x\in\mathbb{R}$, la serie numerica $\sum_{n=1}^{\infty}x^n/n^2$, senza usare teoremi sulle serie di funzioni.
10. Progettare un criterio di arresto per una somma complessa troncata quando è disponibile una maggiorazione della coda $\sum_{n>N}|z_n|$.

### **10. Errori frequenti**

- Trattare $\mathbb{C}$ come un insieme ordinato e parlare di termini complessi positivi.
- Confondere $\sum_{m,n}a_{m,n}$ con una notazione completa senza specificare il modo di convergenza.
- Scambiare righe e colonne soltanto perché entrambe le somme interne esistono.
- Applicare Fubini senza controllare la convergenza assoluta.
- Dimenticare che Tonelli ammette anche il risultato $+\infty$.
- Concludere che una diversa risposta numerica in virgola mobile contraddica l'invarianza matematica per riordinamento assoluto.

### **11. Riepilogo**

> ✅ Le serie complesse si controllano mediante il modulo e le componenti reale e immaginaria. Per le serie doppie, non negatività e convergenza assoluta sono le ipotesi che rendono legittimo cambiare l'ordine di somma; senza di esse, persino due somme iterate esistenti possono avere valori diversi.
