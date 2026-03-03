## **Esercizio 1 (10 punti) — Server di moderazione commenti via socket su porta 80 (HTTP POST) + salvataggio su directory per post**

### **Obiettivo del sistema**

Realizzare (in pseudocodice) un **server HTTP** che:

1. ascolta su **porta 80**;
    
2. riceve richieste **POST** contenenti:
    
    - testo del commento,
        
    - post_id (id del post a cui si riferisce),
        
    - timestamp;
        
    
3. controlla se nel testo compaiono parole presenti in una **lista nera** (assumendo l’esistenza di una libreria, ad esempio contains_blacklisted_words(text) o checkBlacklist(text)).
    
4. se il commento è “pulito”, lo salva su disco in una **directory che ha come nome** **post_id**;
    
5. gestisce il caso in cui la directory **non esista** (creandola);
    
6. scrive sul socket una **risposta HTTP corretta** (status line + header + body, coerenti col risultato).

---

### **Scelte progettuali “da 30 e lode” (robuste e realistiche)**

- **Parsing HTTP minimo ma corretto**: leggere fino a \r\n\r\n (fine header) e poi leggere il body usando Content-Length.
    
- **Formato del body**: non è imposto dal testo. In un compito d’esame conviene gestire almeno un formato standard:
    
    - application/x-www-form-urlencoded (molto comune) **oppure** JSON.
        
        Qui mostro un approccio robusto: supporto x-www-form-urlencoded, e se arriva JSON lo gestisco comunque.
        
    
- **Nome file univoco**: non posso salvare “un commento” sempre nello stesso file o rischio overwrite. Soluzione: filename basato su timestamp + un contatore o random.
    
- **Sicurezza basilare**: sanitizzare post_id (evitare ../), altrimenti path traversal.
    
- **Codici HTTP sensati**:
    
    - 201 Created se salvo,
        
    - 403 Forbidden se contiene parole vietate,
        
    - 400 Bad Request se mancano campi o formato errato,
        
    - 405 Method Not Allowed se non è POST.
    

---

### **Pseudocodice (server concorrente con thread/processi)**

(È volutamente dettagliato: è quello che vuoi stampare e consegnare.)

