# Appello del 24 gennaio 2023 — Soluzione completa

> 📌 **Informazioni appello:** 24/01/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (25 + 30 + 15 + 30).
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — modelli di attacco (COA/KPA/CPA/CCA)
> - [`../../M4_Funzioni_Hash_e_Mac/UD3/L1 - Codici MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L1%20-%20Codici%20MAC.md) — proprietà di sicurezza e tipi di forgery dei MAC
> - [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) — generazione e verifica della firma DSS
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — distribuzione e ricostruzione del segreto

---

## Domanda 1 — Cifrari classici: classificazione attacchi e resistenza (25 punti)

### Traccia originale

> **(25 punti) Cifrari classici.**
>
> a. (5 punti) Dare una classificazione dei possibili attacchi in base alla conoscenza dell'avversario.
>
> b. (20 punti) Secondo la classificazione precedente dire a quali tipi di attacchi resistono i seguenti cifrari, motivando la risposta con un esempio:
>
> i. Cifrario a sostituzione
>
> ii. Cifrario Playfair
>
> iii. Cifrario Hill
>
> iv. DES
>
> v. RSA

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — modelli di attacco
- [`../../M1_Crittografia_Classica/UD2/L5 - Crittoanalisi dei cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L5%20-%20Crittoanalisi%20dei%20cifrari%20a%20sostituzione.md) — attacchi ai cifrari classici

### Soluzione dettagliata

#### Parte a — Classificazione degli attacchi (5 punti)

La classificazione standard degli attacchi crittografici si basa sul **tipo di accesso** che l'avversario ha ai dati, in ordine crescente di potenza:

**1. Ciphertext-Only Attack (COA):**

L'avversario conosce solo uno o più testi cifrati $C_1, C_2, \ldots$ e nessun testo in chiaro corrispondente. Deve determinare il testo in chiaro o la chiave.

_Esempio:_ analisi delle frequenze su testo cifrato monoalfabetico.

**2. Known Plaintext Attack (KPA):**

L'avversario conosce alcune coppie $(P_i, C_i)$ con $C_i = E_k(P_i)$, tutte cifrate con la stessa chiave, e le usa per determinare $k$ o decifrare altri testi.

_Esempio:_ i "crib" alleati su Enigma nella Seconda Guerra Mondiale (porzioni note del formato dei messaggi).

**3. Chosen Plaintext Attack (CPA):**

L'avversario può scegliere messaggi in chiaro arbitrari e ottenerne i cifrati (oracolo di cifratura). Più potente del KPA perché testa ipotesi mirate.

_Esempio:_ attacchi adattivi su blocchi in modalità ECB.

**4. Chosen Ciphertext Attack (CCA):**

L'avversario può scegliere testi cifrati arbitrari e ottenerne la decifratura (oracolo di decifratura). È il modello più forte.

_Esempio:_ padding oracle attack (POODLE, BEAST).

> 📌 Gerarchia di potenza: $\text{CCA} > \text{CPA} > \text{KPA} > \text{COA}$. Un cifrario sicuro contro CCA lo è anche contro tutti i modelli più deboli. La sicurezza semantica moderna richiede resistenza almeno a CPA.

#### Parte b — Resistenza agli attacchi dei vari cifrari (20 punti)

**i. Cifrario a sostituzione monoalfabetica:**

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | ✗ NO | L'analisi delle frequenze identifica le lettere più comuni e ricostruisce la tabella di sostituzione. Con ~50 lettere di testo il cifrario è rotto. |
| KPA | ✗ NO | Ogni coppia nota rivela un'entry della tabella; con 26 coppie la chiave è completa. |
| CPA | ✗ NO | È deterministico: cifrare le 26 lettere rivela l'intera tabella. |

**ii. Cifrario Playfair:**

Cifra **bigrammi** tramite una matrice $5\times5$ derivata dalla chiave.

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | Parziale ✗ | Resiste all'analisi delle frequenze sui singoli caratteri, ma è vulnerabile all'analisi delle frequenze dei **bigrammi** cifrati. Con qualche centinaio di lettere è rotto. |
| KPA | ✗ NO | Ogni bigramma noto vincola la matrice; abbastanza coppie la determinano completamente. |
| CPA | ✗ NO | Cifrare bigrammi scelti rivela la struttura della matrice. |

**iii. Cifrario Hill:**

