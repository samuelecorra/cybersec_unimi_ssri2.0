# **M3 UD4 Lezione 4 - Distanze in $\mathbb R^3$**

### **1. Obiettivi e prerequisiti**

Studiamo tutte le distanze tra punti, rette e piani nello spazio. Il principio unificante della fonte è ridurre ogni caso alla distanza fra due punti oppure alla distanza di un punto da un piano.

---

### **2. Distanza tra due punti**

Per

$$
P=(a,b,c),
\qquad Q=(m,n,p)
$$

la distanza è il modulo di $\overrightarrow{PQ}$:

$$
\boxed{
d(P,Q)=\sqrt{(m-a)^2+(n-b)^2+(p-c)^2}}
$$

Questa formula è il teorema di Pitagora applicato alle tre componenti ortogonali.

---

### **3. Distanza punto-piano**

Sia

$$
\gamma:ax+by+cz+d=0
$$

e sia $P=(m,n,p)$. Il vettore $\mathbf n=(a,b,c)$ è normale al piano. Proiettando sulla sua direzione si ottiene

$$
\boxed{
d(P,\gamma)=
\frac{|am+bn+cp+d|}
{\sqrt{a^2+b^2+c^2}}}
$$

La formula è invariante se l'equazione del piano viene moltiplicata per uno scalare non nullo: numeratore e denominatore vengono moltiplicati per lo stesso valore assoluto.

Se il numeratore è zero, $P\in\gamma$ e la distanza è nulla.

---

### **4. Distanza punto-retta**

Sia

$$
r:X=Q_0+t\mathbf v
$$

Se $P\in r$, allora $d(P,r)=0$. Altrimenti, il punto $Q\in r$ più vicino a $P$ è il piede della perpendicolare. La procedura del PDF è:

1. costruire il piano $\alpha$ passante per $P$ e perpendicolare a $r$; il suo normale è $\mathbf v$;
2. intersecare $\alpha$ con $r$ per trovare $Q$;
3. calcolare $d(P,Q)$.

In forma diretta, se $Q_0$ è un punto di $r$,

$$
d(P,r)=
\frac{\lVert\overrightarrow{Q_0P}\times\mathbf v\rVert}
{\lVert\mathbf v\rVert}
$$

ma il procedimento piano-intersezione resta quello esplicitamente adottato nella fonte.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Distanza retta-piano**

Siano $r$ una retta e $\gamma$ un piano.

- Se $r$ è incidente a $\gamma$, la distanza è zero.
- Se $r\subseteq\gamma$, la distanza è zero.
- Se $r\parallel\gamma$ ed è esterna, ogni punto di $r$ ha la stessa distanza da $\gamma$.

Nell'ultimo caso si sceglie un punto qualsiasi $R\in r$ e si calcola

$$
d(r,\gamma)=d(R,\gamma)
$$

---

### **6. Distanza piano-piano**

Siano $\gamma$ e $\gamma'$ due piani.

- Se sono incidenti o coincidenti, la distanza è zero.
- Se sono paralleli distinti, tutti i punti di un piano hanno la stessa distanza dall'altro.

Si sceglie dunque $P\in\gamma'$ e si calcola

