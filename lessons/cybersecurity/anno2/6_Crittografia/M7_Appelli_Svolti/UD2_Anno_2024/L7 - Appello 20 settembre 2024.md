# Appello del 20 settembre 2024 — Soluzione completa

> 📌 **Informazioni appello:** 20/09/2024, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L5 - Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD2/L5%20-%20Cifrario%20di%20Hill.md) — Hill: definizione, inversione matrice mod 26, cifratura/decifratura, attacchi
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura di Feistel, funzione round aritmetica
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà funzioni hash, funzione identità, paradosso compleanno
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA: generazione chiavi, scelta $d$, cifratura, decifratura, firma

---

## Domanda 1 — Cifrario di Hill (20 punti)

### Traccia originale

> **(20 punti) Cifratura classica.**
>
> a. (10) Discutere le caratteristiche e i possibili attacchi al cifrario di Hill.
>
> b. (10) Si consideri la matrice $\begin{pmatrix} 1 & 4 \\ 1 & 5 \end{pmatrix}$. Se la matrice è valida (provarlo) cifrare e decifrare la parola "ciao".

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L5 - Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD2/L5%20-%20Cifrario%20di%20Hill.md) — Hill: inversione, cifratura, attacchi
- [`../../M1_Crittografia_Classica/UD1/L2 - Teoria dei numeri.md`](../../M1_Crittografia_Classica/UD1/L2%20-%20Teoria%20dei%20numeri.md) — aritmetica modulare, determinante, inversi

### Soluzione dettagliata

#### Parte a — Caratteristiche e attacchi al cifrario di Hill (10 punti)

**Definizione:**

Il **cifrario di Hill** (proposto da Lester Hill nel 1929) è un cifrario a sostituzione poligrammica basato sull'algebra lineare modulo 26. Opera su blocchi di $d$ lettere usando una matrice chiave $K$ di dimensione $d \times d$ a coefficienti in $\mathbb{Z}_{26}$.

**Schema di cifratura:**

1. Convertire il plaintext in vettori di $d$ valori in $\mathbb{Z}_{26}$ (A=0, B=1, ..., Z=25).
2. Per ogni blocco $\mathbf{p} \in \mathbb{Z}_{26}^d$, calcolare il blocco cifrato $\mathbf{c} = K\mathbf{p} \bmod 26$.
3. Riconvertire in lettere.

**Requisiti sulla chiave:**

La matrice $K$ deve essere **invertibile modulo 26**, cioè deve esistere $K^{-1}$ tale che $KK^{-1} \equiv I \pmod{26}$.

Condizione necessaria e sufficiente: $\gcd(\det(K), 26) = 1$, ovvero il determinante di $K$ deve essere coprimo con 26.

Poiché $26 = 2 \times 13$, il determinante deve essere dispari e non divisibile per 13.

**Decifratura:** $\mathbf{p} = K^{-1}\mathbf{c} \bmod 26$.

**Caratteristiche:**

1. **Resistenza all'analisi delle frequenze delle singole lettere:** poiché cifra blocchi di $d$ lettere, le frequenze delle singole lettere del plaintext non sono direttamente visibili nel ciphertext. Con $d=2$, si oscurano le frequenze dei monogrammi.

2. **Diffusione:** ogni lettera del ciphertext dipende da $d$ lettere del plaintext (prodotto matrice-vettore). Questo introduce diffusione locale.

3. **Linearità:** il cifrario è **completamente lineare** (operazioni di algebra lineare su $\mathbb{Z}_{26}$). Questa è la sua principale debolezza.

**Attacchi al cifrario di Hill:**

**1. Attacco con testo in chiaro noto (known-plaintext attack):**

Questo è il principale attacco al cifrario di Hill e ne rivela la debolezza fondamentale.

Se un avversario conosce $d$ coppie di blocchi (plaintext, ciphertext) linearmente indipendenti:
$$\mathbf{c}_1 = K\mathbf{p}_1, \quad \mathbf{c}_2 = K\mathbf{p}_2, \quad \ldots, \quad \mathbf{c}_d = K\mathbf{p}_d$$

Può costruire la matrice delle colonne di plaintext $P = [\mathbf{p}_1 | \mathbf{p}_2 | \cdots | \mathbf{p}_d]$ e quella dei ciphertext $C = [\mathbf{c}_1 | \mathbf{c}_2 | \cdots | \mathbf{c}_d]$.

Poiché $C = KP$, se $P$ è invertibile modulo 26:

$$K = CP^{-1} \bmod 26$$

Con soli $d^2$ caratteri noti (per $d=2$: 4 caratteri), la chiave è completamente recuperata.

**2. Analisi delle frequenze dei digrammi (ciphertext-only per $d=2$):**

Per $d=2$, ci sono $26^2 = 676$ possibili digrammi. Le frequenze dei digrammi nel ciphertext sono una permutazione lineare delle frequenze dei digrammi nel plaintext. Con testo sufficientemente lungo, si può usare un attacco statistico.

**3. Debolezza intrinseca della linearità:**

