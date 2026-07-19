# **M1 UD4 Lezione 1 - Irrazionali con indici pari**

### **1. Introduzione**

Una disequazione irrazionale contiene una o più radici in cui l’incognita compare sotto il segno di radice. In questa lezione studiamo il caso più delicato:

$$
\sqrt[2n]{A(x)}
$$

dove l’indice della radice è **pari**.

Le radici di indice pari introducono due vincoli fondamentali:

- il radicando deve essere non negativo;
- il valore della radice è sempre non negativo.

Queste due proprietà rendono le disequazioni irrazionali diverse dalle disequazioni polinomiali o razionali.

> 📌 Nelle disequazioni con radici di indice pari non basta elevare alla potenza: prima bisogna controllare dominio e segni dei due membri.

La differenza tra equivalenza e produzione di candidati è sviluppata nella [lezione sulle equazioni irrazionali](L0%20-%20Equazioni%20irrazionali.md).

---

### **2. Dominio delle radici pari**

Una radice di indice pari:

$$
\sqrt[2n]{A(x)}
$$

è definita in campo reale se e solo se:

$$
A(x)\ge 0
$$

Per esempio:

$$
\sqrt{x-3}
$$

è definita per:

$$
x-3\ge 0
$$

cioè:

$$
x\ge 3
$$

Questo vincolo è obbligatorio in ogni equazione o disequazione che contiene la radice.

---

### **3. La radice pari è sempre non negativa**

Per ogni $A(x)\ge 0$ si ha:

$$
\sqrt[2n]{A(x)}\ge 0
$$

In particolare:

$$
\sqrt{A(x)}\ge 0
$$

Questa informazione è decisiva quando si confronta la radice con un’altra espressione.

Per esempio:

$$
\sqrt{x+1}< -2
$$

non ha soluzioni, perché il primo membro è sempre $\ge 0$, mentre il secondo membro è negativo.

---

### **4. Elevare a potenza pari: quando è lecito**

La funzione:

$$
t\mapsto t^2
$$

non è crescente su tutta $\mathbb{R}$, perché numeri negativi e positivi possono avere lo stesso quadrato:

$$
(-3)^2=3^2=9
$$

Per questo, da:

$$
a<b
$$

non segue sempre:

$$
a^2<b^2
$$

Esempio:

$$
-5<2
$$

ma:

$$
25>4
$$

L’elevamento al quadrato conserva il verso solo quando entrambi i membri sono non negativi.

> ⚠️ Prima di elevare al quadrato una disequazione bisogna sapere che entrambi i membri sono $\ge 0$, oppure distinguere i casi.

---

### **5. Forma $\sqrt{A(x)}\le B(x)$**

Consideriamo:

$$
\sqrt{A(x)}\le B(x)
$$

Poiché la radice è sempre non negativa, affinché la disequazione sia possibile deve valere:

$$
B(x)\ge 0
$$

Inoltre serve il dominio:

$$
A(x)\ge 0
$$

Se entrambi i membri sono non negativi, possiamo elevare al quadrato:

$$
A(x)\le B(x)^2
$$

Quindi:

$$
\sqrt{A(x)}\le B(x)
$$

è equivalente al sistema:

$$
\begin{cases}
A(x)\ge 0 \\
B(x)\ge 0 \\
A(x)\le B(x)^2
\end{cases}
$$

Per il verso stretto:

$$
\sqrt{A(x)}< B(x)
$$

si ottiene:

$$
\begin{cases}
A(x)\ge 0 \\
B(x)> 0 \\
A(x)< B(x)^2
\end{cases}
$$

Il vincolo $B(x)>0$ è stretto perché una quantità non negativa può essere strettamente minore di $B(x)$ solo se $B(x)$ è positiva.

---

### **6. Esempio: radice minore o uguale di un polinomio**

Risolviamo:

$$
\sqrt{x+2}\le x
$$

Dominio della radice:

$$
x+2\ge 0
$$

cioè:

$$
x\ge -2
$$

Poiché il secondo membro deve essere non negativo:

$$
x\ge 0
$$

Ora possiamo elevare al quadrato:

