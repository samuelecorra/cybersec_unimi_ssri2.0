# **M4 UD3 Lezione 1A - Equazioni e fattorizzazione in C**

### **1. Equazioni di secondo grado**

Consideriamo

$$
az^2+bz+c=0,
\qquad a,b,c\in\mathbb C,
\qquad a\ne0.
$$

Il completamento del quadrato è identico a quello reale:

$$
4a(az^2+bz+c)=(2az+b)^2-(b^2-4ac).
$$

Ponendo

$$
\Delta=b^2-4ac,
$$

l'equazione equivale a

$$
(2az+b)^2=\Delta.
$$

Se $\delta$ è una delle due radici quadrate di $\Delta$, le soluzioni sono

$$
z_{1,2}=\frac{-b\pm\delta}{2a}.
$$

La scelta di $-\delta$ scambia soltanto le due soluzioni. Se $\Delta=0$, si ottiene la radice doppia

$$
z=-\frac b{2a}.
$$

> 📌 In $\mathbb C$ la formula risolutiva non richiede la condizione $\Delta\ge0$: ogni discriminante possiede radici quadrate complesse.

---

### **2. Radice quadrata in forma algebrica**

Per trovare $w=u+vi$ tale che

$$
w^2=a+bi,
$$

confrontiamo le componenti:

$$
\begin{cases}
u^2-v^2=a,\\
2uv=b.
\end{cases}
$$

Poiché $|w|^2=|a+bi|$, vale anche

$$
u^2+v^2=\sqrt{a^2+b^2}.
$$

Sommando e sottraendo,

$$
u^2=\frac{\sqrt{a^2+b^2}+a}{2},
$$

$$
v^2=\frac{\sqrt{a^2+b^2}-a}{2}.
$$

I segni di $u$ e $v$ devono soddisfare $2uv=b$; la seconda radice è l'opposto della prima.

- Se $b>0$, $u$ e $v$ hanno lo stesso segno; la radice principale ha $u>0$ e $v>0$.
- Se $b<0$, $u$ e $v$ hanno segno opposto; la radice principale ha $u>0$ e $v<0$.
- Se $b=0$ e $a>0$, le radici sono $\pm\sqrt a$.
- Se $b=0$ e $a<0$, le radici sono $\pm i\sqrt{-a}$.
- Se $a=b=0$, esiste la sola radice distinta $0$.

Esempio: per $a=5$, $b=12$,

$$
\sqrt{a^2+b^2}=13,
$$

$$
u^2=9,\qquad v^2=4.
$$

Poiché $2uv>0$, $u$ e $v$ hanno lo stesso segno. Le radici di $5+12i$ sono

$$
3+2i,\qquad -3-2i.
$$

---

### **3. Esempi di equazioni quadratiche**

#### **3.1 Coefficienti reali e discriminante negativo**

Per

$$
z^2-4z+13=0,
$$

si ha $\Delta=16-52=-36$ e $\delta=6i$. Quindi

$$
z=2\pm3i.
$$

#### **3.2 Coefficienti complessi**

Risolviamo

$$
z^2-(1+i)z+i=0.
$$

Il discriminante è

$$
\Delta=(1+i)^2-4i=2i-4i=-2i.
$$

Poiché $(1-i)^2=-2i$, possiamo scegliere $\delta=1-i$. Pertanto

$$
z_1=\frac{1+i+1-i}{2}=1,
$$

$$
z_2=\frac{1+i-1+i}{2}=i.
$$

La verifica è

$$
z^2-(1+i)z+i=(z-1)(z-i).
$$

---

### **4. Teorema fondamentale dell'algebra**

#### **4.1 Enunciato**

> 📌 **Teorema fondamentale dell'algebra.** Ogni polinomio non costante a coefficienti complessi possiede almeno una radice in $\mathbb C$.

Applicando ripetutamente il teorema del fattore si ottiene la forma equivalente: se $P\in\mathbb C[z]$ ha grado $n\ge1$, allora esistono $a\ne0$ e $\alpha_1,\ldots,\alpha_n\in\mathbb C$ tali che

$$
P(z)=a\prod_{k=1}^n(z-\alpha_k),
$$

dove le radici sono ripetute secondo la loro molteplicità. Dunque un polinomio di grado $n$ ha esattamente $n$ radici complesse contando le molteplicità.

#### **4.2 Stato della dimostrazione**

