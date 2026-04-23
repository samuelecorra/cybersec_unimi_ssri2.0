# 🔹 Definizione

Un **protocollo di comunicazione** è un insieme di **regole, procedure e formati** che due (o più) entità devono seguire per potersi scambiare dati in maniera corretta e interpretabile.  
Senza protocollo → i dati arriverebbero come rumore incomprensibile.  
Con protocollo → i dati diventano un dialogo strutturato.

📌 Metafora: è come una lingua comune tra persone di paesi diversi. Non basta avere corde vocali (livello fisico), serve sapere il **vocabolario, la grammatica e l’ordine** delle parole (protocollo).

---

## Proprietà essenziali di un protocollo

- **Sintassi** → il formato dei dati (quali campi, quanti bit, in che ordine).
    
- **Semantica** → il significato dei campi (es. `ACK = conferma`, `FIN = fine connessione`).
    
- **Temporizzazione** → i tempi e le modalità di invio (quando iniziare, per quanto durare, come gestire le pause).
    

---

## Tipologie principali di protocolli

### 1. Protocolli seriali punto-punto

![Pasted image 20250909223240.png](../../imgs/Pasted%20image%2020250909223240.png)

- Due soli dispositivi collegati in linea diretta.
    
- Esempi: **RS-232**, collegamento semplice e tradizionale PC ↔ modem.
    
- Vantaggi: semplicità, costo ridotto.
    
- Limiti: distanza limitata, nessuna possibilità di condividere la linea con altri nodi.
    

---

### 2. Protocolli seriali multi-punto (bus condiviso)

![Pasted image 20250909223217.png](../../imgs/Pasted%20image%2020250909223217.png)

- Più dispositivi possono condividere lo stesso collegamento fisico.
    
- Ogni dispositivo deve avere un **indirizzo** o un identificativo univoco.
    
- Esempi: **RS-485**, **CAN Bus** (usato in automobili e sistemi industriali).
    
- Vantaggi: scalabilità, minori cavi.
    
- Limiti: rischio di collisioni, serve un meccanismo di arbitraggio (chi parla quando).
    

---

### 3. Protocolli di rete

![Pasted image 20250909223227.png](../../imgs/Pasted%20image%2020250909223227.png)

- Consentono comunicazione tra dispositivi non direttamente collegati → possono attraversare router, switch, dorsali.
    
- Sono multilivello (si basano su stack protocollari come OSI o TCP/IP).
    
- Esempi: **Ethernet, Wi-Fi, IP, TCP/UDP**.
    
- Vantaggi: permettono Internet e reti distribuite globali.
    
- Limiti: maggiore complessità, bisogno di coordinare più livelli.
    

---

## Idea chiave

Un protocollo è quindi un **contratto condiviso** tra mittente e destinatario.  
Se anche solo uno dei tre elementi (sintassi, semantica, temporizzazione) non coincide → la comunicazione fallisce.

---

# 🔹 Standard elettrici seriali

## Idea generale

Un **protocollo seriale elettrico** specifica:

- **Come** rappresentare gli 0 e 1 in termini di **tensione o segnali**.
    
- **Quante linee** usare (singolo filo, doppino differenziale, ecc.).
    
- **Quale distanza massima** coprire senza degradazione.
    
- **A che velocità** trasmettere i dati (baud rate).
    
- **Quanti dispositivi** possono stare sullo stesso bus.
    

Sono regole “fisiche” di collegamento, che stanno tra il livello **Fisico (1)** e il **Data Link (2)**.

---

## Gli standard più comuni

### 1. **RS-232** (Recommended Standard 232)

- Connessione **punto-punto** (un PC ↔ un modem).
    
- Segnale **non bilanciato** (riferito a massa).
    
- Livelli di tensione:
    
    - “1” logico = da **–15V a –3V**,
        
    - “0” logico = da **+3V a +15V**.
        
- Distanza max: circa **15 metri**.
    
- Velocità tipica: fino a **115 kbps**.
    
- Applicazioni: collegamenti storici PC ↔ periferiche (stampanti, modem).
    

---

### 2. **RS-422**

- Connessione **differenziale** (doppino intrecciato).
    
- Supporta **1 trasmettitore + max 10 ricevitori**.
    
- Distanza max: fino a **1200 metri**.
    
- Velocità: fino a **10 Mbps** su distanze ridotte.
    
- Applicazioni: sistemi industriali, controllo a lunga distanza.
    

---

### 3. **RS-485**

- Evoluzione del 422, sempre **differenziale**.
    
- Supporta **fino a 32 trasmettitori e 32 ricevitori** → bus multi-punto.
    
