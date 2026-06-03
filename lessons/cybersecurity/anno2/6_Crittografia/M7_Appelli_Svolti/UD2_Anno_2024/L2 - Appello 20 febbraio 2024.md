# Appello del 20 febbraio 2024 — Soluzione completa

> 📌 **Informazioni appello:** 20/02/2024, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrario Playfair: costruzione griglia, cifratura, decifratura
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — struttura Feistel del DES e proprietà di simmetria
> - [`../../M4_Funzioni_Hash_e_Mac/UD3/L3 - HMAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L3%20-%20HMAC.md) — HMAC: costruzione con SHA-1
> - [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md) — parametri DSS, firma, verifica, caso s=0

---

## Domanda 1 — Cifratura Playfair (25 punti)

### Traccia originale

> **(25 punti) Cifratura Playfair.**
>
> a. (5) Descrivere brevemente la cifratura Playfair.
>
> b. (10) Discutere i vantaggi rispetto alla cifratura monoalfabetica e le debolezze del sistema Playfair.
>
> c. (10) Utilizzare la parola chiave INVERNO per cifrare il testo in chiaro "parole banali" e decifrare il testo cifrato "dbrpgasr".

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — regole del cifrario Playfair
- [`../../M1_Crittografia_Classica/UD2/L1 - Cifrari monoalfabetici.md`](../../M1_Crittografia_Classica/UD2/L1%20-%20Cifrari%20monoalfabetici.md) — analisi delle frequenze e debolezze

### Soluzione dettagliata

#### Parte a — Descrizione della cifratura Playfair (5 punti)

Il **cifrario Playfair** (inventato da Charles Wheatstone nel 1854, promosso da Lord Playfair) è un cifrario a sostituzione **digramma**: cifra coppie di lettere (digrammi) invece di singole lettere.

**Struttura:**
- Si costruisce una griglia $5 \times 5$ di lettere dell'alfabeto (con I e J fusi in una sola cella, per avere 25 lettere) a partire da una parola chiave.
- Per costruire la griglia: si inserisce la parola chiave (senza ripetizioni) nella griglia riga per riga, poi si completano le celle rimanenti con le lettere dell'alfabeto non ancora presenti, in ordine.

**Regole di cifratura dei digrammi:**

Il testo in chiaro viene diviso in coppie di lettere. Se due lettere della coppia sono uguali, si inserisce una lettera nulla (tipicamente 'X') tra di esse. Se il messaggio ha lunghezza dispari, si aggiunge una lettera nulla in fondo.

1. **Stessa riga:** le due lettere sono sostituite dalle lettere immediatamente a destra (con avvolgimento circolare).
2. **Stessa colonna:** le due lettere sono sostituite dalle lettere immediatamente in basso (con avvolgimento circolare).
3. **Rettangolo:** le due lettere formano gli angoli di un rettangolo. Ciascuna è sostituita dall'altra lettera agli angoli dello stesso rettangolo (sulla stessa riga ma nella colonna dell'altra lettera).

**Decifratura:** applica le stesse regole al contrario (sinistra per stessa riga, su per stessa colonna, rettangolo uguale).

#### Parte b — Vantaggi e debolezze rispetto alla cifratura monoalfabetica (10 punti)

**Vantaggi rispetto alla cifratura monoalfabetica:**

1. **Resistenza all'analisi delle frequenze delle singole lettere:** poiché cifra digrammi, le frequenze delle singole lettere nel ciphertext non corrispondono direttamente alle frequenze dell'alfabeto in chiaro. L'analisi delle frequenze semplice (lettera per lettera) non funziona.

2. **Spazio delle possibili sostituzioni più ampio:** ci sono $26^2 = 676$ possibili digrammi (vs 26 lettere singole), rendendo l'analisi più complessa.

3. **Dipendenza dal contesto:** la sostituzione di una lettera dipende da quale lettera la accompagna nel digramma, introducendo un minimo di contesto.

4. **Sicurezza pratica storica:** fu usato dai britannici nella Prima e Seconda Guerra Mondiale ed è risultato abbastanza sicuro per la comunicazione tattica dell'epoca.

**Debolezze del cifrario Playfair:**

1. **Analisi delle frequenze dei digrammi:** anche se le frequenze delle singole lettere sono oscurate, le frequenze dei digrammi nel ciphertext riflettono le frequenze dei digrammi del plaintext nella stessa lingua. Un testo sufficientemente lungo può essere attaccato tramite analisi statistica dei bigrammi.

2. **Simmetria del rettangolo:** nella regola del rettangolo, se AB cifra in CD, allora CD cifra in AB. Questa proprietà (auto-reciprocità parziale) può essere sfruttata in attacchi known-plaintext.

3. **Dimensione della chiave effettiva ridotta:** la chiave è una permutazione di 25 lettere, ma non tutte le $25!$ permutazioni sono raggiungibili dalla struttura della griglia. Lo spazio delle chiavi effettivo è limitato.

4. **Lettera nulla riconoscibile:** la presenza di 'X' (o 'Q') come lettera nulla inserita per separare doppie lettere può fornire indizi sulla struttura del plaintext.

5. **I e J indistinguibili:** la fusione di I e J introduce ambiguità nella decifratura.

6. **Testo in chiaro strutturato:** la regola dello stesso rettangolo garantisce che ogni digramma produca lo stesso ciphertext con la stessa chiave, rendendo vulnerabile il cifrario ad attacchi con testo in chiaro noto.

> 💡 Playfair è un cifrario a sostituzione poligramma (digramma). È significativamente più robusto della sostituzione monoalfabetica, ma ancora lontano dalla sicurezza moderna. La sua principale debolezza è che cifra digrammi in modo deterministico: lo stesso digramma in chiaro produce sempre lo stesso digramma cifrato (con la stessa chiave).

#### Parte c — Costruzione griglia e cifratura/decifratura (10 punti)

**Costruzione della griglia con chiave INVERNO:**

La parola chiave è INVERNO. Lettere uniche nell'ordine: I, N, V, E, R, O (N compare una sola volta; ricordiamo che I=J).

Si inserisce prima la chiave (senza ripetizioni), poi le lettere rimanenti dell'alfabeto (esclusa J, fusa con I) in ordine:

| | 1 | 2 | 3 | 4 | 5 |
|---|---|---|---|---|---|
| **1** | I/J | N | V | E | R |
| **2** | O | A | B | C | D |
| **3** | F | G | H | K | L |
| **4** | M | P | Q | S | T |
| **5** | U | W | X | Y | Z |

Lettere utilizzate dalla chiave: I, N, V, E, R, O.
Lettere rimanenti in ordine alfabetico (saltando I/J e quelle già presenti): A, B, C, D, F, G, H, K, L, M, P, Q, S, T, U, W, X, Y, Z.

**Cifratura di "parole banali":**

Prepariamo il testo: rimuoviamo spazi → "parolebanali"

Dividiamo in digrammi: PA | RO | LE | BA | NA | LI

Verifichiamo che non ci siano doppie lettere consecutive (nessuna) e che la lunghezza sia pari (12 lettere = 6 coppie, ok).

Cifriamo coppia per coppia:

**Digramma PA:**
- P è in riga 4, colonna 2
- A è in riga 2, colonna 2
- Stessa colonna (colonna 2): si prende la lettera sotto ciascuna.
  - Sotto P (riga 4, col 2) → riga 5, col 2 = **W**
  - Sotto A (riga 2, col 2) → riga 3, col 2 = **G**
- Cifrato: **WG**

**Digramma RO:**
- R è in riga 1, colonna 5
- O è in riga 2, colonna 1
- Rettangolo: R(riga1,col5) e O(riga2,col1) → prendono gli altri angoli del rettangolo (stessa riga, colonna dell'altra):
  - R al rettangolo → riga 1, col 1 = **I**
  - O al rettangolo → riga 2, col 5 = **D**
- Cifrato: **ID**

**Digramma LE:**
- L è in riga 3, colonna 5
- E è in riga 1, colonna 4
- Rettangolo: L(riga3,col5) e E(riga1,col4) → altri angoli:
  - L → riga 3, col 4 = **K**
  - E → riga 1, col 5 = **R**
- Cifrato: **KR**

**Digramma BA:**
- B è in riga 2, colonna 3
- A è in riga 2, colonna 2
- Stessa riga (riga 2): si prende la lettera a destra di ciascuna (con avvolgimento).
  - Destra di B (riga 2, col 3) → col 4 = **C**
  - Destra di A (riga 2, col 2) → col 3 = **B**
- Cifrato: **CB**

**Digramma NA:**
- N è in riga 1, colonna 2
- A è in riga 2, colonna 2
- Stessa colonna (colonna 2): si prende la lettera sotto ciascuna.
  - Sotto N (riga 1, col 2) → riga 2, col 2 = **A**
  - Sotto A (riga 2, col 2) → riga 3, col 2 = **G**
- Cifrato: **AG**

**Digramma LI:**
- L è in riga 3, colonna 5
- I è in riga 1, colonna 1
- Rettangolo: L(riga3,col5) e I(riga1,col1) → altri angoli:
  - L → riga 3, col 1 = **F**
  - I → riga 1, col 5 = **R**
- Cifrato: **FR**

**Testo cifrato di "parole banali":** WGIDKRCBAGFR

---

**Decifratura di "dbrpgasr":**

Dividiamo in digrammi: DB | RP | GA | SR

Decifriamo applicando le regole inverse (stessa riga: lettera a sinistra; stessa colonna: lettera sopra; rettangolo: identico alla cifratura).

**Digramma DB:**
- D è in riga 2, colonna 5
- B è in riga 2, colonna 3
- Stessa riga (riga 2): si prende la lettera a sinistra di ciascuna.
  - Sinistra di D (riga 2, col 5) → col 4 = **C**
  - Sinistra di B (riga 2, col 3) → col 2 = **A**
- Decifrato: **CA**

**Digramma RP:**
- R è in riga 1, colonna 5
- P è in riga 4, colonna 2
- Rettangolo: R(riga1,col5) e P(riga4,col2) → altri angoli:
  - R → riga 1, col 2 = **N**
  - P → riga 4, col 5 = **T**
- Decifrato: **NT**

**Digramma GA:**
- G è in riga 3, colonna 2
- A è in riga 2, colonna 2
- Stessa colonna (colonna 2): si prende la lettera sopra ciascuna.
  - Sopra G (riga 3, col 2) → riga 2, col 2 = **A**
  - Sopra A (riga 2, col 2) → riga 1, col 2 = **N**
- Decifrato: **AN**

**Digramma SR:**
- S è in riga 4, colonna 4
- R è in riga 1, colonna 5
- Rettangolo: S(riga4,col4) e R(riga1,col5) → altri angoli:
  - S → riga 4, col 5 = **T**
  - R → riga 1, col 4 = **E**
- Decifrato: **TE**

**Testo decifrato di "dbrpgasr":** CANTANTE

> ✅ La decifratura di "dbrpgasr" produce "CANTANTE".

---

## Domanda 2 — DES: simmetria cifratura/decifratura (20 punti)

### Traccia originale

> **(20 punti) DES.**
>
> a. (20) Nell'ambito del cifrario DES, provare che la decifratura può essere effettuata applicando l'algoritmo di cifratura al testo cifrato con le chiavi schedulate in ordine inverso.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — struttura Feistel del DES
- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — proprietà di invertibilità dei cifrari di Feistel

### Soluzione dettagliata

#### Struttura del DES e notazione

Il DES è un cifrario di Feistel con 16 round. Notazione:
- $IP$: permutazione iniziale; $IP^{-1}$: permutazione finale (inversa di $IP$)
- $(L_i, R_i)$: metà sinistra e destra dopo il round $i$ ($i=0,\ldots,16$)
- $F$: funzione di round (espansione + XOR con sottochiave + S-box + permutazione $P$)
- $K_i$ ($i = 1, \ldots, 16$): sottochiavi schedulate dalla chiave principale

**Equazioni di cifratura (round $i$, $i=1,\ldots,16$):**

$$L_i = R_{i-1}$$
$$R_i = L_{i-1} \oplus F(R_{i-1}, K_i)$$

**Ciphertext:** $C = IP^{-1}(R_{16} \| L_{16})$ (le due metà vengono scambiate prima di $IP^{-1}$).

> ⚠️ Lo **swap finale** (scambio di $L_{16}$ e $R_{16}$ prima di $IP^{-1}$) è cruciale per la dimostrazione. Senza di esso la simmetria non funzionerebbe.

#### Dimostrazione della simmetria (proprietà DES)

**Enunciato:** applicare il DES al ciphertext $C$ con sottochiavi nell'ordine inverso $K_{16}, K_{15}, \ldots, K_1$ produce il plaintext $M$.

**Passo 1 — Punto di partenza della decifratura:**

Il ciphertext è $C = IP^{-1}(R_{16} \| L_{16})$. Applicando $IP$ al ciphertext:

$$IP(C) = R_{16} \| L_{16}$$

Definiamo lo stato iniziale della decifratura come $(L_0^D, R_0^D) = (R_{16}, L_{16})$ (notare lo swap: ciò che era la metà destra nella cifratura diventa la sinistra nella decifratura, e viceversa).

**Passo 2 — Primo round di decifratura con $K_{16}$:**

$$L_1^D = R_0^D = L_{16}$$

$$R_1^D = L_0^D \oplus F(R_0^D, K_{16}) = R_{16} \oplus F(L_{16}, K_{16})$$

Dall'ultimo round di cifratura sappiamo:
$$R_{16} = L_{15} \oplus F(R_{15}, K_{16}) \quad \text{e} \quad L_{16} = R_{15}$$

Sostituendo:
$$R_1^D = [L_{15} \oplus F(R_{15}, K_{16})] \oplus F(R_{15}, K_{16}) = L_{15} \oplus [F(R_{15}, K_{16}) \oplus F(R_{15}, K_{16})] = L_{15}$$

Quindi: $(L_1^D, R_1^D) = (L_{16}, L_{15}) = (R_{15}, L_{15})$.

**Passo 3 — Induzione:** dimostriamo che dopo il $j$-esimo round di decifratura (con chiave $K_{16-j+1}$):

$$(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$$

*Caso base $j=1$:* verificato al Passo 2: $(L_1^D, R_1^D) = (R_{15}, L_{15})$ ✓

*Passo induttivo:* supponiamo $(L_{j-1}^D, R_{j-1}^D) = (R_{16-j+1}, L_{16-j+1})$. Allora:

$$L_j^D = R_{j-1}^D = L_{16-j+1} = R_{16-j}$$

$$R_j^D = L_{j-1}^D \oplus F(R_{j-1}^D, K_{16-j+1}) = R_{16-j+1} \oplus F(L_{16-j+1}, K_{16-j+1})$$

Dall'equazione di cifratura del round $(16-j+1)$: $R_{16-j+1} = L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})$ e $L_{16-j+1} = R_{16-j}$, quindi:

$$R_j^D = [L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})] \oplus F(R_{16-j}, K_{16-j+1}) = L_{16-j}$$

Quindi $(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$ ✓ L'induzione regge.

**Passo 4 — Stato dopo 16 round di decifratura ($j=16$):**

$$(L_{16}^D, R_{16}^D) = (R_0, L_0)$$

**Passo 5 — Swap finale e applicazione di $IP^{-1}$:**

Prima di applicare $IP^{-1}$, il DES esegue lo swap: l'output del 16° round viene scambiato, producendo $R_{16}^D \| L_{16}^D = L_0 \| R_0$.

Applicando $IP^{-1}$:

$$IP^{-1}(L_0 \| R_0) = IP^{-1}(IP(M)) = M$$

**Conclusione:** la decifratura DES con chiavi in ordine inverso $K_{16}, K_{15}, \ldots, K_1$ produce esattamente il plaintext $M$ originale. $\blacksquare$

> 📌 Questa proprietà è intrinseca alla struttura di Feistel e vale per qualsiasi funzione $F$ (anche non invertibile). L'inversione è garantita dalla struttura, non dalla funzione di round.

---

## Domanda 3 — Funzioni MAC e HMAC (30 punti)

### Traccia originale

> **(30 punti) Funzioni MAC.**
>
> a. (10 punti) Descrivere caratteristiche e applicazioni delle funzioni MAC.
>
> b. (20 punti) Descrivere l'approccio HMAC e fornire una costruzione dove come funzione hash si sia selezionata SHA-1, descrivendo le caratteristiche.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD3/L3 - HMAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L3%20-%20HMAC.md) — costruzione HMAC
- [`../../M4_Funzioni_Hash_e_Mac/UD2/L1 - MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD2/L1%20-%20MAC.md) — definizione e applicazioni MAC

