## **Lezione 3: Protocolli in SSL/TLS**

### **1. Introduzione generale**

Il protocollo **SSL/TLS** è composto da più _sottoprotocolli_, ognuno con un compito preciso.  
Tutti sfruttano il **Record Protocol** per la trasmissione sicura dei messaggi.

I principali protocolli interni sono:

1. **Change Cipher Spec Protocol**
    
2. **Alert Protocol**
    
3. **Handshake Protocol**
    

Questi tre livelli gestiscono la vita completa di una connessione sicura:

- negoziazione iniziale,
    
- gestione delle chiavi e della cifratura,
    
- segnalazione di errori o avvisi.
    

---

### **2. Change Cipher Spec Protocol**

#### **a. Funzione**

È il protocollo più semplice tra i tre.  
Serve per **attivare i nuovi parametri crittografici** che sono stati negoziati durante l’handshake.

#### **b. Struttura**

- Contiene **un solo messaggio**, composto da **un singolo byte** con valore `1`.
    
- Quando viene ricevuto, indica che il destinatario deve:
    
    - **copiare lo stato “in sospeso” nello stato corrente**,
        
    - **aggiornare la suite di cifratura** (nuove chiavi, algoritmi e MAC) per la connessione in corso.
        

In sintesi:

> Dopo aver concordato le chiavi e gli algoritmi, il messaggio `ChangeCipherSpec` segnala: “Da ora in poi usiamo le nuove regole di sicurezza”.

---

### **3. Alert Protocol**

#### **a. Scopo**

Gestisce la **segnalazione di eventi o errori** durante la comunicazione SSL/TLS.  
Serve a notificare l’entità peer (client o server) di eventuali problemi.

#### **b. Struttura**

Ogni messaggio di _alert_ è composto da **due byte**:

1. **Byte 1 – Livello di gravità**
    
    - `1` → _warning_ (avviso)
        
    - `2` → _fatal_ (errore irreversibile)
        
2. **Byte 2 – Codice di errore specifico**, che identifica il tipo di problema.
    

#### **c. Comportamento**

- Gli _alert_ vengono **compressi e cifrati** secondo lo stato di sessione attuale.
    
- Se il livello è _fatal_, la connessione viene **interrotta immediatamente**.
    
- Altre connessioni appartenenti alla stessa sessione possono continuare, ma **non se ne possono aprire di nuove**.
    

**Esempio:**  
Un alert _fatal: bad_record_mac_ indica che il MAC ricevuto non coincide con quello atteso → possibile manomissione del messaggio.

---

### **4. Handshake Protocol**

#### **a. Obiettivo**

Il cuore del protocollo SSL/TLS.  
Serve a:

- **autenticare client e server**,
    
- **negoziare algoritmi di cifratura e MAC**,
    
- **scambiare o generare chiavi crittografiche condivise**.
    

Dopo la conclusione dell’handshake, inizia la trasmissione sicura dei dati.

#### **b. Struttura dei messaggi**

Ogni messaggio dell’handshake ha tre campi principali:

|Campo|Descrizione|
|---|---|
|**Type**|Identifica il tipo di messaggio (1 byte, tra 10 valori possibili)|
|**Length**|Lunghezza del messaggio (3 byte)|
|**Content**|Parametri e dati del messaggio|

---

### **5. Sequenza dei messaggi di Handshake**

Il protocollo segue una precisa sequenza logica di messaggi:

|Ordine|Messaggio|Contenuto / Funzione|
|---|---|---|
|1|**Hello Request**|Richiesta dal server al client di iniziare un handshake.|
|2|**Client Hello**|Il client invia: versione TLS, numero casuale (_random_), session ID, lista di cipher suite e metodi di compressione supportati.|
|3|**Server Hello**|Il server sceglie e comunica: versione TLS, numero casuale, session ID, cipher suite e metodo di compressione.|
|4|**Certificate**|Il server invia la propria catena di certificati X.509v3 (opzionale anche il client).|
|5|**Server Key Exchange**|Il server comunica i parametri per lo scambio di chiavi (es. Diffie-Hellman) e la firma digitale.|
|6|**Certificate Request**|Il server può richiedere il certificato del client (opzionale).|
|7|**Server Done**|Indica la fine della fase del server.|
|8|**Client Certificate**|(se richiesto) il client invia il proprio certificato.|
|9|**Client Key Exchange**|Il client invia i parametri per generare la chiave condivisa.|
|10|**Certificate Verify**|Il client prova la proprietà del certificato firmando un messaggio.|
|11|**Change Cipher Spec**|Attivazione delle chiavi e algoritmi negoziati.|
|12|**Finished**|Entrambe le parti confermano l’integrità di tutti i messaggi scambiati.|

