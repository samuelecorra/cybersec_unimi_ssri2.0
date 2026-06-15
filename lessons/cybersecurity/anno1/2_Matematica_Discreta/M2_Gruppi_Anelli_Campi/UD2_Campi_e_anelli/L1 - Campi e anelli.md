# **M2 UD2 Lezione 1 - Campi e anelli**

### **1. Strutture con due operazioni**

Finora abbiamo studiato strutture con un'unica operazione (i gruppi). Passiamo ora a strutture dotate di **due operazioni**, che chiameremo $+$ e $\times$ per analogia con le operazioni aritmetiche classiche.

> 📌 **Proprietà distributiva:** Un'operazione $\times$ si dice **distributiva** rispetto a $+$ se:
> $$a \times (b + c) = (a \times b) + (a \times c)$$
> e analogamente a sinistra: $(b + c) \times a = (b \times a) + (c \times a)$.

La distributività è il legame fondamentale tra le due operazioni in una struttura a due operazioni.

---

### **2. Definizione di campo**

> 📌 **Definizione:** Una struttura $(C, +, \times)$ è un **campo** se:
> 1. $(C, +)$ è un gruppo abeliano (neutro: $0$, inverso additivo: $-a$)
> 2. $(C \setminus \{0\}, \times)$ è un gruppo abeliano (neutro: $1$, inverso moltiplicativo: $a^{-1}$)
> 3. $\times$ è distributiva rispetto a $+$

---

### **3. Esempi di campi e non-campi**

| Struttura | Campo? | Motivazione |
|-----------|--------|-------------|
| $(\mathbb{Q}, +, \times)$ | **Sì** | Ogni $q \neq 0$ ha inverso $1/q \in \mathbb{Q}$ |
| $(\mathbb{R}, +, \times)$ | **Sì** | Ogni $r \neq 0$ ha inverso $1/r \in \mathbb{R}$ |
| $(\mathbb{N}, +, \times)$ | **No** | $(\mathbb{N}, +)$ non è gruppo (mancano inversi additivi) |
| $(\mathbb{Z}, +, \times)$ | **No** | Mancano inversi moltiplicativi per $|n| > 1$ |
| $(\mathbb{Z}_p, +, \times)$ | **Sì** se $p$ primo | Ogni $[a] \neq [0]$ è invertibile (già visto in M1) |

> ⚠️ $\mathbb{Z}_n$ con $n$ **composto** non è un campo: se $n = mk$ con $1 < m, k < n$, allora $[m]$ e $[k]$ sono divisori di zero ($[m] \times [k] = [0]$) e non sono invertibili.

---

### **4. Proprietà dei campi**

#### **4.1. Lo zero è assorbente**

> 📌 Per ogni $a \in C$: $a \times 0 = 0$.

*Dimostrazione:*
$$a \times 0 = a \times (0 + 0) = a \times 0 + a \times 0$$
Sottraendo $a \times 0$ da entrambi i lati: $0 = a \times 0$. $\square$

#### **4.2. Il prodotto con il negativo**

$$-(a \times b) = (-a) \times b = a \times (-b)$$

*Dimostrazione:*
$$a \times b + (-a) \times b = (a + (-a)) \times b = 0 \times b = 0$$
Quindi $(-a) \times b$ è l'inverso additivo di $a \times b$. $\square$

#### **4.3. Assenza di divisori dello zero**

> 📌 In un campo: $a \times b = 0 \implies a = 0 \lor b = 0$.

*Dimostrazione:* Se $a \neq 0$, allora $a$ è invertibile; moltiplicando per $a^{-1}$: $b = a^{-1} \times 0 = 0$. $\square$

#### **4.4. Legge di cancellazione**

$$a \times b = a \times c \text{ e } a \neq 0 \implies b = c$$

*Dimostrazione:* $a \times (b - c) = 0$; poiché $a \neq 0$, si ha $b - c = 0$, cioè $b = c$. $\square$

#### **4.5. Equazione lineare**

$$ax + b = cx + d \text{ con } a \neq c \implies x = (d - b)(a - c)^{-1}$$

---

### **5. Sottocampo e omomorfismo di campi**

> 📌 **Sottocampo:** $K \subseteq C$ è un **sottocampo** di $(C, +, \times)$ se $(K, +, \times)$ è esso stesso un campo.

> 📌 **Omomorfismo di campi:** $f: C \to D$ è un **omomorfismo di campi** se preserva entrambe le operazioni:
> $$f(a + b) = f(a) + f(b), \qquad f(a \times b) = f(a) \times f(b)$$

---

### **6. Definizione di anello**

I campi richiedono inversi moltiplicativi per tutti gli elementi non nulli. Rilassando questa condizione si ottiene la nozione di **anello**.

> 📌 **Definizione:** Una struttura $(A, +, \times)$ è un **anello** se:
> 1. $(A, +)$ è un **gruppo abeliano** (neutro $0$, inverso $-a$)
> 2. $\times$ è una legge di composizione **interna** e **associativa** su $A$
> 3. $\times$ è **distributiva** rispetto a $+$

> 💡 Un anello non richiede né commutatività del prodotto né esistenza dell'inverso moltiplicativo né esistenza di un neutro moltiplicativo. Si parla di **anello commutativo** se $\times$ è commutativa, di **anello con unità** se esiste $1$.

---

### **7. Esempi di anelli**

| Struttura | Anello | Note |
|-----------|--------|------|
| $(\mathbb{Z}, +, \times)$ | **Sì** | Commutativo, con unità $1$, senza divisori di zero |
| $(\mathbb{Z}_n, +, \times)$ | **Sì** | Commutativo, con unità; ha divisori di zero se $n$ composto |
| Matrici $M_{n \times n}(\mathbb{R})$ | **Sì** | Non commutativo; vedremo in L3 |
| Polinomi $A[x]$ | **Sì** | Commutativo (se $A$ campo); vedremo in L2 |

#### **7.1. Divisori di zero in $\mathbb{Z}_n$**

Se $n$ è composto, $n = m \cdot k$ con $1 < m, k < n$, allora $[m] \times [k] = [0]$ con $[m] \neq [0]$ e $[k] \neq [0]$: **esistono divisori di zero**.

Se $n$ è primo, $\mathbb{Z}_n$ è un campo e non ha divisori di zero.

> ⚠️ La presenza di divisori di zero impedisce la legge di cancellazione: $[m] \times [k] = [m] \times [0]$ non implica $[k] = [0]$ se $[m]$ è divisore di zero.

---

> ✅ Un campo ha due strutture di gruppo abeliano (additiva e moltiplicativa senza lo zero) legate dalla distributività; è privo di divisori di zero e ammette equazioni lineari univocamente risolubili. Un anello rilassa le condizioni sul prodotto (niente inversi, non necessariamente commutativo). $\mathbb{Z}_p$ è un campo iff $p$ è primo; $\mathbb{Z}_n$ con $n$ composto ha divisori di zero.
