# **Lezione 3: SMTP – Introduzione**

---

### **1. Cos’è SMTP**

Il **Simple Mail Transfer Protocol (SMTP)** è il **principale protocollo per lo scambio di posta elettronica** su Internet.  
È il meccanismo che permette ai server di posta di **inviare, ricevere e smistare i messaggi e-mail** tra utenti appartenenti a domini diversi.

SMTP si basa su **TCP**, quindi garantisce **affidabilità nella trasmissione**.  
A differenza di protocolli come **FTP** o **Telnet**, **non è un protocollo interattivo**: l’utente non comunica direttamente con il server SMTP, ma affida l’invio dei messaggi a un programma che li gestisce automaticamente.

---

### **2. Differenze rispetto a FTP e Telnet**

Mentre FTP e Telnet stabiliscono **sessioni interattive** tra client e server, SMTP funziona in modo **asincrono**:

- i messaggi vengono **accodati in uno spooler**, cioè una coda temporanea sul disco;
    
- un **agente SMTP** si occupa di **prelevarli e spedirli** ai server di destinazione;
    
- se il server di destinazione non è raggiungibile, i messaggi restano in coda e vengono **ritrasmessi automaticamente** in un secondo momento.
    

Questo modello permette al sistema di posta elettronica di funzionare anche in caso di **disponibilità parziale della rete**, garantendo che nessun messaggio venga perso.

---

### **3. Architettura del sistema di posta**

In un sistema SMTP completo interagiscono diversi componenti:

1. **Utente finale** – usa un programma di posta (Mail User Agent, MUA) come Outlook, Thunderbird o un client webmail.
    
2. **MTA (Message Transfer Agent)** – è il programma che si occupa di **trasferire i messaggi** tra server SMTP.
    
    - Nei sistemi UNIX, l’MTA più famoso è **Sendmail**, configurato e gestito dall’amministratore di rete.
        
3. **Server di destinazione** – riceve il messaggio e lo consegna alla casella del destinatario.
    

SMTP specifica **come gli MTA devono dialogare tra loro** per garantire la consegna dei messaggi attraverso Internet.  
Ogni passaggio (dal mittente al destinatario) è gestito tramite **connessioni TCP** e **comandi testuali NVT (Network Virtual Terminal)**, in modo simile a FTP e Telnet.

---

### **4. Struttura di un messaggio e-mail**

Un messaggio di posta elettronica inviato tramite SMTP è composto da due sezioni:

![](imgs/Pasted%20image%2020260225161305.png)

#### **a. Intestazione (Header)**

Contiene **informazioni di servizio** sulla provenienza, il destinatario, il soggetto e il formato del messaggio.  
Esempio:

```
From: crossi@www.accademiavenezia.edu
To: "Anna Gatti" <agatti@mondadori.com>
Subject: Spero tu abbia un paio di sci
Date: Sab, 26 Nov 2005 16:50
MIME-Version: 1.0
X-Mailer: Microsoft Outlook Express 5.0
Content-Type: text/plain
Content-Transfer-Encoding: 7bit
```

- `From:` → indica il **mittente**.
    
- `To:` → indica il **destinatario**.
    
- `Subject:` → contiene l’**oggetto del messaggio**.
    
- `Date:` → specifica **data e ora di invio**.
    
- `MIME-Version` → definisce la versione del formato MIME (utile per gestire allegati, immagini, ecc.).
    
- `Content-Type` → specifica il **tipo di contenuto** (testo, HTML, allegato binario...).
    
- `Content-Transfer-Encoding` → indica la **modalità di codifica** (es. `7bit`, `base64`...).
    

---

#### **b. Corpo del messaggio (Body)**

Segue una riga vuota e contiene il **testo effettivo** del messaggio.  
Esempio:

```
Ciao Anna,
Vista la neve che sta scendendo,
forse arriverò un po’ in ritardo!

Carlo
```

Il corpo è ciò che l’utente legge come contenuto principale dell’e-mail.  
Può essere in **testo semplice** oppure in **formato multiparte (MIME)** se include immagini o allegati.

---

### **5. Funzionamento generale**

Quando un utente invia un’e-mail:

1. Il **client di posta** (MUA) invia il messaggio al **server SMTP** configurato.
    
2. Il server lo **accoda nello spooler** e lo affida al **MTA**.
    
3. L’MTA stabilisce una connessione TCP con il server SMTP del dominio del destinatario.
    
4. Attraverso una sequenza di **comandi NVT**, invia l’intestazione e il corpo del messaggio.
    
5. Il server remoto conferma la **ricezione con codici di stato numerici** (simili a FTP).
    

Tutto il processo è **automatico** e trasparente per l’utente.

---

### **6. Sicurezza e limiti di SMTP**

Originariamente SMTP non prevedeva **cifratura** né **autenticazione**.  
Questo lo rende vulnerabile a:

- intercettazioni di messaggi (sniffing),
    
- invii di spam o spoofing (contraffazione dell’indirizzo mittente).
    

Per rendere il sistema più sicuro, oggi SMTP è spesso usato insieme a:

- **STARTTLS**, per cifrare la connessione;
    
- **SMTP AUTH**, per richiedere credenziali prima dell’invio.
    

---

### **7. Conclusione**

SMTP rappresenta una pietra miliare della comunicazione digitale:  
è il **motore invisibile** dietro ogni e-mail inviata o ricevuta.

La sua architettura asincrona basata su **code di messaggi** e la separazione tra **client utente (MUA)** e **agente di trasferimento (MTA)** lo rendono un protocollo **efficiente, robusto e scalabile**, ancora oggi alla base dell’intero sistema di posta elettronica mondiale.