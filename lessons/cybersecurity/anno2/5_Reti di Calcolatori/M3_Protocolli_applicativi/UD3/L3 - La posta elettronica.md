# **Lezione 3: La posta elettronica**

---

### **1. Origini della posta elettronica**

La **posta elettronica** è una delle tecnologie più antiche e rivoluzionarie di Internet.  
Nasce nel **1972**, quando **Ray Tomlinson** installò su **ARPANET** un sistema capace di **scambiare messaggi tra università collegate in rete**.  
Poco dopo, **John Postel** ne definì il funzionamento standard e scrisse la prima **specifica formale del protocollo SMTP** (_Simple Mail Transfer Protocol_), descritta nella **RFC 821**.

Tutta la posta elettronica spedita su Internet, ancora oggi, si basa su questo stesso principio:

> ogni messaggio viene trasferito usando il protocollo **SMTP**, implementato in numerosi programmi detti **Mail Transfer Agent (MTA)**, come il celebre _Sendmail_.

L'idea originale di Postel era di rendere **ciascun computer** in grado di ospitare un server SMTP e quindi di ricevere e inviare messaggi a qualunque altro server SMTP di Internet. Nel tempo, però, i server SMTP attivi tendono a essere **pochi all'interno di un'organizzazione**, con gli altri computer che fanno riferimento ai servizi di questi server centralizzati.

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

### **3. Il dialogo SMTP e il suo "dualismo"**

Il comando `HELO` — originariamente scritto con una sola "L" per via di limitazioni nella lunghezza dei messaggi presenti nelle prime implementazioni — è forse il più famoso dei comandi obbligatori dei protocolli Internet. Non è un'autenticazione: è semplicemente uno **scambio di nomi** tra server.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In SMTP esiste un **dualismo strutturale**: la stessa informazione (mittente, destinatario) compare **sia come messaggio di protocollo** (nel dialogo SMTP tra i due MTA) **sia come campo dell'intestazione del messaggio** che viene effettivamente trasferito. La **mancata concordanza di formato** tra queste due rappresentazioni è uno dei principali problemi del protocollo.

> ⚠️ Questo duplice utilizzo delle stesse informazioni è fonte di vulnerabilità: mittente e destinatario sono dichiarati nel dialogo di protocollo, ma poi gli stessi valori compaiono (eventualmente diversi) nei campi `From:` e `To:` del messaggio. Non c'è garanzia di coerenza tra i due.

La semplicità di SMTP — necessaria per gestire decine e decine di richieste al secondo — è stata quindi ottenuta a prezzo di una vulnerabilità strutturale: **le informazioni base non vengono verificate** e **possono essere duplicate in modo incoerente**.

---

### **4. Punti deboli di SMTP**

SMTP è uno dei protocolli più **vecchi e semplici** di Internet. Le sue vulnerabilità strutturali derivano da due elementi fondamentali:

1. Le informazioni fornite dal mittente **non vengono verificate** dal server ricevente.
2. Le stesse informazioni sono **ripetute sia come messaggio di protocollo sia come contenuto del messaggio**, rendendo possibile introdurre discrepanze.

In pratica, un utente malintenzionato può inviare una mail con un **mittente falso** (spoofing), generando spam o phishing. Ma anche un'implementazione imprecisa del client di posta (non necessariamente malevola) può generare campi incoerenti.

---

### **5. Analisi del campo “Received:”**

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
    

> 📌 Il campo `envelope-from` (es. `<caio@crema.unimi.it>`) **non è il campo `From:` visibile all’utente** nel messaggio: appartiene all’intestazione tecnica SMTP e contiene l’indirizzo email e la user-id del mittente sul MTA di provenienza. È possibile che il campo `From:` all’interno del messaggio abbia un valore diverso dall’`envelope-from`, e SMTP non impone che i due coincidano.

Questo campo non deve essere confuso con il campo `From:` visibile all’utente (che può essere facilmente falsificato):  
`Received:` appartiene all’intestazione **tecnica SMTP**, ed è generato automaticamente dai server intermedi.

---

### **6. Controlli DNS sul mittente**

Il minimo che quasi tutti i server SMTP fanno quando vengono contattati da un server mittente è una **query DNS** sull'`envelope-from`: si prende il FQDN dopo la `@` (es. `crema.unimi.it`), si risolve via DNS e si confronta l'IP ottenuto con l'indirizzo del server SMTP che si è collegato.

> ⚠️ Questi confronti possono dare risultati **incoerenti** non solo a causa di volute manipolazioni delle intestazioni, ma anche perché il client di posta elettronica dell'utente può aver inserito una forma abbreviata nell'`envelope-from`, non traducibile via DNS, senza alcuna intenzione di contraffazione. Programmare bene un server SMTP che gestisca correttamente tutti questi casi è quindi non banale.

> 💡 L'idea di usare query DNS per controllare i campi SMTP è il primo passo verso un **sistema di autenticazione del mittente**, che è stato più volte proposto su Internet (cercate “SPF”, “DKIM”) e che ha ispirato proposte di standard autenticati per SMTP.

---

### **7. Affidabilità dei campi “Received:” e chain of trust**

Un campo `Received:` è affidabile **solo se proviene da un server considerato fidato**. Esiste una **chain of trust**: se ci fidiamo del server mittente `pollon` (il server SMTP del nostro campus), l’indirizzo IP `159.149.70.1` ci è specificato da quel server fidato — non è necessario che il server destinatario si metta a fare ulteriori controlli. Molti server lo fanno lo stesso per sicurezza aggiuntiva.

La parte più importante del campo è l’indirizzo IP (es. `159.149.70.1`), che possiamo verificare tramite:

- una **query DNS inversa** (per risalire al nome del server associato all’IP);
- il comando **WHOIS**, per identificare la **rete e l’organizzazione** proprietaria di quell’indirizzo.
    

---

### **8. Esempio di analisi WHOIS**

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

### **9. Tecniche di difesa: Blacklist e Whitelist**

Conoscere l’indirizzo IP del mittente permette di implementare **strategie di filtraggio** sui server SMTP:

- **Blacklist** → elenchi di server noti per inviare spam o messaggi malevoli.  
    Il server di ricezione **rifiuta automaticamente** le connessioni provenienti da quegli IP.
    
- **Whitelist** → elenchi di server ritenuti affidabili.  
    Il server accetta solo le connessioni provenienti da questi indirizzi.
    

Entrambi i metodi migliorano la sicurezza, ma devono essere usati con cautela:

- Le **blacklist** troppo restrittive possono bloccare anche messaggi legittimi.
    
- Le **whitelist** troppo rigide possono **limitare la disponibilità del servizio** o introdurre ritardi indesiderati.
    

---

### **10. Conclusione**

SMTP è un protocollo storico e tuttora indispensabile, ma la sua architettura **non prevede autenticazione né verifica dell’identità**.  
Questo lo rende vulnerabile a spoofing e spam, rendendo necessari **filtri, controlli DNS e analisi dei log** per identificare l’origine dei messaggi.

L’intestazione `Received:` e le verifiche WHOIS restano strumenti preziosi per **tracciare il percorso reale delle e-mail** e distinguere un messaggio legittimo da uno falsificato.