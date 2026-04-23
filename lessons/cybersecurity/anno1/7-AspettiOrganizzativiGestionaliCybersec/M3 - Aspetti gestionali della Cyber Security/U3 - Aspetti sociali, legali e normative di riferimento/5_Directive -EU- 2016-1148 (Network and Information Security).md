In questa lezione approfondiremo la Direttiva UE 2016/1148 (Network and Information Security) e il suo recepimento italiano con il Decreto Legislativo 18 maggio 2018, n. 65 e dal Decreto del Presidente del Consiglio dei ministri 8 agosto 2019 art. 4.


---
# Direttiva (UE) 2016/1148 – La NIS

La **Direttiva NIS** è il **primo vero atto legislativo dell’Unione Europea** in materia di **sicurezza informatica**.

![Pasted image 20250912230212](../../imgs/Pasted%20image%2020250912230212.png)

- Data di adozione: **6 luglio 2016**.
    
- Finalità: rafforzare la protezione delle reti e dei sistemi informativi negli Stati Membri.
    
- Contesto: rientra nella **strategia per il decennio digitale dell’UE** (pubblicata il **16 dicembre 2020**), che ha due obiettivi politici fondamentali:
    
    1. **Sovranità digitale** → cioè capacità dell’Europa di controllare le proprie infrastrutture critiche senza dipendere da altri Paesi.
        
    2. **Leadership tecnologica** → non solo “tenere il passo”, ma diventare un punto di riferimento globale nel settore.
        

In altre parole: la NIS nasce per **standardizzare la sicurezza informatica** in Europa e per evitare che ci siano Stati molto forti e altri molto deboli, creando un **livello minimo comune**.

---

## Obblighi introdotti dalla Direttiva

La Direttiva NIS non è un “documento di principi”, ma introduce **obblighi concreti** per gli Stati Membri:

1. **Adozione di misure di sicurezza comuni e adeguate** → ogni Paese deve varare leggi interne che obblighino gli operatori critici a implementare controlli di sicurezza.
    
2. **Notifica obbligatoria degli incidenti** → ogni incidente rilevante deve essere segnalato a una **Autorità nazionale** designata.
    
3. **Istituzione dei CSIRT nazionali** (Computer Security Incident Response Team) → squadre operative specializzate nella gestione degli incidenti, sul modello del **CERT-UE**.
    
4. **Creazione di un network europeo** → collegamento tra i CSIRT nazionali e il CERT-UE, per condividere informazioni e coordinare le risposte a livello comunitario.
    

Esempio pratico: se un attacco colpisce un ospedale in Germania e un gestore energetico in Italia, i CSIRT nazionali devono scambiarsi informazioni in tempo reale, così da reagire più rapidamente e prevenire “effetti domino” su scala europea.

---

## Differenza tra **Direttiva** e **Regolamento**

Per capire bene la NIS bisogna distinguere due strumenti giuridici fondamentali dell’UE:

- **Direttiva**:
    
    - Stabilisce un **obiettivo comune** che tutti gli Stati devono raggiungere.
        
    - Lascia libertà ai singoli Paesi di decidere **come** raggiungerlo tramite leggi nazionali.
        
    - Esempio: la **Direttiva sui diritti dei consumatori** → obbliga tutti gli Stati a garantire più tutele ai cittadini (es. eliminare costi nascosti, estendere i tempi di recesso), ma ogni Stato traduce questi principi con proprie leggi interne.
        
- **Regolamento**:
    
    - È un **atto legislativo vincolante e diretto**: entra in vigore nello stesso modo in tutti i Paesi senza bisogno di leggi di recepimento.
        
    - Esempio: il Regolamento sulle **garanzie comuni dei beni importati** → stesso testo, stessa applicazione in tutta l’UE, senza margini di adattamento nazionale.
        

➡️ Questo spiega perché la NIS è stata recepita in Italia con un decreto legislativo (D.lgs. 65/2018): essendo una **Direttiva**, richiedeva un passaggio legislativo nazionale.

---

## Sintesi concettuale

- **La NIS** → primo quadro UE di cybersecurity (2016).
    
- **Obiettivi** → standardizzare la sicurezza, notificare incidenti, creare CSIRT, costruire rete europea.
    
