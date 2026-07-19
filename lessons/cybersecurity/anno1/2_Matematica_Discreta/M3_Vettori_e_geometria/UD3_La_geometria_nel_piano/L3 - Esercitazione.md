# **M3 UD3 Lezione 3 - Esercitazione sulla geometria nel piano**

### **1. Obiettivi**

La lezione raccoglie i due esercizi della fonte e ne ricostruisce integralmente i passaggi annotati: intersezioni con gli assi, rette perpendicolari, area di un quadrato e completamento di un quadrato a partire da tre vertici.

---

### **2. Esercizio 1: retta, perpendicolare e area**

Nel piano si consideri

$$
s:2y-x+1=0
$$

Si richiede di:

1. trovare $Q=s\cap$ asse $y$ e $P=s\cap$ asse $x$;
2. trovare la retta $t$ passante per $P$ e perpendicolare a $s$;
3. trovare $R=t\cap$ asse $y$;
4. calcolare l'area del quadrato di diagonale $PQ$.

#### **2.1. Intersezione con l'asse $y$**

Sull'asse $y$ vale $x=0$. Sostituendo:

$$
2y+1=0
\quad\Longrightarrow\quad
y=-\frac12
$$

Quindi

$$
\boxed{Q=\left(0,-\frac12\right)}
$$

#### **2.2. Intersezione con l'asse $x$**

Sull'asse $x$ vale $y=0$. Sostituendo:

$$
-x+1=0
\quad\Longrightarrow\quad x=1
$$

Quindi

$$
\boxed{P=(1,0)}
$$

#### **2.3. Retta perpendicolare per $P$**

La retta $s$ ha vettore normale $(-1,2)$. Una retta perpendicolare a $s$ può usare questo vettore come direzione; di conseguenza ha un normale ortogonale, per esempio $(2,1)$.

Passando per $P=(1,0)$:

$$
2(x-1)+(y-0)=0
$$

quindi

$$
\boxed{t:2x+y-2=0}
$$

Verifica sui normali:

$$
(-1,2)\cdot(2,1)=-2+2=0
$$

#### **2.4. Intersezione di $t$ con l'asse $y$**

Ponendo $x=0$ in $t$:

$$
y-2=0
\quad\Longrightarrow\quad
\boxed{R=(0,2)}
$$

#### **2.5. Area del quadrato di diagonale $PQ$**

La lunghezza della diagonale è

$$
\begin{aligned}
|PQ|
&=\sqrt{(1-0)^2+
\left(0+\frac12\right)^2}\\
&=\sqrt{1+\frac14}
=\frac{\sqrt5}{2}.
\end{aligned}
$$

Se $d$ è la diagonale di un quadrato e $\ell$ il lato, allora $d=\ell\sqrt2$, dunque

$$
A=\ell^2=\frac{d^2}{2}
$$

Pertanto

$$
A=\frac12\left(\frac{\sqrt5}{2}\right)^2
=\boxed{\frac58}
$$

La costruzione grafica della pagina 2 mostra $s$, la perpendicolare $t$, i punti $P,Q,R$ e un quadrato costruito sulla diagonale $PQ$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Esercizio 2: completare un quadrato**

Si considerino

$$
A=(2,2),
\qquad
B=(-2,-1),
\qquad
C=(1,-5)
$$

Si richiede di:

1. scrivere la retta $r$ per $A$ e $B$;
2. scrivere la retta $s$ per $B$ e $C$;
3. verificare che $r$ e $s$ siano perpendicolari;
4. verificare che $A,B,C$ siano tre vertici consecutivi di un quadrato;
5. trovare il quarto vertice.

#### **3.1. Retta $r$ per $A$ e $B$**

Il vettore direzione è

$$
\overrightarrow{AB}=B-A=
\begin{bmatrix}-4\\-3\end{bmatrix}
$$

Una parametrizzazione è

$$
r:
\begin{cases}
x=2-4t,\\
y=2-3t.
\end{cases}
$$

Un normale è $(3,-4)$; usando $A$:

$$
3(x-2)-4(y-2)=0
$$

quindi

$$
\boxed{r:3x-4y+2=0}
$$

#### **3.2. Retta $s$ per $B$ e $C$**

Il vettore direzione è

$$
\overrightarrow{BC}=C-B=
\begin{bmatrix}3\\-4\end{bmatrix}
$$

Una parametrizzazione è

$$
s:
\begin{cases}
x=-2+3h,\\
y=-1-4h.
\end{cases}
$$

Un normale è $(4,3)$; usando $B$:

$$
4(x+2)+3(y+1)=0
$$

quindi

$$
\boxed{s:4x+3y+11=0}
$$

#### **3.3. Perpendicolarità**

Calcoliamo

$$
\overrightarrow{AB}\cdot\overrightarrow{BC}
=(-4)\cdot3+(-3)\cdot(-4)
=-12+12=0
$$

I lati $AB$ e $BC$ sono quindi perpendicolari.

#### **3.4. Uguaglianza dei lati**

$$
|AB|=\sqrt{(-4)^2+(-3)^2}=5
$$

$$
|BC|=\sqrt{3^2+(-4)^2}=5
$$

Due segmenti consecutivi, perpendicolari e di uguale lunghezza sono due lati consecutivi di un quadrato. Dunque $A,B,C$ sono tre vertici consecutivi, con angolo retto in $B$.

#### **3.5. Quarto vertice**

Nel parallelogramma $ABCD$ con vertici consecutivi $A,B,C,D$ vale

$$
\overrightarrow{AD}=\overrightarrow{BC}
$$

quindi

$$
D=A+(C-B)
$$

Calcolando:

$$
D=(2,2)+(3,-4)=\boxed{(5,-2)}
$$

La soluzione annotata nel PDF trova lo stesso punto intersecando:

- la retta per $A$ parallela a $BC$:

$$
\begin{cases}
x=2+3t,\\
y=2-4t;
\end{cases}
$$

- la retta per $C$ parallela ad $AB$:

$$
\begin{cases}
x=1-4h,\\
y=-5-3h.
\end{cases}
$$

Il sistema porta a $t=1$, $h=-1$ e dunque $D=(5,-2)$.

#### **3.6. Verifica finale**

$$
\overrightarrow{CD}=D-C=
\begin{bmatrix}4\\3\end{bmatrix}
=-\overrightarrow{AB}
$$

$$
\overrightarrow{AD}=D-A=
\begin{bmatrix}3\\-4\end{bmatrix}
=\overrightarrow{BC}
$$

I lati opposti sono paralleli e congruenti, mentre i lati consecutivi sono perpendicolari e lunghi $5$.

---

### **4. Errori comuni**

- Calcolare l'area del quadrato come $d^2$ invece che $d^2/2$.
- Trovare una retta perpendicolare senza imporre il passaggio per il punto dato.
- Verificare soltanto l'angolo retto tra $AB$ e $BC$: per un quadrato serve anche $|AB|=|BC|$.
- Usare $A+B-C$ per il quarto vertice: con l'ordine $A,B,C,D$ la formula corretta è $D=A+C-B$.

---

> ✅ Il primo esercizio produce $P=(1,0)$, $Q=(0,-1/2)$, $R=(0,2)$ e area $5/8$. Nel secondo, i vettori $AB=(-4,-3)$ e $BC=(3,-4)$ sono ortogonali e lunghi $5$; il quarto vertice del quadrato è $D=(5,-2)$.
