# **M4 UD1 Lezione 1 - Obiettivi e funzioni di un sistema distribuito**

---

### **1. Introduzione**

Questa lezione introduce i **sistemi distribuiti**, cioe' l'estensione delle caratteristiche dei sistemi operativi al caso in cui piu' sistemi di elaborazione cooperano attraverso una rete.

Gli aspetti principali sono:

- architetture di elaborazione distribuite;
- sistemi operativi di rete;
- sistemi operativi distribuiti;
- obiettivi e funzionalita' di questi sistemi;
- robustezza, trasparenza, mobilita' e scalabilita'.

> 📌 Un sistema distribuito deve permettere a piu' macchine interconnesse di cooperare come parte di un unico ambiente di elaborazione.

---

### **2. Architettura di elaborazione distribuita**

Un'**architettura di elaborazione distribuita** e' costituita da un insieme di sistemi di elaborazione interconnessi tramite una rete.

Ogni sistema di elaborazione, o nodo, possiede almeno:

- un processore;
- una memoria locale;
- eventuali periferiche locali;
- una connessione alla rete di comunicazione.

Le periferiche possono essere:

- locali a un singolo sistema;
- condivise tra piu' sistemi;
- globali, cioe' accessibili da tutti i sistemi della rete.

La rete stessa puo' essere vista come una periferica per ciascun sistema di elaborazione, perche' consente lo scambio di informazioni con altri nodi.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Eterogeneita'**

Una caratteristica fondamentale dei sistemi distribuiti e' l'**eterogeneita'**.

I nodi possono differire per:

- architettura hardware;
- processori;
- sistemi operativi;
- rappresentazione dei dati;
- periferiche disponibili;
- capacita' di calcolo;
- velocita' di connessione.

Questa eterogeneita' rende piu' complessa la gestione del sistema.

Il sistema operativo deve tenerne conto per garantire:

- cooperazione tra macchine;
- interoperabilita';
- integrazione delle risorse;
- comunicazione corretta tra processi su nodi diversi.

> ⚠️ In un sistema distribuito non si puo' assumere che tutte le macchine rappresentino dati e risorse nello stesso modo.

---

### **4. Siti, host, nodi, client e server**

Un'architettura distribuita e' costituita da un insieme di **siti**, cioe' locazioni in cui sono installate macchine.

Le macchine possono essere chiamate:

- host;
- computer;
- nodi;
- sistemi di elaborazione.

Ogni macchina puo' agire come:

- **server**, quando eroga un servizio;
- **client**, quando usa un servizio.

La stessa macchina puo' essere server in alcune interazioni e client in altre.

> 💡 Client e server non identificano necessariamente macchine diverse: descrivono il ruolo assunto in una specifica interazione.

---

### **5. Vantaggi delle architetture distribuite**

Le architetture distribuite offrono diversi vantaggi rispetto a un singolo sistema centralizzato.

#### **5.1. Integrazione dei sottosistemi**

Molte organizzazioni possiedono gia' sottosistemi installati in siti diversi.

Interconnetterli permette di:

- conservare gli investimenti gia' fatti;
- far comunicare utenti e processi;
- condividere informazioni;
- integrare servizi gia' esistenti.

#### **5.2. Condivisione delle risorse**

La distribuzione permette di condividere:

- risorse informative, come file, database e servizi;
- risorse fisiche, come stampanti, dischi, periferiche e processori.

Questo evita duplicazioni inutili e migliora lo sfruttamento globale del sistema.

#### **5.3. Parallelismo della computazione**

Avendo piu' sistemi di elaborazione, una computazione puo' essere suddivisa tra piu' processori.

Questo permette un parallelismo fisico reale.

Se il problema e' decomponibile, il tempo di elaborazione puo' diminuire.

#### **5.4. Vicinanza agli utenti**

La capacita' di elaborazione puo' essere portata vicino agli utenti.

Questo migliora l'interazione e consente interfacce piu' ricche, anche multimediali, con un livello di astrazione piu' elevato.

#### **5.5. Downsizing**

Il **downsizing** consiste nel ridurre complessita' e costo dei singoli componenti acquistati, scegliendo macchine piu' piccole ma cooperanti.

