## **Lezione 3: Proxy**

### **1. Il concetto di proxy**

Un **proxy** è una tecnica architetturale con cui si introduce un componente intermedio che **media la comunicazione** tra altri componenti, tipicamente un client e un server.

L'idea fondamentale è **disaccoppiare** la comunicazione diretta: client e server non dialogano più l'uno con l'altro in modo immediato, ma attraverso un'entità che si interpone e inoltra, filtra o rielabora il traffico.

> 📌 Un proxy rende la comunicazione indiretta introducendo un intermediario esplicito: non è un attaccante nascosto e non impersona segretamente una delle parti.

La presenza del proxy, nel modello classico, deve essere nota almeno alla parte che lo usa: il client sa di collegarsi al proxy, e il proxy si occupa poi di instaurare o far apparire la comunicazione verso il servizio finale.

Questo punto è importante perché distingue il proxy da tecniche di intercettazione occulta: il proxy **non sparisce dal modello**, ma diventa un componente dichiarato dell'architettura di comunicazione.

---

### **2. Connessioni reali e connessioni apparenti**

Nel funzionamento di un proxy bisogna distinguere tra:

- **connessioni reali**, cioè le connessioni effettivamente instaurate tra client e proxy e tra proxy e server;
- **connessione apparente**, cioè la relazione logica che l'utente percepisce come comunicazione client-server per ottenere un certo servizio.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Dal punto di vista operativo, il client non stabilisce una connessione end-to-end direttamente con il server remoto. Stabilisce una connessione con il proxy; il proxy, a sua volta, stabilisce o gestisce una seconda comunicazione verso il server o verso il backend opportuno.

Questo rende il proxy un punto naturale di:

- controllo;
- autenticazione;
- filtraggio;
- logging;
- mascheramento dell'architettura interna;
- ottimizzazione delle prestazioni applicative.

> ⚠️ La connessione client-server può sembrare unica dal punto di vista funzionale, ma dal punto di vista di rete è spezzata in più tratte reali.

---

### **3. Tipologie di proxy**

Esistono diversi tipi di proxy, usati per scopi differenti.

#### **3.1. Web proxy**

Un **web proxy** è usato soprattutto per traffico HTTP/HTTPS. Il client, invece di raggiungere direttamente il web server, invia la richiesta al proxy.

Il proxy può:

- inoltrare la richiesta al vero web server;
- servire una copia già presente in **cache**;
- verificare se la cache è ancora valida;
- aggiornare il contenuto se la copia locale è scaduta;
- ridurre carico e latenza per contenuti frequentemente richiesti.

Nel caso più semplice, quindi, dietro il web proxy ci sono i veri web server, mentre davanti c'è il client che ottiene una pagina senza necessariamente costringere il server originario a rigenerarla ogni volta.

#### **3.2. Anonymizing proxy**

Un **proxy di anonimizzazione** nasconde la sorgente originale della connessione verso l'esterno. Dal punto di vista del servizio remoto, il soggetto visibile non è più il client iniziale, ma il proxy o la catena di proxy attraversata.

Questa caratteristica ha una forte ambivalenza:

- può essere usata legittimamente per privacy, protezione dell'identità o separazione tra utente e destinazione;
- può essere usata in modo illecito per rendere più difficile attribuire un attacco;
- può essere sfruttata per far apparire la connessione come proveniente da un'altra area geografica e accedere a servizi vincolati alla localizzazione.

Proxy anonimi, distorting proxy e high-anonymity proxy cambiano il grado con cui viene nascosta o alterata l'informazione sull'origine reale della richiesta.

> ⚠️ L'anonimizzazione non è automaticamente sinonimo di sicurezza: la stessa tecnica può proteggere la privacy oppure ostacolare l'attribuzione di attività malevole.

#### **3.3. Reverse proxy**

Un **reverse proxy** consente a utenti esterni di accedere in modo controllato a risorse interne. In questo caso il proxy sta davanti ai server dell'organizzazione e riceve le richieste al posto loro.

È il modello tipico dei frontend HTTP che ricevono traffico pubblico e poi inoltrano le richieste al vero server applicativo o a un insieme di server backend.

#### **3.4. Proxy firewall**

Un **proxy firewall** è un proxy usato esplicitamente per scopi di filtraggio e sicurezza. Si mette in mezzo alla comunicazione non solo per inoltrare traffico, ma anche per introdurre controlli a livello applicativo che un packet filter tradizionale non può eseguire.

