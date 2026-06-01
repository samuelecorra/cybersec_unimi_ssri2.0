# **M2 UD1 Lezione 1 - Struttura e funzioni dei sottosistemi di ingresso/uscita**

---

### **1. Introduzione**

Il sottosistema di **ingresso/uscita** (**I/O**) consente al sistema operativo di gestire la comunicazione tra unità centrale, memoria e periferiche.

L'obiettivo è creare una **virtualizzazione delle periferiche**, fornendo ai processi e al kernel un'interfaccia omogenea che nasconda le differenze fisiche, funzionali e operative dei dispositivi reali.

In questa lezione vengono analizzati:

- le diverse **tipologie di periferiche**;
- le **caratteristiche** di cui il sistema operativo deve tenere conto;
- l'organizzazione del **software di gestione delle periferiche**.

> 📌 Il sottosistema di I/O realizza astrazione e virtualizzazione delle periferiche: i programmi non devono conoscere i dettagli interni dei dispositivi, dei loro comandi o delle loro modalità di collegamento.

---

### **2. Tipologie di periferiche**

Esistono diverse tipologie di periferiche usate nei sistemi di elaborazione per immettere informazioni, produrre risultati, memorizzare dati in modo permanente e comunicare con altri sistemi.

#### **2.1. Dispositivi di ingresso**

I dispositivi di ingresso consentono l'acquisizione di informazioni dall'esterno verso il sistema di elaborazione.

Esempi:

- tastiera;
- mouse;
- altri dispositivi di puntamento.

#### **2.2. Dispositivi di uscita**

I dispositivi di uscita consentono al sistema di produrre informazioni verso l'utente o verso l'ambiente esterno.

Esempi:

- video o monitor;
- stampanti.

#### **2.3. Memorie di massa**

Le memorie di massa permettono la conservazione permanente delle informazioni, anche dopo la terminazione dell'alimentazione elettrica.

Esempi:

- dischi magnetici;
- dischi ottici;
- nastri magnetici.

#### **2.4. Dispositivi di comunicazione e controllo**

Altri dispositivi servono per comunicare con sistemi esterni o per gestire l'evoluzione temporale delle attività.

Esempi:

- interfacce di rete;
- orologi;
- temporizzatori;
- periferiche specializzate per applicazioni specifiche.

---

### **3. Caratteristiche delle periferiche**

Le periferiche hanno caratteristiche molto diverse. Il sistema operativo deve tenerne conto per gestirle in modo efficiente ed efficace.

#### **3.1. Direzione delle operazioni**

Dal punto di vista della direzione del trasferimento, si distinguono:

- **periferiche a sola lettura**, che permettono solo l'acquisizione di informazioni dalla periferica verso il calcolatore, come tastiere o CD-ROM;
- **periferiche a sola scrittura**, che permettono solo il trasferimento dal calcolatore alla periferica, come stampanti o controller video;
- **periferiche di lettura e scrittura**, che supportano entrambe le direzioni, come dischi e nastri.

#### **3.2. Condivisione**

Alcune periferiche possono essere usate solo in modo dedicato, cioè in **mutua esclusione** tra processi.

Esempi tipici:

- stampanti;
- nastri magnetici.

Altre periferiche possono invece essere usate in modo condiviso, consentendo l'accesso coordinato da parte di più processi.

> ⚠️ La condivisibilità di una periferica influenza direttamente le politiche di sincronizzazione e di accodamento delle richieste.

#### **3.3. Metodo di accesso**

Le periferiche differiscono anche per il modo in cui permettono di accedere alle informazioni.

Nell'**accesso sequenziale**, le informazioni vengono lette o scritte una dopo l'altra, secondo un ordine obbligato. Esempi: nastri, modem e dispositivi simili.

Nell'**accesso diretto**, invece, è possibile specificare direttamente l'informazione desiderata e raggiungerla senza scandire tutto il supporto. L'esempio principale è il disco.

#### **3.4. Modo di trasferimento dei dati**

Alcune periferiche trasferiscono dati **a carattere**, cioè un carattere o byte alla volta. Questo è tipico di terminali, tastiere e stampanti.

Altre periferiche trasferiscono dati **a blocchi**, cioè gruppi di byte in una singola operazione. Questo è tipico di dischi e reti.

> 💡 Il trasferimento a blocchi riduce l'overhead per grandi quantità di dati, mentre il trasferimento a carattere è più adatto a dispositivi interattivi o sequenziali.

#### **3.5. Schedulazione del trasferimento**

Le operazioni di I/O possono essere:

- **sincrone** o **bloccanti**, quando il processo deve attendere il completamento dell'operazione prima di proseguire;
- **asincrone** o **non bloccanti**, quando il processo può continuare l'esecuzione mentre il trasferimento avviene in background.

Il caso sincrono è tipico di alcune operazioni su nastro. Il caso asincrono è utile quando si vuole sovrapporre computazione e trasferimento dei dati.

#### **3.6. Parametri temporali**

Per valutare il comportamento di una periferica occorre considerare diversi aspetti temporali:

- **latenza**, cioè il tempo totale richiesto dall'inizio del trasferimento al suo completamento;
- **tempo di ricerca**, cioè il tempo necessario per localizzare l'informazione nella periferica;
- **tempo di trasferimento**, cioè il tempo necessario a trasferire i dati una volta localizzati;
- **ritardo tra operazioni consecutive**, cioè il tempo eventualmente necessario prima che la periferica sia pronta per una nuova richiesta.

> 📌 Le prestazioni di una periferica non dipendono solo dalla velocità di trasferimento, ma anche da latenza, ricerca e ritardi tra operazioni.

---

### **4. Obiettivi del software di gestione**

