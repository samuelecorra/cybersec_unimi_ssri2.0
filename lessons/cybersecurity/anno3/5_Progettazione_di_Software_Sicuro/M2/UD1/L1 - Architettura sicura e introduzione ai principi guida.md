# **Lezione 1: Architettura sicura e introduzione ai principi guida**

### **1. Introduzione all’architettura sicura**

Nel contesto della progettazione di software sicuro, il concetto di **architettura sicura** rappresenta uno degli elementi fondamentali per garantire che un sistema informatico raggiunga un adeguato livello di protezione rispetto alle minacce potenziali. L’architettura non coincide con il codice implementativo, ma costituisce il livello concettuale superiore che guida tutte le decisioni progettuali.

Un’architettura sicura può essere definita come **l’insieme dei principi di alto livello e delle decisioni progettuali** che permettono di realizzare sistemi considerati sufficientemente sicuri per il contesto di utilizzo previsto. Non esiste infatti una sicurezza assoluta: la sicurezza è sempre un compromesso tra protezione, usabilità, costi e prestazioni.

È importante sottolineare che una quantità eccessiva di sicurezza può risultare controproducente. Misure troppo restrittive possono rendere il sistema inutilizzabile, aumentare i costi di manutenzione o incentivare comportamenti di aggiramento da parte degli utenti. Pertanto, l’obiettivo non è massimizzare la sicurezza in senso assoluto, ma raggiungere un **livello adeguato di sicurezza** rispetto al rischio.

L’architettura sicura viene inoltre concepita come un **framework per la progettazione**, basato su quattro principi operativi fondamentali:

- Protect (proteggere)
    
- Deter (scoraggiare)
    
- Detect (scoprire)
    
- React (reagire)
    

Questi quattro elementi rappresentano il ciclo completo della sicurezza: prevenzione, deterrenza, rilevazione e risposta agli incidenti.

Un ulteriore aspetto essenziale è che l’architettura deve essere documentata attraverso un insieme di documenti formali che certificano le decisioni prese, i principi adottati e le motivazioni progettuali. La documentazione è fondamentale per la manutenzione, la verifica e la riusabilità.

Infine, una buona architettura sicura deve essere **riusabile**, cioè applicabile a contesti diversi senza dover essere completamente reinventata.

---

### **2. Differenza tra architettura di sicurezza e politica di sicurezza**

Un concetto cruciale introdotto nella lezione riguarda la distinzione tra **architettura di sicurezza** e **politica di sicurezza (security policy)**.

La politica di sicurezza definisce le regole operative e i vincoli del sistema, stabilendo:

- chi può accedere a quali risorse
    
- quali dati sono accessibili
    
- quali procedure di testing devono essere effettuate
    
- quali condizioni devono essere soddisfatte affinché il sistema sia considerato sicuro
    

In altre parole, la politica rappresenta il **livello normativo e regolatorio** della sicurezza.

L’architettura invece rappresenta il **livello progettuale** che implementa tali regole attraverso strutture software, componenti e meccanismi tecnici.

Un punto fondamentale è che la politica deve essere definita **prima** dell’architettura. La policy fornisce infatti le linee guida e i requisiti che l’architettura dovrà rispettare.

Possiamo quindi riassumere la relazione come segue:

- Policy → definisce cosa deve essere protetto e come
    
- Architettura → definisce come realizzare tecnicamente tale protezione
    

Questa distinzione è fondamentale nella sicurezza software e viene spesso confusa nei progetti reali.

---

### **3. Documenti di architettura**

L’architettura di un sistema non è solo un’idea astratta, ma deve essere formalizzata in documenti strutturati che descrivono le decisioni progettuali principali.

Secondo la lezione, i documenti di architettura dovrebbero definire diversi aspetti fondamentali del sistema:

#### Organizzazione del programma

Descrizione della struttura complessiva del software, dei componenti principali e delle loro relazioni.

#### Strategie di cambiamento

Indicazione delle modalità con cui il sistema potrà evolvere nel tempo. Questo è importante perché i sistemi sicuri devono essere aggiornabili senza compromettere la sicurezza.

#### Decisioni acquisto vs sviluppo

Valutazione se utilizzare componenti esistenti (commerciali o open source) oppure svilupparli internamente. Questa decisione ha un impatto diretto sulla sicurezza, perché il software riusato può introdurre vulnerabilità o dipendenze.

#### Strutture dati principali

Descrizione delle strutture dati critiche, soprattutto quelle che gestiscono informazioni sensibili.

#### Algoritmi chiave

Definizione degli algoritmi fondamentali del sistema, in particolare quelli che influenzano sicurezza, autenticazione, autorizzazione o cifratura.

#### Oggetti principali

Identificazione delle entità software fondamentali, particolarmente rilevante nei sistemi orientati agli oggetti.

