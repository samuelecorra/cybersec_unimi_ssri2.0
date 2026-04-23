# Il modello alla base del networking

Il networking moderno ha un punto di partenza concettuale preciso: il **modello ISO/OSI**. OSI significa _Open Systems Interconnection_, ovvero interconnessione di sistemi aperti, e nasce come tentativo di dare un linguaggio comune universale a tutti i costruttori di computer e apparati di rete.

Prima della sua definizione, ogni azienda progettava reti **chiuse**, con protocolli proprietari e incompatibili con quelli di altri produttori. Il risultato era frammentazione: macchine di marche diverse non potevano scambiarsi dati, se non con costosi adattamenti ad hoc.

L’ISO (International Organization for Standardization) decise quindi, alla fine degli anni ’70, di creare un modello di riferimento che potesse funzionare come **standard internazionale**. Questo modello descrive la comunicazione di rete suddividendola in **sette livelli logici**, ognuno con compiti specifici. L’idea è che ogni livello parli con quello immediatamente superiore e inferiore, senza dover conoscere i dettagli interni degli altri. È un approccio modulare, che rende possibile progettare reti complesse mantenendo ordine e interoperabilità.
Adottato per la prima volta nel 1978.

---

## L’ISO come organizzazione

Per capire il peso del modello OSI bisogna anche conoscere chi lo ha definito.  
L’**ISO** è un organismo internazionale indipendente, con sede a Ginevra, nato nel 1947. Il suo obiettivo è creare e promuovere **standard** che valgano a livello globale in vari ambiti: dall’ingegneria meccanica ai sistemi di qualità, dalla sicurezza alimentare fino, appunto, alle telecomunicazioni e all’informatica.

Gli standard ISO vengono accettati e utilizzati dai singoli Stati membri, che aderiscono tramite i rispettivi enti nazionali di normazione (in Italia, ad esempio, l’UNI). Questo significa che quando parliamo di OSI non parliamo di una teoria accademica isolata, ma di un **documento normativo riconosciuto a livello mondiale**, frutto di compromessi e accordi tra Paesi, industrie e ricercatori.

---

## Il legame con la comunità internazionale

Non a caso, l’ISO ha rapporti istituzionali di primo livello: oltre a coordinare centinaia di comitati tecnici e a pubblicare migliaia di standard, **svolge anche funzioni consultive per l’Organizzazione delle Nazioni Unite (ONU)**. Questo evidenzia la rilevanza politica ed economica delle sue decisioni. Uno standard ISO non è semplicemente una proposta tecnica, ma diventa spesso un **punto di riferimento per il commercio globale e per la cooperazione internazionale**, anche in settori strategici come la cybersecurity e la gestione delle reti.

---
# L’architettura logica del modello ISO/OSI

Il modello ISO/OSI è costruito come una **pila logica di 7 livelli**, disposti verticalmente dal più vicino all’utente (in alto) al più vicino al mezzo fisico (in basso).  
L’idea è che la comunicazione tra due host non avvenga in un blocco unico, ma attraverso **strati successivi**, ognuno con una funzione specifica e ben delimitata. Questo principio si chiama **stratificazione** (_layering_).

---

## La regola fondamentale

Ogni livello:

1. **offre un servizio** al livello superiore, nascondendo i dettagli della sua realizzazione;
    
2. **usa i servizi** del livello inferiore, fidandosi del fatto che siano forniti correttamente.
    

Così, i livelli restano **indipendenti**: posso cambiare la tecnologia di trasmissione (ad es. passare dal cavo in rame alla fibra ottica) senza modificare i protocolli di trasporto o le applicazioni. È la stessa logica per cui puoi sostituire le fondamenta di un edificio con materiali più resistenti, senza dover rifare i piani superiori.

---

## I sette livelli spiegati uno ad uno

![Pasted image 20250909213436.png](../../imgs/Pasted%20image%2020250909213436.png)

Ecco la “mappa mentale” completa, con funzioni chiave ed esempi concreti:

### 7. Applicazione

- È lo strato più vicino all’utente e alle applicazioni reali.
    
- Offre **servizi di rete** visibili: posta elettronica (SMTP/IMAP/POP3), web (HTTP/HTTPS), trasferimento file (FTP), nomi di dominio (DNS), gestione di rete (SNMP), accesso remoto (Telnet, SSH).
    
- Non va confuso con il _software applicativo_: il browser non è “il livello 7”, ma **usa** protocolli che operano a livello 7.
    

---

### 6. Presentazione

- Ha il compito di rendere i dati **comprensibili** al livello applicazione, indipendentemente da come sono rappresentati internamente.
    
- Si occupa di:
    
    - **formati** (testo ASCII vs Unicode, immagini JPEG vs PNG, video MPEG),
        
    - **compressione** (ZIP, MPEG),
        
    - **cifratura** (SSL/TLS).
        
- È lo “interprete” dei dati: traduce dal linguaggio della macchina a un linguaggio condiviso per la trasmissione.
    

---

### 5. Sessione

- Stabilisce, gestisce e termina le **sessioni di comunicazione** tra applicazioni.
    
- Garantisce che due entità possano aprire un dialogo, mantenerlo sincronizzato, sospenderlo e riprenderlo.
    
- Strumento tipico: i **checkpoint**. Se una connessione cade, si può ripartire dall’ultimo punto salvato invece che ricominciare da zero.
    

---

### 4. Trasporto

- Responsabile della **consegna affidabile end-to-end**.
    
- Si occupa di:
    
    - **segmentazione** e riassemblaggio,
        
    - **controllo di flusso** (non saturare il destinatario),
        
    - **controllo di congestione** (non saturare la rete),
        
    - **affidabilità** (ritrasmissione pacchetti persi).
        
- Protocolli chiave: **TCP** (orientato alla connessione, affidabile) e **UDP** (senza connessione, leggero e veloce).
    
- Introduce il concetto di **porte** (es. HTTP = porta 80, HTTPS = porta 443).
    

---

### 3. Rete

- Fornisce l’**indirizzamento logico** e l’**instradamento** dei pacchetti.
    
- Qui troviamo l’**IP (Internet Protocol)**, che assegna indirizzi sorgente/destinazione a ogni datagramma.
    
- Funzioni principali:
    
    - **frammentazione/riassemblaggio** dei pacchetti,
        
    - **routing** tra reti diverse,
        
    - segnalazione di errori (ICMP).
        
- In sintesi: decide **da dove a dove deve andare un pacchetto**.
    

---

### 2. Collegamento (Data Link)

