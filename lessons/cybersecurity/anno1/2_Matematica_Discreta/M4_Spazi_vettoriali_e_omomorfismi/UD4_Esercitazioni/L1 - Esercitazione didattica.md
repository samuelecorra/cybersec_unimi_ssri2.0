# **M4 UD4 Lezione 1 - Esercitazione didattica**

### **1. Parte I - Riconoscere sottospazi di matrici**

#### **1.1. Condizione necessaria e criterio**

Se $W$ è un sottospazio di $V$, necessariamente

$$
0_V\in W.
$$

Per un sottoinsieme non vuoto, il criterio completo è

$$
\forall u,v\in W\ \forall\alpha,\beta\in K,
\qquad \alpha u+\beta v\in W.
$$

In modo equivalente, basta controllare la presenza dello zero e la chiusura rispetto a somma e prodotto per scalari.

Consideriamo ora sottoinsiemi di $M_{2\times2}(\mathbb R)$, la cui matrice generica è

$$
A=\begin{bmatrix}a&b\\c&d\end{bmatrix}.
$$

#### **1.2. Matrici con $b=2c$**

Il vincolo è lineare e omogeneo. Ogni matrice ha la forma

$$
\begin{bmatrix}a&2c\\c&d\end{bmatrix}
=a\begin{bmatrix}1&0\\0&0\end{bmatrix}
+c\begin{bmatrix}0&2\\1&0\end{bmatrix}
+d\begin{bmatrix}0&0\\0&1\end{bmatrix}.
$$

L'insieme è un sottospazio di dimensione $3$ e le tre matrici mostrate ne formano una base.

#### **1.3. Matrici a determinante nullo**

L'insieme contiene lo zero ma non è chiuso rispetto alla somma. Per esempio,

$$
A=\begin{bmatrix}1&0\\0&0\end{bmatrix},\qquad
B=\begin{bmatrix}0&0\\0&1\end{bmatrix}
$$

hanno determinante nullo, mentre

$$
A+B=I_2
$$

ha determinante $1$. Non è un sottospazio.

#### **1.4. Matrici della forma $\begin{bmatrix}a&b\\0&a\end{bmatrix}$**

Si ha

$$
\begin{bmatrix}a&b\\0&a\end{bmatrix}
=a\begin{bmatrix}1&0\\0&1\end{bmatrix}
+b\begin{bmatrix}0&1\\0&0\end{bmatrix}.
$$

È un sottospazio di dimensione $2$ con base

$$
\left(
\begin{bmatrix}1&0\\0&1\end{bmatrix},
\begin{bmatrix}0&1\\0&0\end{bmatrix}
\right).
$$

#### **1.5. Matrici della forma $\begin{bmatrix}a&1\\0&d\end{bmatrix}$**

La matrice nulla non appartiene all'insieme, perché l'elemento in posizione $(1,2)$ è sempre $1$. Non è un sottospazio.

---

### **2. Parte II - Basi e componenti in $\mathbb R^3$**

Siano

$$
u=\begin{bmatrix}1\\2\\0\end{bmatrix},\qquad
v=\begin{bmatrix}2\\0\\1\end{bmatrix},\qquad
w=\begin{bmatrix}h+1\\h\\1\end{bmatrix}.
$$

I tre vettori sono una base se e solo se il determinante della matrice che li ha come colonne è non nullo:

$$
\det
\begin{bmatrix}
1&2&h+1\\
2&0&h\\
0&1&1
\end{bmatrix}
=h-2.
$$

Quindi

$$
\boxed{(u,v,w)\text{ è una base se e solo se }h\neq2.}
$$

Il più piccolo intero $h>2$ è $h=3$, per cui

$$
w=(4,3,1).
$$

Le **componenti** di un vettore $z$ nella base ordinata $(u,v,w)$ sono gli unici scalari $(\alpha,\beta,\gamma)$ tali che

$$
z=\alpha u+\beta v+\gamma w.
$$

Per

$$
z=(4,2,3)
$$

si risolve

$$
\begin{cases}
\alpha+2\beta+4\gamma=4,\\
2\alpha+3\gamma=2,\\
\beta+\gamma=3.
\end{cases}
$$

Da cui

$$
\gamma=-6,\qquad \beta=9,\qquad \alpha=10.
$$

Pertanto

