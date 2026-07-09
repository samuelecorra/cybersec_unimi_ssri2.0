## **Lezione 2: Introduzione ai Firewall**

### **1. Il ruolo del firewall nella sicurezza di rete**

Il **firewall** è il principale meccanismo di **protezione perimetrale** di una rete informatica.  
Il suo scopo è **controllare l’accesso** tra una rete interna (privata) e l’esterno (Internet o altre reti), **filtrando** il traffico secondo regole di sicurezza definite dall’amministratore.

Tutte le connessioni devono **obbligatoriamente passare attraverso il firewall**, dove vengono **analizzate, valutate e autorizzate o bloccate** in base a criteri precisi.  
In questo modo, il firewall funge da **punto di ispezione unico** per tutto il traffico di rete.

Il punto chiave non è solo la presenza del dispositivo, ma il suo **posizionamento**: il firewall deve trovarsi nel punto in cui può effettivamente intercettare il traffico da e verso la rete che deve proteggere. Può quindi essere usato per proteggere un singolo host, una sottorete o l’intera rete aziendale.

Un firewall può essere:

- un **router** dedicato alla sicurezza, oppure
    
- un **computer** (server o PC) configurato per proteggere host o sottoreti specifiche.
    

---

### **2. Personal firewall**

Il **personal firewall** è un software installato su un singolo computer (host), progettato per **proteggere l’utente da connessioni indesiderate** provenienti dall’esterno.

Agisce filtrando le comunicazioni **in ingresso e in uscita** del dispositivo, monitorando:

- le applicazioni che tentano di accedere a Internet,
    
- i pacchetti ricevuti da indirizzi remoti sospetti.
    
![](imgs/Pasted%20image%2020260709141611.png)

Questo tipo di firewall è molto utile su sistemi individuali (es. computer portatili o domestici), ma **non sostituisce** un firewall di rete, poiché agisce solo **a livello locale**.

In termini pratici, il personal firewall protegge la **connettività offerta e ricevuta** dall’host: può limitare quali servizi locali siano raggiungibili dall’esterno e quali applicazioni locali possano aprire connessioni verso la rete.

---

### **3. Firewall ≠ sicurezza totale**

Un firewall, anche se ben configurato, **non garantisce da solo la sicurezza assoluta**.  
Esistono diverse categorie di minacce che possono aggirarlo o sfruttare vulnerabilità di altro tipo:

La sicurezza non dipende soltanto dalla qualità dello strumento, ma soprattutto da:

- **politiche di sicurezza** adottate,
    
- **configurazione effettiva** delle regole,
    
- conoscenza di **minacce e vulnerabilità** rilevanti,
    
- processi organizzativi, competenze e manutenzione operativa.
    

> ⚠️ Un firewall di alto livello, se configurato male, può offrire una protezione inferiore a un firewall più semplice ma coerente con una policy chiara.

#### **a. Insider attacks**

Attacchi che **provengono dall’interno** dell’organizzazione, da utenti o dispositivi già autorizzati.  
In questo caso, il firewall perimetrale può solo limitare i danni attraverso una **partizione accurata delle risorse**, perché l’origine dell’attacco non è esterna.

Ciò non significa che il firewall sia inutile: può comunque controllare il **traffico in uscita** e segnalare connessioni anomale o non conformi rispetto al comportamento atteso della rete.

#### **b. Patch di sicurezza**

Molti sistemi di firewall non si aggiornano automaticamente; **se si dimentica di applicare le patch**, il dispositivo può diventare vulnerabile a exploit noti.  
Applicare patch non significa ottenere un sistema senza falle, ma correggere vulnerabilità specifiche già note.

Prima di applicare una patch è comunque necessario capire:

- quale vulnerabilità viene corretta,
    
- quali minacce vengono mitigate,
    
- quale impatto operativo può avere l’aggiornamento sul firewall.
    

Questo ragionamento vale sia per gli host protetti sia per il firewall stesso, che si trova in una posizione particolarmente esposta.

#### **c. Errori di configurazione**

