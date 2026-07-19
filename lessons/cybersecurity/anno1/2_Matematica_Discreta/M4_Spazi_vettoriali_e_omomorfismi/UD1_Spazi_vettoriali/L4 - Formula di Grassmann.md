# **M4 UD1 Lezione 4 - Formula di Grassmann**

### **1. Intersezione, unione e somma**

Siano $S,T$ sottospazi di uno spazio vettoriale $V$.

L'intersezione

$$
S\cap T=\{v\in V:v\in S\text{ e }v\in T\}
$$

è sempre un sottospazio: contiene $0$ e ogni combinazione lineare di due suoi elementi appartiene sia a $S$ sia a $T$.

L'unione $S\cup T$, invece, è un sottospazio se e solo se uno dei due sottospazi è contenuto nell'altro. Se esistessero $s\in S\setminus T$ e $t\in T\setminus S$, la chiusura della presunta unione imporrebbe $s+t\in S\cup T$. Se $s+t\in S$, allora $t=(s+t)-s\in S$, assurdo; analogamente se $s+t\in T$.

La **somma** è

$$
S+T=\{s+t:s\in S,\ t\in T\}.
$$

È un sottospazio e coincide con il più piccolo sottospazio di $V$ che contiene $S\cup T$.

---

### **2. Formula di Grassmann**

> 📌 Se $S$ e $T$ hanno dimensione finita, allora
> $$
> \dim(S+T)=\dim S+\dim T-\dim(S\cap T).
> $$

#### **2.1. Dimostrazione**

Sia

$$
B_0=(u_1,\ldots,u_r)
$$

una base di $S\cap T$. Per il teorema di completamento estendiamola a basi

$$
B_S=(u_1,\ldots,u_r,s_{r+1},\ldots,s_p)
$$

di $S$ e

$$
B_T=(u_1,\ldots,u_r,t_{r+1},\ldots,t_q)
$$

di $T$.

Il sistema

$$
B=(u_1,\ldots,u_r,s_{r+1},\ldots,s_p,t_{r+1},\ldots,t_q)
$$

genera $S+T$. Verifichiamo l'indipendenza. Se

$$
\sum_{i=1}^r\alpha_i u_i+
\sum_{i=r+1}^p\beta_i s_i+
\sum_{j=r+1}^q\gamma_j t_j=0,
$$

allora

$$
\sum_{i=1}^r\alpha_i u_i+
\sum_{i=r+1}^p\beta_i s_i
=-\sum_{j=r+1}^q\gamma_jt_j.
$$

Il membro sinistro appartiene a $S$, quello destro a $T$; il loro valore comune appartiene quindi a $S\cap T$ ed è combinazione degli $u_i$. L'indipendenza di $B_S$ forza tutti i $\beta_i$ a zero; quella di $B_T$ forza tutti i $\gamma_j$ a zero; infine l'indipendenza di $B_0$ forza tutti gli $\alpha_i$ a zero.

Dunque $B$ è una base e

$$
\dim(S+T)=r+(p-r)+(q-r)=p+q-r.
$$

---

### **3. Somma diretta e complementi**

La somma è **diretta**, scritta

$$
S\oplus T,
$$

se

$$
S\cap T=\{0\}.
$$

In questo caso ogni $v\in S+T$ ha un'unica decomposizione $v=s+t$. Infatti, se $s+t=s'+t'$, allora $s-s'=t'-t\in S\cap T$, quindi entrambi i membri sono zero.

Se $V=S\oplus T$, $T$ è un **complemento** di $S$ in $V$. La decomposizione unica definisce le proiezioni

$$
\pi_S(s+t)=s,\qquad \pi_T(s+t)=t.
$$

---

### **4. Esercizio 1 in $\mathbb R^3$**

Siano

$$
S=\{(a,b,c):a+2b-c=0\},
$$

$$
T=\operatorname{span}\{(1,0,-1),(2,1,0)\}.
$$

Da $c=a+2b$ segue

$$
S=\operatorname{span}\{(1,0,1),(0,1,2)\},\qquad \dim S=2.
$$

I generatori di $T$ sono indipendenti, quindi $\dim T=2$. Un vettore di $T$ è

$$
h(1,0,-1)+k(2,1,0)=(h+2k,k,-h).
$$

La condizione di appartenenza a $S$ è

$$
(h+2k)+2k-(-h)=2h+4k=0,
$$

cioè $h=-2k$. Quindi

$$
S\cap T=\operatorname{span}\{(0,1,2)\}.
$$

Per Grassmann,

$$
\dim(S+T)=2+2-1=3,
$$

perciò $S+T=\mathbb R^3$. Una base è

$$
\bigl((1,0,1),(0,1,2),(1,0,-1)\bigr).
$$

---

### **5. Esercizio 2 in $\mathbb R_3[x]$**

Siano

$$
S=\operatorname{span}\{x^2+1,x^3-x\}
$$

e

$$
T=\{ax^3+bx^2+cx+d:a-b+c=0,\ 2a-d=0\}.
$$

Ponendo $b=a+c$ e $d=2a$,

$$
T=\operatorname{span}\{x^3+x^2+2,x^2+x\},
$$

quindi $\dim S=\dim T=2$.

Un elemento dell'intersezione scritto in $S$ è

$$
h(x^3-x)+k(x^2+1).
$$

I suoi coefficienti sono $a=h,b=k,c=-h,d=k$. I vincoli di $T$ danno

$$
h-k-h=-k=0,\qquad 2h-k=0,
$$

da cui $h=k=0$. Pertanto $S\cap T=\{0\}$ e

$$
\dim(S+T)=4=\dim\mathbb R_3[x].
$$

Quindi $\mathbb R_3[x]=S\oplus T$.

---

### **6. Esercizio 3 e complemento**

Siano

$$
S=\operatorname{span}\{(1,1,1),(0,1,0)\},
$$

$$
T=\operatorname{span}\{(1,0,-1),(2,1,0)\}.
$$

Imponendo

$$
x(1,1,1)+y(0,1,0)=h(1,0,-1)+k(2,1,0)
$$

si ricavano $h=-x$, $k=x$ e $y=0$. Dunque

$$
S\cap T=\operatorname{span}\{(1,1,1)\}.
$$

La formula di Grassmann dà ancora $S+T=\mathbb R^3$. Un complemento dell'intersezione è, per esempio,

$$
C=\operatorname{span}\{(1,0,0),(0,1,0)\},
$$

poiché $C\cap\operatorname{span}\{(1,1,1)\}=\{0\}$ e le dimensioni sommano a $3$.

---

> ✅ Intersezione e somma sono sempre sottospazi; l'unione lo è soltanto nel caso di inclusione. La formula di Grassmann misura esattamente la sovrapposizione tra due sottospazi.
