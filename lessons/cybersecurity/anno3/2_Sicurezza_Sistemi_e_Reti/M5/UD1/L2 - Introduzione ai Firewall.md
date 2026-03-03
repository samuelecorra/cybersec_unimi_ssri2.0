## **Lezione 2: Introduzione ai Firewall**

### **1. Il ruolo del firewall nella sicurezza di rete**

Il **firewall** è il principale meccanismo di **protezione perimetrale** di una rete informatica.  
Il suo scopo è **controllare l’accesso** tra una rete interna (privata) e l’esterno (Internet o altre reti), **filtrando** il traffico secondo regole di sicurezza definite dall’amministratore.

Tutte le connessioni devono **obbligatoriamente passare attraverso il firewall**, dove vengono **analizzate, valutate e autorizzate o bloccate** in base a criteri precisi.  
In questo modo, il firewall funge da **punto di ispezione unico** per tutto il traffico di rete.

Un firewall può essere:

- un **router** dedicato alla sicurezza, oppure
    
- un **computer** (server o PC) configurato per proteggere host o sottoreti specifiche.
    

---

### **2. Personal firewall**

Il **personal firewall** è un software installato su un singolo computer (host), progettato per **proteggere l’utente da connessioni indesiderate** provenienti dall’esterno.

Agisce filtrando le comunicazioni **in ingresso e in uscita** del dispositivo, monitorando:

- le applicazioni che tentano di accedere a Internet,
    
- i pacchetti ricevuti da indirizzi remoti sospetti.
    

Questo tipo di firewall è molto utile su sistemi individuali (es. computer portatili o domestici), ma **non sostituisce** un firewall di rete, poiché agisce solo **a livello locale**.

---

### **3. Firewall ≠ sicurezza totale**

Un firewall, anche se ben configurato, **non garantisce da solo la sicurezza assoluta**.  
Esistono diverse categorie di minacce che possono aggirarlo o sfruttare vulnerabilità di altro tipo:

#### **a. Insider attacks**

Attacchi che **provengono dall’interno** dell’organizzazione, da utenti o dispositivi già autorizzati.  
In questo caso, il firewall può solo limitare i danni attraverso una **partizione accurata delle risorse**.

#### **b. Patch di sicurezza**

Molti sistemi di firewall non si aggiornano automaticamente; **se si dimentica di applicare le patch**, il dispositivo può diventare vulnerabile a exploit noti.

#### **c. Errori di configurazione**

I firewall sono **complessi da configurare** e spesso presentano **regole in conflitto** o configurazioni errate che aprono varchi involontari.

#### **d. Mancanza di Deep Packet Inspection (DPI)**

Non tutti i firewall analizzano i pacchetti fino al livello 7 (applicativo) del modello ISO/OSI.  
Un firewall privo di DPI può essere aggirato da **attacchi che sfruttano contenuti applicativi** (es. malware nascosti in HTTP o HTTPS).

#### **e. Attacchi DDoS**

Gli **attacchi distribuiti di negazione del servizio** sono facili da lanciare ma **molto difficili da bloccare** completamente, anche con firewall di fascia alta.

> Il firewall riduce il rischio, ma non elimina la necessità di monitoraggio costante e aggiornamenti regolari.

---

### **4. I tre principi fondamentali dei firewall**

I concetti base definiti da **Cheswick e Bellovin** restano tutt’oggi i pilastri della progettazione di un sistema firewall:

1. **Il firewall deve essere l’unico punto di contatto** tra la rete interna e l’esterno.  
    Tutto il traffico deve transitare attraverso di esso.
    
2. **Solo il traffico autorizzato può attraversare il firewall.**  
    Tutte le altre connessioni devono essere bloccate o scartate.
    
3. **Il firewall deve essere sicuro in sé stesso.**  
    Se il dispositivo è vulnerabile, compromette l’intera rete che protegge.
    

---

### **5. Vulnerabilità note nei firewall**

Anche i firewall più sofisticati possono contenere **vulnerabilità software**.  
Ecco alcuni esempi reali documentati nel database CVE:

#### **CVE-2018-0986 – Out-of-Bounds Write**

Una vulnerabilità nella **Microsoft Malware Protection Engine** permetteva l’esecuzione di codice remoto tramite un file appositamente costruito.  
Ha coinvolto prodotti come:

- Windows Defender
    
- Microsoft Security Essentials
    
- Microsoft Exchange Server
    
- System Center e Forefront Endpoint Protection.
    

---

#### **CVE-2024-41592 – Stack-based Buffer Overflow**

Un **overflow di buffer nello stack** nella web interface di alcuni router poteva causare:

- **crash del dispositivo** (DoS), o
    
- **esecuzione di codice remoto**, se combinato con altre vulnerabilità correlate (es. CVE-2024-41585).  
    Si attivava inviando **query HTTP anomale** alle pagine CGI.
    

---

#### **CVE-2020-3373 – Uncontrolled Resource Consumption**

Una falla nel sistema di gestione dei **frammenti IP** di **Cisco ASA** e **Cisco Firepower** consentiva a un attaccante remoto di causare **memory leak** e **DoS**.  
Il traffico non veniva più processato, bloccando la rete.

---

#### **CVE-2020-3529 – DoS via SSL VPN Negotiation**

