## **Lezione 4: Altre tecniche di scansione e contromisure**

### **1. Introduzione**

Questa lezione completa l'unità sul network e port scanning presentando alcune tecniche ulteriori e le principali contromisure difensive. Dopo le scansioni TCP classiche e stealth, vengono analizzati:

- **UDP scan**, più lento e ambiguo del TCP scan;

- **FTP bounce scan**, che sfrutta un server FTP come intermediario;

- **OS fingerprinting**, per dedurre il sistema operativo da differenze implementative;

- **nmap**, come strumento automatico di scansione;

- **traceroute**, per ricostruire il percorso dei pacchetti;

- contromisure con firewall, IDS/IPS, filtraggio e hardening.

> 📌 La scansione non riguarda solo "quali porte sono aperte": può rivelare topologia, filtri, sistemi operativi, versioni software e percorsi di rete.

---

### **2. UDP Scan**

#### **2.1. Principio**

La **UDP scan** invia datagrammi UDP verso una o più porte del target. Spesso il datagramma contiene **0 byte di dati**, ma può anche contenere un payload specifico per l'applicazione interrogata.

L'interpretazione tipica è:

|Risposta|Interpretazione|
|---|---|
|Risposta UDP applicativa|Porta aperta|
|ICMP Port Unreachable|Porta chiusa|
|Nessuna risposta|Porta aperta oppure filtrata|
|Altri ICMP unreachable / administratively prohibited|Porta filtrata|

La difficoltà principale è che molti servizi UDP non rispondono a pacchetti vuoti o generici. Quindi l'assenza di risposta non permette quasi mai di concludere con certezza che la porta sia chiusa.

#### **2.2. Perché è lenta**

La UDP scan è più lenta della scansione TCP perché lo scanner deve attendere timeout. Se non arriva risposta, bisogna distinguere tra più possibilità:

- la porta è aperta ma il servizio non risponde a quel payload;

- la porta è filtrata;

- il pacchetto o la risposta sono stati persi;

- l'ICMP di errore è stato limitato o bloccato.

#### **2.3. Probe applicativi**

Per aumentare l'affidabilità, spesso si usano pacchetti UDP specifici per il servizio atteso. Per esempio, se si vuole interrogare un DNS server sulla porta 53, conviene inviare una vera query DNS invece di un datagramma vuoto.

Questo riduce l'ambiguità perché un servizio UDP reale è più propenso a rispondere a una richiesta applicativa valida.

> ⚠️ Una scansione UDP efficace richiede conoscenza del protocollo applicativo. Il solo "pacchetto vuoto" spesso produce risultati `open|filtered`.

#### **2.4. Esempi operativi**

In laboratorio, una scansione UDP mirata può essere eseguita con `nmap` su un insieme ristretto di porte, perché una scansione UDP completa è molto lenta:

```bash
nmap -sU -p 53,123,161 192.168.1.0/24
```

Per servizi noti conviene usare probe applicativi o script specifici, per esempio una richiesta DNS su porta 53:

```bash
nmap --script=dns-recursion -p 53 <target>
```

---

### **3. FTP Bounce Scan**

#### **3.1. Richiamo al protocollo FTP**

FTP, **File Transfer Protocol**, è usato per trasferire file tra client e server. Storicamente usa due connessioni:

- una connessione di **controllo**, tipicamente sulla porta **21**, usata per inviare comandi;

- una connessione di **dati**, storicamente associata alla porta **20** lato server nella modalità attiva.

![](imgs/Pasted%20image%2020260709010828.png)

Nella modalità FTP attiva, il client può usare il comando `PORT` per indicare al server su quale indirizzo IP e su quale porta aprire la connessione dati.

#### **3.2. Meccanismo della scansione**

La **FTP bounce scan** sfrutta proprio il comando `PORT`. L'attaccante usa un server FTP come intermediario, in modo simile a uno zombie:

1. l'attaccante apre una sessione di controllo verso il server FTP;

2. invia un comando `PORT` indicando l'indirizzo e la porta della vittima;

3. chiede al server FTP di effettuare un trasferimento dati;

4. il server FTP prova a connettersi alla porta della vittima indicata;

