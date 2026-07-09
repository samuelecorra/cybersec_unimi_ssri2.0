## **Lezione 3: Packet Filtering**

### **1. Introduzione**

Il **packet filtering** è la tecnica di base utilizzata dai firewall per **controllare il traffico di rete** in base alle informazioni contenute nei pacchetti.  
L’obiettivo è decidere **quali pacchetti possono attraversare il firewall** e quali devono essere bloccati, applicando regole che si basano sui campi degli header IP e TCP/UDP.

Per capire il packet filtering è necessario richiamare alcuni elementi fondamentali dei protocolli di rete: un filtro di pacchetto non ragiona sul “significato applicativo” della comunicazione, ma sui campi che trova nelle intestazioni, come indirizzo sorgente, indirizzo destinazione, protocollo, porte e flag.

Il filtraggio dei pacchetti può essere:

- **stateless (static)** → analizza ogni pacchetto singolarmente;
    
- **stateful (dinamico)** → tiene traccia dello stato delle connessioni in corso.
    

---

## **2. Analisi degli header di rete**

### **2.1 Header IP**

Ogni pacchetto IP contiene informazioni fondamentali che il firewall utilizza per il controllo:

![](imgs/Pasted%20image%2020260709142059.png)

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

Il livello IP è quindi il primo punto in cui il firewall può decidere se un pacchetto è coerente con la policy: può filtrare per sorgente, destinazione, protocollo trasportato, interfaccia di ingresso/uscita e, in alcuni casi, campi legati alla frammentazione.

---

### **2.2 Header TCP**

Il protocollo TCP fornisce informazioni aggiuntive utili per stabilire lo **stato delle connessioni** e la **direzione del traffico**.

Tra i campi più importanti:

![](imgs/Pasted%20image%2020260709142118.png)

- **Porta sorgente e destinazione**
    
- **Sequence number e acknowledgment number**
    
- **Flag di controllo**:
    
    - **SYN** → richiesta di connessione, caratteristica del primo pacchetto di una connessione TCP
        
    - **ACK** → conferma di ricezione del pacchetto precedente, sia esso un pacchetto dati o un pacchetto di controllo come SYN/FIN
        
    - **FIN** → terminazione concordata e ordinata della sessione
        
    - **RST** → reset della connessione, utile anche in alcuni scenari di attacco o scansione
        
    - **PSH** → invio immediato dei dati senza buffering
        

#### **Il significato dei sequence e acknowledgment number**

Ogni flusso TCP è diviso in pacchetti numerati sequenzialmente.  
Quando un pacchetto viene ricevuto, il destinatario risponde con un **ACK** che contiene:  
$$  
ack = seq_{ricevuto} + 1  
$$  
Questo garantisce l’ordinamento e la corretta ricostruzione dei dati ricevuti.

Il valore di acknowledgment informa quindi il mittente che un certo segmento è stato ricevuto correttamente; se la conferma non arriva, TCP può attivare meccanismi di ritrasmissione. Per un firewall, questi campi diventano utili soprattutto quando il filtro mantiene stato e vuole verificare che un pacchetto sia coerente con una sessione realmente esistente.

---

### **2.3 Il Three-Way Handshake**

Il TCP utilizza una **procedura a tre fasi** per instaurare la connessione tra client e server.

![](imgs/Pasted%20image%2020260709142215.png)

|Fase|Direzione|Flag|Descrizione|
|---|---|---|---|
|1|Client → Server|SYN|Il client richiede di aprire una connessione|
|2|Server → Client|SYN + ACK|Il server accetta e risponde|
|3|Client → Server|ACK|Il client conferma l’apertura della connessione|

Solo dopo il completamento del three-way handshake la connessione è considerata **attiva e affidabile**.

Dal punto di vista del firewall, il three-way handshake è una fase critica: osservando SYN, SYN+ACK e ACK si può distinguere una connessione realmente instaurata da pacchetti che simulano una risposta o cercano di inserirsi in una comunicazione mai aperta correttamente.

---

### **2.4 Il protocollo UDP**

