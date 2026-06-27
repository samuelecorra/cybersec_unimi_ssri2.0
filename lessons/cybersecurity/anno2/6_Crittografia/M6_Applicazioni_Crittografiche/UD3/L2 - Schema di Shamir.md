## **Lezione 2: Schema di Shamir**

### **1. Introduzione**

Nella lezione precedente abbiamo visto uno schema di condivisione del segreto di tipo:

$$
(n,n)
$$

In quello schema servivano tutti gli $n$ partecipanti per ricostruire il segreto. Se anche una sola share mancava, il segreto rimaneva completamente nascosto.

In questa lezione si passa a uno schema più generale, chiamato **schema di Shamir**, che permette di costruire schemi a soglia:

$$
(k,n)
$$

In uno schema $(k,n)$:

- ci sono $n$ partecipanti totali;
- bastano $k$ partecipanti per ricostruire il segreto;
- meno di $k$ partecipanti non devono ottenere alcuna informazione sul segreto.

Lo schema di Shamir è basato su un fatto matematico fondamentale: un polinomio di grado $k-1$ è determinato in modo univoco da $k$ suoi punti distinti.

> 📌 Shamir trasforma il problema della condivisione del segreto in un problema di interpolazione polinomiale: le share sono punti di un polinomio, e il segreto è il termine noto del polinomio.

---

### **2. Scenario dello schema $(k,n)$**

Come nella lezione precedente, esiste un **dealer**, cioè l'entità che possiede il segreto e deve distribuirlo.

Il dealer possiede un segreto:

$$
S
$$

e vuole distribuirlo tra:

$$
n
$$

partecipanti:

$$
P_1,P_2,\dots,P_n
$$

Il valore $k$ indica la soglia minima di partecipanti necessaria per la ricostruzione.

Lo schema deve garantire due proprietà:

1. qualunque insieme di $k$ partecipanti può ricostruire $S$;
2. qualunque insieme di $k-1$ partecipanti non può ricostruire $S$ e non ottiene alcuna informazione su $S$.

La seconda proprietà è più forte della semplice impossibilità di calcolo: significa che, anche osservando $k-1$ share, ogni valore possibile del segreto resta compatibile.

---

### **3. Scelta del campo e dei parametri**

Lo schema lavora in un campo finito:

$$
\mathbb{Z}_p
$$

dove $p$ è un numero primo.

Il primo $p$ deve essere scelto abbastanza grande da contenere:

- il segreto $S$;
- gli indici dei partecipanti $1,2,\dots,n$;
- i coefficienti casuali del polinomio.

In pratica si richiede:

$$
p>\max(S,n)
$$

oppure, più in generale, si codifica il segreto come elemento di $\mathbb{Z}_p$ scegliendo $p$ sufficientemente grande.

Lavorare in $\mathbb{Z}_p$ è importante perché, essendo $p$ primo, $\mathbb{Z}_p$ è un campo: ogni elemento non nullo ha inverso. Questo permette di risolvere sistemi lineari e applicare l'interpolazione di Lagrange.

> ⚠️ Le divisioni che compaiono nell'interpolazione di Lagrange sono divisioni modulari: dividere per $a$ significa moltiplicare per l'inverso di $a$ modulo $p$.

---

### **4. Costruzione del polinomio segreto**

Il dealer sceglie casualmente:

$$
k-1
$$

elementi di $\mathbb{Z}_p$:

$$
a_1,a_2,\dots,a_{k-1}
$$

Il segreto:

$$
S\in\mathbb{Z}_p
$$

viene usato come termine noto di un polinomio.

Il dealer costruisce quindi:

$$
f(x)=S+a_1x+a_2x^2+\dots+a_{k-1}x^{k-1}\pmod p
$$

Questo è un polinomio di grado al più:

$$
k-1
$$

Il punto essenziale è che il termine noto è:

$$
f(0)=S
$$

Quindi ricostruire il segreto equivale a ricostruire il polinomio e valutarlo in:

$$
x=0
$$

> 📌 Il segreto non viene distribuito direttamente: viene nascosto come valore del polinomio nel punto $0$.

---

### **5. Calcolo delle share**

Per ciascun partecipante $P_i$, con:

$$
i=1,\dots,n
$$

il dealer calcola:

$$
y_i=f(i)\pmod p
$$

La share assegnata al partecipante $P_i$ è la coppia:

$$
(i,y_i)
$$

Il valore $i$ è l'ascissa del punto, mentre $y_i$ è l'ordinata calcolata dal polinomio.

