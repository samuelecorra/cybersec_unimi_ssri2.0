# Sostituzione su blocchi binari — Esercizio d'esame

> 🗂️ **Fonte:** appello del **17 gennaio 2024**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 17 gennaio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L1%20-%20Appello%2017%20gennaio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura classica a sostituzione su blocchi binari (30 punti)

### Traccia originale

> **(30 punti) Cifratura classica.**
> In un cifrario a sostituzione coppie di simboli sono sostituiti da altre coppie. Per esempio i simboli {00; 01; 10; 11} sono sostituiti da {01; 10; 00; 11}, rispettivamente.
>
> a. (5) Quanti cifrari a sostituzione esistono per parole binarie di lunghezza 2?
>
> b. (10) Se si scrive il cifrario ottenuto in forma tabellare, qual è la dimensione della tabella in bit? Generalizzare la risposta al caso di parole formate da n bit.
>
> c. (15) Se il cifrario opera su un messaggio m=(m1,m2) e con una chiave k=(k1,k2) si ottiene il testo cifrato c=(m1⊕m2⊕k1, m2⊕k2), dove ⊕ è lo XOR:
> i. Quante sostituzioni sono possibili con questo cifrario?
> ii. Cifrare il messaggio M1=0110 con chiave k=(1,1), e decifrare il cifrato ottenuto.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — spazio delle chiavi nei cifrari a sostituzione
- [`../../M1_Crittografia_Classica/UD1/L4 - Proprietà di Sicurezza.md`](../../M1_Crittografia_Classica/UD1/L4%20-%20Proprietà%20di%20Sicurezza.md) — sicurezza e dimensione della chiave

### Soluzione dettagliata

#### Parte a — Numero di cifrari a sostituzione per parole di 2 bit (5 punti)

Un cifrario a sostituzione su parole binarie di lunghezza 2 è una **permutazione** dell'insieme delle parole possibili.

Le parole binarie di lunghezza 2 sono: $\{00, 01, 10, 11\}$, ovvero $2^2 = 4$ parole.

Un cifrario a sostituzione su questo insieme è una funzione biiezione (permutazione) da $\{0,1\}^2$ a $\{0,1\}^2$. Il numero di permutazioni di un insieme di $N$ elementi è $N!$.

Quindi il numero di cifrari a sostituzione è:

$$|\text{Cifrari}| = (2^2)! = 4! = 4 \times 3 \times 2 \times 1 = 24$$

> 📌 In generale, per parole binarie di lunghezza $n$, il numero di cifrari a sostituzione è $(2^n)!$. Questo numero cresce enormemente: per $n=8$ (un byte) si ottiene $256!$, un numero astronomico.

#### Parte b — Dimensione della tabella in bit (10 punti)

**Per parole di lunghezza 2:**

La tabella di sostituzione ha $2^2 = 4$ righe (una per ogni parola in input). Per ogni riga, il valore di output è una delle 4 parole possibili, ciascuna rappresentata con 2 bit.

Dimensione della tabella:

$$\text{dimensione} = (\text{numero di righe}) \times (\text{bit per riga}) = 2^2 \times 2 = 4 \times 2 = 8 \text{ bit}$$

La tabella nell'esempio dato è:

| Input | Output |
|-------|--------|
| 00 | 01 |
| 01 | 10 |
| 10 | 00 |
| 11 | 11 |

Questo corrisponde a 4 entry × 2 bit = 8 bit totali.

**Generalizzazione a parole di n bit:**

Con parole di $n$ bit, il numero di parole possibili è $2^n$. La tabella ha $2^n$ righe, e ogni riga contiene un output di $n$ bit. Quindi:

$$\boxed{\text{dimensione tabella} = 2^n \times n \text{ bit}}$$

**Verifica con n=2:** $2^2 \times 2 = 4 \times 2 = 8$ bit ✓

**Osservazione sulla chiave:** per specificare quale delle $(2^n)!$ permutazioni usare, basterebbe un indice. Ma la tabella completa richiede $n \cdot 2^n$ bit. Per $n=64$, si avrebbero $64 \cdot 2^{64} \approx 10^{21}$ bit, del tutto impraticabile. Questo è il motivo per cui i cifrari pratici non usano tabelle di sostituzione generali ma strutture parametrizzate (come le S-box di DES/AES).

> ⚠️ Distinguere tra la **dimensione della chiave** (quanti bit servono per indicizzare le $(2^n)!$ permutazioni, ovvero $\log_2((2^n)!)$ bit) e la **dimensione della tabella** ($n \cdot 2^n$ bit). La domanda chiede la seconda.

#### Parte c — Cifrario con struttura specifica XOR (15 punti)

Il cifrario è definito come: dato $m = (m_1, m_2)$ con chiave $k = (k_1, k_2)$, il cifrato è:

$$c = (c_1, c_2) = (m_1 \oplus m_2 \oplus k_1,\; m_2 \oplus k_2)$$

dove $m_1, m_2, k_1, k_2 \in \{0, 1\}$ e $m = m_1 m_2$ rappresenta la coppia di bit.