- Gestisce la comunicazione **punto-punto** tra nodi adiacenti.
    
- Suddivide i pacchetti di livello 3 in **frame**, aggiungendo indirizzi **MAC** e meccanismi di controllo degli errori (CRC).
    
- Comprende due sottolivelli:
    
    - **MAC** (Media Access Control): decide chi e quando può trasmettere su un mezzo condiviso (es. Ethernet, Wi-Fi).
        
    - **LLC** (Logical Link Control): gestisce la logica della connessione, multiplexer per diversi protocolli.
        
- Esempi: **Ethernet, Wi-Fi (802.11), PPP, Token Ring, ATM**.
    

---

### 1. Fisico

- È il livello più basso, responsabile della **trasmissione di bit** come segnali elettrici, ottici o radio.
    
- Definisce:
    
    - tipi di cavo (rame, fibra ottica),
        
    - connettori (RJ-45, SC),
        
    - tensioni, frequenze, modulazioni,
        
    - sincronizzazione dei segnali.
        
- In sintesi: traduce “1” e “0” in impulsi reali che viaggiano sul mezzo trasmissivo.
    

---

## Visione d’insieme

La pila OSI è come una catena di montaggio: ogni livello ha compiti chiari, lavora sui dati, li arricchisce con informazioni di controllo, e li passa al livello successivo.  
Grazie a questa struttura modulare:

- un problema può essere isolato al livello corrispondente (debug),
    
- nuove tecnologie possono essere integrate senza stravolgere tutto,
    
- i sistemi di produttori diversi riescono a interoperare.
    

---

📌 **In sintesi**: questa pagina introduce la vera “architettura logica” del networking. Senza questa stratificazione, non sarebbe possibile costruire reti globali scalabili, affidabili e universali come Internet.


---
# Comunicazioni modulari nel modello ISO/OSI

## L’idea di modularità

Il modello ISO/OSI non è solo una lista di sette strati: è un **meccanismo modulare**, che permette di trattare la comunicazione tra due host come una serie ordinata di passaggi, senza confondere i ruoli.  
Questa modularità è la chiave che rende la rete:

- **più semplice da progettare**: ogni livello ha un compito definito,
    
- **più facile da aggiornare**: puoi sostituire una tecnologia in un livello senza dover riscrivere tutto,
    
- **più chiara da capire**: si possono analizzare i problemi isolando il livello in cui avvengono.
    

---

## Comunicazione logica vs comunicazione fisica

![Pasted image 20250909213701.png](../../imgs/Pasted%20image%2020250909213701.png)

Nel grafico vedi due pile OSI: a sinistra l’**Host M** (mittente), a destra l’**Host D** (destinatario).

- Le **frecce tratteggiate blu** rappresentano la **comunicazione logica**: concettualmente, ogni livello “parla” con il suo pari sull’altro host.
    
    - Esempio: il livello Trasporto del mittente usa TCP, che “dialoga” con il TCP del destinatario.
        
    - In realtà, questi due moduli non sono fisicamente collegati, ma **si comportano come se lo fossero**, perché si scambiano dati con regole condivise.
        
- Le **frecce piene dorate** rappresentano la **comunicazione fisica**: il pacchetto scende per tutti i livelli del mittente fino al livello Fisico, dove i dati vengono convertiti in segnali (elettrici, ottici, radio). È **solo qui** che avviene la trasmissione reale sul mezzo.
    

---

## Il viaggio di un pacchetto

Il testo sotto al grafico lo spiega, ma lo possiamo rendere più vivido:

1. **Origine** – Il pacchetto nasce al livello **Applicazione** dell’host mittente (es. un messaggio HTTP).
    
2. **Discesa** – Il dato scende lungo la pila: ogni livello lo incapsula con i propri header (e talvolta trailer), arricchendolo con informazioni di controllo (porte, indirizzi IP, indirizzi MAC, CRC…).
    
3. **Trasmissione fisica** – Al livello **Fisico**, i bit vengono trasformati in segnali sul mezzo di trasmissione (cavo, fibra, radio). Qui avviene il vero “salto” verso l’altro host.
    
4. **Risale la pila** – L’host destinatario riceve i segnali al suo livello Fisico, li converte in bit e poi risale la pila:
    
    - Livello 2 rimuove l’header/trailer del frame,
        
    - Livello 3 estrae l’indirizzo IP e consegna al protocollo giusto,
        
    - Livello 4 riassembla i segmenti in ordine,
        
    - Livelli 5–6 gestiscono sessione, formati, cifratura,
        
    - fino a riconsegnare i dati all’**Applicazione**.
        

Il messaggio torna quindi “intatto” al livello applicativo del destinatario, come se fosse stato trasmesso direttamente da app a app.

---

## Perché è geniale

Questo meccanismo è geniale perché permette di ragionare su **due dimensioni contemporaneamente**:

- la **dimensione verticale**: i dati attraversano tutti i livelli (incapsulamento/decapsulamento);
    
- la **dimensione orizzontale**: i protocolli omologhi comunicano “tra pari” (logica).
    

In altre parole: un pacchetto fa un viaggio **verticale nel mittente**, un salto **orizzontale sul mezzo fisico**, e un altro viaggio **verticale nel destinatario**.

---

📌 **In sintesi**: la pagina sulle _Comunicazioni modulari_ ci mostra che la modularità dell’OSI separa il “che cosa” (comunicazione logica tra livelli pari) dal “come” (trasmissione fisica lungo la pila). Questo consente di analizzare e costruire le reti in modo ordinato, universale e interoperabile.


---
# Incapsulamento e Decapsulamento

## L’idea di base

Ogni livello della pila OSI **aggiunge informazioni proprie** al dato che riceve dal livello superiore. Queste informazioni vengono scritte in un **header** (intestazione), e a volte in un **trailer** (chiusura).  
Questo processo prende il nome di **incapsulamento**: il dato viene “impacchettato” in una nuova busta a ogni livello.  
Al destinatario avviene il contrario: a ogni livello, l’header/trailer viene **letto, interpretato e rimosso**. Questo è il **decapsulamento**.

---

## La matrioska di protocolli

Immagina le famose bambole russe che stanno una dentro l’altra.

- Al livello Applicazione ho il “messaggio” originale (es. `GET /index.html`).
    
- Scendendo nei livelli, ogni strato incarta questo messaggio con la sua busta.
    
- Al livello Fisico non resta che il pacchetto più esterno, fatto di bit che viaggiano su cavi o onde radio.
    