- Distanza max: circa **1200 metri**.
    
- Velocità: fino a **10 Mbps** (su distanze brevi).
    
- Applicazioni: automazione, building automation, bus industriali (es. Modbus).
    

---

### 4. **USB (Universal Serial Bus)**

- Standard universale moderno.
    
- Connessione **host ↔ device** (es. PC ↔ chiavetta).
    
- Alimentazione + dati nello stesso cavo.
    
- Velocità:
    
    - USB 1.1 → 12 Mbps,
        
    - USB 2.0 → 480 Mbps,
        
    - USB 3.x → 5/10/20 Gbps.
        
- Distanza: tipicamente **5 metri** senza hub attivi.
    
- Applicazioni: periferiche generiche (tastiere, dischi, webcam).
    

---

### 5. **RJ-45 (Registered Jack 45)** → Ethernet

- Connettore tipico delle reti LAN.
    
- Usa 4 o 8 fili a coppie intrecciate (cavo UTP o STP).
    
- Velocità:
    
    - Fast Ethernet → 100 Mbps,
        
    - Gigabit Ethernet → 1 Gbps,
        
    - 10GBASE-T → 10 Gbps.
        
- Distanza max: **100 metri** per Cat5e/6.
    
- Applicazioni: LAN, connessioni di rete cablate.
    

---

## Tabella comparativa (riassunto)

|Standard|Tipo connessione|Distanza max|Velocità max|Nodi supportati|Uso tipico|
|---|---|---|---|---|---|
|RS-232|Punto-punto, non bilanciato|15 m|115 kbps|2|PC ↔ modem/periferiche|
|RS-422|Differenziale|1200 m|10 Mbps|1 TX + 10 RX|Automazione, lungo raggio|
|RS-485|Differenziale multipunto|1200 m|10 Mbps|32 TX + 32 RX|Industrial bus, Modbus|
|USB|Host ↔ device|5 m|20 Gbps (USB 3.2)|127 (via hub)|Periferiche PC|
|RJ-45|Ethernet LAN|100 m|10 Gbps|Decine/migliaia|Reti locali|
![Pasted image 20250909223521.png](../../imgs/Pasted%20image%2020250909223521.png)
---

📌 **Sintesi**:  
Gli standard seriali definiscono la **grammatica elettrica** della comunicazione. RS-232 è il più semplice e storico, RS-422/485 si usano in contesti industriali, USB è lo standard moderno per periferiche, e RJ-45 è la base del networking cablato.


---

# 🔹 Cablaggi elettrici di tipo seriale

## 1. **RS-232 (Recommended Standard 232)**

![Pasted image 20250909223930.png](../../imgs/Pasted%20image%2020250909223930.png)

- **Primo standard seriale storico**, diffusissimo in PC e modem.
    
- Schema base:
    
    - **TX (Transmit Data)** → filo che porta i dati _trasmessi_ dal dispositivo.
        
    - **RX (Receive Data)** → filo che porta i dati _ricevuti_.
        
    - **GND (Ground)** → massa, cioè riferimento elettrico comune.
        
- Funzionamento: collegamento **punto-punto** → un trasmettitore parla a un ricevitore.
    
- Limiti:
    
    - Non puoi collegare più dispositivi.
        
    - Sensibile al rumore elettrico.
        
    - Velocità limitata (centinaia di kbit/s).
        
    - Distanza max ≈ 15 m.
        
- Punti di forza: standard universale, semplicissimo, diffusissimo fino agli anni 2000 (porte seriali COM sui PC).
    

---

## 2. **RS-422**

![Pasted image 20250909224001.png](../../imgs/Pasted%20image%2020250909224001.png)

- Evoluzione di RS-232.
    
- Usa **linee differenziali** (coppie di fili: es. **TX+ / TX–**, **RX+ / RX–**) → così il ricevitore legge la differenza di tensione, riducendo il rumore.
    
- Supporta il **multi-drop**: un trasmettitore può parlare con più ricevitori.
    
- Specifiche:
    
    - Fino a **10 Mbit/s** (10 milioni di bit/s).
        
    - Distanza max ≈ 1200 m.
        
    - Fino a 32 dispositivi collegati (espandibili con ripetitori).
        
- Applicazioni: sistemi industriali, controllo remoto, trasmissioni lunghe e robuste.
    
- Nota: poco comune nell’elettronica consumer → spesso servono adattatori RS-422 ↔ RS-232.
    

---

## 3. **RS-485**

![Pasted image 20250909224036.png](../../imgs/Pasted%20image%2020250909224036.png)

