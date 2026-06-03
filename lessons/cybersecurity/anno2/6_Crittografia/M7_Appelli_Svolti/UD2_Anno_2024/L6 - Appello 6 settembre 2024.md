# Appello del 6 settembre 2024 — Soluzione completa

> 📌 **Informazioni appello:** 06/09/2024, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L4 - Cifrari a permutazione.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Cifrari%20a%20permutazione.md) — cifrari a permutazione, composizione, analisi
> - [`../../M4_Funzioni_Hash_e_Mac/UD3/L3 - HMAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L3%20-%20HMAC.md) — MAC e HMAC con funzione hash nota
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — equivalenza RSA-fattorizzazione, ottimizzazione esponenziale
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema (4,4) e (2,3) con calcoli in $\mathbb{Z}_{13}$ e $\mathbb{Z}_{11}$

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

- [`../../M1_Crittografia_Classica/UD2/L4 - Cifrari a permutazione.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Cifrari%20a%20permutazione.md) — definizione, applicazione, composizione

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

---

## Domanda 2 — Funzioni MAC e HMAC (25 punti)

### Traccia originale

> **(25 punti) Funzioni MAC.**
>
> a. (10 punti) Descrivere caratteristiche e applicazioni delle funzioni MAC.
>
> b. (15 punti) Descrivere l'approccio HMAC e fornire una costruzione con una funzione hash nota.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD2/L1 - MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD2/L1%20-%20MAC.md) — definizione e applicazioni MAC
- [`../../M4_Funzioni_Hash_e_Mac/UD3/L3 - HMAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L3%20-%20HMAC.md) — costruzione HMAC

### Soluzione dettagliata

#### Parte a — Caratteristiche e applicazioni delle funzioni MAC (10 punti)

> 📌 Questa domanda è identica alla D3a dell'appello del 20/02/2024 (L2). Si rimanda a quella soluzione per la trattazione completa. Di seguito un riepilogo strutturato.

**Definizione:** un MAC è $t = MAC(k, m)$ con chiave simmetrica $k$, messaggio $m$, tag di autenticazione $t$.

**Proprietà fondamentali:**
1. **Autenticità:** chi conosce $k$ può generare e verificare il tag → garantisce che il messaggio venga da un'entità che condivide $k$.
2. **Integrità:** qualsiasi modifica a $m$ cambia $t$ con alta probabilità → rileva manomissioni.
3. **Non ripudiabilità limitata:** entrambe le parti possono generare il tag → non fornisce non-ripudiabilità forte (a differenza della firma digitale asimmetrica).

**Sicurezza formale:** resistenza alla falsificazione esistenziale (EUF-CMA): un avversario che osserva $(m_i, t_i)$ non può produrre un tag valido per un nuovo messaggio.

**Applicazioni:**
- Autenticazione dei pacchetti in TLS, SSH, IPsec
- Integrità dei file e del software
- Autenticazione nelle API (HMAC-SHA256 in JWT, AWS Signature)
- Cookie di sessione firmati
- Codici di autenticazione nei protocolli di banking

> ⚠️ Il MAC da solo non garantisce confidenzialità. Per dati sensibili si usa Authenticated Encryption (AES-GCM = cifra + autenticazione).

#### Parte b — HMAC con funzione hash nota (15 punti)

**Motivazione di HMAC:**

Le costruzioni naive basate su hash (prefisso o suffisso della chiave) sono vulnerabili ad attacchi:
- $H(k \| m)$: vulnerabile all'attacco di estensione della lunghezza (length extension attack)
- $H(m \| k)$: vulnerabile ad alcune varianti di attacco (a seconda della struttura della hash)

**Costruzione HMAC (RFC 2104):**

$$\text{HMAC}(k, m) = H\big((k' \oplus \text{opad}) \| H((k' \oplus \text{ipad}) \| m)\big)$$

dove:
- $H$: funzione hash crittografica (es. SHA-256 con blocco di 512 bit e output di 256 bit)
- $k'$: chiave normalizzata alla dimensione del blocco di $H$:
  - Se $|k| > b$ (dimensione blocco): $k' = H(k)$, poi padding con $\mathbf{0}$ a $b$ byte
  - Se $|k| \leq b$: $k' = k \| \mathbf{0}^{b - |k|}$ (padding con zeri a destra fino a $b$ byte)
- $\text{ipad}$ = $0x36$ ripetuto $b$ volte (byte $0x36 = 00110110$)
- $\text{opad}$ = $0x5C$ ripetuto $b$ volte (byte $0x5C = 01011100$)

**Algoritmo HMAC-SHA256 passo per passo:**

```
Costanti (per SHA-256, blocco b=64 byte, output n=32 byte):
  ipad = 0x3636...36 (64 byte)
  opad = 0x5C5C...5C (64 byte)

1. Normalizzazione della chiave:
   se |k| > 64 byte: k' = SHA-256(k)
   se |k| < 64 byte: k' = k || 0x00...0 fino a 64 byte
   (se |k| == 64 byte: k' = k)

2. Hash interno:
   inner = SHA-256( (k' XOR ipad) || m )
   // (k' XOR ipad) è un blocco di 64 byte
   // inner è di 32 byte

3. Hash esterno:
   HMAC = SHA-256( (k' XOR opad) || inner )
   // (k' XOR opad) è 64 byte, inner è 32 byte → input di 96 byte
   // HMAC è di 32 byte

Output: HMAC (256 bit = 32 byte)
```

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché ipad e opad sono diversi:**

Se ipad = opad, allora $(k' \oplus \text{ipad}) = (k' \oplus \text{opad})$ e l'hash esterno userebbe lo stesso valore dell'hash interno come chiave "aggiuntiva", creando una struttura ciclica potenzialmente vulnerabile. Con ipad e opad diversi, le due valutazioni di hash sono crittograficamente indipendenti.

**Proprietà di sicurezza di HMAC:**

1. **Resistenza alla length extension:** l'hash esterno $H(k' \oplus \text{opad} \| \cdot)$ usa come input il risultato dell'hash interno, non un valore che l'avversario può estendere conoscendo solo $H(k' \oplus \text{ipad} \| m)$.

2. **Sicurezza riduttiva:** HMAC è provabilmente sicuro se $H$ è una PRF (Pseudo-Random Function). La sicurezza si riduce alla difficoltà di distinguere $H$ da una funzione casuale.

3. **Resistenza alle collisioni hash:** HMAC rimane sicuro anche se vengono trovate collisioni per $H$ (senza chiave), purché $H$ con chiave sia una buona PRF.

**Confronto HMAC-SHA1 vs HMAC-SHA256:**

| Parametro | HMAC-SHA1 | HMAC-SHA256 |
|-----------|-----------|-------------|
| Output | 160 bit (20 byte) | 256 bit (32 byte) |
| Dimensione blocco | 512 bit (64 byte) | 512 bit (64 byte) |
| Sicurezza del tag | ~80 bit | ~128 bit |
| Stato attuale | Sicuro per MAC (non per firme) | Raccomandato |

> 📌 Anche se SHA-1 è considerata compromessa per gli usi che richiedono resistenza alle collisioni (firme digitali, certificati), **HMAC-SHA-1 rimane sicuro** per l'autenticazione dei messaggi. Questo perché la sicurezza del MAC non dipende dalla resistenza alle collisioni di $H$, ma dalla sua proprietà di PRF con chiave.

---

## Domanda 3 — RSA: equivalenza con fattorizzazione e ottimizzazione (20 punti)

### Traccia originale

> **(20 punti) Cifratura asimmetrica RSA.**
>
> a. (10) Discutere l'equivalenza o meno tra il problema RSA e la fattorizzazione dei numeri primi.
>
> b. (10) Discutere i metodi per ottimizzare il calcolo esponenziale nella fase di cifratura.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — sicurezza RSA, esponenziazione rapida

### Soluzione dettagliata

#### Parte a — Equivalenza tra problema RSA e fattorizzazione (10 punti)

**Il problema RSA (RSA Problem):**

Dato il modulo $n$, l'esponente pubblico $e$, e il ciphertext $C = M^e \bmod n$, trovare $M$.

**Il problema della fattorizzazione (Integer Factorization Problem):**

Dato $n = pq$, trovare $p$ e $q$.

**Relazione tra i due problemi:**

**Fattorizzare → Rompere RSA:**

Se si riesce a fattorizzare $n = pq$, si possono calcolare $\varphi(n) = (p-1)(q-1)$ e quindi la chiave privata $d = e^{-1} \bmod \varphi(n)$ tramite l'algoritmo di Euclide esteso. Avendo $d$, si può decifrare qualsiasi ciphertext: $M = C^d \bmod n$.

Quindi: **fattorizzare $n$ implica rompere RSA.**

**Rompere RSA → Fattorizzare:**

La direzione opposta è più sottile. È noto che:
- Se si conosce $\varphi(n)$ (la funzione di Eulero), si può fattorizzare $n$ (come mostrato nell'Appello L3, D3b).
- Se si conosce $d$ (la chiave privata), si può calcolare $ed - 1 = k\varphi(n)$ per qualche $k$, e da lì fattorizzare $n$ con alta probabilità usando tecniche algebriche.

Quindi: **rompere RSA (trovare $d$ o $\varphi(n)$) implica poter fattorizzare $n$.**

**Conclusione sull'equivalenza:**

Rompere RSA **nell'accezione di trovare la chiave privata $d$** è computazionalmente equivalente a fattorizzare $n$: ognuno dei due problemi si riduce polinomialmente all'altro.

**Tuttavia**, rompere RSA **nell'accezione più debole di decriptare un singolo ciphertext** (recuperare $M$ da $C$ senza necessariamente trovare $d$) potrebbe essere più facile della fattorizzazione. Non è noto se il problema RSA (in questa forma debole) sia computazionalmente equivalente alla fattorizzazione:
- Non si sa se esistano algoritmi che riescano a decifrare messaggi RSA senza fattorizzare $n$.
- La congettura è che siano equivalenti, ma non è dimostrata.

> ⚠️ Quindi l'affermazione "la sicurezza di RSA si basa sulla difficoltà della fattorizzazione" è sostanzialmente corretta ma non precisissima: la sicurezza si basa sulla difficoltà del problema RSA, che è almeno difficile quanto la fattorizzazione (ma potrebbero non essere esattamente equivalenti nel senso più forte).

> 📌 Dal punto di vista pratico: se qualcuno riuscisse a fattorizzare efficientemente numeri di 2048+ bit, RSA sarebbe completamente compromesso. Viceversa, non si conoscono attacchi a RSA che non richiedano (implicitamente o esplicitamente) la fattorizzazione.

#### Parte b — Ottimizzazione del calcolo esponenziale in RSA (10 punti)

**Problema:** calcolare $M^e \bmod n$ (cifratura) o $C^d \bmod n$ (decifratura) con $e$, $d$ dell'ordine di $2^{1024}$ o più. La moltiplicazione ripetuta richiederebbe $e$ (o $d$) passi: computazionalmente impossibile.

**1. Esponenziazione rapida (Square-and-Multiply / Fast Exponentiation):**

L'algoritmo principale per ridurre il numero di moltiplicazioni da $O(e)$ a $O(\log e)$.

**Algoritmo (versione bit-by-bit, left-to-right):**

```
Input: base M, esponente e, modulo n
Scrivi e in binario: e = (b_k b_{k-1} ... b_1 b_0)_2

result = 1
Per i = k downto 0:
    result = result^2 mod n   // square
    if b_i == 1:
        result = result * M mod n  // multiply
Output: result
```

**Complessità:** richiede al massimo $2 \log_2 e$ moltiplicazioni modulari (al più $\log_2 e$ operazioni di square + al più $\log_2 e$ operazioni di multiply). Per $e \approx 2^{1024}$, questo è $\approx 2048$ moltiplicazioni invece di $2^{1024}$.

**Esempio (già visto negli appelli precedenti):** $3^{23} \bmod 55$ con $23 = 10111_2$: calcolo in $\lfloor \log_2 23 \rfloor = 4$ passi di square + operazioni di multiply.

**2. Scelta di $e$ piccolo (esponente pubblico ridotto):**

Per la cifratura (che usa $e$), scegliere un $e$ piccolo riduce il costo. Il valore standard è $e = 65537 = 2^{16} + 1$, che ha solo 2 bit a 1 nella rappresentazione binaria. La cifratura richiede 16 operazioni di square + 1 di multiply = 17 operazioni totali.

> ⚠️ L'uso di $e$ molto piccolo (come $e=3$) può essere vulnerabile in certi scenari (broadcast attack, low-exponent attack). $e=65537$ è un buon compromesso tra efficienza e sicurezza.

**3. Teorema Cinese dei Resti (CRT) per la decifratura:**

La decifratura usa $d$ che è dell'ordine di $\varphi(n) \approx n$, quindi potenzialmente molto lento. Usando il CRT, si divide il calcolo modulo $n$ in due calcoli modulo $p$ e $q$:

$$M_p = C^{d_p} \bmod p \quad \text{con } d_p = d \bmod (p-1)$$
$$M_q = C^{d_q} \bmod q \quad \text{con } d_q = d \bmod (q-1)$$

Poi si combina con il CRT per trovare $M \bmod n$.

**Vantaggi del CRT:**
- $p$ e $q$ sono circa la metà della dimensione di $n$ (in bit), quindi $d_p$ e $d_q$ sono circa metà di $d$.
- L'esponenziazione modulo un numero di $n/2$ bit è circa 4 volte più veloce di quella modulo $n$ (il costo è quadratico nella dimensione).
- Complessivamente, il CRT accelera la decifratura di un fattore ~4.

**4. Uso delle finestre (Windowed Exponentiation):**

Invece di elaborare un bit alla volta, si elaborano $w$ bit alla volta (window di dimensione $w$). Si precalcolano $2^w$ multipli di $M$ e si usano per ridurre il numero di operazioni di multiply. Per $w=4$, si risparmiano circa il 25% delle moltiplicazioni rispetto al metodo standard.

**5. Montgomery Multiplication:**

Tecnica specializzata per eseguire moltiplicazioni modulari senza divisioni esplicite (che sono costose). Converte i numeri in "forma di Montgomery" dove la riduzione modulare diventa uno shift. Molto usata in implementazioni hardware e software ottimizzate.

> ✅ **Riepilogo delle ottimizzazioni:**
> 1. Square-and-multiply: $O(\log e)$ invece di $O(e)$ moltiplicazioni
> 2. $e = 65537$: cifratura velocissima (17 operazioni)
> 3. CRT per decifratura: accelerazione 4×
> 4. Windowed exponentiation: ulteriore riduzione del 20-30%
> 5. Montgomery multiplication: riduzione modulare efficiente

---

## Domanda 4 — Secret Sharing numerico (25 punti)

### Traccia originale

> **(25 punti) Secret Sharing.**
>
> a. (10 punti) Siano dati i seguenti share in uno schema $(4, 4)$: $P_1 = 6$, $P_2 = 5$, $P_3 = 4$, $P_4 = 9$. Ricostruire il segreto, sapendo che si opera in $\mathbb{Z}_{13}$.
>
> b. (15 punti) Per uno schema $(2, 3)$ in $\mathbb{Z}_{11}$, ricostruire il segreto avendo le due share $P_1 = 3$, $P_2 = 5$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — secret sharing di Shamir, interpolazione di Lagrange

### Soluzione dettagliata

#### Parte a — Schema $(4, 4)$ in $\mathbb{Z}_{13}$ con share $P_1=6, P_2=5, P_3=4, P_4=9$ (10 punti)

Nello schema $(n,n)$ con somma modulare, il segreto si ricostruisce sommando tutte le $n$ share:

$$S = P_1 + P_2 + P_3 + P_4 \bmod 13$$

$$S = 6 + 5 + 4 + 9 \bmod 13 = 24 \bmod 13$$

$$24 = 1 \cdot 13 + 11 \Rightarrow 24 \bmod 13 = 11$$

**Segreto ricostruito:** $\boxed{S = 11}$

**Verifica della coerenza:** $6 + 5 + 4 + 9 = 24$ e $24 \bmod 13 = 11$ ✓

#### Parte b — Schema $(2, 3)$ in $\mathbb{Z}_{11}$ con share $P_1=3$ (cioè $s(1)=3$) e $P_2=5$ (cioè $s(2)=5$) (15 punti)

Lo schema $(2,3)$ di Shamir usa un polinomio di grado $k-1=1$:

$$f(x) = a_0 + a_1 x \bmod 11$$

dove $a_0 = S$ è il segreto. Le share sono $s(i) = f(i)$.

**Dati:** $s(1) = f(1) = 3$ e $s(2) = f(2) = 5$.

**Metodo 1: Sistema lineare (più rapido per $k=2$):**

$$\begin{cases} f(1) = a_0 + a_1 = 3 \pmod{11} \\ f(2) = a_0 + 2a_1 = 5 \pmod{11} \end{cases}$$

Sottraiamo la prima dalla seconda:

$$a_1 = 5 - 3 = 2 \pmod{11}$$

Dalla prima equazione:

$$a_0 = 3 - a_1 = 3 - 2 = 1 \pmod{11}$$

**Segreto:** $S = a_0 = 1$.

**Metodo 2: Interpolazione di Lagrange:**

$$S = f(0) = s(1) \cdot L_1(0) + s(2) \cdot L_2(0) \bmod 11$$

$$L_1(0) = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \bmod 11$$

$$L_2(0) = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \bmod 11$$

$$S = 3 \cdot 2 + 5 \cdot 10 \bmod 11 = 6 + 50 \bmod 11 = 56 \bmod 11$$

$$56 = 5 \cdot 11 + 1 \Rightarrow 56 \bmod 11 = 1$$

**Segreto ricostruito:** $\boxed{S = 1}$ (entrambi i metodi concordano ✓)

**Verifica:** $f(x) = 1 + 2x$:
- $f(1) = 1 + 2 = 3$ ✓
- $f(2) = 1 + 4 = 5$ ✓
- $f(3) = 1 + 6 = 7$ (share del terzo partecipante, non fornita ma coerente)

> 💡 Per schemi $(2,n)$ il metodo del sistema lineare è molto più veloce dell'interpolazione di Lagrange generale. Per schemi con $k \geq 3$ si deve usare l'interpolazione di Lagrange con inversioni modulari.

---

## Pattern e osservazioni per l'esame

Questo è il primo appello della sessione autunnale (settembre 2024).

- **D1 (30 punti):** cifrari a permutazione con composizione. La parte d (15 punti) richiede di calcolare la permutazione composta e discutere la sicurezza. La permutazione composta si calcola componendo le due funzioni di permutazione.

- **D2 (25 punti):** MAC e HMAC. La parte b chiede di fornire la costruzione HMAC con "una funzione hash nota" (non necessariamente SHA-1 come in febbraio). Usare SHA-256 è pienamente accettabile e dimostra conoscenza aggiornata.

- **D3 (20 punti):** RSA: equivalenza con fattorizzazione (domanda teorica fine) e ottimizzazione dell'esponenziale. La parte a richiede di distinguere tra "rompere RSA = trovare $d$" (equivalente a fattorizzare) e "rompere RSA = decifrare un singolo messaggio" (non provabilmente equivalente). La parte b richiede di conoscere almeno square-and-multiply e CRT.

- **D4 (25 punti):** secret sharing numerico. Schema $(4,4)$: semplicissima somma modulare. Schema $(2,3)$: sistema lineare in $\mathbb{Z}_{11}$.

**Consigli specifici:**

1. Per D1d (composizione permutazioni): fare la tabella $i \to K_2(i) \to K_1(K_2(i))$ per $i=1,...,5$. È un calcolo meccanico.
2. Per D3a (equivalenza RSA-fattorizzazione): essere precisi nella direzione delle implicazioni. Dimostrare entrambe le direzioni.
3. Per D4b: preferire il metodo del sistema lineare rispetto all'interpolazione di Lagrange quando $k=2$ (molto più rapido).
