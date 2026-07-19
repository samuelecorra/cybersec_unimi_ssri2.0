# **M4 UD2 Lezione 5 - Proprietà degli omomorfismi**

### **1. Composizione**

Siano

$$
f:U\to V,
\qquad g:V\to W
$$

applicazioni lineari. La composizione $g\circ f:U\to W$ è lineare, perché

$$
(g\circ f)(\lambda u+\mu v)
=g(\lambda f(u)+\mu f(v))
=\lambda(g\circ f)(u)+\mu(g\circ f)(v).
$$

Scelte basi compatibili, se

$$
[f]=A,qquad[g]=B,
$$

allora

$$
[g\circ f]=BA.
$$

L'ordine è essenziale: si applica prima $A$ e poi $B$.

---

### **2. Applicazione inversa**

Se $f:V\to W$ è un isomorfismo, l'inversa

$$
f^{-1}:W\to V
$$

è lineare. Infatti, posti $u=f(x)$ e $v=f(y)$,

$$
f^{-1}(\lambda u+\mu v)
=f^{-1}(f(\lambda x+\mu y))
=\lambda x+\mu y.
$$

In basi compatibili,

$$
[f^{-1}]=[f]^{-1}.
$$

Quindi una matrice quadrata rappresenta un isomorfismo se e solo se è invertibile.

---

### **3. Forma normale rispetto a due basi**

> 📌 Sia $f:V\to W$ lineare di rango $r$, con $\dim V=n$ e $\dim W=m$. Esistono una base $B$ di $V$ e una base $C$ di $W$ tali che
> $$
> [f]_{C\leftarrow B}=
> \begin{bmatrix}
> I_r&0\\
> 0&0
> \end{bmatrix}.
> $$

#### **3.1. Dimostrazione costruttiva**

Sia $(k_{r+1},\ldots,k_n)$ una base di $\ker f$. Per rango-nullità ha $n-r$ elementi. Completiamola scegliendo $v_1,\ldots,v_r$ in modo che

$$
B=(v_1,\ldots,v_r,k_{r+1},\ldots,k_n)
$$

sia una base di $V$.

I vettori

$$
w_i=f(v_i),\qquad i=1,\ldots,r,
$$

formano una base di $\operatorname{Im}f$. Completiamoli a una base

$$
C=(w_1,\ldots,w_r,w_{r+1},\ldots,w_m)
$$

di $W$. Allora $f(v_i)=w_i$ per $i\leq r$ e $f(k_j)=0$ per $j>r$, che produce la matrice dichiarata.

> ⚠️ Questa forma usa in generale due basi diverse, una nel dominio e una nel codominio. Non è la diagonalizzazione di un endomorfismo rispetto alla stessa base.

---

### **4. Conseguenze**

- Il rango è invariato dai cambiamenti di base.
- Due matrici dello stesso formato rappresentano, rispetto a opportune basi, mappe dello stesso tipo lineare se hanno lo stesso rango.
- Per $r=n=m$ la forma normale è $I_n$ e la mappa è un isomorfismo.
- Per $r=0$ la mappa è nulla.

---

> ✅ Composizione e inversione corrispondono rispettivamente a prodotto e inversione di matrici. Con basi distinte, ogni mappa lineare è riducibile a una forma che mostra soltanto il suo rango.
