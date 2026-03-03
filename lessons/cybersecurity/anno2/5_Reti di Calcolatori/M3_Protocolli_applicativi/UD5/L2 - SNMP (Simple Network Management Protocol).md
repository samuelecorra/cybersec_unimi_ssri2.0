# **Lezione 2: SNMP (Simple Network Management Protocol)**

---

### **1. Visione d’insieme**

**SNMP (Simple Network Management Protocol)** è il protocollo standard per la **gestione remota dei dispositivi di rete**, come router, bridge, switch e server.  
Nasce con l’obiettivo di permettere all’amministratore di **controllare, configurare e monitorare** dispositivi distribuiti in rete **da un unico punto di gestione**.

Il **principio fondamentale** su cui si basa SNMP è semplice:

> la gestione dei dispositivi avviene tramite la **lettura e la modifica dei valori di specifiche variabili**.

Ogni dispositivo di rete espone un insieme di **variabili di stato** (per esempio numero di pacchetti inviati, velocità dell’interfaccia, errori di trasmissione, ecc.) che possono essere:

- **lette** per conoscere lo stato del dispositivo,
    
- o **modificate** per cambiare la sua configurazione.
    

L’approccio operativo è di tipo **“test and set”**, cioè:

- **test** → leggere i valori di una variabile per verificarne lo stato;
    
- **set** → modificarne il valore per impostare nuovi parametri o comportamenti.
    

SNMP gestisce queste operazioni tramite un **protocollo di messaggi semplici**, che viaggiano all’interno della rete IP.

---

### **2. Protocollo dei messaggi SNMP**

I messaggi SNMP vengono trasmessi usando il **protocollo UDP** (User Datagram Protocol), per garantire leggerezza e velocità.  
Le porte standard utilizzate sono:

- **161** → per le **richieste e risposte** (tra gestore e agente);
    
- **162** → per le **notifiche** (messaggi spontanei inviati dai dispositivi).
    

#### **Tipi principali di messaggi SNMP:**

|Tipo di messaggio|Descrizione|
|---|---|
|**GetRequest**|Il gestore (manager) richiede il valore di una o più variabili dal dispositivo (agente).|
|**GetNextRequest**|Richiede la **variabile successiva** nella gerarchia della MIB, utile per scorrere sequenze di dati.|
|**GetResponse**|È la **risposta dell’agente** al manager, contenente i valori richiesti.|
|**SetRequest**|Invia istruzioni all’agente per **impostare nuovi valori** alle variabili.|
|**Trap**|Messaggio **spontaneo dell’agente** verso il manager, per segnalare **eventi o cambiamenti di stato** (es. un guasto o un riavvio).|

Questi messaggi formano il nucleo operativo di SNMP, rendendolo un protocollo **semplice, efficiente e universale** per la gestione di reti eterogenee.

---

### **3. Le “Community” in SNMP**

SNMP introduce il concetto di **community**, ossia un meccanismo di controllo degli accessi basato su **parole chiave condivise** tra gestore e agente.

Ogni community definisce un **livello di autorizzazione**:

|Tipo di community|Permessi associati|
|---|---|
|**Read-only (RO)**|Consente solo la lettura delle variabili.|
|**Read-write (RW)**|Permette sia lettura che modifica.|
|**None**|Nessun accesso autorizzato.|

In pratica, il nome della community funge da **password testuale**, che deve essere inserita in ogni messaggio SNMP.  
Tuttavia, **le versioni più vecchie del protocollo (SNMPv1 e SNMPv2c)** trasmettono queste informazioni **in chiaro**, senza crittografia: ciò le rende vulnerabili a intercettazioni o accessi non autorizzati.

Nonostante questo limite, **SNMP non cifrato è ancora molto diffuso**, soprattutto nelle reti locali o negli ambienti di monitoraggio interni.

---

### **4. Codifica dei messaggi**

