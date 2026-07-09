## **Lezione 2: Tecniche di scansione**

### **1. Introduzione**

Questa lezione entra nel dettaglio delle tecniche usate per raccogliere informazioni su host, porte e servizi. Dopo aver classificato gli approcci generali alla scansione, ora si osservano i pacchetti concreti che uno scanner può inviare e il modo in cui interpreta le risposte.

L'idea di base è sempre la stessa: lo scanner invia una "domanda" alla rete e deduce informazioni dalla risposta, dalla mancata risposta o dal tipo di errore ricevuto. A seconda del protocollo usato, la domanda può essere una richiesta ARP, un messaggio ICMP, un segmento TCP, un datagramma UDP o un pacchetto IP con un particolare protocol number.

> 📌 Una tecnica di scansione non identifica necessariamente subito "il servizio vulnerabile": spesso prima stabilisce solo se l'host è attivo, se una porta è raggiungibile o se un firewall sta filtrando il traffico.

---

### **2. Stati risultanti da una scansione**

Il risultato di una scansione di porta viene classificato in base alla risposta osservata.

- **Open / aperta**: il target risponde in modo compatibile con un servizio attivo sulla porta interrogata. Nel caso TCP, per esempio, un `SYN/ACK` in risposta a un `SYN` indica una porta aperta.

- **Closed / chiusa**: la porta è raggiungibile, ma non c'è un servizio in ascolto. Nel caso TCP, spesso si osserva un `RST`.

- **Filtered / filtrata**: lo scanner non riesce a stabilire lo stato reale perché un firewall o un dispositivo di filtraggio blocca il traffico o le risposte.

Questa classificazione può essere raffinata:

- **unfiltered**: la porta è raggiungibile, ma la tecnica usata non consente di decidere se sia aperta o chiusa;

- **open|filtered**: la porta potrebbe essere aperta oppure filtrata;

- **closed|filtered**: la porta potrebbe essere chiusa oppure filtrata.

> ⚠️ La mancata risposta non significa automaticamente "porta chiusa": può indicare un filtro, un firewall, una policy di drop silenzioso o un host che non risponde a quel tipo di probe.

---

### **3. Host discovery con ARP**

#### **3.1. Scenario**

L'**ARP scan** funziona sulla rete locale. Lo scanner deve trovarsi nella stessa LAN o nello stesso dominio di broadcast degli host da individuare.

ARP serve a risolvere un indirizzo IP nel corrispondente indirizzo MAC. Per scoprire quali dispositivi sono attivi nella sottorete, lo scanner invia richieste ARP in broadcast per una sequenza di indirizzi IP, per esempio incrementando l'ultimo ottetto da `0` a `255`.

Gli host attivi rispondono con una **ARP reply**, dalla quale lo scanner ricava:

- indirizzo IP attivo;

- indirizzo MAC;

- talvolta informazioni indirette sul vendor della scheda di rete, tramite OUI del MAC address.

![](imgs/Pasted%20image%2020260709005848.png)

#### **3.2. Proprietà**

L'ARP scan è molto efficace in LAN perché non dipende da TCP, UDP o ICMP: se un host deve comunicare nella rete locale, deve normalmente rispondere ad ARP.

Limiti:

- non funziona oltre il router, perché ARP non attraversa le reti IP diverse;

- può essere rilevato da strumenti di monitoraggio locali;

- richiede accesso alla rete locale.

---

### **4. ICMP ping e varianti**

#### **4.1. Ping classico**

Il metodo più noto per verificare se un host è attivo è `ping`, basato su ICMP:

1. lo scanner invia un **ICMP Echo Request**;

2. il target, se raggiungibile e configurato per rispondere, invia un **ICMP Echo Reply**;

3. lo scanner misura anche il tempo di risposta, tipicamente in millisecondi.

![](imgs/Pasted%20image%2020260709005915.png)

Il tempo di risposta può dare indicazioni sullo stato della rete: latenza, congestione o distanza approssimativa del target.

#### **4.2. Filtraggio di ICMP**

Molte reti filtrano ICMP Echo Request, soprattutto se proviene dall'esterno. Questo genera falsi negativi: un host può essere attivo anche se non risponde al ping.

Per aggirare filtri troppo semplici, alcuni scanner usano messaggi ICMP diversi dall'Echo Request, per esempio:

- **ICMP Timestamp Request**;

- **ICMP Address Mask Request**.

Alcune regole firewall controllano solo Echo Request/Echo Reply e possono lasciar passare altri tipi ICMP. Se l'host risponde, lo scanner ottiene comunque un'indicazione di attività.

> ⚠️ ICMP filtrato non significa host spento. Significa solo che quel tipo di messaggio non produce una risposta osservabile.

---

### **5. TCP e UDP ping**

#### **5.1. TCP SYN ping**