Invece di usare un unico sistema centrale costoso, si usano piu' componenti specializzati e dimensionati sulle esigenze reali.

#### **5.6. Affidabilita' e disponibilita'**

Un'architettura distribuita puo' aumentare:

- affidabilita';
- tolleranza ai guasti;
- disponibilita' complessiva.

Se una macchina si guasta, alcune attivita' possono essere spostate su altre macchine.

Se una connessione si interrompe, connessioni ridondanti possono permettere comunque l'instradamento delle comunicazioni.

#### **5.7. Scalabilita'**

Un sistema distribuito puo' crescere nel tempo.

E' possibile aggiungere:

- nodi;
- servizi;
- funzioni;
- periferiche;
- capacita' di calcolo.

Questo consente di adattare il sistema alla crescita degli utenti e dei carichi senza eliminare cio' che e' gia' stato acquistato.

---

### **6. Ruolo del sistema operativo**

Il sistema operativo deve supportare l'erogazione dei servizi in uno scenario in cui le risorse sono distribuite.

Deve quindi gestire:

- accesso a risorse remote;
- comunicazione tra processi su nodi diversi;
- condivisione di file e periferiche;
- trasparenza;
- migrazione di dati e processi;
- robustezza e tolleranza ai guasti;
- riconfigurazione del sistema.

Esistono due grandi famiglie:

- sistemi operativi di rete;
- sistemi operativi distribuiti.

---

### **7. Sistemi operativi di rete**

Un **sistema operativo di rete** consente l'interazione tra componenti di un sistema distribuito, ma lascia visibile agli utenti e ai processi la struttura della rete.

In questo modello:

- il processo sa che una risorsa e' remota;
- l'utente spesso deve sapere dove si trova la risorsa;
- il sistema operativo supporta il reperimento e l'uso della risorsa, ma non nasconde completamente la distribuzione.

> 📌 Nei sistemi operativi di rete, la rete resta visibile: l'utente o il processo deve conoscere almeno parte della localizzazione delle risorse.

#### **7.1. Funzioni principali**

Un sistema operativo di rete deve supportare:

- collegamento a macchine remote;
- login remoto;
- attivazione di processi su macchine remote;
- comunicazione tra processi locali e remoti;
- chiamata di procedure remote;
- uso di risorse fisiche remote;
- stampa remota tramite code e spooling;
- trasferimento di file;
- accesso a file system remoti;
- servizi generali come posta elettronica.

#### **7.2. Accesso a file remoti**

Nel sistema operativo di rete, l'accesso a file remoti puo' avvenire come:

- trasferimento da remoto a locale;
- trasferimento da locale a remoto;
- montaggio locale di porzioni di file system remote.

Nel caso del trasferimento, il file viene copiato usando protocolli come FTP.

Nel caso del montaggio, un file server remoto rende visibile localmente una parte del proprio file system.

---

### **8. Sistemi operativi distribuiti**

Un **sistema operativo distribuito** ha un obiettivo piu' ambizioso.

Deve nascondere l'intera architettura fisica distribuita e far apparire l'insieme delle macchine come un unico sistema di elaborazione.

In questo modello:

- le risorse remote appaiono come locali;
- i processi non devono sapere dove si trovano le risorse;
- la localizzazione fisica diventa trasparente;
- il sistema operativo gestisce automaticamente l'accesso alle risorse.

> ✅ In un sistema operativo distribuito, la distribuzione geografica e fisica deve diventare un dettaglio nascosto ai processi.

#### **8.1. Vantaggio della trasparenza**

La trasparenza semplifica l'uso del sistema.

Gli utenti e i processi vedono risorse identificate univocamente, senza dover conoscere:

- il nodo che le ospita;
- il percorso di rete;
- il formato interno;
- il modo concreto di accesso.

Questo consente anche di modificare la struttura hardware senza informare gli utenti, perche' il sistema operativo filtra e nasconde tali cambiamenti.

#### **8.2. Complessita'**

I sistemi operativi distribuiti sono piu' complessi dei sistemi operativi di rete.

Devono infatti realizzare la trasparenza a livello globale, coordinando nodi diversi e risorse distribuite.

---

### **9. Migrazione dei dati**

Un sistema operativo distribuito deve gestire la **migrazione dei dati**.

