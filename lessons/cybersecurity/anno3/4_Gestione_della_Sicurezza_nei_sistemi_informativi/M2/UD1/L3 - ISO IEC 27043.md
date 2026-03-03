# **Lezione 3: ISO/IEC 27043**

### 1. Introduzione allo standard ISO/IEC 27043:2015

Lo standard ISO/IEC 27043:2015 rappresenta un riferimento fondamentale nell’ambito delle investigazioni digitali, in quanto definisce i principi generali e i processi che costituiscono un’indagine informatica completa. L’obiettivo principale dello standard è promuovere l’adozione di best practices forensi e fornire linee guida per l’acquisizione, l’analisi e la gestione delle prove digitali lungo l’intero ciclo investigativo.

A differenza di altri standard che si concentrano su specifiche fasi operative, la ISO/IEC 27043 descrive tutte le fasi dell’attività investigativa, includendo sia le attività precedenti all’identificazione dell’incidente (fase pre-incidente) sia quelle successive (fase post-incidente).

L’approccio metodologico adottato è di tipo top-down: vengono introdotti inizialmente principi e processi a livello generale, che vengono poi progressivamente raffinati con maggiore dettaglio. I processi descritti sono volutamente definiti in modo astratto per renderli applicabili a differenti tipologie di indagini digitali e a diversi contesti operativi.

---

### 2. Ambito delle investigazioni digitali

Le investigazioni digitali si applicano ogni volta che esistono prove di natura digitale, indipendentemente dall’ambito giuridico o organizzativo in cui l’indagine viene svolta. Esse possono quindi essere utilizzate in contesti penali, civili o stragiudiziali.

I processi descritti nello standard sono applicabili a qualsiasi dispositivo digitale, tra cui:

- Personal computer e laptop
    
- Server e infrastrutture di rete
    
- Database e archivi digitali
    
- Dispositivi mobili come smartphone e tablet
    
- Altri dispositivi elettronici contenenti dati
    

Questa ampia applicabilità riflette la crescente diffusione delle tecnologie digitali nella società e la conseguente necessità di strumenti investigativi standardizzati.

---

### 3. Aspetti legali: la figura dell’Expert Witness

Un elemento rilevante nelle investigazioni digitali è la figura dell’expert witness, ovvero l’esperto che supporta il giudice nell’analisi delle prove tecniche. In alcune giurisdizioni questa figura può essere chiamata a testimoniare in tribunale e il suo parere può essere accettato sulla base delle sue competenze, esperienza e formazione.

Perché il parere di un esperto sia considerato valido, devono essere soddisfatti alcuni requisiti fondamentali:

- Le tecniche utilizzate devono essere state testate
    
- Devono essere sottoposte a revisione scientifica e pubblicazione
    
- Devono essere disciplinate da norme che ne regolano l’applicazione
    
- Devono essere largamente accettate dalla comunità scientifica
    
- Deve essere noto, se possibile, il tasso di errore delle tecniche utilizzate
    

Questi criteri derivano dal metodo scientifico e garantiscono l’affidabilità delle conclusioni forensi.

---

### 4. Ammissibilità della prova digitale

L’ammissibilità della prova digitale in ambito legale dipende principalmente da due fattori chiave: pertinenza e autenticità.

La pertinenza riguarda la rilevanza della prova rispetto ai fatti in esame. La prova deve contribuire in modo significativo alla comprensione del caso.

L’autenticità richiede la dimostrazione che la prova non sia stata alterata o manipolata. Ad esempio, nel caso di un’immagine digitale, occorre garantire la provenienza, l’integrità dei metadati durante l’acquisizione e l’affidabilità del processo di estrazione.

Questi requisiti rendono evidente l’importanza delle procedure forensi corrette e della documentazione delle attività svolte.

---

### 5. Classificazione dei processi di investigazione

Lo standard ISO/IEC 27043 distingue diverse classi di processi investigativi, ciascuna con obiettivi specifici.

Le principali classi sono:

- Processi readiness
    
- Processi di pianificazione
    
- Processi di acquisizione
    
- Processi investigativi
    
- Processi concorrenti
    

Le prime quattro classi seguono una sequenza logica, mentre i processi concorrenti possono essere eseguiti parallelamente agli altri.

Questa classificazione consente di organizzare in modo strutturato tutte le attività investigative.

---

### 6. Processi readiness (fase pre-incidente)

I processi readiness riguardano la preparazione dell’organizzazione prima che si verifichi un incidente. Essi hanno lo scopo di garantire che i sistemi siano configurati correttamente e che il personale disponga delle competenze necessarie per affrontare un’indagine.

Gli obiettivi principali includono:

- Massimizzare l’efficacia organizzativa
    
- Ridurre tempi e costi delle indagini
    
