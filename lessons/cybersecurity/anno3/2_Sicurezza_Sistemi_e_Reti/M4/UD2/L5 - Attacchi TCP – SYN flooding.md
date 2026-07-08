## **Lezione 5: Attacchi TCP – SYN flooding**

### **1. Introduzione**

Il **SYN flooding** è un attacco Denial of Service contro la fase di apertura di una connessione TCP. L'idea è semplice: l'attaccante inonda un server con molti pacchetti `SYN`, spesso usando indirizzi sorgente contraffatti o generati casualmente, così da costringere il server a registrare un grande numero di tentativi di connessione non completati.

Il punto debole nasce dal normale funzionamento del three-way handshake:

1. il client invia `SYN`;

2. il server risponde con `SYN/ACK`;

3. il client completa l'handshake con `ACK`;

4. solo dopo questo scambio la connessione passa allo stato stabilito.

![](imgs/Pasted%20image%2020260708221323.png)

Durante il passaggio intermedio, però, il server deve già ricordare che esiste una connessione in corso. Per questo crea uno stato parziale, tipicamente nello stato `SYN-RECEIVED`, e lo conserva in una **backlog queue** fino alla ricezione dell'`ACK` finale o fino al timeout.

> 📌 Il bersaglio del SYN flooding non è innanzitutto il contenuto applicativo, ma la capacità del server di mantenere stati TCP parziali per connessioni ancora incomplete.

---

### **2. Meccanismo operativo**

In un attacco SYN flood l'attaccante invia una grande quantità di pacchetti `SYN` verso un servizio in ascolto, per esempio un server web sulla porta 80. Ogni pacchetto può contenere un indirizzo sorgente diverso e falsificato.

Il server, non potendo sapere a priori se il tentativo sia legittimo o malevolo:

1. registra il tentativo di connessione nella backlog;

2. alloca o prepara una struttura di stato associata alla connessione parziale;

3. invia un `SYN/ACK` all'indirizzo sorgente indicato nel pacchetto;

4. attende l'`ACK` finale.

Se l'indirizzo sorgente era spoofato, il `SYN/ACK` raggiunge un host che non ha mai iniziato quella connessione. Tale host può scartarlo, ignorarlo o rispondere con `RST`; in ogni caso l'attaccante non completa l'handshake. Il server, invece, mantiene lo stato parziale fino al timeout.

Ripetendo questo comportamento migliaia di volte, la backlog queue si riempie. Quando la coda è satura, il server non riesce più a registrare nuovi tentativi e può rifiutare o ignorare connessioni legittime.

![](imgs/Pasted%20image%2020260708221347.png)

> ⚠️ La difficoltà difensiva è strutturale: un server pubblico deve accettare connessioni da client sconosciuti. Prima del completamento dell'handshake, distinguere un `SYN` legittimo da un `SYN` spoofato può essere impossibile.

---

### **3. Perché è efficace: asimmetria tra costo dell'attaccante e costo del server**

Il SYN flooding è efficace perché produce una forte **asimmetria di costo**:

- l'attaccante invia un pacchetto molto piccolo, spesso circa **40 byte** a livello IP/TCP senza opzioni;

- il server deve riservare memoria e stato per una connessione parziale;

- ogni entry del `TCB` o della struttura equivalente può richiedere almeno alcune centinaia di byte, per esempio circa **280 byte**, e in alcune implementazioni può arrivare anche a circa **1300 byte**.

Questa sproporzione permette a un attaccante con poca banda di consumare memoria e capacità di gestione del server. Il problema è aggravato dal fatto che lo stato `SYN-RECEIVED` non viene eliminato immediatamente: il server deve aspettare un timeout, perché un client legittimo potrebbe essere semplicemente lento o trovarsi su una rete congestionata.

> 📌 L'attacco sfrutta il fatto che il server paga il costo dello stato prima di sapere se il client esiste davvero e completerà l'handshake.

---

### **4. Esempi storici e impatto**

Un caso classico è l'attacco contro **Panix**, provider di New York, nel 1996. Il servizio fu sommerso da falsi tentativi di connessione e gli utenti legittimi non riuscivano più ad accedere normalmente, perché le risorse dedicate alle connessioni pendenti venivano consumate dai pacchetti `SYN` malevoli.

