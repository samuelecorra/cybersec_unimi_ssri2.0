# **M1 UD3 Lezione 2 - L'insieme ℤₙ e le operazioni**

### **1. L'insieme ℤₙ**

> 📌 **Definizione:** $\mathbb{Z}_n$ è l'**insieme quoziente** di $\mathbb{Z}$ rispetto alla relazione di congruenza modulo $n$:
> $$\mathbb{Z}_n = \mathbb{Z}/\!\equiv_n = \{[0], [1], [2], \ldots, [n-1]\}$$

$\mathbb{Z}_n$ ha esattamente $n$ elementi: le classi di equivalenza dei resti $0, 1, \ldots, n-1$.

**Rappresentanti:** Ogni classe $[k]$ contiene tutti gli interi congrui a $k$ modulo $n$:
$$[k] = \{\ldots, k-2n, k-n, k, k+n, k+2n, \ldots\}$$

Si usa tipicamente il **minimo resto non negativo** come rappresentante canonico.

---

### **2. Operazioni in ℤₙ**

Le operazioni di $\mathbb{Z}_n$ sono definite sulle classi di equivalenza:

$$[a] + [b] = [a + b] \pmod{n}$$
$$[a] \cdot [b] = [a \cdot b] \pmod{n}$$

Queste definizioni sono **ben poste**: il risultato non dipende dal rappresentante scelto. Se $[a] = [a']$ e $[b] = [b']$, allora $[a+b] = [a'+b']$ (visto nella lezione precedente).

**Esempio in $\mathbb{Z}_7$:**

| + | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|
| 0 | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
| 1 | 1 | 2 | 3 | 4 | 5 | 6 | 0 |
| 2 | 2 | 3 | 4 | 5 | 6 | 0 | 1 |
| ... | | | | | | | |

---

### **3. Proprietà di ℤₙ**

- **Neutro additivo**: $[0]$ (poiché $[a] + [0] = [a]$)
- **Neutro moltiplicativo**: $[1]$ (poiché $[a] \cdot [1] = [a]$)
- **Opposto**: l'opposto di $[a]$ è $[n - a]$ (poiché $[a] + [n-a] = [n] = [0]$)

> ⚠️ **Non vale la legge di annullamento del prodotto:** In $\mathbb{Z}_6$:
> $$[2] \cdot [3] = [6] = [0]$$
> Eppure $[2] \neq [0]$ e $[3] \neq [0]$. I fattori $[2]$ e $[3]$ si chiamano **divisori dello zero**.

> ⚠️ **Non vale la legge di cancellazione moltiplicativa:** Da $[a] \cdot [b] = [a] \cdot [c]$ non segue $[b] = [c]$ in generale.

---

### **4. Calcolo di potenze modulari**

La compatibilità della congruenza con il prodotto permette di calcolare potenze molto grandi modulo $n$ in modo efficiente, riducendo la base ai suoi resti.

**Strategia generale:** Usare la rappresentazione binaria dell'esponente e il metodo delle **quadrature successive** (square-and-multiply).

**Esempio 1:** Calcolare $79^8 \bmod 7$

$$7^1 \equiv 0 \pmod{7} \quad \Rightarrow \quad 79 \equiv 79 - 11 \cdot 7 = 79 - 77 = 2 \pmod{7}$$

$$79^8 \equiv 2^8 = 256 \pmod{7}$$

$$256 = 36 \cdot 7 + 4 \quad \Rightarrow \quad 256 \equiv 4 \pmod{7}$$

Oppure con quadrature: $2^2 = 4$, $2^4 = 16 \equiv 2 \pmod 7$, $2^8 = (2^4)^2 \equiv 2^2 = 4 \pmod 7$.

**Esempio 2:** Calcolare $83^{21} \bmod 8$

$$83 = 10 \cdot 8 + 3 \quad \Rightarrow \quad 83 \equiv 3 \pmod{8}$$

$$3^2 = 9 \equiv 1 \pmod{8}$$

$$83^{21} \equiv 3^{21} = 3 \cdot (3^2)^{10} \equiv 3 \cdot 1^{10} = 3 \pmod{8}$$

---

### **5. Invertibili e divisori dello zero**

> 📌 **Definizione:** $[a] \in \mathbb{Z}_n$ è **invertibile** (o **unità**) se esiste $[b] \in \mathbb{Z}_n$ tale che $[a] \cdot [b] = [1]$.

> 📌 **Teorema:** $[a]$ è invertibile in $\mathbb{Z}_n$ se e solo se $\text{MCD}(a, n) = 1$ (cioè $a$ e $n$ sono coprimi).

**Conseguenza importante:** Se $n$ è primo, **ogni elemento non nullo** di $\mathbb{Z}_n$ è invertibile, poiché ogni $a$ con $1 \leq a \leq n-1$ soddisfa $\text{MCD}(a, n) = 1$.

**Esempio in $\mathbb{Z}_5$ ($5$ primo):**

| $[a]$ | inverso |
|-------|---------|
| $[1]$ | $[1]$ |
| $[2]$ | $[3]$ (poiché $2 \cdot 3 = 6 \equiv 1$) |
| $[3]$ | $[2]$ |
| $[4]$ | $[4]$ (poiché $4 \cdot 4 = 16 \equiv 1$) |

**Esempio in $\mathbb{Z}_6$ ($6$ non primo):**

- $[2]$ non è invertibile: $\text{MCD}(2,6) = 2 \neq 1$
- $[2]$ è un divisore dello zero: $[2] \cdot [3] = [0]$

> 💡 Quando $n$ è primo, $\mathbb{Z}_n$ è un **campo**: ogni elemento non nullo ha un inverso moltiplicativo, e valgono tutte le proprietà dei campi (inclusa la legge di cancellazione). Quando $n$ è composto, $\mathbb{Z}_n$ è solo un anello con divisori dello zero.

---

> ✅ $\mathbb{Z}_n = \{[0],[1],\ldots,[n-1]\}$ ha $n$ elementi con operazioni di addizione e moltiplicazione ben definite. Il neutro additivo è $[0]$, quello moltiplicativo $[1]$, l'opposto di $[a]$ è $[n-a]$. Non vale la legge di annullamento del prodotto né la cancellazione moltiplicativa in generale. $[a]$ è invertibile se e solo se $\text{MCD}(a,n)=1$; se $n$ è primo, $\mathbb{Z}_n$ è un campo.
