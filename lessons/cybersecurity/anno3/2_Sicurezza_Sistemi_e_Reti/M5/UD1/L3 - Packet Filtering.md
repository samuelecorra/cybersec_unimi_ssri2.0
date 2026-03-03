## **Lezione 3: Packet Filtering**

### **1. Introduzione**

Il **packet filtering** è la tecnica di base utilizzata dai firewall per **controllare il traffico di rete** in base alle informazioni contenute nei pacchetti.  
L’obiettivo è decidere **quali pacchetti possono attraversare il firewall** e quali devono essere bloccati, applicando regole che si basano sui campi degli header IP e TCP/UDP.

Il filtraggio dei pacchetti può essere:

- **stateless (static)** → analizza ogni pacchetto singolarmente;
    
- **stateful (dinamico)** → tiene traccia dello stato delle connessioni in corso.
    

---

## **2. Analisi degli header di rete**

### **2.1 Header IP**

Ogni pacchetto IP contiene informazioni fondamentali che il firewall utilizza per il controllo:

- **Versione** → IPv4 o IPv6
    
- **Lunghezza header** → numero di parole da 32 bit
    
- **Type of Service (ToS)** → può contenere richieste di QoS
    
- **Dimensione datagram** → lunghezza totale (header + dati)
    
- **Identificazione** → numero che, insieme all’indirizzo sorgente, identifica univocamente il pacchetto (utile per la frammentazione)
    
- **Fragmentation offset** → posizione del frammento rispetto al pacchetto originale
    
- **Time To Live (TTL)** → numero massimo di hop consentiti (decrementato da ogni router)
    
- **Protocol Service Access Point (SAP)** → indica quale protocollo segue (1 = ICMP, 6 = TCP, 17 = UDP)
    
- **Indirizzo IP sorgente e destinazione**
    

Questi campi consentono al firewall di applicare regole precise su base **indirizzo, protocollo o tipo di servizio**.

---

### **2.2 Header TCP**

Il protocollo TCP fornisce informazioni aggiuntive utili per stabilire lo **stato delle connessioni** e la **direzione del traffico**.

Tra i campi più importanti:

- **Porta sorgente e destinazione**
    
- **Sequence number e acknowledgment number**
    
- **Flag di controllo**:
    
    - **SYN** → richiesta di connessione
        
    - **ACK** → conferma di ricezione
        
    - **FIN** → terminazione della sessione
        
    - **RST** → reset della connessione
        
    - **PSH** → invio immediato dei dati senza buffering
        

#### **Il significato dei sequence e acknowledgment number**

Ogni flusso TCP è diviso in pacchetti numerati sequenzialmente.  
Quando un pacchetto viene ricevuto, il destinatario risponde con un **ACK** che contiene:  
$$  
ack = seq_{ricevuto} + 1  
$$  
Questo garantisce l’ordinamento e la corretta ricostruzione dei dati ricevuti.

---

### **2.3 Il Three-Way Handshake**

Il TCP utilizza una **procedura a tre fasi** per instaurare la connessione tra client e server.

|Fase|Direzione|Flag|Descrizione|
|---|---|---|---|
|1|Client → Server|SYN|Il client richiede di aprire una connessione|
|2|Server → Client|SYN + ACK|Il server accetta e risponde|
|3|Client → Server|ACK|Il client conferma l’apertura della connessione|

Solo dopo il completamento del three-way handshake la connessione è considerata **attiva e affidabile**.

---

### **2.4 Il protocollo UDP**

Il protocollo **UDP (User Datagram Protocol)** si trova allo stesso livello di TCP ma **non è orientato alla connessione**.

- Non esiste un handshake: i pacchetti vengono inviati senza sapere se il destinatario è pronto.
    
- L’header è **molto più semplice** e contiene solo:
    
    - Porta sorgente
        
    - Porta destinazione
        
    - Lunghezza
        
    - Checksum
        

UDP è usato per servizi che richiedono velocità e tollerano perdita di pacchetti (DNS, streaming, VoIP).

---

## **3. Static Stateless Packet Filter (SPF)**

### **3.1 Definizione**

Il **packet filter statico e stateless** controlla il traffico basandosi **solo sulle informazioni degli header** dei pacchetti.  
Ogni pacchetto è confrontato con una lista di regole (ACL – _Access Control List_) e **accettato o scartato** a seconda del risultato.

> Ogni pacchetto è valutato **indipendentemente** dagli altri: non si tiene traccia delle connessioni.

---

### **3.2 Parametri di filtraggio**

Il filtro IP può bloccare o permettere pacchetti in base a:

- tipo di servizio o **porta TCP/UDP**,
    
- campo **protocol** (es. ICMP, TCP, UDP),
    
- **indirizzo IP** sorgente o destinazione,
    
- **indirizzo MAC** sorgente o destinazione,
    
- **interfaccia** di ingresso o uscita.
    

Questo approccio opera principalmente sui livelli **3 (Network)** e **4 (Transport)** del modello ISO/OSI.

---

### **3.3 Caratteristiche**

Il filtro SPF rappresenta la **prima tecnologia firewall** della storia.  
Nonostante oggi sia stato sostituito dai firewall stateful, è ancora utilizzato nei **router e sistemi di fascia bassa** per via della sua semplicità ed efficienza.

#### **Vantaggi**

- Indipendente dalle applicazioni
    
- Elevata scalabilità
    
- Prestazioni eccellenti
    
