## **Lezione 6: Altri attacchi TCP**

### **1. Introduzione**

Dopo ACK storm e SYN flooding, rimangono altre forme di Denial of Service che sfruttano il normale comportamento dei protocolli di rete. Il filo conduttore è sempre lo stesso: pacchetti formalmente validi, spesso con indirizzi sorgente falsificati o generati da macchine controllate dall'attaccante, inducono host e server legittimi a consumare banda, CPU, memoria o capacità applicativa.

In questa lezione vengono analizzati:

- **TCP connection flood**, in cui le connessioni vengono completate davvero;

- attacchi basati su **junk-packet**, cioè pacchetti validi ma costruiti per provocare risposte automatiche;

- attacchi DoS su **UDP**, con il caso classico dell'**NTP amplification**;

- abuso di **ICMP**, in particolare **Smurf** e **Fraggle**.

> 📌 Questi attacchi mostrano un punto importante: non serve sempre "rompere" un protocollo. Spesso basta sfruttarne il comportamento corretto su grande scala o con indirizzi sorgente falsificati.

---

### **2. TCP Connection Flood**

#### **2.1. Idea di base**

Nel **TCP connection flood** l'attaccante non si limita a inviare `SYN` spoofati, come nel SYN flooding. Usa invece una rete di macchine compromesse, cioè una **botnet**, per aprire connessioni TCP reali verso il server.

Ogni macchina zombie:

1. avvia il three-way handshake;

2. completa la connessione con l'`ACK` finale;

3. eventualmente invia una richiesta applicativa, per esempio una richiesta HTTP verso un sito web;

4. ripete rapidamente l'operazione.

Poiché le connessioni vengono effettivamente iniziate e completate, il traffico ha lo stesso aspetto di molte connessioni legittime. Questo rende l'attacco più difficile da filtrare rispetto a un SYN flood classico.

#### **2.2. Perché supera alcune difese anti-SYN flood**

Le difese come SYN cookies, SYN cache o proxy pensati per filtrare connessioni incomplete sono efficaci quando l'attaccante non completa l'handshake. Nel TCP connection flood, invece, i bot rispondono davvero al `SYN/ACK` e portano la connessione allo stato `ESTABLISHED`.

Di conseguenza:

- il server o il proxy non può scartare la connessione solo perché l'handshake è incompleto;

- le richieste applicative possono sembrare normali, soprattutto se sono semplici e ripetitive;

- il carico si sposta dalla backlog TCP alle risorse applicative: socket, thread/processi, CPU, memoria, banda e capacità del web server.

> ⚠️ L'attacco è meno "anonimo" del SYN flood spoofato: le macchine zombie usano i propri indirizzi IP reali e possono quindi essere identificate, filtrate o segnalate. Il vantaggio dell'attaccante è il volume distribuito, non la perfetta invisibilità.

#### **2.3. Potenziale di traffico**

Il docente cita come ordine di grandezza una botnet da circa **20.000 host**, capace di generare traffico dell'ordine di **2 Gbit/s**. Anche se ogni singola macchina produce poco traffico, l'aggregazione rende il flusso complessivo sufficiente a mettere in crisi un server o la sua connettività.

Il punto critico è che i `SYN` e le connessioni di attacco hanno lo stesso formato dei `SYN` e delle connessioni di client reali. La distinzione deve quindi basarsi su segnali indiretti: frequenza, distribuzione geografica, pattern identici, numero di connessioni per indirizzo, comportamento applicativo e reputazione degli IP.

---

### **3. Attacchi basati su junk-packet**

#### **3.1. Definizione**

Con **junk-packet** si indicano pacchetti apparentemente ordinari e formalmente validi, ma costruiti per provocare una reazione automatica nella vittima o in un host intermedio. Non sono necessariamente pacchetti "malformati": spesso rispettano il formato del protocollo, ma vengono inviati in grandi quantità, con flag particolari o con indirizzo sorgente spoofato.

L'obiettivo può essere:

- costringere la vittima a generare risposte inutili;

- creare traffico di ritorno verso un indirizzo spoofato;

- sfruttare la risposta standard dello stack TCP/IP;

