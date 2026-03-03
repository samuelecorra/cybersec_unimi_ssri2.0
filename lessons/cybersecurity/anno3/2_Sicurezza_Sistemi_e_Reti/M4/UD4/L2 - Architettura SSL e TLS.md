## **Lezione 2: Architettura SSL/TLS**

### **1. Introduzione generale**

Il protocollo **SSL/TLS** non è un singolo meccanismo, ma un insieme coordinato di **più protocolli** che cooperano per garantire **confidenzialità**, **integrità** e **autenticazione** dei dati trasmessi tra due applicazioni (tipicamente un client e un server).

TLS agisce come **strato intermedio** tra l’applicazione (es. HTTP) e il livello di trasporto (es. TCP), assicurando che ogni byte che attraversa la rete sia **protetto contro intercettazione e manipolazione**.

---

### **2. Struttura di base del protocollo SSL/TLS**

#### **a. Handshake Protocol**

Questo protocollo gestisce la **fase iniziale** della comunicazione, stabilendo i parametri crittografici tra client e server.  
Utilizza **crittografia a chiave pubblica** per:

- **scambiarsi le chiavi segrete condivise** (session key),
    
- **autenticare le parti** tramite certificati digitali (X.509),
    
- **negoziare la versione del protocollo e gli algoritmi** di cifratura e hash da usare.
    

Durante l’handshake si può autenticare:

- sempre il **server**,
    
- opzionalmente anche il **client**.
    

#### **b. Record Protocol**

Dopo la fase di handshake, entra in funzione il **Record Protocol**, che:

- fornisce servizi di sicurezza ai protocolli di livello superiore (es. HTTPS),
    
- usa le chiavi segrete negoziate per cifrare e autenticare i dati,
    
- assicura **confidenzialità**, **integrità** e **autenticità** dei messaggi applicativi.
    

In sostanza:

> Handshake stabilisce la fiducia → Record la mantiene durante tutta la sessione.

---

### **3. Posizionamento di TLS nel modello a strati**

TLS si colloca tra:

- **livello applicativo** (es. HTTP, SMTP, IMAP, ecc.)
    
- **livello di trasporto** (TCP o, in versione ridotta, UDP con DTLS – _Datagram TLS_).
    

Il flusso avviene così:

```
[ Applicazione ]  →  Dati non protetti  
        ↓  
[ TLS Layer ]  →  Dati cifrati e autenticati  
        ↓  
[ TCP / UDP ]  →  Trasporto su rete
```

Lo **strato inferiore** del sistema TLS è il **Record Protocol**, che gestisce:

- la creazione dei _record_,
    
- l’aggiunta dell’intestazione,
    
- l’inserimento del _payload_ (cioè i dati o i messaggi di controllo),
    
- l’eventuale aggiunta del _MAC_ (Message Authentication Code).
    

Ogni record contiene:

- **Header** → tipo di contenuto, versione, lunghezza
    
- **Payload** → dati cifrati o messaggi di controllo
    
- **MAC opzionale** → garantisce integrità e autenticità
    

---

### **4. Concetti fondamentali: Sessione e Connessione**

TLS distingue chiaramente tra **sessione (session)** e **connessione (connection)**.

#### **a. Connessione**

- È il **canale di trasporto effettivo** tra due peer (es. browser ↔ server web).
    
- È **temporanea** e **univoca** per ogni flusso dati.
    
- Ogni connessione è **associata a una sessione** da cui eredita i parametri di sicurezza.
    

#### **b. Sessione**

- È un’**associazione logica** tra un client e un server, creata durante l’handshake.
    
- Contiene tutti i **parametri crittografici** negoziati.
    
- Può essere **riutilizzata** da più connessioni per evitare nuovi handshake completi (→ risparmio di tempo e CPU).
    

In teoria si possono avere più sessioni attive tra le stesse parti, ma in pratica ne viene usata una alla volta.

**Esempio pratico:**  
Un utente apre più schede sullo stesso sito HTTPS: ogni connessione usa la _stessa sessione TLS_, evitando un nuovo handshake completo.

---

### **5. Stato della sessione (Session State)**

