# **M1 UD3 Lezione 0A - Struttura e operazioni sui polinomi**

### **1. Perché i polinomi sono centrali**

I polinomi sono le espressioni algebriche più semplici capaci di descrivere comportamenti non lineari. Sono definiti per ogni numero reale, si sommano, si moltiplicano e si derivano senza uscire dalla stessa famiglia. Per questo compaiono nelle equazioni, nelle disequazioni, nelle approssimazioni di Taylor e nello studio locale delle funzioni.

Prima di imparare a fattorizzarli è necessario distinguere con precisione l'oggetto formale, la funzione associata e i problemi che possiamo porre su di esso.

### **2. Definizione di polinomio in una variabile**

Un polinomio in una variabile $x$ a coefficienti reali è un'espressione finita della forma:

$$
P(x)=a_nx^n+a_{n-1}x^{n-1}+\cdots+a_1x+a_0,
$$

dove $n\in\mathbb{N}$ e $a_0,a_1,\dots,a_n\in\mathbb{R}$.

Ogni termine $a_kx^k$ è un **monomio**:

- $a_k$ è il coefficiente;
- $x^k$ è la parte letterale;
- $k$ è il grado del monomio, se $a_k\ne0$.

Il coefficiente $a_0$ è il **termine noto**. Se $P$ non è nullo e $a_n\ne0$, il massimo esponente presente, cioè $n$, è il **grado** di $P$:

$$
\deg P=n.
$$

Il coefficiente $a_n$ è il **coefficiente direttivo** o coefficiente principale. Un polinomio è **monico** quando il suo coefficiente direttivo è $1$.

Esempio:

$$
P(x)=-3x^5+2x^2-7
$$

ha grado $5$, coefficiente direttivo $-3$ e termine noto $-7$. I coefficienti di $x^4$, $x^3$ e $x$ sono nulli.

### **3. Il polinomio nullo**

Il polinomio:

$$
0x^n+\cdots+0x+0
$$

è il **polinomio nullo**, indicato semplicemente con $0$. Non possiede un termine di grado massimo. In questa repository lasciamo quindi $\deg 0$ **non definito**.

In trattazioni più avanzate si usa talvolta la convenzione $\deg 0=-\infty$, utile per rendere uniformi alcune formule. Non ne avremo bisogno qui.

> ⚠️ Il polinomio costante non nullo $P(x)=c$ ha grado $0$; il polinomio nullo è un caso diverso.

### **4. Uguaglianza tra polinomi**

Due polinomi sono uguali quando hanno gli stessi coefficienti per ogni potenza di $x$. Per esempio:

$$
ax^2+bx+c=2x^2-3x+1
$$

come identità implica:

$$
a=2,\qquad b=-3,\qquad c=1.
$$

Termini mancanti devono essere letti come termini con coefficiente zero. Quindi:

$$
x^3-2=x^3+0x^2+0x-2.
$$

### **5. Polinomio formale e funzione polinomiale**

Il polinomio formale è la sequenza finita dei coefficienti e delle potenze. La **funzione polinomiale associata** è l'applicazione:

$$
f_P:\mathbb{R}\to\mathbb{R},\qquad f_P(x)=P(x).
$$

Nel caso dei coefficienti reali, due polinomi che assumono lo stesso valore per ogni $x\in\mathbb{R}$ hanno gli stessi coefficienti. Per questo, nel calcolo elementare, si usa spesso la stessa scrittura $P(x)$ sia per il polinomio sia per la funzione associata. La distinzione concettuale resta però utile.

#### **5.1 Valore in un punto e zero**

Il valore assunto in $x=a$ si ottiene sostituendo $a$ alla variabile:

$$
P(a)=a_na^n+a_{n-1}a^{n-1}+\cdots+a_1a+a_0.
$$

Un numero $a$ è uno **zero** o una **radice** di $P$ quando:

$$
P(a)=0.
$$

I termini “zero” e “radice” indicano qui lo stesso valore numerico; il fattore associato sarà invece $x-a$.

Esempio: per $P(x)=x^3-4x$:

$$
P(2)=8-8=0,
$$

quindi $2$ è una radice.

### **6. Identità ed equazione polinomiale**

La scrittura:

$$
P(x)=Q(x)
$$

può avere due significati diversi.

Come **identità**, afferma che l'uguaglianza vale per ogni $x$ del dominio. Per esempio:

$$
(x+1)^2=x^2+2x+1
$$

è vera per ogni $x\in\mathbb{R}$.

Come **equazione**, chiede invece per quali valori di $x$ i due membri coincidono. Per esempio:

$$
x^2+1=2x
$$

equivale a:

$$
(x-1)^2=0,
$$

ed è vera soltanto per $x=1$.

> 📌 Un'identità confronta coefficienti o espressioni per ogni $x$; un'equazione cerca un insieme di soluzioni.

### **7. Somma, differenza e prodotto per uno scalare**

Polinomi dello stesso grado formale si sommano termine a termine, inserendo coefficienti nulli quando necessario.

