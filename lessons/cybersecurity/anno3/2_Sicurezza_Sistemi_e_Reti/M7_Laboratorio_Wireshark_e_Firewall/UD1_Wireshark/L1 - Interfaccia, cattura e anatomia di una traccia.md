# **M7 – Laboratorio: Wireshark e Configurazione Firewall**

Questo modulo raccoglie il materiale operativo per la **seconda parte d'esame (laboratorio)** di Sicurezza dei Sistemi e delle Reti, che è **open book**. Si compone di due unità didattiche:

- **UD1 – Wireshark**: come muoversi con rapidità nell'analisi di una cattura di traffico, dalla lettura degli endpoint fino all'estrazione di file, credenziali e indicatori di attacco.
- **UD2 – Configurazione Firewall**: la metodologia per tradurre una topologia e una politica di sicurezza nella tabella di filtraggio richiesta all'esame, con la gestione corretta del traffico di ritorno, dei servizi multi-connessione e del NAT.

Poiché il laboratorio consente l'uso di manuali e documentazione, l'obiettivo di queste lezioni non è la memorizzazione ma la **velocità di esecuzione**: sapere esattamente quale menu aprire, quale filtro digitare e come interpretare il risultato, così da non perdere tempo durante la prova.

---

## **UD1 – Analisi del traffico con Wireshark**

## **Lezione 1: Interfaccia, cattura e anatomia di una traccia**

### **1. Che cos'è Wireshark e a cosa serve all'esame**

**Wireshark** è un *analizzatore di protocolli* (packet analyzer): cattura i pacchetti che transitano su un'interfaccia di rete e li **decodifica** livello per livello, dal frame Ethernet fino al contenuto applicativo, mostrandoli in forma leggibile. All'esame non si cattura traffico dal vivo: viene fornito un **file di traccia** (una cattura già registrata) da aprire e interpretare. Il compito tipico è ricostruire *chi ha comunicato con chi, con quali protocolli, per fare cosa*, e riconoscere eventuali attività sospette.

> 📌 La cattura è una **fotografia fedele** di ciò che è passato sul cavo: Wireshark non "indovina" nulla, si limita a interpretare i byte realmente presenti. Ogni risposta che dai all'esame deve poter essere **puntata con il dito** su un pacchetto della traccia.

Il motore di cattura si appoggia alle librerie **libpcap** (Linux/macOS) o **Npcap** (Windows), le stesse usate da `tcpdump`. La versione a riga di comando di Wireshark si chiama **`tshark`** ed è utilissima per estrazioni rapide e ripetibili.

---

### **2. Formati dei file di traccia: `.pcap` e `.pcapng`**

Esistono due formati principali, entrambi apribili con Wireshark:

- **`.pcap`** (formato classico *libpcap*): un'intestazione globale seguita da una sequenza di record, ciascuno con timestamp, lunghezza e i byte grezzi del frame.
- **`.pcapng`** (*pcap Next Generation*): formato a blocchi, più ricco (può contenere più interfacce, commenti, informazioni sull'host di cattura).

> 💡 Riconoscere il formato è immediato guardando i primi byte (il *magic number*): `d4 c3 b2 a1` indica un `.pcap` little-endian, mentre `0a 0d 0d 0a` indica un `.pcapng`. Non è una domanda d'esame, ma spiega perché a volte una traccia è `.pcapng` e un'altra `.pcap`: sono equivalenti ai fini dell'analisi.

---

### **3. Il layout a tre riquadri**

Aperta una traccia, la finestra di Wireshark si divide in tre aree che vanno lette in sequenza:

1. **Packet List (in alto)** — l'elenco dei pacchetti, una riga per pacchetto. Le colonne predefinite sono *No.* (numero progressivo), *Time* (istante relativo all'inizio della cattura), *Source*, *Destination*, *Protocol* (il protocollo di livello più alto riconosciuto), *Length* e *Info* (una sintesi testuale). I pacchetti sono **colorati** secondo regole predefinite (es. TCP in grigio-azzurro, errori in nero/rosso), il che aiuta a individuare a colpo d'occhio ritrasmissioni e anomalie.
2. **Packet Details (al centro)** — la decodifica **gerarchica** del pacchetto selezionato, un albero espandibile che segue lo stack: Frame → Ethernet II → IP → TCP/UDP → protocollo applicativo. È qui che si leggono MAC, IP, porte, flag, numeri di sequenza e i campi applicativi.
3. **Packet Bytes (in basso)** — il dump esadecimale/ASCII dei byte grezzi; selezionando un campo nel riquadro centrale, i byte corrispondenti si evidenziano qui.

