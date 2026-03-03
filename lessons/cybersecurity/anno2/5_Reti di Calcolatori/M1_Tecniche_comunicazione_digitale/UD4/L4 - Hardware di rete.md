# **Lezione 4: Hardware di rete**

### **1. Introduzione**

L’hardware di rete comprende tutti i **componenti fisici** che permettono la **connessione e la comunicazione** tra i dispositivi di una rete locale (LAN).  
In questa lezione analizziamo i principali elementi che costituiscono l’infrastruttura fisica:

- **schede di rete (NIC)**,
    
- **cavi e connettori**,
    
- **apparati di rete** come hub, switch, router e gateway.
    

Comprendere la funzione di ciascuno è essenziale per capire **come avviene realmente la trasmissione dei dati** a livello fisico e di collegamento.

---

### **2. Componenti base dell’hardware di rete**

I principali componenti che formano la base fisica di una rete locale sono:

- **NIC (Network Interface Card)**: la scheda d’interfaccia di rete, che collega il computer al mezzo trasmissivo.
    
- **Cavi**: il supporto fisico che trasporta i segnali elettrici o ottici.
    
- **Connettori**: permettono il collegamento fisico tra schede, hub e switch.
    
- **Server**: nodi centrali che forniscono servizi agli altri dispositivi della rete.
    
- **Stazioni di lavoro**: i computer client che utilizzano tali servizi.
    

Oltre a questi, troviamo dispositivi dedicati alla **connessione e al controllo del traffico**, come:

- **Repeater** o **driver di linea**
    
- **Transceiver**
    
- **Hub intelligenti**
    
- **Bridge**
    
- **Switch**
    
- **Router**
    
- **Gateway**
    

Questi apparati operano a diversi livelli del modello ISO/OSI: i primi due a livello fisico, gli altri a livello di collegamento o rete.

---

### **3. Schede di rete (NIC)**

La **scheda di rete** (Network Interface Card) è l’elemento che **consente a un computer di trasmettere e ricevere dati** sulla rete.  
Traduce i bit generati dal processore in **segnali elettrici o ottici** compatibili con il mezzo trasmissivo, e viceversa.

Ogni scheda NIC possiede:

- un **indirizzo MAC univoco**, assegnato dal produttore;
    
- un **driver software** che la rende riconoscibile dal sistema operativo;
    
- un **connettore fisico** (RJ-45, BNC o AUI) per il collegamento via cavo.
    

Esistono diverse **tipologie di schede NIC**, classificate secondo lo **standard Ethernet** di riferimento.

---

### **4. Tipi di schede Ethernet**

#### **a) Schede 10BaseT**

- Reti a **topologia stellare**.
    
- Velocità: **10 Mbps**.
    
- Standard: **Ethernet**.
    
- Cablaggio: **doppino intrecciato (twisted pair)**.
    
- Connettore: **RJ-45**.
    

È il tipo più comune nelle reti LAN moderne, soprattutto nelle versioni aggiornate (100BaseTX, 1000BaseT).

#### **b) Schede 10Base2**

- Reti a **topologia bus**.
    
- Velocità: **10 Mbps**.
    
- Standard: **Ethernet**.
    
- Cablaggio: **coassiale sottile (thinnet)**.
    
- Connettore: **BNC (a baionetta)**.
    

Utilizzate in passato per reti economiche e lineari, ma oggi sostituite quasi ovunque dal doppino.

#### **c) Schede 10Base5**

- Velocità: **10 Mbps**.
    
- Standard: **Ethernet**.
    
- Cablaggio: **coassiale spesso (thicknet)**.
    
- Connettore: **AUI (Attachment Unit Interface)**.
    

Era lo standard originario delle reti Ethernet cablate di grandi dimensioni, oggi obsoleto.

#### **d) Schede 100BaseTX**

- Velocità: **100 Mbps** (Fast Ethernet).
    
- Cablaggio: **doppino intrecciato**.
    
- Connettore: **RJ-45**.
    
- Consigliato l’uso di **cavi di Categoria 5** o superiore.
    

Questa versione ha reso possibile la **transizione verso Ethernet ad alte prestazioni**, mantenendo la compatibilità con i sistemi 10BaseT.

---

### **5. Schede Token Ring**

Le **schede Token Ring** erano utilizzate nelle reti IBM, basate su una topologia ad anello logico.  
Ogni dispositivo trasmetteva i dati solo quando possedeva un **token**, un segnale che garantiva l’accesso ordinato al mezzo trasmissivo.

Due principali varianti:

- **Token Ring originale** → 4 Mbps
    
- **Token Ring avanzata** → 16 Mbps
    

Sebbene superata da Ethernet, questa architettura rimane importante dal punto di vista storico per la gestione deterministica del traffico.

---

### **6. Tipi di bus per le NIC**

Le schede di rete vengono installate su **bus di espansione** della scheda madre del computer.  
I principali tipi di bus sono:

- **PCI (Peripheral Component Interconnect)** → il più diffuso per le NIC tradizionali.
    
- **PCI-X e PCI Express (PCIe)** → versioni successive, più veloci e adatte a server di rete.
    

La **larghezza del bus PCI** è solitamente di **32 bit**, ma esistono versioni a **64 bit** per connessioni ad alta velocità.  
Più ampio è il bus, maggiore è la **quantità di dati trasferibili per ciclo di clock**.

---

### **7. Connessioni via cavo per le NIC**

Le schede di rete possono disporre di diversi tipi di **porte fisiche**, a seconda del cavo utilizzato:

|Tipo di connessione|Tipo di cavo|Connettore|Note|
|:--|:--|:--|:--|
|Coassiale sottile|Thinnet|BNC|Flessibile ma soggetto a interferenze|
|Coassiale spesso|Thicknet|AUI|Più schermato, oggi obsoleto|
|Doppino intrecciato|Twisted pair|RJ-45|Standard moderno per Ethernet|
|Fibra ottica|Multimodale o monomodale|SC, LC|Alta velocità, usata nei backbone|

Alcune schede sono **“combo”**, cioè dotate di **più tipi di connettori** per garantire compatibilità con diversi tipi di cablaggio (es. BNC + RJ-45 + AUI).

---

### **8. Esempio: descrizione di una scheda NIC**

Consideriamo una scheda **PCI 100BaseT**:

- **PCI** → tecnologia del bus di sistema.
    
- **100** → velocità massima in Mbps.
    
- **Base** → indica che segue lo standard **Ethernet baseband**.
    
- **T** → tipo di cavo: **Twisted pair** (doppino intrecciato).
    
- **32-bit** → larghezza del bus PCI, cioè il numero di bit trasferiti in parallelo.
    

In sintesi, una **scheda PCI 100BaseT 32-bit** è una **scheda Ethernet Fast**, con connessione a doppino e interfaccia PCI.

---

### **9. Sintesi concettuale**

- L’hardware di rete comprende **schede di rete, cavi, connettori e apparati di comunicazione**.
    
- Le **NIC** traducono i dati in segnali fisici e identificano il nodo tramite l’indirizzo **MAC**.
    
- Gli standard Ethernet più comuni sono **10BaseT**, **10Base2**, **10Base5** e **100BaseTX**.
    
- Le **Token Ring** rappresentano un’importante alternativa storica.
    
- Le NIC moderne utilizzano bus **PCI/PCIe** e cavi a **doppino intrecciato (RJ-45)**.
    
- Le schede **combo** offrono più opzioni di connettività.
    
- La conoscenza di questi componenti è la base per comprendere la **struttura fisica e logica delle reti locali**.