I firewall sono **complessi da configurare** e spesso presentano **regole in conflitto** o configurazioni errate che aprono varchi involontari.  
Una regola apparentemente corretta può interagire male con altre regole e consentire traffico che avrebbe dovuto essere bloccato.

La configurazione è anche un punto critico di sicurezza: se un attaccante o un utente interno ottiene accesso amministrativo al firewall, può inserire regole che fanno transitare traffico non autorizzato senza generare allarmi evidenti.

#### **d. Mancanza di Deep Packet Inspection (DPI)**

Non tutti i firewall analizzano i pacchetti fino al livello 7 (applicativo) del modello ISO/OSI.  
Un firewall privo di DPI può essere aggirato da **attacchi che sfruttano contenuti applicativi** (es. malware nascosti in HTTP o HTTPS).

I firewall di livello 7 sono più potenti perché osservano il contenuto applicativo, ma richiedono:

- hardware adeguato,
    
- maggiore capacità computazionale,
    
- attenzione alle prestazioni, perché ogni analisi aggiuntiva introduce latenza.
    

Per questo motivo non sono sempre disponibili o sostenibili in tutte le reti.

#### **e. Attacchi DDoS**

Gli **attacchi distribuiti di negazione del servizio** sono facili da lanciare ma **molto difficili da bloccare** completamente, anche con firewall di fascia alta.  
Il traffico può provenire da molti indirizzi IP differenti e raggiungere volumi tali da saturare la capacità del firewall prima ancora che il traffico venga filtrato.

> 📌 Il firewall riduce il rischio, ma non elimina la necessità di monitoraggio costante, aggiornamenti regolari e controllo delle attività interne alla rete.

---

### **4. I tre principi fondamentali dei firewall**

I concetti base definiti da **Cheswick e Bellovin** restano tutt’oggi i pilastri della progettazione di un sistema firewall:

1. **Il firewall deve essere l’unico punto di contatto** tra la rete interna e l’esterno.  
    Tutto il traffico deve transitare attraverso di esso. Se esistono altri punti di accesso, come hotspot, collegamenti Wi-Fi non controllati o connessioni dirette verso Internet, il firewall viene aggirato.
    
2. **Solo il traffico autorizzato può attraversare il firewall.**  
    Tutte le altre connessioni devono essere bloccate o scartate. Il traffico autorizzato deve essere definito esplicitamente in base ai servizi realmente necessari.
    
3. **Il firewall deve essere sicuro in sé stesso.**  
    Se il dispositivo è vulnerabile, compromette l’intera rete che protegge. Un firewall compromesso può essere aggirato, disattivato o riconfigurato per consentire connessioni che dovrebbero essere vietate.
    

---

### **5. Vulnerabilità note nei firewall**

Anche i firewall più sofisticati possono contenere **vulnerabilità software**.  
Ecco alcuni esempi reali documentati nel database CVE:

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il problema riguarda in generale tutto il software che analizza traffico o pacchetti di rete: se il codice di analisi è vulnerabile, un input costruito ad hoc può causare crash, corruzione di memoria, esecuzione di codice remoto o consumo anomalo di risorse.

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
    
Le interfacce web di amministrazione sono particolarmente delicate: espongono funzionalità privilegiate e, se vulnerabili, possono portare al pieno controllo del dispositivo.

---

#### **CVE-2020-3373 – Uncontrolled Resource Consumption**

Una falla nel sistema di gestione dei **frammenti IP** di **Cisco ASA** e **Cisco Firepower** consentiva a un attaccante remoto di causare **memory leak** e **DoS**.  
Il traffico non veniva più processato, bloccando la rete.

L’aspetto interessante è che la vulnerabilità nasce dal trattamento dei pacchetti frammentati: se la deframmentazione non è implementata correttamente, pacchetti costruiti in modo particolare possono produrre effetti di sicurezza inattesi.

---

#### **CVE-2020-3529 – DoS via SSL VPN Negotiation**

Altra vulnerabilità in **Cisco ASA** e **Firepower FTD**, questa volta nel processo di **negoziazione SSL VPN**.  
Un attaccante remoto poteva forzare un **riavvio del dispositivo**, causando interruzioni di servizio.

