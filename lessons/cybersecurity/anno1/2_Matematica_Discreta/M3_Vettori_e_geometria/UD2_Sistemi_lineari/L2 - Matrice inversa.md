# **M3 UD2 Lezione 2 - Matrice inversa**

### **1. Obiettivi e prerequisiti**

Applichiamo Gauss-Jordan al calcolo dell'inversa di una matrice quadrata. Sono prerequisiti prodotto tra matrici, matrice identità, sistemi lineari e operazioni elementari di riga.

---

### **2. Matrice inversa**

Sia $A\in M_n(K)$ una matrice quadrata di ordine $n$ su un campo $K$. Una matrice $A^{-1}$ è detta **inversa** di $A$ se

$$
AA^{-1}=A^{-1}A=I_n
$$

Se esiste, l'inversa è unica. Infatti, se $B$ e $C$ fossero entrambe inverse,

$$
B=B I_n=B(AC)=(BA)C=I_nC=C
$$

> 📌 Una matrice che possiede inversa è detta **invertibile** o **non singolare**.

---

### **3. L'equazione matriciale $AX=I_n$**

Cerchiamo una matrice incognita

$$
X=
\begin{bmatrix}
x_{11}&x_{12}&\cdots&x_{1n}\\
x_{21}&x_{22}&\cdots&x_{2n}\\
\vdots&\vdots&\ddots&\vdots\\
x_{n1}&x_{n2}&\cdots&x_{nn}
\end{bmatrix}
$$

tale che

$$
AX=I_n
$$

Il prodotto della matrice $A$ per la prima colonna di $X$ deve essere la prima colonna di $I_n$; lo stesso vale per ogni altra colonna. Il problema equivale quindi a risolvere simultaneamente gli $n$ sistemi

$$
A\mathbf x_1=\mathbf e_1,
\quad
A\mathbf x_2=\mathbf e_2,
\quad\ldots\quad,
A\mathbf x_n=\mathbf e_n
$$

dove $\mathbf e_j$ sono le colonne della base canonica.

Poiché tutti i sistemi hanno la stessa matrice dei coefficienti, le medesime operazioni di riga possono essere applicate a tutti i termini noti insieme.

---

### **4. Algoritmo di Gauss-Jordan per l'inversa**

Si costruisce la matrice aumentata

$$
[A\mid I_n]
$$

Si applicano operazioni elementari di riga fino a ridurre il blocco sinistro all'identità:

$$
[A\mid I_n]
\longrightarrow
[I_n\mid B]
$$

Le stesse operazioni che trasformano $A$ in $I_n$ trasformano $I_n$ in $B$, e la matrice ottenuta è

$$
B=A^{-1}
$$

La ragione può essere espressa mediante matrici elementari. Se

$$
E_r\cdots E_2E_1A=I_n
$$

allora

$$
E_r\cdots E_2E_1=A^{-1}
$$

applicare le stesse operazioni al blocco $I_n$ produce proprio quel prodotto.

---

### **5. Quando il procedimento fallisce**

Se non è possibile ottenere un pivot in ogni colonna del blocco sinistro, $A$ non è invertibile. In particolare, può comparire una riga nulla nel blocco di $A$ che non può essere rimossa mediante scambi e combinazioni di righe.

Il metodo non decide anticipatamente se l'inversa esista: la verifica avviene durante il calcolo.

> ⚠️ Un elemento diagonale nullo non prova da solo la non invertibilità: prima bisogna verificare se uno scambio di righe può fornire un pivot non nullo.

---

### **6. Esempio su $\mathbb R$**

Il PDF propone

$$
A=
\begin{bmatrix}
1&0&3\\
4&2&-1\\
-2&0&2
\end{bmatrix}
$$

Costruiamo

$$
\left[
\begin{array}{ccc|ccc}
1&0&3&1&0&0\\
4&2&-1&0&1&0\\
-2&0&2&0&0&1
\end{array}
\right].
$$

Applichiamo le operazioni annotate nella fonte:

$$
R_2\leftarrow R_2-4R_1,
\qquad
R_3\leftarrow R_3+2R_1
$$

