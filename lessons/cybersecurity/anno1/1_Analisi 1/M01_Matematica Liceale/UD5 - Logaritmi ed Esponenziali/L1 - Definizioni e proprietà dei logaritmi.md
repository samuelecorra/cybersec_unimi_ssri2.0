# **M1 UD5 Lezione 1 - Definizioni e proprietà dei logaritmi**

### **1. Introduzione**

Il logaritmo nasce per rispondere a una domanda precisa: se conosco una base $a$ e un risultato $b$, quale esponente devo dare ad $a$ per ottenere $b$?

In forma esponenziale la domanda è:

$$
a^x=b
$$

Il logaritmo è proprio il numero $x$ che risolve questa equazione.

> 📌 Il logaritmo trasforma una domanda sull’esponente in un numero reale.

I logaritmi sono fondamentali in Analisi perché permettono di:

- invertire funzioni esponenziali;
- trasformare prodotti in somme;
- gestire crescite molto rapide o molto lente;
- risolvere equazioni e disequazioni in cui l’incognita compare all’esponente.

---

### **2. Definizione di logaritmo**

Siano:

$$
a>0,\quad a\neq 1,\quad b>0
$$

Si definisce:

$$
\log_a b = x
$$

se e solo se:

$$
a^x=b
$$

Il numero $a$ si chiama **base** del logaritmo, mentre $b$ si chiama **argomento**.

Quindi:

$$
\log_a b
$$

è l’esponente da dare ad $a$ per ottenere $b$.

Esempi:

$$
\log_2 8=3
$$

perché:

$$
2^3=8
$$

e:

$$
\log_{10} 0.01=-2
$$

perché:

$$
10^{-2}=0.01
$$

---

### **3. Condizioni di esistenza**

La definizione richiede:

$$
a>0,\quad a\neq 1,\quad b>0
$$

Queste condizioni non sono decorative: sono necessarie.

#### **3.1 Perché la base deve essere positiva**

Se $a<0$, l’espressione $a^x$ non è definita in modo reale e continuo per ogni $x\in\mathbb{R}$.

Per esempio:

$$
(-2)^{1/2}
$$

non è un numero reale.

#### **3.2 Perché la base non può essere 1**

Se $a=1$, allora:

$$
1^x=1
$$

per ogni $x$. Non sarebbe possibile invertire la funzione, perché infiniti esponenti danno lo stesso risultato.

#### **3.3 Perché l’argomento deve essere positivo**

Se $a>0$, allora:

$$
a^x>0
$$

per ogni $x\in\mathbb{R}$. Dunque non può esistere un esponente reale che produca un numero nullo o negativo.

Perciò:

$$
\log_a b
$$

è definito solo per:

$$
b>0
$$

> ⚠️ Il logaritmo di $0$ e il logaritmo di un numero negativo non sono definiti nei reali.

---

### **4. Valori fondamentali**

Dalla definizione seguono subito alcuni valori notevoli.

Poiché:

$$
a^0=1
$$

si ha:

$$
\log_a 1=0
$$

Poiché:

$$
a^1=a
$$

si ha:

$$
\log_a a=1
$$

Inoltre:

$$
\log_a a^x=x
$$

per ogni $x\in\mathbb{R}$, e:

$$
a^{\log_a x}=x
$$

per ogni $x>0$.

Queste due identità esprimono il fatto che logaritmo ed esponenziale in base $a$ sono funzioni inverse.

---

### **5. Logaritmo come funzione inversa**

La funzione esponenziale:

$$
f(x)=a^x
$$

ha dominio:

$$
\mathbb{R}
$$

e immagine:

$$
(0,+\infty)
$$

La funzione logaritmica:

$$
g(x)=\log_a x
$$

ha dominio:

$$
(0,+\infty)
$$

e immagine:

$$
\mathbb{R}
$$

Le due funzioni si scambiano dominio e immagine:

$$
f:\mathbb{R}\to(0,+\infty)
$$

