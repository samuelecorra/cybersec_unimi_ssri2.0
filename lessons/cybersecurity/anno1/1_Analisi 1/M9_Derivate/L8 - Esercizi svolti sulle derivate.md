## **Lezione 8: Esercizi svolti sulle derivate**

### **Ripasso operativo delle regole di derivazione**

### **1. Obiettivo della lezione**

In questa lezione svolgiamo vari esercizi di calcolo delle derivate, tipici del triennio delle superiori ma che ricompaiono spesso anche nei corsi universitari di Analisi 1 o Matematica generale.

L’idea non è introdurre nuove regole, ma **allenarsi a riconoscere la struttura della funzione** e applicare con rapidita:

- somma/differenza
    
- prodotto
    
- quoziente
    
- catena
    
- “trucco” $f(x)^{g(x)}$

---

### **2. Derivate fondamentali da ricordare**

Queste sono le derivate elementari che conviene memorizzare:

$$

\frac{d}{dx}(k)=0

$$

$$

\frac{d}{dx}(x^\alpha)=\alpha x^{\alpha-1}

$$

$$

\frac{d}{dx}(\sin x)=\cos x

$$

$$

\frac{d}{dx}(\cos x)=-\sin x

$$

$$

\frac{d}{dx}(e^x)=e^x

$$

$$

\frac{d}{dx}(\ln x)=\frac{1}{x}\quad (x>0)

$$

---
### **3. Esercizio 1**

$$

f(x)=\frac{1}{2}x^2-3x+7

$$

È una somma algebrica di tre termini, quindi:

$$

f’(x)=\left(\frac{1}{2}x^2\right)’ + (-3x)’ + 7’

$$

Calcoliamo i pezzi:

$$

\left(\frac{1}{2}x^2\right)’=\frac{1}{2}\cdot 2x=x

$$

$$

(-3x)’=-3\cdot 1=-3

$$

$$

7’=0

$$
Risultato:

$$

\boxed{f’(x)=x-3}

$$

**Osservazione importante**

- un numero **moltiplicativo** (tipo $\frac12, -3)$ “sopravvive” e resta davanti
    
- un numero **additivo** (tipo $+7$) “sparisce” perché la derivata della costante è zero

---
### **4. Esercizio 2**

$$

f(x)=x^{2019}-4\cdot\frac{1}{x}+2\sqrt{x}

$$

È ancora una somma algebrica, quindi:

$$

f’(x)=(x^{2019})’ -4\left(\frac{1}{x}\right)’ +2(\sqrt{x})’

$$

Primo termine:

$$

(x^{2019})’=2019x^{2018}

$$

Secondo termine: $\frac{1}{x}=x^{-1}$

$$

(x^{-1})’=-1\cdot x^{-2}=-\frac{1}{x^2}

$$

Quindi:
$$

-4\left(\frac{1}{x}\right)’=-4\left(-\frac{1}{x^2}\right)=\frac{4}{x^2}

$$

Terzo termine: $\sqrt{x}=x^{1/2}$

$$

(x^{1/2})’=\frac12 x^{-1/2}=\frac{1}{2\sqrt{x}}

$$
Quindi:

$$

2(\sqrt{x})’ = 2\cdot \frac{1}{2\sqrt{x}}=\frac{1}{\sqrt{x}}

$$

Risultato:
$$

\boxed{f’(x)=2019x^{2018}+\frac{4}{x^2}+\frac{1}{\sqrt{x}}}

$$

---

### **5. Esercizio 3**

Funzione prodotto:

$$

f(x)=\sin x \cdot (1-2\cos x)

$$

Regola del prodotto:

$$

f’(x)= (\sin x)’ \cdot (1-2\cos x)+\sin x \cdot (1-2\cos x)’

$$

Calcoliamo:

$$

(\sin x)’=\cos x

$$

Per la seconda derivata:

$$

(1-2\cos x)’=0-2(\cos x)’=-2(-\sin x)=2\sin x

$$

Sostituiamo:

$$

f’(x)=\cos x(1-2\cos x)+\sin x(2\sin x)

$$

Sviluppiamo:
$$

f’(x)=\cos x-2\cos^2 x+2\sin^2 x

$$

Risultato:

$$

\boxed{f’(x)=\cos x-2\cos^2 x+2\sin^2 x}

$$

---
### **6. Esercizio 4**

Funzione composta:
$$

f(x)=(4x-1)^3-6

$$

Regola della catena:

- esterna: $u^3$
    
- interna: $u=4x-1$

$$

\frac{d}{dx}(u^3)=3u^2\cdot u’

$$

Quindi:

$$

f’(x)=3(4x-1)^2\cdot (4x-1)’ - 6’

$$

$$

(4x-1)’=4,\qquad 6’=0

$$

Risultato:
$$

\boxed{f’(x)=12(4x-1)^2}

$$

Se sviluppi:

$$

12(4x-1)^2 = 12(16x^2-8x+1)=192x^2-96x+12

$$

---
### **7. Esercizio 5**

$$

f(x)=\ln!\left(\frac{1-x}{1+x}\right)

$$

È una funzione composta:

- esterna: $\ln$
    
- interna: $\dfrac{1-x}{1+x}$

Regola della catena:
$$

f’(x)=\frac{1}{\frac{1-x}{1+x}}\cdot \left(\frac{1-x}{1+x}\right)’

