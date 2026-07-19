# **M9 UD4 Lezione 1 - Punti di non derivabilità**

### **Punti angolosi, tangenti verticali, cuspidi**

### **1. Premessa chiave: continuità vs derivabilità**

La **continuità** in un punto $x_0$ è una condizione **necessaria** per la derivabilità in $x_0$, ma **non sufficiente**.

Questo significa:

- se una funzione **non è continua** in $x_0$, allora **non può essere derivabile** in $x_0$
    
- se una funzione è continua in $x_0$, allora **può essere** derivabile, ma **non è garantito**

Quindi, se ti chiedono: “la funzione è derivabile in $x_0$?”, la prima cosa sensata da fare è:

> **controllare prima la continuità** in $x_0$

---
### **2. Esempio: discontinuità implica non derivabilità**

Consideriamo la funzione definita per tratti:

$$
f(x)=
\begin{cases}
\frac{\sin x}{x} & x\neq 0 \\
0 & x=0
\end{cases}
$$

Vogliamo sapere se è derivabile in $x=0$.

Prima controlliamo la continuità:

$$
\lim_{x\to 0}\frac{\sin x}{x}=1
$$

Ma il valore della funzione in $0$ è:

$$
f(0)=0
$$

Poiché:

$$
\lim_{x\to 0} f(x)=1 \neq 0 = f(0)
$$

la funzione **non è continua** in $0$, quindi:

$$
\boxed{\text{non è derivabile in } x=0}
$$

---
### **3. Caso interessante: funzione continua ma forse non derivabile**

La difficoltà vera nasce quando la funzione **è continua** in $x_0$.

Infatti, anche se è continua, può succedere che in $x_0$:

- la tangente non sia definibile in modo unico
    
- oppure la tangente sia verticale
    
- oppure la pendenza “impazzisca”

---
### **4. Come si controlla la derivabilità in pratica**

Una strada è usare direttamente la definizione:

$$
f'(x_0)=\lim_{h\to 0}\frac{f(x_0+h)-f(x_0)}{h}
$$

Ma nello studio di funzione spesso conviene una strategia più rapida:

1. calcolare $f'(x)$ per $x\neq x_0$
    
2. studiare il comportamento di $f'(x)$ **avvicinandosi a $x_0$** da sinistra e da destra

Si calcolano così:

$$
\lim_{x\to x_0^-} f'(x)
\qquad
\lim_{x\to x_0^+} f'(x)
$$

Questi due limiti rappresentano, in sostanza, la **pendenza** della curva mentre ti avvicini a $x_0$ da sinistra e da destra.

> ⚠️ **Attenzione a non confondere due oggetti diversi**: le **derivate laterali** $f'_\pm(x_0)$ (limiti laterali del *rapporto incrementale*, UD1/L2 — sono la definizione) e i **limiti laterali della derivata** $\lim_{x\to x_0^\pm}f'(x)$ (limiti della *funzione derivata* calcolata altrove). Il fatto che, per una funzione **continua** in $x_0$, il secondo determini il primo quando esiste è un **teorema** — il "teorema del limite della derivata" — che si dimostra con Lagrange nella UD5, Lezione 3. La strategia di questa lezione poggia interamente su quel teorema: qui la usiamo dichiarandola, lì la giustificheremo. E quando i limiti di $f'$ **non esistono**, il teorema tace e bisogna tornare alla definizione (punto 6.2 e Lezione 3, parte B).

Da qui nascono **quattro casi tipici**.

---
### **5. Le 4 casistiche principali**

#### **Caso 1: limiti finiti e uguali**

Se:

- entrambi i limiti esistono
    
- sono finiti
    
- e sono uguali

allora la funzione è derivabile in $x_0$ e vale:

$$
f'(x_0)=\lim_{x\to x_0^-} f'(x)=\lim_{x\to x_0^+} f'(x)
$$
#### **Esempio**

$$
f(x)=
\begin{cases}
\cos x & x\ge 0 \\
x^2+1 & x<0
\end{cases}
$$

È continua in $0$ perché:

$$
f(0)=\cos 0=1
\qquad
\lim_{x\to 0^-}(x^2+1)=1
$$

Deriviamo per tratti:

$$
f'(x)=
\begin{cases}
-\sin x & x>0 \\
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
\boxed{f \text{ è derivabile in } 0 \text{ e } f'(0)=0}
$$

Interpretazione geometrica: raccordo **liscio**, tangente ben definita e qui addirittura **orizzontale**.

---
#### **Caso 2: limiti finiti ma diversi**

Se:

- entrambi i limiti esistono
    
- sono finiti
    
- ma sono diversi

allora la funzione **non** è derivabile in $x_0$ e in $x_0$ c'è un **punto angoloso**.

#### **Esempio: valore assoluto**

$$
f(x)=|x|=
\begin{cases}
x & x\ge 0 \\
-x & x<0
\end{cases}
$$

Derivata per tratti:

$$
f'(x)=
\begin{cases}
1 & x>0 \\
-1 & x<0
\end{cases}
$$

Limiti:

$$
\lim_{x\to 0^+} f'(x)=1
\qquad
\lim_{x\to 0^-} f'(x)=-1
$$

Diversi, quindi:

$$
\boxed{f \text{ non è derivabile in } 0}
$$

Interpretazione: in $0$ non c'è una tangente unica, sembra che “arrivino” due rette diverse.

---
#### **Caso 3: entrambi i limiti infiniti e con lo stesso segno**

Se:

$$
\lim_{x\to x_0^-} f'(x)=+\infty
\qquad
\lim_{x\to x_0^+} f'(x)=+\infty
$$

oppure entrambi $-\infty$, allora la funzione non è derivabile e in $x_0$ ha un **punto a tangente verticale**.

> 📌 Convenzione terminologica: molti testi (e il parlato scolastico) chiamano questo caso "flesso a tangente verticale". A rigore la parola *flesso* riguarda il cambio di **concavità**, un concetto che appartiene a M10 (UD4): qui la usiamo solo come etichetta tradizionale, e il nome tecnicamente pulito è "punto a tangente verticale". Negli esempi tipici ($\sqrt[3]{x}$) il punto è effettivamente anche un flesso, ma le due proprietà vanno tenute distinte.
#### **Esempio: radice cubica**

$$
f(x)=\sqrt[3]{x}=x^{1/3}
$$

Derivata:

$$
f'(x)=\frac{1}{3}x^{-2/3}
$$

Quando $x\to 0^\pm$:

$$
x^{-2/3}\to +\infty
$$

Quindi:

$$
\lim_{x\to 0^-} f'(x)=+\infty
\qquad
\lim_{x\to 0^+} f'(x)=+\infty
$$

Conclusione:

$$
\boxed{\text{flesso a tangente verticale in } 0,\ \text{non derivabile}}
$$

Nota: se i due limiti sono entrambi $+\infty$ si dice spesso **ascendente** (funzione crescente vicino al punto). Se entrambi sono $-\infty$ si dice **discendente**.

Motivo concettuale: una retta verticale non ha coefficiente angolare definito, quindi la derivata non può esistere come numero reale.

---
#### **Caso 4: un limite $+\infty$ e l'altro $-\infty$**

Se:

$$
\lim_{x\to x_0^-} f'(x)=-\infty
\qquad
\lim_{x\to x_0^+} f'(x)=+\infty
$$

o viceversa, allora la funzione non è derivabile e in $x_0$ ha una **cuspide**.
#### **Esempio: modulo della radice cubica**

$$
f(x)=\left|\sqrt[3]{x}\right|
$$

E la forma tipica è una “V curva” con tangente verticale nel punto di raccordo.

In questo caso si verifica che:

- da un lato la pendenza tende a $+\infty$
    
- dall'altro tende a $-\infty$

Quindi non c'è derivabilità.

Geometricamente: c'è una tangente verticale ma il verso di crescita cambia in modo “spigoloso” e la pendenza diverge con segni opposti.

---
### **6. Due osservazioni finali importanti**

#### **6.1 Dominio solo da un lato**

Può succedere che la funzione sia definita solo a destra o solo a sinistra di $x_0$.

Esempi tipici:

- estremi di un dominio tipo $\sqrt{x}$ in $x=0$
    
- bordo di una semicirconferenza

In quel caso puoi calcolare solo il limite che ha senso (destro o sinistro). Se quel limite è infinito, significa tangente verticale e quindi non derivabilità.

---
#### **6.2 Casi rari: limiti che non esistono per motivi più profondi**

È possibile (anche se raro) che i limiti destro e sinistro di $f'(x)$ **non esistano** in modo pulito (non tendono né a un numero, né a infinito).

In quei casi non puoi concludere nulla solo guardando quei limiti: devi tornare alla definizione e calcolare direttamente:

$$
\lim_{h\to 0}\frac{f(x_0+h)-f(x_0)}{h}
$$

Alle superiori praticamente non capitano, ma in ambito universitario possono apparire.

---
### **7. Riepilogo compatto delle casistiche**

Sia $f$ continua in $x_0$.

- se i limiti di $f'(x)$ a sinistra e destra sono **finiti e uguali**
    
    $$\Rightarrow f \text{ derivabile in } x_0$$
    
- se sono **finiti ma diversi**
    
    $$\Rightarrow \text{punto angoloso}$$
    
- se sono **entrambi $+\infty \text{ o entrambi } -\infty$**
    
    $$\Rightarrow \text{flesso a tangente verticale}$$
    
- se uno è $+\infty$ e l'altro è $-\infty$
    
    $$\Rightarrow \text{cuspide}$$

---
### **8. Il quinto caso: oscillazione del rapporto incrementale**

La classificazione precedente presuppone che i limiti laterali di $f'$ esistano (finiti o infiniti). Esiste un quinto comportamento, più sottile: il **rapporto incrementale può oscillare senza limite**. Esempio canonico:

$$
f(x)=\begin{cases} x\sin\dfrac{1}{x}, & x\neq 0\\ 0, & x=0\end{cases}
$$

$f$ è continua in $0$ (M08). Il rapporto incrementale in $0$ è

$$
\frac{f(h)-f(0)}{h} = \sin\frac{1}{h}
$$

che per $h\to 0$ **non ha limite, nemmeno laterale** (M08, criterio sequenziale): niente derivata, niente derivate laterali, niente tangente — nemmeno verticale. Il grafico oscilla tra le rette $y=\pm x$ con pendenze che non si stabilizzano. Non è un angolo, non è una cuspide: è un punto di **non derivabilità per oscillazione**. (Il cugino $x^2\sin\frac 1x$, che invece È derivabile in $0$, è il protagonista della Lezione 3.)

<!-- TODO FIGURA:
Quattro pannelli con i tipi di non derivabilità: (a) punto angoloso |x| in 0 con le due semirette tangenti di pendenza +1 e -1; (b) tangente verticale x^(1/3) in 0 con retta verticale tratteggiata; (c) cuspide |x|^(1/2) (o radice cubica in modulo) con le due pendenze che divergono a +inf e -inf; (d) oscillazione x*sin(1/x) con inviluppo y=±x e secanti dall'origine con pendenze oscillanti.
Obiettivo didattico: tassonomia visiva completa dei punti di non derivabilità, collegando ciascun disegno al comportamento delle derivate laterali o del rapporto incrementale.
-->

---
### **9. Errori frequenti**

- ❌ Saltare il controllo di continuità: se $f$ non è continua in $x_0$, è inutile calcolare i limiti di $f'$.
- ❌ Confondere derivate laterali e limiti laterali della derivata (callout del punto 4).
- ❌ Classificare come "cuspide" ogni tangente verticale: la cuspide richiede segni **opposti** degli infiniti.
- ❌ Dire che una **derivata infinita** è una derivata: la derivabilità richiede il limite **finito**; "$f'(x_0)=+\infty$" è solo un modo di dire che la tangente è verticale, non un valore della funzione derivata.
- ❌ Concludere "non derivabile" quando i limiti di $f'$ non esistono: in quel caso non si conclude nulla senza tornare alla definizione (caso $x^2\sin\frac 1x$, Lezione 3).