- **Direttiva ≠ Regolamento** → la prima fissa obiettivi e va recepita con leggi nazionali; il secondo è immediatamente applicabile in tutti gli Stati.


---
# Obiettivi della Direttiva NIS

![Pasted image 20250912230547](../../imgs/Pasted%20image%2020250912230547.png)

L’idea di fondo della NIS è che **ogni Stato membro** deve portarsi a un livello **“elevato” e omogeneo** di sicurezza delle reti e dei sistemi informativi.  
Il termine _framework comune_ significa che non basta che alcuni Paesi siano molto sicuri e altri meno: tutti devono condividere una base solida, perché la sicurezza informatica è interconnessa.

Pensala come una **catena**: se un anello è debole, l’intera catena può spezzarsi. La NIS serve ad evitare questo scenario.

---

## I quattro pilastri della sicurezza NIS

### 1. Gestione dei rischi di sicurezza

- Significa **mappare, valutare e governare** i rischi prima che diventino incidenti.
    
- Coinvolge attività come: analisi delle vulnerabilità, valutazioni periodiche dei rischi, definizione di politiche di sicurezza e piani di continuità operativa.
    
- Esempio: un gestore elettrico deve avere processi per capire quali sono i suoi asset critici (centrali, SCADA, reti di trasmissione) e prevedere contromisure in caso di minacce.
    

➡️ Qui il concetto è **proattività**: non aspettare che il problema si presenti, ma lavorare in anticipo per ridurre la probabilità che si verifichi.

---

### 2. Protezione contro i cyber attacchi

- Una volta identificati i rischi, bisogna implementare **controlli di sicurezza**.
    
- Parliamo di misure tecniche e organizzative: firewall, IDS/IPS, autenticazione forte, segmentazione di rete, formazione del personale, politiche di backup, ecc.
    
- Obiettivo: **prevenire** che un attacco possa colpire con successo.
    

➡️ In termini pratici: se un attacco di phishing arriva a un ospedale, le difese tecniche (filtri mail, sistemi di detection) e organizzative (formazione del personale) devono bloccarlo prima che diventi un’emergenza.

---

### 3. Individuazione degli incidenti di cybersicurezza

- Nessun sistema è impenetrabile: per questo è essenziale avere la capacità di **scoprire** rapidamente quando qualcosa va storto.
    
- Significa dotarsi di sistemi di monitoraggio, logging avanzato, SIEM (Security Information and Event Management), SOC (Security Operation Center).
    
- Non basta prevenire: bisogna anche **sapere in tempo reale se un attacco è in corso**.
    

➡️ Esempio: un operatore dei trasporti deve essere in grado di rilevare se c’è un accesso anomalo ai suoi sistemi di controllo ferroviario, distinguendo tra un errore tecnico e un attacco mirato.

---

### 4. Riduzione dell’impatto degli incidenti

- Quando un incidente si verifica, l’importante è **limitarne i danni** e ripristinare la funzionalità il più velocemente possibile.
    
- Ciò implica procedure di **incident response**, piani di continuità, disaster recovery, comunicazioni tempestive alle autorità e agli utenti.
    
- L’idea è che la sicurezza non è solo _evitare l’attacco_, ma anche _sapere come rialzarsi rapidamente_.
    

➡️ Esempio: se un ransomware blocca i sistemi informatici di un ospedale, servono piani per garantire comunque l’operatività critica (pronto soccorso, sale operatorie), riducendo l’impatto su cittadini e pazienti.

---

## Sintesi concettuale

Gli **obiettivi NIS** si possono riassumere così:

1. **Governare i rischi** (sapere dove sei vulnerabile).
    
2. **Mettere difese** (proteggersi da attacchi).
    
3. **Accorgersi subito** (individuare incidenti).
    
4. **Limitare i danni** (rispondere e ripristinare).
    

È una logica ciclica: identifico i rischi → mi proteggo → rilevo ciò che sfugge → contengo l’impatto.  
Questo ciclo ricorda molto i framework di gestione della sicurezza come **NIST CSF** o **ISO/IEC 27001**: la NIS li riprende e li armonizza a livello europeo.