È particolarmente rilevante nel contesto dei firewall avanzati perché permette di usare informazioni più ricche dei soli header IP/TCP/UDP.

---

### **4. Reverse proxy**

Un reverse proxy può essere visto come un server esposto che fa da **frontend**: gli utenti esterni parlano con lui, mentre il vero server resta dietro.

Esempio tipico: un server HTTP pubblico riceve le richieste, effettua controlli preliminari e le inoltra al vero server web interno.

#### **4.1. Benefici del reverse proxy**

Il reverse proxy offre diversi vantaggi.

**Nasconde il vero server.**
L'utente esterno non vede direttamente quale server eroga realmente il servizio, quale software esegue, come è organizzato il backend o quanti server siano presenti dietro il proxy.

**Bilancia il carico.**
Dato che occupa una posizione strategica, può distribuire le richieste tra più server backend. In questo modo diventa anche un **load balancer**.

**Accelera il servizio.**
Può agire da **SSL accelerator** o **web accelerator**, gestendo operazioni costose come terminazione TLS, caching di contenuti statici, compressione o invio graduale dei dati al client.

**Aggiunge controlli applicativi.**
Prima di inoltrare la richiesta al server reale, può applicare autenticazione, filtraggio, verifiche protocollari e logging.

> 📌 Il reverse proxy protegge e ottimizza il backend perché concentra in un punto controllato l'accesso dall'esterno verso risorse interne.

#### **4.2. Architettura con firewall**

In una configurazione tipica, il reverse proxy si trova fuori dalla rete interna protetta, oppure in una zona intermedia come una DMZ.

Il flusso logico è il seguente:

1. un utente su Internet vuole raggiungere un web server;
2. la richiesta arriva a un indirizzo esposto dal firewall;
3. il firewall ridirige il traffico verso il reverse proxy;
4. il reverse proxy esegue controlli di autenticazione, filtraggio e verifica protocollare;
5. se la richiesta è ammessa, il proxy la inoltra al vero web server;
6. il server risponde e la risposta torna verso l'utente passando per il proxy.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Questa architettura semplifica anche la configurazione del firewall: il web server interno può essere configurato per accettare traffico solo dal reverse proxy, mentre il traffico esterno diretto al server reale viene negato o ridiretto.

#### **4.3. Varianti architetturali**

La stessa idea può essere realizzata in più modi.

In una prima variante, il reverse proxy è collocato in una zona esposta o semi-esposta e ha direttamente dietro di sé uno o più server. In questo caso il traffico non deve necessariamente rientrare in una rete interna attraverso un ulteriore passaggio del firewall: il proxy e i server backend possono trovarsi nella stessa area controllata.

In una seconda variante, i server reali stanno nella rete interna e il reverse proxy comunica con loro tramite un canale dedicato, per esempio una VPN. Anche in questo caso il punto pubblico resta il proxy, mentre l'accesso ai server effettivi avviene tramite una relazione controllata e specifica.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le configurazioni possono differire nei dettagli, ma l'obiettivo resta lo stesso: fare in modo che le richieste esterne arrivino al reverse proxy, vengano controllate, e solo dopo siano inoltrate ai server che erogano realmente il servizio.

---

### **5. Proxy firewall e application-level gateway**

Quando un proxy è usato come componente di protezione perimetrale prende spesso il nome di **proxy firewall** o **application-level gateway**.

Non bisogna immaginare necessariamente una singola macchina generica che fa da proxy per tutto. In molti casi si tratta di un firewall associato a una serie di **proxy applicativi**, cioè processi o moduli specializzati per singoli protocolli: HTTP, FTP, SMTP, SIP e così via.

Ogni proxy applicativo conosce almeno una parte della semantica del protocollo che deve gestire. Per questo può esaminare contenuti e comandi che un packet filter, fermandosi agli header di rete e trasporto, non riuscirebbe a interpretare.

#### **5.1. Consapevolezza del client**

Un limite importante è che, spesso, l'applicativo client deve essere consapevole dell'esistenza del proxy.

Questo può richiedere:

- configurazione manuale del client;
- modifica dell'applicazione;
- uso di librerie specifiche;
- supporto nativo del protocollo di proxy.

Il proxy rompe quindi il modello client-server originale: introduce un elemento intermedio che deve essere previsto dall'architettura o compensato con meccanismi di trasparenza.

#### **5.2. Mascheramento e autenticazione**

Un proxy firewall può mascherare o rinumerare gli indirizzi interni, rendendo meno visibile la struttura della rete protetta.

