
# Compito B

## Esercizio 1 — Chat UDP fra due peer con fork()

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

```python
# ───────────────────────────────────────────────────────────────────
# peer_chat.py — chat UDP P2P con fork()
# Avvio: ./peer_chat <local_port> <remote_ip> <remote_port>
# ───────────────────────────────────────────────────────────────────

CONST BUF_SIZE = 4096
CONST EXIT_CMD = "exit"

function main(argv):

    # 1) Parsing argomenti -------------------------------------------
    local_port  = int(argv[1])                  # porta locale (bind)
    remote_ip   = argv[2]                       # IP del peer remoto
    remote_port = int(argv[3])                  # porta del peer remoto

    # 2) Creazione e bind del socket UDP -----------------------------
    sock = socket(AF_INET, SOCK_DGRAM)
    bind(sock, ("0.0.0.0", local_port))         # ascolta su tutte le NIC

    print("[INFO] In ascolto su porta " + local_port +
          ", peer = " + remote_ip + ":" + remote_port)

    # 3) Fork: split del processo ------------------------------------
    pid = fork()

    if pid < 0:
        print("[ERRORE] fork fallito")
        close(sock); exit(1)

    # -----------------------------------------------------------------
    # 4) PROCESSO FIGLIO  →  ricezione asincrona
    # -----------------------------------------------------------------
    if pid == 0:                                # figlio
        while true:
            (data, from_addr) = recvfrom(sock, BUF_SIZE)   # bloccante
            if data == EMPTY: continue
            msg = decode_utf8(data)
            print("\n[" + from_addr.ip + ":" + from_addr.port + "] "
                  + msg + "\n> ")
            # NB: non rispondiamo automaticamente; spetta all'utente.
        # mai raggiunto: il figlio termina via signal dal padre

    # -----------------------------------------------------------------
    # 5) PROCESSO PADRE  →  invio da stdin
    # -----------------------------------------------------------------
    else:                                       # padre  (pid > 0)
        install_signal_handler(SIGCHLD, on_child_exit)

        while true:
            print("> ", no_newline=true)
            line = read_line(stdin)             # bloccante su tastiera

            if line == EXIT_CMD:
                # terminazione ordinata
                kill(pid, SIGTERM)              # ferma il figlio
                waitpid(pid)                    # raccoglie lo zombie
                close(sock)
                print("[INFO] Chat terminata.")
                exit(0)

            payload = encode_utf8(line)
            sendto(sock, payload, (remote_ip, remote_port))

# Handler invocato se il figlio muore inaspettatamente -----------------
function on_child_exit(signal):
    waitpid(ANY_CHILD, WNOHANG)
    print("[WARN] Processo di ricezione terminato.")
    exit(1)

main(argv)
```

### Punti di robustezza messi in chiaro

- **Bind solo lato locale**: UDP non ha `connect` obbligatorio. Si fa `bind` sulla porta locale per ricevere; in invio è il sistema operativo a scegliere la porta sorgente, ma qui usiamo la stessa porta sia in entrata sia in uscita (semplifica il `recvfrom` lato peer).
- **Condivisione del socket**: il descrittore restituito da `socket()` è ereditato attraverso `fork()` ed è condiviso fra i due processi. Le primitive `recvfrom` (figlio) e `sendto` (padre) non collidono perché operano in direzioni opposte sul medesimo handle.
- **Terminazione pulita**: il padre invia `SIGTERM` al figlio e attende con `waitpid()` per evitare processi *zombie*. La gestione di `SIGCHLD` copre il caso opposto (crash del figlio).
- **Tempo reale**: il `recvfrom` nel figlio stampa appena arriva il datagramma (no buffer in attesa) — vincolo del testo.
- **Esuriente per UDP**: nessun controllo di sequenza, niente ritrasmissione: se un messaggio si perde, è perso. Questo limite è accettabile per una chat semplice.

---

## Esercizio 2 — UDP vs TCP, sicurezza, multipeer

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

```python
function broadcast(msg):
    for p in peers:
        sendto(sock, msg, (p.ip, p.port))
```

Costi: $O(n)$ datagrammi per messaggio, ogni peer deve conoscere tutti gli altri (problema di **discovery**).

**(b) Multicast UDP** — si assegna un indirizzo di classe D (224.0.0.0 – 239.255.255.255), tutti i peer eseguono `setsockopt(IP_ADD_MEMBERSHIP, group)` e ricevono ogni datagramma inviato sul gruppo.

```python
group_addr = "239.1.2.3"
sock.setsockopt(IPPROTO_IP, IP_ADD_MEMBERSHIP, group_addr + iface_addr)
# invio: un solo sendto a (group_addr, port)
```

Vantaggi: **scalabilità** ($1$ datagramma indipendentemente dal numero di destinatari). Svantaggi: il multicast non è instradato sull’Internet pubblica (resta confinato alla LAN o richiede infrastruttura PIM).

