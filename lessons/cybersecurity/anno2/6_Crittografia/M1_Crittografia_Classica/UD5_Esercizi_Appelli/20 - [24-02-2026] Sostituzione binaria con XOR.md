# Sostituzione binaria con XOR — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 febbraio 2026**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 febbraio 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L2%20-%20Appello%2024%20febbraio%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura classica: sostituzione binaria con chiave XOR (30 punti)

### Traccia originale

> **(30 punti) Cifratura classica.** In un cifrario a sostituzione tuple di simboli sono sostituiti da altre coppie. Per esempio $\{00;01;10;11\}$ sono sostituiti da $\{01;10;00;11\}$.
>
> a. (5) Quanti cifrari a sostituzione esistono per parole binarie di lunghezza 4?
>
> b. (10) Se si scrive il cifrario in forma tabellare, qual è la dimensione della tabella in bit? Generalizzare a parole di $n$ bit.
>
> c. (15) Il cifrario opera su $m=(i,j,k,l)$ con chiave $\mathbf{k}=(k_1,k_2,k_3)$ producendo $c=(i\oplus k_1,\ j\oplus k_2,\ k\oplus k_1\oplus k_2,\ l\oplus k_3)$:
>
> i. Quante sostituzioni sono possibili?
>
> ii. Cifrare $M_1=1011\ 0111$ con $\mathbf{k}=(0,1,1)$ e decifrare il cifrato ottenuto.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — sostituzione su blocchi binari

### Soluzione dettagliata

#### Parte a — Numero di cifrari a sostituzione su 4 bit (5 punti)

Con $n=4$ bit ci sono $2^4=16$ possibili parole. Un cifrario a sostituzione è una permutazione di questi 16 elementi:

$$\text{N. cifrari} = 16! \approx 2{,}09 \times 10^{13}$$

#### Parte b — Dimensione della tabella; generalizzazione (10 punti)

La tabella specifica l'output (n bit) per ciascuno dei $2^n$ possibili input:

$$\text{Dimensione} = n \cdot 2^n \text{ bit}$$

Per $n=2$ (esempio): $2 \cdot 4 = 8$ bit. Per $n=4$: $4 \cdot 16 = 64$ bit. Per $n=8$: $8 \cdot 256 = 2048$ bit.

> 💡 Per $n=64$ la chiave occuperebbe $64 \cdot 2^{64} \approx 10^{21}$ byte — impraticabile. I cifrari moderni (AES) usano strutture algebriche per comprimere questa informazione in 128–256 bit di chiave.

#### Parte c.i — Numero di sostituzioni (5 punti)

La chiave $(k_1,k_2,k_3) \in \{0,1\}^3$ ha $2^3 = 8$ valori possibili. Ogni chiave distinta produce una sostituzione diversa (la mappatura cambia su almeno un elemento se anche un solo bit di chiave cambia):

$$\text{N. sostituzioni} = 8$$

> ⚠️ Lo spazio delle chiavi (8) è enormemente ridotto rispetto alle $16! \approx 2 \times 10^{13}$ sostituzioni possibili su 4 bit. Il cifrario accede a meno di $10^{-9}\%$ delle permutazioni disponibili.

#### Parte c.ii — Cifratura di $M_1=1011\ 0111$ e decifratura (10 punti)

**Chiave:** $(k_1,k_2,k_3)=(0,1,1)$.

**Blocco $B_1 = (1,0,1,1)$:**

$$c = (1\oplus0,\ 0\oplus1,\ 1\oplus0\oplus1,\ 1\oplus1) = (1,1,0,0) = \mathbf{1100}$$

**Blocco $B_2 = (0,1,1,1)$:**

$$c = (0\oplus0,\ 1\oplus1,\ 1\oplus0\oplus1,\ 1\oplus1) = (0,0,0,0) = \mathbf{0000}$$

**Ciphertext:** $C = 1100\ 0000$.

**Decifratura** (XOR è autocomplementare: $i = c_1 \oplus k_1$, ecc.):

- $1100$ con $(0,1,1)$: $1\oplus0=1,\ 1\oplus1=0,\ 0\oplus0\oplus1=1,\ 0\oplus1=1$ → $1011$ ✓
- $0000$ con $(0,1,1)$: $0\oplus0=0,\ 0\oplus1=1,\ 0\oplus0\oplus1=1,\ 0\oplus1=1$ → $0111$ ✓