Soprattutto, può richiedere **autenticazione dell'utente**. Questo corregge uno dei limiti principali del filtraggio basato solo su indirizzi IP: sapere che una certa macchina ha generato traffico non significa sapere quale utente stia compiendo l'azione né se sia autorizzato a farlo.

> 📌 In una policy di sicurezza è più utile sapere "chi sta facendo cosa" che limitarsi a sapere "quale indirizzo IP sta comunicando".

Fingersi un indirizzo IP o una macchina può essere relativamente più semplice, soprattutto in presenza di configurazioni deboli o reti locali compromesse. Fingersi un utente autenticato è possibile in caso di furto credenziali o compromissione del client, ma richiede un attacco diverso e più specifico rispetto al semplice spoofing di rete.

#### **5.3. Controllo del payload e degli input**

Un application-level gateway può controllare il **payload** e gli input applicativi.

Questo è rilevante per attacchi come i buffer overflow: se il proxy conosce la grammatica del protocollo e i limiti attesi dei campi, può bloccare comandi, lunghezze o dati anomali prima che raggiungano il server interno.

Le regole risultano spesso più granulari ma più vicine al linguaggio dell'applicazione: invece di ragionare solo su porte e indirizzi, si può ragionare su comandi, metodi, parametri o sequenze applicative.

> 💡 Il packet filter vede soprattutto "da dove a dove"; il proxy applicativo può vedere anche "che cosa si sta chiedendo".

---

### **6. Vantaggi e limiti degli application-level gateway**

#### **6.1. Vantaggi**

Un application-level gateway offre vantaggi rilevanti:

- impedisce connessioni dirette tra interno ed esterno;
- protegge meglio i server, perché il server reale non è il primo endpoint esposto;
- può autenticare il client;
- può analizzare comandi e dati applicativi;
- può registrare log più ricchi, associando eventi a utenti e non solo a indirizzi IP;
- consente controlli specifici per protocollo quando esiste il relativo proxy.

Questa capacità è particolarmente utile quando il protocollo applicativo contiene informazioni decisive per la sicurezza, come comandi FTP, richieste HTTP, operazioni SMTP o messaggi di segnalazione SIP.

#### **6.2. Costi e limiti**

Il proxy applicativo introduce anche costi.

Ogni applicazione richiede un modulo specifico. Se nasce un nuovo protocollo o un nuovo applicativo, il firewall non può analizzarlo correttamente finché non esiste un proxy dedicato o un plugin aggiornato.

Inoltre l'analisi applicativa consuma risorse e introduce latenza:

- il traffico deve arrivare al proxy;
- il sistema operativo deve consegnarlo al processo o modulo applicativo;
- il proxy deve analizzarlo;
- il proxy deve ricreare o inoltrare la comunicazione verso il server.

Non si tratta quindi di un semplice transito di pacchetti. Il proxy diventa un endpoint effettivo della comunicazione, e questo ha un impatto prestazionale, soprattutto per applicazioni in tempo reale.

> ⚠️ L'application gateway migliora la qualità del filtraggio, ma lo paga con latenza, consumo di risorse e maggiore complessità di supporto.

---

### **7. Il proxy come nuovo punto vulnerabile**

L'introduzione di un proxy aumenta la capacità di controllo, ma introduce anche ulteriore software.

Questo è un trade-off centrale: ogni componente software aggiunto può contenere vulnerabilità. Se il proxy gira direttamente sul firewall o su un dispositivo dedicato al filtraggio, una vulnerabilità nel modulo applicativo può esporre il sistema che dovrebbe proteggere il perimetro.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Un esempio discusso dal docente è una vulnerabilità del 2018 legata al protocollo SIP e a funzionalità ALG/NAT su dispositivi Cisco. Il punto didattico non è il dettaglio della singola CVE, ma il principio generale: più funzionalità protocollari si aggiungono al firewall, più cresce il codice esposto e quindi la superficie d'attacco.

> ⚠️ Un firewall deve essere sicuro prima di tutto su se stesso. Se il componente di protezione diventa facilmente attaccabile, il perimetro perde affidabilità.

Questo non significa che i proxy applicativi vadano evitati. Significa che bisogna valutare il rapporto tra:

- rischio introdotto da nuovo software;
- beneficio di sicurezza ottenuto da un filtraggio più profondo;
- criticità del dispositivo che ospita il proxy;
- necessità reale del controllo applicativo.

