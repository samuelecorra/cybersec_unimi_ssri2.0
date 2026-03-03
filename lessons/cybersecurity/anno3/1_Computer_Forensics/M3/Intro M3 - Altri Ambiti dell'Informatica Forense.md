Dopo aver approfondito l’analisi dei dispositivi di memorizzazione e le tecniche di acquisizione su disco, questo modulo estende il campo d’indagine verso **nuovi ambiti della digital forensics**: la **Network Forensics**, la **Mobile Forensics** e la **Embedded Forensics**.

L’obiettivo è comprendere come i principi metodologici della disciplina — integrità, tracciabilità, ripetibilità e documentazione — si applichino anche in contesti dove la prova digitale è **volatile, distribuita o integrata in sistemi complessi**.

Si studieranno strumenti e procedure per:

- intercettare e analizzare **flussi di rete** e pacchetti in tempo reale;
    
- estrarre e interpretare dati da **dispositivi mobili**;
    
- investigare su **sistemi embedded** e apparati IoT, dove il confine tra hardware e software si fonde.
    

> Questo modulo rappresenta l’evoluzione naturale del percorso forense: dal singolo disco fisso al cyberspazio connesso, dove ogni pacchetto, log o firmware può diventare una prova.

---

# **UD1 – Network Forensics**

### **Introduzione**

La **Network Forensics** è il ramo dell’informatica forense dedicato all’**acquisizione, registrazione e analisi dei dati che transitano in rete**.  
In questa unità si apprenderanno le modalità operative per **catturare pacchetti, monitorare connessioni e ricostruire le comunicazioni digitali**, nel pieno rispetto dei principi di integrità e tracciabilità della prova.

L’analisi del traffico di rete è fondamentale in numerosi contesti investigativi: dal tracciamento di attacchi informatici al rilevamento di esfiltrazioni di dati, fino alla ricostruzione di cronologie di accesso e attività online.  
Tuttavia, la rete introduce una grande **criticità probatoria**: i dati sono **volatili, dinamici e spesso criptati**, quindi devono essere acquisiti con strumenti e procedure altamente controllate.

> La Network Forensics insegna a “leggere il flusso invisibile” della rete, trasformando pacchetti effimeri in **evidenze tecnicamente solide e giuridicamente valide**.


---

## **Lezione 1: Elementi metodologici di Network Forensics**

### **1. Introduzione alla Network Forensics**

La **Network Forensics** è una branca dell’informatica forense che si occupa di **identificare, preservare e analizzare i dati informatici in transito su una rete**.  
A differenza della **Disk Forensics**, che agisce dopo che un evento si è già verificato (_analisi post mortem_), la Network Forensics opera **mentre l’evento è in corso**, rappresentando così uno strumento essenziale di **incident response**.

Il suo obiettivo è **monitorare e analizzare il traffico di rete** proveniente o diretto verso uno o più sistemi, per individuare anomalie, intrusioni o comportamenti illeciti.  
A causa della natura **volatile e dinamica dei dati di rete**, il rischio di perdita, alterazione o distruzione del reperto è estremamente elevato: ciò impone **procedure rigorose e strumenti affidabili**.

---

### **2. Glossario essenziale di Network Forensics**

- **Rete di computer** → insieme di sistemi collegati che scambiano dati reciprocamente.
    
- **Rete locale (LAN)** → rete ad alta velocità che copre un’area geografica limitata (es. ufficio, laboratorio).
    
- **Rete geografica (WAN)** → rete a bassa velocità ma ampia copertura (es. Internet).
    
- **Server** → computer che fornisce servizi agli altri nodi della rete.
    
- **Client** → computer che utilizza i servizi offerti dai server.
    

---

### **3. Scopi della Network Forensics**

La Network Forensics si fonda su due tipi di attività complementari:

- **Attività di monitoraggio:** osservazione e cattura del traffico di rete in tempo reale.
    
- **Attività di analisi:** studio approfondito dei dati catturati per individuare prove o comportamenti sospetti.
    

Questa disciplina si rivela cruciale in tutti i casi in cui i **dati di interesse non risiedono su un singolo supporto di memorizzazione**, ma **si spostano attraverso la rete** durante l’interazione tra sistemi.

---

### **4. Ambiti di applicazione**

La Network Forensics trova impiego in un’ampia gamma di contesti investigativi, tra cui:

- **Accessi abusivi** a sistemi informatici (_art. 615-ter c.p._);
    
- **Ingiurie, diffamazioni e minacce** via Internet o social network;
    
- **Frodi telematiche**, come phishing e truffe via e-mail;
    
- **Danneggiamenti informatici** e sottrazione di dati sensibili;
    
- **Pedopornografia online** e **violazione del diritto d’autore**;
    
- **Analisi di malware** e di comunicazioni sospette tra host remoti.
    

> Poiché oggi quasi tutti i sistemi informatici sono connessi, la prova digitale **non si trova più solo sul disco**, ma **nel flusso di comunicazione tra sistemi**.

---

### **5. Criticità della Network Forensics**

Le principali difficoltà derivano da due fattori:

1. **Complessità delle infrastrutture di rete:** l’enorme numero di dispositivi interconnessi rende difficile isolare e attribuire i flussi di dati a soggetti specifici.
    
2. **Accuratezza e integrità delle informazioni:** i dati in rete sono estremamente **volatili** e possono essere alterati o persi in pochi secondi se non catturati correttamente.
    

> L’analista forense deve perciò agire rapidamente, garantendo che ogni acquisizione sia **autentica, completa e legalmente valida**.

---

### **6. Strumenti e attività operative**

Le attività principali nella Network Forensics includono:

- **Analisi dei file di log** di più sistemi coinvolti;
    
- **Ricerca e utilizzo di sniffer**, strumenti in grado di intercettare pacchetti di rete;
    
- **Verifica della presenza di programmi di controllo remoto** (remote control);
    
- **Identificazione di condivisioni non autorizzate** o software di comunicazione sospetti;
    
- **Controllo dei file di sistema e delle password**, inclusa la ricerca di nuovi utenti;
    
- **Esame delle configurazioni di rete** e dei file anomali.
    

Queste operazioni permettono di **ricostruire il comportamento della rete**, individuare intrusioni e collegare tracce digitali a identità o attività umane.

---

### **7. Profili di legittimità**

Una parte significativa delle attività di Network Forensics si configura come **intercettazione di comunicazioni elettroniche**.  
Di conseguenza, è fondamentale distinguere **quando e da chi tali operazioni siano lecite**.

**Esempio emblematico:**  
un amministratore di sistema, in un contesto multi-utente, intercetta il traffico di rete fra il server e altri sistemi per fini di analisi o sicurezza.  
È lecito? È utilizzabile in giudizio?

- Se l’attività è svolta **senza autorizzazione** e intercetta comunicazioni personali, può integrare una **violazione della privacy o un reato di intercettazione abusiva**.
    
- Se invece è condotta **su incarico dell’autorità giudiziaria** o nell’ambito di una **consulenza tecnica forense**, diventa legittima e probatoriamente utilizzabile.
    

> Ogni attività di cattura del traffico di rete deve quindi essere **autorizzata, tracciata e documentata**, nel rispetto delle norme su **intercettazioni e trattamento dei dati personali**.

---

### **8. Sintesi finale**

|Aspetto|Disk Forensics|Network Forensics|
|---|---|---|
|**Oggetto d’analisi**|Dati memorizzati|Dati in transito|
|**Momento dell’intervento**|Post-evento (post mortem)|Durante l’evento (live)|
|**Rischio principale**|Alterazione del disco|Volatilità del dato|
|**Obiettivo**|Recupero e analisi dei file|Ricostruzione dei flussi di comunicazione|
|**Esempi tipici**|Cancellazione di file, accessi locali|Intrusioni, frodi online, malware di rete|

---

### **9. Conclusioni**

La Network Forensics rappresenta la **frontiera più dinamica e complessa** dell’informatica forense.  
Richiede competenze tecniche, giuridiche e metodologiche integrate per:

- catturare dati volatili in tempo reale;
    
- analizzarli nel rispetto della normativa;
    
- e trasformarli in **evidenze digitali giuridicamente spendibili**.
    

> In un mondo sempre più interconnesso, il confine tra il dato statico e quello in transito si assottiglia: oggi, la vera sfida dell’analista forense è **imparare a leggere la rete come se fosse un disco vivo**.


---

## **Lezione 2: Aspetti operativi di acquisizione e analisi nell’ambito della Network Forensics**

### **1. Introduzione**

La **Network Forensics**, come abbiamo visto, si occupa della **raccolta e analisi dei dati trasmessi in rete** al fine di individuare l’origine, la natura e l’autenticità delle comunicazioni digitali.  
Questa lezione approfondisce gli **aspetti pratici e metodologici** dell’acquisizione, soffermandosi in particolare sul caso emblematico dell’**acquisizione forense di una pagina web**, una delle operazioni più comuni ma anche più delicate nella pratica giudiziaria.

> L’obiettivo è garantire che l’attività di raccolta e conservazione dei dati di rete sia **ripetibile, verificabile e immune da contestazioni**, affinché la prova digitale acquisita sia **valida in sede processuale**.

---

### **2. Individuare l’origine del traffico di rete**

In un’indagine di Network Forensics, il primo passo consiste nel **determinare l’origine dei dati o delle comunicazioni sospette**.  
Ogni sistema connesso a una rete possiede un **indirizzo IP**, che rappresenta la sua “impronta digitale” online. Tuttavia, l’attribuzione di un’azione a un determinato IP non è sempre immediata:

- **Gli Internet Service Provider (ISP)** assegnano gli indirizzi IP dinamicamente, cioè a **utenti diversi in momenti diversi**;
    
- alcuni sistemi vengono usati come **“ponti” o proxy** per mascherare l’origine effettiva delle connessioni;
    
- altre volte si impiegano tecniche di **NAT (Network Address Translation)** o **VPN**, che rendono più complesso risalire al dispositivo fisico responsabile dell’azione.
    

> È quindi fondamentale **correlare l’indirizzo IP al momento preciso dell’attività**, incrociando i **log degli ISP** con i **file di registro dei sistemi** coinvolti.

---

### **3. Correlazione tra Disk Forensics e Network Forensics**

Le due discipline non sono alternative, ma **profondamente interconnesse**:

- La **Network Forensics** serve a **convalidare l’autenticità dei reperti** acquisiti durante un’analisi su disco, verificando la coerenza tra file locali e flussi di rete.
    
- La **Disk Forensics**, viceversa, fornisce i **dati necessari per contestualizzare il traffico di rete**, come indirizzi IP memorizzati, cookie, cronologie di browser, cache DNS o file di configurazione.
    

Insieme, costituiscono un **circuito integrato di prova**: il dato statico (sul disco) e il dato dinamico (in rete) si rafforzano a vicenda nella ricostruzione dei fatti.

---

### **4. Acquisizione forense di una pagina web**

Uno dei casi più ricorrenti nella pratica forense è la **copia certificata di una pagina web**, utilizzata per provare l’esistenza di un contenuto online (es. post diffamatori, minacce, frodi, ecc.) in un preciso momento.

L’obiettivo è produrre una **copia digitale identica all’originale**, dotata di:

- **verificabilità**,
    
- **riproducibilità**,
    
- **inalterabilità**,
    
- e **data certa**.
    

> Questa procedura deve permettere a qualsiasi controparte di **verificare la genuinità della prova**, riducendo il rischio di disconoscimento in sede giudiziaria.

---

### **5. Procedura di acquisizione di una pagina web**

L’acquisizione deve seguire una sequenza **rigorosa e documentata** di passaggi:

1. **Utilizzo di una macchina virtuale** (VM) con sistema operativo forense Linux, predisposta esclusivamente per l’attività di acquisizione.
    
2. **Avvio della registrazione audio e video** dell’intera procedura, così da documentare visivamente ogni azione, anche a beneficio di un giudice o di un perito non tecnico.
    
3. **Attivazione di una cattura del traffico di rete** (tramite tool come Wireshark o tcpdump) per registrare:
    
    - i file scaricati dal browser,
        
    - le richieste e risposte HTTP/HTTPS,
        
    - gli indirizzi IP dei server coinvolti.  
        Questo step consente di provare **la provenienza reale del contenuto**.
        
4. **Consultazione di un sito attendibile** (es. la homepage di un quotidiano) per **marcare l’inizio dell’acquisizione**, certificando la data e l’ora.
    
5. **Navigazione manuale o automatica** del sito da acquisire, visualizzando tutte le pagine e i contenuti pertinenti.
    
6. **Nuova consultazione di un sito esterno** (ad esempio lo stesso quotidiano) per **confermare la fine dell’acquisizione**, attestando il momento di chiusura.
    
7. **Interruzione delle registrazioni**, sia di rete che audio-video.
    
8. **Spegnimento della macchina virtuale**, che verrà poi archiviata come parte del reperto.
    
9. **Creazione di un archivio compresso** contenente:
    
    - i file della VM,
        
    - la registrazione audio-video,
        
    - il dump del traffico di rete,
        
    - i file HTML e multimediali della pagina acquisita.
        
10. **Calcolo dell’hash** dell’intero archivio (es. SHA256) per certificare l’integrità.
    
11. **Apposizione della marca temporale** sull’hash, per garantire **data certa** e **non ripudiabilità**.
    

> Questa metodologia, nota come _forensic web capture_, permette di acquisire un contenuto online in modo **probatoriamente solido**, senza dipendere da strumenti non verificabili (come screenshot o PDF generici).

---

### **6. Fonti di riferimento storico**

In alcuni casi è necessario acquisire **versioni precedenti** di una pagina web.  
Le principali fonti utilizzabili sono:

- **Google Cache** → conserva copie temporanee di pagine web indicizzate.
    
- **Wayback Machine (Internet Archive)** → archivio storico pubblico che permette di recuperare versioni precedenti di un sito.
    

> Tuttavia, queste fonti **non sostituiscono un’acquisizione forense diretta**: forniscono solo un ausilio investigativo preliminare, non sempre sufficiente ai fini processuali.

---

### **7. Esempio pratico: correlazione probatoria**

Durante un’indagine per diffamazione online, un perito forense può:

- acquisire la pagina web incriminata secondo il metodo sopra descritto;
    
- documentare gli IP coinvolti nel traffico HTTP;
    
- confrontare tali IP con i **log del provider** per risalire al dispositivo autore della pubblicazione;
    
- verificare la coerenza tra data di pubblicazione, orari di accesso e metadati dei file.
    

Il tutto con una **tracciabilità completa** della catena di custodia, dall’acquisizione alla presentazione in giudizio.

---

### **8. Sintesi conclusiva**

|Aspetto|Obiettivo|Strumento/Procedura|
|---|---|---|
|**Individuazione origine**|Identificare sistema sorgente|Analisi IP, log ISP|
|**Correlazione forense**|Integrare dati disco e rete|Disk + Network Forensics|
|**Acquisizione web**|Copia forense ripetibile|VM Linux, registrazioni, hash|
|**Verifica temporale**|Garantire data certa|Marca temporale|
|**Fonti storiche**|Versioni passate dei contenuti|Google Cache, Wayback Machine|

---

### **9. Conclusioni**

La **Network Forensics applicata al web** consente di acquisire e preservare contenuti digitali **volatili e in continua evoluzione**, come post, siti, pagine e comunicazioni online.  
Il valore probatorio di tali contenuti dipende **non dal dato in sé**, ma dal **metodo con cui è stato acquisito**.

> Una pagina web acquisita forensicamente non è solo una copia di un sito:  
> è un **reperto digitale certificato**, accompagnato da hash, marca temporale e tracciabilità completa — elementi che ne fanno una **prova inconfutabile** in sede giudiziaria.


---

## **Lezione 3: Cloud Computing e Informatica Forense**

### **1. Introduzione**