Il protocollo **UDP (User Datagram Protocol)** si trova allo stesso livello di TCP ma **non è orientato alla connessione**.

- Non esiste un handshake: i pacchetti vengono inviati senza sapere se il destinatario è pronto.
    
- Non esistono sequence number e acknowledgment number paragonabili a TCP: UDP non fornisce affidabilità, ordinamento o ritrasmissione automatica.
    
- L’header è **molto più semplice** e contiene solo:
    
    - Porta sorgente
        
    - Porta destinazione
        
    - Lunghezza
        
    - Checksum
        

UDP è usato per servizi che richiedono velocità e tollerano perdita di pacchetti (DNS, streaming, VoIP).

Questa semplicità rende UDP più leggero, ma riduce anche le informazioni disponibili al firewall: non essendoci una connessione da seguire, il filtro deve basarsi soprattutto su indirizzi, porte e protocollo.

---

## **3. Static Stateless Packet Filter (SPF)**

### **3.1 Definizione**

Il **packet filter statico e stateless** controlla il traffico basandosi **solo sulle informazioni degli header** dei pacchetti.  
Ogni pacchetto è confrontato con una lista di regole (ACL – _Access Control List_) e **accettato o scartato** a seconda del risultato.

> 📌 Ogni pacchetto è valutato **indipendentemente** dagli altri: non si tiene traccia delle connessioni.

---

### **3.2 Parametri di filtraggio**

Il filtro IP può bloccare o permettere pacchetti in base a:

- tipo di servizio o **porta TCP/UDP**,
    
- campo **protocol** (es. ICMP, TCP, UDP),
    
- **indirizzo IP** sorgente o destinazione,
    
- **indirizzo MAC** sorgente o destinazione,
    
- **interfaccia** di ingresso o uscita.
    

Questo approccio opera principalmente sui livelli **3 (Network)** e **4 (Transport)** del modello ISO/OSI.

Se il filtro lavora soltanto a livello IP o inferiore, le informazioni disponibili sono ancora più limitate: protocollo trasportato, indirizzi, eventuali campi di frammentazione e interfaccia di provenienza o destinazione. Per salire di precisione bisogna considerare anche TCP/UDP, quindi porte e flag.

![](imgs/Pasted%20image%2020260709142301.png)

---

### **3.3 Caratteristiche**

Il filtro SPF rappresenta la **prima tecnologia firewall** della storia.  
Nonostante oggi sia stato superato dai firewall stateful, è ancora utilizzato nei **router e sistemi di fascia bassa** per via della sua semplicità ed efficienza.

Storicamente è stato adottato per primo anche perché richiede poca capacità computazionale: controllare pochi campi dell’intestazione è molto meno costoso che interpretare il contenuto applicativo dei pacchetti. I sistemi moderni, invece, raramente sono puramente statici o puramente stateless: combinano più tecniche, ma la classificazione resta utile a fini didattici.

#### **Vantaggi**

- Indipendente dalle applicazioni
    
- Elevata scalabilità
    
- Prestazioni eccellenti
    
- Basso costo (spesso integrato nei router o nei sistemi operativi)
    

#### **Svantaggi**

- Controlli **poco precisi**, facilmente eludibili (es. _IP spoofing_)
    
- Nessuna analisi dello stato delle connessioni
    
- Difficoltà nel gestire servizi con **porte dinamiche** (es. FTP attivo)
    

Il problema delle porte dinamiche nasce perché alcune applicazioni negoziano nel payload quali porte useranno in seguito. Un filtro statico che non legge il contenuto applicativo vede soltanto le intestazioni e quindi non riesce a seguire questa negoziazione.

#### **Nota terminologica**

Nella terminologia pratica, **static packet filtering** e **stateless packet filtering** sono spesso usati insieme: il filtro applica regole statiche e non conserva memoria del traffico precedente. Quando invece il firewall conserva stato si parla tipicamente di **dynamic packet filtering** o **stateful packet filtering**.

> ⚠️ Nei prodotti reali le categorie non sono sempre nette: è necessario leggere le specifiche tecniche del firewall per capire quali livelli di ispezione siano effettivamente implementati.

