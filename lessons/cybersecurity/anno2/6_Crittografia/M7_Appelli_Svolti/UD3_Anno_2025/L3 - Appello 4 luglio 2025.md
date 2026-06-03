# Appello del 4 luglio 2025 — Soluzione completa

> 📌 **Informazioni appello:** 04/07/2025, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD3/L2%20-%20Il%20Cifrario%20di%20Hill.md) — funzionamento e sicurezza del cifrario di Hill
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD4/L1 - BlowFish.md`](../../M2_Cifrature_Simmetriche_Moderne/UD4/L1%20-%20BlowFish.md) — caratteristiche del cifrario Blowfish
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno
> - [`../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L1 - H(x)=DES_k(x) - analisi one-wayness.md`](../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L1%20-%20H(x)%3DDES_k(x)%20-%20analisi%20one-wayness.md) — one-wayness di DES usato come hash
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema (k,n) e (n,n)

---

## Domanda 1 — Cifrario Hill (25 punti)

### Traccia originale

> **(25 punti) Cifrario Hill.**
>
> a. (10 punti) Descrivere il funzionamento del cifrario di Hill e la sicurezza del cifrario di Hill rispetto a ciphertext-only e known plaintext attack.
>
> b. (15 punti) Sia data la seguente matrice: $k_{11}=3,\ k_{12}=7,\ k_{21}=2,\ k_{22}=5$.
>
> i. Cifrare "ciao"
>
> ii. Decifrare "gocw"

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD3/L2%20-%20Il%20Cifrario%20di%20Hill.md) — algebra del cifrario Hill, inversione matriciale in $\mathbb{Z}_{26}$

### Soluzione dettagliata

#### Parte a — Funzionamento e sicurezza del cifrario di Hill (10 punti)

**Funzionamento:**

Il cifrario di Hill è un cifrario a sostituzione **polialfabetica** basato sull'algebra lineare. Opera su blocchi di $m$ lettere alla volta.

**Setup:**
- Si sceglie una dimensione del blocco $m$ (tipicamente $m=2$ o $m=3$).
- La chiave è una matrice $K$ di dimensione $m \times m$ con coefficienti in $\mathbb{Z}_{26}$, tale che $K$ sia invertibile in $\mathbb{Z}_{26}$ (cioè $\gcd(\det K, 26) = 1$).

**Codifica:** si associa ogni lettera al suo numero: A=0, B=1, C=2, ..., Z=25.

**Cifratura:** dato un vettore plaintext $\mathbf{p} = (p_1, p_2, \ldots, p_m)^T$, il vettore cifrato è:

$$\mathbf{c} = K \cdot \mathbf{p} \pmod{26}$$

**Decifratura:** dato $\mathbf{c}$, si recupera $\mathbf{p}$ con:

$$\mathbf{p} = K^{-1} \cdot \mathbf{c} \pmod{26}$$

dove $K^{-1}$ è la matrice inversa di $K$ in $\mathbb{Z}_{26}$.

**Sicurezza contro ciphertext-only attack:**

Il cifrario di Hill **resiste** all'analisi delle frequenze semplice (ciphertext-only) perché cifra blocchi di lettere insieme: la stessa lettera può produrre cifrati diversi a seconda del contesto (delle lettere adiacenti). Tuttavia, la struttura lineare lascia pattern statistici sui bigrammi o trigrammi, rendendo il cifrario vulnerabile a un'analisi delle frequenze su blocchi di $m$ lettere.

**Sicurezza contro known plaintext attack:**

Il cifrario di Hill è **totalmente insicuro** contro known plaintext attack. Se l'avversario conosce $m$ coppie (plaintext block, ciphertext block) linearmente indipendenti, può costruire il sistema:

$$C = K \cdot P \pmod{26}$$

dove $P$ è la matrice $m \times m$ con i vettori plaintext come colonne e $C$ è la matrice $m \times m$ con i vettori ciphertext. Se $P$ è invertibile, la chiave è:

$$K = C \cdot P^{-1} \pmod{26}$$

> ⚠️ Per il cifrario di Hill con $m=2$, bastano 2 coppie (plaintext, ciphertext) linearmente indipendenti per determinare completamente la chiave $K$ con un known plaintext attack.

#### Parte b.i — Cifratura di "ciao" (10 punti)

**Matrice chiave:**

$$K = \begin{pmatrix} 3 & 7 \\ 2 & 5 \end{pmatrix}$$

**Codifica di "ciao":** C=2, I=8, A=0, O=14.

Si cifra a blocchi di 2 lettere: $(C,I) = (2,8)$ e $(A,O) = (0,14)$.

**Blocco 1: $(2, 8)^T$**

$$\begin{pmatrix} 3 & 7 \\ 2 & 5 \end{pmatrix} \begin{pmatrix} 2 \\ 8 \end{pmatrix} = \begin{pmatrix} 3 \cdot 2 + 7 \cdot 8 \\ 2 \cdot 2 + 5 \cdot 8 \end{pmatrix} = \begin{pmatrix} 6 + 56 \\ 4 + 40 \end{pmatrix} = \begin{pmatrix} 62 \\ 44 \end{pmatrix}$$

Riduzione modulo 26:
- $62 \bmod 26 = 62 - 2 \cdot 26 = 62 - 52 = 10$ → lettera K
- $44 \bmod 26 = 44 - 26 = 18$ → lettera S

Primo blocco cifrato: **KS**

**Blocco 2: $(0, 14)^T$**

$$\begin{pmatrix} 3 & 7 \\ 2 & 5 \end{pmatrix} \begin{pmatrix} 0 \\ 14 \end{pmatrix} = \begin{pmatrix} 3 \cdot 0 + 7 \cdot 14 \\ 2 \cdot 0 + 5 \cdot 14 \end{pmatrix} = \begin{pmatrix} 0 + 98 \\ 0 + 70 \end{pmatrix} = \begin{pmatrix} 98 \\ 70 \end{pmatrix}$$

Riduzione modulo 26:
- $98 \bmod 26 = 98 - 3 \cdot 26 = 98 - 78 = 20$ → lettera U
- $70 \bmod 26 = 70 - 2 \cdot 26 = 70 - 52 = 18$ → lettera S

Secondo blocco cifrato: **US**

**Risultato della cifratura di "ciao": KSUS**

#### Parte b.ii — Decifratura di "gocw" (5 punti)

Per decifrare, calcoliamo $K^{-1} \pmod{26}$.

**Calcolo di $K^{-1}$:**

$\det K = 3 \cdot 5 - 7 \cdot 2 = 15 - 14 = 1$

Poiché $\det K = 1$ e $\gcd(1, 26) = 1$, la matrice è invertibile in $\mathbb{Z}_{26}$.

$(\det K)^{-1} = 1^{-1} = 1 \pmod{26}$

La formula per l'inversa di una matrice $2\times 2$ è:

$$K^{-1} = (\det K)^{-1} \begin{pmatrix} k_{22} & -k_{12} \\ -k_{21} & k_{11} \end{pmatrix} \pmod{26}$$

$$K^{-1} = 1 \cdot \begin{pmatrix} 5 & -7 \\ -2 & 3 \end{pmatrix} \pmod{26} = \begin{pmatrix} 5 & 19 \\ 24 & 3 \end{pmatrix} \pmod{26}$$

(poiché $-7 \bmod 26 = 19$ e $-2 \bmod 26 = 24$)

**Verifica:**

$$K \cdot K^{-1} = \begin{pmatrix} 3 & 7 \\ 2 & 5 \end{pmatrix} \begin{pmatrix} 5 & 19 \\ 24 & 3 \end{pmatrix} \pmod{26}$$

$$= \begin{pmatrix} 15+168 & 57+21 \\ 10+120 & 38+15 \end{pmatrix} = \begin{pmatrix} 183 & 78 \\ 130 & 53 \end{pmatrix} \pmod{26}$$

$$= \begin{pmatrix} 183 \bmod 26 & 78 \bmod 26 \\ 130 \bmod 26 & 53 \bmod 26 \end{pmatrix} = \begin{pmatrix} 1 & 0 \\ 0 & 1 \end{pmatrix} \checkmark$$

**Codifica di "gocw":** G=6, O=14, C=2, W=22.

Blocchi: $(G,O) = (6,14)$ e $(C,W) = (2,22)$.

**Blocco 1: $(6,14)^T$**

$$K^{-1} \begin{pmatrix} 6 \\ 14 \end{pmatrix} = \begin{pmatrix} 5 & 19 \\ 24 & 3 \end{pmatrix} \begin{pmatrix} 6 \\ 14 \end{pmatrix} = \begin{pmatrix} 30 + 266 \\ 144 + 42 \end{pmatrix} = \begin{pmatrix} 296 \\ 186 \end{pmatrix}$$

- $296 \bmod 26 = 296 - 11 \cdot 26 = 296 - 286 = 10$ → lettera K
- $186 \bmod 26 = 186 - 7 \cdot 26 = 186 - 182 = 4$ → lettera E

Primo blocco decrittato: **KE**

**Blocco 2: $(2,22)^T$**

$$K^{-1} \begin{pmatrix} 2 \\ 22 \end{pmatrix} = \begin{pmatrix} 5 & 19 \\ 24 & 3 \end{pmatrix} \begin{pmatrix} 2 \\ 22 \end{pmatrix} = \begin{pmatrix} 10 + 418 \\ 48 + 66 \end{pmatrix} = \begin{pmatrix} 428 \\ 114 \end{pmatrix}$$

- $428 \bmod 26 = 428 - 16 \cdot 26 = 428 - 416 = 12$ → lettera M
- $114 \bmod 26 = 114 - 4 \cdot 26 = 114 - 104 = 10$ → lettera K

Secondo blocco decrittato: **MK**

**Risultato della decifratura di "gocw": KEMK**

---

## Domanda 2 — Blowfish (15 punti)

### Traccia originale

> **(15 punti) Crittosistema Blowfish.**
>
> a. Descrivere le principali caratteristiche del cifrario Blowfish.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD4/L1 - BlowFish.md`](../../M2_Cifrature_Simmetriche_Moderne/UD4/L1%20-%20BlowFish.md) — struttura completa di Blowfish

