# MAC AES-hash con attacco di collisione — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 gennaio 2023**, Domanda 2 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 gennaio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L1%20-%20Appello%2024%20gennaio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