$$

Ora deriviamo la frazione con la regola del quoziente.

Sia:
$$

p(x)=1-x \Rightarrow p’(x)=-1

$$

$$

q(x)=1+x \Rightarrow q’(x)=1

$$

Allora:

$$
\left(\frac{p}{q}\right)’=\frac{p’q-pq’}{q^2} =

\frac{(-1)(1+x)-(1-x)(1)}{(1+x)^2}
$$

Numeratore:

$$

-(1+x)-(1-x)=-1-x-1+x=-2

$$

Quindi:

$$

\left(\frac{1-x}{1+x}\right)’=\frac{-2}{(1+x)^2}

$$

Ora:
$$

\frac{1}{\frac{1-x}{1+x}}=\frac{1+x}{1-x}

$$

Moltiplichiamo:

$$
f’(x)=\frac{1+x}{1-x}\cdot \frac{-2}{(1+x)^2} =

\frac{-2}{(1-x)(1+x)}
$$

E siccome:
$$

(1-x)(1+x)=1-x^2

$$

Risultato:
$$

\boxed{f’(x)=\frac{-2}{1-x^2}}

$$

---
### **8. Esercizio 6**

Somma di cinque termini (tutti elementari):

$$

f(x)=x^3+3^x+3^3+\log_3 x+\sqrt[3]{3}

$$

Deriviamo termine per termine.

$$

(x^3)’=3x^2

$$

Per $3^x$ ricordiamo la regola:

$$

(a^x)’=a^x\ln a

$$
quindi:
$$

(3^x)’=3^x\ln 3

$$

Il termine $3^3=27$ è costante:

$$

(27)’=0

$$

Per il logaritmo in base 3:
$$

(\log_3 x)’=\frac{1}{x\ln 3}\quad (x>0)

$$

Infine $\sqrt[3]{3}$ è costante:

$$

(\sqrt[3]{3})’=0

$$

Risultato:
$$

\boxed{

f’(x)=3x^2+3^x\ln 3+\frac{1}{x\ln 3}

}

$$

---
### **9. Ultimo esempio: funzione “mostruosa”**

La funzione è somma di tre pezzi:

1. $\sqrt{\ln(\sin x)}$ (composizione a tre livelli)
    
2. $x^{\cos x}$ (base ed esponente variabili)
    
3. $\sqrt{\pi}$ (costante)

#### **9.1 Derivata di** $\sqrt{\ln(\sin x)}$

È una composizione a tre livelli:

- esterna: $\sqrt{u}$
    
- intermedia: $\ln u$
    
- interna: $\sin x$

Deriviamo “a catena”.

Derivata della radice:
$$

(\sqrt{u})’=\frac{1}{2\sqrt{u}}

$$

con $u=\ln(\sin x)$:

$$

\frac{1}{2\sqrt{\ln(\sin x)}}

$$

Derivata del logaritmo:
$$

(\ln u)’=\frac{1}{u}

$$

con $u=\sin x$:
$$

\frac{1}{\sin x}

$$

Derivata del seno:
$$

(\sin x)’=\cos x

$$

Moltiplicando tutto:

$$
\boxed{

\frac{d}{dx}\sqrt{\ln(\sin x)} =

\frac{1}{2\sqrt{\ln(\sin x)}}\cdot \frac{1}{\sin x}\cdot \cos x

}

$$

---
#### **9.2 Derivata di** $x^{\cos x}$

Trucco della lezione 7:

$$

x^{\cos x}=e^{\cos \cdot x\ln x}

$$

Deriviamo:

$$
\frac{d}{dx}\left(e^{\cos x\ln x}\right) =

e^{\cos x \cdot \ln x}\cdot \frac{d}{dx}(\cos x \cdot \ln x)

$$

Ora $\cos x\ln x$ è un prodotto:

$$
\frac{d}{dx}(\cos x\ln x) =

(\cos x)’\ln x+\cos x(\ln x)’
$$

$$

(\cos x)’=-\sin x,\qquad (\ln x)’=\frac{1}{x}

$$

Quindi:
$$

-\sin x \ln x + \frac{\cos x}{x}

$$

Sostituendo e tornando alla forma originale $e^{\cos x\ln x}=x^{\cos x}$:

$$
\boxed{
\frac{d}{dx}\left(x^{\cos x}\right) =

x^{\cos x}\left(-\sin x \ln x + \frac{\cos x}{x}\right)

}

$$

---
#### **9.3 Derivata di** $\sqrt{\pi}$

È una costante:

$$

(\sqrt{\pi})’=0

$$

---
### **10. Derivata finale dell’esempio**

Sommiamo i contributi:

$$

\boxed{

f’(x)=

\frac{1}{2\sqrt{\ln(\sin x)}}\cdot \frac{\cos x}{\sin x}

+

x^{\cos x}\left(-\sin x \ln x + \frac{\cos x}{x}\right)

}

$$

Volendo, il primo pezzo può anche essere riscritto usando:

$$

\frac{\cos x}{\sin x}=\cot x

$$

---
### **11. Chiusura**

Questa lezione serve a fissare un punto centrale:

> quasi tutti gli esercizi sulle derivate si risolvono riconoscendo la struttura e applicando 2–3 regole in sequenza, senza mai tornare al rapporto incrementale.

---
