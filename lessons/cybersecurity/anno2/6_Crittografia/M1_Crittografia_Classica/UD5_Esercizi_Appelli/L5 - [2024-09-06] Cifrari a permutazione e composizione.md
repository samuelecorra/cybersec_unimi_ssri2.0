# Cifrari a permutazione e composizione — Esercizio d'esame

> 🗂️ **Fonte:** appello del **6 settembre 2024**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 6 settembre 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L6%20-%20Appello%206%20settembre%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifrari a permutazione e composizione (30 punti)

### Traccia originale

> **(30 punti) Cifratura classica.**
>
> a. (5) Descrivere il funzionamento dei cifrari a permutazione.
>
> b. Si considerino due permutation cipher $P_{k_1}(m)$ e $P_{k_2}(m)$ che operano su blocchi di 5 lettere e restituiscono la permutazione secondo le seguenti regole:
>
> $K_1$: $\begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 2 & 3 & 5 & 4 & 1 \end{pmatrix}$
>
> $K_2$: $\begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 2 & 5 & 4 & 3 & 1 \end{pmatrix}$
>
> c. (10) Si applichi ciascun dei due cifrari al messaggio $m$ = "ciao estate".
>
> d. (15) Si descriva il cifrario $c = P_{k_1}(P_{k_2}(m))$ ottenuto per composizione dei due cifrari e si commenti il suo livello di sicurezza.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L2 - Composizione di cifrari a permutazione.md`](../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L2%20-%20Composizione%20di%20cifrari%20a%20permutazione.md) — definizione, applicazione, composizione

### Soluzione dettagliata

#### Parte a — Cifrari a permutazione (5 punti)

Un **cifrario a permutazione** (o trasposizione) è un cifrario classico in cui le lettere del testo in chiaro non vengono sostituite ma **riordinate** (permutate) secondo una chiave che specifica l'ordine di permutazione.

**Funzionamento:**

1. Il messaggio viene diviso in **blocchi** di lunghezza fissa $d$ (la dimensione del blocco è determinata dalla chiave).
2. La chiave $k$ è una permutazione di $\{1, 2, \ldots, d\}$: $k(i) = j$ significa che la lettera in posizione $i$ del plaintext va in posizione $j$ del ciphertext (o, in notazione alternativa, che la posizione $i$ del ciphertext è occupata dalla lettera originariamente in posizione $k(i)$ del plaintext).
3. Ogni blocco viene permutato secondo la stessa chiave.

**Spazio delle chiavi:** per blocchi di $d$ lettere, esistono $d!$ permutazioni possibili. Per $d=5$: $5!=120$ chiavi; per $d=10$: $10! = 3628800$ chiavi.

**Differenza dalla sostituzione:** nei cifrari a sostituzione le lettere cambiano identità (A→E); nei cifrari a permutazione le lettere mantengono la loro identità ma cambiano posizione.

**Debolezza principale:** i cifrari a permutazione preservano le frequenze delle singole lettere (le stesse lettere del plaintext compaiono nel ciphertext, solo in ordine diverso), quindi sono vulnerabili all'analisi delle frequenze. Sono però resistenti all'analisi semplice basata sulle posizioni.

#### Parte b — Definizione delle chiavi

**Chiave $K_1$:** la posizione $i$ del plaintext va in posizione $K_1(i)$ del ciphertext:

| Posizione input | 1 | 2 | 3 | 4 | 5 |
|----------------|---|---|---|---|---|
| Posizione output | 2 | 3 | 5 | 4 | 1 |

La lettera in posizione 1 va in posizione 2, quella in posizione 2 va in 3, quella in 3 va in 5, quella in 4 rimane in 4, quella in 5 va in 1.

**Chiave $K_2$:**

| Posizione input | 1 | 2 | 3 | 4 | 5 |
|----------------|---|---|---|---|---|
| Posizione output | 2 | 5 | 4 | 3 | 1 |

#### Parte c — Applicazione dei cifrari al messaggio "ciao estate" (10 punti)

**Preparazione del messaggio:**

Rimuoviamo lo spazio: "ciaostate" (9 lettere). Dividiamo in blocchi da 5:
- Blocco 1: **c i a o s**
- Blocco 2 (incompleto): **t a t e** → aggiungiamo lettera nulla per completare a 5: **t a t e X**

> ⚠️ I blocchi incompleti vanno completati con una lettera nulla (tipicamente 'x' o un carattere concordato). Qui usiamo 'X'.

**Applicazione di $K_1$ (la posizione $i$ dell'input va in posizione $K_1(i)$ dell'output):**

Per costruire il ciphertext: la lettera in posizione $i$ del plaintext finisce in posizione $K_1(i)$ del ciphertext.

**Blocco 1 con $K_1$:** plaintext = c(1) i(2) a(3) o(4) s(5)

| Input pos | Lettera | Output pos ($K_1$) |
|-----------|---------|------------------|
| 1 | c | 2 |
| 2 | i | 3 |
| 3 | a | 5 |
| 4 | o | 4 |
| 5 | s | 1 |

Ciphertext blocco 1: pos1=s, pos2=c, pos3=i, pos4=o, pos5=a → **s c i o a**

**Blocco 2 con $K_1$:** plaintext = t(1) a(2) t(3) e(4) X(5)

| Input pos | Lettera | Output pos ($K_1$) |
|-----------|---------|------------------|
| 1 | t | 2 |
| 2 | a | 3 |
| 3 | t | 5 |
| 4 | e | 4 |
| 5 | X | 1 |

Ciphertext blocco 2: pos1=X, pos2=t, pos3=a, pos4=e, pos5=t → **X t a e t**

**Ciphertext con $K_1$:** "SCIOA XTAET"

---

**Applicazione di $K_2$:**

**Blocco 1 con $K_2$:** plaintext = c(1) i(2) a(3) o(4) s(5)

| Input pos | Lettera | Output pos ($K_2$) |
|-----------|---------|------------------|
| 1 | c | 2 |
| 2 | i | 5 |
| 3 | a | 4 |
| 4 | o | 3 |
| 5 | s | 1 |

Ciphertext blocco 1: pos1=s, pos2=c, pos3=o, pos4=a, pos5=i → **s c o a i**

**Blocco 2 con $K_2$:** plaintext = t(1) a(2) t(3) e(4) X(5)

| Input pos | Lettera | Output pos ($K_2$) |
|-----------|---------|------------------|
| 1 | t | 2 |
| 2 | a | 5 |
| 3 | t | 4 |
| 4 | e | 3 |
| 5 | X | 1 |

Ciphertext blocco 2: pos1=X, pos2=t, pos3=e, pos4=t, pos5=a → **X t e t a**

**Ciphertext con $K_2$:** "SCOAI XTETA"

#### Parte d — Composizione $P_{k_1}(P_{k_2}(m))$ (15 punti)

**Calcolo della permutazione composta $K_1 \circ K_2$:**

La composizione $P_{k_1}(P_{k_2}(m))$ significa: prima si applica $K_2$, poi $K_1$.

La permutazione risultante $K_{comp}$ è: $K_{comp}(i) = K_1(K_2(i))$, dove $K_2(i)$ è la posizione dopo $K_2$ e $K_1(\cdot)$ è l'ulteriore spostamento.

Calcoliamo $K_{comp}$ per ogni posizione $i \in \{1,2,3,4,5\}$:

| $i$ | $K_2(i)$ | $K_1(K_2(i))$ | $K_{comp}(i)$ |
|-----|----------|--------------|--------------|
| 1 | 2 | $K_1(2) = 3$ | 3 |
| 2 | 5 | $K_1(5) = 1$ | 1 |
| 3 | 4 | $K_1(4) = 4$ | 4 |
| 4 | 3 | $K_1(3) = 5$ | 5 |
| 5 | 1 | $K_1(1) = 2$ | 2 |

La permutazione composta è:

$$K_{comp}: \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 3 & 1 & 4 & 5 & 2 \end{pmatrix}$$

**Verifica applicando la composizione al blocco 1:** plaintext = c i a o s

| Input pos | Lettera | $K_2$ output | $K_1$ output | Posizione finale |
|-----------|---------|-------------|-------------|-----------------|
| 1 (c) | c | pos 2 | pos 3 | 3 |
| 2 (i) | i | pos 5 | pos 1 | 1 |
| 3 (a) | a | pos 4 | pos 5 | 5 |
| 4 (o) | o | pos 3 | pos 4 | 4 → wait, $K_1(3)=5$... |

Correggo: applicare $P_{k_2}$ poi $P_{k_1}$ significa:
- Prima $P_{k_2}$: la lettera in input-pos $i$ va in output-pos $K_2(i)$. Il blocco diventa (vedi risultato sopra): **s c o a i** (per il blocco 1).
- Poi $P_{k_1}$ su **s c o a i**: la lettera in posizione 1 (s) va in posizione $K_1(1) = 2$; la lettera in posizione 2 (c) va in posizione $K_1(2)=3$; la lettera in posizione 3 (o) va in $K_1(3)=5$; la lettera in posizione 4 (a) va in $K_1(4)=4$; la lettera in posizione 5 (i) va in $K_1(5)=1$.

Risultato composizione blocco 1: pos1=i, pos2=s, pos3=c, pos4=a, pos5=o → **i s c a o**

**Livello di sicurezza del cifrario composto:**

1. **La composizione è ancora un cifrario a permutazione:** la composizione di due permutazioni è ancora una permutazione. Quindi $P_{k_1}(P_{k_2}(\cdot))$ è equivalente a un unico cifrario di trasposizione con chiave $K_{comp}$.

2. **Nessun aumento di sicurezza rispetto a un singolo cifrario a permutazione:** poiché la composizione rimane nello stesso spazio dei cifrari a permutazione, la sicurezza del cifrario composto è identica a quella di un singolo cifrario con la chiave equivalente $K_{comp}$. Analogamente a quanto visto per il cifrario affine (chiusura algebrica), la famiglia dei cifrari a permutazione è chiusa rispetto alla composizione.

3. **Vulnerabilità all'analisi delle frequenze:** il cifrario a permutazione, anche composto, preserva le frequenze delle singole lettere. Un'analisi frequenziale sul ciphertext rivela le stesse frequenze del plaintext (solo le posizioni cambiano).

4. **Attacco con known plaintext:** con un solo blocco di plaintext e il corrispondente ciphertext, un avversario può ricostruire la permutazione chiave. Per $d=5$, bastano 5 caratteri noti per rivelare $K_{comp}$ completamente.

5. **Sicurezza pratica molto bassa:** i cifrari a permutazione hanno un'analisi crittografica elementare. Non sono adatti ad applicazioni moderne.

> 💡 I cifrari a permutazione sono utili come **componente** di cifrari più complessi (come nella struttura P-box di DES), non come cifrari stand-alone. La vera sicurezza dei cifrari moderni deriva dall'alternanza di sostituzioni non lineari e permutazioni lineari (struttura SPN di AES, struttura Feistel di DES).