Quando arriva a destinazione, il processo è inverso: ogni livello apre la sua busta, legge le istruzioni nell’header, le usa per instradare correttamente i dati, e consegna il contenuto al livello sopra.
Perciò, in ogni livello, il pacchetto assume nomi identificativi differenti. Al livello di Trasporto, in cui opera il TCP, il pacchetto viene detto segmento TCP.

Affinché però tutto funzioni nel modo giusto, è sufficiente che ogni livello offra il proprio "servizio" al livello sottostante (o sovrastante) in maniera corretta. Se tutti i livelli svolgono il proprio ruolo correttamente, allora l'intera struttura garantisce la trasmissione corretta del pacchetto. Questo conferisce al Modello OSI robustezza e sicurezza delle trasmissioni. Inoltre, le informazioni aggiunte da uno qualsiasi dei Livelli, in "discesa", devono essere comprese dal corrispondente Livello, in "salita" per raggiungere l''host di destinazione.

---

## Struttura degli header nel grafico

![Pasted image 20250909213829.png](../../imgs/Pasted%20image%2020250909213829.png)

La slide li rappresenta così:

`AH | PH | SH | TH | RH | CH | Data | Trailer`

Dove:

- **AH** = Application Header (livello 7, dati specifici di applicazione come richieste HTTP o comandi SMTP).
    
- **PH** = Presentation Header (livello 6, eventuali informazioni di formato, compressione, cifratura).
    
- **SH** = Session Header (livello 5, per gestire sessione, dialogo, checkpoint).
    
- **TH** = Transport Header (livello 4, es. TCP/UDP: porte, numeri di sequenza, ack, window, flags).
    
- **RH** = Network Header (livello 3, es. IP: indirizzo sorgente/destinazione, TTL, protocol, frammentazione).
    
- **CH** = Data Link Header (livello 2, es. Ethernet: MAC sorgente/destinazione, tipo).
    
- **Trailer** = Data Link Trailer (tipicamente CRC per controllo errori).
    

Il blocco **Data** è il contenuto proveniente dal livello superiore, che per ciascun livello diventa il payload da incapsulare.

---

## Esempio passo-passo (mittente → destinatario)

![Pasted image 20250909213901.png](../../imgs/Pasted%20image%2020250909213901.png)

1. **Applicazione**: genera il messaggio `GET /index.html`.
    
    - Aggiunge **AH** → pacchetto = `[AH + Data]`.
        
2. **Presentazione**: può cifrare (TLS) o comprimere.
    
    - Aggiunge **PH** → `[PH + AH + Data]`.
        
3. **Sessione**: marca il dialogo, aggiunge eventuali checkpoint.
    
    - `[SH + PH + AH + Data]`.
        
4. **Trasporto**: TCP segmenta, aggiunge porte, sequence number, checksum.
    
    - `[TH + SH + PH + AH + Data]`.
        
5. **Rete**: IP incapsula tutto in un datagram, con indirizzi logici e TTL.
    
    - `[RH + TH + SH + PH + AH + Data]`.
        
6. **Collegamento**: Ethernet/Wi-Fi crea un frame con MAC src/dst, tipo, CRC trailer.
    
    - `[CH + RH + TH + SH + PH + AH + Data + Trailer]`.
        
7. **Fisico**: converte in **bit** → segnali elettrici/ottici/radio.
    

---

## Al destinatario (decapsulamento)

![Pasted image 20250909213930.png](../../imgs/Pasted%20image%2020250909213930.png)

1. Livello Fisico riceve i segnali e li traduce in bit.
    
2. Livello 2 legge **CH + Trailer**, li rimuove e consegna il payload al livello 3.
    
3. Livello 3 legge **RH**, lo rimuove, consegna al livello 4.
    
4. Livello 4 legge **TH**, ricostruisce i segmenti in ordine, consegna al livello 5.
    
5. Livello 5–6 gestiscono sessione e formato.
    
6. Livello 7 riceve finalmente i dati originali: il messaggio `GET /index.html`.
    

---

📌 **In sintesi**: l’incapsulamento è come spedire un documento importante con molte buste e etichette:

- ogni ufficio (livello) aggiunge la sua etichetta,
    
- il corriere (livello Fisico) porta il pacco al destinatario,
    
- e lì ogni ufficio rimuove la sua etichetta fino a restituire il documento originale.


---
# 🔹Livello 1 — Fisico

## Idea chiave

Trasforma **bit** in **segnali fisici** (e viceversa) e definisce **come** questi segnali viaggiano su **rame, fibra o aria**. Qui non esistono pacchetti, indirizzi o porte: **solo segnali, tempi e materiali**.

---

## Cosa disciplina

**Obiettivo generale**

- **Adeguamento agli apparati trasmissivi**: specifica cavi (UTP/coassiale), fibra, connettori (RJ-45, SC…), prese, pinout, lunghezze massime, impedenze, schermature.
    
- **Adeguamento alla “tensione dei bit”**: definisce **quali livelli elettrici/ottici/radio** rappresentano lo 0 e l’1 e **con quali margini** (tolleranze, rumore, soglie di decisione).
    

**Forma del segnale e temporizzazione**

- **Forma/Tensione del segnale**: ampiezza, polarità, spettro in frequenza, swing di tensione, potenza irradiata (radio), potenza ottica (fibra).
    
- **Durata del segnale (bit time)**: quante **microsecondi/nanosecondi** dura un bit; da qui derivano:
    
    - **Bitrate** $Rb​=1/Tb​  [bit/s]$
        
    - **Symbol/baud rate** $R_s$​ , se un simbolo codifica più bit (es. PAM-5, QAM…).
        
- **Codifica di linea** (come disegno i bit sul filo): es. **NRZ**, **NRZI**, **Manchester** (utile per ricavare il clock), **PAM** (ampiezze multiple).
    
- **Modulazione** (soprattutto per radio/fibra): altero **ampiezza/frequenza/fase** di una portante: **ASK/FSK/PSK/QAM**, **OFDM/DMT**.
    
- **Sincronizzazione**: clock comune o **clock recovery** dal segnale, preamboli, training sequences; gestione di **jitter** e **skew**.
    

**Bidirezionalità (duplex)**

- **Simplex**: una direzione sola (es. broadcast).
    
- **Half-duplex**: due direzioni ma non insieme (walkie-talkie).
    
- **Full-duplex**: due direzioni **simultanee**. Come si ottiene?
    
    - **FDD** (Frequency Division Duplex): due bande di frequenza diverse (tipico su radio/fibra).
        
    - **TDD** (Time Division Duplex): si alternano finestre temporali.
        
    - Su doppino/fili intrecciati moderni (es. 1000BASE-T) si usa **cancellazione d’eco** per trasmettere e ricevere contemporaneamente sugli stessi conduttori.
        