$$
x+2\le x^2
$$

Portiamo tutto a destra:

$$
x^2-x-2\ge 0
$$

Fattorizziamo:

$$
(x-2)(x+1)\ge 0
$$

Il prodotto è non negativo fuori dalle radici:

$$
x\le -1\quad \text{oppure}\quad x\ge 2
$$

Intersechiamo con la condizione $x\ge 0$:

$$
x\ge 2
$$

La soluzione è:

$$
[2,+\infty)
$$

Verifica rapida: per $x=2$:

$$
\sqrt{4}=2
$$

quindi il punto è incluso.

---

### **7. Forma $\sqrt{A(x)}\ge B(x)$**

La disequazione:

$$
\sqrt{A(x)}\ge B(x)
$$

è diversa dalla precedente.

Serve sempre:

$$
A(x)\ge 0
$$

ma non è sempre necessario che $B(x)\ge 0$.

Infatti, se:

$$
B(x)<0
$$

allora la disequazione è automaticamente vera nel dominio, perché:

$$
\sqrt{A(x)}\ge 0>B(x)
$$

Se invece:

$$
B(x)\ge 0
$$

allora si può elevare al quadrato:

$$
A(x)\ge B(x)^2
$$

Quindi:

$$
\sqrt{A(x)}\ge B(x)
$$

equivale all’unione di due casi:

$$
\begin{cases}
A(x)\ge 0 \\
B(x)<0
\end{cases}
\quad \text{oppure} \quad
\begin{cases}
A(x)\ge 0 \\
B(x)\ge 0 \\
A(x)\ge B(x)^2
\end{cases}
$$

Per il verso stretto:

$$
\sqrt{A(x)}>B(x)
$$

il primo caso diventa $B(x)<0$, sempre automatico nel dominio, mentre nel secondo caso si richiede:

$$
A(x)>B(x)^2
$$

con $B(x)\ge 0$.

---

### **8. Esempio: radice maggiore di un’espressione**

Risolviamo:

$$
\sqrt{x+4}>x
$$

Dominio:

$$
x+4\ge 0
$$

quindi:

$$
x\ge -4
$$

Separiamo i casi.

#### **8.1 Caso $x<0$**

Se $x<0$, allora:

$$
\sqrt{x+4}\ge 0>x
$$

quindi la disequazione è vera per tutti gli $x$ del dominio con $x<0$:

$$
[-4,0)
$$

#### **8.2 Caso $x\ge 0$**

Se $x\ge 0$, possiamo elevare al quadrato:

$$
x+4>x^2
$$

Portiamo tutto a destra:

$$
x^2-x-4<0
$$

Calcoliamo le radici:

$$
x_{1,2}=\frac{1\pm\sqrt{17}}{2}
$$

Il coefficiente principale è positivo, quindi il trinomio è negativo tra le radici:

$$
\frac{1-\sqrt{17}}{2}<x<\frac{1+\sqrt{17}}{2}
$$

Intersechiamo con $x\ge 0$:

$$
0\le x<\frac{1+\sqrt{17}}{2}
$$

Unendo i due casi:

$$
x\in[-4,0)\cup\left[0,\frac{1+\sqrt{17}}{2}\right)
$$

cioè:

$$
x\in\left[-4,\frac{1+\sqrt{17}}{2}\right)
$$

---

### **9. Disequazioni con due radici**

Consideriamo:

$$
\sqrt{A(x)}\le \sqrt{B(x)}
$$

Entrambi i membri sono radici pari, quindi servono i domini:

$$
A(x)\ge 0,\quad B(x)\ge 0
$$

Poiché entrambi i membri sono non negativi, possiamo elevare al quadrato:

$$
A(x)\le B(x)
$$

Quindi:

$$
\sqrt{A(x)}\le \sqrt{B(x)}
$$

è equivalente a:

$$
\begin{cases}
A(x)\ge 0 \\
B(x)\ge 0 \\
A(x)\le B(x)
\end{cases}
$$