- Evoluzione ulteriore di RS-422, oggi **lo standard seriale industriale più diffuso**.
    
- Vantaggi rispetto a RS-422:
    
    - Fino a **256 dispositivi** sullo stesso bus.
        
    - Può supportare **più master** (più trasmettitori principali).
        
    - Ancora più robusto su lunghe distanze (≈ 1200 m).
        
- Modalità di cablaggio:
    
    - **2 fili (half-duplex)** → trasmissione alternata: o parli o ascolti, non insieme.
        
    - **4 fili (full-duplex)** → trasmissione e ricezione contemporanee.
        
- Concetti chiave:
    
    - **Master** = dispositivo che comanda la conversazione, invia ordini.
        
    - **Slave** = dispositivi che rispondono al master.
        
    - In RS-485 ci possono essere più master, ma **uno solo trasmette per volta**, altrimenti c’è collisione.
        
- Applicazioni: automazione industriale, domotica, reti di sensori (es. Modbus su RS-485).
    

---

## 4. **USB (Universal Serial Bus)**

![Pasted image 20250909224053.png](../../imgs/Pasted%20image%2020250909224053.png)

- Standard moderno e universale, ha sostituito quasi tutte le vecchie interfacce seriali.
    
- Trasmette dati **e** alimentazione nello stesso cavo.
    
- Cablaggio tipico USB 2.0:
    
    - **VCC** (+5 V) → alimentazione positiva.
        
    - **D+ (Data Positive)** → linea dati differenziale positiva.
        
    - **D– (Data Negative)** → linea dati differenziale negativa.
        
    - **GND (Ground)** → massa.
        
- Versioni:
    
    - **USB 1.0/1.1** → fino a 12 Mbit/s.
        
    - **USB 2.0** → 480 Mbit/s.
        
    - **USB 3.x** → da 5 fino a 20 Gbit/s.
        
- Tipi di connettori:
    
    - **Standard A/B**, **Mini A/B**, **Micro A/B**, **USB-C** (oggi universale, reversibile).
        
- Applicazioni: tutto → tastiere, mouse, dischi esterni, smartphone, videocamere.
    

---

## 5. **RJ-45 (Registered Jack type 45)**

![Pasted image 20250909224139.png](../../imgs/Pasted%20image%2020250909224139.png)

- Connettore fisico usato nei cavi Ethernet (LAN).
    
- È un connettore **8P8C** (8 Posizioni, 8 Contatti).
    
- Usa coppie di fili intrecciati (**twisted pair**) per ridurre interferenze e diafonia.
    
- Standard di cablaggio più diffusi:
    
    - **T568A** e **T568B** → differiscono nell’ordine dei colori delle coppie (scambio tra la coppia verde e arancione).
        
- Tipologie di cavi:
    
    - **Cavo dritto (straight-through)** → collega dispositivi diversi (PC ↔ switch, PC ↔ router).
        
    - **Cavo incrociato (crossover)** → collega dispositivi uguali (PC ↔ PC, switch ↔ switch).
        
- Funzioni dei pin:
    
    - **TX+ / TX– (Transmit Positive/Negative)** → coppia usata dal mittente per inviare dati.
        
    - **RX+ / RX– (Receive Positive/Negative)** → coppia usata dal ricevente per ricevere dati.
        
    - Pin opzionali → alimentazione (Power over Ethernet – PoE).
        
- Prestazioni:
    
    - **Cat 5e** → fino a 1 Gbps,
        
    - **Cat 6/6a** → fino a 10 Gbps,
        
    - **Cat 7/8** → fino a 40/100 Gbps (su distanze brevi).
        

---

## 6. Piedinatura RS-422/485 (connettore DB9)

![Pasted image 20250909224150.png](../../imgs/Pasted%20image%2020250909224150.png)
- Tipico connettore **a 9 pin (DB9)**.
    
- Pin principali:
    
    - **TXD (Transmit Data)** → linea dati in uscita.
        
    - **RXD (Receive Data)** → linea dati in ingresso.
        
    - **RTS (Request To Send)** → richiesta di invio (controllo di flusso hardware).
        
    - **CTS (Clear To Send)** → autorizzazione a inviare (risposta).
        
    - **GND (Ground)** → massa comune.
        

---

📌 **Sintesi finale (da 30 e lode)**

- **RS-232** → semplice, punto-punto, corto raggio, obsoleto ma storico.
    
- **RS-422** → differenziale, lungo raggio, multi-drop.
    
- **RS-485** → super industriale, fino a 256 nodi, half/full-duplex, multipli master.
    
- **USB** → standard universale moderno, dati + alimentazione.
    
