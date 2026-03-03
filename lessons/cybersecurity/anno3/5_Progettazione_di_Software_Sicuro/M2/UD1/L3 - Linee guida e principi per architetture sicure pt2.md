# **Lezione 3: Linee guida e principi per architetture sicure pt2**

### 1. Livello di fault tolerance e certificazione: funzionalità critiche e “tre R”

La lezione riprende e approfondisce il tema della **fault tolerance** come requisito architetturale centrale nei sistemi sicuri. In particolare, viene richiamato il contesto di certificazioni (citata la certificazione **CERT**) in cui un apparato, per essere considerato adeguato, deve innanzitutto **identificare le funzionalità critiche** del sistema. Questo passaggio è determinante: non tutte le funzionalità hanno lo stesso peso rispetto alla sicurezza e alla continuità operativa; alcune componenti sono “core” e, se compromesse, possono rendere inutili tutte le altre difese.

Per descrivere in modo strutturato la fault tolerance, la lezione introduce il modello delle **tre R**: **Resistance**, **Recognition**, **Recovery**. L’idea è che un sistema non può dirsi robusto solo perché “resiste”: deve anche saper “capire” cosa sta accadendo e “riprendersi” in modo controllato.

La **Resistance** è la capacità di impedire un attacco o, più in generale, di opporre una barriera efficace all’azione malevola. Non è solo prevenzione astratta: riguarda scelte architetturali concrete (separazione dei privilegi, riduzione della superficie d’attacco, controlli di accesso corretti).

La **Recognition** è la capacità di riconoscere gli attacchi e di misurare il danno. Qui entra in gioco la capacità del sistema di osservare se stesso: logging, monitoring, rilevazione di anomalie, e soprattutto la distinzione tra comportamento normale e comportamento sospetto. Un sistema che non riconosce un attacco può continuare a funzionare “apparendo normale” mentre in realtà sta degradando o sta esfiltrando dati.

La **Recovery** è la capacità di mantenere servizi e assetti essenziali durante l’attacco e di ripristinare tutti i servizi dopo l’attacco. Questo implica due aspetti: continuità minima durante l’incidente e ritorno controllato alla piena operatività. In termini architetturali significa prevedere modalità ridotte ma sicure di funzionamento, procedure di ripristino e meccanismi che evitino di “tornare online” in uno stato già compromesso.

Infine, la lezione sottolinea che occorre **valutare il ruolo del software** nel contesto della fault tolerance e del **continuity planning** aziendale, perché il software non è isolato: è un elemento di un sistema socio-tecnico più ampio. Viene citato un esempio relativo all’**accesso a database**, che in molti contesti rappresenta un punto critico sia per la disponibilità sia per la sicurezza.

---

### 2. Error-handling appropriato: ruoli, responsabilità e pianificazione

Un punto chiave della lezione è che molti sistemi vengono compromessi non solo per vulnerabilità “classiche” (input validation, autenticazione, ecc.), ma per **trattamento improprio di errori inaspettati**. L’errore inatteso è spesso il varco attraverso cui un sistema espone informazioni, entra in stati inconsistenti, o consente bypass di controlli.

Per questo, la pianificazione dell’**error-handling** non è un dettaglio implementativo: è una responsabilità distribuita su più ruoli, ciascuno con un compito preciso.

L’**architetto** decide l’impostazione generale del trattamento degli errori, cioè quali strategie esistono, quali fallimenti si considerano, e quali principi (fail-safe, graceful degradation, logging) devono essere rispettati.

Il **designer** definisce come l’applicazione rileva il fallimento, come discrimina tra casi diversi e quale meccanismo di risposta viene adottato. Qui si colloca la progettazione dei flussi alternativi, delle eccezioni, delle condizioni di rollback e delle risposte verso l’utente o verso i servizi chiamanti.

Il **programmatore** implementa: cattura condizioni di decisione e triggering e realizza il design. È la fase in cui il rischio più grande è “semplificare” in modo pericoloso, ad esempio gestendo eccezioni con catch generici, ignorando errori o restituendo valori di default che mascherano il problema.

L’**operatore** verifica in esercizio che processi critici non vengano interrotti silenziosamente e che messaggi appropriati compaiano in console o vengano generati file di log. Questo richiama l’idea che l’operatività è parte della sicurezza: se il sistema cade e nessuno se ne accorge, la sicurezza è già fallita.

Questa suddivisione serve a evitare un errore tipico: trattare l’error-handling come “questione del programmatore”, mentre in realtà è un requisito architetturale che impatta direttamente la sicurezza.

---

### 3. Graceful degradation: continuare a operare in modo ristretto e sicuro

La lezione introduce il principio di **graceful degradation**: quando si verifica un guasto, il sistema non deve necessariamente fermarsi, ma deve continuare a operare in modo ristretto, con funzionalità ridotta, in uno stato degradato ma controllato.

Questo principio è fondamentale perché in contesti reali il fallimento totale spesso è peggiore della riduzione controllata: un arresto improvviso può compromettere disponibilità, integrità e persino sicurezza (ad esempio lasciando risorse in stati incerti).

Vengono citati esempi:

- un esempio di design **senza graceful degradation** collegato ai pacchetti del protocollo **TCP**;
    
- un esempio di design **con graceful degradation** relativo alla gestione della memoria nel sistema operativo **VMS** della Digital.
    

Il significato architetturale è chiaro: la degradazione non è “fare meno cose”, ma fare meno cose **mantenendo le proprietà di sicurezza** e mantenendo prevedibilità. Un sistema degradato deve ancora applicare controlli essenziali, deve ancora preservare la configurazione sicura e deve offrire percorsi di recovery.

---

### 4. Fallimento sicuro: terminare in una configurazione sicura (fault safely)

Accanto alla degradazione controllata, la lezione affronta il principio di **fault safely**: in caso di fallimento, un sistema deve terminare in una **configurazione sicura**. Qui l’idea è che se non puoi garantire il funzionamento corretto, devi almeno garantire che il sistema non diventi pericoloso.

Vengono forniti esempi molto concreti, che mostrano come “sicuro” dipenda dal contesto:

- se il server su cui risiede un firewall muore, la rete deve essere lasciata **chiusa**;
    
- se fallisce il programma che controlla il time lock di una cassaforte, la cassaforte deve rimanere **chiusa**;
    
- se fallisce il programma di controllo di una porta a chiusura elettronica, la porta deve rimanere **aperta**.
    

Quest’ultimo esempio evidenzia il punto più importante: la configurazione sicura non coincide sempre con “chiudere tutto”. In un edificio, ad esempio, l’apertura può essere la configurazione sicura per ragioni di evacuazione e sicurezza fisica delle persone.

Viene citato anche un esempio più complesso: il fallimento del programma che implementa il criterio di “morte cerebrale” in un dispositivo medico. Questo esempio serve a ricordare che in sistemi safety-critical la sicurezza informatica e la sicurezza fisica (safety) si intrecciano: una scelta di fail-safe errata può avere conseguenze gravissime.

---

### 5. Misure di sicurezza adeguate all’utente e accettabilità psicologica

La lezione introduce il principio dell’**accettabilità psicologica**: un sistema sicuro deve essere progettato tenendo conto dei modelli mentali dell’utente e dei paradigmi del mondo reale. Se la sicurezza è percepita come incomprensibile o ostile, l’utente tende a eluderla, e la sicurezza effettiva crolla.

Viene proposto un esempio operativo: selezionare un’interfaccia utente che renda facile fare le cose corrette. Questo concetto è cruciale perché sposta la sicurezza dal solo “controllo” alla “progettazione dell’esperienza”: se per compiere l’azione corretta servono 10 passaggi e per quella scorretta 1 solo, il sistema sta incentivando l’errore.

La slide sottolinea inoltre un punto pratico e brutale: se le misure di sicurezza sono così onerose e irritanti che l’utente le evita, la sicurezza del sistema è compromessa. La sicurezza deve quindi essere efficace ma anche sostenibile per chi la usa quotidianamente.

---

### 6. Responsabilità individuali: accountability e tracciabilità delle azioni

Un’architettura sicura di successo deve garantire che gli individui siano **responsabili** delle proprie azioni. La lezione chiama questo principio **accountable individuals**.

Questo principio richiede condizioni precise:

- ogni utente deve avere e usare un account personale;
    
- deve essere difficile per un utente spacciarsi per un altro;
    
- deve essere chiaramente stabilita la responsabilità della sicurezza delle risorse coinvolte.
    

In pratica, la responsabilità individuale è un prerequisito per auditing, forensics e gestione degli incidenti. Se le azioni non sono attribuibili, non è possibile né prevenire efficacemente né reagire in modo informato. Inoltre, l’accountability ha un effetto deterrente: quando l’utente sa che l’azione è tracciata, diminuisce l’incentivo a comportamenti negligenti o malevoli.

---

### 7. Limitazione del consumo di risorse e integrazione con recovery

La lezione prosegue con il principio di **resource-consumption limitation**: usare le funzionalità del sistema operativo per limitare il consumo di risorse del sistema.

Questo principio è fondamentale per la resilienza agli attacchi di tipo denial of service e, più in generale, per impedire che un singolo attore (utente, processo, componente) monopolizzi CPU, memoria, file descriptor, banda o altre risorse critiche.

La limitazione del consumo deve però essere combinata con un significativo **ripristino da errore** (error recovery). Non basta “tagliare” l’utilizzo: bisogna progettare contromisure che rilevino e gestiscano i casi di raggiunto limite, e bisogna utilizzare tecniche di graceful degradation per mantenere operatività essenziale.

Architetturalmente, questo significa prevedere soglie, circuit breaker, timeout, quote e politiche di backoff, ma anche percorsi di servizio ridotto che proteggano il sistema dal collasso completo.

---

### 8. Ricostruzione degli eventi: auditability e audit log

Un altro principio critico è quello di **auditability**: deve essere possibile ricostruire la sequenza di eventi che hanno condotto a determinate azioni chiave, come ad esempio un cambio di dati.