$$
[z]_{(u,v,w)}=
\begin{bmatrix}10\\9\\-6\end{bmatrix},
$$

e il controllo diretto dà

$$
10(1,2,0)+9(2,0,1)-6(4,3,1)=(4,2,3).
$$

---

### **3. Parte III - Endomorfismo di polinomi**

Consideriamo $\mathbb R_2[x]$ con base canonica, nell'ordine delle dispense,

$$
E=(x^2,x,1).
$$

La mappa è prescritta da

$$
f(1+2x)=x^2,
$$

$$
f(2x-x^2)=x+4,
$$

$$
f(1-x)=x.
$$

#### **3.1. Perché la mappa esiste ed è unica**

Le coordinate dei tre polinomi nella base $E$ sono le colonne di

$$
Q=
\begin{bmatrix}
0&-1&0\\
2&2&-1\\
1&0&1
\end{bmatrix}.
$$

Poiché

$$
\det Q=3\neq0,
$$

i tre polinomi formano una base. L'assegnazione arbitraria delle loro immagini definisce dunque un unico endomorfismo lineare.

#### **3.2. Immagini della base canonica**

Risolvendo le relazioni per $1,x,x^2$ e usando la linearità si ottiene

$$
f(x)=\frac13x^2-\frac13x,
$$

$$
f(1)=\frac13x^2+\frac23x,
$$

$$
f(x^2)=\frac23x^2-\frac53x-4.
$$

Controlliamo, per esempio:

$$
f(1+2x)
=\left(\frac13x^2+\frac23x\right)
+2\left(\frac13x^2-\frac13x\right)
=x^2.
$$

#### **3.3. Matrice nella base canonica**

Le colonne sono le coordinate di $f(x^2),f(x),f(1)$:

$$
[f]_{E\leftarrow E}=
\begin{bmatrix}
\frac23&\frac13&\frac13\\
-\frac53&-\frac13&\frac23\\
-4&0&0
\end{bmatrix}.
$$

---

### **4. Cambiamenti di base nell'esercizio sui polinomi**

Sia

$$
C=(x-2,x^2+1,5x).
$$

La matrice che porta coordinate in $C$ a coordinate canoniche $E$ è

$$
P_C=
\begin{bmatrix}
0&1&0\\
1&0&5\\
-2&1&0
\end{bmatrix}.
$$

#### **4.1. Base $C$ nel dominio e canonica nel codominio**

Calcoliamo:

$$
f(x-2)=-\frac13x^2-\frac53x,
$$

$$
f(x^2+1)=x^2-x-4,
$$

$$
f(5x)=\frac53x^2-\frac53x.
$$

Quindi

$$
[f]_{E\leftarrow C}=
\begin{bmatrix}
-\frac13&1&\frac53\\
-\frac53&-1&-\frac53\\
0&-4&0
\end{bmatrix}.
$$

Questo coincide con $[f]_{E\leftarrow E}P_C$.

#### **4.2. Base $C$ sia nel dominio sia nel codominio**

Per scrivere $p=Ax^2+Bx+C_0$ nella base $C$, cerchiamo

$$
p=\alpha(x-2)+\beta(x^2+1)+\gamma(5x).
$$

Confrontando i coefficienti,

$$
\beta=A,\qquad
\alpha=\frac{A-C_0}{2},\qquad
\gamma=\frac{B-\alpha}{5}.
$$

Applicando queste formule alle tre immagini si ottiene

$$
[f]_{C\leftarrow C}=
\begin{bmatrix}
-\frac16&\frac52&\frac56\\
-\frac13&1&\frac53\\
-\frac3{10}&-\frac7{10}&-\frac12
\end{bmatrix}.
$$

Equivalentemente,

$$
[f]_{C\leftarrow C}
=P_C^{-1}[f]_{E\leftarrow E}P_C.
$$

---

### **5. Checklist conclusiva**

- Un vincolo per sottospazi deve essere stabile sotto combinazioni lineari.
- Per verificare una base, il determinante deve essere non nullo.
- Le componenti dipendono dall'ordine della base.
- Una mappa è determinata dai valori su una base.
- Nella matrice associata, le colonne sono immagini dei vettori della base del dominio, espresse nella base del codominio.

---

> ✅ L'esercitazione collega tutti i temi del modulo: sottospazi, basi, componenti, mappe lineari e cambiamenti di coordinate.
