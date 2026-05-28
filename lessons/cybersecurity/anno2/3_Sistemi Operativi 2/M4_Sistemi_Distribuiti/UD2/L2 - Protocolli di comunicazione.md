# **M4 UD2 Lezione 2 - Protocolli di comunicazione**

---

### **1. Introduzione**

Questa lezione studia come realizzare le comunicazioni in rete attraverso opportuni **protocolli di comunicazione**.

I protocolli servono a rendere trasparenti:

- le differenze tra macchine;
- le differenze tra sistemi operativi;
- le differenze di rappresentazione dei dati;
- i dettagli fisici della rete;
- le modalita' concrete di trasmissione.

L'obiettivo e' fornire ai processi un ambiente di comunicazione uniforme, anche quando le macchine coinvolte sono eterogenee.

> 📌 Un protocollo di comunicazione svolge per la rete un ruolo analogo a quello di un driver: nasconde i dettagli della periferica e offre servizi omogenei.

---

### **2. Problemi della comunicazione distribuita**

La comunicazione in un ambiente distribuito presenta diversi problemi.

#### **2.1. Comunicazione asincrona**

I processi che comunicano su macchine diverse non evolvono necessariamente in modo sincronizzato.

La comunicazione puo' richiedere tempo e il mittente non deve restare bloccato inutilmente in attesa del completamento.

I protocolli devono quindi supportare modalita' di comunicazione che permettano ai processi di continuare a evolvere.

#### **2.2. Errori e guasti**

Durante la comunicazione possono verificarsi:

- perdita di messaggi;
- corruzione dei dati;
- duplicazione di pacchetti;
- ritardi;
- guasti di nodi;
- guasti di collegamenti.

Il protocollo deve tener conto della probabilita' di errore e aumentare la probabilita' di successo della comunicazione.

#### **2.3. Eterogeneita'**

Le macchine in rete possono essere diverse per:

- hardware;
- sistema operativo;
- rappresentazione dei dati;
- protocolli disponibili;
- formato delle informazioni.

Il protocollo deve permettere l'integrazione di questi sistemi in un ambiente comune.

> ⚠️ In un sistema distribuito non basta inviare bit: bisogna garantire che il destinatario possa interpretarli correttamente.

---

### **3. Obiettivi dei protocolli**

I protocolli di comunicazione perseguono diversi obiettivi.

#### **3.1. Semplificazione delle applicazioni**

Le applicazioni non devono conoscere i dettagli fisici della rete.

Devono potersi appoggiare a uno strato di gestione che offra operazioni di comunicazione astratte.

#### **3.2. Ambiente omogeneo**

Il protocollo deve creare una visione omogenea della comunicazione tra macchine anche eterogenee.

#### **3.3. Virtualizzazione della rete**

La comunicazione in rete viene virtualizzata.

Il processo applicativo vede una comunicazione logica con un altro processo, non l'insieme di cavi, schede, router, pacchetti e conversioni necessarie per realizzarla.

#### **3.4. Efficienza**

Il protocollo deve gestire la comunicazione in modo efficiente, riducendo overhead, tempi di attesa e uso inutile della banda.

#### **3.5. Gestione di errori e guasti**

Il protocollo deve rilevare, correggere o mascherare errori quando possibile.

> ✅ L'obiettivo e' rendere trasparenti i dettagli fisici della rete e fornire un servizio di comunicazione affidabile o almeno controllato.

---

### **4. Protocolli come driver di rete**

La soluzione e' introdurre protocolli di comunicazione organizzati in strati.

Il sottosistema di comunicazione in rete viene strutturato come il driver di una periferica complessa.

Ogni strato:

- offre servizi allo strato superiore;
- usa servizi dello strato inferiore;
- comunica logicamente con lo strato equivalente sulla macchina remota.

La comunicazione reale scende lungo la pila del mittente, attraversa la rete e risale lungo la pila del destinatario.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 La stratificazione permette di isolare problemi diversi: trasmissione fisica, instradamento, controllo del flusso, formato dei dati e servizi applicativi.

---

### **5. Modello ISO/OSI**

Il modello teorico di riferimento e' il modello **ISO/OSI**.

ISO indica l'**International Standards Organization**.

OSI significa **Open Systems Interconnection**.

Il modello e' stato definito per interconnettere sistemi aperti ed eterogenei.

Il suo scopo e' chiarire come strutturare astrattamente la comunicazione in rete.

Il modello prevede una pila di sette strati:

1. fisico;
2. collegamento dati;
3. rete;
4. trasporto;
5. sessione;
6. presentazione;
7. applicazione.

---

### **6. Strato fisico**

Lo **strato fisico** gestisce la trasmissione dei bit sul mezzo fisico.

Si occupa degli aspetti:

- meccanici;
- elettrici;
- fisici;
- di connessione.

Definisce, per esempio:

