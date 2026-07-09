## **Lezione 2: Architettura SSL/TLS**

### **1. Introduzione generale**

Il protocollo **SSL/TLS** non è un singolo meccanismo, ma un insieme coordinato di **più protocolli** che cooperano per garantire **confidenzialità**, **integrità** e **autenticazione** dei dati trasmessi tra due applicazioni (tipicamente un client e un server).

TLS agisce come **strato intermedio** tra l’applicazione (es. HTTP) e il livello di trasporto (es. TCP), assicurando che ogni byte che attraversa la rete sia **protetto contro intercettazione e manipolazione**.

![](imgs/Pasted%20image%2020260709011528.png)

Più precisamente, SSL/TLS è una **suite di protocolli**: definisce come negoziare gli algoritmi, come autenticare le parti, come stabilire chiavi segrete condivise e come proteggere poi i dati applicativi durante la comunicazione.

> 📌 La forza di TLS non dipende da un singolo algoritmo, ma dalla combinazione coerente di scambio chiavi, autenticazione, cifratura simmetrica e controllo di integrità.

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
    

Nell’uso web più comune, infatti, è il server a presentare un certificato digitale, che il client verifica per accertarsi dell’identità del sito. L’autenticazione del client tramite certificato è possibile, ma meno frequente negli scenari ordinari di navigazione.

#### **b. Record Protocol**

Dopo la fase di handshake, entra in funzione il **Record Protocol**, che:

- fornisce servizi di sicurezza ai protocolli di livello superiore (es. HTTPS),
    
- usa le chiavi segrete negoziate per cifrare e autenticare i dati,
    
- assicura **confidenzialità**, **integrità** e **autenticità** dei messaggi applicativi.
    

In sostanza:

> Handshake stabilisce la fiducia → Record la mantiene durante tutta la sessione.

#### **c. Cipher suite**

Durante l’handshake client e server negoziano una **cipher suite**, cioè una descrizione compatta degli algoritmi crittografici usati nella sessione. Una cipher suite specifica almeno:

- il meccanismo di **key exchange** (`Kx`), cioè come si stabilisce il segreto condiviso;

- il meccanismo di **authentication** (`Au`), cioè come si autenticano le parti;

- l’algoritmo di **encryption** (`Enc`), cioè la cifratura simmetrica dei dati;

- il meccanismo di **MAC** o autenticazione dei messaggi, cioè il controllo di integrità.

Un esempio storico è una suite del tipo:

```text
SSL_RSA_WITH_3DES_EDE_CBC_SHA
```

che indica, in forma compatta:

|Componente|Significato|
|---|---|
|`RSA`|scambio chiavi e autenticazione basati su RSA|
|`3DES_EDE_CBC`|cifratura simmetrica Triple-DES in modalità CBC|
|`SHA`|funzione hash usata come base per il MAC|

![](imgs/Pasted%20image%2020260709011708.png)

Nelle versioni moderne le suite sono cambiate, e in TLS 1.3 molte scelte legacy sono state eliminate, ma il concetto resta fondamentale: una sessione TLS è caratterizzata da una combinazione precisa di algoritmi concordati.

#### **d. Protocolli principali della suite**

La suite SSL/TLS comprende più protocolli interni, ciascuno con un ruolo specifico:

|Protocollo|Funzione|
|---|---|
|**Record Protocol**|Trasporta dati applicativi e messaggi di controllo in record protetti.|
|**Handshake Protocol**|Negozia algoritmi, parametri di sicurezza, chiavi e autenticazione.|
|**Alert Protocol**|Segnala errori, anomalie o condizioni di chiusura della connessione.|
|**Change Cipher Spec Protocol**|Segnala il passaggio dai parametri provvisori a quelli crittografici negoziati.|
|**Application Data Protocol**|Incapsula i dati applicativi veri e propri una volta stabilita la protezione.|

Il **Change Cipher Spec** non è, in senso stretto, il protocollo che negozia da solo nuovi parametri: la negoziazione è compito dell’Handshake. Il suo ruolo è indicare che da quel punto in poi i record saranno protetti con la cipher suite e le chiavi appena stabilite.