Ogni sessione è descritta da un insieme di **parametri di stato**, essenziali per la sicurezza:

|Parametro|Descrizione|
|---|---|
|**Session ID**|Identificatore scelto dal server per distinguere la sessione.|
|**Peer Certificate**|Certificato X.509.v3 dell’altro peer (server o client).|
|**Compression Method**|Algoritmo usato per comprimere i dati prima della cifratura.|
|**Cipher Spec**|Specifica dei cifrari simmetrici e delle funzioni hash impiegate.|
|**Master Secret**|Segreto principale (48 byte) condiviso tra client e server, base per derivare le chiavi di sessione.|
|**Is Resumable**|Flag che indica se la sessione può essere riutilizzata (es. per la modalità 0-RTT di TLS 1.3).|

---

### **6. Stato della connessione (Connection State)**

Ogni singola connessione possiede anch’essa un proprio stato indipendente, derivato dai parametri di sessione:

|Parametro|Descrizione|
|---|---|
|**Server Random / Client Random**|Sequenze casuali generate per differenziare le chiavi di ogni connessione.|
|**Server Write MAC Secret**|Chiave MAC usata dal server per autenticare i dati che invia.|
|**Client Write MAC Secret**|Chiave MAC usata dal client per autenticare i propri dati.|
|**Server Write Key**|Chiave simmetrica con cui il server cifra i messaggi in uscita.|
|**Client Write Key**|Chiave simmetrica con cui il client cifra i messaggi in uscita.|
|**Initialization Vector (IV)**|Vettore d’inizializzazione per la cifratura a blocchi (generato all’handshake).|
|**Sequence Numbers**|Contatori separati per client e server, usati per numerare i messaggi e prevenire replay.|

> In breve: la _sessione_ definisce i parametri condivisi e la _connessione_ li concretizza per il flusso effettivo di dati.

---

### **7. Il Record Protocol in dettaglio**

Il **Record Protocol** è responsabile del trattamento dei messaggi applicativi e fornisce due servizi principali:

#### **a. Confidenzialità**

I dati vengono cifrati utilizzando una **chiave simmetrica** generata durante l’handshake.  
La crittografia protegge il contenuto da letture non autorizzate.

#### **b. Integrità dei messaggi**

Viene calcolato un **MAC (Message Authentication Code)** tramite una chiave segreta condivisa.  
Il MAC assicura che il messaggio non sia stato alterato durante il transito.

#### **c. Funzionamento pratico**

1. L’applicazione fornisce un messaggio da inviare.
    
2. Il Record Protocol **lo frammenta** in blocchi di dimensione massima $2^{14}$ byte (cioè 16.384 byte).
    
3. I dati possono essere **compressi** (opzionale).
    
4. Si calcola e aggiunge il **MAC**.
    
5. Il blocco viene **cifrato** con l’algoritmo concordato.
    
6. Si aggiunge un’**intestazione (header)**.
    
7. Il record completo viene inviato tramite TCP.
    

---

### **8. Struttura di un record TLS**

Ogni record è composto da:

|Campo|Descrizione|
|---|---|
|**Content Type**|Indica la tipologia del contenuto (es. Handshake, Alert, Application Data). Valore `0x17` → dati applicativi.|
|**Version**|Specifica la versione TLS utilizzata (es. TLS 1.2, TLS 1.3).|
|**Length**|Lunghezza del record (esclusa l’intestazione).|

Il _payload_ contiene il blocco cifrato e autenticato.

---

### **9. Sintesi finale**

L’architettura SSL/TLS si basa su una **divisione modulare**:

|Livello|Funzione principale|
|---|---|
|**Handshake Protocol**|Stabilisce la fiducia, autentica le parti, negozia algoritmi e chiavi.|
|**Record Protocol**|Protegge i dati effettivi con cifratura e MAC.|
|**Session Layer**|Memorizza parametri condivisi per più connessioni.|
|**Connection Layer**|Usa i parametri per gestire i flussi reali di dati.|

Questo design a più livelli consente a TLS di garantire **sicurezza, flessibilità e interoperabilità** in ogni tipo di comunicazione Internet.