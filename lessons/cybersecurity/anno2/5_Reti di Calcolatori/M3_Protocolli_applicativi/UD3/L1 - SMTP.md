# **Lezione 1: SMTP**

---

### **1. Introduzione alla posta elettronica**

La **posta elettronica** è una delle **infrastrutture applicative più importanti** su Internet e sulle reti IP aziendali, con un'importanza fondamentale per il funzionamento delle organizzazioni.

Gli **elementi principali** di un sistema di posta elettronica sono:

- **User Agent (UA)** → il programma che l'utente usa per scrivere e inviare e-mail (es. Outlook, Thunderbird). Come visto nei moduli precedenti, serve per preparare il messaggio di posta.
- **Mail Server** → il programma (MTA, Message Transfer Agent) che si occupa dell'invio dei messaggi via SMTP ad altri mail server.
- **Mailbox** → la directory che contiene tutti i messaggi ricevuti da un certo utente.

---

### **2. Il ruolo del Mail Server e la mailbox**

Ogni **mail server** contiene due componenti fondamentali:

1. **Coda dei messaggi in uscita** → raccoglie le e-mail da spedire.
2. **Mailbox utente** → contiene i messaggi ricevuti.

![](imgs/Pasted%20image%2020260622223802.png)

> ⚠️ Distinzione importante: il **mail server ricevente** può gestire le mailbox in due modi distinti.
>
> - **Configurazione storica**: il destinatario ha un account sulla stessa macchina dove risiede il mail server. In questo caso il server copia i messaggi direttamente nelle directory mailbox locali degli utenti.
> - **Configurazione moderna** (più frequente): il destinatario non ha un account sulla macchina del mail server (es. ti inviano un messaggio al tuo indirizzo presso un provider come TIN). Il mail server mantiene i messaggi in attesa e l'utente si collegherà via **POP o IMAP** per prelevarli dal proprio PC.

---

### **3. Invio del messaggio: user agent e mail server**

Quando si usa un programma come Outlook per inviare un messaggio, il comportamento dipende da dove si trova il mail server:

- **User agent su macchina diversa dal mail server** → il UA usa **SMTP** per trasferire il messaggio al mail server locale.
- **User agent sulla stessa macchina del mail server** → il UA salva direttamente il file del messaggio nella **directory di spool** del mail server, senza aprire una connessione SMTP.

In entrambi i casi, il mail server si trova con un messaggio pronto per partire. Analizza l'indirizzo del destinatario e apre una connessione SMTP verso il mail server di destinazione.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. SMTP come protocollo bidirezionale**

**SMTP** è il protocollo che viene usato:

- dallo **user agent** per trasferire il messaggio al mail server locale;
- dal **mail server locale** per trasferire il messaggio al mail server del dominio del destinatario.

> 📌 Lo stesso mailer può essere a volte **emittente** (quando trasferisce posta verso un collega destinatario) e a volte **destinatario** (quando riceve posta da altri mailer). SMTP è fondamentalmente lo stesso protocollo in entrambe le direzioni.

Il trasferimento avviene su **connessione TCP, porta 25**, e in genere è **point-to-point**: il mail server locale si collega direttamente al mail server del destinatario, senza passaggi intermedi. La ritrasmissione in più hop non è vietata da SMTP, ma va contro l'idea dell'uniformità di Internet (poter contattare qualsiasi macchina direttamente) ed è un caso non frequente.

---

### **5. Il flusso di consegna completo**

![](imgs/Pasted%20image%2020260622224409.png)

Il processo di consegna è il seguente:

1. Il **user agent** (Outlook) trasferisce il messaggio al **mail server locale** via SMTP.
2. Il mail server locale, con un certo ritardo (gestito dalla coda), contatta via SMTP il **mail server del dominio destinatario**.
3. Il mail server remoto riceve il messaggio via SMTP e lo deposita nella **mailbox del destinatario** (locale o in attesa di prelievo via POP/IMAP).

> ⚠️ Quando il vostro Outlook dice che avete "inviato" un messaggio, vuol dire solo che lo ha trasferito al mail server locale. Il mail server locale di solito lo inoltrerà subito, ma **non esiste nel protocollo una certificazione del delay massimo** tra il momento in cui avete trasferito il messaggio al server locale e il momento in cui questo lo trasmetterà. Esistono però **sistemi di posta temporizzata** per messaggi di particolare importanza, che ricevono conferme da ogni punto del percorso.

---

### **6. Le tre fasi di un dialogo SMTP**

Un'interazione SMTP segue sempre tre fasi:

1. **Handshaking (saluto iniziale)**
    Il client apre la connessione TCP sulla porta 25. Attenzione: **SMTP è non standard** perché è il **server a parlare per primo**, inviando un codice 220 con il proprio nome, anche se è il client ad aver aperto la connessione.
    Il client risponde con `HELO` (o `HELLO`) per comunicare la propria identità. Questa non è una vera autenticazione (non ci sono password): è semplicemente uno **scambio di identità** tra server.