- **RJ-45** → base delle reti Ethernet, 8 fili a coppie intrecciate, supporta dritto/incrociato e PoE.


---

# 🔹 Cablaggi in fibra ottica

## Modalità di funzionamento

### 1. **Simplex**

![Pasted image 20250909224449.png](../../imgs/Pasted%20image%2020250909224449.png)

- Comunicazione **monodirezionale**.
    
- Un canale trasmette e l’altro riceve, senza possibilità di inversione.
    
- Esempio: come una strada a senso unico.
    

---

### 2. **Half-Duplex**

![Pasted image 20250909224508.png](../../imgs/Pasted%20image%2020250909224508.png)

- Comunicazione **bidirezionale**, ma **non simultanea**.
    
- La stessa fibra viene usata sia per trasmettere che per ricevere, ma non nello stesso istante.
    
- Esempio: un walkie-talkie → quando uno parla, l’altro deve aspettare per rispondere.
    

---

### 3. **Full-Duplex**

![Pasted image 20250909224525.png](../../imgs/Pasted%20image%2020250909224525.png)

- Comunicazione **bidirezionale simultanea**.
    
- Normalmente richiede due fibre: una dedicata alla trasmissione, l’altra alla ricezione.
    
- Esempio: una strada a doppio senso di marcia, dove entrambe le direzioni sono attive.
    

---

### 4. **WDM – Wavelength Division Multiplexing**

![Pasted image 20250909224534.png](../../imgs/Pasted%20image%2020250909224534.png)

- **Multiplexing a divisione di lunghezza d’onda**: più canali viaggiano sulla **stessa fibra**, usando colori diversi della luce (lunghezze d’onda differenti).
    
- Esempio classico:
    
    - TX = trasmissione a **1310 nm**,
        
    - RX = ricezione a **1550 nm**.
        
- Permette trasmissione bidirezionale su una singola fibra.
    
- È come avere un’autostrada con corsie invisibili differenziate dal colore.
    

---

## Tipologie di fibra ottica


### 1. **Fibra multimodale (OM – Optical Multimode)**

![Pasted image 20250909224633.png](../../imgs/Pasted%20image%2020250909224633.png)

- Il nucleo ha un **diametro grande** (50 o 62,5 µm).
    
- Permette il passaggio di **più modalità di luce** (diversi percorsi e angoli di propagazione).
- 
    ![Pasted image 20250909224647.png](../../imgs/Pasted%20image%2020250909224647.png)
- Varianti:
    
    - **OM1** = 62,5 µm,
        
    - **OM2, OM3, OM4, OM5** = 50 µm.
        
- Vantaggi: consente più canali contemporanei → utile in reti locali.
    
- Svantaggi: i raggi viaggiano su percorsi diversi → questo causa **dispersione modale**, che limita la distanza massima.
    
- Uso tipico: **ambienti chiusi** (scuole, uffici, data center).
    

---

### 2. **Fibra monomodale (OS – Optical Singlemode)**

![Pasted image 20250909224700.png](../../imgs/Pasted%20image%2020250909224700.png)

- Il nucleo è **molto piccolo** (8–10 µm).
    
- Permette il passaggio di una sola modalità (un unico raggio dritto).

![Pasted image 20250909224711.png](../../imgs/Pasted%20image%2020250909224711.png)
    
- Varianti:
    
    - **OS1** → applicazioni indoor (edifici, data center).
        
    - **OS2** → applicazioni outdoor (reti metropolitane, dorsali).
        
- Vantaggi: nessuna dispersione modale, qualità e distanza enormi.
    
- Prestazioni: può raggiungere e superare i **40 km** senza degradare il segnale.
    
- Uso tipico: **reti a lungo raggio** (telecomunicazioni, dorsali Internet).
    

---

## Differenze principali tra multimodale e monomodale

|Caratteristica|Multimodale (OM)|Monomodale (OS)|
|---|---|---|
|Diametro nucleo|50–62,5 µm|8–10 µm|
|Modalità di propagazione|Più percorsi (diversi angoli)|Un solo percorso|
|Dispersione|Presente (dispersione modale)|Assente|
|Distanza max|Pochi km (LAN, data center)|Decine di km (fino a 40+ km)|
|Costo apparati|Più economici|Più costosi|
|Uso tipico|Reti locali interne|Reti a lungo raggio, dorsali|

---

📌 **Sintesi da 30 e lode**  
La fibra ottica può funzionare in modalità **Simplex, Half-Duplex o Full-Duplex**. Con la tecnica **WDM** possiamo trasmettere su una singola fibra usando lunghezze d’onda diverse.  
Le fibre si dividono in:

- **Multimodali (OM)** → più raggi, costi ridotti, ottime per ambienti chiusi.
    
- **Monomodali (OS)** → un raggio solo, altissima qualità, indispensabili per distanze lunghe (fino a decine di km).


---

Ora vediamo in dettaglio la logica di funzionamento del protocollo Ethernet (protocollo di livello 2, Data Link).

Esso usa il meccanismo **CSMA/CD** — _Carrier Sense Multiple Access with Collision Detection_ 
(_ascolta il canale condiviso e, se libero, trasmetti; se avviene una collisione, rilevala e reagisci_). È un **protocollo non deterministico**: il tempo esatto entro cui riuscirai a trasmettere non è prevedibile a priori.

---

# Come funziona CSMA/CD (passo-passo “alla Feynman”)

## 0) Scenario

- **Canale condiviso** (bus o hub): più stazioni collegano la stessa “strada”.
    
- Obiettivo: far parlare uno alla volta senza un semaforo centrale.
    

## 1) **CS — Carrier Sense** (_listen before talking_)

- Ogni stazione **ascolta** il mezzo: “qualcuno sta parlando?”
    
    - Se sente traffico → **attende** (rimane in ascolto).
        
    - Se sente **silenzio** → passa allo step 2.
        

## 2) **MA — Multiple Access** (tutti possono iniziare)

- Poiché tutti ascoltano **lo stesso** silenzio, **due stazioni possono partire quasi insieme** (il segnale impiega tempo a propagarsi lungo il cavo).
    
- Questo può generare una **collisione**.
    

## 3) **CD — Collision Detection** (_listen while talking_)

- Mentre trasmette, la stazione **continua ad ascoltare** il canale e confronta ciò che sente con ciò che sta inviando.
    
- Se i livelli “non tornano” → **collisione** rilevata.
    

## 4) Reazione alla collisione

1. **Jamming**: la stazione interrompe il frame e invia una **breve sequenza di “jamming”** (un disturbo intenzionale) per assicurare che **tutti** percepiscano la collisione e scartino i bit ricevuti.
    
2. **Backoff casuale**: attende un **tempo pseudo-casuale** prima di riprovare (così le due stazioni che hanno colliso **non ripartono insieme**).
    
    - Algoritmo tipico: **binary exponential backoff**
        
        - dopo la 1ª collisione scegli un ritardo uniforme in 0,1 _slot_;
            
        - dopo la 2ª collisione in 0,3;
            
        - dopo la k-esima in 0, 2ᵏ−1 (fino a un massimo, poi **max 16 tentativi** ⇒ se falliscono, la trasmissione viene dichiarata **fallita**).
            
3. **Ritrasmissione**: la stazione ricomincia dal passo 1.
    

> 💡 **Slot time** (tempo di contesa) è il tempo di andata-ritorno del segnale sul dominio di collisione. Storicamente corrisponde a **512 bit-time**: serve per garantire che una collisione venga “vista” mentre stai ancora trasmettendo.

---

## Perché esiste la **dimensione minima del frame** (64 byte)

Per poter **rilevare** una collisione, chi trasmette deve **stare ancora trasmettendo** quando l’eco della collisione torna indietro.  
Se i frame fossero troppo corti, finiresti di inviare prima di “accorgerti” della collisione.  
Per questo Ethernet classico impone **min 64 byte** (header+payload+FCS).

---

## Due casi illustrati nella tua slide

### ✔️ Trasmissione **senza** collisione

1. Ascolto → silenzio.
    
2. Trasmetto.
    
3. L’altra parte riceve il **frame** integro.
    

![Pasted image 20250909225140.png](../../imgs/Pasted%20image%2020250909225140.png)
### ❌ Trasmissione **con** collisione

1. Due stazioni ascoltano → silenzio per entrambe.
    
2. Trasmettono quasi insieme → **collisione**.
    
3. Entrambe rilevano la collisione (**CD**), inviano **jamming**, **backoff** casuale, poi riprovano.
    

---

# “Non deterministico”: cosa significa davvero?

Il tempo d’attesa prima di riuscire a trasmettere **dipende dagli eventi casuali** (collisioni, scelte di backoff, traffico in corso).  
Non puoi **garantire** a priori “trasmetterò entro X ms”: per questo CSMA/CD è **non deterministico** (al contrario di bus industriali _deterministici_ con slot prenotati o token).

---

# Collision domain (dominio di collisione)

- Un **collision domain** è l’insieme di nodi che **condividono** lo stesso mezzo in **half-duplex** e quindi possono collidere.
    
