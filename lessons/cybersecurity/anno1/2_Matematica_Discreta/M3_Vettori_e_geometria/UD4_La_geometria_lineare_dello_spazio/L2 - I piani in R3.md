# **M3 UD4 Lezione 2 - I piani in $\mathbb R^3$**

### **1. Obiettivi e prerequisiti**

Introduciamo i piani nello spazio mediante due descrizioni equivalenti: un punto con due direzioni indipendenti, oppure un punto con un vettore normale. Sono prerequisiti combinazioni lineari, prodotto scalare, prodotto vettoriale e prodotto misto.

---

### **2. Caratterizzazione geometrica**

Nello spazio vale il seguente fatto geometrico:

> 📌 Tutte le rette passanti per un punto $P$ e perpendicolari a una retta $r$ appartengono a uno stesso piano $\pi$; inoltre ogni retta di $\pi$ passante per $P$ è perpendicolare a $r$.

La direzione di $r$ è quindi normale al piano.

La figura della pagina 1 mostra il punto $P$, la retta normale $r$ e più rette del piano passanti per $P$ e ortogonali a $r$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Equazioni parametriche di un piano**

Siano $P,A,B$ tre punti non allineati. I vettori

$$
\mathbf a=\overrightarrow{PA},
\qquad
\mathbf b=\overrightarrow{PB}
$$

non sono paralleli e generano le direzioni del piano.

Un punto $X$ appartiene al piano se e solo se

$$
\overrightarrow{PX}=h\mathbf a+k\mathbf b,
\qquad h,k\in\mathbb R
$$

Se

$$
P=(x_P,y_P,z_P),
\quad
\mathbf a=(a_1,a_2,a_3),
\quad
\mathbf b=(b_1,b_2,b_3)
$$

le equazioni parametriche sono

$$
\begin{cases}
x=x_P+ha_1+kb_1,\\
y=y_P+ha_2+kb_2,\\
z=z_P+ha_3+kb_3.
\end{cases}
$$

Servono due parametri perché il piano ha due direzioni indipendenti.

---

### **4. Equazione cartesiana mediante un normale**

Siano

$$
\mathbf n=
\begin{bmatrix}a\\b\\c\end{bmatrix}
\neq\mathbf0
$$

un vettore perpendicolare al piano e $P=(m,n,p)$ un punto del piano. Un punto $X=(x,y,z)$ appartiene al piano se e solo se

$$
\overrightarrow{PX}\cdot\mathbf n=0
$$

Quindi

$$
a(x-m)+b(y-n)+c(z-p)=0
$$

Sviluppando:

$$
\boxed{ax+by+cz+d=0}
$$

dove

$$
d=-am-bn-cp
$$

Il vettore $(a,b,c)$ è normale al piano; il PDF chiama le sue componenti **parametri direttori del piano**.

> ⚠️ Qui la lettera $n$ compare sia come coordinata del punto $P=(m,n,p)$ sia spesso come simbolo del vettore normale. Per evitare ambiguità, nel seguito useremo $\mathbf n$ per il vettore.

---

### **5. Esempio: piano per un punto con normale assegnata**

Troviamo il piano perpendicolare a

$$
\mathbf n=\begin{bmatrix}1\\3\\-1\end{bmatrix}
$$

e passante per $P=(2,4,7)$. Si scrive

$$
1(x-2)+3(y-4)-1(z-7)=0
$$

Sviluppando:

$$
x-2+3y-12-z+7=0
$$

e quindi

$$
\boxed{x+3y-z-7=0}
$$

Verifica sul punto:

$$
2+3\cdot4-7-7=0
$$

---

### **6. Piano individuato da due direzioni e un punto**

Se il piano è determinato da un punto $P$ e da due vettori non paralleli $\mathbf a$ e $\mathbf b$, allora $X$ appartiene al piano se e solo se i tre vettori

$$
\overrightarrow{PX},\mathbf a,\mathbf b
$$

sono complanari. Il prodotto misto deve quindi annullarsi:

$$
\overrightarrow{PX}\cdot(\mathbf b\times\mathbf a)=0
$$

La forma mnemonica della fonte è

$$
\det
\begin{bmatrix}
x-x_P&y-y_P&z-z_P\\
b_1&b_2&b_3\\
a_1&a_2&a_3
\end{bmatrix}=0.
$$

Sviluppando lungo la prima riga:

$$
\begin{aligned}
&(x-x_P)
\det\begin{bmatrix}b_2&b_3\\a_2&a_3\end{bmatrix}
-(y-y_P)
\det\begin{bmatrix}b_1&b_3\\a_1&a_3\end{bmatrix}\\
&\qquad +(z-z_P)
\det\begin{bmatrix}b_1&b_2\\a_1&a_2\end{bmatrix}=0.
\end{aligned}
$$

Scambiare $\mathbf a$ e $\mathbf b$ cambia il segno dell'intera equazione, ma non il piano descritto.

---

### **7. Esempio: piano per tre punti**

Siano

$$
P=(2,4,7),
\quad A=(0,2,3),
\quad B=(1,1,-1)
$$

Calcoliamo

$$
\overrightarrow{PA}=(-2,-2,-4)
$$

$$
\overrightarrow{PB}=(-1,-3,-8)
$$

Un normale è il prodotto vettoriale

$$
\begin{aligned}
\overrightarrow{PA}\times\overrightarrow{PB}
&=
\begin{bmatrix}
(-2)(-8)-(-4)(-3)\\
(-4)(-1)-(-2)(-8)\\
(-2)(-3)-(-2)(-1)
\end{bmatrix}\\
&=
\begin{bmatrix}4\\-12\\4\end{bmatrix}
=4\begin{bmatrix}1\\-3\\1\end{bmatrix}.
\end{aligned}
$$

Usiamo il normale semplificato $(1,-3,1)$ e il punto $P$:

$$
1(x-2)-3(y-4)+(z-7)=0
$$

Ne segue

$$
\boxed{x-3y+z+3=0}
$$

Verifica:

$$
2-12+7+3=0
$$

$$
0-6+3+3=0
$$

$$
1-3-1+3=0
$$

---

### **8. Condizioni di validità e casi degeneri**

- Il vettore normale non può essere nullo: $a,b,c$ non devono essere tutti zero.
- I due vettori direttori devono essere non paralleli; altrimenti generano soltanto una retta.
- I tre punti devono essere non allineati; se sono allineati, esistono infiniti piani che li contengono.

---

### **9. Errori comuni**

- Usare direttamente le coordinate di $A$ e $B$ invece dei vettori $A-P$ e $B-P$.
- Dimenticare il termine noto $d$ nell'equazione cartesiana.
- Confondere un vettore contenuto nel piano con il normale al piano.
- Credere che cambiare il segno di tutti i coefficienti cambi il piano.

---

> ✅ Un piano può essere descritto come $X=P+h\mathbf a+k\mathbf b$ con due direzioni indipendenti, oppure come insieme dei punti che soddisfano $\overrightarrow{PX}\cdot\mathbf n=0$. La forma cartesiana è $ax+by+cz+d=0$, con $(a,b,c)$ normale al piano.