**(c) Server di rendezvous** — un processo server centrale tiene la lista dei peer connessi e *ribradiata* i messaggi. Ogni peer parla solo con il server, che è anche utile per **NAT traversal** (STUN/TURN) e per la persistenza dei messaggi offline. È l’architettura usata da IRC, XMPP, Slack, Discord, ecc.

Per una piccola chat di gruppo su LAN la soluzione (b) è elegante; per una chat su Internet è realistica solo la (c).

---

## Esercizio 3 (8 pt) — Query DNS non autoritativa

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

## Appendice — Legenda flag `iptables`

> 📌 `iptables` è lo strumento Linux per configurare le regole di filtraggio del kernel (netfilter). Opera su **tabelle** (filter, nat, mangle…); la tabella di default è **filter**, quella usata in tutti gli esercizi di questo corso.

---

### 1. Struttura di un comando iptables

```
iptables  [-t tabella]  <COMANDO>  <CATENA>  [opzioni di match]  -j <TARGET>
```

---

### 2. Comandi principali (cosa fare sulla catena)

| Flag | Forma estesa | Significato |
|---|---|---|
| `-P` | `--policy` | Imposta la **policy di default** della catena: se nessuna regola fa match, si applica questo target (es. `DROP` o `ACCEPT`). |
| `-A` | `--append` | **Aggiunge** la regola in fondo alla catena. Le regole sono valutate in ordine: la prima che fa match termina la valutazione. |
| `-I` | `--insert` | **Inserisce** la regola in una posizione specifica (di default in cima). Utile per mettere regole prioritarie prima di quelle esistenti. |
| `-D` | `--delete` | **Elimina** una regola dalla catena (per numero o per pattern). |
| `-F` | `--flush` | **Svuota** tutte le regole della catena (o di tutte le catene se non specificata). |
| `-L` | `--list` | **Elenca** le regole presenti (aggiungere `-v` per contatori, `-n` per non risolvere nomi). |

---

### 3. Catene (CHAIN) — dove si applica la regola

| Catena | Quando viene attraversata |
|---|---|
| `INPUT` | Pacchetti **destinati alla macchina stessa** (il firewall è il destinatario finale). |
| `OUTPUT` | Pacchetti **generati dalla macchina stessa** e in uscita. |
| FORWARD | Pacchetti **in transito**: entrano da un'interfaccia ed escono da un'altra (il firewall fa da router). È la catena usata per filtrare il traffico tra LAN e Internet. |
| `PREROUTING` | Pacchetti appena arrivati, prima del routing (usata in tabella nat per DNAT). |
| `POSTROUTING` | Pacchetti dopo il routing, prima di uscire (usata in tabella nat per SNAT). |

> 💡 Negli esercizi d'esame la catena più usata è **FORWARD** perché il firewall protegge una LAN interna verso Internet: i pacchetti non sono destinati al firewall stesso ma lo attraversano.

---

### 4. Opzioni di match — criteri di selezione del pacchetto

#### 4.1 Interfacce

| Flag | Forma estesa | Significato |
|---|---|---|
| `-i eth0` | `--in-interface` | Pacchetto **arrivato** dall'interfaccia `eth0`. Usabile in INPUT e FORWARD. |
| `-o eth1` | `--out-interface` | Pacchetto **in uscita** dall'interfaccia `eth1`. Usabile in OUTPUT e FORWARD. |

> ⚠️ `-i` è l'interfaccia di **ingresso**, `-o` quella di **uscita**. In FORWARD si usano entrambi per specificare la direzione del flusso (es. `-i eth0 -o eth1` = dalla LAN verso Internet).

#### 4.2 Indirizzi IP

| Flag | Forma estesa | Significato |
|---|---|---|
| `-s 10.0.0.1` | `--source` | IP **sorgente** del pacchetto. Può essere un singolo IP o una subnet CIDR (es. `10.0.0.0/24`). |
| `-d 10.0.0.1` | `--destination` | IP **destinazione** del pacchetto. Stessa sintassi di `-s`. |
| `! -s 10.0.0.1` | negazione | Il `!` **nega** il match: significa "qualunque sorgente **tranne** `10.0.0.1`". |

#### 4.3 Protocollo

| Flag | Significato |
|---|---|
| `-p tcp` | Match solo sui pacchetti **TCP** (numero protocollo IP = 6). |
| `-p udp` | Match solo sui pacchetti **UDP** (numero protocollo IP = 17). |
| `-p icmp` | Match sui pacchetti **ICMP** (ping, traceroute…). |
| `-p all` | Match su **tutti** i protocolli (default se `-p` è omesso). |

#### 4.4 Porte (richiedono `-p tcp` o `-p udp`)

| Flag | Forma estesa | Significato |
|---|---|---|
| `--sport 80` | `--source-port` | Porta **sorgente** del segmento. |
| `--dport 443` | `--destination-port` | Porta **destinazione** del segmento. |
| `--dport 1024:65535` | range | Intervallo di porte da 1024 a 65535. |

