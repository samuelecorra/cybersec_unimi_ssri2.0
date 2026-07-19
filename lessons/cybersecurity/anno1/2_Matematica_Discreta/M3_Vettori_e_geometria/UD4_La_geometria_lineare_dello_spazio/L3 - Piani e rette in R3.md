# **M3 UD4 Lezione 3 - Piani e rette in $\mathbb R^3$**

### **1. Obiettivi e prerequisiti**

Studiamo le mutue posizioni di piani e rette, l'intersezione tra due piani, i fasci di piani e gli angoli nello spazio. Sono prerequisiti vettori direzione, normali, prodotto scalare e prodotto vettoriale.

---

### **2. Parallelismo e perpendicolarità tra piani**

Siano

$$
\alpha:\ \mathbf n_\alpha\cdot\overrightarrow{QX}=0,
\qquad
\beta:\ \mathbf n_\beta\cdot\overrightarrow{PX}=0
$$

I piani sono paralleli se e solo se i loro normali sono paralleli:

$$
\alpha\parallel\beta
\iff
\mathbf n_\alpha=h\mathbf n_\beta
\quad(h\neq0)
$$

Sono perpendicolari se e solo se i normali sono ortogonali:

$$
\alpha\perp\beta
\iff
\mathbf n_\alpha\cdot\mathbf n_\beta=0
$$

Se i normali sono paralleli, i piani possono essere coincidenti oppure paralleli distinti; occorre controllare anche i termini noti.

---

### **3. Parallelismo e perpendicolarità tra retta e piano**

Sia $r$ una retta di vettore direzione $\mathbf v$ e sia $\alpha$ un piano di normale $\mathbf n$.

La retta è parallela al piano quando la sua direzione è ortogonale al normale:

$$
r\parallel\alpha
\iff
\mathbf v\cdot\mathbf n=0
$$

Questo caso comprende la possibilità $r\subseteq\alpha$. Per distinguere una retta contenuta da una parallela esterna, si verifica se un punto di $r$ soddisfa l'equazione del piano.

La retta è perpendicolare al piano quando la sua direzione è parallela al normale:

$$
r\perp\alpha
\iff
\mathbf v=k\mathbf n
\quad(k\neq0)
$$

Le configurazioni geometriche della pagina 1 mostrano piani paralleli o ortogonali e rette parallele o perpendicolari rispetto al piano.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Esercizio parametrico**

Si consideri la famiglia di rette

$$
r_k:
\begin{cases}
x=1+2t,\\
y=3-kt,\\
z=k-2t,
\end{cases}
$$

e il piano

$$
\pi:x+3y-z=4
$$

Il vettore direzione della retta è

$$
\mathbf v_k=(2,-k,-2)
$$

mentre il normale al piano è

$$
\mathbf n=(1,3,-1)
$$

#### **4.1. Retta parallela al piano**

Imponiamo

$$
\mathbf v_k\cdot\mathbf n=0
$$

Si ottiene

$$
2-3k+2=0
\quad\Longrightarrow\quad
\boxed{k=\frac43}
$$

Per $t=0$ il punto della retta è $(1,3,4/3)$ e

$$
1+9-\frac43=\frac{26}{3}\neq4
$$

quindi la retta è parallela e distinta dal piano, non contenuta in esso.

#### **4.2. Retta perpendicolare al piano**

Imponiamo $\mathbf v_k=\lambda\mathbf n$. Dalla prima componente $\lambda=2$; la seconda dà

$$
-k=6
\quad\Longrightarrow\quad
\boxed{k=-6}
$$

e la terza, $-2=-\lambda$, è coerente.

#### **4.3. Punto di intersezione per $k=-6$**

La retta perpendicolare è

$$
\begin{cases}
x=1+2t,\\
y=3+6t,\\
z=-6-2t.
\end{cases}
$$

Sostituendo nel piano:

$$
1+2t+3(3+6t)-(-6-2t)=4
$$

$$
16+22t=4
\quad\Longrightarrow\quad
t=-\frac6{11}
$$

Quindi

$$
\boxed{H=\left(-\frac1{11},-\frac3{11},-\frac{54}{11}\right)}
$$

La verifica diretta è

