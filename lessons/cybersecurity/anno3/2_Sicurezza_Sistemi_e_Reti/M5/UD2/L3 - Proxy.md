## **Lezione 3: Proxy**

### **1. Introduzione al concetto di Proxy**

Un **proxy** è un componente intermedio che **media le comunicazioni tra due altri sistemi**, tipicamente un **client** e un **server**.  
Invece di instaurare una connessione diretta, il proxy **disaccoppia** i due estremi: ogni parte comunica solo con lui, che poi si occupa di inoltrare o manipolare i dati.

In termini pratici:

- il client **non si collega direttamente** al server remoto, ma al proxy;
    
- il proxy **invia la richiesta per conto del client**, riceve la risposta e la inoltra.
    

Questo meccanismo offre **sicurezza, anonimato e controllo del traffico**.

> In sintesi: un proxy “finge di essere” il destinatario della comunicazione per il client, e il client per il destinatario.

---

## **2. Connessioni reali e apparenti**

Nel funzionamento di un proxy esistono due livelli di comunicazione:

- **Connessioni reali:** sono le connessioni effettivamente instaurate tra il client e il proxy, e tra il proxy e il server.
    
- **Connessioni apparenti:** dal punto di vista del client e del server, sembrano esistere connessioni dirette, ma in realtà **sono due sessioni distinte**.
    

```
Client ⇄ Proxy ⇄ Server
```

Il proxy diventa così un **punto di mediazione e controllo** all’interno della rete.

---

## **3. Tipologie comuni di Proxy**

|Tipo di Proxy|Funzione principale|
|---|---|
|**Web Proxy**|Memorizza copie (cache) delle pagine web per velocizzare gli accessi.|
|**Anonymizing Proxy**|Nasconde l’identità dell’utente (IP reale) verso l’esterno.|
|**Distorting / High-Anonymity Proxy**|Modifica le intestazioni HTTP per fornire anonimato totale.|
|**Reverse Proxy**|Espone risorse interne a utenti esterni in modo controllato.|
|**Proxy Firewall**|Media le connessioni applicative e applica regole di sicurezza sui protocolli.|

---

## **4. Reverse Proxy**

Un **reverse proxy** agisce come un intermediario **davanti ai server interni**.  
Gli utenti esterni si collegano al proxy, che gestisce la richiesta e la inoltra al **server effettivo**, proteggendolo e ottimizzandone le prestazioni.

### **4.1. Esempio tipico**

Un server HTTP che funge solo da **front-end** e che inoltra le richieste al vero web server.

### **4.2. Benefici**

- **Obfuscation:** nasconde la reale identità e configurazione del server interno.
    
- **Load balancing:** distribuisce il carico tra più server backend.
    
- **Acceleratore SSL:** il proxy gestisce la cifratura SSL, sollevando il backend dal carico crittografico.
    
- **Web accelerator:** conserva in cache i contenuti statici.
    
- **Compressione** dei dati inviati ai client.
    
- **Spoon feeding:** riceve l’intera risposta dal server e la invia gradualmente al client, riducendo la pressione sul server applicativo.
    

> Il reverse proxy è un “parafreddo” digitale: assorbe gli urti, protegge e regola il traffico verso il cuore della rete.

---

## **5. Architettura di un Reverse Proxy**

### **Fasi di comunicazione**

1. Un utente esterno apre una connessione verso il Web Server.
    
2. Il traffico viene reindirizzato verso il Reverse Proxy.
    
3. Il proxy effettua **autenticazione, verifica e filtraggio**.
    
4. Infine inoltra la richiesta verso il server interno.
    

### **Schema**

```
Client → [Internet] → Reverse Proxy → Web Server (interno)
```

Ogni passaggio può includere:

- ispezione di sicurezza,
    
- caching,
    
- autenticazione e logging.
    

---

## **6. Configurazioni di Reverse Proxy**

|Scenario|Descrizione|
|---|---|
|**Single reverse proxy**|Un solo proxy serve più server interni.|
|**Reverse proxy con VPN**|Aggiunge un canale cifrato verso i server interni.|
|**Multi-tier**|Una catena di proxy gestisce diverse zone di sicurezza (es. DMZ).|