Un software di sicurezza può avere vulnerabilità, ma la sua assenza può lasciare il sistema ancora più esposto rispetto agli attacchi che quel software avrebbe intercettato.

La regola pragmatica resta: sui firewall è preferibile eseguire il minor numero possibile di servizi, ma nei contesti in cui il filtraggio applicativo è necessario bisogna aggiungerlo in modo controllato, aggiornato e monitorato.

---

### **8. Proxy e traffico cifrato**

Un limite importante dei proxy applicativi emerge quando il traffico è protetto da meccanismi di sicurezza applicativa, per esempio TLS.

Se il payload è cifrato end-to-end tra client e server, il proxy non può ispezionare i dati interni al pacchetto. Può ancora osservare metadati, indirizzi, porte e parte della negoziazione, ma non può applicare regole basate sul contenuto applicativo cifrato.

Per fare filtraggio applicativo su traffico cifrato, il proxy deve diventare un punto esplicito di terminazione o interposizione crittografica:

1. instaura una connessione sicura con il client;
2. decifra il traffico in un punto controllato;
3. applica le policy;
4. instaura una seconda connessione sicura verso il server.

Questa tecnica richiede che l'organizzazione gestisca correttamente certificati, fiducia e consenso operativo, perché introduce un'ulteriore entità autorizzata a vedere il contenuto in chiaro.

> ⚠️ Se il contenuto resta cifrato end-to-end, il proxy non può applicare regole sul payload. Per farlo deve essere parte esplicita della terminazione crittografica.

---

### **9. Varianti di application proxy**

#### **9.1. Transparent proxy**

Un **transparent proxy** cerca di ridurre l'impatto operativo sul client. Il traffico viene intercettato e indirizzato verso il proxy senza richiedere una configurazione esplicita dell'applicazione da parte dell'utente.

Questa soluzione migliora l'usabilità, ma non elimina il problema architetturale: il proxy continua a dover interpretare il traffico e a introdurre un punto intermedio nella comunicazione.

#### **9.2. Strong application proxy**

Uno **strong application proxy** consente solo comandi e dati esplicitamente ammessi.

Dal punto di vista della sicurezza, è il modello più rigoroso: invece di bloccare solo ciò che appare pericoloso, consente esclusivamente ciò che rientra nella grammatica e nella policy prevista.

Il limite è la rigidità. Se si specifica solo un sottoinsieme delle funzionalità legittime, si può ridurre la capacità del servizio di funzionare correttamente.

> 📌 Lo strong application proxy è coerente con il principio del default deny: passa solo ciò che è conosciuto, previsto e autorizzato.

---

### **10. Riepilogo sugli application gateway**

Un application gateway è un firewall che:

- mantiene lo stato delle connessioni TCP;
- reindirizza il traffico come se fosse originato dal firewall o dal gateway;
- usa più proxy applicativi, spesso come plugin o moduli specifici;
- processa solo i protocolli per cui esiste un proxy attivo;
- fa da intermediario reale tra client e server.

I vantaggi principali sono:

- assenza di connessioni dirette tra interno ed esterno;
- autenticazione del client;
- analisi dei comandi applicativi;
- migliore controllo del traffico da e verso la rete;
- log più informativi.

Gli svantaggi principali sono:

- latenza;
- impatto sulle prestazioni;
- difficoltà con applicazioni in tempo reale;
- supporto limitato per nuovi protocolli;
- necessità di plugin aggiornati;
- esposizione diretta del proxy come target.

Rispetto a un packet filter classico, il proxy applicativo è più direttamente esposto: il traffico è destinato a lui, quindi eventuali vulnerabilità nel proxy sono raggiungibili dall'esterno. In un packet filter tradizionale, invece, molte vulnerabilità del dispositivo sono legate soprattutto alle interfacce di gestione, che dovrebbero essere accessibili solo da reti amministrative controllate.

---

### **11. Circuit-level gateway**

Oltre agli application-level gateway esistono proxy firewall che operano a un livello più basso: i **circuit-level gateway**.

Un circuit-level gateway non interpreta il protocollo applicativo e non analizza il payload. Si colloca al livello in cui viene instaurata la connettività tra client e server, cioè principalmente a livello di trasporto.

#### **11.1. Funzionamento**

Il funzionamento è il seguente:

1. il client crea una connessione TCP verso il circuit gateway;
2. chiede al gateway di stabilire una connessione verso il vero server target;
3. il gateway verifica autorizzazione e, se previsto, autenticazione;
4. il gateway contatta il server;
5. il traffico viene inoltrato attraverso le due connessioni.

