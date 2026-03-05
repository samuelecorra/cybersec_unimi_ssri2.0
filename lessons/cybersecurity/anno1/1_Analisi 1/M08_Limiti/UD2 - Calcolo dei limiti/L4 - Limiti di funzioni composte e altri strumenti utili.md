## **Lezione 8: Limiti di funzioni composte e altri strumenti utili**

### **1. Obiettivo della lezione**

In questa lezione impariamo a calcolare i **limiti delle funzioni composte** e introduciamo alcuni strumenti molto utili nel calcolo dei limiti, in particolare:

- il metodo “prima interno, poi esterno” per le funzioni composte
    
- il **cambio di variabile** per formalizzare meglio il ragionamento
    
- il **teorema dei due carabinieri** (teorema del confronto)
    
- alcuni **rimaneggiamenti algebrici** (prodotti notevoli, razionalizzazione) per domare forme indeterminate

---
### **2. Limiti di funzioni composte: idea generale**

Quando una funzione è composta, significa che abbiamo una funzione “esterna” che prende come argomento una funzione “interna”.

L’idea per risolvere questi limiti è molto semplice:

1. si calcola il limite della **funzione interna**
    
2. si usa quel risultato come argomento della **funzione esterna**

In altre parole: prima capisco dove va a finire l’argomento, poi capisco dove va a finire la funzione che lo contiene.

---
### **3. Primo esempio: funzione composta con goniometrica**

Consideriamo:

$$

  

\lim_{x\to +\infty}\cos\left(\frac{3}{e^x}\right)

  

$$
#### **3.1 Analisi della funzione interna**

La funzione interna è:

$$

  

\frac{3}{e^x}

  

$$

Per $x\to +\infty$ abbiamo $e^x\to +\infty$, quindi una costante divisa per qualcosa che esplode tende a $0$:

$$

  

\lim_{x\to +\infty}\frac{3}{e^x}=0

  

$$

---
#### **3.2 Passaggio alla funzione esterna**

Ora dobbiamo calcolare:

$$

  

\lim_{x\to +\infty}\cos\left(\frac{3}{e^x}\right)

  

=

\cos\left(\lim_{x\to +\infty}\frac{3}{e^x}\right)

  

=

\cos(0)

  

$$

E sappiamo che:

$$

  

\cos(0)=1

  

$$

---
#### **3.3 Conclusione**

$$

  

\lim_{x\to +\infty}\cos\left(\frac{3}{e^x}\right)=1

  

$$

---
### **4. Secondo esempio: funzione composta con esponenziale e cambio di variabile**

Consideriamo:

# $$

  

\lim_{x\to +\infty} e^{\frac{x^2+1}{2x^2-x}}

  

$$

Vediamo subito che è una funzione composta, perché abbiamo un esponenziale con esponente una frazione algebrica per niente banale.

---
#### **4.1 Cambio di variabile**

Chiamiamo:

$$

  

y=\frac{x^2+1}{2x^2-x}

  

$$

Allora il limite diventa:

$$

  

\lim_{x\to +\infty} e^{\frac{x^2+1}{2x^2-x}}

  

=

\lim_{x\to +\infty} e^{y}

  

$$

ma ora dobbiamo capire a cosa tende $y$ quando $x\to +\infty$.

---
#### **4.2 Limite della funzione interna**

Calcoliamo:

$$

  

\lim_{x\to +\infty}\frac{x^2+1}{2x^2-x}

  

$$

Raccogliamo il grado massimo $x^2$ sopra e sotto:

$$

  

\frac{x^2+1}{2x^2-x}

  

=

\frac{x^2\left(1+\frac{1}{x^2}\right)}{x^2\left(2-\frac{1}{x}\right)}

  

=

\frac{1+\frac{1}{x^2}}{2-\frac{1}{x}}

  

$$
Ora:

- $\frac{1}{x^2}\to 0$
    
- $\frac{1}{x}\to 0$

Quindi:

$$

  

\lim_{x\to +\infty}\frac{1+\frac{1}{x^2}}{2-\frac{1}{x}}=\frac{1}{2}

  

$$

  

Cioè:

  

$$

  

\lim_{x\to +\infty} y=\frac{1}{2}

  

$$

---

#### **4.3 Limite della funzione esterna**

Ora la variabile che conta è $y$, e $y\to \frac{1}{2}$, quindi:

$$

  

\lim_{x\to +\infty} e^{y}
$$

$$
\lim_{y\to \frac{1}{2}} e^{y}

  

$$

