# **Lezione 1: SMTP**

---

### **1. Introduzione alla posta elettronica**

La **posta elettronica (e-mail)** è una delle **infrastrutture applicative più importanti** su Internet e sulle reti IP aziendali.  
Il suo funzionamento si basa su un insieme coordinato di componenti e protocolli che gestiscono **invio, consegna e ricezione** dei messaggi.

Gli **elementi principali** di un sistema di posta elettronica sono:

- **User Agent (UA)** → il programma usato dall’utente per scrivere, inviare e leggere e-mail (es. Outlook, Thunderbird, Gmail).
    
- **Mail Server** → il computer che gestisce la spedizione e la ricezione dei messaggi.
    
- **Mailbox** → lo spazio riservato a ciascun utente sul server, dove vengono archiviati i messaggi ricevuti.
    

---

### **2. Il ruolo del Mail Server**

Ogni **mail server** contiene due componenti fondamentali:

1. **Coda dei messaggi** → raccoglie le e-mail in **uscita**, in attesa di essere recapitate al server destinatario.
    
2. **Mailbox** → contiene le e-mail **ricevute**, una per ogni utente registrato sul server.

![](imgs/Pasted%20image%2020260225161730.png)

I server comunicano tra loro attraverso protocolli standardizzati.  
Il principale protocollo per la **consegna della posta** è **SMTP (Simple Mail Transfer Protocol)**.

---

### **3. Protocollo SMTP – Funzioni generali**

SMTP si occupa della **trasmissione dei messaggi**:

- dal **client** al **server locale** (quando un utente invia un’e-mail);
    
- e **tra mail server diversi**, durante il recapito verso la destinazione finale.
    

In pratica, **SMTP è attivo sia negli agenti di posta (MUA)** sia nei **mail server (MTA)** che si occupano di smistare la posta.  
Nel contesto SMTP:

- il **client** è il sistema che **invia** il messaggio;
    
- il **server** è quello che **riceve** il messaggio.
    

---

### **4. Meccanismo di trasmissione SMTP**

SMTP utilizza una **connessione TCP**, generalmente sulla **porta 25**, per trasferire la posta elettronica in modo **affidabile**.

Il flusso tipico è il seguente:

1. L’agente o il client invia la mail al **server SMTP locale**.
    
2. Quest’ultimo memorizza temporaneamente il messaggio nella sua **coda di uscita**.
    
3. Il server tenta di **trasferire direttamente** la mail al **server del destinatario** (specificato dal dominio dell’indirizzo e-mail).
    
4. Se il server remoto non è raggiungibile, la mail viene **ritentata periodicamente**, finché non viene consegnata o scade il tempo massimo.
    

Le **ritrasmissioni intermedie** (passaggi attraverso più server) sono oggi **rare**, ma possono ancora verificarsi in reti complesse.

---

### **5. Le tre fasi di un dialogo SMTP**

Un’interazione SMTP segue sempre lo schema **comando/risposta**, in tre fasi principali:

1. **Handshaking (saluto iniziale)**  
    Il client si presenta al server con un messaggio `HELO` (o `EHLO` in versione estesa).  
    Il server risponde confermando l’accettazione della connessione.
    
2. **Trasferimento del messaggio**  
    Il client invia le informazioni sul mittente (`MAIL FROM`), sul destinatario (`RCPT TO`), e poi il contenuto del messaggio (`DATA`).
    
3. **Chiusura della connessione**  
    Dopo la trasmissione, il client chiude la sessione con il comando `QUIT` o semplicemente interrompendo la connessione TCP.
    

Le **righe di comando e di risposta** sono in **testo ASCII**, e terminano con i caratteri **CR-LF** (ritorno carrello + fine riga).  
Le risposte del server includono un **codice numerico** e una **frase esplicativa** (es. `250 OK`).

---

### **6. Relazione tra SMTP e POP**

Una volta che un messaggio è stato **recapitato nella mailbox** del destinatario, non è più SMTP a gestirne l’accesso.  
A questo punto interviene un altro protocollo: il **POP (Post Office Protocol)**.

![](imgs/Pasted%20image%2020260225161801.png)

#### **Flusso di recapito completo:**

1. L’agente di posta dell’utente (MUA) invia il messaggio via **SMTP** al **mail server locale**.
    
2. Il mail server locale comunica con altri server remoti, sempre via **SMTP**, fino a raggiungere il **server del destinatario**.
    
3. Il server destinatario deposita la mail nella **mailbox** dell’utente.
    
4. Infine, l’utente scarica la posta con un **protocollo di accesso** come **POP** (o, oggi più spesso, **IMAP**).

![](imgs/Pasted%20image%2020260225161829.png)

---

### **7. Esempio di interazione SMTP**

Ecco un esempio concreto di sessione SMTP tra due server, `crepes.fr` (mittente) e `hamburger.edu` (destinatario).

**Connessione:**

- Il client SMTP (`crepes.fr`) apre una connessione TCP con il server `hamburger.edu` sulla **porta 25**.

![](imgs/Pasted%20image%2020260225161854.png)

**Dialogo:**

```
S: 220 hamburger.edu
C: HELO crepes.fr
S: 250 HELLO crepes.fr, pleased to meet you
C: MAIL FROM:<alice@crepes.fr>
S: 250 alice@crepes.fr... Sender ok
C: RCPT TO:<bob@hamburger.edu>
S: 250 bob@hamburger.edu... Recipient ok
C: DATA
S: 354 Enter mail, end with "." on a line by itself
C: Do you like ketchup?
C: How about pickles?
C: .
S: 221 hamburger.edu closing connection
```

**Nota:**  
La riga contenente **solo un punto (.)** indica la **fine del messaggio**.  
Tutto ciò che si trova prima viene interpretato come corpo del testo e-mail.

---

### **8. Riassunto concettuale**

|Fase|Azione|Protocollo|Porta|
|---|---|---|---|
|Invio|L’utente invia la mail al server locale|SMTP|25|
|Trasferimento|I server si scambiano i messaggi|SMTP|25|
|Ricezione|L’utente scarica la posta|POP o IMAP|110 / 143|

SMTP è quindi responsabile **solo della consegna** dei messaggi tra sistemi di posta, **non della loro lettura**.

---

### **9. Conclusione**

SMTP è il **cuore del sistema di posta elettronica**:  
assicura che i messaggi partano dal mittente e arrivino, anche attraverso più server, al destinatario corretto.

Il suo modello semplice ma robusto, basato su testo ASCII e connessioni TCP affidabili, ha permesso la costruzione di una delle più durature e fondamentali infrastrutture di Internet.  
Oggi, insieme a POP3 e IMAP, forma la **triade di protocolli** che rende possibile l’intero ciclo di vita dell’e-mail.