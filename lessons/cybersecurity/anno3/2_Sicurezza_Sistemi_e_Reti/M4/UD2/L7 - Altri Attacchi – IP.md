## **Lezione 7: Altri Attacchi – IP**

### **1. Introduzione**

Questa lezione approfondisce le vulnerabilità del livello **IP** e mostra come le debolezze di progettazione del protocollo possano essere sfruttate per creare **attacchi di Denial of Service (DoS)** o per generare **amplificazioni** di traffico devastanti.  
Gli attacchi che analizzeremo (Teardrop, Ping of Death, LAND, DNS Amplification) rappresentano tappe storiche fondamentali nella storia della sicurezza delle reti e spiegano perché le versioni moderne dei protocolli abbiano introdotto verifiche e limiti più rigidi.

---

### **2. Struttura del Datagramma IP**

Ogni datagramma IP è composto da un’intestazione variabile e da un campo dati. Le informazioni fondamentali sono:

- **Version number (4 bit)** – indica la versione del protocollo IP (4 o 6).
    
- **Header length (4 bit)** – lunghezza dell’intestazione (di solito 20 byte, ma può variare).
    
- **Type of Service (8 bit)** – specifica la priorità o la qualità del servizio (QoS).
    
- **Total Length (16 bit)** – lunghezza totale del datagramma, fino a un massimo teorico di **65.535 byte**.  
    In pratica, la maggior parte delle reti Ethernet limita la **MTU (Maximum Transmission Unit)** a **1500 byte**.
    

---

### **3. Frammentazione IP**

#### **3.1. Motivo della frammentazione**

Quando un datagramma IP supera la MTU del collegamento, deve essere **frammentato** in più pezzi, ciascuno dei quali viaggia come datagramma indipendente.  
Ad esempio:

- Ethernet: MTU = 1500 byte
    
- Collegamenti WAN: MTU tipica = 576 byte
    

#### **3.2. Campi di controllo della frammentazione**

Ogni frammento contiene tre campi fondamentali:

1. **Identification** – un numero che consente di riconoscere a quale datagramma originario appartiene il frammento.
    
2. **Flags** – tra cui il bit “More Fragments (MF)” che indica se ci sono altri frammenti successivi.
    
3. **Fragment Offset** – specifica la posizione del frammento all’interno del datagramma originale (in unità di 8 byte).
    

Questi campi permettono al destinatario di **riassemblare** i frammenti nell’ordine corretto. Tuttavia, un loro uso malevolo può portare a crash o corruzioni di memoria.

---

### **4. Teardrop Attack**

#### **4.1. Meccanismo**

L’attacco **Teardrop** sfrutta la gestione dei frammenti IP sovrapposti.  
L’attaccante invia datagrammi con campi di **offset** e **lunghezza** incoerenti, in modo che i frammenti risultino **parzialmente sovrapposti**:

- Frammento 1: posizione 0, lunghezza 60
    
- Frammento 2: posizione 30, lunghezza 90
    
- Frammento 3: posizione 41, lunghezza 173
    

Durante il riassemblaggio, i frammenti non coincidono correttamente: alcune parti si sovrappongono e altre si contraddicono. Il sistema operativo, tentando di ricomporli, **va in errore di gestione della memoria** o in crash.

#### **4.2. Effetto**

Nei sistemi più vulnerabili (vecchie versioni di Windows, Linux e Unix), il risultato era il **blocco totale del kernel** e quindi la **negazione del servizio (DoS)**.  
Il teardrop è stato uno dei primi esempi di attacco _a livello di implementazione del protocollo_, non del protocollo stesso.

---

### **5. Ping of Death**

#### **5.1. Origine e vulnerabilità**

Il **Ping of Death** è un attacco che invia un datagramma IP **frammentato** in modo tale che, una volta riassemblato, superi la dimensione massima consentita di **65.535 byte**.  
Il problema risiede nel campo **Fragment Offset (13 bit)**, che consente un offset massimo di **65.528 byte** ($ (2^{13} - 1) \times 8 $).

Un attaccante può:

1. Inviare un frammento con **offset massimo** (es. 65.528).
    
2. Allegare **più di 8 byte di dati**.  
    → Quando il sistema ricevente tenta di riassemblare il pacchetto, la dimensione totale supera i 65.535 byte, generando un **overflow del buffer di memoria**.
    

#### **5.2. Natura del problema**

Non è un difetto di ICMP in sé, ma del **processo di riassemblaggio IP**, che può coinvolgere qualunque protocollo incapsulato (TCP, UDP, IGMP, ecc.).

#### **5.3. Mitigazione**

Durante il riassemblaggio, il sistema deve verificare che:

