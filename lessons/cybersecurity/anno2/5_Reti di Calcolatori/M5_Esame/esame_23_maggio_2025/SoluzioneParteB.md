# Soluzione Esame di Reti di Calcolatori — Parte B — 23/05/2025

## Esercizio 1 — Chat UDP fra due peer con fork()

**Esercizio 1 (12 punti)** Fornite lo pseudocodice di un programma che permetta a due utenti di comunicare tra loro in tempo reale utilizzando il protocollo UDP.

**Porta locale**: definita dall'utente.
**Porta remota**: indirizzo IP e porta dell'altro peer.

- Ciascuno dei due peer deve poter **inviare e ricevere** messaggi.
- I messaggi devono essere visualizzati in tempo reale nella console di ciascun peer.
- Bonus: Utilizzate **multiprocessing con chiamata fork()** per gestire l'invio e la ricezione simultanei.
- **Uscita**: Digitando exit, il programma termina.

> **Riferimento di teoria**: [M4/UD1/L4 — Funzioni di comunicazione via socket](../../M4_Tecniche_programmazione_distribuita/UD1/L4%20-%20Funzioni%20di%20comunicazione%20via%20socket.md) (`sendto`/`recvfrom`) · [M4/UD1/L2 — Creazione di un socket](../../M4_Tecniche_programmazione_distribuita/UD1/L2%20-%20Creazione%20di%20un%20socket.md)

### Inquadramento teorico

Per una comunicazione peer-to-peer in tempo reale UDP è la scelta naturale:

- non occorre fase di handshake (`connect`),
- ogni `sendto`/`recvfrom` opera su datagrammi indipendenti,
- la latenza è minima.

Per gestire **invio e ricezione simultanei** su un singolo socket sono possibili tre approcci canonici:
S
1. **`select()`** o **`poll()`** su un unico processo (multiplexing I/O);
2. **Multithreading**;
3. **`fork()`** che genera due processi figli che condividono il descrittore di socket.

Il testo richiede la terza soluzione. Dopo `fork()`, **padre e figlio condividono il socket** ($\Rightarrow$ entrambi possono leggere e scrivere senza interferenze, perché `recvfrom` e `sendto` operano in direzioni diverse). Il padre gestisce l’invio (da `stdin`), il figlio gestisce la ricezione (verso `stdout`).

### Pseudocodice

```c
#define BUF 4096

int main(int argc, char *argv[]) {
    int   local_port  = atoi(argv[1]);
    char *remote_ip   = argv[2];
    int   remote_port = atoi(argv[3]);

    int sockfd = socket(AF_INET, SOCK_DGRAM, 0);

    struct sockaddr_in local_addr;
    bzero((char*)&local_addr, sizeof(local_addr));
    local_addr.sin_family      = AF_INET;
    local_addr.sin_port        = htons(local_port);
    local_addr.sin_addr.s_addr = INADDR_ANY;
    bind(sockfd, (struct sockaddr*)&local_addr, sizeof(local_addr));

    struct sockaddr_in peer_addr;
    bzero((char*)&peer_addr, sizeof(peer_addr));
    peer_addr.sin_family      = AF_INET;
    peer_addr.sin_port        = htons(remote_port);
    peer_addr.sin_addr.s_addr = inet_addr(remote_ip);

    if (fork() == 0) {          /* FIGLIO: ricezione */
        char buf[BUF];
        struct sockaddr_in from_addr;
        int from_len = sizeof(from_addr);
        while (1) {
            int n = recvfrom(sockfd, buf, sizeof(buf)-1, 0,
                             (struct sockaddr*)&from_addr, &from_len);
            buf[n] = '\0';
            printf("[%s] %s\n", inet_ntoa(from_addr.sin_addr), buf);
        }
    } else {                    /* PADRE: invio da stdin */
        signal(SIGTERM, cleanExit);
        signal(SIGINT,  cleanExit);
        char line[BUF];
        while (fgets(line, sizeof(line), stdin)) {
            if (strncmp(line, "exit", 4) == 0) { close(sockfd); exit(0); }
            sendto(sockfd, line, strlen(line), 0,
                   (struct sockaddr*)&peer_addr, sizeof(peer_addr));
        }
    }
    return 0;
}
void cleanExit(int sig) { exit(0); }
```