$$
\left[
\begin{array}{ccc|ccc}
1&0&3&1&0&0\\
0&2&-13&-4&1&0\\
0&0&8&2&0&1
\end{array}
\right].
$$

Eliminiamo la terza componente dalle prime due righe senza introdurre subito frazioni:

$$
R_1\leftarrow 8R_1-3R_3,
\qquad
R_2\leftarrow 8R_2+13R_3
$$

$$
\left[
\begin{array}{ccc|ccc}
8&0&0&2&0&-3\\
0&16&0&-6&8&13\\
0&0&8&2&0&1
\end{array}
\right].
$$

Normalizzando le righe si ottiene

$$
\left[
\begin{array}{ccc|ccc}
1&0&0&\frac14&0&-\frac38\\
0&1&0&-\frac38&\frac12&\frac{13}{16}\\
0&0&1&\frac14&0&\frac18
\end{array}
\right].
$$

Pertanto

$$
\boxed{
A^{-1}=
\begin{bmatrix}
\frac14&0&-\frac38\\
-\frac38&\frac12&\frac{13}{16}\\
\frac14&0&\frac18
\end{bmatrix}}
$$

#### **6.1. Verifica**

Moltiplicando $A$ per la prima colonna dell'inversa:

$$
\begin{bmatrix}
1&0&3\\4&2&-1\\-2&0&2
\end{bmatrix}
\begin{bmatrix}
\frac14\\-\frac38\\\frac14
\end{bmatrix}
=
\begin{bmatrix}1\\0\\0\end{bmatrix}.
$$

Le altre due colonne producono rispettivamente $\mathbf e_2$ e $\mathbf e_3$; quindi $AA^{-1}=I_3$.

---

### **7. Lo stesso esempio in $\mathbb Z_5$**

Riduciamo i coefficienti modulo $5$:

$$
A\equiv
\begin{bmatrix}
1&0&3\\
4&2&4\\
3&0&2
\end{bmatrix}
\pmod5
$$

Si può ripetere Gauss-Jordan interamente in $\mathbb Z_5$. Equivalentemente, si riducono modulo $5$ le frazioni dell'inversa reale interpretando ogni denominatore mediante il suo inverso:

$$
4^{-1}\equiv4,
\qquad
2^{-1}\equiv3,
\qquad
8^{-1}\equiv3^{-1}\equiv2
\pmod5
$$

Quindi

$$
\frac14\equiv4,
\quad
-\frac38\equiv-3\cdot2\equiv4,
\quad
\frac12\equiv3,
\quad
\frac{13}{16}\equiv3,
\quad
\frac18\equiv2
\pmod5
$$

Si ottiene

$$
\boxed{
A^{-1}\equiv
\begin{bmatrix}
4&0&4\\
4&3&3\\
4&0&2
\end{bmatrix}
\pmod5}
$$

La verifica diretta dà

$$
\begin{bmatrix}
1&0&3\\4&2&4\\3&0&2
\end{bmatrix}
\begin{bmatrix}
4&0&4\\4&3&3\\4&0&2
\end{bmatrix}
\equiv I_3\pmod5.
$$

> 💡 La stessa matrice con coefficienti interi può essere invertibile in un campo e non invertibile in un altro. L'invertibilità dipende dal fatto che il determinante sia non nullo nel campo considerato.

---

### **8. Errori comuni**

- Applicare un'operazione soltanto al blocco sinistro: deve agire sull'intera matrice $[A\mid I_n]$.
- Concludere che $A$ è non invertibile appena compare uno zero sulla diagonale, senza tentare uno scambio.
- Trattare una divisione in $\mathbb Z_p$ come divisione ordinaria invece che come moltiplicazione per l'inverso modulare.
- Verificare solo alcune entrate: la verifica completa è $AA^{-1}=I_n$.

---

> ✅ Il metodo di Gauss-Jordan calcola l'inversa riducendo $[A\mid I_n]$ a $[I_n\mid A^{-1}]$. L'esempio della fonte produce un'inversa razionale su $\mathbb R$ e, lavorando modulo $5$, la matrice $\begin{bmatrix}4&0&4\\4&3&3\\4&0&2\end{bmatrix}$.
