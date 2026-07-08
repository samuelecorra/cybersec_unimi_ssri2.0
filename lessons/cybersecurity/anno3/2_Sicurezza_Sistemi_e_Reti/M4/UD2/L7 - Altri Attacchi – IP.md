## **Lezione 7: Altri Attacchi – IP**

### **1. Introduzione**

Questa lezione chiude l'unità sugli attacchi ai protocolli TCP/IP analizzando vulnerabilità che sfruttano direttamente pacchetti IP, frammentazione, riassemblaggio e servizi applicativi basati su UDP. Gli attacchi principali sono:

- **Teardrop**, basato su frammenti IP sovrapposti;

- **Ping of Death**, basato su riassemblaggio oltre la dimensione massima del datagramma IP;

- **LAND**, basato su pacchetti TCP/IP con sorgente e destinazione coincidenti;

- **DNS amplification**, basato su resolver DNS aperti e risposte molto più grandi delle richieste.

Il punto comune è che l'attaccante non deve necessariamente violare un meccanismo crittografico: può costruire pacchetti formalmente plausibili, modificando campi dell'header IP o sfruttando il comportamento normale di servizi aperti su Internet, fino a produrre Denial of Service.

> 📌 A questo livello l'attacco nasce spesso dall'ambiguità tra "pacchetto sintatticamente accettabile" e "pacchetto semanticamente sicuro da processare".

---

### **2. Struttura del datagramma IP**

Un datagramma IP è composto da un'intestazione e da un campo dati. Nell'header sono presenti informazioni fondamentali per instradare, frammentare e riassemblare il pacchetto:

![](imgs/Pasted%20image%2020260708223635.png)

- **Version**: indica la versione del protocollo, per esempio IPv4 o IPv6;

- **Header Length**: specifica la lunghezza dell'intestazione;

- **Type of Service**: indica preferenze di trattamento o qualità del servizio;

- **Total Length**: indica la lunghezza complessiva del datagramma, header più dati;

- **Identification**: identifica i frammenti appartenenti allo stesso datagramma originario;

- **Flags**: includono informazioni sulla frammentazione, per esempio se ci sono altri frammenti;

- **Fragment Offset**: indica la posizione del frammento nel datagramma originale;

- **Source Address** e **Destination Address**: indicano mittente e destinatario a livello IP.

La dimensione massima teorica di un datagramma IPv4 è **65.535 byte**, perché il campo `Total Length` è di 16 bit. Nella pratica, però, i datagrammi attraversano collegamenti con limiti più piccoli: su Ethernet, per esempio, la **MTU** tipica è **1500 byte**.

![](imgs/Pasted%20image%2020260708223725.png)

> ⚠️ Il valore massimo di IP non coincide con ciò che una singola rete fisica può trasportare in un frame. Per attraversare link con MTU minore, il datagramma deve essere frammentato.

---

### **3. Frammentazione IP**

#### **3.1. Perché serve la frammentazione**

Ogni tecnologia di collegamento impone una **Maximum Transmission Unit** (`MTU`), cioè la dimensione massima del pacchetto trasportabile in un singolo frame. Se un datagramma IP è più grande della MTU del collegamento, deve essere diviso in frammenti più piccoli.

Ogni frammento viaggia come datagramma IP indipendente, ma contiene campi che permettono al destinatario di ricostruire il datagramma originale:

- lo stesso campo `Identification`, per riconoscere che i frammenti appartengono allo stesso datagramma;

- un `Fragment Offset`, per sapere dove collocare il frammento;

- il flag **More Fragments** (`MF`), per indicare se dopo quel frammento ne arrivano altri.

#### **3.2. Esempio di frammentazione**

Il docente usa l'esempio di un datagramma da circa **4000 byte** che deve attraversare una rete con MTU di circa **1500 byte**. Poiché ogni frammento deve includere anche il proprio header IP, il payload effettivo di ciascun frammento non è 1500 byte pieni: in IPv4, con header minimo da 20 byte, si ottengono tipicamente frammenti con circa **1480 byte** di dati.

Una possibile suddivisione è:

|Frammento|Dati trasportati|Offset|Flag MF|
|---|---:|---:|---|
|1|1480 byte|0|1|
|2|1480 byte|185|1|
|3|1040 byte|370|0|

Gli offset sono espressi in unità di **8 byte**: per questo il secondo frammento, che inizia dopo 1480 byte, ha offset $1480/8 = 185$. L'ultimo frammento ha `MF = 0`, perché segnala che non ci sono altri frammenti successivi.

![](imgs/Pasted%20image%2020260708223752.png)

> 📌 Il destinatario non "vede" subito il datagramma originale: riceve frammenti separati e deve ricomporli usando `Identification`, offset e flag.

---

### **4. Teardrop Attack**

#### **4.1. Meccanismo**

