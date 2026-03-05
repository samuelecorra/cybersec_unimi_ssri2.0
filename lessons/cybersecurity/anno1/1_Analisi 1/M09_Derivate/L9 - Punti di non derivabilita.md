## **Lezione 9: Punti di non derivabilita**

### **Punti angolosi, flessi a tangente verticale, cuspidi**

### **1. Premessa chiave: continuita vs derivabilita**

La **continuita** in un punto $x_0$ e una condizione **necessaria** per la derivabilita in $x_0$, ma **non sufficiente**.

Questo significa:

- se una funzione **non e continua** in $x_0$, allora **non puo essere derivabile** in $x_0$
    
- se una funzione e continua in $x_0$, allora **puo essere** derivabile, ma **non e garantito**

Quindi, se ti chiedono: “la funzione e derivabile in $x_0$?”, la prima cosa sensata da fare e:

> **controllare prima la continuita** in $x_0$

---
### **2. Esempio: discontinuita implica non derivabilita**

Consideriamo la funzione definita per tratti:

$$

f(x)=

\begin{cases}

\frac{\sin x}{x} & x\neq 0 \\\\

0 & x=0

\end{cases}

$$

Vogliamo sapere se e derivabile in $x=0$.

Prima controlliamo la continuita:

$$

\lim_{x\to 0}\frac{\sin x}{x}=1

$$

Ma il valore della funzione in $0$ e:

$$

f(0)=0

$$

Poiche:

$$

\lim_{x\to 0} f(x)=1 \neq 0 = f(0)

$$

la funzione **non e continua** in $0$, quindi:

$$

\boxed{\text{non e derivabile in } x=0}

$$

---
### **3. Caso interessante: funzione continua ma forse non derivabile**

La difficolta vera nasce quando la funzione **e continua** in $x_0$.

Infatti, anche se e continua, puo succedere che in $x_0$:

- la tangente non sia definibile in modo unico
    
- oppure la tangente sia verticale
    
- oppure la pendenza “impazzisca”

---
### **4. Come si controlla la derivabilita in pratica**

Una strada e usare direttamente la definizione:

$$

f’(x_0)=\lim_{h\to 0}\frac{f(x_0+h)-f(x_0)}{h}

$$

Ma nello studio di funzione spesso conviene una strategia piu rapida:

1. calcolare $f’(x)$ per $x\neq x_0$
    
2. studiare il comportamento di $f’(x)$ **avvicinandosi a $x_0$** da sinistra e da destra

Si definiscono cosi:

$$

\lim_{x\to x_0^-} f’(x)

\qquad

\lim_{x\to x_0^+} f’(x)

$$

Questi due limiti rappresentano, in sostanza, la **pendenza** della curva mentre ti avvicini a $x_0$ da sinistra e da destra.

Da qui nascono **quattro casi tipici**.

---
## **5. Le 4 casistiche principali**

### **Caso 1: limiti finiti e uguali**

Se:

- entrambi i limiti esistono
    
- sono finiti
    
- e sono uguali

allora la funzione e derivabile in $x_0$ e vale:

$$

f’(x_0)=\lim_{x\to x_0^-} f’(x)=\lim_{x\to x_0^+} f’(x)

$$
#### **Esempio**

$$

f(x)=

\begin{cases}

\cos x & x\ge 0 \

x^2+1 & x<0

\end{cases}

$$

E continua in $0$ perche:

$$

f(0)=\cos 0=1

\qquad

\lim_{x\to 0^-}(x^2+1)=1

$$

Deriviamo per tratti:

$$

f’(x)=

\begin{cases}

-\sin x & x>0 \

2x & x<0

\end{cases}

$$

Ora calcoliamo i limiti:

$$

\lim_{x\to 0^+}(-\sin x)=0

\qquad

\lim_{x\to 0^-}(2x)=0

$$

Sono finiti e uguali, quindi:

$$

\boxed{f \text{ e derivabile in } 0 \text{ e } f’(0)=0}

$$

Interpretazione geometrica: raccordo **liscio**, tangente ben definita e qui addirittura **orizzontale**.

---
### **Caso 2: limiti finiti ma diversi**

Se:

- entrambi i limiti esistono
    
- sono finiti
    
- ma sono diversi

allora la funzione **non** e derivabile in $x_0$ e in $x_0$ c e un **punto angoloso**.

#### **Esempio: valore assoluto**

$$

