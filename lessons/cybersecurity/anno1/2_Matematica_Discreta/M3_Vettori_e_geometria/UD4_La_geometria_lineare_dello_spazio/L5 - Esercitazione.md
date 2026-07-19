# **M3 UD4 Lezione 5 - Esercitazione sulla geometria nello spazio**

### **1. Dati e obiettivi**

Si considerino il piano

$$
\pi:3x+y-2z+3=0
$$

la retta

$$
r:x=y-1=2-z
$$

e il punto

$$
Q=(1,1,3)
$$

Si richiede di:

1. trovare $P=r\cap\pi$;
2. calcolare $d(P,Q)$;
3. trovare il simmetrico $R$ di $Q$ rispetto a $P$;
4. stabilire se esiste un piano passante per $P,Q$ e perpendicolare a $\pi$;
5. trovare la retta $s$ per $Q$ perpendicolare a $\pi$ e il punto $S=s\cap\pi$;
6. calcolare l'area del triangolo $QRS$.

---

### **2. Soluzione 1: punto $P$, distanza e simmetrico**

#### **2.1. Parametrizzazione della retta**

Poniamo

$$
x=y-1=2-z=t
$$

Allora

$$
r:
\begin{cases}
x=t,\\
y=1+t,\\
z=2-t.
\end{cases}
$$

#### **2.2. Intersezione con il piano**

Sostituendo nell'equazione di $\pi$:

$$
3t+(1+t)-2(2-t)+3=0
$$

I termini costanti si annullano e resta

$$
6t=0
\quad\Longrightarrow\quad t=0
$$

Quindi

$$
\boxed{P=(0,1,2)}
$$

Verifica:

$$
3\cdot0+1-2\cdot2+3=0
$$

#### **2.3. Distanza fra $P$ e $Q$**

$$
\overrightarrow{PQ}=Q-P=(1,0,1)
$$

quindi

$$
d(P,Q)=\sqrt{1^2+0^2+1^2}
=\boxed{\sqrt2}
$$

#### **2.4. Simmetrico di $Q$ rispetto a $P$**

Il punto $P$ deve essere il punto medio di $QR$:

$$
P=\frac{Q+R}{2}
$$

Pertanto

$$
R=2P-Q
$$

Calcolando:

$$
R=(0,2,4)-(1,1,3)
=\boxed{(-1,1,1)}
$$

La verifica è

$$
\frac{Q+R}{2}=
\left(0,1,2\right)=P
$$

---

### **3. Soluzione 2: piano per $P,Q$ perpendicolare a $\pi$**

Il piano $\pi$ ha normale

$$
\mathbf n_\pi=(3,1,-2)
$$

Il piano cercato deve contenere

$$
\overrightarrow{PQ}=(1,0,1)
$$

e deve essere perpendicolare a $\pi$. Se $\mathbf n_\alpha$ è il suo normale, devono valere

$$
\mathbf n_\alpha\cdot\overrightarrow{PQ}=0
$$

$$
\mathbf n_\alpha\cdot\mathbf n_\pi=0
$$

Un modo diretto per ottenere un vettore ortogonale a entrambi è

$$
\mathbf n_\alpha=
\overrightarrow{PQ}\times\mathbf n_\pi
$$

Calcoliamo:

$$
\begin{aligned}
(1,0,1)\times(3,1,-2)
&=(-1,5,1).
\end{aligned}
$$

Usando il punto $P=(0,1,2)$:

$$
-(x-0)+5(y-1)+(z-2)=0
$$

quindi

$$
\boxed{\alpha:-x+5y+z-7=0}
$$

La soluzione annotata nel PDF arriva alla stessa equazione ponendo un normale generico $(a,b,c)$ e risolvendo

$$
\begin{cases}
a+c=0,\\
3a+b-2c=0.
\end{cases}
$$

Da $c=-a$ e $b=-5a$ si può scegliere $(a,b,c)=(1,-5,-1)$, che produce l'equazione equivalente

$$
x-5y-z+7=0
$$

Il piano esiste ed è unico, perché $\overrightarrow{PQ}$ non è parallelo al normale di $\pi$.

---

### **4. Soluzione 3: perpendicolare al piano e piede $S$**

Una retta perpendicolare a $\pi$ deve avere direzione parallela al normale $(3,1,-2)$. Passando per $Q=(1,1,3)$:

$$
s:
\begin{cases}
x=1+3t,\\
y=1+t,\\
z=3-2t.
\end{cases}
$$

Per trovare $S=s\cap\pi$, sostituiamo:

$$
3(1+3t)+(1+t)-2(3-2t)+3=0
$$