### Soluzione dettagliata

Blowfish è un cifrario a blocchi simmetrico progettato da Bruce Schneier nel 1993 come alternativa libera e più veloce al DES.

**Caratteristiche principali:**

**Struttura:**
- Cifrario di **Feistel** a 16 round.
- Opera su blocchi di **64 bit**.
- Chiave di lunghezza variabile da **32 a 448 bit** (multipli di 8 bit).

**Componenti:**
- **Array P:** 18 sottochiavi a 32 bit ($P_1, P_2, \ldots, P_{18}$), generate dalla chiave principale. Le prime due e le ultime due vengono usate come XOR pre/post-round (whitening).
- **S-box:** 4 S-box di dimensione $256 \times 32$ bit (ognuna mappa 8 bit di input in 32 bit di output). Le S-box sono dipendenti dalla chiave (vengono generate durante il key scheduling).

**Funzione di round $F$:** dato un input di 32 bit diviso in 4 byte $a, b, c, d$:

$$F(x) = ((S_1[a] + S_2[b] \bmod 2^{32}) \oplus S_3[c]) + S_4[d] \bmod 2^{32}$$

**Key scheduling:**

Il processo di generazione delle sottochiavi è costoso:
1. Si inizializzano le S-box e l'array P con le cifre decimali di $\pi$ (per evitare backdoor).
2. Si esegue lo XOR dell'array P con i bit della chiave (ripetendo la chiave se necessario).
3. Si eseguono 521 cifrature con la chiave corrente per generare i valori finali di P e delle S-box.

