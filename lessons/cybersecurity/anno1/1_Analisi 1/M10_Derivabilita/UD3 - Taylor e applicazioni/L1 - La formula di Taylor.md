# **M10 UD3 Lezione 1 - La formula di Taylor**

### **1. Approssimare una funzione con un polinomio**

La formula di Taylor risponde a una domanda centrale: come possiamo approssimare una funzione regolare vicino a un punto $x_0$ usando un polinomio?

Un polinomio è più semplice da manipolare di una funzione generica. Se scegliamo bene i coefficienti, possiamo costruire un polinomio che abbia nello stesso punto il medesimo valore, la stessa derivata, la stessa derivata seconda e così via.

### **2. Polinomio di Taylor**

Supponiamo che $f$ abbia derivate fino all'ordine $n$ in un intorno di $x_0$. Il polinomio di Taylor di ordine $n$ centrato in $x_0$ è

$$
T_n(x)=\sum_{k=0}^{n}\frac{f^{(k)}(x_0)}{k!}(x-x_0)^k.
$$

Esplicitamente:

$$
T_n(x)=f(x_0)+f'(x_0)(x-x_0)+\frac{f''(x_0)}{2!}(x-x_0)^2+\cdots+\frac{f^{(n)}(x_0)}{n!}(x-x_0)^n.
$$

Il polinomio è costruito in modo che

$$
T_n^{(k)}(x_0)=f^{(k)}(x_0)
$$

per ogni $k=0,1,\ldots,n$.

**Verifica della costruzione.** Derivando $k$ volte il monomio $\frac{f^{(j)}(x_0)}{j!}(x-x_0)^j$ e valutando in $x_0$, sopravvive solo il termine con $j=k$ (i gradi minori si annullano derivando, i maggiori si annullano in $x_0$), e vale $\frac{f^{(k)}(x_0)}{k!}\cdot k! = f^{(k)}(x_0)$ ✓ (pattern delle derivate delle potenze, M09/UD3/L5). I fattoriali a denominatore sono **esattamente** ciò che serve a compensare i $k!$ prodotti dalla derivazione.

**Unicità.** $T_n$ è **l'unico** polinomio di grado $\le n$ con queste $n+1$ condizioni: se $P$ e $Q$ le soddisfano entrambi, $D=P-Q$ è un polinomio di grado $\le n$ con $D^{(k)}(x_0)=0$ per $k=0,\dots,n$, e scrivendolo in potenze di $(x-x_0)$ tutti i coefficienti risultano nulli: $D\equiv 0$. Vale di più (lo dà il resto di Peano, Lezione 2): $T_n$ è l'unico polinomio di grado $\le n$ che approssima $f$ a meno di $o((x-x_0)^n)$ — per $n=1$ è la "migliore approssimazione lineare" di M09/UD1/L5, di cui Taylor è la generalizzazione a ogni ordine.

> 📌 Taylor non dice solo "approssima con un polinomio": dice quale polinomio usare, perché è l'unico candidato ragionevole, e come controllare l'errore tramite un resto.

### **3. Formula con resto**

La formula di Taylor si scrive

$$
f(x)=T_n(x)+R_n(x),
$$

dove $R_n(x)$ è il resto, cioè l'errore commesso sostituendo $f$ con il suo polinomio di Taylor.

Le forme principali del resto che useremo sono:

$$
R_n(x)=o((x-x_0)^n)
$$

quando si usa il resto di Peano, e

$$
R_n(x)=\frac{f^{(n+1)}(\xi)}{(n+1)!}(x-x_0)^{n+1}
$$

quando si usa il resto di Lagrange, con $\xi$ compreso tra $x$ e $x_0$.

### **4. Esempio: esponenziale**

Per

$$
f(x)=e^x
$$

tutte le derivate sono ancora $e^x$. Centrando in $x_0=0$, si ha

$$
f^{(k)}(0)=1
$$

per ogni $k$. Il polinomio di Taylor di ordine $n$ è

$$
T_n(x)=1+x+\frac{x^2}{2!}+\cdots+\frac{x^n}{n!}.
$$

Quindi, vicino a $0$,

$$
e^x=1+x+\frac{x^2}{2!}+\cdots+\frac{x^n}{n!}+R_n(x).
$$

<!-- TODO FIGURA:
Grafico di sin x su [-pi, pi] con sovrapposti i polinomi di Taylor T1(x)=x, T3(x)=x-x^3/6, T5(x)=x-x^3/6+x^5/120 in colori diversi: T1 aderisce solo vicinissimo a 0, T3 segue fino a circa ±1.5, T5 fino a quasi ±pi; evidenziare con una fascia il progressivo allargamento della zona di aderenza.
Obiettivo didattico: mostrare che aumentando l'ordine il polinomio di Taylor aderisce alla funzione su una zona sempre piu' ampia attorno al centro.
-->

### **5. Esempio: seno**

Per $f(x)=\sin x$, centrando in $0$, le derivate alternano seno e coseno. Si ottiene

$$
\sin x=x-\frac{x^3}{3!}+\frac{x^5}{5!}-\cdots.
$$

I termini pari non compaiono perché le derivate pari del seno in $0$ valgono $0$.

### **6. Riepilogo**

La formula di Taylor costruisce un polinomio che riproduce le derivate di una funzione fino a un certo ordine nel punto scelto. Il polinomio fornisce l'approssimazione, il resto misura l'errore. Le lezioni successive distinguono le forme più utili del resto.