Il gateway è quindi un intermediario, ma non a livello applicativo: non comprende i comandi dell'applicazione, si limita a gestire il circuito di comunicazione.

#### **11.2. Vantaggi**

Il circuit-level gateway protegge i server interni perché crea connessioni per conto loro. Il server non riceve direttamente il traffico originario del client esterno, ma traffico proveniente dal gateway.

Questo isolamento può ridurre l'esposizione a:

- attacchi legati alla fase di instaurazione della connessione;
- manipolazioni di basso livello;
- problemi di frammentazione IP;
- connessioni non autorizzate verso host protetti.

Può inoltre introdurre autenticazione del client prima di instaurare il circuito.

#### **11.3. Limiti**

Il limite principale è che il circuit-level gateway resta cieco rispetto al contenuto applicativo.

Se l'attacco è contenuto nei comandi dell'applicazione, nei parametri o nel payload, il circuit gateway non ha gli strumenti semantici per riconoscerlo. Per questo conserva molte limitazioni dei packet filter, pur offrendo un livello di interposizione più forte.

Anche in questo caso le applicazioni possono dover essere modificate o configurate per sapere che devono passare attraverso il gateway.

---

### **12. Confronto tra packet filter, circuit gateway e application gateway**

Le tre soluzioni agiscono a livelli diversi.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Un **packet filter** controlla il traffico che attraversa il perimetro. Le sue decisioni sono basate soprattutto su indirizzi, porte, protocolli, flag e stato della connessione. Tutto il traffico deve passare dal firewall: se esistono percorsi alternativi, il modello di sicurezza perde senso.

Un **circuit gateway** tiene traccia della connettività e fa da intermediario per la parte di trasporto. Nel caso TCP/IP, è il gateway che instaura connessioni per conto degli endpoint effettivi.

Un **application gateway** interpreta il protocollo specifico dell'applicazione. Può ispezionare comandi, dati e sequenze applicative, e può decidere se inoltrare, bloccare o modificare il traffico in base a regole più ricche.

> 📌 Packet filter, circuit gateway e application gateway non sono intercambiabili: aumentano progressivamente profondità di analisi e complessità operativa.

In entrambe le famiglie basate su gateway è possibile richiedere autenticazione prima di avviare o consentire la connettività, perché il gateway è il destinatario immediato della richiesta.

---

### **13. Bastion host**

I proxy e i gateway hanno bisogno di una piattaforma su cui girare. Questa piattaforma può coincidere con il firewall oppure essere un host addizionale dedicato.

Quando un host implementa servizi proxy o gateway in una posizione critica del perimetro, prende il nome di **bastion host**.

Il bastion host è un target naturale per gli attacchi, quindi deve essere progettato e mantenuto con particolare rigore.

Caratteristiche richieste:

- sistema operativo sicuro e rinforzato;
- superficie software minima;
- solo i servizi strettamente necessari;
- solo i proxy effettivamente richiesti;
- ogni servizio esposto solo al sottoinsieme di host che deve usarlo;
- autenticazione robusta;
- logging e auditing;
- monitoraggio costante;
- aggiornamenti rapidi.

> ⚠️ Il bastion host non è un server qualunque: è un punto critico della sicurezza perimetrale e va trattato come componente ad alta esposizione.

---

### **14. SOCKS**

Una tecnologia storicamente importante per realizzare circuit-level gateway è **SOCKS**.

SOCKS è un proxy di livello trasporto, quindi opera al **livello 4** e permette a un client di raggiungere un server passando attraverso un server SOCKS intermedio.

È nato come soluzione per far transitare applicazioni attraverso firewall senza esporre direttamente la rete interna. Le versioni più moderne hanno introdotto funzionalità più robuste di autenticazione e supporto a più protocolli.

#### **14.1. Funzionamento di SOCKS**

Per usare SOCKS, il client non apre direttamente il socket verso il server finale. Usa invece funzioni o librerie compatibili con SOCKS che instaurano un canale verso il server SOCKS.

Il server SOCKS:

- riceve la richiesta del client;
- verifica le ACL;
- controlla eventuali credenziali;
- apre il canale verso la destinazione richiesta;
- collega il canale interno con quello esterno;
- maschera l'indirizzo interno dietro il proxy.

In pratica, l'applicazione deve essere compatibile con SOCKS oppure deve essere modificata o ricompilata con librerie adatte.

