## **Lezione 5: Derivata del reciproco e del quoziente**

### **1. Obiettivo della lezione**

In questa lezione completiamo il quadro delle **regole di derivazione** studiando:

- la derivata del **reciproco** di una funzione
    
- la derivata del **quoziente** tra due funzioni

Queste regole permettono di derivare rapidamente moltissime funzioni senza ricorrere al limite del rapporto incrementale.

---
### **2. Derivata del reciproco di una funzione**

Sia $f(x)$ una funzione **derivabile** in un certo intervallo e tale che:

$$

f(x) \neq 0

$$

Vogliamo calcolare la derivata della funzione:

$$

g(x) = \frac{1}{f(x)}

$$

Vale la seguente regola fondamentale:

$$
\frac{d}{dx}\left(\frac{1}{f(x)}\right) =

  

-\frac{f’(x)}{[f(x)]^2}

$$

---
### **3. Dimostrazione della regola del reciproco**

Partiamo dalla definizione di derivata:

$$

g’(x) = \lim_{h \to 0}

\frac{g(x+h) - g(x)}{h}

$$
Nel nostro caso:
$$

g(x+h) = \frac{1}{f(x+h)}, \qquad

g(x) = \frac{1}{f(x)}

$$

Quindi:
$$

g’(x) =

\lim_{h \to 0}

\frac{\frac{1}{f(x+h)} - \frac{1}{f(x)}}{h}

$$

Mettiamo a denominatore comune:

$$

\frac{f(x) - f(x+h)}{h \cdot f(x+h) \cdot f(x)}

$$

Scriviamo ora l’espressione come prodotto di due fattori:

$$

\left(

\frac{f(x) - f(x+h)}{h}

\right)

\cdot

\frac{1}{f(x+h),f(x)}

$$

Analizziamo i due pezzi separatamente.

Il primo fattore è:

$$
\frac{f(x) - f(x+h)}{h} =

\frac{f(x+h) - f(x)}{h}
$$

Quando $h \to 0$, questo tende a:

$$

- f’(x)
    
    $$
  
Il secondo fattore, poiché $f$ è derivabile (e quindi continua), soddisfa:

$$

f(x+h) \to f(x)

$$
quindi:

$$

\frac{1}{f(x+h),f(x)} \to \frac{1}{[f(x)]^2}

$$

Moltiplicando i limiti otteniamo:

$$

g’(x) = -\frac{f’(x)}{[f(x)]^2}

$$

---
### **4. Condizione di validità**

La formula ha senso **solo nei punti in cui**:

$$

f(x) \neq 0

$$

poiché il reciproco $\frac{1}{f(x)}$ non è definito se $f(x)=0$.

---
### **5. Esempi sul reciproco**

#### **Esempio 1**

Calcolare la derivata di:
$$

f(x) = \frac{1}{\cos x}

$$
Usiamo la regola:
$$

f’(x) = -\frac{-\sin x}{\cos^2 x}

$$
cioè:
$$

f’(x) = \frac{\sin x}{\cos^2 x}

$$

---
#### **Esempio 2**

Calcolare la derivata di:
$$

f(x) = \frac{1}{\ln x}

$$
Applicando la regola:
$$

f’(x) = -\frac{\frac{1}{x}}{(\ln x)^2}

$$
quindi:
$$

f’(x) = -\frac{1}{x(\ln x)^2}

$$
con la condizione:
$$

x > 0

$$

---
### **6. Derivata del quoziente di due funzioni**

Siano $p(x)$ e $q(x)$ due funzioni **derivabili** e tali che:

$$

q(x) \neq 0

$$
Consideriamo la funzione:
$$

f(x) = \frac{p(x)}{q(x)}

$$

La derivata è data dalla **regola del quoziente**:

$$
f’(x) =
\frac{p’(x) \cdot q(x) - p(x) \cdot q’(x)}{[q(x)]^2}

$$

---
### **7. Esempi sul quoziente**

#### **Esempio 1**

Calcolare la derivata di:

$$

f(x) = \frac{\sin x}{\cos x}

$$

Identifichiamo:

- $p(x) = \sin x \Rightarrow p’(x)=\cos x$
    
- $q(x) = \cos x \Rightarrow q’(x)=-\sin x$

Applichiamo la formula:
$$
f’(x) =

\frac{\cos x \cdot \cos x - \sin x \cdot (-\sin x)}{\cos^2 x}
$$

cioè:
$$
f’(x) =

\frac{\cos^2 x + \sin^2 x}{\cos^2 x}
$$

Usando l’identità fondamentale:
$$

\sin^2 x + \cos^2 x = 1

$$

otteniamo:
$$

f’(x) = \frac{1}{\cos^2 x}

$$
Quindi:
$$

\frac{d}{dx}(\tan x) = \frac{1}{\cos^2 x}

$$

---
#### **Esempio 2**

Calcolare la derivata di:
$$

f(x) = \frac{e^x}{x}

$$
Abbiamo:

- $p(x)=e^x \Rightarrow p’(x)=e^x$
    
- $q(x)=x \Rightarrow q’(x)=1$
  
Applicando la formula:

$$f’(x) =

\frac{e^x \cdot x - e^x \cdot 1}{x^2}
$$

Raccogliendo $e^x$:

$$
f’(x) =

\frac{e^x(x-1)}{x^2}
$$

---
### **8. Perché la regola del quoziente è vera**

Scriviamo il quoziente come prodotto:

$$

\frac{p(x)}{q(x)} = p(x)\cdot \frac{1}{q(x)}

$$

Deriviamo usando la **regola del prodotto**:
$$ = f’(x)

p’(x)\cdot\frac{1}{q(x)}

+

p(x)\cdot\frac{d}{dx} \cdot\left(\frac{1}{q(x)}\right)

$$

Ora usiamo la regola del reciproco:

$$
\frac{d}{dx}\left(\frac{1}{q(x)}\right) =

-\frac{q’(x)}{[q(x)]^2}
$$

Sostituendo:

$$
f’(x) =\frac{p’(x)}{q(x)} =

\frac{p(x),q’(x)}{[q(x)]^2}

$$

Portando a denominatore comune:
$$
f’(x) =

\frac{p’(x) \cdot q(x) - p(x) \cdot q’(x)}{[q(x)]^2}
$$

---
### **9. Anticipazione**

👉 **Nella prossima lezione** affronteremo uno degli strumenti più importanti di tutta l’Analisi 1:

**la derivazione delle funzioni composte** (regola della catena).