> In sintesi: il Livello 1 stabilisce **quali segnali**, **dove**, **con che tempi** e **con quali materiali**.

---

## Protocolli/tecnologie citati nella slide (con “una riga utile” ciascuno)

- **Bluetooth (PHY)**: radio a **2.4 GHz**, salti di frequenza; modulazioni **GFSK/π⁄4-DQPSK/8-DPSK** a seconda del profilo; potenze basse, pensato per corto raggio → il PHY definisce canali, potenza, modulazione, temporizzazione.
    
- **DSL (Digital Subscriber Line)**: su **doppino telefonico**; modulazione **DMT/OFDM** con tante sottoportanti; profili **ADSL/VDSL** con FDD/TDD; specifica spettri, potenze, maschere e sincronismi.
    
- **FDDI (Fiber Distributed Data Interface)**: **fibra** a 100 Mb/s (storico), anello ridondante; qui al Livello 1 definisce lunghezze d’onda/ottiche, potenze, connettori e parametri fisici.
    
- **OTN (Optical Transport Network)**: standard per **trasporto ottico** su più **λ** (wavelength); framing a livello fisico/trasporto ottico, budgeting di potenza, **rigenerazione 3R** (reamplify, reshape, retime).
    
- **RS-232**: seriale **asincrona** su rame; livelli **± V** (logica invertita), bit time, start/stop bit, nessuna codifica di linea sofisticata → è la scuola base della temporizzazione fisica.
    
- **UWB (Ultra-Wideband)**: impulsi **larghissima banda** a bassissima potenza; altissima risoluzione temporale (buona per ranging), definisce maschere spettrali e tempi strettissimi.
    

> Nota: molte tecnologie “famosissime” (es. **Ethernet 1000BASE-T**, **Wi-Fi 802.11 PHY**) sono altri esempi tipici di specifiche di **Livello 1** (cavi/canali, modulazioni, potenze, preamboli).

---

# 🔹 Livello 2 – Collegamento (Data Link)

## Funzione generale

Il livello di collegamento è il ponte tra il **mondo dei pacchetti logici** (Livello 3 – Rete) e il **mondo dei segnali** (Livello 1 – Fisico).  
Il suo compito è:

- **impacchettare** i dati in **frame (trame)**,
    
- **indirizzarli** a un destinatario preciso (indirizzi MAC),
    
- **garantire affidabilità locale** (controllo errori, ritrasmissione),
    
- **regolare l’accesso al mezzo condiviso** (MAC).
    

In breve: il Livello 2 prende un pacchetto di rete (es. IP), lo incapsula in un frame e lo consegna al livello fisico perché venga trasmesso senza errori tra due nodi adiacenti.

---

## Protocolli principali

A questo livello esistono protocolli diversi per gestire le varie tipologie e caratteristiche dei canali di trasmissione (es. reti locali LAN, reti geografiche WAN, sistemi WiFI, ecc).

- **Ethernet**: la tecnologia più diffusa per reti LAN cablate, con frame standardizzati e indirizzi MAC.
    
- **Wi-Fi (802.11)**: versione wireless del collegamento locale, con frame più complessi (fino a 4 indirizzi MAC).
    
- **PPP (Point-to-Point Protocol)**: usato in collegamenti punto-punto (es. vecchi modem, tunnel).
    
- **Token Ring**: storico, ogni nodo trasmetteva quando riceveva il “gettone logico”.
    
- **ATM (Asynchronous Transfer Mode)**: orientato a celle fisse da 53 byte, usato in backbone e telco.
    

---

## Struttura dei frame (Ethernet & Wi-Fi)

### 📦 Frame Ethernet

Campi principali:

1. **Preamble + SFD (Start Frame Delimiter)**: sequenza di sincronizzazione → serve al ricevente per agganciarsi al clock e capire dove inizia il frame.
    
2. **MAC Destination (48 bit)**: indirizzo fisico del destinatario.
    
3. **MAC Source (48 bit)**: indirizzo fisico del mittente.
    
4. **Length/Type**: dimensione dei dati o tipo di protocollo incapsulato (es. 0x0800 = IPv4, 0x86DD = IPv6).
    
5. **Data**: il payload (pacchetto di Livello 3, es. un datagram IP).
    
6. **CRC (Cyclic Redundancy Check)**: controllo errori; il ricevente ricalcola e confronta.
    

---

### 📡 Frame Wi-Fi (802.11)

Più articolato di Ethernet perché deve gestire reti wireless con AP e roaming.  
Campi principali:

1. **Frame Control**: indica tipo di frame (dati, gestione, controllo).
    
2. **Duration**: durata prevista della trasmissione (utile per riservare il canale).
    
3. **Indirizzi MAC (fino a 4)**:
    
    - MAC 1 = destinatario immediato,
        
    - MAC 2 = mittente immediato,
        
    - MAC 3 = router o AP,
        
    - MAC 4 = opzionale (in scenari complessi tipo mesh).
        
4. **Seq Control**: numerazione dei frame, utile per evitare duplicati.
    
5. **Data**: il payload vero e proprio.
    
6. **CRC**: controllo errori, come in Ethernet.
    

---

## Funzioni principali del livello 2

### 1. Identificazione dei nodi (indirizzi MAC)

- Ogni scheda di rete (NIC) ha un **indirizzo MAC univoco a 48 bit**, assegnato dal produttore e cablato nell’hardware.
    
- È unico al mondo e identifica in modo **non ambiguo** il dispositivo a livello globale.
    
- Esempio: `00:1A:92:3F:56:4B` → i primi 24 bit identificano il produttore (OUI), i restanti il dispositivo.
    

---

### 2. Controllo degli errori

- Al frame viene aggiunto un campo di **checksum/CRC**.
    
- Il ricevente ricalcola:
    
    - se coincide → il frame è integro,
        
    - se differisce → frame scartato.
        

---

### 3. Correzione errori tramite ritrasmissione

- I pacchetti sono numerati.
    
- Il ricevente risponde con **ACK** (positivo o negativo).
    
- Se l’ACK non arriva entro un timeout → il mittente **ritrasmette**.
    
- **Piggybacking**: l’ACK può essere “inserito” dentro un frame di dati che viaggia in direzione opposta → risparmio di traffico.
    
- Se arrivano duplicati (per ritrasmissioni multiple) → vengono scartati.
    

