# **M2 UD1 Lezione 4 - Il gruppo simmetrico**

### **1. Permutazioni**

> 📌 **Definizione:** Dato un insieme finito $X = \{1, 2, \ldots, n\}$, una **permutazione** di $X$ è una **biiezione** $p: X \to X$.

Le permutazioni si notano con la scrittura a **due righe**:

$$p = \begin{pmatrix} 1 & 2 & 3 & \cdots & n \\ p(1) & p(2) & p(3) & \cdots & p(n) \end{pmatrix}$$

**Esempio in $S_5$:**

$$p = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 3 & 5 & 1 & 2 & 4 \end{pmatrix}$$

cioè $p(1)=3$, $p(2)=5$, $p(3)=1$, $p(4)=2$, $p(5)=4$.

---

### **2. Il gruppo simmetrico $S_n$**

> 📌 **Definizione:** $S_n$ è l'insieme di tutte le permutazioni di $X = \{1, 2, \ldots, n\}$, con l'operazione di **composizione**:
> $$(p_1 \circ p_2)(x) = p_1(p_2(x))$$

> ⚠️ La composizione si legge **da destra a sinistra**: si applica prima $p_2$, poi $p_1$.

> 📌 **Teorema:** $|S_n| = n!$

*Dimostrazione:* Il numero di biiezioni da $\{1, \ldots, n\}$ a sé stesso è $n \cdot (n-1) \cdots 2 \cdot 1 = n!$. $\square$

> 📌 **Teorema:** $S_n$ è **non commutativo** per $n > 2$.

*Dimostrazione:* Controesempio in $S_3$: prendere $p_1 = (1\;2)$ e $p_2 = (2\;3)$; si verifica che $p_1 \circ p_2 \neq p_2 \circ p_1$. $\square$

---

### **3. Permutazione inversa**

Per calcolare $p^{-1}$: si **scambia** la riga superiore con quella inferiore e si riordina la prima riga.

**Esempio:** Per $p = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 3 & 5 & 1 & 2 & 4 \end{pmatrix}$, prima si scambia:

$$\begin{pmatrix} 3 & 5 & 1 & 2 & 4 \\ 1 & 2 & 3 & 4 & 5 \end{pmatrix}$$

poi si riordina la prima riga in senso crescente:

$$p^{-1} = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 3 & 4 & 1 & 5 & 2 \end{pmatrix}$$

Verifica: $p \circ p^{-1} = \text{id}$. ✓

---

### **4. Cicli**

> 📌 **Definizione:** Un **ciclo di lunghezza $m$** è la permutazione che manda:
> $$a_1 \mapsto a_2, \quad a_2 \mapsto a_3, \quad \ldots, \quad a_{m-1} \mapsto a_m, \quad a_m \mapsto a_1$$
> e lascia fissi tutti gli altri elementi. Si scrive: $(a_1 \; a_2 \; \cdots \; a_m)$.

Un ciclo di lunghezza $2$ si chiama **trasposizione**.

**Proprietà:**
- I cicli **disgiunti** (senza elementi in comune) **commutano**: $(a_1 \cdots a_k)(b_1 \cdots b_j) = (b_1 \cdots b_j)(a_1 \cdots a_k)$

---

### **5. Decomposizione in cicli disgiunti**