Anche quando una vulnerabilità non porta direttamente a esfiltrazione di dati, può comunque produrre un danno grave: il dispositivo può riavviarsi, scollegarsi dalla rete o interrompere il filtraggio, rendendo indisponibile il servizio.

> 📌 Questi esempi mostrano che anche i sistemi di difesa necessitano di **monitoraggio continuo**, patch tempestive e aggiornamenti software.

---

### **6. Firewall e livelli ISO/OSI**

I firewall possono operare su diversi **livelli del modello ISO/OSI**, a seconda della loro complessità e del tipo di filtraggio:

![](imgs/Pasted%20image%2020260709141651.png)

|Tipo di firewall|Livello ISO/OSI|Descrizione|
|---|---|---|
|**Packet filter**|Livello 3–4|Controlla intestazioni IP, TCP/UDP (indirizzi, porte, protocolli).|
|**Circuit gateway**|Livello 5|Controlla la creazione delle connessioni (sessioni).|
|**Application gateway (proxy)**|Livello 7|Analizza il contenuto dei pacchetti e le richieste applicative.|

Più precisamente:

- a livello **network**, il packet filter osserva soprattutto intestazioni IP, indirizzi sorgente/destinazione e campi di controllo;
    
- a livello **trasporto**, il firewall ragiona su TCP/UDP, porte e caratteristiche della connessione;
    
- a livello **applicativo**, il firewall guarda il payload e può interpretare protocolli come HTTP, FTP o altri servizi.
    

> 📌 In generale, maggiore è il livello di analisi, più profonda è la protezione, ma aumenta anche il carico computazionale.

---

### **7. Firewall di rete**

Un **firewall di rete** è una macchina dedicata che filtra **tutto il traffico in entrata e uscita** verso la rete locale.  
In pratica, si colloca **tra la LAN interna e Internet**, diventando il **punto di controllo del traffico**.

![](imgs/Pasted%20image%2020260709141710.png)

Senza un firewall, tutti i dispositivi della LAN sarebbero **direttamente esposti** ai rischi di Internet.

![](imgs/Pasted%20image%2020260709141728.png)

In assenza di filtraggio, il traffico proveniente da Internet può raggiungere direttamente i target interni usando protocolli diversi. Alcuni protocolli possono essere necessari per il business, come FTP o SSH in determinati scenari amministrativi; altri, come Telnet, sono normalmente da bloccare perché espongono il sistema a rischi elevati.

Filtrare significa quindi decidere:

- quali protocolli possono transitare,
    
- in quale direzione possono essere usati,
    
- verso quali host o sottoreti sono ammessi.
    

![](imgs/Pasted%20image%2020260709141838.png)

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

![](imgs/Pasted%20image%2020260709141907.png)

La DMZ non è una rete “sicura” nello stesso senso della LAN interna: è una rete controllata, ma volutamente esposta, perché contiene servizi che devono essere raggiungibili dall’esterno, come web server, mail server o server FTP.

La rete interna, invece, deve rimanere non accessibile dall’esterno. Può essere autorizzata a uscire verso Internet, ad esempio per navigazione o accesso a servizi remoti, ma sempre secondo regole definite.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **c. Architettura a tre gambe**

Per gestire questa separazione, il firewall dispone di **tre interfacce di rete**:

1. **WAN (esterna)** → verso Internet,
    
2. **DMZ (zona pubblica)** → per i servizi accessibili,
    
3. **LAN (interna)** → rete privata e riservata.
    

Questa configurazione prende il nome di **three-legged architecture**.

Dal punto di vista logico, le tre interfacce corrispondono a **zone di sicurezza** diverse. Il firewall può quindi applicare regole differenti a seconda della direzione e della zona coinvolta: Internet verso DMZ, Internet verso LAN, LAN verso Internet, LAN verso DMZ, DMZ verso LAN.

![](imgs/Pasted%20image%2020260709141946.png)

---

### **9. Politiche di filtraggio e default policy**

Il comportamento del firewall si basa su **policy di accesso** che determinano quali connessioni sono permesse o vietate.  
Le due politiche di default principali sono:

- **Default deny (principio del minimo privilegio):**  
    Tutto è vietato, tranne ciò che è esplicitamente permesso. ✅ _(approccio consigliato)_
    
- **Default allow:**  
    Tutto è permesso, tranne ciò che è esplicitamente vietato. ❌ _(approccio rischioso)_
    

La default policy definisce cosa succede quando una connessione non corrisponde ad alcuna regola esplicita. L’approccio più restrittivo e normalmente più sicuro è il **default deny**, perché obbliga l’amministratore a dichiarare in modo consapevole quali servizi devono essere aperti.

---

### **10. Effetti del firewall sulla rete**

Il firewall consente di:

- definire **zone con diversi livelli di sicurezza**,
    
- controllare le **connessioni tra le interfacce**,
    
- limitare l’accesso diretto ai soli componenti esterni,
    
- isolare eventuali compromissioni nella **DMZ**, impedendo che si propaghino alla rete interna.
    

La segmentazione della rete aiuta il firewall perché consente di costruire policy più precise: non basta dire che SSH o FTP sono consentiti, bisogna anche stabilire **verso quali host**, **da quali zone** e **in quale direzione**.

Questo vale anche per i flussi interni, ad esempio tra LAN e DMZ. La presenza di insider, dispositivi non autorizzati o malware portati dentro la rete rende necessario filtrare e spesso loggare anche traffico che non proviene direttamente da Internet.

> 📌 In questo modo il firewall diventa il **fulcro della sicurezza perimetrale**, garantendo una separazione chiara tra interno, esterno e zona intermedia.

---

### **11. Origini storiche dei firewall**

I primi firewall apparvero alla fine degli anni ’80:

- **1989 – Unix-based gateway** (Mogul)
    
- **1992 – ULTRIX e proxy gateway** (Ranum)
    

Questi sistemi agivano come **gatekeeper**: ricevevano richieste dall’interno e le inoltravano verso l’esterno, filtrando il traffico in base a regole statiche.

![](imgs/Pasted%20image%2020260709142017.png)

Nei primi schemi architetturali comparivano spesso più componenti: un **packet screen** dedicato al controllo dei pacchetti in transito e un **gatekeeper** incaricato di raccogliere, filtrare e inoltrare richieste applicative lecite.

---

### **12. Tipi di firewall (livelli di implementazione)**

Esistono diverse modalità di realizzazione dei firewall, che si distinguono per **complessità e profondità di analisi**:

|Categoria|Descrizione|
|---|---|
|**Static packet filtering**|Analizza i pacchetti in base a regole fisse (indirizzi IP, porte, protocolli).|
|**Dynamic packet filtering (stateful)**|Tiene traccia dello stato delle connessioni e consente solo i pacchetti appartenenti a sessioni legittime.|
|**Application gateway (proxy)**|Intermedia le comunicazioni applicative (es. HTTP, FTP) controllando il contenuto dei messaggi.|
|**Circuit-level gateway**|Gestisce la creazione dei canali di comunicazione, garantendo che solo le sessioni autorizzate vengano mantenute.|

La distinzione centrale è tra firewall che filtrano semplicemente il traffico in transito e firewall che mantengono **stato**. Un firewall stateless valuta ogni pacchetto isolatamente; un firewall stateful ricorda il traffico precedente e può verificare se un pacchetto appartiene a una connessione già autorizzata.

I **proxy** sono ancora diversi: non si limitano a osservare il traffico che passa, ma si interpongono nella comunicazione come entità di rete esplicita. In questo modo possono separare logicamente mittente e destinatario e fare controlli più profondi a livello applicativo o di circuito.

---

### **13. Conclusione**

Il firewall è uno **strumento essenziale ma non sufficiente**:

- deve essere **ben progettato e configurato**,
    
- aggiornato costantemente,
    
- e integrato in un sistema di sicurezza più ampio che includa monitoraggio, patch management e segmentazione di rete.
    

> 📌 La vera forza del firewall non risiede solo nel bloccare pacchetti, ma nel **definire chiaramente i confini della fiducia** all’interno di una rete.
