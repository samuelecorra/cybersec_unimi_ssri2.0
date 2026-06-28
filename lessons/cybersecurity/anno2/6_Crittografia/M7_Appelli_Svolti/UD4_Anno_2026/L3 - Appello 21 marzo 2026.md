# Appello del 21 marzo 2026 — Soluzione completa

> 📌 **Informazioni appello:** 21/03/2026, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
>
> - [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — sostituzione su blocchi binari
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Reti di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Reti%20di%20Feistel.md) — reti di Feistel
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir

---

## Domanda 1 — Cifratura simmetrica: sostituzione a blocchi e cifrario ottale (20 punti)

### Traccia originale

> **(20 punti) Cifratura simmetrica.**
>
> a. (5 punti) Discutere la seguente affermazione: *"In un generico algoritmo di cifratura a sostituzione a blocchi di $n$ bit, le dimensioni della chiave sono $n \cdot 2^n$"*.
>
> b. Si consideri il seguente cifrario che prende blocchi binari di 3 bit, esegue una conversione in ottale, esegue un mapping, e restituisce blocchi binari di 3 bit.
>
> | Input ottale | Output ottale |
> |---|---|
> | 0 | 0 |
> | 1 | 1 |
> | 2 | 2 |
> | 3 | 3 |
> | 4 | 4 |
> | 5 | 5 |
> | 6 | 6 |
> | 7 | 7 |
>
> c. (5 punti) Si indichino le caratteristiche del cifrario, specificando quanti possibili cifrari diversi è possibile ottenere cambiando il mapping.
>
> d. (10 punti) Si abbia un linguaggio con 8 lettere: R, O, L, P, S, T, A, E dove si usa la rappresentazione decimale R=0, O=1, L=2, P=3, S=4, T=5, A=6, E=7. Per cifrare una lettera si converte in forma binaria, si applica il cifrario, e si ottiene una nuova lettera. Cifrare la parola "PROLE". Si faccia qualche considerazione sulla robustezza di questo cifrario.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — sostituzione su blocchi binari e dimensione chiave

### Soluzione dettagliata

#### Parte a — Affermazione sulla dimensione della chiave (5 punti)

L'affermazione è **corretta**. Ecco la dimostrazione:

Un cifrario a sostituzione su blocchi di $n$ bit è una permutazione $\pi: \{0,1\}^n \to \{0,1\}^n$. La **chiave** è la permutazione stessa, ovvero la tabella che specifica per ogni possibile plaintext l'output corrispondente.

Per specificare completamente tale tabella:
- Ci sono $2^n$ possibili input (blocchi di $n$ bit).
- Per ogni input, l'output richiede $n$ bit.
- Totale: $n \cdot 2^n$ bit.

$$\text{Dimensione chiave} = n \cdot 2^n \text{ bit}$$

Esempi:
| $n$ | $2^n$ input | Bit output per input | Totale |
|---|---|---|---|
| 3 | 8 | 3 | 24 bit |
| 8 | 256 | 8 | 2048 bit |
| 64 | $2^{64}$ | 64 | $64 \cdot 2^{64}$ bit (impraticabile) |

Questo spiega perché i cifrari a sostituzione generica non sono scalabili: per $n=64$ la chiave occuperebbe $\approx 10^{21}$ byte. I cifrari pratici (AES, DES) usano strutture algebriche compatte per rappresentare funzioni simili con chiavi di 128–256 bit.

#### Parte c — Caratteristiche del cifrario ottale (5 punti)

Il cifrario opera su blocchi di **3 bit** ($n=3$), mappando valori ottali $\{0,\ldots,7\}$ in $\{0,\ldots,7\}$. La chiave è una permutazione di 8 elementi.

**Quanti cifrari diversi è possibile ottenere cambiando il mapping:**

$$8! = 40320 \text{ permutazioni}$$

Il mapping mostrato nella traccia (identità: $0\to0, 1\to1, \ldots, 7\to7$) è uno solo di questi 40320.

**Dimensione della chiave:** $n \cdot 2^n = 3 \cdot 8 = 24$ bit (per specificare la permutazione completa).

#### Parte d — Cifratura di "PROLE" e robustezza (10 punti)

**Rappresentazioni:** R=0, O=1, L=2, P=3, S=4, T=5, A=6, E=7.

"PROLE" = P(3), R(0), O(1), L(2), E(7).

