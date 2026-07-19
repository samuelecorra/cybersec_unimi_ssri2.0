# **M11 Lezione 13 - Integrali e area**

### **1. Integrale definito e area orientata**

L'integrale definito

$$
\int_a^b f(x)\,dx
$$

è collegato all'area compresa tra il grafico di $f(x)$, l'asse delle ascisse e le rette verticali $x=a$ e $x=b$. Bisogna però fare attenzione: l'integrale definito non fornisce direttamente l'area geometrica, ma un'**area orientata**, cioè un'area con segno.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Se il grafico di $f$ sta sopra l'asse delle ascisse su tutto l'intervallo $[a,b]$, allora

$$
\int_a^b f(x)\,dx=A,
$$

dove $A$ è l'area geometrica della regione.

Se invece il grafico di $f$ sta sotto l'asse delle ascisse su tutto l'intervallo $[a,b]$, allora l'integrale è negativo:

$$
\int_a^b f(x)\,dx=-A.
$$

Se la funzione cambia segno, l'integrale somma le aree sopra l'asse e sottrae quelle sotto l'asse. Per esempio, se nell'intervallo compaiono una regione positiva di area $A_1$ e una regione negativa di area $A_2$, allora

$$
\int_a^b f(x)\,dx=A_1-A_2.
$$

> 📌 L'integrale definito calcola area con segno. L'area geometrica si ottiene sommando sempre contributi positivi.

### **2. Area geometrica e valore assoluto**

Per calcolare l'area geometrica tra il grafico di $f$ e l'asse $x$ su $[a,b]$, bisogna integrare il valore assoluto:

$$
A=\int_a^b |f(x)|\,dx.
$$

In pratica, quasi sempre si spezza l'intervallo nei punti in cui la funzione si annulla, cioè nei punti in cui il grafico attraversa l'asse delle ascisse.

Se $f$ è positiva su $[a,c]$ e negativa su $[c,b]$, allora

$$
A=\int_a^c f(x)\,dx-\int_c^b f(x)\,dx.
$$

Il segno meno davanti al secondo integrale serve perché, su $[c,b]$, l'integrale di $f$ è negativo.

> ⚠️ Non confondere $\int_a^b f(x)\,dx$ con l'area geometrica. Se ci sono parti sotto l'asse, l'integrale può essere zero anche quando l'area è positiva.

---
### **3. Esempio 1: area tra $y=\cos x$ e l'asse $x$**

Calcoliamo l'area delimitata dalla funzione

$$
y=\cos x
$$

e dall'asse delle ascisse nell'intervallo

$$
[0,2\pi].
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.1 Individuare dove la funzione cambia segno**

La funzione $\cos x$ si annulla in

$$
x=\frac{\pi}{2}
\qquad\text{e}\qquad
x=\frac{3\pi}{2}
$$

all'interno dell'intervallo $[0,2\pi]$.

Il segno è:

- $\cos x\ge 0$ su $\left[0,\frac{\pi}{2}\right]$;
- $\cos x\le 0$ su $\left[\frac{\pi}{2},\frac{3\pi}{2}\right]$;
- $\cos x\ge 0$ su $\left[\frac{3\pi}{2},2\pi\right]$.

Quindi la regione totale è formata da tre parti: $A_1$ sopra l'asse, $A_2$ sotto l'asse, $A_3$ sopra l'asse.

L'integrale orientato sarebbe

$$
\int_0^{2\pi}\cos x\,dx=A_1-A_2+A_3.
$$

L'area geometrica, invece, è

$$
A=A_1+A_2+A_3.
$$

#### **3.2 Scrittura dell'area**

Per rendere positivo anche il tratto sotto l'asse:

$$
A=
\int_0^{\frac{\pi}{2}}\cos x\,dx
-
\int_{\frac{\pi}{2}}^{\frac{3\pi}{2}}\cos x\,dx
+
\int_{\frac{3\pi}{2}}^{2\pi}\cos x\,dx.
$$

Calcoliamo usando la primitiva

$$
\int \cos x\,dx=\sin x+C.
$$

Otteniamo:

$$
A=
\left[\sin x\right]_0^{\frac{\pi}{2}}
-
\left[\sin x\right]_{\frac{\pi}{2}}^{\frac{3\pi}{2}}
+
\left[\sin x\right]_{\frac{3\pi}{2}}^{2\pi}.
$$

Valutiamo i tre contributi:

$$
\left[\sin x\right]_0^{\frac{\pi}{2}}
=
\sin\left(\frac{\pi}{2}\right)-\sin 0
=1-0=1.
$$

Sul tratto centrale:

$$
\left[\sin x\right]_{\frac{\pi}{2}}^{\frac{3\pi}{2}}
=
\sin\left(\frac{3\pi}{2}\right)-\sin\left(\frac{\pi}{2}\right)
=-1-1=-2.
$$