### Soluzione dettagliata

#### Parte a — Caratteristiche e applicazioni delle funzioni MAC (10 punti)

**Definizione:**

Un **Message Authentication Code (MAC)** è una funzione $MAC: \mathcal{K} \times \mathcal{M} \to \mathcal{T}$ che prende in input una chiave segreta $k$ e un messaggio $m$ e produce un tag di autenticazione $t = MAC(k, m)$.

**Caratteristiche principali:**

1. **Autenticità:** solo chi conosce la chiave $k$ può generare un tag valido. Il destinatario, conoscendo $k$, può verificare che il messaggio provenga dall'entità che conosce la chiave.

2. **Integrità:** qualsiasi modifica al messaggio $m$ (anche di un solo bit) produce un tag diverso con alta probabilità. Il destinatario verifica l'integrità ricalcolando $MAC(k, m')$ e confrontandolo con il tag ricevuto $t$.

3. **Non ripudiabilità condizionata:** a differenza delle firme digitali, il MAC richiede una chiave simmetrica condivisa. Entrambe le parti (mittente e destinatario) possono generare il tag, quindi il MAC non fornisce non ripudiabilità forte (ma garantisce autenticazione reciproca).

4. **Efficienza:** i MAC simmetrici sono molto più efficienti delle firme digitali asimmetriche, rendendoli preferibili per autenticazione di grandi quantità di dati.

