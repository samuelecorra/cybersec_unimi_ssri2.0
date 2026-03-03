# **Lezione 2: HTTP (HyperText Transfer Protocol)**

---

### **1. Che cos’è HTTP**

**HTTP** è il **linguaggio del Web**, cioè il protocollo di comunicazione che regola lo scambio di messaggi tra:

- i **browser web** (client), e
    
- i **server web** (server).
    

Opera sulla **porta TCP 80** e segue le specifiche definite nella **RFC 1945**.  
HTTP è il fondamento di tutte le applicazioni web: ogni clic, immagine o richiesta passa da una **richiesta HTTP** e da una **risposta HTTP**.

---

### **2. URI, URN e URL**

Per comprendere come HTTP localizza e richiede risorse, bisogna distinguere tre concetti chiave:

|Sigla|Significato|Funzione|
|---|---|---|
|**URI (Uniform Resource Identifier)**|Identifica in modo univoco una risorsa su Internet.|Descrive “qualcosa” a livello logico.|
|**URN (Universal Resource Name)**|Fornisce un **nome permanente** alla risorsa, indipendente dal luogo in cui si trova.|È un identificatore stabile nel tempo.|
|**URL (Uniform Resource Locator)**|Indica **dove** e **come** recuperare la risorsa.|Specifica il protocollo, l’host e il percorso.|

Un **URL** è dunque un tipo particolare di URI che dice **dove** si trova una risorsa e **come** accedervi.

---

### **3. Struttura di un URL**

Un URL ha una struttura generale del tipo:

```
protocol://host:porta/percorso/nome_risorsa
```

#### **Esempi:**

```
http://xxx.myplace.com/www/index.html
http://xxx.myplace.com:8080/cgi-bin/t.exe
```

#### **Componenti:**

- **Protocollo** → `http`, `https`, `ftp`, `news`...
    
- **Host** → nome del server (`xxx.myplace.com`)
    
- **Porta** → di default è `80`, ma può essere diversa (`8080`)
    
- **Percorso di filesystem** → indica la cartella dove si trova la risorsa
    
- **Nome della risorsa** → file o programma da eseguire
    

---

### **4. Metodi HTTP**

I **metodi** definiscono l’azione che il client vuole eseguire sul server.  
I principali sono **GET**, **POST**, **PUT** e **HEAD**.

#### **a. GET**

- Recupera una risorsa identificata da un URL.
    
- Può:
    
    - richiedere una pagina HTML;
        
    - eseguire un programma (es. uno script CGI);
        
    - passare parametri direttamente nell’URL.
        
- È **semplice ma poco sicuro**, perché i parametri diventano visibili nella barra degli indirizzi.
    

#### **b. POST**

- È il metodo preferito per **inviare dati da form HTML**.
    
- Invia i dati nel **corpo del messaggio**, non nell’URL → **maggiore privacy**.
    
- Attiva un programma sul server (es. CGI) che elabora i dati.
    
- I parametri vengono ricevuti nello **standard input** del programma server.
    

#### **c. PUT**

- Serve per **trasferire un file dal client al server**.
    
- È usato nei sistemi di **upload** o nelle **API REST**.
    

#### **d. HEAD**

- Chiede solo l’**intestazione (header)** di una risorsa, senza il corpo.
    
- È utile per controllare **data di modifica**, **dimensione** o **validità** della copia nella cache.
    
- Usato in schemi di **ottimizzazione**: il client scarica la risorsa solo se è stata aggiornata.
    

---

### **5. Le richieste HTTP**

Le **richieste** sono inviate dal client al server e contengono:

- un **header HTTP** (intestazione, invisibile all’utente);
    
- un **URL** della risorsa richiesta;
    
- eventuali **dati** (per POST o PUT).
    

L’**header** include informazioni come:

- tipo di contenuto (`Content-Type`);
    
- lunghezza (`Content-Length`);
    
- browser utilizzato (`User-Agent`);
    
- tipi di contenuto accettabili (`Accept`);
    
- lingua preferita (`Accept-Language`).
    

---

### **6. Campi principali dell’header di richiesta**

#### **a. From**

Indica l’indirizzo e-mail dell’utente che ha effettuato la richiesta.  
Non sempre corrisponde al nome dell’host richiedente, perché può passare attraverso **proxy**.

