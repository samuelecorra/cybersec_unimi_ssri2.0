# Cos’è una “rete”

Immagina una mappa: **punti** collegati da **strade**. In una rete i punti sono “cose” (computer, telefoni, centrali, antenne) e le strade sono collegamenti (cavi, fibre, radio).

## Cos’è un **grafo** (il modello matematico di una rete)

Un grafo si scrive così:

$$G=(V,E)$$

- **$V$** = _Vertices_ → **insieme dei vertici** (in italiano: **nodi**).
    
- **$E$** = _Edges_ → **insieme degli archi** (in italiano: **collegamenti** o **link**).
    

> Nota: le lettere V ed E vengono dall’inglese _Vertices_ ed _Edges_. Per questo vedi proprio “V” ed “E”. Esploreremo meglio in futuro la questione...
---

# Cos’è una **rete di telecomunicazioni**

“**Tele**” (gr. _lontano_): rete che **trasporta informazione a distanza** (voce, dati, video).

**Componenti principali: approfondiremo in futuro...**

- **Terminali**: telefoni, PC, sensori.
    
- **Nodi di rete**: commutatori/centrali, router, stazioni radio base.
    
- **Link**: **rame**, **fibra ottica**, **radio** (microonde, cellulare, Wi-Fi), **satellite**.
    
- **Canale**: il link “visto” dal segnale (banda concessa, rumore, regole fisiche).
    
- **Protocolli**: regole per incartare, indirizzare, consegnare e correggere (livelli: fisico → collegamento → rete → trasporto… in piccolo, l’idea dell’OSI).
    
- **Indirizzamento**: come “chiamo” i terminali (numeri telefonici; IP/DNS su Internet).
    

**Metriche chiave**

- **Banda (Hz)**: quanta “larghezza di spettro” mi lascia passare il canale.
    
- **Capacità (bit/s)**: massimo teorico d’informazione (dipende da banda, **SNR**, modulazione/codifica).
    
- **Throughput (bit/s)**: velocità **reale** percepita.
    
- **Latenza (ms)** & **jitter**: tempo e variabilità → critici per voce/video.
    
- **Affidabilità**: probabilità di consegna corretta (ridondanza + codici d’errore).
    

---

# La traiettoria storica della slide (e il “perché” di ogni passo)

![Pasted image 20250908211708.png](../../imgs/Pasted%20image%2020250908211708.png)

1. **In principio fu il telegrafo**  
    Prima rete moderna: messaggi brevi, affidabili a distanza, ma **legata ai cavi**.
    
2. **La rete telefonica**  
    Passo enorme: non più messaggi puntuali ma **conversazioni in tempo reale** tra persone lontane. (Qui dopo entreremo meglio.)
    
3. **Le prime reti senza fili**  
    L’aria diventa il “cavo” di tutti: **copertura più rapida**, nascono la mobilità e i collegamenti dove i cavi non arrivano facilmente.
    
4. **Dal mondo analogico al digitale**  
    Con i computer, le informazioni diventano **bit**: la trasmissione si fa più **robusta** e **flessibile**; diventa facile condividere lo stesso mezzo tra più utenti e servizi.
    
5. **Le reti cellulari**  
    Il territorio viene diviso in **celle**: così la stessa “frequenza” si riusa in zone diverse → più utenti serviti e **vera mobilità** (telefonino).
    
6. **Arrivano i satelliti**  
    **Copertura globale** e tratte oceaniche: con una “singola infrastruttura in cielo” collego zone lontanissime. (Compromesso: più distanza = più ritardo.)
    
7. **La rivoluzione della fibra ottica** (ritorno ai cavi)  
    Si torna al cavo, ma di **luce**: **capacità enorme**, **perdite piccole**, **pochi disturbi**. La fibra diventa la **spina dorsale** di Internet moderno.
    

👉 Morale della slide: non è un “vai e torna” casuale; è **scegliere il mezzo giusto per l’esigenza giusta**:

- **Senza fili/satellite** per coprire veloce e ovunque,
    
- **Fibra** per trasportare **tanto** e **veloce** dove passa il grosso del traffico.
    

---

# Due chicche introduttive utili (che aiutano a capire il resto del corso)

- **“Rete telematica”** = telecomunicazioni + informatica: trasporto (rete) + elaborazione (computer). Internet è la **rete delle reti** che ha unito tutto.
    
- **Scala**: man mano che crescono utenti e servizi, si passa da “due che parlano” a **milioni** che scambiano dati: servono regole comuni (standard) e un’infrastruttura gerarchica (accesso → dorsali). CHE VUOL DIRE? Le reti di telecomunicazioni non sono un “ammasso di fili”, ma sono **organizzate a livelli**, un po’ come le strade:

- **accesso** → le stradine di quartiere che portano le persone sulle vie principali. Nelle telecomunicazioni sono i collegamenti tra il singolo utente (casa, ufficio, smartphone) e la rete (doppino, fibra FTTH, antenna 4G/5G).
    
