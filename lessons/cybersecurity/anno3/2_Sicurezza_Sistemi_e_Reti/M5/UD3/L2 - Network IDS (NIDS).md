## **Lezione 2: Network IDS (NIDS)**

### **1. Introduzione**

Un **Network Intrusion Detection System** o **NIDS** è un IDS orientato al traffico di rete: osserva pacchetti, flussi e sequenze di comunicazione per individuare pattern sospetti, generare eventi di sicurezza e, in alcune configurazioni, interagire con altri componenti difensivi.

Rispetto a un **Host-Based IDS** (**HIDS**), che osserva soprattutto un singolo host, un NIDS è pensato per controllare punti strategici della rete: collegamenti verso Internet, DMZ, segmenti interni, porte mirror degli switch, router o firewall.

Il punto importante non è che il NIDS "veda tutto" in assoluto. Vede ciò che passa dal punto in cui è collocato. Per questo l'architettura e il posizionamento dei sensori sono parte essenziale della qualità della rilevazione.

> 📌 Un NIDS non è solo un programma che sniffa pacchetti: è un'architettura composta da sensori, logica di analisi, gestione degli allarmi, comunicazione sicura tra componenti e policy operative.

---

### **2. Componenti di un Network IDS**

Un'architettura NIDS tipica contiene tre componenti logici.

I **sensor** sono i componenti che osservano il traffico o raccolgono informazioni dagli host. Il loro compito è:

- controllare pacchetti, flussi o log;
- individuare pattern sospetti;
- generare eventi di sicurezza;
- in alcuni casi attivare interazioni con il sistema, per esempio notifiche, reset TCP o richieste di modifica al firewall.

Il **director** coordina i sensori. Riceve eventi e allarmi, mantiene o distribuisce il database dei pattern noti, aggiorna le firme, imposta configurazioni e centralizza le decisioni di gestione.

L'**IDS Message System** è il sistema di comunicazione tra i componenti dell'IDS. Deve garantire uno scambio **sicuro e affidabile** di informazioni tra sensori, analizzatori e director, perché un IDS distribuito perde valore se i suoi componenti non riescono a comunicare in modo coerente.

> 💡 Il sensore osserva, il director coordina, il message system collega. La qualità dell'IDS dipende dalla cooperazione tra questi elementi, non solo dalla precisione del singolo sensore.

---

### **3. Dispiegamento in una rete con DMZ**

Il docente considera una rete tradizionale a tre zone:

- rete esterna;
- DMZ;
- rete interna;
- firewall tra le zone.

![](imgs/Pasted%20image%2020260709175938.png)

In uno scenario di questo tipo, i **network sensor** possono essere collocati in più punti.

Un primo sensore può essere posizionato **prima del firewall** o sul firewall ma **prima del filtraggio**, così da osservare che cosa arriva dall'esterno prima che il firewall scarti o modifichi il traffico.

Altri sensori possono essere collocati nella **DMZ**, dove sono presenti servizi esposti, e nella **rete interna**, dove è importante rilevare traffico anomalo, movimenti laterali o attività generate da host già compromessi.

Accanto ai sensori di rete possono esserci anche **host IDS** installati su server e macchine rilevanti. Anche se si parla di Network IDS, integrare informazioni host-based è utile perché un pattern di attacco può essere evidente solo correlando:

- pacchetti osservati in rete;
- log applicativi;
- log di sistema;
- modifiche locali a file o processi;
- eventi di autenticazione.

Il flusso è bidirezionale:

- i sensori inviano eventi e dati al director;
- il director può inviare ai sensori configurazioni, aggiornamenti delle firme e istruzioni operative.

> 📌 Un NIDS efficace non lavora in isolamento: correla ciò che vede sulla rete con ciò che accade sugli host.

---

### **4. Standardizzazione e interoperabilità**

Per far cooperare componenti diversi servono formati condivisi.

Il problema riguarda due piani distinti:

- il formato dei **pattern** o delle **signature** usate per riconoscere attacchi;
- il formato degli **eventi**, degli **allarmi** e dei messaggi scambiati tra componenti IDS.