**Proprietà di sicurezza:**

Il MAC deve essere sicuro contro **falsificazione esistenziale** (existential forgery): un avversario che osserva coppie $(m_i, t_i)$ non deve poter produrre un tag valido per un nuovo messaggio $m \notin \{m_i\}$ senza conoscere la chiave.

**Applicazioni:**

1. **Autenticazione di messaggi nelle reti:** TLS/SSL usa MAC (tipicamente HMAC) per autenticare i record di protocollo, garantendo che i dati non siano stati modificati in transito.
2. **Verifica di integrità dei file:** sistemi di verifica dell'integrità software usano MAC per rilevare modifiche non autorizzate.
3. **Protocolli di autenticazione:** SSH, IPsec, e altri protocolli usano MAC per autenticare i pacchetti.
4. **Cookies di sessione:** alcune implementazioni usano MAC per firmare i cookie e rilevare manomissioni.

> ⚠️ Il MAC garantisce **autenticazione e integrità** ma **non confidenzialità**. Per proteggere anche la riservatezza, si combina MAC con cifratura (paradigma Encrypt-then-MAC o Authenticated Encryption come AES-GCM).

#### Parte b — HMAC con SHA-1 (20 punti)

**Problema delle costruzioni MAC naive basate su hash:**

Un approccio ingenuo come $MAC(k, m) = H(k \| m)$ (prefisso della chiave) è vulnerabile all'**attacco di estensione della lunghezza** (length extension attack): sfruttando la struttura iterativa (Merkle-Damgård) di molte funzioni hash (SHA-1, MD5, SHA-256), un avversario che conosce $H(k \| m)$ può calcolare $H(k \| m \| \text{padding} \| m')$ senza conoscere $k$.

