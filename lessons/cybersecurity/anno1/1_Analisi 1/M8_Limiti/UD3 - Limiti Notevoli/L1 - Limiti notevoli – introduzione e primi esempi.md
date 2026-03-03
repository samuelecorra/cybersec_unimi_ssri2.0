## **Lezione 9: Limiti notevoli – introduzione e primi esempi**

### **1. Obiettivo della lezione**

In questa lezione introduciamo i **limiti notevoli**, cioè alcune **forme indeterminate ricorrenti** di cui conviene imparare direttamente il risultato.

L’idea è semplice: questi limiti compaiono così spesso che, una volta memorizzati, ci permettono di risolvere **una quantità enorme di esercizi** riconoscendo “pezzi standard” dentro limiti più complicati.

I **due limiti notevoli fondamentali** sono sostanzialmente due. Da questi ne ricaveremo poi altri quattro molto frequenti, e infine vedremo alcuni esempi di utilizzo.

---
### **2. I due limiti notevoli fondamentali**

#### **2.1 Primo limite notevole fondamentale**

$$

\lim_{x\to 0}\frac{\sin x}{x}=1

$$

Se provi a sostituire $x=0$ ottieni una forma indeterminata:

$$

\frac{0}{0}

$$

e si dimostra (con argomenti geometrici o con il teorema del confronto) che il limite vale **1**.

---
#### **2.2 Secondo limite notevole fondamentale**

$$

\lim_{x\to \pm\infty}\left(1+\frac{1}{x}\right)^x = e

$$

Questo si presenta come forma indeterminata:

$$

1^\infty

$$

Il risultato è $e$, il **numero di Nepero**:

$$

e \approx 2.718

$$

e, come $\pi$, è un numero **irrazionale** e **trascendente**.

---
### **3. Limiti ricorrenti ottenibili dal primo limite notevole**

Da
$$

\lim_{x\to 0}\frac{\sin x}{x}=1

$$

si ricavano alcuni limiti che compaiono di continuo.

---
### **4. Primo derivato: limite della tangente**

Consideriamo:
$$

\lim_{x\to 0}\frac{\tan x}{x}

$$
#### **4.1 Riscrittura della tangente**

$$

\tan x = \frac{\sin x}{\cos x}

$$

Quindi, rimaneggiando convenientemente:
$$

\frac{\tan x}{x} =

  

\frac{\sin x}{x}\cdot\frac{1}{\cos x}

$$

---
#### **4.2 Limite dei singoli pezzi**

- $\displaystyle \lim_{x\to 0}\frac{\sin x}{x}=1$
    
- $\displaystyle \lim_{x\to 0}\cos x = 1 \Rightarrow\lim_{x\to 0}\frac{1}{\cos x}=1$


---
#### **4.3 Conclusione**

$$

\lim_{x\to 0}\frac{\tan x}{x}=1

$$

---
### **5. Secondo derivato: limite con $1-\cos x$**

Consideriamo:

$$

\lim_{x\to 0}\frac{1-\cos x}{x^2}

$$

Anche qui, sostituendo $x=0$ otteniamo:

$$

\frac{0}{0}

$$

#### **5.1 Moltiplicazione per il coniugato**

Moltiplichiamo numeratore e denominatore per $1+\cos x$:
 **$$
\frac{1-\cos x}{x^2}\cdot\frac{1+\cos x}{1+\cos x} =

  

\frac{1-\cos^2 x}{x^2(1+\cos x)}

$$
---

#### **5.2 Uso dell’identità fondamentale**

Ricordiamo:

$$

\sin^2 x+\cos^2 x=1

\quad\Rightarrow\quad

1-\cos^2 x=\sin^2 x

$$

Quindi:

$$
\frac{1-\cos^2 x}{x^2(1+\cos x)} =

\frac{\sin^2 x}{x^2}\cdot\frac{1}{1+\cos x}
$$

---
#### **5.3 Limite dei singoli pezzi**

- $\displaystyle \frac{\sin^2 x}{x^2}=\left(\frac{\sin x}{x}\right)^2 \to 1^2=1$
    
