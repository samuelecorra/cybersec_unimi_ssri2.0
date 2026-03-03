# **Lezione 3: Complementi di IP**

---

### **1. Subnetting: il principio**

Nelle reti IP composte da più sottoreti collegate tra loro tramite **router**, è indispensabile che **ogni rete locale abbia un net_ID diverso**.  
Questo si ottiene **suddividendo l’host_ID originale** in due parti:

$$  
\text{Host\_ID} = \text{Subnet\_ID} + \text{Host\_nella\_subnet}  
$$

La porzione dell’indirizzo IP usata per identificare la sottorete si chiama **subnet_ID**.

#### **Esempio**

In una rete di classe C come `192.168.10.0`, si può utilizzare una parte dei bit riservati all’host per creare sottoreti:

$$  
\text{192.168.10.0} \Rightarrow  
\begin{cases}  
\text{192.168.10.0/26} & \text{subnet 1} \\\\  
\text{192.168.10.64/26} & \text{subnet 2} \\\\  
\text{192.168.10.128/26} & \text{subnet 3} \\\\  
\text{192.168.10.192/26} & \text{subnet 4}  
\end{cases}  
$$

In questo modo una singola rete logica viene **suddivisa in quattro subnet** più piccole.

---

### **2. Struttura della maschera di sottorete**

La **maschera di sottorete** è un numero binario di **32 bit**, composto da:

- Tutti **1** per la parte di **prefisso** (net_ID + subnet_ID);
    
- Tutti **0** per la parte di **host_ID**.
    

#### **Esempio**

$$  
\text{Maschera } = 255.255.255.224 \Rightarrow \text{11111111.11111111.11111111.11100000}  
$$  
In questo caso, i primi 27 bit sono riservati a net_ID + subnet_ID, e i restanti 5 bit agli host.

---

### **3. Notazione CIDR (Classless Inter-Domain Routing)**

Spesso, invece di scrivere **indirizzo + maschera**, si usa la **notazione compatta CIDR**, che specifica la lunghezza del prefisso direttamente accanto all’indirizzo IP.

#### **Esempio**

$$  
194.36.20.0/27  
$$

Significa che:

- Il **prefisso** (rete + subnet) è lungo **27 bit**;
    
- Restano **5 bit** per identificare gli host.
    

Questo tipo di indirizzamento non segue più le classi A-B-C tradizionali e viene quindi chiamato **classless** (senza classi).

---

### **4. Importanza della maschera corretta**

Una **maschera errata** può compromettere completamente la comunicazione:

- Se il **net_ID** calcolato da mittente e destinatario non coincide, il pacchetto verrà **instradato sulla rete sbagliata**.
    
- Di conseguenza, l’host destinatario **non sarà mai raggiunto**.
    

Per questo motivo, durante la configurazione IP, è essenziale che **tutti gli host di una subnet abbiano la stessa maschera**.

---

### **5. Funzione logica della maschera**

Senza conoscere la maschera di sottorete, un host:

- **non può sapere** se due indirizzi IP appartengono alla stessa subnet;
    
- **non può decidere** se inviare un pacchetto **direttamente** o **tramite un gateway**.
    

Formalmente, la verifica di appartenenza alla stessa rete avviene con l’operazione:

$$  
\begin{cases}  
\text{IP}_1 \; \text{AND} \; \text{Mask} = P \\\\  
\text{IP}_2 \; \text{AND} \; \text{Mask} = P  
\end{cases}  
\Rightarrow  
\text{stessa rete se i risultati coincidono.}  
$$

---

### **6. Il ruolo dei router**

Per rendere **operativa** un’inter-rete IP, non basta configurare gli indirizzi sugli host:  
è necessario anche **configurare e attivare i router** che interconnettono le varie reti locali.

#### **Struttura tipica di un router**

Il tipo più semplice di router è un **computer con due o più schede di rete**, ognuna collegata a una sottorete diversa.  
Ogni interfaccia avrà quindi:

- un **indirizzo IP distinto**,
    
- e una **maschera di sottorete appropriata**.
    

#### **Configurazione**