Il dealer distribuisce poi ogni share in modo sicuro:

- $P_1$ riceve solo $(1,y_1)$;
- $P_2$ riceve solo $(2,y_2)$;
- in generale $P_i$ riceve solo $(i,y_i)$.

Gli altri partecipanti non devono vedere le share altrui, almeno fino alla fase volontaria di ricostruzione.

---

### **6. Esempio numerico: schema $(3,5)$**

Consideriamo uno schema:

$$
(3,5)
$$

Ci sono cinque partecipanti, ma bastano tre share per ricostruire il segreto.

Scegliamo:

$$
p=19
$$

e supponiamo di voler condividere il segreto:

$$
S=12
$$

Poiché la soglia è:

$$
k=3
$$

il dealer deve costruire un polinomio di grado:

$$
k-1=2
$$

Sceglie quindi due coefficienti casuali in $\mathbb{Z}_{19}$:

$$
a_1=11
$$

$$
a_2=2
$$

Il polinomio diventa:

$$
f(x)=12+11x+2x^2\pmod {19}
$$

Il dealer calcola ora le share valutando il polinomio nei punti:

$$
1,2,3,4,5
$$

#### **6.1. Calcolo di $f(1)$**

$$
f(1)=12+11\cdot1+2\cdot1^2=25
$$

Poiché:

$$
25\equiv 6\pmod {19}
$$

la prima share è:

$$
(1,6)
$$

#### **6.2. Calcolo di $f(2)$**

$$
f(2)=12+11\cdot2+2\cdot2^2
$$

$$
f(2)=12+22+8=42
$$

Poiché:

$$
42\equiv 4\pmod {19}
$$

la seconda share è:

$$
(2,4)
$$

#### **6.3. Calcolo di $f(3)$**

$$
f(3)=12+11\cdot3+2\cdot3^2
$$

$$
f(3)=12+33+18=63
$$

Poiché:

$$
63\equiv 6\pmod {19}
$$

la terza share è:

$$
(3,6)
$$

#### **6.4. Calcolo di $f(4)$**

$$
f(4)=12+11\cdot4+2\cdot4^2
$$

$$
f(4)=12+44+32=88
$$

Poiché:

$$
88\equiv 12\pmod {19}
$$

la quarta share è:

$$
(4,12)
$$

#### **6.5. Calcolo di $f(5)$**

$$
f(5)=12+11\cdot5+2\cdot5^2
$$

$$
f(5)=12+55+50=117
$$

Poiché:

$$
117\equiv 3\pmod {19}
$$

la quinta share è:

$$
(5,3)
$$

Quindi il dealer distribuisce:

- $P_1$ riceve $(1,6)$;
- $P_2$ riceve $(2,4)$;
- $P_3$ riceve $(3,6)$;
- $P_4$ riceve $(4,12)$;
- $P_5$ riceve $(5,3)$.

> ✅ Le share sono punti del polinomio $f(x)=12+11x+2x^2 \bmod 19$. Il segreto è il valore $f(0)=12$.

---

### **7. Ricostruzione tramite sistema lineare**

Quando almeno $k$ partecipanti collaborano, essi possiedono $k$ punti distinti del polinomio.

Nel nostro esempio, bastano tre partecipanti.

Supponiamo che collaborino:

$$
P_1,\ P_2,\ P_4
$$

Le loro share sono:

$$
(1,6),\ (2,4),\ (4,12)
$$

Essi sanno che il polinomio ha forma:

$$
f(x)=S+a_1x+a_2x^2\pmod {19}
$$

Sostituendo i tre punti, ottengono il sistema:

$$
\begin{cases}
6 \equiv S+a_1+a_2 \pmod {19}\\
4 \equiv S+2a_1+4a_2 \pmod {19}\\
12 \equiv S+4a_1+16a_2 \pmod {19}
\end{cases}
$$

Le incognite sono:

$$
S,\ a_1,\ a_2
$$

Ci sono tre equazioni e tre incognite, quindi il sistema è determinato.

#### **7.1. Risoluzione esplicita dell'esempio**

Sottraiamo la prima equazione dalla seconda:

$$
(S+2a_1+4a_2)-(S+a_1+a_2)\equiv 4-6 \pmod {19}
$$

Quindi:

$$
a_1+3a_2\equiv -2 \pmod {19}
$$

cioè:

$$
a_1+3a_2\equiv 17 \pmod {19}
$$

Sottraiamo la prima equazione dalla terza:

$$
(S+4a_1+16a_2)-(S+a_1+a_2)\equiv 12-6 \pmod {19}
$$