f(x)=|x|=

\begin{cases}

x & x\ge 0 \

-x & x<0

\end{cases}

$$

Derivata per tratti:

$$

f’(x)=

\begin{cases}

1 & x>0 \\\\

-1 & x<0

\end{cases}

$$

Limiti:

$$

\lim_{x\to 0^+} f’(x)=1

\qquad

\lim_{x\to 0^-} f’(x)=-1

$$

Diversi, quindi:

$$

\boxed{f \text{ non e derivabile in } 0}

$$

Interpretazione: in $0$ non c e una tangente unica, sembra che “arrivino” due rette diverse.

---
### **Caso 3: entrambi i limiti infiniti e con lo stesso segno**

Se:

$$

\lim_{x\to x_0^-} f’(x)=+\infty

\qquad

\lim_{x\to x_0^+} f’(x)=+\infty

$$

oppure entrambi $-\infty$, allora la funzione non e derivabile e in $x_0$ ha un **flesso a tangente verticale**.
#### **Esempio: radice cubica**

$$

f(x)=\sqrt[3]{x}=x^{1/3}

$$

Derivata:

$$

f’(x)=\frac{1}{3}x^{-2/3}

$$

Quando $x\to 0^\pm$:

$$

x^{-2/3}\to +\infty

$$

Quindi:

$$

\lim_{x\to 0^-} f’(x)=+\infty

\qquad

\lim_{x\to 0^+} f’(x)=+\infty

$$

Conclusione:

$$

\boxed{\text{flesso a tangente verticale in } 0,\ \text{non derivabile}}

$$

Nota: se i due limiti sono entrambi $+\infty$ si dice spesso **ascendente** (funzione crescente vicino al punto). Se entrambi sono $-\infty$ si dice **discendente**.

Motivo concettuale: una retta verticale non ha coefficiente angolare definito, quindi la derivata non puo esistere come numero reale.

---
### **Caso 4: un limite $+\infty$ e l altro $-\infty$**

Se:

$$

\lim_{x\to x_0^-} f’(x)=-\infty

\qquad

\lim_{x\to x_0^+} f’(x)=+\infty

$$

o viceversa, allora la funzione non e derivabile e in $x_0$ ha una **cuspide**.
#### **Esempio: modulo della radice cubica**

$$

f(x)=\left|\sqrt[3]{x}\right|

$$

E la forma tipica e una “V curva” con tangente verticale nel punto di raccordo.

In questo caso si verifica che:

- da un lato la pendenza tende a $+\infty$
    
- dall altro tende a $-\infty$

Quindi non c e derivabilita.

Geometricamente: c e una tangente verticale ma il verso di crescita cambia in modo “spigoloso” e la pendenza diverge con segni opposti.

---
## **6. Due osservazioni finali importanti**

### **6.1 Dominio solo da un lato**

Puo succedere che la funzione sia definita solo a destra o solo a sinistra di $x_0$.

Esempi tipici:

- estremi di un dominio tipo $\sqrt{x}$ in $x=0$
    
- bordo di una semicirconferenza

In quel caso puoi calcolare solo il limite che ha senso (destro o sinistro). Se quel limite e infinito, significa tangente verticale e quindi non derivabilita.

---
### **6.2 Casi rari: limiti che non esistono per motivi piu profondi**

E possibile (anche se raro) che i limiti destro e sinistro di $f’(x)$ **non esistano** in modo pulito (non tendono ne a un numero, ne a infinito).

In quei casi non puoi concludere nulla solo guardando quei limiti: devi tornare alla definizione e calcolare direttamente:

$$

\lim_{h\to 0}\frac{f(x_0+h)-f(x_0)}{h}

$$

Alle superiori praticamente non capitano, ma in ambito universitario possono apparire.

---
### **7. Riepilogo compatto delle casistiche**

Sia $f$ continua in $x_0$.

- se i limiti di $f’(x)$ a sinistra e destra sono **finiti e uguali**
    
    $$\Rightarrow f \text{ derivabile in } x_0$$
    
- se sono **finiti ma diversi**
    
    $$\Rightarrow \text{punto angoloso}$$
    
- se sono **entrambi $+\infty \text{ o entrambi } -\infty$**
    
    $$\Rightarrow \text{flesso a tangente verticale}$$
    
- se uno e $+\infty$ e l altro e $-\infty$
    
    $$\Rightarrow \text{cuspide}$$