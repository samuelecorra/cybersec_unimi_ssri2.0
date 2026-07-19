# **M10 UD3 Lezione 5 - Applicazioni**

### **1. Taylor nel calcolo dei limiti**

L'applicazione più frequente di Taylor in Analisi 1 è il calcolo di limiti con cancellazioni. Gli sviluppi permettono di individuare il primo termine non nullo e quindi la parte principale.

### **2. Esempio con seno**

Calcoliamo

$$
\lim_{x\to 0}\frac{\sin x-x}{x^3}.
$$

Usiamo

$$
\sin x=x-\frac{x^3}{6}+o(x^3).
$$

Quindi

$$
\sin x-x=-\frac{x^3}{6}+o(x^3).
$$

Dividendo per $x^3$:

$$
\frac{\sin x-x}{x^3}
=-\frac{1}{6}+\frac{o(x^3)}{x^3}.
$$

Il limite è

$$
-\frac{1}{6}.
$$

### **3. Esempio con più funzioni**

Calcoliamo

$$
\lim_{x\to 0}\frac{e^x-\cos x-x}{x^2}.
$$

Sviluppiamo:

$$
e^x=1+x+\frac{x^2}{2}+o(x^2),
$$

$$
\cos x=1-\frac{x^2}{2}+o(x^2).
$$

Allora

$$
e^x-\cos x-x
=\left(1+x+\frac{x^2}{2}+o(x^2)\right)
-\left(1-\frac{x^2}{2}+o(x^2)\right)-x.
$$

Cancellando:

$$
e^x-\cos x-x=x^2+o(x^2).
$$

Quindi il limite è

$$
1.
$$

### **4. Approssimazioni numeriche**

Taylor permette anche di approssimare valori. Per esempio, per $x$ piccolo:

$$
e^x\approx 1+x+\frac{x^2}{2}.
$$

Per $x=0.1$:

$$
e^{0.1}\approx 1+0.1+\frac{0.01}{2}=1.105.
$$

Il resto di Lagrange consente poi di stimare l'errore:

$$
|R_2(0.1)|\le \frac{e^{0.1}}{6}(0.1)^3.
$$

### **5. Taylor e studio locale del grafico: il criterio delle derivate successive**

Lo sviluppo di Taylor rende finalmente **teorema** (e non più annuncio: M09/UD5/L4 lo aveva promesso) la classificazione dei punti stazionari.

**Teorema (criterio delle derivate successive).** Sia $f'(x_0)=f''(x_0)=\dots=f^{(m-1)}(x_0)=0$ e $f^{(m)}(x_0)\neq 0$, con $m\ge 2$ (e le ipotesi di regolarità di Peano). Allora:

1. $m$ **pari** e $f^{(m)}(x_0)>0$: **minimo locale**;
2. $m$ **pari** e $f^{(m)}(x_0)<0$: **massimo locale**;
3. $m$ **dispari**: **nessun estremo** ($f$ attraversa la sua tangente orizzontale).

*Dimostrazione.* Con le ipotesi, lo sviluppo di Peano si riduce a

$$
f(x)-f(x_0) = \frac{f^{(m)}(x_0)}{m!}(x-x_0)^m + o((x-x_0)^m)
= (x-x_0)^m\left[\frac{f^{(m)}(x_0)}{m!} + \omega(x)\right]
$$

con $\omega(x)\to 0$. Per la permanenza del segno (M08), in un intorno puntato la parentesi quadra ha il **segno di $f^{(m)}(x_0)$**. Se $m$ è pari, $(x-x_0)^m>0$ nei due lati: l'incremento $f(x)-f(x_0)$ ha segno costante — positivo (minimo) o negativo (massimo) secondo $f^{(m)}(x_0)$. Se $m$ è dispari, $(x-x_0)^m$ cambia segno attraversando $x_0$: l'incremento pure, e $x_0$ non è estremo. $\square$

Il caso $m=2$ è il **criterio della derivata seconda** ($f''(x_0)>0\Rightarrow$ minimo), stavolta con la dimostrazione completa. Esempi tarati: $x^4$ ($m=4$ pari, minimo), $-x^4$ (massimo), $x^3$ ($m=3$ dispari, niente estremo), $x^5$ (idem) — la famiglia che il solo $f''(x_0)=0$ non sapeva distinguere.

> ⚠️ Il criterio presuppone che **esista** una prima derivata non nulla in $x_0$. Esistono funzioni ($C^\infty$ piatte, come $e^{-1/x^2}$ prolungata con $0$) con **tutte** le derivate nulle nel punto: lì il criterio tace per sempre e decide solo lo studio del segno — il limite strutturale dello strumento, non un difetto di calcolo.

### **6. Riepilogo**

Taylor è uno strumento trasversale: calcola limiti, produce approssimazioni numeriche e descrive il comportamento locale di una funzione. La parte più importante è scegliere l'ordine giusto dello sviluppo, cioè abbastanza alto da superare tutte le cancellazioni presenti.
