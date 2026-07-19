# **M3 UD3 Lezione 2 - Mutue posizioni di rette in $\mathbb R^2$**

### **1. Obiettivi e prerequisiti**

Studiamo parallelismo, perpendicolarità, incidenza, distanze e fasci di rette nel piano. Sono prerequisiti le forme parametrica e cartesiana di una retta, i sistemi lineari e il prodotto scalare.

---

### **2. Parallelismo e perpendicolarità**

Siano

$$
r:\ \overrightarrow{QP}=t\mathbf v,
\qquad
s:\ \overrightarrow{TX}=k\mathbf u
$$

Allora

$$
r\parallel s\iff\mathbf v=h\mathbf u
\quad\text{per qualche }h\neq0
$$

$$
r\perp s\iff\mathbf v\cdot\mathbf u=0
$$

Se le rette sono in forma cartesiana,

$$
r:ax+by+c=0,
\qquad
s:a'x+b'y+c'=0
$$

i vettori normali sono $\mathbf n=(a,b)$ e $\mathbf n'=(a',b')$. Pertanto

$$
r\parallel s\iff(a,b)=h(a',b')
$$

$$
r\perp s\iff aa'+bb'=0
$$

Nel caso misto, una retta di direzione $\mathbf v$ è parallela a una retta di normale $\mathbf n$ se $\mathbf v\cdot\mathbf n=0$, ed è perpendicolare se $\mathbf v$ è multiplo di $\mathbf n$.

---

### **3. Esempi su parallelismo e perpendicolarità**

#### **3.1. Rette per un punto**

Data

$$
r:3x+4y-1=0
$$

e il punto $A=(1,4)$:

- la parallela per $A$ ha lo stesso vettore normale $(3,4)$:

$$
3x+4y+c=0,
\qquad 3+16+c=0
$$

quindi

$$
\boxed{3x+4y-19=0}
$$

- una perpendicolare ha normale $(4,-3)$:

$$
4x-3y+c=0,
\qquad 4-12+c=0
$$

quindi

$$
\boxed{4x-3y+8=0}
$$

#### **3.2. Triangolo rettangolo**

Siano

$$
A=(2,3),
\quad B=(1,1),
\quad C=(3,0)
$$

Calcoliamo i lati uscenti da $B$:

$$
\overrightarrow{BA}=\begin{bmatrix}1\\2\end{bmatrix},
\qquad
\overrightarrow{BC}=\begin{bmatrix}2\\-1\end{bmatrix}
$$

Poiché

$$
\overrightarrow{BA}\cdot\overrightarrow{BC}=2-2=0
$$

l'angolo in $B$ è retto. L'ipotenusa è dunque $AC$.

---

### **4. Incidenza di due rette**

Per trovare il punto comune alle rette

$$
ax+by+c=0,
\qquad
a'x+b'y+c'=0
$$

si risolve il sistema

$$
\begin{cases}
ax+by+c=0,\\
a'x+b'y+c'=0.
\end{cases}
$$

Si hanno tre casi:

1. i vettori normali non sono proporzionali: una sola intersezione;
2. tutti i coefficienti, inclusi i termini noti, sono proporzionali: rette coincidenti;
3. i normali sono proporzionali ma i termini noti non rispettano la stessa proporzione: rette parallele distinte.

Nel caso parametrico occorre usare parametri diversi:

$$
r:P=Q+t\mathbf v,
\qquad
s:P=T+h\mathbf u
$$

Il punto comune non è in generale associato allo stesso valore numerico del parametro sulle due rette.

> ⚠️ Scrivere lo stesso simbolo $t$ nelle due parametrizzazioni può imporre un vincolo inesistente e far perdere l'intersezione corretta.

---

### **5. Esempi di incidenza**

#### **5.1. Piede della perpendicolare**

Intersechiamo

$$
3x+4y-1=0
$$

con la perpendicolare per $A=(1,4)$ trovata sopra:

$$
4x-3y+8=0
$$

Dal sistema

$$
\begin{cases}
3x+4y=1,\\
4x-3y=-8
\end{cases}
$$

si ottiene, eliminando $x$,

$$
25y=28
\quad\Longrightarrow\quad
y=\frac{28}{25}
$$

Quindi

$$
3x=1-\frac{112}{25}=-\frac{87}{25},
\qquad
x=-\frac{29}{25}
$$

Il punto comune è

$$
\boxed{H=\left(-\frac{29}{25},\frac{28}{25}\right)}
$$

#### **5.2. Intersezioni con gli assi**

