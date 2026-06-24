# Formulario — DNS: Legenda + Pattern Esercizi

**Basato su M3/UD1/L1-L4 — Prof. Ernesto Damiani**
Per gli esercizi che chiedono di illustrare query DNS, commentare risposte dig/nslookup, distinguere autoritativo/non autoritativo.

---

## PARTE 1 — LEGENDA DNS

### 1. Cos'è il DNS

Il **Domain Name System** è un sistema **distribuito e gerarchico** che traduce nomi simbolici in indirizzi IP (e viceversa).

**Caratteristiche fondamentali:**
| Proprietà | Meccanismo |
|---|---|
| **Distribuzione** | Ogni organizzazione gestisce la propria zona; nessun server conosce tutto il namespace |
| **Coerenza** | Master + slave sincronizzati via numero seriale nel record SOA |
| **Scalabilità** | Gerarchia + delegazione dei sottodomini |
| **Affidabilità** | Server multipli per zona, cache, fault tolerance |
| **Dinamicità** | DDNS: aggiornamenti automatici per IP variabili |

---

### 2. Gerarchia DNS

```
. (root)
├── com
│   └── google
│       └── www  →  A  142.250.180.78
├── it
│   └── unimi
│       └── www  →  A  149.132.4.10
└── net
    └── ripe
        ├── www  →  A  193.0.0.135
        └── ns   →  A  193.0.0.129   (il name server stesso)
```

**Livelli:**
- **Root (`.`)** → conosce i TLD
- **TLD** (top-level domain: `.com`, `.it`, `.net`, `.edu`, …) → conosce i domini di 2° livello
- **Dominio di 2° livello** (`unimi.it`, `ripe.net`) → zona gestita dall'organizzazione
- **Sottodomini** (delegati a sub-zone se necessario)

---

### 3. Attori della risoluzione

| Attore | Ruolo |
|---|---|
| **Resolver** | Libreria sul client (OS). Pone le query per conto delle applicazioni. API: `gethostbyname()`, `gethostbyaddr()` |
| **Caching forwarder** (DNS locale di rete) | Riceve le query dei client, le risolve (iterativamente verso la gerarchia) e cachea i risultati |
| **Root server** | 13 cluster globali. Conosce i server TLD. Non ha risposte finali, solo referral |
| **TLD server** (gtld-server) | Server per `.com`, `.it`, ecc. Risponde con il referral al server autorevole del dominio |
| **Server autorevole** | Ha la risposta finale (record A, MX, ecc.) per la zona di cui è responsabile |

---

### 4. Resource Record (RR) — struttura

Ogni voce nel database DNS è un RR. Formato:

```
<Etichetta>    <TTL>    <Classe>    <Tipo>    <RDATA>
www.ripe.net.  3600     IN          A         193.0.0.135
ripe.net.      7200     IN          NS        ns.ripe.net.
```