Poiché questo tratto è sotto l'asse, nella formula dell'area compare con il segno meno:

$$
-(-2)=2.
$$

Infine:

$$
\left[\sin x\right]_{\frac{3\pi}{2}}^{2\pi}
=
\sin(2\pi)-\sin\left(\frac{3\pi}{2}\right)
=0-(-1)=1.
$$

Quindi:

$$
A=1+2+1=4.
$$

#### **3.3 Risultato**

L'area delimitata da $y=\cos x$ e dall'asse delle ascisse in $[0,2\pi]$ è

$$
A=4.
$$

Osserviamo invece che l'integrale orientato vale

$$
\int_0^{2\pi}\cos x\,dx
=
\left[\sin x\right]_0^{2\pi}
=0.
$$

Questo conferma che integrale orientato e area geometrica non coincidono quando la funzione cambia segno.

---
### **4. Area tra due curve**

Consideriamo due funzioni $f$ e $g$ definite su $[a,b]$. Se su tutto l'intervallo il grafico di $f$ sta sopra il grafico di $g$, allora l'area compresa tra le due curve e le rette verticali $x=a$ e $x=b$ è

$$
A=\int_a^b f(x)\,dx-\int_a^b g(x)\,dx.
$$

Per linearità:

$$
A=\int_a^b \left[f(x)-g(x)\right]\,dx.
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La formula è valida anche se una o entrambe le funzioni sono negative: ciò che conta è integrare **quella sopra meno quella sotto**.

> 📌 Per l'area tra due curve non interessa se i grafici stanno sopra o sotto l'asse $x$. Interessa solo quale grafico è superiore nell'intervallo considerato.

Se le due curve si intersecano più volte, bisogna spezzare l'intervallo nei punti di intersezione e, in ogni tratto, integrare funzione superiore meno funzione inferiore.

### **5. Esempio 2: area delimitata da $y=x$ e $y=2-x^2$**

Calcoliamo l'area della regione di piano delimitata dai grafici

$$
y=x
$$

e

$$
y=2-x^2.
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.1 Punti di intersezione**

I punti di intersezione si trovano risolvendo il sistema

$$
\begin{cases}
y=x \\
y=2-x^2
\end{cases}
$$

Uguagliamo le due espressioni:

$$
2-x^2=x.
$$

Portiamo tutto a sinistra:

$$
x^2+x-2=0.
$$

Fattorizziamo:

$$
(x+2)(x-1)=0.
$$

Quindi:

$$
x=-2
\qquad\text{oppure}\qquad
x=1.
$$

La regione chiusa è compresa tra $x=-2$ e $x=1$.

#### **5.2 Funzione sopra e funzione sotto**

Per capire quale curva sta sopra, prendiamo un punto interno all'intervallo, ad esempio $x=0$.

Per la retta:

$$
y=x=0.
$$

Per la parabola:

$$
y=2-x^2=2.
$$

Quindi, nell'intervallo considerato, la parabola è sopra la retta. L'area è:

$$
A=\int_{-2}^{1}\left[(2-x^2)-x\right]\,dx.
$$

#### **5.3 Calcolo dell'integrale**

Calcoliamo:

$$
A=\int_{-2}^{1}(2-x^2-x)\,dx.
$$

Una primitiva è:

$$
2x-\frac{x^3}{3}-\frac{x^2}{2}.
$$

Quindi:

$$
A=
\left[
2x-\frac{x^3}{3}-\frac{x^2}{2}
\right]_{-2}^{1}.
$$

Valutiamo in $x=1$:

$$
2\cdot 1-\frac{1^3}{3}-\frac{1^2}{2}
=2-\frac{1}{3}-\frac{1}{2}
=\frac{7}{6}.
$$

Valutiamo in $x=-2$:

$$
2(-2)-\frac{(-2)^3}{3}-\frac{(-2)^2}{2}
=-4+\frac{8}{3}-2
=-\frac{10}{3}.
$$

Sottraendo:

$$
A=\frac{7}{6}-\left(-\frac{10}{3}\right)
=\frac{7}{6}+\frac{20}{6}
=\frac{27}{6}
=\frac{9}{2}.
$$

#### **5.4 Risultato**

L'area delimitata dai grafici $y=x$ e $y=2-x^2$ è

$$
A=\frac{9}{2}.
$$

### **6. Riepilogo**

Per calcolare aree con gli integrali bisogna distinguere:

- integrale definito, che dà area orientata;
- area tra grafico e asse $x$, che richiede il valore assoluto o lo spezzamento nei cambi di segno;
- area tra due curve, che si calcola come integrale di funzione sopra meno funzione sotto.

Il passaggio decisivo è sempre geometrico: individuare gli intervalli corretti e capire quale contributo deve essere preso con segno positivo.
