# **Lezione 1: Proprietà delle Specifiche**

### 1. Introduzione alla specifica dei requisiti nel contesto della progettazione sicura

Nel processo di sviluppo del software, e in particolare nella progettazione di software sicuro, la fase di specifica dei requisiti rappresenta uno dei momenti più critici dell’intero ciclo di vita. La descrizione iniziale di un sistema software avviene infatti in termini di requisiti, cioè attraverso l’individuazione delle caratteristiche che il sistema deve possedere per soddisfare le esigenze del committente e degli utenti finali.

La specifica dei requisiti costituisce una descrizione completa e non ambigua dei requisiti stessi. Essa ha lo scopo fondamentale di definire **cosa** il sistema deve fare, senza entrare nel merito di **come** tali funzionalità verranno implementate. Questa distinzione tra cosa e come è essenziale perché consente di separare la fase di analisi dei bisogni dalla fase di progettazione tecnica, riducendo il rischio di errori architetturali prematuri.

I requisiti possono essere classificati in tre categorie principali:

- requisiti funzionali, che descrivono i servizi e le funzionalità offerte dal sistema;
    
- requisiti non funzionali, che riguardano proprietà qualitative come prestazioni, sicurezza, affidabilità e usabilità;
    
- requisiti relativi al processo e alla manutenzione, che includono vincoli organizzativi, normativi e di evoluzione del sistema nel tempo.
    

Nel contesto della sicurezza software, i requisiti non funzionali assumono un ruolo particolarmente rilevante, poiché includono proprietà quali confidenzialità, integrità, disponibilità e resilienza agli attacchi.

---

### 2. La specifica come contratto tra committente e produttore

Una specifica dei requisiti non deve essere considerata solamente un documento tecnico, ma piuttosto un vero e proprio accordo contrattuale tra il produttore del servizio software e il committente. Questo significa che la specifica stabilisce formalmente cosa ci si aspetta dal sistema e costituisce il riferimento per la validazione e l’accettazione finale.

La rilevanza di questa fase è confermata da studi empirici. Secondo dati riportati nella letteratura, una percentuale molto elevata di progetti software — circa il 73% — viene abbandonata oppure non soddisfa le aspettative principalmente a causa di errori nei requisiti. Le cause principali di fallimento includono:

- difetti iniziali nei requisiti;
    
- mancato coinvolgimento degli utenti o degli stakeholder;
    
- incapacità di gestire le variazioni dei requisiti durante lo sviluppo.
    

Questi fattori sono particolarmente critici nei sistemi software sicuri, dove un errore nei requisiti può tradursi direttamente in vulnerabilità sfruttabili.

---

### 3. Processo di sviluppo della specifica dei requisiti

La costruzione di una specifica richiede un processo iterativo e cooperativo. Non si tratta di un’attività lineare, ma di un ciclo di raffinamento progressivo nel quale i requisiti vengono identificati, discussi, modificati e migliorati attraverso l’interazione con tutti gli stakeholder coinvolti nel sistema.

Gli stakeholder includono:

- committenti;
    
- utenti finali;
    
- sviluppatori;
    
- esperti di dominio;
    
- responsabili della sicurezza.
    

Il processo di specifica richiede inoltre l’impiego di linguaggi formali o semiformali. L’utilizzo di formalismi consente infatti di ridurre ambiguità e interpretazioni errate, migliorando la precisione e la verificabilità dei requisiti.

Nel contesto della sicurezza software, i metodi formali assumono un’importanza ancora maggiore, poiché permettono di dimostrare proprietà di sicurezza prima dell’implementazione.

---

### 4. Qualità delle specifiche: caratteristiche fondamentali

Una specifica di qualità deve soddisfare una serie di proprietà essenziali che garantiscono la correttezza del processo di sviluppo e la soddisfazione dei requisiti del cliente. Le principali caratteristiche qualitative sono:

- chiarezza;
    
- non ambiguità;
    
- consistenza;
    
- completezza (interna ed esterna);
    
- incrementalità;
    
- comprensibilità.
    

Queste proprietà sono fondamentali per evitare errori progettuali e per garantire la sicurezza del sistema fin dalle fasi iniziali.

---

### 5. Chiarezza delle specifiche

La chiarezza implica che la specifica descriva in modo il più possibile preciso e comprensibile i termini, le operazioni e i concetti coinvolti.

