# **M2 UD2 Lezione 2 - Anelli di polinomi**

### **1. Definizione di polinomio**

Sia $A$ un campo. Un **polinomio** in $x$ a coefficienti in $A$ è un'espressione della forma:

$$p(x) = a_n x^n + a_{n-1} x^{n-1} + \cdots + a_1 x + a_0, \qquad a_i \in A, \quad n \in \mathbb{N}$$

> 📌 **Definizione:** L'insieme di tutti i polinomi a coefficienti nel campo $A$ si denota $A[x]$.

---

### **2. Nomenclatura**

| Termine | Definizione |
|---------|-------------|
| **Grado** $\text{gr}[p(x)]$ | Il più alto esponente con coefficiente non nullo; se $p = 0$ il grado è $-\infty$ (o non definito) |
| **Coefficienti** | Gli $a_i \in A$; $a_k$ è il coefficiente del monomio di grado $k$ |
| **Coefficiente direttore** (o **leading coefficient**) | Il coefficiente $a_n$ del termine di grado massimo |
| **Polinomio monico** | $a_n = 1$ |
| **Termine noto** | $a_0$, il termine di grado zero |
| **Polinomi di grado $0$** | Le costanti non nulle; corrispondono agli elementi del campo $A$ |

> ⚠️ Il **polinomio zero** (tutti i coefficienti nulli) è caso speciale: non ha grado definito (o si dice di grado $-\infty$). Va distinto dalla costante $[0]$.

---

### **3. Uguaglianza tra polinomi**

$$p(x) = q(x) \iff \text{gr}[p] = \text{gr}[q] \text{ e } \forall i: a_i = b_i$$

Due polinomi sono uguali se e solo se hanno lo stesso grado e tutti i coefficienti corrispondenti sono uguali.

---

### **4. Addizione di polinomi**

L'addizione è **termine a termine** (per coefficienti dello stesso grado):

$$(a_n x^n + \cdots + a_0) + (b_n x^n + \cdots + b_0) = (a_n + b_n)x^n + \cdots + (a_0 + b_0)$$

**Proprietà del grado:**

$$\text{gr}[p + q] \leq \max\{\text{gr}[p], \text{gr}[q]\}$$

con possibile **abbassamento del grado** se i termini direttori si cancellano.

**Struttura di gruppo di $(A[x], +)$:**

- **Neutro:** il polinomio zero $0 = 0 \cdot x^0$
- **Inverso** (opposto) di $p(x) = a_n x^n + \cdots + a_0$: $-p(x) = (-a_n)x^n + \cdots + (-a_0)$
- **Commutatività:** Sì, ereditata dalla commutatività di $+$ in $A$

> 📌 $(A[x], +)$ è un **gruppo abeliano**.

---

### **5. Moltiplicazione di polinomi**

Il prodotto $p(x) \cdot q(x)$ si calcola con la regola del prodotto distributivo. Il coefficiente del termine di grado $i$ nel prodotto è:

$$c_i = \sum_{\substack{h + k = i \\ h, k \geq 0}} a_h \cdot b_k$$

**Esempio:**

$$(x^2 + x + 1)(x + 1) = x^3 + x^2 + x + x^2 + x + 1 = x^3 + 2x^2 + 2x + 1$$

**Proprietà del grado (fondamentale):**

> 📌 Se $A$ è un **campo** (senza divisori di zero):
> $$\text{gr}[p \cdot q] = \text{gr}[p] + \text{gr}[q]$$

> ⚠️ Se $A$ è solo un anello con divisori di zero, questa formula può fallire: il termine di grado massimo può annullarsi.

**Proprietà della moltiplicazione in $A[x]$ (con $A$ campo):**

- **Chiusura:** Il prodotto di due polinomi è un polinomio. ✓
- **Neutro:** Il polinomio $1$ (costante $1 \in A$). ✓
- **Associatività:** Sì. ✓
- **Commutatività:** Sì (se $A$ è campo). ✓
- **Nessun divisore di zero:** Se $p \neq 0$ e $q \neq 0$, allora $\text{gr}[pq] = \text{gr}[p] + \text{gr}[q] \geq 0$, quindi $pq \neq 0$. ✓
- **Invertibili:** Solo i polinomi di grado $0$ (le costanti non nulle) hanno inverso moltiplicativo; i polinomi di grado $\geq 1$ **non sono invertibili**.

---

### **6. Struttura di anello di $A[x]$**

> 📌 **Teorema:** Se $A$ è un campo, allora $(A[x], +, \times)$ è un **anello commutativo con unità**, senza divisori di zero.

**Riepilogo della struttura:**

| Proprietà | Valore |
|-----------|--------|
| $(A[x], +)$ | gruppo abeliano |
| Moltiplicazione | associativa, commutativa |
| Neutro moltiplicativo | $1$ |
| Divisori di zero | assenti (se $A$ campo) |
| Invertibili | solo le costanti $a \neq 0$ |

> 💡 $A[x]$ è un anello ma **non un campo**: i polinomi di grado $\geq 1$ non hanno inverso moltiplicativo in $A[x]$. Per ottenere una struttura di campo si devono introdurre i **polinomi razionali** $A(x) = \{p/q \mid p, q \in A[x], q \neq 0\}$.

---

> ✅ $A[x]$ è l'anello dei polinomi a coefficienti nel campo $A$. L'addizione è termine a termine; il grado del prodotto è la somma dei gradi (se $A$ è campo). $(A[x], +, \times)$ è un anello commutativo con unità senza divisori di zero, ma non un campo perché i polinomi di grado positivo non sono invertibili.
