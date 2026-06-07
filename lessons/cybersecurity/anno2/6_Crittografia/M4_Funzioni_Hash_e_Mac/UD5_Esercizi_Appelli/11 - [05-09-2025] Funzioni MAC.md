# Funzioni MAC — Esercizio d'esame

> 🗂️ **Fonte:** appello del **5 settembre 2025**, Domanda 3 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 5 settembre 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L5%20-%20Appello%205%20settembre%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
