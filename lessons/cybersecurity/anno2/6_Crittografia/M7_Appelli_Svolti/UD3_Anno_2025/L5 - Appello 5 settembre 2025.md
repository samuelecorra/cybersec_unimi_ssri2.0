# Appello del 5 settembre 2025 — Soluzione completa

> 📌 **Informazioni appello:** 05/09/2025, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrari affini
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — DES doppio e attacco meet-in-the-middle
> - [`../../M4_Funzioni_Hash_e_Mac/UD3/L1 - Codici MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L1%20-%20Codici%20MAC.md) — caratteristiche e applicazioni MAC
> - [`../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L2 - MAC personalizzato AES+hash - attacco per collisione.md`](../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L2%20-%20MAC%20personalizzato%20AES+hash%20-%20attacco%20per%20collisione.md) — attacco al MAC AES+hash
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir, variante (n,n)

---

## Domanda 1 — Cifrario affine (25 punti)

### Traccia originale

> **(25 punti) Cifrario affine.**
>
> a. (10 punti) Si descriva il funzionamento dei cifrari affini.
>
> Si consideri un cifrario implementato in $\mathbb{Z}_{26}$:
>
> b. (15 punti) Sapendo che "bgvralav" è il cifrato di "wlestate" recuperare le chiavi usate, illustrando il procedimento.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrario affine: definizione e inversione

### Soluzione dettagliata

#### Parte a — Funzionamento dei cifrari affini (10 punti)

Il cifrario affine è un cifrario a sostituzione monoalfabetica che generalizza il cifrario di Cesare.

**Definizione:**

Ogni lettera dell'alfabeto viene mappata a un numero in $\mathbb{Z}_{26}$ (A=0, B=1, ..., Z=25). La funzione di cifratura è:

$$E(x) = (a \cdot x + b) \bmod 26$$

dove:
- $a \in \mathbb{Z}_{26}$ è il **moltiplicatore** (deve soddisfare $\gcd(a, 26) = 1$ per garantire l'invertibilità)
- $b \in \mathbb{Z}_{26}$ è il **traslatore** (shift)
- $(a, b)$ è la chiave

**Decifratura:** per invertire, si applica l'inversa della funzione affine:

$$D(y) = a^{-1} \cdot (y - b) \bmod 26$$

dove $a^{-1}$ è l'inverso moltiplicativo di $a$ in $\mathbb{Z}_{26}$.

**Condizione di invertibilità:** $\gcd(a, 26) = 1$. I valori di $a$ invertibili in $\mathbb{Z}_{26}$ sono: 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 (12 valori). Con $b \in \{0, \ldots, 25\}$, lo spazio delle chiavi ha $12 \times 26 = 312$ chiavi.

**Casi speciali:**
- $a=1$: si riduce al cifrario di Cesare.
- $b=0$: cifrario di Atbash (se $a=25$).

**Sicurezza:**

Il cifrario affine è vulnerabile sia all'analisi delle frequenze (essendo monoalfabetico) sia alla crittoanalisi algebrica: con due coppie (plaintext, ciphertext) note, si può risolvere un sistema di due equazioni lineari per trovare $(a, b)$.

> ⚠️ Lo spazio di 312 chiavi è troppo piccolo per la sicurezza moderna: si potrebbe fare brute force a mano.

#### Parte b — Recupero delle chiavi: "wlestate" → "bgvralav" (15 punti)

**Codifica delle lettere (A=0, ..., Z=25):**

| Lettera | Codice |
|---------|--------|
| A | 0 |
| B | 1 |
| C | 2 |
| D | 3 |
| E | 4 |
| F | 5 |
| G | 6 |
| L | 11 |
| R | 17 |
| S | 18 |
| T | 19 |
| V | 21 |
| W | 22 |

**Coppie (plaintext, ciphertext):**

| Posizione | Plaintext | Cifrato | $p_i$ | $c_i$ |
|-----------|-----------|---------|-------|-------|
| 1 | w | b | 22 | 1 |
| 2 | l | g | 11 | 6 |
| 3 | e | v | 4 | 21 |
| 4 | s | r | 18 | 17 |
| 5 | t | a | 19 | 0 |
| 6 | a | l | 0 | 11 |
| 7 | t | a | 19 | 0 |
| 8 | e | v | 4 | 21 |

**Sistema di equazioni:**

Dalla funzione affine $c = a \cdot p + b \pmod{26}$, usiamo due coppie per costruire un sistema.

Usiamo la coppia 1 ($p=22, c=1$) e la coppia 2 ($p=11, c=6$):

$$22a + b \equiv 1 \pmod{26} \quad \text{(1)}$$

$$11a + b \equiv 6 \pmod{26} \quad \text{(2)}$$

**Sottrazione (1) - (2):**

$$22a - 11a \equiv 1 - 6 \pmod{26}$$

$$11a \equiv -5 \equiv 21 \pmod{26}$$

Dobbiamo trovare $11^{-1} \pmod{26}$. Usando l'algoritmo di Euclide esteso:

$26 = 2 \cdot 11 + 4$

$11 = 2 \cdot 4 + 3$

$4 = 1 \cdot 3 + 1$

$3 = 3 \cdot 1$

Risalendo: $1 = 4 - 1 \cdot 3 = 4 - 1 \cdot (11 - 2 \cdot 4) = 3 \cdot 4 - 11 = 3(26 - 2 \cdot 11) - 11 = 3 \cdot 26 - 7 \cdot 11$

Quindi $11^{-1} \equiv -7 \equiv 19 \pmod{26}$.

Verifica: $11 \cdot 19 = 209 = 8 \cdot 26 + 1 \equiv 1 \pmod{26}$ ✓

$$a = 11^{-1} \cdot 21 = 19 \cdot 21 \pmod{26}$$

$$19 \cdot 21 = 399 = 15 \cdot 26 + 9$$

$$a = 9$$

**Calcolo di $b$:** dalla equazione (1):

$$22 \cdot 9 + b \equiv 1 \pmod{26}$$

$$198 + b \equiv 1 \pmod{26}$$

$198 = 7 \cdot 26 + 16$, quindi $198 \bmod 26 = 16$.

$$16 + b \equiv 1 \pmod{26}$$

$$b \equiv 1 - 16 \equiv -15 \equiv 11 \pmod{26}$$

**Chiavi trovate: $a = 9$, $b = 11$**

**Funzione di cifratura:** $E(x) = 9x + 11 \pmod{26}$

**Verifica su tutte le coppie:**

| $p$ | $9p + 11 \bmod 26$ | Atteso $c$ |
|-----|-------------------|-----------|
| 22 (w) | $198 + 11 = 209 \bmod 26 = 209 - 8\cdot26 = 209-208 = 1$ | 1 (b) ✓ |
| 11 (l) | $99 + 11 = 110 \bmod 26 = 110 - 4\cdot26 = 6$ | 6 (g) ✓ |
| 4 (e) | $36 + 11 = 47 \bmod 26 = 21$ | 21 (v) ✓ |
| 18 (s) | $162 + 11 = 173 \bmod 26 = 173 - 6\cdot26 = 173-156 = 17$ | 17 (r) ✓ |
| 19 (t) | $171 + 11 = 182 \bmod 26 = 182 - 7\cdot26 = 182-182 = 0$ | 0 (a) ✓ |
| 0 (a) | $0 + 11 = 11$ | 11 (l) ✓ |

Tutte le coppie sono verificate.

> 📌 Con solo 2 coppie note (plaintext, ciphertext), il cifrario affine è completamente rotto. Questo è il known plaintext attack applicato al cifrario affine.

---

## Domanda 2 — Double DES e attacco meet-in-the-middle (20 punti)

### Traccia originale

> **(20 punti) Descrivere ed analizzare il DES doppio.**
>
> a. (10 punti) Descrivere il DES doppio.
>
> b. (10 punti) Illustrare come rompere il DES doppio mediante un attacco di tipo known plaintext e analizzare la complessità (tempo, spazio) dell'attacco.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — Double DES completo

### Soluzione dettagliata

#### Parte a — DES doppio (10 punti)

Il **Double DES** (2DES) è una semplice estensione del DES che applica il cifrario due volte con due chiavi distinte, nel tentativo di aumentare la sicurezza rispetto al DES singolo (che ha una chiave di soli 56 bit, vulnerabile a brute force).

**Schema:**

$$\text{Cifratura: } C = DES_{K_2}(DES_{K_1}(P))$$

$$\text{Decifratura: } P = DES_{K_1}^{-1}(DES_{K_2}^{-1}(C))$$

**Chiave:** la chiave composta è $(K_1, K_2)$ con $K_1, K_2$ ciascuna di 56 bit, per una chiave effettiva totale di **112 bit**.

**Motivazione:** con 112 bit di chiave, ci si aspettava una sicurezza di $2^{112}$ operazioni per il brute force. Invece, come vedremo, la sicurezza effettiva è solo $2^{56}$.

> ⚠️ Il Double DES non è mai stato adottato come standard. Al suo posto si usa il Triple DES (3DES) che ha dimostrato resistere all'attacco meet-in-the-middle in modo più efficace.

#### Parte b — Attacco meet-in-the-middle (10 punti)

L'attacco **meet-in-the-middle** (MITM) fu proposto da Diffie e Hellman nel 1977 per dimostrare l'inefficacia del Double DES.

**Setup dell'attacco:**

L'avversario conosce almeno una coppia $(P, C)$ con $C = DES_{K_2}(DES_{K_1}(P))$.

**Idea chiave:** definiamo il valore intermedio:

$$X = DES_{K_1}(P) = DES_{K_2}^{-1}(C)$$

La stessa quantità $X$ può essere calcolata sia partendo da $P$ (cifrando con $K_1$) sia partendo da $C$ (decifrando con $K_2$). Quindi si cerca la corrispondenza "nel mezzo" ($X$).

**Fasi dell'attacco:**

**Fase 1 — Forward pass (da $P$):**

Per ogni possibile chiave $K \in \{0,1\}^{56}$ ($2^{56}$ chiavi totali):
- Calcola $X_K = DES_K(P)$
- Memorizza la coppia $(X_K, K)$ in una tabella $T$ indicizzata per valore di $X_K$.

Costo: $2^{56}$ cifrature DES, memorizzazione di $2^{56}$ entry da 64+56 bit.

**Fase 2 — Backward pass (da $C$):**

Per ogni possibile chiave $K' \in \{0,1\}^{56}$ ($2^{56}$ chiavi totali):
- Calcola $Y_{K'} = DES_{K'}^{-1}(C)$
- Cerca $Y_{K'}$ nella tabella $T$

Se $Y_{K'} = X_K$ per qualche $K$, allora la coppia $(K, K')$ è un candidato per la chiave $(K_1, K_2)$.

Costo: $2^{56}$ decifrature DES + $2^{56}$ ricerche in tabella (O(1) per ricerca hash).

**Fase 3 — Verifica:**

Per ogni coppia candidata $(K, K')$, si verifica con ulteriori coppie note $(P_i, C_i)$ che $DES_{K'}(DES_K(P_i)) = C_i$. I falsi positivi vengono eliminati.

Il numero atteso di falsi positivi è $2^{112} / 2^{64} = 2^{48}$ (molto alto con una sola coppia). Con 2-3 coppie note, i falsi positivi diventano trascurabili.

**Analisi della complessità:**

| Risorsa | Double DES (atteso naive) | Double DES (attacco MITM) | DES singolo |
|---------|--------------------------|--------------------------|-------------|
| Tempo | $2^{112}$ | $\mathbf{2^{57}}$ (circa $2 \times 2^{56}$) | $2^{56}$ |
| Spazio | $O(1)$ | $\mathbf{2^{56} \times 120\ \text{bit}}$ | $O(1)$ |

> 📌 L'attacco MITM rompe il Double DES con circa $2 \cdot 2^{56} = 2^{57}$ operazioni DES, ovvero con lo stesso costo (a meno di un fattore 2) del brute force su DES singolo. La raddoppiatura della chiave non raddoppia la sicurezza in bit: porta da 56 a 57 bit di sicurezza effettiva.

> ⚠️ Trade-off tempo/spazio: l'attacco richiede $2^{56}$ entry in memoria, circa $2^{56} \times 15\ \text{bytes} \approx 10^{18}$ bytes = $10^9$ GB. Con hardware moderno è impraticabile, ma il punto teorico è che la sicurezza è molto inferiore ai 112 bit attesi.

---

## Domanda 3 — Funzioni MAC (30 punti)

### Traccia originale

> **(30 punti) Funzioni MAC.**
>
> a. (10) Descrivere caratteristiche e applicazioni delle funzioni MAC.
>
> b. (20) Si consideri la seguente funzione MAC basata sul cifrario AES, e sia $h$ una funzione di hash resistente alle collisioni che restituisce un output di lunghezza $n$. Per ogni messaggio $m$ con chiave condivisa $k$, la funzione MAC funziona nel seguente modo:
>
> $$\text{Se } |m| = n \quad MAC_k(m) = AES_k(m)$$
> $$\text{Se } |m| > n \quad MAC_k(m) = AES_k(h(m))$$
>
> i. (5) Definire i parametri di utilizzo del MAC proposto.
>
> ii. (15) Discutere la sicurezza del MAC proposto, dimostrando un possibile attacco (sugg. Si usino due messaggi, $m$ e $h(m)$ come input).

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD3/L1 - Codici MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L1%20-%20Codici%20MAC.md) — definizione e applicazioni MAC
- [`../../M4_Funzioni_Hash_e_Mac/UD3/L2 - Costruzione di MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L2%20-%20Costruzione%20di%20MAC.md) — costruzioni standard
- [`../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L2 - MAC personalizzato AES+hash - attacco per collisione.md`](../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L2%20-%20MAC%20personalizzato%20AES+hash%20-%20attacco%20per%20collisione.md) — attacco per collisione

