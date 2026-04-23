Ecco ora uno standard molto importante nel mondo industriale lo standard ISA/IEC 62443. Nato come ISA 99 è il primo standard che si occupa specificamente dei sistemi informatici di controllo industriale identificati come IACS. ISA (International Society of Automation) è un’associazione no-profit americana di professionisti del settore che si occupa di definire standard di base e fondazionali.


---
## ISA/IEC 62443 series

La serie **ISA/IEC 62443** nasce come evoluzione di un progetto della **ISA (International Society of Automation)**, un’associazione no-profit americana che dal 1999 ha avviato un gruppo di lavoro specifico per la sicurezza dei sistemi di automazione industriale. Questo gruppo di lavoro, noto come **ISA99**, ha pubblicato nel 2002 le prime versioni degli standard. Successivamente, nel 2010, tali standard sono stati adottati e formalizzati a livello internazionale dalla **IEC (International Electrotechnical Commission)**, assumendo la denominazione ufficiale **ISA/IEC 62443**.

![Pasted image 20250912205047.png](../../imgs/Pasted%20image%2020250912205047.png)

### Obiettivi principali

La serie 62443 ha lo scopo di:

- **Fornire un quadro flessibile e modulare** per affrontare i rischi di sicurezza informatica legati agli IACS (_Industrial Automation and Control Systems_).
    
- **Mitigare le vulnerabilità attuali e future** che possono colpire sistemi di automazione industriale, incluse infrastrutture critiche come energia, trasporti, acqua, oil&gas, chimica.
    
- **Standardizzare approcci e requisiti** per settori industriali molto diversi tra loro, mantenendo però una struttura comune.
    

### Metodo di sviluppo

Gli standard non sono stati creati in maniera isolata, ma attraverso il contributo di una vasta rete internazionale di esperti di sicurezza IACS. Questo ha permesso di:

- Tenere conto delle **esperienze pratiche** maturate nei diversi settori industriali.
    
- Incorporare **conoscenze aggiornate** rispetto alle minacce e alle contromisure tecnologiche più recenti.
    
- Garantire che lo standard fosse **applicabile globalmente** e non limitato a un contesto nazionale o settoriale.
    

### Importanza

Rispetto ad altri standard di cybersecurity (es. ISO/IEC 27000 o NIST CSF), la serie 62443 si distingue per un aspetto fondamentale:  
👉 **si concentra in modo specifico sugli ambienti industriali e sugli impianti di automazione**, che hanno esigenze diverse rispetto ai sistemi IT tradizionali.


---
## Posizionamento di ISA/IEC 62443

Uno degli aspetti più distintivi della serie **ISA/IEC 62443** rispetto ad altri standard di cybersecurity (come **ISO/IEC 27000** o **NIST CSF**) è il **livello di profondità** a cui si applica.

- Gli altri standard rimangono prevalentemente a un livello **strategico/organizzativo**, rivolti a top management, processi aziendali e politiche di sicurezza informatica.
    
- ISA/IEC 62443, invece, **scende fino al livello dell’impianto**: affronta quindi non solo la governance, ma anche i requisiti operativi e tecnici per proteggere macchine, linee produttive e dispositivi di automazione.
    
![Pasted image 20250912205151.png](../../imgs/Pasted%20image%2020250912205151.png)
### Business Rationale e CSMS

Il **Business Rationale**, cioè la logica di business che guida le decisioni sulla sicurezza, rimane comunque di competenza del **top management**.  
Tuttavia, a differenza degli altri standard:

- In **ISA/IEC 62443** questa parte è trattata in modo meno approfondito.
    
- L’attenzione è concentrata sul **Cyber Security Management System (CSMS)** specifico per gli **IACS**, che richiede un approccio molto operativo.
    

### Sintesi

👉 **ISA/IEC 62443 integra due dimensioni**:

1. **Dimensione manageriale**: linee guida generali per la gestione del rischio (business rationale).
    
2. **Dimensione tecnica e operativa**: requisiti concreti di protezione a livello di impianto, sistemi e componenti.
    

---

## Cosa sono gli IACS (Industrial Automation and Control Systems)

Gli **IACS** (Industrial Automation and Control Systems) comprendono l’insieme delle tecnologie, hardware e software, che permettono di **automatizzare, monitorare e controllare processi industriali e impianti produttivi**.