**Con il mapping identità** (0→0, 1→1, …, 7→7):

| Lettera | Decimale | Binario | Mapping | Binario out | Lettera out |
|---|---|---|---|---|---|
| P | 3 | 011 | 011→011 | 011 | P |
| R | 0 | 000 | 000→000 | 000 | R |
| O | 1 | 001 | 001→001 | 001 | O |
| L | 2 | 010 | 010→010 | 010 | L |
| E | 7 | 111 | 111→111 | 111 | E |

**Ciphertext: "PROLE"** (identico al plaintext).

**Considerazioni sulla robustezza:**

Il cifrario con mapping identità è completamente insicuro: il ciphertext è uguale al plaintext. Ma in generale, considerando un mapping casuale segreto:

1. **Vulnerabilità all'analisi delle frequenze:** con alfabeto di soli 8 simboli, le frequenze delle lettere si preservano nel ciphertext (è una sostituzione monoalfabetica). Con abbastanza testo cifrato è rompibile.

2. **Vulnerabilità a KPA:** ogni coppia (plaintext, ciphertext) rivela direttamente un elemento della tabella di sostituzione. Con 8 coppie note, la permutazione è completamente determinata e la chiave è ricostruita.

3. **Vulnerabilità a CPA:** l'avversario che sceglie i plaintext può cifrare tutti gli 8 simboli con un'unica query e ricostruire immediatamente l'intera tabella di sostituzione.

4. **Piccolo spazio delle chiavi:** $8! = 40320 \approx 2^{15{,}3}$ — uno spazio esauribile con brute force in meno di un secondo.

> ⚠️ Il cifrario è un cifrario di Cesare generalizzato su alfabeto di 8 simboli. Ha esattamente le stesse vulnerabilità di qualsiasi cifrario a sostituzione monoalfabetica classico.

---

## Domanda 2 — Rete di Feistel a 2 round con $f(x,K) = K \oplus \bar{x}$ (35 punti)

### Traccia originale

> **(35 punti) Cifratura simmetrica.** Si consideri una rete di Feistel a 2 fasi in cui la chiave $K$ è lunga la metà del blocco e la funzione round è definita come $f(x,K) = K \oplus \bar{x}$ (dove $\bar{x}$ è il complemento bit a bit, quindi se $x=0101$ allora $\bar{x}=1010$).
>
> i. (10) Si illustri come si ottiene il messaggio cifrato $C = L_2 R_2$ dalla cifratura del messaggio $M = L_0 R_0$.
>
> ii. (10) Si dimostri che la decifratura è possibile partendo da $C = L_2 R_2$ per ottenere $M = L_0 R_0$.
>
> iii. (5) Si cifri il messaggio $10101001$ con $K=1010$.
>
> iv. (10) Si facciano considerazioni sulla sicurezza dello schema proposto.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Reti di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Reti%20di%20Feistel.md) — reti di Feistel

### Soluzione dettagliata

#### Parte i — Cifratura $M = L_0 R_0 \to C = L_2 R_2$ (10 punti)

**Schema Feistel a 2 round** con funzione $f(x,K) = K \oplus \bar{x}$:

**Round 1:**

$$L_1 = R_0$$
$$R_1 = L_0 \oplus f(R_0, K) = L_0 \oplus K \oplus \bar{R}_0$$

**Round 2:**

$$L_2 = R_1 = L_0 \oplus K \oplus \bar{R}_0$$
$$R_2 = L_1 \oplus f(R_1, K) = R_0 \oplus K \oplus \bar{R}_1$$

dove $\bar{R}_1 = \overline{L_0 \oplus K \oplus \bar{R}_0}$.

**Ciphertext:** $C = L_2 \| R_2$.

#### Parte ii — Correttezza della decifratura (10 punti)

Dato $C = L_2 \| R_2$, si applica la struttura Feistel in senso inverso (le coppie $(L,R)$ si scambiano):

**Inverso round 2:** dato $(L_2, R_2)$, si recupera $(L_1, R_1)$:

$$R_1 = L_2 \quad \text{(dalla relazione } L_2 = R_1\text{)}$$
$$L_1 = R_2 \oplus f(L_2, K) = R_2 \oplus K \oplus \bar{L}_2$$

**Verifica di $L_1$:** sostituendo $R_2 = L_1 \oplus K \oplus \bar{R}_1 = L_1 \oplus K \oplus \bar{L}_2$:

$$R_2 \oplus K \oplus \bar{L}_2 = (L_1 \oplus K \oplus \bar{L}_2) \oplus K \oplus \bar{L}_2 = L_1 \oplus (K \oplus K) \oplus (\bar{L}_2 \oplus \bar{L}_2) = L_1 \checkmark$$

**Inverso round 1:** dato $(L_1, R_1)$, si recupera $(L_0, R_0)$:

$$R_0 = L_1 \quad \text{(dalla relazione } L_1 = R_0\text{)}$$
$$L_0 = R_1 \oplus f(L_1, K) = R_1 \oplus K \oplus \bar{L}_1$$

**Verifica di $L_0$:** sostituendo $R_1 = L_0 \oplus K \oplus \bar{R}_0 = L_0 \oplus K \oplus \bar{L}_1$:

$$R_1 \oplus K \oplus \bar{L}_1 = (L_0 \oplus K \oplus \bar{L}_1) \oplus K \oplus \bar{L}_1 = L_0 \checkmark$$

> 📌 La decifratura di una rete di Feistel usa sempre la stessa struttura con le stesse funzioni round, semplicemente applicando i round in ordine inverso con $L$ e $R$ scambiati. Non è necessario invertire la funzione $f$.

#### Parte iii — Cifratura di $10101001$ con $K=1010$ (5 punti)

**Messaggio:** $M = L_0 \| R_0 = 1010 \| 1001$, $K = 1010$.

**Round 1:**

$$L_1 = R_0 = 1001$$

$$\bar{R}_0 = \overline{1001} = 0110$$

$$R_1 = L_0 \oplus K \oplus \bar{R}_0 = 1010 \oplus 1010 \oplus 0110$$

$$1010 \oplus 1010 = 0000, \qquad 0000 \oplus 0110 = 0110$$

$$R_1 = 0110$$

**Round 2:**

$$L_2 = R_1 = 0110$$

$$\bar{R}_1 = \overline{0110} = 1001$$

$$R_2 = L_1 \oplus K \oplus \bar{R}_1 = 1001 \oplus 1010 \oplus 1001$$

$$1001 \oplus 1010 = 0011, \qquad 0011 \oplus 1001 = 1010$$

$$R_2 = 1010$$

**Ciphertext:** $C = L_2 \| R_2 = \mathbf{0110\ 1010}$.

**Verifica della decifratura:**

Dato $C = 0110 \| 1010$:

Inverso round 2: $R_1 = L_2 = 0110$; $L_1 = R_2 \oplus K \oplus \bar{L}_2 = 1010 \oplus 1010 \oplus \overline{0110} = 0000 \oplus 1001 = 1001$ ✓

Inverso round 1: $R_0 = L_1 = 1001$ ✓; $L_0 = R_1 \oplus K \oplus \bar{L}_1 = 0110 \oplus 1010 \oplus \overline{1001} = 0110 \oplus 1010 \oplus 0110 = 1010$ ✓

#### Parte iv — Considerazioni sulla sicurezza (10 punti)

**Struttura generale:** la rete di Feistel con 2 round è una costruzione valida (decifrabile), ma ha diverse vulnerabilità specifiche dovute alla funzione round $f(x,K) = K \oplus \bar{x}$.

**1. La funzione round $f$ è lineare:**

$$f(x,K) = K \oplus \bar{x} = K \oplus \mathbf{1}^n \oplus x$$

dove $\mathbf{1}^n$ è la stringa di tutti 1. Questa è un'operazione **affine** (XOR con costante), non una funzione non-lineare. I cifrari robusti richiedono non-linearità per resistere alla crittoanalisi lineare. AES usa S-box non-lineari; questa funzione è completamente lineare.

**2. Solo 2 round:**

Le reti di Feistel robusti usano tipicamente 16+ round (DES: 16 round, Blowfish: 16 round). Con 2 soli round, la diffusione (spreading dei bit) è limitata:
- Ogni bit di $L_0$ influenza solo $L_2$ e parte di $R_2$ dopo 2 round.
- Non si raggiunge la "confusione" e "diffusione" richieste da Shannon.

**3. Vulnerabilità a KPA:**

Data la linearità della funzione round, le equazioni che legano plaintext, ciphertext e chiave sono sistemi lineari. Con sufficienti coppie $(M_i, C_i)$, l'avversario può impostare e risolvere un sistema lineare per trovare $K$.