- come devono essere realizzate le interconnessioni;
- quali segnali rappresentano i bit;
- come i bit vengono trasmessi sul canale.

> 📌 Lo strato fisico non interpreta messaggi: trasmette bit.

---

### **7. Strato di collegamento dati**

Lo **strato di collegamento dati** gestisce l'invio e la ricezione della singola porzione di messaggio di lunghezza controllata.

Questa porzione puo' essere vista come un pacchetto o frame.

Lo strato si occupa di:

- invio del pacchetto sul collegamento;
- ricezione del pacchetto;
- rilevazione degli errori;
- eventuale correzione o ritrasmissione;
- controllo della correttezza locale della trasmissione.

---

### **8. Strato di rete**

Lo **strato di rete** gestisce la connessione logica e l'instradamento dei pacchetti nella rete.

Si occupa di:

- determinare l'indirizzo di destinazione;
- scegliere il percorso;
- inoltrare pacchetti;
- decodificare gli indirizzi dei pacchetti in ingresso;
- stabilire se un pacchetto e' destinato alla macchina corrente.

> 📌 Lo strato di rete decide dove devono andare i pacchetti.

---

### **9. Strato di trasporto**

Lo **strato di trasporto** crea un servizio di comunicazione tra processi indipendente dai dettagli della rete sottostante.

Si occupa di:

- prendere messaggi di lunghezza variabile;
- dividerli in pacchetti;
- inviare i pacchetti;
- mantenere l'ordine;
- controllare il flusso;
- gestire errori a livello di messaggio;
- ricostruire il messaggio al destinatario.

Al di sopra dello strato di trasporto, i processi possono vedere uno scambio di messaggi piu' astratto e indipendente dalla struttura fisica della rete.

---

### **10. Strato di sessione**

Lo **strato di sessione** gestisce la comunicazione a livello di sessione tra processi.

Si occupa di:

- apertura della sessione;
- mantenimento della sessione;
- chiusura della sessione;
- ordinamento dei messaggi della sessione;
- gestione degli errori relativi alla sessione.

La sessione rappresenta il contesto logico entro cui due processi cooperano.

---

### **11. Strato di presentazione**

Lo **strato di presentazione** risolve le differenze di formato nella rappresentazione delle informazioni.

Macchine diverse possono rappresentare in modo diverso:

- numeri;
- caratteri;
- stringhe;
- strutture dati;
- formati binari.

Lo strato di presentazione effettua conversioni per rendere comprensibili i dati al destinatario.

Gestisce inoltre modalita' di comunicazione come:

- **semi-duplex**, una direzione alla volta;
- **full-duplex**, entrambe le direzioni contemporaneamente.

> 💡 Sopra lo strato di presentazione, lo scambio dei messaggi diventa indipendente dalla sintassi interna delle singole macchine.

---

### **12. Strato di applicazione**

Lo **strato di applicazione** fornisce servizi di rete direttamente utilizzabili dalle applicazioni.

Esempi:

- trasferimento di file;
- connessione remota;
- posta elettronica;
- basi di dati distribuite;
- servizi web;
- risoluzione dei nomi.

Questo strato offre protocolli standard per applicazioni distribuite comuni.

---

### **13. Visione complessiva della pila ISO/OSI**

La pila ISO/OSI costruisce livelli di astrazione crescenti.

Gli strati bassi, dal fisico al trasporto, creano un ambiente di rete omogeneo e indipendente dalle strutture fisiche.

Gli strati superiori, dalla sessione all'applicazione, costruiscono un ambiente piu' astratto per la cooperazione tra applicazioni.

```text
+----------------------+
| 7. Applicazione      |
| 6. Presentazione     |
| 5. Sessione          |
| 4. Trasporto         |
| 3. Rete              |
| 2. Collegamento dati |
| 1. Fisico            |
+----------------------+
```

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Incapsulamento dei messaggi**

Nel modello a strati, il messaggio viene composto progressivamente.

I dati applicativi vengono arricchiti con informazioni di servizio man mano che scendono lungo la pila.

Ogni strato aggiunge informazioni utili alla propria funzione.

Per esempio:

- lo strato applicativo aggiunge informazioni applicative;
- lo strato di presentazione aggiunge informazioni di formato;
- lo strato di sessione aggiunge informazioni di sessione;
- lo strato di trasporto aggiunge informazioni per ordinamento e controllo del flusso;
- lo strato di rete aggiunge informazioni di indirizzamento e instradamento;
- lo strato di collegamento dati aggiunge header e chiusure per controllare il singolo pacchetto.

Al destinatario avviene il processo inverso: ogni strato rimuove e interpreta le informazioni corrispondenti.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 L'incapsulamento permette a ogni strato di aggiungere il proprio controllo senza modificare il contenuto logico gestito dagli strati superiori.

---

### **15. Modelli reali**