> 📌 Il key scheduling di Blowfish richiede l'equivalente di **4096 cifrature DES**. Questo lo rende eccellente contro attacchi brute-force con chiavi diverse (ogni nuova chiave richiede molto tempo per inizializzare), ma inadatto in scenari dove la chiave cambia frequentemente.

**Sicurezza:**
- Nessun attacco pratico noto contro Blowfish con chiave $\geq$ 128 bit.
- Vulnerabile agli attacchi a chiave correlata (birthday attack sulle S-box) solo con chiavi molto brevi ($\leq 64$ bit).
- Non adatto per proteggere password quando usato come KDF diretto (bcrypt, che usa Blowfish, risolve questo con un costo di hashing configurabile).

**Limitazioni:**
- Il blocco da 64 bit è considerato piccolo per applicazioni moderne (vulnerabile al birthday attack con dati $\geq 4$ GB): si preferisce oggi AES (128 bit di blocco).
- Non è adatto per ambienti con vincoli di memoria stretti: le S-box richiedono circa 4 KB di RAM.

> 💡 Blowfish è stato progettato esplicitamente come software-optimized cipher: è molto veloce su CPU general-purpose degli anni '90. AES, progettato dieci anni dopo, è oggi più veloce grazie alle istruzioni hardware AES-NI.