### Note chiave

- **`bind()` obbligatorio lato ricezione**: serve per fissare la porta locale; il peer deve sapere su quale porta mandare (M4/UD1/L2).
- **Socket condiviso tra fork**: figlio usa `recvfrom`, padre usa `sendto` sullo stesso `sockfd` — non collidono perché direzioni opposte.

---

## Esercizio 2 — UDP vs TCP, sicurezza, multipeer

**Esercizio 2 (6 punti)** Con riferimento all'esercizio 1, rispondete alle seguenti domande:

1. Quali sono i vantaggi e gli svantaggi dell'uso di UDP rispetto a TCP in questo contesto?
2. Come potreste migliorare la sicurezza della comunicazione?
3. Come potreste estendere il programma per supportare più peer?

> **Riferimento di teoria**: [M2/UD6/L2 — UDP e TCP a confronto](../../M2_Protocolli_rete_TCP_IP/UD6/L2%20-%20UDP%20e%20TCP%20a%20confronto.md)

### 1. Vantaggi e svantaggi di UDP rispetto a TCP nel contesto della chat

| Aspetto | TCP | UDP |
|---|---|---|
| Connessione | 3-way handshake | nessuno |
| Affidabilità | consegna garantita, ordinata, deduplicata | best-effort |
| Controllo di flusso | sì (RcvWindow) | no |
| Controllo di congestione | sì (Slow Start + AIMD) | no |
| Header | 20 byte (min) | 8 byte |
| Latenza setup | RTT ≥ 1 | 0 |
| Multicast/Broadcast | no (1-a-1) | sì |
| Framing | stream continuo (Nagle) | preserva i confini dei datagrammi |

**Vantaggi di UDP nella chat P2P**:

- **Latenza minima**: niente handshake, niente attesa di ACK ⇒ il messaggio compare “in tempo reale”.
- **Header leggero**: 8 byte contro 20+, importante per messaggi corti (analogo al ragionamento sui flussi interattivi visto in `M2/UD5/L5`).
- **Confini di messaggio preservati**: un `sendto(line)` corrisponde a un `recvfrom(line)` lato peer ⇒ niente parsing custom.
- **Multicast nativo**: facilita un’evoluzione verso chat di gruppo.
- **Semplicità**: non occorre `listen()`/`accept()`, riduce il codice e i potenziali bug.

**Svantaggi**:

- **Nessuna garanzia di consegna**: un messaggio perso non viene ritrasmesso ⇒ l’utente non sa se l’altro l’ha letto.
- **Nessun ordinamento**: i messaggi possono essere visualizzati nell’ordine sbagliato.
- **Nessuna deduplicazione**: un datagramma può apparire due volte.
- **MTU**: messaggi più lunghi di $\sim 1500$ byte vengono frammentati a livello IP; perdita di un solo frammento $\Rightarrow$ perdita dell’intero datagramma.
- **NAT/firewall**: i datagrammi UDP attraversano i NAT con più difficoltà rispetto a TCP (timeout dei mapping più brevi).

In una chat reale UDP è accettabile per messaggi *fire-and-forget* e per la presenza online (typing indicator, heartbeat). Se serve garanzia di consegna conviene aggiungere un piccolo protocollo applicativo sopra UDP (numero di sequenza + ACK + ritrasmissione) — è il pattern del **middleware** su UDP.

### 2. Miglioramento della sicurezza

La chat dell’Esercizio 1 viaggia **in chiaro** e **non autenticata**. Possibili contromisure, in ordine di efficacia crescente:

