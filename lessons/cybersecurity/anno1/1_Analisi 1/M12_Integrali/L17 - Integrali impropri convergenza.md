# **M11 Lezione 17 - Integrali impropri: convergenza**

### **1. Perché servono i criteri di convergenza**

Negli integrali impropri non sempre è possibile, o conveniente, calcolare esplicitamente una primitiva. In molti esercizi l'obiettivo non è trovare il valore dell'integrale, ma stabilire se l'integrale **converge** oppure **diverge**.

In questi casi si usano criteri di confronto: si confronta l'integranda con una funzione più semplice, di cui conosciamo già il comportamento.

> 📌 Per studiare la convergenza non serve sempre calcolare l'integrale. Spesso basta confrontarlo con un integrale improprio noto.

---
### **2. Criterio del confronto su $[a,+\infty)$**

Siano

$$
f,g:[a,+\infty)\to\mathbb{R}
$$

due funzioni continue tali che

$$
0\le f(x)\le g(x)
$$

per ogni $x\in[a,+\infty)$.

Allora valgono due implicazioni fondamentali:

1. se

$$
\int_a^{+\infty}g(x)\,dx
$$

converge, allora converge anche

$$
\int_a^{+\infty}f(x)\,dx;
$$

2. se

$$
\int_a^{+\infty}f(x)\,dx
$$

diverge a $+\infty$, allora diverge a $+\infty$ anche

$$
\int_a^{+\infty}g(x)\,dx.
$$

L'idea è geometrica: se l'area sotto la funzione più grande è finita, allora anche l'area sotto la funzione più piccola è finita; se invece l'area sotto la funzione più piccola è infinita, allora lo è anche quella sotto la funzione più grande.

### **3. Esempio 1: confronto tra $e^{-x^2}$ ed $e^{-x}$**

Stabiliamo se converge

$$
\int_1^{+\infty}e^{-x^2}\,dx.
$$

Per $x\ge 1$ si ha

$$
x^2\ge x.
$$

Moltiplicando per $-1$ si inverte il verso:

$$
-x^2\le -x.
$$

Poiché l'esponenziale è crescente:

$$
e^{-x^2}\le e^{-x}.
$$

Inoltre entrambe le funzioni sono non negative, quindi

$$
0\le e^{-x^2}\le e^{-x}
$$

per ogni $x\ge 1$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Calcoliamo l'integrale di confronto:

$$
\int_1^{+\infty}e^{-x}\,dx
=
\lim_{M\to+\infty}\int_1^M e^{-x}\,dx.
$$

Una primitiva è

$$
-e^{-x}.
$$

Quindi:

$$
\lim_{M\to+\infty}\left[-e^{-x}\right]_1^M
=
\lim_{M\to+\infty}\left(-e^{-M}+e^{-1}\right)
=\frac{1}{e}.
$$

L'integrale di $e^{-x}$ converge. Per il criterio del confronto converge anche

$$
\int_1^{+\infty}e^{-x^2}\,dx.
$$

#### **3.1 Risultato**

$$
\int_1^{+\infty}e^{-x^2}\,dx
\quad\text{converge.}
$$

---
### **4. Criterio del confronto asintotico su $[a,+\infty)$**

Siano

$$
f,g:[a,+\infty)\to\mathbb{R}
$$

due funzioni continue e positive. Se

$$
f(x)\sim g(x)
\qquad\text{per }x\to+\infty,
$$

cioè

$$
\lim_{x\to+\infty}\frac{f(x)}{g(x)}=1,
$$

allora i due integrali impropri hanno lo stesso comportamento:

$$
\int_a^{+\infty}f(x)\,dx
$$

converge o diverge se e solo se converge o diverge

$$
\int_a^{+\infty}g(x)\,dx.
$$

Questo criterio è molto usato perché negli integrali impropri conta il comportamento della funzione vicino al punto problematico. Nel caso di intervallo illimitato, conta il comportamento per $x\to+\infty$.

### **5. Integrali impropri utili da ricordare all'infinito**

Per $a>0$:

$$
\int_a^{+\infty}\frac{1}{x^\alpha}\,dx
$$

converge se

$$
\alpha>1,
$$

e diverge a $+\infty$ se

$$
\alpha\le 1.
$$

Per $a>1$:

$$
\int_a^{+\infty}\frac{1}{x^\alpha(\ln x)^\beta}\,dx
$$

converge se

$$
\alpha>1
$$

oppure se

$$
\alpha=1
\qquad\text{e}\qquad
\beta>1.
$$

Diverge se

$$
\alpha<1
$$

oppure se

$$
\alpha=1
\qquad\text{e}\qquad
\beta\le 1.
$$

> ⚠️ I modelli con logaritmo richiedono $a>1$, così $\ln x$ è positivo sull'intervallo e non si introduce un'altra singolarità.

---
### **6. Esempio 2: funzione razionale all'infinito**

Stabiliamo se converge

$$
\int_1^{+\infty}\frac{x+5}{x^3+x^2+1}\,dx.
$$

Per $x\to+\infty$, il numeratore si comporta come $x$ e il denominatore come $x^3$. Quindi:

$$
\frac{x+5}{x^3+x^2+1}\sim \frac{x}{x^3}=\frac{1}{x^2}.
$$

L'integrale modello

$$
\int_1^{+\infty}\frac{1}{x^2}\,dx
$$

converge, perché è del tipo