#### **14.2. SOCKS v4 e SOCKS v5**

SOCKS v4 era una tecnologia utile ma limitata:

- supportava principalmente TCP;
- aveva meccanismi di autenticazione deboli;
- offriva poche garanzie aggiuntive di sicurezza.

SOCKS v5 migliora il modello:

- supporta TCP e UDP;
- introduce autenticazione più robusta;
- può supportare meccanismi come username/password o GSS-API;
- può supportare IPv6;
- può proteggere meglio la comunicazione tra client e server SOCKS.

Lo standard di riferimento per SOCKS v5 è la RFC 1928, con RFC collegate per autenticazione e meccanismi aggiuntivi.

> ⚠️ SOCKS riduce l'esposizione diretta della rete interna, ma non è trasparente per tutte le applicazioni: il supporto lato client resta un vincolo operativo.

---

### **15. Trasparenza, plugin e prestazioni**

La mancanza di trasparenza è uno dei limiti storici dei proxy.

Per ridurlo, molti firewall moderni cercano di avvicinare il modello proxy all'uso trasparente tramite plugin o analizzatori di protocollo locali. Quando arriva traffico di un certo tipo, il firewall lo può indirizzare a un modulo dedicato che lo analizza come farebbe un proxy applicativo.

Questa soluzione rende più semplice l'uso operativo, ma non elimina il costo tecnico dell'interposizione.

Il proxy deve comunque:

- ricevere pacchetti destinati a lui;
- farli elaborare dal sistema operativo;
- analizzarli a livello applicativo o di trasporto;
- ricreare o inoltrare la comunicazione.

Rispetto a un packet filter che valuta e inoltra pacchetti, il proxy è più pesante perché termina almeno una parte della comunicazione.

> 📌 Anche quando il proxy appare trasparente all'utente, non è trasparente dal punto di vista delle prestazioni: introduce sempre elaborazione aggiuntiva.

---

### **16. Frammentazione IP e proxy**

La frammentazione IP si verifica quando un datagramma deve attraversare reti con MTU più piccola rispetto alla dimensione originaria del pacchetto.

Dal punto di vista del filtraggio, la frammentazione è problematica perché non tutti i frammenti contengono le stesse informazioni. Per esempio, l'header TCP o UDP completo è presente nel primo frammento, mentre i frammenti successivi possono non contenere porte e flag utili al packet filter.

Un attaccante può sfruttare questa caratteristica frammentando deliberatamente il traffico per rendere più difficile al firewall capire che cosa stia attraversando il perimetro.

Con un proxy, il problema cambia: il proxy è il destinatario effettivo della comunicazione sulla tratta esterna. Di conseguenza deve ricomporre i frammenti prima di decidere che cosa fare.

Dopo la ricomposizione può:

- analizzare il pacchetto completo;
- applicare le policy;
- scartare il traffico anomalo;
- inoltrare solo traffico accettabile verso il server interno.

> 📌 Gli attacchi basati su frammentazione IP sono più difficili contro un proxy perché il proxy deve riassemblare il traffico prima di reinoltrarlo.

---

### **17. Considerazioni conclusive**

I proxy firewall sono tecnologie di filtraggio più avanzate dei packet filter tradizionali.

Le due categorie principali sono:

- **application-level gateway**, che analizzano anche il protocollo applicativo;
- **circuit-level gateway**, che si interpongono a livello di connessione senza interpretare il payload.

Il vantaggio principale è la possibilità di fare un'analisi più raffinata del traffico. Nel caso degli application gateway, il firewall può leggere comandi e dati applicativi, almeno quando il traffico non è cifrato o quando il proxy è autorizzato a terminarlo.

Il costo è dato da:

- prestazioni peggiori rispetto al filtraggio semplice;
- maggiore complessità;
- necessità di plugin specifici;
- minore trasparenza;
- maggiore superficie software esposta.

Storicamente, i proxy esistevano già prima di molte soluzioni firewall moderne, ma sono stati in parte superati dai packet filter e dagli stateful firewall perché più semplici, più veloci e meno intrusivi.

Oggi stanno riemergendo perché molte vulnerabilità rilevanti sono applicative: per contrastarle non basta sapere chi comunica con chi, bisogna leggere e comprendere ciò che viene comunicato.

> ✅ I proxy firewall aumentano profondità e precisione del controllo, ma richiedono architetture più attente: il componente che vede di più è anche quello che deve essere protetto meglio.