La migrazione dei dati consiste nello spostare informazioni tra macchine diverse.

Puo' avvenire in diversi modi.

#### **9.1. Copia, elaborazione e salvataggio**

Il sistema puo':

1. copiare un file da una macchina remota a una macchina locale;
2. elaborarlo localmente;
3. salvare il file modificato sulla macchina remota.

Il tutto avviene in modo trasparente per il processo.

#### **9.2. Copia parziale**

In alternativa, il sistema puo' copiare solo porzioni del file.

Questo approccio e' simile alla paginazione:

- non si trasferisce tutto lo spazio informativo;
- si trasferiscono solo le parti necessarie;
- il sistema carica progressivamente le porzioni richieste.

#### **9.3. Compatibilita' dei dati**

La migrazione dei dati deve affrontare il problema della compatibilita' di rappresentazione.

Macchine diverse possono rappresentare diversamente:

- numeri;
- caratteri;
- strutture dati;
- formati binari;
- ordinamento dei byte.

Il sistema deve quindi nascondere o gestire queste differenze.

---

### **10. Migrazione computazionale**

La **migrazione computazionale** consiste nello spostare l'esecuzione, in tutto o in parte, verso una macchina remota.

Le forme principali sono:

- migrazione di procedura;
- migrazione di processo;
- agenti mobili.

---

### **11. Chiamata di procedura remota**

La migrazione di una procedura puo' essere realizzata mediante **Remote Procedure Call**.

In questo caso:

- la procedura risiede su una macchina remota;
- il processo locale la invoca come se fosse locale;
- un processo remoto esegue effettivamente la procedura;
- i risultati vengono restituiti tramite scambio di messaggi.

La RPC e' utile quando:

- l'hardware remoto e' preferibile;
- il software remoto e' piu' adatto;
- la risorsa necessaria esiste solo in remoto;
- i dati remoti sono troppo grandi da trasferire;
- i dati non devono essere spostati per motivi di protezione o riservatezza.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Migrazione dei processi e agenti mobili**

Invece di spostare una singola procedura, si puo' spostare l'intero processo.

La **migrazione dei processi** trasferisce:

- codice;
- stato;
- dati;
- ambiente di esecuzione;
- contesto necessario alla ripresa della computazione.

Una forma moderna e' costituita dagli **agenti mobili**, entita' software che si spostano tra nodi per eseguire compiti in prossimita' delle risorse.

#### **12.1. Obiettivi**

La migrazione dei processi serve a:

- bilanciare il carico;
- sfruttare processori sottoutilizzati;
- velocizzare la computazione;
- usare hardware o software disponibili solo in remoto;
- accedere a dati che devono restare in remoto.

#### **12.2. Costo di gestione**

La migrazione non e' sempre conveniente.

Spostare un processo richiede tempo.

Se il tempo di migrazione supera il guadagno ottenuto, lo spostamento non conviene.

> ⚠️ La migrazione e' utile solo se il beneficio supera il costo di trasferimento e riconfigurazione.

---

### **13. File system e risorse distribuite**

Un sistema operativo distribuito deve fornire un **file system distribuito**.

Il file system globale deve essere visto nello stesso modo da qualunque macchina.

Questo puo' avvenire montando file system remoti in modo omogeneo e trasparente, come se fossero dischi locali.

Il sistema deve inoltre rendere trasparente l'uso di risorse fisiche distribuite.

Per esempio, una stampante remota deve poter essere usata senza conoscere esplicitamente:

- il nodo su cui si trova;
- il percorso di rete;
- i dettagli di configurazione locale.

Servizi distribuiti come la posta elettronica devono essere disponibili nello stesso modo da qualunque macchina.

---

### **14. Robustezza dei sistemi distribuiti**

Un sistema distribuito puo' essere piu' robusto di un singolo sistema di elaborazione, ma solo se il sistema operativo gestisce correttamente guasti e malfunzionamenti.

Le funzioni principali sono:

- rilevamento dei guasti;
- mascheramento degli errori;
- riconfigurazione;
- ripristino.

---

### **15. Rilevamento dei guasti**

Il rilevamento dei guasti puo' essere effettuato tramite:

- monitoraggio periodico;
- handshaking tra macchine;
- timeout sulle richieste;
- computazioni duplicate con confronto dei risultati.

Se una macchina non risponde a un controllo periodico o non risponde entro un tempo massimo, puo' essere considerata non disponibile.

Se due computazioni duplicate producono risultati diversi, il sistema puo' rilevare un possibile errore dovuto a guasto.

> 📌 Il timeout e' una tecnica semplice per sospettare l'indisponibilita' di una macchina o connessione.

---

### **16. Mascheramento degli errori**

In applicazioni critiche non basta rilevare l'errore: bisogna mascherarlo.

Il mascheramento puo' essere ottenuto tramite:

- duplicazione delle risorse;
- computazioni replicate;
- votazione a maggioranza.

Con la votazione, la stessa computazione viene eseguita su piu' macchine.

Il risultato accettato e' quello scelto dalla maggioranza.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ Il mascheramento permette di continuare l'elaborazione anche se una parte del sistema produce risultati errati.

---

### **17. Riconfigurazione**

Quando viene rilevato un guasto, il sistema deve riconfigurarsi.

La riconfigurazione puo' richiedere:

- esclusione di un collegamento guasto;
- esclusione di una macchina guasta;
- aggiornamento delle tabelle di instradamento;
- spostamento di compiti su altri nodi;
- sostituzione temporanea di risorse non disponibili;
- accettazione di prestazioni degradate.

L'obiettivo e' continuare a erogare il servizio, anche se con funzionalita' o prestazioni ridotte.

---

### **18. Ripristino**

Quando il componente guasto viene riparato, il sistema deve reintegrarlo.

Il ripristino richiede:

- informare le altre macchine;
- aggiornare le tabelle;
- reinserire il nodo o collegamento nelle attivita' operative;
- garantire la consistenza delle informazioni nei vari siti;
- completare servizi rimasti sospesi.

Per esempio, messaggi di posta non consegnati a causa di un'interruzione devono essere completati quando la connettivita' torna disponibile.

---

### **19. Aspetti progettuali**

La progettazione di un sistema operativo per architetture distribuite deve garantire:

- trasparenza dell'allocazione delle risorse;
- mobilita' dell'utente;
- mobilita' della computazione;
- mobilita' dei dati;
- tolleranza ai guasti;
- scalabilita' delle architetture;
- scalabilita' delle funzioni.

La trasparenza deve riguardare:

- processori;
- dispositivi di memoria;
- periferiche;
- file;
- servizi.

L'obiettivo e' evitare che programmatori e utenti debbano conoscere dettagli non necessari sulla localizzazione fisica delle risorse.

> 💡 Come il file system nasconde la posizione fisica dei file sui dischi locali, il sistema operativo distribuito deve nascondere la posizione delle risorse nella rete.

---

### **20. Sintesi**

| Aspetto | Descrizione |
|---|---|
| **Architettura distribuita** | Insieme di nodi interconnessi tramite rete |
| **Eterogeneita'** | Differenze hardware, software e di rappresentazione dei dati |
| **Vantaggi** | Integrazione, condivisione, parallelismo, downsizing, affidabilita', scalabilita' |
| **Sistema operativo di rete** | Supporta risorse remote ma lascia visibile la rete |
| **Sistema operativo distribuito** | Nasconde la rete e mostra un unico sistema logico |
| **Migrazione dei dati** | Spostamento trasparente di file o porzioni di file |
| **Migrazione computazionale** | RPC, migrazione di processi, agenti mobili |
| **Robustezza** | Rilevamento, mascheramento, riconfigurazione, ripristino |
| **Progettazione** | Trasparenza, mobilita', tolleranza ai guasti, scalabilita' |

---

### **21. Conclusione**

I sistemi distribuiti estendono il modello del sistema operativo a un insieme di macchine interconnesse.

Il sistema operativo deve permettere cooperazione, condivisione e comunicazione tra nodi, gestendo al tempo stesso eterogeneita', guasti e localizzazione remota delle risorse.

La differenza fondamentale e' tra sistemi operativi di rete, che supportano l'uso di risorse remote lasciando visibile la rete, e sistemi operativi distribuiti, che nascondono la distribuzione e fanno apparire l'intera architettura come un unico sistema.