- contribuire a un attacco di reflection o amplification.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Reazioni tipiche degli stack di rete**

La risposta dipende dal protocollo, dal flag e dallo stato della porta:

|Pacchetto ricevuto|Contesto tipico|Risposta possibile|
|---|---|---|
|`SYN` verso porta aperta|Servizio in ascolto|`SYN/ACK`|
|`SYN` verso porta chiusa|Nessun servizio in ascolto|`RST`|
|`ACK` inatteso|Nessuna connessione corrispondente|`RST` o scarto|
|`RST`|Reset di connessione|Di norma nessuna risposta|
|UDP verso porta chiusa|Nessun servizio UDP|ICMP Port Unreachable|
|ICMP Echo Request|Host raggiungibile e configurato per rispondere|ICMP Echo Reply|

![](imgs/Pasted%20image%2020260708222556.png)

Queste reazioni, innocue in condizioni normali, diventano pericolose quando l'attaccante può generare grandi volumi di pacchetti con indirizzi sorgente falsificati.

#### **3.3. Perché sono facili da produrre**

La generazione di pacchetti TCP, UDP o ICMP con campi arbitrari è tecnicamente semplice per chi ha accesso a raw socket, librerie di packet crafting o strumenti equivalenti. L'attaccante può variare indirizzi, porte, flag e payload, producendo flussi molto grandi a basso costo.

Le misure mostrate a lezione evidenziano che questi attacchi non sono solo storici: continuano a comparire in traffico reale, con flussi basati su `SYN`, ICMP e UDP osservati su base giornaliera.

> 📌 Il punto d'esame non è il singolo pacchetto, ma la scala: un comportamento di risposta normale può diventare un DoS quando viene moltiplicato per migliaia o milioni di pacchetti.

---

### **4. UDP: proprietà e rischio**

#### **4.1. Caratteristiche di UDP**

UDP è un protocollo di trasporto:

- **connectionless**, cioè senza instaurazione di connessione;

- **stateless**, perché non mantiene uno stato di sessione come TCP;

- privo di garanzie di consegna;

- privo di garanzie sull'ordine dei datagrammi;

- privo di controllo di flusso e congestione paragonabile a TCP;

- privo di autenticazione nativa del mittente.

![](imgs/Pasted%20image%2020260708222624.png)

Per queste ragioni è usato in applicazioni che privilegiano semplicità e velocità, come streaming multimediale, trasmissioni real-time, DNS, NTP e vari protocolli di segnalazione.

#### **4.2. Perché UDP è utile negli attacchi DoS**

L'assenza di handshake rende lo spoofing più semplice: un datagramma UDP può essere inviato con indirizzo sorgente falsificato e il destinatario, se il servizio risponde, invierà la risposta all'indirizzo indicato come sorgente.

Questo crea due condizioni favorevoli all'attaccante:

- **reflection**: la risposta va alla vittima, non all'attaccante;

- **amplification**: se la risposta è più grande della richiesta, la vittima riceve più traffico di quanto l'attaccante abbia generato.

---

### **5. NTP Amplification**

#### **5.1. NTP e funzione `monlist`**

Il **Network Time Protocol** (`NTP`) è un protocollo basato su UDP usato per sincronizzare gli orologi dei sistemi. Normalmente un client invia una piccola richiesta e il server risponde con informazioni temporali.

Il problema storico nasce da alcune vecchie implementazioni che esponevano anche funzioni di monitoraggio, tra cui `monlist`. Tale comando restituiva la lista degli ultimi host che avevano interrogato il server NTP, fino a circa **600** voci.

#### **5.2. Meccanismo dell'attacco**

L'attacco funziona così:

1. l'attaccante invia a un server NTP una richiesta `monlist`;

2. nel pacchetto inserisce come IP sorgente l'indirizzo della vittima;

3. il server NTP risponde alla vittima, non all'attaccante;

4. la risposta contiene una lista molto più grande della richiesta iniziale;

5. ripetendo il processo su molti server NTP esposti, la vittima riceve un volume enorme di traffico.

![](imgs/Pasted%20image%2020260708222705.png)

