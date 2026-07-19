# **M3 UD1 Lezione 3 - Prodotto scalare**

### **1. Obiettivi e prerequisiti**

Introduciamo un'operazione tra due vettori il cui risultato è un numero reale. Servono modulo, componenti e angolo tra due direzioni.

Al termine sapremo calcolare il prodotto scalare geometricamente e mediante le componenti, riconoscere l'ortogonalità e ricavare l'angolo tra due vettori.

---

### **2. Definizione geometrica**

Siano $\mathbf v$ e $\mathbf w$ due vettori e sia $\theta\in[0,\pi]$ l'angolo compreso tra due loro rappresentanti applicati nello stesso punto.

> 📌 Il **prodotto scalare** è il numero reale
>
> $$
> \mathbf v\cdot\mathbf w=
> \lVert\mathbf v\rVert\,\lVert\mathbf w\rVert\cos\theta
> $$

Si tratta dunque di un'applicazione

$$
V\times V\longrightarrow\mathbb R
$$

non di un'operazione interna in $V$.

Se uno dei vettori è nullo, l'angolo è indeterminato; il prodotto scalare viene comunque definito uguale a zero, coerentemente con il fattore nullo del modulo:

$$
\mathbf 0\cdot\mathbf w=0
$$

> ⚠️ Il risultato del prodotto scalare non è un vettore.

---

### **3. Ortogonalità e angolo**

Per vettori non nulli,

$$
\mathbf v\cdot\mathbf w=0
\iff \cos\theta=0
\iff \theta=\frac\pi2
$$

Quindi due vettori non nulli sono perpendicolari se e solo se il loro prodotto scalare è zero. Se è presente un vettore nullo, il prodotto è ancora zero, ma non si può parlare della sua direzione come perpendicolare.

Per $\mathbf v,\mathbf w\neq\mathbf 0$ si ricava

$$
\cos\theta=
\frac{\mathbf v\cdot\mathbf w}
{\lVert\mathbf v\rVert\,\lVert\mathbf w\rVert}
$$

Il segno del prodotto scalare permette già di classificare l'angolo:

- $\mathbf v\cdot\mathbf w>0$: angolo acuto;
- $\mathbf v\cdot\mathbf w=0$: angolo retto;
- $\mathbf v\cdot\mathbf w<0$: angolo ottuso.

---

### **4. Norma e proiezione**

Ponendo $\mathbf w=\mathbf v$ si ottiene

$$
\mathbf v\cdot\mathbf v=
\lVert\mathbf v\rVert^2\cos0=
\lVert\mathbf v\rVert^2
$$

da cui

$$
\lVert\mathbf v\rVert=
\sqrt{\mathbf v\cdot\mathbf v}
$$

Se $\mathbf v$ è un versore, cioè $\lVert\mathbf v\rVert=1$, allora

$$
\mathbf v\cdot\mathbf w=
\lVert\mathbf w\rVert\cos\theta
$$

è la **componente scalare orientata** di $\mathbf w$ nella direzione di $\mathbf v$. Può essere negativa se la proiezione è discordante con $\mathbf v$.

---

### **5. Proprietà algebriche**

Il prodotto scalare è **commutativo**:

$$
\mathbf v\cdot\mathbf w=
\mathbf w\cdot\mathbf v
$$

perché l'angolo e il prodotto dei moduli non cambiano scambiando i vettori.

È compatibile con il prodotto per scalare:

$$
k(\mathbf v\cdot\mathbf w)
=(k\mathbf v)\cdot\mathbf w
=\mathbf v\cdot(k\mathbf w)
$$

per ogni $k\in\mathbb R$. Per $k<0$, l'inversione del verso cambia il coseno da $\cos\theta$ a $-\cos\theta$, compensando il valore assoluto presente nel modulo.

È distributivo rispetto alla somma:

$$
(\mathbf v+\mathbf w)\cdot\mathbf u
=\mathbf v\cdot\mathbf u+
\mathbf w\cdot\mathbf u
$$

Più in generale,

