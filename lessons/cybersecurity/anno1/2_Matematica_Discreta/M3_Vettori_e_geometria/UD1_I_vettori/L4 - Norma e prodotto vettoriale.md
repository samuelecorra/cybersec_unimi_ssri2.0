# **M3 UD1 Lezione 4 - Norma e prodotto vettoriale**

### **1. Obiettivi e prerequisiti**

Completiamo le operazioni sui vettori introducendo norma, disuguaglianza di Cauchy-Schwarz, prodotto vettoriale e prodotto misto. Sono prerequisiti componenti, prodotto scalare e angolo tra vettori.

---

### **2. Norma euclidea**

La **norma**, o modulo, di un vettore è la sua lunghezza. Dalla relazione

$$
\lVert\mathbf v\rVert^2=\mathbf v\cdot\mathbf v
$$

segue, nel piano,

$$
\mathbf v=\begin{bmatrix}a\\b\end{bmatrix}
\quad\Longrightarrow\quad
\lVert\mathbf v\rVert=\sqrt{a^2+b^2}
$$

e nello spazio,

$$
\mathbf v=\begin{bmatrix}a\\b\\c\end{bmatrix}
\quad\Longrightarrow\quad
\lVert\mathbf v\rVert=\sqrt{a^2+b^2+c^2}
$$

La norma può anche essere caratterizzata assiomaticamente dalle proprietà:

1. **positività definita**:

$$
\lVert\mathbf v\rVert\geq0,
\qquad
\lVert\mathbf v\rVert=0\iff\mathbf v=\mathbf0
$$

2. **omogeneità assoluta**:

$$
\lVert k\mathbf v\rVert=|k|\,\lVert\mathbf v\rVert
$$

3. **disuguaglianza triangolare**:

$$
\lVert\mathbf v+\mathbf w\rVert
\leq\lVert\mathbf v\rVert+\lVert\mathbf w\rVert
$$

---

### **3. Disuguaglianza di Cauchy-Schwarz e angolo**

Dalla definizione di prodotto scalare e dal fatto che $|\cos\theta|\leq1$ segue

> 📌 **Disuguaglianza di Cauchy-Schwarz**
>
> $$
> |\mathbf v\cdot\mathbf w|
> \leq\lVert\mathbf v\rVert\,\lVert\mathbf w\rVert
> $$

Per vettori non nulli permette di garantire che

$$
-1\leq
\frac{\mathbf v\cdot\mathbf w}
{\lVert\mathbf v\rVert\lVert\mathbf w\rVert}
\leq1
$$

e dunque che il coseno dell'angolo sia ben definito.

Nel piano, per

$$
\mathbf v=\begin{bmatrix}a\\b\end{bmatrix},
\qquad
\mathbf w=\begin{bmatrix}c\\d\end{bmatrix}
$$

si ha

$$
\cos\theta=
\frac{ac+bd}
{\sqrt{a^2+b^2}\sqrt{c^2+d^2}}
$$

Nello spazio, per

$$
\mathbf v=\begin{bmatrix}a\\b\\c\end{bmatrix},
\qquad
\mathbf w=\begin{bmatrix}m\\n\\p\end{bmatrix}
$$

si ha

$$
\cos\theta=
\frac{am+bn+cp}
{\sqrt{a^2+b^2+c^2}\sqrt{m^2+n^2+p^2}}
$$

---

### **4. Prodotto vettoriale nello spazio**

Il **prodotto vettoriale** è definito per vettori dello spazio tridimensionale. Dati $\mathbf v,\mathbf w\in\mathbb R^3$, il vettore

$$
\mathbf u=\mathbf v\times\mathbf w
$$

è caratterizzato da:

- modulo

$$
\lVert\mathbf u\rVert=
\lVert\mathbf v\rVert\,\lVert\mathbf w\rVert,|\sin\theta|
$$

- direzione perpendicolare al piano contenente $\mathbf v$ e $\mathbf w$, quando i due vettori sono non nulli e non paralleli;
- verso determinato dalla **regola della mano destra**: pollice lungo $\mathbf v$, indice lungo $\mathbf w$, medio lungo $\mathbf v\times\mathbf w$.

Il modulo $\lVert\mathbf v\times\mathbf w\rVert$ è l'area del parallelogramma avente $\mathbf v$ e $\mathbf w$ come lati adiacenti. L'area del triangolo costruito sugli stessi vettori è quindi

$$
A=\frac12\lVert\mathbf v\times\mathbf w\rVert
$$

**Rappresentazione della pagina 2 del PDF.** La figura mostra $\mathbf v$ e $\mathbf w$ nel loro piano e $\mathbf u$ sulla retta perpendicolare, orientata secondo la mano destra.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Proprietà del prodotto vettoriale**

Se uno dei vettori è nullo, il prodotto è nullo. Inoltre, per vettori non nulli,

$$
\mathbf v\times\mathbf w=\mathbf0
\iff \sin\theta=0
\iff \mathbf v\parallel\mathbf w
$$

Il prodotto vettoriale è **anticommutativo**:

$$
\mathbf v\times\mathbf w
=-(\mathbf w\times\mathbf v)
$$

Per i versori cartesiani valgono

$$
\mathbf i\times\mathbf i=
\mathbf j\times\mathbf j=
\mathbf k\times\mathbf k=\mathbf0
$$

$$
\mathbf i\times\mathbf j=\mathbf k,
\qquad
\mathbf j\times\mathbf k=\mathbf i,
\qquad
\mathbf k\times\mathbf i=\mathbf j
$$