> 📌 L'input del cifrario è una parola di 2 bit $m = m_1 m_2$, e la chiave è anch'essa $k = k_1 k_2$ di 2 bit. L'output è $c = c_1 c_2$ di 2 bit.

##### Parte c.i — Quante sostituzioni sono possibili (5 punti)

La chiave $k = (k_1, k_2)$ con $k_1, k_2 \in \{0,1\}$ ha $2^2 = 4$ valori possibili. Ogni valore di chiave definisce una diversa funzione di sostituzione. Verifichiamo che le 4 funzioni siano distinte:

**Per $k = (0,0)$:** $c = (m_1 \oplus m_2, m_2)$

| Input $m_1 m_2$ | Output $c_1 c_2$ |
|-----------------|-----------------|
| 00 | 00 |
| 01 | 11 |
| 10 | 10 |
| 11 | 01 |

**Per $k = (0,1)$:** $c = (m_1 \oplus m_2, m_2 \oplus 1)$

| Input $m_1 m_2$ | Output $c_1 c_2$ |
|-----------------|-----------------|
| 00 | 01 |
| 01 | 10 |
| 10 | 11 |
| 11 | 00 |

**Per $k = (1,0)$:** $c = (m_1 \oplus m_2 \oplus 1, m_2)$

| Input $m_1 m_2$ | Output $c_1 c_2$ |
|-----------------|-----------------|
| 00 | 10 |
| 01 | 01 |
| 10 | 00 |
| 11 | 11 |

**Per $k = (1,1)$:** $c = (m_1 \oplus m_2 \oplus 1, m_2 \oplus 1)$

| Input $m_1 m_2$ | Output $c_1 c_2$ |
|-----------------|-----------------|
| 00 | 11 |
| 01 | 00 |
| 10 | 01 |
| 11 | 10 |

Le 4 funzioni sono tutte distinte e tutte sono permutazioni (biiezioni). Quindi:

$$\text{Sostituzioni possibili} = 4$$

> ⚠️ Notare che questo cifrario realizza solo 4 delle 24 permutazioni possibili per 2 bit. Lo spazio delle chiavi è molto ridotto rispetto a un cifrario a sostituzione generico.

##### Parte c.ii — Cifrare M1=0110 con k=(1,1) e poi decifrare (10 punti)

Il messaggio $M_1 = 0110$ è composto da due coppie di bit: il primo blocco è $m^{(1)} = 01$ e il secondo blocco è $m^{(2)} = 10$.

La chiave è $k = (k_1, k_2) = (1, 1)$.

**Cifratura del primo blocco $m^{(1)} = 01$ (ovvero $m_1=0, m_2=1$):**

$$c_1^{(1)} = m_1 \oplus m_2 \oplus k_1 = 0 \oplus 1 \oplus 1 = 0$$
$$c_2^{(1)} = m_2 \oplus k_2 = 1 \oplus 1 = 0$$

Primo blocco cifrato: $c^{(1)} = 00$

**Cifratura del secondo blocco $m^{(2)} = 10$ (ovvero $m_1=1, m_2=0$):**

$$c_1^{(2)} = m_1 \oplus m_2 \oplus k_1 = 1 \oplus 0 \oplus 1 = 0$$
$$c_2^{(2)} = m_2 \oplus k_2 = 0 \oplus 1 = 1$$

Secondo blocco cifrato: $c^{(2)} = 01$

**Testo cifrato completo:** $C = 0001$

**Decifratura:** dato $c = (c_1, c_2)$ e chiave $k = (k_1, k_2)$, ricaviamo $m$ invertendo le equazioni:

Dall'equazione $c_2 = m_2 \oplus k_2$ si ottiene immediatamente:
$$m_2 = c_2 \oplus k_2$$

Dall'equazione $c_1 = m_1 \oplus m_2 \oplus k_1$, avendo già $m_2$:
$$m_1 = c_1 \oplus m_2 \oplus k_1 = c_1 \oplus (c_2 \oplus k_2) \oplus k_1$$

**Decifratura del primo blocco cifrato $c^{(1)} = 00$ (ovvero $c_1=0, c_2=0$):**

$$m_2^{(1)} = c_2^{(1)} \oplus k_2 = 0 \oplus 1 = 1$$
$$m_1^{(1)} = c_1^{(1)} \oplus m_2^{(1)} \oplus k_1 = 0 \oplus 1 \oplus 1 = 0$$

Primo blocco decifrato: $m^{(1)} = 01$ ✓

**Decifratura del secondo blocco cifrato $c^{(2)} = 01$ (ovvero $c_1=0, c_2=1$):**

$$m_2^{(2)} = c_2^{(2)} \oplus k_2 = 1 \oplus 1 = 0$$
$$m_1^{(2)} = c_1^{(2)} \oplus m_2^{(2)} \oplus k_1 = 0 \oplus 0 \oplus 1 = 1$$

Secondo blocco decifrato: $m^{(2)} = 10$ ✓

**Messaggio decifrato:** $M_1 = 0110$ ✓ La decifratura ha riprodotto correttamente il plaintext originale.