Qualsiasi cifrario completamente lineare è vulnerabile alla crittoanalisi lineare. L'equazione $\mathbf{c} = K\mathbf{p}$ è un sistema di equazioni lineari in $\mathbb{Z}_{26}$: risolverlo richiede solo algebra elementare.

> ⚠️ Il cifrario di Hill non è sicuro per uso pratico moderno. Il suo interesse è puramente didattico: illustra come l'algebra lineare possa essere applicata alla crittografia, e come la linearità sia una debolezza fondamentale che i cifrari moderni (con S-box non lineari) evitano esplicitamente.

#### Parte b — Verifica validità matrice e cifratura/decifratura di "ciao" (10 punti)

**Matrice chiave:**

$$K = \begin{pmatrix} 1 & 4 \\ 1 & 5 \end{pmatrix}$$

**Verifica della validità (invertibilità mod 26):**

Calcolo del determinante:

$$\det(K) = 1 \cdot 5 - 4 \cdot 1 = 5 - 4 = 1$$

Verifica: $\gcd(\det(K), 26) = \gcd(1, 26) = 1$ ✓

La matrice è invertibile modulo 26 (il determinante 1 è già il suo stesso inverso modulo qualsiasi numero).

**Calcolo di $K^{-1} \bmod 26$:**

La formula per l'inversa di una matrice $2 \times 2$ è:

$$K^{-1} = (\det K)^{-1} \begin{pmatrix} d & -b \\ -c & a \end{pmatrix} \bmod 26$$

Per $K = \begin{pmatrix} a & b \\ c & d \end{pmatrix} = \begin{pmatrix} 1 & 4 \\ 1 & 5 \end{pmatrix}$:

$$K^{-1} = 1^{-1} \begin{pmatrix} 5 & -4 \\ -1 & 1 \end{pmatrix} \bmod 26 = \begin{pmatrix} 5 & -4 \\ -1 & 1 \end{pmatrix} \bmod 26$$

Riduciamo modulo 26 i valori negativi:
- $-4 \bmod 26 = 22$
- $-1 \bmod 26 = 25$

$$K^{-1} = \begin{pmatrix} 5 & 22 \\ 25 & 1 \end{pmatrix} \bmod 26$$

**Verifica:** $KK^{-1} = \begin{pmatrix}1&4\\1&5\end{pmatrix}\begin{pmatrix}5&22\\25&1\end{pmatrix} \bmod 26$:

- $(1,1)$: $1\cdot5 + 4\cdot25 = 5 + 100 = 105 \bmod 26 = 105 - 4\cdot26 = 105-104=1$ ✓
- $(1,2)$: $1\cdot22 + 4\cdot1 = 22 + 4 = 26 \bmod 26 = 0$ ✓
- $(2,1)$: $1\cdot5 + 5\cdot25 = 5 + 125 = 130 \bmod 26 = 0$ ✓
- $(2,2)$: $1\cdot22 + 5\cdot1 = 22 + 5 = 27 \bmod 26 = 1$ ✓

$KK^{-1} = I \bmod 26$ ✓

**Codifica di "ciao":**

Usiamo la codifica standard A=0, B=1, C=2, D=3, E=4, F=5, G=6, H=7, I=8, J=9, K=10, L=11, M=12, N=13, O=14, P=15, Q=16, R=17, S=18, T=19, U=20, V=21, W=22, X=23, Y=24, Z=25.

| Lettera | Valore |
|---------|--------|
| C | 2 |
| I | 8 |
| A | 0 |
| O | 14 |

La parola "ciao" ha 4 lettere, quindi forma due blocchi di 2:
- Blocco 1: $\mathbf{p}_1 = \begin{pmatrix} 2 \\ 8 \end{pmatrix}$ (CI)
- Blocco 2: $\mathbf{p}_2 = \begin{pmatrix} 0 \\ 14 \end{pmatrix}$ (AO)

**Cifratura del blocco 1:**

$$\mathbf{c}_1 = K\mathbf{p}_1 = \begin{pmatrix}1&4\\1&5\end{pmatrix}\begin{pmatrix}2\\8\end{pmatrix} \bmod 26$$

$$c_1[1] = 1\cdot2 + 4\cdot8 = 2 + 32 = 34 \bmod 26 = 8 \quad \to \quad \text{I}$$
$$c_1[2] = 1\cdot2 + 5\cdot8 = 2 + 40 = 42 \bmod 26 = 16 \quad \to \quad \text{Q}$$

Blocco 1 cifrato: **IQ**

**Cifratura del blocco 2:**

$$\mathbf{c}_2 = K\mathbf{p}_2 = \begin{pmatrix}1&4\\1&5\end{pmatrix}\begin{pmatrix}0\\14\end{pmatrix} \bmod 26$$

$$c_2[1] = 1\cdot0 + 4\cdot14 = 0 + 56 = 56 \bmod 26 = 56 - 2\cdot26 = 4 \quad \to \quad \text{E}$$
$$c_2[2] = 1\cdot0 + 5\cdot14 = 0 + 70 = 70 \bmod 26 = 70 - 2\cdot26 = 18 \quad \to \quad \text{S}$$