Il **cloud computing** rappresenta oggi uno dei contesti più complessi e affascinanti per l’informatica forense.  
Si tratta di una tecnologia che consente di **usufruire di risorse hardware e software** (come archiviazione, applicazioni o potenza di calcolo) tramite **server remoti** gestiti da un **Cloud Service Provider (CSP)**.  
L’utente, in genere, **accede a tali risorse come servizio**, spesso in abbonamento, senza possedere fisicamente l’infrastruttura.

Il cloud modifica radicalmente l’approccio all’investigazione digitale, poiché **i dati non risiedono più su dispositivi locali**, ma su **server distribuiti**, spesso situati in **Paesi diversi e sotto differenti giurisdizioni**.

---

### **2. Definizione formale (NIST)**

Il **National Institute of Standards and Technology (NIST)** definisce il cloud computing come un modello che consente un **accesso on-demand e ubiquo** a risorse informatiche configurabili (reti, server, storage, applicazioni, servizi), che possono essere rapidamente fornite e rilasciate con **intervento minimo del provider**.

Il NIST distingue **tre principali modelli di servizio**:

---

### **3. Modelli di servizio**

#### **a. SaaS – Software as a Service**

L’utente finale “affitta” una **piattaforma software completa** (come suite di produttività, CRM (**Customer Relationship Management**), o sistemi di vendita) gestita interamente dal provider.  
Il vantaggio principale è la semplicità: l’organizzazione può concentrarsi sul proprio core business senza gestire infrastrutture.

Tuttavia, l’utente:

- **non ha controllo** sui formati dei dati,
    
- **non gestisce** le misure di sicurezza o i backup,
    
- **dipende interamente** dal fornitore in caso di guasto o perdita di dati.


> Esempi: Google Workspace, Microsoft 365, Salesforce, la suita Adobe...

---

#### **b. PaaS – Platform as a Service**

Il provider fornisce una **piattaforma di sviluppo** che permette di creare applicazioni da zero, utilizzando risorse hardware e software sottostanti (es. database, API, ambienti runtime).

- Il **cliente sviluppa e gestisce il codice**, mentre il provider si occupa dell’infrastruttura.
    
- L’analisi forense deve quindi concentrarsi sugli **errori di configurazione, bug o exploit applicativi**.
    
- I **log applicativi** e le **API esposte** dal provider diventano fondamentali per l’indagine.
    

> Esempi: Google App Engine, AWS Elastic Beanstalk, Microsoft Azure App Service.

---

#### **c. IaaS – Infrastructure as a Service**

Il provider mette a disposizione **infrastrutture virtuali complete** (macchine virtuali, storage, reti) su cui l’utente ha **pieno controllo amministrativo**.  
Il cliente sceglie sistemi operativi, linguaggi e strumenti, ma è responsabile della **gestione logica e della sicurezza interna**.

Il provider, invece, garantisce:

- la **sicurezza fisica** dei data center,
    
- la **disponibilità del servizio**,
    
- la **connettività**.
    

> Esempi: Amazon EC2, Microsoft Azure VM, Google Compute Engine.

---

### **4. Modelli di distribuzione**

#### **a. Cloud pubblico**

Gestito interamente da un provider esterno, che offre risorse in rete (VM, storage, applicazioni).

**Vantaggi:**

- Costi ridotti e nessun investimento iniziale.
    
- Nessuna gestione fisica o manutenzione.
    
- Elevata efficienza e scalabilità.


**Svantaggi:**

- Maggiori preoccupazioni per la **riservatezza dei dati**.
    
- Rischio di **dipendenza dal provider** (lock-in).
    
- Possibile **chiusura o cambio di policy** del servizio.


---

#### **b. Cloud privato**

Costruito e gestito **direttamente dall’organizzazione** su server di proprietà.

**Vantaggi:**

- Maggiore riservatezza e controllo.
    
- Scalabilità e personalizzazione totale.
    

**Svantaggi:**

- Alti costi iniziali.
    
- Gestione complessa: personale IT, energia, sicurezza, backup.
    

> È la scelta tipica di enti pubblici, istituzioni sanitarie o realtà che trattano dati sensibili.

---

#### **c. Cloud ibrido**

Soluzione intermedia che integra risorse interne e cloud pubblico.  
È ideale per aziende che vogliono migrare gradualmente al cloud o mantenere dati sensibili localmente.

**Vantaggi:**

- Flessibilità e bilanciamento dei carichi di lavoro.
    
- Possibilità di gestire picchi di traffico o esigenze temporanee.
    

**Criticità:**

- Rischio di **perdita di controllo** su parte dei dati.
    
- Necessità di **rispetto delle normative sulla geolocalizzazione dei dati**.
    
- Difficoltà nel verificare le **policy del provider** (es. data wiping o replicazione).
    

---

### **5. Problematiche forensi nel cloud**

Le indagini forensi nel cloud sono estremamente complesse per una serie di motivi tecnici e giuridici:

- **Difficoltà di accesso ai dati grezzi**, poiché spesso non è possibile identificare i supporti fisici in un data center remoto.
    
- **Crittografia e distribuzione geografica** dei dati, che possono trovarsi sotto giurisdizioni diverse.
    
- **“Matrioske” di servizi cloud**: provider che si appoggiano ad altri provider, complicando ulteriormente la catena di responsabilità.
    
- **Necessità di cooperazione del Cloud Service Provider (CSP)** per ottenere copie certificate di log, file o macchine virtuali.
    

> In genere, l’investigatore non può accedere direttamente ai supporti, ma deve basarsi sui **dati forniti dal provider stesso**, nel rispetto dei principi di **pertinenza, affidabilità e completezza**.

---

### **6. Strumenti per la forensics nel cloud**

|Strumento|Cloud supportato|File System Windows|File System macOS|
|---|---|---|---|
|**Carbonite**|Backup cloud|✅|✅|
|**Dropbox**|Storage cloud|✅|✅|
|**Dropbox Decryption**|Decifratura contenuti|✅|❌|
|**Flickr**|Repository immagini|✅|✅|
|**Google Docs / Drive**|Documenti e storage|✅|✅|
|**SkyDrive (OneDrive)**|Cloud Microsoft|✅|❌|

Tra i tool più noti troviamo **Magnet Internet Evidence Finder**, capace di estrarre artefatti digitali da servizi cloud e sincronizzazioni locali.

---

### **7. Opportunità e benefici del cloud per la forensics**

Sebbene presenti numerose sfide, il cloud può anche **potenziare l’informatica forense**:

- Possibilità di **archiviare e analizzare grandi quantità di reperti** in ambienti scalabili.
    
- Esecuzione **distribuita e parallela** delle analisi (frammentazione e ricomposizione dei risultati).
    
- Adozione di **tool forensi open source**, più trasparenti e replicabili.
    
- **Acquisto temporaneo di VM** dedicate alle indagini (“forensic cloud instances”).
    
- Accesso remoto e sicuro ai risultati dell’analisi da qualsiasi dispositivo.
    

> Il cloud, se correttamente configurato, può diventare **una risorsa per la forensics**, non solo un ostacolo: amplifica la capacità computazionale e favorisce la collaborazione tra team investigativi.

---

### **8. Considerazioni conclusive**

L’analisi forense nel cloud richiede una **nuova mentalità investigativa**, fondata su:

- cooperazione con i provider;
    
- attenzione ai confini giuridici internazionali;
    
- gestione della crittografia e delle copie distribuite;
    
- uso di strumenti certificati e procedure documentate.
    

> Il cloud “non dimentica mai”, ma **ricordare non basta**: serve metodo, rigore e controllo.  
> Solo così l’analista forense può trasformare un’infrastruttura remota e complessa in una **fonte di prova attendibile e verificabile**.


---

## **Lezione 4: Le intercettazioni telematiche**

### **1. Introduzione**

