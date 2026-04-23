
Nella Lezione 3 ti sei mosso “a livello componenti”: mezzi trasmissivi e dispositivi (hub, switch, router), domini di collisione e di broadcast, indirizzamento IP e primi cenni di segmentazione/subnetting. In altre parole, hai osservato **“che cosa”** compone una rete e **“come”** quei pezzi si comportano localmente (instradamento, commutazione, inoltro). La Lezione 4 sale di quota: non si limita ai singoli apparati, ma mette a fuoco **la forma complessiva del sistema** — sia dal punto di vista delle **funzionalità** che esso espone (la sua **architettura logica**), sia dal punto di vista delle **connessioni fisiche** tra i nodi (la sua **architettura fisica**, cioè la **topologia**). È il passaggio dall’“ingegneria dei mattoni” all’“ingegneria dell’edificio”: i mattoni restano fondamentali, ma ora contano la planimetria, le vie di fuga, i colli di bottiglia e i punti singoli di guasto. Questa prospettiva è cruciale in cybersecurity, perché **affidabilità, disponibilità e superficie d’attacco** dipendono in modo diretto da scelte architetturali: segmentazioni logiche, ruoli client/server o P2P, ridondanze e assenza di SPOF.

---

# Architetture logiche e fisiche

## Che cos’è un’architettura di rete (logica)

Per **architettura logica** intendiamo l’insieme delle **funzionalità** della rete e **delle relazioni** tra esse: quali servizi la rete offre (es. autenticazione, naming, risoluzione indirizzi, condivisione risorse, erogazione applicativa), **come** tali servizi sono **strutturati** (moduli/ruoli) e **interconnessi** (dipendenze, flussi). È una descrizione “software-centrica”: parla di **ruoli** (client, server, peer), **protocolli** e **politiche** (es. segmentazione logica, piani di controllo/dati, domini di fiducia). Non ti dice dove passano i cavi, ma **chi serve cosa a chi** e **attraverso quali interfacce**.

### Esempi rapidi e concreti

- **Client/Server**: i client **richiedono** servizi, i server **li erogano** (es. web, posta, directory).
    
- **Peer-to-Peer (P2P)**: ogni nodo può **domandare e offrire** servizi; spesso si usa per distribuzione e condivisione tra pari.
    
- **Ibride**: combinano i due modelli (es. controllo centralizzato per discovery/autenticazione + scambio dati P2P).  
    Queste scelte sono **logiche**: organizzano funzionalità e responsabilità, non i cablaggi.
    

## Che cos’è l’architettura fisica (topologia)

![Pasted image 20250910000725.png](../../imgs/Pasted%20image%2020250910000725.png)

Per **architettura fisica** si intende la **rappresentazione delle connessioni reali** fra i nodi: è la **topologia**. Qui il focus è “cable-centrico/radio-centrico”: **chi è collegato a chi** e **con quali percorsi** (stella, anello, albero, maglia, bus, lineare). La topologia **determina** percorsi alternativi, punti di concentrazione, costi di cablaggio e impatti dei guasti. In breve: è la “mappa elettrica/ottica/radio” della rete.

### Perché separare logica e fisica (ma farle dialogare)

- La **logica** ti dà **servizi, ruoli e flussi**; la **fisica** ti dà **percorsi, ridondanza e colli di bottiglia**.
    
- Progetti bene quando **mappi correttamente** i requisiti logici sulla topologia fisica: se scegli un modello client/server critico, **eviti SPOF** sul percorso verso i server; se abbracci P2P ad alto traffico laterale, **eviti stelle iper-concentrate** e valuti **maglia/anello** per non saturare i rami verso il centro.
    

## Errori tipici da evitare

1. **Confondere il “chi fa cosa” col “chi è collegato a chi”**: un P2P **non impone** una maglia fisica completa; un client/server **non impone** una stella: sono piani diversi.
    
2. **Progettare la logica ignorando i fallimenti fisici**: se centralizzi servizi senza **ridondanza fisica**, stai creando **SPOF**.
    
3. **Costruire topologie robuste senza allineare i flussi logici**: una maglia costosa non serve, se poi la logica convoglia tutto su un unico server centrale.  
    (Questi temi sfoceranno subito dopo in **SPOF** e **ridondanza**, cardini della chiusura di lezione.)
    

