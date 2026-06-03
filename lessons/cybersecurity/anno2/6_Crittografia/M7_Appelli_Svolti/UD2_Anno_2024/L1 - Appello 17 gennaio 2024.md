# Appello del 17 gennaio 2024 — Soluzione completa

> 📌 **Informazioni appello:** 17/01/2024, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrari a sostituzione su blocchi binari, dimensione chiave
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD3/L1 - AES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD3/L1%20-%20AES.md) — struttura e operazioni del cifrario AES
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà di sicurezza delle funzioni hash
> - [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — cifratura e decifratura El-Gamal, versione ellittica

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

---

## Domanda 2 — Crittosistema AES (20 punti)

### Traccia originale

> **(20 punti) Crittosistema AES.**
>
> a. (20) Descrivere le principali caratteristiche del cifrario AES e le operazioni di cifratura e di decifratura.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD3/L1 - AES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD3/L1%20-%20AES.md) — struttura completa di AES
- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — confronto con DES e cifrari a blocchi

### Soluzione dettagliata

#### Caratteristiche generali di AES

**AES (Advanced Encryption Standard)** è il cifrario a blocchi simmetrico standardizzato dal NIST nel 2001, scelto tramite competizione pubblica (algoritmo Rijndael di Daemen e Rijmen).

**Parametri principali:**
- **Dimensione del blocco:** 128 bit (16 byte), fissa.
- **Lunghezza della chiave:** 128, 192 o 256 bit (AES-128, AES-192, AES-256).
- **Numero di round:** 10 (AES-128), 12 (AES-192), 14 (AES-256).

**Struttura:** a differenza del DES (struttura di Feistel), AES utilizza una struttura a **rete di sostituzione-permutazione (SPN)**. L'intero blocco di 128 bit è trasformato ad ogni round.

> 📌 AES non è un cifrario di Feistel: l'intero blocco viene trasformato ad ogni round, non solo metà. Questo lo rende più efficiente in termini di diffusione.

#### Rappresentazione dello stato

Lo stato interno di AES è una matrice $4 \times 4$ di byte (128 bit totali). I byte del plaintext vengono disposti per colonne nella matrice.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### Quattro operazioni di round