Blocco 2 cifrato: **ES**

**Ciphertext:** IQES

**Decifratura del blocco 1 (verifica):**

$$\mathbf{p}_1 = K^{-1}\mathbf{c}_1 = \begin{pmatrix}5&22\\25&1\end{pmatrix}\begin{pmatrix}8\\16\end{pmatrix} \bmod 26$$

$$p_1[1] = 5\cdot8 + 22\cdot16 = 40 + 352 = 392 \bmod 26$$
$$392 = 15\cdot26 + 2 \Rightarrow 392 \bmod 26 = 2 \quad \to \quad \text{C} \checkmark$$

$$p_1[2] = 25\cdot8 + 1\cdot16 = 200 + 16 = 216 \bmod 26$$
$$216 = 8\cdot26 + 8 \Rightarrow 216 \bmod 26 = 8 \quad \to \quad \text{I} \checkmark$$

**Decifratura del blocco 2 (verifica):**

$$\mathbf{p}_2 = K^{-1}\mathbf{c}_2 = \begin{pmatrix}5&22\\25&1\end{pmatrix}\begin{pmatrix}4\\18\end{pmatrix} \bmod 26$$

$$p_2[1] = 5\cdot4 + 22\cdot18 = 20 + 396 = 416 \bmod 26$$
$$416 = 16\cdot26 = 416 \Rightarrow 416 \bmod 26 = 0 \quad \to \quad \text{A} \checkmark$$

$$p_2[2] = 25\cdot4 + 1\cdot18 = 100 + 18 = 118 \bmod 26$$
$$118 = 4\cdot26 + 14 \Rightarrow 118 \bmod 26 = 14 \quad \to \quad \text{O} \checkmark$$

**Riepilogo:**
- Plaintext: CIAO → Ciphertext: IQES ✓
- Decifratura di IQES → CIAO ✓

---

## Domanda 2 — Rete di Feistel a 2 round con funzione aritmetica mod 15 (35 punti)

### Traccia originale

> **(35 punti) Cifratura simmetrica.**
> Si consideri una rete di Feistel a 2 fasi in cui la chiave è lunga la metà del blocco e la funzione round è definita come $f_i(x, K) = (2 \cdot x \cdot K) \bmod 15$, per $i = 1, 2$, dove $K$ appartiene a $\mathbb{Z}_{15}$.
>
> i. (10) Si illustri come si ottiene il messaggio cifrato $C = L_2 R_2$ dalla cifratura del messaggio $M = L_0 R_0$.
>
> ii. (15) Si cifri il messaggio 1010 1100 con $K = 7$.
>
> iii. (10) Si facciano considerazioni sulla sicurezza, considerando il valore numerico della chiave.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura Feistel
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — analogia DES

### Soluzione dettagliata

#### Parte i — Schema generale di cifratura Feistel 2 round (10 punti)

**Struttura della rete di Feistel a 2 round:**

Il messaggio $M = L_0 R_0$ è diviso in due metà di eguale lunghezza (4 bit ciascuna nel nostro caso, dato che il blocco è di 8 bit e la chiave è lunga la metà del blocco).

La funzione round $f_i(x, K) = (2 \cdot x \cdot K) \bmod 15$ dipende dal valore decimale di $x$ (metà destra del blocco) e dalla chiave $K \in \mathbb{Z}_{15}$.

**Round 1:**

$$L_1 = R_0$$
$$R_1 = L_0 \oplus f_1(R_0, K) = L_0 \oplus [(2 \cdot R_0 \cdot K) \bmod 15]$$

**Round 2:**

$$L_2 = R_1$$
$$R_2 = L_1 \oplus f_2(R_1, K) = R_0 \oplus [(2 \cdot R_1 \cdot K) \bmod 15]$$

**Ciphertext:**

$$C = L_2 R_2 = R_1\ R_2$$

dove:
- $R_1 = L_0 \oplus [(2 \cdot \text{dec}(R_0) \cdot K) \bmod 15]$ (con $\oplus$ su 4 bit e $(2 \cdot \text{dec}(R_0) \cdot K) \bmod 15$ come valore intero da riconvertire in 4 bit)
- $R_2 = R_0 \oplus [(2 \cdot \text{dec}(R_1) \cdot K) \bmod 15]$

> ⚠️ Attenzione all'interpretazione della funzione $f$: il XOR è bit-a-bit su 4 bit, mentre il calcolo $(2xK) \bmod 15$ è su interi. Il risultato di $f$ (un valore in $\{0,...,14\}$, quindi a 4 bit) viene poi XORato con $L$ (anch'esso a 4 bit).

#### Parte ii — Cifratura di 1010 1100 con $K = 7$ (15 punti)

**Dati:**
- Plaintext: $M = $ 1010 1100
- $L_0 = $ 1010 $= 10_{10}$
- $R_0 = $ 1100 $= 12_{10}$
- $K = 7 \in \mathbb{Z}_{15}$

**Round 1:**

