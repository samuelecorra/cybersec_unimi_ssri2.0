# **UD2 – Firewall avanzati**

### **Introduzione**

In questa unità si approfondiscono le **tecniche avanzate di protezione perimetrale**, analizzando come i firewall si comportano in **scenari complessi** e in presenza di **applicazioni dinamiche** o **multi-connessione**.  
Si studiano le **modalità di gestione del traffico** nei protocolli più delicati (SMTP, FTP, RPC), l’uso dei **Dynamic Packet Filters** e le **architetture Proxy**, che permettono un controllo più profondo del contenuto applicativo.

L’unità affronta infine gli **scenari di attacco e difesa** tipici delle moderne infrastrutture di rete e introduce le **architetture Netfilter e IPTables**, base dei firewall nei sistemi Linux.



---

## **Lezione 1: Packet Filtering Avanzati**

### **1. Introduzione**

I firewall basati su **packet filtering** sono ancora largamente utilizzati, ma la crescente complessità delle applicazioni e la diffusione di **traffico cifrato** impongono un’evoluzione verso tecniche più sofisticate.  
Molti attacchi moderni avvengono infatti su **porte non standard** o sfruttano **canali cifrati HTTPS/TLS**, rendendo insufficiente il semplice filtraggio stateful.

Secondo il **SonicWall Report (2018)**, quasi **il 20% dei 700 milioni di attacchi analizzati** proveniva da porte non standard.  
Inoltre, circa il **58% dei siti di phishing** oggi opera sotto **HTTPS**, sfruttando la fiducia legata al lucchetto del browser.

> Il traffico cifrato è un vantaggio anche per gli attaccanti: nasconde i payload malevoli ai firewall di vecchia generazione.

---

## **2. Difficoltà del packet filtering**

Il filtraggio statico (SPF) è efficace per applicazioni semplici come **Telnet**, **SSH** o **rlogin**, dove il **ruolo client-server** è fisso e lo scambio di pacchetti segue un pattern prevedibile di tipo _request/reply_.

Tuttavia, per applicazioni più complesse — come **SMTP**, **FTP** o **RPC** — la gestione diventa molto più delicata:

- i ruoli client/server possono **invertirsi dinamicamente**;
    
- le porte di comunicazione possono essere **negoziate a runtime**;
    
- alcuni protocolli utilizzano **più connessioni TCP parallele**.
    

> In questi casi, un firewall deve essere configurato con estrema attenzione, evitando politiche troppo permissive.

---

## **3. SMTP – Simple Mail Transfer Protocol**

### **3.1. Funzione generale**

SMTP gestisce lo **scambio di messaggi di posta elettronica** tra server di posta, utilizzando la **porta TCP 25**.  
Ogni utente è identificato da un indirizzo del tipo `nomeutente@dominio`.

I client di posta, tuttavia, usano SMTP **solo per l’invio**; per ricevere messaggi si affidano ad altri protocolli:

- **POP3 (Post Office Protocol)**
    
- **IMAP (Internet Message Access Protocol)**
    

---

### **3.2. Evoluzione e sicurezza**

In origine, SMTP **non prevedeva autenticazione**, favorendo la nascita dello **SPAM** e dei **server open relay**.  
Oggi si utilizzano estensioni come:

- **SMTP-AUTH**, per autenticare il mittente;
    
- **STARTTLS**, per cifrare la connessione sulle porte standard (25, 110, 143);
    
- versioni SSL dedicate:
    
    - POP3S → porta 995
        
    - IMAPS → porta 993
        
    - SMTPS → porta 465
        

> Inoltre, il contenuto del messaggio può essere cifrato end-to-end con **PGP**.

---

### **3.3. Principali comandi SMTP**

|Comando|Funzione|
|---|---|
|`HELO` / `EHLO`|Identifica il client SMTP al server|
|`AUTH LOGIN`|Avvia l’autenticazione|
|`MAIL FROM:`|Specifica il mittente|
|`RCPT TO:`|Specifica il destinatario|
|`DATA`|Indica l’inizio del corpo del messaggio|
|`RSET`|Annulla i comandi precedenti|
|`VRFY`|Verifica l’esistenza di un utente|
|`NOOP`|Test di connessione (nessuna operazione)|
|`QUIT`|Termina la sessione|

