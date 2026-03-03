# **Lezione 2: ISO/IEC 27035**

### 1. Introduzione allo standard ISO/IEC 27035

Lo standard ISO/IEC 27035 rientra nella famiglia degli standard ISO dedicati alla sicurezza delle informazioni e si concentra in modo specifico sulla gestione degli incidenti di sicurezza informatica (information security incident management). Esso fornisce linee guida metodologiche per identificare, documentare, valutare e rispondere agli incidenti, includendo anche il processo di apprendimento successivo basato sull’esperienza maturata.

Lo standard è articolato in più parti principali. La Parte 1 riguarda i principi della gestione degli incidenti e descrive concetti fondamentali, fasi operative e modalità per migliorare i processi organizzativi. La Parte 2 fornisce invece linee guida per pianificare e preparare la risposta agli incidenti.

L’approccio complessivo dello standard si fonda sull’idea che gli incidenti non possano essere completamente evitati, anche in presenza di policy e controlli di sicurezza efficaci. Di conseguenza, le organizzazioni devono sviluppare capacità strutturate per gestire gli incidenti in modo efficiente e controllato.

---

### 2. Necessità della gestione degli incidenti informatici

Un concetto centrale dello standard è che nessun sistema informativo può essere considerato completamente sicuro. Anche con adeguati controlli tecnici e organizzativi, gli incidenti possono verificarsi.

L’impreparazione nella gestione degli incidenti può generare impatti negativi significativi sul business, inclusi danni economici, perdita di reputazione, interruzione delle attività operative e perdita di fiducia da parte degli stakeholder.

Per questo motivo le organizzazioni devono essere in grado di:

- Individuare, documentare e valutare gli incidenti
    
- Rispondere agli incidenti attivando controlli adeguati
    
- Documentare vulnerabilità di sicurezza
    
- Apprendere dall’esperienza maturata
    

Questo approccio introduce il concetto di miglioramento continuo nella gestione della sicurezza.

---

### 3. Definizioni fondamentali dello standard

Lo standard introduce una terminologia precisa per descrivere gli elementi coinvolti nella gestione degli incidenti.

L’information security investigation è l’attività di esame, analisi e interpretazione che consente di comprendere un incidente informatico.

L’incident response team (IRT) è il gruppo di specialisti responsabile della gestione degli incidenti. Esso può assumere anche denominazioni alternative come CERT (Computer Emergency Response Team) o CSIRT (Computer Security Incident Response Team).

Un information security event è un evento che può indicare una possibile violazione della sicurezza.

Un information security incident è uno o più eventi che causano o possono causare danni agli asset dell’organizzazione.

L’information security incident management rappresenta l’approccio organizzativo complessivo alla gestione degli incidenti.

L’incident handling comprende le attività operative di individuazione, documentazione, risposta e apprendimento.

L’incident response è l’insieme delle azioni intraprese per ridurre o risolvere l’incidente e ripristinare le condizioni operative normali.

Il point of contact (PoC) è il ruolo aziendale che coordina le attività di gestione dell’incidente.

La precisione terminologica è fondamentale per evitare ambiguità operative e garantire coerenza nei processi organizzativi.

---

### 4. Relazione tra minaccia, vulnerabilità ed incidente

Lo standard descrive una catena causale che collega diversi concetti fondamentali della sicurezza informatica.

Una minaccia sfrutta una vulnerabilità presente in un sistema o in una rete, generando un information security event. Questo evento può evolvere in un information security incident che impatta sugli asset dell’organizzazione e sulle operazioni aziendali.

Questa rappresentazione evidenzia come gli incidenti non siano eventi isolati ma il risultato di interazioni tra fattori di rischio esistenti.

Comprendere questa relazione è essenziale per sviluppare strategie di prevenzione efficaci e migliorare la gestione del rischio.

---

### 5. Obiettivi della gestione degli incidenti

Un approccio ben pianificato alla gestione degli incidenti deve perseguire diversi obiettivi fondamentali.

Gli eventi di sicurezza devono essere identificati e gestiti in modo efficiente, stabilendo quando classificarli come incidenti. Gli incidenti identificati devono essere valutati e affrontati rapidamente per ridurre gli effetti negativi.

Un altro obiettivo consiste nel minimizzare l’impatto degli incidenti attraverso controlli appropriati e garantire la continuità operativa dell’organizzazione (business continuity).

È inoltre necessario valutare e affrontare le vulnerabilità per prevenire nuovi incidenti, attività tipicamente svolta dall’IRT o da altri team specializzati.

Infine, è fondamentale apprendere dalle esperienze per migliorare continuamente i processi di sicurezza.

Per raggiungere tali obiettivi, lo standard sottolinea la necessità di documentare gli incidenti in modo coerente e utilizzando standard appropriati.

---

### 6. Relazione con ISO/IEC 27001 e ISMS

Uno degli obiettivi dello standard ISO/IEC 27035 è fornire indicazioni operative per soddisfare i requisiti dello standard ISO/IEC 27001, in particolare per quanto riguarda l’Information Security Management System (ISMS).

La gestione degli incidenti diventa quindi un elemento integrato del sistema di gestione della sicurezza delle informazioni, contribuendo alla riduzione dei rischi e alla protezione degli asset informativi.

Il modello evidenzia come la gestione degli incidenti sia influenzata da controlli di sicurezza, stakeholder esterni e team di risposta agli incidenti, e come essa contribuisca al miglioramento complessivo della sicurezza delle informazioni.

---

### 7. Benefici di un approccio strutturato

L’adozione di un approccio strutturato alla gestione degli incidenti produce numerosi benefici.