### Soluzione dettagliata

#### Parte a — Caratteristiche e applicazioni delle funzioni MAC (10 punti)

Un **Message Authentication Code (MAC)** è una stringa di lunghezza fissa generata da un messaggio $m$ e una chiave segreta condivisa $k$:

$$\tau = MAC_k(m)$$

**Caratteristiche:**

1. **Autenticazione dell'origine:** solo chi conosce $k$ può generare un MAC valido. Se il destinatario verifica che $MAC_k(m) = \tau$, è certo che il messaggio proviene da qualcuno che conosce $k$.

2. **Integrità del messaggio:** qualsiasi modifica a $m$ (anche di un solo bit) produce un MAC diverso con alta probabilità. Un attaccante che altera $m$ non può produrre il corrispondente MAC valido senza conoscere $k$.

3. **Differenza da firma digitale:** il MAC è simmetrico (entrambe le parti condividono $k$), non fornisce non-repudiation (il destinatario potrebbe in teoria forgiare un MAC, poiché conosce $k$). La firma digitale è asimmetrica e fornisce non-repudiation.

4. **Non è cifratura:** il MAC non nasconde il contenuto del messaggio. Garantisce integrità e autenticità, non confidenzialità.

**Applicazioni:**

- **TLS/HTTPS:** HMAC viene usato per autenticare i messaggi nel record layer.
- **API security:** le API keys usano HMAC per autenticare le richieste.
- **Integrità dei file:** MAC su file di configurazione critici.
- **Autenticazione di pacchetti di rete** (IPSec, SSH).
- **Banche:** codici di autenticazione delle transazioni.