---

### **3.4 Applicazioni del packet filtering statico**

Gli SPF sono comunemente utilizzati come **prima linea di difesa** in configurazioni perimetrali semplici, con regole del tipo:

- La rete interna può **iniziare connessioni verso l’esterno**,
    
- Ma l’esterno **non può iniziare connessioni verso l’interno**.
    

Esempi:

- Connessioni **SSH** o **ping** solo da LAN → Internet
    
- Blocco totale delle connessioni inverse.

Questa configurazione è tipica quando la LAN deve poter navigare o usare servizi remoti, ma non deve essere direttamente raggiungibile dall’esterno. La situazione cambia se esiste una DMZ: in quel caso alcuni servizi pubblici devono essere raggiungibili da Internet, ma non per questo la rete interna deve diventare accessibile.

![](imgs/Pasted%20image%2020260709142358.png)
    

---

## **4. Gestione delle connessioni TCP e protocolli connectionless**

### **4.1 Controllo delle connessioni TCP**

Il firewall può decidere di consentire solo le connessioni con **flag coerenti** (es. SYN da interno verso esterno).

In un SPF configurato in modo corretto:

- Solo i pacchetti che avviano connessioni legittime (SYN) da dentro la rete vengono accettati.
    
- I pacchetti provenienti dall’esterno con SYN vengono **scartati**.

Il ragionamento è il seguente: poiché una connessione TCP inizia con SYN, bloccare i SYN provenienti dall’esterno impedisce l’apertura di nuove connessioni dall’esterno verso la rete interna. Al contrario, i SYN in uscita possono essere ammessi se la policy consente agli host interni di iniziare connessioni verso Internet.

> ⚠️ Questo controllo non equivale a una vera gestione dello stato: un filtro stateless osserva i flag del singolo pacchetto, ma non ricostruisce l’intera storia della connessione.
    

---

### **4.2 Protocolli connectionless**

I protocolli senza connessione, come **UDP** e **ICMP**, non prevedono handshake.  
Le comunicazioni possono essere:

- **bidirezionali** → es. _Ping (ICMP Echo Request/Reply)_, _DNS query (UDP)_
    
- **unidirezionali** → es. _ICMP Source Quench_
    

Un firewall SPF deve gestire entrambi i casi in modo coerente con la policy definita.

Qui emerge un aspetto pratico importante: per configurare bene un firewall bisogna conoscere i protocolli applicativi e di rete. Non basta sapere che un servizio “usa UDP” o “usa ICMP”; bisogna capire se il flusso previsto è unidirezionale, bidirezionale, quali porte coinvolge e quali risposte sono legittime.

---

## **5. Efficacia e limiti dello SPF**

Gli SPF sono efficaci contro attacchi semplici ma **non contro tecniche sofisticate**.

La ragione è strutturale: il filtro vede intestazioni, non contenuto applicativo. Può bloccare indirizzi, porte, protocolli e alcuni comportamenti evidenti, ma non può interpretare ciò che avviene nel payload.

### **Controlli tipici**

- **IP Spoofing:** verifica dell’indirizzo sorgente
    
- **Tentativi di connessione:** controllo su indirizzi, porte e flag TCP
    
- **Traffico ICMP:** filtro su tipo e codice
    
- **Source Routing:** blocco dei pacchetti che usano routing esplicito
    

Spesso, lo **SPF è implementato direttamente nel border router**, come primo livello di protezione perimetrale.

Questa scelta è efficiente, ma va valutata con attenzione: il border router può avere capacità di filtraggio limitate, può non essere sotto il pieno controllo dell’organizzazione e non sostituisce un firewall dedicato quando servono policy più articolate.

Gli SPF restano comunque necessari come **prima scrematura**: molte minacce persistenti non sono zero-day sofisticati, ma tecniche note da anni che continuano a funzionare contro reti esposte o configurate male.

> 📌 È utile per bloccare minacce basilari e comprendere il comportamento dei protocolli, ma non offre sicurezza completa.