```
CONSTANT PORT = 80
CONSTANT BASE_DIR = "./comments"          # directory radice dove salvare tutto

function main():
    server_sock = socket(AF_INET, SOCK_STREAM)
    set_option(server_sock, SO_REUSEADDR, true)

    bind(server_sock, "0.0.0.0", PORT)
    listen(server_sock, backlog=128)

    while true:
        client_sock, client_addr = accept(server_sock)
        spawn_thread(handle_client, client_sock, client_addr)


function handle_client(sock, addr):
    try:
        # 1) Leggo header HTTP fino a CRLF CRLF
        header_bytes = read_until(sock, "\r\n\r\n", max_bytes=65536)
        if header_bytes == ERROR:
            send_http(sock, 400, "Bad Request", "Header non valido")
            close(sock)
            return

        request_line, headers = parse_http_headers(header_bytes)
        method, path, version = parse_request_line(request_line)

        # 2) Accetto solo POST (come richiesto dal testo)
        if method != "POST":
            send_http(sock, 405, "Method Not Allowed",
                      "Usare POST", extra_headers={"Allow":"POST"})
            close(sock)
            return

        # 3) Leggo Content-Length per capire quanto body leggere
        if "Content-Length" not in headers:
            send_http(sock, 411, "Length Required",
                      "Manca Content-Length")
            close(sock)
            return

        content_length = to_int(headers["Content-Length"])
        if content_length < 0 or content_length > 10^7:
            send_http(sock, 413, "Payload Too Large", "Body troppo grande")
            close(sock)
            return

        body = read_exact(sock, content_length)
        if body == ERROR:
            send_http(sock, 400, "Bad Request", "Body incompleto")
            close(sock)
            return

        # 4) Parsing del body: preferibilmente form-urlencoded; fallback JSON
        content_type = headers.get("Content-Type", "")
        data = {}

        if starts_with(content_type, "application/x-www-form-urlencoded"):
            data = parse_form_urlencoded(body)     # es: comment=...&post_id=...&timestamp=...
        else if starts_with(content_type, "application/json"):
            data = parse_json(body)                # es: {"comment":"..","post_id":"..","timestamp":".."}
        else:
            # formato non specificato: provo form-urlencoded come default pragmatico
            data = try_parse_form_urlencoded(body)
            if data == ERROR:
                send_http(sock, 400, "Bad Request", "Formato body non supportato")
                close(sock)
                return

        # 5) Validazione campi obbligatori
        if "comment" not in data or "post_id" not in data or "timestamp" not in data:
            send_http(sock, 400, "Bad Request",
                      "Campi richiesti: comment, post_id, timestamp")
            close(sock)
            return

        comment_text = data["comment"]
        post_id_raw  = data["post_id"]
        timestamp    = data["timestamp"]

        # 6) Sanitizzazione post_id (evito path traversal e caratteri strani)
        post_id = sanitize_post_id(post_id_raw)
        if post_id == ERROR:
            send_http(sock, 400, "Bad Request", "post_id non valido")
            close(sock)
            return

        # 7) Moderazione: controllo blacklist (libreria ipotizzata dal testo)
        if contains_blacklisted_words(comment_text) == true:
            # commento eliminato: non salvo nulla
            send_http(sock, 403, "Forbidden",
                      "Commento rifiutato: contiene parole non ammesse")
            close(sock)
            return

        # 8) Directory del post: BASE_DIR/post_id (gestire caso non esiste)
        ensure_directory_exists(BASE_DIR)                   # crea BASE_DIR se manca
        post_dir = join_path(BASE_DIR, post_id)

        if directory_exists(post_dir) == false:
            ok = create_directory(post_dir)
            if ok == false:
                send_http(sock, 500, "Internal Server Error",
                          "Impossibile creare directory del post")
                close(sock)
                return

        # 9) Salvataggio: file univoco
        #    esempio: <timestamp>_<ip>_<random>.txt
        safe_ts = sanitize_timestamp(timestamp)             # opzionale: rimuove caratteri pericolosi
        rnd = random_hex(8)
        filename = safe_ts + "_" + addr.ip + "_" + rnd + ".txt"
        file_path = join_path(post_dir, filename)

        ok = write_text_file(file_path, comment_text)
        if ok == false:
            send_http(sock, 500, "Internal Server Error",
                      "Errore nel salvataggio del commento")
            close(sock)
            return

        # 10) Risposta HTTP corretta
        body_resp = "OK: commento salvato in " + file_path
        send_http(sock, 201, "Created", body_resp,
                  extra_headers={"Content-Type":"text/plain; charset=utf-8"})

        close(sock)

    except Exception as e:
        # fallback di robustezza
        send_http(sock, 500, "Internal Server Error", "Errore inatteso")
        close(sock)


function send_http(sock, status_code, reason, body, extra_headers={}):
    body_bytes = to_bytes(body, "utf-8")

    response = ""
    response += "HTTP/1.1 " + status_code + " " + reason + "\r\n"
    response += "Date: " + http_date_now() + "\r\n"
    response += "Connection: close\r\n"
    response += "Content-Length: " + len(body_bytes) + "\r\n"

    # header extra (Content-Type, Allow, ecc.)
    for k,v in extra_headers:
        response += k + ": " + v + "\r\n"

    response += "\r\n"

    write_all(sock, to_bytes(response, "ascii"))
    write_all(sock, body_bytes)


function sanitize_post_id(s):
    # accetto solo [A-Za-z0-9_-], lunghezza ragionevole
    if length(s) == 0 or length(s) > 64:
        return ERROR
    for ch in s:
        if not (is_alnum(ch) or ch == '_' or ch == '-'):
            return ERROR
    return s
```

---

## **Esercizio 2 (8 punti) — Significato dei campi della richiesta HTTP multipart/form-data**

La richiesta mostrata è una **HTTP/1.1 POST** verso la risorsa /test sul server example.com, e invia un corpo in formato **multipart/form-data**, tipico dell’invio di form HTML con campi multipli e (eventualmente) file.

Riporto i campi e il loro significato in modo sistematico.