Il **Teardrop attack** sfrutta il processo di riassemblaggio dei frammenti IP. Un attaccante invia frammenti con valori di offset e lunghezza incoerenti, in modo che le porzioni del datagramma originale risultino sovrapposte o incompatibili.

Per esempio, invece di inviare frammenti ordinati e non sovrapposti, l'attaccante può costruire frammenti che dichiarano posizioni come:

- frammento 1: inizio a offset 0;

- frammento 2: inizio prima della fine del frammento precedente;

- frammento 3: ulteriore sovrapposizione o intervallo incoerente.

Il sistema operativo ricevente tenta di ricostruire il datagramma originale, ma trova frammenti che non combaciano correttamente. Nelle implementazioni vulnerabili, questa situazione poteva portare a errori di gestione della memoria, crash del kernel o blocco dei servizi di rete.

![](imgs/Pasted%20image%2020260708223829.png)

#### **4.2. Natura dell'attacco**

Teardrop non sfrutta un problema del contenuto applicativo, ma un problema nel modo in cui lo stack IP gestisce casi anomali durante il riassemblaggio. Il pacchetto malevolo forza il sistema operativo a processare una configurazione di frammenti che non dovrebbe essere accettata.

> ⚠️ La mitigazione corretta è validare rigorosamente offset e lunghezze: frammenti sovrapposti, incoerenti o impossibili devono essere scartati prima di arrivare a corrompere lo stato di riassemblaggio.

---

### **5. Ping of Death**

#### **5.1. Origine e vulnerabilità**

Il **Ping of Death** è un attacco storico che usa tipicamente un messaggio ICMP Echo Request, cioè un `ping`, incapsulato in frammenti IP costruiti in modo anomalo. Il problema non è ICMP in sé, ma il riassemblaggio IP.

La dimensione massima di un datagramma IPv4 è **65.535 byte**. Tuttavia, il campo `Fragment Offset` può indicare una posizione molto vicina a tale limite: l'offset massimo è

$$
(2^{13} - 1) \cdot 8 = 65.528 \text{ byte}
$$

Se un attaccante invia un frammento con offset massimo e allega **più di 8 byte** di dati, il datagramma riassemblato supera il limite massimo consentito:

$$
65.528 + \text{dati del frammento} > 65.535
$$

Nelle implementazioni vulnerabili, il ricevente tentava comunque di assemblare il pacchetto e poteva produrre un **buffer overflow** o un crash.

#### **5.2. Natura del problema**

Il nome "Ping of Death" deriva dal fatto che l'esempio classico usa `ping`, ma il difetto è più generale: riguarda il controllo della dimensione finale durante il riassemblaggio dei frammenti IP. In linea di principio può coinvolgere qualunque protocollo incapsulato in IP, non solo ICMP.

#### **5.3. Mitigazione**

Durante il riassemblaggio lo stack IP deve verificare che la posizione dichiarata dal frammento più la sua lunghezza non superi la dimensione massima del datagramma:

$$
\text{Fragment Offset} + \text{Fragment Length} \le 65.535
$$

Se il controllo fallisce, il frammento deve essere scartato. Gli stack moderni implementano questi controlli, rendendo inefficace l'attacco originario.

---

### **6. LAND Attack**

#### **6.1. Meccanismo**

Il **LAND attack** consiste nell'inviare un pacchetto TCP, tipicamente un `SYN`, in cui:

- l'indirizzo IP sorgente coincide con l'indirizzo IP destinazione;

- la porta sorgente coincide con la porta destinazione;

- la porta destinazione è associata a un servizio aperto sulla vittima.

In questo modo la macchina ricevente si trova, di fatto, a ricevere una richiesta apparentemente proveniente da sé stessa e diretta a sé stessa. Nelle implementazioni vulnerabili, lo stack TCP/IP poteva entrare in un loop di risposta, consumando CPU e bloccando il sistema.

#### **6.2. Effetti e sistemi coinvolti**

Il transcript richiama in particolare vecchie implementazioni e librerie di rete usate in sistemi come **Windows XP SP2** e **Windows Server 2003**, oltre a servizi o protocolli che in alcuni casi hanno manifestato comportamenti analoghi, come **SNMP** e Kerberos sulla porta TCP 88.

Il problema comune è l'assenza di un controllo elementare: alcuni pacchetti non hanno senso operativo se sorgente e destinazione coincidono nello stesso modo. Se lo stack non li filtra, possono nascere loop interni e quindi Denial of Service.

#### **6.3. Mitigazione**

Gli stack moderni scartano pacchetti con sorgente palesemente non valida, per esempio pacchetti provenienti dall'esterno con IP sorgente uguale all'indirizzo locale della macchina. Anche firewall e router possono applicare regole per bloccare traffico con indirizzi sorgente impossibili o sospetti.

> 📌 LAND è un esempio didattico di quanto sia importante non limitarsi a verificare la sintassi del pacchetto: bisogna anche controllare che la combinazione dei campi abbia senso.