---

### **3.4. Fasi di una sessione SMTP**

1. **Connessione TCP** alla porta 25 (risposta `220 Ready`)
    
2. **Handshake HELO/EHLO** (risposta `250 OK`)
    
3. **Invio del mittente** con `MAIL FROM:`
    
4. **Invio dei destinatari** con `RCPT TO:`
    
5. **Scrittura del messaggio** con `DATA`, chiusura con `.`
    
6. **Comando `QUIT`** e terminazione della connessione (`221 Closing`)
    

---

### **3.5. Codici di risposta**

|Codice|Significato|Esempio|
|---|---|---|
|**1xx**|Messaggio informativo||
|**2xx**|Successo|`250 Sender OK`|
|**3xx**|Successo parziale (richiede altro comando)|`354 Enter mail, end with "."`|
|**4xx**|Errore temporaneo|`421 Service not available`|
|**5xx**|Errore permanente|`501 Syntax error in parameters`|

---

### **3.6. Protezioni anti-spam di base**

- Verifica dell’esistenza del **dominio mittente**
    
- **Autenticazione obbligatoria**
    
- Associazione tra utente e login
    
- Blocco del relay non autorizzato
    

---

## **4. Packet Filtering per SMTP**

### **4.1. Scenario aziendale**

Si vuole che **solo un server SMTP aziendale** possa comunicare con l’esterno.

```text
smtpSrv := 159.149.70.23
External := not(159.149.70.0/24)
```

---

### **4.2. Prima proposta (incompleta)**

|Direzione|IP sorg.|IP dest.|Prot.|Porta sorg.|Porta dest.|Flag|Azione|
|---|---|---|---|---|---|---|---|
|IN|External|smtpSrv|TCP|>1023|25|1/0|Permit|
|OUT|smtpSrv|External|TCP|25|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

Questa configurazione copre **solo metà del comportamento SMTP**, ignorando le connessioni generate dal server stesso per inviare posta.

---

### **4.3. Configurazione corretta**

|Direzione|IP sorg.|IP dest.|Prot.|Porta sorg.|Porta dest.|Flag|Azione|
|---|---|---|---|---|---|---|---|
|IN|External|smtpSrv|TCP|>1023|25|1/0|Permit|
|OUT|smtpSrv|External|TCP|25|>1023|1|Permit|
|OUT|smtpSrv|External|TCP|>1023|25|1/0|Permit|
|IN|External|smtpSrv|TCP|25|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

> In questo modo si gestiscono entrambe le direzioni: ricezione e invio di posta tra server SMTP.

---

## **5. FTP – File Transfer Protocol**

### **5.1. Struttura**

FTP usa **due processi separati**:

- **PI (Protocol Interpreter)** per comandi e risposte
    
- **DTP (Data Transfer Process)** per il trasferimento dei dati
    

Il server ascolta sulla **porta TCP 21**, ma il trasferimento file avviene sulla **porta TCP 20**.

---

### **5.2. Modalità operative**

|Modalità|Descrizione|
|---|---|
|**Active mode**|Il **server** apre la connessione dati verso il client (porta 20 → porta casuale >1023)|
|**Passive mode (PASV)**|Il **client** apre entrambe le connessioni (verso porta casuale del server)|

> Oggi quasi tutti gli FTP moderni usano **Passive mode** di default (es. browser web).

---

### **5.3. Problemi nel packet filtering**

Nel caso **Active mode**, il firewall deve accettare connessioni **in entrata da porte elevate (>1023)**.  
Questo è **molto pericoloso**, perché:

- espone porte usate anche da servizi e trojan;
    
- consente attacchi di **port scanning** e spoofing.
    

#### Esempio di policy FTP attiva (rischiosa)

