# **M2 UD1 Lezione 3 - Gruppi di classi di resto e gruppi ciclici**

### **1. Il gruppo additivo $\mathbb{Z}_n$**

> 📌 **Teorema:** $(\mathbb{Z}_n, +)$ è un **gruppo abeliano di ordine $n$**.

**Dimostrazione:** $\mathbb{Z}_n = \{[0], [1], \ldots, [n-1]\}$ con la somma $[a] + [b] = [a + b \bmod n]$.

- **Chiusura:** La somma di due classi è una classe. ✓
- **Associatività:** Ereditata da $\mathbb{Z}$. ✓
- **Neutro:** $[0]$ (poiché $[a] + [0] = [a]$). ✓
- **Inverso:** $[a]^{-1} = [n - a]$ (poiché $[a] + [n-a] = [n] = [0]$). ✓
- **Commutatività:** $[a] + [b] = [a+b] = [b+a] = [b] + [a]$. ✓ $\square$

---

### **2. Il gruppo moltiplicativo $\mathbb{Z}_n^*$**

Nel gruppo additivo $(\mathbb{Z}_n, +)$ ogni elemento ha inverso additivo. Per la moltiplicazione la situazione è diversa.

> 📌 **Teorema:** $(\mathbb{Z}_p \setminus \{[0]\}, \times)$ è un gruppo se e solo se $p$ è **primo**.

*Dimostrazione:*
- ($\Leftarrow$) Se $p$ è primo e $[a] \neq [0]$, allora $\text{MCD}(a, p) = 1$, quindi $[a]$ è invertibile in $\mathbb{Z}_p$.
- ($\Rightarrow$) Se $p = m \cdot k$ con $1 < m, k < p$, allora $[m] \cdot [k] = [0]$ ma $[m] \neq [0]$ e $[k] \neq [0]$: $[m]$ e $[k]$ sono divisori di zero, quindi non invertibili. Il prodotto senza $[0]$ non è chiuso. $\square$

**Definizione generale:**

> 📌 $\mathbb{Z}_n^* = \{[a] \in \mathbb{Z}_n \mid \text{MCD}(a, n) = 1\}$ — insieme degli elementi **invertibili** di $\mathbb{Z}_n$.

> 📌 **Teorema:** $(\mathbb{Z}_n^*, \times)$ è sempre un **gruppo abeliano**. Il suo ordine è $|\mathbb{Z}_n^*| = \varphi(n)$ (funzione di Eulero).

**Esempi:**
- $\mathbb{Z}_8^* = \{[1], [3], [5], [7]\}$, ordine $\varphi(8) = 4$
- $\mathbb{Z}_{12}^* = \{[1], [5], [7], [11]\}$, ordine $\varphi(12) = 4$
- $\mathbb{Z}_{16}^* = \{[1], [3], [5], [7], [9], [11], [13], [15]\}$, ordine $\varphi(16) = 8$

---

### **3. Periodi degli elementi**

**Calcolo del periodo in $(\mathbb{Z}_{12}^*, \times)$:**

| Elemento | Potenze | Periodo |
|----------|---------|---------|
| $[1]$ | $1^1 = 1$ | $r = 1$ |
| $[5]$ | $5^1=5$, $5^2=25\equiv 1$ | $r = 2$ |
| $[7]$ | $7^1=7$, $7^2=49\equiv 1$ | $r = 2$ |
| $[11]$ | $11^1=11$, $11^2=121\equiv 1$ | $r = 2$ |

**Calcolo del periodo in $(\mathbb{Z}_7^*, \times)$:**

| Elemento | Periodo | Generatore? |
|----------|---------|-------------|
| $[1]$ | $1$ | No |
| $[2]$ | $3$ ($2,4,1$) | No |
| $[3]$ | $6$ ($3,2,6,4,5,1$) | **Sì** |
| $[4]$ | $3$ ($4,2,1$) | No |
| $[5]$ | $6$ ($5,4,6,2,3,1$) | **Sì** |
| $[6]$ | $2$ ($6,1$) | No |

**Calcolo del periodo in $(\mathbb{Z}_5, +)$ (notazione additiva: $r \cdot a = 0$):**