---

### **7. DNS Amplification Attack**

#### **7.1. Meccanismo generale**

Il **DNS amplification attack** è un DDoS riflesso e amplificato basato su UDP. DNS deve essere raggiungibile dall'esterno e deve rispondere alle richieste dei client legittimi; questa apertura può però essere abusata da un attaccante.

Il meccanismo è:

1. l'attaccante individua resolver DNS aperti;

2. invia query DNS usando come IP sorgente l'indirizzo della vittima;

3. i resolver rispondono alla vittima;

4. le risposte sono molto più grandi delle richieste;

5. la vittima riceve traffico amplificato da molti resolver contemporaneamente.

![](imgs/Pasted%20image%2020260708223934.png)

#### **7.2. Perché DNS amplifica**

Una query DNS può essere molto piccola, per esempio dell'ordine di **60-64 byte**. Alcune risposte, invece, possono arrivare a circa **3000 byte** o più, soprattutto con query che richiedono molte informazioni.

Esempio classico:

```bash
dig ANY isc.org @x.x.x.x
```

Una query di circa **64 byte** può generare una risposta di circa **3.223 byte**, con un fattore di amplificazione superiore a **50×**.

> ⚠️ La pericolosità nasce dalla combinazione di tre fattori: UDP consente spoofing più semplice, DNS è un servizio esposto pubblicamente, e alcune risposte sono molto più grandi delle richieste.

#### **7.3. Caso Spamhaus e dati storici**

Nel 2013 un attacco DDoS contro **Spamhaus**, mitigato da **Cloudflare**, raggiunse volumi enormi. Le note riportano:

- picchi di **75 Gbit/s** e poi fino a **309 Gbit/s**;

- durata dell'ordine di **28 minuti** per il picco maggiore;

- circa **30.000 resolver DNS aperti** coinvolti;

- circa **2,5 Mbit/s** medi per resolver;

- una botnet o infrastruttura controllata dall'attaccante molto più piccola del traffico finale prodotto.

Il fattore di amplificazione permette infatti a una botnet relativamente contenuta, o a risorse cloud limitate, di provocare un volume di traffico molto superiore verso la vittima.

#### **7.4. Crescita della superficie di attacco**

Il numero di resolver aperti su Internet è stato storicamente molto elevato:

- circa **0,58 milioni** nel 2006 secondo misure citate nelle slide;

- oltre **28 milioni** nel 2014 secondo l'OpenResolver Project.

Questo dato è importante perché gli attacchi di amplificazione non dipendono solo dalla macchina dell'attaccante: dipendono anche dalla quantità di servizi legittimi mal configurati o troppo permissivi disponibili come reflector.

#### **7.5. Contromisure**

Le contromisure principali sono:

- chiudere o limitare i resolver aperti;

- rispondere solo ai client autorizzati o alla propria rete;

- applicare rate limiting alle risposte DNS;

- ridurre o bloccare query ad alta amplificazione, come alcune query `ANY`;

- applicare filtri anti-spoofing a livello ISP, così che l'attaccante non possa inviare query con IP sorgente della vittima.

---

### **8. Sintesi finale**

|Attacco|Livello|Meccanismo|Effetto|Contromisure|
|---|---|---|---|---|
|**Teardrop**|IP|Frammenti sovrapposti o incoerenti|Crash o blocco durante riassemblaggio|Validazione rigorosa di offset e lunghezze|
|**Ping of Death**|IP/ICMP|Riassemblaggio oltre 65.535 byte|Overflow o crash|Controllo su offset + lunghezza|
|**LAND**|TCP/IP|IP e porte sorgente = destinazione|Loop interno, CPU saturata, DoS|Scarto pacchetti self-source/impossibili|
|**DNS Amplification**|UDP/DNS|Query piccola con IP spoofato, risposta grande|DDoS riflesso e amplificato|Resolver chiusi, rate limit, BCP38|

---

### **9. Considerazioni conclusive**

Questi attacchi mostrano che la sicurezza dei protocolli non dipende solo dalla robustezza teorica del formato, ma anche da come gli stack implementano i casi limite. Frammenti sovrapposti, offset fuori limite, indirizzi sorgente impossibili e resolver aperti sono tutti esempi di input che un sistema deve rifiutare o limitare.

La lezione più importante è che le difese devono essere distribuite: il singolo host deve validare i pacchetti, i server devono essere configurati in modo restrittivo e gli operatori di rete devono impedire lo spoofing degli indirizzi sorgente. Senza questa cooperazione, servizi perfettamente legittimi possono diventare strumenti di attacco contro terzi.

> ✅ Punto d'esame: Teardrop e Ping of Death sono attacchi al riassemblaggio IP; LAND è un attacco alla coerenza dei campi sorgente/destinazione; DNS amplification è un attacco di reflection + amplification che sfrutta resolver aperti e UDP.