- **dorsali** (o _backbone_) → sono come le autostrade: portano tantissimo traffico su lunghe distanze, collegando città, regioni, stati. Nelle telecomunicazioni oggi sono soprattutto **fibre ottiche ad alta capacità**.
    

in mezzo ci sono i livelli intermedi, detti spesso **rete di aggregazione o metropolitana**: strade provinciali che raccolgono il traffico delle vie locali e lo immettono nelle autostrade.

👉 quindi: la gerarchia serve a far sì che milioni di utenti possano comunicare senza che la rete “collassi”.


---
# La rete telefonica: nascita, funzionamento e importanza storica

Subito dopo il telegrafo, l’invenzione del **telefono** segnò la nascita della seconda grande rete di telecomunicazioni del XIX secolo. A differenza del telegrafo, che trasmetteva impulsi elettrici brevi e discreti, la voce è un segnale **più complesso e “pesante”**, fatto di onde sonore continue che devono essere riprodotte in maniera fedele. Per trasportare la voce serviva dunque inviare un’**onda elettromagnetica modulata**, capace di attraversare il canale di comunicazione e di essere poi riconvertita nel suono originale all’arrivo.

Per riuscire in questo, fu introdotto il concetto di **modulazione**: in parole semplici, si prende un segnale di voce (a bassa frequenza) e lo si “appoggia” sopra un’onda portante di frequenza più alta, che è più adatta a viaggiare lungo i cavi o nell’etere. 

## DIGRESSIONE: L'ETERE:
### L’etere nella storia delle telecomunicazioni

Quando senti parlare di **etere** nelle vecchie lezioni o nei testi di inizio ’900, **non è un materiale fisico** che esiste davvero: era una **ipotesi scientifica** dell’Ottocento.

Gli studiosi dell’epoca si chiedevano:

- le onde sonore hanno bisogno dell’aria per propagarsi,
    
- le onde marine hanno bisogno dell’acqua,
    
- quindi… le onde elettromagnetiche (luce, radio) avranno bisogno di un mezzo invisibile!
    

Questo mezzo immaginario fu chiamato **“etere luminifero”**, pensato come un fluido sottilissimo, presente ovunque nell’universo, dentro cui si propagavano le onde luminose e radio.

---

### Perché se ne parlava tanto

- Nel linguaggio comune, ancora oggi “lanciare un segnale nell’etere” significa “mandarlo in aria senza fili”.
    
- Nell’800 e nei primi del ’900, si credeva davvero che fosse un mezzo reale.
    

---

### La scoperta: l’etere non esiste

L’esperimento cruciale fu quello di **Michelson e Morley (1887)**: tentarono di misurare il “vento d’etere” causato dal movimento della Terra. Risultato: **niente etere**.  
Più tardi, con **Einstein e la relatività ristretta (1905)**, fu chiaro: le onde elettromagnetiche **non hanno bisogno di un mezzo**. Possono propagarsi **anche nel vuoto**.

---

### Oggi

- **Fisica**: l’etere non esiste, è un concetto superato.
    
- **Linguaggio tecnico**: a volte si usa ancora per dire **“nell’aria”** o **“nello spazio libero”** → cioè il mezzo attraverso cui passano i segnali radio.
    

---

📌 In breve: l’etere era un **mezzo immaginario** ipotizzato per spiegare la propagazione della luce e delle onde radio; oggi sappiamo che i campi elettromagnetici si propagano **nel vuoto**, senza bisogno di supporto.

---

### TORNANDO ALLA MODULAZIONE
Questo trucco non solo permette di adattare il segnale al canale, ma apre la strada alla **moltiplicazione**: più conversazioni diverse possono viaggiare contemporaneamente sullo stesso mezzo, ognuna su una portante leggermente diversa. Nasce così la possibilità di far coesistere molti utenti in parallelo.

La rete telefonica ebbe un impatto epocale perché fu la **prima rete a entrare nelle case e negli uffici**, portando la comunicazione diretta a livello personale. Se il telegrafo restava limitato agli uffici postali e statali, la rete telefonica scendeva nella quotidianità delle persone. All’inizio, però, era necessaria la mediazione di un **centralino manuale**: l’utente sollevava la cornetta, un operatore rispondeva, ascoltava la richiesta e, fisicamente, inseriva un cavo per collegare il chiamante con il destinatario. Era un sistema affascinante ma laborioso e poco scalabile.

La svolta avvenne nel **1910** con la **commutazione automatica**: il selettore a disco. Per la prima volta non serviva più l’operatore: componendo il numero direttamente sul telefono, la rete creava da sola il collegamento con l’utente desiderato. Questo meccanismo permise di eliminare il centralino locale per le chiamate vicine, anche se per le comunicazioni a lunga distanza rimase per decenni il bisogno di un centralino intermedio. Solo nel **1970**, in Italia, fu introdotta la **teleselezione** su scala nazionale: la possibilità di comporre da casa, senza interventi manuali, qualsiasi numero nel Paese.

In sintesi, la rete telefonica portò tre innovazioni decisive: 

(1) l’uso della **modulazione** per adattare e moltiplicare i segnali sul canale, 

