## **Lezione 11: Continuita e derivabilita**

### **Parametri per raccordo $C^1$ e caso “derivabile ma $\lim f’(x)$ non esiste”**

### **1. Obiettivo della lezione**

In questa lezione compaiono due idee molto importanti:

1. **Problemi con parametri**: scegliere $A,B$ affinche una funzione definita a tratti sia **continua e derivabile** in un punto di raccordo.
    
2. **Caso sottile**: una funzione puo essere **derivabile** in $x_0$ anche se **non esiste** il limite della derivata $f’(x)$ per $x\to x_0$.

---
## **Parte A: Trovare parametri per continuita e derivabilita in un punto**

### **2. Esercizio 1**

Trovare $A,B$ affinche la funzione

$$

f(x)=

\begin{cases}

A\sin(2x)-4 & x<0 \

B(x-1)+e^x & x\ge 0

\end{cases}

$$

sia **continua** e **derivabile** in $x=0$.

---
### **3. Condizione di continuita in $x=0$**

Per continuita in $0$ serve:

$$

\lim_{x\to 0^-} f(x)=f(0)=\lim_{x\to 0^+} f(x)

$$
#### **3.1 Limite sinistro**

Per $x<0$:

$$

\lim_{x\to 0^-}\left(A\sin(2x)-4\right)

= A\sin(0)-4=-4

$$
#### **3.2 Limite destro e valore in $0$**

Per $x\ge 0$:

$$

f(0)=B(0-1)+e^0=-B+1

$$

e quindi anche:

$$

\lim_{x\to 0^+}\left(B(x-1)+e^x\right)=-B+1

$$

Imponiamo l’uguaglianza:
$$

-4=-B+1

$$

Da cui:
$$

B=5

$$

Risultato parziale:

$$

\boxed{B=5}

$$

---
### **4. Condizione di derivabilita in $x=0$**

Una volta garantita la continuita, per derivabilita imponiamo l’uguaglianza delle derivate laterali:

$$

\lim_{x\to 0^-} f’(x)=\lim_{x\to 0^+} f’(x)

$$
#### **4.1 Derivata a sinistra**

Per $x<0$:

$$

f(x)=A\sin(2x)-4

$$
Deriviamo con catena:
$$

\frac{d}{dx}\sin(2x)=\cos(2x)\cdot 2

$$
Quindi:

$$

f’(x)=A\cdot 2\cos(2x)=2A\cos(2x)

$$

Limite in $0^-$:
$$

\lim_{x\to 0^-}2A\cos(2x)=2A\cos(0)=2A

$$
#### **4.2 Derivata a destra**

Per $x\ge 0$:
$$

f(x)=B(x-1)+e^x

$$

Derivata:
$$

f’(x)=B+e^x

$$

Limite in $0^+$:

$$

\lim_{x\to 0^+}(B+e^x)=B+1

$$

Imponiamo derivabilita:

$$

2A=B+1

$$

Sostituendo $B=5$:

$$

2A=6 \Rightarrow A=3

$$

Conclusione:
$$

\boxed{A=3,\quad B=5}

$$

**Idea da portarsi a casa (pattern d’esame)**

Per raccordo in $x_0$:

- continuita: valori laterali uguali (e uguali al valore nel punto)
    
- derivabilita: derivate laterali uguali

---
## **Parte B: Derivabile in $0$ ma $\lim_{x\to 0}f’(x)$ non esiste**

### **5. Esercizio 2**

Data:

$$

f(x)=

\begin{cases}

x^2\sin\left(\frac{1}{x}\right) & x\neq 0 \\\\

0 & x=0

\end{cases}

$$

Dopo aver verificato che e continua, mostrare che e derivabile in $0$ anche se **non esiste**:

$$

\lim_{x\to 0} f’(x)

$$

---
### **6. Continuita in $x=0$**

Per $x\neq 0$:
$$

f(x)=x^2\sin\left(\frac{1}{x}\right)

$$

Sappiamo che:
$$

-1\le \sin\left(\frac{1}{x}\right)\le 1

$$

Moltiplicando per $x^2\ge 0$:
$$

