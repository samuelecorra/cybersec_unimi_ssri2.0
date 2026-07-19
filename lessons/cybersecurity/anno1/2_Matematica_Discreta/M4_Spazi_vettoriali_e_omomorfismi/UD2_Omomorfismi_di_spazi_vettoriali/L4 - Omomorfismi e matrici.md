# **M4 UD2 Lezione 4 - Omomorfismi e matrici**

### **1. Matrice associata a una mappa lineare**

Sia $f:V\to W$ lineare, con basi ordinate

$$
B=(v_1,\ldots,v_n),\qquad C=(w_1,\ldots,w_m).
$$

Per ogni $j$ scriviamo

$$
f(v_j)=a_{1j}w_1+\cdots+a_{mj}w_m.
$$

La **matrice associata** è

$$
[f]_{C\leftarrow B}=A=(a_{ij})\in M_{m\times n}(K).
$$

La colonna $j$ di $A$ è $[f(v_j)]_C$. Per ogni $v\in V$ vale

$$
[f(v)]_C=A[v]_B.
$$

> ⚠️ La matrice dipende sia dalla base del dominio sia da quella del codominio, oltre che dalla mappa.

---

### **2. Cambiamento di base**

Se $P_B$ ha come colonne i vettori della base $B$ espressi nella base canonica, allora

$$
[v]_{\mathrm{can}}=P_B[v]_B.
$$

Se $A$ è la matrice canonica di $f$ e $P_C$ è l'analoga matrice del codominio,

$$
[f]_{C\leftarrow B}=P_C^{-1}AP_B.
$$

In particolare, per un endomorfismo rappresentato nella stessa base al dominio e al codominio,

$$
[f]_B=P_B^{-1}AP_B.
$$

---

### **3. Esercizio completo**

Sia

$$
f:\mathbb R^3\to\mathbb R^3,
\qquad f(a,b,c)=(a-b,a+2c,c+b),
$$

e sia

$$
B=\bigl((1,1,0),(0,2,0),(1,0,-1)\bigr).
$$

#### **3.1. Basi canoniche**

Le immagini dei vettori canonici sono

$$
f(e_1)=(1,1,0),\quad
f(e_2)=(-1,0,1),\quad
f(e_3)=(0,2,1).
$$

Quindi

$$
A=
\begin{bmatrix}
1&-1&0\\
1&0&2\\
0&1&1
\end{bmatrix}.
$$

#### **3.2. Base $B$ nel dominio, canonica nel codominio**

Calcoliamo

$$
f(1,1,0)=(0,1,1),
$$

$$
f(0,2,0)=(-2,0,2),
$$

$$
f(1,0,-1)=(1,-1,-1).
$$

Pertanto

$$
[f]_{\mathrm{can}\leftarrow B}=
\begin{bmatrix}
0&-2&1\\
1&0&-1\\
1&2&-1
\end{bmatrix}.
$$

#### **3.3. Base $B$ sia nel dominio sia nel codominio**

La matrice di cambiamento è

$$
P_B=
\begin{bmatrix}
1&0&1\\
1&2&0\\
0&0&-1
\end{bmatrix}.
$$

Per un vettore $y=(y_1,y_2,y_3)$, le coordinate in $B$ sono

$$
[y]_B=
\begin{bmatrix}
y_1+y_3\\
\dfrac{y_2-y_1-y_3}{2}\\
-y_3
\end{bmatrix}.
$$

Applicando la formula alle tre immagini precedenti otteniamo

$$
[f]_{B\leftarrow B}=
\begin{bmatrix}
1&0&0\\
0&0&-\frac12\\
-1&-2&1
\end{bmatrix}.
$$

La stessa matrice si ricava da $P_B^{-1}AP_B$.

---

### **4. Controlli essenziali**

- Se $\dim V=n$ e $\dim W=m$, la matrice deve essere $m\times n$.
- Le colonne sono immagini dei vettori della base del **dominio**.
- Ogni colonna va espressa nella base del **codominio**.
- Cambiare soltanto la base del dominio moltiplica a destra per $P_B$; cambiare quella del codominio moltiplica a sinistra per $P_C^{-1}$.

---

> ✅ Le matrici sono la descrizione in coordinate delle applicazioni lineari. La formula $[f(v)]_C=[f]_{C\leftarrow B}[v]_B$ collega senza ambiguità vettori astratti e calcolo matriciale.
