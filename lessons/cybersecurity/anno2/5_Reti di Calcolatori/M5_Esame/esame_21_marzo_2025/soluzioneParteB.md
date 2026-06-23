# Soluzione Esame di Reti di Calcolatori — Parte B — 21/03/2025

**Prof. Ernesto Damiani**

Per ogni esercizio è indicato il riferimento alle lezioni di teoria del corso, l'inquadramento concettuale, lo svolgimento e — dove richiesto — i diagrammi.

---

## Esercizio 1 — Server di moderazione commenti via HTTP POST su porta 80

**Esercizio 1 (10 punti)** Un social network online deve moderare i commenti ai post, eliminando quelli che contengono le parole di una lista nera. Fornite lo pseudocodice di un sistema server basato su socket che riceve dai client in porta 80 dei POST con il testo commento, l'id del post a cui si riferisce e un timestamp, controlla la presenza delle parole non ammesse (ipotizzate di usare una chiamata di libreria) e se il controllo è ok salva il commento in una directory il cui nome coincide con l'id del post. ATTENZIONE: Gestite il caso in cui la directory non esista. È vostra responsabilità scrivere sul socket l'intestazione HTTP corretta.

> **Riferimenti di teoria**:
>
> - [M4/UD1/L2 — Creazione di un socket](../../M4_Tecniche_programmazione_distribuita/UD1/L2%20-%20Creazione%20di%20un%20socket.md) (`socket()`, `bind()`)
> - [M4/UD1/L3 — Setup di connessione](../../M4_Tecniche_programmazione_distribuita/UD1/L3%20-%20Setup%20di%20connessione.md) (`listen()`, `accept()`)
> - [M4/UD1/L4 — Funzioni di comunicazione via socket](../../M4_Tecniche_programmazione_distribuita/UD1/L4%20-%20Funzioni%20di%20comunicazione%20via%20socket.md) (`send()`, `recv()`)
> - [M4/UD1/L5.2 — TCPServerEcho](../../M4_Tecniche_programmazione_distribuita/UD1/L5/L5.2%20-%20TCPServerEcho.md) (template server TCP concorrente)
> - [M3/UD4/L2 — HTTP](../../M3_Protocolli_applicativi/UD4/L2%20-%20HTTP%20(HyperText%20Transfer%20Protocol).md) (formato richiesta/risposta, header, metodo POST)
> - [M3/UD4/L3 — WWW e HTTP](../../M3_Protocolli_applicativi/UD4/L3%20-%20WWW%20e%20HTTP.md) (codici di stato, entity header)

### Inquadramento teorico