La procedura di configurazione varia in base al **modello** e al **sistema operativo** del router (ad esempio Cisco IOS, Linux, Windows Server), ma in ogni caso serve a definire:

- le **interfacce di rete** attive;
    
- le **tabelle di instradamento** per i pacchetti;
    
- eventuali **regole di filtraggio o NAT**.
    

---

### **7. Frammentazione dei pacchetti IP**

Quando un pacchetto IP viaggia da un host a un altro, può attraversare reti con **dimensioni massime dei frame (MTU)** differenti.  
Se il pacchetto è più grande della MTU della rete successiva, il router **lo frammenta** in più parti.

#### **Requisiti**

- Ogni rete deve accettare pacchetti di **almeno 68 byte**.  
    Questo valore deriva da:  
    $$  
    68 = 60 \text{ (dimensione massima dell’header IP)} + 8 \text{ (minimo campo dati)}  
    $$
    
- Se una rete usa frame più piccoli di 68 byte, deve implementare una **segmentazione trasparente a livello inferiore**.
    

#### **Processo di frammentazione**

1. Il pacchetto IP viene **diviso in frammenti** compatibili con la MTU della rete attraversata.
    
2. Ogni frammento riceve:
    
    - un **header IP** con un campo **offset** che ne indica la posizione;
        
    - un **identificatore** comune per permettere la **ricomposizione** a destinazione.
        
3. L’host destinatario **riassembla** i frammenti per ricostruire il pacchetto originale.
    

---

### **8. Sintesi della lezione**

Abbiamo completato l’approfondimento del protocollo IP introducendo tre concetti chiave:

1. **Subnetting** → suddivisione logica di una rete in sottoreti più piccole.
    
2. **CIDR e maschere** → sistema flessibile e classless per definire il prefisso di rete.
    
3. **Frammentazione IP** → meccanismo che consente di trasmettere pacchetti su reti con MTU diverse.
    

Questi strumenti rendono il protocollo IP **scalabile, efficiente e compatibile** con reti eterogenee, garantendo la comunicazione tra milioni di dispositivi distribuiti nel mondo.


---

## **Dispensa – Complementi di IP**

### **1. Le maschere di sottorete**

#### **1.1 Maschere di default (Default Subnet Mask)**

Ogni classe di indirizzi IP ha una **maschera di sottorete standard**, detta _di default_, che corrisponde alla porzione dell’indirizzo riservata alla rete.

|Classe|Maschera di default|Rappresentazione binaria|
|---|---|---|
|A|255.0.0.0|11111111.00000000.00000000.00000000|
|B|255.255.0.0|11111111.11111111.00000000.00000000|
|C|255.255.255.0|11111111.11111111.11111111.00000000|

#### **Esempio**

Per una rete privata con indirizzi da `10.93.24.0` a `10.93.24.255`, la maschera di default è **255.255.255.0** (classe C).

Applicando l’operazione AND tra indirizzo IP e maschera:

$$  
(10.93.24.12 \; \text{AND} \; 255.255.255.0) = 10.93.24.0  
$$

In binario:

$$  
\begin{cases}  
00001010.01011101.00011000.00001100 \\\\  
11111111.11111111.11111111.00000000 \\  
\hline  
00001010.01011101.00011000.00000000  
\end{cases}  
$$

> 🔹 Gli host con **host_ID = 0** e **host_ID = 255** non vengono usati (rispettivamente riservati a _network address_ e _broadcast_), quindi la rete dispone di **254 indirizzi utilizzabili**.

---

### **1.2 Uso delle maschere**

Non è possibile determinare se due indirizzi IP appartengono alla **stessa sottorete** senza conoscere la **maschera di sottorete**.

#### **Esempio**

L’host `200.20.1.5` invia un pacchetto a `200.20.6.8`.  
Bisogna capire se i due indirizzi si trovano sulla stessa subnet o meno:

- Se la maschera è **255.255.255.0**  
    → i net_ID sono diversi (`200.20.1` e `200.20.6`) ⇒ serve un **router**.
    
- Se la maschera è **255.255.0.0**  
    → entrambi appartengono al net_ID `200.20` ⇒ comunicano **direttamente** tramite Ethernet.
    