Per le firme non esiste un unico standard universale. Nella pratica è molto diffuso il formato usato da **Snort**, che è diventato uno standard di fatto per molte regole di rilevazione.

Per allarmi, eventi e scambio di messaggi esistono invece formati più formalizzati. Alcuni sono proprietari, per esempio legati all'ecosistema Cisco o ad altri vendor; altri sono stati standardizzati in ambito IETF.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Gli obiettivi degli standard sono:

- permettere la comunicazione tra componenti dello stesso IDS;
- consentire interoperabilità tra strumenti di vendor diversi;
- raccogliere dati da più IDS;
- produrre statistiche aggregate;
- supportare attività di prevenzione, risposta e remediation;
- facilitare scambio di informazioni tra enti, CERT, SOC o organizzazioni diverse.

Un esempio tipico è la raccolta di statistiche a livello nazionale o settoriale sui pattern di attacco rilevati da più sistemi IDS.

---

### **5. Flusso dei dati in un NIDS**

Un IDS distribuito trasforma osservazioni grezze in decisioni operative.

![](imgs/Pasted%20image%2020260709175953.png)

La catena logica è la seguente:

- una **data source** rappresenta l'attività osservabile, per esempio traffico, log o eventi host;
- il **sensor** cattura l'attività e decide se generare un evento;
- l'**analyzer** valuta gli eventi per capire se indicano una condizione rilevante;
- il **manager** gestisce gli alert;
- l'**operator** può ricevere notifiche, analizzare gli alert e guidare le decisioni;
- l'**administrator** definisce le security policy che determinano che cosa deve essere rilevato e come bisogna reagire.

La distinzione tra **activity**, **event**, **alert**, **notification** e **response** è importante:

- un'activity è un comportamento osservato;
- un event è una rilevazione significativa prodotta dal sensore;
- un alert è un allarme derivato dall'analisi degli eventi;
- una notification è la comunicazione dell'allarme a un operatore o a un altro sistema;
- una response è l'azione conseguente, automatica o manuale.

Tutto è guidato da **security policy**. Come le policy di controllo accessi dicono chi può fare cosa su quali oggetti, le policy IDS indicano:

- quali pattern generano eventi;
- quali eventi diventano alert;
- quali alert richiedono notifica;
- quali alert richiedono intervento umano;
- quali alert possono attivare una risposta automatica;
- quando modificare la configurazione di un sensore o di un dispositivo di rete.

> ⚠️ Se le policy sono troppo aggressive, aumentano i falsi positivi. Se sono troppo permissive, l'IDS produce pochi allarmi ma rischia di non rilevare attacchi reali.

---

### **6. IDMEF e IDXP**

#### **6.1. IDMEF**

**IDMEF** significa **Intrusion Detection Message Exchange Format**.

È un formato per rappresentare messaggi di intrusion detection, in particolare alert, in modo strutturato. Il docente sottolinea alcune proprietà:

- è indipendente dal protocollo di rete sottostante;
- supporta internazionalizzazione e localizzazione;
- permette aggregazione e filtraggio dei dati;
- viene tipicamente usato dal manager o da componenti di correlazione.

Il formato è basato su XML e consente di rappresentare informazioni come:

- analizzatore che ha generato l'allarme;
- nodo o sensore coinvolto;
- sorgente dell'attività sospetta;
- destinazione;
- classificazione dell'attacco;
- riferimenti a vulnerabilità note, per esempio CVE;
- link o riferimenti per approfondimento.

![](imgs/Pasted%20image%2020260709180011.png)

L'aspetto didattico importante non è memorizzare ogni tag XML, ma capire perché un formato strutturato è utile: rende gli alert scambiabili, filtrabili e correlabili da strumenti diversi.

#### **6.2. IDXP**

**IDXP** significa **Intrusion Detection Exchange Protocol**.

È un protocollo pensato per lo scambio di messaggi tra componenti IDS. È definito tramite RFC e si appoggia a profili di sicurezza. Il profilo base indicato dal docente è **TLS**, usato per proteggere la comunicazione tra componenti.

IDMEF descrive il formato dei messaggi; IDXP riguarda il trasporto e lo scambio sicuro di tali messaggi.

