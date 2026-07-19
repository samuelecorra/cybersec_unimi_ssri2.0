# **M8 UD3 Lezione 2 - Limiti notevoli advanced (magheggi e sostituzioni)**

### **1. Obiettivo della lezione**

Nella lezione precedente abbiamo visto i **limiti notevoli fondamentali** e alcune conseguenze immediate.

Qui facciamo un salto di livello: vediamo esercizi in cui il limite notevole **non compare “pulito”**, ma bisogna fare un po’ di **magheggio algebrico** (moltiplica/dividi, aggiungi/togli, cambio di variabile) per ricondursi a qualcosa che sappiamo già.

---
### **2. Richiamo rapido dei limiti notevoli che useremo**

I due fondamentali:
$$
\lim_{x\to 0}\frac{\sin x}{x}=1
$$

$$
\lim_{x\to \pm\infty}\left(1+\frac{1}{x}\right)^x=e
$$

Conseguenze super-ricorrenti:

$$
\lim_{x\to 0}\frac{\tan x}{x}=1
$$

$$
\lim_{x\to 0}\frac{1-\cos x}{x^2}=\frac12
$$

$$
\lim_{x\to 0}\frac{\ln(1+x)}{x}=1
$$

$$
\lim_{x\to 0}\frac{e^x-1}{x}=1
$$

---
### **3. Esercizio 1**

Calcolare:

$$
\lim_{x\to 0}\frac{\tan(3x)}{x}
$$
#### **3.1 Analisi preliminare**

Sostituendo $x=0$:

- $\tan(3x)\to 0$
    
- $x\to 0$

Forma indeterminata:
$$
\frac{0}{0}
$$

#### **3.2 Magheggio: “voglio lo stesso argomento sopra e sotto”**

Scrivo $x=\frac{3x}{3}$, cioè inserisco $3x$ al denominatore e compenso con un $3$:

$$
\frac{\tan(3x)}{x}=\frac{\tan(3x)}{\frac{3x}{3}}=3\cdot \frac{\tan(3x)}{3x}
$$
Ora faccio il cambio di variabile:

$$
y=3x \quad \Rightarrow \quad x\to 0 \Rightarrow y\to 0
$$
Quindi:
$$
3\cdot \lim_{y\to 0}\frac{\tan y}{y}=3\cdot 1=3
$$
#### **3.3 Risultato**

$$
\lim_{x\to 0}\frac{\tan(3x)}{x}=3
$$

---
### **4. Esercizio 2**

Calcolare:

$$
\lim_{x\to 0}\frac{\ln(1+\sqrt2\,x)}{x}
$$
#### **4.1 Forma indeterminata**

Per $x\to 0$:

- $\ln(1+\sqrt2\,x)\to \ln(1)=0$
    
- $x\to 0$

Quindi:
$$
\frac{0}{0}
$$
#### **4.2 Magheggio: forzo il denominatore a diventare $\sqrt2\,x$**

Moltiplico e divido per $\sqrt2$:
 
$$
\frac{\ln(1+\sqrt2\,x)}{x} =
\sqrt2\cdot \frac{\ln(1+\sqrt2\,x)}{\sqrt2\,x}
$$
Cambio variabile:
$$
y=\sqrt2\,x \quad \Rightarrow \quad x\to 0 \Rightarrow y\to 0
$$
Allora:
$$
\sqrt2\cdot \lim_{y\to 0}\frac{\ln(1+y)}{y}=\sqrt2\cdot 1=\sqrt2
$$
#### **4.3 Risultato**
$$
\lim_{x\to 0}\frac{\ln(1+\sqrt2\,x)}{x}=\sqrt2
$$

---
### **5. Esercizio 3**

Calcolare:
$$
\lim_{x\to 0}\frac{\ln(1+\sin x)}{\sin x}
$$
#### **5.1 Idea chiave**

Qui il limite notevole
$$
\lim_{u\to 0}\frac{\ln(1+u)}{u}=1
$$

vale anche se al posto di $u$ c’è una funzione che tende a $0$.