- Basso costo (spesso integrato nei router o nei sistemi operativi)
    

#### **Svantaggi**

- Controlli **poco precisi**, facilmente eludibili (es. _IP spoofing_)
    
- Nessuna analisi dello stato delle connessioni
    
- Difficoltà nel gestire servizi con **porte dinamiche** (es. FTP attivo)
    

---

### **3.4 Applicazioni del packet filtering statico**

Gli SPF sono comunemente utilizzati come **prima linea di difesa** in configurazioni perimetrali semplici, con regole del tipo:

- La rete interna può **iniziare connessioni verso l’esterno**,
    
- Ma l’esterno **non può iniziare connessioni verso l’interno**.
    

Esempi:

- Connessioni **SSH** o **ping** solo da LAN → Internet
    
- Blocco totale delle connessioni inverse.
    

---

## **4. Gestione delle connessioni TCP e protocolli connectionless**

### **4.1 Controllo delle connessioni TCP**

Il firewall può decidere di consentire solo le connessioni con **flag coerenti** (es. SYN da interno verso esterno).

In un SPF configurato in modo corretto:

- Solo i pacchetti che avviano connessioni legittime (SYN) da dentro la rete vengono accettati.
    
- I pacchetti provenienti dall’esterno con SYN vengono **scartati**.
    

---

### **4.2 Protocolli connectionless**

I protocolli senza connessione, come **UDP** e **ICMP**, non prevedono handshake.  
Le comunicazioni possono essere:

- **bidirezionali** → es. _Ping (ICMP Echo Request/Reply)_, _DNS query (UDP)_
    
- **unidirezionali** → es. _ICMP Source Quench_
    

Un firewall SPF deve gestire entrambi i casi in modo coerente con la policy definita.

---

## **5. Efficacia e limiti dello SPF**

Gli SPF sono efficaci contro attacchi semplici ma **non contro tecniche sofisticate**.

### **Controlli tipici**

- **IP Spoofing:** verifica dell’indirizzo sorgente
    
- **Tentativi di connessione:** controllo su indirizzi, porte e flag TCP
    
- **Traffico ICMP:** filtro su tipo e codice
    
- **Source Routing:** blocco dei pacchetti che usano routing esplicito
    

Spesso, lo **SPF è implementato direttamente nel border router**, come primo livello di protezione perimetrale.

> È utile per bloccare minacce basilari e comprendere il comportamento dei protocolli, ma non offre sicurezza completa.

---

## **6. Stateful Packet Filtering**

### **6.1 Differenza concettuale**

Un **packet filter** può essere:

- **Stateless:** ogni pacchetto analizzato indipendentemente;
    
- **Stateful:** il firewall mantiene **una tabella delle connessioni attive** (state table) per riconoscere pacchetti appartenenti a sessioni già autorizzate.
    

Oggi la distinzione è più teorica che pratica: quasi tutti i firewall moderni implementano un certo grado di **protocol inspection** multilivello.

---

### **6.2 Protocol Inspection**

La **stateful inspection** consiste nel registrare in una tabella tutte le connessioni autorizzate.  
Per ogni connessione vengono memorizzati:

- IP sorgente e destinazione,
    
- porte,
    
- protocollo,
    
- flag TCP,
    
- sequence e acknowledgment number.
    

In questo modo, il firewall può riconoscere i pacchetti legittimi appartenenti a connessioni già esistenti e bloccare tentativi di intrusione come il **session hijacking**.

---

### **6.3 Funzionamento pratico**

Quando un pacchetto arriva:

1. Il firewall controlla se appartiene a una **connessione registrata** nella state table.
    
    - Se sì → il pacchetto passa senza ulteriori controlli.
        
2. Se non appartiene a una connessione nota → viene trattato come **nuovo pacchetto** e sottoposto alle regole di filtraggio.
    

> Questo approccio migliora la sicurezza e riduce il carico computazionale.

---

## **7. Considerazioni finali**

Anche i migliori sistemi di packet filtering **non analizzano il contenuto** dei pacchetti.  
Ciò significa che:

- non possono **bloccare virus o malware**,
    
- hanno problemi con protocolli che **negoziano porte dinamicamente** (es. FTP attivo),
    
- non riescono a rilevare attacchi basati sul contenuto applicativo.
    

Per questo motivo, i produttori stanno integrando tecniche di **Deep Packet Inspection (DPI)** e **intelligenza artificiale**, specialmente in ambito **IoT** (es. acquisizione di Sentryo da parte di Cisco).

---

### **8. Porte e servizi su Internet**

Ogni applicazione o servizio di rete è associato a **porte numeriche standard** (es. 80 per HTTP, 443 per HTTPS).  
Tuttavia, alcuni malware o servizi malevoli **nascondono le loro porte** per sfuggire ai controlli dei firewall basati solo su numeri di porta o protocolli.

> Per questo è necessario un approccio multilivello, che unisca packet filtering, DPI e analisi comportamentale.

---

### **9. Conclusione**

Il **packet filtering** rappresenta il fondamento della sicurezza di rete, ma da solo **non basta**.  
È il primo livello della difesa, efficace solo se:

- integrato con meccanismi di **stateful inspection**,
    
- costantemente aggiornato,
    
- e accompagnato da una **politica di sicurezza chiara e coerente**.
    

> In sintesi: lo stateless packet filtering è il “muro”, ma il firewall stateful è la “guardia” che riconosce chi ha già bussato.