---

### **1) Request line**

POST /test HTTP/1.1

- **POST**: metodo usato per inviare dati al server nel body della richiesta (tipicamente per creare risorse o inviare form).
    
- **/test**: path (risorsa) sul server a cui viene inviata la richiesta.
    
- **HTTP/1.1**: versione del protocollo. In HTTP/1.1:
    
    - l’header Host è obbligatorio;
        
    - le connessioni sono persistent di default (salvo Connection: close).

---

### **2) Header Host**

Host: example.com

- Identifica l’host “logico” richiesto.
    
- È fondamentale in HTTP/1.1 perché più siti (virtual hosting) possono condividere lo stesso IP: il server usa Host per sapere quale dominio/virtual host deve gestire la richiesta.
    

---

### **3) Header Content-Type (multipart/form-data + boundary)**

Content-Type: multipart/form-data; boundary="delimiter12345"

- Indica che il body non è un testo unico, ma è diviso in **parti** (part) separate.
    
- multipart/form-data è lo standard per inviare form con campi multipli e upload.
    
- boundary="delimiter12345" definisce la stringa che separa le parti del body.
    
    - Ogni parte inizia con --delimiter12345
        
    - L’ultima chiusura è --delimiter12345-- (notare i due trattini finali).

---

### **4) Corpo della richiesta: parti multipart**

#### **Struttura generale**

Ogni parte ha:

1. una riga di boundary --delimiter...
    
2. uno o più header specifici della parte (per esempio Content-Disposition)
    
3. una riga vuota
    
4. il contenuto della parte (valore del campo o contenuto del file)
    
---

### **Parte 1**

```
--delimiter12345
Content-Disposition: form-data; name="field1"

value1
```

- Content-Disposition: form-data indica che questa parte rappresenta un campo di un form.
    
- name="field1" è il nome del campo (come l’attributo name in HTML).
    
- Il contenuto vero e proprio è value1, cioè il valore associato a field1.

In termini applicativi: il server riceverà un campo field1 = "value1".

---

### **Parte 2**

```
--delimiter12345
Content-Disposition: form-data; name="field2"; filename="example.txt"

value2
```

Qui compare anche:

- filename="example.txt": segnala che questo campo è trattato come **upload di file** (anche se nell’esempio il contenuto è la stringa value2).

In una richiesta reale di upload, al posto di value2 ci sarebbero i **byte del file**. Spesso (non obbligatorio ma comune) si include anche un header della parte:

- Content-Type: text/plain (o altro mime type del file)

Quindi: field2 è un campo che contiene un file chiamato example.txt, e il suo contenuto è quello riportato dopo la riga vuota.

---

### **Chiusura del multipart**

--delimiter12345--

- Indica la **fine definitiva** del corpo multipart.
    
- Serve al parser per sapere che non ci sono altre parti.
    

---

## **Esercizio 3 (6 punti) — Richieste/risposte HTTP per pagina con 10 immagini (HTTP/1.0 vs HTTP/1.1)**

### **Scenario**

Un browser deve scaricare:

1. una pagina HTML (es. /index.html) da un server (chiamiamolo **Server A**);
    
2. la pagina contiene riferimenti a **10 immagini**;
    
3. tra queste immagini:
    
    - **2** stanno sullo **stesso server A**,
        
    - **3** stanno su un **server diverso** (chiamiamolo **Server B**),
        
    - le altre immagini (non specificate nel testo) sono comunque risorse HTTP e si gestiscono allo stesso modo: ciò che cambia è **l’host** a cui puntano.

Per mostrare correttamente le richieste, basta esemplificare con nomi chiari. Uso:

- Server A: www.siteA.com
    
- Server B: img.siteB.com
    
- HTML: /index.html
    
- Immagini su A: /img/a1.jpg, /img/a2.jpg
    
- Immagini su B: /img/b1.jpg, /img/b2.jpg, /img/b3.jpg
    
- Le restanti: /img/a3.jpg ... /img/a10.jpg (o su altri host, se presenti: cambia solo Host/connessione).
    

---

### **A) Caso HTTP/1.0 (connessioni non persistenti di default)**