Le **intercettazioni telematiche** rappresentano una delle attività più delicate della **Network Forensics**, poiché comportano la **cattura diretta del traffico dati** che transita tra due o più sistemi informatici.  
Sono disciplinate dall’**art. 266-bis del Codice di Procedura Penale**, che consente di leggere e registrare il contenuto delle comunicazioni informatiche o telematiche **mediante qualunque tipo di collegamento**, sia esso **via cavo** che **wireless**.

> Le intercettazioni non servono solo per l’analisi del traffico, ma anche come **strumento probatorio** e **tecnico di indagine** nei casi di criminalità informatica, frodi online e accessi abusivi.

---

### **2. Tipologie di intercettazioni**

Esistono principalmente **due modalità operative**, che si distinguono per il livello della **pila protocollare** in cui viene applicato il filtro.

#### **a. Intercettazioni a bersaglio**

- Catturano **tutto il traffico** relativo a uno o più sistemi **precisamente identificati**.
    
- Operano ai **livelli più bassi** della pila (livello fisico o datalink).
    
- L’obiettivo è monitorare in modo puntuale e completo tutte le comunicazioni di un host specifico.
    

Esempio: intercettazione del traffico di rete di un determinato server sospettato di ospitare contenuti illeciti.

#### **b. Intercettazioni parametriche**

- Riguardano il traffico relativo a **una o più applicazioni specifiche**, indipendentemente dai sistemi coinvolti.
    
- Agiscono ai **livelli alti** della pila (livello applicativo o di sessione).
    
- Si basano su **pattern predefiniti** (es. protocolli, porte, stringhe o tipi di contenuto).
    

Esempio: monitoraggio di tutto il traffico di posta elettronica SMTP o di messaggistica istantanea.

> La distinzione fra “bersaglio” e “parametrica” riflette il **punto di osservazione** scelto lungo la pila di protocolli: quanto più si sale di livello, tanto più il controllo diventa “semantico” ma anche selettivo.

---

### **3. Modalità operative delle intercettazioni**

L’intercettazione si attua mediante l’uso di una **sonda di rete (wiretap)** installata **nel punto più vicino possibile al bersaglio**, per ridurre la quantità di traffico non rilevante.

Normalmente, le sonde vengono posizionate:

- **nei punti di aggregazione del traffico**, come router, switch o gateway;
    
- **nelle dorsali di rete** o **nodi ISP** dove transitano più flussi simultanei;
    
- in alcuni casi, **direttamente sull’interfaccia di rete** del sistema monitorato.

---

### **4. Finalità dell’intercettazione**

Le intercettazioni possono avere scopi **tecnici, investigativi o probatori**, a seconda del contesto in cui vengono eseguite.

#### **a. Analisi delle prestazioni**

Serve a valutare lo stato di salute della rete, identificare **colli di bottiglia** e **punti di congestione**.

#### **b. Monitoraggio di sicurezza**

Utilizzata per **rilevare intrusioni, malware o spyware** in tempo reale.

#### **c. Incident response e Network Forensics**

Impiegata per **ricostruire gli eventi** in seguito a un incidente informatico, **documentando il traffico generato** e garantendone la **valenza probatoria**.

> In ambito forense, la finalità principale è **la raccolta di dati di rete con valore legale**, preservandone l’autenticità e la completezza.

---

### **5. Meccanismo tecnico di cattura**

In condizioni normali, una **scheda di rete** riceve soltanto i pacchetti **destinati al proprio indirizzo MAC**.  
Per intercettare tutto il traffico circostante, è necessario attivare la **modalità promiscua**:

```bash
ifconfig eth0 promisc
```

In questa modalità:

- la scheda cattura **tutti i pacchetti** che transitano sul canale,
    
- indipendentemente dal destinatario reale,
    
- consentendo all’analista di osservare l’intero flusso di comunicazioni.
    

---

### **6. Problemi tecnici delle intercettazioni**

Le intercettazioni telematiche presentano diverse **criticità tecniche e operative**:

- **Difficoltà di implementazione:** richiedono infrastrutture costose e personale altamente specializzato.
    
- **Limitata efficacia in presenza di cifratura:** le comunicazioni HTTPS, TLS, VPN o cifrate end-to-end non sono leggibili senza la chiave privata.
    
- **Gestione del filtro:** definire pattern troppo ampi genera **rumore (false positive)**, mentre filtri troppo restrittivi causano **perdita di dati rilevanti**.
    
- **Risorse hardware elevate:** la cattura in tempo reale richiede potenza di calcolo e larghezza di banda considerevoli.
    

---

### **7. Vantaggi delle intercettazioni**

Nonostante le difficoltà, le intercettazioni telematiche restano uno **strumento insostituibile** in vari scenari:

- Possono essere **l’unico modo** per ottenere informazioni **senza accedere fisicamente** al luogo in cui si trova il sistema monitorato.
    
- Consentono di **localizzare il sistema bersaglio**, tracciandone l’origine e la destinazione del traffico.
    
- Permettono **analisi immediate** in situazioni di emergenza (attacchi in corso, esfiltrazioni, sabotaggi).
    

> Le intercettazioni, se correttamente condotte, consentono di “fotografare” in tempo reale ciò che accade nella rete, fornendo **prove dirette e cronologicamente certe**.

---

### **8. Modalità operative: live e differita**

Esistono due principali strategie di lavoro:

#### **a. Ricerca live**

- Vengono catturati **solo i pacchetti che corrispondono ai pattern di filtro**.
    
- Richiede elevata **potenza computazionale** per non perdere pacchetti in tempo reale.
    
- Riduce lo spazio di archiviazione, ma aumenta il rischio di **perdita di dati marginali**.
    

#### **b. Memorizzazione e ricerca differita**

- Tutto il traffico viene **registrato integralmente**.
    
- I filtri vengono applicati **successivamente** durante l’analisi offline.
    
- Richiede **molta memoria di archiviazione**, ma garantisce **maggiore completezza**.
    

> La scelta dipende dal contesto operativo: in attività giudiziarie si preferisce quasi sempre la **memorizzazione integrale**, per assicurare la tracciabilità completa del flusso dati.

---

### **9. Aspetti giuridici**

Le intercettazioni telematiche, come tutte le forme di captazione di comunicazioni, **richiedono un’autorizzazione formale dell’Autorità Giudiziaria (A.G.)**.  
Senza tale autorizzazione — o senza il consenso del titolare della rete — l’attività costituisce **reato di intercettazione abusiva**.

#### **Normativa di riferimento**

- **Art. 266-bis c.p.p.** → disciplina le intercettazioni di comunicazioni informatiche o telematiche.
    
- **Art. 617-quater c.p.** → punisce chi intercetta abusivamente comunicazioni informatiche o telematiche.
    
- **GDPR e Codice Privacy (D.Lgs. 196/2003)** → tutelano la riservatezza dei dati personali durante le attività di trattamento e analisi.
    

> Ogni intercettazione deve essere **motivata, proporzionata e circoscritta**, con una chiara **catena di custodia** dei dati raccolti.

---

### **10. Sintesi finale**

|Aspetto|Descrizione sintetica|
|---|---|
|**Tipologie**|A bersaglio (livelli bassi), Parametriche (livelli alti)|
|**Meccanismo**|Sonda wiretap, modalità promiscua|
|**Problemi**|Costi, cifratura, filtro errato, perdita dati|
|**Vantaggi**|Unico metodo remoto, localizzazione, rapidità|
|**Modalità**|Live (in tempo reale) o differita (registrazione completa)|
|**Legalità**|Richiede autorizzazione dell’A.G., altrimenti è reato|

---

### **11. Conclusioni**

Le intercettazioni telematiche costituiscono una **frontiera avanzata** della Network Forensics, dove la tecnologia incontra il diritto penale e la tutela della privacy.  
Sono strumenti potenti ma intrinsecamente rischiosi, da utilizzare **solo in modo legittimo e controllato**.

> L’analista forense deve padroneggiare non solo la tecnica di cattura, ma anche il **contesto normativo**, perché in questo ambito **la competenza giuridica è parte della prova tecnica stessa**.

---

