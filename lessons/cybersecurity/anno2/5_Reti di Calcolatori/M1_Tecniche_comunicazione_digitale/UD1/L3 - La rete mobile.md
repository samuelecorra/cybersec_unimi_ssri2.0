# **Lezione 3: La rete mobile**

### **1. Dalle radio mobili ai telefoni cellulari**

Le **reti mobili** nascono come evoluzione delle prime comunicazioni radio.  
Il **primo sistema radio mobile** fu sviluppato nei **Laboratori Bell** e introdotto nel **1946**.  
All’epoca si trattava di un sistema molto rudimentale: poche frequenze disponibili e pochissimi utenti serviti.

- Negli **anni ’60** furono introdotti i primi miglioramenti, ma la capacità rimaneva limitata
    
- Nel **1976**, la rete mobile di New York disponeva di appena **12 canali** e serviva **543 clienti paganti**

Il sistema era quindi **insostenibile su larga scala**: era necessario un nuovo modello di gestione dello spettro radio.

---

### **2. Il concetto di “cellulare”**

Nel **1968**, i Laboratori Bell proposero alla **Federal Communications Commission (FCC)** il concetto di **telefonia cellulare**:  
anziché un’unica grande antenna per una città, il territorio veniva **suddiviso in celle**, ciascuna servita da una stazione radio a bassa potenza.

![](imgs/Pasted%20image%2020260212114951.png)

Questo modello risolveva due problemi fondamentali:

1. **Riuso delle frequenze:** celle lontane possono usare le stesse frequenze senza interferire.
    
2. **Efficienza di banda:** il canale radio viene occupato solo durante la conversazione, non perennemente.
    

In pratica, la rete cellulare è una **rete di piccole radio interconnesse**, coordinate in modo da fornire copertura continua a un’area geografica estesa.

---

### **3. I primi esperimenti**

Negli Stati Uniti, la **FCC** assegnò alle telecomunicazioni mobili lo spettro compreso tra **825–845 MHz** e **870–890 MHz**, recuperato liberando i canali televisivi UHF 70–83.  
L’azienda **AT&T** fu incaricata di sviluppare il sistema a **Chicago**, uno dei primi test di rete cellulare urbana.

Tuttavia, i primi telefoni mobili generarono molte **polemiche**:

- banda radio molto limitata e facilmente congestionata;
    
- uso inefficiente delle frequenze, soprattutto durante i **momenti di silenzio** della conversazione.
    

---

### **4. Funzionamento di una rete cellulare**

Ogni **cella** è un’area geografica servita da una **stazione radio a bassa potenza** (raggio tipico tra **3 e 15 km**).  
Le celle sono progettate per **sovrapporsi parzialmente**, così da garantire **copertura continua**.

I principi fondamentali sono:

- Il **riuso delle frequenze**: due celle distanti possono usare le stesse frequenze senza interferenze.
    
- La **riduzione dell’interferenza**: limitando la potenza del segnale, si minimizza il disturbo su celle adiacenti.
    
- La **continuità del servizio**: quando un utente si sposta, il telefono misura continuamente la potenza dei segnali circostanti e si connette automaticamente al segnale più forte.
    

Il passaggio da una cella all’altra si chiama **handover** (o “consegna”):  
il sistema trasferisce la comunicazione alla nuova cella **senza interrompere la chiamata**.  
Idealmente, questo processo è **trasparente all’utente**.

---

### **5. Le stazioni base e la rete di controllo**

Le reti mobili si appoggiano alle **reti telefoniche esistenti** per completare le chiamate, soprattutto verso telefoni fissi.  
Ogni gruppo di celle è coordinato da un **MTSO (Mobile Telephone Switching Office)**, ossia il “cuore” della rete cellulare.

![](imgs/Pasted%20image%2020260212115737.png)

Le sue funzioni principali:

- collegare la rete mobile alla rete telefonica pubblica **PSTN**;
    