---

#### **Notazione compatta CIDR**

Invece di indicare la coppia _indirizzo + maschera_, si scrive l’indirizzo seguito dalla **lunghezza del prefisso in bit**:

|Notazione|Significato|
|---|---|
|`196.20.45.1/24`|Maschera 255.255.255.0 (classe C standard)|
|`196.20.45.1/27`|Maschera 255.255.255.224 → subnet_ID di 3 bit (6 sottoreti)|

> Gli indirizzi di questo tipo **non seguono più le classi IP originali** e sono detti **classless**.

---

#### **Esempio di subnetting su classe B**

Indirizzo di partenza: `129.10.0.0` (classe B → maschera di default 255.255.0.0).

- Se la rete rimane unica, dispone di:  
    $$  
    2^{16} - 2 = 65,534 \text{ host utilizzabili}  
    $$
    
- Se invece si vogliono creare sottoreti, si possono dedicare **5 bit dell’host_ID** al subnet_ID:
    

$$  
2^5 - 2 = 30 \text{ sottoreti}, \quad \text{ognuna con } 2^{11} - 2 = 2046 \text{ host.}  
$$

La nuova maschera sarà **255.255.248.0**, poiché i primi 5 bit del terzo byte vengono posti a 1.

---

### **1.3 Tabella delle maschere**

Questa tabella mostra le principali combinazioni tra numero di bit usati per il _subnet_ID_ e la corrispondente maschera, con il numero di **sottoreti** e di **host disponibili**.

|Bit subnet_ID (S)|Byte della maschera|N° sottoreti $(2^S - 2)$|Host per subnet (classe A)|Host per subnet (classe B)|Host per subnet (classe C)|
|---|---|---|---|---|---|
|2|192|2|4M|16K|62|
|3|224|6|2M|8K|30|
|4|240|14|1M|4K|14|
|5|248|30|500K|2K|6|
|6|252|62|250K|1K|2|
|7|254|126|130K|510|–|
|8|255|254|65K|254|–|

---

#### **Esempio: rete classe C con 4 sottoreti**

- subnet_ID → 3 bit
    
- maschera → **255.255.255.224**
    
- ogni subnet → **30 host utilizzabili**
    

|subnet_ID|Intervallo host|Maschera|
|---|---|---|
|196.93.24.32|196.93.24.33 – 63|255.255.255.224|
|196.93.24.64|196.93.24.65 – 95|255.255.255.224|
|196.93.24.96|196.93.24.97 – 127|255.255.255.224|
|196.93.24.128|196.93.24.129 – 159|255.255.255.224|

---

### **1.4 Il ruolo dei router**

In una rete IP composta da più sottoreti, oltre alla configurazione di indirizzi e maschere sugli host, è necessario **configurare i router di interconnessione**.

- Ogni router deve conoscere la **mappa delle subnet** collegate alle sue interfacce.
    
- È necessario **attivare l’instradamento IP**, ad esempio:
    
    - Su router Cisco → `set ip routing on`
        
    - Su Windows Server → abilitare “**Instradamento IP**”
        
    - Su Linux → modificare `/proc/sys/net/ipv4/ip_forward = 1`
        

> Ogni router deve avere **una scheda di rete per ciascuna subnet collegata**.

---

### **1.5 Multihoming**

Il **multihoming** è la capacità di una **singola scheda di rete** di gestire **più indirizzi IP** (fino a 5 circa).  
Ogni indirizzo può avere anche un **gateway predefinito differente**.

- Se il primo gateway non è disponibile, viene usato quello successivo.
    
- I pacchetti inviati a uno qualsiasi degli indirizzi configurati vengono comunque **accettati dalla scheda**.
    

Questo meccanismo è utile per:

- sistemi server con più ruoli (es. web + database),
    
- dispositivi con più reti virtuali (VLAN),
    
- configurazioni di failover o alta disponibilità.
    

---

### **2. Problemi di indirizzamento IP**

Una **configurazione errata** può compromettere la comunicazione o addirittura bloccare la rete.

#### **Errori più comuni**