Perfetto 📱  
Ecco la **breve introduzione discorsiva** alla **UD2 – Mobile Forensics**, coerente con il tono e lo stile delle unità precedenti, in **Markdown Obsidian-ready** con **headings in grassetto**.

---

# **UD2 – Mobile Forensics**

### **Introduzione**

La **Mobile Forensics** è la branca dell’informatica forense dedicata all’**acquisizione, conservazione e analisi dei dati presenti sui dispositivi mobili** — smartphone, tablet, SIM card e memorie esterne.  
In un’epoca in cui gran parte della vita personale e professionale si svolge attraverso lo smartphone, questi dispositivi rappresentano **una delle fonti probatorie più ricche e sensibili**.

L’analista forense deve saper **estrarre informazioni** come chiamate, SMS, chat, immagini, cronologia web, geolocalizzazioni e dati applicativi, **senza alterare** il contenuto originale.  
La sfida è doppia: da un lato la **protezione dei dati cifrati e sincronizzati nel cloud**, dall’altro la **continua evoluzione tecnologica** dei sistemi operativi mobili.

> La Mobile Forensics insegna a “dialogare” con i dispositivi digitali personali nel pieno rispetto della loro integrità, trasformando un semplice smartphone in una **prova tecnica completa e giuridicamente valida**.


---

## **Lezione 1: Elementi di Mobile Forensics**

### **1. Introduzione**

La **Mobile Forensics** è la branca dell’informatica forense che si occupa dell’**identificazione, raccolta, conservazione, analisi e interpretazione** delle prove digitali contenute nei **dispositivi mobili** — come telefoni cellulari, smartphone e tablet.  
Questi dispositivi, oggi, racchiudono una quantità di informazioni personali, professionali e relazionali enorme: comunicazioni, immagini, cronologie, geolocalizzazioni e dati applicativi.  
Analizzarli correttamente significa **ricostruire eventi e comportamenti digitali** con valore legale.

> In ambito forense, lo smartphone è a tutti gli effetti un **sistema informatico completo**, e deve essere trattato con le stesse cautele previste per l’analisi dei computer tradizionali.

---

### **2. Dispositivi mobili vs. computer**

Dal punto di vista tecnico, **non esiste una reale differenza** tra un computer e un dispositivo mobile: entrambi possiedono un sistema operativo, gestiscono memoria interna ed esterna, comunicano in rete e trattano dati digitali.

**Differenze apparenti:**

- **Dimensioni fisiche** e portabilità.
    
- **Interfaccia utente** (touch screen e app integrate).
    
- **Modalità di comunicazione**, sia vocali che testuali.
    

Ma, sotto il profilo forense, il principio è chiaro:

> Uno smartphone è un **elaboratore digitale** dotato di memoria, rete e software: dunque, soggetto alle stesse regole di integrità, preservazione e analisi.

---

### **3. Funzioni principali di un dispositivo mobile**

Il dispositivo mobile svolge una molteplicità di ruoli contemporaneamente. Ognuno di essi genera **fonti di prova digitali** potenzialmente rilevanti.

#### **a. Funzione di comunicazione**

- Chiamate vocali in entrata, uscita e perse.
    
- SMS e MMS inviati, ricevuti, non inviati o in bozza.
    
- Email e messaggistica istantanea (Skype, Telegram, WhatsApp, Signal).
    
- Log di accesso Wi-Fi e rete cellulare.
    
- Dati di rete: IMEI, IMSI, operatore, celle agganciate.
    

#### **b. Funzione di memorizzazione**

- Rubrica telefonica con nomi, numeri, indirizzi, note e gruppi.
    
- Fotografie, video, registrazioni audio.
    
- File di produttività (documenti, PDF, note, app di archiviazione cloud).
    

#### **c. Funzione di agenda**

- Eventi e promemoria con data, ora, allarmi e ricorrenze.
    
- Inviti e appuntamenti sincronizzati con calendari cloud.
    

#### **d. Funzione di navigatore**

- Storico dei luoghi visitati.
    
- Coordinate GPS, indirizzi e mappe visualizzate.
    
- Dati di geolocalizzazione incorporati nei **metadati EXIF** delle immagini.
    
- Localizzazioni provenienti da messaggistica o social network.
    

#### **e. Funzione di web client**

- Cronologia di navigazione.
    
- Storico ricerche, download e preferiti.
    
- Cookie e dati di sessione salvati nel browser.
    

> Ogni funzione lascia **tracce digitali** distribuite in archivi di sistema, cache, database SQLite e file di log, tutti potenzialmente acquisibili con strumenti forensi.

---

### **4. Mobile Forensics: definizione operativa**

Secondo il **Digital Forensic Research Workshop (DFRWS, 2001)**, la Mobile Forensics è:

> _“L’uso di metodi scientificamente validati per l’identificazione, raccolta, conservazione, validazione, analisi, interpretazione, documentazione e presentazione delle prove digitali al fine di favorire la ricostruzione dei fatti.”_

Ciò implica che ogni analisi su un dispositivo mobile deve seguire un **metodo ripetibile, verificabile e documentato**, per garantire la **valenza probatoria** dei risultati.

---

### **5. Modalità di trattamento dei dispositivi mobili**

L’acquisizione dei dati da un dispositivo mobile può avvenire con diverse tecniche, che variano in **profondità** e **grado di invasività**.

#### **a. Acquisizione logica**

- Si effettua tramite collegamento **USB, Bluetooth o rete**.
    
- Utilizza **interfacce messe a disposizione dal produttore** (es. backup o API).
    
- Recupera tutti i dati **visibili all’utente**, come rubrica, SMS, registro chiamate, foto, app.
    
- Può includere anche alcuni **dati cancellati**, a seconda del dispositivo e del software.
    
- È **non distruttiva**, ma **non garantisce completezza**: non accede a tutte le aree di memoria.
    

#### **b. Acquisizione fisica**

- Consiste nell’**estrazione bit-a-bit** o quasi bit-a-bit dell’intero contenuto di memoria.
    
- Si ottiene con **strumenti hardware dedicati** (es. Cellebrite UFED, XRY, Oxygen Forensics).
    
- Permette di recuperare anche **dati cancellati** o **aree non allocate**.
    
- Richiede strumenti in grado di leggere e interpretare i **dati grezzi**.
    
- È la tecnica più completa, ma anche la più complessa e, talvolta, **non replicabile**.
    

#### **c. Acquisizione via agent**

- Alcuni tool installano un **software temporaneo (“agent”)** sul dispositivo.
    
- Questa modalità ibrida consente di accedere a più dati rispetto all’acquisizione logica, ma non raggiunge la profondità di quella fisica.
    
- Può alterare il reperto (introduce codice esterno), quindi **va documentata in modo rigoroso**.
    
- Non consente di estrarre aree riservate del sistema operativo o dump completi della memoria.
    

#### **d. Acquisizione fotografica**

- Utilizzata solo in casi particolari, consiste nel **fotografare lo schermo** o alcune evidenze visibili.
    
- È **una forma di documentazione**, non una vera acquisizione forense.
    
- Può servire come **integrazione visiva** o per dimostrare lo stato del dispositivo.
    

---

### **6. Rintracciabilità dei dati su altri sistemi**

Molti dati di interesse forense possono essere **replicati o sincronizzati** su altri sistemi, il che offre ulteriori possibilità di verifica:

- **Email** → reperibili anche su computer o server remoti.
    
- **Elenco chiamate** → ottenibile dai tabulati telefonici dell’operatore.
    
- **Messaggi** → presenti sui dispositivi degli interlocutori.
    
- **Geolocalizzazioni** → ricavabili anche dai log delle celle telefoniche.
    

> La Mobile Forensics non si limita al singolo dispositivo: si estende all’intero **ecosistema di sincronizzazione** che lo circonda.

---

### **7. Considerazioni conclusive**

|Aspetto|Descrizione sintetica|
|---|---|
|**Natura del dispositivo**|Sistema informatico completo|
|**Dati acquisibili**|Comunicazioni, geolocalizzazioni, immagini, cronologie|
|**Metodi principali**|Logico, fisico, via agent, fotografico|
|**Criticità**|Rischio di alterazione, cifratura, varietà hardware/software|
|**Principio forense**|Ripetibilità, documentazione, integrità|