$$
\int_1^{+\infty}\frac{1}{x^\alpha}\,dx
$$

con

$$
\alpha=2>1.
$$

Per il criterio del confronto asintotico, anche

$$
\int_1^{+\infty}\frac{x+5}{x^3+x^2+1}\,dx
$$

converge.

#### **6.1 Risultato**

$$
\int_1^{+\infty}\frac{x+5}{x^3+x^2+1}\,dx
\quad\text{converge.}
$$

### **7. Note sulle funzioni positive**

Se $f(x)\ge 0$ definitivamente, cioè da un certo punto in poi, allora

$$
\int_a^{+\infty}f(x)\,dx
$$

può solo convergere oppure divergere a $+\infty$. Non può divergere a $-\infty$, perché le aree orientate sono tutte non negative.

Per applicare il criterio del confronto e il confronto asintotico non serve che $f$ e $g$ siano positive fin dall'estremo iniziale $a$: basta che lo siano **definitivamente**. In altre parole, è sufficiente che esista $c>a$ tale che

$$
f(x)\ge 0,
\qquad
g(x)\ge 0
$$

per ogni $x\ge c$.

La parte dell'integrale su $[a,c]$ è propria e non modifica la convergenza all'infinito.

---
### **8. Criteri vicino a un estremo finito**

I criteri valgono anche per integrali impropri in cui la funzione esplode vicino a un estremo finito.

Siano

$$
f,g:(a,b]\to\mathbb{R}
$$

continue, con

$$
\lim_{x\to a^+}f(x)=+\infty,
\qquad
\lim_{x\to a^+}g(x)=+\infty.
$$

#### **8.1 Criterio del confronto**

Se

$$
0\le f(x)\le g(x)
$$

in $(a,b]$, allora:

1. se

$$
\int_a^b g(x)\,dx
$$

converge, allora converge anche

$$
\int_a^b f(x)\,dx;
$$

2. se

$$
\int_a^b f(x)\,dx
$$

diverge a $+\infty$, allora diverge a $+\infty$ anche

$$
\int_a^b g(x)\,dx.
$$

#### **8.2 Criterio del confronto asintotico**

Se $f$ e $g$ sono positive e

$$
f(x)\sim g(x)
\qquad\text{per }x\to a^+,
$$

allora:

$$
\int_a^b f(x)\,dx
$$

converge o diverge a $+\infty$ se e solo se converge o diverge

$$
\int_a^b g(x)\,dx.
$$

### **9. Integrali impropri utili vicino a un estremo**

Per un estremo problematico $a$, il modello fondamentale è:

$$
\int_a^b\frac{1}{(x-a)^\alpha}\,dx.
$$

Questo integrale converge se

$$
\alpha<1,
$$

e diverge a $+\infty$ se

$$
\alpha\ge 1.
$$

La soglia è opposta rispetto al caso all'infinito: vicino a un punto finito, una singolarità come $\frac{1}{\sqrt{x-a}}$ è integrabile, mentre $\frac{1}{x-a}$ non lo è.

---
### **10. Esempio 3: singolarità in $0$**

Stabiliamo se converge

$$
\int_0^2\frac{1}{x^4+\sqrt{x}}\,dx.
$$

L'intervallo è limitato, ma la funzione ha un problema in $0$, perché il denominatore tende a $0$:

$$
x^4+\sqrt{x}\to 0^+
\qquad\text{per }x\to 0^+.
$$

Studiamo il comportamento asintotico dell'integranda per $x\to 0^+$. Nel denominatore confrontiamo i due termini:

$$
x^4
\qquad\text{e}\qquad
\sqrt{x}=x^{1/2}.
$$

Per $x\to 0^+$, il termine dominante nel denominatore è quello che tende a $0$ più lentamente, cioè

$$
x^{1/2}.
$$

Quindi:

$$
x^4+\sqrt{x}\sim \sqrt{x}.
$$

Di conseguenza:

$$
\frac{1}{x^4+\sqrt{x}}
\sim
\frac{1}{\sqrt{x}}
=
\frac{1}{x^{1/2}}
$$

per $x\to 0^+$.

Ora confrontiamo con il modello:

$$
\int_0^2\frac{1}{x^{1/2}}\,dx.
$$

Questo integrale converge perché è del tipo

$$
\int_0^2\frac{1}{x^\alpha}\,dx
$$

con

$$
\alpha=\frac{1}{2}<1.
$$

Per il criterio del confronto asintotico converge anche

$$
\int_0^2\frac{1}{x^4+\sqrt{x}}\,dx.
$$

#### **10.1 Risultato**

$$
\int_0^2\frac{1}{x^4+\sqrt{x}}\,dx
\quad\text{converge.}
$$

### **11. Riepilogo**

Per studiare la convergenza degli integrali impropri:

- all'infinito si confronta l'integranda con modelli come $\frac{1}{x^\alpha}$ o $\frac{1}{x^\alpha(\ln x)^\beta}$;
- vicino a un estremo finito si confronta con $\frac{1}{(x-a)^\alpha}$;
- il confronto diretto funziona con disuguaglianze $0\le f\le g$;
- il confronto asintotico funziona quando le funzioni positive hanno lo stesso comportamento nel punto problematico.

La scelta del modello corretto dipende sempre dal punto in cui nasce l'improprietà: $+\infty$, $-\infty$ oppure un estremo finito.