$$
-\frac1{11}+3\left(-\frac3{11}\right)
-\left(-\frac{54}{11}\right)=\frac{44}{11}=4
$$

---

### **5. Retta intersezione di due piani**

Due piani non paralleli

$$
\alpha:ax+by+cz+d=0
$$

$$
\beta:mx+ny+pz+q=0
$$

si intersecano lungo una retta descritta dal sistema

$$
\begin{cases}
ax+by+cz+d=0,\\
mx+ny+pz+q=0.
\end{cases}
$$

Per ottenere una forma parametrica servono:

1. un punto $R$ che risolva il sistema;
2. un vettore direzione ortogonale a entrambi i normali

$$
\mathbf n_\alpha=(a,b,c),
\qquad
\mathbf n_\beta=(m,n,p)
$$

Un tale vettore è

$$
\mathbf v=\mathbf n_\alpha\times\mathbf n_\beta
$$

Quindi la retta di intersezione è

$$
X=R+t(\mathbf n_\alpha\times\mathbf n_\beta)
$$

Se il prodotto vettoriale è nullo, i normali sono paralleli e i piani non individuano una retta: sono paralleli distinti o coincidenti.

---

### **6. Fasci di piani**

La famiglia ottenuta combinando linearmente le equazioni di due piani $\alpha$ e $\beta$ è il **fascio di piani** da essi generato.

Se

$$
\alpha:ax+by+cz+d=0
$$

$$
\beta:mx+ny+pz+q=0
$$

il fascio è

$$
h(ax+by+cz+d)+k(mx+ny+pz+q)=0
$$

con $(h,k)\neq(0,0)$.

- Se $\alpha$ e $\beta$ sono incidenti, il fascio è **proprio** e tutti i piani contengono la retta sostegno $s=\alpha\cap\beta$.
- Se $\alpha$ e $\beta$ sono paralleli, il fascio è **improprio** e tutti i suoi piani sono paralleli.

Se una retta è data proprio come intersezione dei due piani, la formula precedente descrive tutti i piani che la contengono.

---

### **7. Angoli nello spazio**

Per due vettori non nulli,

$$
\cos\theta=
\frac{\mathbf v\cdot\mathbf w}
{\lVert\mathbf v\rVert\lVert\mathbf w\rVert}
$$

#### **7.1. Angolo tra due rette**

È l'angolo tra i loro vettori direzione. Se si intende l'angolo non orientato minore o uguale a $\pi/2$, si usa

$$
\cos\theta=
\frac{|\mathbf v\cdot\mathbf w|}
{\lVert\mathbf v\rVert\lVert\mathbf w\rVert}
$$

#### **7.2. Angolo tra due piani**

È l'angolo tra i rispettivi vettori normali:

$$
\cos\theta=
\frac{|\mathbf n_\alpha\cdot\mathbf n_\beta|}
{\lVert\mathbf n_\alpha\rVert
\lVert\mathbf n_\beta\rVert}
$$

#### **7.3. Angolo tra retta e piano**

È il complementare dell'angolo tra il vettore direzione $\mathbf v$ della retta e il normale $\mathbf n$ del piano. Se $\varphi\in[0,\pi/2]$ è l'angolo retta-piano,

$$
\sin\varphi=
\frac{|\mathbf v\cdot\mathbf n|}
{\lVert\mathbf v\rVert\lVert\mathbf n\rVert}
$$

Questa formula restituisce $0$ per una retta parallela al piano e $\pi/2$ per una retta perpendicolare.

---

### **8. Errori comuni**

- Dire che una retta con $\mathbf v\cdot\mathbf n=0$ è certamente esterna al piano: potrebbe esservi contenuta.
- Usare il prodotto vettoriale dei vettori direzione anziché dei normali per trovare l'intersezione di due piani.
- Ammettere $(h,k)=(0,0)$ in un fascio.
- Confondere l'angolo retta-piano con l'angolo retta-normale invece del suo complementare.

---

> ✅ Le relazioni tra piani e rette dipendono da vettori direzione e normali. Due piani non paralleli si intersecano in una retta diretta come il prodotto vettoriale dei normali. Le combinazioni lineari delle loro equazioni formano un fascio; gli angoli si calcolano mediante prodotti scalari.