- **Più nodi/traffico ⇒ più collisioni ⇒ meno efficienza**.
    
- **Come si riduce** il collision domain?
    
    - Con **switch** (commutatori): **ogni porta** diventa **un dominio di collisione separato**. In **full-duplex** sulle porte switch **le collisioni scompaiono** e CSMA/CD non serve più.
        
    - Con **VLAN** e segmentazioni topologiche: riduci la contesa tra gruppi di host.
        
    - Evita **hub** (ripetitori condivisi): **estendono** il collision domain, non lo dividono.
        

> Oggi la gran parte delle LAN usa **switch full-duplex**: niente collisioni, niente jamming, niente backoff. Il meccanismo CSMA/CD rimane fondamentale dal punto di vista **storico e concettuale** (e per capire perché certi vincoli — come i 64 byte minimi — esistono).

---

## Riassunto

- **CSMA/CD** = _Carrier Sense_ (ascolta), _Multiple Access_ (tutti possono parlare), _Collision Detection_ (rileva mentre parli).
    
- In caso di collisione: **jamming → backoff casuale (esponenziale) → ritenta (≤16 volte)**.
    
- **Min frame 64 B** per “vedere” eventuali collisioni.
    
- **Non deterministico** = non posso promettere un tempo massimo di trasmissione.
    
- **Collision domain** si riduce con **switch**; in **full-duplex** le collisioni non avvengono.


---

Perfetto 👌, siamo al cuore della lezione: il **funzionamento del protocollo TCP/IP** e in particolare la logica della **connessione TCP** con le sue **socket** e il celebre **three-way handshake**. Ti spiego tutto da cima a fondo con massima chiarezza, senza dare nulla per scontato.

---

# 🔹 La Socket TCP – il “cervello” del protocollo

- **Socket** = interfaccia software che rappresenta un’estremità di una connessione di rete.
    
- Ogni socket è identificata da una **coppia (IP, porta)**.
    
- Quando un client vuole comunicare con un server:
    
    - apre una socket locale,
        
    - la associa a un **IP destinatario + porta del server**,
        
    - e usa questa socket per inviare/ricevere dati.
        

👉 Le socket sono i **punti di contatto** tra applicazioni e rete: due applicazioni (es. browser ↔ server web) comunicano **solo perché le loro socket sono state accoppiate** tramite TCP.

---

# 🔹 Il Three-Way Handshake (3 fasi di avvio della connessione)

## Step 1 – SYN (synchronize)

- Il **client** vuole avviare una connessione.
    
- Invia al server un pacchetto con:
    
    - **SYN=1** (flag di sincronizzazione),
        
    - un **Sequence Number (Seq=X)** scelto casualmente (serve come numero iniziale per contare i byte).
        
- Scopo: dire “voglio connettermi e il mio contatore parte da X”.
    

---

## Step 2 – SYN-ACK (synchronize + acknowledgement)

- Il **server** riceve il SYN.
    
- Risponde con un pacchetto:
    
    - **SYN=1, ACK=1** (sincronizza e conferma),
        
    - il suo **Sequence Number (Seq=Y)**,
        
    - un **Acknowledgment Number (Ack=X+1)** che conferma di aver ricevuto il numero del client.
        
- Scopo: dire “ho ricevuto la tua richiesta, la accetto, ecco il mio contatore Y”.
    

---

## Step 3 – ACK (acknowledgement)

- Il **client** riceve il SYN-ACK.
    
- Risponde con un pacchetto:
    
    - **ACK=1**,
        
    - **Seq=X+1**,
        
    - **Ack=Y+1** (conferma di aver ricevuto il numero del server).
        
- A questo punto la connessione è **stabilita**.
    
- Il client può già allegare **dati utili** dentro questo pacchetto ACK.
    

---

# 🔹 Tabella riepilogativa (come nella tua slide)

|N°|Mittente|Destinatario|SYN|ACK|Seq. n.|Ack. n.|
|---|---|---|---|---|---|---|
|1|Client|Server|1|0|X|–|
|2|Server|Client|1|1|Y|X+1|
|3|Client|Server|0|1|X+1|Y+1|

---

# 🔹 Perché serve tutto questo?

- **Affidabilità**: evitare che vecchi pacchetti (ancora in circolazione) vengano scambiati per nuovi.
    
- **Sincronizzazione**: entrambi gli host si mettono d’accordo su numeri di sequenza e acknowledgment.
    
- **Sicurezza di base**: garantisce che entrambe le parti siano attive e pronte.
    

👉 Senza handshake, potresti inviare dati a un host che non è pronto, generando confusione.

---

