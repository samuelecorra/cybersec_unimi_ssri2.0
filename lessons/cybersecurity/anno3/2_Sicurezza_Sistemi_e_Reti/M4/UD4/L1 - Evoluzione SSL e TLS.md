# **UD4: Sicurezza del livello trasporto

**Obiettivo:** presentare in modo essenziale le funzioni di TLS (confidenzialità, integrità, autenticazione) e le principali aree vulnerabili (handshake/protocollo, PKI/certificati, implementazioni).

**In sintesi:** studieremo il modello handshake → record layer → gestione certificati, e le contromisure principali come uso di TLS 1.3, cipher moderni con forward secrecy, corretta gestione delle CA e aggiornamenti/patching delle implementazioni.


---

## **Lezione 1: Evoluzione SSL/TLS**

### **1. Introduzione**

I protocolli **SSL (Secure Sockets Layer)** e **TLS (Transport Layer Security)** costituiscono la base della **sicurezza del livello di trasporto** su Internet.  
Il loro scopo principale è **garantire la riservatezza e l’integrità dei dati** scambiati tra due applicazioni che comunicano attraverso la rete, anche in presenza di un attaccante attivo.

In altre parole, TLS/SSL servono a costruire un **canale sicuro end-to-end**, dove le informazioni trasmesse non possono essere intercettate o alterate da terzi.

Oggi TLS rappresenta il **de facto standard** per la sicurezza di Internet, utilizzato da:

- browser web per connessioni HTTPS,
    
- sistemi VoIP,
    
- servizi di pagamento elettronico,
    
- e numerose altre applicazioni di rete.
    

---

### **2. Sicurezza ai vari livelli**

Per comprendere il ruolo di SSL/TLS, ricordiamo che la sicurezza può essere implementata a diversi livelli del modello ISO/OSI:

- **Livello applicativo** → es. PGP o S/MIME (proteggono il contenuto applicativo come le email).
    
- **Livello trasporto** → SSL/TLS (protegge il canale di comunicazione tra due processi).
    
- **Livello rete** → IPsec (protegge i pacchetti IP).
    

TLS dunque si colloca **al livello di trasporto**, offrendo una protezione trasparente alle applicazioni che vi si appoggiano.

---

### **3. Origini storiche**

L’evoluzione del protocollo può essere riassunta come segue:

|Versione|Anno|Autore / Ente|Note principali|
|---|---|---|---|
|**SSL 1.0**|1994 (interno Netscape)|Netscape|Prototipo mai pubblicato|
|**SSL 2.0**|Nov 1994|Netscape|Diverse vulnerabilità|
|**SSL 3.0**|Nov 1996|Netscape e Paul Kocher|Revisione completa|
|**TLS 1.0**|Gen 1999|IETF|Basato su SSL 3.0 ma non interoperabile|
|**TLS 1.1**|Apr 2006|IETF|Migliorie di sicurezza e padding|
|**TLS 1.2**|Ago 2008|IETF|Algoritmi modulari e SHA-256|
|**TLS 1.3**|Ago 2018 (RFC 8446)|IETF|Handshake semplificato e maggiore privacy|

**Nota:** TLS 1.0 fu concepito come standard Internet partendo da SSL 3.0, ma i due protocolli non erano compatibili a causa di differenze negli algoritmi crittografici e nella struttura del messaggio.

---

### **4. Problemi di sicurezza in TLS 1.2**

Nel corso degli anni, **numerose vulnerabilità** sono state scoperte in TLS 1.2 e versioni precedenti.  
Queste riguardano:

#### **a. Debolezze crittografiche**

- Algoritmi di scambio chiavi insicuri (es. RSA statico).
    
- Cifrari obsoleti o con padding prevedibile.
    
- Firme digitali vulnerabili.
    

#### **b. Bug di implementazione**

Esempi famosi:

- **Heartbleed** (2014): perdita di memoria da OpenSSL.
    
- **BERserk**: vulnerabilità nella validazione delle firme.
    

#### **c. Difetti di progettazione del protocollo**

Il principale difetto era la **vulnerabilità ai downgrade attacks**.  
Durante l’handshake, un attaccante “in-the-middle” poteva forzare client e server a negoziare una **versione più vecchia o un cifrario debole**, riducendo la sicurezza della connessione.

Inoltre, in TLS 1.2:

- **le firme digitali** non proteggevano l’intero handshake;
    
- l’integrità era garantita solo **dopo** la scelta della suite di cifratura.
    

Di conseguenza, un attaccante in una rete pubblica (es. Wi-Fi aeroportuale) poteva manipolare la negoziazione della suite e imporre l’uso di un algoritmo non sicuro.

Per risolvere tutto ciò, **TLS 1.3** ha introdotto una **riprogettazione totale dell’handshake**, con firme più robuste e controlli integrali sull’integrità del canale sin dai primi messaggi.

---

### **5. Miglioramenti di TLS 1.3: performance e privacy**

TLS 1.3 è progettato con due obiettivi principali:

- **riduzione della latenza**,
    
- **aumento della privacy dell’utente**.
    

#### **a. Performance**

- Il numero di parametri è stato ridotto.
    
- L’**handshake** è stato semplificato e reso più veloce.
    
- Introdotta la modalità **1-RTT** (_One Round Trip Time_):  
    il client può inviare la propria chiave Diffie-Hellman nel **primo messaggio**, riducendo a una sola transazione la creazione del canale sicuro.
    
- Aggiunta anche la modalità **0-RTT** (_Zero Round Trip Time_):  
    se client e server si sono già connessi in passato, possono **riutilizzare una chiave pre-condivisa (PSK)** per stabilire subito un canale cifrato senza attendere l’handshake completo.
    

Queste innovazioni rendono TLS 1.3 **più rapido e scalabile**, migliorando le prestazioni in ambienti come data center o dispositivi mobili.

#### **b. Privacy**

TLS include un’estensione chiamata **SNI (Server Name Indication)**, che consente al client di indicare al server il nome del dominio richiesto durante l’handshake iniziale.  
Nelle versioni precedenti (fino a TLS 1.2), l’SNI veniva inviato **in chiaro**, permettendo a un osservatore di sapere quali siti web venivano visitati, anche su connessioni HTTPS.

TLS 1.3 introduce la **crittografia dell’SNI** tramite una nuova estensione.  
Se correttamente implementata, questa funzione impedisce a terzi di conoscere il nome del dominio contattato, **migliorando la riservatezza e l’anonimato** degli utenti.

---

### **6. Conclusione**

L’evoluzione da SSL a TLS 1.3 rappresenta un **salto qualitativo** nella sicurezza del web:

- dalle prime implementazioni deboli e vulnerabili,
    
- a un protocollo moderno, più veloce, sicuro e orientato alla privacy.
    

TLS 1.3 è oggi **l’unico standard raccomandato** per le comunicazioni sicure su Internet.  
Le versioni precedenti (SSL e TLS 1.0/1.1/1.2) devono essere considerate **deprecate** e **non più utilizzabili** nei sistemi moderni.