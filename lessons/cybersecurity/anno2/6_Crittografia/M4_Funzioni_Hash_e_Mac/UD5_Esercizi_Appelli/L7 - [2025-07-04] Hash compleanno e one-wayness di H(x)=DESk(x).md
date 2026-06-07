# Hash compleanno e one-wayness di H(x)=DESk(x) — Esercizio d'esame

> 🗂️ **Fonte:** appello del **4 luglio 2025**, Domanda 3 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 4 luglio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L3%20-%20Appello%204%20luglio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
- [`../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L1 - H(x)=DES_k(x) - analisi one-wayness.md`](../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L1%20-%20H%28x%29=DES_k%28x%29%20-%20analisi%20one-wayness.md) — analisi dettagliata

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
