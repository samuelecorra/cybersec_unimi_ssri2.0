# Funzioni hash — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 gennaio 2025**, Domanda 3 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 gennaio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L1%20-%20Appello%2020%20gennaio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Funzioni hash (20 punti)

### Traccia originale

> **(20 punti) Funzioni hash.**
>
> a. (10 punti) Illustrare le proprietà di sicurezza delle funzioni hash.
>
> b. (10 punti) Dimostrare che la funzione hash $H(x) = 5x + 12 \bmod 20$ non ha la proprietà di resistenza debole alle collisioni e trovare inoltre una collisione per $H(6)$.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — le tre proprietà di sicurezza
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno e second preimage

### Soluzione dettagliata

#### Parte a — Proprietà di sicurezza delle funzioni hash (10 punti)

Una funzione hash crittografica $H: \{0,1\}^* \to \{0,1\}^n$ deve soddisfare tre proprietà di sicurezza:

**1. Resistenza alla preimmagine (One-wayness / Preimage resistance):**

Data un'immagine $h$, deve essere computazionalmente difficile trovare un $x$ tale che $H(x) = h$.

$$\forall h,\quad \text{difficile trovare } x \text{ t.c. } H(x) = h$$

Questo garantisce che dall'hash non si possa risalire al messaggio originale.

**2. Resistenza debole alle collisioni (Second preimage resistance / Weak collision resistance):**

Dato un messaggio $x$, deve essere computazionalmente difficile trovare un messaggio diverso $x' \neq x$ tale che $H(x') = H(x)$.

$$\forall x,\quad \text{difficile trovare } x' \neq x \text{ t.c. } H(x') = H(x)$$

Questa proprietà protegge l'integrità: un avversario non può modificare un messaggio firmato mantenendo lo stesso hash.

**3. Resistenza forte alle collisioni (Strong collision resistance / Collision resistance):**

Deve essere computazionalmente difficile trovare **qualsiasi** coppia $(x, x')$ con $x \neq x'$ tale che $H(x) = H(x')$.

$$\text{difficile trovare } (x, x') \text{ con } x \neq x' \text{ t.c. } H(x) = H(x')$$

> 📌 La resistenza forte alle collisioni implica la resistenza debole, che a sua volta implica la one-wayness (in senso generale). La gerarchia è: collision resistance $\Rightarrow$ second preimage resistance $\Rightarrow$ preimage resistance.

> ⚠️ Il paradosso del compleanno mostra che trovare una collisione (strong) richiede solo $O(2^{n/2})$ tentativi (invece di $O(2^n)$), il che è il motivo per cui gli hash moderni usano output da 256 o 512 bit.

#### Parte b — $H(x) = 5x + 12 \bmod 20$ non ha second preimage resistance (10 punti)

**Calcolo di $H(6)$:**

$$H(6) = 5 \cdot 6 + 12 \bmod 20 = 30 + 12 \bmod 20 = 42 \bmod 20 = 2$$

**Trovare una collisione per $H(6) = 2$:**

Dobbiamo trovare $x' \neq 6$ tale che $H(x') = 2$, ovvero:

$$5x' + 12 \equiv 2 \pmod{20}$$

$$5x' \equiv 2 - 12 \equiv -10 \equiv 10 \pmod{20}$$

$$5x' \equiv 10 \pmod{20}$$

Dividiamo ambo i membri per $d = \mcd(5, 20) = 5$. Poiché $5 \mid 10$, l'equazione ha esattamente $d = 5$ soluzioni distinte in $\mathbb{Z}_{20}$.

Dividendo tutti e tre i termini per 5 (e riducendo il modulo da 20 a $20/5 = 4$):

$$x' \equiv \frac{10}{5} \pmod{\frac{20}{5}} \implies x' \equiv 2 \pmod{4}$$

Risaliamo in $\mathbb{Z}_{20}$ partendo da $x'_0 = 2$ con passo $n/d = 20/5 = 4$, per $d = 5$ passi:

$$x'_0 = 2, \quad x'_1 = 2+4 = 6, \quad x'_2 = 6+4 = 10, \quad x'_3 = 10+4 = 14, \quad x'_4 = 14+4 = 18$$

Le 5 soluzioni in $\mathbb{Z}_{20}$ sono quindi $\{2, 6, 10, 14, 18\}$ (verificabile anche dalla tabella):

Cerchiamo i valori $x' \in \{0, 1, \ldots, 19\}$ tali che $5x' \bmod 20 = 10$:

| $x'$ | $5x' \bmod 20$ |
|------|---------------|
| 0 | 0 |
| 1 | 5 |
| 2 | 10 ✓ |
| 3 | 15 |
| 4 | 0 |
| 6 | 10 (il valore di partenza) |
| 8 | 0 |
| 10 | 10 ✓ |
| 14 | 10 ✓ |
| 18 | 10 ✓ |

Verifica: $H(2) = 5 \cdot 2 + 12 \bmod 20 = 10 + 12 \bmod 20 = 22 \bmod 20 = 2$ ✓

**Collisione trovata:** $H(6) = H(2) = 2$, con $6 \neq 2$.

**Dimostrazione che la proprietà manca in generale:**

La funzione $H(x) = 5x + 12 \bmod 20$ è lineare, quindi iniettiva solo se $\mcd(5, 20) = 1$. Ma $\mcd(5, 20) = 5 \neq 1$, quindi $H$ non è iniettiva: esiste una classe di residui modulo $20/5 = 4$ che producono lo stesso output. Ogni valore nell'immagine di $H$ ha esattamente 5 preimmagini, il che rende banalmente semplice trovare second preimages (e collisioni).

> ⚠️ Per avere second preimage resistance, una funzione hash deve avere un output molto più grande dell'input, non essere lineare, e non avere struttura algebrica sfruttabile. Questa funzione viola tutte e tre queste caratteristiche.