(2) la diffusione capillare della comunicazione diretta nelle abitazioni e negli uffici, e 

(3) la transizione dai **centralini manuali** alla **commutazione automatica**, che aprì la strada alle reti moderne. Fu il passaggio da un mondo in cui pochi operatori gestivano le connessioni, a un mondo in cui chiunque poteva comporre un numero e connettersi istantaneamente, senza barriere intermedie.

## LA COMMUTAZIONE
La **commutazione** è il **meccanismo con cui una rete mette in comunicazione due o più utenti**, decidendo come instradare i loro segnali attraverso l’infrastruttura.  
In pratica: è il modo in cui la rete **crea il collegamento logico** tra chi parla e chi ascolta.

---

## Nella rete telefonica

- All’inizio c’era il **centralino manuale**: l’operatore collegava fisicamente due fili → commutazione “umana”.
    
- Poi arrivò la **commutazione automatica a circuito**: componendo il numero, la rete creava un **circuito dedicato** tra i due telefoni. Durante la chiamata quel percorso rimaneva riservato.
    

---

## Tipi principali di commutazione

1. **Commutazione di circuito**
    
    - un collegamento dedicato da A a B (come nelle telefonate classiche).
        
    - vantaggi: voce in tempo reale, qualità stabile.
        
    - svantaggi: risorsa occupata anche se stai in silenzio.
        
2. **Commutazione di pacchetto**
    
    - il messaggio è spezzato in pacchetti; ciascuno viaggia da solo e la rete li ricompone all’arrivo (come avviene su Internet).
        
    - vantaggi: uso efficiente delle risorse, tanti utenti condividono lo stesso canale.
        
    - svantaggi: ritardi variabili (jitter), pacchetti persi.
        
3. (Storico) **Commutazione di messaggio**
    
    - intero messaggio memorizzato e ritrasmesso nodo per nodo.
        
    - oggi poco usata, ma antesignana del packet switching.
        

---

📌 **Takeaway accademico ma chiaro**:  
La commutazione è l’insieme delle **tecniche e dei dispositivi** che permettono a una rete di **collegare utenti tra loro**, decidendo _chi parla con chi e come_. Nel telefono era un circuito riservato; in Internet sono pacchetti condivisi.

---
# LE PRIME RETI SENZA FILI
### 1. Prima immagine – Riflessione ionosferica
![Pasted image 20250908213237.png](../../imgs/Pasted%20image%2020250908213237.png)
Vedi l’antenna a terra che manda un segnale verso l’alto, il segnale rimbalza sulla **ionosfera** (uno strato alto dell’atmosfera, carico di particelle ionizzate) e torna giù su una barca molto lontana.  
👉 Questo spiega **perché le onde radio riescono a coprire grandi distanze** senza cavi: sfruttano la riflessione sulla ionosfera per “saltare oltre l’orizzonte”.

---
### 2. Seconda immagine – Larghezza di banda
![Pasted image 20250908213329.png](../../imgs/Pasted%20image%2020250908213329.png)
![Pasted image 20250908213351.png](../../imgs/Pasted%20image%2020250908213351.png)
### 3. Terza immagine – Frequenza e potenza
![Pasted image 20250908213503.png](../../imgs/Pasted%20image%2020250908213503.png)
Sopra vedi due onde:

- **bassa frequenza** (pochi cicli al secondo → onde “lunghe”),
    
- **alta frequenza** (molti cicli → onde “corte”).
    

Sotto ci sono due tecnologie storiche:

- **Trasformatori a bassa potenza** → usati da Marconi con onde lunghe, ma limitavano la distanza e la quantità di dati.
    
- **Valvole termoioniche (triodi, anni ’20)** → permettono di **amplificare** i segnali e trasmettere a potenze maggiori, aprendo la strada alle alte frequenze e a comunicazioni più efficienti.  
    👉 Tradotto: il salto tecnologico dalle onde lunghe di Marconi alle onde corte degli anni ’20 fu reso possibile dall’elettronica.
    

---
Il **grande vantaggio delle reti senza fili** rispetto ai cavi è duplice:

1. Coprono **grandi distanze** con più facilità.
    
2. Possono raggiungere **ricevitori mobili** (navi, mezzi in movimento), impossibile con i cavi.
    

All’epoca di **Marconi** (fine ’800, inizio ’900) si usavano **onde lunghe** (bassa frequenza). Queste avevano due limiti seri:

- Richiedevano trasmettitori a **bassa potenza**, che potevano trasportare solo pochissima informazione (giusto qualche linea telegrafica, niente di più ricco).
    
- Non erano adatte a coprire grandi distanze, nonostante i tentativi di riflessione ionosferica.
    

Il salto di qualità avvenne negli **anni ’20** del ’900, quando furono introdotte le **valvole termoioniche** (il triodo). Grazie a queste, i segnali radio potevano essere **amplificati** e trasmessi a potenze molto più alte. Così divenne possibile usare anche **onde corte** (alta frequenza). Le onde corte, proprio per la loro natura, riuscivano a trasportare più informazione e a sfruttare meglio la riflessione ionosferica, permettendo comunicazioni su **distanze enormi** in modo molto più efficiente.