---

## Domanda 3 — Funzioni hash: attacco a compleanno e one-wayness di $H(x) = DES_k(x)$ (30 punti)

### Traccia originale

> **(30 punti) Funzioni hash.**
>
> a. (15 punti) Si descriva l'attacco a compleanno e le sue implicazioni per le funzioni hash.
>
> b. (15 punti) Si discuta la proprietà di one-wayness per la funzione hash $H(x) = DES_k(x)$ per un certo $k$. Motivare le risposte alle seguenti domande:
>
> i. Se $k$ può variare, in generale $H(x)$ è una funzione one way?
>
> ii. Per $k$ fisso, $H(x)$ è una funzione one way se $k$ è nota?
>
> iii. Per $k$ fisso, $H(x)$ è una funzione one way se $k$ non è nota?

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno
- [`../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L1 - H(x)=DES_k(x) - analisi one-wayness.md`](../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L1%20-%20H(x)%3DDES_k(x)%20-%20analisi%20one-wayness.md) — analisi dettagliata

### Soluzione dettagliata

#### Parte a — Attacco a compleanno e implicazioni (15 punti)

**Il paradosso del compleanno:**

Quante persone devo raccogliere in una stanza affinché ci sia probabilità $\geq 50\%$ che due di esse condividano il compleanno? La risposta intuitiva sarebbe vicina a 183 (metà di 365), ma la risposta matematica corretta è solo **23**.

Il motivo è che cerchiamo una collisione tra **qualsiasi** coppia, non una collisione con una persona specifica. Il numero di coppie possibili tra $n$ persone è $\binom{n}{2} = n(n-1)/2$, che cresce quadraticamente in $n$.

**Applicazione alle funzioni hash:**

Sia $H: \{0,1\}^* \to \{0,1\}^n$ una funzione hash con output di $n$ bit ($N = 2^n$ possibili valori).

- **Trovare una preimmagine** (target specifico): richiede circa $O(2^n)$ valutazioni di $H$ (brute force).
- **Trovare una collisione** (qualsiasi coppia): il birthday bound garantisce che con $O(2^{n/2})$ valutazioni si trova una collisione con probabilità $\geq 50\%$.

**L'attacco a compleanno formalmente:**

1. Genera casualmente $q$ messaggi $m_1, m_2, \ldots, m_q$.
2. Calcola $H(m_i)$ per ogni $i$.
3. Cerca tra i $q$ hash una coppia con lo stesso valore.

La probabilità di trovare almeno una collisione è circa:

$$P_{\text{collision}} \approx 1 - e^{-q^2 / (2 \cdot 2^n)}$$

Per $P_{\text{collision}} \geq 50\%$, si richiede $q \approx 1.17 \cdot 2^{n/2}$.

**Implicazioni pratiche:**

| Lunghezza hash $n$ | Costo brute-force preimage | Costo birthday (collisione) |
|--------------------|---------------------------|----------------------------|
| 64 bit | $2^{64} \approx 1.8 \times 10^{19}$ | $2^{32} \approx 4.3 \times 10^9$ (fattibile!) |
| 128 bit | $2^{128}$ | $2^{64}$ |
| 256 bit | $2^{256}$ | $2^{128}$ |

> ⚠️ MD5 (128 bit di output) offre sicurezza $2^{64}$ contro collisioni: nel 2004 sono state trovate collisioni per MD5. SHA-1 (160 bit) è stato rotto praticamente nel 2017 (SHAttered). Per questo oggi si usano SHA-256 o SHA-3 (256+ bit).

**Implicazione principale:** per garantire $2^k$ bit di sicurezza contro attacchi a collisione, la funzione hash deve avere un output di almeno $2k$ bit. Per 128 bit di sicurezza → output di 256 bit.

#### Parte b — One-wayness di $H(x) = DES_k(x)$ (15 punti)

La funzione $H(x) = DES_k(x)$ usa il cifrario DES come funzione hash: l'input $x$ (di 64 bit) viene cifrato con la chiave $k$ (di 56 bit), producendo un output di 64 bit.

> 📌 DES è una permutazione biiezione su $\{0,1\}^{64}$ per ogni chiave fissa $k$. Questo è fondamentale per tutta l'analisi seguente.

**Parte b.i — $k$ variabile: $H(x)$ è one-way?**

Se $k$ può variare (e non è fissa), la funzione $H(x)$ non è ben definita come funzione hash (dipende anche da $k$). Interpretiamo: l'avversario riceve $h = DES_k(x)$ e vuole trovare $x$ (o qualsiasi preimmagine), senza conoscere $k$.

**Risposta: in generale NO, $H(x)$ non è one-way se $k$ può variare.**

Ragionamento: se $k$ è variabile e nota, allora per trovare $x$ dato $h$, l'avversario calcola semplicemente:

$$x = DES_k^{-1}(h)$$

Poiché DES è un cifrario con decifratura efficiente, e $k$ è nota, l'inversione richiede solo una singola operazione DES. $H$ non è one-way.

Se $k$ è variabile e **non nota** ad Alice (ma cambia ad ogni uso), allora il problema di invertire $H$ diventa il problema di trovare $(x, k)$ tali che $DES_k(x) = h$, che è difficile ma per ragioni diverse dalla one-wayness standard.

**Parte b.ii — $k$ fisso, $k$ nota: $H(x)$ è one-way?**

**Risposta: NO, $H(x) = DES_k(x)$ NON è one-way se $k$ è nota.**

Ragionamento: DES è un algoritmo simmetrico con cifratura e decifratura entrambe efficienti. Se $k$ è nota, l'avversario riceve $h = DES_k(x)$ e calcola:

$$x = DES_k^{-1}(h)$$

in tempo $O(1)$ (una singola operazione di decifratura DES). La preimmagine si trova immediatamente.

> ⚠️ Un cifrario è per definizione invertibile dato $k$: $DES_k^{-1}(DES_k(x)) = x$. Qualsiasi cifrario a blocco usato "direttamente" come funzione hash con la chiave nota non può essere one-way.

**Parte b.iii — $k$ fisso, $k$ non nota: $H(x)$ è one-way?**

**Risposta: in questo caso $H(x) = DES_k(x)$ PUÒ essere considerata one-way** (con alcune riserve).

