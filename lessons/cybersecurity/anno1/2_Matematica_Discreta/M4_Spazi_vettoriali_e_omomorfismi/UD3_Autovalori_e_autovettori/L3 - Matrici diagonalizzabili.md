# **M4 UD3 Lezione 3 - Matrici diagonalizzabili**

### **1. Definizione**

Un endomorfismo $f:V\to V$ è **diagonalizzabile** se esiste una base $B$ di $V$ formata da autovettori. In tale base,

$$
[f]_B=\operatorname{diag}(\lambda_1,\ldots,\lambda_n).
$$

Una matrice $A$ è diagonalizzabile se è simile a una matrice diagonale:

$$
D=P^{-1}AP.
$$

Le colonne di $P$ sono gli autovettori della base; gli elementi diagonali di $D$ sono i corrispondenti autovalori nello stesso ordine.

---

### **2. Molteplicità algebrica e geometrica**

La **molteplicità algebrica** $m_a(\lambda)$ è la molteplicità di $\lambda$ come radice del polinomio caratteristico.

La **molteplicità geometrica** è

$$
m_g(\lambda)=\dim V_\lambda
=\dim\ker(A-\lambda I)
=n-\operatorname{rk}(A-\lambda I).
$$

Vale sempre

$$
1\leq m_g(\lambda)\leq m_a(\lambda).
$$

Un autovalore è detto **regolare** quando

$$
m_g(\lambda)=m_a(\lambda).
$$

---

### **3. Criteri di diagonalizzabilità**

Un endomorfismo di uno spazio $n$-dimensionale è diagonalizzabile sul campo $K$ se e solo se:

1. il polinomio caratteristico si spezza completamente in $K$, cioè la somma delle molteplicità algebriche degli autovalori in $K$ è $n$;
2. ogni autovalore è regolare.

Equivalentemente,

$$
\sum_\lambda\dim V_\lambda=n.
$$

Una condizione sufficiente molto utile è avere $n$ autovalori distinti: i relativi autovettori sono automaticamente indipendenti.

---

### **4. Il ruolo del campo**

La diagonalizzabilità dipende dal campo. Le dispense riportano la matrice

$$
A=\begin{bmatrix}2&2\\1&0\end{bmatrix}
$$

come esempio privo di autovalori reali. Il calcolo mostra però

$$
\det(A-\lambda I)=
\begin{vmatrix}2-\lambda&2\\1&-\lambda\end{vmatrix}
=\lambda^2-2\lambda-2,
$$

con radici reali

$$
\lambda=1\pm\sqrt3.
$$

Quindi questa matrice ha due autovalori reali distinti ed è diagonalizzabile su $\mathbb R$.

Un esempio corretto del fenomeno che la slide intende illustrare è la rotazione

$$
R=\begin{bmatrix}0&-1\\1&0\end{bmatrix},
$$

per cui

$$
p_R(\lambda)=\lambda^2+1.
$$

Essa non ha autovalori in $\mathbb R$ e non è diagonalizzabile su $\mathbb R$, mentre su $\mathbb C$ ha autovalori $i$ e $-i$ ed è diagonalizzabile.

> ⚠️ Prima di studiare le molteplicità geometriche bisogna verificare che il polinomio caratteristico si spezzi nel campo di base.

---

### **5. Procedura di diagonalizzazione**

1. Calcolare e fattorizzare $p_A(t)$.
2. Trovare $m_a(\lambda)$ per ogni radice.
3. Calcolare una base di $\ker(A-\lambda I)$ e $m_g(\lambda)$.
4. Verificare che la somma delle dimensioni degli autospazi sia $n$.
5. Affiancare gli autovettori come colonne di $P$.
6. Scrivere $D$ con gli autovalori nello stesso ordine e controllare $AP=PD$.

---

> ✅ Diagonalizzare significa trovare abbastanza autovettori indipendenti da costruire una base. Il confronto tra molteplicità algebrica e geometrica misura esattamente l'eventuale difetto.
