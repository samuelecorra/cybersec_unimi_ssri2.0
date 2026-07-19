# **M3 UD4 Lezione 1 - Le rette in $\mathbb R^3$**

### **1. Obiettivi e prerequisiti**

Estendiamo allo spazio la descrizione parametrica delle rette e studiamo parallelismo, perpendicolarità e incidenza. Sono prerequisiti le componenti in $\mathbb R^3$, il prodotto scalare e i sistemi lineari.

---

### **2. Retta nello spazio**

Fissati un punto $Q\in\mathbb R^3$ e un vettore non nullo $\mathbf v$, la retta passante per $Q$ e diretta come $\mathbf v$ è

$$
r=\left\{P\in\mathbb R^3:
\overrightarrow{QP}=k\mathbf v,
\ k\in\mathbb R\right\}
$$

Il vettore $\mathbf v$ è un **vettore direzione** della retta.

Se $A$ e $B$ sono due punti distinti, la retta per $A$ e $B$ è

$$
\overrightarrow{AP}=k\overrightarrow{AB}
$$

---

### **3. Equazioni parametriche**

Siano

$$
Q=(a,b,c),
\qquad
\mathbf v=
\begin{bmatrix}r\\s\\t\end{bmatrix},
\qquad
P=(x,y,z)
$$

Dalla relazione

$$
\begin{bmatrix}
x-a\\y-b\\z-c
\end{bmatrix}
=k
\begin{bmatrix}
r\\s\\t
\end{bmatrix}
$$

si ottengono le equazioni parametriche

$$
\boxed{
\begin{cases}
x=a+kr,\\
y=b+ks,\\
z=c+kt,
\end{cases}
\qquad k\in\mathbb R.}
$$

Nel piano una retta è descritta da un'equazione cartesiana; nello spazio, eliminando un parametro da tre equazioni, si ottengono in generale **due** equazioni cartesiane indipendenti. Una retta di $\mathbb R^3$ può infatti essere vista come intersezione di due piani non paralleli.

---

### **4. Parallelismo e perpendicolarità**

Siano $r$ e $s$ rette con vettori direzione $\mathbf v$ e $\mathbf u$.

$$
r\parallel s
\iff
\mathbf v=h\mathbf u
\quad\text{per qualche }h\neq0
$$

$$
r\perp s
\Longrightarrow
\mathbf v\cdot\mathbf u=0
$$

Nello spazio l'ortogonalità dei vettori direzione non garantisce che le rette si incontrino. Perché due rette siano geometricamente perpendicolari devono essere sia ortogonali sia incidenti.

> ⚠️ In $\mathbb R^3$ due rette con direzioni ortogonali possono essere sghembe.

#### **4.1. Esempio: rette per l'origine**

Data

$$
r:
\begin{cases}
x=1-t,\\
y=2+t,\\
z=3-t,
\end{cases}
$$

un vettore direzione è $\mathbf v=(-1,1,-1)$.

La parallela per l'origine è

$$
\boxed{
\begin{cases}
x=-t,\\
y=t,\\
z=-t.
\end{cases}}
$$

Per una perpendicolare per l'origine, cerchiamo $\mathbf u=(a,b,c)$ tale che

$$
\mathbf u\cdot\mathbf v=-a+b-c=0
$$

Quindi $b=a+c$ e tutte le direzioni ortogonali sono

$$
\mathbf u=(a,a+c,c),
\qquad (a,c)\neq(0,0)
$$

La famiglia di rette è

$$
\begin{cases}
x=at,\\
y=(a+c)t,\\
z=ct.
\end{cases}
$$

Per esempio, scegliendo $(a,c)=(1,0)$ si ottiene la retta di direzione $(1,1,0)$.

> 💡 A differenza del piano, attraverso un punto passano infinite rette perpendicolari a una direzione data: le loro direzioni formano un intero piano vettoriale.

---

### **5. Incidenza di rette**

Siano