👉 Concetti fondamentali da portarsi dietro:

- **Larghezza di banda** = gamma di frequenze che un canale lascia passare. Misura in Hz.
    
- **Più banda = più informazione trasportabile** (capacità del canale).
    
- La **tecnologia elettronica (valvole)** ha reso possibile aumentare potenza e usare frequenze più alte → comunicazioni senza fili davvero su scala globale.
    

---

📌 **Mini-takeaway finale**  
Le prime reti senza fili erano rivoluzionarie perché **liberavano la comunicazione dal cavo**. Marconi dimostrò la possibilità, ma la vera svolta arrivò negli anni ’20 con l’elettronica, che rese le radio potenti, le onde corte utilizzabili e le comunicazioni a distanza molto più ricche ed efficienti.

---
# Dalla trasmissione analogica a quella digitale

## 1. “In chiaro”: cosa significa

Con **“segnale analogico in chiaro”**, si intende un segnale **non trasformato né criptato né modulato**, cioè preso così com’è dalla sorgente.

- Esempio: la voce che esce dal microfono come **variazione diretta di tensione elettrica**.
    
- È leggibile immediatamente, perché “assomiglia” al fenomeno originale (l’onda sonora → onda elettrica con la stessa forma).
    

“In chiaro” quindi = **non codificato, non modulato, non crittato**. È il segnale “nudo e crudo”, fedele alla sua forma naturale.  
👉 Problema: così è molto **vulnerabile a rumori, disturbi e attenuazioni**, e non viaggia bene su lunghe distanze o canali condivisi.

---

## 2. Perché servono nuove tecniche con il digitale

Con l’arrivo dei computer, le informazioni diventano **bit (0 e 1)**. Ma i canali fisici (cavi, onde radio) non possono trasportare direttamente sequenze di zeri e uno come se fossero “lampadine accese e spente”.
Bisogna **mappare i bit su un segnale fisico**, cioè **modulare** un’onda portante per trasmettere in modo robusto.  
👉 Ecco perché anche i segnali digitali hanno bisogno di **modulazioni**, proprio come quelli analogici IN CHIARO che andarono via via scomparendo.

---
# Da quale coppia di elementi parte la modulazione
## 1. Segnale **modulante**

- È il **contenuto da trasmettere**, l’informazione originale.
    
- Nel caso analogico: la voce di una telefonata, la musica di una radio, un’immagine televisiva.
    
- Ha di solito **frequenze basse** (es. voce: 300–3400 Hz).  
    👉 È quello che _modula_ la portante, cioè la “plasma” dandole la forma dei dati da inviare.
    

---

## 2. Segnale **portante**

- È un’onda sinusoidale **pura e ad alta frequenza**.
    
- Serve solo da “mezzo di trasporto” per far viaggiare lontano il segnale modulante.
    
- Non porta significato di per sé: è come un cavallo senza cavaliere.  
    👉 Quando lo combini con il modulante, diventa il “cavallo che trasporta il messaggio”.
    

---

## 3. Processo di **modulazione**

- Si “incide” il segnale modulante sulla portante, cambiando uno dei suoi parametri (ampiezza, frequenza, fase).
    
- Così l’informazione originale si **sposta** in una banda di frequenze più alta, dove il canale trasmissivo funziona meglio.

---
Distinguiamo i due tipi di modulazione:
## 1. Modulazioni analogiche
![Pasted image 20250908214215.png](../../imgs/Pasted%20image%2020250908214215.png)

- **Modulazione di ampiezza (AM)**: si varia l’altezza (ampiezza) dell’onda portante in base al segnale da trasmettere. È la radio AM classica.
    
- **Modulazione di frequenza (FM)**: si varia la frequenza istantanea della portante. È la radio FM che conosciamo tutti: più robusta ai disturbi.
    
- **Modulazione di fase (PM)**: si cambia la “fase” dell’onda (cioè dove si trova il suo ciclo) per seguire il segnale. Meno intuitiva da vedere, ma utile e base per modulazioni più moderne.
    

👉 In tutti i casi, il segnale originario (voce, musica) viene “inciso” su una portante ad alta frequenza per poter viaggiare lontano e insieme ad altri.

---

## 2. Modulazioni digitali
![Pasted image 20250908214531.png](../../imgs/Pasted%20image%2020250908214531.png)
Qui i bit (0 e 1) controllano parametri della portante:

- **ASK (Amplitude Shift Keying)**:  
    “Shift” = cambio. Si cambia l’ampiezza dell’onda: ampiezza alta = 1, ampiezza bassa = 0.  
    (È simile alla AM, ma usata a due livelli per i bit.)
    
- **FSK (Frequency Shift Keying)**:  
    Si usano due frequenze diverse: una rappresenta lo 0, l’altra l’1.  
    (È robusta e intuitiva, usata ad esempio nei vecchi modem a 56k.)
    
- **PSK (Phase Shift Keying)**:  
    Si cambia la fase della portante per distinguere 0 e 1 (es. 0° = “0”, 180° = “1”).  
    (È la base di tecniche più complesse come QPSK, 16-QAM, ecc.)
    
