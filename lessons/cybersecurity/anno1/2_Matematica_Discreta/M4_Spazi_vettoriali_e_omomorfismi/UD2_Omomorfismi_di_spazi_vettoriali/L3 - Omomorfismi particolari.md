# **M4 UD2 Lezione 3 - Omomorfismi particolari**

### **1. Iniettività, suriettività e isomorfismi**

Per una mappa lineare $f:V\to W$:

- $f$ è **iniettiva** se $f(u)=f(v)$ implica $u=v$;
- $f$ è **suriettiva** se $\operatorname{Im}f=W$;
- $f$ è **biettiva** se è iniettiva e suriettiva;
- una mappa lineare biettiva è un **isomorfismo**.

La linearità rende l'iniettività equivalente a

$$
\ker f=\{0_V\}.
$$

Infatti $f(u)=f(v)$ equivale a $f(u-v)=0$.

Se $V$ e $W$ hanno dimensione finita:

$$
f\text{ iniettiva}\Longrightarrow\dim V\leq\dim W,
$$

$$
f\text{ suriettiva}\Longrightarrow\dim V\geq\dim W.
$$

Se $\dim V=\dim W$, iniettività, suriettività e biettività sono equivalenti.

Due spazi sono **isomorfi** se esiste un isomorfismo tra essi. Ogni spazio $V$ di dimensione $n$ sul campo $K$ è isomorfo a $K^n$: scelta una base $B$, l'applicazione delle coordinate

$$
v\longmapsto[v]_B
$$

è un isomorfismo.

---

### **2. Una mappa lineare è determinata da una base**

> 📌 Sia $B=(v_1,\ldots,v_n)$ una base di $V$. Scelti arbitrariamente $w_1,\ldots,w_n\in W$, esiste un'unica applicazione lineare $f:V\to W$ tale che
> $$
> f(v_i)=w_i\qquad(i=1,\ldots,n).
> $$

#### **2.1. Costruzione e dimostrazione**

Ogni $v\in V$ si scrive in modo unico come

$$
v=\lambda_1v_1+\cdots+\lambda_nv_n.
$$

Definiamo

$$
f(v)=\lambda_1w_1+\cdots+\lambda_nw_n.
$$

L'unicità delle componenti rende la definizione ben posta; la formula mostra direttamente la linearità. Qualunque altra mappa lineare $g$ con $g(v_i)=w_i$ deve soddisfare

$$
g(v)=\sum_i\lambda_ig(v_i)=\sum_i\lambda_iw_i=f(v),
$$

quindi $g=f$.

> ⚠️ Senza linearità, conoscere i valori su una base non determina una funzione su tutti gli altri vettori.

Per esempio, in $\mathbb R^2$ entrambe le funzioni

$$
F(a,b)=(a,0),
\qquad
G(a,b)=(a^2,0)
$$

assumono gli stessi valori sui vettori della base canonica:

$$
F(1,0)=G(1,0)=(1,0),
\qquad
F(0,1)=G(0,1)=(0,0).
$$

Tuttavia $F\neq G$; inoltre $G$ non è lineare. È proprio l'ipotesi di linearità a estendere univocamente i valori dalla base a tutto lo spazio.

---

### **3. Compatibilità dei dati assegnati**

Siano

$$
v_1=(1,2,1),\qquad v_2=(2,4,2),\qquad v_3=(1,1,1),
$$

e si richieda una mappa lineare con

$$
f(v_1)=(0,0,1),\qquad
f(v_2)=(0,1,0),\qquad
f(v_3)=(1,h,1).
$$

Poiché

$$
v_2=2v_1,
$$

la linearità imporrebbe

$$
f(v_2)=2f(v_1)=(0,0,2).
$$

Il dato prescritto è invece $(0,1,0)$. La contraddizione non dipende da $h$:

$$
\boxed{\text{non esiste alcuna mappa lineare per nessun }h.}
$$

> 💡 Se i vettori assegnati non costituiscono una base, le loro relazioni lineari devono essere rispettate dalle immagini.

---

### **4. Endomorfismi e automorfismi**

Una mappa lineare $f:V\to V$ è un **endomorfismo**. Un endomorfismo biettivo è un **automorfismo**. Questi termini saranno centrali nello studio di matrici inverse, autovalori e diagonalizzazione.

---

> ✅ Un isomorfismo conserva tutta la struttura lineare. I valori su una base determinano univocamente una mappa lineare; dati su un sistema dipendente sono ammissibili soltanto se ne conservano tutte le relazioni.