---
# CERT-UE – Computer Emergency Response Team dell’Unione Europea

![Pasted image 20250912230643](../../imgs/Pasted%20image%2020250912230643.png)

## Che cos’è

Il **CERT-UE** è la **squadra di pronto intervento informatico** dedicata esclusivamente alle **istituzioni, organi e agenzie dell’Unione Europea**.  
Non si occupa quindi dei singoli Stati membri, ma dell’ecosistema istituzionale dell’UE.

Pensa al CERT-UE come al **“118 digitale”** per il Parlamento Europeo, la Commissione Europea, la Corte di Giustizia, l’ENISA e tutte le agenzie UE: se c’è un incidente informatico che colpisce uno di questi enti, il CERT-UE interviene.

---

## Da chi è composto

Il CERT-UE è formato da un **gruppo di esperti di sicurezza informatica** provenienti dalle istituzioni e dagli organi dell’Unione.  
Questi esperti non lavorano isolati: mettono in comune competenze e risorse, così da avere un team di alto livello con una visione ampia sulle minacce.

---

## Cosa fa in pratica

1. **Raccoglie informazioni**
    
    - Monitora costantemente il panorama delle minacce e le vulnerabilità che riguardano le infrastrutture informatiche non classificate dell’UE.
        
    - Fonti: feed di intelligence, collaborazione con CSIRT nazionali, rapporti di sicurezza, partner industriali.
        
2. **Gestisce e analizza**
    
    - Valuta se un evento sospetto è un vero incidente.
        
    - Analizza malware, log di rete, indicatori di compromissione.
        
    - Produce rapporti tecnici e analisi dettagliate.
        
3. **Condivide le informazioni**
    
    - Diffonde alert, bollettini e best practice verso tutte le istituzioni e agenzie UE.
        
    - Lo scopo è garantire che **tutti** abbiano lo stesso livello di consapevolezza sulle minacce emergenti.
        
4. **Coordina le risposte agli incidenti**
    
    - Se un attacco colpisce più enti (es. Commissione e Parlamento), il CERT-UE coordina la gestione comune.
        
    - Fornisce assistenza tecnica diretta o mette in contatto le squadre dei vari enti per risolvere l’incidente in modo coordinato.
        
5. **Supporto operativo specializzato**
    
    - Quando serve, fornisce team di risposta rapida (es. esperti che si collegano ai sistemi compromessi per contenerli).
        
    - In alcuni casi coordina società esterne di cybersecurity che supportano con strumenti avanzati.
        

---

## Un esempio concreto

Immagina che un attacco **APT (Advanced Persistent Threat)** riesca a penetrare nei server email di un’agenzia UE.

- Il CERT-UE riceve la segnalazione e verifica la gravità.
    
- Analizza indicatori tecnici (es. IP sospetti, hash di file malevoli).
    
- Invia allerta a tutte le istituzioni UE: “attenzione, questo malware sta colpendo i nostri sistemi, questi sono gli indicatori da bloccare”.
    
- Coordina le contromisure tra i vari enti (patching, blocco degli indirizzi IP, analisi forense).
    

In questo modo un attacco che potrebbe dilagare viene contenuto velocemente.

---

## Perché è importante

Il CERT-UE assicura che l’UE:

- **non sia frammentata**: invece di ogni istituzione che si difende da sola, c’è una squadra comune che centralizza la risposta.
    
- **abbia un livello minimo di resilienza condivisa**, indipendente dalle capacità dei singoli enti.
    
- **possa reagire in modo rapido e uniforme** anche in scenari di attacchi coordinati a livello geopolitico.
    

---

### Sintesi rapida

- CERT-UE = “118 digitale” per le istituzioni UE.
    
- Raccolta, analisi, condivisione info → minacce, vulnerabilità, incidenti.
    
- Coordinamento risposta → livello inter-istituzionale.
    
- Supporto tecnico diretto e specializzato in caso di crisi.


---
# Recezione della Direttiva NIS in Italia

## Normativa di riferimento

- **Decreto Legislativo 18 maggio 2018, n. 65** → recepisce formalmente la Direttiva NIS.
    
