# **M1 UD7 Lezione 2 - Grafici**

### **1. Introduzione**

I grafici delle funzioni iperboliche si capiscono partendo dalle definizioni esponenziali:

$$
\sinh x=\frac{e^x-e^{-x}}{2}
$$

$$
\cosh x=\frac{e^x+e^{-x}}{2}
$$

$$
\tanh x=\frac{\sinh x}{\cosh x}
$$

Il termine $e^x$ domina per $x\to+\infty$, mentre $e^{-x}$ domina per $x\to-\infty$. Questa asimmetria controlla la forma dei grafici.

<!-- TODO FIGURA:
Grafici sovrapposti di y=sinh x e y=cosh x per x in [-3,3]. Evidenziare (0,0), (0,1), simmetria di sinh rispetto all'origine, simmetria di cosh rispetto all'asse y e crescita esponenziale. Obiettivo didattico: confrontare parità, immagini e punti notevoli.
-->

---

### **2. Grafico di $\sinh x$**

La funzione:

$$
f(x)=\sinh x
$$

è dispari:

$$
\sinh(-x)=-\sinh x
$$

quindi il grafico è simmetrico rispetto all’origine.

Inoltre:

$$
\sinh 0=0
$$

Per $x\to+\infty$, il termine $e^x$ domina:

$$
\sinh x=\frac{e^x-e^{-x}}{2}\sim \frac{e^x}{2}
$$

Per $x\to-\infty$, domina $e^{-x}$ con segno negativo:

$$
\sinh x\sim -\frac{e^{-x}}{2}
$$

Quindi $\sinh x$ cresce da $-\infty$ a $+\infty$ e attraversa l’origine.

---

### **3. Immagine e monotonia di $\sinh x$**

L’immagine di $\sinh x$ è:

$$
\mathbb{R}
$$

La funzione è strettamente crescente su tutta la retta reale.

Questo significa che per ogni numero reale $y$ esiste un unico $x$ tale che:

$$
\sinh x=y
$$

Quindi $\sinh x$ è invertibile su tutto $\mathbb{R}$.

---

### **4. Grafico di $\cosh x$**

La funzione:

$$
f(x)=\cosh x
$$

è pari:

$$
\cosh(-x)=\cosh x
$$

quindi il grafico è simmetrico rispetto all’asse $y$.

Il valore minimo è:

$$
\cosh 0=1
$$

e:

$$
\cosh x\ge 1
$$

per ogni $x\in\mathbb{R}$.

Per $x\to+\infty$:

$$
\cosh x\sim \frac{e^x}{2}
$$

Per $x\to-\infty$:

$$
\cosh x\sim \frac{e^{-x}}{2}
$$

Quindi il grafico scende fino al punto $(0,1)$ e poi risale simmetricamente.

---

### **5. Immagine e minimo di $\cosh x$**

L’immagine di $\cosh x$ è:

$$
[1,+\infty)
$$

Il minimo assoluto è:

$$
1
$$

raggiunto per:

$$
x=0
$$

Il coseno iperbolico non è invertibile su tutta $\mathbb{R}$, perché è pari. Lo diventa se si restringe il dominio, per esempio a:

$$
[0,+\infty)
$$

---

### **6. Grafico di $\tanh x$**

La tangente iperbolica è:

$$
\tanh x=\frac{e^x-e^{-x}}{e^x+e^{-x}}
$$

È dispari:

$$
\tanh(-x)=-\tanh x
$$

e:

$$
\tanh 0=0
$$

Per capire il comportamento agli estremi, dividiamo numeratore e denominatore per $e^x$:

$$
\tanh x=\frac{1-e^{-2x}}{1+e^{-2x}}
$$

Quando $x\to+\infty$, si ha $e^{-2x}\to 0$, quindi:

$$
\tanh x\to 1
$$

Quando $x\to-\infty$, si ottiene:

$$
\tanh x\to -1
$$

---

### **7. Asintoti orizzontali di $\tanh x$**

La funzione $\tanh x$ ha due asintoti orizzontali:

$$
y=1
$$

per $x\to+\infty$, e:

$$
y=-1
$$

per $x\to-\infty$.

La sua immagine è:

$$
(-1,1)
$$

Il valore $1$ non viene mai raggiunto, e nemmeno il valore $-1$.

<!-- TODO FIGURA:
Grafico di y=tanh x per x in [-4,4], con punto (0,0), simmetria rispetto all'origine e rette tratteggiate y=-1 e y=1. Specificare che tali rette sono valori limite non raggiunti. Obiettivo didattico: visualizzare immagine (-1,1), monotonia e saturazione.
-->

