# Hash identita e paradosso del compleanno — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 settembre 2024**, Domanda 3 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 settembre 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L7%20-%20Appello%2020%20settembre%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