---

## **6. Stateful Packet Filtering**

### **6.1 Differenza concettuale**

Un **packet filter** può essere:

- **Stateless:** ogni pacchetto analizzato indipendentemente;
    
- **Stateful:** il firewall mantiene **una tabella delle connessioni attive** (state table) per riconoscere pacchetti appartenenti a sessioni già autorizzate.
    

Oggi la distinzione è più teorica che pratica: quasi tutti i firewall moderni implementano un certo grado di **protocol inspection** multilivello.

Il passaggio concettuale è però essenziale: un filtro stateless decide guardando solo il pacchetto corrente; un filtro stateful decide anche in base a ciò che ha già visto transitare.

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

Il **session hijacking** consiste nel tentativo di inserirsi in una sessione già stabilita fingendo di essere uno degli attori legittimi. Un filtro statico difficilmente può rilevarlo, mentre un filtro stateful può confrontare il pacchetto con le informazioni memorizzate sulla connessione.

---

### **6.3 Funzionamento pratico**

Quando un pacchetto arriva:

1. Il firewall controlla se appartiene a una **connessione registrata** nella state table.
    
    - Se sì → il pacchetto passa senza ulteriori controlli.
        
2. Se non appartiene a una connessione nota → viene trattato come **nuovo pacchetto** e sottoposto alle regole di filtraggio.
    

Questo approccio può migliorare le prestazioni, perché i pacchetti già riconducibili a connessioni autorizzate non devono essere valutati ogni volta come se fossero completamente nuovi.

Il vantaggio ha però un costo: il firewall deve mantenere in memoria una **connection/state table** con le connessioni attive o recentemente osservate. La progettazione del firewall deve quindi bilanciare accuratezza del controllo, consumo di memoria e velocità di forwarding.

> 📌 Lo stateful filtering migliora sicurezza e prestazioni, ma introduce uno stato interno che deve essere gestito correttamente.

---

## **7. Considerazioni finali**

Anche i migliori sistemi di packet filtering **non analizzano il contenuto** dei pacchetti.  
Ciò significa che:

- non possono **bloccare virus o malware**,
    
- hanno problemi con protocolli che **negoziano porte dinamicamente** (es. FTP attivo),
    
- non riescono a rilevare attacchi basati sul contenuto applicativo.
    

Per questo motivo, i produttori stanno integrando tecniche di **Deep Packet Inspection (DPI)** e **intelligenza artificiale**, specialmente in ambito **IoT** (es. acquisizione di Sentryo da parte di Cisco).

Il punto non è solo bloccare più traffico, ma riconoscere traffico anomalo che usa porte apparentemente lecite o che nasconde la parte rilevante dell’attacco nel payload. Questa evoluzione è sempre più importante perché il firewall deve gestire molto traffico senza rallentare eccessivamente la rete.

---

### **8. Porte e servizi su Internet**

Ogni applicazione o servizio di rete è associato a **porte numeriche standard** (es. 80 per HTTP, 443 per HTTPS).  
Tuttavia, alcuni malware o servizi malevoli **nascondono le loro porte** per sfuggire ai controlli dei firewall basati solo su numeri di porta o protocolli.

L’associazione tra porta e servizio è una convenzione, non una garanzia: un servizio può essere configurato su una porta non standard e un traffico malevolo può usare porte normalmente considerate legittime.

> 📌 Per questo è necessario un approccio multilivello, che unisca packet filtering, DPI e analisi comportamentale.

![](imgs/Pasted%20image%2020260709142439.png)

---

### **9. Conclusione**

Il **packet filtering** rappresenta il fondamento della sicurezza di rete, ma da solo **non basta**.  
È il primo livello della difesa, efficace solo se:

- integrato con meccanismi di **stateful inspection**,
    
- costantemente aggiornato,
    
- e accompagnato da una **politica di sicurezza chiara e coerente**.
    

> 📌 In sintesi: lo stateless packet filtering effettua una prima selezione sui pacchetti, mentre il firewall stateful riconosce anche la storia delle connessioni già autorizzate.
