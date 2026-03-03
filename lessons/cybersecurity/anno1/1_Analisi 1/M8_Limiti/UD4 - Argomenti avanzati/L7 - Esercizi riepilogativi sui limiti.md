## **Lezione 17: Esercizi riepilogativi sui limiti**

### **1. Obiettivo della lezione**

Concludiamo il percorso sui limiti facendo alcuni **esercizi di riepilogo**, usando:

- **limiti notevoli** ed equivalenze,
    
- oppure **sviluppi di Taylor** quando serve.

---
## **2. Esercizio 1**

Calcolare:
$$

\lim_{x \to 0} \frac{x^3}{\tan x - \sin x}

$$
### **2.1 Forma indeterminata**

Per $x \to 0$:

- $x^3 \to 0$
    
- $\tan x \to 0$
    
- $\sin x \to 0$  

Quindi:
$$

\frac{0}{0}

$$

---
### **2.2 Soluzione con limiti notevoli**

Riscriviamo:
$$

\tan x = \frac{\sin x}{\cos x}

$$
Allora:
$$
\tan x - \sin x =

\sin x\left(\frac{1}{\cos x}-1\right) =

\sin x\left(\frac{1-\cos x}{\cos x}\right)

$$

Quindi il limite diventa:
$$

\lim_{x \to 0}

\frac{x^3}{\sin x}\cdot\frac{\cos x}{1-\cos x}

$$

Ora scriviamo $x^3 = x\cdot x^2$:

$$

\lim_{x \to 0}

\left(\frac{x}{\sin x}\right)

\left(\frac{x^2}{1-\cos x}\right)

\cos x

$$

Usiamo i limiti noti:

$$

\lim_{x \to 0}\frac{\sin x}{x}=1

\quad \Longrightarrow \quad

\lim_{x \to 0}\frac{x}{\sin x}=1

$$

$$

\lim_{x \to 0}\frac{1-\cos x}{x^2}=\frac{1}{2}

\quad \Longrightarrow \quad

\lim_{x \to 0}\frac{x^2}{1-\cos x}=2

$$

$$

\lim_{x \to 0}\cos x = 1

$$

Quindi:

$$

1 \cdot 2 \cdot 1 = 2

$$

Conclusione:
$$

\boxed{2}

$$

---
### **2.3 Soluzione alternativa con Taylor**

Sviluppi al terzo ordine:

$$

\tan x = x + \frac{x^3}{3} + o(x^3)

$$

$$

\sin x = x - \frac{x^3}{6} + o(x^3)

$$

Differenza:
$$
\tan x - \sin x =

\left(x + \frac{x^3}{3}\right) - \left(x - \frac{x^3}{6}\right) + o(x^3)
$$

I termini in $x$ si elidono e resta:

$$
\tan x - \sin x =

\left(\frac{x^3}{3} + \frac{x^3}{6}\right) + o(x^3) =

\frac{x^3}{2} + o(x^3)
$$

Quindi:
$$

\lim_{x \to 0}\frac{x^3}{\frac{x^3}{2}+o(x^3)}=2

$$
Conclusione:
$$

\boxed{2}

$$

---
## **3. Esercizio 2**

Calcolare:
$$

\lim_{x \to 0}

\frac{\ln\left(1+\sin^4 x\right)}{x}

\cdot

\sin\left(\frac{2-x}{x^2}\right)

$$
### **3.1 Studio del primo fattore**

Usiamo:
$$

\sin x \sim x \quad (x \to 0)

$$

Quindi:
$$

\sin^4 x \sim x^4

$$

Poi:
$$

\ln(1+t) \sim t \quad (t \to 0)

$$

con $t=\sin^4 x$:
$$

\ln(1+\sin^4 x) \sim \sin^4 x \sim x^4

$$

Quindi:
$$

\frac{\ln(1+\sin^4 x)}{x} \sim \frac{x^4}{x}=x^3

$$
e dunque:
$$

\frac{\ln(1+\sin^4 x)}{x} \to 0

$$

---
### **3.2 Studio del secondo fattore**

L’argomento del seno è:

$$

\frac{2-x}{x^2}

$$

Per $x \to 0$ il denominatore va a $0$ mentre il numeratore tende a $2$, quindi:

$$

\frac{2-x}{x^2} \to \pm\infty

$$

Il seno non ammette limite quando l’argomento tende a infinito, ma è sempre limitato:

$$

-1 \le \sin\left(\frac{2-x}{x^2}\right) \le 1

$$

---
### **3.3 Conclusione con confronto**

Il prodotto è:

- un fattore che tende a $0$,
    
- un fattore limitato.

Quindi il limite vale:
$$

\boxed{0}

$$

Formalmente:

$$

\left|

\frac{\ln(1+\sin^4 x)}{x}

\cdot

\sin\left(\frac{2-x}{x^2}\right)

\right|

\le

\left|

\frac{\ln(1+\sin^4 x)}{x}

\right|

\to 0

$$

Per il teorema dei due carabinieri, il prodotto tende a $0$.

---
## **4. Esercizio 3**

Calcolare:
$$

\lim_{x \to 0}

\frac{\frac{1}{2}\sin(x^2)+\ln(\cos x)}

{\sqrt{1+x^4}-1}

$$
### **4.1 Denominatore**

Usiamo:
$$

(1+u)^{1/2}=1+\frac{1}{2}u+o(u)

$$

con $u=x^4$:

$$

\sqrt{1+x^4}=1+\frac{1}{2}x^4+o(x^4)

$$
Quindi:
$$

\sqrt{1+x^4}-1=\frac{1}{2}x^4+o(x^4)

$$

---
### **4.2 Primo termine del numeratore: $\frac{1}{2}\sin(x^2)$**

Sviluppo del seno:
$$

\sin t = t + o(t)

$$
con $t=x^2$:
$$

\sin(x^2)=x^2+o(x^2)

$$

Per i nostri scopi basta scrivere l’errore come $o(x^4)$, perché il termine successivo sarebbe di ordine $x^6$:

$$

\sin(x^2)=x^2+o(x^4)

$$
Quindi:
$$

\frac{1}{2}\sin(x^2)=\frac{1}{2}x^2+o(x^4)

$$

---
### **4.3 Secondo termine del numeratore: $\ln(\cos x)$**

Sviluppo del coseno al quarto ordine:

$$

\cos x = 1-\frac{x^2}{2}+\frac{x^4}{24}+o(x^4)

$$

Poniamo:

$$

T = -\frac{x^2}{2}+\frac{x^4}{24}+o(x^4)

$$

così che:

$$

\cos x = 1+T

$$

Ora usiamo lo sviluppo del logaritmo al secondo ordine:

$$

\ln(1+T)=T-\frac{T^2}{2}+o(T^2)

$$

Osserviamo che $T \sim -\frac{x^2}{2}$, quindi:

$$

T^2 \sim \frac{x^4}{4}

$$

e dunque $T^2$ è esattamente dell’ordine che ci interessa.

Calcoliamo:

$$
\ln(\cos x) =

\left(-\frac{x^2}{2}+\frac{x^4}{24}\right) +

\frac{1}{2}\left(\frac{x^4}{4}\right)

+

o(x^4)

$$

cioè:

$$
\ln(\cos x) =

-\frac{x^2}{2} 

+

\frac{x^4}{24} +
  

\frac{x^4}{8}

+

o(x^4)

$$

Mettiamo insieme i termini in $x^4$:

$$
\frac{1}{24}-\frac{1}{8} =

\frac{1}{24}-\frac{3}{24} =

-\frac{2}{24} =

-\frac{1}{12}

$$

Quindi:

$$
\ln(\cos x) =

-\frac{x^2}{2}

-\frac{x^4}{12}

+

o(x^4)

$$

---
### **4.4 Numeratore totale**

Sommiamo:

$$
\frac{1}{2}\sin(x^2)+\ln(\cos x) =

\left(\frac{1}{2}x^2+o(x^4)\right)

+

\left(-\frac{x^2}{2}-\frac{x^4}{12}+o(x^4)\right)

$$

I termini in $x^2$ si cancellano e rimane:

$$

-\frac{x^4}{12}+o(x^4)

$$

---
### **4.5 Calcolo del limite**

Abbiamo quindi:
$$

\lim_{x \to 0}

\frac{-\frac{x^4}{12}+o(x^4)}{\frac{x^4}{2}+o(x^4)}

$$
Raccogliamo $x^4$:
$$

\lim_{x \to 0}

\frac{x^4\left(-\frac{1}{12}+\frac{o(x^4)}{x^4}\right)}

{x^4\left(\frac{1}{2}+\frac{o(x^4)}{x^4}\right)}

$$

Semplifichiamo $x^4$ e usiamo:

$$

\frac{o(x^4)}{x^4}\to 0

$$
Otteniamo:
$$

\frac{-\frac{1}{12}}{\frac{1}{2}}=-\frac{1}{6}

$$
Conclusione:
$$

\boxed{-\frac{1}{6}}

$$

---
