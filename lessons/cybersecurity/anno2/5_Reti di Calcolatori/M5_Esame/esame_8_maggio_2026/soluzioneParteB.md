# Soluzione Esame di Reti di Calcolatori — Parte B — 8/05/2026

**Prof. Ernesto Damiani**

Per ogni esercizio è indicato il riferimento alle lezioni di teoria del corso, l'inquadramento concettuale, lo svolgimento e — dove richiesto — i diagrammi.

---

## Indice

- [Esercizio 1 — Proxy TCP per Wi-Fi di bordo con autenticazione a token](#esercizio-1--proxy-tcp-per-wi-fi-di-bordo-con-autenticazione-a-token)
- [Esercizio 2 — Analisi di una richiesta HTTP/1.1](#esercizio-2--analisi-di-una-richiesta-http11)
- [Esercizio 3 — Download di una pagina con 4 immagini in HTTP/1.0 e HTTP/1.1](#esercizio-3--download-di-una-pagina-con-4-immagini-in-http10-e-http11)
- [Esercizio 4 — Regola di firewall: solo proxy → TCP esterno, UDP disabilitato](#esercizio-4--regola-di-firewall-solo-proxy--tcp-esterno-udp-disabilitato)

---

## Esercizio 1 — Proxy TCP per Wi-Fi di bordo con autenticazione a token

**Esercizio 1 (12 punti)** Una compagnia aerea low cost vuole gestire il wi-fi di bordo. Non sono previsti pagamenti: durante il volo, alla rete dell'aeromobile possono accedere solo viaggiatori in possesso di un token d'autenticazione rilasciato dal programma fedeltà della compagnia. Collegandosi alla rete, i client ricevono un indirizzo IP privato tramite DHCP; la loro configurazione comprende l'indirizzo IP – sempre privato – del sistema proxy. Scrivete lo pseudo-codice del proxy basato su socket TCP che (i) quando un client inizia la connessione controlla se il suo indirizzo è presente in un file CONNECTED. Se non risulta presente, il proxy raccoglie username e password dell'utente, si collega al server remoto del programma fedeltà per ricevere un token (ii) controlla il token ricevuto (ipotizzate di usare una chiamata di libreria) e se il controllo è ok salva l'indirizzo del client nel file CONNECTED. Un processo separato del proxy (di cui non dovete occuparvi) eseguirà poi le connessioni verso Internet per conto dei client CONNECTED. ATTENZIONE: È vostra responsabilità leggere dal socket l'indirizzo privato dei client.

> **Riferimenti di teoria**:
>
> - [M4/UD1/L2 — Creazione di un socket](../../M4_Tecniche_programmazione_distribuita/UD1/L2%20-%20Creazione%20di%20un%20socket.md) (`socket()`, `bind()`, ruolo `INADDR_ANY`)
> - [M4/UD1/L3 — Setup di connessione](../../M4_Tecniche_programmazione_distribuita/UD1/L3%20-%20Setup%20di%20connessione.md) (`listen()`, `accept()`, `connect()`)
> - [M4/UD1/L4 — Funzioni di comunicazione via socket](../../M4_Tecniche_programmazione_distribuita/UD1/L4%20-%20Funzioni%20di%20comunicazione%20via%20socket.md) (`send()`, `recv()`, `close()`)
> - [M4/UD1/L5 — Codifica degli indirizzi](../../M4_Tecniche_programmazione_distribuita/UD1/L5%20-%20Codifica%20degli%20indirizzi.md) (lettura dell'indirizzo del client dalla `accept()`)
> - [M2/UD2/L6 — DHCP (Dynamic Host Configuration Protocol)](<../../M2_Protocolli_rete_TCP_IP/UD2/L6%20-%20DHCP%20(Dynamic%20Host%20Configuration%20Protocol).md>) (configurazione automatica del client a bordo)
> - [M2/UD4/L1 — Indirizzi pubblici e privati](../../M2_Protocolli_rete_TCP_IP/UD4/L1%20-%20Indirizzi%20pubblici%20e%20privati.md) (perché il piano di indirizzamento è privato)

### Inquadramento teorico

Lo scenario è quello classico di un **proxy applicativo** che fa da gateway autenticato per una LAN isolata (la rete Wi-Fi dell'aeromobile). Il piano d'indirizzamento è **privato** (cfr. M2/UD4/L1): i client ricevono via DHCP indirizzi non instradabili (es. `10.x.x.x` o `192.168.x.x`) e tutto il traffico verso Internet attraversa il proxy.

Il proxy è un **server TCP** (`SOCK_STREAM`), per cui valgono i passi canonici (cfr. M4/UD1/L3):

1. `socket()` → creazione del socket di ascolto;
2. `bind()` → associazione alla porta proxy (es. 8080);
3. `listen()` → coda di richieste pendenti;
4. `accept()` (bloccante) → restituisce un **nuovo socket dedicato** + struttura `sockaddr` con l'**IP del client**;
5. `recv()`/`send()` → dialogo applicativo;
6. `close()` → rilascio risorse.

> **Punto chiave del testo dell'esercizio**: l'indirizzo IP privato del client **non va dedotto da header applicativi**, ma **letto direttamente dal socket** — esattamente ciò che fa la `accept()` riempiendo il parametro `sockaddr_in` con l'IP/porta del peer connesso (cfr. M4/UD1/L3, §5). Questo evita che un client malevolo possa "millantare" un indirizzo diverso da quello che gli ha assegnato il DHCP di bordo.

Per **non bloccare** il proxy mentre dialoga con il programma fedeltà remoto, si delega ogni nuova connessione client a un **processo figlio** (`fork()`) o a un **thread**; il padre torna immediatamente a `accept()`.

### Pseudocodice del proxy

```c
#define PROXY_PORT     8080
#define FEDELTA_IP     "10.10.10.1"
#define FEDELTA_PORT   443
#define CONNECTED_FILE "/var/lib/proxy/CONNECTED"
#define BUF            4096

int main(void) {
    int sock_srv = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in srv_addr;
    bzero((char*)&srv_addr, sizeof(srv_addr));
    srv_addr.sin_family      = AF_INET;
    srv_addr.sin_port        = htons(PROXY_PORT);
    srv_addr.sin_addr.s_addr = INADDR_ANY;

    bind(sock_srv, (struct sockaddr*)&srv_addr, sizeof(srv_addr));
    listen(sock_srv, 64);

    while (1) {
        struct sockaddr_in cli_addr;
        int cli_len = sizeof(cli_addr);
        int sock_cli = accept(sock_srv, (struct sockaddr*)&cli_addr, &cli_len);

        /* IP del client letto dal kernel tramite accept() — non falsificabile */
        char client_ip[16];
        strcpy(client_ip, inet_ntoa(cli_addr.sin_addr));

        if (fork() == 0) {          /* figlio: gestisce UN client */
            close(sock_srv);
            char buf[BUF];

            /* 1. già autorizzato? cerca IP nel file CONNECTED (alto livello) */
            if (ip_in_file(CONNECTED_FILE, client_ip)) {
                send(sock_cli, "ALREADY_AUTHORIZED\n", 19, 0);
                close(sock_cli); exit(0);
            }

            /* 2. raccogli credenziali dal client */
            send(sock_cli, "USERNAME?\n", 10, 0);
            recv(sock_cli, buf, sizeof(buf), 0);   /* legge username (TCP stream) */
            char username[256]; /* copia da buf */

            send(sock_cli, "PASSWORD?\n", 10, 0);
            recv(sock_cli, buf, sizeof(buf), 0);   /* legge password */
            char password[256]; /* copia da buf */

            /* 3. apre connessione TCP al server fedeltà — lato attivo */
            struct sockaddr_in fed_addr;
            bzero((char*)&fed_addr, sizeof(fed_addr));
            fed_addr.sin_family      = AF_INET;
            fed_addr.sin_port        = htons(FEDELTA_PORT);
            fed_addr.sin_addr.s_addr = inet_addr(FEDELTA_IP);

            int sock_fed = socket(AF_INET, SOCK_STREAM, 0);
            connect(sock_fed, (struct sockaddr*)&fed_addr, sizeof(fed_addr));

            /* 4. invia credenziali, ricevi token */
            snprintf(buf, sizeof(buf), "AUTH %s %s\n", username, password);
            send(sock_fed, buf, strlen(buf), 0);
            recv(sock_fed, buf, sizeof(buf), 0);   /* token */
            close(sock_fed);

            /* 5. valida token (chiamata di libreria) */
            if (!loyalty_check_token(buf)) {
                send(sock_cli, "AUTH_FAIL\n", 10, 0);
            } else {
                /* 6. registra IP nel file CONNECTED e autorizza */
                append_ip(CONNECTED_FILE, client_ip);  /* alto livello */
                send(sock_cli, "AUTH_OK\n", 8, 0);
            }

            close(sock_cli);
            exit(0);
        }
        close(sock_cli);    /* padre: torna subito ad accept() */
    }
}
```

### Note chiave

1. **IP letto da `accept()`**: il kernel popola `cli_addr` con l'IP reale del mittente del SYN; il client non può falsificarlo.
2. **TCP è stream**: `recv()` può tornare parziale → in pratica si legge in loop fino a `\n` per ogni riga di testo.
3. **`fork()` per concorrenza**: ogni figlio gestisce un solo client; il padre torna immediatamente ad `accept()` (cfr. M4/UD1/L6).

### Diagramma di sequenza

```
Client                Proxy (porta 8080)              Server Fedeltà
──────                ─────────────────              ───────────────
 SYN ─────────────────► accept() ritorna
                       (legge IP client dal sock)
                       │
                       ├─ IP in CONNECTED?
                       │    SÌ → "ALREADY_AUTHORIZED" ──┐
                       │                                │
                       │    NO ↓                         │
                       │                                │
   "USERNAME?"  ◄──────┤                                │
   user ────────────────►                               │
   "PASSWORD?"  ◄──────┤                                │
   pwd ─────────────────►                               │
                       │── SYN ────────────────────────►│
                       │◄── SYN+ACK ────────────────────│
                       │── ACK + AUTH user pwd ─────────►│
                       │◄── TKN:abc123 ─────────────────│
                       │── FIN ────────────────────────►│
                       │
                       ├─ loyalty_lib.check_token(tkn)
                       │    KO → "AUTH_FAIL"  ─────────┐
                       │    OK ↓                       │
                       │    append IP a CONNECTED      │
   "AUTH_OK" ◄─────────┤                               │
```

---

## Esercizio 2 — Analisi di una richiesta HTTP/1.1

**Esercizio 2 (8 punti)**

Considerate la seguente richiesta http:

```
GET /sesar/index.html HTTP/1.1
Host: di.unimi.it
User agent: Mozilla/5.0 (Macintosh; U; PPC Mac OS X; en)
Accept: ext/xml, application/xml, application/xhtml+xml, text/html;q=0.9,
text/plain;q=0.8, image/png,*,*;q=0.5
Accept: it
Keep-Alive: 200
Connection: keep-alive
```

Rispondete alle seguenti domande:

1. Qual è l'URL richiesto?
2. Cosa significano i parametri q=0.8 e q=0.5?
3. Il browser richiede una connessione persistente o non persistente?
4. Qual è il tipo (e la versione) di browser utilizzato dal client nella richiesta HTTP?


> **Riferimenti di teoria**:
>
> - [M3/UD4/L1 — World Wide Web](../../M3_Protocolli_applicativi/UD4/L1%20-%20World%20Wide%20Web.md) (HTTP/1.0 vs 1.1, connessioni persistenti)
> - [M3/UD4/L2 — HTTP (HyperText Transfer Protocol)](<../../M3_Protocolli_applicativi/UD4/L2%20-%20HTTP%20(HyperText%20Transfer%20Protocol).md>) (URL, header di richiesta, `Accept`, `User-Agent`)
> - [M3/UD4/L3 — WWW e HTTP](../../M3_Protocolli_applicativi/UD4/L3%20-%20WWW%20e%20HTTP.md) (riga di richiesta, header `Connection`)

### Richiesta da analizzare

```
GET /sesar/index.html HTTP/1.1
Host: di.unimi.it
User agent: Mozilla/5.0 (Macintosh; U; PPC Mac OS X; en)
Accept: ext/xml, application/xml, application/xhtml+xml, text/html;q=0.9,
        text/plain;q=0.8, image/png,*,*;q=0.5
Accept: it
Keep-Alive: 200
Connection: keep-alive
```

### 1. Qual è l'URL richiesto?

Un **URL** è composto da `protocollo://host[:porta]/percorso/risorsa` (cfr. M3/UD4/L2, §3). Componendo:

- **Protocollo**: la riga di richiesta dichiara `HTTP/1.1` (è la versione, ma per default lo schema è `http`);
- **Host**: dal campo `Host: di.unimi.it`;
- **Porta**: non specificata → quella di default di HTTP, ossia **80**;
- **Percorso/risorsa**: dalla _request line_ `GET /sesar/index.html HTTP/1.1`.

$$
\boxed{\;\text{URL} \;=\; \texttt{http://di.unimi.it/sesar/index.html}\;}
$$

(In forma esplicita con porta: `http://di.unimi.it:80/sesar/index.html`.)

### 2. Cosa significano i parametri `q=0.8` e `q=0.5`?

I valori `q=…` (chiamati **quality value** o **relative preference**) compaiono nei campi di **content negotiation** come `Accept`, `Accept-Language`, `Accept-Encoding`, `Accept-Charset` (cfr. M3/UD4/L2, §6.b — campo `Accept`). Indicano la **preferenza relativa** del client per uno specifico MIME-type rispetto agli altri elencati.

| Valore di `q` | Significato                                  |
| ------------- | -------------------------------------------- |
| `q=1.0`       | preferito al massimo (default se omesso)     |
| `q=0.8`       | accettabile, preferenza media                |
| `q=0.5`       | accettabile come _fallback_ di basso livello |
| `q=0`         | **non** accettato (rifiuto esplicito)        |

Nello specifico della richiesta:

| MIME-type                                             |   `q` effettivo | Interpretazione                                        |
| ----------------------------------------------------- | --------------: | ------------------------------------------------------ |
| `ext/xml`, `application/xml`, `application/xhtml+xml` | 1.0 (implicito) | i tipi XML/XHTML sono i preferiti                      |
| `text/html`                                           |         **0.9** | HTML va benissimo, leggermente meno preferito di XML   |
| `text/plain`                                          |         **0.8** | testo puro accettabile                                 |
| `image/png`                                           | 1.0 (implicito) | PNG preferito                                          |
| `*/*` (o `*,*`)                                       |         **0.5** | qualunque altra cosa è accettata, ma è l'ultima scelta |

> Quindi `q=0.8` e `q=0.5` **non sono probabilità** né "qualità tecniche": sono **pesi relativi** che il browser usa per dire al server "se devi scegliere fra due rappresentazioni, dammi quella con `q` maggiore". Il server confronta l'`Accept` con i tipi a sua disposizione, calcola il _quality factor_ di ciascuno e serve quello migliore (algoritmo di **server-driven content negotiation**, RFC 7231 §5.3).

### 3. Il browser richiede una connessione persistente o non persistente?

Ci sono **due indizi convergenti**:

1. La versione dichiarata è **HTTP/1.1**, che per default usa connessioni persistenti (cfr. M3/UD4/L1, §6).
2. Il campo esplicito `Connection: keep-alive` (più il `Keep-Alive: 200` che suggerisce timeout di 200 secondi) **forza esplicitamente** la modalità persistente, anche in caso di interlocuzione con server HTTP/1.0.

$$
\boxed{\;\text{Connessione PERSISTENTE (keep-alive)}\;}
$$

Concretamente: dopo la risposta a `index.html`, la **stessa connessione TCP** resterà aperta e verrà riutilizzata per scaricare gli oggetti collegati (immagini, CSS, JS, …), evitando il costo di nuovi _three-way handshake_.

### 4. Tipo e versione del browser?

Il campo `User agent` (sebbene scritto separato — andrebbe `User-Agent` — è chiaramente lo stesso header) è il banner identificativo del client (cfr. M3/UD4/L2, §6.d):

```
User agent: Mozilla/5.0 (Macintosh; U; PPC Mac OS X; en)
```

Decodifica:

| Campo                            | Significato                                                                                                |
| -------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| `Mozilla/5.0`                    | nome simbolico del motore di rendering (oggi usato come stringa di compatibilità da quasi tutti i browser) |
| `Macintosh; U; PPC Mac OS X; en` | piattaforma host: **Mac con CPU PowerPC**, sistema operativo **Mac OS X**, locale **inglese**              |

> **Risposta sintetica**: si tratta di un browser della famiglia **Mozilla 5.0** (es. Firefox/Netscape) in esecuzione su **Mac OS X PowerPC**.

$$
\boxed{\;\text{Browser: Mozilla 5.0 su Mac OS X (PPC, locale en)}\;}
$$

Nota: la stringa "Mozilla/5.0" è oggi usata anche da Chrome, Safari, Edge, ecc., per ragioni storiche di compatibilità — chiamato fenomeno di **User-Agent string bloat** (cfr. RFC 9110 §10.1.5).

---

## Esercizio 3 — Download di una pagina con 4 immagini in HTTP/1.0 e HTTP/1.1

**Esercizio 3 (6 punti)** Un browser deve scaricare una pagina Web che contiene 4 immagini, due delle quali sono memorizzate sullo stesso server e due su un server diverso. MOSTRATE esplicitamente le richieste HTTP generate dal browser e le risposte dei server. Considerate sia l'utilizzo di HTTP 1.0 che 1.1.

> **Riferimenti di teoria**:
>
> - [M3/UD4/L1 — World Wide Web](../../M3_Protocolli_applicativi/UD4/L1%20-%20World%20Wide%20Web.md) (HTTP/1.0 = nuova TCP per ogni risorsa; HTTP/1.1 = persistente + pipelining)
> - [M3/UD4/L2 — HTTP](<../../M3_Protocolli_applicativi/UD4/L2%20-%20HTTP%20(HyperText%20Transfer%20Protocol).md>) (metodi GET, header)
> - [M3/UD4/L3 — WWW e HTTP](../../M3_Protocolli_applicativi/UD4/L3%20-%20WWW%20e%20HTTP.md) (richiesta–risposta)

### Inquadramento teorico

Una pagina HTML (es. `index.html`) referenzia altre risorse (immagini, fogli di stile, script). Il browser **deve aprire una richiesta HTTP per ogni risorsa**: i tag `<img>` non vengono "scaricati gratis" insieme all'HTML. Quante connessioni TCP servono dipende dalla versione del protocollo (cfr. M3/UD4/L1, §5–6).

| Versione     | Connessione TCP                        | Riutilizzo                                       | Pipelining                                      |
| ------------ | -------------------------------------- | ------------------------------------------------ | ----------------------------------------------- |
| **HTTP/1.0** | una **nuova** TCP per ogni oggetto     | no (salvo `Connection: keep-alive` non standard) | no                                              |
| **HTTP/1.1** | connessione **persistente** di default | sì                                               | sì (più request prima di attendere le response) |

### Topologia delle risorse

Sia `S1` il server che ospita HTML + 2 immagini (`img1`, `img2`) e `S2` quello che ospita le altre 2 (`img3`, `img4`):

```
   Client (browser)
       │
       ├──── S1 ────  index.html, img1, img2
       └──── S2 ────  img3, img4
```

In totale **5 oggetti** = 1 HTML + 4 immagini.

---

### Caso A — HTTP/1.0 (connessione non persistente)

Ogni oggetto richiede l'intero ciclo:

$$
\text{three-way handshake} \;\to\; \text{GET} \;\to\; \text{response} \;\to\; \text{FIN/ACK (close)}
$$

quindi `1+4 = 5` connessioni TCP — distribuite fra S1 (3) e S2 (2). I browser dell'epoca spesso aprivano fino a 4–6 connessioni TCP **in parallelo** verso lo stesso host per mitigare la latenza, ma concettualmente ogni richiesta è indipendente.

#### Sequenza delle richieste/risposte

```
Client                      S1 (di.unimi.it)              S2 (img.cdn.com)
──────                      ────────────────              ────────────────

(1) TCP a S1 #1
    ─── SYN ───────────────►
    ◄── SYN+ACK ───────────
    ─── ACK ───────────────►
    ─── GET /index.html HTTP/1.0 ─►
                          HTTP/1.0 200 OK
                          Content-Type: text/html
                          Content-Length: N
                          <html>…<img1>…<img2>…<img3>…<img4>…</html>
    ◄──────────────── (FIN, chiude)

   [browser fa il parsing dell'HTML e scopre 4 <img>]

(2) TCP a S1 #2
    ─── SYN ─► … ── GET /img1.jpg HTTP/1.0 ─► HTTP/1.0 200 OK + <jpeg> ── FIN

(3) TCP a S1 #3
    ─── SYN ─► … ── GET /img2.jpg HTTP/1.0 ─► HTTP/1.0 200 OK + <jpeg> ── FIN

(4) TCP a S2 #1
                                                ─── SYN ─► …
                                                ── GET /img3.png HTTP/1.0 ─►
                                                HTTP/1.0 200 OK + <png> ── FIN

(5) TCP a S2 #2
                                                ─── SYN ─► …
                                                ── GET /img4.png HTTP/1.0 ─►
                                                HTTP/1.0 200 OK + <png> ── FIN
```

#### Costo in RTT (modello semplice, senza parallelismo)

Indicando con `RTT` il round-trip time tra client e ciascun server, ogni transazione "vale" 2·RTT (1 per il three-way handshake + 1 per la coppia request/response):

$$
T_{HTTP/1.0} \;\approx\; \underbrace{2\cdot RTT_{S1}}_{\text{index.html}} \;+\; \underbrace{2\cdot 2\cdot RTT_{S1}}_{\text{img1,img2 seriali}} \;+\; \underbrace{2\cdot 2\cdot RTT_{S2}}_{\text{img3,img4 seriali}}
$$

= **6·RTT(S1) + 4·RTT(S2)** + tempo di trasferimento dei byte.

(Con il parallelismo classico — fino a 4 TCP simultanee per host — il costo si riduce praticamente a 2·RTT(S1) + 2·RTT(S2), perché le immagini di ciascun server scendono in parallelo).

---

### Caso B — HTTP/1.1 (connessione persistente, eventualmente con pipelining)

In HTTP/1.1 si aprono **al massimo 2 connessioni TCP**: una verso S1 (riusata per HTML + img1 + img2) e una verso S2 (riusata per img3 + img4).

#### Variante B.1 — Connessioni persistenti senza pipelining

Le richieste sono seriali sulla stessa TCP, ma senza chiudere e riaprire:

```
Client                    S1                                  S2
──────                    ──                                  ──

TCP S1 (persistente)
─── SYN/SYN+ACK/ACK ────►
─── GET /index.html HTTP/1.1
    Host: di.unimi.it
    Connection: keep-alive ─►
◄── HTTP/1.1 200 OK + <html> ────
                                              [parsing → trovate 4 img]

─── GET /img1.jpg HTTP/1.1
    Connection: keep-alive ─►
◄── HTTP/1.1 200 OK + <jpeg> ────

─── GET /img2.jpg HTTP/1.1
    Connection: close ─►        (ultima richiesta a S1)
◄── HTTP/1.1 200 OK + <jpeg> ──── FIN

TCP S2 (persistente)
─── SYN/SYN+ACK/ACK ──────────────────────────────────────────►
─── GET /img3.png HTTP/1.1
    Host: img.cdn.com
    Connection: keep-alive ─────────────────────────────────►
◄────────────────────────────────────────────── HTTP/1.1 200 OK + <png>

─── GET /img4.png HTTP/1.1
    Connection: close ──────────────────────────────────────►
◄────────────────────────────────────────────── HTTP/1.1 200 OK + <png>  FIN
```

#### Variante B.2 — Connessioni persistenti con pipelining

Il client invia subito **tutte le richieste** sulla stessa TCP, senza attendere le response (RFC 2616 §8.1.2.2). Il server _deve_ rispondere nello stesso ordine.

```
Client                    S1                                  S2
──────                    ──                                  ──

TCP S1 (persistente)
─── SYN/SYN+ACK/ACK ────►
─── GET /index.html HTTP/1.1 ─►
◄── HTTP/1.1 200 OK + <html> ──

   (parsing immediato: il browser scopre img1, img2, img3, img4)

─── GET /img1.jpg HTTP/1.1 ────►
─── GET /img2.jpg HTTP/1.1 ────►        ← pipelining
◄── HTTP/1.1 200 OK + <img1> ──
◄── HTTP/1.1 200 OK + <img2> ── FIN

TCP S2 (persistente, in parallelo)
─── SYN/SYN+ACK/ACK ──────────────────────────────────────────►
─── GET /img3.png HTTP/1.1 ───────────────────────────────────►
─── GET /img4.png HTTP/1.1 ───────────────────────────────────►   ← pipelining
◄──────────────────────────────────────────── HTTP/1.1 200 OK + <img3>
◄──────────────────────────────────────────── HTTP/1.1 200 OK + <img4>  FIN
```

#### Costo in RTT (HTTP/1.1, persistente, S1 e S2 in parallelo)

- TCP setup verso S1 e S2 in parallelo: **1·RTT** (il maggiore dei due).
- Richiesta dell'HTML e parse: **1·RTT(S1)**.
- Con pipelining, le 2 immagini di S1 e le 2 di S2 sono già richieste subito dopo l'HTML, e tornano in un altro RTT: **1·RTT**.

$$
T_{HTTP/1.1,\,pipeline} \;\approx\; 1\cdot RTT_{setup} \;+\; 1\cdot RTT_{html} \;+\; 1\cdot RTT_{immagini}
\;=\; \mathbf{3\cdot RTT}
$$

Confronto:

| Schema                                              | Connessioni TCP | RTT totali (ottimistici) |
| --------------------------------------------------- | --------------: | -----------------------: |
| HTTP/1.0 seriale                                    |               5 |                  ~10·RTT |
| HTTP/1.0 con parallelismo browser (4 conn per host) |   5 (parallele) |                   ~4·RTT |
| HTTP/1.1 persistente, senza pipelining              |               2 |                   ~5·RTT |
| **HTTP/1.1 persistente, con pipelining**            |               2 |               **~3·RTT** |

Si vede chiaramente perché HTTP/1.1 rappresenta un **salto di prestazioni** rispetto a HTTP/1.0 (cfr. M3/UD4/L1, §6): meno connessioni TCP, meno handshake, niente _slow start_ TCP ripetuto, e — con pipelining — la latenza si comprime ulteriormente.

---

## Esercizio 4 — Regola di firewall: solo proxy → TCP esterno, UDP disabilitato

**Esercizio 4 (4 punti)** Siete incaricati di impostare il firewall per la rete di bordo dell'Esercizio 1. Scrivete la regola in base alla quale solo l'indirizzo IP del proxy può avere connessioni TCP con l'esterno, mentre UDP è disabilitato. Specificate la regola da utilizzare.

> **Riferimenti di teoria**:
>
> - [M2/UD2/L1 — Internet Protocol (IP)](<../../M2_Protocolli_rete_TCP_IP/UD2/L1%20-%20Internet%20Protocol%20(IP).md>) (filtering basato su IP/protocollo)
> - [M2/UD6/L2 — UDP e TCP a confronto](../../M2_Protocolli_rete_TCP_IP/UD6/L2%20-%20UDP%20e%20TCP%20a%20confronto.md) (numeri di protocollo: TCP=6, UDP=17)
> - [M2/UD4/L1 — Indirizzi pubblici e privati](../../M2_Protocolli_rete_TCP_IP/UD4/L1%20-%20Indirizzi%20pubblici%20e%20privati.md) (politica di filtering al gateway)
> - Cfr. anche soluzione "[Esercizio 3 (4 pt) — Regola di firewall TCP](../esame_23_maggio_2025/SoluzioneParteA.md#esercizio-3-4-pt--regola-di-firewall-tcp)" del 23/05/2025 per il pattern stateless/stateful

### Inquadramento

Lo scenario è quello dell'Esercizio 1: una LAN privata "di bordo" con

- molti **client** con IP privati (assegnati via DHCP),
- un **proxy** con IP privato fisso (es. `10.0.0.1`),
- un **gateway/firewall** verso Internet che applica le regole di filtering.

La policy richiesta è:

1. **TCP esterno**: ammesso **solo** se la sorgente locale è il proxy.
2. **UDP esterno**: completamente vietato (nessun host, neppure il proxy).
3. Implicito: il traffico interno alla LAN resta libero (i client parlano con il proxy in TCP).

Sia `PROXY_IP = 10.0.0.1` (IP privato del proxy, da configurare).
Sia `eth_int` l'interfaccia del firewall verso la LAN di bordo, `eth_ext` quella verso Internet.

### Regola (linguaggio neutro)

```
FORWARD  (LAN → Internet, da eth_int a eth_ext):
    ACCEPT  se  protocol == TCP  AND  src_ip == 10.0.0.1
    DROP    se  protocol == TCP  AND  src_ip != 10.0.0.1
    DROP    se  protocol == UDP                       (per qualunque src)
    ACCEPT  altrimenti (es. ICMP, opzionale)

FORWARD  (Internet → LAN, da eth_ext a eth_int):
    ACCEPT  se  protocol == TCP  AND  dst_ip == 10.0.0.1
                                  AND  stato == ESTABLISHED  (firewall stateful)
    DROP    se  protocol == UDP
    DROP    altrimenti
```

### Implementazione con `iptables` (Linux netfilter)

```bash
# ─── Variabili ────────────────────────────────────────────────────────
PROXY_IP=10.0.0.1
LAN_NET=10.0.0.0/24
IF_INT=eth0          # verso la LAN di bordo
IF_EXT=eth1          # verso il satellite/uplink Internet

# ─── 1. Policy di default: nega tutto in forward ─────────────────────
iptables -P FORWARD DROP

# ─── 2. Ammetti TCP uscente SOLO dal proxy ───────────────────────────
iptables -A FORWARD -i $IF_INT -o $IF_EXT \
         -s $PROXY_IP -p tcp -j ACCEPT

# ─── 3. Permetti il ritorno (risposte TCP al proxy) ──────────────────
# usa connection tracking per ammettere SOLO segmenti di connessioni
# avviate dal proxy
iptables -A FORWARD -i $IF_EXT -o $IF_INT \
         -d $PROXY_IP -p tcp \
         -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# ─── 4. Blocca esplicitamente UDP in entrambe le direzioni ───────────
iptables -A FORWARD -p udp -j DROP

# ─── 5. (Opzionale) Tutto il resto è già negato dalla policy DROP ────
```

> **Nota sul match `-p tcp` senza `--syn`**: vogliamo ammettere **tutta** la connessione, non solo il `SYN` iniziale. Per impedire che client non-proxy possano spedire pacchetti TCP con sorgente "forgiata" pari all'IP del proxy, è opportuno aggiungere `-i $IF_INT -m mac --mac-source <MAC_proxy>` o usare _port security_ sullo switch (cfr. M1/UD2/L6 — Switched Ethernet).

### Forma sintetica della regola (come potrebbe essere scritta in compito)

```text
FORWARD chain:
  -i eth_int -o eth_ext -s 10.0.0.1 -p tcp                 -j ACCEPT
  -i eth_ext -o eth_int -d 10.0.0.1 -p tcp -m conntrack
                                       --ctstate ESTABLISHED,RELATED -j ACCEPT
  -p udp                                                   -j DROP
  default                                                  -j DROP
```

### Spiegazione "in chiaro"

| Direzione      | Sorgente                  | Protocollo | Decisione         |
| -------------- | ------------------------- | ---------- | ----------------- |
| LAN → Internet | `10.0.0.1` (proxy)        | TCP        | **ACCEPT**        |
| LAN → Internet | qualunque altro           | TCP        | DROP              |
| LAN → Internet | qualunque                 | UDP        | **DROP**          |
| Internet → LAN | risposta TCP al proxy     | TCP        | ACCEPT (stateful) |
| Internet → LAN | nuovo TCP non sollecitato | TCP        | DROP              |
| Internet → LAN | qualunque                 | UDP        | **DROP**          |

Questa configurazione coincide con il pattern "**out OK solo se è il proxy, in solo se è risposta al proxy**", versione più restrittiva del firewall residenziale standard discusso a pagina 757 della soluzione del 23/05/2025.

---

---
