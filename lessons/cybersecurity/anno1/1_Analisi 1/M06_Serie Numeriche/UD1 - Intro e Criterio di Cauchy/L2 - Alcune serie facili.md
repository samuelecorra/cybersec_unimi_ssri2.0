# **M6 UD1 Lezione 2 - Serie geometriche, telescopiche e armoniche**

### **1. Serie geometriche**

Per $q\in\mathbb R$ o $q\in\mathbb C$, consideriamo

$$
\sum_{n=0}^{\infty}q^n.
$$

Se $q\neq1$, moltiplicando la somma parziale per $1-q$ si ottiene

$$
(1-q)S_N=(1+q+\cdots+q^N)-(q+q^2+\cdots+q^{N+1})
=1-q^{N+1},
$$

quindi

$$
S_N=\frac{1-q^{N+1}}{1-q}.
$$

Se $|q|<1$, allora $q^{N+1}\to0$ e

$$
\sum_{n=0}^{\infty}q^n=\frac1{1-q}.
$$

Se $|q|\geq1$, il termine generale $q^n$ non tende a zero: per $q=1$ è costante, per $q=-1$ alterna e per $|q|>1$ cresce in modulo. Pertanto

$$
\sum_{n=0}^{\infty}q^n\text{ converge}
\quad\Longleftrightarrow\quad
|q|<1.
$$

<!-- TODO FIGURA: per q=1/2, rappresentare i termini q^n e le somme parziali S_N=2-2^{-N} su due pannelli coordinati, con retta limite S=2 e resto geometrico. Obiettivo didattico: mostrare come termini positivi decrescenti producano somme parziali crescenti e limitate. -->

<!-- TODO FIGURA: quattro pannelli delle somme parziali geometriche per q=1, q=-1, q=3/2 e q=-3/2; indicare crescita, oscillazione limitata e oscillazione illimitata. Obiettivo didattico: distinguere tutti i casi divergenti della serie geometrica. -->

### **2. Geometrica con coefficienti e indice generico**

Per $|q|<1$,

$$
\sum_{n=n_0}^{\infty}a q^n
=\frac{a q^{n_0}}{1-q}.
$$

Se la somma parziale termina all'indice $N$, il resto esatto è

$$
R_N=\sum_{n=N+1}^{\infty}a q^n
=\frac{a q^{N+1}}{1-q}.
$$

Per l'errore assoluto,

$$
|R_N|=\frac{|a|\,|q|^{N+1}}{|1-q|}.
$$

Occorre distinguere questa formula dalla maggiorazione più grossolana ottenuta sostituendo $|1-q|$ con $1-|q|$.

### **3. Serie telescopiche**

Una serie è telescopica quando

$$
a_n=b_n-b_{n+1}.
$$

Allora

$$
S_N=\sum_{n=n_0}^{N}(b_n-b_{n+1})
=b_{n_0}-b_{N+1}.
$$

La serie converge se e solo se $(b_{N+1})$ converge; se $b_n\to L$,

$$
\sum_{n=n_0}^{\infty}(b_n-b_{n+1})=b_{n_0}-L.
$$

Il resto è particolarmente trasparente:

$$
R_N=b_{N+1}-L.
$$

La serie di Mengoli soddisfa

$$
\frac1{n(n+1)}=\frac1n-\frac1{n+1},
$$

perciò

$$
\sum_{n=1}^{\infty}\frac1{n(n+1)}=1.
$$

<!-- TODO FIGURA: scrivere su righe successive i termini (1-1/2)+(1/2-1/3)+...+(1/N-1/(N+1)); usare frecce per le cancellazioni e lasciare evidenti i due termini di bordo. Obiettivo didattico: visualizzare il telescopaggio senza perdere indici o termini residui. -->

### **4. Telescopiche meno immediate**

La decomposizione

$$
\frac1{n(n+2)}=\frac12\left(\frac1n-\frac1{n+2}\right)
$$

produce cancellazioni a distanza due. Per $N\geq1$,

$$
\sum_{n=1}^{N}\frac1{n(n+2)}
=\frac12\left(1+\frac12-\frac1{N+1}-\frac1{N+2}\right),
$$

e quindi la somma è $3/4$.

Un altro schema è

$$
\sqrt{n+1}-\sqrt n,
$$

le cui somme parziali valgono $\sqrt{N+1}-1$ e divergono a $+\infty$: essere telescopica non implica convergere.

### **5. Divergenza della serie armonica**

Consideriamo

$$
\sum_{n=1}^{\infty}\frac1n.
$$

Raggruppiamo gli addendi in blocchi dyadici:

$$
1+\frac12
+\left(\frac13+\frac14\right)
+\left(\frac15+\cdots+\frac18\right)+\cdots.
$$

Nel blocco da $2^{k-1}+1$ a $2^k$ ci sono $2^{k-1}$ termini, ciascuno almeno $1/2^k$. Il contributo del blocco è dunque almeno