Siano:

$$
P(x)=2x^3-x+4,
$$

$$
Q(x)=-x^3+3x^2-2.
$$

Allora:

$$
P(x)+Q(x)=x^3+3x^2-x+2
$$

e:

$$
P(x)-Q(x)=3x^3-3x^2-x+6.
$$

Moltiplicando per uno scalare $\lambda\in\mathbb{R}$, ogni coefficiente viene moltiplicato per $\lambda$:

$$
\lambda P(x)=2\lambda x^3-\lambda x+4\lambda.
$$

Se $P\ne0$ e $\lambda\ne0$, allora $\deg(\lambda P)=\deg P$.

### **8. Grado della somma**

In generale:

$$
\deg(P+Q)\le\max\{\deg P,\deg Q\}.
$$

L'uguaglianza può fallire per cancellazione dei termini di grado massimo. Per esempio:

$$
P(x)=3x^4+x,
$$

$$
Q(x)=-3x^4+2x^2-1.
$$

Entrambi hanno grado $4$, ma:

$$
P(x)+Q(x)=2x^2+x-1
$$

ha grado $2$.

Se $\deg P\ne\deg Q$, il termine di grado maggiore non può cancellarsi e il grado della somma è il massimo dei due.

### **9. Prodotto di polinomi**

Il prodotto si ottiene applicando la proprietà distributiva a ogni coppia di monomi. Per esempio:

$$
(2x^2-x+1)(x+3)
$$

diventa:

$$
2x^3+6x^2-x^2-3x+x+3,
$$

quindi:

$$
(2x^2-x+1)(x+3)=2x^3+5x^2-2x+3.
$$

Per polinomi non nulli vale:

$$
\deg(PQ)=\deg P+\deg Q.
$$

Infatti, se i termini direttivi sono $a_mx^m$ e $b_nx^n$, il prodotto contiene il termine:

$$
a_mb_nx^{m+n}.
$$

Poiché $a_m\ne0$ e $b_n\ne0$, anche $a_mb_n\ne0$ e nessun altro prodotto produce una potenza maggiore di $m+n$.

### **10. Potenze di polinomi**

Per $k\in\mathbb{N}$, la potenza $P(x)^k$ è il prodotto di $k$ copie di $P(x)$. Se $P\ne0$:

$$
\deg(P^k)=k\deg P.
$$

Per esempio:

$$
\deg\left[(x^2-x+1)^4\right]=8.
$$

Per sviluppare potenze di binomi useremo i prodotti notevoli e il binomio di Newton, già introdotto nell'unità sugli strumenti discreti.

### **11. Composizione di polinomi**

La composizione si ottiene sostituendo un polinomio dentro un altro:

$$
(P\circ Q)(x)=P(Q(x)).
$$

Se:

$$
P(t)=t^2-1,\qquad Q(x)=x+2,
$$

allora:

$$
(P\circ Q)(x)=(x+2)^2-1=x^2+4x+3.
$$

Se $P$ e $Q$ sono non costanti:

$$
\deg(P\circ Q)=\deg P\cdot\deg Q.
$$

Questa operazione tornerà nello studio delle funzioni composte e nelle sostituzioni algebriche.

### **12. Equazioni, funzioni e grafici**

L'equazione polinomiale:

$$
P(x)=0
$$

chiede gli zeri del polinomio. Geometricamente sono le ascisse dei punti in cui il grafico della funzione $y=P(x)$ incontra l'asse $x$.

Un polinomio non nullo di grado $n$ non può avere più di $n$ radici reali distinte. Questa proprietà seguirà dal teorema del fattore: ogni radice distinta fornisce un fattore lineare distinto e il prodotto di più di $n$ fattori lineari avrebbe grado maggiore di $n$.

### **13. Errori frequenti**

Gli errori strutturali seguenti compromettono spesso anche divisioni e fattorizzazioni successive.

#### **13.1 Confondere grado e numero di termini**

$x^{10}+1$ ha due termini ma grado $10$.

#### **13.2 Ignorare i coefficienti nulli**

In $x^4-3x+1$ i coefficienti di $x^3$ e $x^2$ sono zero. Sarà essenziale inserirli nella divisione e in Ruffini.

#### **13.3 Confondere identità ed equazione**

Da $P(x)=Q(x)$ per un solo valore di $x$ non segue che i due polinomi siano identici.

#### **13.4 Applicare sempre l'uguaglianza dei gradi nella somma**

La cancellazione dei termini direttivi può abbassare il grado di $P+Q$.

### **14. Riepilogo**

> ✅ Un polinomio è una somma finita di monomi con esponenti naturali. Il grado misura la massima potenza con coefficiente non nullo.

Gli strumenti fondamentali sono:

- valutazione $P(a)$ e ricerca degli zeri;
- uguaglianza coefficiente per coefficiente;
- somma, prodotto, potenza e composizione;
- distinzione tra identità, equazione e funzione associata;
- controllo del grado e delle possibili cancellazioni.
