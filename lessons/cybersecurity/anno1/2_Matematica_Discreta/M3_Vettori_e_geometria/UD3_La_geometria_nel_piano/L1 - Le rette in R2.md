# **M3 UD3 Lezione 1 - Le rette in $\mathbb R^2$**

### **1. Obiettivi e prerequisiti**

Questa lezione descrive una retta del piano mediante un punto e una direzione, passando dalla forma vettoriale alle equazioni parametriche e cartesiane. Sono prerequisiti componenti, parallelismo e prodotto scalare.

---

### **2. Retta come luogo geometrico**

Due vettori non nulli $\mathbf v$ e $\mathbf w$ hanno la stessa direzione se esiste $t\in\mathbb R\setminus\{0\}$ tale che

$$
\mathbf v=t\mathbf w
$$

Se $t>0$ hanno anche lo stesso verso; se $t<0$ hanno versi opposti.

Fissati un punto $Q\in\mathbb R^2$ e un vettore non nullo $\mathbf v$, esiste una sola retta passante per $Q$ con direzione $\mathbf v$. Essa è il luogo

$$
r=\left\{P\in\mathbb R^2:
\overrightarrow{QP}=t\mathbf v,
\ t\in\mathbb R\right\}
$$

Il vettore $\mathbf v$ è detto **vettore direzione** della retta.

Per $t=0$ si ottiene $P=Q$; valori positivi e negativi di $t$ percorrono le due semirette opposte.

---

### **3. Equazioni parametriche**

Siano

$$
Q\equiv(r,s),
\qquad
\mathbf v=\begin{bmatrix}m\\n\end{bmatrix},
\qquad
P\equiv(x,y)
$$

Dall'equazione vettoriale

$$
\overrightarrow{QP}=t\mathbf v
$$

segue

$$
\begin{bmatrix}
x-r\\y-s
\end{bmatrix}
=t
\begin{bmatrix}
m\\n
\end{bmatrix},
$$

cioè le equazioni parametriche

$$
\begin{cases}
x=r+mt,\\
y=s+nt,
\end{cases}
\qquad t\in\mathbb R.
$$

Moltiplicare $\mathbf v$ per uno scalare non nullo produce una parametrizzazione diversa della stessa retta.

#### **3.1. Retta per due punti**

Siano $A=(x_A,y_A)$ e $B=(x_B,y_B)$ distinti. Un vettore direzione è

$$
\overrightarrow{AB}=
\begin{bmatrix}
x_B-x_A\\y_B-y_A
\end{bmatrix}
$$

La retta per $A$ e $B$ ha equazioni

$$
\begin{cases}
x=x_A+(x_B-x_A)t,\\
y=y_A+(y_B-y_A)t.
\end{cases}
$$

I valori notevoli sono:

- $t=0$: il punto $A$;
- $t=1$: il punto $B$;
- $t=\frac12$: il punto medio di $AB$;
- $t\geq0$: la semiretta con origine $A$ contenente $B$;
- $t<0$: la semiretta opposta.

---

### **4. Equazione cartesiana**

Eliminiamo $t$ dal sistema

$$
x=r+mt,
\qquad y=s+nt
$$

Moltiplicando la prima relazione per $n$, la seconda per $m$ e sottraendo:

$$
n(x-r)-m(y-s)=0
$$

Posto

$$
a=n,
\qquad b=-m,
\qquad c=-nr+ms
$$

si ottiene la forma generale

$$
\boxed{ax+by+c=0}
$$

con $(a,b)\neq(0,0)$.

Il vettore

$$
\mathbf n=\begin{bmatrix}a\\b\end{bmatrix}
$$

è perpendicolare alla retta ed è detto **vettore normale**, mentre un vettore direzione può essere scelto come

$$
\mathbf v=\begin{bmatrix}-b\\a\end{bmatrix}
$$

oppure come un suo multiplo non nullo. Infatti

$$
\mathbf n\cdot\mathbf v=a(-b)+ba=0
$$

> 📌 Una retta è determinata da un punto e da un vettore direzione, oppure da un punto e da un vettore normale.

---

### **5. Dalla forma cartesiana alla parametrica**

Data

$$
ax+by+c=0
$$

si sceglie un punto $Q=(r,s)$ che soddisfi l'equazione e si usa $(-b,a)$ come direzione:

$$
\begin{cases}
x=r-bt,\\
y=s+at.
\end{cases}
$$

Se $b\neq0$, per trovare $Q$ si può porre $x=0$ e calcolare $y=-c/b$. Se $b=0$, allora $a\neq0$ e si può porre $y=0$, ottenendo $x=-c/a$.

---

### **6. Esempio 1: retta per due punti**

Determiniamo le equazioni della retta per

$$
A=(1,4),
\qquad B=(-1,3)
$$

Il vettore direzione è

$$
\overrightarrow{AB}=
\begin{bmatrix}-2\\-1\end{bmatrix}
$$

Le equazioni parametriche annotate nel PDF sono

$$
\begin{cases}
x=1-2t,\\
y=4-t.
\end{cases}
$$

Per eliminare $t$, dalla seconda equazione $t=4-y$; sostituendo nella prima,

$$
x=1-2(4-y)=2y-7
$$

Quindi

$$
\boxed{x-2y+7=0}
$$

Verifica:

$$
1-2\cdot4+7=0,
\qquad
-1-2\cdot3+7=0
$$

---

### **7. Esempio 2: retta perpendicolare a un vettore**

Siano

$$
A=(4,-1),
\qquad B=(2,3)
$$

Cerchiamo la retta passante per $A$ e perpendicolare a $\overrightarrow{AB}$. Si ha

$$
\overrightarrow{AB}=
\begin{bmatrix}-2\\4\end{bmatrix}
$$

Poiché questo vettore è normale alla retta cercata,

$$
-2(x-4)+4(y+1)=0
$$

Sviluppando e dividendo per $2$:

$$
\boxed{-x+2y+6=0}
$$

Un vettore direzione della retta è $(2,1)$, ortogonale a $(-2,4)$:

$$
(-2,4)\cdot(2,1)=-4+4=0
$$

Una forma parametrica è quindi

$$
\begin{cases}
x=4+2t,\\
y=-1+t.
\end{cases}
$$

---

### **8. Errori comuni**

- Usare $B$ invece di $B-A$ come vettore direzione della retta per $A$ e $B$.
- Dimenticare che $A$ e $B$ devono essere distinti.
- Confondere il vettore normale $(a,b)$ con un vettore direzione.
- Dividere per $m$ o $n$ per eliminare il parametro senza gestire il caso in cui uno sia zero.
- Credere che una parametrizzazione sia unica: si può cambiare punto base e riscalare il vettore direzione.

---

> ✅ La retta per $Q=(r,s)$ con direzione $(m,n)$ ha equazioni $x=r+mt$, $y=s+nt$. Eliminando il parametro si ottiene $n(x-r)-m(y-s)=0$, cioè $ax+by+c=0$. Il vettore $(a,b)$ è normale alla retta e $(-b,a)$ ne è un vettore direzione.