$$  
\text{Fragment Offset} + \text{Total Length} < 65.535  
$$

Se questa somma è maggiore, il pacchetto deve essere **scartato** come non valido.  
Tale controllo oggi è implementato in tutti gli stack IP moderni, rendendo inefficace l’attacco originario.

---

### **6. LAND Attack (Local Area Network Denial)**

#### **6.1. Meccanismo**

Scoperto nel 1997 dal ricercatore “m3lt”, il **LAND attack** sfrutta la possibilità di inviare un pacchetto TCP con **indirizzo IP sorgente e destinazione identici** e la **stessa porta** aperta.  
In pratica, la macchina si ritrova a **rispondere a sé stessa** in un loop continuo.

#### **6.2. Effetti**

Questo genera:

- Loopback involontario nello stack TCP/IP;
    
- Saturazione della CPU;
    
- Blocco o crash del sistema operativo.
    

È stato riscontrato in sistemi come **Windows XP SP2** e **Windows Server 2003**, ma anche in protocolli come **SNMP** e servizi Kerberos (porta TCP 88).

#### **6.3. Mitigazione**

Gli stack moderni rifiutano automaticamente pacchetti con IP sorgente uguale all’indirizzo locale, impedendo che la macchina risponda a sé stessa.

---

### **7. DNS Amplification Attack**

#### **7.1. Meccanismo generale**

Il **DNS amplification** è un attacco **DDoS riflesso e amplificato** basato su UDP.  
L’attaccante invia query DNS a **resolver aperti** (open resolvers), falsificando l’indirizzo sorgente in modo che la risposta venga inviata alla **vittima**.

Esempio:

```bash
dig ANY isc.org @x.x.x.x
```

Questa query da **64 byte** può generare una risposta di **3.223 byte**, con un fattore di amplificazione di **oltre 50×**.

#### **7.2. Caso Spamhaus (Marzo 2013)**

Nel marzo 2013, un attacco contro **Spamhaus**, mitigato da **Cloudflare**, raggiunse **75 Gbps** e poi **309 Gbps** per 28 minuti.  
Caratteristiche principali:

- Oltre **30.000 resolver DNS aperti** coinvolti.
    
- Ogni resolver inviava in media **2,5 Mbps** di traffico.
    
- L’attaccante controllava solo una piccola botnet o cluster cloud (es. AWS) con **750 Mbps** totali di banda in uscita.  
    → Amplificazione complessiva 50× e riflessione globale.
    

#### **7.3. Dati storici**

- 2006: circa **0,58 milioni** di resolver aperti (Kaminsky–Shiffman).
    
- 2014: oltre **28 milioni** (OpenResolver Project).  
    Questo mostra la crescita incontrollata della superficie di attacco.
    

#### **7.4. Meccanismo tecnico**

1. L’attaccante invia una richiesta DNS `ANY` (che restituisce l’intera zona).
    
2. Usa come sorgente IP quello della vittima.
    
3. Ogni server risponde alla vittima con pacchetti di migliaia di byte.
    
4. L’aggregazione simultanea di migliaia di risposte sovraccarica la vittima.
    

---

### **8. Sintesi finale**

|**Attacco**|**Livello**|**Descrizione sintetica**|**Effetto principale**|**Contromisure**|
|---|---|---|---|---|
|**Teardrop**|IP|Frammenti sovrapposti con offset incoerenti|Crash del sistema durante riassemblaggio|Controlli sui campi offset e lunghezza|
|**Ping of Death**|IP|Frammenti che superano 65.535 byte|Buffer overflow, crash|Verifica che Offset + Lunghezza < 65.535|
|**LAND**|TCP/IP|IP e porta sorgente = destinazione|Loopback e blocco CPU|Scarto automatico pacchetti self-source|
|**DNS Amplification**|UDP / DNS|Risposte amplificate da resolver aperti|DDoS riflesso massivo|Chiusura resolver, rate-limit, BCP38|

---

### **9. Considerazioni conclusive**

Questi attacchi mostrano una lezione fondamentale della sicurezza delle reti: **anche piccole ambiguità nei protocolli o cattive configurazioni possono generare effetti catastrofici su scala globale**.  
Il livello IP, pur essendo “stateless”, ha un impatto enorme sull’affidabilità del sistema nel suo complesso.  
Le contromisure introdotte — verifica dei frammenti, blocco pacchetti self-source, disabilitazione dei resolver aperti — sono ormai parte integrante delle implementazioni sicure moderne, ma la loro efficacia dipende dal fatto che siano **attivamente applicate da tutti gli attori della rete**, non solo dalla singola macchina vittima.