#### **5.3. Reflection e amplification insieme**

Questo attacco combina due proprietà:

- è un attacco di **reflection**, perché il server NTP funge da reflector e invia la risposta alla vittima;

- è un attacco di **amplification**, perché la risposta è molto più grande della richiesta.

Nel transcript viene citato l'ordine di grandezza classico: una richiesta di circa **234 byte** può provocare una risposta dell'ordine di **49 KB**. Anche se il fattore di amplificazione effettivo dipende da implementazione, configurazione e modo di misurare il traffico, il principio è chiaro: una piccola quantità di traffico in uscita dall'attaccante può generare un volume molto maggiore verso la vittima.

#### **5.4. Casi reali**

Tra dicembre 2013 e febbraio 2014 furono osservati grandi attacchi DDoS basati su NTP amplification. Il docente cita uno scenario con migliaia di server NTP coinvolti, circa **4529** server nella slide, e volumi complessivi fino all'ordine di **centinaia di Gbit/s**.

Nelle stime aggregate riportate a lezione, poche centinaia di migliaia di richieste potevano tradursi in centinaia di Gbit/s diretti contro la vittima. La slide cita anche un fattore di amplificazione dell'ordine di **19×** in uno degli scenari: il valore preciso dipende dal tipo di richiesta, dalla risposta generata e dal criterio con cui si misura il traffico, ma il punto essenziale è che l'attaccante spende molta meno banda di quella che fa arrivare alla vittima.

Il motivo della gravità era la grande quantità di server NTP mal configurati o non aggiornati esposti su Internet. Il transcript cita circa **7 milioni** di server vulnerabili o non adeguatamente protetti come superficie potenziale.

> ⚠️ L'NTP amplification è un esempio perfetto di cattiva esposizione di un servizio legittimo: il server NTP non è "malevolo", ma viene abusato come amplificatore.

#### **5.5. Contromisure**

Le difese principali sono:

- disabilitare `monlist` e funzioni di monitoraggio non necessarie;

- aggiornare NTP a versioni che rimuovono o limitano tali comandi;

- applicare rate limiting alle risposte;

- evitare che server pubblici rispondano indiscriminatamente a richieste di monitoraggio;

- applicare filtri anti-spoofing a livello ISP, secondo il principio di BCP38.

---

### **6. ICMP: funzioni e struttura**

#### **6.1. Scopo di ICMP**

ICMP, **Internet Control Message Protocol**, è un protocollo di controllo usato da host e router per fornire feedback sullo stato della rete. Non serve a trasportare dati applicativi, ma a segnalare condizioni operative come errori, host non raggiungibili, scadenza del TTL o tempi di risposta.

Esempi tipici:

- `ping`, basato su Echo Request ed Echo Reply;

- `Destination Host Unreachable`;

- `Destination Network Unreachable`;

- `Time Exceeded`, per esempio quando il TTL scende a zero;

- messaggi di diagnostica e segnalazione usati da host e router.

#### **6.2. Formato dei messaggi**

I messaggi ICMP sono trasportati dentro datagrammi IP. La struttura include almeno:

- un campo **Type**, che identifica la categoria del messaggio;

- un campo **Code**, che specifica il caso particolare;

- un contenuto che spesso include parte del datagramma originale che ha generato l'errore.

![](imgs/Pasted%20image%2020260708222750.png)

Nel caso di `ping`:

- Echo Request: `Type = 8`, `Code = 0`;

- Echo Reply: `Type = 0`, `Code = 0`.

ICMP è quindi fondamentale per il funzionamento e la diagnostica della rete, ma può essere abusato quando host o router rispondono automaticamente a richieste costruite con IP sorgente falsificato.

---

### **7. Smurf e Fraggle**

#### **7.1. Smurf attack**

Lo **Smurf attack** sfrutta ICMP Echo Request indirizzate al broadcast di una rete. L'attaccante invia un pacchetto `ping` verso l'indirizzo broadcast di una sottorete, ma inserisce come IP sorgente quello della vittima.

Se la rete inoltra il broadcast e gli host rispondono:

1. ogni host della sottorete riceve l'Echo Request;