> L’analisi dei dispositivi mobili è una delle attività più complesse dell’informatica forense, a causa dell’enorme varietà di architetture, sistemi operativi e meccanismi di sicurezza.  
> Tuttavia, se condotta con rigore metodologico, permette di **trasformare un oggetto d’uso quotidiano in una fonte probatoria di valore straordinario**.

---

## **Lezione 2: La valutazione dell’attendibilità dei riscontri**

### **1. Introduzione**

Nell’ambito della **Mobile Forensics**, l’analisi tecnica di un dispositivo non si esaurisce con l’acquisizione dei dati: è fondamentale procedere alla **valutazione dell’attendibilità dei riscontri**.  
Questo significa stabilire se le informazioni estratte (messaggi, registri, file, cronologie) siano **autentiche, coerenti e non manipolate**.

> L’obiettivo è dimostrare che il dato digitale sia **integro**, **attribuibile** a un soggetto e **contestualizzato correttamente** nel tempo e nello spazio.

---

### **2. La fase di “valutazione” del reperto**

Dopo l’acquisizione, l’analista forense passa alla fase di **valutazione** del materiale estratto.  
Questa fase serve a:

- **Verificare la consistenza dei dati**, confrontando orari, metadati e contenuti.
    
- **Escludere la presenza di alterazioni volontarie** o errori di interpretazione.
    
- **Documentare** ogni passaggio di analisi, in modo che la procedura sia **ripetibile** da terzi.
    

Un esempio classico è quello dell’analisi di **messaggi SMS** o di **chat di messaggistica**.  
Per attestare la loro autenticità, l’analista deve verificare che **la data, l’ora e il mittente** coincidano con i log di sistema e con le altre fonti disponibili.

---

### **3. L’esempio operativo: la verifica dei messaggi SMS**

Nel caso mostrato dal prof. Caccavella, si parte da un **messaggio ricevuto alle ore 18:23**, visualizzato sul dispositivo come “non letto”.  
L’analista, nella fase di valutazione, **ricostruisce l’ambiente di analisi**, impostando la **stessa data e ora** del messaggio per **replicare il contesto originario**.

La schermata di lavoro comprende:

- **Data e ora** impostate uguali a quelle del messaggio ricevuto;
    
- **Numero telefonico** dell’interessato;
    
- **Cartella di destinazione** del messaggio (inbox, sent, draft, ecc.);
    
- **Corpo del messaggio** con il testo integrale;
    
- **Stato del messaggio** (letto/non letto, consegnato, inviato).
    

Questa ricostruzione consente di confrontare i dati originali con quelli **eventualmente presenti su altri dispositivi** o nei **tabulati del gestore telefonico**.

---

### **4. Analisi dei dettagli tecnici**

Durante la valutazione, vengono esaminati in dettaglio:

- i **metadati del messaggio** (data di invio, ricezione, stato di consegna);
    
- il **numero del mittente e del destinatario**;
    
- la **cartella di sistema** in cui il messaggio è stato archiviato;
    
- eventuali **discrepanze temporali** tra messaggio e orologio di sistema.
    

Tali elementi servono a verificare se il messaggio:

- sia **autentico e originario**;
    
- sia stato **modificato o ricreato** con strumenti esterni;
    
- o costituisca un **falso elemento probatorio** costruito ad arte.
    

---

### **5. Manipolazioni e falsi digitali**

Il docente sottolinea come sia oggi **tecnicamente possibile ricreare falsi elementi probatori** su un telefono cellulare, analogamente a quanto si può fare su un computer.  
Esistono software e applicazioni capaci di:

- generare messaggi SMS o chat **retrodatati**;
    
- **simulare notifiche** o conversazioni false;
    
- creare **log di chiamate fittizi** o contatti inesistenti.
    

> “Ciò che oggi si può fare, si poteva fare anche allora” – ricorda il prof. Caccavella – a dimostrazione che il problema della falsificazione digitale **non è nuovo**, ma è divenuto più accessibile e diffuso.

---

### **6. Dispositivo mobile come sistema informatico**

È essenziale ricordare che **un telefono cellulare è un sistema informatico a tutti gli effetti**.  
Pertanto, ogni analisi deve tener conto delle **stesse criticità metodologiche e giuridiche** previste per i computer tradizionali:

- rischio di alterazione del dato durante l’acquisizione;
    
- necessità di mantenere la **catena di custodia**;
    
- obbligo di utilizzare **strumenti certificati e validati**;
    
- rispetto del **principio di non modificazione** del reperto originale.
    

---

### **7. Servizi di telefonia e falsi probatori**

Oltre ai dispositivi in sé, anche alcuni **servizi di telefonia** possono essere utilizzati per creare falsi riscontri, ad esempio:

- messaggi inviati da **servizi web anonimi** che simulano mittenti reali;
    
- **email spoofing** con indirizzi apparentemente autentici;
    
- **applicazioni di messaggistica** che permettono di cancellare o modificare messaggi retroattivamente.
    

> L’analista forense deve quindi distinguere tra il **dato reale** e la **rappresentazione apparente del dato**, concentrandosi sull’origine tecnica delle informazioni.

---

### **8. In sintesi**

|Aspetto|Descrizione|
|---|---|
|**Obiettivo**|Verificare autenticità e integrità dei dati mobili|
|**Fase di valutazione**|Confronto tra messaggi, metadati e log|
|**Rischi**|Falsi probatori, retrodatazioni, manipolazioni|
|**Strumenti**|Analisi forense con ricostruzione temporale|
|**Principio**|Lo smartphone è un sistema informatico completo|
|**Attenzione**|Anche i servizi di telefonia possono essere alterati|

---

### **9. Conclusioni**

La **valutazione dell’attendibilità dei riscontri** è una fase critica della Mobile Forensics, perché trasforma il dato grezzo in **prova utilizzabile**.  
Ogni messaggio, chiamata o file deve essere interpretato alla luce del contesto tecnico, temporale e giuridico in cui è stato generato.

> L’analista non deve mai fidarsi del dato “così come appare”, ma verificarne sempre **la genesi, la coerenza e la ripetibilità**.  
> Solo in questo modo la prova digitale potrà reggere al vaglio di un’aula di tribunale.


---

## **Lezione 3: La geolocalizzazione dei dispositivi di telefonia mobile**

### **1. Introduzione**

La **geolocalizzazione forense** consiste nell’individuare la **posizione geografica di un dispositivo mobile** attraverso l’analisi delle **antenne (celle) di rete** a cui esso si collega.  
Questa pratica, denominata _cell site analysis_, è largamente utilizzata nelle indagini penali per **ricostruire gli spostamenti di una persona** o per **verificare la compatibilità tra presenza fisica e eventi delittuosi**.

> L’obiettivo non è fornire una posizione esatta, ma **determinare un’area di compatibilità**: il dispositivo _poteva trovarsi_ in quella zona, non necessariamente _era_ lì con certezza.

---

### **2. Cos’è la Cell Site Analysis**

La **cell site analysis** è l’attività che permette di:

- analizzare le **antenne BTS (Base Transceiver Station)** a cui si connette il dispositivo mobile;
    
- stimare il **raggio di copertura** di ciascuna antenna;
    
- individuare la **compatibilità o incompatibilità** della posizione del dispositivo con i luoghi oggetto dell’indagine.
    

Il principio è semplice: ogni volta che uno smartphone effettua una chiamata, invia un messaggio o accede a Internet, stabilisce un collegamento radio con una determinata **stazione base (cell tower)**.  
Registrando queste connessioni e confrontandole con la **mappa delle celle attive**, è possibile stimare dove si trovava il dispositivo in quel momento.

---

### **3. Struttura della rete GSM**

La **rete GSM (Global System for Mobile Communications)** si compone di più sottosistemi coordinati:

#### **a. Stazione mobile (Mobile Station – MS)**