- **DPCM 8 agosto 2019, art. 4** → definisce nel dettaglio le competenze, i ruoli e i meccanismi di coordinamento.
    

---

## Struttura organizzativa italiana

### 1. Autorità competenti NIS

Sono stati individuati **cinque ministeri**, ognuno responsabile di un settore strategico.  
Le Autorità NIS hanno tre compiti fondamentali:

- Vigilare sull’applicazione della direttiva.
    
- Esercitare poteri ispettivi.
    
- Applicare eventuali sanzioni.
    

### 2. CSIRT Italia

- Istituito presso l’**Agenzia per la Cybersicurezza Nazionale (ACN)**.
    
- È l’organo operativo: riceve le notifiche, gestisce e coordina la risposta agli incidenti.
    

### 3. Punto di contatto unico NIS

- Designato il **Dipartimento delle Informazioni per la Sicurezza (DIS)**.
    
- Ha il compito di fare da **interfaccia ufficiale** tra l’Italia e le istituzioni UE in materia NIS.
    

---

## Autorità NIS e settori di competenza

1. **Ministero dello Sviluppo Economico (MISE)**
    
    - **Energia** → sottosettori: energia elettrica, gas, petrolio.
        
    - **Infrastrutture digitali**.
        
    - **Servizi digitali** (es. cloud, marketplace, motori di ricerca).
        
2. **Ministero delle Infrastrutture e dei Trasporti (MIT)**
    
    - **Trasporti** → sottosettori:
        
        - Trasporto aereo.
            
        - Trasporto ferroviario.
            
        - Trasporto per vie d’acqua.
            
        - Trasporto su strada.
            
3. **Ministero dell’Economia e delle Finanze (MEF)**
    
    - In collaborazione con **Banca d’Italia** e **CONSOB**.
        
    - **Settore bancario**.
        
    - **Infrastrutture dei mercati finanziari** (es. sistemi di pagamento, clearing, mercati regolamentati).
        
4. **Ministero della Salute** (con Regioni e Province autonome di Trento e Bolzano, anche tramite le autorità sanitarie territoriali competenti)
    
    - **Settore sanitario**.
        
5. **Ministero dell’Ambiente e della Tutela del Territorio e del Mare** (con Regioni e Province autonome di Trento e Bolzano, anche tramite autorità territoriali competenti)
    
    - **Fornitura e distribuzione di acqua potabile**.
        

---

## Sintesi visiva (mappa mentale testuale)

- **Norma quadro**: D.lgs. 65/2018 + DPCM 2019.
    
- **Punto di contatto unico**: DIS.
    
- **Operatività incidenti**: CSIRT Italia (ACN).
    
- **Vigilanza settoriale**:
    
    - MISE → energia, infrastrutture digitali, servizi digitali.
        
    - MIT → trasporti (aereo, ferroviario, vie d’acqua, strada).
        
    - MEF (con Banca d’Italia e CONSOB) → banche e mercati finanziari.
        
    - Salute (con Regioni/Province) → settore sanitario.
        
    - Ambiente (con Regioni/Province) → acqua potabile.


---
# CSIRT Italia – Computer Security Incident Response Team Nazionale

![Pasted image 20250912230938](../../imgs/Pasted%20image%2020250912230938.png)
## Collocazione istituzionale

Il **CSIRT Italia** è istituito presso l’**Agenzia per la Cybersicurezza Nazionale (ACN)**.  
→ Questo lo rende parte integrante della struttura pubblica che coordina tutte le attività nazionali di cybersicurezza.

---

## Compiti principali

1. **Monitoraggio degli incidenti a livello nazionale**
    
    - Il CSIRT raccoglie e analizza informazioni sugli incidenti che colpiscono infrastrutture e servizi critici in Italia.
        
    - Ha una funzione di osservatorio costante: deve capire “chi sta attaccando cosa” e con quale intensità.
        
2. **Emissione di preallarmi, allerte e annunci**
    
    - Se individua una minaccia o un attacco in corso, diffonde comunicazioni ufficiali alle parti interessate (operatori, enti pubblici, aziende critiche).
        
    - Può pubblicare **alert tecnici**, indicatori di compromissione (IoC), bollettini su vulnerabilità.
        
