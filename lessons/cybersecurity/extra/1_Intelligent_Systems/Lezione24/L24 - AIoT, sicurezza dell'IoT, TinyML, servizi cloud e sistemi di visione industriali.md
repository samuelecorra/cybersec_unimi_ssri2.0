## **Lezione 24: AIoT, sicurezza dell’IoT, TinyML, servizi cloud e sistemi di visione industriali**

L’ultima lezione del corso **IS4** riporta tutto ciò che si è visto dentro i **sistemi reali**. È divisa in due parti.

- **Parte A: AIoT.** L’**Artificial Intelligence of Things** unisce l’infrastruttura IoT ai modelli di AI. La lezione ne presenta:
  - l’hardware e il software abilitanti;
  - la **sicurezza** dell’IoT, con gli aggiornamenti **OTA**, il **Cyber Resilience Act** europeo e il framework del **NIST**;
  - le piattaforme di **AWS**, **MATLAB**, **Azure** e **Google Coral**;
  - le **schede** per l’AI embedded.
- **Parte B: software e servizi per il ML.** Comprende:
  - il **TinyML**;
  - i servizi cloud (**MLaaS**, IaaS, PaaS, SaaS);
  - un’ampia rassegna di **sistemi di visione intelligenti** per robotica, fabbrica, sorveglianza, ambiente, logistica e trasporti, incluso il caso **Pirelli Cyber Tyre**;
  - i principi di **Industria 4.0 e 5.0**;
  - i **sistemi di visione industriali**;
  - l’annuncio della simulazione d’esame finale.

Fonte: [PDF della Lezione 24](Lesson_24_Machine_Learning_Software_Cloud_Services.pdf), 121 pagine, senza supplementi. Le note didattiche aggiunte e i frammenti di codice ricostruiti sono segnalati; le date normative sono state verificate rispetto alle slide e le differenze sono indicate con ⚠️. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–5)_

Titolo originale della parte A: *Artificial Intelligence of Things (AIoT), IoT security, HW/SW environments for IoT/AIoT*. L’indice (p. 2) elenca:

1. **architettura AIoT** e scelte di **rilascio**;
2. **sicurezza dell’IoT** e aggiornamenti **OTA**;
3. **gateway edge**, **runtime embedded** e **TinyML**;
4. **servizi cloud di ML**;
5. **sistemi di visione intelligenti** per l’industria e la logistica;
6. concetti di progetto per **Industria 4.0 e 5.0**.

Le pp. 3–5 sono immagini introduttive senza testo.

<!-- SOURCE_VISUAL id="IS24-V01" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="3" type="immagine" description="Immagine introduttiva della lezione, prima pagina senza testo" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V02" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="4" type="immagine" description="Immagine introduttiva della lezione, seconda pagina senza testo" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V03" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="5" type="immagine" description="Immagine introduttiva della lezione, terza pagina senza testo" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

**Parte A: Artificial Intelligence of Things**

### **1. AIoT: una nuova frontiera per le applicazioni**

_(slide pp. 6–14)_

#### **1.1. Che cos’è e che cosa lo rende possibile**

_(slide pp. 6–10)_

> 📌 **Artificial Intelligence of Things (AIoT)** (p. 7).
> - L’AIoT **combina l’infrastruttura IoT con i modelli di AI**. I dispositivi IoT **raccolgono dati** dai processi fisici.
> - L’AI **trasforma questi dati** in **predizioni**, **allarmi di anomalia**, **azioni ottimizzate** e **supporto alle decisioni**.
> - La tecnologia AIoT crea «**macchine che imparano dai dati**».
> - I **dati in tempo reale** sono il valore chiave di tutti i casi d’uso AIoT.
> - Nei sistemi moderni **parte dell’intelligenza** può girare **nel cloud**, **all’edge** o **direttamente sul dispositivo**, in base ai vincoli di **latenza**, **banda**, **privacy**, **energia** e **affidabilità**.

> 💡 **Dove mettere l’intelligenza (nota didattica).** I cinque vincoli della slide spingono in direzioni diverse (Lezione 05, §5):
>
> | Vincolo | Spinge verso | Esempio |
> |---|---|---|
> | **latenza** bassa | dispositivo o edge | fermare un nastro se compare un pezzo difettoso |
> | **banda** limitata | dispositivo o edge, inviando solo eventi | telecamere in un campo agricolo con copertura 4G |
> | **privacy** | dispositivo | parola di attivazione di un altoparlante (§1.2) |
> | **energia** | dispositivo con modello minuscolo, oppure cloud se il calcolo è raro | sensore a batteria |
> | **affidabilità** | edge, che funziona anche senza rete | controllo di un impianto |
> | modelli **grandi** e **riaddestramento** | cloud | aggiornamento periodico del modello di tutta la flotta |
>
> **Il peso della banda in numeri.** Una telecamera Full HD a 30 fotogrammi al secondo compressa in H.264 produce circa 4 Mbit/s. In un giorno:
> $$4\ \text{Mbit/s} \times 86\,400\ \text{s} = 345\,600\ \text{Mbit} \approx 43\ \text{GB}$$
> per **una sola** telecamera. Cento telecamere inviate tutte al cloud superano i 4 TB al giorno; un modello all’edge che invia solo gli eventi riduce il traffico di ordini di grandezza.

**Hardware: che cosa abilita l’AI?** (p. 8). L’AIoT è resa possibile da dispositivi e infrastrutture capaci di **percepire**, **calcolare**, **comunicare** e **agire**.

| Componente | Esempi (slide) |
|---|---|
| **sensori** | telecamere, IMU, microfoni, temperatura, pressione, vibrazione, sensori negli pneumatici |
| **microcontrollori e CPU embedded** | ESP32, STM32, ARM Cortex-M, dispositivi di classe Raspberry Pi |
| **acceleratori di AI** | GPU, TPU, NPU, VPU, Edge TPU, Hailo, Jetson, FPGA (Lezione 23, §6) |
| **connettività** | Wi-Fi, Bluetooth, Zigbee, LoRaWAN, 4G/5G, Ethernet, fieldbus |
| **gateway edge** | PC industriali o macchine locali che eseguono runtime edge |

**Software: che cosa abilita l’AI?** (p. 9). Il software rende possibili **interoperabilità**, **rilascio dei modelli**, **monitoraggio** e **controllo**.

| Componente | Esempi (slide) |
|---|---|
| **runtime embedded** | FreeRTOS, Eclipse ThreadX, Linux embedded |
| **runtime di AI** | TensorFlow Lite, ONNX Runtime, SDK dei produttori |
| **protocolli di comunicazione** | MQTT, HTTP/REST, OPC UA, Modbus, CAN bus |
| **servizi cloud/edge** | AWS IoT Greengrass, Azure IoT, piattaforme «in stile» Google Cloud IoT/Vertex AI |
| **API e strumenti MLOps** | endpoint dei modelli, aggiornamenti OTA, logging, monitoraggio, pipeline di riaddestramento |

> ⚠️ **Nomi che cambiano (nota didattica).** Il servizio Google Cloud IoT Core è stato **ritirato nel 2023**: per questo la slide parla di piattaforme «in stile» Google. Anche TensorFlow Lite è stato rinominato **LiteRT** nel 2024. La slide p. 63 dà la regola giusta: concentrarsi sui **concetti di progetto**, non sui nomi dei prodotti.

> 📌 **Che cosa migliora l’AIoT** (p. 10). Progettando un’applicazione AI + IoT bisogna puntare a un sistema:
> - **più robusto**: **autocalibrazione**, **autodiagnosi**, **migliore riduzione del rumore** grazie alle capacità adattive;
> - **più sicuro**: le informazioni vengono fornite **con la loro accuratezza** e con una **valutazione della qualità** dei dati. Esempio: «**temperatura = 152 gradi**»: è un **errore del sensore** o il motore **si sta fondendo**?
> - **più scalabile**: supportando protocolli e formati diversi, il sistema dialoga con molte applicazioni, dispositivi e servizi.
>
> In breve: **più prezioso**. Gli **stessi componenti**, ma è il **software** a rendere il dispositivo più attraente.

> 💡 **Rispondere alla domanda dei 152 gradi (nota didattica).** Un sistema AIoT non si fida di una misura isolata, ma la confronta con più controlli:
> 1. **plausibilità fisica**: il valore rientra nel campo di misura del sensore e nel campo possibile del processo?
> 2. **velocità di variazione**: se un secondo prima il motore era a 70 °C, un salto a 152 °C in un secondo è fisicamente impossibile, ed è quasi certamente un guasto del sensore;
> 3. **ridondanza**: un secondo sensore, o un **sensore virtuale** che stima la temperatura da corrente e velocità (Lezione 03), conferma o smentisce;
> 4. **statistica**: quanto dista il valore dalla distribuzione recente, per esempio in deviazioni standard (Lezione 10, §3.3).
>
> La risposta giusta non è né «ignora» né «allarme», ma «**allarme con la sua qualità**»: il sensore è probabilmente guasto, e va comunque controllato.

