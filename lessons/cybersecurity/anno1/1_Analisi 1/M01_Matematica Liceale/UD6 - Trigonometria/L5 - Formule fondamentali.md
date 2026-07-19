# **M1 UD6 Lezione 5 - Formule fondamentali**

### **1. Introduzione**

Le formule goniometriche permettono di trasformare espressioni con seno, coseno e tangente. Non servono solo a semplificare calcoli: sono strumenti per risolvere equazioni, studiare funzioni periodiche, calcolare limiti e, più avanti, derivate e integrali.

La regola pratica è:

> 📌 Una formula goniometrica va usata per trasformare l’espressione in una forma più semplice, non per complicarla.

---

### **2. Identità fondamentale**

Dalla circonferenza goniometrica:

$$
\sin^2 x+\cos^2 x=1
$$

Da questa si ricavano:

$$
\sin^2 x=1-\cos^2 x
$$

e:

$$
\cos^2 x=1-\sin^2 x
$$

Dividendo per $\cos^2 x$, quando $\cos x\neq 0$, si ottiene:

$$
\tan^2 x+1=\frac{1}{\cos^2 x}
$$

Dividendo per $\sin^2 x$, quando $\sin x\neq 0$, si ottiene:

$$
1+\cot^2 x=\frac{1}{\sin^2 x}
$$

---

### **3. Formule di addizione**

Le formule di addizione sono:

$$
\sin(x+y)=\sin x\cos y+\cos x\sin y
$$

$$
\cos(x+y)=\cos x\cos y-\sin x\sin y
$$

#### **3.1 Dimostrazione mediante prodotto scalare e rotazione**

Consideriamo i vettori unitari

$$
u=(\cos\alpha,\sin\alpha),\qquad v=(\cos\beta,\sin\beta).
$$

L'angolo tra essi è $\alpha-\beta$, perciò, dalla definizione geometrica di prodotto scalare,

$$
u\cdot v=\|u\|\,\|v\|\cos(\alpha-\beta)=\cos(\alpha-\beta).
$$

Calcolando lo stesso prodotto per componenti e usando $\|u\|=\|v\|=1$, si ottiene

$$
\cos(\alpha-\beta)=\cos\alpha\cos\beta+\sin\alpha\sin\beta.
$$

Sostituendo $\beta$ con $-\beta$ e usando la parità del coseno e la disparità del seno segue

$$
\cos(\alpha+\beta)=\cos\alpha\cos\beta-\sin\alpha\sin\beta.
$$

Infine, dalla relazione $\sin t=\cos(\pi/2-t)$,

$$
\sin(\alpha+\beta)
=\cos\left(\frac{\pi}{2}-\alpha-\beta\right)
=\sin\alpha\cos\beta+\cos\alpha\sin\beta.
$$

La formula della differenza si ottiene ancora sostituendo $\beta$ con $-\beta$.

<!-- TODO FIGURA:
Circonferenza unitaria con i vettori u=(cos alpha,sin alpha) e v=(cos beta,sin beta), l'angolo alpha-beta tra essi e le proiezioni sugli assi. Affiancare la lettura del prodotto scalare. Obiettivo didattico: visualizzare la dimostrazione della formula del coseno della differenza.
-->

Per la tangente, quando le espressioni sono definite:

$$
\tan(x+y)=\frac{\tan x+\tan y}{1-\tan x\tan y}
$$

Questa formula richiede

$$
\cos x\neq 0,\qquad \cos y\neq 0,\qquad \cos(x+y)\neq 0.
$$

L'ultima condizione equivale, sotto le prime due, a $1-\tan x\tan y\neq 0$. La formula si ricava dividendo numeratore e denominatore di

$$
\tan(x+y)=\frac{\sin(x+y)}{\cos(x+y)}
$$

per $\cos x\cos y$.

Queste formule permettono di calcolare valori non immediati e di manipolare espressioni composte.

---

### **4. Formule di sottrazione**

Sostituendo $y$ con $-y$ e usando parità e disparità:

$$
\sin(x-y)=\sin x\cos y-\cos x\sin y
$$

$$
\cos(x-y)=\cos x\cos y+\sin x\sin y
$$

Per la tangente:

$$
\tan(x-y)=\frac{\tan x-\tan y}{1+\tan x\tan y}
$$

Sono necessarie $\cos x\neq 0$, $\cos y\neq 0$ e $\cos(x-y)\neq 0$.

---

### **5. Formule di duplicazione**

Ponendo $y=x$ nelle formule di addizione:

$$
\sin(2x)=2\sin x\cos x
$$

$$
\cos(2x)=\cos^2 x-\sin^2 x
$$

Usando l’identità fondamentale, il coseno doppio si può scrivere anche come:

$$
\cos(2x)=1-2\sin^2 x
$$

oppure:

$$
\cos(2x)=2\cos^2 x-1
$$

Per la tangente:

$$
\tan(2x)=\frac{2\tan x}{1-\tan^2 x}
$$

quando $\cos x\neq 0$ e $\cos(2x)\neq 0$. La sola esistenza di $\tan(2x)$ non garantisce che il secondo membro sia utilizzabile: per esempio, per $x=\pi/2$ il primo membro è definito, ma $\tan x$ non lo è.

---

### **6. Formule di bisezione**

Dalle formule del coseno doppio:

$$
\cos(2x)=1-2\sin^2 x
$$

si ricava:

$$
\sin^2 x=\frac{1-\cos(2x)}{2}
$$

Analogamente, da:

$$
\cos(2x)=2\cos^2 x-1
$$

si ricava:

$$
\cos^2 x=\frac{1+\cos(2x)}{2}
$$

Queste formule sono utili quando compaiono quadrati di seno e coseno.

Sostituendo $x$ con $x/2$ si ottengono

$$
\sin^2\frac{x}{2}=\frac{1-\cos x}{2},
$$

$$
\cos^2\frac{x}{2}=\frac{1+\cos x}{2}.
$$

Estraendo la radice bisogna conservare entrambi i segni:

$$
\sin\frac{x}{2}=\pm\sqrt{\frac{1-\cos x}{2}},
$$

$$
\cos\frac{x}{2}=\pm\sqrt{\frac{1+\cos x}{2}}.
$$

Il segno non è arbitrario: dipende dal quadrante in cui si trova $x/2$. Per esempio, se $x=3\pi$, allora $x/2=3\pi/2$ e il seno del semangolo è negativo.

#### **6.1 Formule della tangente del semangolo**

Quando i denominatori sono non nulli,

$$
\tan\frac{x}{2}=\frac{\sin x}{1+\cos x}
$$

e

$$
\tan\frac{x}{2}=\frac{1-\cos x}{\sin x}.
$$

La prima si ottiene dividendo $\sin x=2\sin(x/2)\cos(x/2)$ per $1+\cos x=2\cos^2(x/2)$; la seconda usando $1-\cos x=2\sin^2(x/2)$.

#### **6.2 Sostituzione universale**

Ponendo

$$
t=\tan\frac{x}{2}
$$

si ricavano

$$
\sin x=\frac{2t}{1+t^2},\qquad \cos x=\frac{1-t^2}{1+t^2}.
$$

La sostituzione trasforma espressioni razionali in seno e coseno in espressioni razionali in $t$. Sarà ripresa nelle equazioni avanzate e negli integrali; i punti $x=\pi+2k\pi$, per i quali $t$ non è definita, devono essere controllati separatamente.

---

### **7. Formule di prostaferesi**

Le formule di prostaferesi trasformano somme in prodotti:

$$
\sin x+\sin y=2\sin\left(\frac{x+y}{2}\right)\cos\left(\frac{x-y}{2}\right)
$$

$$
\sin x-\sin y=2\cos\left(\frac{x+y}{2}\right)\sin\left(\frac{x-y}{2}\right)
$$

