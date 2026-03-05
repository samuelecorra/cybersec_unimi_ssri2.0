## **Lezione 15: Limiti con Taylor (resto di Peano) — Esempi**

### **1. Obiettivo della lezione**

Concludiamo il discorso sulla **formula di Taylor con resto di Peano** vedendo **due limiti** che si risolvono in modo pulito usando gli **sviluppi di Taylor**.

In entrambi i casi l’idea è sempre la stessa:

- riconoscere una forma indeterminata;
    
- sostituire ogni funzione con lo **sviluppo al giusto ordine**;
    
- semplificare e usare il fatto che i termini di tipo $o(x^k)$ diventano trascurabili.

---
### **2. Esempio 1**

Calcolare:
$$

\lim_{x \to 0} \frac{\tan x - x}{e^x - 1 + \ln(1-x)}

$$
#### **2.1 Forma indeterminata**

Per $x \to 0$:

- $\tan x - x \to 0$
    
- $e^x - 1 + \ln(1-x) \to 0$

Quindi è una forma:
$$

\frac{0}{0}

$$

---
#### **2.2 Numeratore**

Dallo sviluppo di Taylor della tangente (centrato in $0$):

$$

\tan x = x + \frac{x^3}{3} + o(x^3)

$$

Quindi:
$$

\tan x - x = \frac{x^3}{3} + o(x^3)

$$

---
#### **2.3 Denominatore**

Sviluppo dell’esponenziale fino al terzo ordine:

$$

e^x - 1 = x + \frac{x^2}{2} + \frac{x^3}{6} + o(x^3)

$$

Sviluppo del logaritmo (ricavato da $\ln(1+x)$ sostituendo $x \mapsto -x$):

$$

\ln(1-x) = -x - \frac{x^2}{2} - \frac{x^3}{3} + o(x^3)

$$
Sommiamo:

$$
e^x - 1 + \ln(1-x) =

\left(x + \frac{x^2}{2} + \frac{x^3}{6}\right)

+

\left(-x - \frac{x^2}{2} - \frac{x^3}{3}\right)

+

o(x^3)

$$

Si cancellano i termini di ordine $x$ e $x^2$:

$$
e^x - 1 + \ln(1-x) =

  

\left(\frac{x^3}{6} - \frac{x^3}{3}\right)

+

o(x^3)

$$
Quindi:
$$

e^x - 1 + \ln(1-x) = -\frac{x^3}{6} + o(x^3)

$$

---
#### **2.4 Calcolo del limite**

Sostituendo numeratore e denominatore:

$$

\lim_{x \to 0} \frac{\frac{x^3}{3} + o(x^3)}{-\frac{x^3}{6} + o(x^3)}

$$
Raccogliamo $x^3$ sopra e sotto:

$$

\lim_{x \to 0}

\frac{x^3\left(\frac{1}{3} + \frac{o(x^3)}{x^3}\right)}

{x^3\left(-\frac{1}{6} + \frac{o(x^3)}{x^3}\right)}

$$

Semplifichiamo $x^3$ e usiamo:

$$

\frac{o(x^3)}{x^3} \to 0

$$
Otteniamo:

$$

\frac{\frac{1}{3}}{-\frac{1}{6}} = -2

$$
Conclusione:
$$

\boxed{-2}

$$

---
#### **2.5 Perché proprio il terzo ordine**

- Se ti fermi al primo ordine, molti termini si cancellano e rimangono solo $o(\cdot)$, quindi non puoi concludere.
    
- Se vai oltre, aggiungi termini **trascurabili** che non cambiano il valore del limite.  

Regola operativa: sviluppi fino a quando compare il **primo termine non nullo** che determina davvero il valore del limite.

---
### **3. Esempio 2**

Calcolare:
$$

\lim_{x \to 0} \frac{\sin(x^2)\left(e^{\sin x}-1-x\right)}{\sin^2 x - x^2}