$$
(h\mathbf v+k\mathbf w)\cdot\mathbf u
=h(\mathbf v\cdot\mathbf u)+
k(\mathbf w\cdot\mathbf u)
$$

Non ha senso chiedere l'associatività nella forma

$$
(\mathbf u\cdot\mathbf v)\cdot\mathbf w
$$

perché $\mathbf u\cdot\mathbf v$ è un numero reale e il secondo prodotto scalare richiederebbe due vettori.

> 💡 La proprietà corretta non è “non associativo”, ma “l'espressione associativa non è definita con questi tipi di operandi”.

---

### **6. Versori cartesiani**

In un riferimento cartesiano ortogonale monometrico, i versori degli assi sono unitari e a due a due perpendicolari:

$$
\mathbf i\cdot\mathbf i=
\mathbf j\cdot\mathbf j=
\mathbf k\cdot\mathbf k=1
$$

$$
\mathbf i\cdot\mathbf j=
\mathbf j\cdot\mathbf i=
\mathbf i\cdot\mathbf k=
\mathbf k\cdot\mathbf i=
\mathbf j\cdot\mathbf k=
\mathbf k\cdot\mathbf j=0
$$

---

### **7. Formula mediante le componenti**

#### **7.1. Nel piano**

Siano

$$
\mathbf v=
\begin{bmatrix}a\\b\end{bmatrix}
=a\mathbf i+b\mathbf j,
\qquad
\mathbf w=
\begin{bmatrix}c\\d\end{bmatrix}
=c\mathbf i+d\mathbf j
$$

Per bilinearità e ortonormalità della base,

$$
\begin{aligned}
\mathbf v\cdot\mathbf w
&=(a\mathbf i+b\mathbf j)\cdot(c\mathbf i+d\mathbf j)\\
&=ac(\mathbf i\cdot\mathbf i)
+ad(\mathbf i\cdot\mathbf j)
+bc(\mathbf j\cdot\mathbf i)
+bd(\mathbf j\cdot\mathbf j)\\
&=ac+bd.
\end{aligned}
$$

#### **7.2. Nello spazio**

Per

$$
\mathbf v=
\begin{bmatrix}a\\b\\c\end{bmatrix},
\qquad
\mathbf w=
\begin{bmatrix}m\\n\\p\end{bmatrix}
$$

si ottiene analogamente

$$
\mathbf v\cdot\mathbf w=am+bn+cp
$$

> ⚠️ Queste formule come somma dei prodotti delle componenti valgono direttamente soltanto in una base ortonormale. In una base obliqua compaiono anche i prodotti scalari tra i vettori di base.

---

### **8. Esempio completo**

Siano

$$
\mathbf v=\begin{bmatrix}1\\2\\-1\end{bmatrix},
\qquad
\mathbf w=\begin{bmatrix}2\\0\\2\end{bmatrix}
$$

Il prodotto scalare è

$$
\mathbf v\cdot\mathbf w=1\cdot2+2\cdot0+(-1)\cdot2=0
$$

quindi i vettori sono perpendicolari. Verifichiamo i moduli:

$$
\lVert\mathbf v\rVert=\sqrt{1+4+1}=\sqrt6,
\qquad
\lVert\mathbf w\rVert=\sqrt{4+0+4}=2\sqrt2
$$

La formula dell'angolo dà $\cos\theta=0$ e quindi $\theta=\pi/2$.

---

### **9. Errori comuni**

- Confondere prodotto scalare e moltiplicazione per scalare.
- Concludere che il vettore nullo è “perpendicolare a ogni vettore”: il suo prodotto scalare è zero, ma la sua direzione è indeterminata.
- Usare la formula del coseno quando uno dei moduli è zero.
- Cercare di applicare l'associatività a un'operazione con risultato scalare.

---

> ✅ Il prodotto scalare è $\mathbf v\cdot\mathbf w=\lVert\mathbf v\rVert\lVert\mathbf w\rVert\cos\theta$. In una base cartesiana ortonormale si calcola sommando i prodotti delle componenti corrispondenti. Consente di riconoscere l'ortogonalità, calcolare la norma, ricavare l'angolo e misurare proiezioni scalari.