Il teorema non è un fatto puramente formale sulle operazioni complesse. Le dimostrazioni standard usano strumenti come completezza, continuità, compattezza, integrali complessi o teorema di Liouville. Questi strumenti non sono ancora disponibili.

La dimostrazione viene quindi **rinviata esplicitamente**, non assunta come già svolta. In M04 si usano soltanto l'enunciato e le sue conseguenze algebriche; la completezza di $\mathbb R$ studiata in M02 non basta da sola senza la successiva teoria analitica.

Un campo nel quale ogni polinomio non costante ha una radice, e quindi si spezza in fattori lineari, si dice **algebricamente chiuso**. Il Teorema fondamentale dell'algebra afferma precisamente che $\mathbb C$ è algebricamente chiuso; $\mathbb R$ non lo è, come mostra $z^2+1$.

---

### **5. Molteplicità e fattorizzazione**

#### **5.1 Definizione**

Un numero $\alpha$ è radice di molteplicità $m\ge1$ di $P$ se

$$
P(z)=(z-\alpha)^mQ(z)
$$

con $Q(\alpha)\ne0$.

La radice è **semplice** se $m=1$ e **multipla** se $m\ge2$.

Usando la derivata formale dei polinomi, equivalente alla derivata usuale che sarà studiata in M09, vale il criterio

$$
\alpha\text{ ha molteplicità }m
$$

se e solo se

$$
P(\alpha)=P'(\alpha)=\cdots=P^{(m-1)}(\alpha)=0
$$

e

$$
P^{(m)}(\alpha)\ne0.
$$

Il criterio si ricava derivando ripetutamente la fattorizzazione $(z-\alpha)^mQ(z)$ con $Q(\alpha)\ne0$; qui è un approfondimento algebrico, non un uso anticipato dei teoremi analitici sulle derivate.

Esempio:

$$
P(z)=(z-i)^3(z+2)^2
$$

ha grado $5$, radice $i$ di molteplicità $3$ e radice $-2$ di molteplicità $2$.

#### **5.2 Fattorizzazione su C e su R**

Su $\mathbb C$ ogni polinomio non costante si fattorizza in fattori lineari. Su $\mathbb R$ i fattori irriducibili hanno grado $1$ oppure $2$ con discriminante negativo.

Per esempio,

$$
z^4+5z^2+4=(z^2+1)(z^2+4)
$$

è una fattorizzazione reale in quadratici irriducibili, mentre su $\mathbb C$

$$
z^4+5z^2+4=(z-i)(z+i)(z-2i)(z+2i).
$$

---

### **6. Polinomi reali e radici coniugate**

**Teorema.** Se $P\in\mathbb R[z]$ e $P(\alpha)=0$, allora

$$
P(\overline\alpha)=0.
$$

Le due radici hanno la stessa molteplicità.

**Dimostrazione.** Se

$$
P(z)=a_0+a_1z+\cdots+a_nz^n
$$

con $a_k\in\mathbb R$, allora $\overline{a_k}=a_k$ e

$$
\overline{P(\alpha)}
=
\sum_{k=0}^na_k\overline\alpha^{,k}
=P(\overline\alpha).
$$

Se $P(\alpha)=0$, il membro sinistro vale $0$, dunque $P(\overline\alpha)=0$. Se inoltre

$$
P(z)=(z-\alpha)^mQ(z),\qquad Q(\alpha)\ne0,
$$

allora, usando $P(z)=\overline{P(\overline z)}$,

$$
P(z)=(z-\overline\alpha)^m\overline{Q(\overline z)}.
$$

Il fattore residuo in $z=\overline\alpha$ vale $\overline{Q(\alpha)}\ne0$, quindi $\overline\alpha$ ha la stessa molteplicità $m$. $\square$

Se $\alpha=a+bi$ con $b\ne0$, la coppia coniugata produce il fattore reale

$$
(z-\alpha)(z-\overline\alpha)
=z^2-2az+(a^2+b^2).
$$

> ⚠️ Le radici coniugate sono garantite solo quando tutti i coefficienti del polinomio sono reali. L'equazione $z-i=0$ ha radice $i$ ma non $-i$.

---

### **7. Equazioni di grado superiore**

#### **7.1 Riduzione a potenze**

Per

$$
z^6+7z^3-8=0,
$$

poniamo $u=z^3$:

$$
u^2+7u-8=(u-1)(u+8)=0.
$$

Occorre quindi risolvere

$$
z^3=1
$$

e

$$
z^3=-8.
$$

