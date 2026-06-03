# **L1 – Verifica numerica che g è radice primitiva di $\mathbb{Z}_p^*$**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 1 appello (230922) con peso di **~4 punti** (parte della domanda 4a).
>
> **Studia prima:**
> - [UD1 / L2 – Scelta dei parametri](../UD1/L2%20-%20Scelta%20dei%20parametri.md) → definizione di generatore/radice primitiva di $\mathbb{Z}_p^*$; criterio efficiente basato sulla fattorizzazione di $p-1$; esempi per $p=11$

---

### **1. Il problema e il contesto d'esame**

L'appello 230922 contiene la seguente domanda:

> *"Sia $q = 19$ il numero primo comune e sia il generatore $g = 10$. Dimostrare che 10 è un generatore = radice primitiva di $\mathbb{Z}_{19}^*$."*

La lezione UD1/L2 fornisce già la definizione e il criterio teorico. Questa lezione fornisce i **due metodi di calcolo** con la **procedura passo-passo** per p=19, g=10.

---

### **2. Definizione e prerequisito**

> 📌 **Definizione:** $g$ è una **radice primitiva** (generatore) di $\mathbb{Z}_p^*$ se e solo se le potenze $g^1, g^2, \ldots, g^{p-1}$ generano **tutti** gli elementi di $\mathbb{Z}_p^* = \{1, 2, \ldots, p-1\}$.
>
> Equivalentemente: l'**ordine** di $g$ in $\mathbb{Z}_p^*$ è esattamente $p-1$.

Dato $p = 19$ (primo), il gruppo $\mathbb{Z}_{19}^* = \{1, 2, \ldots, 18\}$ ha ordine $p-1 = 18$.

---

### **3. Metodo 1 — Enumerazione completa delle potenze (naive)**

Il modo più diretto è calcolare $g^1, g^2, \ldots, g^{p-1} \bmod p$ e verificare che si ottengano tutti i valori $\{1, 2, \ldots, 18\}$.

**Calcolo di $10^i \bmod 19$ per $i = 1, \ldots, 18$:**

Ogni riga si ottiene moltiplicando la precedente per 10 e riducendo mod 19.

| $i$ | Calcolo | $10^i \bmod 19$ |
|-----|---------|-----------------|
| 1  | $10$                      | **10** |
| 2  | $10 \times 10 = 100 = 5 \times 19 + 5$ | **5** |
| 3  | $10 \times 5 = 50 = 2 \times 19 + 12$ | **12** |
| 4  | $10 \times 12 = 120 = 6 \times 19 + 6$ | **6** |
| 5  | $10 \times 6 = 60 = 3 \times 19 + 3$ | **3** |
| 6  | $10 \times 3 = 30 = 1 \times 19 + 11$ | **11** |
| 7  | $10 \times 11 = 110 = 5 \times 19 + 15$ | **15** |
| 8  | $10 \times 15 = 150 = 7 \times 19 + 17$ | **17** |
| 9  | $10 \times 17 = 170 = 8 \times 19 + 18$ | **18** |
| 10 | $10 \times 18 = 180 = 9 \times 19 + 9$ | **9** |
| 11 | $10 \times 9 = 90 = 4 \times 19 + 14$ | **14** |
| 12 | $10 \times 14 = 140 = 7 \times 19 + 7$ | **7** |
| 13 | $10 \times 7 = 70 = 3 \times 19 + 13$ | **13** |
| 14 | $10 \times 13 = 130 = 6 \times 19 + 16$ | **16** |
| 15 | $10 \times 16 = 160 = 8 \times 19 + 8$ | **8** |
| 16 | $10 \times 8 = 80 = 4 \times 19 + 4$ | **4** |
| 17 | $10 \times 4 = 40 = 2 \times 19 + 2$ | **2** |
| 18 | $10 \times 2 = 20 = 1 \times 19 + 1$ | **1** |

**Insieme dei valori generati:** $\{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18\} = \mathbb{Z}_{19}^*$

> ✅ Tutti i 18 elementi non nulli di $\mathbb{Z}_{19}$ sono stati generati. L'ordine di 10 è esattamente 18 = p-1. Quindi **10 è una radice primitiva di $\mathbb{Z}_{19}^*$**.

---

### **4. Metodo 2 — Criterio efficiente (fattorizzazione di p-1)**

Il metodo naive richiede $p-2$ moltiplicazioni. Per $p$ grande è impraticabile. Il metodo efficiente usa il seguente teorema (già in UD1/L2):

> 📌 **Teorema:** $g$ è un generatore di $\mathbb{Z}_p^*$ se e solo se, per ogni fattore primo $q_i$ di $p-1$:
> $$g^{(p-1)/q_i} \not\equiv 1 \pmod{p}$$

**Applicazione a $p=19$, $g=10$:**

**Passo 1 — Fattorizzazione di $p-1$:**
$$p - 1 = 18 = 2 \times 3^2$$

