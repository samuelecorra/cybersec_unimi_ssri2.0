# **Lezione 3: WWW e HTTP**

---

### **1. Il World Wide Web come sistema distribuito**

Il **World Wide Web (WWW)** è oggi un **servizio client/server distribuito**.  
Un utente, tramite un **browser**, può accedere a **documenti e servizi** ospitati da server remoti sparsi nel mondo.

Tuttavia, il servizio non risiede in un’unica posizione: è **distribuito in più punti**, chiamati **siti web**.  
Ogni sito rappresenta un nodo del sistema globale, che collabora con gli altri per fornire risorse e contenuti agli utenti.

---

### **2. Architettura del WWW**

L’architettura del Web segue il **modello a due livelli** tipico dei sistemi client/server.

![](imgs/Pasted%20image%2020260225163313.png)

- **Il client Web (browser)**:
    
    - accetta richieste dall’utente (clic, URL, form);
        
    - formula **richieste HTTP**;
        
    - interpreta la risposta e visualizza il contenuto.

![](imgs/Pasted%20image%2020260225163335.png)

- **Il server Web**:
    
    - riceve le richieste;
        
    - elabora il contenuto richiesto (pagina statica o generata al momento);
        
    - invia la **risposta HTTP** al browser.
        

Il collegamento tra client e server avviene sempre attraverso il **protocollo HTTP**, che governa il dialogo tra i due.

---

### **3. Tipologie di documenti Web**

Nel WWW i documenti si dividono in **tre grandi categorie**, in base al **momento in cui viene generato il contenuto**:

1. **Documenti statici**
    
2. **Documenti dinamici**
    
3. **Documenti attivi**
    

Vediamoli uno per uno.

---

### **4. Documenti statici**

Un **documento statico** è un file memorizzato sul server **così com’è**, pronto per essere inviato al client senza ulteriori elaborazioni.  

![](imgs/Pasted%20image%2020260225163347.png)

Esempi tipici:

- file `.html` tradizionali;
    
- immagini `.jpg`, `.gif`, `.png`;
    
- documenti `.pdf`.
    

Quando il client invia la richiesta HTTP (metodo `GET`), il server restituisce il file direttamente, senza modificarlo.

**Caratteristiche:**

- generazione **una volta sola**, visualizzazione **molteplici**;
    
- contenuto **invariabile nel tempo**, salvo aggiornamenti manuali del webmaster;
    
- adatto a **siti informativi o brochure digitali**.
    

---

### **5. Documenti dinamici**

Un **documento dinamico** è **generato al momento della richiesta**, in base a dati o parametri forniti dal client.  
Questo tipo di documento è alla base del Web moderno (e-commerce, social network, applicazioni online).

Esistono due principali modalità di generazione dinamica:

#### **a. Con CGI (Common Gateway Interface)**

![](imgs/Pasted%20image%2020260225163402.png)

- Il browser invia una richiesta HTTP (tipicamente `POST` o `GET`) a un **programma sul server**.
    
- Il server avvia un processo CGI che elabora i dati e **produce in tempo reale** una pagina HTML personalizzata.
    
- Esempio: moduli di login, motori di ricerca, moduli di contatto.
    

#### **b. Con script lato server**

![](imgs/Pasted%20image%2020260225163419.png)

- Qui l’elaborazione avviene **direttamente nel server Web**, senza lanciare processi esterni.
    
- Linguaggi comuni: **PHP, ASP, Python, Node.js**.
    
- Più efficiente del CGI classico perché **evita la creazione di processi aggiuntivi**.
    

**Esempio:**  
Una pagina PHP che mostra l’ora corrente:

```php
<html>
  <body>
    Ora corrente: <?php echo date("H:i:s"); ?>
  </body>
</html>
```

Il server genera dinamicamente l’HTML completo e lo invia al browser.

---

### **6. Documenti attivi**

I **documenti attivi** contengono **componenti eseguibili** che operano sul lato client, cioè **nel browser dell’utente**.

Due modalità principali:

#### **a. Applet Java**

![](imgs/Pasted%20image%2020260225163439.png)

- Piccoli programmi scritti in **Java** che vengono scaricati dal server e **eseguiti localmente** nel browser.
    
- Permettono interattività avanzata (grafici, simulazioni, giochi), ma sono oggi poco usati per motivi di sicurezza.
    

#### **b. Script lato client**

![](imgs/Pasted%20image%2020260225163450.png)

- Linguaggi come **JavaScript** permettono di rendere **dinamico e interattivo** il contenuto della pagina, senza ricaricare il server.
    