> 💡 Metodo rapido: si scorre la **Packet List** per orientarsi, si clicca il pacchetto interessante e si espande nel **Packet Details** solo il livello che serve. Raramente serve leggere il dump esadecimale, tranne quando si cerca la *magic number* di un file (vedi L5).

---

### **4. Anatomia di un pacchetto: leggere lo stack**

Espandendo il riquadro dei dettagli si ritrova, dal basso verso l'alto dello stack, l'incapsulamento visto in [M4/UD1/L1 – Modello ISO OSI](../../M4/UD1/L1%20-%20Modello%20ISO%20OSI.md):

- **Frame**: metadati di Wireshark (numero, timestamp, lunghezza) — non è un livello reale, è la "scatola" del catturatore.
- **Ethernet II**: indirizzi **MAC** sorgente e destinazione (48 bit). Wireshark risolve automaticamente i primi 24 bit (l'**OUI**, *Organizationally Unique Identifier*) nel nome del **produttore** della scheda di rete — informazione preziosa per dedurre l'hardware (vedi L4).
- **Internet Protocol**: indirizzi **IP** sorgente/destinazione, TTL, protocollo incapsulato.
- **TCP / UDP**: porte sorgente/destinazione, e per TCP i numeri di **sequenza** e **acknowledgment**, i **flag** (SYN, ACK, FIN, RST, PSH) e la finestra.
- **Livello applicativo**: HTTP, DNS, FTP, TLS, ecc., con i rispettivi campi decodificati.

> 📌 La quaterna `(IP sorgente, porta sorgente, IP destinazione, porta destinazione)` più il protocollo identifica univocamente una **connessione**. Tenerla a mente è la chiave per rispondere a "quali nodi sono coinvolti" e per seguire una singola sessione dentro una cattura affollata.

---

### **5. Tempistica: la colonna Time**

Per default la colonna *Time* mostra i secondi trascorsi **dall'inizio della cattura** (il primo pacchetto è a $t=0$). Questo permette di rispondere a domande del tipo "*quando inizia la connessione FTP?*": si individua il primo pacchetto della sessione (di norma il **SYN** verso la porta del servizio) e si legge il suo tempo relativo.

Dal menu **View → Time Display Format** si può cambiare il riferimento: ora assoluta (data e ora reali del pacchetto), tempo dall'inizio della cattura, oppure **secondi dal pacchetto precedente** (utile per misurare i ritardi tra messaggi). Per correlare eventi con un orario reale (es. in un contesto forense) si usa il formato assoluto.

> ⚠️ Attenzione a non confondere il *numero di pacchetto* (colonna No., un semplice indice) con il *tempo*: due pacchetti consecutivi possono essere distanti secondi o microsecondi. Per l'ordine cronologico conta il Time, non è detto coincida con l'ordine di visualizzazione dopo aver applicato un filtro.

---

### **6. Un flusso di lavoro-tipo per l'esame**

Di fronte a una traccia sconosciuta, conviene procedere sempre nello stesso ordine, dal generale al particolare:

1. **Panoramica dei protocolli**: `Statistics → Protocol Hierarchy` per capire in un colpo d'occhio *cosa* contiene la cattura (quanta parte è HTTP, DNS, TLS, FTP…). Vedi L3.
2. **Chi parla con chi**: `Statistics → Conversations` per la lista delle coppie di endpoint e dei volumi di traffico. Vedi L3.
3. **Isolare la sessione richiesta** con un filtro di visualizzazione (es. `ftp`, `dns`, `tcp.stream eq 0`). Vedi L2.
4. **Ricostruire il dialogo** con `Follow → TCP/HTTP Stream`. Vedi L3.
5. **Estrarre gli artefatti**: file trasferiti, credenziali, URL, indicatori di compromissione. Vedi L5.

> ✅ **Ricapitolando** — Wireshark decodifica una cattura (`.pcap`/`.pcapng`) mostrando ogni pacchetto su tre riquadri: lista, dettagli gerarchici (Frame → Ethernet → IP → TCP/UDP → applicativo) e byte grezzi. La quaterna identifica una connessione, la colonna Time (relativa all'inizio) risponde ai "quando", e l'OUI del MAC anticipa il produttore dell'hardware. Il metodo vincente all'esame è top-down: prima le statistiche d'insieme, poi il filtro sulla sessione, infine il Follow Stream e l'estrazione degli artefatti.

Prossima lezione: [[L2 - Filtri di cattura e di visualizzazione]].
