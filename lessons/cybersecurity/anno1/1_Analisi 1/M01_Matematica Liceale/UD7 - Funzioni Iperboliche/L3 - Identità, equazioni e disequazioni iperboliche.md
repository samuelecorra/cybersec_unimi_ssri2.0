# **M1 UD7 Lezione 3 - Identità, equazioni e disequazioni iperboliche**

### **1. Dalle definizioni alle formule**

Le formule iperboliche non vanno ottenute sostituendo simboli nelle formule trigonometriche. Si ricavano dalle definizioni

$$
\sinh x=\frac{e^x-e^{-x}}{2},\qquad
\cosh x=\frac{e^x+e^{-x}}{2}
$$

e dalla proprietà $e^{x+y}=e^xe^y$. Questo metodo determina automaticamente i segni corretti.

### **2. Formula di addizione del seno iperbolico**

Vogliamo dimostrare

$$
\sinh(x+y)=\sinh x\cosh y+\cosh x\sinh y.
$$

Sviluppiamo il secondo membro:

$$
\sinh x\cosh y+\cosh x\sinh y
=\frac{(e^x-e^{-x})(e^y+e^{-y})+(e^x+e^{-x})(e^y-e^{-y})}{4}.
$$

Moltiplicando e sommando, i termini misti si cancellano:

$$
\frac{2e^{x+y}-2e^{-(x+y)}}{4}
=\frac{e^{x+y}-e^{-(x+y)}}{2}
=\sinh(x+y).
$$

### **3. Formula di addizione del coseno iperbolico**

Analogamente,

$$
\cosh x\cosh y+\sinh x\sinh y
=\frac{(e^x+e^{-x})(e^y+e^{-y})+(e^x-e^{-x})(e^y-e^{-y})}{4}.
$$

I termini misti si cancellano e resta

$$
\frac{2e^{x+y}+2e^{-(x+y)}}{4}
=\cosh(x+y).
$$

Quindi

$$
\cosh(x+y)=\cosh x\cosh y+\sinh x\sinh y.
$$

> ⚠️ Nella formula di $\cosh(x+y)$ compare un segno positivo, mentre in $\cos(x+y)$ il termine $\sin x\sin y$ ha segno negativo.

### **4. Formule di sottrazione**

Sostituendo $y$ con $-y$ e usando la parità di $\cosh$ e la disparità di $\sinh$,

$$
\sinh(x-y)=\sinh x\cosh y-\cosh x\sinh y,
$$

$$
\cosh(x-y)=\cosh x\cosh y-\sinh x\sinh y.
$$

Da queste formule si ricavano anche le trasformazioni

$$
\cosh u-\cosh v
=2\sinh\frac{u+v}{2}\sinh\frac{u-v}{2},
$$

$$
\sinh u-\sinh v
=2\cosh\frac{u+v}{2}\sinh\frac{u-v}{2}.
$$

La prima giustifica, per $u>v\geq0$, la monotonia crescente di $\cosh$ sul semiasse positivo.

### **5. Formule della tangente iperbolica**

Dividendo la formula di $\sinh(x+y)$ per quella di $\cosh(x+y)$ e poi numeratore e denominatore per $\cosh x\cosh y>0$, si ottiene

$$
\tanh(x+y)
=\frac{\tanh x+\tanh y}{1+\tanh x\tanh y}.
$$

Analogamente,

$$
\tanh(x-y)
=\frac{\tanh x-\tanh y}{1-\tanh x\tanh y}.
$$

I denominatori non si annullano per argomenti reali, perché $|\tanh x|<1$ e $|\tanh y|<1$.

### **6. Formule di duplicazione**

Ponendo $y=x$ nelle formule di addizione,

$$
\sinh(2x)=2\sinh x\cosh x,
$$

$$
\cosh(2x)=\cosh^2x+\sinh^2x.
$$

Usando $\cosh^2x-\sinh^2x=1$ si ottengono le forme equivalenti

$$
\cosh(2x)=2\cosh^2x-1,
$$

$$
\cosh(2x)=1+2\sinh^2x.
$$

Per la tangente iperbolica,

$$
\tanh(2x)=\frac{2\tanh x}{1+\tanh^2x}.
$$

### **7. Formule di bisezione**

Sostituendo $x$ con $x/2$ nelle formule del coseno doppio,

$$
\cosh^2\frac{x}{2}=\frac{\cosh x+1}{2},
$$

$$
\sinh^2\frac{x}{2}=\frac{\cosh x-1}{2}.
$$

Poiché $\cosh(x/2)>0$,

$$
\cosh\frac{x}{2}=\sqrt{\frac{\cosh x+1}{2}}.
$$

Il segno del seno iperbolico coincide invece con quello dell'argomento:

$$
\sinh\frac{x}{2}
=\begin{cases}
\sqrt{\frac{\cosh x-1}{2}} & x\geq0 \\
-\sqrt{\frac{\cosh x-1}{2}} & x<0.
\end{cases}
$$

Inoltre,

$$
\tanh\frac{x}{2}=\frac{\sinh x}{\cosh x+1}
$$

per ogni $x\in\mathbb{R}$, mentre

$$
\tanh\frac{x}{2}=\frac{\cosh x-1}{\sinh x}
$$

