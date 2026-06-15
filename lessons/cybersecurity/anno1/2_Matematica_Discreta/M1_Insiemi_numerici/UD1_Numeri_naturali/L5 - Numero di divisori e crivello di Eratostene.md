# **M1 UD1 Lezione 5 - Numero di divisori e crivello di Eratostene**

### **1. Contare i divisori di un numero**

Dato un numero $n \geq 1$, ci interessa sapere quanti divisori ha. La risposta si ricava direttamente dalla fattorizzazione in primi.

> 📌 **Teorema:** Se $n = p_1^{e_1} \cdot p_2^{e_2} \cdots p_k^{e_k}$, allora il numero di divisori di $n$ è:
> $$\tau(n) = (e_1 + 1)(e_2 + 1) \cdots (e_k + 1)$$

**Motivazione:** Ogni divisore $d$ di $n$ ha la forma $d = p_1^{f_1} \cdot p_2^{f_2} \cdots p_k^{f_k}$ dove $0 \leq f_i \leq e_i$ per ogni $i$. Il numero di scelte per $f_i$ è $e_i + 1$ (da $0$ a $e_i$ incluso). Per il principio moltiplicativo, il totale è $(e_1+1)(e_2+1)\cdots(e_k+1)$.

---

### **2. Esempi di calcolo di τ(n)**

**Esempio 1:** $n = 144$

$$144 = 2^4 \cdot 3^2$$

$$\tau(144) = (4+1)(2+1) = 5 \cdot 3 = 15$$

I 15 divisori di 144 sono: $1, 2, 3, 4, 6, 8, 9, 12, 16, 18, 24, 36, 48, 72, 144$.

**Esempio 2:** $n = 360$

$$360 = 2^3 \cdot 3^2 \cdot 5$$

$$\tau(360) = (3+1)(2+1)(1+1) = 4 \cdot 3 \cdot 2 = 24$$

**Esempio 3:** $n = p$ (primo)

$$\tau(p) = (1+1) = 2$$

Conferma la definizione: un primo ha esattamente 2 divisori.

**Esempio 4:** $n = p^k$

$$\tau(p^k) = k+1$$

**Esempio 5:** $n = p \cdot q$ con $p, q$ primi distinti

$$\tau(pq) = 2 \cdot 2 = 4$$

I 4 divisori sono $1, p, q, pq$.

> 💡 Un numero $n$ è un **quadrato perfetto** se e solo se $\tau(n)$ è dispari. Questo perché nella fattorizzazione $n = p_1^{e_1} \cdots p_k^{e_k}$, $n$ è un quadrato perfetto esattamente quando tutti gli $e_i$ sono pari, e in tal caso ogni $(e_i+1)$ è dispari, rendendo il prodotto dispari.

---

### **3. Il crivello di Eratostene**

Il **crivello di Eratostene** è un algoritmo sistematico per trovare tutti i numeri primi fino a un limite $N$ fissato. Fu inventato dal matematico greco **Eratostene di Cirene** nel III secolo a.C.

#### **3.1. Procedura**

1. Scrivi tutti i numeri da $2$ a $N$
2. Inizia dal primo numero non cancellato: è primo. Cancella tutti i suoi multipli (escluso sé stesso)
3. Passa al successivo numero non cancellato e ripeti
4. Termina quando il numero corrente supera $\sqrt{N}$
5. Tutti i numeri rimasti non cancellati sono primi

#### **3.2. Perché ci si ferma a $\sqrt{N}$?**

Se $n \leq N$ è composto, allora $n = a \cdot b$ con $a \leq b$, dunque $a \leq \sqrt{n} \leq \sqrt{N}$. Ciò significa che $n$ ha un divisore primo $\leq \sqrt{N}$, che sarà già stato usato per cancellarlo nelle iterazioni precedenti.

#### **3.3. Esempio: primi fino a 100**

Si procede nell'ordine: cancella i multipli di $2$ (tutti i pari $\geq 4$), poi i multipli di $3$ (quelli non già cancellati), poi i multipli di $5$, poi i multipli di $7$ (poiché $\sqrt{100} = 10$ e il prossimo primo sarebbe $11 > 10$, ci si ferma).

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

I numeri primi fino a 100 sono:
$$2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97$$

In totale 25 numeri primi nel range $[1, 100]$.

#### **3.4. Complessità**

Il crivello di Eratostene ha complessità $O(N \log \log N)$, molto più efficiente di verificare la primalità di ciascun numero singolarmente.

> 📌 Il crivello funziona perché ogni numero composto viene "colpito" almeno una volta durante la cancellazione dei multipli del suo fattore primo più piccolo. Non servono divisioni: si usano solo addizioni (o equivalentemente, si salta di $p$ in $p$).

---

> ✅ Il numero di divisori di $n = p_1^{e_1} \cdots p_k^{e_k}$ è $\tau(n) = (e_1+1)\cdots(e_k+1)$. Il crivello di Eratostene (III sec. a.C.) trova tutti i primi $\leq N$ cancellando successivamente i multipli di $2, 3, 5, 7, \ldots$ fino a $\sqrt{N}$.