Questo principio richiede che l’applicazione e il sistema host creino e mantengano gli **audit log**. I log non sono “debug”: sono prove operative. Devono essere affidabili, consistenti, protetti da alterazioni e sufficientemente dettagliati da consentire analisi a posteriori.

La possibilità di ricostruire gli eventi è indispensabile sia per la risposta agli incidenti sia per la conformità a policy e normative, perché permette di capire cosa è successo, quando, come e con quale impatto.

---

### 9. Non monoliticità: considerare l’ecosistema completo di applicazioni e componenti

La lezione evidenzia un errore di prospettiva comune: considerare un’applicazione come entità monolitica e isolata. In realtà, la sicurezza deve essere garantita in ogni fase dello sviluppo e, soprattutto, nella valutazione delle minacce e della chain of trust bisogna considerare l’intera collezione di elementi interoperanti.

Vanno quindi inclusi:

- applicazioni software interoperanti,
    
- supporto software,
    
- connessione di rete,
    
- hardware.
    

Viene citato un esempio: un file Word con macro che richiama un programma Visual Basic. L’esempio mostra che le superfici d’attacco spesso nascono dalle interazioni tra componenti che, singolarmente, sembrano ragionevoli.

La slide conclude con un’osservazione fondamentale: un insieme di decisioni di design ragionevoli può combinarsi in modo irragionevole. Questo è uno dei motivi per cui l’architettura è centrale: serve a controllare la complessità emergente delle interazioni.

---

### 10. Riuso di software sicuro e limiti delle assunzioni su open/closed source

La lezione introduce il tema del **riuso di codice certificato sicuro**: molte grandi organizzazioni hanno politiche di riuso e repository di codice già validato. Il riuso può essere un vantaggio enorme, ma richiede maturità di processo (versioning, controlli, audit, dipendenze).

Viene inoltre ricordato che gli esperti discutono da anni se sia più sicuro il software open source o quello proprietario. La lezione non prende una posizione assoluta, ma introduce un principio operativo: non assumere che un programma o un algoritmo sia sicuro dagli attacchi solo perché è pubblicamente disponibile. La disponibilità pubblica del codice non garantisce automaticamente assenza di vulnerabilità, così come la chiusura del codice non garantisce sicurezza.

La sicurezza dipende dai processi, dagli audit e dall’uso corretto, non da un’etichetta.

---

### 11. Modularizzazione e semplicità: isolamento di funzioni privilegiate

La lezione riprende il principio di **modularizzazione**, collegandolo esplicitamente alla sicurezza.

Modularizzare significa:

- suddividere la progettazione in moduli,
    
- definire con precisione i punti di interfaccia tra moduli (argomenti, strutture di memoria comuni, ecc.),
    
- limitare privilegi e risorse ai moduli che realmente ne necessitano.
    

Un elemento centrale è che le funzioni che richiedono privilegi speciali devono essere isolate e codificate in moduli separati e semplici. Questo riduce il rischio che codice complesso e ampio operi con privilegi elevati.

La lezione aggiunge anche una regola generale: realizzare sistemi il più semplici possibile. La semplicità non è minimalismo estetico: è riduzione di stati, percorsi e casi, quindi riduzione di bug e di vulnerabilità.

---

### 12. Tecniche ingegneristiche come fattore critico di sicurezza

La lezione conclude sottolineando che le tecniche di sviluppo sono un fattore critico: il software sicuro richiede buona progettazione e buone tecniche di progettazione.

Vengono indicati tre macro-fattori che generano molti attacchi:

- mancanza di progettazione,
    
- debolezze umane,
    
- scarsa capacità di programmazione.
    

Una buona architettura di sicurezza può eliminare o mitigare i primi due fattori, ma non il terzo. Questo significa che l’architettura è necessaria ma non sufficiente: servono anche competenze implementative, review, testing e disciplina di sviluppo.

Questa osservazione è coerente con la visione del Modulo 1: la sicurezza nasce da processo e metodo, non da un singolo controllo o da una singola libreria.

---

### 13. Caso di studio: TCP SYN flood e principi violati

La lezione richiama l’analisi degli attacchi **TCP SYN flood** e afferma che, in tali attacchi, risultano violati diversi principi di progettazione:

- progettare con in mente il nemico,
    
- garantire un adeguato livello di fault tolerance,
    
- applicare il principio di graceful degradation,
    
- controllare il consumo di risorse.
    

Il collegamento è didatticamente importante perché mostra come i principi non siano astratti: la loro violazione produce conseguenze osservabili e sfruttabili dagli attaccanti.

---

### 14. Sintesi finale della lezione

La lezione ha presentato una seconda serie di principi guida per sviluppare architetture sicure, approfondendo soprattutto i temi di fault tolerance, gestione degli errori, comportamento in caso di guasto, centralità dell’utente, accountability, limitazione delle risorse, auditabilità, visione non monolitica del sistema, riuso di software sicuro, modularizzazione, semplicità e qualità delle tecniche ingegneristiche.

Il filo logico complessivo è che un’architettura sicura non si limita a “bloccare l’attacco”, ma deve gestire anche fallimenti, degradazioni, responsabilità, continuità e ricostruibilità degli eventi, integrando sicurezza e ingegneria del software in modo coerente.