Il software di gestione delle periferiche deve fornire un'interfaccia unica e omogenea, rendendo trasparenti le differenze tra dispositivi.

Gli obiettivi principali sono:

- semplificare lo sviluppo del software applicativo;
- evitare che i programmi conoscano i dettagli interni delle periferiche;
- standardizzare le operazioni di gestione;
- organizzare il sottosistema di I/O in livelli chiari e modulari;
- fornire astrazione e virtualizzazione delle periferiche.

Per raggiungere questi obiettivi, il software di I/O viene tipicamente organizzato in strati.

---

### **5. Organizzazione a strati del software di I/O**

La gestione delle periferiche è organizzata in livelli, ciascuno con un compito specifico:

1. **gestione del canale di comunicazione**;
2. **device dependent driver**;
3. **device independent driver**.

Questa organizzazione permette di separare il collegamento fisico, le specificità del dispositivo e l'interfaccia astratta esposta al sistema operativo.

---

### **6. Gestione del canale di comunicazione**

La gestione del canale di comunicazione ha lo scopo di rendere trasparente il modo in cui la periferica è connessa all'unità centrale.

Questo livello governa il trasferimento di informazioni e comandi tra CPU e periferica, indipendentemente dalla struttura concreta del collegamento.

#### **6.1. Funzione principale**

Il canale di comunicazione garantisce che:

- i comandi inviati dall'unità centrale raggiungano la periferica;
- i dati e le risposte della periferica arrivino all'unità centrale;
- il software superiore non debba conoscere i dettagli della connessione fisica.

#### **6.2. Modalità di gestione**

La trasparenza può riguardare diverse modalità di collegamento e trasferimento:

- periferiche **mappate in memoria**;
- gestione tramite **attesa attiva** o polling;
- gestione tramite **interruzioni**;
- uso del **DMA** per trasferire blocchi di dati senza intervento continuo della CPU.

> 💡 Questo strato nasconde il "come" della connessione: i livelli superiori devono poter inviare comandi e ricevere dati senza dipendere dal meccanismo fisico usato.

---

### **7. Device Dependent Driver**

Il **device dependent driver** è lo strato dipendente dal dispositivo.

Il suo obiettivo è rendere trasparenti le differenze tra dispositivi della stessa tipologia, per esempio tra modelli diversi o produttori diversi.

#### **7.1. Funzioni principali**

Questo livello:

- nasconde le differenze tra modelli della stessa famiglia di periferiche;
- uniforma il linguaggio di comando per una certa tipologia di dispositivo;
- gestisce le differenze nei protocolli di controllo;
- gestisce il trattamento specifico degli errori;
- presenta in modo uniforme le risposte prodotte dalla periferica.

Per esempio, il sistema operativo dovrebbe poter trattare le stampanti di una certa tipologia nello stesso modo, indipendentemente dal produttore o dal modello specifico.

> 📌 Il device dependent driver omogeneizza i dispositivi dello stesso tipo: rende uguali, verso l'alto, periferiche che in realtà hanno comandi e comportamenti specifici diversi.

---

### **8. Device Independent Driver**

Il **device independent driver** innalza ulteriormente il livello di astrazione.

Il suo scopo è mostrare tutte le tipologie di periferiche tramite un'interfaccia omogenea, indipendente dalla categoria del dispositivo.

#### **8.1. Astrazione comune**

Questo livello rende trasparenti:

- le differenze tra tipi di dispositivi;
- il formato dei comandi;
- il modo di accesso alle risorse informative;
- il trattamento generale degli errori;
- la gestione comune dei dispositivi.

#### **8.2. Funzioni generali**

Il device independent driver fornisce funzioni comuni come:

- **bufferizzazione**, per rendere trasparente il fatto che i dati siano trasferiti a carattere o a blocchi;
- **caching**, per evitare di richiedere più volte alla periferica informazioni già recuperate;
- **spooling**, per accodare richieste di accesso alla periferica ed eseguirle in modo asincrono rispetto ai processi richiedenti;
- gestione uniforme degli errori;
- interfaccia standard verso kernel e programmi applicativi.

> ✅ Il device independent driver fornisce la vera visione astratta della periferica: verso l'alto, dispositivi diversi diventano risorse gestibili con operazioni omogenee.

---

### **9. Struttura complessiva del sottosistema**

Il software di gestione delle periferiche è tipicamente organizzato secondo una struttura stratificata.

A livello hardware si trovano le singole periferiche. Nell'unità centrale sono presenti i controller, connessi alle periferiche tramite opportuni canali di comunicazione. Sopra questi componenti hardware, il sistema operativo fornisce driver specifici e funzioni generali.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La stratificazione permette al kernel di vedere un'interfaccia omogenea, mentre i livelli inferiori si occupano delle differenze reali tra dispositivi, controller e canali di comunicazione.

---

### **10. Sintesi finale**

> ✅ Il sottosistema di ingresso/uscita serve a virtualizzare le periferiche, nascondendo al software applicativo la complessità dei dispositivi reali.

Le periferiche si distinguono per:

- direzione delle operazioni;
- possibilità di condivisione;
- metodo di accesso;
- modalità di trasferimento;
- schedulazione sincrona o asincrona;
- parametri temporali come latenza, ricerca, trasferimento e ritardi tra operazioni.

Il software di gestione è organizzato in strati:

- **gestione del canale di comunicazione**, che nasconde il collegamento fisico;
- **device dependent driver**, che nasconde le differenze tra dispositivi dello stesso tipo;
- **device independent driver**, che fornisce una visione astratta e omogenea indipendente dalla tipologia di periferica.

Questa organizzazione rende più semplice la gestione del sistema operativo e l'interazione con programmi applicativi e utenti.
