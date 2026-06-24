# Formulario — Analisi Richiesta/Risposta HTTP

**Basato su M3/UD4/L1-L2-L3 — Prof. Ernesto Damiani**
Per gli esercizi che chiedono di analizzare, commentare o costruire messaggi HTTP.

---

## 1. Struttura di una URL

```
protocollo://host[:porta]/percorso/risorsa
```

| Componente | Esempio | Note |
|---|---|---|
| **Protocollo** | `http` / `https` | Definisce come si accede alla risorsa |
| **Host** | `di.unimi.it` | FQDN (risolto via DNS) |
| **Porta** | `:80` / `:443` | **Omessa = porta di default**: HTTP→80, HTTPS→443 |
| **Percorso** | `/sesar/index.html` | Path nel filesystem del server |

**Esempio completo:**
```
http://di.unimi.it/sesar/index.html
http://di.unimi.it:8080/cgi-bin/t.exe
```

> **Nota esame:** se la porta non è indicata nella richiesta né nel testo, scrivila comunque in forma esplicita nella risposta: `http://host:80/percorso`.

---

## 2. Struttura del messaggio di richiesta (client → server)

```
<METODO> <URL> <versione HTTP>\r\n
<Header1>: <valore>\r\n
<Header2>: <valore>\r\n
...
\r\n
[corpo — presente solo per POST; assente per GET/HEAD]
```

Esempio reale:
```
GET /index.html HTTP/1.1
Host: www.sito.com
Accept: text/html, image/jpeg;q=0.9
Connection: keep-alive
```

---

## 3. Struttura del messaggio di risposta (server → client)

```
<versione HTTP> <codice stato> <reason phrase>\r\n
<Header1>: <valore>\r\n
<Header2>: <valore>\r\n
\r\n
[corpo — presente per GET, assente per HEAD]
```

Esempio reale:
```
HTTP/1.1 200 OK
Date: Mon, 08 Dec 2008 18:42:20 GMT
Server: Apache/2.0
Content-Type: text/html; charset=utf-8
Content-Length: 4096

<html>...</html>
```

---

## 4. Metodi HTTP

| Metodo | Semantica | Corpo richiesta | Corpo risposta |
|---|---|---|---|
| **GET** | Recupera risorsa identificata dalla URL | No | Sì |
| **HEAD** | Come GET ma risponde con soli header (no corpo) | No | No |
| **POST** | Invia dati al server nel corpo (non nella URL) | Sì | Sì |
| **PUT** | Deposita/sostituisce un file al path della URL | Sì | Opzionale |
| **CONNECT** | Debugging: il server rimanda in eco la richiesta | - | - |
| **OPTIONS** | Chiede la lista dei metodi supportati dal server | No | Sì |

**Differenza GET vs POST:**
- `GET`: i parametri sono **nella URL** (`?nome=val&altro=val`) → visibili, loggabili da proxy
- `POST`: i parametri sono nel **body** → più privati, non nella URL

**Idempotenza:**
- `GET`, `HEAD`, `PUT`, `OPTIONS` → **idempotenti** (più invii = stesso risultato)
- `POST` → **non idempotente** (ogni invio può creare una nuova risorsa)

---

## 5. Header di richiesta — tutti quelli del corso

| Header | Significato | Nota esame |
|---|---|---|
| `Host` | Nome del server (obbligatorio in HTTP/1.1) | Usato per virtual hosting. Assente → server risponde 400 |
| `Accept` | Tipi MIME accettabili in risposta | Contiene quality values `q=` (vedi §6) |
| `Accept-Encoding` | Compressioni accettate (gzip, deflate, br) | Il server comprimerà in base a questo |
| `Accept-Language` | Lingue preferite con `q=` | Es. `it-IT,it;q=0.9,en;q=0.8` |
| `User-Agent` | Identificativo del software client (browser, versione, OS) | Non verificato dal server; stringhe convenzionali (es. Mozilla) |
| `Referer` | URL della pagina da cui si è arrivati al link | Log, revenue sharing, analytics |
| `Authorization` | Credenziali di autenticazione | Es. `Bearer <JWT>`, `Basic <base64>` |
| `If-Modified-Since` | GET condizionale: invia solo se più recente di questa data | Risposta 304 se non modificato |
| `If-Match` | Invia solo se l'ETag corrente corrisponde | 412 se non corrisponde (ottimistic locking) |
| `If-Range` | Scarica solo la parte aggiornata della risorsa | - |
| `Pragma: no-cache` | Ordina al proxy di non servire dalla cache | Il server logga l'accesso |
| `From` | Email dell'utente/browser | Raramente verificato |
| `Charge-To` | Account per fatturazione o analisi costi | Raro |
| `Connection` | `keep-alive` (persistente) / `close` (chiudi dopo la risposta) | Default HTTP/1.1 = keep-alive |
| `Keep-Alive` | Parametri della connessione persistente (timeout in secondi) | Es. `Keep-Alive: 200` |
| `Content-Type` | Tipo MIME del body (solo per POST/PUT) | Es. `application/json`, `multipart/form-data` |
| `Content-Length` | Dimensione del body in byte | Permette al server di sapere quanti byte leggere |