$$
g:(0,+\infty)\to\mathbb{R}
$$

e valgono:

$$
g(f(x))=x
$$

$$
f(g(x))=x
$$

Non bisogna confondere la **funzione inversa** con il reciproco. L'inversa di $a^x$ è $\log_a x$ perché annulla l'effetto della composizione; il reciproco di $a^x$ è invece $a^{-x}$.

<!-- TODO FIGURA:
Nello stesso piano cartesiano rappresentare y=a^x e y=log_a(x), prima per a>1
e poi per 0<a<1. Tracciare la retta y=x ed evidenziare coppie di punti scambiate,
come (0,1) e (1,0). Indicare domini, immagini e asintoti.
Obiettivo didattico: mostrare la simmetria tra funzioni inverse.
-->

---

### **6. Monotonia del logaritmo**

La monotonia del logaritmo dipende dalla base.

Se:

$$
a>1
$$

allora:

$$
x\mapsto \log_a x
$$

è strettamente crescente.

Se invece:

$$
0<a<1
$$

allora:

$$
x\mapsto \log_a x
$$

è strettamente decrescente.

Questo sarà fondamentale nelle disequazioni logaritmiche.

> ⚠️ Quando $0<a<1$, passando da $\log_a A(x)<\log_a B(x)$ ad $A(x)$ e $B(x)$ il verso si inverte.

<!-- TODO FIGURA:
Grafici sullo stesso piano di y=log_2(x) e y=log_(1/2)(x). Evidenziare il punto (1,0),
il dominio x>0, l'asintoto verticale x=0 e la monotonia opposta delle due curve.
Obiettivo didattico: confrontare logaritmi con base maggiore di 1 e compresa tra 0 e 1.
-->

---

### **7. Proprietà algebriche dei logaritmi**

Le proprietà dei logaritmi derivano dalle proprietà delle potenze.

In tutte le formule seguenti assumiamo:

$$
a>0,\quad a\neq 1,\quad x>0,\quad y>0
$$

#### **7.1 Logaritmo di un prodotto**

$$
\log_a(xy)=\log_a x+\log_a y
$$

Perché? Se:

$$
\log_a x=u,\quad \log_a y=v
$$

allora:

$$
x=a^u,\quad y=a^v
$$

quindi:

$$
xy=a^u\cdot a^v=a^{u+v}
$$

e dunque:

$$
\log_a(xy)=u+v
$$

#### **7.2 Logaritmo di un quoziente**

$$
\log_a\left(\frac{x}{y}\right)=\log_a x-\log_a y
$$

Il quoziente deve essere formato da argomenti positivi:

$$
x>0,\quad y>0
$$

Infatti, ponendo $u=\log_a x$ e $v=\log_a y$, si ha:

$$
\frac xy=\frac{a^u}{a^v}=a^{u-v}
$$

e quindi, per definizione di logaritmo:

$$
\log_a\left(\frac xy\right)=u-v=\log_a x-\log_a y
$$

#### **7.3 Logaritmo di una potenza**

Per ogni $r\in\mathbb{R}$:

$$
\log_a(x^r)=r\log_a x
$$

con $x>0$.

La dimostrazione è analoga. Se $x=a^u$, allora $x^r=a^{ur}$ e dunque:

$$
\log_a(x^r)=ur=r\log_a x
$$

Esempio:

$$
\log_2(x^3)=3\log_2 x
$$

ma solo per:

$$
x>0
$$

Se l'argomento originario è $x^2$, esso è positivo per ogni $x\ne0$ anche quando $x<0$. La forma valida sul dominio completo è:

$$
\log_a(x^2)=2\log_a|x|,\qquad x\ne0
$$

Scrivere $2\log_a x$ restringerebbe indebitamente il dominio a $x>0$.

---

### **8. Attenzione al dominio nelle proprietà**

La proprietà:

$$
\log_a(xy)=\log_a x+\log_a y
$$

richiede:

$$
x>0,\quad y>0
$$

