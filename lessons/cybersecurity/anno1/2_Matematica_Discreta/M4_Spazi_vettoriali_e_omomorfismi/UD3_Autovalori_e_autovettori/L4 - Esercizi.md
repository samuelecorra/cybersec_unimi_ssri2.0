# **M4 UD3 Lezione 4 - Esercizi su autovalori e diagonalizzazione**

### **1. Endomorfismo non diagonalizzabile**

Sia

$$
f(a,b,c)=(2a+b,c-a,b+2c).
$$

Nella base canonica,

$$
A=
\begin{bmatrix}
2&1&0\\
-1&0&1\\
0&1&2
\end{bmatrix}.
$$

Il polinomio caratteristico è

$$
\det(A-\lambda I)=-\lambda(2-\lambda)^2.
$$

Gli autovalori sono $0$, di molteplicità algebrica $1$, e $2$, di molteplicità algebrica $2$.

Per $\lambda=0$:

$$
V_0=\ker A=\operatorname{span}\{(1,-2,1)\}.
$$

Per $\lambda=2$:

$$
A-2I=
\begin{bmatrix}
0&1&0\\
-1&-2&1\\
0&1&0
\end{bmatrix},
$$

da cui $y=0$ e $z=x$:

$$
V_2=\operatorname{span}\{(1,0,1)\}.
$$

Poiché

$$
m_g(2)=1<2=m_a(2),
$$

$f$ non è diagonalizzabile.

---

### **2. Matrice triangolare con parametro**

Consideriamo

$$
A_h=
\begin{bmatrix}
2h&1&2\\
0&h&0\\
0&0&3
\end{bmatrix}.
$$

Essendo triangolare, gli autovalori sono $2h,h,3$. Sono distinti salvo nei casi

$$
h=0,\qquad h=\frac32,\qquad h=3.
$$

Se sono distinti, la matrice è diagonalizzabile.

#### **2.1. Caso $h=0$**

L'autovalore $0$ ha molteplicità algebrica $2$. Il sistema $A_0x=0$ impone $z=0$ e $y=0$, quindi

$$
m_g(0)=1<2.
$$

La matrice non è diagonalizzabile.

#### **2.2. Caso $h=\frac32$**

L'autovalore $3$ ha molteplicità algebrica $2$. La matrice $A_{3/2}-3I$ impone $y=0$ e $z=0$, dunque

$$
m_g(3)=1<2.
$$

La matrice non è diagonalizzabile.

#### **2.3. Caso $h=3$**

L'autovalore $3$ ha molteplicità algebrica $2$, ma

$$
A_3-3I=
\begin{bmatrix}
3&1&2\\
0&0&0\\
0&0&0
\end{bmatrix}
$$

ha rango $1$ e nucleo di dimensione $2$. L'autovalore è regolare; insieme all'autospazio dell'autovalore semplice $6$ si ottengono tre autovettori indipendenti.

In conclusione,

$$
\boxed{A_h\text{ è diagonalizzabile se e solo se }h\notin\left\{0,\frac32\right\}.}
$$

---

### **3. Similitudine di due matrici**

Siano

$$
A=
\begin{bmatrix}
1&0&7\\
0&1&4\\
0&0&2
\end{bmatrix},
$$

$$
B_k=
\begin{bmatrix}
2&0&0\\
k-2&1&0\\
5&k&1
\end{bmatrix}.
$$

Entrambe hanno autovalori $1$, di molteplicità algebrica $2$, e $2$, di molteplicità algebrica $1$.

Per $A$,

$$
A-I=
\begin{bmatrix}
0&0&7\\
0&0&4\\
0&0&1
\end{bmatrix}
$$

ha rango $1$, quindi $m_g^A(1)=2$: $A$ è diagonalizzabile.

Per $B_k$,

$$
B_k-I=
\begin{bmatrix}
1&0&0\\
k-2&0&0\\
5&k&0
\end{bmatrix}.
$$

Se $k\neq0$, il rango è $2$ e $m_g^{B_k}(1)=1$, perciò $B_k$ non è diagonalizzabile e non può essere simile ad $A$.

Se $k=0$, il rango è $1$ e $m_g^{B_0}(1)=2$. Entrambe le matrici sono allora diagonalizzabili e simili alla stessa diagonale

$$
\operatorname{diag}(1,1,2).
$$

Pertanto

$$
\boxed{A\text{ e }B_k\text{ sono simili se e solo se }k=0.}
$$

---

> ✅ Gli autovalori da soli non determinano la diagonalizzabilità né la similitudine: bisogna controllare le dimensioni degli autospazi, soprattutto per gli autovalori multipli.