$$
2^{k-1}\frac1{2^k}=\frac12.
$$

Le somme parziali $S_{2^m}$ sono almeno $1+m/2$ e tendono a $+\infty$. Poiché i termini sono positivi, tutte le somme parziali crescono: la serie armonica diverge a $+\infty$.

<!-- TODO FIGURA: raggruppare i termini della serie armonica in blocchi di lunghezza 1,2,4,8; sotto ogni blocco mostrare la minorazione uniforme 1/2 e le somme parziali S_{2^m}. Obiettivo didattico: rendere visibile la divergenza nonostante 1/n tenda a zero. -->

### **6. Serie armoniche generalizzate**

La serie

$$
\sum_{n=1}^{\infty}\frac1{n^p}
$$

è detta **$p$-serie**.

Se $p\leq0$, i termini non tendono a zero. Se $0<p\leq1$,

$$
\frac1{n^p}\geq\frac1n,
$$

quindi la serie diverge per confronto con l'armonica.

Se $p>1$, nel blocco $2^k\leq n<2^{k+1}$ ci sono $2^k$ termini e

$$
\frac1{n^p}\leq\frac1{2^{kp}}.
$$

Il blocco è quindi al più

$$
2^k\frac1{2^{kp}}=2^{-k(p-1)}.
$$

La somma dei maggioranti è geometrica con ragione $2^{-(p-1)}<1$. Per confronto, la $p$-serie converge. Abbiamo dimostrato

$$
\sum_{n=1}^{\infty}\frac1{n^p}\text{ converge}
\quad\Longleftrightarrow\quad
p>1.
$$

Il criterio integrale fornirà in M11 una dimostrazione alternativa, ma non è necessario qui.

### **7. Rappresentazioni decimali**

Il decimale periodico $0{,}999\ldots$ significa il limite delle somme parziali

$$
\sum_{n=1}^{N}\frac9{10^n}.
$$

Essendo una geometrica,

$$
\sum_{n=1}^{\infty}\frac9{10^n}
=\frac{9/10}{1-1/10}=1.
$$

Non esiste un «ultimo 9». L'uguaglianza mostra la non unicità di alcune rappresentazioni decimali: ogni decimale finito ha anche una rappresentazione con una coda infinita di $9$.

### **8. Velocità di convergenza**

Due serie possono convergere ma richiedere numeri di termini molto diversi. Per la geometrica con $q=1/2$,

$$
|R_N|=2^{-N}.
$$

Per ottenere errore inferiore a $10^{-6}$ bastano circa venti termini. La serie armonica alternata, studiata in UD3, ha invece una stima del resto dell'ordine di $1/N$ e richiede circa un milione di termini per la stessa garanzia.

<!-- TODO FIGURA: grafico semilogaritmico degli errori garantiti 2^{-N} e 1/(N+1), con soglia 10^{-6} e indici richiesti evidenziati. Obiettivo didattico: distinguere convergenza teorica e velocità numerica di convergenza. -->

### **9. Errori frequenti**

1. Usare la formula geometrica quando $|q|\geq1$.
2. Dimenticare il fattore $q^{n_0}$ con indice iniziale non nullo.
3. Scrivere il resto geometrico con $q^N$ invece di $q^{N+1}$.
4. Cancellare termini telescopici senza espandere i bordi.
5. Credere che ogni serie telescopica converga.
6. Dedurre la convergenza dell'armonica da $1/n\to0$.
7. Citare la classificazione delle $p$-serie senza controllare il caso di frontiera $p=1$.

### **10. Esercizi**

1. Classificare $\sum q^n$ per $q=2/3,-1/2,1,-1,2,-2$ tramite somme parziali.
2. Calcolare somma e resto di $\sum_{n=3}^{\infty}5(1/4)^n$.
3. Trovare il minimo $N$ che garantisce errore inferiore a $10^{-8}$ per $\sum_{n=0}^{\infty}3^{-n}$.
4. Calcolare $\sum_{n=2}^{\infty}1/[n(n+1)]$.
5. Verificare la formula delle somme parziali di $\sum1/[n(n+2)]$.
6. Studiare $\sum(\sqrt{n+1}-\sqrt n)$ senza invocare criteri esterni.
7. Ricostruire la prova per blocchi della divergenza armonica.
8. Dimostrare la convergenza della $p$-serie per $p>1$ dettagliando il confronto tra blocchi.
9. Trasformare $0{,}272727\ldots$ in frazione mediante una serie geometrica.
10. Confrontare il numero di termini necessario per errori $10^{-3}$ e $10^{-6}$ nelle geometriche di ragione $1/2$ e $9/10$.

### **11. Riepilogo**

> ✅ Geometriche e telescopiche si risolvono dalle somme parziali; armonica e $p$-serie richiedono confronti per blocchi. Le formule del resto e gli indici sono parte del risultato, non dettagli ornamentali.