## Mini-checklist di progettazione (orientata allo studio)

- **Definisci la logica**: servizi, ruoli (client/server/P2P/ibrido), domini di fiducia.
    
- **Stima i flussi**: chi parla con chi, quanto spesso, con quali requisiti (latenza, banda, continuità).
    
- **Scegli la topologia** coerente: stella/anello/albero/maglia/bus in funzione di **costi, scalabilità, affidabilità**.
    
- **Riduci o elimina gli SPOF** e **introduci ridondanza** lungo i percorsi critici (tema del blocco successivo).


---
# Tipologie di Rete

![Pasted image 20250910000827.png](../../imgs/Pasted%20image%2020250910000827.png)

## 1. PAN e WPAN (Personal Area Network)

- **Definizione**: rete a raggio ridotto (pochi metri), tipicamente per collegare **periferiche** a un dispositivo principale (es. PC, smartphone).
    
- **Cablata (PAN)**: si basa su standard come **USB** o **FireWire**, pensata per collegamenti diretti e veloci.
    
- **Wireless (WPAN)**: si affida a tecnologie come **Bluetooth**, **Wireless USB**, **IrDA**, usata per accessori senza cavo.
    
- **Caratteristiche**:
    
    - Portata molto limitata → ideale per periferiche.
        
    - Scalabilità minima: pochi dispositivi.
        
    - Sicurezza: dipende dallo standard (Bluetooth include autenticazione e cifratura, ma resta più vulnerabile di collegamenti cablati).
        

---

## 2. LAN (Local Area Network)

- **Definizione**: rete privata che collega più dispositivi in un’area circoscritta (ufficio, scuola, edificio).
    
- **Standard tipico**: **Ethernet** (su rame o fibra ottica).
    
- **Caratteristiche**:
    
    - Elevata velocità (oggi si parla di Gigabit e 10 Gigabit Ethernet).
        
    - Raggio d’azione tipico: un edificio o un campus.
        
    - Possibile estensione tramite dispositivi attivi (switch, repeater).
        
    - La **fibra ottica** può estendere il raggio anche a chilometri, ma rimane circoscritta a un contesto privato.
        
- **Vantaggio**: costi contenuti, elevata velocità, gestione autonoma.
    
- **Limite**: la portata geografica, che richiede tecniche superiori (MAN, WAN) per connettere sedi distanti.
    

---

## 3. MAN (Metropolitan Area Network)

- **Definizione**: rete che collega più LAN situate in **aree urbane** o metropolitane.
    
- **Caratteristiche**:
    
    - Usa **fibra ottica** ad alta capacità, spesso con linee dedicate affittate dai provider.
        
    - Le prestazioni sono comparabili a quelle di una LAN, ma distribuite su scala cittadina.
        
    - Le MAN sono fornite da **operatori specializzati** e collegano filiali, enti, università.
        
- **Esempio pratico**: rete che connette tutti i campus di un’università dentro la stessa città.
    
- **Ruolo**: fungono da ponte naturale tra LAN locali e WAN globali.
    

---

## 4. WAN (Wide Area Network)

- **Definizione**: rete che copre aree **nazionali, continentali o globali**, collegando LAN e MAN sparse nel mondo.
    
- **Caratteristiche**:
    
    - Numero di dispositivi collegabili → **teoricamente illimitato**.
        
    - Tecnologie tipiche:
        
        - **IP/MPLS** (Multiprotocol Label Switching)
            
        - **PDH** (Plesiochronous Digital Hierarchy)
            
        - **SDH** (Synchronous Digital Hierarchy)
            
        - **SONET** (Synchronous Optical Network)
            
        - **ATM** (Asynchronous Transfer Mode)
            
    - Non si basano solo su Ethernet, ma su un mix di tecniche per garantire instradamento globale.
        
- **Aspetto gestionale**:
    
    - Le WAN sono per lo più **possedute da provider** o grandi organizzazioni.
        
    - Gli **ISP** (Internet Service Provider) sfruttano le WAN per connettere le reti private dei clienti a **Internet**.
        