**Costruzione HMAC:**

HMAC (Hash-based MAC) è definito nella RFC 2104 e risolve il problema usando la chiave sia all'inizio che alla fine del calcolo hash:

$$\text{HMAC}(k, m) = H\big((k \oplus \text{opad}) \| H((k \oplus \text{ipad}) \| m)\big)$$

dove:
- $H$: funzione hash crittografica (in questo caso SHA-1)
- $k$: chiave, paddinata a 512 bit (dimensione del blocco di SHA-1); se $|k| > 512$ bit, si fa prima $k = H(k)$
- $\text{ipad}$: costante interna = byte $0x36$ ripetuto 64 volte (512 bit)
- $\text{opad}$: costante esterna = byte $0x5C$ ripetuto 64 volte (512 bit)

**Procedura passo-passo per HMAC-SHA-1:**

```
Input: chiave k, messaggio m

1. Se |k| > 64 byte: k = SHA-1(k)  // riduce la chiave
   Se |k| < 64 byte: k = k || 0x00...0  // padding con zeri a destra fino a 64 byte

2. Calcola k_ipad = k XOR ipad  // ipad = 0x3636...36 (64 byte)

3. Calcola k_opad = k XOR opad  // opad = 0x5C5C...5C (64 byte)

4. Hash interno: inner_hash = SHA-1(k_ipad || m)
   // k_ipad (64 byte) concatenato con m, poi SHA-1 → 20 byte

5. Hash esterno: HMAC = SHA-1(k_opad || inner_hash)
   // k_opad (64 byte) concatenato con inner_hash (20 byte), poi SHA-1 → 20 byte

Output: HMAC (20 byte = 160 bit)
```

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Caratteristiche di HMAC-SHA-1:**