> 📌 Formato e protocollo non sono la stessa cosa: IDMEF struttura l'informazione, IDXP aiuta a scambiarla in modo controllato.

---

### **7. SDEE e IODEF**

#### **7.1. SDEE**

**SDEE** significa **Security Device Event Exchange**.

È un formato/protocollo di scambio eventi nato in ambito vendor, molto legato agli apparati Cisco e a sistemi proprietari. Il docente lo presenta come uno standard di fatto, ma chiuso.

Caratteristiche principali:

- scambio di messaggi in XML;
- paradigma Web Services;
- trasporto tipicamente su HTTP o HTTPS;
- uso pratico in ambienti proprietari o integrati da uno stesso ecosistema.

Il limite è evidente: funziona bene quando gli strumenti parlano quel formato, ma non è un vero standard aperto e universale.

#### **7.2. IODEF**

**IODEF** significa **Incident Object Description and Exchange Format**.

È pensato per scambiare informazioni sugli incidenti, non solo sul singolo alert. Può essere usato tra enti diversi per:

- condividere dati su eventi e incidenti;
- ottenere statistiche;
- valutare rischi;
- supportare correlazione e risposta coordinata.

Rispetto a un alert isolato, IODEF guarda al contesto dell'incidente: che cosa è successo, quali sistemi sono coinvolti, quali indicatori sono stati osservati e quali conseguenze operative ne derivano.

---

### **8. Dal rilevamento alla prevenzione: ponte verso gli IPS**

Un IDS è principalmente un sistema di **detection**. Può notificare, registrare, correlare e talvolta interagire con altri strumenti, ma non nasce necessariamente per impedire direttamente l'attacco.

Quando si spinge il sistema verso la reazione automatica e la prevenzione si entra nel mondo degli **Intrusion Prevention System** o **IPS**.

Il docente avverte però che il termine IPS è spesso usato commercialmente in modo ampio. Non sempre indica un singolo prodotto: più correttamente indica una tecnologia o una strategia composta da più componenti, anche a livelli diversi, che cooperano per prevenire il danno.

Un esempio è un firewall dinamico distribuito che, appena un pattern di attacco sembra materializzarsi, modifica regole o blocca traffico.

Il problema centrale è il falso positivo:

- in un IDS un falso positivo genera rumore e carico operativo;
- in un IPS un falso positivo può troncare una connessione lecita o bloccare un'applicazione aziendale.

> ⚠️ Il falso positivo è sempre un problema, ma in un IPS ha un costo immediato: l'errore non produce solo un allarme sbagliato, produce un'azione sbagliata.

---

### **9. Honeypot e honeynet**

Per riconoscere pattern d'attacco si possono usare repository di firme, database di vulnerabilità e pattern noti. Un'altra tecnica è osservare direttamente gli attaccanti in un ambiente controllato.

Un **honeypot** è un sistema deliberatamente esposto o vulnerabile, costruito per attirare attaccanti e studiarne il comportamento.

Una **honeynet** è una rete di honeypot o un segmento di rete progettato per sembrare interessante, ma separato dalla produzione reale.

![](imgs/Pasted%20image%2020260709180028.png)

Il docente descrive una rete finta, distinta dalla vera DMZ, con dispositivi e servizi configurati in modo da attirare attacchi. L'idea è che molti attaccanti, soprattutto bot automatici, cerchino servizi esposti, versioni vulnerabili o configurazioni deboli.

I vantaggi sono:

- osservare attacchi reali in un ambiente controllato;
- imparare nuovi pattern;
- aggiornare regole e firme;
- raccogliere evidenze su tecniche, strumenti e sequenze operative;
- studiare anche comportamenti non ancora classificati.

Gli honeypot possono aiutare anche nella ricerca di **zero-day**, perché espongono bersagli apparentemente vulnerabili e permettono di vedere se qualcuno sfrutta debolezze non ancora note.

Il rischio è altrettanto importante: si stanno attirando attaccanti dentro una rete controllata. L'isolamento deve quindi essere rigoroso.

Contromisure essenziali:

- separazione forte dalla rete di produzione;
- monitoraggio continuo;
- controllo del traffico in uscita dalla honeynet;
- impossibilità per l'attaccante di usare l'honeypot come ponte verso sistemi reali;
- procedure di reset e analisi forense dell'ambiente compromesso.