Cifra blocchi di $m$ lettere come $\mathbf{c} = K\mathbf{p} \bmod 26$, con $K$ matrice $m\times m$ invertibile.

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | Parziale ✗ | Essendo poligrammico nasconde le frequenze delle singole lettere, ma l'analisi statistica dei blocchi può rivelare struttura con testo sufficiente. |
| KPA | ✗ NO | Con $m$ coppie (blocco chiaro, blocco cifrato) linearmente indipendenti si risolve $K = C\,P^{-1} \pmod{26}$. Per $m=2$ bastano 2 coppie. |
| CPA | ✗ NO | Scegliendo come plaintext i vettori della base standard $e_1,\dots,e_m$ si ottengono direttamente le colonne di $K$: chiave trovata in $m$ query. |

**iv. DES:**

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | ✓ SÌ (con riserve) | Chiave a 56 bit: brute force in $2^{55}$ in media. Rotto nel 1998 (Deep Crack, 22 ore). Nessun attacco statistico pratico in solo COA. |
| KPA | ✓ SÌ (con riserve) | La crittoanalisi lineare richiede $\approx 2^{43}$ coppie note: teoricamente rompibile, impraticabile all'epoca. |
| CPA | ✓ SÌ (teoricamente) | La crittoanalisi differenziale richiede $\approx 2^{47}$ testi scelti: troppi per essere pratici. |
| CCA | ✓ SÌ | Nessun attacco CCA pratico noto. |

_Nota:_ il limite reale di DES è la chiave a 56 bit (brute force oggi fattibile), non un difetto strutturale. È comunque obsoleto.

**v. RSA:**

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | ✓ SÌ | La sicurezza poggia sulla difficoltà di fattorizzare $n=pq$: per $n$ di 2048 bit non si conoscono algoritmi classici polinomiali. |
| KPA | ✓ SÌ (con padding) | Il textbook RSA deterministico è vulnerabile ad attacco a dizionario su spazi di messaggi piccoli; con OAEP RSA resiste. |
| CPA | ✓ SÌ (con OAEP) | Textbook RSA è deterministico → vulnerabile a CPA; RSA-OAEP è semanticamente sicuro. |
| CCA | ✓ SÌ (con OAEP) | Textbook RSA è malleabile (omomorfismo moltiplicativo): $E(m_1)E(m_2)=E(m_1 m_2)$. OAEP elimina la vulnerabilità. |

> ⚠️ Vulnerabilità CCA del textbook RSA: dato $C=M^e \bmod n$, l'avversario sceglie $r$, calcola $C'=r^e C \bmod n=(rM)^e$, ottiene dall'oracolo $M'=C'^d=rM$ e recupera $M = M' r^{-1} \bmod n$.

---

## Domanda 2 — Funzioni MAC: schema AES/DES + hash e attacco (30 punti)

### Traccia originale