Tra questi vi sono il miglioramento generale della sicurezza, la rapida identificazione e valutazione degli eventi, la riduzione degli impatti negativi sul business e il rafforzamento della prevenzione.

Un approccio strutturato consente inoltre di sviluppare metodologie per individuare nuove minacce e vulnerabilità, definire priorità operative e classificare correttamente eventi e incidenti.

Un ulteriore beneficio riguarda il supporto alla raccolta e gestione delle prove digitali, che possono essere utilizzate in eventuali procedimenti giudiziari.

Dal punto di vista organizzativo, la gestione strutturata degli incidenti facilita la pianificazione del budget e l’allocazione delle risorse, oltre a migliorare la formazione e la consapevolezza del personale.

---

### 8. Fasi della gestione degli incidenti secondo ISO/IEC 27035

Lo standard identifica cinque fasi principali del processo di gestione degli incidenti:

1. Pianificazione e preparazione
    
2. Individuazione e documentazione
    
3. Valutazione e decisione
    
4. Risposta
    
5. Apprendimento
    

Queste fasi costituiscono un processo iterativo che migliora nel tempo grazie all’esperienza accumulata e all’analisi degli errori.

Il modello operativo coinvolge diversi attori, tra cui utenti, Point of Contact, IRT interno ed eventualmente IRT esterno o strutture di gestione della crisi.

---

### 9. Fase 1: Pianificazione e preparazione

La fase di pianificazione e preparazione ha lo scopo di predisporre l’organizzazione alla gestione degli incidenti prima che essi si verifichino.

Le attività includono:

- Definizione delle policy di gestione degli incidenti
    
- Aggiornamento delle policy di sicurezza e gestione del rischio
    
- Elaborazione di un piano di gestione degli incidenti e delle comunicazioni
    
- Costituzione dell’IRT e formazione del personale
    
- Creazione di relazioni con organizzazioni interne ed esterne
    
- Sviluppo di meccanismi tecnici e organizzativi di supporto
    
- Implementazione di sistemi informativi per il supporto all’IRT
    
- Programmi di formazione e consapevolezza
    
- Simulazioni e test dei piani
    

Al termine di questa fase, l’organizzazione deve essere pronta a gestire incidenti sia dal punto di vista procedurale sia documentale.

---

### 10. Fasi 2 e 3: Individuazione, documentazione, valutazione e decisione

La fase di individuazione e documentazione riguarda il monitoraggio dei sistemi informativi e delle reti per identificare anomalie o eventi sospetti.

Le attività principali comprendono:

- Monitoraggio delle attività
    
- Raccolta di informazioni su vulnerabilità ed eventi
    
- Registrazione delle decisioni e dei risultati
    
- Raccolta e conservazione delle evidenze
    
- Aggiornamento del database di sicurezza
    

La fase di valutazione e decisione consiste nel determinare la gravità dell’evento, assegnare responsabilità e stabilire le azioni da intraprendere, utilizzando procedure formali e adeguando la risposta al livello di criticità.

Una documentazione accurata è essenziale in entrambe le fasi.

---

### 11. Fase 4: Risposta

La fase di risposta rappresenta il momento operativo centrale della gestione dell’incidente.

Le attività includono:

- Classificazione dell’incidente
    
- Aggiornamento dello stato
    
- Eventuale escalation a gestione di crisi
    
- Identificazione delle persone coinvolte
    
- Assegnazione di compiti e risorse
    
- Documentazione completa delle attività
    
- Raccolta e conservazione delle prove
    

Dopo il recupero dei sistemi, devono essere svolte attività post-incidente proporzionate alla criticità.

L’incidente può essere considerato chiuso quando l’IRT o personale qualificato ritiene che sia completamente risolto.

Infine, tutte le informazioni devono essere registrate nel database di sicurezza.

---

### 12. Fase 5: Apprendimento

La fase di apprendimento consente di trasformare l’esperienza dell’incidente in miglioramenti organizzativi.

Le attività includono:

- Identificazione delle lezioni apprese
    
- Miglioramento dei processi di controllo
    
- Aggiornamento della gestione del rischio
    
- Revisione di policy, procedure e documentazione
    
- Condivisione delle informazioni apprese
    
- Valutazione delle prestazioni dell’IRT
    

Questa fase rappresenta il cuore del miglioramento continuo del sistema di sicurezza.

---

### 13. Esempi di incidenti e cause

Lo standard fornisce esempi di incidenti informatici e delle loro possibili cause.

Tra gli attacchi vi sono i Denial of Service (DoS) e Distributed Denial of Service (DDoS), che possono essere deliberati o causati da problemi tecnici.

Gli accessi non autorizzati possono derivare da tentativi di recupero password, exploit di vulnerabilità o errori di configurazione.

Il malware rappresenta un’altra categoria importante di incidenti.

Gli abusi includono l’uso improprio delle risorse aziendali, come installazione di strumenti di hacking o utilizzo di email aziendali per scopi non autorizzati.

Infine, la raccolta di informazioni può avvenire tramite tecniche tecniche, come port scanning, oppure non tecniche, come social engineering.

---

### 14. Sintesi finale

La lezione ha presentato lo standard ISO/IEC 27035, che definisce principi, fasi e metodologie per la gestione degli incidenti informatici. Sono stati analizzati i concetti fondamentali, le relazioni tra minaccia, vulnerabilità ed incidente, le cinque fasi del processo e i benefici di un approccio strutturato.

È stato evidenziato che la gestione degli incidenti è un processo iterativo, strettamente integrato con il sistema di gestione della sicurezza delle informazioni, e che una gestione efficace degli incidenti precede e supporta la gestione della prova digitale.

Una gestione impropria o improvvisata degli incidenti può infatti portare alla perdita di prove digitali fondamentali.

---