3. **Intervento in caso di incidente**
    
    - In situazioni di emergenza, coordina o fornisce supporto operativo diretto.
        
    - Esempio: un attacco ransomware a un ospedale → il CSIRT guida l’analisi e suggerisce le contromisure immediate.
        
4. **Analisi dinamica dei rischi e degli incidenti**
    
    - Non si limita a “fotografare” i problemi, ma produce analisi in tempo reale sui rischi emergenti.
        
    - Identifica trend, campagne di attacco e possibili evoluzioni delle minacce.
        
5. **Sensibilizzazione situazionale (Situational Awareness)**
    
    - Fornisce alle istituzioni e agli operatori un quadro costante della situazione cyber nazionale.
        
    - Significa “dare consapevolezza”: non solo reagire agli attacchi, ma far capire ai responsabili IT e di sicurezza cosa sta accadendo nello scenario nazionale.
        
6. **Partecipazione alla rete dei CSIRT europei**
    
    - È collegato al network dei CSIRT istituito dalla Direttiva NIS.
        
    - Condivide informazioni con gli altri Stati membri e con il CERT-UE, per favorire il coordinamento europeo.
        

---

## Ruolo nella cooperazione

- Il CSIRT Italia non lavora solo con lo Stato, ma **collabora attivamente con il settore privato** (banche, aziende energetiche, trasporti, telco).
    
- Promuove **prassi comuni e standardizzate** in diversi ambiti:
    
    - Procedure di gestione degli incidenti.
        
    - Classificazione dei rischi e degli incidenti.
        
    - Tassonomie per condividere informazioni in modo uniforme.
        

➡️ In pratica, lavora per uniformare il linguaggio e le procedure tra pubblico e privato, così che quando si parla di un incidente cyber tutti lo descrivano nello stesso modo.

---

## Sintesi concettuale

Il **CSIRT Italia** è il “centro di pronto intervento nazionale” sugli incidenti cyber.

- **Dove sta** → presso l’ACN.
    
- **Cosa fa** → monitora, allerta, interviene, analizza, sensibilizza, coopera a livello UE.
    
- **Perché è cruciale** → assicura che l’Italia abbia un punto unico e coordinato per gestire le emergenze cyber, senza frammentazione.


---
# Destinatari della Direttiva NIS

La Direttiva NIS, insieme al decreto di attuazione italiano, identifica due grandi categorie di soggetti obbligati a rispettare le misure di sicurezza e gli obblighi di notifica:

---

## 1. Operatori di Servizi Essenziali (OES)

### Definizione

Gli **OES** sono i soggetti, pubblici o privati, che forniscono **servizi essenziali** per la società e l’economia.  
Essenziale significa: se il servizio si interrompe, ci sono impatti gravi sul funzionamento della vita quotidiana dei cittadini o sulla stabilità economica.

### Settori interessati

- **Sanità** (ospedali, cliniche, sistemi sanitari digitalizzati).
    
- **Energia** (produzione, trasmissione e distribuzione di elettricità, gas, petrolio).
    
- **Trasporti** (aereo, ferroviario, marittimo/vie d’acqua, stradale).
    
- **Settore bancario** (banche, intermediari finanziari).
    
- **Infrastrutture dei mercati finanziari** (borse, sistemi di pagamento, clearing).
    
- **Fornitura e distribuzione di acqua potabile**.
    
- **Infrastrutture digitali** (es. Internet Exchange Points, DNS, TLD).
    

➡️ In sintesi: sono gli operatori che **devono rimanere sempre attivi**, perché un’interruzione metterebbe a rischio cittadini, economia e sicurezza nazionale.

---

## 2. Digital Service Provider (DSP)

### Definizione

I **DSP** sono le persone giuridiche che forniscono **servizi della società dell’informazione**.  
Qui rientrano:

- **E-commerce** (marketplace online, grandi piattaforme di vendita).
    
- **Cloud computing** (provider di servizi cloud).
    
- **Motori di ricerca**.
    
- (in senso esteso anche social network, financial providers online).
    

### Requisiti territoriali

Il DSP deve avere:

- **Sede principale o sociale** in uno Stato UE, oppure
    