Non basta che il prodotto sia positivo.

Per esempio, se:

$$
x=-2,\quad y=-3
$$

allora:

$$
xy=6>0
$$

e quindi:

$$
\log_a(xy)=\log_a 6
$$

è definito, ma:

$$
\log_a x+\log_a y
$$

non ha senso nei reali.

> ⚠️ Le proprietà dei logaritmi non si applicano ignorando le condizioni di positività dei singoli argomenti.

---

### **9. Formula di cambiamento di base**

Se:

$$
a>0,\quad a\neq 1,\quad b>0,\quad b\neq 1,\quad x>0
$$

allora:

$$
\log_a x=\frac{\log_b x}{\log_b a}
$$

Questa formula permette di riscrivere un logaritmo in una base diversa.

In particolare, usando il logaritmo naturale:

$$
\log_a x=\frac{\ln x}{\ln a}
$$

dove:

$$
\ln x=\log_e x
$$

Il numero $e$ sarà studiato più avanti, ma già ora è utile sapere che $\ln$ indica il logaritmo in base $e$.

---

### **10. Logaritmo decimale e logaritmo naturale**

Due basi sono particolarmente frequenti.

Il **logaritmo decimale** è:

$$
\log_{10} x
$$

e spesso si indica semplicemente con:

$$
\log x
$$

Il **logaritmo naturale** è:

$$
\ln x=\log_e x
$$

In Analisi 1 il logaritmo naturale è il più importante, perché ha proprietà differenziali particolarmente semplici.

---

### **11. Esempi di calcolo**

#### **11.1 Calcolo diretto**

Calcoliamo:

$$
\log_3 81
$$

Cerchiamo l’esponente $x$ tale che:

$$
3^x=81
$$

Poiché:

$$
81=3^4
$$

si ha:

$$
\log_3 81=4
$$

#### **11.2 Argomento frazionario**

Calcoliamo:

$$
\log_2\left(\frac{1}{8}\right)
$$

Poiché:

$$
\frac{1}{8}=2^{-3}
$$

si ottiene:

$$
\log_2\left(\frac{1}{8}\right)=-3
$$

#### **11.3 Uso delle proprietà**

Semplifichiamo:

$$
\log_5(25x)-\log_5 x
$$

con:

$$
x>0
$$

Usando il quoziente:

$$
\log_5(25x)-\log_5 x=\log_5\left(\frac{25x}{x}\right)
$$

quindi:

$$
\log_5(25)=2
$$

---

### **12. Errori tipici**

#### **12.1 Somma dentro il logaritmo**

In generale:

$$
\log_a(x+y)\neq \log_a x+\log_a y
$$

La proprietà vale per il prodotto, non per la somma.

#### **12.2 Logaritmo di una differenza**

In generale:

$$
\log_a(x-y)\neq \log_a x-\log_a y
$$

La differenza di logaritmi corrisponde al logaritmo di un quoziente:

$$
\log_a x-\log_a y=\log_a\left(\frac{x}{y}\right)
$$

#### **12.3 Dimenticare l’argomento positivo**

L’espressione:

$$
\log_a(x-4)
$$

richiede:

$$
x-4>0
$$

cioè:

$$
x>4
$$

Non è definita per $x=4$.

---

### **13. Riepilogo**

> ✅ Il logaritmo $\log_a b$ è l’esponente da dare alla base $a$ per ottenere $b$.

Punti essenziali:

- la base deve soddisfare $a>0$ e $a\neq 1$;
- l’argomento deve essere positivo;
- $\log_a 1=0$ e $\log_a a=1$;
- $\log_a(a^x)=x$;
- $a^{\log_a x}=x$ per $x>0$;
- il logaritmo è crescente se $a>1$ e decrescente se $0<a<1$;
- prodotto, quoziente e potenza diventano rispettivamente somma, differenza e fattore moltiplicativo.

La prossima lezione userà queste proprietà per risolvere equazioni e disequazioni logaritmiche.
