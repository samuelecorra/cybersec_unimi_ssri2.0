# **M4 UD1 Lezione 2 - Forma algebrica**

### **1. Obiettivo operativo**

La forma

$$
z=a+bi
$$

permette di calcolare usando le regole dell'algebra reale e la relazione $i^2=-1$. In questa lezione deriviamo le operazioni, il coniugato e l'inverso senza introdurre scorciatoie non giustificate.

---

### **2. Somma, opposto e sottrazione**

Per $z=a+bi$ e $w=c+di$,

$$
z+w=(a+c)+(b+d)i.
$$

L'opposto è

$$
-z=-a-bi,
$$

perciò

$$
z-w=(a-c)+(b-d)i.
$$

Esempio:

$$
(3-2i)-(-1+5i)=4-7i.
$$

> ⚠️ Sommare o sottrarre significa raccogliere separatamente parte reale e coefficiente di $i$.

---

### **3. Prodotto**

Usando la distributività,

$$
(a+bi)(c+di)=ac+adi+bci+bdi^2.
$$

Poiché $i^2=-1$,

$$
(a+bi)(c+di)=(ac-bd)+(ad+bc)i.
$$

Esempio:

$$
(2-3i)(1+4i)=2+8i-3i-12i^2=14+5i.
$$

Due identità utili sono

$$
(a+bi)(a-bi)=a^2+b^2
$$

e

$$
(z+w)^2=z^2+2zw+w^2,
$$

che valgono perché $\mathbb C$ è un campo commutativo.

---

### **4. Potenze dell'unità immaginaria**

Le potenze di $i$ sono periodiche di periodo $4$:

$$
i^0=1,\qquad i^1=i,\qquad i^2=-1,\qquad i^3=-i,\qquad i^4=1.
$$

Se $n=4q+r$, con $r\in\{0,1,2,3\}$, allora

$$
i^n=i^r.
$$

Per esempio,

$$
i^{2026}=i^{4\cdot506+2}=i^2=-1.
$$

Poiché $i^{-1}=1/i=-i$, la stessa regola modulo $4$ vale per ogni $n\in\mathbb Z$. Per esempio,

$$
i^{-7}=i^{4(-2)+1}=i.
$$

> 💡 Per esponenti negativi si può scegliere il resto non negativo della divisione euclidea per $4$; non occorre sviluppare frazioni ripetute.

Anche le somme si riducono per blocchi di quattro. Poiché

$$
1+i+i^2+i^3=0,
$$

per ogni $q\in\mathbb N$ vale

$$
\sum_{k=0}^{4q-1}i^k=0.
$$

Se l'estremo non chiude un blocco, si sommano soltanto i termini residui. Per esempio,

$$
\sum_{k=0}^{2026}i^k
=
\sum_{k=0}^{2023}i^k+i^{2024}+i^{2025}+i^{2026}
=0+1+i-1=i.
$$

---

### **5. Coniugato**

#### **5.1 Definizione e caratterizzazioni**

Il **coniugato** di $z=a+bi$ è

$$
\overline z=a-bi.
$$

Valgono

$$
z+\overline z=2\operatorname{Re}(z),
$$

$$
z-\overline z=2i\operatorname{Im}(z),
$$

e quindi

$$
\operatorname{Re}(z)=\frac{z+\overline z}{2},\qquad
\operatorname{Im}(z)=\frac{z-\overline z}{2i}.
$$

Inoltre,

$$
z\in\mathbb R\iff z=\overline z,
$$

$$
z\text{ è immaginario puro}\iff z=-\overline z.
$$

#### **5.2 Proprietà algebriche**

Per ogni $z,w\in\mathbb C$,

$$
\overline{\overline z}=z,
$$

$$
\overline{z+w}=\overline z+\overline w,
$$

$$
\overline{zw}=\overline z\,\overline w.
$$

Se $w\ne0$,

$$
\overline{\left(\frac{z}{w}\right)}=\frac{\overline z}{\overline w}.
$$

Dimostriamo la proprietà del prodotto. Se $z=a+bi$ e $w=c+di$, allora

$$
\overline{zw}=(ac-bd)-(ad+bc)i.
$$

D'altra parte,

$$
\overline z\,\overline w=(a-bi)(c-di)=(ac-bd)-(ad+bc)i.
$$

I due membri coincidono.

---

### **6. Modulo e norma quadrata**

Per $z=a+bi$ definiamo

$$
|z|=\sqrt{a^2+b^2}.
$$

Poiché

$$
z\overline z=(a+bi)(a-bi)=a^2+b^2,
$$

si ha l'identità fondamentale

$$
z\overline z=|z|^2.
$$

Ne seguono

$$
|z|\ge0,
$$

$$
|z|=0\iff z=0,
$$

$$
|\overline z|=|z|,
$$

$$
|\operatorname{Re}(z)|\le |z|,\qquad
|\operatorname{Im}(z)|\le |z|.
$$

Le proprietà moltiplicative e le disuguaglianze triangolari saranno dimostrate geometricamente e algebricamente nella lezione successiva.

---

### **7. Inverso e divisione**

