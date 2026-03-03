# **Lezione 1: Criteri di scelta di tecnologie sicure**

### 1. Introduzione: dai principi teorici alla scelta delle tecnologie

Dopo aver analizzato i principi generali delle architetture sicure, la lezione introduce il passaggio dalla teoria alla pratica, concentrandosi sui criteri per scegliere le tecnologie da utilizzare nello sviluppo di software sicuro. La sicurezza non dipende infatti solo dall’architettura logica del sistema, ma anche dagli strumenti tecnologici impiegati per realizzarlo.

La lezione individua quattro ambiti principali in cui la scelta tecnologica influisce in modo determinante sulla sicurezza:

1. linguaggio di programmazione
    
2. piattaforma distribuita
    
3. sistema operativo
    
4. tecnologie di autenticazione
    

Queste scelte costituiscono la base infrastrutturale del sistema e determinano sia le vulnerabilità possibili sia le difese disponibili.

---

### 2. Scelta del linguaggio di programmazione

La scelta del linguaggio di programmazione è una decisione fondamentale nella progettazione di software sicuro. I fattori che influenzano tale scelta includono:

- efficienza
    
- potere espressivo
    
- portabilità
    
- familiarità del team di sviluppo
    
- sicurezza
    

Tra i linguaggi possibili vengono citati C e C++, Java, C#, Visual Basic, Python, Perl, ML, Lisp e Scheme.

Il linguaggio non è solo uno strumento di implementazione, ma un vero e proprio **vincolo architetturale**, perché determina il modello di memoria, il sistema di tipi, la gestione degli errori e la presenza o meno di controlli automatici di sicurezza.

---

### 3. Efficienza e compromessi con la sicurezza

L’efficienza è spesso uno dei principali argomenti nella scelta del linguaggio, e per questo motivo linguaggi come C e C++ vengono frequentemente preferiti.

La lezione propone una classificazione approssimativa delle prestazioni:

C/C++ << Java/C# << Visual Basic << Perl, Python

Tuttavia, viene sottolineato che talvolta è vantaggioso sacrificare una parte dell’efficienza per ottenere altri benefici, come maggiore sicurezza e affidabilità.

Questo introduce un concetto importante: la sicurezza è spesso in conflitto con le prestazioni, e la progettazione richiede compromessi consapevoli.

---

### 4. Sicurezza dei linguaggi di programmazione

Non tutti i linguaggi offrono lo stesso livello di sicurezza intrinseca.

I programmi scritti in C tendono ad essere più inaffidabili perché il linguaggio consente accesso diretto alla memoria e operazioni pericolose senza controlli automatici.

I linguaggi interpretati, come Visual Basic o Perl, sono generalmente più sicuri dal punto di vista della memoria, ma possono contenere codice sintatticamente errato che non è mai stato eseguito e quindi non rilevato.

I linguaggi ad alto livello come Java e C# offrono livelli di sicurezza molto elevati grazie a:

- gestione automatica della memoria
    
- controlli di tipo
    
- meccanismi di protezione runtime
    

Questi vantaggi però comportano costi in termini di efficienza e complessità di sviluppo.

La lezione evidenzia anche un rischio importante: utilizzare in modo improprio i meccanismi di information hiding del linguaggio (come `private`) pensando che forniscano sicurezza reale. L’information hiding è un meccanismo di progettazione, non una protezione contro attaccanti.

---

### 5. Java come linguaggio orientato alla sicurezza

Java viene presentato come un linguaggio particolarmente vantaggioso dal punto di vista della sicurezza.

Le caratteristiche principali includono:

- assenza di puntatori espliciti
    
- controllo degli accessi ai buffer
    
- garbage collection
    
- forte controllo statico dei tipi
    
- sistema di eccezioni con obbligo di gestione
    
- framework per eseguire codice non fidato in sandbox
    
- librerie dedicate alla sicurezza
    

Queste caratteristiche riducono rischi classici come buffer overflow e errori di memoria, che sono tra le vulnerabilità più comuni nei sistemi scritti in linguaggi a basso livello.

---

### 6. Piattaforme distribuite ad oggetti

Molte applicazioni client-server moderne si basano su piattaforme distribuite orientate agli oggetti. Oltre alle caratteristiche funzionali e di integrazione, queste piattaforme differiscono anche per il livello di sicurezza che offrono.

La lezione analizza tre principali tecnologie:

1. CORBA
    
2. DCOM
    
3. EJB e RMI
    

Queste piattaforme rappresentano modelli diversi di comunicazione remota e gestione dei componenti distribuiti.

---

### 7. CORBA e i servizi di sicurezza

CORBA è uno standard proposto dall’OMG (Object Management Group). La sicurezza può essere implementata a due livelli:

- livello base, gestito dall’ORB (Object Request Broker)
    
- livello avanzato con servizi di sicurezza specifici
    

Tra i servizi di sicurezza disponibili troviamo:

- comunicazione cifrata tramite protocollo IIOP
    
- autenticazione dei client
    
- controllo degli accessi
    
- gestione delle credenziali con delega
    

La lezione sottolinea che le implementazioni concrete possono differire dagli standard, perché non tutti i servizi previsti vengono sempre implementati.

---

### 8. DCOM e livelli di autenticazione

DCOM (Distributed Component Object Model) è una tecnologia Microsoft che offre diversi livelli di sicurezza configurabili tramite l’authentication level.

I livelli vanno da assenza totale di sicurezza fino a comunicazioni cifrate con integrità garantita.

Un aspetto importante è che ogni livello superiore eredita le caratteristiche e le debolezze dei livelli inferiori, quindi eventuali vulnerabilità di base si propagano.

DCOM supporta inoltre servizi di impersonificazione, simili ai meccanismi di delega delle credenziali.

---

### 9. EJB e RMI

EJB (Enterprise Java Beans) e RMI (Remote Method Invocation) rappresentano l’approccio Java alla distribuzione di componenti.

La comunicazione avviene tramite RMI, mentre il server offre:

- controllo degli accessi
    
- servizi di cifratura
    

Un aspetto interessante è che la politica di sicurezza viene definita non dai componenti stessi, ma da chi li assembla nel sistema.

La lezione evidenzia però una debolezza importante: RMI permette il download dinamico di codice, inclusi componenti del layer di sicurezza, che potrebbero viaggiare in modo non sicuro. Le versioni più recenti cercano di mitigare questi problemi.

---

### 10. Sicurezza del sistema operativo

Il sistema operativo rappresenta uno dei livelli più importanti della sicurezza complessiva.

Due caratteristiche fondamentali vengono evidenziate:

#### Separazione tra kernel space e user space

I programmi applicativi operano nello spazio utente, mentre il sistema operativo opera nello spazio kernel. L’accesso alle risorse avviene tramite il sistema operativo, che agisce come mediatore.

#### Separazione tra processi

Un processo non deve poter accedere direttamente ai dati di un altro processo.

Sistemi operativi semplici o datati, come Win98 o PalmOS, non garantivano adeguatamente queste separazioni, aumentando il rischio di compromissione.

Inoltre, spesso non esiste separazione interna tra componenti del kernel: se una parte fallisce, l’intero sistema può bloccarsi.

---

### 11. Tecnologie di autenticazione

La scelta delle tecnologie di autenticazione è un elemento cruciale della sicurezza.

L’autenticazione non si limita a username e password, anche se questo metodo rimane valido quando accompagnato da politiche adeguate di gestione delle credenziali.

Altre tecnologie includono:

- autenticazione basata sull’host
    
- oggetti fisici
    
- autenticazione biometrica
    

---

### 12. Autenticazione basata sull’host

Questa forma di autenticazione utilizza caratteristiche della macchina, come:

- indirizzo IP
    
- nome DNS
    
- indirizzo MAC
    
- cookie o ticket memorizzati
    

Tuttavia, tali meccanismi non sono completamente affidabili perché:

- MAC e identificativi sono forniti dal client
    
- IP e DNS possono essere soggetti a spoofing
    

Pertanto, questa modalità non dovrebbe essere utilizzata come unico fattore di autenticazione.

---

### 13. Autenticazione tramite oggetti fisici

L’autenticazione può essere basata su oggetti fisici come chiavi o smart card.

I vantaggi includono la necessità di possedere fisicamente l’oggetto, ma esistono anche svantaggi significativi:

- necessità di hardware aggiuntivo
    
- possibilità di prestito dell’oggetto
    
- rischio di perdita o furto
    
- possibilità di clonazione
    

Questo mostra che il possesso non equivale necessariamente all’identità.

---

### 14. Autenticazione biometrica

L’autenticazione biometrica utilizza caratteristiche fisiche personali come impronte digitali, volto, voce o iride.

I vantaggi principali sono:

- impossibilità di dimenticare o perdere la caratteristica
    
- unicità biologica
    

Tuttavia, esistono anche problemi rilevanti:

- costo dell’hardware
    
- possibilità di copia delle caratteristiche
    
- impossibilità di revoca
    
- problemi di privacy
    

Un esempio critico riguarda i dati genetici (DNA), che introducono implicazioni etiche e legali importanti.

---

### 15. Sintesi della lezione

La lezione ha mostrato come la sicurezza influenzi direttamente le decisioni tecnologiche in diversi ambiti.

In particolare:

- la scelta del linguaggio di programmazione
    
- le piattaforme distribuite
    
- il sistema operativo
    
- le tecnologie di autenticazione
    

È stato evidenziato il vantaggio dei linguaggi ad alto livello come Java e C#, e sono state analizzate diverse soluzioni tecnologiche con i relativi compromessi di sicurezza.

Il messaggio centrale è che la sicurezza non nasce solo dall’architettura logica, ma anche dalle tecnologie utilizzate per implementarla.