> ⚠️ Un honeypot non è "un server debole lasciato lì": è un ambiente deliberatamente vulnerabile, ma progettato per essere osservabile, isolato e sacrificabile.

---

### **10. Snort come strumento NIDS**

**Snort** è uno degli strumenti storici più importanti nel mondo NIDS.

Nel contesto della lezione viene presentato come uno strumento gratuito o comunque utilizzabile in modalità gratuita, molto diffuso, testato, supportato e oggi legato all'ecosistema Cisco. La sua importanza didattica deriva dal fatto che mostra concretamente come un NIDS possa essere configurato tramite regole.

Snort può funzionare come:

- **sniffer**, leggendo e visualizzando pacchetti;
- **packet logger**, salvando pacchetti su disco;
- **Network IDS**, applicando regole di detection;
- **modalità inline/IPS**, quando lavora insieme a meccanismi di filtraggio come `iptables`.

Snort usa meccanismi di cattura dei pacchetti basati su librerie come **libpcap**. Può anche salvare traffico in formato compatto e binario, compatibile con strumenti di analisi successiva come `tcpdump` o Wireshark.

Il vantaggio del formato binario è doppio:

- acquisizione più efficiente;
- possibilità di riesaminare a posteriori il traffico associato a un evento sospetto.

Questa conservazione è utile quando un pattern non è ancora classificato ma appare anomalo: il traffico registrato diventa evidenza tecnica per analisi successive.

---

### **11. Configurazione di Snort**

Il file principale è `snort.conf`.

Contiene:

- variabili di configurazione, come reti interne, server fidati e indirizzi rilevanti;
- impostazioni pensate anche per ridurre falsi positivi;
- configurazione dei preprocessori;
- formato dell'output;
- eventuale invio verso database, file, syslog o formati standard;
- elenco dei file di regole da caricare.

I **preprocessor** sono moduli che permettono analisi più estese dei pacchetti prima dell'applicazione delle regole. Possono normalizzare traffico, ricostruire sessioni, interpretare protocolli o preparare informazioni utili alla detection.

Due famiglie di file sono centrali:

- i file `*.rules`, che contengono le regole operative;
- `classification.conf`, che associa categorie e priorità agli allarmi.

Le regole sono normalmente scritte su una singola riga e hanno due parti logiche:

- **header**, con azione, protocollo, indirizzi e porte;
- **options**, con messaggi, contenuti da cercare, condizioni aggiuntive e metadati.

---

### **12. Struttura di una regola Snort**

Il formato generale è:

```text
azione protocollo sorgente porta_sorgente direzione destinazione porta_destinazione (opzioni)
```

La parte di header risponde a domande operative:

- quale azione compiere;
- quale protocollo osservare;
- quale sorgente considerare;
- quale destinazione considerare;
- quale direzione del traffico analizzare;
- quali porte coinvolgere.

Le azioni principali sono:

- `alert`, che genera un allarme;
- `log`, che registra il pacchetto;
- `pass`, che ignora il pacchetto;
- `activate`, che genera un alert e attiva una regola dinamica;
- `dynamic`, che resta inattiva finché non viene attivata da una regola `activate`.

La freccia `->` indica la direzione dal soggetto alla destinazione. Snort supporta anche la forma bidirezionale `<>`; se si vuole invertire il verso, normalmente si scambiano sorgente e destinazione.

La parte `options` permette controlli più approfonditi. Alcune opzioni tipiche sono:

- `msg`, per il messaggio di alert;
- `content`, per cercare una stringa o una sequenza di byte nel payload;
- `nocase`, per ricerca non sensibile a maiuscole/minuscole;
- `logto`, per loggare in un file specifico;
- test su campi IP/TCP/ICMP, per esempio TTL, TOS, identificativo IP, flag, frammentazione, dimensione del payload, tipo ICMP o caratteristiche RPC.

> 📌 La differenza rispetto a un packet filter classico è che Snort può cercare pattern nel payload e combinare condizioni più ricche di quelle disponibili nei soli header.

---

### **13. Esempi di regole Snort**