2. ogni host genera un Echo Reply;

3. tutte le risposte vengono inviate alla vittima;

4. la vittima viene inondata da un traffico amplificato.

![](imgs/Pasted%20image%2020260708222816.png)

Il fattore di amplificazione dipende dal numero di host che rispondono al broadcast. Una sola richiesta può quindi produrre decine, centinaia o migliaia di risposte.

#### **7.2. Fraggle**

**Fraggle** è una variante dello stesso principio basata su UDP invece che su ICMP. L'attaccante invia pacchetti UDP verso un indirizzo di broadcast, con IP sorgente impostato alla vittima. I servizi UDP raggiunti dagli host della rete rispondono alla vittima, generando traffico riflesso.

#### **7.3. Prevenzione**

La prevenzione essenziale consiste nel bloccare l'abuso del broadcast:

- rifiutare pacchetti provenienti dall'esterno e diretti a indirizzi di broadcast interni;

- disabilitare la risposta a richieste ICMP broadcast quando non necessaria;

- filtrare traffico con IP sorgente falsificato;

- configurare router e firewall affinché non inoltrino directed broadcast dall'esterno.

> 📌 Smurf e Fraggle sono casi didatticamente importanti perché mostrano come un normale meccanismo di broadcast possa trasformare una singola richiesta in molte risposte verso la vittima.

---

### **8. Reflection vs amplification**

È utile distinguere i due concetti:

- **reflection**: l'attaccante non invia traffico direttamente alla vittima, ma a un reflector; usando IP spoofing, fa sì che la risposta del reflector vada alla vittima;

- **amplification**: la risposta generata dal reflector è più grande della richiesta iniziale.

Un attacco può essere solo riflesso, solo amplificato o entrambe le cose. NTP amplification e Smurf combinano entrambi gli aspetti: il traffico è riflesso verso la vittima e il volume finale è maggiore di quello inviato dall'attaccante.

---

### **9. Strategie di difesa**

Le contromisure vanno applicate a più livelli.

#### **9.1. Lato rete e ISP**

- Applicare ingress/egress filtering per impedire IP spoofing.

- Usare sistemi di rilevazione e mitigazione DDoS, scrubbing center e rate limiting.

- Bloccare traffico diretto a broadcast interni quando arriva dall'esterno.

#### **9.2. Lato server e servizi**

- Aggiornare servizi esposti, in particolare UDP.

- Disabilitare funzioni di monitoraggio non necessarie, come `monlist`.

- Limitare la dimensione e la frequenza delle risposte.

- Usare reverse proxy, CDN o WAF per assorbire o filtrare traffico anomalo.

#### **9.3. Lato host**

- Limitare risposte ICMP non necessarie.

- Monitorare anomalie con NetFlow, sFlow, log firewall e metriche applicative.

- Applicare policy di rate limiting per IP, prefisso o comportamento.

---

### **10. Sintesi finale**

|Attacco|Vettore|Meccanismo|Effetto|Contromisure|
|---|---|---|---|---|
|TCP connection flood|TCP completo|Bot completano handshake e generano richieste|Saturazione risorse applicative|Rate limiting, WAF, proxy, analisi comportamentale|
|Junk-packet|TCP/UDP/ICMP|Pacchetti validi provocano risposte automatiche|Traffico inutile o riflesso|Filtri, rate limiting, hardening stack|
|NTP amplification|UDP/NTP|`monlist` con IP sorgente spoofato|Reflection + amplification|Disabilitare `monlist`, aggiornare NTP, BCP38|
|Smurf|ICMP broadcast|Echo Request broadcast con sorgente vittima|Molte Echo Reply verso la vittima|Bloccare directed broadcast, anti-spoofing|
|Fraggle|UDP broadcast|Pacchetti UDP broadcast con sorgente vittima|Risposte UDP riflesse|Bloccare broadcast esterni, filtrare servizi UDP|

> ✅ Punto d'esame: la difesa non può essere solo locale. Reflection e amplification si risolvono davvero solo se i reflector vengono configurati correttamente e se la rete impedisce lo spoofing degli indirizzi sorgente.
