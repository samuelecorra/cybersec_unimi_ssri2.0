# **M1 UD3 Lezione 4 - Il teorema cinese del resto**

### **1. Il problema delle uova**

Il teorema cinese del resto ha origini antiche. Nella matematica cinese classica (III-IV secolo d.C.) compare il seguente problema:

> *Un contadino trasporta un certo numero di uova. Se le conta a gruppi di 3 ne avanzano 2; a gruppi di 5 ne avanzano 3; a gruppi di 7 ne avanzano 2. Quante uova ha?*

Questo si traduce nel sistema:

$$\begin{cases} x \equiv 2 \pmod{3} \\ x \equiv 3 \pmod{5} \\ x \equiv 2 \pmod{7} \end{cases}$$

La risposta è $x = 23$ (o più in generale $23 + 105k$ per $k \geq 0$, poiché $3 \cdot 5 \cdot 7 = 105$).

---

### **2. Enunciato del teorema**

> 📌 **Teorema Cinese del Resto (TCR):** Siano $n_1, n_2, \ldots, n_h$ interi positivi a **due a due coprimi** (cioè $\text{MCD}(n_i, n_j) = 1$ per ogni $i \neq j$), e siano $a_1, a_2, \ldots, a_h$ interi arbitrari. Allora il sistema:
> $$\begin{cases} x \equiv a_1 \pmod{n_1} \\ x \equiv a_2 \pmod{n_2} \\ \vdots \\ x \equiv a_h \pmod{n_h} \end{cases}$$
> ha un'**unica soluzione** modulo $M = n_1 \cdot n_2 \cdots n_h$.

> ⚠️ La coprimità dei moduli è essenziale per garantire l'esistenza e l'unicità della soluzione. Se i moduli non sono coprimi a due a due, il sistema può avere zero o più soluzioni.

---

### **3. Metodo costruttivo di risoluzione**

**Passo 1:** Calcolare $M = n_1 \cdot n_2 \cdots n_h$.

**Passo 2:** Per ogni $i$, calcolare $M_i = M / n_i$ (prodotto di tutti i moduli tranne $n_i$).

**Passo 3:** Poiché $\text{MCD}(M_i, n_i) = 1$, trovare l'inverso $s_i$ di $M_i$ modulo $n_i$ tramite Bézout:
$$M_i s_i \equiv 1 \pmod{n_i}$$

**Passo 4:** La soluzione è:
$$x_0 = \sum_{i=1}^{h} a_i M_i s_i$$

La soluzione generale è $x_0 + kM$ per ogni $k \in \mathbb{Z}$, e la soluzione minima positiva è $x_0 \bmod M$.

---

### **4. Esempio completo**

**Esempio:** Risolvere il sistema:

$$\begin{cases} x \equiv 2 \pmod{3} \\ x \equiv 3 \pmod{4} \\ x \equiv 1 \pmod{5} \end{cases}$$

**Passo 1:** $M = 3 \cdot 4 \cdot 5 = 60$.

I moduli sono coprimi a due a due? $\text{MCD}(3,4)=1$, $\text{MCD}(3,5)=1$, $\text{MCD}(4,5)=1$. ✓

**Passo 2:**
- $M_1 = 60/3 = 20$
- $M_2 = 60/4 = 15$
- $M_3 = 60/5 = 12$

**Passo 3:** Trovare gli inversi.

$s_1$: inverso di $20$ modulo $3$. $20 \equiv 2 \pmod 3$, e $2 \cdot 2 = 4 \equiv 1 \pmod 3$, quindi $s_1 = 2$.

$s_2$: inverso di $15$ modulo $4$. $15 \equiv 3 \pmod 4$, e $3 \cdot 3 = 9 \equiv 1 \pmod 4$, quindi $s_2 = 3$.

$s_3$: inverso di $12$ modulo $5$. $12 \equiv 2 \pmod 5$, e $2 \cdot 3 = 6 \equiv 1 \pmod 5$, quindi $s_3 = 3$.

**Passo 4:**

$$x_0 = a_1 M_1 s_1 + a_2 M_2 s_2 + a_3 M_3 s_3 = 2 \cdot 20 \cdot 2 + 3 \cdot 15 \cdot 3 + 1 \cdot 12 \cdot 3$$
$$= 80 + 135 + 36 = 251$$

Soluzione minima positiva: $251 \bmod 60 = 251 - 4 \cdot 60 = 251 - 240 = 11$.

**Verifica:**
- $11 = 3 \cdot 3 + 2 \equiv 2 \pmod 3$ ✓
- $11 = 2 \cdot 4 + 3 \equiv 3 \pmod 4$ ✓
- $11 = 2 \cdot 5 + 1 \equiv 1 \pmod 5$ ✓

---

### **5. Il problema cinese originale**

Il problema del contadino cinese con i moduli $3, 4, 5, 7$... aspetta: il problema classico usa $3, 5, 7$.

$$\begin{cases} x \equiv 2 \pmod{3} \\ x \equiv 3 \pmod{5} \\ x \equiv 2 \pmod{7} \end{cases}$$

$M = 3 \cdot 5 \cdot 7 = 105$, $M_1 = 35$, $M_2 = 21$, $M_3 = 15$.

Inversi: $35 \equiv 2 \pmod 3 \Rightarrow s_1 = 2$; $21 \equiv 1 \pmod 5 \Rightarrow s_2 = 1$; $15 \equiv 1 \pmod 7 \Rightarrow s_3 = 1$.

$$x_0 = 2 \cdot 35 \cdot 2 + 3 \cdot 21 \cdot 1 + 2 \cdot 15 \cdot 1 = 140 + 63 + 30 = 233$$

$233 \bmod 105 = 233 - 2 \cdot 105 = 23$.

La risposta è **23 uova** (o $23 + 105k$). ✓

---

> ✅ Il TCR garantisce l'esistenza e l'unicità della soluzione di un sistema di congruenze con moduli coprimi a due a due. Il metodo costruttivo usa gli inversi di $M/n_i$ modulo $n_i$ (calcolati con Bézout) per costruire la soluzione come combinazione lineare. La soluzione è unica modulo $M = n_1 n_2 \cdots n_h$.