- **Esempio pratico**: la stessa **Internet** è una WAN planetaria.
    

---

## Visione d’insieme

- **PAN/WPAN** → personale, pochi metri, periferiche.
    
- **LAN** → locale, edifici o campus, controllo privato.
    
- **MAN** → urbano/metropolitano, connettività tra sedi.
    
- **WAN** → globale, backbone per l’interconnessione.
    

L’evoluzione è **dal privato e circoscritto al pubblico e globale**: ogni tipologia superiore integra la precedente, ma introduce **nuove sfide di sicurezza e gestione** (autenticazione, segmentazione, scalabilità, ridondanza).


---
# Architetture logiche di rete

## Il significato di "architettura logica"

Finora abbiamo distinto i tipi di rete (PAN, LAN, MAN, WAN) in base all’estensione geografica e agli standard di collegamento. Ora spostiamo l’attenzione non più sul **“quanto”** si estende la rete, ma sul **“come”** i nodi organizzano le proprie relazioni funzionali.

Un’**architettura logica di rete** descrive quindi **il modello di interazione tra i nodi**:

- chi fornisce servizi,
    
- chi li richiede,
    
- e come viene gestito lo scambio dei dati.
    

Le due macro-famiglie sono **Client/Server** e **Peer-to-Peer (P2P)**. Spesso, però, le reti reali adottano modelli **ibridi** che mescolano i due paradigmi.

![Pasted image 20250910001205.png](../../imgs/Pasted%20image%2020250910001205.png)

---

## 1. Architettura Client/Server

- **Descrizione**: i nodi si dividono in due ruoli distinti:
    
    - **Client** → chiedono servizi.
        
    - **Server** → li erogano, centralizzando risorse e dati.
        
- **Esempio classico**:
    
    - Server web che ospita un sito.
        
    - Browser dei client che richiedono le pagine.
        
- **Caratteristiche**:
    
    - **Centralizzazione**: il server è il fulcro della rete.
        
    - **Gestione semplificata**: aggiornamenti e politiche applicative sono concentrate.
        
    - **Scalabilità limitata**: troppi client contemporanei possono saturare il server.
        
    - **Vulnerabilità**: se cade il server, cade il servizio per tutti → SPOF.
        
- **Uso tipico**: ambienti aziendali, applicazioni web, sistemi bancari.
    

---

## 2. Architettura Peer-to-Peer (P2P)

- **Descrizione**: tutti i nodi hanno lo stesso “peso logico”: possono sia richiedere che fornire servizi.
    
- **Esempio classico**: reti di file sharing (BitTorrent, eMule).
    
- **Caratteristiche**:
    
    - **Decentralizzazione**: non c’è un unico server centrale.
        
    - **Robustezza**: se un peer cade, gli altri continuano a comunicare.
        
    - **Scalabilità**: molto alta, la rete cresce aggiungendo nodi.
        
    - **Svantaggi**: maggiore complessità nella gestione (indirizzamento, ricerca risorse, sicurezza).
        
- **Uso tipico**: distribuzione di file, blockchain, applicazioni collaborative.
    

---

## 3. Architetture ibride

- **Descrizione**: combinano elementi Client/Server e P2P.
    
- **Esempio**:
    
    - Un server centrale gestisce l’autenticazione e l’indicizzazione delle risorse,
        
    - Ma lo scambio dei dati avviene direttamente tra peer (es. Skype nelle prime versioni).
        
- **Vantaggi**:
    
    - Si riduce il carico sul server centrale.
        
    - Si mantiene un minimo di controllo centralizzato (utile per sicurezza e gestione).
        
- **Uso tipico**: sistemi di messaggistica istantanea, videoconferenze, giochi online.
    

---

## Confronto tra i modelli

- **Client/Server** → semplice, centralizzato, ma fragile sul piano della ridondanza.
    
- **P2P** → distribuito, scalabile e resiliente, ma complesso da gestire.
    
- **Ibrido** → compromesso: centralizzazione minima + distribuzione delle risorse.
    

---

👉 Questo macroblocco ti serve per capire che **la logica della rete** è una scelta architetturale che influenza direttamente la **sicurezza**:

- se centralizzi, rischi SPOF e attacchi mirati,
    
