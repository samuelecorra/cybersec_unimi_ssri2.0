# **M2 UD2 Lezione 3 - Anello delle matrici**

### **1. Vettori**

Sia $K$ un campo. Un **vettore riga** di lunghezza $n$ è una $n$-upla ordinata:

$$\mathbf{a} = [a_1, a_2, \ldots, a_n], \qquad a_i \in K$$

Un **vettore colonna** di lunghezza $n$ è:

$$\mathbf{b} = \begin{bmatrix} b_1 \\ b_2 \\ \vdots \\ b_n \end{bmatrix}, \qquad b_i \in K$$

Gli $a_i$ (e $b_i$) si chiamano **componenti** del vettore.

**Somma di vettori** (stessa lunghezza, componente per componente):

$$[a_1, \ldots, a_n] + [b_1, \ldots, b_n] = [a_1 + b_1, \ldots, a_n + b_n]$$

---

### **2. Prodotto scalare (riga per colonna)**

Il **prodotto scalare** tra un vettore riga $[a_1, \ldots, a_n]$ e un vettore colonna $[b_1, \ldots, b_n]^T$ restituisce uno scalare in $K$:

$$[a_1, \ldots, a_n] \cdot \begin{bmatrix} b_1 \\ \vdots \\ b_n \end{bmatrix} = \sum_{i=1}^n a_i b_i = a_1 b_1 + a_2 b_2 + \cdots + a_n b_n$$

---

### **3. Definizione di matrice**

> 📌 **Definizione:** Una **matrice** $n \times m$ a coefficienti in $K$ è una tabella rettangolare con $n$ righe e $m$ colonne:
> $$A = [a_{ik}]_{\substack{i = 1, \ldots, n \\ k = 1, \ldots, m}}, \qquad a_{ik} \in K$$
> dove $i$ indica la **riga** e $k$ la **colonna**.

L'insieme di tutte le matrici $n \times m$ a coefficienti in $K$ si denota $M_{n \times m}[K]$.

---

### **4. Addizione di matrici**

La somma è definita solo tra matrici della **stessa dimensione** ($n \times m$), componente per componente:

$$[a_{ik}] + [b_{ik}] = [a_{ik} + b_{ik}]$$

**Struttura di $(M_{n \times m}[K], +)$:**

| Proprietà | Elemento |
|-----------|----------|
| Neutro | Matrice zero $O$ (tutti gli elementi nulli) |
| Inverso (opposto) | $-A = [-a_{ik}]$ |
| Commutatività | Sì |

> 📌 $(M_{n \times m}[K], +)$ è un **gruppo abeliano**.

**Esempio:** Addizione in $M_{2 \times 2}[\mathbb{R}]$:

$$\begin{bmatrix} 1 & 2 \\ 3 & 4 \end{bmatrix} + \begin{bmatrix} 5 & -1 \\ 0 & 2 \end{bmatrix} = \begin{bmatrix} 6 & 1 \\ 3 & 6 \end{bmatrix}$$

---

### **5. Prodotto di matrici (righe per colonne)**

Il prodotto $A \cdot B$ è definito solo quando il numero di **colonne di $A$** è uguale al numero di **righe di $B$**:

$$A \in M_{m \times n}[K], \quad B \in M_{n \times p}[K] \implies C = A \cdot B \in M_{m \times p}[K]$$

L'elemento $(i, k)$ della matrice prodotto $C$ è il prodotto scalare della $i$-esima riga di $A$ per la $k$-esima colonna di $B$:

$$c_{ik} = \sum_{j=1}^n a_{ij} \cdot b_{jk}$$

---

### **6. Proprietà del prodotto di matrici**

> 📌 Il prodotto di matrici è **associativo**:
> $$(AB)C = A(BC)$$

> ⚠️ Il prodotto di matrici è in generale **non commutativo**: $AB \neq BA$.

Questo vale anche quando entrambe le dimensioni sono compatibili con l'inversione dell'ordine (es. matrici quadrate $n \times n$): $AB$ e $BA$ possono differire.

---

### **7. Anello delle matrici quadrate**