---

### 4. Connessione logica (LLC)

- Il **sottolivello LLC (Logical Link Control)** fornisce un’interfaccia uniforme al livello di rete.
    
- Significa che IP (Livello 3) può ricevere servizi identici sia se sotto c’è Ethernet, sia se c’è Wi-Fi, senza doversi occupare dei dettagli fisici.
    
- LLC = **astrazione** che unifica tecnologie diverse.
    

---

### 5. Controllo del flusso

- Evita che un mittente veloce saturi un ricevente lento.
    
- Se non ci fosse, il destinatario rischierebbe **overflow di buffer** e perdita di dati.
    
- Soluzione: sincronizzare i ritmi → il mittente si adegua al più lento.
    

---

### 6. Accesso al mezzo (MAC)

- In reti condivise (Ethernet, Wi-Fi) più nodi competono per lo stesso canale.
    
- Serve una disciplina di accesso (**MAC – Medium Access Control**).
    
    - Ethernet: **CSMA/CD** (Carrier Sense Multiple Access with Collision Detection).
        
    - Wi-Fi: **CSMA/CA** (Collision Avoidance, con RTS/CTS).
        
- Obiettivo: ridurre o gestire le collisioni.
    

---

### 7. Data Framing

- Il **frame** è l’unità dati del livello 2.
    
- È formato incapsulando il pacchetto di rete (es. IP) dentro **header + trailer**.
    
- L’header aggiunge info (MAC src/dst, tipo, controllo), il trailer chiude con CRC.
    

---

### 8. Multiplazione

- Lo stesso collegamento fisico può trasportare più canali logici.
    
- Tecniche:
    
    - **FDM** (Frequency Division Multiplexing),
        
    - **TDM** (Time Division Multiplexing),
        
    - **CDM** (Code Division Multiplexing).
        
- Scopo: sfruttare meglio la capacità del mezzo e permettere trasmissioni simultanee.
    

---

# 🔹 Livello 3 – Rete (Network Layer)

## Compito generale

Il livello di rete ha il compito di **trasportare pacchetti da un host mittente a un host destinatario che non sono direttamente collegati**.  
Per farlo, deve affrontare due grandi sfide:

1. **Indirizzamento logico** → assegnare a ogni nodo un indirizzo unico e indipendente dall’hardware.
    
2. **Instradamento (Routing)** → trovare il percorso migliore (o alternativo) tra sorgente e destinazione attraverso la rete.
    

In altre parole: se il livello 2 garantisce la comunicazione **tra nodi adiacenti**, il livello 3 costruisce la comunicazione **tra nodi arbitrari** su reti diverse.

---

## Protocolli principali

- **IP (Internet Protocol)** → il protocollo cardine di Internet, usato in due versioni:
    
    - **IPv4 (Internet Protocol version 4)** → 32 bit, notazione decimale puntata (es. `192.168.1.1`).
        
    - **IPv6 (Internet Protocol version 6)** → 128 bit, notazione esadecimale (es. `2001:0db8:85a3::8a2e:0370:7334`).
        
- **X.25** → protocollo storico di rete a commutazione di pacchetto, usato nelle prime WAN (Wide Area Network).
    
- **IPX (Internetwork Packet Exchange)** → usato nelle reti Novell NetWare (oggi obsoleto).
    

---

## Funzioni fondamentali del livello di rete

### 1. Indirizzamento logico

- Ogni host deve avere un **indirizzo logico univoco** su tutta la rete (es. IP address).
    
- È indipendente dall’hardware → a differenza del MAC Address, che è legato alla scheda di rete.
    
- Questo consente di far comunicare reti con tecnologie fisiche diverse.
    

---

### 2. Instradamento (Routing)

- Il livello 3 deve scegliere il **cammino migliore** per raggiungere la destinazione.
    
- Se un link è guasto, deve trovare un **cammino alternativo**.
    
- **Algoritmi di routing**:
    
    - **Statico** → le tabelle di instradamento sono configurate manualmente dall’amministratore e non cambiano automaticamente.
        
    - **Dinamico** → le tabelle si aggiornano in base allo stato reale della rete (latenza, congestione, guasti). Esempi di protocolli: **RIP (Routing Information Protocol)**, **OSPF (Open Shortest Path First)**, **BGP (Border Gateway Protocol)**.
        

---

### 3. Incapsulamento del pacchetto

- I dati ricevuti dal livello superiore (es. un segmento TCP del livello 4) vengono incapsulati in un’unità chiamata **pacchetto** o **datagramma**.
    
- Viene aggiunta un’intestazione (Network Layer Header) con tutte le informazioni necessarie: indirizzi IP, identificazione, TTL, ecc.
    

---

### 4. Gestione errore e diagnostica

- Il livello 3 non si occupa di errori di trasmissione dei bit (questo è compito del livello 2), ma può segnalare problemi legati alla consegna dei pacchetti.
    
- Protocollo dedicato: **ICMP (Internet Control Message Protocol)** → usato per inviare messaggi di errore e diagnostica (es. `ping`, `time exceeded`, `destination unreachable`).
    

---

### 5. Frammentazione e riassemblaggio

- I pacchetti IP devono adattarsi alla **MTU (Maximum Transmission Unit)** del livello 2 sottostante.
    
- Se un pacchetto è troppo grande, il livello 3 lo **frammenta** in più pezzi, ognuno con un header che indica come ricomporlo.
    
- Al destinatario, il livello 3 esegue il **riassemblaggio**.
    
- Inoltre, in reti eterogenee, può essere necessario tradurre indirizzi e riformattare pacchetti.
    

---

### 6. Gestione delle connessioni

- Alcuni protocolli di rete prevedono la creazione di un **circuito virtuale** (connessione logica stabile, come X.25).
    
- L’IP, invece, è **connectionless**: invia i pacchetti indipendenti l’uno dall’altro, senza stabilire un canale permanente.
    

---

## Indirizzi IP

### IPv4

- 32 bit (4 byte).
    
- Notazione decimale puntata: es. `172.16.254.1`.
    
- Rappresentazione binaria: `10101100.00010000.11111110.00000001`.
    
- Totale di circa 4,3 miliardi di indirizzi unici (2³²).
    

### IPv6

- 128 bit (16 byte).
    
- Notazione esadecimale: es. `2001:0db8:AC10:FE01:0000:0000:0000:0000`.
    
- Spesso si abbrevia eliminando blocchi di zeri: `2001:0db8:AC10:FE01::`.
    