#### Funzionalità generiche

Descrizione delle funzionalità di base offerte dal sistema.

#### Gestione degli errori

L’architettura deve prevedere il comportamento del sistema in presenza di errori, distinguendo tra:

- error handling correttivo
    
- error handling determinativo (diagnostico)
    

#### Robustezza attiva o passiva

Un sistema può reagire attivamente agli errori oppure limitarsi a resistere senza collassare.

#### Tolleranza ai guasti (fault tolerance)

Capacità del sistema di continuare a funzionare anche in presenza di malfunzionamenti.

Questi elementi dimostrano che l’architettura sicura non riguarda solo la protezione dagli attacchi, ma anche l’affidabilità complessiva del sistema.

---

### **4. Principi fondamentali di un’architettura sicura — Parte 1**

La lezione introduce una serie di principi guida per la progettazione sicura. Questi principi rappresentano linee guida generali che devono essere applicate durante la progettazione.

Un primo principio consiste nell’**iniziare facendo domande**. Ciò significa comprendere il contesto, le minacce, gli obiettivi e gli attori coinvolti prima di progettare.

È poi necessario decidere quale livello di sicurezza sia sufficiente, evitando sia sottoprotezione sia sovraprotezione.

Un aspetto critico è l’identificazione delle **assunzioni**. Molti sistemi falliscono perché le assunzioni implicite si rivelano false nel mondo reale.

La progettazione deve essere effettuata **tenendo in mente il nemico**, cioè assumendo che esistano attaccanti motivati e competenti.

È inoltre fondamentale rispettare la **chain of trust**, cioè la catena di fiducia tra componenti e livelli del sistema.

Un altro principio riguarda la definizione di **privilegi adeguati**, collegato al principio del minimo privilegio studiato nel Modulo 1.

Infine, le azioni del sistema devono essere testate rispetto alla policy di sicurezza.

---

### **5. Principi fondamentali di un’architettura sicura — Parte 2**

La seconda serie di principi riguarda aspetti strutturali e operativi della progettazione.

È importante suddividere il sistema in **moduli**, riducendo la complessità e limitando la propagazione delle vulnerabilità.

Le informazioni non devono essere offuscate inutilmente, ma rese comprensibili per garantire manutenzione e verifica.

Il sistema deve mantenere uno **stato minimo in memoria**, riducendo superfici di attacco e possibilità di inconsistenza.

Un principio cruciale riguarda la **fault tolerance**, cioè la capacità di funzionare anche in presenza di errori.

La gestione degli errori deve essere pianificata e non improvvisata.

Il principio di **graceful degradation** stabilisce che, in caso di problemi, il sistema deve degradare gradualmente le funzionalità anziché fallire completamente.

È essenziale che un fallimento lasci il sistema in una configurazione sicura (fail secure).

La sicurezza deve essere adeguata al tipo di utente, evitando complessità inutili.

Gli utenti devono essere responsabilizzati nelle loro azioni, perché la sicurezza è anche un problema umano.

Infine, il sistema deve controllare e limitare il consumo delle risorse per prevenire attacchi come denial of service.

---

### **6. Principi fondamentali di un’architettura sicura — Parte 3**

L’ultima serie di principi riguarda aspetti avanzati della progettazione sicura.

Il sistema deve garantire la possibilità di **ricostruire gli eventi**, quindi deve prevedere logging e auditing adeguati.

È necessario pianificare **livelli multipli di difesa** (defense in depth), evitando di affidarsi a un unico meccanismo di protezione.

Le applicazioni non devono essere considerate monolitiche, ma composte da componenti separati.

Il riuso di software certificato sicuro è fortemente raccomandato, perché riduce rischi e costi.

Devono essere utilizzate tecniche di ingegneria standard, evitando soluzioni improvvisate.

Infine, il principio più importante è progettare la sicurezza **sin dalle fasi iniziali del ciclo di vita**. Questo concetto è coerente con quanto visto nel Modulo 1 sul Secure Software Development Lifecycle.

---

### **7. Sintesi della lezione**

In questa lezione sono stati introdotti i concetti fondamentali dell’architettura sicura.

In particolare:

- è stata definita l’architettura sicura come insieme di principi e decisioni progettuali
    
- è stata chiarita la differenza tra architettura e politica di sicurezza
    
- sono stati descritti i documenti architetturali
    
- sono stati introdotti i principi guida per la progettazione sicura
    

Un messaggio fondamentale della lezione è che molti problemi di sicurezza derivano dal fatto che questi principi vengono ignorati durante lo sviluppo.

---

### **8. Prossimi passi**

Le lezioni successive presenteranno in dettaglio i principi di un’architettura sicura, approfondendone il significato e le modalità applicative.