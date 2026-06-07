# Cifrario affine — Esercizio d'esame

> 🗂️ **Fonte:** appello del **5 settembre 2025**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 5 settembre 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L5%20-%20Appello%205%20settembre%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifrario affine (25 punti)

### Traccia originale

> **(25 punti) Cifrario affine.**
>
> a. (10 punti) Si descriva il funzionamento dei cifrari affini.
>
> Si consideri un cifrario implementato in $\mathbb{Z}_{26}$:
>
> b. (15 punti) Sapendo che "bgvralav" è il cifrato di "wlestate" recuperare le chiavi usate, illustrando il procedimento.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrario affine: definizione e inversione

### Soluzione dettagliata

#### Parte a — Funzionamento dei cifrari affini (10 punti)

Il cifrario affine è un cifrario a sostituzione monoalfabetica che generalizza il cifrario di Cesare.

**Definizione:**

Ogni lettera dell'alfabeto viene mappata a un numero in $\mathbb{Z}_{26}$ (A=0, B=1, ..., Z=25). La funzione di cifratura è:

$$E(x) = (a \cdot x + b) \bmod 26$$

dove:
- $a \in \mathbb{Z}_{26}$ è il **moltiplicatore** (deve soddisfare $\gcd(a, 26) = 1$ per garantire l'invertibilità)
- $b \in \mathbb{Z}_{26}$ è il **traslatore** (shift)
- $(a, b)$ è la chiave

**Decifratura:** per invertire, si applica l'inversa della funzione affine:

$$D(y) = a^{-1} \cdot (y - b) \bmod 26$$

dove $a^{-1}$ è l'inverso moltiplicativo di $a$ in $\mathbb{Z}_{26}$.

**Condizione di invertibilità:** $\gcd(a, 26) = 1$. I valori di $a$ invertibili in $\mathbb{Z}_{26}$ sono: 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 (12 valori). Con $b \in \{0, \ldots, 25\}$, lo spazio delle chiavi ha $12 \times 26 = 312$ chiavi.

**Casi speciali:**
- $a=1$: si riduce al cifrario di Cesare.
- $b=0$: cifrario di Atbash (se $a=25$).

**Sicurezza:**

Il cifrario affine è vulnerabile sia all'analisi delle frequenze (essendo monoalfabetico) sia alla crittoanalisi algebrica: con due coppie (plaintext, ciphertext) note, si può risolvere un sistema di due equazioni lineari per trovare $(a, b)$.

> ⚠️ Lo spazio di 312 chiavi è troppo piccolo per la sicurezza moderna: si potrebbe fare brute force a mano.

#### Parte b — Recupero delle chiavi: "wlestate" → "bgvralav" (15 punti)

**Codifica delle lettere (A=0, ..., Z=25):**

| Lettera | Codice |
|---------|--------|
| A | 0 |
| B | 1 |
| C | 2 |
| D | 3 |
| E | 4 |
| F | 5 |
| G | 6 |
| L | 11 |
| R | 17 |
| S | 18 |
| T | 19 |
| V | 21 |
| W | 22 |

**Coppie (plaintext, ciphertext):**

| Posizione | Plaintext | Cifrato | $p_i$ | $c_i$ |
|-----------|-----------|---------|-------|-------|
| 1 | w | b | 22 | 1 |
| 2 | l | g | 11 | 6 |
| 3 | e | v | 4 | 21 |
| 4 | s | r | 18 | 17 |
| 5 | t | a | 19 | 0 |
| 6 | a | l | 0 | 11 |
| 7 | t | a | 19 | 0 |
| 8 | e | v | 4 | 21 |

**Sistema di equazioni:**

Dalla funzione affine $c = a \cdot p + b \pmod{26}$, usiamo due coppie per costruire un sistema.

Usiamo la coppia 1 ($p=22, c=1$) e la coppia 2 ($p=11, c=6$):

$$22a + b \equiv 1 \pmod{26} \tag{1}$$

$$11a + b \equiv 6 \pmod{26} \tag{2}$$

**Sottrazione (1) - (2):**

$$22a - 11a \equiv 1 - 6 \pmod{26}$$

$$11a \equiv -5 \equiv 21 \pmod{26}$$

Dobbiamo trovare $11^{-1} \pmod{26}$. Usando l'algoritmo di Euclide esteso:

$26 = 2 \cdot 11 + 4$

$11 = 2 \cdot 4 + 3$

$4 = 1 \cdot 3 + 1$

$3 = 3 \cdot 1$

Risalendo: $1 = 4 - 1 \cdot 3 = 4 - 1 \cdot (11 - 2 \cdot 4) = 3 \cdot 4 - 11 = 3(26 - 2 \cdot 11) - 11 = 3 \cdot 26 - 7 \cdot 11$

Quindi $11^{-1} \equiv -7 \equiv 19 \pmod{26}$.

Verifica: $11 \cdot 19 = 209 = 8 \cdot 26 + 1 \equiv 1 \pmod{26}$ ✓

$$a = 11^{-1} \cdot 21 = 19 \cdot 21 \pmod{26}$$

$$19 \cdot 21 = 399 = 15 \cdot 26 + 9$$

$$a = 9$$

**Calcolo di $b$:** dalla equazione (1):

$$22 \cdot 9 + b \equiv 1 \pmod{26}$$

$$198 + b \equiv 1 \pmod{26}$$

$198 = 7 \cdot 26 + 16$, quindi $198 \bmod 26 = 16$.

$$16 + b \equiv 1 \pmod{26}$$

$$b \equiv 1 - 16 \equiv -15 \equiv 11 \pmod{26}$$

**Chiavi trovate: $a = 9$, $b = 11$**

**Funzione di cifratura:** $E(x) = 9x + 11 \pmod{26}$

**Verifica su tutte le coppie:**

| $p$ | $9p + 11 \bmod 26$ | Atteso $c$ |
|-----|-------------------|-----------|
| 22 (w) | $198 + 11 = 209 \bmod 26 = 209 - 8\cdot26 = 209-208 = 1$ | 1 (b) ✓ |
| 11 (l) | $99 + 11 = 110 \bmod 26 = 110 - 4\cdot26 = 6$ | 6 (g) ✓ |
| 4 (e) | $36 + 11 = 47 \bmod 26 = 21$ | 21 (v) ✓ |
| 18 (s) | $162 + 11 = 173 \bmod 26 = 173 - 6\cdot26 = 173-156 = 17$ | 17 (r) ✓ |
| 19 (t) | $171 + 11 = 182 \bmod 26 = 182 - 7\cdot26 = 182-182 = 0$ | 0 (a) ✓ |
| 0 (a) | $0 + 11 = 11$ | 11 (l) ✓ |

Tutte le coppie sono verificate.

> 📌 Con solo 2 coppie note (plaintext, ciphertext), il cifrario affine è completamente rotto. Questo è il known plaintext attack applicato al cifrario affine.
