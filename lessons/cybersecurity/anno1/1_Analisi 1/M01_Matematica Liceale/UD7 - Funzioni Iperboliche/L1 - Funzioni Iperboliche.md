# **M1 UD7 Lezione 1 - Funzioni Iperboliche**

### **1. Introduzione**

Le funzioni iperboliche sono funzioni costruite a partire dall’esponenziale naturale. I loro nomi ricordano le funzioni goniometriche perché hanno identità formali simili, ma la geometria di riferimento non è la circonferenza: è l’iperbole.

Le funzioni principali sono:

$$
\sinh x,\quad \cosh x,\quad \tanh x
$$

Si leggono rispettivamente **seno iperbolico**, **coseno iperbolico** e **tangente iperbolica**.

> 📌 Le funzioni iperboliche sono combinazioni di $e^x$ ed $e^{-x}$.

---

### **2. Definizione di seno iperbolico**

Il seno iperbolico è definito da:

$$
\sinh x=\frac{e^x-e^{-x}}{2}
$$

Dominio:

$$
\mathbb{R}
$$

Poiché $e^x$ ed $e^{-x}$ sono definiti per ogni $x\in\mathbb{R}$, anche $\sinh x$ è definito su tutta la retta reale.

Valori immediati:

$$
\sinh 0=\frac{e^0-e^0}{2}=0
$$

---

### **3. Definizione di coseno iperbolico**

Il coseno iperbolico è definito da:

$$
\cosh x=\frac{e^x+e^{-x}}{2}
$$

Dominio:

$$
\mathbb{R}
$$

Valore in zero:

$$
\cosh 0=\frac{e^0+e^0}{2}=1
$$

Inoltre:

$$
\cosh x\ge 1
$$

per ogni $x\in\mathbb{R}$.

---

### **4. Definizione di tangente iperbolica**

La tangente iperbolica è:

$$
\tanh x=\frac{\sinh x}{\cosh x}
$$

Usando le definizioni:

$$
\tanh x=\frac{e^x-e^{-x}}{e^x+e^{-x}}
$$

Poiché:

$$
\cosh x>0
$$

per ogni $x$, la tangente iperbolica è definita su tutto $\mathbb{R}$.

---

### **5. Identità fondamentale**

Calcoliamo:

$$
\cosh^2 x-\sinh^2 x
$$

Usiamo la differenza di quadrati:

$$
\cosh^2 x-\sinh^2 x=(\cosh x-\sinh x)(\cosh x+\sinh x)
$$

Dalle definizioni:

$$
\cosh x+\sinh x=\frac{e^x+e^{-x}}{2}+\frac{e^x-e^{-x}}{2}=e^x
$$

e:

$$
\cosh x-\sinh x=\frac{e^x+e^{-x}}{2}-\frac{e^x-e^{-x}}{2}=e^{-x}
$$

Quindi:

$$
\cosh^2 x-\sinh^2 x=e^{-x}\cdot e^x=1
$$

L’identità fondamentale è:

$$
\cosh^2 x-\sinh^2 x=1
$$

Questa è analoga a:

$$
\cos^2 x+\sin^2 x=1
$$

ma con il segno meno.

---

### **6. Parità**

Il seno iperbolico è dispari:

$$
\sinh(-x)=-\sinh x
$$

Infatti:

$$
\sinh(-x)=\frac{e^{-x}-e^x}{2}=-\frac{e^x-e^{-x}}{2}
$$

Il coseno iperbolico è pari:

$$
\cosh(-x)=\cosh x
$$

La tangente iperbolica è dispari:

$$
\tanh(-x)=-\tanh x
$$

---

### **7. Relazioni utili**

Dalle definizioni si ottiene:

$$
e^x=\cosh x+\sinh x
$$

e:

$$
e^{-x}=\cosh x-\sinh x
$$

Queste formule mostrano che le funzioni iperboliche non sono oggetti separati dall’esponenziale: sono un modo simmetrico di combinare crescita $e^x$ e decadimento $e^{-x}$.

---

### **8. Confronto con le funzioni goniometriche**

Le somiglianze principali sono:

$$
\sin(-x)=-\sin x,\quad \sinh(-x)=-\sinh x
$$

$$
\cos(-x)=\cos x,\quad \cosh(-x)=\cosh x
$$

Ma l’identità fondamentale cambia:

$$
\sin^2 x+\cos^2 x=1
$$

mentre:

$$
\cosh^2 x-\sinh^2 x=1
$$

Inoltre, seno e coseno sono periodici e limitati, mentre seno iperbolico e coseno iperbolico non sono periodici e crescono in modulo.

---

### **9. Prime proprietà qualitative**

Il seno iperbolico:

- è definito su $\mathbb{R}$;
- è dispari;
- vale $0$ in $x=0$;
- assume tutti i valori reali.

Il coseno iperbolico:

- è definito su $\mathbb{R}$;
- è pari;
- vale $1$ in $x=0$;
- è sempre maggiore o uguale a $1$.

La tangente iperbolica:

- è definita su $\mathbb{R}$;
- è dispari;
- ha valori compresi tra $-1$ e $1$.

---

### **10. Origine geometrica: l'iperbole equilatera**

La circonferenza unitaria è descritta da

$$
X^2+Y^2=1
$$

e viene parametrizzata da $X=\cos t$, $Y=\sin t$. Per l'iperbole equilatera

$$
X^2-Y^2=1
$$

la parametrizzazione naturale del ramo destro è

$$
X=\cosh t,\qquad Y=\sinh t.
$$

Infatti, sostituendo nell'equazione dell'iperbole,

$$
X^2-Y^2=\cosh^2t-\sinh^2t=1.
$$

