# **Lezione 11: Integrali – esercizi svolti**

---
# **1)** $\int \left(2-\frac{x}{2}-\frac{2}{x}\right)\,dx$

## **1.1 Sfrutto la linearita**

$$
\int \left(2-\frac{x}{2}-\frac{2}{x}\right),dx =

\int 2,dx-\frac12\int x,dx-2\int \frac1x,dx
$$

## **1.2 Primitive immediate**

$$

\int 2,dx=2x

$$  
$$

\int x,dx=\frac{x^2}{2}

$$

$$

\int \frac1x,dx=\ln|x|

$$
## **1.3 Risultato**

$$
2x-\frac12\cdot\frac{x^2}{2}-2\ln|x|+C =

2x-\frac{x^2}{4}-2\ln|x|+C
$$

---
# **2)** $\int \frac{\ln x}{x^2}\,dx$

Qui conviene **per parti**, vedendo l’integranda come prodotto:

$$

\frac{\ln x}{x^2}=(\ln x)\cdot x^{-2}

$$
## **2.1 Scelta per parti**

Prendo:  
$$

f(x)=\ln x

\qquad

g’(x)=\frac{1}{x^2}=x^{-2}

$$

Allora:
$$

f’(x)=\frac{1}{x}

\qquad

g(x)=\int x^{-2},dx=-x^{-1}=-\frac1x

$$

## **2.2 Applico la formula**

$$

\int f,g’,dx=f\cdot g-\int f’\cdot g,dx

$$
Quindi:

$$
\int \frac{\ln x}{x^2},dx =

\ln x\left(-\frac1x\right)-\int \frac1x\left(-\frac1x\right),dx
$$

Semplifico:

$$
-\frac{\ln x}{x}+\int \frac{1}{x^2},dx
$$
E:

$$

\int \frac{1}{x^2},dx=\int x^{-2},dx=-\frac1x

$$
## **2.3 Risultato**

$$
-\frac{\ln x}{x}-\frac1x+C =

-\frac{\ln x+1}{x}+C
$$

---
# **3)** $\int_{-b}^{b}$ |x-a|\,dx **con** $0<a<b$

## **3.1 Tolgo il valore assoluto spezzando in** x=a

Dato che -b<a<b, si spezza:

$$
\int_{-b}^{b}|x-a|,dx =

\int_{-b}^{a}|x-a|,dx+\int_{a}^{b}|x-a|,dx
$$
Ora:

- se x\le a, allora x-a\le 0 quindi |x-a|=a-x
    
- se x\ge a, allora x-a\ge 0 quindi |x-a|=x-a  

Quindi:

$$
\int_{-b}^{b}|x-a|,dx =  

\int_{-b}^{a}(a-x),dx+\int_{a}^{b}(x-a),dx
$$
## **3.2 Calcolo i due integrali**

Primo:

$$
\int_{-b}^{a}(a-x),dx =  

\left[ax-\frac{x^2}{2}\right]_{-b}^{a}
$$
Valuto:

$$
\left(ax-\frac{x^2}{2}\right)\Big|_{x=a} =
a^2-\frac{a^2}{2}=\frac{a^2}{2}
$$

$$
\left(ax-\frac{x^2}{2}\right)\Big|_{x=-b} =

-a b-\frac{b^2}{2}
$$
Quindi:

$$
\int_{-b}^{a}(a-x),dx =

\frac{a^2}{2}-\left(-ab-\frac{b^2}{2}\right) =

\frac{a^2}{2}+ab+\frac{b^2}{2}
$$
Secondo:

$$
\int_{a}^{b}(x-a),dx =  

\left[\frac{x^2}{2}-ax\right]_{a}^{b}
$$
Valuto:

$$
\left(\frac{x^2}{2}-ax\right)\Big|_{x=b} = 

\frac{b^2}{2}-ab
$$

$$
\left(\frac{x^2}{2}-ax\right)\Big|_{x=a} =

\frac{a^2}{2}-a^2=-\frac{a^2}{2}
$$
Quindi:

$$
\int_{a}^{b}(x-a),dx =

\left(\frac{b^2}{2}-ab\right)-\left(-\frac{a^2}{2}\right) =

\frac{b^2}{2}-ab+\frac{a^2}{2}
$$

## **3.3 Somma e semplifico**

$$\int_{-b}^{b}|x-a|,dx =

\left(\frac{a^2}{2}+ab+\frac{b^2}{2}\right)
+
\left(\frac{b^2}{2}-ab+\frac{a^2}{2}\right)
$$

I termini +ab e -ab si cancellano, resta:

$$

=a^2+b^2

$$

---
# **4)** $\int \frac{e^x}{e^{2x}-3e^x+2}\,dx$

Qui la cosa “ricorrente” e scomoda e e^x. Quindi **sostituzione**.
## **4.1 Sostituzione**

$$

y=e^x

\qquad\Rightarrow\qquad

dy=e^x,dx

$$
Allora l’integrale diventa:

$$\int \frac{e^x,dx}{e^{2x}-3e^x+2} =

\int \frac{dy}{y^2-3y+2}
$$

## **4.2 Scompongo il denominatore**
  
$$

y^2-3y+2=(y-1)(y-2)

$$
## **4.3 Fratti semplici**

Cerco:
$$

\frac{1}{(y-1)(y-2)}=\frac{A}{y-1}+\frac{B}{y-2}

$$
Denominatore comune:
$$

1=A(y-2)+B(y-1)=(A+B)y-(2A+B)

$$

Confronto coefficienti con 1=0\cdot y+1:
$$

\begin{cases}

A+B=0\

-2A-B=1

\end{cases}

$$
Da $B=-A$. Sostituisco:
$$

-2A-(-A)=-A=1

\qquad\Rightarrow\qquad

A=-1

$$
Quindi:
$$

B=1

$$
Allora:
$$

\frac{1}{(y-1)(y-2)}=-\frac{1}{y-1}+\frac{1}{y-2}

$$
## **4.4 Integro e torno a** x

$$
\int \frac{dy}{(y-1)(y-2)} =

-\int \frac{1}{y-1},dy+\int \frac{1}{y-2},dy
$$

$$
-\ln|y-1|+\ln|y-2|+C =  

\ln\left|\frac{y-2}{y-1}\right|+C
$$

Ora $y=e^x$:  

$$

\ln\left|\frac{e^x-2}{e^x-1}\right|+C

$$

---
## **Risultati finali compatti**

$$
\int \left(2-\frac{x}{2}-\frac{2}{x}\right),dx =

2x-\frac{x^2}{4}-2\ln|x|+C
$$

$$
\int \frac{\ln x}{x^2},dx =  

-\frac{\ln x+1}{x}+C
$$

$$\int_{-b}^{b}|x-a|,dx =
a^2+b^2
\qquad (0<a<b)
$$

$$
\int \frac{e^x}{e^{2x}-3e^x+2},dx =
\ln\left|\frac{e^x-2}{e^x-1}\right|+C
$$