- se distribuisci, devi gestire fiducia e autenticazione su larga scala.


---
# Confronto delle Topologie

![Pasted image 20250910123601.png](../../imgs/Pasted%20image%2020250910123601.png)

## 1. Lineare aperta

- **Affidabilità**: scarsa.
    
    - Se un nodo o un cavo si interrompe, la rete si spezza in due sottoreti isolate.
        
- **Scalabilità**: inefficiente.
    
    - Aggiungere o rimuovere un nodo intermedio blocca temporaneamente la rete.
        
- **Costi**: bassi (cavi ridotti).
    
- **Uso**: poco diffusa oggi per la sua fragilità.
    
- **Numero rami**: N-1.
    

---

## 2. Anello

- **Affidabilità**: alta/robusta.
    
    - I dati possono viaggiare in entrambi i sensi → se un percorso si guasta, si usa l’altro.
        
- **Scalabilità**: problematica.
    
    - Aggiungere o rimuovere un nodo comporta riaprire l’intero anello e variazioni di prestazioni.
        
- **Ottimizzazione banda**: possibile bilanciare pacchetti nei due versi.
    
- **Costi**: bassi (non serve un nodo centrale).
    
- **Numero rami**: N.
    

---

## 3. Albero

- **Affidabilità**: scarsa.
    
    - Ogni nodo ha un solo percorso verso la radice → ridondanza nulla.
        
    - Se cade il nodo di origine, cade tutta la rete.
        
- **Scalabilità**: buona solo in teoria, ma fragilità strutturale limita l’affidabilità.
    
- **Costi**: bassi.
    
- **Numero rami**: N-1.
    

---

## 4. Stella

- **Affidabilità**: alta.
    
    - Ogni dispositivo è collegato direttamente allo switch centrale.
        
    - Un guasto di un cavo isola solo un host, la rete resta operativa.
        
    - Resta però uno **SPOF**: lo switch.
        
- **Scalabilità**: buona (aggiungere un host = nuovo cavo verso lo switch).
    
- **Costi**: alti (molti cavi + switch centrale).
    
- **Numero rami**: N.
    

---

## 5. Maglia

- **Affidabilità**: massima.
    
    - Ogni nodo ha più percorsi alternativi → finché non è totalmente isolato, resta connesso.
        
- **Scalabilità**: ottima in termini di resilienza, ma costosissima da realizzare.
    
- **Costi**: elevati (molti collegamenti). Usata più facilmente in **wireless mesh**.
    
- **Numero rami**: N*(N-1)/2 (per una maglia completa).
    

---

## 6. Bus

- **Affidabilità**: media.
    
    - Un guasto al bus principale divide la rete in due sottoreti funzionanti.
        
- **Scalabilità**: buona.
    
    - Aggiungere un nodo è semplice, basta collegarlo al cavo.
        
- **Costi**: bassi (un solo cavo condiviso).
    
- **Numero rami**: 1 condiviso.

## Confronto sintetico

|Topologia|Affidabilità|Scalabilità|Costi|Note|
|---|---|---|---|---|
|**Lineare**|Bassa|Scarsa|Bassi|rete fragile, obsoleta|
|**Anello**|Alta|Scarsa|Bassi|robusta ma poco scalabile|
|**Albero**|Bassa|Media|Bassi|gerarchica ma fragile|
|**Stella**|Alta|Buona|Alti|standard LAN, SPOF sullo switch|
|**Maglia**|Massima|Ottima (ma costosa)|Molto alti|altissima resilienza|
|**Bus**|Media|Buona|Bassi|semplice, ma un guasto al bus divide la rete|

---

👉 Nota di sintesi:  
La scelta della topologia **non è mai solo tecnica**, ma è sempre un compromesso tra **affidabilità**, **scalabilità** e **costi**.

- Le LAN moderne usano **stella** per l’equilibrio affidabilità/costi.
    
- Le WAN e backbone adottano spesso **maglie parziali o complete** per garantire resilienza.
    
- Le topologie lineare, bus e albero sono oggi più di interesse storico o didattico.


---
# Single Point of Failure (SPOF)