---

### **8. Confronto grafico con seno, coseno e tangente**

Le funzioni goniometriche sono periodiche:

$$
\sin(x+2\pi)=\sin x
$$

$$
\cos(x+2\pi)=\cos x
$$

Le funzioni iperboliche invece non sono periodiche.

Il coseno ordinario è limitato tra $-1$ e $1$; il coseno iperbolico è sempre almeno $1$ e cresce senza limite.

La tangente ordinaria ha infiniti asintoti verticali; la tangente iperbolica è definita su tutta $\mathbb{R}$ e ha asintoti orizzontali.

<!-- TODO FIGURA:
Confronto di y=sin x e y=sinh x sullo stesso sistema di assi per x in [-3,3]. Evidenziare origine, disparità, oscillazione limitata del seno e crescita non periodica di sinh. Obiettivo didattico: impedire l'analogia falsa della periodicità.
-->

<!-- TODO FIGURA:
Confronto di y=cos x e y=cosh x per x in [-3,3]. Evidenziare il valore comune 1 in x=0, la limitatezza del coseno e il minimo globale di cosh. Obiettivo didattico: distinguere funzione circolare limitata e funzione iperbolica crescente.
-->

---

### **9. Lettura qualitativa dei grafici**

Per riconoscere i grafici:

- $\sinh x$ passa per l’origine, è dispari e cresce senza limiti;
- $\cosh x$ ha minimo in $(0,1)$ ed è simmetrica rispetto all’asse $y$;
- $\tanh x$ passa per l’origine, è limitata tra $-1$ e $1$ e ha forma a saturazione.

Queste forme torneranno nello studio di funzione, nei limiti e in alcuni modelli fisici o ingegneristici.

---

### **10. Giustificazione algebrica della monotonia**

#### **10.1 Seno iperbolico**

Se $y>x$, allora $e^y>e^x$ ed $e^{-y}<e^{-x}$. Pertanto

$$
e^y-e^{-y}>e^x-e^{-x},
$$

e dividendo per $2$ segue $\sinh y>\sinh x$. Il seno iperbolico è strettamente crescente su tutta $\mathbb{R}$ e quindi iniettivo.

#### **10.2 Tangente iperbolica**

La funzione

$$
g(u)=\frac{u-1}{u+1}
$$

è strettamente crescente per $u>0$. Infatti, se $v>u>0$, allora

$$
\frac{v-1}{v+1}-\frac{u-1}{u+1}
=\frac{2(v-u)}{(v+1)(u+1)}>0.
$$

Poiché $u=e^{2x}$ cresce con $x$, anche $\tanh x=g(e^{2x})$ è strettamente crescente e iniettiva su $\mathbb{R}$.

#### **10.3 Coseno iperbolico**

Il coseno iperbolico è pari, quindi non è iniettivo su $\mathbb{R}$. È strettamente crescente su $[0,+\infty)$ e strettamente decrescente su $(-\infty,0]$. La formula di sottrazione che sarà dimostrata nella lezione successiva rende il fatto trasparente:

$$
\cosh y-\cosh x
=2\sinh\frac{x+y}{2}\sinh\frac{y-x}{2}>0
$$

quando $y>x\geq0$.

### **11. Grafici delle funzioni reciproche**

#### **11.1 Cotangente iperbolica**

La funzione $\coth x$ ha dominio $\mathbb{R}\setminus\{0\}$, è dispari e non ha zeri. Dall'identità $\coth^2x-1=\operatorname{csch}^2x>0$ segue

$$
|\coth x|>1.
$$

La sua immagine è

$$
(-\infty,-1)\cup(1,+\infty).
$$

È positiva per $x>0$ e negativa per $x<0$. In termini futuri, $x=0$ è un asintoto verticale e $y=\pm1$ sono asintoti orizzontali sui due rami.

#### **11.2 Secante iperbolica**

La funzione $\operatorname{sech}x$ è definita su $\mathbb{R}$, è pari, sempre positiva e non ha zeri. Poiché $\cosh x\geq1$,

$$
0<\operatorname{sech}x\leq1.
$$

L'immagine è $(0,1]$ e il massimo $1$ è raggiunto in $x=0$.

#### **11.3 Cosecante iperbolica**

La funzione $\operatorname{csch}x$ ha dominio $\mathbb{R}\setminus\{0\}$, è dispari, ha lo stesso segno di $x$ e non ha zeri. La sua immagine è $\mathbb{R}\setminus\{0\}$. In termini di limiti futuri, presenta un asintoto verticale in $x=0$ e tende a $0$ senza raggiungerlo quando $|x|$ cresce.

