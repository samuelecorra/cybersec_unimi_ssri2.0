# **M1 UD3 Lezione 6 - Teoremi di Wilson, Fermat e Eulero-Fermat**

### **1. Teorema di Wilson**

> 📌 **Teorema di Wilson:** $p$ è primo se e solo se:
> $$(p-1)! \equiv -1 \equiv p-1 \pmod{p}$$

Il teorema fornisce una **caratterizzazione** dei numeri primi tramite il fattoriale.

**Esempi:**

- $p = 7$:
$$6! = (6 \cdot 5)(4 \cdot 3 \cdot 2) \equiv 2 \cdot 3 \equiv 6 \equiv -1 \pmod{7} \quad ✓$$

- $p = 11$:
$$10! = (10 \cdot 9)(8 \cdot 7)(6 \cdot 5)(4 \cdot 3 \cdot 2) \equiv 2 \cdot 1 \cdot 8 \cdot 2 \equiv 10 \equiv -1 \pmod{11} \quad ✓$$

> 💡 Per calcolare $6! \bmod 7$ non è necessario calcolare $6! = 720$. Si usano l'associatività del prodotto e la riduzione modulare dei fattori: $(6 \cdot 1) \cdot (5 \cdot 3) \cdot (4 \cdot 2) = 6 \cdot 15 \cdot 8 \equiv 6 \cdot 1 \cdot 1 = 6 \pmod{7}$.

**Verifica su numeri non primi:**

- $n = 8$: $7! = (7 \cdot 6)(5 \cdot 4)(3 \cdot 2) \equiv 2 \cdot 4 \cdot 6 \equiv 0 \pmod{8}$ (non $\equiv -1$) ✓
- $n = 12$: $11! = 11 \cdot 10 \cdot 9 \cdot 8 \cdots \equiv 0 \pmod{12}$ (l'8 introduce il fattore 4 e il 12 introduce 3) ✓

---

### **2. Algoritmo da Wilson (e perché non usarlo)**

Dal teorema di Wilson si può derivare un algoritmo per testare la primalità:

```
leggi(p)
a := 1
per i := 2 fino a p-1 fai
    a := (a · i) mod p
se a = p-1 scrivi "è primo"
altrimenti scrivi "è composto"
```

> ⚠️ Questo algoritmo è **teoricamente corretto** ma **praticamente inutilizzabile** per due motivi:
> 1. Il prodotto $a \cdot i$ diventa presto molto grande (anche se $a, i < p$, il prodotto può avere cifre dell'ordine di $p^2$)
> 2. Il numero di operazioni è dell'ordine di $2p$, mentre il metodo classico (controllare i divisori fino a $\sqrt{p}$) richiede $O(\sqrt{p})$ operazioni — per $p = 100$: $200$ contro $10$!

---

### **3. Teorema di Fermat (piccolo)**

> 📌 **Teorema di Fermat (piccolo):** Se $p$ è primo e $\text{MCD}(a, p) = 1$ (cioè $p \nmid a$), allora:
> $$a^{p-1} \equiv 1 \pmod{p}$$

**Corollario — calcolo dell'inverso:** Poiché $a^{p-1} = a \cdot a^{p-2} \equiv 1 \pmod{p}$:

$$a^{-1} \equiv a^{p-2} \pmod{p}$$

L'inverso di $a$ in $\mathbb{Z}_p$ è $a^{p-2}$.

**Esempio:** $p = 11$, $a = 7$. Trovare $7^{-1}$ in $\mathbb{Z}_{11}$.

$$7^{-1} \equiv 7^{9} \pmod{11}$$

Calcoliamo con quadrature successive:

$$7^2 = 49 \equiv 5 \pmod{11}$$
$$7^4 = (7^2)^2 \equiv 5^2 = 25 \equiv 3 \pmod{11}$$
$$7^8 = (7^4)^2 \equiv 3^2 = 9 \pmod{11}$$
$$7^9 = 7^8 \cdot 7 \equiv 9 \cdot 7 = 63 \equiv 8 \pmod{11}$$

Verifica: $7 \cdot 8 = 56 = 5 \cdot 11 + 1 \equiv 1 \pmod{11}$. ✓

---

### **4. Teorema di Eulero-Fermat**

Il teorema di Fermat si generalizza a moduli non necessariamente primi tramite la **funzione di Eulero** $\varphi$.

> 📌 **Funzione di Eulero:** $\varphi(n)$ è il numero di interi tra $1$ e $n$ che sono coprimi con $n$:
> $$\varphi(n) = |\{k \in \mathbb{N} : 1 \leq k \leq n,\; \text{MCD}(k,n) = 1\}|$$

**Esempi:**
- $\varphi(7) = 6$ (7 è primo: tutti gli interi $1, \ldots, 6$ sono coprimi con 7)
- $\varphi(8) = 4$ (i coprimi con 8 tra 1 e 8 sono $1, 3, 5, 7$)
- $\varphi(12) = 4$ (i coprimi con 12 tra 1 e 12 sono $1, 5, 7, 11$)
- Per $p$ primo: $\varphi(p) = p - 1$

> 📌 **Teorema di Eulero-Fermat:** Se $\text{MCD}(a, n) = 1$, allora:
> $$a^{\varphi(n)} \equiv 1 \pmod{n}$$

Il teorema di Fermat è il caso speciale $n = p$ primo (con $\varphi(p) = p-1$).

---

### **5. Riduzione dell'esponente**

I due teoremi consentono di calcolare potenze enormi molto rapidamente, riducendo l'esponente:

- Se $p$ è **primo**: l'esponente si può ridurre **modulo $p-1$**
- Se $n$ **non è primo**: l'esponente si può ridurre **modulo $\varphi(n)$**

> ⚠️ Questo funziona solo quando la **base è coprima con il modulo**. Se $\text{MCD}(a, n) > 1$, bisogna trattare il caso separatamente.

---

### **6. Esempio di calcolo con i teoremi**

**Calcolare $5^{328} \bmod 7$ e $5^{328} \bmod 8$.**

**Caso $\bmod 7$ (primo):**

$7$ è primo, vale Fermat: $5^6 \equiv 1 \pmod{7}$.

$$328 = 6 \cdot 54 + 4 \quad \Rightarrow \quad 5^{328} = (5^6)^{54} \cdot 5^4 \equiv 1^{54} \cdot 5^4 = 625 \pmod{7}$$

$$625 = 89 \cdot 7 + 2 \quad \Rightarrow \quad 5^{328} \equiv 2 \pmod{7}$$

**Caso $\bmod 8$ (non primo):**

$\varphi(8) = 4$ (coprimi con 8: $1, 3, 5, 7$).

$\text{MCD}(5, 8) = 1$, vale Eulero-Fermat: $5^4 \equiv 1 \pmod{8}$.

$$328 = 4 \cdot 82 \quad \Rightarrow \quad 5^{328} = (5^4)^{82} \equiv 1^{82} = 1 \pmod{8}$$

---

> ✅ Il teorema di Wilson ($p$ primo $\iff (p-1)! \equiv -1 \pmod p$) caratterizza i primi ma non è algoritmo pratico. Il piccolo teorema di Fermat ($a^{p-1} \equiv 1 \pmod p$ per $p$ primo e $p \nmid a$) permette di calcolare l'inverso come $a^{p-2}$ e di ridurre gli esponenti modulo $p-1$. Il teorema di Eulero-Fermat ($a^{\varphi(n)} \equiv 1 \pmod n$) generalizza Fermat a qualunque modulo, riducendo l'esponente modulo $\varphi(n)$.
