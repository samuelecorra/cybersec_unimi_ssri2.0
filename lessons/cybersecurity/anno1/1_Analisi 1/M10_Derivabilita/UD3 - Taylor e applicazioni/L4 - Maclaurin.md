# **M10 UD3 Lezione 4 - Maclaurin**

### **1. Taylor centrato in zero**

La formula di Maclaurin è la formula di Taylor centrata in

$$
x_0=0.
$$

Il polinomio di Maclaurin di ordine $n$ è

$$
T_n(x)=\sum_{k=0}^{n}\frac{f^{(k)}(0)}{k!}x^k.
$$

È la forma più usata nei limiti per $x\to 0$ e nelle approssimazioni locali vicino all'origine.

### **2. Sviluppi principali**

Gli sviluppi fondamentali sono:

$$
e^x=1+x+\frac{x^2}{2!}+\frac{x^3}{3!}+\cdots+\frac{x^n}{n!}+o(x^n),
$$

$$
\sin x=x-\frac{x^3}{3!}+\frac{x^5}{5!}-\cdots+(-1)^m\frac{x^{2m+1}}{(2m+1)!}+o(x^{2m+1}),
$$

$$
\cos x=1-\frac{x^2}{2!}+\frac{x^4}{4!}-\cdots+(-1)^m\frac{x^{2m}}{(2m)!}+o(x^{2m}),
$$

$$
\ln(1+x)=x-\frac{x^2}{2}+\frac{x^3}{3}-\cdots+(-1)^{n+1}\frac{x^n}{n}+o(x^n),
$$

$$
(1+x)^\alpha=1+\alpha x+\frac{\alpha(\alpha-1)}{2}x^2+\cdots+\binom{\alpha}{n}x^n+o(x^n),
\qquad
\binom{\alpha}{n}=\frac{\alpha(\alpha-1)\cdots(\alpha-n+1)}{n!}
$$

(il binomiale generalizzato: per $\alpha\in\mathbb{N}$ la serie si tronca e ridà il binomio di Newton di M01). Casi particolari usatissimi: $\frac{1}{1+x}=1-x+x^2-x^3+o(x^3)$ (cioè $\alpha=-1$: la geometrica di M05 in versione locale) e $\sqrt{1+x}=1+\frac x2-\frac{x^2}{8}+o(x^2)$.

**Sviluppi complementari** (tutti ricavabili dai precedenti; il grado dispari/pari segue dalla simmetria):

$$
\tan x = x+\frac{x^3}{3}+o(x^4),
\qquad
\arctan x = x-\frac{x^3}{3}+o(x^4),
\qquad
\arcsin x = x+\frac{x^3}{6}+o(x^4)
$$

$$
\sinh x = x+\frac{x^3}{3!}+\frac{x^5}{5!}+o(x^6),
\qquad
\cosh x = 1+\frac{x^2}{2!}+\frac{x^4}{4!}+o(x^5),
\qquad
\tanh x = x-\frac{x^3}{3}+o(x^4)
$$

Gli iperbolici sono i circolari **senza segni alterni** (coerente con $(\sinh)'=\cosh$ senza meno, M09/UD3/L2: la parentela via $e^{x}$ contro $e^{ix}$ diventa visibile negli sviluppi). Per $\tan$ e $\arctan$ nota i segni opposti del termine cubico: $\tan$ anticipa, $\arctan$ ritarda — e i loro sviluppi si controllano l'un l'altro componendo $\arctan(\tan x)=x$.

### **3. Funzioni pari e dispari**

Gli sviluppi riflettono le simmetrie. La funzione $\sin x$ è dispari, quindi nello sviluppo compaiono solo potenze dispari. La funzione $\cos x$ è pari, quindi compaiono solo potenze pari.

Questo aiuta a evitare errori: un termine $x^2$ nello sviluppo di $\sin x$ o un termine $x^3$ nello sviluppo di $\cos x$ vicino a $0$ sarebbe incompatibile con la simmetria.

### **4. Esempio: sviluppo di $\cos(2x)$**

Partiamo da

$$
\cos u=1-\frac{u^2}{2}+\frac{u^4}{24}+o(u^4).
$$

Ponendo $u=2x$:

$$
\cos(2x)=1-\frac{(2x)^2}{2}+\frac{(2x)^4}{24}+o(x^4).
$$

Quindi

$$
\cos(2x)=1-2x^2+\frac{2}{3}x^4+o(x^4).
$$

### **5. Esempio: sviluppo di $\ln(1+x^2)$**

Usiamo

$$
\ln(1+u)=u-\frac{u^2}{2}+o(u^2).
$$

Ponendo $u=x^2$:

$$
\ln(1+x^2)=x^2-\frac{x^4}{2}+o(x^4).
$$

Il resto diventa $o(x^4)$ perché $u^2=x^4$.

### **6. Riepilogo**

Maclaurin è Taylor centrato in zero. Gli sviluppi principali vanno conosciuti e usati con attenzione nelle composizioni: sostituire $u$ con una funzione di $x$ cambia l'ordine del resto. La scelta dell'ordine dipende dalle cancellazioni presenti nel problema.