<!-- TODO FIGURA:
Tre pannelli per y=coth x, y=sech x e y=csch x su x in [-4,4]. Per coth e csch escludere x=0 e segnare l'asintoto verticale; per coth aggiungere y=±1; per sech evidenziare (0,1), parità e valori positivi. Obiettivo didattico: confrontare domini, immagini, simmetrie e punti esclusi.
-->

### **12. Confronto sistematico con le funzioni circolari**

| Funzioni circolari | Funzioni iperboliche |
|---|---|
| $\cos^2x+\sin^2x=1$ | $\cosh^2x-\sinh^2x=1$ |
| circonferenza $X^2+Y^2=1$ | iperbole $X^2-Y^2=1$ |
| seno e coseno periodici | nessuna periodicità reale |
| $\sin x,\cos x\in[-1,1]$ | $\sinh x\in\mathbb{R}$, $\cosh x\geq1$ |
| $\tan$ ha punti esclusi | $\tanh$ è definita su $\mathbb{R}$ |
| $\tan$ ha immagine $\mathbb{R}$ | $\tanh$ ha immagine $(-1,1)$ |

Le analogie di parità sono reali: seno e seno iperbolico sono dispari, coseno e coseno iperbolico sono pari. Non è invece lecito sostituire meccanicamente $\sin$ con $\sinh$ nelle formule: alcuni segni cambiano.

### **13. Applicazioni essenziali**

#### **13.1 Catenaria**

Un cavo omogeneo sospeso ai due estremi assume, nel modello ideale, la forma

$$
y=a\cosh\frac{x}{a}.
$$

La curva è detta **catenaria** e non è una parabola. La derivazione fisica richiede equilibri di forze ed equazioni differenziali, che non vengono sviluppati qui.

<!-- TODO FIGURA:
Grafico della catenaria y=a cosh(x/a) per a=1 e x in [-3,3], con minimo (0,1), asse di simmetria y e due punti di sospensione. Affiancare una parabola tratteggiata solo per mostrare che le curve non coincidono. Obiettivo didattico: collegare cosh a un'applicazione fisica reale.
-->

#### **13.2 Altri contesti**

- $\sinh$ e $\cosh$ formano combinazioni naturali delle soluzioni $e^x$ ed $e^{-x}$ di equazioni differenziali lineari;
- la rapidità nella relatività ristretta usa $\tanh$ per parametrizzare velocità di modulo minore del valore limite;
- $\tanh$ modella transizioni e saturazioni ed è collegata alla funzione logistica;
- sostituzioni come $x=\sinh t$ o $x=\cosh t$ semplificano radicali del tipo $\sqrt{x^2+1}$ e $\sqrt{x^2-1}$;
- le inverse iperboliche compaiono in primitive contenenti tali radicali.

Questi richiami motivano lo studio; derivate, integrali ed equazioni differenziali saranno trattati nei moduli competenti.

### **14. Collegamenti complessi e futuri**

Nell'estensione complessa, dalla formula di Eulero si ricavano

$$
\cos(ix)=\cosh x,
$$

$$
\sin(ix)=i\sinh x.
$$

Queste identità richiedono esponenziale, seno e coseno complessi e non vengono usate come fondamento della teoria reale. Nei moduli successivi saranno inoltre formalizzati continuità, limiti, asintoti, derivate e sviluppi di Taylor delle funzioni iperboliche.

### **15. Errori frequenti**

- attribuire periodicità reale a $\sinh$, $\cosh$ o $\tanh$;
- confondere $\cosh^2x-\sinh^2x=1$ con una somma;
- credere che $\cosh$ sia iniettiva su tutta $\mathbb{R}$;
- includere $\pm1$ nell'immagine di $\tanh$;
- dimenticare $x=0$ dai punti esclusi di $\coth$ e $\operatorname{csch}$;
- confondere $\operatorname{sech}$, reciproco di $\cosh$, con $\operatorname{csch}$, reciproco di $\sinh$;
- usare le identità complesse prima di avere introdotto la formula di Eulero.

### **16. Riepilogo**

> ✅ I grafici iperbolici derivano direttamente dalla combinazione di $e^x$ ed $e^{-x}$.

Proprietà grafiche principali:

- $\sinh x$ è dispari, crescente, con immagine $\mathbb{R}$;
- $\cosh x$ è pari, ha minimo $1$, con immagine $[1,+\infty)$;
- $\tanh x$ è dispari, crescente, con immagine $(-1,1)$;
- $\tanh x$ ha asintoti orizzontali $y=1$ e $y=-1$;
- nessuna delle tre funzioni è periodica.