> 📌 Il server deve parlare **HTTP su TCP**: il client (un browser o un'app del social network) apre una connessione TCP sulla porta 80, invia un messaggio HTTP/1.1 con metodo **POST** e il body contiene i campi del commento. Il server è quindi un **server TCP** (`SOCK_STREAM`) che interpreta il protocollo applicativo HTTP. — *M4 UD1 L3, M3 UD4 L2*

Il flusso canonico lato server (cfr. M4/UD1/L3 e L5.2):

1. `socket()` → crea il socket di ascolto;
2. `bind()` → aggancia la porta 80;
3. `listen()` → mette in coda le richieste pendenti;
4. `accept()` (bloccante) → restituisce un socket dedicato per ogni client;
5. Delegare ogni connessione a un **processo figlio** via `fork()` (cfr. M4/UD1/L6);
6. Leggere la richiesta HTTP con `recv()`, analizzarla, salvare il commento;
7. Scrivere la risposta HTTP con `send()`;
8. `close()` → rilascia le risorse.

> ⚠️ TCP è uno **stream di byte**: non c'è garanzia che un singolo `recv()` restituisca l'intero messaggio HTTP. L'header HTTP termina con la sequenza `\r\n\r\n` e il body ha lunghezza nota dall'header `Content-Length`. Il server deve leggere iterativamente fino a ricevere tutti i byte attesi.

**Formato del body HTTP POST atteso** (stile `application/x-www-form-urlencoded`):

```
comment=Testo+del+commento&post_id=42&timestamp=1711015200
```

**Formato della risposta HTTP corretta** (richiesto esplicitamente dal testo):

```
HTTP/1.1 <codice> <reason>\r\n
Content-Type: text/plain; charset=utf-8\r\n
Content-Length: <N>\r\n
Connection: close\r\n
\r\n
<body>
```

### Pseudocodice del server

```c
#define PORT     80
#define BUF      8192
#define BASE_DIR "./comments"

int main(void) {
    int sock_srv = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in srv_addr;
    bzero((char*)&srv_addr, sizeof(srv_addr));
    srv_addr.sin_family      = AF_INET;
    srv_addr.sin_port        = htons(PORT);
    srv_addr.sin_addr.s_addr = htonl(INADDR_ANY);

    bind(sock_srv, (struct sockaddr*)&srv_addr, sizeof(srv_addr));
    listen(sock_srv, 5);

    while (1) {
        struct sockaddr_in cli_addr;
        int cli_len = sizeof(cli_addr);
        int sock_cli = accept(sock_srv, (struct sockaddr*)&cli_addr, &cli_len);

        if (fork() == 0) {          /* figlio: gestisce UN client */
            close(sock_srv);

            char buf[BUF];
            /* leggo header HTTP byte per byte fino a \r\n\r\n,
             * poi body per Content-Length byte (TCP è stream) */
            recv(sock_cli, buf, sizeof(buf), 0);

            /* parsing body: estrae comment, post_id, timestamp (alto livello) */

            if (check_blacklist(comment)) {
                send(sock_cli,
                     "HTTP/1.1 403 Forbidden\r\nContent-Length: 0\r\n\r\n",
                     46, 0);
            } else {
                /* crea ./comments e ./comments/<post_id>; errno==EEXIST → già esiste */
                mkdir(BASE_DIR, 0755);
                snprintf(post_dir, sizeof(post_dir), "%s/%s", BASE_DIR, post_id);
                mkdir(post_dir, 0755);

                /* salva commento in file <timestamp>.txt */
                FILE *f = fopen(file_path, "w");
                fprintf(f, "%s\n", comment);
                fclose(f);

                send(sock_cli,
                     "HTTP/1.1 201 Created\r\nContent-Length: 0\r\n\r\n",
                     43, 0);
            }

            close(sock_cli);
            exit(0);
        }
        close(sock_cli);    /* padre: torna subito ad accept() */
    }
}
```

### Note chiave

1. **TCP è stream**: `recv()` può tornare parziale → in pratica si legge in loop fino a `\r\n\r\n` (header) poi `Content-Length` byte (body).
2. **`mkdir()` + `errno == EEXIST`**: se la directory esiste già non è errore; il test evita di fallire sui commenti successivi allo stesso post.
3. **`fork()` per concorrenza**: il padre torna immediatamente ad `accept()`; il figlio gestisce il singolo client e termina (cfr. M4/UD1/L6).

### Diagramma di sequenza

```
Client (browser/app)              Server (porta 80)
─────────────────                 ─────────────────
  SYN ──────────────────────────► accept() ritorna
  SYN+ACK ◄────────────────────── fork() → figlio
  ACK ──────────────────────────►
                                   │
  POST / HTTP/1.1            ─────►│ recv() header (fino a \r\n\r\n)
  Content-Length: N               │ recv() body (loop per Content-Length byte)
  \r\n                            │
  comment=...&post_id=7&ts=...    │
                                   │ check_blacklist(comment)
                                   │    → KO: send_http(403)
                                   │    → OK ↓
                                   │ mkdir("./comments/7", 0755)   ← se non esiste
                                   │ fopen/fprintf/fclose
  HTTP/1.1 201 Created ◄───────── │ send_http(201)
  Content-Length: 17              │
  \r\n                            │
  Commento salvato                │ close(sock_client); exit(0)
```

---

## Esercizio 2 — Analisi di una richiesta HTTP multipart/form-data

**Esercizio 2 (8 punti)** Spiegate il significato dei campi della seguente richiesta HTTP.

> **Riferimenti di teoria**:
>
> - [M3/UD4/L2 — HTTP](../../M3_Protocolli_applicativi/UD4/L2%20-%20HTTP%20(HyperText%20Transfer%20Protocol).md) (metodo POST, Content-Type, Content-Disposition, multipart)
> - [M3/UD4/L3 — WWW e HTTP](../../M3_Protocolli_applicativi/UD4/L3%20-%20WWW%20e%20HTTP.md) (format richiesta, entity header)

### Richiesta da analizzare

```
POST /test HTTP/1.1
Host: example.com
Content-Type: multipart/form-data;boundary="delimiter12345"

--delimiter12345
Content-Disposition: form-data; name="field1"

value1
--delimiter12345
Content-Disposition: form-data; name="field2";
filename="example.txt"

value2
--delimiter12345--
```

### 1. Request line — `POST /test HTTP/1.1`

| Campo | Significato |
|---|---|
| **POST** | Metodo HTTP: invia dati al server nel body. In HTTP/1.1 è il metodo usato per sottomettere form, caricare file, creare risorse (cfr. M3/UD4/L2 §5: POST non è idempotente, non ha effetto cache). |
| **/test** | Path (URI) della risorsa sul server a cui è destinata la richiesta. |
| **HTTP/1.1** | Versione del protocollo: connessioni persistenti di default, header `Host` obbligatorio, chunked transfer encoding disponibile. |

### 2. Header `Host: example.com`

- **Obbligatorio in HTTP/1.1** (mancando, il server deve rispondere 400 Bad Request).
- Identifica il nome logico del server, necessario per il **virtual hosting**: più siti possono condividere lo stesso IP e il server usa `Host` per instradare la richiesta al sito corretto. (cfr. M3/UD4/L2 §6)

### 3. Header `Content-Type: multipart/form-data; boundary="delimiter12345"`

Due informazioni fondamentali:

| Campo | Significato |
|---|---|
| `multipart/form-data` | Il body è diviso in **più parti** (parti multipart), ciascuna con i propri metadati. È il tipo obbligatorio per form HTML che inviano file (`<form enctype="multipart/form-data">`). |
| `boundary="delimiter12345"` | Stringa separatrice che il parser del server usa per trovare i confini tra le parti. Ogni parte inizia con `--delimiter12345` e il body termina con `--delimiter12345--` (doppio trattino finale). |

> 📌 `multipart/form-data` è usato al posto di `application/x-www-form-urlencoded` quando il form contiene **upload di file**: i byte del file non possono essere URL-encoded senza esplosione di dimensione.

### 4. Struttura del body multipart

#### Parte 1 — campo testo

```
--delimiter12345
Content-Disposition: form-data; name="field1"

value1
```

| Elemento | Significato |
|---|---|
| `--delimiter12345` | Marca l'inizio di una nuova parte. |
| `Content-Disposition: form-data; name="field1"` | Header della parte: dichiara che è un campo di form (`form-data`) il cui nome HTML è `field1`. |
| *(riga vuota)* | Separatore tra header della parte e corpo della parte (come `\r\n\r\n` in HTTP). |
| `value1` | Valore del campo: il server riceverà `field1 = "value1"`. |

#### Parte 2 — upload di file

```
--delimiter12345
Content-Disposition: form-data; name="field2";
filename="example.txt"

value2
```

| Elemento | Significato |
|---|---|
| `name="field2"` | Nome del campo nel form. |
| `filename="example.txt"` | Presenza di `filename` segnala che questo campo trasporta un **file**, il cui nome originale sul client è `example.txt`. In una trasmissione reale, al posto di `value2` ci sarebbero i byte del file; è spesso accompagnato da `Content-Type: text/plain` (o il MIME type del file). |
| `value2` | Contenuto del file (qui simbolico). |

#### Chiusura del multipart

```
--delimiter12345--
```

Il doppio trattino finale (`--`) segnala al parser la **fine definitiva del body**. Nessun'altra parte seguirà.

> ✅ Riassunto: la richiesta invia due campi form. `field1` è un campo testuale (valore `value1`). `field2` è un upload di file (nome `example.txt`, contenuto `value2`). Il server deve leggere il body, trovare ogni `--boundary`, estrarre `name` e (se presente) `filename` dall'header `Content-Disposition`, e infine leggere il corpo di ciascuna parte fino alla boundary successiva.

---

## Esercizio 3 — Download di una pagina con 10 immagini in HTTP/1.0 e HTTP/1.1

**Esercizio 3 (6 punti)** Un browser deve scaricare una pagina Web che contiene 10 immagini, due delle quali sono memorizzate sullo stesso server e tre su un server diverso. MOSTRATE le richieste HTTP generate dal browser e le risposte dei server. Considerate sia l'utilizzo di HTTP 1.0 che 1.1.

> **Riferimenti di teoria**:
>
> - [M3/UD4/L1 — World Wide Web](../../M3_Protocolli_applicativi/UD4/L1%20-%20World%20Wide%20Web.md) (HTTP/1.0 = 1 TCP per oggetto; HTTP/1.1 = persistente)
> - [M3/UD4/L2 — HTTP](../../M3_Protocolli_applicativi/UD4/L2%20-%20HTTP%20(HyperText%20Transfer%20Protocol).md) (GET, Host, Connection, pipelining)

### Topologia delle risorse

```
Browser
  │
  ├── Server A (www.sito.com)  ──  index.html
  │                            ──  img1.jpg, img2.jpg    (le "due" del testo)
  │                            ──  img4 … img10          (le restanti 5+)
  │
  └── Server B (img.cdn.com)   ──  img3a.png, img3b.png, img3c.png
                                    (le "tre su server diverso" del testo)
```

Totale oggetti: **1 HTML + 10 immagini = 11 risorse**.

- **2 immagini su Server A** (stesso server dell'HTML): le chiameremo `img1.jpg`, `img2.jpg`.
- **3 immagini su Server B** (server diverso): `img3a.png`, `img3b.png`, `img3c.png`.
- **5 immagini rimanenti**: il testo non specifica l'host; per semplificare le mostriamo su Server A (`img4.jpg` … `img8.jpg`). Il meccanismo è identico.

---

### Caso A — HTTP/1.0 (connessione non persistente)

> 📌 In HTTP/1.0 ogni oggetto richiede l'apertura di **una nuova connessione TCP** (three-way handshake + richiesta + risposta + chiusura). Non è obbligatorio `Host`; le connessioni persistenti non sono standard. — *M3/UD4/L1 §5*

Con 11 oggetti su 2 host → **11 connessioni TCP totali**: 8 verso A, 3 verso B.

#### Sequenza (mostrata in modo rappresentativo)

```
Browser                     Server A (www.sito.com)         Server B (img.cdn.com)
───────                     ──────────────────────          ──────────────────────

[Connessione 1 → A]
─── SYN ──────────────────►
◄── SYN+ACK ───────────────
─── ACK ──────────────────►
─── GET /index.html HTTP/1.0 ──────────────────────────────►
◄── HTTP/1.0 200 OK
    Content-Type: text/html
    Content-Length: N
    <html>...(10 <img>)...</html> ─────────────────────────
─── FIN ──────────────────►   [connessione chiusa]

   [parser HTML: individua 10 immagini → apre una connessione per ciascuna]

[Connessione 2 → A: img1.jpg]
─── SYN ──────────────────►
─── GET /img1.jpg HTTP/1.0 ──────────────────────────────►
◄── HTTP/1.0 200 OK
    Content-Type: image/jpeg
    Content-Length: M1
    <byte immagine 1> ─────────────────────────────────────
─── FIN ──────────────────►

[Connessione 3 → A: img2.jpg]   ← stessa struttura, omessa per brevità

[Connessioni 4–8 → A: img4…img8]  ← stessa struttura

[Connessione 9 → B: img3a.png]
                                                ─── SYN ───────────────────►
                                                ─── GET /img3a.png HTTP/1.0 ─►
                                                ◄── HTTP/1.0 200 OK
                                                    Content-Type: image/png
                                                    Content-Length: M9
                                                    <byte> ──────────────────
                                                ─── FIN ───────────────────►

[Connessioni 10–11 → B: img3b, img3c]  ← stessa struttura
```

**Costo**: con 11 connessioni seriali, ogni connessione vale almeno **2·RTT** (1 per handshake + 1 per GET/risposta) → ordine di **22·RTT** nel caso peggiore. I browser dell'epoca aprono fino a 4 TCP in parallelo per host, abbassando il tempo reale.

---

### Caso B — HTTP/1.1 (connessione persistente, `Host` obbligatorio)

> 📌 In HTTP/1.1 la connessione TCP è **persistente di default** (`Connection: keep-alive`): una singola connessione può trasportare più coppie richiesta/risposta, eliminando la latenza degli handshake ripetuti. — *M3/UD4/L1 §6*

Si apre **1 connessione verso A** (per HTML + 7 immagini su A) e **1 connessione verso B** (per 3 immagini su B): **2 connessioni TCP totali**.

```
Browser                     Server A (www.sito.com)         Server B (img.cdn.com)
───────                     ──────────────────────          ──────────────────────

[TCP persistente verso A]
─── SYN/SYN+ACK/ACK ──────►

─── GET /index.html HTTP/1.1
    Host: www.sito.com
    Connection: keep-alive ─►
◄── HTTP/1.1 200 OK
    Content-Length: N
    Connection: keep-alive
    <html>...(10 <img>)...</html>

   [parser HTML: individua 10 immagini]
   [apre in parallelo una connessione TCP verso B]

─── GET /img1.jpg HTTP/1.1
    Host: www.sito.com ────►
◄── HTTP/1.1 200 OK
    Content-Length: M1
    <byte img1>

─── GET /img2.jpg HTTP/1.1
    Host: www.sito.com ────►      ← sulla STESSA connessione TCP
◄── HTTP/1.1 200 OK
    Content-Length: M2
    <byte img2>

─── GET /img4.jpg … GET /img8.jpg HTTP/1.1
    Host: www.sito.com ────►      ← stessa connessione
◄── HTTP/1.1 200 OK ... (per ciascuna)

─── GET /index.html HTTP/1.1
    Connection: close ─────►      ← ultima richiesta: chiude la connessione
◄── HTTP/1.1 200 OK  FIN

                                                [TCP persistente verso B]
                                                ─── SYN/SYN+ACK/ACK ──────────►
                                                ─── GET /img3a.png HTTP/1.1
                                                    Host: img.cdn.com
                                                    Connection: keep-alive ───►
                                                ◄── HTTP/1.1 200 OK
                                                    Content-Length: P1
                                                    <byte img3a>
                                                ─── GET /img3b.png HTTP/1.1 ──►
                                                ◄── HTTP/1.1 200 OK <byte img3b>
                                                ─── GET /img3c.png HTTP/1.1
                                                    Connection: close ─────────►
                                                ◄── HTTP/1.1 200 OK <byte img3c>  FIN
```

**Costo** (con le due connessioni A e B aperte in parallelo):

$$
T_{HTTP/1.1} \;\approx\; \underbrace{1 \cdot RTT}_{\text{setup A}} + \underbrace{1 \cdot RTT}_{\text{HTML}} + \underbrace{1 \cdot RTT}_{\text{img A in parallelo a img B}}
$$

≈ **3·RTT** contro le ~22·RTT di HTTP/1.0 seriale.

#### Confronto riepilogativo

| Schema | Connessioni TCP | RTT approssimativi |
|---|---:|---:|
| HTTP/1.0 (seriale) | 11 | ~22·RTT |
| HTTP/1.0 con parallelismo browser (4 conn per host) | 11 (parallele) | ~6·RTT |
| **HTTP/1.1 persistente** | **2** | **~3–4·RTT** |

> ✅ HTTP/1.1 riduce drasticamente il numero di connessioni TCP: da 11 a 2. Il risparmio deriva dall'eliminazione degli handshake ripetuti (ciascuno costa 1·RTT) e dal riavvio della fase di *Slow Start* TCP (cfr. M2/UD5).

---

## Esercizio 4 — Regola di firewall: accesso Internet da una sola sottorete

**Esercizio 4 (6 punti)** Siete i gestori del firewall di una rete. Volete abilitare l'accesso a internet da una sola sottorete vostra rete e bloccare l'accesso per tutti gli altri. Specificate la regola da utilizzare.

> **Riferimenti di teoria**:
>
> - [M2/UD2/L1 — Internet Protocol (IP)](<../../M2_Protocolli_rete_TCP_IP/UD2/L1%20-%20Internet%20Protocol%20(IP).md>) (filtering IP, numeri protocollo)
> - Cfr. anche soluzione "[Esercizio 3 (4 pt) — Regola di firewall TCP](../esame_23_maggio_2025/SoluzioneParteB.md#esercizio-3-4-pt--regola-di-firewall-tcp)" del 23/05/2025 e "[Esercizio 4](../esame_8_maggio_2026/soluzioneParteB.md#esercizio-4--regola-di-firewall-solo-proxy--tcp-esterno-udp-disabilitato)" dell'8/05/2026 per il pattern stateless/stateful.

### Inquadramento

Il firewall si trova tra la **LAN interna** (con più sottoreti) e **Internet**. Vogliamo:

1. **Permettere** il traffico da uscita verso Internet **solo** se la sorgente è nella sottorete autorizzata.
2. **Bloccare** tutto il traffico da/verso Internet degli altri host/sottoreti.
3. **Permettere** il traffico di ritorno (risposte) per le connessioni avviate dalla sottorete autorizzata.

Ipotesi:
- Sottorete autorizzata: `192.168.10.0/24`
- Interfaccia LAN: `eth_int`
- Interfaccia WAN (Internet): `eth_ext`

### Regola in linguaggio neutro (pseudo-ACL)

```
FORWARD (LAN → Internet, da eth_int a eth_ext):
    ACCEPT  se  src_ip ∈ 192.168.10.0/24   ← sottorete autorizzata
    DROP    altrimenti                       ← tutto il resto bloccato

FORWARD (Internet → LAN, da eth_ext a eth_int):
    ACCEPT  se  stato == ESTABLISHED         ← ritorno di connessioni avviate da noi
    DROP    altrimenti                       ← nessuna connessione nuova dall'esterno
```

> ⚠️ Senza la regola sul traffico di ritorno (`ESTABLISHED`), le risposte al nostro traffico verrebbero bloccate. Il firewall **stateful** tiene una tabella delle connessioni attive e ammette automaticamente i pacchetti di ritorno.

### Implementazione `iptables` (Linux netfilter)

```bash
# ─── Variabili ────────────────────────────────────────────────────────
AUTH_NET=192.168.10.0/24   # sottorete autorizzata
IF_INT=eth0                # interfaccia LAN
IF_EXT=eth1                # interfaccia Internet (WAN)

# ─── 1. Policy di default: nega tutto il traffico in transito ────────
iptables -P FORWARD DROP

# ─── 2. Permetti l'uscita SOLO dalla sottorete autorizzata ───────────
iptables -A FORWARD -i $IF_INT -o $IF_EXT -s $AUTH_NET -j ACCEPT

# ─── 3. Permetti il ritorno: risposte a connessioni avviate da noi ───
iptables -A FORWARD -i $IF_EXT -o $IF_INT \
         -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# ─── 4. Tutto il resto è già negato dalla policy DROP ────────────────
```

### Spiegazione riga per riga

| Regola | Effetto |
|---|---|
| `-P FORWARD DROP` | Default: ogni pacchetto in transito che non fa match con una regola `ACCEPT` viene scartato silenziosamente. |
| `-i $IF_INT -o $IF_EXT -s $AUTH_NET -j ACCEPT` | I pacchetti **in uscita** verso Internet sono accettati **solo** se la sorgente appartiene a `192.168.10.0/24`. Ogni altro host/sottorete interna è bloccato dalla policy DROP. |
| `-i $IF_EXT -o $IF_INT --ctstate ESTABLISHED,RELATED -j ACCEPT` | I pacchetti in **ingresso** da Internet sono accettati solo se appartengono a una connessione **già avviata dall'interno** (stato `ESTABLISHED`) o sono correlati (es. errori ICMP, `RELATED`). Nuove connessioni dall'esterno vengono bloccate. |

### Tabella del comportamento risultante

| Direzione | Sorgente | Decisione |
|---|---|---|
| LAN → Internet | `192.168.10.x` (sottorete autorizzata) | **ACCEPT** |
| LAN → Internet | qualunque altro host LAN | DROP |
| Internet → LAN | risposta a connessione avviata da `192.168.10.x` | ACCEPT (stateful) |
| Internet → LAN | qualunque connessione nuova | DROP |

> 💡 Questa è la stessa politica di base dei firewall residenziali e aziendali: **"solo la rete fidata può uscire; dall'esterno si entra solo se si è già stati invitati"**. Il modulo `conntrack` di netfilter rende la regola semplice ed efficiente senza dover gestire manualmente i flag TCP (`SYN`, `ACK`).

---
