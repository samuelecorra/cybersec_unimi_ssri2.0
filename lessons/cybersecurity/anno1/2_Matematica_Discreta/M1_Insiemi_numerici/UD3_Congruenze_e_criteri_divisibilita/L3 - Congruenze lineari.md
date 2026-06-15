# **M1 UD3 Lezione 3 - Congruenze lineari**

### **1. Definizione e forma generale**

Una **congruenza lineare** è un'equazione della forma:

$$ax \equiv b \pmod{n}$$

dove $a, b, n$ sono interi fissati ($n \geq 1$) e $x$ è l'incognita in $\mathbb{Z}_n$.

L'obiettivo è trovare tutti i valori di $x$ (modulo $n$) che soddisfano la congruenza.

---

### **2. Proprietà fondamentali delle congruenze lineari**

**Proprietà 1:** Si possono ridurre i coefficienti modulo $n$.

$$ax \equiv b \pmod{n} \iff (a \bmod n) \cdot x \equiv (b \bmod n) \pmod{n}$$

Questo semplifica i calcoli quando $a$ o $b$ sono grandi.

**Proprietà 2:** Si può moltiplicare entrambi i membri per lo stesso fattore.

$$ax \equiv b \pmod{n} \Rightarrow kax \equiv kb \pmod{n}$$

**Proprietà 3 (cancellazione controllata):** Si può cancellare un fattore comune, ma il modulo si riduce.

$$kax \equiv kb \pmod{n} \text{ e } \text{MCD}(k, n) = 1 \Rightarrow ax \equiv b \pmod{n}$$

> ⚠️ Se $\text{MCD}(k, n) > 1$, la cancellazione **cambia il modulo**: $kax \equiv kb \pmod{n}$ diventa $ax \equiv b \pmod{n/\text{MCD}(k,n)}$. Questo è un caso delicato che può moltiplicare le soluzioni.

**Proprietà 4:** Una congruenza si può "spezzare" e "ricomporre" modulo divisori coprimi.

---

### **3. Condizione di esistenza delle soluzioni**

> 📌 **Teorema:** La congruenza $ax \equiv b \pmod{n}$ ha soluzioni se e solo se:
> $$d = \text{MCD}(a, n) \mid b$$
>
> In caso affermativo, esistono esattamente $d$ soluzioni distinte modulo $n$, che si trovano a distanza $n/d$ l'una dall'altra.

**Casi particolari:**
- Se $d = 1$ (cioè $\text{MCD}(a,n)=1$): esiste un'unica soluzione modulo $n$, data dall'inverso di $a$.
- Se $d > 1$ ma $d \nmid b$: nessuna soluzione.
- Se $d > 1$ e $d \mid b$: $d$ soluzioni distinte modulo $n$.

---

### **4. Metodo di risoluzione**

**Passo 1:** Calcolare $d = \text{MCD}(a, n)$.

**Passo 2:** Verificare se $d \mid b$. Se no, nessuna soluzione.

**Passo 3:** Dividere tutto per $d$: la congruenza equivale a $\frac{a}{d}x \equiv \frac{b}{d} \pmod{\frac{n}{d}}$.

**Passo 4:** Ora $\text{MCD}(a/d, n/d) = 1$, quindi $(a/d)$ è invertibile in $\mathbb{Z}_{n/d}$. Trovare l'inverso tramite Bézout e calcolare $x_0 = (a/d)^{-1} \cdot (b/d) \bmod (n/d)$.

**Passo 5:** Le $d$ soluzioni modulo $n$ sono: $x_0, x_0 + n/d, x_0 + 2n/d, \ldots, x_0 + (d-1) \cdot n/d$.

---

### **5. Esempio completo**

**Esempio:** Risolvere $42x \equiv 6 \pmod{15}$.

**Passo 1:** $d = \text{MCD}(42, 15)$.

$$42 = 2 \cdot 15 + 12 \quad \Rightarrow \quad \text{MCD}(42,15) = \text{MCD}(15,12)$$
$$15 = 1 \cdot 12 + 3 \quad \Rightarrow \quad \text{MCD}(15,12) = \text{MCD}(12,3)$$
$$12 = 4 \cdot 3 + 0 \quad \Rightarrow \quad \text{MCD}(12,3) = 3$$

Quindi $d = 3$.

**Passo 2:** $d = 3 \mid 6$? Sì. Avremo $d = 3$ soluzioni.

**Passo 3:** Dividiamo per $3$: $\dfrac{42}{3} x \equiv \dfrac{6}{3} \pmod{\dfrac{15}{3}}$, cioè $14x \equiv 2 \pmod{5}$.

**Passo 4:** Ridurre $14 \bmod 5 = 4$ e $2 \bmod 5 = 2$: la congruenza è $4x \equiv 2 \pmod 5$.

L'inverso di $4$ in $\mathbb{Z}_5$: $4 \cdot 4 = 16 \equiv 1 \pmod 5$, quindi $4^{-1} \equiv 4$.

$x_0 = 4 \cdot 2 = 8 \equiv 3 \pmod 5$.

**Passo 5:** Le $3$ soluzioni modulo $15$ sono:

$$x \equiv 3 \pmod{5} \quad \Rightarrow \quad x \in \{3, 8, 13\} \pmod{15}$$

**Verifica:**
- $42 \cdot 3 = 126 = 8 \cdot 15 + 6 \equiv 6 \pmod{15}$ ✓
- $42 \cdot 8 = 336 = 22 \cdot 15 + 6 \equiv 6 \pmod{15}$ ✓
- $42 \cdot 13 = 546 = 36 \cdot 15 + 6 \equiv 6 \pmod{15}$ ✓

---

> ✅ La congruenza $ax \equiv b \pmod n$ ha soluzioni se e solo se $\text{MCD}(a,n) \mid b$, e in tal caso le soluzioni sono $d = \text{MCD}(a,n)$ in numero, equidistanziate di $n/d$ modulo $n$. Il metodo di risoluzione si basa sull'identità di Bézout (per trovare l'inverso di $a/d$ in $\mathbb{Z}_{n/d}$) e sulla divisione di tutta la congruenza per $d$.
