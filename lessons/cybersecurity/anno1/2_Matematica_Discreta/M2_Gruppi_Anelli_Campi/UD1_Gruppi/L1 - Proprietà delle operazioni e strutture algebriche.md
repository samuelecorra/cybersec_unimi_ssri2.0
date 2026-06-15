# **M2 UD1 Lezione 1 - Proprietà delle operazioni e strutture algebriche**

### **1. Strutture algebriche**

Le operazioni che si definiscono su un insieme, e le loro proprietà, determinano la **struttura** di quell'insieme. Due aspetti fondamentali:

- **Stesso insieme, operazioni diverse:** $(\mathbb{Z}, +)$ e $(\mathbb{Z}, \times)$ hanno strutture diverse, pur condividendo il supporto.
- **Stessa operazione, insiemi diversi:** $(\mathbb{N}, \times)$ e $(\mathbb{Q}, \times)$ hanno strutture diverse perché in $\mathbb{Q}$ ogni non-zero ha inverso, in $\mathbb{N}$ no.

> 📌 Una **struttura algebrica** è la terna formata da: l'insieme, le operazioni definite su di esso, e le proprietà soddisfatte da tali operazioni.

Studiare strutture algebriche permette di trattare in modo unificato oggetti apparentemente diversi (numeri, polinomi, matrici, permutazioni...) che condividono le stesse proprietà operative.

---

### **2. Operazioni**

> 📌 **Definizione:** Un'**operazione** (o **legge di composizione**) $\circledast$ su un insieme $A$ è un'applicazione:
> $$\circledast: A \times A \to A'$$
> che associa ad ogni coppia ordinata $(a, b) \in A \times A$ uno ed un solo elemento $c \in A'$, detto **risultato**:
> $$a \circledast b = c$$

Se $A' = A$ (il risultato appartiene allo stesso insieme), si parla di **legge di composizione interna**.

**Tipi di operazioni:**
- **Operazioni binarie** $A \times A \to A$: somma, prodotto, ...
- **Operazioni unarie** $A \to A$: radice quadrata, negazione, ...
- **Operazioni $n$-arie** $A^n \to A$: media aritmetica, ...

**Proprietà fondamentali di un'operazione:**
1. Il risultato è **univocamente determinato** (per ogni coppia di input, esiste un solo output)
2. L'operazione è **definita per ogni coppia** di elementi del dominio
3. Se il risultato appartiene allo stesso insieme in cui è definita l'operazione, si dice che l'insieme è **chiuso** rispetto a quella operazione

---

### **3. Proprietà delle operazioni**

#### **3.1. Proprietà associativa**

L'operazione $\circledast$ su $A$ è **associativa** se:

$$\forall a, b, c \in A: \quad a \circledast (b \circledast c) = (a \circledast b) \circledast c$$

L'associatività rende irrilevante l'ordine di esecuzione delle operazioni (le parentesi sono superflue).

#### **3.2. Proprietà commutativa**

L'operazione $\circledast$ su $A$ è **commutativa** se:

$$\forall a, b \in A: \quad a \circledast b = b \circledast a$$

#### **3.3. Elemento neutro**

Un elemento $u \in A$ è **elemento neutro** di $\circledast$ se:

$$\forall a \in A: \quad a \circledast u = u \circledast a = a$$

> 📌 **Teorema:** L'elemento neutro di un'operazione, se esiste, è **unico**.
>
> *Dimostrazione:* Se esistessero due neutri $u$ e $v$:
> - $v \circledast u = v$ (perché $u$ è neutro)
> - $v \circledast u = u$ (perché $v$ è neutro)
> - Quindi $u = v$. $\square$

#### **3.4. Elemento inverso**

Dato un insieme $A$ con operazione $\circledast$ ed elemento neutro $u$, l'elemento $a' \in A$ è **inverso** di $a \in A$ rispetto a $\circledast$ se:

$$a \circledast a' = a' \circledast a = u$$

**Proprietà immediate:**
- L'inverso dell'elemento neutro $u$ è $u$ stesso: $u \circledast u = u$
- L'inverso dell'inverso di $a$ è $a$ stesso: $(a')' = a$

> ⚠️ L'inverso non è sempre definito per tutti gli elementi. Ad esempio in $(\mathbb{N}, +)$ il solo elemento con inverso è $0$ (l'inverso di $n > 0$ sarebbe $-n \notin \mathbb{N}$).

---

### **4. Esempi di operazioni non standard**

Non è necessario restringersi alle operazioni consuete. Si possono definire operazioni arbitrarie e studiarne le proprietà caso per caso.

**Esempio 1:** $a \circledast b = a + 2b$ su $\mathbb{N}$

- Non è commutativa: $a \circledast b = a + 2b \neq b + 2a = b \circledast a$ (salvo $a = b$)
- Non è associativa: $(a \circledast b) \circledast c = (a+2b)+2c$ ma $a \circledast (b \circledast c) = a+2(b+2c) = a+2b+4c$

**Esempio 2:** $a \circledast b = a^b$ su $\mathbb{N}$

- Non commutativa: $2^3 = 8 \neq 9 = 3^2$
- Non associativa: $(2^3)^2 = 64 \neq 512 = 2^{(3^2)}$

> 💡 Verificare una proprietà richiede di dimostrarla per **ogni** coppia (o terna) di elementi. Per confutarla basta un solo **controesempio**.

---

> ✅ Una struttura algebrica è definita dall'insieme, dalle operazioni e dalle loro proprietà. Le proprietà chiave di un'operazione binaria interna sono: associatività, commutatività, esistenza dell'elemento neutro (unico) e degli inversi. Queste proprietà verranno combinate per definire le strutture di gruppo, anello e campo nei moduli successivi.