- **DSB (Double Side Band)**:  
    È una forma di AM dove, quando moduli, compaiono due copie della banda del segnale (una sopra e una sotto la portante). Qui è citata come esempio di “variazione di banda”.
    

👉 La logica è sempre: i bit non si trasmettono “così come sono”, ma comandano **come cambiare** un parametro fisico di un’onda portante.

---

## 5. Takeaway accademico

- **Analogico in chiaro**: segnale continuo non trasformato, fedele alla sorgente ma fragile.
    
- **Digitale**: nasce l’esigenza di codificare 0 e 1 in onde fisiche → modulazioni digitali.
    
- **Modulazioni analogiche (AM/FM/PM)**: variano ampiezza, frequenza, fase.
    
- **Modulazioni digitali (ASK/FSK/PSK/DSB)**: gli stessi parametri, ma comandati da bit discreti.

---
# Dalle trasmissioni radio alle reti cellulari

Abbiamo visto come le **prime reti senza fili** abbiano liberato la comunicazione dal vincolo dei cavi, sfruttando le onde radio per trasmettere a lunga distanza e perfino raggiungere mezzi in movimento. Negli anni ’20, con l’elettronica e le valvole termoioniche, fu possibile aumentare la potenza e l’efficienza delle trasmissioni, aprendo la strada a un uso sempre più massiccio dell’etere per comunicare. Ma con la crescita della popolazione e delle esigenze di comunicazione, bisognava trovare un modello più scalabile: non bastava più avere una singola stazione che coprisse una grande area, serviva una struttura capace di servire **milioni di utenti contemporanei**. Qui nasce l’idea delle **reti cellulari**.

---

# Le Reti Cellulari
![Pasted image 20250908215306.png](../../imgs/Pasted%20image%2020250908215306.png)
![Pasted image 20250908215345.png](../../imgs/Pasted%20image%2020250908215345.png)
Le **reti di telefonia cellulare** appartengono alla grande famiglia delle reti **wireless** (“senza fili”), ma non sono semplici radio potenziate: sono strutture molto sofisticate, che combinano diverse tecnologie per garantire copertura, capacità e mobilità. La loro caratteristica fondamentale è l’organizzazione in **celle**: porzioni di territorio, ciascuna servita da una **stazione radio base**.

Ogni cella funziona come una piccola “isola” di copertura, dove la stazione radio dialoga con i telefoni degli utenti che si trovano al suo interno. Più celle vicine formano una rete continua, capace di coprire intere città, regioni o Paesi. Da qui deriva il nome “rete cellulare”: la copertura è fatta a mosaico, proprio come un alveare di celle esagonali.

---

## Le novità introdotte dalla cellular/mobile network

L’idea delle celle porta a due vantaggi fondamentali:

1. **Potenza ridotta**  
    Ogni stazione radio deve coprire solo un’area limitata, quindi basta una potenza relativamente bassa. Questo rende l’infrastruttura più efficiente e riduce le interferenze con altre stazioni lontane.
    
2. **Riutilizzo delle frequenze**  
    Le celle adiacenti usano **canali diversi** per evitare interferenze, ma le stesse frequenze possono essere riutilizzate in celle più distanti. Così, con uno spettro limitato, è possibile servire moltissimi utenti. È come organizzare una città: due strade vicine non possono avere lo stesso nome, ma in quartieri lontani sì.
    

Il funzionamento della rete cellulare si basa inoltre su un meccanismo dinamico: quando ci muoviamo, il telefono **dialoga continuamente** con le stazioni vicine per capire quale offre il segnale migliore. Quando lasciamo una cella ed entriamo in un’altra, avviene il cosiddetto **handover**: il telefono riceve l’ordine di cambiare frequenza e agganciarsi alla nuova stazione senza che la conversazione venga interrotta. Questo è ciò che rende possibile, per esempio, **parlare al telefono in macchina o in treno senza cadute di linea**.

Dal punto di vista tecnologico, le reti cellulari hanno visto diverse generazioni:

- **1G**: voce analogica.
    
- **2G**: digitalizzazione della voce e primi SMS.
    
- **3G**: dati mobili a bassa velocità (nascita di Internet mobile).
    
- **4G**: alta velocità, streaming, applicazioni multimediali.
    
- **5G**: altissima capacità, bassissima latenza, miliardi di dispositivi connessi (non solo persone, ma anche sensori e macchine → Internet of Things).
    

---

# Conclusione e collegamento

Le reti cellulari hanno reso la comunicazione **personale, mobile e ubiqua**, trasformando il telefono in un compagno costante della vita quotidiana. Tuttavia, la loro potenza e la loro utilità sarebbero limitate se non fossero integrate con un’altra tecnologia capace di andare **ancora oltre**, là dove neanche le celle possono arrivare: i **satelliti**.

---
# Perché i satelliti hanno potenziato le telecomunicazioni