Il modello ISO/OSI e' teorico, chiaro e gerarchico.

I modelli reali usati nelle reti moderne sono piu' semplici ed efficienti.

Devono essere:

- rapidi nell'incapsulamento;
- rapidi nell'invio;
- piu' semplici da implementare;
- piu' adatti alle reti reali.

Il prezzo e' una minore separazione astratta tra gli strati rispetto al modello ISO/OSI.

I modelli reali principali sono basati su:

- IP;
- TCP;
- UDP;
- protocolli applicativi.

---

### **16. Protocollo IP**

Il **protocollo Internet**, o **IP**, corrisponde sostanzialmente allo strato di rete.

Si occupa di:

- gestire pacchetti;
- indirizzare pacchetti;
- instradare pacchetti nella rete.

IP fornisce un servizio di base per portare pacchetti verso una destinazione.

Non garantisce, da solo, affidabilita' completa.

---

### **17. TCP e UDP**

Sopra IP si colloca lo strato di trasporto.

I protocolli principali sono:

- TCP;
- UDP.

#### **17.1. UDP**

**UDP**, User Datagram Protocol, gestisce comunicazioni senza connessione.

E' per definizione non affidabile.

Questo significa che non garantisce:

- consegna;
- ordine;
- assenza di duplicazioni;
- correzione automatica degli errori.

Le applicazioni che usano UDP devono sapere che la comunicazione non e' garantita e, se necessario, devono gestire autonomamente gli errori.

#### **17.2. TCP**

**TCP**, Transmission Control Protocol, e' orientato alla connessione.

E' affidabile perche' tenta di recuperare gli errori della comunicazione, quando possibile.

Gestisce:

- apertura della connessione;
- ordinamento dei dati;
- controllo del flusso;
- rilevazione delle perdite;
- ritrasmissione;
- chiusura della connessione.

> ✅ TCP privilegia affidabilita' e ordine; UDP privilegia semplicita' e rapidita'.

---

### **18. Relazione tra ISO/OSI e TCP/IP**

Nel modello reale:

- IP corrisponde sostanzialmente allo strato di rete;
- TCP e UDP corrispondono allo strato di trasporto;
- i livelli fisico e collegamento dati non sono definiti in modo unico dal modello TCP/IP;
- sessione e presentazione non sono strati standard separati;
- molte funzioni superiori vengono inglobate nei protocolli applicativi.

| ISO/OSI | Modello reale |
|---|---|
| Applicazione | Protocolli applicativi |
| Presentazione | Inclusa nell'applicazione o nelle librerie |
| Sessione | Inclusa nell'applicazione o in TCP |
| Trasporto | TCP / UDP |
| Rete | IP |
| Collegamento dati | Ethernet, Wi-Fi, tecnologie locali |
| Fisico | Mezzi e dispositivi fisici |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **19. Protocolli applicativi**

A livello applicativo esistono protocolli specializzati, costruiti sopra TCP/IP o UDP/IP.

Esempi:

| Protocollo | Funzione |
|---|---|
| **Telnet** | Connessione remota non sicura |
| **SSH** | Connessione remota sicura |
| **FTP** | Trasferimento file |
| **SFTP** | Trasferimento file sicuro |
| **HTTP** | Accesso a siti web |
| **HTTPS** | Accesso sicuro a siti web |
| **SMTP / POP / IMAP** | Servizi di posta elettronica |
| **DNS** | Risoluzione dei nomi |

Le versioni sicure, come SSH, SFTP e HTTPS, aggiungono meccanismi di protezione alla comunicazione.

---

### **20. Sintesi**

| Aspetto | Descrizione |
|---|---|
| **Problemi** | Asincronia, errori, guasti, eterogeneita' |
| **Obiettivi** | Astrazione, omogeneita', efficienza, gestione errori |
| **Protocolli** | Driver stratificati della comunicazione di rete |
| **ISO/OSI** | Modello teorico a sette strati |
| **Strati bassi** | Rendono omogenea la rete fisica |
| **Strati alti** | Forniscono servizi applicativi standard |
| **Incapsulamento** | Ogni strato aggiunge informazioni di controllo |
| **IP** | Instradamento dei pacchetti |
| **UDP** | Trasporto semplice, senza connessione, non affidabile |
| **TCP** | Trasporto affidabile, orientato alla connessione |
| **Applicativi** | SSH, FTP, HTTP, posta, DNS |

---

### **21. Conclusione**

I protocolli di comunicazione permettono di costruire un ambiente di rete astratto, omogeneo e utilizzabile da applicazioni distribuite.

Il modello ISO/OSI chiarisce teoricamente la separazione degli strati e delle responsabilita'.

I modelli reali, basati su IP, TCP, UDP e protocolli applicativi, sacrificano parte della purezza del modello teorico per ottenere maggiore efficienza e semplicita' operativa.
