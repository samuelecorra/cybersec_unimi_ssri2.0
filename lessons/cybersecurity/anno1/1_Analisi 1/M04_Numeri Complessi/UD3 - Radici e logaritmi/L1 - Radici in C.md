# **M4 UD3 Lezione 1 - Radici in C**

### **1. Il problema delle radici n-esime**

Fissato $n\in\mathbb N$ con $n\ge2$, una **radice $n$-esima** di $z\in\mathbb C$ è un numero $w\in\mathbb C$ tale che

$$
w^n=z.
$$

Se $z=0$, l'unica soluzione è $w=0$: infatti $w^n=0$ implica $w=0$ perché $\mathbb C$ è un campo.

Supponiamo ora $z\ne0$ e scriviamo

$$
z=\rho e^{i\theta},\qquad \rho>0.
$$

---

### **2. Formula delle radici**

Le radici $n$-esime di $z$ sono

$$
w_k=\sqrt[n]{\rho}\,
e^{i(\theta+2k\pi)/n},
\qquad k=0,1,\ldots,n-1.
$$

In forma trigonometrica,

$$
w_k=\sqrt[n]{\rho}
\left[
\cos\left(\frac{\theta+2k\pi}{n}\right)
+i\sin\left(\frac{\theta+2k\pi}{n}\right)
\right].
$$

> 📌 Un complesso non nullo ha esattamente $n$ radici $n$-esime distinte; $0$ ne ha una sola, $0$, con molteplicità $n$ come radice del polinomio $w^n$.

---

### **3. Dimostrazione: esistenza, distinzione e completezza**

#### **3.1 Ogni candidato è una radice**

Per De Moivre,

$$
w_k^n
=
\rho e^{i(\theta+2k\pi)}
=
\rho e^{i\theta}
=z.
$$

#### **3.2 I candidati sono distinti**

Supponiamo $w_j=w_k$ con $0\le j,k\le n-1$. I moduli coincidono già; l'uguaglianza delle fasi richiede

$$
\frac{\theta+2j\pi}{n}-\frac{\theta+2k\pi}{n}
=2m\pi
$$

per qualche $m\in\mathbb Z$. Quindi

$$
j-k=mn.
$$

Ma $|j-k|<n$, dunque $m=0$ e $j=k$.

#### **3.3 Non esistono altre radici**

Sia $w=re^{i\varphi}$ una soluzione. Dal confronto dei moduli,

$$
r^n=\rho,
$$

e poiché $r\ge0$,

$$
r=\sqrt[n]{\rho}.
$$

Dal confronto degli argomenti,

$$
n\varphi=\theta+2h\pi
$$

per qualche $h\in\mathbb Z$, dunque

$$
\varphi=\frac{\theta+2h\pi}{n}.
$$

Dividendo $h$ per $n$, $h=qn+k$ con $0\le k<n$; allora l'angolo differisce da quello di $w_k$ per $2q\pi$. Quindi $w=w_k$. La lista è completa. $\square$

---

### **4. Geometria delle radici**

Tutte le radici hanno modulo $\sqrt[n]{\rho}$ e gli argomenti consecutivi differiscono di

$$
\frac{2\pi}{n}.
$$

Sono quindi i vertici di un poligono regolare di $n$ lati centrato nell'origine.

<!-- TODO FIGURA: radici n-esime di un complesso z non nullo su circonferenza di raggio rho^(1/n), con primo angolo theta/n e separazione 2pi/n; obiettivo didattico: visualizzare esistenza, equidistribuzione e numero esatto delle radici. -->

La somma delle radici è $0$ per $n\ge2$. Geometricamente è la somma vettoriale dei vertici di un poligono regolare centrato nell'origine; una dimostrazione algebrica segue dalla teoria delle radici dell'unità.

---

### **5. Esempi completi**

#### **5.1 Radici quadrate di un numero non reale**

Risolviamo

$$
w^2=3+4i.
$$

Il secondo membro ha modulo $5$. Una via polare usa $\theta=\operatorname{Arg}(3+4i)$:

$$
w_k=\sqrt5e^{i(\theta+2k\pi)/2},\qquad k=0,1.
$$

Poiché $(2+i)^2=3+4i$, le due radici sono

$$
2+i,\qquad -2-i.
$$

#### **5.2 Radici cubiche di -8**

Scriviamo

$$
-8=8e^{i\pi}.
$$

Le radici sono

$$
w_k=2e^{i(\pi+2k\pi)/3},\qquad k=0,1,2.
$$

Quindi

$$
w_0=1+\sqrt3i,
$$

$$
w_1=-2,
$$

$$
w_2=1-\sqrt3i.
$$

#### **5.3 La radice di un reale positivo**

L'equazione $w^4=16$ non ha soltanto le radici reali $\pm2$. In $\mathbb C$ le quattro soluzioni sono

$$
2,\quad 2i,\quad -2,\quad -2i.
$$

---

### **6. Radici dell'unità**

Le soluzioni di

$$
w^n=1
$$

sono

$$
\omega_k=e^{2k\pi i/n},\qquad k=0,1,\ldots,n-1.
$$

Ponendo

$$
\omega=e^{2\pi i/n},
$$

si possono scrivere come

$$
1,\omega,\omega^2,\ldots,\omega^{n-1}.
$$

La radice $\omega$ è **primitiva** perché le sue potenze generano tutte le radici. Più in generale, $\omega_k$ è primitiva se e solo se $\gcd(k,n)=1$.