> **(30 punti) Funzioni MAC.**
>
> a. (10) Descrivere caratteristiche e applicazioni delle funzioni MAC.
>
> b. (20) Si consideri la seguente funzione MAC basata sul cifrario simmetrico AES, e sia $h$ una funzione di hash resistente alle collisioni che restituisce un output di lunghezza $n$. Per ogni messaggio $m$ con chiave condivisa $k$, la funzione MAC funziona nel seguente modo:
> $$\text{MAC}_k(m) = \begin{cases} \text{AES}_k(m) & \text{se } |m| = n \\ \text{AES}_k(h(m)) & \text{se } |m| > n \end{cases}$$
>
> i. (5) Definire i parametri di utilizzo del MAC proposto, scegliendo DES come cifrario simmetrico ed una funzione hash.
>
> ii. (15) Discutere la sicurezza del MAC proposto, dimostrando un possibile attacco (sugg. si usino due messaggi, $m$ e $h(m)$ come input).

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD3/L1 - Codici MAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L1%20-%20Codici%20MAC.md) — proprietà, tipi di attacco e di forgery
- [`../../M4_Funzioni_Hash_e_Mac/UD3/L3 - HMAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L3%20-%20HMAC.md) — costruzione MAC sicura
- [`../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L2 - MAC personalizzato AES+hash - attacco per collisione.md`](../../M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L2%20-%20MAC%20personalizzato%20AES+hash%20-%20attacco%20per%20collisione.md) — questo identico schema d'esame

### Soluzione dettagliata

#### Parte a — Caratteristiche e applicazioni delle funzioni MAC (10 punti)

Un **MAC (Message Authentication Code)** è una funzione $\text{MAC}_k: \{0,1\}^* \to \{0,1\}^t$ parametrizzata da una **chiave segreta condivisa** $k$, che associa a ogni messaggio $m$ un **tag** di autenticazione $t = \text{MAC}_k(m)$.

**Caratteristiche:**

- **Autenticazione dell'origine + integrità:** il destinatario, che condivide $k$, ricalcola il tag e lo confronta con quello ricevuto; se coincidono il messaggio è autentico e integro.
- **Chiave simmetrica:** mittente e destinatario condividono la stessa $k$ (a differenza delle firme digitali, asimmetriche).
- **Niente non-ripudio:** poiché entrambe le parti conoscono $k$, un MAC valido non prova *quale* delle due ha generato il messaggio (al contrario di una firma digitale).
- **Output di lunghezza fissa** e calcolo efficiente.

**Proprietà di sicurezza:** deve essere computazionalmente **unforgeable**, cioè un avversario che osserva coppie $(m_i, \text{MAC}_k(m_i))$ non deve riuscire a produrre una coppia $(m^*, t^*)$ valida per un messaggio $m^*$ mai autenticato (**existential unforgeability under chosen message attack**, EUF-CMA).

**Applicazioni:** autenticazione di messaggi nei protocolli di rete (IPsec, TLS con HMAC), integrità di file/cookie, costruzioni di cifratura autenticata (encrypt-then-MAC), challenge-response.

#### Parte b.i — Parametri dello schema con DES (5 punti)

Scegliendo **DES** come cifrario simmetrico, il blocco ha dimensione $n = 64$ bit, quindi i parametri sono:

- **Chiave:** $k$ chiave DES da **56 bit** (segreta, condivisa).
- **Cifrario:** $\text{DES}_k(\cdot)$, permutazione su blocchi da $n = 64$ bit.
- **Funzione hash:** $h$ pubblica e nota, resistente alle collisioni, con output troncato/dimensionato a **esattamente $n = 64$ bit** (così $h(m)$ è un blocco DES valido). Es. SHA-256 troncata a 64 bit.
- **Input:** messaggio $m$ di lunghezza arbitraria ($\ge 64$ bit).
- **Output:** tag di **64 bit**.

Lo schema diventa quindi:
$$\text{MAC}_k(m) = \begin{cases} \text{DES}_k(m) & \text{se } |m| = 64 \\ \text{DES}_k(h(m)) & \text{se } |m| > 64 \end{cases}$$

#### Parte b.ii — Sicurezza e attacco (15 punti)

**Osservazione chiave (collisione strutturale):** per ogni messaggio lungo $m$ con $|m| > n$, il digest $h(m)$ ha esattamente $n$ bit, quindi $h(m)$ è un **messaggio corto valido** per lo schema. Valutando i due casi:

$$\text{MAC}_k(m) = \text{DES}_k(h(m)) \quad (\text{perché } |m| > n)$$
$$\text{MAC}_k(h(m)) = \text{DES}_k(h(m)) \quad (\text{perché } |h(m)| = n)$$

Da cui l'identità:

$$\boxed{\text{MAC}_k(m) = \text{MAC}_k(h(m)) \quad \text{per ogni } m \text{ con } |m| > n}$$

**Attacco (existential forgery under CMA, una sola query):**

1. L'avversario sceglie un messaggio lungo $m$ qualsiasi ($|m| > 64$) e chiede all'oracolo il tag $t = \text{MAC}_k(m) = \text{DES}_k(h(m))$.
2. Calcola autonomamente $m' = h(m)$ ($h$ è pubblica, $|m'| = 64$ bit) — nessuna query aggiuntiva.
3. Presenta la coppia $(m', t)$ come messaggio autentico.

**Verifica del successo:** il verificatore controlla $\text{MAC}_k(m') \stackrel{?}{=} t$. Poiché $|m'| = 64$ si usa il primo caso:
$$\text{MAC}_k(m') = \text{DES}_k(m') = \text{DES}_k(h(m)) = t \;\checkmark$$

L'avversario ha prodotto un tag valido per $m' = h(m)$, **mai presentato** all'oracolo, con una sola query e una valutazione di $h$. Lo schema **non è sicuro**.

> ⚠️ **Causa radice:** lo schema ha due comportamenti distinti per lunghezze diverse, e l'output di $h$ (64 bit) ricade esattamente nella categoria "messaggio corto", creando un percorso di calcolo sovrapposto. Il suggerimento della traccia ("usare $m$ e $h(m)$") indica proprio questa collisione.

> 💡 **HMAC** evita il problema: $\text{HMAC}(K,M)=H\big((K'\oplus\text{opad}) \,\|\, H((K'\oplus\text{ipad}) \,\|\, M)\big)$. La chiave è mescolata con `ipad`/`opad` e non esiste un percorso alternativo per lunghezze diverse, quindi $m$ e $h(m)$ producono sempre tag diversi.

> ⚠️ Nota aggiuntiva su DES: indipendentemente dalla collisione, l'uso di DES introduce anche la debolezza della chiave a 56 bit (tag a 64 bit attaccabile per forza bruta sulla chiave); ma il difetto **fatale e immediato** dello schema è la collisione strutturale sopra, valida con una singola query a prescindere dal cifrario.

---

## Domanda 3 — Firma DSS (15 punti)

### Traccia originale

> **(15 punti) Firma DSS.**
>
> a. (10) Descrivere le fasi di generazione e verifica della firma DSS.
>
> b. (5) Cosa succede se viene firmato lo stesso messaggio in due diverse occasioni? Qual è la differenza con RSA?

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) — schema DSS completo
- [`../../M5_Firme_Digitali/UD3/L3 - Sicurezza dello schema DSS.md`](../../M5_Firme_Digitali/UD3/L3%20-%20Sicurezza%20dello%20schema%20DSS.md) — riutilizzo del nonce

### Soluzione dettagliata

#### Parte a — Generazione e verifica della firma DSS (10 punti)

Il **Digital Signature Standard (DSS)**, basato sull'algoritmo **DSA**, è lo standard NIST (FIPS 186) per le firme digitali, fondato sulla difficoltà del **logaritmo discreto**.

**Parametri di sistema (pubblici):**

- $p$: primo grande di $L$ bit ($L = 1024, 2048, 3072$).
- $q$: primo di 160/256 bit con $q \mid (p-1)$.
- $g$: generatore del sottogruppo di ordine $q$, $g = h^{(p-1)/q} \bmod p$ per qualche $h>1$ con $g>1$.
- $H$: funzione hash crittografica (SHA-256 o superiore).

**Chiavi dell'utente:**

- **Privata:** $x \in \{1, \ldots, q-1\}$ casuale.
- **Pubblica:** $y = g^x \bmod p$.

**Fase di firma del messaggio $M$:**

1. Sceglie un **nonce segreto** $k \in \{1, \ldots, q-1\}$ **casuale e unico** per ogni firma.
2. $r = (g^k \bmod p) \bmod q$; se $r=0$ riparte con nuovo $k$.
3. $s = k^{-1}\big(H(M) + x\,r\big) \bmod q$; se $s=0$ riparte.
4. La firma è la coppia $(r, s)$.

**Fase di verifica** (con chiave pubblica $y$, messaggio $M$, firma $(r,s)$):

1. Controlla $0 < r < q$ e $0 < s < q$ (altrimenti rifiuta).
2. $w = s^{-1} \bmod q$.
3. $u_1 = H(M)\,w \bmod q$.
4. $u_2 = r\,w \bmod q$.
5. $v = (g^{u_1} y^{u_2} \bmod p) \bmod q$.
6. **Accetta** se e solo se $v = r$.

**Correttezza:** da $s = k^{-1}(H(M)+xr)$ segue $k = w(H(M)+xr) = u_1 + x\,u_2 \pmod q$, quindi
$$v = (g^{u_1} y^{u_2} \bmod p)\bmod q = (g^{u_1 + x u_2}\bmod p)\bmod q = (g^{k}\bmod p)\bmod q = r \;\checkmark$$

> 📌 Se $k$ è noto, la chiave privata si ricava subito: $x = r^{-1}(s\,k - H(M)) \bmod q$. Per questo $k$ va tenuto segreto e cambiato ad ogni firma.

#### Parte b — Stesso messaggio firmato due volte; confronto con RSA (5 punti)

**Con nonce $k$ diverso (uso corretto):** poiché $r=(g^k \bmod p)\bmod q$ dipende da $k$, due firme dello stesso $M$ con $k$ diversi danno $(r_1,s_1) \ne (r_2,s_2)$: **firme diverse, entrambe valide**, senza rivelare nulla sulla chiave. DSS è quindi **probabilistico**.

**Con stesso nonce $k$ (errore critico):** se $k$ viene riusato (anche per messaggi diversi $M, M'$) si ottiene $r=r'$, e l'avversario calcola
$$k = (s - s')^{-1}\big(H(M) - H(M')\big) \bmod q, \qquad x = r^{-1}(s\,k - H(M)) \bmod q$$
recuperando la **chiave privata**. 

> ⚠️ Attacco reale: la chiave di firma del firmware della **PlayStation 3** (2010) fu estratta perché Sony usava un $k$ costante.

**Differenza con RSA:** il **textbook RSA** è **deterministico** — firmare lo stesso messaggio due volte produce **sempre la stessa firma** ($\sigma = M^d \bmod n$), senza nonce. DSS invece è **probabilistico** (dipende da $k$): stesse firme solo se si riusa $k$ (cosa da evitare). 

| Proprietà | DSS/DSA | RSA |
| --------- | ------- | --- |
| Determinismo | No (dipende dal nonce $k$) | Sì (textbook); RSA-PSS è randomizzato |
| Stesso $M$ due volte | Firme diverse (se $k$ diverso) | Stessa firma |
| Riutilizzo nonce | Critico: rivela $x$ | N/A (nessun nonce) |
| Sicurezza basata su | Logaritmo discreto | Fattorizzazione |

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (10 punti) Per uno schema $(3,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che le share in possesso di Alice sono $s_1=6$, $s_2=4$, $s_3=2$.
>
> c. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir e interpolazione di Lagrange in $\mathbb{Z}_p$
- [`../../M6_Applicazioni_Crittografiche/UD3/L1 - Scenari e applicazioni.md`](../../M6_Applicazioni_Crittografiche/UD3/L1%20-%20Scenari%20e%20applicazioni.md) — scenari del secret sharing

### Soluzione dettagliata

#### Parte a — Distribuzione e ricostruzione in uno schema $(k,n)$ (10 punti)

In uno **schema soglia $(k,n)$** un segreto $S$ è diviso in $n$ share distribuite a $n$ partecipanti, in modo che:

- **qualsiasi** $k$ share permettano di ricostruire $S$;
- **qualsiasi** $k-1$ (o meno) share non rivelino **alcuna** informazione su $S$.

**Schema di Shamir — Distribuzione (dealer):**

1. Si lavora nel campo finito $\mathbb{Z}_p$ con $p$ primo, $p > n$ e $p > S$.
2. Il dealer pone $a_0 = S$ e sceglie casualmente i coefficienti $a_1, \ldots, a_{k-1} \in \mathbb{Z}_p$, definendo il **polinomio di grado $k-1$**:
$$f(x) = a_0 + a_1 x + a_2 x^2 + \cdots + a_{k-1}x^{k-1} \bmod p$$
3. Calcola le $n$ share come valutazioni del polinomio in punti distinti non nulli: $s_i = f(i) \bmod p$, per $i = 1, \ldots, n$.
4. Distribuisce in modo sicuro la share $s_i$ all'$i$-esimo partecipante; il polinomio (e quindi $S=f(0)$) resta segreto.

**Ricostruzione:** dati $k$ punti $(i, s_i)$, il polinomio $f$ di grado $k-1$ è univocamente determinato (un polinomio di grado $k-1$ è individuato da $k$ punti). Si ricostruisce $S = f(0)$ tramite **interpolazione di Lagrange** in $\mathbb{Z}_p$:
$$S = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1 \\ l\ne j}}^{k} \frac{0 - i_l}{i_j - i_l} \bmod p$$

> 📌 La sicurezza è **perfetta** (incondizionata): con solo $k-1$ share, per ogni possibile valore del segreto esiste esattamente un polinomio compatibile, quindi le $k-1$ share non restringono i candidati. Il caso $(n,n)$ è il sottocaso additivo: $S = (s_1 + \cdots + s_n)\bmod p$.

#### Parte b — Schema $(3,3)$ in $\mathbb{Z}_{11}$ con $s_1=6, s_2=4, s_3=2$ (10 punti)

Nello schema **$(n,n)$** con $n=k=3$ tutte le share sono necessarie e la condivisione è quella **additiva**: il segreto è la somma modulare delle share.

$$S = s_1 + s_2 + s_3 \bmod 11 = 6 + 4 + 2 \bmod 11 = 12 \bmod 11 = 1$$

**Segreto ricostruito:** $\boxed{S = 1}$

**Verifica di coerenza:** se il dealer fissa $S=1$ e sceglie $s_1=6, s_2=4$, allora $s_3 = S - s_1 - s_2 = 1 - 6 - 4 = -9 \equiv 2 \pmod{11}$ ✓

> 📌 Nello schema $(n,n)$ la ricostruzione è una semplice somma modulare: l'interpolazione di Lagrange serve solo quando $k < n$.

#### Parte c — Schema $(2,3)$ in $\mathbb{Z}_{11}$ con $s(1)=3, s(2)=4$ (10 punti)

Lo schema $(2,3)$ di Shamir usa un polinomio di grado $k-1 = 1$: $f(x) = a_0 + a_1 x \bmod 11$, con $a_0 = S$.

**Dati:** punti $(1, 3)$ e $(2, 4)$.

**Metodo 1 — Interpolazione di Lagrange in $x=0$:**

$$L_1(0) = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2, \qquad L_2(0) = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$S = s(1)\,L_1(0) + s(2)\,L_2(0) = 3\cdot 2 + 4\cdot 10 = 6 + 40 = 46 \bmod 11 = 2$$

**Segreto ricostruito:** $\boxed{S = 2}$

**Metodo 2 — Sistema lineare (alternativo):**

$$\begin{cases} a_0 + a_1 = 3 \pmod{11} \\ a_0 + 2a_1 = 4 \pmod{11} \end{cases}$$

Sottraendo: $a_1 = 1$; dalla prima $a_0 = 3 - 1 = 2$. Quindi $f(x) = 2 + x$ e $S = f(0) = 2$.

**Verifica:** $f(1) = 3$ ✓, $f(2) = 4$ ✓. La terza share (non richiesta) sarebbe $s(3) = f(3) = 5$, e qualsiasi 2 delle 3 share ricostruiscono $S=2$.

---

## Pattern e osservazioni per l'esame

Appello della sessione invernale (gennaio 2023). Struttura tipica del corso Cimato: cifrari classici + MAC + firma + secret sharing.

- **D1 (25 punti):** domanda ricorrente *quasi identica* in molti appelli (cfr. 19/09/2025). Schema vincente: classificare COA/KPA/CPA/CCA con un esempio ciascuno, poi tabella di resistenza cifrario-per-cifrario. Punti chiave: i cifrari classici (sostituzione, Playfair, Hill) cadono già in COA/KPA; DES e RSA resistono ai modelli classici (con le riserve note: 56 bit per DES, determinismo/OAEP per RSA).

- **D2 (30 punti):** lo schema MAC ibrido AES/DES + hash. Il difetto è la **collisione strutturale** $\text{MAC}_k(m)=\text{MAC}_k(h(m))$ per $|m|>n$: existential forgery con **una sola query**. Adattare $n=64$ se si sceglie DES. Citare HMAC come costruzione corretta.

- **D3 (15 punti):** DSS standard. Memorizzare le formule di firma ($r=(g^k\bmod p)\bmod q$, $s=k^{-1}(H(M)+xr)\bmod q$) e verifica ($v=(g^{u_1}y^{u_2}\bmod p)\bmod q$, accetta se $v=r$). Il punto b verte sul **nonce**: $k$ diverso → firme diverse e sicure; $k$ riusato → chiave privata compromessa. RSA (textbook) è invece deterministico.

- **D4 (30 punti):** secret sharing di Shamir. Per $(3,3)$ usare la **somma additiva** ($S=1$); per $(2,3)$ usare **Lagrange** o il sistema lineare ($S=2$). Ricordare: $(n,n)$ è additivo, $(k,n)$ con $k<n$ richiede interpolazione.

> ✅ **Tutti e quattro i quesiti di questo appello ricompaiono, con numeri identici o quasi, negli appelli 2024–2025** già risolti (D1↔19/09/2025, D2↔05/09/2025 e lezione M4/UD4-L2, D3↔19/09/2025, D4↔08/07/2024). Padroneggiare questo appello copre una grossa fetta del corso.