Un altro esempio è il worm **MS Blaster** del 2003, che generava SYN flood verso la porta 80 di `windowsupdate.com`. Il worm produceva circa **50 pacchetti al secondo**, ciascuno di circa **40 byte**, usando indirizzi IP sorgente casuali. Una mitigazione pragmatica adottata da Microsoft fu cambiare il riferimento usato per il servizio di aggiornamento, spostandolo verso `windowsupdate.microsoft.com`, così da rendere inefficace il dominio hardcoded dal worm.

Il caso dell'**Estonia** mostra invece l'uso politico di attacchi DoS e DDoS. In seguito a tensioni legate alla rimozione di simboli del precedente potere sovietico, diversi siti istituzionali e governativi estoni furono colpiti da traffico massivo, inclusi SYN flood e ICMP flood. Una mitigazione adottata fu filtrare o bloccare parte del traffico proveniente dall'estero, mantenendo accessibili i servizi almeno ai cittadini e agli utenti interni al paese.

![](imgs/Pasted%20image%2020260708221411.png)

Questi esempi mostrano che l'impatto non è soltanto tecnico: un SYN flood può rendere indisponibili posta, aggiornamenti software, siti istituzionali e servizi essenziali.

---

### **5. Parametri dell'attacco e stima della banda necessaria**

Per capire quanto traffico serva a mantenere un server sotto attacco, bisogna conoscere almeno:

- la dimensione della backlog queue;

- il timeout dello stato `SYN-RECEIVED`;

- la dimensione media dello stato allocato o della entry associata al tentativo;

- la dimensione dei pacchetti `SYN` inviati dall'attaccante.

Supponiamo, come nell'esempio discusso a lezione, una backlog di **128 entry** e pacchetti `SYN` da **40 byte**. Per riempire inizialmente la coda bastano:

$$
128 \cdot 40 = 5120 \text{ byte}
$$

Se il timeout dello stato parziale è circa **189 secondi**, l'attaccante deve poi mantenere piena la coda reinviando traffico prima che le vecchie entry scadano:

$$
\frac{5120}{189} \simeq 27 \text{ byte/s}
$$

La quantità è sorprendentemente bassa. Anche aumentando la backlog, l'ordine di grandezza resta gestibile per l'attaccante: con **2048 entry** servono

$$
2048 \cdot 40 = 81920 \text{ byte}
$$

che, distribuiti su un timeout dell'ordine di pochi minuti, corrispondono a poche centinaia di byte al secondo.

![](imgs/Pasted%20image%2020260708221444.png)

> ⚠️ Aumentare la backlog sposta la soglia, ma non elimina la vulnerabilità: l'attaccante può aumentare il numero di `SYN`, mentre il server continua a dover mantenere stato per richieste non ancora verificate.

---

### **6. Contromisure lato end-host**

Le difese più immediate sono:

- **aumentare la dimensione della backlog**, così da tollerare più connessioni half-open;

- **ridurre il timeout** dello stato `SYN-RECEIVED`, liberando prima le entry incomplete;

- usare **SYN cache**, cioè strutture più leggere del `TCB` completo per memorizzare provvisoriamente i tentativi;

- usare **SYN cookies**, che rinviano l'allocazione dello stato fino a quando il client dimostra di aver ricevuto il `SYN/ACK`;

- cancellare casualmente entry incomplete quando la coda è piena, aumentando la probabilità che almeno alcune connessioni legittime riescano a procedere.

![](imgs/Pasted%20image%2020260708221754.png)

Le prime due contromisure sono utili ma non risolutive. Una backlog più grande può comunque essere saturata; un timeout troppo breve può penalizzare client legittimi lenti. Per questo le contromisure più interessanti sono quelle che riducono o posticipano l'allocazione dello stato.

---

### **7. SYN cookies: principio**

L'idea dei **SYN cookies** è posticipare la creazione dello stato sul server. Invece di allocare subito un `TCB` completo quando arriva un `SYN`, il server risponde con un `SYN/ACK` il cui numero di sequenza contiene un valore speciale, detto cookie.

![](imgs/Pasted%20image%2020260708221525.png)

