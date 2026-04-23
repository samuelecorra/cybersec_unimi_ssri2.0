In questa lezione approfondiremo la disciplina dell’Informatica Forense.
### Definizione

L’**Informatica Forense** è la disciplina che si occupa di analizzare e risolvere casi legati alla **criminalità informatica**. Rientrano in questo ambito:

- i **crimini commessi tramite computer**,
    
- i **crimini diretti contro un computer o un sistema informatico**,
    
- i casi in cui un computer o un dispositivo digitale rappresenta una **fonte di prova**.
    

In sintesi, qualsiasi situazione in cui le tecnologie informatiche sono **mezzo, obiettivo o contenitore di evidenze** può essere oggetto di indagine forense.

### Collocazione nelle scienze forensi

L’informatica forense è una **branca delle scienze forensi**. Non si limita al contrasto dei crimini informatici in senso stretto, ma trova applicazione anche in ambito **civile**, dove i dati digitali possono assumere rilevanza probatoria in controversie giudiziarie.

### Importanza nei processi civili

Oltre al contesto penale, sempre più spesso nei **processi civili** si ricorre a prove digitali, ad esempio per dimostrare:

- la validità di un contratto stipulato via e-mail,
    
- la titolarità di un’opera digitale,
    
- la tracciabilità di comunicazioni tra due parti in causa.
    

### Prove digitali

Le **prove digitali** devono possedere caratteristiche ben precise per poter essere considerate in tribunale:

- **Rilevanza** → devono avere utilità concreta nel caso specifico;
    
- **Ammissibilità** → devono essere ottenute nel rispetto della legge e delle procedure processuali;
    
- **Autenticità** → devono essere integre, non alterate e attribuibili con certezza alla loro fonte;
    
- **Legalità** → devono essere raccolte secondo modalità conformi alla normativa vigente.
    

Queste prove sono costituite da **informazioni generate e trasmesse in formato digitale**, dotate di **valore probatorio** e quindi affidabili per supportare accuse o difese in sede giudiziaria.


---
## Campi di applicazione dell’Informatica Forense

Nel tempo, all’interno dell’informatica forense si sono sviluppate tre principali **specializzazioni**, ognuna con tecniche e strumenti dedicati:

### 1. Disk Forensics

La **disk forensics** si occupa dell’acquisizione e dell’analisi dei **supporti di memoria**.

- Dispositivi coinvolti: hard disk, computer, notebook, chiavette USB, server, CD e DVD.
    
- Obiettivi: recuperare, esaminare e preservare file e dati che possono costituire prova.
    
- **Esempi pratici**:
    
    - recupero di file cancellati intenzionalmente in un caso di frode;
        
    - analisi di contenuti multimediali per individuare materiale pedopornografico;
        
    - verifica di log di sistema per ricostruire accessi abusivi.
        

### 2. Network Forensics

La **network forensics** è dedicata all’acquisizione e all’analisi dei **dati di rete** e delle informazioni presenti su Internet.

- Attività principali: cattura e monitoraggio del traffico, acquisizione di pagine web, analisi di pacchetti e log di rete.
    
- Obiettivi: identificare origini e modalità di un attacco informatico, ricostruire comunicazioni o tracciarne i percorsi.
    
- **Esempi pratici**:
    
    - individuare l’indirizzo IP da cui è partito un attacco DDoS;
        
    - ricostruire una campagna di phishing analizzando i server coinvolti;
        
    - raccogliere prove di scambio illecito di dati su forum online.
        

### 3. Mobile Forensics

La **mobile forensics** riguarda l’acquisizione e l’analisi dei **dispositivi mobili**.

- Dispositivi coinvolti: telefoni cellulari, smartphone e tablet.
    
- Obiettivi: estrarre e preservare dati personali, comunicazioni e contenuti multimediali in modo da garantirne l’integrità.
    
- **Esempi pratici**:
    
    - analisi di chat e messaggi in casi di stalking o diffamazione;
        
    - recupero di dati cancellati da uno smartphone in un’indagine per truffa;
        
    - geolocalizzazione di un sospetto tramite i dati di navigazione o GPS.


---
## Quando si utilizza l’Informatica Forense?

L’informatica forense trova applicazione ogni volta che una **prova digitale** è necessaria per indagare su un reato o per sostenerne l’accusa/ difesa in tribunale.

Ecco i principali **ambiti di utilizzo**, con esempi concreti:

### Terrorismo

Analisi di comunicazioni, forum online, chat crittografate e social network per individuare piani di attacco o reclutamenti.

### Cracking

