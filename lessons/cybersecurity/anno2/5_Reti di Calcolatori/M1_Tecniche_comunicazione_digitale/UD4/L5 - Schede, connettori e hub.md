# **Lezione 5: Schede, connettori e hub**

### **1. Introduzione**

In questa lezione analizziamo tre componenti fondamentali dell’infrastruttura fisica di una rete locale (LAN):  
le **schede di rete**, i **connettori** e gli **hub**.  
Si tratta di elementi che operano ai **livelli più bassi del modello ISO/OSI**, in particolare al **livello fisico** e, in parte, al **collegamento dati**.

Lo scopo è comprendere **come i dispositivi si collegano fisicamente** tra loro, **quali standard vengono utilizzati** e **come avviene la distribuzione del segnale** all’interno di una rete.

---

### **2. Esempi di schede Ethernet**

#### **a) Scheda 10Base2**

La **scheda 10Base2** rappresenta uno degli standard Ethernet storici più diffusi negli anni ’90.  
Le sue caratteristiche principali sono:

- Velocità: **10 Mbps**
    
- Cablaggio: **coassiale sottile (Thinnet)**
    
- Topologia: **bus lineare**
    
- Connettore: **BNC (British Naval Connector, a baionetta, per connetterla al cavo coassiale sottile)**

![](imgs/Pasted%20image%2020260213024307.png)

Ogni scheda veniva collegata al cavo coassiale tramite un **connettore a T**, che permetteva di unire il segmento del bus a più stazioni di lavoro.

Questa tecnologia, pur essendo ormai obsoleta, è importante per capire **come le reti Ethernet si siano evolute** dalle topologie lineari a quelle a stella.

---

#### **b) Scheda 10BaseT**

La **scheda 10BaseT** segna il passaggio alla **topologia a stella**, oggi lo standard di fatto per tutte le LAN. Segue una scheda storica:

![](imgs/Pasted%20image%2020260213024401.png)

Le sue caratteristiche sono:

- Velocità: **10 Mbps**
    
- Cablaggio: **doppino intrecciato (twisted pair)**
    
- Connettore: **RJ-45**
    
- Collegamento: tramite **hub o switch** al centro della stella
    

La sigla 10BaseT si legge così:

- **10** → velocità in Mbps
    
- **Base** → trasmissione in banda base
    
- **T** → twisted pair
    

Questa architettura consente di isolare meglio i guasti: se un cavo si interrompe, viene escluso solo il singolo nodo e non tutta la rete.

---

#### **c) Schede Combo**

Le **schede combo** sono schede di rete progettate per supportare **più standard fisici di connessione**.

![](imgs/Pasted%20image%2020260213024427.png)

Possono avere, ad esempio, **più connettori** dello stesso tipo di scheda:

- **BNC** (per cavo coassiale)
    
- **RJ-45** (per doppino)
    
- **AUI** (Attachment Unit Interface)
    

In questo modo una scheda combo può essere utilizzata in **ambienti misti**, dove coesistono diverse tipologie di cablaggio o dove la rete è in fase di aggiornamento tecnologico.

---

### **3. Connettori e terminatori**

#### **a) Connettori a T**

Il **connettore a T** è utilizzato nelle reti Ethernet **10Base2** a topologia bus.  
Collega:

- una porta della **scheda NIC**;
    
- i due segmenti del **cavo coassiale** che proseguono verso le altre stazioni.

![](imgs/Pasted%20image%2020260213024446.png)

La forma a “T” permette il passaggio continuo del segnale lungo il bus e contemporaneamente la connessione della scheda di rete.

#### **b) Terminatori**

Nelle reti con topologia **a bus**, le estremità del cavo devono essere **terminate** con una **resistenza** per evitare **riflessioni del segnale**.

Senza terminatori, il segnale elettrico arriverebbe alla fine del cavo e **rimbalzerebbe indietro**, causando interferenze e pacchetti corrotti.

Ogni estremità della rete 10Base2 deve quindi essere **chiusa da un terminatore**.

