# **Lezione 2: MIME (Multipurpose Internet Mail Extensions)**

---

### **1. Il formato base dei messaggi e-mail**

Un messaggio di posta elettronica è composto da due parti fondamentali:

![](imgs/Pasted%20image%2020260225161953.png)

1. **Intestazione (Header)**  
    Contiene le informazioni di servizio necessarie per gestire il messaggio.  
    Alcuni esempi di righe tipiche:
    
    ```
    From: alice@crepes.fr
    To: bob@hamburger.edu
    Subject: Saluti da Parigi
    ```
    
2. **Corpo (Body)**  
    Contiene il vero contenuto del messaggio, scritto in **caratteri ASCII**.
    

Tra intestazione e corpo è sempre presente **una riga vuota**, che funge da separatore.  
SMTP, infatti, interpreta tutto ciò che precede la riga vuota come intestazione, e tutto ciò che segue come corpo del messaggio.

---

### **2. Limiti del formato originale e nascita di MIME**

Il protocollo **SMTP** impone che **tutti i dati trasmessi siano codificati in ASCII a 7 bit**.  
Questo rappresentava un grave limite: non era possibile inviare immagini, file binari, video o testo in lingue con caratteri speciali.

Per superare questo problema nacque **MIME (Multipurpose Internet Mail Extensions)**, uno **standard di estensione del formato e-mail**.  
MIME consente di:

- dichiarare **tipi di contenuto diversi dal semplice testo**;
    
- **codificare** dati binari in formato ASCII;
    
- combinare più parti (testo, immagini, allegati, audio, ecc.) in un unico messaggio.
    

---

### **3. Struttura di un messaggio MIME**

Un messaggio MIME aggiunge nuove righe all’intestazione per dichiarare:

- la **versione** del protocollo MIME usata;
    
- il **tipo di contenuto** trasmesso (testo, immagine, file...);
    
- il **metodo di codifica** usato per convertire i dati binari in ASCII.
    
![](imgs/Pasted%20image%2020260225162033.png)

Esempio:

```
From: alice@crepes.fr
To: bob@hamburger.edu
Subject: Picture of yummy crepe
MIME-Version: 1.0
Content-Transfer-Encoding: base64
Content-Type: image/jpeg

(base64 encoded data)
```

Significato dei campi:

- `MIME-Version: 1.0` → indica che il messaggio segue lo standard MIME.
    
- `Content-Transfer-Encoding` → specifica **come i dati sono stati codificati** (ad esempio, `base64` o `quoted-printable`).
    
- `Content-Type` → descrive **che tipo di dati** contiene il messaggio (ad esempio, `image/jpeg`, `text/html`, `application/pdf`).
    

---

### **4. Tipi di contenuto MIME**

Ogni tipo MIME è identificato da una coppia **`type/subtype`**, eventualmente seguita da parametri aggiuntivi.  
Ecco i principali tipi:

#### **a. Testo**

- Esempi:  
    `text/plain` → testo semplice  
    `text/html` → testo formattato in HTML
    
- Parametri possibili:  
    `charset=us-ascii`, `charset=utf-8`
    

#### **b. Immagini**

- Sottotipi: `image/jpeg`, `image/gif`, `image/png`
    

#### **c. Audio**

- Sottotipi: `audio/basic` (codifica µ-law 8-bit), `audio/32kadpcm` (ADPCM a 32 kbps)
    

#### **d. Video**

- Sottotipi: `video/mpeg`, `video/quicktime`
    

#### **e. Applicazioni**

- File binari o dati da elaborare con software specifici.  
    Esempi:  
    `application/pdf`, `application/msword`, `application/octet-stream`

![](imgs/Pasted%20image%2020260225162116.png)

---

### **5. Esempi di intestazioni MIME complete**

**Esempio 1 – Messaggio di testo semplice:**

```
Content-Type: text/plain; charset=us-ascii
Content-Transfer-Encoding: quoted-printable
```

**Esempio 2 – Allegato PDF:**

```
Content-Type: application/pdf; filename=relazione.pdf
Content-Transfer-Encoding: base64
```

---

### **6. Messaggi con contenuti multipli**

Quando un messaggio contiene **più parti eterogenee** (ad esempio testo + immagine o testo + allegato), si utilizza un **messaggio “multipart”**.

![](imgs/Pasted%20image%2020260225162136.png)

Esempio:

```
From: alice@crepes.fr
To: bob@hamburger.edu
Subject: Picture of yummy crepe
MIME-Version: 1.0
Content-Type: multipart/mixed; boundary=98766789

--98766789
Content-Type: text/plain
Content-Transfer-Encoding: quoted-printable

Dear Bob,
Please find a picture of a crepe.

--98766789
Content-Type: image/jpeg
Content-Transfer-Encoding: base64

(base64 encoded image data)
--98766789--
```

Spiegazione:

- `multipart/mixed` → indica che il messaggio contiene **più parti di tipo diverso**.
    
- `boundary=98766789` → definisce il **separatore** tra le varie sezioni del messaggio.
    
- Ogni parte include **le proprie intestazioni MIME** e la **propria codifica**.
    
- Il messaggio termina con il **separatore seguito da due trattini** (`--98766789--`).
    

---

### **7. Altri tipi di contenuti multipli**

MIME consente diverse modalità per combinare parti multiple:

|Tipo Multipart|Significato|
|---|---|
|`multipart/mixed`|Messaggio con allegati di vario tipo|
|`multipart/alternative`|Stesso contenuto in più formati (es. testo e HTML)|
|`multipart/related`|Parti collegate tra loro (es. HTML + immagini inline)|
|`multipart/report`|Messaggi di stato o notifiche di consegna|

---

### **8. Come funziona la codifica dei dati**

Poiché SMTP accetta solo caratteri ASCII, MIME converte i dati binari in testo leggibile.  
I due metodi più diffusi sono:

1. **Base64**
    
    - Converte 3 byte binari in 4 caratteri ASCII.
        
    - Aumenta la dimensione dei dati del 33%, ma garantisce compatibilità totale.
        
    - Usato per immagini, PDF, allegati binari.
        
2. **Quoted-printable**
    
    - Mantiene leggibile il testo, codificando solo i caratteri speciali (es. “è”, “à”, “%”).
        
    - Usato per testi con caratteri non ASCII.
        

---

### **9. Esempio completo di messaggio MIME**

![](imgs/Pasted%20image%2020260225162240.png)

```
From: alice@crepes.fr
To: bob@hamburger.edu
Subject: Picture of yummy crepe
MIME-Version: 1.0
Content-Type: multipart/mixed; boundary=12345

--12345
Content-Type: text/plain; charset=utf-8
Content-Transfer-Encoding: quoted-printable

Ciao Bob!
Ti invio la foto della crêpe promessa.

--12345
Content-Type: image/jpeg
Content-Transfer-Encoding: base64

/9j/4AAQSkZJRgABAQEASABIAAD... (dati codificati)
--12345--
```

Questo messaggio contiene:

- una parte testuale (`text/plain`);
    
- un’immagine JPEG (`image/jpeg`);
    
- entrambe racchiuse in un’unica struttura `multipart/mixed`.
    

---

### **10. Conclusione**

Il protocollo **MIME** ha reso possibile la **posta elettronica moderna**, permettendo di:

- allegare file di qualunque tipo;
    
- inviare messaggi multilingue;
    
- mescolare testo, immagini e allegati nello stesso messaggio;
    
- gestire tutto in modo compatibile con i vecchi server SMTP a 7 bit.
    

In sintesi, **MIME è ciò che ha trasformato l’e-mail da semplice testo ASCII a un mezzo multimediale completo.**