- **Output:** 160 bit (20 byte), la stessa dimensione dell'output di SHA-1.
- **Sicurezza:** HMAC è provabilmente sicuro (forgery esistenziale computazionalmente difficile) se SHA-1 è una funzione pseudo-random. La sicurezza si riduce alla difficoltà di attaccare la funzione hash interna.
- **Resistenza al length extension:** l'hash esterno $H(k \oplus \text{opad} \| \cdot)$ impedisce l'attacco di estensione della lunghezza perché l'avversario non conosce il risultato dell'hash interno usato come input all'hash esterno.
- **Resistenza alle collisioni hash:** HMAC-SHA-1 rimane sicuro anche se vengono trovate collisioni per SHA-1 (senza chiave), purché SHA-1 rimanga una PRF con chiave.
- **Efficienza:** richiede solo 2 valutazioni di SHA-1 per blocchi di messaggio, con un overhead minimo rispetto a SHA-1 puro.

**Caratteristiche di SHA-1 (usata in HMAC-SHA-1):**

- Funzione hash della famiglia SHA (Secure Hash Algorithm), standardizzata dal NIST nel 1995.
- **Input:** messaggi di lunghezza arbitraria fino a $2^{64}-1$ bit.
- **Output:** digest di 160 bit.
- **Struttura:** iterativa Merkle-Damgård con funzione di compressione che processa blocchi da 512 bit.
- **Stato interno:** 5 word da 32 bit (160 bit totali).
- **Round:** 80 round organizzati in 4 fasi da 20 round ciascuna con funzioni logiche diverse.

