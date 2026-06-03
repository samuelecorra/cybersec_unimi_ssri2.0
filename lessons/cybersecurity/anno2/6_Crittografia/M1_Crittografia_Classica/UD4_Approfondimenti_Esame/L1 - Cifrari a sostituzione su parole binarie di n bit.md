# **L1 – Cifrari a sostituzione su parole binarie di n bit**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 2 appelli d'esame (240117 e 250715) con peso di **30 punti** ciascuno.
>
> **Studia prima:**
> - [UD2 / L3 – Cifrari a sostituzione](../UD2/L3%20-%20Cifrari%20a%20sostituzione.md) → definizione generale di cifrario a sostituzione su alfabeto di 26 lettere
> - [UD2 / L5 – Crittoanalisi dei cifrari a sostituzione](../UD2/L5%20-%20Crittoanalisi%20dei%20cifrari%20a%20sostituzione.md) → One-Time Pad, XOR su bit, sicurezza perfetta

---

### **1. Generalizzazione: dall'alfabeto di 26 lettere alle parole binarie**

Nelle lezioni canoniche, un **cifrario a sostituzione monoalfabetico** opera su un alfabeto di 26 simboli:

$$
\pi : \{A, B, \ldots, Z\} \longrightarrow \{A, B, \ldots, Z\}
$$

La funzione $\pi$ è una **biiezione** (permutazione) dell'alfabeto.

La generalizzazione naturale è sostituire l'alfabeto di 26 lettere con l'insieme di tutte le **stringhe binarie di lunghezza $n$**:

$$
\Sigma_n = \{0,1\}^n = \{\underbrace{00\ldots0}_{n}, \underbrace{00\ldots1}_{n}, \ldots, \underbrace{11\ldots1}_{n}\}
$$

L'alfabeto $\Sigma_n$ ha esattamente $|\Sigma_n| = 2^n$ simboli (le parole binarie di lunghezza $n$).

> 📌 Un **cifrario a sostituzione su parole binarie di n bit** è una biiezione:
> $$\pi : \{0,1\}^n \longrightarrow \{0,1\}^n$$
> La chiave del cifrario **è** la biiezione $\pi$, oppure equivalentemente la sua tabella di sostituzione.

---

### **2. Quanti cifrari a sostituzione esistono per parole di n bit?**

Un cifrario a sostituzione è una permutazione dell'insieme $\{0,1\}^n$.  
Il numero totale di permutazioni di un insieme con $N$ elementi è $N!$.

Poiché $|\Sigma_n| = 2^n$, il numero di cifrari a sostituzione distinti è:

$$
\boxed{N_{\text{cifrari}} = (2^n)!}
$$

#### **Tabella comparativa per piccoli valori di n**

| $n$ | $|\Sigma_n| = 2^n$ | $(2^n)!$ | Ordine di grandezza |
|-----|-------------------|----------|---------------------|
| 1   | 2                 | 2! = 2   | 2 |
| 2   | 4                 | 4! = 24  | $\approx 10^1$ |
| 3   | 8                 | 8! = 40 320 | $\approx 10^4$ |
| 4   | 16                | 16! ≈ 2.09 × 10¹³ | $\approx 10^{13}$ |
| 8   | 256               | 256! ≈ 8.58 × 10^{506} | astronomi​co |

> 💡 Per $n=8$ (byte), lo spazio dei cifrari è astronomico, rendendo un attacco a forza bruta del tutto impraticabile — ma la **rappresentazione della chiave è enorme**, come vedremo.

---

### **3. Dimensione della tabella di sostituzione**

Per specificare completamente un cifrario a sostituzione su $\{0,1\}^n$, si costruisce una **tabella di sostituzione** (lookup table) che associa ogni parola in chiaro alla corrispondente parola cifrata.

La tabella ha:
- **$2^n$ righe** (una per ogni parola in chiaro),
- **$n$ bit** per riga (per memorizzare la parola cifrata di output).

La dimensione totale della tabella è quindi:

$$
\boxed{D_{\text{tabella}} = n \cdot 2^n \quad \text{bit}}
$$

#### **Esempi**

| $n$ | Righe ($2^n$) | Bit per riga ($n$) | Totale ($n \cdot 2^n$) |
|-----|--------------|-------------------|------------------------|
| 1   | 2            | 1                 | 2 bit |
| 2   | 4            | 2                 | 8 bit |
| 3   | 8            | 3                 | 24 bit |
| 4   | 16           | 4                 | 64 bit |
| 8   | 256          | 8                 | 2 048 bit = 256 byte |

> ⚠️ Confronto con l'alfabeto di 26 lettere: lì la tabella ha 26 righe × log₂(26) ≈ 4,7 bit/riga ≈ 122 bit. Per $n=8$ la tabella è enormemente più grande (2 048 bit), e per $n=16$ sarebbe 16 × 65 536 = 1 048 576 bit ≈ 128 KB. Questa è la principale **difficoltà pratica** dei cifrari a sostituzione su parole lunghe.

