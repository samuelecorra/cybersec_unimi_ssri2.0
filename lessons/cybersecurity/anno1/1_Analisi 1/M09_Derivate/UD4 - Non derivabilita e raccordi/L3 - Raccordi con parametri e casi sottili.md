# **M9 UD4 Lezione 3 - Raccordi con parametri e casi sottili**

### **Parametri per raccordo $C^1$ e caso “derivabile ma $\lim f'(x)$ non esiste”**

### **1. Obiettivo della lezione**

In questa lezione compaiono due idee molto importanti:

1. **Problemi con parametri**: scegliere $A,B$ affinché una funzione definita a tratti sia **continua e derivabile** in un punto di raccordo.
    
2. **Caso sottile**: una funzione può essere **derivabile** in $x_0$ anche se **non esiste** il limite della derivata $f'(x)$ per $x\to x_0$.

---
## **Parte A: Trovare parametri per continuità e derivabilità in un punto**

### **2. Esercizio 1**

Trovare $A,B$ affinché la funzione

$$
f(x)=
\begin{cases}
A\sin(2x)-4 & x<0 \\
B(x-1)+e^x & x\ge 0
\end{cases}
$$

sia **continua** e **derivabile** in $x=0$.

---
### **3. Condizione di continuità in $x=0$**

Per continuità in $0$ serve:

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
### **4. Condizione di derivabilità in $x=0$**

Una volta garantita la continuità, per derivabilità imponiamo l’uguaglianza dei limiti laterali della derivata:

$$
\lim_{x\to 0^-} f'(x)=\lim_{x\to 0^+} f'(x)
$$

> ⚠️ Questo passaggio è legittimo **solo dopo** aver imposto la continuità, e poggia sul teorema del limite della derivata (UD5, Lezione 3): per una funzione continua nel punto, se i limiti laterali di $f'$ esistono finiti allora coincidono con le derivate laterali. Imporre l'uguaglianza dei limiti di $f'$ **senza** continuità è un errore classico: le due condizioni vanno in quest'ordine.
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
f'(x)=A\cdot 2\cos(2x)=2A\cos(2x)
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
f'(x)=B+e^x
$$

Limite in $0^+$:

$$
\lim_{x\to 0^+}(B+e^x)=B+1
$$

Imponiamo derivabilità:

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

- continuità: valori laterali uguali (e uguali al valore nel punto)
    
- derivabilità: derivate laterali uguali

---
## **Parte B: Derivabile in $0$ ma $\lim_{x\to 0}f'(x)$ non esiste**

### **5. Esercizio 2**

Data:

$$
f(x)=
\begin{cases}
x^2\sin\left(\frac{1}{x}\right) & x\neq 0 \\
0 & x=0
\end{cases}
$$

Dopo aver verificato che è continua, mostrare che è derivabile in $0$ anche se **non esiste**:

$$
\lim_{x\to 0} f'(x)
$$

---
### **6. Continuità in $x=0$**

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

E poiché:
$$
f(0)=0
$$

concludiamo:
$$
\boxed{f \text{ è continua in } 0}
$$

---
### **7. La derivata per $x\neq 0$ e perché il suo limite non esiste**

Per $x\neq 0$ deriviamo:
$$
f(x)=x^2\sin\left(\frac{1}{x}\right)
$$

È un prodotto: $x^2$ e $\sin(1/x)$.

$$
f'(x)=2x\sin\left(\frac{1}{x}\right)+x^2\cdot \frac{d}{dx}\left(\sin\left(\frac{1}{x}\right)\right)
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
f'(x)=2x\sin\left(\frac{1}{x}\right)+x^2\left(-\frac{\cos(1/x)}{x^2}\right)
$$
Semplificando:

$$
f'(x)=2x\sin\left(\frac{1}{x}\right)-\cos\left(\frac{1}{x}\right)
$$

Ora guardiamo il limite per $x\to 0$:

- il primo pezzo tende a $0$ perché $2x\to 0$ e il seno è limitato
    
- il secondo pezzo **non ha limite** perché $\cos(1/x)$ oscilla tra $-1$ e $1$ senza stabilizzarsi quando $1/x\to \pm\infty$

Quindi:

$$
\boxed{\lim_{x\to 0} f'(x) \text{ non esiste}}
$$

Punto cruciale: **questo NON decide la derivabilità in $0$**.

---
### **8. Derivabilità in $0$ con il rapporto incrementale**

Per definizione:

$$
f'(0)=\lim_{h\to 0}\frac{f(h)-f(0)}{h}
$$

Qui $f(0)=0$ e per $h\neq 0$:

$$
f(h)=h^2\sin\left(\frac{1}{h}\right)
$$

Quindi:
$$
f'(0)=\lim_{h\to 0}\frac{h^2\sin(1/h)}{h}
=\lim_{h\to 0}h\sin\left(\frac{1}{h}\right)
$$

Ora:

- $h\to 0$
    
- $\sin(1/h)$ è limitato tra $-1$ e $1$
  
Quindi il prodotto tende a $0$:

$$
\boxed{f'(0)=0}
$$

Conclusione:

$$
\boxed{f \text{ è derivabile in } 0,\ \text{e } f'(0)=0}
$$

---
### **9. Strategia generale riassunta**

Supponi che una funzione sia derivabile a sinistra e a destra di $x_0$ e vuoi capire cosa succede in $x_0$.
#### **Step 1: continuità**

Se non è continua in $x_0$:

$$
\Rightarrow \text{non è derivabile}
$$
#### **Step 2: limiti delle derivate laterali**

Calcola:

$$
\lim_{x\to x_0^-} f'(x),\qquad \lim_{x\to x_0^+} f'(x)
$$

- se esistono, sono finiti e uguali $\Rightarrow$ derivabile e quel valore è $f'(x_0)$
    
- se sono diversi o infiniti $\Rightarrow$ non derivabile (angolo, cuspide, tangente verticale, ecc.)

#### **Step 3: caso raro**

Se questi limiti **non esistono** (oscillazioni o altre patologie), non puoi concludere nulla.

Devi tornare alla definizione:

$$
f'(x_0)=\lim_{h\to 0}\frac{f(x_0+h)-f(x_0)}{h}
$$

> ✅ L'esempio $x^2\sin\frac 1x$ mostra anche un fatto di struttura: una funzione può essere **derivabile ovunque senza che $f'$ sia continua** ($f'$ esiste in $0$ ma ha una discontinuità oscillatoria lì). Quindi "derivabile" e "di classe $C^1$" non sono sinonimi: la gerarchia $C^0\supsetneq\{\text{derivabili}\}\supsetneq C^1$ è sviluppata nella UD3, Lezione 5, e la discontinuità di $f'$ non può mai essere a salto per il teorema di Darboux (UD5, Lezione 5).
