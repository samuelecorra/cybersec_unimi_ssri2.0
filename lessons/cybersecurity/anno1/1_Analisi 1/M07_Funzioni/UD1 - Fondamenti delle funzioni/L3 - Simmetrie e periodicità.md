# **M7 UD1 Lezione 3 - Simmetrie e periodicità**

### **1. Introduzione**

Simmetrie e periodicità permettono di semplificare lo studio di una funzione. Se una funzione è pari, dispari o periodica, non è necessario analizzare tutto il dominio da zero: una parte del grafico determina il resto.

> 📌 Le simmetrie riducono il lavoro nello studio del grafico.

---

### **2. Funzione pari**

Una funzione $f:D\to\mathbb{R}$ è **pari** se:

1. il dominio è simmetrico rispetto a $0$;
2. per ogni $x\in D$ vale:

$$
f(-x)=f(x)
$$

Il grafico di una funzione pari è simmetrico rispetto all’asse $y$.

Esempi:

$$
f(x)=x^2
$$

$$
f(x)=\cos x
$$

---

### **3. Funzione dispari**

Una funzione è **dispari** se:

1. il dominio è simmetrico rispetto a $0$;
2. per ogni $x\in D$ vale:

$$
f(-x)=-f(x)
$$

Il grafico di una funzione dispari è simmetrico rispetto all’origine.

Esempi:

$$
f(x)=x^3
$$

$$
f(x)=\sin x
$$

---

### **4. Attenzione al dominio**

Prima di verificare parità o disparità bisogna controllare il dominio.

Esempio:

$$
f(x)=\sqrt{x^2-1}
$$

ha dominio:

$$
(-\infty,-1]\cup[1,+\infty)
$$

che è simmetrico rispetto a $0$.

Inoltre:

$$
f(-x)=\sqrt{(-x)^2-1}=\sqrt{x^2-1}=f(x)
$$

quindi la funzione è pari.

Se il dominio non è simmetrico, la funzione non può essere pari né dispari.

---

### **5. Funzione periodica**

Una funzione $f:D\to\mathbb{R}$ è **periodica** se esiste $T>0$ tale che:

$$
x\in D\iff x+T\in D
$$

e

$$
f(x+T)=f(x)
$$

per ogni $x\in D$.

Il numero $T$ si chiama periodo. Il più piccolo periodo positivo, se esiste, si chiama **periodo fondamentale**.

Esempi:

$$
\sin x
$$

e:

$$
\cos x
$$

hanno periodo fondamentale $2\pi$.

La tangente ha periodo fondamentale:

$$
\pi
$$

La condizione sul dominio evita definizioni vacue. Per esempio, la funzione $f:\{0\}\to\mathbb{R}$ non diventa periodica solo perché, per quasi ogni $T$, non esistono coppie $x,x+T$ entrambe nel dominio.

---

### **6. Funzioni non periodiche**

Molte funzioni elementari non sono periodiche:

$$
x^2,\quad e^x,\quad \log x
$$

Per esempio, $e^x$ cresce senza ripetere i valori con una distanza fissa. Una funzione periodica invece ripete esattamente lo stesso comportamento.

---

### **7. Uso nello studio del grafico**

Se una funzione è pari, basta studiarla per:

$$
x\ge 0
$$

e riflettere il grafico rispetto all’asse $y$.

Se è dispari, basta studiarla per:

$$
x\ge 0
$$

e riflettere rispetto all’origine.

Se è periodica, basta studiarla su un intervallo lungo un periodo, ad esempio:

$$
[0,T]
$$

e ripetere il grafico.

<!-- TODO FIGURA:
Tre pannelli: parabola pari riflessa rispetto all'asse y, cubica dispari ruotata di 180 gradi attorno all'origine e seno ripetuto su tre periodi con freccia di lunghezza 2pi.
Obiettivo didattico: associare ogni identità al corretto movimento geometrico e distinguere simmetria da periodicità.
-->

---

### **8. Algebra della parità**

Su un dominio comune simmetrico rispetto a $0$:

- somma e differenza di funzioni pari sono pari;
- somma e differenza di funzioni dispari sono dispari;
- il prodotto di due funzioni con la stessa parità è pari;
- il prodotto di una pari e una dispari è dispari.

Per esempio, se $f$ è pari e $g$ dispari,

$$
(fg)(-x)=f(-x)g(-x)=f(x)(-g(x))=-(fg)(x).
$$

Una funzione può essere né pari né dispari, come $f(x)=x+1$. L'unica funzione contemporaneamente pari e dispari è la funzione nulla, perché

$$
f(x)=f(-x)=-f(x)
$$

implica $f(x)=0$.

### **9. Decomposizione pari-dispari**

Se $D$ è simmetrico e $f:D\to\mathbb{R}$, definiamo

$$
f_p(x)=\frac{f(x)+f(-x)}{2},
$$

$$
f_d(x)=\frac{f(x)-f(-x)}{2}.
$$

Allora $f_p$ è pari, $f_d$ è dispari e

$$
f=f_p+f_d.
$$

La decomposizione è unica. Se anche $f=p+d$ con $p$ pari e $d$ dispari, valutando in $x$ e $-x$ si ottiene

$$
f(x)+f(-x)=2p(x),
$$

$$
f(x)-f(-x)=2d(x),
$$

quindi necessariamente $p=f_p$ e $d=f_d$. $\square$

### **10. Proprietà dei periodi**

Se $T$ è un periodo, anche ogni multiplo intero positivo $nT$ è un periodo. Se $T_1$ e $T_2$ sono periodi e $T_1/T_2\in\mathbb{Q}$, esiste un multiplo positivo comune che è periodo di entrambe; di conseguenza la somma e il prodotto sono periodici.

Senza commensurabilità la somma può non essere periodica. Un esempio è

$$
f(x)=\sin x+\sin(\sqrt2x).
$$

I periodi fondamentali dei due addendi sono $2\pi$ e $\sqrt2\pi$, il cui rapporto è irrazionale; una dimostrazione completa della non periodicità può essere ottenuta usando l'unicità delle frequenze trigonometriche e non è necessaria per l'uso operativo del controesempio.

Non ogni funzione periodica possiede un periodo fondamentale. Ogni $T>0$ è periodo di una funzione costante, quindi non esiste il più piccolo periodo positivo. Anche la funzione indicatrice di $\mathbb{Q}$ ha ogni numero razionale positivo come periodo e nessun periodo fondamentale.

> ⚠️ La somma di due funzioni periodiche è certamente periodica solo quando possiedono periodi commensurabili.

### **11. Esercizi**

1. Classifica per parità $x^4+x^2$, $x^3-x$, $x^3+x^2$ e $|x-1|$.
2. Calcola parte pari e parte dispari di $f(x)=e^x$.
3. Dimostra le regole di parità per quozienti, specificando il dominio.
4. Trova un dominio non simmetrico sul quale la formula $x^2$ non definisce una funzione pari.
5. Determina un periodo comune di $\sin(2x)$ e $\cos(3x)$ e il periodo fondamentale della loro somma.
6. Spiega perché la funzione costante è periodica ma non ha periodo fondamentale.
7. Determina tutti i valori di $a>0$ per cui $\sin x+\cos(ax)$ ha certamente un periodo comune ricavabile dai periodi elementari.

---

### **12. Riepilogo**

> ✅ Parità, disparità e periodicità sono proprietà globali del grafico.

Punti essenziali:

- pari: $f(-x)=f(x)$, simmetria rispetto all’asse $y$;
- dispari: $f(-x)=-f(x)$, simmetria rispetto all’origine;
- il dominio deve essere simmetrico per parità o disparità;
- periodica: $f(x+T)=f(x)$;
- il dominio di una funzione periodica deve essere invariante per traslazione di un periodo;
- ogni funzione su un dominio simmetrico si decompone in modo unico in parte pari e dispari;
- periodi incommensurabili possono distruggere la periodicità della somma;
- le simmetrie riducono l’intervallo da studiare.