> 📌 **Teorema:** $(M_{n \times n}[K], +, \cdot)$ è un **anello non commutativo con unità**.

L'unità moltiplicativa è la **matrice identità** $I_n = [\delta_{ik}]$ dove:

$$\delta_{ik} = \begin{cases} 1 & \text{se } i = k \\ 0 & \text{se } i \neq k \end{cases}$$

Esempio per $n = 3$:

$$I_3 = \begin{bmatrix} 1 & 0 & 0 \\ 0 & 1 & 0 \\ 0 & 0 & 1 \end{bmatrix}$$

**Proprietà:** $A \cdot I_n = I_n \cdot A = A$ per ogni $A \in M_{n \times n}[K]$.

---

### **8. Matrice inversa**

Non tutte le matrici quadrate sono invertibili. Per le matrici $2 \times 2$:

$$A = \begin{bmatrix} a & b \\ c & d \end{bmatrix}, \qquad A^{-1} = \frac{1}{\Delta} \begin{bmatrix} d & -b \\ -c & a \end{bmatrix}, \qquad \Delta = ad - bc$$

$A$ è invertibile se e solo se $\Delta = ad - bc$ è **invertibile** nel campo $K$.

#### **8.1. Esempio su $\mathbb{R}$**

$$A = \begin{bmatrix} 3 & 1 \\ 1 & 4 \end{bmatrix}, \qquad \Delta = 12 - 1 = 11$$

$$A^{-1} = \frac{1}{11} \begin{bmatrix} 4 & -1 \\ -1 & 3 \end{bmatrix} = \begin{bmatrix} 4/11 & -1/11 \\ -1/11 & 3/11 \end{bmatrix}$$

**Verifica:** $A \cdot A^{-1} = I_2$. ✓

#### **8.2. Esempio su $\mathbb{Z}_7$**

$$A = \begin{bmatrix} 3 & 5 \\ 2 & 4 \end{bmatrix} \in M_{2 \times 2}[\mathbb{Z}_7], \qquad \Delta = 3 \cdot 4 - 5 \cdot 2 = 12 - 10 = 2 \pmod{7}$$

Serve $\Delta^{-1}$ in $\mathbb{Z}_7$: $2 \cdot 4 = 8 \equiv 1 \pmod 7$, quindi $\Delta^{-1} = [4]$.

$$A^{-1} = 4 \cdot \begin{bmatrix} 4 & -5 \\ -2 & 3 \end{bmatrix} = 4 \cdot \begin{bmatrix} 4 & 2 \\ 5 & 3 \end{bmatrix} = \begin{bmatrix} 16 & 8 \\ 20 & 12 \end{bmatrix} \equiv \begin{bmatrix} 2 & 1 \\ 6 & 5 \end{bmatrix} \pmod 7$$

#### **8.3. Esempio su $\mathbb{Z}_{11}$**

$$A = \begin{bmatrix} 1 & 3 \\ 2 & 7 \end{bmatrix} \in M_{2 \times 2}[\mathbb{Z}_{11}], \qquad \Delta = 7 - 6 = 1$$

Poiché $\Delta = 1$ è già invertibile con $\Delta^{-1} = 1$:

$$A^{-1} = 1 \cdot \begin{bmatrix} 7 & -3 \\ -2 & 1 \end{bmatrix} = \begin{bmatrix} 7 & -3 \\ -2 & 1 \end{bmatrix} \equiv \begin{bmatrix} 7 & 8 \\ 9 & 1 \end{bmatrix} \pmod{11}$$

(poiché $-3 \equiv 8$ e $-2 \equiv 9$ modulo $11$).

---

> ✅ Le matrici $n \times m$ a coefficienti in un campo $K$ formano un gruppo abeliano rispetto alla somma. Il prodotto righe-per-colonne è definito quando le dimensioni interne coincidono, è associativo ma non commutativo. $(M_{n \times n}[K], +, \cdot)$ è un anello non commutativo con unità $I_n$. La matrice $2 \times 2$ è invertibile sse il suo determinante $\Delta = ad - bc$ è invertibile in $K$; la formula dell'inversa si adatta al campo (reali, $\mathbb{Z}_p$, ecc.).