- Totale: 2¹²⁸ indirizzi → praticamente inesauribili.
    

---

## Struttura dell’header IPv4 (campi principali)

1. **Version** → indica la versione del protocollo (4 o 6).
    
2. **IHL (Internet Header Length)** → lunghezza dell’header, in multipli di 32 bit.
    
3. **Type of Service (TOS)** → oggi sostituito da DSCP (Differentiated Services Code Point); serve a dare priorità o qualità di servizio (QoS).
    
4. **Total Length** → dimensione totale del pacchetto (header + dati), max 65.535 byte.
    
5. **Identification** → numero che identifica un datagramma; usato per ricomporre i frammenti.
    
6. **Flags** → 3 bit, usati per la frammentazione:
    
    - primo bit = 0 (riservato),
        
    - **DF (Don’t Fragment)** = se 1, il pacchetto non può essere frammentato,
        
    - **MF (More Fragments)** = se 1, indica che ci sono altri frammenti.
        
7. **Fragment Offset** → posizione del frammento rispetto all’inizio del datagramma.
    
8. **TTL (Time To Live)** → contatore che decremente a ogni router attraversato; evita loop infiniti.
    
9. **Protocol** → specifica il protocollo del livello di trasporto a cui consegnare i dati (es. 6 = TCP, 17 = UDP).
    
10. **Header Checksum** → calcolato solo sull’header; verificato e ricalcolato a ogni nodo.
    
11. **Source IP Address** → indirizzo IP del mittente.
    
12. **Destination IP Address** → indirizzo IP del destinatario.
    
13. **Options** (opzionali) → campi usati per funzioni particolari (es. sicurezza, tracciamento percorso).
    

---

📌 **Sintesi**:  
Il livello di rete garantisce che i pacchetti viaggino da un host all’altro **attraverso più reti**, indipendentemente dal mezzo fisico usato. Usa indirizzi logici (IP), trova percorsi tramite algoritmi di routing, incapsula i dati in pacchetti, frammenta se necessario, e segnala errori con protocolli di supporto (ICMP). L’IP, con i suoi header IPv4/IPv6, è il cuore di questo livello.


---
# 🔹 Livello 4 – Trasporto (Transport Layer)

## Compito generale

Il livello di trasporto fa da **ponte logico tra applicazioni** che girano su host diversi.  
Mentre il livello 3 si limita a “spedire pacchetti a un indirizzo IP”, il livello 4 assicura che i dati arrivino:

- all’applicazione giusta (tramite **porte**),
    
- nell’ordine corretto,
    
- senza perdite o duplicazioni,
    
- a un ritmo sostenibile sia per gli host che per la rete.
    

In sintesi: il livello di rete è come il corriere che ti porta il pacco fino al palazzo, il livello di trasporto è il **postino che bussa all’appartamento giusto, controlla che il contenuto sia integro e raccoglie la ricevuta di consegna**.

---

## Protocolli principali

- **TCP (Transmission Control Protocol)** → orientato alla connessione, affidabile, con controllo di ordine e ritrasmissioni.
    
- **UDP (User Datagram Protocol)** → senza connessione, leggero, usato per streaming, DNS, VoIP.
    
- **SPX (Sequenced Packet Exchange)** → usato su IPX, storico delle reti Novell.
    

---

## Funzioni principali

1. **Servizio orientato alla connessione**
    
    - Tipico di TCP: si crea una connessione virtuale (handshake), la si usa per trasmettere, la si chiude al termine.
        
    - Garantisce affidabilità, ma non deve restare aperta troppo a lungo → altrimenti congestiona la rete.
        
2. **Ordine di consegna corretto**
    
    - I segmenti numerati con **Sequence Number** possono seguire strade diverse nella rete.
        
    - TCP li ricompone nell’ordine giusto al destinatario.
        
3. **Trasferimento affidabile**
    
    - Ogni segmento ricevuto viene confermato con un **ACK (Acknowledgment)**.
        
    - Se un ACK non arriva, il mittente ritrasmette.
        
4. **Controllo di flusso**
    
    - Impedisce che un host veloce sommerga di dati un host lento.
        
    - Il ricevente indica la propria capacità nella **Window Size**.
        
5. **Controllo di congestione**
    
    - TCP adatta dinamicamente la velocità in base alle condizioni della rete (slow start, congestion avoidance).
        
    - Evita di intasare i router con troppi pacchetti simultanei.
        
6. **Orientamento al byte**
    
    - TCP vede la comunicazione come uno **stream di byte continuo**, non come pacchetti separati.
        
    - È il protocollo a segmentare lo stream, non l’applicazione.
        
7. **Multiplexing tramite porte**
    
    - Più connessioni possono coesistere tra gli stessi host grazie all’uso delle **porte**.
        
    - Es: Web = porta 80/443, Mail = 25/587, DNS = 53.
        
    - UDP e TCP usano spazi di porte distinti.
        

---

## Struttura di un segmento TCP (Header TCP)

1. **Source Port** (porta sorgente) → identifica l’applicazione mittente.
    
2. **Destination Port** (porta destinazione) → identifica l’applicazione ricevente.
    
3. **Sequence Number** → numero di sequenza che indica la posizione dei dati nello stream.
    
    - Serve per ricostruire l’ordine e rilevare perdite.
        
4. **Acknowledgment Number** → conferma di ricezione: indica il prossimo byte che il destinatario si aspetta.
    
5. **Data Offset** → lunghezza dell’header, in parole da 32 bit (dword).
    
6. **Reserved** → bit riservati per usi futuri, impostati a 0.
    
7. **Flags** (chiamati anche **control bits**) → singoli bit che controllano la connessione:
    
    - **URG** = Urgent → dati urgenti presenti.
        
    - **ACK** = Acknowledgment valido.
        
    - **PSH** = Push → consegna immediata ai livelli superiori.
        
    - **RST** = Reset → resetta la connessione.
        
    - **SYN** = Synchronize → avvia la connessione (handshake).
        
    - **FIN** = Finish → termina la connessione.
        
8. **Window Size** → quanti byte il ricevente può accettare; usata per il controllo di flusso.
    
9. **Checksum (CRC – Cyclic Redundancy Check)** → controllo integrità del segmento TCP (header + dati).
    
10. **Urgent Pointer** → usato se URG=1, indica l’offset dei dati urgenti.
    
11. **Options** → campi opzionali (es. MSS = Maximum Segment Size, timestamp, scaling).
    
12. **Data** → il payload vero e proprio (dati applicativi, es. HTTP).
    

---

## Differenze chiave TCP vs UDP