| Elemento | Periodo |
|----------|---------|
| $[0]$ | $1$ |
| $[1]$ | $5$ (il minimo $r$ con $r \cdot 1 \equiv 0 \pmod 5$ è $r=5$) |
| $[2]$ | $5$ |
| $[3]$ | $5$ |
| $[4]$ | $5$ |

> 💡 In un gruppo **additivo**, $a^r = r \cdot a = \underbrace{a + a + \cdots + a}_{r}$. Il periodo è il minimo $r > 0$ con $r \cdot a = 0$.

---

### **4. Gruppi ciclici**

#### **4.1. Definizione**

> 📌 **Definizione:** Un gruppo $(G, \circledast)$ è **ciclico** se esiste $g \in G$ tale che ogni elemento di $G$ è una potenza di $g$:
> $$G = \{g^k \mid k \in \mathbb{Z}\} = \langle g \rangle$$
> L'elemento $g$ si dice **generatore** del gruppo.

**Esempi di gruppi ciclici:**
- $(\mathbb{Z}, +)$: ciclico, generato da $1$ (o da $-1$); $n = n \cdot 1 = 1 + 1 + \cdots + 1$
- $(\mathbb{Z}_n, +)$: ciclico, generato da $[1]$
- $(\mathbb{Z}_7^*, \times)$: ciclico (generatori: $[3]$ e $[5]$, entrambi di periodo $6 = \varphi(7)$)
- $(\mathbb{Z}_5^*, \times)$: ciclico (generatori: $[2]$ e $[3]$ di periodo $4$; $[4]$ non è generatore, periodo $2$)

---

#### **4.2. Teoremi sui gruppi ciclici**

> 📌 **Teorema:** Ogni gruppo ciclico è abeliano.

*Dimostrazione:* Siano $a = g^i$ e $b = g^k$. Allora $a \circledast b = g^i \circledast g^k = g^{i+k} = g^{k+i} = g^k \circledast g^i = b \circledast a$. $\square$

> 📌 **Teorema:** Le potenze di un elemento $g$ di un gruppo finito formano un **sottogruppo ciclico** $\langle g \rangle \subseteq G$.

> 📌 **Teorema:** Un gruppo di ordine **primo** è necessariamente ciclico (e ha soli sottogruppi banali).

*Dimostrazione:* Sia $|G| = p$ primo. Per qualsiasi $a \neq u$, il sottogruppo $\langle a \rangle$ ha ordine $r > 1$; per il teorema di Lagrange $r \mid p$, quindi $r = p$ e $\langle a \rangle = G$. $\square$

> 📌 **Teorema:** Ogni sottogruppo di un gruppo ciclico è ciclico.

*Dimostrazione (cenno):* Sia $H \leq G = \langle g \rangle$. Si prende il più piccolo esponente positivo $m$ tale che $g^m \in H$: si dimostra che $\langle g^m \rangle = H$. $\square$

**Conseguenza:** Un gruppo ciclico di ordine $n$ ha **esattamente un sottogruppo per ogni divisore di $n$**.

---

#### **4.3. Sottogruppi di $(\mathbb{Z}_{12}, +)$**

$|G| = 12$, divisori: $1, 2, 3, 4, 6, 12$.

| Ordine | Sottogruppo | Generatore |
|--------|-------------|------------|
| $1$ | $\{[0]\}$ | $[0]$ |
| $2$ | $\{[0], [6]\}$ | $[6]$ |
| $3$ | $\{[0], [4], [8]\}$ | $[4]$ |
| $4$ | $\{[0], [3], [6], [9]\}$ | $[3]$ |
| $6$ | $\{[0], [2], [4], [6], [8], [10]\}$ | $[2]$ |
| $12$ | $\mathbb{Z}_{12}$ | $[1]$ |

> ⚠️ Il generatore del sottogruppo di ordine $d$ in $(\mathbb{Z}_n, +)$ è $[n/d]$, cioè la classe di $n/d$.

---

> ✅ $(\mathbb{Z}_n, +)$ è sempre un gruppo abeliano di ordine $n$. $(\mathbb{Z}_n^*, \times)$ è un gruppo abeliano di ordine $\varphi(n)$; $\mathbb{Z}_p \setminus \{0\}$ è un gruppo moltiplica­tivo solo se $p$ è primo. Un gruppo ciclico è generato da un singolo elemento, è abeliano, e ha esattamente un sottogruppo per ogni divisore del suo ordine.
