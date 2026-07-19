# **M4 UD2 Lezione 9 - Sistemi e matrici**

### **1. Un sistema come controimmagine**

Un sistema lineare

$$
Ax=b
$$

è associato alla mappa lineare

$$
f_A:K^n\to K^m,\qquad f_A(x)=Ax.
$$

L'insieme delle soluzioni è

$$
f_A^{-1}(b).
$$

Il sistema omogeneo $Ax=0$ ha come insieme delle soluzioni

$$
\ker f_A.
$$

Se $x_0$ è una soluzione particolare di $Ax=b$, allora tutte e sole le soluzioni sono

$$
x=x_0+z,\qquad z\in\ker A.
$$

Infatti $A(x_0+z)=b$; viceversa, da $Ax=Ax_0$ segue $A(x-x_0)=0$. L'insieme delle soluzioni non omogenee è dunque uno spazio affine parallelo al nucleo.

---

### **2. Teorema di Rouché-Capelli**

> 📌 Il sistema $Ax=b$ è compatibile se e solo se
> $$
> \operatorname{rk}A=\operatorname{rk}[A\mid b].
> $$

Se il rango comune è $r$ e $A$ ha $n$ colonne, la soluzione dipende da

$$
n-r
$$

parametri liberi. Su un campo infinito, se $n-r>0$, esistono infinite soluzioni. Su un campo finito con $q$ elementi, esistono esattamente

$$
q^{n-r}
$$

soluzioni.

Per una matrice quadrata $A\in M_n(K)$ con $\det A\neq0$, il sistema ha un'unica soluzione

$$
x=A^{-1}b.
$$

La regola di Cramer fornisce

$$
x_i=\frac{\det A_i}{\det A},
$$

dove $A_i$ si ottiene sostituendo la colonna $i$ di $A$ con $b$.

---

### **3. Esercizio reale con parametro**

Consideriamo

$$
\begin{bmatrix}
1&1&h\\
1&h&1\\
h&1&1
\end{bmatrix}
\begin{bmatrix}x\\y\\z\end{bmatrix}
=\begin{bmatrix}1\\1\\1\end{bmatrix}.
$$

Il determinante è

$$
\det A=(h-1)^2(h+2).
$$

#### **3.1. Caso $h\neq1,-2$**

$A$ è invertibile e il sistema ha un'unica soluzione. Per simmetria $x=y=z$, e sostituendo:

$$
(h+2)x=1,
$$

quindi

$$
x=y=z=\frac1{h+2}.
$$

#### **3.2. Caso $h=1$**

Tutte le equazioni coincidono:

$$
x+y+z=1.
$$

Si ha

$$
\operatorname{rk}A=\operatorname{rk}[A\mid b]=1,
$$

e le soluzioni dipendono da due parametri:

$$
(x,y,z)=(1-s-t,s,t).
$$

#### **3.3. Caso $h=-2$**

La somma delle tre righe della matrice dei coefficienti è nulla, mentre la somma dei termini noti è $3$. Pertanto

$$
\operatorname{rk}A=2<3=\operatorname{rk}[A\mid b],
$$

e il sistema è impossibile.

---

### **4. Sistema omogeneo su $\mathbb Z_7$**

Sia

$$
A=
\begin{bmatrix}
1&2&1\\
h&1&0\\
1&2h&h+1
\end{bmatrix}
$$

e studiamo $Ax=0$ in $\mathbb Z_7^3$. Il determinante è

$$
\det A=-h\pmod7.
$$

Se $h\neq0$ in $\mathbb Z_7$, $A$ è invertibile e l'unica soluzione è $(0,0,0)$.

Se $h=0$,

$$
A=
\begin{bmatrix}
1&2&1\\
0&1&0\\
1&0&1
\end{bmatrix}
$$

ha rango $2$. Dalla seconda equazione $y=0$ e dalla prima $x+z=0$, dunque

$$
(x,y,z)=t(1,0,-1),\qquad t\in\mathbb Z_7.
$$

Le soluzioni sono esattamente $7$, non infinite.

> ⚠️ Il numero di parametri non va confuso con il numero di soluzioni: su un campo finito ogni parametro ha soltanto un numero finito di valori.

---

> ✅ Un sistema lineare è lo studio delle fibre della mappa $x\mapsto Ax$. Rango, nucleo e campo di base determinano compatibilità, dimensione e numero delle soluzioni.