- un **rappresentante designato** sul territorio nazionale.
    

### Esclusioni

Non rientrano nella normativa i DSP che sono:

- **Micro imprese** (< 10 dipendenti e < 2 milioni di fatturato annuo).
    
- **Piccole imprese** (< 50 dipendenti o fatturato < 10 milioni annui).
    

➡️ La logica è: concentrare l’obbligo su soggetti di dimensioni significative, che hanno un impatto reale sulla società e sull’economia.

---

## Differenza chiave tra OES e DSP

- **OES** → settori critici “tradizionali” (energia, acqua, trasporti, sanità, finanza). Se falliscono, la società si blocca.
    
- **DSP** → servizi digitali “nativi online” (cloud, e-commerce, motori di ricerca). Se falliscono, colpiscono economia digitale e mercati globali.
    

Entrambe le categorie hanno l’obbligo di:

1. Implementare misure di sicurezza.
    
2. Notificare gli incidenti al CSIRT Italia.
    

---

## Esempio pratico

- **OES**: un ospedale colpito da ransomware → obbligo di notifica, piani di sicurezza, collaborazione con CSIRT.
    
- **DSP**: un provider di cloud computing vittima di un attacco DDoS → obbligo di notifica, contromisure di resilienza.


---
# Settori e Sottosettori destinatari NIS

## 1. Operatori di Servizi Essenziali (OES)

### **Energia**

- **Elettricità**: produttori, gestori della trasmissione (TSO), gestori della distribuzione (DSO).
    
- **Gas**: imprese del gas naturale, gestori di stoccaggi e terminali GNL.
    
- **Petrolio**: operatori di estrazione, raffinazione, trasporto e distribuzione.
    

### **Trasporti**

- **Trasporto aereo**: compagnie aeree, gestori aeroportuali, fornitori di servizi di navigazione aerea (ATC).
    
- **Trasporto ferroviario**: gestori dell’infrastruttura ferroviaria, imprese ferroviarie.
    
- **Trasporto per vie d’acqua**: gestori portuali, autorità portuali, compagnie di navigazione, sistemi VTS (Vessel Traffic Services).
    
- **Trasporto su strada**: autorità stradali, gestori ITS (Intelligent Transport Systems).
    

### **Settore bancario**

- Enti creditizi (banche).
    

### **Infrastrutture dei mercati finanziari**

- Sedi di negoziazione.
    
- Controparti centrali (CCP).
    
- Altri sistemi che supportano il funzionamento dei mercati finanziari.
    

### **Sanità**

- Ospedali pubblici e privati.
    
- Cliniche.
    
- Prestatori di assistenza sanitaria essenziali.
    

### **Fornitura e distribuzione di acqua potabile**

- Imprese che forniscono e distribuiscono acqua destinata al consumo umano.
    
- Esclusioni: piccole reti locali senza rilevanza critica.
    

### **Infrastrutture digitali**

- Internet Exchange Points (IXP).
    
- Domain Name System (DNS).
    
- Top Level Domain registries (TLD).
    

---

## 2. Digital Service Provider (DSP)

- **Marketplace online** (es. Amazon, eBay, Zalando).
    
- **Servizi di cloud computing** (es. AWS, Azure, Google Cloud).
    
- **Motori di ricerca** (es. Google, Bing).
    

➡️ **Esclusioni**: micro e piccole imprese (meno di 50 dipendenti o < 10 milioni € di fatturato annuo).

---

## Sintesi tabellare

|Categoria|Settore|Sottosettori / Servizi|
|---|---|---|
|**OES**|Energia|Elettricità, Gas, Petrolio|
||Trasporti|Aereo, Ferroviario, Vie d’acqua, Strada|
||Bancario|Enti creditizi|
||Mercati finanziari|Sedi di negoziazione, CCP|
||Sanità|Ospedali, cliniche, prestatori essenziali|
||Acqua potabile|Fornitori e distributori|
||Infrastrutture digitali|IXP, DNS, TLD|
|**DSP**|Servizi digitali|Marketplace online, Cloud computing, Motori di ricerca|

---

## Idea-chiave

- Gli **OES** coprono i settori critici “fisici” e infrastrutturali.
    