$$
r:
\begin{cases}
x=x_0+tr,\\
y=y_0+ts,\\
z=z_0+tv,
\end{cases}
\qquad
s:
\begin{cases}
x=x_1+ha,\\
y=y_1+hb,\\
z=z_1+hc.
\end{cases}
$$

Per cercare un punto comune si risolve

$$
\begin{cases}
x_0+tr=x_1+ha,\\
y_0+ts=y_1+hb,\\
z_0+tv=z_1+hc.
\end{cases}
$$

Occorrono parametri distinti $t$ e $h$. Il sistema ha tre equazioni in due incognite e può risultare:

- compatibile: le rette sono incidenti o coincidenti;
- incompatibile con direzioni parallele: rette parallele distinte;
- incompatibile con direzioni non parallele: rette sghembe.

---

### **6. Esempio 1: parametro di incidenza**

Stabiliamo per quale valore di $k$ sono incidenti

$$
r:
\begin{cases}
x=1-t,\\
y=2-t,\\
z=3-t,
\end{cases}
$$

e

$$
s:
\begin{cases}
x=1+kh,\\
y=3+(3-k)h,\\
z=4+2kh.
\end{cases}
$$

Uguagliando le coordinate:

$$
\begin{cases}
-t=kh,\\
-1-t=(3-k)h,\\
-1-t=2kh.
\end{cases}
$$

Dalla prima $t=-kh$. La terza diventa

$$
-1+kh=2kh
\quad\Longrightarrow\quad
kh=-1
$$

Quindi $t=1$. La seconda fornisce

$$
-2=(3-k)h
$$

Poiché $h=-1/k$,

$$
-2=-\frac{3-k}{k}
\quad\Longrightarrow\quad
2k=3-k
\quad\Longrightarrow\quad
\boxed{k=1}
$$

Ne seguono $h=-1$, $t=1$ e il punto comune

$$
\boxed{P=(0,1,2)}
$$

---

### **7. Esempio 2: perpendicolare incidente da un punto**

Sia

$$
P=(1,0,1)
$$

e sia

$$
r:
\begin{cases}
x=1-t,\\
y=2+t,\\
z=1+2t.
\end{cases}
$$

Cerchiamo il piede $R(t)$ della perpendicolare da $P$ a $r$:

$$
R(t)=(1-t,2+t,1+2t)
$$

Il vettore

$$
\overrightarrow{PR}=R-P=(-t,2+t,2t)
$$

deve essere ortogonale al vettore direzione di $r$,

$$
\mathbf v=(-1,1,2)
$$

Imponendo il prodotto scalare nullo:

$$
(-t)(-1)+(2+t)\cdot1+(2t)\cdot2=0
$$

$$
6t+2=0
\quad\Longrightarrow\quad
t=-\frac13
$$

Pertanto

$$
R=\left(\frac43,\frac53,\frac13\right)
$$

e

$$
\overrightarrow{PR}=
\begin{bmatrix}
\frac13\\[2pt]\frac53\\[2pt]-\frac23
\end{bmatrix}
$$

La retta cercata è

$$
\boxed{
\begin{cases}
x=1+\frac h3,\\
y=\frac{5h}{3},\\
z=1-\frac{2h}{3}.
\end{cases}}
$$

Equivalentemente si può usare la direzione intera $(1,5,-2)$:

$$
x=1+\lambda,
\qquad y=5\lambda,
\qquad z=1-2\lambda
$$

---

### **8. Errori comuni**

- Usare lo stesso parametro per due rette diverse nell'equazione di incidenza.
- Concludere che due rette sono perpendicolari dal solo prodotto scalare nullo senza controllare l'incidenza.
- Dimenticare che due rette non incidenti possono essere parallele oppure sghembe.
- Cercare una singola equazione cartesiana per una retta nello spazio.

---

> ✅ Una retta di $\mathbb R^3$ è $P=Q+t\mathbf v$ e possiede tre equazioni parametriche. Parallelismo e ortogonalità si leggono dai vettori direzione, ma la perpendicolarità geometrica richiede anche l'incidenza. Quest'ultima si determina uguagliando le coordinate con parametri distinti.
