# **M8 UD3 Lezione 1 - Limiti notevoli: introduzione e primi esempi**

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

Questo limite è così importante che lo **dimostriamo integralmente**, con un argomento geometrico e il teorema dei carabinieri. La dimostrazione è un classico assoluto dell'orale.

##### **2.1.1 La disuguaglianza geometrica fondamentale**

Lavoriamo sulla circonferenza goniometrica (raggio $1$) con l'angolo $x$ misurato **in radianti** e, per ora, $0 < x < \frac{\pi}{2}$.

<!-- TODO FIGURA:
Circonferenza goniometrica di raggio 1, centro O. Angolo x nel primo quadrante con punto P sulla circonferenza, piede H della perpendicolare da P all'asse orizzontale (segmento PH = sin x), punto A = (1,0), punto T sull'intersezione tra la retta OP prolungata e la tangente verticale in A (segmento AT = tan x). Evidenziare con tre colori: il triangolo OAP (area (sin x)/2), il settore circolare OAP (area x/2), il triangolo OAT (area (tan x)/2), con inclusioni successive.
Obiettivo didattico: rendere visibile la catena di aree (sin x)/2 <= x/2 <= (tan x)/2 da cui parte la dimostrazione del limite fondamentale sin(x)/x -> 1.
-->

Confrontiamo tre figure una dentro l'altra:

- il **triangolo** $OAP$, di area $\dfrac{\sin x}{2}$;
- il **settore circolare** $OAP$, di area $\dfrac{x}{2}$ (è qui che serve il radiante: l'area del settore di raggio $1$ e angolo $x$ è $\frac{x}{2}$ **solo** se $x$ è in radianti);
- il **triangolo** $OAT$, di area $\dfrac{\tan x}{2}$.

Poiché ogni figura contiene la precedente:

$$
\frac{\sin x}{2} \le \frac{x}{2} \le \frac{\tan x}{2}
\qquad\Longrightarrow\qquad
\sin x \le x \le \tan x \qquad \left(0<x<\tfrac{\pi}{2}\right)
$$

##### **2.1.2 Dalla disuguaglianza al limite (lato destro)**

Per $0<x<\frac{\pi}{2}$ si ha $\sin x>0$, quindi possiamo dividere tutta la catena per $\sin x$ senza cambiare i versi:

$$
1 \le \frac{x}{\sin x} \le \frac{1}{\cos x}
$$

e passando ai reciproci (tutte quantità positive, i versi si invertono):

$$
\cos x \le \frac{\sin x}{x} \le 1
$$

Ora serve sapere che $\cos x \to 1$ per $x\to 0$. Anche questo si dimostra dalla geometria: dalla formula di bisezione (M01) e da $|\sin t|\le|t|$ (conseguenza di $\sin x \le x$ appena vista, estesa ai negativi per simmetria):

$$
0 \le 1-\cos x = 2\sin^2\frac{x}{2} \le 2\left(\frac{x}{2}\right)^2 = \frac{x^2}{2} \xrightarrow[x\to 0]{} 0
$$

quindi $\cos x\to 1$ per carabinieri. Applicando i carabinieri alla catena $\cos x \le \frac{\sin x}{x} \le 1$:

$$
\lim_{x\to 0^+}\frac{\sin x}{x}=1
$$

##### **2.1.3 Estensione al lato sinistro e conclusione**

La funzione $\dfrac{\sin x}{x}$ è **pari**: sostituendo $x$ con $-x$,

$$
\frac{\sin(-x)}{-x} = \frac{-\sin x}{-x} = \frac{\sin x}{x}
$$

quindi il comportamento per $x\to 0^-$ è identico a quello per $x\to 0^+$, e il limite sinistro vale anch'esso $1$. Poiché limite destro e sinistro coincidono:

$$
\boxed{\lim_{x\to 0}\frac{\sin x}{x}=1}
$$

> ⚠️ **Il risultato dipende dai radianti.** Se l'angolo fosse misurato in gradi, l'area del settore non sarebbe più $\frac{x}{2}$: varrebbe $\sin(x^\circ) = \sin\!\left(\frac{\pi x}{180}\right)$ e quindi
>
> $$
> \lim_{x\to 0}\frac{\sin(x^\circ)}{x} = \frac{\pi}{180} \approx 0{,}0175
> $$
>
> È il motivo per cui in Analisi gli angoli si misurano **sempre** in radianti: solo così $\sin x \sim x$, e più avanti solo così la derivata di $\sin$ sarà $\cos$.

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

> 📌 **Da dove viene questo limite?** Il numero $e$ è stato **definito** in M05 come limite della successione $\left(1+\frac{1}{n}\right)^n$, che è monotona crescente e limitata. Il limite di **funzione** scritto qui sopra non è la definizione: è un **teorema** che estende quel risultato dalla variabile intera $n$ alla variabile reale $x$. La prova per $x\to+\infty$ usa la parte intera $n=\lfloor x\rfloor$ e il confinamento
>
> $$
> \left(1+\frac{1}{n+1}\right)^{n} \le \left(1+\frac{1}{x}\right)^{x} \le \left(1+\frac{1}{n}\right)^{n+1}
> $$
>
> valido per $x\ge 1$ (si maggiora/minora sia la base sia l'esponente usando la monotonia delle potenze). I due estremi si riscrivono come $\left(1+\frac{1}{n+1}\right)^{n+1}\!\big/\left(1+\frac{1}{n+1}\right)$ e $\left(1+\frac{1}{n}\right)^{n}\!\cdot\left(1+\frac{1}{n}\right)$, ed entrambi tendono a $e\cdot 1 = e$ per la definizione di M05; il teorema dei carabinieri (nella versione a soglie della Lezione 3D) conclude. Per $x\to-\infty$ ci si riconduce al caso precedente con la sostituzione $x=-t-1$, $t\to+\infty$ (il dominio richiede $1+\frac 1x>0$, cioè $x<-1$, per dare senso alla potenza reale). L'uguaglianza dei due versi giustifica la scrittura compatta $x\to\pm\infty$.

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

$$
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

$$
\lim_{x\to 0}
\frac{2\frac{\sin x}{x}+4\frac{\tan x}{x}}{\cos x+2\frac{\sin x}{x}} =
\frac{2\cdot 1+4\cdot 1}{1+2\cdot 1} =
\frac{6}{3} = 2
$$

(il passaggio finale è l'algebra dei limiti: numeratore e denominatore hanno limite finito e il limite del denominatore è $3\neq 0$, quindi il limite del quoziente è il quoziente dei limiti).

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

Se $x\to 0^+$ allora $y\to +\infty$; se $x\to 0^-$ allora $y\to -\infty$. Nei due casi il limite notevole del punto 2.2 vale comunque $e$ (lo abbiamo enunciato proprio per $x\to\pm\infty$), quindi i due limiti laterali coincideranno.

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

L'argomento del logaritmo tende a $e$, e il logaritmo naturale è **continuo** in $e$: quindi possiamo passare al limite dentro il logaritmo (teorema di composizione con funzione esterna continua, Lezione 3F della UD1). Poiché $\ln e = 1$:

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
\lim_{x\to 0}\frac{a^x-1}{x}=\ln a \qquad (a>0)
$$
e

$$
\lim_{x\to 0}\frac{\log_a(1+x)}{x}=\frac{1}{\ln a} \qquad (a>0,\ a\neq 1)
$$

Sono meno frequenti dei precedenti, ma ogni tanto compaiono e può valere la pena ricordarli. Le derivazioni sono brevi e istruttive.

**Derivazione del primo.** Scriviamo $a^x = e^{x\ln a}$. Se $a\neq 1$, poniamo $u = x\ln a$: quando $x\to 0$ anche $u\to 0$, e

$$
\frac{a^x-1}{x} = \frac{e^{x\ln a}-1}{x} = \ln a\cdot\frac{e^{u}-1}{u} \xrightarrow[u\to 0]{} \ln a\cdot 1 = \ln a
$$

(il cambio di variabile è legittimo perché $u=x\ln a\neq 0$ per $x\neq 0$). Se $a=1$ il numeratore è identicamente nullo e il limite è $0=\ln 1$: la formula copre anche questo caso.

**Derivazione del secondo.** Con il cambiamento di base $\log_a(1+x) = \dfrac{\ln(1+x)}{\ln a}$:

$$
\frac{\log_a(1+x)}{x} = \frac{1}{\ln a}\cdot\frac{\ln(1+x)}{x} \xrightarrow[x\to 0]{} \frac{1}{\ln a}
$$

---
### **12. Chiusura**

In questa lezione abbiamo visto:

- i **due limiti notevoli fondamentali**
    
- quattro limiti ricorrenti che si ottengono da essi
    
- come usarli in esercizi riconoscendo i “pezzi standard”

Nella prossima lezione vedremo **esercizi più avanzati** con i limiti notevoli e come combinarli con scomposizioni, razionalizzazioni e confronti asintotici.

RIASSUNTO:

![](../imgs/Pasted%20image%2020251218193121.png)

![](../imgs/Pasted%20image%2020251218193148.png)