2. **Trasferimento del messaggio**
    - `MAIL FROM: <mittente>` → il server verifica e può rifiutare il mittente (possibile meccanismo di blacklist anti-spam).
    - `RCPT TO: <destinatario>` → il server verifica che il destinatario abbia un account riconosciuto sulla macchina. Si può configurare il server per **non accettare posta da certi mittenti** (blacklist), oppure per non accettare destinatari non registrati.
    - `DATA` → inizia il corpo del messaggio. Il server risponde con **354** (codice 3xx: "tutto ok finora, mandami i dati e poi vediamo"). Il messaggio termina con una **riga contenente solo un punto (.)**, che funge da delimitatore.

3. **Chiusura della connessione**
    Il server risponde con **221** confermando che il messaggio è stato accettato e la connessione può essere chiusa.

Le **righe di comando e risposta** sono in **testo NVT (ASCII esteso)** e terminano con **CR-LF**.

---

### **7. Esempio di interazione SMTP**

Il client SMTP (`crepes.fr`) stabilisce la connessione TCP con il server `hamburger.edu` alla **porta 25**.

![](imgs/Pasted%20image%2020260622224506.png)

```
Server: 220 hamburger.edu
Client: HELLO crepes.fr
     S: 250 HELLO crepes.fr, pleased to meet you
     C: MAIL FROM: <alice@crepes.fr>
     S: 250 alice@crepes.fr... Sender ok
     C: RCPT TO: <bob@hamburger.edu>
     S: 250 bob@hamburger.edu... Recipient ok
     C: DATA
     S: 354 Enter mail, end with "." on a line by itself
     C: Do you like ketchup?
     C: .
     S: 221 hamburger.edu closing connection
```

La riga con il **solo punto** è il delimitatore di messaggi. Il codice 221 conferma che il servizio è stato eseguito correttamente.

---

### **8. Sicurezza e scelta del mail server**

SMTP è normalmente **point-to-point**: i messaggi non passano da server intermedi, riducendo il rischio di intercettazione sui link Internet. Tuttavia:

> ⚠️ Se si usa il mailer di un **provider esterno** (es. quello di un ISP), nulla vieta tecnicamente a quel provider di esaminare il contenuto delle mail in transito. Il rischio che qualcuno monitori continuamente i collegamenti di rete è relativo, ma l'idea che qualcuno possa accedere al mailer del provider e leggere la posta di un'organizzazione è concreta.

Per questo motivo, enti pubblici, grandi aziende e organizzazioni tendono a **gestire i propri server di posta**, mantenendo il controllo del flusso di messaggi.

---

### **9. Riepilogo del flusso e di POP**

| Fase | Azione | Protocollo | Porta |
| :--- | :----- | :--------- | :---- |
| Invio | User agent → mail server locale | SMTP | 25 |
| Trasferimento | Mail server locale → mail server remoto | SMTP | 25 |
| Ricezione | Destinatario scarica i messaggi dal server | POP o IMAP | 110 / 143 |

Una volta recapitato il messaggio nella mailbox del destinatario, entra in gioco il **POP (Post Office Protocol)**: è il protocollo con cui il client va a recuperare la posta elettronica che è arrivata via SMTP al mail server ed è diretta a lui.

![](imgs/Pasted%20image%2020260622224325.png)

Perché è necessario un protocollo separato? Come visto nella sezione 2, il caso oggi più frequente è che il destinatario **non abbia un account** sulla macchina del mail server. Ad esempio, quando qualcuno vi manda un messaggio al vostro indirizzo presso un provider (es. TIN), il mailer del provider riceve il messaggio via SMTP ma non ha una vostra directory locale dove metterlo. Lo mantiene in attesa, e voi — quando vi collegate con il vostro programma di posta — utilizzate **POP** per scaricarne l'intero contenuto sul vostro PC.

> 📌 La configurazione dell'indirizzo del mail server locale fa parte del setup di qualunque user agent: Outlook (o qualsiasi altro client) deve sapere a quale mailer rivolgersi sia per inviare (via SMTP) sia per ricevere (via POP/IMAP).

Esiste anche la variante **IMAP (Internet Message Access Protocol)**, che permette di leggere i messaggi che si trovano sul server **senza scaricarli localmente**: i messaggi restano sul server e il client ne visualizza solo il contenuto, consentendo l'accesso da più dispositivi in modo sincronizzato.

> ✅ SMTP è responsabile **solo della consegna** dei messaggi tra sistemi di posta, non della loro lettura da parte dell'utente finale — compito di POP o IMAP.
