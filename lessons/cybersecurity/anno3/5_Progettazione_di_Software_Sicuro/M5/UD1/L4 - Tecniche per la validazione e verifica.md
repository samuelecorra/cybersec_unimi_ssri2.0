# **Lezione 4: Tecniche per la validazione e verifica**

---

### 1. Il testing lungo il ciclo di sviluppo del software

Il testing non deve essere considerato esclusivamente come una fase finale del ciclo di vita del software, ma come un’attività che dovrebbe essere applicata lungo tutto il processo di sviluppo. L’estensione del testing a tutte le fasi del ciclo di vita produce benefici non soltanto in termini di sicurezza, ma anche di affidabilità, usabilità e qualità complessiva del sistema.

In questa prospettiva più ampia, il testing rientra nel concetto generale di **validazione e verifica** (Verification and Validation, V&V), talvolta indicato anche come controllo del software o software auditing. Questo approccio considera l’intero processo di sviluppo come soggetto a controlli sistematici.

---

### 2. Validazione e verifica: definizioni fondamentali

La distinzione tra validazione e verifica rappresenta uno dei concetti più importanti dell’ingegneria del software.

La **validazione** consiste nel controllare che il software sia corretto rispetto ai bisogni dell’utente. Essa verifica che il sistema soddisfi i requisiti dell’utente, spesso espressi in forma informale. In termini sintetici, la validazione risponde alla domanda:

> Stiamo costruendo il sistema giusto?  
> (_building the right system_)

La **verifica**, invece, consiste nel controllare che l’implementazione corrisponda alla specifica tecnica definita durante la progettazione. Essa risponde alla domanda:

> Stiamo costruendo il sistema nel modo giusto?  
> (_building the system right_)

Questa distinzione è cruciale perché un sistema può essere verificato correttamente rispetto alle specifiche ma non validato rispetto ai bisogni reali dell’utente.

---

### 3. Importanza della validazione del progetto

Gli errori introdotti nella fase di progettazione hanno un impatto estremamente elevato sul costo complessivo del software. Correggere difetti progettuali dopo l’implementazione o addirittura dopo il rilascio richiede sforzi molto maggiori rispetto alla loro individuazione precoce.

Pertanto, trovare ed eliminare bug fin dalle prime fasi del ciclo di vita è fondamentale.

Tuttavia esistono anche difficoltà significative:

- la validazione dei progetti è poco supportata da strumenti automatici,
    
- spesso si possono fornire soltanto linee guida procedurali,
    
- gli strumenti disponibili, come i metodi formali, richiedono investimenti elevati in termini di competenze e risorse.
    

---

### 4. Linee guida per la validazione del progetto

Alcune pratiche risultano particolarmente efficaci nella validazione dei progetti software.

Una prima tecnica consiste nella **revisione del progetto**, che dovrebbe essere effettuata indipendentemente dal grado di formalità del modello progettuale prima di procedere all’implementazione. Strumenti utili includono diagrammi di flusso e altri diagrammi strutturali. È inoltre fondamentale adottare una prospettiva orientata alla sicurezza, considerando esplicitamente cosa potrebbe fare un utente malevolo.

Un’altra pratica importante è l’uso di **revisori esterni**, cioè persone non direttamente coinvolte nel progetto. Questo aumenta la probabilità di individuare errori grazie a punti di vista indipendenti.

Infine, risultano utili **checklist** e **scorecard**, cioè strumenti strutturati che guidano la revisione attraverso una lista di controlli, talvolta accompagnati da sistemi di punteggio per valutare la qualità del progetto.

---

### 5. Uso dei metodi formali nella validazione

I **metodi formali** rappresentano tecniche avanzate per la validazione e verifica dei sistemi software. Essi prevedono la costruzione di un modello matematico del sistema utilizzando linguaggi con una forte base logico-matematica.

Esempi di metodi formali includono:

- Z
    
- Abstract State Machines
    
- B
    

In contesti critici, come il controllo di centrali nucleari, la logica del sistema viene formalizzata e dimostrata matematicamente per garantire che eventi pericolosi producano sempre le azioni di sicurezza previste.

Nonostante la loro potenza, i metodi formali presentano costi elevati e richiedono competenze specialistiche, limitandone l’adozione su larga scala.

---

### 6. Tecniche di analisi delle specifiche formali

Quando si utilizzano metodi formali, diventano possibili diverse tecniche di analisi.

Una prima categoria riguarda le **analisi sintattiche e di conformità**, che verificano se la specifica soddisfa proprietà fondamentali come:

- completezza, cioè la definizione del comportamento per ogni evento possibile,
    
- consistenza, cioè l’assenza di ambiguità o contraddizioni.
    

Una seconda categoria riguarda la **verifica formale di proprietà**, che consiste nel dimostrare matematicamente che determinate proprietà sono implicate dal modello.

Infine, è possibile effettuare **simulazioni ed esecuzioni di scenari**, anche in forma grafica e interattiva, per verificare che la specifica rappresenti correttamente i requisiti dell’utente.

---

### 7. Validazione e verifica a livello di implementazione

Le tecniche di V&V applicate al codice possono essere suddivise in due categorie principali: tecniche statiche e tecniche dinamiche.

Le **tecniche statiche** raccolgono informazioni sul software senza eseguirlo. Tra queste rientrano:

