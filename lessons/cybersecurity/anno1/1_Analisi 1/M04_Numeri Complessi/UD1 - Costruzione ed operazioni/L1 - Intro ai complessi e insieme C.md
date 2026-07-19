# **M4 UD1 Lezione 1 - Introduzione ai complessi e insieme C**

### **1. Perché introdurre nuovi numeri**

Nell'insieme dei numeri reali l'equazione

$$
x^2+1=0
$$

non ha soluzioni, perché $x^2\ge 0$ per ogni $x\in\mathbb R$. Il problema non si risolve dichiarando informalmente che $\sqrt{-1}$ esiste: occorre costruire un sistema numerico più ampio, dotato di operazioni coerenti, nel quale sia presente un elemento il cui quadrato valga $-1$.

Storicamente i numeri complessi emersero nel XVI secolo durante lo studio delle equazioni cubiche. Bombelli ne rese sistematico il calcolo; la rappresentazione geometrica fu sviluppata da Wessel e Argand e consolidata da Gauss. Il termine **immaginario** è storico e non indica numeri meno legittimi dei reali.

> 💡 L'estensione $\mathbb R\subset\mathbb C$ è analoga, per metodo, alle precedenti estensioni $\mathbb N\subset\mathbb Z\subset\mathbb Q\subset\mathbb R$: si amplia l'insieme conservando le operazioni già note.

---

### **2. Costruzione rigorosa come coppie reali**

#### **2.1 L'insieme sottostante**

Definiamo

$$
\mathbb C=\mathbb R^2=\{(a,b):a,b\in\mathbb R\}.
$$

Su queste coppie introduciamo le operazioni

$$
(a,b)+(c,d)=(a+c,b+d)
$$

e

$$
(a,b)\cdot(c,d)=(ac-bd,ad+bc).
$$

La seconda definizione è progettata affinché la coppia $(0,1)$ abbia quadrato $(-1,0)$:

$$
(0,1)^2=(0\cdot0-1\cdot1,0\cdot1+1\cdot0)=(-1,0).
$$

#### **2.2 Zero, unità e opposto**

Gli elementi neutri sono

$$
0_{\mathbb C}=(0,0),\qquad 1_{\mathbb C}=(1,0).
$$

L'opposto di $(a,b)$ è

$$
-(a,b)=(-a,-b).
$$

Infatti

$$
(a,b)+(-a,-b)=(0,0).
$$

> 📌 Un numero complesso è, per definizione, una coppia ordinata di reali dotata delle due operazioni precedenti. La scrittura $a+bi$ verrà dedotta da questa costruzione.

---

### **3. I reali dentro i complessi e l'unità immaginaria**

Identifichiamo ogni reale $a$ con la coppia $(a,0)$. Questa identificazione conserva somma e prodotto:

$$
(a,0)+(c,0)=(a+c,0),
$$

$$
(a,0)(c,0)=(ac,0).
$$

Possiamo quindi scrivere $a$ al posto di $(a,0)$ e considerare

$$
\mathbb R\subset\mathbb C.
$$

Definiamo l'**unità immaginaria**

$$
i=(0,1).
$$

Allora

$$
i^2=(-1,0)=-1.
$$

Ogni coppia $(a,b)$ si riscrive come

$$
(a,b)=(a,0)+(b,0)(0,1)=a+bi.
$$

Questa è la **forma algebrica** di un numero complesso.

> ⚠️ La notazione $i=\sqrt{-1}$ può essere usata come promemoria, ma non come definizione rigorosa. Inoltre l'equazione $w^2=-1$ ha due soluzioni, $i$ e $-i$; il simbolo di radice non seleziona automaticamente una radice principale in $\mathbb C$.

---

### **4. Parte reale, parte immaginaria e uguaglianza**

Per

$$
z=a+bi
$$

si definiscono

$$
\operatorname{Re}(z)=a,\qquad \operatorname{Im}(z)=b.
$$

La parte immaginaria è il coefficiente reale $b$, non il termine $bi$.

Poiché le coppie ordinate sono uguali componente per componente,

$$
a+bi=c+di
$$

se e solo se

$$
a=c\quad\text{e}\quad b=d.
$$

In particolare,

$$
a+bi=0\iff a=0\ \text{e}\ b=0.
$$

Esempi:

- $3-2i$ ha parte reale $3$ e parte immaginaria $-2$;
- $-5=-5+0i$ è reale;
- $4i=0+4i$ è immaginario puro;
- $0$ è contemporaneamente reale e immaginario puro.

---

### **5. La struttura di campo**

#### **5.1 Proprietà delle operazioni**

Le operazioni definite sulle coppie soddisfano associatività e commutatività di somma e prodotto, distributività, esistenza di $0$, $1$ e degli opposti. Per esempio, la distributività si verifica componente per componente:

$$
(a,b)\bigl((c,d)+(e,f)\bigr)
=
(a,b)(c+e,d+f)
$$

$$
=\bigl(a(c+e)-b(d+f),a(d+f)+b(c+e)\bigr),
$$

che coincide con la somma di $(a,b)(c,d)$ e $(a,b)(e,f)$.

#### **5.2 Inverso moltiplicativo**

Sia $z=(a,b)\ne(0,0)$. Poiché $a^2+b^2>0$, poniamo

$$
z^{-1}=\left(\frac{a}{a^2+b^2},-\frac{b}{a^2+b^2}\right).
$$

Moltiplicando,