> 📌 **Teorema:** Ogni permutazione si scrive in modo **unico** (a meno dell'ordine) come prodotto di **cicli disgiunti**.

**Algoritmo costruttivo:**
1. Parto dall'elemento più piccolo $a_1$ non ancora assegnato.
2. Seguo l'orbita: $a_1 \to p(a_1) \to p^2(a_1) \to \ldots$ finché non torno ad $a_1$.
3. Scrivo il ciclo $(a_1 \; p(a_1) \; \ldots)$.
4. Ripeto con il prossimo elemento non assegnato.
5. Gli elementi fissi (con $p(a) = a$) danno cicli di lunghezza $1$, di solito omessi.

**Esempio in $S_7$:**

$$p = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 & 6 & 7 \\ 4 & 7 & 5 & 1 & 3 & 2 & 6 \end{pmatrix}$$

- Da $1$: $1 \to 4 \to 1$ → ciclo $(1 \; 4)$
- Da $2$: $2 \to 7 \to 6 \to 2$ → ciclo $(2 \; 7 \; 6)$
- Da $3$: $3 \to 5 \to 3$ → ciclo $(3 \; 5)$

Risultato: $p = (1\;4)(2\;7\;6)(3\;5)$.

**Esempio in $S_9$:**

$$p = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 & 6 & 7 & 8 & 9 \\ 3 & 1 & 8 & 5 & 9 & 6 & 4 & 2 & 7 \end{pmatrix}$$

- Da $1$: $1 \to 3 \to 8 \to 2 \to 1$ → ciclo $(1\;3\;8\;2)$
- Da $4$: $4 \to 5 \to 9 \to 7 \to 4$ → ciclo $(4\;5\;9\;7)$
- $6 \to 6$: punto fisso (omesso)

Risultato: $p = (1\;3\;8\;2)(4\;5\;9\;7)$.

---

### **6. Trasposizioni e parità**

> 📌 **Teorema:** Ogni permutazione si scrive come prodotto di **trasposizioni**.

**Formula per scomporre un ciclo in trasposizioni:**

$$(a_1 \; a_2 \; \cdots \; a_k) = (a_1 \; a_k)(a_1 \; a_{k-1}) \cdots (a_1 \; a_2)$$

**Esempio:** $(1\;4\;2\;6\;7\;3\;8\;5) = (1\;5)(1\;8)(1\;3)(1\;7)(1\;6)(1\;2)(1\;4)$.

> 📌 **Parità di una permutazione:** Sebbene la decomposizione in trasposizioni non sia unica, il **numero di trasposizioni** nella decomposizione ha sempre la **stessa parità** (pari o dispari). Una permutazione è:
> - **pari** se si scrive come prodotto di un numero pari di trasposizioni
> - **dispari** se si scrive come prodotto di un numero dispari di trasposizioni

> ⚠️ Una stessa permutazione può avere decomposizioni in trasposizioni di lunghezze diverse, ma sempre tutte pari o tutte dispari.

---

### **7. Periodo di una permutazione**

> 📌 **Periodo di un ciclo di lunghezza $m$:** il periodo è $m$.

> 📌 **Periodo di un prodotto di cicli disgiunti:** è il **minimo comune multiplo** delle lunghezze dei cicli.

**Esempio 1:** $P = (1\;4\;2\;6\;7\;3\;8\;5)$ è un ciclo di lunghezza $8$. Periodo = $8$.

Potenze di $P$:

| $k$ | $P^k$ |
|-----|-------|
| $1$ | $(1\;4\;2\;6\;7\;3\;8\;5)$ |
| $2$ | $(1\;2\;7\;8)(4\;6\;3\;5)$ |
| $3$ | $(1\;6\;8\;4\;7\;2\;3\;5)$ ← ciclo inverso di $P^5$ |
| $4$ | $(1\;7)(2\;8)(4\;3)(6\;5)$ |
| $5$ | ... |
| $7$ | $P^{-1}$ |
| $8$ | $\text{id}$ |

**Esempio 2:** $p = (1\;3\;5\;2)(4\;6\;7)$, cicli di lunghezze $4$ e $3$.

$$\text{periodo} = \text{mcm}(4, 3) = 12$$

**Esempio 3:** $p = (1\;4\;2\;7\;5\;3)$ ha periodo $6$; $q = (1\;4)(2\;6)$ ha periodo $2$.

I prodotti $pq$ e $qp$ vanno calcolati componendo le permutazioni e verificando che in generale $pq \neq qp$ (gruppo non commutativo).

---

> ✅ $S_n$ è il gruppo delle biiezioni di $\{1,\ldots,n\}$ con la composizione; ha ordine $n!$ ed è non commutativo per $n>2$. Ogni permutazione si decompone in modo unico in cicli disgiunti (che commutano). La decomposizione in trasposizioni non è unica ma la parità è invariante. Il periodo di un prodotto di cicli disgiunti è il mcm delle loro lunghezze.
