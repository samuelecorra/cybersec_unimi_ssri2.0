# **Lezione 1: ISO/IEC 27037**

### 1. Introduzione agli standard ISO e IEC

La gestione della sicurezza nei sistemi informativi si fonda, a livello internazionale, su un insieme di standard tecnici che hanno lo scopo di uniformare metodologie, procedure e requisiti qualitativi. Tra le organizzazioni più importanti che si occupano della definizione di tali standard vi sono ISO e IEC.

L’ISO (International Organization for Standardization) rappresenta la principale organizzazione mondiale per la definizione di norme tecniche. Fondata il 23 febbraio 1947 e con sede a Ginevra, essa è composta dagli organismi nazionali di standardizzazione di oltre 160 Paesi. Il suo obiettivo principale è promuovere la standardizzazione internazionale per facilitare il commercio globale, garantire qualità e sicurezza dei prodotti e migliorare l’interoperabilità dei sistemi.

L’IEC (International Electrotechnical Commission) è invece l’organizzazione internazionale che si occupa della standardizzazione nei settori dell’elettricità, dell’elettronica e delle tecnologie correlate. Fondata nel 1906 e trasferita a Ginevra nel 1948, coinvolge più di 60 Paesi. Molti standard sono sviluppati congiuntamente tra ISO e IEC, dando origine agli standard ISO/IEC.

Questa collaborazione è particolarmente rilevante nel campo della sicurezza informatica e dell’informatica forense, dove la componente tecnologica è strettamente connessa con quella procedurale.

---

### 2. Obiettivo dello standard ISO/IEC 27037:2012

Lo standard ISO/IEC 27037:2012 fornisce linee guida per l’identificazione, la raccolta, l’acquisizione e la conservazione delle prove digitali. Esso rappresenta uno standard fondamentale nell’ambito della digital forensics perché definisce procedure operative che permettono di garantire l’integrità e la validità legale delle evidenze informatiche.

Per ciascuna fase del trattamento della prova digitale, lo standard sottolinea l’importanza di alcuni elementi fondamentali:

- Documentazione delle attività (logging)
    
- Tracciabilità delle operazioni attraverso la catena di custodia (chain of custody)
    
- Pianificazione delle priorità di intervento
    
- Protezione e imballaggio dei reperti
    
- Trasporto sicuro dei reperti reali o virtuali
    
- Definizione dei ruoli e delle responsabilità nelle diverse fasi
    

Lo standard si concentra quindi sulle fasi iniziali del ciclo di vita della prova digitale e non entra nella fase di analisi forense vera e propria.

---

### 3. Figure professionali coinvolte nella gestione della prova digitale

Lo standard identifica diverse figure professionali che possono trattare le prove digitali.

La figura del Digital Evidence First Responder (DEFR) rappresenta l’operatore autorizzato e qualificato a intervenire per primo sulla scena dell’incidente o del crimine informatico. Il suo compito principale è identificare, raccogliere, imballare e conservare le prove digitali in modo corretto.

Il Digital Evidence Specialist (DES) possiede competenze più avanzate e può svolgere sia i compiti del DEFR sia attività specialistiche legate a problematiche tecniche complesse, come acquisizioni di rete o gestione di sistemi operativi.

L’Incident Response Specialist è invece l’operatore che si occupa del primo intervento dopo un incidente informatico, ma non deve essere confuso con l’amministratore di sistema, poiché i ruoli e le responsabilità sono differenti.

Infine, il Forensic Laboratory Manager è responsabile del laboratorio di informatica forense e delle attività che vi vengono svolte.

---

### 4. Compiti del Digital Evidence First Responder (DEFR)

Il DEFR svolge un ruolo critico nelle prime fasi dell’indagine, poiché eventuali errori iniziali possono compromettere definitivamente la validità delle prove.

Tra i principali compiti rientrano:

- Mettere in sicurezza la scena nel minor tempo possibile
    
- Controllare l’area contenente dispositivi digitali
    
- Identificare il responsabile dell’area
    
- Allontanare le persone dai dispositivi e dall’alimentazione
    
- Documentare gli accessi autorizzati
    
- Individuare persone con possibili moventi o informazioni rilevanti
    
- Non alterare lo stato delle apparecchiature (se acceso non spegnere, se spento non accendere)
    
- Documentare accuratamente la scena tramite fotografie, video e schemi
    
- Ricercare informazioni utili come password, PIN, note e manuali
    

L’obiettivo principale è preservare lo stato originale dei dispositivi per evitare alterazioni dei dati.

---

### 5. Localizzazione dei dati di interesse

Le prove digitali possono trovarsi in una grande varietà di dispositivi, tra cui:

- Dischi rigidi e supporti di memorizzazione
    
- Dispositivi mobili e schede di memoria
    
- Fotocamere e videocamere
    
- Sistemi connessi in rete
    
- Infrastrutture basate su protocolli TCP/IP
    

La complessità dell’ambiente tecnologico moderno rende sempre più difficile individuare tutte le possibili fonti di dati rilevanti, soprattutto considerando tecnologie come cloud computing o sistemi distribuiti.

---

### 6. Glossario tecnico e concetti fondamentali

Lo standard introduce alcune definizioni fondamentali per la digital forensics.

Lo spazio allocato è la porzione di memoria utilizzata dal sistema operativo per memorizzare dati, mentre lo spazio non allocato è l’area disponibile che può contenere residui di dati precedentemente cancellati.

Lo slack space rappresenta la parte inutilizzata tra la fine dei dati di un file e la fine del settore fisico su disco.

La prova digitale è qualsiasi informazione memorizzata o trasmessa in formato binario che può essere utilizzata come evidenza.

La copia di prova digitale è una copia creata per preservare l’affidabilità dell’evidenza, includendo anche la procedura di verifica.