Calcolo di $f_1(R_0, K) = (2 \cdot R_0 \cdot K) \bmod 15 = (2 \cdot 12 \cdot 7) \bmod 15$:

$$2 \cdot 12 = 24$$
$$24 \cdot 7 = 168$$
$$168 \bmod 15 = 168 - 11 \cdot 15 = 168 - 165 = 3$$

Quindi $f_1(12, 7) = 3 = 0011_2$.

Aggiornamento Feistel:
$$L_1 = R_0 = 1100$$
$$R_1 = L_0 \oplus f_1(R_0, K) = 1010 \oplus 0011$$

$$1010$$
$$0011$$
$$\overline{1001}$$

Quindi $R_1 = 1001 = 9_{10}$.

Stato dopo Round 1: $L_1 = 1100$ (12), $R_1 = 1001$ (9)

**Round 2:**

Calcolo di $f_2(R_1, K) = (2 \cdot R_1 \cdot K) \bmod 15 = (2 \cdot 9 \cdot 7) \bmod 15$:

$$2 \cdot 9 = 18$$
$$18 \cdot 7 = 126$$
$$126 \bmod 15 = 126 - 8 \cdot 15 = 126 - 120 = 6$$

Quindi $f_2(9, 7) = 6 = 0110_2$.

Aggiornamento Feistel:
$$L_2 = R_1 = 1001$$
$$R_2 = L_1 \oplus f_2(R_1, K) = 1100 \oplus 0110$$

$$1100$$
$$0110$$
$$\overline{1010}$$

Quindi $R_2 = 1010 = 10_{10}$.

Stato dopo Round 2: $L_2 = 1001$ (9), $R_2 = 1010$ (10)

**Ciphertext:**

$$C = L_2 R_2 = 1001\ 1010$$

> 📌 **Riepilogo delle computazioni:**
>
> | Fase | L (bin) | L (dec) | R (bin) | R (dec) | f(R,K) |
> |------|---------|---------|---------|---------|--------|
> | Plaintext | 1010 | 10 | 1100 | 12 | — |
> | Dopo Round 1 | 1100 | 12 | 1001 | 9 | $(2\cdot12\cdot7)\bmod15=3$ |
> | Dopo Round 2 | 1001 | 9 | 1010 | 10 | $(2\cdot9\cdot7)\bmod15=6$ |

**Ciphertext finale:** $C = $ **1001 1010**

#### Parte iii — Considerazioni sulla sicurezza e il valore numerico della chiave (10 punti)

La funzione di round è $f(x, K) = (2xK) \bmod 15$.

**Analisi per valori specifici di $K$:**

**1. $K = 0$:**
$$f(x, 0) = (2 \cdot x \cdot 0) \bmod 15 = 0$$

Per qualsiasi $x$, $f = 0$. Il XOR con 0 è l'identità. Il round non cifra nulla: $R_i = L_{i-1}$ e $L_i = R_{i-1}$. Entrambi i round riducono il cifrario a una semplice trasposizione. Sicurezza **nulla**: la chiave è immediatamente rivelata se $K=0$.

**2. $K = 5$:**
$$f(x, 5) = (2x \cdot 5) \bmod 15 = (10x) \bmod 15$$

$10$ e $15$ hanno $\gcd(10, 15) = 5$. L'immagine di $x \mapsto 10x \bmod 15$ è $\{0, 5, 10\}$ (solo multipli di 5): molti valori di $x$ producono lo stesso $f$. Grande perdita di informazione.

**3. $K = 3$:**
$$f(x, 3) = (6x) \bmod 15 = (6x) \bmod 15$$

$\gcd(6, 15) = 3$. L'immagine è $\{0, 3, 6, 9, 12\}$: solo 5 valori su 16 possibili (per $x \in \{0,...,15\}$ non tutti i valori di $f$ sono raggiungibili).

