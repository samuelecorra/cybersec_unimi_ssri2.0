# **M4 UD3 Lezione 2 - Ricerca di autovalori e autovettori**

### **1. Equazione caratteristica**

Sia $A$ la matrice di un endomorfismo $f:V\to V$ in una base. Lo scalare $\lambda$ è un autovalore se esiste $x\neq0$ tale che

$$
Ax=\lambda x,
$$

ossia

$$
(A-\lambda I)x=0.
$$

Il sistema omogeneo possiede soluzioni non nulle se e solo se $A-\lambda I$ è singolare:

$$
\det(A-\lambda I)=0.
$$

Il polinomio

$$
p_A(t)=\det(A-tI)
$$

è il **polinomio caratteristico** nella convenzione adottata nelle dispense. Le sue radici nel campo $K$ sono gli autovalori.

Per ogni radice $\lambda$, gli autovettori si trovano risolvendo

$$
(A-\lambda I)x=0;
$$

il relativo autospazio è proprio questo nucleo.

---

### **2. Traccia e determinante**

Contando gli autovalori con molteplicità algebrica in un campo in cui il polinomio caratteristico si spezza,

$$
\lambda_1+\cdots+\lambda_n=\operatorname{tr}A,
$$

$$
\lambda_1\cdots\lambda_n=\det A.
$$

Con la convenzione alternativa $\det(tI-A)$ cambiano alcuni segni dei coefficienti, ma non le radici.

---

### **3. Esempio completo**

Sia

$$
A=
\begin{bmatrix}
2&0&0\\
0&3&1\\
1&0&4
\end{bmatrix}.
$$

Poiché è quasi triangolare,

$$
\det(A-\lambda I)
=(2-\lambda)(3-\lambda)(4-\lambda).
$$

Gli autovalori sono $2,3,4$.

#### **3.1. Autovalore $2$**

$$
A-2I=
\begin{bmatrix}
0&0&0\\
0&1&1\\
1&0&2
\end{bmatrix}.
$$

Il sistema dà $b+c=0$ e $a+2c=0$. Ponendo $c=1$:

$$
V_2=\operatorname{span}\{(-2,-1,1)\}.
$$

#### **3.2. Autovalore $3$**

Da $(A-3I)x=0$ segue $a=0$ e $c=0$, mentre $b$ è libero:

$$
V_3=\operatorname{span}\{(0,1,0)\}.
$$

#### **3.3. Autovalore $4$**

Si ottiene $a=0$ e $c=b$:

$$
V_4=\operatorname{span}\{(0,1,1)\}.
$$

I tre autovettori sono indipendenti, in accordo con il teorema sugli autovalori distinti.

---

### **4. Matrici simili**

Due matrici quadrate $A$ e $B$ rappresentano lo stesso endomorfismo in basi diverse se sono **simili**:

$$
B=H^{-1}AH
$$

per una matrice invertibile $H$. Equivalentemente, ponendo $M=H^{-1}$,

$$
A=M^{-1}BM.
$$

Le matrici simili hanno lo stesso polinomio caratteristico:

$$
\det(B-tI)
=\det(H^{-1}(A-tI)H)
=\det(A-tI).
$$

Quindi hanno gli stessi autovalori, traccia e determinante.

Il converso non vale. Per esempio,

$$
J=\begin{bmatrix}1&1\\0&1\end{bmatrix},
\qquad
I=\begin{bmatrix}1&0\\0&1\end{bmatrix}
$$

hanno entrambe polinomio caratteristico $(1-t)^2$, ma non sono simili. Infatti $I$ è simile soltanto a se stessa, poiché $H^{-1}IH=I$ per ogni $H$ invertibile; equivalentemente, l'autospazio di $I$ per $1$ ha dimensione $2$, mentre quello di $J$ ha dimensione $1$.

---

### **5. Procedura risolutiva**

1. Costruire $A-tI$.
2. Calcolarne il determinante e fattorizzarlo nel campo considerato.
3. Per ogni radice $\lambda$, ridurre $A-\lambda I$.
4. Descrivere il nucleo con una base.
5. Escludere sempre il vettore nullo dall'insieme degli autovettori.

---

> ✅ Gli autovalori sono le radici del polinomio caratteristico; gli autovettori relativi a $\lambda$ sono i vettori non nulli del nucleo di $A-\lambda I$.