![Pasted image 20250912205334.png](../../imgs/Pasted%20image%2020250912205334.png)

Lo standard ISA/IEC 62443 si concentra su tre tipologie principali:

### 1. SCADA – Supervisory Control And Data Acquisition

- È un **sistema di supervisione e acquisizione dati**.
    
- Permette agli operatori di **condurre un impianto, una linea o una macchina industriale**.
    
- Può essere **stand-alone** (indipendente) o basato su architetture distribuite di computer.
    
- Si appoggia spesso a **sistemi operativi commerciali standard** (es. Windows).
    
- Viene usato in contesti come reti elettriche, distribuzione idrica, trasporti.
    

### 2. PLC – Programmable Logic Controller

- È un **controllore logico programmabile**.
    
- Si interfaccia attraverso gli **I/O (Input/Output)** con:
    
    - Sensori
        
    - Attuatori
        
    - Motori
        
- Permette di realizzare **logiche di automazione industriale**.
    
- Usa **sistemi operativi realtime proprietari**, progettati per garantire affidabilità e risposta immediata.
    

### 3. DCS – Distributed Control System

- È un **sistema di controllo distribuito** che integra:
    
    - **HMI (Human-Machine Interface)**
        
    - **PLC**
        
- È particolarmente usato negli **impianti di processo** (chimico, petrolchimico, Oil&Gas).
    
- Punti di forza:
    
    - Alta **configurabilità**
        
    - Adattabilità a contesti fortemente **standardizzati**.
        

---

📌 **Nota chiave**: questi sistemi non sono semplici computer “da ufficio”, ma infrastrutture critiche che controllano direttamente processi fisici (es. generazione elettrica, distribuzione idrica, chimica). Per questo richiedono standard di sicurezza dedicati.


---
## Struttura di ISA/IEC 62443

Lo standard **ISA/IEC 62443** è organizzato in una **serie modulare di documenti**, che insieme formano un framework organico e coerente per la sicurezza degli **IACS**.  
L’intera serie è **in continua evoluzione**: alcuni documenti sono pubblicati, altri in revisione, altri ancora in lavorazione.

### Le 4 famiglie principali (come mostrato dalla figura)

![Pasted image 20250916214726.png](../../imgs/Pasted%20image%2020250916214726.png)

🔹 **1. General (blu in alto nella figura)**

- Raccoglie i documenti di **base concettuale**.
    
- Qui troviamo **vocabolari, modelli, concetti** e metriche di riferimento.
    
- Include anche la descrizione del **ciclo di vita della sicurezza IACS** e casi d’uso.
    
- Obiettivo: dare un **linguaggio comune** a tutti gli attori.
    

🔹 **2. Policies & Procedures (verde nella figura)**

- Riguarda la definizione e l’implementazione di un **CSMS (Cyber Security Management System)**.
    
- Include linee guida su gestione delle patch, requisiti per fornitori, metodologie di valutazione.
    
- È la parte più vicina alla **governance** e ai processi organizzativi.
    

🔹 **3. System (arancione nella figura)**

- Focalizzata sui sistemi di automazione industriale nel loro insieme.
    
- Tratta:
    
    - Tecnologie di sicurezza,
        
    - Metodi di valutazione del rischio,
        
    - Definizione dei **livelli di sicurezza (Security Levels, SL)**.
        
- Obiettivo: fornire criteri sistemici e metodologici per la progettazione sicura.
    

🔹 **4. Component (rosso in basso nella figura)**

- Si concentra sui **singoli componenti** hardware/software degli IACS.
    
- Stabilisce requisiti tecnici e vincoli sul **ciclo di vita del prodotto**.
    
- È pensata per i **fornitori di prodotti e componenti**, così che i singoli pezzi siano sicuri e integrabili in un sistema conforme.
    

---

## Collegamenti con i modelli precedenti

La struttura ISA/IEC 62443 **riprende e adatta** concetti dai modelli precedenti, in particolare:

- **ISA-95** (modello per l’integrazione dei sistemi aziendali e di automazione).
    
- **Purdue model** (architettura a livelli usata negli ambienti industriali).
    