| Livello | Tecnica | Cosa protegge |
|---|---|---|
| Trasporto | **DTLS** (TLS over UDP, RFC 6347/9147) | confidenzialità + integrità + autenticazione del canale |
| Rete | **IPsec** (AH + ESP) | confidenzialità/integrità a livello IP, trasparente all’applicazione |
| VPN | tunnel **WireGuard / OpenVPN** | incapsula tutto il traffico fra i peer |
| Applicazione | cifratura **end-to-end** con AEAD (es. `libsodium`: `crypto_secretbox` o `crypto_box`) + scambio chiavi **Diffie-Hellman** | confidenzialità + integrità + autenticazione |
| Identità | **certificati X.509** o **chiavi pubbliche** firmate | autentica i peer, blocca *man-in-the-middle* |
| Replay | **numero di sequenza + nonce + timestamp** | impedisce di rigiocare datagrammi catturati |

Esempio di pacchetto applicativo sicuro:

$$
\text{datagram} \;=\; \underbrace{\text{seq} \;\|\; \text{nonce}}_{\text{12 byte}} \;\|\; \underbrace{\text{AES-GCM}_{k}(\text{plaintext})}_{\text{ciphertext + tag}}
$$

Considerazioni aggiuntive: chiave $k$ derivata da scambio DH **all’avvio della sessione**, rotazione periodica, **PFS** (Perfect Forward Secrecy), validazione dell’indirizzo del peer (vincolare `recvfrom` a *peer noti*), eventuale firewall (Esercizio 3 di seguito).

### 3. Estensione a più peer

Tre topologie possibili:

**(a) Mesh full P2P** — ogni peer mantiene la lista `peers[] = {(ip, porta), …}`. All’invio si itera:

```c
/* invia msg a tutti i peer della lista — O(n) sendto() */
void broadcast(char *msg, int sockfd,
               struct sockaddr_in peers[], int n_peers) {
    for (int i = 0; i < n_peers; i++)
        sendto(sockfd, msg, strlen(msg), 0,
               (struct sockaddr *)&peers[i], sizeof(peers[i]));
}
```

Costi: $O(n)$ datagrammi per messaggio, ogni peer deve conoscere tutti gli altri (problema di **discovery**).

