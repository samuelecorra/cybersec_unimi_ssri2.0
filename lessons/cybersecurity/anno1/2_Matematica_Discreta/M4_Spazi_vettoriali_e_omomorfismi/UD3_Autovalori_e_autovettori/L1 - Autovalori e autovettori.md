# **M4 UD3 Lezione 1 - Autovalori e autovettori**

### **1. Definizioni**

Sia $f:V\to V$ un endomorfismo su un campo $K$. Uno scalare $\lambda\in K$ è un **autovalore** di $f$ se esiste un vettore non nullo $v\in V$ tale che

$$
f(v)=\lambda v.
$$

Il vettore $v$ è un **autovettore** relativo a $\lambda$.

> ⚠️ Il vettore nullo soddisfa $f(0)=\lambda0$ per ogni $\lambda$, ma per definizione non è un autovettore.

L'**autospazio** relativo a $\lambda$ è

$$
V_\lambda=\{v\in V:f(v)=\lambda v\}
=\ker(f-\lambda I_V).
$$

Esso contiene anche il vettore nullo ed è un sottospazio. Lo scalare $\lambda$ è un autovalore se e solo se

$$
V_\lambda\neq\{0\}.
$$

Un autovettore non nullo può appartenere a un solo autovalore: se $f(v)=\lambda v=\mu v$, allora $(\lambda-\mu)v=0$ e dunque $\lambda=\mu$. Se $v$ è un autovettore relativo a $\lambda$, ogni multiplo non nullo $cv$ lo è ancora; inoltre ogni combinazione lineare di autovettori relativi allo stesso $\lambda$ resta in $V_\lambda$.

---

### **2. Significato geometrico**

Un autovettore individua una direzione che l'endomorfismo non devia: $f$ può dilatarla, contrarla o invertirne il verso, ma l'immagine resta sulla stessa retta.

- Se $\lambda=1$, i vettori dell'autospazio sono fissi.
- Se $\lambda=0$, l'autospazio coincide con il nucleo.
- Se $\lambda<0$ su $\mathbb R$, il verso è invertito.

Ogni autospazio è invariante:

$$
f(V_\lambda)\subseteq V_\lambda,
$$

poiché per $v\in V_\lambda$,

$$
f(f(v))=f(\lambda v)=\lambda f(v).
$$

Il converso non vale per sottospazi invarianti di dimensione maggiore di $1$: un sottospazio può essere mandato in sé senza che tutti i suoi vettori siano moltiplicati per lo stesso scalare. Per una retta invariante, invece, ogni suo vettore non nullo è un autovettore.

---

### **3. Autospazi distinti sono indipendenti**

> 📌 Autovettori relativi ad autovalori distinti sono linearmente indipendenti.

#### **3.1. Dimostrazione**

Siano $v_1,\ldots,v_r$ autovettori relativi agli autovalori distinti $\lambda_1,\ldots,\lambda_r$. Procediamo per induzione su $r$.

Per $r=1$ è evidente. Supponiamo

$$
\alpha_1v_1+\cdots+\alpha_rv_r=0.
$$

Applichiamo $f$:

$$
\alpha_1\lambda_1v_1+\cdots+\alpha_r\lambda_rv_r=0.
$$

Sottraiamo $\lambda_r$ volte la prima relazione:

$$
\alpha_1(\lambda_1-\lambda_r)v_1+
\cdots+
\alpha_{r-1}(\lambda_{r-1}-\lambda_r)v_{r-1}=0.
$$

Per l'ipotesi induttiva e la distinzione degli autovalori,

$$
\alpha_i(\lambda_i-\lambda_r)=0
$$

implica $\alpha_i=0$ per $i<r$. La relazione iniziale dà poi $\alpha_r=0$.

Più in generale, la somma di autospazi relativi ad autovalori distinti è diretta.

---

### **4. Conseguenze**

Se $\dim V=n$, un endomorfismo non può avere più di $n$ autovalori distinti nel campo $K$. Se possiede esattamente $n$ autovalori distinti, scegliendo un autovettore per ciascuno si ottiene una base di $V$.

---

> ✅ Autovalori e autovettori descrivono le direzioni intrinseche di un endomorfismo. Ogni autospazio è un nucleo e autospazi distinti contribuiscono con direzioni indipendenti.