---

### **4. Cifrario XOR puro: c = m ⊕ k**

Il cifrario più semplice su $\{0,1\}^n$ è il **cifrario XOR** con chiave fissa:

$$
c = m \oplus k \quad \text{con } m, k, c \in \{0,1\}^n
$$

Per ogni chiave $k$ fissata, questo cifrario definisce una biiezione specifica di $\{0,1\}^n$.  
Al variare di $k$ su tutti i $2^n$ valori possibili, otteniamo $2^n$ biiezioni distinte.

> 📌 Il cifrario XOR implementa esattamente $2^n$ delle $(2^n)!$ sostituzioni possibili.

Queste $2^n$ sostituzioni sono una **frazione minuscola** del totale:

$$
\frac{2^n}{(2^n)!} \xrightarrow{n \to \infty} 0 \quad \text{(rapidissimamente)}
$$

> ⚠️ **Implicazione crittografica:** il cifrario XOR puro è computazionalmente debole rispetto a un cifrario a sostituzione casuale, perché un avversario deve cercare solo tra $2^n$ chiavi invece di $(2^n)!$ permutazioni.

---

### **5. Cifrario XOR a due componenti: c = (m₁⊕m₂⊕k₁, m₂⊕k₂)**

Questo è il tipo di cifrario specifico che compare negli appelli d'esame. Lavora su **parole di 2 bit** e usa una chiave a **2 bit** $(k_1, k_2)$.

#### **Definizione**

L'alfabeto è $\{0,1\}^2 = \{00, 01, 10, 11\}$ ($n=2$, 4 simboli).  
Il messaggio è una coppia $(m_1, m_2) \in \{0,1\}^2$.  
La chiave è $(k_1, k_2) \in \{0,1\}^2$.

La cifratura è definita da:

$$
\begin{cases}
c_1 = m_1 \oplus m_2 \oplus k_1 \\
c_2 = m_2 \oplus k_2
\end{cases}
$$

#### **Decifratura**

Si inverte il sistema. Da $c_2 = m_2 \oplus k_2$ si ricava:

$$
m_2 = c_2 \oplus k_2
$$

Poi da $c_1 = m_1 \oplus m_2 \oplus k_1$ si ricava:

$$
m_1 = c_1 \oplus m_2 \oplus k_1 = c_1 \oplus (c_2 \oplus k_2) \oplus k_1
$$

> 📌 La decifratura richiede la chiave $(k_1, k_2)$. La struttura è invertibile per qualsiasi chiave.

#### **Quante sostituzioni implementa?**

Per rispondere, calcoliamo la tabella di sostituzione per ognuno dei 4 possibili valori di chiave:

**Chiave $(k_1=0, k_2=0)$:**

| Messaggio $(m_1, m_2)$ | $c_1 = m_1{\oplus}m_2{\oplus}0$ | $c_2 = m_2{\oplus}0$ | Ciphertext |
|------------------------|----------------------------------|----------------------|------------|
| $(0,0) = 00$           | $0{\oplus}0{\oplus}0 = 0$        | $0{\oplus}0 = 0$     | $00$       |
| $(0,1) = 01$           | $0{\oplus}1{\oplus}0 = 1$        | $1{\oplus}0 = 1$     | $11$       |
| $(1,0) = 10$           | $1{\oplus}0{\oplus}0 = 1$        | $0{\oplus}0 = 0$     | $10$       |
| $(1,1) = 11$           | $1{\oplus}1{\oplus}0 = 0$        | $1{\oplus}0 = 1$     | $01$       |

Permutazione: $00 \to 00,\ 01 \to 11,\ 10 \to 10,\ 11 \to 01$

**Chiave $(k_1=1, k_2=0)$:**

| Messaggio | $c_1$ | $c_2$ | Ciphertext |
|-----------|-------|-------|------------|
| $00$ | $0{\oplus}0{\oplus}1=1$ | $0$ | $10$ |
| $01$ | $0{\oplus}1{\oplus}1=0$ | $1$ | $01$ |
| $10$ | $1{\oplus}0{\oplus}1=0$ | $0$ | $00$ |
| $11$ | $1{\oplus}1{\oplus}1=1$ | $1$ | $11$ |

Permutazione: $00 \to 10,\ 01 \to 01,\ 10 \to 00,\ 11 \to 11$

**Chiave $(k_1=0, k_2=1)$:**

| Messaggio | $c_1$ | $c_2$ | Ciphertext |
|-----------|-------|-------|------------|
| $00$ | $0$ | $0{\oplus}1=1$ | $01$ |
| $01$ | $1$ | $1{\oplus}1=0$ | $10$ |
| $10$ | $1$ | $0{\oplus}1=1$ | $11$ |
| $11$ | $0$ | $1{\oplus}1=0$ | $00$ |

Permutazione: $00 \to 01,\ 01 \to 10,\ 10 \to 11,\ 11 \to 00$

**Chiave $(k_1=1, k_2=1)$:**