Il cookie codifica informazioni sufficienti a riconoscere in seguito la connessione, per esempio:

- indirizzo IP e porta sorgente;

- indirizzo IP e porta destinazione;

- un valore temporale o timestamp;

- alcune opzioni essenziali della connessione;

- un segreto noto solo al server.

![](imgs/Pasted%20image%2020260708221703.png)

Il server calcola questo valore usando una funzione non facilmente falsificabile, tipicamente una funzione hash o un MAC con chiave segreta. Poi inserisce il risultato nel sequence number del `SYN/ACK`.

Il client legittimo non deve conoscere il meccanismo interno: secondo TCP, risponderà semplicemente con un `ACK` che conferma il sequence number ricevuto. Quando il server riceve tale `ACK`, ricalcola il cookie dai parametri della connessione e dal proprio segreto. Se il valore combacia con quello riportato dal client, allora il server alloca finalmente lo stato completo e considera valida la connessione.

> 📌 Con i SYN cookies il server resta sostanzialmente **stateless** finché il presunto client non dimostra di aver ricevuto il `SYN/ACK`. Un indirizzo spoofato non riceve il cookie e quindi non può completare correttamente l'handshake.

---

### **8. Limiti e dettagli dei SYN cookies**

I SYN cookies non rendono TCP autenticato in senso crittografico generale: mitigano specificamente il consumo di stato durante l'handshake. La sicurezza dipende dal fatto che il cookie non sia prevedibile per l'attaccante e sia legato alla specifica connessione.

Un cookie debole o prevedibile non sarebbe sufficiente. Per questo deve dipendere almeno dalla quaterna della connessione, da un'informazione temporale e da un segreto del server. In pratica il server deve poter verificare:

$$
\text{cookie ricevuto} = F(\text{IP sorgente}, \text{porta sorgente}, \text{IP destinazione}, \text{porta destinazione}, \text{tempo}, \text{segreto})
$$

dove $F$ è una funzione progettata per impedire a un attaccante di costruire cookie validi senza conoscere il segreto.

Il prezzo da pagare è che, non avendo salvato stato iniziale, il server può memorizzare nel cookie solo un numero limitato di informazioni. Alcune opzioni TCP possono quindi essere gestite in modo ridotto o recuperate solo dopo che l'handshake è stato completato.

---

### **9. Proxy e mitigazioni infrastrutturali**

Un'ulteriore soluzione consiste nell'usare un **proxy** o un servizio di mitigazione esterno. Il proxy riceve il traffico, gestisce o filtra i tentativi di handshake e inoltra al server reale solo le connessioni che hanno completato correttamente la sequenza `SYN`, `SYN/ACK`, `ACK`.

In questo modo il server originario non viene saturato direttamente da stati half-open. La difesa può essere implementata da apparati di rete, reverse proxy o servizi di scrubbing specializzati. Un esempio commerciale citato nelle note è **Prolexic** (poi confluito in Akamai), usato per filtrare traffico malevolo prima che raggiunga l'infrastruttura bersaglio.

Questa tecnica è utile soprattutto contro attacchi volumetrici o distribuiti, ma sposta parte della fiducia e della complessità sull'infrastruttura di mitigazione.

---

### **10. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Tipo di attacco**|DoS contro l'apertura delle connessioni TCP|
|**Tecnica**|Invio massivo di `SYN`, spesso con IP sorgente spoofati o casuali|
|**Risorsa colpita**|Backlog queue e stato `SYN-RECEIVED`/`TCB`|
|**Effetto**|Il server non riesce ad accettare nuove connessioni legittime|
|**Asimmetria**|Pochi byte inviati dall'attaccante causano molta più memoria/stato sul server|
|**Contromisure deboli**|Aumentare backlog, ridurre timeout|
|**Contromisure robuste**|SYN cache, SYN cookies, cancellazione selettiva di entry incomplete, proxy/scrubbing|

> ✅ Punto d'esame: il SYN flooding è difficile da filtrare perché sfrutta un comportamento necessario di TCP. La difesa più elegante è posticipare l'allocazione dello stato: il server non deve pagare il costo completo della connessione finché il client non prova di esistere completando l'handshake.