Esempio di allarme su un possibile tentativo Telnet:

```snort
alert tcp any any -> 193.205.161.191 23 (msg:"Tentativo di connessione Telnet"; content:"Last login";)
```

La regola osserva traffico TCP verso l'host indicato e la porta 23, cercando nel payload la stringa `Last login`.

Esempio di accesso esterno sospetto a `mountd`:

```snort
alert tcp !192.168.1.0/24 any -> 192.168.1.0/24 111 (content:"|00 01 86 a5|"; msg:"external mountd access";)
```

Qui la sorgente è negata rispetto alla rete interna: la regola guarda traffico proveniente dall'esterno verso la rete interna sulla porta 111.

Esempio con variabili d'ambiente:

```snort
alert tcp $EXTERNAL_NET any -> $HTTP_SERVERS 80 (msg:"WEB-IIS cmd.exe access"; content:"cmd.exe"; nocase;)
```

Questa regola usa variabili come `$EXTERNAL_NET` e `$HTTP_SERVERS` e cerca `cmd.exe` nel traffico HTTP, tipico di alcune classi di attacco applicativo contro server web IIS.

> ⚠️ Regole di questo tipo sono potenti ma delicate: una stringa cercata nel payload può comparire anche in traffico benigno, quindi contesto e tuning sono fondamentali.

---

### **14. Modalità operative di Snort**

Snort può essere usato in più modalità.

In **sniffer mode** legge i pacchetti dalla rete e li visualizza come flusso continuo:

```bash
snort -v -i eth0
```

In **packet logger mode** registra i pacchetti su disco:

```bash
snort -dev -i eth0 -l ./log
```

In **IDS mode** carica il file di configurazione e usa le regole per registrare o segnalare solo pacchetti sospetti:

```bash
snort -c snort.conf -i eth0
```

Gli alert possono essere scritti su file, inviati a syslog o raccolti da applicazioni terze che svolgono correlazione dei log e gestione centralizzata degli allarmi.

> 💡 Snort da solo può generare alert, ma in un ambiente reale serve spesso un sistema di raccolta, correlazione e presentazione che trasformi i log in informazioni operative.

---

### **15. Snort in modalità inline**

La modalità inline avvicina Snort al comportamento di un IPS.

In questo scenario Snort riceve i pacchetti da un meccanismo di filtraggio, per esempio `iptables`, che usa un target come `QUEUE` per consegnare i pacchetti al motore di analisi.

Snort deve essere compilato o configurato con supporto alla modalità inline. Dopo l'analisi, può indicare al sistema di filtraggio che cosa fare.

Azioni principali:

- `drop`, per ordinare lo scarto del pacchetto;
- `reject`, per scartare e inviare una risposta di reset o rifiuto;
- `sdrop`, per scartare senza loggare.

Esempio di regola che elimina pacchetti TCP diretti alla porta 80:

```snort
drop tcp any any -> any 80 (classtype:attempted-user; msg:"Port 80 connection initiated";)
```

> ⚠️ In modalità inline, una regola sbagliata non produce solo un allarme sbagliato: può bloccare traffico reale.

---

### **16. Snort come sensore in una rete reale**

Snort può funzionare come sensore di traffico in una rete, ma una singola istanza non basta sempre.

In reti fisiche complesse, con più sottoreti, DMZ, segmenti separati e percorsi diversi, servono più sensori collocati nei punti giusti. Solo così si forma davvero un Network IDS distribuito.

Le informazioni raccolte devono poi essere gestite in modo efficiente:

- normalizzazione degli alert;
- correlazione tra sensori;
- deduplicazione;
- priorità;
- conservazione storica;
- analisi forense;
- visualizzazione per operatori.

Il docente sottolinea che molte capacità di detection e molte firme esistono già, ma ciò che spesso manca in una soluzione aperta è un vero **sistema di comando e controllo**: GUI, workflow, dashboard, gestione degli alert e integrazione con processi operativi.

Nel tempo sono nati diversi frontend per Snort, per esempio ACID/BASE, Snorby e altri progetti. Alcuni sono stati abbandonati, altri sono stati ripresi o sostituiti. L'ecosistema delle interfacce grafiche è dinamico e va verificato caso per caso.