5. dall'esito riportato nella sessione FTP, l'attaccante deduce se la porta della vittima è aperta o chiusa.

Se il server FTP riesce ad aprire la connessione dati, la porta della vittima è probabilmente aperta. Se il tentativo fallisce e il server FTP restituisce un errore, la porta può essere chiusa o filtrata.

![](imgs/Pasted%20image%2020260709010847.png)

#### **3.3. Vantaggi e limiti**

Vantaggi:

- la vittima vede traffico proveniente dal server FTP, non direttamente dall'attaccante;

- non servono raw socket o pacchetti TCP/IP costruiti manualmente;

- la tecnica sfrutta un comportamento applicativo legittimo del protocollo FTP.

Limiti:

- lascia tracce sul server FTP usato come intermediario;

- funziona solo con server FTP configurati in modo permissivo;

- è lenta;

- oggi molti server FTP impediscono questo abuso.

#### **3.4. Contromisure**

Le difese principali sono:

- rifiutare comandi `PORT` che indicano un indirizzo IP diverso da quello del client FTP;

- impedire che il server FTP apra connessioni verso porte privilegiate o inferiori a `1024`;

- disabilitare la modalità attiva se non necessaria;

- loggare e monitorare comandi `PORT` anomali.

> 📌 FTP bounce mostra che anche un protocollo applicativo legittimo può diventare un vettore di scansione se permette a un client di far connettere il server verso terzi.

---

### **4. OS Fingerprinting**

#### **4.1. Scopo**

L'**OS fingerprinting** cerca di determinare quale sistema operativo stia usando un host remoto. L'idea è che gli stack TCP/IP non sono implementati in modo perfettamente identico: sistemi operativi e versioni diverse rispondono in modo leggermente diverso a pacchetti normali o anomali.

Queste differenze vengono raccolte in database di fingerprint: osservando le risposte del target, lo scanner confronta il comportamento con profili noti.

#### **4.2. Fingerprinting attivo e passivo**

Esistono due approcci.

- **Fingerprinting attivo**: lo scanner invia pacchetti appositi, anche anomali, e osserva come il target risponde.

- **Fingerprinting passivo**: lo scanner osserva traffico reale già presente e deduce il sistema operativo senza generare pacchetti.

Il fingerprinting attivo è più ricco di informazioni ma più rilevabile. Quello passivo è meno intrusivo, ma dipende dal traffico disponibile.

#### **4.3. Segnali usati**

Esempi di segnali citati nel transcript:

- risposta a pacchetti `FIN` su porte chiuse;

- risposta a pacchetti con flag `FIN` e `SYN` contemporaneamente;

- comportamento davanti a flag TCP non usati o "bogus";

- dimensione della TCP Window;

- quantità di dati del pacchetto originale riportata dentro messaggi ICMP di errore;

- generazione degli Initial Sequence Number;

- andamento del campo IP Identification (`IP ID`).

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.4. Esempi di differenze implementative**

Alcuni esempi didattici:

- sistemi diversi possono rispondere o non rispondere a un pacchetto `FIN` inatteso;

- alcuni sistemi possono mantenere flag anomali anche nella risposta;

- sistemi come AIX, OpenBSD o FreeBSD possono mostrare valori di window size differenti;

- alcune implementazioni includono più di 8 byte del pacchetto originale nei messaggi ICMP di errore;

- pattern di incremento dell'IP ID o degli ISN possono suggerire una famiglia di sistemi operativi.

> ⚠️ Il fingerprinting non è una prova matematica: produce una stima basata su pattern. Firewall, NAT, proxy, normalizzatori e sistemi di deception possono alterare i risultati.

#### **4.5. Difese contro il fingerprinting**

Le contromisure includono:

- IDS/NIDS che rilevano probe anomali;

- firewall che filtrano pacchetti non standard;

- normalizzazione del traffico;

- riduzione dei banner applicativi;

- modifica o mascheramento della fingerprint;

- honeypot o sistemi di deception che restituiscono fingerprint ingannevoli.

---

### **5. nmap: Network Mapper**

#### **5.1. Funzione**