Un **satellite per telecomunicazioni** è, in pratica, una **stazione ripetitrice** messa in orbita: ascolta un segnale che sale da terra (**uplink**), lo **rilancia** verso un’altra zona della Terra (**downlink**).  
Risultato: colleghi **punti lontanissimi** senza stendere cavi, superi oceani e deserti, raggiungi **luoghi remoti o in movimento** (navi, aerei, spedizioni).

> **Telstar (1962)**: primo satellite di telecomunicazioni operativo. Nato per portare oltre oceano ciò che i cavi telefonici dell’epoca non reggevano bene (pochi canali, costi alti). Da lì parte l’era delle comunicazioni globali via satellite. Messo in orbita dalla società statunitense Bell Telephone nel 1962. Il suo scopo era sostituirsi ai cavi telefonici transatlantici, compito per cui le emissioni radio terrestri non erano adatte.

Per molti anni la rete internazionale via satellite è stata gestita quasi in **monopolio** da **Intelsat** (consorzio internazionale): lanciò diverse “generazioni” di satelliti e rese ordinario ciò che prima era straordinario (telefonate e TV intercontinentali). ( **International Telecommunications Satellite Organization** (**ITSO**))

---

# Come funziona, davvero (senza formule)

1. **Stazioni di terra** (teleport / earth station): grandi antenne paraboliche che puntano il satellite.
    
2. **Uplink → satellite → downlink**: il satellite riceve, **trasla di frequenza** e amplifica (transponder “bent-pipe”), poi ritrasmette verso l’area di servizio (**footprint**/beam).
    
3. **Bande di frequenza**: si usano porzioni dello spettro dedicate (tipicamente **C, Ku, Ka** per i collegamenti dati/TV; **L/S** per servizi speciali). Ogni banda ha pro/contro (es. **piogge** attenuano molto la Ka → “rain fade”).
    
4. **Capacità & potenza**: ogni transponder ha **banda** (MHz) e **potenza** limitate; la “taglia” del link si progetta con un **link budget** (quanto segnale utile ho rispetto al rumore).
    

---

# Le orbite (contano per copertura e ritardo)

![Pasted image 20250908211708.png](../../imgs/Pasted%20image%2020250908211708.png)
    
    - Pro: **copertura enorme** (tre satelliti coprono quasi tutto il globo), antenne fisse.
        
    - Contro: **latenza alta** (~250 ms solo andata; ~500–600 ms A/R). Ottimo per TV broadcast, meno per applicazioni sensibili al ritardo.
        
- **MEO** (altitudine intermedia): compromesso tra latenza e copertura (es. sistemi di navigazione).
    
- **LEO** (_bassa orbita_, ~500–1.200 km): costellazioni di tanti satelliti che “passano sopra” velocemente.
    
    - Pro: **latenza bassa** (decine di ms), buona per Internet interattivo; possibile **handover** satellite-satellite.
        
    - Contro: servono tante unità e una rete di **stazioni di terra**; gestione più complessa (Doppler, inseguimento).
        

---

# Cosa hanno abilitato (ieri e oggi)

- **Telefonia internazionale**: prima applicazione killer (Telstar, poi Intelsat).
    
- **TV via satellite**: telegiornali e grandi eventi in diretta mondiale; distribuzione broadcast efficiente.
    
    
- **Marittimo & aeronautico**: connettività a bordo.
![Pasted image 20250908213237.png](../../imgs/Pasted%20image%2020250908213237.png)
- **Emergenze e disaster recovery**: ripristini rapidi quando le infrastrutture a terra sono danneggiate.
    
- **Nuove reti LEO**: Internet a bassa latenza e **link inter-satellitari** (la rete “corre anche in cielo”).
    
> Nota sul cellulare: le **reti mobili** usano soprattutto collegamenti terrestri, ma i satelliti servono per **backhaul** tra centrali lontane, per collegare Paesi/continenti o per coprire zone senza infrastruttura. Oggi esistono anche servizi **direct-to-device** (standard 3GPP NTN): alcuni smartphone parlano _direttamente_ al satellite per messaggi/ SOS.

---
![Pasted image 20250908213329.png](../../imgs/Pasted%20image%2020250908213329.png)
![Pasted image 20250908213351.png](../../imgs/Pasted%20image%2020250908213351.png)

- **Latenza**: i GEO hanno ritardo elevato; la fibra è velocissima su dorsali terrestri/sottomarine.
    
![Pasted image 20250908213503.png](../../imgs/Pasted%20image%2020250908213503.png)
    
- **Costo per bit**: di norma più favorevole in fibra quando la posa è possibile.
    
👉 Quindi: **integrazione** più che sostituzione. Fibra per **tanta banda e bassa latenza** dove si può; **satelliti** per **coprire ovunque**, per la **mobilità globale** e per la **resilienza**.
---
## GSM – definizione in breve

**GSM** significa **Global System for Mobile Communications** (in origine _Groupe Spécial Mobile_).  


## Cosa ha introdotto di nuovo
1. **Digitalizzazione della voce** → niente più trasmissione analogica: la voce viene campionata, compressa e trasmessa come dati digitali.
    