Quindi:

$$
3a_1+15a_2\equiv 6 \pmod {19}
$$

Dalla prima relazione:

$$
a_1\equiv 17-3a_2\pmod {19}
$$

Sostituendo nella seconda:

$$
3(17-3a_2)+15a_2\equiv 6 \pmod {19}
$$

cioè:

$$
51-9a_2+15a_2\equiv 6 \pmod {19}
$$

$$
51+6a_2\equiv 6 \pmod {19}
$$

Poiché:

$$
51\equiv 13\pmod {19}
$$

si ha:

$$
13+6a_2\equiv 6 \pmod {19}
$$

quindi:

$$
6a_2\equiv -7\equiv 12 \pmod {19}
$$

L'inverso di $6$ modulo $19$ è $16$, perché:

$$
6\cdot16=96\equiv 1\pmod {19}
$$

Allora:

$$
a_2\equiv 12\cdot16\equiv 2\pmod {19}
$$

Ora:

$$
a_1\equiv 17-3\cdot2=11\pmod {19}
$$

Infine dalla prima equazione:

$$
6\equiv S+11+2\pmod {19}
$$

quindi:

$$
S\equiv 6-13\equiv -7\equiv 12\pmod {19}
$$

Il segreto ricostruito è:

$$
S=12
$$

---

### **8. Forma matriciale e matrice di Vandermonde**

La ricostruzione può essere descritta anche in forma matriciale.

Con i punti:

$$
(i_1,y_{i_1}),\dots,(i_k,y_{i_k})
$$

il sistema è:

$$
\begin{bmatrix}
1 & i_1 & i_1^2 & \cdots & i_1^{k-1}\\
1 & i_2 & i_2^2 & \cdots & i_2^{k-1}\\
\vdots & \vdots & \vdots & \ddots & \vdots\\
1 & i_k & i_k^2 & \cdots & i_k^{k-1}
\end{bmatrix}
\begin{bmatrix}
S\\
a_1\\
\vdots\\
a_{k-1}
\end{bmatrix}
\equiv
\begin{bmatrix}
y_{i_1}\\
y_{i_2}\\
\vdots\\
y_{i_k}
\end{bmatrix}
\pmod p
$$

La matrice dei coefficienti è una **matrice di Vandermonde**.

Il suo determinante è:

$$
\det(V)=\prod_{1\le t<r\le k}(i_r-i_t)\pmod p
$$

Gli indici:

$$
i_1,\dots,i_k
$$

sono tutti distinti, perché appartengono a partecipanti diversi.

Inoltre si sceglie:

$$
p>n
$$

quindi due indici distinti tra $1$ e $n$ restano distinti anche modulo $p$.

Perciò:

$$
i_r-i_t\not\equiv 0\pmod p
$$

per ogni coppia $r\ne t$.

Ogni fattore del prodotto è diverso da zero in $\mathbb{Z}_p$; poiché $\mathbb{Z}_p$ è un campo, il prodotto di elementi non nulli è ancora non nullo.

Quindi:

$$
\det(V)\not\equiv 0\pmod p
$$

La matrice è invertibile e il sistema ha un'unica soluzione.

> 📌 Questa è la ragione matematica per cui qualunque gruppo di $k$ partecipanti può ricostruire il segreto: i loro $k$ punti distinti determinano un unico polinomio di grado al più $k-1$.

---

### **9. Generalizzazione a qualunque gruppo di $k$ partecipanti**

Il ragionamento non dipende dalla scelta specifica di:

$$
P_1,P_2,P_4
$$

Vale per qualunque insieme di $k$ partecipanti:

$$
P_{i_1},P_{i_2},\dots,P_{i_k}
$$

Essi possiedono le share:

$$
(i_1,y_{i_1}),\dots,(i_k,y_{i_k})
$$

e quindi possono scrivere un sistema lineare con:

- $k$ equazioni;
- $k$ incognite:

$$
S,a_1,\dots,a_{k-1}
$$

La matrice associata è sempre una Vandermonde costruita su ascisse distinte. Il suo determinante è sempre non nullo modulo $p$, quindi il sistema ammette una sola soluzione.

Questa soluzione contiene anche il segreto $S$.

---

### **10. Ricostruzione tramite interpolazione di Lagrange**

Il sistema lineare non è l'unico modo per ricostruire il segreto.

Si può usare anche la **formula di interpolazione di Lagrange**.

L'idea generale è nota: per due punti passa una sola retta; più in generale, per $k$ punti distinti passa un solo polinomio di grado al più $k-1$.