> ⚠️ Distinzione utile: Handshake decide i parametri; Change Cipher Spec segnala quando quei parametri diventano effettivi; Record Protocol applica concretamente cifratura e integrità ai dati.

---

### **3. Posizionamento di TLS nel modello a strati**

TLS si colloca tra:

- **livello applicativo** (es. HTTP, SMTP, IMAP, ecc.)
    
- **livello di trasporto** (TCP o, in versione ridotta, UDP con DTLS – _Datagram TLS_).
    

Il flusso concettuale è: l’applicazione consegna dati in chiaro a TLS; TLS li trasforma in record protetti; il livello di trasporto trasmette questi record sulla rete, normalmente tramite TCP e, in casi specifici, tramite UDP con DTLS.

Lo **strato inferiore** del sistema TLS è il **Record Protocol**, che gestisce:

- la creazione dei _record_,
    
- l’aggiunta dell’intestazione,
    
- l’inserimento del _payload_ (cioè i dati o i messaggi di controllo),
    
- l’eventuale aggiunta del _MAC_ (Message Authentication Code).
    

Ogni record contiene:

- **Header** → tipo di contenuto, versione, lunghezza
    
- **Payload** → dati cifrati o messaggi di controllo
    
- **MAC opzionale** → garantisce integrità e autenticità
    

Il Record Protocol trasporta sia **messaggi di controllo** sia **dati applicativi**. Per questo il campo iniziale dell’header deve indicare a quale protocollo interno appartiene il contenuto del record: Alert, Handshake, Change Cipher Spec o Application Data.

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
    

La creazione di una sessione è costosa perché richiede negoziazione crittografica, autenticazione e derivazione delle chiavi. Una volta stabiliti i parametri della sessione, più connessioni possono sfruttarli senza ripetere ogni volta l’intero handshake.

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

Il **Session ID** è una sequenza arbitraria di byte scelta dal server per recuperare lo stato della sessione. Il **Master Secret** non viene usato direttamente per cifrare tutti i dati: serve come materiale di base per derivare chiavi operative distinte.

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

Le chiavi sono direzionali: il traffico **client → server** e il traffico **server → client** usano segreti distinti. Questa separazione evita che l’uso di una chiave in una direzione possa interferire con la protezione dell’altra.

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
    
4. Si calcola e aggiunge il **MAC**, usando una chiave segreta condivisa.
    
5. Il blocco composto da dati compressi e MAC viene **cifrato** con l’algoritmo concordato.
    
6. Si aggiunge un’**intestazione (header)**.
    
7. Il record completo viene inviato tramite TCP.
    

Nelle versioni classiche di SSL/TLS il flusso concettuale è quindi: frammentazione → compressione opzionale → MAC → cifratura → header. Con cifrari a blocchi può essere necessario aggiungere **padding**, in modo che la porzione cifrata abbia una lunghezza compatibile con il blocco dell’algoritmo simmetrico selezionato.

![](imgs/Pasted%20image%2020260709011829.png)

---

### **8. Struttura di un record TLS**

Ogni record è composto da:

![](imgs/Pasted%20image%2020260709011844.png)

|Campo|Descrizione|
|---|---|
|**Content Type**|1 byte. Indica la tipologia del contenuto: Handshake, Alert, Change Cipher Spec o Application Data. Valore `0x17` → dati applicativi.|
|**Version**|2 byte. Specifica la versione SSL/TLS usata per il record.|
|**Length**|2 byte. Indica la lunghezza del payload del record, esclusa l’intestazione.|

Il _payload_ contiene il blocco cifrato e autenticato.

Nel caso classico con MAC e cifratura a blocchi, il payload prima della cifratura può essere visto come:

- dati compressi;

- MAC calcolato sui dati;

- eventuali byte di padding;

- informazione sulla lunghezza del padding, se prevista dal formato.

L’intestazione serve al destinatario per sapere come interpretare il record, quale versione applicare e quanti byte leggere per ricostruire correttamente il messaggio.

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
