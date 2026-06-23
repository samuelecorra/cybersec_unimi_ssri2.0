# **Lezione 2: HTTP (HyperText Transfer Protocol)**

---

### **1. Che cos'è HTTP**

**HTTP (HyperText Transfer Protocol)** è il protocollo che consente ai browser — situati sul computer degli utenti — di comunicare con i server web. È talmente fondamentale da essere spesso chiamato il **linguaggio del Web**. I server web rispondono normalmente sulla **porta TCP 80**, sebbene siano comuni anche le porte 8080 e 4040.

Il formato del dialogo — cioè i messaggi che HTTP consente di scambiare — è specificato dalla **RFC 1945** dell'IETF.

---

### **2. URI, URN e URL**

Per identificare le risorse che il server detiene e che il client vuole richiedere, si usano degli identificativi. La nozione più generale è quella di **URI (Uniform Resource Identifier)**: un identificativo universale di una risorsa.

Esistono due tipi fondamentali di URI:

- **URN (Universal Resource Name)**: un nome scritto correttamente secondo le regole di un certo sistema di denominazione. Identifica univocamente la risorsa — è un nome permanente, indipendente da dove si trova.
- **URL (Uniform Resource Locator)**: è anch'esso un URN (identifica univocamente la risorsa), ma in più contiene una **ricetta per trovare la risorsa** — cioè le istruzioni necessarie per individuarne la posizione su Internet.

> 📌 Sul Web si utilizzano **URL**: nomi universali che identificano univocamente delle risorse (pezzi di contenuto) e mantengono anche le istruzioni necessarie per identificarne la posizione su Internet.

---

### **3. Struttura di un URL**

```
protocollo://host:porta/percorso/nome_risorsa
```

**Esempi:**

```
http://xxx.myplace.com/www/index.html
http://xxx.myplace.com:8080/cgi-bin/t.exe
```

**Componenti:**

- **Protocollo**: definisce la modalità con cui sarà possibile richiedere la risorsa (es. `http`, `https`, `ftp`, `news`). Le URL si usano anche fuori dal Web, nell'ambito di altri protocolli dove occorre identificare univocamente risorse da trasferire via rete.
- **Host**: è un **FQDN (Fully Qualified Domain Name)**, traducibile via DNS per ottenere l'indirizzo IP del server che detiene la risorsa.
- **Porta**: storicamente 80 per i grandi server web, ma anche 8080 e 4040 sono porte molto comuni.
- **Percorso di filesystem**: il percorso a partire da una radice convenzionale (che dipende dal web server specifico) che identifica la risorsa all'interno del server.

> 💡 Una URL è **globalmente unica su Internet**: il nome host identifica univocamente un solo host su Internet, e all'interno di quell'host il percorso di filesystem individua in modo univoco una risorsa.

---

### **4. Metodi HTTP**

I metodi definiscono l'azione che il client vuole eseguire. I quattro fondamentali sono **GET**, **POST**, **PUT** e **HEAD**.

#### **a. GET**

La GET è il messaggio principe che si invia insieme a una URL per chiedere al server di trasferire quella risorsa.

Ha tre utilizzi principali:

1. **File statico**: se la URL corrisponde a un file (tipicamente HTML — i browser nascono per mostrare file HTML), la GET ha la semantica di una semplice richiesta di trasferimento del file che il browser mostrerà all'utente.

2. **Eseguibile senza parametri (CGI)**: la URL può essere il nome di un eseguibile sul server. L'eseguibile viene lanciato; il suo output viene trasferito al client. Storicamente, gli eseguibili accessibili in questo modo venivano salvati in una directory denominata **CGI (Common Gateway Interface)** — da cui l'acronimo ancora oggi diffuso.

3. **Eseguibile con parametri**: è possibile aggiungere in coda alla URL delle **coppie attributo-valore** da passare come parametri all'eseguibile. Sul server, il programma identificato dal percorso della URL riceverà questi argomenti tramite i meccanismi CGI.

#### **b. POST**

La POST ha la stessa semantica della GET quando si tratta di eseguire un programma sul server (la URL identifica un eseguibile), ma con una differenza cruciale: i parametri non vengono attaccati alla URL, bensì trasportati come **payload del messaggio HTTP**.

È compito del server trasferire quel payload all'eseguibile tramite i meccanismi di input del sistema operativo (sysin). Questo introduce una **mancanza di standardizzazione**: il modo in cui i parametri vengono passati all'eseguibile dipende dall'OS installato sul server (e.g., Windows e Linux usano meccanismi diversi).