![Pasted image 20250910123736.png](../../imgs/Pasted%20image%2020250910123736.png)

## Introduzione e legame con le topologie

Dal confronto tra le topologie è emerso che alcune sono più **robuste** (es. maglia), altre più **fragili** (es. lineare, albero).  
La ragione sta nella presenza o assenza di **percorsi alternativi**: se esiste un solo punto critico, il guasto di quel punto blocca o degrada l’intero sistema.  
Questo è esattamente ciò che si intende per **Single Point of Failure**.

---

## Definizione

Un **Single Point of Failure (SPOF)** è **qualsiasi componente unico e insostituibile** di un sistema informatico o di rete, hardware o software, il cui guasto provoca:

- **Anomalie** nel funzionamento, oppure
    
- La **cessazione completa** del servizio.
    

Esempi concreti:

- In una topologia a **stella**, lo **switch centrale** è uno SPOF: se si guasta, tutta la rete cade.
    
- In un sistema **client/server**, il **server centrale** è uno SPOF: se non risponde, nessun client ottiene servizio.
    
- In un data center, un **router unico** senza backup è uno SPOF per la connettività.
    
- In un’applicazione web, il **database principale** senza replica è uno SPOF logico.
    

---

## Impatto in sicurezza

Gli SPOF non sono solo un problema di **affidabilità tecnica**, ma anche di **cybersecurity**:

- Un attaccante può colpire intenzionalmente il punto debole per interrompere il servizio.
    
- Anche un malfunzionamento accidentale (bug, guasto hardware) ha lo stesso effetto.
    

👉 Per questo, nella progettazione sicura, uno SPOF è considerato **una vulnerabilità critica** da eliminare o mitigare.

---

## Strategie per evitarli

Gli SPOF si evitano o si riducono tramite **ridondanza**:

- Più dispositivi che svolgono la stessa funzione.
    
- Più percorsi possibili per raggiungere un nodo.
    
- Sistemi distribuiti che replicano i dati e i servizi.
    

Esempi:

- Due switch centrali in parallelo (stella ridondata).
    
- Replica dei server in cluster.
    
- Database replicati su più macchine.
    
- Backup dei router con protocolli come HSRP o VRRP.
    

---

👉 In sintesi:  
Un solo SPOF può compromettere l’intero sistema, anche se tutti gli altri componenti funzionano perfettamente. La sua eliminazione o mitigazione è quindi un requisito **fondamentale di progettazione** in rete e in sicurezza.


---
# Ridondanza

![Pasted image 20250910124032.png](../../imgs/Pasted%20image%2020250910124032.png)
## Concetto chiave

La **ridondanza** consiste nell’avere **più dispositivi** o percorsi che svolgono la **stessa funzione critica**.  
In questo modo, se uno si guasta, gli altri subentrano, evitando che il malfunzionamento di un singolo elemento comprometta l’intero sistema.

In altre parole:

- Lo **SPOF** è il “nemico” (un punto unico che può bloccare tutto).
    
- La **ridondanza** è la “cura” (duplicare i punti critici per non fermarsi mai).
    

---

## Esempio pratico (schema che hai allegato)

Nell’immagine si vede un’infrastruttura ridondata:

- Due **router** collegano la rete interna a Internet.
    
- Ciascun router si collega a uno **switch** separato.
    
- Ogni switch serve un **host**.
    

Se un router, uno switch o un collegamento cade, l’altro percorso rimane attivo → la comunicazione continua senza interruzioni.

---

## Applicazioni tipiche

- **Router ridondati**: per garantire continuità di connessione verso l’esterno.
    
- **Switch in parallelo**: per evitare che il guasto di un singolo switch isoli una LAN.
    
- **Server in cluster**: più macchine che erogano lo stesso servizio.
    
- **Database replicati**: copie sincronizzate per garantire disponibilità e integrità.
    
- **Percorsi di rete multipli**: cablaggi o link wireless di backup.
    

---

## Benefici

- **Aumenta l’affidabilità**: il sistema continua a funzionare anche con guasti locali.
    
- **Aumenta la disponibilità**: riduce i tempi di inattività (downtime).
    
- **Mitiga gli SPOF**: ogni componente critico deve avere almeno un “gemello”.
    

