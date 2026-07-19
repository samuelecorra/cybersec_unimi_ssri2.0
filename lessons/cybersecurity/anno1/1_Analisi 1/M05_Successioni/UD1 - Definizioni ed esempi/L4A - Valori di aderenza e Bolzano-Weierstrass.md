# **M5 UD1 Lezione 4A - Valori di aderenza e Bolzano-Weierstrass**

### **1. Valori di aderenza**

Un numero $\ell\in\overline{\mathbb R}=\mathbb R\cup\{-\infty,+\infty\}$ è un **valore di aderenza** di $(a_n)$ se esiste una sottosuccessione tale che

$$
a_{\varphi(k)}\to\ell.
$$

Occorre distinguere:

- limite della successione completa;
- limite di una sottosuccessione;
- punto di accumulazione dell'insieme immagine;
- valore assunto infinite volte.

Un valore assunto infinite volte è un valore di aderenza tramite una sottosuccessione costante. Tuttavia un punto di accumulazione dell'immagine può non essere assunto.

---

### **2. Esempi**

1. $(-1)^n$ ha valori di aderenza $-1$ e $1$.
2. $1/(n+1)$ ha unico valore di aderenza $0$, non appartenente all'immagine.
3. $\sin(\pi n/2)$ ha valori $-1,0,1$.
4. $(-1)^nn$ ha valori di aderenza estesi $-\infty,+\infty$.
5. Una enumerazione dei razionali in $[0,1]$, ripetuta opportunamente, può avere ogni punto di $[0,1]$ come valore di aderenza.

---

### **3. Intervalli annidati**

**Teorema.** Se $I_n=[\alpha_n,\beta_n]$ sono intervalli chiusi non vuoti, limitati e

$$
I_{n+1}\subseteq I_n,
$$

allora

$$
\bigcap_{n=0}^{\infty}I_n\ne\varnothing.
$$

Se inoltre $\beta_n-\alpha_n\to0$, l'intersezione contiene un solo punto.

**Dimostrazione.** Le successioni $(\alpha_n)$ e $(\beta_n)$ sono rispettivamente crescente e decrescente, e $\alpha_n\le\beta_m$ per ogni $n,m$. Per completezza esiste

$$
x=\sup\{\alpha_n:n\in\mathbb N\}.
$$

Allora $\alpha_n\le x\le\beta_n$ per ogni $n$, quindi $x\in I_n$ per ogni $n$. Se $x,y$ appartengono a tutti gli intervalli,

$$
|x-y|\le\beta_n-\alpha_n\to0,
$$

dunque $x=y$. $\square$

---

### **4. Teorema di Bolzano-Weierstrass**

> **Teorema.** Ogni successione reale limitata possiede una sottosuccessione convergente.

**Dimostrazione per bisezione.** Tutti i termini stanno in un intervallo chiuso $I_0$. Dividiamolo in due metà: almeno una contiene infiniti termini; scegliamola come $I_1$. Ripetendo, costruiamo intervalli chiusi annidati $I_k$ con lunghezza $|I_0|/2^k$, ciascuno contenente infiniti termini.

Scegliamo ricorsivamente indici

$$
n_0<n_1<n_2<\cdots
$$

con $a_{n_k}\in I_k$. Per il teorema degli intervalli annidati esiste un unico $\ell$ comune. Poiché $a_{n_k},\ell\in I_k$,

$$
|a_{n_k}-\ell|\le\operatorname{lunghezza}(I_k)\to0.
$$

Quindi $a_{n_k}\to\ell$. $\square$

<!-- TODO FIGURA: intervallo iniziale contenente la successione limitata, bisezioni successive con metà contenente infiniti termini, indici n_k crescenti e unico punto comune ell; obiettivo didattico: seguire la costruzione di Bolzano-Weierstrass senza invocare compattezza. -->

---

### **5. Conseguenze**

- Una successione limitata ha almeno un valore di aderenza reale.
- Se una successione limitata ha un solo valore di aderenza $L$, allora converge a $L$.

Per la seconda proprietà, se non convergesse, esisterebbero $\varepsilon_0>0$ e infiniti termini fuori da $(L-\varepsilon_0,L+\varepsilon_0)$. Essi formerebbero una sottosuccessione limitata; Bolzano-Weierstrass ne estrarrebbe una convergente a un valore diverso da $L$, contraddizione.

---

### **6. Bisezione numerica**

Il metodo di bisezione per approssimare uno zero costruisce intervalli annidati dimezzandone la lunghezza. L'esistenza dello zero richiede il teorema dei valori intermedi, che appartiene alla continuità; M05 giustifica invece la convergenza delle approssimazioni una volta costruiti gli intervalli.

L'errore dopo $n$ bisezioni è al più

$$
\frac{\beta_0-\alpha_0}{2^{n+1}}
$$

se si usa il punto medio.

---

### **7. Errori frequenti**

1. Confondere valore assunto infinite volte e punto di accumulazione.
2. Applicare Bolzano-Weierstrass a una successione illimitata.
3. Scegliere indici non crescenti nella bisezione.
4. Usare la compattezza senza averla introdotta.
5. Credere che una sottosuccessione convergente renda convergente l'originale.

---

### **8. Esercizi**

1. Determina i valori di aderenza di $\cos(\pi n/2)$.
2. Costruisci una successione con valori di aderenza $\{0,1,2\}$.
3. Dimostra che ogni successione limitata e priva di sottosuccessioni con limiti distinti converge.
4. Completa tutti i dettagli della scelta degli indici nella prova di Bolzano-Weierstrass.
5. Mostra che gli intervalli $(0,1/n)$ annidati non hanno intersezione, spiegando il ruolo della chiusura.
6. Stima quante bisezioni servono per un errore inferiore a $10^{-6}$ partendo da un intervallo lungo $3$.

---

### **9. Riepilogo**

> ✅ Bolzano-Weierstrass trasforma la limitatezza in convergenza di almeno una sottosuccessione; la prova usa completezza e intervalli annidati, non intuizione grafica.
