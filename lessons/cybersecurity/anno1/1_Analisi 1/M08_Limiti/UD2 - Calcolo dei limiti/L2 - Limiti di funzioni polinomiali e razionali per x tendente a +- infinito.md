## **Lezione 6: Limiti di funzioni polinomiali e razionali per $x \to \pm\infty$**

### **1. Obiettivo della lezione**

In questa lezione impariamo a calcolare i **limiti delle funzioni razionali** quando la variabile tende a $+\infty$ o a $-\infty$.

Prima di occuparci delle funzioni razionali, però, è indispensabile capire bene **come funzionano i limiti delle funzioni polinomiali all’infinito**, perché la strategia che useremo è sostanzialmente la stessa.

---
### **2. Limiti di funzioni polinomiali per $x \to +\infty$**

Consideriamo il limite

$$

\lim_{x\to +\infty}\left(x^3 - 5x^2 + 1\right)

$$
#### **2.1 Analisi preliminare**

- $x^3 \to +\infty$
    
- $5x^2 \to +\infty$
    
- il termine $+1$ è trascurabile rispetto agli altri due

Siamo quindi davanti a una **forma indeterminata** del tipo

$$

\infty - \infty

$$

Per risolverla dobbiamo capire **quale termine domina**, cioè quale cresce più velocemente.

---
#### **2.2 Termine dominante**

Tra $x^3$ e $x^2$ domina $x^3$, perché a parità di $x$ grande:

$$

x^3 \gg x^2

$$

Per rendere questa idea rigorosa, raccogliamo il termine di grado massimo.

---
#### **2.3 Raccoglimento del termine di grado massimo**
 **$$


x^3 - 5x^2 + 1 =

  

x^3\left(1 - \frac{5}{x} + \frac{1}{x^3}\right)

$$
Ora analizziamo i limiti dei singoli pezzi:

- $x^3 \to +\infty$
    
- $\dfrac{5}{x} \to 0$
    
- $\dfrac{1}{x^3} \to 0$

Quindi la parentesi tende a $1$.

---
### **2.4 Conclusione**

$$
\lim_{x\to +\infty}\left(x^3 - 5x^2 + 1\right)

= (+\infty)\cdot 1

= +\infty

$$

---
#### **2.5 Regola generale per i polinomi**

Per **qualsiasi polinomio**, quando $x \to \pm\infty$, il comportamento è deciso **solo dal termine di grado massimo**.

Tutti gli altri termini diventano trascurabili.

---
### **3. Limiti di funzioni razionali per $x \to +\infty$**

Passiamo ora alle funzioni razionali.

Consideriamo il limite

$$

\lim_{x\to +\infty}\frac{x^3 - 4x - 3}{2x^2 + 5}

$$
#### **3.1 Analisi preliminare**

- il numeratore tende a $+\infty$ (domina $x^3$)
    
- il denominatore tende a $+\infty$ (domina $2x^2$)

Siamo quindi davanti alla forma indeterminata

$$
\frac{\infty}{\infty}
$$

---
#### **3.2 Strategia**

Si procede così:

1. si raccoglie il termine di grado massimo **al numeratore**
    
2. si raccoglie il termine di grado massimo **al denominatore**
    
3. si semplifica
    
4. si studiano i limiti dei pezzi rimasti

---
#### **3.3 Raccoglimento**

Numeratore:
$$
x^3 - 4x - 3 =

x^3\left(1 - \frac{4}{x^2} - \frac{3}{x^3}\right)
$$

Denominatore:
$$
2x^2 + 5 =

2x^2\left(1 + \frac{5}{2x^2}\right)
$$

---
#### **3.4 Semplificazione**

$$

\frac{x^3}{2x^2} = \frac{x}{2}

$$

Quindi il limite diventa

$$

\frac{x}{2}

\cdot

\frac{1 - \frac{4}{x^2} - \frac{3}{x^3}}{1 + \frac{5}{2x^2}}

$$
---
#### **3.5 Limite dei singoli pezzi**

- $\dfrac{x}{2} \to +\infty$
    
- $\dfrac{4}{x^2} \to 0$
    
- $\dfrac{3}{x^3} \to 0$
    
- $\dfrac{5}{2x^2} \to 0$
    

La frazione tende a $1$. E per x tendente a infinito, $\frac{x}{2} \cdot 1 = +infinito$

---
#### **3.6 Conclusione**
$$
\lim_{x\to +\infty}\frac{x^3 - 4x - 3}{2x^2 + 5} =

+\infty

$$

---
### **4. Secondo esempio: denominatore di grado maggiore**

Consideriamo ora

$$

\lim_{x\to -\infty}\frac{x^3 - 4x}{5x^6 - 1}

$$
#### **4.1 Analisi preliminare**

- $x^3 \to -\infty$ (potenza dispari)
    
- $x^6 \to +\infty$ (potenza pari)

Siamo ancora in una forma $\dfrac{\infty}{\infty}$, ma ora il **denominatore cresce molto più velocemente**.

---
#### **4.2 Raccoglimento**

$$
\frac{x^3 - 4x}{5x^6 - 1} =

\frac{x^3\left(1 - \frac{4}{x^2}\right)}{5x^6\left(1 - \frac{1}{5x^6}\right)}
$$

Semplificando:

$$

\frac{x^3}{5x^6} = \frac{1}{5x^3}

$$

---
#### **4.3 Limite**

- $\dfrac{1}{5x^3} \to 0$
    
- le parentesi tendono a $1$

Quindi

$$

\lim_{x\to -\infty}\frac{x^3 - 4x}{5x^6 - 1} = 0

$$

---
### **5. Terzo esempio: stesso grado**

Consideriamo

$$

\lim_{x\to -\infty}\frac{2x^2 - x}{x^2 + 1}

$$
#### **5.1 Raccoglimento**

$$
\frac{2x^2 - x}{x^2 + 1} =

\frac{x^2\left(2 - \frac{1}{x}\right)}{x^2\left(1 + \frac{1}{x^2}\right)}
$$

Semplificando:

$$

\frac{2 - \frac{1}{x}}{1 + \frac{1}{x^2}}

$$
---
#### **5.2 Limite**

- $\dfrac{1}{x} \to 0$
    
- $\dfrac{1}{x^2} \to 0$

Quindi

$$

\lim_{x\to -\infty}\frac{2x^2 - x}{x^2 + 1} = 2

$$

---
### **6. Regola finale: confronto dei gradi**

Sia

- $n = \deg(P)$
    
- $m = \deg(Q)$

Allora:
#### **Caso 1: $n > m$**

Il numeratore cresce più velocemente.

$$

\lim_{x\to \pm\infty}\frac{P(x)}{Q(x)} = \pm\infty

$$

(il segno dipende dai coefficienti e dal verso dell’infinito)

---
#### **Caso 2: $n < m$**

Il denominatore cresce più velocemente.

$$

\lim_{x\to \pm\infty}\frac{P(x)}{Q(x)} = 0

$$

---
### **Caso 3: $n = m$**

Il limite è il **rapporto dei coefficienti principali**:

$$
\lim_{x\to \pm\infty}\frac{a_n x^n + \dots}{b_n x^n + \dots} =

\frac{a_n}{b_n}

$$

---
#### **7. Chiusura**

Prima ancora di fare i conti, **guardare i gradi di numeratore e denominatore** permette spesso di prevedere subito il risultato del limite: infinito, zero oppure numero finito.

Nella prossima lezione estenderemo questo studio ai limiti all’infinito che coinvolgono **esponenziali, logaritmi e funzioni miste**.