**nmap** sta per **Network Mapper** ed è uno strumento open source che automatizza molte tecniche di scansione viste nelle lezioni precedenti.

Può eseguire:

- host discovery;

- port scanning TCP e UDP;

- SYN scan, connect scan e ACK scan;

- version detection;

- OS detection;

- script di enumerazione e vulnerability checking tramite NSE;

- scansioni su un singolo host, su più host o su intere sottoreti.

L'output tipico è un inventario delle porte aperte, dei servizi disponibili e, quando possibile, del sistema operativo e delle versioni software.

#### **5.2. Esempi operativi**

Esempi tipici in un ambiente autorizzato o di laboratorio:

```bash
nmap -sS -sV -O -p 1-1024 10.0.0.5
nmap -sU -p 53,123 10.0.0.0/24
nmap --script vuln 10.0.0.5
```

Nel primo caso si combinano SYN scan, rilevamento versione e OS detection; nel secondo si esegue una scansione UDP mirata; nel terzo si usano script NSE orientati alla ricerca di vulnerabilità note.

#### **5.3. Rilevabilità**

nmap è molto usato anche dagli amministratori, ma proprio per questo molti IDS/IPS hanno regole per riconoscere pattern tipici delle sue scansioni. L'uso su sistemi non autorizzati può avere conseguenze legali e operative.

> 📌 nmap non è "l'attacco": è uno strumento. Il contesto e l'autorizzazione determinano se il suo uso è amministrazione, audit o ricognizione malevola.

---

### **6. Traceroute**

#### **6.1. Principio**

**Traceroute** ricostruisce il percorso seguito dai pacchetti verso una destinazione sfruttando il campo **TTL** degli header IP.

Il TTL, **Time To Live**, viene decrementato di 1 a ogni hop. Quando raggiunge 0, il router scarta il pacchetto e risponde con un messaggio ICMP **Time Exceeded**. Tale messaggio contiene l'indirizzo del router che ha scartato il pacchetto.

Traceroute invia pacchetti con TTL crescente:

1. TTL = 1: risponde il primo router;

2. TTL = 2: risponde il secondo router;

3. TTL = 3: risponde il terzo router;

4. e così via, fino alla destinazione.

In questo modo si ottiene la sequenza degli hop attraversati e spesso anche una misura dei tempi di risposta.

![](imgs/Pasted%20image%2020260709011130.png)

#### **6.2. Termine della scansione**

La procedura termina quando:

- arriva una risposta dalla destinazione finale;

- oppure si raggiunge un limite massimo di hop;

- oppure i pacchetti vengono filtrati e non si ricevono più risposte utili.

Se la destinazione non accetta il tipo di pacchetto usato, può arrivare un messaggio ICMP di porta non raggiungibile o un'altra indicazione di errore.

#### **6.3. Limiti e varianti**

Molti firewall bloccano ICMP o pacchetti usati da traceroute, perché rivelano informazioni sulla topologia interna della rete. Per questo esistono varianti:

- traceroute basato su UDP;

- traceroute basato su ICMP;

- **tcptraceroute**, che usa pacchetti TCP, spesso `SYN`, verso porte come 80 o 443 per attraversare filtri che lasciano passare traffico web.

> ⚠️ Traceroute può rivelare router, segmenti intermedi e tempi di attraversamento: per un difensore sono informazioni utili, ma per un attaccante sono dati di ricognizione.

#### **6.4. Esempi operativi**

Esempi di comandi usati in laboratorio:

```bash
traceroute <host>
traceroute -T -p 80 <host>
tcptraceroute <host> 80
```

La variante TCP verso porta 80 è utile quando i filtri bloccano UDP o ICMP ma lasciano passare traffico web.

---

### **7. Firewall, IDS e IPS**

#### **7.1. Rilevazione**

Le attività di scanning sono spesso riconoscibili perché producono pattern:

- molte porte interrogate in sequenza;

- molte destinazioni contattate sulla stessa porta;

- pacchetti con flag anomali;

- molte connessioni incomplete;

- molte risposte `RST`;

- ICMP o UDP ripetuti;

- probe riconducibili a tool noti come nmap.