| Campo | Significato |
|---|---|
| **Etichetta** | Il nome del dominio/host (include il punto finale nell'FQDN) |
| **TTL** | Time To Live in secondi: per quanto il record può restare in cache nei forwarder |
| **Classe** | `IN` = Internet (quasi sempre) |
| **Tipo** | Natura dell'informazione (vedi §5) |
| **RDATA** | Il dato: indirizzo IP, nome di un server, ecc. |

---

### 5. Tipi di Resource Record

| Tipo | Significato | Esempio RDATA |
|---|---|---|
| **A** | Nome → indirizzo IPv4 | `149.132.4.10` |
| **AAAA** | Nome → indirizzo IPv6 | `2001:db8::1` |
| **NS** | Zona → name server autoritativo | `ns.ripe.net.` |
| **SOA** | Start of Authority: parametri di gestione della zona | (vedi §6) |
| **MX** | Mail eXchanger: server di posta per il dominio | `10 mail.unimi.it.` |
| **CNAME** | Canonical Name: alias per un altro nome | `www.alias.it. → host.it.` |
| **PTR** | Indirizzo IP → nome (reverse lookup) | `135.0.0.193.in-addr.arpa. → www.ripe.net.` |

---

### 6. Record SOA — struttura completa

Ogni zona ha un record SOA. Contiene i parametri operativi della zona.

```
ripe.net. 7200 IN SOA ns.ripe.net. olaf.ripe.net. (
    2001061501   ; Serial        — numero versione del file di zona
    43200        ; Refresh       — ogni quanto gli slave controllano aggiornamenti (12 ore)
    14400        ; Retry         — se il refresh fallisce, riprova dopo (4 ore)
    345600       ; Expire        — dopo questo tempo la copia slave non è più valida (4 giorni)
    7200         ; Negative TTL  — durata dei risultati "nome inesistente" in cache (2 ore)
)
```

| Parametro SOA | Scopo |
|---|---|
| **Serial** | Numero versione. Se aumenta, gli slave scaricano la nuova zona |
| **Refresh** | Frequenza con cui gli slave controllano se il serial è cambiato |
| **Retry** | Intervallo di attesa prima di ritentare se il refresh fallisce |
| **Expire** | Tempo massimo dopo il quale la copia slave è considerata non valida (anche senza aggiornamento) |
| **Negative TTL** | Quanto tempo cachare le risposte "NXDOMAIN" (nome inesistente) |

> **Differenza fondamentale: TTL del RR vs timer SOA**
> - **TTL nel RR** → per i **client/forwarder**: quanto tempo possono tenere in cache QUESTO record
> - **Timer SOA** → per i **server slave**: quanto tempo aspettare tra una sincronizzazione e l'altra

---

### 7. Il TTL e la propagazione

Il **TTL** controlla la cache dei forwarder:
- **TTL alto** (es. 86400 = 24h): record stabile, i resolver non fanno query frequenti
- **TTL basso** (es. 60s): record dinamico (DDNS, cambio IP imminente)

**Segnale diagnostico in esame:** un TTL "non rotondo" (es. 136 invece di 3600) significa che il record sta **scadendo in cache** — è stato decrementato e non è una risposta fresca dall'autorevole.

**Propagazione delle modifiche ("macchia d'olio"):**
Quando cambi un record DNS non si propaga istantaneamente:
1. Aggiorni il master → aumenti il serial
2. Gli slave si sincronizzano solo al prossimo Refresh
3. I forwarder usano la copia in cache fino alla scadenza del TTL
4. I client (specie Windows) possono avere resolver locali con cache propria

---

### 8. Query ricorsiva vs iterativa

| | Ricorsiva | Iterativa |
|---|---|---|
| **Chi la risolve** | Il server DNS delegato (fa tutto il lavoro) | Il resolver stesso (passo dopo passo) |
| **Comportamento** | Client chiede e aspetta risposta finale | Client chiede referral, poi interroga il referral, poi il prossimo... |
| **Flag DNS** | `RD=1` (Recursion Desired) nella query; `RA=1` nella risposta | `RD=0` |
| **Tipico attore** | **Client → caching forwarder** (query ricorsiva) | **Caching forwarder → root/TLD/autorevole** (query iterative) |

**Schema di risoluzione `www.unimi.it` via `8.8.8.8`:**
```
Client
  │ query ricorsiva RD=1
  ▼
8.8.8.8 (caching forwarder Google)
  │ query iterativa
  ▼
Root server (.)
  │ referral: "chiedi al TLD .it"
  ▼
TLD server .it
  │ referral: "chiedi al NS di unimi.it"
  ▼
NS autoritativo unimi.it → risponde con AA=1, indirizzo IP
  │
  ▼
8.8.8.8 → cachea il risultato → risponde al Client (AA=0)
```

> **Regola da memorizzare:** verso il client = **ricorsiva** (un solo scambio). Internamente, il forwarder usa **iterative** verso la gerarchia. Il client non è coinvolto nei passi intermedi.

---

### 9. Flag DNS — header del messaggio

Un messaggio DNS ha 16 bit di flag. I più importanti per l'esame:

| Flag | Significato |
|---|---|
| `QR` | 0 = query, 1 = risposta |
| `OPCODE` | 0 = query standard |
| **`AA`** | **Authoritative Answer**: il server che risponde è autorevole per la zona. **Assente nelle risposte di cache.** |
| `TC` | TrunCated: risposta troncata (si usa TCP invece di UDP) |
| **`RD`** | **Recursion Desired**: il client chiede al server di risolvere ricorsivamente (flag nella query) |
| **`RA`** | **Recursion Available**: il server offre il servizio di ricorsione (flag nella risposta) |
| `AD` | Authentic Data: risposta validata DNSSEC |
| `CD` | Checking Disabled (DNSSEC) |

---

### 10. Struttura messaggio DNS

| Sezione | Cosa contiene |
|---|---|
| **Header** | ID transazione, flags, contatori delle sezioni |
| **Question** | Il nome richiesto + tipo (A, NS, MX, …) + classe (IN) |
| **Answer** | I Resource Record che rispondono alla domanda |
| **Authority** | RR NS che indicano i server autorevoli per la zona |
| **Additional** | RR aggiuntivi utili (es. A del server NS indicato in Authority) |

---

## PARTE 2 — PATTERN ESERCIZI DNS

### 11. Esercizio tipo A — "Illustra la risoluzione di X"

**Struttura risposta:**

1. **Tipo di query:** ricorsiva (client → forwarder) con `RD=1`
2. **Diagramma a frecce** con i passi (vedi §8)
3. **Tabella campi** della query e della risposta (vedi §12)
4. **Flag AA=0** nella risposta del forwarder (non è autorevole)

**Schema diagramma:**
```
CLIENT (resolver)                8.8.8.8 (forwarder)
   │                                    │
   │── UDP:53, QNAME: www.X, RD=1 ─────►│
   │                    8.8.8.8 iterativamente risolve:
   │                    1) chiede al Root → referral TLD
   │                    2) chiede al TLD  → referral NS
   │                    3) chiede al NS   → risposta AA=1
   │◄── UDP:53, ANCOUNT=1, AA=0, RA=1 ──│
   │    www.X. IN A <ip>                 │
```

---

### 12. Esercizio tipo B — "Campi della query e della risposta"

**Tabella query:**

| Campo | Dimensione | Valore |
|---|---|---|
| Transaction ID | 16 bit | `0x1234` (scelto dal client per abbinare query/risposta) |
| Flags | 16 bit | `QR=0` (query), `OPCODE=0` (standard), `RD=1` (recursion desired) |
| QDCOUNT | 16 bit | `1` (una domanda) |
| ANCOUNT | 16 bit | `0` (nessuna risposta nella query) |
| NSCOUNT | 16 bit | `0` |
| ARCOUNT | 16 bit | `0` |
| QNAME | variabile | `www.unimi.it.` (codificato come label con lunghezza) |
| QTYPE | 16 bit | `1` = A (record IPv4) |
| QCLASS | 16 bit | `1` = IN (Internet) |

**Tabella risposta:**

| Campo | Valore |
|---|---|
| Transaction ID | Stesso della query (abbinamento) |
| Flags | `QR=1` (risposta), `AA=0` (non autorevole), `RD=1`, `RA=1` (recursion available) |
| ANCOUNT | `1` (un record A nella risposta) |
| Answer — NAME | `www.unimi.it.` |
| Answer — TYPE | `A` |
| Answer — CLASS | `IN` |
| Answer — TTL | es. `300` secondi |
| Answer — RDLENGTH | `4` byte (IPv4 = 4 byte) |
| Answer — RDATA | es. `149.132.4.10` |

---

### 13. Esercizio tipo C — "Come si capisce che la risposta NON è autoritativa?"

**Tre indizi convergenti:**

1. **Flag `AA` assente** nell'header DNS (prova formale — RFC 1035)
   - In `dig`: `flags: qr rd ra;` → manca `aa`
   - In `nslookup`: riga `Non-authoritative answer:`

2. **TTL non rotondo** (es. `136` anziché `3600`) → il record sta scadendo in cache, è stato decrementato dal tempo già passato

3. **Server interrogato non è il NS autorevole** per la zona:
   - `8.8.8.8` non compare nei record `NS` di `unimi.it`
   - Per risposta autoritativa: `dig @ns.unimi.it www.unimi.it A` → `flags: qr aa rd;`

**Perché è importante sapere se è autoritativa:**
| Motivo | Spiegazione |
|---|---|
| **Freshness** | Una risposta cached può essere obsoleta fino al TTL residuo |
| **Troubleshooting** | Per diagnosticare propagazione DNS, bisogna confrontare autorevole vs cache |
| **Cache poisoning** | Le risposte cached sono bersaglio degli attacchi DNS; sapere la fonte aiuta |
| **DNSSEC** | Le firme RRSIG vengono dai server autorevoli |

---

### 14. Esercizio tipo D — "Ricorsiva o iterativa?"

**Risposta standard:** la query **client → 8.8.8.8** è **ricorsiva** perché:
- Il client imposta `RD=1` (Recursion Desired)
- Il client si affida completamente a `8.8.8.8` per tutta la risoluzione
- Scambia **un solo datagramma** di andata e uno di ritorno
- Non è in grado di interrogare autonomamente root server, TLD server, ecc.

`8.8.8.8` risponde con `RA=1` (Recursion Available) → conferma che offre il servizio.

**Internamente**, `8.8.8.8` usa query **iterative** verso root/TLD/autorevole.

---

### 15. Lettura output `dig`

```
;; flags: qr rd ra;   QUERY: 1, ANSWER: 1, AUTHORITY: 0, ADDITIONAL: 1
                ^^^^^
                NO "aa" = non autoritativa

;; ANSWER SECTION:
www.unimi.it.   136    IN    A    149.132.4.10
                ^^^
                TTL residuo (non rotondo = in cache)
```

**Analisi sistematica:**
1. `flags:` → cerca `aa` (se assente = non autoritativo)
2. `ra` nelle flags → il server offre ricorsione
3. TTL in ANSWER → se non rotondo = risposta cached
4. `AUTHORITY: 0` → il server non ha incluso i NS autorevoli (segnale ulteriore di risposta cached)
5. `SERVER:` → chi ha risposto; se non è un NS di zona = non autoritativo

---

### 16. Sicurezza DNS — load balancing e privacy

**Un nome → più IP** (load balancing):
Il server autorevole può restituire più record A per lo stesso nome; il resolver sceglie (round-robin o per prossimità) → bilanciamento del carico.

**Più nomi → un IP** (virtual hosting):
Usato per ospitare più siti sullo stesso server (insieme all'header `Host` di HTTP/1.1).

**Best practice di sicurezza:**
> La distribuzione dei nomi visibile su Internet **non dovrebbe corrispondere all'effettiva struttura interna**. Il DNS pubblico dovrebbe esporre il minimo necessario.
