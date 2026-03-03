# **Lezione 7: VLAN e Trunking**

### **1. Cos’è una VLAN**

Una **VLAN (Virtual LAN)** è una **rete Ethernet virtuale** costruita all’interno di uno switch fisico.  
Gli **switch moderni** possono infatti suddividere le loro porte in **gruppi logici separati**, in modo che ogni gruppo rappresenti una **rete locale indipendente** dalle altre.

Questo significa che:

- gli host collegati alle porte appartenenti alla stessa VLAN **possono comunicare tra loro** come se fossero su un’unica rete fisica;
    
- invece, gli host appartenenti a VLAN diverse **non possono comunicare direttamente**, anche se si trovano sullo stesso switch.
    

Le VLAN servono quindi a **segmentare logicamente** una rete per motivi di **sicurezza**, **gestione** e **prestazioni**, senza modificare la topologia fisica.

---

### **2. Identificazione delle VLAN**

Ogni VLAN è contrassegnata da un **numero identificativo**, detto **VLAN ID**, tipicamente compreso tra **1 e 4094**.  
Alcuni produttori, come **Cisco**, adottano questa numerazione come standard operativo.

Ad esempio:

- VLAN 10 → Ufficio amministrativo
    
- VLAN 20 → Laboratorio tecnico
    
- VLAN 30 → Studenti
    

In questo modo si possono creare più reti indipendenti all’interno dello stesso edificio o campus, pur utilizzando la **stessa infrastruttura cablata**.

---

### **3. Isolamento logico**

Nel caso mostrato, se le porte dello switch sono suddivise in VLAN separate:

- Gianni e Giulia, appartenenti alla stessa VLAN, **possono scambiarsi frame**;

![](imgs/Pasted%20image%2020260212160610.png)
    
- mentre Gianni e Bruno, appartenenti a VLAN diverse, **non possono comunicare** direttamente.
    

In sostanza, le VLAN creano **domini di broadcast separati**: un frame di broadcast inviato su una VLAN **non viene propagato** alle altre.  
Questo riduce il traffico inutile e migliora le prestazioni complessive.

---

### **4. Comunicazione tra VLAN diverse**

Se due dispositivi appartenenti a VLAN diverse devono comunicare, serve un **dispositivo intermedio** che “colleghi” le reti virtuali:

- un **bridge**, se l’interconnessione avviene ancora a livello 2 (Ethernet);
    
- oppure un **router**, se l’interconnessione avviene a livello 3 (rete IP).

![](imgs/Pasted%20image%2020260212160756.png)

Nelle reti moderne, la seconda soluzione è la più diffusa: gli switch di fascia alta integrano **funzionalità di routing di livello 3**, diventando **switch/router ibridi** in grado di connettere direttamente VLAN differenti.

![](imgs/Pasted%20image%2020260212160903.png)

---

### **5. Il problema del collegamento tra switch**

Supponiamo di avere due switch, ciascuno configurato con più VLAN (es. VLAN 10, 20 e 30).  
Se collegassimo una porta normale dello switch A a una porta normale dello switch B, quella connessione riguarderebbe **una sola VLAN**.

Per collegare **tutte le VLAN** tra i due switch, sarebbe necessario **un cavo per ciascuna VLAN** — soluzione chiaramente inefficiente e ingestibile.

---

### **6. Il concetto di trunking**

Il **trunking** risolve questo problema.  
Si tratta di una **tecnica che permette a una singola connessione fisica** tra due switch di **trasportare simultaneamente il traffico di più VLAN**.

Una **porta di trunk** è dunque una porta speciale dello switch configurata per **incapsulare i frame** in modo da includere l’identificativo della VLAN di appartenenza.

Così, anche se due switch condividono un solo cavo, ciascun frame porta con sé un “cartellino” che specifica **a quale VLAN appartiene**.

---

### **7. Come funziona il trunking**

Quando due porte di trunk vengono collegate tra loro:

- ciascun frame Ethernet inviato viene **etichettato (tagged)** con il numero della VLAN di origine;
    
- lo switch di destinazione, leggendo l’etichetta, **inoltra il frame** solo alle porte appartenenti a quella VLAN.

![](imgs/Pasted%20image%2020260212161222.png)

In questo modo, più VLAN possono comunicare attraverso **un unico collegamento fisico**, mantenendo **l’isolamento logico** dei domini.

![](imgs/Pasted%20image%2020260212161346.png)

---

### **8. Tipologie di protocolli di trunking**

Esistono due principali modalità per implementare il trunking tra switch.

#### **a) Protocolli a incapsulamento – ISL (Inter-Switch Link, Cisco)**

- Introdotto da Cisco prima della standardizzazione IEEE.
    
- Aggiunge un **header aggiuntivo** al frame Ethernet originale, che contiene l’ID della VLAN di destinazione.
    
- È proprietario, quindi funziona **solo tra dispositivi Cisco**.

![](imgs/Pasted%20image%2020260212161407.png)

In pratica, il frame viene “impacchettato” dentro un altro frame, e il dispositivo ricevente lo “spacchetta” rimuovendo l’header ISL.

#### **b) Protocolli a piggyback – IEEE 802.1Q**

- È lo **standard aperto** utilizzato oggi in quasi tutte le reti.
    
- Inserisce direttamente nel frame Ethernet un **campo di 4 byte** contenente il VLAN ID.
    
- Questo campo è inserito **tra gli indirizzi MAC e il campo Type** del frame originale.
    

Schema del frame “taggato”:

```
| Dest MAC | Src MAC | 802.1Q Tag (4 byte) | Type | Payload | CRC |
```

Poiché l’aggiunta di 4 byte modifica la lunghezza del frame, lo switch deve **ricalcolare il CRC** sia in ingresso sia in uscita dal trunk.

---

### **9. Connessione a livello superiore – Router-on-a-stick**

Un’altra soluzione per collegare VLAN diverse consiste nel connettere **un router** a una **porta di trunk** di uno switch.  
In questo caso, il router riceve frame provenienti da più VLAN, riconoscendole grazie ai **tag 802.1Q**.

![](imgs/Pasted%20image%2020260212161702.png)

Il router utilizza quindi **interfacce virtuali** (una per VLAN), ognuna configurata con il proprio indirizzo IP.  
Quando riceve un frame, lo instrada verso la VLAN di destinazione.

Questa configurazione, detta **router-on-a-stick**, è molto comune nelle reti aziendali medio-piccole:  
un solo router può gestire il traffico inter-VLAN su un **unico collegamento fisico**, semplificando la rete.

![](imgs/Pasted%20image%2020260212161843.png)

---

### **10. Sintesi concettuale**

- Le **VLAN** segmentano logicamente la rete in più domini Ethernet indipendenti.
    
- Per comunicare tra VLAN diverse serve un **router o uno switch di livello 3**.
    
- Il **trunking** consente di collegare più VLAN tra switch tramite un solo cavo.
    
- I frame “taggati” indicano la VLAN di appartenenza mediante **ISL (Cisco)** o **IEEE 802.1Q (standard)**.
    
- La soluzione **router-on-a-stick** permette di gestire più VLAN su una singola interfaccia trunk, con instradamento a livello IP.

---

### **11. Domande di fine unità**

![](imgs/Pasted%20image%2020251125063214.png)

![](imgs/Pasted%20image%2020251125063225.png)