2. **SIM card** → la scheda con il numero utente, che permette di essere riconosciuti sulla rete.
![Pasted image 20250908215306.png](../../imgs/Pasted%20image%2020250908215306.png)
![Pasted image 20250908215345.png](../../imgs/Pasted%20image%2020250908215345.png)
    
4. **SMS** → i famosi messaggi di testo, nati proprio con il GSM.
    
    

![Pasted image 20250908221330.png](../../imgs/Pasted%20image%2020250908221330.png)


Il GSM è stato il **primo vero sistema globale**, adottato praticamente ovunque nel mondo (tranne alcune eccezioni iniziali negli USA). Ha permesso di avere:
![Pasted image 20250908221356.png](../../imgs/Pasted%20image%2020250908221356.png)
- un **linguaggio comune** per i telefoni,
- tariffe più accessibili,
    
![Pasted image 20250908221415.png](../../imgs/Pasted%20image%2020250908221415.png)


- **In origine** il **GSM** è nato come **rete terrestre**: celle, antenne, commutatori. Non usava satelliti per servire gli utenti direttamente.
![Pasted image 20250908221454.png](../../imgs/Pasted%20image%2020250908221454.png)
- **Ma**: per collegare tra loro reti **in Paesi diversi o su continenti diversi**, serviva un’infrastruttura di trasporto (backhaul). Qui entrano in gioco i **satelliti**: non per parlare direttamente con il telefono, ma per collegare **tra loro i core network** degli operatori mobili, soprattutto dove non c’erano fibre sottomarine o dorsali terrestri.