Nel caso dello schema di Shamir, ogni share è un punto del polinomio:

$$
f(x)
$$

Poiché $f(x)$ ha grado al più $k-1$, un gruppo di $k$ partecipanti ha abbastanza punti per ricostruire tutto il polinomio.

Se i punti disponibili sono:

$$
(i_1,y_{i_1}),\dots,(i_k,y_{i_k})
$$

allora il polinomio è:

$$
f(x)=\sum_{j=1}^{k} y_{i_j} L_j(x)\pmod p
$$

dove:

$$
L_j(x)=\prod_{\substack{t=1\\t\ne j}}^{k}\frac{x-i_t}{i_j-i_t}\pmod p
$$

I polinomi $L_j(x)$ sono costruiti in modo che:

$$
L_j(i_j)=1
$$

e:

$$
L_j(i_t)=0
$$

per ogni $t\ne j$.

Così, quando si valuta $f(x)$ in uno dei punti noti, sopravvive solo il termine corrispondente a quella share.

---

### **11. Calcolo diretto del segreto con Lagrange**

Per ricostruire il segreto non è necessario conoscere tutto il polinomio.

Infatti il segreto è:

$$
S=f(0)
$$

Quindi si può sostituire direttamente:

$$
x=0
$$

nella formula di Lagrange.

Si ottiene:

$$
S=\sum_{j=1}^{k} y_{i_j}\lambda_j\pmod p
$$

dove:

$$
\lambda_j=\prod_{\substack{t=1\\t\ne j}}^{k}\frac{-i_t}{i_j-i_t}\pmod p
$$

I coefficienti:

$$
\lambda_j
$$

dipendono solo dagli indici dei partecipanti, non dai valori delle share. Per questo, se si sa in anticipo quali partecipanti collaboreranno, tali coefficienti possono anche essere precomputati per velocizzare la ricostruzione.

> 💡 Lagrange permette di ricostruire direttamente $f(0)$: non serve calcolare esplicitamente tutti i coefficienti del polinomio.

---

### **12. Esempio di Lagrange con $P_1$, $P_2$, $P_4$**

Usiamo di nuovo le share:

$$
(1,6),\ (2,4),\ (4,12)
$$

Vogliamo calcolare:

$$
S=f(0)
$$

I coefficienti di Lagrange in $0$ sono:

$$
\lambda_1=\frac{(-2)(-4)}{(1-2)(1-4)}\pmod {19}
$$

Il numeratore è:

$$
8
$$

Il denominatore è:

$$
(-1)(-3)=3
$$

L'inverso di $3$ modulo $19$ è $13$, quindi:

$$
\lambda_1=8\cdot13\equiv 9\pmod {19}
$$

Per il secondo punto:

$$
\lambda_2=\frac{(-1)(-4)}{(2-1)(2-4)}\pmod {19}
$$

Il numeratore è:

$$
4
$$

Il denominatore è:

$$
1\cdot(-2)=-2\equiv 17\pmod {19}
$$

L'inverso di $17$ modulo $19$ è $9$, quindi:

$$
\lambda_2=4\cdot9\equiv 17\pmod {19}
$$

Per il quarto punto:

$$
\lambda_4=\frac{(-1)(-2)}{(4-1)(4-2)}\pmod {19}
$$

Il numeratore è:

$$
2
$$

Il denominatore è:

$$
3\cdot2=6
$$

L'inverso di $6$ modulo $19$ è $16$, quindi:

$$
\lambda_4=2\cdot16\equiv 13\pmod {19}
$$

Ora:

$$
S=6\lambda_1+4\lambda_2+12\lambda_4\pmod {19}
$$

Sostituendo:

$$
S=6\cdot9+4\cdot17+12\cdot13\pmod {19}
$$

$$
S=54+68+156=278
$$

Poiché:

$$
278\equiv 12\pmod {19}
$$

si ricostruisce:

$$
S=12
$$

---

### **13. Cosa succede con $k-1$ partecipanti**

Consideriamo ora il caso in cui collaborino solo:

$$
k-1
$$

partecipanti.

Essi possiedono:

- $k-1$ share;
- quindi $k-1$ equazioni.

Ma le incognite del polinomio sono:

$$
S,a_1,\dots,a_{k-1}
$$

cioè $k$ incognite.

Il sistema ha quindi:

$$
k-1
$$

equazioni in:

$$
k
$$

incognite.

Manca un'equazione per determinare univocamente il polinomio.

In termini geometrici, $k-1$ punti non bastano a determinare un polinomio di grado $k-1$.