I messaggi SNMP sono codificati come **flussi di byte** secondo le regole **ASN.1 BER**  
(_Abstract Syntax Notation One – Basic Encoding Rules_), lo standard usato per rappresentare in modo univoco i dati in forma binaria.

Ogni elemento del messaggio è espresso come una **tripla (Type, Length, Value)**, dove:

- **Type** specifica il tipo di dato (intero, stringa, indirizzo IP, ecc.);
    
- **Length** indica la lunghezza del valore in byte;
    
- **Value** contiene effettivamente il dato.
    

#### **Tipi di dato usati in SNMP:**

- Tipi ASN.1 di base:
    
    - **INTEGER**, **OCTET STRING**, **OBJECT IDENTIFIER**, **SEQUENCE**.
        
- Tipi specifici di SNMP:
    
    - **Gauge** (valore misurabile, come la temperatura o il traffico),
        
    - **Counter** (contatore incrementale),
        
    - **IPAddress** (indirizzo IP a 32 bit).
        

Questa struttura standardizzata consente a sistemi diversi (hardware e software) di **scambiarsi informazioni di gestione in modo interoperabile**.

---

### **5. SMI – Structure of Management Information**

Per organizzare e descrivere in modo coerente le informazioni gestite, SNMP utilizza un modello chiamato **SMI (Structure of Management Information)**.

L’SMI stabilisce **come devono essere definite le variabili** e **come vengono identificate all’interno della rete**.  
Ogni variabile corrisponde a un **oggetto** della **MIB (Management Information Base)**, che rappresenta il “catalogo” di tutti i dati gestibili.

#### **Caratteristiche della MIB:**

- È un **file di testo strutturato**, scritto in **ASN.1**, che definisce le variabili e le loro relazioni gerarchiche.
    
- Ogni variabile ha un **Object Identifier (OID)**, cioè un nome numerico univoco che ne specifica la posizione nella gerarchia.
    
- Le **variabili standard** sono definite nelle **RFC** (Request For Comments).
    
- I **produttori di dispositivi** possono aggiungere **variabili specifiche**, dette _proprietary MIBs_, per rappresentare parametri unici dei loro apparati.
    

Esempio (da RFC 1213, MIB-II):

```
iso.org.dod.internet.mgmt.mib-2.system.sysUpTime
```

→ indica il tempo trascorso dall’ultimo riavvio del dispositivo.

---

### **6. Funzionamento sintetico di SNMP**

Un’architettura SNMP tipica prevede due attori principali:

1. **Manager (gestore)**  
    È l’applicazione centrale che invia richieste SNMP, raccoglie le risposte e mantiene sotto controllo l’intera rete.
    
2. **Agent (agente)**  
    È il software in esecuzione su ciascun dispositivo gestito, incaricato di leggere i dati locali e rispondere alle richieste del manager.
    

Il manager interroga periodicamente gli agenti con messaggi **GetRequest** e riceve i valori aggiornati tramite **GetResponse**.  
Quando un evento importante si verifica (per esempio, un’interfaccia di rete che si disconnette), l’agente invia **spontaneamente** un messaggio **Trap** al manager per segnalare il problema.

---

### **7. Conclusione**

**SNMP** rappresenta la **spina dorsale dell’amministrazione remota delle reti IP**.  
È un protocollo semplice ma estremamente potente, basato sul concetto di **manipolazione di variabili** che descrivono lo stato di ogni apparato.

Grazie a meccanismi leggeri come **UDP**, a un linguaggio comune di descrizione dei dati (**ASN.1**) e a strutture standard come la **MIB**, SNMP consente:

- monitoraggio centralizzato,
    
- configurazione remota,
    
- e notifica automatica degli eventi.
    

Anche se le versioni più recenti (SNMPv3) hanno introdotto **sicurezza e autenticazione**, i principi fondamentali restano invariati:

> osservare, misurare e controllare la rete in modo semplice, universale e distribuito.