Grazie a questi riferimenti, la serie 62443 riesce a:

- Integrare sicurezza e gestione del rischio in tutte le aree aziendali.
    
- Coprire non solo le **architetture di riferimento**, ma anche:
    
    - processi di sicurezza,
        
    - requisiti tecnologici,
        
    - controlli,
        
    - procedure di accettazione/test,
        
    - sviluppo sicuro dei prodotti,
        
    - ciclo di vita della sicurezza,
        
    - gestione centralizzata con un **CSMS**.
        

---

## Esempi di documenti (con codici)

- **62443-1-1** → concetti e modelli introduttivi.
    
- **62443-1-2** → glossario di termini e abbreviazioni.
    
- **62443-1-3** → metriche quantitative.
    
- **62443-1-4** → descrizione del ciclo di vita della sicurezza IACS.
    
- **62443-2-1** → requisiti per implementare un CSMS.
    
- **62443-2-2** → metodologia di valutazione della protezione IACS.
    
- **62443-2-3** → gestione delle patch.
    
- **62443-2-4** → requisiti per i fornitori di sistemi IACS.
    
- **62443-2-5** → guida per il funzionamento di un CSMS.
    
- **62443-3-1** → applicazione delle tecnologie di sicurezza negli IACS.
    
- **62443-3-2** → valutazione del rischio e progettazione sicura dei sistemi.
    
- **62443-3-3** → requisiti di sicurezza e livelli di sicurezza per i sistemi.
    
- **62443-4-1** → requisiti per lo sviluppo sicuro dei prodotti (ciclo di vita).
    
- **62443-4-2** → requisiti tecnici per i componenti IACS.
    

---

## Commento alla figura

![Pasted image 20250912205624.png](../../imgs/Pasted%20image%2020250912205624.png)

La figura che hai allegato è molto chiara:

- A sinistra troviamo le **quattro macro-aree** (Modelli → Gestione → Sistema → Componente).
    
- A destra, ogni rettangolo colorato rappresenta i **documenti associati** a quella famiglia, con i rispettivi codici (es. 62443-1-1, 62443-2-1, ecc.).
    
- L’**ordine verticale** riflette la logica di approfondimento:
    
    - Si parte dai concetti di base (General),
        
    - si passa alle regole organizzative (Policies & Procedures),
        
    - poi ai sistemi (System),
        
    - fino ai singoli componenti (Component).
        

In altre parole, la figura rappresenta la **mappa completa della serie ISA/IEC 62443**, mostrando come ogni documento si colloca all’interno del framework.


---
## Elementi di un CSMS – ISA/IEC 62443-2-1

Il documento **ISA/IEC 62443-2-1** definisce in modo dettagliato quali siano gli elementi costitutivi di un **CSMS** per gli IACS.  
L’obiettivo è fornire un approccio **strutturato e replicabile** per:

1. Analizzare i rischi,
    
2. Indirizzarli con contromisure adeguate,
    
3. Monitorare e migliorare il sistema di sicurezza nel tempo.
    

---

### Le 3 macro-categorie di elementi (come mostrato nell’immagine a sinistra)

1. **Risk Analysis (Analisi del rischio)**
    
    - Include il **Business rationale** (motivazioni economiche e di business che giustificano l’investimento in sicurezza).
        
    - Comprende la fase di **Risk identification, classification and assessment**, ossia identificazione, classificazione e valutazione dei rischi.
        
2. **Addressing risk with the CSMS (Affrontare il rischio tramite il CSMS)**  
    Racchiude tre sottoinsiemi fondamentali:
    
    - **Security policy, organization and awareness**
        
        - Definizione dello **scope** del CSMS.
            
        - Organizzazione interna e ruoli.
            
        - Formazione del personale e sensibilizzazione.
            
        - Piani di continuità operativa.
            
        - Policy e procedure di sicurezza.
            
    - **Selected security countermeasures**
        
        - Sicurezza del personale.
            
        - Sicurezza fisica e ambientale.
            
        - Segmentazione di rete.
            
        - Controlli sugli accessi (account, autenticazione, autorizzazione).
            
    - **Implementation**
        
        - Gestione del rischio e implementazione delle contromisure.
            
        - Sviluppo e manutenzione sicura dei sistemi.
            
        - Gestione delle informazioni e dei documenti.
            
        - Pianificazione e risposta agli incidenti.
            