Ragionamento: se $k$ è fissa e sconosciuta all'avversario, per trovare $x$ dato $h = DES_k(x)$ l'avversario deve:
1. Trovare $x$ direttamente: richiederebbe eseguire una ricerca esaustiva su tutti i $2^{64}$ possibili input $x$ verificando $DES_k(x) = h$. Ma senza conoscere $k$, non può neanche verificare se un tentativo è corretto.
2. Trovare $k$ prima: richiederebbe un attacco brute-force sulla chiave ($2^{56}$ possibilità per DES), poi decifrare. Con hardware moderno, $2^{56}$ è fattibile (DES è stato rotto nel 1998 con il Deep Crack in 22 ore).

**Riserva importante:** la sicurezza è condizionata alla difficoltà di trovare $k$. Con DES, lo spazio delle chiavi ($2^{56}$) è oggi insufficiente. Con 3DES o AES al posto di DES, la proprietà one-way sarebbe più robusta.

> 💡 Questo caso illustra il concetto di "funzione hash costruita da un cifrario": la costruzione Matyas-Meyer-Oseas e Davies-Meyer usano cifrari a blocchi come componenti delle funzioni hash. La sicurezza dipende criticamente dal nascondere la chiave (o dall'uso del messaggio come chiave su dati noti).

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti). Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(n,n)$.
>
> b. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> c. (10 punti) Descrivere l'applicazione dello schema $(2,3)$ considerando $\mathbb{Z}_{19}$ e il segreto $s=3$. (scegliere dei coefficienti casuali)

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema completo di Shamir

### Soluzione dettagliata

#### Parte a — Schema $(n,n)$: distribuzione e ricostruzione (10 punti)

Nello schema $(n,n)$ il segreto $s$ è diviso tra $n$ partecipanti e **tutti** gli $n$ partecipanti sono necessari per ricostruirlo (nessun sottoinsieme più piccolo funziona).

**Fase di distribuzione:**

1. Si sceglie un primo $p > s$ e $p > n$.
2. Si generano $n-1$ valori casuali $r_1, r_2, \ldots, r_{n-1}$ uniformemente in $\mathbb{Z}_p$.
3. Si calcola l'$n$-esima share come:

$$r_n = s \oplus r_1 \oplus r_2 \oplus \ldots \oplus r_{n-1} \pmod{p}$$

(oppure, nella variante additiva: $r_n = s - r_1 - r_2 - \ldots - r_{n-1} \pmod{p}$)

4. Ogni partecipante $P_i$ riceve la share $r_i$ in modo privato.

**Fase di ricostruzione:**

Tutti e $n$ i partecipanti si riuniscono e combinano le loro share:

$$s = r_1 \oplus r_2 \oplus \ldots \oplus r_n \pmod{p}$$

(nella variante additiva: $s = r_1 + r_2 + \ldots + r_n \pmod{p}$)

**Sicurezza:** con $n-1$ share (un partecipante assente), il segreto è completamente nascosto: per qualsiasi valore di $s' \in \mathbb{Z}_p$, esiste esattamente un valore della share mancante che produce $s'$. Il sistema ha sicurezza perfetta (information-theoretic).

> 📌 Lo schema $(n,n)$ è il caso limite del one-time pad distribuito tra $n$ partecipanti.

#### Parte b — Schema $(k,n)$ di Shamir: distribuzione e ricostruzione (10 punti)

(Si veda la soluzione completa in L2, Domanda 4a. Qui sintetizzo per completezza.)

**Distribuzione:** il dealer sceglie un polinomio $f(x)$ di grado $k-1$ su $\mathbb{Z}_p$ con $f(0) = s$:

$$f(x) = s + a_1 x + a_2 x^2 + \ldots + a_{k-1} x^{k-1} \pmod{p}$$

Il partecipante $P_i$ riceve $s_i = f(i) \pmod{p}$.

**Ricostruzione:** qualsiasi $k$ partecipanti usano interpolazione di Lagrange per calcolare $s = f(0)$.

#### Parte c — Applicazione pratica schema $(2,3)$ in $\mathbb{Z}_{19}$, segreto $s=3$ (10 punti)

