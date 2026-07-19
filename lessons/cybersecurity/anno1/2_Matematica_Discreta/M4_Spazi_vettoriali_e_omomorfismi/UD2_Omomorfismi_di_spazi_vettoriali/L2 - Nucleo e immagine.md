# **M4 UD2 Lezione 2 - Nucleo e immagine**

### **1. Definizioni**

Sia $f:V\to W$ lineare.

Il **nucleo** è

$$
\ker f=\{v\in V:f(v)=0_W\}=f^{-1}(\{0_W\}).
$$

L'**immagine** è

$$
\operatorname{Im}f=\{f(v):v\in V\}\subseteq W.
$$

Il nucleo misura quanti vettori vengono annullati; l'immagine misura la parte del codominio effettivamente raggiunta.

---

### **2. Nucleo e immagine sono sottospazi**

Se $u,v\in\ker f$ e $\lambda,\mu\in K$, allora

$$
f(\lambda u+\mu v)=\lambda f(u)+\mu f(v)=0,
$$

quindi $\ker f\leq V$.

Se $y_1=f(u)$ e $y_2=f(v)$ appartengono all'immagine, allora

$$
\lambda y_1+\mu y_2
=f(\lambda u+\mu v)\in\operatorname{Im}f,
$$

quindi $\operatorname{Im}f\leq W$.

Se $B=(v_1,\ldots,v_n)$ è una base di $V$, allora

$$
\operatorname{Im}f=\operatorname{span}\{f(v_1),\ldots,f(v_n)\}.
$$

Le immagini dei vettori di base generano l'immagine, ma non sono necessariamente indipendenti.

---

### **3. Teorema della dimensione**

> 📌 **Teorema rango-nullità.** Se $V$ ha dimensione finita, allora
> $$
> \dim V=\dim\ker f+\dim\operatorname{Im}f.
> $$

#### **3.1. Dimostrazione**

Sia $(u_1,\ldots,u_r)$ una base di $\ker f$. Completiamola a una base di $V$:

$$
B=(u_1,\ldots,u_r,v_{r+1},\ldots,v_n).
$$

Poiché $f(u_i)=0$, l'immagine è generata da

$$
f(v_{r+1}),\ldots,f(v_n).
$$

Questi vettori sono indipendenti. Se

$$
\sum_{j=r+1}^n\lambda_jf(v_j)=0,
$$

allora

$$
f\left(\sum_{j=r+1}^n\lambda_jv_j\right)=0,
$$

perciò la somma appartiene al nucleo ed è anche combinazione dei $v_j$. Scrivendola nella base $B$, l'unicità delle componenti forza tutti i $\lambda_j$ a zero. Dunque le immagini formano una base di $\operatorname{Im}f$ e

$$
\dim\operatorname{Im}f=n-r.
$$

#### **3.2. Confronto con gli omomorfismi di gruppi**

Per gruppi finiti, il risultato analogo è

$$
|G|=|\ker f|\,|f(G)|.
$$

Le due formule non sono identiche: una somma di dimensioni sostituisce il prodotto degli ordini. Hanno però la stessa origine strutturale, perché ogni fibra non vuota è un laterale del nucleo. Se $f(v)=w$, allora

$$
f^{-1}(w)=v+\ker f.
$$

---

### **4. Esercizio con parametro**

Sia

$$
f(x,y,z)=(hx+y+h^2-4,\ x+hy+3z,\ x+y+z).
$$

Affinché $f$ sia lineare è necessario $f(0,0,0)=0$, dunque

$$
h^2-4=0\quad\Longleftrightarrow\quad h=\pm2.
$$

Per tali valori il termine costante scompare e tutte le coordinate sono forme lineari.

#### **4.1. Caso $h=2$**

La matrice è

$$
A_2=\begin{bmatrix}2&1&0\\1&2&3\\1&1&1\end{bmatrix}.
$$

La soluzione di $A_2(x,y,z)^T=0$ è

$$
y=-2x,\qquad z=x,
$$

quindi

$$
\ker f=\operatorname{span}\{(1,-2,1)\},\qquad \dim\ker f=1.
$$

Per rango-nullità $\dim\operatorname{Im}f=2$. Una base dell'immagine è

$$
\bigl((2,1,1),(1,2,1)\bigr)=\bigl(f(e_1),f(e_2)\bigr).
$$

Infatti $f(e_3)=(0,3,1)$ è combinazione dei primi due.

#### **4.2. Caso $h=-2$**

$$
A_{-2}=\begin{bmatrix}-2&1&0\\1&-2&3\\1&1&1\end{bmatrix}
$$

è invertibile. Pertanto

$$
\ker f=\{0\},\qquad \operatorname{Im}f=\mathbb R^3.
$$

---

### **5. Esercizio a valori polinomiali**

Sia

$$
f(a,b,c)=(a-2b-c)x^2+(a+c)x+2b+2c.
$$

Il nucleo è dato dal sistema

$$
\begin{cases}
a-2b-c=0,\\
a+c=0,\\
2b+2c=0.
\end{cases}
$$

Si ottiene $(a,b,c)=a(1,1,-1)$, quindi

$$
\ker f=\operatorname{span}\{(1,1,-1)\}.
$$

La dimensione dell'immagine è $3-1=2$. Una base è

$$
f(e_1)=x^2+x,\qquad f(e_2)=-2x^2+2.
$$

---

> ✅ Il nucleo e l'immagine sono i due sottospazi fondamentali associati a una mappa lineare. In dimensione finita, le loro dimensioni si ripartiscono esattamente la dimensione del dominio.