Semplificando:

$$
1+14t=0
\quad\Longrightarrow\quad
t=-\frac1{14}
$$

Quindi

$$
\boxed{
S=\left(
\frac{11}{14},
\frac{13}{14},
\frac{22}{7}
\right)}.
$$

Verifica sul piano:

$$
3\cdot\frac{11}{14}
+\frac{13}{14}
-2\cdot\frac{22}{7}+3=0
$$

Inoltre $S$ è il piede della perpendicolare da $Q$ a $\pi$, quindi

$$
d(Q,\pi)=|QS|
$$

---

### **5. Soluzione 4: area del triangolo $QRS$**

I punti sono

$$
Q=(1,1,3)
$$

$$
R=(-1,1,1)
$$

$$
S=\left(\frac{11}{14},\frac{13}{14},\frac{22}{7}\right)
$$

La fonte calcola l'area usando come base $QR$ e determinando l'altezza da $S$ alla retta $QR$.

#### **5.1. Lunghezza della base**

$$
\overrightarrow{QR}=R-Q=(-2,0,-2)
$$

quindi

$$
|QR|=\sqrt{4+4}=2\sqrt2
$$

#### **5.2. Retta $QR$**

Possiamo usare la direzione $(1,0,1)$:

$$
\ell:
\begin{cases}
x=1+t,\\
y=1,\\
z=3+t.
\end{cases}
$$

#### **5.3. Piano per $S$ perpendicolare a $QR$**

Il normale è $(1,0,1)$. Il piano è

$$
x-\frac{11}{14}+z-\frac{22}{7}=0
$$

ossia

$$
x+z-\frac{55}{14}=0
$$

Intersecandolo con $\ell$:

$$
1+t+3+t-\frac{55}{14}=0
$$

$$
2t+\frac1{14}=0
\quad\Longrightarrow\quad
t=-\frac1{28}
$$

Il piede dell'altezza è

$$
H=\left(\frac{27}{28},1,\frac{83}{28}\right)
$$

#### **5.4. Altezza**

$$
\overrightarrow{SH}
=H-S
=
\begin{bmatrix}
\frac5{28}\\[2pt]
\frac2{28}\\[2pt]
-\frac5{28}
\end{bmatrix}.
$$

Pertanto

$$
|SH|=
\frac{\sqrt{25+4+25}}{28}
=\frac{3\sqrt6}{28}
$$

#### **5.5. Area**

$$
\begin{aligned}
A_{QRS}
&=\frac12|QR|\,|SH|\\
&=\frac12(2\sqrt2)
\left(\frac{3\sqrt6}{28}\right)\\
&=\boxed{\frac{3\sqrt3}{14}}.
\end{aligned}
$$

#### **5.6. Verifica con il prodotto vettoriale**

Come controllo indipendente,

$$
\overrightarrow{QS}=
\left(-\frac3{14},-\frac1{14},\frac17\right)
$$

$$
\overrightarrow{QR}\times\overrightarrow{QS}
=\left(-\frac17,\frac57,\frac17\right)
$$

Il suo modulo è

$$
\frac{\sqrt{1+25+1}}7
=\frac{3\sqrt3}{7}
$$

e metà di tale valore è ancora $3\sqrt3/14$.

La figura manoscritta della pagina 3 mostra il triangolo $QRS$, la base $QR$, il punto medio $P$ e l'altezza $SH$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Controlli complessivi**

- $P$ appartiene sia a $r$ sia a $\pi$.
- $P$ è il punto medio di $QR$.
- Il normale di $\alpha$ è ortogonale sia a $PQ$ sia al normale di $\pi$.
- La direzione di $s$ coincide con il normale di $\pi$.
- $S$ appartiene sia a $s$ sia a $\pi$.
- Il valore dell'area coincide con quello ottenuto mediante il prodotto vettoriale.

---

### **7. Errori comuni**

- Parametrizzare $x=y-1=2-z$ senza considerare il segno in $z=2-t$.
- Calcolare il simmetrico con $R=Q-P$ invece di $R=2P-Q$.
- Per un piano perpendicolare a $\pi$, imporre che il suo normale sia parallelo al normale di $\pi$: ciò produrrebbe un piano parallelo.
- Calcolare l'area del triangolo senza il fattore $1/2$.

---

> ✅ L'esercizio produce $P=(0,1,2)$, $d(P,Q)=\sqrt2$, $R=(-1,1,1)$, il piano $-x+5y+z-7=0$, la retta $s:(x,y,z)=(1,1,3)+t(3,1,-2)$, il punto $S=(11/14,13/14,22/7)$ e l'area $3\sqrt3/14$.