**(b) Multicast UDP** — si assegna un indirizzo di gruppo di classe D (224.0.0.0–239.255.255.255). Ogni peer deve iscriversi al gruppo tramite una chiamata di gestione SO (fuori dall'API base della Socket Library insegnata nel corso). Una volta iscritto, il peer riceve automaticamente tutti i datagrammi inviati all'indirizzo di gruppo. L'invio è un semplice `sendto()` verso quell'indirizzo — M4/UD1/L4:

```c
/* invio multicast: un solo sendto raggiunge tutti i peer iscritti al gruppo */
struct sockaddr_in grp_addr;
bzero((char *)&grp_addr, sizeof(grp_addr));
grp_addr.sin_family      = AF_INET;
grp_addr.sin_port        = htons(port);
grp_addr.sin_addr.s_addr = inet_addr("239.1.2.3");
sendto(sockfd, msg, strlen(msg), 0,
       (struct sockaddr *)&grp_addr, sizeof(grp_addr));
```

Vantaggi: **scalabilità** ($1$ datagramma indipendentemente dal numero di destinatari). Svantaggi: il multicast non è instradato sull’Internet pubblica (resta confinato alla LAN o richiede infrastruttura PIM).

**(c) Server di rendezvous** — un processo server centrale tiene la lista dei peer connessi e *ribradiata* i messaggi. Ogni peer parla solo con il server, che è anche utile per **NAT traversal** (STUN/TURN) e per la persistenza dei messaggi offline. È l’architettura usata da IRC, XMPP, Slack, Discord, ecc.

Per una piccola chat di gruppo su LAN la soluzione (b) è elegante; per una chat su Internet è realistica solo la (c).

---

## Esercizio 3 (8 pt) — Query DNS non autoritativa

**Esercizio 3 (8 punti)** Avete inviato una query DNS di tipo A (IPv4) verso un server *non-autoritativo* (es. 8.8.8.8 di Google) per ottenere informazioni sul dominio Internet a vostra scelta (es. example.com, un.org, ecc.). Fornite e commentate le risposte ricevute. Da dove si evince che la risposta non è autoritativa? Perché è utile sapere se una risposta DNS è autoritativa?

> **Riferimento di teoria**: [M3/UD1/L4 — Risoluzione dei nomi](../../M3_Protocolli_applicativi/UD1/L4%20-%20Risoluzione%20dei%20nomi.md) (resolver, RR, cache vs autoritativo) · [M3/UD1/L2 — Caratteristiche del DNS](../../M3_Protocolli_applicativi/UD1/L2%20-%20Caratteristiche%20del%20DNS.md)

### Inquadramento teorico

Il **DNS** è un sistema distribuito: ogni zona è gestita da almeno un **name server autorevole** (record `NS`). Un name server è **autoritativo per una zona** se la sua copia è il *master* o uno *slave* sincronizzato e marcato come tale.

I resolver pubblici come `8.8.8.8` (Google) **non sono autoritativi** per `example.com`: si limitano a inoltrare la query (o a servirla dalla loro cache). Il flag che distingue le due risposte è il bit **AA** (*Authoritative Answer*) presente nell’header del messaggio DNS (RFC 1035, §4.1.1).

| Flag | Significato |
|---|---|
| `qr` | risposta (vs query) |
| `aa` | **Authoritative Answer** — la risposta proviene dal name server autoritativo per la zona |
| `tc` | TrunCated — risposta troncata |
| `rd` | Recursion Desired — il client chiede la ricorsione |
| `ra` | Recursion Available — il server offre ricorsione |
| `ad` | Authentic Data (DNSSEC) — risposta validata DNSSEC |
| `cd` | Checking Disabled (DNSSEC) |

### Query e risposta commentata

Eseguendo:

```bash
dig @8.8.8.8 example.com A
```

L’output tipico è:

```dns
; <<>> DiG 9.18.0 <<>> @8.8.8.8 example.com A
; (1 server found)
;; global options: +cmd
;; Got answer:
;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 17924
;; flags: qr rd ra; QUERY: 1, ANSWER: 6, AUTHORITY: 0, ADDITIONAL: 1
                ^^^^^^^^^
                ⟵ NB: NESSUN flag "aa"

;; OPT PSEUDOSECTION:
; EDNS: version: 0, flags:; udp: 1232
;; QUESTION SECTION:
;example.com.                   IN      A

;; ANSWER SECTION:
example.com.            136     IN      A       23.220.75.232
example.com.            136     IN      A       23.220.75.245
example.com.            136     IN      A       23.215.0.136
example.com.            136     IN      A       23.215.0.138
example.com.            136     IN      A       96.7.128.198
example.com.            136     IN      A       96.7.128.175

;; Query time: 28 msec
;; SERVER: 8.8.8.8#53(8.8.8.8) (UDP)
;; WHEN: Fri May 16 12:00:00 UTC 2026
;; MSG SIZE  rcvd: 135
```

#### Lettura riga per riga

- `opcode: QUERY` ⇒ il messaggio è una query DNS standard.
- `status: NOERROR` ⇒ la risoluzione è riuscita.
- `flags: qr rd ra;` ⇒ sono settati **`qr`** (è una risposta), **`rd`** (l’abbiamo chiesta ricorsiva), **`ra`** (il server la offre).
  **Manca `aa`**: la risposta **non è autoritativa**.
- `ANSWER SECTION` ⇒ contiene 6 record `A` (IPv4) per `example.com` — il dominio è servito in *load balancing* su 6 indirizzi.
- Il campo `TTL = 136` (anziché un valore “rotondo” come $3600$, $7200$, …) indica che il record sta **scadendo nella cache**: era originariamente più alto ed è stato decrementato dal cache server — ulteriore segnale di risposta cached.
- `AUTHORITY: 0` ⇒ il server non ha incluso i record `NS` autoritativi della zona (sarebbero stati `a.iana-servers.net.`, `b.iana-servers.net.`).

### Da dove si evince che la risposta NON è autoritativa

1. **Header DNS — assenza del flag `aa`**: è la prova formale, presa direttamente dal byte di flag della risposta (bit 5 del secondo ottetto dell’header, RFC 1035).
2. In `nslookup` la stessa cosa appare come riga esplicita:
   ```
   Server:        8.8.8.8
   Address:       8.8.8.8#53

   Non-authoritative answer:
   Name:   example.com
   Address: 23.220.75.232
   ```
3. **TTL non-rotondo**: rivela che il record proviene da una cache (è stato decrementato del tempo trascorso dal fetch).
4. **Server interrogato**: `8.8.8.8` non compare nei record `NS` di `example.com` (i NS autorevoli sono `a.iana-servers.net.` e `b.iana-servers.net.`); per ottenere una risposta autoritativa bisognerebbe interrogare uno di questi:
   ```bash
   dig @a.iana-servers.net example.com A
   ;; flags: qr aa rd;          ← flag "aa" presente
   ```

### Perché è utile sapere se una risposta è autoritativa

| Motivazione | Spiegazione |
|---|---|
| **Freshness** | Una risposta autorevole rispecchia lo stato attuale della zona; una cached può essere obsoleta (fino al TTL residuo). Cruciale per chi ha appena aggiornato un record (es. cambio IP di un server). |
| **Troubleshooting** | In caso di errori di propagazione DNS bisogna confrontare ciò che dice il NS autoritativo con ciò che restituiscono i resolver — se differiscono, è in corso una propagazione. |
| **DNS hijacking / cache poisoning** | Le risposte cached sono il principale bersaglio degli attacchi di cache poisoning. Sapere se la risposta è autorevole aiuta a distinguere fra cache compromessa e zona compromessa. |
| **DNSSEC** | Solo i NS autoritativi pubblicano i record `RRSIG`. La validazione DNSSEC parte dalla risposta firmata dell’autoritativo. |
| **Security / forensics** | In contesti come *split-horizon DNS*, server autorevoli interni rispondono diversamente da quelli pubblici: per audit serve sapere quale fonte ha risposto. |
| **CDN / load balancing geo-DNS** | I CDN restituiscono IP diversi a seconda della località del resolver; sapere chi ha risposto (autorevole o cache locale) aiuta a interpretare i risultati. |

---

## Esercizio 3 (4 pt) — Regola di firewall TCP

**Esercizio 3 (4 punti)** Siete i gestori del firewall di una rete. Volete impedire ai vostri utenti di rispondere a richieste di connessione TCP generate all'esterno, mantenendo però la loro possibilità di connettersi a server su Internet e ricevere le relative risposte. Specificate la regola da utilizzare.

> **Riferimento di teoria**: [M2/UD2/L1 — Internet Protocol (IP)](../../M2_Protocolli_rete_TCP_IP/UD2/L1%20-%20Internet%20Protocol%20(IP).md) (§1.6 instradamento e filtraggio dei pacchetti)

### Inquadramento

Una connessione TCP è instaurata con un **three-way handshake**:

```
Client                            Server
  │  ── SYN ───────────────────►   │
  │  ◄── SYN+ACK ───────────────   │
  │  ── ACK ────────────────────►   │
  │     ─── connessione attiva ──   │
```

Il **primo segmento** di una connessione è l’unico che porta il flag `SYN` settato e il flag `ACK` **non** settato (`SYN=1, ACK=0`). I segmenti successivi, sia in invio sia in ricezione, hanno **sempre `ACK=1`**.

> **Conseguenza operativa**: per impedire connessioni *iniziate dall’esterno*, basta scartare i pacchetti con `SYN=1 AND ACK=0` provenienti da Internet, senza toccare gli altri. Le risposte ai *nostri* SYN (cioè i SYN+ACK del server remoto) hanno `SYN=1 AND ACK=1` e quindi **non vengono colpite** dalla regola.

Un firewall che ragiona in questo modo è detto **stateless**; può comunque distinguere connessioni nuove da quelle in corso grazie ai flag TCP.

Un firewall **stateful** (detto anche *connection-tracking*) tiene una tabella delle connessioni attive e permette di esprimere la stessa politica in modo più diretto e più sicuro (copre anche segmenti riassemblati o casi limite).

### Soluzione stateless

Linguaggio neutro:

```text
INPUT  (interfaccia esterna):
    DROP    se  protocol == TCP  AND  flag SYN = 1  AND  flag ACK = 0
    ACCEPT  altrimenti
OUTPUT (interfaccia esterna):
    ACCEPT
```

Espressione `iptables` (Linux, netfilter):

```bash
# blocca i SYN (senza ACK) che arrivano dall'esterno
iptables -A INPUT  -i eth0 -p tcp --syn -j DROP

# tutto il resto (incluso SYN+ACK) è ammesso
iptables -A INPUT  -i eth0 -p tcp -j ACCEPT

# il traffico in uscita è libero
iptables -A OUTPUT -o eth0 -p tcp -j ACCEPT
```

> Il match `--syn` di iptables è l’abbreviazione di `--tcp-flags FIN,SYN,RST,ACK SYN`, ovvero “SYN settato, tutti gli altri (FIN, RST, ACK) azzerati”.

### Soluzione stateful (consigliata in produzione)

```bash
# default policy: blocco tutto in ingresso
iptables -P INPUT  DROP
iptables -P FORWARD DROP
iptables -P OUTPUT ACCEPT

# il loopback è sempre lecito
iptables -A INPUT -i lo -j ACCEPT

# accetto solo pacchetti che appartengono a connessioni
# AVVIATE DA NOI (ESTABLISHED) o correlate (RELATED, es. errori ICMP)
iptables -A INPUT  -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# qualunque cosa NEW in ingresso (incluso SYN) viene scartata implicitamente
# dalla policy di default
```

Cosa accade in concreto:

| Evento | Pacchetto | Stato conntrack | Esito |
|---|---|---|---|
| Esterno tenta `telnet ip 22` | `SYN` (ext → noi) | NEW | **DROP** |
| Noi facciamo `curl https://server.com` | `SYN` (noi → ext) | NEW (OUTPUT) | ACCEPT |
| Risposta del server | `SYN+ACK` (ext → noi) | ESTABLISHED | **ACCEPT** |
| Continuazione | `ACK`, `PSH+ACK`, … | ESTABLISHED | ACCEPT |
| Chiusura | `FIN+ACK` | ESTABLISHED | ACCEPT |

### Sintesi della regola richiesta

> **Regola**: scartare in ingresso ogni segmento TCP con `SYN=1, ACK=0` (ovvero **ogni primo segmento di una nuova connessione** entrante); accettare tutti gli altri segmenti TCP, sia in ingresso (es. `SYN+ACK`, `ACK`) sia in uscita.
>
> In termini *stateful*: `iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT`, con policy di `INPUT` impostata a `DROP`.

Questa è una delle politiche più diffuse: **“out OK, in solo se è risposta a un nostro out”** — è la base concettuale del NAT residenziale e del modello di sicurezza della maggior parte dei firewall personali.

---

## Riferimenti al corso

Per ogni esercizio, i materiali di studio rilevanti del modulo:

- TCP CWND / SSTHRESH / Slow Start / Congestion Avoidance: `M2/UD5/L8`, `M2/UD5/L9`, `M2/UD5/L10`, `M2/UD5/L11`.
- UDP, header, confini di messaggio: `M2/UD6/L1`, `M2/UD6/L2`.
- Subnetting, FLSM/VLSM, routing table, ARP: `M2/UD2/L1`, `M2/UD2/L2`, `M2/UD2/L4`, `M2/UD3/L3`.
- Socket UDP, `fork()`, comunicazione client-server: `M4/UD1/L2`, `M4/UD1/L3`, `M4/UD1/L4`.
- DNS, resolver, autoritativo vs cache: `M3/UD1/L2`, `M3/UD1/L4`.
- TCP setup / flag SYN / firewall: `M2/UD5/L5_1`, `M4/UD1/L3`.

---

*Fine soluzione.*

---