**4. Relazione tra $L_2$ e la chiave:**

$$L_2 = L_0 \oplus K \oplus \bar{R}_0$$

Dato un KPA con $M = (L_0, R_0)$ e $C = (L_2, R_2)$:

$$K = L_0 \oplus L_2 \oplus \bar{R}_0$$

**Con una sola coppia plaintext-ciphertext nota, la chiave $K$ è completamente determinata!**

> ⚠️ Questo cifrario è **completamente insicuro contro KPA**: una singola coppia $(M, C)$ rivela la chiave. La linearità di $f(x,K) = K \oplus \bar{x}$ rispetto a $K$ è la causa fondamentale di questa debolezza.

**Riepilogo:**

| Proprietà | Valore | Problema |
|---|---|---|
| Linearità di $f$ | ✗ Non-lineare | $f$ è affine: vulnerabile a crittoanalisi lineare |
| Numero di round | 2 | Troppo basso per diffusione adeguata |
| Sicurezza contro COA | Debole | Solo $2^{|K|}$ chiavi da testare |
| Sicurezza contro KPA | ✗ Nulla | Chiave determinata da 1 coppia |

---

## Domanda 3 — Funzioni Hash: birthday attack e one-wayness di $H(x) = \text{DES}_k(x)$ (30 punti)

### Traccia originale

> **(30 punti) Funzioni hash.**
>
> a. (15 punti) Si descriva l'attacco a compleanno e le sue implicazioni per le funzioni hash.
>
> b. (15 punti) Si discuta la proprietà di one-wayness per la funzione hash $H(x) = \text{DES}_k(x)$ per un certo $k$. Motivare le risposte alle seguenti domande:
>
> i. Se $k$ può variare, in generale $H(x)$ è una funzione one-way?
>
> ii. Per $k$ fisso, $H(x)$ è una funzione one-way se $k$ è nota?
>
> iii. Per $k$ fisso, $H(x)$ è una funzione one-way se $k$ non è nota?

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà di sicurezza hash

### Soluzione dettagliata

#### Parte a — Attacco a compleanno e implicazioni (15 punti)

**Il paradosso del compleanno.** In un gruppo di 23 persone, la probabilità che due condividano il compleanno supera il 50%, nonostante ci siano 365 possibili compleanni. Il motivo: il numero di coppie è $\binom{23}{2} = 253 \approx \frac{365}{2}$.

**Attacco a compleanno su funzioni hash.** Per una funzione $H: \{0,1\}^* \to \{0,1\}^m$ con output da $m$ bit, trovare una **collisione** (due input diversi con stesso hash) richiede:

$$n \approx 1{,}17 \cdot 2^{m/2} \text{ tentativi}$$

invece di $O(2^m)$ necessari per un attacco brute-force diretto. Il costo è $O(2^{m/2})$.

**Algoritmo:**
1. Genera $t \approx 2^{m/2}$ messaggi casuali $x_1, \ldots, x_t$ e i loro hash $H(x_i)$.
2. Inseriscili in una tabella hash indicizzata per output.
3. Cerca collisioni: due input con lo stesso output.
4. Con alta probabilità ($>50\%$) almeno una coppia collide entro $O(2^{m/2})$ operazioni.

**Implicazioni per le funzioni hash:**

| $m$ (bit output) | Sicurezza contro preimage | Sicurezza contro collisione | Sicuro? |
|---|---|---|---|
| 64 | $2^{64}$ | $2^{32}$ | ✗ (pratico) |
| 128 | $2^{128}$ | $2^{64}$ | Borderline |
| 160 (SHA-1) | $2^{160}$ | $2^{80}$ | ✗ obsoleto |
| 256 (SHA-256) | $2^{256}$ | $2^{128}$ | ✓ sicuro |

> ⚠️ Il birthday attack rende necessario raddoppiare la lunghezza dell'output per mantenere il livello di sicurezza desiderato contro le collisioni. Per 128 bit di sicurezza contro collisioni, l'hash deve avere almeno 256 bit di output: ecco perché SHA-256 è il minimo raccomandato oggi.

#### Parte b — One-wayness di $H(x) = \text{DES}_k(x)$ (15 punti)

**Caratteristiche di $H(x) = \text{DES}_k(x)$:**