**Proprietà di sicurezza:**

Il MAC deve resistere alla **forgiatura esistenziale** con attacchi a chosen-message: un avversario che può ottenere MAC di messaggi a sua scelta non deve essere in grado di forgiare un MAC valido per un nuovo messaggio.

> 📌 La sicurezza del MAC è formalizzata nel modello EUF-CMA (Existentially Unforgeble under Chosen Message Attack). HMAC e CMAC sono costruzioni standard con sicurezza dimostrabile.

#### Parte b.i — Parametri del MAC proposto (5 punti)

**Schema:**

$$MAC_k(m) = \begin{cases} AES_k(m) & \text{se } |m| = n \\ AES_k(h(m)) & \text{se } |m| > n \end{cases}$$

**Parametri:**

- $n$: lunghezza del blocco di AES = **128 bit**. I messaggi di esattamente 128 bit vengono cifrati direttamente.
- $h$: funzione hash resistente alle collisioni con output di lunghezza $n = 128$ bit. Può essere SHA-256 troncata a 128 bit, o un'altra funzione con output 128 bit.
- $k$: chiave AES di **128 bit** (o 192 o 256 bit), condivisa tra mittente e destinatario.
- Applicabilità: messaggi di lunghezza $|m| \geq n$. Per $|m| < n$ il MAC non è definito (sarebbe necessario un padding).

