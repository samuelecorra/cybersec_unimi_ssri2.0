# **Lezione 3: Complementi di SNMP**

---

### **1. Il costrutto `MODULE-IDENTITY`**

In SNMPv2, ogni modulo MIB è definito da un **costrutto principale** chiamato `MODULE-IDENTITY`.  
Serve per **identificare univocamente** il modulo, specificandone l’origine, la versione e lo scopo.

Esempio tratto dal modulo **ipMIB**:

```plaintext
ipMIB MODULE-IDENTITY
  LAST-UPDATED "941101000Z"
  ORGANIZATION "IETF SNMPv2 Working Group"
  CONTACT-INFO
    "Keith McCloghrie..."
  DESCRIPTION
    "Il modulo MIB per gestire implementazioni IP e ICMP,
     escludendo però la gestione delle rotte IP."
  REVISION "019331000Z"
::= { mib-2 48 }
```

In sintesi:

- `MODULE-IDENTITY` dichiara il **nome e le informazioni anagrafiche** del modulo;
    
- `LAST-UPDATED` indica la **data dell’ultima revisione**;
    
- `ORGANIZATION` specifica il gruppo che mantiene il modulo;
    
- `DESCRIPTION` spiega **che cosa gestisce** la MIB;
    
- la riga finale `::= { mib-2 48 }` definisce il **punto di ancoraggio** del modulo all’interno dell’albero globale degli identificatori OID.
    

---

### **2. Il modulo UDP nella MIB-II**

Un esempio pratico di struttura MIB è il **modulo UDP**, che raccoglie i principali parametri relativi al protocollo **User Datagram Protocol**.

|OID|Nome|Tipo|Descrizione|
|---|---|---|---|
|`1.3.6.1.2.1.7.1`|`udpInDatagr`|`Counter32`|Numero totale di datagrammi UDP consegnati a questo nodo.|
|`1.3.6.1.2.1.7.2`|`udpNoPorte`|`Counter32`|Datagrammi non consegnabili per assenza di applicazioni sulla porta di destinazione.|
|`1.3.6.1.2.1.7.3`|`udpInError`|`Counter32`|Datagrammi scartati per errori diversi (es. corruzione o overflow).|
|`1.3.6.1.2.1.7.4`|`udpDatagrInv`|`Counter32`|Numero totale di datagrammi inviati da questo nodo.|
|`1.3.6.1.2.1.7.5`|`udpTabella`|`SEQUENCE`|Una riga per ogni porta UDP in uso: contiene il numero di porta e l’indirizzo IP associato.|

Questa struttura consente al gestore SNMP di monitorare **il traffico UDP** in ingresso e in uscita e di rilevare eventuali **errori di consegna o configurazione**.

---

### **3. Assegnazione dei nomi SNMP**

Per identificare in modo univoco ogni oggetto gestito in rete, SNMP utilizza l’**albero OSI degli identificatori di oggetto (OID, Object Identifier)**.

#### **Caratteristiche principali:**

- La struttura è **gerarchica**, simile a un albero genealogico.
    
- Ogni punto di biforcazione (nodo) ha:
    
    - un **nome testuale**,
        
    - e un **numero associato**.
        
- I rami sono concatenati per formare un **percorso numerico univoco**.
    

In questo modo, ogni variabile o oggetto nella MIB può essere identificato da una **sequenza di numeri separati da punti**, che rappresentano la sua posizione nell’albero.

---

### **4. Esempio di nome SNMP (OID)**

Ecco un esempio concreto di **identificatore SNMP**:

![](imgs/Pasted%20image%2020260225163942.png)

```
1.3.6.1.2.1.7.1
```

La gerarchia corrisponde a:

|Numero|Nome|Significato|
|---|---|---|
|1|iso|Organizzazione internazionale per la standardizzazione|
|3|org|Organizzazione generica|
|6|dod|Dipartimento della Difesa degli Stati Uniti|
|1|internet|Rete Internet|
|2|mgmt|Sezione di management (gestione)|
|1|mib-2|MIB standard version 2|
|7|udp|Sezione dedicata al protocollo UDP|
|1|udpInDatagrams|Variabile che conta i datagrammi UDP ricevuti|

**Esempio interpretato:**  
L’OID `1.3.6.1.2.1.7.1` indica il contatore dei datagrammi UDP ricevuti da un nodo nella rete.

---

### **5. Modalità di raccolta dati in SNMP**

SNMP consente due differenti modalità per raccogliere informazioni dai dispositivi di rete:

![](imgs/Pasted%20image%2020260225163958.png)

#### **a. Modalità sincrona (richiesta/risposta)**

È la modalità classica in cui:

- il **manager invia** una richiesta (`GetRequest` o `GetNextRequest`),
    
- e l’**agente risponde** con un messaggio `GetResponse`.
    

Questo approccio è **deterministico e controllato**, ideale per interrogazioni periodiche o statistiche.

#### **b. Modalità asincrona (Trap)**

In questa modalità, l’agente invia **spontaneamente** un messaggio di tipo `Trap` verso il manager, per segnalare:

- eventi anomali (es. guasti, superamento soglie, riavvii),
    
- o cambiamenti di stato significativi.
    

Le _trap_ permettono un **monitoraggio in tempo reale**, senza attendere una richiesta diretta.

---

### **6. Software di gestione di rete**

Per sfruttare al meglio SNMP, esistono diversi **software di network management** che forniscono interfacce grafiche e strumenti di automazione.  
Questi programmi utilizzano i protocolli standard (SNMP, CMIP, ICMP, ecc.) per interrogare e controllare i dispositivi di rete.

#### **Esempi di software di gestione:**

- **CiscoWorks** – soluzione Cisco per il monitoraggio e la configurazione centralizzata dei router e switch.
    
- **OpenView** – piattaforma Hewlett-Packard per la supervisione di reti eterogenee.
    
- **Solstice** – sistema Sun Microsystems per la gestione distribuita di apparati e servizi.
    

Questi strumenti permettono di **visualizzare graficamente la topologia di rete**, raccogliere statistiche SNMP e ricevere automaticamente notifiche (trap) sugli eventi.

---

### **7. Conclusione**

SNMP, grazie alla sua architettura modulare basata su MIB e OID, rappresenta un sistema **universale di monitoraggio** per reti TCP/IP.  
I costrutti come `MODULE-IDENTITY` rendono le MIB **documentate e scalabili**, mentre i meccanismi di _polling_ (sincroni) e _trap_ (asincroni) permettono di bilanciare efficienza e tempestività.

In combinazione con i moderni software di gestione, SNMP continua a essere la **spina dorsale della network administration**, mantenendo in equilibrio semplicità, compatibilità e potenza.