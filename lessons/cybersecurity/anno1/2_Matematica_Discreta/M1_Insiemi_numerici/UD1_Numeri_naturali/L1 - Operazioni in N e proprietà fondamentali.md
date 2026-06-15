# **M1 UD1 Lezione 1 - Operazioni in ℕ e proprietà fondamentali**

### **1. L'insieme dei numeri naturali**

I **numeri naturali** sono l'insieme su cui si fonda tutta la matematica elementare discreta. Esistono due convenzioni sulla loro definizione:

- $\mathbb{N} = \{0, 1, 2, 3, \ldots\}$ (inclusivo dello zero — convenzione più usata in matematica moderna)
- $\mathbb{N}_0 = \{1, 2, 3, \ldots\}$ (senza lo zero — convenzione più antica)

> 📌 Nel corso si adotta $\mathbb{N}$ con lo zero incluso. Occorre prestare attenzione quando si parla di "elementi non nulli" o si usa $\mathbb{N}_0$.

---

### **2. Operazioni interne**

Un'operazione è **interna** a un insieme $A$ se, dati due elementi di $A$, il risultato appartiene ancora ad $A$.

Le due operazioni fondamentali su $\mathbb{N}$ sono:

- **Addizione**: $+: \mathbb{N} \times \mathbb{N} \to \mathbb{N}$, con $(a, b) \mapsto a + b$
- **Moltiplicazione**: $\cdot: \mathbb{N} \times \mathbb{N} \to \mathbb{N}$, con $(a, b) \mapsto a \cdot b$

Entrambe sono operazioni interne perché la somma e il prodotto di due naturali è sempre un naturale.

> ⚠️ La **sottrazione** e la **divisione** non sono operazioni interne su $\mathbb{N}$: ad esempio $3 - 5 \notin \mathbb{N}$ e $7 / 2 \notin \mathbb{N}$.

#### **2.1. Esempi di operazioni non standard**

Non tutte le operazioni binarie sono quelle consuete. Si possono definire operazioni arbitrarie e studiarne le proprietà caso per caso.

**Esempio 1:** $a * b = a + 2b$

- Non è commutativa: $a * b = a + 2b \neq b + 2a = b * a$ (salvo $a = b$)
- Verificare l'associatività: $(a*b)*c = (a+2b)*c = a+2b+2c$, mentre $a*(b*c) = a+2(b+2c) = a+2b+4c$. Non associativa.

**Esempio 2:** $a * b = a^b$

- Non è commutativa: $2^3 = 8 \neq 3^2 = 9$
- Non è associativa: $(2^3)^2 = 64 \neq 2^{(3^2)} = 2^9 = 512$

---

### **3. Proprietà delle operazioni**

#### **3.1. Associatività**

Un'operazione $*$ su $A$ è **associativa** se:

$$\forall a, b, c \in A:\quad (a * b) * c = a * (b * c)$$

Sia $+$ che $\cdot$ sono associative in $\mathbb{N}$. Grazie all'associatività, le parentesi diventano irrilevanti e si può scrivere $a + b + c$ senza ambiguità.

#### **3.2. Commutatività**

Un'operazione $*$ è **commutativa** se:

$$\forall a, b \in A:\quad a * b = b * a$$

Sia $+$ che $\cdot$ sono commutative in $\mathbb{N}$.

#### **3.3. Elemento neutro**

Un elemento $e \in A$ è **neutro** per $*$ se:

$$\forall a \in A:\quad e * a = a * e = a$$

- Neutro per l'addizione: $0$ (poiché $a + 0 = 0 + a = a$)
- Neutro per la moltiplicazione: $1$ (poiché $a \cdot 1 = 1 \cdot a = a$)

> ⚠️ L'elemento neutro è **unico**: se esistessero due neutri $e_1$ ed $e_2$, si avrebbe $e_1 = e_1 * e_2 = e_2$.

Attenzione: $0$ e $1$ sono neutri **solo per le rispettive operazioni** — lo zero non è neutro per la moltiplicazione, né l'uno per l'addizione.

#### **3.4. Elemento assorbente**

Un elemento $z \in A$ è **assorbente** per $*$ se:

$$\forall a \in A:\quad z * a = a * z = z$$

- Assorbente per la moltiplicazione: $0$ (poiché $a \cdot 0 = 0 \cdot a = 0$)
- L'addizione non ha elementi assorbenti in $\mathbb{N}$

> 📌 Il neutro e l'assorbente sono duali: il neutro "non fa niente", l'assorbente "inghiotte tutto".

#### **3.5. Distributività**

La moltiplicazione è **distributiva** rispetto all'addizione:

$$a \cdot (b + c) = a \cdot b + a \cdot c$$

> ⚠️ L'addizione **non** è distributiva rispetto alla moltiplicazione: in generale $a + (b \cdot c) \neq (a + b) \cdot (a + c)$.

**Controesempio:** $1 + (2 \cdot 3) = 7$, ma $(1+2)(1+3) = 12$.

La distributività vale anche per le operazioni insiemistiche:

$$A \cap (B \cup C) = (A \cap B) \cup (A \cap C)$$
$$A \cup (B \cap C) = (A \cup B) \cap (A \cup C)$$

---

### **4. Ordinamento di ℕ**

L'insieme $\mathbb{N}$ è dotato di un **ordinamento totale** $\leq$: dati qualunque $a, b \in \mathbb{N}$, vale esattamente una delle tre:

$$a < b, \quad a = b, \quad a > b$$

> 📌 L'ordinamento di $\mathbb{N}$ è **discreto**: tra $n$ e $n+1$ non esiste alcun numero naturale. Questa proprietà è alla base del principio di induzione.

L'ordinamento è **compatibile** con le operazioni:
- Se $a \leq b$, allora $a + c \leq b + c$
- Se $a \leq b$ e $c \geq 0$, allora $a \cdot c \leq b \cdot c$

---

### **5. Leggi di cancellazione**

#### **5.1. Cancellazione per la somma**

$$a + c = b + c \implies a = b$$

Equivalentemente: se si somma lo stesso valore a entrambi i membri di un'uguaglianza, l'uguaglianza si conserva (e viceversa).

#### **5.2. Cancellazione per il prodotto**

$$a \cdot c = b \cdot c \text{ e } c \neq 0 \implies a = b$$

> ⚠️ La condizione $c \neq 0$ è essenziale: se $c = 0$, l'equazione $a \cdot 0 = b \cdot 0$ è sempre vera (entrambi i membri valgono 0) e non si può concludere nulla su $a$ e $b$.

> 💡 Le leggi di cancellazione sono particolarmente importanti nelle strutture algebriche più generali (anelli, campi): in $\mathbb{Z}_n$ la cancellazione per il prodotto può fallire anche per $c \neq 0$ se $c$ e $n$ non sono coprimi.

---

> ✅ Le operazioni $+$ e $\cdot$ su $\mathbb{N}$ sono associative, commutative, con elementi neutri rispettivi $0$ e $1$, con $0$ assorbente per $\cdot$, con $\cdot$ distributiva su $+$, e soddisfano entrambe le leggi di cancellazione (quella moltiplicativa solo per fattori non nulli). L'ordinamento totale di $\mathbb{N}$ è discreto e compatibile con le operazioni.