$$
#### **3.1 Forma indeterminata**

Per $x \to 0$:

- $\sin(x^2) \to 0$
    
- $e^{\sin x}-1-x \to 0$
    
- $\sin^2 x - x^2 \to 0$

Quindi è ancora:
$$

\frac{0}{0}

$$

---
#### **3.2 Primo fattore del numeratore: $\sin(x^2)$**

Dallo sviluppo del seno:
$$

\sin t = t + o(t)

$$
con $t = x^2$:
$$

\sin(x^2) = x^2 + o(x^2)

$$

---
#### **3.3 Secondo fattore del numeratore: $e^{\sin x}-1-x$**

Sviluppo di $e^u$ fino al secondo ordine:
$$

e^u = 1 + u + \frac{u^2}{2} + o(u^2)

$$
con $u = \sin x$:
$$

e^{\sin x} = 1 + \sin x + \frac{\sin^2 x}{2} + o(\sin^2 x)

$$
Quindi:

$$
e^{\sin x} - 1 - x =

(\sin x - x) + \frac{\sin^2 x}{2} + o(\sin^2 x)
$$
Ora usiamo:
$$

\sin x = x - \frac{x^3}{6} + o(x^3)

$$
da cui:
$$

\sin x - x = -\frac{x^3}{6} + o(x^3)

$$

e inoltre:
$$

\sin^2 x = x^2 + o(x^2)

$$
Quindi:
$$

\frac{\sin^2 x}{2} = \frac{x^2}{2} + o(x^2)

$$

Sommiamo i contributi dominanti: il termine in $x^2$ domina su quello in $x^3$.

Otteniamo:
$$

e^{\sin x} - 1 - x = \frac{x^2}{2} + o(x^2)

$$

---
#### **3.4 Denominatore: $\sin^2 x - x^2$**

Sviluppo del seno fino al terzo ordine:

$$

\sin x = x - \frac{x^3}{6} + o(x^3)

$$
Quadrato:
$$

\sin^2 x = \left(x - \frac{x^3}{6} + o(x^3)\right)^2

$$
Tenendo i termini fino a $x^4$:
$$

\sin^2 x = x^2 - \frac{x^4}{3} + o(x^4)

$$
Quindi:
$$

\sin^2 x - x^2 = -\frac{x^4}{3} + o(x^4)

$$

---
#### **3.5 Calcolo del limite**

Sostituiamo tutto:
$$

\lim_{x \to 0}

\frac{\left(x^2 + o(x^2)\right)\left(\frac{x^2}{2} + o(x^2)\right)}

{-\frac{x^4}{3} + o(x^4)}

$$
Il numeratore:

$$
\left(x^2 + o(x^2)\right)\left(\frac{x^2}{2} + o(x^2)\right) =

\frac{x^4}{2} + o(x^4)
$$
Quindi il limite diventa:
$$

\lim_{x \to 0}

\frac{\frac{x^4}{2} + o(x^4)}

{-\frac{x^4}{3} + o(x^4)}

$$

Raccogliamo $x^4$:
$$

\lim_{x \to 0}

\frac{x^4\left(\frac{1}{2} + \frac{o(x^4)}{x^4}\right)}

{x^4\left(-\frac{1}{3} + \frac{o(x^4)}{x^4}\right)}

$$
Semplifichiamo $x^4$ e usiamo:
$$

\frac{o(x^4)}{x^4} \to 0

$$

Otteniamo:
$$

\frac{\frac{1}{2}}{-\frac{1}{3}} = -\frac{3}{2}

$$

Conclusione:
$$

\boxed{-\frac{3}{2}}

$$

---
### **4. Idea chiave da portare a casa**

- sviluppa ogni pezzo fino a quando compare il **termine dominante utile**;
    
- se un termine è di ordine più alto, viene **assorbito** nel piccolo-o;
    
- alla fine il limite è il rapporto dei coefficienti dei primi termini non nulli.