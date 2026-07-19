## **Lezione 8: Integrali di funzioni razionali “semplici”**

---
### **1. Obiettivo della lezione**

Una **funzione razionale** è una funzione del tipo:
$$
\frac{P(x)}{Q(x)}
$$

dove $P(x)$ e $Q(x)$ sono polinomi.

In questa lezione non fai ancora “il metodo generale”, ma impari i **mattoni base** che poi userai nel metodo completo.

---
### **2. Caso A: numeratore = derivata del denominatore**

Se riconosci:
$$
\int \frac{f'(x)}{f(x)}\,dx
$$
allora:
$$
\int \frac{f'(x)}{f(x)}\,dx=\ln|f(x)|+C
$$
#### **Esempio**

$$
\int \frac{2x}{x^2-5}\,dx
$$

Perché: $f(x)=x^2-5 \Rightarrow f'(x)=2x$.

Risultato:  
$$
\ln|x^2-5|+C
$$

---
#### **Quando manca una costante**

$$
\int \frac{x^3}{x^4+1}\,dx
$$
Qui $f(x)=x^4+1 \Rightarrow f'(x)=4x^3$.

Aggiusto:  

$$
\int \frac{x^3}{x^4+1}\,dx =
\frac14\int \frac{4x^3}{x^4+1}\,dx =
\frac14\ln(x^4+1)+C
$$

_(senza modulo perché $x^4+1>0$ sempre)._

---
### **3. Caso B: costante sopra, primo grado sotto**

Forma:  
$$
\int \frac{k}{ax+b}\,dx
$$

Idea: trasformare il numeratore in $a$.

Risultato:  
$$
\int \frac{k}{ax+b}\,dx=\frac{k}{a}\ln|ax+b|+C
$$
#### **Esempio**  

$$
\int \frac{3}{2x-1}\,dx =
\frac{3}{2}\ln|2x-1|+C
$$

---
### **4. Caso C: costante sopra, quadrato di un binomio sotto**

Forma:
$$
\int \frac{k}{(ax+b)^2}\,dx
$$

Riscrivi come potenza negativa:
$$
\frac{1}{(ax+b)^2}=(ax+b)^{-2}
$$

e usi la regola:

$$
\int f(x)^n f'(x)\,dx=\frac{f(x)^{n+1}}{n+1}+C\qquad(n\neq -1)
$$ 
#### **Esempio**

$$
\int \frac{5}{(2x-1)^2}\,dx
$$  
Porto fuori $5$ e faccio comparire il $2$:

$$
5\int (2x-1)^{-2}dx =
\frac52\int 2(2x-1)^{-2}dx
$$
  
Ora applico la regola con $n=-2$:
 
 $$
\frac52\cdot \frac{(2x-1)^{-1}}{-1}+C =
-\frac52\cdot \frac{1}{2x-1}+C
$$

---
### **5. Caso D: primo grado sopra, secondo grado sotto con $\Delta<0$**

Qui $Q(x)$ **non si fattorizza in reali**, tipicamente del tipo:

$$
ax^2+bx+c\quad \text{con}\quad \Delta=b^2-4ac<0
$$

  Strategia: provi a scomporre in somma di due pezzi che portano a:

1. **logaritmo** (se riesci a creare $Q'(x)$ sopra)

$$
\int \frac{Q'(x)}{Q(x)}dx=\ln|Q(x)|+C
$$

2. **arctan** (quando arrivi a forma “$1+(\cdot)^2$”)

$$
\int \frac{u'(x)}{1+u(x)^2}\,dx=\arctan(u(x))+C
$$

---
#### **Esempio 1**

$$
\int \frac{3x+1}{x^2+1}\,dx
$$
Spacco:
$$
\int \frac{3x}{x^2+1}\,dx+\int \frac{1}{x^2+1}\,dx
$$

Primo pezzo (logaritmo): aggiusto per avere $2x$:

$$
\int \frac{3x}{x^2+1}\,dx =
\frac32\int \frac{2x}{x^2+1}\,dx =
\frac32\ln(x^2+1)
$$

Secondo pezzo (arctan):
$$
\int \frac{1}{x^2+1}\,dx=\arctan(x)
$$
Risultato finale:

$$
\frac32\ln(x^2+1)+\arctan(x)+C
$$

---
#### **Esempio 2 “più complesso” (idea chiave: completamento del quadrato)**

Se hai un denominatore tipo:
$$
9x^2+6x+2
$$
completi il quadrato:
$$
9x^2+6x+2=(3x+1)^2+1
$$
Poi sistemi il numeratore per creare:

- un pezzo $Q'(x)$ per il logaritmo
    
- un pezzo $u'(x)$ su $1+u^2$ per l’arctan

Ed è normalissimo che nel risultato compaiano **sia $\ln(\cdot)$ sia $\arctan(\cdot)$**.

---
### **6. Mini-check mentale rapidissimo**

Quando vedi una razionale, chiediti in quest’ordine:
#### **(1) Sopra c’è (quasi) la derivata di sotto?**

→ **logaritmo**
#### **(2) Sotto è un primo grado?**

→ **logaritmo**
#### **(3) Sotto è un binomio al quadrato?**

→ **potenza negativa** (tipo $1/(ax+b)$)  
#### **(4) Sotto è un secondo grado con $\Delta<0$?**

→ **completa il quadrato** e punta a **logaritmo + arctan**