> 💡 Questa mancanza di standardizzazione corrisponde però a una maggiore **privacy**: i parametri viaggiano nel payload HTTP e non nella URL (che è nell'intestazione del messaggio), rendendoli meno visibili e meno facilmente intercettabili da proxy o log.

#### **c. PUT**

Serve per prendere un file e depositarlo nel percorso specificato dalla URL — una vera e propria scrittura dal browser al server. Oggi usata **solo in casi particolari**.

#### **d. HEAD**

Richiede una risorsa ma ne ottiene **solo una comunicazione di stato** — non il contenuto. Serve per esempio per scaricare una risorsa solo se il suo stato (es. data di ultima modifica) è più recente della copia detenuta nella cache del browser o di un proxy intermedio.

---

### **5. L'header della richiesta HTTP**

L'header HTTP della richiesta — invisibile all'utente — contiene il nome del metodo (GET, POST, ecc.), la URL e una serie di **campi descrittivi** che il server utilizza per personalizzare la risposta.

> ⚠️ Tutte le affermazioni contenute nell'header non vengono verificate: anche HTTP, come SMTP, è un protocollo abbastanza aperto dal punto di vista della sicurezza — non c'è nessuna forma di controllo da parte del server sulla veridicità di quanto il client dichiara su se stesso.

---

### **6. Campi principali dell'header di richiesta**

#### **a. From**

Contiene l'indirizzo email dell'utente (o meglio del browser) che ha fatto la richiesta. Dovrebbe essere un indirizzo email valido (formato FQDN in poche parole).

> ⚠️ Se c'è un proxy HTTP tra client e server, il campo `From` conterrà l'indirizzo del proxy, non quello dell'utente reale. I server web raramente verificano questo campo (a differenza dei server SMTP), ma alcuni possono rifiutare richieste in cui non è compilato correttamente.

#### **b. Accept**

Contiene una stringa — che segue una grammatica formale e può includere caratteri jolly — che indica i tipi MIME che il browser è in grado di accettare come risposta.

La grammatica formale è la seguente:

```
<field> = Accept: <entry> * [,<entry>]
<entry> = <content type> *[;<param>]
<param> = <attribute> = <float>
<attr> = q / mxs / mxb
<float> = <ANSI-C floating point number>
```

Esempi:

```
Accept: text/html
Accept: audio/basic; q=1
```

Il server può usare questo campo per scegliere quale versione di una risorsa inviare, se sul server sono disponibili più versioni con caratteristiche diverse (es. diverse qualità audio o video).

> ⚠️ Se il campo `Accept` non compare nell'header, il default è **`text/html`** (non testo semplice).

#### **c. Accept-Encoding**

Specifica quali tipi di compressione il browser sa gestire. Il server effettuerà la compressione sulla base di quanto dichiarato.

```
Accept-Encoding: x-compress; x-zip
```

#### **d. User-Agent**

Indica il software client che ha inviato la richiesta, con nome, versione e informazioni sulle librerie installate. Segue anch'esso una piccola grammatica formale.

Eccola:

```
<field> = User-Agent: <product>
<product> = <word> [/<version>]
<version> = <word>
```

Ad esempio:

```
User-Agent: IBM WebExplorer DLL /v960311
```

> ⚠️ Sfortunatamente, la gran parte dei client utilizza **stringhe convenzionali** (es. molti browser inviano "Mozilla" come user-agent, indipendentemente dal browser effettivo). Tanto più generica è la dichiarazione di user-agent, tanto meno il server può personalizzare la propria risposta.

#### **e. Referer**

Indica l'URL del documento da cui il client ha ricavato la URL che sta richiedendo. Nella maggior parte dei casi la URL non è stata digitata dall'utente ma ottenuta cliccando su un link in una pagina precedente; in quel caso l'header può contenere il `Referer` — il server originale da cui quella URL è stata ottenuta.

Questo consente al server ricevente di:
- creare back-link o salvare file di log;
- implementare sistemi di **revenue sharing**: se un utente completa un acquisto dopo aver cliccato su una pubblicità, il referer comunica al sito venditore a quale fonte pubblicitaria attribuire il ricavo.

Esempio:

```
Referer: http://www.myplace.com/ads/advertisement.html
```

#### **f. Authorization**

Permette di aggiungere coppie di sicurezza all'header HTTP. Due forme:

- **Username/password**: forma di autenticazione molto debole, perché la coppia può essere intercettata e riprodotta.
- **Token di sicurezza**: token ottenuti da server di autenticazione esterni come **Kerberos**. I server che richiedono autenticazione risponderanno solo alle richieste contenenti un token che riescono a validare.

```
Authorization: user fred:mypassword
Authorization: kerberos kerberosparameters
```

#### **g. Charge-To**

Contiene informazioni di account per l'addebito. Può essere usato sia per una vera e propria **fatturazione** (se la risorsa è a pagamento) sia per una semplice **analisi statistica** dei costi di gestione del server, attribuendo a ciascun chiamante la propria quota di utilizzo.

#### **h. Pragma**

Pur essendo originariamente pensato come comunicazione al server, in pratica è un'indicazione rivolta al **proxy**. Il valore più comune è:

```
Pragma: no-cache
```

Questo obbliga il proxy a non servire il file dalla propria cache, ma a scaricarlo sempre dal **server originale** — anche quando la copia in cache è identica. Un motivo concreto per usarlo: forzare il server a **loggare l'accesso**. Se il proxy servisse dalla cache, il server non saprebbe che quell'accesso è avvenuto.

#### **i. If-Modified-Since**

Permette di eseguire una **GET condizionale**: la risorsa viene inviata solo se è stata modificata dopo la data specificata. Se la copia sul server non è più recente, il server risponde con:

```
HTTP/1.0 304 Not Modified
```

e il browser usa la copia già presente nella cache. La data di ultima modifica fa parte dello "stato" della risorsa.

---

### **7. Le risposte HTTP**

Quando riceve una richiesta, il server restituisce una risposta formata da:

1. **Linea di stato** (status line)
2. **Header di risposta**
3. **Corpo del messaggio** (di solito HTML, ma può essere qualunque tipo MIME che il client sa visualizzare)

Esempio di status line:

```
HTTP/1.0 200 OK
```

---

### **8. Codici di stato HTTP**

I codici di stato sono a **tre cifre**; la prima cifra identifica la famiglia:

| Famiglia | Significato | Note |
|---|---|---|
| **1xx** | Non utilizzata | Riservata |
| **2xx** | Successo | La richiesta è accettata ed eseguita (es. 200 OK, 250) |
| **3xx** | Redirezione / non necessaria | Non è un errore: servono più informazioni, oppure la richiesta è inutile (es. 304: risorsa non modificata) |
| **4xx** | Errore del client | Errore nel formato o nel contenuto della richiesta |
| **5xx** | Errore del server | Errore interno: il server non riesce a soddisfare la richiesta |

> 📌 La stessa struttura di codici numerici a tre cifre con famiglie semantiche si ritrova in SMTP — un pattern ricorrente nei protocolli Internet.

---

### **9. Header delle risposte**

L'header di risposta precede il contenuto trasferito al client e contiene:

| Campo              | Significato                                                                  |
| ------------------ | ---------------------------------------------------------------------------- |
| `Content-Encoding` | Tipo di codifica/compressione applicata ai dati                              |
| `Content-Length`   | Dimensione del contenuto in byte                                             |
| `Content-Type`     | Tipo MIME della risorsa (es. `text/html`)                                    |
| `Last-Modified`    | Data di ultima modifica della risorsa                                        |
| `Expires`          | Data di scadenza: oltre questa data la copia in cache deve essere aggiornata |
| extension-header   | Intestazioni aggiuntive specifiche del server o dell'applicazione            |

E a seguire ci sarà il corpo-contenuto vero e proprio, di solito in formato HTML, ma può essere qualunque tipo MIME che il client sa visualizzare.

---

### **10. HTTP come protocollo senza stato (stateless)**

HTTP è per definizione un protocollo **stateless**: ogni richiesta fa storia a sé. Due richieste successive identiche ricevono la stessa risposta, indipendentemente da tutto ciò che è avvenuto prima. Il server non mantiene alcuna memoria delle interazioni precedenti con uno stesso client — non esiste il concetto di "sessione" che comprenda più richieste HTTP.

Questo crea problemi per applicazioni che richiedono stato, come l'**e-commerce**: se un utente compila un modulo di acquisto in sette passi, il server non è in grado di sapere che la sua quinta richiesta è parte di quella sequenza — e non deve quindi trattarla come una richiesta indipendente.

---

### **11. Tre tecniche per rendere HTTP stateful**

Esistono RFC che aggiungono a HTTP la capacità di gestire sessioni. Le tecniche principali sono tre:

1. **Cookie** (stato salvato sul client dal server): il server salva un file (**cookie**) sul client, contenente informazioni sulle richieste precedenti, l'autenticazione, ecc. Il client lo presenta al server in ogni GET successiva, consentendo al server di ricostruire la storia delle richieste di quel client.

2. **Stato salvato lato client** (non cookie): uno script sul client mantiene lo stato in memoria locale. A ogni GET, il client re-invia come parametro le risposte ricevute alle GET precedenti (o informazioni su di esse), ricordando al server lo stato della computazione.

3. **Stato salvato lato server** (session key): il server comunica al client una **chiave di sessione** (un identificatore numerico). Il client include questa chiave come parametro in tutte le GET successive della stessa sessione, consentendo al server di recuperare la storia salvata per quella chiave.

> ✅ HTTP è stateless by design, ma può essere reso stateful con una di queste tre tecniche — ciascuna con trade-off diversi tra carico sul client, carico sul server e privacy.