- I **DSP** coprono i servizi digitali che abilitano l’economia online.
    
- Tutti hanno obbligo di sicurezza e di notifica degli incidenti.


---
## Direttiva NIS, tutti gli attori:

![Pasted image 20250912231314](../../imgs/Pasted%20image%2020250912231314.png)

Ecco uno schema di insieme che rappresenta tutti gli attori che partecipano alla direttiva NIS e al suo recepimento italiano.
# Come si legge il grafico

## 1) Nucleo centrale (ruolo operativo nazionale)

- **CSIRT Italia**: è il centro di pronto intervento. Riceve le notifiche di incidente, monitora, allerta, coordina la risposta tecnica.
    
- **Punto di Contatto Unico (DIS)**: è il canale ufficiale verso l’UE. Non fa l’IR operativo: cura lo **scambio formale di informazioni** tra Italia e istituzioni europee.
    

👉 Pensa: **CSIRT = “soccorso tecnico”**, **DIS = “canale diplomatico”**.

---

## 2) Corona azzurra (attori governativi NIS)

- **Autorità competenti NIS**: i 5 ministeri “per settore” che **vigilano, ispezionano e sanzionano**:
    
    - Sviluppo Economico → **energia**, **infrastrutture digitali**, **servizi digitali** (DSP/FSD).
        
    - Infrastrutture e Trasporti → **trasporti** (aereo, ferroviario, vie d’acqua, strada).
        
    - Economia e Finanze (con **Banca d’Italia** e **CONSOB**) → **bancario** e **infrastrutture dei mercati finanziari**.
        
    - Salute (con **Regioni/Province autonome**) → **sanitario**.
        
    - Ambiente/Tutela del territorio e del mare (con **Regioni/Province autonome**) → **acqua potabile**.
        
- **Comitato Tecnico di Raccordo**: tavolo tecnico che **allinea** CSIRT, DIS e Autorità competenti per avere criteri e procedure comuni.
    

👉 Qui si decide **chi controlla chi**, **come** si applicano gli obblighi e **come** si coordina il Paese.

---

## 3) Spicchio verde (servizi interessati: i destinatari)

- **Operatori di Servizi Essenziali (OSE)**: settori **energia, trasporti, bancario, mercati finanziari, sanitario, acqua potabile, infrastrutture digitali**.
    
- **Fornitori di Servizi Digitali (FSD)** = **DSP**: **cloud**, **motori di ricerca**, **e-commerce** (e servizi digitali affini).
    

👉 Questi sono i **soggetti obbligati**: devono gestire il rischio, adottare misure di sicurezza e **notificare** gli incidenti.

---

## 4) Spicchio blu scuro (cooperazione europea)

- **Rete CSIRT UE**: collega i CSIRT nazionali tra loro e con il **CERT-UE**, per scambio rapido di indicatori/allerte.
    
- **Gruppo di Cooperazione**: livello **strategico-politico**; armonizza approcci, piani, linee guida tra Stati.
    
- **Altri Stati Membri**: l’ecosistema europeo che riceve e fornisce informazioni in caso di incidenti transfrontalieri.
    

👉 Questo garantisce che un evento in uno Stato **non resti isolato** ma venga gestito in modo **coordinato** a livello UE.

---

# Flusso tipico “end-to-end” (esempio concreto)

1. **Incidente** su un **cloud provider** (DSP) o su un **ospedale** (OSE).
    
2. L’operatore **notifica** al **CSIRT Italia** (obbligo NIS).
    
3. Il **CSIRT**:
    
    - emette **preallarmi/IoC** agli altri operatori potenzialmente impattati;
        
    - coordina **contenimento e ripristino**;
        
    - informa le **Autorità competenti** del settore.
        
4. L’**Autorità competente** valuta profilo regolatorio: **ispezioni**, **prescrizioni**, eventuali **sanzioni**.
    
5. Il **DIS** (Punto di contatto) **scambia informazioni** con l’UE; il **CSIRT** alimenta la **Rete CSIRT UE**; se serve, il **Gruppo di Cooperazione** definisce indirizzi comuni.
    
6. Gli **altri Stati membri** ricevono l’allerta tecnica e si **preparano/aggiornano** le difese.
    