---

### **17. Argus**

**Argus** è uno strumento interessante ma non nasce propriamente come IDS.

Non analizza i pacchetti per cercare direttamente exploit o stringhe malevole. Registra invece attività di rete a livello di **connessione** o **flusso**.

Questo significa che Argus produce una vista sintetica:

- chi ha parlato con chi;
- su quale protocollo;
- con quali porte;
- per quanto tempo;
- con quanti byte o pacchetti;
- con quali caratteristiche di flusso.

I dati sono esportati in un formato binario compatto e possono essere analizzati da strumenti come `ra`, `ratop` e altri comandi dell'ecosistema Argus.

In alcune configurazioni Argus espone continuamente i dati prodotti, permettendo ad altri servizi di collegarsi e fare analisi successive.

> 📌 Argus non sostituisce Snort: offre una vista flow-based che può essere molto utile per analisi forense, capacity planning, anomaly detection e ricostruzione post-incidente.

---

### **18. Suricata**

**Suricata** è un motore moderno di network security monitoring.

Può essere usato per:

- real-time intrusion detection;
- intrusion prevention inline;
- network security monitoring;
- analisi offline di file `pcap`;
- cattura, classificazione e riesame di traffico.

Suricata è spesso considerato un'alternativa moderna a Snort, con architettura multithread e supporto a molte regole in formato compatibile con Snort.

Il punto concettuale è che il mondo NIDS non è limitato a un singolo strumento: Snort è fondamentale per capire regole e modello operativo, ma esistono motori e piattaforme diverse con obiettivi simili.

![](imgs/Pasted%20image%2020260709180103.png)

---

### **19. IDS lungo la timeline di un attacco**

Il docente chiude con lo schema tipico di un attacco.

![](imgs/Pasted%20image%2020260709180119.png)

Una sequenza semplificata comprende:

- fase di **pre-attacco**, con raccolta di informazioni, analisi dei bersagli, scansioni e prove iniziali;
- fase di **intrusione**, in cui l'attaccante entra nel sistema o sfrutta una vulnerabilità;
- eventuale deposito di codice o componente malevolo che verrà attivato in un secondo momento;
- fase di **attivazione** o esecuzione dell'attacco vero e proprio;
- fase di **copertura**, in cui l'attaccante cerca di nascondere tracce, cancellare log o ridurre la visibilità;
- fase di **danno**, quando si manifestano esfiltrazione, alterazione, indisponibilità o abuso operativo;
- fase di **post-attacco**, in cui l'organizzazione ricostruisce che cosa è successo.

IDS e IPS possono essere utili nella fase di attacco, se riconoscono un pattern noto e generano un allarme tempestivo. Tuttavia sono molto importanti anche dopo l'attacco.

Anche quando non hanno riconosciuto subito un pattern, possono aver registrato traffico, eventi, log o flussi. Quelle informazioni permettono di:

- ricostruire la sequenza dell'incidente;
- capire quali host sono realmente coinvolti;
- distinguere sistemi compromessi da sistemi solo esposti;
- individuare vulnerabilità sfruttate;
- migliorare regole, policy e configurazioni;
- progettare una remediation più precisa.

> ✅ Il valore di un IDS non si misura solo sugli attacchi bloccati in tempo reale. Si misura anche sulla qualità delle evidenze che fornisce per capire, contenere e correggere un incidente.

---

### **20. Conclusione**

Un **Network IDS** è un'architettura di osservazione e analisi del traffico di rete, basata su sensori, director, sistemi di messaggistica, policy e formati di interoperabilità.

La lezione mostra tre idee centrali:

- i sensori devono essere collocati nei punti giusti e integrati con dati host-based;
- gli standard di messaggistica servono per scambiare alert, correlare eventi e supportare risposta coordinata;
- strumenti come Snort, Argus e Suricata sono utili solo se inseriti in un processo operativo capace di gestire regole, falsi positivi, logging, correlazione e analisi post-incidente.

> 📌 In un'architettura difensiva multilivello, il firewall filtra, il NIDS osserva e correla, l'IPS può reagire, ma la qualità finale dipende dalla configurazione e dal processo operativo che li governa.