---

## Attenzione nella progettazione

La ridondanza non deve essere solo **parziale**, altrimenti si rischia di introdurre **nuovi SPOF nascosti**.  
Esempio: avere due switch ridondati ma un solo router significa che il router resta SPOF → l’intero sistema crolla comunque.

👉 Per essere efficace, la ridondanza deve coinvolgere **l’intera catena** dei dispositivi.



---
# RIASSUNTONE DEL MODULO
# Modulo 1 — Introduzione alla Cybersecurity

**Corso di Aspetti Organizzativi e Gestionali della Cybersecurity (AOGC)**

Il Modulo 1 è dedicato all’**introduzione ai fondamenti della cybersecurity**. È articolato in tre unità didattiche (UD1 – Introduzione, UD2 – Modelli e Approcci, UD3 – Tecnologia), ciascuna con più lezioni che affrontano i concetti di base da un punto di vista organizzativo, gestionale e tecnologico.

---

## Unità Didattica 1 — Introduzione alla Cybersecurity

L’UD1 inquadra la disciplina dal punto di vista **storico, concettuale e metodologico**.

- **Concetto di sicurezza informatica**: protezione di dati, sistemi e processi critici da minacce interne ed esterne.
    
- **Evoluzione della cybersecurity**: da esigenza tecnica (protezione di macchine e reti) a disciplina strategica con impatti su governance, diritto, società.
    
- **Dimensione organizzativa**: la sicurezza non è solo tecnologia, ma anche processi, norme e cultura aziendale.
    
- **Principio CIA** (Confidentiality, Integrity, Availability): le tre colonne portanti su cui si fonda ogni modello di sicurezza.
    
- **Rischio informatico**: definizione, misurazione e primi cenni alla gestione del rischio.
    

---

## Unità Didattica 2 — Modelli e Approcci alla Sicurezza

L’UD2 introduce i principali **modelli teorici** per comprendere e affrontare la sicurezza.

- **Modello ISO/OSI** e stratificazione delle comunicazioni: ogni livello introduce vulnerabilità specifiche e contromisure dedicate.
    
- **TCP/IP e protocolli di rete**: focus sugli elementi fondamentali delle comunicazioni digitali (IP addressing, socket TCP, determinismo vs non determinismo).
    
- **Attacchi e minacce**: introduzione a DoS/DDoS, social engineering, furto d’identità, phishing.
    
- **Incidente informatico**: definizione, esempi storici (Morris Worm) e principi di gestione.
    
- **Subnetting e NAT**: strumenti di ottimizzazione, isolamento e protezione nelle reti.
    
- **Approccio organizzativo**: l’adozione di standard e buone pratiche come primo livello di difesa.
    

---

## Unità Didattica 3 — Tecnologia

L’UD3 esplora i **fondamenti tecnici** delle infrastrutture di rete, con un taglio orientato alla progettazione sicura.

### Lezione 1 — Formalizzazione dei linguaggi e macchine a stati

- Introduzione ai linguaggi formali e alle macchine a stati finiti come strumenti concettuali per modellare i sistemi.
    
- Concetto di programmabilità e relazione con l’architettura degli elaboratori.
    

### Lezione 2 — Dispositivi di rete

- Analisi di hub, switch, router e firewall.
    
- Domini di collisione e broadcast.
    
- Gestione del traffico e segmentazione della rete.
    

### Lezione 3 — Modelli di comunicazione

- ISO/OSI e TCP/IP come schemi di riferimento.
    
- Socket TCP come “cervello” del protocollo.
    
- Comunicazioni real-time e determinismo.
    

### Lezione 4 — Architetture di rete

- **Architettura logica** vs **architettura fisica** (topologia).
    
- **Tipologie di rete**: PAN, LAN, MAN, WAN.
    
- **Architetture logiche**: Client/Server, Peer-to-Peer, Ibride.
    
- **Topologie fisiche**: lineare, anello, albero, stella, maglia, bus.
    
- Confronto tra topologie → affidabilità, scalabilità, costi.
    
- **SPOF**: single point of failure come punto critico da eliminare.
    
- **Ridondanza**: soluzione per garantire affidabilità e disponibilità dei sistemi.