L’esponenziale è continuo, quindi basta sostituire:

$$

  

\lim_{y\to \frac{1}{2}} e^{y}=e^{\frac{1}{2}}

  

$$

---
#### **4.4 Conclusione**

$$

  

\lim_{x\to +\infty} e^{\frac{x^2+1}{2x^2-x}} = e^{\frac{1}{2}} = \sqrt{e}

  

$$

---
### **5. Teorema dei due carabinieri (teorema del confronto)**

Vediamo un esempio tipico in cui serve.
Consideriamo:

$$

  

\lim_{x\to +\infty}\frac{\sin\left(7^x+\ln x\right)}{5x^2+1}

  

$$
#### **5.1 Analisi preliminare**

Il denominatore tende chiaramente a $+\infty$:

$$

  

5x^2+1 \to +\infty

  

$$

Il problema è il numeratore: l’argomento del seno tende a $+\infty$, ma il seno non “si stabilizza”, continua a oscillare.

Quindi il limite del solo numeratore **non esiste**.

Ma questo non vuol dire che non esista il limite della frazione.

Infatti, anche se $\sin(qualcosa)$ oscilla, sappiamo sempre che:

$$

  

-1 \le \sin\left(7^x+\ln x\right)\le 1

  

$$

---
#### **5.2 Divisione per una quantità positiva**

Dividiamo tutta la disuguaglianza per $5x^2+1$.

Nota importante: per $x$ grande, $5x^2+1>0$, quindi il verso delle disuguaglianze non cambia.

  

$$

  

\frac{-1}{5x^2+1}

\le

\frac{\sin\left(7^x+\ln x\right)}{5x^2+1}

\le

\frac{1}{5x^2+1}

  

$$

Ora calcoliamo i limiti dei due estremi.

---
#### **5.3 Limiti delle funzioni esterne**

$$

  

\lim_{x\to +\infty}\frac{-1}{5x^2+1}=0

  

\qquad

  

\lim_{x\to +\infty}\frac{1}{5x^2+1}=0

  

$$

Quindi la funzione centrale è schiacciata tra due funzioni che tendono entrambe a $0$.

---
#### **5.4 Conclusione (teorema dei due carabinieri)**

$$

  

\lim_{x\to +\infty}\frac{\sin\left(7^x+\ln x\right)}{5x^2+1}=0

  

$$

---
### **6. Rimaneggiamenti algebrici: prodotti notevoli e razionalizzazione**

Molte forme indeterminate si risolvono semplicemente “rimaneggiando” l’espressione.
Esempio:

$$

  

\lim_{x\to 4}\frac{\sqrt{x}-2}{x-4}

  

$$
#### **6.1 Analisi preliminare**

Sostituendo $x=4$ otteniamo:

- numeratore: $\sqrt{4}-2=0$
    
- denominatore: $4-4=0$

Quindi è una forma indeterminata:

$$

  

\frac{0}{0}

  

$$

---
#### **6.2 Razionalizzazione con il coniugato**

Moltiplichiamo numeratore e denominatore per il coniugato $\sqrt{x}+2$:

$$
\frac{\sqrt{x}-2}{x-4}\cdot\frac{\sqrt{x}+2}{\sqrt{x}+2} =

\frac{(\sqrt{x}-2)(\sqrt{x}+2)}{(x-4)(\sqrt{x}+2)}
$$

Ora usiamo il prodotto notevole:

$$

  

(\sqrt{x}-2)(\sqrt{x}+2)=x-4

  

$$
Quindi:

$$

  

\frac{x-4}{(x-4)(\sqrt{x}+2)}=\frac{1}{\sqrt{x}+2}

  

$$

---
#### **6.3 Conclusione**

Ora possiamo sostituire $x=4$:
$$

  

\lim_{x\to 4}\frac{1}{\sqrt{x}+2}=\frac{1}{\sqrt{4}+2}=\frac{1}{4}

  

$$

---
### **7. Chiusura**

In questa lezione abbiamo visto quattro idee fondamentali:

- per una funzione composta: prima limite della parte interna, poi della parte esterna
    
- il cambio di variabile rende il ragionamento più pulito
    
- il teorema dei due carabinieri permette di gestire funzioni oscillanti dentro frazioni
    
- molte forme indeterminate si risolvono con rimaneggiamenti algebrici (coniugato, prodotti notevoli, semplificazioni)

Nella prossima lezione introdurremo l’ultimo grande strumento che ci manca per i limiti: la famiglia dei **limiti notevoli**.