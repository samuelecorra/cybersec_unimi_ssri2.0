# **M5 UD2 Lezione 3 - Successioni di Cauchy e completezza**

### **1. Definizione**

Una successione reale $(a_n)$ è di **Cauchy** se

$$
\forall\varepsilon>0\ \exists N\in\mathbb N\
\ \forall m,n\ge N:
|a_n-a_m|<\varepsilon.
$$

La definizione confronta due termini qualsiasi della stessa coda e non presuppone di conoscere un limite.

<!-- TODO FIGURA: grafico discreto con una coda contenuta in un intervallo di diametro epsilon, due indici arbitrari m,n>=N e distanza verticale |a_n-a_m|; obiettivo didattico: distinguere criterio di Cauchy e convergenza verso un limite già noto. -->

---

### **2. Convergente implica Cauchy**

**Teorema.** Ogni successione convergente è di Cauchy.

**Dimostrazione.** Se $a_n\to L$, dato $\varepsilon>0$ scegliamo $N$ tale che

$$
|a_j-L|<\frac\varepsilon2
$$

per ogni $j\ge N$. Per $m,n\ge N$,

$$
|a_n-a_m|
\le|a_n-L|+|a_m-L|
<\varepsilon.
$$

Quindi $(a_n)$ è di Cauchy. $\square$

---

### **3. Cauchy implica limitata**

**Teorema.** Ogni successione di Cauchy è limitata.

**Dimostrazione.** Usiamo $\varepsilon=1$. Esiste $N$ tale che per $n\ge N$

$$
|a_n-a_N|<1.
$$

Quindi $|a_n|<1+|a_N|$ per la coda. Includendo i primi $N$ termini mediante un massimo finito si ottiene un limite globale. $\square$

---

### **4. Cauchy implica convergente in R**

**Teorema.** Ogni successione reale di Cauchy converge.

**Dimostrazione.** Una successione di Cauchy è limitata; per Bolzano-Weierstrass possiede una sottosuccessione

$$
a_{n_k}\to L.
$$

Dati $\varepsilon>0$, scegliamo $N_1$ tale che $m,n\ge N_1$ implichi $|a_n-a_m|<\varepsilon/2$. Scegliamo poi $k$ abbastanza grande da avere

$$
n_k\ge N_1,
\qquad
|a_{n_k}-L|<\frac\varepsilon2.
$$

Per ogni $n\ge N_1$,

$$
|a_n-L|
\le|a_n-a_{n_k}|+|a_{n_k}-L|
<\varepsilon.
$$

Quindi $a_n\to L$. $\square$

Ne segue il **criterio completo di Cauchy**:

$$
(a_n)\text{ converge in }\mathbb R
\iff
(a_n)\text{ è di Cauchy}.
$$

---

### **5. Perché il campo conta**

In $\mathbb Q$ il criterio sufficiente fallisce. Definiamo

$$
q_n=\frac{\lfloor10^n\sqrt2\rfloor}{10^n}\in\mathbb Q.
$$

Si ha

$$
0\le\sqrt2-q_n<10^{-n},
$$

quindi $(q_n)$ è di Cauchy, ma non converge a un elemento di $\mathbb Q$: il suo limite reale è $\sqrt2\notin\mathbb Q$.

La completezza non significa che ogni successione converga, ma che ogni successione internamente compatibile nel senso di Cauchy possiede il proprio limite nel campo.

---

### **6. Forme equivalenti della completezza**

In $\mathbb R$ sono strettamente collegate:

- proprietà dell'estremo superiore;
- convergenza delle monotone limitate;
- teorema degli intervalli annidati;
- Bolzano-Weierstrass;
- completezza di Cauchy.

In M05 sono state dimostrate le implicazioni effettivamente usate:

$$
\text{supremo}\Rightarrow\text{monotone e intervalli annidati},
$$

$$
\text{intervalli annidati}\Rightarrow\text{Bolzano-Weierstrass},
$$

$$
\text{Bolzano-Weierstrass}\Rightarrow\text{Cauchy completo}.
$$

La dimostrazione di tutte le equivalenze inverse è un approfondimento non necessario alla progressione.

---

### **7. Successioni complesse di Cauchy**

Una successione $(z_n)$ in $\mathbb C$ è di Cauchy se

$$
|z_n-z_m|<\varepsilon
$$

per $m,n$ sufficientemente grandi. Poiché le componenti reali e immaginarie sono di Cauchy e $\mathbb R$ è completo, anche $\mathbb C$ è completo rispetto al modulo. Questo è il solo ampliamento complesso necessario per preparare future serie complesse.

---

### **8. Esempi**

La successione $1/(n+1)$ è Cauchy perché converge. Una verifica diretta usa, per $m,n\ge N$,

$$
\left|\frac1{n+1}-\frac1{m+1}\right|
\le\frac1{n+1}+\frac1{m+1}
\le\frac2{N+1}.
$$

La successione $(-1)^n$ non è Cauchy: per ogni $N$ esistono un indice pari e uno dispari maggiori di $N$, con distanza $2$.

---

### **9. Errori frequenti**

1. Confrontare solo termini consecutivi: $|a_{n+1}-a_n|\to0$ non implica Cauchy, come mostra $a_n=\ln(n+1)$.
2. Usare un limite nella definizione di Cauchy.
3. Controllare una sola coppia $m,n$.
4. Dimenticare che la sufficienza dipende dalla completezza dello spazio.
5. Confondere Cauchy e monotonia.

---

### **10. Esercizi**

1. Verifica direttamente che $2^{-n}$ è di Cauchy.
2. Nega formalmente la proprietà di Cauchy.
3. Dimostra che una successione di Cauchy possiede al più un valore di aderenza.
4. Mostra che $a_n=\sum_{k=1}^n1/k$ non è di Cauchy scegliendo $m=2n$ e stimando la differenza.
5. Controlla che le approssimazioni decimali per difetto di $\sqrt3$ sono Cauchy in $\mathbb Q$ ma non vi convergono.
6. Dimostra la completezza di $\mathbb C$ tramite le componenti.
7. Costruisci una successione limitata non Cauchy.
8. Spiega quali teoremi della sezione 6 dipendono dall'ordine e quali soltanto dalla distanza.

---

### **11. Riepilogo**

> ✅ In $\mathbb R$ convergenza e proprietà di Cauchy sono equivalenti; la direzione difficile usa limitatezza, Bolzano-Weierstrass e completezza.