---

# Perché è disegnato così

- **Centro** = funzione operativa (CSIRT) + interfaccia UE (DIS).
    
- **Corona azzurra** = **governance nazionale** (chi vigila e coordina le politiche).
    
- **Verde** = **destinatari** soggetti agli obblighi.
    
- **Blu scuro** = **rete europea** che evita frammentazione e ritardi informativi.


---
# NIS 2 – La revisione della Direttiva NIS

![Pasted image 20250912231455](../../imgs/Pasted%20image%2020250912231455.png)

## Perché serve una revisione

La **NIS 1** (2016) è stata un passo avanti enorme, ma con il tempo sono emersi limiti:

- Troppa differenza di recepimento tra Stati Membri (alcuni molto rigidi, altri molto lenti).
    
- La distinzione **OES/DSP** creava confusione e disparità di trattamento.
    
- Lo scenario delle minacce si è evoluto: supply chain attacks, ransomware su scala nazionale, interconnessione sempre più stretta tra pubblico e privato.
    

Da qui nasce la **NIS 2**, approvata a livello politico nel 2022 e attualmente in fase di recepimento nei singoli Stati.

---

## Novità principali introdotte da NIS 2

### 1. Eliminazione distinzione OES/DSP

- Sparisce la vecchia divisione **Operatori di Servizi Essenziali (OES)** e **Digital Service Provider (DSP)**.
    
- Le aziende sono ora classificate in due categorie:
    
    - **Essenziali**: servizi di impatto critico altissimo (es. energia, sanità, finanza, trasporti, infrastrutture digitali).
        
    - **Importanti**: servizi che non bloccano l’intero Paese ma hanno comunque impatto significativo.
        
- Differenza pratica:
    
    - **Essenziali** → vigilanza **più stringente e continua**.
        
    - **Importanti** → vigilanza **a posteriori o su campione**.
        

---

### 2. Ampliamento dell’ambito di applicazione

La NIS 2 coinvolge molti più settori e servizi rispetto alla NIS 1:

- **Produzione farmaceutica**.
    
- **Dispositivi medici**.
    
- **Prodotti chimici critici**.
    
- **Settore alimentare**.
    
- **Gestione acque reflue**.
    
- **Gestione rifiuti**.
    
- **Servizi postali**.
    
- **Pubblica amministrazione** (novità cruciale: anche le PA devono adeguarsi).
    

➡️ In pratica: la direttiva abbraccia non solo le infrastrutture classiche, ma anche le **filiera industriale** e i **servizi quotidiani**.

---

### 3. Rafforzamento della supply chain security

Uno dei punti chiave della NIS 2 è la **protezione lungo tutta la catena di approvvigionamento**.

- Un’azienda non deve guardare solo alla propria sicurezza, ma anche a quella dei suoi fornitori e partner.
    
- Obbligo di valutare e gestire i rischi derivanti da terze parti (supply chain, subappalti, fornitori di software e hardware).
    
- Significa che, ad esempio, un ospedale non può limitarsi a mettere in sicurezza i propri server: deve verificare che anche il fornitore del software gestionale rispetti standard adeguati.
    

---

## Obiettivi strategici della NIS 2

1. **Uniformare** i requisiti minimi tra Stati Membri, riducendo differenze nazionali.
    
2. **Estendere** la copertura a settori cruciali prima esclusi.
    
3. **Rendere più chiara** la classificazione dei destinatari (essenziali vs importanti).
    
4. **Alzare l’asticella** della sicurezza anche nel privato e lungo tutta la filiera produttiva.
    

---

## Situazione attuale

- La revisione è in fase finale di valutazione da parte della Commissione Europea.
    
- Non c’è ancora certezza assoluta sui tempi di pubblicazione definitiva e recepimento in Italia, ma la direzione è chiara: entro pochi anni, la NIS 2 sarà pienamente vincolante.
    

---

## Sintesi visiva

- **NIS 1 (2016)**: OES + DSP, ambito limitato, focus su settori essenziali.
    
- **NIS 2 (2022)**: Essenziali + Importanti, ambito ampliato, focus anche su supply chain e PA, vigilanza rafforzata.