Indagini sugli accessi illeciti a sistemi informatici (violazione di password, sfruttamento di vulnerabilità, manipolazione di software).

### Pedopornografia

Recupero e analisi di contenuti multimediali su computer, dispositivi mobili o cloud; tracciamento delle reti di distribuzione.

### Truffe online

Analisi di transazioni sospette, siti di e-commerce falsi, vendite fraudolente e schemi di scam.

### Phishing

Identificazione di email, siti web e server utilizzati per carpire credenziali e dati bancari delle vittime.

### Violazione della privacy

Indagini su intercettazioni abusive di comunicazioni, diffusione illecita di dati personali o sorveglianza non autorizzata.

### Spamming

Analisi delle campagne di invio massivo di messaggi indesiderati, con tracciamento delle infrastrutture usate per lo spam.

### Stalking

Recupero di messaggi, chiamate e geolocalizzazioni su smartphone; ricostruzione di attività intrusive online.

### Ingiuria e diffamazione

Raccolta di prove da social network, forum o chat, dimostrando la paternità di messaggi offensivi.

### Frode informatica

Analisi di sistemi e database per identificare falsificazioni, manipolazioni di conti o alterazioni di dati contabili.

### Furto e riuso di dati aziendali

Tracciamento di sottrazione di file sensibili (es. brevetti, segreti industriali), anche con verifica di copie non autorizzate.

### Violazioni al diritto d’autore

Indagini su distribuzione illegale di software, musica, film o libri digitali tramite piattaforme web e peer-to-peer.

### Accessi abusivi

Verifica dei log di sistema per provare intrusioni non autorizzate in reti aziendali o personali.

### Danneggiamenti informatici

Analisi di malware, ransomware o attacchi DDoS che hanno compromesso sistemi, dati o infrastrutture.


---
## Le fasi dell’Informatica Forense

Un’indagine di informatica forense si articola in più fasi, ciascuna con procedure specifiche volte a garantire la validità e l’affidabilità delle prove digitali.

### 1. Identificazione

- **Obiettivo**: individuare i dati digitali rilevanti per l’indagine.
    
- **Attività principali**:
    
    - riconoscere i dispositivi di memorizzazione (hard disk, notebook, chiavette USB, server, smartphone, ecc.);
        
    - delimitare il perimetro delle informazioni potenzialmente utili;
        
    - distinguere tra dati pertinenti e non pertinenti.
        
- **Esempio**: individuare il laptop e il cellulare di un sospettato di frode informatica come possibili fonti di prova.
    

### 2. Raccolta

- **Obiettivo**: mettere in sicurezza i dispositivi contenenti i dati di interesse.
    
- **Attività principali**:
    
    - smontaggio fisico dei supporti di memoria;
        
    - adozione di procedure per il trasporto sicuro;
        
    - conservazione controllata per evitare manomissioni o perdite.
        
- **Esempio**: sequestrare un hard disk sigillandolo in una busta antistatica per preservarne l’integrità.
    

### 3. Acquisizione

- **Obiettivo**: realizzare una copia forense completa dei dati.
    
- **Attività principali**:
    
    - utilizzo di software e dispositivi hardware specializzati;
        
    - generazione di una **copia bit a bit** (copia esatta e completa dei dati originali);
        
    - calcolo di un **valore di hash** (impronta digitale univoca) per garantire l’integrità e dimostrare che i dati non sono stati alterati.
        
- **Esempio**: creare un’immagine forense di un disco rigido sospettato di contenere malware.
    

### 4. Analisi

- **Obiettivo**: estrarre dai dati acquisiti solo quelli **rilevanti**, attribuendo loro un significato utile al caso.
    
- **Attività principali**:
    
    - filtrare le informazioni pertinenti;
        
    - ricostruire azioni, cronologie e collegamenti;
        
    - valutare il contributo probatorio dei dati rispetto a un’ipotesi accusatoria o difensiva.
        
- **Esempio**: analizzare cronologie web, file cancellati o log di sistema per provare un accesso abusivo.
    

### 5. Presentazione

- **Obiettivo**: comunicare i risultati dell’indagine in modo chiaro e valido in sede giudiziaria.
    
- **Attività principali**:
    
    - redazione di una **relazione tecnica dettagliata**;
        
    - esposizione delle prove raccolte, delle metodologie usate e delle conclusioni raggiunte;
        
    - spiegazione dei dati in modo comprensibile anche a chi non ha competenze tecniche (magistrati, avvocati, giuria).
        
- **Esempio**: presentare in tribunale un report con schermate, timeline delle attività digitali e valori hash che garantiscono l’autenticità dei dati.