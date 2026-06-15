# **M1 UD1 Lezione 4 - Divisione e numeri primi**

### **1. La divisione euclidea in ℕ**

> 📌 **Teorema della divisione euclidea:** Per ogni $a \in \mathbb{N}$ e $b \in \mathbb{N}$ con $b \neq 0$, esistono unici $q, r \in \mathbb{N}$ tali che:
> $$a = bq + r, \qquad 0 \leq r < b$$

- $q$ è il **quoziente** (intero), scritto anche $a \div b$ oppure $a \operatorname{div} b$
- $r$ è il **resto**, scritto anche $a \bmod b$

**Esempi:**
- $17 = 5 \cdot 3 + 2$: quoziente $3$, resto $2$
- $20 = 4 \cdot 5 + 0$: quoziente $5$, resto $0$

> ⚠️ L'**unicità** di quoziente e resto è fondamentale: per ogni coppia $(a, b)$ esiste una sola coppia $(q, r)$ con $0 \leq r < b$. Senza questo vincolo ci sarebbero infinite rappresentazioni (ad es. $17 = 5 \cdot 2 + 7$ ma $7 \geq 5$, quindi non è la divisione euclidea).

---

### **2. Divisori e multipli**

**Definizione:** $b$ **divide** $a$ (si scrive $b \mid a$) se esiste $k \in \mathbb{N}$ tale che $a = kb$, equivalentemente se $a \bmod b = 0$.

In questo caso:
- $b$ è un **divisore** di $a$
- $a$ è un **multiplo** di $b$

**Esempi:**
- $3 \mid 12$ (poiché $12 = 3 \cdot 4$)
- $5 \nmid 13$ (poiché $13 = 5 \cdot 2 + 3$, resto $\neq 0$)

**Proprietà della divisibilità:**
- $1 \mid a$ per ogni $a$
- $a \mid a$ (riflessività)
- Se $a \mid b$ e $b \mid c$, allora $a \mid c$ (transitività)
- Se $a \mid b$ e $a \mid c$, allora $a \mid (b + c)$ e $a \mid (b - c)$

---

### **3. Numeri primi**

> 📌 **Definizione:** Un numero $p \in \mathbb{N}$ con $p \geq 2$ è **primo** se ha esattamente due divisori: $1$ e $p$ stesso.

I primi numeri primi sono: $2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, \ldots$

**Casi particolari:**
- $1$ **non è primo**: ha un solo divisore (sé stesso), non due. Questa esclusione è necessaria per la validità del teorema fondamentale dell'aritmetica.
- Un numero $n \geq 2$ che non è primo è detto **composto**.

> ⚠️ La scelta di escludere $1$ dai numeri primi non è arbitraria: se $1$ fosse primo, la fattorizzazione non sarebbe unica (si potrebbe moltiplicare per $1$ quante volte si vuole).

> 💡 Come verificare se $n$ è primo? Basta controllare se ha divisori tra $2$ e $\lfloor\sqrt{n}\rfloor$. Se $n$ fosse composto, uno dei suoi fattori sarebbe $\leq \sqrt{n}$.

---

### **4. Infinità dei numeri primi**

> 📌 **Teorema di Euclide:** I numeri primi sono infiniti.

**Dimostrazione** (per assurdo): Supponiamo per assurdo che i numeri primi siano finiti: $p_1, p_2, \ldots, p_k$. Consideriamo il numero:

$$P = p_1 \cdot p_2 \cdots p_k + 1$$

Analizziamo $P$: per definizione, $P > 1$, quindi $P$ è divisibile da qualche primo $p$. Ma $P$ diviso per qualunque $p_i$ dà resto $1$ (non $0$), poiché $p_i \mid p_1 p_2 \cdots p_k$ e $P = p_1 p_2 \cdots p_k + 1$.

Dunque $p$ non appartiene alla lista $\{p_1, \ldots, p_k\}$ — contraddizione con l'ipotesi che la lista sia completa. $\square$

> ⚠️ Attenzione: $P = p_1 p_2 \cdots p_k + 1$ non è necessariamente primo. Ad esempio $2 \cdot 3 \cdot 5 \cdot 7 + 1 = 211$ è primo, ma $2 \cdot 3 \cdot 5 \cdot 7 \cdot 11 \cdot 13 + 1 = 30031 = 59 \times 509$ è composto. Il punto è solo che $P$ ha un fattore primo diverso da tutti i $p_i$.

---

### **5. Teorema fondamentale dell'aritmetica**

> 📌 **Teorema fondamentale dell'aritmetica:** Ogni intero $n \geq 2$ può essere scritto come prodotto di numeri primi in modo **unico**, a meno dell'ordine dei fattori:
> $$n = p_1^{e_1} \cdot p_2^{e_2} \cdots p_k^{e_k}$$
> con $p_1 < p_2 < \cdots < p_k$ primi e $e_i \geq 1$.

Questa rappresentazione si chiama **fattorizzazione in fattori primi** (o **scomposizione in primi**).

**Esempi:**
- $12 = 2^2 \cdot 3$
- $360 = 2^3 \cdot 3^2 \cdot 5$
- $1001 = 7 \cdot 11 \cdot 13$

Il teorema ha due parti:

1. **Esistenza**: ogni $n \geq 2$ si può fattorizzare (dimostrazione per induzione/buon ordinamento)
2. **Unicità**: la fattorizzazione è unica (la dimostrazione richiede il lemma di Euclide: se $p \mid ab$ e $p$ è primo, allora $p \mid a$ oppure $p \mid b$)

> 💡 Il teorema fondamentale è alla base di quasi tutta la teoria dei numeri elementare. Permette di ridurre ogni proprietà di un numero intero allo studio delle sue potenze prime, che sono molto più semplici da analizzare.

---

> ✅ La divisione euclidea in $\mathbb{N}$ fornisce un quoziente e un resto unici. La divisibilità ($b \mid a$) è una relazione transitiva su $\mathbb{N}$. I numeri primi (esattamente 2 divisori, $\geq 2$) sono infiniti per il teorema di Euclide. Il teorema fondamentale dell'aritmetica garantisce che ogni $n \geq 2$ ha un'unica fattorizzazione in primi.
