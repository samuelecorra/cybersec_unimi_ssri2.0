# Appello del 7 settembre 2023 — Soluzione completa

> 📌 **Informazioni appello:** 07/09/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (25 + 30 + 15 + 30).
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD3/L1 - Cifrature multilettera e polialfabetica.md`](../../M1_Crittografia_Classica/UD3/L1%20-%20Cifrature%20multilettera%20e%20polialfabetica.md) — cifrario Playfair
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalit%C3%A0%20operative%20del%20DES.md) — modalità OFB
> - [`../../M4_Funzioni_Hash_e_Mac/UD3/L1 - Codici MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L1%20-%20Codici%20MAC.md) — funzioni MAC e HMAC
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — secret sharing

---

## Domanda 1 — Cifratura Playfair (25 punti)

### Traccia originale

> **(25 punti) Cifratura Playfair.**
>
> a. (5) Descrivere brevemente la cifratura Playfair.
>
> b. (10) Discutere i vantaggi rispetto alla cifratura monoalfabetica e le debolezze del sistema Playfair.
>
> c. (10) Usare la parola chiave "welcome back" per cifrare il testo in chiaro "pilotare" e decifrare il testo cifrato "gbtzlthdsn".

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD3/L1 - Cifrature multilettera e polialfabetica.md`](../../M1_Crittografia_Classica/UD3/L1%20-%20Cifrature%20multilettera%20e%20polialfabetica.md) — Playfair e cifrari multilettera

### Soluzione dettagliata

#### Parte a — Descrizione del Playfair (5 punti)

Il **Playfair** è un cifrario a sostituzione **digrafica** (opera su coppie di lettere). La chiave è una parola da cui si costruisce una matrice $5\times5$: si scrivono le lettere della chiave (senza ripetizioni), poi le restanti lettere dell'alfabeto in ordine, fondendo I/J in una cella (25 lettere). Il testo si divide in **bigrammi** (separando con una X le lettere doppie e completando l'ultimo bigramma se necessario) e ogni bigramma si cifra con tre regole:

- **stessa riga** → ciascuna lettera è sostituita da quella alla sua **destra** (ciclico);
- **stessa colonna** → ciascuna è sostituita da quella **sotto** (ciclico);
- **rettangolo** → ciascuna è sostituita dalla lettera sulla **propria riga** ma nella **colonna dell'altra**.

La decifratura usa le regole inverse (sinistra / sopra / stessa regola del rettangolo).

#### Parte b — Vantaggi vs monoalfabetica e debolezze (10 punti)

**Vantaggi rispetto alla monoalfabetica:** cifrando **bigrammi** anziché singole lettere, una stessa lettera è cifrata diversamente a seconda della compagna, quindi **l'analisi delle frequenze sulle singole lettere non funziona**. Lo spazio delle "unità" passa da 26 a $\sim$600 bigrammi, appiattendo molto le statistiche di primo ordine.

**Debolezze:** rimane vulnerabile all'**analisi delle frequenze dei bigrammi** (i bigrammi comuni della lingua lasciano tracce statistiche) con testo sufficiente; è un cifrario **classico deterministico**, quindi cade sotto known-plaintext (bigrammi noti vincolano la griglia); la fusione I/J e la gestione delle doppie introducono ambiguità; chiave e struttura $5\times5$ hanno entropia limitata. Inadatto a usi moderni.

#### Parte c — Cifrare "pilotare" e decifrare "gbtzlthdsn" (10 punti)

**Costruzione della matrice** con chiave "welcome back" (lettere uniche: W,E,L,C,O,M,B,A,K; poi le restanti, I/J fuse):

| | c0 | c1 | c2 | c3 | c4 |
|---|---|---|---|---|---|
| **r0** | W | E | L | C | O |
| **r1** | M | B | A | K | D |
| **r2** | F | G | H | I/J | N |
| **r3** | P | Q | R | S | T |
| **r4** | U | V | X | Y | Z |

**Cifratura di "pilotare"** → bigrammi PI · LO · TA · RE:

- **PI**: P(r3,c0), I(r2,c3) → rettangolo → P→(r3,c3)=**S**, I→(r2,c0)=**F** ⇒ SF
- **LO**: L(r0,c2), O(r0,c4) → stessa riga → L→(r0,c3)=**C**, O→(r0,c0)=**W** ⇒ CW
- **TA**: T(r3,c4), A(r1,c2) → rettangolo → T→(r3,c2)=**R**, A→(r1,c4)=**D** ⇒ RD
- **RE**: R(r3,c2), E(r0,c1) → rettangolo → R→(r3,c1)=**Q**, E→(r0,c2)=**L** ⇒ QL

**Cifrato:** "pilotare" → **SFCWRDQL**.

**Decifratura di "gbtzlthdsn"** → bigrammi GB · TZ · LT · HD · SN (regole inverse):

- **GB**: G(r2,c1), B(r1,c1) → stessa colonna → sopra → G→(r1,c1)=**B**, B→(r0,c1)=**E** ⇒ be
- **TZ**: T(r3,c4), Z(r4,c4) → stessa colonna → sopra → T→(r2,c4)=**N**, Z→(r3,c4)=**T** ⇒ nt
- **LT**: L(r0,c2), T(r3,c4) → rettangolo → L→(r0,c4)=**O**, T→(r3,c2)=**R** ⇒ or
- **HD**: H(r2,c2), D(r1,c4) → rettangolo → H→(r2,c4)=**N**, D→(r1,c2)=**A** ⇒ na
- **SN**: S(r3,c3), N(r2,c4) → rettangolo → S→(r3,c4)=**T**, N→(r2,c3)=**I** ⇒ ti

**Chiaro:** "gbtzlthdsn" → **bentornati** (coerente con il tema "welcome back").

---

## Domanda 2 — Cifratura simmetrica: attacco a una modalità OFB con keystream riusato (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.** Codice a blocchi $E$ usato in modalità $c_t = m_t \oplus w_t$, con $w_0 = IV$ e $w_t = E_K(w_{t-1})$. I cifrati sono archiviati. Il blocco $c_i$ viene **eliminato** e i blocchi $c_{i+1},\ldots,c_n$ vengono decifrati, **ri-cifrati con le stesse variabili** e riarchiviati, ottenendo $c'_i = m_{i+1}\oplus w_i,\; c'_{i+1} = m_{i+2}\oplus w_{i+1},\;\ldots,\; c'_{n-1} = m_n \oplus w_{n-1}$. Dimostrare che se $m_i$ è noto, oltre al file originale e a quello aggiornato, allora per ogni $t\ge i$ si deducono tutte le $w_t$ e tutti gli $m_t$. Suggerire infine quando usare o non usare questa modalità.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalit%C3%A0%20operative%20del%20DES.md) — modalità OFB e riuso del keystream

### Soluzione dettagliata

La modalità descritta è **OFB (Output Feedback)**: il keystream $w_t = E_K(w_{t-1})$ è **indipendente dal plaintext** e $c_t = m_t \oplus w_t$. Il punto chiave è che la ri-archiviazione **riusa lo stesso keystream** $w_t$ (stesso IV/chiave): ogni $w_t$ maschera così **due** plaintext diversi (problema del **two-time-pad**).

**Dati noti all'avversario:** $m_i$, il file originale $\{c_t = m_t \oplus w_t\}_{t}$, il file aggiornato $\{c'_t = m_{t+1} \oplus w_t\}_{t\ge i}$.

**Dimostrazione per induzione su $t \ge i$.**

*Base* ($t = i$): da $c_i = m_i \oplus w_i$ con $m_i$ e $c_i$ noti,
$$w_i = c_i \oplus m_i.$$

*Passo induttivo*: supponiamo nota $w_t$ (con $t \ge i$). Allora:
1. dal file **aggiornato**, $c'_t = m_{t+1} \oplus w_t \Rightarrow m_{t+1} = c'_t \oplus w_t$ (recupero il plaintext successivo);
2. dal file **originale**, $c_{t+1} = m_{t+1} \oplus w_{t+1} \Rightarrow w_{t+1} = c_{t+1} \oplus m_{t+1}$ (recupero la keystream successiva).

Quindi da $w_i$ si ricavano in cascata $m_{i+1}, w_{i+1}, m_{i+2}, w_{i+2}, \ldots$: **tutte** le $w_t$ e **tutti** gli $m_t$ per $t \ge i$ (oltre a $m_i$ già noto). $\blacksquare$

> 📌 Equivalentemente, "allineando" i due file: $c_t \oplus c'_t = (m_t \oplus w_t)\oplus(m_{t+1}\oplus w_t) = m_t \oplus m_{t+1}$. Il keystream si cancella e resta lo XOR di plaintext adiacenti: noto **un** plaintext ($m_i$), tutta la catena si srotola. È esattamente la rottura del two-time-pad.

**Indicazioni d'uso.** Questa modalità (OFB/stream) è sicura **solo** se ogni cifratura usa una **coppia (chiave, IV) unica e mai riutilizzata**: il keystream non va mai riusato per cifrare dati diversi. Quindi:

- **Non usarla** quando i dati vengono **modificati/ri-archiviati riusando lo stesso IV/keystream** (come nello scenario): qualsiasi edit con keystream riusato è catastrofico.
- **Usarla** solo per cifrare un flusso *una volta sola* con IV fresco; per ogni nuova cifratura (anche di una versione modificata) generare un **nuovo IV**. In alternativa usare modalità progettate per il riuso controllato (es. CTR con nonce unico) o, se serve integrità/editabilità, schemi autenticati con nonce gestiti correttamente.

---

## Domanda 3 — Funzioni MAC e HMAC (15 punti)

### Traccia originale

> **(15 punti) Funzioni MAC.** Descrivere caratteristiche e applicazioni delle funzioni MAC, descrivendo HMAC.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD3/L1 - Codici MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L1%20-%20Codici%20MAC.md) — MAC
- [`../../M4_Funzioni_Hash_e_Mac/UD3/L3 - HMAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L3%20-%20HMAC.md) — HMAC