> I reverse proxy moderni spesso integrano VPN e moduli di autenticazione per garantire l’accesso sicuro a reti aziendali interne.

---

## **7. Proxy Firewall: Application-Level Gateway (ALG)**

Un **Application-Level Gateway** è un tipo di **proxy firewall** che analizza il contenuto dei pacchetti **a livello applicativo (Layer 7)**.  
Ogni applicazione (HTTP, FTP, SMTP, ecc.) ha un proprio modulo proxy dedicato.

### **Caratteristiche principali**

- Analizza il **contenuto reale** delle comunicazioni, non solo gli header.
    
- Può **mascherare o rinumerare gli indirizzi IP interni**.
    
- Può **autenticare gli utenti** prima di consentire la comunicazione.
    
- Protegge da attacchi **buffer overflow** e vulnerabilità applicative.
    

> È un firewall “consapevole del linguaggio” delle applicazioni.

---

## **8. Application-Level Gateway: vantaggi e limiti**

### **Vantaggi**

- Regole più **granulari e leggibili** rispetto ai packet filter.
    
- **Migliore protezione** dei server interni.
    
- **Supporto all’autenticazione** e ai log dettagliati delle connessioni.
    
- Analisi dei **comandi applicativi** (es. comandi FTP, SMTP, HTTP).
    

### **Svantaggi**

- Ogni applicazione richiede **un proxy dedicato** → ritardo nel supporto di nuove tecnologie.
    
- **Alti consumi di risorse** (molti processi, spesso in user-mode).
    
- **Prestazioni inferiori** rispetto ai firewall stateless o stateful.
    
- **Mancanza di trasparenza** per i client: spesso occorre configurare manualmente il software.
    

---

## **9. Vulnerabilità dei proxy applicativi**

Anche un proxy può contenere **falle di sicurezza**.  
Esempio reale: **CVE-2018-0476**, una vulnerabilità nel modulo NAT/SIP ALG di **Cisco IOS XE**, che permetteva a un attaccante remoto di causare un **riavvio del dispositivo** inviando pacchetti SIP appositamente costruiti.

> Nessun software è immune: anche un firewall “intelligente” può diventare il punto debole se non viene aggiornato.

---

## **10. Varianti di Application Proxy**

|Tipo|Descrizione|
|---|---|
|**Transparent Proxy**|Più discreto: i client non devono essere configurati. Tutto il traffico viene intercettato automaticamente.|
|**Strong Application Proxy**|Filtra rigidamente comandi e dati: solo quelli esplicitamente permessi vengono trasmessi. È l’unico modello pienamente sicuro per ambienti critici.|

---

## **11. Ricapitolando – Application-Level Gateway**

### **Vantaggi**

- Blocca connessioni dirette tra interno ed esterno.
    
- Supporta autenticazione e logging.
    
- Analizza i comandi a livello applicativo.
    
- Mantiene log dettagliati delle attività.
    

### **Svantaggi**

- Difficoltà con applicazioni in tempo reale.
    
- Limitato supporto ai protocolli più recenti.
    
- Impatto sulle prestazioni.
    
- Espone il sistema operativo del firewall a potenziali vulnerabilità.
    

---

## **12. Circuit-Level Gateway**

Un **Circuit-Level Gateway** opera a un livello più basso (Layer 4), creando un **circuito virtuale** tra client e server senza interpretare il contenuto dei dati.  
È un proxy **non “application-aware”**, che si limita a gestire le connessioni TCP.

### **Funzionamento**

1. Il client apre una connessione TCP verso il gateway.
    
2. Il gateway **autentica** e **autorizza** il client.
    
3. Il gateway apre una seconda connessione verso il server.
    
4. Tutto il traffico viene inoltrato trasparentemente.
    

> È come un centralino che verifica chi chiama, ma non ascolta la conversazione.

---

### **Vantaggi**