In HTTP/1.0, la modalità tipica è:

- **1 connessione TCP per ogni oggetto** (HTML + ciascuna immagine),
    
- salvo l’uso esplicito di Connection: keep-alive (non standardizzato in 1.0 e non sempre supportato).

#### **1) Scarico la pagina HTML (connessione 1)**

**Richiesta**

```
GET /index.html HTTP/1.0
```

**Risposta**

```
HTTP/1.0 200 OK
Content-Type: text/html
Content-Length: ...

<html> ... riferimenti alle immagini ... </html>
```

La connessione viene normalmente chiusa dal server.

#### **2) Scarico le immagini**

Per ogni immagine, il browser apre una nuova connessione e invia un GET.

Esempio per un’immagine su **Server A** (connessione 2):

```
GET /img/a1.jpg HTTP/1.0
```

Risposta:

```
HTTP/1.0 200 OK
Content-Type: image/jpeg
Content-Length: ...

... byte dell'immagine ...
```

Esempio per un’immagine su **Server B** (nuova connessione verso un host diverso):

```
GET /img/b1.jpg HTTP/1.0
```

Risposta:

```
HTTP/1.0 200 OK
Content-Type: image/jpeg
Content-Length: ...

... byte dell'immagine ...
```

**Osservazione chiave (da dire in consegna):** con HTTP/1.0 il costo principale è l’overhead di aprire/chiudere molte connessioni TCP (handshake, slow start, ecc.). Con 10 immagini, si arriva facilmente a **11 connessioni** (1 HTML + 10 immagini), distribuite tra A e B.

---

### **B) Caso HTTP/1.1 (connessioni persistenti + Host obbligatorio)**

In HTTP/1.1:

- Host è obbligatorio.
    
- Le connessioni sono **persistenti di default**: una singola connessione può trasferire più risorse in sequenza.
    
- Il browser in pratica può aprire **più connessioni in parallelo**, ma il punto concettuale d’esame è che **non deve** aprirne una per ogni oggetto.

#### **1) Connessione verso Server A: scarico HTML e immagini su A**

**Richiesta HTML (stessa connessione)**

```
GET /index.html HTTP/1.1
Host: www.siteA.com
Connection: keep-alive
```

**Risposta**

```
HTTP/1.1 200 OK
Content-Type: text/html
Content-Length: ...
Connection: keep-alive

<html> ... </html>
```

Ora, sulla **stessa connessione TCP**, il browser richiede le immagini su A (una dopo l’altra; opzionalmente con pipelining, concetto spesso citato in teoria HTTP/1.1).

Esempio:

```
GET /img/a1.jpg HTTP/1.1
Host: www.siteA.com

GET /img/a2.jpg HTTP/1.1
Host: www.siteA.com
```

Risposte (in ordine):

```
HTTP/1.1 200 OK
Content-Type: image/jpeg
Content-Length: ...

... bytes a1 ...

HTTP/1.1 200 OK
Content-Type: image/jpeg
Content-Length: ...

... bytes a2 ...
```

#### **2) Connessione verso Server B: scarico immagini su B**

Essendo un host diverso, serve una **connessione distinta** (o più, ma concettualmente basta una):

```
GET /img/b1.jpg HTTP/1.1
Host: img.siteB.com
Connection: keep-alive

GET /img/b2.jpg HTTP/1.1
Host: img.siteB.com

GET /img/b3.jpg HTTP/1.1
Host: img.siteB.com
```

Risposte:

```
HTTP/1.1 200 OK
Content-Type: image/jpeg
Content-Length: ...
... bytes b1 ...

HTTP/1.1 200 OK
Content-Type: image/jpeg
Content-Length: ...
... bytes b2 ...

HTTP/1.1 200 OK
Content-Type: image/jpeg
Content-Length: ...
... bytes b3 ...
```

**Osservazione chiave (da 30 e lode):**

- HTTP/1.1 riduce drasticamente overhead e latenza perché riusa la connessione.
    
- Le richieste verso host diversi richiedono connessioni distinte (A e B), ma **all’interno dello stesso host** si sfrutta la persistenza.
    