Quindi basta verificare:
$$
\sin x \to 0 \quad \text{quando } x\to 0
$$
#### **5.2 Cambio variabile (versione formale)**

Pongo:
$$
y=\sin x \quad \Rightarrow \quad x\to 0 \Rightarrow y\to 0
$$
Allora:

$$
\lim_{x\to 0}\frac{\ln(1+\sin x)}{\sin x} =
\lim_{y\to 0}\frac{\ln(1+y)}{y}=1
$$
#### **5.3 Risultato**

$$
\lim_{x\to 0}\frac{\ln(1+\sin x)}{\sin x}=1
$$

---
### **6. Esercizio 4**

Calcolare:
$$
\lim_{x\to 0}\frac{\ln(1+\sin x)}{x}
$$
#### **6.1 Qui non è “pulito”**

Il numeratore contiene $\ln(1+\sin x)$, ma sotto c’è $x$, non $\sin x$.
#### **6.2 Magheggio: moltiplico e divido per $\sin x$**

Scrivo:  

$$
\frac{\ln(1+\sin x)}{x} =
\frac{\ln(1+\sin x)}{\sin x}\cdot \frac{\sin x}{x}
$$

Ora i due fattori sono noti:

- $\displaystyle \lim_{x\to 0}\frac{\ln(1+\sin x)}{\sin x}=1$ (Esercizio 3)
    
- $\displaystyle \lim_{x\to 0}\frac{\sin x}{x}=1$ (limite notevole)

Quindi:
$$
\lim_{x\to 0}\frac{\ln(1+\sin x)}{x}=1\cdot 1=1
$$

#### **6.3 Risultato**

$$
\lim_{x\to 0}\frac{\ln(1+\sin x)}{x}=1
$$

---
### **7. Esercizio 5**

Calcolare:
$$
\lim_{x\to 0}\frac{e^x-\cos(\sqrt{x})}{x}
$$
#### **7.1 Analisi preliminare**

Per $x\to 0$:

- $e^x\to 1$
    
- $\cos(\sqrt{x})\to \cos(0)=1$  

Quindi numeratore $\to 0$ e denominatore $\to 0$:

$$
\frac{0}{0}
$$

#### **7.2 Magheggio: aggiungo e tolgo $1$**

L’idea è far comparire **due limiti noti**: $\dfrac{e^x-1}{x}$ e $\dfrac{1-\cos(\cdot)}{(\cdot)^2}$.
Scrivo:

$$
e^x-\cos(\sqrt{x}) =
(e^x-1) + (1-\cos(\sqrt{x}))
$$

Quindi:

$$
\frac{e^x-\cos(\sqrt{x})}{x} =
\frac{e^x-1}{x} + \frac{1-\cos(\sqrt{x})}{x}
$$

Il primo pezzo è immediato:
$$
\lim_{x\to 0}\frac{e^x-1}{x}=1
$$

Per il secondo pezzo voglio riconoscere  
$$
\lim_{u\to 0}\frac{1-\cos u}{u^2}=\frac12
$$

Qui $u=\sqrt{x}$, e infatti $u\to 0$ quando $x\to 0$.

Inoltre $u^2=x$, quindi:

$$
\frac{1-\cos(\sqrt{x})}{x} =
\frac{1-\cos u}{u^2}
\to \frac12
$$

#### **7.3 Somma dei contributi**

$$
\lim_{x\to 0}\frac{e^x-\cos(\sqrt{x})}{x} =
1+\frac12=\frac32
$$
#### **7.4 Risultato**

$$
\lim_{x\to 0}\frac{e^x-\cos(\sqrt{x})}{x}=\frac32
$$

---
### **8. Un limite notevole in più: la potenza generalizzata**

Chiudiamo il repertorio con un limite che useremo continuamente nelle equivalenze asintotiche:

$$
\lim_{x\to 0}\frac{(1+x)^\alpha-1}{x}=\alpha \qquad (\alpha\in\mathbb{R})
$$

#### **8.1 Derivazione (non circolare, senza Taylor)**

Per $x$ vicino a $0$ si ha $1+x>0$, quindi possiamo scrivere la potenza reale tramite esponenziale e logaritmo (M01):