- gestire la **commutazione** delle chiamate mobili;
    
- coordinare gli **handover** durante gli spostamenti dell’utente;
    
- allocare e liberare i canali radio;
    
- monitorare lo stato dei dispositivi registrati nella zona.
    

Se chiami un telefono fisso, il tuo cellulare comunica con l’MTSO, che **inserisce la chiamata nella PSTN**.  
Se chiami un altro cellulare, l’MTSO **gestisce direttamente il collegamento tra le due celle**.

---

### **6. Il telefono cellulare come radio**

Un **telefono cellulare** è a tutti gli effetti una **radio ricetrasmittente**.  
Ogni cella utilizza un **insieme di frequenze specifiche** per trasmettere e ricevere.

Il funzionamento generale è il seguente:

1. Quando accendi il telefono, questo si **registra** sulla rete e ascolta una **frequenza di controllo**.
    
2. Quando effettui una chiamata, il telefono **richiede al sistema** una coppia di frequenze (una per trasmettere, una per ricevere).
    
3. Quando ricevi una chiamata, il sistema la instrada tramite il canale di controllo, che comunica al telefono **quale frequenza usare** per stabilire la connessione.
    

Questo scambio dinamico di frequenze è gestito interamente dalla rete, così che l’utente percepisca un servizio continuo e trasparente.

---

### **7. Le generazioni della tecnologia cellulare**

#### **AMPS – Advanced Mobile Phone System (1G)**

- **Prima generazione**, completamente **analogica**.
    
- Basata su **commutazione di circuito**.
    
- Largamente diffusa negli Stati Uniti per decenni.
    
- Elevato consumo di banda e scarsa sicurezza, ma tecnologia pionieristica.
    

#### **GSM – Global System for Mobile Communications (2G)**

- **Seconda generazione**, completamente **digitale**.
    
- Standard europeo adottato a livello mondiale.
    
- Introduce **SMS**, **cifratura delle comunicazioni**, e **roaming internazionale**.
    
- Usa tecniche di **multiplexing** per condividere i canali (TDMA).
    

#### **IS-95 – Interim Standard 95**

- Standard alternativo sviluppato negli USA.
    
- Sempre **digitale**, ma basato su **CDMA (Code Division Multiple Access)** anziché TDMA.
    
- Incompatibile con GSM, il che portò a frammentazione tra operatori.
    

#### **UMTS – Universal Mobile Telecommunication System (3G)**

- **Terza generazione**, totalmente digitale.
    
- Offre **trasmissione dati ad alta velocità**, videochiamate e Internet mobile.
    
- Usa il **W-CDMA** come tecnologia di accesso radio.
    

---

### **8. Dalla telefonia mobile all’Internet mobile**

Le reti mobili moderne derivano direttamente dai principi introdotti da Bell negli anni ’60:

- divisione in celle,
    
- riuso delle frequenze,
    
- handover automatico,
    
- gestione centralizzata delle connessioni.
    

Con il tempo, la telefonia mobile ha **incorporato i protocolli dati di Internet**, evolvendosi in reti **4G LTE** e oggi **5G**, dove la voce stessa viaggia come **pacchetti IP** (Voice over LTE, Long Term Evolution).

---

### **9. Riassunto concettuale**

- Le **reti mobili** nascono dai sistemi radio mobili degli anni ’40.
    
- Il **concetto di cella** (Bell Labs, 1968) consente il riuso delle frequenze e la copertura continua.
    
- L’**handover** garantisce la continuità della chiamata durante gli spostamenti.
    
- Le **stazioni base (BTS)** e gli **MTSO** coordinano il traffico e l’accesso alla PSTN.
    
- Ogni cellulare è una **radio intelligente** che negozia dinamicamente le frequenze.
    
- Le **generazioni (1G → 2G → 3G)** segnano il passaggio da analogico a digitale, fino all’integrazione con le reti dati.