#### 4.5 Flag TCP (richiedono `-p tcp`)

| Flag | Significato |
|---|---|
| `--syn` | Match sui pacchetti con **solo SYN settato** (FIN, RST, ACK azzerati). Equivale a `--tcp-flags FIN,SYN,RST,ACK SYN`. Identifica i pacchetti di **apertura connessione** (primo passo del three-way handshake). Bloccare `--syn` impedisce nuove connessioni in ingresso senza toccare le risposte. |
| `--tcp-flags <mask> <set>` | Forma generica: `<mask>` = insieme di flag da esaminare; `<set>` = quelli che devono risultare settati. Es. `--tcp-flags SYN,RST SYN,RST` = pacchetto con SYN e RST entrambi settati (illegale). |

> ⚠️ `--syn` cattura solo il primo SYN. I pacchetti successivi della stessa connessione TCP (ACK, FIN…) non hanno il solo SYN settato, quindi non fanno match e vengono gestiti dalle regole successive o dalla policy di default.

#### 4.6 Moduli estesi (`-m`)

I moduli estendono le capacità di match. Si caricano con `-m <nome_modulo>`.

**Modulo `conntrack`** — connection tracking stateful:

| Stato (`--ctstate`) | Significato |
|---|---|
| `NEW` | Pacchetto che avvia una **nuova** connessione (il kernel non ha visto questo flusso prima). |
| `ESTABLISHED` | Pacchetto appartenente a una connessione già **stabilita** (andata e ritorno già visti). |
| `RELATED` | Pacchetto **correlato** a una connessione esistente (es. ICMP error su un flusso TCP, o canale dati FTP correlato al controllo). |
| `INVALID` | Pacchetto che non corrisponde ad alcuna connessione nota e non è un nuovo SYN: va droppato. |
| `ESTABLISHED,RELATED` | Match su entrambi: permette il traffico di **ritorno** per connessioni avviate dall'interno. È il pattern tipico del firewall stateful che blocca le connessioni nuove in ingresso ma lascia passare le risposte. |

---

### 5. Target (`-j`) — cosa fare con il pacchetto

| Target | Significato |
|---|---|
| `ACCEPT` | **Lascia passare** il pacchetto. Nessuna ulteriore regola viene valutata per questo pacchetto. |
| `DROP` | **Scarta silenziosamente** il pacchetto. Il mittente non riceve alcuna notifica: la connessione risulta in timeout. |
| `REJECT` | **Scarta** il pacchetto **e invia un errore ICMP** al mittente. Più cortese di DROP ma rivela la presenza del firewall. |
| `LOG` | **Registra** le informazioni del pacchetto nel log di sistema e poi **continua** la valutazione delle regole successive (non è un target terminale). |

> 💡 **DROP vs REJECT**: in contesti di sicurezza si preferisce `DROP` perché non rivela informazioni al potenziale attaccante (il port scan restituisce timeout anziché ICMP error). `REJECT` è più utile nella LAN interna per dare feedback agli utenti.

---

### 6. Logica di valutazione delle regole

```
Pacchetto in arrivo
        |
        v
  Regola 1: fa match?  --NO-->  Regola 2: fa match?  --NO-->  ...  --NO-->  Policy di default (-P)
        |                               |
       SI                              SI
        |                               |
        v                               v
   Applica target                Applica target
   (ACCEPT o DROP)               (ACCEPT o DROP)
   Fine valutazione              Fine valutazione
```

> ⚠️ Le regole sono valutate **in ordine**. Al primo match, la valutazione si interrompe e il target viene applicato. Se nessuna regola fa match, si applica la **policy di default** (impostata con `-P`). Per questo la policy `DROP` con regole `ACCEPT` selettive è la configurazione più sicura.

---

### 7. Esempio di lettura di una regola completa

```bash
iptables -A FORWARD -i eth0 -o eth1 -s 10.0.0.1 -p tcp -j ACCEPT
```

| Pezzo | Significato |
|---|---|
| `-A FORWARD` | Aggiungi in coda alla catena FORWARD. |
| `-i eth0` | Il pacchetto è arrivato dall'interfaccia `eth0` (LAN interna). |
| `-o eth1` | Il pacchetto uscirà dall'interfaccia `eth1` (Internet). |
| `-s 10.0.0.1` | L'IP sorgente è `10.0.0.1` (il proxy). |
| `-p tcp` | Il protocollo è TCP. |
| `-j ACCEPT` | Se tutti i criteri sono soddisfatti: lascia passare il pacchetto. |

**Lettura in chiaro:** *"Aggiungi alla catena FORWARD una regola che accetta i pacchetti TCP provenienti dalla LAN (eth0) verso Internet (eth1) se la sorgente è il proxy (10.0.0.1)."*