mentre invertendo l'ordine cambia il segno:

$$
\mathbf j\times\mathbf i=-\mathbf k,
\qquad
\mathbf k\times\mathbf j=-\mathbf i,
\qquad
\mathbf i\times\mathbf k=-\mathbf j
$$

Non è associativo. Il controesempio del PDF è

$$
(\mathbf i\times\mathbf i)\times\mathbf k
=\mathbf0
$$

mentre

$$
\mathbf i\times(\mathbf i\times\mathbf k)
=\mathbf i\times(-\mathbf j)=-\mathbf k
$$

È omogeneo e distributivo:

$$
k(\mathbf v\times\mathbf w)
=(k\mathbf v)\times\mathbf w
=\mathbf v\times(k\mathbf w)
$$

$$
\mathbf v\times(\mathbf w+\mathbf z)
=\mathbf v\times\mathbf w+
\mathbf v\times\mathbf z
$$

---

### **6. Calcolo mediante le componenti**

Siano

$$
\mathbf v=
\begin{bmatrix}a\\b\\c\end{bmatrix}
=a\mathbf i+b\mathbf j+c\mathbf k,
\qquad
\mathbf w=
\begin{bmatrix}m\\n\\p\end{bmatrix}
=m\mathbf i+n\mathbf j+p\mathbf k
$$

Sviluppando con distributività e usando la tavola dei prodotti dei versori:

$$
\begin{aligned}
\mathbf v\times\mathbf w
&=(a\mathbf i+b\mathbf j+c\mathbf k)
\times(m\mathbf i+n\mathbf j+p\mathbf k)\\
&=(bp-cn)\mathbf i+(cm-ap)\mathbf j+(an-bm)\mathbf k.
\end{aligned}
$$

Quindi

$$
\boxed{
\mathbf v\times\mathbf w=
\begin{bmatrix}
bp-cn\\
cm-ap\\
an-bm
\end{bmatrix}}
$$

Come promemoria si usa lo sviluppo formale

$$
\mathbf v\times\mathbf w=
\det\begin{bmatrix}
\mathbf i&\mathbf j&\mathbf k\\
a&b&c\\
m&n&p
\end{bmatrix}
$$

La nozione generale di determinante verrà sviluppata più avanti; qui questa scrittura serve a ricordare segni e componenti.

**Esempio.** Per

$$
\mathbf v=\begin{bmatrix}1\\2\\0\end{bmatrix},
\qquad
\mathbf w=\begin{bmatrix}0\\1\\3\end{bmatrix}
$$

si ottiene

$$
\mathbf v\times\mathbf w=
\begin{bmatrix}6\\-3\\1\end{bmatrix}
$$

La verifica di ortogonalità è

$$
\begin{bmatrix}6\\-3\\1\end{bmatrix}
\cdot
\begin{bmatrix}1\\2\\0\end{bmatrix}=6-6=0
$$

$$
\begin{bmatrix}6\\-3\\1\end{bmatrix}
\cdot
\begin{bmatrix}0\\1\\3\end{bmatrix}=-3+3=0
$$

---

### **7. Prodotto misto**

Siano

$$
\mathbf v=\begin{bmatrix}a\\b\\c\end{bmatrix},
\qquad
\mathbf w=\begin{bmatrix}m\\n\\p\end{bmatrix},
\qquad
\mathbf z=\begin{bmatrix}r\\s\\t\end{bmatrix}
$$

Il **prodotto misto** è il numero reale

$$
(\mathbf v\times\mathbf w)\cdot\mathbf z
$$

Le parentesi sono logicamente determinate dai tipi: bisogna prima eseguire il prodotto vettoriale e poi quello scalare. Mediante le componenti,

$$
\begin{aligned}
(\mathbf v\times\mathbf w)\cdot\mathbf z
&=r(bp-cn)+s(cm-ap)+t(an-bm)\\
&=\det\begin{bmatrix}
r&s&t\\
a&b&c\\
m&n&p
\end{bmatrix}.
\end{aligned}
$$

Il valore assoluto del prodotto misto è il volume del parallelepipedo generato dai tre vettori. Pertanto

$$
 (\mathbf v\times\mathbf w)\cdot\mathbf z=0
$$

se i tre vettori sono complanari, inclusi i casi degeneri in cui uno è nullo o due sono paralleli.

---

### **8. Refuso nella fonte**

Nella sintesi della pagina 4 il PDF afferma: “Il prodotto scalare di due vettori è un vettore”. Il contesto della lezione e la definizione precedente mostrano che si tratta di un refuso: la formulazione corretta è

> 📌 Il **prodotto vettoriale** di due vettori di $\mathbb R^3$ è un vettore; il prodotto scalare è invece un numero reale.

---

### **9. Errori comuni**

- Dimenticare che $\mathbf v\times\mathbf w$ cambia segno invertendo l'ordine.
- Usare il prodotto vettoriale standard per vettori del piano senza prima immergerli in $\mathbb R^3$ con terza componente nulla.
- Confondere il prodotto misto, scalare, con un doppio prodotto vettoriale.
- Omettere il valore assoluto nell'interpretazione di area o volume.

---

> ✅ La norma euclidea deriva dal prodotto scalare. Il prodotto vettoriale in $\mathbb R^3$ produce un vettore perpendicolare ai fattori, con modulo uguale all'area del parallelogramma. Il prodotto misto produce uno scalare, il cui valore assoluto è il volume del parallelepipedo e che si annulla per vettori complanari.
