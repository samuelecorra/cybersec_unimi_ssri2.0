# **M2 UD1 Lezione 2 - Gruppi e loro proprietà**

### **1. Definizione di gruppo**

> 📌 **Definizione:** Un **gruppo** è una coppia $(G, \circledast)$ dove $G$ è un insieme e $\circledast$ una legge di composizione interna su $G$ che soddisfa i seguenti quattro assiomi:
> 1. **Chiusura:** $\forall a, b \in G: a \circledast b \in G$
> 2. **Associatività:** $\forall a, b, c \in G: a \circledast (b \circledast c) = (a \circledast b) \circledast c$
> 3. **Elemento neutro:** $\exists u \in G \;\forall a \in G: a \circledast u = u \circledast a = a$
> 4. **Elemento inverso:** $\forall a \in G \;\exists a' \in G: a \circledast a' = a' \circledast a = u$

> 📌 Un gruppo $(G, \circledast)$ è detto **abeliano** (o **commutativo**) se vale inoltre:
> $$\forall a, b \in G: \quad a \circledast b = b \circledast a$$

---

### **2. Proprietà fondamentali dei gruppi**

#### **2.1. Unicità dell'elemento neutro**

> 📌 **Teorema:** L'elemento neutro di un gruppo è unico.

*Dimostrazione:* Se esistessero $u$ e $v$ con le proprietà dell'elemento neutro, allora $v = v \circledast u = u$. $\square$

#### **2.2. Unicità dell'inverso**

> 📌 **Teorema:** Per ogni $a \in G$, l'elemento inverso è unico.

*Dimostrazione:* Siano $a'$ e $a''$ entrambi inversi di $a$. Allora:
$$a' = a' \circledast u = a' \circledast (a \circledast a'') = (a' \circledast a) \circledast a'' = u \circledast a'' = a''$$

Quindi $a' = a''$. $\square$

#### **2.3. Unicità della soluzione di un'equazione**

> 📌 **Teorema:** In un gruppo $(G, \circledast)$, per ogni $a, b \in G$ l'equazione $a \circledast x = b$ ha un'**unica soluzione** $x = a' \circledast b$.

*Dimostrazione:*
- **Esistenza:** $a \circledast (a' \circledast b) = (a \circledast a') \circledast b = u \circledast b = b$. ✓
- **Unicità:** Se $a \circledast x = b$ e $a \circledast y = b$, allora $a' \circledast (a \circledast x) = a' \circledast (a \circledast y)$, cioè $x = y$. $\square$

> ⚠️ L'equazione $a \circledast x = b$ si risolve moltiplicando a **sinistra** per $a'$. In gruppi non abeliani, l'ordine conta: $x \circledast a = b$ ha soluzione $x = b \circledast a'$ (si moltiplica a destra).

---

### **3. Ordine di un gruppo e periodo di un elemento**

> 📌 **Ordine di un gruppo:** $|G|$ = numero di elementi di $G$. Se $|G| < \infty$ il gruppo è **finito**.

> 📌 **Periodo di un elemento:** In un gruppo finito $(G, \circledast)$, il **periodo** (o ordine) di $a \in G$ è il più piccolo intero positivo $r$ tale che:
> $$a^r = \underbrace{a \circledast a \circledast \cdots \circledast a}_{r \text{ volte}} = u$$

> 💡 Nella notazione additiva (per gruppi abeliani con operazione $+$), $a^r$ diventa $r \cdot a = \underbrace{a + a + \cdots + a}_{r}$.

---

### **4. Esempi di gruppi**