- In pratica i browser aprono più connessioni parallele per performance, ma il punto concettuale è: **HTTP/1.1 permette più oggetti su una singola connessione**.
    

---

## **Esercizio 4 (6 punti) — Regola firewall: consentire Internet solo a una sottorete, bloccare tutti gli altri**

### **Richiesta**

Sei gestore del firewall. Vuoi:

- **abilitare** l’accesso a Internet **solo** da **una sottorete** interna;
    
- **bloccare** l’accesso per tutte le altre sottoreti/host.

L’idea corretta è sempre la stessa, indipendentemente dal prodotto:

1. **permit/allow**: traffico in uscita verso Internet dalla sottorete autorizzata;
    
2. **permit/allow**: traffico di ritorno relativo a connessioni già stabilite (stateful firewall);
    
3. **deny/drop**: tutto il resto verso Internet.
    

---

### **Soluzione in forma di ACL “classica” (stile router/firewall con regole top-down)**

Supponiamo che la sottorete autorizzata sia:

$$

192.168.10.0/24

$$

Regole:

1. **Consenti** traffico da 192.168.10.0/24 verso “any” (Internet)
    
2. **Nega** traffico da “any” verso “any” (default deny per le altre sottoreti)
    
In pseudo-ACL:

```
permit ip 192.168.10.0/24 any
deny   ip any any
```

Questa è la risposta “secca” corretta se il docente intende una regola di policy generale.

---

### **Soluzione “da 30 e lode” con firewall stateful (iptables / netfilter)**

In un firewall reale, è fondamentale non bloccare le risposte ai pacchetti già ammessi. Quindi:

- **Regola 1**: consenti ritorno di connessioni già stabilite
    
- **Regola 2**: consenti nuovi flussi dalla sottorete autorizzata verso Internet
    
- **Regola 3**: blocca il resto

Esempio iptables (catena FORWARD, firewall tra LAN e WAN):

Assumiamo:

- interfaccia LAN: eth1
    
- interfaccia WAN: eth0
    
- sottorete autorizzata: 192.168.10.0/24
    

```
# 1) Permetti traffico di ritorno (connessioni già stabilite)
iptables -A FORWARD -i eth0 -o eth1 -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# 2) Permetti uscita verso Internet SOLO dalla sottorete autorizzata
iptables -A FORWARD -i eth1 -o eth0 -s 192.168.10.0/24 -m conntrack --ctstate NEW,ESTABLISHED -j ACCEPT

# 3) Blocca tutte le altre sottoreti/host che tentano di uscire su Internet
iptables -A FORWARD -i eth1 -o eth0 -j DROP
```

**Commento tecnico essenziale:**

- La prima regola garantisce che le risposte (SYN/ACK, pacchetti dati di ritorno, ICMP correlati) non vengano bloccate.
    
- La seconda regola implementa il vincolo “solo quella sottorete”.
    
- La terza chiude tutto il resto (principio di “default deny”).

Se il firewall fa anche NAT (tipico caso casalingo/aziendale), aggiungeresti anche:

```
iptables -t nat -A POSTROUTING -o eth0 -s 192.168.10.0/24 -j MASQUERADE
```

Ma nel testo non viene richiesto esplicitamente: è un dettaglio che puoi citare come completamento architetturale.

---

## **Esercizio 1 — Esame 16-06-2015**

**Testo:** “Si consideri la seguente risposta HTTP. Dopo aver descritto la risposta riga per riga, si presenti una richiesta HTTP che può aver generato tale risposta.”

Di seguito tratto la risposta **riga per riga**, spiegando sia il significato protocollare (HTTP/1.1) sia le implicazioni pratiche (caching, interpretazione del corpo, trasferimento “chunked”), e poi propongo una **richiesta HTTP plausibile** che produce esattamente una risposta di quel tipo.

---

# **1) Descrizione riga per riga della risposta HTTP**

La risposta mostrata è (riassunta):

