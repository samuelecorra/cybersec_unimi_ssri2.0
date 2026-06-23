# **Lezione 3: WWW e HTTP**

---

### **1. Il Web come sistema di servizi distribuiti**

Il **World Wide Web** è oggi un vero e proprio insieme di **servizi distribuiti**. Le risorse associate a una URL sono custodite in modo distribuito presso vari siti.

![](imgs/Pasted%20image%2020260225163313.png)

Un client che esegue una connessione e manda una richiesta a un sito riceve una risposta che può essere — come vedremo — sia una risposta presa da uno storage del sito, sia una risposta calcolata sul momento. Questa risposta può contenere informazioni che portano il client a fare una richiesta a un **altro sito ancora**: è la navigazione libera che tutti conosciamo, ma può anche essere una serie di operazioni connesse a un **processo aziendale**, in cui il server web remoto diventa in realtà un **servizio** che rappresenta una delle fasi di una sessione di interesse per il client.

> 💡 Questa visione del Web come luogo in cui si compiono operazioni coordinate che costituiscono processi è una visione molto recente e sarà approfondita nei corsi di laurea magistrale.

---

### **2. Il browser come "macchina"**

![](imgs/Pasted%20image%2020260225163335.png)

Il browser è oggi una vera e propria **macchina** nel senso informatico del termine. Al suo interno abbiamo:

- un **controller** capace di azionare stack di protocollo applicativo differenti — non solo HTTP/TCP/IP, ma anche lo stack di trasferimento file (FTP), lo stack di collegamento diretto in emulazione terminale (Telnet), e lo stack della posta elettronica;
- questi stack possono essere attivati in modo **alternativo o parallelo** a seconda della risorsa richiesta;
- quando arrivano dati da uno di questi stack, il controller li passa all'opportuno **attuatore locale**, che può essere:
  - un **interprete di rendering**: visualizza l'HTML sullo schermo;
  - un **motore di esecuzione**: esegue contenuto attivo (JavaScript, Java applet, ecc.).

---

### **3. Tipologie di documenti Web**

In questa nuova visione del Web si distinguono le risorse remote a seconda del **momento in cui viene determinato il contenuto** della risorsa restituita:

1. **Servizi statici**: il contenuto è stato determinato molto prima che arrivasse la richiesta, è salvato staticamente sul server, ed è indipendente dal contenuto della richiesta.
2. **Servizi dinamici**: il contenuto viene determinato **al momento della richiesta**, sulla base dei parametri forniti con la richiesta stessa.
3. **Contenuto attivo**: ciò che il servizio restituisce non è qualcosa da utilizzare direttamente, ma qualcosa da **eseguire localmente sul client** per ottenere la risorsa.

---

### **4. Documenti statici**

![](imgs/Pasted%20image%2020260225163347.png)

Un documento statico è un file memorizzato sul server **così com'è**, salvato e pronto indipendentemente dal contenuto della richiesta. Quando il client invia una GET, il server restituisce il file direttamente senza alcuna elaborazione.

Esempi tipici: file `.html`, immagini `.jpg`/`.gif`/`.png`, documenti `.pdf`.

---

### **5. Documenti dinamici**

La richiesta arriva al server e provoca l'esecuzione di un programma (o script), il quale calcola la risorsa da restituire — eventualmente tenendo conto dei parametri arrivati con la richiesta. Il contenuto della risorsa è **determinato al momento della richiesta**.

#### **a. Con CGI (Common Gateway Interface)**

![](imgs/Pasted%20image%2020260225163402.png)

Il paradigma storico: il browser invia una GET o POST a un programma eseguibile sul server; il server lancia il processo CGI che produce in tempo reale la risposta. Ancora oggi si usa il termine CGI con riferimento a questo schema.

#### **b. Con script lato server**

![](imgs/Pasted%20image%2020260225163419.png)

Oggi è più comune che venga eseguito uno **script** (PHP, Python, Node.js, ecc.) anziché un eseguibile CGI tradizionale. Non cambia nulla dal punto di vista concettuale: il contenuto è sempre determinato al momento della richiesta e può tenere conto dei parametri forniti.

---

### **6. Documenti attivi**

![](imgs/Pasted%20image%2020260225163439.png)

Il server restituisce non la risorsa, ma **contenuto attivo** da eseguire sul client per ottenere la risorsa. Il momento in cui si determina il contenuto che il client userà può essere **successivo alla richiesta**: dipende da quando viene effettivamente eseguito il codice sul client.

#### **a. Applet Java**

Piccoli programmi Java scaricati dal server ed eseguiti nella JVM del browser. Richiedono che il client disponga di una macchina virtuale Java.

#### **b. JavaScript**

![](imgs/Pasted%20image%2020260225163450.png)

Inviato come script, non richiede JVM — solo un interprete nel browser. Il paradigma è comunque di tipo **attivo**: il contenuto è determinato dall'esecuzione dello script sul client, in un momento qualunque successivo alla risposta del server.

---

### **7. Struttura dei messaggi HTTP (riepilogo)**

![](imgs/Pasted%20image%2020260225163502.png)

#### **Messaggio di richiesta (client → server)**

```
<metodo> <URL> <versione HTTP>
<header di richiesta>
<riga vuota>
[corpo — presente solo per POST; assente/vuoto per GET]
```

![](imgs/Pasted%20image%2020260225163514.png)

Esempio di riga di richiesta:

```
GET /index.html HTTP/1.1
```

> 📌 Differenza fondamentale tra HTTP/1.0 e HTTP/1.1: nella versione 1.0 si apre una connessione TCP per ogni richiesta/risposta; nella versione 1.1 si mantiene una **connessione TCP persistente** su cui richieste e risposte continuano a viaggiare, consentendo alla finestra di throughput di restare vicino all'ottimo.