È il **terminale** usato dall’abbonato (telefono o smartphone) per richiedere servizi di comunicazione.  
Ogni terminale è identificato da un codice univoco chiamato **IMEI (International Mobile Equipment Identity)**.

#### **b. Scheda SIM (Subscriber Identity Module)**

È il chip contenente:

- i dati dell’abbonamento,
    
- le chiavi di autenticazione e cifratura,
    
- l’identificativo univoco **IMSI (International Mobile Subscriber Identity)**.
    

La SIM può essere protetta da PIN e trasferita su altri dispositivi, rendendo fondamentale distinguere **il terminale (IMEI)** dall’**utenza (IMSI)**.

#### **c. Stazione base (Base Station Subsystem – BSS)**

Gestisce la **parte radio** della rete e include:

- **BTS (Base Transceiver Station)** → realizza i canali di comunicazione e fornisce la copertura radio a una specifica **cella**;
    
- **BSC (Base Station Controller)** → controlla più BTS, gestisce le risorse radio e coordina le connessioni verso il **NSS (Network Switching Subsystem)**.
    

#### **d. Sottosistema di rete (Network Switching Subsystem – NSS)**

Include il **Mobile Switching Center (MSC)**, responsabile dell’instradamento delle comunicazioni tra dispositivi mobili e tra reti diverse (mobili o fisse).

#### **e. Sottosistema operativo (OSS – Operation and Support Subsystem)**

Si occupa della **manutenzione e supervisione** della rete, garantendone il corretto funzionamento.

---

### **4. Copertura del territorio e struttura delle celle**

La rete cellulare suddivide il territorio in **celle**, ciascuna servita da una BTS.  
Ogni cella ha:

- un’estensione geografica variabile (da poche decine di metri in città a chilometri in zone rurali);
    
- una **forma irregolare**, determinata da ostacoli fisici, potenza del segnale e frequenze radio.
    

È importante ricordare che:

- in ogni punto del territorio **si possono ricevere più segnali** contemporaneamente;
    
- **collegarsi a un’antenna** non significa trovarsi fisicamente sotto di essa;
    
- la “cella di connessione” indica solo un’**area di compatibilità**, non una posizione certa.
    

> È quindi scorretto — e metodologicamente errato — “geolocalizzare” un individuo entro una piccola area solo sulla base della cella a cui il telefono era collegato.

---

### **5. Il meccanismo dell’handover**

Il **handover** è il processo con cui un dispositivo mobile **passa da una cella all’altra** durante una comunicazione in corso, senza interrompere la connessione.  
Questo fenomeno è cruciale per la ricostruzione dei movimenti di un utente.

#### **Tipologie di handover**

| Tipo di handover             | Causa                                                                    | Come avviene                                                                     | Vantaggi                                           | Problematiche                                                                              |
| ---------------------------- | ------------------------------------------------------------------------ | -------------------------------------------------------------------------------- | -------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| **Handover di salvataggio**  | Deterioramento della qualità del segnale radio (es. spostamento in auto) | Il dispositivo rileva una BTS con segnale più forte e si collega automaticamente | Continuità della comunicazione anche in movimento  | Rischio di disconnessioni se la decisione è troppo rapida o causata da ostacoli temporanei |
| **Handover di confinamento** | Sovraccarico di una BTS in zone densamente popolate                      | Il dispositivo si collega a una BTS meno affollata                               | Migliore qualità e distribuzione delle connessioni | Possibile collegamento a BTS **lontane** dal punto fisico effettivo del dispositivo        |

> Il handover, se correttamente interpretato, consente di **tracciare percorsi e movimenti**, ma può anche indurre in errore se non si considera il contesto radioelettrico.

---

### **6. Analisi di casi reali**

Il prof. Caccavella mostra diversi esempi pratici, tra cui:

- la **linea di handover** tra le BTS di **Via Piave** e **Via Ximenes** (Brindisi);
    
- la ricostruzione delle connessioni tra **Alberona, Biccari e Tertiveri**, dove il dispositivo mostra un **handover di sconfinamento**, cioè un passaggio anomalo verso celle molto distanti.
    

> Tali casi dimostrano che la **copertura teorica** delle celle non corrisponde sempre alla **copertura reale**, poiché il segnale può variare per ostacoli, condizioni atmosferiche o congestione della rete.

---

### **7. Considerazioni tecniche sull’analisi delle celle**

Per ottenere risultati attendibili, la cell site analysis deve essere:

- **corredata da misurazioni sul campo**, effettuate con **misuratori di campo elettromagnetico**;
    
- integrata con dati dei **gestori di rete** (tabulati, log delle BTS, mappe di copertura reali);
    
- supportata da **verifiche logiche** sui tempi e sulle distanze, per evitare interpretazioni arbitrarie.
    

La documentazione del traffico telefonico ha **inizialmente solo valore indiziario**.  
Diventa **prova tecnica** solo quando viene **corroborata da altri elementi**: orari, spostamenti, testimonianze, dati ambientali.

---

### **8. Esempio di localizzazione reale**

Esempio pratico di geolocalizzazione tramite connessioni alle BTS:

- **Via Erbosa 1 – Gatteo (SS16 Adriatica, San Mauro Pascoli)**
    
- **Via Eulero 58 – Cesenatico**
    
- **Via Torricelli – Cesenatico (Romagna Center)**
    

Queste connessioni evidenziano **posizioni approssimative**, calcolate in base alla copertura delle celle, ma non identificano un punto preciso.

---

### **9. Considerazioni finali**

|Aspetto|Descrizione|
|---|---|
|**Scopo della cell site analysis**|Ricostruire movimenti e compatibilità spaziali|
|**Precisione**|Limitata: fornisce aree, non coordinate certe|
|**Handover**|Passaggio automatico tra celle, utile ma ambiguo|
|**Strumenti necessari**|Log operatori, misuratori di campo, mappe radio|
|**Valore probatorio**|Indiziario, da integrare con altri riscontri|
|**Rischio di errore**|Elevato senza verifica sul campo|

> La geolocalizzazione basata sulle celle è **uno strumento investigativo potente ma non infallibile**:  
> deve sempre essere **contestualizzata, verificata e validata** attraverso misurazioni e riscontri esterni.

---

### **10. Conclusioni**

La **geolocalizzazione forense** rappresenta un passaggio cruciale nelle indagini digitali, ma richiede **cautela e metodo**.  
L’analista deve:

- conoscere la struttura tecnica della rete GSM;
    
- interpretare correttamente handover e coperture;
    
- evitare di trarre conclusioni affrettate basate su dati incompleti.
    

> In sintesi: la geolocalizzazione non “prova” la presenza, ma **indica la compatibilità**.  
> È la logica investigativa, supportata dalla tecnica, a trasformare quel dato in **prova utile e contestualizzata**.

---

Perfetto ⚙️  
Ecco la **breve introduzione discorsiva** alla **UD3 – Embedded Forensics**, nello stesso stile rigoroso e chiaro usato per le unità precedenti, in formato **Markdown Obsidian-ready** con **headings in grassetto**.

---

# **UD3 – Embedded Forensics**

### **Introduzione**

La **Embedded Forensics** si occupa dell’**analisi forense dei dispositivi elettronici integrati**, cioè di tutti quei sistemi che racchiudono **hardware, software e memoria non accessibile direttamente**.  
Rientrano in questa categoria router, smart TV, dash cam, dispositivi IoT, centraline automobilistiche, videocamere di sorveglianza, e in generale qualunque apparato “intelligente” con un sistema operativo interno.

L’obiettivo dell’analista è **identificare, acquisire e interpretare i dati digitali** memorizzati in questi sistemi — come log, immagini, cronologie o configurazioni — **senza alterarne il contenuto originario**.  
L’approccio richiede conoscenze interdisciplinari che combinano **elettronica, informatica e tecnica forense**, poiché spesso l’accesso avviene tramite **interfacce hardware dirette** (UART, JTAG, ISP, NAND) o mediante **estrazioni logiche dal firmware**.