$$
(1+x)^\alpha = e^{\alpha\ln(1+x)}
$$

Se $\alpha=0$ il limite è banalmente $0$. Sia $\alpha \neq 0$ e poniamo $u=\alpha\ln(1+x)$: per $x\to 0$, $u\to 0$, e $u\neq 0$ per $x\neq 0$ vicino a $0$. Allora, moltiplicando e dividendo:

$$
\frac{(1+x)^\alpha-1}{x}
= \frac{e^{u}-1}{u}\cdot\frac{u}{x}
= \underbrace{\frac{e^{u}-1}{u}}_{\to\ 1}\cdot\ \alpha\cdot\underbrace{\frac{\ln(1+x)}{x}}_{\to\ 1}
\ \xrightarrow[x\to 0]{}\ \alpha
$$

Abbiamo usato **solo** i due limiti notevoli $\frac{e^u-1}{u}\to 1$ e $\frac{\ln(1+x)}{x}\to 1$ già dimostrati nella lezione precedente: nessuna derivata, nessuno sviluppo di Taylor.

#### **8.2 Esempio d'uso**

$$
\lim_{x\to 0}\frac{\sqrt{1+x}-1}{x} = \frac{1}{2}
\qquad\text{(caso } \alpha = \tfrac{1}{2}\text{)}
$$

che ritroveremo come equivalenza $\sqrt{1+x}-1 \sim \frac{x}{2}$ nella UD4.

---
### **9. Limiti del tipo $\left(1+\frac{a}{x}\right)^{bx}$**

Generalizziamo il limite di $e$: per $a,b\in\mathbb{R}$,

$$
\lim_{x\to +\infty}\left(1+\frac{a}{x}\right)^{bx} = e^{ab}
$$

**Derivazione.** Se $a=0$ la base è $1$ e il limite è $1=e^0$. Se $a\neq 0$, per $x$ abbastanza grande $1+\frac{a}{x}>0$ e possiamo porre $y=\frac{x}{a}$, così che $\frac{a}{x}=\frac{1}{y}$ e $bx = ab\,y$:

$$
\left(1+\frac{a}{x}\right)^{bx} = \left[\left(1+\frac{1}{y}\right)^{y}\right]^{ab}
$$

Per $x\to+\infty$ si ha $y\to+\infty$ se $a>0$, $y\to-\infty$ se $a<0$: in entrambi i casi $\left(1+\frac{1}{y}\right)^y\to e$. La funzione esterna $t\mapsto t^{ab}$ è continua in $t=e>0$, quindi il tutto tende a $e^{ab}$.

**Esempio.**

$$
\lim_{x\to +\infty}\left(1-\frac{2}{x}\right)^{3x} = e^{-6}
$$

> ⚠️ Non tutte le forme $1^\infty$ danno $e^{qualcosa}$ in modo automatico: la strategia generale è scrivere $f(x)^{g(x)} = e^{\,g(x)\ln f(x)}$ e studiare l'esponente. Il caso $\left(1+\frac{a}{x}\right)^{bx}$ è solo il più frequente.

---
### **10. Chiusura: il “focus” della lezione**

In molti esercizi il limite notevole è **nascosto**. Le mosse standard per “tirarlo fuori” sono sempre queste:

1. **Rendere uguali** (o proporzionali) gli argomenti sopra e sotto: es. $\tan(3x)/x \Rightarrow \tan(3x)/(3x)$.
    
2. **Moltiplicare e dividere** per lo stesso fattore (o per la stessa funzione): es. $\ln(1+\sin x)/x \Rightarrow \cdot \sin x/\sin x$.
    
3. **Aggiungere e togliere** la stessa quantità: es. $e^x-\cos(\sqrt{x}) \Rightarrow (e^x-1)+(1-\cos(\sqrt{x}))$.
    
4. **Cambio di variabile** per rendere il limite identico a quello notevole.

Nel prossimo step entrano in gioco strumenti “più potenti” per forme indeterminate più toste: **de l’Hôpital**, **sviluppi di Taylor**, **equivalenze asintotiche**.