3. **Monitoring and improving the CSMS (Monitoraggio e miglioramento)**
    
    - Include il **conformance** (verifica della conformità).
        
    - Processi di **revisione, miglioramento e mantenimento** del CSMS nel tempo.
        

---

### Struttura di ogni elemento (diagramma a destra)

![Pasted image 20250912205755.png](../../imgs/Pasted%20image%2020250912205755.png)

Ogni elemento del CSMS è descritto da:

- **Obiettivo** → cosa si vuole ottenere.
    
- **Fondamento logico (Rationale)** → perché è necessario.
    
- **Descrizione** → come va interpretato.
    
- **Requisiti** → una lista numerata (Requisito 1, 2, … n) che dettaglia le azioni specifiche da compiere.
    

Infine, nell’**Annex A** del documento, viene fornita una **guida pratica** su come sviluppare ogni elemento in base al contesto reale e alle necessità specifiche dell’organizzazione.

---

## Commento all’immagine

![Pasted image 20250912205800.png](../../imgs/Pasted%20image%2020250912205800.png)

L’immagine schematizza bene la logica del CSMS:

- In alto, il **rettangolo blu “Risk analysis”** mostra che tutto parte dall’analisi dei rischi e dal business rationale.
    
- Al centro, i tre blocchi principali (**policy, contromisure, implementazione**) costituiscono il cuore operativo del CSMS.
    
- In basso, la **sezione blu “Monitoring and improving”** ricorda che il sistema non è statico: deve essere **monitorato, migliorato e aggiornato continuamente**.
    
- La parte a destra evidenzia come ogni elemento non sia vago, ma strutturato in obiettivi, logica, descrizione e requisiti concreti, con supporto nell’Annex A.
    

👉 In sostanza, il modello **trasforma l’analisi dei rischi in misure organizzative, tecniche e procedurali**, e chiude il ciclo con monitoraggio e miglioramento continuo.


---
## Requisiti di un CSMS – Esempio: _Risk Identification, Classification and Assessment_

Il documento **ISA/IEC 62443-2-1** specifica che ogni elemento del **CSMS** viene articolato in tre parti (Obiettivo – Fondamento Logico – Descrizione) e si traduce in una serie di **requisiti operativi**.  
Nella slide, come esempio, è presentato l’elemento **“Risk identification, classification and assessment”**.

![Pasted image 20250912205856.png](../../imgs/Pasted%20image%2020250912205856.png)

---

### 1. Obiettivo

- **Identificare l’insieme dei rischi relativi agli IACS** affrontati da un’organizzazione.
    
- Classificarli e valutarne **gravità e probabilità**.
    
- Fornire una base chiara per stabilire quali minacce devono essere mitigate e con quale priorità.
    

👉 In altre parole: senza una mappa dei rischi, non ha senso parlare di sicurezza.

---

### 2. Fondamento Logico (Rationale)

- Il motivo principale per cui un’organizzazione investe in **cybersecurity industriale** è **ridurre il rischio**.
    
- La riduzione del rischio deve però basarsi su una **comprensione realistica**:
    
    - Quali rischi esistono?
        
    - Quanto sono probabili?
        
    - Quali conseguenze potrebbero avere?
        
- Solo con questa base si possono selezionare e giustificare le contromisure.
    

---

### 3. Descrizione

- L’organizzazione deve proteggere la propria capacità di **portare avanti le missioni e i processi critici**.
    
- Ciò avviene tramite:
    
    - **Identificazione delle minacce**,
        
    - **Prioritizzazione** in base al livello di rischio,
        
    - **Analisi delle vulnerabilità** e delle possibili conseguenze,
        
    - Uso di **metodologie riconosciute e diffuse** (es. analisi qualitativa e quantitativa dei rischi).
        

👉 Il risultato è un quadro oggettivo e documentato della situazione di rischio.

---

### 4. Requisiti associati

Da questi tre elementi derivano i requisiti operativi.  
Nella slide non sono elencati in dettaglio, ma in ISA/IEC 62443-2-1 ogni requisito è numerato e descritto.  
Ad esempio, per l’elemento “Risk identification, classification and assessment” si possono avere requisiti come:

- Documentare un processo formale di identificazione dei rischi.
    
- Mantenere un inventario aggiornato degli asset IACS.
    
- Classificare i rischi in base a criteri comuni (probabilità × impatto).
    
- Riesaminare periodicamente l’analisi dei rischi per aggiornarla.
    

---

## Commento alla slide

![Pasted image 20250912205850.png](../../imgs/Pasted%20image%2020250912205850.png)

La figura a destra nella slide sintetizza la logica:

- Ogni **elemento del CSMS** è descritto da _Obiettivo → Fondamento logico → Descrizione_.
    
- Da qui derivano i **Requisiti numerati** (Requisito 1, Requisito 2, … n).
    
- L’**Annex A** fornisce una guida operativa per contestualizzare ogni requisito alle esigenze specifiche dell’organizzazione.
    

👉 Questo approccio permette di **standardizzare e verificare** la sicurezza:

- Non solo principi generali, ma requisiti concreti da implementare e misurare.


---
## Zones and Conduits – ISA/IEC 62443-1-1

Il concetto di **zone e conduits** è uno dei pilastri della serie ISA/IEC 62443 perché introduce la logica della **segmentazione** e della **difesa in profondità** negli ambienti industriali.

### Zone

- Una **zona** è un insieme di risorse (fisiche o logiche) che condividono **requisiti di sicurezza comuni**.
    
- I requisiti vengono determinati in base al **Security Level (SL)**, stabilito dall’**analisi del rischio**.
    
- Le zone hanno **confini ben definiti** che separano ciò che è interno (protegge risorse omogenee) da ciò che è esterno (altre zone o ambienti).
    
- All’interno di una zona, le informazioni possono muoversi liberamente; quando devono attraversare i confini, passano per i **conduits**.
    
- Una zona può essere ulteriormente suddivisa in **sottozone**, ciascuna con requisiti di sicurezza più specifici: questo consente di implementare una vera **difesa multilivello (defense-in-depth)**.
    

### Conduits

- I **conduits** sono i “canali di comunicazione” tra due zone.
    
- Possono essere reti cablate, connessioni wireless, o altre forme di collegamento.
    
- Oltre a veicolare le informazioni, i conduits implementano **funzioni di sicurezza** che:
    
    - Garantisco comunicazioni sicure,
        
    - Consentono l’interoperabilità di zone con **differenti livelli di sicurezza**, evitando compromessi.
        

👉 In sintesi: **Zone = compartimenti di sicurezza**; **Conduits = corridoi controllati che collegano i compartimenti**.

---

## Commento al grafico

![Pasted image 20250912210029.png](../../imgs/Pasted%20image%2020250912210029.png)

Nell’immagine vediamo un esempio pratico di applicazione del modello:

- In alto c’è l’**Enterprise Zone** (zona aziendale), con laptop, workstation, server, mainframe → rappresenta la parte IT classica.
    
- La connessione tra l’Enterprise Zone e le zone degli impianti avviene tramite un **Enterprise Conduit** (linea rossa), che regola lo scambio di dati.
    
- In basso troviamo tre **Plant Zones** (A, B e C), ciascuna dedicata a un impianto:
    
    - Ogni plant ha una propria **Control Zone** (verde) con i sistemi applicativi (server, HMI, workstation di controllo).
        
    - Ogni Control Zone è collegata a una **Plant Control/Conduit** (magenta), che funge da barriera controllata tra i sistemi applicativi e i livelli più bassi (controller, PLC).
        
- Le zone sono compartimentate: ogni Plant Zone è separata dalle altre, e le comunicazioni avvengono solo tramite conduits dedicati.
    

📌 Questo modello mostra come **l’architettura industriale non sia una rete piatta**, ma un insieme di zone separate, con **“passaggi obbligati” controllati** (conduits) che riducono il rischio di propagazione degli attacchi


---
## Foundational Requirements – ISA/IEC 62443-1-1

Gli standard ISA/IEC 62443 introducono i **requisiti fondazionali** per la sicurezza degli IACS.  
Questi requisiti nascono come estensione del modello classico **CIA** (Confidentiality, Integrity, Availability), ma vengono **riordinati** perché negli ambienti industriali le priorità cambiano radicalmente rispetto all’IT tradizionale.