#### **7.1 Formula dell'inverso**

Se $z\ne0$, allora $|z|^2>0$ e

$$
z^{-1}=\frac{\overline z}{|z|^2}.
$$

Infatti,

$$
z\frac{\overline z}{|z|^2}=\frac{z\overline z}{|z|^2}=1.
$$

Per $z=a+bi$,

$$
\frac1{a+bi}=\frac{a-bi}{a^2+b^2}.
$$

#### **7.2 Quoziente**

Se $w=c+di\ne0$,

$$
\frac zw=\frac{z\overline w}{w\overline w}.
$$

In coordinate,

$$
\frac{a+bi}{c+di}
=
\frac{(a+bi)(c-di)}{c^2+d^2}
=
\frac{ac+bd}{c^2+d^2}
+
\frac{bc-ad}{c^2+d^2}i.
$$

Esempio:

$$
\frac{3+4i}{1-2i}
=
\frac{(3+4i)(1+2i)}{5}
=
-1+2i.
$$

> ⚠️ Il denominatore $c^2+d^2$ è nullo se e solo se $c=d=0$. La divisione per $0$ resta impossibile anche in $\mathbb C$.

---

### **8. Equazioni algebriche elementari**

#### **8.1 Equazione lineare**

Se $az=b$ con $a\ne0$, allora

$$
z=\frac ba.
$$

Per esempio,

$$
(2-i)z=3+5i
$$

dà

$$
z=\frac{3+5i}{2-i}=\frac{(3+5i)(2+i)}5=\frac15+\frac{13}{5}i.
$$

#### **8.2 Equazione con coniugato**

Risolviamo

$$
z+2\overline z=3-i.
$$

Ponendo $z=x+yi$,

$$
(x+yi)+2(x-yi)=3-i,
$$

quindi

$$
3x-yi=3-i.
$$

Per uguaglianza delle componenti,

$$
x=1,\qquad y=1,
$$

e pertanto $z=1+i$.

#### **8.3 Parametro reale**

Determiniamo $t\in\mathbb R$ affinché

$$
\frac{t+i}{1-i}
$$

sia reale. Razionalizzando,

$$
\frac{(t+i)(1+i)}2=\frac{t-1}{2}+\frac{t+1}{2}i.
$$

La parte immaginaria deve essere nulla, dunque $t=-1$.

---

### **9. Identità e verifiche formative**

#### **9.1 Nessun divisore dello zero**

Se $zw=0$ e $z\ne0$, moltiplicando per $z^{-1}$ si ottiene $w=0$. Quindi

$$
zw=0\implies z=0\ \text{oppure}\ w=0.
$$

#### **9.2 Un'identità con il coniugato**

Per ogni $z,w\in\mathbb C$,

$$
|z+w|^2+|z-w|^2=2|z|^2+2|w|^2.
$$

Infatti,

$$
|z+w|^2=(z+w)(\overline z+\overline w)
$$

e

$$
|z-w|^2=(z-w)(\overline z-\overline w).
$$

Sommando, i termini misti si cancellano e restano $2z\overline z+2w\overline w$.

---

### **10. Errori frequenti**

1. Scrivere $i^2=1$ o $i^3=1$.
2. Dimenticare i termini incrociati nel prodotto.
3. Razionalizzare con lo stesso denominatore invece che con il coniugato.
4. Confondere $\overline{z+w}$ con $\overline z+w$.
5. Credere che $\overline{zw}=\overline z\,w$.
6. Usare $z/\overline z=1$: vale solo per $z$ reale non nullo.
7. Dimenticare che $\operatorname{Im}(z)$ è reale.

---

### **11. Esercizi**

1. Calcola $(2+3i)^2$, $(1-i)^5$, $i^{137}$, $i^{-2026}$ e $\sum_{k=3}^{103}i^k$.
2. Riduci in forma algebrica $\dfrac{2-5i}{3+i}$ e verificane il risultato moltiplicando per il denominatore.
3. Dimostra tutte le proprietà del coniugato enunciate nella sezione 5.
4. Risolvi $(1+2i)z=4-i$.
5. Risolvi il sistema $z+\overline z=6$, $z-\overline z=-4i$.
6. Determina $a\in\mathbb R$ affinché $(a+i)^2$ sia immaginario puro.
7. Dimostra che $z^2=\overline z^{,2}$ se e solo se $z$ è reale oppure immaginario puro.
8. Verifica l'identità del parallelogramma della sezione 9 con $z=1+2i$ e $w=-3+i$.

---

### **12. Riepilogo**

> ✅ In forma algebrica si calcola separando le componenti reali e immaginarie; coniugato e identità $z\overline z=|z|^2$ trasformano la divisione in un calcolo reale al denominatore.

Formule essenziali:

$$
(a+bi)(c+di)=(ac-bd)+(ad+bc)i,
$$

$$
\overline{zw}=\overline z\,\overline w,
$$

$$
z^{-1}=\frac{\overline z}{|z|^2}\quad(z\ne0),
$$

$$
i^n=i^r\quad\text{se }n\equiv r\pmod4.
$$
