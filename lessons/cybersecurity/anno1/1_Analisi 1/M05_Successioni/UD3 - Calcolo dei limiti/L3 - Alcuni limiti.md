# **M5 UD3 Lezione 3 - Limiti notevoli e tecniche**

### **1. Rapporti di polinomi**

Per $P,Q$ polinomi, si divide per la massima potenza di $n$ presente. Se $p=\deg P$ e $q=\deg Q$:

- $p<q$: il rapporto tende a $0$;
- $p=q$: tende al rapporto dei coefficienti principali;
- $p>q$: cresce in modulo come $n^{p-q}$, con segno da controllare.

Esempio:

$$
\frac{-2n^3+n}{5n^2-1}\sim-\frac25n\to-\infty.
$$

---

### **2. Radicali e razionalizzazione**

Per

$$
a_n=\sqrt{n^2+an+b}-n,
$$

si usa il coniugato:

$$
a_n
=
\frac{an+b}{\sqrt{n^2+an+b}+n}\to\frac a2,
$$

purché il radicando sia definito definitivamente. La scala dominante si identifica solo dopo avere controllato segno e dominio.

---

### **3. Radici n-esime**

Per $c>0$,

$$
\sqrt[n]{c}\to1.
$$

Se $c\ge1$, poniamo $\sqrt[n]{c}=1+h_n$ con $h_n\ge0$. Bernoulli, dimostrata in UD4/L0, dà

$$
c=(1+h_n)^n\ge1+nh_n,
$$

quindi $0\le h_n\le(c-1)/n\to0$. Se $0<c<1$, si applica il caso precedente a $1/c$ e si usa il reciproco.

Inoltre

$$
\sqrt[n]{n}\to1.
$$

Per $n\ge2$, ponendo $n=(1+h_n)^n$ e usando i primi tre termini binomiali,

$$
n\ge\binom n2h_n^2,
$$

da cui

$$
0\le h_n\le\sqrt{\frac{2}{n-1}}\to0.
$$

---

### **4. Crescite fondamentali**

Per $a>1$ e $\alpha>0$:

$$
\frac{n^\alpha}{a^n}\to0.
$$

Per $\alpha$ intero il risultato segue da Stolz iterato o dal rapporto consecutivo; il caso reale si domina con un intero $p>\alpha$.

Inoltre

$$
\frac{a^n}{n!}\to0.
$$

Infatti, scelto $N>2a$, per $n\ge N$ ogni nuovo rapporto soddisfa

$$
\frac{a^{n+1}/(n+1)!}{a^n/n!}=\frac a{n+1}<\frac12,
$$

quindi la coda è dominata da una geometrica.

Infine

$$
\frac{n!}{n^n}\to0,
$$

perché almeno $\lfloor n/2\rfloor$ fattori di $n!/n^n=\prod_{k=1}^n(k/n)$ sono al più $1/2$.

La gerarchia giustificata è

$$
\log n\ll n^\alpha\ll a^n\ll n!\ll n^n.
$$

Il primo confronto è registrato qui come anticipazione: la sua prova organica usa la funzione esponenziale o il logaritmo e sarà completata nei moduli sui limiti di funzione. Gli altri confronti sono già giustificati con strumenti sequenziali.

<!-- TODO FIGURA: grafico in scala logaritmica delle successioni log n, n^2, 2^n, n! e n^n per indici moderati, senza suggerire prova grafica; obiettivo didattico: confrontare ordini di crescita e collegarli alle dimostrazioni per rapporti e Stolz. -->

---

### **5. Limiti trigonometrici sequenziali**

Dalle disuguaglianze geometriche, per $0<x<\pi/2$,

$$
\cos x\le\frac{\sin x}{x}\le1.
$$

La convergenza del termine a sinistra si giustifica senza assumere la continuità del coseno. Dall'identità $1-\cos x=2\sin^2(x/2)$ e dalla stima geometrica $|\sin y|\leq|y|$ segue

$$
0\leq1-\cos x\leq\frac{x^2}{2}.
$$

Per $x=1/(n+1)$ il teorema dei carabinieri dà $\cos(1/(n+1))\to1$. Applicandolo quindi alla disuguaglianza precedente, si ottiene

$$
\frac{\sin(1/(n+1))}{1/(n+1)}\to1.
$$

Quindi

$$
(n+1)\sin\frac1{n+1}\to1,
\qquad
\sin\frac1{n+1}\to0.
$$

Invece $(\sin n)$ non converge. Se convergesse a $L$, dall'identità

$$
\sin(n+2)-\sin n=2\sin1\cos(n+1)
$$

seguirebbe $\cos n\to0$. Allora $\sin^2n+\cos^2n=1$ darebbe $L^2=1$, mentre

$$
\sin(n+1)=\sin n\cos1+\cos n\sin1
$$

darebbe $L=L\cos1$ e quindi $L=0$: contraddizione. Non occorre affermare la densità di $(\sin n)$ in $[-1,1]$.

Per i carabinieri,

$$
\frac{\sin n}{n+1}\to0.
$$

---

### **6. Valore assoluto, parte intera e frazionaria**

Se $a_n\to a$, allora $|a_n|\to|a|$. Per la parte intera non vale un teorema generale nei punti interi.

Se $a_n\to a\notin\mathbb Z$, allora

$$
\lfloor a_n\rfloor=\lfloor a\rfloor
$$

definitivamente. Se $a\in\mathbb Z$, può fallire: per $a_n=a+(-1)^n/(n+1)$ la parte intera oscilla tra $a-1$ e $a$.

La parte frazionaria

$$
\{x\}=x-\lfloor x\rfloor
$$

è compresa in $[0,1)$ e può oscillare anche se una successione diverge, come $\{n/2\}$.

---

### **7. Classificazione di esempi oscillanti**

- $(-1)^n$: oscillante limitata.
- $(-1)^nn$: oscillante illimitata, senza limite esteso.
- $n\sin(\pi n/2)$: assume $0$ e valori di modulo crescente, senza limite esteso.
- $\sin n$: limitata e non convergente.
- una successione definitivamente costante: convergente.

---

### **8. Errori frequenti**

1. Usare una gerarchia di crescita senza citarne la prova.
2. Razionalizzare senza controllare il dominio.
3. Affermare che $\sin n$ è densa senza strumenti aritmetici.
4. Passare la parte intera al limite in un punto intero.
5. Confondere limitata e convergente.
6. Usare il rapporto consecutivo vicino a $1$ come criterio universale.

---

### **9. Esercizi**

1. Calcola $\sqrt{4n^2+3n}-2n$.
2. Dimostra $\sqrt[n]{1/5}\to1$.
3. Prova $3^n/n!\to0$ con una stima geometrica esplicita.
4. Mostra $n!/n^n\to0$ dettagliando il numero dei fattori al più $1/2$.
5. Studia $n^4/1.01^n$.
6. Dimostra $\sin(1/n)\sim1/n$ adattando gli indici.
7. Classifica $\lfloor(-1)^n/(n+1)\rfloor$ e la sua parte frazionaria.
8. Determina limsup e liminf di $\sin(\pi n/2)+1/(n+1)$.
9. Spiega perché $a_{n+1}/a_n\to1$ vale sia per $a_n=n$ sia per $a_n=1/n$.

---

### **10. Riepilogo**

> ✅ Limiti notevoli e gerarchie sono risultati da dimostrare con Bernoulli, rapporti, Stolz, carabinieri o razionalizzazione; il grafico resta soltanto un supporto intuitivo.
