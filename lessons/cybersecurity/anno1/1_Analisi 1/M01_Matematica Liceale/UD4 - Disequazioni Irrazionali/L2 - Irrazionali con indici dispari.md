# **M1 UD4 Lezione 2 - Irrazionali con indici dispari**

### **1. Introduzione**

Le radici di indice dispari hanno un comportamento molto diverso dalle radici di indice pari.

Una radice come:

$$
\sqrt[3]{A(x)}
$$

è definita anche quando il radicando è negativo. Per esempio:

$$
\sqrt[3]{-8}=-2
$$

Questo rende le disequazioni con radici dispari più semplici dal punto di vista del dominio, ma richiede comunque attenzione nei passaggi algebrici.

Per il confronto con gli elevamenti pari e con la verifica dei candidati si veda anche [L0 - Equazioni irrazionali](L0%20-%20Equazioni%20irrazionali.md).

> 📌 La radice di indice dispari è definita per ogni radicando reale ed è una funzione crescente.

---

### **2. Dominio delle radici dispari**

Una radice di indice dispari:

$$
\sqrt[2n+1]{A(x)}
$$

è definita per ogni valore reale di $A(x)$.

Quindi, se $A(x)$ è un polinomio, non nasce alcuna condizione di dominio:

$$
\sqrt[3]{x^2-5x+1}
$$

è definita per ogni:

$$
x\in\mathbb{R}
$$

Se però il radicando contiene frazioni, logaritmi o altre espressioni con dominio proprio, bisogna comunque rispettare quei domini. In questa unità, però, ci concentriamo sulle disequazioni irrazionali elementari con radicandi polinomiali o razionali semplici.

---

### **3. Monotonia della radice dispari**

La funzione:

$$
t\mapsto \sqrt[2n+1]{t}
$$

è crescente su tutto $\mathbb{R}$.

Questo significa che:

$$
a<b \iff \sqrt[2n+1]{a}<\sqrt[2n+1]{b}
$$

e anche:

$$
a\le b \iff \sqrt[2n+1]{a}\le \sqrt[2n+1]{b}
$$

Questa proprietà permette di elevare entrambi i membri alla potenza dispari corrispondente senza cambiare il verso e senza introdurre condizioni di segno.

Per esempio:

$$
\sqrt[3]{A(x)}<B(x)
$$

è equivalente a:

$$
A(x)<B(x)^3
$$

purché entrambi i membri siano definiti.

---

### **4. Differenza essenziale rispetto alle radici pari**

Per le radici pari:

$$
\sqrt{A(x)}
$$

bisogna imporre:

$$
A(x)\ge 0
$$

e il valore della radice è sempre $\ge 0$.

Per le radici dispari:

$$
\sqrt[3]{A(x)}
$$

non serve imporre:

$$
A(x)\ge 0
$$

e il valore della radice può essere negativo, nullo o positivo.

Per esempio:

$$
\sqrt[3]{x}<0
$$

è equivalente a:

$$
x<0
$$

mentre:

$$
\sqrt{x}<0
$$

non ha soluzioni reali.

---

### **5. Forma $\sqrt[3]{A(x)}\ \square\ B(x)$**

Consideriamo:

$$
\sqrt[3]{A(x)}\ \square\ B(x)
$$

dove $\square$ è uno tra:

$$
>,\quad \ge,\quad <,\quad \le
$$

Poiché la funzione cubo:

$$
t\mapsto t^3
$$

è crescente su tutto $\mathbb{R}$, possiamo elevare al cubo conservando il verso:

$$
A(x)\ \square\ B(x)^3
$$

Non serve discutere il segno di $B(x)$.

---

### **6. Esempio base**

Risolviamo:

$$
\sqrt[3]{x+1}<2
$$

Eleviamo al cubo:

$$
x+1<8
$$

quindi:

$$
x<7
$$

La soluzione è:

$$
(-\infty,7)
$$

Non c’è alcuna condizione del tipo $x+1\ge 0$, perché la radice cubica è definita anche per $x+1<0$.

---

### **7. Esempio con secondo membro variabile**

Risolviamo:

$$
\sqrt[3]{2x-1}\ge x
$$

Eleviamo al cubo:

$$
2x-1\ge x^3
$$

Portiamo tutto a sinistra:

$$
-x^3+2x-1\ge 0
$$

Moltiplichiamo per $-1$, invertendo il verso:

$$
x^3-2x+1\le 0
$$

Fattorizziamo. Notiamo che $x=1$ è uno zero:

$$
1-2+1=0
$$

Dividendo per $(x-1)$ si ottiene:

$$
x^3-2x+1=(x-1)(x^2+x-1)
$$

Le radici del trinomio sono:

$$
x=\frac{-1\pm\sqrt{5}}{2}
$$

Poiché:

$$
\frac{-1+\sqrt{5}}{2}\approx 0.618
$$

l'ordine delle tre radici è:

$$
\frac{-1-\sqrt{5}}{2}<\frac{-1+\sqrt{5}}{2}<1
$$

Il polinomio cubico:

$$
(x-1)(x^2+x-1)
$$

ha coefficiente principale positivo e tre radici semplici, quindi il segno alterna attraversando ciascuna radice. Per $x$ molto grande è positivo. Andando da destra verso sinistra, i segni sono:

$$
+,\quad -,\quad +,\quad -
$$

La disequazione richiede:

$$
x^3-2x+1\le 0
$$

quindi prendiamo gli intervalli dove il polinomio è negativo e includiamo le radici:

$$
x\in\left(-\infty,\frac{-1-\sqrt{5}}{2}\right]\cup\left[\frac{-1+\sqrt{5}}{2},1\right]
$$

Questo esempio mostra che la parte irrazionale può essere eliminata in modo diretto, ma poi resta una normale disequazione polinomiale.

---

### **8. Radici dispari su entrambi i membri**

Consideriamo:

$$
\sqrt[3]{A(x)}\le \sqrt[3]{B(x)}
$$

Poiché la radice cubica è crescente:

$$
\sqrt[3]{A(x)}\le \sqrt[3]{B(x)}
\iff
A(x)\le B(x)
$$

Esempio:

$$
\sqrt[3]{x^2-1}\le \sqrt[3]{3x+5}
$$

è equivalente a:

$$
x^2-1\le 3x+5
$$

Portiamo tutto a sinistra:

$$
x^2-3x-6\le 0
$$

Calcoliamo le radici:

$$
x_{1,2}=\frac{3\pm\sqrt{33}}{2}
$$

Poiché il coefficiente di $x^2$ è positivo, il trinomio è non positivo tra le radici:

$$
x\in\left[\frac{3-\sqrt{33}}{2},\frac{3+\sqrt{33}}{2}\right]
$$

---

### **9. Radici di indice dispari maggiore di 3**

Il ragionamento vale per ogni radice di indice dispari:

$$
\sqrt[5]{A(x)},\quad \sqrt[7]{A(x)},\quad \sqrt[2n+1]{A(x)}
$$

Per esempio:

$$
\sqrt[5]{x-2}\ge -1
$$

Eleviamo alla quinta:

$$
x-2\ge (-1)^5
$$

cioè:

$$
x-2\ge -1
$$

quindi:

$$
x\ge 1
$$

La soluzione è:

$$
[1,+\infty)
$$

---

### **10. Attenzione alle potenze razionali**

Una scrittura come:

$$
(x-2)^{1/3}
$$

viene spesso identificata con:

$$
\sqrt[3]{x-2}
$$

Nel calcolo reale elementare questa lettura è naturale. Tuttavia, quando si lavora con potenze razionali nella teoria generale delle funzioni, molti testi preferiscono definire:

$$
a^r
$$

inizialmente per $a\ge 0$, per mantenere coerenza con le potenze reali e con i logaritmi.

In questa unità, quando una radice dispari è scritta esplicitamente con il simbolo:

$$
\sqrt[3]{\,\cdot\,}
$$

la consideriamo definita anche su radicandi negativi.

> 💡 La notazione radicale esplicita elimina l’ambiguità: $\sqrt[3]{x}$ è definita per ogni $x\in\mathbb{R}$.

---

### **11. Esempio con radicando razionale**

Risolviamo:

$$
\sqrt[3]{\frac{x+1}{x-2}}\le 1
$$

La radice cubica è definita per ogni valore reale del radicando, ma il radicando razionale richiede:

$$
x-2\neq 0
$$

quindi:

$$
x\neq 2
$$

Eleviamo al cubo:

$$
\frac{x+1}{x-2}\le 1
$$

Portiamo tutto a sinistra:

$$
\frac{x+1}{x-2}-1\le 0
$$

Riduciamo:

$$
\frac{x+1-(x-2)}{x-2}\le 0
$$

cioè:

$$
\frac{3}{x-2}\le 0
$$

Poiché $3>0$, serve:

$$
x-2<0
$$

quindi:

$$
x<2
$$

La soluzione è:

$$
(-\infty,2)
$$

Il punto $2$ è escluso dal dominio e infatti non compare nella soluzione.

---

### **12. Errore tipico: aggiungere condizioni di dominio inutili**

Consideriamo:

$$
\sqrt[3]{x-5}\ge 0
$$

Un errore frequente è imporre:

$$
x-5\ge 0
$$

come se la radice fosse quadrata. In questo caso il risultato finale sarebbe comunque:

$$
x\ge 5
$$

ma il ragionamento sarebbe sbagliato.

Il metodo corretto è:

$$
\sqrt[3]{x-5}\ge 0
\iff
x-5\ge 0^3
$$

cioè:

$$
x\ge 5
$$

La condizione $x\ge 5$ nasce dalla disequazione, non dal dominio.

La differenza diventa evidente in:

$$
\sqrt[3]{x-5}\ge -2
$$

Elevando al cubo:

$$
x-5\ge -8
$$

quindi:

$$
x\ge -3
$$

Se avessimo imposto erroneamente $x\ge 5$, avremmo perso soluzioni corrette.

---

### **13. Riepilogo**

> ✅ Le disequazioni con radici di indice dispari si risolvono sfruttando il fatto che la radice dispari è definita su tutto $\mathbb{R}$ ed è crescente.

Regole principali:

- $\sqrt[2n+1]{A(x)}$ non richiede $A(x)\ge 0$;
- la radice dispari può assumere valori negativi;
- elevare a potenza dispari conserva il verso della disequazione;
- non serve discutere il segno del secondo membro;
- eventuali vincoli di dominio possono arrivare dal radicando, se contiene frazioni o altre espressioni non sempre definite;
- dopo l’elevamento si risolve la disequazione algebrica ottenuta.

Il confronto con le radici pari è essenziale: molte difficoltà delle disequazioni irrazionali derivano proprio dal capire quando una trasformazione è equivalente e quando invece richiede condizioni aggiuntive.