---

## 6. Quality values — `q=`

I valori `q=` compaiono negli header di **content negotiation** (`Accept`, `Accept-Language`, `Accept-Encoding`) e indicano la **preferenza relativa** del client.

**Scala:** da `q=0` (rifiuto esplicito) a `q=1.0` (preferenza massima, default se omesso).

```
Accept: text/html;q=1.0, application/json;q=0.9, */*;q=0.5
```

| Valore `q` | Significato |
|---|---|
| `q=1.0` | Preferenza massima (anche se omesso) |
| `q=0.9` | Alta preferenza, leggermente minore |
| `q=0.8` | Preferenza media |
| `q=0.5` | Fallback di basso livello |
| `q=0` | **Rifiuto esplicito** — non inviare mai questo tipo |

**Esempio da esame reale** (dal 8/05/2026, Es. 2):
```
Accept: ext/xml, application/xml, application/xhtml+xml, text/html;q=0.9,
        text/plain;q=0.8, image/png,*,*;q=0.5
```

| MIME-type | q | Interpretazione |
|---|---|---|
| ext/xml, application/xml, application/xhtml+xml | 1.0 (implicito) | Preferiti |
| text/html | 0.9 | Molto buono |
| text/plain | 0.8 | Accettabile |
| image/png | 1.0 (implicito) | Preferito |
| `*/*` | 0.5 | Qualsiasi cosa, ultima scelta |

> **Non sono probabilità**: sono **pesi** per il server-driven content negotiation. Il server sceglie la rappresentazione con q maggiore tra quelle che sa fornire.

---

## 7. Header di risposta

| Header | Significato |
|---|---|
| `Content-Type` | Tipo MIME del corpo (es. `text/html`, `image/jpeg`, `application/json`) |
| `Content-Length` | Dimensione del corpo in byte |
| `Content-Encoding` | Compressione applicata (es. `gzip`) |
| `Content-Language` | Lingua del contenuto |
| `Last-Modified` | Data di ultima modifica della risorsa |
| `Expires` | Data di scadenza della cache |
| `ETag` | Hash della risorsa — usato per validare cache e If-Match |
| `Location` | Nuovo URL della risorsa (usato con 301/302 redirect) |
| `Server` | Nome e versione del web server (es. `Apache/2.0`) |
| `Date` | Data/ora della risposta |
| `Retry-After` | Il server non è disponibile ora; riprova dopo questo tempo |
| `Age` | Età del documento in cache |
| `Connection: keep-alive` / `close` | Indica al client se la connessione resta aperta |
| `MIME-Version: 1.0` | Versione MIME (raro ma compare negli esempi del prof) |

---

## 8. Codici di stato HTTP

| Famiglia | Significato | Esempi chiave |
|---|---|---|
| **1xx** | Non usata / riservata | — |
| **2xx** | Successo | **200 OK** (GET riuscita), **201 Created** (PUT/POST che crea risorsa) |
| **3xx** | Redirezione / non necessaria | **301** Moved Permanently, **302** Found (redirect temporaneo), **304** Not Modified (cache valida) |
| **4xx** | Errore del client | **400** Bad Request, **401** Unauthorized, **403** Forbidden, **404** Not Found, **412** Precondition Failed (If-Match fallito) |
| **5xx** | Errore del server | **500** Internal Server Error |

**I più richiesti in esame:**

| Codice | Quando scriverlo |
|---|---|
| 200 OK | GET/HEAD riuscita |
| 201 Created | POST/PUT che crea una nuova risorsa |
| 304 Not Modified | If-Modified-Since: la risorsa non è cambiata |
| 400 Bad Request | Richiesta malformata |
| 403 Forbidden | Autenticato ma non autorizzato (blacklist, permessi) |
| 404 Not Found | Risorsa inesistente |
| 412 Precondition Failed | If-Match: ETag diverso (ottimistic locking fallito) |
| 500 Internal Server Error | Crash del server / errore interno |

---

## 9. HTTP/1.0 vs HTTP/1.1 — differenze chiave

