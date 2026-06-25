# Soluzione Esame di Reti di Calcolatori — Parte B — 20/03/2026

**Prof. Ernesto Damiani**

Per ogni esercizio è indicato il riferimento alle lezioni di teoria del corso, l'inquadramento concettuale, lo svolgimento e — dove richiesto — i diagrammi.

---

## Indice

- [Esercizio 1 — Query DNS di tipo A per www.unimi.it verso 8.8.8.8](#esercizio-1--query-dns-di-tipo-a-per-wwwunimit-verso-8888)
- [Esercizio 2 — Servizio UDP client-server per conversione orario in CET](#esercizio-2--servizio-udp-client-server-per-conversione-orario-in-cet)
- [Esercizio 3 — Analisi di una richiesta HTTP PUT](#esercizio-3--analisi-di-una-richiesta-http-put)

---

## Esercizio 1 — Query DNS di tipo A per www.unimi.it verso 8.8.8.8

**Esercizio 1 (10 punti)** Illustrate tramite un diagramma una richiesta DNS di tipo A per la traduzione del nome `www.unimi.it` inviata al server dei nomi pubblico `8.8.8.8` (Google Public DNS). Indicate: i campi della query e della risposta; l'indirizzo IP restituito dal server. Specificate se la query è ricorsiva o iterativa e motivate la scelta.

---

### Inquadramento teorico

> 📌 Il DNS (Domain Name System) è il sistema distribuito e gerarchico che traduce nomi simbolici in indirizzi IP. Le query possono essere **ricorsive** (il server lavora per conto del client fino ad ottenere la risposta finale) o **iterative** (il server risponde con il miglior indirizzo parziale che conosce, delegando il passo successivo al resolver). — *M3 UD1 L2, L4*

I **Resource Record di tipo A** associano un nome di dominio a un indirizzo IPv4. Un record A ha la forma:

```
www.unimi.it.   IN   A   <indirizzo IPv4>
```

Quando il client interroga il server pubblico `8.8.8.8` (Google Public DNS), invia la query su **porta UDP 53** (o TCP 53 per risposte di grandi dimensioni).

---

### Tipo di query: ricorsiva

La query è **ricorsiva**. Motivazione:

- Il client (resolver della macchina dell'utente) non è in grado di effettuare l'intera catena di risoluzione: non conosce i root server nè i server TLD `.it`.
- Con una query **ricorsiva**, il client imposta il flag `RD = 1` (Recursion Desired) nel messaggio DNS e si affida completamente al server `8.8.8.8`, che si occupa di risolvere il nome percorrendo autonomamente la gerarchia DNS.
- Il server pubblico `8.8.8.8` agisce da **resolver ricorsivo** (caching/forwarder): esegue internamente le query iterative verso root server -> TLD `.it` -> server autorevole `unimi.it`, poi restituisce la risposta finale al client.

> 💡 Verso il client la query è **ricorsiva** (un solo scambio client<->8.8.8.8). Internamente, `8.8.8.8` usa query **iterative** verso la gerarchia DNS. Il client non è coinvolto in questi passi intermedi.

---

### Campi del messaggio DNS — Query

Il messaggio DNS ha una struttura fissa:

| Campo | Dimensione | Valore nella nostra query |
|---|---|---|
| **Transaction ID** | 16 bit | `0x1A2B` (scelto dal client, usato per abbinare query e risposta) |
| **Flags** | 16 bit | `QR=0` (query), `OPCODE=0` (standard), `RD=1` (recursion desired) |
| **QDCOUNT** | 16 bit | `1` (una sola domanda) |
| **ANCOUNT** | 16 bit | `0` (nessuna risposta nella query) |
| **NSCOUNT** | 16 bit | `0` |
| **ARCOUNT** | 16 bit | `0` |
| **QNAME** | variabile | `www.unimi.it.` (codificato come sequenza di label precedute dalla loro lunghezza) |
| **QTYPE** | 16 bit | `1` = `A` (richiesta di indirizzo IPv4) |
| **QCLASS** | 16 bit | `1` = `IN` (Internet) |

---

### Campi del messaggio DNS — Risposta

Quando `8.8.8.8` ha completato la risoluzione, invia al client il messaggio di risposta:

| Campo | Valore |
|---|---|
| **Transaction ID** | `0x1A2B` (stesso della query, per abbinamento) |
| **Flags** | `QR=1` (risposta), `AA=0` (non autorevole — risposta da cache o forwarding), `RD=1`, `RA=1` (recursion available) |
| **QDCOUNT** | `1` |
| **ANCOUNT** | `1` (un record A nella sezione Answer) |
| **Sezione Question** | `www.unimi.it. IN A` (ripetuta) |
| **Sezione Answer — NAME** | `www.unimi.it.` |
| **Sezione Answer — TYPE** | `A` (`1`) |
| **Sezione Answer — CLASS** | `IN` (`1`) |
| **Sezione Answer — TTL** | es. `300` secondi |
| **Sezione Answer — RDLENGTH** | `4` (4 byte per IPv4) |
| **Sezione Answer — RDATA** | `149.132.4.10` (indirizzo IPv4 di www.unimi.it) |

> ⚠️ Il flag `AA=0` indica che la risposta **non è autorevole**: proviene dalla cache o dal processo di forwarding di `8.8.8.8`, non direttamente dal server autorevole `unimi.it`. Una risposta con `AA=1` si otterrebbe interrogando direttamente il name server di `unimi.it`.

---

### Diagramma della risoluzione

```
CLIENT (resolver locale)              8.8.8.8 (Google Public DNS)
        |                                       |
        |--- Query ricorsiva UDP:53 ----------->|
        |    QNAME: www.unimi.it.               |
        |    QTYPE: A, RD=1                     |
        |                                       |
        |                    8.8.8.8 esegue internamente:
        |                    1) Query iterativa .. Root server (.)
        |                       Risposta: chiedi ai server TLD .it
        |                    2) Query iterativa .. TLD .it server
        |                       Risposta: chiedi al NS di unimi.it
        |                    3) Query iterativa .. NS unimi.it (autorevole)
        |                       Risposta AA=1: www.unimi.it A 149.132.4.10
        |                                       |
        |<-- Risposta ricorsiva UDP:53 ---------|
        |    ANCOUNT=1                          |
        |    www.unimi.it. IN A 149.132.4.10    |
        |    TTL=300, AA=0, RA=1                |
```

> ✅ Il client invia **una sola query UDP** verso `8.8.8.8` e riceve **una sola risposta** con l'indirizzo IP finale. Tutta la complessita' della risoluzione gerarchica è delegata al server ricorsivo `8.8.8.8`.

---

## Esercizio 2 — Servizio UDP client-server per conversione orario in CET

**Esercizio 2 (10 punti)** Dovete realizzare un servizio client-server (basato su UDP) per convertire l'orario di sistema del client in CET (Central European Time).
- **(1)** Scrivete lo pseudocodice del server che riceve una stringa nel formato `hh:mm:ss`; converte l'orario in CET; restituisce la stringa nello stesso formato.
- **(2)** Scrivete lo pseudocodice del client che legge l'ora locale dal sistema; invoca il server (indirizzo `127.0.0.1`, porta `12345`) inviando l'ora locale; riceve l'ora CET.

---

### Inquadramento teorico

> 📌 UDP (`SOCK_DGRAM`) è un protocollo **senza connessione**: ogni datagramma viene inviato tramite `sendto()` e ricevuto con `recvfrom()`. Non è necessario un setup esplicito (niente `listen()` né `accept()`): il server usa `bind()` per registrare la porta di ascolto; il client usa direttamente `sendto()` specificando l'indirizzo del server. — *M4 UD1 L2, L3, L4*

Poiché il servizio è su `localhost` (`127.0.0.1`), client e server comunicano sulla stessa macchina. Il server **non si connette** mai al client: riceve il datagramma con `recvfrom()` — che gli fornisce automaticamente l'indirizzo del mittente — poi risponde con `sendto()`.

**Nota sulla conversione CET:** CET è UTC+1 (UTC+2 in estate, CEST).

---

### (1) Pseudocodice del SERVER

```
[INIT]
sockfd = socket(AF_INET, SOCK_DGRAM, 0)
se errore -> termina con messaggio di errore

[BIND sulla porta di ascolto]
server_addr.sin_family      = AF_INET
server_addr.sin_port        = htons(12345)
server_addr.sin_addr.s_addr = INADDR_ANY  -- su tutte le interfacce

bind(sockfd, &server_addr, sizeof(server_addr))
se errore -> termina con messaggio di errore

[CICLO PRINCIPALE]
loop infinito:

    -- 1. Ricezione del datagramma dal client
    nBytes = recvfrom(sockfd, buffer, sizeof(buffer), 0,
                      &client_addr, &client_addr_len)
    -- buffer contiene la stringa "hh:mm:ss" inviata dal client

    -- 2. Parsing della stringa
    [hh, mm, ss] = parse(buffer)

    -- 3. Conversione in CET (UTC+1)
    offset_cet = +1
    hh_cet = (hh + offset_cet) mod 24

    -- 4. Formattazione della risposta
    risposta = format("%02d:%02d:%02d", hh_cet, mm, ss)

    -- 5. Invio della risposta al client
    sendto(sockfd, risposta, strlen(risposta), 0,
           &client_addr, client_addr_len)

-- Il server gira indefinitamente finché non viene interrotto
close(sockfd)
```

> 💡 Il server non chiama mai `listen()` né `accept()`: in UDP non c'è fase di setup della connessione. Il server rimane bloccato su `recvfrom()` in attesa del prossimo datagramma.

---

### (2) Pseudocodice del CLIENT

```
[INIT]
sockfd = socket(AF_INET, SOCK_DGRAM, 0)
se errore -> termina con messaggio di errore

[Configurazione dell'indirizzo del server]
server_addr.sin_family      = AF_INET
server_addr.sin_port        = htons(12345)
server_addr.sin_addr        = inet_addr("127.0.0.1")

-- 1. Lettura dell'ora locale dal sistema
ora_locale  = get_local_time()
stringa_ora = format("%02d:%02d:%02d",
                     ora_locale.hh, ora_locale.mm, ora_locale.ss)

-- 2. Invio dell'ora locale al server via UDP
nBytes = sendto(sockfd, stringa_ora, strlen(stringa_ora), 0,
                &server_addr, sizeof(server_addr))
se errore -> termina con messaggio di errore

-- 3. Ricezione dell'ora CET dal server
nBytes = recvfrom(sockfd, buffer_risposta, sizeof(buffer_risposta), 0,
                  NULL, NULL)
se errore -> termina con messaggio di errore

-- 4. Stampa del risultato
print("Ora locale: " + stringa_ora)
print("Ora CET:    " + buffer_risposta)

close(sockfd)
```

> ⚠️ Il client **non chiama `bind()`**: in UDP, il sistema operativo assegna automaticamente una porta effimera al momento della `sendto()`. Il server recupera l'indirizzo sorgente tramite `recvfrom()` e usa quell'indirizzo per inviare la risposta.

---

### Schema del dialogo UDP

```
CLIENT                                SERVER (127.0.0.1:12345)
   |                                       |
   |  legge ora locale dal sistema         |
   |  stringa_ora = "14:35:22"             |
   |                                       |
   |--- sendto() ------------------------->|  recvfrom() riceve "14:35:22"
   |    UDP datagramma: "14:35:22"         |
   |                                       |  converte: 14+1=15 -> "15:35:22"
   |                                       |
   |<-- risposta sendto() -----------------|  verso client_addr (porta effimera)
   |    UDP datagramma: "15:35:22"         |
   |                                       |
   |  stampa "Ora CET: 15:35:22"           |
```

> ✅ Il servizio è stateless: ogni coppia (richiesta, risposta) è completamente autocontenuta. Ogni nuova invocazione del client è indipendente dalle precedenti.

---

## Esercizio 3 — Analisi di una richiesta HTTP PUT

**Esercizio 3 (10 punti)** Di seguito è riportata la richiesta HTTP inviata da un client:

```http
PUT /api/v1/documents/5281 HTTP/1.1
Host: documents.example.org
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/134.0.0.0
Accept: application/json
Accept-Language: it-IT,it;q=0.9,en;q=0.8
Accept-Encoding: gzip, deflate, br
Content-Type: application/json; charset=utf-8
Content-Length: 142
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.[...]
If-Match: "alb2c3d4e5f6"
Origin: https://editor.example.org
Referer: https://editor.example.org/documents/5281
Connection: keep-alive
```

**(i)** Indicate il metodo HTTP utilizzato e spiegate brevemente la sua semantica.
**(ii)** Elencate tutti gli header presenti e indicate il loro significato.
**(iii)** Supponendo che il server risponda con il codice 412 Precondition Failed, quale può essere la causa più probabile?

---

### Inquadramento teorico

| Header | Valore | Significato |
|--------|--------|-------------|
| **Host** | `documents.example.org` | Host di destinazione. Obbligatorio in HTTP/1.1 per virtual hosting. |
| **User-Agent** | `Mozilla/5.0 … Chrome/134 …` | Identifica browser e SO del client. |

> 📌 HTTP è il protocollo del Web: ogni messaggio ha una **request line**, **header** e **body** opzionale. I metodi definiscono la semantica, gli header forniscono metadati. — *M3 UD4 L2, L3*

---

### (i) Metodo HTTP: PUT

Il metodo utilizzato è **`PUT`**.

PUT è un metodo HTTP usato per **trasferire una risorsa dal client al server**, sostituendo completamente la risorsa identificata dall'URI specificato con il contenuto del corpo della richiesta.

In questo caso:

- La risorsa è il documento `5281` all'URI `/api/v1/documents/5281`.
- Il body (`Content-Length: 142` byte) contiene il nuovo stato del documento in JSON.
- `PUT` è **idempotente**: applicarlo più volte produce lo stesso risultato.
- È il metodo canonico REST per la sostituzione completa di una risorsa.

> 💡 `POST` crea; `PUT` **sostituisce** integralmente; `PATCH` modifica parzialmente.

---

### (ii) Header presenti e loro significato

| Header | Valore | Significato |
|---|---|---|
| **Host** | `documents.example.org` | Host di destinazione. Obbligatorio in HTTP/1.1 per il virtual hosting. |
| **User-Agent** | `Mozilla/5.0 … Chrome/134 …` | Identifica il software client (browser, versione, SO). |
| **Accept** | `application/json` | Tipi MIME accettabili nella risposta: il client vuole JSON. |
| **Accept-Language** | `it-IT,it;q=0.9,en;q=0.8` | Lingua preferita della risposta, ordinata per priorità `q`. |
| **Accept-Encoding** | `gzip, deflate, br` | Algoritmi di compressione accettati (`br` = Brotli). |
| **Content-Type** | `application/json; charset=utf-8` | Tipo MIME e codifica del body della richiesta. |
| **Content-Length** | `142` | Dimensione in byte del body. Permette al server di sapere quanti byte leggere. |
| **Authorization** | `Bearer eyJhbGci…` | Token JWT di autenticazione Bearer. Il server lo verifica per autorizzare l'operazione. |
| **If-Match** | `alb2c3d4e5f6` | ETag condizionale: la PUT viene eseguita **solo se** l'ETag corrente della risorsa corrisponde. Previene il *lost update problem*. |
| **Origin** | `https://editor.example.org` | Origine della richiesta: usato dal meccanismo **CORS** per le policy cross-domain. |
| **Referer** | `https://editor.example.org/documents/5281` | URL della pagina di provenienza: usato per logging e analytics. |
| **Connection** | `keep-alive` | Mantiene la connessione TCP aperta per richieste successive (default in HTTP/1.1). |

---

### (iii) Causa più probabile del codice 412 Precondition Failed

Il codice **412 Precondition Failed** indica che **almeno una condizione nell'header condizionale non è soddisfatta** dal server.

**Causa più probabile: ETag non corrispondente nell'header ``If-Match``**

La richiesta include `If-Match: alb2c3d4e5f6`, che istruisce il server: *esegui la `PUT` solo se l'ETag attuale della risorsa è `alb2c3d4e5f6`*.

Il server risponde **412** se l'ETag della risorsa è **cambiato** da quando il client ha letto il documento. Questo accade tipicamente perché:

1. **Un altro client ha modificato la risorsa** nel frattempo, aggiornando l'ETag sul server.
2. La risorsa è stata **modificata o eliminata** per qualunque altra ragione.

> 📌 Il meccanismo ETag + ``If-Match`` implementa **ottimistic locking** in HTTP/REST: le modifiche vengono applicate solo se il client stava lavorando sulla versione più recente della risorsa, prevenendo il *lost update problem*.

> ⚠️ Senza ``If-Match``, il server avrebbe accettato la ``PUT`` indiscriminatamente, rischiando sovrascrittura di modifiche altrui. Il codice 412 segnala al client di ricaricare la risorsa (nuovo ETag) e ritentare.