- **TCP** → connessione, affidabilità, ordine, controllo → più overhead ma sicuro.
    
- **UDP** → nessuna connessione, niente controllo, consegna “best effort” → più veloce, adatto per streaming e tempo reale.
    

---

📌 **Sintesi da 30 e lode**:  
Il livello 4 non si limita a “spedire pacchetti”: crea un **dialogo controllato tra applicazioni**, garantendo che i dati arrivino completi, ordinati e alla velocità giusta. TCP realizza questo con segmentazione, numerazione, ACK, controllo di flusso e congestione, mentre UDP punta su leggerezza e velocità sacrificando l’affidabilità.


---

# 🔹 Livello 5 – Sessione (Session Layer)

## Compito generale

Il livello di sessione ha la responsabilità di **creare, mantenere e terminare una sessione di comunicazione** tra due applicazioni su host diversi.

- Una **sessione** è un dialogo logico che dura per tutto il tempo in cui due processi devono comunicare.
    
- È come una “conversazione telefonica” tra due computer: non basta avere la linea fisica e non basta spedire pacchetti → bisogna anche avere la logica che gestisce l’inizio, il mantenimento e la fine della conversazione.
    

---

## Funzioni principali

### 1. Definizione della sessione

- Stabilisce il **canale logico** tra due applicazioni.
    
- Decide **quando** la sessione inizia, **quanto dura** e **quando termina**.
    
- Garantisce che entrambe le parti sappiano di essere in comunicazione.
    

---

### 2. Sincronizzazione

- Una delle funzioni più importanti.
    
- Il livello 5 può inserire dei **checkpoint (punti di sincronizzazione)** all’interno del flusso di dati.
    
- Questi checkpoint sono come “salvataggi automatici”:
    
    - Ad ogni checkpoint, entrambi gli host memorizzano lo stato della trasmissione (backup dei dati inviati/ricevuti, configurazione della rete, clock).
        
    - Se la sessione si interrompe (guasto, timeout, disconnessione), non bisogna ricominciare da zero → basta riprendere dall’ultimo checkpoint salvato.
        

📌 **Esempio pratico**:

- Immagina un file di 1 GB trasferito tra due computer.
    
- Senza checkpoint: se la sessione cade al 900° MB, devi ricominciare tutto.
    
- Con checkpoint: riprendi dal 900° MB, evitando di rispedire i primi 899 MB.
    

---

### 3. Gestione delle interruzioni

- La sessione può essere interrotta volontariamente (chiusura corretta) o involontariamente (guasto).
    
- Grazie alla sincronizzazione, è possibile **riprendere la comunicazione dal punto in cui si era interrotta**, invece di perdere l’intero lavoro.
    
- Questo rende il livello 5 fondamentale per applicazioni lunghe, delicate o sensibili agli errori.
    

---

## Protocolli principali

- **RPC (Remote Procedure Call)** → permette di chiamare procedure su un altro computer come se fossero locali.
    
- **ASP (AppleTalk Session Protocol)** → protocollo di sessione delle reti AppleTalk.
    
- **DNA SCP (Digital Network Architecture Session Control Protocol)** → usato nei sistemi DEC (Digital Equipment Corporation).
    
- **X-Windows System** → sistema di finestre distribuito, in cui la gestione della sessione tra client e server è essenziale.
    

---

## Illustrazione del meccanismo (grafico della slide)

- Linea di trasmissione con segmenti → rappresenta il flusso di dati.
    
- Blocchi “CP” (CheckPoint) → indicano i punti in cui i dati vengono salvati e sincronizzati.
    
- Se la comunicazione cade, al ripristino la sessione può ricominciare dall’ultimo CP memorizzato.
    

---

## Sintesi

Il livello 5 (Sessione):

- Crea e gestisce il **dialogo logico** tra due applicazioni.
    
- Introduce **checkpoint** per consentire riprese dopo interruzioni.
    
- Coordina l’avvio, la durata e la chiusura delle sessioni.
    
- È particolarmente utile nei trasferimenti di lunga durata, nelle comunicazioni sensibili e nei sistemi distribuiti.


---
# 🔹 Livello 6 – Presentazione (Presentation Layer)

## Compito generale

Il livello di presentazione si occupa di **tradurre, formattare, cifrare e comprimere i dati** scambiati tra applicazioni.  
Se il livello 5 (Sessione) crea e gestisce il dialogo logico, il livello 6 si assicura che ciò che viene trasmesso sia **leggibile e coerente** su entrambi i lati.

📌 **Metafora utile**: se due persone parlano lingue diverse ma vogliono dialogare, il livello di presentazione è l’interprete che traduce il messaggio in un linguaggio comune e poi lo riconverte nella lingua del destinatario.

---

## Funzioni principali

### 1. Crittografia (Encryption)

- Garantisce **riservatezza e sicurezza** delle comunicazioni.
    
- I dati vengono cifrati in trasmissione e decifrati in ricezione.
    
- Scopo: impedire che un intercettatore comprenda il contenuto dei messaggi.
    
- Esempi: **SSL/TLS (Secure Socket Layer / Transport Layer Security)**, protocolli che proteggono HTTPS, email sicure, VPN.
    

---

### 2. Formattazione (Syntax / Data Representation)

- Ogni computer ha la propria rappresentazione interna dei dati (es. big-endian vs little-endian, set di caratteri diversi).
    
- Il livello di presentazione converte i dati:
    
    - in **trasmissione** → dalla sintassi locale al formato standard di trasferimento,
        
    - in **ricezione** → dal formato standard alla sintassi locale del destinatario.
        
- Così si assicura che i dati siano **interpretabili allo stesso modo** da sistemi diversi.
    

📌 Esempio:

- Il testo `"CIAO"` sul mittente potrebbe essere codificato in **ASCII** (1 byte per carattere),
    
- sul destinatario in **EBCDIC** (formato mainframe IBM).
    
- Il livello 6 traduce tra questi due mondi in modo trasparente.
    

---

### 3. Compressione dei dati (Data Compression)

- Riduce il numero di bit da trasmettere mantenendo inalterata l’informazione.
    
- Vantaggi:
    
    - risparmio di banda,
        
    - trasferimenti più rapidi,
        
    - meno spazio su disco.
        
- Tecniche:
    
    - **Lossless** (senza perdita) → ZIP, PNG, TIFF.
        
    - **Lossy** (con perdita controllata) → JPEG (immagini), MPEG (video), MP3 (audio).
        

---

## Protocolli e standard tipici

