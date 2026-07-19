# **M3 UD1 Lezione 2 - Componenti di un vettore**

### **1. Obiettivi e prerequisiti**

Partendo dalle operazioni geometriche sui vettori, introduciamo sistemi di riferimento, proiezioni e componenti nel piano e nello spazio. Sono prerequisiti la somma di vettori e il prodotto per uno scalare.

Al termine sapremo distinguere nettamente:

- una **proiezione**, che è un vettore;
- una **componente**, che è un numero reale;
- coordinate di un punto e componenti del corrispondente vettore applicato nell'origine.

---

### **2. Sistemi di riferimento nel piano**

Un sistema di riferimento del piano è **cartesiano** quando è costituito da due assi incidenti e orientati. È inoltre:

- **ortogonale** se gli assi sono perpendicolari;
- **monometrico** se sui due assi si usa la stessa unità di misura.

Il riferimento standard del corso è cartesiano, ortogonale e monometrico, con origine $O$, asse $x$ orizzontale e asse $y$ verticale.

> 💡 In applicazioni fisiche, elettroniche o statistiche si possono usare riferimenti obliqui o scale diverse. Le formule più semplici di questa unità dipendono però dall'ortogonalità e dalla stessa unità di misura.

---

### **3. Scomposizione lungo due rette**

Fissate due rette incidenti $r$ e $s$, ogni vettore $\mathbf v$ del piano si scompone in modo unico come

$$
\mathbf v=\mathbf u+\mathbf w
$$

dove $\mathbf u$ è parallelo a $r$ e $\mathbf w$ è parallelo a $s$. I vettori $\mathbf u$ e $\mathbf w$ sono le **proiezioni oblique** di $\mathbf v$ lungo le due direzioni fissate.

L'unicità deriva dal fatto che due direzioni distinte nel piano costituiscono una base: se

$$
\mathbf v=\mathbf u_1+\mathbf w_1=\mathbf u_2+\mathbf w_2
$$

allora $\mathbf u_1-\mathbf u_2=-(\mathbf w_1-\mathbf w_2)$ è parallelo sia a $r$ sia a $s$; poiché le rette sono distinte, tale vettore è nullo.

Se si usano tre rette incidenti, una scomposizione è ancora possibile ma non è più unica: le tre direzioni generano il piano, ma sono linearmente dipendenti.

**Rappresentazione della pagina 2 del PDF.** Lo stesso vettore è mostrato come somma di tre vettori paralleli a tre rette incidenti in due modi diversi, visualizzando la perdita di unicità.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Componenti nel piano**

Nel riferimento cartesiano ortogonale standard indichiamo con $\mathbf i$ e $\mathbf j$ i versori degli assi $x$ e $y$:

$$
\lVert\mathbf i\rVert=\lVert\mathbf j\rVert=1
$$

Sia $\mathbf v$ applicato nell'origine e sia $Q\equiv(a,b)$ il suo punto finale. Le proiezioni sui due assi sono

$$
\mathbf v_x=a\mathbf i,
\qquad
\mathbf v_y=b\mathbf j
$$

e quindi

$$
\mathbf v=\mathbf v_x+\mathbf v_y=a\mathbf i+b\mathbf j
$$

I numeri $a$ e $b$ sono le **componenti** di $\mathbf v$ e si raccolgono nel vettore colonna

$$
\mathbf v=
\begin{bmatrix}
a\\
b
\end{bmatrix}
$$

> 📌 Le proiezioni $\mathbf v_x$ e $\mathbf v_y$ sono vettori; le componenti $a$ e $b$ sono scalari.

Se $\mathbf v$ è applicato nell'origine, le sue componenti coincidono con le coordinate del punto finale. Se il rappresentante parte da $P\equiv(x_P,y_P)$ e termina in $Q\equiv(x_Q,y_Q)$, allora

$$
\mathbf v=overrightarrow{PQ}=
\begin{bmatrix}
x_Q-x_P\\
y_Q-y_P
\end{bmatrix}
$$

**Esempio.** Se $P=(2,-1)$ e $Q=(5,3)$, allora