La retta per $A=(2,3)$ e $B=(1,1)$ ha direzione $(-1,-2)$:

$$
\begin{cases}
x=2-t,\\
y=3-2t.
\end{cases}
$$

La forma cartesiana è

$$
\boxed{2x-y-1=0}
$$

Con l'asse $x$, $y=0$ e quindi $x=1/2$; con l'asse $y$, $x=0$ e quindi $y=-1$. Le intersezioni sono

$$
\left(\frac12,0\right),
\qquad (0,-1)
$$

---

### **6. Distanze nel piano**

#### **6.1. Distanza tra due punti**

Per $P=(a,b)$ e $Q=(m,n)$,

$$
d(P,Q)=\lVert\overrightarrow{PQ}\rVert
=\sqrt{(m-a)^2+(n-b)^2}
$$

#### **6.2. Distanza punto-retta**

Sia

$$
r:ax+by+c=0
$$

e sia $P=(m,n)$. Il segmento più corto tra $P$ e $r$ è perpendicolare alla retta. Poiché $(a,b)$ è un vettore normale, la distanza è la proiezione di un vettore $\overrightarrow{RP}$, con $R=(h,k)\in r$, sulla direzione normale:

$$
d(P,r)=
\frac{|\overrightarrow{RP}\cdot(a,b)|}{\sqrt{a^2+b^2}}
$$

Essendo $ah+bk+c=0$,

$$
\begin{aligned}
|\overrightarrow{RP}\cdot(a,b)|
&=|a(m-h)+b(n-k)|\\
&=|am+bn-(ah+bk)|\\
&=|am+bn+c|.
\end{aligned}
$$

Quindi

> 📌 La formula operativa è
>
> $$
> \boxed{d(P,r)=
> \frac{|am+bn+c|}{\sqrt{a^2+b^2}}}
> $$

**Rappresentazione della pagina 3 del PDF.** La figura mostra il punto $P$, un punto generico $R$ della retta, il piede $Q$ della perpendicolare e la proiezione di $\overrightarrow{RP}$ sul normale.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Fasci di rette**

Siano

$$
r:ax+by+c=0,
\qquad
s:a'x+b'y+c'=0
$$

La famiglia

$$
h(ax+by+c)+k(a'x+b'y+c')=0,
\qquad (h,k)\neq(0,0)
$$

è il **fascio di rette** generato da $r$ e $s$.

- Se $r$ e $s$ sono incidenti, ogni retta del fascio passa per il punto $P=r\cap s$. Il fascio è **proprio** e $P$ ne è il **centro** o **sostegno**.
- Se $r$ e $s$ sono parallele, ogni retta del fascio è parallela alle generatrici. Il fascio è **improprio**.

Le coppie $(h,k)$ e $(\lambda h,\lambda k)$ con $\lambda\neq0$ rappresentano la stessa retta.

---

### **8. Esempi conclusivi**

#### **8.1. Retta del fascio passante per l'origine**

Consideriamo

$$
r:3x-2y+1=0,
\qquad
s:2x+y-3=0
$$

Il fascio è

$$
h(3x-2y+1)+k(2x+y-3)=0
$$

Perché la retta passi per l'origine deve essere

$$
h-3k=0
\quad\Longrightarrow\quad h=3k
$$

Ponendo $k=1$, $h=3$:

$$
3(3x-2y+1)+(2x+y-3)=0
$$

da cui

$$
\boxed{11x-5y=0}
$$

#### **8.2. Distanza punto-retta**

Per $P=(1,2)$ e $r:3x+4y-1=0$,

$$
d(P,r)=
\frac{|3+8-1|}{\sqrt{9+16}}
=\frac{10}{5}=\boxed{2}
$$

---

### **9. Errori comuni**

- Controllare il parallelismo confrontando solo un coefficiente.
- Confondere normali perpendicolari con rette parallele: normali perpendicolari corrispondono a rette perpendicolari.
- Omettere il valore assoluto nella distanza punto-retta.
- Usare $(h,k)=(0,0)$ nel fascio, ottenendo $0=0$ e non una retta.
- Usare lo stesso parametro per due rette distinte durante il calcolo dell'intersezione.

---

> ✅ Le mutue posizioni delle rette si leggono dai vettori direzione o normali. L'incidenza si determina con un sistema lineare; la distanza punto-retta è $|am+bn+c|/\sqrt{a^2+b^2}$; una combinazione lineare di due equazioni genera il fascio proprio o improprio individuato dalle due rette.