Il **TCP SYN ping** invia un segmento `SYN` verso una porta nota, per esempio una porta su cui è probabile trovare un servizio. Lo scopo non è ancora completare una scansione dettagliata delle porte, ma capire se l'host risponde.

Possibili risposte:

- `SYN/ACK`: la porta è aperta e l'host è attivo;

- `RST`: la porta è chiusa, ma l'host è comunque attivo;

- nessuna risposta o ICMP unreachable: il traffico può essere filtrato.

In discovery, sia `SYN/ACK` sia `RST` sono utili: anche una porta chiusa dimostra che l'host ha ricevuto il pacchetto e ha reagito.

#### **5.2. TCP ACK ping**

Il **TCP ACK ping** invia un segmento con flag `ACK` verso il target. Se non esiste una connessione TCP corrispondente, uno stack raggiungibile tende a rispondere con `RST`.

Questo può essere utile quando regole firewall semplici bloccano i `SYN`, ma non filtrano pacchetti `ACK`. In quel caso, l'ACK ping può rivelare che l'host è attivo anche se il SYN ping non ha prodotto risposta.

> 📌 Il TCP ACK ping di solito non serve a dire se la porta è aperta: serve soprattutto a capire se l'host è raggiungibile e se il traffico è filtrato.

#### **5.3. UDP ping**

Nel **UDP ping** lo scanner invia un datagramma UDP verso una porta del target. Se la porta è chiusa, lo stack IP dovrebbe rispondere con un messaggio ICMP **Port Unreachable**.

Interpretazione:

- ICMP Port Unreachable: l'host è attivo, ma quella porta UDP è chiusa;

- risposta applicativa UDP: porta aperta o servizio attivo;

- nessuna risposta: stato ambiguo, perché la porta potrebbe essere aperta, filtrata o il pacchetto/errore potrebbe essere stato bloccato.

#### **5.4. IP protocol ping**

Un datagramma IP contiene un campo **Protocol** che indica quale protocollo è incapsulato, per esempio TCP, UDP, ICMP, IGMP e così via. Una tecnica di discovery consiste nell'inviare pacchetti IP con un protocol number che probabilmente non è supportato dal target.

Se l'host è attivo ma non supporta quel protocollo, può rispondere con ICMP **Protocol Unreachable**. Anche in questo caso lo scanner non scopre necessariamente una porta, ma ricava un'informazione sull'esistenza dell'host.

---

### **6. Porte TCP/UDP e intervalli**

Le porte sono identificate da un numero a **16 bit**, quindi vanno da `0` a `65535`.

Gli intervalli principali sono:

- **well-known ports**: da `0` a `1023`, riservate ai servizi standard;

- **registered ports**: da `1024` a `49151`, assegnate o registrate per applicazioni specifiche;

- **dynamic/private ports**: da `49152` a `65535`, usate tipicamente come porte effimere lato client.

Esempi di porte note:

|Porta|Servizio|
|---:|---|
|21|FTP|
|22|SSH|
|25|SMTP|
|53|DNS|
|80|HTTP|
|443|HTTPS|

La scansione delle porte serve a capire quali servizi sono in ascolto e quindi quali software possono essere interrogati, identificati o eventualmente attaccati.

![](imgs/Pasted%20image%2020260709010017.png)

---

### **7. Regole TCP utili per la scansione**

Molte tecniche di port scanning sfruttano il comportamento previsto dallo standard TCP.

Regole essenziali:

1. Se arriva un segmento con flag `RST`, normalmente viene scartato senza generare risposta.

2. Se una porta è nello stato **closed** e arriva un segmento senza `RST`, il target risponde con `RST`.

3. Se una porta è in stato **LISTEN**:

   - un `SYN` produce un `SYN/ACK`;

   - un `ACK` non associato a una connessione produce un `RST`;

   - segmenti non validi o inattesi possono essere scartati senza risposta.

Queste regole sono alla base di scansioni `SYN`, `ACK`, `FIN`, `NULL`, `Xmas` e di molte tecniche di fingerprinting.

> 📌 Lo scanner non "legge" direttamente lo stato della porta: lo inferisce osservando come lo stack TCP reagisce a segmenti costruiti apposta.

---

### **8. TCP Connect Scan**

La **TCP Connect Scan** usa la normale chiamata di sistema `connect()` per aprire una connessione TCP verso una porta del target.

Lo scanner lascia che sia il sistema operativo a gestire l'handshake:

1. invio di `SYN`;

2. ricezione eventuale di `SYN/ACK`;

3. invio dell'`ACK` finale;

4. connessione completata.

![](imgs/Pasted%20image%2020260709010048.png)

Interpretazione:

- `SYN/ACK` e connessione riuscita: porta aperta;

- `RST`: porta chiusa;

- nessuna risposta o ICMP unreachable: porta filtrata o host non raggiungibile.

![](imgs/Pasted%20image%2020260709010137.png)

Vantaggi:

- non richiede necessariamente privilegi speciali o raw socket;

- è semplice da implementare;

- usa lo stack TCP del sistema operativo.

Svantaggi:

- completa davvero la connessione;

- è facilmente loggata dal servizio o dal sistema operativo;

- è più rumorosa e intrusiva rispetto a tecniche half-open.

---

### **9. TCP SYN Scan**

La **TCP SYN Scan** è detta anche **half-open scan** perché avvia l'handshake ma non lo completa.

Procedura:

1. lo scanner invia un `SYN`;

2. se riceve `SYN/ACK`, conclude che la porta è aperta;

3. invece di completare la connessione con `ACK`, invia un `RST` per interrompere la sessione;

4. se riceve `RST`, conclude che la porta è chiusa;

5. se non riceve risposta o riceve messaggi di errore, interpreta la porta come filtrata o ambigua.

![](imgs/Pasted%20image%2020260709010222.png)

Rispetto alla connect scan, la SYN scan è meno intrusiva perché non stabilisce completamente la connessione. Tuttavia non è invisibile: i `SYN` possono essere registrati da firewall, IDS/IPS o log dello stack TCP.

|Tecnica|Connessione completata?|Rumorosità|Requisiti|
|---|---|---|---|
|`connect()` scan|Sì|Alta|Privilegi normali|
|SYN scan|No, half-open|Media|Raw packet / privilegi elevati|

---

### **10. Version detection e fingerprinting**

Una volta trovata una porta aperta, lo scanner può raccogliere informazioni più specifiche sul servizio.

![](imgs/Pasted%20image%2020260709010253.png)

Le fonti informative includono:

- banner applicativi;

- risposte HTTP, SMTP, FTP, SSH o DNS;

- tempi di risposta;

- differenze nel comportamento dello stack TCP/IP;

- messaggi di errore;

- campi e opzioni nei pacchetti.

Queste informazioni possono rivelare:

- software in uso;

- versione del server;

- librerie o stack di rete;

- sistema operativo probabile;

- configurazioni applicative.

Esempio: una risposta HTTP può indicare il tipo e la versione del web server; una risposta TCP può mostrare pattern compatibili con uno specifico sistema operativo.

> ⚠️ Più lo scanner interroga i servizi a livello applicativo, più aumenta la quantità di informazioni raccolte, ma anche la probabilità di lasciare tracce nei log.

---

### **11. UDP port scanning**

La scansione UDP è più ambigua di quella TCP, perché UDP non ha handshake.

Lo scanner invia un datagramma UDP e osserva:

- risposta applicativa UDP: porta aperta;

- ICMP Port Unreachable: porta chiusa;

- nessuna risposta: porta aperta, filtrata o pacchetto perso.

Per questo le scansioni UDP sono spesso più lente: lo scanner deve attendere timeout e distinguere, per quanto possibile, tra silenzio legittimo, filtro e perdita.

---

### **12. Log e rilevazione**

Tutte le tecniche attive possono lasciare tracce:

- connessioni completate nei log applicativi;

- tentativi di connessione falliti;

- sequenze di `SYN` verso molte porte;

- ICMP o UDP ripetuti verso molti host;

- pattern riconoscibili da IDS/IPS.

La connect scan è in genere la più evidente perché completa l'handshake e può essere registrata dal servizio. La SYN scan è meno evidente, ma non invisibile. Le tecniche di discovery possono comunque essere rilevate se producono pattern anomali o ripetitivi.

---

### **13. Sintesi finale**

|Tecnica|Obiettivo|Risposta utile|Informazione ricavata|
|---|---|---|---|
|ARP scan|Host discovery in LAN|ARP reply|Host attivo e MAC address|
|ICMP ping|Host discovery|Echo Reply|Host raggiungibile e latenza|
|ICMP alternativi|Host discovery evasiva|Timestamp/Address Mask reply|Host attivo se Echo è filtrato|
|TCP SYN ping|Host discovery|`SYN/ACK` o `RST`|Host attivo|
|TCP ACK ping|Host discovery / filtri|`RST`|Host raggiungibile o non filtrato|
|UDP ping|Host discovery|ICMP Port Unreachable|Host attivo|
|IP protocol ping|Host discovery|ICMP Protocol Unreachable|Host attivo|
|TCP connect scan|Port scanning|Connessione riuscita / `RST`|Porta aperta o chiusa|
|TCP SYN scan|Port scanning half-open|`SYN/ACK`, `RST`, timeout|Porta aperta, chiusa o filtrata|
|UDP scan|Port scanning UDP|Risposta UDP / ICMP errore / timeout|Stato spesso ambiguo|

> ✅ Punto d'esame: una scansione non interpreta solo le risposte positive. Anche `RST`, ICMP unreachable e assenza di risposta sono segnali: la difficoltà è capire se indicano porta chiusa, host attivo, filtro o ambiguità.