> L’Embedded Forensics rappresenta la **frontiera più tecnica e fisica** dell’informatica forense: qui l’investigatore non analizza un computer o uno smartphone, ma il cuore digitale nascosto dentro ogni oggetto connesso.


---

## **Lezione 1: Embedded Forensics**

### **1. Introduzione**

La **Embedded Forensics** si occupa dell’**analisi e acquisizione di dati digitali** provenienti da **sistemi elettronici integrati** — detti _embedded systems_ — cioè apparati nei quali **hardware, firmware e software** coesistono in un unico dispositivo.  
A differenza dei computer tradizionali, questi sistemi non sono progettati per l’interazione diretta con l’utente, ma per **svolgere funzioni specifiche** all’interno di macchine, veicoli o apparecchiature industriali.

Esempi comuni sono:

- centraline elettroniche di veicoli (ECU, ABS, airbag, infotainment);
    
- dispositivi IoT (telecamere, router, smart TV, domotica);
    
- apparecchi biomedicali e strumentazione sanitaria;
    
- controller industriali e PLC (Programmable Logic Controller).
    

> L’obiettivo dell’analista forense è **riconoscere la presenza di memoria digitale** in tali dispositivi, **estrarre i dati rilevanti** e **preservarne l’integrità**, nel pieno rispetto dei principi dell’informatica forense.

---

### **2. Individuazione dei dispositivi embedded**

L’attività di Embedded Forensics inizia sempre con la **fase di individuazione**:  
l’analista deve riconoscere **quali apparati contengano informazioni digitali** potenzialmente utili all’indagine.

#### **Criteri di individuazione**

- Presenza di **memorie non volatili** (EEPROM, Flash, SD, NAND).
    
- Possibilità di **interfacciamento fisico** tramite porte UART, JTAG, ISP o USB.
    
- Funzioni di **registrazione, automazione o controllo** che implicano la memorizzazione di eventi o log.
    
- Presenza di un **sistema operativo embedded** (es. Linux Embedded, RTOS, VxWorks, QNX).
    

L’analista deve inoltre valutare se il dispositivo sia:

- **autonomo**, con memoria interna e log locali;
    
- oppure **interconnesso**, con sincronizzazione dati verso server remoti o cloud industriali.
    

> L’individuazione corretta è fondamentale: in molti casi la “prova digitale nascosta” si trova **non nel computer dell’utente, ma nella macchina che utilizza**.

---

### **3. Metodologia operativa**

Nel trattare un dispositivo embedded, l’analista deve rispettare i **principi fondamentali dell’informatica forense**, adattandoli al contesto tecnico particolare.

#### **Principi metodologici**

1. **Preservare i reperti informatici** durante le fasi di acquisizione.
    
    - Evitare ogni modifica del contenuto originario.
        
    - Se possibile, **effettuare la copia su immagine bit-a-bit** della memoria.
        
2. **Documentare ogni operazione svolta**.
    
    - Annotare strumenti, versioni software, parametri di accesso e orari.
        
3. **Attenersi a procedure standardizzate**.
    
    - Applicare protocolli di settore (ISO/IEC 27037 e 27042).
        
4. **Sperimentare e validare**.
    
    - In molti casi, non esistono procedure codificate per ogni dispositivo: occorre **sperimentare**, testare, e dimostrare la ripetibilità dell’acquisizione.
        

> La sperimentazione, se condotta e documentata correttamente, è **la forma più alta di verifica scientifica** nella forensics embedded.

---

### **4. Esempio: automazione industriale**

Uno dei campi più significativi dell’Embedded Forensics è quello dell’**automazione industriale**.  
In questo settore, i dispositivi embedded controllano macchine, linee di produzione e impianti complessi; l’analisi forense mira a **verificare anomalie, guasti o comportamenti imprevisti** che possano avere conseguenze economiche o legali.

#### **Caso tipico**

Un perito riceve una relazione tecnica che afferma:

> “Il veicolo mostra un guasto fisso sulla componente del _bus dati_, e tale guasto implica funzionamenti anomali e imprevedibili del veicolo anche durante la marcia, rendendolo di conseguenza inidoneo alla circolazione su strada.”

In situazioni simili, l’analista forense deve:

- acquisire il **contenuto della centralina elettronica** (ECU);
    
- analizzare i **codici errore (DTC – Diagnostic Trouble Codes)**;
    
- confrontarli con **dati ambientali o di funzionamento**;
    
- determinare se il guasto sia di natura **hardware, software o manipolativa**.
    

> L’Embedded Forensics automobilistica è oggi uno strumento centrale nei casi di **incidenti stradali, frodi assicurative o sabotaggi elettronici**.

---

### **5. Applicazioni in ambito industriale**

Nel settore dell’automazione, le analisi possono riguardare:

- **PLC e sistemi SCADA**, per verificare sequenze di comandi inviati alle macchine;
    
- **robot industriali** e linee automatizzate, per controllare log di esecuzione o errori;
    
- **impianti di sicurezza** (sensori, allarmi, sistemi antincendio);
    
- **sistemi energetici** (centraline, inverter, smart grid).
    

L’obiettivo è stabilire **se un malfunzionamento sia accidentale o doloso**, se sia imputabile a errore umano, guasto meccanico o **intervento esterno**.  
In alcuni casi, l’analista può ricostruire la **sequenza temporale degli eventi** direttamente dai log del controller.

---

### **6. Infortunistica sul lavoro**

Anche nel campo della **sicurezza sul lavoro**, i sistemi embedded svolgono un ruolo cruciale.  
Macchinari, carrelli elevatori, sistemi di automazione industriale e dispositivi di protezione registrano **log di funzionamento** che possono diventare prove decisive in caso di infortuni o contenziosi.

Esempi di elementi analizzabili:

- tempi di attivazione e spegnimento di macchinari;
    
- stato dei sensori di sicurezza (porte, barriere, pulsanti d’arresto);
    
- log di manutenzione e aggiornamento firmware;
    
- anomalie o disattivazioni manuali dei sistemi di sicurezza.
    

> In questi casi, la Embedded Forensics serve a **stabilire la dinamica degli eventi**, verificando se i dispositivi abbiano operato correttamente e se le procedure di sicurezza siano state rispettate.

---

### **7. Informatica forense sanitaria**

Un ambito in forte crescita è l’**informatica forense sanitaria**, che riguarda i **dispositivi medicali digitali**: pompe di infusione, pacemaker, defibrillatori impiantabili, sistemi diagnostici e apparecchi di imaging.

La loro analisi può rivelare:

- parametri vitali registrati (battito, pressione, frequenza cardiaca);
    
- log di utilizzo e tempi di intervento;
    
- eventuali malfunzionamenti o manipolazioni del software medico.
    

> In ambito ospedaliero, l’Embedded Forensics consente di **accertare la causa di eventi clinici** o verificare la **correttezza delle apparecchiature diagnostiche**, con implicazioni medico-legali rilevanti.

---

### **8. In sintesi**

|Ambito di applicazione|Obiettivo forense|Tipologia di dispositivi|
|---|---|---|
|**Automotive**|Analisi centraline e log di bordo|ECU, ABS, Airbag, Infotainment|
|**Industriale**|Ricostruzione di eventi o guasti|PLC, SCADA, Robot, Inverter|
|**Sicurezza sul lavoro**|Verifica dinamiche e rispetto protocolli|Sensori, barriere, macchinari|
|**Sanitario**|Analisi medico-legale di apparecchi digitali|Pacemaker, defibrillatori, pompe infusione|

---

### **9. Conclusioni**

L’**Embedded Forensics** rappresenta un’estensione naturale dell’informatica forense verso il mondo fisico e industriale.  
Ogni dispositivo “intelligente” può contenere **tracce digitali** che, se acquisite correttamente, assumono valore probatorio.

> L’analista embedded deve unire rigore forense, conoscenze elettroniche e capacità sperimentale.  
> In un ambito dove raramente esistono procedure standard, **la sperimentazione scientifica** è la migliore garanzia di **verifica e attendibilità**.

---