Il valore di hash è una stringa prodotta da una funzione hash, utilizzata per garantire l’integrità dei dati.

La validazione consiste nella conferma che i requisiti stabiliti siano stati soddisfatti.

La funzione di verifica consente di controllare che due insiemi di dati siano identici.

Lo standard include inoltre numerosi acronimi tecnici rilevanti nel contesto investigativo digitale.

---

### 7. Requisiti generali della prova digitale

Per essere utilizzabile, una prova digitale deve rispettare alcuni requisiti fondamentali.

La pertinenza riguarda la capacità della prova di contribuire a dimostrare o confutare un’ipotesi investigativa.

L’affidabilità richiede che la prova sia genuina e che i processi utilizzati siano documentati e ripetibili.

La sufficienza implica che venga raccolta una quantità adeguata di materiale, senza necessariamente acquisire sempre una copia completa, valutando caso per caso anche in base alla legislazione nazionale.

Accanto a questi requisiti, esistono aspetti chiave metodologici:

- Verificabilità: un terzo deve poter valutare le attività svolte
    
- Ripetibilità: le operazioni producono lo stesso risultato nelle stesse condizioni
    
- Riproducibilità: lo stesso risultato con strumenti diversi
    
- Giustificabilità: dimostrare che le scelte erano appropriate
    

Questi principi derivano dal metodo scientifico e sono essenziali per la validità forense.

---

### 8. Processo di gestione della prova digitale

Lo standard identifica quattro fasi principali del trattamento della prova digitale:

1. Identificazione
    
2. Raccolta
    
3. Acquisizione
    
4. Conservazione
    

È importante sottolineare che lo standard non copre la fase di analisi forense.

La prova digitale è intrinsecamente fragile e può essere alterata accidentalmente, naturalmente o intenzionalmente. Per questo motivo tutte le procedure sono orientate alla preservazione dei bit originali e alla prevenzione delle modifiche.

---

### 9. Fase di identificazione

L’identificazione consiste nella ricerca dei dispositivi che possono contenere dati rilevanti. La prova digitale può esistere sia in forma fisica (device) sia in forma logica (rappresentazione dei dati).

Occorre dare priorità ai dati volatili e considerare dispositivi difficili da individuare per dimensioni o posizione geografica, come sistemi cloud o storage distribuiti.

Un computer viene definito come un dispositivo digitale standalone che riceve, elabora e memorizza dati e produce risultati, anche se non è connesso alla rete. Tuttavia, la presenza di interfacce di rete richiede di considerare eventuali comunicazioni con altri sistemi.

Il DEFR deve documentare accuratamente ogni supporto, identificando marca, tipo e numero di serie, lo stato dei dispositivi e il contenuto visibile a schermo, utilizzando fotografie, video e verbali.

Devono inoltre essere recuperati cavi di alimentazione e utilizzati strumenti per individuare segnali wireless non visibili.

Nelle decisioni operative devono essere considerati fattori come volatilità dei dati, presenza di cifratura, criticità del sistema, requisiti legali e risorse disponibili.

---

### 10. Fase di raccolta

La raccolta consiste nella rimozione dei dispositivi dalla posizione originale e nel loro trasporto in laboratorio per le fasi successive.

Il dispositivo può trovarsi acceso o spento, e ciascuna situazione richiede approcci e strumenti differenti. Le decisioni operative devono basarsi sulla situazione concreta, sui costi e sui tempi disponibili.

È essenziale documentare tutte le operazioni e raccogliere anche accessori correlati ai dispositivi.

---

### 11. Fase di acquisizione

L’acquisizione prevede la creazione di una copia forense del supporto o dei dati, accompagnata dalla documentazione completa delle procedure, degli strumenti e delle attività svolte.

L’acquisizione può riguardare:

- Intero supporto
    
- Partizione
    
- Gruppo di file
    

Tuttavia, acquisire solo un insieme di file comporta la perdita di informazioni importanti, come spazio non allocato o file cancellati.

L’obiettivo è apportare il minor numero possibile di modifiche ai dati originali, idealmente nessuna. Eventuali alterazioni devono essere documentate e giustificate, ad esempio in presenza di sistemi attivi o settori danneggiati.

---

### 12. Fase di conservazione

La conservazione ha lo scopo di proteggere l’integrità e la riservatezza dei dati da alterazioni naturali, accidentali o dolose.

Devono essere utilizzati imballaggi adeguati, come contenitori antistatici per supporti magnetici, senza danneggiare i dispositivi.

Occorre etichettare tutti i reperti, verificare lo stato delle batterie, bloccare parti mobili e ridurre i rischi legati al trasporto.

Devono essere preservate anche eventuali tracce non digitali, come tracce biologiche, utilizzando guanti puliti.

---

### 13. Catena di custodia

La catena di custodia rappresenta la documentazione completa dei movimenti e delle interazioni con la prova digitale a partire dalla raccolta.

Può essere mantenuta in formato cartaceo o digitale e deve includere:

- Identificativo unico dell’evidenza
    
- Informazioni su chi, quando, dove e perché ha avuto accesso
    
- Documentazione delle alterazioni inevitabili con indicazione del responsabile
    

La catena di custodia è fondamentale per garantire la validità legale della prova.

---

### 14. Sintesi finale

La lezione ha introdotto lo standard ISO/IEC 27037, che definisce linee guida per la gestione iniziale delle prove digitali. Sono state analizzate le figure professionali coinvolte, i requisiti della prova digitale e le quattro fasi principali del processo: identificazione, raccolta, acquisizione e conservazione.

È stato evidenziato che lo standard non copre la fase di analisi forense e che la documentazione completa delle attività e la corretta gestione della catena di custodia rappresentano elementi essenziali per garantire l’integrità e la validità delle evidenze digitali.