- Minimizzare interferenze nei processi aziendali
    
- Preservare o migliorare la sicurezza dei sistemi
    

Questa classe di processi è facoltativa e ogni organizzazione può decidere se implementarla.

Le attività comprendono la definizione degli scenari, l’individuazione delle fonti di prova, la pianificazione della raccolta pre-incidente, la gestione dei dati potenzialmente rilevanti, la definizione dell’architettura dei sistemi e la valutazione dei risultati.

L’importanza dei processi readiness risiede nella possibilità di anticipare problemi investigativi e migliorare l’efficienza operativa.

---

### 7. Processi di inizializzazione

I processi di inizializzazione riguardano la fase iniziale dell’indagine digitale e comprendono:

- Rilevamento dell’incidente
    
- Prima risposta
    
- Pianificazione
    
- Preparazione
    

Il rilevamento dell’incidente consiste nell’identificare relazioni tra sistemi informativi e sistemi di rilevamento esterni, come IDS, IPS, log e sistemi di monitoraggio delle modifiche.

Questa fase include tre sotto-processi: segnalazione, classificazione e descrizione dell’incidente.

La prima risposta dipende dalla gravità dell’evento e può includere azioni come la disconnessione dalla rete. Tuttavia, è fondamentale evitare operazioni che possano compromettere l’integrità della prova, come lo spegnimento improprio dei dispositivi.

La pianificazione mira a definire strategie operative, garantire risorse adeguate e massimizzare l’utilizzo delle prove digitali minimizzando costi e interferenze.

La preparazione riguarda la disponibilità di attrezzature, infrastrutture, risorse umane, formazione e documentazione necessarie per l’indagine.

Questa fase rende l’investigatore più efficiente nelle fasi successive di acquisizione.

---

### 8. Processi di acquisizione

I processi di acquisizione rappresentano la fase operativa in cui vengono individuate e raccolte le prove digitali.

Gli obiettivi principali sono:

- Identificare potenziali prove digitali
    
- Valutare la possibilità di acquisizione
    
- Valutare il trasporto e lo stoccaggio delle prove
    

L’identificazione delle prove è particolarmente critica perché la mancata individuazione iniziale può rendere impossibile il recupero successivo, soprattutto in ambienti dinamici come reti o cloud.

Una volta individuate le prove, devono essere raccolte mantenendone l’integrità, poiché errori procedurali possono renderle inutilizzabili.

L’acquisizione consente l’analisi successiva e deve rispettare procedure rigorose, incluse verifiche di integrità tramite funzioni hash, come previsto anche dalla ISO/IEC 27037.

Il trasporto delle prove può essere fisico o elettronico e deve preservare integrità e catena di custodia, utilizzando eventualmente crittografia e firme digitali.

Lo stoccaggio è necessario quando l’analisi non avviene immediatamente o quando è richiesta una custodia temporanea. Occorre prevenire danni causati da condizioni ambientali o malfunzionamenti.

Il mantenimento dell’integrità e della catena di custodia è un requisito fondamentale durante tutto il processo.

---

### 9. Processi investigativi

I processi investigativi hanno lo scopo di determinare il valore delle prove digitali raccolte.

Essi comprendono:

- Esame e analisi delle prove
    
- Interpretazione dei risultati
    
- Segnalazione
    
- Presentazione
    
- Chiusura delle indagini
    

Questa fase rappresenta il momento in cui i dati acquisiti vengono trasformati in informazioni utili per l’indagine e per eventuali procedimenti giudiziari.

---

### 10. Processi concorrenti

I processi concorrenti possono intervenire in parallelo a qualsiasi classe di processi investigativi e includono:

- Ottenimento delle autorizzazioni
    
- Documentazione
    
- Gestione del flusso informativo
    
- Preservazione della catena di custodia
    
- Protezione delle prove digitali
    
- Interazione con l’indagine fisica
    

La natura concorrente di questi processi significa che non seguono un ordine sequenziale ma supportano continuamente l’indagine.

Essi garantiscono coerenza, legalità e tracciabilità dell’intero processo investigativo.

---

### 11. Sintesi finale

La lezione ha introdotto lo standard ISO/IEC 27043, che definisce i principi generali e i processi completi delle investigazioni digitali. Sono state analizzate le classi di processi investigative, dalla fase pre-incidente fino alla presentazione delle prove, evidenziando l’importanza della preparazione organizzativa e della corretta gestione delle evidenze.

È stato inoltre sottolineato il ruolo degli aspetti legali, dell’ammissibilità della prova e della figura dell’expert witness.

Il fine ultimo delle investigazioni digitali rimane la raccolta, identificazione, classificazione e interpretazione delle prove digitali in modo affidabile e ripetibile.