👉 Quindi: il prof ha ragione se intende dire che il **sistema GSM sfrutta i satelliti per interconnettere le reti mobili** di Paesi diversi.  
![Pasted image 20250909154041.png](../../imgs/Pasted%20image%2020250909154041.png#5)
## Situazione oggi
- Le **reti mobili terrestri** usano soprattutto **fibra ottica e dorsali terrestri** per collegarsi tra loro, ma i satelliti restano importanti come **backup** o in aree remote.
    
![Pasted image 20250909152848.png](../../imgs/Pasted%20image%2020250909152848.png)


---
![Pasted image 20250909162321.png](../../imgs/Pasted%20image%2020250909162321.png)
# Mini-sunto “da esame”
- I **satelliti** hanno reso **globale** la comunicazione: uplink → transponder → downlink.
    
![Pasted image 20250909162509.png](../../imgs/Pasted%20image%2020250909162509.png)
    
    
- Oggi: TV/broadcast, backhaul e ridondanza, navi/aerei, emergenze; con le costellazioni LEO anche **Internet interattivo** a bassa latenza.
![Pasted image 20250909164925.png](../../imgs/Pasted%20image%2020250909164925.png)
- Non sostituiscono la **fibra**: la **completano**.
---
# IL COLPO DI GENIO: LA FIBRA
![Pasted image 20250909171136.png](../../imgs/Pasted%20image%2020250909171136.png)


# Com’è fatta una fibra ottica
![Pasted image 20250909172601.png](../../imgs/Pasted%20image%2020250909172601.png)
- **Core (nucleo)**: sottile filo di vetro super-puro che **trasporta la luce**.
- **Cladding (rivestimento)**: altro vetro con **indice di rifrazione leggermente minore** del core.
    
![Pasted image 20250909173516.png](../../imgs/Pasted%20image%2020250909173516.png)
    

> L’accoppiata _core > cladding_ (indice più alto nel core) è la chiave fisica del “miracolo”.
![Pasted image 20250909173545.png](../../imgs/Pasted%20image%2020250909173545.png)
---

# Principio fisico (disegno “zig-zag”: riflessione interna totale)

Quando un raggio di luce passa da un mezzo **più “denso” otticamente** (core) a uno **meno denso** (cladding), se l’angolo è abbastanza obliquo **non esce** ma **rimbalza**: è la **Riflessione Interna Totale (TIR)**. 
![Pasted image 20250908221356.png](../../imgs/Pasted%20image%2020250908221356.png)
Così la luce resta “intrappolata” e avanza a zig-zag lungo il core con perdite minime. È come una pallina che rimbalza in un tubo lucidissimo.
![Pasted image 20250908221415.png](../../imgs/Pasted%20image%2020250908221415.png)
---

# Come si porta _l’informazione_ sulla luce

1. **Trasmettitore**: un **LED** (distanze brevi) o un **laser** (distanze lunghe, bit-rate alto) genera luce.
    
2. **Modulazione**: codifichiamo i dati in **variazioni** della luce. Le basi:
    
    - **On/Off (intensity modulation)**: luce “più/meno” intensa per 1/0 (O-OK).
        
    - Per collegamenti spinti si usano anche variazioni di **fase/frequenza/ampiezza** della portante ottica (formati coerenti).
        
3. **Propagazione**: la luce corre nella fibra; lungo strada possiamo usare **amplificatori ottici** (es. EDFA) o rigeneratori.
    
4. **Ricevitore**: un **fotodiodo** (PIN/APD) trasforma la luce in corrente; l’elettronica ricostruisce i bit.
    

---

# Perché la fibra è una rivoluzione (slide: “banda di trasmissione”)

- **Banda enorme**: la luce vive a frequenze dell’**infrarosso-ottico** (centinaia di THz) → spazio per **tanti** canali e bit-rate altissimi.
    
- **Bassa attenuazione**: perdite minime (tipico ~**0,2 dB/km** intorno a 1550 nm).
    
- **Immunità ai disturbi EM**: niente interferenze radio/elettriche.
    
- **Sicurezza fisica**: intercettarla senza lasciare tracce è difficile.
    
- **Peso/ingombro**: cavi sottili, molte “vie” nello stesso cavo.
    

**Conseguenza pratica** (come dice la slide): sostituisce il rame nelle reti telefoniche e diventa la **spina dorsale di Internet**: pagine ricche, video in diretta, cloud, download giganti → tutto sorretto dai cavi ottici terrestri e **sottomarini**.

---

# Tipi di fibra (due righe utili d’esame)

- **Multimodale (MMF)**: core grande (50/62,5 µm), facile da accoppiare, **distanze brevi** (LAN, data center). Limite: **dispersione modale** (i “raggi” fanno percorsi diversi e si allargano nel tempo).
    
- **Monomodale (SMF)**: core ~9 µm, un solo modo di propagazione → **distanze lunghe e capacità altissime** (backbone, dorsali).
    

**Finestre operative** tipiche: ~**850 nm** (MMF), **1310 nm** e **1550 nm** (SMF), dove perdite e dispersione sono favorevoli.

---

# Cosa limita davvero una tratta ottica (per completezza chiara)

- **Attenuazione**: assorbimento/scattering del vetro, pieghe strette (micro/macro-bending).
    
- **Dispersione**: gli impulsi si allargano (cromatica/modale) → si “pestano i piedi” a bit-rate alti.
    
- **Allineamenti**: giunti/connettori devono essere perfetti (il core è microscopico).
    

Soluzioni: **amplificatori ottici**, **rigenerazioni O/E/O** dove serve, **compensazione di dispersione**, cavi e posa curati.

---

# Come si moltiplica la capacità (accenno fondamentale)

**WDM – Wavelength Division Multiplexing**: metti **più “colori”** (lunghezze d’onda) diversi sulla stessa fibra, ognuno è un canale indipendente.

- **CWDM**: pochi canali, spaziati larghi.
    
- **DWDM**: tanti canali, spaziati stretti → ciascuno anche a 100/200/400 Gb/s → capacità complessive **mostruose** per dorsali e cavi sottomarini.
    

---

# Tavola spettri

Le radio usano bande da **kHz a GHz** (ELF…EHF). Le **“IR-ottiche”** stanno **centinaia di THz**: ecco perché la fibra offre **larghezze di banda** e velocità **di ordini di grandezza superiori** rispetto a rame e radio.
![Pasted image 20250908221454.png](../../imgs/Pasted%20image%2020250908221454.png)

---
Yes esatto, i **THz** (terahertz) entrano in gioco quando parliamo della **luce nelle fibre ottiche**.

---

### 1. Frequenze radio vs ottiche

- Le **onde radio** che hai visto finora (ELF, HF, VHF, UHF, ecc.) arrivano fino a **centinaia di GHz**.
    
- Ma la **luce visibile e infrarossa** ha frequenze molto più alte: **centinaia di migliaia di GHz**, cioè **centinaia di terahertz (THz)**.
    

Ad esempio:

- luce rossa (λ ≈ 700 nm) → f ≈ 430 THz
    
- luce verde (λ ≈ 550 nm) → f ≈ 540 THz
    
- luce blu (λ ≈ 450 nm) → f ≈ 670 THz
    

Le fibre ottiche non lavorano proprio nel visibile ma nell’**infrarosso vicino** (tipicamente 850 nm, 1310 nm, 1550 nm) → quindi siamo in un range di **200–400 THz**.

---

### 2. Perché è una rivoluzione

Usare frequenze così alte significa avere una **larghezza di banda enorme** (ricordi: più è larga la banda, più info per unità di tempo posso trasmettere).  
Per questo la fibra può reggere:

- centinaia di **Gb/s per canale**
    
- e con WDM (multiplexing di lunghezze d’onda diverse) → **Tb/s per fibra**
    

---

📌 **Sintesi chiara**:  
Le frequenze delle fibre ottiche stanno nell’ordine dei **centinaia di THz**: milioni di volte più alte delle frequenze radio. Questo spiega perché la fibra ottica ha rivoluzionato le telecomunicazioni, offrendo capacità di trasmissione senza paragoni!

# Sunto “da 30 e lode”

La fibra ottica è un **guida-luce**: grazie alla **riflessione interna totale** confina la luce in un core di vetro. Variando la luce (intensità/fase/frequenza) codifichiamo i dati; con **laser, fotodiodi e WDM** portiamo **tantissima informazione** con **poche perdite** e **poca latenza** su **grandi distanze**. Per questo ha sostituito il rame nelle dorsali e reso possibile l’Internet che usiamo ogni giorno.