#### **1.2. Esempi di AIoT**

_(slide pp. 11–14)_

**Altoparlanti intelligenti** (pp. 11–12).

- Un altoparlante con AI elabora la **parola di attivazione** con un modello NLP **locale**, invece di inviare al cloud tutto l’audio registrato.
- Tipo: **sorgente di dati dinamica** (Lezione 05, §6.2).
- Approccio **ibrido**:
  - **elaborazione locale** per l’**attivazione**;
  - **elaborazione remota** per l’**NLP**, oggi in parte eseguito anch’esso in locale.
- Lo strato **fog** è **quasi assente**.
- **Senza connessione** le capacità del dispositivo sono **fortemente limitate**.

**Illuminazione intelligente e connessa** (p. 13). La slide confronta l’IoT **tradizionale** e l’AIoT:

- lampadine **Bluetooth** senza bridge;
- lampadine collegate a un **bridge**;
- **dispositivi edge**;
- una **sorgente di comandi** che passa dal **cloud** (NLP) e arriva direttamente al bridge.

<!-- SOURCE_VISUAL id="IS24-V04" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="13" type="schema" description="Illuminazione connessa: IoT tradizionale con Bluetooth e bridge contro AIoT con comandi NLP dal cloud" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Negozi e cartelloni pubblicitari intelligenti** (p. 14). L’AIoT usa di solito **visione artificiale** e **sensori** per raccogliere informazioni su clienti, veicoli e altro. Esempio: la campagna **#LookUp** di **British Airways** a **Piccadilly Circus**. Una lampadina disegnata sul cartellone resta **spenta** finché un passante non **ci cammina sotto**. Allora, grazie ai **sensori di movimento**, si **accende**: a chi guarda il cartellone sembra che la persona abbia appena avuto un’idea geniale, il «momento della lampadina».

<!-- SOURCE_VISUAL id="IS24-V05" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="14" type="immagine" description="Cartellone interattivo British Airways a Piccadilly Circus con la lampadina che si accende" reason="Esempio visibile solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Sicurezza dell’IoT**

_(slide pp. 15–21)_

**Il problema** (p. 16). Il **numero** dei dispositivi e la loro **rilevanza** pongono **enormi problemi di sicurezza**. Basta pensare a:

- **pacemaker**;
- **sistemi semaforici**;
- **sistemi di controllo e guida delle auto**.

**Interazioni complesse** → **più problemi di sicurezza**.

**Sei principi di cybersicurezza dell’IoT nello stack** (p. 17). La figura mostra i sei principi distribuiti sui livelli dello stack, con al centro gli **aggiornamenti OTA**. La slide spiega le sigle dei sistemi aziendali collegati:

- **PLM** (*Product Lifecycle Management*);
- **ERP** (*Enterprise Resource Planning*);
- **SCM** (*Supply Chain Management*);
- **CRM** (*Customer Relationship Management*).

<!-- SOURCE_VISUAL id="IS24-V06" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="17" type="schema" description="Sei principi di cybersicurezza dell’IoT nello stack con aggiornamenti OTA e sistemi PLM, ERP, SCM, CRM" reason="I sei principi sono presenti solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **OTA** (*Over-the-Air*, p. 18). Un **metodo standardizzato** per inviare **aggiornamenti di software e firmware** ai dispositivi IoT **da remoto**, **senza accesso fisico**.
> - È diventato una **pietra angolare** degli ecosistemi IoT moderni: permette miglioramenti e **patch di sicurezza** per auto connesse, sensori intelligenti, macchinari industriali.
> - Per garantire **integrità** e **riservatezza** durante la trasmissione si usano protocolli di comunicazione sicuri:
>   - **HTTPS**;
>   - **MQTT**, protocollo di messaggistica **leggero** progettato per comunicare in modo efficiente in ambienti con **risorse limitate**;
>   - **TLS**, protocollo crittografico che **cifra i dati**, **autentica gli estremi** della comunicazione e ne garantisce l’**integrità**.

> ⚠️ **MQTT da solo non è sicuro (nota didattica).** HTTPS è HTTP su TLS; MQTT invece è un protocollo di messaggistica che **diventa sicuro solo se trasportato su TLS** (tipicamente sulla porta 8883) e con autenticazione dei dispositivi. Un aggiornamento OTA robusto richiede inoltre:
> - la **firma digitale del firmware**, verificata dal dispositivo prima di installarlo: TLS protegge il canale, ma non dice se il firmware viene dal produttore;
> - la protezione dal **rollback** a versioni vecchie e vulnerabili;
> - un meccanismo di **ripristino** (per esempio due partizioni A/B) se l’aggiornamento fallisce a metà.

