# **M1 UD3 Lezione 0D - Teoremi del resto e del fattore**

### **1. Dalla divisione alla valutazione**

Quando il divisore è $x-a$, il resto è una costante perché deve avere grado minore di $1$. I teoremi del resto e del fattore collegano questa costante al valore $P(a)$ e trasformano la ricerca di fattori in una ricerca di zeri.

### **2. Teorema del resto**

Sia $P(x)$ un polinomio. Il resto della divisione di $P(x)$ per $x-a$ è $P(a)$.

#### **2.1 Dimostrazione**

Per la divisione euclidea esistono un polinomio $Q(x)$ e una costante $r$ tali che:

$$
P(x)=(x-a)Q(x)+r.
$$

Valutiamo l'identità in $x=a$:

$$
P(a)=(a-a)Q(a)+r=r.
$$

Quindi il resto è $P(a)$.

> 📌 L'ultima casella della tabella di Ruffini è $P(a)$ perché Ruffini esegue proprio la divisione per $x-a$.

### **3. Calcolare un resto senza dividere**

Troviamo il resto della divisione di:

$$
P(x)=2x^5-3x^2+7
$$

per $x+2$. Scriviamo $x+2=x-(-2)$ e calcoliamo:

$$
P(-2)=2(-2)^5-3(-2)^2+7=-64-12+7=-69.
$$

Il resto è $-69$ senza eseguire alcuna divisione.

### **4. Resto assegnato e parametri**

Sia:

$$
P_k(x)=x^3+kx+2.
$$

Determiniamo $k$ affinché la divisione per $x-1$ abbia resto $5$. Per il teorema del resto:

$$
P_k(1)=1+k+2=k+3.
$$

Imponiamo:

$$
k+3=5,
$$

da cui:

$$
k=2.
$$

### **5. Teorema del fattore**

Per ogni polinomio $P(x)$ vale l'equivalenza:

$$
P(a)=0
\iff
(x-a)\mid P(x).
$$

In altre parole, $a$ è uno zero di $P$ se e solo se $x-a$ è un fattore di $P$.

#### **5.1 Prima implicazione**

Supponiamo $P(a)=0$. Per il teorema del resto, il resto della divisione per $x-a$ è $P(a)$, quindi è zero. La divisione è esatta:

$$
P(x)=(x-a)Q(x).
$$

Pertanto $x-a$ divide $P$.

#### **5.2 Seconda implicazione**

Supponiamo che $x-a$ divida $P$. Esiste allora $Q(x)$ tale che:

$$
P(x)=(x-a)Q(x).
$$

Valutando in $x=a$:

$$
P(a)=(a-a)Q(a)=0.
$$

Quindi $a$ è una radice.

### **6. Verificare e utilizzare una radice**

Consideriamo:

$$
P(x)=x^3-4x^2+x+6.
$$

Calcoliamo:

$$
P(2)=8-16+2+6=0.
$$

Per il teorema del fattore, $x-2$ divide $P$. Ruffini produce:

$$
P(x)=(x-2)(x^2-2x-3).
$$

Fattorizzando il quoziente:

$$
P(x)=(x-2)(x-3)(x+1).
$$

### **7. Determinare un parametro con il teorema del fattore**

Per quale valore di $k$ il polinomio:

$$
P_k(x)=x^3+kx^2-4x-4
$$

è divisibile per $x+1$?

Il fattore $x+1=x-(-1)$ richiede:

$$
P_k(-1)=0.
$$

Calcoliamo:

$$
-1+k+4-4=k-1.
$$

Quindi $k=1$. In tal caso:

$$
x^3+x^2-4x-4=(x+1)(x^2-4).
$$

### **8. Costruire un polinomio da zeri assegnati**

Se vogliamo un polinomio di grado $3$ con zero $1$ di molteplicità $2$, zero $-2$ semplice e coefficiente direttivo $3$, dobbiamo scrivere:

$$
P(x)=3(x-1)^2(x+2).
$$

Il coefficiente moltiplicativo è necessario: gli zeri da soli determinano il polinomio solo a meno di una costante non nulla.

### **9. Teorema delle radici razionali**

Sia:

$$
P(x)=a_nx^n+a_{n-1}x^{n-1}+\cdots+a_1x+a_0
$$

un polinomio a coefficienti interi, con $a_n\ne0$. Se la frazione ridotta $\frac{p}{q}$, con $\gcd(p,q)=1$, è una radice razionale, allora:

$$
p\mid a_0
$$

e:

$$
q\mid a_n.
$$

Il teorema produce un elenco finito di **candidati**. Ogni candidato deve essere verificato calcolando $P\left(\frac pq\right)$.

#### **9.1 Dimostrazione**

Supponiamo:

$$
P\left(\frac pq\right)=0.
$$

Moltiplicando per $q^n$:

$$
a_np^n+a_{n-1}p^{n-1}q+\cdots+a_1pq^{n-1}+a_0q^n=0.
$$

Isoliamo il primo termine:

$$
a_np^n=-q\left(a_{n-1}p^{n-1}+\cdots+a_1pq^{n-2}+a_0q^{n-1}\right).
$$

Quindi $q$ divide $a_np^n$. Poiché $p$ e $q$ sono coprimi, $q$ non divide $p^n$ e deve dividere $a_n$.

Isolando invece l'ultimo termine:

$$
a_0q^n=-p\left(a_np^{n-1}+a_{n-1}p^{n-2}q+\cdots+a_1q^{n-1}\right),
$$

si conclude analogamente che $p$ divide $a_0$.

### **10. Caso monico**

Se il polinomio è monico, $a_n=1$. Allora $q$ divide $1$, quindi $q=1$. Ogni eventuale radice razionale è intera e divide il termine noto.

