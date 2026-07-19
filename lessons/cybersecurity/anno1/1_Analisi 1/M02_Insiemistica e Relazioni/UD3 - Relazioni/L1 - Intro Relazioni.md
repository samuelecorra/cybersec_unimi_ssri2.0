# **M2 UD3 Lezione 1 - Intro Relazioni**

### **1. Introduzione**

Una relazione descrive quando due oggetti sono collegati secondo una certa proprietà. È un concetto più generale di funzione: una funzione associa a ogni elemento un solo valore, mentre una relazione può collegare un elemento a nessuno, uno o più elementi.

Esempi di relazioni:

- “essere minore o uguale” tra numeri reali;
- “avere lo stesso resto nella divisione per $n$” tra interi;
- “essere parallele” tra rette;
- “essere sottoinsieme di” tra insiemi.

> 📌 Le relazioni formalizzano il concetto di confronto o collegamento tra elementi.

---

### **2. Relazione binaria**

Siano $A$ e $B$ due insiemi. Una **relazione binaria** da $A$ a $B$ è un sottoinsieme del prodotto cartesiano:

$$
R\subseteq A\times B
$$

Se:

$$
(a,b)\in R
$$

si dice che $a$ è in relazione con $b$ e si può scrivere:

$$
aRb
$$

Quando $A=B$, si parla di relazione su $A$:

$$
R\subseteq A\times A
$$

---

### **3. Esempio su numeri reali**

La relazione:

$$
x\le y
$$

su $\mathbb{R}$ è l’insieme:

$$
R=\{(x,y)\in\mathbb{R}^2: x\le y\}
$$

Per esempio:

$$
(2,5)\in R
$$

perché:

$$
2\le 5
$$

mentre:

$$
(7,3)\notin R
$$

perché:

$$
7\le 3
$$

è falso.

---

### **4. Dominio, immagine, inversa e composizione**

Per una relazione $R\subseteq A\times B$, il **dominio della relazione** è:

$$
\operatorname{Dom}(R)=\{a\in A:\exists b\in B \text{ tale che } (a,b)\in R\}
$$

L’**immagine della relazione** è:

$$
\operatorname{Im}(R)=\{b\in B:\exists a\in A \text{ tale che } (a,b)\in R\}
$$

La relazione inversa è:

$$
R^{-1}=\{(b,a)\in B\times A:(a,b)\in R\}
$$

Se $R\subseteq A\times B$ e $S\subseteq B\times C$, la composizione $S\circ R$ è:

$$
S\circ R=\{(a,c)\in A\times C:\exists b\in B,\ (a,b)\in R \text{ e } (b,c)\in S\}
$$

Nella scrittura $S\circ R$ si applica prima $R$ e poi $S$, come nella composizione di funzioni.

Esempio: se $R=\{(1,2),(1,3),(4,2)\}$, allora $\operatorname{Dom}(R)=\{1,4\}$ e $\operatorname{Im}(R)=\{2,3\}$.

---

### **5. Relazione e funzione**

Una funzione:

$$
f:A\to B
$$

può essere vista come una relazione particolare:

$$
G_f=\{(a,f(a)):a\in A\}
$$

cioè il suo grafico.

La differenza è che una funzione deve associare a ogni $a\in A$ uno e un solo elemento di $B$.

Una relazione generica non ha questo vincolo.

---

### **6. Proprietà delle relazioni su un insieme**

Quando $R$ è una relazione su $A$, le proprietà più importanti sono:

- riflessività;
- irriflessività;
- simmetria;
- antisimmetria;
- asimmetria;
- transitività.

Queste proprietà permettono di distinguere relazioni d’ordine e relazioni di equivalenza.

---

### **7. Riflessività e irriflessività**

Una relazione $R$ su $A$ è **riflessiva** se ogni elemento è in relazione con se stesso:

$$
\forall a\in A,\ aRa
$$

Esempio:

La relazione $\le$ su $\mathbb{R}$ è riflessiva, perché:

$$
x\le x
$$

per ogni $x\in\mathbb{R}$.