- **ASCII (American Standard Code for Information Interchange)** ed **EBCDIC (Extended Binary Coded Decimal Interchange Code)** → codifiche di testo.
    
- **GIF (Graphic Interchange Format)** → immagini con compressione lossless e palette ridotta.
    
- **JPEG (Joint Photographic Experts Group)** → immagini con compressione lossy.
    
- **TIFF (Tagged Image File Format)** → immagini ad alta qualità (spesso lossless).
    
- **MPEG (Motion Picture Experts Group)** → standard video con compressione lossy.
    
- **MIDI (Musical Instrument Digital Interface)** → rappresentazione di eventi musicali digitali.
    
- **QuickTime** → contenitore multimediale per audio/video.
    

---

## Differenze con altri livelli

- Il **Livello 4 (Trasporto)** non guarda al contenuto, ma solo a spedire segmenti corretti e ordinati.
    
- Il **Livello 5 (Sessione)** si preoccupa della gestione del dialogo e dei checkpoint.
    
- Il **Livello 6 (Presentazione)** si concentra invece su **cosa significano i dati** e su come renderli interoperabili, sicuri e compatti.
    

---

## Sintesi

Il livello di presentazione è il **traduttore universale** del modello OSI.

- Rende i dati indipendenti dalla piattaforma che li elabora.
    
- Li protegge con la **crittografia**, li rende più leggeri con la **compressione** e li uniforma con la **formattazione**.
    
- Grazie a questo livello, un file di testo, un’immagine, un brano musicale o un video possono essere scambiati senza che mittente e destinatario si preoccupino dei dettagli interni delle rispettive macchine.


---
# 🔹 Livello 7 – Applicazione (Application Layer)

## Compito generale

Il livello di applicazione è quello che **interagisce direttamente con i programmi e con l’utente finale**.  
Qui non parliamo più di incapsulamento o trasporto, ma dei **protocolli che forniscono i servizi di rete reali**: posta elettronica, web, trasferimento file, login remoto, gestione di risorse distribuite.

📌 **Metafora**: se tutta la pila OSI è come un grattacielo di telecomunicazioni, il livello applicazione è **l’ufficio dove lavori**. I piani sottostanti (fisico, collegamento, rete, trasporto, sessione, presentazione) ti hanno portato l’infrastruttura: cavi, elettricità, linee telefoniche, interpreti, sincronizzazioni. Ora sei tu che puoi effettivamente parlare con il tuo interlocutore e scambiare documenti.

---

## Funzioni principali

- **Scambio di email** (SMTP, POP, IMAP).
    
- **Trasferimento di file** (FTP, HTTP, Gnutella, ecc.).
    
- **Accesso a database** e a risorse distribuite.
    
- **Navigazione web** (HTTP/HTTPS).
    
- **Gestione remota** (SSH, Telnet, SNMP).
    
- **Emulazione di terminali** → far sì che un computer si comporti come se fosse un terminale collegato a un mainframe.
    
- **Sincronizzazione della comunicazione** e identificazione delle risorse disponibili nella rete.
    

---

## Classi di protocolli

### 1. Protocolli di servizio

- **DHCP (Dynamic Host Configuration Protocol)** → assegna automaticamente indirizzi IP, subnet mask, gateway e DNS ai client della rete.
    
- **DNS (Domain Name System)** → traduce i nomi a dominio leggibili (es. `www.google.com`) in indirizzi IP numerici.
    
- **Finger protocol** → usato storicamente per ottenere informazioni sugli utenti remoti (oggi obsoleto).
    
- **NTP (Network Time Protocol)** → sincronizza gli orologi dei sistemi in rete.
    
- **SNMP (Simple Network Management Protocol)** → gestione e monitoraggio di dispositivi di rete (router, switch, server).
    
- **LDAP (Lightweight Directory Access Protocol)** → accesso a directory distribuite (es. rubriche, autenticazione centralizzata).
    

---

### 2. Protocolli di accesso remoto

- **Telnet** → login remoto in chiaro (non sicuro, quasi dismesso).
    
- **SSH (Secure Shell)** → login remoto cifrato, oggi lo standard per amministrare server e apparati di rete.
    

---

### 3. Posta elettronica e newsgroup

- **SMTP (Simple Mail Transfer Protocol)** → invio delle email dal client al server o tra server.
    
- **POP (Post Office Protocol)** → scarica i messaggi dal server al client, in genere li elimina dal server.
    
- **IMAP (Internet Message Access Protocol)** → consente di gestire le email direttamente sul server, mantenendo sincronizzati più dispositivi.
    
- **NNTP (Network News Transfer Protocol)** → protocollo per i newsgroup (forum distribuiti, oggi quasi scomparsi).
    

---

### 4. Trasferimento di file e web

- **FTP (File Transfer Protocol)** → trasferimento di file tra client e server, supporta anche autenticazione.
    
- **HTTP (HyperText Transfer Protocol)** → base del World Wide Web, usato per trasferire pagine, immagini, contenuti multimediali. La versione sicura è **HTTPS** (con TLS).
    
- **IRC (Internet Relay Chat)** → protocollo storico per chat testuali in tempo reale.
    
- **Gnutella** → protocollo peer-to-peer per la condivisione di file.
    

---

## Relazione con i livelli sottostanti

- Non implementa servizi di trasporto o indirizzamento: **si appoggia al livello di trasporto (TCP/UDP)**.
    
- Non traduce formati: questa è una funzione del **livello presentazione**.
    
- Non crea sessioni: lo fa il **livello 5 (Sessione)**.
    
- Si concentra esclusivamente sul **servizio applicativo finale** che l’utente vede e usa.
    

---

## Esempi concreti (nella vita reale)

- Quando navighi con Chrome o Firefox → usi **HTTP/HTTPS**.
    
- Quando controlli la posta su Gmail → dietro le quinte agiscono **SMTP/IMAP**.
    
- Quando apri una connessione SSH a un server → sei su **Livello 7 (SSH)** che si appoggia su TCP/22.
    
- Quando scarichi file da un server → usi **FTP** o **HTTP**.
    
- Quando la tua macchina ottiene l’IP dal router → agisce **DHCP**.
    

---

## Sintesi

Il livello applicazione è la **faccia visibile della rete**.  
Qui troviamo tutti i protocolli che realizzano i servizi quotidiani: posta elettronica, web, chat, trasferimento di file, gestione remota.  
È il livello che interagisce con l’utente e con il software applicativo, sfruttando i servizi dei livelli sottostanti per fornire **un’esperienza trasparente, affidabile e interoperabile**.