| Messaggio | $c_1$ | $c_2$ | Ciphertext |
|-----------|-------|-------|------------|
| $00$ | $1$ | $1$ | $11$ |
| $01$ | $0$ | $0$ | $00$ |
| $10$ | $0$ | $1$ | $01$ |
| $11$ | $1$ | $0$ | $10$ |

Permutazione: $00 \to 11,\ 01 \to 00,\ 10 \to 01,\ 11 \to 10$

**Riepilogo delle 4 permutazioni:**

| Chiave $(k_1,k_2)$ | $00 \to$ | $01 \to$ | $10 \to$ | $11 \to$ |
|--------------------|----------|----------|----------|----------|
| $(0,0)$            | $00$     | $11$     | $10$     | $01$     |
| $(1,0)$            | $10$     | $01$     | $00$     | $11$     |
| $(0,1)$            | $01$     | $10$     | $11$     | $00$     |
| $(1,1)$            | $11$     | $00$     | $01$     | $10$     |

Le 4 permutazioni sono **tutte distinte** tra loro, quindi il cifrario implementa esattamente **4 sostituzioni distinte**.

> ⚠️ Il totale possibile per $n=2$ è $(2^2)! = 4! = 24$ sostituzioni. Questo cifrario copre solo $4/24 \approx 16.7\%$ dello spazio delle permutazioni, rendendolo molto più vulnerabile di un cifrario a sostituzione generale.

---

### **6. Esercizio svolto stile esame**

#### **Testo tipico (30 punti)**

> Sia dato un cifrario a sostituzione su parole binarie di lunghezza $n$.
>
> (a) Quanti cifrari a sostituzione distinti esistono? *(8 pt)*
>
> (b) Qual è la dimensione in bit della tabella di sostituzione che specifica un cifrario? *(7 pt)*
>
> (c) Sia dato il cifrario con chiave $(k_1, k_2)$ definito da $c = (m_1 \oplus m_2 \oplus k_1,\ m_2 \oplus k_2)$. Quante sostituzioni distinte implementa? Cifrare il messaggio $(0,1)$ con chiave $(1,1)$. Decifrare il ciphertext $(1,0)$ con chiave $(0,1)$. *(15 pt)*

#### **Soluzione completa**

**Risposta (a):**

L'alfabeto ha $2^n$ simboli. Il numero di biiezioni (permutazioni) di un insieme di $N$ elementi è $N!$.  
Sostituendo $N = 2^n$:

$$
N_{\text{cifrari}} = (2^n)!
$$

**Risposta (b):**

La tabella ha $2^n$ righe, una per ogni possibile parola in chiaro, e ogni riga contiene la parola cifrata corrispondente di $n$ bit:

$$
D_{\text{tabella}} = n \cdot 2^n \quad \text{bit}
$$

**Risposta (c) — numero di sostituzioni:**

Le chiavi $(k_1, k_2) \in \{0,1\}^2$ sono 4. Come dimostrato nella Sezione 5, ciascuna produce una permutazione distinta. Il cifrario implementa quindi **4 sostituzioni distinte**.

Confronto: $(2^2)! = 24$ permutazioni totali; questo cifrario ne implementa $4$, ovvero il $16.7\%$.

**Cifratura di $(m_1,m_2) = (0,1)$ con $(k_1,k_2) = (1,1)$:**

$$
c_1 = 0 \oplus 1 \oplus 1 = 0
$$
$$
c_2 = 1 \oplus 1 = 0
$$

Ciphertext: $(0,0) = 00$ ✓

**Decifratura di $(c_1,c_2) = (1,0)$ con $(k_1,k_2) = (0,1)$:**

$$
m_2 = c_2 \oplus k_2 = 0 \oplus 1 = 1
$$
$$
m_1 = c_1 \oplus m_2 \oplus k_1 = 1 \oplus 1 \oplus 0 = 0
$$

Plaintext: $(m_1,m_2) = (0,1) = 01$ ✓

---

### **7. Schema riassuntivo**

| Domanda | Risposta chiave |
|---------|----------------|
| Numero di cifrari su $\{0,1\}^n$ | $(2^n)!$ |
| Dimensione tabella di sostituzione | $n \cdot 2^n$ bit |
| Cifrario XOR puro $c = m \oplus k$ | $2^n$ sostituzioni distinte (su $(2^n)!$ possibili) |
| Cifrario XOR composito su 2 bit | 4 sostituzioni distinte (su $4! = 24$ possibili) |
| Decifratura di $(c_1, c_2)$ | $m_2 = c_2 \oplus k_2$; $m_1 = c_1 \oplus m_2 \oplus k_1$ |

> ✅ **Recap:** i cifrari a sostituzione binari generalizzano il concetto già noto su alfabeto di 26 lettere. La chiave è una permutazione dell'insieme $\{0,1\}^n$. I cifrari lineari (XOR-based) implementano solo una piccola fraction delle $(2^n)!$ permutazioni totali, il che ne limita la sicurezza rispetto a un cifrario a sostituzione generale con tabella casuale.