```
HTTP/1.1 200 OK
Date: Sun, 14 Jun 2015 09:51:49 GMT
Server: Apache
X-Powered-By: PHP/5.2.17-pl0-gentoo
Expires: Mon, 01 Jan 2001 00:00:01 GMT
Cache-Control: no-cache, must-revalidate
Content-Type: text/javascript
Transfer-Encoding: chunked
Content-Type: text/html

20a7
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd"
<HTML>
<HEAD>
[...]
</HEAD>
<BODY ...>
...
</BODY>
</HTML>
23e
[...]
0
```

Di seguito la spiegazione puntuale.

---

## **1.1 Status line**

### **HTTP/1.1 200 OK**

- **HTTP/1.1**: indica la versione del protocollo usata nella risposta. In HTTP/1.1 valgono regole importanti come:
    
    - connessione **persistente** di default (salvo Connection: close);
        
    - presenza tipica di meccanismi di caching e richieste condizionali.
        
    
- **200**: codice di stato **Success**. Significa che la richiesta è stata gestita con successo e che il server sta restituendo il contenuto della risorsa richiesta.
    
- **OK**: reason phrase, testo descrittivo non vincolante per la semantica (utile per lettura umana).

---

## **1.2 Header di data e identificazione software**

### **Date: Sun, 14 Jun 2015 09:51:49 GMT**

- È la data/ora in cui il server ha generato la risposta, espressa in formato HTTP-date (RFC 7231) e in **GMT/UTC**.
    
- È importante per:
    
    - caching (valutare freshness/expired),
        
    - debug e sincronizzazione.

### **Server: Apache**

- Indica (in forma dichiarativa) il software server HTTP (qui Apache).
    
- Non è obbligatorio, ma molto comune.
    
- Nota “da 30 e lode”: esporre dettagli server può avere implicazioni di **information disclosure** (fingerprinting), anche se in ambito accademico si descrive solo il significato.
    
### **X-Powered-By: PHP/5.2.17-pl0-gentoo**

- Header non standard (“X-”), tipicamente aggiunto dallo stack applicativo.
    
- Qui comunica che la risposta è stata generata/gestita da un runtime **PHP** (versione e distro).
    
- Anche questo ha implicazioni di sicurezza (rivela versione) ma a livello HTTP è semplicemente un metadato informativo.
    

---

## **1.3 Header di caching: Expires e Cache-Control**

### **Expires: Mon, 01 Jan 2001 00:00:01 GMT**

- Expires specifica una data oltre la quale la risorsa deve considerarsi **scaduta** (non più “fresh”) e quindi non riutilizzabile senza validazione.
    
- La data indicata è **molto nel passato** rispetto a Date (2001 vs 2015): ciò equivale a dire “questa risorsa è già scaduta appena la ricevi”.
    
- Effetto pratico: un client/cache **non dovrebbe** usare una copia locale senza contattare di nuovo il server.

### **Cache-Control: no-cache, must-revalidate**

Qui ci sono **due direttive**:

- **no-cache**: non significa “non memorizzare”, ma significa che **anche se memorizzi**, **non puoi riutilizzare** la risposta senza prima fare **revalidazione** col server (tipicamente con If-Modified-Since / If-None-Match e risposta 304 Not Modified).
    
- **must-revalidate**: rafforza il vincolo: una cache, se la risorsa è scaduta, **deve** revalidare; non può servire contenuto “stale” in autonomia.
  
**Conclusione caching:** combinando Expires nel passato + no-cache, must-revalidate, il server sta dicendo:

> “Puoi anche conservarla, ma ogni volta che la vuoi usare devi chiedermi conferma.”

---

## **1.4 Header di tipo contenuto: Content-Type (qui c’è un’anomalia importante)**

### **Content-Type: text/javascript**

- Indica il tipo MIME del corpo. text/javascript suggerisce che il contenuto sia un file JavaScript.
    
- Serve al browser per interpretare il body e decidere come trattarlo (rendering, esecuzione script, download, ecc.).

### **Content-Type: text/html**

- Questa riga indica invece che il contenuto è **HTML**, cioè pagina da renderizzare.
    
- Dato che il body che si vede contiene effettivamente markup HTML (```<!DOCTYPE ...> <HTML> <HEAD> <BODY> ...```), **text/html** è coerente col contenuto.

### **Nota critica (importantissima, tipica domanda-trabocchetto)**