$$
(a,b)z^{-1}
=
\left(\frac{a^2+b^2}{a^2+b^2},\frac{-ab+ab}{a^2+b^2}\right)
=(1,0).
$$

Ogni complesso non nullo possiede dunque un inverso.

> 📌 Con le operazioni definite, $\mathbb C$ è un **campo**: vi si possono eseguire somma, sottrazione, prodotto e divisione per un elemento non nullo.

La costruzione non assume l'esistenza dei complessi: mostra esplicitamente che le regole sono realizzabili mediante coppie di numeri reali.

---

### **6. Perché C non è un campo ordinato**

Sui reali l'ordine è compatibile con le operazioni: se $x>0$ e $y>0$, allora $xy>0$, e per ogni $x\ne0$ vale $x^2>0$.

Supponiamo per assurdo di poter definire su $\mathbb C$ un ordine totale con le stesse proprietà. Poiché $i\ne0$, si avrebbe

$$
i^2>0.
$$

Ma $i^2=-1$, dunque $-1>0$. D'altra parte anche $1=1^2>0$, e sommando due quantità positive si otterrebbe

$$
0=1+(-1)>0,
$$

contraddizione.

Quindi non esiste su $\mathbb C$ un ordine totale compatibile con somma e prodotto che estenda quello reale.

> ⚠️ Espressioni come $z>0$, $z<w$ o "il maggiore tra due complessi" non hanno significato canonico. Si possono confrontare i moduli $|z|$ e $|w|$, ma questo non ordina i numeri complessi come campo.

---

### **7. Chiusura delle equazioni e ruolo del modulo**

L'introduzione di $i$ risolve immediatamente equazioni come

$$
z^2+1=0,
$$

ma il risultato più profondo è il **Teorema fondamentale dell'algebra**: ogni polinomio non costante a coefficienti complessi ha almeno una radice complessa. Il teorema sarà enunciato con precisione in UD3; la sua dimostrazione richiede strumenti analitici successivi e verrà rinviata esplicitamente.

In assenza di un ordine, la nozione quantitativa fondamentale è il **modulo**. Per $z=a+bi$ si porrà

$$
|z|=\sqrt{a^2+b^2},
$$

che misura la distanza di $z$ dall'origine nel piano complesso.

---

### **8. Esempi graduati**

#### **8.1 Traduzione tra coppia e forma algebrica**

La coppia $(-2,5)$ rappresenta

$$
-2+5i.
$$

Viceversa, $7-3i$ rappresenta $(7,-3)$.

#### **8.2 Uguaglianza con parametro**

Determiniamo $x,y\in\mathbb R$ affinché

$$
(2x-y)+(x+3y)i=5-4i.
$$

L'uguaglianza tra complessi equivale al sistema reale

$$
\begin{cases}
2x-y=5,\\
x+3y=-4.
\end{cases}
$$

Dalla prima $y=2x-5$; sostituendo nella seconda,

$$
x+3(2x-5)=-4,
$$

da cui $7x=11$. Pertanto

$$
x=\frac{11}{7},\qquad y=-\frac{13}{7}.
$$

#### **8.3 Verifica nella costruzione**

Calcoliamo direttamente con le coppie:

$$
(2,1)(3,-4)=(2\cdot3-1\cdot(-4),2\cdot(-4)+1\cdot3)=(10,-5).
$$

In forma algebrica,

$$
(2+i)(3-4i)=10-5i.
$$

---

### **9. Errori frequenti**

1. Scrivere $\operatorname{Im}(a+bi)=bi$ invece di $b$.
2. Trattare $i$ come una variabile reale: $i$ è un elemento fissato con $i^2=-1$.
3. Deducere da $a+bi=0$ soltanto $a+b=0$: devono annullarsi entrambe le componenti.
4. Usare disuguaglianze tra complessi come se esistesse l'ordine reale.
5. Considerare $\mathbb C$ un insieme formalmente aggiunto senza verificare che le operazioni siano coerenti.

---

### **10. Esercizi**

1. Traduci in forma algebrica le coppie $(0,-3)$, $(\sqrt2,0)$ e $(-1,\pi)$; indica parte reale e immaginaria.
2. Determina $x,y\in\mathbb R$ sapendo che $(x+2y)+(3x-y)i=1+8i$.
3. Verifica dalle definizioni sulle coppie che $(1,2)\bigl((3,0)+(0,-1)\bigr)=(1,2)(3,0)+(1,2)(0,-1)$.
4. Trova l'inverso di $(2,-1)$ e controlla il prodotto con la coppia iniziale.
5. Spiega perché ordinare i complessi lessicograficamente non rende l'ordine compatibile con il prodotto.
6. Dimostra che se $z^2=0$ in $\mathbb C$, allora $z=0$, usando l'esistenza dell'inverso.

---

### **11. Riepilogo e collegamenti**

> ✅ $\mathbb C$ è il campo costruito su $\mathbb R^2$ con $(a,b)(c,d)=(ac-bd,ad+bc)$; identificando $i=(0,1)$, ogni elemento assume la forma unica $a+bi$ e soddisfa $i^2=-1$.

- $\mathbb R$ è immerso in $\mathbb C$ tramite $a\mapsto(a,0)$;
- uguaglianza, somma e prodotto si leggono componente per componente;
- ogni complesso non nullo ha un inverso;
- nessun ordine di campo può estendere l'ordine reale a $\mathbb C$;
- M01 fornisce algebra e trigonometria; le lezioni successive sviluppano calcolo, geometria, forme polari, radici ed equazioni.
