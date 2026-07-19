# **M4 UD1 Lezione 2 - Sistemi generatori**

### **1. Obiettivi e prerequisiti**

Studiamo combinazioni lineari, sistemi generatori e indipendenza lineare. Supponiamo note le definizioni di spazio e sottospazio vettoriale.

---

### **2. Combinazioni lineari e span**

Siano $v_1,\ldots,v_n\in V$. Una **combinazione lineare** è un vettore

$$
\lambda_1v_1+\cdots+\lambda_nv_n,
\qquad \lambda_i\in K.
$$

Gli scalari $\lambda_i$ sono le **componenti** del vettore rispetto al sistema ordinato $(v_1,\ldots,v_n)$.

L'insieme di tutte le combinazioni lineari è

$$
\operatorname{span}(v_1,\ldots,v_n)
=\left\{\sum_{i=1}^n\lambda_iv_i:\lambda_i\in K\right\}.
$$

Esso è un sottospazio ed è il più piccolo sottospazio contenente tutti i $v_i$.

Il sistema $S=\{v_1,\ldots,v_n\}$ è un **sistema di generatori** di $V$ se

$$
V=\operatorname{span}(v_1,\ldots,v_n).
$$

Uno spazio che ammette un sistema finito di generatori si dice **finitamente generato**.

> ⚠️ Le componenti rispetto a un sistema generatore possono non essere uniche. Anche il vettore nullo può avere rappresentazioni non banali.

---

### **3. Tre sistemi in $\mathbb R^2$**

#### **3.1. Un sistema generatore con componenti uniche**

Sia

$$
S_1=\{(1,1),(1,2)\}.
$$

Per rappresentare $(x,y)$ cerchiamo $a,b$ tali che

$$
a(1,1)+b(1,2)=(x,y),
$$

cioè

$$
\begin{cases}
a+b=x,\\
a+2b=y.
\end{cases}
$$

La soluzione è $b=y-x$ e $a=2x-y$, quindi $S_1$ genera $\mathbb R^2$.

Per $(4,-1)$:

$$
\begin{cases}
a+b=4,\\
a+2b=-1,
\end{cases}
\qquad b=-5,\quad a=9,
$$

e dunque

$$
(4,-1)=9(1,1)-5(1,2).
$$

#### **3.2. Un sistema che non genera**

$$
S_2=\{(1,1),(2,2)\}
$$

genera soltanto la retta $\{(t,t):t\in\mathbb R\}$, perché $(2,2)=2(1,1)$.

#### **3.3. Un sistema ridondante**

$$
S_3=\{(1,1),(2,0),(0,1)\}
$$

genera $\mathbb R^2$, ma le componenti non sono uniche. Per $(4,-1)$:

$$
a(1,1)+b(2,0)+c(0,1)=(4,-1)
$$

equivale a

$$
a+2b=4,\qquad a+c=-1.
$$

Scelto liberamente $a=t$,

$$
b=\frac{4-t}{2},\qquad c=-1-t.
$$

Ogni $t\in\mathbb R$ dà una rappresentazione diversa.

---

### **4. Indipendenza lineare**

I vettori $v_1,\ldots,v_n$ sono **linearmente indipendenti** se

$$
\lambda_1v_1+\cdots+\lambda_nv_n=0_V
$$

implica

$$
\lambda_1=\cdots=\lambda_n=0_K.
$$

Se esiste una relazione con almeno un coefficiente non nullo, il sistema è **linearmente dipendente**.

#### **4.1. Indipendenza e unicità delle componenti**

Se $v_1,\ldots,v_n$ sono indipendenti e

$$
v=\sum_{i=1}^n\lambda_iv_i=\sum_{i=1}^n\mu_iv_i,
$$

sottraendo le due espressioni si ottiene

$$
\sum_{i=1}^n(\lambda_i-\mu_i)v_i=0_V.
$$

L'indipendenza forza $\lambda_i-\mu_i=0$, dunque $\lambda_i=\mu_i$ per ogni $i$.

Viceversa, se il sistema è dipendente, una relazione non banale dello zero permette di modificare le componenti di qualunque rappresentazione e produce non unicità.

> 📌 Un sistema generatore dà esistenza delle componenti; l'indipendenza lineare dà unicità.

---

### **5. Errori comuni**

- Verificare soltanto che i vettori siano diversi: vettori distinti possono essere dipendenti.
- Dimenticare che il sistema è ordinato quando si parla di componenti.
- Confondere $\operatorname{span}(S)$ con $S$: lo span contiene tutte le combinazioni lineari.
- Concludere che un sistema con più vettori “genera di più”: i vettori aggiunti possono essere ridondanti.

---

> ✅ Lo span misura ciò che un sistema riesce a generare. L'indipendenza lineare esclude ridondanze e rende uniche le componenti.