**4. $K = 7$ (il caso dell'esame):**
$$f(x, 7) = (14x) \bmod 15$$

$\gcd(14, 15) = 1$: la funzione $x \mapsto 14x \bmod 15$ è una **biiezione** su $\mathbb{Z}_{15}$ (tutti i valori $0$-$14$ sono raggiunti esattamente una volta). Questo è il caso migliore per la funzione di round.

**5. Problema con $\mathbb{Z}_{15}$ (non primo):**

$15 = 3 \times 5$ non è primo. Questo significa che $\mathbb{Z}_{15}$ non è un campo: ci sono divisori di zero ($3 \cdot 5 = 15 \equiv 0$). Per certi valori di $K$ e $x$, $f(x, K) = 0$ anche con $x, K \neq 0$, il che crea "buchi" nella funzione di round e riduce la sicurezza.

**Debolezze generali:**

1. **Funzione di round lineare:** $f$ è moltiplicazione modulare → relazione lineare tra plaintext e ciphertext → vulnerabile alla crittoanalisi lineare.

2. **Chiave piccola:** $K \in \mathbb{Z}_{15}$ ha solo 15 valori. Un attacco a forza bruta richiede al massimo 15 tentativi.

3. **Solo 2 round:** insufficiente per garantire diffusione e confusione adeguate.

4. **Modulo 15 non primo:** causa debolezze strutturali (divisori di zero, non iniettività per certi $K$).

**Chiavi "sicure" vs "insicure":**

La sicurezza dipende fortemente dal valore di $K$:
- $K \in \{1, 2, 4, 7, 8, 11, 13, 14\}$ (coprimi con 15): $f$ è biiezione su $\mathbb{Z}_{15}$ → comportamento migliore.
- $K \in \{3, 5, 6, 9, 10, 12\}$ (non coprimi con 15): $f$ non è biiezione → perdita di informazione.
- $K = 0$: nessuna cifratura.

> ⚠️ Questo cifrario è pedagogicamente interessante ma crittograficamente insicuro. La dipendenza del livello di sicurezza dal valore numerico specifico della chiave è una caratteristica profondamente indesiderabile in un cifrario reale.

---

## Domanda 3 — Funzioni hash: funzione identità e paradosso del compleanno (25 punti)

### Traccia originale

> **(25 punti) Funzioni hash.**
>
> a. (15 punti) Si consideri come funzione hash la funzione identità $I: \{0,1\}^{64} \to \{0,1\}^{64}$ e se ne discutano le proprietà.
>
> b. (10 punti) Si illustri il paradosso del compleanno e le sue conseguenze per le funzioni hash.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà di sicurezza delle funzioni hash
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — paradosso del compleanno

### Soluzione dettagliata

#### Parte a — Funzione identità $I: \{0,1\}^{64} \to \{0,1\}^{64}$ come funzione hash (15 punti)

La funzione identità $I(x) = x$ per $x \in \{0,1\}^{64}$ è una funzione che mappa ogni stringa di 64 bit in se stessa.

**Analisi delle proprietà di sicurezza:**

**1. Resistenza alla preimmagine (One-wayness):**

**MANCA** completamente. Dato un hash $h = I(x) = x$, trovare una preimmagine è banale: la preimmagine è $h$ stesso. Non c'è alcun calcolo da fare.

Formalmente: $\forall h$, $I^{-1}(h) = h$, quindi trovare $x$ tale che $I(x) = h$ richiede 0 operazioni.

**2. Resistenza debole alle collisioni (Second preimage resistance):**

**MANCA** completamente. Dato $x$, trovare $x' \neq x$ tale che $I(x') = I(x)$ equivale a trovare $x' \neq x$ tale che $x' = x$: questa è una contraddizione. Quindi...