I fattori primi di 18 sono: $q_1 = 2$ e $q_2 = 3$.

**Passo 2 — Verifica per $q_1 = 2$:**

$$10^{18/2} = 10^9 \bmod 19$$

Calcolo rapido con esponenziazione binaria ($9 = 1001_2$):
$$10^1 = 10, \quad 10^2 = 5, \quad 10^4 = 25 \bmod 19 = 6, \quad 10^8 = 36 \bmod 19 = 17$$
$$10^9 = 10^8 \cdot 10^1 = 17 \cdot 10 = 170 = 8 \times 19 + 18 \equiv 18 \pmod{19}$$

Poiché $18 \ne 1$: $10^9 \not\equiv 1 \pmod{19}$ ✓

**Passo 3 — Verifica per $q_2 = 3$:**

$$10^{18/3} = 10^6 \bmod 19$$

$$10^6 = 10^4 \cdot 10^2 = 6 \cdot 5 = 30 = 1 \times 19 + 11 \equiv 11 \pmod{19}$$

Poiché $11 \ne 1$: $10^6 \not\equiv 1 \pmod{19}$ ✓

**Entrambe le condizioni sono soddisfatte** → **10 è una radice primitiva di $\mathbb{Z}_{19}^*$**.

> 💡 **Vantaggio del metodo 2:** invece di 17 moltiplicazioni (metodo naive), bastano 2 verifiche — una per ogni fattore primo di $p-1$. Per $p$ grande con pochi fattori primi, questo è enormemente più efficiente.

---

### **5. Confronto tra i due metodi**

| Aspetto | Metodo 1 (naive) | Metodo 2 (efficiente) |
|---------|-----------------|----------------------|
| Procedura | Calcola $g^1, \ldots, g^{p-1} \bmod p$ | Fattorizza $p-1$; controlla $g^{(p-1)/q_i} \ne 1$ |
| Numero di operazioni | $p-2$ moltiplicazioni | $\lfloor \log_2(p-1) \rfloor$ per ogni fattore primo |
| Utile per | $p$ piccolo (es. all'esame) | $p$ grande (pratica reale) |
| Output | Tabella completa di tutte le potenze | Solo due valori da verificare |
| Richiede | Solo aritmetica modulare | Fattorizzazione di $p-1$ |

> ⚠️ **All'esame**, con $p = 19$, entrambi i metodi sono accettabili. Il metodo 2 è più rapido da scrivere (2 calcoli vs 18). Se il professore dice "dimostrare", il metodo 1 è più diretto come "prova per costruzione"; il metodo 2 è più elegante ed è quello che L2 di UD1 insegna.

---

### **6. Esercizio svolto stile esame (~4 punti)**

#### **Testo (Appello 230922, parte della domanda 4a)**

> *"Sia $q = 19$ il numero primo comune e sia il generatore $g = 10$. Dimostrare che 10 è un generatore = radice primitiva di $\mathbb{Z}_{19}^*$."*

---

#### **Soluzione con il metodo efficiente (consigliato):**

$g$ è radice primitiva di $\mathbb{Z}_p^*$ se e solo se $g^{(p-1)/q_i} \not\equiv 1 \pmod{p}$ per ogni fattore primo $q_i$ di $p-1$.

**Fattorizzazione:** $p - 1 = 18 = 2 \times 3^2$. Fattori primi: $\{2, 3\}$.

**Verifica per $q_1 = 2$:**
$$10^{18/2} = 10^9 \bmod 19 = 18 \ne 1 \quad \checkmark$$

**Verifica per $q_2 = 3$:**
$$10^{18/3} = 10^6 \bmod 19 = 11 \ne 1 \quad \checkmark$$

Poiché entrambe le condizioni sono verificate, $g = 10$ è una **radice primitiva di $\mathbb{Z}_{19}^*$**.

---

### **7. Note pratiche per l'esame**

- Il numero di radici primitive di $\mathbb{Z}_p^*$ è $\varphi(p-1)$. Per $p=19$: $\varphi(18) = \varphi(2)\cdot\varphi(9) = 1 \cdot 6 = 6$. Esistono quindi 6 radici primitive in $\mathbb{Z}_{19}^*$.

- Se il verificatore restituisce 1 per uno qualsiasi dei fattori primi, $g$ **non** è una radice primitiva. In tal caso si può trovare quale sia il vero ordine di $g$: è il più piccolo divisore $d$ di $p-1$ tale che $g^d \equiv 1 \pmod{p}$.

> ✅ **Recap:** per dimostrare che $g$ è radice primitiva di $\mathbb{Z}_p^*$, il metodo più rapido è fattorizzare $p-1$ e verificare $g^{(p-1)/q_i} \ne 1 \pmod p$ per ogni fattore primo $q_i$. Per $p=19$, $g=10$: $10^9 \equiv 18 \pmod{19}$ e $10^6 \equiv 11 \pmod{19}$, entrambe diverse da 1 → 10 è generatore.