L'**ordine** di una radice dell'unità $\zeta$ è il minimo intero positivo $m$ tale che $\zeta^m=1$. Per

$$
\omega_k=e^{2k\pi i/n}
$$

si ha

$$
\operatorname{ord}(\omega_k)=\frac{n}{\gcd(k,n)}.
$$

Di conseguenza $\omega_k$ è primitiva esattamente quando il suo ordine è $n$.

Le radici dell'unità sono chiuse rispetto a prodotto e inverso:

$$
\omega_j\omega_k=\omega_{j+k\bmod n},
$$

$$
\omega_k^{-1}=\omega_{n-k\bmod n}.
$$

Poiché il coniugato di un numero di modulo $1$ coincide con l'inverso,

$$
\overline{\omega_k}=\omega_k^{-1}=\omega_{n-k\bmod n}.
$$

Formano quindi un gruppo ciclico di ordine $n$.

<!-- TODO FIGURA: radici ottave dell'unità etichettate 1, omega, ..., omega^7 sulla circonferenza unitaria, con prodotti letti come somma degli indici modulo 8; obiettivo didattico: collegare poligono regolare e struttura ciclica. -->

---

### **7. Somma e fattorizzazione delle radici dell'unità**

Se $\omega\ne1$ e $\omega^n=1$, la somma geometrica dà

$$
1+\omega+\omega^2+\cdots+\omega^{n-1}
=
\frac{\omega^n-1}{\omega-1}=0.
$$

Quindi, per $n\ge2$,

$$
\sum_{k=0}^{n-1}\omega_k=0.
$$

Inoltre,

$$
z^n-1=\prod_{k=0}^{n-1}(z-\omega_k).
$$

Separando la radice $1$,

$$
z^n-1=(z-1)(z^{n-1}+z^{n-2}+\cdots+z+1).
$$

Il prodotto di tutte le radici è

$$
\prod_{k=0}^{n-1}\omega_k=(-1)^{n-1}.
$$

Questo segue confrontando il termine noto nella fattorizzazione di $z^n-1$.

---

### **8. Tutte le radici da una sola radice**

Se $w_0^n=z\ne0$, allora tutte le radici di $z$ sono

$$
w_0\omega_k,
\qquad k=0,\ldots,n-1,
$$

dove $\omega_k$ percorre le radici $n$-esime dell'unità. Infatti

$$
(w_0\omega_k)^n=w_0^n\omega_k^n=z.
$$

La completezza segue perché sono $n$ valori distinti.

Questa formulazione separa la scala e l'orientamento iniziale, contenuti in $w_0$, dalla simmetria regolare delle radici dell'unità.

---

### **9. Radice principale e notazione**

In campo reale $\sqrt{x}$ per $x\ge0$ indica la radice non negativa. In $\mathbb C$ non esiste un ordine compatibile che permetta la stessa scelta.

Si può definire una **radice principale** usando l'argomento principale:

$$
\sqrt[n]{z}_{\mathrm{pr}}
=
|z|^{1/n}e^{i\operatorname{Arg}(z)/n},
\qquad z\ne0.
$$

È una convenzione di ramo, non l'insieme di tutte le radici. Perciò

$$
\sqrt{z^2}_{\mathrm{pr}}=z
$$

non vale per ogni $z\in\mathbb C$. Per esempio, con $z=-1$,

$$
\sqrt{(-1)^2}_{\mathrm{pr}}=\sqrt1=1\ne-1.
$$

---

### **10. Errori frequenti**

1. Restituire una sola radice dividendo un solo argomento per $n$.
2. Usare $k=0,\ldots,n$ e contare due volte la prima radice.
3. Dimenticare che $z=0$ è un caso separato.
4. Confondere $\sqrt[n]{\rho}$, reale positivo, con le radici complesse di $z$.
5. Credere che tutte le radici dell'unità siano primitive.
6. Applicare automaticamente $\sqrt{z^2}=z$.

---

### **11. Esercizi**

1. Trova e rappresenta tutte le radici quarte di $-16$.
2. Risolvi $w^3=8i$ e verifica ogni soluzione con De Moivre.
3. Dimostra che le radici $n$-esime di $z\ne0$ hanno somma nulla.
4. Determina le radici primitive seste e ottave dell'unità.
5. Fattorizza $z^6-1$ completamente in $\mathbb C$.
6. Calcola somma e prodotto delle radici quinte di $32i$ senza convertirle tutte in forma algebrica.
7. Dimostra che le radici di $z$ sono $w_0$ moltiplicato per le radici dell'unità.
8. Trova un controesempio complesso a $\sqrt{zw}_{\mathrm{pr}}=\sqrt z_{\mathrm{pr}}\sqrt w_{\mathrm{pr}}$.
9. Spiega perché $w^n=0$ ha una sola soluzione distinta ma una radice di molteplicità $n$.

---

### **12. Riepilogo**

> ✅ La formula delle radici è stata verificata, i suoi valori sono distinti e ogni possibile soluzione appartiene alla lista; le radici dell'unità organizzano tutte le soluzioni in un poligono regolare e in un gruppo ciclico.

$$
w_k=|z|^{1/n}e^{i(\theta+2k\pi)/n},
\qquad k=0,\ldots,n-1.
$$

La notazione di radice principale seleziona un ramo e non deve cancellare la natura plurivoca dell'equazione $w^n=z$.