-x^2 \le x^2\sin\left(\frac{1}{x}\right)\le x^2

$$

Quando $x\to 0$:
$$

-x^2\to 0,\qquad x^2\to 0

$$

Quindi per confronto:
$$

\lim_{x\to 0}x^2\sin\left(\frac{1}{x}\right)=0

$$

E poiche:
$$

f(0)=0

$$

concludiamo:
$$

\boxed{f \text{ e continua in } 0}

$$

---
### **7. La derivata per $x\neq 0$ e perche il suo limite non esiste**

Per $x\neq 0$ deriviamo:
$$

f(x)=x^2\sin\left(\frac{1}{x}\right)

$$

E un prodotto: $x^2$ e $\sin(1/x)$.

$$

f’(x)=2x\sin\left(\frac{1}{x}\right)+x^2\cdot \frac{d}{dx}\left(\sin\left(\frac{1}{x}\right)\right)

$$

Derivata composta:
$$

\frac{d}{dx}\sin\left(\frac{1}{x}\right)=\cos\left(\frac{1}{x}\right)\cdot \frac{d}{dx}\left(\frac{1}{x}\right)

$$

e:
$$

\frac{d}{dx}\left(\frac{1}{x}\right)=-\frac{1}{x^2}

$$

Quindi:
$$

\frac{d}{dx}\sin\left(\frac{1}{x}\right)=-\frac{\cos(1/x)}{x^2}

$$

Sostituiamo:

$$

f’(x)=2x\sin\left(\frac{1}{x}\right)+x^2\left(-\frac{\cos(1/x)}{x^2}\right)

$$
Semplificando:

$$

f’(x)=2x\sin\left(\frac{1}{x}\right)-\cos\left(\frac{1}{x}\right)

$$

Ora guardiamo il limite per $x\to 0$:

- il primo pezzo tende a $0$ perche $2x\to 0$ e il seno e limitato
    
- il secondo pezzo **non ha limite** perche $\cos(1/x)$ oscilla tra $-1$ e $1$ senza stabilizzarsi quando $1/x\to \pm\infty$

Quindi:

$$

\boxed{\lim_{x\to 0} f’(x) \text{ non esiste}}

$$

Punto cruciale: **questo NON decide la derivabilita in $0$**.

---
### **8. Derivabilita in $0$ con il rapporto incrementale**

Per definizione:

$$

f’(0)=\lim_{h\to 0}\frac{f(h)-f(0)}{h}

$$

Qui $f(0)=0$ e per $h\neq 0$:

$$

f(h)=h^2\sin\left(\frac{1}{h}\right)

$$

Quindi:
$$

f’(0)=\lim_{h\to 0}\frac{h^2\sin(1/h)}{h}

=\lim_{h\to 0}h\sin\left(\frac{1}{h}\right)

$$

Ora:

- $h\to 0$
    
- $\sin(1/h)$ e limitato tra $-1$ e $1$
  
Quindi il prodotto tende a $0$:

$$

\boxed{f’(0)=0}

$$

Conclusione:

$$

\boxed{f \text{ e derivabile in } 0,\ \text{e } f’(0)=0}

$$

---
## **9. Strategia generale riassunta**

Supponi che una funzione sia derivabile a sinistra e a destra di $x_0$ e vuoi capire cosa succede in $x_0$.
### **Step 1: continuita**

Se non e continua in $x_0$:

$$

\Rightarrow \text{non e derivabile}

$$
### **Step 2: limiti delle derivate laterali**

Calcola:

$$

\lim_{x\to x_0^-} f’(x),\qquad \lim_{x\to x_0^+} f’(x)

$$

- se esistono, sono finiti e uguali $\Rightarrow$ derivabile e quel valore e $f’(x_0)$
    
- se sono diversi o infiniti $\Rightarrow$ non derivabile (angolo, cuspide, tangente verticale, ecc.)

### **Step 3: caso raro**

Se questi limiti **non esistono** (oscillazioni o altre patologie), non puoi concludere nulla.

Devi tornare alla definizione:

$$

f’(x_0)=\lim_{h\to 0}\frac{f(x_0+h)-f(x_0)}{h}

$$