Un **IDS** rileva e segnala questi pattern. Un **IPS** può anche bloccare o modificare il traffico in tempo reale.

#### **7.2. Dinamica attaccante-difensore**

Il transcript sottolinea l'alternanza tipica:

- gli scanner introducono tecniche nuove per aggirare firewall e IDS;

- i sistemi difensivi aggiornano regole, signature e normalizzazioni;

- gli scanner cambiano timing, flag, frammentazione, sorgenti e payload;

- i difensori correlano più segnali e introducono controlli stateful.

La scansione è quindi una fase preliminare dell'attacco, ma anche un comportamento che le difese moderne cercano attivamente di riconoscere e interrompere.

#### **7.3. Evasione firewall/IDS**

Tecniche di evasione ricorrenti sono:

- **frammentazione IP**, per spezzare i probe e rendere più difficile il matching delle signature se l'IDS non riassembla correttamente;

- **timing low-and-slow**, per diluire la scansione nel tempo ed evitare soglie banali;

- **payload obfuscation**, per cambiare contenuto e flag dei pacchetti senza cambiare lo scopo della sonda;

- **source distribution**, tramite molte sorgenti, proxy o botnet, per rendere meno evidente l'origine della ricognizione;

- **packet crafting**, cioè invio di pacchetti non standard per provocare risposte particolari negli stack TCP/IP.

Le contromisure coerenti sono ispezione stateful, normalizzazione e riassemblaggio prima dell'analisi, rate limiting, anomaly detection, honeypot/tarpit e, contro lo spoofing, ingress filtering secondo il principio BCP38.

---

### **8. Contromisure generali**

Le difese più importanti sono:

- **prevenzione**: disabilitare servizi non necessari e chiudere porte non usate;

- **filtraggio**: usare firewall, preferibilmente stateful, per accettare solo traffico coerente con connessioni legittime;

- **normalizzazione**: riassemblare frammenti e rimuovere ambiguità prima dell'ispezione;

- **rilevazione**: usare IDS/NIDS per riconoscere pattern di scansione;

- **prevenzione attiva**: usare IPS per bloccare traffico ritenuto malevolo;

- **blacklisting dinamico**: bloccare temporaneamente IP sorgente sospetti;

- **rate limiting**: limitare il numero di tentativi o pacchetti anomali;

- **hardening applicativo**: ridurre banner, versioni esposte e servizi pubblici;

- **logging centralizzato**: correlare eventi su firewall, host, server e IDS.

> 📌 La difesa migliore non è solo "bloccare lo scan": è ridurre le informazioni utili che uno scan può ottenere.

---

### **9. Sintesi finale**

|Tecnica|Scopo|Segnale osservato|Contromisure|
|---|---|---|---|
|UDP scan|Scoprire servizi UDP|Risposta UDP, ICMP Port Unreachable, timeout|Rate limit, filtri UDP/ICMP, probe applicativi controllati|
|FTP bounce|Usare FTP server come intermediario|Esito del comando/connessione dati|Bloccare `PORT` verso IP diversi dal client, limitare porte <1024|
|OS fingerprinting|Identificare OS remoto|Differenze nello stack TCP/IP|Normalizzazione, filtraggio, deception, riduzione banner|
|nmap|Automatizzare scanning|Pattern noti di probe|IDS/IPS, rate limit, logging|
|Traceroute|Mappare percorso di rete|ICMP Time Exceeded per TTL crescente|Filtrare ICMP/TTL basso, limitare risposte informative|

> ✅ Punto d'esame: le contromisure devono agire prima, durante e dopo la scansione: ridurre la superficie esposta, filtrare il traffico anomalo, rilevare pattern sospetti e reagire automaticamente quando l'attività diventa chiaramente ricognitiva.

In forma intuitiva:

- per mappare una rete si inviano sonde mirate e si interpretano risposte, errori e silenzi;

- le tecniche avanzate sfruttano sia protocolli applicativi, come FTP, sia differenze implementative degli stack;

- la difesa efficace è multilivello: riduzione della superficie, normalizzazione, rilevazione, blocco dello spoofing e deception quando utile.