| Aspetto | HTTP/1.0 | HTTP/1.1 |
|---|---|---|
| **Connessione TCP** | Una nuova per ogni oggetto (chiusa dopo ogni risposta) | **Persistente di default** (keep-alive) |
| **Header `Host`** | Facoltativo | **Obbligatorio** |
| **Pipelining** | No | Sì (più richieste senza aspettare risposta) |
| **Connessioni per pagina** | 1 TCP per oggetto → N+1 connessioni per pagina con N immagini | 1 TCP per server, riusata per tutti gli oggetti |
| `Connection: keep-alive` | Estensione non standard (non garantita) | Comportamento di default |
| `Connection: close` | Non necessario (default) | Serve per chiudere esplicitamente la connessione persistente |

**Come riconoscere la versione in un esame:**
- `HTTP/1.0` nella request line → connessione non persistente (anche se c'è `Connection: keep-alive`, è non standard)
- `HTTP/1.1` nella request line → persistente di default

---

## 10. Connessione persistente — indicatori

Il browser **richiede connessione persistente** se si verificano uno o più di questi:
1. Versione dichiarata è `HTTP/1.1` (default keep-alive)
2. Header esplicito `Connection: keep-alive`
3. Header `Keep-Alive: <timeout>` (es. `Keep-Alive: 200`)

**Conseguenza pratica:** dopo la risposta alla prima richiesta, la stessa connessione TCP resta aperta e serve per scaricare immagini, CSS, JS, ecc. — senza nuovi three-way handshake.

---

## 11. HTTP stateless → stateful — tre tecniche

| Tecnica | Chi mantiene lo stato | Meccanismo |
|---|---|---|
| **Cookie** | **Client** (salvato dal server) | Il server invia `Set-Cookie` nella risposta; il client lo rimanda in ogni GET successiva |
| **Stato lato client** (non cookie) | **Client** (in memoria) | Uno script sul client rimanda come parametro le risposte precedenti |
| **Session key** | **Server** | Il server dà al client un ID numerico; il client lo manda come parametro in ogni GET della sessione |

---

## 12. Tipi di documenti Web

| Tipo | Quando il contenuto è determinato | Esempi |
|---|---|---|
| **Statico** | Prima della richiesta (file su disco) | HTML, immagini, PDF |
| **Dinamico** | Al momento della richiesta | PHP, CGI, Python, Node.js |
| **Attivo** | Sul client, dopo il download | JavaScript, Java applet |

---

## 13. multipart/form-data — struttura

Usato quando un form HTML invia file (`<form enctype="multipart/form-data">`).

```
POST /upload HTTP/1.1
Host: example.com
Content-Type: multipart/form-data; boundary="sep123"

--sep123
Content-Disposition: form-data; name="campo_testo"

valore del campo
--sep123
Content-Disposition: form-data; name="file_upload"; filename="doc.txt"
Content-Type: text/plain

contenuto del file (byte)
--sep123--
```

**Struttura di ogni parte:**
1. `--boundary` (inizio parte)
2. Header della parte (`Content-Disposition`, opzionale `Content-Type`)
3. Riga vuota
4. Corpo della parte (testo o byte del file)
5. `--boundary--` (fine body, doppio trattino finale)

**`Content-Disposition` in una parte:**
- `name="campo"` → campo testuale del form
- `name="campo"; filename="file.txt"` → upload di file (presenza di `filename` distingue i due)

---

## 14. Come rispondere a un esercizio di analisi HTTP

**Passo 1 — Request line:** identifica metodo + URL + versione.

**Passo 2 — Costruisci l'URL completo:**
```
<protocollo> + "://" + Host: <header> + [:porta] + <path dalla request line>
```
Porta omessa → aggiungi il default (80 per http, 443 per https).

**Passo 3 — Analizza ogni header:**
- `Accept` → elenca i tipi con i rispettivi `q=`, spiega la precedenza
- `Connection` + `Keep-Alive` + versione 1.1 → connessione persistente o non persistente
- `User-Agent` → identifica browser e piattaforma
- `Authorization` → tipo di autenticazione (Basic / Bearer JWT / Kerberos)
- `If-Match` → ottimistic locking con ETag
- `Content-Type` → tipo del body (solo se c'è un body)

**Passo 4 — Risposta a domande specifiche:**
- Connessione persistente? → HTTP/1.1 E/O `Connection: keep-alive` → SÌ
- URL? → ricostruisci con protocollo + host + porta + path
- q=0.8? → preferenza relativa (non probabilità) per content negotiation
- 412 Precondition Failed? → ETag non corrisponde (If-Match fallito)
