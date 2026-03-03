## **Lezione 2: Schema di Shamir**

### **1. Introduzione**

Lo **schema di Shamir** è un sistema di **condivisione a soglia del segreto** (_threshold secret sharing scheme_) che consente di suddividere un segreto $S$ tra $n$ partecipanti, in modo che solo un gruppo di almeno **$k$ partecipanti** possa ricostruirlo.  
È basato sull’**interpolazione polinomiale di Lagrange** e sfrutta le proprietà dei **campi finiti $\mathbb{Z}_p$**.  
L’idea chiave è rappresentare il segreto come il termine noto di un polinomio di grado $k-1$, i cui coefficienti vengono scelti casualmente.

---

### **2. Inizializzazione dello schema $(k, n)$**

1. Si sceglie un **numero primo $p$**, con $p > \max(S, n)$.
    
2. Si scelgono casualmente **$k - 1$ coefficienti**:  
    $$  
    a_1, a_2, \dots, a_{k-1} \in \mathbb{Z}_p  
    $$
    
3. Si definisce il **polinomio segreto**:  
    $$  
    f(x) = S + a_1x + a_2x^2 + \dots + a_{k-1}x^{k-1} \pmod p  
    $$
    

---

### **3. Calcolo e distribuzione delle share**

Il **dealer** calcola per ciascun partecipante $P_i$ ($1 \le i \le n$):

$$  
y_i = f(i) \pmod p  
$$

La coppia $(i, y_i)$ rappresenta la **share personale** assegnata a $P_i$.

Ogni partecipante riceve una sola coppia, e nessuna informazione sulle altre.

---

### **4. Esempio pratico – Schema $(3, 5)$**

Siano dati:  
$$  
p = 19, \quad S = 12, \quad a_1 = 11, \quad a_2 = 2  
$$

Allora:  
$$  
f(x) = 12 + 11x + 2x^2 \pmod{19}  
$$

Calcoliamo le cinque share:

|Partecipante|$i$|$y_i = f(i) \bmod 19$|
|---|---|---|
|P₁|1|6|
|P₂|2|4|
|P₃|3|3|
|P₄|4|12|
|P₅|5|6|

Il dealer distribuisce quindi a ciascun partecipante la propria share $(i, y_i)$.

---

### **5. Ricostruzione del segreto**

Per ricostruire $S$, almeno $k$ partecipanti devono combinare le proprie share.  
Avendo $k$ coppie $(i_j, y_{i_j})$, si ottiene un sistema di $k$ equazioni:

$$  
y_{i_j} = S + a_1 i_j + a_2 i_j^2 + \dots + a_{k-1} i_j^{k-1} \pmod p  
$$

Questo sistema ammette **una sola soluzione**, poiché la **matrice di Vandermonde** associata ai coefficienti è invertibile modulo $p$.

Esempio con $P_1, P_2, P_4$:  
$$  
\begin{cases}  
6 = S + 11 \cdot 1 + 2 \cdot 1^2 \  
4 = S + 11 \cdot 2 + 2 \cdot 2^2 \  
12 = S + 11 \cdot 4 + 2 \cdot 4^2  
\end{cases}  
$$

La soluzione è unica:  
$$  
S = 12, \quad a_1 = 11, \quad a_2 = 2  
$$

---

### **6. Interpolazione di Lagrange**

Il segreto può essere ricostruito anche **senza risolvere il sistema lineare**, ma usando la **formula di Lagrange** per l’interpolazione polinomiale.

Per $k$ partecipanti $(i_1, y_{i_1}), \dots, (i_k, y_{i_k})$, si definisce:

$$  
f(x) = \sum_{j=1}^{k} y_{i_j} \prod_{\substack{t=1 \ t \ne j}}^{k} \frac{x - i_t}{i_j - i_t} \pmod p  
$$

Poiché il segreto è $S = f(0)$, basta calcolare:

$$  
S = \sum_{j=1}^{k} y_{i_j} \prod_{\substack{t=1 \ t \ne j}}^{k} \frac{-i_t}{i_j - i_t} \pmod p  
$$

In pratica, **$S$ è una combinazione lineare delle share**.

---

### **7. Caso di $k - 1$ partecipanti**

Se partecipano solo $k - 1$ utenti, essi hanno:

- $k - 1$ equazioni,
    
- $k$ incognite ($S, a_1, \dots, a_{k-1}$).
    

Il sistema risulta **indeterminato**, e quindi **ogni valore di $S$ è equiprobabile**:  
$$  
\Pr(S | \text{share}_1, \dots, \text{share}_{k-1}) = \Pr(S)  
$$

In altre parole, **nessuna informazione sul segreto** può essere ricavata da meno di $k$ share.

---

### **8. Sintesi finale**

- Lo **schema di Shamir** è una realizzazione elegante e sicura di uno **schema $(k, n)$**.
    
- Il segreto $S$ è il termine noto di un **polinomio casuale** su $\mathbb{Z}_p$.
    
- Almeno **$k$ partecipanti** sono necessari per ricostruire $S$ (tramite equazioni o interpolazione).
    
- Con meno di $k$ partecipanti, il segreto resta **perfettamente nascosto**.
    
- Il sistema è **linearmente sicuro** e **matematicamente deterministico**, garantendo un equilibrio perfetto tra sicurezza e semplicità computazionale.