> ⚠️ Aspetta: $I$ è una **biiezione** (iniettiva e suriettiva su $\{0,1\}^{64}$). Non esistono collisioni! Quindi $I$ ha banalmente la resistenza alle collisioni (forte e debole) perché non ci sono coppie $(x, x')$ con $x \neq x'$ e $I(x) = I(x')$.

**Analisi corretta:**

- **Preimage resistance:** MANCA (triviale invertire).
- **Second preimage resistance:** SODDISFATTA vacuamente (non esistono second preimages perché $I$ è iniettiva).
- **Collision resistance:** SODDISFATTA vacuamente (non esistono collisioni).

**Ma queste sono le proprietà "giuste"?**

In crittografia pratica, una funzione hash è usata per comprimere dati: tipicamente $H: \{0,1\}^* \to \{0,1\}^n$ con l'input più lungo dell'output. Se l'input è della **stessa lunghezza** dell'output (come nella funzione identità su 64 bit), la funzione è necessariamente iniettiva (almeno sul dominio di dimensione $2^{64}$), e la resistenza alle collisioni è banalmente soddisfatta ma inutile.

**Inutilità pratica della funzione identità:**

1. **Nessuna compressione:** l'hash è lungo quanto il messaggio. Non c'è alcun vantaggio pratico nell'usare $I$ come hash.

2. **Nessuna one-wayness:** il messaggio originale è completamente esposto nell'hash. Qualsiasi sistema che usa un hash per nascondere il messaggio (es. password hashing, commitment schemes) è completamente compromesso.

3. **Il "digest" è il messaggio:** firmare $H(m)$ con $I$ è identico a firmare $m$ direttamente. Non c'è alcuna riduzione della dimensione.

4. **Non è pseudo-random:** $I(x)$ ha distribuzione identica a $x$, quindi non si comporta come una funzione casuale.

**Conclusione:**

La funzione identità soddisfa formalmente le proprietà di collision resistance e second preimage resistance (per l'assenza di collisioni), ma manca completamente di one-wayness e di qualsiasi utilità pratica come funzione hash. Una funzione hash crittografica deve essere **one-way** (non invertibile), **comprimere** l'input, e comportarsi come una funzione pseudo-casuale. $I$ non soddisfa nessuno di questi requisiti.

> 📌 Questo esempio illustra che le tre proprietà di sicurezza delle funzioni hash non sono indipendenti nel senso che la loro soddisfazione "vacua" non è sufficiente. Una funzione hash deve essere progettata per essere crittograficamente utile: one-way, compressiva, e pseudo-random.

#### Parte b — Paradosso del compleanno e conseguenze per le funzioni hash (10 punti)

**Il paradosso del compleanno:**

Il paradosso del compleanno è un risultato probabilistico controintuitivo: in un gruppo di soli 23 persone, la probabilità che almeno due abbiano lo stesso compleanno (tra 365 giorni) supera il 50%. Con 70 persone, la probabilità supera il 99.9%.

**Formalizzazione:**

Sia $H: \{0,1\}^* \to \{0,1\}^n$ una funzione hash ideale (comportamento pseudo-casuale).

**Domanda:** quante valutazioni casuali di $H$ sono necessarie per trovare due input $x_1 \neq x_2$ con $H(x_1) = H(x_2)$ (collisione) con probabilità $\geq 1/2$?

**Risposta:** circa $\sqrt{2^n} = 2^{n/2}$ valutazioni.

**Derivazione (analisi del compleanno):**

Dopo aver valutato $H$ su $k$ input casuali distinti $x_1, x_2, \ldots, x_k$, la probabilità che **non** ci siano collisioni è:

$$P(\text{nessuna collisione}) = 1 \cdot \left(1 - \frac{1}{2^n}\right) \cdot \left(1 - \frac{2}{2^n}\right) \cdots \left(1 - \frac{k-1}{2^n}\right)$$

Usando l'approssimazione $\ln(1-x) \approx -x$ per $x$ piccolo:

$$\ln P \approx -\frac{0 + 1 + 2 + \cdots + (k-1)}{2^n} = -\frac{k(k-1)/2}{2^n} \approx -\frac{k^2}{2^{n+1}}$$

Per $P = 1/2$: $k^2 \approx 2^n \ln 2 \approx 0.693 \cdot 2^n$, quindi:

$$k \approx \sqrt{0.693 \cdot 2^n} \approx 0.83 \cdot 2^{n/2}$$

L'ordine è $O(2^{n/2})$.

**Conseguenze per le funzioni hash:**

1. **Vulnerabilità alla forza bruta per collision resistance:** trovare una collisione per una hash da $n$ bit richiede solo $O(2^{n/2})$ operazioni, non $O(2^n)$. Questo è il **birthday attack** (attacco del compleanno).

2. **Dimensione dell'output:** per avere sicurezza equivalente a $2^{128}$ operazioni contro le collisioni, l'hash deve avere almeno $2n = 256$ bit di output. Questo è il motivo per cui SHA-256 (256 bit) è considerato sicuro a 128 bit contro le collisioni, mentre MD5 (128 bit) offre solo $2^{64}$ sicurezza contro le collisioni (non più sufficiente).

3. **SHA-1 compromessa:** SHA-1 produce output da 160 bit → sicurezza contro collisioni di $2^{80}$, non di $2^{160}$. Il birthday attack riduce la sicurezza effettiva. Nel 2005 sono stati trovati attacchi teorici più efficienti del birthday attack, e nel 2017 Google ha dimostrato una collisione pratica (progetto SHAttered).

4. **Regola pratica:** per $k$ bit di sicurezza contro le collisioni, usare un hash con output di $2k$ bit.

| Funzione hash | Output | Sicurezza vs collisioni | Stato |
|---------------|--------|------------------------|-------|
| MD5 | 128 bit | $2^{64}$ | Compromessa (collisioni trovate) |
| SHA-1 | 160 bit | $2^{80}$ | Deprecata (SHAttered 2017) |
| SHA-256 | 256 bit | $2^{128}$ | Raccomandata |
| SHA-3-256 | 256 bit | $2^{128}$ | Raccomandata |

> 💡 Il paradosso del compleanno illustra perché la sicurezza "dimezzata" contro le collisioni è una proprietà fondamentale delle funzioni hash: non è un difetto evitabile ma una conseguenza matematica inevitabile del birthday bound, indipendentemente dalla costruzione specifica dell'hash.

---

## Domanda 4 — RSA completo: generazione chiavi, cifratura, decifratura, firma (20 punti)

### Traccia originale

> **(20 punti) Cifratura e Firma RSA.** Alice vuole generare una coppia di chiavi RSA. Vengono selezionati i due primi $p = 5$ e $q = 7$.
>
> a. Calcolare gli ulteriori parametri necessari.
>
> b. Nel selezionare la chiave privata, Alice ha dei dubbi sulla scelta di $d = 3$. Discutere la scelta.
>
> c. Alice decide di scegliere $d = 11$. Qual è la chiave pubblica: $e_1 = 13$ o $e_2 = 11$?
>
> d. Se Bob vuole mandare il messaggio $M = 33$ ad Alice, quale chiave deve usare e qual è il messaggio spedito ad Alice?
>
> e. Una volta ricevuto il messaggio cifrato, mostrare le operazioni che Alice compie per la decifratura.
>
> f. Se Alice vuole spedire a Bob un messaggio firmato $M' = 6$, cosa spedisce Alice? Come fa Bob a verificare che il messaggio è stato spedito da Alice?

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA completo
- [`../../M5_Firme_Digitali/UD2/L1 - Firma digitale con RSA.md`](../../M5_Firme_Digitali/UD2/L1%20-%20Firma%20digitale%20con%20RSA.md) — firma RSA

### Soluzione dettagliata

#### Parte a — Calcolo dei parametri RSA (punteggio incluso nel totale)

**Dati:** $p = 5$, $q = 7$.

**Calcolo del modulo:**

$$n = p \cdot q = 5 \cdot 7 = 35$$

**Calcolo della funzione di Eulero:**

$$\varphi(n) = (p-1)(q-1) = (5-1)(7-1) = 4 \cdot 6 = 24$$

**Riepilogo dei parametri RSA per Alice:**
- Modulo: $n = 35$
- $\varphi(35) = 24$
- Chiave pubblica: $(n, e)$ — da determinare
- Chiave privata: $(n, d)$ — da determinare

#### Parte b — Discussione della scelta $d = 3$ (punteggio incluso nel totale)

Per usare $d = 3$ come chiave privata, deve valere $ed \equiv 1 \pmod{24}$.

Con $d = 3$: dobbiamo trovare $e$ tale che $3e \equiv 1 \pmod{24}$, ovvero l'inverso di 3 modulo 24.

$\gcd(3, 24) = 3 \neq 1$: il 3 **non ha inverso modulo 24** (poiché 3 divide 24).

Quindi **$d = 3$ non è una scelta valida** per la chiave privata RSA con $\varphi(n) = 24$. Non esiste una chiave pubblica $e$ corrispondente.

**Motivo formale:** la condizione per un valido esponente privato è $\gcd(d, \varphi(n)) = 1$. Poiché $\gcd(3, 24) = 3 \neq 1$, la scelta è invalida.

> ⚠️ In generale, $d$ deve essere scelto coprimo con $\varphi(n)$. Un errore comune è scegliere $d$ senza verificare questa condizione. Per $\varphi(24) = $ i valori coprimi con 24 sono: 1, 5, 7, 11, 13, 17, 19, 23.

#### Parte c — Chiave pubblica corrispondente a $d = 11$ (punteggio incluso nel totale)

Con $d = 11$, cerchiamo $e$ tale che $11e \equiv 1 \pmod{24}$.

Verifica di $e_1 = 13$: $11 \cdot 13 = 143 = 5 \cdot 24 + 23 \bmod 24 = 23 \neq 1$. **Non valido.**

Verifica di $e_2 = 11$: $11 \cdot 11 = 121 = 5 \cdot 24 + 1 \bmod 24 = 1$ ✓. **Valido.**

**La chiave pubblica è $e = 11$** (e coincide con la chiave privata: $e = d = 11$).

> 📌 Notare che in questo caso la chiave pubblica e quella privata coincidono ($e = d = 11$). Questo è possibile perché $11^2 = 121 \equiv 1 \pmod{24}$: 11 è la propria inversa moltiplicativa modulo 24. Non è un errore, ma è crittograficamente indesiderabile avere $e = d$ (riduce la sicurezza del sistema poiché chiunque conosce $e$ conosce anche $d$). In pratica si sceglie sempre $e \neq d$.

**Chiave pubblica di Alice:** $(n, e) = (35, 11)$.
**Chiave privata di Alice:** $(n, d) = (35, 11)$.

#### Parte d — Cifratura da parte di Bob (punteggio incluso nel totale)

Bob vuole spedire ad Alice il messaggio $M = 33$.

**Bob deve usare la chiave pubblica di Alice** $(n, e) = (35, 11)$ per cifrare.

**Cifratura:** $C = M^e \bmod n = 33^{11} \bmod 35$.

Usiamo l'esponenziazione rapida. $11 = 1011_2$:

$$33^1 = 33$$
$$33^2 = 1089 \bmod 35$$
$$1089 = 31 \cdot 35 + 4 \Rightarrow 1089 \bmod 35 = 4$$
$$33^4 = 4^2 = 16$$
$$33^8 = 16^2 = 256 \bmod 35$$
$$256 = 7 \cdot 35 + 11 \Rightarrow 256 \bmod 35 = 11$$

$11 = 8 + 2 + 1$:
$$33^{11} = 33^8 \cdot 33^2 \cdot 33^1 \bmod 35 = 11 \cdot 4 \cdot 33 \bmod 35$$

$$11 \cdot 4 = 44 \bmod 35 = 9$$
$$9 \cdot 33 = 297 \bmod 35$$
$$297 = 8 \cdot 35 + 17 \Rightarrow 297 \bmod 35 = 17$$

**Bob spedisce ad Alice:** $C = 17$.

#### Parte e — Decifratura da parte di Alice (punteggio incluso nel totale)

Alice riceve $C = 17$ e usa la sua chiave privata $d = 11$.

**Decifratura:** $M = C^d \bmod n = 17^{11} \bmod 35$.

$11 = 8 + 2 + 1 = 1011_2$:

$$17^1 = 17$$
$$17^2 = 289 \bmod 35 = 289 - 8\cdot35 = 289 - 280 = 9$$
$$17^4 = 9^2 = 81 \bmod 35 = 81 - 2\cdot35 = 11$$
$$17^8 = 11^2 = 121 \bmod 35 = 121 - 3\cdot35 = 16$$

$$17^{11} = 17^8 \cdot 17^2 \cdot 17^1 \bmod 35 = 16 \cdot 9 \cdot 17 \bmod 35$$

$$16 \cdot 9 = 144 \bmod 35 = 144 - 4\cdot35 = 4$$
$$4 \cdot 17 = 68 \bmod 35 = 68 - 35 = 33$$

**Messaggio decifrato:** $M = 33$ ✓

#### Parte f — Firma RSA di Alice su $M' = 6$ (punteggio incluso nel totale)

**Firma:** Alice vuole firmare $M' = 6$ e spedirlo a Bob.

Per firmare, Alice usa la propria **chiave privata** $(n, d) = (35, 11)$:

$$S = (M')^d \bmod n = 6^{11} \bmod 35$$

Calcolo di $6^{11} \bmod 35$:

$$6^1 = 6$$
$$6^2 = 36 \bmod 35 = 1$$
$$6^4 = 1^2 = 1$$
$$6^8 = 1^2 = 1$$

$$6^{11} = 6^8 \cdot 6^2 \cdot 6^1 = 1 \cdot 1 \cdot 6 = 6$$

Quindi la firma è $S = 6$.

> 💡 In questo caso la firma coincide con il messaggio ($S = M' = 6$) perché $6^2 \equiv 1 \pmod{35}$, quindi $6^{11} = 6^{10} \cdot 6 = (6^2)^5 \cdot 6 = 1^5 \cdot 6 = 6$. Questo non è un errore ma una coincidenza numerica dei parametri scelti.

**Alice spedisce a Bob la coppia** $(M', S) = (6, 6)$ (messaggio + firma), oppure solo la firma $S = 6$ (con il messaggio implicito o trasmesso separatamente).

**Verifica da parte di Bob:**

Bob riceve $(M', S) = (6, 6)$ e vuole verificare che la firma sia di Alice.

Bob usa la **chiave pubblica di Alice** $(n, e) = (35, 11)$:

$$V = S^e \bmod n = 6^{11} \bmod 35 = 6$$

Bob confronta $V$ con $M'$: $V = 6 = M'$ ✓

La firma è valida: il messaggio proviene da Alice (solo Alice conosce $d = 11$ che le ha permesso di calcolare $S = (M')^d$).

**Schema generale della firma RSA:**

- **Firma:** il firmatario usa la chiave **privata** per firmare: $S = (M')^d \bmod n$.
- **Verifica:** il verificatore usa la chiave **pubblica** del firmatario per verificare: $V = S^e \bmod n$; la firma è valida se $V = M'$.
- **Correttezza:** $S^e = ((M')^d)^e = (M')^{de} \equiv M' \pmod{n}$ per la correttezza di RSA.

> ⚠️ Nella firma digitale RSA, i ruoli delle chiavi sono **invertiti** rispetto alla cifratura: la chiave privata viene usata per firmare (tutti possono verificare con la chiave pubblica, solo il firmatario può generare la firma).

---

## Pattern e osservazioni per l'esame

Questo è il secondo appello autunnale (settembre 2024). È uno degli appelli più completi e vari dell'anno.

- **D1 (20 punti):** Hill con matrice $2\times2$. Calcoli: determinante, matrice aggiunta, inversione modulo 26, cifratura blocco per blocco. Da memorizzare: la formula dell'inversa $2\times2$ con la matrice aggiunta, e il fatto che $\det(K)$ deve essere coprimo con 26.

- **D2 (35 punti):** Feistel con funzione aritmetica mod 15 (diversa da tutte le versioni precedenti). La parte iii sulla sicurezza in funzione del valore numerico di $K$ è originale: analizzare $\gcd(2K, 15)$ per diversi valori di $K$ e discutere quando la funzione di round è biiettiva.

- **D3 (25 punti):** la funzione identità come hash è una domanda trabocchetto. La risposta corretta richiede di distinguere tra: proprietà formalmente soddisfatte (collision resistance vacua per biiezione) e proprietà necessarie per l'utilità pratica (one-wayness, compressione). La parte b sul paradosso del compleanno è standard.

- **D4 (20 punti):** RSA completo con firma. È l'appello più dettagliato su RSA dell'anno: generazione chiavi, verifica di validità di $d$, scelta di $e$, cifratura, decifratura, firma e verifica. Tutte le operazioni vanno mostrate con tutti i calcoli di esponenziazione modulare.

**Consigli per Hill:**

1. Verificare sempre $\gcd(\det(K), 26) = 1$ prima di procedere.
2. Per l'inversione: calcolare la matrice aggiunta (scambiare diagonale principale, negare anti-diagonale), poi moltiplicare per $(\det K)^{-1} \bmod 26$.
3. Per la cifratura: convertire ogni coppia di lettere in un vettore colonna, moltiplicare per $K$ mod 26, convertire il risultato in lettere.
4. Ogni calcolo va fatto esplicitamente (prodotti scalari, riduzioni modulo 26).