# 🔹 Esempio concreto

- Apri un browser e scrivi `www.google.com`.
    
- Il tuo PC (client) apre una socket verso l’IP di Google sulla porta **80 (HTTP)** o **443 (HTTPS)**.
    
- Scatta il **three-way handshake** tra client e server.
    
- Solo dopo questa stretta di mano puoi inviare la richiesta `GET /index.html`.
    

---

# 🔹 Una curiosità tecnica (avanzata)

Il numero iniziale di sequenza (ISN – Initial Sequence Number) è scelto in modo **casuale e variabile nel tempo**, per prevenire attacchi di **session hijacking** (un attaccante che prova a indovinare i numeri per inserirsi nella comunicazione).

---

📌 **Sintesi da 30 e lode**

- TCP crea connessioni affidabili usando le **socket**.
    
- Il **three-way handshake (SYN → SYN-ACK → ACK)** sincronizza i contatori e conferma che entrambe le parti siano pronte.
    
- Dopo questa fase, la trasmissione dei dati può avvenire in piena affidabilità, con ordinamento e ritrasmissione automatica in caso di perdita.


---

Perfetto 🚀, concludiamo la lezione con i concetti di **trasmissioni deterministiche e real-time**, fondamentali quando si lavora con sistemi critici o processi fisici complessi.

---

# 🔹 Comunicazioni real-time

- Una comunicazione è **real-time** quando la trasmissione delle informazioni avviene:
    
    - **istantaneamente**, oppure
        
    - con una **latenza molto bassa e prevedibile**, fissata in anticipo.
        
- Latenza = tempo che intercorre tra l’invio di un dato e la sua ricezione.
    

📌 Esempi:

- Una videochiamata: non è istantanea, ma la latenza (100–200 ms) è **accettabile e costante**.
    
- I sistemi industriali o di automazione: devono reagire entro tempi ben precisi (es. spegnere un macchinario in 10 ms in caso di emergenza).
    

---

# 🔹 Determinismo

- Nei sistemi informatici e nelle comunicazioni si definisce **determinismo** la capacità di:
    
    - rispettare la relazione **causa-effetto** in ordine temporale,
        
    - garantendo che ad **ogni causa (input)** corrisponda un **effetto (output)** in tempi prevedibili.
        

📌 Significa che:

- Se il sistema riceve **Causa 1, Causa 2, Causa 3** nell’ordine temporale giusto, deve produrre **Effetto 1, Effetto 2, Effetto 3** nello stesso ordine e nei tempi stabiliti.
    

---

# 🔹 Non determinismo

- Quando il sistema **non garantisce** tempi certi né ordine perfetto tra causa ed effetto, si parla di **non determinismo**.
    
- Esempio: Ethernet classico con CSMA/CD → non sai **quando** il pacchetto verrà trasmesso, perché le collisioni introducono ritardi variabili e imprevedibili.
    

---

# 🔹 Differenza chiave

- **Deterministico** = posso prevedere _a priori_ il tempo massimo entro cui il sistema risponde.
    
- **Real-time** = il sistema risponde entro tempi che sono **abbastanza bassi e certi** da rispettare il vincolo operativo.
    

⚠️ Nota:

- Un sistema real-time **deve essere deterministico**.
    
- Un sistema deterministico **non è necessariamente real-time** se i tempi garantiti sono troppo lunghi per l’applicazione.
    

---

# 🔹 Esempi pratici

- **Real-time deterministico**:
    
    - Bus industriali (es. _CAN-bus_, _PROFIBUS_).
        
    - Sistemi avionici: sensori e attuatori devono rispondere entro tempi garantiti per motivi di sicurezza.
        
- **Non deterministico**:
    
    - Rete Ethernet tradizionale.
        
    - Internet in generale: la consegna è affidabile, ma non puoi garantire _quando_ esattamente un pacchetto arriverà.
        

---

# 📌 Sintesi finale

- **Comunicazione real-time**: scambio di informazioni con latenza trascurabile o definita.
    
- **Determinismo**: rispetto rigoroso del principio di causalità (causa→effetto) con tempi prevedibili.
    
- **Non determinismo**: impossibilità di prevedere tempi e ordine delle risposte.
    
- Questi concetti sono fondamentali nei sistemi **critici** (industria, medicina, trasporti, robotica), dove **ogni ritardo imprevedibile può tradursi in un rischio reale**.


---

# SUNTO LEZIONE 2:
# 📘 Lezione 2 – I protocolli di comunicazione

## 🔹 Concetto di protocollo

- **Protocollo** = insieme di regole (sintassi, semantica, temporizzazione) per comunicare correttamente.
    