DES è un cifrario a blocchi con blocchi da 64 bit e chiave da 56 bit. Per $k$ fissa, $\text{DES}_k$ è una permutazione su $\{0,1\}^{64}$:
- Dominio = Codominio = $\{0,1\}^{64}$ (nessuna compressione).
- Ogni output ha esattamente una preimmagine (per la chiave fissa $k$).
- L'algoritmo di inversione (decifratura DES) è pubblico e altrettanto efficiente della cifratura.

> ⚠️ Come AES, anche DES usato direttamente come hash non comprime il dato (dominio = codominio), il che viola la definizione standard di funzione hash crittografica (che richiede output fisso e dominio arbitrariamente grande).

**i. Se $k$ può variare:**

Se $k$ è variabile e noto (pubblico), per ogni output $h$ dato, l'avversario sceglie liberamente qualsiasi $k'$ e calcola $x = \text{DES}_{k'}^{-1}(h)$ — trovando così una preimmagine in tempo $O(1)$.

→ $H$ **NON è one-way** quando $k$ è variabile e noto: la preimmagine si trova immediatamente.

**ii. Per $k$ fisso e noto:**

$\text{DES}_k$ è una permutazione nota con algoritmo di inversione esplicito (la decifratura DES). Dato $h = \text{DES}_k(x)$:

$$x = \text{DES}_k^{-1}(h) \quad \text{(calcolato in O(1))}$$

→ $H$ **NON è one-way** quando $k$ è fisso e noto.

**iii. Per $k$ fisso e non noto:**

Dato $h = \text{DES}_k(x)$ con $k$ sconosciuto, l'avversario vuole trovare $x$.

**Approccio:** ricerca esaustiva sulla chiave.
- Per ogni $k' \in \{0,1\}^{56}$: calcola $x' = \text{DES}_{k'}^{-1}(h)$.
- Senza ulteriori informazioni: non è possibile verificare quale $x'$ è corretto (qualsiasi $k'$ produce un candidato plausibile).
- Con almeno una coppia $(m^*, c^*)$ aggiuntiva nota: si determina $k$ univocamente (o si riduce il numero di candidati), poi si calcola $x = \text{DES}_k^{-1}(h)$.

**Complessità:** $2^{56}$ operazioni per esaurire lo spazio delle chiavi.

Con gli standard di sicurezza attuali (2026), $2^{56}$ è computazionalmente **raggiungibile** con hardware dedicato (Deep Crack ha rotto DES-56 in 22 ore nel 1998; con hardware moderno il tempo è di minuti). Quindi:

→ $H$ con $k$ fisso e non noto è **teoricamente one-way** (richiede $2^{56}$ lavoro), ma **non è computazionalmente sicura** con gli standard moderni poiché $2^{56}$ è praticabile.

**Riepilogo:**

| Caso | One-way? | Motivazione |
|---|---|---|
| $k$ variabile (noto) | ✗ NO | Si calcola $\text{DES}_{k'}^{-1}(h)$ con chiave scelta liberamente |
| $k$ fisso, noto | ✗ NO | $x = \text{DES}_k^{-1}(h)$ con algoritmo di decifratura pubblico |
| $k$ fisso, non noto | Teoricamente sì, praticamente ✗ | Richiede $2^{56}$ brute-force su chiave: praticabile oggi |

> 💡 **Confronto con AES:** $H(x) = \text{AES}_k(x)$ con $k$ non noto offre $2^{128}$ sicurezza (chiave a 128 bit), considerata sicura. $H(x) = \text{DES}_k(x)$ con $k$ non noto offre solo $2^{56}$ sicurezza, non più sufficiente. La differenza sta interamente nella lunghezza della chiave.

---

## Domanda 4 — Secret Sharing (15 punti)

### Traccia originale

> **(15 punti) Secret Sharing.**
>
> a. (15) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$ con un esempio di applicazione dello schema $(2,3)$ considerando $\mathbb{Z}_{11}$ e il segreto $s=7$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Schema $(k,n)$: distribuzione, ricostruzione ed esempio $(2,3)$ in $\mathbb{Z}_{11}$ con $s=7$ (15 punti)

**Distribuzione (dealer $\to$ $n$ partecipanti):**

Il dealer possiede il segreto $s \in \mathbb{Z}_p$ e sceglie un primo $p > n$. Costruisce un polinomio casuale di grado $k-1$:

$$f(x) = s + a_1 x + a_2 x^2 + \ldots + a_{k-1} x^{k-1} \pmod{p}$$

con $a_1, \ldots, a_{k-1}$ scelti casualmente in $\mathbb{Z}_p$. Il partecipante $P_i$ riceve la share $s_i = f(i) \pmod{p}$.

**Proprietà:** qualsiasi $k$ share permettono di ricostruire $s$; con meno di $k$ share il segreto è perfettamente nascosto (sicurezza alla Shannon).

**Ricostruzione (qualsiasi $k$ partecipanti):**

Dati $k$ punti $(i_1, s_{i_1}), \ldots, (i_k, s_{i_k})$, si usa l'interpolazione di Lagrange:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1\\l\neq j}}^{k} \frac{0 - i_l}{i_j - i_l} \pmod{p}$$

---

**Esempio applicativo: schema $(2,3)$ in $\mathbb{Z}_{11}$, segreto $s=7$.**

Il polinomio ha grado $k-1=1$: $f(x) = 7 + a_1 x \pmod{11}$.

**Scelta di $a_1$ casuale:** sia $a_1 = 3$ (valore scelto dal dealer).

$$f(x) = 7 + 3x \pmod{11}$$

**Calcolo delle share per i 3 partecipanti:**

$$s_1 = f(1) = 7 + 3 = 10 \pmod{11}$$
$$s_2 = f(2) = 7 + 6 = 13 \bmod 11 = 2$$
$$s_3 = f(3) = 7 + 9 = 16 \bmod 11 = 5$$

Le 3 share distribuite sono: $(P_1, 10)$, $(P_2, 2)$, $(P_3, 5)$.

**Ricostruzione con le share di $P_1$ e $P_2$** (punti $(1,10)$ e $(2,2)$):

$$f(0) = 10 \cdot \lambda_1 + 2 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0-2}{1-2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0-1}{2-1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 10 \cdot 2 + 2 \cdot 10 = 20 + 20 = 40 \pmod{11}$$

$40 = 3 \cdot 11 + 7$, quindi $40 \bmod 11 = 7$.

$$\boxed{s = 7} \checkmark$$

**Ricostruzione alternativa con $P_1$ e $P_3$** (punti $(1,10)$ e $(3,5)$) — verifica:

$$\lambda_1 = \frac{0-3}{1-3} = \frac{-3}{-2} = 3 \cdot 2^{-1} \bmod 11$$

$2^{-1} \bmod 11$: $2 \cdot 6 = 12 \equiv 1 \pmod{11}$, quindi $2^{-1} = 6$.

$$\lambda_1 = 3 \cdot 6 = 18 \bmod 11 = 7$$

$$\lambda_3 = \frac{0-1}{3-1} = \frac{-1}{2} = -1 \cdot 6 = -6 \equiv 5 \pmod{11}$$

$$f(0) = 10 \cdot 7 + 5 \cdot 5 = 70 + 25 = 95 \pmod{11}$$

$95 = 8 \cdot 11 + 7$, quindi $95 \bmod 11 = 7$. $\checkmark$

> 💡 Con qualsiasi sottoinsieme di $k=2$ share su $n=3$, si ricostruisce sempre lo stesso segreto $s=7$. Con una sola share, non è possibile ricavare alcuna informazione su $s$ (sicurezza perfetta).

---

## Pattern e osservazioni per l'esame

**Struttura:**

- D1 (20 punti): sostituzione a blocchi 3-bit/ottale. Il mapping è identità: "PROLE" cifra a "PROLE". La parte teorica (affermazione $n \cdot 2^n$) è diretta; la parte pratica è immediata con il mapping dato.
- D2 (35 punti, la più pesante): Feistel 2 round con $f(x,K) = K \oplus \bar{x}$. La linearità di $f$ porta alla vulnerabilità critica: una sola coppia KPA rivela $K = L_0 \oplus L_2 \oplus \bar{R}_0$. Il professore chiede questa osservazione nella parte iv.
- D3 (30 punti): attacco a compleanno (identico a L2, D2a) + one-wayness DES. La struttura è analoga alla D2b di L2 (AES), ma DES ha solo 56 bit di chiave → la risposta a "k non nota" è "teoricamente sì ma praticamente no" invece di "sì".
- D4 (15 punti, la più leggera del 2026): segreto $s=7$ dato, chiede di costruire l'esempio. Si sceglie $a_1$ liberamente e si derivano le share.