**Flusso di utilizzo:**
1. Mittente calcola $\tau = MAC_k(m)$ e trasmette $(m, \tau)$.
2. Destinatario riceve $(m', \tau')$, ricalcola $MAC_k(m')$ e verifica $MAC_k(m') \stackrel{?}{=} \tau'$.

#### Parte b.ii — Analisi di sicurezza e attacco per collisione (15 punti)

**Attacco proposto:**

Scegliamo due messaggi strategici. Sia $m$ un messaggio con $|m| > n$ (quindi si usa $MAC_k(m) = AES_k(h(m))$) tale che $|h(m)| = n$ (per definizione di $h$).

Ora consideriamo il messaggio $m' = h(m)$, che ha lunghezza $|m'| = |h(m)| = n$. Per questo messaggio si usa il primo ramo: $MAC_k(m') = AES_k(m') = AES_k(h(m))$.

**Osservazione chiave:**

$$MAC_k(m) = AES_k(h(m)) = AES_k(m') = MAC_k(h(m))$$

Quindi: $MAC_k(m) = MAC_k(h(m))$, con $m \neq h(m)$ (poiché $|m| > n$ e $|h(m)| = n$, e in generale $m \neq h(m)$).

**Abbiamo trovato una collisione del MAC:** due messaggi diversi ($m$ e $h(m)$) producono lo stesso MAC.

**Implicazioni pratiche dell'attacco:**

Un avversario che ottiene il MAC di $m$ (ad esempio intercettando la trasmissione $(m, \tau)$) può produrre un messaggio diverso $m' = h(m)$ con lo stesso MAC $\tau$, senza conoscere $k$. Inviando $(h(m), \tau)$ al destinatario, il MAC verrà verificato come valido.

**Perché questo è un problema grave:**

- L'attacco è completamente passivo: l'avversario non ha bisogno di conoscere $k$ o di fare query MAC.
- Richiede solo di calcolare $h(m)$, che è pubblicamente calcolabile.
- Viola la proprietà EUF-CMA: l'avversario ha forgiato un MAC valido per un nuovo messaggio ($h(m)$).

> ⚠️ La vulnerabilità nasce dall'incoerenza del MAC: i due rami ($|m|=n$ e $|m|>n$) non sono collegati in modo sicuro. Il ramo per $|m|=n$ è $AES_k(m)$, ma il ramo per $|m|>n$ riduce il messaggio a $h(m)$ che ha esattamente lunghezza $n$, finendo per chiamare implicitamente lo stesso AES sullo stesso input. Un design corretto includerebbe nel calcolo del MAC informazioni sulla lunghezza del messaggio (length padding) per distinguere i due casi.

**Schema sicuro alternativo:**

Usare HMAC: $HMAC_k(m) = H(k \| H(k' \| m))$ con lunghezza $k'$ interna diversa, oppure CMAC (basato su AES in modalità CBC con padding standardizzato) che non ha questa vulnerabilità.

---

## Domanda 4 — Secret Sharing (25 punti)

### Traccia originale

> **(25 punti) Secret Sharing.**
>
> c. (10 punti). Siano dati i seguenti share in uno schema $(4,4)$: $P_1=5,\ P_2=3,\ P_3=4,\ P_4=9$. Ricostruire il segreto, sapendo che si opera in $\mathbb{Z}_{13}$.
>
> d. (15 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$, ricostruire il segreto avendo le due share $P_1=3,\ P_2=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema (n,n) e (k,n)

### Soluzione dettagliata

#### Parte c — Schema $(4,4)$ in $\mathbb{Z}_{13}$: ricostruzione (10 punti)

**Schema $(4,4)$:** tutti e 4 i partecipanti partecipano. Come descritto in L3 (Domanda 4a), nello schema $(n,n)$ le share sono generate con il meccanismo additivo:

$$s = P_1 + P_2 + P_3 + P_4 \pmod{p}$$

(Oppure con XOR: $s = P_1 \oplus P_2 \oplus P_3 \oplus P_4$; usiamo la versione additiva modulo $p$.)

**Calcolo del segreto:**

$$s = P_1 + P_2 + P_3 + P_4 \pmod{13}$$

$$= 5 + 3 + 4 + 9 \pmod{13}$$

$$= 21 \pmod{13}$$

$21 = 1 \cdot 13 + 8$, quindi $21 \bmod 13 = 8$.

**Il segreto è $s = 8$.**

> 📌 Nello schema $(n,n)$ additivo, la ricostruzione è semplicissima: somma di tutte le share modulo $p$. Non serve l'interpolazione di Lagrange.

**Verifica della coerenza:** se il segreto fosse stato distribuito con il metodo $(n,n)$ additivo, la share $P_4$ sarebbe stata calcolata come $P_4 = s - P_1 - P_2 - P_3 \bmod p = 8 - 5 - 3 - 4 \bmod 13 = -4 \bmod 13 = 9$ ✓ (coerente con $P_4 = 9$).

#### Parte d — Schema $(2,3)$ in $\mathbb{Z}_{11}$: ricostruzione con $P_1=3$, $P_2=4$ (15 punti)

**Dati:**
- Schema $(2,3)$: threshold $k=2$, partecipanti $n=3$
- Campo: $\mathbb{Z}_{11}$ ($p=11$)
- Share: $(1, P_1) = (1, 3)$ e $(2, P_2) = (2, 4)$

Con $k=2$, il polinomio è di grado 1: $f(x) = s + a_1 x \pmod{11}$.

**Interpolazione di Lagrange:**

$$f(0) = P_1 \cdot \lambda_1 + P_2 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 3 \cdot 2 + 4 \cdot 10 = 6 + 40 = 46 \pmod{11}$$

$46 = 4 \cdot 11 + 2$, quindi $46 \bmod 11 = 2$.

**Il segreto è $s = 2$.**

> 💡 Stessa coppia di share $(1,3)$ e $(2,4)$ come nell'appello del 10/02/2025 (D4b) e dell'appello del 15/07/2025 (D4c, in $\mathbb{Z}_7$). Il segreto dipende dal campo: in $\mathbb{Z}_{11}$ è 2, in $\mathbb{Z}_7$ è 2, in $\mathbb{Z}_{11}$ del 10/02 è pure 2. Questo perché la retta passante per $(1,3)$ e $(2,4)$ ha equazione $f(x) = 2 + x$ su qualsiasi campo in cui le operazioni siano consistenti.

**Verifica:** $f(x) = 2 + x$. $f(1) = 3$ ✓, $f(2) = 4$ ✓, $f(0) = 2$ ✓.

---

## Pattern e osservazioni per l'esame

Questo appello (prima sessione autunnale) è caratterizzato dall'alta varietà di argomenti:

**Struttura:**
- D1 (25 punti) sul cifrario affine: la parte b è un known plaintext attack — impostare il sistema di 2 equazioni e risolverlo. Il passo critico è calcolare $11^{-1} \bmod 26$.
- D2 (20 punti) su Double DES: è una domanda molto frequente nell'ottimale tra teoria e calcolo. Descrivere MITM con le due fasi, poi analizzare la complessità spazio/tempo.
- D3 (30 punti) su MAC: è la domanda più pesante. L'attacco al MAC AES+hash richiede di identificare la collisione strutturale tra i due rami.
- D4 (25 punti) su Secret Sharing: combina uno schema $(4,4)$ (somma diretta) e uno $(2,3)$ (Lagrange). Il primo è più semplice del secondo.

**Consigli pratici:**
- Per il cifrario affine: memorizzare gli inversi di $\{1,3,5,7,9,11,15,17,19,21,23,25\}$ in $\mathbb{Z}_{26}$ (almeno i più comuni: $3^{-1}=9$, $5^{-1}=21$, $7^{-1}=15$, $9^{-1}=3$, $11^{-1}=19$).
- Per MITM su Double DES: la frase chiave è "stessa complessità del brute force DES singolo" ($2^{57}$ invece di $2^{112}$) + spazio $2^{56}$.
- Per l'attacco MAC: il suggerimento del professore ("si usino due messaggi, $m$ e $h(m)$ come input") è la traccia esatta. Seguirla letteralmente.
- Per lo schema $(4,4)$: non applicare Lagrange! È semplice somma modulare.