Dopo il messaggio **Finished**, la connessione è pienamente operativa e i dati applicativi possono transitare in modo cifrato.

---

### **6. Generazione e scambio delle chiavi**

La costruzione delle chiavi TLS avviene in **tre fasi principali**:

#### **a. Pre-Master Secret**

- Il client genera un valore casuale chiamato _pre-master secret_.
    
- Lo cifra con la **chiave pubblica del server** (contenuta nel certificato).
    
- Solo il server, con la sua chiave privata, può decifrarlo.
    

#### **b. Master Secret**

- Entrambe le parti calcolano un _master secret_ combinando:
    
    $$  
    \text{master\_secret} = f(\text{pre\_master\_secret}, \text{client\_random}, \text{server\_random})  
    $$
    
    dove $f$ è una funzione di derivazione crittografica (PRF).
    
- Questo valore diventa la base di tutte le chiavi successive.
    

#### **c. Session Keys**

Dal _master secret_ vengono derivate **4 chiavi principali**:

1. **Client MAC key**
    
2. **Server MAC key**
    
3. **Client encryption key**
    
4. **Server encryption key**
    

Queste chiavi assicurano confidenzialità e integrità in entrambe le direzioni di comunicazione.

---

### **7. Invio e ricezione dei dati**

Dopo la conclusione dell’handshake:

- i messaggi applicativi passano attraverso il **Record Protocol**,
    
- vengono **numerati, autenticati, cifrati** e poi inviati tramite TCP,
    
- il destinatario li **decifra, verifica il MAC** e li ricompone in ordine.
    

Tutti i messaggi successivi sono ora pienamente protetti.

---

### **8. Differenze tra SSL e SSH**

Sebbene **SSL (o TLS)** e **SSH** sembrino simili — entrambi creano un canale cifrato — hanno differenze fondamentali nel design.

|Aspetto|SSL/TLS|SSH|
|---|---|---|
|**Scopo principale**|Sicurezza generica per applicazioni (HTTP, SMTP, ecc.)|Accesso remoto sicuro e tunneling specifico|
|**Struttura del tunnel**|Basato su Record Protocol e Handshake|Basato su una pipeline criptata interna|
|**Gestione delle chiavi**|Usa certificati X.509 standard|Usa un proprio formato di chiavi pubbliche|
|**Autenticazione**|Basata su CA e certificati digitali|Può essere basata su password o chiavi pubbliche locali|
|**Multiplexing interno**|Non previsto nativamente|Supporta più canali paralleli (file transfer, shell, ecc.)|
|**Estendibilità**|Applicazioni esterne come HTTPS, FTPS, IMAPS|Include nativamente SFTP, SCP, terminale remoto, ecc.|

**In sintesi:**

- SSL/TLS fornisce il _canale sicuro_, su cui le applicazioni costruiscono i propri protocolli (es. HTTPS).
    
- SSH, invece, integra già diversi servizi all’interno del suo tunnel.
    

> Concettualmente, potresti sostituire la parte di trasporto dati di SSH con quella di SSL, oppure viceversa, ottenendo schemi equivalenti di sicurezza ma con gestione diversa delle identità e dei servizi.

---

### **9. Conclusione**

La famiglia di protocolli SSL/TLS rappresenta l’**architettura completa della sicurezza del trasporto Internet**:

- l’**Handshake** negozia fiducia e chiavi,
    
- il **Change Cipher Spec** attiva la cifratura,
    
- l’**Alert** gestisce errori e chiusure,
    
- il **Record Protocol** trasmette i dati in modo sicuro.
    

Nel complesso, questi meccanismi garantiscono che ogni connessione Internet sicura (HTTPS, SMTPs, IMAPs, ecc.) sia:

- **autenticata**,
    
- **cifrata**,
    
- **integra**.