$$
\overrightarrow{PQ}=
\begin{bmatrix}3\\4\end{bmatrix}
=3\mathbf i+4\mathbf j
$$

---

### **5. Sistema di riferimento nello spazio**

Nello spazio il riferimento standard è formato da tre assi $x,y,z$ incidenti nell'origine, a due a due perpendicolari e con la stessa unità di misura. I rispettivi versori sono $\mathbf i,\mathbf j,\mathbf k$.

Tre rette $r,s,t$ incidenti in un punto $P$ e non complanari consentono di scomporre univocamente ogni vettore $\mathbf v$ nelle tre direzioni.

La costruzione geometrica del PDF usa un parallelepipedo: dal punto finale di $\mathbf v$ si tracciano i piani paralleli ai piani $(r,s)$, $(r,t)$ e $(s,t)$. Le loro intersezioni con gli assi individuano tre spigoli $\overrightarrow{PR}$, $\overrightarrow{PS}$ e $\overrightarrow{PT}$, dei quali $\mathbf v$ è la diagonale:

$$
\mathbf v=\overrightarrow{PR}+\overrightarrow{PS}+\overrightarrow{PT}
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Componenti nello spazio**

Sia $P\equiv(a,b,c)$ il punto finale di un vettore $\mathbf v$ applicato nell'origine. Le proiezioni sui tre assi sono

$$
\mathbf v_x=a\mathbf i,
\qquad
\mathbf v_y=b\mathbf j,
\qquad
\mathbf v_z=c\mathbf k
$$

Pertanto

$$
\mathbf v=a\mathbf i+b\mathbf j+c\mathbf k
=
\begin{bmatrix}
a\\b\\c
\end{bmatrix}
$$

Se invece $\mathbf v=\overrightarrow{PQ}$, con

$$
P=(x_P,y_P,z_P),
\qquad Q=(x_Q,y_Q,z_Q)
$$

allora

$$
\overrightarrow{PQ}=
\begin{bmatrix}
x_Q-x_P\\
y_Q-y_P\\
z_Q-z_P
\end{bmatrix}
$$

---

### **7. Operazioni componente per componente**

La proiezione è compatibile con somma e prodotto per scalare. Di conseguenza, se

$$
\mathbf v=\begin{bmatrix}a\\b\\c\end{bmatrix},
\qquad
\mathbf w=\begin{bmatrix}m\\n\\p\end{bmatrix}
$$

allora

$$
\mathbf v+\mathbf w=
\begin{bmatrix}
a+m\\b+n\\c+p
\end{bmatrix}
$$

e, per $k\in\mathbb R$,

$$
k\mathbf v=
\begin{bmatrix}
ka\\kb\\kc
\end{bmatrix}
$$

Le formule piane si ottengono eliminando la terza componente.

**Esempio.** Per

$$
\mathbf v=\begin{bmatrix}1\\-2\\3\end{bmatrix},
\qquad
\mathbf w=\begin{bmatrix}4\\1\\0\end{bmatrix}
$$

si ha

$$
2\mathbf v-\mathbf w=
\begin{bmatrix}2\\-4\\6\end{bmatrix}
-
\begin{bmatrix}4\\1\\0\end{bmatrix}
=
\begin{bmatrix}-2\\-5\\6\end{bmatrix}
$$

---

### **8. Errori comuni**

- Chiamare “componente” il vettore proiezione.
- Dimenticare che la scomposizione lungo tre direzioni del piano non è unica.
- Confondere le coordinate del punto iniziale con le componenti di $\overrightarrow{PQ}$: occorre sottrarre punto finale meno punto iniziale.
- Usare formule ortogonali in un riferimento obliquo senza tener conto degli angoli fra gli assi.

---

> ✅ Nel piano un vettore si esprime univocamente come $a\mathbf i+b\mathbf j$ e nello spazio come $a\mathbf i+b\mathbf j+c\mathbf k$. Le proiezioni sono vettori, mentre $a,b,c$ sono scalari. Somma e prodotto per scalare si calcolano componente per componente.