---

### Differenza tra IT e IACS

- **IT tradizionale (General Purpose Information Systems)**
    
    - Priorità: **Confidentiality → Integrity → Availability**
        
    - Si cerca prima di tutto di proteggere i dati sensibili (es. dati personali, segreti aziendali).
        
- **IACS (Industrial Automation & Control Systems)**
    
    - Priorità invertita: **Availability → Integrity → Confidentiality**
        
    - Motivo:
        
        - Se un sistema SCADA o PLC non è disponibile, l’impianto si ferma → gravi danni economici.
            
        - Se l’integrità del dato è compromessa, macchine e processi possono diventare pericolosi.
            
        - La confidenzialità, pur importante (es. protezione del know-how), è meno critica nell’operatività immediata.
            

📌 In sintesi: negli IACS, **la continuità operativa e la sicurezza fisica** hanno la precedenza rispetto alla protezione dei dati.

---

### I 7 Requisiti Fondazionali

![Pasted image 20250912210222.png](../../imgs/Pasted%20image%2020250912210222.png)

1. **Access Control (AC)**  
    Controllo degli accessi a dispositivi e informazioni → impedisce interrogazioni non autorizzate.
    
2. **Use Control (UC)**  
    Controllo dell’uso dei dispositivi e delle informazioni → evita operazioni non autorizzate.
    
3. **Data Integrity (DI)**  
    Garantire che i dati non vengano alterati in modo non autorizzato.
    
4. **Data Confidentiality (DC)**  
    Protezione della riservatezza dei dati → evitare fughe di informazioni.
    
5. **Restrict Data Flow (RDF)**  
    Limitare il flusso dei dati per impedire che informazioni vengano pubblicate o raggiungano soggetti non autorizzati.
    
6. **Timely Response to Event (TRE)**  
    Rispondere rapidamente a violazioni di sicurezza:
    
    - notificare l’autorità preposta,
        
    - raccogliere prove forensi,
        
    - avviare azioni correttive immediate in situazioni mission-critical o con impatto sulla sicurezza umana.
        
7. **Resource Availability (RE)**  
    Garantire la disponibilità delle risorse di rete → protezione contro attacchi DoS (Denial of Service).
    

---

## Commento al grafico (freccia a sinistra)

![Pasted image 20250912210211.png](../../imgs/Pasted%20image%2020250912210211.png)

La freccia mostra in maniera intuitiva la **differenza di priorità** tra IACS e IT:

- **A sinistra (IACS)** → la freccia punta in alto partendo dalla Confidentiality, ma con **Availability in cima**: significa che la disponibilità è la priorità numero uno, seguita dall’integrità e infine dalla confidenzialità.
    
- **A destra (IT tradizionale)** → la freccia è invertita: la Confidentiality è in cima, Availability in fondo.
    

👉 Questo evidenzia la filosofia industriale: **un dato segreto perso è un danno, ma una macchina ferma o un impianto fuori controllo è un disastro**.


---
## Security Levels – ISA/IEC 62443-1-1

Dopo aver introdotto le **Zone** e i **Foundational Requirements**, lo standard stabilisce anche i **Livelli di Sicurezza (SL)** come metodo qualitativo per valutare il livello di protezione di una zona, di un sistema o di un componente.

### Livelli qualitativi di sicurezza

- **SL 1 – Basso** → protezione di base, difende solo contro errori o violazioni casuali.
    
- **SL 2 – Medio** → protezione contro attacchi intenzionali condotti con mezzi semplici, da soggetti con basse risorse e scarsa competenza.
    
- **SL 3 – Alto** → protezione contro attacchi intenzionali più sofisticati, con risorse moderate e competenze specifiche sugli IACS.
    

_(Nella parte di serie 62443-3-3, che vedremo dopo, i livelli vengono estesi fino a SL4, ma qui la base è su tre livelli.)_

---

### Tre tipologie di Security Level

Lo standard non si limita a dire _che livello di sicurezza c’è_, ma distingue tre prospettive:

1. **SL Target (SL-T)**
    
    - È il livello **obiettivo** di sicurezza che una zona o un conduit dovrebbe raggiungere.
        
    - Si stabilisce **dopo la valutazione del rischio**.
        