Altra vulnerabilità in **Cisco ASA** e **Firepower FTD**, questa volta nel processo di **negoziazione SSL VPN**.  
Un attaccante remoto poteva forzare un **riavvio del dispositivo**, causando interruzioni di servizio.

> Questi esempi mostrano che anche i sistemi di difesa necessitano di **monitoraggio continuo**, patch tempestive e aggiornamenti software.

---

### **6. Firewall e livelli ISO/OSI**

I firewall possono operare su diversi **livelli del modello ISO/OSI**, a seconda della loro complessità e del tipo di filtraggio:

|Tipo di firewall|Livello ISO/OSI|Descrizione|
|---|---|---|
|**Packet filter**|Livello 3–4|Controlla intestazioni IP, TCP/UDP (indirizzi, porte, protocolli).|
|**Circuit gateway**|Livello 5|Controlla la creazione delle connessioni (sessioni).|
|**Application gateway (proxy)**|Livello 7|Analizza il contenuto dei pacchetti e le richieste applicative.|

> In generale, maggiore è il livello di analisi, più profonda è la protezione, ma aumenta anche il carico computazionale.

---

### **7. Firewall di rete**

Un **firewall di rete** è una macchina dedicata che filtra **tutto il traffico in entrata e uscita** verso la rete locale.  
In pratica, si colloca **tra la LAN interna e Internet**, diventando il **punto di controllo del traffico**.

```
LAN  ⇄  FIREWALL  ⇄  INTERNET
```

Senza un firewall, tutti i dispositivi della LAN sarebbero **direttamente esposti** ai rischi di Internet.

---

### **8. Protezione perimetrale e DMZ**

#### **a. Il problema**

Alcuni computer nella rete interna devono fornire **servizi pubblici** (web server, mail server, FTP).  
Se il firewall consentisse l’accesso diretto, l’intera rete interna risulterebbe esposta.

#### **b. La soluzione: la DMZ (De-Militarized Zone)**

Per separare i sistemi pubblici da quelli privati, si introduce una **zona intermedia** tra Internet e la rete interna: la **DMZ**.

- La DMZ ospita i server pubblici accessibili dall’esterno.
    
- La rete interna rimane invisibile e inaccessibile dall’esterno.
    
- Il firewall **filtra e controlla** rigorosamente il traffico tra le tre zone.
    

```
INTERNET ⇄ FIREWALL ⇄ DMZ ⇄ FIREWALL ⇄ RETE INTERNA
```

#### **c. Architettura a tre gambe**

Per gestire questa separazione, il firewall dispone di **tre interfacce di rete**:

1. **WAN (esterna)** → verso Internet,
    
2. **DMZ (zona pubblica)** → per i servizi accessibili,
    
3. **LAN (interna)** → rete privata e riservata.
    

Questa configurazione prende il nome di **three-legged architecture**.

---

### **9. Politiche di filtraggio e default policy**

Il comportamento del firewall si basa su **policy di accesso** che determinano quali connessioni sono permesse o vietate.  
Le due politiche di default principali sono:

- **Default deny (principio del minimo privilegio):**  
    Tutto è vietato, tranne ciò che è esplicitamente permesso. ✅ _(approccio consigliato)_
    
- **Default allow:**  
    Tutto è permesso, tranne ciò che è esplicitamente vietato. ❌ _(approccio rischioso)_
    

---

### **10. Effetti del firewall sulla rete**

Il firewall consente di:

- definire **zone con diversi livelli di sicurezza**,
    
- controllare le **connessioni tra le interfacce**,
    
- limitare l’accesso diretto ai soli componenti esterni,
    
- isolare eventuali compromissioni nella **DMZ**, impedendo che si propaghino alla rete interna.
    

> In questo modo il firewall diventa il **fulcro della sicurezza perimetrale**, garantendo una separazione chiara tra interno, esterno e zona intermedia.

---

### **11. Origini storiche dei firewall**

I primi firewall apparvero alla fine degli anni ’80:

- **1989 – Unix-based gateway** (Mogul)
    
- **1992 – ULTRIX e proxy gateway** (Ranum)
    

Questi sistemi agivano come **gatekeeper**: ricevevano richieste dall’interno e le inoltravano verso l’esterno, filtrando il traffico in base a regole statiche.

---

### **12. Tipi di firewall (livelli di implementazione)**

Esistono diverse modalità di realizzazione dei firewall, che si distinguono per **complessità e profondità di analisi**:

|Categoria|Descrizione|
|---|---|
|**Static packet filtering**|Analizza i pacchetti in base a regole fisse (indirizzi IP, porte, protocolli).|
|**Dynamic packet filtering (stateful)**|Tiene traccia dello stato delle connessioni e consente solo i pacchetti appartenenti a sessioni legittime.|
|**Application gateway (proxy)**|Intermedia le comunicazioni applicative (es. HTTP, FTP) controllando il contenuto dei messaggi.|
|**Circuit-level gateway**|Gestisce la creazione dei canali di comunicazione, garantendo che solo le sessioni autorizzate vengano mantenute.|

---

### **13. Conclusione**

Il firewall è uno **strumento essenziale ma non sufficiente**:

- deve essere **ben progettato e configurato**,
    
- aggiornato costantemente,
    
- e integrato in un sistema di sicurezza più ampio che includa monitoraggio, patch management e segmentazione di rete.
    

> La vera forza del firewall non risiede solo nel bloccare pacchetti, ma nel **definire chiaramente i confini della fiducia** all’interno di una rete.