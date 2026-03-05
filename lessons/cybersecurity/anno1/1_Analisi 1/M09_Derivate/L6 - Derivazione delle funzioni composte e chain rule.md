## **Lezione 6: Derivazione delle funzioni composte**

### **La regola della catena (chain rule)**

### **1. Obiettivo della lezione**

In questa lezione studiamo come derivare le **funzioni composte**, introducendo una delle regole più importanti di tutta l’Analisi 1: **la regola della catena** (_chain rule_).

È la regola che permette di derivare funzioni in cui una funzione è **inserita come argomento di un’altra**.

---
### **2. Idea fondamentale della regola della catena**

Siano:
$$

y = f(g(x))

$$

cioè una funzione composta, dove:

- $g(x)$ è la **funzione interna**
    
- $f(x)$ è la **funzione esterna**

Allora vale la **regola della catena**:

$$

\frac{dy}{dx} = f’(g(x)) \cdot g’(x)

$$

In parole semplici:

> **la derivata della funzione composta è il prodotto tra**

- > **la derivata della funzione esterna**, calcolata nella funzione interna
    
- > **la derivata della funzione interna**

---
### **3. Notazione alternativa**

Talvolta la derivata viene indicata con il simbolo:

$$

\frac{d}{dx}

$$

che è solo un **modo alternativo** di scrivere la derivata.

Scrivere $\dfrac{d}{dx}f(x)$ significa esattamente $f’(x)$.

---
### **4. Primo esempio: $y = \sin(x^2)$**

La funzione è composta perché:

- funzione esterna: $f(x) = \sin x$
    
- funzione interna: $g(x) = x^2$
  
Applichiamo la regola della catena.

**Derivata della funzione esterna**

La derivata di $\sin x$ è $\cos x$, ma l’argomento non è $x$, bensì $x^2$:

$$

f’(g(x)) = \cos(x^2)

$$

**Derivata della funzione interna**

$$

g’(x) = 2x

$$

**Derivata finale**

$$

y’ = \cos(x^2)\cdot 2x

$$

---
### **5. Secondo esempio: $y = \ln(\sin x)$**

Qui:

- funzione esterna: $f(x) = \ln x$
    
- funzione interna: $g(x) = \sin x$

**Derivata della funzione esterna**

La derivata di $\ln x$ è $\dfrac{1}{x}$, quindi:

$$

f’(g(x)) = \frac{1}{\sin x}

$$

**Derivata della funzione interna**

$$

g’(x) = \cos x

$$

**Derivata finale**

$$

y’ = \frac{1}{\sin x}\cdot \cos x

$$
cioè:
$$

y’ = \frac{\cos x}{\sin x}

$$

che è la **cotangente**.

---
### **6. Terzo esempio: $y = e^{x^{2013}}$**

Qui:

- funzione esterna: $f(x) = e^x$
    
- funzione interna: $g(x) = x^{2013}$
  
**Derivata della funzione esterna**

L’esponenziale è uguale alla sua derivata:

$$

f’(g(x)) = e^{x^{2013}}

$$

**Derivata della funzione interna**

$$

g’(x) = 2013x^{2012}

$$

**Derivata finale**

$$

y’ = e^{x^{2013}} \cdot 2013x^{2012}

$$

---
### **7. Funzioni composte con prodotti interni**

Consideriamo:

$$

y = \sin(x\tan x)

$$
Qui:

- funzione esterna: $f(x) = \sin x$
    
- funzione interna: $g(x) = x\tan x$

**Derivata della funzione esterna**

$$

f’(g(x)) = \cos(x\tan x)

$$

**Derivata della funzione interna**

$g(x) = x\tan x$ è un **prodotto**, quindi:

$$

g’(x) = 1\cdot \tan x + x \cdot \frac{1}{\cos^2 x}

$$

**Derivata finale**

$$

y’ = \cos(x\tan x)\left(\tan x + \frac{x}{\cos^2 x}\right)

$$

Qui vediamo una situazione molto tipica:

**la regola della catena si combina con altre regole di derivazione**, in questo caso quella del prodotto.

---
### **8. Composizione di più funzioni**

La regola della catena **non vale solo per due funzioni**, ma si estende naturalmente a tre, quattro o più funzioni composte.

Consideriamo:

$$

y = \sin\left(e^{x^2}\right)

$$

Abbiamo **tre livelli**:

1. funzione più esterna: $\sin$
    
2. funzione intermedia: $e^x$
    
3. funzione più interna: $x^2$

Si procede **dall’esterno verso l’interno**, moltiplicando tutte le derivate.

**Derivata del seno**

$$

\cos\left(e^{x^2}\right)

$$

**Derivata dell’esponenziale**

$$

e^{x^2}

$$

**Derivata di $x^2$**

$$

2x

$$

**Derivata finale**

$$

y’ = \cos\left(e^{x^2}\right)\cdot e^{x^2}\cdot 2x

$$

---
### **9. Idea chiave da ricordare**

Per derivare una funzione composta:

1. individua **la funzione più esterna**
    
2. derivala **lasciando invariato l’argomento**
    
3. moltiplica per la derivata della funzione interna
    
4. se la funzione interna è a sua volta composta, **ripeti il procedimento**

È una catena di derivate, una dopo l’altra.

---
### **10. Anticipazione**

👉 **Nella prossima lezione** affronteremo:

- esercizi di riepilogo
    
- funzioni composte particolarmente articolate
    
- combinazioni di più regole di derivazione