Nella risposta compaiono **due header Content-Type**, cosa che **non è corretta** in una risposta ben formata (per una singola rappresentazione della risorsa ci si aspetta un solo Content-Type).

Cosa può significare in pratica:

1. **Errore di configurazione / sovrascrittura**: un componente (es. PHP o un modulo Apache) imposta Content-Type: text/javascript, poi un altro componente lo sovrascrive a text/html (o viceversa).
    
2. **Comportamento del client**: molti parser adottano la regola empirica “l’ultimo header vince”, ma non è una garanzia universale; in generale è una situazione ambigua e non desiderabile.
    
3. **Interpretazione più sensata per l’esame**: evidenziare l’anomalia e concludere che, guardando il corpo, il tipo corretto è **text/html**.
    

Questa osservazione è esattamente il tipo di dettaglio che distingue una risposta “buona” da una “da 30 e lode”.

---

## **1.5 Trasferimento del corpo: chunked transfer encoding**

  

### **Transfer-Encoding: chunked**

- In HTTP/1.1, Transfer-Encoding: chunked significa che il body è inviato in **blocchi (chunk)**, ciascuno preceduto dalla sua lunghezza in **esadecimale**.
    
- È usato quando il server:
    
    - non conosce in anticipo la dimensione totale (contenuto generato dinamicamente),
        
    - oppure vuole iniziare a trasmettere subito senza calcolare Content-Length.
        
    
Con chunked:

- **non** si usa Content-Length (o comunque non è quello che determina la lunghezza del body),
    
- il body termina con un chunk di dimensione **0**.
    

---

## **1.6 Separatore header/body**

Dopo gli header c’è una riga vuota (CRLF CRLF) che separa:

- **header section**
    
- **message body**


È il meccanismo standard HTTP.

---

## **1.7 Corpo “chunked”: lettura corretta dei pezzi**

Nel body compaiono numeri come:

### **20a7**

- È la dimensione del **primo chunk** in **hex**.
    
- Significa: “nei prossimi 0x20a7 byte c’è contenuto del body”.
    
Subito dopo infatti si vede HTML:

```
<!DOCTYPE HTML PUBLIC ...
<HTML>
<HEAD>...
</HEAD>
<BODY ...>...
</BODY>
</HTML>
```

Poi compare:

### **23e**

- È la dimensione del **chunk successivo**, sempre in hex (0x23e byte).

Poi:

### **0**

- Chunk finale di dimensione zero: indica **fine del body**.
    
- Dopo 0 possono esistere “trailer header” (non mostrati qui), ma spesso non ci sono.
    

---

# **2) Richiesta HTTP plausibile che può aver generato tale risposta**

Una risposta 200 OK con body HTML e trasferimento chunked è tipicamente generata da una semplice richiesta **GET** a una risorsa (spesso dinamica: PHP) su Apache.

In HTTP/1.1 la richiesta deve includere almeno Host. Un esempio minimo ma realistico (compatibile con questa risposta) è:

```
GET /index.php HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: it-IT,it;q=0.9
Connection: close
```

Spiegazione essenziale del perché questa richiesta “genera” una risposta come quella:

- GET /index.php: una pagina generata da PHP giustifica X-Powered-By: PHP/... e l’uso di chunked (contenuto prodotto dinamicamente).
    
- Host: necessario in HTTP/1.1.
    
- Accept: text/html ...: coerente col fatto che il server manda HTML.
    
- Connection: close: non è obbligatorio, ma rende coerente lo scenario d’esame senza parlare di persistenza (in ogni caso chunked funziona anche con keep-alive).
    
Se vuoi una variante ancora più “pulita” e minimale (da consegna), puoi usare:

```
GET /test HTTP/1.1
Host: example.com
Connection: close
```

---

# **3) Nota conclusiva “da lode” (brevissima ma ad alto valore)**

- La risposta è formalmente HTTP/1.1 con **body chunked**, corretta struttura generale.
    
- L’unica anomalia rilevante è la presenza di **due** **Content-Type** discordanti; osservando il corpo, il tipo coerente è **text/html**, quindi l’header text/javascript è verosimilmente un errore di configurazione o un residuo di generazione lato server.