#### **b. Accept**

Elenca i **tipi di contenuto MIME** che il client è in grado di interpretare.

**Esempi:**

```
Accept: text/html
Accept: audio/basic; q=1
```

Se il campo `Accept` è assente, si assume che il client accetti **testo normale (text/plain)**.

#### **c. Accept-Encoding**

Definisce gli **schemi di codifica** (compressione) accettabili dal client.  
Esempio:

```
Accept-Encoding: x-compress, x-zip
```

#### **d. User-Agent**

Indica il **software client** che ha inviato la richiesta, con nome e versione.  
Esempio:

```
User-Agent: IBM WebExplorer DLL /v960311
```

#### **e. Referer**

Specifica **l’URL della pagina da cui l’utente è arrivato**.  
Serve al server per creare log di navigazione o per individuare collegamenti errati.  
Esempio:

```
Referer: http://www.w3.com/xxx.html
```

#### **f. Authorization**

Usato per i meccanismi di **autenticazione e password**.  
Esempio:

```
Authorization: user fred:mypassword
Authorization: kerberos kerberosparameters
```

#### **g. Charge-To**

Campo opzionale per informazioni di **accounting** o fatturazione.

#### **h. Pragma**

Serve a **controllare la cache** nei proxy.  
Il valore più comune è:

```
Pragma: no-cache
```

Questo obbliga il proxy a ottenere sempre il documento **direttamente dal server originale**, senza usare copie locali.

#### **i. If-Modified-Since**

Permette di eseguire un **GET condizionale**.  
Il server invia la risorsa solo se è stata modificata dopo la data specificata.  
In caso contrario, risponde con:

```
HTTP/1.0 304 Not Modified
```

così il browser può usare la copia già presente nella cache.

---

### **7. Le risposte HTTP**

Quando riceve una richiesta, il **server Web** restituisce una **risposta** formata da:

1. **Linea di stato** (status line)
    
2. **Header di risposta**
    
3. **Corpo del messaggio** (spesso un documento HTML)
    

Esempio di **status line**:

```
HTTP/1.0 200 OK
```

---

### **8. Codici di stato HTTP**

|Codice|Significato generale|Descrizione|
|---|---|---|
|**1xx**|Informativi|Riservati a usi futuri|
|**2xx**|Successo|La richiesta è stata accettata e completata|
|**3xx**|Redirezione|Sono necessarie ulteriori azioni (es. redirect)|
|**4xx**|Errore del client|Richiesta sintatticamente errata o non autorizzata|
|**5xx**|Errore del server|Il server non può soddisfare la richiesta|

---

### **9. Header delle risposte**

Oltre alla linea di stato, l’header può includere:

|Campo|Significato|
|---|---|
|`Content-Encoding`|Tipo di codifica applicata ai dati|
|`Content-Length`|Dimensione del contenuto (in byte)|
|`Content-Type`|Tipo MIME della risorsa (es. `text/html`)|
|`Expires`|Data di scadenza oltre la quale la copia deve essere aggiornata|
|`Last-Modified`|Data di ultima modifica della risorsa|
|`extension-header`|Campi aggiuntivi definiti dal server o da estensioni|

Il corpo del messaggio contiene normalmente il **documento HTML** richiesto.

---

### **10. HTTP come protocollo senza stato**

HTTP è definito come **protocollo senza stato (stateless)**.  
Ogni scambio (connect → request → response → disconnect) è **indipendente** dagli altri:  
il server **non conserva alcuna informazione** sulle interazioni precedenti.

Di conseguenza:

- tra una pagina web e l’altra **non c’è memoria del contesto** dell’utente;
    
- le applicazioni web devono implementare **meccanismi di sessione** (cookie, session ID, token) per mantenere lo stato di login, carrello, ecc.
    

---

### **11. Conclusione**

HTTP è la **spina dorsale del Web**: un protocollo testuale, semplice, scalabile e indipendente dallo stato.  
Conoscere la sua struttura (metodi, header, codici di stato) è essenziale per comprendere **come browser e server dialogano** e per progettare **servizi Web efficienti e sicuri**.

Dalla richiesta `GET` più semplice fino alle API REST moderne, **tutto nel Web nasce da una riga di comando HTTP**.