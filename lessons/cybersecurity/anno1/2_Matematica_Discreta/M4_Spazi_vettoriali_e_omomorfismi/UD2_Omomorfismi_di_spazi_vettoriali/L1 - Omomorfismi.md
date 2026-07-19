# **M4 UD2 Lezione 1 - Omomorfismi**

### **1. Definizione**

Siano $V$ e $W$ spazi vettoriali sullo stesso campo $K$. Un'applicazione

$$
f:V\longrightarrow W
$$

è un **omomorfismo di spazi vettoriali**, o **applicazione lineare**, se per ogni $u,v\in V$ e $\lambda,\mu\in K$ vale

$$
f(\lambda u+\mu v)=\lambda f(u)+\mu f(v).
$$

È equivalente richiedere separatamente

$$
f(u+v)=f(u)+f(v),\qquad f(\lambda u)=\lambda f(u).
$$

Per induzione, la linearità conserva ogni combinazione lineare finita:

$$
f\left(\sum_{i=1}^n\lambda_iv_i\right)
=\sum_{i=1}^n\lambda_if(v_i).
$$

---

### **2. Conseguenze immediate**

Ogni applicazione lineare soddisfa

$$
f(0_V)=0_W.
$$

Infatti $f(0_V)=f(0_Kv)=0_Kf(v)=0_W$. Inoltre

$$
f(-v)=-f(v),\qquad f(u-v)=f(u)-f(v).
$$

> ⚠️ Se $f(0_V)\neq0_W$, l'applicazione non è lineare. La condizione $f(0)=0$, tuttavia, è soltanto necessaria e non basta da sola.

---

### **3. Esempi in coordinate**

Consideriamo $f_1:\mathbb R^3\to\mathbb R^2$ definita da

$$
f_1(a,b,c)=(a+b,b-c).
$$

Per $u=(a,b,c)$, $v=(a',b',c')$ e $\lambda,\mu\in\mathbb R$,

$$
f_1(\lambda u+\mu v)
=\lambda(a+b,b-c)+\mu(a'+b',b'-c')
=\lambda f_1(u)+\mu f_1(v),
$$

quindi $f_1$ è lineare.

L'applicazione

$$
f_2(a,b,c)=(a-b,a+c^2)
$$

non è lineare, a causa del termine quadratico. Per esempio,

$$
f_2(0,0,2)=(0,4)\neq2(0,1)=2f_2(0,0,1).
$$

È invece lineare

$$
f_3(a,b,c)=(a+b,b-2c).
$$

---

### **4. Un'applicazione a valori polinomiali**

Sia

$$
f:\mathbb R^3\longrightarrow\mathbb R_2[x],
$$

$$
f(a,b,c)=ax^2+(a-b)x-2c.
$$

Ogni coefficiente del polinomio dipende linearmente da $a,b,c$. Infatti

$$
f(a,b,c)=a(x^2+x)+b(-x)+c(-2).
$$

Quindi, per ogni combinazione lineare dei vettori in ingresso, i coefficienti si combinano con gli stessi scalari: $f$ è un omomorfismo.

> 💡 Un'espressione coordinata definisce una mappa lineare quando ciascuna coordinata d'uscita è una forma lineare omogenea nelle coordinate d'ingresso: non devono comparire potenze, prodotti tra variabili o termini costanti non nulli.

---

### **5. Procedura di verifica**

1. Controllare che dominio e codominio siano spazi sullo stesso campo.
2. Calcolare $f(\lambda u+\mu v)$ con elementi generici.
3. Confrontarlo con $\lambda f(u)+\mu f(v)$.
4. Per trovare rapidamente un controesempio, provare $0$, la moltiplicazione per uno scalare o l'additività.

---

> ✅ Un omomorfismo conserva esattamente la struttura lineare: zero, somme, multipli scalari e quindi tutte le combinazioni lineari.