2. **SL Achieved (SL-A)**
    
    - È il livello **effettivamente raggiunto** nella realtà, verificato tramite misurazioni o audit.
        
    - Deve essere sempre ≥ al livello target.
        
3. **SL Capability (SL-C)**
    
    - Indica la **capacità massima** raggiungibile, tenendo conto:
        
        - delle contromisure implementabili (tecniche, organizzative, fisiche),
            
        - o dei limiti intrinseci di dispositivi/sistemi.
            

---

### Contromisure

Gli SL non sono solo teorici: sono collegati a diverse categorie di contromisure:

- **Tecniche** → firewall, antivirus, intrusion detection.
    
- **Amministrative** → policy, procedure, linee guida.
    
- **Fisiche** → barriere fisiche, porte blindate, armadi chiusi.
    

---

## Commento al grafico

Nel grafico della slide vediamo la rappresentazione dei concetti principali:

![Pasted image 20250912210618.png](../../imgs/Pasted%20image%2020250912210618.png)

![Pasted image 20250912210628.png](../../imgs/Pasted%20image%2020250912210628.png)
- A sinistra: la **scala dei Security Levels** (basso, medio, alto).
    
- A destra: le tre tipologie di SL (Target, Achieved, Capability), mostrate come livelli misurabili di sicurezza da associare a zone e conduits.
    
- In basso: esempi di **contromisure** che concorrono a determinare il livello effettivo di protezione.
    

👉 La logica è la seguente:

- Prima si fa l’**analisi del rischio** → si definisce il **livello Target**.
    
- Poi si implementano contromisure → si misura il **livello Achieved**.
    
- Infine, si valuta se esistono limiti o margini ulteriori → si identifica il **livello Capability**.
    

📌 Questo modello evita di avere obiettivi irrealistici: un impianto può porsi un livello target SL2, ottenere SL2 (Achieved) e sapere che non potrà superare SL3 per vincoli tecnici → consapevolezza e gestione realistica della sicurezza.


---
## Livelli di Sicurezza di Sistema – ISA/IEC 62443-3-3

Il documento **ISA/IEC 62443-3-3** prende i **Foundational Requirements (FR)** e li declina in **Security Levels (SL)** specifici per i sistemi e i componenti.  
L’idea è di valutare la protezione di un sistema industriale contro attacchi di intensità crescente, considerando **tecnologia, risorse economiche e motivazione degli attaccanti**.

---

### I 7 Foundational Requirements (FR)

![Pasted image 20250912210745.png](../../imgs/Pasted%20image%2020250912210745.png)

1. **FR1 – Identification and Authentication Control (IAC)**
    
2. **FR2 – Use Control (UC)**
    
3. **FR3 – System Integrity (SI)**
    
4. **FR4 – Data Confidentiality (DC)**
    
5. **FR5 – Restrict Data Flow (RDF)**
    
6. **FR6 – Timely Response to Event (TRE)**
    
7. **FR7 – Resource Availability (RE)**
    

👉 Questi sono gli stessi introdotti in 62443-1-1, ma qui vengono declinati in requisiti concreti di sicurezza a livello di sistema.

---

### I 4 Livelli di Sicurezza (SL)

![Pasted image 20250912210749.png](../../imgs/Pasted%20image%2020250912210749.png)

- **SL 1 – Protezione contro violazioni casuali**  
    Difesa da errori non intenzionali, guasti, o incidenti accidentali.
    
- **SL 2 – Protezione contro violazioni intenzionali semplici**  
    Attacchi condotti con **mezzi rudimentali**, poche risorse, skill generici, scarsa motivazione.
    
- **SL 3 – Protezione contro violazioni intenzionali sofisticate**  
    Attacchi più complessi, con **risorse moderate**, competenze specifiche sugli IACS, e motivazione media.
    
- **SL 4 – Protezione contro violazioni avanzate**  
    Attacchi di attori avanzati, con **risorse estese**, skill specialistici sugli IACS e **alta motivazione** (tipicamente minacce di livello statale o gruppi APT).
    

---

### Requisiti di Sicurezza (SR) e Requirement Enhancements (RE)

- Ogni FR viene tradotto in **Security Requirements (SR)** numerati (es. SR 1.X per l’IAC).
    