Consideriamo:

$$
P(x)=x^3-2x^2-5x+6.
$$

I candidati sono:

$$
\pm1,\ \pm2,\ \pm3,\ \pm6.
$$

Calcoliamo $P(1)=0$. Dividendo per $x-1$:

$$
P(x)=(x-1)(x^2-x-6).
$$

Quindi:

$$
P(x)=(x-1)(x-3)(x+2).
$$

### **11. Polinomio non monico**

Consideriamo:

$$
P(x)=2x^3+x^2-8x-4.
$$

I numeratori possibili dividono $4$ e i denominatori possibili dividono $2$. Tra i candidati ridotti compaiono:

$$
\pm1,\ \pm2,\ \pm4,\ \pm\frac12.
$$

Verificando si trovano le radici $2$, $-2$ e $-\frac12$. Infatti:

$$
P(x)=(2x+1)(x^2-4)=(2x+1)(x-2)(x+2).
$$

Il fattore associato alla radice $-\frac12$ può essere scritto come $x+\frac12$ oppure, assorbendo il denominatore nel coefficiente, come $2x+1$.

### **12. Assenza di radici razionali**

Per:

$$
P(x)=x^3-x+1
$$

gli unici candidati razionali sono $1$ e $-1$. Tuttavia:

$$
P(1)=1,\qquad P(-1)=1.
$$

Il polinomio non ha radici razionali. Questo non significa che non abbia radici reali: essendo di grado dispari, il suo grafico assume valori di segno opposto per $x$ sufficientemente negativo e positivo, e quindi possiede almeno una radice reale. Tale radice è irrazionale.

> ⚠️ “Candidato” non significa “radice”, e “nessuna radice razionale” non significa “nessuna radice reale”.

### **13. Relazioni di Viète per il secondo grado**

La derivazione completa per $P(x)=ax^2+bx+c$ è collocata nella [lezione sulle equazioni di secondo grado](../UD1%20-%201o%20e%202o%20grado/L4%20-%20Equazioni%20e%20disequazioni%20di%202o%20grado.md). Qui richiamiamo soltanto il risultato, necessario per estenderne la struttura al terzo grado:

$$
x_1+x_2=-\frac ba,
$$

$$
x_1x_2=\frac ca.
$$

Le radici sono sempre contate con la loro molteplicità.

### **14. Viète per un polinomio cubico**

Per un polinomio monico con radici $x_1,x_2,x_3$:

$$
(x-x_1)(x-x_2)(x-x_3)
$$

lo sviluppo è:

$$
x^3-(x_1+x_2+x_3)x^2
+(x_1x_2+x_1x_3+x_2x_3)x
-x_1x_2x_3.
$$

Ponendo:

$$
s_1=x_1+x_2+x_3,
$$

$$
s_2=x_1x_2+x_1x_3+x_2x_3,
$$

$$
s_3=x_1x_2x_3,
$$

si ottiene:

$$
x^3-s_1x^2+s_2x-s_3.
$$

I numeri $s_1,s_2,s_3$ sono le somme simmetriche elementari: non cambiano se si scambia l'ordine delle radici.

### **15. Esercizi con Viète**

Le relazioni tra coefficienti e radici permettono sia di costruire polinomi sia di ricavare informazioni senza risolvere direttamente l'equazione.

#### **15.1 Costruire un polinomio**

Il polinomio monico con radici $2,-1,4$ è:

$$
(x-2)(x+1)(x-4).
$$

La somma delle radici è $5$, la somma dei prodotti a coppie è $2$ e il prodotto è $-8$. Quindi:

$$
P(x)=x^3-5x^2+2x+8.
$$

#### **15.2 Informazioni senza risolvere**

Se due radici soddisfano:

$$
x_1+x_2=7,\qquad x_1x_2=10,
$$

sono radici del polinomio:

$$
x^2-7x+10.
$$

Fattorizzando si ritrovano $2$ e $5$.

#### **15.3 Determinare una relazione possibile**

Supponiamo $x_1x_2=6$ e $x_1^2+x_2^2=13$. Poiché:

$$
x_1^2+x_2^2=(x_1+x_2)^2-2x_1x_2,
$$

si ha:

$$
13=(x_1+x_2)^2-12,
$$

quindi $x_1+x_2=\pm5$. I possibili polinomi monici sono:

$$
x^2-5x+6
$$

e:

$$
x^2+5x+6.
$$

#### **15.4 Determinare un parametro**

Le radici di:

$$
P_k(x)=x^2-(k+1)x+k
$$

hanno somma $k+1$ e prodotto $k$. Se si richiede che la somma valga $5$, allora:

$$
k+1=5,
$$

quindi $k=4$. Il prodotto delle radici vale allora $4$ e il polinomio diventa:

$$
x^2-5x+4=(x-1)(x-4).
$$

Le radici $1$ e $4$ verificano sia la somma sia il prodotto prescritti.

### **16. Errori frequenti**

- Usare $a$ invece di $-a$ per il fattore $x-a$.
- Confondere $P(a)=0$ con il fatto che $a$ sia un fattore: il fattore è $x-a$.
- Provare solo i divisori positivi del termine noto.
- Chiamare “Ruffini” il teorema delle radici razionali.
- Credere che tutti i candidati siano radici.
- Perdere il coefficiente direttivo nella ricostruzione da radici.
- In Viète dimenticare il segno meno nella somma delle radici.

### **17. Riepilogo**

> ✅ Il resto della divisione per $x-a$ è $P(a)$; il resto è nullo esattamente quando $x-a$ è un fattore.

Il teorema delle radici razionali restringe la ricerca a un elenco finito, mentre le relazioni di Viète permettono di passare dai coefficienti alle somme simmetriche delle radici e viceversa.
