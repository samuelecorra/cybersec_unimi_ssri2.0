## **Lezione 6: Integrazione per sostituzione**

---
### **1. Idea chiave**

La sostituzione serve a trasformare un integrale “brutto” in uno “standard”, riconoscendo una **funzione composta**.

Se vedi qualcosa del tipo:

- $f(g(x))$
    
- e vicino compare (o puoi far comparire) $g’(x)$

allora puoi mettere:
$$

y=g(x)

\qquad\Rightarrow\qquad

dy=g’(x),dx

$$

e l’integrale diventa un integrale in $y$.

---
### **2. Procedura standard**

#### **Caso A: integrale indefinito**

1. scegli la parte “scomoda” e poni:
$$

y=g(x)

$$

2. calcoli il differenziale:
$$

dy=g’(x),dx

$$

3. sostituisci tutto e integri in $y$
    
4. alla fine **torni a $x$** sostituendo $y=g(x)$.

---
#### **Caso B: integrale definito**

Stessa procedura, ma **in più** trasformi gli estremi:

se $y=g(x)$ allora

$$

x=a \Rightarrow y=g(a),

\qquad

x=b \Rightarrow y=g(b)

$$

così non devi “tornare a $x$” alla fine.

---
### **3. Esempi della lezione**

---
#### **3.1 $\int \sin(e^x),e^x,dx$**

Sostituzione naturale:
$$

y=e^x

\qquad\Rightarrow\qquad

dy=e^x,dx

$$

Allora:
$$

\int \sin(e^x),e^x,dx=\int \sin(y),dy=-\cos(y)+C

$$

Ritorno a $x$:
$$

-\cos(e^x)+C

$$

---
#### **3.2 $\int \cos x;\sin(\sin x),dx$**

La parte interna è $\sin x$:
$$

y=\sin x

\qquad\Rightarrow\qquad

dy=\cos x,dx

$$
Quindi:
$$

\int \cos x;\sin(\sin x),dx=\int \sin(y),dy=-\cos(y)+C

$$
Ritorno a $x$:
$$

-\cos(\sin x)+C

$$

---
#### **3.3 $\int \frac{e^x}{1+e^{2x}},dx$**

Riscrivo $e^{2x}=(e^x)^2$:
$$

\int \frac{e^x}{1+(e^x)^2},dx

$$

Sostituisco:
$$

y=e^x

\qquad\Rightarrow\qquad

dy=e^x,dx

$$
Diventa:

$$

\int \frac{1}{1+y^2},dy=\arctan(y)+C

$$
Ritorno a $x$:
$$

\arctan(e^x)+C

$$

---
#### **3.4 Integrale definito: $\int_{0}^{\sqrt{\pi}} x\cos(x^2),dx$**

Sostituzione:
$$

y=x^2

\qquad\Rightarrow\qquad

dy=2x,dx

\qquad\Rightarrow\qquad

x,dx=\frac{1}{2}dy

$$
Trasformo gli estremi:
$$

x=0 \Rightarrow y=0

\qquad

x=\sqrt{\pi} \Rightarrow y=\pi

$$
Quindi:

$$
\int_{0}^{\sqrt{\pi}} x\cos(x^2)\,dx
=
\frac12 \int_{0}^{\pi} \cos(y)\,dy
=
\frac12 \left[\sin y\right]_{0}^{\pi}.
$$

Ma:
$$

\sin\pi-\sin0=0-0=0

$$
Risultato:
$$

0

$$

---
#### **3.5 $\int \frac{1}{x(\ln x+1)},dx$**

Metto in evidenza la struttura “derivata del logaritmo”:

$$

\frac{1}{x(\ln x+1)}=\frac{1}{\ln x+1}\cdot \frac{1}{x}

$$
Sostituzione:
$$

y=\ln x+1

\qquad\Rightarrow\qquad

dy=\frac{1}{x},dx

$$
Allora:
$$

\int \frac{1}{x(\ln x+1)},dx=\int \frac{1}{y},dy=\ln|y|+C

$$
Ritorno a $x$:
$$

\ln|\ln x+1|+C

$$

---
### **4. Caso “inverso”: quando poni $x=\varphi(y)$**

Questo succede quando l’espressione suggerisce una sostituzione trigonometrica (tipico: $\sqrt{1-x^2}$, $\sqrt{a^2-x^2}$, ecc.).

Esempio della lezione:
$$

\int \sqrt{1-x^2},dx

$$

Suggerimento:
$$

x=\sin y

\qquad\Rightarrow\qquad

dx=\cos y,dy

$$
Allora:
$$

\sqrt{1-x^2}=\sqrt{1-\sin^2 y}=\sqrt{\cos^2 y}=|\cos y|

$$

Nella pratica (con scelta corretta dell’intervallo) si lavora con $\cos y$ e diventa:

$$
\int \sqrt{1-x^2},dx =

  

\int \cos y \cdot \cos y,dy =

  

\int \cos^2 y,dy

$$

E questo è un integrale già noto (lo hai visto nella lezione sugli integrali ciclici / oppure con formule goniometriche).

---
### **5. Checklist mentale per riconoscerla al volo**

Quando guardi un integrale, chiediti:

1. c’è una funzione “dentro” un’altra?
    
2. vicino compare la derivata della parte interna (o ci arrivo con un fattore costante)?
    
3. se sì, metto $y=$ “parte interna” e sostituisco.