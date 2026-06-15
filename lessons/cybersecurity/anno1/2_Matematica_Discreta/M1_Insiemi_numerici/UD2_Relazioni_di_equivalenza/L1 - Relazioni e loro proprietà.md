# **M1 UD2 Lezione 1 - Relazioni e loro proprietà**

### **1. L'idea di relazione**

In matematica, la parola "uguale" ha un significato molto preciso: due oggetti sono uguali se e solo se sono **identici**, cioè sono lo stesso oggetto. Ma spesso ci interessa confrontare oggetti in senso più debole: due triangoli possono essere "simili" senza essere identici, due rette possono essere "parallele", due numeri possono avere lo stesso resto nella divisione per 7.

Tutte queste situazioni si formalizzano con il concetto di **relazione**.

> 💡 Le relazioni servono per due scopi principali in matematica:
> - **Classificare**: raggruppare oggetti "equivalenti" (relazioni di equivalenza)
> - **Ordinare**: disporre oggetti in sequenza (relazioni d'ordine)

---

### **2. Il prodotto cartesiano**

> 📌 **Definizione:** Il **prodotto cartesiano** di due insiemi $A$ e $B$ è l'insieme di tutte le coppie ordinate $(a, b)$ con $a \in A$ e $b \in B$:
> $$A \times B = \{(a, b) : a \in A,\, b \in B\}$$

**Esempio:** Se $A = \{1, 2\}$ e $B = \{x, y, z\}$:
$$A \times B = \{(1,x), (1,y), (1,z), (2,x), (2,y), (2,z)\}$$

$|A \times B| = |A| \cdot |B| = 2 \cdot 3 = 6$.

Il prodotto cartesiano $\mathbb{R} \times \mathbb{R} = \mathbb{R}^2$ è il piano cartesiano.

---

### **3. Definizione di relazione**

> 📌 **Definizione:** Una **relazione** $\mathcal{R}$ su $A \times B$ è un sottoinsieme di $A \times B$. Quando $(a, b) \in \mathcal{R}$ si scrive $a \mathcal{R} b$ e si dice che "$a$ è in relazione con $b$".

Se $A = B$, si parla di relazione su $A$ (o relazione binaria omogenea).

#### **3.1. Relazioni come proposizioni aperte**

Una relazione può essere definita tramite una **proposizione aperta** $P(x, y)$:

$$\mathcal{R} = \{(x, y) \in A \times B : P(x, y) \text{ è vera}\}$$

**Esempi di relazioni su $\mathbb{N} \times \mathbb{N}$:**

| Relazione | Proposizione $P(x,y)$ |
|-----------|----------------------|
| $\leq$ | $x \leq y$ |
| $\mid$ (divisibilità) | $x$ divide $y$ |
| congruenza mod $n$ | $x \equiv y \pmod{n}$ |
| somma div 3 | $3 \mid (x+y)$ |
| coprimi | $\text{MCD}(x,y)=1$ |

**Esempi di relazioni geometriche:**
- Similitudine tra triangoli
- Parallelismo tra rette
- Perpendicolarità tra rette

---

### **4. Proprietà delle relazioni**

Sia $\mathcal{R}$ una relazione su $A \times A$. Le quattro proprietà principali sono:

#### **4.1. Riflessività**

$$\forall a \in A:\quad a \mathcal{R} a$$

Ogni elemento è in relazione con sé stesso.

**Esempi:**
- $\leq$ è riflessiva ($a \leq a$) ✓
- Parallelismo è riflessivo (una retta è parallela a sé stessa) ✓
- $x = 3y$ non è riflessiva ($a = 3a$ solo per $a=0$) ✗

#### **4.2. Simmetria**

$$\forall a, b \in A:\quad a \mathcal{R} b \Rightarrow b \mathcal{R} a$$

Se $a$ è in relazione con $b$, allora anche $b$ è in relazione con $a$.

**Esempi:**
- $=$ (uguaglianza) è simmetrica ✓
- Similitudine è simmetrica ✓
- $\leq$ non è simmetrica ($3 \leq 5$ ma $5 \not\leq 3$) ✗
- Divisibilità non è simmetrica ($2 \mid 6$ ma $6 \nmid 2$) ✗

#### **4.3. Transitività**

$$\forall a, b, c \in A:\quad (a \mathcal{R} b \text{ e } b \mathcal{R} c) \Rightarrow a \mathcal{R} c$$

**Esempi:**
- $\leq$ è transitiva ✓
- Divisibilità è transitiva ✓
- Perpendicolarità **non** è transitiva: $r \perp s$ e $s \perp t$ non implica $r \perp t$ (anzi, implica $r \parallel t$) ✗

#### **4.4. Antisimmetria**

$$\forall a, b \in A:\quad (a \mathcal{R} b \text{ e } b \mathcal{R} a) \Rightarrow a = b$$

Due elementi in relazione in entrambe le direzioni devono essere lo stesso elemento.

> ⚠️ Simmetria e antisimmetria non sono opposte: una relazione può essere né simmetrica né antisimmetrica, oppure può essere entrambe (solo l'uguaglianza lo è, su insiemi non banali). La forma "debole" dell'antisimmetria dice: se $a \neq b$ e $a \mathcal{R} b$, allora $b \not\mathcal{R} a$.

---

### **5. Esercizi di classificazione**

**Esercizio 1:** La relazione $x \mathcal{R} y \Leftrightarrow x = 3y$ su $\mathbb{N}$

- **Riflessiva?** $a = 3a \Rightarrow a = 0$. Non vale per tutti. ✗
- **Simmetrica?** $x = 3y$ implica $y = x/3$, che in generale non vale $y = 3x$. ✗
- **Transitiva?** Se $x = 3y$ e $y = 3z$, allora $x = 9z$. Non implica $x = 3z$. ✗
- **Antisimmetrica?** Se $x = 3y$ e $y = 3x$, allora $x = 9x$, quindi $x = 0 = y$. ✓

**Esercizio 2:** La relazione di divisibilità $x \mid y$ su $\mathbb{N}$

- **Riflessiva?** $a \mid a$. ✓
- **Simmetrica?** $2 \mid 6$ ma $6 \nmid 2$. ✗
- **Transitiva?** Se $a \mid b$ e $b \mid c$, allora $a \mid c$. ✓
- **Antisimmetrica?** Se $a \mid b$ e $b \mid a$, allora $a = b$ (su $\mathbb{N}$). ✓

**Esercizio 3:** La relazione $x > y$ su $\mathbb{N}$

- **Riflessiva?** $a > a$ è falso. ✗
- **Simmetrica?** $3 > 1$ ma non $1 > 3$. ✗
- **Transitiva?** Se $a > b$ e $b > c$, allora $a > c$. ✓
- **Antisimmetrica?** Se $a > b$ e $b > a$, è impossibile (contraddizione). Vacuamente vera. ✓

---

> ✅ Una relazione su $A$ è un sottoinsieme di $A \times A$. Le quattro proprietà fondamentali sono: riflessività (ogni elemento si relaziona con sé), simmetria (la relazione è bidirezionale), transitività (catena di relazioni si propaga), antisimmetria (la bidirezionalità implica l'identità). Le relazioni di equivalenza e d'ordine sono combinazioni specifiche di queste proprietà.