Ogni round (tranne l'ultimo) applica in sequenza quattro trasformazioni:

**1. SubBytes (sostituzione):**

Ogni byte della matrice di stato viene sostituito tramite una S-box fissa a 256 entry. La S-box di AES è costruita come:
1. Inversione moltiplicativa in $GF(2^8)$: ogni byte $b$ è sostituito con $b^{-1}$ (tranne 0 che rimane 0).
2. Trasformazione affine in $GF(2)$: moltiplicazione per una matrice binaria fissa + addizione di un vettore costante.

Questa operazione fornisce la **confusione** (non-linearità).

**2. ShiftRows (permutazione per righe):**

Le righe della matrice di stato vengono scalate ciclicamente verso sinistra:
- Riga 0: nessuno shift
- Riga 1: shift di 1 posizione a sinistra
- Riga 2: shift di 2 posizioni a sinistra
- Riga 3: shift di 3 posizioni a sinistra

Questa operazione garantisce che i byte di una stessa colonna originale vengano distribuiti in colonne diverse dopo la trasformazione.

**3. MixColumns (diffusione per colonne):**

Ogni colonna di 4 byte è trattata come un polinomio a coefficienti in $GF(2^8)$ e moltiplicata per un polinomio fisso $c(x) = 3x^3 + x^2 + x + 2$ (mod $x^4 + 1$). In forma matriciale:

$$\begin{pmatrix} s'_0 \\ s'_1 \\ s'_2 \\ s'_3 \end{pmatrix} = \begin{pmatrix} 02 & 03 & 01 & 01 \\ 01 & 02 & 03 & 01 \\ 01 & 01 & 02 & 03 \\ 03 & 01 & 01 & 02 \end{pmatrix} \begin{pmatrix} s_0 \\ s_1 \\ s_2 \\ s_3 \end{pmatrix}$$

dove le moltiplicazioni sono in $GF(2^8)$. Questa è la principale operazione di **diffusione**.

> ⚠️ MixColumns **non viene applicata nell'ultimo round**. Questo semplifica l'implementazione inversa senza influire sulla sicurezza.

**4. AddRoundKey (aggiunta della sottochiave):**

Ogni byte dello stato viene combinato con il corrispondente byte della sottochiave del round tramite XOR. Le sottochiavi sono derivate dalla chiave principale tramite il **Key Schedule** (espansione della chiave).

#### Key Schedule (espansione della chiave)

A partire dalla chiave principale (128/192/256 bit), il Key Schedule genera $N_r + 1$ sottochiavi da 128 bit ciascuna (una per ogni round + una iniziale). L'espansione usa operazioni di SubWord (SubBytes su una word), RotWord (rotazione di byte), e XOR con costanti di round (Rcon).

#### Struttura completa della cifratura

```
Input: Plaintext P, Chiave K
1. Espansione della chiave (Key Schedule)
2. AddRoundKey con la sottochiave iniziale K_0
3. Per i = 1 to N_r - 1:
   a. SubBytes
   b. ShiftRows
   c. MixColumns
   d. AddRoundKey con K_i
4. Ultimo round (senza MixColumns):
   a. SubBytes
   b. ShiftRows
   c. AddRoundKey con K_{N_r}
Output: Ciphertext C
```

#### Decifratura di AES

La decifratura applica le operazioni inverse in ordine inverso:

- **InvShiftRows:** shift ciclico delle righe verso destra.
- **InvSubBytes:** S-box inversa (l'inversione è ben definita perché SubBytes è biiezione).
- **InvMixColumns:** moltiplicazione per la matrice inversa in $GF(2^8)$.
- **AddRoundKey:** identica alla cifratura (XOR è la propria inversa).

La struttura è:

```
1. AddRoundKey con K_{N_r}
2. Per i = N_r - 1 downto 1:
   a. InvShiftRows
   b. InvSubBytes
   c. AddRoundKey con K_i
   d. InvMixColumns
3. Ultimo round inverso:
   a. InvShiftRows
   b. InvSubBytes
   c. AddRoundKey con K_0
Output: Plaintext P
```

> 💡 Esiste anche una formulazione equivalente della decifratura (decifratura equivalente) che permette di usare la stessa struttura della cifratura, cambiando solo l'ordine delle operazioni. Questo è utile per implementazioni ottimizzate in hardware.

#### Sicurezza di AES

- **Resistenza alla crittoanalisi differenziale e lineare:** la struttura di AES è stata progettata esplicitamente per resistere a questi attacchi. Il numero di round è scelto per garantire sufficiente diffusione (criterio dell'avalanche effect).
- **Chiavi di 128 bit:** la forza bruta richiederebbe $2^{128}$ operazioni, computazionalmente impraticabile.
- **Nessuna backdoor nota:** dopo oltre 20 anni di analisi pubblica, non sono stati trovati attacchi pratici ad AES con chiave completa (gli unici attacchi noti sono teorici e richiedono chiavi/round ridotti).

---

## Domanda 3 — Funzioni hash (20 punti)

### Traccia originale

> **(20 punti) Funzioni hash.**
>
> a. (10 punti) Illustrare le proprietà di sicurezza delle funzioni hash.
>
> b. (10 punti) Dimostrare che la funzione hash $H(x) = 2x + 15 \bmod 20$ non ha la proprietà di resistenza debole alle collisioni, mostrando come sia facile trovare una collisione per $H(6)$.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — le tre proprietà di sicurezza
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno e second preimage

### Soluzione dettagliata

#### Parte a — Proprietà di sicurezza delle funzioni hash (10 punti)

Una funzione hash crittografica $H: \{0,1\}^* \to \{0,1\}^n$ deve soddisfare tre proprietà di sicurezza fondamentali:

**1. Resistenza alla preimmagine (One-wayness / Preimage resistance):**

Data un'immagine $h$, deve essere computazionalmente difficile trovare un $x$ tale che $H(x) = h$.

$$\forall h,\quad \text{difficile trovare } x \text{ t.c. } H(x) = h$$

Questo garantisce la non invertibilità: dall'hash non si può risalire al messaggio originale.

**2. Resistenza debole alle collisioni (Second preimage resistance):**

Dato un messaggio $x$, deve essere computazionalmente difficile trovare un messaggio diverso $x' \neq x$ tale che $H(x') = H(x)$.

$$\forall x,\quad \text{difficile trovare } x' \neq x \text{ t.c. } H(x') = H(x)$$

Questa proprietà protegge l'integrità: un avversario non può sostituire un messaggio autenticato con uno diverso mantenendo lo stesso hash.

**3. Resistenza forte alle collisioni (Strong collision resistance):**

Deve essere computazionalmente difficile trovare **qualsiasi** coppia $(x, x')$ con $x \neq x'$ tale che $H(x) = H(x')$.

$$\text{difficile trovare } (x, x') \text{ con } x \neq x' \text{ t.c. } H(x) = H(x')$$

> 📌 **Gerarchia delle proprietà:** la resistenza forte alle collisioni implica la resistenza debole (second preimage), che a sua volta implica la resistenza alla preimmagine. Rompere la preimage non implica rompere le collisioni, ma rompere le collisioni non implica rompere la second preimage. Formalmente: strong collision resistance $\Rightarrow$ second preimage resistance $\Rightarrow$ preimage resistance.

> ⚠️ Il **paradosso del compleanno** mostra che trovare una collisione forte richiede solo $O(2^{n/2})$ tentativi (non $O(2^n)$). Per questo motivo gli hash moderni usano almeno 256 bit di output (SHA-256, SHA-3).

#### Parte b — $H(x) = 2x + 15 \bmod 20$ manca di second preimage resistance (10 punti)

**Calcolo di $H(6)$:**

$$H(6) = 2 \cdot 6 + 15 \bmod 20 = 12 + 15 \bmod 20 = 27 \bmod 20 = 7$$

**Trovare una second preimage per $H(6) = 7$:**

Dobbiamo trovare $x' \neq 6$ tale che $H(x') = 7$, ovvero:

$$2x' + 15 \equiv 7 \pmod{20}$$

$$2x' \equiv 7 - 15 \equiv -8 \equiv 12 \pmod{20}$$

$$2x' \equiv 12 \pmod{20}$$

Dividiamo entrambi i membri per 2. Poiché $\gcd(2, 20) = 2$ e $2 \mid 12$, l'equazione ha esattamente $\gcd(2, 20) = 2$ soluzioni in $\mathbb{Z}_{20}$.

Dividiamo tutto per 2: $x' \equiv 6 \pmod{10}$.

Le soluzioni in $\{0, 1, \ldots, 19\}$ sono: $x' = 6$ e $x' = 16$.

**Second preimage trovata:** $x' = 16$.

**Verifica:**
$$H(16) = 2 \cdot 16 + 15 \bmod 20 = 32 + 15 \bmod 20 = 47 \bmod 20 = 7 = H(6) \checkmark$$

**Collisione:** $H(6) = H(16) = 7$, con $6 \neq 16$.

**Perché la proprietà manca strutturalmente:**

La funzione $H(x) = 2x + 15 \bmod 20$ è **affine** (lineare con traslazione). Poiché $\gcd(2, 20) = 2 \neq 1$, la funzione lineare $x \mapsto 2x \bmod 20$ non è iniettiva: il suo kernel ha dimensione 2, quindi ogni elemento dell'immagine ha esattamente 2 preimmagini. Trovare una second preimage si riduce a risolvere una semplice equazione lineare congruenziale, operazione computazionalmente banale.

> ⚠️ Una funzione hash deve essere non lineare, non invertibile, e con output ben distribuito. Una funzione affine modulo un intero composto viola tutte queste caratteristiche e non può essere usata in crittografia.

---

## Domanda 4 — El-Gamal (30 punti)

### Traccia originale

> **(30 punti) El Gamal.** Si consideri un cifrario di El-Gamal con i seguenti parametri: primo $p = 11$, generatore $g = 2$ e numero scelto per la chiave privata di Alice $\alpha = 6$.
>
> a. (5 punti) Esplicitare la chiave pubblica di Alice.
>
> b. (10 punti) Mostrare cosa Bob spedisce ad Alice volendo cifrare il messaggio $M = 4$ e avendo scelto come numero casuale per la cifratura $k = 3$.
>
> c. (10 punti) Mostrare come Alice riesce a decifrare il messaggio.
>
> d. (5 punti) Fare cenni all'utilizzo di El-Gamal per cifratura ellittica.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — schema completo El-Gamal
- [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — fondamento matematico
- [`../../M3_Cifrature_Asimmetriche/UD4/L2 - Crittosistemi su curve ellittiche.md`](../../M3_Cifrature_Asimmetriche/UD4/L2%20-%20Crittosistemi%20su%20curve%20ellittiche.md) — versione ellittica

### Soluzione dettagliata

#### Setup del sistema El-Gamal

**Parametri pubblici:**
- Primo: $p = 11$
- Generatore di $\mathbb{Z}_{11}^*$: $g = 2$
- Chiave privata di Alice: $\alpha = 6$

> 📌 Verifichiamo che $g = 2$ generi tutto $\mathbb{Z}_{11}^*$. L'ordine del gruppo è $p - 1 = 10$. Potenze di 2 mod 11:
> $2^1=2,\ 2^2=4,\ 2^3=8,\ 2^4=5,\ 2^5=10,\ 2^6=9,\ 2^7=7,\ 2^8=3,\ 2^9=6,\ 2^{10}=1$.
> Tutti e 10 gli elementi di $\mathbb{Z}_{11}^*$ compaiono: $g=2$ è un generatore (radice primitiva).

#### Parte a — Chiave pubblica di Alice (5 punti)

La chiave pubblica di Alice è $\beta = g^\alpha \bmod p$:

$$\beta = 2^6 \bmod 11$$

Calcolo passo-passo usando le potenze già calcolate:

$$2^1 = 2$$
$$2^2 = 4$$
$$2^4 = 4^2 = 16 \bmod 11 = 5$$
$$2^6 = 2^4 \cdot 2^2 = 5 \cdot 4 = 20 \bmod 11 = 9$$

**Chiave pubblica di Alice:** $\beta = 9$.

La chiave pubblica completa pubblicata da Alice è la tripla $(p, g, \beta) = (11, 2, 9)$.

#### Parte b — Cifratura da parte di Bob (10 punti)

Bob vuole cifrare $M = 4$ con la chiave pubblica di Alice $(p=11, g=2, \beta=9)$ e il numero casuale $k = 3$.

**Schema El-Gamal:** il ciphertext è la coppia $(c_1, c_2)$ dove:

$$c_1 = g^k \bmod p = 2^3 \bmod 11$$

Calcolo di $2^3 \bmod 11$:

$$2^3 = 8$$

Quindi $c_1 = 8$.

$$c_2 = M \cdot \beta^k \bmod p = 4 \cdot 9^3 \bmod 11$$

Calcolo di $9^3 \bmod 11$:

$$9^1 = 9$$
$$9^2 = 81 \bmod 11 = 81 - 7 \cdot 11 = 81 - 77 = 4$$
$$9^3 = 9^2 \cdot 9 = 4 \cdot 9 = 36 \bmod 11 = 36 - 3 \cdot 11 = 36 - 33 = 3$$

Quindi:

$$c_2 = 4 \cdot 3 \bmod 11 = 12 \bmod 11 = 1$$

**Bob spedisce ad Alice il ciphertext:** $(c_1, c_2) = (8, 1)$.

> 📌 Il numero casuale $k=3$ è scelto da Bob ed è tenuto segreto. La coppia $(8,1)$ non rivela nulla su $M=4$ a chi non conosce la chiave privata $\alpha=6$ di Alice (il problema è riconducibile al logaritmo discreto).

#### Parte c — Decifratura da parte di Alice (10 punti)

Alice riceve $(c_1, c_2) = (8, 1)$ e conosce la chiave privata $\alpha = 6$.

**Formula di decifratura El-Gamal:**

$$M = c_2 \cdot (c_1^\alpha)^{-1} \bmod p$$

**Passo 1:** Calcolo di $c_1^\alpha \bmod p = 8^6 \bmod 11$.

Usando l'esponenziazione rapida (square-and-multiply):

$$8^1 = 8$$
$$8^2 = 64 \bmod 11 = 64 - 5 \cdot 11 = 64 - 55 = 9$$
$$8^4 = 9^2 = 81 \bmod 11 = 4$$
$$8^6 = 8^4 \cdot 8^2 = 4 \cdot 9 = 36 \bmod 11 = 3$$

Quindi $c_1^\alpha = 8^6 \bmod 11 = 3$.

**Passo 2:** Calcolo di $(c_1^\alpha)^{-1} = 3^{-1} \bmod 11$.

Usando il piccolo teorema di Fermat: $3^{-1} \equiv 3^{p-2} = 3^9 \bmod 11$.

$$3^1 = 3$$
$$3^2 = 9$$
$$3^4 = 9^2 = 81 \bmod 11 = 4$$
$$3^8 = 4^2 = 16 \bmod 11 = 5$$
$$3^9 = 3^8 \cdot 3 = 5 \cdot 3 = 15 \bmod 11 = 4$$

Verifica: $3 \cdot 4 = 12 \bmod 11 = 1$ ✓ Quindi $3^{-1} \bmod 11 = 4$.

**Passo 3:** Calcolo di $M$:

$$M = c_2 \cdot (c_1^\alpha)^{-1} \bmod 11 = 1 \cdot 4 \bmod 11 = 4$$

**Messaggio recuperato:** $M = 4$ ✓

**Correttezza algebrica:**

$$c_2 \cdot (c_1^\alpha)^{-1} = M \cdot \beta^k \cdot (g^{k\alpha})^{-1} = M \cdot g^{k\alpha} \cdot g^{-k\alpha} = M$$

poiché $\beta = g^\alpha \Rightarrow \beta^k = g^{k\alpha} = c_1^\alpha$.

#### Parte d — El-Gamal su curve ellittiche (5 punti)

Il crittosistema El-Gamal si adatta alle curve ellittiche sostituendo il gruppo moltiplicativo $\mathbb{Z}_p^*$ con il gruppo additivo dei punti di una curva ellittica $E(\mathbb{F}_p)$.

**Corrispondenza tra le due versioni:**

| El-Gamal classico | El-Gamal su curve ellittiche (ECEG) |
|-------------------|--------------------------------------|
| Gruppo $\mathbb{Z}_p^*$ (moltiplicativo) | Gruppo $E(\mathbb{F}_p)$ (additivo) |
| Generatore $g \in \mathbb{Z}_p^*$ | Punto base $G \in E(\mathbb{F}_p)$ |
| Chiave privata $\alpha \in \mathbb{Z}$ | Chiave privata $\alpha \in \mathbb{Z}$ |
| Chiave pubblica $\beta = g^\alpha$ | Chiave pubblica $Q = \alpha G$ (moltiplicazione scalare) |
| Cifratura: $(g^k,\; M \cdot \beta^k)$ | Cifratura: $(kG,\; M + kQ)$ dove $M$ è un punto |
| Decifratura: $c_2 \cdot (c_1^\alpha)^{-1}$ | Decifratura: $c_2 - \alpha c_1$ (sottrazione di punti) |

**Problema del logaritmo discreto ellittico (ECDLP):**

La sicurezza si basa sull'ECDLP: dato $G$ e $Q = \alpha G$, trovare $\alpha$. Il problema ECDLP è ritenuto più difficile del DLP classico per la stessa dimensione del gruppo, permettendo chiavi più corte a parità di sicurezza (256 bit EC $\approx$ 3072 bit RSA).

> 💡 In pratica, il messaggio $M$ deve essere codificato come punto sulla curva, operazione non banale. Per questo si usano schemi ibridi (ECIES): El-Gamal ellittico per lo scambio di chiave + AES per la cifratura del messaggio effettivo.

---

## Pattern e osservazioni per l'esame

Questo è l'appello della sessione invernale (gennaio). Caratteristiche da notare:

- **D1 (30 punti):** cifratura classica con componente sia teorica (conteggio permutazioni, tabella) sia pratica (cifratura e decifratura passo-passo). Il punto c richiede di ragionare su quante sostituzioni realizza una struttura parametrica: la risposta non è 24 ma solo 4, perché lo spazio delle chiavi è limitato.

- **D2 (20 punti):** AES è presente. È una domanda descrittiva: enumerare le 4 operazioni per round con precisione matematica (SubBytes in $GF(2^8)$, MixColumns come moltiplicazione polinomiale). Non dimenticare che MixColumns è assente nell'ultimo round.

- **D3 (20 punti):** identica struttura alla D3 dell'appello 20/01/2025 (stessa domanda b con funzione diversa). Padroneggiare la tecnica: calcolare il valore, risolvere la congruenza lineare con $\gcd$, trovare le preimmagini multiple.

- **D4 (30 punti):** El-Gamal numerico classico. Verificare sempre prima che $g$ sia un generatore. La parte d (5 punti) su ECC è uguale in quasi tutti gli appelli: usare la tabella di corrispondenza, menzionare ECDLP e vantaggi in termini di dimensione chiave.

**Consiglio gestione tempo:** D1 e D4 valgono 30 punti ciascuna; investire proporzionalmente più tempo. Per D2 (AES), una descrizione strutturata delle 4 operazioni con formule vale i 20 punti anche senza calcoli numerici.