| Struttura | Gruppo? | Motivazione |
|-----------|---------|-------------|
| $(\mathbb{N}, +)$ | **No** | Non esiste inverso: $-n \notin \mathbb{N}$ per $n > 0$ |
| $(\mathbb{Z}, +)$ | **Sì** | Neutro $0$, inverso $-n$; abeliano |
| $(\mathbb{Q}, +)$ | **Sì** | Neutro $0$, inverso $-q$; abeliano |
| $(\mathbb{Q} \setminus \{0\}, \times)$ | **Sì** | Neutro $1$, inverso $1/q \in \mathbb{Q} \setminus \{0\}$; abeliano |
| $(\mathbb{R}, +)$ | **Sì** | Abeliano |
| $(\mathbb{R} \setminus \{0\}, \times)$ | **Sì** | Abeliano |
| $\{3n \mid n \in \mathbb{Z}\}$ con $+$ | **Sì** | $3n + 3m = 3(n+m)$; neutro $3 \cdot 0 = 0$; inverso $3(-n)$ |
| Monomi con $+$ | **No** | Non chiuso: $x + 2x^2$ non è un monomio |
| Polinomi con $+$ | **Sì** | $(\mathbb{Z}[x], +)$ è gruppo abeliano |

---

### **5. Sottogruppi**

> 📌 **Definizione:** Un sottoinsieme $H \subseteq G$ è un **sottogruppo** di $(G, \circledast)$ se $(H, \circledast)$ è esso stesso un gruppo.

**Sottogruppi banali/impropri:**
- $\{u\}$ (il solo elemento neutro) — **sottogruppo triviale**
- $G$ stesso — **sottogruppo improprio**

Tutti gli altri sottogruppi si dicono **propri**.

#### **5.1. Criterio per i sottogruppi**

> 📌 **Criterio:** $H \subseteq G$ (con $H \neq \emptyset$) è un sottogruppo di $G$ se e solo se:
> $$\forall a, b \in H: \quad a \circledast b^{-1} \in H$$

*Dimostrazione:* ($\Rightarrow$) Se $H$ è sottogruppo, allora $b^{-1} \in H$ e $a \circledast b^{-1} \in H$ per chiusura. ($\Leftarrow$) Con $a = b$ si ottiene $u \in H$; con $a = u$ si ha $b^{-1} \in H$; la chiusura segue applicando la condizione a $a$ e $b^{-1}$. $\square$

> 💡 Per i **gruppi finiti**, il criterio si semplifica: $H$ è sottogruppo $\iff \forall a, b \in H: a \circledast b \in H$ (la chiusura basta, perché in un insieme finito la chiusura implica l'esistenza degli inversi).

---

### **6. Esercizi sui sottogruppi di $(\mathbb{Z}, +)$**

**1.** I numeri **pari** formano un sottogruppo di $(\mathbb{Z}, +)$? Sì: la somma di due pari è pari, $0$ è pari, l'opposto di un pari è pari. ✓

**2.** I numeri **dispari** formano un sottogruppo? No: la somma di due dispari è pari, non dispari. ✗

**3.** Le **potenze di 2** ($\{2^n \mid n \in \mathbb{N}\}$) con $\times$? No: manca l'inverso moltiplicativo ($1/2^n \notin \mathbb{N}$). ✗

**4.** I **quadrati perfetti** ($\{n^2 \mid n \in \mathbb{N}\}$) con $+$? No: $1 + 4 = 5$ non è un quadrato. ✗

**5.** Le **frazioni egiziane** $\left\{1, \frac{1}{2}, \frac{1}{3}, \frac{1}{4}, \ldots\right\}$ con $\times$? No: $\frac{1}{2} \times \frac{1}{3} = \frac{1}{6}$ appartiene, ma manca il neutro moltiplicativo $1$ e l'inverso di $\frac{1}{n}$ sarebbe $n$, che non è una frazione egizia unitaria per $n > 1$. ✗

---

> ✅ Un gruppo soddisfa quattro assiomi: chiusura, associatività, neutro unico e inverso unico. In un gruppo l'equazione $a \circledast x = b$ ha sempre un'unica soluzione. Il periodo di un elemento è il minimo esponente che dà il neutro. Un sottoinsieme non vuoto è sottogruppo sse è chiuso rispetto a $a \circledast b^{-1}$.
