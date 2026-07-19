# **M10 UD3 Lezione 3 - Il resto di Lagrange**

### **1. Perché serve un resto esplicito**

Il resto di Peano è perfetto per i limiti, ma non fornisce direttamente una maggiorazione numerica dell'errore. Se vogliamo sapere quanto è accurata un'approssimazione, è più utile il resto di Lagrange.

### **2. Formula**

Supponiamo che $f$ abbia derivata di ordine $n+1$ in un intervallo che contiene $x_0$ e $x$. Allora

$$
f(x)=\sum_{k=0}^{n}\frac{f^{(k)}(x_0)}{k!}(x-x_0)^k+R_n(x),
$$

dove esiste un punto $\xi$ compreso tra $x_0$ e $x$ tale che

$$
R_n(x)=\frac{f^{(n+1)}(\xi)}{(n+1)!}(x-x_0)^{n+1}.
$$

Il punto $\xi$ non è noto in generale, ma la formula permette di stimare il resto se sappiamo limitare la derivata di ordine $n+1$.

**Casi di controllo**: per $n=0$ la formula è $f(x)=f(x_0)+f'(\xi)(x-x_0)$ — è esattamente il **teorema di Lagrange** di M09 (UD5/L3), di cui questo resto è la generalizzazione a ogni ordine (e da cui prende il nome).

### **2bis. Dimostrazione**

*Dimostrazione (con il teorema di Cauchy).* Fissiamo $x\neq x_0$ e consideriamo, come funzioni della variabile ausiliaria $t$ nell'intervallo di estremi $x_0$ e $x$:

$$
g(t) = f(x) - \sum_{k=0}^{n}\frac{f^{(k)}(t)}{k!}(x-t)^k,
\qquad
h(t) = (x-t)^{n+1}
$$

($g(t)$ è "l'errore di Taylor visto dal centro mobile $t$"). Entrambe sono derivabili nell'intervallo (qui servono le $n+1$ derivate di $f$), ed entrambe si annullano in $t=x$: $g(x)=f(x)-f(x)=0$, $h(x)=0$.

Il calcolo di $g'(t)$ è una cascata **telescopica**: derivando il termine $k$-esimo (prodotto!) compaiono $-\frac{f^{(k+1)}(t)}{k!}(x-t)^k$ e $+\frac{f^{(k)}(t)}{(k-1)!}(x-t)^{k-1}$, e il secondo cancella il primo del termine precedente. Sopravvive solo la coda:

$$
g'(t) = -\frac{f^{(n+1)}(t)}{n!}(x-t)^n,
\qquad
h'(t) = -(n+1)(x-t)^n \neq 0 \text{ per } t\neq x
$$

Applichiamo **Cauchy** (M09/UD5/L5) sull'intervallo di estremi $x_0$ e $x$: esiste $\xi$ strettamente compreso tale che

$$
\frac{g(x_0)-g(x)}{h(x_0)-h(x)} = \frac{g'(\xi)}{h'(\xi)}
$$

Il membro sinistro è $\dfrac{g(x_0)}{h(x_0)} = \dfrac{R_n(x)}{(x-x_0)^{n+1}}$ (per definizione $g(x_0)$ è proprio $f(x)-T_n(x)=R_n(x)$); il membro destro è

$$
\frac{-\frac{f^{(n+1)}(\xi)}{n!}(x-\xi)^n}{-(n+1)(x-\xi)^n} = \frac{f^{(n+1)}(\xi)}{(n+1)!}
$$

Uguagliando e moltiplicando per $(x-x_0)^{n+1}$ si ottiene la formula. $\square$

> 📌 Scegliendo $h(t)=(x-t)^{p}$ con altri esponenti si ottengono altre forme del resto (Cauchy, integrale): è la stessa macchina con un metro diverso. Per Analisi 1 bastano Peano (limiti) e Lagrange (stime).

### **3. Stima dell'errore**

Se nell'intervallo considerato vale

$$
|f^{(n+1)}(t)|\le M,
$$

allora

$$
|R_n(x)|\le \frac{M}{(n+1)!}|x-x_0|^{n+1}.
$$

Questa è una stima concreta dell'errore commesso sostituendo $f(x)$ con $T_n(x)$.

### **4. Esempio: approssimare $e^x$**

Approssimiamo $e^x$ vicino a $0$ con

$$
T_2(x)=1+x+\frac{x^2}{2}.
$$

Il resto di Lagrange è

$$
R_2(x)=\frac{e^\xi}{3!}x^3
$$

con $\xi$ compreso tra $0$ e $x$.

Se $0\le x\le 0.1$, allora $e^\xi\le e^{0.1}$. Quindi

$$
|R_2(x)|\le \frac{e^{0.1}}{6}|x|^3.
$$

Per $x=0.1$:

$$
|R_2(0.1)|\le \frac{e^{0.1}}{6}\cdot 0.001.
$$

Questa stima quantifica l'errore massimo dell'approssimazione.

### **5. Confronto con Peano**

Il resto di Peano dice:

$$
R_2(x)=o(x^2)
$$

per $x\to 0$. Il resto di Lagrange dice invece:

$$
R_2(x)=\frac{e^\xi}{6}x^3.
$$

La prima forma è più sintetica per i limiti, la seconda è più utile per stimare l'errore.

### **6. Riepilogo**

Il resto di Lagrange completa la formula di Taylor con un termine di errore controllabile. Anche se il punto $\xi$ non è noto, una maggiorazione della derivata successiva permette di stimare l'errore. Questo rende Taylor uno strumento non solo teorico, ma anche numerico.