Quando $t$ percorre $\mathbb{R}$, $\cosh t\geq1$, quindi il punto resta sul ramo destro. Il parametro non è un angolo euclideo e le funzioni iperboliche non sono periodiche. Esiste anche un'interpretazione di $t$ mediante area iperbolica, ma la sua giustificazione richiede l'integrale e sarà ripresa nel modulo corrispondente.

<!-- TODO FIGURA:
Due pannelli con assi cartesiani. A sinistra circonferenza X^2+Y^2=1 e punto (cos t,sin t); a destra iperbole X^2-Y^2=1 e punto (cosh t,sinh t) sul ramo destro. Indicare i segni nelle equazioni e che il parametro iperbolico non è un angolo euclideo. Intervallo visibile [-3,3] su entrambi gli assi. Obiettivo didattico: confrontare le due parametrizzazioni senza suggerire periodicità.
-->

<!-- TODO FIGURA:
Ramo destro dell'iperbole X^2-Y^2=1 per t=-2,-1,0,1,2. Marcare i punti (cosh t,sinh t), il punto (1,0), il verso di crescita di t e la simmetria rispetto all'asse X. Obiettivo didattico: mostrare come la parametrizzazione percorre una sola volta il ramo destro.
-->

### **11. Status della definizione esponenziale**

In questo modulo si usano le proprietà operative dell'esponenziale reale:

$$
e^x>0,\qquad e^{x+y}=e^xe^y,\qquad e^{-x}=\frac{1}{e^x}.
$$

La costruzione rigorosa di $e^x$ per ogni $x\in\mathbb{R}$ richiede completezza, successioni e continuità. Tali fondamenti appartengono ai moduli successivi; le definizioni iperboliche qui sono quindi rigorose rispetto alle proprietà dell'esponenziale assunte come già disponibili operativamente.

### **12. Cotangente, secante e cosecante iperboliche**

La **cotangente iperbolica** è

$$
\coth x=\frac{\cosh x}{\sinh x},
$$

definita per $x\neq0$, perché $\sinh x=0$ soltanto per $x=0$.

La **secante iperbolica** è

$$
\operatorname{sech}x=\frac{1}{\cosh x}.
$$

Poiché $\cosh x\geq1$, è definita per ogni reale.

La **cosecante iperbolica** è

$$
\operatorname{csch}x=\frac{1}{\sinh x},
$$

e ha dominio $\mathbb{R}\setminus\{0\}$.

In questa repository si adottano uniformemente le notazioni $\sinh$, $\cosh$, $\tanh$, $\coth$, $\operatorname{sech}$ e $\operatorname{csch}$.

### **13. Dimostrazione di $\cosh x\geq1$**

Poniamo $u=e^x>0$. La disuguaglianza

$$
(u-1)^2\geq0
$$

equivale a

$$
u^2-2u+1\geq0.
$$

Dividendo per $u>0$ si ottiene

$$
u+\frac1u\geq2.
$$

Poiché $u=e^x$ e $1/u=e^{-x}$,

$$
\cosh x=\frac{e^x+e^{-x}}{2}\geq1.
$$

L'uguaglianza vale se e solo se $u=1$, cioè $x=0$. Pertanto $(0,1)$ è l'unico punto di minimo globale del coseno iperbolico.

### **14. Forma esponenziale della tangente iperbolica**

Moltiplicando numeratore e denominatore per $e^x>0$,

$$
\tanh x
=\frac{e^x-e^{-x}}{e^x+e^{-x}}
=\frac{e^{2x}-1}{e^{2x}+1}.
$$

Il denominatore è sempre positivo. Inoltre, per ogni $u=e^{2x}>0$,

$$
|u-1|<u+1.
$$

Dividendo per $u+1>0$ segue

$$
|\tanh x|<1.
$$

Questa disuguaglianza dimostra che l'immagine è contenuta in $(-1,1)$; il fatto che ogni valore di tale intervallo venga assunto sarà giustificato tramite l'inversa.

### **15. Identità derivate**

Dall'identità fondamentale,

$$
\cosh^2x=1+\sinh^2x.
$$

Dividendo per $\cosh^2x$, lecita perché $\cosh x>0$, si ottiene

$$
1-\tanh^2x=\operatorname{sech}^2x.
$$

Dividendo invece per $\sinh^2x$, per $x\neq0$, si ricava

$$
\coth^2x-1=\operatorname{csch}^2x.
$$

> ⚠️ La seconda identità non è definita in $x=0$: sia $\coth x$ sia $\operatorname{csch}x$ hanno lì un punto escluso.

### **16. Esempi diretti**

Per $x=\ln2$ si ha $e^x=2$ ed $e^{-x}=1/2$. Quindi

$$
\sinh(\ln2)=\frac{2-\frac12}{2}=\frac34,
$$

$$
\cosh(\ln2)=\frac{2+\frac12}{2}=\frac54,
$$

$$
\tanh(\ln2)=\frac{\frac34}{\frac54}=\frac35.
$$

Il controllo

$$
\left(\frac54\right)^2-\left(\frac34\right)^2=1
$$

conferma l'identità fondamentale.

### **17. Riepilogo**

> ✅ Le funzioni iperboliche sono definite tramite l’esponenziale e soddisfano identità simili, ma non uguali, a quelle goniometriche.

Definizioni:

$$
\sinh x=\frac{e^x-e^{-x}}{2}
$$

$$
\cosh x=\frac{e^x+e^{-x}}{2}
$$

$$
\tanh x=\frac{\sinh x}{\cosh x}
$$

Identità fondamentale:

$$
\cosh^2 x-\sinh^2 x=1
$$
