# **M4 UD1 Lezione 3 - Piano complesso, modulo e distanza**

### **1. Dal numero al punto**

Il complesso $z=a+bi$ è la coppia $(a,b)$ e si rappresenta nel **piano complesso** o piano di Argand-Gauss. L'asse orizzontale è l'asse reale, quello verticale è l'asse immaginario. Lo stesso oggetto può essere letto come punto $(a,b)$ o come vettore applicato nell'origine.

In terminologia geometrica, il numero $z$ è detto **affisso** del punto $(a,b)$; viceversa, il punto è l'immagine geometrica di $z$.

<!-- TODO FIGURA: piano complesso con assi Re e Im, punto z=a+bi, proiezioni a e b e vettore dall'origine; obiettivo didattico: coordinare forma algebrica, coppia ordinata e rappresentazione vettoriale. -->

> 📌 L'identificazione $\mathbb C\cong\mathbb R^2$ è geometrica e metrica; il prodotto complesso non è il prodotto scalare tra vettori.

---

### **2. Somma, opposto e coniugato nel piano**

La somma $z+w$ è la somma vettoriale e si costruisce con il parallelogramma. L'opposto $-z$ è la simmetria rispetto all'origine. Il coniugato

$$
\overline z=a-bi
$$

è la riflessione di $z$ rispetto all'asse reale.

Ne seguono altre due simmetrie:

$$
-\overline z=-a+bi
$$

è la riflessione rispetto all'asse immaginario, mentre $-z$ è la rotazione di angolo $\pi$ attorno all'origine.

<!-- TODO FIGURA: stesso punto z e immagini bar z, -z e -bar z nei quattro quadranti, con assi e archi di simmetria; obiettivo didattico: distinguere coniugio, opposto e loro composizione. -->

---

### **3. Modulo e distanza**

Il modulo di $z=a+bi$ è

$$
|z|=\sqrt{a^2+b^2}.
$$

Per il teorema di Pitagora è la distanza dall'origine. Se $z,w\in\mathbb C$, la distanza tra i punti è

$$
d(z,w)=|z-w|.
$$

In coordinate, per $z=a+bi$ e $w=c+di$,

$$
|z-w|=\sqrt{(a-c)^2+(b-d)^2}.
$$

La funzione $d$ è una metrica: per ogni $z,w,u\in\mathbb C$,

$$
d(z,w)\ge0,
$$

$$
d(z,w)=0\iff z=w,
$$

$$
d(z,w)=d(w,z),
$$

$$
d(z,u)\le d(z,w)+d(w,u).
$$

L'ultima proprietà deriva dalla disuguaglianza triangolare dimostrata nella sezione 5.

---

### **4. Proprietà moltiplicative del modulo**

#### **4.1 Prodotto**

Per ogni $z,w\in\mathbb C$,

$$
|zw|=|z|\,|w|.
$$

**Dimostrazione.** Entrambi i membri sono non negativi. Usando coniugio e norma quadrata,

$$
|zw|^2=(zw)\overline{zw}=zw\overline z\,\overline w
=z\overline z\,w\overline w=|z|^2|w|^2.
$$

Prendendo la radice quadrata non negativa si ottiene la tesi. $\square$

#### **4.2 Inverso, quoziente e potenze**

Se $z\ne0$,

$$
|z^{-1}|=\frac1{|z|}.
$$

Se $w\ne0$,

$$
\left|\frac zw\right|=\frac{|z|}{|w|}.
$$

Per ogni $n\in\mathbb Z$ e $z\ne0$ quando $n<0$,

$$
|z^n|=|z|^n.
$$

---

### **5. Disuguaglianze fondamentali**

#### **5.1 Disuguaglianza triangolare**

Per ogni $z,w\in\mathbb C$,

$$
|z+w|\le |z|+|w|.
$$

**Dimostrazione.** Si ha

$$
|z+w|^2=(z+w)(\overline z+\overline w)
=|z|^2+|w|^2+z\overline w+\overline z w.
$$

Poiché $z\overline w+\overline z w=2\operatorname{Re}(z\overline w)$ e $\operatorname{Re}(u)\le|u|$,

$$
|z+w|^2
\le |z|^2+|w|^2+2|z\overline w|
=|z|^2+|w|^2+2|z|\,|w|
=(|z|+|w|)^2.
$$

I membri sono non negativi, quindi si può estrarre la radice. $\square$

L'uguaglianza vale se e solo se $z$ e $w$ hanno la stessa direzione, cioè uno è un multiplo reale non negativo dell'altro, includendo il caso in cui uno dei due sia nullo.

#### **5.2 Disuguaglianza triangolare inversa**

Per ogni $z,w\in\mathbb C$,

$$
\bigl||z|-|w|\bigr|\le |z-w|.
$$

**Dimostrazione.** Da $z=(z-w)+w$ segue

$$
|z|\le|z-w|+|w|,
$$

quindi $|z|-|w|\le|z-w|$. Scambiando $z$ e $w$ si ottiene $|w|-|z|\le|z-w|$. Le due disuguaglianze equivalgono alla tesi. $\square$

