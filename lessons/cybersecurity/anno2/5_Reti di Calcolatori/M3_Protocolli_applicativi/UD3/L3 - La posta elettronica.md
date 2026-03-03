# **Lezione 3: La posta elettronica**

---

### **1. Origini della posta elettronica**

La **posta elettronica** è una delle tecnologie più antiche e rivoluzionarie di Internet.  
Nasce nel **1972**, quando **Ray Tomlinson** installò su **ARPANET** un sistema capace di **scambiare messaggi tra università collegate in rete**.  
Poco dopo, **John Postel** ne definì il funzionamento standard e scrisse la prima **specifica formale del protocollo SMTP** (_Simple Mail Transfer Protocol_), descritta nella **RFC 821**.

Tutta la posta elettronica spedita su Internet, ancora oggi, si basa su questo stesso principio:

> ogni messaggio viene trasferito usando il protocollo **SMTP**, implementato in numerosi programmi detti **Mail Transfer Agent (MTA)**, come il celebre _Sendmail_.

Un **server SMTP** può inviare e ricevere posta da **qualsiasi altro server SMTP** nel mondo, rendendo il sistema globale e interoperabile.

---

### **2. Server mittente e server ricevente**

In una comunicazione e-mail si distinguono due ruoli principali:

![](imgs/Pasted%20image%2020260225162452.png)

1. **Server mittente (Mail Submission Agent)**
    
    - Riceve il messaggio da un **client di posta** (come Outlook o Thunderbird).
        
    - Lo elabora e lo invia al server destinatario, stabilendo una sessione **SMTP**.
        
2. **Server ricevente (Mail Delivery Agent)**
    
    - Riceve il messaggio dal server mittente.
        
    - Lo deposita nella **mailbox del destinatario**.
        
    - Rende poi disponibile la posta al vero utente finale tramite **protocolli di consegna** come **POP3** o **IMAP**.
        

Il passaggio tra server SMTP e protocolli POP/IMAP rappresenta il confine tra la **fase di trasporto** e la **fase di accesso** del sistema di posta elettronica.

---

### **3. Punti deboli di SMTP**

SMTP è uno dei protocolli più **vecchi e semplici** di Internet.  
Questa semplicità, necessaria per gestire centinaia di messaggi al secondo, comporta anche **vulnerabilità strutturali**.

Infatti:

- le informazioni fornite dal mittente (nome, indirizzo e-mail, dominio) **non vengono verificate** dal server ricevente;
    
- ciò rende possibile **falsificare facilmente** sia il nome del mittente sia il dominio di provenienza.
    

In pratica, un utente malintenzionato può inviare una mail con un **mittente falso**, generando spam o phishing.

---

### **4. Analisi del campo “Received:”**

Ogni messaggio di posta elettronica include, nell’intestazione, uno o più campi `Received:`.  
Questi campi vengono aggiunti **automaticamente** dai server SMTP che gestiscono il messaggio durante il suo percorso.

Esempio reale:

```
Received: from 159.149.70.1 by pollon
(envelope-from <caio@crema.unimi.it>, uid 201)
08 Dec 2008 18:42:20 -0000
```

Questo significa:

- il messaggio è stato **ricevuto** dal server SMTP chiamato `pollon`;
    
- il messaggio **proviene da** un MTA con indirizzo IP `159.149.70.1`;
    
- l’indirizzo del mittente dichiarato è `caio@crema.unimi.it`;
    
- la **user-id** del mittente sul server d’origine è `201`.
    

Questo campo non deve essere confuso con il campo `From:` visibile all’utente (che può essere facilmente falsificato):  
`Received:` appartiene all’intestazione **tecnica SMTP**, ed è generato automaticamente dai server intermedi.

---

### **5. Controlli DNS sul mittente**

Molti server SMTP “diffidenti” adottano alcune precauzioni per verificare la legittimità del mittente.  
Una delle più comuni consiste nel controllare se il **dominio dopo la chiocciola (@)**, presente nel campo `envelope-from`, sia **traducibile tramite DNS**.

Esempio:  
se il messaggio proviene da `user@crema.unimi.it`, il server ricevente può interrogare il DNS per verificare che `crema.unimi.it` **esista davvero**.  
Se il dominio non è risolvibile, il messaggio può essere **rifiutato** come sospetto.

Questo controllo aiuta a bloccare messaggi provenienti da software di posta che generano **campi SMTP falsi o incompleti**, tipici dello spam o dei malware.

---

### **6. Affidabilità dei campi “Received:”**

Un campo `Received:` è affidabile **solo se proviene da un server considerato fidato**.  
Nel nostro esempio, possiamo ritenere attendibile il campo generato da `pollon`, ma non necessariamente quello dei server precedenti.

La parte più importante del campo è l’indirizzo IP (es. `159.149.70.1`), che possiamo verificare tramite:

- una **query DNS inversa** (per risalire al nome del server associato all’IP);
    
- o una ricerca **WHOIS**, per identificare la **rete e l’organizzazione** proprietaria di quell’indirizzo.
    

---

### **7. Esempio di analisi WHOIS**

Eseguendo una ricerca WHOIS sull’indirizzo `159.149.70.1`, si ottiene:

```
# ARIN WHOIS database
inetnum: 159.149.0.0 - 159.149.255.255
netname: UNIMINET
descr: Università degli Studi di Milano
country: IT
remarks: To notify abuse mailto: cert@garr.it
remarks: Multiple-Lans of Milan University
```

Queste informazioni indicano che l’indirizzo IP appartiene alla **rete dell’Università degli Studi di Milano**.  
Questo rafforza la fiducia sull’autenticità del messaggio proveniente da quell’indirizzo.

---

### **8. Tecniche di difesa: Blacklist e Whitelist**

Conoscere l’indirizzo IP del mittente permette di implementare **strategie di filtraggio** sui server SMTP:

- **Blacklist** → elenchi di server noti per inviare spam o messaggi malevoli.  
    Il server di ricezione **rifiuta automaticamente** le connessioni provenienti da quegli IP.
    
- **Whitelist** → elenchi di server ritenuti affidabili.  
    Il server accetta solo le connessioni provenienti da questi indirizzi.
    

Entrambi i metodi migliorano la sicurezza, ma devono essere usati con cautela:

- Le **blacklist** troppo restrittive possono bloccare anche messaggi legittimi.
    
- Le **whitelist** troppo rigide possono **limitare la disponibilità del servizio** o introdurre ritardi indesiderati.
    

---

### **9. Conclusione**

SMTP è un protocollo storico e tuttora indispensabile, ma la sua architettura **non prevede autenticazione né verifica dell’identità**.  
Questo lo rende vulnerabile a spoofing e spam, rendendo necessari **filtri, controlli DNS e analisi dei log** per identificare l’origine dei messaggi.

L’intestazione `Received:` e le verifiche WHOIS restano strumenti preziosi per **tracciare il percorso reale delle e-mail** e distinguere un messaggio legittimo da uno falsificato.