In realtà, se $A(x)\le B(x)$ e $A(x)\ge 0$, allora segue automaticamente $B(x)\ge 0$. Tuttavia, nelle prime esercitazioni è meglio scrivere esplicitamente entrambi i domini per evitare errori.

---

### **10. Esempio con due radici**

Risolviamo:

$$
\sqrt{2x-1}\le \sqrt{x+3}
$$

Dominio:

$$
\begin{cases}
2x-1\ge 0 \\
x+3\ge 0
\end{cases}
$$

cioè:

$$
\begin{cases}
x\ge \frac{1}{2} \\
x\ge -3
\end{cases}
$$

Quindi:

$$
x\ge \frac{1}{2}
$$

Eleviamo al quadrato:

$$
2x-1\le x+3
$$

quindi:

$$
x\le 4
$$

Intersechiamo:

$$
\frac{1}{2}\le x\le 4
$$

La soluzione è:

$$
\left[\frac{1}{2},4\right]
$$

---

### **11. Radici di indice pari maggiore di 2**

Il ragionamento non cambia per radici come:

$$
\sqrt[4]{A(x)},\quad \sqrt[6]{A(x)},\quad \sqrt[2n]{A(x)}
$$

Il dominio resta:

$$
A(x)\ge 0
$$

e la radice resta non negativa.

Se entrambi i membri sono non negativi, si può elevare alla potenza pari corrispondente.

Per esempio:

$$
\sqrt[4]{x-1}\le 2
$$

richiede:

$$
x-1\ge 0
$$

cioè:

$$
x\ge 1
$$

Poiché $2>0$, eleviamo alla quarta:

$$
x-1\le 16
$$

quindi:

$$
x\le 17
$$

La soluzione è:

$$
[1,17]
$$

---

### **12. Errore tipico: dimenticare il segno del secondo membro**

Consideriamo:

$$
\sqrt{x-1}\le x-3
$$

Un errore frequente è elevare subito al quadrato:

$$
x-1\le (x-3)^2
$$

ma questo passaggio è lecito solo se:

$$
x-3\ge 0
$$

Bisogna quindi imporre:

$$
\begin{cases}
x-1\ge 0 \\
x-3\ge 0 \\
x-1\le (x-3)^2
\end{cases}
$$

Il vincolo più forte tra i primi due è:

$$
x\ge 3
$$

Poi:

$$
x-1\le x^2-6x+9
$$

cioè:

$$
x^2-7x+10\ge 0
$$

Fattorizziamo:

$$
(x-5)(x-2)\ge 0
$$

quindi:

$$
x\le 2\quad \text{oppure}\quad x\ge 5
$$

Intersecando con $x\ge 3$:

$$
x\ge 5
$$

La soluzione è:

$$
[5,+\infty)
$$

#### **12.1 Esempio parametrico elementare**

Consideriamo:

$$
\sqrt{x+1}\le m
$$

Se $m<0$, la disequazione è impossibile perché il primo membro è non negativo. Se $m\ge0$, possiamo elevare al quadrato e intersecare con il dominio:

$$
\begin{cases}
x\ge-1 \\
x+1\le m^2
\end{cases}
$$

Pertanto:

$$
S_m=
\begin{cases}
\varnothing & m<0 \\
[-1,m^2-1] & m\ge0
\end{cases}
$$

Il parametro modifica non solo l'estremo dell'intervallo, ma anche l'esistenza stessa delle soluzioni.

---

### **13. Riepilogo**

> ✅ Le disequazioni irrazionali con radici di indice pari richiedono sempre un controllo esplicito del dominio e dei segni prima dell’elevamento a potenza.

Regole principali:

- $\sqrt[2n]{A(x)}$ richiede $A(x)\ge 0$;
- $\sqrt[2n]{A(x)}$ è sempre non negativa;
- per $\sqrt{A(x)}\le B(x)$ serve $B(x)\ge 0$;
- per $\sqrt{A(x)}\ge B(x)$ il caso $B(x)<0$ è automaticamente vero nel dominio;
- elevare a potenza pari è un passaggio equivalente solo quando i membri confrontati sono non negativi;
- alla fine bisogna sempre intersecare tutte le condizioni trovate.