- Esempi: validazione dei form, animazioni, aggiornamento del DOM.
    

---

### **7. Messaggi di richiesta e di risposta HTTP**

Il dialogo tra browser e server si basa su due messaggi principali:

![](imgs/Pasted%20image%2020260225163502.png)

- **Messaggio di richiesta** (dal client al server)
    
- **Messaggio di risposta** (dal server al client)
    

#### **Riga di richiesta**

Contiene:

![](imgs/Pasted%20image%2020260225163514.png)

```
<metodo> <URL> <versione HTTP>
```

Esempio:

```
GET /index.html HTTP/1.1
```

#### **Riga di stato (della risposta)**

Contiene:

```
<versione HTTP> <codice di stato> <descrizione>
```

Esempio:

```
HTTP/1.1 200 OK
```

---

### **8. Metodi HTTP**

I principali **metodi di richiesta** sono:

- `GET` – recupera una risorsa;
    
- `POST` – invia dati al server (moduli, upload, ecc.);
    
- `HEAD` – chiede solo l’intestazione, senza il corpo;
    
- `PUT` – carica un file sul server;
    
- `DELETE` – elimina una risorsa (raramente usato nei browser comuni).

![](imgs/Pasted%20image%2020260225163525.png)

---

### **9. Codici di stato HTTP**

Ogni risposta HTTP contiene un **codice numerico** a tre cifre che indica l’esito della richiesta.  
Ecco i principali:

![](imgs/Pasted%20image%2020260225163540.png)

![](imgs/Pasted%20image%2020260225163559.png)

|Categoria|Esempi|Significato|
|---|---|---|
|**1xx**|100 Continue|Messaggio informativo (raro)|
|**2xx**|200 OK|Operazione completata correttamente|
|**3xx**|301 Moved Permanently, 302 Found|Redirezione: la risorsa si trova altrove|
|**4xx**|403 Forbidden, 404 Not Found|Errore del client|
|**5xx**|500 Internal Server Error|Errore del server|

Queste risposte consentono al browser di **gestire correttamente l’esito della richiesta**, mostrando messaggi o caricando nuove pagine.

---

### **10. Tipi di header HTTP**

HTTP definisce diversi tipi di **intestazioni (header)**, suddivise in quattro categorie principali:

![](imgs/Pasted%20image%2020260225163610.png)

![](imgs/Pasted%20image%2020260225163632.png)

![](imgs/Pasted%20image%2020260225163649.png)

![](imgs/Pasted%20image%2020260225163659.png)

|Tipo di header|Descrizione|
|---|---|
|**Generali**|Informazioni valide per richiesta e risposta (es. `Date`, `Connection`)|
|**Di richiesta**|Forniti dal client (es. `Accept`, `User-Agent`, `Referer`)|
|**Di risposta**|Forniti dal server (es. `Server`, `Location`, `Set-Cookie`)|
|**Di entità**|Descrivono il contenuto (es. `Content-Type`, `Content-Length`, `Last-Modified`)|

---

### **11. Esempio completo di richiesta e risposta**

#### **Richiesta (client → server)**

```
GET /usr/bin/image1 HTTP/1.1
Accept: image/gif, image/jpeg
Accept-Language: it
Connection: close
```

- Metodo: `GET`
    
- URL: `/usr/bin/image1`
    
- Versione: `HTTP/1.1`
    
- Il client dichiara di accettare immagini GIF o JPEG.

![](imgs/Pasted%20image%2020260225163717.png)

#### **Risposta (server → client)**

```
HTTP/1.1 200 OK
Date: Mon, 08 Dec 2008 18:42:20 GMT
Server: Apache/2.0
MIME-Version: 1.0
Content-Length: 4096
Content-Type: image/jpeg
```

Dopo l’intestazione, il server invia il **corpo del messaggio**, cioè i **dati binari dell’immagine**.

---

### **12. Conclusione**

Il WWW è un sistema distribuito basato su **HTTP**, che rende possibile la comunicazione tra browser e server in modo uniforme e indipendente dalla piattaforma.  
Attraverso **documenti statici, dinamici e attivi**, il Web è passato da semplice rete di pagine collegate a una **piattaforma interattiva globale**, capace di ospitare applicazioni complesse e servizi evoluti.

HTTP, con la sua semplicità testuale e la struttura a richiesta-risposta, resta il **cuore pulsante del Web**, costantemente evoluto ma ancora fedele ai principi nati con la prima versione di TBL (Tim Berners-Lee).