- revisioni e walkthrough,
    
- ispezioni del codice,
    
- analisi statica automatica,
    
- verifica formale della correttezza dei programmi.
    

Le **tecniche dinamiche**, invece, analizzano il software durante l’esecuzione. Esse includono:

- testing mediante scenari,
    
- random testing,
    
- profiling per la valutazione delle prestazioni.
    

Questa distinzione è fondamentale perché le tecniche statiche permettono di individuare problemi prima dell’esecuzione, mentre quelle dinamiche permettono di osservare il comportamento reale del sistema.

---

### 8. Analisi statica del codice

L’analisi statica del codice utilizza tecniche di analisi del flusso di controllo e del flusso dei dati, già impiegate dai compilatori per ottimizzazioni, ma applicabili anche alla valutazione della qualità e della sicurezza del software.

Esempi di problemi individuabili includono:

- istruzioni non raggiungibili,
    
- variabili non inizializzate,
    
- variabili dichiarate ma mai utilizzate,
    
- variabili scritte ma mai lette,
    
- errori nell’uso degli array.
    

Esistono diversi strumenti automatici per l’analisi statica, tra cui:

- RATS (Rough Auditing Tool for Security),
    
- SPLINT,
    
- FLAWFINDER.
    

Questi strumenti sono particolarmente utili nel contesto della sicurezza, poiché permettono di individuare vulnerabilità potenziali prima dell’esecuzione del software.

---

### 9. Verifica formale dei programmi

È possibile dimostrare formalmente la correttezza di un programma utilizzando tecniche logiche. Un software è considerato corretto quando realizza esattamente la specifica, che deve essere espressa in forma formale.

La logica più utilizzata è la **logica di Hoare**, basata su precondizioni e postcondizioni che descrivono il comportamento del programma.

La dimostrazione può essere condotta manualmente con supporto di strumenti automatici oppure mediante strumenti completamente automatici, come i **model checker**.

Esempi di strumenti includono:

- BLAST
    
- Key
    
- SLAM
    

Queste tecniche sono molto potenti ma richiedono uno sforzo significativo nella definizione delle specifiche e nella prova di correttezza.

---

### 10. Ispezione del software

L’**ispezione** rappresenta una tecnica manuale ma estremamente efficace per individuare difetti. Tra i modelli più noti vi è il **modello Fagan**, caratterizzato da un processo formale e strutturato.

L’ispezione può essere applicata non solo al codice ma anche a requisiti e progettazione.

Come illustrato nella slide grafica (pagina 7), l’ispezione coinvolge ruoli specifici:

- **Moderatore**, che coordina il processo e proviene spesso da un progetto diverso.
    
- **Lettori o addetti al test**, che analizzano il codice alla ricerca di difetti.
    
- **Autore**, che partecipa passivamente e risponde alle domande.
    

Un aspetto organizzativo importante riguarda la gestione dei difetti trovati. I difetti individuati durante l’ispezione non devono essere utilizzati per valutazioni personali, per evitare che gli sviluppatori siano incentivati a nascondere errori. Al contrario, difetti scoperti durante il testing successivo possono essere considerati nelle valutazioni.

---

### 11. Checklist e motivi dell’efficacia dell’ispezione

Le checklist rappresentano strumenti fondamentali per l’ispezione. Un esempio proveniente dalla NASA prevede liste di controlli suddivise in categorie come funzionalità, uso dei dati, controllo, connessioni, calcolo e manutenzione.

Domande tipiche includono:

- ogni modulo ha una singola funzione?
    
- il codice corrisponde al progetto?
    
- i commenti sono sufficienti?
    

L’ispezione risulta efficace perché:

- segue un processo formale con tracciamento dei risultati,
    
- utilizza checklist che migliorano nel tempo,
    
- introduce dinamiche sociali che incentivano la qualità,
    
- può essere applicata anche a programmi incompleti.
    

Tra i limiti principali vi sono la scalabilità limitata e la difficoltà di applicazione incrementale.

---

### 12. Analisi dinamica

L’analisi dinamica comprende tecniche che osservano il comportamento del software durante l’esecuzione.

I **runtime checkers** analizzano l’esecuzione per individuare problemi come violazioni di memoria o deadlock prima dell’interazione con il sistema operativo. Esempi includono LibSafe, PurifyPlus e strumenti Immunix.

I **profiler** evidenziano comportamenti anomali relativi alle prestazioni.

I **penetration testing tools** effettuano scansioni del software utilizzando modelli di vulnerabilità noti, rappresentando un collegamento diretto con la sicurezza applicativa.

---

### Sintesi della lezione

In questa lezione sono stati introdotti i concetti di validazione e verifica, distinguendo il controllo rispetto ai bisogni dell’utente dalla verifica della conformità alla specifica. È stata evidenziata l’importanza della validazione precoce del progetto e sono state presentate linee guida operative, incluse revisioni, checklist e revisori esterni.

Sono state inoltre analizzate tecniche avanzate come i metodi formali e la verifica formale dei programmi, insieme alle tecniche di validazione e verifica dell’implementazione, suddivise in analisi statica e dinamica. Particolare attenzione è stata dedicata all’ispezione del software e agli strumenti automatici di analisi.

Nel complesso, la lezione mostra come il testing sia solo una componente di un insieme più ampio di tecniche di qualità del software.