- Gli SR possono includere:
    
    - **requisiti di base**,
        
    - **Requirement Enhancements (RE)** → requisiti aggiuntivi che rafforzano ulteriormente la protezione.
        

---

## Commento al grafico

La slide rappresenta la logica di mappatura:

- A sinistra: i **7 Foundational Requirements (FR)**.
    
- A destra: i **4 Security Levels (SL)**, dalla protezione minima (SL1) a quella massima (SL4).
    
- Ogni FR viene valutato in base ai 4 SL → questo crea una **matrice di requisiti**.
    
- In basso, il riquadro “SL-C (IAC, control systems) 1…4” indica che per il requisito di identificazione e autenticazione (IAC) esistono versioni diverse di requisiti a seconda del livello di sicurezza desiderato.
    
- L’output finale è un insieme di **SR numerati (es. SR 1.X)**, che specificano cosa deve essere implementato per raggiungere un certo livello di sicurezza su un dato requisito.
    

📌 In sostanza:

- Lo standard non si limita a dire “serve SL3”, ma spiega **per ogni requisito** cosa significa SL1, SL2, SL3, SL4.
    
- Questo consente una progettazione modulare e verificabile della sicurezza dei sistemi IACS.


---
## Maturity Levels – ISA/IEC 62443-4-1

Questa parte dello standard non riguarda direttamente i sistemi in esercizio, ma i **fornitori di componenti e prodotti industriali** (es. PLC, HMI, software di automazione).  
L’obiettivo è garantire che anche il **processo di sviluppo** dei prodotti segua criteri di sicurezza robusti, ripetibili e controllati.

Lo standard **62443-4-1** introduce i **Livelli di Maturità (Maturity Levels)** per classificare la capacità di un fornitore di sviluppare prodotti sicuri nel tempo.

---

### I 4 Livelli di Maturità

1. **Livello 1 – Iniziale**
    
    - Lo sviluppo del prodotto è **ad hoc**, non pianificato né documentato.
        
    - Le attività di sicurezza sono sporadiche o inesistenti.
        
    - Rischio: vulnerabilità elevate e mancanza di coerenza.
        
2. **Livello 2 – Gestito**
    
    - Il fornitore segue **linee guida scritte**.
        
    - Il personale è formato e ha competenze adeguate.
        
    - I processi sono **ripetibili** e non dipendono da singoli individui.
        
3. **Livello 3 – Definito (Praticato)**
    
    - I processi sono **documentati e standardizzati** a livello di tutta l’organizzazione.
        
    - Esistono **evidenze** che le procedure di sicurezza sono praticate regolarmente.
        
    - I processi sono applicati in modo uniforme in tutti i team.
        
4. **Livello 4 – Miglioramento (Quantitativamente gestito)**
    
    - Il fornitore usa **metriche di processo** per monitorare efficacia e performance.
        
    - Viene perseguito il **miglioramento continuo** della sicurezza nello sviluppo dei prodotti.
        
    - È il livello più alto, tipico di fornitori maturi e affidabili.
        

---

### Significato

- Questi livelli servono a distinguere i fornitori che **improvvisano** da quelli che hanno una **cultura di sicurezza strutturata**.
    
- Per gli operatori industriali, sapere che un prodotto proviene da un fornitore “Maturity Level 3 o 4” significa avere maggiore fiducia nella robustezza di quel componente.
    

---

## Commento al grafico della slide

![Pasted image 20250912211015.png](../../imgs/Pasted%20image%2020250912211015.png)

- La slide (che rappresenta i quattro livelli di maturità) mostra una progressione verticale:
    
    - dal livello iniziale (ad hoc, caotico),
        
    - fino al livello di miglioramento continuo (monitoraggio con metriche).
        
- È una logica simile ai modelli di **maturità dei processi (CMMI)**, ma applicata specificamente allo sviluppo sicuro dei prodotti industriali.
    
- La crescita è **incrementale**: non si può arrivare al livello 4 senza aver consolidato i livelli precedenti.
    

📌 In sintesi: **ISA/IEC 62443-4-1 sposta il focus dagli impianti ai produttori**, assicurandosi che la sicurezza venga incorporata **già in fase di progettazione e sviluppo**.


---