$$
d(\gamma,\gamma')=d(P,\gamma)
$$

Nel caso in cui le equazioni siano già normalizzate con gli stessi coefficienti $a,b,c$,

$$
\gamma:ax+by+cz+d=0
$$

$$
\gamma':ax+by+cz+d'=0
$$

si ottiene direttamente

$$
d(\gamma,\gamma')=
\frac{|d-d'|}{\sqrt{a^2+b^2+c^2}}
$$

---

### **7. Distanza retta-retta**

Due rette nello spazio possono essere:

- incidenti;
- coincidenti;
- parallele distinte;
- sghembe.

Nei primi due casi la distanza è zero.

#### **7.1. Rette parallele distinte**

Tutti i punti di una retta hanno la stessa distanza dall'altra. Si può:

1. scegliere un piano perpendicolare a entrambe;
2. determinare i due punti in cui il piano interseca le rette;
3. calcolare la distanza fra tali punti.

Equivalentemente, scelti $P\in r$ e un punto $Q_0\in s$ con direzione comune $\mathbf v$,

$$
d(r,s)=
\frac{\lVert\overrightarrow{Q_0P}\times\mathbf v\rVert}
{\lVert\mathbf v\rVert}
$$

#### **7.2. Rette sghembe**

La distanza è la lunghezza del segmento $RS$, con $R\in r$ e $S\in s$, perpendicolare e incidente a entrambe le rette. Tale segmento è quello di lunghezza minima che collega le due rette.

La fonte propone due metodi.

**Metodo 1 — Perpendicolare comune.** Si prendono punti variabili $R\in r$ e $S\in s$ e si impone

$$
\overrightarrow{RS}\cdot\mathbf v_r=0,
\qquad
\overrightarrow{RS}\cdot\mathbf v_s=0
$$

Risolto il sistema nei due parametri,

$$
d(r,s)=\lVert\overrightarrow{RS}\rVert
$$

**Metodo 2 — Piano parallelo.** Si costruisce il piano $\alpha$ contenente $s$ e parallelo a $r$. Per qualunque $R\in r$,

$$
d(r,s)=d(R,\alpha)
$$

Questo secondo metodo conduce usualmente a conti più semplici.

Per completezza, se $\mathbf v_r\times\mathbf v_s\neq0$, la formula compatta è

$$
d(r,s)=
\frac{|\overrightarrow{Q_0P_0}\cdot
(\mathbf v_r\times\mathbf v_s)|}
{\lVert\mathbf v_r\times\mathbf v_s\rVert}
$$

dove $P_0\in r$ e $Q_0\in s$.

---

### **8. Esercizio completo**

Siano

$$
P=(1,2,0)
$$

e la retta in forma simmetrica

$$
r:x=y-1=z+2
$$

Introduciamo il parametro $t$ ponendo ogni espressione uguale a $t$:

$$
r:
\begin{cases}
x=t,\\
y=t+1,\\
z=t-2.
\end{cases}
$$

Un punto è $Q_0=(0,1,-2)$ e un vettore direzione è

$$
\mathbf v=(1,1,1)
$$

#### **8.1. Piano per $P$ perpendicolare a $r$**

Il normale del piano deve essere parallelo a $\mathbf v$. Quindi

$$
1(x-1)+1(y-2)+1(z-0)=0
$$

ossia

$$
\boxed{\alpha:x+y+z-3=0}
$$

#### **8.2. Distanza di $P$ da $r$**

Il piede $Q$ è $r\cap\alpha$. Sostituendo le parametriche:

$$
t+(t+1)+(t-2)-3=0
$$

$$
3t-4=0
\quad\Longrightarrow\quad
t=\frac43
$$

Quindi

$$
Q=\left(\frac43,\frac73,-\frac23\right)
$$

Il vettore $\overrightarrow{PQ}$ è

$$
\overrightarrow{PQ}=
\begin{bmatrix}
\frac13\\[2pt]\frac13\\[2pt]-\frac23
\end{bmatrix}
$$

e la distanza è

$$
d(P,r)=
\sqrt{\frac19+\frac19+\frac49}
=\boxed{\frac{\sqrt6}{3}}
$$

Verifica di perpendicolarità:

$$
\overrightarrow{PQ}\cdot(1,1,1)
=\frac13+\frac13-\frac23=0
$$

#### **8.3. Piano per $P$, per l'origine e parallelo a $r$**

Il piano deve contenere i vettori

$$
\overrightarrow{OP}=(1,2,0),
\qquad
\mathbf v=(1,1,1)
$$

Un normale è

$$
\overrightarrow{OP}\times\mathbf v
=
\begin{bmatrix}2\\-1\\-1\end{bmatrix}
$$

Poiché il piano passa per l'origine,

$$
\boxed{\beta:2x-y-z=0}
$$

Verifica sul punto $P$:

$$
2\cdot1-2-0=0
$$

---

### **9. Errori comuni**

- Applicare la formula punto-piano a una retta senza prima costruire il piano opportuno.
- Per rette sghembe, scegliere un segmento che unisca due punti qualsiasi invece della perpendicolare comune.
- Dimenticare di distinguere retta parallela al piano e retta contenuta nel piano.
- Interpretare erroneamente $x=y-1=z+2$ come tre equazioni uguali a zero anziché introdurre un parametro comune.

---

> ✅ Ogni distanza nello spazio si riduce a una distanza tra punti o a una distanza punto-piano. Nell'esercizio, il piano perpendicolare è $x+y+z-3=0$, il piede è $(4/3,7/3,-2/3)$, la distanza vale $\sqrt6/3$ e il piano per $P$, per l'origine e parallelo alla retta è $2x-y-z=0$.