> 💡 **Un sensore che pubblica su MQTT con TLS (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> import json
> import paho.mqtt.client as mqtt
>
> client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION2, client_id="sensore-42")
> client.tls_set(ca_certs="ca.pem", certfile="device.crt", keyfile="device.key")
> client.connect("broker.example.com", 8883)      # MQTT su TLS
> client.loop_start()
> info = client.publish("fabbrica/linea1/temperatura", json.dumps({"t": 71.3}), qos=1)
> info.wait_for_publish()
> client.loop_stop()
> client.disconnect()
> ```
> Il dispositivo si autentica con un **proprio certificato** (`device.crt`), non con una password condivisa: è la pratica dei servizi IoT cloud, e risponde al divieto di credenziali cablate nel codice del Cyber Resilience Act.

> 📌 **Cyber Resilience Act** (UE, pp. 19–20). **Sicurezza obbligatoria**:
> - regolamento UE che impone la **cybersicurezza** a **tutti i prodotti digitali** (hardware e software) venduti sul mercato europeo;
> - **marcatura CE** per certificare la conformità;
> - **supporto per tutto il ciclo di vita**: aggiornamenti e segnalazione delle vulnerabilità.
>
> **«Security by design» obbligatoria** per i produttori:
>
> | Requisito | Esempio (slide) |
> |---|---|
> | **valutazione e mitigazione del rischio** | analisi del rischio di cybersicurezza **fin dal primo passo del progetto** |
> | **impostazioni predefinite sicure** | i prodotti devono uscire di fabbrica con configurazioni sicure, per esempio una **password forte** come predefinita |
> | **protezione dalle minacce comuni** | **TLS** per la trasmissione, difesa da exploit come gli **attacchi di injection** |
> | **superficie d’attacco ridotta** | limitare funzioni non necessarie, **porte aperte** e interfacce esposte |
> | **pratiche di sviluppo sicuro** | per esempio **evitare credenziali cablate** nel codice |
> | **sicurezza nel ciclo di vita** | aggiornamenti di software e firmware per correggere le vulnerabilità |
> | **trasparenza e documentazione** | — |

> ⚠️ **Le date del CRA (nota didattica).** La slide scrive «2025». Il Cyber Resilience Act è il **Regolamento (UE) 2024/2847**:
> - è **entrato in vigore** il 10 dicembre 2024;
> - gli **obblighi di segnalazione** delle vulnerabilità sfruttate e degli incidenti si applicano dall’**11 settembre 2026**;
> - la **generalità degli obblighi** si applica dall’**11 dicembre 2027**.
>
> Come per l’AI Act (Lezione 02, §1), conviene ricordare la logica a scadenze successive più che una sola data.

> 📌 **NIST IoT Cybersecurity Framework** (USA, p. 21). Un framework **volontario** statunitense per proteggere dispositivi ed ecosistemi IoT, organizzato in cinque funzioni. Esempio di una **fabbrica intelligente** con sensori IoT:
>
> | Funzione | Nella fabbrica |
> |---|---|
> | **Identify** | **mappare** tutti i sensori e i loro dati in uscita |
> | **Protect** | **cifrare** le comunicazioni dei sensori |
> | **Detect** | **monitorare** attività anomale dei sensori |
> | **Respond** | **isolare** un sensore compromesso |
> | **Recover** | **aggiornare il firmware** e rafforzare la sicurezza |

> ⚠️ **Quale documento NIST (nota didattica).** Le cinque funzioni sono quelle del **NIST Cybersecurity Framework** generale. La versione **CSF 2.0** (febbraio 2024) ne aggiunge una sesta, **Govern**, che riguarda strategia, ruoli e responsabilità. Le raccomandazioni specifiche per i dispositivi IoT sono nella serie **NIST IR 8259** (capacità di base richieste ai produttori). Il messaggio della slide resta valido: in Europa la sicurezza dei prodotti è **obbligatoria** (CRA), negli USA il framework è **volontario**.

---

### **3. Piattaforme per l’IoT e l’AIoT**

_(slide pp. 22–37)_

#### **3.1. Amazon AWS IoT e FreeRTOS**

_(slide pp. 22–28)_

**AWS IoT** (pp. 22–24) è una piattaforma di servizi IoT per soluzioni **industriali**, **consumer** e **commerciali**:

- **ampia e profonda**, **dall’edge al cloud**;
- il software per i dispositivi (**FreeRTOS**) fornisce raccolta e analisi dei dati **in locale** (**AWS IoT Greengrass**);
- **gestione dei dati** e **analisi avanzate**.

<!-- SOURCE_VISUAL id="IS24-V07" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="24" type="schema" description="L’idea di base di AWS IoT dall’edge al cloud" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **FreeRTOS per i dispositivi AIoT embedded** (p. 25). Un **sistema operativo in tempo reale** per **microcontrollori**, **open source**, che rende i piccoli dispositivi edge a basso consumo facili da **programmare**, **rilasciare**, **proteggere**, **connettere** e **gestire**. Un **microcontrollore** contiene un processore **semplice** e con **risorse limitate**, presente in molti dispositivi:
> - elettrodomestici e sensori;
> - fitness tracker;
> - automazione industriale e automobili;
> - schede come un **Raspberry Pi 3B**.

> 💡 **Perché «in tempo reale» (nota didattica).** Un sistema operativo in tempo reale garantisce che un compito venga eseguito **entro una scadenza nota**, non solo «il più velocemente possibile». Un airbag o un freno devono reagire in pochi millisecondi **sempre**, anche nel caso peggiore; un sistema operativo generico può ritardare un processo per centinaia di millisecondi senza violare nessuna regola.

La p. 26 mostra i **passi di progetto**; la p. 27 **AWS IoT Greengrass**, un **gateway edge** che gira su una macchina locale; la p. 28 il percorso **da una «lampada» a un’applicazione di terze parti**. AWS offre anche una piattaforma di AI (§7).

<!-- SOURCE_VISUAL id="IS24-V08" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="26" type="schema" description="Passi di progetto di una soluzione AWS IoT" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V09" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="27" type="schema" description="AWS IoT Greengrass come gateway edge su una macchina locale" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V10" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="28" type="schema" description="Da una lampada a un’applicazione di terze parti con AWS" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. MATLAB, ThingSpeak e Arduino**

_(slide pp. 29–32)_

**MATLAB e l’IoT** (pp. 29–30): con attenzione alle **schede embedded** come **Arduino**. MATLAB è un’ottima piattaforma di ML, ma non solo. La p. 31 presenta **ThingSpeak**, il servizio IoT di MathWorks per raccogliere, visualizzare e analizzare dati dei sensori nel cloud. La p. 32 distingue le funzioni **off-line** e **on-line**.

<!-- SOURCE_VISUAL id="IS24-V11" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="29" type="immagine" description="MATLAB e IoT con le schede Arduino" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V12" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="31" type="schermata" description="Il servizio MATLAB ThingSpeak" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V13" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="32" type="schema" description="Funzioni off-line e on-line in MATLAB per l’IoT" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Off-line e on-line (nota didattica).** Nel flusso tipico:
> - **off-line**, in MATLAB sul PC o nel cloud, si analizzano i dati storici, si addestra e si valida il modello;
> - **on-line**, ThingSpeak o il dispositivo eseguono in tempo reale il modello già addestrato sui nuovi dati, generano grafici e allarmi.
>
> È la separazione tra addestramento e inferenza vista per gli smart camera industriali (§11): **si addestra altrove, si esegue sul posto**.

#### **3.3. Microsoft Azure IoT ed Eclipse ThreadX**

_(slide pp. 33–34)_

La p. 33 presenta **Microsoft Azure IoT**.

<!-- SOURCE_VISUAL id="IS24-V14" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="33" type="schema" description="Architettura di Microsoft Azure IoT" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Eclipse ThreadX, già Azure RTOS** (p. 34). Una **suite di sviluppo embedded** con un sistema operativo **piccolo ma potente**, che offre prestazioni affidabili e **velocissime** su dispositivi con risorse limitate.
> - **Facile da usare**.
> - **Portabile**: supporta i più diffusi **microcontrollori a 32 bit** e gli strumenti di sviluppo embedded, con **riuso** di progetti e conoscenze precedenti.
> - **Collaudato sul mercato**: oltre **6,2 miliardi di dispositivi** nel mondo.
> - **Sicuro**: **IPsec** a livello IP, **TLS** e **DTLS** a livello socket.
> - Il firmware si può aggiornare **over-the-air** con la gestione automatica dei dispositivi.

#### **3.4. Google Coral**

_(slide pp. 35–37)_

> 📌 **Google Coral** (pp. 35–37). Una piattaforma sviluppata da Google per costruire e rilasciare l’**AI all’edge** **senza dipendere dal cloud**.
> - È composta da **componenti hardware**, **strumenti software** e **modelli di ML pre-addestrati**, pensati per un’elaborazione dell’AI **veloce ed efficiente in locale** sui sistemi embedded.
> - L’hardware ruota attorno alla **Edge TPU** (Lezione 23, §6.4), in una fascia di prezzo tra **25 e 130 dollari**.
> - **Raffreddamento passivo** (senza ventole) nella maggior parte delle configurazioni.
> - Un’**ampia gamma di modelli pre-addestrati** da installare sull’hardware.
> - **Inferenza molto veloce**. Un **transfer learning limitato** sul dispositivo è possibile solo in casi specifici.

---

### **4. Schede per l’IoT e l’AIoT**

_(slide pp. 38–47)_

**Schede per l’IoT** (p. 38): dalle schede **Arduino** alle schede **industriali** con acceleratori.

<!-- SOURCE_VISUAL id="IS24-V15" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="38" type="immagini" description="Schede per l’IoT: Arduino, schede industriali e acceleratori" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Alcuni dispositivi IoT** (p. 39), dal più piccolo al più grande:

| Dispositivo | Caratteristiche (slide) | MATLAB | AWS IoT |
|---|---|---|---|
| **ATmega328P** (Arduino UNO) | microcontrollore **a 8 bit**, **32 KB di flash** | sì | **no** |
| **Atmel ATSAMW25** (Arduino MKR1000) | SoC progettato per progetti e dispositivi IoT | — | — |
| processori **ARM** più grandi | **32 e 64 bit** | sì | **sì** |
| **FPGA** + microcontrollore molto grande + **telecamere di visione** | — | — | — |

La slide riporta anche un costo di «1 euro», verosimilmente riferito al microcontrollore più piccolo.

> 💡 **Quanto è piccolo un microcontrollore (nota didattica).** L’ATmega328P ha 32 KB di flash per il programma e **2 KB di RAM**. Un modello con 1000 parametri quantizzati a 8 bit occupa circa 1 KB e ci sta. MobileNetV2, con circa 3,4 milioni di parametri, occupa circa 3,4 MB anche in INT8: **mille volte** la memoria disponibile. Per questo i microcontrollori piccoli ospitano **alberi di decisione** o **reti minuscole** (§4, sensore con machine learning core; §6, TinyML), mentre la visione richiede schede con acceleratori.

**Schede e AIoT** (pp. 40–41, 43). Tabelle comparative da *qengineering.eu* su deep learning con Raspberry Pi e alternative, con le prestazioni misurate in **TOPS** (*Tera Operations Per Second*, Lezione 23, §6.4). La slide annuncia che alcune di queste schede verranno **usate e riaddestrate** nel corso.

<!-- SOURCE_VISUAL id="IS24-V16" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="40" type="tabella" description="Confronto delle schede per il deep learning embedded (qengineering.eu)" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V17" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="41" type="tabella" description="Confronto delle schede per il deep learning embedded, seconda parte" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V18" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="43" type="tabella" description="Schede per l’AIoT con prestazioni in TOPS" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Raspberry Pi AI Kit** (p. 42).
> - Unisce la scheda **M.2 HAT+** di Raspberry Pi a un **modulo di accelerazione Hailo**, per **Raspberry Pi 5**.
> - È un modo **accessibile**, **economico** ed **efficiente nei consumi** per integrare AI ad alte prestazioni.
> - Il modulo è un acceleratore per l’inferenza neurale da **13 TOPS**, basato sul chip **Hailo-8L**.
> - Lavora in precisione **intera a 8 bit** (INT8).

**Schede industriali per l’AIoT** (p. 44). Molte aziende producono schede industriali con **acceleratori per modelli di deep learning**. Per esempio **ST** con **STM32CubeMX** offre:

- la **conversione automatica** di una rete neurale pre-addestrata;
- l’**integrazione** della libreria ottimizzata generata nel progetto dell’utente;
- la **compatibilità con Azure**.

Come esempio di scheda la slide cita il **MAX78000**.

> 💡 **Il MAX78000 (nota didattica).** Non è un prodotto ST ma di Analog Devices (già Maxim Integrated): un microcontrollore con un **acceleratore hardware per CNN** integrato, pensato per eseguire piccole reti di visione e audio con consumi dell’ordine dei millijoule per inferenza. Illustra la stessa tendenza della slide: l’acceleratore scende **dentro** il microcontrollore.

> 📌 **AIoT nella logistica e nelle applicazioni generali** (p. 45). **Intelligenza sul sensore**: il tracciamento intelligente dei beni con il machine learning.
> - Il modulo inerziale a 6 assi **LSM6DSOX** ha un **machine learning core** integrato.
> - Si può costruire un **albero di decisione** per il sensore **senza scrivere una riga di codice**, e portare così nel sensore stesso la logica di **tracciamento intelligente** dei beni (per esempio «in movimento», «fermo», «caduto»).

> 💡 **Perché un albero nel sensore (nota didattica).** Un albero di profondità 4 richiede al più 4 confronti per decisione, e ha al più $2^4 - 1 = 15$ nodi interni (Lezione 12). Il sensore calcola poche feature sulla finestra di accelerazioni (media, varianza, picchi), percorre l’albero e **sveglia il microcontrollore solo quando la classe cambia**. Il microcontrollore principale resta spento quasi sempre, e la batteria dura mesi.

**White paper di MATLAB per l’IoT** (pp. 46–47, **non d’esame**). Un breve documento con una buona panoramica ed esempi di progetti e implementazioni, per esempio come creare un **sistema di allarme per la manutenzione predittiva**. I concetti si applicano a molte applicazioni e contesti.

---

### **5. Punti principali della parte A**

_(slide p. 48)_

> ✅ **Punti principali della parte A** (p. 48, elenco della slide).
> - Caso d’uso: l’architettura IoT del **Pirelli Cyber Tyre**.
> - **Sicurezza dell’IoT**.
> - **Artificial Intelligence of Things** (AIoT).
> - Toolbox:
>   - introduzione a MATLAB, MATLAB per IoT e AIoT, il caso d’uso di **Arduino**;
>   - **Amazon AWS IoT**, **FreeRTOS**;
>   - «Azure ROT» e «W11 IoT»;
> - **schede industriali** per l’AIoT.

> ⚠️ **L’elenco non corrisponde del tutto alle slide (nota didattica).**
> - Il **Pirelli Cyber Tyre** è trattato nella **parte B** (§9.3).
> - «Azure ROT» sta per **Azure RTOS**, cioè Eclipse ThreadX (§3.3).
> - **Windows 11 IoT** e l’**introduzione a MATLAB** non compaiono nelle slide della lezione.
>
> È un elenco ereditato da una versione precedente della lezione: per lo studio fa fede il contenuto delle §1–4.

---

**Parte B: software e servizi cloud per il ML**

### **6. TinyML: machine learning per dispositivi piccolissimi**

_(slide pp. 49–55)_

Titolo della parte B (p. 49): *Machine Learning Software and Cloud Services, Designing for Industry 4.0 and 5.0 applications*.

> 📌 **TinyML** (pp. 50–51). Un **sottoinsieme** del machine learning che permette di eseguire modelli su **dispositivi piccoli a basso consumo**, come i **microcontrollori**. I modelli TinyML vengono tipicamente **addestrati su server cloud potenti** e poi **rilasciati sui dispositivi edge**, dove eseguono l’**inferenza** con **bassa latenza** e **basso consumo**.

**Esempi** (p. 52):

- un **termostato** intelligente che impara le preferenze di riscaldamento e raffrescamento e regola la temperatura;
- un **fitness tracker** indossabile che segue attività e qualità del sonno;
- un **altoparlante** che riconosce la voce e risponde ai comandi;
- una **telecamera di sicurezza** che rileva intrusi e invia un allarme;
- una **macchina** di fabbrica che monitora le proprie prestazioni e **predice la manutenzione** necessaria.

**Applicazioni** (p. 53):

| Settore | Compiti (slide) |
|---|---|
| **dispositivi indossabili** | smartwatch e fitness tracker: tracciamento dell’attività, frequenza cardiaca, riconoscimento dei gesti |
| **IoT industriale** | **manutenzione predittiva**, **rilevamento di anomalie** |
| **casa intelligente** | rilevamento del movimento, riconoscimento vocale, gestione dell’energia |
| **altro** | agricoltura, sanità, trasporti |

La p. 54 confronta il ML **tradizionale** con il **TinyML**.

<!-- SOURCE_VISUAL id="IS24-V19" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="54" type="schema" description="ML tradizionale contro TinyML" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **TensorFlow Lite** (p. 55). Una versione **leggera** di TensorFlow progettata per dispositivi **mobili** ed **embedded**. Permette l’**inferenza sul dispositivo** con **bassa latenza** e un **binario piccolo**, ideale per ambienti con risorse limitate come smartphone, dispositivi IoT e microcontrollori.

> 💡 **Dal modello Keras al file per il dispositivo (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> import tensorflow as tf
>
> converter = tf.lite.TFLiteConverter.from_keras_model(model)   # model: modello Keras addestrato
> converter.optimizations = [tf.lite.Optimize.DEFAULT]           # quantizzazione dei pesi
> tflite_model = converter.convert()
>
> with open("model.tflite", "wb") as f:
>     f.write(tflite_model)
> ```
> Con `Optimize.DEFAULT` i pesi vengono quantizzati a 8 bit (Lezione 23, §6.3). Per portare a interi anche le **attivazioni**, come richiedono molti microcontrollori e acceleratori, bisogna fornire al convertitore un piccolo **dataset rappresentativo**, con cui stimare gli intervalli dei valori. Dal 2024 TensorFlow Lite si chiama **LiteRT**, ma il formato `.tflite` è lo stesso.