#### **5.3 Stime utili**

Per somme finite,

$$
\left|\sum_{k=1}^n z_k\right|\le\sum_{k=1}^n|z_k|.
$$

Inoltre,

$$
|\operatorname{Re}(z)|\le|z|,\qquad
|\operatorname{Im}(z)|\le|z|,
$$

e

$$
|z+w|\ge\bigl||z|-|w|\bigr|.
$$

Queste stime saranno usate in M05-M08 per successioni e limiti.

---

### **6. Circonferenze, dischi e intorni**

Fissati $z_0\in\mathbb C$ e $r>0$:

$$
|z-z_0|=r
$$

descrive la circonferenza di centro $z_0$ e raggio $r$;

$$
|z-z_0|<r
$$

descrive il disco aperto;

$$
|z-z_0|\le r
$$

descrive il disco chiuso;

$$
0<|z-z_0|<r
$$

descrive l'intorno bucato di $z_0$.

<!-- TODO FIGURA: quattro pannelli con circonferenza, disco aperto, disco chiuso e intorno bucato centrati in z_0, bordi pieni o tratteggiati coerenti; obiettivo didattico: distinguere uguaglianza, disuguaglianza stretta, inclusione del bordo ed esclusione del centro. -->

Esempio:

$$
|z-(1+2i)|\le3
$$

equivale, ponendo $z=x+yi$, a

$$
(x-1)^2+(y-2)^2\le9.
$$

---

### **7. Argomento geometrico**

Per $z\ne0$, un **argomento** di $z$ è un angolo orientato $\theta$ dall'asse reale positivo al vettore che rappresenta $z$. Tutti gli argomenti sono

$$
\theta+2k\pi,\qquad k\in\mathbb Z.
$$

Adottiamo la convenzione

$$
\operatorname{Arg}(z)\in(-\pi,\pi].
$$

L'origine non ha direzione, dunque $\arg 0$ e $\operatorname{Arg}(0)$ non sono definiti.

<!-- TODO FIGURA: piano con un complesso non nullo z, modulo r, angolo principale theta e un giro aggiuntivo theta+2pi; evidenziare il semiasse reale negativo come bordo della convenzione (-pi,pi]; obiettivo didattico: separare argomento multivalore e argomento principale. -->

La determinazione numerica dell'argomento e la forma polare saranno sviluppate in UD2.

---

### **8. Esempi e controesempi**

#### **8.1 Distanza e luogo**

I punti equidistanti da $1$ e $-1$ soddisfano

$$
|z-1|=|z+1|.
$$

Con $z=x+yi$ e quadrando,

$$
(x-1)^2+y^2=(x+1)^2+y^2,
$$

da cui $x=0$: il luogo è l'asse immaginario.

#### **8.2 Uguaglianza triangolare**

Per $z=1+i$ e $w=2+2i$,

$$
|z+w|=|3+3i|=3\sqrt2=|z|+|w|.
$$

Per $z=1$ e $w=i$,

$$
|1+i|=\sqrt2<2=|1|+|i|.
$$

Il secondo esempio mostra che l'uguaglianza non vale in generale.

#### **8.3 Modulo uguale non significa numero uguale**

I numeri $1$, $-1$, $i$ e $-i$ hanno tutti modulo $1$, ma sono distinti. Il modulo perde l'informazione sulla direzione.

---

### **9. Errori frequenti**

1. Confondere $|z|$ con il valore assoluto applicato separatamente alle componenti.
2. Scrivere $|z+w|=|z|+|w|$ senza verificare il caso di uguaglianza.
3. Confondere $|z-w|$ con $|z|-|w|$.
4. Assegnare un argomento a $0$.
5. Confondere il punto $z$ con il suo modulo, che è un reale non negativo.
6. Dimenticare il centro in $|z-z_0|=r$.

---

### **10. Esercizi**

1. Rappresenta $2-3i$, $-2+3i$, i loro coniugati e opposti.
2. Calcola la distanza tra $1+4i$ e $-2-i$.
3. Dimostra $|z_1z_2\cdots z_n|=|z_1|\cdots|z_n|$ per induzione.
4. Usa la disuguaglianza triangolare inversa per stimare $|z-3i|$ sapendo che $|z|=5$.
5. Traduci in coordinate cartesiane $|z+2-i|<4$.
6. Determina per quali $z$ vale $|z-1|=|z-i|$.
7. Trova i casi di uguaglianza in $|z+1|\le|z|+1$.
8. Dimostra che se $|z|<1$, allora $|1+z|>1-|z|>0$.

---

### **11. Riepilogo**

> ✅ Il modulo è la distanza euclidea dall'origine e soddisfa le proprietà che rendono $\mathbb C$ uno spazio metrico; coniugato, somma e opposto hanno interpretazioni geometriche immediate.

$$
|zw|=|z|\,|w|,
$$

$$
|z+w|\le|z|+|w|,
$$

$$
\bigl||z|-|w|\bigr|\le|z-w|.
$$

Queste formule sono sia strumenti di calcolo sia prerequisiti per la futura analisi di successioni e funzioni.