### Soluzione dettagliata

**Caratteristiche.** Un **MAC** è una funzione $\text{MAC}_k(m)$ che, con una **chiave segreta condivisa** $k$, produce un **tag** di lunghezza fissa che garantisce **autenticazione dell'origine** e **integrità**: il destinatario, che conosce $k$, ricalcola il tag e lo confronta. È **simmetrico** (a differenza delle firme digitali) e non offre **non-ripudio** (entrambe le parti conoscono $k$). Requisito di sicurezza: **unforgeability** (EUF-CMA) — un avversario che vede coppie $(m_i, \text{MAC}_k(m_i))$ non deve poter produrre un tag valido per un messaggio nuovo.

**Applicazioni:** autenticazione di messaggi in protocolli di rete (IPsec, TLS), integrità di cookie/token, cifratura autenticata (encrypt-then-MAC), challenge-response.

**HMAC.** Costruzione standard di MAC a partire da una funzione hash $H$ (SHA-256, …):
$$\text{HMAC}(K, m) = H\big((K' \oplus \text{opad}) \,\|\, H((K' \oplus \text{ipad}) \,\|\, m)\big)$$
dove $K'$ è la chiave adattata alla dimensione del blocco e `ipad`/`opad` sono costanti di padding. La **doppia applicazione** di $H$ con la chiave mescolata prima (ipad) e dopo (opad) rende HMAC **resistente agli attacchi di length-extension** che affliggono il MAC naïf $H(K \| m)$, ed è dimostrabilmente sicuro se la funzione di compressione è una PRF.