---

### **7. Servizi cloud per il ML**

_(slide pp. 56–63)_

> 📌 **Machine Learning as a Service (MLaaS)** (p. 57). Una **definizione ombrello** per le piattaforme cloud **automatiche o semi-automatiche** che coprono la maggior parte dei compiti infrastrutturali:
> - **pre-elaborazione** dei dati;
> - **addestramento** del modello;
> - **valutazione** del modello;
> - **gestione dei dataset**.
>
> I risultati delle predizioni si collegano, per esempio, all’infrastruttura IT interna tramite **API REST**. Una API **REST** (*Representational State Transfer*) è un insieme di regole che permette ad applicazioni diverse di comunicare via web con i metodi HTTP standard: **GET**, **POST**, **PUT**, **DELETE**.

La p. 58 mostra l’esempio di **Microsoft Azure**.

<!-- SOURCE_VISUAL id="IS24-V20" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="58" type="schermata" description="Esempio di servizio MLaaS: Microsoft Azure" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Chiamare un modello via REST (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> import requests
>
> resp = requests.post(
>     "https://esempio-endpoint.cloud/models/qualita/predict",   # indirizzo di esempio
>     headers={"Authorization": "Bearer <token>"},
>     json={"instances": [[5.1, 3.5, 1.4, 0.2]]},
>     timeout=10,
> )
> resp.raise_for_status()
> print(resp.json())          # per esempio {"predictions": ["setosa"]}
> ```
> Il sistema aziendale non deve sapere nulla del modello: invia dati in JSON con un **POST** e riceve la predizione. Il prezzo di questa comodità è che **i dati escono dall’azienda** a ogni chiamata: valgono le regole sulla sovranità dei dati della Lezione 15, §2.

**Il modello a tre livelli dei servizi cloud** (pp. 59–62):

| Livello | Che cosa offre il fornitore (slide) | Esempio (slide) |
|---|---|---|
| **IaaS** (*Infrastructure as a Service*) | accesso **a consumo** (*pay-as-you-go*) a **storage**, **rete**, **server** e altre risorse di calcolo nel cloud | **Amazon Web Services** |
| **PaaS** (*Platform as a Service*) | un **ambiente cloud** in cui gli utenti costruiscono e distribuiscono applicazioni; il fornitore gestisce l’**infrastruttura sottostante** | **Windows Azure** |
| **SaaS** (*Software as a Service*) | **software e applicazioni** via Internet; gli utenti **si abbonano** e accedono via web o tramite le API del fornitore | **Google Workspace**, **Dropbox** |

La p. 62 avverte: **le cose cambiano in fretta**.

<!-- SOURCE_VISUAL id="IS24-V21" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="62" type="schema" description="Il modello a tre livelli dei servizi cloud: IaaS, PaaS, SaaS" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Chi gestisce che cosa (nota didattica).** Il criterio per non confondere i tre livelli è **fin dove arriva la responsabilità del cliente**:
>
> | Che cosa | On-premise | IaaS | PaaS | SaaS |
> |---|---|---|---|---|
> | hardware, rete, data center | cliente | fornitore | fornitore | fornitore |
> | sistema operativo, runtime | cliente | **cliente** | fornitore | fornitore |
> | applicazione, modello | cliente | cliente | **cliente** | fornitore |
> | dati e loro configurazione | cliente | cliente | cliente | **cliente** |
>
> I dati restano **sempre** responsabilità del cliente, anche in SaaS. Per il ML: noleggiare una macchina con GPU è IaaS; un servizio che addestra e ospita il modello caricato è PaaS o MLaaS; un’API già pronta di riconoscimento delle immagini è SaaS.

**Tre servizi MLaaS principali** (p. 63). **Nomi dei prodotti, interfacce e servizi cambiano nel tempo**: per l’offerta attuale di MLaaS e piattaforme di AI si consultano i siti dei principali fornitori (**Google Cloud**, **AWS**, **Microsoft Azure**). Bisogna **concentrarsi sui concetti di progetto**, non memorizzare nomi e caratteristiche dei prodotti.

---

### **8. Sistemi di visione intelligenti: le applicazioni**

_(slide pp. 64–77)_

**Sei esempi di sistemi di visione intelligenti** (pp. 64–65): **robotica**, **controllo**, **fabbrica intelligente**, **sorveglianza**, **ambiente intelligente**, **logistica**.

<!-- SOURCE_VISUAL id="IS24-V22" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="64" type="immagine" description="Introduzione ai sistemi di visione intelligenti" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.1. Robotica e controllo**

_(slide pp. 66–68)_

**Sistemi intelligenti per la robotica** (pp. 66–67).

- **Macchine automatiche** che svolgono compiti prima eseguiti da persone. Esempi: **robot industriali** (pick and place, taglio, saldatura), **aerei da combattimento senza pilota**, **cercatori di mine**, **aspirapolvere autonomi**.
- **Componenti**: struttura del corpo, **attuatori**, fonte di energia, **sensori**, **controllore** (la parte basata sull’AI).
- Uso **generale**: ottimizzazione delle **traiettorie**, **aptica**, sensori, **controllori migliorati**, sistemi di visione, **lavoro collaborativo**. Molti usano **telecamere**.

**Metodi e applicazioni: il controllo** (p. 68). Sistemi intelligenti per il **controllo** di processi industriali, **automazione**, sistemi robotici, prodotti complessi, **reti di distribuzione elettrica**, sistemi **automobilistici** e di **trasporto**. La slide mostra il controllo di una **posizione**, della **stabilità** e di un **impianto**.

#### **8.2. Fabbrica, sorveglianza, ambienti intelligenti**

_(slide pp. 69–73)_

| Applicazione (slide) | Sfide e funzioni |
|---|---|
| **ispezione della produzione** (fabbrica intelligente, p. 69) | **ingressi non ideali** e **cambiamenti nel tempo** |
| **sorveglianza e sicurezza** (p. 70) | **scenari complessi e diversi** da gestire |
| **auto intelligenti** (p. 71) | **identificazione del conducente**, **assistenza alla guida**, **guida autonoma**, sicurezza e sorveglianza, **impostazioni automatiche** |
| **intrattenimento** (p. 72) | **identificazione**, **profilazione** (adulto, bambino, cane, gatto), sicurezza |
| **gestione intelligente del traffico** (p. 73, smart city) | **rilevamento di anomalie**, **prevenzione del crimine**, sicurezza e sorveglianza, profilazione (adulto, bambino) |

<!-- SOURCE_VISUAL id="IS24-V23" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="69" type="immagini" description="Visione intelligente per l’ispezione della produzione" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V24" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="70" type="immagini" description="Visione intelligente per sorveglianza e sicurezza" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V25" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="71" type="immagini" description="Ambienti intelligenti: l’auto" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V26" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="72" type="immagini" description="Ambienti intelligenti: l’intrattenimento" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V27" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="73" type="immagini" description="Gestione intelligente del traffico nella smart city" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Profilazione e AI Act (nota didattica).** Profilare le persone negli spazi pubblici per la «prevenzione del crimine» tocca i casi più regolati dell’AI Act (Lezione 02, §1.3). Sono **vietati**:
> - l’identificazione biometrica remota **in tempo reale** negli spazi pubblici a fini di polizia, salvo eccezioni strettamente elencate;
> - la valutazione del rischio che una persona commetta reati basata **solo sulla profilazione**.
>
> Molti altri usi biometrici sono **ad alto rischio**. Un sistema tecnicamente semplice può quindi essere **non rilasciabile** per ragioni normative: va verificato **prima** del progetto, non dopo.

#### **8.3. Ambiente e logistica**

_(slide pp. 74–77)_

> 📌 **Rilevamento del fumo degli incendi boschivi** (p. 74).
> - **Il problema**: riguarda le regioni con **clima caldo** e **vegetazione estesa**. Le tecniche attuali richiedono **grandi infrastrutture** e **ispezione umana**.
> - **L’approccio innovativo** di visione artificiale deve lavorare con:
>   - luce **visibile**;
>   - fotogrammi di **bassa qualità**;
>   - **potenza hardware limitata**;
>   - **ambienti diversi**.
> - **Le feature distintive** del fumo sono di due tipi:
>   - **statiche**: analisi del **colore del fumo**, rilevamento dei **bordi netti**, analisi del **disordine del perimetro**;
>   - **dinamiche**: rilevamento di **regioni in crescita** e di **regioni che salgono**.
> - **Intelligenza computazionale**: il sistema è **flessibile** e può **imparare nuovi ambienti**.
> - **La pipeline**: rilevamento delle **regioni in movimento** → **estrazione delle feature** → **classificatore** di intelligenza computazionale → fotogramma con fumo → **fumo segmentato**. Il **passo 1** (estrazione delle feature) è **progettato dal designer**; il **passo 2** è la **classificazione con una rete neurale**.

<!-- SOURCE_VISUAL id="IS24-V28" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="74" type="diagramma" description="Pipeline di rilevamento del fumo: regioni in movimento, feature statiche e dinamiche, classificatore neurale" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Un ibrido esemplare (nota didattica).** È la soluzione ibrida della Lezione 23, §2.2: le feature sono progettate con la conoscenza del fenomeno (il fumo **sale**, **cresce**, ha **bordi sfumati** e un perimetro **irregolare**), e la rete neurale le combina. La scelta è dettata dai vincoli della slide:
> - l’hardware limitato esclude una CNN profonda su ogni fotogramma;
> - la varietà degli ambienti rende preziose feature fisicamente motivate, che generalizzano meglio di feature apprese su pochi scenari.
>
> Il rilevamento delle regioni in movimento fa da **filtro dei candidati**, come la watershed nel caso dei droni.

**AI nella logistica (con lenti liquide)** (p. 75). Su un **nastro trasportatore** i **codici a barre** si leggono in modo efficace **cambiando la focale** dell’ottica; l’**OCR con deep learning** garantisce poi l’**identificazione corretta**.

> 💡 **Perché le lenti liquide (nota didattica).** Pacchi di altezze diverse passano sotto la stessa telecamera a distanze diverse, e un’ottica fissa ha una **profondità di campo** limitata: alcuni codici risulterebbero sfocati. Una lente liquida cambia la propria curvatura con una tensione elettrica e rimette a fuoco **in pochi millisecondi**, senza parti meccaniche in movimento. Un sensore di altezza, o l’immagine stessa, dice alla lente dove mettere a fuoco per ogni pacco.

<!-- SOURCE_VISUAL id="IS24-V29" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="76" type="immagini" description="Metodi e applicazioni della visione nella logistica" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Inventario con droni** (p. 77). L’automazione dell’inventario con i droni riguarda un’industria del magazzinaggio da **1,9 trilioni di dollari**. **Corvus Robotics**, fondata da Mohammed Kabir, usa droni capaci di **navigare in ambienti senza GPS** (l’interno dei magazzini) per accelerare la gestione dell’inventario.

---

### **9. Trasporti intelligenti**

_(slide pp. 78–89)_

#### **9.1. V2X e platooning**

_(slide pp. 78–82)_

> 📌 **Vehicle to everything (V2X)** (p. 79). Un termine che indica una comunicazione:
> - ad **alta banda**;
> - a **bassa latenza**;
> - **altamente affidabile**
>
> tra un’ampia gamma di sensori legati al **trasporto** e al **traffico**. Le reti mobili **5G** saranno fondamentali per la connettività **veicolo-veicolo** (V2V) e **veicolo-infrastruttura** (V2I).

La p. 80 mostra la **road map del V2X** (da leggere pensando anche alle auto elettriche e ibride).

<!-- SOURCE_VISUAL id="IS24-V30" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="80" type="schema" description="Road map del V2X" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Le funzioni del V2X** (p. 81). Immaginiamo di essere un **pedone** che si avvicina alle **strisce**: il **semaforo** può cambiare colore e le **auto** vengono avvisate prima. Il risultato è una **circolazione più fluida e sicura**.

> 💡 **Perché la latenza conta (nota didattica).** A 130 km/h un’auto percorre
> $$\frac{130\ \text{km/h}}{3{,}6} \approx 36{,}1\ \text{m/s}$$
> Con 100 ms di latenza, un avviso arriva quando l’auto ha già percorso circa 3,6 m; con 10 ms, circa 36 cm. Per un avviso di frenata tra veicoli vicini la differenza è tra un tamponamento evitato e uno no.

> 📌 **Platooning con l’IoT** (p. 82). Un sistema di trasporto innovativo in cui i **camion** viaggiano **molto vicini**, uno dietro l’altro, grazie a un sistema di comunicazione comune basato sull’IoT **V2V**.
> - Serve **un solo conducente**, nel mezzo di testa.
> - **Tesla**, **Volvo**, **Iveco**, **Scania** e altri stanno sviluppando prototipi.
> - L’introduzione sul mercato è prevista tra il **2023 e il 2030**.

<!-- SOURCE_VISUAL id="IS24-V31" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="82" type="immagine" description="Platooning di camion con comunicazione V2V" reason="Immagine presente solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.2. Sistemi di trasporto intelligenti**

_(slide pp. 83–84)_

> 📌 **Sistemi di trasporto intelligenti (ITS)** (p. 83). Sistemi avanzati di gestione del **trasporto** e del **traffico** a livello di autostrada, città o regione, che permettono agli utenti di essere **meglio informati** e di usare le reti di trasporto in modo **più sicuro**, **coordinato** e «**intelligente**» grazie all’AI. Esempi:
> - **chiamata dei soccorsi** quando avviene un incidente;
> - **telecamere** per far rispettare il codice della strada;
> - **segnali** che cambiano il limite di velocità in base alle condizioni del traffico e del meteo;
> - sistemi **anticollisione**.

**Caratteristiche chiave** (p. 84):

| Caratteristica | Dettaglio (slide) |
|---|---|
| **comunicazioni wireless** | a **corto raggio** (fino a **350 m**, con IEEE 802.11); a **lungo raggio** tramite reti infrastrutturali come **WiMAX** (IEEE 802.16), **GSM**, **3G**, **4G**, **5G** |
| **tecnologie di calcolo** | **microcontrollori** su sensori e dispositivi dell’auto; **IoT** e dispositivi di dati dinamici; **sensori virtuali** (per esempio il sensore di presenza di **ghiaccio**) |
| **fusione di dati eterogenei** | da sensori, telecamere di sorveglianza, smartphone, antenne GSM, con **protocolli diversi** e **intelligenza artificiale** |

#### **9.3. Caso d’uso: Pirelli Cyber Tyre**

_(slide pp. 85–89)_

**Da un sensore al cloud** (p. 85). Il sistema **Pirelli Cyber** (detto anche «Connesso»).

<!-- SOURCE_VISUAL id="IS24-V32" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="85" type="immagine" description="Pirelli Cyber: da un sensore al cloud" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Da un piccolo sensore alla smart city** (p. 86). **ADAS** (*Advanced Driver Assistance Systems*) potenziati dal **5G**, in un progetto di **Pirelli**, **Ericsson**, **Audi**, **TIM**, **Italdesign** e **KTH**:
> 1. i **sensori negli pneumatici** sono **a contatto con la strada**;
> 2. i sensori **rilevano pericoli** sul fondo stradale (**acqua**, **scarsa aderenza**, …);
> 3. l’auto **adatta** il proprio **controllo** e i sistemi di **assistenza alla guida**;
> 4. l’auto **condivide** la stessa informazione con **le altre auto** e con l’**infrastruttura**.

<!-- SOURCE_VISUAL id="IS24-V33" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="86" type="schema" description="Sensori negli pneumatici che rilevano il pericolo e lo condividono via 5G con auto e infrastruttura" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’auto Cyber Car IoT** (p. 87): il **sensore** nello pneumatico misura la **pressione** e fornisce **altre informazioni**. **Edge → fog → cloud** (p. 88): i dati salgono dal sensore all’auto e al cloud **Amazon AWS**. **Opportunità di business** (p. 89) nate dall’**architettura a sensori intelligenti**: *Cyber Tyre* per le auto e *Cyber Fleet* per le flotte.

<!-- SOURCE_VISUAL id="IS24-V34" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="87" type="immagine" description="Sensore di pressione e informazioni nello pneumatico Pirelli Cyber" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V35" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="88" type="schema" description="Pirelli Cyber Car: architettura edge, fog e cloud su Amazon AWS" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Leggere il caso con gli strumenti del corso (nota didattica).**
>
> | Livello | Nel Cyber Tyre | Lezione |
> |---|---|---|
> | **sensore** | pressione, temperatura, accelerazioni al contatto con la strada: sorgente di dati **dinamica** | Lezione 05, §6.2 |
> | **edge** (auto) | stima dell’**aderenza** e del **pericolo** in tempo reale: latenza minima, perché controlla l’auto | §1.1 |
> | **fog** (rete 5G, infrastruttura) | **condivisione** con le auto vicine | §9.1 |
> | **cloud** (AWS) | storico della flotta, **manutenzione predittiva**, nuovi servizi | §7 |
>
> Il valore di business sta nel software e nei servizi costruiti sopra lo stesso sensore: è il «più prezioso» della p. 10.

---

### **10. Industria 4.0 e 5.0**

_(slide pp. 90–99)_

#### **10.1. Industria 4.0**

_(slide pp. 90–96)_

**In breve** (p. 91). **Tecnologie, architetture, macchine, sensori e dispositivi IoT** con connettività (wireless) potenziata, collegati a un sistema che può **rappresentare l’intera linea di produzione** e **prendere decisioni da solo**. Sono disponibili **molti programmi di finanziamento** per l’Industria 4.0, e **tutti i settori** stanno investendo.

> 📌 **Industria 4.0** (p. 92). Indica **processi e sistemi connessi altamente intelligenti**, creati attraverso la **comunicazione digitale in rete dei dati**. È «**l’argomento del corso**».

**L’ambiente I4.0** (p. 93). L’**Internet of People** (IoP) è la nuova infrastruttura da creare perché le persone possano **comunicare tra loro** con i **propri dispositivi**, **direttamente**, **senza dipendere da una terza parte fidata** che stabilisca la connessione.

**Tecnologie digitali che contribuiscono** (p. 94), dal rapporto PwC *Building the digital enterprise*.

<!-- SOURCE_VISUAL id="IS24-V36" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="90" type="immagine" description="Introduzione all’Industria 4.0" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V37" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="94" type="schema" description="Tecnologie digitali dell’Industria 4.0 dal rapporto PwC" reason="Elenco delle tecnologie presente solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **I principi di progetto dell’Industria 4.0** (pp. 95–96). Sono i requisiti per essere conformi ai **finanziamenti** Industria 4.0: se si guida il progetto di un sistema finanziato, vanno tenuti presenti.
> 1. **Interoperabilità**: la capacità di macchine, dispositivi, sensori e persone di **connettersi e comunicare** tra loro tramite l’**Internet of Things** o l’**Internet of People**.
> 2. **Trasparenza dell’informazione**: la capacità dei sistemi informativi di creare una **copia virtuale del mondo fisico**, arricchendo i **modelli digitali dell’impianto** con i **dati dei sensori**. Richiede di **aggregare** i dati grezzi in **informazioni di contesto** di valore più alto.
> 3. **Assistenza tecnica migliorata**: la capacità dei sistemi di assistenza di **supportare le persone**:
>    - **aggregando** e **visualizzando** le informazioni in modo comprensibile, per decisioni informate e problemi urgenti;
>    - sfruttando i **sistemi cyber-fisici** per i compiti **sgradevoli**, **troppo faticosi** o **pericolosi** per i colleghi umani.
> 4. **Decisioni decentralizzate**: la capacità dei sistemi cyber-fisici di **decidere da soli** e svolgere i compiti nel modo **più autonomo possibile**. **Solo** in caso di **eccezioni**, **interferenze** o **obiettivi in conflitto** i compiti vengono delegati a un **livello superiore**.

> 💡 **I principi e il corso (nota didattica).**
> - La **trasparenza dell’informazione** descrive il **digital twin**: un modello dell’impianto aggiornato dai sensori, che richiede dati **sincronizzati e armonizzati** (Lezione 06).
> - L’**assistenza tecnica** è la visualizzazione e la dashboard della Lezione 11, §6.
> - Le **decisioni decentralizzate** con escalation delle eccezioni sono il modello «**edge decide, livello superiore interviene sulle eccezioni**» del §1.1, e anche la logica della classe «nessuno» e della soglia di confidenza (Lezione 05, §1.6): il sistema autonomo deve **sapere quando non sa**.

#### **10.2. Industria 5.0**

_(slide pp. 97–99)_

> 📌 **Industria 5.0** (pp. 98–99). Punta a:
> - un **approccio centrato sull’uomo** per le tecnologie digitali, **intelligenza artificiale** inclusa;
> - l’**aggiornamento** e la **riqualificazione** delle competenze dei lavoratori europei, in particolare quelle **digitali**;
> - industrie **moderne**, **efficienti nell’uso delle risorse** e **sostenibili**, e la transizione a un’**economia circolare**;
> - un’industria **competitiva a livello globale** e **leader nel mondo**, accelerando gli investimenti in **ricerca e innovazione**.

<!-- SOURCE_VISUAL id="IS24-V38" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="98" type="immagine" description="Industria 5.0" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **4.0 contro 5.0 (nota didattica).** La Commissione europea (2021) riassume l’Industria 5.0 in tre valori:
> - **centralità dell’uomo**;
> - **sostenibilità**;
> - **resilienza**.
>
> Non sostituisce l’Industria 4.0, ma ne cambia l’obiettivo: la 4.0 chiede **che cosa può fare la tecnologia** (connettere, automatizzare, decidere); la 5.0 chiede **a che cosa deve servire** (le persone, l’ambiente, la capacità di reggere agli shock). Nel corso questo corrisponde al progetto centrato sull’uomo della Lezione 05, §1.2 e alle regole dell’AI Act (Lezione 02, §1). Il quadro storico delle ere industriali è nella Lezione 04, §5.

---

### **11. Sistemi di visione industriali**

_(slide pp. 100–113)_

#### **11.1. Il ruolo delle telecamere intelligenti**

_(slide pp. 100–106)_

**Sistema di visione intelligente** (p. 101). Le **telecamere industriali** diventano **sempre più piccole ed economiche**, mentre le prestazioni **migliorano** continuamente. I sistemi di imaging per la visione artificiale, insieme a **capacità di rete** sempre maggiori, aprono il potenziale per **nuove applicazioni** e per l’avanzamento dell’Industria 4.0.

> 📌 **Sistemi di visione per l’Industria 4.0** (p. 102). Sono **gli occhi** che catturano il processo e forniscono **feedback** tramite il software di elaborazione delle immagini. In **tutte le fasi** della produzione, dall’**ispezione delle materie prime** e dal **monitoraggio della produzione** fino all’**ispezione finale** e all’**assicurazione qualità**, le telecamere industriali sono usate in tutti i settori e sono una parte **indispensabile** per ottenere **efficienza** e **alti standard di qualità** (white paper Basler, *Industrial Cameras in Industry 4.0 for Efficient Production*).

> 📌 **Storia recente** (p. 103). Dal **2018–2020**: **telecamere intelligenti con FPGA**:
> - con **reti neurali profonde a bordo**;
> - che eseguono **misure**, lettura di **OCR + QR + codici a barre** e tecniche avanzate;
> - che **non addestrano a bordo**: **solo inferenza**.

**Sensori e telecamere intelligenti** (p. 104). **Telecamera** + **elaborazione a bordo** = **smart camera**. La slide mostra anche un **profilometro laser 3D**.

<!-- SOURCE_VISUAL id="IS24-V39" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="103" type="immagini" description="Smart camera con FPGA e rete neurale a bordo" reason="Immagini presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V40" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="104" type="immagini" description="Sensori industriali: smart camera e profilometro laser 3D" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sistemi di visione industriali** (pp. 105–106): **qualità**, **ispezione**, **produzione**, **sicurezza**, **lungo tutta la catena produttiva**.

<!-- SOURCE_VISUAL id="IS24-V41" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="105" type="immagini" description="Sistemi di visione industriali per qualità, ispezione, produzione e sicurezza" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V42" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="106" type="immagini" description="Visione industriale lungo tutta la catena produttiva" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.2. Il sistema completo**

_(slide pp. 107–111)_

> 📌 **La configurazione di base** (p. 107). **Hardware + software** (+ **modelli profondi**) per:
> - **classificazione**;
> - **misure**;
> - **valutazione della qualità**.
>
> Con i **sistemi di illuminazione** come parte essenziale del sistema.

<!-- SOURCE_VISUAL id="IS24-V43" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="107" type="schema" description="Configurazione di base di un sistema di visione industriale con illuminazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché l’illuminazione è metà del sistema (nota didattica).** Un modello non può recuperare un difetto che l’immagine **non contiene**. Un graffio su una superficie lucida è invisibile con luce diffusa e diventa evidentissimo con luce **radente**. Una retroilluminazione trasforma il contorno di un pezzo in una silhouette nera su fondo bianco, facile da misurare. Progettare bene luce e ottica rende spesso **inutile** un modello complesso, ed è la prima applicazione del rasoio di Occam (Lezione 05, §3.2) in un sistema di visione.

La p. 108 mostra l’**ambiente di visione industriale** con l’**unità di elaborazione delle immagini**.

<!-- SOURCE_VISUAL id="IS24-V44" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="108" type="schema" description="Ambiente di visione industriale con unità di elaborazione delle immagini" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Esempio di rete di controllo dell’automazione** (p. 109). Un sistema intelligente **scarta i pezzi difettosi** da un nastro trasportatore.
> - La rete usa **protocolli fieldbus** su una rete **Ethernet 10/100** con supporto alla **sincronizzazione temporale IEEE 1588**.
> - I componenti sono:
>   - **HMI**;
>   - **I/O distribuiti** e **switch Ethernet**;
>   - **controllori di movimento** e **di azionamento**;
>   - **tempo GPS** con pannello di sincronizzazione;
>   - **smart camera**, che prende la **decisione a bordo**;
>   - **braccio robotico** e controllo del nastro, con la **scatola dei pezzi difettosi** e l’imballaggio dei pezzi buoni.

<!-- SOURCE_VISUAL id="IS24-V45" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="109" type="schema" description="Rete di controllo dell’automazione con smart camera, controllori di movimento e sincronizzazione IEEE 1588" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché serve IEEE 1588 (nota didattica).** La telecamera vede il pezzo in un punto, il braccio lo scarta **più avanti** sul nastro. Se il nastro scorre a 1 m/s e il braccio è 0,5 m dopo la telecamera, il braccio deve agire esattamente 500 ms dopo lo scatto. Un errore di **10 ms** tra gli orologi dei due dispositivi sposta il punto di presa di **1 cm**, abbastanza per mancare un pezzo piccolo. Il **Precision Time Protocol** (IEEE 1588) sincronizza gli orologi con precisione sotto il millisecondo, come visto per la sincronizzazione dei dati nella Lezione 06.

**Esempio: misura dei contatti di un relè** (p. 110). Due **telecamere USB** con ottica **C-mount**, il **relè da controllare**, una **struttura di attuazione e movimentazione** e un **sensore di prossimità**.

<!-- SOURCE_VISUAL id="IS24-V46" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="110" type="immagine" description="Sistema di misura dei contatti di un relè con due telecamere USB" reason="Configurazione visibile solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sistemi completi** (p. 111): **100 000–200 000 euro**.

<!-- SOURCE_VISUAL id="IS24-V47" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="111" type="immagini" description="Sistemi di visione industriali completi da 100.000 a 200.000 euro" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3. Rilevamento di anomalie e piccoli progetti**

_(slide pp. 112–113)_

> 📌 **Rilevamento di anomalie** (p. 112). Come si insegna a una telecamera a **riconoscere deviazioni** che **non sono prevedibili**, o non lo sono del tutto? L’elaborazione delle immagini **basata su regole** dovrebbe arrendersi; con i sistemi di AI la sfida si risolve facilmente. Il rilevamento di anomalie è la **terza funzionalità dell’AI**, oltre al **rilevamento** e alla **classificazione** degli oggetti. Consiste nell’identificare **eventi**, **oggetti** od **osservazioni rari**, sospetti perché **differiscono in modo significativo** dai comportamenti o dai pattern standard.

> 💡 **Come si fa senza esempi di difetti (nota didattica).** Il problema pratico è che i difetti sono **rari** e **tutti diversi**: non si può addestrare un classificatore con una classe «difetto» ben rappresentata (Lezione 14, §2). Si addestra allora un modello **solo sui pezzi buoni**, per esempio un **autoencoder** (Lezione 22, §1), e si segnala come anomalia ciò che il modello **non sa ricostruire**:
> $$\text{anomalia} \iff \lVert \mathbf{x} - \hat{\mathbf{x}} \rVert^2 > \tau$$
> La soglia $\tau$ si sceglie su un **validation set di pezzi buoni**, per esempio al 99° percentile dell’errore di ricostruzione: così si accetta circa l’1% di falsi allarmi. «Facilmente», come dice la slide, va letto con cautela. Serve verificare su difetti veri, anche pochi, che la soglia li catturi, e controllare nel tempo il **drift** (Lezione 11, §4): un cambio di fornitore della materia prima può far sembrare anomali tutti i pezzi.

**Piccoli progetti e piccoli budget** (p. 113): una telecamera da **2 megapixel** a **8 dollari**.

<!-- SOURCE_VISUAL id="IS24-V48" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="113" type="immagine" description="Telecamera da 2 MP a 8 dollari per piccoli progetti" reason="Immagine presente solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Da 8 dollari a 200 000 euro (nota didattica).** La differenza di prezzo tra le pp. 111 e 113 non sta nel sensore. Un sistema industriale completo include:
> - **ottiche** e **illuminazione** progettate per il pezzo;
> - **meccanica** e integrazione con robot e PLC;
> - **sincronizzazione**;
> - **validazione** su migliaia di pezzi;
> - **certificazione** e **manutenzione** per anni.
>
> Un prototipo a 8 dollari dimostra che l’idea funziona; un sistema a 200 000 euro garantisce che funzioni **sempre**, su una linea che non può fermarsi. È la distanza tra «saper addestrare una rete» e «saper progettare un sistema intelligente» con cui il corso si era aperto (Lezione 01, §2.3).

---

### **12. Simulazione d’esame**

_(slide p. 114)_

> 📌 **Simulazione d’esame** (p. 114).
> - Una simulazione dell’esame viene **svolta in aula**. Come spiegato nella prima lezione, **non viene distribuito alcun PDF**.
> - Per le domande sulle **prime 4 e 6 lezioni** si vedano le simulazioni della **lezione 4** e della **lezione 6**.
> - Per le domande sulle **lezioni 4–13** si veda la simulazione della **lezione 13**.
> - Questa simulazione si concentra sulle **lezioni dalla 14** in poi (il testo della slide si interrompe qui).

> ⚠️ **Dove sono le simulazioni in questi appunti.** Nei materiali del corso:
> - la prima simulazione è nella **Lezione 04, §6**;
> - la simulazione breve di 5 domande è nella **Lezione 06**;
> - la simulazione di metà corso con 20 domande è nel PDF della **Lezione 12**, non della 13 come indica la slide.
>
> La simulazione finale non è pubblicata; il metodo di risposta e le regole di punteggio restano quelli delle simulazioni precedenti.

---

### **13. Allegato: approfondimento sull’Industria 4.0 (non d’esame)**

_(slide pp. 116–121)_

L’allegato, esplicitamente **non oggetto d’esame**, descrive **4 caratteristiche sinergiche** dell’Industria 4.0 (p. 117).

| Caratteristica | Contenuto (slide) |
|---|---|
| **1. Integrazione verticale** dei sistemi di produzione intelligenti (p. 118) | usa i **sistemi di produzione cyber-fisici** (CPPS) perché gli impianti reagiscano rapidamente a variazioni della **domanda**, delle **scorte** e ai **guasti**. Le **fabbriche intelligenti** si **auto-organizzano** e permettono una produzione **personalizzata** sul cliente, dal mercato alla produzione |
| **2. Integrazione orizzontale** tramite una nuova generazione di **reti globali della catena del valore** (p. 119) | reti locali e globali collegano tramite CPPS **logistica**, **magazzino**, **produzione**, **marketing** e **vendite**. Le reti sono **ottimizzate in tempo reale**, con **trasparenza** e **flessibilità** per rispondere più in fretta a problemi e guasti e un’**ottimizzazione globale** migliore. La **storia di ogni parte o prodotto** è registrata e consultabile in qualsiasi momento: **tracciabilità** costante |
| **3. Ingegneria continua** lungo tutta la catena del valore (p. 120) | lo **sviluppo** e la **produzione** di **nuovi prodotti** e **sistemi produttivi** sono integrati e coordinati con il **ciclo di vita** dei prodotti, creando sinergie tra sviluppo del prodotto e sistemi di produzione: sviluppo → produzione → ciclo di vita |
| **4. Accelerazione** tramite **tecnologie esponenziali** (p. 121) | l’Industria 4.0 richiede soluzioni di automazione **altamente cognitive** e **altamente autonome**. Per aumentare ancora l’autonomia e accelerare **individualizzazione** e **flessibilità** servono **intelligenza artificiale**, **robotica avanzata** e **tecnologie dei sensori** |

<!-- SOURCE_VISUAL id="IS24-V49" source="Lesson_24_Machine_Learning_Software_Cloud_Services.pdf" page="118" type="schema" description="Integrazione verticale tra mercato e produzione nell’Industria 4.0" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **IoT** / **AIoT** | dispositivi che raccolgono e trasmettono dati / dati trasformati in predizioni, allarmi e azioni |
| **Cloud** / **edge** / **dispositivo** | modelli grandi e riaddestramento / bassa latenza e funzionamento senza rete / privacy ed energia minima |
| **Sistema operativo generico** / **RTOS** | il più veloce possibile in media / entro una scadenza garantita |
| **HTTPS** / **MQTT** | HTTP su TLS / messaggistica leggera, sicura solo su TLS |
| **TLS sul canale** / **firma del firmware** | protegge il trasporto dell’aggiornamento / garantisce che il firmware venga dal produttore |
| **Cyber Resilience Act** / **NIST framework** | obbligatorio per i prodotti venduti nell’UE, marcatura CE / volontario negli USA |
| **FreeRTOS** / **ThreadX** / **Greengrass** | RTOS per microcontrollori (AWS) / RTOS embedded (già Azure RTOS) / gateway edge su macchina locale |
| **Microcontrollore** / **scheda con acceleratore** | KB di memoria, alberi e reti minuscole / TOPS di calcolo, visione e CNN |
| **TinyML** / **ML tradizionale** | inferenza su microcontrollori a basso consumo / addestramento e inferenza su server |
| **MLaaS** / **IaaS** / **PaaS** / **SaaS** | piattaforma di ML completa / infrastruttura a consumo / ambiente per costruire applicazioni / applicazione pronta |
| **Rilevamento/classificazione** / **rilevamento di anomalie** | classi note e rappresentate / deviazioni rare e imprevedibili, modello addestrato sui normali |
| **V2V** / **V2I** | veicolo con veicolo / veicolo con infrastruttura |
| **Industria 4.0** / **5.0** | connessione, automazione, decisioni decentralizzate / centralità dell’uomo, sostenibilità, resilienza |
| **Smart camera** / **sistema di visione completo** | telecamera con inferenza a bordo / ottica, luce, meccanica, integrazione, validazione e certificazione |
| **Addestramento** / **inferenza a bordo** | altrove, su server o cloud / sul posto, sulla smart camera o sul dispositivo |

---

### **15. Sintesi della lezione**

_(slide p. 115)_

> ✅ **Punti principali** (p. 115, con l’integrazione della parte A).
> - **AIoT**: architettura cloud/edge/dispositivo decisa da latenza, banda, privacy, energia e affidabilità; hardware e software abilitanti; qualità del dato come parte del sistema.
> - **Sicurezza dell’IoT**: aggiornamenti OTA protetti, Cyber Resilience Act e framework NIST.
> - **Piattaforme e schede**: AWS IoT con FreeRTOS e Greengrass, MATLAB e ThingSpeak, Azure IoT ed Eclipse ThreadX, Google Coral, Raspberry Pi AI Kit, schede industriali e sensori con ML a bordo.
> - **TinyML** e **servizi cloud per il ML** (MLaaS, IaaS, PaaS, SaaS): concetti di progetto, non nomi di prodotti.
> - **Applicazioni dei sistemi di visione intelligenti**:
>   - **Industria 4.0 e 5.0** e i loro principi di progetto;
>   - **ambient intelligence** e domotica;
>   - **supply chain** e logistica;
>   - applicazioni **ambientali** come il rilevamento del fumo;
>   - trasporti intelligenti e il caso Pirelli Cyber Tyre.
> - **Sistemi di visione intelligenti**: rilevamento, segmentazione e riconoscimento con soluzioni classiche, profonde e ibride; smart camera, illuminazione, sincronizzazione e rilevamento di anomalie.
> - **Allegato**: dettagli sull’Industria 4.0 (non d’esame).