$$
\cos x+\cos y=2\cos\left(\frac{x+y}{2}\right)\cos\left(\frac{x-y}{2}\right)
$$

$$
\cos x-\cos y=-2\sin\left(\frac{x+y}{2}\right)\sin\left(\frac{x-y}{2}\right)
$$

Sono particolarmente utili nelle equazioni, perché trasformano una somma in un prodotto uguale a zero.

---

### **8. Formule di Werner**

Le formule di Werner trasformano prodotti in somme:

$$
\sin x\sin y=\frac{1}{2}[\cos(x-y)-\cos(x+y)]
$$

$$
\cos x\cos y=\frac{1}{2}[\cos(x-y)+\cos(x+y)]
$$

$$
\sin x\cos y=\frac{1}{2}[\sin(x+y)+\sin(x-y)]
$$

Sono utili soprattutto in integrazione e manipolazioni algebriche.

---

### **9. Esempio: calcolo di un valore**

Calcoliamo:

$$
\sin\frac{5\pi}{12}
$$

Osserviamo che:

$$
\frac{5\pi}{12}=\frac{\pi}{4}+\frac{\pi}{6}
$$

Usiamo la formula di addizione:

$$
\sin\left(\frac{\pi}{4}+\frac{\pi}{6}\right)
=
\sin\frac{\pi}{4}\cos\frac{\pi}{6}
+\cos\frac{\pi}{4}\sin\frac{\pi}{6}
$$

Sostituiamo i valori:

$$
\frac{\sqrt{2}}{2}\cdot\frac{\sqrt{3}}{2}
+\frac{\sqrt{2}}{2}\cdot\frac{1}{2}
$$

Quindi:

$$
\sin\frac{5\pi}{12}=\frac{\sqrt{6}+\sqrt{2}}{4}
$$

---

### **10. Esempio: semplificazione**

Semplifichiamo:

$$
1-\cos(2x)
$$

Usiamo:

$$
\cos(2x)=1-2\sin^2 x
$$

Allora:

$$
1-\cos(2x)=1-(1-2\sin^2 x)=2\sin^2 x
$$

Quindi:

$$
1-\cos(2x)=2\sin^2 x
$$

---

### **11. Errori tipici**

#### **11.1 Segno nel coseno della somma**

La formula corretta è:

$$
\cos(x+y)=\cos x\cos y-\sin x\sin y
$$

Il segno meno è essenziale.

#### **11.2 Confondere quadrato e doppio angolo**

In generale:

$$
\sin(2x)\neq 2\sin x
$$

La formula corretta è:

$$
\sin(2x)=2\sin x\cos x
$$

#### **11.3 Usare formule con denominatori senza dominio**

La formula:

$$
\tan(x+y)=\frac{\tan x+\tan y}{1-\tan x\tan y}
$$

richiede che tutti i termini coinvolti siano definiti.

#### **11.4 Dimenticare il segno nella bisezione**

Da una formula per $\sin^2(x/2)$ non segue automaticamente che $\sin(x/2)$ sia positivo. Il quadrante del semangolo è parte del problema.

#### **11.5 Usare gradi nelle formule analitiche**

Le identità trigonometriche valgono anche se gli angoli sono espressi coerentemente in gradi, ma i collegamenti con limiti, derivate e integrali richiedono i radianti. Mescolare unità nella stessa formula è sempre scorretto.

---

### **12. Riepilogo**

> ✅ Le formule fondamentali collegano valori goniometrici di somme, differenze, doppi angoli e prodotti.

Da ricordare con priorità:

$$
\sin^2 x+\cos^2 x=1
$$

$$
\sin(x+y)=\sin x\cos y+\cos x\sin y
$$

$$
\cos(x+y)=\cos x\cos y-\sin x\sin y
$$

$$
\sin(2x)=2\sin x\cos x
$$

$$
\cos(2x)=\cos^2 x-\sin^2 x
$$

Queste formule saranno usate direttamente nella prossima lezione su equazioni e disequazioni goniometriche.