Ciascuna equazione fornisce tre radici distinte; in totale si ottengono sei radici, come previsto dal grado.

#### **7.2 Fattorizzazione notevole**

L'equazione

$$
z^4+4=0
$$

si tratta con l'identità di Sophie Germain:

$$
z^4+4=(z^2-2z+2)(z^2+2z+2).
$$

Le quattro soluzioni sono

$$
1+i,\quad 1-i,\quad -1+i,\quad -1-i.
$$

---

### **8. Equazioni con coniugato e modulo**

Queste equazioni non sono in generale polinomiali in $z$ soltanto. Si usano coordinate cartesiane o forma polare.

#### **8.1 Equazione z^2=bar z**

La soluzione $z=0$ va trattata separatamente. Se $z=re^{i\theta}\ne0$,

$$
r^2e^{2i\theta}=re^{-i\theta}.
$$

Confrontando i moduli, $r=1$; confrontando gli argomenti,

$$
3\theta\equiv0\pmod{2\pi}.
$$

Le soluzioni sono quindi

$$
z=0,\quad 1,\quad e^{2\pi i/3},\quad e^{4\pi i/3}.
$$

#### **8.2 Equazione z+bar z=|z|^2**

Ponendo $z=x+yi$,

$$
2x=x^2+y^2,
$$

cioè

$$
(x-1)^2+y^2=1.
$$

L'insieme delle soluzioni è una circonferenza, non un elenco finito.

#### **8.3 Equazione |z|=z**

Il primo membro è reale non negativo. L'uguaglianza vale quindi se e solo se

$$
z\in[0,+\infty)\subset\mathbb R.
$$

Il caso

$$
|z|=-1
$$

non ha invece soluzioni, perché il modulo è sempre non negativo. Equazioni con modulo o coniugato possono quindi avere un numero finito di soluzioni, un luogo infinito oppure nessuna soluzione.

---

### **9. Strategia generale**

Per un'equazione complessa:

1. controlla il dominio e separa $z=0$ quando si divide per $z$;
2. scegli forma algebrica per somme, coniugati e componenti;
3. scegli forma polare per potenze, moduli e argomenti;
4. sfrutta sostituzioni e fattorizzazioni prima della formula generale;
5. conta le radici con le molteplicità se l'equazione è polinomiale;
6. verifica le soluzioni nell'equazione iniziale.

---

### **10. Errori frequenti**

1. Usare $\sqrt\Delta$ come un unico valore senza il segno $\pm$.
2. Dichiarare $n$ radici distinte anche in presenza di molteplicità.
3. Applicare il teorema delle radici coniugate a coefficienti non reali.
4. Contare solo le soluzioni reali di un polinomio reale.
5. Dividere per $z$ e perdere la soluzione $z=0$.
6. Trattare $\overline z$ come una variabile indipendente.
7. Presentare il Teorema fondamentale dell'algebra come dimostrato senza strumenti sufficienti.

---

### **11. Esercizi d'esame**

1. Risolvi $z^2+(2-i)z-2i=0$ e verifica somma e prodotto delle radici.
2. Trova in forma algebrica le radici quadrate di $-7+24i$.
3. Risolvi $z^8-17z^4+16=0$ e conta le soluzioni distinte.
4. Fattorizza completamente in $\mathbb C$ e in $\mathbb R$ il polinomio $z^4+4z^2+16$.
5. Costruisci il polinomio monico reale di grado minimo avente radici $2+i$ e $-1+2i$.
6. Determina tutte le soluzioni di $z^3=\overline z$.
7. Descrivi geometricamente le soluzioni di $z+\overline z=2|z|^2$.
8. Dimostra che un polinomio reale di grado dispari ha almeno una radice reale, usando il Teorema fondamentale dell'algebra e le coppie coniugate.
9. Trova un polinomio a coefficienti complessi per cui $i$ è radice ma $-i$ non lo è.
10. Spiega la differenza tra "quattro radici" e "quattro radici distinte" usando $(z-1)^3(z+i)$.

---

### **12. Riepilogo**

> ✅ Le equazioni quadratiche conservano la formula risolutiva in $\mathbb C$; il Teorema fondamentale dell'algebra garantisce la fattorizzazione lineare, mentre per coefficienti reali le radici non reali compaiono in coppie coniugate.

Sono stati distinti enunciato e prova del Teorema fondamentale: la conseguenza algebrica è disponibile, la dimostrazione analitica resta correttamente rinviata.