richiede $x\neq0$.

### **8. Verifica simbolica di un'identità**

Dimostriamo, per $x\neq0$,

$$
\frac{\cosh(2x)-1}{\sinh(2x)}=\tanh x.
$$

Usando duplicazione e identità fondamentale,

$$
\frac{\cosh(2x)-1}{\sinh(2x)}
=\frac{2\sinh^2x}{2\sinh x\cosh x}
=\frac{\sinh x}{\cosh x}
=\tanh x.
$$

La condizione $x\neq0$ giustifica la semplificazione di $\sinh x$.

### **9. Equazioni elementari**

#### **9.1 Equazione $\sinh x=a$**

Poiché $\sinh:\mathbb{R}\to\mathbb{R}$ è strettamente crescente e suriettiva, per ogni $a\in\mathbb{R}$ esiste una sola soluzione:

$$
x=\operatorname{arsinh}a.
$$

#### **9.2 Equazione $\cosh x=a$**

Poiché $\cosh x\geq1$:

- se $a<1$, non esistono soluzioni reali;
- se $a=1$, l'unica soluzione è $x=0$;
- se $a>1$, le soluzioni sono

$$
x=\pm\operatorname{arcosh}a.
$$

#### **9.3 Equazione $\tanh x=a$**

Poiché l'immagine di $\tanh$ è $(-1,1)$, esiste una sola soluzione se $|a|<1$:

$$
x=\operatorname{artanh}a.
$$

Per $|a|\geq1$ non esistono soluzioni reali.

### **10. Equazioni riconducibili**

Risolviamo

$$
2\sinh^2x-3\sinh x-2=0.
$$

Ponendo $t=\sinh x$, si ottiene

$$
2t^2-3t-2=(2t+1)(t-2)=0.
$$

Poiché $\sinh$ assume ogni valore reale,

$$
x=\operatorname{arsinh}2
$$

oppure

$$
x=\operatorname{arsinh}\left(-\frac12\right).
$$

Per un'equazione come $\cosh(2x)=3$, la parità produce invece due soluzioni:

$$
2x=\pm\operatorname{arcosh}3,
$$

$$
x=\pm\frac12\operatorname{arcosh}3.
$$

### **11. Disequazioni iperboliche**

La stretta monotonia di $\sinh$ dà, per ogni $a\in\mathbb{R}$,

$$
\sinh x>a
\iff
x>\operatorname{arsinh}a.
$$

Per la tangente iperbolica,

$$
\tanh x<a
$$

ha:

- nessuna soluzione se $a\leq-1$;
- soluzione $x<\operatorname{artanh}a$ se $-1<a<1$;
- soluzione $\mathbb{R}$ se $a\geq1$.

Per il coseno iperbolico, la parità è decisiva. La disequazione

$$
\cosh x\leq a
$$

ha:

- nessuna soluzione se $a<1$;
- soluzione $\{0\}$ se $a=1$;
- soluzione

$$
-\operatorname{arcosh}a\leq x\leq\operatorname{arcosh}a
$$

se $a>1$.

La disequazione stretta richiesta nella forma opposta,

$$
\cosh x>a,
$$

ha invece:

- soluzione $\mathbb{R}$ se $a<1$;
- soluzione $\mathbb{R}\setminus\{0\}$ se $a=1$;
- soluzione

$$
x<-\operatorname{arcosh}a
\quad\text{oppure}\quad
x>\operatorname{arcosh}a
$$

se $a>1$.

### **12. Esercizio completo in forma esponenziale**

Risolviamo

$$
\tanh x=\frac35.
$$

Il dominio è $\mathbb{R}$. Scriviamo la funzione mediante l'esponenziale:

$$
\frac{e^{2x}-1}{e^{2x}+1}=\frac35.
$$

Il denominatore è positivo, quindi il prodotto incrociato è equivalente:

$$
5(e^{2x}-1)=3(e^{2x}+1).
$$

Semplificando,

$$
2e^{2x}=8,\qquad e^{2x}=4.
$$

Applicando il logaritmo,

$$
2x=\ln4,\qquad x=\ln2.
$$

Poiché $3/5\in(-1,1)$, la soluzione soddisfa la condizione d'immagine e può essere riscritta come

$$
x=\operatorname{artanh}\frac35.
$$

Numericamente,

$$
\ln2\approx0{,}6931,
$$

e la sostituzione fornisce $\tanh(\ln2)=3/5$.

### **13. Errori frequenti**

- usare un segno meno nella formula di $\cosh(x+y)$;
- scrivere $\cosh(2x)=\cosh^2x-\sinh^2x$, che varrebbe sempre $1$;
- dimenticare il segno di $\sinh(x/2)$ nelle formule di bisezione;
- cercare soluzioni di $\cosh x=a$ con $a<1$;
- fornire una sola soluzione quando $\cosh x=a>1$;
- assegnare a $\tanh x$ valori uguali o esterni a $\pm1$;
- dividere per $\sinh x$ senza separare il caso $x=0$.

### **14. Riepilogo**

> ✅ Le formule iperboliche discendono dall'esponenziale. Equazioni e disequazioni si risolvono usando immagini, monotonia e, per $\cosh$, la simmetria pari.