---

### **14. Esempio con solo $P_1$ e $P_2$**

Nel nostro schema $(3,5)$ servono:

$$
k=3
$$

partecipanti.

Supponiamo però che collaborino solo:

$$
P_1
$$

e:

$$
P_2
$$

Le share sono:

$$
(1,6)
$$

e:

$$
(2,4)
$$

Essi possono scrivere:

$$
\begin{cases}
6\equiv S+a_1+a_2 \pmod {19}\\
4\equiv S+2a_1+4a_2 \pmod {19}
\end{cases}
$$

Ci sono due equazioni ma tre incognite:

$$
S,\ a_1,\ a_2
$$

Quindi il sistema non determina il segreto.

Vediamo perché ogni valore di $S$ è possibile.

Dalla prima equazione:

$$
a_1+a_2\equiv 6-S\pmod {19}
$$

Dalla seconda:

$$
2a_1+4a_2\equiv 4-S\pmod {19}
$$

Moltiplichiamo la prima per $2$:

$$
2a_1+2a_2\equiv 12-2S\pmod {19}
$$

Sottraendola dalla seconda:

$$
2a_2\equiv (4-S)-(12-2S)\pmod {19}
$$

quindi:

$$
2a_2\equiv S-8\pmod {19}
$$

Poiché l'inverso di $2$ modulo $19$ è $10$, per qualunque valore scelto di $S$ esiste:

$$
a_2\equiv 10(S-8)\pmod {19}
$$

e poi:

$$
a_1\equiv 6-S-a_2\pmod {19}
$$

Quindi per ogni possibile:

$$
S\in\mathbb{Z}_{19}
$$

esistono coefficienti $a_1$ e $a_2$ compatibili con le due share osservate.

Di conseguenza, $P_1$ e $P_2$ non possono sapere se il segreto sia $0$, $1$, $2$, oppure qualunque altro elemento di $\mathbb{Z}_{19}$.

> 📌 Con $k-1$ share, il segreto resta completamente indeterminato: ogni valore del campo è ancora possibile.

---

### **15. Sicurezza perfetta dello schema**

La sicurezza dello schema di Shamir deriva dalla casualità dei coefficienti:

$$
a_1,\dots,a_{k-1}
$$

Per un gruppo con meno di $k$ share, esiste sempre un numero sufficiente di coefficienti liberi da adattare a qualunque valore del segreto.

Quindi le share osservate non restringono lo spazio dei possibili segreti.

Formalmente, per ogni insieme non autorizzato di meno di $k$ partecipanti:

$$
\Pr(S\mid \text{share osservate})=\Pr(S)
$$

Questo significa che lo schema offre **sicurezza perfetta** rispetto ai gruppi non autorizzati: l'incertezza sul segreto non diminuisce.

> ⚠️ Questa proprietà presuppone che i coefficienti casuali siano scelti uniformemente in $\mathbb{Z}_p$ e che le share siano distribuite tramite canali sicuri.

---

### **16. Sintesi finale**

In questa lezione abbiamo visto lo **schema di Shamir** per la condivisione del segreto.

I punti principali sono:

- lo schema generalizza il caso $(n,n)$ a uno schema a soglia $(k,n)$;
- il dealer sceglie un primo $p$ e lavora in $\mathbb{Z}_p$;
- il segreto $S$ viene inserito come termine noto di un polinomio;
- il dealer sceglie casualmente $k-1$ coefficienti:

$$
a_1,\dots,a_{k-1}
$$

- costruisce il polinomio:

$$
f(x)=S+a_1x+\dots+a_{k-1}x^{k-1}\pmod p
$$

- la share del partecipante $P_i$ è il punto:

$$
(i,f(i))
$$

- qualunque gruppo di $k$ partecipanti possiede $k$ punti distinti;
- questi $k$ punti determinano un unico polinomio di grado al più $k-1$;
- la ricostruzione può avvenire risolvendo un sistema lineare;
- la matrice del sistema è una Vandermonde con determinante non nullo;
- in alternativa, si può usare l'interpolazione di Lagrange;
- per ricostruire solo il segreto basta calcolare $f(0)$;
- con meno di $k$ share si hanno meno equazioni delle incognite;
- ogni valore di $S$ resta possibile ed equiprobabile.

> ✅ Lo schema di Shamir è potente perché combina flessibilità e sicurezza: qualunque gruppo di almeno $k$ partecipanti ricostruisce il segreto, mentre qualunque gruppo più piccolo non apprende nulla.