Una relazione è **irriflessiva** se nessun elemento è in relazione con se stesso:

$$
\forall a\in A,\ \neg(aRa)
$$

La relazione $<$ su $\mathbb{R}$ è irriflessiva, perché non vale mai $x<x$.

---

### **8. Simmetria**

Una relazione $R$ è **simmetrica** se:

$$
aRb\Rightarrow bRa
$$

per ogni $a,b\in A$.

Esempio:

La relazione “avere la stessa età” è simmetrica: se $a$ ha la stessa età di $b$, allora $b$ ha la stessa età di $a$.

La relazione $\le$ non è simmetrica, perché:

$$
2\le 3
$$

ma:

$$
3\le 2
$$

è falso.

---

### **9. Antisimmetria e asimmetria**

Una relazione $R$ è **antisimmetrica** se:

$$
aRb \wedge bRa \Rightarrow a=b
$$

Esempio:

La relazione $\le$ su $\mathbb{R}$ è antisimmetrica: se:

$$
a\le b
$$

e:

$$
b\le a
$$

allora:

$$
a=b
$$

Una relazione è **asimmetrica** se:

$$
aRb\Rightarrow \neg(bRa)
$$

per ogni $a,b\in A$.

La relazione $<$ è asimmetrica: se $a<b$, non può valere anche $b<a$.

> ⚠️ Asimmetrica e antisimmetrica non sono sinonimi: $\le$ è antisimmetrica ma non asimmetrica, perché $a\le a$.

---

### **10. Transitività**

Una relazione $R$ è **transitiva** se:

$$
aRb \wedge bRc \Rightarrow aRc
$$

Esempio:

La relazione $\le$ è transitiva: se:

$$
a\le b
$$

e:

$$
b\le c
$$

allora:

$$
a\le c
$$

---

### **11. Totalità**

Una relazione $R$ su $A$ è **totale** o **connessa** se ogni coppia di elementi è confrontabile:

$$
\forall a,b\in A,\ aRb \vee bRa
$$

Per l’ordine usuale $\le$ su $\mathbb{R}$ questa proprietà vale. Per l’inclusione $\subseteq$ su $\mathcal{P}(\{1,2\})$ non vale, perché $\{1\}$ e $\{2\}$ non sono confrontabili.

---

### **12. Esercizi rappresentativi**

1. Siano:

$$
R=\{(1,a),(1,b),(2,b)\}\subseteq\{1,2\}\times\{a,b\}
$$

Determinare dominio, immagine e relazione inversa.

Si ha:

$$
\operatorname{Dom}(R)=\{1,2\}
$$

$$
\operatorname{Im}(R)=\{a,b\}
$$

e:

$$
R^{-1}=\{(a,1),(b,1),(b,2)\}
$$

2. Siano:

$$
R=\{(1,2),(2,3)\},\qquad S=\{(2,a),(3,b)\}
$$

Allora:

$$
S\circ R=\{(1,a),(2,b)\}
$$

perché $1$ passa da $2$ e poi ad $a$, mentre $2$ passa da $3$ e poi a $b$.

3. La relazione $R$ su $\mathbb{Z}$ definita da:

$$
aRb \iff a-b \text{ è multiplo di } 3
$$

è riflessiva, simmetrica e transitiva; non è antisimmetrica, perché $0R3$ e $3R0$, ma $0\neq 3$.

---

### **13. Riepilogo**

> ✅ Una relazione binaria è un sottoinsieme di un prodotto cartesiano e descrive quali coppie di elementi sono collegate.

Punti essenziali:

- $R\subseteq A\times B$ è una relazione da $A$ a $B$;
- $aRb$ significa $(a,b)\in R$;
- dominio, immagine, inversa e composizione si definiscono anche per relazioni non funzionali;
- una funzione è una relazione con vincoli speciali;
- riflessività, irriflessività, simmetria, antisimmetria, asimmetria, transitività e totalità classificano le relazioni;
- ordini ed equivalenze nascono da combinazioni di queste proprietà.
