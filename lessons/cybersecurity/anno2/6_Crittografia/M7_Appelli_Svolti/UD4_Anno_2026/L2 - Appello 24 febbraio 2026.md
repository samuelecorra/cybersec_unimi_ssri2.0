# Appello del 24 febbraio 2026 — Soluzione completa

> 📌 **Informazioni appello:** 24/02/2026, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
>
> - [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — sostituzione su blocchi binari
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà di sicurezza hash
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno
> - [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — El Gamal
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir

---

## Domanda 1 — Cifratura classica: sostituzione binaria con XOR (30 punti)

### Traccia originale

> **(30 punti) Cifratura classica.** In un cifrario a sostituzione tuple di simboli sono sostituiti da altre coppie. Per esempio i simboli $\{00; 01; 10; 11\}$ sono sostituiti da $\{01; 10; 00; 11\}$, rispettivamente.
>
> a. (5) Quanti cifrari a sostituzione esistono per parole binarie di lunghezza 4?
>
> b. (10) Se si scrive il cifrario ottenuto in forma tabellare, qual è la dimensione della tabella in bit? Generalizzare la risposta al caso di parole formate da $n$ bit.
>
> c. (15) Se il cifrario opera su un messaggio $m=(i,j,k,l)$ e con una chiave $\mathbf{k}=(k_1,k_2,k_3)$ si ottiene il testo cifrato nel seguente modo $c=(i \oplus k_1,\ j \oplus k_2,\ k \oplus k_1 \oplus k_2,\ l \oplus k_3)$:
>
> i. Quante sostituzioni sono possibili con questo cifrario?
>
> ii. Cifrare il messaggio $M_1 = 1011\ 0111$ con chiave $\mathbf{k}=(0,1,1)$, e decifrare il cifrato ottenuto.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrari su blocchi binari

### Soluzione dettagliata

#### Parte a — Quanti cifrari a sostituzione per parole di 4 bit? (5 punti)

Un **cifrario a sostituzione** su parole di $n$ bit è una biiezione (permutazione) dall'insieme dei $2^n$ possibili plaintext all'insieme dei $2^n$ possibili ciphertext.

Per $n = 4$: ci sono $2^4 = 16$ possibili parole, quindi il numero di cifrari a sostituzione è il numero di permutazioni di 16 elementi:

$$\text{N. cifrari} = 16! \approx 2{,}09 \times 10^{13}$$

#### Parte b — Dimensione della tabella; generalizzazione (10 punti)

Per il cifrario dell'esempio (parole di 2 bit, $n=2$): ci sono $2^2 = 4$ possibili parole in chiaro. La tabella ha 4 righe, e per ogni riga si specifica l'immagine (2 bit). Dimensione totale:

$$4 \text{ voci} \times 2 \text{ bit} = 8 \text{ bit}$$

**Generalizzazione a $n$ bit:**

La tabella ha $2^n$ voci (una per ciascun possibile plaintext), ciascuna di $n$ bit (l'output corrispondente). Dimensione totale:

$$\boxed{n \cdot 2^n \text{ bit}}$$

| $n$ | Voci | Bit per voce | Totale bit |
|-----|------|--------------|------------|
| 2 | 4 | 2 | 8 |
| 4 | 16 | 4 | 64 |
| 8 | 256 | 8 | 2048 |
| 64 | $2^{64}$ | 64 | $64 \cdot 2^{64}$ |

> 💡 Questo spiega perché i cifrari a sostituzione generica su blocchi grandi sono impraticabili: per $n=64$ la chiave richiederebbe $64 \cdot 2^{64} \approx 10^{21}$ bit di storage. AES utilizza invece una struttura algebrica per rappresentare la sostituzione con soli 128/192/256 bit di chiave.

#### Parte c.i — Quante sostituzioni con il cifrario $c=(i \oplus k_1,\ j \oplus k_2,\ k \oplus k_1 \oplus k_2,\ l \oplus k_3)$? (5 punti)

La chiave è $\mathbf{k} = (k_1, k_2, k_3)$ con $k_1, k_2, k_3 \in \{0,1\}$, quindi ci sono $2^3 = 8$ chiavi possibili.

Ogni chiave diversa produce una sostituzione diversa: se $(k_1,k_2,k_3) \neq (k_1',k_2',k_3')$, almeno un bit di chiave differisce, il che cambia la mappatura di almeno un plaintext. Quindi:

$$\text{N. sostituzioni} = 8$$

> ⚠️ Questo cifrario accede a soli 8 delle $16! \approx 2 \times 10^{13}$ possibili sostituzioni su 4 bit. Lo spazio delle chiavi è enormemente ridotto rispetto a un cifrario a sostituzione generale, il che lo rende molto più vulnerabile.

#### Parte c.ii — Cifratura di $M_1 = 1011\ 0111$ con $\mathbf{k}=(0,1,1)$ e decifratura (10 punti)

Il messaggio è composto da due blocchi da 4 bit: $B_1 = 1011$ e $B_2 = 0111$.

**Cifratura di $B_1 = (i,j,k,l) = (1,0,1,1)$ con $(k_1,k_2,k_3) = (0,1,1)$:**

$$c_1 = (1\oplus 0,\ 0\oplus 1,\ 1\oplus 0\oplus 1,\ 1\oplus 1) = (1,1,0,0) = \mathbf{1100}$$

**Cifratura di $B_2 = (i,j,k,l) = (0,1,1,1)$ con $(k_1,k_2,k_3) = (0,1,1)$:**

$$c_2 = (0\oplus 0,\ 1\oplus 1,\ 1\oplus 0\oplus 1,\ 1\oplus 1) = (0,0,0,0) = \mathbf{0000}$$

**Ciphertext:** $C = 1100\ 0000$.

**Decifratura:** dallo schema $c = (i \oplus k_1,\ j \oplus k_2,\ k \oplus k_1 \oplus k_2,\ l \oplus k_3)$, poiché XOR è autocomplementare:

$$i = c_1 \oplus k_1, \quad j = c_2 \oplus k_2, \quad k = c_3 \oplus k_1 \oplus k_2, \quad l = c_4 \oplus k_3$$

Decifratura di $1100$ con $(0,1,1)$:

$$i=1\oplus 0=1,\quad j=1\oplus 1=0,\quad k=0\oplus 0\oplus 1=1,\quad l=0\oplus 1=1 \quad \Rightarrow\quad 1011\ \checkmark$$

Decifratura di $0000$ con $(0,1,1)$:

$$i=0\oplus 0=0,\quad j=0\oplus 1=1,\quad k=0\oplus 0\oplus 1=1,\quad l=0\oplus 1=1 \quad \Rightarrow\quad 0111\ \checkmark$$

---

## Domanda 2 — Funzioni Hash: attacco a compleanno e one-wayness di $H(x)=\text{AES}_k(x)$ (30 punti)

### Traccia originale

> **(30 punti) Funzioni Hash.**
>
> a. (15 punti) Si descriva l'attacco a compleanno e le sue implicazioni per le funzioni hash.
>
> b. (15 punti) Sia $E$ un cifrario simmetrico robusto. Si discuta la proprietà di one-wayness per la funzione hash $H(x) = \text{AES}_k(x)$ per un certo $k$, rispondendo alle seguenti domande:
>
> i. Discutere le caratteristiche della funzione hash così costruita (in termini di dimensione dei bit) e le proprietà di sicurezza considerando due casi:
> - $k$ fisso e conosciuto
> - $k$ fisso e non conosciuto

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà di sicurezza

### Soluzione dettagliata

#### Parte a — Attacco a compleanno e implicazioni (15 punti)

**Il paradosso del compleanno.** In un gruppo di 23 persone, la probabilità che due abbiano lo stesso compleanno supera il 50%. Il motivo è che il numero di **coppie** possibili cresce quadraticamente con il numero di persone: con $n$ persone si formano $\binom{n}{2} \approx n^2/2$ coppie.

**Attacco a compleanno su funzioni hash.** Una funzione hash $H: \{0,1\}^* \to \{0,1\}^m$ produce output da $m$ bit (cioè $2^m$ possibili valori). La **collision resistance** richiede che sia computazionalmente difficile trovare $x \neq x'$ con $H(x) = H(x')$.

Per il **birthday attack**, l'avversario genera messaggi casuali $x_1, x_2, \ldots, x_n$ e calcola i loro hash. La probabilità di trovare una collisione supera il 50% quando:

$$n \approx 1{,}17 \cdot \sqrt{2^m} = 1{,}17 \cdot 2^{m/2}$$

Quindi servono solo $O(2^{m/2})$ operazioni, non $O(2^m)$.

**Implicazioni per le funzioni hash:**

| Output $m$ | Brute force (preimage) | Birthday (collision) |
|---|---|---|
| 64 bit | $2^{64}$ | $2^{32}$ (pratico!) |
| 128 bit | $2^{128}$ | $2^{64}$ (borderline) |
| 256 bit | $2^{256}$ | $2^{128}$ (sicuro) |

> ⚠️ Il birthday attack dimezza la sicurezza contro collisioni. Per avere 128 bit di sicurezza contro collisioni, l'output deve essere almeno 256 bit. Questo spiega perché SHA-256 è il minimo raccomandato oggi, e SHA-1 (160 bit → $2^{80}$ sicurezza contro collisioni) è considerato obsoleto.

**Algoritmo (birthday attack pratico):**
1. Genera $2^{m/2}$ coppie $(x_i, H(x_i))$ e memorizzale in una hash table.
2. Per ogni nuovo $x_j$, calcola $H(x_j)$ e cerca una collisione nella tabella.
3. Con alta probabilità la collisione si trova entro $O(2^{m/2})$ passi.

Tempo: $O(2^{m/2})$, Spazio: $O(2^{m/2})$ (riducibile con l'algoritmo di Pollard's rho a $O(1)$ spazio).

#### Parte b — One-wayness di $H(x) = \text{AES}_k(x)$ (15 punti)

**Caratteristiche della funzione:**

AES (con chiave a 128 bit) è un cifrario a blocchi da 128 bit. La funzione $H(x) = \text{AES}_k(x)$ ha:

- **Dominio:** $\{0,1\}^{128}$ (solo blocchi da 128 bit, non arbitrariamente lunghi).
- **Codominio:** $\{0,1\}^{128}$.
- **Dimensione input = dimensione output**: $H$ non comprime il messaggio, a differenza di una funzione hash crittografica vera (che di solito ha dominio infinito e output fisso di $n$ bit).
- Per chiave $k$ fissa, $\text{AES}_k$ è una **permutazione** (biiezione): ogni output ha esattamente una preimmagine.

> ⚠️ Questa funzione NON soddisfa i requisiti di una funzione hash crittografica moderna perché non comprime (dominio = codominio) e non ammette input di lunghezza arbitraria. Serve come primitiva per costruire hash (es. Davies-Meyer: $H_i = E_{m_i}(H_{i-1}) \oplus H_{i-1}$).

**Caso 1 — $k$ fisso e conosciuto:**

Se $k$ è noto, $\text{AES}_k$ è una permutazione nota con un algoritmo di inversione esplicito e efficiente (AES specifica l'algoritmo di decifratura).

Dato $h = \text{AES}_k(x)$, il crittoanalista calcola immediatamente:

$$x = \text{AES}_k^{-1}(h)$$

in tempo equivalente a una singola operazione AES.

→ $H(x) = \text{AES}_k(x)$ con $k$ **noto NON è one-way**. Invertire è banale.

**Caso 2 — $k$ fisso e non conosciuto:**

Se $k$ è sconosciuto, dato $h = \text{AES}_k(x)$ l'avversario deve trovare $x$ senza conoscere $k$.

Strategia di attacco: ricerca esaustiva su $k$.
- Per ogni candidato $k' \in \{0,1\}^{128}$, calcola $x' = \text{AES}_{k'}^{-1}(h)$.
- Servono $2^{128}$ operazioni nel caso peggiore.
- Senza coppie $(x^*, h^*)$ note, non è possibile verificare quale $k'$ è quello corretto: ogni $k'$ produce un diverso candidato $x'$, tutti ugualmente plausibili.

→ $H(x) = \text{AES}_k(x)$ con $k$ **sconosciuto è computazionalmente one-way** con livello di sicurezza $2^{128}$ (assumendo AES sicuro), che è considerato sicuro con gli standard odierni.

**Riepilogo:**

| Caso | One-way? | Motivazione |
|---|---|---|
| $k$ fisso e noto | ✗ NO | $x = \text{AES}_k^{-1}(h)$ in tempo O(1) |
| $k$ fisso e non noto | ✓ SÌ (comp.) | Ricerca esaustiva su $k$: $2^{128}$ operazioni |

> 💡 Il caso "$k$ sconosciuto" è alla base della costruzione **Davies-Meyer**: la chiave di AES viene usata come blocco del messaggio, rendendo l'inversione equivalente a trovare la chiave, che è difficile per AES sicuro.

---

## Domanda 3 — Algoritmo di El Gamal (20 punti)

### Traccia originale

> **(20 punti) Algoritmo di El Gamal.**
>
> a. (5) Descrivere la procedura di cifratura e decifratura di un messaggio $m$.
>
> b. (15) Si consideri la cifratura nel campo $\mathbb{Z}_{11}$ con generatore $g=6$ e sia $3$ la chiave pubblica di Alice. Cifrare e decifrare il messaggio $4$ per Alice con $k=5$.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — El Gamal completo

### Soluzione dettagliata

#### Parte a — Schema El Gamal: cifratura e decifratura (5 punti)

**Setup:** un primo $p$, un generatore $g$ di $\mathbb{Z}_p^*$, e le chiavi di Alice:
- Chiave privata: $a \in \{1, \ldots, p-2\}$ casuale.
- Chiave pubblica: $y = g^a \bmod p$.

**Cifratura** del messaggio $m \in \mathbb{Z}_p^*$:
1. Il mittente sceglie un nonce $k \in \{1, \ldots, p-2\}$ casuale.
2. Calcola $c_1 = g^k \bmod p$.
3. Calcola $c_2 = m \cdot y^k \bmod p = m \cdot g^{ak} \bmod p$.
4. Invia il ciphertext $(c_1, c_2)$.

**Decifratura** di $(c_1, c_2)$ con chiave privata $a$:
1. Calcola $s = c_1^a \bmod p = g^{ak} \bmod p$.
2. Calcola $m = c_2 \cdot s^{-1} \bmod p = m \cdot g^{ak} \cdot g^{-ak} = m$.

> 📌 La sicurezza si basa sulla difficoltà del problema del logaritmo discreto: $a$ è il logaritmo discreto di $y$ in base $g$ modulo $p$. Senza $a$, calcolare $s = g^{ak}$ da $c_1 = g^k$ e $y = g^a$ richiederebbe risolvere il Diffie-Hellman problem.

#### Parte b — Cifratura in $\mathbb{Z}_{11}$, $g=6$, $y_A=3$, $m=4$, $k=5$ (15 punti)

**Parametri:** $p=11$, $g=6$, chiave pubblica di Alice $y_A = 3$, nonce $k=5$.

**Calcoli preliminari — $g^k \bmod p = 6^5 \bmod 11$:**

$$6^1 = 6,\quad 6^2 = 36 \bmod 11 = 3,\quad 6^3 = 18 \bmod 11 = 7,\quad 6^4 = 42 \bmod 11 = 9,\quad 6^5 = 54 \bmod 11 = 10$$

$$c_1 = 6^5 \bmod 11 = 10$$

**Calcolo di $y_A^k \bmod p = 3^5 \bmod 11$:**

$$3^1=3,\quad 3^2=9,\quad 3^3=27\bmod 11=5,\quad 3^4=15\bmod 11=4,\quad 3^5=12\bmod 11=1$$

$$c_2 = m \cdot y_A^k \bmod p = 4 \cdot 1 \bmod 11 = 4$$

**Ciphertext:** $(c_1, c_2) = (10, 4)$.

**Decifratura:** Alice determina la chiave privata $a$ tale che $g^a = y_A$, cioè $6^a \equiv 3 \pmod{11}$.

Da sopra: $6^2 = 3 \bmod 11$, quindi $a = 2$.

$$s = c_1^a \bmod p = 10^2 \bmod 11 = 100 \bmod 11 = 1$$

$(100 = 9 \cdot 11 + 1)$

$$m = c_2 \cdot s^{-1} \bmod p = 4 \cdot 1^{-1} \bmod 11 = 4 \cdot 1 = 4 \checkmark$$

**Riepilogo:**

| Fase | Calcolo | Risultato |
|---|---|---|
| $c_1 = g^k \bmod p$ | $6^5 \bmod 11$ | $10$ |
| $c_2 = m \cdot y_A^k \bmod p$ | $4 \cdot 3^5 \bmod 11 = 4 \cdot 1$ | $4$ |
| $a$ (chiave privata Alice) | $6^a \equiv 3 \pmod{11}$ | $a=2$ |
| $s = c_1^a \bmod p$ | $10^2 \bmod 11$ | $1$ |
| $m = c_2 / s \bmod p$ | $4 / 1 \bmod 11$ | $4$ ✓ |

> 💡 Il fatto che $3^5 \equiv 1 \pmod{11}$ significa che il nonce $k=5$ produce uno "scudo" $y_A^k = 1$, il che rende $c_2 = m$ (il messaggio appare in chiaro nel ciphertext!). Questo non è un difetto del protocollo ma un caso particolare dovuto alla scelta di $k=5$: $y_A = 3$ ha ordine $5$ in $\mathbb{Z}_{11}^*$, quindi $3^5 = 1$.

---

## Domanda 4 — Secret Sharing (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione nel caso $(k,n)$.
>
> b. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1)=5$ e $s(2)=7$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$ (10 punti)

**Distribuzione:** il dealer sceglie un primo $p > n$ e un segreto $s \in \mathbb{Z}_p$. Costruisce un polinomio casuale di grado $k-1$:

$$f(x) = s + a_1 x + \ldots + a_{k-1} x^{k-1} \pmod{p}$$

con $f(0) = s$ e $a_1, \ldots, a_{k-1}$ casuali. Il partecipante $P_i$ riceve $s_i = f(i) \pmod{p}$.

**Ricostruzione:** qualsiasi $k$ partecipanti $(i_1, s_{i_1}), \ldots, (i_k, s_{i_k})$ usano interpolazione di Lagrange:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1\\l\neq j}}^{k} \frac{0 - i_l}{i_j - i_l} \pmod{p}$$

Con meno di $k$ share, il segreto è perfettamente nascosto (sicurezza alla Shannon).

#### Parte b — Schema $(2,3)$ in $\mathbb{Z}_{11}$, $s(1)=5$, $s(2)=7$ (10 punti)

**Dati:** $p=11$, share $(1,5)$ e $(2,7)$.

**Interpolazione di Lagrange:**

$$f(0) = 5 \cdot \lambda_1 + 7 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 5 \cdot 2 + 7 \cdot 10 = 10 + 70 = 80 \pmod{11}$$

$80 = 7 \cdot 11 + 3$, quindi $80 \bmod 11 = 3$.

**Il segreto è $s = 3$.**

**Verifica:** il polinomio è $f(x) = 3 + a_1 x$. Da $f(1) = 3 + a_1 = 5 \Rightarrow a_1 = 2$. Quindi $f(x) = 3 + 2x$.

- $f(1) = 3 + 2 = 5$ ✓
- $f(2) = 3 + 4 = 7$ ✓
- $f(0) = 3$ ✓

---

## Pattern e osservazioni per l'esame

**Struttura:**

- D1 (30 punti): sostituzione binaria XOR — schema insolito che mescola sostituzione classica con operazioni XOR e chiave ridotta. La parte c.ii è puro calcolo e non può essere sbagliata se si applica la formula.
- D2 (30 punti): hash + one-wayness AES. La parte a (birthday attack) è teoria standard; la parte b richiede di distinguere chiaramente il caso $k$ noto (non one-way: si inverte con AES$^{-1}$) da $k$ sconosciuto (one-way: serve brute-force su $2^{128}$ chiavi).
- D3 (20 punti): El Gamal numerico. Calcolo diretto, nessuna complessità concettuale. Attenzione: $3^5 \bmod 11 = 1$, quindi $c_2 = m$ — il ciphertext della parte dati coincide col plaintext.
- D4 (20 punti): Secret Sharing $(2,3)$ in $\mathbb{Z}_{11}$ — sempre lo stesso procedimento. Con $\lambda_1=2$ e $\lambda_2=10$ fissi per indici 1 e 2, la formula si riduce a $s = 2 s_1 + 10 s_2 \bmod 11$.