![](imgs/Pasted%20image%2020260213024521.png)

---

### **4. Gli hub**

#### **a) Cos’è un hub**

Un **hub** è un dispositivo di rete che opera al **livello fisico (livello 1 del modello OSI)**.  
Il suo compito è **replicare il segnale elettrico** ricevuto su una porta e **trasmetterlo su tutte le altre**.

In altre parole, l’hub agisce come un **distributore di segnale**, senza alcuna capacità di filtraggio o di gestione del traffico.  
Per questo motivo, tutti i dispositivi collegati allo stesso hub condividono:

- lo **stesso dominio di collisione**,
    
- e lo **stesso dominio di broadcast**.
    

---

#### **b) Tipologie di hub**

Esistono due categorie principali:

1. **Hub passivi**
    
    - Non amplificano né rigenerano il segnale.
        
    - Si limitano a fornire la connessione elettrica tra le porte.
        
    - Sono usati solo per piccole reti, a basse velocità e brevi distanze.

![](imgs/Pasted%20image%2020260213024811.png)

2. **Hub attivi**
    
    - Rigenerano e ritrasmettono i segnali in ingresso.
        
    - Alcuni modelli possono essere **gestiti da remoto**, per monitorare traffico e stato delle porte.
        
    - Funzionano come **ripetitori multiporta**, estendendo il raggio massimo della rete.

![](imgs/Pasted%20image%2020260213024757.png)

---

#### **c) Gestione remota degli hub**

Gli **hub intelligenti** (o **smart hub**) supportano protocolli di **monitoraggio e controllo remoto**.  
Il più diffuso è lo **SNMP (Simple Network Management Protocol)**, che permette:

- la **rilevazione di guasti** o porte inattive;
    
- il **controllo del traffico** su ciascuna porta;
    
- l’invio di **statistiche e allarmi** all’amministratore di rete.

![](imgs/Pasted%20image%2020260213024937.png)

Attraverso software di gestione SNMP, è possibile **monitorare lo stato dell’intero backbone** e individuare rapidamente eventuali anomalie.

---

### **5. Hub e topologia a stella**

Nelle reti Ethernet moderne, gli **hub** sono impiegati in configurazioni **a stella fisica**:  
ogni stazione di lavoro è collegata con un cavo dedicato all’hub, che rappresenta il **centro del sistema**.

![](imgs/Pasted%20image%2020260213024958.png)

Se un cavo si danneggia, **solo quella connessione si interrompe**, mentre le altre restano operative.  
Inoltre, gli hub possono essere **impilati verticalmente (stackable)**, creando strutture modulari e scalabili.

---

### **6. Evoluzione: dagli hub agli switch**

Gli **hub** sono stati gradualmente sostituiti dagli **switch**, che operano a **livello 2 (Data Link)** invece che a livello 1.  
A differenza degli hub:

- gli **switch analizzano gli indirizzi MAC** dei frame;
    
- instradano i pacchetti **solo verso la porta di destinazione corretta**;
    
- creano **domini di collisione separati** per ogni porta.
    

Tuttavia, comprendere gli hub rimane importante per capire **le origini dell’Ethernet commutata** e la logica dei **domini di rete condivisi**.

---

### **7. Sintesi concettuale**

- Le **schede di rete (NIC)** traducono i dati in segnali elettrici e si collegano tramite **connettori** specifici (BNC, RJ-45, AUI).
    
- I **connettori a T** e i **terminatori** sono tipici delle reti a **bus (10Base2)**.
    
- Gli **hub** operano a livello fisico, replicando i segnali senza filtraggio.
    
- Gli **hub attivi** rigenerano i segnali; quelli **passivi** si limitano al collegamento elettrico.
    
- Il **protocollo SNMP** consente la gestione remota degli hub intelligenti.
    
- Gli **switch** hanno progressivamente sostituito gli hub, offrendo **maggiore efficienza e isolamento** dei domini di collisione.