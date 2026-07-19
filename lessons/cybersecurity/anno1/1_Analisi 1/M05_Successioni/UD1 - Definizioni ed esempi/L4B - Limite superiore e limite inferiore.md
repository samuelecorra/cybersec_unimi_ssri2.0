# **M5 UD1 Lezione 4B - Limite superiore e limite inferiore**

### **1. Estremi delle code**

Sia $(a_n)$ limitata. Per ogni $n$ definiamo

$$
s_n=\sup\{a_k:k\ge n\},
$$

$$
i_n=\inf\{a_k:k\ge n\}.
$$

Eliminando termini, il supremo non può aumentare e l'infimo non può diminuire:

$$
s_{n+1}\le s_n,
\qquad
i_{n+1}\ge i_n.
$$

Inoltre $i_n\le a_n\le s_n$.

<!-- TODO FIGURA: grafico discreto oscillante con code da n in poi, inviluppo superiore s_n decrescente e inferiore i_n crescente verso due quote; obiettivo didattico: interpretare limsup e liminf come limiti degli estremi delle code. -->

---

### **2. Definizioni**

Per monotonia e limitatezza, esistono i limiti

$$
\limsup_{n\to\infty}a_n
=
\lim_{n\to\infty}s_n
=
\inf_n s_n,
$$

$$
\liminf_{n\to\infty}a_n
=
\lim_{n\to\infty}i_n
=
\sup_n i_n.
$$

Per successioni non limitate si estendono le definizioni a $\overline{\mathbb R}$.

Poiché $i_n\le s_n$,

$$
\liminf a_n\le\limsup a_n.
$$

---

### **3. Relazione con i valori di aderenza**

Per una successione reale limitata:

- $\limsup a_n$ è il massimo dei valori di aderenza;
- $\liminf a_n$ è il minimo dei valori di aderenza.

**Dimostrazione per il limsup.** Poniamo $S=\lim s_n$. Costruiamo ricorsivamente indici crescenti. Scelto $k_{n-1}$, poniamo $r_n=\max\{n,k_{n-1}+1\}$ e scegliamo $k_n\geq r_n$ tale che

$$
a_{k_n}>s_{r_n}-\frac1{n+1}.
$$

La scelta è possibile per la definizione di supremo. Poiché $a_{k_n}\leq s_{r_n}$ e $s_{r_n}\to S$, i carabinieri danno $a_{k_n}\to S$. Se $a_{h_j}\to\ell$ è un'altra sottosuccessione, per ogni coda fissata $n$ vale definitivamente $a_{h_j}\leq s_n$; passando al limite si ottiene $\ell\leq s_n$ per ogni $n$, quindi $\ell\leq S$. Pertanto $S$ è il massimo dei valori di aderenza. Il caso del liminf è analogo.

---

### **4. Criterio di convergenza**

> **Teorema.** Una successione limitata converge a $L$ se e solo se

$$
\liminf a_n=\limsup a_n=L.
$$

Se $a_n\to L$, anche $i_n,s_n\to L$. Viceversa, da

$$
i_n\le a_n\le s_n
$$

e dal comune limite $L$, il teorema dei carabinieri produce $a_n\to L$.

---

### **5. Esempi**

Per $a_n=(-1)^n$,

$$
\limsup a_n=1,\qquad \liminf a_n=-1.
$$

Per $a_n=\sin(\pi n/2)$ i valori di aderenza sono $-1,0,1$, quindi gli stessi estremi valgono.

Per $a_n=(-1)^n/(n+1)$,

$$
\limsup a_n=\liminf a_n=0,
$$

e la successione converge a $0$.

---

### **6. Monotonia rispetto al confronto**

Se $a_n\le b_n$ definitivamente, allora

$$
\limsup a_n\le\limsup b_n,
$$

$$
\liminf a_n\le\liminf b_n.
$$

Infatti la stessa disuguaglianza vale tra supremi e infimi delle code a partire da una soglia comune.

---

### **7. Errori frequenti**

1. Definire il limsup come $\sup\{a_n\}$ dell'intera successione.
2. Dimenticare le code $k\ge n$.
3. Confondere limsup con massimo assunto definitivamente.
4. Usare valori reali senza ammettere $\pm\infty$ per successioni illimitate.
5. Concludere convergenza quando liminf e limsup sono distinti.

---

### **8. Esercizi**

1. Calcola limsup e liminf di $2+(-1)^n/(n+1)$.
2. Studia $a_n=n\sin(\pi n/2)$ nei reali estesi.
3. Determina le successioni $s_n,i_n$ per $a_n=(-1)^n$.
4. Dimostra rigorosamente la caratterizzazione mediante valori di aderenza.
5. Prova le disuguaglianze di monotonia rispetto al confronto definitivo.
6. Costruisci una successione con liminf $0$, limsup $2$ e infiniti valori di aderenza.

---

### **9. Riepilogo**

> ✅ Limite superiore e inferiore misurano gli estremi asintotici delle code; coincidono esattamente quando una successione limitata converge.