- Tipologie:
    
    - **Seriali punto-punto** → due dispositivi (RS-232).
        
    - **Seriali multi-punto** → più dispositivi sullo stesso bus (RS-485).
        
    - **Di rete** → attraversano più nodi/router (Ethernet, IP, TCP/UDP).
        

---

## 🔹 Standard elettrici seriali

|Standard|Tipo|Distanza|Velocità|Nodi|Uso tipico|
|---|---|---|---|---|---|
|**RS-232**|Punto-punto, non bilanciato|15 m|115 kbps|2|PC ↔ modem, legacy|
|**RS-422**|Differenziale, multi-drop|1200 m|10 Mbps|1 TX + 10 RX|Controlli industriali|
|**RS-485**|Differenziale, multipunto|1200 m|10 Mbps|fino 256|Automazione, Modbus|
|**USB**|Host ↔ device|5 m|20 Gbps (USB 3.2)|fino 127 (hub)|Periferiche PC|
|**RJ-45 (Ethernet)**|LAN, twisted pair|100 m|10 Gbps+|centinaia|Reti locali|

---

## 🔹 Cablaggi seriali

- **RS-232** → TX, RX, GND.
    
- **RS-422/485** → linee differenziali (TX+/TX–, RX+/RX–).
    
- **RS-485** → half-duplex (2 fili) o full-duplex (4 fili), supporto multi-master.
    
- **USB** → Vcc, D+, D–, GND, connettori A/B/Mini/Micro/C.
    
- **RJ-45** → 8 pin (TX+/–, RX+/–, PoE). Schemi T568A/T568B.
    
- **Cavi dritti**: PC ↔ switch.
    
- **Cavi incrociati**: PC ↔ PC, switch ↔ switch.
    

---

## 🔹 Fibra ottica

- **Simplex** → trasmissione unidirezionale.
    
- **Half-duplex** → bidirezionale, ma alternata.
    
- **Full-duplex** → bidirezionale simultanea.
    
- **WDM (Wavelength Division Multiplexing)** → più lunghezze d’onda diverse sulla stessa fibra.
    

### Tipi di fibra

|Tipo|Diametro nucleo|Modalità|Distanza|Uso|
|---|---|---|---|---|
|**Multimodale (OM1–OM5)**|50–62,5 µm|più raggi (modi)|corto raggio (LAN, data center)|indoor|
|**Monomodale (OS1–OS2)**|8–10 µm|un solo raggio|lungo raggio (>40 km)|dorsali telecom|

---

## 🔹 Ethernet (Livello 2)

- Protocollo **CSMA/CD** (_Carrier Sense Multiple Access with Collision Detection_).
    
- Fasi:
    
    1. **Carrier sense** → ascolto del mezzo.
        
    2. **Multiple access** → più nodi possono iniziare insieme → rischio collisione.
        
    3. **Collision detection** → jamming + backoff casuale.
        
- **Non deterministico** → non garantisce tempi certi.
    
- **Collision domain**: insieme di nodi che condividono il mezzo; ridotto/eliminato da switch full-duplex.
    
- Frame minimo: **64 byte** (per rilevare collisioni).
    

---

## 🔹 TCP/IP (Livello 4)

- **Socket TCP** → cervello che gestisce la connessione (identificata da IP + porta).
    
- **Three-way handshake**:
    
    1. Client → Server: SYN, Seq=X.
        
    2. Server → Client: SYN+ACK, Seq=Y, Ack=X+1.
        
    3. Client → Server: ACK, Seq=X+1, Ack=Y+1 (+ eventuali dati).
        
- Scopo: sincronizzare numeri di sequenza, confermare la disponibilità, avviare la connessione.
    

---

## 🔹 Determinismo e real-time

- **Comunicazione real-time** → scambio con latenza trascurabile o predefinita.
    
- **Determinismo** → rispetto del principio di causalità (causa→effetto nell’ordine corretto e in tempi prevedibili).
    
- **Non determinismo** → tempi variabili/imprevedibili (es. Ethernet classico).
    
- **Esempi**:
    
    - Deterministico + real-time → bus industriali, avionica.
        
    - Non deterministico → Internet, Ethernet con collisioni.
        

---

📌 **Sintesi “da 30 e lode”**  
Questa lezione ha mostrato come il concetto astratto di **protocollo** si traduce in regole fisiche (RS, USB, RJ-45, fibra), in metodi di accesso condiviso (Ethernet), in protocolli di trasporto affidabili (TCP), e infine nei concetti di **determinismo** e **real-time** indispensabili per i sistemi critici.