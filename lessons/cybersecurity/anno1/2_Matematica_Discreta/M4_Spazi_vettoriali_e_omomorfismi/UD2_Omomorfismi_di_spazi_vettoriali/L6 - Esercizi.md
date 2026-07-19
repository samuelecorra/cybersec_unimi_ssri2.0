# **M4 UD2 Lezione 6 - Esercizi sugli omomorfismi**

### **1. Ricostruire una mappa da valori assegnati**

In $\mathbb R^3$, con base canonica $(i,j,k)$, sia dato

$$
f(i-j)=2i+k,
$$

$$
f(j)=j+2k,
$$

$$
f(k+i)=2i-j-k.
$$

Poiché $i=(i-j)+j$,

$$
f(i)=2i+j+3k.
$$

Inoltre $k=(k+i)-i$, quindi

$$
f(k)=(2i-j-k)-(2i+j+3k)=-2j-4k.
$$

Le colonne $f(i),f(j),f(k)$ danno

$$
A=
\begin{bmatrix}
2&0&0\\
1&1&-2\\
3&2&-4
\end{bmatrix}.
$$

Per esempio,

$$
f(3i-4j+2k)=3f(i)-4f(j)+2f(k)=(6,-5,-7).
$$

Risolvendo $A(a,b,c)^T=0$ si ottiene $a=0$ e $b=2c$, perciò

$$
\ker f=\operatorname{span}\{(0,2,1)\}.
$$

Il rango è $2$ e una base dell'immagine è

$$
\bigl((2,1,3),(0,1,2)\bigr)=\bigl(f(i),f(j)\bigr).
$$

---

### **2. Esistenza, rango e parametro**

Siano

$$
v_1=(1,2,0),\quad v_2=(2,0,1),\quad v_3=(0,1,1)
$$

e siano assegnate le immagini

$$
w_1=(0,0,1),\quad w_2=(2h,3,1),\quad w_3=(h,0,1).
$$

La matrice con colonne $v_1,v_2,v_3$ ha determinante $-5\neq0$. Quindi i tre vettori formano una base e, per ogni $h\in\mathbb R$, esiste un'unica mappa lineare con $f(v_i)=w_i$.

La matrice delle immagini ha determinante

$$
\det\begin{bmatrix}0&2h&h\\0&3&0\\1&1&1\end{bmatrix}=-3h.
$$

Se $h\neq0$, le immagini sono una base:

$$
\ker f=\{0\},\qquad \operatorname{Im}f=\mathbb R^3,
$$

e $f$ è un isomorfismo.

Se $h=0$, $w_3=w_1$ e

$$
\operatorname{Im}f=\operatorname{span}\{(0,0,1),(0,3,1)\},
$$

mentre

$$
f(v_1-v_3)=0,
$$

perciò

$$
\ker f=\operatorname{span}\{v_1-v_3\}
=\operatorname{span}\{(1,1,-1)\}.
$$

---

### **3. Endomorfismo di $\mathbb R_2[x]$**

Sia $f$ definito da

$$
f(1-x)=2x^2,
$$

$$
f(x-x^2)=x+3,
$$

$$
f(1+x)=4.
$$

Sommando la prima e la terza relazione,

$$
2f(1)=2x^2+4,
$$

quindi

$$
f(1)=x^2+2.
$$

Segue

$$
f(x)=f(1+x)-f(1)=2-x^2
$$

e, dalla seconda relazione,

$$
f(x^2)=f(x)-(x+3)=-x^2-x-1.
$$

Nella base canonica ordinata $B=(x^2,x,1)$ la matrice è

$$
[f]_B=
\begin{bmatrix}
-1&-1&1\\
-1&0&0\\
-1&2&2
\end{bmatrix}.
$$

---

### **4. Costruire la forma normale di rango**

Sia

$$
f(a,b,c)=(a+b,b+c,c-a).
$$

Il nucleo si ricava dal sistema

$$
a+b=0,\qquad b+c=0,\qquad c-a=0,
$$

e vale

$$
\ker f=\operatorname{span}\{(1,-1,1)\}.
$$

Scegliamo nel dominio la base ordinata

$$
B=\bigl((1,0,0),(0,1,0),(1,-1,1)\bigr).
$$

Le prime due immagini sono

$$
w_1=(1,0,-1),\qquad w_2=(1,1,0),
$$

indipendenti. Completiamole con $(0,0,1)$ e poniamo

$$
C=\bigl((1,0,-1),(1,1,0),(0,0,1)\bigr).
$$

Poiché il terzo vettore di $B$ appartiene al nucleo,

$$
[f]_{C\leftarrow B}=
\begin{bmatrix}
1&0&0\\
0&1&0\\
0&0&0
\end{bmatrix}.
$$

> ⚠️ Nella pagina sorgente la base del dominio è elencata con il vettore del nucleo al primo posto, ma la matrice conclusiva lo colloca implicitamente al terzo posto. L'ordine qui adottato rende coerenti colonne e matrice diagonale.

---

> ✅ Gli esercizi mostrano quattro idee: usare la linearità per ricostruire immagini mancanti, controllare i dati su una base, tradurre polinomi in coordinate e scegliere basi adattate a nucleo e immagine.