#### **Messaggio di risposta (server → client)**

```
<versione HTTP> <codice di stato> <descrizione testuale>
<header di risposta>
<riga vuota>
[corpo — presente per GET, assente per HEAD]
```

Esempio di riga di stato:

```
HTTP/1.1 200 OK
```

La versione HTTP nella risposta corrisponde a quella comunicata dal client nella richiesta.

---

### **8. Metodi HTTP — tabella completa**

![](imgs/Pasted%20image%2020260225163525.png)

I primi quattro sono i principali, descritti nella lezione precedente. In aggiunta:

| Metodo | Descrizione |
|---|---|
| `GET` | Recupera una risorsa identificata da una URL |
| `HEAD` | Chiede solo lo stato/header della risorsa, senza il corpo |
| `POST` | Invia dati al server (parametri nel payload, non nella URL) |
| `PUT` | Deposita un file nel percorso specificato dalla URL |
| `CONNECT` | Quasi mai usato; serve per il **debugging** — chiede al server di rimandare indietro la richiesta in eco per verificare l'assenza di problemi |
| `OPTIONS` | Chiede al server la **lista dei metodi** a cui risponde. Non tutti i server, ad esempio, consentono di fare una PUT creando una nuova URL. |

---

### **9. Codici di stato HTTP — tabella completa**

![](imgs/Pasted%20image%2020260225163540.png)

![](imgs/Pasted%20image%2020260225163559.png)

| Famiglia | Significato | Note |
|---|---|---|
| **1xx** | Non definita nello standard | Riservata; non usata in pratica |
| **2xx** | Successo | La richiesta è accettata ed eseguita (es. 200 OK; 201 = PUT con creazione di nuova URL sul server) |
| **3xx** | Interlocutori | Non è un errore: occorre continuare l'interazione per concluderla (es. 304 Not Modified: la risorsa non è cambiata dall'ultima richiesta) |
| **4xx** | Errore del client | Errore nel formato o nel contenuto della richiesta (es. 403 Forbidden, 404 Not Found) |
| **5xx** | Errore del server | Errore interno causato dalla richiesta — il server non riesce a soddisfarla (es. 500 Internal Server Error) |

---

### **10. Tipi di header HTTP**

HTTP definisce quattro categorie di header:

![](imgs/Pasted%20image%2020260225163610.png)

#### **Header generali**

Validi per richiesta e risposta (es. `Date`, `Connection`).

#### **Header di richiesta**

![](imgs/Pasted%20image%2020260225163632.png)

Includono i campi visti nella lezione precedente (`Accept`, `Accept-Encoding`, `From`, `User-Agent`, `Referer`, `If-Modified-Since`, `Authorization`, `Pragma`, `Charge-To`) più altri degni di nota:

- **If-Match**: riceve il documento solo se corrisponde a un dato **ETag** (tag di identificazione della versione).
- **If-Range**: riceve il documento **in parte** — molto utile per aggiornare pezzi di pagina senza ritrasferirla interamente.

#### **Header di risposta**

![](imgs/Pasted%20image%2020260225163649.png)

Contengono informazioni sul server e sullo stato della risposta:

- **Retry-After**: il server non può rispondere ora, ma indica una data/ora in cui tornerà disponibile.
- **Server**: nome e numero di versione del server web.
- **Age**: età del documento.

#### **Entity header**

![](imgs/Pasted%20image%2020260225163659.png)

Descrivono il contenuto trasferito, tra cui:

- **Date** / **Last-Modified**: quando il contenuto può cambiare / data di ultima modifica.
- **Location**: posizione attuale del documento se è stato spostato.
- **ETag**: riassunto (hash) della risposta, usabile per confrontare con versioni precedenti o copie in cache.
- **Content-Encoding**, **Content-Language**: tipo di codifica e lingua del contenuto.
- **Content-Range**: parte della risorsa originaria coperta da questa risposta.
- **Content-Type**: tipo MIME del contenuto.

---

### **11. Esempio completo di richiesta e risposta**

Proponiaamo un esercizio tipo esame: recuperare un'immagine al percorso `/usr/bin/image1`, specificando che il client accetta sia GIF che JPEG, in HTTP/1.1.

Prima di costruire la risposta, occorre ragionare:
- È una GET → **nessun corpo nella richiesta**.
- La risposta avrà: riga di stato + header (data, nome server, versione MIME, tipo di contenuto, lunghezza del dato) + **corpo con l'immagine**.

![](imgs/Pasted%20image%2020260225163717.png)

#### **Richiesta (client → server)**

```
GET /usr/bin/image1 HTTP/1.1
Accept: image/gif, image/jpeg
```

#### **Risposta (server → client)**

```
HTTP/1.1 200 OK
Date: Mon, 08 Dec 2008 18:42:20 GMT
Server: Apache/2.0
MIME-Version: 1.0
Content-Type: image/jpeg
Content-Length: 4096

[dati binari dell'immagine]
```

Il codice **200 OK** conferma che la richiesta è soddisfacibile. A differenza della richiesta (che non aveva corpo), la risposta include il **corpo del messaggio** con il documento richiesto.

---

### **12. Conclusione**

HTTP, con la sua struttura testuale a richiesta-risposta, è il fondamento su cui poggia l'intero Web — dai semplici file statici ai complessi servizi dinamici e ai documenti attivi. Comprendere la struttura dei messaggi, i codici di stato e le categorie di header è indispensabile sia per sviluppare applicazioni web che per diagnosticarne il comportamento.