Un requisito poco chiaro genera interpretazioni diverse tra sviluppatori e committenti. Ad esempio, descrivere la selezione di un’area di documento senza definire cosa si intenda esattamente per “area su cui si vuole operare” introduce ambiguità concettuali che possono portare a implementazioni non coerenti.

Nel software sicuro, la mancanza di chiarezza può portare a errori di implementazione che si trasformano in vulnerabilità, ad esempio nei controlli di accesso o nella gestione delle autorizzazioni.

---

### 6. Non ambiguità delle specifiche

Una specifica non ambigua non deve consentire interpretazioni multiple. L’ambiguità è una delle principali fonti di errori nei sistemi software.

Un esempio riguarda un requisito in cui un messaggio deve essere triplicato e inviato su tre canali, con una politica di decisione “2 su 3”. Senza chiarire se il ricevente debba attendere tutte le copie prima della decisione, il comportamento del sistema rimane indeterminato.

Un altro esempio è il requisito di ordinare una sequenza di numeri senza specificare se l’ordinamento debba essere crescente o decrescente. In questo caso, il comportamento finale dipenderebbe da una scelta arbitraria del programmatore.

Nel contesto della sicurezza, l’ambiguità può generare gravi problemi, come interpretazioni diverse delle politiche di autenticazione o autorizzazione.

---

### 7. Consistenza delle specifiche

La consistenza richiede che la specifica non contenga contraddizioni interne. Requisiti contraddittori rendono impossibile l’implementazione corretta del sistema.

Un esempio riguarda la formattazione del testo con lunghezza fissa delle linee e il divieto di spezzare parole, senza specificare cosa accade quando una parola supera la lunghezza massima consentita. In questo caso, i requisiti entrano in conflitto.

Nella sicurezza software, inconsistenze nei requisiti possono portare a comportamenti imprevedibili e a violazioni delle politiche di sicurezza.

---

### 8. Completezza delle specifiche

La completezza implica che tutte le modalità di funzionamento del sistema siano descritte in modo dettagliato e senza lacune.

Un esempio di incompletezza è la richiesta di trovare il massimo tra due numeri senza specificare il formato dell’input. Anche dettagli apparentemente minori possono influire sul comportamento del sistema.

La completezza si divide in due forme:

#### Completezza interna

La specifica deve definire tutti i concetti e la terminologia utilizzata. Questo richiede la presenza di un glossario che chiarisca ogni nuovo termine.

Ad esempio, l’espressione “stato di attesa-per-richiesta” deve essere formalmente definita per evitare interpretazioni divergenti.

#### Completezza esterna

La specifica deve coprire tutti i requisiti richiesti dal committente. Nessuna funzionalità attesa deve rimanere non documentata.

---

### 9. Incrementalità delle specifiche

L’incrementalità indica che la specifica viene sviluppata attraverso passi successivi di raffinamento. Questo approccio consente di:

- migliorare progressivamente completezza e consistenza;
    
- ottenere feedback dal cliente;
    
- adattarsi ai cambiamenti dei requisiti.
    

Nel software sicuro, l’approccio incrementale è particolarmente utile perché consente di integrare gradualmente requisiti di sicurezza emergenti durante l’analisi dei rischi.

---

### 10. Comprensibilità delle specifiche

Poiché la specifica rappresenta un contratto tra committente e produttore, deve essere comprensibile anche per chi non possiede competenze tecniche avanzate.

Una buona specifica dovrebbe essere:

- intuitiva;
    
- possibilmente supportata da elementi visuali;
    
- concisa ma completa.
    

La comprensibilità influenza direttamente la scelta dei metodi di modellazione, che devono bilanciare formalismo e accessibilità.

---

### 11. Sintesi della lezione

In questa lezione sono stati introdotti i concetti fondamentali relativi alla specifica dei requisiti nel contesto della progettazione di software sicuro. È stato chiarito che la specifica rappresenta un contratto tra committente e produttore e costituisce la base dell’intero processo di sviluppo.

Sono state analizzate le principali proprietà di qualità di una specifica:

- chiarezza;
    
- non ambiguità;
    
- consistenza;
    
- completezza;
    
- incrementalità;
    
- comprensibilità.
    

Queste proprietà sono essenziali per ridurre i rischi di fallimento dei progetti software e per garantire la sicurezza del sistema fin dalle prime fasi di progettazione.