- $\displaystyle 1+\cos x \to 2 \Rightarrow \frac{1}{1+\cos x}\to \frac{1}{2}$

---
#### **5.4 Conclusione**
 $$
\lim_{x\to 0}\frac{1-\cos x}{x^2} =

1\cdot\frac{1}{2} =

\frac{1}{2}

$$

---
### **6. Esempio 1: riconoscere i pezzi notevoli**

Consideriamo:

$$

\lim_{x\to 0}\frac{2\sin x + 4\tan x}{x\cos x + 2\sin x}

$$
#### **6.1 Analisi preliminare**

Sostituendo $x=0$ otteniamo:

- numeratore: $0$
    
- denominatore: $0$

quindi forma:

$$

\frac{0}{0}

$$

---
#### **6.2 Far comparire i pezzi notevoli**

L’idea è far comparire $\dfrac{\sin x}{x}$ e $\dfrac{\tan x}{x}$.

Raccogliamo una $x$ al numeratore e al denominatore:
Numeratore:
$$
2\sin x + 4\tan x =

x\left(2\frac{\sin x}{x}+4\frac{\tan x}{x}\right)
$$
Denominatore:
$$
x\cos x + 2\sin x =

x\left(\cos x+2\frac{\sin x}{x}\right)
$$
Quindi:

$$
\frac{2\sin x + 4\tan x}{x\cos x + 2\sin x} = 

\frac{x\left(2\frac{\sin x}{x}+4\frac{\tan x}{x}\right)}{x\left(\cos x+2\frac{\sin x}{x}\right)} =

\frac{2\frac{\sin x}{x}+4\frac{\tan x}{x}}{\cos x+2\frac{\sin x}{x}}
$$

---
#### **6.3 Calcolo del limite**

Ora possiamo sostituire i limiti noti:

- $\dfrac{\sin x}{x}\to 1$
    
- $\dfrac{\tan x}{x}\to 1$
    
- $\cos x\to 1$

Quindi:

$$\lim_{x\to 0}  =

\frac{2\frac{\sin x}{x}+4\frac{\tan x}{x}}{\cos x+2\frac{\sin x}{x}} =

  

\frac{2\cdot 1+4\cdot 1}{1+2\cdot 1} =

\frac{6}{3} = 2

$$

---
### **7. Esempio 2: usare il limite di $\dfrac{1-\cos x}{x^2}$**

Consideriamo:

$$

\lim_{x\to 0}\sqrt\frac{\cos x-\cos^2 x}{2x^2}

$$
#### **7.1 Analisi preliminare**

Per $x\to 0$:

- $\cos x\to 1$
    
- $\cos^2 x\to 1$

quindi sotto radice c’è $1-1=0$ e sotto c’è $0$:

$$

\frac{0}{0}

$$

---
#### **7.2 Fattorizzazione sotto radice**

Sotto radice:

$$
\cos x-\cos^2 x =

\cos x(1-\cos x)
$$
Quindi:

$$
\sqrt{\frac{\cos x-\cos^2 x}{2x^2}} =

\sqrt{\frac{\cos x(1-\cos x)}{2x^2}}
$$

Riscriviamo separando:
$$
=

\sqrt{\frac{\cos x}{2}} \cdot \sqrt{\frac{1-\cos x}{x^2}}
$$

#### **7.3 Limite dei singoli fattori**

Quando $x \to 0$:
$$

\sqrt{\frac{\cos x}{2}} \to \sqrt{\frac{1}{2}}

$$

Per il limite notevole già noto:

$$

\lim_{x\to 0}\frac{1-\cos x}{x^2}=\frac12

$$
di conseguenza il secondo fattore va a:
$$

\sqrt{\frac{1-\cos x}{x^2}} \to \sqrt{\frac12}

$$

---
#### **7.4 Conclusione**

Moltiplicando i due limiti:

$$
\sqrt{\frac{1}{2}}\cdot \sqrt{\frac{1}{2}} =

\sqrt{\frac{1}{4}} =

\frac{1}{2}
$$

---
#### **7.5 Risultato finale**

$$