|Dir.|IP sorg.|IP dest.|Porta sorg.|Porta dest.|Azione|
|---|---|---|---|---|---|
|OUT|Internal|External|>1023|21|Permit|
|IN|External|Internal|21|>1023|Permit|
|IN|External|Internal|20|>1023|Permit|
|OUT|Internal|External|>1023|20|Permit|
|Any|Any|Any|Any|Any|Deny|

---

### **5.4. FTP Passive mode**

Nel **Passive mode**, il client avvia la connessione dati, mantenendo così il **principio “solo connessioni interne in uscita”**.

|Dir.|IP sorg.|IP dest.|Porta sorg.|Porta dest.|Azione|
|---|---|---|---|---|---|
|OUT|Internal|External|>1023|21|Permit|
|IN|External|Internal|21|>1023|Permit|
|OUT|Internal|External|>1023|>1023|Permit|
|IN|External|Internal|>1023|>1023|Permit|
|Any|Any|Any|Any|Any|Deny|

> È la modalità consigliata, poiché riduce drasticamente i rischi di apertura indesiderata di porte interne.

---

## **6. RPC – Remote Procedure Call**

### **6.1. Funzione e vulnerabilità**

RPC consente a un’applicazione di **eseguire funzioni su un host remoto**.  
Microsoft ha riscontrato gravi vulnerabilità nelle **interfacce DCOM** che utilizzano **RPC su TCP/135**.

Un attaccante può inviare **messaggi malformati** alla porta 135 per eseguire **codice arbitrario** sul sistema remoto.  
Questo è stato il meccanismo alla base del worm **BlueKeep (CVE-2019-0708)**, evoluzione del celebre **WannaCry**.

---

### **6.2. Difficoltà di filtraggio**

Il problema principale del filtraggio RPC è che **le porte sono dinamiche e imprevedibili**.  
Dopo la connessione iniziale su 135 o 111, il server assegna **una porta casuale** per la sessione.

> Di conseguenza, un firewall tradizionale non può sapere in anticipo quali porte aprire, creando enormi buchi di sicurezza.

---

### **6.3. Policy di filtraggio RPC**

|Direzione|IP sorg.|IP dest.|Prot.|Porta sorg.|Porta dest.|Azione|
|---|---|---|---|---|---|---|
|IN|External|rpcSrv|TCP/UDP|>1023|111,135|Permit|
|OUT|rpcSrv|External|TCP/UDP|111,135|>1023|Permit|
|IN|External|rpcSrv|TCP/UDP|>1023|any|Permit|
|OUT|rpcSrv|External|TCP/UDP|any|>1023|Permit|
|Any|Any|Any|Any|Any|Deny||

> Una configurazione simile è troppo permissiva e rischiosa, poiché consente connessioni da esterno a interno su porte alte.

---

### **6.4. Attacchi e contromisure**

- **BlueKeep**: esecuzione remota di codice su sistemi Windows non patchati via RDP (porta 3389)
    
- **Derivati CVE-2019-0887**: vulnerabilità RDP anche in FreeRDP e rdesktop (client Kali Linux)
    
- **Contromisure**:
    
    - bloccare porte RPC/RDP non necessarie
        
    - mantenere sistemi aggiornati
        
    - usare firewall di nuova generazione con ispezione profonda (DPI)
        

---

## **7. Protocolli firewall-friendly**

|Firewall-friendly|Non firewall-friendly|
|---|---|
|**SSH**, **Telnet**|**FTP attivo**, **RPC**, **RDP**|

> I protocolli “friendly” mantengono porte e ruoli statici; quelli “non friendly” richiedono regole dinamiche o port-mapping complessi.

---

## **8. Conclusione**

Il **packet filtering avanzato** richiede una conoscenza profonda dei protocolli applicativi.  
Ogni configurazione deve:

- mantenere una **politica conservativa**;
    
- considerare il comportamento **dinamico** delle applicazioni;
    
- evitare l’apertura indiscriminata di porte alte;
    
- integrare sistemi **stateful** e **DPI-SSL** per il traffico cifrato.
    

> In sintesi: la sicurezza non consiste nel bloccare tutto, ma nel sapere **esattamente cosa permettere** e **perché**.


---