> ⚠️ SHA-1 è considerata **non sicura** per molte applicazioni crittografiche a partire dal 2017, quando Google ha dimostrato una collisione pratica (SHAttered). Tuttavia, HMAC-SHA-1 rimane sicuro per l'autenticazione dei messaggi (MAC) perché la sicurezza del MAC non dipende dalla resistenza alle collisioni di SHA-1, ma dalla sua proprietà di PRF.

> 📌 Per nuove implementazioni si raccomanda HMAC-SHA-256 o HMAC-SHA-3, che usano funzioni hash più moderne e con output più lungo.

---

## Domanda 4 — Firma digitale DSS (25 punti)

### Traccia originale

> **(25 punti) Firma digitale DSS.**
>
> a. (5) Descrivere i parametri dell'algoritmo di firma DSS.
>
> b. (10) Descrivere la procedura di generazione e verifica della firma di un messaggio m.
>
> c. (10) Cosa succede se durante la generazione della firma $(r, s)$ di un messaggio $m$ si produce un valore $s = 0$. È vero che bisogna generare un nuovo valore casuale $k$ e ricalcolare la firma? Giustificare la risposta.

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md) — parametri, generazione e verifica DSS
- [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — fondamento matematico

### Soluzione dettagliata

#### Parte a — Parametri dell'algoritmo DSS (5 punti)

Il **Digital Signature Standard (DSS)** usa l'algoritmo **DSA (Digital Signature Algorithm)**. I parametri si dividono in:

**Parametri globali (condivisi da tutti gli utenti):**

- $p$: primo di lunghezza $L$ bit con $512 \leq L \leq 1024$ (multiplo di 64); deve valere $p-1 \equiv 0 \pmod{q}$.
- $q$: primo di 160 bit, divisore di $p-1$.
- $g$: generatore del sottogruppo di ordine $q$ in $\mathbb{Z}_p^*$, calcolato come $g = h^{(p-1)/q} \bmod p$ con $h$ tale che $h^{(p-1)/q} \not\equiv 1 \pmod{p}$.

**Parametri per utente:**

- $x$: chiave privata, intero casuale con $0 < x < q$.
- $y$: chiave pubblica, $y = g^x \bmod p$.

> 📌 La sicurezza del DSA si basa sulla difficoltà del **problema del logaritmo discreto** in $\mathbb{Z}_p^*$ (trovare $x$ da $y = g^x \bmod p$) e nella sua variante nel sottogruppo di ordine $q$.

#### Parte b — Generazione e verifica della firma (10 punti)

**Generazione della firma di un messaggio $m$:**

1. Scegliere un numero casuale $k$ con $0 < k < q$ (segreto, usa-e-getta: diverso per ogni firma).
2. Calcolare $r = (g^k \bmod p) \bmod q$.
   - Se $r = 0$, scegliere un nuovo $k$ e ricominciare.
3. Calcolare $k^{-1} \bmod q$ (inverso di $k$ modulo $q$, che esiste poiché $q$ è primo e $0 < k < q$).
4. Calcolare $s = k^{-1}(H(m) + xr) \bmod q$, dove $H$ è SHA-1.
   - Se $s = 0$, scegliere un nuovo $k$ e ricominciare.
5. La firma è la coppia $(r, s)$.

**Verifica della firma $(r, s)$ su messaggio $m$ con chiave pubblica $y$:**

1. Verificare $0 < r < q$ e $0 < s < q$; se una delle condizioni non vale, la firma è invalida.
2. Calcolare $w = s^{-1} \bmod q$.
3. Calcolare $u_1 = H(m) \cdot w \bmod q$.
4. Calcolare $u_2 = r \cdot w \bmod q$.
5. Calcolare $v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q$.
6. La firma è valida se e solo se $v = r$.

**Correttezza della verifica:**

$$v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q = (g^{H(m) \cdot w} \cdot g^{x \cdot r \cdot w} \bmod p) \bmod q$$

$$= (g^{w(H(m) + xr)} \bmod p) \bmod q$$

Poiché $s = k^{-1}(H(m) + xr) \bmod q$, si ha $w = s^{-1} = k(H(m) + xr)^{-1} \bmod q$, quindi:

$$w(H(m) + xr) \equiv k \pmod{q}$$

Dunque:

$$v = (g^k \bmod p) \bmod q = r$$

La verifica è corretta. $\blacksquare$

#### Parte c — Cosa succede se s=0 (10 punti)

**Risposta: sì, bisogna scegliere un nuovo $k$ e ricalcolare la firma. La giustificazione è duplice: matematica e di sicurezza.**

**Motivo matematico:**

La firma è la coppia $(r, s)$ con $s = k^{-1}(H(m) + xr) \bmod q$.

Se $s = 0$, la firma $(r, 0)$ non è una firma valida nel senso del protocollo, poiché durante la verifica si deve calcolare $w = s^{-1} \bmod q$, e $s = 0$ non ha inverso modulo $q$. La verifica fallirebbe (o produrrebbe un errore di divisione per zero). Pertanto la firma $(r, 0)$ è intrinsecamente inutilizzabile.

**Motivo di sicurezza:**

Supponiamo (per assurdo) che si accettasse $s = 0$ come firma valida e che la verifica fosse modificata per gestire questo caso. Allora:

$$s = k^{-1}(H(m) + xr) \equiv 0 \pmod{q}$$

Questo implica $H(m) + xr \equiv 0 \pmod{q}$, ovvero $xr \equiv -H(m) \pmod{q}$. Se $r \neq 0$ e $r$ è invertibile modulo $q$ (poiché $\gcd(r, q) = 1$ con alta probabilità), si potrebbe ricavare:

$$x \equiv -H(m) \cdot r^{-1} \pmod{q}$$

La chiave privata $x$ sarebbe **direttamente esposta** a chiunque conosca la firma $(r, 0)$, il messaggio $m$ e i parametri pubblici. L'intero sistema crittografico sarebbe compromesso.

**Probabilità che $s = 0$:**

$s = 0$ se e solo se $H(m) + xr \equiv 0 \pmod{q}$, evento che ha probabilità circa $1/q \approx 2^{-160}$, quindi estremamente raro in pratica. Tuttavia il protocollo deve gestire correttamente anche questo caso.

> ✅ **Conclusione:** se $s = 0$ (o anche $r = 0$), è obbligatorio generare un nuovo $k$ casuale e ricalcolare la firma. Questo garantisce che la firma sia valida sintatticamente (entrambi i componenti non nulli) e che non vengano esposti parametri segreti.

> ⚠️ Una vulnerabilità analoga esiste con $k$ riusato: se la stessa $k$ viene usata per firmare due messaggi diversi $m_1$ e $m_2$, si producono due firme $(r, s_1)$ e $(r, s_2)$ con lo stesso $r$. Da queste due firme si può calcolare $k$ e poi $x$. Questo è il motivo per cui $k$ deve essere generato casualmente e in modo fresco per ogni firma.

---

## Pattern e osservazioni per l'esame

Questo è l'appello della sessione straordinaria (febbraio). Caratteristiche da notare:

- **D1 (25 punti):** Playfair è una domanda classica che richiede pratica con la costruzione della griglia. La parte c con esempi numerici (cifrare e decifrare) vale 10 punti: fare attenzione alle regole (stessa riga, stessa colonna, rettangolo) e alla corrispondenza I=J.

- **D2 (20 punti):** la dimostrazione della simmetria DES è identica a quella dell'appello del 20/01/2025. È un teorema da conoscere a memoria con dimostrazione per induzione. La chiave è il ruolo dello swap finale nella struttura Feistel.

- **D3 (30 punti):** MAC e HMAC sono frequenti nelle sessioni straordinarie e autunnali. HMAC con SHA-1 richiede conoscenza della formula esatta con ipad/opad e della struttura a doppio hash.

- **D4 (25 punti):** DSS con il caso $s=0$ è una domanda trabocchetto classica del prof. Cimato. La risposta deve spiegare sia il motivo matematico (divisione per zero nella verifica) che il motivo di sicurezza (esposizione della chiave privata). Conoscere anche il caso analogo del $k$ riusato.

**Consigli pratici:**

- Per Playfair: costruire la griglia con molta cura, verificare che ogni lettera compaia una sola volta. Per la cifratura, applicare le 3 regole nell'ordine corretto.
- Per la dimostrazione DES: usare l'induzione in modo esplicito con notazione $(L_i^D, R_i^D)$ per distinguere dalla cifratura.
- Per HMAC: memorizzare la formula $H((k \oplus opad) \| H((k \oplus ipad) \| m))$ e i valori esatti di ipad ($0x36$) e opad ($0x5C$).
- Per DSS con $s=0$: strutturare la risposta in due parti (motivo matematico + motivo di sicurezza) per ottenere il massimo punteggio.