\boxed{

\lim_{x\to 0}\sqrt{\frac{\cos x-\cos^2 x}{2x^2}}=\frac12

}

$$
---

### **8. Limiti ricorrenti ottenibili dal secondo limite notevole**

Ora passiamo ai limiti che discendono da:

$$

\lim_{x\to \pm\infty}\left(1+\frac{1}{x}\right)^x = e

$$

---
### **9. Terzo limite ricorrente: $\dfrac{\ln(1+x)}{x}$**

Consideriamo:
$$

\lim_{x\to 0}\frac{\ln(1+x)}{x}

$$
Forma indeterminata:
$$

\frac{0}{0}

$$

  #### **9.1 Spostare $\frac{1}{x}$ ad esponente**

Usiamo la proprietà:

$$

k\ln A = \ln(A^k)

$$
Quindi:

$$
\frac{\ln(1+x)}{x} =

\ln\left((1+x)^{\frac{1}{x}}\right)

$$

---
#### **9.2 Cambio di variabile**

Poniamo:

$$

y=\frac{1}{x}

$$

Se $x\to 0$, allora $y\to \pm\infty$ (in base al verso, ma il limite notevole vale uguale).

Inoltre:

$$

1+x = 1+\frac{1}{y}

$$
Quindi:

$$
\ln\left((1+x)^{\frac{1}{x}}\right) =

\ln\left(\left(1+\frac{1}{y}\right)^y\right)
$$

---
#### **9.3 Applicazione del limite notevole**

Il logaritmo è l'esponente a cui deve essere elevata la base, in questo caso $e$ in quanto log naturale, per ottenere l'argomento, che è $e$. Ma è immediato, $e^1 = e$ quindi:

$$

\left(1+\frac{1}{y}\right)^y \to e

\quad\Rightarrow\quad

\ln\left(\left(1+\frac{1}{y}\right)^y\right)\to \ln(e)=1

$$

---
#### **9.4 Conclusione**

$$

\lim_{x\to 0}\frac{\ln(1+x)}{x}=1

$$

---
### **10. Quarto limite ricorrente: $\dfrac{e^x-1}{x}$**

Consideriamo:
$$

\lim_{x\to 0}\frac{e^x-1}{x}

$$

Forma indeterminata:
$$

\frac{0}{0}

$$

  #### **10.1 Sostituzione furba**

Poniamo:
$$

y=e^x-1

$$
Allora:
$$

e^x = 1+y

\quad\Rightarrow\quad

x=\ln(1+y)

$$

Inoltre, se $x\to 0$ allora $e^x\to 1$ e quindi $y\to 0$.

Sostituendo:
$$
\frac{e^x-1}{x} =

\frac{y}{\ln(1+y)}
$$

---
#### **10.2 Collegamento con il limite precedente**

Dal punto 9 sappiamo:

$$

\lim_{y\to 0}\frac{\ln(1+y)}{y}=1

$$

Ma noi finora siamo giunti al reciproco...
Quindi invertendo:

$$

\lim_{y\to 0}\frac{y}{\ln(1+y)}=1

$$

---
#### **10.3 Conclusione**

$$

\lim_{x\to 0}\frac{e^x-1}{x}=1

$$

---
### **11. Due conseguenze meno frequenti**

Da questi si ricavano anche:
$$

\lim_{x\to 0}\frac{a^x-1}{x}=\ln a

$$
e

$$

\lim_{x\to 0}\frac{\log_a(1+x)}{x}=\frac{1}{\ln a}

$$

Sono meno frequenti dei precedenti, ma ogni tanto compaiono e può valere la pena ricordarli.

---
### **12. Chiusura**

In questa lezione abbiamo visto:

- i **due limiti notevoli fondamentali**
    
- quattro limiti ricorrenti che si ottengono da essi
    
- come usarli in esercizi riconoscendo i “pezzi standard”

Nella prossima lezione vedremo **esercizi più avanzati** con i limiti notevoli e come combinarli con scomposizioni, razionalizzazioni e confronti asintotici.

RIASSUNTO:

![](imgs/Pasted%20image%2020251218193121.png)

![](imgs/Pasted%20image%2020251218193148.png)