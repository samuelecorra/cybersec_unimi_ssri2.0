# **M9 UD4 Lezione 2 - Continuità e derivabilità: esercizi**

### **Esercizi tipici, derivata con definizione, funzioni definite a tratti**

### **1. Obiettivo della lezione**

In questa lezione vediamo alcune tipologie classiche di esercizi su:

- calcolo della derivata **con la definizione** (rapporto incrementale)
    
- intervalli di **continuità** e **derivabilità**
    
- classificazione di **discontinuità** e **punti di non derivabilità** per funzioni definite a tratti

---
## **Parte A: derivata con definizione e intervalli di continuità/derivabilità**

### **2. Esercizio 1**

Determinare con la definizione la derivata di:

$$
f(x)=\sqrt{x+2}
$$

e dire dove la funzione è continua e derivabile.

---
### **3. Derivata con la definizione**

Per definizione:

$$
f'(x)=\lim_{h\to 0}\frac{f(x+h)-f(x)}{h}
$$

Calcoliamo $f(x+h)$:

$$
f(x+h)=\sqrt{x+h+2}
$$

Quindi:

$$
f'(x)=\lim_{h\to 0}\frac{\sqrt{x+h+2}-\sqrt{x+2}}{h}
$$

Sostituendo $h=0$ otteniamo forma indeterminata:

$$
\frac{0}{0}
$$

Per eliminare la differenza di radici, moltiplichiamo e dividiamo per il **coniugato**:

$$
\frac{\sqrt{x+h+2}-\sqrt{x+2}}{h}\cdot
\frac{\sqrt{x+h+2}+\sqrt{x+2}}{\sqrt{x+h+2}+\sqrt{x+2}}
$$

Al numeratore compare il prodotto notevole:

$$
(\sqrt{A}-\sqrt{B})(\sqrt{A}+\sqrt{B})=A-B
$$

con $A=x+h+2$ e $B=x+2$. Quindi:

$$
\frac{(x+h+2)-(x+2)}{h\left(\sqrt{x+h+2}+\sqrt{x+2}\right)}
$$

Semplifichiamo:

$$
(x+h+2)-(x+2)=h
$$

Quindi:

$$
\frac{h}{h\left(\sqrt{x+h+2}+\sqrt{x+2}\right)}
$$

e possiamo semplificare $h$:

$$
f'(x)=\lim_{h\to 0}\frac{1}{\sqrt{x+h+2}+\sqrt{x+2}}
$$

Ora possiamo sostituire $h=0$:

$$
f'(x)=\frac{1}{\sqrt{x+2}+\sqrt{x+2}}
=\frac{1}{2\sqrt{x+2}}
$$

Risultato:

$$
\boxed{f'(x)=\frac{1}{2\sqrt{x+2}}}
$$

---
### **4. Intervalli di continuità e derivabilità**

#### **Dominio e continuità**

La radice quadrata richiede:

$$
x+2\ge 0 \quad \Rightarrow \quad x\ge -2
$$

Essendo una funzione elementare, è continua su tutto il suo dominio:

$$
\boxed{\text{continua per } x\in[-2,+\infty)}
$$
#### **Derivabilità**

La derivata trovata è:

$$
f'(x)=\frac{1}{2\sqrt{x+2}}
$$

Qui il denominatore si annulla in $x=-2$. Quindi $f'(x)$ non è definita in $-2$.

Conclusione:

$$
\boxed{\text{derivabile per } x\in(-2,+\infty)}
$$

Interpretazione grafica: in $x=-2$ la funzione ha **tangente verticale**, quindi non può essere derivabile in quel punto. Precisazione da bordo del dominio: $-2$ è un estremo, quindi lì ha senso soltanto il rapporto incrementale **destro**, che vale $\frac{\sqrt{h}}{h}=\frac{1}{\sqrt h}\to+\infty$: derivata destra infinita, coerente con il limite di $f'(x)$ per $x\to-2^+$.

---
## **Parte B: funzione definita a tratti, discontinuità e non derivabilità**

### **5. Esercizio 2**

Classificare punti di discontinuità e non derivabilità della funzione:

$$
f(x)=
\begin{cases}
x+2 & x\le -2 \\
x^2-4 & -2<x<2 \\
\sqrt{x} & x\ge 2
\end{cases}
$$

---
### **6. Idea generale**

Dentro ciascun intervallo la funzione coincide con:

- un polinomio lineare
    
- un polinomio
    
- una radice

Quindi in ciascun tratto è **continua e derivabile**.

I punti critici possibili sono solo i punti di raccordo:

$$
x=-2 \qquad \text{e} \qquad x=2
$$

---
### **7. Continuità in** $x=-2$

Calcoliamo i limiti laterali.

#### **Limite sinistro (primo tratto)**

$$
\lim_{x\to -2^-}(x+2)=-2+2=0
$$
#### **Limite destro (secondo tratto)**

$$
\lim_{x\to -2^+}(x^2-4)=(-2)^2-4=4-4=0
$$

I due limiti coincidono, quindi il limite esiste ed è $0$.

Valore della funzione in $-2$:

$$
f(-2)=(-2)+2=0
$$

Quindi:
$$
\boxed{f \text{ è continua in } x=-2}
$$

---
### **8. Continuità in** $x=2$
#### **Limite sinistro (secondo tratto)**

$$
\lim_{x\to 2^-}(x^2-4)=2^2-4=0
$$
#### **Limite destro (terzo tratto)**

$$
\lim_{x\to 2^+}\sqrt{x}=\sqrt{2}
$$

I due limiti sono **finiti ma diversi**, quindi:

$$
\boxed{f \text{ non è continua in } x=2}
$$

Classificazione: discontinuità di prima specie (a salto).

Il salto vale:

$$
\sqrt{2}-0=\sqrt{2}
$$

---
### **9. Derivabilità in $x=-2$**

Per verificare derivabilità nel punto di raccordo, usiamo la strategia dei limiti delle derivate laterali.

Derivate nei tratti:

- per $x<-2$:
    
$$
(x+2)'=1
$$

- per $-2<x<2$:
    
$$
(x^2-4)'=2x
$$

Ora limiti laterali delle derivate in $-2$.
#### **Derivata sinistra**

$$
\lim_{x\to -2^-} 1 = 1
$$
#### **Derivata destra**

$$
\lim_{x\to -2^+} 2x = 2(-2)=-4
$$

Sono finiti ma diversi, quindi la funzione **non è derivabile** in $-2$.

Conclusione:

$$
\boxed{f \text{ non è derivabile in } x=-2 \text{ e ha un punto angoloso}}
$$

Interpretazione geometrica: due pendenze diverse, quindi non esiste una tangente unica.

---
### **10. Derivabilità in** $x=2$

Qui la funzione non è continua, e la continuità è necessaria per la derivabilità.

Quindi senza calcoli:

$$
\boxed{f \text{ non è derivabile in } x=2}
$$

Motivo: prima ancora di parlare di tangente, c'è un salto.

---
### **11. Conclusioni finali dell’esercizio**

- su ogni tratto interno la funzione è continua e derivabile
    
- in $x=-2$:
    
    - continua
        
    - **non derivabile**
        
    - **punto angoloso**
        
    
- in $x=2$:
    
    - **non continua**
        
    - discontinuità a salto
        
    - quindi non derivabile

Riassunto:

$$
\boxed{
\text{continua in } x=-2,\ \text{non derivabile (angolo)}
}
$$

$$
\boxed{
\text{discontinua in } x=2\ \text{(salto), quindi non derivabile}
}
$$

---