- Isola i server dagli attacchi sull’handshake TCP o sulla frammentazione IP.
    
- Può autenticare i client.
    
- Protegge i sistemi interni, creando connessioni “per conto loro”.
    

### **Svantaggi**

- Non interpreta i dati applicativi.
    
- Molte limitazioni tipiche del packet filtering rimangono.
    
- Può richiedere modifiche ai client.
    

---

## **13. Bastion Host**

Il **Bastion Host** è l’**host critico** che ospita i servizi proxy.  
Deve essere **estremamente sicuro** perché rappresenta la **prima linea di difesa**.

### **Caratteristiche**

- Sistema operativo **minimale e rinforzato**.
    
- Solo i **servizi proxy strettamente necessari**.
    
- Autenticazione avanzata per utenti e host.
    
- Supporto per **logging e auditing completi**.
    

> È il “castello fortificato” del sistema proxy.

---

## **14. SOCKS: il proxy di livello trasporto**

**SOCKS** è un protocollo standard per i **circuit-level gateway** (Layer 4).  
Permette il transito autenticato del traffico TCP/UDP attraverso un firewall.

### **Versioni**

- **v4 (MIPS, NEC):** solo TCP, autenticazione debole.
    
- **v5 (IETF):** supporta TCP e UDP, autenticazione user/password o GSS-API, e cifratura.
    

### **RFC principali**

- RFC 1928 – SOCKS v5
    
- RFC 1929 – Username/Password Authentication
    
- RFC 1961 – GSS-API Authentication
    
- RFC 3089 – IPv6/IPv4 Gateway Mechanism
    

---

### **Funzionamento**

Il client, invece di usare direttamente le funzioni standard di rete (`connect()`, `bind()`, `accept()`), le sostituisce con versioni **modificate** che comunicano col server SOCKS.  
Il proxy controlla la ACL, apre il canale con il proprio IP e “collega” la connessione interna con quella esterna.

---

### **Critiche a SOCKS v4**

|Limite|Soluzione in SOCKS v5|
|---|---|
|Non distingue rete interna/esterna|Introduzione di ACL più granulari|
|Autenticazione debole (identd)|Username/password o GSS-API|
|Solo TCP supportato|Aggiunto supporto UDP|
|Nessuna cifratura|Crittografia integrata tra client e server SOCKS|

---

## **15. Transparent Application Gateways**

Uno dei problemi principali dei proxy applicativi è la **mancanza di trasparenza**: i client devono essere configurati manualmente.  
Per risolvere questo limite, i moderni firewall integrano **transparent gateways**, che intercettano il traffico senza richiedere configurazioni particolari.

> L’obiettivo è rendere l’uso del proxy invisibile all’utente finale, senza ridurre la sicurezza.

---

## **16. Frammentazione IP e Proxy**

La **frammentazione IP** divide i pacchetti in segmenti più piccoli per adattarli alle reti con MTU ridotta.  
Tuttavia, i pacchetti frammentati possono causare:

- **problemi di sicurezza** (evasione dei controlli di filtraggio),
    
- difficoltà di ricomposizione nei firewall tradizionali.
    

I proxy sono meno vulnerabili perché, essendo destinatari “logici” dei messaggi, **ricompongono i frammenti** prima di inoltrarli.

---

## **17. Proxy Firewall: Conclusioni**

- Opera a **livello applicativo**, quindi può analizzare i dati in profondità.
    
- Ha prestazioni potenzialmente **più lente** ma sicurezza **superiore**.
    
- Migliore analisi rispetto ai firewall stateful.
    
- Supporta molte applicazioni comuni (HTTP, FTP, SMTP, ecc.).
    
- Dopo un periodo di declino, i proxy firewall **stanno riemergendo** grazie all’aumento delle vulnerabilità applicative.
    
- Spesso sono oggi **integrati come moduli o plugin** di firewall stateful o DPI.
    

> In sintesi: il proxy firewall rappresenta l’unione perfetta tra **analisi profonda, controllo fine e sicurezza applicativa** — a costo di un po’ di velocità.


---