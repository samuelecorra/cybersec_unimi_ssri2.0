# **M5 UD3 Lezione 2A - Stolz-Cesaro e medie di Cesaro**

### **1. Teorema di Stolz-Cesaro**

> **Teorema.** Siano $(a_n)$ reale e $(b_n)$ strettamente crescente, con $b_n\to+\infty$. Se esiste finito

$$
\lim_{n\to\infty}
\frac{a_{n+1}-a_n}{b_{n+1}-b_n}=L,
$$

allora

$$
\lim_{n\to\infty}\frac{a_n}{b_n}=L.
$$

Esistono versioni per limiti infiniti e per denominatori decrescenti verso $0$; qui si usa soltanto la forma enunciata.

---

### **2. Dimostrazione**

Dato $\varepsilon>0$, esiste $N$ tale che per $k\ge N$

$$
L-\varepsilon
<
\frac{a_{k+1}-a_k}{b_{k+1}-b_k}
<L+\varepsilon.
$$

Poiché $b_{k+1}-b_k>0$, sommando da $k=N$ a $n-1$ otteniamo

$$
(L-\varepsilon)(b_n-b_N)
<a_n-a_N
<(L+\varepsilon)(b_n-b_N).
$$

Dividendo per $b_n>0$ definitivamente,

$$
(L-\varepsilon)\left(1-\frac{b_N}{b_n}\right)+\frac{a_N}{b_n}
<\frac{a_n}{b_n}
$$

e un analogo maggiorante con $L+\varepsilon$. Poiché $b_n\to+\infty$, i termini con $a_N,b_N$ tendono a $0$. Ne segue

$$
L-\varepsilon\le\liminf\frac{a_n}{b_n}
\le\limsup\frac{a_n}{b_n}\le L+\varepsilon.
$$

L'arbitrarietà di $\varepsilon$ conclude la prova. $\square$

> ⚠️ Stolz-Cesaro non è l'Hôpital discreto da applicare automaticamente: crescita stretta e divergenza del denominatore sono ipotesi essenziali.

---

### **3. Applicazioni**

Per

$$
\frac{1+2+\cdots+n}{n^2},
$$

poniamo $a_n=\sum_{k=1}^nk$, $b_n=n^2$. Allora

$$
\frac{a_{n+1}-a_n}{b_{n+1}-b_n}
=
\frac{n+1}{2n+1}\to\frac12,
$$

quindi il rapporto tende a $1/2$.

Per $a>1$ e $p\in\mathbb N$,

$$
\frac{n^p}{a^n}\to0
$$

può essere ottenuto iterando Stolz sul rapporto con denominatore $a^n$, perché le differenze finite abbassano il grado del polinomio.

---

### **4. Teorema delle medie di Cesaro**

> **Teorema.** Se $a_n\to L$, allora

$$
\sigma_n=\frac{a_1+\cdots+a_n}{n}\to L.
$$

**Dimostrazione diretta.** Scriviamo

$$
\sigma_n-L
=
\frac1n\sum_{k=1}^n(a_k-L).
$$

Dato $\varepsilon>0$, scegliamo $N$ tale che $|a_k-L|<\varepsilon/2$ per $k\ge N$. La parte iniziale ha somma fissa

$$
C=\sum_{k=1}^{N-1}|a_k-L|.
$$

Per $n$ abbastanza grande, $C/n<\varepsilon/2$; quindi

$$
|\sigma_n-L|
\le\frac Cn+\frac1n\sum_{k=N}^n|a_k-L|
<\varepsilon.
$$

$\square$

Il converso è falso: per $a_n=(-1)^n$, le medie tendono a $0$, ma la successione non converge.

---

### **5. Collegamento tra Stolz e Cesaro**

Ponendo

$$
A_n=a_1+\cdots+a_n,\qquad b_n=n,
$$

si ha

$$
\frac{A_{n+1}-A_n}{(n+1)-n}=a_{n+1}\to L.
$$

Stolz-Cesaro produce $A_n/n\to L$. La prova diretta resta utile perché mostra il meccanismo coda più parte iniziale.

---

### **6. Lemma di Toeplitz come approfondimento**

Siano $t_{n,k}\ge0$ con

$$
\sum_k t_{n,k}=1,
$$

ogni riga finita e, per ogni $k$ fissato,

$$
t_{n,k}\to0.
$$

Se $a_k\to L$, allora

$$
\sum_k t_{n,k}a_k\to L.
$$

La dimostrazione ripete la separazione tra un numero finito di termini iniziali e una coda uniforme. Le medie di Cesaro corrispondono a $t_{n,k}=1/n$ per $1\le k\le n$. Questo lemma è facoltativo e prepara metodi di media nelle serie.

---

### **7. Errori frequenti**

1. Applicare Stolz con $b_n$ non strettamente crescente.
2. Dimenticare $b_n\to+\infty$.
3. Usare il teorema quando il limite delle differenze non esiste.
4. Credere vero il converso di Cesaro.
5. Confondere media dei primi $n$ termini e termine medio di una coppia.

---

### **8. Esercizi**

1. Usa Stolz per calcolare $(1^2+\cdots+n^2)/n^3$.
2. Calcola $(\ln1+\cdots+\ln n)/n\ln n$ con uno strumento appropriato.
3. Dimostra Cesaro usando Stolz.
4. Calcola le medie di $a_n=(-1)^n$ separando $n$ pari e dispari.
5. Trova una successione divergente le cui medie divergano a $+\infty$.
6. Verifica le ipotesi di Toeplitz per le medie pesate $2k/[n(n+1)]$.
7. Mostra con un controesempio che $b_n\to+\infty$ senza monotonia non basta alla forma enunciata di Stolz.

---

### **9. Riepilogo**

> ✅ Stolz-Cesaro confronta incrementi sotto ipotesi precise; Cesaro conserva i limiti ma può regolarizzare successioni oscillanti, perciò il converso è falso.
