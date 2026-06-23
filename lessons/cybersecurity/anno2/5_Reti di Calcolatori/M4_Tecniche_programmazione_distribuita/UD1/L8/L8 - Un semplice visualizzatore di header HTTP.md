## **Lezione 8: Un semplice visualizzatore di header HTTP**

### **1. Introduzione**

Il **visualizzatore di header HTTP** è uno strumento didattico utile per **osservare il dialogo tra client e server web**.  
Un esempio pratico è l’**HTTP Viewer di Rex Swain** (disponibile su [www.rexswain.com/httpview.html](http://www.rexswain.com/httpview.html)), che consente di analizzare in dettaglio le **richieste e risposte HTTP** generate durante una normale comunicazione web.

L’obiettivo è comprendere **come funziona il protocollo HTTP**, cioè il linguaggio con cui browser e server si scambiano informazioni.

---

### **2. Come usare l’HTTP Viewer**

L’interfaccia è semplice: basta inserire un **URL** e fare clic su **Submit**.  
Si può quindi analizzare **l’header** e, se desiderato, anche il **contenuto** della risposta del server.

#### **Opzioni principali**

- **URL**  
    Deve sempre iniziare con `http://`.  
    (Il visualizzatore non supporta connessioni HTTPS, cioè cifrate).
    
- **Request Type**
    
    - `GET`: richiesta predefinita, mostra sia l’header che il contenuto della pagina.
        
    - `HEAD`: mostra solo l’header HTTP, senza scaricare il corpo del documento.
        
    - `TRACE`: esegue un semplice eco della richiesta (poco utile nella pratica).
        
- **Version**  
    Permette di scegliere la versione del protocollo HTTP.
    
    - `HTTP/1.1` è la più recente e diffusa.  
        Attenzione: in `HTTP/1.1` il server può inviare dati **“spezzettati”** (chunked transfer), cioè divisi in segmenti.
        

---

### **3. Opzioni di visualizzazione**

- **Display Format**
    
    - `Auto-Detect`: opzione predefinita; riconosce automaticamente il tipo di contenuto (`Content-Type`) e sceglie il formato più adatto.
        
    - `Text`: forza la visualizzazione testuale (utile per HTML, CSS, JS).
        
    - `Hex`: visualizza i dati in esadecimale (utile per immagini o file binari).
        

---

### **4. Opzioni aggiuntive del visualizzatore**

#### **User-Agent**

Permette di impostare una **stringa di identificazione del browser**.  
Alcuni server rispondono in modo diverso a seconda del client dichiarato.  
Se non viene specificato nulla, l’HTTP Viewer utilizza la stringa del browser corrente.

#### **Referer**

Campo opzionale che comunica al server **da quale URL si è arrivati** alla risorsa richiesta.  
Può influenzare la risposta del server, che può:

- personalizzare i contenuti,
    
- ottimizzare la cache,
    
- o registrare statistiche di provenienza (back-link).
    

> ⚠️ Il campo `Referer` non dovrebbe essere inviato se l’URL è stata digitata manualmente, cioè senza provenire da un’altra pagina.

#### **Accept-Encoding**

Serve a dichiarare **quali formati compressi** il client è in grado di gestire.  
Esempio: `gzip`, `compress`, `deflate`.  
Il server potrà inviare la risposta in uno di questi formati, riducendo la dimensione dei dati.

#### **Auto-Follow Location**

Se attiva, il visualizzatore **segue automaticamente i reindirizzamenti HTTP**.  
Quando un server risponde con un header `Location:`, l’HTTP Viewer:

- esegue automaticamente la nuova richiesta,
    
- fino a un massimo di **4 reindirizzamenti consecutivi**.
    

---

### **5. Cosa mostra l’HTTP Viewer**

#### **a. Sezione “Header”**

Contiene le **informazioni tecniche** che il browser riceverebbe, ma che normalmente non mostra.  
Esempi di campi tipici:

- `Last-Modified:` → data dell’ultima modifica della risorsa.
    
- `Set-Cookie:` → chiede al browser di salvare un cookie locale.
    
- `Location:` → indica un nuovo indirizzo verso cui il browser deve reindirizzarsi.
    
- `Transfer-Encoding:` → specifica se la risposta è inviata a blocchi (_chunked_).
    

#### **b. Sezione “Content”**

Mostra il **corpo della risposta HTTP**, cioè i dati veri e propri (testo, HTML, immagini, ecc.).  
In modalità testuale, i caratteri speciali sono rappresentati in modo esplicito:

|Simbolo|Significato|Codice esadecimale|
|---|---|---|
|`(LF)`|Line Feed (nuova riga)|`0A`|
|`(CR)`|Carriage Return|`0D`|
|`(HT)`|Tabulazione orizzontale|`09`|
|`(00)`|Byte nullo|`00`|

---

### **6. Esempio di utilizzo pratico**

Prova a inserire nel campo URL:

```
http://www.amazon.com
```

e seleziona l’opzione **Auto-Follow Location**.

Il risultato mostrerà che:

- il server di Amazon **reindirizza due volte** prima di arrivare alla pagina finale;
    
- durante questi passaggi, **vengono impostati vari cookie** (`Set-Cookie`).
    

Se nel tuo browser esistono già cookie di Amazon, la risposta del server sarà diversa:  
potrebbe, ad esempio, salutarti per nome o caricare automaticamente preferenze salvate.

---

### **7. Applicazioni e vantaggi**

L’HTTP Viewer è utile per:

- **analizzare le richieste HTTP reali**,
    
- **debuggare applicazioni web**,
    
- **osservare i reindirizzamenti** e i cookie,
    
- **studiare gli header** inviati dai server moderni.
    

Può anche servire per vedere il **testo sorgente** di file `.js` o `.css` direttamente dal server, senza bisogno del browser.

---

### **8. Altri strumenti simili**

Nella home page dell’autore sono disponibili altri strumenti didattici e dimostrativi, tra cui:

- esempi in **APL, REXX, KEDIT, Perl, HTML**;
    
- strumenti per analizzare **cookie, form, variabili CGI, colori RGB** e **Server Side Includes (SSI)**.
    

---

### **9. Conclusione**

L’**HTTP Viewer** di Rex Swain è un laboratorio interattivo per capire **come avviene la comunicazione tra browser e server web**.  
Permette di osservare in chiaro:

- gli **header** che regolano la trasmissione;
    
- il **contenuto** inviato o ricevuto;
    
- i meccanismi di **redirect, compressione e caching**.
    

> In sintesi: è uno strumento semplice ma potentissimo per “guardare sotto il cofano” del protocollo HTTP, cioè il cuore del Web.