**Setup:**
- Schema $(2,3)$: $k=2$ (threshold), $n=3$ (partecipanti)
- Campo: $\mathbb{Z}_{19}$ ($p=19$)
- Segreto: $s=3$
- Polinomio di grado $k-1=1$: $f(x) = 3 + a_1 x \pmod{19}$

**Scelta del coefficiente casuale:** sia $a_1 = 5$ (esempio di coefficiente scelto casualmente).

$$f(x) = 3 + 5x \pmod{19}$$

**Calcolo delle share per i 3 partecipanti:**

$$s_1 = f(1) = 3 + 5 \cdot 1 = 8 \pmod{19} = 8$$

$$s_2 = f(2) = 3 + 5 \cdot 2 = 3 + 10 = 13 \pmod{19} = 13$$

$$s_3 = f(3) = 3 + 5 \cdot 3 = 3 + 15 = 18 \pmod{19} = 18$$

**Distribuzione:**
- $P_1$ riceve $(1, 8)$
- $P_2$ riceve $(2, 13)$
- $P_3$ riceve $(3, 18)$

**Ricostruzione con $P_1$ e $P_2$ (usando le share $(1,8)$ e $(2,13)$):**

Con $k=2$:

$$s = f(0) = s_1 \cdot \lambda_1 + s_2 \cdot \lambda_2 \pmod{19}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{19}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 18 \pmod{19}$$

$$s = 8 \cdot 2 + 13 \cdot 18 = 16 + 234 = 250 \pmod{19}$$

$250 = 13 \cdot 19 + 3$, quindi $250 \bmod 19 = 3$ ✓

**Il segreto è $s = 3$** (verificato).

**Ricostruzione alternativa con $P_1$ e $P_3$ (verifica):**

Con punti $(1,8)$ e $(3,18)$:

$$\lambda_1 = \frac{0-3}{1-3} = \frac{-3}{-2} = 3 \cdot 2^{-1} \pmod{19}$$

$2^{-1} \pmod{19}$: $2 \cdot 10 = 20 \equiv 1 \pmod{19}$, quindi $2^{-1} = 10$.

$$\lambda_1 = 3 \cdot 10 = 30 \bmod 19 = 11$$

$$\lambda_3 = \frac{0-1}{3-1} = \frac{-1}{2} = (-1) \cdot 2^{-1} = (-1) \cdot 10 = -10 \equiv 9 \pmod{19}$$

$$s = 8 \cdot 11 + 18 \cdot 9 = 88 + 162 = 250 \pmod{19} = 3 \checkmark$$

---

## Pattern e osservazioni per l'esame

Questo appello (prima sessione estiva) presenta caratteristiche tipiche delle sessioni di luglio:

**Struttura:**
- Equa distribuzione delle domande teoriche e pratiche.
- D1 (Hill) richiede sia teoria che calcolo matriciale: invertire una matrice in $\mathbb{Z}_{26}$ è il punto critico, richiede pratica.
- D2 (Blowfish) è quasi interamente teorica: serve memorizzare le caratteristiche chiave.
- D3 (Hash) è divisa: birthday attack (15 punti teorici puri) + one-wayness DES (15 punti analisi teorica con ragionamento).
- D4 (Secret Sharing) è ampia (30 punti) ma routinaria se si padroneggia Lagrange.

**Consigli pratici:**
- Per Hill: verificare sempre il determinante prima di calcolare $K^{-1}$. Se $\det K = 0$ o $\gcd(\det K, 26) \neq 1$, la chiave non è valida.
- Per il birthday attack: ricordare la formula $O(2^{n/2})$ e spiegare il paradosso del compleanno come analogia.
- Per $DES_k$ come hash: trattare i tre casi separatamente, con argomentazione chiara per ognuno.
- Per lo schema $(n,n)$: non confonderlo con $(k,n)$; specificare che tutti $n$ partecipanti sono richiesti.