1. **net_ID non valido**  
    → I pacchetti vengono instradati sulla rete sbagliata.
    
2. **Duplicazione di indirizzi IP**  
    → Due host con lo stesso IP entrano in conflitto.
    

#### **Effetti**

- In Windows, al rilevamento di un duplicato, il sistema mostra un errore e disattiva il modulo TCP/IP.
    
- In reti miste (Windows + Linux), un conflitto può bloccare uno dei sistemi.
    

> Solo utenti con privilegi **amministrativi (root o admin)** possono modificare gli indirizzi IP per evitare configurazioni accidentali.

---

### **3. Frammentazione (approfondimento)**

Quando un pacchetto IP attraversa reti con **MTU diverse**, può essere necessario frammentarlo.  
Il processo può avvenire in qualsiasi router lungo il percorso.

#### **Condizione minima**

Ogni rete deve supportare **frame di almeno 68 byte**, valore derivato da:  
$$  
68 = 60 \text{ (header IP massimo)} + 8 \text{ (dati minimi di un frammento)}  
$$

---

#### **Processo di frammentazione**

1. Il **flag DF (Don’t Fragment)** indica se la frammentazione è permessa:
    
    - Se DF = 1 → il pacchetto viene scartato e il router invia un messaggio **ICMP “Fragmentation needed”**.
        
    - Se DF = 0 → il pacchetto viene diviso in frammenti.
        
2. Ogni frammento:
    
    - ha una **lunghezza multipla di 8 byte** (tranne l’ultimo),
        
    - contiene l’**header IP originale** con alcune modifiche:
        
        - **MF (More Fragments)** = 1 per tutti i frammenti tranne l’ultimo;
            
        - **Fragment Offset** indica la posizione nel datagramma originale (in unità di 8 byte);
            
        - **Checksum** e **lunghezza** vengono aggiornati.
            
3. I frammenti sono inviati **come pacchetti IP separati** e possono subire **ulteriori frammentazioni** se incontrano reti con MTU ancora più piccole.
    

---

#### **Riassemblaggio**

All’arrivo dei frammenti, l’host destinatario:

1. Alloca un **buffer** in memoria per ricomporre i dati.
    
2. Avvia un **timer (TTL del datagramma)**:  
    se il tempo scade prima che tutti i frammenti siano arrivati, il pacchetto viene scartato.
    
3. Usa il campo **fragment offset** per rimettere i frammenti nell’ordine corretto.
    
4. Quando tutti i frammenti sono ricevuti, il pacchetto viene ricostruito e passato al livello superiore.
    

---

### **Conclusione**

Questa dispensa approfondisce i dettagli tecnici introdotti nella **Lezione 3**:

- la **costruzione delle maschere di sottorete** e il calcolo dei bit di subnetting;
    
- il **ruolo dei router** e il concetto di **multihoming**;
    
- i **problemi di indirizzamento** più comuni;
    
- e la **frammentazione IP** con le sue regole operative.
    

Questi elementi completano la comprensione del protocollo IP e preparano alla successiva analisi dei protocolli di **trasporto TCP e UDP**, che si basano direttamente su IP per la consegna affidabile dei dati.

---


ESERCIZI:

Mostrare che convertendo gli indirizzi in binario e usando la maschera di sottorete 255.255.248.0, gli indirizzi 129.10.147.32 e 129.10.148.85 sono sulla stessa sottorete.


Configurare una rete IP composta da un'unica rete locale, usando indirizzi IP privati e la tecnica statica di attribuzione degli indirizzi. Prevedendo di collegare alla rete al massimo 256 macchine, determinate:

1. l'intervallo degli indirizzi IP;
2. la maschera di sottorete di default da usare.



ESERCIZIO 3 
Una rete IP composta da una sola rete locale contiene: - 5 server Windows NT; - 200 computer con Windows NT workstation; - 100 computer Windows 95/98; - 12 computer Unix. Quali classi di indirizzi IP possono essere usati per questa rete? 

SOLUZIONE Gli indirizzi di classe A o di classe B. Quelli di classe C non possono essere usati perché gli indirizzi possono avere al massimo 254 computer su ogni rete.