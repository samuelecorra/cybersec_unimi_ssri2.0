# **Lezione 9: Tecnologia di commutazione**

### **1. Introduzione**

Dopo aver studiato il ruolo e il funzionamento generale degli **switch**, questa lezione approfondisce **come avviene realmente la commutazione dei pacchetti** all’interno di tali dispositivi.

Gli switch non si limitano a inoltrare i frame da una porta all’altra: esistono infatti **diverse tecnologie di commutazione**, ognuna con caratteristiche specifiche in termini di **prestazioni, affidabilità e latenza**.

Le due tecniche principali sono:

- **Cut-through switching**
    
- **Store-and-forward switching**
    

---

### **2. Funzionamento base di uno switch**

Ogni volta che uno switch riceve un **frame Ethernet**:

![](imgs/Pasted%20image%2020260213031926.png)

1. **Analizza l’indirizzo di destinazione** contenuto nell’intestazione.
    
2. Se il destinatario **si trova nello stesso segmento di rete**, il frame **non viene inoltrato** (per evitare traffico inutile).
    
3. Se invece il destinatario **appartiene a un segmento diverso**, lo switch **inoltra il frame** verso la porta corretta.
    

In questo modo lo switch **isola i segmenti locali** e **dirige il traffico solo dove serve**, migliorando notevolmente le prestazioni della rete.

---

### **3. Tecnologie di commutazione**

A seconda del modo in cui lo switch elabora il pacchetto, si distinguono due approcci fondamentali:

#### **a) Commutazione cut-through**

Nella modalità **cut-through**, lo switch **non attende la ricezione completa del frame**.  
Appena riconosce l’**intestazione con l’indirizzo di destinazione**, **inizia subito l’inoltro** verso la porta di uscita corrispondente.

**Vantaggi:**

- **Bassa latenza**: il ritardo introdotto dallo switch è minimo.
    
- **Trasmissione quasi immediata**, ideale per ambienti dove la velocità è prioritaria.
    

**Svantaggi:**

- Non vengono filtrati i **pacchetti danneggiati** o con **errori di CRC**, perché il controllo d’integrità si trova alla fine del frame.
    
- Eventuali errori si propagano alla rete, generando **traffico inutile o corrotto**.
    

In sintesi, la modalità cut-through privilegia **la velocità rispetto all’affidabilità**.

---

#### **b) Commutazione store-and-forward**

Nella modalità **store-and-forward**, lo switch **riceve e analizza l’intero pacchetto** prima di trasmetterlo.  
Solo dopo averlo **verificato completamente**, il frame viene inoltrato.

**Vantaggi:**

- **Massima affidabilità**: i pacchetti danneggiati vengono **filtrati e scartati**.
    
- Migliore controllo della **qualità del traffico** e della **congestione**.
    

**Svantaggi:**

- Introduce **una latenza leggermente superiore**, poiché lo switch deve attendere la ricezione completa del pacchetto.
    
- Richiede **maggiore memoria interna** per l’immagazzinamento temporaneo dei frame.
    

In sintesi, lo **store-and-forward** privilegia **la correttezza e la stabilità** del traffico rispetto alla velocità pura.

---

### **4. Confronto tra cut-through e store-and-forward**

![](imgs/Pasted%20image%2020260213032340.png)

|**Caratteristica**|**Cut-through**|**Store-and-forward**|
|:--|:--|:--|
|**Tempo di inoltro**|Minimo (inizia appena letta l’intestazione)|Maggiore (attende l’intero frame)|
|**Affidabilità**|Bassa – non filtra pacchetti danneggiati|Alta – verifica e filtra pacchetti errati|
|**Latenza**|Molto bassa|Moderata|
|**Consumo di memoria**|Ridotto|Elevato|
|**Ideale per**|Reti interne veloci e stabili|Reti complesse o eterogenee con rischio di errori|

In molti switch moderni, la **tecnologia di commutazione è ibrida**:  
viene utilizzato il **cut-through** per il traffico locale veloce e **store-and-forward** per i collegamenti più lenti o meno affidabili.

---

### **5. Livello operativo nello stack ISO/OSI**

È importante sottolineare che, in **entrambe le tecnologie**, la commutazione avviene **a livello 2 del modello ISO/OSI**, ovvero al **livello Data Link** (sottolivello MAC).

Questo significa che:

- **non avviene alcuna conversione di protocollo** (es. da Ethernet a Wi-Fi o da IP a un altro formato);
    
- lo switch lavora **sui frame Ethernet**, non sui pacchetti IP;
    
- le decisioni di inoltro e filtraggio vengono prese **in base agli indirizzi MAC** e non agli indirizzi IP.
    

In altre parole, la commutazione è un’operazione **puramente locale e trasparente** per i livelli superiori.

---

### **6. Sintesi concettuale**

- Gli **switch** analizzano i frame e li **inoltrano solo se necessario**, riducendo il traffico.
    
- Le **due tecniche principali** di commutazione sono:
    
    - **Cut-through** → massima velocità, minima affidabilità.
        
    - **Store-and-forward** → massima affidabilità, latenza maggiore.
        
- Entrambe operano al **livello 2 (Data Link, sottolivello MAC)** e non implicano conversione di protocollo.
    
- Gli **switch moderni** adottano spesso **approcci misti**, combinando rapidità e controllo d’errore.