> 💡 HMAC è preferito al "prefix-MAC" $H(K\|m)$ proprio perché quest'ultimo, con hash di tipo Merkle-Damgård, è vulnerabile al length-extension.

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (10 punti) Per uno schema $(3,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto, con $s_1=6, s_2=4, s_3=2$.
>
> c. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto, con $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — interpolazione di Lagrange in $\mathbb{Z}_p$
- [`../../M6_Applicazioni_Crittografiche/UD3/L1 - Scenari e applicazioni.md`](../../M6_Applicazioni_Crittografiche/UD3/L1%20-%20Scenari%20e%20applicazioni.md) — scenari

### Soluzione dettagliata

#### Parte a — Distribuzione e ricostruzione $(k,n)$ (10 punti)

Schema soglia $(k,n)$ di **Shamir**: il segreto $S$ è diviso in $n$ share; **qualsiasi $k$** share ricostruiscono $S$, mentre $k-1$ o meno non rivelano nulla (sicurezza perfetta).

**Distribuzione (dealer):** in $\mathbb{Z}_p$ ($p$ primo, $p>n$, $p>S$) pone $a_0 = S$, sceglie casualmente $a_1,\ldots,a_{k-1}$ e definisce $f(x) = a_0 + a_1 x + \cdots + a_{k-1}x^{k-1} \bmod p$; calcola le share $s_i = f(i)$ per $i=1,\ldots,n$ e le distribuisce.

**Ricostruzione:** dati $k$ punti $(i, s_i)$, il polinomio di grado $k-1$ è univocamente determinato; si recupera $S = f(0)$ con l'**interpolazione di Lagrange**:
$$S = \sum_{j=1}^{k} s_{i_j} \prod_{l \neq j} \frac{0 - i_l}{i_j - i_l} \bmod p$$

> 📌 Il caso $(n,n)$ è il sottocaso **additivo**: $S = (s_1 + \cdots + s_n)\bmod p$.

#### Parte b — Schema $(3,3)$ in $\mathbb{Z}_{11}$ (10 punti)

Con $k=n=3$ è uno schema $(n,n)$ **additivo**: $S = s_1+s_2+s_3 \bmod 11 = 6+4+2 = 12 \equiv 1$.

**Segreto:** $\boxed{S = 1}$. Verifica: $s_3 = S - s_1 - s_2 = 1 - 6 - 4 = -9 \equiv 2 \pmod{11}$ ✓.

#### Parte c — Schema $(2,3)$ in $\mathbb{Z}_{11}$ (10 punti)

Polinomio di grado 1: $f(x) = a_0 + a_1 x$, $a_0 = S$. Punti $(1,3)$, $(2,4)$.

**Lagrange in $x=0$:** $L_1(0) = \frac{0-2}{1-2} = 2$, $L_2(0) = \frac{0-1}{2-1} = -1 \equiv 10$. Quindi
$$S = 3\cdot2 + 4\cdot10 = 6 + 40 = 46 \equiv 2 \pmod{11}$$

**Sistema lineare (verifica):** $a_0+a_1=3$, $a_0+2a_1=4$ → $a_1=1$, $a_0=2$, cioè $f(x)=2+x$, $S=f(0)=2$. Controllo: $f(1)=3$ ✓, $f(2)=4$ ✓.

**Segreto:** $\boxed{S = 2}$.

---

## Pattern e osservazioni per l'esame

Appello della sessione autunnale (settembre 2023). Lo **stesso testo è ricomparso nell'appello dell'08/09/2023** (vedi L7).

- **D1 (25):** Playfair operativo. Costruire bene la matrice $5\times5$ (chiave senza ripetizioni, I/J fuse), poi applicare le 3 regole. Qui "pilotare" → **SFCWRDQL** e "gbtzlthdsn" → **bentornati**.
- **D2 (30):** rottura di OFB con **keystream riusato**. La chiave della dimostrazione: $c_t \oplus c'_t = m_t \oplus m_{t+1}$ (two-time-pad); per induzione da $w_i = c_i \oplus m_i$ si srotola tutto. Mai riusare IV/keystream.
- **D3 (15):** MAC + HMAC (formula con ipad/opad, resistenza al length-extension). Descrittiva.
- **D4 (30):** secret sharing — $(3,3)$ additivo ($S=1$) e $(2,3)$ con Lagrange ($S=2$). Identico ad altri appelli (cfr. 24/01/2023, 08/07/2024).
