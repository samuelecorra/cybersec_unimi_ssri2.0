# **M4 UD2 Lezione 8 - Rango di una matrice**

### **1. Definizioni equivalenti**

Per $A\in M_{m\times n}(K)$, il **rango** può essere definito in modi equivalenti:

$$
\operatorname{rk}A=dim\operatorname{span}\{\text{colonne di }A\},
$$

$$
\operatorname{rk}A=dim\operatorname{span}\{\text{righe di }A\},
$$

$$
\operatorname{rk}A=\max\{r:\text{esiste un minore }r\times r\text{ non nullo}\}.
$$

In particolare,

$$
0\leq\operatorname{rk}A\leq\min\{m,n\}.
$$

Il rango è anche la dimensione dell'immagine dell'applicazione lineare $x\mapsto Ax$.

---

### **2. Calcolo del rango**

Il metodo di Gauss conserva il rango e lo rende uguale al numero di pivot della forma a scala.

Il criterio dei minori procede invece così:

1. trovare un minore non nullo di ordine $r$;
2. tentare di orlarlo con una riga e una colonna per ottenere minori di ordine $r+1$;
3. se tutti gli orlati sono nulli, il rango è $r$.

Questo è il metodo dei **minori orlati di Kronecker**.

---

### **3. Primo esercizio**

Sia

$$
A=
\begin{bmatrix}
2&1&3&1\\
4&2&6&3\\
10&0&5&10
\end{bmatrix}.
$$

Un minore $3\times3$ ottenuto dalle colonne $1,2,4$ è

$$
\det
\begin{bmatrix}
2&1&1\\
4&2&3\\
10&0&10
\end{bmatrix}
=-10\neq0.
$$

Poiché la matrice ha soltanto tre righe,

$$
\boxed{\operatorname{rk}A=3}.
$$

---

### **4. Secondo esercizio con parametro**

Consideriamo

$$
C=
\begin{bmatrix}
h&0&1\\
2&h&3\\
2h+1&1&4h\\
1&h&2
\end{bmatrix}.
$$

Il minore formato dalle ultime due colonne delle righe $2$ e $3$ contiene, equivalentemente, un minore costante non nullo; per esempio nelle righe $2$ e $4$ e nelle colonne $1$ e $3$:

$$
\det\begin{bmatrix}2&3\\1&2\end{bmatrix}=1.
$$

Dunque $\operatorname{rk}C\geq2$ per ogni $h$.

Il minore $3\times3$ delle righe $1,2,4$ vale

$$
\det
\begin{bmatrix}
h&0&1\\
2&h&3\\
1&h&2
\end{bmatrix}=h(1-h).
$$

Quindi il rango è $3$ se $h\neq0,1$. Per $h=0$, il minore delle righe $1,3,4$ vale $-1$, e il rango resta $3$. Per $h=1$, tutti i minori di ordine $3$ sono nulli, mentre il minore di ordine $2$ appena esibito è $1$.

Pertanto

$$
\operatorname{rk}C=
\begin{cases}
2,&h=1,\\
3,&h\neq1.
\end{cases}
$$

---

### **5. Errori comuni**

- Concludere $\operatorname{rk}A=r$ dopo aver trovato un solo minore $r\times r$ non nullo, senza escludere ordini maggiori.
- Dimenticare che un minore è il determinante di una sottomatrice quadrata.
- Dividere per un'espressione contenente il parametro senza separare i valori che la annullano.
- Confondere rango della matrice con numero di righe o colonne.

---

> ✅ Il rango è la massima quantità di informazione lineare indipendente contenuta nelle righe o nelle colonne. Gauss e minori forniscono due metodi equivalenti per calcolarlo.
